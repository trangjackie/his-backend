package com.vns.his.gateway;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class UrlPathFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        String requestURI = RequestContext.getCurrentContext().getRequest().getRequestURI();
        return requestURI.matches("/v3/api-docs/.+");
    }

    @Override
    public Object run() {
        String requestURI = RequestContext.getCurrentContext().getRequest().getRequestURI();
		
		//extract the last one by the way, that the service name
        String servicePattern = "/v3/api-docs/(?<group>.+)";
        Pattern compile = Pattern.compile(servicePattern);
        Matcher matcher = compile.matcher(requestURI);

        String group = "";
        while (matcher.find()) {
            group = matcher.group("group");
        }

		//Rewrite routing
        String path = "/" + group + "/v3/api-docs";

        RequestContext context = RequestContext.getCurrentContext();
        context.put(FilterConstants.REQUEST_URI_KEY, path);
        return null;
    }
}