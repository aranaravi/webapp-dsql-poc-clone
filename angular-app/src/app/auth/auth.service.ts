import { Injectable } from '@angular/core';
import { keycloak } from './keycloak.config';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  logout() {
    keycloak.logout({
      redirectUri: window.location.origin
    });
  }

  getToken() {
    return keycloak.token;
  }

  isLoggedIn() {
    return !!keycloak.authenticated;
  }
}