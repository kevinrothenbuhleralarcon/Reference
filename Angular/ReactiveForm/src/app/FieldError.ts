export class FieldError {
    name!: string;
    errorType!: string;
    errorMessage!: string;

  constructor(name: string, errorType: string, errorMessage: string) {
    this.name = name;
    this.errorType = errorType;
    this.errorMessage = errorMessage;
  }
}
