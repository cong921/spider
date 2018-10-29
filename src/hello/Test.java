package hello;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class Test {
	public void go() throws Exception {
		// 首先，必需要取得一个Scheduler的引用
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		// jobs可以在scheduled的sched.start()方法前被调用

		// job 1将每隔20秒执行一次
		JobDetail job = newJob(BeijingPageProcessor.class).withIdentity("job1", "group1").build();
		CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(cronSchedule("0 0 3 1/5 * ? ")).build();
		Date ft = sched.scheduleJob(job, trigger);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(
				job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

		// job 2将每2分钟执行一次（在该分钟的第15秒)
		/*job = newJob(AskAndAnswerProcessor.class).withIdentity("job2", "group1").build();
		trigger = newTrigger().withIdentity("trigger2", "group1").withSchedule(cronSchedule("0 0/2 * * * ?")).build();
		ft = sched.scheduleJob(job, trigger);
		System.out.println(
				job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());*/

		// 开始执行，start()方法被调用后，计时器就开始工作，计时调度中允许放入N个Job
		sched.start();
		try {
			// 主线程等待100年
			Thread.sleep(100*365*24*60*60L * 1000L);
			/*while(true) {
				
			}*/
		} catch (Exception e) {
		}
		// 关闭定时调度，定时器不再工作
		sched.shutdown(true);
	}

	public static void main(String[] args) throws Exception {
		Test test = new Test();
		test.go();
	}

}
