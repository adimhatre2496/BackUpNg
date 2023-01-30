package com.example.patientdetails.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Practice_Details")
public class PracticeEntity
    {
        @Id
        @SequenceGenerator(name = "practice_id", sequenceName = "practice_id",initialValue = 100, allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "practice_id")
        @Column(name = "practice_id")
        private Long id;

        private String practiceName;
        private Double copay;

        @ManyToOne
        @JoinColumn(name ="patient_id")
        private PatientEntity patientEntity;

    }
