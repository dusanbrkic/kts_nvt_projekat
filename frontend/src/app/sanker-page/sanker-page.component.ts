import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-sanker-page',
  templateUrl: './sanker-page.component.html',
  styleUrls: ['./sanker-page.component.scss'],
})
export class SankerPageComponent implements OnInit {
  items!: MenuItem[];
  selectedTab: number = 0;

  constructor(private router: Router,private authService: AuthService) {}

  ngOnInit(): void {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-fw pi-home',
        command: (event) => {
          this.selectedTab = 0;
        },
      },
      {
        label: 'Button Notifications',
        icon: 'pi pi-fw pi-bell',
        style: { 'margin-left': 'auto' },
        command: (event) => {
        },
      },
      {
        label: 'Button Return',
        icon: 'pi pi-fw pi-power-off',
        style: { align: 'right' },
        command: (event) => {
          this.authService.logout()
          this.router.navigateByUrl('/');
        },
      },
    ];
  }
}
