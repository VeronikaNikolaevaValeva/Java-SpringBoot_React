import login from "../integration/login_test.js";
import getUser from "../integration/getUser_test.js";
import forceClick from "../integration/forceClick.js";

describe("renders the home, checkout and shopping cart page ", () => {

    it('womens and mens clothes', () => {
        cy.visit('/')

        cy.get('#womenNavBar > a').forceClick();
        cy.get('#menNavBar > a').forceClick();
        cy.get('#MainPageNavigation_Navlogo__2-2el').forceClick();
    })

    it('proceed to checkout', () => {
        cy.visit('user/login')
        cy.get('#usernameInput').clear();
        cy.get('#usernameInput').type('veronika');
        cy.get('#passwordInput').clear();
        cy.get('#passwordInput').type('veronika');
        cy.get('.SignInPage_submitSignIn__2YZBq').click();
        cy.get('#shoppingCartNavBar > a').forceClick();
        cy.get('#proceedToCheckoutButton').forceClick();
    })
    
})