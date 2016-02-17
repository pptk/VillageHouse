package com.dao;

import java.io.Serializable;

import com.model.RepairModel;

public interface RepairDao {
    
	Serializable add_Repair(RepairModel repair);
	
	String repair_deal(String rusername,String rname,String rcontent,String raddress,String rtime); //—È÷§
}
