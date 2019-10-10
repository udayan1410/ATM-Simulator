<%-- 
    Document   : Register
    Created on : 11 Jul, 2019, 11:01:34 PM
    Author     : Shivam
--%>

<%@page import="Handlers.RegisterClickHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        
        div{
            background: #fffffff;
            border:1px solid lightgrey;
            border-radius: 10px;
            padding-top: 5px;
            padding-bottom: 30px;
            padding-left: 60px;
            padding-right: 60px;
            margin-left: 30%;
            margin-top:50px;
            width:500px;
        }
        
         p{
            font-family: "Georgia";
            color: #1a73e8;
            font-size: 38px;
        }
        
        input{
           height:45px;
           width: 330px;
           font-size: 17px; 
        }
        
        .Registerbtn{
            border:1px solid #1a73e8;
            padding-left: 40px;
            padding-right: 40px;
            background:#1a73e8;
            text-decoration: none;
            color:#ffffff;
            transition: 0.4s;
            cursor: pointer;
        }
        
        .Registerbtn:hover{
            color:#1a73e8;
            background:#ffffff;
        }
        
    </style>
    
    <script type="text/javascript">
        function validate()
        {
           var flag = true;
           
           var FirstName = document.getElementsByName("FirstName")[0].value;
           var LastName = document.getElementsByName("LastName")[0].value;
           var email = document.getElementsByName("email")[0].value;
           var phone = document.getElementsByName("phone")[0].value;
           var CardName = document.getElementsByName("CardName")[0].value;
           var CardNumber = document.getElementsByName("CardNumber")[0].value;
           var cvv = document.getElementsByName("cvv")[0].value;
           var pincode = document.getElementsByName("pincode")[0].value;
           
           if(FirstName.length<1 || LastName.length<1){
             flag=false;
             alert("Enter valid username");
           }          
           else if(email.length<9 || email.indexOf("@")<=3 || email.indexOf(".")<2){
             flag=false;
             alert("Enter valid Email");
           }             
           else if(phone.length!=10){
             flag=false;
             alert("Enter valid Phone no.");
           }
           else if(CardName.length<3){
             flag=false;
             alert("Enter name on card"); 
           }
           else if(CardNumber.length!=16){
             flag=false;  
             alert("Enter valid card number");
           }            
           else if(cvv.length!=3){
             flag=false;
             alert("Enter valid cvv");
           }
           else if(pincode.length!=4){
             flag=false;  
             alert("Enter valid pincode");
           }
             
         return flag;
        };
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ATM Simulator</title>
    </head>
    <body>
        <form action="RegisterClickHandler" method="post" onsubmit="return validate();">
        <div align="center" style="margin-right: 20%;">
             <p>REGISTER</p>
             <input type="text" name="FirstName" placeholder="First Name"><br><br><br>
             <input type="text" name="LastName" placeholder="Last Name"><br><br><br>
             <input type="text" name="email" placeholder="Email-id"><br><br><br>
             <input type="number" name="phone" placeholder="Phone number"><br><br><br> 
             <input type="text" name="CardName" placeholder="Name on Card"><br><br><br>
             <input type="number" name="CardNumber" placeholder="Card Number"><br><br><br>
             <input type="password" name="cvv" placeholder="CVV"><br><br><br>
             <input type="password" name="pincode" placeholder="ATM Pincode"><br><br><br> 
             <input class="Registerbtn" type="submit" name="submitbtn" value="Submit">
        </div>
    </form>
        <br/>
        <br/>
        <br/><br/>
        
        
    </body>
</html>
