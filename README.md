# Aftas Sports Club Competition Management

## Overview

Aftas Sports Club, located in Mirleft, Tiznit, offers a variety of sports and activities, including Surfing, Tennis, Quad Biking, Underwater Fishing, Paragliding, and more. The club regularly organizes underwater fishing competitions across different regions of Morocco. Members can participate in these competitions by paying a fee to confirm their participation. The club manages logistics, appoints a jury to calculate results, and displays the podium.

Aflas Club aims to modernize competition management by developing a responsive web application to meet the needs of club administration and the jury.

## Backend (Spring Boot)

### User Stories

#### Admin Features

1. As an administrator, I want to add a competition.
    - Specify a unique code for the competition.
    - Provide the date, start time, end time, number of participants, and location.

2. As an administrator, I want to view a list of competitions with filters (ongoing, closed).
    - Filter competitions that are currently ongoing.
    - Filter closed competitions.

3. As an admin, I want to register a member for a competition.
    - Search for a member by number, name, or surname.
    - Register a member for a competition if they are not already registered.

4. As a user, I want to record the result of a competition.
    - Record the number of fishes caught during the competition.
    - Confirm my participation after recording the result.

5. As a user, I want to view the podium for the top three participants in the competition.
    - View the podium after the competition ends.

#### Features

- Use Spring Boot to develop the API.
- Use Liquibase for managing db
- Organize the application into layers.
- Data validation is mandatory.
- Centralized exception handling (RestControllerAdvice).
- Implement pagination for search results and competition lists.
- Write unit tests for the competition results calculation service.

#### Steps to run the application
```bash
# Clone the repository
git clone https://github.com/shadowgks/AppGCM.git
```
```bash
# Go to the backend directory
cd AppGCM
```
```bash
# install dependencies
mvn install
```
```bash
# Run the application
mvn spring-boot:run
```
[Postman Collection download ](https://api.postman.com/collections/25872642-165b598c-f840-4c1a-9fcc-b21650d2022b?access_key=PMAT-01HJ5YFS3MSX3E5T27D3Q53GJA)

[Frontend Repository](https://github.com/shadowgks/AppGCM-FrontEnd)

[Canva Presentation](https://www.canva.com/design/DAF3WU10IBE/OhjXvqL-PUy_bDxDsu1qxw/edit?utm_content=DAF3WU10IBE&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

