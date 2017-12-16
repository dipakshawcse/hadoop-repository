package com.bdp.mapreduce.distinct.mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DistinctValueMapper 
		extends Mapper<LongWritable, Text, Text, IntWritable>{
	private static final IntWritable one = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, 
		Mapper<LongWritable, Text, Text, IntWritable>.Context context)
		throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String[] words = value.toString().split(" ");
		for (String string : words) {
			context.write(new Text(string.replaceAll("[-+.^:,?]","")), one);
		}
	}
}
