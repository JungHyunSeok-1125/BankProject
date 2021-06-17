package kr.ac.kopo.service;

public class AdminServiceFactory {

	private static AdminService service = new AdminService();
	
	public static AdminService getInstance() {
		return service;
	}
}
