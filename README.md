Coverage: 80.3%
# CoolStore - Inventory Management System

The following project consists of a piece of CLI software designed to allow the user to manage inventory within an SQL Database. It includes CRUD Commands (Create Read Update Delete) and covers Customers, Items, and Orders. Additionally, it includes a Calculator class which allows it to cost all orders with the same reference, enabling a sum to be costed for multiple items in one purchase.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
MySQL 8.0CE 
mysql-connector-java-8.0.29.jar (not necessary, but improves connection significantly and reduces timezone error)
Java Development Kit (JDK)
Maven Apache
JUnit
Mockito
```

### Installing

Fork the git repo, then clone to your local machine using your prefered interface

If on the command line, move to your chosen location and run
```
cd your/chosen/path
gh repo clone your-github-name/projectName
```

If using VSCode, you can simply chose Files > Clone from GitHub as long as you have your GitHub account connected

Open a local instance of MySQL and use the script 'CoolStoreDB' to create the database

Open the repo in your chosen IDE, then navigate to the db.properties file 

```
CoolStore/srs/main/resources/db.properties
```

Update the db.properties file to reflect the database name, and your SQL username and password

Ensure your compile environment is suitable for the plugins, and you're good to go!

## Running the tests

Within the test folder, you will find a series of tests to run on the code totalling 80.3% coverage, they are broken into three categories:

```
src/test/java/com/qa/coolstore/controllers
```

This folder contains CustomerControllerTest, ItemControllerTest, and OrderControllerTest. Review the Javadoc for full explanations, but these tests can be run as they stand. Each test in this package is designed to ensure that the Controller classes used the DAO Classes to execute the commands they are given, and use them the exact correct number of times.

Additionally, these tests all use Mockito to run, as dictated by the 
```
@RunWith(MockitoJUnitRunner.class)
```
annotation preceding the class itself. This is installed through a dependency in the pom.xml file.

```
<dependency>
	<groupId>org.mockito</groupId>
	<artifactId>mockito-core</artifactId>
	<version>3.7.7</version>
	<scope>test</scope>
</dependency>
```

```
src/test/java/com/qa/coolstore/persistence/dao
```

CustomerDAOTest, ItemDAOTest, and OrderDAOTest all require the use of an SQL schema to populate the database tables with the appropriate data. Prior to running these, ensure you have the CoolStoreData and CoolStoreData-orderOnly sql files in
```
src/test/java/resources
```
If you chose to make any changes to the read(), readAll(), readLatest(), and update() tests for any of the classes, ensure you amend the data in the sql script to match the object changes.


```
src/test/java/com/qa/coolstore/persistence/domain
```

Finally, CalculatorTest, CustomerTest, ItemTest, and OrderTest can be found here. CustomerTest, ItemTest, and OrderTest simply ensure the hashcode() and equals() overwrites in the Object classes have executed correctly. Should you change any object attributes, ensure you also update the hashcode() and equals() overwrite methods otherwise these tests will fail.

Additionally, these tests rely on the jqno.equalsverifier. This is installed through a dependency in the pom.xml file

```
<dependency>
	<groupId>nl.jqno.equalsverifier</groupId>
	<artifactId>equalsverifier</artifactId>
	<version>3.10</version>
	<scope>test</scope>
</dependency>
```

CalculatorTest operates on the object Calculator, and full Javadoc comments can be found for a more detailed breakdown. The purpose of this test is to ensure the Calulcator.calculate(Long id) method produces a correct result. This test relies on the CoolStoreData-calcOnly.sql file, and any changes made to the item or order costs, amounts, references or similar will need to be reflected in the tests or else they won't run.

## Deployment

To deploy this project, firstly ensure you have the Maven plugin to build a Jar:
```
<plugins>
    <plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-jar-plugin</artifactId>
		<version>3.2.0</version>

		<configuration>
			<archive>
				<manifest>
					<mainClass>com.qa.coolstore.Main</mainClass>
				</manifest>
			</archive>
		</configuration>
	</plugin>
