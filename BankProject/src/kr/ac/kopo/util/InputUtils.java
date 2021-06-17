package kr.ac.kopo.util;

import java.util.Random;
import java.util.Scanner;

public class InputUtils {
	
	static Scanner sc = new Scanner(System.in);

	/**
	 * 랜덤 숫자 생성기
	 * @param len 길이 
	 * @param dupCd 중복 허용 여부 (1: 중복허용, 2:중복제거)
	 * @return
	 */
	 public static String numberGen(int len, int dupCd ) {
	        
        Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수
        
        for(int i=0;i<len;i++) {
            
            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));
            
            if(dupCd==1) {
                //중복 허용시 numStr에 append
                numStr += ran;
            }else if(dupCd==2) {
                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
                if(!numStr.contains(ran)) {
                    //중복된 값이 없으면 numStr에 append
                    numStr += ran;
                }else {
                    //생성된 난수가 중복되면 루틴을 다시 실행한다
                    i-=1;
                }
            }
        }
        return numStr;
    }
	 
	 /**
		 * 숫자 입력받기
		 * 
		 * @param str 질문
		 * @return 입력받은 숫자
		 */
		public static int getInt(String str) {
			System.out.print(str);
			int input = sc.nextInt();
			return input;
		}
		
		public static int getInt() {
			int input = sc.nextInt();
			sc.nextLine();
			return input;
		}
		

		/**
		 * 문자 입력 받기
		 * 
		 * @param str 질문
		 * @return 입력받은 문자
		 */
		public static char getChar(String str) {
			System.out.print(str);
			char input = sc.nextLine().charAt(0);
			return input;
		}
		
		public static char getChar() {
			char input = sc.nextLine().charAt(0);
			sc.nextLine();
			return input;
		}
		

		/**
		 * 문자열 입력받기
		 * 
		 * @param str 질문
		 * @return 입력받은 문자열
		 */
		public static String getString(String str) {
			System.out.print(str);
			String input = sc.nextLine();
			return input;
		}
		
		public static String getString() {
			String input = sc.nextLine();
			return input;
		}
		

		/**
		 * double 입력받기
		 * 
		 * @param str 질문
		 * @return
		 */
		public static Double getDouble(String str) {
			System.out.print(str);
			Double input = sc.nextDouble();
			return input;
		}
		
		public static Double getDouble() {
			Double input = sc.nextDouble();
			sc.nextLine();
			return input;
		}
		

		/**
		 * 1 ~ 입력받은 수까지 랜덤숫자 출력
		 * 
		 * @return 1 ~ num 사이의 랜덤숫자
		 */
		public static int getRandomNum(int num) {

			Random r = new Random();
			int randNum = r.nextInt(num) + 1;
			return randNum;
		}
		
}
