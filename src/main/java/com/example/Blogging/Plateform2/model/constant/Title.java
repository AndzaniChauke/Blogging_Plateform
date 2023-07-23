package com.example.Blogging.Plateform2.model.constant;

public enum Title {
    DR("Dr"), MISS("Miss"), MR("Mr"), MRS("Mrs"), PROF("Prof");

    private String description;

    Title(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
