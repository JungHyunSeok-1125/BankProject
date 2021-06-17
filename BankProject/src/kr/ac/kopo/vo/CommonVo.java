package kr.ac.kopo.vo;

public class CommonVo {

	private int resultCode = 0;
	private String resultString = "";
	
	public void reset() {
		this.resultCode = 0;
		this.resultString = null;
	}
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	
	public void setResultCodeZero() {
		setResultCode(0);
	}
	public void setResultCodeOne() {
		setResultCode(1);
	}
	public void setResultCodeTwo() {
		setResultCode(2);
	}
	public void setResultCodeMinus() {
		setResultCode(-1);
	}
	
}
