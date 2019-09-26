smprtz-2 lab1

1. create Maven project (using Intellij IDEA)
2. add mysql dependecy (pom.xml file)
3. check if mysql jdbs driver included (File -> Project Structure -> Libraries)
4. create package in src/main/java 
5. add entery point of app - Main class (with public static void  main(String[] args) in it)
6. install mysql server (ubuntu: sudo apt-get install mysql-server; sudo mysql_secure_installation utility)
7. create (copy and paste) class that encapsulated connection to db (DBConnector)
8. define (copy and paste) basic CRUD operations with abstract dao interface (CrudDao)
9. data model (class) for each entity (tabel)
10. Dao interface for each entity that implements abstract dao interface (CrudDao)
11. Implement Dao interface for each entity (using SQL and jdbc api)

as soon as files are similar auto-replace is your friend (Edit -> Find -> Replase or Ctrl+R)

have fun
