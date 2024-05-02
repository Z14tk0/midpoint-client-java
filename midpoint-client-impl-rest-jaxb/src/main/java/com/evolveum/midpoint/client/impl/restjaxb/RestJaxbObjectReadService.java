package com.evolveum.midpoint.client.impl.restjaxb;

import com.evolveum.midpoint.client.api.GetOption;
import com.evolveum.midpoint.client.api.GetOptionSupport;
import com.evolveum.midpoint.client.api.GetOptionsBuilder;
import com.evolveum.midpoint.client.api.ObjectReadService;
import com.evolveum.midpoint.client.api.exception.*;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectType;

public class RestJaxbObjectReadService<O extends ObjectType> extends AbstractObjectWebResource<O> implements ObjectReadService<O>  {

    public RestJaxbObjectReadService(RestJaxbService service, Class<O> type, String oid) {
        super(service, type, oid);
    }

    @Override
    public GetOptionSupport.WithGet<O> options() {
        return new GetOptionsBuilder.WithGet<>() {

            @Override
            public O get() throws ObjectNotFoundException, SchemaException, AuthenticationException, AuthorizationException, InternalServerErrorException {
                return getService().getObject(getType(), getOid(), optionsAsStringList(), getInclude(), getExclude());
            }
        };
    }

    @Override
    public O get() throws ObjectNotFoundException, SchemaException, AuthenticationException, AuthorizationException, InternalServerErrorException {
        return getService().getObject(getType(), getOid());
    }
}
