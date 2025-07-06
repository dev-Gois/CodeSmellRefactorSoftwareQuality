package org.example.studysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private final List<String> searchHistory = new ArrayList<>();
    private final Map<String, Integer> searchCount = new HashMap<>();
    private boolean isLocked;
    private int numUsages;
    private String logName;

    public SearchLog(String logName) {
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    // Encapsulated field - instead of exposing the entire list for direct replacement,
    // provide controlled methods to add and retrieve a copy.
    public void addSearchHistory(String search) {
        if (!isLocked) {
            this.searchHistory.add(search);
        }
    }

    public List<String> getSearchHistory() {
        // Return an unmodifiable copy to prevent external modification
        return Collections.unmodifiableList(new ArrayList<>(this.searchHistory));
    }

    // Do not allow replacing the entire searchHistory list to keep internal control
    // If really needed, you can provide a method to replace it, but it’s better to avoid.

    // For searchCount, we avoid exposing the internal map directly
    public void incrementSearchCount(String searchTerm) {
        if (!isLocked) {
            this.searchCount.put(searchTerm, this.searchCount.getOrDefault(searchTerm, 0) + 1);
        }
    }

    public Map<String, Integer> getSearchCount() {
        // Return an unmodifiable copy to prevent external modification
        return Collections.unmodifiableMap(new HashMap<>(this.searchCount));
    }

    // Instead of a setter for searchCount map, provide methods to manipulate it.

    public boolean isLocked() {
        return this.isLocked;
    }

    public void setLocked(boolean locked) {
        this.isLocked = locked;
    }

    public int getNumUsages() {
        return this.numUsages;
    }

    // Instead of setNumUsages, provide increment usage method (depends on usage)
    public void setNumUsages(int numUsages) {
        this.numUsages = numUsages;
    }

    public String getLogName() {
        return this.logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}
