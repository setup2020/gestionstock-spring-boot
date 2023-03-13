package cm.aupas.gestionstock.services.strategy;

import cm.aupas.gestionstock.dto.CompanyDto;
import cm.aupas.gestionstock.dto.UserDto;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import cm.aupas.gestionstock.services.CompanyService;
import cm.aupas.gestionstock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;


@Service("companyStrategy")
@Slf4j
public class SaveCompanyPhoto implements Strategy<CompanyDto>{
    private final CompanyService companyService;
    private final FlickrService flickrService;

    public SaveCompanyPhoto(CompanyService companyService, FlickrService flickrService) {
        this.companyService = companyService;
        this.flickrService = flickrService;
    }

    @Override
    public CompanyDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {
        CompanyDto companyDto=companyService.findById(id);
        String url=flickrService.savePhoto(photo, title);
        if(!StringUtils.hasLength(url)){
            throw  new InvalidOperationException("Erreur lors de l'enregistrement de la  photo de l'entrepriser", ErrorCode.ERROR_500);
        }
        companyDto.setPhoto(url);
        return companyService.save(companyDto);
    }
}
