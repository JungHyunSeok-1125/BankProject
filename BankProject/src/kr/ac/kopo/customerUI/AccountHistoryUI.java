package kr.ac.kopo.customerUI;

import java.util.List;

import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.ExternalInformationVo;
import kr.ac.kopo.vo.HanaBankVo;
import kr.ac.kopo.vo.HistoryVo;

public class AccountHistoryUI extends CustomerUI{


	private BankCustomerVo bankCustomUser;
	
	public AccountHistoryUI(BankCustomerVo bankUser) {
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {
		
		int i=0;
		int accountNum = 0;
		int receiveBankNum = 0;
		List<HistoryVo> hanaBankHistory = null;
		
		List<HanaBankVo> bankCustomerResult = service.searchHanaBankAccount(bankCustomUser);
		List<ExternalInformationVo> bankAllCustomerResult = service.searchAllBank(bankCustomUser);
		
		accountNum = accountList(bankCustomerResult,bankAllCustomerResult, "조회");//LINE :: 송금 계좌 입력
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		
			if(accountNum <= bankCustomerResult.size()) {//IF :: 송금 계좌가 하나은행인 경우
				hanaBankHistory = service.searchHistory(bankCustomerResult.get(accountNum-1));
			} else { //ELSE :: 송금 계좌가 외부은행인 경우
				hanaBankHistory = service.searchHistory(bankAllCustomerResult.get(accountNum-bankCustomerResult.size()-1));
			}
		System.out.println("=================================================================================================================================================");
		System.out.println("\t\t\t\t 외부은행 계좌 ");
		System.out.println("=================================================================================================================================================");
		System.out.println("업무\t\t\t\t 시간 \t\t\t 상대 계좌 번호\t\t 거래 은행\t\t\t 거래금액 \t\t\t거래 후 잔액 ");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");	
		for(HistoryVo hv : hanaBankHistory) {
			System.out.println(i+1 +": "+
					hv.getTask()+"\t\t"+
					hv.getDateFormat()+"\t\t"+
					hv.getSenderAccount()+"\t\t"+
					hv.getSenderBank() +"\t\t\t"+
					hv.getModChange()+"\t\t\t"+
					hv.getBeforeChange() 
					);
			i++;
		}
	}
	


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
				System.out.println(i+1 +":  "+
							bankList.getCustomerId()+"\t\t"+
								bankList.getBankName()+"\t\t"+
								bankList.getAccountNumber()+"\t\t"+
								bankList.getExternalBankVo().getAccountChangeWithComma() +"               "+
								bankList.getExternalBankVo().getNickName() +"\t\t"+
								bankList.getExternalBankVo().getCustomerId());
			i++;
			}
			
		}//LINE :: else 종료
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
		
		accountNum = scanInt(mode+"할 계좌를 입력해 주세요 (숫자로 입력해주세요) : ");
		return accountNum;
	}//LINE :: 리스트 메소드 종료
	


	
	

}
