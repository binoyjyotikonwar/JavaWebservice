package org.binoy.konwar.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.binoy.konwar.messenger.model.Message;
import org.binoy.konwar.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	MessageService aMessageService = new MessageService();
	
	/* This is the 1st sample method  */
	
	/* @GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessages() {
		return "Hello world";
	}
	*/
	/* This is the 1st sample method  */
	
	
	
	/* This is the 2nd sample method to get  all hard coded responds from getAllMessages of  MessageService*/
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessage(){
		return aMessageService.getAllMessages();
		
	}
	/* This is the 2nd sample method to get  all hard coded responds from getAllMessages of  MessageService*/
	
	/* How to use path */
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Test";
	}
	
	
	/* How to use path param and how to extract the path param value */
//	@GET
//	@Path("/{messageId}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String test2(@PathParam("messageId") String messageId) {
//		return "Got path param" + messageId;
//	}
	
	
	
	/* How to use get message with path param id */
//	@GET
//	@Path("/{messageId}")
//	@Produces(MediaType.APPLICATION_XML)
//	public Message getMessage(@PathParam("messageId") long id) {
//		return aMessageService.getMessage(id);
//	}
	
	/*
	/something/{id1}/name/{id2}
	
	@PathParam("id1") int id1,
	@PathParam("id2") int id2
	
	
	
	
	
	/messages?year=2015
	/messages?start=10&size=20
	
	
	*/
	

	// All JSON respond
	// we will need ths  jersey-media-moxy  on pom.xml  for JSON support
//	@GET
//	@Path("/getMessageJson")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Message> getMessageJson(){
//		return aMessageService.getAllMessages();
//		
//	}
	
	
	// If we want to use same method for 2 pupose like query param 
	//or non query param
	//Query param http://localhost:8080/messenger/webapi/messages/getMessageJson?year=2022
	
	
	//Non query param  http://localhost:8080/messenger/webapi/messages/getMessageJson
	
//	@GET
//	@Path("/getMessageJson")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Message> getMessageJson(@QueryParam("year") int year){
//		if(year > 0) {
//			 return aMessageService.getAllMessagesForYear(year);
//		}
//		return aMessageService.getAllMessages();
//		
//	}
	
	
	
	
	//For multiple query param
//http://localhost:8080/messenger/webapi/messages/getMessageJson?start=0&size=2
	
	
	@GET
	@Path("/getMessageJson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessageJson(@QueryParam("year") int year,
										@QueryParam("start") int start,
										@QueryParam("size") int size){
		if(year > 0) {
			 return aMessageService.getAllMessagesForYear(year);
		}
		if(start >= 0 && size >= 0) {
			return aMessageService.getAllMessagePaginated(start, size);
		}
		return aMessageService.getAllMessages();
		
	}
	
	// The method which consume json input and produce json output
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		return aMessageService.addMessage(message);
		
	}
	
	// PUT we use for update the record for specific ID
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,Message message){
		message.setId(id);
		return aMessageService.updateMessage(message);
		
	}
	
	
	/* How to use get message with path param id */
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id) {
		return aMessageService.getMessage(id);
	}
	
	// This is to delete record for id
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id) {
		aMessageService.removeMessage(id);
	}
	
	
	//Param Annotations
	
	
}
