/*
 * package com.rest.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.rest.dao.RegisterDao; import com.rest.model.City; import
 * com.rest.model.CityArea; import com.rest.model.User; import
 * com.rest.utility.ConstantsUtil; import com.rest.utility.ImageUtil;
 * 
 * @Service public class RegisterServiceImpl implements RegisterService {
 * 
 * @Autowired RegisterDao registerDao;
 * 
 * @Override public boolean addUser(User user) throws Exception { return
 * registerDao.addUser(user); }
 * 
 * @Override public String getUserPasswordById(int userId) { return
 * registerDao.getUserPasswordById(userId); }
 * 
 * @Override public List<User> getUserList(int societyId, int userId, String
 * dateTime)throws Exception { return registerDao.getUserList(societyId,userId,
 * dateTime); }
 * 
 * 
 * @Override public User getUserById(int userId) throws Exception { return
 * registerDao.getUserById(userId); }
 * 
 * @Override public void updateOtpAndStatusByUserId(int userId, String userOTP,
 * int userStatus) throws Exception {
 * registerDao.updateOtpAndStatusByUserId(userId, userOTP, userStatus); }
 * 
 * @Override public boolean checkEmail(String userEmail) { return
 * registerDao.checkEmail(userEmail); }
 * 
 * @Override public boolean checkMobile(String userMobile) { return
 * registerDao.checkMobile(userMobile); }
 * 
 * @Override public void updateEmailId(String userOtp, String userEmail, int
 * userId){ registerDao.updateEmailId(userOtp, userEmail, userId); }
 * 
 * @Override public void updateMobileNumber(String userOtp, String mobileNumber,
 * int userId){ registerDao.updateMobileNumber(userOtp, mobileNumber, userId); }
 * 
 * @Override public void updateMobileAndEmail(String userEmail, String
 * mobileNumber, int userId){ registerDao.updateMobileAndEmail(userEmail,
 * mobileNumber, userId); }
 * 
 * @Override public boolean updateFullUserInfo(User user) throws Exception {
 * return registerDao.updateFullUserInfo(user); }
 * 
 * @Override public boolean changeProfilePic(User user) throws Exception {
 * 
 * int userId = user.getUserId(); String profilePic =
 * registerDao.getProfilePicById(userId);
 * 
 * String filePath = ConstantsUtil.IMAGE_LOCATION + profilePic + "." +
 * ConstantsUtil.IMAGE_FORMAT; ImageUtil.deleteFile(filePath); String
 * thumbnailPath = ConstantsUtil.IMAGE_LOCATION + profilePic + "icon." +
 * ConstantsUtil.IMAGE_FORMAT; ImageUtil.deleteFile(thumbnailPath);
 * 
 * return registerDao.changeProfilePic(user); }
 * 
 * public String getProfilePicById(int userId){ return
 * registerDao.getProfilePicById(userId); }
 * 
 * public String getuserOTPById(int userId) throws Exception { return
 * registerDao.getuserOTPById(userId); }
 * 
 * public boolean validateMobileNEmail(String userMobile, String userEmail, int
 * userId){ return registerDao.validateMobileNEmail(userMobile, userEmail,
 * userId); }
 * 
 * @Override public List<User> getAllUsersByStatus(int status) throws Exception
 * { return registerDao.getAllUsersByStatus(status); }
 * 
 * public String getMemberNameById(int userId) { return
 * registerDao.getMemberNameById(userId); }
 * 
 * @Override public List<City> getCityList() throws Exception { return
 * registerDao.getCityList(); }
 * 
 * @Override public List<CityArea> getAreaListByCity(int cityId) throws
 * Exception { return registerDao.getAreaListByCity(cityId); }
 * 
 * @Override public boolean addCity(City city) throws Exception { return
 * registerDao.addCity(city); }
 * 
 * @Override public boolean addArea(CityArea cityArea) throws Exception { return
 * registerDao.addArea(cityArea); } }
 */