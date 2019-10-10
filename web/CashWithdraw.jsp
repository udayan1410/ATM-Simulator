<%-- 
    Document   : CashWithdraw
    Created on : 15 Jul, 2019, 10:28:51 PM
    Author     : Shivam
--%>

<%@page import="Utils.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        
        div{
           border:1px solid lightgrey;
           border-radius: 10px;
           width:500px;
           margin-left: auto;         
           margin-right: auto;
           margin-top: 8%;
           padding-left: 40px;
           padding-right: 40px;
           padding-bottom: 60px;
           margin-bottom: 40px;            
        }  
        
        .button{
            border:1px solid #1a73e8;
            padding: 10px;
            padding-left: 40px;
            padding-right: 40px;
            background:#1a73e8;
            text-decoration: none;
            color:#ffffff;
            transition: 0.2s;
            cursor: pointer;
        }
        
        .button:hover{
            color:#1a73e8;
            background:#ffffff;
        }
        
        p{
            font-family: "Georgia";
            color: #1a73e8;
            font-size: 38px;
        }
        
        .balance{
           font-family: default;
           font-size: 26px;
        }
    </style>
     <script type="text/javascript">
            function validate()
            {
                var flag=true;
                
                var WithdrawAmount = document.getElementsByName("WithdrawAmount")[0].value;
                
                if(WithdrawAmount.length==0)
                {
                    alert("Amount cannot be empty");
                    flag=false;
                }  
                  if(isNaN(WithdrawAmount)){
                    flag=false;
                    alert("Amount cannot be alphabets");
                }
                if(WithdrawAmount<0){
                    flag=false;
                    alert("Amount cannot be negative");
                }
               
                                                   
                return flag;
            };
        </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ATM Simulator</title>
    </head>
    <body>
        <div align="center"> 
            <form action="WithdrawMoneyHandler" method="post" onsubmit="return validate();">
            <p><u>Cash Withdrawal</u></p><br><br>                         
            <input type="text" name="WithdrawAmount" placeholder="Enter Amount to withdraw" style="height:45px;width: 300px;font-size: 17px"><br><br>
    
             <%       
                 //out.print("id="+Constants.id);
              out.print("<p class=\"balance\">Your Account Balance is: &nbsp;"+Constants.balance+"&#x20b9 </p>");  %><br>
              
            <input class="button" type="submit" name="button" value="Withdraw" style="height:45px;width: 300px;font-size: 17px">
            </form>
        </div>
    </body>
</html>
