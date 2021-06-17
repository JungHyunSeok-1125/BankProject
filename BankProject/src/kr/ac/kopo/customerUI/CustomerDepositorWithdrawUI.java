package kr.ac.kopo.customerUI;

import java.util.List;

import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.ExternalInformationVo;
import kr.ac.kopo.vo.HanaBankVo;

public class CustomerDepositorWithdrawUI extends CustomerUI {

	private BankCustomerVo bankCustomUser;
	private int bankMode;
	
	public CustomerDepositorWithdrawUI(BankCustomerVo bankUser,int i) {
		this.bankMode = i;
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {
		
		int i=0;
		int accountNum = 0;
		String mode;
		if(bankMode == 1) mode = "입금";
		else mode = "출금";
		int money = 1;
		List<HanaBankVo> bankCustomerResult = service.searchHanaBankAccount(bankCustomUser);
		List<ExternalInformationVo> bankAllCustomerResult = service.searchAllBank(bankCustomUser);
		
		System.out.println("============================================================================================================================");
		System.out.println("\t\t\t\t 하나은행 계좌 ");
		System.out.println("============================================================================================================================");
		System.out.println("계좌번호\t\t\t\t 잔액\t\t\t 별칭\t\t 은행아이디\t\t ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		if(bankCustomerResult.size() ==0 ) {
			
			System.out.println("조회할 게시물이 없습니다.");		
		}else {
			
			for(HanaBankVo bankList : bankCustomerResult) {
			System.out.println(i+1 +": "+
							   bankList.getAccountNumber()+"\t\t"+
						       bankList.getAccountChangeWithComma()+"\t\t"+
						       bankList.getNickName() +"\t\t"+
							   bankList.getCustomerId() );
			i++;
			}
			System.out.println("                                                                                                                            ");
			System.out.println("============================================================================================================================");
			System.out.println("\t\t\t\t 외부은행 계좌 ");
			System.out.println("============================================================================================================================");
			System.out.println("사용자아이디\t 은행이름\t\t 계좌번호\t\t\t 잔액\t\t\t 별칭\t\t\t 외부은행아이디");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			for(ExternalInformationVo bankList : bankAllCustomerResult) {
				System.out.println(i+1 +": "+
							bankList.getCustomerId()+"\t\t"+
								bankList.getBankName()+"\t\t"+
								bankList.getAccountNumber()+"\t\t"+
								bankList.getExternalBankVo().getAccountChangeWithComma() +"               "+
								bankList.getExternalBankVo().getNickName() +"\t\t"+
								bankList.getExternalBankVo().getCustomerId());
			i++;
			}
			
		}
		//LINE :: 사용자가 보유한 계좌 리스트를 조회한다.
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		accountNum = scanInt(mode +"할 계좌를 입력해 주세요 (숫자로 입력해주세요) : ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		while(money == 1) {
			money = scanInt(mode +"할 금액을 입력해 주세요 (2원 이상 입력해 주세요) : ");			
		}
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		
		
		
		if(accountNum <= bankCustomerResult.size()) {//IF :: 하나은행 계좌로 할지 아니면 외부 계좌로 할지의 분기점	

			if(bankMode == -1 && bankCustomerResult.get(accountNum-1).getAccountChange() < money) {//IF :: 잔액부족 확인
				
				System.out.println("잔액이 부족합니다");
			
			} else {//ELSE :: 돈이 충만한 경우
			
				System.out.println(bankCustomerResult.get(accountNum-1).getAccountNumber()+", 하나은행에 "+mode+"합니다.");
	
				bankCustomerResult.get(accountNum-1).setAccountChange(bankCustomerResult.get(accountNum-1).getAccountChange() + money * bankMode );//LINE :: 금액 변경
				
				service.bankDepositAccount(bankCustomerResult.get(accountNum-1), money*bankMode, bankMode);
			
			}
			
		} else {
			if(bankMode == -1 && bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1).getExternalBankVo().getAccountChange() < money) {//IF :: 외부은행 잔액부족 확인
				
				System.out.println("잔액이 부족합니다");
			
			} else {//ELSE :: 돈이 충만한 경우		
				
				System.out.println(bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1).getBankName()+", " +
				
				bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1).getAccountNumber()+" 에 "+ mode +"합니다.");
				
				bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1).getExternalBankVo().setAccountChange(bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1).getExternalBankVo().getAccountChange() + money * bankMode);
				
				service.bankDepositAccount(bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1),money*bankMode, bankMode);
			}
		}
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                                                                                            ");
		
		
		
		
	}

}
