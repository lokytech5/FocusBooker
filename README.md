# focusBooker 📸
focusBooker is a Spring Boot application designed to assist users in booking photography services. With a clean API design, users can easily manage their profiles and book their photography sessions.

## 🚀 Features
User Management: List, fetch, update, and delete users.
Booking Management: Admins can view all bookings, users can book photography services, and fetch details of specific bookings.

## 📝 API Documentation with Swagger
focusBooker leverages Swagger for API documentation. With Swagger's interactive documentation, you can:

- View a list of all available endpoints.
- Understand the purpose of each endpoint.
- See request/response models.
- Try out the API calls directly from the browser.

## Accessing Swagger UI:
1. Start the focusBooker application.
2. Visit http://localhost:8080/swagger-ui/index.html# in your browser.
3. Explore the available endpoints, their details, and try them out directly from the UI.

## Swagger UI Screenshots:
![Swagger Screenshot 1](/static/Screen1.png) </br>
![Swagger Screenshot 2](/static/Screen2.png) </br>
![Swagger Screenshot 3](/static/Screen3.png)

## 🛠 Setup & Installation
1. Clone the repository:
`git clone https://github.com/yourgithubusername/focusBooker.git`

2. Navigate to the directory:
`cd focusBooker`

3. Install dependencies:
   If you're using Maven:
`mvn install`

4. Run the application:
`mvn spring-boot:run`

Your application should now be running at http://localhost:8080.

## 📜 License
This project is licensed under the MIT License.

## 🤝 Contributing
Contributions, issues, and feature requests are welcome! See our contributing guide for more details.


## Prerequisites
- Java 8 or newer
- Maven
- A suitable database, with configuration specified in application.properties


## Security
The application uses JWT-based security. Make sure to obtain a valid token before making authenticated requests. Some endpoints like /booking require additional privileges such as ADMIN role.