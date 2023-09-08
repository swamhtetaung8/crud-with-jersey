package org.swamhtetaung.jersey.crud_jersey;

import java.util.List;

import org.swamhtetaung.jersey.crud_jersey.models.UserModel;
import org.swamhtetaung.jersey.crud_jersey.services.UserService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "api" path)
 */

@Path("api")
public class UserResource {
	
	UserService userService = new UserService();

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }
    
    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel storeUser(UserModel user) {
    	return userService.storeUser(user);
    }
    
    @GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel getUserById(@PathParam("userId") int userId) {
    	return userService.getUserById(userId);
    }
    
    @PUT
    @Path("/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel updateUser(@PathParam("userId") int userId, UserModel user) {
    	return userService.updateUser(userId, user);
    }
    
    @DELETE
    @Path("/users/{userId}")
    public void deleteUser(@PathParam("userId") int userId) {
    	userService.deleteUser(userId);
    }
    
}
