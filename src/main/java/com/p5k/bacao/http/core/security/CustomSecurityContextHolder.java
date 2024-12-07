package com.p5k.bacao.http.core.security;

import cn.hutool.core.convert.Convert;
import com.p5k.bacao.http.core.xtools.XChecker;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import com.google.gson.internal.LinkedTreeMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomSecurityContextHolder {

    private static final ThreadLocal<Claims> customerContext = new ThreadLocal<>();
    private static final String ID = "id";
    private static final String USER_NAME = "sub";
    public static final String AUTHORITIES = "authorities";

    private CustomSecurityContextHolder() {
    }

    public static void setCustomerContext(Claims claims) {
        customerContext.set(claims);
    }

    public static Claims getCustomerContext() {
        return customerContext.get();
    }

    public static void clear() {
        customerContext.remove();
    }

    public static String getUserId() {
        if (XChecker.isNotNull(customerContext.get()))
            return Convert.convert(String.class, customerContext.get().get(ID));

        return StringUtils.EMPTY;
    }

    public static String getUsername() {
        if (XChecker.isNotNull(customerContext.get()))
            return Convert.convert(String.class, customerContext.get().get(USER_NAME));
        return StringUtils.EMPTY;
    }

    public static Set<String> getAuthorities() {
        Set<String> authoritiesSet = new HashSet<>();
        if (XChecker.isNotNull(customerContext.get()) && XChecker.isNotNull(customerContext.get().get(AUTHORITIES))) {
            List<LinkedTreeMap<String, String>> authorities = (List<LinkedTreeMap<String, String>>) customerContext.get().get(AUTHORITIES);
            authorities.stream().forEach(authorityMap -> {
                String authority = authorityMap.get("role");
                if (XChecker.isNotNull(authority)) {
                    authoritiesSet.add(authority);
                }
            });
        }
        return authoritiesSet;

    }

}
