package org.example.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Customer {
	//客户id
	private Integer cid;
	//客户名称
	private String custName;
	//客户级别
	private String custLevel;
	//客户来源
	private String custSource;
	//联系电话
	private String custPhone;
	//手机
	private String custMobile;
	
	//在客户实体类里面表示多个联系人，一个客户有多个联系人
	//hibernate要求使用集合表示多的数据，使用set集合
	private Set<LinkMan> setLinkMan = new HashSet<LinkMan>();
}
