package org.telegram.messenger.voip;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import org.telegram.messenger.ApplicationLoader;

/* loaded from: classes.dex */
public class CallNotificationSoundProvider extends ContentProvider {
    public CallNotificationSoundProvider() {
    }

    @Override // android.content.ContentProvider
    public int delete(Uri r1, String r2, String[] r3) {
        return 0;
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
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri r2, String r3) {
        if ("r".equals(r3) == false) goto L17;
        if (ApplicationLoader.applicationContext == null) goto L15;
        VoIPService r1 = VoIPService.getSharedInstance();     // Catch: Exception -> L11
        if (r1 == null) goto L9;
        r1.startRingtoneAndVibration();     // Catch: Exception -> L11
    L9:
        ParcelFileDescriptor[] r12 = ParcelFileDescriptor.createPipe();     // Catch: Exception -> L11
        ParcelFileDescriptor.AutoCloseOutputStream r22 = new ParcelFileDescriptor.AutoCloseOutputStream(r12[1]);     // Catch: Exception -> L11
        r22.write(new byte[]{82, 73, 70, 70, 41, 0, 0, 0, 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, 1, 0, 68, -84, 0, 0, 16, -79, 2, 0, 2, 0, 16, 0, 100, 97, 116, 97, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});     // Catch: Exception -> L11
        r22.close();     // Catch: Exception -> L11
        return r12[0];
    L11:
        e = move-exception;
        throw new FileNotFoundException(e.getMessage());
    L15:
        throw new FileNotFoundException("Unexpected application state");
    L17:
        throw new SecurityException("Unexpected file mode " + r3);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri r1, String[] r2, String r3, String[] r4, String r5) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri r1, ContentValues r2, String r3, String[] r4) {
        return 0;
    }
}
