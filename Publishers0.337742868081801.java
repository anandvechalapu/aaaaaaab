·       Ability to download the list of publishers as .csv format.
·       User should be able to add/edit publication.
·       User should have Submit and Reset button for adding/editing publication.
·       User should be able to access the sub functionalities like edit, view and download.
·       User name should not be empty; username should be unique.
·       Clicking on Download Data will allow User to view the results in Excel format.

The Spring Boot application generated for this user story should include the following classes:

1. PublisherController – This will handle the requests from the front end and will contain all the necessary methods to handle the operations like add, edit and download.

2. PublisherService – This will contain the business logic for the operations like add, edit and download.

3. PublisherRepository – This will provide the database layer for the application and will contain methods to interact with the database.

4. Publisher – This is the model class that will contain the fields and validations for the publishers.

5. PublisherValidation – This is a utility class that will contain methods to validate the publishers.