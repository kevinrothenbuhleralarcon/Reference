import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
    googleURL = 'http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:4200/';

    constructor(private route: ActivatedRoute) {

    }

    ngOnInit(): void {
        // const token: string = this.route.snapshot.queryParamMap.get('token');
        // const error: string = this.route.snapshot.queryParamMap.get('error');
    }
}
