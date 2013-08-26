package voting.strategy;

import java.util.HashMap;
import java.util.Map;

import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;

public class CombCountConceretStrategy implements AggregationStrategy{


	@Override
	public Map<String, Double> aggregate(VoterProvider provider) {
		Map<String,Double> countsMap = new HashMap<>();

		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			int parentCount = 0;
		
			if (countsMap.containsKey(parentId))
			{
				parentCount = countsMap.get(parentId).intValue();
			}
			
			parentCount ++;
			countsMap.put(parentId, (double)parentCount);
		}

		return countsMap;
	}

}
