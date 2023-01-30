package com.example.patientdetails.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Patient_Details")
public class PatientEntity
    {
        @Id
        @SequenceGenerator(name = "patient_id", sequenceName = "patient_id", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_id")
        private Long id;

        private String firstName;
        private String lastName;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name ="patient_id")
        private List<PracticeEntity> practices;
    }
