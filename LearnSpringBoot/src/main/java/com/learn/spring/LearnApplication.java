package com.learn.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.learn.spring", "com.learn.spring4.controller", "com.learn.spring4.dao", "com.learn.spring4.service" })
public class LearnApplication {

//	private static final Logger logger = LoggerFactory.getLogger(LearnApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

	// todo: JPA
//	@Bean
//	public CommandLineRunner setup(CarRepository carD) {
//		return (args) -> {
//			employeeRepository.save(new Employee("Gustavo", "Ponce", true));
//			employeeRepository.save(new Employee("John", "Smith", true));
//			employeeRepository.save(new Employee("Jim ", "Morrison", false));
//			employeeRepository.save(new Employee("David", "Gilmour", true));
//			logger.info("The sample data has been generated");
//		};
//	}
	
//	@Repository("employeeRepository")
//	public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//	}

}
