package kr.ac.kopo.customerUI;

import java.util.List;

import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.HanaBankVo;

public class CustomerHanaBankUI extends CustomerUI{


	private BankCustomerVo bankCustomUser;
	
	public CustomerHanaBankUI(BankCustomerVo bankUser) {
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {
		
		List<HanaBankVo> bankCustomerResult = service.searchHanaBankAccount(bankCustomUser);
		
		System.out.println("================================================================================");
		System.out.println("\t 사용자의 전체 계좌 조회");
		System.out.println("================================================================================");
		System.out.println("\t 계좌번호\t\t 잔액\t\t 별칭\t\t 은행아이디");
		System.out.println("---------------------------------------------------------------------------------");
		if(bankCustomerResult.size() ==0 ) {
			
			System.out.println("조회할 게시물이 없습니다.");		
		}else {
			
			for(HanaBankVo bankList : bankCustomerResult) {
				System.out.println("    "+bankList.getAccountNumber()+"\t"+
								   bankList.getAccountChangeWithComma()+"\t"+
								   bankList.getNickName() +"\t\t  "+
								   bankList.getCustomerId() );
			}
		}
		System.out.println("---------------------------------------------------------------------------------");
		
		
		
	}
	
}
