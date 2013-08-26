package voting.strategy;

import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;

import java.util.HashMap;
import java.util.Map;


public class CombBordaCountConceretStrategy implements AggregationStrategy{

	@Override
	public Map<String, Double> aggregate(VoterProvider provider) 
	{
		
		Map<String, Double> scores = new HashMap<>();
		
		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			
			double parentScore = 0.0000;
			
			if (scores.containsKey(parentId))
			{
				parentScore = scores.get(parentId);
			}
			
		//	System.out.printf("p:%.0f s: %d r: %d", parentScore,provider.size(),voter.rank );
			parentScore +=  provider.size() - ( voter.rank - 1 );
			//System.out.printf("p:%.0f \n",parentScore);
			scores.put(parentId, parentScore);
		}
		
	  return scores;
	}

}
