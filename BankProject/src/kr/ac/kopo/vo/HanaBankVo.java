package kr.ac.kopo.vo;

import java.text.DecimalFormat;

public class HanaBankVo extends CommonVo{

	private int AccountPk;
	private String AccountNumber;
	private int AccountChange;
	private String NickName;
	private String CustomerId;
	private String inputDate;
	
	private BankCustomerVo bankCustomers;
	
	public HanaBankVo() {
	}

	public HanaBankVo(int accountPk, String accountNumber, int accountChange, String nickName, String customerId) {
		super();
		AccountPk = accountPk;
		this.AccountNumber = accountNumber;
		this.AccountChange = accountChange;
		this.NickName = nickName;
		this.CustomerId = customerId;
	}
	
	public HanaBankVo(int accountPk, String accountNumber, int accountChange, String nickName, String customerId, String inputDate) {
		super();
		this.AccountPk = accountPk;
		this.AccountNumber = accountNumber;
		this.AccountChange = accountChange;
		this.NickName = nickName;
		this.CustomerId = customerId;
		this.inputDate = inputDate;
	}
	

	public BankCustomerVo getBankCustomers() {
		return bankCustomers;
	}

	public void setBankCustomers(BankCustomerVo bankCustomers) {
		this.bankCustomers = bankCustomers;
	}

	public int getAccountPk() {
		return AccountPk;
	}

	public void setAccountPk(int accountPk) {
		AccountPk = accountPk;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public int getAccountChange() {
		return AccountChange;
	}
	
	public String getAccountChangeWithComma() {
		
		DecimalFormat formater = new DecimalFormat("###,###,###,###,###,###");
		
		return formater.format(this.AccountChange);
	}

	public void setAccountChange(int accountChange) {
		AccountChange = accountChange;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

}
