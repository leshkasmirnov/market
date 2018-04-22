Test task: Design model for simple market and provide test scenario
-

Using libraries:
-
- Spring 5.0.5.RELEASE
- Hibernate 5.2.16.Final
- Hikari Connection pool 2.3.0
- H2 database for RDBMS 1.4.197

To start app:
-
1. Checkout
2. Build:
````
mvn clean compile assembly:single
````
3. Start:
````
java -jar market-1.0-SNAPSHOT-jar-with-dependencies.jar
````

Description:
-
This is command line application. First you can choose code of item what you want to buy.
And than follow application instructions (enter count of item etc.)

App's database works in 2 modes: memory and file. By default: memory. 
You can uncomment line in src/main/resources/market.yml setting file to use file mode