</plugins>
```
and a fat Jar:
```
<plugins>
    <plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-assembly-plugin</artifactId>
		<configuration>
			<archive>
				<manifestEntries>
					<Multi-Release>true</Multi-Release>
				</manifestEntries>
			</archive>
		</configuration>
		<executions>
			<execution>
				<phase>package</phase>
				<goals>
					<goal>single</goal>
				</goals>
				<configuration>
					<archive>
						<manifest>
							<mainClass>
								com.qa.coolstore.Main
							</mainClass>
						</manifest>
					</archive>
					<descriptorRefs>
						<descriptorRef>jar-with-dependencies</descriptorRef>
					</descriptorRefs>
				</configuration>
			</execution>
		</executions>
	</plugin>
</plugins>
```
NB: For both the above, ensure you change the package in the <mainClass> tag to the package name if it does not match the one in the above examples

A few other plugins are used to ensure project success, and your pom.xml file should look like the following (bar any changes to names or versions you may have made):

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.qa</groupId>
  <artifactId>CoolStore</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>CoolStore (Function Project)</name>
	<properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>
	<dependencies>
		<dependency>
			<groupId>nl.jqno.equalsverifier</groupId>
			<artifactId>equalsverifier</artifactId>
			<version>3.10</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<version>1.4.200</version>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.19</version>
		</dependency>
		<dependency>
			<groupId>org.apache.logging.log4j</groupId>
			<artifactId>log4j-core</artifactId>
			<version>2.13.3</version>
		</dependency>
		<dependency>
			<groupId>org.apache.logging.log4j</groupId>
			<artifactId>log4j-api</artifactId>
			<version>2.13.3</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>3.7.7</version>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/junit/junit -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13.1</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<!-- Creates a jar for the code (does not contain other dependencies which 
				are needed for the project to run out of the box) -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-jar-plugin</artifactId>
				<version>3.2.0</version>
				<configuration>
					<archive>
						<manifest>
							<mainClass>com.qa.coolstore.Main</mainClass>
						</manifest>
					</archive>
				</configuration>
			</plugin>
			<!-- Creates a "fat jar" with jar-with-dependencies at the end of the 
				project jar -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-assembly-plugin</artifactId>
				<configuration>
					<archive>
						<manifestEntries>
							<Multi-Release>true</Multi-Release>
						</manifestEntries>
					</archive>
				</configuration>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>single</goal>
						</goals>
						<configuration>
							<archive>
								<manifest>
									<mainClass>
										com.qa.coolstore.Main
									</mainClass>
								</manifest>
							</archive>
							<descriptorRefs>
								<descriptorRef>jar-with-dependencies</descriptorRef>
							</descriptorRefs>
						</configuration>
					</execution>
				</executions>
			</plugin>
			<!-- Plugin for code coverage. Useful as sonarqube integrates well with 
				jacoco -->
			<plugin>
				<groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<version>0.8.5</version>
				<executions>
					<execution>
						<goals>
							<goal>prepare-agent</goal>
						</goals>
					</execution>
					<!-- attached to Maven test phase -->
					<execution>
						<id>report</id>
						<phase>test</phase>
						<goals>
							<goal>report</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
			<!-- Plugin to send files -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-deploy-plugin</artifactId>
				<version>3.0.0-M1</version>
			</plugin>
		</plugins>
	</build>
</project>
```

From here, open your CLI terminal and ensure you have navigated to the maven project location using
```
cd path/to/file
```
Then execute the following comands to clean your target folder and package the project into an executable JAR file:
```
mvn clean
mvn package
```

From here, you can navigate to the location of the new .jar file using
```
cd path/to/repo/CoolStore/src/target/
```

Ensure that you have both the name-of-project.jar and name-of-project-with-dependencies.jar files, and execute the following command to begin running the CoolStore IMS:

```
java -jar name-of-project-with-dependencies.jar
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

* We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Emily Bradfield** - *Initial Work* - [emmy-bradfield](https://github.com/emmy-bradfield)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

Special thanks to my QATrainers,
* Pier Barbs
* Joran Benbelaid
* Ed Reynolds

for their support and guidance in my development and understanding of Javascript and software development
