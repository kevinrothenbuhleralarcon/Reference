import {Component} from '@angular/core';
import {AbstractControl, FormControl, FormGroup} from "@angular/forms";
import {FakeLoginService} from "./fake-login.service";
import {Credentials} from "./model/Credentials";
import {filter, first} from "rxjs";
import {FieldError} from "./model/FieldError";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  loginForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  });


  constructor(private fakeLoginService: FakeLoginService) {

  }

  get username(): AbstractControl<any, any> | null {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl<any, any> | null {
    return this.loginForm.get('password');
  }

  login(): void {
    this.fakeLoginService.login(new Credentials(this.loginForm.value)).pipe(first())
      .subscribe(errors =>{
        errors.forEach(err => {
          const controlErrors = this.loginForm.get(err.name)?.errors;
          this.loginForm.get(err.name)?.setErrors({...controlErrors, [err.errorType]: err.errorMessage})
        })
      });
  }
}
