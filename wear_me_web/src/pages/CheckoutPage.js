import React from "react";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";
import Header from "../components/layout/Header";
import Layout from "../components/layout/Layout";
import userService from "../services/user.service";
import classes from '../components/cssFiles/Checkout.module.css';
import CheckoutBillingAddress from "../components/layout/CheckoutBillingAddress";
import CheckoutPaymentInfo from "../components/layout/CheckoutPaymentInformation";
import { useState } from "react";

function PaymentCheckoutPage (){
    const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });

  return (
    <Layout>
          <div classname={classes.mainBilling}>
                <CheckoutBillingAddress /> 
                <CheckoutPaymentInfo />
          </div>
    </Layout>
        
   
  );
};

export default PaymentCheckoutPage;