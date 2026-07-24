import Keycloak from 'keycloak-js';

const keycloak = new (Keycloak as any)({
  url: 'https://34.222.26.97',
  realm: 'amplify',
  clientId: 'dsql-poc-client'
});

export { keycloak };
