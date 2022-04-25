/*
 * package com.rest.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.rest.dao.LoginDao; import com.rest.utility.DateTimeUtil;
 * 
 * @Service public class LoginServiceImpl implements LoginService {
 * 
 * @Autowired LoginDao loginDao;
 * 
 * @Override public int getUserIdByEmailAndPassword(String username, String
 * password) throws Exception {
 * 
 * return loginDao.getUserIdByEmailAndPassword(username,password); }
 * 
 * @Override public String getStatusByEmailAndPassword(String email, String
 * password) throws Exception {
 * 
 * return loginDao.getStatusByEmailAndPassword(email,password); }
 * 
 * @Override public String getPasswordByEmail(String email) throws Exception {
 * 
 * return loginDao.getPasswordByEmail(email); }
 * 
 * @Override public void updateRandomPassword(String userPassword, int
 * userId)throws Exception {
 * 
 * loginDao.updateRandomPassword(userPassword, userId); }
 * 
 * @Override public void updateNewPassword(String userPassword, int userId
 * )throws Exception {
 * 
 * loginDao.updateNewPassword(userPassword, userId ); }
 * 
 * @Override public boolean verifyUserByEmail(String userEmail)throws Exception
 * {
 * 
 * return loginDao.verifyUserByEmail(userEmail); }
 * 
 * 
 * @Override public int getUserIdByEmailOrMobile(String userEmailOrMobile, int
 * flag)throws Exception {
 * 
 * return loginDao.getUserIdByEmailOrMobile(userEmailOrMobile, flag); }
 * 
 * @Override public void updateOTPById(String userOTP, int userId)throws
 * Exception {
 * 
 * loginDao.updateOTPById(userOTP, userId); }
 * 
 * @Override public String getPasswordByUserId(int userId) throws Exception{
 * 
 * return loginDao.getPasswordByUserId(userId);
 * 
 * }
 * 
 * @Override public String getPasswordByEmailOrMobile(String username) throws
 * Exception{
 * 
 * return loginDao.getPasswordByEmailOrMobile(username); }
 * 
 * @Override public String getEndTimeOfOTP(String createdDateTime, String
 * timeLimit) {
 * 
 * return loginDao.getEndTimeOfOTP(createdDateTime, timeLimit); }
 * 
 * }
 */