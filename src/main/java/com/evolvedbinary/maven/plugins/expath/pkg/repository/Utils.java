package com.evolvedbinary.maven.plugins.expath.pkg.repository;

import javax.annotation.Nullable;

public class Utils {
    public static boolean isEmpty(@Nullable final String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNonEmpty(@Nullable final String s) {
        return s != null && !s.isEmpty();
    }
}
