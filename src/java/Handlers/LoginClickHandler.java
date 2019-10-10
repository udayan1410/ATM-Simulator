package Handlers;
import Database.DBHelper;
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
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/LoginClickHandler"})
public class LoginClickHandler extends HttpServlet {

    
    public LoginClickHandler(){
        
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response )throws ServletException{
        
    }
    
  
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response )throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
       
        DBHelper db;
        try {
            db = new DBHelper();
            boolean flag=db.readfromDB(request.getParameter("cardnumber"),request.getParameter("pincode"));
            
            if(flag)
            {
                System.out.print(""+db.getIdFromCardNumber(request.getParameter("cardnumber")));
                String id = db.getIdFromCardNumber(request.getParameter("cardnumber"));
                          
                Constants.id = id;
                Constants.cardnumber = request.getParameter("cardnumber");
                Constants.pincode = request.getParameter("pincode");
                
                response.sendRedirect("MainActivity.jsp");
                pw.close();
            }
            else{
              response.sendRedirect("/ATM_Simulator/Success/LoginFailed.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginClickHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
