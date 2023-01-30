package com.example.patientdetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest
    {
      private String firstName;
      private String lastName;

//      private byte[] image;

      private List<PracticeRequest> practices;

    }
