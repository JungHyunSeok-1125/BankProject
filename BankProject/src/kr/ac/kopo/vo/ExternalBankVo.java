package kr.ac.kopo.vo;

import java.text.DecimalFormat;

public class ExternalBankVo extends CommonVo {

	private String accountNumber;
	private Long accountChange;
	private String nickName;
	private String customerId;

	
	public ExternalBankVo() {
	}

	public ExternalBankVo(String accountNumber, Long accountChange, String nickName, String customerId) {
		super();
		this.accountNumber = accountNumber;
		this.accountChange = accountChange;
		this.nickName = nickName;
		this.customerId = customerId;
	}


	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getAccountChange() {
		return accountChange;
	}
	
	public String getAccountChangeWithComma() {
		
		DecimalFormat formater = new DecimalFormat("###,###,###,###,###,###");
		
		return formater.format(this.accountChange);
	}

	public void setAccountChange(Long accountChange) {
		this.accountChange = accountChange;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
