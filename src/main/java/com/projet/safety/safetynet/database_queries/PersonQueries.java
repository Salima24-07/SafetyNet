package com.projet.safety.safetynet.database_queries;

public class PersonQueries {
	
	public static final String SQL_CREATE = "INSERT INTO PERSON(FIRST_NAME, "
			+"LAST_NAME, ADDRESS, CITY, ZIP, PHONE, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public static final String SQL_UPDATE = "UPDATE PERSON SET ADDRESS = ?, CITY = ?, ZIP = ?,"
			+"PHONE = ?, EMAIL = ? WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	public static final String SQL_DELETE = "DELETE FROM PERSON WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	public static final String SQL_GET_EMAILS_BY_CITY = "SELECT EMAIL FROM PERSON WHERE CITY = ?";

	public static final String SQL_GET_PHONE_NUMBERS_BY_STATION = "SELECT DISTINCT PHONE FROM PERSON P "
			+"INNER JOIN FIRESTATION FS ON FS.ADDRESS = P.ADDRESS WHERE STATION = ?";
	
	public static final String SQL_GET_PERSON_INFO_BY_NAME = "select p.first_name, p.last_name, "
			+ "concat(p.address, ', ', p.zip, ' ', p.city) address, "
			+ "extract(year from age(mr.birthdate)) age, "
			+ "p.email, mr.allergies, mr.medications "
			+ "from person p "
			+ "inner join medicalrecord mr on mr.first_name = p.first_name and mr.last_name = p.last_name "
			+ "where p.first_name = ? and p.last_name = ?";
	
	public static final String SQL_GET_PERSON_INFO_BY_ADDRESS = "select p.first_name, p.last_name, "
			+ "extract(year from age(mr.birthdate)) age, "
			+ "p.phone, mr.allergies, mr.medications "
			+ "from person p "
			+ "inner join medicalrecord mr on mr.first_name = p.first_name and mr.last_name = p.last_name "
			+ "where p.address = ?";
	
	public static final String SQL_GET_CHILDREN_BY_ADDRESS = "select t1.first_name, t1.last_name, "
			+ "extract(year from age(mr.birthdate)) age,\r\n"
			+ "array_agg(concat(t2.first_name, ' ', t2.last_name)) autres\r\n"
			+ "from person t1\r\n"
			+ "inner join medicalrecord mr on t1.first_name = mr.first_name\r\n"
			+ "and t1.last_name = mr.last_name\r\n"
			+ "left join person t2 on t1.address = t2.address\r\n"
			+ "and (t1.first_name <> t2.first_name\r\n"
			+ "or t1.last_name <> t2.last_name)\r\n"
			+ "where extract(year from age(mr.birthdate)) <= 18\r\n"
			+ "and t1.address = ? \r\n"
			+ "group by 1, 2, 3";
	
	public static final String SLQ_GET_STATIONS = "SELECT STATION, ADDRESS FROM FIRESTATION "
			+ "WHERE STATION = ? ;";

}
