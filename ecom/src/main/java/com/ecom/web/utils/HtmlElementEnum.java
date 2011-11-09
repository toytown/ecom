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

/**
 * @author vytautas r.
 */
public enum HtmlElementEnum {
	/**
	 * 
	 */
	PROTOCOL_HTTP("http"),

	/**
	 * 
	 */
	ALT("alt"),

	/**
	 * 
	 */
	TITLE("title"),

	/**
	 * 
	 */
	SRC("src"),

	/**
	 * 
	 */
	WIDTH("width"),

	/**
	 * 
	 */
	HEIGHT("height"),

	/**
	 * 
	 */
	BR("<br/>");

	private final String value;

	private HtmlElementEnum(String value) {
		this.value = value;
	}

	/**
	 * @return string representation of html element value
	 */
	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}