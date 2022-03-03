package sg.edu.nus.iss.app.addressWorkshop13;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sg.edu.nus.iss.app.addressWorkshop13.controller.AddressBookController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AddressWorkshop13ApplicationTests {

	@Autowired
	private AddressBookController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
