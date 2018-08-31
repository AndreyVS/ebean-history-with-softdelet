package models;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.History;
import io.ebean.annotation.HistoryExclude;
import io.ebean.annotation.SoftDelete;

import javax.persistence.*;

@Entity
@Table(name = "users")
@History
public class User extends Model {

    public static final Finder<String, User> find = new Finder<>(User.class);

    @Id
    public Long id;

    @Version
    Long version;

    @SoftDelete
    public Boolean deleted;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Settings settings;

}