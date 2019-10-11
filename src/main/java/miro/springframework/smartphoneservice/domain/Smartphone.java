package miro.springframework.smartphoneservice.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 *
 */
@Entity
public class Smartphone {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String smartphoneName;
    private String smartphoneManufacturer;

    @Column(unique = true)
    private String upc;

    private BigDecimal price;

    private Integer minOnHand;
    private Integer quantityToAssemble;


    public Smartphone(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String smartphoneName, String smartphoneManufacturer, String upc, BigDecimal price, Integer minOnHand, Integer quantityToAssemble) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.smartphoneName = smartphoneName;
        this.smartphoneManufacturer = smartphoneManufacturer;
        this.upc = upc;
        this.price = price;
        this.minOnHand = minOnHand;
        this.quantityToAssemble = quantityToAssemble;
    }

    public Smartphone() {
    }

    public static SmartphoneBuilder builder() {
        return new SmartphoneBuilder();
    }

    public UUID getId() {
        return this.id;
    }

    public Long getVersion() {
        return this.version;
    }

    public Timestamp getCreatedDate() {
        return this.createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getSmartphoneName() {
        return this.smartphoneName;
    }

    public String getSmartphoneManufacturer() {
        return this.smartphoneManufacturer;
    }

    public String getUpc() {
        return this.upc;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Integer getMinOnHand() {
        return this.minOnHand;
    }

    public Integer getQuantityToAssemble() {
        return this.quantityToAssemble;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setSmartphoneName(String smartphoneName) {
        this.smartphoneName = smartphoneName;
    }

    public void setSmartphoneManufacturer(String smartphoneManufacturer) {
        this.smartphoneManufacturer = smartphoneManufacturer;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMinOnHand(Integer minOnHand) {
        this.minOnHand = minOnHand;
    }

    public void setQuantityToAssemble(Integer quantityToAssemble) {
        this.quantityToAssemble = quantityToAssemble;
    }

    public static class SmartphoneBuilder {
        private UUID id;
        private Long version;
        private Timestamp createdDate;
        private Timestamp lastModifiedDate;
        private String smartphoneName;
        private String smartphoneManufacturer;
        private String upc;
        private BigDecimal price;
        private Integer minOnHand;
        private Integer quantityToAssemble;

        SmartphoneBuilder() {
        }

        public Smartphone.SmartphoneBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public Smartphone.SmartphoneBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public Smartphone.SmartphoneBuilder createdDate(Timestamp createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Smartphone.SmartphoneBuilder lastModifiedDate(Timestamp lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public Smartphone.SmartphoneBuilder smartphoneName(String smartphoneName) {
            this.smartphoneName = smartphoneName;
            return this;
        }

        public Smartphone.SmartphoneBuilder smartphoneManufacturer(String smartphoneManufacturer) {
            this.smartphoneManufacturer = smartphoneManufacturer;
            return this;
        }

        public Smartphone.SmartphoneBuilder upc(String upc) {
            this.upc = upc;
            return this;
        }

        public Smartphone.SmartphoneBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Smartphone.SmartphoneBuilder minOnHand(Integer minOnHand) {
            this.minOnHand = minOnHand;
            return this;
        }

        public Smartphone.SmartphoneBuilder quantityToAssemble(Integer quantityToAssemble) {
            this.quantityToAssemble = quantityToAssemble;
            return this;
        }

        public Smartphone build() {
            return new Smartphone(id, version, createdDate, lastModifiedDate, smartphoneName, smartphoneManufacturer, upc, price, minOnHand, quantityToAssemble);
        }

        public String toString() {
            return "Smartphone.SmartphoneBuilder(id=" + this.id + ", version=" + this.version + ", createdDate=" + this.createdDate + ", lastModifiedDate=" + this.lastModifiedDate + ", smartphoneName=" + this.smartphoneName + ", smartphoneManufacturer=" + this.smartphoneManufacturer + ", upc=" + this.upc + ", price=" + this.price + ", minOnHand=" + this.minOnHand + ", quantityToAssemble=" + this.quantityToAssemble + ")";
        }
    }
}
