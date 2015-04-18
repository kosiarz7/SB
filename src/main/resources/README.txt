1. Konfiguracja Mavena.
Trzeba dodać do lokalnego repo jar z connectorem do Oracla. Plik znajduje się w src/main/resources/libs/ojdbc6.jar. Polecenie:
mvn install:install-file -Dfile=PATH_TO_FILE/ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar

2. Uruchomienie.
Potrzebny jest serwer JavyEE, ja korzystałem z wildfly8.2.0.Final. Wystarczy wdrożyć paczkę (SB.war) zbudowaną mavenowym
taskiem (mvn install) na serwer JavyEE.
Aplikacja powina się znajdowąć pod adresem: adres_serwera/SB/app. Logowanie się jest pod: adres_serwera/SB/app/login

3. Baza danych. 
W pliku src/main/resources/schema.sql zacząłem pisać schemat (pola potrzebne do logowania). 
W pliku src/main/resources/data.sql przykładowe dane, które pozwalają się zalogować.

4. Połączenie z BD.
Konfiguracja połączenia jest w pliku: src/main/resources/hibernate.cfg.xml.

5. Szablon.
Szablon strony znajduje się w: WEB-INF/templates/mainTemplate.xhtml. Wystarczy aby nowa strona go rozszerzała i będzie wszystko ładnie wyglądało.
Zasoby WWW są w webapp/resources/css - arkusze stylów i webapp/resources/img - obrazy.