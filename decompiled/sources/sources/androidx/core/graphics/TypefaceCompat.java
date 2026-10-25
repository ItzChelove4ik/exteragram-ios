package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.text.PositionedGlyphs;
import android.graphics.text.TextRunShaper;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import androidx.tracing.Trace;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.IOException;
import java.util.List;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes.dex */
public abstract class TypefaceCompat {
    private static Paint sCachedPaint;
    private static final LruCache<String, Typeface> sTypefaceCache = null;
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl = null;

    /* loaded from: classes3.dex */
    public static class ResourcesCallbackAdapter extends FontsContractCompat.FontRequestCallback {
        private ResourcesCompat.FontCallback mFontCallback;

        public ResourcesCallbackAdapter(ResourcesCompat.FontCallback r1) {
            this.mFontCallback = r1;
        }

        @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
        public void onTypefaceRequestFailed(int r1) {
            ResourcesCompat.FontCallback r0 = this.mFontCallback;
            if (r0 == null) goto L6;
            r0.onFontRetrievalFailed(r1);
            return;
        }

        @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
        public void onTypefaceRetrieved(Typeface r1) {
            ResourcesCompat.FontCallback r0 = this.mFontCallback;
            if (r0 == null) goto L6;
            r0.onFontRetrieved(r1);
            return;
        }
    }

    static {
        Trace.beginSection("TypefaceCompat static init");
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L6;
        sTypefaceCompatImpl = new TypefaceCompatApi31Impl();
    L18:
        sTypefaceCache = new LruCache(16);
        sCachedPaint = null;
        Trace.endSection();
        return;
    L6:
        if (r0 < 29) goto L9;
        sTypefaceCompatImpl = new TypefaceCompatApi29Impl();
        goto L18
    L9:
        if (r0 < 28) goto L12;
        sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        goto L18
    L12:
        if (r0 < 26) goto L15;
        sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        goto L18
    L15:
        if (TypefaceCompatApi24Impl.isUsable() == false) goto L17;
        sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
        goto L18
    L17:
        sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        goto L18
    }

