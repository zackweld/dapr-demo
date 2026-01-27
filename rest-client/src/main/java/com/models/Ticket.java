package com.models;

public class Ticket {
    private String title;
    private String owner;
    private boolean approved;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "[title=" + title + ", owner=" + owner + ", approved=" + approved + "]";
    }
}
