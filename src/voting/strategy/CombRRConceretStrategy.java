package voting.strategy;

import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;

import java.util.HashMap;
import java.util.Map;

public class CombRRConceretStrategy implements AggregationStrategy{
	
	@Override
	public Map<String, Double> aggregate(VoterProvider provider) {
		Map<String, Double> scores = new HashMap<>();
		
		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			
			double parentScore = 0.0;
			
			if (scores.containsKey(parentId))
			{
				parentScore = scores.get(parentId);
			}
			
			parentScore += (1.0/voter.rank);
			
			scores.put(parentId, parentScore);
		}
		
	  return scores;
	}


}
