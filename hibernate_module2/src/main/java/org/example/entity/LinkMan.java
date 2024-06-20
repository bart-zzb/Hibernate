package org.example.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkMan {
	private Integer lkm_id; // 联系人编号(主键)
	private String lkm_name;// 联系人姓名
	private String lkm_gender;// 联系人性别
	private String lkm_phone;// 联系人办公电话
	
	// 在联系人实体类里面表示所属客户,一个联系人只能属于一个客户
	private Customer customer;
}
