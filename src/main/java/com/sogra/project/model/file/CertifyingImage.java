package com.sogra.project.model.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "certifying_images")
@NoArgsConstructor
public class CertifyingImage {

    @Id
    @Column(name = "certifying_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String certifyingId;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @Builder
    public CertifyingImage(String certifyingId, String name, String type, byte[] data) {
        this.certifyingId = certifyingId;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getCertifyingId() {
        return certifyingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
