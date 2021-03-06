##Coding Challenge 1: Toy Robot
####Requirements
    The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 x 5 units. There are no other obstructions on the table surface.
    The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement that would result in the robot
    falling from the table must be prevented, however further valid movement commands must still be allowed.

#### Application
    Create an application that can read in commands of the following form:

    PLACE X,Y,F
    MOVE
    LEFT
    RIGHT
    REPORT

    PLACE : will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.
            The origin(0,0) can be considered to be the SOUTH WEST most corner.
    MOVE : will move the toy robot one unit forward in the direction it is currently facing.
    LEFT and RIGHT : will rotate the robot 90 degrees in the specified direction without changing the position of the robot.
    REPORT : will announce the X,Y and F of the robot.


#### Expectations of your application:
    The application must be a Spring-Boot-Application
    Input must be realised over the REST-API, take care when designing the REST-API
    The robot that is not on the table can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands.
    The robot must not fall off the table during movement. This also includes the initial placement of the toy robot.
    Any move that would cause the robot to fall must be ignored.
    It is not required to provide any graphical output showing the movement of the toy robot.
#### Conceptual Example from CLI implementation:
    PLACE 0,0,NORTH MOVE REPORT
    Output: 0,1,NORTH

    PLACE 0,0,NORTH LEFT REPORT
    Output: 0,0,WEST

    PLACE 1,2,EAST MOVE MOVE LEFT MOVE REPORT
    Output: 3,3,NORTH

    MOVE REPORT
    Output: ROBOT MISSING

#### Assumption:
    I needed to clarify few points before starting this program. But I did not have the opportunity to ask.
    So I made a few assumptions before starting. Bellow are some of them.
    * If any MOVE command results the toy to fall, those are ignored and placed at the initial postion.
    ex PLACE 1,1,WEST MOVE REPORT. This will place the toy robot at 1,1,WEST after the MOVE is executed.
    * I tried to keep same input and output structure given in CLI Implementation 
        to keep the implementation as close as possible with the requirement.
    * No Response if REPORT command is missing.
    * (0,0), (5,0),(0,5), (5,5) - Assumed the co-oridnates of the table.

#### How to test toy-robot-api from Postman client
    if your are running from localhost:
    http://localhost:8081/toy-robot-api/v1/command
    Headers 
    Accept: application/json
    Content-Type: application/json
    Http method: POST with Json Request body
    Example Request body: 
    {
    "command": "PLACE 0,2,WEST MOVE LEFT RIGHT MOVE LEFT MOVE REPORT"
    }
#### Spring Test cases
    Included in the test folder.
