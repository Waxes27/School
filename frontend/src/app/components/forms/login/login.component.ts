import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { json } from 'body-parser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass'],
})
export class LoginComponent implements OnInit {
  private submitted: boolean = false;

  loginForm = new FormGroup({
    username: new FormControl('', [<any>Validators.required]),
    password: new FormControl('', [
      <any>Validators.required,
      <any>Validators.minLength(6),
    ]),
  });

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {}

  onSubmit() {
    var formData: any = new FormData();

    formData.append('username', this.loginForm.get('username')?.value);
    formData.append('password', this.loginForm.get('password')?.value);

    this.http.post('http://localhost:8080/login', formData).subscribe({
      next: (res) => {

        switch (JSON.parse(JSON.parse(JSON.stringify(res))['object'])['userRole']){
          case "STUDENT":
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
}
