
# Expence Tracker App




##FrameWorks and Languages Used : 
* Java 
* Spring Boot 
* Hibernate

### Data Flow

## Controller :
+ Controller - The controller layer handles the HTTP requests, translates the JSON parameter to object, and authenticates the request and transfer it to the business (service) layer. In short, it consists of views i.e., frontend part.



* Users :

        + SignUp User
        2. SignIn User
        2. Get Users (All and by id)
        3. Delete Users By Id
        

 * Product :

        1. Add Product
        2. Delete Product by ID
        3. Update Product by Id ;
        4. Get Product by userId , get-Product-by-timespan, 
           get-Product-on-particular-date-time,
           get-total-Product-in-a-month
 
## Repository 
+ Repository - Here database is stored, for which I have used MYSQL. In the database layer, CRUD (create, read, update, delete , signIn , signUp) operations are performed.

## Service
+ Service -The business layer handles all the business logic. It consists of service classes and uses services provided by data access layers.

## Database 
+ DataBase Class - Here the class user is defined and the whatever a user class will load. e.g -userId, userName, address, phone number etc.

## models 
+ models - Theire Are Two Models
 * Users
   + userName.
   + Email.
   + password.
   + Product
  + productId.
  + productDescription.
  + productPrice.
  + ProductDateAndTime.

 

 * Product -

        + Add Product
        + Delete Product by ID
        + Update Product by Id ;
        + Get Product by userId , get-Product-by-timespan, 
           get-Product-on-particular-date-time,
           get-total-Product-in-a-month

## Project Summary           
  * Different EndPoints is been provided, for ex - Get Product by userId , 2) get-Product-by-timespan 
        3) get-Product-on-particular-date-tim , 4) get-total-Product-in-a-month.

  * The link provided below is of swagger link which is hosted on AWS server.  
   + http://localhost:8080/swagger-ui/index.html#/product-controller/getExpensesByTimeSpan    
