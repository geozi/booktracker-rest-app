package controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Repository;
import service.IRepositoryService;
import service.RepositoryServiceImpl;
import service.dto.delete.RepositoryDeleteDTO;
import service.dto.insert.RepositoryInsertDTO;
import service.dto.readonly.RepositoryReadOnlyDTO;
import service.dto.update.RepositoryUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.Validation;
import service.util.mapper.RepositoryMapper;

import java.util.List;
import java.util.Objects;

/**
 * The {@link RepositoryRestController} class provides web services
 * for the Repository primary entity.
 */
@Path("/repositories")
@RequestScoped
public class RepositoryRestController {

    private final IRepositoryService repositoryService = new RepositoryServiceImpl();

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRepo(RepositoryInsertDTO dto, @Context UriInfo uriInfo) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            Repository repo = repositoryService.insertRepo(dto);
            RepositoryReadOnlyDTO repoDTO = RepositoryMapper.mapToReadOnlyDTO(repo);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(repoDTO.getId())).build()).entity(repoDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Repository insert error").build();
        }
    }

    @Path("/{repoId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRepo(@PathParam("repoId") Long repoId, RepositoryUpdateDTO dto) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            if(!Objects.equals(dto.getId(), repoId)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
            }
            dto.setId(repoId);
            Repository repo = repositoryService.updateRepo(dto);
            RepositoryReadOnlyDTO repoDTO = RepositoryMapper.mapToReadOnlyDTO(repo);
            return Response.status(Response.Status.OK).entity(repoDTO).build();

        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Repository not found").build();
        }
    }


    @Path("/{repoId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRepo(@PathParam("repoId") Long repoId) {
        RepositoryDeleteDTO dto = new RepositoryDeleteDTO();

        try {
            dto.setId(repoId);
            List<String> errors = Validation.validateDTO(dto);

            if (!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

            Repository repo = RepositoryMapper.mapToRepository(dto);
            RepositoryReadOnlyDTO repoDTO = RepositoryMapper.mapToReadOnlyDTO(repo);
            repositoryService.deleteRepo(dto);

            return Response.status(Response.Status.OK).entity(repoDTO).build();

        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Repository not found").build();
        }
    }

    @Path("/{repoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRepositoryById(@PathParam("repoId") Long repoId) {
        Repository repo;
        try {
            repo = repositoryService.getRepoById(repoId);
            RepositoryReadOnlyDTO repoDTO = RepositoryMapper.mapToReadOnlyDTO(repo);
            return Response.status(Response.Status.OK).entity(repoDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Repository not found").build();
        }
    }
}
