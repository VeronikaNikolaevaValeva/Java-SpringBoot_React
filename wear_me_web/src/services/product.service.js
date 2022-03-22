import axios from "axios";
import authHeader from "./auth-header";
import React from 'react';
import { useState } from 'react';


const API_URL = "http://localhost:8080/product";


 const getListOfNineProducts=(num, category)=>{
  var axios = require('axios');
  var config = {
    method: 'get',
    url: 'http://localhost:8080/product/NineProducts/' + num + '/' + category ,
    headers: { 
      'Authorization': 'Bearer ' + authHeader()
    }
  };
  
  return axios(config)
  .then(function (response) {
    const name = num + category;
    localStorage.setItem(name, JSON.stringify(response.data));
    return response.data;
  })
  .catch(function (error) {
    return {};
  });
};

const getListOfProductByGender = (num, gender, category) => {
  var axios = require('axios');
  var qs = require('qs');
  
  var config = {
    method: 'get',
    url: 'http://localhost:8080/product/NineProducts/' + num + '/'+ gender + '/' + category,
    headers: { 
      'Authorization': 'Bearer ' + authHeader()
    }
  };
  
  return axios(config)
  .then(function (response) {
    const name = num + '&' + gender + category;
    localStorage.setItem(name, JSON.stringify(response.data));
    return response.data;
  })
  .catch(function (error) {
    return {};
  });
};


function addProductToShoppingCart(productId, username){
  var axios = require('axios');
  var config = {
    method: 'post',
    url: 'http://localhost:8080/shoppingCart/addItem/' + username +'/' + productId,
    headers: { 
      'Authorization': 'Bearer ' + authHeader()
    }
  };

  axios(config)
  .then(function (response) {
  })
  .catch(function (error) {
  });
};

const getListOfAllItemsInShoppingCart = (username) => {
var axios = require('axios');
var config = {
  method: 'get',
  url: 'http://localhost:8080/shoppingCart/AllShoppingCartItems/' + username,
  headers: { 
    'Authorization':  'Bearer ' + authHeader()
  }
};

return axios(config)
.then(function (response) {
  //localStorage.setItem("shoppingCart"+username, JSON.stringify(response.data));
  return response.data;
})
.catch(function (error) {
});
}

function deleteItemFromShoppingCart(shoppingCartId){
  var axios = require('axios');

  var config = {
    method: 'delete',
    url: 'http://localhost:8080/shoppingCart/deleteItem/' + shoppingCartId,
    headers: { 
     'Authorization': 'Bearer ' + authHeader()
    }
  };

  axios(config)
  .then(function (response) {
  })
  .catch(function (error) {
  });
}

function addProductToFavouritesItem(productId, username){
  var axios = require('axios');
  var data = '';
  
  var config = {
    method: 'post',
    url: 'http://localhost:8080/favouriteItem/addItem/' +username + '/' + productId,
    headers: { 
      'Authorization': 'Bearer ' + authHeader()
    },
    data : data
  };
  
  axios(config)
  .then(function (response) {
  })
  .catch(function (error) {
  });
};


function getListOfALlfavouriteItems(username){
  var axios = require('axios');
var data = '';

var config = {
  method: 'get',
  url: 'http://localhost:8080/favouriteItem/AllFavouriteItems/' + username,
  headers: { 
    'Authorization': 'Bearer ' + authHeader()
  },
  data : data
};

return axios(config)
.then(function (response) {
  return response.data;
})
.catch(function (error) {
});
};

function deleteItemFromListOfFavourite(shoppingCartId){
  var axios = require('axios');
  var data = '';
  
  var config = {
    method: 'delete',
    url: 'http://localhost:8080/favouriteItem/deleteItem/'+shoppingCartId,
    headers: { 
      'Authorization': 'Bearer ' + authHeader() 
    },
    data : data
  };
  
  axios(config)
  .then(function (response) {
  })
  .catch(function (error) {
  });
  
}

export default {
    getListOfNineProducts,
    getListOfProductByGender,
    addProductToShoppingCart,
    getListOfAllItemsInShoppingCart,
    deleteItemFromShoppingCart,
    addProductToFavouritesItem,
    getListOfALlfavouriteItems,
    deleteItemFromListOfFavourite
};