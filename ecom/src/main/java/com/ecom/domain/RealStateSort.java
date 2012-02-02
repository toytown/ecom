package com.ecom.domain;

public enum RealStateSort {
	 PRC_ASC, PRC_DESC, ROOMS_ASC, ROOMS_DESC, SIZE_ASC, SIZE_DESC;
	 
	 public static RealStateSort getSort(String sortOrder) {
	     
     
	     if (sortOrder.equalsIgnoreCase("PRC_ASC")) {
	         return PRC_ASC;
	     }
	     
         if (sortOrder.equalsIgnoreCase("PRC_DESC")) {
             return PRC_DESC;
         }

         if (sortOrder.equalsIgnoreCase("ROOMS_ASC")) {
             return ROOMS_ASC;
         }
         
         if (sortOrder.equalsIgnoreCase("ROOMS_DESC")) {
             return ROOMS_DESC;
         }
         
         if (sortOrder.equalsIgnoreCase("SIZE_ASC")) {
             return SIZE_ASC;
         }
         
         if (sortOrder.equalsIgnoreCase("SIZE_DESC")) {
             return SIZE_DESC;
         }
         
	     return ROOMS_DESC;
	 }
}
