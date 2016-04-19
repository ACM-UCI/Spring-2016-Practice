
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int minDifficulty = sc.nextInt();
		int maxDifficulty = sc.nextInt();
		int minDiff = sc.nextInt();
		
		ArrayList<Integer> difficulties = new ArrayList<Integer>();
		for(int i = 0; i < n; i++)
			difficulties.add(sc.nextInt());
		sc.close();
		
		int options = 0;
		int numSets = (int)Math.pow(2, n);
		for(int i = 1; i < numSets; i++)
		{
			ArrayList<Integer> currSet = new ArrayList<Integer>();
			for(int j = 0; j < n; j++)
			{
				if((i & (1 << j)) > 0)
					currSet.add(difficulties.get(j));
			}
			
			Collections.sort(currSet);
			int totalDifficulty = 0;
			for(int diff : currSet)
				totalDifficulty += diff;
			
			int thisDiff = currSet.get(currSet.size() - 1) - currSet.get(0);
			
			if(totalDifficulty >= minDifficulty && totalDifficulty <= maxDifficulty
					&& thisDiff >= minDiff)
				options++;
		}
		
		System.out.println(options);
	}

}
