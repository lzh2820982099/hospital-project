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
@Table(name="lb_hospitalization")
public class LbHospitalization   {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_patient_id = "patient_id";
	public static final String ALIAS_bed = "bed";
	public static final String ALIAS_door = "door";
	public static final String ALIAS_floor = "floor";
	public static final String ALIAS_medical_name = "medical_name";
	public static final String ALIAS_intime = "intime";
	public static final String ALIAS_outtime = "outtime";
	
	/*
	主键
	*/
	private Integer id ;
	/*
	患者id
	*/
	private Integer patientId ;
	/*
	床号
	*/
	private String bed ;
	/*
	房间号
	*/
	private String door ;
	/*
	楼层
	*/
	private String floor ;
	/*
	病名称
	*/
	private String medicalName ;
	/*
	住院时间
	*/
	private Date intime ;
	/*
	出院时间
	*/
	private Date outtime ;
	
	public LbHospitalization() {
	}
}
