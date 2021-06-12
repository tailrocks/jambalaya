package com.zhokhov.jambalaya.tenancy;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * @author Alexey Zhokhov
 */
public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(@Nullable CharSequence str) {
        return !isEmpty(str);
    }

    public static boolean hasText(@Nullable CharSequence str) {
        if (isEmpty(str)) {
            return false;
        }

        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
