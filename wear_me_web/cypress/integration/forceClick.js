Cypress.Commands.add('forceClick', {prevSubject: 'element'}, (subject, options) => {
    cy.wrap(subject).click({force: true})
  });