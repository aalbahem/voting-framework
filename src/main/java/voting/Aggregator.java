package voting;


/**
 * @author ameertawfik
 *
 */
public class Aggregator implements Comparable<Aggregator> {
	
	public final String id;
	public double score;
	
	public int compareTo(Aggregator o) {
		return Double.compare(score, o.score);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aggregator other = (Aggregator) obj;
		if (Double.doubleToLongBits(score) != Double
				.doubleToLongBits(other.score))
			return false;
		return true;
	}
	
	
	
	public Aggregator(String id, double score) {
		this.score = score;
		this.id = id;
	}
}
