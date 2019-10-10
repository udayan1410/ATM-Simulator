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
@WebServlet(urlPatterns = {"/DepositMoneyHandler"})
public class DepositMoneyHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Deposit=Integer.parseInt(req.getParameter("DepositAmount"));
        int Balance=Integer.parseInt(Constants.balance);
        
            DBHelper db;
            try {
                db = new DBHelper();
                int result=Balance+Deposit;
                Constants.balance=String.valueOf(result);
                db.updateDB(Constants.id,Constants.balance,"credit",String.valueOf(Deposit),"You");
                UserModel usermodel = new UserModel();
                usermodel = db.readeverythingfromDB(Constants.id);
                MailHelper mh = new MailHelper(usermodel.getEmail());
                mh.sendBalanceDepositMail(String.valueOf(Deposit),String.valueOf(result),usermodel.getCardnumber());
                resp.sendRedirect("/ATM_Simulator/Success/DepositSuccess.jsp");
            } 
            catch (Exception ex) {
                Logger.getLogger(WithdrawMoneyHandler.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

   
}
