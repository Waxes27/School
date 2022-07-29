import { HttpClient } from '@angular/common/http';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { LoginService } from 'src/app/service/auth/login.service';
import { Principal } from '../../model/principal.model';
import { School } from '../../model/school.model';
import { Student } from '../../model/student.model';
import { Teacher } from '../../model/teacher.model';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.sass'],
})
export class StudentComponent implements OnInit, OnDestroy {
  school: School = new School('Test School','school','@gmail.com',new Principal())
  teacher: Teacher = new Teacher('', '', '', this.school,'')
  user: Student = new Student(
    'test Full name',
    'test username',
    'test@gmail.com',
    'TEST',
    this.teacher
  );
  subscription: Subscription = this.loginService.currentMessage.subscribe(
    (message) => {
      this.teacher = this.generateTeacher(JSON.stringify(JSON.parse(JSON.parse(message)['teacher'])))

      this.user = new Student(
        JSON.parse(message)['name'],
        JSON.parse(message)['username'],
        JSON.parse(message)['email'],
        JSON.parse(message)['userRole'],
        this.teacher

      );
    }
  );
  emailString: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    console.log(this.user);
    this.emailString = 'mailto:' + this.user.teacher?.email;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  generateTeacher(json: any): Teacher{
    // {"school":"{\"principal\":\"{\\\"name\\\":\\\"p p\\\",\\\"userRole\\\":\\\"PRINCIPAL\\\",\\\"email\\\":\\\"p\\\",\\\"username\\\":\\\"p\\\"}\",\"name\":\"Prestige College\",\"userRole\":\"SCHOOL\",\"email\":\"prestige@gmail.com\",\"username\":\"prestige\"}","name":"Lin Frame","userRole":"TEACHER","email":"lin.drame@gmail.com","username":"teacher"}
    // this.school = JSON.parse(json)['school']


    // console.log(json);

    const newTeacher  = new Teacher(
      JSON.parse(json)['name'],
      JSON.parse(json)['username'],
      JSON.parse(json)['email'],
      this.generateSchool(JSON.parse(json)['school']),
      ''
    )
      console.log(newTeacher);

    return newTeacher;
  }

  generateSchool(json: any) : School{
    console.log(JSON.parse(json)['name'])
    return new School(
      JSON.parse(json)['name'],
      JSON.parse(json)['username'],
      JSON.parse(json)['email'],
      this.generatePrincipal({})
    )
  }

  generatePrincipal(json:any): Principal{
    return new Principal()
  }
}
