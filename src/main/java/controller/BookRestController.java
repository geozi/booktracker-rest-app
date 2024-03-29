package controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Book;
import service.BookServiceImpl;
import service.IBookService;
import service.dto.delete.BookDeleteDTO;
import service.dto.insert.BookInsertDTO;
import service.dto.readonly.BookReadOnlyDTO;
import service.dto.update.BookUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.Validation;
import service.util.mapper.BookMapper;

import java.util.List;
import java.util.Objects;

/**
 * The {@link BookRestController} class provides web services for
 * the Book primary entity.
 */
@Path("/books")
@RequestScoped
public class BookRestController {

    private final IBookService bookService = new BookServiceImpl();

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(BookInsertDTO dto, @Context UriInfo uriInfo) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            Book book = bookService.insertBook(dto);
            BookReadOnlyDTO bookDTO = BookMapper.mapToReadOnlyDTO(book);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(bookDTO.getId())).build()).entity(bookDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Book insert error").build();
        }
    }

    @Path("/{bookId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("bookId") Long bookId, BookUpdateDTO dto) {
        List<String> errors = Validation.validateDTO(dto);

        if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            if(!Objects.equals(dto.getId(), bookId)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
            }
            dto.setId(bookId);
            Book book = bookService.updateBook(dto);
            BookReadOnlyDTO bookDTO = BookMapper.mapToReadOnlyDTO(book);
            return Response.status(Response.Status.OK).entity(bookDTO).build();
        } catch(EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Book not found").build();
        }
    }

    @Path("/{bookId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("bookId") Long bookId) {
        BookDeleteDTO dto = new BookDeleteDTO();

        try {
            dto.setId(bookId);
            List<String> errors = Validation.validateDTO(dto);

            if(!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

            Book book = BookMapper.mapToBook(dto);
            BookReadOnlyDTO bookDTO = BookMapper.mapToReadOnlyDTO(book);
            bookService.deleteBook(dto);
            return Response.status(Response.Status.OK).entity(bookDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Book not found").build();
        }
    }

    @Path("/{bookId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("bookId") Long bookId) {
        Book book;

        try {
            book = bookService.getBookById(bookId);
            BookReadOnlyDTO bookDTO = BookMapper.mapToReadOnlyDTO(book);
            return Response.status(Response.Status.OK).entity(bookDTO).build();
        } catch(EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Book not found").build();
        }
    }
}
