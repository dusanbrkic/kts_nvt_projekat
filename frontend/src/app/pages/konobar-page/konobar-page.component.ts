import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem, MessageService } from 'primeng/api';
import { AuthService } from '../../services/auth.service';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-konobar-page',
  templateUrl: './konobar-page.component.html',
  styleUrls: ['./konobar-page.component.scss'],
})
export class KonobarPageComponent implements OnInit {
  items!: MenuItem[];
  selectedTab: number = 0;

  stompClient: any;

  constructor(private router: Router, private authService: AuthService,private messageService: MessageService) {}

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
        label: 'Button Return',
        icon: 'pi pi-fw pi-power-off',
        style: { 'margin-left': 'auto' },
        command: (event) => {
          this.authService.logout();
          this.stompClient.disconnect()
          this.router.navigateByUrl('/');
        },
      },
    ];

    const socket = new SockJS(environment.baseUrl + 'websocket');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, (frame: any) => {
      this.stompClient.subscribe('/topic/preuzmi-porudzbina-notification',(message: any) => {
        console.log(JSON.parse(message.body).content)
        this.messageService.add({severity:'info', sticky: true,summary:'Notification', detail:JSON.parse(message.body).content});
      });

      this.stompClient.subscribe('/topic/spremi-jela-notification',(message: any) => {
        console.log(JSON.parse(message.body).content)
        this.messageService.add({severity:'info', summary:'Notification', sticky: true, detail:JSON.parse(message.body).content});
      });

      this.stompClient.subscribe('/topic/spremi-pica-notification',(message: any) => {
        console.log(JSON.parse(message.body).content)
        this.messageService.add({severity:'info', summary:'Notification', sticky: true, detail:JSON.parse(message.body).content});
      });
    });
  }
}
