package com.example.jasyptencryption.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity
    {
        @Id
        @SequenceGenerator(name = "author_seq", sequenceName = "author_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
        private Long id;

        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;

        @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authorEntity")
        private List<BookEntity> bookEntity;
    }