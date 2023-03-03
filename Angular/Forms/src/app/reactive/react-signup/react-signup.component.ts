import {Component, OnDestroy, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup} from "@angular/forms";
import {Credentials} from "../model/Credentials";
import {first, Subscription} from "rxjs";
import {FakeLoginService} from "../fake-login.service";

@Component({
  selector: 'app-react-signup',
  templateUrl: './react-signup.component.html',
  styleUrls: ['./react-signup.component.scss']
})
export class ReactSignupComponent implements OnInit, OnDestroy {

  loginForm: FormGroup;
  private credentials = new Credentials();
  private formSubscription!: Subscription;


  constructor(private fakeLoginService: FakeLoginService, private fb: FormBuilder) {
    this.loginForm = fb.group(this.credentials);
  }

  ngOnInit(): void {
    this.formSubscription = this.loginForm.valueChanges.subscribe(() => {
      this.credentials.setCredentials(this.loginForm.value);
    })
  }

  ngOnDestroy(): void {
    this.formSubscription.unsubscribe();
  }


  get username(): AbstractControl<any, any> | null {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl<any, any> | null {
    return this.loginForm.get('password');
  }

  login(): void {
    this.fakeLoginService.login(this.credentials).pipe(first())
      .subscribe(errors => {
        errors.forEach(err => {
          const controlErrors = this.loginForm.get(err.name)?.errors;
          this.loginForm.get(err.name)?.setErrors({...controlErrors, [err.errorType]: err.errorMessage})
        })
      });
  }
}
