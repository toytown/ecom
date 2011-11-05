/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ecom.web.utils;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Link class combines web page class and it's parameters.
 * <p>
 * Used in combination with history panel currently.
 * 
 * @author vytautas r.
 * 
 */
public class Link implements Serializable {
	private static final long serialVersionUID = 1L;

	private Class<? extends IRequestablePage> pageClass;

	private PageParameters pageParameters;

	/**
	 * Construct.
	 * 
	 * @param <C>
	 * @param pageClass2
	 * @param parameters
	 */
	public <C extends IRequestablePage> Link(Class<? extends IRequestablePage> pageClass2, PageParameters parameters) {
		pageClass = pageClass2;
		pageParameters = parameters;
		if (pageParameters == null) {
			pageParameters = new PageParameters();
		}
	}

	/**
	 * @return page class
	 */
	public Class<? extends IRequestablePage> getPageClass() {
		return pageClass;
	}

	/**
	 * @param pageClass
	 */
	public void setPageClass(Class<? extends IRequestablePage> pageClass) {
		this.pageClass = pageClass;
	}

	/**
	 * @return page parameters of selected link
	 */
	public PageParameters getPageParameters() {
		return pageParameters;
	}

	/**
	 * @param pageParameters
	 */
	public void setPageParameters(PageParameters pageParameters) {
		this.pageParameters = pageParameters;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Link) {
			Link o = (Link)obj;
			EqualsBuilder equalsBuilder = new EqualsBuilder();
			equalsBuilder.append(getPageClass().getName(), o.getPageClass().getName());
			return equalsBuilder.isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getPageClass().getName());
		return builder.toHashCode();
	}
}
