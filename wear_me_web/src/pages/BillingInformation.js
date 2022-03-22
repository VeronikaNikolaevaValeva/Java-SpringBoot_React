import React from "react";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";
import Header from "../components/layout/Header";
import Layout from "../components/layout/Layout";
import BillingInformationComponent from "../components/layout/BillingInformationComponent";
import billingInformation from "../services/billing.information";
import userService from "../services/user.service";
import { useState } from "react";

function BillingInformation (){
    const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });

  return (
    <Layout>
        <div> 
            <BillingInformationComponent value = {username} />
        </div>
    </Layout>
        
   
  );
};

export default BillingInformation;