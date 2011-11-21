package com.ecom.web.upload;

import org.apache.log4j.Logger;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Bytes;

public class FileUploadForm extends Form<String> {

    private static final long serialVersionUID = -8486596461194196986L;

    private static final Logger logger = Logger.getLogger(FileUploadForm.class);


    

    public FileUploadForm(String name, IModel<String> realStateIdModel) {
        super(name, realStateIdModel);
        Injector.get().inject(this);
        

        
        // set this form to multi-part mode (always needed for uploads!)
        setMultiPart(true);

        
        // Set maximum size to 5000K 
        setMaxSize(Bytes.kilobytes(5000));

    }

   

    


   
}
