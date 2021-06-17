package kr.ac.kopo.main;

import kr.ac.kopo.ui.BankUI;

public class BankMain {

	public static void main(String[] args) {

		BankUI ui = new BankUI();
		
		try {
			ui.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}//LINE :: try - catch 종료
	     // 메인까지 예외처리를 해서 메인까지 보내버리겠다.(중간에 예외처리해줘야 한다. 메인에서 끝내면 프로그램이 그대로 종료되서 안좋다)		
	}

}
