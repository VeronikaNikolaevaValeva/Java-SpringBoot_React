import Header from "../components/layout/Header";

import {useRef} from 'react';
import { useState } from 'react';
import classes from '../components/cssFiles/SignUpPage.module.css';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

import AuthService from "../services/auth.service";


const validEmail = (value) => {
    if (!isEmail(value)) {
      return (
        <div>
             <p className={classes.forgotSignIn} align="center">This is not a valid email.</p>
        </div>
      );
    }
  };
  
function SignInPage(props){
    const form = useRef();
  const checkBtn = useRef();

  const [firstName, setFirstName] = useState("");
  const [familyName, setFamilyName] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [telephoneNum, setTelephoneNum] = useState("");
  const [streetAddress, setStreetAddress] = useState("");
  const [streetNumber, setStreetNumber] = useState("");
  const [zipCode, setZipCode] = useState("");
  const [town, setTown] = useState("");
  const [country, setCountry] = useState("");
  const [accountStatus, setAccountStatus] = useState("ACTIVE");
  const [role, setRole] = useState("CUSTOMER");


  const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeFirstName = (e) => {
    const firstName = e.target.value;
    setFirstName(firstName);
  };

  const onChangeFamilyName = (e) => {
    const familyName = e.target.value;
    setFamilyName(familyName);
  };

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const onChangeEmail = (e) => {
    const email = e.target.value;
    setEmail(email);
  };

  const onChangeDateOfBirth = (e) => {
    const dateOfBirth = e.target.value;
    setDateOfBirth(dateOfBirth);
  };
  const onChangeTelephoneNum = (e) => {
    const telephoneNum = e.target.value;
    setTelephoneNum(telephoneNum);
  };
  const onChangeStreetAddress = (e) => {
    const streetAddress = e.target.value;
    setStreetAddress(streetAddress);
  };
  const onChangeStreetNumber = (e) => {
    const streetNumber = e.target.value;
    setStreetNumber(streetNumber);
  };
  const onChangeZipCode = (e) => {
    const zipCode = e.target.value;
    setZipCode(zipCode);
  };
  const onChangeTown = (e) => {
    const town = e.target.value;
    setTown(town);
  };
  const onChangeCountry = (e) => {
    const country = e.target.value;
    setCountry(country);
  };

  function homePage(){
      
      window.location.href = "http://localhost:3000";
   }
  const handleRegister = (e) => {
    e.preventDefault();

    setMessage("");
    setSuccessful(false);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      AuthService.register(firstName, familyName, username, password, email, dateOfBirth, telephoneNum,
        streetAddress, streetNumber, zipCode, town, country, accountStatus, role).then(
        (response) => {
          setMessage(response.data.message);
          setSuccessful(true);
          homePage();
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
  };

    return (
        <div>
            <Header />
            <div className={classes.BodySignIn}>
            <div className={classes.mainSignIn}>
                <p className={classes.signSignIn} align="center">Sign up</p>
                <Form className={classes.SignIn} onSubmit={handleRegister} ref={form}>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="First name" value={firstName}
                  onChange={onChangeFirstName}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Family name" value={familyName}
                  onChange={onChangeFamilyName}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Username" value={username}
                  onChange={onChangeUsername}></Input>
                <Input className={classes.passSignIn} required type="password" align="center" placeholder="Password" value={password}
                  onChange={onChangePassword}></Input>
                <Input className={classes.unSignIn} required type="email" align="center" placeholder="E-mail" value={email}
                  onChange={onChangeEmail}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Date of birth (yyyy-MM-dd)" value={dateOfBirth}
                  onChange={onChangeDateOfBirth}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Telephone number" value={telephoneNum}
                  onChange={onChangeTelephoneNum}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Street address" value={streetAddress}
                  onChange={onChangeStreetAddress}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Street number" value={streetNumber}
                  onChange={onChangeStreetNumber}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Zip code" value={zipCode}
                  onChange={onChangeZipCode}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Town" value={town}
                  onChange={onChangeTown}></Input>
                <Input className={classes.unSignIn} required type="text" align="center" placeholder="Country" value={country}
                  onChange={onChangeCountry}></Input>
                    <button className={classes.submitSignIn} align="center">Sing in</button>
                    
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

export default SignInPage;