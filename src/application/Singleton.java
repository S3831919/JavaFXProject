package application;

//https://www.youtube.com/watch?v=tSZn4wkBIu8

public class Singleton {
	
	private static volatile Singleton instance; 
	
	private String username = "glang01"; 
	private String password = "Password1"; 
	
	private Singleton(String username, String password) {
		this.username = username;
		this.password = password; 
	}
	
	public static Singleton getInstance(String username, String password) {
		Singleton result = instance;
		if (instance == null) {
			
		synchronized (Singleton.class){
			 result = instance;

		if (instance == null ) {
			instance = new Singleton(username, password); 
		}
	}
}		
		
		return instance; 
	}

}
