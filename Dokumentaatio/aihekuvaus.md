# Aihemäärittely

## Aihe:

Helsingin katuruokakulttuuri kukoistaa, ja kuluneena kesänä katukuvaan on ilmestynyt useita todella hyviä ruokarekkoja, -pyöriä ja -mopoja. Koska näillä **ketterillä kärryillä** on tapana liikkua paikasta toiseen, voi lempiruoan tai -kahvin saanti olla sattumankauppaa...

Tähän tarkoitukseen aion toteuttaa ohjelman jolla **asiakas** voisi paikantaa kyseiset kärryt, ja lähelle saapuvista **lempikojuista** saisi halutessaan **ilmoituksen**.

Ohjelman tällä kurssilla totetutettava versio tulee olemaan hyvin yksinkertaistettu ja toimimaan ns. **simulaattoritilassa**.

Ohjelman innoittajana toimii Helsingin Kahvikomppanian kahvimopo - oman perheen yritys. Suunnitellunkaltaisia ohjelmia on maailmalla käytössä (esim. Snaksy.com, Truckily, osin FourSquare).

## Ohjelman kuvaus:

Ohjelmaa voi käyttää asiakkaana tai toimijana. Kun ruokakärrytoimija saapuu myyntipaikalle ja on valmis avaamaan luukut, hän painaa napista tilan avoimeksi, jolloin automaattisesti kärryn sijainnin näkee kartalla). Kun toimija lopettaa myynnin, hän painaa lopeta myynti-nappia, joka poistaa kärryn kartalta. Asiakkaat näkevät kartalla auki olevat lähimmät ruokapaikkansa.

## Käyttäjät:

- Hyvästä ruuasta ja kahvista kiinnostuneet satunnaiset kaupunkilaiset ("Asiakkaat")
- Usein tietystä myyntikärryllä asioivat ("Asiakkaat")
- Katuruokayritykset ("Toimijat")
- ...

## Kaikkien käyttäjien toiminnot:
- Oman sijainnin näyttäminen kartalla

## Asiakkaan toiminnot
- Myyntikärryn asettaminen suosikiksi (jolloin tieto kärryn läheisyydestä tulee asiakkaalle)
- Toimijan ilmoitusten lukeminen

## Myyjän toiminnot
- Sijainnin lähetys kartalle
- Statuksen asettaminen (AUKI/KIINNI)
- Promoviestit nähtäväksi kartalta

## Sanastoa:
- *Toimija*: StreetFood-toimija, joka vastaa myyntikärryn tietojen päivityksestä.
- *Asiakas*: Palvelujen käyttäjä, antaa luvan sijainnin seurantaan ja merkitsee suosikiksi joitain kohteita.
- *Myyntikärry*: Ruoan tai kahvin myyntipaikka, josta tuotteen voi ostaa
- *Suosikki*: Asiakas voi merkitä tietyn toimijan suosikkilistalleen, jolloin sen sijainti lähellä näkyy asiakkaan karttakuvassa.


