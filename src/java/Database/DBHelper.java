
package Database;

import Models.HistoryModel;
import Models.UserModel;
import Utils.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shivam
 */
public class DBHelper {
    Connection con=null;
    
    public DBHelper() throws Exception{
                                                
                 Class.forName("com.mysql.jdbc.Driver");
                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");              
              
    }
    
    public String getId(String phone) throws SQLException{
        
        PreparedStatement st = con.prepareStatement("select ID from userdata where Phone='"+phone+"'");
        ResultSet rs = st.executeQuery();
        String id="";
        while(rs.next()){
            id = rs.getString(1);
        }
        
      return id;
    }
    
    
     public String getIdFromCardNumber(String cardnumber) throws SQLException{
        
        PreparedStatement st = con.prepareStatement("select ID from account where cardnumber='"+cardnumber+"'");
        ResultSet rs = st.executeQuery();
        String id="";
        while(rs.next()){
            id = rs.getString(1);
        }
        
      return id;
    }
    
    
    public void insertToDB(UserModel um){
        try {
        String sql="Insert into userdata(FirstName,LastName,Email,Phone) values('"+um.getFirstname()+"','"+um.getLastname()+"','"+um.getEmail()+"','"+um.getPhone()+"')";
        Statement st;
        
            st = con.createStatement();
            st.executeUpdate(sql);
            
            

            sql="Insert into account(id,cardnumber,cardname,cvv,pincode,balance) values('"+getId(um.getPhone())+"','"+um.getCardnumber()+"','"+um.getCardname()+"','"+um.getCvv()+"','"+um.getPincode()+"','0')";
                
            st = con.createStatement();
            st.executeUpdate(sql);
                  
     
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDB(String id,String balance,String remark,String amount,String senderORreceiver){
        try {
        String sql="UPDATE account SET balance='"+balance+"' where ID='"+id+"'";
        Statement st;
        
            st = con.createStatement();
            st.executeUpdate(sql);
                  
            updateHistoryTable(id,amount,remark,senderORreceiver);
     
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean readfromDB(String CardNumber,String pincode) throws SQLException{
        
        PreparedStatement st = con.prepareStatement("select ID from account where cardnumber='"+CardNumber+"' and pincode='"+pincode+"'");
        ResultSet rs = st.executeQuery();
        String id="";
        while(rs.next()){
            id = rs.getString(1);
        }
        if(id.equalsIgnoreCase(""))
            return false;
        return true;
    }
    
    
    public UserModel readeverythingfromDB(String id) throws SQLException{
        UserModel m = new UserModel();
        
        PreparedStatement st = con.prepareStatement("select * from account where ID='"+id+"'");
        ResultSet rs = st.executeQuery();
        PreparedStatement st2 = con.prepareStatement("select email from userdata where ID='"+id+"'");
        ResultSet rs2 = st2.executeQuery();
        
        while(rs.next()){
        m.setCardnumber( rs.getString(2));
        m.setCardname(rs.getString(3));
        m.setCvv(rs.getString(4));
        m.setPincode(rs.getString(5));
        m.setBalance(rs.getString(6));
        }
        while(rs2.next()){
        m.setEmail(rs2.getString(1));  
        }
        
        return m;
    }
    
    public UserModel readeverythingfromDBfromCardnumber(String cardnumber) throws SQLException{
        UserModel m = new UserModel();
        
        PreparedStatement st = con.prepareStatement("select * from account where cardnumber='"+cardnumber+"'");
        ResultSet rs = st.executeQuery();
        
        m.setCardnumber("");
        m.setCardname("");
        m.setCvv("");
        m.setPincode("");
        m.setBalance("");
        m.setId("");
        
        while(rs.next()){           
        m.setId(rs.getString(1));
        m.setCardnumber( rs.getString(2));
        m.setCardname(rs.getString(3));
        m.setCvv(rs.getString(4));
        m.setPincode(rs.getString(5));
        m.setBalance(rs.getString(6));       
        }
        
        return m;
    }
    
   
    
      public void updateHistoryTable(String id,String amount,String remark,String senderORreceiver){
        long datelong = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date(datelong);
        String currentDate = sdf.format(date);
        
        try{     
        String sql = "Insert into history values('"+id+"','"+currentDate+"','"+amount+"','"+remark+"','"+senderORreceiver+"')";
        Statement st;
          st = con.createStatement();
            st.executeUpdate(sql);
        }catch(Exception e){e.printStackTrace();}             
    }
      
      public ArrayList<HistoryModel> getHistoryFromID(String id)
      {
          ArrayList<HistoryModel> finalList = new ArrayList();
          
          try{
            PreparedStatement st = con.prepareStatement("select * from history where ID='"+id+"'");
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){        
                HistoryModel hm = new HistoryModel();
                hm.setId(id);
                hm.setDate(rs.getString(2));
                hm.setAmount(rs.getString(3));
                hm.setRemark(rs.getString(4));   
                hm.setSenderORreceiver(rs.getString(5));
                finalList.add(hm);
            }                   
          }
          catch(Exception e){e.printStackTrace();}
                 
          return finalList;
      }
              
     public void deletefromDB(String id) throws SQLException{
         String sql = "delete from userdata where ID='"+id+"'";
         Statement st;
         st = con.createStatement(); 
         st.executeUpdate(sql);
     }
     
     public boolean checkCardNumber(String cardnumber){
        
        try{
            PreparedStatement st = con.prepareStatement("select cardnumber from account where cardnumber='"+cardnumber+"'");
            ResultSet rs = st.executeQuery();
            String ca="";
            while(rs.next()){        
              ca=rs.getString(1);
            }
            if(ca.equalsIgnoreCase(""))
                return true;
          }
          catch(Exception e){e.printStackTrace();}
        
       return false;
     }
}