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

import com.evolveum.midpoint.client.api.ObjectModifyService;
import com.evolveum.midpoint.client.api.ObjectService;
import com.evolveum.midpoint.client.api.exception.ObjectNotFoundException;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author semancik
 *
 */
public class RestJaxbObjectService<O extends ObjectType> extends AbstractObjectWebResource<O> implements ObjectService<O> {

    List<String> options = new ArrayList<>();
    List<String> include = new ArrayList<>();
    List<String> exclude = new ArrayList<>();

	public RestJaxbObjectService(final RestJaxbService service, final Class<O> type, final String oid) {
		super(service, type, oid);
	}

	@Override
	public O get() throws ObjectNotFoundException {
		return get(null);
	}

	@Override
	public O get(List<String> options) throws ObjectNotFoundException {
		return get(options, null, null);
	}

	@Override
	public O get(List<String> options, List<String> include, List<String> exclude) throws ObjectNotFoundException {

        var mergedOptions = new ArrayList<>(this.options);
        var mergedInclude = new ArrayList<>(this.include);
        var mergedExclude = new ArrayList<>(this.exclude);
        if (options != null) {
            mergedOptions.addAll(options);
        }
        if (include != null) {
            mergedInclude.addAll(include);
        }
        if (exclude != null) {
            mergedExclude.addAll(exclude);
        }
        return getService().getObject(getType(), getOid(), mergedOptions, mergedInclude, mergedExclude);
	}

	@Override
	public void delete() throws ObjectNotFoundException
	{
		 getService().deleteObject(getType(), getOid());
	}

	@Override
	public ObjectModifyService<O> modify() throws ObjectNotFoundException {
		return new RestJaxbObjectModifyService<>(getService(), getType(), getOid(), options);
	}

    @Override
    public ObjectService<O> addOption(String option) {
        options.add(option);
        return this;
    }

    @Override
    public List<String> getOptions() {
        return options;
    }

    @Override
    public ObjectService<O> include(String path) {
        include.add(path);
        return this;
    }

    @Override
    public ObjectService<O> exclude(String path) {
        exclude.add(path);
        return this;
    }
}
