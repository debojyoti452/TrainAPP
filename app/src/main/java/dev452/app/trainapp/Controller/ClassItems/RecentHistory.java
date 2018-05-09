package dev452.app.trainapp.Controller.ClassItems;

public class RecentHistory {
    private String recentSrc, recentDest;
public RecentHistory(){

}
    public RecentHistory(String recentSrc, String recentDest) {
        this.recentSrc = recentSrc;
        this.recentDest = recentDest;
    }

    public String getRecentSrc() {
        return recentSrc;
    }

    public void setRecentSrc(String recentSrc) {
        this.recentSrc = recentSrc;
    }

    public String getRecentDest() {
        return recentDest;
    }

    public void setRecentDest(String recentDest) {
        this.recentDest = recentDest;
    }
    
}
