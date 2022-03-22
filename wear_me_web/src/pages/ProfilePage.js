import React from "react";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";
import Header from "../components/layout/Header";
import Layout from "../components/layout/Layout";
import ProfileComponent from "../components/layout/ProfileComponent";
import PasswordComponent from "../components/layout/PasswordComponent";

function Profile (){
  return (
    <Layout>
        <div>
          <ProfileComponent />  
          {/* <PasswordComponent /> */}
        </div>
         </Layout>
        
   
  );
};

export default Profile;