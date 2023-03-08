import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/service';

@Component({
  selector: 'app-approve-registration',
  templateUrl: './approve-registration.component.html',
  styleUrls: ['./approve-registration.component.css']
})
export class ApproveRegistrationComponent implements OnInit{
 
  public users: User[] = [];

  key: string = '';
  name: string = '';
  reverse = false;
  isLoggedIn = false;
  role: string = '';
  

  constructor(private userService: UserService, private tokenStorageService: TokenStorageService) {}

  
   ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.role = this.tokenStorageService.getUserRole();
      this.getNotApprovedUsers();
    }
  }

  public getNotApprovedUsers(): void {
    this.userService.getAllNonApproved().subscribe(
      (response: User[]) => {
        this.users = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public approveUserRegistration(user: User): void {

    const approvedUser = {
      id: user.id,
      firstname: user.firstname,
      lastname: user.lastname,
      email: user.email,
      username: user.username,
      password: user.password,
      approved: user.approved,
      role: user.role
    };

    this.userService.approveUser(approvedUser).subscribe(
      (response: User) => {
        console.log(response);
        this.getNotApprovedUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public searchUsers(key: string): void {
    const results: User[] = [];
    for (const user of this.users) {
      if (user.firstname.toLowerCase().indexOf(key.toLowerCase()) !== -1 
      || user.lastname.toLowerCase().indexOf(key.toLowerCase()) !== -1 
      || user.username.toLowerCase().indexOf(key.toLowerCase()) !== -1 ) {
        results.push(user);
      }
    }
    this.users = results;
    if (results.length === 0 || !key){
      this.getNotApprovedUsers();
    }
  }

}
  





  

