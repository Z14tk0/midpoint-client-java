package com.evolveum.midpoint.client.api;

import com.evolveum.midpoint.client.api.exception.CommonException;
import com.evolveum.midpoint.client.api.verb.Post;

import java.util.List;

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

}
