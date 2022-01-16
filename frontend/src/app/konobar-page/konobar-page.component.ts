import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-konobar-page',
  templateUrl: './konobar-page.component.html',
  styleUrls: ['./konobar-page.component.scss']
})
export class KonobarPageComponent implements OnInit {

  items!: MenuItem[];
  selectedTab: number=0;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.items = [
      {label: 'Home', icon: 'pi pi-fw pi-home',command: (event) => {
        this.selectedTab=0
      }},
      {label: 'Calendar', icon: 'pi pi-fw pi-calendar',command: (event) => {
        this.selectedTab=1
      }},
      {label: 'Edit', icon: 'pi pi-fw pi-pencil',command: (event) => {
        this.selectedTab=2
      }},
      {label: 'Button', icon: 'pi pi-fw pi-power-off',style: {'margin-left': 'auto'},command: (event)=>{
        this.router.navigateByUrl('/');
      }},
  ];
  }

}
