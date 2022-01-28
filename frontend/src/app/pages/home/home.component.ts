import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Login } from '../../model/Login';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private authService: AuthService,private router: Router,private messageService: MessageService) {}

  ngOnInit(): void {
    this.authService.logout()
  }

  displayModal: boolean = false;
  username: string = '';
  password: string = '';

  showModalDialog() {
    this.username=''
    this.password=''
    this.displayModal = true;
  }

  login() {
    if (this.username !== '' && this.password !== '') {
      const auth: Login = {
        username: this.username,
        password: this.password,
      };
  
      this.authService.login(auth).subscribe(
        (result) => {
          localStorage.setItem("user", JSON.stringify(result));
          switch(result.role){
            case "ROLE_ADMIN":{
              this.router.navigate(["/admin"]);
              break
            }
            case "ROLE_MANAGER":{
              this.router.navigate(["/menadzer"]);
              break
            }
            case "ROLE_GLAVNI_KUVAR":{
              this.router.navigate(["/kuvar"]);
              break
            }
            default:{
              this.messageService.add({severity:'error', summary:'Error', detail:'Something went wrong'});
            }
          }
        },
        (error) => {
          console.log(error)
          this.messageService.add({severity:'error', summary:'Bad credentials', detail:'Username or password are wrong'});
        }
      );
  
    }else{
      this.messageService.add({severity:'error', summary:'Bad input', detail:'Bad input'});
    }
  }
}
