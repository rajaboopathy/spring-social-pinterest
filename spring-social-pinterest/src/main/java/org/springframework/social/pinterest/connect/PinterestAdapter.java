package org.springframework.social.pinterest.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.pinterest.api.PinInterest;
import org.springframework.social.pinterest.api.User;

/**
 * Created by Rajaboopathy Vijay on 10/28/15.
 */
public class PinterestAdapter implements ApiAdapter<PinInterest> {
    @Override
    public boolean test(PinInterest pinInterest) {
        try {
            pinInterest.getUserOperations().getUserProfile();
            return true;
        } catch (ApiException e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(PinInterest pinInterest, ConnectionValues values) {
        User profile = pinInterest.fetchObject("me",User.class);
        values.setProviderUserId(profile.getId());
        values.setDisplayName(profile.getUserName());
        values.setProfileUrl(profile.getUrl());
    }

    @Override
    public UserProfile fetchUserProfile(PinInterest pinInterest) {
        User profile = pinInterest.getUserOperations().getUserProfile();
        return  new UserProfileBuilder().setName(profile.getUserName()).setFirstName(profile.getFirstName()).setLastName(profile.getLastName()).build();
    }

    @Override
    public void updateStatus(PinInterest pinInterest, String message) {
      //Do nothing on updates
    }
}
