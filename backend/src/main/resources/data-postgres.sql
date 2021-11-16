insert into stolovi (zauzet) values(false);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, putanja_slike, obrisan) values ('Perica', 'Peric', 0, '1990-01-01', 45000.0, 4, 'pera.jpg', false);
insert into menadzeri (id, korisnicko_ime, lozinka) values(1, 'pera', 'pera');


insert into plate (pocetak_vazenja, zaposleni_id) values ('2021-11-11', 1);

insert into zaposleni (ime, prezime, pol, datum_rodjenja, trenutna_plata, zaposlenje, putanja_slike, obrisan) values ('Marko', 'Maric', 0, '1990-01-01', 60000.0, 3, 'marko.jpg', false);
insert into kuvari (id) values(2);
insert into glavni_kuvari (korisnicko_ime, lozinka, id) values ('marko', 'marko', 2);

