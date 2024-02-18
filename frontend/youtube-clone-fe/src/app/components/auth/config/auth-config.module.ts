import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-0rqhonwrmz8ypep7.us.auth0.com',
            redirectUrl: 'http://localhost:4200/callback',
            clientId: '0JpnNm3LD7FqiFbGWXVHLDEV0t5h8hLx',
            scope: 'openid profile offline_access email',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
            secureRoutes: ['http://localhost:8080/'],
            customParamsAuthRequest:{
                audience: 'http://localhost:8080/'
            }
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
