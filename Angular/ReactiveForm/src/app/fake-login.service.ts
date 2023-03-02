import {Injectable} from '@angular/core';
import {Credentials} from "./model/Credentials";
import {delay, Observable, of} from "rxjs";
import {FieldError} from "./model/FieldError";

@Injectable({
  providedIn: 'root'
})
export class FakeLoginService {

  constructor() { }

  login(credentials: Credentials): Observable<FieldError[]> {
    let errors = [];
    if (credentials.username !== 'kevin') {
      errors.push(new FieldError('username', 'notValid', 'Username is not correct'));
      errors.push(new FieldError('username', 'tooLong', 'Username is too long'));
    }
    if (credentials.password !== '1234') {
      errors.push(new FieldError('password', 'notValid', 'Password is not correct'));
    }

    return of(errors).pipe(delay(1000));
  }
}
