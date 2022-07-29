import { HttpClient } from '@angular/common/http';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { LoginService } from 'src/app/service/auth/login.service';
import { Student } from '../../model/student.model';
import { Teacher } from '../../model/teacher.model';


@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.sass']
})
export class StudentComponent implements OnInit,OnDestroy {
  user : Student = new Student("test Full name","test username","test@gmail.com","TEST",new Teacher("","",""))
  subscription: Subscription = this.loginService.currentMessage.subscribe(message => this.user = new Student(
    JSON.parse(message)['name'],
    JSON.parse(message)["username"],
    JSON.parse(message)["email"],
    JSON.parse(message)["userRole"],
    JSON.parse(JSON.parse(message)["teacher"])
    ));
    emailString : string = ''


  constructor(private http: HttpClient, private router: Router, private loginService: LoginService) {}

  ngOnInit(): void {
    console.log(this.user);
    this.emailString = 'mailto:'+this.user.teacher.email

  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
