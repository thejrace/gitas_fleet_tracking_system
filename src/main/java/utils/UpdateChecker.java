package utils;

import interfaces.ActionCallback;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class UpdateChecker {

    public static boolean action(){
        // check updates
        JSONObject updateRequest = new JSONObject( APIRequest.GET(SharedConfig.DATA.getJSONArray("base_api").getString(0)+"fts/version") );
        return !updateRequest.getString("last_version").equals(SharedConfig.VERSION);
    }

    public static void download(){

        if( Common.checkFile(   SharedConfig.DATA.getString("installDir") + "GFTS.exe" ) ){
            System.out.println("Yedek alınıyor..");
            Common.copyFile( new File( SharedConfig.DATA.getString("installDir") + "GFTS.exe"), new File( SharedConfig.DATA.getString("installDir") + "GFTS_old.exe" ) );
        }

        FileDownload.downloadFileFromUrl(SharedConfig.DATA.getString("download_url"), SharedConfig.DATA.getString("installDir") + "GFTS_new.exe", new ActionCallback() {
            @Override
            public void onSuccess(String... params) {
                System.out.println("Yeni versiyon indirildi! Yedekler temizleniyor..");
                if( !Common.deleteFile( SharedConfig.DATA.getString("installDir") + "GFTS_old.exe") ) System.out.println("Yedekler temizlenemedi..");
            }
            @Override
            public void onError(int type) {
                System.out.println("Yeni versiyon indirilirken bir HATA oluştu!!");
            }
        });

    }

}
