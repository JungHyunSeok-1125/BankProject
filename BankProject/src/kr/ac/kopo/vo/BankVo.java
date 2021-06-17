package kr.ac.kopo.vo;

import java.util.List;

public class BankVo extends CommonVo {

    private int bankPk;
	private String bankName;

	private List<ExternalInformationVo> externamBankInformationVo;
	
	public BankVo() {
	}

	public BankVo(int bankPk, String bankName) {
		super();
		this.bankPk = bankPk;
		this.bankName = bankName;
	}

	public List<ExternalInformationVo> getExternamBankInformationVo() {
		return externamBankInformationVo;
	}

	public void setExternamBankInformationVo(List<ExternalInformationVo> externamBankInformationVo) {
		this.externamBankInformationVo = externamBankInformationVo;
	}

	public int getBankPk() {
		return bankPk;
	}

	public void setBankPk(int bankPk) {
		this.bankPk = bankPk;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
