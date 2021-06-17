package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.vo.BankCustomerVo;

public abstract class BaseUI implements IBoardUI{

	private Scanner sc;
	private static BankCustomerVo bankUser = null;
	
	public BaseUI() {
		sc = new Scanner(System.in);
	}
	
	/**
	 * 문자 입력 받기
	 * @param msg
	 * @return
	 */
	protected String scanStr(String msg) {
		
		System.out.print("\t"+msg);
		String str = sc.nextLine();
		return str;
		
	}

	/**
	 * 숫자 입력 받기
	 * @param msg
	 * @return
	 */
	protected int scanInt(String msg) {
		
		int num = Integer.parseInt(scanStr(msg));
		return num;
	}

	public BankCustomerVo getBankUser() {
		return bankUser;
	}

	public void setBankUser(BankCustomerVo bankUser) {
		this.bankUser = bankUser;
	}
	
	
}
