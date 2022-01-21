import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-menadzer-page',
  templateUrl: './menadzer-page.component.html',
  styleUrls: ['./menadzer-page.component.scss']
})
export class MenadzerPageComponent implements OnInit {
  items!: MenuItem[];
  selectedTab: number = 0;

  constructor(private router: Router) { }

  ngOnInit(): void {
    localStorage.setItem('user', 'MENADZER');
    this.items = [
      {
        label: 'Zaposleni',
        icon: 'pi pi-fw pi-users',
        command: (event) => {
          this.selectedTab = 0;
        },
      },
      {
        label: 'Menu',
        icon: 'pi pi-fw pi-book',
        command: (event) => {
          this.selectedTab = 1;
        },
      },
      {
        label: 'Izvestaji',
        icon: 'pi pi-fw pi-chart-bar',
        command: (event) => {
          this.selectedTab = 2;
        },
      },
      {
        label: 'Button Return',
        icon: 'pi pi-fw pi-power-off',
        style: { 'margin-left': 'auto' },
        command: (event) => {
          this.router.navigateByUrl('/');
        },
      },
    ];
  }

}
