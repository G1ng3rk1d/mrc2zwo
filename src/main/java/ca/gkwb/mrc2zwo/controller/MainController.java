package ca.gkwb.mrc2zwo.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gkwb.mrc2zwo.bo.WorkoutConverterBO;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {

	WorkoutConverterBO workoutConverterBO;
	
	@RequestMapping("/")
	@ResponseBody
	public String root() {
		return "Welcome to the MRC to ZWO converter!";
	}
	
	@PostMapping("/convert")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes){
        try {
            byte[] bytes = file.getBytes();
            String fileString = new String(bytes, StandardCharsets.UTF_8);
            String result =  workoutConverterBO.convertMRCtoZWO(fileString);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(result); 
        } catch (Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_HTML).body("Error Processing File");
        }
    }  	
}
