package voting.strategy;


import java.util.HashMap;
import java.util.Map;

import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;

public class ExpCombSUMConceretStrategy implements AggregationStrategy {

	public Map<String, Double> aggregate(VoterProvider provider) {
		Map<String, Double> scores = new HashMap();
		
		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			
			double parentScore = 0.0;
			
			if (scores.containsKey(parentId))
			{
				parentScore = scores.get(parentId);
			}
			
			parentScore += Math.exp(voter.score);
			
			scores.put(parentId, parentScore);
		}
		
	  return scores;

	}

}
