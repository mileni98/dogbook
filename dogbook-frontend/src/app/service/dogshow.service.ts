import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DogShow } from '../model/dogshow.model';
import { environment } from 'src/environments/environments';


@Injectable({providedIn: 'root'})
export class DogShowService {

    private apiServerUrl = environment.apiDogShowUrl;
    private baseDogShowsUrl = '/api/v1/dogshows';

    constructor(private http: HttpClient) {}

    public getAll(): Observable<DogShow[]> {
        return this.http.get<DogShow[]>(this.apiServerUrl + this.baseDogShowsUrl +'/all');
    }
    
    public addDogShow(dogShow : DogShow): Observable<DogShow> {
        return this.http.post<DogShow>(this.apiServerUrl + this.baseDogShowsUrl +'/add-dogshow', dogShow);
    }

    public getDogShow(dogShowId : number): Observable<DogShow> {
        return this.http.get<DogShow>(this.apiServerUrl + this.baseDogShowsUrl + '/${dogShowId}');
    }

    public updateDogShow(dogShow : DogShow): Observable<DogShow> {
        return this.http.put<DogShow>(this.apiServerUrl + this.baseDogShowsUrl + '/update-dogshow', dogShow);
      }
  
}