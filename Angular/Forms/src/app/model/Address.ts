import {FormGroup} from "@angular/forms";

export class Address {
    street?: string;
    city?: string;
    zip?: number;

    public static fromForm(form: FormGroup): Address {
        const address = new Address();

        address.street = form.get('street')?.value;
        address.city = form.get('city')?.value;
        address.zip = Number(form.get('zip')?.value);

        return address;
    }
}