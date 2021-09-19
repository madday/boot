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

	@Column(name = "ds_ip")
	private String dsIp;

	@Column(name = "ds_port")
	private String dsPort;

	@Column(name = "ds_user")
	private String dsUser;

	@Column(name = "ds_password")
	private String dsPassword;

	@Column(name = "ds_driver")
	private String dsDriver;

	@Column(name = "ds_note")
	private String dsNote;

	public String getDsUser() {
		return dsUser;
	}

	public void setDsUser(String dsUser) {
		this.dsUser = dsUser;
	}

	public String getDsPassword() {
		return dsPassword;
	}

	public void setDsPassword(String dsPassword) {
		this.dsPassword = dsPassword;
	}

	public String getDsDriver() {
		return dsDriver;
	}

	public void setDsDriver(String dsDriver) {
		this.dsDriver = dsDriver;
	}

	public String getDsId() {
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}

	public String getDsIp() {
		return dsIp;
	}

	public void setDsIp(String dsIp) {
		this.dsIp = dsIp;
	}

	public String getDsPort() {
		return dsPort;
	}

	public void setDsPort(String dsPort) {
		this.dsPort = dsPort;
	}

	public String getDsUrl() {
		return dsUrl;
	}

	public void setDsUrl(String dsUrl) {
		this.dsUrl = dsUrl;
	}

	public String getDsNote() {
		return dsNote;
	}

	public void setDsNote(String dsNote) {
		this.dsNote = dsNote;
	}
}
