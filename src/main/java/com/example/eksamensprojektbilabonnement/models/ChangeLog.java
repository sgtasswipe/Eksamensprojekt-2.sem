package com.example.eksamensprojektbilabonnement.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChangeLog {
    private int logId;
    private String changedBy;
    private String change_;
    private LocalDateTime timestamped;
    private String tableChanged;
    private String actionType;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getTableChanged() {
        return tableChanged;
    }

    public void setTableChanged(String tableChanged) {
        this.tableChanged = tableChanged;
    }

    public ChangeLog() {
    }


    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }


    public String getChange_() {
        return change_;
    }

    public void setChange_(String change_) {
        this.change_ = change_;
    }

    public LocalDateTime getTimestamped() {
        return timestamped;
    }

    public void setTimestamped(LocalDateTime timestamped) {
        this.timestamped = timestamped;
    }
}
