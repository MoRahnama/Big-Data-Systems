

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/*
 * @Author: Mohammadali Rahnama - 3709515
 */

public class PopulationReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override

	public void reduce(Text key, Iterable<IntWritable> values,  Context context)  throws IOException, InterruptedException {
		
		int maxValue = Integer.MIN_VALUE;

		for (IntWritable value : values) {

			maxValue = Math.max(maxValue, value.get());

		}
		context.write(key, new IntWritable(maxValue));
		
		int minValue = Integer.MAX_VALUE;

		for (IntWritable value : values) {

			minValue = Math.min(minValue, value.get());

		}
		context.write(key, new IntWritable(minValue));

	}

}