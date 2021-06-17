package kr.ac.kopo.vo;

public class ExternalInformationVo extends CommonVo {

	private int no;
	private int bankNum;
	private String customerId;
	private String accountNumber;
	
	private BankCustomerVo bankCustomers;
	private BankVo banks;
	private ExternalBankVo externalBankVo;
	
	public ExternalInformationVo() {
	}

	public ExternalInformationVo(int no, int bankNum, String customerId, String accountNumber) {
		super();
		this.no = no;
		this.bankNum = bankNum;
		this.customerId = customerId;
		this.accountNumber = accountNumber;
	}

	public ExternalInformationVo(int no, int BANK_PK, String INTERNAL_CUSTOMER_ID, String ACCOUNT_NUMBER, Long ACCOUNT_CHANGE, String NICK_NAME, String EXTERNAL_CUSTOMER_ID) {

		ExternalBankVo externamBankVo = new ExternalBankVo(ACCOUNT_NUMBER, ACCOUNT_CHANGE, NICK_NAME, EXTERNAL_CUSTOMER_ID);
		this.no = no;
		this.bankNum = BANK_PK;
		this.customerId = INTERNAL_CUSTOMER_ID;
		this.accountNumber = ACCOUNT_NUMBER;
		this.setExternalBankVo(externamBankVo);
		
		
	}

	public BankCustomerVo getBankCustomers() {
		return bankCustomers;
	}

	public void setBankCustomers(BankCustomerVo bankCustomers) {
		this.bankCustomers = bankCustomers;
	}

	public BankVo getBanks() {
		return banks;
	}

	public void setBanks(BankVo banks) {
		this.banks = banks;
	}
	
	public ExternalBankVo getExternalBankVo() {
		return externalBankVo;
	}

	public void setExternalBankVo(ExternalBankVo externalBankVo) {
		this.externalBankVo = externalBankVo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBankNum() {
		return bankNum;
	}
	
	public String getBankName() {
		if(this.bankNum == 1) return "신한은행";
		else if(this.bankNum ==2)return "국민은행";
		else if(this.bankNum ==3)return "기업은행";
		else if(this.bankNum ==4)return "우리은행";
		else return "하나은행";
	}
	
	public String getEngBankName() {
		if(this.bankNum == 1) return "SHINHAN_BANK";
		else if(this.bankNum ==2)return "KOOKMIN_BANK";
		else if(this.bankNum ==3)return "IBK_BANK";
		else if(this.bankNum ==4)return "WOORI_BANK";
		else return "HANA_BANK";
	}
	
	

	public void setBankNum(int bankNum) {
		this.bankNum = bankNum;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
