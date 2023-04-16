package dev.akuniutka.skillfactory.lms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.akuniutka.skillfactory.lms.io.XlsReader;
import dev.akuniutka.skillfactory.lms.model.LmsData;

import java.util.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtil {
    private static final Logger LOGGER = Logger.getLogger(XlsReader.class.getName());
    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH_mm_ss.SSSZ";

    private JsonUtil() {}

    public static String serialize(Object obj) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(obj);
    }

    public static <T> T deserialize(String json, Type typeOfT) {
        return new Gson().fromJson(json, typeOfT);
    }

    public static void serializeToFIle(LmsData lmsData) {
        LOGGER.info("serializing data to JSON");
        File file = new File("./jsonReqs");
        if (!file.exists() && !file.mkdir()) {
            LOGGER.severe("cannot create directory for JSON files");
            throw new RuntimeException("cannot create directory for JSON files");
        }
        Date date = lmsData.getProcessedAt();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        file = new File("./jsonReqs/req " + dateFormat.format(date) + ".json");
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(serialize(lmsData));
        } catch (FileNotFoundException e) {
            LOGGER.severe("cannot open file to write JSON");
            throw new RuntimeException("cannot open file to write JSON");
        }
        LOGGER.info("data serialized to JSON");
    }
}
