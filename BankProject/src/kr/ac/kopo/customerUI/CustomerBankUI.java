package kr.ac.kopo.customerUI;

import java.util.List;

import kr.ac.kopo.common.Bank_Mapper;
import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.ExternalInformationVo;

public class CustomerBankUI extends CustomerUI {

	private BankCustomerVo bankCustomUser;
	
	public CustomerBankUI(BankCustomerVo bankUser) {
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {
		
		int bankNum = Bank_Mapper.bankListWithOutHana();
		
		List<ExternalInformationVo> bankCustomerResult = service.searchBank(bankCustomUser,bankNum);
		
		System.out.println("============================================================================================================================");
		System.out.println("\t\t\t\t\t "+bankCustomerResult.get(0).getBankName()+" 계좌 조회");
		System.out.println("============================================================================================================================");
		System.out.println("사용자아이디\t 은행이름\t\t 계좌번호\t\t\t 잔액\t\t\t 별칭\t\t\t 외부은행아이디");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		if(bankCustomerResult.size() ==0 ) {
			
			System.out.println("\t조회할 게시물이 없습니다.");		
		}else {
			
			for(ExternalInformationVo bankList : bankCustomerResult) {
				System.out.println(bankList.getCustomerId()+"\t\t"+bankList.getBankName()+"\t\t"+bankList.getAccountNumber()+"\t\t"+bankList.getExternalBankVo().getAccountChangeWithComma() +"\t\t"+
			bankList.getExternalBankVo().getNickName() +"\t\t"+
			bankList.getExternalBankVo().getCustomerId());
			}
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	}

}
