# ParkingLot

##Problem Statment
Recently the new parking lot opened at Adidas Headquarters. Management wants this parking to be managed by a computer and asked you to design a solution using object oriented principles.
The characteristics of the parking are the following:
*The parking lot has many levels. Each level has multiple rows of spots.
*The parking lot can park bikes, motorcycles, cars and buses.
*The parking lot has mini spots, compact spots and large spots.
*A bike and a motorcycle can park in any spot
*A car can park in either a single compact spot or a single large spot
*A bus can park in six large spots that are consecutive and within the same row. It cannot park in other spots.

The solution should be generic, that is, we should be able to use the same solution for different parking lots (other than Adidas) without recompiling the source code. In the specific case of Adidas, the parking 
lot has 5 levels with 15 rows of spots each. Each row has 15 mini spots, 40 compact spots and 20 large spots.

Adidas employees (bikes, motorcycles and cars) prefer to park in the lower levels whenever possible and, given a level, they prefer the rows nearer to the exit (you can assume that the rows with lower numbers and nearer to the exit).

Bus drivers prefer rows nearer to the exit over parking level (e.g. if it is possible to park in the third row of the first floor and in the first row of the second floor they would go to the second floor).

When entering the parking lot, Adidas employees give their employee number and the parking tracking system automatically records where the bike/motorbike/car/bus is parked . When leaving the parking lot, Adidas employees give their employee id and the system returns the level, row and spot where their bike/motorbike/car/bus is parked. This comes handy as they are forgetful. They are also impatient, so this operation should be as fast as possible.

Your task is to write a console Java application with the following behaviour:
  1.  When the user enters his employee id (8 digit integer) and type of vehicle, find the most suitable spot and park the vehicle in that spot.
    *  Example input: park 12345678 bike
    *  Example output: Bike for employee 12345678 is parked in level 2, row 5, spot 7.

  2.  When the user enters an employee id, show where his/her vehicle is parked (remember, this should be as fast as possible):
    *  Example input: employee 12345678
    *  Example output:  Bike for employee 12345678 is parked in level 2, row 5, spot 7.

  3.  The user can ask the program to show the current occupation of the parking lot, a specific level , a specific row within a level, or a specific spot. The program will give the information requested plus the percentage of occupation (for parking, level and row).
    * Example input: parking
    * Example output: 
      1. Percentage of occupation (parking): 29% (10% mini spots, 10% compact spots, 9% large spots)
      2. level 1, row 1, spot 1: bike for employee 87654321
      3. Level 1, row 1, spot 2: motorbike for employee 09876543
      4. …
    * Example input: parking level 3
    * Example output:
      1. Percentage of occupation (parking level 3): 29% (10% mini spots, 10% compact spots, 9% large spots)
      2. level 3, row 1, spot 1: bike for employee 87654321
      3. Level 3, row 1, spot 2: motorbike for employee 09876543
      4. …
    * Example input: parking level 3 row 5
      1. Percentage of occupation (parking level 3 row 5): 29% (10% mini spots, 10% compact spots, 9% large spots)
      2. level 3, row 5, spot 1: bike for employee 87654321 
      3. Level 3, row 5, spot 2: motorbike for employee 09876543
      4. …
    * Example input: parking level 3 row 5 spot 4
      1.  level 3, row 5, spot 4: bike for employee 87654321
  4. The user can ask the program to free a spot (simulating an employee leaving the parking lot). If the spot specified is one occupied by a bus, then the bus will leave and all large spots it occupied will be freed:
    * Example input: leave level 1, row 3, spot 5
    * Example output: bike for employee 18273645 left the parking

  5. It should accept the commands “help” for printing instructions and “exit” for terminating the execution.
