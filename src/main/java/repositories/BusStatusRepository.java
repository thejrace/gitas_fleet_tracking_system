/*
 *  Gitas Fleet Tracking System 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
package repositories;

import controllers.ControllerHub;
import enums.AlarmType;
import enums.BusRunStatus;
import models.Alarm;
import models.Bus;
import models.BusRun;
import utils.Common;
import utils.RunTimeDiff;
import utils.ThreadHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class BusStatusRepository {

    /**
     * Code of the bus
     */
    private String code;

    /**
     * Status output of the bus
     */
    private String status;

    /**
     * Status in details
     */
    private String statusLabel;

    /**
     * Additional status data line
     */
    private String subStatusLabel;

    /**
     * Constructor
     *
     * @param code
     */
    public BusStatusRepository(String code){
        this.code = code;
    }

    /**
     * Process bus data to output status and alarms
     *
     * @param bus
     */
    public void processRunData(Bus bus, Map<String, Integer> runStatusSummary, int activeRunIndex ){

        if( runStatusSummary.get(BusRunStatus.B).equals(runStatusSummary.get("TOTAL"))){
            // all waiting

            statusLabel = "Seferini bekliyor.";
            subStatusLabel = "İlk sefer: " + bus.getRunData().get(0).getORER();
            status = BusRunStatus.B;

        } else if( runStatusSummary.get(BusRunStatus.T).equals(runStatusSummary.get("TOTAL"))){
            // 100% finished successfully

            statusLabel = "Günü tamamladı.";
            subStatusLabel = "Sefer yüzdesi: 100%";
            status = BusRunStatus.T;

        } else if( activeRunIndex == -1 ){
            // all finished or all zayi
            if( runStatusSummary.get(BusRunStatus.I) + runStatusSummary.get(BusRunStatus.Y) == runStatusSummary.get("TOTAL") ){
                // all runs are zayi
                statusLabel = "Sefer iptal.";
                subStatusLabel = "Durum Kodu: " + bus.getRunData().get(0).getStatusCode();
                status = BusRunStatus.I;
                addAlarm(new Alarm(AlarmType.RED, code, "Sefer iptalleri var!"));
            } else {
                // finished the day but there are zayi runs
                statusLabel = "Günü tamamladı.";
                subStatusLabel = "Sefer yüzdesi: " + ( runStatusSummary.get(BusRunStatus.T) / runStatusSummary.get("TOTAL") ) * 100;
                status = BusRunStatus.T;
                addAlarm(new Alarm(AlarmType.RED, code, "Sefer iptalleri var!"));
            }

        } else {
            // we'll loop through runs

            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            String NOW = dateFormat.format(date);

            ArrayList<BusRun> runData = bus.getRunData();
            for( int a = 0; a < runData.size(); a++ ){

                // active status and labels are updated accordingly activeRunIndex
                if( a == activeRunIndex ){
                    String runStatus = runData.get(a).getStatus();

                    if( runStatus.equals(BusRunStatus.A) ){
                        statusLabel = "Aktif sefer ( "+runData.get(a).getORER()+" )";
                        subStatusLabel = Common.fetchStopName(runData.get(a).getCurrentStop());
                        status = BusRunStatus.A;
                    } else {
                        // waiting
                        statusLabel = "Seferini bekliyor.";
                        subStatusLabel = "Bir sonraki sefer: "+runData.get(a).getORER()+"";
                        status = BusRunStatus.B;
                    }
                }

                // below checks are all for alarms
                if( runData.get(a).getStatus().equals(BusRunStatus.A) ){
                    // if current run is active, we check if bus has enough time to catch next run
                    try {
                        if( RunTimeDiff.calculate( runData.get(a).getEstimatedEndTime(), runData.get(a+1).getORER() ) < 0 ){
                            // its gonna be late ALARM
                            System.out.println(code + " active but its late");
                            addAlarm(new Alarm(AlarmType.BLUE, code, "Bir sonraki sefere geç kaldı!"));
                        }

                        // check if it has previously zayi runs in order to show 'runs fixed alarm'
                        if( runStatusSummary.get(BusRunStatus.I) > 0 || runStatusSummary.get(BusRunStatus.Y) > 0 ){
                            System.out.println(code + " runs fixed!");
                            addAlarm(new Alarm(AlarmType.GREEN, code, "Zayi seferler düzeltildi!"));
                        }

                    } catch( IndexOutOfBoundsException e ){

                    }
                }

                if( runData.get(a).getStatus().equals(BusRunStatus.B) ){
                    // if current run is waiting, we check if it's late or not
                    try {
                        if( RunTimeDiff.calculate(NOW, runData.get(a).getORER()) < 0 ){
                            // it's late
                            System.out.println(code + " waiting and its late");
                            addAlarm(new Alarm(AlarmType.BLUE, code, "Vakti gelen seferine başlamadı!"));
                        }

                        // check if it has previously zayi runs in order to show 'runs fixed alarm'
                        if( runStatusSummary.get(BusRunStatus.I) > 0 || runStatusSummary.get(BusRunStatus.Y) > 0 ){

                            System.out.println(code + " runs fixed!");
                            addAlarm(new Alarm(AlarmType.GREEN, code, "Zayi seferler düzeltildi!"));
                        }

                    } catch( IndexOutOfBoundsException e ){

                    }

                }

                if( runData.get(a).getStatus().equals(BusRunStatus.I)  ){

                    addAlarm(new Alarm(AlarmType.RED, code, "Sefer iptalleri var!"));

                    System.out.println(code + " has zayi run.");

                }

                if( runData.get(a).getStatus().equals(BusRunStatus.Y)  ){

                    addAlarm(new Alarm(AlarmType.RED, code, "Yarım kalan seferler var!"));

                    System.out.println(code + " has zayi half run.");

                }

            }

        }

    }

    private void addAlarm( Alarm alarmItem ){
        ThreadHelper.runOnUIThread(() -> {
            ControllerHub.AlarmController.addAlarm(alarmItem);
        });
    }

    /**
     * Getter for status
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Getter for statusLabel
     *
     * @return
     */
    public String getStatusLabel() {
        return statusLabel;
    }

    /**
     * Getter for subStatusLabel
     *
     * @return
     */
    public String getSubStatusLabel() {
        return subStatusLabel;
    }
}
