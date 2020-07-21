package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher("Jurek", "Warszawka");
        Author author = new Author("Jacek", "Szyper");
        Author a2 = new Author("Bartosz", "Omiotek");
        Book book = new Book("Narcyz", "osas222");
        Book b2 = new Book("No own mind", "24124124");

        publisherRepository.save(publisher);

        System.out.println("Publishers count: " + publisherRepository.count());

        author.getBooks().add(book);
        author.getBooks().add(b2);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(author);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        System.out.println("Author: author got: " + author.getBooks().size() + " books");

        a2.getBooks().add(b2);

        b2.getAuthors().add(a2);
        b2.setPublisher(publisher);
        publisher.getBooks().add(b2);

        authorRepository.save(a2);
        bookRepository.save(b2);
        publisherRepository.save(publisher);

        System.out.println("Started bootstrap...");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " + publisher.getBooks().size());
    }
}
