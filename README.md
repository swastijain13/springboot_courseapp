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

### Student
- `id`: Integer (Auto-generated)
- `name`: String

### Course
- `id`: Integer (Auto-generated)
- `title` : String


## API Endpoints

- Add Student: `POST /api/add_student`
  ```json
  {
    "name": "swasti"
  }
  ```

- Add Course: `POST /api/add_course`
  ```json
  {
    "title" : "springboot"
  }
  ```

- Enroll Student: `POST /api/students/<student_id>/enroll/<course_id>`
- Response :
  ```
  "Student Enrolled succesfully"
  ```
- Remove Enrollment : `DELETE /api/students/<student_id>/enroll/<course_id>`
- Response :
  ```
  "Enrollment removed successfully"
  ```

- Get all enrolled courses for a student : `GET /api/students/<student_id>/courses`
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



