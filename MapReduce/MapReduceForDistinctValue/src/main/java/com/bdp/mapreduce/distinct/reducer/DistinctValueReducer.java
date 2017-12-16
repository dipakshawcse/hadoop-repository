package com.bdp.mapreduce.distinct.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DistinctValueReducer 
				extends Reducer<Text, IntWritable, Text, NullWritable>{
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, 
		Reducer<Text, IntWritable, Text, NullWritable>.Context context)
		throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int sum = 0;
		for (IntWritable value : values) {
			sum += value.get();
			if (sum == 1)
				context.write(key, NullWritable.get());
		}
	}
}


