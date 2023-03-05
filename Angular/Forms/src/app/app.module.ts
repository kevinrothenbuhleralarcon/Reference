import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HomeComponent} from './home/home.component';
import {NavComponent} from './nav/nav.component';
import {TemplateModule} from "./template/template.module";
import {ReactiveModule} from "./reactive/reactive.module";
import {SharedModule} from "./shared-components/shared.module.";
import { AddressComponent } from './shared-components/address/address.component';

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        NavComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        ReactiveFormsModule,
        TemplateModule,
        ReactiveModule,
        SharedModule,
        FormsModule
    ],
    providers: [],
    exports: [
        AddressComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
