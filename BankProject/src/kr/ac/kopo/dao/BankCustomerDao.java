package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.common.COLUMN;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BankCustomerVo;
import kr.ac.kopo.vo.ExternalInformationVo;
import kr.ac.kopo.vo.HanaBankVo;
import kr.ac.kopo.vo.HistoryVo;

public class BankCustomerDao {

	/**
	 * 은행 사용자 로그인
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public BankCustomerVo loginBankCustomer(String id, String password) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		BankCustomerVo bankCustomerResult = null;
		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM BANK_CUSTOMER WHERE CUSTOMER_ID = ? AND CUSTOMER_PWD = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				String customerId = rs.getString("CUSTOMER_ID");
				String name = rs.getString("CUSTOMER_NAME");
				String pwd = rs.getString("CUSTOMER_PWD");
				String inputdate = rs.getString("CUSTOMER_INPUT_DATE");
				String registrationNumber = rs.getString("CUSTOMER_REGISTRATION_NUMBER");

				bankCustomerResult = new BankCustomerVo(customerId, name, pwd, inputdate, registrationNumber);
				bankCustomerResult.setResultCodeOne();
			} else {
				bankCustomerResult.setResultCodeMinus();
			}

		} catch (Exception e) {
			System.out.println("로그인에 실패하였습니다");
		}
		return bankCustomerResult;

	}

	/**
	 * 은행 이름으로 관련된 전체 은행들 조회
	 * 
	 * @param bankCustomUser 사용자
	 * @param bankName       은행이름
	 * @return
	 */
	public List<ExternalInformationVo> searchAllBankName(BankCustomerVo bankCustomUser) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		List<ExternalInformationVo> list = new ArrayList<ExternalInformationVo>();

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT E.NO, E.BANK_PK, E.CUSTOMER_ID AS INTERNAL_CUSTOMER_ID, K.ACCOUNT_NUMBER, K.ACCOUNT_CHANGE, K.NICK_NAME, K.CUSTOMER_ID AS EXTERNAL_CUSTOMER_ID FROM  EXTERNAL_BANK E, SHINHAN_BANK K WHERE E.ACCOUNT_NUMBER = K.ACCOUNT_NUMBER AND E.CUSTOMER_ID = ? "
							+ "UNION ALL SELECT E.NO, E.BANK_PK, E.CUSTOMER_ID AS INTERNAL_CUSTOMER_ID, K.ACCOUNT_NUMBER, K.ACCOUNT_CHANGE, K.NICK_NAME, K.CUSTOMER_ID AS EXTERNAL_CUSTOMER_ID FROM  EXTERNAL_BANK E, WOORI_BANK K WHERE E.ACCOUNT_NUMBER = K.ACCOUNT_NUMBER AND E.CUSTOMER_ID = ? "
							+ "UNION ALL SELECT E.NO, E.BANK_PK, E.CUSTOMER_ID AS INTERNAL_CUSTOMER_ID, K.ACCOUNT_NUMBER, K.ACCOUNT_CHANGE, K.NICK_NAME, K.CUSTOMER_ID AS EXTERNAL_CUSTOMER_ID FROM  EXTERNAL_BANK E, KOOKMIN_BANK K WHERE E.ACCOUNT_NUMBER = K.ACCOUNT_NUMBER AND E.CUSTOMER_ID = ? "
							+ "UNION ALL SELECT E.NO, E.BANK_PK, E.CUSTOMER_ID AS INTERNAL_CUSTOMER_ID, K.ACCOUNT_NUMBER, K.ACCOUNT_CHANGE, K.NICK_NAME, K.CUSTOMER_ID AS EXTERNAL_CUSTOMER_ID FROM  EXTERNAL_BANK E, IBK_BANK K WHERE E.ACCOUNT_NUMBER = K.ACCOUNT_NUMBER AND E.CUSTOMER_ID = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bankCustomUser.getCustomerId());
			pstmt.setString(2, bankCustomUser.getCustomerId());
			pstmt.setString(3, bankCustomUser.getCustomerId());
			pstmt.setString(4, bankCustomUser.getCustomerId());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				int EXT_BANK_PK = rs.getInt("NO");
				int BANK_PK = rs.getInt("BANK_PK");
				String INTERNAL_CUSTOMER_ID = rs.getString("INTERNAL_CUSTOMER_ID");
				String ACCOUNT_NUMBER = rs.getString("ACCOUNT_NUMBER");
				Long ACCOUNT_CHANGE = rs.getLong("ACCOUNT_CHANGE");
				String NICK_NAME = rs.getString("NICK_NAME");
				String EXTERNAL_CUSTOMER_ID = rs.getString("EXTERNAL_CUSTOMER_ID");

