package kr.ac.kopo.ui;

import kr.ac.kopo.adminUI.AdminLoginUI;
import kr.ac.kopo.customerUI.AccountHistoryUI;
import kr.ac.kopo.customerUI.CustomerAllBankUI;
import kr.ac.kopo.customerUI.CustomerBankTransferUI;
import kr.ac.kopo.customerUI.CustomerBankUI;
import kr.ac.kopo.customerUI.CustomerCreateHanaBankUI;
import kr.ac.kopo.customerUI.CustomerDepositorWithdrawUI;
import kr.ac.kopo.customerUI.CustomerHanaBankUI;
import kr.ac.kopo.customerUI.CustomerLoginUI;
import kr.ac.kopo.customerUI.DeleteAccountUI;
import kr.ac.kopo.customerUI.ModifyNickNameUI;
import kr.ac.kopo.vo.BankCustomerVo;

public class BankUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		while(true) {	
			try {
				if(getBankUser() != null){
					
					while(true) {	
						int type = userMenu(getBankUser());
						IBoardUI customui = null;
						switch(type) {
						
						case 1://CASE :: 입금
							customui = new CustomerDepositorWithdrawUI(getBankUser(),1);
							break;
						case 2://CASE :: 출금
							customui = new CustomerDepositorWithdrawUI(getBankUser(),-1);
							break;
						case 3://CASE :: 계좌이체
							customui = new CustomerBankTransferUI(getBankUser());
							break;
						case 4://CASE :: 계좌등록
							customui = new CustomerCreateHanaBankUI(getBankUser());
							break;
						case 5://CASE :: 하나은행계좌조회
							customui = new CustomerHanaBankUI(getBankUser());
							break;
						case 6://CASE :: 은행별 계좌조회
							customui = new CustomerBankUI(getBankUser());
							break;
						case 7://CASE :: 전체은행조회
							customui = new CustomerAllBankUI(getBankUser());
							break;
						case 8://CASE :: 거래 내역 조회
							customui = new AccountHistoryUI(getBankUser());
							break;
						case 9://CASE :: 닉네임 변경
							customui = new ModifyNickNameUI(getBankUser());
							break;
						case 10://CASE :: 사용자 삭제
							customui = new DeleteAccountUI(getBankUser());
							break;
						case 0:
							setBankUser(null);
							break;
						}//LINE :: USER SWITCH 종료
						if(customui != null)
							customui.execute();//LINE :: 실행
						
						if(getBankUser()==null) {
							break;
						}//IF :: 사용자 화면 해제
					}//LINE :: WHILE 종료
				}//IF :: 로그인 되었을 때의 문장

				int type = mainMenu();
				IBoardUI mainui = null;
				
				switch(type) {
				
				case 1://CASE :: 사용자 로그인
					mainui = new CustomerLoginUI();
					break;
				case 2://CASE :: 관리자 로그인
					mainui = new AdminLoginUI();
					break;
				case 0://CASE :: 종료
					mainui = new ExitUI();
					break;
				}//LINE :: SWITCH 종료
				
				if(mainui != null)
					mainui.execute();
				else 
					System.out.println("잘못선택하셨습니다");
			}catch(Exception e){
			}
		}//LINE :: WHILE 종료
		
	}

	private int mainMenu() {
		
		System.out.println("                                                                                ");
		System.out.println("================================================================================");
		System.out.println("\t 하나은행 통합계좌 관리시스템	\t\t\t\t0. 종료");
		System.out.println("================================================================================");
		System.out.println("\t1. 사용자 로그인\t2. 관리자 로그인");
		System.out.println("--------------------------------------------------------------------------------");
		
		int type = scanInt("메뉴 중 원하는 항목을 선택하세요 ");
		
		return type;
	}
	
	private int userMenu(BankCustomerVo bankCustomerVo) {
		
		System.out.println("                                                                                ");
		System.out.println("================================================================================");
		System.out.println("\t 하나은행 통합계좌 관리시스템 \t\t "+bankCustomerVo.getName()+"님 환영합니다!\t\t0. 로그아웃");
		System.out.println("================================================================================");

		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\t 입출금/계좌이체 \t|\t계좌조회 \t\t\t|\t사용자 관리 ");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\t1. 입금\t\t|\t5. 하나은행계좌조회\t\t|\t9. 닉네임 변경");
		System.out.println("\t2. 출금\t\t|\t6. 은행별 계좌조회\t\t|\t10. 계좌 해지");
		System.out.println("\t3. 계좌 이체\t|\t7. 전체 은행 조회\t\t|\t");
		System.out.println("\t4. 계좌 등록\t|\t8. 거래내역 조회\t\t|\t");
		
		System.out.println("---------------------------------------------------------------------------------");
		
		int type = scanInt("원하시는 서비스를 선택해 주세요 :");
		
		System.out.println("---------------------------------------------------------------------------------");
		return type;
	}


}
