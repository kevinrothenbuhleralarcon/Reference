import {Component, Injector, Input, OnInit} from '@angular/core';
import {
    ControlValueAccessor,
    FormControl, FormControlDirective,
    FormControlName,
    FormGroupDirective,
    NG_VALUE_ACCESSOR,
    NgControl
} from "@angular/forms";

@Component({
    selector: 'app-input-with-errors',
    templateUrl: './input-with-errors.component.html',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            multi: true,
            useExisting: InputWithErrorsComponent
        }
    ]
})
export class InputWithErrorsComponent implements ControlValueAccessor, OnInit {

    @Input()
    label: string = '';

    @Input()
    name: string = '';

    @Input()
    type: string = 'text';

    formControl!: FormControl;

    private onChange = (inputValue: string) => {
    };

    private onTouched = () => {
    };

    private touched = false;

    constructor(private injector: Injector) {
    }

    ngOnInit(): void {
        const ngControl = this.injector.get(NgControl);

        if (ngControl instanceof FormControlName) {
            this.formControl = this.injector
                    .get(FormGroupDirective)
                    .getControl(ngControl);
        } else {
            this.formControl = (ngControl as FormControlDirective).form as FormControl;
        }
    }

    handleChange() {
        this.markAsTouched();
        this.onChange(this.formControl.value);
    }

    writeValue(value: string): void {
        this.formControl.setValue(value);
    }

    registerOnChange(onChange: (value: string) => void): void {
        this.onChange = onChange;
    }

    registerOnTouched(onTouched: () => void): void {
        this.onTouched = onTouched;
    }

    private markAsTouched(): void {
        if (!this.touched) {
            this.touched = true;
            this.onTouched();
        }
    }
}
