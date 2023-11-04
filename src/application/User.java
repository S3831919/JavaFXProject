package application;

public class User {
	
	protected String firstName = "";
	protected String userName = ""; 
	protected String password = ""; 
	protected String lastName = "";
	protected String email = ""; 
	
	public User() {
		
		
	}
	
	public User(String firstName, String lastName, String password, String userName) {
		
		this.firstName = firstName;
		this.lastName = lastName; 
		this.userName = userName; 
		this.password = password; 

		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
	
	
}
