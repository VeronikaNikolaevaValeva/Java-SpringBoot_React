import Layout from "../components/layout/Layout";
import ShoppingCartItem from "../components/layout/ShoppingCartItem";
import classes from "../components/cssFiles/ShoppingCartItem.module.css";
import productService from "../services/product.service";
import userService from "../services/user.service";
import { idText } from "typescript";
import { useState } from "react";
import {Link } from 'react-router-dom';

function ShoppingCartPage(){

    const param = "REMOVE";
    const [products, setProducts] = useState("state");
    const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });
    if(products == "state"){
        productService.getListOfAllItemsInShoppingCart(username).then(response => {
            setProducts(JSON.parse(JSON.stringify(response)));
        });
    }

    function Checkout(){
        if(username!="null"){
            window.location.href = "http://localhost:3000/user/checkout";
        }
        else if(username=="null"){
            window.location.href = "http://localhost:3000/user/login";
        }
    }
    
    const totalSum = "00.00"
    if(Array.isArray(products)&&products.length){
        const listItems = products.map((product) =>
        <p><ShoppingCartItem value={[product, param]}/></p>);
        return (
        <Layout>
         <h1>Shopping cart</h1>
         <div>
         <div className={classes.body}>
            <div className={classes.CartContainer}>
            <div className={classes.Header}>
                <h3 className={classes.Heading}>Shopping Cart</h3>
            </div>

            <div>{listItems}</div>

                <hr className={classes.hr} /> 
            <div className={classes.checkout}>
                <div className={classes.total}>
                    <div>
                        <div className={classes.subtotal}>Sub-Total</div>
                    </div>
                    <div className={classes.totalamount}>{totalSum} €</div>
                </div>
                <button id="proceedToCheckoutButton" onClick={Checkout} className={classes.checkoutbutton}><a>Check out</a></button>
            </div>
            </div>
         </div>
        </div>
        </Layout>
    );
    }
    else{
    return (
        <Layout>
         <h1>Shopping cart</h1>
         <div>
         <div className={classes.body}>
            <div className={classes.CartContainer}>
            <div className={classes.Header}>
                <h3 className={classes.Heading}>Shopping Cart</h3>
            </div>
            <h3 className={classes.EmptyShoppingCart}>Shopping cart is empty</h3>
                <hr className={classes.hr} /> 
            <div className={classes.checkout}>
                <div className={classes.total}>
                    <div>
                        <div className={classes.subtotal}>Sub-Total</div>
                    </div>
                    <div className={classes.totalamount}>{totalSum} €</div>
                </div>
                <button id="proceedToCheckoutButton" className={classes.checkoutbutton}>Check out</button>
            </div>
            </div>
         </div>
        </div>
        </Layout>
    );
    }
    
}

export default ShoppingCartPage;