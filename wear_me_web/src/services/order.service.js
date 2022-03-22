import authHeader from "./auth-header";

function getAllOrders(username){
    var axios = require('axios');

    var config = {
      method: 'get',
      url: 'http://localhost:8080/order/AllOrders/' + username,
      headers: { 
        'Authorization': 'Bearer ' + authHeader() 
      }
    };
    
    return axios(config)
    .then(function (response) {
      return response;
    })
    .catch(function (error) {
    });
};

function getAllProductsOrders(username){
    var axios = require('axios');

var config = {
  method: 'get',
  url: 'http://localhost:8080/order/Products/' + username,
  headers: { 
    'Authorization': 'Bearer ' + authHeader() 
    }
};

return axios(config)
.then(function (response) {
  return response;
})
.catch(function (error) {
});
};


function createOrder(userId, firstName, familyName, username, password, email, dateOfBirth, telephoneNumber, streetAddress, streetNumber,
                      zipCode, town, country, accountStatus, role, 
                      paymentId, cardType, cardNumber, cardName, expirationDate, cardHolderName,
                      billingStreet, billingHouseNumber, billingZipCode, billingCity, billingCountry, couponCode){
  var axios = require('axios');
var data = JSON.stringify({
  "user": {
    "id": userId,
    "firstName": firstName,
    "familyName": familyName,
    "username": username,
    "password": password,
    "email": email,
    "dateOfBirth": dateOfBirth,
    "telephoneNumber": telephoneNumber,
    "streetAddress": streetAddress,
    "streetNumber": streetNumber,
    "zipCode": zipCode,
    "town": town,
    "country": country,
    "accountStatus": accountStatus,
    "role": role
  },
  "billingAddress": billingStreet + " "+ billingHouseNumber +" ("+ billingZipCode + "), "+ billingCity + ", "+ billingCountry,
  "paymentInformation": {
    "id": paymentId,
    "user": {
        "id": userId,
        "firstName": firstName,
        "familyName": familyName,
        "username": username,
        "password": password,
        "email": email,
        "dateOfBirth": dateOfBirth,
        "telephoneNumber": telephoneNumber,
        "streetAddress": streetAddress,
        "streetNumber": streetNumber,
        "zipCode": zipCode,
        "town": town,
        "country": country,
        "accountStatus": accountStatus,
        "role": role
    },
    "cardType": cardType,
    "cardName": cardName,
    "cardNumber": cardNumber,
    "cardHolderName": cardHolderName,
    "expirationDate": expirationDate
  },
  "totalPrice": 0
});

var config = {
  method: 'post',
  url: 'http://localhost:8080/order/addOrder/' + couponCode,
  headers: { 
    'Authorization': 'Bearer ' + authHeader() ,
    'Content-Type': 'application/json'
  },
  data : data
};

axios(config)
.then(function (response) {
})
.catch(function (error) {
});


};

export default {
  getAllOrders,
  getAllProductsOrders,
  createOrder
};