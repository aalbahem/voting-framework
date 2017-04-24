package voting.strategy;

import helpers.CollectionHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;

public class CombMEDConceretStrategy implements AggregationStrategy {

	DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
	public Map<String, Double> aggregate(VoterProvider provider) {
		Map<String, List<Double>> scoreListMap = new HashMap();
		
		
		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			List<Double> scoreList = new LinkedList<Double>();
			
			if (scoreListMap.containsKey(parentId))
			{
			  scoreList = scoreListMap.get(parentId);
			}
			
			scoreList.add((double)voter.score);
			scoreListMap.put(parentId, scoreList);
		}
		
		Map<String, Double> scoresMap = new HashMap();
		
		for (String key : scoreListMap.keySet()){
			descriptiveStatistics = new DescriptiveStatistics(CollectionHelper.convert(scoreListMap.get(key).toArray(new Double[1])));
			scoresMap.put(key, descriptiveStatistics.getPercentile(50));
		}
		
	  return scoresMap;
	}

}
