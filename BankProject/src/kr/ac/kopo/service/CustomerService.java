package kr.ac.kopo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.common.COLUMN;
import kr.ac.kopo.dao.BankCustomerDao;
import kr.ac.kopo.util.DateUtils;
import kr.ac.kopo.util.InputUtils;
import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.ExternalInformationVo;
import kr.ac.kopo.vo.HanaBankVo;
import kr.ac.kopo.vo.HistoryVo;

public class CustomerService{

	private BankCustomerDao dao;
	
	public CustomerService() {
		dao = new BankCustomerDao();
	}
	

	
	/**
	 * 로그인 기능 구현
	 * @param id 아이디
	 * @param password 패스워드
	 * @return
	 * @throws Exception
	 */
	public BankCustomerVo loginCustomer(String id, String password) throws Exception {
		
		BankCustomerVo bankCustomerResult = dao.loginBankCustomer(id, password);
		
		if(bankCustomerResult.getResultCode() == 1) {
			bankCustomerResult.setResultString("\t로그인에 성공하였습니다.");
		} else if(bankCustomerResult.getResultCode() == 0) {
			bankCustomerResult.setResultString("오류입니다.");
		} else {
			bankCustomerResult.setResultString("사용자 정보가 없습니다.");
		}
		
		return bankCustomerResult;
	}

	/**
	 * 해당 은행 조회해서 리턴
	 * @param bankCustomUser 로그인된 사용자
	 * @param bankNum 은행번호
	 * @return
	 */
	public List<ExternalInformationVo> searchBank(BankCustomerVo bankCustomUser, int bankNum) {
		
		List<ExternalInformationVo> bankAllResult = new ArrayList<ExternalInformationVo>();
		String bank = null;
		if (bankNum == 1) bank = "SHINHAN_BANK";
		else if (bankNum == 2) bank = "KOOKMIN_BANK";
		else if (bankNum == 3) bank = "IBK_BANK";
		else if (bankNum == 4) bank = "WOORI_BANK";
		
		List<ExternalInformationVo> bankResult = dao.searchByBankName(bankCustomUser, bank);
		
		bankAllResult.addAll(bankResult);
		
		return bankAllResult;
	}

	/**
	 * 전체 은행 조회
	 * @param bankCustomUser
	 * @return
	 */
	public List<ExternalInformationVo> searchAllBank(BankCustomerVo bankCustomUser) {
		
		List<ExternalInformationVo> bankAllResult = new ArrayList<ExternalInformationVo>();
		List<ExternalInformationVo> bankResult = dao.searchAllBankName(bankCustomUser);
		
		bankAllResult.addAll(bankResult);
				
		return bankAllResult;
	}
	
	/**
	 * 하나은행 계좌 조회
	 * @param bankCustomUser
	 * @return
	 */
	public List<HanaBankVo> searchHanaBankAccount(BankCustomerVo bankCustomUser) {
		
		List<HanaBankVo> hanaBankResult = new ArrayList<HanaBankVo>();
		
		hanaBankResult = dao.searchHanaBankByCustomer(bankCustomUser);
		
		return hanaBankResult;
	}
	
	/**
	 * 계좌 생성 가능 여부 판단
	 * @param bankCustomUser
	 * @return
	 * @throws Exception
	 */
	public boolean checkMonthRegistration(BankCustomerVo bankCustomUser) throws Exception {
		
		HanaBankVo hanaBankResult = new HanaBankVo();
		
		hanaBankResult = dao.searchRecentHanaBankReg(bankCustomUser);
		
		if(hanaBankResult.getResultCode() < 0 ||DateUtils.addOneMonth(hanaBankResult.getInputDate())) 
			return false; 
		else 
			return true;
	}

	
	/**
	 * 계좌 생성
	 * @param bankCustomUser
	 * @param nickName
	 * @return
	 * @throws Exception
	 */
	public HanaBankVo createRegistration(BankCustomerVo bankCustomUser, String nickName) throws Exception {
		
		HanaBankVo hanaBankResult = new HanaBankVo();
		
		hanaBankResult = dao.searchRecentHanaBankReg(bankCustomUser);
		
		if(hanaBankResult.getResultCode() < 0) {

			HanaBankVo addHanaBankResult = new HanaBankVo();
			
			addHanaBankResult.setAccountNumber(InputUtils.numberGen(6, 1)+"-"+InputUtils.numberGen(6, 1));
			addHanaBankResult.setAccountChange(1000);
			addHanaBankResult.setNickName(nickName);
			addHanaBankResult.setCustomerId(bankCustomUser.getCustomerId());
			
			dao.addHanaBankCustomer(addHanaBankResult);
		//IF :: 계좌가 하나도 없는 경우
		} else if(DateUtils.addOneMonth(hanaBankResult.getInputDate())) {
			
			HanaBankVo addHanaBankResult = new HanaBankVo();
			
			addHanaBankResult.setAccountNumber(InputUtils.numberGen(6, 1)+"-"+InputUtils.numberGen(6, 1));
			addHanaBankResult.setAccountChange(1000);
			addHanaBankResult.setNickName(nickName);
			addHanaBankResult.setCustomerId(bankCustomUser.getCustomerId());
			
			dao.addHanaBankCustomer(addHanaBankResult);
		//ELSE - IF :: 계좌 존재하는 경우 1달 막기
		}
		
		
		return hanaBankResult;
	}

