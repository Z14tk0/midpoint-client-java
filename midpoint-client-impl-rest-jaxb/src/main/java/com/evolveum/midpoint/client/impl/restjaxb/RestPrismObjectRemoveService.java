package com.evolveum.midpoint.client.impl.restjaxb;

import com.evolveum.midpoint.client.api.ExecuteOptionSupport;
import com.evolveum.midpoint.client.api.ExecuteOptionsBuilder;
import com.evolveum.midpoint.client.api.ObjectRemoveService;
import com.evolveum.midpoint.client.api.exception.AuthenticationException;
import com.evolveum.midpoint.client.api.exception.InternalServerErrorException;
import com.evolveum.midpoint.client.api.exception.ObjectNotFoundException;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectType;

public class RestPrismObjectRemoveService<O extends ObjectType> extends AbstractObjectWebResource<O> implements ObjectRemoveService<O> {

    public RestPrismObjectRemoveService(RestJaxbService service, Class<O> type, String oid) {
        super(service, type, oid);
    }

    @Override
    public ExecuteOptionSupport.WithDelete<O> options() {
        return new ExecuteOptionsBuilder.WithDelete<O>() {
            @Override
            public void delete() throws ObjectNotFoundException, AuthenticationException, InternalServerErrorException {
                getService().deleteObject(getType(), getOid(), optionsAsStringList());
            }
        };
    }

    @Override
    public void delete() throws ObjectNotFoundException, AuthenticationException, InternalServerErrorException {
        getService().deleteObject(getType(), getOid());
    }
}
