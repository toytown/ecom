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
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.ecom.web.main.EcomSession;

public class BookMarkDisplayPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public BookMarkDisplayPanel(String id) {

		super(id);

		final EcomSession session = (EcomSession) Session.get();

		List<IModel<String>> favList = new ArrayList<IModel<String>>();

		for (Entry<String, String> keyVal : session.getFavourites().entrySet()) {
            if (keyVal.getValue().length() > 36) {
                String valDisp = keyVal.getValue().substring(0, 32) + " ....";
                favList.add(Model.of(valDisp));
            } else {
                favList.add(Model.of(keyVal.getValue()));
            }
		}

		final List<IModel<String>> favModel = new ArrayList<IModel<String>>(favList);

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
		
		view.setItemReuseStrategy(ReuseIfModelsEqualStrategy.getInstance());
		add(view);
		
		
	}

	
	@Override
	public boolean getStatelessHint() {
		return true;
	}

}
