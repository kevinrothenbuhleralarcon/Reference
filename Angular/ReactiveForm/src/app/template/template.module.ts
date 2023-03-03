import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TemplateSignupComponent} from './template-signup/template-signup.component';
import {TemplateRoutingModule} from "./template-routing.module";
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    TemplateSignupComponent
  ],
  imports: [
    CommonModule,
    TemplateRoutingModule,
    FormsModule
  ]
})
export class TemplateModule { }