    public static Typeface create(Context r0, Typeface r1, int r2) {
        if (r0 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("Context cannot be null");
        return null;
    L4:
        return Typeface.create(r1, r2);
    }

    public static Typeface createFromFontInfo(Context r1, CancellationSignal r2, FontsContractCompat.FontInfo[] r3, int r4) {
        Trace.beginSection("TypefaceCompat.createFromFontInfo");
        Typeface r12 = sTypefaceCompatImpl.createFromFontInfo(r1, r2, r3, r4);     // Catch: Throwable -> L6
        Trace.endSection();
        return r12;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static Typeface createFromFontInfoWithFallback(Context r1, CancellationSignal r2, List<FontsContractCompat.FontInfo[]> r3, int r4) {
        Trace.beginSection("TypefaceCompat.createFromFontInfoWithFallback");
        Typeface r12 = sTypefaceCompatImpl.createFromFontInfoWithFallback(r1, r2, r3, r4);     // Catch: Throwable -> L6
        Trace.endSection();
        return r12;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static Typeface createFromResourcesFamilyXml(Context r12, FontResourcesParserCompat.FamilyResourceEntry r13, Resources r14, int r15, String r16, int r17, int r18, ResourcesCompat.FontCallback r19, Handler r20, boolean r21) {
        if ((r13 instanceof FontResourcesParserCompat.ProviderResourceEntry) == false) goto L23;
        FontResourcesParserCompat.ProviderResourceEntry r132 = (FontResourcesParserCompat.ProviderResourceEntry) r13;
        Typeface r2 = getSystemFontFamilyWithFallback(r132);
        if (r2 == null) goto L11;
        if (r19 == null) goto L8;
        r19.callbackSuccessAsync(r2, r20);
    L8:
        sTypefaceCache.put(createResourceUid(r14, r15, r16, r17, r18), r2);
        return r2;
    L11:
        if (r21 == true) goto L13;
        if (r19 == null) goto L14;
    L15:
        boolean r8 = false;
    L18:
        if (r21 == false) goto L21;
        int r22 = r132.getTimeout();
    L20:
        int r9 = r22;
        Typeface r122 = FontsContractCompat.requestFont(r12, r132.getRequests(), r18, r8, r9, ResourcesCompat.FontCallback.getHandler(r20), new ResourcesCallbackAdapter(r19));
    L28:
        if (r122 == null) goto L30;
        sTypefaceCache.put(createResourceUid(r14, r15, r16, r17, r18), r122);
    L30:
        return r122;
    L21:
        r22 = -1;
    L14:
        r8 = true;
        goto L18
    L13:
        if (r132.getFetchStrategy() != 0) goto L15;
    L23:
        r122 = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(r12, (FontResourcesParserCompat.FontFamilyFilesResourceEntry) r13, r14, r18);
        if (r19 == null) goto L28;
        if (r122 == null) goto L27;
        r19.callbackSuccessAsync(r122, r20);
        goto L28
    L27:
        r19.callbackFailAsync(-3, r20);
        goto L28
    }

    public static Typeface createFromResourcesFontFile(Context r6, Resources r7, int r8, String r9, int r10, int r11) {
        Typeface r62 = sTypefaceCompatImpl.createFromResourcesFontFile(r6, r7, r8, r9, r11);
        if (r62 == null) goto L5;
        String r72 = createResourceUid(r7, r8, r9, r10, r11);
        sTypefaceCache.put(r72, r62);
    L5:
        return r62;
    }

    private static String createResourceUid(Resources r1, int r2, String r3, int r4, int r5) {
        return r1.getResourcePackageName(r2) + SignatureVisitor.SUPER + r3 + SignatureVisitor.SUPER + r4 + SignatureVisitor.SUPER + r2 + SignatureVisitor.SUPER + r5;
    }

    public static Typeface findFromCache(Resources r1, int r2, String r3, int r4, int r5) {
        return sTypefaceCache.get(createResourceUid(r1, r2, r3, r4, r5));
    }

    public static Typeface getSystemFontFamily(String r3) {
        if (r3 != null) goto L5;
    L12:
        return null;
    L5:
        if (r3.isEmpty() == true) goto L12;
        Typeface r32 = Typeface.create(r3, 0);
        Typeface r1 = Typeface.create(Typeface.DEFAULT, 0);
        if (r32 == null) goto L12;
        if (r32.equals(r1) == true) goto L12;
        return r32;
    }

    private static Typeface getSystemFontFamilyWithFallback(FontResourcesParserCompat.ProviderResourceEntry r8) {
        String r0 = r8.getSystemFontFamilyName();
        if (TextUtils.isEmpty(r0) == true) goto L7;
        Typeface r02 = getSystemFontFamily(r0);
        if (r02 == null) goto L7;
        return r02;
    L7:
        List<FontRequest> r82 = r8.getRequests();
        int r1 = 0;
        if (r82.size() != 1) goto L12;
        return getSystemFontFamily(r82.get(0).getSystemFont());
    L12:
        if (Build.VERSION.SDK_INT >= 31) goto L14;
        return null;
    L14:
        int r03 = 0;
    L16:
        if (r03 >= r82.size()) goto L21;
        if (getSystemFontFamily(r82.get(r03).getSystemFont()) == null) goto L19;
        r03 = r03 + 1;
        goto L16
    L19:
        return null;
    L21:
        Typeface.CustomFallbackBuilder r04 = null;
    L23:
        if (r1 >= r82.size()) goto L45;
        FontRequest r3 = r82.get(r1);
        if (r1 == (r82.size() - 1)) goto L27;
    L29:
        Font r5 = guessPrimaryFont(getSystemFontFamily(r3.getSystemFont()));
        if (r5 == null) goto L31;
        if (TextUtils.isEmpty(r3.getVariationSettings()) == false) goto L46;
        FontFamily r32 = TypefaceCompat$$ExternalSyntheticApiModelOutline1.m(r5).build();
    L40:
        if (r04 != null) goto L42;
        r04 = TypefaceCompat$$ExternalSyntheticApiModelOutline2.m(r32);
    L43:
        r1 = r1 + 1;
        goto L23
    L42:
        r04.addCustomFallback(r32);
        goto L43
    L46:
        TypefaceCompat$$ExternalSyntheticApiModelOutline3.m();     // Catch: IOException -> L37
        TypefaceCompat$$ExternalSyntheticApiModelOutline4.m();     // Catch: IOException -> L37
        r32 = TypefaceCompat$$ExternalSyntheticApiModelOutline1.m(TypefaceCompat$$ExternalSyntheticApiModelOutline0.m(r5).setFontVariationSettings(r3.getVariationSettings()).build()).build();     // Catch: IOException -> L37
    L37:
        Log.e("TypefaceCompat", "Failed to clone Font instance. Fall back to provider font.");
        return null;
    L31:
        Log.w("TypefaceCompat", "Unable identify the primary font for " + r3.getSystemFont() + ". Falling back to provider font.");
        return null;
    L27:
        if (TextUtils.isEmpty(r3.getVariationSettings()) == false) goto L29;
        r04.setSystemFallback(r3.getSystemFont());
    L45:
        return r04.build();
    }

    public static Font guessPrimaryFont(Typeface r10) {
        if (sCachedPaint != null) goto L5;
        sCachedPaint = new Paint();
    L5:
        sCachedPaint.setTextSize(10.0f);
        sCachedPaint.setTypeface(r10);
        PositionedGlyphs r102 = TextRunShaper.shapeTextRun(" ", 0, 1, 0, 1, 0.0f, 0.0f, false, sCachedPaint);
        if (r102.glyphCount() != 0) goto L10;
        return null;
    L10:
        return r102.getFont(0);
    }
}
