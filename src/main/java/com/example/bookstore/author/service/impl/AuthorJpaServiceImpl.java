package com.example.bookstore.author.service.impl;

import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.model.AuthorJpa;
import com.example.bookstore.model.exceptions.InvalidArgumentException;
import com.example.bookstore.author.mapper.AuthorMapper;
import com.example.bookstore.author.repository.AuthorJpaRepository;
import com.example.bookstore.author.service.AuthorJpaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class AuthorJpaServiceImpl implements AuthorJpaService {

    private AuthorJpaRepository authorJpaRepository;

    /**
     * This method chcecks, if in DB exists author with the same @Name and @Surname.
     * If exists, throws {@InvalidArgumentException}, else it saves new Author.
     *
     * @param author - {@Author} provided by user
     * @return {@Author} object from database
     */
    @Override
    public Author saveAuthor(Author author) {
        Optional<AuthorJpa> authorCheck = authorJpaRepository.findFirstByNameIgnoreCaseAndSurnameIgnoreCase(author.getName(), author.getSurname());
        if (authorCheck.isPresent()) {
            throw new InvalidArgumentException("Message");
        }
        AuthorJpa savedAuthor = authorJpaRepository.save(AuthorMapper.MAPPER.toAuthorJpa(author));
        return AuthorMapper.MAPPER.toAuthor(savedAuthor);
    }

    @Override
    public Author updateAuthor(Author author) {
        AuthorJpa updatedAuthor = authorJpaRepository.save(AuthorMapper.MAPPER.toAuthorJpa(author));
        return AuthorMapper.MAPPER.toAuthor(updatedAuthor);
    }
}
