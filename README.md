# FileUpload Example using Spring boot

prerequisites
=============
1. Java 1.8 or higher
2. Apache Maven
3. Eclipse or STS(spring tool suite)

How to run Application
======================
1. Clone this project to your local repository
2. Using maven build the project


Serivice URLs
================
1. File Upload Service url : <br>
    Request url : http://\<host:port\>/uploadfile <br>
    Request method : POST

2. File meta data attributes url : <br>
    Request url : http://\<host:port\>/download/\<fileName\> <br>
    Request method : GET

3. File download service url : <br>
    Request url : http://\<host:port\>/getMetaData/\<fileName\> <br>
    Request method : GET
    
    
   # Alfresco Exercise
   Request url : http://\<host:port\>/alfresco/s/user/permissions <br>
   Request method : POST <br>
   Input JSON Format : {"nodeRef":"workspace://SpacesStore/92e9d3b0-3a32-44ef-a346-0d3b7a8e6c4a","userName":"Venkat"}



