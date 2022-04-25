/*
 * package com.rest.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.stereotype.Component;
 * 
 * import com.rest.model.City; import com.rest.model.CityArea; import
 * com.rest.model.User;
 * 
 * @Component public interface RegisterService {
 * 
 * public boolean addUser(User user) throws Exception ;
 * 
 * public String getUserPasswordById(int userId);
 * 
 * public List<User> getUserList(int societyId, int userId, String dateTime)
 * throws Exception;
 * 
 * public User getUserById(int userId) throws Exception;
 * 
 * public void updateOtpAndStatusByUserId(int userId, String userOTP, int
 * userStatus) throws Exception;
 * 
 * public boolean checkEmail(String userEmail);
 * 
 * public boolean checkMobile(String userMobile);
 * 
 * public void updateEmailId(String userOtp, String userEmail, int userId);
 * 
 * public void updateMobileNumber(String userOtp, String mobileNumber, int
 * userId);
 * 
 * public void updateMobileAndEmail(String userEmail, String mobileNumber, int
 * userId);
 * 
 * public boolean updateFullUserInfo(User user) throws Exception;
 * 
 * public boolean changeProfilePic(User user) throws Exception;
 * 
 * public String getProfilePicById(int userId);
 * 
 * public String getuserOTPById(int userId) throws Exception;
 * 
 * public boolean validateMobileNEmail(String userMobile, String userEmail, int
 * userId);
 * 
 * public List<User> getAllUsersByStatus(int status) throws Exception;
 * 
 * public String getMemberNameById(int userId);
 * 
 * public List<City> getCityList() throws Exception;
 * 
 * public List<CityArea> getAreaListByCity(int cityId) throws Exception;
 * 
 * public boolean addCity(City city) throws Exception;
 * 
 * public boolean addArea(CityArea cityArea) throws Exception; }
 */