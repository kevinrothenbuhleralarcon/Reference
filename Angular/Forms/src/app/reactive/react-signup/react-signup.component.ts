import {Component, OnDestroy, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormControlName, FormGroup, Validators} from "@angular/forms";
import {Credentials} from "../model/Credentials";
import {first, Subscription} from "rxjs";
import {FakeLoginService} from "../fake-login.service";

@Component({
  selector: 'app-react-signup',
  templateUrl: './react-signup.component.html'
})
export class ReactSignupComponent {
  loginForm: FormGroup;

  constructor(private fakeLoginService: FakeLoginService, private fb: FormBuilder) {
    this.loginForm = fb.group(new Credentials());
    this.username?.setValidators(Validators.required);
  }

  get username(): AbstractControl<any, any> | null {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl<any, any> | null {
    return this.loginForm.get('password');
  }

  login(): void {
    const credentials = (this.loginForm.value as Credentials);
    this.fakeLoginService.login(credentials).pipe(first())
      .subscribe(errors => {
        errors.forEach(err => {
          const controlErrors = this.loginForm.get(err.name)?.errors;
          this.loginForm.get(err.name)?.setErrors({...controlErrors, [err.errorType]: err.errorMessage})
        })
      });
  }
}
