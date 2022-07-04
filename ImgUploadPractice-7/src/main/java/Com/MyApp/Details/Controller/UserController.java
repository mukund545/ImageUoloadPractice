
package Com.MyApp.Details.Controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import Com.MyApp.Details.Entity.User;
import Com.MyApp.Details.Service.UserService;

@RestController
@RequestMapping("/usera")
public class UserController {
	
	@Autowired
    UserService userService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping(value = "/upload", consumes = { MediaType.APPLICATION_JSON_VALUE,
												 MediaType.MULTIPART_FORM_DATA_VALUE })
     public User upload
	(@RequestPart("user") String user,
	 @RequestPart("file") MultipartFile file) throws IOException {

		User userJson = userService.getJson(user, file,path);
		
		return userJson;
	}
	
	@GetMapping(value = "/download/{userId}",produces = { MediaType.APPLICATION_JSON_VALUE} )
		public User getById(@PathVariable("userId") int userId) {
		
		User user = this.userService.getByeId(userId);
		
		return user;
		
	}
	@GetMapping(value = "/downlo/{imgName}",produces = {MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public String downloadImage(@PathVariable String imgName,
			HttpServletResponse response) throws IOException {
		
		
		String users = this.userService.getByImageName(imgName);
		 
		InputStream is = this.userService.getResourse(path,imgName);
		
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(is, response.getOutputStream());
		return users;
		
	}
	@GetMapping("/down/{imgName}")
	public User getByName(@PathVariable("imgName") String imgName) {
		
		User user = this.userService.getByName(imgName);
		
		return user;
		
		
	}

}