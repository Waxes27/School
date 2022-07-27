import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {
  private submitted: boolean = false;

  loginForm = new FormGroup({
    username: new FormControl('',[<any>Validators.required]),
    password: new FormControl('', [<any>Validators.required, <any>Validators.minLength(6)]),
  });

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(){}

}
