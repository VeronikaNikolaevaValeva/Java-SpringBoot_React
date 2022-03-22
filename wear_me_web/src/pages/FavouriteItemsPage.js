import Layout from "../components/layout/Layout";
import ShoppingCartItem from "../components/layout/ShoppingCartItem";
import classes from "../components/cssFiles/ShoppingCartItem.module.css";
import productService from "../services/product.service";
import userService from "../services/user.service";
import { idText } from "typescript";
import { useState } from "react";

function FavouriteItemsPage(){

    const param = "ADD TO CART";
    const [products, setProducts] = useState("state");
    const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });
    if(products == "state"){
        productService.getListOfALlfavouriteItems(username).then(response => {
            setProducts(JSON.parse(JSON.stringify(response)));
        });
    }
    const totalSum = "00.00"
    if(Array.isArray(products)&&products.length){
        const listItems = products.map((product) =>
        <p><ShoppingCartItem value={[product, param]}/></p>);
        return (
        <Layout>
         <h1>List of favourite items</h1>
         <div>
         <div className={classes.body}>
            <div className={classes.CartContainer}>
            <div className={classes.Header}>
                <h3 className={classes.Heading}>List of favourite items</h3>
            </div>
            <div>{listItems}</div>
                <hr className={classes.hr} /> 
            </div>
         </div>
        </div>
        </Layout>
    );
    }
    else{
    return (
        <Layout>
         <h1>List of favourite items</h1>
         <div>
         <div className={classes.body}>
            <div className={classes.CartContainer}>
            <div className={classes.Header}>
                <h3 className={classes.Heading}>List of favourite items</h3>
            </div>
            <h3 className={classes.EmptyShoppingCart}>List of favourite items is empty</h3>
                <hr className={classes.hr} /> 
           
            </div>
         </div>
        </div>
        </Layout>
    );
    }
    
}

export default FavouriteItemsPage;