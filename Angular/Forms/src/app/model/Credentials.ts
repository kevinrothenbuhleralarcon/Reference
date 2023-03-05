export class Credentials {
  username: string = '';
  password: string = '';

  setCredentials(init?: Credentials) {
    Object.assign(this, init);
  }
}
