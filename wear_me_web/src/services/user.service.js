import authHeader from "./auth-header";

const getUserByUsername = () => {
  var axios = require('axios');
  var config = {
    method: 'get',
    url: 'http://localhost:8080/customer/getLoggedUser',
    headers: { 
      'Authorization': 'Bearer ' + authHeader()
    }
  };

  return axios(config)
  .then(function (response) {
    return response.data;
  })
  .catch(function (error) {
  });

};

const updateInfo = (firstName, familyName, username, email, dateOfBirth, telephoneNum, 
  streetAddress, streetNumber, zipCode, town, country) => {
    var axios = require('axios');
var data = JSON.stringify({
  "firstName": firstName,
  "familyName": familyName,
  "username": username,
  "password": "password",
  "email": email,
  "dateOfBirth": dateOfBirth,
  "telephoneNumber": telephoneNum,
  "streetAddress": streetAddress,
  "streetNumber": streetNumber,
  "zipCode": zipCode,
  "town": town,
  "country": country,
  "accountStatus": "ACTIVE",
  "role": "CUSTOMER"
});

var config = {
  method: 'post',
  url: 'http://localhost:8080/customer/UpdateUser',
  headers: { 
    'Authorization': 'Bearer ' + authHeader(),
    'Content-Type': 'application/json'
  },
  data : data
};

 axios(config)
      .then(function (response) {
        getUserByUsername();
      })
  .catch(function (error) {
  });
}


export default {
  getUserByUsername,
  updateInfo
};