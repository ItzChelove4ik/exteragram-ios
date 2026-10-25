package androidx.core.graphics;

import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;

/* loaded from: classes.dex */
public class TypefaceCompatApi31Impl extends TypefaceCompatApi29Impl {
    public TypefaceCompatApi31Impl() {
    }

    private static Typeface getSystemFontFamily(String r2) {
        Typeface r22 = Typeface.create(r2, 0);
        Typeface r0 = Typeface.create(Typeface.DEFAULT, 0);
        if (r22 != null) goto L5;
        return null;
    L5:
        if (r22.equals(r0) == true) goto L9;
        return r22;
    L9:
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatApi29Impl
    public Font getFontFromSystemFont(FontsContractCompat.FontInfo r3) {
        String r2 = r3.getSystemFont();
        if (r2 != null) goto L5;
        return null;
    L5:
        Typeface r22 = getSystemFontFamily(r2);
        if (r22 != null) goto L8;
        return null;
    L8:
        Font r23 = TypefaceCompat.guessPrimaryFont(r22);
        if (r23 != null) goto L12;
        return null;
    L12:
        if (TextUtils.isEmpty(r3.getVariationSettings()) == false) goto L18;
        return r23;
    L18:
        return new Font.Builder(r23).setFontVariationSettings(r3.getVariationSettings()).build();
    L16:
        Log.e("TypefaceCompatApi31Impl", "Failed to clone Font instance. Fall back to provider font.");
        return null;
    }
}
