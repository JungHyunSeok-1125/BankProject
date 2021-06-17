package kr.ac.kopo.vo;

import kr.ac.kopo.common.COLUMN;
import kr.ac.kopo.util.DateUtils;

public class HistoryVo extends CommonVo {

	private int historyPk;
	private String task;
	private String date;
	private String receiver;
	private int receiverBank;
	private String senderAccount;
	private int senderBank;
	private int beforeChange;
	private int modChange;
	
	public HistoryVo() {
	}

	public HistoryVo(int historyPk, String task, String date, String receiver, int receiverBank, String senderAccount,
			int senderBank) {
		super();
		this.historyPk = historyPk;
		this.task = task;
		this.date = date;
		this.receiver = receiver;
		this.receiverBank = receiverBank;
		this.senderAccount = senderAccount;
		this.senderBank = senderBank;
	}
	

	public HistoryVo(String task, String date, String senderAccount, int senderBank, int beforeChange, int modChange) {
		this.task = task;
		this.date = date;
		this.senderAccount = senderAccount;
		this.senderBank = senderBank;
		this.beforeChange=beforeChange;
		this.modChange=modChange;
	}


	public int getHistoryPk() {
		return historyPk;
	}

	public void setHistoryPk(int historyPk) {
		this.historyPk = historyPk;
	}

	public String getTask() {
		if(this.task.equals(COLUMN.ACCOUNT.TASK.DEPOSIT_ATM)) {
			return COLUMN.ACCOUNT.TASK_KOR.DEPOSIT_ATM_KOR;
		}else if(this.task.equals(COLUMN.ACCOUNT.TASK.WITHDRAWAL_ATM)) {
			return COLUMN.ACCOUNT.TASK_KOR.WITHDRAWAL_ATM_KOR;
		}else if(this.task.equals(COLUMN.ACCOUNT.TASK.DEPOSIT)) {
			return COLUMN.ACCOUNT.TASK_KOR.DEPOSIT_KOR;
		}else if(this.task.equals(COLUMN.ACCOUNT.TASK.WITHDRAWAL)) {
			return COLUMN.ACCOUNT.TASK_KOR.WITHDRAWAL_KOR;
		}
		return task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}

	public String getDate() {
		return date;
	}
	
	public String getDateFormat() {
		String formatDate = DateUtils.restoreDatetime(date);
		return formatDate;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getReceiverBank() {
		return receiverBank;
	}

	public void setReceiverBank(int receiverBank) {
		this.receiverBank = receiverBank;
	}

	public String getSenderAccount() {
		if(senderAccount.equals(COLUMN.DEFAULTSTATE)) {
			return "\t";
		}
		return senderAccount;
	}

	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	public String getSenderBank() {
		if(senderBank == COLUMN.BANK.BANK_TABLE_NO.HANA_BANK) {
			return "하나은행";
		}else if(senderBank == COLUMN.BANK.BANK_TABLE_NO.SHINHAN_BANK) {
			return "신한은행";
		}else if(senderBank == COLUMN.BANK.BANK_TABLE_NO.KOOKMIN_BANK) {
			return "국민은행";
		}else if(senderBank == COLUMN.BANK.BANK_TABLE_NO.IBK_BANK) {
			return "기업은행";
		}else if(senderBank == COLUMN.BANK.BANK_TABLE_NO.WOORI_BANK) {
			return "우리은행";
		} else if(senderBank == COLUMN.BANK.BANK_TABLE_NO.WOORI_BANK) {
			return "우리은행";
		} else if(senderBank == COLUMN.DEFAULTSTATENUM) {
			return "    ";
		} else {
			return Integer.toString(senderBank);
		}
	}

	public void setSenderBank(int senderBank) {
		this.senderBank = senderBank;
	}

	public int getBeforeChange() {
		return beforeChange;
	}

	public void setBeforeChange(int beforeChange) {
		this.beforeChange = beforeChange;
	}

	public int getModChange() {
		return modChange;
	}

	public void setModChange(int modChange) {
		this.modChange = modChange;
	}
	

}
