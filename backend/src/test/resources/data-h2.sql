/*insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, naziv_slike, obrisan, identification_number,username,password) values ('Perica', 'Peric', 0, '1990-01-01', 45000.0, 1, 'pera.jpg', false, '12345','user1','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW');

insert into plate (pocetak_vazenja, zaposleni_id) values ('2021-11-11', 1);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, naziv_slike, obrisan, identification_number,username,password) values ('Marko', 'Maric', 0, '1990-01-01', 60000.0, 3, 'marko.jpg', false, '54321','user2','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW');

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, naziv_slike, obrisan, identification_number,username,password) values ('Jovana', 'Jovic', 0, '1990-07-08', 43000.0, 3, 'jovana.jpg', false, '13245','user3','$2a$12$XG8yzdx3RghoK0y2RnMON.gLs1ky7e/R4toD.kSCwIokZWKKdIVSW');

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, naziv_slike, obrisan, identification_number,username,password) values ('Jovana', 'Jovic', 0, '1990-07-08', 43000.0, 1, 'jovana.jpg', false, '13247','user4','dsasd');
insert into konobari (id) values(4);
*/
--zones
insert into zone (identification_number, naziv, template_path, obrisan) values ('12345', 'Terasica', 'terasica.jpg', false);
insert into zone (identification_number, naziv, template_path, obrisan) values ('56789', 'Potkrovlje', 'potkrovljice.jpg', false);

insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values (true, '12345112', 'astal1', 4, 170.0, 470.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values (true, '12334512', 'astal2', 4, 170.0, 320.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values (true, '12345212', 'astal3', 4, 170.0, 170.0, false);

insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '22345112', 'astal4', 4, 320.0, 470.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '22334512', 'astal5', 4, 320.0, 320.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '22345212', 'astal6', 4, 320.0, 170.0, false);

insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '32345112', 'astal7', 4, 470.0, 470.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '32334512', 'astal8', 4, 470.0, 320.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '32345212', 'astal9', 4, 470.0, 170.0, false);



insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '42345112', 'astal10', 4, 170.0, 470.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '42334512', 'astal11', 4, 170.0, 320.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '42345212', 'astal12', 4, 170.0, 170.0, false);

insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '52345112', 'astal13', 4, 320.0, 470.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '52334512', 'astal14', 4, 320.0, 320.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '52345212', 'astal15', 4, 320.0, 170.0, false);

insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '62345112', 'astal16', 4, 470.0, 470.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '62334512', 'astal17', 4, 470.0, 320.0, false);
insert into stolovi (zauzet, identification_number, naziv_stola, broj_mesta, pozicija_x, pozicija_y, obrisan)values ('false', '62345212', 'astal18', 4, 470.0, 170.0, false);



insert into zone_stolovi (zone_id, stolovi_id) values (1,1);
insert into zone_stolovi (zone_id, stolovi_id) values (1,2);
insert into zone_stolovi (zone_id, stolovi_id) values (1,3);
insert into zone_stolovi (zone_id, stolovi_id) values (1,4);
insert into zone_stolovi (zone_id, stolovi_id) values (1,5);
insert into zone_stolovi (zone_id, stolovi_id) values (1,6);
insert into zone_stolovi (zone_id, stolovi_id) values (1,7);
insert into zone_stolovi (zone_id, stolovi_id) values (1,8);
insert into zone_stolovi (zone_id, stolovi_id) values (1,9);

insert into zone_stolovi (zone_id, stolovi_id) values (2,10);
insert into zone_stolovi (zone_id, stolovi_id) values (2,11);
insert into zone_stolovi (zone_id, stolovi_id) values (2,12);
insert into zone_stolovi (zone_id, stolovi_id) values (2,13);
insert into zone_stolovi (zone_id, stolovi_id) values (2,14);
insert into zone_stolovi (zone_id, stolovi_id) values (2,15);
insert into zone_stolovi (zone_id, stolovi_id) values (2,16);
insert into zone_stolovi (zone_id, stolovi_id) values (2,17);
insert into zone_stolovi (zone_id, stolovi_id) values (2,18);

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

--stare - naplacene
insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-23', 'Posluziti hranu dok je topla.', false, 4, 1, 730.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 3, 1, 1, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 2, 6, 1, false);


insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2021-12-12', 'Dajte mi majoneza.', false, 4, 1, 860.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Stavite mi majoneza.', 3, 3, 2, false);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (1.0, 'Bez kiselih krastavaca.', 3, 4, 2, false);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (3.0, 'Stavite mi pavlake.', 3, 2, 2, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 2, 6, 2, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Bez leda.', 2, 7, 2, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2021-01-02', 'Dajte mi Kepac.', false, 4, 1, 920.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Stavite mi kepaca.', 3, 2, 3, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 2, 6, 3, false);


--trenutne

--KREIRANA za preuzimanje kuvar, ne menja se status porudzbine   id = 4
insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-28', 'Gosti zure, posluziti brzo.', false, 0, 1, 2650.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 0, 3, 1, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 0, 6, 1, false);

--KREIRANA za pripremanje sanker, jelo vec spremno, menja se status porudzbine u PRIPREMLJENO   id = 5
insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-28', 'Gosti jako gladni.', false, 0, 2, 2725.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Sa svezim sastojcima.', 2, 3, 1, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (3.0, 'Sa ledom.', 0, 6, 1, false);

--PREUZETA za pripremanje kuvar, pice vec spremno, menja se status porudzbine u PRIPREMLJENO   id = 6
insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, sto_id, ukupna_cena) values ('2022-01-28', 'Gosti jako gladni.', false, 1, 3, 690.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Puno soli.', 1, 2, 1, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 1, 8, 1, false);

--roles

insert into roles (role,deleted) values ('ROLE_ADMIN',false);
insert into roles (role,deleted) values ('ROLE_MANAGER',false);
insert into roles (role,deleted) values ('ROLE_KONOBAR',false);
insert into roles (role,deleted) values ('ROLE_SANKER',false);
insert into roles (role,deleted) values ('ROLE_KUVAR',false);
insert into roles (role,deleted) values ('ROLE_GLAVNI_KUVAR',false);

--user-roles
/*
INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (1, 1); 
INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (2, 2); 
INSERT INTO ZAPOSLENI_ROLE (zaposleni_id, role_id) VALUES (3, 6);
*/
