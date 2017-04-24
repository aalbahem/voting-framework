package helpers;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Created by e29296 on 24/04/2017.
 */
public class StatsHelper {

    public static double median(double[] values){
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(values);

        return descriptiveStatistics.getPercentile(50);
    }
}
