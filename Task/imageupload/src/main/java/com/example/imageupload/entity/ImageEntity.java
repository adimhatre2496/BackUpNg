package com.example.imageupload.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ImageRecord")
public class ImageEntity {
    @Id
    @SequenceGenerator(name = "IMAGE_id", initialValue = 1, sequenceName = "IMAGE_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_id")
    private Long id;

    private String name;
    private String contentType;
    private Long size;

    @Lob //@Lob annotation specifies that the database should store the property as Large Object
    private byte[] data;

}
