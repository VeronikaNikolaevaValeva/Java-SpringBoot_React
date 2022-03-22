import {useRef} from 'react';
import { useState } from 'react';
import classes from '../../components/cssFiles/SignUpPage.module.css';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";



const validEmail = (value) => {
    if (!isEmail(value)) {
      return (
        <div>
             <p className={classes.forgotSignIn} align="center">This is not a valid email.</p>
        </div>
      );
    }
  };
  
function PasswordComponent(){
    const form = useRef();
  const checkBtn = useRef();

  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [repeatNewPassword, setRepeatNewPassword] = useState("");

  const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeCurrentPassword = (e) => {
    const currentPassword = e.target.value;
    setCurrentPassword(currentPassword);
  };

  const onChangeNewPassword = (e) => {
    const newPassword = e.target.value;
    setNewPassword(newPassword);
  };

  const onChangeRepeatNewPassword = (e) => {
    const repeatNewPassword = e.target.value;
    setRepeatNewPassword(repeatNewPassword);
  };


  const handlePasswordChange = (e) => {
    e.preventDefault();

    setMessage("");
    setSuccessful(false);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
    
    }
  };

    return (
        <div>
            <div className={classes.BodySignIn}>
            <div className={classes.mainPassword}>
                <p className={classes.signSignIn} align="center">Password</p>
                <Form className={classes.SignIn} onSubmit={handlePasswordChange} ref={form}>
                <Input className={classes.passSignIn} required type="password" align="center" placeholder="Current password" value={currentPassword}
                  onChange={onChangeCurrentPassword}></Input>
                  <Input className={classes.passSignIn} required type="password" align="center" placeholder="New password" value={newPassword}
                  onChange={onChangeNewPassword}></Input>
                  <Input className={classes.passSignIn} required type="password" align="center" placeholder="Repeat new password" value={repeatNewPassword}
                  onChange={onChangeRepeatNewPassword}></Input>
                    <button className={classes.submitSignIn} align="center">Save</button>
                    
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

export default PasswordComponent;