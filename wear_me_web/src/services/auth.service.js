import axios from 'axios';
import React from 'react';
import userService from "../services/user.service";
const API_URL = "http://localhost:8080";



const register = (firstName, familyName, username, password, email, dateOfBirth, telephoneNum, 
    streetAddress, streetNumber, zipCode, town, country, accStatus, role ) => {
  return axios.post(API_URL + "/user/signUp", {
      firstName,
      familyName,
    username,
    password,
    email,
    dateOfBirth,
    telephoneNum,
    streetAddress,
    streetNumber,
    zipCode,
    town,
    country,
    accStatus,
    role
  });
};

const login = (username, password) => {
var axios = require('axios');
var qs = require('qs');
var data = qs.stringify({
  'username': username,
  'password': password 
});
var config = {
  method: 'post',
  url: 'http://localhost:8080/user/login',
  headers: { 
    'Content-Type': 'application/x-www-form-urlencoded'
  },
  data : data
};
return axios(config)
.then(function (response) {
      localStorage.setItem("accessTokens", JSON.stringify(response.data));
      return response.data;

})
.catch(function (error) {
});
};



const logout = () => {
  localStorage.removeItem("user");
  localStorage.removeItem("salesManager");
  localStorage.removeItem("customerManager");
  localStorage.removeItem("accessTokens");
  localStorage.removeItem("username");
  localStorage.removeItem("allproducts");
  localStorage.removeItem("shoppingCart");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

export default {
  register,
  login,
  logout,
  getCurrentUser
};