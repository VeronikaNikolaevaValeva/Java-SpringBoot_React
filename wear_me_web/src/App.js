import React, {useState, useEffect} from "react";
import axios from "axios";
import {Route, Switch} from 'react-router-dom';
import SignInPage from "./pages/SignInPage"; 
import SignUpPage from "./pages/SignUpPage"; 
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/ProfilePage";
import ClothesPage from "./pages/ClothesPage";
import ShoppingCartPage from "./pages/ShoppingCartPage";
import FavouriteItemsPage from "./pages/FavouriteItemsPage";
import BillingInformation from "./pages/BillingInformation";
import CheckoutPage from "./pages/CheckoutPage";
import PaymentCheckoutPage from "./pages/PaymentCheckoutPage";
import PastOrderPage from "./pages/PastOrderPage";

function App() {
  const path = 'http://localhost:3000';
  return (
    
      <Switch> 
        <Route path='/' exact={true}>
          <HomePage />
        </Route>
        <Route path='/user/login' exact={true}>
          <SignInPage />
        </Route>
        <Route path='/user/signup' exact={true}>
          <SignUpPage />
        </Route>
        <Route path='/user/profile' exact={true}>
          <ProfilePage />
        </Route>
        <Route path='/user/clothes/F' exact={true}>
          <ClothesPage value = {"&FEMALE"}/>
        </Route>
        <Route path='/user/clothes/M' exact={true}>
          <ClothesPage value = {"&MALE"}/>
        </Route>
        <Route path='/user/shoppingcart' exact={true}>
          <ShoppingCartPage />
        </Route>
        <Route path='/user/favouriteItems' exact={true}>
          <FavouriteItemsPage />
        </Route>
        <Route path='/user/billingInfo' exact={true}>
          <BillingInformation />
        </Route>
        <Route path='/user/checkout' exact={true}>
          <CheckoutPage />
        </Route>
        <Route path='/user/payment' exact={true}>
          <PaymentCheckoutPage />
        </Route>
        <Route path='/user/orders' exact={true}>
          <PastOrderPage />
        </Route>
      </Switch>
  );
}

export default App;
