package com.ecom.web.search;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.EmptyDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealState;
import com.ecom.web.components.pagination.ResultTablePagingNavigator;
import com.ecom.web.data.RealStateDataProvider;

public class SearchResultPage extends HomePage {

    private static final long serialVersionUID = -6983320790900379278L;

    
    public SearchResultPage(final PageParameters params) {

        //List<NamedPair> nameValueKey = params.getAllNamed();
       
        
        SearchRequest req = new SearchRequest();
        CompoundPropertyModel<SearchRequest> searchReqModel = new CompoundPropertyModel<SearchRequest>(req);
        final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
        dataContainer.setOutputMarkupId(true);
        dataContainer.setOutputMarkupPlaceholderTag(true);
        
        StatelessForm<SearchRequest> searchForm = new StatelessForm<SearchRequest>("searchForm", searchReqModel);

        TextField<Double> priceFromTxt = new TextField<Double>("priceFrom");
        TextField<Double> priceToTxt = new TextField<Double>("priceTo");
        TextField<Double> areaFromTxt = new TextField<Double>("areaFrom");
        TextField<Double> areaToTxt = new TextField<Double>("areaTo");
        TextField<Double> roomsFromTxt = new TextField<Double>("roomsFrom");
        TextField<Double> roomsToTxt = new TextField<Double>("roomsTo");

        searchForm.add(priceFromTxt);
        searchForm.add(priceToTxt);
        searchForm.add(areaFromTxt);
        searchForm.add(areaToTxt);
        searchForm.add(roomsFromTxt);
        searchForm.add(roomsToTxt);
        
       
        final IDataProvider<RealState> emptyDataprovider = new EmptyDataProvider<RealState>();

        
        DataView<RealState> emptyDataView = new DataView<RealState>("searchResultsView", emptyDataprovider) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(Item<RealState> item) {
                final RealState realState = (RealState) item.getModelObject();
				Link detailImageLink = new Link("detailImageLink"){
					@Override
					public void onClick() {
						//FIXME setResponsePage(new RealStateDetailView(advert));
					}
				};
				detailImageLink.add(new Image("title_image", "D:/dev/gitRepository/ecom/ecom/src/main/webapp/images/p2.jpg"));
				item.add(detailImageLink);
				item.add(new Label("title", realState.getTitle()));
                item.add(new Label("description", realState.getDescription()));
                item.add(new Label("price", String.valueOf(realState.getCost())));

            }
        };
        
        
        final ResultTablePagingNavigator<RealState> pagingNavigator = new ResultTablePagingNavigator<RealState>("pagingNavigator", emptyDataView, emptyDataprovider, 20);
        pagingNavigator.setVisible(true);
        pagingNavigator.setOutputMarkupId(true);
        emptyDataView.setOutputMarkupId(true);
        emptyDataView.setOutputMarkupPlaceholderTag(true);
        dataContainer.addOrReplace(emptyDataView);
        add(pagingNavigator);
        add(dataContainer);
        
        IndicatingAjaxButton deatailSearchBtn = new IndicatingAjaxButton("detailSearchBtn") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                final ISortableDataProvider dataProvider = new RealStateDataProvider();
                DataView<RealState> dataView = new DataView<RealState>("searchResultsView", dataProvider) {
                    private static final long serialVersionUID = -8557003080882186607L;

                    @Override
                    protected void populateItem(Item<RealState> item) {
                        final RealState realState = (RealState) item.getModelObject();

        				Link detailImageLink = new Link("detailImageLink"){
        					@Override
        					public void onClick() {
        						//FIXME setResponsePage(new RealStateDetailView(advert));
        					}
        				};
        				detailImageLink.add(getTitleImage());
        				item.add(detailImageLink);                        
                        item.add(new Label("title", realState.getTitle()));
                        item.add(new Label("description", realState.getDescription()));
                        item.add(new Label("price", String.valueOf(realState.getCost())));
                        
                    }
                    
                };
                
                
                dataView.setOutputMarkupId(true);
                dataView.setOutputMarkupPlaceholderTag(true);               
                
                final ResultTablePagingNavigator<RealState> pagingNavigator = new ResultTablePagingNavigator<RealState>("pagingNavigator", dataView, dataProvider, 20);
                pagingNavigator.setVisible(true);
                pagingNavigator.setOutputMarkupId(true);
                
                dataContainer.addOrReplace(dataView);
                
                target.add(pagingNavigator);
                target.add(dataContainer);
                
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                
            }
            
        };
        
        searchForm.add(deatailSearchBtn);
        
        addOrReplace(searchForm);

    }
    
    
	protected Image getTitleImage() {
		return new NonCachingImage("title_image", new AbstractReadOnlyModel() {

			@Override
			public Object getObject() {
				return new RenderedDynamicImageResource(50, 50) {

					@Override
					protected boolean render(Graphics2D g2) {

						BufferedImage baseIcon = null;
						BufferedImage baseIconOut = null;
						try {
							String imageFilePath = "D:/dev/gitRepository/ecom/ecom/src/main/webapp/images/p2.jpg";
//							String imageFilePath = titleImageDir + File.separator + advert.getTitleImage();
							File imageFile = new File(imageFilePath);
							if (imageFile.canRead()) {
								long startTime = System.currentTimeMillis();
								baseIcon = ImageIO.read(imageFile);
								long endTime = System.currentTimeMillis();
								//logger.debug("Time taken to read image : " + (endTime - startTime) + " milliseconds.");
								baseIconOut = ImageUtils.resize(baseIcon, 50, 50);
								endTime = System.currentTimeMillis();
								//logger.debug("Time taken to resize image : " + (endTime - startTime) + " milliseconds.");
							} else {
								throw new RuntimeException("No image file found at " + imageFilePath);
							}

						} catch (IOException e) {
							throw new WicketRuntimeException(e);
						}
						g2.drawImage(baseIconOut, null, null);
						return true;
					}

				};
			}
		});		
	}    

}
