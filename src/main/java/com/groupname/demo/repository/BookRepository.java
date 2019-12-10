package com.groupname.demo.repository;

import com.groupname.demo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,String> {
    BookEntity findByIsbn(String isbn);
    @Override
    ArrayList<BookEntity> findAll();
}
