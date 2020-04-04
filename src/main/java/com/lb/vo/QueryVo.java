package com.lb.vo;

import lombok.Data;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName PatientQueryVo.java
 * @Description 患者查询条件包装类
 * @createTime 2020年03月29日 21:00:00
 */
@Data
public class QueryVo {
    private long pageNo = 1;
    private long pageSize = 5;
    private String patientName;
    private String doctorName;
    private Integer userId;
    private String certId;
    private Integer doctorId;
}
