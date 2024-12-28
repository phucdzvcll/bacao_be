package com.p5k.bacao.http.core.util;

import com.p5k.bacao.http.core.xtools.XChecker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component
public class WebUtils {
    private static final List<Locale> LOCALES = Arrays.asList(
            new Locale("en"),
            new Locale("vi"),
            new Locale("kr")
    );

    private static MessageSource messageSource;
    private static LocaleResolver localeResolver;

    public WebUtils(final MessageSource messageSource, final LocaleResolver localeResolver) {
        WebUtils.messageSource = messageSource;
        WebUtils.localeResolver = localeResolver;
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getMessage(final String code, final Object... args) {
        Locale locale = null;
        try {
            HttpServletRequest servletRequest = getRequest();
            String headerLang = servletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
            locale = resolveLocale(headerLang);
        } catch (Exception ignored) {
        }
        // Check in case resolveLocale cannot find in LOCALES
        if (null == locale) {
            locale = LocaleContextHolder.getLocale();
        }
        return messageSource.getMessage(code, args, locale);
    }

    public static String getMessageForSocket(final String code, final Object... args) {
        Locale locale = null;
        try {
            locale = resolveLocale(null);
        } catch (Exception ignored) {
        }
        // Check in case resolveLocale cannot find in LOCALES
        if (null == locale) {
            locale = LocaleContextHolder.getLocale();
        }
        return messageSource.getMessage(code, args, locale);
    }

    public static Locale resolveLocale(String headerLang) {
        if (XChecker.isEmpty(headerLang)) {
            return new Locale("vi");
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(headerLang);
        return Locale.lookup(list, LOCALES);
    }

    public static boolean isRequiredField(final Object dto, final String fieldName) throws
            NoSuchFieldException {
        return dto.getClass().getDeclaredField(fieldName).getAnnotation(NotNull.class) != null;
    }

//    public static String getClientIpAddr() {
//        return XIpUtils.getIpAddr();
//    }
//
//    public static String getClientIpAddr(HttpServletRequest request) {
//        return XIpUtils.getIpAddr(request);
//    }

    /**
     * <p>
     *   Return Language of Locale
     * </p>
     * @author Nien Nguyen Quang
     * @created 2024-May-03
     * @return
     */
    public static String getLang() {
        Locale locale = null;
        try {
            HttpServletRequest servletRequest = getRequest();
            String headerLang = servletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
            locale = resolveLocale(headerLang);
        } catch (Exception ignored) {
            locale = new Locale("vi");
        }
        // Check in case resolveLocale cannot find in LOCALES
        if (XChecker.isNull(locale)) {
            locale = LocaleContextHolder.getLocale();

        }
        return locale.getLanguage();
    }
}
