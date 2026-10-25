package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.R$attr;
import androidx.core.R$styleable;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.plugins.PluginsConstants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public abstract class ColorStateListInflaterCompat {
    private static final ThreadLocal<TypedValue> sTempTypedValue = null;

    static {
        sTempTypedValue = new ThreadLocal();
    }

    public static ColorStateList createFromXml(Resources r4, XmlPullParser r5, Resources.Theme r6) {
        AttributeSet r0 = Xml.asAttributeSet(r5);
    L3:
        int r1 = r5.next();
        if (r1 == 2) goto L8;
        if (r1 != 1) goto L3;
    L8:
        if (r1 != 2) goto L12;
        return createFromXmlInner(r4, r5, r0, r6);
    L12:
        throw new XmlPullParserException("No start tag found");
    }

    public static ColorStateList createFromXmlInner(Resources r2, XmlPullParser r3, AttributeSet r4, Resources.Theme r5) {
        String r0 = r3.getName();
        if (r0.equals(PluginsConstants.Settings.TYPE_SELECTOR) == false) goto L7;
        return inflate(r2, r3, r4, r5);
    L7:
        throw new XmlPullParserException(r3.getPositionDescription() + ": invalid color state list tag " + r0);
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> r0 = sTempTypedValue;
        TypedValue r1 = r0.get();
        if (r1 != null) goto L6;
        TypedValue r12 = new TypedValue();
        r0.set(r12);
        return r12;
    L6:
        return r1;
    }

    public static ColorStateList inflate(Resources r0, int r1, Resources.Theme r2) {
        return createFromXml(r0, r0.getXml(r1), r2);
    L4:
        e = move-exception;
        Log.e("CSLCompat", "Failed to inflate ColorStateList.", e);
        return null;
    }

    private static ColorStateList inflate(Resources r17, XmlPullParser r18, AttributeSet r19, Resources.Theme r20) {
        Resources r0 = r17;
        int r4 = 1;
        int r3 = r18.getDepth() + 1;
        int[][] r6 = new int[20][];
        int[] r5 = new int[20];
        int r8 = 0;
    L3:
        int r9 = r18.next();
        if (r9 == r4) goto L53;
        int r10 = r18.getDepth();
        if (r10 >= r3) goto L10;
        if (r9 == 3) goto L53;
    L10:
        if (r9 != 2) goto L52;
        if (r10 > r3) goto L52;
        if (r18.getName().equals(PluginsConstants.Settings.ITEM) == false) goto L52;
        TypedArray r92 = obtainAttributes(r0, r20, r19, R$styleable.ColorStateListItem);
        int r102 = r92.getResourceId(R$styleable.ColorStateListItem_android_color, -1);
        if (r102 != (-1)) goto L18;
    L22:
        int r103 = r92.getColor(R$styleable.ColorStateListItem_android_color, -65281);
    L23:
        float r12 = 1.0f;
        if (r92.hasValue(R$styleable.ColorStateListItem_android_alpha) == false) goto L27;
        r12 = r92.getFloat(R$styleable.ColorStateListItem_android_alpha, 1.0f);
    L30:
        if (Build.VERSION.SDK_INT >= 31) goto L32;
    L34:
        float r11 = r92.getFloat(R$styleable.ColorStateListItem_lStar, -1.0f);
    L35:
        r92.recycle();
        int r93 = r19.getAttributeCount();
        int[] r13 = new int[r93];
        int r14 = 0;
        int r15 = 0;
    L36:
        if (r14 >= r93) goto L51;
        int r42 = r19.getAttributeNameResource(r14);
        if (r42 == 16843173) goto L50;
        if (r42 == 16843551) goto L50;
        if (r42 == R$attr.alpha) goto L50;
        if (r42 == R$attr.lStar) goto L50;
        int r7 = r15 + 1;
        if (r19.getAttributeBooleanValue(r14, false) == true) goto L49;
        r42 = -r42;
    L49:
        r13[r15] = r42;
        r15 = r7;
    L50:
        r14 = r14 + 1;
        goto L36
    L51:
        int[] r02 = StateSet.trimStateSet(r13, r15);
        r5 = GrowingArrayUtils.append(r5, r8, modulateColorAlpha(r103, r12, r11));
        r6 = (int[][]) GrowingArrayUtils.append(r6, r8, r02);
        r8 = r8 + 1;
        goto L52
    L32:
        if (r92.hasValue(R$styleable.ColorStateListItem_android_lStar) == false) goto L34;
        r11 = r92.getFloat(R$styleable.ColorStateListItem_android_lStar, -1.0f);
        goto L35
    L27:
        if (r92.hasValue(R$styleable.ColorStateListItem_alpha) == false) goto L30;
        r12 = r92.getFloat(R$styleable.ColorStateListItem_alpha, 1.0f);
        goto L30
    L18:
        if (isColorInt(r0, r102) == true) goto L22;
        r103 = createFromXml(r0, r0.getXml(r102), r20).getDefaultColor();     // Catch: Exception -> L21
    L21:
        r103 = r92.getColor(R$styleable.ColorStateListItem_android_color, -65281);
    L52:
        r4 = 1;
        r0 = r17;
    L53:
        int[] r03 = new int[r8];
        int[][] r1 = new int[r8][];
        System.arraycopy(r5, 0, r03, 0, r8);
        System.arraycopy(r6, 0, r1, 0, r8);
        return new ColorStateList(r1, r03);
    }

    private static boolean isColorInt(Resources r2, int r3) {
        TypedValue r0 = getTypedValue();
        r2.getValue(r3, r0, true);
        int r22 = r0.type;
        if (r22 >= 28) goto L5;
        return false;
    L5:
        if (r22 > 31) goto L9;
        return true;
    L9:
        return false;
    }

    private static int modulateColorAlpha(int r3, float r4, float r5) {
        if (r5 >= 0.0f) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (r4 != 1.0f) goto L12;
        if (r0 == true) goto L12;
        return r3;
    L12:
        int r42 = MathUtils.clamp((int) ((Color.alpha(r3) * r4) + 0.5f), 0, 255);
        if (r0 == false) goto L16;
        CamColor r32 = CamColor.fromColor(r3);
        r3 = CamColor.toColor(r32.getHue(), r32.getChroma(), r5);
    L16:
        return (r3 & 16777215) | (r42 << 24);
    L5:
        if (r5 > 100.0f) goto L7;
        r0 = true;
        goto L9
    }

    private static TypedArray obtainAttributes(Resources r0, Resources.Theme r1, AttributeSet r2, int[] r3) {
        if (r1 != null) goto L6;
        return r0.obtainAttributes(r2, r3);
    L6:
        return r1.obtainStyledAttributes(r2, r3, 0, 0);
    }
}
