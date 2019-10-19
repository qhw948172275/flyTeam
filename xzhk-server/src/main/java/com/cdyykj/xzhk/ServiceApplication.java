package com.cdyykj.xzhk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 电大助手项目
* @author chenbiao
* @date 2018年7月28日 下午3:57:19 
* 
* 开启任务调度 @EnableScheduling
*
 */
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages={"com.cdyykj"})
@MapperScan(basePackages= {"com.cdyykj.xzhk.dao","com.cdyykj.system.dao"})
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args); 
	}
}
