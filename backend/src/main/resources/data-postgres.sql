insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, naziv_slike, obrisan,username,password) values ('Perica', 'Peric', 0, '1990-01-01', 45000.0, 'pera.jpg', false,'user1','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW');

insert into plate (pocetak_vazenja, zaposleni_id) values ('2021-11-11', 1);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata , naziv_slike, obrisan,username,password) values ('Marko', 'Maric', 0, '1990-01-01', 60000.0, 'marko.jpg', false,'user2','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW');

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata , naziv_slike, obrisan,username,password) values ('Jovana', 'Jovic', 1, '1990-07-08', 43000.0, 'jovana.jpg', false,'user3','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW');

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, naziv_slike, obrisan,username,password) values ('Jovana', 'Jovic', 1, '1990-07-08', 43000.0, 'jovana.jpg', false,'user4','$2a$12$2Juja5C8eWU3IDEH8FGN2.5p6GV3uK7h2NlTQ0qnKgwhMB2eFyb4q');

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, naziv_slike, obrisan,username,password) values ('Petar', 'Petrovic', 0, '1990-07-08', 43000.0, 'marko.jpg', false,'user5','$2a$12$2Juja5C8eWU3IDEH8FGN2.5p6GV3uK7h2NlTQ0qnKgwhMB2eFyb4q');

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, naziv_slike, obrisan,username,password) values ('Mirko', 'Mirkovic', 0, '1990-07-08', 43000.0, 'marko.jpg', false,'user6','$2a$12$2Juja5C8eWU3IDEH8FGN2.5p6GV3uK7h2NlTQ0qnKgwhMB2eFyb4q');

--zones
insert into zone (identification_number, naziv, template_path, obrisan) values ('12345', 'Terasica', 'terasica.jpg', false);
insert into zone (identification_number, naziv, template_path, obrisan) values ('56789', 'Potkrovlje', 'potkrovljice.jpg', false);

insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan, zone_id)values ('false', '12345112', 'astal1', 4, 195.5, 469.603, false, 1);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan, zone_id)values ('false', '12334512', 'astal2', 4, 195.5, 221.603, false, 1);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan, zone_id)values ('false', '12345212', 'astal3', 4, 651.5, 469.603, false, 2);

insert into zone_stolovi (zone_id, stolovi_id) values (1,1);
insert into zone_stolovi (zone_id, stolovi_id) values (1,2);
insert into zone_stolovi (zone_id, stolovi_id) values (2,3);

--jela

insert into artikli (naziv, trenutna_cena, obrisan, pic_name) values ('Pljeskavica', 240, false, 'pljeska.jpg');
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (1, 30000, 'Jelo od mlevenog mesa spremljeno na rostilju', 1, 2);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 240, 1);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Cevapi', 220, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (2, 30000, 'Rolovano jelo od mlevenog mesa spremljeno na rostilju', 1, 2);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 220, 2);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Kavijar', 1200, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (3, 30000, 'Luksuzno jelo od ribljih jaja', 0, 0);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 1200, 3);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Grcka salata', 300, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (4, 30000, 'Salata spremljena na grcki nacin sa povrcem i feta sirom', 2, 2);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 300, 4);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Rib-eye', 1100, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (5, 30000, 'Odrezak govedine spremljen na rostilju na nacin po zahtevu kupca', 1, 1);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 1100, 5);
--pice

insert into artikli (naziv, trenutna_cena, obrisan, pic_name) values ('Coca Cola 0.33l', 125, false, 'coke.jpg');
insert into pice (id) values (6);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 125, 6);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Sprite 0.33l', 125, false);
insert into pice (id) values (7);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', '2022-01-20 13:30:00', 125, 7);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-20 13:30:00', null, 150, 7);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Fanta 0.33l', 125, false);
insert into pice (id) values (8);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 125, 8);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Limunada 0.33l', 80, false);
insert into pice (id) values (9);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 80, 9);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Limanada 0.5l', 125, false);
insert into pice (id) values (10);
insert into cenovnik (pocetak_vazenja, kraj_vazenja, cena, artikal_id) values ('2022-01-10 13:30:00', null, 125, 10);

--porudzbine
insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2021-11-11', 'Posluziti hranu dok je topla.', false, 0, 1, 730.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 0, 1, 1, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 0, 6, 1, false);


insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2021-12-12', 'Dajte mi majoneza.', false, 1, 1, 860.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Stavite mi majoneza.', 0, 3, 2, false);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (1.0, 'Bez kiselih krastavaca.', 1, 4, 2, false);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (3.0, 'Stavite mi pavlake.', 2, 2, 2, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 0, 6, 2, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Bez leda.', 1, 7, 2, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2021-01-02', 'Dajte mi Kepac.', false, 3, 1, 920.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Stavite mi kepaca.', 3, 2, 3, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 2, 6, 3, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-25', 'Posluziti hranu dok je topla.', false, 0, 1, 730.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 0, 1, 4, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 0, 6, 4, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-23', 'Posluziti hranu dok je topla.', false, 4, 1, 855);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 3, 1, 5, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (3.0, 'Sa ledom.', 2, 6, 5, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-22', 'Posluziti hranu dok je topla.', false, 4, 1, 1040);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 3, 2, 6, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (4.0, 'Sa ledom.', 2, 7, 6, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-21', 'Posluziti hranu dok je topla.', false, 4, 1, 890);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 3, 2, 7, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (3.0, 'Sa ledom.', 2, 7, 7, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-20', 'Posluziti hranu dok je topla.', false, 4, 1, 2655);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 3, 5, 8, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 2, 7, 8, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (1.0, 'Sa ledom.', 2, 8, 8, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (1.0, 'Sa ledom.', 2, 9, 8, false);

--roles

insert into roles (role,deleted) values ('ROLE_ADMIN',false);
insert into roles (role,deleted) values ('ROLE_MANAGER',false);
insert into roles (role,deleted) values ('ROLE_KONOBAR',false);
insert into roles (role,deleted) values ('ROLE_SANKER',false);
insert into roles (role,deleted) values ('ROLE_KUVAR',false);
insert into roles (role,deleted) values ('ROLE_GLAVNI_KUVAR',false);

--user-roles

INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (1, 1); 
INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (2, 2); 
INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (3, 6);
INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (4, 3);
INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (5, 4);
INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (6, 5);
