package controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Edition;
import service.EditionServiceImpl;
import service.IEditionService;
import service.dto.delete.EditionDeleteDTO;
import service.dto.insert.EditionInsertDTO;
import service.dto.readonly.EditionReadOnlyDTO;
import service.dto.update.EditionUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.Validation;
import service.util.mapper.EditionMapper;

import java.util.List;
import java.util.Objects;

/**
 * The {@link EditionRestController} class provides web services
 * for the Edition primary entity.
 */
@Path("/editions")
@RequestScoped
public class EditionRestController {
    private final IEditionService editionService = new EditionServiceImpl();

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEdition(EditionInsertDTO dto, @Context UriInfo uriInfo) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            Edition edition = editionService.insertEdition(dto);
            EditionReadOnlyDTO editionDTO = EditionMapper.mapToReadOnlyDTO(edition);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(editionDTO.getId())).build()).entity(editionDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Edition insert error").build();
        }
    }

    @Path("/{editionId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEdition(@PathParam("editionId") Long editionId, EditionUpdateDTO dto) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            if(!Objects.equals(dto.getId(), editionId)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
            }
            dto.setId(editionId);
            Edition edition = editionService.updateEdition(dto);
            EditionReadOnlyDTO editionDTO = EditionMapper.mapToReadOnlyDTO(edition);
            return Response.status(Response.Status.OK).entity(editionDTO).build();

        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Edition not found").build();
        }
    }

    @Path("/{editionId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEdition(@PathParam("editionId") Long editionId) {
        EditionDeleteDTO dto = new EditionDeleteDTO();

        try {
            dto.setId(editionId);
            List<String> errors = Validation.validateDTO(dto);

            if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

            Edition edition = EditionMapper.mapToEdition(dto);
            EditionReadOnlyDTO editionDTO = EditionMapper.mapToReadOnlyDTO(edition);
            editionService.deleteEdition(dto);

            return Response.status(Response.Status.OK).entity(editionDTO).build();

        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Edition not found").build();
        }
    }

    @Path("/{editionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEditionById(@PathParam("editionId") Long editionId) {
        Edition edition;

        try {
            edition = editionService.getEditionById(editionId);
            EditionReadOnlyDTO editionDTO = EditionMapper.mapToReadOnlyDTO(edition);
            return Response.status(Response.Status.OK).entity(editionDTO).build();
        } catch(EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Edition not found").build();
        }
    }
}
