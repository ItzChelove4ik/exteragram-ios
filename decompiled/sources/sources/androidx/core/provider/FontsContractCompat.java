package androidx.core.provider;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.camera.core.ImageCapture$$ExternalSyntheticBackport0;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Preconditions;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.MediaError;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class FontsContractCompat {

    public static class FontFamilyResult {
        private final List<FontInfo[]> mFonts;
        private final int mStatusCode;

        public FontFamilyResult(int r1, List<FontInfo[]> r2) {
            this.mStatusCode = r1;
            this.mFonts = r2;
        }

        @Deprecated
        public FontFamilyResult(int r1, FontInfo[] r2) {
            this.mStatusCode = r1;
            this.mFonts = Collections.singletonList(r2);
        }

        public static FontFamilyResult create(int r1, List<FontInfo[]> r2) {
            return new FontFamilyResult(r1, r2);
        }

        public static FontFamilyResult create(int r1, FontInfo[] r2) {
            return new FontFamilyResult(r1, r2);
        }

        public FontInfo[] getFonts() {
            return this.mFonts.get(0);
        }

        public List<FontInfo[]> getFontsWithFallbacks() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public boolean hasFallback() {
            if (this.mFonts.size() <= 1) goto L5;
            return true;
        L5:
            return false;
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final String mVariationSettings;
        private final int mWeight;

        public FontInfo(Uri r1, int r2, int r3, boolean r4, String r5, int r6) {
            this.mUri = (Uri) Preconditions.checkNotNull(r1);
            this.mTtcIndex = r2;
            this.mWeight = r3;
            this.mItalic = r4;
            this.mVariationSettings = r5;
            this.mResultCode = r6;
        }

        public FontInfo(String r3, String r4) {
            this.mUri = new Uri.Builder().scheme("systemfont").authority(r3).build();
            this.mTtcIndex = 0;
            this.mWeight = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
            this.mItalic = false;
            this.mVariationSettings = r4;
            this.mResultCode = 0;
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        public String getSystemFont() {
            if (isSystemFont() == true) goto L5;
            return null;
        L5:
            return this.mUri.getAuthority();
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public String getVariationSettings() {
            return this.mVariationSettings;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public boolean isSystemFont() {
            return Objects.equals(this.mUri.getScheme(), "systemfont");
        }
    }

    /* loaded from: classes3.dex */
    public static class FontRequestCallback {
        public FontRequestCallback() {
        }

        public abstract void onTypefaceRequestFailed(int r1);

        public abstract void onTypefaceRetrieved(Typeface r1);
    }

    public static Typeface buildTypeface(Context r1, CancellationSignal r2, FontInfo[] r3) {
        return TypefaceCompat.createFromFontInfo(r1, r2, r3, 0);
    }

    public static FontFamilyResult fetchFonts(Context r0, CancellationSignal r1, FontRequest r2) {
        return FontProvider.getFontFamilyResult(r0, ImageCapture$$ExternalSyntheticBackport0.m(r2), r1);
    }

    public static Typeface requestFont(Context r1, List<FontRequest> r2, int r3, boolean r4, int r5, Handler r6, FontRequestCallback r7) {
        CallbackWrapper r0 = new CallbackWrapper(r7, RequestExecutor.createHandlerExecutor(r6));
        if (r4 == false) goto L11;
        if (r2.size() <= 1) goto L7;
        f$$ExternalSyntheticBUOutline1.m("Fallbacks with blocking fetches are not supported for performance reasons");
        return null;
    L7:
        return FontRequestWorker.requestFontSync(r1, r2.get(0), r0, r3, r5);
    L11:
        return FontRequestWorker.requestFontAsync(r1, r2, r3, null, r0);
    }
}
