import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ReactSignupComponent} from "./react-signup/react-signup.component";

const routes: Routes = [
  {path: 'react', component: ReactSignupComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class ReactiveRoutingModule { }
