/*
 * package com.rest.controller;
 * 
 * import org.apache.log4j.Logger; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.rest.utility.sendEmail; import com.rest.model.User; import
 * com.rest.model.Status; import com.rest.service.LoginService; import
 * com.rest.service.RegisterService; import com.rest.utility.CommonUtil; import
 * com.rest.utility.ConstantsUtil; import com.rest.utility.PasswordEncoder;
 * import com.rest.utility.RandomPasswordGenerator;
 * 
 * @RestController
 * 
 * @CrossOrigin(origins = "*", allowedHeaders = "*") public class
 * LoginController {
 * 
 * @Autowired LoginService loginService;
 * 
 * @Autowired RegisterService registerService;
 * 
 * @Autowired RandomPasswordGenerator randomPasswordGenerator;
 * 
 * @Autowired sendEmail sendEmail;
 * 
 * @Autowired PasswordEncoder passwordEncoder;
 * 
 * BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
 * 
 * static final Logger logger = Logger.getLogger(LoginController.class);
 * 
 * 
 * For login
 * 
 * @RequestMapping(value = "/login", method = RequestMethod.POST)
 * public @ResponseBody Status loginUser(@RequestBody User user) throws
 * Exception {
 * 
 * String newEmailOrMobile = user.getEmailOrMobile(); String password =
 * user.getUserPassword();
 * 
 * int changeType = CommonUtil.checkEmailOrMobile(newEmailOrMobile);
 * 
 * boolean flag = false; 
 * if(changeType == 1) {
 * if(registerService.checkMobile(newEmailOrMobile)) { flag = true; }
 * 
 * } else if(changeType == 2) { if(registerService.checkEmail(newEmailOrMobile))
 * { flag = true; }
 * 
 * } else if(changeType == 0) {
 * logger.error("Invalid email or mobile number. Try Again."); return new
 * Status(1004, "Invalid email or mobile number."); 
 * }
 * 
 * String storedPassword = loginService.getPasswordByEmailOrMobile(newEmailOrMobile);
 * logger.error("storedPassword : "+storedPassword);
 * 
 * if(storedPassword == null || storedPassword.equals("")){
 * logger.error("null storedPassword"); return new Status(1017,
 * "Wrong email/mobile or password."); 
 * }
 * 
 * int userId = loginService.getUserIdByEmailAndPassword(newEmailOrMobile,storedPassword);
 * if(userId == 0){ 
 * logger.error("userId == 0"); return new Status(1017, "Wrong email/mobile or password."); }
 * 
 * try {
 * 
 * if(flag == true){
 * 
 * if(storedPassword != null && bCryptPasswordEncoder.matches(password,
 * storedPassword)){
 * 
 * User users = registerService.getUserById(userId); 
 * int status = users.getUserStatus(); 
 * String userOtp = users.getUserOTP();
 * 
 * logger.error("userOtp "+userOtp);
 * logger.error("userOtp length "+userOtp.length());
 * 
 * if(userOtp.length() > 0) { logger.error("OTP Verification Pending."); return
 * new Status(userId, 1014, "OTP verification pending.");
 * 
 * } else if(userOtp.length()==0){ if(status == 1 ){
 * logger.info("Login Successful"); return new Status(1015, "Login successful.",
 * users);
 * 
 * }else{ logger.info("Inactive Member"); return new Status(userId, 1016,
 * "You are inactive member."); } }
 * 
 * } else { logger.error("Wrong email/mobile or password."); return new
 * Status(userId, 1017, "Wrong email/mobile or password."); } }
 * 
 * }catch(Exception ex){ logger.error("Exception @Login"+ex.toString()); }
 * return new Status(userId, 1017, "Wrong email/mobile or password."); }
 * 
 * 
 * Forget Password, Validate user and generate random password and send it via
 * Email or Mobile
 * 
 * @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
 * public @ResponseBody Status forgetPassword(@RequestBody User user) throws
 * Exception {
 * 
 * String newEmailOrMobile = user.getEmailOrMobile(); int userId =
 * user.getUserId(); int emailOrMobileFlag = 0;
 * 
 * try { emailOrMobileFlag = CommonUtil.checkEmailOrMobile(newEmailOrMobile);
 * if(emailOrMobileFlag == 0){
 * 
 * logger.error("Invalid email or mobile number. Try Again."); return new
 * Status(userId, 1004, "Invalid email or mobile number."); } } catch(Exception
 * ex) { logger.error("Exception while"); return new Status(userId, 1004,
 * "Invalid email or mobile number."); }
 * 
 * try { userId = loginService.getUserIdByEmailOrMobile(newEmailOrMobile,
 * emailOrMobileFlag); if(userId == 0) { logger.error(newEmailOrMobile+
 * " not registered. Please verify again."); return new Status(1018,
 * "Member not registered."); } } catch(Exception ex) {
 * 
 * logger.error("Exception while verifying User registered or not : "+ex.
 * toString()); return new Status(1018, "Member not registered."); }
 * 
 * try { String randomPassword =
 * randomPasswordGenerator.generateRandomPassword(ConstantsUtil.MIN_LEN,
 * ConstantsUtil.MAX_LEN, ConstantsUtil.NO_OF_CAPS_ALPHA,
 * ConstantsUtil.NO_OF_DIGITS, ConstantsUtil.NO_OF_SPL_CHARS); String
 * userPassword = passwordEncoder.getHashPassword(randomPassword); User register
 * = registerService.getUserById(userId); String to = register.getUserEmail();
 * String subject = "Password Reset Successfuly"; String messageBody =
 * "Your new Password is: "+randomPassword;
 * 
 * loginService.updateRandomPassword(userPassword, userId);
 * sendEmail.sendMail(ConstantsUtil.FROM, to, subject, messageBody);
 * 
 * logger.info("Random Password Generated"); return new Status(userId, 1019,
 * "Random password generated.");
 * 
 * } catch(Exception ex) {
 * 
 * logger.error("Exception @Generating Random Password"+ex.toString()); } return
 * new Status(userId, 1032, "Password reset failed."); }
 * 
 * 
 * Change Password
 * 
 * @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
 * public @ResponseBody Status changePassword(@RequestBody User user) throws
 * Exception {
 * 
 * int userId = user.getUserId(); String oldPassword = user.getUserPassword();
 * String newPassword = user.getNewUserPassword();
 * 
 * try { String storedPassword = registerService.getUserPasswordById(userId);
 * if(storedPassword != null && bCryptPasswordEncoder.matches(oldPassword,
 * storedPassword)){
 * 
 * String userPassword = passwordEncoder.getHashPassword(newPassword);
 * logger.info("userPassword : "+userPassword);
 * loginService.updateNewPassword(userPassword, userId );
 * 
 * logger.info("Password has been changed successfully."); return new
 * Status(userId, 1020, "Your password has been changed successfully."); } else
 * { logger.info("Existing Passowrd is incorrect."); return new Status(userId,
 * 1021, "Old Passowrd is incorrect."); }
 * 
 * } catch(Exception ex) {
 * logger.error("Exceptin while change password : "+ex.toString()); return new
 * Status(userId, 1033, "Change password failed."); } }
 * 
 * @RequestMapping(value = "/changeEmailOrMobile", method = RequestMethod.POST)
 * public @ResponseBody Status verifyPassword(@RequestBody User user) throws
 * Exception {
 * 
 * int userId = user.getUserId(); String password = user.getUserPassword();
 * String email = user.getUserEmail(); String mobile = user.getUserMobile();
 * 
 * try { String storedPassword = loginService.getPasswordByUserId(userId);
 * if(storedPassword != null && bCryptPasswordEncoder.matches(password,
 * storedPassword)){
 * 
 * registerService.updateMobileAndEmail(email, mobile, userId);
 * logger.info("Email Id/Mobile Number changed successfuly."); return new
 * Status(userId, 1034, "Changes updated successfuly."); } else {
 * logger.info("Incorrect Password."); return new Status(userId, 1021,
 * "Incorrect Password."); }
 * 
 * } catch(Exception ex) {
 * logger.error("Exceptin while change password : "+ex.toString()); return new
 * Status(userId, 1035, "Password verification failed."); } }
 * 
 * @RequestMapping(value = "/logout/{userId}", method = RequestMethod.GET)
 * public @ResponseBody Status logout(@PathVariable("userId") int userId) {
 * 
 * try { return new Status(userId, 1038, "Logout successfully."); } catch
 * (Exception e) { logger.error("Exceptin while logout : "+e.toString()); return
 * new Status(1039, "Logout failed."); } } }
 */