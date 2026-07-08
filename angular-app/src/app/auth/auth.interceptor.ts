import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { keycloak } from './keycloak.config';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: any, next: any) {

    if (!keycloak.authenticated) {
      return next.handle(req);
    }

    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${keycloak.token}`
      }
    });

    return next.handle(cloned);
  }
}
