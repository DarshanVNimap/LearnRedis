package com.learnRedis.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("books")
public class Books implements Serializable{
	

	/**
	 * 
	 */
	@Id
	private Integer id;
	private String title;
	private String author;
	private Long price;
	private Long pages;

}
