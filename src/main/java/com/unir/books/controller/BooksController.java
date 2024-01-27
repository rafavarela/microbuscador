package com.unir.books.controller;

import com.unir.books.model.pojo.Book;
import com.unir.books.model.pojo.BookDto;
import com.unir.books.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BooksController {

    private final BooksService service;

    public BooksController(BooksService service) {
        this.service = service;
    }

    //    @GetMapping("/books")
//    @Operation(
//            operationId = "Obtener libros",
//            description = "Operacion de lectura",
//            summary = "Se devuelve una lista de todos los libros almacenados en la base de datos.")
//    @ApiResponse(
//            responseCode = "200",
//            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
//    public ResponseEntity<List<Book>> getBooks(
//            @RequestHeader Map<String, String> headers,
//
//            @Parameter(name = "title", description = "Nombre del libro. No tiene por que ser exacto", example = "años de la soledad", required = false)
//            @RequestParam(required = false) String title,
//
//            @Parameter(name = "category", description = "Categoría del libro. No tiene por que ser exacto", example = "Novela", required = false)
//            @RequestParam(required = false) String category,
//
//            @Parameter(name = "author", description = "Autor del libro. No tiene por que ser exacto", example = "García Márquez", required = false)
//            @RequestParam(required = false) String author,
//
//            @Parameter(name = "description", description = "Texto incluido en la descripción del libro. No tiene por que ser exacto", example = "pueblo de Macondo", required = false)
//            @RequestParam(required = false) String description
//            ) {
//
//        log.info("headers: {}", headers);
//
//        List<Book> books = service.getBooks(title, category, author, description);
//
//        return ResponseEntity.ok(Objects.requireNonNullElse(books, Collections.emptyList()));
//    }
//
    @GetMapping("/books/{bookId}")
    @Operation(
            operationId = "Obtener un libro",
            description = "Operacion de lectura",
            summary = "Se devuelve un libro a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el libro con el identificador indicado.")
    public ResponseEntity<BookDto> getBook(@PathVariable String bookId) {

        //log.info("Request received for book {}", bookId);
        BookDto bookDto = service.getBook(bookId);

        if (bookDto != null) {
            return ResponseEntity.ok(bookDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/books")
    @Operation(
            operationId = "Insertar un libro",
            description = "Operacion de escritura",
            summary = "Se crea un libro a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el libro con el identificador indicado.")
    public ResponseEntity<BookDto> addProduct(@RequestBody BookDto request) {

        BookDto createdBook = service.createBook(request);

        if (createdBook != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
