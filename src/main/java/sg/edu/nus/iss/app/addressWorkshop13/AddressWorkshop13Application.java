package sg.edu.nus.iss.app.addressWorkshop13;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sg.edu.nus.iss.app.addressWorkshop13.util.IOUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class AddressWorkshop13Application {

	private static final Logger logger= Logger.getLogger(AddressWorkshop13Application.class.getName());
	private static final String DATA_DIR = "dataDir";

	public static void main(String[] args) {

		//SpringApplication.run(AddressWorkshop13Application.class, args);
		SpringApplication app =new SpringApplication(AddressWorkshop13Application.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> optVals =appArgs.getOptionValues(DATA_DIR);
		logger.info("optVals >" + optVals);

		if(optVals!=null) {
			IOUtil.createDirectory((String)optVals.get(0));
		}
		else {
			// if cannot find any dataDir argument from the program
			// args then abort the program
			logger.log(Level.WARNING,"Data directory is not specified.");
			System.exit(1);
		}

		app.run(args);
	}

}
