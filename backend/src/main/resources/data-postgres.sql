insert into stolovi (zauzet) values(false);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, putanja_slike, obrisan) values ('Perica', 'Peric', 0, '1990-01-01', 45000.0, 4, 'pera.jpg', false);
insert into menadzeri (id, korisnicko_ime, lozinka) values(1, 'pera', 'pera');


insert into plate (pocetak_vazenja, zaposleni_id) values ('2021-11-11', 1);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, putanja_slike, obrisan) values ('Marko', 'Maric', 0, '1990-01-01', 60000.0, 3, 'marko.jpg', false);
insert into kuvari (id) values(2);
insert into glavni_kuvari (korisnicko_ime, lozinka, id) values ('marko', 'marko', 2);

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