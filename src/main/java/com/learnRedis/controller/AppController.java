package com.learnRedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.learnRedis.entity.Books;
import com.learnRedis.repository.BookRepo;

@RestController
@RequestMapping(path =  "/api/v1/" , consumes = "application/json" , produces = "application/json")


public class AppController {
	
	@Autowired
	private BookRepo repo;
	
	
//	@GetMapping
//	@Cacheable
//	public ResponseEntity<?> findAll() {
//		return ResponseEntity.ok(repo.findAll());
//	}
	
	@GetMapping("{id}")
	@Cacheable(key = "#id" , value = "books")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		return ResponseEntity.ok(repo.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Books book) {
		return ResponseEntity.ok(repo.save(book));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateBook(@RequestBody Books book , @PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(repo.save(book));
	}
	
//	@DeleteMapping("{id}")
//	public ResponseEntity<?> removeBook(@PathVariable(name = "id") Integer id) {
//		return ResponseEntity.ok(repo.delete(id));
//	}

}
