package kr.ac.kopo.service;

import kr.ac.kopo.dao.AdminDao;
import kr.ac.kopo.vo.Admin;

public class AdminService {

	private AdminDao dao;
	
	public AdminService() {
		dao = new AdminDao();
	}

	public Admin loginAdmin(String id, String password) throws Exception {
		
		Admin adminResult = dao.loginAdmin(id, password);
		
		if(adminResult.getResultCode() == 1) {
			adminResult.setResultString("로그인에 성공하였습니다.");
		} else if(adminResult.getResultCode() == 0) {
			adminResult.setResultString("오류입니다.");
		} else {
			adminResult.setResultString("사용자 정보가 없습니다.");
		}
		
		return adminResult;
	}
}
