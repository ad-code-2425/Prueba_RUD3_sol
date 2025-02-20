package com.example.hibernate.model.servicio;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.example.hibernate.model.AccMovement;
import com.example.hibernate.model.Account;
import com.example.hibernate.model.dao.AccMovementDaoHibernate;
import com.example.hibernate.model.dao.AccountDaoHibernate;
import com.example.hibernate.model.dao.IAccMovementDao;
import com.example.hibernate.model.dao.IAccountDao;

public class AccountServicio implements IAccountServicio {

    private IAccountDao accountDao;
    private IAccMovementDao accmovDao;

    public AccountServicio() {
        this.accountDao = new AccountDaoHibernate();
        this.accmovDao = new AccMovementDaoHibernate();
    }

    @Override
    public AccMovement autoTransferir(Integer accountno, double diferencia) {
        return this.accountDao.executarDentroTransaccion(() -> {
            Account account = this.accountDao.find(accountno);
            
            account.setAmount(account.getAmount().add( BigDecimal.valueOf(diferencia)));
            this.accountDao.update(account);

         

            AccMovement mov = new AccMovement(account, account, BigDecimal.valueOf(diferencia), Instant.now());
            this.accmovDao.create(mov);

            return mov;
        });
    }

    

    @Override
    public void crear(Account account) {
        this.accountDao.executarDentroTransaccion(() -> {

            this.accountDao.create(account);
            return null;
        });
    }

    @Override
    public void update(Account account) {
        this.accountDao.executarDentroTransaccion(() -> {

            this.accountDao.update(account);
            return null;
        });
    }

    @Override
    public List<Account> getAccountsByEmpno(Integer empno) {

      return  this.accountDao.executarDentroTransaccion(() -> {
            return this.accountDao.findAccountsByEmpno(empno);
        });
    }

    @Override
    public void delete(Integer accountno) {
          this.accountDao.executarDentroTransaccion(() -> {
              this.accountDao.remove(accountno);
              return null;
        });

    }

}
