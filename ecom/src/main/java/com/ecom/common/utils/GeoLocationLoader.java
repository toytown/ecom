package com.ecom.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class GeoLocationLoader {

    private static final String DB_NAME_KEY = "dbname";
    private static final String GEO_LOC_COLLECTION = "geoLocation";

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./misc/geodb/GeoPC_DE.csv"))));
            String line = null;

            Mongo m = new Mongo("localhost");
            Properties props = new Properties();
            props.load(ClassLoader.getSystemResourceAsStream("ecom.properties"));

            if (props.getProperty(DB_NAME_KEY) == null) {
                throw new IllegalArgumentException("No such property key exists in ecom.properties file " + DB_NAME_KEY);
            }

            DB db = m.getDB(props.getProperty(DB_NAME_KEY));
            DBCollection coll = db.getCollection(GEO_LOC_COLLECTION);
            coll.drop();
            
            int counter = 0;
            boolean skipFirst = false;
                    
            while ((line = reader.readLine()) != null) {

                if (!skipFirst) {
                    skipFirst = true;
                    continue;
                }
                if (line.trim().length() < 1) {
                    continue;
                }
                String[] val = line.split(";");

                BasicDBObject geoLocation = new BasicDBObject();

                geoLocation.put("country", cleanupQuotes(val[0]));
                geoLocation.put("language", cleanupQuotes(val[1]));
                geoLocation.put("id", cleanupQuotes(val[2]));
                geoLocation.put("iso2", cleanupQuotes(val[3]));
                geoLocation.put("region1", cleanupQuotes(val[4]));
                geoLocation.put("region2", cleanupQuotes(val[5]));
                geoLocation.put("region3", cleanupQuotes(val[6]));
                geoLocation.put("region4", cleanupQuotes(val[7]));
                geoLocation.put("zip", cleanupQuotes(val[8]));
                geoLocation.put("city", cleanupQuotes(val[9]));
                geoLocation.put("area1", cleanupQuotes(val[10]));
                geoLocation.put("area2", cleanupQuotes(val[11]));
                geoLocation.put("lat", cleanupQuotes(val[12]));
                geoLocation.put("lng", cleanupQuotes(val[13]));
                geoLocation.put("timezone", cleanupQuotes(val[14]));

                coll.insert(geoLocation);
                counter++;
            }

            System.out.println("Total records loaded into the database = " + counter);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    private static String cleanupQuotes(String token) {
        StringBuffer buf = new StringBuffer();
        int length = token.length();
        int curIndex = 0;

        if (token.startsWith("\"") && token.endsWith("\"")) {
            curIndex = 1;
            length--;
        }

        boolean oneQuoteFound = false;
        boolean twoQuotesFound = false;

        while (curIndex < length) {
            char curChar = token.charAt(curIndex);
            if (curChar == '"') {
                twoQuotesFound = (oneQuoteFound) ? true : false;
                oneQuoteFound = true;
            } else {
                oneQuoteFound = false;
                twoQuotesFound = false;
            }

            if (twoQuotesFound) {
                twoQuotesFound = false;
                oneQuoteFound = false;
                curIndex++;
                continue;
            }

            buf.append(curChar);
            curIndex++;
        }

        return buf.toString();
    }    

}
