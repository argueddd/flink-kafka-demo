package org.example;

import java.util.Map;

/**
 * @author wei.haothoughtworks.com
 */
public class PlayStart {
    public String userID;
    public long timestamp;
    public Map<String, Object> fields;
    public Map<String, String> tags;

    public PlayStart() {
    }

    public PlayStart(String userID, long timestamp, Map<String, Object> fields, Map<String, String> tags) {
        this.userID = userID;
        this.timestamp = timestamp;
        this.fields = fields;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "PlayStart{" +
                "userID='" + userID + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", fields=" + fields +
                ", tags=" + tags +
                '}';
    }

    public String getUserID() {
        return userID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
