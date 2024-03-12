## Car Fleet Management System: Java and JDBC Project Overview

The car fleet management system is a robust Java and JDBC application designed to streamline the management of vehicle fleets. With a focus on backend development and database integration, the system ensures efficient data handling and user authentication while providing essential functionalities such as car attributes management, ticket tracking, and automated notifications. Powered by MariaDB and featuring a simplistic JavaFX frontend, the project emphasizes security, usability, and scalability in managing fleet operations effectively.

### Backend Development:
1. **User Authentication and Authorization:**
   - Stored user credentials in the database (MariaDB).
   - Utilized JDBC to query the database for user existence based on provided credentials.
   - Implemented password hashing for security, storing only the hashed passwords in the database.
   - Granted different levels of access and permissions to users based on their hierarchical roles within the system.

2. **Database Management System:**
   - Leveraged MariaDB as the backend database system to store and manage the fleet data efficiently.

### Functionalities:
1. **Car Attributes Management:**
   - Stored detailed information about each car in the fleet, including attributes like color, license plate number, etc.
   - Ensured robust data management and organization for efficient retrieval and updating.

2. **Ticket Management:**
   - Managed a list of tickets associated with each car.
   - Recorded ticket details such as nature, price, and date for tracking and reporting purposes.

3. **Automated Notification System:**
   - Developed an automatic email notification system.
   - Notified users when their car tax was nearing expiration, enhancing user experience and compliance.

### Frontend Development:
1. **JavaFX Frontend:**
   - Created a simple and user-friendly frontend using JavaFX.
   - Designed an intuitive interface to interact with the backend functionalities of the car fleet management system.

### Security Measures:
1. **Password Encryption:**
   - Employed password hashing techniques to ensure the security of user credentials.
   - Stored only the hashed passwords in the database to mitigate the risk of unauthorized access.

### Future Enhancements:
1. **UI/UX Improvements:**
   - Enhance the frontend interface with more modern design elements and user experience optimizations.
   
2. **Data Analytics Integration:**
   - Integrate data analytics functionalities to provide insights into fleet performance and maintenance trends.

3. **Expanded Notification System:**
   - Extend the notification system to include alerts for other important events, such as vehicle maintenance schedules or upcoming inspections.
