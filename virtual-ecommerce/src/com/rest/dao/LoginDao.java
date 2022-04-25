package com.rest.dao;
  
import org.springframework.stereotype.Component;
  
@Component
public interface LoginDao {
  
  public int getUserIdByEmailAndPassword(String username, String password)throws Exception;
  
  public String getStatusByEmailAndPassword(String email, String password)throws Exception;
  
  public String getPasswordByEmail(String email)throws Exception;
  
  public void updateRandomPassword(String userPassword, int registerId) throws  Exception;
  
  public void updateNewPassword(String userPassword, int registerId) throws  Exception;
  
  public boolean verifyUserByEmail(String userEmail) throws Exception;
  
  public int getUserIdByEmailOrMobile(String userEmailOrMobile, int flag) throws Exception;
  
  public void updateOTPById(String userOTP, int registerId)throws Exception;
  
  public String getPasswordByUserId(int userId)throws Exception;
  
  public String getPasswordByEmailOrMobile(String username) throws Exception;
  
  public String getEndTimeOfOTP(String createdDateTime, String timeLimit);
  
} 