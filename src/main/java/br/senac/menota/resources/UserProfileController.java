package br.senac.menota.resources;

import br.senac.menota.dtos.UserProfileResponseDTO;
import br.senac.menota.dtos.UserProfileUpdateRequestDTO;
import br.senac.menota.services.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserProfileResponseDTO getCurrentUserProfile() {
        return userProfileService.getCurrentUserProfile();
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileResponseDTO updateProfile(@RequestBody UserProfileUpdateRequestDTO request) {
        return userProfileService.updateProfile(request);
    }
} 