Cypress.Commands.add("login", (username, password) => {
    var data = ({
        'username': username,
        'password': password 
      });
        cy.request({
            method: 'POST',
            url: 'http://localhost:8080/user/login',
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: data
          }).then(({ body }) => {
            cy.log(JSON.parse(JSON.stringify(body.access_token)));
            window.localStorage.setItem("accessTokens", JSON.parse(JSON.stringify(body.access_token)))
    })
  })
