package com.example.fast_food.repositories;

import com.example.fast_food.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByEmail(String email);

    Account findByAccountId(long id);

    @Query("UPDATE Account a SET a.fullname=:fullname,a.name=:name,a.date=:date,a.phone=:phone WHERE a.accountId=:id")
    void updateProfile(@Param("fullname") String fullname,@Param("name") String name,@Param("date") Date date,@Param("phone") String phone,@Param("id") long id);
}
