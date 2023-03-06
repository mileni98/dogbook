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
      this.role = this.tokenStorage.getUserRole();
    }
  }

  onSubmit(): void {
    this.authService.authenticate(this.form).subscribe(
      response => {
        this.tokenStorage.saveToken(response.token);
        this.tokenStorage.saveUser(response);
        this.user = this.tokenStorage.getToken();
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.username = this.tokenStorage.getUsername();
        this.role = this.tokenStorage.getUserRole();
        this.reloadPage();
        
      },
      error => {
        this.errorMessage = error;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
    this.role = this.tokenStorage.getUserRole();
  }
  
}
