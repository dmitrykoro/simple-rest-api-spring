package com.paper_submission_system.api.model;

import com.paper_submission_system.api.dao.AccountDAO;
import jakarta.persistence.*;


@Entity
@Table(name = "account")
public class AccountModel implements AccountDAO {
    @Id
    private String id;

    public AccountModel() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}