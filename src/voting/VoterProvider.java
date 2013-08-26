package voting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public interface VoterProvider {

	public Voter nextVoter();
	public Map<String,List<String>> groupVoters() throws Exception;
	public boolean hasNext();
	public int size();
}
