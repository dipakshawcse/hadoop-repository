package com.bdp.mapreduce.duplicaterecord.driver;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.bdp.mapreduce.duplicaterecord.mapper.DuplicateRecordMapper;
import com.bdp.mapreduce.duplicaterecord.reducer.DuplicateRecordReducer;

public class DuplicateRecordDriver
			extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int jobStatus = ToolRunner.run(new DuplicateRecordDriver(), args);
		System.out.println("job Status=="+ jobStatus);
	}

	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Job job = new Job(getConf(), "Duplicate Record");
		job.setJarByClass(getClass());
		
		job.setMapperClass(DuplicateRecordMapper.class);
		job.setReducerClass(DuplicateRecordReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		return job.waitForCompletion(true) ? 0 : 1;	
	}
}
