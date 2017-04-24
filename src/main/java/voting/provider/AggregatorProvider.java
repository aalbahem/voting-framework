package voting.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import voting.Aggregator;
import voting.Voter;
import voting.VoterProvider;

public class AggregatorProvider implements VoterProvider{

	private int next = -1;
	private Aggregator[] aggregators;
	
	public AggregatorProvider(Aggregator[] aggregators)
	{
		next = (aggregators.length>=0)?0:-1;
		this.aggregators = aggregators;
	}

	public Voter nextVoter() {
		if  (!hasNext()){
			return null;
		}
		
		return new Voter(next+1,(float)aggregators[next].score, aggregators[next++].id );
	}

	public boolean hasNext() {
		
		if (aggregators == null || next < 0 || next >= aggregators.length){ 
			return false;
		}else{
			return true;
		}	
	}

	public int size() {
		return aggregators.length;
		
	}

	public Map<String, List<String>> groupVoters() {
		// TODO Auto-generated method stub
		return null;
	}

}
