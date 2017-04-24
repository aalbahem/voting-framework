import static org.junit.Assert.*;

import helpers.StatsHelper;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import voting.Aggregator;
import voting.VotingModel;
import voting.provider.AggregatorProvider;
import voting.strategy.CombANZConceretStrategy;
import voting.strategy.CombBordaCountConceretStrategy;
import voting.strategy.CombCountConceretStrategy;
import voting.strategy.CombGNZConceretStrategy;
import voting.strategy.CombMAXConceretStrategy;
import voting.strategy.CombMEDConceretStrategy;
import voting.strategy.CombMINConceretStrategy;
import voting.strategy.CombMNZConceretStrategy;
import voting.strategy.CombPRODConceretStrategy;
import voting.strategy.CombRRConceretStrategy;
import voting.strategy.CombSUMConceretStrategy;
import voting.strategy.ExpCombANZConceretStrategy;
import voting.strategy.ExpCombMNZConceretStrategy;
import voting.strategy.ExpCombSUMConceretStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.junit.Before;
import org.junit.Test;



public class AggregatorStrategyTest {

	
	Aggregator[] docs;// we use an array of aggregates as voters
	double[] d1 = {1.0};
	double[] d2 = {3.0,2.0};
	double[] d3 = {6.0,5.0,4.0};
	int[] r1 ={6};
	int[] r2 ={4,5};
	int[] r3 ={1,2,3};
	VotingModel model;
	
	@Before
	public void setUp()
	{
	
	  docs = new Aggregator[6];
	  docs[0] = new Aggregator("3",d3[0]);
	  docs[1] = new Aggregator("3",d3[1]);
	  docs[2] = new Aggregator("3",d3[2]);
	  docs[3] = new Aggregator("2",d2[0]);
	  docs[4] = new Aggregator("2",d2[1]);
	  docs[5] = new Aggregator("1",d1[0]);
	  
	  model = new VotingModel(new AggregatorProvider(docs));
	  
		
	}
	
	@Test
	public void testCombBordaCount() throws IOException
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		Aggregator[] aggregratos = model.vote(new CombBordaCountConceretStrategy());
		for (Aggregator aggregator : aggregratos) {
			result.put(aggregator.id,aggregator.score);
		}
		
		double s1 = (6-5);
		double s2 = (6-4) + (6-3);
		double s3 = (6-0) + (6-1) +(6-2)  ;
		
