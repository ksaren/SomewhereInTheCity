# SomewhereInTheCity

Ohjelmoinnin harjoitustyönä toteutettu ohjelma jolla katuruokayrittäjät ilmoittavat missä ovat ja asiakkaat voivat seurata lempiruokapaikkojensa liikkeitä.

1. Huomio ohjelman käyttämisestä.
Ohjelma on toteutettu demotoiminta-asteelle, eikä sitä tälläisenaan voi käyttää oikeaan paikannukseen. Ohjelmasta puuttuu todellinen paikannus ja tiedostojen tallennus - kaikki ohjelmaan syötetyt tiedot ovat suoritusaikaisia.

Ohjelman graafinen käyttäliittymä ei (todellakaan) ole loppuun asti hiottu, tämän vuoksi mm. joissakin tapauksissa teksti saattaa esimerkiksi joutua hieman ikkuna-alueen reunan yli ja kaikki tekstialueet eivät aina skaalaudu kauniisti.

Ohjelmaa voi testata esimerkiksi seuraavilla mallikäyttäjätiedoilla, jotka löytyvät ohjelmasta aina käynnistyksen jälkeen:

**Asiakas**
Teppo Testaaja
käyttäjätunnus: *tt*
salasana: *123456*

**Toimija**
Suvin Sumppila
käyttäjätunnus: *susu*
salasana: *pannukuuma*

2. Riippuvuudet
Ohjelma tarvitsee toimiakseen kolme pientä kuvatiedostoa, jotka löytyvät toimitetusta zip-paketista mutta eivät jar-tiedostosta. Ohjelma tarvitsee myös internet-yhteyden, sillä se hakee googlen karttapalvelusta karttatietoja.

3. Käyttöohjeet
- Käynnistä ohjelma
- Aukeaa kirjautumisikkuna, valitse joko kirjautuminen tai uuden käyttäjän luonti.
	-- Uutta käyttäjää luodessa voit valita haluatko luoda tilin ruokatoimijalle vai asiakkaalle.
	-- Jos kirjaudut testivaiheen ohjelmaan, käytä mallitunnuksia jotka on luotu ohjelman käynnistyksen yhteydessä (kts. yllä ja 		"luoMalliAsiakkaat"/"luoMalliToimijat"-metodissa).
- Onnistuneen kirjautumisen päätteeksi aukeaa ohjelmaikkuna.
	Asiakasikkuna:
		-- Päivitä -päivittää kartan ja sen toimijat
		-- Suosikkien tallennus -valitse listalta lempitoimijasi ja paina tähtinappia. Valitut suosikit näkyvät ikkunan oikeassa alalaidassa.
		-- Kartan alueella olevat suosikit näkyvät sekä markkerina kartalla, että merkkiä vastaavan numeron kanssa infopaneelissa oikeassa 			reunassa.
	Toimijaikkuna:
		-- Päivitä -päivittää kartan
		-- Avaa/sulje myynti -vain avonaisten toimijoiden sijainnit näkyvät asiakkaiden kartalla
		-- Promoteksti -kirjoita teksti ikkunan alareunan tekstialueelle ja paina aseta-nappia. Teksti näkyy asiakkaalla kun tämä päivittää 			karttansa.

4. Käytön lopetus
- Ohjelma suljetaan X-napista sivun vasemmassa yläkulmassa.

19.10.2015
	


