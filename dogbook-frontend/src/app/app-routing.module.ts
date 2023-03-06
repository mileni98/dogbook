import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { RegisterUserComponent } from './component/register-user/register-user.component';
import { LoginComponent } from './component/login/login.component'
import { ApproveRegistrationComponent } from './component/approve-registration/approve-registration.component';
import { AdminGuard } from './component/guard/admin.guard';
import { NotFoundComponent } from './component/not-found/not-found.component';
import { ForbiddenComponent } from './component/forbidden/forbidden.component';

const routes: Routes = [
  {path: 'register-user', component: RegisterUserComponent},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'approve-registration', component: ApproveRegistrationComponent, canActivate: [AdminGuard]},
  {path: '403', component: ForbiddenComponent},
  {path: '404', component: NotFoundComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
