/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    public static boolean checkFile( String path ){
        File f = new File( path );
        return f.exists();
    }

    public static String readJSONFile( String src ){
        try {
            FileReader fr = new FileReader( src);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while( line != null ){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            br.close();
            fr.close();
            return sb.toString();
        } catch( IOException e ){
            e.printStackTrace();
            return null;
        }
    }

    public static void copyFile(File source, File dest) {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch( Exception e ) {
            e.printStackTrace();
        }
        try {
            sourceChannel.close();
            destChannel.close();
        } catch( NullPointerException | IOException e ){

        }
    }

    public static boolean writeStaticData( String filePath, String content ){
        try{
            PrintWriter writer = new PrintWriter( filePath, "UTF-8");
            writer.print(content);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteFile( String path ){
        try{
            File f = new File( path );
            return (f.exists() && f.delete() );
        } catch( Exception e ){
            e.printStackTrace();
        }
        return false;
    }

    public static String regexTrim( String str ){
        return str.replaceAll("\u00A0", "");
    }

    /**
     * Return a current date time string
     *
     * @return string datetime
     */
    public static String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentHMin() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);

    }

    /**
     * Fetch stop name from string with no ( ex: 15-Beykoz -> Beykoz )
     *
     * @param stop stop name with no
     *
     * @return only stop name
     */
    public static String fetchStopName( String stop ){
        try {
            return stop.substring(stop.indexOf('-')+1, stop.indexOf(" ("));
        } catch( StringIndexOutOfBoundsException e ){

        }
        return "N/A";
    }
}
