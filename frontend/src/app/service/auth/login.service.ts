import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class LoginService {

  private messageSource = new BehaviorSubject('{"state":"nothing here"}');
  currentMessage = this.messageSource.asObservable();

  constructor(private http:HttpClient, private router:Router) { }

  changeMessage(message: string) {
    this.messageSource.next(message)
  }

  login(formData :FormData){
    this.http.post('http://localhost:8080/login', formData).subscribe({
      next: (res) => {

        switch (JSON.parse(JSON.stringify(res))['userRole']){
          case "STUDENT":
            console.log(res);

            this.setUserJson(JSON.stringify(res))
            this.router.navigate(['/student-dashboard']);

            break;
          case "TEACHER":
            this.router.navigate(['/teacher-dashboard']);
            break
          default:
            console.log("FAILEd");

        }





      },
      error: (res) => console.log(res),
    });
  }

  setUserJson(json:string){
    this.changeMessage(json)
  }

}
