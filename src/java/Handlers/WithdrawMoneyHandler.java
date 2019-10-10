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
@WebServlet(urlPatterns = {"/WithdrawMoneyHandler"})
public class WithdrawMoneyHandler extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Withdraw=Integer.parseInt(req.getParameter("WithdrawAmount"));
        int Balance=Integer.parseInt(Constants.balance);
        if(Withdraw>Balance){
                        
            resp.sendRedirect("/ATM_Simulator/Success/WithdrawFail.jsp");
        }
        else{
            DBHelper db;
            try {
                db = new DBHelper();
                int result=Balance-Withdraw;
                Constants.balance=String.valueOf(result);
                db.updateDB(Constants.id,Constants.balance,"debit",String.valueOf(Withdraw),"You");
                UserModel usermodel = new UserModel();
                usermodel = db.readeverythingfromDB(Constants.id);
                MailHelper mh = new MailHelper(usermodel.getEmail());
                mh.sendBalanceWithdrawMail(String.valueOf(Withdraw),String.valueOf(result),usermodel.getCardnumber());
                resp.sendRedirect("/ATM_Simulator/Success/WithdrawSuccess.jsp");
            } 
            catch (Exception ex) {
                Logger.getLogger(WithdrawMoneyHandler.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    
               
    }
    
    
    
}
