insert into stolovi (zauzet) values(false);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, putanja_slike, obrisan, identification_number) values ('Perica', 'Peric', 0, '1990-01-01', 45000.0, 4, 'pera.jpg', false, '12345');
insert into menadzeri (id, korisnicko_ime, lozinka) values(1, 'pera', 'pera');


insert into plate (pocetak_vazenja, zaposleni_id) values ('2021-11-11', 1);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, putanja_slike, obrisan, identification_number) values ('Marko', 'Maric', 0, '1990-01-01', 60000.0, 3, 'marko.jpg', false, '54321');
insert into kuvari (id) values(2);
insert into glavni_kuvari (korisnicko_ime, lozinka, id) values ('marko', 'marko', 2);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, putanja_slike, obrisan, identification_number) values ('Jovana', 'Jovic', 0, '1990-07-08', 43000.0, 1, 'jovana.jpg', false, '13245');
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

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, konobar_id, sto_id, ukupna_cena) values ('2021-12-12', 'Dajte mi majoneza.', false, 1, 3, 1, 860.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Stavite mi majoneza.', 0, 3, 2, false);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (1.0, 'Bez kiselih krastavaca.', 1, 4, 2, false);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (3.0, 'Stavite mi pavlake.', 2, 2, 2, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 0, 6, 2, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Bez leda.', 1, 7, 2, false);

insert into porudzbine (datum_vreme, napomena, obrisan, status_porudzbine, konobar_id, sto_id, ukupna_cena) values ('2021-01-02', 'Dajte mi Kepac.', false, 3, 3, 1, 920.0);
insert into jela_porudzbine (kolicina, napomena, status_jela, jelo_id, porudzbina_id, obrisan) values (2.0, 'Stavite mi kepaca.', 3, 2, 3, false);
insert into pice_porudzbine (kolicina, napomena, status_pica, pice_id, porudzbina_id, obrisan) values (2.0, 'Sa ledom.', 2, 6, 3, false);
