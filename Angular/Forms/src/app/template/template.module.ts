import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TemplateSignupComponent} from './template-signup/template-signup.component';
import {TemplateRoutingModule} from "./template-routing.module";
import {FormsModule} from "@angular/forms";
import {SharedModule} from "../shared-components/shared.module.";


@NgModule({
  declarations: [
    TemplateSignupComponent
  ],
  imports: [
    CommonModule,
    TemplateRoutingModule,
    FormsModule,
    SharedModule,
  ]
})
export class TemplateModule { }
