/*
 * package com.rest.controller;
 * 
 * import org.apache.log4j.Logger; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.rest.model.User; import com.rest.model.City; import
 * com.rest.model.CityArea; import com.rest.model.Status; import
 * com.rest.service.LoginService; import com.rest.service.RegisterService;
 * import com.rest.service.UtilityService; import com.rest.utility.CommonUtil;
 * import com.rest.utility.ConstantsUtil; import com.rest.utility.ImageUtil;
 * import com.rest.utility.PasswordEncoder; import com.rest.utility.sendEmail;
 * 
 * @RestController
 * 
 * @CrossOrigin(origins = "*", allowedHeaders = "*") public class
 * RegisterController {
 * 
 * @Autowired RegisterService registerService;
 * 
 * @Autowired PasswordEncoder passwordEncoder;
 * 
 * @Autowired sendEmail sendEmail;
 * 
 * @Autowired LoginService loginService;
 * 
 * @Autowired UtilityService utilityService;
 * 
 * 
 * static final Logger logger = Logger.getLogger(RegisterController.class);
 * 
 * 
 * Register User(Member) if NOT EXISTS
 * 
 * @RequestMapping(value = "/registerUserAndClient", method =
 * RequestMethod.POST) public @ResponseBody Status registerMember(@RequestBody
 * User user) {
 * 
 * try { String userMobile = user.getUserMobile(); String userEmail =
 * user.getUserEmail(); int userId = 0;
 * 
 * if(registerService.checkMobile(userMobile) ||
 * registerService.checkEmail(userEmail)) {
 * 
 * userId = loginService.getUserIdByEmailOrMobile(userMobile, 1); String userOtp
 * = registerService.getuserOTPById(userId);
 * 
 * if(userOtp.length() > 0){ logger.error("OTP Verification Pending."); return
 * new Status(userId, 1014, "OTP verification pending.");
 * 
 * } else if(userOtp.length() == 0){
 * logger.warn("Mobile number "+userMobile+" or Email Id "+ userEmail
 * +" already registered."); return new Status(1001,
 * "Mobile number or Email id already registered."); } }
 * 
 * String OTP = CommonUtil.generateOTP(ConstantsUtil.OTP_SIZE); //generate OTP
 * String password = user.getUserPassword(); String to = user.getUserEmail();
 * String subject = "OTP Verification"; String messageBody =
 * "Your OTP for verification is: "+OTP;
 * 
 * String encryptedpassword = passwordEncoder.getHashPassword(password); //To
 * Encrypt password logger.info("Encrypted password : "+encryptedpassword);
 * 
 * if(encryptedpassword == "") {
 * logger.error("Exception while password encryption process."); return new
 * Status(1022, "Registration process failed."); }
 * 
 * user.setUserPassword(encryptedpassword);
 * user.setUserStatus(ConstantsUtil.INACTIVE_STATUS); user.setUserOTP(OTP);
 * 
 * sendEmail.sendMail(ConstantsUtil.FROM, to, subject, messageBody); //send
 * email of OTP
 * 
 * registerService.addUser(user); //To Add User
 * 
 * int id = loginService.getUserIdByEmailOrMobile(userMobile, 1); //get userId
 * by email logger.info("Member added successfully."); return new Status(id,
 * 1002, "Member added successfully.");
 * 
 * } catch (Exception e) { logger.error("Exception @addUser "+e.toString());
 * return new Status(1022, "Registration process failed."); } }
 * 
 * 
 * OTP verification for User Validation using Email & User ID
 * 
 * @RequestMapping(value = "/OTPVerification", method = RequestMethod.POST)
 * public @ResponseBody Status OTPVerification(@RequestBody User user) {
 * 
 * try { int userId = user.getUserId(); String OTP = user.getUserOTP(); String
 * userOTP = registerService.getuserOTPById(userId);
 * 
 * if(userOTP.equals(OTP)) {
 * 
 * registerService.updateOtpAndStatusByUserId(userId, "",
 * ConstantsUtil.ACTIVE_STATUS); return new Status(userId, 1003,
 * "OTP verification successful.");
 * 
 * } else { logger.error("Wrong OTP"); return new Status(userId, 1005,
 * "You entered wrong OTP."); }
 * 
 * } catch (Exception ex) {
 * logger.error("Exceptions while OTP verification : "+ex.toString()); } return
 * new Status(1023, "OTP verification process failed."); }
 * 
 * 
 * Resend OTP to User using userId
 * 
 * @RequestMapping(value = "/resendOTP", method = RequestMethod.POST)
 * public @ResponseBody Status resendOTP(@RequestBody User user) throws
 * Exception { try { int userId = user.getUserId(); String userOTP =
 * CommonUtil.generateOTP(ConstantsUtil.OTP_SIZE); String to =
 * registerService.getUserById(userId).getUserEmail(); String subject =
 * "OTP Verification"; String messageBody =
 * "Your OTP for verification is: "+userOTP;
 * 
 * int changeType = CommonUtil.checkEmailOrMobile(to); boolean flag = false;
 * 
 * if(changeType == 1) { if(registerService.checkMobile(to)) { flag = true; } }
 * else if(changeType == 2) { if(registerService.checkEmail(to)) { flag = true;
 * } } else if(changeType == 0) {
 * logger.error("Invalid email or mobile number. Try Again."); return new
 * Status(userId, 1004, "Invalid email or mobile number."); }
 * 
 * if(flag == false){ logger.error(to+" not registered. Please verify again.");
 * return new Status(userId, 1018, "Member not registered.");
 * 
 * } else if(flag == true){ sendEmail.sendMail(ConstantsUtil.FROM, to, subject,
 * messageBody); String endTime =
 * registerService.getEndTime(updatedDateTime,ConstantsUtil.TIMELIMIT);
 * registerService.updateOtpAndStatusByUserId(userId, userOTP,
 * ConstantsUtil.INACTIVE_STATUS);
 * 
 * logger.info("Resend OTP Successfully !"); return new Status(userId, 1006,
 * "Resend OTP successfully."); }
 * 
 * } catch (Exception ex) {
 * logger.error("Exceptions @resendOTP : "+ex.toString()); } return new
 * Status(1024, "OTP resend process failed."); }
 * 
 * 
 * 
 * Request to Change existing Email Id/Mobile Number (Validate user & send OTP
 * to new Email Id/Mobile Number)
 * 
 * @RequestMapping(value = "/requestChangeEmailOrMobile", method =
 * RequestMethod.POST) public @ResponseBody Status
 * requestChangeMobileOrEmail(@RequestBody User user) throws Exception {
 * 
 * try { int userId = user.getUserId(); String newEmailOrMobile =
 * user.getEmailOrMobile(); int changeType =
 * CommonUtil.checkEmailOrMobile(newEmailOrMobile); //To get change Type as
 * "mobile=1" or "email=2"
 * 
 * if(changeType == 1 && registerService.checkMobile(newEmailOrMobile) ==
 * false){ //check mobile exists or not
 * 
 * try{ String userOTP = CommonUtil.generateOTP(ConstantsUtil.OTP_SIZE); String
 * subject = "OTP Verification"; String messageBody =
 * "Your OTP for verification is: "+userOTP;
 * sendEmail.sendMail(ConstantsUtil.FROM, newEmailOrMobile, subject,
 * messageBody);
 * 
 * registerService.updateOtpAndStatusByUserId(userId, userOTP, 1); return new
 * Status(userId, 1010, "OTP send successfuly.");
 * 
 * }catch (Exception ex) {
 * 
 * logger.error("Exception @changeEmailOrMobile. : "+ex.toString()); return new
 * Status(userId, 1027, "Change Mobile number process failed."); }
 * 
 * } else if(changeType == 2 && registerService.checkEmail(newEmailOrMobile) ==
 * false){ //check email exists or not
 * 
 * try{ String userOTP = CommonUtil.generateOTP(ConstantsUtil.OTP_SIZE); String
 * subject = "OTP Verification"; String messageBody =
 * "Your OTP for verification is:"+" "+userOTP;
 * sendEmail.sendMail(ConstantsUtil.FROM, newEmailOrMobile, subject,
 * messageBody);
 * 
 * registerService.updateOtpAndStatusByUserId(userId, userOTP, 1); return new
 * Status(userId, 1010, "OTP send successfuly.");
 * 
 * }catch (Exception ex) {
 * 
 * logger.error("Exception @changeEmailOrMobile. : "+ex.toString()); return new
 * Status(userId, 1028, "Change Email id process failed."); }
 * 
 * } else if(changeType == 0) {
 * logger.error("Invalid email or mobile number. Try Again."); return new
 * Status(userId, 1004, "Invalid email or mobile number."); }
 * 
 * }catch (Exception ex) {
 * 
 * logger.error("Exceptions @editMobileOrEmail.."+ex.toString()); } return new
 * Status(1004,"Invalid email or mobile number."); }
 * 
 * 
 * 
 * Update Member Profile
 * 
 * 
 * 
 * 
 * @RequestMapping(value = "/updateProfile", consumes = {
 * "multipart/form-data"}, method = RequestMethod.POST) public @ResponseBody
 * Status updateProfile(@RequestBody User user, HttpServletRequest
 * servletRequest , @RequestParam("files") MultipartFile[] files) {
 * 
 * @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
 * public @ResponseBody Status updateProfile(@RequestBody User user) throws
 * Exception { int userId = user.getUserId();
 * 
 * try {
 * 
 * //Get the uploaded files and store them // List<MultipartFile> files =
 * 
 * Arrays.asList(files); List<String> fileNames = new ArrayList<String>(); if
 * (null != files && files.length > 0) { for (MultipartFile multipartFile :
 * files) {
 * 
 * String fileName = multipartFile.getOriginalFilename();
 * fileNames.add(fileName);
 * 
 * File imageFile = new File(ConstantsUtil.IMAGE_LOCATION, fileName); try {
 * multipartFile.transferTo(imageFile); } catch (IOException e) {
 * e.printStackTrace(); } } }
 * 
 * 
 * registerService.updateFullUserInfo(user);
 * 
 * return new Status(userId, 1012, "Profile updated successfully.");
 * 
 * 
 * } catch (Exception e) { return new Status(userId, 1030,
 * "Update profile failed."); } }
 * 
 * 
 * 
 * Update Member Profile Picture
 * 
 * 
 * @RequestMapping(value = "/changeProfilePic", method = RequestMethod.POST)
 * public @ResponseBody Status changeProfilePic(@RequestBody User user) { int
 * userId = user.getUserId();
 * user.setProfilePicName(ImageUtil.generateUniqueFileName()); try {
 * registerService.changeProfilePic(user); return new Status(userId, 1013,
 * "Profile picture changed successfully."); } catch (Exception e) { return new
 * Status(userId, 1031, "Profile picture change failed."); } }
 * 
 * 
 * 
 * Add City
 * 
 * 
 * @RequestMapping(value = "/addCity", method = RequestMethod.POST)
 * public @ResponseBody Status addCity(@RequestBody City city) {
 * 
 * try { registerService.addCity(city); return new Status(1032,
 * "City added successfuly.", utilityService.getRecentObject(City.class,
 * "cityId")); } catch (Exception e) { return new Status(1033,
 * "City failed to add."); } }
 * 
 * 
 * Get city list
 * 
 * 
 * @RequestMapping(value = "/getCityList", method = RequestMethod.GET)
 * public @ResponseBody Status getCityList() {
 * 
 * try { return new Status(registerService.getCityList(), 1036,
 * "City list fetched."); } catch (Exception e) { return new Status(1037,
 * "Failed to fetch city list."); } }
 * 
 * 
 * Get Area
 * 
 * 
 * @RequestMapping(value = "/addArea", method = RequestMethod.POST)
 * public @ResponseBody Status addArea(@RequestBody CityArea cityArea) {
 * 
 * try { registerService.addArea(cityArea); return new Status(1034,
 * "Area added successfuly.", utilityService.getRecentObject(CityArea.class,
 * "areaId")); } catch (Exception e) { return new Status(1035,
 * "Area failed to add."); } }
 * 
 * 
 * Get area list by city
 * 
 * 
 * @RequestMapping(value = "/getAreaList/{cityId}", method = RequestMethod.GET)
 * public @ResponseBody Status addArea(@PathVariable("cityId") int cityId) {
 * 
 * try { return new Status(registerService.getAreaListByCity(cityId), 1038,
 * "Area list fetched."); } catch (Exception e) { return new Status(1039,
 * "Failed to fetch area list."); } } }
 */