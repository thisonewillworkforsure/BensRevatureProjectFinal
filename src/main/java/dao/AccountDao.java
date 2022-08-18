package dao;

import java.util.List;

import pojo.AccountPojo;

public interface AccountDao {
	public List<AccountPojo> getAllAccount();
	
	public AccountPojo createAccount(AccountPojo accountPojo);
	
    AccountPojo updateAccount(AccountPojo accountPojo);
	
	public void deleteAccount(AccountPojo accountPojo);
	
	public AccountPojo getOneAccount(AccountPojo accountPojo);
}
