package sg.edu.nus.iss.app.addressWorkshop13.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;
import sg.edu.nus.iss.app.addressWorkshop13.model.Contact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contacts {

    private Logger logger = Logger.getLogger(Contacts.class.getName());

    public void saveContact(Contact contact, Model model, ApplicationArguments applnArgs) {
        String dataFileName = contact.getId();
        Set<String> optNames = applnArgs.getOptionNames();
        String [] optNamesArr = optNames.toArray(new String [optNames.size()]);
        List<String> optValues = applnArgs.getOptionValues(optNamesArr[0]);
        String [] optValuesArr = optValues.toArray(new String[optValues.size()]);

        PrintWriter printWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(optValuesArr[0]+ "/" + dataFileName,Charset.forName("UTF-8"));
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(contact.getName());
            printWriter.println(contact.getEmail());
            printWriter.println(contact.getPhoneNumber());
        }
        catch (IOException e) {
            logger.log(Level.WARNING,e.getMessage());
        }
        finally {
            //clean back the resources so that it doesnt not consume all the java memory
            printWriter.close();
            try {
                fileWriter.close();
            }
            catch (IOException e) {
                logger.log(Level.WARNING,e.getMessage());
            }
        }
        model.addAttribute("contact",
                new Contact(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhoneNumber()));
    }

    public void getContactById(Model model, String contactId, ApplicationArguments applnArgs) {
        Set<String> optNames = applnArgs.getOptionNames();
        String [] optNamesArr = optNames.toArray(new String [optNames.size()]);
        List<String> optValues = applnArgs.getOptionValues(optNamesArr[0]);
        String [] optValuesArr = optValues.toArray(new String[optValues.size()]);

        Contact cResp = new Contact();
        try {
            Path filePath = new File(optValuesArr[0] + "/" + contactId).toPath();
            logger.log(Level.INFO, "create filePath >" +optValuesArr[0] + "/" + contactId);
            logger.log(Level.INFO,"optValues > " + optValuesArr[0]);
            Charset charset = Charset.forName("UTF-8");
            logger.log(Level.INFO,"filePath >" +filePath);
            List<String> stringList = Files.readAllLines(filePath,charset);
            cResp.setName(stringList.get(0));
            cResp.setEmail(stringList.get(1));
            cResp.setPhoneNumber(stringList.get(2));
            cResp.setId(contactId);
        }
        catch (IOException e) {
            logger.log(Level.WARNING,e.getMessage());
        }

        model.addAttribute("contact",cResp);
    }
}
