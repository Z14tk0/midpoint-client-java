/*
 * Copyright (c) 2017-2020 Evolveum
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
package com.evolveum.midpoint.client.impl.restjaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.evolveum.midpoint.client.api.*;

import com.evolveum.midpoint.client.api.exception.CommonException;

import jakarta.ws.rs.core.Response;

import com.evolveum.midpoint.client.api.exception.ObjectAlreadyExistsException;
import com.evolveum.midpoint.client.api.exception.ObjectNotFoundException;
import com.evolveum.midpoint.client.api.exception.SystemException;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectType;

/**
 * @author semancik
 * @author katkav
 *
 */
public class RestJaxbObjectAddService<O extends ObjectType> extends AbstractObjectTypeWebResource<O> implements ObjectAddService<O> {

	private final O object;
    private final ExecuteOptionSupport.WithPost<ObjectReference<O>> options = new ExecuteOptionsBuilder.WithPost<ObjectReference<O>>() {
        @Override
        public TaskFuture<ObjectReference<O>> apost() throws ObjectAlreadyExistsException, ObjectNotFoundException {
            return RestJaxbObjectAddService.this.apost(optionsAsStringList());
        }
    };

	public RestJaxbObjectAddService(final RestJaxbService service, final Class<O> type, final O object) {
		super(service, type);
		this.object = object;
	}



    @Override
    public RestJaxbObjectAddService<O> addOption(String value) {
        options().option(value);
        return this;
    }

    @Override
    public ExecuteOptionSupport.WithPost<ObjectReference<O>> options() {
        return options;
    }

    @Override
	public TaskFuture<ObjectReference<O>> apost() throws CommonException {
        return options().apost();
    }

    public TaskFuture<ObjectReference<O>> apost(List<String> options) throws ObjectAlreadyExistsException, ObjectNotFoundException {

            // TODO: item object

		// if object created (sync):
		String restPath = Types.findType(getType()).getRestPath();
        Response response = getService().post(restPath, object, Map.of("options", options));

		switch(response.getStatus()) {
			case 409:
				throw new ObjectAlreadyExistsException(response.getStatusInfo().getReasonPhrase());
			case 500:
				throw new SystemException(response.getStatusInfo().getReasonPhrase());
			case 201:
			case 202:
            case 240: // handled error
            case 250: // partial error (TODO should be handled in this way?)
				String oid = RestUtil.getOidFromLocation(response, restPath);
				RestJaxbObjectReference<O> ref = new RestJaxbObjectReference<>(getService(), getType(), oid);
				return new RestJaxbCompletedFuture<>(ref);
			default:
                throw new UnsupportedOperationException("Implement other status codes, unsupported return status: " + response.getStatus());
		}
	}
}
