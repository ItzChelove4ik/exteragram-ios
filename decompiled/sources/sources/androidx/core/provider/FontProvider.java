package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.tracing.Trace;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
abstract class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = null;
    private static final LruCache<ProviderCacheKey, ProviderInfo> sProviderCache = null;

    public interface ContentQueryWrapper {
        static ContentQueryWrapper make(Context r1, Uri r2) {
            return new ContentQueryWrapperApi24Impl(r1, r2);
        }

        void close();

        Cursor query(Uri r1, String[] r2, String r3, String[] r4, String r5, CancellationSignal r6);
    }

    public static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        public ContentQueryWrapperApi24Impl(Context r1, Uri r2) {
            this.mClient = r1.getContentResolver().acquireUnstableContentProviderClient(r2);
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() {
            ContentProviderClient r0 = this.mClient;
            if (r0 == null) goto L6;
            r0.close();
            return;
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor query(Uri r3, String[] r4, String r5, String[] r6, String r7, CancellationSignal r8) {
            ContentProviderClient r2 = this.mClient;
            if (r2 != null) goto L10;
            return null;
        L10:
            return r2.query(r3, r4, r5, r6, r7, r8);
        L7:
            e = move-exception;
            Log.w("FontsProvider", "Unable to query the content provider", e);
            return null;
        }
    }

    public static class ProviderCacheKey {
        String mAuthority;
        List<List<byte[]>> mCertificates;
        String mPackageName;

        public ProviderCacheKey(String r1, String r2, List<List<byte[]>> r3) {
            this.mAuthority = r1;
            this.mPackageName = r2;
            this.mCertificates = r3;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ProviderCacheKey) == true) goto L8;
            return false;
        L8:
            ProviderCacheKey r52 = (ProviderCacheKey) r5;
            if (Objects.equals(this.mAuthority, r52.mAuthority) == true) goto L11;
        L15:
            return false;
        L11:
            if (Objects.equals(this.mPackageName, r52.mPackageName) == false) goto L15;
            if (Objects.equals(this.mCertificates, r52.mCertificates) == false) goto L15;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.mAuthority, this.mPackageName, this.mCertificates});
        }
    }

    public static /* synthetic */ int $r8$lambda$YnOn4sMaJN6i8fkk9HOHIkI5PVE(byte[] r4, byte[] r5) {
        if (r4.length != r5.length) goto L5;
        int r1 = 0;
    L8:
        if (r1 >= r4.length) goto L14;
        byte r2 = r4[r1];
        byte r3 = r5[r1];
        if (r2 != r3) goto L12;
        r1 = r1 + 1;
        goto L8
    L12:
        return r2 - r3;
    L14:
        return 0;
    L5:
        return r4.length - r5.length;
    }

    static {
        sProviderCache = new LruCache(2);
        sByteArrayComparator = new FontProvider$$ExternalSyntheticLambda0();
    }

    private static List<byte[]> convertToByteArrayList(Signature[] r4) {
        ArrayList r0 = new ArrayList();
        int r1 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.add(r4[r2].toByteArray());
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    private static boolean equalsByteArrayList(List<byte[]> r4, List<byte[]> r5) {
        if (r4.size() == r5.size()) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r4.size()) goto L12;
        if (Arrays.equals(r4.get(r0), r5.get(r0)) == false) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    private static List<List<byte[]>> getCertificates(FontRequest r1, Resources r2) {
        if (r1.getCertificates() == null) goto L7;
        return r1.getCertificates();
    L7:
        return FontResourcesParserCompat.readCerts(r2, r1.getCertificatesArrayResId());
    }

    public static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context r6, List<FontRequest> r7, CancellationSignal r8) {
        Trace.beginSection("FontProvider.getFontFamilyResult");
        ArrayList r0 = new ArrayList();     // Catch: Throwable -> L23
        int r2 = 0;
    L5:
        if (r2 >= r7.size()) goto L20;
        FontRequest r3 = r7.get(r2);     // Catch: Throwable -> L23
        if (Build.VERSION.SDK_INT < 31) goto L13;
        String r4 = r3.getSystemFont();     // Catch: Throwable -> L23
        Typeface r5 = TypefaceCompat.getSystemFontFamily(r4);     // Catch: Throwable -> L23
        if (r5 == null) goto L13;
        if (TypefaceCompat.guessPrimaryFont(r5) == null) goto L13;
        r0.add(new FontsContractCompat.FontInfo[]{new FontsContractCompat.FontInfo(r4, r3.getVariationSettings())});     // Catch: Throwable -> L23
    L19:
        r2 = r2 + 1;     // Catch: Throwable -> L23
    L13:
        ProviderInfo r42 = getProvider(r6.getPackageManager(), r3, r6.getResources());     // Catch: Throwable -> L23
        if (r42 == null) goto L15;
        r0.add(query(r6, r3, r42.authority, r8));     // Catch: Throwable -> L23
        goto L19
    L15:
        FontsContractCompat.FontFamilyResult r62 = FontsContractCompat.FontFamilyResult.create(1, null);     // Catch: Throwable -> L23
        Trace.endSection();
        return r62;
    L20:
        FontsContractCompat.FontFamilyResult r63 = FontsContractCompat.FontFamilyResult.create(0, r0);     // Catch: Throwable -> L23
        Trace.endSection();
        return r63;
    L23:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static ProviderInfo getProvider(PackageManager r7, FontRequest r8, Resources r9) {
        Trace.beginSection("FontProvider.getProvider");
        List<List<byte[]>> r92 = getCertificates(r8, r9);     // Catch: Throwable -> L26
        ProviderCacheKey r2 = new ProviderCacheKey(r8.getProviderAuthority(), r8.getProviderPackage(), r92);     // Catch: Throwable -> L26
        ProviderInfo r3 = sProviderCache.get(r2);     // Catch: Throwable -> L26
        if (r3 == null) goto L7;
        Trace.endSection();
        return r3;
    L7:
        String r32 = r8.getProviderAuthority();     // Catch: Throwable -> L26
        int r4 = 0;
        ProviderInfo r5 = r7.resolveContentProvider(r32, 0);     // Catch: Throwable -> L26
        if (r5 == null) goto L25;
        if (r5.packageName.equals(r8.getProviderPackage()) == false) goto L23;
        List<byte[]> r72 = convertToByteArrayList(r7.getPackageInfo(r5.packageName, 64).signatures);     // Catch: Throwable -> L26
        Collections.sort(r72, sByteArrayComparator);     // Catch: Throwable -> L26
    L13:
        if (r4 >= r92.size()) goto L20;
        ArrayList r82 = new ArrayList(r92.get(r4));     // Catch: Throwable -> L26
        Collections.sort(r82, sByteArrayComparator);     // Catch: Throwable -> L26
        if (equalsByteArrayList(r72, r82) == true) goto L16;
        r4 = r4 + 1;
        goto L13
    L16:
        sProviderCache.put(r2, r5);     // Catch: Throwable -> L26
        Trace.endSection();
        return r5;
    L20:
        Trace.endSection();
        return null;
    L23:
        throw new PackageManager.NameNotFoundException("Found content provider " + r32 + ", but package was not " + r8.getProviderPackage());     // Catch: Throwable -> L26
    L25:
        throw new PackageManager.NameNotFoundException("No package found for authority: " + r32);     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r19v10 */
    /* JADX WARN: Type inference failed for: r19v2, types: [androidx.core.provider.FontProvider$ContentQueryWrapper] */
    public static FontsContractCompat.FontInfo[] query(Context r19, FontRequest r20, String r21, CancellationSignal r22) {
        Trace.beginSection("FontProvider.query");
        ArrayList r2 = new ArrayList();     // Catch: Throwable -> L57
        Uri r5 = new Uri.Builder().scheme("content").authority(r21).build();     // Catch: Throwable -> L57
        Uri r0 = new Uri.Builder().scheme("content").authority(r21).appendPath("file").build();     // Catch: Throwable -> L57
        ContentQueryWrapper r4 = ContentQueryWrapper.make(r19, r5);     // Catch: Throwable -> L57
        Cursor r1 = null;
        String[] r6 = {"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"};     // Catch: Throwable -> L17
        Trace.beginSection("ContentQueryWrapper.query");     // Catch: Throwable -> L17
        r1 = r4.query(r5, r6, "query = ?", new String[]{r20.getQuery()}, null, r22);     // Catch: Throwable -> L49
        Trace.endSection();     // Catch: Throwable -> L17
        if (r1 != null) goto L10;
    L43:
        ContentQueryWrapper r192 = r4;
    L44:
        if (r1 == null) goto L46;
        r1.close();     // Catch: Throwable -> L57
    L46:
        r192.close();     // Catch: Throwable -> L57
        FontsContractCompat.FontInfo[] r02 = (FontsContractCompat.FontInfo[]) r2.toArray(new FontsContractCompat.FontInfo[0]);     // Catch: Throwable -> L57
        Trace.endSection();
        return r02;
    L10:
        if (r1.getCount() <= 0) goto L43;
        int r23 = r1.getColumnIndex("result_code");     // Catch: Throwable -> L17
        ArrayList r62 = new ArrayList();     // Catch: Throwable -> L17
        int r7 = r1.getColumnIndex("_id");     // Catch: Throwable -> L17
        int r8 = r1.getColumnIndex("file_id");     // Catch: Throwable -> L17
        int r9 = r1.getColumnIndex("font_ttc_index");     // Catch: Throwable -> L17
        int r10 = r1.getColumnIndex("font_weight");     // Catch: Throwable -> L17
        int r11 = r1.getColumnIndex("font_italic");     // Catch: Throwable -> L17
    L12:
        if (r1.moveToNext() == false) goto L42;
        if (r23 == (-1)) goto L19;
        int r18 = r1.getInt(r23);     // Catch: Throwable -> L17
    L20:
        if (r9 != (-1)) goto L22;
        int r14 = 0;
    L24:
        if (r8 != (-1)) goto L30;
        ContentQueryWrapper r193 = r4;
        Uri r3 = ContentUris.withAppendedId(r5, r1.getLong(r7));     // Catch: Throwable -> L28
    L27:
        Uri r13 = r3;
        if (r10 == (-1)) goto L34;
        int r32 = r1.getInt(r10);     // Catch: Throwable -> L28
    L33:
        int r15 = r32;
        if (r11 != (-1)) goto L37;
    L39:
        boolean r16 = false;
    L40:
        r62.add(new FontsContractCompat.FontInfo(r13, r14, r15, r16, r20.getVariationSettings(), r18));     // Catch: Throwable -> L28
        r4 = r193;
        goto L12
    L37:
        if (r1.getInt(r11) != 1) goto L39;
        r16 = true;
        goto L40
    L34:
        r32 = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        goto L33
    L30:
        r193 = r4;
        r3 = ContentUris.withAppendedId(r0, r1.getLong(r8));     // Catch: Throwable -> L28
        goto L27
    L22:
        r14 = r1.getInt(r9);     // Catch: Throwable -> L17
        goto L24
    L19:
        r18 = 0;
        goto L20
    L42:
        r192 = r4;
        r2 = r62;
        goto L44
    L28:
        th = th;
        ?? r194 = r19;
    L53:
        if (r1 == null) goto L55;
        r1.close();     // Catch: Throwable -> L57
    L55:
        r194.close();     // Catch: Throwable -> L57
        throw th;     // Catch: Throwable -> L57
    L49:
        th = move-exception;
        Trace.endSection();     // Catch: Throwable -> L28
        throw th;     // Catch: Throwable -> L28
    L17:
        th = th;
        r194 = r4;
    L57:
        th = move-exception;
        Trace.endSection();
        throw th;
    }
}
