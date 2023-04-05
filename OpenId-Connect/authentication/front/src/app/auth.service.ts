import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(private httpClient: HttpClient) {}

    public login(): Observable<any> {
        return this.httpClient.get("http://localhost:8080/oauth2/authorization/google");
        // return this.httpClient.get("http://localhost:8080/auth/");
    }
}
