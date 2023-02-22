import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

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

  constructor(private userService: UserService) {

  }

  ngOnInit(): void {

  }



}
