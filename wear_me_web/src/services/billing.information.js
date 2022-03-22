import authHeader from "./auth-header";

function getUsersBillingInformation(username){
    var axios = require('axios');

    var config = {
      method: 'get',
      url: 'http://localhost:8080/paymentInfo/AllPaymentInfo/' + username,
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


function addPaymentInformation(username,cardType,cardNumber,cardName,cardHolderName,expirationDate){
    var axios = require('axios');
    var config = {
      method: 'post',
      url: 'http://localhost:8080/paymentInfo/addPaymentInfo/' + username + '/' + cardType + '/' + cardNumber + '/' + cardName + '/' +
                cardHolderName + '/' + expirationDate,
      headers: { 
        'Authorization': 'Bearer ' + authHeader() 
        }
    };
    
   return axios(config)
    .then(function (response) {
    })
    .catch(function (error) {
    });
};

function updatePaymentInfo(username,cardType,cardNumber,cardName,cardHolderName,expirationDate){
    var axios = require('axios');

var config = {
  method: 'put',
  url: 'http://localhost:8080/paymentInfo/updatePaymentInfo/' + username + '/' + cardType + '/' + cardNumber + '/' + cardName + '/' +
            cardHolderName + '/' + expirationDate,
  headers: { 
    'Authorization': 'Bearer ' + authHeader() 
    }
};

return axios(config)
.then(function (response) {
  return response
})
.catch(function (error) {
});
}

export default {
    getUsersBillingInformation,
    addPaymentInformation,
    updatePaymentInfo
};