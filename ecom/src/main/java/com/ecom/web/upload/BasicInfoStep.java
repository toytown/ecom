package com.ecom.web.upload;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.validation.validator.StringValidator;

import com.ecom.domain.AppartmentType;
import com.ecom.domain.Condition;
import com.ecom.domain.HeatingType;
import com.ecom.domain.HouseType;
import com.ecom.domain.OffererType;
import com.ecom.domain.OfficeType;
import com.ecom.domain.RealState;
import com.ecom.domain.RealStateCategory;
import com.ecom.domain.TariffType;
import com.ecom.repository.RealStateRepository;
import com.ecom.web.components.gmap.api.GLatLng;
import com.ecom.web.components.image.EcomImageResouceReference;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.components.validation.ErrorClassAppender;
import com.ecom.web.components.wizard.WizardStep;
import com.ecom.web.main.EcomApplication;
import com.ecom.web.main.EcomSession;
import com.ecom.web.main.ServerGeocoder;

public class BasicInfoStep extends WizardStep {

	private static final long serialVersionUID = 769908228950127137L;

	private WebMarkupContainer titleImageContainer;

	private Form<RealState> realStateUploadInfoForm;
	@SpringBean
	private RealStateRepository realStateRepository;

	@Override
	public void onInitialize() {
		super.onInitialize();
		Injector.get().inject(this);
	}

	public BasicInfoStep(IModel<String> wizard_title, IModel<String> summary, final IModel<RealState> realStateModel) {
		super(wizard_title, summary, realStateModel);
	}

