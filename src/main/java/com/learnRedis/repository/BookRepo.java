package com.learnRedis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.learnRedis.entity.Books;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BookRepo {
	
	@Autowired
	private RedisTemplate<String, Object> template;
	
	private String HASH_KEY = "books";
	
	public Books save(Books book) {
		
		template.opsForHash().put(HASH_KEY, book.getId(), book);
		return book;
	}
	
	
	public Books findById(Integer id) {
        return (Books) template.opsForHash().get(HASH_KEY, id);
    }

}
