package edu.vandy.cs251.imagegrabber;

import android.media.MediaRouter;
import android.net.Uri;

import java.io.Serializable;

/**
 * This is the class that represents the image object
 */
public class Image implements Serializable{
    /** Image uri **/
    public final String uri;


    /* Time taken to download that image **/
    public final float downloadTime;

    /**
     * Constructs the image
     * @param uri The uri that indicated the location of image in the device
     * @param downloadTime The time taken to download that particular image
     */
    public Image(String uri, long downloadTime) {
        this.uri = uri;
        this.downloadTime = downloadTime;
    }

    /**
     * This is the function that returns the uri of the image
     * @return
     */
    public Uri getUri(){
        return Uri.parse(uri);
    }
}
