import {Component, OnDestroy, OnInit, Self} from '@angular/core';
import {AbstractControl, ControlValueAccessor, FormBuilder, FormGroup, NgControl, Validators} from "@angular/forms";
import {Address} from "../../model/Address";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-address',
    templateUrl: './address.component.html'
})
export class AddressComponent implements ControlValueAccessor, OnInit, OnDestroy {

    addressForm: FormGroup;

    private subscription!: Subscription;
    private validationSubscription!: Subscription;

    onTouched = () => {
    };

    constructor(private fb: FormBuilder, @Self() public ngControl: NgControl) {
        this.ngControl.valueAccessor = this;
        this.addressForm = this.fb.group({
            street: ['', Validators.required],
            city: ['', Validators.required],
            zip: ['']
        });
    }

    get street(): AbstractControl<any, any> | null {
        return this.addressForm.get('street');
    }

    get city(): AbstractControl<any, any> | null {
        return this.addressForm.get('city');
    }

    get zip(): AbstractControl<any, any> | null {
        return this.addressForm.get('zip');
    }

    ngOnInit() {
        this.registerErrorChanged();
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
        this.validationSubscription.unsubscribe();
    }

    registerOnChange(onChange: (value: Address) => void): void {
        this.subscription = this.addressForm.valueChanges.subscribe(value => {
            onChange(value);
        });
    }

    registerOnTouched(onTouched: () => void): void {
        this.onTouched = onTouched;
    }

    writeValue(address: Address): void {
        if (address === null) {
            return
        }
        this.street?.setValue(address.street !== undefined ? address.street : '');
        this.city?.setValue(address.city !== undefined ? address.city : '');
        this.zip?.setValue(address.zip !== undefined ? address.zip : '');
    }

    private registerErrorChanged(): void {
        if (this.ngControl.statusChanges === null) {
            return;
        }
        this.validationSubscription = this.ngControl.statusChanges.subscribe(() => {
            this.mapErrorsToControl();
        })
    }

    private mapErrorsToControl(): void {
        const parentErrors = this.ngControl.errors;
        if (parentErrors === null) {
            return;
        }
        const mapErrors = new Map(Object.entries(parentErrors));
        mapErrors.forEach((value, key) => {
            const control = this.addressForm.get(key);
            if (control !== null) {
                const currentErrors = control.errors;
                control.setErrors({...currentErrors, ...value})
            }
        });
    }
}
