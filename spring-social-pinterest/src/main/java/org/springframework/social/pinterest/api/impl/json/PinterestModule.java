package org.springframework.social.pinterest.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.pinterest.api.User;

/**
 * Created by dfc677 on 10/26/15.
 */
public class PinterestModule extends SimpleModule {

    public PinterestModule() {
        super("PinterestModule");
    }

    @Override
    public void  setupModule(SetupContext context)
    {
       context.setMixInAnnotations(User.class,UserMixin.class);
    }
}
