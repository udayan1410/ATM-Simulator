/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Database.DBHelper;
import Mail.MailHelper;
import Models.UserModel;
import Utils.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shivam
 */
@WebServlet(urlPatterns = {"/TransferMoneyHandler"})
public class TransferMoneyHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ReceiverCardNumber=req.getParameter("ReceiverCardNumber");
        int TransferAmount=Integer.parseInt(req.getParameter("TransferAmount"));
        int Balance = Integer.parseInt(Constants.balance);
        UserModel m=new UserModel();
        try {
            DBHelper db=new DBHelper();
            m=db.readeverythingfromDBfromCardnumber(ReceiverCardNumber);
            if(!m.getId().equalsIgnoreCase("")){    //Check if receiver exists
                if(Balance>=TransferAmount){
                    String finalBalance = String.valueOf(Balance-TransferAmount);
                    Constants.balance = finalBalance;
                    
                    db.updateDB(Constants.id, finalBalance,"debit",String.valueOf(TransferAmount),ReceiverCardNumber);  //Sender
                    UserModel usermodel = new UserModel();
                    usermodel = db.readeverythingfromDB(Constants.id);
                    MailHelper mh = new MailHelper(usermodel.getEmail());
                    mh.sendBalanceWithdrawMail(String.valueOf(TransferAmount),finalBalance,usermodel.getCardnumber());   //Send mail to sender
                    
                    m=db.readeverythingfromDBfromCardnumber(ReceiverCardNumber);
                    int currentReceiverBalance = Integer.parseInt(m.getBalance());
                    int finalReceiverBalance = currentReceiverBalance+TransferAmount;
                    
                    db.updateDB(m.getId(), String.valueOf(finalReceiverBalance),"credit",String.valueOf(TransferAmount),Constants.cardnumber); //Receiver
                    UserModel model = new UserModel();
                    model = db.readeverythingfromDB(db.getIdFromCardNumber(ReceiverCardNumber));
                    MailHelper mail = new MailHelper(model.getEmail());
                    mail.sendBalanceDepositMail(String.valueOf(TransferAmount),String.valueOf(finalReceiverBalance),ReceiverCardNumber); //Send mail to receiver
                }
               resp.sendRedirect("http://192.168.0.29:8080/ATM_Simulator/Success/TransferSuccess.jsp");   
            }
            else{
               resp.sendRedirect("http://192.168.0.29:8080/ATM_Simulator/Success/TransferFail.jsp");  
            }
            
        } catch (Exception ex) {
            Logger.getLogger(TransferMoneyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
   
  
}
