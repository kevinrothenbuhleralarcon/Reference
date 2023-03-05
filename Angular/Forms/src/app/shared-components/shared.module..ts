import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputWithErrorsComponent} from "./input-with-errors/input-with-errors.component";
import {AddressComponent} from "./address/address.component";


@NgModule({
    declarations: [
        InputWithErrorsComponent,
        AddressComponent
    ],
    exports: [
        InputWithErrorsComponent,
        AddressComponent,
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
    ]
})
export class SharedModule {
}