import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { keycloak } from './keycloak.config';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  async canActivate(): Promise<boolean> {

    const authenticated = keycloak.authenticated;

    if (authenticated) {
      return true;
    }

    try {
      await keycloak.login({
        redirectUri: window.location.origin + '/tutorial'
      });
    } catch (e) {
      console.error('Login failed', e);
      this.router.navigate(['/']);
    }

    return false;
  }
}