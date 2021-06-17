package kr.ac.kopo.customerUI;

import java.util.List;

import kr.ac.kopo.common.Bank_Mapper;
import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.HanaBankVo;

public class DeleteAccountUI extends CustomerUI{


	private BankCustomerVo bankCustomUser;
	
	public DeleteAccountUI(BankCustomerVo bankUser) {
		this.bankCustomUser = bankUser;
	}

	@Override
	public void execute() throws Exception {
		
		List<HanaBankVo> bankCustomerResult = service.searchHanaBankAccount(bankCustomUser);
		int i=0;
		int delAccountNum = 0;
		int receiveBankNum = -1;
		String receiverAccount = null;
		
		System.out.println("-----------------------------------------------");
		System.out.println("\t 사용자의 전체 계좌 조회");
		System.out.println("-----------------------------------------------");
		System.out.println("\t\t 계좌번호\t\t 잔액\t\t 별칭\t\t 은행아이디");
		System.out.println("-----------------------------------------------");
		if(bankCustomerResult.size() ==0 ) {
			
			System.out.println("조회할 게시물이 없습니다.");		
		}else {
			
			for(HanaBankVo bankList : bankCustomerResult) {
				System.out.println(i+1 +": "+ bankList.getAccountNumber()+"\t\t"+
							       bankList.getAccountChangeWithComma()+"\t\t"+
							       bankList.getAccountNumber()+"\t\t"+bankList.getNickName() +"\t\t"+
							       bankList.getCustomerId() );
				i++;
			}
		}
		
		delAccountNum = scanInt("삭제할 계좌를 입력해 주세요 (숫자로 입력해주세요) : ");
		
		if(bankCustomerResult.get(delAccountNum-1).getAccountChange() > 0) {
			
			System.out.println("잔액이 남아있습니다. 잔액을 입금할 은행을 선택해 주세요");
			receiveBankNum = Bank_Mapper.bankList() -1;
			receiverAccount = scanStr("잔액을 이체할 계좌의 계좌번호를 입력해 주세요 ");
		}
		
		service.deleteAccount(bankCustomerResult.get(delAccountNum-1),receiveBankNum,receiverAccount);
		
		
		System.out.println("-----------------------------------------------");
		
		
		
	}
	
	
}