	/**
	 * 입금 (하나은행)
	 * @param hanaBankVo 이체할 계좌
	 * @param money 이체할 금액
	 * @return
	 * @throws Exception 
	 */
	public void bankDepositAccount(HanaBankVo hanaBankVo, int money, int mode) throws Exception {

		dao.updateHanaBankChange(hanaBankVo, money, mode);
	}
	
	/**
	 * 입금 (외부은행)
	 * @param extBankVo
	 * @throws SQLException 
	 */
	public void bankDepositAccount(ExternalInformationVo extBankVo,int money, int mode) throws SQLException {

		dao.updateExternalBankChange(extBankVo,money,mode);
		
	}

	/**
	 * 계좌이체 (오버로딩)
	 * @param hanaBankVo 보내는 은행 (하나은행)
	 * @param receiverBank 받는 은행 번호
	 * @param receiverAccount 받는 은행 계좌
	 * @param money 금액
	 * @throws Exception 예외처리
	 */
	public void bankTransfer(HanaBankVo hanaBankVo, int receiverBank, String receiverAccount, int money) throws Exception {

		String bankName= null;
		HanaBankVo hanaReceiverResult = null;
		ExternalInformationVo externalReceiverResult = null;
		hanaBankVo.setAccountChange(hanaBankVo.getAccountChange() - money);//LINE :: 금액 차감

		if(receiverBank == 1) {
			bankName =  "SHINHAN_BANK";
		}
		else if(receiverBank == 2) bankName = "KOOKMIN_BANK";
		else if(receiverBank == 3) bankName = "IBK_BANK";
		else if(receiverBank == 4) bankName = "WOORI_BANK";
		else bankName =  "HANA_BANK";
		
		if(bankName.equals("HANA_BANK")) {
			hanaReceiverResult = dao.obtainHanaBank(receiverAccount);

			if(hanaReceiverResult.getResultCode() > 0) {
				hanaReceiverResult.setAccountChange(hanaReceiverResult.getAccountChange() + money);
				dao.hanaBankTransfer(hanaBankVo, hanaReceiverResult, money);
			} else {
				System.out.println("계좌가 존재하지 않습니다.");
			}
		}else {
			externalReceiverResult = dao.obtainExternalBank(bankName,receiverAccount);
			
			if(externalReceiverResult.getResultCode() > 0) {
				externalReceiverResult.getExternalBankVo().setAccountChange(externalReceiverResult.getExternalBankVo().getAccountChange() + money);
				dao.hanaBankTransfer(hanaBankVo, externalReceiverResult,money,COLUMN.ACCOUNT.PROCEDURE_DEPOSIT_WITHDRAWAL.HANA_TO_EXTERNAL);
			}else {
				System.out.println("계좌가 존재하지 않습니다.");
			}
		}
		
		
	}

