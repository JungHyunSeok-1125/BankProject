package kr.ac.kopo.customerUI;

import kr.ac.kopo.vo.BankCustomerVo;

public class CustomerLoginUI extends CustomerUI {

	@Override
	public void execute() throws Exception {

		System.out.println("--------------------------------------------------------------------------------");
		String id = scanStr("아이디를 입력하세요 : ");
		String password = scanStr("비밀번호를 입력하세요 : ");
		System.out.println("--------------------------------------------------------------------------------");
		
		BankCustomerVo bankCustomerResult = service.loginCustomer(id, password);
		System.out.println(bankCustomerResult.getResultString());
		
		setBankUser(bankCustomerResult);
		
		
	}

}
