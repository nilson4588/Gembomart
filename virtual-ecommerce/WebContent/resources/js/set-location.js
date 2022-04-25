         
    	  if(localStorage.getItem("stateCode") != null) {
    		  
    		  $("#state").val(localStorage.getItem("stateCode"));
    		  
    		  $.ajax({
          		url: "/virtual-ecommerce/districtListByState/"+localStorage.getItem("stateCode"), 
          		success: function(result){
          			  var slctSubcat=$('#district, #district1'); 
          			
          			  option="<option value='0'>Select District</option>";
                      
          			  slctSubcat.empty();
                      for(var i=0; i<result.length; i++){ 
                          option = option + "<option value='"+result[i].districtCode + "'>"+result[i].districtName + "</option>";
                      }
                      slctSubcat.append(option);
                      $("#district").val(localStorage.getItem("districtCode"));
                      
                      
                      $.ajax({
                    		url: "/virtual-ecommerce/talukaListByDistrict/"+localStorage.getItem("districtCode"), 
                    		success: function(result){
                    			var slctSubcat=$('#taluka, #taluka1'); 
                    			
                    			option="<option value='0'>Select Taluka</option>";
                                
                    			slctSubcat.empty();
                                for(var i=0; i<result.length; i++){                    	
                                    option = option + "<option value='"+result[i].talukaCode + "'>"+result[i].talukaName + "</option>";
                                }
                                slctSubcat.append(option);
                                $("#taluka").val(localStorage.getItem("talukaCode"));
                                
                                $.ajax({
                            		url: "/virtual-ecommerce/getSellerListByArea/"+localStorage.getItem("talukaCode"), 
                            		success: function(result){
                            			
                            			var slctSubcat=$('#product-5');         			 
                            			//slctSubcat.empty();
                            			var data="";
                                       for(var i=0; i<result.length; i++){ 
                                            
                                    	   data = data +    " <div class='product-box product-wrap product-style-1'>"+
                                                "<div class='img-wrapper'>"+
                                                  
                                                    "<div class='front'>"+
                                                       " <a href='productlist/"+result[i].sellerId+"'>"+
                                                       "  <img src='"+imgLocation+"/"+result[i].shopImage+"' class='img-fluid blur-up lazyload bg-img' alt=''></a>"+
                                                   "  </div>"+                               
                                                     "<div class='addtocart_btn'>"+
                                                         "<button class='add-button add_cart' title='Add to cart'>"+result[i].shopName+"</button>"+
                                                         "<div class='qty-box cart_qty'>"+
                                                            " <div class='input-group'>"+
                                                              "   <button type='button' class='btn quantity-left-minus' data-type='minus'"+
                                                             "        data-field=''>"+
                                                                "     <i class='fa fa-minus' aria-hidden='true'></i>"+
                                                              "   </button>"+
                                                             "    <input type='text' name='quantity'"+
                                                             "        class='form-control input-number qty-input' value='1'>"+
                                                             "    <button type='button' class='btn quantity-right-plus' data-type='plus'"+
                                                              "       data-field=''>"+
                                                              "       <i class='fa fa-plus' aria-hidden='true'></i>"+
                                                            "    </button>"+
                                                           "  </div>"+
                                                       "  </div>"+
                                                    " </div>"+
                                                " </div>"+                           
                                             " </div> ";
                                    	   
                                        	//alert(result[i].shopName+" "+result[i].sellerContactNo);
                                        }
                                       
                                       slctSubcat.html(data);                                      
                                  	}
                            	});
                          	}
                      });   
                   }
          	  });
    		  
    		  
    	  }
    	  
    	  
    	  
    	  var imgLocation = $("#imgLocation").val();
    	
    	
    	$("#state, #state1").change(function(){
    		
    		var stateCode = $(this).val(); 
    		localStorage.setItem("stateCode", stateCode);
    		
        	$.ajax({
        		url: "/virtual-ecommerce/districtListByState/"+stateCode, 
        		success: function(result){
        			var slctSubcat=$('#district, #district1'); 
        			
        			option="<option value='0'>Select District</option>";
                    
        			slctSubcat.empty();
                    for(var i=0; i<result.length; i++){ 
                        option = option + "<option value='"+result[i].districtCode + "'>"+result[i].districtName + "</option>";
                    }
                    slctSubcat.append(option);
                    $("#state").val(stateCode);
              	}
        	});
    		
    	});
    	
		$("#district, #district1").change(function(){
    		
    		var districtCode = $(this).val();   
    		localStorage.setItem("districtCode", districtCode);
    		
        	$.ajax({
        		url: "/virtual-ecommerce/talukaListByDistrict/"+districtCode, 
        		success: function(result){
        			var slctSubcat=$('#taluka, #taluka1'); 
        			
        			option="<option value='0'>Select Taluka</option>";
                    
        			slctSubcat.empty();
                    for(var i=0; i<result.length; i++){                    	
                        option = option + "<option value='"+result[i].talukaCode + "'>"+result[i].talukaName + "</option>";
                    }
                    slctSubcat.append(option);
                    $("#district").val(districtCode);
              	}
        	});    		
    	});
		
		
		$("#taluka, #taluka1").change(function(){
    		
    		var talukaCode = $(this).val(); 
    		localStorage.setItem("talukaCode", talukaCode);
    		
        	$.ajax({
        		url: "/virtual-ecommerce/getSellerListByArea/"+talukaCode, 
        		success: function(result){
        			
        			var slctSubcat=$('#product-5');         			 
        			//slctSubcat.empty();
        			var data="";
                   for(var i=0; i<result.length; i++){ 
                        
                	   data = data +    " <div class='product-box product-wrap product-style-1'>"+
                            "<div class='img-wrapper'>"+
                              
                                "<div class='front'>"+
                                    " <a href='productlist/"+result[i].sellerId+"'>"+
                                   "  <img src='"+imgLocation+"/"+result[i].shopImage+"' class='img-fluid blur-up lazyload bg-img' alt=''></a>"+
                               "  </div>"+                               
                                 "<div class='addtocart_btn'>"+
                                     "<button class='add-button add_cart' title='Add to cart'>"+result[i].shopName+"</button>"+
                                     "<div class='qty-box cart_qty'>"+
                                        " <div class='input-group'>"+
                                          "   <button type='button' class='btn quantity-left-minus' data-type='minus'"+
                                         "        data-field=''>"+
                                            "     <i class='fa fa-minus' aria-hidden='true'></i>"+
                                          "   </button>"+
                                         "    <input type='text' name='quantity'"+
                                         "        class='form-control input-number qty-input' value='1'>"+
                                         "    <button type='button' class='btn quantity-right-plus' data-type='plus'"+
                                          "       data-field=''>"+
                                          "       <i class='fa fa-plus' aria-hidden='true'></i>"+
                                        "    </button>"+
                                       "  </div>"+
                                   "  </div>"+
                                " </div>"+
                            " </div>"+                           
                         " </div> ";
                	   
                    	//alert(result[i].shopName+" "+result[i].sellerContactNo);
                    }
                   
                   slctSubcat.html(data);
                   $("#taluka").val(talukaCode);
              	}
        	});    		
    	});