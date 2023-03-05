import {Component, ElementRef, Injector, Input, OnInit, Optional, Self, ViewChild} from '@angular/core';
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
})
export class InputWithErrorsComponent implements ControlValueAccessor {

    @Input()
    label: string = '';

    @Input()
    name: string = '';

    @Input()
    type: string = 'text';

    @ViewChild('input', {static: true})
    input!: ElementRef;

    onChange = (inputValue: string) => {
    };

    onTouched = () => {
    };

    constructor(@Optional() @Self() public ngControl: NgControl) {
        // So if we use the component without a ngModel it does not trigger an error
        if (ngControl !== null) {
            ngControl.valueAccessor = this;
        }
    }

    writeValue(value: string): void {
        this.input.nativeElement.value = value;
    }

    registerOnChange(onChange: (value: string) => void): void {
        this.onChange = onChange;
    }

    registerOnTouched(onTouched: () => void): void {
        this.onTouched = onTouched;
    }
}
