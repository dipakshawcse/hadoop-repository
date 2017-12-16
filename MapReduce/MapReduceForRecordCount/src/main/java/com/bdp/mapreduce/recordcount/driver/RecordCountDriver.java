package com.bdp.mapreduce.recordcount.driver;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.bdp.mapreduce.recordcount.mapper.RecordCountMapper;
import com.bdp.mapreduce.recordcount.reducer.RecordCountReducer;

public class RecordCountDriver 
				extends Configured implements Tool{

	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Job job = new Job(getConf(), "Record Count");
		job.setJarByClass(getClass());
		
		job.setMapperClass(RecordCountMapper.class);
		job.setReducerClass(RecordCountReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		return job.waitForCompletion(true) ? 0 : 1;	
	}
	
	public static void main(String[] args) throws Exception {
		int jobStatus = ToolRunner.run(new RecordCountDriver(), args);
		System.out.println(jobStatus);
	}
}
