import Keycloak from 'keycloak-js';

const keycloak = new (Keycloak as any)({
  url: 'http://localhost:8080',
  realm: 'amplify',
  clientId: 'dsql-poc-client'
});

export { keycloak };