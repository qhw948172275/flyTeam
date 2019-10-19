package com.cdyykj.commons.task.config;

import com.cdyykj.commons.task.JobFactory;
import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by haoxy on 2018/9/28. E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Configuration
public class SchedulerConfig {

	/**
	 * 演示环境
	 */
	public static final String PREVIEW_PROFILE = "preview";
	/**
	 * 开发环境
	 */
	public static final String DEV_PROFILE = "dev";
	/**
	 * 测试环境
	 */
	public static final String TEST_PROFILE = "test";
	/**
	 * 生产环境
	 */
	public static final String PROD_PROFILE = "prod";

	@Autowired
	private ApplicationContext context;

	/**
	 * 获取当前应用运行环境
	 * 
	 * @author chenbiao
	 * @date 2019年6月6日 下午5:08:48 参数说明
	 * 
	 * @return
	 */
	public String getActiveProfile() {
		return context.getEnvironment().getActiveProfiles()[0];
	}

	@Bean(name = "SchedulerFactory")
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(getJobFactory());
		factory.setQuartzProperties(quartzProperties());
		return factory;
	}

	@Bean
	public JobFactory getJobFactory() {
		return new JobFactory();
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz/"+getActiveProfile()+".properties"));
		// 在quartz.properties中的属性被读取并注入后再初始化对象
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	/**
	 * quartz初始化监听器 这个监听器可以监听到工程的启动，在工程停止再启动时可以让已有的定时任务继续进行。
	 * 
	 * @return
	 */
	@Bean
	public QuartzInitializerListener executorListener() {
		return new QuartzInitializerListener();
	}

	/**
	 *
	 * 通过SchedulerFactoryBean获取Scheduler的实例
	 */
	@Bean(name = "Scheduler")
	public Scheduler scheduler() throws IOException {
		return schedulerFactoryBean().getScheduler();
	}

}
