package service;

import model.Product;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/Products")

public class ProductService {
	
	
	Product productObj = new Product(); 
	
	
	//Reading
	//completed projects
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProducts() 
	 { 
	 return productObj.readProducts();
	 } 
	
	
	
	
	//Inserting
	//completed projects
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(@FormParam("productCode") String productCode, 
			 @FormParam("projectName") String projectName, 
			 @FormParam("projectDesc") String projectDesc,
			 @FormParam("price") String price)
			  
	{ 
		String output = productObj.insertProduct(productCode, projectName, projectDesc, price); 
		return output; 
	}
	
	
	
	
	
	//Updating
	//Complete projects
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String productData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject(); 
		
		//Read the values from the JSON object
		String productID = productObject.get("productID").getAsString(); 
		String productCode = productObject.get("productCode").getAsString(); 
		String projectName = productObject.get("projectName").getAsString(); 
		String projectDesc = productObject.get("projectDesc").getAsString(); 
		String price = productObject.get("price").getAsString(); 
		
		String output = productObj.updateProduct(productID, productCode, projectName, projectDesc, price); 
	
		return output; 
	}
	

	
	
	//Deleting
	
	//complete projects
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String productData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <productID>
		String productID = doc.select("productID").text(); 
		String output = productObj.deleteProduct(productID); 
		return output; 
	}
	
	
	
	
	
}
