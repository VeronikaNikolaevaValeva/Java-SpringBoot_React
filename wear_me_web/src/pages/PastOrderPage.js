import Layout from "../components/layout/Layout";
import PastOrdersComponent from "../components/layout/PastOrdersComponent";
import classes from "../components/cssFiles/ShoppingCartItem.module.css";
import productService from "../services/product.service";
import orderService from "../services/order.service";
import userService from "../services/user.service";
import { idText } from "typescript";
import { useState } from "react";

function PastOrderPage(){

    const [products, setProducts] = useState("state");
    const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });
    if(products == "state"){
        orderService.getAllProductsOrders(username).then(response => {
            setProducts(JSON.parse(JSON.stringify(response.data)));
        });
    }
    
    if(Array.isArray(products)&&products.length){
        const listItems = products.map((order) =>
        <p><PastOrdersComponent value={order}/></p>);
        return (
        <Layout>
         <h1>Past orders</h1>
         <div>
         <div className={classes.body}>
            <div className={classes.CartContainer}>
            <div className={classes.Header}>
                <h3 className={classes.Heading}>Past orders</h3>
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
         <h1>Past orders</h1>
         <div>
         <div className={classes.body}>
            <div className={classes.CartContainer}>
            <div className={classes.Header}>
                <h3 className={classes.Heading}>Past orders</h3>
            </div>
            <h3 className={classes.EmptyShoppingCart}>Your haven't made any orders yet!</h3>
                <hr className={classes.hr} /> 
           
            </div>
         </div>
        </div>
        </Layout>
    );
    }
    
}

export default PastOrderPage;