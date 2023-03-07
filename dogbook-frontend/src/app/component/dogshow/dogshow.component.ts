import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DogShow } from 'src/app/model/dogshow.model';
import { DogShowService } from 'src/app/service/dogshow.service';
import { NgForm } from '@angular/forms';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-dogshow',
  templateUrl: './dogshow.component.html',
  styleUrls: ['./dogshow.component.css']
})
export class DogshowComponent implements OnInit{

  public dogshows: DogShow[] = [];
  public seeDogshow: DogShow;
  public editDogshow: DogShow;

  key: string = '';
  name: string = '';
  reverse = false;
  isLoggedIn = false;
  role: string = '';

  constructor(private dogShowService: DogShowService, private tokenStorageService: TokenStorageService) {
    this.seeDogshow = {
        id: '1',
        name: 'Westminster Dog Show',
        location: 'New York City',
        date: new Date(),
        maximumScore: '100',
        finished: false,
        closed: false,
    };
    this.editDogshow = {
      id: '1',
      name: 'Westminster Dog Show',
      location: 'New York City',
      date: new Date(),
      maximumScore: '100',
      finished: false,
      closed: false,
    };
  };
    
  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.role = this.tokenStorageService.getUserRole();
      this.getDogShows();
    }
  }

  public sortData() : DogShow[]{
    return this.dogshows.sort((a, b) => {
      return <any>new Date(b.date) - <any>new Date(a.date);
    });
  }

  public getDogShows(): void {
    this.dogShowService.getAll().subscribe(
      (response: DogShow[]) => {
        this.dogshows = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public onAddDogShow(addForm: NgForm):void {
    document.getElementById('add-dogshow-form')?.click();
    this.dogShowService.addDogShow(addForm.value).subscribe(
      (response: DogShow) => {
        console.log(response);
        this.getDogShows();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    )
  }

  public onUpdateDogShow(dogShow: DogShow): void {
    this.dogShowService.updateDogShow(dogShow).subscribe(
      (response: DogShow) => {
        console.log(response);
        this.getDogShows();
        console.log("Stiglo");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  public onOpenModalDogs(dogShow: DogShow, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'showDogs') {
      this.seeDogshow = dogShow;
      button.setAttribute('data-target', '#showDogsModal');
    }
    if (mode === 'u') {
      this.editDogshow = dogShow;
      button.setAttribute('data-target', '#u');
    }
    container?.appendChild(button);
    button.click();
  }
  
  public searchDogShows(key: string): void {
    const results: DogShow[] = [];
    for (const dogshow of this.dogshows) {
      if (dogshow.name.toLowerCase().indexOf(key.toLowerCase()) !== -1 
      || dogshow.location.toLowerCase().indexOf(key.toLowerCase()) !== -1 ) {
        results.push(dogshow);
      }
    }
    this.dogshows = results;
    if (results.length === 0 || !key){
      this.getDogShows();
    }
  }

  public onOpenModal(dogShow: null, mode: string): void {
    const container = document.getElementById('main-container')
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-toggle', '#addDogShowModal');
    }
    container?.appendChild(button);
    button.click();
  }

}
