package com.brightbots.box;

import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;


/**
 * This class create the main connection to the Box API
 * for subsequent consumptions.
 *
 * <p>
 * By Brightbots Inc. All rights reserved.
 * <p>
 *
 * @since   2022-01-24
 * @version 1.0
 * @author  Cristian Perez
 *
 */
@Component
public class BoxAuth {

    /**
     * Object for Start connection to the BOX API.
     */
    protected BoxDeveloperEditionAPIConnection api;
    /**
     * BufferedReader for read BoxConfig JSON file.
     */
    private BufferedReader reader;
    /**
     * InputStream for obtain info inside BoxConfig JSON file.
     */
    protected  InputStream inputStream = getClass().getResourceAsStream("/BoxConfig2.json");

    /**
     * Main object that contains the configuration info for connection
     */
    private BoxConfig config;

    /**
     * Class Constructor
     * @throws IOException if the process to obtain JSON config file generate an issue.
     * @see IOException
     */
   public BoxAuth(Environment env) throws IOException {
        if(inputStream != null){
           reader = new BufferedReader(new InputStreamReader(inputStream));
        }
        BoxConfig config = BoxConfig.readFrom(reader);
        api = BoxDeveloperEditionAPIConnection.getAppUserConnection("17629787268", config);
   }

    /**
     * Method to obtain BoxDeveloperEditionAPIConnection for subsequent consumptions.
     * @return BoxDeveloperEditionAPIConnection This returns the created object for consume BOX API.
     */
    public BoxDeveloperEditionAPIConnection getApi() {
        return api;
    }
}
