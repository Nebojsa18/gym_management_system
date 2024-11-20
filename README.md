# Gym management system

This application is part of a diploma thesis project titled "Software System for Managing Gym Operations in Jakarta EE". The application enables the management of a gym's operations. The admin, or trainer, logs into the system using their credentials (username and password) to access the application's functionalities. Once logged in, the trainer has access to registered trainers, gym members, their memberships, and training schedules. The trainer can search for and manage member information, as well as handle memberships and training appointments. The application allows the scheduling of training sessions, where the trainer can create, modify, or cancel appointments. During the session scheduling, the trainer selects the exercises to be performed for that specific training.

# Conceptual model
![image](https://github.com/user-attachments/assets/73ebf7c4-b2ee-4a51-b6b8-96da73e0583d)

# Implementation
The system consists of two applications: a server-side and a client-side. The server-side was developed in Java with the Spring Boot framework, while the client-side was built using the React library and JavaScript. MySQL was used as the database management system to store and manage data efficiently. The server exposes RESTful APIs for the client to interact with, allowing operations such as managing members, trainers, and training sessions. The front-end communicates with the back-end via HTTP requests, rendering data dynamically. The entire system was developed in the NetBeans 15 IDE, providing a unified environment for both the server and client applications.

# User interface
