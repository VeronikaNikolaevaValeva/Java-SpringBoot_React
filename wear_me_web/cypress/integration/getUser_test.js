Cypress.Commands.add("getUser", () => {

        cy.request({
            method: 'get',
            url: 'http://localhost:8080/customer/getLoggedUser',
            headers: { 
                'Authorization': 'Bearer ' + localStorage.getItem("accessTokens")
            }
          }).then(({ body }) => {
            window.localStorage.setItem("user", JSON.stringify(body))
    })
  })
 