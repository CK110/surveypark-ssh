package surveypark;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestSpringQuartz  extends QuartzJobBean{

	  /*ҵ��ʵ��*/
    public void work() {
        System.out.println("ִ�е�������"+new Date());
    }


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		 this.work();
		
	}
}
