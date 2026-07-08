import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { keycloak } from './app/auth/keycloak.config';

function bootstrap() {
  platformBrowserDynamic()
    .bootstrapModule(AppModule)
    .catch(err => console.error(err));
}

keycloak.init({
  onLoad: 'login-required',
  checkLoginIframe: false
}).then((authenticated: boolean) => {

  if (authenticated) {
    console.log("User logged in");
    bootstrap();
  } else {
    window.location.reload();
  }

}).catch((err: any) => {
  console.error('Keycloak init failed', err);
  window.location.reload();
});

setInterval(() => {

  if (keycloak.authenticated) {

    keycloak.updateToken(30).then((refreshed: boolean) => {

      if (refreshed) {
        console.log('Token refreshed');
      }

    }).catch(() => {

      console.warn('Token refresh failed, logging out');

      keycloak.logout({
        redirectUri: window.location.origin
      });

    });
  }

}, 60000);