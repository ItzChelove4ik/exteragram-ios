package org.telegram.messenger;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.telegram.messenger.NotificationCenter;

/* loaded from: classes.dex */
public class NotificationImageProvider extends ContentProvider implements NotificationCenter.NotificationCenterDelegate {
    private static String authority;
    private static UriMatcher matcher;
    private HashMap<String, Long> fileStartTimes;
    private final Object sync;
    private HashSet<String> waitingForFiles;

    public NotificationImageProvider() {
        this.waitingForFiles = new HashSet();
        this.sync = new Object();
        this.fileStartTimes = new HashMap();
    }

    public static String getAuthority() {
        if (authority != null) goto L6;
        authority = ApplicationLoader.getApplicationId() + ".notification_image_provider";
    L6:
        return authority;
    }

    private static UriMatcher getUriMatcher() {
        if (matcher != null) goto L6;
        UriMatcher r0 = new UriMatcher(-1);
        matcher = r0;
        r0.addURI(getAuthority(), "msg_media_raw/#/*", 1);
    L6:
        return matcher;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri r1, String r2, String[] r3) {
        return 0;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.fileLoaded) goto L16;
        Object r12 = this.sync;
        monitor-enter(r12);
        String r22 = (String) r3[0];     // Catch: Throwable -> L10
        if (this.waitingForFiles.remove(r22) == false) goto L12;
        this.fileStartTimes.remove(r22);     // Catch: Throwable -> L10
        this.sync.notifyAll();     // Catch: Throwable -> L10
    L12:
        monitor-exit(r12);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    @Override // android.content.ContentProvider
    public String[] getStreamTypes(Uri r1, String r2) {
        if (r2.startsWith("*/") == true) goto L10;
        if (r2.startsWith("image/") == true) goto L10;
        return null;
    L10:
        return new String[]{"image/jpeg", "image/png", "image/webp"};
    }

    @Override // android.content.ContentProvider
    public String getType(Uri r1) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri r1, ContentValues r2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        int r0 = 0;
    L4:
        if (r0 >= UserConfig.getActivatedAccountsCount()) goto L6;
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.fileLoaded);
        r0 = r0 + 1;
        goto L4
    L6:
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri r10, String r11) {
        if ("r".equals(r11) == false) goto L56;
        if (getUriMatcher().match(r10) != 1) goto L54;
        List<String> r112 = r10.getPathSegments();
        Integer.parseInt(r112.get(1));
        String r113 = r112.get(2);
        String r0 = r10.getQueryParameter("final_path");
        String r102 = r10.getQueryParameter("fallback");
        File r1 = new File(r0);
        ApplicationLoader.postInitApplication();
        if (AndroidUtilities.isInternalUri(Uri.fromFile(r1)) == true) goto L52;
        if (r1.exists() == true) goto L50;
        Long r02 = this.fileStartTimes.get(r113);
        if (r02 == null) goto L13;
        long r3 = r02.longValue();
    L14:
        if (r02 != null) goto L17;
        this.fileStartTimes.put(r113, Long.valueOf(r3));
    L17:
        if (r1.exists() == true) goto L45;
        if ((System.currentTimeMillis() - r3) >= 3000) goto L21;
        Object r03 = this.sync;
        monitor-enter(r03);
        this.waitingForFiles.add(r113);     // Catch: Throwable -> L38
        this.sync.wait(1000);     // Catch: Throwable -> L38 InterruptedException -> L57
    L40:
        monitor-exit(r03);     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        throw th;
    L21:
        if (BuildVars.LOGS_ENABLED == false) goto L24;
        FileLog.w("Waiting for " + r113 + " to download timed out");
    L24:
        if (TextUtils.isEmpty(r102) == true) goto L32;
        File r9 = new File(r102);
        if (AndroidUtilities.isInternalUri(Uri.fromFile(r9)) == true) goto L30;
        return ParcelFileDescriptor.open(r9, 268435456);
    L30:
        throw new SecurityException("trying to read internal file");
    L32:
        throw new FileNotFoundException("Download timed out");
    L45:
        if (AndroidUtilities.isInternalUri(Uri.fromFile(r1)) == false) goto L50;
        throw new SecurityException("trying to read internal file");
    L13:
        r3 = System.currentTimeMillis();
    L50:
        return ParcelFileDescriptor.open(r1, 268435456);
    L52:
        throw new SecurityException("trying to read internal file");
    L54:
        throw new FileNotFoundException("Invalid URI");
    L56:
        throw new SecurityException("Can only open files for read");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri r1, String[] r2, String r3, String[] r4, String r5) {
        return null;
    }

    @Override // android.content.ContentProvider
    public void shutdown() {
        int r0 = 0;
    L4:
        if (r0 >= UserConfig.getActivatedAccountsCount()) goto L6;
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.fileLoaded);
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.content.ContentProvider
    public int update(Uri r1, ContentValues r2, String r3, String[] r4) {
        return 0;
    }
}
