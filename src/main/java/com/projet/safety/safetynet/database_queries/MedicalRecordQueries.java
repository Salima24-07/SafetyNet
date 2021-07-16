package com.projet.safety.safetynet.database_queries;

public class MedicalRecordQueries {

    public static final String SQL_CREATE = "INSERT INTO MEDICALRECORD(FIRST_NAME, LAST_NAME, BIRTHDATE, "
			+"MEDICATIONS, ALLERGIES) VALUES (?, ?, ?, ?, ?)";
	
	public static final String SQL_UPDATE = "UPDATE MEDICALRECORD SET BIRTHDATE = ?, MEDICATIONS = ?,"
			+"ALLERGIES = ? WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	public static final String SQL_DELETE = "DELETE FROM MEDICALRECORD WHERE FIRST_NAME = ? AND LAST_NAME = ?";

}
