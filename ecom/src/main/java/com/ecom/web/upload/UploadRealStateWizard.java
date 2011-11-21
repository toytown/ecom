package com.ecom.web.upload;

import java.util.UUID;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;

import com.ecom.common.utils.AppConfig;
import com.ecom.web.components.wizard.WizardComponent;
import com.ecom.web.components.wizard.WizardModel;
import com.ecom.web.components.wizard.WizardStep;

public class UploadRealStateWizard extends WizardComponent {

	private static final long serialVersionUID = 4564355287570612619L;
	private  FileUploadField file1;
	private  FileUploadField file2;
	private  FileUploadField file3;
	private  FileUploadField file4;
	private  FileUploadField file5;
	private  FileUploadField file6;
	private  FileUploadField file7;
	private  FileUploadField file8;
	private  FileUploadField file9;
	private  FileUploadField file10;
	
	@SpringBean
	private AppConfig appConfig;



	public UploadRealStateWizard(String id, WizardModel wizardModel, boolean showStepNumbers) {
		super(id, wizardModel, showStepNumbers);
		wizardModel.add(new ImageUploadStep(new Model("Setp 2"), null));

	}

	protected class ImageUploadStep extends WizardStep {

		private static final long serialVersionUID = 4891384497087272754L;

		
		public ImageUploadStep(IModel<String> title, IModel<String> summary) {
			super(title, summary);
			Injector.get().inject(this);

			final String realStateId = UUID.randomUUID().toString();
			IModel<String> realStateIdModel = new Model<String>(realStateId);
			Form<?> imageUploadForm = new Form<Void>("uploadForm") {
				/**
				 * @see org.apache.wicket.markup.html.form.Form#onSubmit()
				 */
				@Override
				protected void onSubmit() {
					// display uploaded info
					FileUpload upload = file1.getFileUpload();
					if (upload == null) {
						info("No file uploaded");
					} else {
						info("File-Name: " + upload.getClientFileName() + " File-Size: " + Bytes.bytes(upload.getSize()).toString());
					}
				}
			};
			imageUploadForm.setMultiPart(true);

			file1 = new FileUploadField("file1");
			file2 = new FileUploadField("file2");
			file3 = new FileUploadField("file3");
			file4 = new FileUploadField("file4");
			file5 = new FileUploadField("file5");
			file6 = new FileUploadField("file6");
			file7 = new FileUploadField("file7");
			file8 = new FileUploadField("file8");
			file9 = new FileUploadField("file9");
			file10 = new FileUploadField("file10");

			imageUploadForm.add(file1);
			imageUploadForm.add(file2);
			imageUploadForm.add(file3);
			imageUploadForm.add(file4);
			imageUploadForm.add(file5);
			imageUploadForm.add(file6);
			imageUploadForm.add(file7);
			imageUploadForm.add(file8);
			imageUploadForm.add(file9);
			imageUploadForm.add(file10);

			imageUploadForm.add(new Button("upload"));

			add(imageUploadForm);
		}
	}

	public FileUploadField getFile1() {
		return file1;
	}

	public void setFile1(FileUploadField file1) {
		this.file1 = file1;
	}

	public FileUploadField getFile2() {
		return file2;
	}

	public void setFile2(FileUploadField file2) {
		this.file2 = file2;
	}

	public FileUploadField getFile3() {
		return file3;
	}

	public void setFile3(FileUploadField file3) {
		this.file3 = file3;
	}

	public FileUploadField getFile4() {
		return file4;
	}

	public void setFile4(FileUploadField file4) {
		this.file4 = file4;
	}

	public FileUploadField getFile5() {
		return file5;
	}

	public void setFile5(FileUploadField file5) {
		this.file5 = file5;
	}

	public FileUploadField getFile6() {
		return file6;
	}

	public void setFile6(FileUploadField file6) {
		this.file6 = file6;
	}

	public FileUploadField getFile7() {
		return file7;
	}

	public void setFile7(FileUploadField file7) {
		this.file7 = file7;
	}

	public FileUploadField getFile8() {
		return file8;
	}

	public void setFile8(FileUploadField file8) {
		this.file8 = file8;
	}

	public FileUploadField getFile9() {
		return file9;
	}

	public void setFile9(FileUploadField file9) {
		this.file9 = file9;
	}

	public FileUploadField getFile10() {
		return file10;
	}

	public void setFile10(FileUploadField file10) {
		this.file10 = file10;
	}


}
