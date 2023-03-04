import {Component, OnInit} from '@angular/core';
import {first} from "rxjs";
import {NgForm} from "@angular/forms";
import {Credentials} from "../model/Credentials";
import {FakeLoginService} from "../fake-login.service";

@Component({
  selector: 'app-template-signup',
  templateUrl: './template-signup.component.html',
})
export class TemplateSignupComponent {
  constructor(private fakeLoginService: FakeLoginService) { }

  login(form: NgForm): void {
    const credentials = (form.value as Credentials);

    this.fakeLoginService.login(credentials).pipe(first())
      .subscribe(errors => {
        errors.forEach(err => {
          const control = form.controls[err.name];
          if (control !== null) {
            const controlErrors = control.errors;
            control.setErrors({...controlErrors, [err.errorType]: err.errorMessage});
          }
        })
      });
  }
}
