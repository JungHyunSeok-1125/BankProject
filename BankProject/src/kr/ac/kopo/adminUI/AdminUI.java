package kr.ac.kopo.adminUI;

import kr.ac.kopo.service.AdminService;
import kr.ac.kopo.service.AdminServiceFactory;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.Admin;

public abstract class AdminUI extends BaseUI{

	protected AdminService service;
	
	private static Admin admin = null;
	
	public AdminUI() {
		service = AdminServiceFactory.getInstance();
	}

	public void setAdminUser(Admin admin) {
		this.admin = admin;
	}
	
	
}
