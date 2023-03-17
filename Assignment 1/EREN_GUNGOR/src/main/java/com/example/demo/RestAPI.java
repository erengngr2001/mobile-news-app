package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class RestAPI {

	@Autowired
	AccountRepository accRepo;
	@Autowired
	TransactionRepository transRepo;

	@PostMapping("/save")
	public Message<Account> createAccount(@RequestBody Account account) {
		if (null != account.getId() && null != account.getOwner()) {
			account.setCreateDate(LocalDateTime.now());
			accRepo.insert(account);
			Message<Account> msg = new Message<>();
			msg.setData(account);
			msg.setMessage("SUCCESS");
			return msg;
		}
		return new Message<>("ERROR:missing fields", null);
	}

	@GetMapping("/{accountId}")
	public Message<AccountSummary> printAccSum(@PathVariable String accountId) {
		int balance = 0;
		AccountSummary accSum = new AccountSummary();

		if (0 == accRepo.findAllById(accountId).size()) {
			return new Message<>("ERROR:account doesnt exist!", null);
		}

		List<Transaction> arr = transRepo.findAllByFromAccountId(accountId);
		List<Data> dataArr = new ArrayList<>();
		for (Transaction trsc : arr) {
			balance -= trsc.getAmount();
			Data data = new Data(trsc.getId(), accRepo.findById(accountId).get(),
					accRepo.findById(trsc.getToAccountId()).get(), trsc.getAmount());
			dataArr.add(data);
		}

		List<Transaction> arr2 = transRepo.findAllByToAccountId(accountId);
		List<Data> dataArr2 = new ArrayList<>();
		for (Transaction trsc : arr2) {
			balance += trsc.getAmount();
			Data data = new Data(trsc.getId(), accRepo.findById(trsc.getFromAccountId()).get(),
					accRepo.findById(accountId).get(), trsc.getAmount());
			dataArr2.add(data);
		}

		accSum.setBalance(balance);
		accSum.setId(accountId);
		accSum.setOwner(accRepo.findById(accountId).get().getOwner());
		accSum.setCreateTime(accRepo.findById(accountId).get().getCreateDate());
		accSum.setTransactionsOut(dataArr);
		accSum.setTransactionsIn(dataArr2);

		return new Message<>("SUCCESS", accSum);
	}

}
