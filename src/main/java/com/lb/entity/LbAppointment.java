package com.lb.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2020-03-25
*/
@Data
@Table(name="lb_appointment")
public class LbAppointment   {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_doctor_id = "doctor_id";
	public static final String ALIAS_patient_id = "patient_id";
	public static final String ALIAS_expenses = "expenses";
	public static final String ALIAS_time = "time";
	
	/*
	主键
	*/
	private Integer id ;
	/*
	医生id
	*/
	private Integer doctorId ;
	/*
	患者id
	*/
	private Integer patientId ;
	/*
	门诊费
	*/
	private BigDecimal expenses ;
	/*
	预约时间
	*/
	private Date time ;
}
