package ca.gkwb.mrc2zwo.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/convert", headers = "content-type=multipart/*", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        
        if (file.getName().matches(".+\\.mrc") ) {
            redirectAttributes.addFlashAttribute("message", "Not an MRC file");
            return "redirect:uploadStatus";
        }        
        
        try {
            byte[] bytes = file.getBytes();
            String fileString = new String(bytes, StandardCharsets.UTF_8);
            return workoutConverterBO.convertMRCtoZWO(fileString);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }  	
}
