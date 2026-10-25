package com.google.firebase.messaging;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.Closeable;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class ImageDownload implements Closeable {
    private volatile Future<?> future;
    private Task<Bitmap> task;
    private final URL url;

    public static /* synthetic */ void $r8$lambda$eScMs6vxKn1BVRLt69sEYv0FXxI(ImageDownload r0, TaskCompletionSource r1) {
        r0.getClass();
        r1.setResult(r0.blockingDownload());     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        r1.setException(e);
    }

    private ImageDownload(URL r1) {
        this.url = r1;
    }

    private byte[] blockingDownloadBytes() {
        URLConnection r0 = this.url.openConnection();
        if (r0.getContentLength() > 1048576) goto L23;
        InputStream r02 = r0.getInputStream();
        byte[] r1 = ByteStreams.toByteArray(ByteStreams.limit(r02, 1048577));     // Catch: Throwable -> L16
        if (r02 == null) goto L9;
        r02.close();
    L9:
        if (Log.isLoggable("FirebaseMessaging", 2) == false) goto L12;
        Log.v("FirebaseMessaging", "Downloaded " + r1.length + " bytes from " + this.url);
    L12:
        if (r1.length > 1048576) goto L14;
        return r1;
    L14:
        Model$$ExternalSyntheticBUOutline0.m("Image exceeds max size of 1048576");
        return null;
    L16:
        th = move-exception;
        if (r02 != null) goto L27;
    L22:
        throw th;
    L27:
        r02.close();     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        th.addSuppressed(th);
        goto L22
    L23:
        Model$$ExternalSyntheticBUOutline0.m("Content-Length exceeds max size of 1048576");
        return null;
    }

    public static ImageDownload create(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L9;
        return null;
    L9:
        return new ImageDownload(new URL(r3));
    L7:
        Log.w("FirebaseMessaging", "Not downloading image, bad URL: " + r3);
        return null;
    }

    public Bitmap blockingDownload() {
        if (Log.isLoggable("FirebaseMessaging", 4) == false) goto L5;
        Log.i("FirebaseMessaging", "Starting download of: " + this.url);
    L5:
        byte[] r0 = blockingDownloadBytes();
        Bitmap r02 = BitmapFactory.decodeByteArray(r0, 0, r0.length);
        if (r02 != null) goto L8;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Failed to decode image: ", this.url);
        return null;
    L8:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L10;
        Log.d("FirebaseMessaging", "Successfully downloaded image: " + this.url);
    L10:
        return r02;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.future.cancel(true);
    }

    public Task<Bitmap> getTask() {
        return (Task) Preconditions.checkNotNull(this.task);
    }

    public void start(ExecutorService r3) {
        TaskCompletionSource r0 = new TaskCompletionSource();
        this.future = r3.submit(new ImageDownload$$ExternalSyntheticLambda0(this, r0));
        this.task = r0.getTask();
    }
}
