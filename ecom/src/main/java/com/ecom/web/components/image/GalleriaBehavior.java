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
package com.ecom.web.components.image;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * @author vytautas r.
 */
public class GalleriaBehavior extends Behavior {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final JavaScriptResourceReference GALLERIA_JS = new JavaScriptResourceReference(GalleriaBehavior.class, "galleria-1.2.5.min.js");
	private static final JavaScriptResourceReference GALLERIA_THEME_JS = new JavaScriptResourceReference(GalleriaBehavior.class,
		"themes/classic/galleria.classic.min.js");

	private boolean renderJqueryReference = true;

	/** there is not default width - that means it should be 100% by default */
	private int width = 0;

	private int height = 90;

	/**
	 * Construct.
	 */
	public GalleriaBehavior() {
	}

	/**
	 * Construct.
	 * 
	 * @param width
	 * @param height
	 */
	public GalleriaBehavior(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);

		if (isRenderJqueryReference()) {
			response.renderJavaScriptReference("http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js");
		}
		response.renderJavaScriptReference(GALLERIA_JS);
		response.renderJavaScriptReference(GALLERIA_THEME_JS);
	}


	@Override
	public void afterRender(Component component) {
		super.afterRender(component);
		Response response = RequestCycle.get().getResponse();
		response.write(getJavaScript());
	}

	private CharSequence getJavaScript() {
		StringBuilder galleriaConfig = new StringBuilder();
		galleriaConfig.append("<script type=\"text/javascript\">\n");
		galleriaConfig.append("$('#gallery').galleria({");
		if (getWidth() > 0) {
			galleriaConfig.append(String.format("width: %d,", getWidth()));
		}
		galleriaConfig.append(String.format("height: %d });\n", getHeight()));
		galleriaConfig.append("</script>\n");
		return galleriaConfig.toString();
	}

	/**
	 * Gets renderJqueryReference.
	 * 
	 * @return renderJqueryReference
	 */
	public boolean isRenderJqueryReference() {
		return renderJqueryReference;
	}

	/**
	 * Sets renderJqueryReference.
	 * 
	 * @param renderJqueryReference
	 *            renderJqueryReference
	 */
	public void setRenderJqueryReference(boolean renderJqueryReference) {
		this.renderJqueryReference = renderJqueryReference;
	}

	/**
	 * Gets width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets width.
	 * 
	 * @param width
	 *            width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets height.
	 * 
	 * @param height
	 *            height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
