package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {
    
    @Autowired
    private RecoveryRoomService rrService;
    private static final String VIEWS_ROOM_CREATE_OR_UPDATE_FORM = "recoveryroom/createOrUpdateRecoveryRoomForm";

    @GetMapping("/create")
	public String initCreationForm(ModelMap model) {
		RecoveryRoom recoveryRoom = new RecoveryRoom();
        model.addAttribute("recoveryRoom", recoveryRoom);
        model.addAttribute("types", rrService.getAllRecoveryRoomTypes());
		return VIEWS_ROOM_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(path="/create")
	public String processCreationForm(@Valid RecoveryRoom recoveryRoom, BindingResult result, ModelMap model) {	
        String view = "welcome";
		if (result.hasErrors()) {
            model.addAttribute("recoveryRoom", recoveryRoom);
            model.addAttribute("productType", rrService.getAllRecoveryRoomTypes());
			return VIEWS_ROOM_CREATE_OR_UPDATE_FORM;
		}else {
            rrService.save(recoveryRoom);
            model.addAttribute("message","Product succesfully created");
        }
        return view;
    }
}
