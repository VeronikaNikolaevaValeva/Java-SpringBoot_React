import {useRef} from 'react';
import { useState } from 'react';
import classes from '../../components/cssFiles/SignUpPage.module.css';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import billingInformation from '../../services/billing.information';
import userService from '../../services/user.service';

function BillingInformationComponent(props){
    const form = useRef();
  const checkBtn = useRef();
  const [billingInfo, setBillingInfo] = useState("state");
  const [username, setUsername] = useState("null")
  const [billingId, setBillingId] = useState("")
  const [exists, setExists] = useState("null")
  if(username=="null"){
      userService.getUserByUsername().then(response=>{
        setUsername(JSON.parse(JSON.stringify(response)).username);
    });
  }
  
  const [cardType, setCardType] = useState("");
  const [cardName, setCardName] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [cardHolderName, setCardHolderName] = useState("");
  const [expirationDate, setExpirationDate] = useState("");

  const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState("");

  if(billingInfo == "state" && username!="null"){
    setBillingId("null");
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
        setBillingInfo("notExisting");
    }

  const onChangeCardType = (e) => {
    const changedCardType = e.target.value;
    setCardType(changedCardType);
  };

  const onChangeCardName = (e) => {
    const changedCardName = e.target.value;
    setCardName(changedCardName);
  };

  const onChangeCardNumber = (e) => {
    const changedCardNumber = e.target.value;
    setCardNumber(changedCardNumber);
  };

  const onChangeCardHolderName= (e) => {
    const changedCardHolderName = e.target.value;
    setCardHolderName(changedCardHolderName);
  };

  const onChangeExpirationDate = (e) => {
    const changedExpirationDate = e.target.value;
    setExpirationDate(changedExpirationDate);
  };
  
  const handleInfoChanged = (e) => {
    e.preventDefault();

    setMessage("");
    setSuccessful(false);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
        if(billingId=="null"){
            billingInformation.addPaymentInformation(username, cardType, cardNumber, cardName, cardHolderName, expirationDate).then(
                (response) => {
                    setMessage(JSON.stringify(response.data));
                    setSuccessful(true);
                  },
                  (error) => {
                    const resMessage =
                      (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                      error.message ||
                      error.toString();
                    setMessage(resMessage);
                    setSuccessful(false);
                  }
            );
        }
        else{
            billingInformation.updatePaymentInfo(username, cardType, cardNumber, cardName, cardHolderName, expirationDate).then(
                (response) => {
                    setMessage(JSON.stringify(response.data));
                    setSuccessful(true);
                  },
                  (error) => {
                    const resMessage =
                      (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                      error.message ||
                      error.toString();
                    setMessage(resMessage);
                    setSuccessful(false);
                  }
            );
        }
    }

  };

    return (
        <div>
            <div className={classes.BodySignIn}>
            <div className={classes.mainBillinginfo}>
                <p className={classes.signSignIn} align="center">Billing information</p>
                <Form className={classes.SignIn} onSubmit={handleInfoChanged} ref={form}>
                <p className={classes.placeholder} align="center">Card type</p>
                <Input className={classes.passSignIn} required align="center" placeholder="Card type" value={cardType}
                  onChange={onChangeCardType}></Input>
                  <p className={classes.placeholder} align="center">Card name</p>
                  <Input className={classes.passSignIn} required  align="center" placeholder="Card name" value={cardName}
                  onChange={onChangeCardName}></Input>
                  <p className={classes.placeholder} align="center">Card number</p>
                  <Input className={classes.passSignIn} required align="center" placeholder="Card number" value={cardNumber}
                  onChange={onChangeCardNumber}></Input>
                  <p className={classes.placeholder} align="center">Card holder name</p>
                  <Input className={classes.passSignIn} required align="center" placeholder="Card holder name" value={cardHolderName}
                  onChange={onChangeCardHolderName}></Input>
                  <p className={classes.placeholder} align="center">Expiration date</p>
                  <Input className={classes.passSignIn} required align="center" placeholder="Expiration date" value={expirationDate}
                  onChange={onChangeExpirationDate}></Input>
                    <button className={classes.submitBillingInfo} align="center">Save</button>
                    
                    {message && (
            <div>
              <div>
                <p className={classes.forgotSignIn} align="center">{message}</p>
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
                </Form>
            </div>  
        </div>
        </div>
    );
}

export default BillingInformationComponent;