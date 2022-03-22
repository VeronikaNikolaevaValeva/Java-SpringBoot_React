import {useRef} from 'react';
import {useEffect} from 'react';
import { useState } from 'react';
import classes from '../../components/cssFiles/SignUpPage.module.css';
import userService from '../../services/user.service';
import authService from '../../services/auth.service';
import getCustomer from '../../services/getCustomer';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

function ProfileComponent(){

  const form = useRef();
  const checkBtn = useRef();
  const [FirstName, setFirstName] = useState(" ");
  const [FamilyName, setFamilyName] = useState(" ");
  const [Username, setUsername] = useState(" ");
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

  
  userService.getUserByUsername().then(response=>{
   if(FirstName==" " && response!= null){
     setFirstName(JSON.parse(JSON.stringify(response)).firstName);
     setFamilyName(JSON.parse(JSON.stringify(response)).familyName);
     setUsername(JSON.parse(JSON.stringify(response)).username);
     setEmail(JSON.parse(JSON.stringify(response)).email);
     setDateOfBirth(JSON.parse(JSON.stringify(response)).dateOfBirth);
     setTelephoneNum(JSON.parse(JSON.stringify(response)).telephoneNumber);
     setStreetAddress(JSON.parse(JSON.stringify(response)).streetAddress);
     setStreetNumber(JSON.parse(JSON.stringify(response)).streetNumber);
     setZipCode(JSON.parse(JSON.stringify(response)).zipCode);
     setTown(JSON.parse(JSON.stringify(response)).town);
     setCountry(JSON.parse(JSON.stringify(response)).country);
     setAccountStatus(JSON.parse(JSON.stringify(response)).AccountStatus);
     setRole(JSON.parse(JSON.stringify(response)).type);
   }
 });
  const [message, setMessage] = useState("");

  const onChangeFirstName = (e) => {
    const FirstName = e.target.value;
    setFirstName(FirstName);
  };
  const onChangeFamilyName = (e) => {
    const FamilyName = e.target.value;
    setFamilyName(FamilyName);
  };
  const onChangeUsername = (e) => {
    const Username = e.target.value;
    setUsername(Username);
  };
  const onChangeEmail = (e) => {
    const Email = e.target.value;
    setEmail(Email);
  };
  const onChangeDateOfBirth = (e) => {
    const DateOfBirth = e.target.value;
    setDateOfBirth(DateOfBirth);
  };
  const onChangeTelephoneNum = (e) => {
    const TelephoneNum = e.target.value;
    setTelephoneNum(TelephoneNum);
  };
  const onChangeStreetAddress = (e) => {
    const StreetAddress = e.target.value;
    setStreetAddress(StreetAddress);
  };
  const onChangeStreetNumber = (e) => {
    const StreetNumber = e.target.value;
    setStreetNumber(StreetNumber);
  };
  const onChangeZipCode = (e) => {
    const ZipCode = e.target.value;
    setZipCode(ZipCode);
  };
  const onChangeTown = (e) => {
    const Town = e.target.value;
    setTown(Town);
  };
  const onChangeCountry = (e) => {
    const Country = e.target.value;
    setCountry(Country);
  };

  const handleSaveChanges = (e) => {
    e.preventDefault();
    setMessage("");
    form.current.validateAll();
    if (checkBtn.current.context._errors.length === 0) {
      userService.updateInfo(FirstName, FamilyName, Username, Email, DateOfBirth, TelephoneNum,
        StreetAddress, StreetNumber, ZipCode, Town, Country);
    }
  };

    return (
        <div>
            <div className={classes.BodySignIn}>
            <div className={classes.mainProfile}>
                <p className={classes.signSignIn} align="center">Personal information</p>
                <Form className={classes.SignIn} onSubmit={handleSaveChanges} ref={form}>
                <p className={classes.placeholder} align="center">First name</p>
                <Input className={classes.unSignIn} required type="text" align="center" value={FirstName}
                  onChange={onChangeFirstName}></Input>
                  <p className={classes.placeholder} align="center">Fiamily name</p>
                <Input className={classes.unSignIn} required type="text" align="center" value={FamilyName}
                  onChange={onChangeFamilyName}></Input>
                  <p className={classes.placeholder} align="center">Username</p>
                <Input className={classes.unSignIn} required type="text" align="center" value={Username}
                  onChange={onChangeUsername}></Input>
                  <p className={classes.placeholder} align="center">E-mail</p>
                <Input className={classes.unSignIn} required type="email" align="center" value={Email}
                  onChange={onChangeEmail}></Input>
                  <p className={classes.placeholder} align="center">Date of birth</p>
                <Input className={classes.unSignIn} required type="text" align="center" value={DateOfBirth}
                  onChange={onChangeDateOfBirth}></Input>
                  <p id="profileTelephoneNum" className={classes.placeholder} align="center">Telephone number</p>
                <Input className={classes.unSignIn} required type="text" align="center"  value={TelephoneNum}
                  onChange={onChangeTelephoneNum}></Input>
                  <p className={classes.placeholder} align="center">Street address</p>
                <Input className={classes.unSignIn} required type="text" align="center" value={StreetAddress}
                  onChange={onChangeStreetAddress}></Input>
                  <p className={classes.placeholder} align="center">Street number</p>
                <Input className={classes.unSignIn} required type="text" align="center"  value={StreetNumber}
                  onChange={onChangeStreetNumber}></Input>
                  <p className={classes.placeholder} align="center">Zip Code</p>
                <Input className={classes.unSignIn} required type="text" align="center" value={ZipCode}
                  onChange={onChangeZipCode}></Input>
                  <p className={classes.placeholder} align="center">Town</p>
                <Input className={classes.unSignIn} required type="text" align="center"  value={Town}
                  onChange={onChangeTown}></Input>
                  <p className={classes.placeholder} align="center">Country</p>
                <Input className={classes.unSignIn} required type="text" align="center"  value={Country}
                  onChange={onChangeCountry}></Input>
                    <button  id="ProfileSaveButton" className={classes.submitSignIn} align="center">Save</button>
                    
                    {message && (
            <div>
              <div>
                <p className={classes.forgotSignIn} align="center">{message}</p>
              </div>
            </div>
          )}
          <CheckButton ref={checkBtn} />

                </Form>
            </div>  
        </div>
        </div>
    );
}

export default ProfileComponent;