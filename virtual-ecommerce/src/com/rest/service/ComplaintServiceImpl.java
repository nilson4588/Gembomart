package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.ComplaintDao;
import com.rest.model.Complaint;
import com.rest.utility.CommonUtil;
import com.rest.utility.DateTimeUtil;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	ComplaintDao complaintDao;
	
	@Override
	public int saveOrUpdateComplaint(Complaint complaint) throws Exception {
		// TODO Auto-generated method stub
		complaint.setComplaintDatetime(DateTimeUtil.getSysDateTime()); 
	    complaint.setComplaintSolvedDatetime("");
		return complaintDao.saveOrUpdateComplaint(complaint) ;
	}

	@Override
	public Complaint getComplaintById(int complaintId) throws Exception {
		// TODO Auto-generated method stub
		return complaintDao.getComplaintById(complaintId);
	}

	@Override
	public List<Complaint> getComplaintList(Complaint complaint) throws Exception {
		// TODO Auto-generated method stub
		
		List<Complaint> complaintList = complaintDao.getComplaintList(complaint);
		List<Complaint> complaintList1 = new ArrayList<Complaint>();
		for (Complaint complaint2 : complaintList) {
			int complaintType    = complaint2.getComplaintType();
			String complaintDesc = CommonUtil.getComplaintType().get(complaintType).toString();
			complaint2.setComplaintTypeDesc(complaintDesc);
			
			complaintList1.add(complaint2);
		}
		
		return complaintList1;
	}

	@Override
	public void changeComplaintStatus(Complaint complaint) throws Exception {
		// TODO Auto-generated method stub
		complaint.setComplaintSolvedDatetime(DateTimeUtil.getSysDateTime());
        complaintDao.changeComplaintStatus(complaint);
	}
	
	@Override
	public List<Complaint> complaintList() throws Exception {
		
		List<Complaint> complaintList  = complaintDao.complaintList();
		List<Complaint> complaintList1 = new ArrayList<Complaint>();
				
		for (Complaint complaint : complaintList) {
			
			int complaintType    = complaint.getComplaintType();
			String complaintDesc = CommonUtil.getComplaintType().get(complaintType).toString();
			complaint.setComplaintTypeDesc(complaintDesc);
			complaintList1.add(complaint);
		}
		
		return complaintList1;
	}

}
