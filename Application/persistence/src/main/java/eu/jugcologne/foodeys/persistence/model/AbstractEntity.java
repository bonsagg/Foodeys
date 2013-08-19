package eu.jugcologne.foodeys.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This class provides the basic properties and methods each entity in Foodeys
 * needs.
 *
 * @author Daniel Sachse <mail@wombatsoftware.de>
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 5874026098285563101L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdOn", updatable = false, nullable = false)
    private Date createdOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdate")
    private Date lastUpdate;

    @Version
    @Column(name = "version")
    private int version = 0;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        if (id != null) {
            return id.equals(((AbstractEntity) that).id);
        }
        return super.equals(that);
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        if (this.id != null) {
            throw new PersistenceException("Cannot alter immutable ID of persistent object with id: " + id);
        }

        this.id = id;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }

    public boolean isPersistent() {
        return getId() != null;
    }

    @PrePersist
    void prePersist() {
        createdOn = new Date();
    }

    @PreUpdate
    void preUpdate() {
        lastUpdate = new Date();
    }

    @Override
    public String toString() {
        String result = getClass().getName();

        if (id != null) {
            result += "[ id: " + id + " ]";
        }

        return result;
    }
}