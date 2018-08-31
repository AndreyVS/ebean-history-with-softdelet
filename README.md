### Java 8 play framework 2.6 sbt project

**before run:**
- create MySQL DB with name "ebeantest"
- set username and password for DB in conf/application.conf

**to run app**
$ sbt run

when the server's up

open in browser http://localhost:9000

since BD is empty, play will show: Database 'default' needs evolution!
just click "Applay this script now" and DB will be filled

next see app/controllers/HomeController.java. There two not worked case getDeletedUser(Long id) and getDeletedSettingsWithFetch(Long id). These both throws "Query threw SQLException:No value specified for parameter 2 Bind values:[1, ]"