package models;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.SoftDelete;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account extends Model {

    public static final Finder<String, Account> find = new Finder<>(Account.class);

    @Id
    public Long id;

    @Version
    Long version;

    @SoftDelete
    public Boolean deleted;

}