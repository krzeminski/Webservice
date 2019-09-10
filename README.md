# Webservice
My first RESTful API web service.

#I'm currently trying to upgade some functionalities.

To run this project listed below are needed:
Java 8 or higher, database of your choice.
You may want to use Postman as an API development environment to check out the functionality.

To set up database you need to change "spring.datasource.*" and "hibernate.dialect." properties acordingly to your data source. 
Do it in \src\main\resources\application file.

The project is built using Java 12, spring boot, hibernate, maven and mySQL database.

To test functionality you shall issue a REST request.
I've been doing this in Postman by sending e.g. GET request on http://localhost:8080/notes to get all notes saved in a "notes" table
in my database.
Avaible options can be found in NotesController class.
