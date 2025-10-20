run:
	mvn spring-boot:run

package:
	mvn clean package -Dusertimezone=UTC

install:
	mvn clean install -Dusertimezone=UTC -Duser.country.format=US -Duser.language.format=en

install-no-test:
	mvn clean install -DskipTests=true -Dsonar.skip=true -Dusertimezone=UTC

test:
	mvn clean test -Dusertimezone=UTC -Duser.country.format=US -Duser.language.format=en

docker-up:
	sudo docker compose up --build

docker-app:docker-db
	sudo docker start seguro_app

docker-db:
	sudo sudo docker start mysql_db