		assertEquals("1", s1, result.get("1"),0.00); 
		assertEquals("2", s2, result.get("2"),0.00);
		assertEquals("3",s3, result.get("3"),0.00);
	}
	
	@Test
	public void testCombRRConceretStrategy() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombRRConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", 1.0/r1[0], result.get("1"),0.00); 
		assertEquals("2", 1.0/r2[0] + 1.0/r2[1], result.get("2"),0.00);
		assertEquals("3", 1.0/r3[0] + 1.0/r3[1] + 1.0/r3[2] , result.get("3"),0.00);
	}
	
	
	@Test
	public void testCombSUM() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombSUMConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1",d1[0] , result.get("1"),0.00); 
		assertEquals("2", d2[0]+d2[1], result.get("2"),0.00);
		assertEquals("3", d3[0]+d3[1]+d3[2], result.get("3"),0.00);
	}
	
	
	@Test
	public void testCombPROD() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombPRODConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1",d1[0] , result.get("1"),0.00); 
		assertEquals("2", d2[0]*d2[1], result.get("2"),0.00);
		assertEquals("3", d3[0]*d3[1]*d3[2], result.get("3"),0.00);
	}
	
	
	@Test
	public void testCombANZ() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombANZConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", StatUtils.mean(d1), result.get("1"),0.00);
		assertEquals("2", StatUtils.mean(d2), result.get("2"),0.00);
		assertEquals("3", StatUtils.mean(d3), result.get("3"),0.00);
	}
	
	
	@Test
	public void testCombMNZ() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombMNZConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", (d1[0])* 1, result.get("1"),0.00); 
		assertEquals("2", (d2[0]+d2[1])*2, result.get("2"),0.00);
		assertEquals("3", (d3[0]+d3[1]+d3[2])*3, result.get("3"),0.00);
	}
	
	
	@Test
	public void testCombCount() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombCountConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", d1.length, result.get("1").intValue()); 
		assertEquals("2", d2.length, result.get("2").intValue());
		assertEquals("3", d3.length, result.get("3").intValue());
	}
	
	
	@Test
	public void testCombMAX() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombMAXConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", d1[0], result.get("1"),0.00); 
		assertEquals("2", d2[0], result.get("2"),0.00);
		assertEquals("3", d3[0], result.get("3"),0.00);
	}
	
	
	@Test
	public void testCombMIN() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombMINConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", d1[0], result.get("1"),0.00); 
		assertEquals("2", d2[1], result.get("2"),0.00);
		assertEquals("3", d3[2], result.get("3"),0.00);
	}
	
	
	@Test
	public void testCombMED() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Aggregator aggregator : model.vote(new CombMEDConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}

		assertEquals("1", StatsHelper.median(d1), result.get("1"),0.00);
		assertEquals("2", StatsHelper.median(d2), result.get("2"),0.00);
		assertEquals("3", StatsHelper.median(d3), result.get("3"),0.00);
	}
	
	
	@Test
	public void testExpCombSum() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		for (Aggregator aggregator : model.vote(new ExpCombSUMConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", Math.exp(d1[0]), result.get("1"),0.00); 
		assertEquals("2", Math.exp(d2[0]) + Math.exp(d2[1]), result.get("2"),0.00);
		assertEquals("3", Math.exp(d3[0])+ Math.exp(d3[1]) + Math.exp(d3[2]), result.get("3"),0.00);
	}
	
	
	@Test
	public void testExpCombMNZ() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		for (Aggregator aggregator : model.vote(new ExpCombMNZConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", (Math.exp(d1[0])) * 1, result.get("1"),0.00); 
		assertEquals("2", (Math.exp(d2[0]) + Math.exp(d2[1])) * 2, result.get("2"),0.00);
		assertEquals("3", (Math.exp(d3[0])+ Math.exp(d3[1]) + Math.exp(d3[2])) * 3, result.get("3"),0.00);
	}
	
	
	@Test
	public void testExpCombANZ() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		for (Aggregator aggregator : model.vote(new ExpCombANZConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}

		assertEquals("1", (Math.exp(d1[0])) / 1, result.get("1"),0.00); 
		assertEquals("2", (Math.exp(d2[0]) + Math.exp(d2[1])) / 2, result.get("2"),0.00);
		assertEquals("3", (Math.exp(d3[0])+ Math.exp(d3[1]) + Math.exp(d3[2])) / 3, result.get("3"),0.00);
	}
	
	
	@Test
	public void testCombGMean() throws Exception
	{
		Map<String, Double> result = new HashMap<String, Double>();
		for (Aggregator aggregator : model.vote(new CombGNZConceretStrategy())) {
			result.put(aggregator.id, aggregator.score);
		}
		
		assertEquals("1", Math.pow(d1[0],1.0/d1.length), result.get("1"),0.00); 
		assertEquals("2", Math.pow(d2[0] * d2[1],1.0/d2.length), result.get("2"),0.00);
		assertEquals("3", Math.pow(d3[0] * d3[1] * d3[2],1.0/d3.length), result.get("3"),0.00);
		
	}
	
	
	public void testSort() throws Exception
	{
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (Aggregator aggregator : model.vote(new CombSUMConceretStrategy())) {
			result.add(aggregator.id);
		}
		
		assertEquals("1st : ", "3", result.get(0)); 
		assertEquals("2", "2", result.get(1));
		assertEquals("3", "1", result.get(2));
	}

}
