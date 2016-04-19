
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int numPeople = sc.nextInt();
		int numPairs = sc.nextInt();
		
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < numPeople; i++)
			names.add(sc.next().trim());
		
		ArrayList<String> pair1 = new ArrayList<String>();
		ArrayList<String> pair2 = new ArrayList<String>();
		for(int i = 0; i < numPairs; i++)
		{
			pair1.add(sc.next().trim());
			pair2.add(sc.next().trim());
		}
		
		int numSets = (int)Math.pow(2, numPeople);
		ArrayList<String> bestSet = new ArrayList<String>();
		for(int i = 1; i < numSets; i++)
		{
			ArrayList<String> currSet = new ArrayList<String>();
			for(int j = 0; j < numPeople; j++)
			{
				if((i & (1 << j)) > 0)
				{
					currSet.add(names.get(j));
				}
			}
			
			boolean validSet = true;
			for(int j = 0; j < numPairs; j++)
			{
				String person1 = pair1.get(j);
				String person2 = pair2.get(j);
				if(currSet.contains(person1)
						&& currSet.contains(person2))
				{
					validSet = false;
					break;
				}
			}
			
			if(validSet)
			{
				if(currSet.size() > bestSet.size())
				{
					bestSet = currSet;
				}
			}
		}
		
		Collections.sort(bestSet);
		System.out.println(bestSet.size());
		for(String name : bestSet)
		{
			System.out.println(name);
		}
	}
}
