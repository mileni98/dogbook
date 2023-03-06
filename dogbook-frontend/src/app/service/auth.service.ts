import { Injectable } from '@angular/core'
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { User} from '../model/user.model';
import { environment } from 'src/environments/environments';
import { catchError } from 'rxjs/operators';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable({providedIn: 'root'})
export class AuthService {

    private apiServerUrl = environment.apiUserUrl;

    constructor(private http: HttpClient) {}

    public register(user : User): Observable<User> {
      let path = '/auth/register';
      return this.http.post<User>(this.apiServerUrl + path, user, httpOptions)
       .pipe(
        catchError((error) => {
          console.log('Error:', error);
          return this.handleError(error, path);
        })
      );
    }

    public authenticate(credentials: any): Observable<any> {
      let path = '/auth/authenticate';
      return this.http.post(this.apiServerUrl + '/auth/authenticate', {
        username: credentials.username,
        password: credentials.password
      }, httpOptions).pipe(
        catchError((error) => {
          console.log('Error:', error);
          return this.handleError(error, path);
        })
      );
    }

    public handleError(error: any, path: string): Observable<never> {
      let errorMessage = 'An error occurred. Please try again later.';
      
      if (error instanceof HttpErrorResponse) {
        switch (path) {
          case '/auth/register':
            if (error.status === 400) {
              errorMessage = 'User with this username already exist! Please try again.';
            } 
            break;

          case '/auth/authenticate':
            if (error.status === 400) {
              errorMessage = 'Invalid authentication credentials. Please try again.';
            } else if (error.status === 403) {
              errorMessage = 'You are not authorized to perform this action.';
            } 
            break;

          default:
            errorMessage = 'An error occurred. Please try again later.';
            break;
        }
      } 
      return throwError(errorMessage);
    }

}