				ExternalInformationVo externalBankVo = new ExternalInformationVo(EXT_BANK_PK, BANK_PK,
						INTERNAL_CUSTOMER_ID, ACCOUNT_NUMBER, ACCOUNT_CHANGE, NICK_NAME, EXTERNAL_CUSTOMER_ID);
				list.add(externalBankVo);

			}

		} catch (Exception e) {
		}
		return list;

	}

	/**
	 * 은행 이름으로 관련된 여러 은행들 조회
	 * 
	 * @param bankCustomUser 사용자
	 * @param bankName       은행이름
	 * @return
	 */
	public List<ExternalInformationVo> searchByBankName(BankCustomerVo bankCustomUser, String bankName) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		List<ExternalInformationVo> list = new ArrayList<ExternalInformationVo>();

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "
					+ "		E.NO, E.BANK_PK, E.CUSTOMER_ID AS INTERNAL_CUSTOMER_ID, K.ACCOUNT_NUMBER, K.ACCOUNT_CHANGE, K.NICK_NAME, K.CUSTOMER_ID AS EXTERNAL_CUSTOMER_ID "
					+ "FROM " + "		EXTERNAL_BANK E, " + bankName + " K " + "WHERE "
					+ "		E.ACCOUNT_NUMBER = K.ACCOUNT_NUMBER " + "AND " + "		E.CUSTOMER_ID = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bankCustomUser.getCustomerId());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				int EXT_BANK_PK = rs.getInt("NO");
				int BANK_PK = rs.getInt("BANK_PK");
				String INTERNAL_CUSTOMER_ID = rs.getString("INTERNAL_CUSTOMER_ID");
				String ACCOUNT_NUMBER = rs.getString("ACCOUNT_NUMBER");
				Long ACCOUNT_CHANGE = rs.getLong("ACCOUNT_CHANGE");
				String NICK_NAME = rs.getString("NICK_NAME");
				String EXTERNAL_CUSTOMER_ID = rs.getString("EXTERNAL_CUSTOMER_ID");

				ExternalInformationVo externalBankVo = new ExternalInformationVo(EXT_BANK_PK, BANK_PK,
						INTERNAL_CUSTOMER_ID, ACCOUNT_NUMBER, ACCOUNT_CHANGE, NICK_NAME, EXTERNAL_CUSTOMER_ID);
				list.add(externalBankVo);

			}

		} catch (Exception e) {
		}
		return list;

	}

	/**
	 * 은행 계좌
	 * 
	 * @param bankCustomUser
	 * @param string
	 * @return
	 */
	public List<HanaBankVo> searchHanaBankByCustomer(BankCustomerVo bankCustomUser) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		List<HanaBankVo> list = new ArrayList<HanaBankVo>();

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM HANA_BANK WHERE CUSTOMER_ID = ? ORDER BY CUSTOMER_ID DESC");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bankCustomUser.getCustomerId());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				int HANA_ACCOUNT_PK = rs.getInt("HANA_ACCOUNT_PK");
				String HANA_ACCOUNT_NUMBER = rs.getString("HANA_ACCOUNT_NUMBER");
				int HANA_ACCOUNT_CHANGE = rs.getInt("HANA_ACCOUNT_CHANGE");
				String NICK_NAME = rs.getString("NICK_NAME");
				String CUSTOMER_ID = rs.getString("CUSTOMER_ID");
				String INPUT_DATE = rs.getString("INPUT_DATE");

				HanaBankVo hanaBankVo = new HanaBankVo(HANA_ACCOUNT_PK, HANA_ACCOUNT_NUMBER, HANA_ACCOUNT_CHANGE,
						NICK_NAME, CUSTOMER_ID, INPUT_DATE);
				list.add(hanaBankVo);

			}

		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * 최신의 결과 반환
	 * 
	 * @param bankCustomUser
	 * @param string
	 * @return
	 */
	public HanaBankVo searchRecentHanaBankReg(BankCustomerVo bankCustomUser) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		HanaBankVo hanaUser = new HanaBankVo();

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "
					+ "		HANA_ACCOUNT_PK,HANA_ACCOUNT_NUMBER,HANA_ACCOUNT_CHANGE,NICK_NAME,CUSTOMER_ID, TO_CHAR(INPUT_DATE,'yyyy-mm-dd') as INPUT_DATE "
					+ "FROM " + "		(SELECT * FROM HANA_BANK WHERE CUSTOMER_ID = ? " + "ORDER BY "
					+ "		HANA_ACCOUNT_PK DESC) " + "WHERE " + "		ROWNUM = 1");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bankCustomUser.getCustomerId());

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				int HANA_ACCOUNT_PK = rs.getInt("HANA_ACCOUNT_PK");
				String HANA_ACCOUNT_NUMBER = rs.getString("HANA_ACCOUNT_NUMBER");
				int HANA_ACCOUNT_CHANGE = rs.getInt("HANA_ACCOUNT_CHANGE");
				String NICK_NAME = rs.getString("NICK_NAME");
				String CUSTOMER_ID = rs.getString("CUSTOMER_ID");
				String INPUT_DATE = rs.getString("INPUT_DATE");
				hanaUser = new HanaBankVo(HANA_ACCOUNT_PK, HANA_ACCOUNT_NUMBER, HANA_ACCOUNT_CHANGE, NICK_NAME,
						CUSTOMER_ID, INPUT_DATE);

			} else {
				hanaUser.setResultCodeMinus();
			}

		} catch (Exception e) {
		}
		return hanaUser;
	}

	/**
	 * 사용자 추가
	 */
	public void addHanaBankCustomer(HanaBankVo hanaBankVo) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO " + "		HANA_BANK "
					+ "		(HANA_ACCOUNT_PK, HANA_ACCOUNT_NUMBER,HANA_ACCOUNT_CHANGE, NICK_NAME,CUSTOMER_ID, INPUT_DATE) "
					+ "VALUES"
					+ "		(HANA_ACCOUNT_SEQUENCE.NEXTVAL,?,?,?,?,TO_DATE(SYSDATE,'YY/MM/DD HH24:MI:SS'))");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, hanaBankVo.getAccountNumber());
			pstmt.setInt(2, hanaBankVo.getAccountChange());
			pstmt.setString(3, hanaBankVo.getNickName());
			pstmt.setString(4, hanaBankVo.getCustomerId());

			pstmt.executeUpdate();

		} catch (Exception e) {

		}

	}

	/**
	 * 잔액 변경
	 * 
	 * @param hanaBankResult
	 * @throws Exception
	 */
	public void updateHanaBankChange(HanaBankVo hanaBankResult, int money, int mode) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String task = null;
		try {
			
			
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE HANA_BANK SET HANA_ACCOUNT_CHANGE = ? , " + "NICK_NAME = ? WHERE HANA_ACCOUNT_PK = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, hanaBankResult.getAccountChange());
			pstmt.setString(2, hanaBankResult.getNickName());
			pstmt.setInt(3, hanaBankResult.getAccountPk());

			pstmt.executeUpdate();

			if(mode == 1) task = COLUMN.ACCOUNT.TASK.DEPOSIT_ATM;
			else if(mode == -1) task = COLUMN.ACCOUNT.TASK.WITHDRAWAL_ATM;
			
			if(mode != 0) {
				addHanaHistory(conn, hanaBankResult, task, COLUMN.DEFAULTSTATE, COLUMN.DEFAULTSTATENUM, hanaBankResult.getAccountChange(), money);
			}
			
			System.out.println("성공하셨습니다");
		} catch (Exception e) {
			conn.rollback();
			System.out.println("실패하셨습니다.");
		}finally {
			conn.setAutoCommit(true);
		}
	}

	/**
	 * 잔액 변경
	 * @param extBankVo 외부은행 정보
	 * @param money 변동금액
	 * @param mode 업무
	 * @throws SQLException
	 */
	public void updateExternalBankChange(ExternalInformationVo extBankVo, int money, int mode) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String task = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE " + extBankVo.getEngBankName() + " SET ACCOUNT_CHANGE = ?" + " WHERE ACCOUNT_NUMBER = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setFloat(1, extBankVo.getExternalBankVo().getAccountChange());
			pstmt.setString(2, extBankVo.getExternalBankVo().getAccountNumber());

			pstmt.executeUpdate();
			
			if(mode == 1) task = COLUMN.ACCOUNT.TASK.DEPOSIT_ATM;
			else if(mode == -1) task = COLUMN.ACCOUNT.TASK.WITHDRAWAL_ATM;
			
			if(mode != 0) {
				addExternalBankHistory(conn, task, extBankVo.getAccountNumber(), extBankVo.getBankNum(),COLUMN.DEFAULTSTATE, COLUMN.DEFAULTSTATENUM, extBankVo.getExternalBankVo().getAccountChange(), money);				
			}
			
			System.out.println("성공하셨습니다.");
		} catch (Exception e) {

			conn.rollback();
			System.out.println("실패하셨습니다.");
		}finally {
			conn.setAutoCommit(true);
		}
	}

	/**
	 * 하나은행 사용자 정보 가져오기
	 * 
	 * @param receiverBank    은행 번호
	 * @param receiverAccount
	 * @return
	 */
	public HanaBankVo obtainHanaBank(String receiverAccount) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		HanaBankVo hanabank = null;
		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM HANA_BANK WHERE HANA_ACCOUNT_NUMBER = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, receiverAccount);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				int HANA_ACCOUNT_PK = rs.getInt("HANA_ACCOUNT_PK");
				String HANA_ACCOUNT_NUMBER = rs.getString("HANA_ACCOUNT_NUMBER");
				int HANA_ACCOUNT_CHANGE = rs.getInt("HANA_ACCOUNT_CHANGE");
				String NICK_NAME = rs.getString("NICK_NAME");
				String CUSTOMER_ID = rs.getString("CUSTOMER_ID");
				String INPUT_DATE = rs.getString("INPUT_DATE");

				hanabank = new HanaBankVo(HANA_ACCOUNT_PK, HANA_ACCOUNT_NUMBER, HANA_ACCOUNT_CHANGE, NICK_NAME,
						CUSTOMER_ID, INPUT_DATE);
				hanabank.setResultCodeOne();

			} else {
				hanabank.setResultCodeMinus();
			}
		} catch (Exception e) {
		}
		return hanabank;
	}

	/**
	 * 외부은행 사용자 정보 가져오기
	 * 
	 * @param receiverBank
	 * @param receiverAccount
	 * @return
	 */
	public ExternalInformationVo obtainExternalBank(String receiverBank, String receiverAccount) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ExternalInformationVo externalBankVo = new ExternalInformationVo();

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT E.NO, E.BANK_PK, E.CUSTOMER_ID AS INTERNAL_CUSTOMER_ID, K.ACCOUNT_NUMBER, K.ACCOUNT_CHANGE, K.NICK_NAME, K.CUSTOMER_ID AS EXTERNAL_CUSTOMER_ID"
							+ " FROM " + receiverBank
							+ " K, EXTERNAL_BANK E WHERE K.ACCOUNT_NUMBER = E.ACCOUNT_NUMBER AND "
							+ "E.ACCOUNT_NUMBER = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, receiverAccount);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				int EXT_BANK_PK = rs.getInt("NO");
				int BANK_PK = rs.getInt("BANK_PK");
				String INTERNAL_CUSTOMER_ID = rs.getString("INTERNAL_CUSTOMER_ID");
				String ACCOUNT_NUMBER = rs.getString("ACCOUNT_NUMBER");
				Long ACCOUNT_CHANGE = rs.getLong("ACCOUNT_CHANGE");
				String NICK_NAME = rs.getString("NICK_NAME");
				String EXTERNAL_CUSTOMER_ID = rs.getString("EXTERNAL_CUSTOMER_ID");

				externalBankVo = new ExternalInformationVo(EXT_BANK_PK, BANK_PK, INTERNAL_CUSTOMER_ID, ACCOUNT_NUMBER,
						ACCOUNT_CHANGE, NICK_NAME, EXTERNAL_CUSTOMER_ID);
				externalBankVo.setResultCodeOne();

			} else {
				externalBankVo.setResultCodeMinus();
			}
		} catch (Exception e) {
		}
		return externalBankVo;
	}

	/**
	 * 계좌 이체
	 * 
	 * @param hanaBankVo 하나은행1
	 * @param hanaResult 하나은행2
	 * @throws SQLException
	 */
	public void hanaBankTransfer(HanaBankVo hanaSenderResult, HanaBankVo hanaReceiveResult, int money) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String task = null;
		
		try {
			conn = new ConnectionFactory().getConnection();

			conn.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE HANA_BANK SET HANA_ACCOUNT_CHANGE = ?  " + "WHERE HANA_ACCOUNT_PK = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, hanaSenderResult.getAccountChange());
			pstmt.setInt(2, hanaSenderResult.getAccountPk());

			pstmt.executeUpdate();

			sql = new StringBuilder();

			sql.append("UPDATE HANA_BANK SET HANA_ACCOUNT_CHANGE = ?  " + "WHERE HANA_ACCOUNT_PK = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, hanaReceiveResult.getAccountChange());
			pstmt.setInt(2, hanaReceiveResult.getAccountPk());

			pstmt.executeUpdate();
			
			addHanaHistory(conn, hanaSenderResult, COLUMN.ACCOUNT.TASK.WITHDRAWAL, hanaReceiveResult.getAccountNumber(), COLUMN.BANK.BANK_TABLE_NO.HANA_BANK, hanaSenderResult.getAccountChange(), money);//LINE :: 돈 빠지는 기록
			addHanaHistory(conn, hanaReceiveResult, COLUMN.ACCOUNT.TASK.DEPOSIT, hanaSenderResult.getAccountNumber(), COLUMN.BANK.BANK_TABLE_NO.HANA_BANK, hanaSenderResult.getAccountChange(), money);//LINE :: 돈 빠지는 기록
			
			conn.commit();
			System.out.println("성공하셨습니다");

		} catch (Exception e) {
			conn.rollback();
			System.out.println("실패하셨습니다.");
		} finally {
			conn.setAutoCommit(true);
		}
	}

	/**
	 * 계좌이체 (하나은행 <-> 외부은행)
	 * 
	 * @param hanaBankVo
	 * @param externalResult
	 * @throws SQLException
	 */
	public void hanaBankTransfer(HanaBankVo hanaBankVo, ExternalInformationVo externalResult, int money, String procedure) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String hana_task = null;
		String ext_task = null;
		int hana_mul;
		int ext_mul;
		try {
			conn = new ConnectionFactory().getConnection();

			conn.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE HANA_BANK SET HANA_ACCOUNT_CHANGE = ?  " + "WHERE HANA_ACCOUNT_PK = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, hanaBankVo.getAccountChange());
			pstmt.setInt(2, hanaBankVo.getAccountPk());

			pstmt.executeUpdate();

			sql = new StringBuilder();

			sql.append("UPDATE " + externalResult.getEngBankName() + " SET ACCOUNT_CHANGE = ?"
					+ " WHERE ACCOUNT_NUMBER = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setFloat(1, externalResult.getExternalBankVo().getAccountChange());
			pstmt.setString(2, externalResult.getExternalBankVo().getAccountNumber());

			pstmt.executeUpdate();

			if(procedure.equals(COLUMN.ACCOUNT.PROCEDURE_DEPOSIT_WITHDRAWAL.HANA_TO_EXTERNAL)) {
				hana_task = COLUMN.ACCOUNT.TASK.DEPOSIT;
				ext_task = COLUMN.ACCOUNT.TASK.WITHDRAWAL;
				hana_mul = -1;
				ext_mul = 1;
			}else {
				hana_task = COLUMN.ACCOUNT.TASK.WITHDRAWAL;
				ext_task = COLUMN.ACCOUNT.TASK.DEPOSIT;
				hana_mul = 1;
				ext_mul = -1;
			}
			addHanaHistory(conn, hanaBankVo, hana_task, hanaBankVo.getAccountNumber(), COLUMN.BANK.BANK_TABLE_NO.HANA_BANK, hanaBankVo.getAccountChange(), money*hana_mul);//LINE :: 돈 빠지는 기록
			addExternalBankHistory(conn, ext_task, externalResult.getAccountNumber(), externalResult.getBankNum(),hanaBankVo.getAccountNumber(), COLUMN.BANK.BANK_TABLE_NO.HANA_BANK, externalResult.getExternalBankVo().getAccountChange(), money*ext_mul);//LINE :: 돈 들어오는 기록				
			
			conn.commit();
			System.out.println("성공하셨습니다.");
		} catch (Exception e) {
			conn.rollback();
			System.out.println("실패하셨습니다.");
		} finally {
			conn.setAutoCommit(true);
		}
	}

	/**
	 * 계좌이체 (외부은행 <-> 외부은행)
	 * 
	 * @param externalInformationVo
	 * @param externalResult
	 * @throws SQLException
	 */
	public void hanaBankTransfer(ExternalInformationVo externalSender, ExternalInformationVo externalReceiver, int money)
			throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE " + externalSender.getEngBankName() + " SET ACCOUNT_CHANGE = ?"
					+ " WHERE ACCOUNT_NUMBER = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setFloat(1, externalSender.getExternalBankVo().getAccountChange());
			pstmt.setString(2, externalSender.getExternalBankVo().getAccountNumber());

			pstmt.executeUpdate();

			sql = new StringBuilder();

			sql.append("UPDATE " + externalReceiver.getEngBankName() + " SET ACCOUNT_CHANGE = ?"
					+ " WHERE ACCOUNT_NUMBER = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setFloat(1, externalReceiver.getExternalBankVo().getAccountChange());
			pstmt.setString(2, externalReceiver.getExternalBankVo().getAccountNumber());

			pstmt.executeUpdate();

			addExternalBankHistory(conn, COLUMN.ACCOUNT.TASK.WITHDRAWAL,externalReceiver.getAccountNumber(), externalReceiver.getBankNum(), externalSender.getAccountNumber(), externalSender.getBankNum(), externalSender.getExternalBankVo().getAccountChange(), money*-1);	
			addExternalBankHistory(conn, COLUMN.ACCOUNT.TASK.DEPOSIT,externalSender.getAccountNumber(), externalSender.getBankNum(), externalReceiver.getAccountNumber(), externalReceiver.getBankNum(), externalReceiver.getExternalBankVo().getAccountChange(), money);
			
			conn.commit();
			System.out.println("성공하셨습니다.");
		} catch (Exception e) {
			conn.rollback();
			System.out.println("실패하셨습니다.");
		} finally {
			conn.setAutoCommit(true);
		}
	}

	/**
	 * 계좌 삭제 (하나은행 이체)
	 * 
	 * @param hanaBankVo
	 * @param hanaResult
	 * @throws SQLException
	 */
	public void hanaBankDeleteAccount(HanaBankVo hanaBankVo, HanaBankVo hanaResult) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM HANA_BANK" + " WHERE HANA_ACCOUNT_PK = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, hanaBankVo.getAccountPk());

			pstmt.executeUpdate();

			sql = new StringBuilder();

			sql.append("UPDATE HANA_BANK SET HANA_ACCOUNT_CHANGE = ?" + " WHERE HANA_ACCOUNT_PK = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setFloat(1, hanaResult.getAccountChange());
			pstmt.setInt(2, hanaResult.getAccountPk());

			pstmt.executeUpdate();

			addHanaHistory(conn, hanaResult, COLUMN.ACCOUNT.TASK.DEPOSIT, hanaBankVo.getAccountNumber(), COLUMN.BANK.BANK_TABLE_NO.HANA_BANK, hanaResult.getAccountChange(), hanaBankVo.getAccountChange());
			
			conn.commit();
			System.out.println("성공하셨습니다.");
		} catch (Exception e) {
			conn.rollback();
			System.out.println("실패하셨습니다.");
		} finally {
			conn.setAutoCommit(true);
		}
	}

	/**
	 * 계좌 삭제 (외부 은행 이체)
	 * 
	 * @param hanaBankVo
	 * @param externalResult
	 * @throws SQLException
	 */
	public void hanaBankDeleteAccount(HanaBankVo hanaBankVo, ExternalInformationVo externalResult) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			conn.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM HANA_BANK " + "WHERE HANA_ACCOUNT_PK = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, hanaBankVo.getAccountPk());

			pstmt.executeUpdate();

			sql = new StringBuilder();

			sql.append("UPDATE " + externalResult.getEngBankName() + " SET ACCOUNT_CHANGE = ?"
					+ " WHERE ACCOUNT_NUMBER = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setFloat(1, externalResult.getExternalBankVo().getAccountChange());
			pstmt.setString(2, externalResult.getExternalBankVo().getAccountNumber());

			pstmt.executeUpdate();

			addExternalBankHistory(conn, COLUMN.ACCOUNT.TASK.DEPOSIT,externalResult.getAccountNumber(), externalResult.getBankNum(), hanaBankVo.getAccountNumber(), COLUMN.BANK.BANK_TABLE_NO.HANA_BANK, externalResult.getExternalBankVo().getAccountChange(), hanaBankVo.getAccountChange());//LINE :: 돈 나가는 기록	
			
			
			conn.commit();
			System.out.println("성공하셨습니다.");
		} catch (Exception e) {
			conn.rollback();
			System.out.println("실패하셨습니다.");
		} finally {
			conn.setAutoCommit(true);
		}

	}

	/**
	 * 계좌 삭제 (하나은행만 삭제)
	 * 
	 * @param hanaBankVo
	 */
	public void hanaBankDeleteAccount(HanaBankVo hanaBankVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM HANA_BANK" + " WHERE HANA_ACCOUNT_PK = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, hanaBankVo.getAccountPk());

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("실패하셨습니다.");
		}
	}

	/**
	 * 하나은행 거래내역 추가
	 * 
	 * @param conn            커넥션 객체
	 * @param hanabankUser    사용자
	 * @param task            업무
	 * @param receiverAccount 받는사람 계좌 번호
	 * @param hanaBank     받는 은행
	 * @param beforeMoney     이전 금액
	 * @param changeMoney     변동 금액
	 * @throws SQLException
	 */
	public void addHanaHistory(Connection conn, HanaBankVo hanabankUser, String task, String receiverAccount, int hanaBank, int beforeMoney, int changeMoney) throws SQLException {

		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO HANA_HISTORY VALUES(HANA_HISTORY_SEQUENCE.NEXTVAL, ? , SYSDATE , ? ,?,?,?,?)");

		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, task);
		pstmt.setString(2, receiverAccount);
		pstmt.setInt(3, hanabankUser.getAccountPk());
		pstmt.setInt(4, hanaBank);
		pstmt.setInt(5, beforeMoney);
		pstmt.setInt(6, changeMoney);

		pstmt.executeUpdate();


	}

	/**
	 * 외부은행 계좌 추가
	 * 
	 * @param conn            커넥션 객체
	 * @param task            업무
	 * @param receiverAccount 받는 계좌
	 * @param receiveBank     받는 은행
	 * @param senderAccount   보내는 사람 계좌
	 * @param senderBank      보내는 사람은행
	 * @param beforeMoney     이전 금액
	 * @param changeMoney     변동 금액
	 * @throws SQLException
	 */
	public void addExternalBankHistory(Connection conn, String task, String receiverAccount, int receiveBank, String senderAccount, int senderBank, Long beforeMoney, int changeMoney) throws SQLException {

		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO HISTORY VALUES(EXTERNAL_BANK_HISTORY_SEQUENCE.NEXTVAL,?,SYSDATE,?,?,?,?,?,?)");

		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, task);
		pstmt.setString(2, receiverAccount);
		pstmt.setInt(3, receiveBank);
		pstmt.setString(4, senderAccount);
		pstmt.setInt(5, senderBank);
		pstmt.setLong(6, beforeMoney);
		pstmt.setInt(7, changeMoney);

		pstmt.executeUpdate();


	}

	public List<HistoryVo> searchHistory(HanaBankVo hanaBankVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<HistoryVo> list = new ArrayList<HistoryVo>();

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "
					+ "		TASK, TO_CHAR(HISTORY_DATE,'yyyymmddhh24miss') AS HISTORY_DATE, SENDER_ACCOUNT, SENDER_BANK, BEFORE_CHANGE, MOD_CHANGE "
					+ "FROM "
					+ "		HANA_HISTORY "
					+ "WHERE "
					+ "		HANA_ACCOUNT_PK = ? ORDER BY HISTORY_PK DESC");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, hanaBankVo.getAccountPk());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String TASK = rs.getString("TASK");
				String HISTORY_DATE = rs.getString("HISTORY_DATE");
				String SENDER_ACCOUNT = rs.getString("SENDER_ACCOUNT");
				int SENDER_BANK = rs.getInt("SENDER_BANK");
				int BEFORE_CHANGE = rs.getInt("BEFORE_CHANGE");
				int MOD_CHANGE = rs.getInt("MOD_CHANGE");

				HistoryVo hanaBankHistoryVo = new HistoryVo(TASK, HISTORY_DATE, SENDER_ACCOUNT,	SENDER_BANK, BEFORE_CHANGE, MOD_CHANGE);
				list.add(hanaBankHistoryVo);
			}

		} catch (Exception e) {
		}
		return list;
	}

	public List<HistoryVo> searchHistory(ExternalInformationVo externalInformationVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<HistoryVo> list = new ArrayList<HistoryVo>();

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "
					+ "		TASK, TO_CHAR(HISTORY_DATE,'yyyymmddhh24miss') AS HISTORY_DATE, "
					+ "		RECEIVER_ACCOUNT, RECEIVER_BANK, BEFORE_CHANGE, MOD_CHANGE "
					+ "FROM "
					+ "		HISTORY "
					+ "WHERE "
					+ "		SENDER_ACCOUNT = ? AND SENDER_BANK = ? "
					+ "ORDER BY "
					+ "		HISTORY_PK DESC");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, externalInformationVo.getExternalBankVo().getAccountNumber());
			pstmt.setInt(2, externalInformationVo.getBankNum());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String TASK = rs.getString("TASK");
				String HISTORY_DATE = rs.getString("HISTORY_DATE");
				String RECEIVER_ACCOUNT = rs.getString("RECEIVER_ACCOUNT");
				int RECEIVER_BANK = rs.getInt("RECEIVER_BANK");
				int BEFORE_CHANGE = rs.getInt("BEFORE_CHANGE");
				int MOD_CHANGE = rs.getInt("MOD_CHANGE");

				HistoryVo hanaBankHistoryVo = new HistoryVo(TASK, HISTORY_DATE, RECEIVER_ACCOUNT, RECEIVER_BANK, BEFORE_CHANGE, MOD_CHANGE);
				list.add(hanaBankHistoryVo);
			}

		} catch (Exception e) {
		}
		return list;
	}

}