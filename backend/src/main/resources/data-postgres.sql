insert into stolovi (zauzet) values(false);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, naziv_slike, obrisan, identification_number) values ('Perica', 'Peric', 0, '1990-01-01', 45000.0, 4, 'pera.jpg', false, '12345');
insert into menadzeri (id, korisnicko_ime, lozinka) values(1, 'pera', 'pera');


insert into plate (pocetak_vazenja, zaposleni_id) values ('2021-11-11', 1);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, naziv_slike, obrisan, identification_number) values ('Marko', 'Maric', 0, '1990-01-01', 60000.0, 3, 'marko.jpg', false, '54321');
insert into kuvari (id) values(2);
insert into glavni_kuvari (korisnicko_ime, lozinka, id) values ('marko', 'marko', 2);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, naziv_slike, obrisan, identification_number) values ('Jovana', 'Jovic', 0, '1990-07-08', 43000.0, 1, 'jovana.jpg', false, '13245');
insert into konobari (id) values(3);

--jela

insert into artikli (naziv, trenutna_cena, obrisan) values ('Pljeskavica', 240, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (1, 30000, 'Jelo od mlevenog mesa spremljeno na rostilju', 1, 2);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Cevapi', 220, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (2, 30000, 'Rolovano jelo od mlevenog mesa spremljeno na rostilju', 1, 2);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Kavijar', 1200, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (3, 30000, 'Luksuzno jelo od ribljih jaja', 0, 0);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Grcka salata', 300, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (4, 30000, 'Salata spremljena na grcki nacin sa povrcem i feta sirom', 2, 2);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Rib-eye', 1100, false);
insert into jela (id, vreme_pripreme_mils, opis,kategorija_jela, tip_jela) values (5, 30000, 'Odrezak govedine spremljen na rostilju na nacin po zahtevu kupca', 1, 1);

--pice

insert into artikli (naziv, trenutna_cena, obrisan) values ('Coca Cola 0.33l', 125, false);
insert into pice (id) values (6);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Sprite 0.33l', 125, false);
insert into pice (id) values (7);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Fanta 0.33l', 125, false);
insert into pice (id) values (8);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Limunada 0.33l', 80, false);
insert into pice (id) values (9);

insert into artikli (naziv, trenutna_cena, obrisan) values ('Limanada 0.5l', 125, false);
insert into pice (id) values (10);

--porudzbine
insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, konobar_id, sto_id, ukupna_cena) values ('2021-11-11', 'Posluziti hranu dok je topla.', false, 0, 3, 1, 730.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 0, 1, 1, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 0, 6, 1, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, konobar_id, sto_id, ukupna_cena) values ('2021-11-10', 'Posluziti hranu brzo, gladne musterije.', false, 1, 3, 1, 690.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Lepo spremiti.', 1, 2, 2, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 1, 6, 2, false);

--roles

insert into roles (role,deleted) values ('ROLE_ADMIN',false);
insert into roles (role,deleted) values ('ROLE_MANAGER',false);
insert into roles (role,deleted) values ('ROLE_KONOBAR',false);
insert into roles (role,deleted) values ('ROLE_SANKER',false);
insert into roles (role,deleted) values ('ROLE_KUVAR',false);
insert into roles (role,deleted) values ('ROLE_GLAVNI_KUVAR',false);

--users

insert into users (username,password,deleted) values ('user1','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW',false);
insert into users (username,password,deleted) values ('user2','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW',false);
insert into users (username,password,deleted) values ('user3','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW',false);

--user-roles

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 6); 
