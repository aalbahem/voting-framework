package voting.strategy;

import helpers.CollectionHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



import flanagan.analysis.Stat;


import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;

public class CombMEDConceretStrategy implements AggregationStrategy {

	@Override
	public Map<String, Double> aggregate(VoterProvider provider) {
		Map<String, List<Double>> scoreListMap = new HashMap<>();
		
		
		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			List<Double> scoreList = new LinkedList<>();
			
			if (scoreListMap.containsKey(parentId))
			{
			  scoreList = scoreListMap.get(parentId);
			}
			
			scoreList.add((double)voter.score);
			scoreListMap.put(parentId, scoreList);
		}
		
		Map<String, Double> scoresMap = new HashMap<>();
		
		for (String key : scoreListMap.keySet()){
			scoresMap.put(key, Stat.median(CollectionHelper.convert(scoreListMap.get(key).toArray(new Double[1]))));
		}
		
	  return scoresMap;
	}

}
