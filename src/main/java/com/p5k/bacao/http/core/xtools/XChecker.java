package com.p5k.bacao.http.core.xtools;

import cn.hutool.core.util.BooleanUtil;
import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.exception.ServiceException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;

@UtilityClass
public class XChecker {

    public static void isTrueThruMsg(boolean isTrue, String message) {
        if (isTrue) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        }
    }

    public static void isTrueThruMsg(boolean isTrue, ServiceCodeEnum serviceCodeEnum) {
        if (isTrue) {
            throw new ServiceException(serviceCodeEnum);
        }
    }

    public static void isFalseThruMsg(boolean isFalse, String message) {
        if (!isFalse) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        }
    }

    public static void isFalseThruMsg(boolean isFalse, ServiceCodeEnum responseCodeEnum) {
        if (!isFalse) {
            throw new ServiceException(responseCodeEnum);
        }
    }

    /**
     * * Determine whether a Collection is empty, including ListSetQueue
     *
     * @param coll Collection to be judged
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    public static void isEmptyThruMsg(Collection<?> coll) {
        isEmptyThruMsg(coll, "Collection is Empty");
    }

    public static void isEmptyThruMsg(Collection<?> coll, String message) {
        isTrueThruMsg(isEmpty(coll), message);
    }

    /**
     * * Determine whether a Collection is non-empty and contains ListSetQueue
     *
     * @param coll Collection to be judged
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * * Determine whether an object array is empty
     *
     * @param objects The array of objects to be judged * @return true: empty false: not empty
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    public static void isEmptyThruMsg(Object[] objects) {
        isEmptyThruMsg(objects, "Object is Empty");
    }

    public static void isEmptyThruMsg(Object[] objects, String message) {
        isTrueThruMsg(isEmpty(objects), message);
    }

    /**
     * * Determine whether an object array is not empty
     *
     * @param objects The array of objects to be judged
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * * Determine whether a Map is empty
     *
     * @param map Map to be judged
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    public static void isEmptyThruMsg(Map<?, ?> map) {
        isEmptyThruMsg(map, "Map is Empty");
    }

    public static void isEmptyThruMsg(Map<?, ?> map, String message) {
        isTrueThruMsg(isEmpty(map), message);
    }

    /**
     * * Determine whether a Map is empty
     *
     * @param map Map to be judged
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * * Determine whether a string is empty
     *
     * @param str String
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || XStrxUtils.isEmpty(str);
    }

    public static void isEmptyThruMsg(String str) {
        isEmptyThruMsg(str, "Object is Empty");
    }

    public static void isEmptyThruMsg(String str, String message) {
        isTrueThruMsg(isEmpty(str), message);
    }

    public static void isEmptyThruMsg(String str, ServiceCodeEnum serviceCodeEnum) {
        isTrueThruMsg(isEmpty(str), serviceCodeEnum);
    }

    public static void isEmptyThruMsg(ServiceCodeEnum serviceCodeEnum, String ...strs) {
        isEmptyThruMsg(strs);
        for (String str : strs) {
            isTrueThruMsg(isEmpty(str), serviceCodeEnum);
        }

    }

    /**
     * * Determine whether a string is non-empty
     *
     * @param str String
     * @return true: non-empty string false: empty string
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Determine whether the given object is empty: i.e. {@code null} or of zero length.
     *
     * @param obj the object to check
     * @return Whether the array is empty
     */
    public static boolean isEmpty(@Nullable Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    public static void isEmptyThruMsg(@Nullable Object obj) {
        isEmptyThruMsg(obj, "Object is Empty");
    }

    public static void isEmptyThruMsg(@Nullable Object obj, String message) {
        isTrueThruMsg(isEmpty(obj), message);
    }

    /**
     * Determine whether the given object is not empty: i.e. {@code null} or of zero length.
     *
     * @param obj the object to check
     * @return boolean
     */
    public static boolean isNotEmpty(@Nullable Object obj) {
        return !ObjectUtils.isEmpty(obj);
    }

    /**
     * * Determine whether an object is empty
     *
     * @param object Object
     * @return true: empty false: not empty
     */
    public static boolean isNull(@Nullable Object object) {
        return XObjectUtils.allNull(object);
    }

    public static void isNullThruMsg(@Nullable Object object) {
        isNullThruMsg(object, "Object is Null");
    }

    public static void isNullThruMsg(@Nullable Object object, String message) {
        isTrueThruMsg(isNull(object), message);
    }

    public static void isNullThruMsg(@Nullable Object object, ServiceCodeEnum serviceCodeEnum) {
        isTrueThruMsg(isNull(object), serviceCodeEnum);
    }

    /**
     * * Determine whether an object is not empty
     *
     * @param object Object
     * @return true: not empty false: empty
     */
    public static boolean isNotNull(@Nullable Object object) {
        return !isNull(object);
    }

    /**
     * * Determine whether an object is an array type (array of Java basic types)
     *
     * @param object object
     * @return true: it is an array false: it is not an array
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && ObjectUtils.isArray(object);
    }

    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than 0, and it contains at
     * least one non-whitespace character.
     * <pre class="code">
     * $.isBlank(null)		= true
     * $.isBlank("")		= true
     * $.isBlank(" ")		= true
     * $.isBlank("12345")	= false
     * $.isBlank(" 12345 ")	= false
     * </pre>
     *
     * @param cs the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null}, its length is greater
     * than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean isBlank(@Nullable final CharSequence cs) {
        return XStrxUtils.isBlank(cs);
    }

    public static void isBlankThruMsg(@Nullable final CharSequence cs) {
        isBlankThruMsg(cs, "Object is Blank");
    }

    public static void isBlankThruMsg(@Nullable final CharSequence cs, String message) {
        isTrueThruMsg(isBlank(cs), message);
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     * <pre>
     * $.isNotBlank(null)	= false
     * $.isNotBlank("")		= false
     * $.isNotBlank(" ")	= false
     * $.isNotBlank("bob")	= true
     * $.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is not empty and not null and not whitespace
     * @see Character#isWhitespace
     */
    public static boolean isNotBlank(@Nullable final CharSequence cs) {
        return isNotBlank(cs);
    }

    /**
     * There is any Blank
     *
     * @param css CharSequence
     * @return boolean
     */
    public static boolean isAnyBlank(final CharSequence... css) {
        return StringUtils.isAnyBlank(css);
    }

    public static void isAnyBlankThruMsg(final CharSequence... css) {
        isAnyBlankThruMsg("Has Blank", css);
    }

    public static void isAnyBlankThruMsg(String message, final CharSequence... css) {
        isTrueThruMsg(isAnyBlank(css), message);
    }

    public static boolean isAnyEmpty(final CharSequence... css) {
        return StringUtils.isAnyEmpty(css);
    }

    public static void isAnyEmptyThruMsg(final CharSequence... css) {
        isAnyEmptyThruMsg("Has Empty", css);
    }

    public static void isAnyEmptyThruMsg(String message, final CharSequence... css) {
        isTrueThruMsg(isAnyEmpty(css), message);
    }

    /**
     * Whether all blank
     *
     * @param css CharSequence
     * @return boolean
     */
    public static boolean isNoneBlank(final CharSequence... css) {
        return isNoneBlank(css);
    }

    /**
     * Whether there is an Empty Object in the object group
     *
     * @param os object group
     * @return boolean
     */
    public static boolean hasEmpty(Object... os) {
        for (Object o : os) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether there is a String has Length
     *
     * @param str char
     * @return boolean
     */
    public static boolean hasLength(CharSequence str) {
        return ((isNotEmpty(str)) && (str.length() > 0));
    }

    /**
     * Whether there is a String has Length
     *
     * @param str char
     * @return boolean
     */
    public static boolean hasLength(String str) {
        return hasLength(str);
    }

    /**
     * Whether there is a String has Length
     *
     * @param str char
     * @return boolean
     */
    public static boolean hasText(CharSequence str) {
        if (!(hasLength(str))) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; ++i) {
            if (!(Character.isWhitespace(str.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether there is a String has Length
     *
     * @param str char
     * @return boolean
     */
    public static boolean hasText(String str) {
        return hasText(str);
    }

    /**
     * Whether there is a String has containt Whitespace
     *
     * @param str char
     * @return boolean
     */
    public static boolean hasContainsWhitespace(CharSequence str) {
        if (!(hasLength(str))) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; ++i) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether there is a String has containt Whitespace
     *
     * @param str char
     * @return boolean
     */
    public static boolean hasContainsWhitespace(String str) {
        return hasContainsWhitespace(str);
    }


    /**
     * Whether all the objects in the group are Empty Object
     *
     * @param os object group
     * @return boolean
     */
    public static boolean allEmpty(Object... os) {
        for (Object o : os) {
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine whether a string is a number
     *
     * @param cs the CharSequence to check, may be null
     * @return {boolean}
     */
    public static boolean isNumeric(final CharSequence cs) {
        if (isBlank(cs)) {
            return false;
        }
        for (int i = cs.length(); --i >= 0; ) {
            int chr = cs.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }


    /**
     * Intercept different parts of the two strings (the same length), and determine whether the
     * intercepted substrings are the same.<br> If any string is null, return false
     *
     * @param str1       first string
     * @param start1     The position where the first string starts
     * @param str2       second string
     * @param start2     The position where the second string starts
     * @param length     interception length
     * @param ignoreCase whether to ignore case
     * @return are the substrings the same
     * @since 3.2.1
     */
    public static boolean isSubEquals(CharSequence str1, int start1, CharSequence str2, int start2,
                                      int length, boolean ignoreCase) {
        if (isNull(str1) || isNull(str2)) {
            return false;
        }
        return str1.toString().regionMatches(ignoreCase, start1, str2.toString(), start2, length);
    }

    /**
     * Compare whether two objects are equal. <br> There are two same conditions, and one of them can
     * be satisfied:<br>
     *
     * @param obj1 object 1
     * @param obj2 object 2
     * @return is equal
     */
    public static boolean equals(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }

    /**
     * Compare two strings (case sensitive).
     *
     * <pre>
     * equalsIgnoreCase(null, null)   = true
     * equalsIgnoreCase(null, &quot;abc&quot;)  = false
     * equalsIgnoreCase(&quot;abc&quot;, null)  = false
     * equalsIgnoreCase(&quot;abc&quot;, &quot;abc&quot;) = true
     * equalsIgnoreCase(&quot;abc&quot;, &quot;ABC&quot;) = true
     * </pre>
     *
     * @param str1 String to compare 1
     * @param str2 String to compare 2
     * @return If the two strings are the same or both are <code>null</code>, then return
     * <code>true</code>
     */
    public static boolean equals(CharSequence str1, CharSequence str2) {
        return equals(str1, str2, false);
    }

    /**
     * Compare two strings (case sensitive).
     *
     * <pre>
     * equalsIgnoreCase(null, null)   = true
     * equalsIgnoreCase(null, &quot;abc&quot;)  = false
     * equalsIgnoreCase(&quot;abc&quot;, null)  = false
     * equalsIgnoreCase(&quot;abc&quot;, &quot;abc&quot;) = true
     * equalsIgnoreCase(&quot;abc&quot;, &quot;ABC&quot;) = true
     * </pre>
     *
     * @param str1       String to compare 1
     * @param str2       String to compare 2
     * @param ignoreCase Boolean to compare 2
     * @return If the two strings are the same or both are <code>null</code>, then return
     * <code>true</code>
     */
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (isNull(str1)) {
            // Only when both are null can it be judged equal
            return str2 == null;
        }
        if (isNull(str2)) {
            // String 2 is empty, string 1 is not empty, directly false
            return false;
        }

        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.equals(str2);
        }
    }

    /**
     * Determine if the given objects are equal, returning {@code true} if both are {@code null} or
     * {@code false} if only one is {@code null}.
     * <p>Compares arrays with {@code Arrays.equals}, performing an equality
     * check based on the array elements rather than the array reference.
     *
     * @param o1 first Object to compare
     * @param o2 second Object to compare
     * @return whether the given objects are equal
     * @see Object#equals(Object)
     * @see Arrays#equals
     */
    public static boolean equalsSafe(@Nullable Object o1, @Nullable Object o2) {
        return ObjectUtils.nullSafeEquals(o1, o2);
    }

    /**
     * Check whether the given Iterator contains the given element.
     *
     * @param iterator the Iterator to check
     * @param element  the element to look for
     * @return {@code true} if found, {@code false} otherwise
     */
    public static boolean contains(@Nullable Iterator<?> iterator, Object element) {
        return XColsUtils.contains(iterator, element);
    }

    /**
     * Check whether the given Enumeration contains the given element.
     *
     * @param enumeration the Enumeration to check
     * @param element     the element to look for
     * @return {@code true} if found, {@code false} otherwise
     */
    public static boolean contains(@Nullable Enumeration<?> enumeration, Object element) {
        return XColsUtils.contains(enumeration, element);
    }

    public static boolean isEmpty(Map rowMap, String paramStr) throws Exception {
        if (!rowMap.containsKey(paramStr)) {
            return true;
        }

        Object obj = rowMap.get(paramStr);
        if (obj == null) {
            return true;
        }

        String str = "";
        if (obj instanceof BigDecimal) {
            str = obj.toString();
        } else if (obj instanceof Double) {
            str = obj.toString();
        } else if (obj instanceof Integer) {
            str = ((Integer) obj).toString();
        } else if (obj instanceof String) {
            str = (String) obj;
        } else if (obj instanceof Date) {
            str = obj.toString();
        } else {
            throw new Exception("Not a comparable data type");
        }

        return XStrxUtils.isEmpty(str) || "NULL".equals(str) || "null".equals(str);
    }

    public static boolean isEqual(Map map1, String param1, Map map2, String param2) throws Exception {
        return XChecker.isNotEmpty(map1, param1)
                && XChecker.isNotEmpty(map2, param2)
                && map1.get(param1).equals(map2.get(param2));
    }

    public static boolean isNotEmpty(Map rowMap, String paramStr) throws Exception {
        return !XChecker.isEmpty(rowMap, paramStr);
    }

    public static boolean isNotEqual(Map map1, String param1, Map map2, String param2)
            throws Exception {
        return !XChecker.isEqual(map1, param1, map2, param2);
    }

    public static boolean isOverSize(String obj, int size) {
        if (isNull(obj) || isEmpty(obj) || isEmpty(size)) {
            return true;
        }
        return obj.length() > size;
    }

    public static void isOverSizeThruMsg(String obj, int size, String message) {
        isTrueThruMsg(isOverSize(obj, size), message);
    }

    public static boolean isOverSize(int obj, int size) {
        if (isNull(obj) || isEmpty(obj) || isEmpty(size)) {
            return true;
        }
        return obj > size;
    }

    public static void isOverSizeThruMsg(int obj, int size, String message) {
        isTrueThruMsg(isOverSize(obj, size), message);
    }

    public static boolean isExistIn(final Set<String> setStr, String item) {
        return setStr.contains(item);
    }

    public static boolean isExistIn(final ArrayList setStr, String item) {
        return setStr.contains(item);
    }

    public static boolean isExistIn(final List<String> items, String item) {
        return items.contains(item);
    }

    public static boolean isExistIn(final String setStr, String item) {
        String splitter = ",";
        return (isOverSize(setStr, 300)) ?
                isExistInLarge(setStr, splitter, item) : isExistInSmall(setStr, splitter, item);
    }

    public static boolean isExistIn(final String setStr, String splitter, String item) {
        return (isOverSize(setStr, 300)) ?
                isExistInLarge(setStr, splitter, item) : isExistInSmall(setStr, splitter, item);
    }

    public static boolean isExistInSmall(final String setStr, String splitter, String item) {
        return isExistIn(Arrays.asList(StringUtils.split(setStr, splitter)), item);
    }

    public static boolean isExistInLarge(final String setStr, String splitter, String item) {
        return isExistIn(new HashSet(Arrays.asList(StringUtils.split(setStr, splitter))), item);
    }

    /**
     * Check all boolean input is true.
     *
     * @param boolVals  boolean input values
     * @return          all values true: True else False
     */
    public static boolean isAllTrue(boolean... boolVals) {
        return BooleanUtil.and(boolVals);
    }

    /**
     * Check one boolean input is true.
     *
     * @param boolVals  boolean input values
     * @return          one values true: True else False
     */
    public static boolean isOneTrue(boolean... boolVals) {
        return BooleanUtil.or(boolVals);
    }
}
