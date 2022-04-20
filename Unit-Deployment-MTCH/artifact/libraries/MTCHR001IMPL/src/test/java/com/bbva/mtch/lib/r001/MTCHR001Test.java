package com.bbva.mtch.lib.r001;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/MTCHR001-app.xml",
		"classpath:/META-INF/spring/MTCHR001-app-test.xml",
		"classpath:/META-INF/spring/MTCHR001-arc.xml",
		"classpath:/META-INF/spring/MTCHR001-arc-test.xml" })
public class MTCHR001Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(MTCHR001Test.class);

	@Spy
	private Context context;

	@Resource(name = "mtchR001")
	private MTCHR001 mtchR001;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.mtchR001;
		if(this.mtchR001 instanceof Advised){
			Advised advised = (Advised) this.mtchR001;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		LOGGER.info("Executing the test...");
		//mtchR001.execute();
		Assert.assertEquals(0, context.getAdviceList().size());
	}
	
}
