# Bulk Lily Instruction processor
This is used to process a class of actions that needs to be performed on a dataset, submitted via a csv file.

##  Running the application locally
Please run from the projects root.
`./gradlew bootjar`

This creates an executable jar file in `/build/libs/`

Finally, run `java -jar ./build/libs/lily-0.0.1-SNAPSHOT.jar`

### Testing using postman
Please download the collection stored in `/src/test/resources/collection`
and run it in postman.

### Csv file format.
Please follow the format of the csv file specified in `/src/test/resources/Order.csv`

## Running tests locally
`./gradlew test`
