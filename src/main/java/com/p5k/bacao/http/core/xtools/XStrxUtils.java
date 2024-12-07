package com.p5k.bacao.http.core.xtools;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XStrxUtils extends StringUtils {

    public static String defaultString(final String str) {
        return StringUtils.defaultString(str, StringPool.EMPTY);
    }
    
    public static String defaultString(final String str, final String defaultStr) {
        return StringUtils.defaultString(str, defaultStr);
    }

    public static List<String> findDifference(List<String> a, List<String> b) {
        Set<String> uniqueElementsInA = new HashSet<>(a);
        uniqueElementsInA.removeAll(b);
        return new ArrayList<>(uniqueElementsInA);
    }

}
