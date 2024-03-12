package controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.UriBuilder;
import model.Author;
import service.AuthorServiceImpl;
import service.IAuthorService;
import service.dto.delete.AuthorDeleteDTO;
import service.dto.insert.AuthorInsertDTO;
import service.dto.readonly.AuthorReadOnlyDTO;
import service.dto.update.AuthorUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.Validation;
import service.util.mapper.AuthorMapper;

import java.util.List;
import java.util.Objects;

/**
 * The {@link AuthorRestController} class provides web services for
 * the Author primary entity.
 */
@Path("/authors")
@RequestScoped
public class AuthorRestController {

    private final IAuthorService authorService = new AuthorServiceImpl();

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAuthor(AuthorInsertDTO dto, @Context UriInfo uriInfo) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            Author author = authorService.insertAuthor(dto);
            AuthorReadOnlyDTO authorDTO = AuthorMapper.mapToReadOnlyDTO(author);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(authorDTO.getId())).build()).entity(authorDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Author insert error").build();
        }
    }

    @Path("/{authorId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAuthor(@PathParam("authorId") Long authorId, AuthorUpdateDTO dto) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            if(!Objects.equals(dto.getId(), authorId)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
            }
            dto.setId(authorId);
            Author author = authorService.updateAuthor(dto);
            AuthorReadOnlyDTO authorDTO = AuthorMapper.mapToReadOnlyDTO(author);
            return Response.status(Response.Status.OK).entity(authorDTO).build();

        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found").build();
        }

    }

    @Path("/{authorId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(@PathParam("authorId") Long authorId) {

        AuthorDeleteDTO dto = new AuthorDeleteDTO();

        try {
            dto.setId(authorId);
            List<String> errors = Validation.validateDTO(dto);

            if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

            Author author = AuthorMapper.mapToAuthor(dto);
            AuthorReadOnlyDTO authorDTO = AuthorMapper.mapToReadOnlyDTO(author);
            authorService.deleteAuthor(dto);

            return Response.status(Response.Status.OK).entity(authorDTO).build();

        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found").build();
        }
    }

    @Path("/{authorId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorById(@PathParam("authorId") Long authorId) {
        Author author;
        try {
            author = authorService.getAuthorById(authorId);
            AuthorReadOnlyDTO authorDTO = AuthorMapper.mapToReadOnlyDTO(author);
            return Response.status(Response.Status.OK).entity(authorDTO).build();
        } catch(EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
        }
    }

}
