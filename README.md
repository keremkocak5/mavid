# Link Converter 

A project to convert URLs to deeplinks and vice versa. 

## Functional Background

This web service converts an URL to deeplink, or a deeplink to an URL. Any input parameter which can be classified as "Product" or "Search" page is transformed into a corresponding link. Trendyol home page is returned as the output parameter for uncategorized links. All requests are persisted into the database. The records can be accessed through *trendyoltest.ty_link_converter_log* table in the database. 

Basic authentication is required to access the services; please refer to the "Security" section of this document for a demo username and password. 

Execution performance (in miliseconds) of each request is printed on the console.

For security reasons, "*" (asterisk) character is omitted from URLs and deeplinks when persisted into DB. 

### URL to Deeplink

Every product in Trendyol has multiple product detail page URLs.

https://www.trendyol.com/{BrandName-or-CategoryName}/{ProductName}-p-{ContentId}?boutiqueId={BoutiqueId}&merchantId={MerchantId}

All URLs are classified in the following three groups:

(A) *Product URLs*
- Product detail page URL must contain "-p-" text.
- Product detail page URLs must contain contentId which is located after "-p-" prefix.
- URL can contain boutiqueId and merchantId.
- If URL doesn't contain boutiqueId, CampaignId shouldn't be added to deeplink.
- If URL doesn't contain merchantId, MerchantId shouldn't be added to deeplink.
- Deeplink and Web URL have differences on CampaignId and boutiqueId. Deeplinks have CampaignId, web URLs have boutiqueId.

(B) *Search URLs*
- Search pages must contain "tum--urunler".
- "q" query are converted to Query deeplink parameter.

(C) *Other URLs*
- Any URL which does not match the above. The default page of Trendyol is returned for those.

##### API Access Information

HTTP Method: GET

The web service accepts JSON objects. 

Endpoint: http://localhost:8080/api/v1/linkconverter/deeplink

Request Input Name | Optional/Mandatory | Type | Details
------------ | ------------- | ------------- | -------------
url | Mandatory | String | Min 10, max 450 characters. Urls are **not** case sensitive.

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

### Deeplink to Url

Every product in Trendyol has multiple product detail page URLs.

ty://?Page=Product&ContentId={ContentId}&CampaignId={CampaignId}&MerchantId={MerchantId}

All URLs are classified in the following three groups:

(A) *Product URLs*
- Product detail page URL must have a Page attribute set to "Product" 
- Product detail page URL must have a ContentId attribute  


(B) *Search URLs*
- Product detail page URL must have a Page attribute set to "Search"

(C) *Other URLs*
- Any URL which does not match the above. The default page of Trendyol is returned for those.

##### API Access Information

HTTP Method: GET

The web service accepts JSON objects. 

Endpoint: http://localhost:8080/api/v1/linkconverter/url

Request Input Name | Optional/Mandatory | Type | Details
------------ | ------------- | ------------- | -------------
deeplink | Mandatory | String | Min 10, max 450 characters. Deeplinks are case sensitive.

Sample Request:

```
{
	"deeplink" : "t://?Page=Search&deneme=312&Query=222&kerem=333"
}
```

Sample Response: 
```
{
    "url": "https://www.trendyol.com/tum--urunler?q=222"
}
```


## Tecnical Background

### Running the service

This application uses port **8080**. The Maven command below can be used to run the application:
```
mvn spring-boot:run
```


### Api Documentation

Once the application is up and running on your local device, you may access [Swagger UI](http://localhost:8080/swagger-ui.html) to display the available API's. 

### Security

Spring Web Security ensures authentication for accessing web services. In this demo application, username and password are embedded into SecurityConfig class. Since this is only a demo, you can use **user1/1234** to gain access.


### Compiling the project

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


### Running the MySQL Database

The connection parameters of MySQL Database must be specified in the application.properties file. Do not forget to replace your username and password. The following command can be executed on MySQL server to create 'trendyoltest' DB. 

```
CREATE DATABASE trendyoltest;
```

Since Automatic DDL is disabled, the required tables of this application must be created manually. DDL for creating **ty_link_converter_log table** is as follows:

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
- JUnitParams 1.1.1
- Project Lombok
- mysql-connector-java
- Jacoco 0.8.6
- MySQL connection, as specified in "Running the MSQL Database" section.