# Opdrachtbeschrijving

## Inleiding

Je bent net begonnen als developer bij een bedrijf genaamd Tech It Easy, dat TV's verkoopt. Tijdens de cursus Spring Boot ga jij een backend applicatie voor hen programmeren. De winkel heeft een inventaris van televisies die moet worden bijgehouden. Na iedere les gaan we deze applicatie een stukje verder uitbouwen in de vorm van huiswerk. Zo krijgen we stap-voor-stap meer ervaring in het bouwen van een backend applicatie. Aan het einde van de cursus zullen we een werkende Tech It Easy backend hebben!

## Recap van vorige opdracht

Je hebt inmiddels je applicatie draaiend gekregen, dit geeft je de mogelijkheid om in een database bij te gaan houden welke tv's er in de inventaris van TechItEasy. Als je de benamingen hebt aangehouden die we tot nu toe hebben gebruikt, kunnen we een _data.sql_ gaan maken waardoor we gegevens in de database kunnen zetten. Dit is een soort van pre-fill van je database. Ook gaan we na deze les wat relaties toevoegen aan je applicatie. 

## Randvoorwaarden

- Het project bevat de volgende `Models`:
  - `Television`
  - `RemoteController` met de _variables_: 
    -_Long_`id`
    -_String_`compatibleWith`
    -_String_`batteryType`
    -_String_`name`
    -_String_`brand`
    -_Double_ `price`
    -_Integer_ `originalStock`
  - `CI-Module` met de _variables_:
    -_Long_ `id`
    -_String_ `name`
    -_String_ `type`
    -_Double_ `price`
  - `WallBracket` met de _variables_:
    -_Long_ `id`
    -_String_ `size`
    -_Boolean_ `ajustable`
    -_String_ `name`
    -_Double_ `price`
- Voor elk van deze modellen bevat je applicatie ook een `Repository`, `Controller`, `Dto`, `InputDto` en een `Service`
- Een `OneToOne` relatie tussen `Television` en `RemoteController`
- Een `OneToMany` relatie tussen `Television` en `CI-Module`
- Een `ManyToMany` relatie tussen `Television`en `WallBracket`

### Belangrijk
Je mag de constructors uit de modellen weglaten. Deze vult spring boot automatisch in als niet gedefinieerd zijn. Ook uit de `Television` mogen de constructors verwijderd worden.
In deze opdracht heb ik een voorbeeld relatie uitgelegd aan de hand van het stappenplan. Als je hier makkelijk door heen gaat, mag je ook de _one to many_ relatie maken tussen `Television` en `CIModule`. Hierbij is voor jouw applicatie van toepassing dat meerdere tv's één ci-module kunnen hebben. 
Als zelfs de _one to many_ redelijk eenvoudig is voor je mag je een _many to many_ relatie leggen tussen `Television` en `WallBracket`. Dus meerdere tv's kunnen meerdere walbrackets hebben en andersom. Deze antwoorden komen volgende week wel in de antwoorden te staan.

## Stappenplan
_Let op_: het is uitdagender om jouw eigen stappenplan te maken. Mocht je niet zo goed weten waar je moet beginnen, kun je onderstaand stappenplan volgen:
1. Maak in de map `Models` _klasse_ aan voor `RemoteController`, `CI-Module` en `WallBracket` (voeg de juiste _annotatie_, _variables_, _getters&setters_ en _constructors_ toe)
2. Maak in de map `Repositories` voor elk model een `Repository` aan (die elk de `JpaRepository` _extends_)
3. Maak in de map `Controllers` voor elk model een `Controller` aan (met juiste _annotatie_, _@Autowired_ en _requestMappings_)
4. Maak in de map `Dtos` voor elk model een `Dto` en `InputDto` aan (met juiste _variables_ en toewijzingen)
5. Maak in de map `Services` voor elk model een `Service` aan (met juiste _annotatie_, _@Autowired_ en _functions_)
6. Leg een OneToOne relatie tussen `Television` en `RemoteController` door in beide _models_ _@OneToOne_ toe te voegen, gevolgd door het model waar de relatie mee ligt in de vorm van `Model` `object` (bijvoorbeeld `Television` `television`) op de volgende regel
7. Een OneToOne relatie heeft een eigenaar van de relatie nodig, maak de `Television` eigenaar door in `RemoteController` achter de _@OneToOne_ _mappedBy_ toe te voegen op deze manier _@OneToOne(mappedBy = "remotecontroller"). Dit zorgt er voor dat in de `Television` tabel een kolom toegevoegd met de naam `remotecontroller_id`. Vergeet niet de getter en setter toe te voegen na het leggen van de relatie in de modellen
8. Om deze kolom ook te vullen zal je in _servicelaag_ ook een functie moeten maken die een koppeling maakt tussen de `Television` en de `RemoteController`. Dit doe je in de `TelevisionService` 
9. Voeg de functie "assignRemoteControllerToTelevision" toe in de `TelevisionService`. Zoals je ziet herkent de `TelevisionService` de `RemoteControllerRepository` niet, dit komt omdat we deze nog niet gekoppeld hebben met een _@Autowired_, gelukkig hoef je niet alles opnieuw te doen. Je kan bovenaan in de `TelevisionService` onder de private `TelevisionRepository` een private `RemoteControllerRepository` declareren. En dan in de bestaande _@Autowired_ deze toevoegen op dezelfde manier als de `TelevisionRepository`. Dit resulteerd in: 
`@Autowired
 public TelevisionService (TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository) {
 this.televisionRepository = televisionRepository;
 this.remoteControllerRepository = remoteControllerRepository;}`
10. Om deze functie uit te kunnen voeren moet je in de `TelevisionController` een _PutRequest_ maken met endpoint _"/televisions/{id}/remotecontroller"_ om aan te spreken. Voeg deze toe en geef de _televisionId_ mee als _@PathVariable_ en de _remoteControllerId_ als _@RequestBody_ doormiddel van een `IdInputDto` _input_.  
11. Hiervoor missen je nog de `IdInputDto`. Maak in het mapje `Dtos` een nieuwe klasse aan voor de `IdInputDto`. Declareer in deze dto een _public Long id_ toe, meer hoeft er niet in
12. Gefeliciteerd, je hebt zo juist de eerste relatie gelegd in je applicatie.
13. Alleen als je nu met een get alle `Televisions` ophaalt zien je geen `RemoteController`. Dit komt omdat we in de `TelevisionDto` nog niks hebben verteld over de `RemoteController`.De makkelijkste manier om hier de connectie te leggen is de `public RemoteControllerDto remoteController;` toe te voegen aan de variable van de `TelevisionDto` 
14. Test alle functies voor je applicatie zover met postman.
