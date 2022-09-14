package com.jersey.demorest;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {

	AlienRepository repo =new AlienRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Alien> getAliens()
	{
		System.out.println("Get Alien Called...");
		return repo.getAliens();
	}
	
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien getAlien(@PathParam("id") int id)
	{
		return repo.getAlien(id);
	}
	
	@POST		  //Used for creation of resource
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Alien createAlien(Alien a1)
	{
		System.out.println(a1);
		repo.create(a1);
		
		return a1;
	}

	@PUT		 //For updating
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Alien updateAlien(Alien a1)
	{
		System.out.println(a1);
		
		if(repo.getAlien(a1.getId()).getId()==0)
		{
			repo.create(a1);
		}
		else
		{
		repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("alien/{id}")
	public Alien killAlien(@PathParam("id") int id)
	{
		Alien a=repo.getAlien(id);
		
		if(a.getId()!=0)
			System.out.println("Alien get Deleted with id...."+id);
			repo.delete(id);
		
		return a;
	}

}

