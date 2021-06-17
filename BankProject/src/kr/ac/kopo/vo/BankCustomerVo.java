package kr.ac.kopo.vo;

import java.util.List;

public class BankCustomerVo extends CommonVo {

	private String customerId;
	private String name;
	private String pwd;
	private String inputDate;
	private String registrationNumber;

	private List<ExternalInformationVo> externalBankVo;
	
	private List<HanaBankVo> hanaBankVo;
		
	public BankCustomerVo() {
	}

	public BankCustomerVo(String customerId, String name, String pwd, String inputDate, String registrationNumber) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.pwd = pwd;
		this.inputDate = inputDate;
		this.registrationNumber = registrationNumber;
	}

	public List<ExternalInformationVo> getExternalBankVo() {
		return externalBankVo;
	}

	public void setExternalBankVo(List<ExternalInformationVo> externalBankVo) {
		this.externalBankVo = externalBankVo;
	}

	public List<HanaBankVo> getHanaBankVo() {
		return hanaBankVo;
	}

	public void setHanaBankVo(List<HanaBankVo> hanaBankVo) {
		this.hanaBankVo = hanaBankVo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

}
