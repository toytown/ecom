package com.ecom.web.upload;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;

import com.ecom.common.utils.AppConfig;
import com.ecom.web.components.image.StaticImage;
import com.ecom.web.components.wizard.WizardStep;

public class ImageUploadStep extends WizardStep {

    private FileListView fileListView = null;

    @SpringBean
    private AppConfig appConfig;

    public ImageUploadStep(IModel<String> title, IModel<String> summary) {
        super(title, summary);
        Injector.get().inject(this);

        final String realStateId = UUID.randomUUID().toString();
        IModel<String> realStateIdModel = new Model<String>(realStateId);
        FileUploadForm imageUploadForm = new FileUploadForm("uploadForm", realStateIdModel);

        fileListView = new FileListView("fileList", new LoadableDetachableModel<List<File>>() {
            @Override
            protected List<File> load() {
                File dir = new File(appConfig.getImageRepository() + File.separator + realStateId);
                
                if (!dir.exists()) {
                    return Arrays.asList(new File("c:/temp").listFiles());
                }
                return Arrays.asList(dir.listFiles());
            }
        });
        add(fileListView);

        add(imageUploadForm);
    }

    private class FileListView extends ListView<File> {
        /**
         * Construct.
         * 
         * @param name
         *            Component name
         * @param files
         *            The file list model
         */
        public FileListView(String name, final IModel<List<File>> files) {
            super(name, files);
        }

        /**
         * @see ListView#populateItem(ListItem)
         */
        @Override
        protected void populateItem(ListItem<File> listItem) {
            final File file = listItem.getModelObject();
            listItem.add(new Label("file", file.getName()));
            listItem.add(new Link<Void>("delete") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick() {
                    Files.remove(file);
                    info("Deleted " + file);
                }
            });
        }
    }

    private class ImageFileListView extends ListView<StaticImage> {
        /**
         * Construct.
         * 
         * @param name
         *            Component name
         * @param files
         *            The file list model
         */
        public ImageFileListView(String name, final IModel<List<StaticImage>> files) {
            super(name, files);
        }

        /**
         * @see ListView#populateItem(ListItem)
         */
        @Override
        protected void populateItem(ListItem<StaticImage> listItem) {
            //final StaticImage file = listItem.getModelObject();
            //listItem.add(new Label("file", file.getName()));

        }
    }
}
