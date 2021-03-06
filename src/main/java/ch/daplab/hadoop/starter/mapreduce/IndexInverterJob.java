package ch.daplab.hadoop.starter.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class IndexInverterJob extends Configured implements Tool {


    public static class IndexInverterMapper extends
            Mapper<LongWritable, Text, Text, Text> {


    }

    public static class IndexInverterReducer
            extends Reducer<Text, Text, Text, Tool> {


    }


    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf(), "IndexInverterJob");
        job.setJarByClass(IndexInverterJob.class);
        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        out.getFileSystem(getConf()).delete(out, true);
        FileInputFormat.setInputPaths(job, in);
        FileOutputFormat.setOutputPath(job, out);
        job.setMapperClass(IndexInverterMapper.class);
        job.setReducerClass(IndexInverterReducer.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) {
        int result;
        try {
            result = ToolRunner.run(new Configuration(), new IndexInverterJob(), args);
            System.exit(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
