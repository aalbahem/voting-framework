package voting;

public class Voter {

	public final float score;
	public  final int rank;
	public final String aggregatorID;
	
	public Voter(int rank, float score, String aggregatorID)
	{
		this.aggregatorID = aggregatorID;
		this.rank = rank;
		this.score = score;
	}
	

}
