package com.google.common.base;

/* loaded from: classes5.dex */
public abstract class CharMatcher implements Predicate<Character> {

    public static abstract class FastMatcher extends CharMatcher {
        public FastMatcher() {
        }

        @Override // com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character r1) {
            return super.apply(r1);
        }
    }

    public static final class Is extends FastMatcher {
        private final char match;

        public Is(char r1) {
            this.match = r1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char r1) {
            if (r1 != this.match) goto L6;
            return true;
        L6:
            return false;
        }

        public String toString() {
            return "CharMatcher.is('" + CharMatcher.access$100(this.match) + "')";
        }
    }

    public static abstract class NamedFastMatcher extends FastMatcher {
        private final String description;

        public NamedFastMatcher(String r1) {
            this.description = (String) Preconditions.checkNotNull(r1);
        }

        public final String toString() {
            return this.description;
        }
    }

    public static final class None extends NamedFastMatcher {
        static final CharMatcher INSTANCE = null;

        static {
            INSTANCE = new None();
        }

        private None() {
            super("CharMatcher.none()");
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence r1, int r2) {
            Preconditions.checkPositionIndex(r2, r1.length());
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char r1) {
            return false;
        }
    }

    public CharMatcher() {
    }

    public static /* synthetic */ String access$100(char r0) {
        return showCharacter(r0);
    }

    public static CharMatcher is(char r1) {
        return new Is(r1);
    }

    public static CharMatcher none() {
        return None.INSTANCE;
    }

    private static String showCharacter(char r6) {
        char[] r0 = new char[6];
        int r2 = 0;
        r0[0] = '\\';
        r0[1] = 'u';
        r0[2] = 0;
        r0[3] = 0;
        r0[4] = 0;
        r0[5] = 0;
    L3:
        if (r2 >= 4) goto L6;
        r0[5 - r2] = "0123456789ABCDEF".charAt(r6 & 15);
        r6 = (char) (r6 >> 4);
        r2 = r2 + 1;
        goto L3
    L6:
        return String.copyValueOf(r0);
    }

    @Deprecated
    public boolean apply(Character r1) {
        return matches(r1.charValue());
    }

    public int indexIn(CharSequence r3, int r4) {
        int r0 = r3.length();
        Preconditions.checkPositionIndex(r4, r0);
    L3:
        if (r4 >= r0) goto L8;
        if (matches(r3.charAt(r4)) == true) goto L6;
        r4 = r4 + 1;
        goto L3
    L6:
        return r4;
    L8:
        return -1;
    }

    public abstract boolean matches(char r1);
}
