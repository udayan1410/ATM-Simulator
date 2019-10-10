<%-- 
    Document   : CashTransfer
    Created on : 16 Jul, 2019, 10:23:02 PM
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
           margin-top: 5%;
           padding-left: 40px;
           padding-right: 40px;
           padding-bottom: 50px;
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
                
                var TransferAmount = document.getElementsByName("TransferAmount")[0].value;
                var Balance = document.getElementsByName("Balance")[0].value;              
                if(TransferAmount.length==0)
                {
                    alert("Amount cannot be empty");
                    flag=false;
                }  
                if(isNaN(TransferAmount)){
                    flag=false;
                    alert("Amount cannot be alphabets");
                }
                if(TransferAmount<0){
                    flag=false;
                    alert("Amount cannot be negative");
                }
                if(TransferAmount>Balance){
                    flag=false;
                    alert("Check account balance before transferring money");
                }
                                      
                var pincode = document.getElementsByName("pincode")[0].value;
                var CorrectPincode = document.getElementsByName("CorrectPincode")[0].value;
                if(pincode!==CorrectPincode){
                    flag=false;
                    alert("Pincode Invalid");
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
            <form action="TransferMoneyHandler" method="post" onsubmit="return validate();">
                <input type="hidden" name="CorrectPincode" value="<%=Constants.pincode%>" >
                <input type="hidden" name="Balance" value="<%=Constants.balance%>" >
                
                <p><u>Transfer Money</u></p><br>
                
                <p class="balance"><i><u>Receiver credentials: </u></i></p><br>
                
            <input type="text" name="ReceiverCardNumber" placeholder="Receiver Card number" style="height:45px;width: 300px;font-size: 17px"><br><br><br>
            
            <input type="text" name="TransferAmount" placeholder="Enter Amount to transfer" style="height:45px;width: 300px;font-size: 17px"><br><br><br><br>
            
            <p class="balance"><i><u>Sender credentials: </u></i></p>
            
            <% out.print("<p class=\"balance\">Card number: &nbsp;"+Constants.cardnumber+"</p>");  %><br>
            
            <input type="password" name="pincode" placeholder="Enter 4-digit ATM Pincode" style="height:45px;width: 300px;font-size: 17px"><br><br>
            
            <% out.print("<p class=\"balance\">Your Account Balance is: &nbsp;"+Constants.balance+"&#x20b9 </p>");  %><br><br><br>
            
            <input class="button" type="submit" name="button" value="Transfer" style="height:45px;width: 300px;font-size: 17px">
            </form>
        </div>
    </body>
</html>
