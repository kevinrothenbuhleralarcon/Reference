import {Component, OnDestroy} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {FakeLoginService} from "./fake-login.service";
import {Credentials} from "./model/Credentials";
import {first, Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnDestroy{
  // loginForm = new FormGroup({
  //   username: new FormControl(),
  //   password: new FormControl()
  // });

  loginForm: FormGroup;
  private credentials = new Credentials();
  private formSubscription: Subscription;


  constructor(private fakeLoginService: FakeLoginService, private fb: FormBuilder) {
    this.loginForm = fb.group(this.credentials);
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
      .subscribe(errors =>{
        errors.forEach(err => {
          const controlErrors = this.loginForm.get(err.name)?.errors;
          this.loginForm.get(err.name)?.setErrors({...controlErrors, [err.errorType]: err.errorMessage})
        })
      });
  }
}
