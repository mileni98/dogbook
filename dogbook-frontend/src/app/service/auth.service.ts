import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User} from '../model/user.model';
import { environment } from 'src/environments/environments';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable({providedIn: 'root'})
export class AuthService {

    private apiServerUrl = environment.apiUserUrl;

    constructor(private http: HttpClient) {}

    public register(user : User): Observable<User> {
        return this.http.post<User>(this.apiServerUrl + '/auth/register', user, httpOptions);
    }

    public authenticate(credentials: any): Observable<any> {
        return this.http.post(this.apiServerUrl + '/auth/authenticate', {
            username: credentials.username,
            password: credentials.password
        }, httpOptions);
    }
     
    
}