package kr.ac.kopo.common;

public class COLUMN {
	

	public static final String YES = "Y";
	
	public static final String NO = "N";

	public static final String DEFAULTSTATE = "NOTING";
	public static final int DEFAULTSTATENUM = -100;
	public static final class BANK {
		
		/**
		 * 은행 이름으로 테이블 이름 바로 접근하기 위함
		 */
		public static final class BANK_TABLE {

			/**
			 * 하나은행
			 */
			public static final String 하나은행 = "HANA_BANK";

			/**
			 * 신한은행
			 */
			public static final String 신한은행 = "SHINHAN_BANK";

			/**
			 * 국민은행
			 */
			public static final String 국민은행 = "KOOKMIN_BANK";
			
			/**
			 * 우리은행
			 */
			public static final String 우리은행 = "WOORI_BANK";
			
			/**
			 * 기업은행
			 */
			public static final String 기업은행 = "IBK_BANK";

		}
		
		/**
		 * 은행 이름으로 은행 번호 바로 접근하기 위함
		 */
		public static final class BANK_TABLE_NO {

			/**
			 * 하나은행
			 */
			public static final int HANA_BANK = 0;

			/**
			 * 신한은행
			 */
			public static final int SHINHAN_BANK = 1;

			/**
			 * 국민은행
			 */
			public static final int KOOKMIN_BANK = 2;
			
			/**
			 * 기업은행
			 */
			public static final int IBK_BANK = 3;

			/**
			 * 우리은행
			 */
			public static final int WOORI_BANK = 4;
			

		}
		
		
	}
	
	public static final class ACCOUNT {
		
		public static final class TASK {

			/**
			 * 입금(ATM)
			 */
			public static final String DEPOSIT_ATM = "DEPOSIT_ATM";
			
			/**
			 * 출금(ATM)
			 */
			public static final String WITHDRAWAL_ATM = "WITHDRAWAL_ATM";

			/**
			 * 계좌 이체(입금)
			 */
			public static final String DEPOSIT = "DEPOSIT";
			
			/**
			 * 계좌 이체(출금)
			 */
			public static final String WITHDRAWAL = "WITHDRAWAL";
			
		}
		
		public static final class TASK_KOR {

			/**
			 * 입금(ATM)
			 */
			public static final String DEPOSIT_ATM_KOR = "입금(ATM)";
			
			/**
			 * 출금(ATM)
			 */
			public static final String WITHDRAWAL_ATM_KOR = "출금(ATM)";

			/**
			 * 계좌 이체(입금)
			 */
			public static final String DEPOSIT_KOR = "계좌 이체(입금)";
			
			/**
			 * 계좌 이체(출금)
			 */
			public static final String WITHDRAWAL_KOR = "계좌 이체(출금)";
			
		}
		

		public static final class PROCEDURE_DEPOSIT_WITHDRAWAL {

			/**
			 * 하나은행 -> 외부은행
			 */
			public static final String HANA_TO_EXTERNAL = "HANA_TO_EXTERNAL";
			
			/**
			 * 외부은행 -> 하나은행
			 */
			public static final String EXTERNAL_TO_HANA = "EXTERNAL_TO_HANA";

			/**
			 * 하나은행 -> 하나은행
			 */
			public static final String HANA_TO_HANA = "HANA_TO_HANA";
			
			/**
			 * 외부은행 -> 외부은행
			 */
			public static final String EXTERNAL_TO_EXTERNAL = "EXTERNAL_TO_EXTERNAL";
			
		}
		
		
		

		
		
	}



}
