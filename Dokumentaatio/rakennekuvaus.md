**SomewhereInTheCity-projektin rakennekuvaus**

Ohjelma rakentuu kolmesta pakkauksesta; kayttajat, sijainti ja kayttoliittyma. Erillistä main-luokkaa ei ole, vaan ohjelman suorituksen aloittaa KirjautumisIkkuna-luokan main-metodi.

*Kayttajat-pakkaus* sisältää Kayttaja-luokan, joka tarjoaa kaikkien käytttäjien yhteiset peruskentät kuten nimen, käyttäjätunnuksen ja salasanan, sekä niiden muuttamiseen ja tarkistamiseen tarvittavat metodit. Asiakas- ja Toimija-luokat perivät Käyttäjän ja tarjoavat kullekin käyttäjätyypille ominaiset lisäominaisuudet, kuten suosikkiruokapaikkojen seuraamisen asiakkaalle. Lisäksi pakkauksesta löytyy staattiset luokat asiakkaiden ja toimijoiden keräämiseksi HashSet-joukkoon (Asiakkaat ja Toimijat), joista niitä on helppo tarkastella esimerkiksi kirjautumisen yhteydessä.

*Sijainti-pakkaus* sisältää kaksi luokkaa; Kartta-luokan ja GoogleSijainti-luokan. Jälkimmäinen sisältää kentät ja metodit sijaintitietojen hakemiseen ja tarkasteluun, sekä joitain Google APIen käyttöön liittyviä ominaisuuksia joiden avulla oikeiden koordinaattien hakeminen voisi jatkossa olla mahdollista. GoogleSijainti-luokka tarjoaa myös ohjelmalle arvoOmaSijainti-metodin, joka yhdistää sattumanvaraisen pituuspiirin ja leveyspiirin Helsingin alueelta jotta kartta päivittyy realistisesti - samoja metodeita voisi hyvin pienillä muutoksilla käyttää myös oikeilla paikannetuilla sijaintitiedoilla. Pakkauksen toinen luokka, Kartta, vastaa staattisen kartan hakemisesta Goolen karttapalvelusta sijaintiolion mukaisesta paikasta. Kartan metodeita käytetään myös mm. tarkistamaan, onko jokin käyttäjä (suosikki) valitulla karttakuvalla - siis lähistöllä.

*Kayttoliittyma-pakkaus* sisältää kaksi JFrame/ActionListener-luokkaa; KirjautumisIkkunan ja OhjelmaIkkunan, sekä ohjelmaikkunan komponenttiluokat ja näistä irrallisen SuosikkiLähellä-luokan.
SuosikkiLahella on käyttää karttaa, sijaintia, asiakkaan ja toimijan metodeita sekä graafisia JLabeleitä luodakseen kulloinkin asiakkaan karttakuvassa näytettävät suosikkimyyjien sijaintimarkkerit. Se myös kommunikoi kiinteästi OhjelmaIkkunan komponenttien (Merkkipaneelin ja AsiakasPaneelin) kanssa -siksipä kyseinen luokka voisikin olla sijoitettuna mihin tahansa kolmesta pakkauksesta.

KirjautumisIkkuna kerää käyttäjältä tiedot kirjautumisesta tai uuden käyttäjän luonnista sekä luo tarvittavat käyttäjä-oliot jotka sitten siirtää parametrinaan OhjelmaIkkunalle tämän konstruktorissa onnistuneen kirjautumisen päätteeksi. OhjelmaIkkuna tutkii minkä tyyppinen käyttäjä ohjelmalla on, suorittaa tyypinmuunnoksen Asiakkkaaksi tai Toimijaksi ja rakentaa ohjelman graafisen liittymän käyttäjätyypin mukaiseksi. Molemmille tyypeille yhteisesti rakennetaan karttakuva (KarttaPaneeli) ja sen päälle markkereita näyttävä Merkkipaneeli, joka ovat Yläpaneelin(JLayeredPane) päällekkäisiä komponentteja.

Painamalla päivitänappia ohjelma hakee (=arpoo) sijainnin, rakentaa uuden kartan sen mukaan, asettaa omaa sijaintia kuvaavan markkerin keskelle kuvaa ja tutkii sitten asiakaskäyttöliittymässä onko avonaisia asiakkaan suosikiksi merkitsemiä toimijoita kartta-alueen sisällä. Jos kyllä, niin ohjelma rakentaa niille markkerit liukuvilla numeroilla ja näyttää kartan viereisessä infopaneelissa näiden toimijoiden nimet ja promotekstin.

18.10.2015


