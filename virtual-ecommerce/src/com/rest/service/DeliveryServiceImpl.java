package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.DeliveryDao;
import com.rest.model.Delivery;
import com.rest.model.DeliveryOrder;
import com.rest.utility.DateTimeUtil;

@Service 
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	DeliveryDao deliveryDao;
	
	@Autowired
	FranchiseLocalService franchiseLocalService;
	
	@Override
	public int saveOrUpdateDelivery(Delivery delivery) throws Exception {
		// TODO Auto-generated method stub
		delivery.setDeliveryRegistrationDatetime(DateTimeUtil.getSysDateTime());
		return deliveryDao.saveOrUpdateDelivery(delivery);
	}

	@Override
	public Delivery getDeliveryById(int deliveryId) throws Exception {
		// TODO Auto-generated method stub
		return deliveryDao.getDeliveryById(deliveryId);
	}

	@Override
	public List<Delivery> getDeliveryList() throws Exception {
		// TODO Auto-generated method stub
		List<Delivery> deliveryList = deliveryDao.getDeliveryList();
		List<Delivery> deliveryList1 = new ArrayList<Delivery>();
		for (Delivery delivery : deliveryList) {
			int localfranchiseId = delivery.getLocalFranchiseId();
			String localfranchiseName = franchiseLocalService.getFranchiseLocalById(localfranchiseId).getFullName();
			delivery.setLocalFranchiseName(localfranchiseName);
			deliveryList1.add(delivery);
		}
		return deliveryList1;
	}

	@Override
	public Delivery getDeliveryByMobileNo(long mobileNo) throws Exception {
		// TODO Auto-generated method stub
		return deliveryDao.getDeliveryByMobileNo(mobileNo);
	}

	@Override
	public int activateOrDeactivateDelivery(Delivery delivery) throws Exception {
		return deliveryDao.activateOrDeactivateDelivery(delivery);
	}
	
	@Override
	public List<Delivery> deliveryListByFranchiseLocal(int localFranchiseId) throws Exception {
		return deliveryDao.deliveryListByFranchiseLocal(localFranchiseId);
	}
	
	@Override
	public List<Delivery> deliveryListByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		return deliveryDao.deliveryListByFranchiseTaluka(talukaFranchiseId);
	}

	@Override
	public List<Delivery> deliveryListByFranchiseDistrict(int districtFranchiseId) throws Exception {
		return deliveryDao.deliveryListByFranchiseDistrict(districtFranchiseId);
	}
	
	@Override
	public List<DeliveryOrder> getDeliveryOrders(int localFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return deliveryDao.getDeliveryOrders(createdDateTime, localFranchiseId);
	}
	
	@Override
	public Delivery getDeliveryByMobileAndPassword(long mobileNumber, String password) throws Exception {
		return deliveryDao.getDeliveryByMobileAndPassword(mobileNumber, password);
	}
	
	@Override
	public int changePasswordByDeliveryMobile(Delivery delivery) throws Exception {
		return deliveryDao.changePasswordByDeliveryMobile(delivery);
	}
	
	@Override
	public List<String> deliveryTokenByOrderSellerId(int sellerId) throws Exception {
		return deliveryDao.deliveryTokenByOrderSellerId(sellerId);
	}
	
	@Override
	public String getTokenByDeliveryId(int deliveryId) throws Exception {
		return deliveryDao.getTokenByDeliveryId(deliveryId);
	}
	
	@Override
	public int updateDeliveryToken(Delivery delivery) throws Exception {
		return deliveryDao.updateDeliveryToken(delivery);
	}
}