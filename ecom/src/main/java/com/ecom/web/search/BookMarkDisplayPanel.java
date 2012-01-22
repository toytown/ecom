package com.ecom.web.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.ecom.web.main.EcomSession;

public class BookMarkDisplayPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public BookMarkDisplayPanel(String id) {

		super(id);

		final EcomSession session = (EcomSession) Session.get();

		List<String> favList = new ArrayList<String>();

		for (Entry<String, String> keyVal : session.getFavourites().entrySet()) {
			favList.add(keyVal.getKey());
		}

		final List<IModel<String>> favModel = new ArrayList<IModel<String>>();

		RefreshingView<String> view = new RefreshingView<String>("view") {

			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<String>> getItemModels() {
				return favModel.iterator();
			}

			@Override
			protected void populateItem(Item<String> item) {
				String markedVal = item.getModelObject();
				item.add(new Label("markedRealState", Model.of(markedVal)));
			}

		};
		
		add(view);
	}

	@Override
	public boolean getStatelessHint() {
		return true;
	}

}
