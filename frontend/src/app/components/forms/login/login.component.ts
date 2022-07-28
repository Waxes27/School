import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { LoginService } from '../../../service/auth/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass'],
})
export class LoginComponent implements OnInit {
  private submitted: boolean = false;
  @Output() messageEvent = new EventEmitter<string>();

  loginForm = new FormGroup({
    username: new FormControl('', [<any>Validators.required]),
    password: new FormControl('', [
      <any>Validators.required,
      <any>Validators.minLength(6),
    ]),
  });

  constructor(private http: HttpClient, private router: Router, private loginService: LoginService) {}
  message:string = '';
  subscription: Subscription = this.loginService.currentMessage.subscribe(message => this.message = message);

  ngOnInit(): void {
    this.subscription = this.loginService.currentMessage.subscribe(message => this.message = message)
  }


  onSubmit() {
    var formData: any = new FormData();


    formData.append('username', this.loginForm.get('username')?.value);
    formData.append('password', this.loginForm.get('password')?.value);
    

    this.loginService.login(formData)

  }





}
