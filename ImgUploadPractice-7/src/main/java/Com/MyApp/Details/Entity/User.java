package Com.MyApp.Details.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usera")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@JsonIgnoreProperties(ignoreUnknown = true)	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int userId;
	
		private String firstName;
		private String lastName;
		private int age;
		private String place;
		private String fileName;

}


