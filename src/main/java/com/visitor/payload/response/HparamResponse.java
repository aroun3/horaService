package com.visitor.payload.response;


import java.util.Date;

 public interface HparamResponse {
   
     Integer getId();

     String getMinCheckin();

     String gethCheckin();

     String getLateCheckin();
    
     String getMaxCheckin() ;

     String getMinCheckout();

     String getHearlyCheckout();
    
     String gethCheckout() ;

     String getMaxChectout();

     Date getCallbackDate() ;

     String getArea();
}
