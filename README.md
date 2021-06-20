# Uberisation-VRPSPD
Label setting algorithm and nearest neighbor search are used to implement the vehicle routing problem with simultaneous pickup and drop(VRPSPD) 
Project Description:
Digital Supply Chain has enhanced value because of the newer technologies such as IoT, Blockchain, Cloud, AI, etc. This project requires creation of solution components for supply chain - digital twin. This will assist customers in simulation and automation.
My work is involved in the ‘Uberisation’ problem which is part of the supply chain.

	Uberisation is a process in which the use of a digitalised platform enabling peer-peer transactions, helps in minimising the distance between the supplier and customer of a service.

Problem statement: The vehicle routing problem with simultaneous pickup and drop orders (VRPSPD) minimizing the total distance travelled by the vehicle and delay of the delivery without violating the service times.  
The initial solution is implemented using the Label Setting Algorithm.

Workflow:
The overview of VRPSPD is understood by referring through various research papers and other resources.
The objectives of the problem are defined to implement in JAVA and Python.
The data is generated which has to be used as input for the algorithm.
Using the Label Setting algorithm, the objectives are achieved successfully.
The delay is minimised while the vehicle capacity and service time are not violated.


Approach for the implemented algorithm:
From the given data of locations, the feasible locations are found from each source location(i.e.,depot initially)
The feasibility is considered when the total time taken to travel from source location to the next location is greater than or equal to estimated pickup time of that next location.
From the list of feasible locations, the location with minimum distance is chosen to be travelled.
For every location, drop order is checked and if it is present in the vehicle, then the order is removed from the vehicle.
If there is a pickup order, the vehicle capacity constraint is checked and if it is not violating then the order is added to the vehicle.
In case if the vehicle capacity constraint is violated, the order is added to the next vehicle.
The remaining drop orders for each vehicle are dropped using the Nearest Neighbour algorithm.
The whole process is repeated until the service time of the vehicle ends.
Input and Output:
	The input data is extracted from excel sheets and it has the following fields:
Distance between locations(coordinates)
Drop locations 
Weight of the pickup orders
Pickup times for locations with pickup orders
The distance between coordinates is calculated by the Euclidean distance formula.

The output generated data contains the following fields:
Location
Drop location for that particular location
Order id
Pickup order weight - order to be pickup up
Drop order weight - order to be dropped if any
Vehicle number
Estimated Pickup Time - time given by the customer
Actual Pickup Time - time the vehicle reached the location
Findings and Results:
The implemented algorithm works effectively for static input data, as in if the orders are available at the beginning of the day, then the results are found to be accurate by minimizing the total cost.
The algorithm can be used effectively for a certain range of locations i.e., towns and cities.
The delay is minimised while the vehicle capacity and service time are not violated.
The algorithm can scale up to more than 10,000 locations.
The time complexity for the algorithm is O(n^2).
