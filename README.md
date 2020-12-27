# Link Converter 

A project to convert URLs to deeplinks and vice versa. 

## Functional Background

This web service converts an URL to deeplink, or a deeplink to an URL. Any input parameter which can be classified as "Product" or "Search" page is transformed into a corresponding link. Trendyol home page is returned as the output parameter for uncategorized input parameters. All requests are persisted into database. The records can be accessed in trendyoltest.ty_link_converter_log table. 

Basic authentication is required to access the services, please read the "Security" section for a demo username and password. 

For security reasons, "*" (asterisk) character is omitted from URLs when persisted into DB. 

In case of a persistence layer failure, such as an exception while storing the transaction into the db, the web service continues functioning, and returns responses, tough the transactions would not be saved. The application is designed to return a response even if an internal exception occours. However, in the case when db is totaly inaccessible, the application will fail to start. 

Execution performance (in miliseconds) of each request is printed on the console.

### URL to Deeplink

Every product in Trendyol has multiple product detail page URLs.

https://www.trendyol.com/{BrandName-or-CategoryName}/{ProductName}-p-{ContentId}?boutiqueId={BoutiqueId}&merchantId={MerchantId}

All URLs are classified in the following three groups:

(A) Product URLs
- Product detail page URL must contain "-p-" text.
- Product detail page URLs must contain contentId which is located after "-p-" prefix.
- URL can contain boutiqueId and merchantId.
- If URL doesn't contain boutiqueId, CampaignId shouldn't be added to deeplink.
- If URL doesn't contain merchantId, MerchantId shouldn't be added to deeplink.
- Deeplink and Web URL have differences on CampaignId and boutiqueId. Deeplinks have CampaignId, web URLs have boutiqueId.

(B) Search URLs
- Search pages must contain "tum--urunler".
- "q" query are converted to Query deeplink parameter.

(C) Other URLs
- Any URL which does not match the above. The default page of Trendyol is returned for those.

##### API Access Information

HTTP Method: GET

The web service accepts JSON objects. 

Endpoint: http://localhost:8080/api/v1/linkconverter/deeplink

Request Input Name | Request Input Details
------------ | -------------
url | String. Min 10, max 450 characters. Specifies the url to be convered. Uppercase, lowercase and other variants are accepted. 

Sample Request:

```
{
	"url" : "https://www.trendyol.com/tum--urunler?q=elbiçöşğse"
}
```

Sample Response: 
```
{
    "deeplink": "ty://?Page=Search&Query=elbiçöşğse"
}
```


## Tecnical Background

### Running the service

The Maven command below can be used to run the application:
```
mvn spring-boot:run
```


### Api Documentation

Once the application is up and running on your local device, you may access [Swagger UI](http://localhost:8080/swagger-ui.html) to display the available API's. 

### Security

Spring Web Security ensures authentication for accessing web services. In this demo application, username and password are embedded into SecurityConfig class. Since this is only a demo, you can use user1/1234 to be gain access.


### Compiling the service

The following Maven command should be executed to compile the service:
```
mvn clean install
```

### Test Coverage

Test coverage is reported with Jacoco. Execute the following Maven command the generate Jacoco report:
```
mvn clean test
```

A Jacoco coverage file will be generated in your local directory ".../linkconverter/target/site/jacoco/index.html" 

### Running the MSQL Database

The connection parameters of MySQL Database must be specified in the application.properties file. Do not forget to replace your username and password. The following line can be executed on MySQL server to create 'trendyoltest' DB. 

```
CREATE DATABASE trendyoltest;
```

Since Automatic DDL is disabled, the required tables of this application must be created manually. DDL for creating ty_link_converter_log table:

```
CREATE TABLE  `trendyoltest`.`ty_link_converter_log` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK of table',
  `URL` varchar(450) NOT NULL,
  `DEEPLINK` varchar(450) NOT NULL,
  `CONVERSION_TYPE` int(10) unsigned NOT NULL COMMENT '1: URL TO DEEPLINK. 2: DEEPLINK TO URL.',
  `MATCH_TYPE` int(10) unsigned NOT NULL COMMENT '1: PRODUCT PAGE. 2: SEARCH PAGE. 0: UNCLASSIFIED.',
  `DATETIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DATE AND TIME OF TRANSACTION',
  `USERNAME` varchar(45) NOT NULL COMMENT 'USERNAME OF THE REQUESTER',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='A transaction log of Trendyol URLs and deeplinks';
```

### Project Dependencies 
- Java 1.8.0_131 
- Spring Boot 2.4.1
- maven 3.6.3
- Swagger 2.4.0
- Junit 5
- Jacoco 0.8.6
- MySQL connection, as specified in "Running the MSQL Database" section.