import {Component} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Credentials} from "../../model/Credentials";
import {first} from "rxjs";
import {FakeLoginService} from "../../shared-components/fake-login.service";

@Component({
    selector: 'app-react-signup',
    templateUrl: './react-signup.component.html'
})
export class ReactSignupComponent {
    loginForm: FormGroup;

    constructor(private fakeLoginService: FakeLoginService, private fb: FormBuilder) {
        this.loginForm = fb.group({
            username: ['', Validators.required],
            password: [''],
            address:[]
        });
    }

    get username(): AbstractControl<any, any> | null {
        return this.loginForm.get('username');
    }

    get password(): AbstractControl<any, any> | null {
        return this.loginForm.get('password');
    }

    login(): void {
        const credential = Credentials.fromForm(this.loginForm);
        this.fakeLoginService.login(credential).pipe(first())
                .subscribe(errors => {
                    errors.forEach(err => {
                        const controlErrors = this.loginForm.get(err.name)?.errors;
                        this.loginForm.get(err.name)?.setErrors({...controlErrors, [err.errorType]: err.errorMessage})
                    })
                });
    }
}
