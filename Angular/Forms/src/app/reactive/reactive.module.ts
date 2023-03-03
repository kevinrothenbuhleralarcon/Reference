import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactSignupComponent} from './react-signup/react-signup.component';
import {ReactiveRoutingModule} from "./reactive-routing.module";
import {ReactiveFormsModule} from "@angular/forms";
import { InputWithErrorsComponent } from './input-with-errors/input-with-errors.component';


@NgModule({
  declarations: [
    ReactSignupComponent,
    InputWithErrorsComponent
  ],
  imports: [
    CommonModule,
    ReactiveRoutingModule,
    ReactiveFormsModule
  ]
})
export class ReactiveModule { }
