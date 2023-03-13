package cm.aupas.gestionstock.services.strategy;

import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import cm.aupas.gestionstock.services.CustomerService;
import cm.aupas.gestionstock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("customerStrategy")
@Slf4j
public class SaveCustomerPhoto implements Strategy<CustomerDto>{

    private final FlickrService flickrService;
    private final CustomerService customerService;

    public SaveCustomerPhoto(FlickrService flickrService, CustomerService customerService) {
        this.flickrService = flickrService;
        this.customerService = customerService;
    }

    @Override
    public CustomerDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {
        CustomerDto customerDto=customerService.findById(id);
        String url=flickrService.savePhoto(photo,title);
        if(!StringUtils.hasLength(url)){
            throw  new InvalidOperationException("Erreur lors de l'enregistrement de la  photo du client", ErrorCode.ERROR_500);
        }
        customerDto.setPhoto(url);
        return customerService.save(customerDto);
    }
}
