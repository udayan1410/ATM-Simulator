<%-- 
    Document   : MainActivity
    Created on : 11 Jul, 2019, 11:03:32 PM
    Author     : Shivam
--%>

<%@page import="Utils.Constants"%>
<%@page import="Models.UserModel"%>
<%@page import="Database.DBHelper"%>
<%@page import="Handlers.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .general{
            border:1px solid #1a73e8;
            padding-left: 40px;
            padding-right: 40px;
            background:#ffffff;
            text-decoration: none;
            color:#1a73e8;
            height:45px;
            width: 330px;
            font-size: 17px; 
            transition: 0.4s;
            cursor: pointer;  
        }
        
        .general:hover{
            color:#ffffff;
            background:#1a73e8;  
        }
        
        img{
           position: relative;
           margin-top: 25px;
        }
        
        .welcome{
           font-size: 30px;
           color:#1a73e8;
           margin-left: 8%;
        }
        
        .balance{
           font-size: 26px;
           color:#1a73e8; 
        }
        
        .logout{
           height:40px;
           width:100px;
           text-align: center;
           border:1px solid #1a73e8;
           background:#ffffff;
           text-decoration: none;
           color: #1a73e8;
           margin-right: 20px;          
           border-radius: 7px;
           font-size: 17px; 
           transition: 0.4s;
           cursor: pointer;
           float:right;
        }
        
        .logout:hover{
            color:#ffffff;
            background:#1a73e8;  
        }
        
        .delete{
           position:absolute;
            height:40px;
           width:160px;
           text-align: center;
           border:1px solid red;
           background:#ffffff;
           text-decoration: none;
           color: red;          
           border-radius: 7px;
           font-size: 17px; 
           transition: 0.4s;
           cursor: pointer;
           left:88%;
           top:13%
        }
        
        .delete:hover{
            color:#ffffff;
            background: red;  
        }
    </style>
    <script type="text/javascript">
            function DeleteCheck()
            {
                var flag=false;
                
                if(flag==false){
                    var result = confirm("Are you sure you want to delete account?");
                    if(result==true)
                    flag=true;
                    else 
                    flag=false;
                }       
                return flag;
            };
            
            function LogoutCheck()
            {
                var flag=false;
                
                if(flag==false){
                    var result = confirm("Are you sure you want to logout?");
                    if(result==true)
                    flag=true;
                    else 
                    flag=false;
                }        
                return flag;
            };
     </script>
    <head>
           <% UserModel m;
          String id = Constants.id;                             
           DBHelper db = new DBHelper();
           m = db.readeverythingfromDB(id); 
           String Cardname = m.getCardname();
           String Balance = m.getBalance();
           Constants.balance=Balance;
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ATM Simulator</title>
    </head>
    <body>
            <form method="post" action="Login.jsp" onsubmit="return LogoutCheck();"><a href="#"><input class="logout" type="submit" name="Logout" value="Logout"></a></form>
            <form method="post" action="DeleteAccountHandler" onsubmit="return DeleteCheck();"><input class="delete" type="submit" name="Delete" value="Delete Account"></form>
        
        <div align="center">
        <%             
            out.print("<p class=\"welcome\">Welcome  "+Cardname+"</p>"); %>
            
        <img src="account.png" height="147px" width="147px"><br><br><br>
        
        <%       
            out.print("<p class=\"balance\">Your Account Balance is: "+Balance+"</p>"); %><br><br>
            
            <form action="CashWithdraw.jsp">                 
                <input class="general" type="submit" name="CashWithdrawal" value="Cash Withdrawal">         
            </form><br><br><br>
            <form action="CashDeposit.jsp">               
                <input class="general" type="submit" name="CashDeposit" value="Cash Deposit">
            </form><br><br><br>
            <form action="CashTransfer.jsp">
                <input class="general" type="submit" name="TransferMoney" value="Transfer money">
            </form><br><br><br>
            <form action="TransactionHistory.jsp">               
                <input class="general" type="submit" name="TransactionHistory" value="Transaction History" style="margin-bottom: 60px;">
            </form>
        </div>
       
    </body>
</html>
