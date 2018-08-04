import { Component, OnInit, ElementRef } from '@angular/core';
import { User } from '../user';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User;
  registerForm: ElementRef;
  constructor(
    private authService: AuthenticationService,
    private router: Router) { 
      this.newUser = new User(); 
  }

  ngOnInit() {
  }

  registerUser() {
    this.authService.registerUser(this.newUser)
      .subscribe((data) => {
        this.router.navigate(['/login']);
      });    
  }

}
