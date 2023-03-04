import {Component, Input, OnInit} from '@angular/core';
import {ControlContainer, NgForm} from "@angular/forms";

@Component({
  selector: 'app-input-with-errors',
  templateUrl: './input-with-errors.component.html',
  viewProviders: [ { provide: ControlContainer, useExisting: NgForm } ]
})
export class InputWithErrorsComponent implements OnInit {

  @Input()
  label: string = '';

  @Input()
  name: string = '';

  @Input()
  type: string = 'text';

  constructor() { }

  ngOnInit(): void {
  }

}
