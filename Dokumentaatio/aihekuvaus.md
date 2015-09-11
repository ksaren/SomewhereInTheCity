# Aihemäärittely

## Aihe:

**Helsingin** katuruokakulttuuri kukoistaa, ja tänä kesänä katukuvaan on ilmestynyt useita todella hyviä ruokarekkoja, -pyöriä ja -mopoja. Koska näillä **ketterillä kärryillä** on tapana liikkua paikasta toiseen, voi lempiruoan tai -kahvin saanti olla sattumankauppaa...

Tähän tarkoitukseen aion toteuttaa ohjelman jolla **asiakas** voisi paikantaa kyseiset kärryt, ja lähelle saapuvista **lempikojuista** saisi halutessaan **ilmoituksen**.

Ohjelman tällä kurssilla totetutettava versio tulee olemaan hyvin yksinkertaistettu ja toimimaan ns. **simulaattoritilassa**.

Ohjelman esimerkkinä toimii Helsingin Kahvikomppanian kahvimopo. Vastaavia ohjelmia on maailmalla käytössä (esim. Snaksy.com, osin FourSquare) mutta toistaiseksi mikään niistä ei toimi kunnolla Helsingissä.

## Ohjelman kuvaus:

Ohjelmaa voi käyttää asiakkaana tai **toimijana**. Kun ruokakärrytoimija saapuu **myyntipaikalle** ja on valmis **avaamaan luukut**, hän painaa **napista** joka automaattisesti päivittää kärryn **sijainnin** **kartalle** (ja päivittää **tilan** mahdollisen **lisäviestin** kera) ja ilmoitus kärrystä lähellä lähtee **kanta-asiakkaille**. Kun toimija lopettaa myynnin, hän painaa **toista nappia**, joka poistaa kärryn kartalta.
Asiakkaat käyttävät ohjelmaa **satunnaisasiakkaina** tai kanta-asiakkaina. Satunnaisasiakas päivittää sijaintinsa kartalle ja saa tiedon **lähellä olevista myyntikärryistä**. Kanta-asiakkaan sijainti päivittyy automaattisesti ja hänelle tulee ilmoitus kun **suosikiksi** merkitty myyntikärry on saapunut lähelle.

## Käyttäjät:

- Hyvästä ruuasta ja kahvista kiinnostuneet satunnaiset kaupunkilaiset ("Asiakkaat")
- Usein tietystä myyntikärryllä asioivat ("Kantikset")
- Katuruokayritykset ("Toimijat")
- ...

## Kaikkien käyttäjien toiminnot:
- Oman sijainnin näyttäminen kartalla
- (Valitun) myyntikärryn sijainti kartalla
- ...

## Asiakkaan toiminnot
- Myyntikärryn asettaminen seurattavaksi (jolloin tieto kärryn läheisyydestä tulee asiakkaalle -> Kantis)
- Viestin lähetys myyjälle
- Toimijan ilmoitusten lukeminen
- Toimijan ilmoitusten tilaaminen 

## Myyjän toiminnot
- Sijainnin lähetys kartalle
- Statuksen asettaminen (AUKI/KIINNI)
- Promoviestit nähtäväksi kartalta

## Sanastoa:
- *Toimija*: StreetFood-toimija, joka vastaa myyntikärryn tietojen päivityksestä.
- *Asiakas*: Palvelujen käyttäjä, jakautuu kahteen asiakastyyppiin
    - *Satunnaisasiakas*: Hakee lähellä olevia kohteita ja paikantaa sijaintinsa manuaalisesti
    - *Kantis*: Antaa luvan sijainnin seurantaan ja merkitsee suosikiksi joitain kohteita.
- *Myyntikärry*: Ruoan tai kahvin myyntipaikka, josta tuotteen voi ostaa
- *Suosikki*: Kantis voi merkitä tietyn toimijan suosikkilistalleen, jolloin sen saapuminen lähelle antaa kantikselle asiasta ilmoituksen.


