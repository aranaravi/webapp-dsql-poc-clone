import Keycloak from 'keycloak-js';

const keycloak = new (Keycloak as any)({
  url: 'https://44.227.63.113',
  realm: 'amplify',
  clientId: 'dsql-poc-client'
});

export { keycloak };
