package dev.akuniutka.skillfactory.lms.util;

import dev.akuniutka.skillfactory.lms.model.LmsData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlUtil {
    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH_mm_ss.SSSZ";
    private XmlUtil() {}

    public static void marshal(LmsData lmsData) throws FileNotFoundException {
        File file = new File("./xmlReqs");
        if (!file.exists() && !file.mkdir()) {
            throw new FileNotFoundException();
        }
        Date date = new Date();
        lmsData.setProcessedAt(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        file = new File("./xmlReqs/req " + dateFormat.format(date) + ".xml");
        try {
            JAXBContext context = JAXBContext.newInstance(LmsData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(lmsData, file);
        } catch (JAXBException e) {
            throw new RuntimeException();
        }
//        try (PrintWriter out = new PrintWriter(file)) {
//            out.println("test");
//            try {
//                JAXBContext context = JAXBContext.newInstance(LmsData.class);
//                Marshaller marshaller = context.createMarshaller();
//                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//                marshaller.marshal(lmsData, out);
//            } catch (JAXBException e) {
//                throw new RuntimeException();
//            }
//        }

    }
}
