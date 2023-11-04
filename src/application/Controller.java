package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Scanner;

import Code.MediaAnalyser;
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
	private TextField password;
	
	@FXML
	private Text logInStatus;
	
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
	User user = new User("","","","");
	  
	public void print(ArrayList postList) {
		
		textArea.setText(postList.toString());
	
	}

	 
	 public void createUser(){
		 
		 
			 user.setUserName(userName.getText());
			 user.setPassword(password.getText());
			 userList.add(user);
			 
			 userName.setText(user.getUserName());
			 password.setText(user.getPassword());
			 			
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
		    boolean flag = false;
		    
//		    for(int i = 0; i < userList.size(); i++) {
//		    	if(userList.get(i).getUserName().equals(userName.getText())) {
//		    		
//		    	}
//		    }
	

		    if (userName.getText().equals("Username") && password.getText().equals("Password")) {
		        logInStatus.setText("Welcome");
		        flag = true;
		    } else {
		        logInStatus.setText("Incorrect Username/Password. Please try again");
		    }

		    if (flag) {
		        Parent root = FXMLLoader.load(getClass().getResource("NormalWelcome.fxml"));
		        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		    }
		}
	 
	 public void switchToNormalAddAPost(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalAddAPost.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToNormalPostSubmitted(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("NormalPostSubmitted.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 
	 public void switchToChangePassword(ActionEvent event) throws IOException {
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


