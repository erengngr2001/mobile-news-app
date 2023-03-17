package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionAPI {

	@Autowired
	private TransactionRepository transRepo;
	@Autowired
	private AccountRepository accRepo;

	@PostMapping("/save")
	public Message<Data> saveTransaction(@RequestBody Transaction transaction) {
		if (null != transaction.getAmount() && null != transaction.getFromAccountId()
				&& null != transaction.getToAccountId()) {
			transRepo.insert(transaction);
			Data data = new Data(transaction.getId(), accRepo.findById(transaction.getFromAccountId()).get(),
					accRepo.findById(transaction.getToAccountId()).get(), transaction.getAmount());
			Message<Data> msg = new Message<>();
			msg.setData(data);
			msg.setMessage("SUCCESS");
			return msg;
		}
		/*
		 * else if (null == transaction.getFromAccountId() || null ==
		 * transaction.getToAccountId()) { return new Message<>("ERROR: account id",
		 * null); }
		 */

		return new Message<>("ERROR:missing fields", null);
	}

	@GetMapping("/to/{accountId}")
	public Message_List<Data> listIncoming(@PathVariable String accountId) {
		if (0 == accRepo.findAllById(accountId).size()) {
			return new Message_List<>("ERROR:account doesnt exist!", null);
		}

		List<Transaction> arr = transRepo.findAllByToAccountId(accountId);
		List<Data> dataArr = new ArrayList<>();
		for (Transaction trsc : arr) {
			Data data = new Data(trsc.getId(), accRepo.findById(trsc.getFromAccountId()).get(),
					accRepo.findById(accountId).get(), trsc.getAmount());
			dataArr.add(data);
		}

		return new Message_List<>("SUCCESS", dataArr);
	}

	@GetMapping("/from/{accountId}")
	public Message_List<Data> listOutgoing(@PathVariable String accountId) {
		if (0 == accRepo.findAllById(accountId).size()) {
			return new Message_List<>("ERROR:account doesnt exist!", null);
		}

		List<Transaction> arr = transRepo.findAllByFromAccountId(accountId);
		List<Data> dataArr = new ArrayList<>();
		for (Transaction trsc : arr) {
			Data data = new Data(trsc.getId(), accRepo.findById(accountId).get(),
					accRepo.findById(trsc.getToAccountId()).get(), trsc.getAmount());
			dataArr.add(data);
		}

		return new Message_List<>("SUCCESS", dataArr);
	}

}
