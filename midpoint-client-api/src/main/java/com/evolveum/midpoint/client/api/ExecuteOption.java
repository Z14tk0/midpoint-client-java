/*
 * Copyright (c) 2023 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evolveum.midpoint.client.api;

public enum ExecuteOption {

    RAW("raw"), // add, get, modify
    EXECUTE_IMMEDIATELY_AFTER_APPROVAL("executeImmediatelyAfterApproval"), // modify, delete
    FORCE("force"), //
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

    public static ExecuteOption from(String option) {
        for (var maybe : values()) {
            if (maybe.apiValue.equals(option)) {
                return maybe;
            }
        }
        throw new IllegalArgumentException("Unknown option '" + option + "'");
    }

    public String apiValue() {
        return apiValue;
    }

    @Override
    public String toString() {
        return apiValue;
    }
}
