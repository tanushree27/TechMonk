# TECHMONK

Backend microservices architecture of a stackover flow competitor.
The System covers the following features.
1. Top questions are to be shown in the home page
2. Users can create a profile
3. Users can post a question, tag a question
4. Users can answer to a question. Rich media content (photos/videos)
   can be added as an answer.
5. Users can answer to an answer.
6. Users can vote to an answer or question
7. User can search the tags and browse the questions by tags
8. Users can search questions/answers by text

# Setup

## Prerequisites
- PostgreSQL
- Redis (Caching)
- MinIO (blob storage for media)
- Maven
- Java

## Steps to run
- Spin up all the prerequisites
- Update the application.properties with their respective urls and ports.
- Start both the microservices using `mvn run`