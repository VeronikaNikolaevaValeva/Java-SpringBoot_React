import {useRef} from 'react';
import { useState } from 'react';
import React, { Component } from 'react';
import classes from '../../components/cssFiles/Checkout.module.css';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import orderService from '../../services/order.service';
import CheckButton from "react-validation/build/button";
import billingInformation from '../../services/billing.information';
import userService from '../../services/user.service';


function CheckoutBillingAddress(){
  const form = useRef();
  const checkBtn = useRef();
  const [message, setMessage] = useState("");
  const [successful, setSuccessful] = useState(false);
    
    const [userId, setUserId] = useState(" ");
    const [FirstName, setFirstName] = useState(" ");
    const [FamilyName, setFamilyName] = useState(" ");
    const [Username, setUsername] = useState("null");
    const [Password, setPassword] = useState(" ");
    const [Email, setEmail] = useState(" ");
    const [DateOfBirth, setDateOfBirth] = useState(" ");
    const [TelephoneNum, setTelephoneNum] = useState(" ");
    const [StreetAddress, setStreetAddress] = useState(" ");
    const [StreetNumber, setStreetNumber] = useState(" ");
    const [ZipCode, setZipCode] = useState(" ");
    const [Town, setTown] = useState(" ");
    const [Country, setCountry] = useState(" ");
    const [AccountStatus, setAccountStatus] = useState(" ");
    const [Role, setRole] = useState(" ");
    const [couponCode, setCouponCode] = useState("none");

  const [cardType, setCardType] = useState("type");
  const [cardName, setCardName] = useState("name");
  const [cardNumber, setCardNumber] = useState("number");
  const [cardHolderName, setCardHolderName] = useState("holder name");
  const [expirationDate, setExpirationDate] = useState("expiration date");
  const [billingInfo, setBillingInfo] = useState("state");
  const [billingId, setBillingId] = useState("")

  const [billingStreetAddress, setBillingStreetAddress] = useState(" ");
  const [billingStreetNumber, setBillingStreetNumber] = useState(" ");
  const [billingZipCode, setBillingZipCode] = useState(" ");
  const [billingTown, setBillingTown] = useState(" ");
  const [billingCountry, setBillingCountry] = useState(" ");

  if(billingInfo == "state" && Username!="null"){
    setBillingId("null");
    setBillingInfo("not null");
    billingInformation.getUsersBillingInformation(Username).then(response => {
        setBillingId(JSON.parse(JSON.stringify(response)).id);
        setCardType(JSON.parse(JSON.stringify(response)).cardType);
        setCardName(JSON.parse(JSON.stringify(response)).cardName);
        setCardNumber(JSON.parse(JSON.stringify(response)).cardNumber);
        setCardHolderName(JSON.parse(JSON.stringify(response)).cardHolderName);
        setExpirationDate(JSON.parse(JSON.stringify(response)).expirationDate);
        });
    }

  userService.getUserByUsername().then(response=>{
    if(StreetAddress==" " && response!= null){
      setUserId(JSON.parse(JSON.stringify(response)).id);
      setFirstName(JSON.parse(JSON.stringify(response)).firstName);
     setFamilyName(JSON.parse(JSON.stringify(response)).familyName);
     setUsername(JSON.parse(JSON.stringify(response)).username);
     setPassword(JSON.parse(JSON.stringify(response)).password);
     setEmail(JSON.parse(JSON.stringify(response)).email);
     setDateOfBirth(JSON.parse(JSON.stringify(response)).dateOfBirth);
     setTelephoneNum(JSON.parse(JSON.stringify(response)).telephoneNumber);
     setStreetAddress(JSON.parse(JSON.stringify(response)).streetAddress);
     setBillingStreetAddress(JSON.parse(JSON.stringify(response)).streetAddress);
     setStreetNumber(JSON.parse(JSON.stringify(response)).streetNumber);
     setBillingStreetNumber(JSON.parse(JSON.stringify(response)).streetNumber);
     setZipCode(JSON.parse(JSON.stringify(response)).zipCode);
     setBillingZipCode(JSON.parse(JSON.stringify(response)).zipCode);
     setTown(JSON.parse(JSON.stringify(response)).town);
     setBillingTown(JSON.parse(JSON.stringify(response)).town);
     setCountry(JSON.parse(JSON.stringify(response)).country);
     setBillingCountry(JSON.parse(JSON.stringify(response)).country);
     setAccountStatus(JSON.parse(JSON.stringify(response)).accountStatus);
     setRole(JSON.parse(JSON.stringify(response)).role);
    }
  });

  const onChangeStreet = (e) => {
    const changedStreet = e.target.value;
    setBillingStreetAddress(changedStreet);
  };

  const onChangeHouseNumber= (e) => {
    const changedHouseNumber = e.target.value;
    setBillingStreetNumber(changedHouseNumber);
  };

  const onChangeZipCode = (e) => {
    const changedZipCode = e.target.value;
    setBillingZipCode(changedZipCode);
  };

  const onChangeCity= (e) => {
    const changedCity = e.target.value;
    setBillingTown(changedCity);
  };

  const onChangeCountry = (e) => {
    const changedCountry = e.target.value;
    setBillingCountry(changedCountry);
  };
  

  const onChangeCouponCode = (e) => {
    const changedCouponCode = e.target.value;
    setCouponCode(changedCouponCode);
  };


  const submitBillingAddress = (e) => {
    console.log(couponCode);
    e.preventDefault();
    setMessage("");
    setSuccessful(false);
    form.current.validateAll();
    if (checkBtn.current.context._errors.length === 0) {
      orderService.createOrder(userId, FirstName, FamilyName, Username, Password, Email, DateOfBirth, TelephoneNum, StreetAddress, StreetNumber,
        ZipCode, Town, Country, AccountStatus, Role, 
        billingId, cardType, cardNumber, cardName, expirationDate, cardHolderName,
        billingStreetAddress, billingStreetNumber, billingZipCode, billingTown, billingCountry, couponCode);
    }
  };

    return (
      <div className={classes.card}>
      <p className={classes.signSignIn} align="center">Delivery address</p>
                <Form className={classes.SignIn} onSubmit={submitBillingAddress} ref={form}>
                <p className={classes.placeholder} align="center">Street</p>
                <Input id="checkoutStreet" className={classes.passSignIn} required align="center" placeholder="Street" value={billingStreetAddress}
                  onChange={onChangeStreet}></Input>
                  <p  className={classes.placeholder} align="center">House number</p>
                  <Input id="checkoutHouseNum" className={classes.passSignIn} required  align="center" placeholder="House number" value={billingStreetNumber}
                  onChange={onChangeHouseNumber}></Input>
                  <p  className={classes.placeholder} align="center">Zip code</p>
                  <Input id="checkoutZipCode" className={classes.passSignIn} required align="center" placeholder="Zip code" value={billingZipCode}
                  onChange={onChangeZipCode}></Input>
                  <p  className={classes.placeholder} align="center">City</p>
                  <Input id="checkoutCity" className={classes.passSignIn} required align="center" placeholder="City" value={billingTown}
                  onChange={onChangeCity}></Input>
                  <p className={classes.placeholder} align="center">Country</p>
                  <Input id="checkoutCountry" className={classes.passSignIn} required align="center" placeholder="Country" value={billingCountry}
                  onChange={onChangeCountry}></Input>
                  <p className={classes.signSignIn} align="center">Discount</p>
                  <p  className={classes.placeholder} align="center">Coupon code</p>
                  <Input id="checkoutDiscount" className={classes.passSignIn} align="center" placeholder="Coupon code" value={couponCode}
                  onChange={onChangeCouponCode}></Input>
                  <button id="checkoutButton" className={classes.submitBillingAddress} align="center">Save</button>
                  {message && (
            <div>
              <div>
                <p className={classes.placeholder} align="center">{message}</p>
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
                </Form>
                </div> 
    );
}

export default CheckoutBillingAddress;