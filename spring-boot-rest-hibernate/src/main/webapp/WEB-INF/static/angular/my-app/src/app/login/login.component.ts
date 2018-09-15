import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '../auth/auth.service';
import {  Router } from '@angular/router';

//import Output, EventEmitter from angular core
import { Input, Output, EventEmitter} from '@angular/core'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  private formSubmitAttempt: boolean;

  @Output()
  change: EventEmitter<number> = new EventEmitter<number>();


  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    alert(localStorage.getItem('userInfo'))
    if(localStorage.getItem('userInfo')){
      this.router.navigate(['/customer-list'])
    }
    this.form = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    return (
      (!this.form.get(field).valid && this.form.get(field).touched) ||
      (this.form.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit() {
    if (this.form.valid) {
      this.authService.login(this.form.value);
    }
    this.formSubmitAttempt = true;
  }

}
