import {Component, OnDestroy} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {FakeLoginService} from "./reactive/fake-login.service";
import {Credentials} from "./reactive/model/Credentials";
import {first, Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent{

}
