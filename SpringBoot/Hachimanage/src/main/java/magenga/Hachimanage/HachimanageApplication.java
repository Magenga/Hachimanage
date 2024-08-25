package magenga.Hachimanage;

import magenga.Hachimanage.systemMethod.SystemLogging;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HachimanageApplication {


    public static void main(String[] args) {SpringApplication.run(HachimanageApplication.class, args);}

	//系統紀錄class實體化
	private final SystemLogging systemLogging = new SystemLogging();

	@Bean
	public CommandLineRunner commandLineRunner ()  {

		return runner -> {
			systemLogging.startedTimes();
			System.out.println("Backend start, if any thing important, write here.");
		};

	}

}
