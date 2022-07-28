import { HttpClient } from '@angular/common/http';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { LoginService } from 'src/app/service/auth/login.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.sass']
})
export class StudentComponent implements OnInit,OnDestroy {
  data = {
    'object':"Login"
  };
  subscription: Subscription = this.loginService.currentMessage.subscribe(message => this.data = JSON.parse(message));


  constructor(private http: HttpClient, private router: Router, private loginService: LoginService) {}

  ngOnInit(): void {
    console.log(this.data);

  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
