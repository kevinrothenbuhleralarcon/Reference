import {Component, OnInit} from '@angular/core';
import {Address} from "../model/Address";
import {NgForm} from "@angular/forms";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

    address = new Address();

    constructor() {
    }

    ngOnInit(): void {
    }

    onSubmit(form: NgForm): void {
        const group = form.controls['address'];
        group.setErrors({'street': {'too long': 'Street is too long', 'invalid': 'Street is invalid'}});
    }
}
