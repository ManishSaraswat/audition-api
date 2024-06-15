# Audition API

Audition API is a Spring Boot application developed in Java, using Gradle as a build tool.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Gradle

### Installing

1. **Clone the repository**
<br/> git clone https://github.com/ManishSaraswat/audition-api.git <br/>


2. **Navigate into the cloned repository**
<br/> cd audition-api <br/>


3. **Build the project**
<br/> gradle build <br/>


4. **Run the project**
<br/> gradle bootRun <br/>


5. **Run the tests**
<br/> gradle test <br/>

## API Documentation
<br/> Audion API supports 3 endpoints <br/>

### 1. Get all the available Audition Posts
#### `GET /posts?filter={userId}`

- **Description**: Retrieves all available posts. User can provide a option userId filter to filter out.
- **Parameters**: userId (optional).
- **Response**:
  ```json
  [
    {
        "userId": 1,
        "id": 1,
        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
    },
    {
        "userId": 1,
        "id": 2,
        "title": "qui est esse",
        "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
    }
  ]

### 2. Get the Audition Post by the id
#### `GET /posts/{id}`

- **Description**: Retrieves Audition Posts by given Id.
- **Parameters**: Id
- **Response**:
  ```json
  {
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
  }

### 3. Get all comments associated with a post
#### `GET /posts/{id}/comments`

- **Description**: Retrieves all available Comments associated with a post.
- **Parameters**: Id.
- **Response**:
  ```json
  [
    {
        "postId": 1,
        "id": 1,
        "name": "id labore ex et quam laborum",
        "email": "Eliseo@gardner.biz",
        "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
    }
  ]


## Built With
Spring Boot - The web framework used <br/>
Gradle - Dependency Management


