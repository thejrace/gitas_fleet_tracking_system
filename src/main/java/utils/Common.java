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

}
