
//Assume we have coins worth 1, 2, 5, 10, 25, 50
//Make change with the least possible number of coins

//Example input: 57
//Example output: 5 - 2 quarters, 1 nickel, 2 pennies

import java.util.Scanner;
import java.util.ArrayList;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		ArrayList<Integer> denoms = new ArrayList<Integer>();
		denoms.add(1);
		denoms.add(5);
		denoms.add(10);
		denoms.add(25);
		
		int waysOrdered = countWays(n, denoms);
		int waysUnordered = countWays2(n, denoms);
		
		System.out.println(waysOrdered + ", " + waysUnordered);
		/*ArrayList<Integer> answer = makeChange1(n, denoms);
		for(int coin : answer)
			System.out.print(coin + " ");*/
	}
	
	//Bottom-up recursion
	public static ArrayList<Integer> makeChange1(int n, ArrayList<Integer> denoms)
	{
		int[] coins = new int[n + 1];
		coins[0] = 0;
		
		for(int i = 1; i <= n; i++)
		{
			int minCoins = i;
			for(int amount : denoms)
			{
				if(i >= amount)
					minCoins = Math.min(minCoins, coins[i - amount] + 1);
			}
			
			coins[i] = minCoins;
		}

		ArrayList<Integer> answer = getCoins(n, coins, denoms);
		
		return answer;
	}
	
	public static ArrayList<Integer> getCoins(int n, int[] coins, ArrayList<Integer> denoms)
	{
		ArrayList<Integer> coinsToGive = new ArrayList<Integer>();
		
		int amount = n;
		while(amount > 0)
		{
			for(int nextCoin : denoms)
			{
				if(amount >= nextCoin
						&& coins[amount] == coins[amount - nextCoin] + 1)
				{
					coinsToGive.add(nextCoin);
					amount -= nextCoin;
					break;
				}
			}
		}
		
		return coinsToGive;
	}
	
	//Counts the number of sets of coins we can give to make n cents in change,
	//where order does matter
	public static int countWays(int n, ArrayList<Integer> denoms)
	{
		int[] ways = new int[n + 1];
		ways[0] = 1;
		
		for(int i = 1; i <= n; i++)
		{
			for(int nextCoin : denoms)
			{
				if(i >= nextCoin)
					ways[i] += ways[i - nextCoin];
			}
		}
		
		return ways[n];
	}
	
	//Counts the number of sets of coins we can give to make n cents in change,
	//where order does not matter
	public static int countWays2(int n, ArrayList<Integer> denoms)
	{
		int[][] ways = new int[denoms.size()][n + 1];
		ways[0][0] = 1;
		for(int i = 1; i < denoms.size(); i++)
			ways[i][0] = 0;
		
		for(int i = 1; i <= n; i++)
		{
			for(int j = 0; j < denoms.size(); j++)
			{
				for(int k = 0; k <= j; k++)
				{
					//Look at previous rows, and add coin of current denomination
					if(i >= denoms.get(j))
						ways[j][i] += ways[j - k][i - denoms.get(j)];
				}
			}
		}
		
		int totalWays = 0;
		for(int i = 0; i < denoms.size(); i++)
			totalWays += ways[i][n];
		
		return totalWays;
	}
}
