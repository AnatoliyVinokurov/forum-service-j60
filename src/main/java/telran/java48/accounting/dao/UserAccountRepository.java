package telran.java48.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java48.accounting.model.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
