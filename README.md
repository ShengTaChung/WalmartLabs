# WalmartLabs Coding Challenge

### Assumptions:
1. The spec does not define what is a best seat, so I created a venue abstract class as a template class. And I created the seating levels in myVenue class (like airline seating classes) where user can configure number of levels in venue and number of seats in each level. When a user requests a best seat, the organizer will search for an available seat from the highest level to lowest (1 to n) and return a seat from the level.

2. A venue is represented as n by m 2-D form.

3. There should be a third-party application that provides configuration and uses the functionalities provided by this ticket service.

4. A third-party application should determine where each seat should be located in venue.

5. The remove expired seatholds function should be run by another application once every x seconds.

6. A user can hold and reserve seats multiple times.

7. When a seat is reserved the email of the holder will be identified on the seat.

8. No refund or switch ownership after reservation is completed.


### Instructions for building/testing the project
1. Clone the repo
`git clone https://github.com/ShengTaChung/WalmartLabs.git`

2. cd into WalmartLabs folder
`cd WalmartLabs`

3. Build the project:
`./gradlew build`

4. run test
`./gradlew test`

