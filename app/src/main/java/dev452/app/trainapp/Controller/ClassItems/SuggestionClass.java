package dev452.app.trainapp.Controller.ClassItems;

import java.util.ArrayList;

public class SuggestionClass {
    private String sourceSuggestion;
    private String codeSuggestion;

    public SuggestionClass(){

    }

    public SuggestionClass(String srcSuggest, String codeSuggestion){
        this.sourceSuggestion = srcSuggest;
        this.codeSuggestion = codeSuggestion;
    }


    public String getSourceSuggestion() {
        return sourceSuggestion;
    }

    public void setSourceSuggestion(String sourceSuggestion) {
        this.sourceSuggestion = sourceSuggestion;
    }

    public String getCodeSuggestion() {
        return codeSuggestion;
    }

    public void setCodeSuggestion(String codeSuggestion) {
        this.codeSuggestion = codeSuggestion;
    }


}
