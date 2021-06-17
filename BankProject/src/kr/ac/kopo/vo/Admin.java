package kr.ac.kopo.vo;

public class Admin extends CommonVo{

	int adminPk;
	String adminId;
	String adminPwd;
	String inputDate;
	String adminLevel;

	public Admin() {
	}

	public Admin(int adminPk, String adminId, String adminPwd, String inputDate, String adminLevel) {
		super();
		this.adminPk = adminPk;
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.inputDate = inputDate;
		this.adminLevel = adminLevel;
	}

	public int getAdminPk() {
		return adminPk;
	}

	public void setAdminPk(int adminPk) {
		this.adminPk = adminPk;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}

}
