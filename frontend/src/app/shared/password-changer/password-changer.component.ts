import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-password-changer',
  templateUrl: './password-changer.component.html',
  styleUrls: ['./password-changer.component.scss'],
})
export class PasswordChangerComponent implements OnChanges {
  @Input() displayModal: boolean = false;
  @Input() onCloseCallback: any;

  password: string = '';
  repeatPassword: string = '';

  constructor(private messageService: MessageService,private authService: AuthService) {}
  ngOnChanges(changes: SimpleChanges): void {
    this.password = '';
    this.repeatPassword = '';
  }

  ngOnInit(): void {}

  change() {
    if (
      this.password !== '' &&
      this.repeatPassword !== '' &&
      this.password === this.repeatPassword
    ) {
      this.authService.chnagePasswordForCurrentUser(this.password,(message: string, status: string)=>{
        this.messageService.add({
          severity: status,
          summary: message,
          detail: message,
        });
      })
      this.displayModal = false;
      this.onCloseCallback();
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Bad input',
        detail: 'Bad input',
      });
    }
  }

  close() {
    this.displayModal = false;
    this.onCloseCallback();
  }
}
