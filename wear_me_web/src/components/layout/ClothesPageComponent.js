import classes from '../../components/cssFiles/HomePageComponent.module.css';
import productService from '../../services/product.service';
import userService from '../../services/user.service';
import React, { useState, useEffect } from 'react';
function ClothesPageComponent(props){
	
	const name = props.value[1] + props.value[2] + props.value[3];
	const products = JSON.parse(localStorage.getItem(name));
	const [username, setUsername] = useState("null")
    userService.getUserByUsername().then(response=>{
        if(username=="null"){
            setUsername(JSON.parse(JSON.stringify(response)).username);
        }
    });
	
	function AddToCart(productID){
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


if (Array.isArray(products)&&products.length){
	const pr = [products[props.value[0]], products[props.value[0]+1], products[props.value[0]+2]];

	return (
		<div>
		<div className={classes.body}>
			<article className={classes.cards}>
				<article className={classes.card}>
				<img className={classes.cardimage} src={pr[0].photoURL} alt="image" />
				<div className={classes.cardcontent}>
					<h3>{pr[0].name} {pr[0].model}</h3>
					<p>{pr[0].brand}</p>
					<p>{pr[0].Price}</p>
					<button id="addProd1ToCart" onClick={() => AddToCart(pr[0].id)} className={classes.addToBasket}>Add to cart</button>
					<button id="addProd1ToFav" onClick={() => AddToFavourites(pr[0].id)} className={classes.addToFavourites}>Save for later</button>
				</div>
				<footer className={classes.cardfooter}>
				</footer>
				</article>
				<article className={classes.card}>
				<img className={classes.cardimage} src={pr[1].photoURL} alt="image" />
				<div className={classes.cardcontent}>
				<h3>{pr[1].name} {pr[1].model}</h3>
					<p>{pr[1].brand}</p>
					<p>{pr[1].Price}</p>
					<button id="addProd2ToCart" onClick={() => AddToCart(pr[1].id)} className={classes.addToBasket}>Add to cart</button>
					<button id="addProd2ToFav" onClick={() => AddToFavourites(pr[1].id)} className={classes.addToFavourites}>Save for later</button>
				</div>
				<footer className={classes.cardfooter}>
				</footer>
				</article> 
				<article className={classes.card}>
				<img className={classes.cardimage} src={pr[2].photoURL} alt="image" />
				<div className={classes.cardcontent}>
				<h3>{pr[2].name} {pr[2].model}</h3>
					<p>{pr[2].brand}</p>
					<p>{pr[2].Price}</p>
					<button id="addProd3ToCart" onClick={() => AddToCart(pr[2].id)} className={classes.addToBasket}>Add to cart</button>
					<button id="addProd3ToFav" onClick={() => AddToFavourites(pr[2].id)} className={classes.addToFavourites}>Save for later</button>
				</div>
				<footer className={classes.cardfooter}>
				</footer>
				</article>  
			</article> 
			</div>
	 </div>
	 
	);
}
else{
	return (
		<div>
			<h1 className={classes.commingSoon}>We appreciate your interest in this category. Sadly, this is not available at the moment.</h1>
		</div>
	);
}
	    
}
export default ClothesPageComponent;