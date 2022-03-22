import {useRef} from 'react';
import { useState } from 'react';
import classes from '../../components/cssFiles/Checkout.module.css';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import billingInformation from '../../services/billing.information';
import userService from '../../services/user.service';


function CheckoutPaymentInfo(){
    const form = useRef();
    const checkBtn = useRef();
    const [username, setUsername] = useState("null");

    const [cardType, setCardType] = useState("type");
    const [cardName, setCardName] = useState("name");
    const [cardNumber, setCardNumber] = useState("number");
    const [cardHolderName, setCardHolderName] = useState("holder name");
    const [expirationDate, setExpirationDate] = useState("expiration date");
    const [existing, setExisting] = useState("yes");
    const [billingInfo, setBillingInfo] = useState("state");
    const [billingId, setBillingId] = useState("")
 
  if(username=="null"){
    userService.getUserByUsername().then(response=>{
        setUsername(JSON.parse(JSON.stringify(response)).username);
   });
}
  if(billingInfo == "state" && username!="null"){
    setBillingId("null");
    setBillingInfo("not null");
    billingInformation.getUsersBillingInformation(username).then(response => {
        setBillingId(JSON.parse(JSON.stringify(response)).id);
        setCardType(JSON.parse(JSON.stringify(response)).cardType);
        setCardName(JSON.parse(JSON.stringify(response)).cardName);
        setCardNumber(JSON.parse(JSON.stringify(response)).cardNumber);
        setCardHolderName(JSON.parse(JSON.stringify(response)).cardHolderName);
        setExpirationDate(JSON.parse(JSON.stringify(response)).expirationDate);
        });
    }

    if(billingId=="null" && billingInfo=="state"){
        setExisting("no");
    }

    return (
        <div className={classes.card2}>
                <p className={classes.signSignIn} align="center">Payment Information</p>
                <Form className={classes.SignIn} ref={form}>
                  <p className={classes.placeholder} align="center">Card type</p>
                  <Input className={classes.passSignIn} required align="center" placeholder="Card type" value={cardType} readOnly = {true}></Input>
                  <p className={classes.placeholder} align="center">Card name</p>
                  <Input className={classes.passSignIn} required align="center" placeholder="Card name" value={cardName} readOnly = {true}></Input>
                  <p className={classes.placeholder} align="center">Card number</p>
                  <Input className={classes.passSignIn} required align="center" placeholder="Card number" value={cardNumber} readOnly = {true}></Input>
                  <p className={classes.placeholder} align="center">Card holder name</p>
                  <Input className={classes.passSignIn} required align="center" placeholder="Card holder name" value={cardHolderName} readOnly = {true}></Input>
                  <p className={classes.placeholder} align="center">Expiration date</p>
                  <Input className={classes.passSignIn} required align="center" placeholder="Expiration date" value={expirationDate} readOnly = {true}></Input>
                </Form>
        </div>
     
    );
}

export default CheckoutPaymentInfo;