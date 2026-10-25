package org.telegram.ui.web;

import android.os.AsyncTask;
import android.webkit.MimeTypeMap;
import androidx.annotation.Keep;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.recorder.StoryEntry;

@Keep
/* loaded from: classes6.dex */
public class HttpGetFileTask extends AsyncTask<String, Void, File> {
    private Utilities.Callback<File> doneCallback;
    private Exception exception;
    private File file;
    private long max_size;
    private String overrideExt;
    private Utilities.Callback<Float> progressCallback;

    /* renamed from: $r8$lambda$9IL_HH7KyGbsLIzT-A2j2xC699w, reason: not valid java name */
    public static /* synthetic */ void m22052$r8$lambda$9IL_HH7KyGbsLIzTA2j2xC699w(HttpGetFileTask r0, float r1) {
        r0.lambda$doInBackground$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$zcP4TYguDO_CcTzli0UE9_vJCM0(HttpGetFileTask r0) {
        r0.lambda$doInBackground$1();
    }

    public HttpGetFileTask(Utilities.Callback<File> r3, Utilities.Callback<Float> r4) {
        this.max_size = -1;
        this.doneCallback = r3;
        this.progressCallback = r4;
    }

    private /* synthetic */ void lambda$doInBackground$0(float r1) {
        this.progressCallback.run(Float.valueOf(r1));
    }

    private /* synthetic */ void lambda$doInBackground$1() {
        this.progressCallback.run(Float.valueOf(1.0f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v10 */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r16v12, types: [long] */
    /* JADX WARN: Type inference failed for: r16v13 */
    /* JADX WARN: Type inference failed for: r16v2, types: [int] */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v7 */
    /* JADX WARN: Type inference failed for: r16v8 */
    /* JADX WARN: Type inference failed for: r16v9 */
    /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
    public File doInBackground2(String... r19) {
        String r3 = r19[0];
        long r4 = 0;
        int r6 = 0;
        long r7 = 0;
    L4:
        if (r6 >= 5) goto L118;
        if (r6 <= 0) goto L8;
        boolean r10 = true;
    L141:
        HttpURLConnection r11 = (HttpURLConnection) new URL(r3).openConnection();     // Catch: Exception -> L12
        r11.setRequestMethod("GET");     // Catch: Exception -> L12
        if (r10 == false) goto L14;
        r11.setRequestProperty("Range", "bytes=" + r7 + "-");     // Catch: Exception -> L12
    L14:
        r11.setDoInput(true);     // Catch: Exception -> L12
        int r0 = r11.getResponseCode();     // Catch: Exception -> L12
        if (r0 >= 200) goto L17;
    L19:
        InputStream r02 = r11.getErrorStream();     // Catch: Exception -> L12
    L20:
        int r12 = r11.getResponseCode();     // Catch: Exception -> L12
        if (r10 == true) goto L23;
    L33:
        long r122 = r11.getContentLengthLong();     // Catch: Exception -> L12
        long r14 = this.max_size;     // Catch: Exception -> L12
        ?? r16 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1));
        if (r16 <= 0) goto L42;
        if (r122 <= r14) goto L42;
        r02.close();     // Catch: Exception -> L12
        if (this.file == null) goto L40;
        this.file = null;     // Catch: Exception -> L12
    L40:
        return null;
    L42:
        if (this.file != null) goto L126;
        String r142 = this.overrideExt;     // Catch: Exception -> L12
        if (r142 != null) goto L47;
        r142 = MimeTypeMap.getSingleton().getExtensionFromMimeType(r11.getContentType());     // Catch: Exception -> L12
    L47:
        this.file = StoryEntry.makeCacheFile(UserConfig.selectedAccount, r142);     // Catch: Exception -> L12
    L126:
        BufferedInputStream r112 = new BufferedInputStream(r02, 16384);     // Catch: Exception -> L12
        FileOutputStream r15 = new FileOutputStream(this.file, r10);     // Catch: Throwable -> L106
        FileChannel r102 = r15.getChannel();     // Catch: Throwable -> L99
        byte[] r03 = new byte[16384];     // Catch: Throwable -> L91
    L52:
        int r143 = r112.read(r03);     // Catch: Throwable -> L91
        r16 = r4;
        if (r143 == (-1)) goto L81;
        r102.write(ByteBuffer.wrap(r03, 0, r143));     // Catch: Throwable -> L59
        r7 = r7 + r143;     // Catch: Throwable -> L59
        if (isCancelled() == false) goto L74;
        this.file.delete();     // Catch: Throwable -> L59 Exception -> L61
    L63:
        r102.close();     // Catch: Throwable -> L71
        r15.close();     // Catch: Throwable -> L69
        r112.close();     // Catch: Exception -> L67
        return null;
    L61:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L59
        goto L63
    L74:
        if (r122 <= r16) goto L79;
        float r42 = Utilities.clamp01(r7 / r122);     // Catch: Throwable -> L59
        if (this.progressCallback == null) goto L79;
        AndroidUtilities.runOnUIThread(new HttpGetFileTask$$ExternalSyntheticLambda0(this, r42));     // Catch: Throwable -> L59
    L79:
        r4 = r16;
    L59:
        th = th;
    L60:
        Throwable r43 = th;
        if (r102 == null) goto L157;
        r102.close();     // Catch: Throwable -> L96
    L158:
        throw r43;     // Catch: Throwable -> L71
    L96:
        th = move-exception;
        r43.addSuppressed(th);     // Catch: Throwable -> L71
        throw r43;     // Catch: Throwable -> L71
    L157:
        throw r43;     // Catch: Throwable -> L71
    L81:
        if (this.progressCallback == null) goto L83;
        AndroidUtilities.runOnUIThread(new HttpGetFileTask$$ExternalSyntheticLambda1(this));     // Catch: Throwable -> L59
    L83:
        if (r102 == null) goto L85;
        r102.close();     // Catch: Throwable -> L71
    L85:
        r15.close();     // Catch: Throwable -> L69
        r112.close();     // Catch: Exception -> L67
        if (isCancelled() == false) goto L90;
        return null;
    L90:
        return this.file;
    L91:
        th = th;
        r16 = r4;
    L99:
        th = th;
        r16 = r4;
    L72:
        Throwable r44 = th;
        r15.close();     // Catch: Throwable -> L103
    L159:
        throw r44;     // Catch: Throwable -> L69
    L103:
        th = move-exception;
        r44.addSuppressed(th);     // Catch: Throwable -> L69
        throw r44;     // Catch: Throwable -> L69
    L71:
        th = th;
    L106:
        th = th;
        r16 = r4;
    L70:
        Throwable r45 = th;
        r112.close();     // Catch: Throwable -> L110
    L160:
        throw r45;     // Catch: Exception -> L67
    L110:
        th = move-exception;
        r45.addSuppressed(th);     // Catch: Exception -> L67
        throw r45;     // Catch: Exception -> L67
    L69:
        th = th;
    L67:
        e = e;
    L114:
        if ((e instanceof ProtocolException) == false) goto L116;
        FileLog.d("got unexpected end of stream, lets try to resume");
        r6 = r6 + 1;
        r4 = r16;
        goto L4
    L116:
        this.exception = e;
        FileLog.e(e);
        return null;
    L23:
        if (r12 == 206) goto L33;
        FileLog.d("failed to resume, server doesn't support partial content. downloading from the beginning");     // Catch: Exception -> L12
        File r72 = this.file;     // Catch: Exception -> L30
        if (r72 != null) goto L131;
    L32:
        r10 = false;
        r7 = r4;
        goto L33
    L131:
        r72.delete();     // Catch: Exception -> L120
    L28:
        this.file = null;     // Catch: Exception -> L30
    L30:
        e = e;
        r7 = r4;
        r16 = r7;
        goto L114
    L17:
        if (r0 >= 300) goto L19;
        r02 = r11.getInputStream();     // Catch: Exception -> L12
    L12:
        e = e;
        r16 = r4;
        goto L114
    L8:
        r10 = false;
        goto L141
    L118:
        this.exception = new RuntimeException("too many retries");
        return null;
    }

    @Override // android.os.AsyncTask
    public /* bridge */ /* synthetic */ File doInBackground(String[] r1) {
        return doInBackground2(r1);
    }

    /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
    public void onPostExecute2(File r2) {
        Utilities.Callback<File> r0 = this.doneCallback;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.exception != null) goto L8;
        r0.run(r2);
        return;
    L8:
        r0.run(null);
    }

    @Override // android.os.AsyncTask
    public /* bridge */ /* synthetic */ void onPostExecute(File r1) {
        onPostExecute2(r1);
    }

    @Keep
    public HttpGetFileTask setDestFile(File r1) {
        this.file = r1;
        return this;
    }

    @Keep
    public HttpGetFileTask setMaxSize(long r1) {
        this.max_size = r1;
        return this;
    }

    @Keep
    public HttpGetFileTask setOverrideExtension(String r1) {
        this.overrideExt = r1;
        return this;
    }
}
