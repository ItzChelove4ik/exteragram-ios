package org.aspectj.runtime.reflect;

import java.lang.reflect.Modifier;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
class StringMaker {
    static StringMaker longStringMaker;
    static StringMaker middleStringMaker;
    static StringMaker shortStringMaker;
    int cacheOffset;
    boolean includeArgs;
    boolean includeEnclosingPoint;
    boolean includeJoinPointTypeName;
    boolean includeModifiers;
    boolean includeThrows;
    boolean shortKindName;
    boolean shortPrimaryTypeNames;
    boolean shortTypeNames;

    static {
        StringMaker r0 = new StringMaker();
        shortStringMaker = r0;
        r0.shortTypeNames = true;
        r0.includeArgs = false;
        r0.includeThrows = false;
        r0.includeModifiers = false;
        r0.shortPrimaryTypeNames = true;
        r0.includeJoinPointTypeName = false;
        r0.includeEnclosingPoint = false;
        r0.cacheOffset = 0;
        StringMaker r02 = new StringMaker();
        middleStringMaker = r02;
        r02.shortTypeNames = true;
        r02.includeArgs = true;
        r02.includeThrows = false;
        r02.includeModifiers = false;
        r02.shortPrimaryTypeNames = false;
        shortStringMaker.cacheOffset = 1;
        StringMaker r03 = new StringMaker();
        longStringMaker = r03;
        r03.shortTypeNames = false;
        r03.includeArgs = true;
        r03.includeThrows = false;
        r03.includeModifiers = true;
        r03.shortPrimaryTypeNames = false;
        r03.shortKindName = false;
        r03.cacheOffset = 2;
    }

    public StringMaker() {
        this.shortTypeNames = true;
        this.includeArgs = true;
        this.includeThrows = false;
        this.includeModifiers = false;
        this.shortPrimaryTypeNames = false;
        this.includeJoinPointTypeName = true;
        this.includeEnclosingPoint = true;
        this.shortKindName = true;
    }

    public void addSignature(StringBuffer r2, Class[] r3) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (this.includeArgs == false) goto L7;
        r2.append("(");
        addTypeNames(r2, r3);
        r2.append(")");
        return;
    L7:
        if (r3.length != 0) goto L10;
        r2.append("()");
        return;
    L10:
        r2.append("(..)");
    }

    public void addThrows(StringBuffer r2, Class[] r3) {
        if (this.includeThrows == false) goto L10;
        if (r3 != null) goto L6;
        return;
    L6:
        if (r3.length == 0) goto L12;
        r2.append(" throws ");
        addTypeNames(r2, r3);
        return;
    L12:
        return;
    }

    public void addTypeNames(StringBuffer r3, Class[] r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L8;
        if (r0 <= 0) goto L7;
        r3.append(", ");
    L7:
        r3.append(makeTypeName(r4[r0]));
        r0 = r0 + 1;
        goto L4
    }

    public String makeKindName(String r2) {
        int r1 = r2.lastIndexOf(45);
        if (r1 != (-1)) goto L6;
        return r2;
    L6:
        return r2.substring(r1 + 1);
    }

    public String makeModifiersString(int r2) {
        if (this.includeModifiers == true) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r1 = Modifier.toString(r2);
        if (r1.length() != 0) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return r1.concat(" ");
    }

    public String makePrimaryTypeName(Class r2, String r3) {
        return makeTypeName(r2, r3, this.shortPrimaryTypeNames);
    }

    public String makeTypeName(Class r3) {
        return makeTypeName(r3, r3.getName(), this.shortTypeNames);
    }

    public String makeTypeName(Class r2, String r3, boolean r4) {
        if (r2 != null) goto L6;
        return "ANONYMOUS";
    L6:
        if (r2.isArray() == false) goto L10;
        Class<?> r22 = r2.getComponentType();
        StringBuffer r32 = new StringBuffer();
        r32.append(makeTypeName(r22, r22.getName(), r4));
        r32.append(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
        return r32.toString();
    L10:
        if (r4 == false) goto L14;
        return stripPackageName(r3).replace(Typography.dollar, '.');
    L14:
        return r3.replace(Typography.dollar, '.');
    }

    public String stripPackageName(String r2) {
        int r1 = r2.lastIndexOf(46);
        if (r1 != (-1)) goto L6;
        return r2;
    L6:
        return r2.substring(r1 + 1);
    }
}
