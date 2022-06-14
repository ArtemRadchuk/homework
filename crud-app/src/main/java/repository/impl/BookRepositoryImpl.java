package repository.impl;

import lombok.RequiredArgsConstructor;
import model.Book;
import org.springframework.stereotype.Component;
import repository.BookRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository<Book, String, Integer> {

    private final EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("FROM book_list.book;", Book.class).getResultList();
    }

    public void updateBook(Book book, String title, String description, String isbn, int printYear) {
        entityManager.createQuery("update book_list.book set(title, description, isbn, print_year, read_already) = ("
                + title + ", " + description + ", " + isbn + ", " + ", " + printYear +
                ", 0) where id = "  + book.getId() +";");
    }

    @Override
    public void create(Book book) {
        entityManager.createQuery("insert into book_list.book(title, description, isbn, author, genre,  print_year, read_already) values" +
                " (" + book.getTitle() + ", " + book.getDescription() + ", " + book.getIsbn() + ", " + book.getAuthor() +
                ", " + book.getGenre()  + ", " + book.getPrintYear() + ", 0);");
    }

    @Override
    public Book findByName(String title) {
        return entityManager.createQuery("FROM book_list.book where title =" + title, Book.class).getSingleResult();
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book.getId());
    }

    @Override
    public void deleteById(Integer id) {
        entityManager.remove(id);
    }
}