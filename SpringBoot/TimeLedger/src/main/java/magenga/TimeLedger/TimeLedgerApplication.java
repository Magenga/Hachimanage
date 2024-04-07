package magenga.TimeLedger;

import magenga.TimeLedger.systemMethod.SystemLogging;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TimeLedgerApplication {


    public static void main(String[] args) {SpringApplication.run(TimeLedgerApplication.class, args);}

	//系統紀錄class實體化
	private final SystemLogging systemLogging = new SystemLogging();
	//可以放系統參數
//    private final int systemStartedCounter = systemLogging.readCounterFile();//後台啟動計數，可以記錄其他相關系統參數等～

	@Bean
	public CommandLineRunner commandLineRunner ()  {

		return runner -> {
			systemLogging.startedTimes();
			System.out.println("Backend start, if any thing important, write here.");
		};

	}

}
