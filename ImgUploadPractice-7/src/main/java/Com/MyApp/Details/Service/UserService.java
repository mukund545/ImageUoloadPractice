
package Com.MyApp.Details.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Com.MyApp.Details.Entity.User;
import Com.MyApp.Details.Repository.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	public User getJson(String user, MultipartFile file,String path) throws IOException {

		User userJson = new User();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			userJson = objectMapper.readValue(user, User.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}

		/*int fileCount = file.size();
		userJson.setCount(fileCount);*/

		String name = file.getOriginalFilename();
		String fullName = path+ File.separator+name;

		File f = new File(path);
		if(!f.exists()) {
			f.mkdir(); 
		}

		Files.copy(file.getInputStream(), Paths.get(fullName));


		userJson.setFileName(fullName);

		this.userRepo.save(userJson);

		return userJson;

	}

	public User getByeId(int userId) {

		return this.userRepo.findById(userId).get();
	}


	public InputStream getResourse(String path, String imageName)
			throws FileNotFoundException {

		String fullPath = path +File.separator+imageName;

		InputStream is = new FileInputStream(fullPath);

		return is;


	}

	public String  getByImageName(String imgName) throws JsonProcessingException{

		User user = this.userRepo.findUserByFileName(imgName);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);
		System.out.println("ResultingJSONstring = " + json);
		return json;
	}
	public User getByName(String name) {
		
		User user = this.userRepo.findUserByFileName(name);
		return user;
	}

}
