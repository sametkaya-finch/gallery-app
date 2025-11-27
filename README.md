# Gallerist Application – Spring Boot Backend

Bu proje, bir galerici (gallerist) uygulamasının backend tarafını Spring Boot ile geliştirerek kullanıcı yönetimi, araç yönetimi, satış işlemleri ve güvenlik katmanlarını içeren temel bir domain yapısı oluşturmayı amaçlar.

## Teknolojiler
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security (JWT Authentication)
- PostgreSQL 
- Lombok

## Proje Özeti
Proje, bir araç galerisi için temel yönetim operasyonlarını içermektedir.

### 1. Domain Model ve Entity Yapısı
Aşağıdaki ana varlıklar oluşturulmuş ve birbirleriyle ilişkilendirilmiştir:
- Customer
- Address
- Account
- Gallerist
- Car
- GalleristCar
- SaledCar
- RefreshToken

Tüm entity’ler JPA üzerinden veritabanına bağlanır ve `BaseEntity` üzerinden ortak alanlar yönetilir.

### 2. CRUD Servisleri
Aşağıdaki varlıklar için create, read, update ve delete operasyonları tamamlanmıştır:
- Address  
- Account  
- Customer  
- Gallerist  
- Car  
- GalleristCar  

### 3. Authentication & Authorization (JWT)
Projede JWT tabanlı bir güvenlik yapısı kurulmuştur:
- Kullanıcı kayıt (register)
- Giriş (authenticate)
- Token üretme
- Refresh token sistemi
- Yetkilendirme filtreleri
- SecurityConfig üzerinden endpoint koruma ve yapılandırma

### 4. Exception Handling
Global bir exception handling yapısı oluşturulmuştur:
- BaseException
- ErrorMessage
- GlobalExceptionHandler
- ApiError

Tüm hatalar standart bir API cevabı halinde handle edilmektedir.

### 5. Currency Rate Integration
Merkez Bankası üzerinden USD kuru alınarak:
- Müşterinin parasının dolara çevrilmesi
- Satın alma işleminde kur bazlı kontrol yapılması sağlanmaktadır.

### 6. Araç Satın Alma İş Akışı
`buyCar` servisi ile:
- Müşteri bakiyesi kontrol edilir  
- Araç durumu güncellenir (Available → Sold)  
- Satış kaydı tutulur  
- Müşteri bakiyesi düşülür  
- GalleristCar ilişkisi güncellenir  

Bu sayede uçtan uca bir satış süreci tamamlanmaktadır.

## Notlar
Bu proje, Spring Boot öğrenme sürecini pekiştirmek, gerçek bir domain yapısı kurmak ve temel security ile business logic pratiklerini göstermek amacıyla hazırlanmıştır.



