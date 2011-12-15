package com.ecom.repository;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.common.utils.AppConfig;
import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealState;
import com.ecom.service.interfaces.ImageService;
import com.ecom.test.AbstractIntegrationTest;

public class RealStateRepositoryTest extends AbstractIntegrationTest {

	@Autowired
	private AppConfig config;

	@Autowired
	private RealStateRepository realStateRepository;

	@Autowired
	private ImageService imageService;

	@Before
	public void purgeRepository() {
		realStateRepository.deleteAll();
	}

	@Test
	public void testSaveRealState() throws Exception {

		for (int i = 1; i <= 10; i++) {
			RealState appartment = new RealState();
			appartment.setTitle(i + " zimmer appartment");

			ObjectId objId = new ObjectId();
			appartment.setId(objId);

			if (i % 2 == 0) {

				appartment.setTotalRooms(2d);
				appartment.setBedRooms(1);
				appartment.setCost(600);
				appartment.setSize(50 + i);
				appartment.setKitchenAvailable(true);
				appartment.setProvisionFree(true);
				appartment.setBalconyAvailable(false);
				appartment.setDescription("2 rooms appartment, schöne wohnlage. Sehr gutes lage, ideal für couples.");
				appartment.setStreet("Schlesierstr");
				appartment.setHouseNo("4b");
				appartment.setAreaCode("81667");
				appartment.setCity("München");

			} else if (i % 3 == 0) {
				appartment.setBalconyAvailable(true);
				appartment.setTotalRooms(3d);
				appartment.setBedRooms(2);
				appartment.setCost(800);
				appartment.setSize(67d + i * 1.1);
				appartment.setKitchenAvailable(true);
				appartment.setProvisionFree(true);
				appartment.setBalconyAvailable(true);
				appartment.setTitle("3 zimmer appartment, EBK, familien freündlich. nahe von U -bhan. provision frei....");
				appartment.setStreet("Schöhäuser allee");
				appartment.setHouseNo("24 b");
				appartment.setAreaCode("10337");
				appartment.setCity("Berlin");

			} else if (i % 4 == 0) {
				appartment.setBalconyAvailable(true);
				appartment.setTotalRooms(4d);
				appartment.setBedRooms(3);
				appartment.setCost(600 + i * 20);
				appartment.setSize(80 + i * 1.5);
				appartment.setKitchenAvailable(true);
				appartment.setBalconyAvailable(true);
				appartment.setTitle("4 zimmer hell, ruhig, provisinfrei mit panoromablick. Einbaukuche");
				appartment.setStreet("Hauptstr");
				appartment.setHouseNo("15");
				appartment.setAreaCode("10337");
				appartment.setCity("Frankfirt");

			} else if (i % 5 == 0) {
				appartment.setBalconyAvailable(true);
				appartment.setTotalRooms(5d);
				appartment.setBedRooms(4);
				appartment.setCost(800 + i * 20);
				appartment.setSize(101 + i * 1.5);
				appartment.setKitchenAvailable(true);
				appartment.setBalconyAvailable(true);
				appartment.setTitle("5 zimmer groß, ruhig, provisinfrei mit panoromablick. kaum zu vorstellbar.");
				appartment.setStreet("Hamburgerstr");
				appartment.setHouseNo("23 -37a");
				appartment.setAreaCode("67789");
				appartment.setCity("Hamburg");

			} else {
				appartment.setBalconyAvailable(false);
				appartment.setTotalRooms(1d);
				appartment.setBedRooms(1);
				appartment.setCost(500 + i * 20);
				appartment.setSize(50);
				appartment.setKitchenAvailable(true);
				appartment.setBalconyAvailable(true);
				appartment.setTitle("5 zimmer groß, ruhig, provisinfrei mit panoromablick. kaum zu vorstellbar.");
				appartment.setStreet("Neuhauser allee");
				appartment.setHouseNo("22a");
				appartment.setAreaCode("10117");
				appartment.setCity("Berlin");
			}

			appartment
					.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
			appartment
					.setAreaDescription("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum");

			appartment.setCategoryId(1);
			appartment.setTypeId(1);

			appartment.setUserName("test");
			realStateRepository.save(appartment);

			// save images
			File dir = new File(config.getImageRepository() + File.separator + appartment.getId().toString());

			if (!dir.exists()) {
				dir.mkdirs();
			}

			for (int j = 2; j <= 3; j++) {

				String other_image = "large_image_" + j + ".jpg";
				File largeImageFile = readImageFile("test-images/" + other_image);
				InputStream in = new FileInputStream(largeImageFile);
				imageService.saveUploadedImageFileInDB(largeImageFile.getName(), in, appartment, false);

			}

			String title_image = "large_image_" + i + ".jpg";

			if (i % 2 == 0) {
				title_image = "large_image_" + i + ".jpg";
			} else if (i % 3 == 0) {
				title_image = "large_image_" + i + ".jpg";
			} else if (i % 4 == 0) {
				title_image = "large_image_" + i + ".jpg";
			} else if (i % 5 == 0) {
				title_image = "large_image_" + i + ".jpg";
			}

			File titleImageFile = readImageFile("test-images/" + title_image);
            InputStream in = new FileInputStream(titleImageFile);
			imageService.saveUploadedImageFileInDB(titleImageFile.getName(), in, appartment, true);

		}

		assert (realStateRepository.count() > 0);
	}

	protected void createImage(BufferedImage image, String appartmentId, String fileName, boolean isThumbNail) throws IOException {
		if (isThumbNail) {
			image = ImageUtils.resize(image, 120, 90);
		} else {
			image = ImageUtils.resize(image, 480, 367);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		ImageIO.write(image, "jpeg", baos);
		baos.flush();
		byte[] imageBytes = baos.toByteArray();

		File dir = new File(config.getImageRepository() + File.separator + appartmentId);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		File imageFile = new File(config.getImageRepository() + File.separator + appartmentId + File.separator + fileName);

		if (imageFile.createNewFile()) {
			FileOutputStream fos = new FileOutputStream(imageFile);
			fos.write(imageBytes);
			fos.flush();
			fos.close();
		}

		baos.close();
	}
}
