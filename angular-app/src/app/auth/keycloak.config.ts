import Keycloak from 'keycloak-js';

const keycloak = new (Keycloak as any)({
  url: 'https://login.amplify.com',
  realm: 'amplify',
  clientId: 'dsql-poc-client'
});

export { keycloak };
