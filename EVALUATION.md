# Vertaisarviointi

|            |               |
| ---------- | ------------- |
| Arvioija   | Jani Koski    |
| Arvioitava | Leevi Kukkula |

## Käytettävyys

---

Sovellus kaatuu avattaessa ja android studio antaa virheen "java.lang.NumberFormatException: For input string: "10,10"", sovellus lähtee toimimaan vaihtamalla puhelimen käyttökieleksi englanti.

Sovellus kaatuu myös mikäli hakuehdoksi laittaa tekstiä mitä ei haetusta datasta löydy.

Kryptovaluuttojen selaus on selkeästi näkyvillä ja niiden haku on tehty helpoksi. Listauksessa näkyy olennaisimmat tiedot ja painamalla valuuttaa avautuu uusi näkymä missä on muita valuuttaan liittyviä tietoja.

My portfolioon ostaminen on tehty hyvin, mutta myymisessä täytyy ensin etsiä valuutta listasta ja sen jälkeen laittaa myyntimäärä. Nykyinen valuuttamäärä mitä on ostettuna voisi näkyä valitun valuutan näkymässä.

My portfoliossa näkyviä valuuttoja ei pysty painamaan, paremman käytettävyyden puolesta näitä painamalla voisi avautua suoraan näkymä, mistä voisi kyseistä valuuttaa myydä tai ostaa.

Kokonaisuudessaan käyttöliittymä on selkeä ja helppo käyttää poislukien omien valuuttojen myyminen/ostaminen.

## Tekniset ominaisuudet(vaikeusaste)

---

Teknisiltä ominaisuuksilta koodi on 3/5. Koodissa on käytetty tallennusta puhelimen sisäiseen muistiin, datan filtteröintiä käyttäjän syötteen mukaan ja tehty omat adapterit recyclerviewille

## Dokumentaatio

---

Koodin kommentointi on tehty hyvin, tarvittavat funktiot on kommentoitu sisältäen selitykset myös funktion parametreille.

README.md tiedostosta löytyy vain pakolliset tiedot. README.md:hen olisi voinut sisällyttää sovelluksen käyttöohjeet, kuvia sovelluksesta ja sovelluksessa käytetyt kirjastot.

## Koodin laatu

---

Koodissa muuttujien nimeäminen oli hyvää, niistä ei tarvitse arvailla mihin niitä käytetään.

Koodia on pilkottu hyvin osiin ja tehty funktioita uudelleen käytettävälle koodille.

Koodista voisi vielä siistiä ylimääräiset importit ja turhat logitukset pois.

## Yleiset arviointikriteerit

---

Projektia ajettaessa puhelimelle ei sovellus toiminut suoraan vaan vaati laitteen kielen muuttamista englanniksi (Taitaa johtua , ja . käyttöeroista eri maiden välillä).

Sovelluksen käytön aikana ilmeni muutamia sovelluksen kaatavia bugeja. Sovellus kaatuu, mikäli "next page" näppäintä painaa liian nopeasti ja jos hakuun laittaa tekstiä mitä ei datasta löydy.

Androidin omia API:a ei käytetty.

Hyvää projektissa oli selkeä käyttöliittymä, hyvin kommentoitu koodi ja selkeästi nimetyt muuttujat.

Näiden perusteella antaisin arvosanaksi 2-3.
