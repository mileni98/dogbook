import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  username = '';
  role = '';
  private user: any;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) {}


  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      //this.role = this.tokenStorage.getUser().role;
      this.role = this.tokenStorage.getUserRole();
    }
  }

  onSubmit(): void {
    this.authService.authenticate(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);
        this.user = this.tokenStorage.getToken();
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.username = this.tokenStorage.getUsername();
        this.role = this.tokenStorage.getUserRole();
        this.reloadPage();
        
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
    this.role = this.tokenStorage.getUserRole();
  }
  
}
