/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shivam
 */
package Mail;
import Models.UserModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  


public class MailHelper {
    public String toMail;
    
    
   public  MailHelper(String toMail){
        this.toMail=toMail;  
    }
    
    
    public void sendRegistrationMail() throws Exception{
        try{
             final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		  // Get a Properties object
		    Properties props = System.getProperties();
		    
		    
                     final String username = "atmsimulator7@gmail.com";
                     final String password = "shivam25";
   
                    
            
               Session  session = Session.getInstance(props, 
		                          new Authenticator(){
		                             protected PasswordAuthentication getPasswordAuthentication() {
		                                return new PasswordAuthentication(username, password);
		                             }});
            
          Message  messg = new MimeMessage(session);
		     messg.setFrom(new InternetAddress(username));
		     messg.setRecipients(Message.RecipientType.TO, 
		                      InternetAddress.parse(toMail,false));
                     
         messg.setSubject("Registration Mail");   
         messg.setText("Thank You for registering with ATM Simulator.\nWe hope you like our service.\n\n---ATM Simulator Team");
		     messg.setSentDate(new Date());
		     Transport transport = session.getTransport("smtps");
                     transport.connect("smtp.gmail.com",465,username,password);
                     transport.sendMessage(messg,messg.getAllRecipients());
                     transport.close();                
                     }
                     catch(Exception e){e.printStackTrace();}
    }
      
    
    public void sendBalanceWithdrawMail(String WithdrawAmount,String TotalBalance,String cardnumber){
        try{
             final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		  // Get a Properties object
		    Properties props = System.getProperties();
		    		    
                     final String username = "atmsimulator7@gmail.com";
                     final String password = "shivam25";
   
               Session  session = Session.getInstance(props, 
		                          new Authenticator(){
		                             protected PasswordAuthentication getPasswordAuthentication() {
		                                return new PasswordAuthentication(username, password);
		                             }});
            
          Message  messg = new MimeMessage(session);
		     messg.setFrom(new InternetAddress(username));
		     messg.setRecipients(Message.RecipientType.TO, 
		                      InternetAddress.parse(toMail,false));
         
         String cardnumber4digit=cardnumber.substring(12, cardnumber.length());            
          
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 Date date = new Date();
                     
         messg.setSubject("Cash Withdrawal Mail");
         messg.setText("Dear Customer,\n\nGreetings from CashConnect\n\nYour AC XXXXX"+cardnumber4digit+" was Debited INR "+WithdrawAmount+" on "+dateFormat.format(date)+".\n\nAvailable Balance is INR "+TotalBalance+".\n\nNEVER SHARE your Card number, CVV, PIN, OTP, Internet Banking User ID, Password or URN with anyone, even if the caller claims to be a bank employee. Sharing these details can lead to unauthorised access to your account.\n\nLooking forward to more opportunities to be of service to you.\n\nSincerely,\nCustomer Service Team\nCashConnect Limited\n\nThis is a system-generated e-mail. Please do not reply to this e-mail.");
		     messg.setSentDate(new Date());
		     Transport transport = session.getTransport("smtps");
                     transport.connect("smtp.gmail.com",465,username,password);
                     transport.sendMessage(messg,messg.getAllRecipients());
                     transport.close();                
                     }
                     catch(Exception e){e.printStackTrace();}
    }
   
    
    public void sendBalanceDepositMail(String DepositAmount,String TotalBalance,String cardnumber){
        try{
             final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		  // Get a Properties object
		    Properties props = System.getProperties();		    
		    
                     final String username = "atmsimulator7@gmail.com";
                     final String password = "shivam25";
               
               Session  session = Session.getInstance(props, 
		                          new Authenticator(){
		                             protected PasswordAuthentication getPasswordAuthentication() {
		                                return new PasswordAuthentication(username, password);
		                             }});
            
          Message  messg = new MimeMessage(session);
		     messg.setFrom(new InternetAddress(username));
		     messg.setRecipients(Message.RecipientType.TO, 
		                      InternetAddress.parse(toMail,false));
         
         String cardnumber4digit=cardnumber.substring(12, cardnumber.length());            
          
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 Date date = new Date();
        
         messg.setSubject("Cash Deposit Mail");
         messg.setText("Dear Customer,\n\nGreetings from CashConnect\n\nYour AC XXXXX"+cardnumber4digit+" was Credited INR "+DepositAmount+" on "+dateFormat.format(date)+".\n\nAvailable Balance is INR "+TotalBalance+".\n\nNEVER SHARE your Card number, CVV, PIN, OTP, Internet Banking User ID, Password or URN with anyone, even if the caller claims to be a bank employee. Sharing these details can lead to unauthorised access to your account.\n\nLooking forward to more opportunities to be of service to you.\n\nSincerely,\nCustomer Service Team\nCashConnect Limited\n\nThis is a system-generated e-mail. Please do not reply to this e-mail.");
		     messg.setSentDate(new Date());
		     Transport transport = session.getTransport("smtps");
                     transport.connect("smtp.gmail.com",465,username,password);
                     transport.sendMessage(messg,messg.getAllRecipients());
                     transport.close();                
                     }
                     catch(Exception e){e.printStackTrace();}
    }
}