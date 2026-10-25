package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.exteragram.messenger.plugins.PluginsConstants;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public final class ComplexColorCompat {
    private int mColor;
    private final ColorStateList mColorStateList;
    private final Shader mShader;

    private ComplexColorCompat(Shader r1, ColorStateList r2, int r3) {
        this.mShader = r1;
        this.mColorStateList = r2;
        this.mColor = r3;
    }

    private static ComplexColorCompat createFromXml(Resources r4, int r5, Resources.Theme r6) {
        XmlResourceParser r52 = r4.getXml(r5);
        AttributeSet r0 = Xml.asAttributeSet(r52);
    L3:
        int r1 = r52.next();
        if (r1 == 2) goto L8;
        if (r1 != 1) goto L3;
    L8:
        if (r1 != 2) goto L20;
        String r12 = r52.getName();
        r12.getClass();
        if (r12.equals("gradient") == true) goto L18;
        if (r12.equals(PluginsConstants.Settings.TYPE_SELECTOR) == false) goto L16;
        return from(ColorStateListInflaterCompat.createFromXmlInner(r4, r52, r0, r6));
    L16:
        throw new XmlPullParserException(r52.getPositionDescription() + ": unsupported complex color tag " + r12);
    L18:
        return from(GradientColorInflaterCompat.createFromXmlInner(r4, r52, r0, r6));
    L20:
        throw new XmlPullParserException("No start tag found");
    }

    public static ComplexColorCompat from(int r2) {
        return new ComplexColorCompat(null, null, r2);
    }

    public static ComplexColorCompat from(ColorStateList r3) {
        return new ComplexColorCompat(null, r3, r3.getDefaultColor());
    }

    public static ComplexColorCompat from(Shader r3) {
        return new ComplexColorCompat(r3, null, 0);
    }

    public static ComplexColorCompat inflate(Resources r0, int r1, Resources.Theme r2) {
        return createFromXml(r0, r1, r2);
    L4:
        e = move-exception;
        Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
        return null;
    }

    public int getColor() {
        return this.mColor;
    }

    public Shader getShader() {
        return this.mShader;
    }

    public boolean isGradient() {
        if (this.mShader == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isStateful() {
        if (this.mShader != null) goto L10;
        ColorStateList r1 = this.mColorStateList;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isStateful() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean onStateChanged(int[] r3) {
        if (isStateful() == false) goto L8;
        ColorStateList r0 = this.mColorStateList;
        int r32 = r0.getColorForState(r3, r0.getDefaultColor());
        if (r32 == this.mColor) goto L10;
        this.mColor = r32;
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    public void setColor(int r1) {
        this.mColor = r1;
    }

    public boolean willDraw() {
        if (isGradient() == false) goto L5;
        return true;
    L5:
        if (this.mColor != 0) goto L11;
        return false;
    L11:
        return true;
    }
}
