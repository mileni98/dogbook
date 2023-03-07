import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterUserComponent } from './component/register-user/register-user.component';
import { HeaderComponent } from './component/header/header.component';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import { ApproveRegistrationComponent } from './component/approve-registration/approve-registration.component';
import { AdminGuard } from './component/guard/admin.guard';
import { NotFoundComponent } from './component/not-found/not-found.component';
import { ForbiddenComponent } from './component/forbidden/forbidden.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { DogshowComponent } from './component/dogshow/dogshow.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterUserComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    ApproveRegistrationComponent,
    NotFoundComponent,
    ForbiddenComponent,
    DogshowComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    AdminGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
