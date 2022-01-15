import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor() { }


  ngOnInit(): void {
  }

  displayModal: boolean = false;
  username: string="";
  password: string="";

  showModalDialog() {
    this.displayModal = true;
}

}
