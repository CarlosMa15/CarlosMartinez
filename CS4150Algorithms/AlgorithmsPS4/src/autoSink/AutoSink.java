/*************************************************************
 * Author: Carlos Martinez
 * Date: September 18, 2018
 * Assignment: Auto Sink
 ************************************************************/

package autoSink;

// Import Statements
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class keeps track of most of the information of the graph
 * @author Carlos Martinez
 */
class Vertex implements Comparable<Vertex> {
	
	/**
	 * Name of the city
	 */
	public String name;
	
	/**
	 * The Cities it can visit
	 */
	public LinkedList<Edge> edges = new LinkedList<Edge>();
	
	/**
	 * The minimal amount of cost available to do a trip, default +Infinity
	 */
	public double minValue = Double.POSITIVE_INFINITY;
	
	/**
	 * The city where the person came from, so you can track your way back
	 */
	public Vertex previous;

	/**
	 * This creates a new Vertex
	 * @param name - The name of the the City
	 */
	public Vertex(String name) {
		this.name = name;
	}

	/**
	 * This prints the name of the City, Used for debugging purposes
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * Used to be able Vertex, with the minValue
	 */
	public int compareTo(Vertex o) {
		return Double.compare(this.minValue, o.minValue);
	}
}

/**
 * This keeps track of the city and it's cost
 * @author Carlos Martinez
 */
class Edge {
	
	/**
	 * City
	 */
	public Vertex goal;
	
	/**
	 * Cost to visit City
	 */
	public double cost;

	/**
	 * Creates Edge
	 * @param goal - City in Vertex form
	 * @param cost - Cost to visit City
	 */
	public Edge(Vertex goal, double cost) {
		this.goal = goal;
		this.cost = cost;
	}
}

/**
 * This class was created and used to complete the the Auto Sink assignment
 * on Kattis. This takes a numbers of cities, an number of roads, and a number
 * of trips. This class keeps track of its information and at the end prints the
 * results of all the most minimal amount of cost it takes to make that trip. If
 * you are already there print zero. If no trip is possible print "NO"
 * @author Carlos Martinez
 */
public class AutoSink {
	
	/**
	 * This methods starts from the starting city, and every city it can
	 * visit it sets its minValue to the minimal cost it takes to visit it
	 * @param start the starting point from a trip
	 */
	public static void findPath(Vertex start) {
		start.minValue = 0.0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(start);
		
		while (!vertexQueue.isEmpty()) {
			Vertex firstCity = vertexQueue.poll();
			
			//Look through the cities to get shortest path
			for (Edge visitedCity : firstCity.edges) {
				Vertex newCity = visitedCity.goal;
				double weight = visitedCity.cost;
				double distance = firstCity.minValue + weight;
				
				if (distance < newCity.minValue) {
					vertexQueue.remove(newCity);
					newCity.minValue = distance;
					newCity.previous = firstCity;
					vertexQueue.add(newCity);
				}
			}
		}
	}

	/**
	 * This resets the distance of the Vertex to be able to calculate
	 * the next trip's information.
	 * @param map - The Map Vertex Representation Of The Graph
	 */
	public static void resetAll(HashMap<String, Vertex> map) {
		for (Map.Entry<String, Vertex> entry : map.entrySet()) {
			map.get(entry.getKey()).minValue = Double.POSITIVE_INFINITY;
		}
	}

	//Start of the work
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		
		//Objects
		Scanner input = new Scanner(System.in);                           //Gets user input
		int cities = 0;                                                   //The numbers of cities
		int highways = 0;												  //The number of roads
		int trips = 0;													  //The number of trip to do
		
		//Gets number of cities and starts setting things
		cities = Integer.parseInt(input.nextLine());                      //Get number of cities
		HashMap<String, Integer> vertex = new HashMap<String, Integer>(); //City Cost Tracker
		HashMap<String, Vertex> vertices = new HashMap<String, Vertex>(); //City Road Tracker
		
		//Read the list of city and cost, starts keeping track of information
		for (int i = 0; i < cities; i++) {
			String[] cityCostElements = input.nextLine().split(" ");
			String cityName = cityCostElements[0].toString();
			vertices.put(cityName, new Vertex(cityName));
			int cityCost = Integer.parseInt(cityCostElements[1]);
			vertex.put(cityName, cityCost);
		}
		
		//Gets number of roads and starts keeping track of information
		highways = Integer.parseInt(input.nextLine());
		for (int i = 0; i < highways; i++) {
			String[] cityRoadElements = input.nextLine().split(" ");
			String firstCity = cityRoadElements[0].toString();
			String secondCity = cityRoadElements[1].toString();
			vertices.get(firstCity).edges.add(new Edge(vertices.get(secondCity), vertex.get(secondCity)));
		}
		
		//Gets the number of trips
		trips = Integer.parseInt(input.nextLine());
		List<String> resultsForPrinting = new ArrayList<String>();
		
		//Goes through each trip calculating the minimal trip cost, keeps tracks of the results
		for (int i = 0; i < trips; i++) {
			if (input.hasNextLine()) {
				String[] tripElements = input.nextLine().split(" ");
				String startingCity = tripElements[0].toString();
				String endingCity = tripElements[1].toString();
				
				// If they are the same city cost is zero, you already there
				if (startingCity.equals(endingCity)) {
					resultsForPrinting.add("" + 0);
					continue;
				}
				
				
				findPath(vertices.get(startingCity)); // Calculate the path to city
				
				
				 //If path = +infinity that means that the city can not be reach by
				 //the starting city, no route connecting the two cities. 
				if (vertices.get(endingCity).minValue == Double.POSITIVE_INFINITY) {
					resultsForPrinting.add("NO");
					
				} else { //Print City Cost if Found 
					resultsForPrinting.add("" + (int) vertices.get(endingCity).minValue);
				}
				
				//Reset things for next calculations
				resetAll(vertices);
			}
		}
		
		//Print The Results Of Each Trip
		for (String result : resultsForPrinting) {
			System.out.println(result);
		}
		return;
	}
}