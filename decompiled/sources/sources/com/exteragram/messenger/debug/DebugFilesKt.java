package com.exteragram.messenger.debug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.core.content.FileProvider;
import java.io.File;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0000¨\u0006\u000b"}, d2 = {"debugFilesDir", "Ljava/io/File;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "shareDebugFile", _UrlKt.FRAGMENT_ENCODE_SET, "activity", "Landroid/app/Activity;", "file", "mimeType", "title", "TMessagesProj"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class DebugFilesKt {
    public static final File debugFilesDir(String r3) {
        Context r0 = ApplicationLoader.applicationContext;
        File r2 = r0.getExternalFilesDir(null);
        if (r2 != null) goto L6;
        r2 = r0.getCacheDir();
    L6:
        return new File(r2, r3);
    }

    public static final void shareDebugFile(Activity r2, File r3, String r4, String r5) {
        r2.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType(r4).putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(r2, ApplicationLoader.getApplicationId() + ".provider", r3)).addFlags(1), r5));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }
}
