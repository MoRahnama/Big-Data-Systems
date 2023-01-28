
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * @Author: Mohammadali Rahnama - 3709515
 */

public class PopulationMapper extends  Mapper<LongWritable, Text, Text, IntWritable> {

	
	@Override
	
	 public void map(LongWritable key, Text value, Context context)  throws IOException, InterruptedException {
	
		
		String line = value.toString();
		String City = "null" ;
		String Country = "null" ;
		
		int Population = 0;
		int Population_start = 0;
		int Population_end = 0;
		int Country_start = 0;
		int Country_end = 0;
		int count = 0;
		
		for (int x=0;x<line.length();x++){
			if (line.charAt(x) == '\"'){count++;}
			if (count == 2){if(x-1!=1){City = line.substring(1,x-1);}}
			if (count == 8){Country_start = x+2;}
			if (count == 9){Country_end = x+1;}
			if (count == 18){Population_start = x+2;}
			if (count == 19){Population_end = x+1;}
		}
		
		Country = line.substring(Country_start,Country_end);
		
		if (Population_start != Population_end){
		Population = (int) Double.parseDouble(line.substring(Population_start, Population_end));};
		
		if(City != "null" && Population != 0){
		//	System.out.println(Country + ", " + City + ": " + Population);
			context.write(new Text(Country), new IntWritable(Population));
		}
	
	}
}