	@Override
	public void onConfigure() {
		titleImageContainer = new WebMarkupContainer("titleImageContainer");
		titleImageContainer.setOutputMarkupId(true);

		// @SuppressWarnings("unchecked")
		final IModel<RealState> realStateModel = (IModel<RealState>) this.getDefaultModel();

		RealState realState = realStateModel.getObject();
		realState.setInsertedTs(new Date());
		realState.setUpdatedTs(new Date());
		final ModalWindow modalWindow;
		modalWindow = new ModalWindow("titleUploadFileModalWindow");
		modalWindow.setCssClassName(ModalWindow.CSS_CLASS_BLUE);
		modalWindow.setCookieName("titleUploadFileModalWindow");
		modalWindow.setInitialHeight(130);
		modalWindow.setInitialWidth(190);
		modalWindow.setPageCreator(new ModalWindow.PageCreator() {

			private static final long serialVersionUID = 1L;

			@Override
			public Page createPage() {

				return new TitleImageUploadPage(modalWindow, realStateModel);
			}

		});

		modalWindow.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {

				RealState realState = realStateRepository.findOne(realStateUploadInfoForm.getModelObject().getId());

				if (realState != null && !StringUtils.isEmpty(realState.getTitleThumbNailImage())) {
					realStateUploadInfoForm.modelChanged();
					AjaxLink<Void> imgUploadLink = new AjaxLink<Void>("titleUpload") {

						private static final long serialVersionUID = 1L;

						@Override
						public void onClick(AjaxRequestTarget target) {
							// modalWindow.show(target);

						}

					};
					imgUploadLink.add(getTitleImage(realState));
					titleImageContainer.addOrReplace(imgUploadLink);
					target.add(titleImageContainer);
				}

				return true;
			}
		});

        AjaxLink<Void> imgUploadLink = new AjaxLink<Void>("titleUpload") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.show(target);

            }

        };
        
		if (realState != null && !StringUtils.isEmpty(realState.getTitleImageId())) {
		    StaticImage img = getTitleImage(realStateModel.getObject());
		    imgUploadLink.add(img);
			titleImageContainer.add(imgUploadLink);
			
		} else {
			ContextImage img = new ContextImage("title_image", new Model<String>("images/no_photo_icon.gif"));

			imgUploadLink.add(img);
			titleImageContainer.add(imgUploadLink);
		}

		realStateUploadInfoForm = new Form<RealState>("realStateAdvertForm", realStateModel) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onModelChanged() {
				RealState realStateFromDB = realStateRepository.findOne(this.getModelObject().getId());
				RealState realStateFromGUI = this.getModelObject();

				if (realStateFromGUI != null && realStateFromDB != null && !realStateFromDB.getTitleImages().isEmpty()) {
					realStateFromGUI.addTitleImages(realStateFromDB.getTitleImages());
					realStateRepository.delete(this.getModelObject().getId());
					realStateRepository.save(realStateFromGUI);
				}

			}

		};
		realStateUploadInfoForm.add(titleImageContainer);
		realStateUploadInfoForm.setOutputMarkupId(true);
		realStateUploadInfoForm.visitChildren(FormComponent.class, new IVisitor<FormComponent<?>, Void>() {

			@Override
			public void component(FormComponent<?> component, IVisit<Void> visit) {
				component.add(new ErrorClassAppender());
			}
		});

		realStateUploadInfoForm.add(new AjaxLink<Void>("uploadTitleImage") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				modalWindow.show(target);
			}
		});

		realStateUploadInfoForm.add(modalWindow);

		final TextArea<String> title = new TextArea<String>("title");
		title.setRequired(true);
		title.add(StringValidator.maximumLength(150));
		title.setOutputMarkupId(true);
		title.add(new ErrorClassAppender());

		TextArea<String> description = new TextArea<String>("description");
		description.setRequired(true);
		description.add(StringValidator.maximumLength(600));

		TextArea<String> areaDescription = new TextArea<String>("areaDescription");

		TextArea<String> fittings = new TextArea<String>("fittings");

		TextField<String> city = new TextField<String>("city");
		city.setRequired(true);

		TextField<String> areaCode = new TextField<String>("areaCode");
		areaCode.setRequired(true);

		CheckBox showFullAddress = new CheckBox("allowFullAddressDisplay");

		// apartment type
		if (realState.getRealStateCategory() != null) {
			if (realState.getOffererType().equals(OffererType.Business)) {
				IModel<OfficeType> officeSelected = new Model<OfficeType>(OfficeType.AppartmentOffice);
				DropDownChoice<OfficeType> officeType = new DropDownChoice<OfficeType>("appartmentType", officeSelected, Arrays.asList(OfficeType
						.values()), new EnumChoiceRenderer<OfficeType>());
				realStateUploadInfoForm.add(officeType);
			} else {

				if (realState.getRealStateCategory().equals(RealStateCategory.Appartment)) {
					IModel<AppartmentType> appartmentSelected = new Model<AppartmentType>(AppartmentType.Etagewohnung);
					DropDownChoice<AppartmentType> appartmentType = new DropDownChoice<AppartmentType>("appartmentType", appartmentSelected,
							Arrays.asList(AppartmentType.values()), new EnumChoiceRenderer<AppartmentType>());
					realStateUploadInfoForm.add(appartmentType);
				} else if (realState.getRealStateCategory().equals(RealStateCategory.House)) {
					IModel<HouseType> houseSelected = new Model<HouseType>(HouseType.Einfamilienhaus);
					DropDownChoice<HouseType> hauseType = new DropDownChoice<HouseType>("appartmentType", houseSelected,
							Arrays.asList(HouseType.values()), new EnumChoiceRenderer<HouseType>());
					realStateUploadInfoForm.add(hauseType);
				}
			}
		}

		TextField<String> contactTitle = new TextField<String>("contactInfo.title");
		TextField<String> contactFirstName = new TextField<String>("contactInfo.firstName");
		TextField<String> contactLastName = new TextField<String>("contactInfo.lastName");
		TextField<String> contactEmail = new TextField<String>("contactInfo.email");
		TextField<String> contactPhone = new TextField<String>("contactInfo.phone");
		TextField<String> contactMobile = new TextField<String>("contactInfo.mobile");
		TextField<String> contactStreet = new TextField<String>("contactInfo.street");

		realStateUploadInfoForm.add(contactTitle);
		realStateUploadInfoForm.add(contactFirstName);
		realStateUploadInfoForm.add(contactLastName);
		realStateUploadInfoForm.add(contactEmail);
		realStateUploadInfoForm.add(contactPhone);
		realStateUploadInfoForm.add(contactMobile);
		realStateUploadInfoForm.add(contactStreet);

		TextField<String> street = new TextField<String>("street");
		TextField<String> houseNo = new TextField<String>("houseNo");
		TextField<Double> size = new TextField<Double>("size");
		TextField<Double> cost = new TextField<Double>("cost");
		TextField<Double> floor = new TextField<Double>("floor");
		TextField<Double> totalFloors = new TextField<Double>("totalFloors");
		TextField<Double> totalRooms = new TextField<Double>("totalRooms");
		TextField<Integer> bedRooms = new TextField<Integer>("bedRooms");
		TextField<Integer> bathRooms = new TextField<Integer>("bathRooms");
		
		IModel<HeatingType> heatingTypeSelected = new Model<HeatingType>(HeatingType.Centralheating);
		DropDownChoice<HeatingType> heatingType = new DropDownChoice<HeatingType>("heatingType", heatingTypeSelected, Arrays.asList(HeatingType.values()), new EnumChoiceRenderer<HeatingType>());

		IModel<Condition> conditionTypeSelected = new Model<Condition>(Condition.Good);		
		DropDownChoice<Condition> condition = new DropDownChoice<Condition>("condition", conditionTypeSelected, Arrays.asList(Condition.values()));

		
		CheckBox toiletWithBathRoom = new CheckBox("toiletWithBathRoom");
		CheckBox cellarAvailable = new CheckBox("cellarAvailable");
		CheckBox balconyAvailable = new CheckBox("balconyAvailable");
		CheckBox elevatorAvailable = new CheckBox("elevatorAvailable");
		CheckBox gardenAvailable = new CheckBox("gardenAvailable");
		CheckBox heatingCostIncluded = new CheckBox("heatingCostIncluded");
		CheckBox energyPassAvailable = new CheckBox("energyPassAvailable");
		CheckBox kitchenAvailable = new CheckBox("kitchenAvailable");
		CheckBox furnished = new CheckBox("furnished");
		CheckBox animalsAllowed = new CheckBox("animalsAllowed");
		CheckBox garageAvailable = new CheckBox("garageAvailable");
		CheckBox rented = new CheckBox("rented");

		TextField<Double> additionalCost = new TextField<Double>("additionalCost");
		TextField<Double> energyRequirement = new TextField<Double>("energyRequirement");
		TextField<Double> depositPeriod = new TextField<Double>("depositPeriod");
		DateTextField availableFrom = new DateTextField("availableFrom");
		TextField<Integer> noOfGarages= new TextField<Integer>("noOfGarages");
		TextField<Double> garageCost = new TextField<Double>("garageCost");
		TextField<Integer> builtYear = new TextField<Integer>("builtYear");
		TextField<String> provisionCondition = new TextField<String>("provisionCondition");
		TextField<String> lastRenovatedYear = new TextField<String>("lastRenovatedYear");
		CheckBox provisionFree = new CheckBox("provisionFree");
		CheckBox barrierFree = new CheckBox("barrierFree");
		CheckBox seniorAppartment = new CheckBox("seniorAppartment");

		//Payment Info
        WebMarkupContainer paymentInfo = new WebMarkupContainer("paymentInfo");

        TextField<String> referenceName = new TextField<String>("paymentInfo.referenceName");
        TextField<String> bankName = new TextField<String>("paymentInfo.bankName");
        TextField<String> blz = new TextField<String>("paymentInfo.blz");
        TextField<String> accountNumber = new TextField<String>("paymentInfo.accountNumber");
        TextArea<String> referenceText = new TextArea<String>("paymentInfo.referenceText");

        paymentInfo.add(referenceName);
        paymentInfo.add(bankName);
        paymentInfo.add(blz);
        paymentInfo.add(accountNumber);
        paymentInfo.add(referenceText);
        
        paymentInfo.setVisible(realStateModel.getObject().getTariffType() != null && realStateModel.getObject().getTariffType().equals(TariffType.Profi));
        
		realStateUploadInfoForm.add(title);
		realStateUploadInfoForm.add(description);
		realStateUploadInfoForm.add(areaDescription);
		realStateUploadInfoForm.add(fittings);
		realStateUploadInfoForm.add(city);
		realStateUploadInfoForm.add(areaCode);
		realStateUploadInfoForm.add(street);
		realStateUploadInfoForm.add(houseNo);
		realStateUploadInfoForm.add(showFullAddress);
		realStateUploadInfoForm.add(size);
		realStateUploadInfoForm.add(cost);
		realStateUploadInfoForm.add(floor);
		realStateUploadInfoForm.add(totalFloors);
		realStateUploadInfoForm.add(totalRooms);
		realStateUploadInfoForm.add(bedRooms);
		realStateUploadInfoForm.add(bathRooms);
		realStateUploadInfoForm.add(toiletWithBathRoom);
		realStateUploadInfoForm.add(cellarAvailable);
		realStateUploadInfoForm.add(balconyAvailable);
		realStateUploadInfoForm.add(elevatorAvailable);
		realStateUploadInfoForm.add(gardenAvailable);
		realStateUploadInfoForm.add(heatingCostIncluded);
		realStateUploadInfoForm.add(energyPassAvailable);
		realStateUploadInfoForm.add(provisionCondition);
		realStateUploadInfoForm.add(kitchenAvailable);
		realStateUploadInfoForm.add(furnished);
		realStateUploadInfoForm.add(animalsAllowed);
		realStateUploadInfoForm.add(noOfGarages);
		realStateUploadInfoForm.add(garageAvailable);
		realStateUploadInfoForm.add(rented);
		realStateUploadInfoForm.add(additionalCost);
		realStateUploadInfoForm.add(energyRequirement);
		realStateUploadInfoForm.add(depositPeriod);
		realStateUploadInfoForm.add(availableFrom);
		realStateUploadInfoForm.add(garageCost);
		realStateUploadInfoForm.add(builtYear);
		realStateUploadInfoForm.add(lastRenovatedYear);
		realStateUploadInfoForm.add(provisionFree);
		realStateUploadInfoForm.add(barrierFree);
		realStateUploadInfoForm.add(seniorAppartment);
		realStateUploadInfoForm.add(paymentInfo);
		realStateUploadInfoForm.add(heatingType);
		realStateUploadInfoForm.add(condition);

		addOrReplace(realStateUploadInfoForm);
	}

	private StaticImage getTitleImage(RealState realState) {
		if (realState != null) {
			ResourceReference imagesResourceReference = new EcomImageResouceReference();
			PageParameters imageParameters = new PageParameters();
			String imageId = realState.getTitleThumbNailImage();
			imageParameters.set("id", imageId);

			CharSequence urlForImage = getRequestCycle().urlFor(imagesResourceReference, imageParameters);
			StaticImage titleImage = new StaticImage("title_image", Model.of(urlForImage.toString()));

			return titleImage;

		} else {
			return null;
		}
	}

	@Override
	public void applyState() {

		RealState realState = (RealState) this.getDefaultModelObject();

		if (realState != null) {
			EcomSession session = (EcomSession) Session.get();
			realState.setUserId(session.getUserId());

			realState.setUserId(session.getUserId());
			ServerGeocoder geocoder = EcomApplication.get().getServerGeocoder();

			try {
				GLatLng lating = geocoder.findAddress(realState.getAddress());
				if (lating != null) {
					Double[] location = new Double[] { lating.getLat(), lating.getLng() };
					realState.setLocation(location);
				} else {

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			realState.setUpdatedTs(new Date());
			realStateRepository.save(realState);
		}

	}
}
