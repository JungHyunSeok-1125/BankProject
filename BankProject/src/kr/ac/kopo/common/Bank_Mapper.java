package kr.ac.kopo.common;

import java.util.Scanner;

import kr.ac.kopo.util.InputUtils;

public class Bank_Mapper {

	static Scanner sc = new Scanner(System.in);
	/**
	 * 은행 리스트 조회
	 * @return
	 */
	public static int bankList() {
		System.out.println("\t\t1. 하나은행");
		System.out.println("\t\t2. 신한은행");
		System.out.println("\t\t3. 국민은행");
		System.out.println("\t\t4. 기업은행");
		System.out.println("\t\t5. 우리은행");
		
		int bankNum = InputUtils.getInt("\t은행을 입력해 주세요 : ");
		return bankNum -1;
	}//LINE :: 리스트 메소드 종료
	
	/**
	 * 은행 리스트 조회
	 * @return
	 */
	public static int bankListWithOutHana() {
		System.out.println("\t\t1. 신한은행");
		System.out.println("\t\t2. 국민은행");
		System.out.println("\t\t3. 기업은행");
		System.out.println("\t\t4. 우리은행");
		
		int bankNum = InputUtils.getInt("\t은행을 입력해 주세요 : ");
		return bankNum;
	}//LINE :: 리스트 메소드 종료
	
	
	
}
