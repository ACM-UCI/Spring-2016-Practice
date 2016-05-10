
import java.util.Scanner;
import java.util.HashMap;

//Dynamic programming solution for finding the nth Fibonacci number:
//F[0] = 1, F[1] = 1
//F[n] = F[n - 1] + F[n - 2]
public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		//int answer = computeFib(n);
		long answer = computeFib3(n);
		//System.out.println(answer);
	}

	public static int computeFib(int n)
	{
		if(n == 0 || n == 1)
			return 1;
		else
			return computeFib(n - 1) + computeFib(n - 2);
	}
	
	public static long computeFib2(int n)
	{
		long[] fib = new long[n + 1];
		fib[0] = 1;
		fib[1] = 1;
		
		for(int i = 2; i <= n; i++)
		{
			fib[i] = fib[i - 1] + fib[i - 2];
			System.out.println(i + ", " + fib[i]);
		}
		
		return fib[n];
	}
	
	public static long computeFib3(int n)
	{
		HashMap<Integer, Long> fib = new HashMap<Integer, Long>();
		fib.put(0, 1L);
		fib.put(1, 1L);
		return computeFibRec(n, fib);		
	}
	
	public static long computeFibRec(int n, HashMap<Integer, Long> map)
	{
		System.out.println("Computing Fibonacci number: " + n);	
		long fib1, fib2;
		if(map.containsKey(n - 1))
			fib1 = map.get(n - 1);
		else
			fib1 = computeFibRec(n - 1, map);
		
		if(map.containsKey(n - 2))
			fib2 = map.get(n - 2);
		else
			fib2 = computeFibRec(n - 2, map);
		
		long answer = fib1 + fib2;
		map.put(n, answer);
		
		return answer;
	}
}
