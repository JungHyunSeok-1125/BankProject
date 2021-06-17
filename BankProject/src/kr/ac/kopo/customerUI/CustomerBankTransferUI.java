package kr.ac.kopo.customerUI;

import java.util.List;

import kr.ac.kopo.common.Bank_Mapper;
import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.ExternalInformationVo;
import kr.ac.kopo.vo.HanaBankVo;

public class CustomerBankTransferUI extends CustomerUI{

private BankCustomerVo bankCustomUser;
	
	public CustomerBankTransferUI(BankCustomerVo bankUser) {
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {

		int i=0;
		int accountNum = 0;
		int receiveBankNum = 0;
		
		List<HanaBankVo> bankCustomerResult = service.searchHanaBankAccount(bankCustomUser);
		List<ExternalInformationVo> bankAllCustomerResult = service.searchAllBank(bankCustomUser);
		
		accountNum = accountList(bankCustomerResult,bankAllCustomerResult, "송금");//LINE :: 송금 계좌 입력
		System.out.println("-----------------------------------------------");
		int money = scanInt("송금할 금액을 입력해 주세요 : ");
		
		
		
		if(accountNum <= bankCustomerResult.size()) {//IF :: 송금 계좌가 하나은행인 경우
			if(bankCustomerResult.get(accountNum-1).getAccountChange() < money) System.out.println("잔액이 부족합니다");//LINE :: 하나잔액부족 확인
			else {
				receiveBankNum = Bank_Mapper.bankList();
				String receiverAccount = scanStr("이체할 계좌의 계좌번호를 입력해 주세요 ");
				service.bankTransfer(bankCustomerResult.get(accountNum-1),receiveBankNum,receiverAccount,money);
			}
		} else { //ELSE :: 송금 계좌가 외부은행인 경우
			if(bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1).getExternalBankVo().getAccountChange() < money)System.out.println("잔액이 부족합니다");//LINE :: 외부은행 잔액부족 확인
			else {
				receiveBankNum = Bank_Mapper.bankList();
				String receiverAccount = scanStr("이체할 계좌의 계좌번호를 입력해 주세요 ");
				service.bankTransfer(bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1),receiveBankNum,receiverAccount,money);
					
				}
			}
		
		}//LINE :: execute() 종료
		
		
		
		
	

	/**
	 * 전체 계좌 띄우면서 숫자 입력 받는 메소드
	 * @param bankCustomerResult 하나은행 계좌 리스트
	 * @param bankAllCustomerResult 외부은행 계좌 리스트
	 * @param mode //mode 할 금액을 입력해 주세요 
	 * @return
	 */
	private int accountList(List<HanaBankVo> bankCustomerResult, List<ExternalInformationVo> bankAllCustomerResult ,String mode) {
		
		int i=0;
		int accountNum = 0;
		System.out.println("=================================================================================================================================================");
		System.out.println("\t\t\t\t 하나은행 계좌 ");
		System.out.println("=================================================================================================================================================");
		System.out.println("계좌번호\t\t\t\t 잔액\t\t\t 별칭\t\t 은행아이디\t\t ");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
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
			System.out.println("                                                                                                                                                 ");
			System.out.println("                                                                                                                                                 ");
			System.out.println("=================================================================================================================================================");
			System.out.println("\t\t\t\t 외부은행 계좌 ");
			System.out.println("=================================================================================================================================================");
			System.out.println("사용자아이디\t 은행이름\t\t 계좌번호\t\t\t 잔액\t\t\t 별칭\t\t\t 외부은행아이디");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
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
			
		}//LINE :: else 종료
		accountNum = scanInt(mode+"할 계좌를 입력해 주세요 (숫자로 입력해주세요) : ");
		return accountNum;
	}//LINE :: 리스트 메소드 종료
	

	


}


