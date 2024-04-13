package Service;

import Model.Account;
import DAO.AccountDao;

import java.util.List;

public class AccountService {
    AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account createAccount(Account account) {
        List<Account> accounts = accountDao.getAllAccounts();
        if (accounts.contains(account)) {
            return null;
        }
        return accountDao.createAccount(account);
    }

    public Account getAccountByUsername(String username) {
        return accountDao.getAccountByUsername(username);
    }
}
