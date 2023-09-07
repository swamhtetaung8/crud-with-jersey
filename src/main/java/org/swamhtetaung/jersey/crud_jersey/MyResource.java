package org.swamhtetaung.jersey.crud_jersey;

import org.swamhtetaung.jersey.crud_jersey.models.UserModel;
import org.swamhtetaung.jersey.crud_jersey.services.UserService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("crud")
public class MyResource {
	
	UserService userService = new UserService();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel storeUser(UserModel user) {
    	return userService.storeUser(user);
    }
}
