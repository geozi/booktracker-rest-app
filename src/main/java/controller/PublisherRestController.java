package controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Publisher;
import service.IPublisherService;
import service.PublisherServiceImpl;
import service.dto.delete.PublisherDeleteDTO;
import service.dto.insert.PublisherInsertDTO;
import service.dto.readonly.PublisherReadOnlyDTO;
import service.dto.update.PublisherUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.Validation;
import service.util.mapper.PublisherMapper;

import java.util.List;
import java.util.Objects;

/**
 * The {@link PublisherRestController} class provides web services
 * for the Publisher primary entity.
 */
@Path("/publishers")
@RequestScoped
public class PublisherRestController {

    private final IPublisherService publisherService = new PublisherServiceImpl();

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPublisher(PublisherInsertDTO dto, @Context UriInfo uriInfo) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            Publisher publisher = publisherService.insertPublisher(dto);
            PublisherReadOnlyDTO publisherDTO = PublisherMapper.mapToReadOnlyDTO(publisher);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(publisherDTO.getId())).build()).entity(publisherDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Publisher not found").build();
        }
    }

    @Path("/{publisherId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePublisher(@PathParam("publisherId") Long publisherId, PublisherUpdateDTO dto) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            if(!Objects.equals(dto.getId(), publisherId)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
            }
            dto.setId(publisherId);
            Publisher publisher = publisherService.updatePublisher(dto);
            PublisherReadOnlyDTO publisherDTO = PublisherMapper.mapToReadOnlyDTO(publisher);
            return Response.status(Response.Status.OK).entity(publisherDTO).build();

        } catch(EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Publisher not found").build();
        }
    }
    @Path("/{publisherId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePublisher(@PathParam("publisherId") Long publisherId){
        PublisherDeleteDTO dto = new PublisherDeleteDTO();

        try {
            dto.setId(publisherId);
            List<String> errors = Validation.validateDTO(dto);

            if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

            Publisher publisher = PublisherMapper.mapToPublisher(dto);
            PublisherReadOnlyDTO publisherDTO = PublisherMapper.mapToReadOnlyDTO(publisher);
            publisherService.deletePublisher(dto);

            return Response.status(Response.Status.OK).entity(publisherDTO).build();
        } catch(EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Publisher not found").build();
        }
    }

    @Path("/{publisherId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPublisherById(@PathParam("publisherId") Long publisherId) {
        Publisher publisher;
        try {
            publisher = publisherService.getPublisherById(publisherId);
            PublisherReadOnlyDTO publisherDTO = PublisherMapper.mapToReadOnlyDTO(publisher);
            return Response.status(Response.Status.OK).entity(publisherDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Publisher not found").build();
        }
    }

}
