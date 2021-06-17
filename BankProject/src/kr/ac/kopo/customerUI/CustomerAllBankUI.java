package kr.ac.kopo.customerUI;

import java.util.List;

import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.ExternalInformationVo;
import kr.ac.kopo.vo.HanaBankVo;

public class CustomerAllBankUI extends CustomerUI {

	private BankCustomerVo bankCustomUser;
	
	public CustomerAllBankUI(BankCustomerVo bankUser) {
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {
		
		List<HanaBankVo> bankCustomerResult = service.searchHanaBankAccount(bankCustomUser);
		List<ExternalInformationVo> bankAllCustomerResult = service.searchAllBank(bankCustomUser);
		
		accountList(bankCustomerResult,bankAllCustomerResult); 
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * 전체 계좌 띄우면서 숫자 입력 받는 메소드
	 * @param bankCustomerResult 하나은행 계좌 리스트
	 * @param bankAllCustomerResult 외부은행 계좌 리스트
	 * @param mode //mode 할 금액을 입력해 주세요 
	 * @return
	 */
	private void accountList(List<HanaBankVo> bankCustomerResult, List<ExternalInformationVo> bankAllCustomerResult) {
		
		int i=0;
		System.out.println("=================================================================================================================================================");
		System.out.println("\t\t\t\t 하나은행 계좌 ");
		System.out.println("=================================================================================================================================================");
		System.out.println("계좌번호\t\t\t\t 잔액\t\t\t 별칭\t\t 은행아이디\t\t ");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
		if(bankCustomerResult.size() ==0 ) {
			
			System.out.println("\t조회할 게시물이 없습니다.");		
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
	}//LINE :: 리스트 메소드 종료
	

}
