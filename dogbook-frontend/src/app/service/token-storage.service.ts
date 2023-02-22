import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private user: any;
  private decoded: any;

  constructor(private router: Router) { }

  signOut(): void {
    window.sessionStorage.clear();
    this.router.navigate(['/login']);
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    const token = sessionStorage.getItem(TOKEN_KEY);
    if (token) {
      return token;
    } else {
      // handle the case where the token is not set 
      return null;
    }
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
  const userString = sessionStorage.getItem(USER_KEY);
  if (userString) {
    return JSON.parse(userString);
  } else {
      // handle the case where the user is not logged in
      return null;
    }
  }

  public getUsername(): string{
    this.user = this.getToken();
    let sub: any;
    ({sub} = jwt_decode(this.user));
    console.log(sub);
    return sub;
  }

  public getUserRole(): string{
    this.user = this.getToken();
    this.decoded = jwt_decode(this.user);
    return this.decoded.role;
  }

  public permissionForPage(role: string): boolean{
    if (!this.isLoggedIn()) {
      return false;
    }
    if(this.getToken() !== role){
      return false;
    }
    return true;
  }

  public isLoggedIn(): boolean{
    this.user = this.getToken();
    if (this.user === null){
      return false;
    }
    return  true;
  }
}
