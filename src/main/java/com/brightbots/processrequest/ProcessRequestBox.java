package com.brightbots.processrequest;

import com.box.sdk.BoxFile;
import com.box.sdk.BoxFile.Info;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxSharedLink;
import com.brightbots.box.BoxAuth;
import com.brightbots.dto.webhook.RequestWebwook;
import com.brightbots.dto.RestfulResponse;
import com.brightbots.util.UtilBox;
import com.brightbots.util.UtilRestful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class ProcessRequestBox {

    private static final Logger log = Logger.getLogger(ProcessRequestBox.class.getName());

    @Autowired
    private BoxAuth boxAuth;

    @Autowired
    private UtilRestful utilRestful;

    @Autowired
    private UtilBox utilBox;

    protected BoxFile boxFile;
    protected BoxFolder rootFolder;
    protected BoxSharedLink boxSharedLink;

    protected Info newFileInfo;

    protected String sharedLinkURL;
    protected String listSharedLink;

    public ResponseEntity<RestfulResponse> generateSharedLinkWebhook(RequestWebwook requestWebwook) {
        try {
            if (requestWebwook.getSource().getType().equalsIgnoreCase("FOLDER")) {
                log.info("Initializing main process: " + requestWebwook.getSource().getId());
                rootFolder = new BoxFolder(boxAuth.getApi(), requestWebwook.getSource().getId());
                listSharedLink = utilBox.listSharedLinksOnFolder(rootFolder);
                return new ResponseEntity<>(utilRestful.getOkResponse(listSharedLink, "none", "Shared links obtained correctly"), HttpStatus.OK);
            } else if (requestWebwook.getSource().getType().equalsIgnoreCase("FILE")) {
                log.info("Initializing main process: " + requestWebwook.getSource().getType());
                boxFile = new BoxFile(boxAuth.getApi(), requestWebwook.getSource().getId());
                log.info("Creating box file");
                if (boxFile.getInfo() != null && boxFile.getInfo().getSharedLink() != null && boxFile.getInfo().getSharedLink().getURL() != null) {
                    sharedLinkURL = boxFile.getInfo().getSharedLink().getURL();
                    log.info("Finished process to obtain shared folder completed.");
                    return new ResponseEntity<>(utilRestful.getOkResponse(sharedLinkURL, "none", "Shared links obtained correctly"), HttpStatus.OK);
                } else {
                    boxSharedLink = boxFile.createSharedLink(utilBox.createSharedLinkNonExpiracy());
                    return new ResponseEntity<>(utilRestful.getOkResponse(boxSharedLink.getURL(), "none", "Shared links obtained correctly. Created a new one fr this File."), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(utilRestful.getErrorResponse("Resource type are not allowed yet. RT=" + requestWebwook.getSource().getType() + " Its null or empty. Values (Info, SharedLink, URL)", null, null, "Change resource type for someone supported.", "Sorry the requested item its not allowed for the solution yet."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(utilRestful.getErrorResponse("Exception generated on the service", ex.toString(), ex.getMessage(), "Repeat or finished the process", "Sorry service unavailable. Please contact support admin."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<RestfulResponse> generateSharedLinkExpiry(String fileId, Boolean expiracy) {
        try {
            log.info("Initializing main process: " + fileId);
            boxFile = new BoxFile(boxAuth.getApi(), fileId);
            log.info("Creating box file");
            if(expiracy){
                boxSharedLink = boxFile.createSharedLink(utilBox.createSharedLinkExpiracy());
            } else {
                boxSharedLink = boxFile.createSharedLink(utilBox.createSharedLinkNonExpiracy());
            }
            log.info("Finished process to obtain shared folder completed.");
            return new ResponseEntity<>(utilRestful.getOkResponse(boxSharedLink.getURL(), "none", "Shared links obtained correctly"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(utilRestful.getErrorResponse("Exception generated on the service", ex.toString(), ex.getMessage(), "Repeat or finished the process", "Sorry service unavailable. Please contact support admin."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void saveBoxFile(FileInputStream fileInputStream, String fileName, String folderId) throws IOException {
        try {
            log.info("Initializing saving file: " + fileName);
            rootFolder = new BoxFolder(boxAuth.getApi(), folderId);
            newFileInfo = rootFolder.uploadFile(fileInputStream, fileName);
            log.info("Finished process to obtain shared folder completed. FileID: " + newFileInfo.getID());
        } catch (Exception ex) {
            log.info("Exception generated on the Scheduler: " + ex.toString() + " - " + ex.getMessage());
        } finally {
            fileInputStream.close();
        }
    }
}
