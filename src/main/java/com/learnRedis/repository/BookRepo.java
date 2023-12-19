package com.learnRedis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	
	public Books save(Books books) {
		
		template.opsForHash().put(HASH_KEY, books.getId(), books);
		return books;
	}
	
	@Cacheable(value = "cacheBook" , key = "#id")
	public Object findById(Integer id) {
		log.info("from DB");
        return  template.opsForHash().get(HASH_KEY, id);
    }
	
	public List<?> findAll(){
		return template.opsForHash().values(HASH_KEY);
	}
		
	@CacheEvict(value = "cacheBook" , key="#id")
	public String deleteById(Integer id) {
		template.opsForHash().delete(HASH_KEY, id);
		return "Book has been removed!";
	}
	

}
