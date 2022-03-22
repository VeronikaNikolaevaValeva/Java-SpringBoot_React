import NextPageButtons from './NextpageButtons';
import ClothesPageComponent from './ClothesPageComponent';
import classes from "../cssFiles/nextPageButton.module.css";
import classes1 from "../cssFiles/CategoriesNavBar.module.css";
import productService from '../../services/product.service';
import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import authHeader from '../../services/auth-header';
import userService from '../../services/user.service';
const ENDPOINT = "http://localhost:8080/webSocket";

function ClothesPageBody(props){
    const [page, setPage] = useState(1);
    const [category, setCategory] = useState("SNEAKERS");
    const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });

    function SelectCategory(changeCat){
        if(category!=changeCat){
            if(props.value === ""){
                productService.getListOfNineProducts("1", changeCat); 
             }
             else if(props.value === "&FEMALE"){
                productService.getListOfProductByGender("1", "FEMALE", changeCat); 
             }
             else if(props.value === "&MALE"){
                productService.getListOfProductByGender("1", "MALE", changeCat); 
             } 
            sendMessage(changeCat);
            setCategory(changeCat);
            setPage(1);
        }
    }

    function NextPageFunction(){
        if(props.value === ""){
           productService.getListOfNineProducts(page+1, category); 
        }
        else if(props.value === "&FEMALE"){
            productService.getListOfProductByGender(page+1, "FEMALE", category); 
         }
         else if(props.value === "&MALE"){
            productService.getListOfProductByGender(page+1, "MALE", category); 
         }
        setPage(page+1);
    }
    function PrevPageFunction(){
        if(page>1){
            if(props.value === ""){
                productService.getListOfNineProducts(page-1, category); 
             }
             else if(props.value === "&FEMALE"){
                productService.getListOfProductByGender(page-1, "FEMALE", category); 
             }
             else if(props.value === "&MALE"){
                productService.getListOfProductByGender(page-1, "MALE", category); 
             }
            setPage(page-1);
        }
    }
    const [stompClient, setStompClient] = useState(null);
    useEffect(() => {
            const socket = SockJS(ENDPOINT);
            const stompClient = Stomp.over(socket);
            const subscriptionURL = '/topic/discounts/' + authHeader();
            stompClient.connect({}, () => {
              stompClient.subscribe(subscriptionURL, (data) => {
                const result=  JSON.parse(JSON.stringify(data)).body;
                  alert(result);
              });
            });
            setStompClient(stompClient);
          }, []);

    function sendMessage(category) {
        stompClient.send("/webSocket/header", {}, authHeader());
        stompClient.send("/webSocket/hey", {}, category);
    };
        return (
            <div>
                <ul className={classes1.ul1}>
                    <button id="shirtsCat" onClick={() => SelectCategory('SHIRTS')} className={classes1.button} ><li><a className={classes1.lia}>Shirts</a></li></button>
                    <button id="tshirtsCat" onClick={() => SelectCategory('TSHIRTS')} className={classes1.button}><li><a className={classes1.lia}>T-Shirts</a></li></button>
                    <button id="pantsCat" onClick={() => SelectCategory('PANTS')} className={classes1.button}><li><a className={classes1.lia}>Pants</a></li></button>
                    <button id="jacketsCat" onClick={() => SelectCategory('JACKETS')} className={classes1.button}><li><a className={classes1.lia}>Jackets</a></li></button>
                    <button id="skirtsCat" onClick={() => SelectCategory('SKIRTS')} className={classes1.button}><li><a className={classes1.lia}>Skirts</a></li></button>
                    <button id="dressesCat" onClick={() => SelectCategory('DRESSES')} className={classes1.button}><li><a className={classes1.lia}>Dresses</a></li></button>
                    <button id="swimsuitsCat" onClick={() => SelectCategory('SWIMSUITS')} className={classes1.button}><li><a className={classes1.lia}>Swimsuits</a></li></button>
                    <button id="sweatersCat" onClick={() => SelectCategory('SWEATERS')} className={classes1.button}><li><a className={classes1.lia}>Sweaters</a></li></button>
                </ul>
                <ul className={classes1.ul2}>
                    <button id="sandalsCat" onClick={() => SelectCategory('SANDALS')} className={classes1.button}><li><a className={classes1.lia}>Sandals</a></li></button>
                    <button id="sneakersCat" onClick={() => SelectCategory('SNEAKERS')} className={classes1.button}><li><a className={classes1.lia}>Sneakers</a></li></button>
                    <button id="formalShoesCat" onClick={() => SelectCategory('FORMALSHOES')} className={classes1.button}><li><a className={classes1.lia}>Formal shoes</a></li></button>
                    <button id="bootsCat" onClick={() => SelectCategory('BOOTS')} className={classes1.button}><li><a className={classes1.lia}>Boots</a></li></button>
                    <button id="slipperssCat" onClick={() => SelectCategory('SLIPPERS')} className={classes1.button}><li><a className={classes1.lia}>Slippers</a></li></button>
                    <button id="flipflopssCat" onClick={() => SelectCategory('FLIPFLOPS')} className={classes1.button}><li><a className={classes1.lia}>Flip flops</a></li></button>
                </ul>
                <br />
                <h3 className={classes1.h3}>{category}</h3>
                <ClothesPageComponent value = {[0, page, props.value, category]}/>
                <ClothesPageComponent value = {[3, page, props.value, category]}/>
                <ClothesPageComponent value = {[6, page, props.value, category]}/>
                <div className={classes.formatPosition}>
                    <br />
                <button onClick = {PrevPageFunction} className={classes.formatbutton}><a className={classes.format} className={classes.previousround}>&#8249;</a></button>
                <button className={classes.formatPagebutton}><a className={classes.format} className={classes.previousround}>{page}</a></button>
                <button onClick = {NextPageFunction} className={classes.formatbutton}><a className={classes.format} className={classes.nextround}>&#8250;</a></button>
                <br /> <br /> <br />
                </div>
            </div>
        );
    
}
export default ClothesPageBody;