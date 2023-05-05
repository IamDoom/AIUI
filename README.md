# Projectomschrijving HAL-2023 (SE Semester 2)
Hieronder vind je de projectomschrijving van de opdracht HAL-2023 voor SE Semester 2 –
Achtereenvolgens vind je een introductie op de organisatie 42 B.V., de requirements voor het project en
de manier waarop je het als groep gaat uitvoeren.

### HAL-2023
Als software engineer bij 42 B.V. heb je goede documentatie nodig. We gebruiken diverse bronnen, waaronder ChatGPT, een AI-chatbot die nauwkeurige antwoorden geeft. We zoeken nu naar een AI-assistent die onze eigen bronnen combineert met lokale AI om optimaal gebruik te maken van de nieuwste AI-technologie. We werken aan een endpoint voor het sturen en ontvangen van resultaten. Bekijk het bijgevoegde diagram voor de soorten bronnen die we gebruiken. We slaan onze interne bronnen op in een Confluence-server en een database die direct kan worden aangeroepen door onze ElasticSearch-service, die fungeert als een zoekmachine. Voor project 2 vraagt 42 om een grafische user interface (GUI), die gebruikt kan worden om prompts te versturen en antwoorden te laten zien. Voor een voorbeeld van een minimalistische GUI, kun je kijken op de website van ChatGPT.

### Non-functional requirements:
- De user interface moet Nederlandstalig zijn.
- De GUI moet functioneel zijn en er verzorgd uit zien (componenten op het scherm moeten
bijvoorbeeld uitgelijnd zijn op het scherm), maar besteed niet te veel aandacht aan de ‘look and
feel’ van de applicatie omdat dit geen leerdoel is.
- De applicatie moet functioneren zonder internetverbinding. In dergelijke gevallen zou een
draagbare versie van de AI gebruikt worden voor het beantwoorden van prompts.
- Het gebruik van een database is niet noodzakelijk en is daarom ook niet vereist. Een aantal
instanties mogen hardcoded opgenomen worden zodat de applicatie kan functioneren.

### Must have requirements:
- De gebruiker moet in en uit kunnen loggen.
- Minimale resolutie waarop de applicatie moet werken is 800*600.
- Het is mogelijk om verschillende tabs te openen, die elk een eigen gespreksgeschiedenis
hebben.
- Het is mogelijk om elk gesprek een onderwerp te geven. Het onderwerp van het actieve gesprek
is ten alle tijden duidelijk zichtbaar.
- De taal van de assistent moet aangepast kunnen worden.
- Een ingelogde gebruiker moet zijn wachtwoord, gebruikersnaam en email-adres aan kunnen
aanpassen.
