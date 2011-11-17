package com.ecom.web.upload;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.Files;

import com.ecom.domain.RealStateImage;
import com.ecom.web.components.wizard.WizardStep;

public class ImageUploadStep extends WizardStep {

    private FileListView fileListView = null;

    public ImageUploadStep(IModel<String> title, IModel<String> summary) {
        super(title, summary);
        IModel<RealStateImage> imageModel = new Model<RealStateImage>();
        FileUploadForm imageUploadForm = new FileUploadForm("uploadForm", imageModel);
        fileListView = new FileListView("fileList", new LoadableDetachableModel<List<File>>()
        {
            @Override
            protected List<File> load()
            {
                return Arrays.asList(new File("c:/temp").listFiles());
            }
        });
        add(fileListView);
        add(imageUploadForm);
    }

    private class FileListView extends ListView<File>
    {
        /**
         * Construct.
         * 
         * @param name
         *            Component name
         * @param files
         *            The file list model
         */
        public FileListView(String name, final IModel<List<File>> files)
        {
            super(name, files);
        }

        /**
         * @see ListView#populateItem(ListItem)
         */
        @Override
        protected void populateItem(ListItem<File> listItem)
        {
            final File file = listItem.getModelObject();
            listItem.add(new Label("file", file.getName()));
            listItem.add(new Link<Void>("delete")
            {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick()
                {
                    Files.remove(file);
                    info("Deleted " + file);
                }
            });
        }
    }
}
