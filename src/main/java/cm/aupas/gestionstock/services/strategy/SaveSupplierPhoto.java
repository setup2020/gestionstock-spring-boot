package cm.aupas.gestionstock.services.strategy;

import cm.aupas.gestionstock.domain.Supplier;
import cm.aupas.gestionstock.dto.SupplierDto;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import cm.aupas.gestionstock.services.FlickrService;
import cm.aupas.gestionstock.services.SupplierService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;


@Service("supplierStrategy")
@Slf4j
public class SaveSupplierPhoto implements Strategy<SupplierDto>{

private final SupplierService supplierService;
private final FlickrService flickrService;

    public SaveSupplierPhoto(SupplierService supplierService, FlickrService flickrService) {
        this.supplierService = supplierService;
        this.flickrService = flickrService;
    }

    @Override
    public SupplierDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {
        SupplierDto supplierDto=supplierService.findById(id);
        String url=flickrService.savePhoto(photo, title);
        if(!StringUtils.hasLength(url)){
            throw  new InvalidOperationException("Erreur lors de l'enregistrement de la  photo du fournisseur", ErrorCode.ERROR_500);
        }
        supplierDto.setPhoto(url);
        return supplierService.save(supplierDto);
    }
}
