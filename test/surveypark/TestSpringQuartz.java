package surveypark;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestSpringQuartz  extends QuartzJobBean{

	  /*业务实现*/
    public void work() {
        System.out.println("执行调度任务："+new Date());
    }


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		 this.work();
		
	}
}
