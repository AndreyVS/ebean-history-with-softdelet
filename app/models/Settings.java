package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.History;
import io.ebean.annotation.HistoryExclude;
import io.ebean.annotation.SoftDelete;

import javax.persistence.*;

@Entity
@Table(name = "settings")
@History
public class Settings extends Model {

    public static final Finder<String, Settings> find = new Finder<>(Settings.class);

    @Id
    public Long id;

    @Version
    Long version;

    @SoftDelete
    public Boolean deleted;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnore
    public User getUser(){
        return this.user;
    }

}
