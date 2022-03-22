import { useState } from 'react';
import classes from "../cssFiles/ShoppingCartItem.module.css";
import productService from "../../services/product.service";
import userService from '../../services/user.service';
function PastOrdersComponent(props){ 

    const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });
        return (
            <div>
                <div className={classes.CartItems}>
                    <div className={classes.imageBox}>
                        <img src={props.value.product.photoURL} className={classes.photo}></img>
                    </div>
                    <div className={classes.about}>
                        <h1 className={classes.ordertitle}>{props.value.product.name}</h1>
                        <h2 className={classes.ordertitle}>Order number: {props.value.orderInformation.orderNumber}</h2>
                        <h3 className={classes.ordersubtitle}>Delivery address: {props.value.orderInformation.billingAddress}</h3>
                        <h3 className={classes.ordersubtitle}>Total price: {props.value.orderInformation.totalPrice}</h3>
                        <h3 className={classes.ordersubtitle}>Payed via: {props.value.orderInformation.paymentInformation.cardHolderName} - {props.value.orderInformation.paymentInformation.cardName} {props.value.orderInformation.paymentInformation.cardType}</h3>
                    </div>
                    <div className={classes.counter}></div>
                    <div className={classes.prices}></div>
                    <div className={classes.PricesBox}>
                        <div className={classes.amount}>{props.value.orderInformation.totalPrice} €</div>
                    </div>
                </div>
            </div>
        );
    
}

export default PastOrdersComponent;