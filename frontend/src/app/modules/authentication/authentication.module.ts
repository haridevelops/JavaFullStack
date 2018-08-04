import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthenticationRoutingModule } from './authentication-router.module';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule, MatCardModule } from '@angular/material';
import { AuthenticationService } from './authentication.service';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AuthenticationRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule
  ],
  declarations: [LoginComponent, RegisterComponent],
  providers: [
    AuthenticationService
  ],
  exports: [
    LoginComponent, 
    RegisterComponent
  ],
})
export class AuthenticationModule { }
