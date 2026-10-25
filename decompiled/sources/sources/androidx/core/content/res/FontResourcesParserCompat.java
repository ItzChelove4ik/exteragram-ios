package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Base64;
import android.util.Xml;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.core.R$styleable;
import androidx.core.provider.FontRequest;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public abstract class FontResourcesParserCompat {

    public interface FamilyResourceEntry {
    }

    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(FontFileResourceEntry[] r1) {
            this.mEntries = r1;
        }

        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    public static final class FontFileResourceEntry {
        private final String mFileName;
        private final boolean mItalic;
        private final int mResourceId;
        private final int mTtcIndex;
        private final String mVariationSettings;
        private final int mWeight;

        public FontFileResourceEntry(String r1, int r2, boolean r3, String r4, int r5, int r6) {
            this.mFileName = r1;
            this.mWeight = r2;
            this.mItalic = r3;
            this.mVariationSettings = r4;
            this.mTtcIndex = r5;
            this.mResourceId = r6;
        }

        public String getFileName() {
            return this.mFileName;
        }

        public int getResourceId() {
            return this.mResourceId;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
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
    }

    public static final class ProviderResourceEntry implements FamilyResourceEntry {
        private final List<FontRequest> mRequests;
        private final int mStrategy;
        private final String mSystemFontFamilyName;
        private final int mTimeoutMs;

        public ProviderResourceEntry(List<FontRequest> r1, int r2, int r3, String r4) {
            this.mRequests = r1;
            this.mStrategy = r2;
            this.mTimeoutMs = r3;
            this.mSystemFontFamilyName = r4;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        public List<FontRequest> getRequests() {
            return this.mRequests;
        }

        public String getSystemFontFamilyName() {
            return this.mSystemFontFamilyName;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }
    }

    private static int getType(TypedArray r0, int r1) {
        return r0.getType(r1);
    }

    public static FamilyResourceEntry parse(XmlPullParser r3, Resources r4) {
    L2:
        int r0 = r3.next();
        if (r0 == 2) goto L7;
        if (r0 != 1) goto L2;
    L7:
        if (r0 != 2) goto L11;
        return readFamilies(r3, r4);
    L11:
        throw new XmlPullParserException("No start tag found");
    }

    public static List<List<byte[]>> readCerts(Resources r5, int r6) {
        if (r6 == 0) goto L4;
        TypedArray r0 = r5.obtainTypedArray(r6);
    L11:
        th = move-exception;
        r0.recycle();
        throw th;
    L7:
        if (r0.length() != 0) goto L13;
        List<List<byte[]>> r52 = Collections.EMPTY_LIST;     // Catch: Throwable -> L11
        r0.recycle();
        return r52;
    L13:
        ArrayList r1 = new ArrayList();     // Catch: Throwable -> L11
        if (getType(r0, 0) != 1) goto L22;
        int r62 = 0;
    L17:
        if (r62 >= r0.length()) goto L23;
        int r3 = r0.getResourceId(r62, 0);     // Catch: Throwable -> L11
        if (r3 == 0) goto L21;
        r1.add(toByteArrayList(r5.getStringArray(r3)));     // Catch: Throwable -> L11
    L21:
        r62 = r62 + 1;     // Catch: Throwable -> L11
    L23:
        r0.recycle();
        return r1;
    L22:
        r1.add(toByteArrayList(r5.getStringArray(r6)));     // Catch: Throwable -> L11
        goto L23
    L4:
        return Collections.EMPTY_LIST;
    }

    private static FontRequest readFallback(XmlPullParser r8, Resources r9, String r10, String r11, List<List<byte[]>> r12) {
        TypedArray r92 = r9.obtainAttributes(Xml.asAttributeSet(r8), R$styleable.FontFamilyProviderFallback);
        String r4 = r92.getString(R$styleable.FontFamilyProviderFallback_fontProviderQuery);     // Catch: Throwable -> L8
        String r6 = r92.getString(R$styleable.FontFamilyProviderFallback_fontProviderSystemFontFamily);     // Catch: Throwable -> L8
        String r7 = r92.getString(R$styleable.FontFamilyProviderFallback_fontVariationSettings);     // Catch: Throwable -> L8
        if (r4 == null) goto L14;
    L6:
        if (r8.next() == 3) goto L10;
        skip(r8);     // Catch: Throwable -> L8
        goto L6
    L10:
        FontRequest r1 = new FontRequest(r10, r11, r4, r12, r6, r7);     // Catch: Throwable -> L8
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r92);
        return r1;
    L14:
        throw new XmlPullParserException("query attribute must be set in fallback element");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        if (r92 == null) goto L25;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r92);     // Catch: Throwable -> L18
        throw th;
    L18:
        th = move-exception;
        th.addSuppressed(th);
        throw th;
    L25:
        throw th;
    }

    private static FamilyResourceEntry readFamilies(XmlPullParser r3, Resources r4) {
        r3.require(2, null, "font-family");
        if (r3.getName().equals("font-family") == true) goto L5;
        skip(r3);
        return null;
    L5:
        return readFamily(r3, r4);
    }

    private static FamilyResourceEntry readFamily(XmlPullParser r14, Resources r15) {
        TypedArray r0 = r15.obtainAttributes(Xml.asAttributeSet(r14), R$styleable.FontFamily);
        String r3 = r0.getString(R$styleable.FontFamily_fontProviderAuthority);
        String r4 = r0.getString(R$styleable.FontFamily_fontProviderPackage);
        String r5 = r0.getString(R$styleable.FontFamily_fontProviderQuery);
        String r1 = r0.getString(R$styleable.FontFamily_fontProviderFallbackQuery);
        int r2 = r0.getResourceId(R$styleable.FontFamily_fontProviderCerts, 0);
        int r9 = r0.getInteger(R$styleable.FontFamily_fontProviderFetchStrategy, 1);
        int r10 = r0.getInteger(R$styleable.FontFamily_fontProviderFetchTimeout, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        String r11 = r0.getString(R$styleable.FontFamily_fontProviderSystemFontFamily);
        r0.recycle();
        if (r3 == null) goto L27;
        if (r4 == null) goto L27;
        List<List<byte[]>> r6 = readCerts(r15, r2);
        ArrayList r12 = new ArrayList();
    L7:
        if (r14.next() == 3) goto L16;
        if (r14.getEventType() != 2) goto L7;
        if (r14.getName().equals("fallback") == true) goto L13;
        skip(r14);
        goto L7
    L13:
        r12.add(readFallback(r14, r15, r3, r4, r6));
        goto L7
    L16:
        if (r12.isEmpty() == false) goto L18;
        if (r5 == null) goto L25;
        r12.add(new FontRequest(r3, r4, r5, r6, null, null));
        if (r1 == null) goto L24;
        r12.add(new FontRequest(r3, r4, r1, r6, null, null));
    L24:
        return new ProviderResourceEntry(r12, r9, r10, r11);
    L25:
        f$$ExternalSyntheticBUOutline1.m("The provider font XML requires query attribute or fallback children.");
        return null;
    L18:
        return new ProviderResourceEntry(r12, r9, r10, r11);
    L27:
        ArrayList r13 = new ArrayList();
    L29:
        if (r14.next() == 3) goto L38;
        if (r14.getEventType() != 2) goto L29;
        if (r14.getName().equals("font") == true) goto L35;
        skip(r14);
        goto L29
    L35:
        r13.add(readFont(r14, r15));
        goto L29
    L38:
        if (r13.isEmpty() == false) goto L41;
        return null;
    L41:
        return new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) r13.toArray(new FontFileResourceEntry[0]));
    }

    private static FontFileResourceEntry readFont(XmlPullParser r9, Resources r10) {
        TypedArray r102 = r10.obtainAttributes(Xml.asAttributeSet(r9), R$styleable.FontFamilyFont);
        if (r102.hasValue(R$styleable.FontFamilyFont_fontWeight) == false) goto L5;
        int r0 = R$styleable.FontFamilyFont_fontWeight;
    L6:
        int r4 = r102.getInt(r0, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        if (r102.hasValue(R$styleable.FontFamilyFont_fontStyle) == false) goto L9;
        int r02 = R$styleable.FontFamilyFont_fontStyle;
    L11:
        if (1 != r102.getInt(r02, 0)) goto L13;
        boolean r5 = true;
    L15:
        if (r102.hasValue(R$styleable.FontFamilyFont_ttcIndex) == false) goto L17;
        int r03 = R$styleable.FontFamilyFont_ttcIndex;
    L19:
        if (r102.hasValue(R$styleable.FontFamilyFont_fontVariationSettings) == false) goto L21;
        int r2 = R$styleable.FontFamilyFont_fontVariationSettings;
    L22:
        String r6 = r102.getString(r2);
        int r7 = r102.getInt(r03, 0);
        if (r102.hasValue(R$styleable.FontFamilyFont_font) == false) goto L25;
        int r04 = R$styleable.FontFamilyFont_font;
    L26:
        int r8 = r102.getResourceId(r04, 0);
        String r3 = r102.getString(r04);
        r102.recycle();
    L28:
        if (r9.next() == 3) goto L31;
        skip(r9);
        goto L28
    L31:
        return new FontFileResourceEntry(r3, r4, r5, r6, r7, r8);
    L25:
        r04 = R$styleable.FontFamilyFont_android_font;
        goto L26
    L21:
        r2 = R$styleable.FontFamilyFont_android_fontVariationSettings;
        goto L22
    L17:
        r03 = R$styleable.FontFamilyFont_android_ttcIndex;
        goto L19
    L13:
        r5 = false;
        goto L15
    L9:
        r02 = R$styleable.FontFamilyFont_android_fontStyle;
        goto L11
    L5:
        r0 = R$styleable.FontFamilyFont_android_fontWeight;
        goto L6
    }

    private static void skip(XmlPullParser r3) {
        int r0 = 1;
    L3:
        if (r0 <= 0) goto L11;
        int r1 = r3.next();
        if (r1 != 2) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        if (r1 != 3) goto L3;
        r0 = r0 - 1;
        goto L3
    }

    private static List<byte[]> toByteArrayList(String[] r5) {
        ArrayList r0 = new ArrayList();
        int r1 = r5.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        r0.add(Base64.decode(r5[r3], 0));
        r3 = r3 + 1;
        goto L3
    L5:
        return r0;
    }
}
