package kr.ac.kopo.adminUI;

import kr.ac.kopo.vo.Admin;

public class AdminLoginUI extends AdminUI {

	@Override
	public void execute() throws Exception {

		System.out.println("-------------------------------------");
		String id = scanStr("아이디를 입력하세요 : ");
		String password = scanStr("비밀번호를 입력하세요 : ");
		
		Admin adminResult = service.loginAdmin(id, password);

		setAdminUser(adminResult);
		
	}

}
