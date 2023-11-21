package com.evolveum.midpoint.client.impl.restjaxb;

import com.evolveum.midpoint.client.api.*;
import com.evolveum.midpoint.client.api.exception.*;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectType;

public class RestJaxbObjectRemoveService<O extends ObjectType> extends AbstractObjectWebResource<O> implements ObjectRemoveService<O>  {

    public RestJaxbObjectRemoveService(RestJaxbService service, Class<O> type, String oid) {
        super(service, type, oid);
    }

    private ExecuteOptionSupport.WithDelete<O> options = new ExecuteOptionsBuilder.WithDelete<>() {

        @Override
        public void delete() throws ObjectNotFoundException, AuthenticationException, InternalServerErrorException {
            getService().deleteObject(getType(), getOid(), optionsAsStringList());
        }
    };;

    @Override
    public ExecuteOptionSupport.WithDelete<O> options() {
        return options;
    }

    @Override
    public void delete() throws ObjectNotFoundException, AuthenticationException, InternalServerErrorException {
        options().delete();
    }
}
