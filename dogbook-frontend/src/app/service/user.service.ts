import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { environment } from 'src/environments/environments';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable({providedIn: 'root'})
export class UserService {

    private apiServerUrl = environment.apiUserUrl;

    constructor(private http: HttpClient) {}

    public getAll(): Observable<User[]> {
        return this.http.get<User[]>(this.apiServerUrl + '/api/v1/users/all');
    }

    public getAllNonApproved(): Observable<User[]> {
        return this.http.get<User[]>(this.apiServerUrl + '/api/v1/users/all-non-approved');
    }
    
    public getOwner(ownerUsername : string): Observable<User> {
        return this.http.get<User>(this.apiServerUrl + '/api/v1/users/${ownerUsername}');
    }

    public approveUser(user : User): Observable<User> {
        return this.http.put<User>(this.apiServerUrl + '/api/v1/users/approve', user);
    }

}