package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes3.dex */
public abstract class TypedArrayUtils {
    public static boolean getNamedBoolean(TypedArray r0, XmlPullParser r1, String r2, int r3, boolean r4) {
        if (hasAttribute(r1, r2) == true) goto L6;
        return r4;
    L6:
        return r0.getBoolean(r3, r4);
    }

    public static int getNamedColor(TypedArray r0, XmlPullParser r1, String r2, int r3, int r4) {
        if (hasAttribute(r1, r2) == true) goto L6;
        return r4;
    L6:
        return r0.getColor(r3, r4);
    }

    public static ColorStateList getNamedColorStateList(TypedArray r1, XmlPullParser r2, Resources.Theme r3, String r4, int r5) {
        if (hasAttribute(r2, r4) == false) goto L16;
        TypedValue r22 = new TypedValue();
        r1.getValue(r5, r22);
        int r42 = r22.type;
        if (r42 == 2) goto L15;
        if (r42 < 28) goto L13;
        if (r42 > 31) goto L13;
        return getNamedColorStateListFromInt(r22);
    L13:
        return ColorStateListInflaterCompat.inflate(r1.getResources(), r1.getResourceId(r5, 0), r3);
    L15:
        throw new UnsupportedOperationException("Failed to resolve attribute at index " + r5 + ": " + r22);
    L16:
        return null;
    }

    private static ColorStateList getNamedColorStateListFromInt(TypedValue r0) {
        return ColorStateList.valueOf(r0.data);
    }

    public static ComplexColorCompat getNamedComplexColor(TypedArray r1, XmlPullParser r2, Resources.Theme r3, String r4, int r5, int r6) {
        if (hasAttribute(r2, r4) == false) goto L14;
        TypedValue r22 = new TypedValue();
        r1.getValue(r5, r22);
        int r42 = r22.type;
        if (r42 >= 28) goto L7;
    L10:
        ComplexColorCompat r12 = ComplexColorCompat.inflate(r1.getResources(), r1.getResourceId(r5, 0), r3);
        if (r12 == null) goto L14;
        return r12;
    L7:
        if (r42 > 31) goto L10;
        return ComplexColorCompat.from(r22.data);
    L14:
        return ComplexColorCompat.from(r6);
    }

    public static float getNamedFloat(TypedArray r0, XmlPullParser r1, String r2, int r3, float r4) {
        if (hasAttribute(r1, r2) == true) goto L6;
        return r4;
    L6:
        return r0.getFloat(r3, r4);
    }

    public static int getNamedInt(TypedArray r0, XmlPullParser r1, String r2, int r3, int r4) {
        if (hasAttribute(r1, r2) == true) goto L6;
        return r4;
    L6:
        return r0.getInt(r3, r4);
    }

    public static boolean hasAttribute(XmlPullParser r1, String r2) {
        if (r1.getAttributeValue("http://schemas.android.com/apk/res/android", r2) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static TypedArray obtainAttributes(Resources r0, Resources.Theme r1, AttributeSet r2, int[] r3) {
        if (r1 != null) goto L6;
        return r0.obtainAttributes(r2, r3);
    L6:
        return r1.obtainStyledAttributes(r2, r3, 0, 0);
    }
}
