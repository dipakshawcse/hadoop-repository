package com.bdp.mapreduce.recordcount.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RecordCountReducer 
				extends Reducer<Text, IntWritable, NullWritable, IntWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, NullWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int recordCount = 0;
		for (IntWritable value : values) {
			recordCount += value.get();
		}
		context.write( NullWritable.get(), new IntWritable(recordCount));
	}
}
