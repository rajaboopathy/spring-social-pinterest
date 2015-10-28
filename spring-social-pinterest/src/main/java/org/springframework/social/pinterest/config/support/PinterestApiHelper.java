package org.springframework.social.pinterest.config.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.pinterest.api.PinInterest;

/**
 * Created by Rajaboopathy Vijay on 10/28/15.
 */
public class PinterestApiHelper implements ApiHelper<PinInterest> {

    private final UsersConnectionRepository usersConnectionRepository;

    private final UserIdSource userIdSource;

    public PinterestApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userIdSource = userIdSource;
    }

    public PinInterest getApi() {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting API binding instance for Pinterest");
        }

        Connection<PinInterest> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId()).findPrimaryConnection(PinInterest.class);
        if (logger.isDebugEnabled() && connection == null) {
            logger.debug("No current connection; Returning default PinterestTemplate instance.");
        }
        return connection != null ? connection.getApi() : null;
    }

    private final static Log logger = LogFactory.getLog(PinterestApiHelper.class);


}
