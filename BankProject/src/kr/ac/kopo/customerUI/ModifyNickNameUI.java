package kr.ac.kopo.customerUI;

import java.util.List;

import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.ExternalInformationVo;
import kr.ac.kopo.vo.HanaBankVo;

public class ModifyNickNameUI extends CustomerUI {

	private BankCustomerVo bankCustomUser;
	
	public ModifyNickNameUI(BankCustomerVo bankUser) {
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {
		
		int i=0;
		int accountNum = 0;
		
		List<HanaBankVo> bankCustomerResult = service.searchHanaBankAccount(bankCustomUser);
		List<ExternalInformationVo> bankAllCustomerResult = service.searchAllBank(bankCustomUser);
		
		System.out.println("==============================================================================================");
		System.out.println("\t\t\t\t 하나은행 계좌 ");
		System.out.println("==============================================================================================");
		System.out.println("계좌번호\t\t\t\t 잔액\t\t\t 별칭\t\t 은행아이디\t\t ");
		System.out.println("----------------------------------------------------------------------------------------------");
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
			System.out.println("==============================================================================================");
			System.out.println("\t\t\t\t 외부은행 계좌 ");
			System.out.println("==============================================================================================");
			System.out.println("사용자아이디\t 은행이름\t\t 계좌번호\t\t\t 잔액\t\t\t 별칭\t\t\t 외부은행아이디");
			System.out.println("----------------------------------------------------------------------------------------------");
			for(ExternalInformationVo bankList : bankAllCustomerResult) {
				System.out.println(i+1 +": "+
							bankList.getCustomerId()+"\t\t"+
								bankList.getBankName()+"\t\t"+
								bankList.getAccountNumber()+"\t\t"+
								bankList.getExternalBankVo().getAccountChange() +"               "+
								bankList.getExternalBankVo().getNickName() +"\t\t"+
								bankList.getExternalBankVo().getCustomerId());
			i++;
			}
			
		}
		//LINE :: 사용자가 보유한 계좌 리스트를 조회한다.
		accountNum = scanInt("변경할 계좌를 입력해 주세요 (숫자로 입력해주세요) : ");
		System.out.println("-----------------------------------------------");

		String nickName = scanStr("변경할 닉네임을 입력해 주세요 : ");
		
		
		
		if(accountNum <= bankCustomerResult.size()) {//IF :: 하나은행 계좌로 할지 아니면 외부 계좌로 할지의 분기점	
			System.out.println(bankCustomerResult.get(accountNum-1).getAccountNumber()+" 계좌의 닉네임을 변경합니다.");
			bankCustomerResult.get(accountNum-1).setNickName(nickName);
			//LINE :: 금액 변경
			service.bankDepositAccount(bankCustomerResult.get(accountNum-1),0,0);
			
		} else {
			System.out.println(bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1).getAccountNumber()+" 계좌의 닉네임을 변경합니다.");
			
			bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1).getExternalBankVo().setNickName(nickName);
			service.bankDepositAccount(bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1),0,0);
		}
		
		System.out.println("-----------------------------------------------");
		
		
		
	}

}
