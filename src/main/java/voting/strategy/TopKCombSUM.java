package voting.strategy;

import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;

public class TopKCombSUM implements AggregationStrategy{

	private int k;
	
	public TopKCombSUM(int k) {
	  this.k = k;
	}

	public Map<String, Double> aggregate(VoterProvider provider) {
		Map<String, Double> scoresMap = new HashMap();
		Map<String,Integer> countsMap = new HashMap();
		
		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			int parentCount = 0;
			double parentScore = 1.0;
			
			if (scoresMap.containsKey(parentId))
			{
				parentScore = scoresMap.get(parentId);
				parentCount = countsMap.get(parentId);
			}
			
			if (parentCount < this.k){
				parentScore += voter.score;
				parentCount ++;
				scoresMap.put(parentId, parentScore);
				countsMap.put(parentId, parentCount);
			}
		}
		
	  return scoresMap;
	}


}
