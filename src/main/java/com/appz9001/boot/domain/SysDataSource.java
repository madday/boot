package com.appz9001.boot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="sys_datasource")
public class SysDataSource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ds_id")
	private String dsId;
	
	@Column(name = "ds_url")
	private String dsUrl;

	@Column(name = "ds_user")
	private String ds_user;

	@Column(name = "ds_password")
	private String ds_password;

	@Column(name = "ds_driver")
	private String ds_driver;


	public String getDsUrl() {
		return dsUrl;
	}

	public void setDsUrl(String dsUrl) {
		this.dsUrl = dsUrl;
	}

	public String getDs_user() {
		return ds_user;
	}

	public void setDs_user(String ds_user) {
		this.ds_user = ds_user;
	}

	public String getDs_password() {
		return ds_password;
	}

	public void setDs_password(String ds_password) {
		this.ds_password = ds_password;
	}

	public String getDs_driver() {
		return ds_driver;
	}

	public void setDs_driver(String ds_driver) {
		this.ds_driver = ds_driver;
	}
}
