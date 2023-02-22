import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import {TokenStorageService} from 'src/app/service/token-storage.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  isLoggedIn = false;
  showAdmin = false;
  showOwner = false;
  username: string = '';
  role: string = '';

  constructor(private tokenStorageService: TokenStorageService, private userService: UserService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.role = this.tokenStorageService.getUserRole();
      this.username = this.tokenStorageService.getUsername();
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
