import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ReactiveFormsModule} from "@angular/forms";
import {HomeComponent} from './home/home.component';
import {NavComponent} from './nav/nav.component';
import {TemplateModule} from "./template/template.module";
import {ReactiveModule} from "./reactive/reactive.module";
import {InputWithErrorsComponent} from "./shared-components/input-with-errors/input-with-errors.component";

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        NavComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        ReactiveFormsModule,
        TemplateModule,
        ReactiveModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
