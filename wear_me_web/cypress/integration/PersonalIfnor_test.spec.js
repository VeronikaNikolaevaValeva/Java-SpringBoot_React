import login from "../integration/login_test.js";
import getUser from "../integration/getUser_test.js";
import forceClick from "../integration/forceClick.js";
describe("renders the profile page and billing Info page", () => {
  
    it('logging in to my profile', () => {
        cy.visit('user/login')
        cy.get('#usernameInput').clear();
        cy.get('#usernameInput').type('veronika');
        cy.get('#passwordInput').clear();
        cy.get('#passwordInput').type('veronika');
        cy.get('.SignInPage_submitSignIn__2YZBq').click();
        cy.get('#profileNavBar > a').forceClick();
    })

    it('chnaging my profile info', () => {
        cy.visit('user/login')
        cy.get('#usernameInput').clear();
        cy.get('#usernameInput').type('veronika');
        cy.get('#passwordInput').clear();
        cy.get('#passwordInput').type('veronika');
        cy.get('.SignInPage_submitSignIn__2YZBq').click();
        cy.get('#profileNavBar > a').forceClick();
        cy.get(':nth-child(12) > .SignUpPage_unSignIn__2ptR0').clear();
        cy.get(':nth-child(12) > .SignUpPage_unSignIn__2ptR0').type('+310630319446');
        cy.get('#ProfileSaveButton').click();
        cy.get('#MainPageNavigation_Navlogo__2-2el').click();
        cy.get('#profileNavBar > a').forceClick();
    })

    it('logging in to my billing info', () => {
        cy.visit('user/login')
        cy.get('#usernameInput').clear();
        cy.get('#usernameInput').type('veronika');
        cy.get('#passwordInput').clear();
        cy.get('#passwordInput').type('veronika');
        cy.get('.SignInPage_submitSignIn__2YZBq').click();
        cy.get('#billingInfoNavBar > a').forceClick();
    })

    it('changing my billing info', () => {
        cy.visit('user/login')
        cy.get('#usernameInput').clear();
        cy.get('#usernameInput').type('veronika');
        cy.get('#passwordInput').clear();
        cy.get('#passwordInput').type('veronika');
        cy.get('.SignInPage_submitSignIn__2YZBq').click();
        cy.get('#billingInfoNavBar > a').forceClick();
        cy.get(':nth-child(6) > .SignUpPage_passSignIn__-7MU1').click();
        cy.get(':nth-child(6) > .SignUpPage_passSignIn__-7MU1').clear();
        cy.get(':nth-child(6) > .SignUpPage_passSignIn__-7MU1').type('11111');
        cy.get('.SignUpPage_submitBillingInfo__K87Vz').click();
    })

    it('checking my past order page', () => {
        cy.visit('user/login')
        cy.get('#usernameInput').clear();
        cy.get('#usernameInput').type('veronika');
        cy.get('#passwordInput').clear();
        cy.get('#passwordInput').type('veronika');
        cy.get('.SignInPage_submitSignIn__2YZBq').click();
        cy.get('#youOrdersNavBar > a').forceClick();
    })

    
    
})