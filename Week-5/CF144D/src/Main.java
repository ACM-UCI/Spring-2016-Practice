
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numCities = sc.nextInt();
		int numRoads = sc.nextInt();
		int capital = sc.nextInt() - 1; //Convert to 0-based
		
		ArrayList<HashMap<Integer, Integer>> distances = new ArrayList<HashMap<Integer, Integer>>();
		for(int i = 0; i < numCities; i++)
		{
			distances.add(new HashMap<Integer, Integer>());
		}

		for(int i = 0; i < numRoads; i++)
		{
			int city1 = sc.nextInt() - 1; //Change from 1-based to 0-based
			int city2 = sc.nextInt() - 1;
			int distance = sc.nextInt();
			
			distances.get(city1).put(city2, distance);
			distances.get(city2).put(city1, distance);
		}
		
		int missileDist = sc.nextInt();
		sc.close();
		
		int[] shortestPath = new int[numCities];
		for(int i = 0; i < numCities; i++)
		{
			if(i == capital)
				shortestPath[i] = 0;
			else
				shortestPath[i] = -1;
		}
		
		PriorityQueue<Pair> queue = new PriorityQueue<Pair>();
		for(int city : distances.get(capital).keySet())
		{
			queue.add(new Pair(city, distances.get(capital).get(city)));
		}
		
		int numSilos = 0;
		while(!queue.isEmpty())
		{
			Pair nextPair = queue.remove();
			
			if(shortestPath[nextPair.city] == -1)
			{
				//System.out.println("Distance to " + (nextPair.city + 1)
				//		+ " is " + nextPair.distance);
				
				shortestPath[nextPair.city] = nextPair.distance;
				for(int nextCity : distances.get(nextPair.city).keySet())
				{
					if(shortestPath[nextCity] == -1)
					{
						int newDistance = distances.get(nextPair.city).get(nextCity) + nextPair.distance;
						queue.add(new Pair(nextCity, newDistance));
					}
				}
			}
		}
		
		for(int i = 0; i < numCities; i++)
		{
			if(shortestPath[i] == missileDist)
			{
				//System.out.println("Silo at " + (i + 1));
				numSilos++;
			}
			
			for(int adjCity : distances.get(i).keySet())
			{
				if(adjCity < i)
				{
					int roadLength = distances.get(i).get(adjCity);
					int silo1 = missileDist - shortestPath[i];
					int silo2 = missileDist - shortestPath[adjCity];
					
					//System.out.println("Edge: " + (i + 1) + ", " + (adjCity + 1));
					//System.out.println("Silo distances: " + silo1 + ", " + silo2);
					if(silo1 > 0 && silo2 > 0)
					{
						if(silo1 + silo2 < roadLength)
						{
							//System.out.println("2 silos on edge from " + (i + 1)
							//	+ " to " + (adjCity + 1));
							numSilos += 2;
						}
						else if(silo1 + silo2 == roadLength)
						{
							//System.out.println("1 silo on edge from " + (i + 1)
							//		+ " to " + (adjCity + 1));
							numSilos++;
						}
					}
					else if(silo1 <= 0 && silo2 > 0)
					{
						if(silo2 < roadLength)
						{
							//System.out.println("Silo near " + (adjCity + 1) 
							//		+ " on road to " + (i + 1));
							numSilos++;
						}
					}
					else if(silo2 <= 0 && silo1 > 0)
					{
						if(silo1 < roadLength)
						{
							//System.out.println("Silo near " + (i + 1)
							//		+ " on road to " + (adjCity + 1));
							numSilos++;
						}
					}
				}
			}
		}
		
		System.out.println(numSilos);
	}
}

class Pair implements Comparable<Pair>
{
	int city;
	int distance;
	
	public Pair(int city, int distance)
	{
		this.city = city;
		this.distance = distance;
	}
	
	public int compareTo(Pair otherPair)
	{
		return ((Integer)(this.distance)).compareTo(otherPair.distance);
	}
}
