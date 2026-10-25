package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.core.util.ObjectsCompat;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class FileProvider extends ContentProvider {
    private static final String[] COLUMNS = null;
    private static final File DEVICE_ROOT = null;
    private static final HashMap<String, PathStrategy> sCache = null;
    private String mAuthority;
    private PathStrategy mLocalPathStrategy;
    private final Object mLock;
    private final int mResourceId;

    public interface PathStrategy {
        File getFileForUri(Uri r1);

        Uri getUriForFile(File r1);
    }

    public static class SimplePathStrategy implements PathStrategy {
        private final String mAuthority;
        private final HashMap<String, File> mRoots;

        public SimplePathStrategy(String r2) {
            this.mRoots = new HashMap();
            this.mAuthority = r2;
        }

        private boolean belongsToRoot(String r1, String r2) {
            return FileProvider.access$000(r1).startsWith(FileProvider.access$000(r2) + '/');
        }

        public void addRoot(String r3, File r4) {
            if (TextUtils.isEmpty(r3) == false) goto L12;
            f$$ExternalSyntheticBUOutline1.m("Name must not be empty");
            return;
        L12:
            this.mRoots.put(r3, r4.getCanonicalFile());
            return;
        L7:
            e = move-exception;
            throw new IllegalArgumentException("Failed to resolve canonical path for " + r4, e);
        }

        @Override // androidx.core.content.FileProvider.PathStrategy
        public File getFileForUri(Uri r6) {
            String r0 = r6.getEncodedPath();
            int r1 = r0.indexOf(47, 1);
            if (r1 == (-1)) goto L17;
            String r3 = Uri.decode(r0.substring(1, r1));
            String r02 = Uri.decode(r0.substring(r1 + 1));
            File r12 = this.mRoots.get(r3);
            if (r12 == null) goto L15;
            File r62 = new File(r12, r02);
            File r63 = r62.getCanonicalFile();     // Catch: IOException -> L13
            if (belongsToRoot(r63.getPath(), r12.getPath()) == false) goto L12;
            return r63;
        L12:
            throw new SecurityException("Resolved path jumped beyond configured root");
        L13:
            Native$$ExternalSyntheticBUOutline5.m("Failed to resolve canonical path for ", r62);
            return null;
        L15:
            Native$$ExternalSyntheticBUOutline5.m("Unable to find configured root for ", r6);
            return null;
        L17:
            Native$$ExternalSyntheticBUOutline5.m("Unable to find path from root: ", r6);
            return null;
        }

        @Override // androidx.core.content.FileProvider.PathStrategy
        public Uri getUriForFile(File r7) {
            String r72 = r7.getCanonicalPath();     // Catch: IOException -> L22
            Iterator<Map.Entry<String, File>> r1 = this.mRoots.entrySet().iterator();
            Map.Entry<String, File> r2 = null;
        L6:
            if (r1.hasNext() == false) goto L13;
            Map.Entry<String, File> r3 = r1.next();
            String r4 = r3.getValue().getPath();
            if (belongsToRoot(r72, r4) == false) goto L6;
            if (r2 == null) goto L12;
            if (r4.length() <= r2.getValue().getPath().length()) goto L6;
        L12:
            r2 = r3;
            goto L6
        L13:
            if (r2 == null) goto L20;
            String r0 = r2.getValue().getPath();
            if (r0.endsWith("/") == false) goto L17;
            String r73 = r72.substring(r0.length());
        L19:
            return new Uri.Builder().scheme("content").authority(this.mAuthority).encodedPath(Uri.encode(r2.getKey()) + '/' + Uri.encode(r73, "/")).build();
        L17:
            r73 = r72.substring(r0.length() + 1);
            goto L19
        L20:
            Native$$ExternalSyntheticBUOutline5.m("Failed to find configured root that contains ", r72);
            return null;
        L22:
            Native$$ExternalSyntheticBUOutline5.m("Failed to resolve canonical path for ", r7);
            return null;
        }
    }

    static {
        COLUMNS = new String[]{"_display_name", "_size"};
        DEVICE_ROOT = new File("/");
        sCache = new HashMap();
    }

    public FileProvider() {
        this(0);
    }

    public FileProvider(int r2) {
        this.mLock = new Object();
        this.mResourceId = r2;
    }

    public static /* synthetic */ String access$000(String r0) {
        return removeTrailingSlash(r0);
    }

    private static File buildPath(File r4, String... r5) {
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        String r2 = r5[r1];
        if (r2 == null) goto L7;
        r4 = new File(r4, r2);
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        return r4;
    }

    private static Object[] copyOf(Object[] r2, int r3) {
        Object[] r0 = new Object[r3];
        System.arraycopy(r2, 0, r0, 0, r3);
        return r0;
    }

    private static String[] copyOf(String[] r2, int r3) {
        String[] r0 = new String[r3];
        System.arraycopy(r2, 0, r0, 0, r3);
        return r0;
    }

    public static XmlResourceParser getFileProviderPathsMetaData(Context r3, String r4, ProviderInfo r5, int r6) {
        if (r5 != null) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Couldn't find meta-data for provider with authority ", r4);
        return null;
    L5:
        if (r5.metaData != null) goto L8;
        if (r6 == 0) goto L8;
        Bundle r42 = new Bundle(1);
        r5.metaData = r42;
        r42.putInt("android.support.FILE_PROVIDER_PATHS", r6);
    L8:
        XmlResourceParser r32 = r5.loadXmlMetaData(r3.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if (r32 == null) goto L11;
        return r32;
    L11:
        f$$ExternalSyntheticBUOutline1.m("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        return null;
    }

    private PathStrategy getLocalPathStrategy() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        ObjectsCompat.requireNonNull(this.mAuthority, "mAuthority is null. Did you override attachInfo and did not call super.attachInfo()?");     // Catch: Throwable -> L7
        if (this.mLocalPathStrategy != null) goto L9;
        this.mLocalPathStrategy = getPathStrategy(getContext(), this.mAuthority, this.mResourceId);     // Catch: Throwable -> L7
    L9:
        PathStrategy r4 = this.mLocalPathStrategy;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r4;
    L7:
        th = move-exception;
        throw th;
    }

    private static PathStrategy getPathStrategy(Context r2, String r3, int r4) {
        HashMap<String, PathStrategy> r0 = sCache;
        monitor-enter(r0);
        PathStrategy r1 = r0.get(r3);     // Catch: Throwable -> L8
        if (r1 == null) goto L21;
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r1;
    L21:
        r1 = parsePathStrategy(r2, r3, r4);     // Catch: Throwable -> L8 XmlPullParserException -> L10 IOException -> L13
        r0.put(r3, r1);     // Catch: Throwable -> L8
        goto L16
    L13:
        e = move-exception;
        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);     // Catch: Throwable -> L8
    L10:
        e = move-exception;
        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public static Uri getUriForFile(Context r1, String r2, File r3) {
        return getPathStrategy(r1, r2, 0).getUriForFile(r3);
    }

    private static int modeToMode(String r1) {
        if ("r".equals(r1) == false) goto L7;
        return 268435456;
    L7:
        if ("w".equals(r1) == false) goto L9;
        return 738197504;
    L9:
        if ("wt".equals(r1) == false) goto L12;
        return 738197504;
    L12:
        if ("wa".equals(r1) == false) goto L16;
        return 704643072;
    L16:
        if ("rw".equals(r1) == false) goto L20;
        return 939524096;
    L20:
        if ("rwt".equals(r1) == false) goto L23;
        return 1006632960;
    L23:
        Native$$ExternalSyntheticBUOutline5.m("Invalid mode: ", r1);
        return 0;
    }

    private static PathStrategy parsePathStrategy(Context r6, String r7, int r8) {
        SimplePathStrategy r0 = new SimplePathStrategy(r7);
        XmlResourceParser r72 = getFileProviderPathsMetaData(r6, r7, r6.getPackageManager().resolveContentProvider(r7, 128), r8);
    L3:
        int r82 = r72.next();
        if (r82 == 1) goto L36;
        if (r82 != 2) goto L3;
        String r83 = r72.getName();
        File r2 = null;
        String r1 = r72.getAttributeValue(null, "name");
        String r3 = r72.getAttributeValue(null, "path");
        if ("root-path".equals(r83) == false) goto L11;
        r2 = DEVICE_ROOT;
    L34:
        if (r2 == null) goto L3;
        r0.addRoot(r1, buildPath(r2, new String[]{r3}));
        goto L3
    L11:
        if ("files-path".equals(r83) == false) goto L14;
        r2 = r6.getFilesDir();
        goto L34
    L14:
        if ("cache-path".equals(r83) == false) goto L17;
        r2 = r6.getCacheDir();
        goto L34
    L17:
        if ("external-path".equals(r83) == false) goto L20;
        r2 = Environment.getExternalStorageDirectory();
        goto L34
    L20:
        if ("external-files-path".equals(r83) == false) goto L25;
        File[] r84 = ContextCompat.getExternalFilesDirs(r6, null);
        if (r84.length <= 0) goto L34;
        r2 = r84[0];
        goto L34
    L25:
        if ("external-cache-path".equals(r83) == false) goto L30;
        File[] r85 = ContextCompat.getExternalCacheDirs(r6);
        if (r85.length <= 0) goto L34;
        r2 = r85[0];
        goto L34
    L30:
        if ("external-media-path".equals(r83) == false) goto L34;
        File[] r86 = r6.getExternalMediaDirs();
        if (r86.length <= 0) goto L34;
        r2 = r86[0];
        goto L34
    L36:
        return r0;
    }

    private static String removeTrailingSlash(String r2) {
        if (r2.length() > 0) goto L5;
        return r2;
    L5:
        if (r2.charAt(r2.length() - 1) == '/') goto L7;
        return r2;
    L7:
        return r2.substring(0, r2.length() - 1);
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context r1, ProviderInfo r2) {
        super.attachInfo(r1, r2);
        if (r2.exported == true) goto L30;
        if (r2.grantUriPermissions == false) goto L28;
        String r12 = r2.authority;
        if (r12 == null) goto L26;
        if (r12.trim().isEmpty() == true) goto L26;
        String r13 = r2.authority.split(";")[0];
        Object r22 = this.mLock;
        monitor-enter(r22);
        this.mAuthority = r13;     // Catch: Throwable -> L22
        monitor-exit(r22);     // Catch: Throwable -> L22
        HashMap<String, PathStrategy> r0 = sCache;
        monitor-enter(r0);
        r0.remove(r13);     // Catch: Throwable -> L19
        monitor-exit(r0);     // Catch: Throwable -> L19
        return;
    L19:
        th = move-exception;
        throw th;
    L22:
        th = move-exception;
        throw th;
    L26:
        throw new SecurityException("Provider must have a non-empty authority");
    L28:
        throw new SecurityException("Provider must grant uri permissions");
    L30:
        throw new SecurityException("Provider must not be exported");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri r1, String r2, String[] r3) {
        return getLocalPathStrategy().getFileForUri(r1).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri r2) {
        File r1 = getLocalPathStrategy().getFileForUri(r2);
        int r22 = r1.getName().lastIndexOf(46);
        if (r22 < 0) goto L7;
        String r12 = r1.getName().substring(r22 + 1);
        String r13 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(r12);
        if (r13 == null) goto L9;
        return r13;
    L9:
        return "application/octet-stream";
    L7:
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public String getTypeAnonymous(Uri r1) {
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri r1, ContentValues r2) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    @SuppressLint({"UnknownNullness"})
    public ParcelFileDescriptor openFile(Uri r1, String r2) {
        return ParcelFileDescriptor.open(getLocalPathStrategy().getFileForUri(r1), modeToMode(r2));
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri r6, String[] r7, String r8, String[] r9, String r10) {
        File r5 = getLocalPathStrategy().getFileForUri(r6);
        String r62 = r6.getQueryParameter("displayName");
        if (r7 != null) goto L5;
        r7 = COLUMNS;
    L5:
        String[] r82 = new String[r7.length];
        Object[] r92 = new Object[r7.length];
        int r102 = r7.length;
        int r0 = 0;
        int r1 = 0;
    L6:
        if (r0 >= r102) goto L19;
        String r2 = r7[r0];
        if ("_display_name".equals(r2) == false) goto L16;
        r82[r1] = "_display_name";
        int r22 = r1 + 1;
        if (r62 != null) goto L12;
        String r3 = r5.getName();
    L13:
        r92[r1] = r3;
    L14:
        r1 = r22;
    L18:
        r0 = r0 + 1;
        goto L6
    L12:
        r3 = r62;
        goto L13
    L16:
        if ("_size".equals(r2) == false) goto L18;
        r82[r1] = "_size";
        r22 = r1 + 1;
        r92[r1] = Long.valueOf(r5.length());
        goto L14
    L19:
        String[] r52 = copyOf(r82, r1);
        Object[] r63 = copyOf(r92, r1);
        MatrixCursor r72 = new MatrixCursor(r52, 1);
        r72.addRow(r63);
        return r72;
    }

    @Override // android.content.ContentProvider
    public int update(Uri r1, ContentValues r2, String r3, String[] r4) {
        throw new UnsupportedOperationException("No external updates");
    }
}
