package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.services.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.logging.Filter;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {



    private final Flickr flickr;

    public FlickrServiceImpl(Flickr flickr) {
        this.flickr = flickr;
    }

    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {
        UploadMetaData uploadMetaData =new UploadMetaData();
        String photoId=flickr.getUploader().upload(photo,uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }



}
