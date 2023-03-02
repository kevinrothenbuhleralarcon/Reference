export class Credentials {
  username!: string;
  password!: string;

  public constructor(init?: Partial<Credentials>) {
    Object.assign(this, init);
  }
}
