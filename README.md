# Schoolproject

Small project for teaching purposes.

* Wildfly
* JEE 8 
* Java 11
* Git
* Maven
* MySQL

## Wildfly configuration

Install any Wildfly release you want. I use 18.

Add a user, and place the school.cli script under the bin folder.<br>
Create database school. The script will need a mysql connector under `C:\Users`
to work. 

The script is predefined for `mysql.connector-java-8.0.12.jar`. Change location and version for your own liking.

Start Wildfly, and once running, open a new prompt, and go to the bin folder.<br>
Write `jboss-cli -c --file=school.cli`

It should say outcome success. Write `jboss-cli -c --command=:reload` to restart the server.

## Endpoints 

* Get all students: <br> 
    **GET** <br>
    *http://localhost:8080/school/student* <br>
    
* Get student by name: <br>
    **GET** <br>
    *http://localhost:8080/school/student/byName/{name}* <br>

* Get student by full name: <br>
    **GET** <br>
    *http://localhost:8080/school/student/byFullName/?{forename={forename}&lastname={lastname}}* <br>

* Get student by email: <br>
    **GET** <br>
    *http://localhost:8080/school/student/{email}* <br>
    
* Create new Student: <br>
    **POST** <br>
    *http://localhost:8080/school/student* <br>
    Body: <br>
            {
            	"forename" : "{forename}",
            	"lastname" : "{lastname}",
            	"email"  : "{email}"
            } <br>
            
* Update Student (can update email): <br>
    **PUT** <br>
    *http://localhost:8080/school/student/{email}* <br>
    Body: <br>
            {
            	"forename" : "{forename}",
            	"lastname" : "{lastname}",
            	"email"  : "{email}"
            } <br>
            
* Update Student partially (can't update email): <br>
    **PATCH** <br>
    *http://localhost:8080/school/student/{email}* <br>
    Body: <br>
            {
                "forename" : "{forename}",
                "lastname" : "{lastname}"
            } <br>
            ...or either one of forename/lastname <br>
            
* Remove Student: <br>
    **DELETE** <br>
    *http://localhost:8080/school/student/{email}* <br>