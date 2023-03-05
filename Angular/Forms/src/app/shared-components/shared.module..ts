import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from "@angular/forms";
import {InputWithErrorsComponent} from "./input-with-errors/input-with-errors.component";
import {FakeLoginService} from "./fake-login.service";


@NgModule({
    declarations: [
        InputWithErrorsComponent,
    ],
    exports: [
        InputWithErrorsComponent,
    ],
    imports: [
        CommonModule,
        FormsModule,
    ]
})
export class SharedModule {
}