package org.springframework.social.pinterest.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.pinterest.api.Board;
import org.springframework.social.pinterest.api.Pin;
import org.springframework.social.pinterest.api.User;

/**
 * Created by Rajaboopathy Vijay on 10/26/15.
 */
public class PinterestModule extends SimpleModule {

    public PinterestModule() {
        super("PinterestModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(User.class, UserMixin.class);
        context.setMixInAnnotations(Board.class, BoardMixin.class);
        context.setMixInAnnotations(Pin.class, PinMixin.class);
    }
}
