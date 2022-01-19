import { Component, ElementRef, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import Sto from 'src/app/model/Sto';
import Zona from 'src/app/model/Zona';

@Component({
  selector: 'app-layout-admin',
  templateUrl: './layout-admin.component.html',
  styleUrls: ['./layout-admin.component.scss'],
})
export class LayoutAdminComponent implements OnInit {
  zone!: Zona[];
  selectedZona!: Zona;

  displayModal: boolean = false;
  selectedSto!: Sto;
  newNaziv: string = '';
  newBroj: number = 0;
  errors: boolean = false;

  displayModalNewZone: boolean=false;
  newZoneNaziv: string="";

  displayModalNazivZone: boolean=false;
  updateNaziv: string="";

  constructor(private elem: ElementRef,private confirmationService: ConfirmationService,private messageService: MessageService) {}

  ngOnInit(): void {
    this.zone = [
      {
        id: 1,
        naziv: 'Prizemlje',
        stolovi: [
          {
            id: 1,
            zauzet: false,
            brojMesta: 4,
            x: 651.5,
            y: 227.046875,
            naziv: 'Sto 1',
          },
          {
            id: 2,
            zauzet: false,
            brojMesta: 3,
            x: 195.5,
            y: 469.609375,
            naziv: 'Sto 2',
          },
        ],
        template: '/assets/zones/zone1.png',
      },
      {
        id: 2,
        naziv: 'Sprat I',
        stolovi: [
          {
            id: 3,
            zauzet: false,
            brojMesta: 4,
            x: 30,
            y: 30,
            naziv: 'Sto 1',
          },
        ],
        template: '/assets/zones/zone2.png',
      },
    ];

    this.selectedZona = this.zone[0];
  }

  onDragEnded(event: any) {
    let element = event.source.getRootElement();
    let boundingClientRect = element.getBoundingClientRect();
    let parentPosition = this.getPosition(element);
    console.log(element.offsetLeft);
    console.log(element.offsetTop);
    console.log(
      'x: ' + (boundingClientRect.x - parentPosition.left),
      'y: ' + (boundingClientRect.y - parentPosition.top)
    );
    this.selectedZona.stolovi.forEach((sto) => {
      if (sto.id == element.id) {
        sto.x += boundingClientRect.x - parentPosition.left;
        sto.y += boundingClientRect.y - parentPosition.top;
      }
    });
    event.source._dragRef.reset();
  }

  getPosition(el: any) {
    let x = 0;
    let y = 0;
    while (el && !isNaN(el.offsetLeft) && !isNaN(el.offsetTop)) {
      x += el.offsetLeft - el.scrollLeft;
      y += el.offsetTop - el.scrollTop;
      el = el.offsetParent;
    }
    return { top: y, left: x };
  }

  addTable() {
    //this.tables.push('Table ' + (this.tables.length + 1));

    const uuid = Math.floor(Math.random() * (1000000 - 0 + 1) + 0);
    this.selectedZona.stolovi.push({
      id: uuid,
      zauzet: false,
      brojMesta: 4,
      x: 0,
      y: 0,
      naziv: 'Novi sto',
    });
    console.log(this.selectedZona.stolovi);
  }

  deleteTable() {
    this.selectedZona.stolovi = this.selectedZona.stolovi.filter(
      (s) => s.id !== this.selectedSto.id
    );
    this.displayModal = false;
  }

  selectSto(sto: Sto) {
    this.errors = false;
    this.selectedSto = sto;
    this.displayModal = true;
    this.newNaziv = sto.naziv;
    this.newBroj = sto.brojMesta;
  }

  save() {
    console.log(this.selectedZona);
  }

  saveTable() {
    if (this.newNaziv === '' || this.newBroj === null) {
      this.errors = true;
      return;
    }
    this.errors = false;
    this.selectedSto.naziv = this.newNaziv;
    this.selectedSto.brojMesta = this.newBroj;
    this.displayModal = false;
  }

  addZone() {
    this.displayModalNewZone=true;
  }

  addNewZone(){
    if(this.newZoneNaziv===""){
      return
    }
    this.zone.push({
      id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
      naziv: this.newZoneNaziv,
      stolovi: [],
      template: '/assets/zones/zone1.png',
    })
    this.displayModalNewZone=false;
  }

  deleteZone() {

    this.confirmationService.confirm({
      message: 'Da li Ste sigurni da želite da obrišete zonu ' + this.selectedZona.naziv + '?',
      header: 'Potvrda',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.zone = this.zone.filter((z) => z.id !== this.selectedZona.id);
        this.selectedZona=this.zone[0]
          this.messageService.add({severity:'success', summary: 'Uspeh', detail: 'Zona obrisana', life: 3000});
      }
  });
  }

  openNazivModal(){
    this.displayModalNazivZone=true;
    this.updateNaziv=this.selectedZona.naziv;
  }

  changeNaziv(){
    if(this.updateNaziv.length===0){
      return
    }
    this.selectedZona.naziv=this.updateNaziv
    this.displayModalNazivZone=false;
  }
}
