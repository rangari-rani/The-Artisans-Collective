package com.art.service;

import com.art.modal.Order;
import com.art.modal.Seller;
import com.art.modal.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);

    List<Transaction> getTransactionsBySellerId(Seller seller);

    List<Transaction> getAllTransactions();
}
