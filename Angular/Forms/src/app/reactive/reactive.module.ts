import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactSignupComponent} from './react-signup/react-signup.component';
import {ReactiveRoutingModule} from "./reactive-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared-components/shared.module.";


@NgModule({
    declarations: [
        ReactSignupComponent,
    ],
    imports: [
        CommonModule,
        ReactiveRoutingModule,
        ReactiveFormsModule,
        FormsModule,
        SharedModule,
    ]
})
export class ReactiveModule {
}
