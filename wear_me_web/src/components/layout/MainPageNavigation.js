import {Link } from 'react-router-dom';
import authHeader from '../../services/auth.service.js';
import classes from '../cssFiles/MainPageNavigation.module.css';
import productService from "../../services/product.service.js";
import React, { useState, useEffect } from 'react';
function LogoutFunction(){
  authHeader.logout();
}

function LoadShoppingCart(){
  if(localStorage.getItem("accessTokens")!=null){
    const username = localStorage.getItem("username");
    productService.getListOfAllItemsInShoppingCart(username);
  }
}

function LoadListOfFavourites(){
  if(localStorage.getItem("accessTokens")!=null){
    const username = localStorage.getItem("username");
    productService.getListOfALlfavouriteItems(username);
  }
}

function MainPageNavigation(){ 

  const [profilePath, setProfilePath] = useState("/user/profile");
  function ProfileFunction(){
      if(localStorage.getItem("username")==null){
          setProfilePath("/user/login");
          window.location.href = "http://localhost:3000/user/login";
      }
      else if((localStorage.getItem("username")!=null)){
          setProfilePath("/user/profile");
      }
  }


    return (
      <div className={classes.Nav}> 
      <header className={classes.headerNav}>
      <Link to='/'><a id={classes.Navlogo}>WEAR ME</a></Link>
<nav className={classes.navNav}>
<ul className={classes.navUl}>
<Link to='/'><li className={classes.navli}><a className={classes.nava} href="#" id={classes.current}>Home</a>
</li></Link>

<li id="clothesNavLi" className={classes.navli}><a className={classes.nava}>Clothes</a>
<div className={classes.dropdowncontent}>
    <li id="womenNavBar"><Link to='/user/clothes/F'>Women</Link></li>
    <li id="menNavBar"><Link to='/user/clothes/M'>Men</Link></li>
  </div> 
</li>
<li id="cartNavLi" className={classes.navli}><a className={classes.nava}>Cart</a>
<div className={classes.dropdowncontent}>
    <li id="shoppingCartNavBar" onClick={LoadShoppingCart}><Link to='/user/shoppingcart'>Shopping cart</Link></li>
    <li id="favNavBar" onClick={LoadListOfFavourites}><Link to='/user/favouriteItems'>Favourites</Link></li>
  </div> 
</li>
<li id="youNavLi" className={classes.navli}><a className={classes.nava}>YOU</a>
<div className={classes.dropdowncontent}>
    <li id="profileNavBar" onClick={ProfileFunction}><Link to={profilePath}>Profile</Link></li>
    <li id="billingInfoNavBar" onClick={ProfileFunction}><Link to='/user/billingInfo'>Billing information</Link></li>
    <li id="youOrdersNavBar" onClick={ProfileFunction}><Link to='/user/orders'>Your orders</Link></li>
    <li id="LogOut" onClick={LogoutFunction}><Link to='/user/login'>Log out</Link></li>
  </div> 
</li>
</ul>
</nav>
</header>
</div>
     );
}

export default MainPageNavigation;