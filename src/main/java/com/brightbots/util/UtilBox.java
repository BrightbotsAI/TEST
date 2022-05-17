package com.brightbots.util;

import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxSharedLink;
import com.box.sdk.sharedlink.BoxSharedLinkRequest;
import com.brightbots.box.BoxAuth;
import com.brightbots.processrequest.ProcessRequestBox;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class UtilBox {

    private static final Logger log = Logger.getLogger(UtilBox.class.getName());

    @Autowired
    private Environment env;

    @Autowired
    private BoxAuth boxAuth;

    protected BoxFile boxFile;
    protected BoxSharedLink boxSharedLink;
    protected BoxSharedLinkRequest sharedLinkRequest;
    protected Date expiracyDate;

    protected String listSharedLink;
    protected List<String> sharedLinks;


    public BoxSharedLinkRequest createSharedLinkNonExpiracy() {
        sharedLinkRequest = new BoxSharedLinkRequest()
                .access(BoxSharedLink.Access.OPEN)
                .permissions(true, true);
        return sharedLinkRequest;
    }

    public BoxSharedLinkRequest createSharedLinkExpiracy() {
        expiracyDate = Calendar.getInstance().getTime();
        expiracyDate = DateUtils.addHours(expiracyDate, Integer.parseInt(Objects.requireNonNull(env.getProperty("box.shared.link.expyracy.hours"))));
        sharedLinkRequest = new BoxSharedLinkRequest()
                .access(BoxSharedLink.Access.OPEN)
                .permissions(true, true).unsharedDate(expiracyDate);
        return sharedLinkRequest;
    }

    public String listSharedLinksOnFolder(BoxFolder rootFolder) {
        sharedLinks = new ArrayList<>();
        listSharedLink = "";
        rootFolder.forEach(itemInfo -> {
            if (itemInfo instanceof BoxFile.Info) {
                boxFile = new BoxFile(boxAuth.getApi(), itemInfo.getID());
                boxSharedLink = boxFile.createSharedLink(createSharedLinkNonExpiracy());
                sharedLinks.add("- " + boxSharedLink.getURL());
                log.info("ADDING: " + boxSharedLink.getURL());
            } else if (itemInfo instanceof BoxFolder.Info) {
                BoxFolder insideFolder = new BoxFolder(boxAuth.getApi(), itemInfo.getID());
                listSharedLinksOnSubFolder(insideFolder, sharedLinks);
            }
        });
        sharedLinks.forEach(linkInd -> {
            listSharedLink = listSharedLink + linkInd;
        });
        return listSharedLink;
    }

    public void listSharedLinksOnSubFolder(BoxFolder rootFolder, List<String> sharedLinksProcessed) {
        rootFolder.forEach(itemInfo -> {
            if (itemInfo instanceof BoxFile.Info) {
                boxFile = new BoxFile(boxAuth.getApi(), itemInfo.getID());
                boxSharedLink = boxFile.createSharedLink(createSharedLinkNonExpiracy());
                sharedLinksProcessed.add("- " + boxSharedLink.getURL());
                log.info("ADDING: " + boxSharedLink.getURL());
            } else if (itemInfo instanceof BoxFolder.Info) {
                BoxFolder insideFolder = new BoxFolder(boxAuth.getApi(), itemInfo.getID());
                listSharedLinksOnSubFolder(insideFolder, sharedLinksProcessed);
            }
        });
    }
}
