import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-konobar-page',
  templateUrl: './konobar-page.component.html',
  styleUrls: ['./konobar-page.component.scss']
})
export class KonobarPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  displayModal: boolean = false;

  showModalDialog() {
    this.displayModal = true;
}
}
