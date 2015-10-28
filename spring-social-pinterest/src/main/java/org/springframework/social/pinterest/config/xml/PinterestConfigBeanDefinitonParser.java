package org.springframework.social.pinterest.config.xml;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.config.xml.AbstractProviderConfigNamespaceHandler;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.pinterest.config.support.PinterestApiHelper;
import org.springframework.social.pinterest.connect.PinterestConnectionFactory;
import org.springframework.social.pinterest.security.PinterestAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

import java.util.Map;

/**
 * Created by Rajaboopathy Vijay on 10/28/15.
 */
public class PinterestConfigBeanDefinitonParser extends AbstractProviderConfigBeanDefinitionParser {

    public PinterestConfigBeanDefinitonParser() {
        super(PinterestConnectionFactory.class, PinterestApiHelper.class);
    }

    @Override
    protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
        return PinterestAuthenticationService.class;
    }

    @Override
    protected BeanDefinition getConnectionFactoryBeanDefinition(String appId, String appSecret, Map<String, Object> allAttributes) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(PinterestConnectionFactory.class).addConstructorArgValue(appId).addConstructorArgValue(appSecret);
        if (allAttributes.containsKey("app-namespace")) {
            builder.addConstructorArgValue(allAttributes.get("app-namespace"));
        }
        return builder.getBeanDefinition();
    }

}
