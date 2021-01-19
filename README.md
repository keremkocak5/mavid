# Mavi Dev Kullanici Servisi 

Basit bir kullanici CRUD Servisi 

## API'ler


endpoint: http://localhost:8080/api/v1/md/user

Servis JSON formatında istekleri kabul eder. 

### GET Metodu

Tüm kullanıcıları listeler

Request Alan İsmi | Zorunlu/Seçmeli | Tip | Detaylar
------------ | ------------- | ------------- | -------------

Response Alan İsmi | Zorunlu/Seçmeli | Tip | Detaylar
------------ | ------------- | ------------- | -------------
userResponseDetailDTOs | Zorunlu | UserResponseDetailDTOs | Detayları için aşağı bakınız

UserResponseDetailDTOs | Zorunlu/Seçmeli | Tip | Detaylar
------------ | ------------- | ------------- | -------------
name | Zorunlu | String | 
surname | Zorunlu | String | 
birthCity | Zorunlu | String | 

Örnek İstek:

```
{
}
```

Örnek Yanıt: 
```
{
    "userResponseDetailDTOs": [
        {
            "id": 12,
            "name": "kerem",
            "surname": "kocak",
            "birthCity": "ankara",
            "status": 1
        }
    ]
}
```

### POST Metodu

Kullanıcı Ekler

Request Alan İsmi | Zorunlu/Seçmeli | Tip | Detaylar
------------ | ------------- | ------------- | -------------
name | Zorunlu | String | 
surname | Zorunlu | String | 
birthCity | Zorunlu | String | 


Örnek İstek:

```
{
    "name": "kerem",
    "surname": "kocak",
    "birthCity": "ankara"
}
```

Örnek Yanıt: 
```
{
}
```


### PATCH Metodu

Kullanıcı düzenler

Request Alan İsmi | Zorunlu/Seçmeli | Tip | Detaylar
------------ | ------------- | ------------- | -------------
id | Zorunlu | String | Update edilecek id belirtir 
name | Zorunlu | String | 
surname | Zorunlu | String | 
birthCity | Zorunlu | String | 


Örnek İstek:

```
{
    "id":"12",
    "name": "kerem",
    "surname": "kocak",
    "birthCity": "ankara"
}
```

Örnek Yanıt: 
```
{
}
```


### DELETE Metodu

Kullanıcı siler

Request Alan İsmi | Zorunlu/Seçmeli | Tip | Detaylar
------------ | ------------- | ------------- | -------------
name | Zorunlu | String | Update edilecek id belirtir 



Örnek İstek:

```
{
    "id":"12",
}
```

Örnek Yanıt: 
```
{
}
```



## Teknik Meseleler

### Endpointler

Bu uygulama **8080** portunu kullanır. Aşağıdaki maven komutu ile uygulama çalıştırılabilir:

```
mvn spring-boot:run
```

### Api Dökümanları

Uygulama ayağa kalkınca [Swagger UI](http://localhost:8080/swagger-ui.html) ile API'lar incelenebilir. 

### Güvenlik

Demo amaçlı olarak **user1/1234** ile servisler çağrılabilirler (Basic Authentication).


### Projenin Derlenmesi

Aşağıdaki komut ile proje derlenebilir.
```
mvn clean install
```

### Test Coverage

Aşağıdaki komut ile test coverage çıkarılabilir.
```
mvn clean test
```

Test coverage için, yereldeki ".../linkconverter/target/site/jacoco/index.html" dosyası gözlenebilir.  


### MySQL Veritabanı Komutları

Veritabanında gereken konfigurasyon için, aşağıdaki komutlar sırası ile MySQL'de çalıştırılmalılar.

```
CREATE DATABASE mavidev;

CREATE USER 'mavidev'@'localhost' IDENTIFIED BY 'mavipass';

GRANT ALL PRIVILEGES ON * . * TO 'mavidev'@'localhost';

DROP TABLE IF EXISTS `mavidev`.`md_user`;
CREATE TABLE  `mavidev`.`md_user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `NAME` varchar(45) NOT NULL COMMENT 'Kisi Ismi',
  `SURNAME` varchar(45) NOT NULL COMMENT 'Kisi Soyismi',
  `BIRTH_CITY` varchar(45) NOT NULL COMMENT 'Dogdugu Sehir',
  `STATUS` int(10) unsigned NOT NULL COMMENT '1: AKTIF 2: PASIF',
  `CREATE_DATE` datetime NOT NULL COMMENT 'Kayit Yaratildigi Tarih',
  `CREATE_USER` varchar(45) NOT NULL COMMENT 'Kayit Yaratan Kullanici',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT 'Kayit Guncellendigi Tarih',
  `UPDATE_USER` varchar(45) DEFAULT NULL COMMENT 'Kayit Guncelleyen Kullanici',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT 'Kayit Silinme Tarihi',
  `DELETE_USER` varchar(45) DEFAULT NULL COMMENT 'Kayit Silen Kullanici',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='mavidev kullanicilarinin kaydedildigi tablo';
```

### Bağımlılıklar 
- Java 1.8.0_131 
- Spring Boot 2.4.1
- maven 3.6.3
- Swagger 2.4.0
- Junit 5
- JUnitParams 1.1.1
- Project Lombok
- mysql-connector-java
- Jacoco 0.8.6
- MySQL 