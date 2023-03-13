package cm.aupas.gestionstock.services.strategy;

import cm.aupas.gestionstock.dto.UserDto;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import cm.aupas.gestionstock.services.FlickrService;
import cm.aupas.gestionstock.services.UserService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;


@Service("userStrategy")
@Slf4j
public class SaveUserPhoto implements Strategy<UserDto> {
    private final UserService userService;
    private final FlickrService flickrService;

    public SaveUserPhoto(UserService userService, FlickrService flickrService) {
        this.userService = userService;
        this.flickrService = flickrService;
    }

    @Override
    public UserDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {
        UserDto userDtos = userService.findById(id);
        String url = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(url)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la  photo de l'utilisateur", ErrorCode.ERROR_500);
        }
        userDtos.setPhoto(url);
        return userService.save(userDtos);
    }
}
