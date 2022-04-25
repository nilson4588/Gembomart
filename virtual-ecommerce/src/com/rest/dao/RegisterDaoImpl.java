/*
 * package com.rest.dao;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.hibernate.Criteria; import org.hibernate.Query; import
 * org.hibernate.Session; import org.hibernate.SessionFactory; import
 * org.hibernate.Transaction; import org.hibernate.criterion.Projections; import
 * org.hibernate.criterion.Restrictions; import
 * org.hibernate.transform.Transformers; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Component;
 * 
 * import com.rest.model.City; import com.rest.model.CityArea; import
 * com.rest.model.User; import com.rest.utility.ConstantsUtil; import
 * com.rest.utility.ImageUtil;
 * 
 * @Component public class RegisterDaoImpl implements RegisterDao {
 * 
 * @Autowired SessionFactory sessionFactory;
 * 
 * Session session = null; Transaction tx = null;
 * 
 * //To get Users list by societyId (list visible only to 'userId' belong to
 * 'societyId')
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Override public List<User> getUserList(int societyId, int userId, String
 * dateTime) throws Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String resultQuery =
 * " select u.userId as userId, u.userName as userName, u.userMobile as userMobile, u.userEmail as userEmail, u.userStatus as userStatus, u.userGender as userGender, u.dateOfBirth as dateOfBirth, u.occupation as occupation from User as u "
 * ;
 * 
 * List<User> userList1 = session.createQuery(resultQuery)
 * .setParameter("socid", societyId)
 * .setResultTransformer(Transformers.aliasToBean(User.class)) .list();
 * 
 * tx.commit(); session.close();
 * 
 * List<User> userList2 = new ArrayList<User>();
 * 
 * for( User u : userList1) { String profilePic = u.getProfilePicName();
 * if(profilePic.length()>0){
 * u.setProfilePicName(ConstantsUtil.SERVER_IMG_LOCATION+profilePic+"."+
 * ConstantsUtil.IMAGE_FORMAT);
 * u.setProfilePicThumbnail(ConstantsUtil.SERVER_IMG_LOCATION+profilePic+"icon."
 * +ConstantsUtil.IMAGE_FORMAT); }else{
 * u.setProfilePicThumbnail(ConstantsUtil.SERVER_IMG_LOCATION+"user.png"); }
 * //u.setVehicleDetails(getAllVehicleByUserId(memberId)); userList2.add(u); }
 * 
 * return userList2; }
 * 
 * 
 * @Override public User getUserById(int userId) throws Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String resultQuery =
 * " select u.userId as userId, u.userName as userName, u.userMobile as userMobile, u.userEmail as userEmail, u.userStatus as userStatus, u.userOTP as userOTP, "
 * +
 * " u.profileBio as profileBio, u.profilePicName as profilePicName, u.userGender as userGender, u.dateOfBirth as dateOfBirth, u.occupation as occupation "
 * + " from User as u where u.userId = :userId "; //and u.updatedDateTime =
 * :updatedDateTime
 * 
 * Query query = session.createQuery(resultQuery); query.setParameter("userId",
 * userId);
 * 
 * User user = (User)
 * query.setResultTransformer(Transformers.aliasToBean(User.class)).uniqueResult
 * ();
 * 
 * String profilePic = user.getProfilePicName(); if(profilePic.length()>0){
 * user.setProfilePicName(ConstantsUtil.SERVER_IMG_LOCATION+profilePic+"."+
 * ConstantsUtil.IMAGE_FORMAT);
 * user.setProfilePicThumbnail(ConstantsUtil.SERVER_IMG_LOCATION+profilePic+
 * "icon."+ConstantsUtil.IMAGE_FORMAT); }else{
 * user.setProfilePicThumbnail(ConstantsUtil.SERVER_IMG_LOCATION+"user.png"); }
 * 
 * String fetchedPenaltyDate = user.getPenaltyDate();
 * user.setPenaltyDate(fetchedPenaltyDate+"-"+DateTimeUtil.getCurrentMonthYear()
 * );
 * 
 * tx.commit(); session.close();
 * 
 * //user.setVehicleDetails(getAllVehicleByUserId(userId));
 * 
 * return user; }
 * 
 * // To add user (Registration)
 * 
 * @Override public boolean addUser(User user) throws Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * session.save(user); tx.commit(); session.close();
 * 
 * return true; }
 * 
 * //To update Otp, updatedDateTime by userId
 * 
 * @Override public void updateOtpAndStatusByUserId(int userId, String userOTP,
 * int userStatus) throws Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String hqlUpdate =
 * "update User set userOTP = :userOTP, userStatus = :userStatus where userId = :userId"
 * ; session.createQuery(hqlUpdate) .setString("userOTP", userOTP)
 * .setInteger("userStatus", userStatus) .setInteger("userId", userId)
 * .executeUpdate();
 * 
 * tx.commit(); session.close(); }
 * 
 * @Override public String getMemberNameById(int userId){
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * String uname = null;
 * 
 * Criteria criteria = session.createCriteria(User.class)
 * .setProjection(Projections.property("userName"))
 * .add(Restrictions.eq("userId", userId));
 * 
 * uname = (String) criteria.uniqueResult(); tx.commit(); session.close();
 * 
 * return uname; }
 * 
 * @Override public String getUserPasswordById(int userId){
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * String pwd = null;
 * 
 * Criteria criteria = session.createCriteria(User.class)
 * .setProjection(Projections.property("userPassword"))
 * .add(Restrictions.eq("userId", userId));
 * 
 * pwd = (String) criteria.uniqueResult(); tx.commit(); session.close();
 * 
 * return pwd; }
 * 
 * @Override public String getProfilePicById(int userId){
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * String dp = null;
 * 
 * Criteria criteria = session.createCriteria(User.class)
 * .setProjection(Projections.property("profilePicName"))
 * .add(Restrictions.eq("userId", userId));
 * 
 * dp = (String) criteria.uniqueResult(); tx.commit(); session.close();
 * 
 * return dp; }
 * 
 * //To get userOTP by userId
 * 
 * @Override public String getuserOTPById(int userId) throws Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * String otp = null;
 * 
 * Criteria criteria = session.createCriteria(User.class)
 * .setProjection(Projections.property("userOTP"))
 * .add(Restrictions.eq("userId", userId));
 * 
 * otp = (String) criteria.uniqueResult(); tx.commit(); session.close();
 * 
 * return otp; }
 * 
 * 
 * //To get member list by status
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Override public List<User> getAllUsersByStatus(int userStatus) throws
 * Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String resultQuery =
 * " select u.userId as userId, u.userName as userName, u.userMobile as userMobile, u.userEmail as userEmail, u.userStatus as userStatus from User as u where u.userStatus = :userStatus "
 * ;
 * 
 * Query query = session.createQuery(resultQuery);
 * query.setParameter("userStatus", userStatus);
 * 
 * List<User> userList1 =
 * query.setResultTransformer(Transformers.aliasToBean(User.class)).list();
 * 
 * tx.commit(); session.close();
 * 
 * return userList1; }
 * 
 * //To check email exists or not
 * 
 * @Override public boolean checkEmail(String userEmail) {
 * 
 * boolean result=false; try { session = sessionFactory.openSession(); tx =
 * session.beginTransaction();
 * 
 * Criteria criteria = session.createCriteria(User.class);
 * criteria.add(Restrictions.eq("userEmail", userEmail));
 * //user=(User)criteria.uniqueResult();
 * 
 * tx.commit(); if(criteria.list().size() > 0) result = true;
 * 
 * }catch(Exception e){ e.printStackTrace(); }finally{ session.close(); } return
 * result; }
 * 
 * //To check mobile exists or not
 * 
 * @Override public boolean checkMobile(String userMobile) {
 * 
 * boolean result = false; try { session = sessionFactory.openSession(); tx =
 * session.beginTransaction();
 * 
 * Criteria criteria = session.createCriteria(User.class);
 * criteria.add(Restrictions.eq("userMobile", userMobile)); //user =
 * (User)criteria.uniqueResult(); tx.commit();
 * 
 * if(criteria.list().size() > 0) result = true;
 * 
 * }catch(Exception e){ e.printStackTrace(); }finally { session.close(); }
 * return result; }
 * 
 * 
 * //To update new email Id
 * 
 * @Override public void updateEmailId(String userOtp, String userEmail, int
 * userId) {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String hqlUpdate =
 * "update User set userOTP = :userOTP, userEmail = :userEmail where userId = :userId"
 * ; session.createQuery(hqlUpdate) .setString("userOTP", userOtp)
 * .setString("userEmail", userEmail) .setInteger("userId", userId)
 * .executeUpdate();
 * 
 * tx.commit(); session.close(); }
 * 
 * //To update new Mobile Number
 * 
 * @Override public void updateMobileNumber(String userOtp, String mobileNumber,
 * int userId) {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String hqlUpdate =
 * "update User set userOTP = :userOTP, userMobile = :userMobile where userId = :userId"
 * ; session.createQuery(hqlUpdate) .setString("userOTP", userOtp)
 * .setString("userMobile", mobileNumber) .setInteger("userId", userId)
 * .executeUpdate();
 * 
 * tx.commit(); session.close(); }
 * 
 * @Override public void updateMobileAndEmail(String userEmail, String
 * mobileNumber, int userId){
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String hqlUpdate =
 * "update User set userEmail = :userEmail, userMobile = :userMobile where userId = :userId"
 * ; session.createQuery(hqlUpdate) .setString("userEmail", userEmail)
 * .setString("userMobile", mobileNumber) .setInteger("userId", userId)
 * .executeUpdate();
 * 
 * tx.commit(); session.close(); }
 * 
 * @Override public boolean updateFullUserInfo(User user) throws Exception {
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String hqlUpdate =
 * "update User set userName = :userName, userMobileAlternate = :userMobileAlternate, cityId = :cityId, areaId = :areaId, userAddress = :userAddress,  userGender = :userGender, dateOfBirth = :dateOfBirth, maritalStatus = :maritalStatus, marriageDate = :marriageDate, profileBio = :profileBio, occupation = :occupation, organizationName = :organizationName, userDesignation = :userDesignation, userRemark = :userRemark, instituteName = :instituteName, branchName = :branchName   where userId = :userId"
 * ; session.createQuery(hqlUpdate) .setString("userName", user.getUserName())
 * .setString("userMobileAlternate", user.getUserMobileAlternate())
 * .setInteger("cityId", user.getCityId()) .setInteger("areaId",
 * user.getAreaId()) .setString("userAddress", user.getUserAddress())
 * .setInteger("userGender", user.getUserGender()) .setString("dateOfBirth",
 * user.getDateOfBirth()) .setInteger("maritalStatus", user.getMaritalStatus())
 * .setString("marriageDate", user.getMarriageDate()) .setString("profileBio",
 * user.getProfileBio()) .setString("occupation", user.getOccupation())
 * .setString("organizationName", user.getOrganizationName())
 * .setString("userDesignation", user.getUserDesignation())
 * .setString("userRemark", user.getUserRemark()) .setString("instituteName",
 * user.getInstituteName()) .setString("branchName", user.getBranchName())
 * .setInteger("userId", user.getUserId()) .executeUpdate();
 * 
 * tx.commit(); session.close();
 * 
 * return true; }
 * 
 * @Override public boolean changeProfilePic(User user) throws Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * 
 * String hqlUpdate =
 * "update User set profilePicName = :profilePicName where userId = :userId";
 * session.createQuery(hqlUpdate) .setString("profilePicName",
 * user.getProfilePicName()) .setInteger("userId", user.getUserId())
 * .executeUpdate();
 * 
 * ImageUtil.decodeFileToBase64Binary(user.getImageInByte(),
 * user.getProfilePicName(), true); //Decode image file and save to desired
 * location
 * 
 * tx.commit(); session.close();
 * 
 * return true; }
 * 
 * @Override public boolean validateMobileNEmail(String userMobile, String
 * userEmail, int userId) {
 * 
 * boolean result = true; try { session = sessionFactory.openSession(); tx =
 * session.beginTransaction();
 * 
 * String resultQuery =
 * " select userMobile from User where (userMobile = :userMobile or userEmail = :userEmail) and userId <> :userId "
 * ;
 * 
 * Query query = session.createQuery(resultQuery);
 * query.setParameter("userMobile", userMobile); query.setParameter("userEmail",
 * userEmail); query.setParameter("userId", userId);
 * 
 * List<User> ulist = query.list(); int listSize = ulist.size();
 * 
 * tx.commit();
 * 
 * if(listSize > 0) result = false;
 * 
 * }catch(Exception e){ e.printStackTrace(); }finally { session.close(); }
 * return result; }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Override public List<City> getCityList() throws Exception {
 * 
 * List<City> cityList = null; try { session = sessionFactory.openSession(); tx
 * = session.beginTransaction();
 * 
 * cityList = session.createCriteria(City.class).list();
 * 
 * tx.commit();
 * 
 * }catch(Exception e){ e.printStackTrace(); }finally { session.close(); }
 * 
 * return cityList; }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Override public List<CityArea> getAreaListByCity(int cityId) throws
 * Exception {
 * 
 * List<CityArea> areaList = null; try { session = sessionFactory.openSession();
 * tx = session.beginTransaction();
 * 
 * Criteria criteria = session.createCriteria(CityArea.class);
 * criteria.add(Restrictions.eq("cityId", cityId));
 * 
 * tx.commit();
 * 
 * areaList = (ArrayList<CityArea>)criteria.list();
 * 
 * }catch(Exception e){ e.printStackTrace(); }finally { session.close(); }
 * 
 * return areaList; }
 * 
 * @Override public boolean addCity(City city) throws Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * session.save(city); tx.commit(); session.close();
 * 
 * return true; }
 * 
 * @Override public boolean addArea(CityArea cityArea) throws Exception {
 * 
 * session = sessionFactory.openSession(); tx = session.beginTransaction();
 * session.save(cityArea); tx.commit(); session.close();
 * 
 * return true; } }
 */