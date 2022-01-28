import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-menadzer-page',
  templateUrl: './menadzer-page.component.html',
  styleUrls: ['./menadzer-page.component.scss']
})
export class MenadzerPageComponent implements OnInit {
  items!: MenuItem[];
  selectedTab: number = 0;

  showPasswordChanger: boolean=false;
  passwordOnClose: any;

  constructor(private router: Router,private authService: AuthService) { }

  ngOnInit(): void {
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
        label: 'Predlozi',
        icon: 'pi pi-fw pi-file',
        command: (event) => {
          this.selectedTab = 3;
        },
      },
      {
        label: 'Button Change Password',
        icon: 'pi pi-fw pi-key',
        style: { 'margin-left': 'auto' },
        command: (event) => {
          this.showPasswordChanger=true
        },
      },
      {
        label: 'Button Return',
        icon: 'pi pi-fw pi-power-off',
        style: { align: 'left' },
        command: (event) => {
          this.authService.logout()
          this.router.navigateByUrl('/');
        },
      },
    ];

    this.passwordOnClose=()=>{
      this.showPasswordChanger=false;
    }
  }

}
