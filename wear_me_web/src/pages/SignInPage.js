import Header from '../components/layout/Header';
import {useRef} from 'react';
import {Link } from 'react-router-dom';
import { useState } from 'react';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import classes from '../components/cssFiles/SignInPage.module.css';
import AuthService from "../services/auth.service";

if(localStorage.getItem("user")!=null){
  AuthService.logout();
  }


function SignInPage(){
    const form = useRef();
    const checkBtn = useRef();
  
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");
  
    const onChangeUsername = (e) => {
      const username = e.target.value;
      setUsername(username);
    };
  
    const onChangePassword = (e) => {
      const password = e.target.value;
      setPassword(password);
    };
  
    const handleLogin = (e) => {
      e.preventDefault();
      setMessage("");
      setLoading(true);
  
      form.current.validateAll();
  
     function homePage(){
        window.location.href = "http://localhost:3000";
     }

      if (checkBtn.current.context._errors.length === 0) {
        AuthService.login(username, password).then(
          () => {
            localStorage.setItem("username", username);
            if(localStorage.getItem("accessTokens")!=null){
              homePage();
            }
            else{
              setLoading(false);
              setMessage("Wrong username or password.");
            }
          },
          (error) => {
            const resMessage =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
  
            setLoading(false);
            setMessage(resMessage);
          }
        );
      } else {
        setLoading(false);
      }
    };
    return (
        <div>
            <Header />
            <div className={classes.BodySignIn}>
            <div className={classes.mainSignIn}>
                <p id="signInSign" className={classes.signSignIn} align="center">Sign in</p>
                <Form className={classes.SignIn} onSubmit={handleLogin} ref={form}>
                <Input id="usernameInput" className={classes.unSignIn} type="text" align="center" placeholder="Username" onChange={onChangeUsername}
              required></Input>
                <Input id="passwordInput" className={classes.passSignIn} type="password" align="center" placeholder="Password" onChange={onChangePassword}
              required></Input>
                    <button  className={classes.submitSignIn} align="center" disabled={loading}>
              {loading && (
                <span id="signInButton" className="spinner-border spinner-border-sm"></span>)}Sign in</button>
                    <p className={classes.forgotSignIn} align="center"><a className={classes.SignIn}>Forgot password</a></p> 
                    <Link to='/user/signup'><button className={classes.submitSignUp} align="center">Create account</button></Link>
                    {message && (
            <div className="form-group">
              <div><p className={classes.forgotSignIn} align="center">{message}</p>
              
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