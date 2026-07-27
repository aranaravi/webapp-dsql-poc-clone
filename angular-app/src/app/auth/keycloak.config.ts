import Keycloak from 'keycloak-js';

const keycloak = new (Keycloak as any)({
  url: 'https://100.31.220.151',
  realm: 'amplify',
  clientId: 'dsql-poc-client'
});

export { keycloak };
