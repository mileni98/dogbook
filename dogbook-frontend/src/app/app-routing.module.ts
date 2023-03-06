import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { RegisterUserComponent } from './component/register-user/register-user.component';
import { LoginComponent } from './component/login/login.component'
import { ApproveRegistrationComponent } from './component/approve-registration/approve-registration.component';
import { AdminGuard } from './component/guard/admin.guard';

const routes: Routes = [
  {path: 'register-user', component: RegisterUserComponent},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'approve-registration', component: ApproveRegistrationComponent, canActivate: [AdminGuard]},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
