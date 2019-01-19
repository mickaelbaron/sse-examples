# Projet sse-helloworld-client

Cet exemple montre comment utiliser JAX-RS 2.1 et l'implémentation Jersey pour du développement client avec la technologie Server-Sent Events et le langage Java.

Nous montrons également comment déployer le code comme une application Java classique par l'intermédiaire du serveur web Grizzly.

## Démarrer le serveur SSE du projet _sse-helloworld_

* Se déplacer à la racine du projet _sse-helloworld_

* Compiler le projet

```console
mvn clean package
```

* Exécuter le projet

```console
java -cp "target/classes:target/dependency/*" fr.mickaelbaron.helloworldserversentevents.HelloWorldServerSetEventsLauncher
janv. 19, 2019 4:34:19 PM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9992]
janv. 19, 2019 4:34:19 PM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
Jersey app started available at http://localhost:9992/api
Hit enter to stop it...
```

## Comment compiler

* À la racine du projet, exécuter la ligne de commande suivante :

```console
mvn clean package
```

## Comment exécuter

Le programme client accepte un paramètre correspondant à l'URI de la ressource à invoquer. Ci-dessous les différentes URI autorisées permettant d'accéder aux ressources du projet _sse-helloworld_ : *sse* *sse/andstop*, *sse/withstreaming*, *sse-broadcast* et *sse-broadcast-json*.

* Exécuter la ligne de commande suivante en ajoutant une des URI de la liste précédente :

```console
java -cp "target/classes:target/dependency/*" fr.mickaelbaron.helloworldserversenteventsclient.HelloWorldSseClient sse/withstreaming
http://localhost:9992/api/sse/withstreaming
Wainting for incoming event ...
HelloWorld17:04:01.231375
HelloWorld17:04:02.239671
HelloWorld17:04:03.245671
```
