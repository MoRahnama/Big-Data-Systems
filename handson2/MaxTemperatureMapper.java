
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * @Author: S. Ray for demo
 */

public class MaxTemperatureMapper extends  Mapper<LongWritable, Text, Text, IntWritable> {

	private static final int MISSING = 9999;
	
	@Override
	
	 public void map(LongWritable key, Text value, Context context)  throws IOException, InterruptedException {
	
		String line = value.toString();
		String City = "";
		int Population = 0;
		int Population_start = 0;
		int Population_end = 0;
		int x = 1;
		
		while (line.charAt(x) != '\"'){
			City += line.charAt(x);
			x++;
		}
		
		int count = 0;
		while (count < 20){
			if (line.charAt(x) == '\"'){count++;}
			if (count == 17){Population_start = x+2;}
			if (count == 19){Population_end = x-1;}
			x++;
		}
		
		Population = Integer.parseInt(line.substring(Population_start, Population_end));
		context.write(new Text(City), new IntWritable(Population));
	
	}
}