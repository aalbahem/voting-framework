package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CollectionHelper {

	public static double[] convert(Double[] dd)
	{
		double[] d = new double[dd.length];
		
		for (int i = 0; i < d.length; i++)
			d[i] = dd[i].doubleValue();
		
		return d;
	}
	
	
	public static double[] convert(ArrayList<Double> dd)
	{
		double[] d = new double[dd.size()];
		
		for (int i = 0; i < d.length; i++)
			d[i] = dd.get(i);
		
		return d;
	}
	
	public static int[] convert(Integer[] ii)
	{
		int[] it = new int[ii.length];
		
		for (int j = 0; j < it.length; j++)
			it[j] = ii[j].intValue();
		
		return it;
	}
	
	
	public static Integer[] convert(int[] ii)
	{
		Integer[] it = new Integer[ii.length];
		
		for (int j = 0; j < it.length; j++)
			it[j] = ii[j];
		
		return it;
	}
	
	public static double[][] convert(Double[][] dd)
	{
		double[][] d = new double[dd.length][];
		
		for (int i = 0; i < dd.length; i++)
		{
			d[i] = new double[dd[i].length];
			
			for (int j = 0;  j < dd[i].length; j++)
				d[i][j] = dd[i][i].doubleValue();
		}
			
		return d;
	}
	

	public static int[][] convert(Integer[][] ii)
	{
		int[][] it = new int[ii.length][];
		
		for (int i = 0; i < ii.length; i++)
		{
			it[i] = new int[ii[i].length];
			
			for (int j = 0;  j < ii[i].length; j++)
				it[i][j] = ii[i][i].intValue();
		}
			
		return it;
	}
	
	
	public static Set<Integer> toSet(int[] i)
	{
		Set<Integer> iset = new HashSet<Integer>();
		
		for (int it : i )
			iset.add(it);
		return iset;
	}
	
	public static int[] toIntArray(List<String> list){
		int[] items = new int[list.size()];
		
		int i = 0;
		Iterator<String> itr = list.iterator();
		while(itr.hasNext() ){
		  items[i++] = Integer.parseInt(itr.next());	
		}
		
		return items;
	}
}
