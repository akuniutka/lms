package dev.akuniutka.skillfactory.lms.util;

import dev.akuniutka.skillfactory.lms.io.XlsReader;
import dev.akuniutka.skillfactory.lms.model.LmsData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class XmlUtil {
    private static final Logger LOGGER = Logger.getLogger(XlsReader.class.getName());
    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH_mm_ss.SSSZ";

    private XmlUtil() {
    }

    public static void marshal(LmsData lmsData) {
        LOGGER.info("marshalling data to XML");
        File file = new File("./xmlReqs");
        if (!file.exists() && !file.mkdir()) {
            LOGGER.severe("cannot create directory for XML files");
            throw new RuntimeException("cannot create directory for XML files");
        }
        Date date = lmsData.getProcessedAt();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        file = new File("./xmlReqs/req " + dateFormat.format(date) + ".xml");
        try {
            JAXBContext context = JAXBContext.newInstance(LmsData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(lmsData, file);
        } catch (JAXBException e) {
            LOGGER.severe("error writing data to XML");
            throw new RuntimeException("error writing data to XML");
        }
        LOGGER.info("data marshalled to XML");
    }
}
