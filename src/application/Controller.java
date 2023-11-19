package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Scanner;

import Code.DatabaseConnection;
import Code.MediaAnalyser;
import Code.Post;
import Code.Reply;
import Code.User;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root; 
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private Button test;
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField passwordChangeuserName;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField lastName;
	
	@FXML
	private TextField password;
	
	@FXML
	private TextField changePassword;
	
	@FXML
	private TextField changePasswordConfirm;
	
	@FXML
	private Text logInStatus;
	
	@FXML
	private Text submitStatus;
	
	@FXML
	public Text welcome1;
	
	@FXML
	public Text welcomeHome;
	
	@FXML
	private TextField PostID;
	
	@FXML
	private TextField PostIDSearch;
	
	@FXML
	private TextField Content;
	
	@FXML
	private TextField Author;
	
	@FXML
	private TextField Likes;
	
	@FXML
	private TextField Shares;
	
	@FXML
	private TextField TimeOfPost;
	
	//CSV Column values 
	int ID = 0;
	int content = 1;
	int author = 2; 
	int likes = 3; 
	int shares = 4; 
	int time = 5; 
	int type = 6; 	
	
	String userEntry = ""; 
	
	Boolean validation = false; 
	int userSelection = 0; 
	List<String[]> list = new ArrayList<>();
	
	Boolean exceptionCheck = false;
	
	LocalDateTime timeStamp = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	String formattedTimestamp = timeStamp.format(formatter);
	
	//Scanner to take user input
	Scanner input = new Scanner(System.in);
	
	//creates the array list that contains the Posts
	ArrayList<Post> postList = new ArrayList<Post>();
	
	//creates the array list that contains the Reply
	ArrayList<Reply> replyList = new ArrayList<Reply>();
	
	//creates the array list that contains the Reply
	ArrayList<User> userList = new ArrayList<User>();
	
	//create object for menu methods		
	MediaAnalyser media = new MediaAnalyser(); 
	
	//create a new post 
	Post post = new Post(0, "", "", 0, 0, "",0);

	//create a new reply 
	Reply reply = new Reply(0, "", "", 0, 0, "",0);

	//create a user
	User user = new User("first","last","pass","user");
	  
	public void print(ArrayList postList) {
		
		textArea.setText(postList.toString());
	
	}
	
	public void printPosts() {
		
		final String TABLE_NAME = "Post";

		try (Connection con = DatabaseConnection.getConnection()) {
		    String query = "SELECT * FROM " + TABLE_NAME;
		    try (PreparedStatement stmt = con.prepareStatement(query)) {
		        ResultSet resultSet = stmt.executeQuery();

		        StringBuilder resultText = new StringBuilder();

		        while (resultSet.next()) {
		            // Retrieve data from the result set
		            String PostIDSQL = resultSet.getString("PostID");
		            String ContentSQL = resultSet.getString("Content");
		            String AuthorSQL = resultSet.getString("Author");
		            int LikesSQL = resultSet.getInt("Likes");
		            int SharesSQL = resultSet.getInt("Shares");
		            String DateTimeSQL = resultSet.getString("DateTime");

		            // Concatenate the data and add a newline
		            resultText.append("PostID: ").append(PostIDSQL).append(" ")
		            .append("Content: ").append(ContentSQL).append(" ")
		            .append("Author: ").append(AuthorSQL).append(" ")
		            .append("Likes: ").append(LikesSQL).append(" ")
		            .append("Shares: ").append(SharesSQL).append(" ")
		            .append("Date: ").append(DateTimeSQL).append("\n");
		        }

		        // Set the final text to the TextArea
		        textArea.setText(resultText.toString());
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
	}
	
	public void addAPost() {
		
//		//create new Post and Reply object 
//		post = new Post(0, "", "", 0, 0, "",0);
//		reply = new Reply(0, "", "", 0, 0, "",0);
//		
//		postList.add(post);
//		replyList.add(reply);
//		
		post.setPostID(Integer.parseInt(PostID.getText()));
		post.setPostContent(Content.getText());
		post.setPostAuthor(Author.getText());
		post.setPostLikes(Integer.parseInt(Likes.getText()));
		post.setPostShares(Integer.parseInt(Shares.getText()));
		post.setTimeStamp(TimeOfPost.getText());
		
		 final String TABLE_NAME = "Post";

		 try (Connection con = DatabaseConnection.getConnection();
		      Statement stmt = con.createStatement();) {
		     String query = "INSERT INTO " + TABLE_NAME +
		                    " VALUES ('" + post.getPostID() + "', '" + post.getPostContent() +
		                    "', '" + post.getPostAuthor() + "', '" + post.getPostLikes() + 	
		     				"', '" + post.getPostShares() + "', '" + post.getTimeStamp() + "')";	

				int result = stmt.executeUpdate(query);

				if (result == 1) {
					System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
					System.out.println(result + " row(s) affected");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}	

	}

	 public void createUser(){
		
		 	 user.setFirstName(firstName.getText());
		 	 user.setLastName(lastName.getText());
			 user.setUserName(userName.getText());
			 user.setPassword(password.getText());
			 userList.add(user);	
			 
			 final String TABLE_NAME = "User";

			 try (Connection con = DatabaseConnection.getConnection();
			      Statement stmt = con.createStatement();) {
			     String query = "INSERT INTO " + TABLE_NAME +
			                    " VALUES ('" + user.getUserName() + "', '" + user.getFirstName() +
			                    "', '" + user.getLastName() + "', '" + user.getPassword() + "')";					

					int result = stmt.executeUpdate(query);

					if (result == 1) {
						System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
						System.out.println(result + " row(s) affected");
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}		 
			 			
		 }

	 
	 public void updateUser(){
		 
		 firstName.setText(firstName.getText());
		 			
	 }
	 
	 public void changeAPassword(){
		 
		 passwordChangeuserName.setText(userName.getText());
		 
		 String a = changePassword.getText();
		 String b = changePasswordConfirm.getText(); 
		 
		 if(a == b) {
			 user.setPassword(a);
		 }
	 }
	 
	 public void switchToLogIn(ActionEvent event) throws IOException {  
				 
		 //adds user to the system
		 try {
		 createUser();
		 }
		 catch (NullPointerException e){
			 
			 System.out.print(e);
			 
		 }
		 
		 Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
		  
		 }

	 public void switchToSignUp(ActionEvent event) throws IOException {
	
		  Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		  
		 }
	 
	 public void switchToNormalWelcome(ActionEvent event) throws IOException {	
		 
		        final String TABLE_NAME = "User";
		        
			  	boolean flag = false;
			  	
			  	//check the user exists in the database and verifies credentials 

		        try (Connection con = DatabaseConnection.getConnection()) {
		            // Assuming you have a User class with appropriate fields
		            String query = "SELECT * FROM " + TABLE_NAME;
		            try (PreparedStatement stmt = con.prepareStatement(query)) {
		                ResultSet resultSet = stmt.executeQuery();

		                while (resultSet.next()) {
		                    // Retrieve data from the result set
		                    String userNameSQL = resultSet.getString("userName");
		                    String firstNameSQL = resultSet.getString("firstName");
		                    String lastNameSQL = resultSet.getString("lastName");
		                    String passwordSQL = resultSet.getString("password");
		               
		        		  	
		        		    if (userName.getText().equals(userNameSQL) && password.getText().equals(passwordSQL)) {
		        		        logInStatus.setText("Welcome");
		        		        flag = true;
		        		    } else {
		        		        logInStatus.setText("Incorrect Username/Password. Please try again");
		        		    }

		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }	        
		        

		    if (flag) {
		        Parent root = FXMLLoader.load(getClass().getResource("NormalWelcome.fxml"));
		        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		    }
		}
	 
	 public void switchToNormalWelcomeHome(ActionEvent event) throws IOException {
		 
		 try {
		 welcome1.setText(firstName.getText());
		 welcomeHome.setText(firstName.getText());
		 }
		 catch (NullPointerException e){
			 
		 }
		 
		 
     Parent root = FXMLLoader.load(getClass().getResource("NormalWelcomeHome.fxml"));
     stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
	 }


	 
	 public void switchToNormalAddAPost(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalAddAPost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalPostSubmitted(ActionEvent event) throws IOException {
		 Boolean flag = false; 
		 
		 try {
		 addAPost(); 
		 flag = true; 
		 }
		 catch (NumberFormatException e) {
			 System.out.print(e);
		 }
		 
		 if(flag) {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalPostSubmitted.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
		 else {
			 submitStatus.setText("Incorrect number format");
		 }
	 }
	 
	 public void switchToChangePassword(ActionEvent event) throws IOException {
		 
		 try {
		 changeAPassword();
		 }
		 catch (NullPointerException e) {
			 
			 System.out.print("Bruh, your username field is empty");
		 }
		 
		  Parent root = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToPasswordChanged(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("PasswordChanged.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalDisplayMyPosts(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalDisplayMyPosts.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalRetrieveAPost(ActionEvent event) throws IOException {
		 
		  Parent root = FXMLLoader.load(getClass().getResource("NormalRetrieveAPost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalPost(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalPost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalRetrieveReplies(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalRetrieveReplies.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalReplies(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalReplies.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalRemovePost(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalRemovePost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalPostRemoved(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalPostRemoved.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalDisplayAllPosts(ActionEvent event) throws IOException {	

			 try {
				 printPosts();
			 }
			 catch (NullPointerException e) {
				 System.out.print(e);
			 }	
		 	
		  Parent root = FXMLLoader.load(getClass().getResource("NormalDisplayAllPosts.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene); 
		  stage.show();
 
		 }
	 
	 public void switchToNormalEditMyProfile(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalEditMyProfile.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalChangeName(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalChangeName.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalNameChanged(ActionEvent event) throws IOException {
		 
		 try {
		 updateUser();
		 }
		 catch (NullPointerException e){
			 
			 System.out.print(e);
			 
		 }
		 
		 Parent root = FXMLLoader.load(getClass().getResource("NormalNameChanged.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalChangeUsername(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalChangeUsername.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToUpgradeToVIP(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("UpgradeToVIP.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToGOVIP(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("UpgradeToVIP.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPConfirmed(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPConfirmed.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPWelcome(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPWelcome.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPPostSubmitted(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPPostSubmitted.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPDisplayMyPosts(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPDisplayMyPosts.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPPost(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPPost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPAddAPost(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPAddAPost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPRetrieveAPost(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPRetrieveAPost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPRetrieveReplies(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPRetrieveReplies.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPReplies(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPReplies.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPRemovePost(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPRemovePost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPPostRemoved(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPPostRemoved.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPDisplayAllPosts(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPDisplayAllPosts.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPEditMyProfile(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPEditMyProfile.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPNameChanged(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPNameChanged.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPChangeUsername(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPChangeUsername.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPChangeName(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPChangeName.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPData(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPData.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPPie1(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPPie1.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPBulkImport(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPBulkImport.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToVIPFileImported(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("VIPFileImported.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }


	 


}


