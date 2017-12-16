package com.bdp.mapreduce.duplicaterecord.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.google.common.collect.Iterables;

/*
 * Here getting input from mapper in the format key, value
 * Key is of type Text
 * Value is of type IntWritable
 * Reducer will give only that records which are duplicate.
 */
public class DuplicateRecordReducer 
			extends Reducer<Text, IntWritable, Text, NullWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		if (Iterables.size(values) > 1)
			context.write(key, NullWritable.get()); //Here I want only record
	}
}
