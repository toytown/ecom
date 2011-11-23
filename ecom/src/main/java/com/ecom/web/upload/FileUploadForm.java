package com.ecom.web.upload;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Bytes;
import org.bson.types.ObjectId;

public class FileUploadForm<T> extends Form<ObjectId> {

    private static final long serialVersionUID = -8486596461194196986L;


    public FileUploadForm(String name,  IModel<ObjectId> realStateIdModel) {
        super(name, realStateIdModel);
        
        // set this form to multi-part mode (always needed for uploads!)
        setMultiPart(true);

        
        // Set maximum size to 5000K 
        setMaxSize(Bytes.kilobytes(5000));

    }

}
