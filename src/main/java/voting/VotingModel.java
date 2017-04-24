package voting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class VotingModel {
	
	private VoterProvider voterProvider;
	

	public VotingModel(VoterProvider provider)
	{
	   this.voterProvider = provider;	
	}
	
	public Aggregator[] vote(AggregationStrategy aggregationStrategy) throws IOException
	{
		Map<String, Double> results = aggregationStrategy.aggregate(this.voterProvider);
		
		ArrayList<Aggregator>aggregators = new ArrayList();
		int i = 0;
		
		for (Map.Entry<String, Double> entry : results.entrySet())
		{
			aggregators.add( new Aggregator(entry.getKey(), entry.getValue().doubleValue()));
		}
		
		Collections.sort(aggregators);
		Collections.reverse(aggregators);
		
		return aggregators.toArray(new Aggregator[1]);
	}

	public Map<String, List<String>> distributeVoters() throws Exception {
		return voterProvider.groupVoters();
	}
}
