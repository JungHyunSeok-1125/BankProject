package kr.ac.kopo.customerUI;

import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.HanaBankVo;

public class CustomerCreateHanaBankUI extends CustomerUI{

private BankCustomerVo bankCustomUser;
	
	public CustomerCreateHanaBankUI(BankCustomerVo bankUser) {
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {
		
		if(service.checkMonthRegistration(bankCustomUser)) {
			System.out.println("30일 이후에 새로운 계좌 생성이 가능합니다.");			
		} else {
			
			System.out.println("---------------------------------------------------------------------------------");
		
		String nickName = scanStr("닉네임을 입력하세요 : ");
		HanaBankVo hanaBankResult = service.createRegistration(bankCustomUser,nickName);
		System.out.println("\t계좌 생성에 성공하였습니다");
		System.out.println("---------------------------------------------------------------------------------");
		}
	}
}
