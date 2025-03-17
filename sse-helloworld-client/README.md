# Projet sse-helloworld-client

Cet exemple montre comment utiliser JAX-RS et l'implémentation Jersey pour du développement client avec la technologie Server-Sent Events et le langage Java.

Nous montrons également comment déployer le code comme une application Java classique par l'intermédiaire du serveur web Grizzly.

## Démarrer le serveur SSE du projet _sse-helloworld_

- Se déplacer à la racine du projet _sse-helloworld_

- Compiler le projet

```bash
mvn clean package
```

- Exécuter le projet

```bash
java -cp "target/classes:target/dependency/*" fr.mickaelbaron.helloworldserversentevents.HelloWorldServerSetEventsLauncher
```

La sortie console attendue :

```bash
mars 17, 2025 3:26:42 PM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9992]
mars 17, 2025 3:26:42 PM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
Jersey app started available at http://localhost:9992/api
Hit enter to stop it...
```

## Comment compiler

- Ouvrir un nouveau terminal et à la racine du projet _sse-helloworld-client_, exécuter la ligne de commande suivante :

```bash
mvn clean package
```

## Comment exécuter

Le programme client accepte un paramètre correspondant à l'URI de la ressource à invoquer. Ci-dessous les différentes URI autorisées permettant d'accéder aux ressources du projet *sse-helloworld* : _sse_ _sse/andstop_, _sse/withstreaming_, _sse-broadcast_ et _sse-broadcast-json_.

- Exécuter la ligne de commande suivante en ajoutant une des URI de la liste précédente :

```bash
java -cp "target/classes:target/dependency/*" fr.mickaelbaron.helloworldserversenteventsclient.HelloWorldSseClient sse/withstreaming
```

La sortie console attendue :

```bash
http://localhost:9992/api/sse/withstreaming
Wainting for incoming event ...
HelloWorld15:28:00.138936
HelloWorld15:28:05.151310
HelloWorld15:28:10.153649
```
