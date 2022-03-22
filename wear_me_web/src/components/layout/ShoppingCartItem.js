import { useState } from 'react';
import classes from "../cssFiles/ShoppingCartItem.module.css";
import productService from "../../services/product.service";
import userService from '../../services/user.service';
function ShoppingCartItem(props){ 

    const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });
    function RemoveItemFromShoppingCart(){
            productService.deleteItemFromShoppingCart(props.value[0].cartId);
            window.location.reload();
    }
    function RemoveItemFromListOfFavourites(){
        productService.deleteItemFromListOfFavourite(props.value[0].id);
        window.location.reload();
    }
    function AddToShoppingCart(productID){
        if(username!="null"){
			productService.addProductToShoppingCart(productID, username);
			productService.getListOfAllItemsInShoppingCart(username);
		}
    }
    function AddToFavourites(productID){
        if(username!="null"){
			productService.addProductToFavouritesItem(productID, username);
			productService.getListOfALlfavouriteItems(username);
		}
    }

    if(props.value[1] == "ADD TO CART"){
        return (
            <div>
                <div className={classes.CartItems}>
                    <div className={classes.imageBox}>
                        <img src={props.value[0].product.photoURL} className={classes.photo}></img>
                    </div>
                    <div className={classes.about}>
                        <h1 className={classes.title}>{props.value[0].product.name}</h1>
                        <h3 className={classes.subtitle}>{props.value[0].product.description}</h3>
                    </div>
                    <div className={classes.counter}></div>
                    <div className={classes.prices}></div>
                    <div className={classes.PricesBox}>
                        <div className={classes.amount}>{props.value[0].product.salesPrice} €</div>
                        <button id="addToCart" onClick={() => AddToShoppingCart(props.value[0].product.id)} className={classes.remove}><a>{props.value[1]}</a></button>
                        <button id="cartRemove" onClick={RemoveItemFromListOfFavourites} className={classes.remove}><a>REMOVE</a></button>
                    </div>
                </div>
            </div>
        );
    }
    else{
        return (
            <div>
                <div className={classes.CartItems}>
                    <div className={classes.imageBox}>
                        <img src={props.value[0].product.photoURL} className={classes.photo}></img>
                    </div>
                    <div className={classes.about}>
                        <h1 className={classes.title}>{props.value[0].product.name}</h1>
                        <h3 className={classes.subtitle}>{props.value[0].product.description}</h3>
                    </div>
                    <div className={classes.counter}></div>
                    <div className={classes.prices}></div>
                    <div className={classes.PricesBox}>
                        <div className={classes.amount}>{props.value[0].product.salesPrice} €</div>
                        <button id="addToFav" onClick={() => AddToFavourites(props.value[0].product.id)} className={classes.remove}><a>SAVE FOR LATER</a></button>
                        <button id="favRemove" onClick={RemoveItemFromShoppingCart} className={classes.remove}><a>{props.value[1]}</a></button>
                    </div>
                </div>
            </div>
        );
    }

    
}

export default ShoppingCartItem;