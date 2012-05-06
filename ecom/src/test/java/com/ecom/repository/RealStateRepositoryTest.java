package com.ecom.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.domain.RealState;
import com.ecom.service.interfaces.ImageService;
import com.ecom.test.AbstractIntegrationTest;

public class RealStateRepositoryTest extends AbstractIntegrationTest {
	
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
				appartment.setLocation(new Double[] {48.13258, 11.59129});

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
				appartment.setAreaCode("10315");
				appartment.setCity("Berlin");
				appartment.setLocation(new Double[] {52.50556, 13.51156});

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
				appartment.setAreaCode("79283");
				appartment.setCity("Bollschweil");
				appartment.setLocation(new Double[] {47.92015, 7.78645});

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
				appartment.setLocation(new Double[] {53.57452, 9.95952});

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
				appartment.setAreaCode("10337");
				appartment.setCity("Berlin");
				appartment.setLocation(new Double[] {52.50556, 13.51156});
			}

			appartment
					.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
			appartment
					.setAreaDescription("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum");

			appartment.setCategoryId(1);
			appartment.setTypeId(1);

			appartment.setUserId(getDefaultUser().getId().toString());
			realStateRepository.save(appartment);

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


}
