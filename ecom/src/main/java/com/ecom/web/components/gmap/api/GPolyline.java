/*
 * 
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ecom.web.components.gmap.api;

import org.apache.wicket.ajax.AjaxRequestTarget;

import com.ecom.web.components.gmap.js.Array;
import com.ecom.web.components.gmap.js.Constructor;


/**
 * Represents an Google Maps API's <a
 * href="http://www.google.com/apis/maps/documentation/reference.html#GPolyline">GPolyline</a>.
 */
public class GPolyline extends GOverlay
{
	private static final long serialVersionUID = 1L;

	private final GLatLng[] gLatLngs;
	private final String color;
	private final int weight;
	private final float opacity;

	public GPolyline(String color, int weight, float opacity, GLatLng... gLatLngs)
	{
		super();

		this.gLatLngs = gLatLngs;
		this.color = color;
		this.weight = weight;
		this.opacity = opacity;
	}

	@Override
	public String getJSconstructor()
	{
		Constructor constructor = new Constructor("GPolyline");

		Array array = new Array();
		for (GLatLng gLatLng : gLatLngs)
		{
			array.add(gLatLng.getJSconstructor());
		}
		constructor.add(array.toJS());

		constructor.addString(color);
		constructor.addString(weight);
		constructor.addString(opacity);

		return constructor.toJS();
	}

	@Override
	protected void updateOnAjaxCall(AjaxRequestTarget target, GEvent overlayEvent)
	{
		// TODO
	}
}
