# Opdrachtbeschrijving

## Inleiding

Je bent net begonnen als developer bij een bedrijf genaamd Tech It Easy, dat TV's verkoopt. Tijdens de cursus Spring Boot ga jij een backend applicatie voor hen programmeren. De winkel heeft een inventaris van televisies die moet worden bijgehouden. Na iedere les gaan we deze applicatie een stukje verder uitbouwen in de vorm van huiswerk. Zo krijgen we stap-voor-stap meer ervaring in het bouwen van een backend applicatie. Aan het einde van de cursus zullen we een werkende Tech It Easy backend hebben!

## Recap van vorige opdracht

Je hebt de vorige les de applicatie voor je werkgever TechItEasy uitgebreid met een `Model` en een `Repository`. Een `Model` is een _Klasse_ in je project en bevat _variabele_ en _constructors_, en geven de mogelijkheid om meerdere objecten aan te maken in je database van deze _Klasse_. De `Repository` is verlengd met de `JpaRepository`, daardoor kan je de standaard find functies uit gaan voeren in de `Service` die we komende les gaan toevoegen aan het project. Naast de standaard _query's_ die al staan in de `JpaRepository` kunnen we later eventueel nog _query's_ schrijven in de `Repository` voor wat moeilijkere _find_ functies. Maar hoe gaaf zou het zijn als het project zou kunnen draaien? Goed nieuws na deze les moet het project werken en de juiste response geven, als we requests doen via `Postman`.

## Randvoorwaarden

- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `Services`;
- De map `Services` bevat een _klasse_ genaamd `TelevisionService`;
- De `TelevisionService` bevat:
  - [x] de juiste _annotatie_
  - [x] een _private_ variable `TelevisionRepository`
  - [x] de connectie van de `Service` en de `Repository` door middel van een _autowired_
  - [x] een _functie_ voor het ophalen van alle `Televisions`
  - [x] een _functie_ voor het ophalen van 1 `Television`
  - [x] een _functie_ voor het opslaan van 1 `Television`
  - [x] een _functie_ voor het verwijderen van 1 `Television`
  - [] een _functie_ voor het update van 1 `Television`
- [x] De `Controller` is door middel van een _autowired_ gelinkt aan de `Service`
- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `Dtos`
- De map `Dtos` bevat een `TelevisionDto` en een `TelevisionInputDto`
- [x] De _requestMappings_ in de `Controller` worden aangepast zodat deze de juiste response doorgeven via de `Service`
- [] De `Service` maakt gebruik van de gegevens die via de `Controller` doorkrijgen van de `Dtos`
- [x] Het project bevat de _validator_ dependency uit hoofdstuk 8.1 van de Spring Boot cursus in `Edhub`

### Belangrijk
- Na deze les moeten de _requestMappings_ in de controller de juiste responses geven
- Na deze les is de applicatie voor het eerst functioneel

- Het is belangrijk goed te snappen hoe de lagen in onze multi-tier application samenwerken:
  - [] De `Controller` ontvangt een verzoek op een _endpoint_, als er _variable_ worden meegeven aan dit verzoek komen die binnen via de `TelevisionInputDto` ->
  - [] De `Controller` geeft aan de hand van het verzoek en eventuele meegekregen _variable_ dit door aan de `Service` ->
  - [] De `Service` spreekt aan de hand van de geschreven functie de `Repository` aan met de juiste _find_ functie/query ->
  - [] De `Repository` gaat aan de hand van het bijbehorende `Model` zoeken naar de juiste gegevens en stuurt deze terug naar de `Service` ->
  - [] De `Service` past de logica toe uit de functie en geeft de response aan de hand van de `TelevisionDto` terug aan de `Controller`
    (De controller wordt vanuit de _front-end_ of vanuit _Postman_ aangesproken, en geeft ook het antwoord weer terug.)

## Stappenplan
_Let op_: het is uitdagender om jouw eigen stappenplan te maken. Mocht je niet zo goed weten waar je moet beginnen, kun je onderstaand stappenplan volgen:
1. [x] Voeg de benodigde dependencies toe aan je _POM.xml_ en laat `Maven` deze instaleren
2. [x] Maak een map genaamd `Services` aan in de map-structuur
3. [x] Maak een _klasse_ genaamd `TelevisionService` aan in de map `Services`
4. [x] Voeg de `Service` _annotatie_ boven de _klasse_ toe
5. [x] Maak in de `TelevisionService` een _private_ variabele aan voor de `TelevisionRepository`
6. [x] Voeg een `@Autowired` toe om de `Repository` in de `TelevisionService` te kunnen gebruiken
7. [x] Maak in de `TelevisionService` de functie _getTelevisions_ aan de hand van het voorbeeld in hoofdstuk 6.2 van de cursus Spring Boot cursus in `Edhub`
8. [x] Doe dit ook voor de _getTelevision_, _saveTelevision_, _updateTelevision_ en de _deleteTelevision_
9. [x] Maak in de `TelevisionController` een `@Autowired` om de `Service` te kunnen gebruiken in de `Controller`
10. [x] Maak in het project een map aan genaamd `Dtos`
11. [x] Maak in deze map een klasse aan genaamd `TelevisionDto`
12. [x] Defenieer in deze klasse alle variabele die een `Television` object bevat (dezelfde als in het model gedefinieerd staan)
13. [x] Maak daaronder een `public` `static` functie aan genaamd _fromTelevision_. Deze heeft als returnwaarde `TelevisionDto` en als attribuut `Television television`
14. [x] Maak in deze functie een `var dto = new TelevisionDto();`
15. [x] Voeg in de functie voor iedere _variable_, die je boven deze functie hebt gedeclareerd, een toewijzing naar de dto variable (bijvoorbeeld: `dto.name = television.getName();` )
16. [x] Return als laatste stap in deze functie, het _dto_ object en sluit de functie af
17. [x] Maak een klasse aan in de map `Dtos` genaamd `TelevisionInputDto`
18. [x] Defenieer ook in deze klasse alle variabelen die een `Television` object bevat
19. [] Maak een `public` functie genaamd `toTelevision()` met returnwaarde `Television`
20. [] Maak in deze functie een `var television = new Television();`
21. [] Voeg in de functie voor iedere _variable_, die je boven deze functie hebt gedeclareerd, een toewijzing naar de television variable (bijvoorbeeld: `television.setName(name);` )
22. [] Pas de `RequestMappings` in de `TelevisionController`, zodat de controller het verzoek doet aan de _servicelaag_ en het response ook ontvangt van de _servicelaag_ (dit omdat we de `Controller` eigenlijk zo dom mogelijk willen houden)
23. [] Pas de _servicelaag_ zo aan dat deze gebruik maakt van de gegevens die de _controllerlaag_ doorgeeft, de `Service` moet hier dan de logica aan toepassen om met gebruik van de `Dtos` een _returnwaarde_ terug te kunnen sturen naar de controller