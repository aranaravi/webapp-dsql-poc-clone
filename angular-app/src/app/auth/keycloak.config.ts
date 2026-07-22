import Keycloak from 'keycloak-js';

const keycloak = new (Keycloak as any)({
  url: 'https://3.227.9.80',
  realm: 'amplify',
  clientId: 'dsql-poc-client'
});

export { keycloak };