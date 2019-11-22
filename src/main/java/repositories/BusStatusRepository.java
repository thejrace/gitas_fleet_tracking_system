package repositories;

import models.Bus;
import models.BusRun;
import utils.RunTimeDiff;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class BusStatusRepository {

    private String code;

    private String status;

    private String statusLabel;

    private String subStatusLabel;

    public BusStatusRepository(String code){
        this.code = code;
    }

    /**
     * Process bus data to output status and alarms
     *
     * @param bus
     */
    public void processRunData(Bus bus, Map<String, Integer> runStatusSummary, int activeRunIndex ){

        if( runStatusSummary.get("B").equals(runStatusSummary.get("TOTAL"))){
            // all waiting

            statusLabel = "Seferini bekliyor.";
            subStatusLabel = "İlk sefer: " + bus.getRunData().get(0).getORER();

        } else if( runStatusSummary.get("T").equals(runStatusSummary.get("TOTAL"))){
            // 100% finished successfully

            statusLabel = "Günü tamamladı.";
            subStatusLabel = "Sefer yüzdesi: 100%";

        } else if( activeRunIndex == -1 ){
            // all finished or all zayi


        } else {
            // we'll loop through runs

            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            String NOW = dateFormat.format(date);

            ArrayList<BusRun> runData = bus.getRunData();
            for( int a = 0; a < runData.size(); a++ ){

                // active status and labels are updated accordingly activeRunIndex
                if( a == activeRunIndex ){
                    status = runData.get(a).getStatus();

                    if( status.equals("A") ){
                        statusLabel = "Aktif sefer ( "+runData.get(a).getORER()+" )";
                        subStatusLabel = runData.get(a).getCurrentStop();
                    } else {
                        // waiting
                        statusLabel = "Seferini bekliyor.";
                        subStatusLabel = "Bir sonraki sefer: "+runData.get(a).getORER()+"";
                    }
                }

                // below checks are all for alarms
                if( runData.get(a).getStatus().equals("A") ){
                    // if current run is active, we check if bus has enough time to catch next run
                    try {
                        if( RunTimeDiff.calculate( runData.get(a).getEstimatedEndTime(), runData.get(a+1).getORER() ) < 0 ){
                            // its gonna be late ALARM
                            System.out.println(code + " active but its late");

                        }
                    } catch( ArrayIndexOutOfBoundsException e ){

                    }

                }

                if( runData.get(a).getStatus().equals("B") ){
                    // if current run is waiting, we check if it's late or not
                    try {

                        if( RunTimeDiff.calculate(NOW, runData.get(a).getORER()) < 0 ){
                            // it's late
                            System.out.println(code + " waiting and its late");
                        }

                        // check if it has previously zayi runs in order to show 'runs fixed alarm'

                    } catch( ArrayIndexOutOfBoundsException e ){

                    }

                }

                if( runData.get(a).getStatus().equals("I")  ){

                    System.out.println(code + " has zayi run.");

                }

                if( runData.get(a).getStatus().equals("Y")  ){

                    System.out.println(code + " has zayi half run.");

                }

            }

        }

    }

    public String getStatus() {
        return status;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public String getSubStatusLabel() {
        return subStatusLabel;
    }
}
