package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

	List<Transaction> findAllByFromAccountId(String accountId);

	List<Transaction> findAllByToAccountId(String accountId);

}
