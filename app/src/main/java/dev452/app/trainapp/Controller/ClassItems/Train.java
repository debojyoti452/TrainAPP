package dev452.app.trainapp.Controller.ClassItems;



public class Train {
    private String srcName, destName, TrainName, TrainNumber, sourceName, destinationName;
    private String srcTime, destTime, tDays, tRuns, srTime, detTime;



    public Train() {

    }
    public Train(String SName, String DName, String ATime, String DTime, String TName, String TNumber, String srcTime, String destTime, String days, String runs, String nameSource, String nameDestination) {
        this.srcName = SName;
        this.destName = DName;
        this.srcTime = ATime;
        this.destTime = DTime;
        this.TrainName =TName;
        this.TrainNumber = TNumber;
        this.tDays = days;
        this.tRuns = runs;
        this.srTime = srcTime;
        this.detTime = destTime;
        this.sourceName = nameSource;
        this.destinationName = nameDestination;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public String getSrcTime() {
        return srcTime;
    }

    public void setSrcTime(String srcTime) {
        this.srcTime = srcTime;
    }

    public String getDestTime() {
        return destTime;
    }

    public void setDestTime(String destTime) {
        this.destTime = destTime;
    }

    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String trainName) {
        TrainName = trainName;
    }

    public String getTrainNumber() {
        return TrainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        TrainNumber = trainNumber;
    }

    public String gettDays() {
        return tDays;
    }

    public void settDays(String tDays) {
        this.tDays = tDays;
    }

    public String gettRuns() {
        return tRuns;
    }

    public void settRuns(String tRuns) {
        this.tRuns = tRuns;
    }

    public String getSrTime() {
        return srTime;
    }

    public void setSrTime(String srTime) {
        this.srTime = srTime;
    }

    public String getDetTime() {
        return detTime;
    }

    public void setDetTime(String detTime) {
        this.detTime = detTime;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }


}
