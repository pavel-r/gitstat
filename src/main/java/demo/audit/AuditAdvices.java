package demo.audit;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

@Component @Aspect
public class AuditAdvices {

	@Autowired
	private GaugeService gaugeService; 

	@Around("@annotation(auditExecTime)")
	public Object doAuditExecTime(ProceedingJoinPoint pjp, AuditExecTime auditExecTime) throws Throwable{
		Instant before = Instant.now();
		Object retValue = pjp.proceed();
		Instant after = Instant.now();
//		System.out.printf("Metric %s reports %s", auditExecTime.metric(), Duration.between(before, after).toMillis());
		gaugeService.submit(auditExecTime.metric(), Duration.between(before, after).toMillis());
		return retValue;
	}
}
