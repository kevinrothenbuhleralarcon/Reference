import {Component, inject, Input, OnDestroy, OnInit} from '@angular/core';
import {ControlContainer, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {map, Observable} from "rxjs";

@Component({
    selector: 'app-address',
    templateUrl: './address.component.html',
    viewProviders: [
        {
            provide: ControlContainer,
            useFactory: () => inject(ControlContainer, {skipSelf: true})
        }
    ]
})
export class AddressComponent implements OnInit, OnDestroy {

    @Input() controlKey!: string;

    @Input() label!: string;

    shouldDisplayCity!: Observable<boolean>;

    private parentContainer = inject(ControlContainer);

    constructor(private fb: FormBuilder) {
    }

    get parentFormGroup(): FormGroup {
        return this.parentContainer.control as FormGroup;
    }

    get street(): FormControl<string> {
        return this.parentFormGroup.get([this.controlKey, 'street']) as FormControl;
    }

    get city(): FormControl<string> {
        return this.parentFormGroup.get([this.controlKey, 'city']) as FormControl;
    }

    get zip(): FormControl<string> {
        return this.parentFormGroup.get([this.controlKey, 'zip']) as FormControl;
    }

    ngOnInit() {
        this.parentFormGroup.addControl(this.controlKey, this.fb.group({
            street: ['', Validators.required],
            city: ['', Validators.required],
            zip: ['']
        }));
    }

    ngOnDestroy(): void {
        this.parentFormGroup.removeControl(this.controlKey);
    }
}
