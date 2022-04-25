package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.EnquiryDao;
import com.rest.model.Enquiry;
import com.rest.utility.DateTimeUtil;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	EnquiryDao enquiryDao;
	
	@Override
	public int saveOrUpdateEnquiry(Enquiry enquiry) throws Exception {
		// TODO Auto-generated method stub
		enquiry.setIsActive(1);
		enquiry.setRecordDatetime(DateTimeUtil.getSysDateTime());
		return enquiryDao.saveOrUpdateEnquiry(enquiry);
	}

	@Override
	public Enquiry getEnquiryById(int enquiryId) throws Exception {
		// TODO Auto-generated method stub
		return enquiryDao.getEnquiryById(enquiryId);
	}

	@Override
	public List<Enquiry> getEnquiryList() throws Exception {
		// TODO Auto-generated method stub
		return enquiryDao.getEnquiryList();
	}

	@Override
	public int activateOrDeactivateEnquiry(Enquiry enquiry) throws Exception {
		// TODO Auto-generated method stub
		return enquiryDao.activateOrDeactivateEnquiry(enquiry);
	}	
}