	/**
	 * 계좌 송금 (오버로딩)
	 * @param externalInformationVo 보내는 외부 은행 정보
	 * @param receiverBank 받는 은행
	 * @param receiverAccount 계좌
	 * @param money 금액
	 * @throws Exception 예외처리
	 */
	public void bankTransfer(ExternalInformationVo externalInformationVo, int receiverBank,String receiverAccount, int money) throws Exception {

		String bankName= null;
		HanaBankVo hanaResult = null;
		ExternalInformationVo externalResult = null;
		externalInformationVo.getExternalBankVo().setAccountChange(externalInformationVo.getExternalBankVo().getAccountChange() - money);//LINE :: 금액 증가	
		if(receiverBank == 1) {
			bankName =  "SHINHAN_BANK";
		}
		else if(receiverBank == 2) bankName = "KOOKMIN_BANK";
		else if(receiverBank == 3) bankName = "IBK_BANK";
		else if(receiverBank == 4) bankName = "WOORI_BANK";
		else bankName =  "HANA_BANK";
		
		if(bankName.equals("HANA_BANK")) {
			hanaResult = dao.obtainHanaBank(receiverAccount);//LINE :: 하나은행 정보 가져오기

			if(hanaResult.getResultCode() > 0) {
				hanaResult.setAccountChange(hanaResult.getAccountChange() + money);
				dao.hanaBankTransfer(hanaResult, externalInformationVo,money,COLUMN.ACCOUNT.PROCEDURE_DEPOSIT_WITHDRAWAL.HANA_TO_EXTERNAL);
			} else {
				System.out.println("계좌가 존재하지 않습니다.");
			}
		}else {
			externalResult = dao.obtainExternalBank(bankName,receiverAccount);//LINE :: 외부은행 정보 가져오기
			
			if(externalResult.getResultCode() > 0) {
				externalResult.getExternalBankVo().setAccountChange(externalResult.getExternalBankVo().getAccountChange() + money);
				dao.hanaBankTransfer(externalInformationVo, externalResult,money);
			}else {
				System.out.println("계좌가 존재하지 않습니다.");
			}
		}
	}

	/**
	 * 계좌 삭제
	 * @param hanaBankVo 삭제할 하나은행 계좌
	 * @param receiveBankNum 잔액 받을 은행 번호
	 * @param receiverAccount 잔액 받을 은행 계좌 번호
	 * @throws Exception
	 */
	public void deleteAccount(HanaBankVo hanaBankVo, int receiveBankNum, String receiverAccount) throws Exception {
		
		String bankName= null;
		HanaBankVo hanaResult = null;
		ExternalInformationVo externalResult = null;
		
		if(receiveBankNum == 1) {
			bankName =  "SHINHAN_BANK";
		}
		else if(receiveBankNum == 2) bankName = "KOOKMIN_BANK";
		else if(receiveBankNum == 3) bankName = "IBK_BANK";
		else if(receiveBankNum == 4) bankName = "WOORI_BANK";
		else if(receiveBankNum == 0)bankName =  "HANA_BANK";
		else bankName="NOMONEY";
		
		if(bankName.equals("HANA_BANK")) {
			hanaResult = dao.obtainHanaBank(receiverAccount);//LINE :: 하나은행 정보 가져오기

			if(hanaResult.getResultCode() > 0) {
				hanaResult.setAccountChange(hanaResult.getAccountChange() + hanaBankVo.getAccountChange());
				dao.hanaBankDeleteAccount(hanaBankVo, hanaResult);
			} else {
				System.out.println("계좌가 존재하지 않습니다.");
			}
		}else if(bankName.equals("NOMONEY")){
			dao.hanaBankDeleteAccount(hanaBankVo);
		} else {
			externalResult = dao.obtainExternalBank(bankName,receiverAccount);//LINE :: 외부은행 정보 가져오기
			
			if(externalResult.getResultCode() > 0) {
				externalResult.getExternalBankVo().setAccountChange(externalResult.getExternalBankVo().getAccountChange() + hanaBankVo.getAccountChange());
				dao.hanaBankDeleteAccount(hanaBankVo, externalResult);
			}else {
				System.out.println("계좌가 존재하지 않습니다.");
			}
		}
		
	}

	/**
	 * 하나은행 계좌 조회
	 * @param hanaBankVo
	 */
	public List<HistoryVo> searchHistory(HanaBankVo hanaBankVo) {

		List<HistoryVo> list = new ArrayList<HistoryVo>();
		
		list = dao.searchHistory(hanaBankVo);
		
		return list;
		
	}
	
	/**
	 * 외부은행 계좌 조회
	 * @param externalInformationVo
	 */
	public List<HistoryVo> searchHistory(ExternalInformationVo externalInformationVo) {

		List<HistoryVo> list = new ArrayList<HistoryVo>();
		
		list = dao.searchHistory(externalInformationVo);
		
		return list;
	}



	

}
