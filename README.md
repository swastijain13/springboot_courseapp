# Course Enrollment Application System

## Running the Application

1. Clone the repository
2. Navigate to project directory
3. Run: `./mvnw spring-boot:run` on vs code **OR**
   Open main file CourseApplication.java in the directory src/main/java/com/example/courseApplication
   and Run the file
5. Application will start on port 8005
6. use H2 Database url provided to connect to db on the h2 console.

## Data Models

### User
- `id`: Integer (Auto-generated)
- `username`: String
- `email` : String
- `role` : String ("ADMIN" / "STUDENT") (BY DEFAULT :"STUDENT")
- `password` : String

### Course
- `id`: Integer (Auto-generated)
- `title` : String

## DTOs
### UserDTO
- `id` : Integer
- `username` : String
- `email` : String

### CourseDTO
- `id` : Integer
- `title` : String

## API Endpoints
#### Auth endpoints:
###### User register(as student): `POST /api/auth/register`
  ```json
  {
     "username": "swasti",
     "email" : "swasti@example.com",
     "password" : "swasti"
  }
  ```
###### User login: `POST /api/auth/login`
  REQUEST PARAMETERS :
  ```
  {
     "username" : "swasti",
     "password" : "swasti"
  }
  ```
- Response :
   ```
   token
   ```

#### Admin authorized endpoints:
- GET TOKEN FOR ADMIN
- LOGIN USING ADMIN CREDENTIALS
- ADMIN USERNAME : "ADMIN", ADMIN PASSWORD : "PASSWORD"
  
###### Add Course: `POST /api/add_course`
  ```json
  {
    "title" : "springboot"
  }
  ```
  Auth type : Bearer token
- token :
  ```
  admin_token
  ```
- Response :
  ```
  {
     "id" : "course_id",
     "title" : "course_title"
  }
  ```

###### LIST ALL STUDENTS ENROLLED IN A COURSE
- `GET /api/admin/course/{courseId}/students`
- Auth type : Bearer token
- token :
  ```
  admin_token
  ```
- RESPONSE :
  ```
  [
      {
        "id" : "student_id",
        "username" : "student_name",
        "email" : "student_email"
     },
     {
        "id" : "student_id2",
        "username" : "student_name2",
        "email" : "student_email2"
     }...
  ]
  ```
  
#### USER AUTHENTICATED ENDPOINTS:
###### Enroll in course
- `POST /api/students/{studentID}/enroll/{courseID}`
- Auth type : Bearer token
- token :
  ```
  student_token
  ```
  Response :
  ```
  "Student Enrolled succesfully"
  ```
  
###### Remove Enrollment 
- `DELETE /api/students/{studentId}/enroll/{courseId}`
- Auth type : Bearer token
- token :
  ```
  student_token
  ```
  Response :
  ```
  "Enrollment removed successfully"
  ```

###### Get all enrolled courses for a student
- `GET /api/students/{studentId}/courses`
- Auth type : Bearer token
- token :
  ```
  student_token
  ```
- Response :
  ```
  {
    {
     "id" : "course_id",
     "title" : "course_title"
    },
    {
    "id" : "course_id_2",
    "title" : "course_title_2"
    }
  }
  ```
   

## Error Responses
```json
{
    "message" : "Error message"
    "status": 400
}
```

### Prerequisites
- Java 21
- Maven



