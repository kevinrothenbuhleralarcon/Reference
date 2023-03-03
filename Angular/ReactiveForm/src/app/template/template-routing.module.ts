import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TemplateSignupComponent} from "./template-signup/template-signup.component";

const routes: Routes = [
  {path: 'template', component: TemplateSignupComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class TemplateRoutingModule { }
