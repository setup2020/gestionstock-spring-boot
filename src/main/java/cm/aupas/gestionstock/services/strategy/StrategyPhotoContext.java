package cm.aupas.gestionstock.services.strategy;

import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContext {

    private final BeanFactory beanFactory;

    private Strategy strategy;

    @Setter
    private  String context;




    public StrategyPhotoContext(BeanFactory beanFactory){

        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context, Long id, InputStream photo, String title) throws FlickrException {
        determinContext(context);
        return strategy.savePhoto(id, photo, title);
    }

    private void determinContext(String context){
        final String beanName=context+"Strategy";
        switch (context){
            case "article":
                strategy=beanFactory.getBean(beanName,SaveArticlePhoto.class);

                break;
            case "customer":
                strategy=beanFactory.getBean(beanName,SaveCustomerPhoto.class);
                break;

            case "supplier":
                strategy=beanFactory.getBean(beanName,SaveSupplierPhoto.class);
                break;
            case "company":
                strategy=beanFactory.getBean(beanName,SaveCompanyPhoto.class);
                break;

            case "user":
                strategy=beanFactory.getBean(beanName,SaveUserPhoto.class);
                break;

            default: throw new InvalidOperationException("Context inconnu", ErrorCode.ERROR_500);
        }
    }


}
