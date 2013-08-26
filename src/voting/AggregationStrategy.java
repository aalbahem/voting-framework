package voting;


import java.io.IOException;
import java.util.Map;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;

public interface AggregationStrategy {
	public abstract Map<String, Double> aggregate(VoterProvider provider) ;
}
