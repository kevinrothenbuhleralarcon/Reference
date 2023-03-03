import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TemplateSignupComponent} from './template-signup/template-signup.component';
import {TemplateRoutingModule} from "./template-routing.module";
import {FormsModule} from "@angular/forms";
import { InputWithErrorsComponent } from './input-with-errors/input-with-errors.component';


@NgModule({
  declarations: [
    TemplateSignupComponent,
    InputWithErrorsComponent
  ],
  imports: [
    CommonModule,
    TemplateRoutingModule,
    FormsModule
  ]
})
export class TemplateModule { }
