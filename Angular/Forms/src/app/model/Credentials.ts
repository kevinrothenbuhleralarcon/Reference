import {AbstractControl, FormGroup} from "@angular/forms";

export class Credentials {
  username: string = '';
  password: string = '';

  public static fromForm(form: FormGroup): Credentials {
    const credentials = new Credentials();

    credentials.username = form.get('username')?.value;
    credentials.password = form.get('password')?.value;

    return credentials;
  }


}
