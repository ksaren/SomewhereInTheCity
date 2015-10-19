**Testausdokumentti kirjautumistoimista**
Somewhere in the city

1. Syötetään kaikkiin kenttiin tyhjää:
- Ohjelma luo käyttäjäolion ja asettaa sen oletuksena asiakastyyppiseksi. Kirjautumisen lopuksi ohjelma antaa virheilmoituksen epäonnistuneesta kirjautumisesta ja pyytää yrittämään alusta.

2. Syötetään kaikki arvot kuten on ajateltu. Käyttäjätunnus on vapaa:
- Sekä ruokatoimijan että asiakkaan luonti onnistuvat normaalisti.

3. Salasanansyöttövirheet:
- Uutta käyttäjää luodessa ohjelma hyväksyy salasanan vain jos se on väh. 6 merkkiä ja salasana on molemmilla syöttökerroilla sama.

4. Luodaan uusi käyttäjä olemassaolevilla tiedoilla:
- Ohjelma ilmoittaa virheestä ja pyytää siirtymään alkuun tai lopettamaan.



**JUnit-testien tarkastelua**
Ohjelmaa ei ole testattu JUnit-testeillä aukottomasti. Testikattavuus Käyttäjät- ja Sijaintipakkauksessa on noin 50-80%, joskin demokäyttöön suunnitellut olionluontimetodit - joita ei ole testattu ollenkaan - heikentävät varsinkin toimija-luokan testikattavuutta.

Ohjelmaa on testattu melko monipuolisesti testikäyttäjillä, ja kaikki testaajien löytämät bugit on korjattu. Ohjelma toimiikin varsin vakaasti eikä sitä ainakaan oma testaajaporukkani ole saanut enää kaatumaan tai toimimaan väärin, kunhan tarvittavat kuvatiedostot ovat saatavilla.
