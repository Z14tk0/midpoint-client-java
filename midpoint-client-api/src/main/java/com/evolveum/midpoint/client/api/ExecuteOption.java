package com.evolveum.midpoint.client.api;

public enum ExecuteOption {

    RAW("raw"),
    EXECUTE_IMMEDIATELY_AFTER_APPROVAL("executeImmediatelyAfterApproval"),
    FORCE("force"),
    PUSH_CHANGES("pushChanges"),
    NO_CRYPT("noCrypt"),
    OVERWRITE("overwrite"),
    RECONCILE("reconcile"),
    IS_IMPORT("isImport"),
    LIMIT_PROPAGATION("limitPropagation"),
    REEVALUATE_SEARCH_FILTERS("reevaluateSearchFilters");

    private final String apiValue;

    ExecuteOption(String value) {
        this.apiValue = value;
    }

    public String apiValue() {
        return apiValue;
    }

    @Override
    public String toString() {
        return apiValue;
    }
}
