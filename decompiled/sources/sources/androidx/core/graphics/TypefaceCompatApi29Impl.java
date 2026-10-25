package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import com.google.android.gms.cast.MediaError;
import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    public TypefaceCompatApi29Impl() {
    }

    private Font findBaseFont(FontFamily r5, int r6) {
        if ((r6 & 1) == 0) goto L5;
        int r0 = 700;
    L6:
        int r2 = 1;
        if ((r6 & 2) == 0) goto L9;
        int r62 = 1;
    L10:
        FontStyle r4 = new FontStyle(r0, r62);
        Font r63 = r5.getFont(0);
        int r02 = getMatchScore(r4, r63.getStyle());
    L12:
        if (r2 >= r5.getSize()) goto L17;
        Font r1 = r5.getFont(r2);
        int r3 = getMatchScore(r4, r1.getStyle());
        if (r3 >= r02) goto L16;
        r63 = r1;
        r02 = r3;
    L16:
        r2 = r2 + 1;
        goto L12
    L17:
        return r63;
    L9:
        r62 = 0;
        goto L10
    L5:
        r0 = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        goto L6
    }

    private Font getFont(CancellationSignal r2, FontsContractCompat.FontInfo r3, ContentResolver r4) {
        if (r3.isSystemFont() == false) goto L7;
        return getFontFromSystemFont(r3);
    L7:
        return getFontFromProvider(r2, r3, r4);
    }

    private Font getFontFromProvider(CancellationSignal r3, FontsContractCompat.FontInfo r4, ContentResolver r5) {
        ParcelFileDescriptor r32 = r5.openFileDescriptor(r4.getUri(), "r", r3);     // Catch: IOException -> L8
        if (r32 != null) goto L28;
        if (r32 == null) goto L10;
        r32.close();     // Catch: IOException -> L8
        return null;
    L10:
        return null;
    L28:
        Font.Builder r52 = new Font.Builder(r32).setWeight(r4.getWeight()).setSlant(r4.isItalic() ? 1 : 0).setTtcIndex(r4.getTtcIndex());     // Catch: Throwable -> L14
        if (TextUtils.isEmpty(r4.getVariationSettings()) == true) goto L16;
        r52.setFontVariationSettings(r4.getVariationSettings());     // Catch: Throwable -> L14
    L16:
        Font r42 = r52.build();     // Catch: Throwable -> L14
        r32.close();     // Catch: IOException -> L8
        return r42;
    L14:
        th = move-exception;
        r32.close();     // Catch: Throwable -> L21
    L23:
        throw th;     // Catch: IOException -> L8
    L21:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L8
    L8:
        e = move-exception;
        Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
        return null;
    }

    private static int getMatchScore(FontStyle r2, FontStyle r3) {
        int r0 = Math.abs(r2.getWeight() - r3.getWeight()) / 100;
        if (r2.getSlant() != r3.getSlant()) goto L5;
        int r22 = 0;
    L7:
        return r0 + r22;
    L5:
        r22 = 2;
        goto L7
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context r7, FontResourcesParserCompat.FontFamilyFilesResourceEntry r8, Resources r9, int r10) {
        FontResourcesParserCompat.FontFileResourceEntry[] r82 = r8.getEntries();     // Catch: Exception -> L9
        int r0 = r82.length;     // Catch: Exception -> L9
        int r1 = 0;
        FontFamily.Builder r2 = null;
    L4:
        if (r1 >= r0) goto L13;
        FontResourcesParserCompat.FontFileResourceEntry r3 = r82[r1];     // Catch: Exception -> L9
        Font r32 = new Font.Builder(r9, r3.getResourceId()).setWeight(r3.getWeight()).setSlant(r3.isItalic() ? 1 : 0).setTtcIndex(r3.getTtcIndex()).setFontVariationSettings(r3.getVariationSettings()).build();     // Catch: Exception -> L9 IOException -> L19
        if (r2 != null) goto L11;
        r2 = new FontFamily.Builder(r32);     // Catch: Exception -> L9 IOException -> L19
        goto L12
    L11:
        r2.addFont(r32);     // Catch: Exception -> L9 IOException -> L19
    L12:
        r1 = r1 + 1;
        goto L4
    L13:
        if (r2 != null) goto L15;
        return null;
    L15:
        FontFamily r83 = r2.build();     // Catch: Exception -> L9
        return new Typeface.CustomFallbackBuilder(r83).setStyle(findBaseFont(r83, r10).getStyle()).build();
    L9:
        e = move-exception;
        Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context r2, CancellationSignal r3, FontsContractCompat.FontInfo[] r4, int r5) {
        FontFamily r22 = getFontFamily(r3, r4, r2.getContentResolver());     // Catch: Exception -> L8
        if (r22 != null) goto L6;
        return null;
    L6:
        return new Typeface.CustomFallbackBuilder(r22).setStyle(findBaseFont(r22, r5).getStyle()).build();
    L8:
        e = move-exception;
        Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfoWithFallback(Context r6, CancellationSignal r7, List<FontsContractCompat.FontInfo[]> r8, int r9) {
        ContentResolver r62 = r6.getContentResolver();
        FontFamily r0 = getFontFamily(r7, r8.get(0), r62);     // Catch: Exception -> L12
        if (r0 != null) goto L6;
        return null;
    L6:
        Typeface.CustomFallbackBuilder r2 = new Typeface.CustomFallbackBuilder(r0);     // Catch: Exception -> L12
        int r3 = 1;
    L8:
        if (r3 >= r8.size()) goto L15;
        FontFamily r4 = getFontFamily(r7, r8.get(r3), r62);     // Catch: Exception -> L12
        if (r4 == null) goto L14;
        r2.addCustomFallback(r4);     // Catch: Exception -> L12
    L14:
        r3 = r3 + 1;     // Catch: Exception -> L12
        goto L8
    L15:
        return r2.setStyle(findBaseFont(r0, r9).getStyle()).build();
    L12:
        e = move-exception;
        Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromResourcesFontFile(Context r1, Resources r2, int r3, String r4, int r5) {
        Font r0 = new Font.Builder(r2, r3).build();     // Catch: Exception -> L4
        return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(r0).build()).setStyle(r0.getStyle()).build();
    L4:
        e = move-exception;
        Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
        return null;
    }

    public FontFamily getFontFamily(CancellationSignal r6, FontsContractCompat.FontInfo[] r7, ContentResolver r8) {
        int r0 = r7.length;
        int r2 = 0;
        FontFamily.Builder r3 = null;
    L3:
        if (r2 >= r0) goto L11;
        Font r4 = getFont(r6, r7[r2], r8);
        if (r4 == null) goto L10;
        if (r3 != null) goto L9;
        r3 = new FontFamily.Builder(r4);
        goto L10
    L9:
        r3.addFont(r4);
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        if (r3 != null) goto L14;
        return null;
    L14:
        return r3.build();
    }

    public Font getFontFromSystemFont(FontsContractCompat.FontInfo r1) {
        throw new UnsupportedOperationException("Getting font from Typeface is not supported before API31");
    }
}
