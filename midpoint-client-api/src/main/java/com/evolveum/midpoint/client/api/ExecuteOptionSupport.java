package com.evolveum.midpoint.client.api;

import com.evolveum.midpoint.client.api.exception.CommonException;
import com.evolveum.midpoint.client.api.verb.Post;

import java.util.List;

import static com.evolveum.midpoint.client.api.ExecuteOption.*;
public interface ExecuteOptionSupport<S extends ExecuteOptionSupport> {


    default S addOption(ExecuteOption option) {
        return addOption(option.apiValue());
    }

    S addOption(String option);

    default S mergeOptions(List<String> options) {
        if (options != null) {
            for (var opt: options) {
                addOption(opt);
            }
        }
        return self();
    }

    default S self() {
        return (S) this;
    }

    List<String> getOptions();

    interface WithPost<P,S extends WithPost<P,S>> extends ExecuteOptionSupport<S>, Post<P> {

        default TaskFuture<P> apost(List<String> options) throws CommonException {
            mergeOptions(options);
            return apost();
        }

        default P post(List<String> options) throws CommonException {
            mergeOptions(options);
            return post();
        }
    }

    default S raw() {
        return addOption(RAW);
    }

    default S executeImmediatelyAfterApproval() {
        return addOption(EXECUTE_IMMEDIATELY_AFTER_APPROVAL);
    }

    default S force() {
        return addOption(FORCE);
    }

    default S pushChanges() {
        return addOption(PUSH_CHANGES);
    }

    default S noCrypt() {
        return addOption(NO_CRYPT);
    }

    default S overwrite() {
        return addOption(OVERWRITE);
    }

    default S reconcile() {
        return addOption(RECONCILE);
    }

    default S isImport() {
        return addOption(IS_IMPORT);
    }

    default S limitPropagation() {
        return addOption(LIMIT_PROPAGATION);
    }

    default S reevaluateSearchFilters() {
        return addOption(REEVALUATE_SEARCH_FILTERS);
    }


}
