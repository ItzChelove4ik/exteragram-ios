package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import androidx.core.R$styleable;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
abstract class GradientColorInflaterCompat {

    public static final class ColorStops {
        final int[] mColors;
        final float[] mOffsets;

        public ColorStops(int r1, int r2) {
            this.mColors = new int[]{r1, r2};
            this.mOffsets = new float[]{0.0f, 1.0f};
        }

        public ColorStops(int r1, int r2, int r3) {
            this.mColors = new int[]{r1, r2, r3};
            this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
        }

        public ColorStops(List<Integer> r5, List<Float> r6) {
            int r0 = r5.size();
            this.mColors = new int[r0];
            this.mOffsets = new float[r0];
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.mColors[r1] = r5.get(r1).intValue();
            this.mOffsets[r1] = r6.get(r1).floatValue();
            r1 = r1 + 1;
            goto L3
        }
    }

    private static ColorStops checkColors(ColorStops r0, int r1, int r2, boolean r3, int r4) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        if (r3 == false) goto L8;
        return new ColorStops(r1, r4, r2);
    L8:
        return new ColorStops(r1, r2);
    }

    public static Shader createFromXmlInner(Resources r20, XmlPullParser r21, AttributeSet r22, Resources.Theme r23) {
        String r1 = r21.getName();
        if (r1.equals("gradient") == false) goto L19;
        TypedArray r12 = TypedArrayUtils.obtainAttributes(r20, r23, r22, R$styleable.GradientColor);
        float r9 = TypedArrayUtils.getNamedFloat(r12, r21, "startX", R$styleable.GradientColor_android_startX, 0.0f);
        float r10 = TypedArrayUtils.getNamedFloat(r12, r21, "startY", R$styleable.GradientColor_android_startY, 0.0f);
        float r11 = TypedArrayUtils.getNamedFloat(r12, r21, "endX", R$styleable.GradientColor_android_endX, 0.0f);
        float r122 = TypedArrayUtils.getNamedFloat(r12, r21, "endY", R$styleable.GradientColor_android_endY, 0.0f);
        float r14 = TypedArrayUtils.getNamedFloat(r12, r21, "centerX", R$styleable.GradientColor_android_centerX, 0.0f);
        float r15 = TypedArrayUtils.getNamedFloat(r12, r21, "centerY", R$styleable.GradientColor_android_centerY, 0.0f);
        int r5 = TypedArrayUtils.getNamedInt(r12, r21, "type", R$styleable.GradientColor_android_type, 0);
        int r6 = TypedArrayUtils.getNamedColor(r12, r21, "startColor", R$styleable.GradientColor_android_startColor, 0);
        boolean r7 = TypedArrayUtils.hasAttribute(r21, "centerColor");
        int r2 = TypedArrayUtils.getNamedColor(r12, r21, "centerColor", R$styleable.GradientColor_android_centerColor, 0);
        int r3 = TypedArrayUtils.getNamedColor(r12, r21, "endColor", R$styleable.GradientColor_android_endColor, 0);
        int r4 = TypedArrayUtils.getNamedInt(r12, r21, "tileMode", R$styleable.GradientColor_android_tileMode, 0);
        float r8 = TypedArrayUtils.getNamedFloat(r12, r21, "gradientRadius", R$styleable.GradientColor_android_gradientRadius, 0.0f);
        r12.recycle();
        ColorStops r0 = checkColors(inflateChildElements(r20, r21, r22, r23), r6, r3, r7, r2);
        if (r5 == 1) goto L13;
        if (r5 == 2) goto L11;
        return new LinearGradient(r9, r10, r11, r122, r0.mColors, r0.mOffsets, parseTileMode(r4));
    L11:
        return new SweepGradient(r14, r15, r0.mColors, r0.mOffsets);
    L13:
        if (r8 <= 0.0f) goto L17;
        return new RadialGradient(r14, r15, r8, r0.mColors, r0.mOffsets, parseTileMode(r4));
    L17:
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    L19:
        throw new XmlPullParserException(r21.getPositionDescription() + ": invalid gradient color tag " + r1);
    }

    private static ColorStops inflateChildElements(Resources r8, XmlPullParser r9, AttributeSet r10, Resources.Theme r11) {
        int r0 = r9.getDepth() + 1;
        ArrayList r2 = new ArrayList(20);
        ArrayList r4 = new ArrayList(20);
    L3:
        int r3 = r9.next();
        if (r3 == 1) goto L23;
        int r5 = r9.getDepth();
        if (r5 >= r0) goto L10;
        if (r3 == 3) goto L23;
    L10:
        if (r3 != 2) goto L3;
        if (r5 > r0) goto L3;
        if (r9.getName().equals(PluginsConstants.Settings.ITEM) == false) goto L3;
        TypedArray r32 = TypedArrayUtils.obtainAttributes(r8, r11, r10, R$styleable.GradientColorItem);
        boolean r52 = r32.hasValue(R$styleable.GradientColorItem_android_color);
        boolean r6 = r32.hasValue(R$styleable.GradientColorItem_android_offset);
        if (r52 == false) goto L21;
        if (r6 == false) goto L21;
        int r53 = r32.getColor(R$styleable.GradientColorItem_android_color, 0);
        float r62 = r32.getFloat(R$styleable.GradientColorItem_android_offset, 0.0f);
        r32.recycle();
        r4.add(Integer.valueOf(r53));
        r2.add(Float.valueOf(r62));
    L21:
        throw new XmlPullParserException(r9.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
    L23:
        if (r4.size() > 0) goto L25;
        return null;
    L25:
        return new ColorStops(r4, r2);
    }

    private static Shader.TileMode parseTileMode(int r1) {
        if (r1 == 1) goto L11;
        if (r1 == 2) goto L9;
        return Shader.TileMode.CLAMP;
    L9:
        return Shader.TileMode.MIRROR;
    L11:
        return Shader.TileMode.REPEAT;
    }
}
