import { Component } from '@angular/core';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  template: `
  <mat-toolbar color="primary">
      <span>Movie Cruiser</span>
      <button mat-button [routerLink]="['/movies/popular']">Popular Movies</button>    
      <button mat-button [routerLink]="['/movies/toprated']">Top Rated Movies</button>
      <button mat-button [routerLink]="['/movies/watchlist']">Watchlist</button>
      <button mat-button [routerLink]="['/movies/search']">Search</button>
      <button mat-button (click)="logout()">Logout</button>
  </mat-toolbar>
  <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  constructor(private auth: AuthenticationService, private router: Router){}
  
  logout() {
    this.auth.removeToken();
    this.router.navigate (['/login']);
  }
}
