package dev452.app.trainapp.Controller.ClassItems;

public class TrainStation {
    private String stationName;
    private String stationCode;
    private String mainCode;
    private String srcNameMarker;
    public TrainStation(){

    }
    public TrainStation(String stnName, String stnCode, String mCode, String sName){
        this.stationCode = stnCode;
        this.stationName = stnName;
        this.mainCode = mCode;
        this.srcNameMarker = sName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getSrcNameMarker() {
        return srcNameMarker;
    }

    public void setSrcNameMarker(String srcNameMarker) {
        this.srcNameMarker = srcNameMarker;
    }
}