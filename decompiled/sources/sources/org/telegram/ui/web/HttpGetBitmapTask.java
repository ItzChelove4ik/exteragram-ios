package org.telegram.ui.web;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public class HttpGetBitmapTask extends AsyncTask<String, Void, Bitmap> {
    private final Utilities.Callback<Bitmap> callback;
    private Exception exception;
    private final HashMap<String, String> headers;

    public HttpGetBitmapTask(Utilities.Callback<Bitmap> r2) {
        this.headers = new HashMap();
        this.callback = r2;
    }

    /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
    public Bitmap doInBackground2(String... r6) {
        HttpURLConnection r62 = (HttpURLConnection) new URL(r6[0]).openConnection();     // Catch: Exception -> L12
        Iterator<Map.Entry<String, String>> r2 = this.headers.entrySet().iterator();     // Catch: Exception -> L12
    L5:
        if (r2.hasNext() == false) goto L14;
        Map.Entry<String, String> r3 = r2.next();     // Catch: Exception -> L12
        if (r3.getKey() == null) goto L5;
        if (r3.getValue() == null) goto L5;
        r62.setRequestProperty(r3.getKey(), r3.getValue());     // Catch: Exception -> L12
        goto L5
    L14:
        r62.setRequestMethod("GET");     // Catch: Exception -> L12
        r62.setDoInput(true);     // Catch: Exception -> L12
        int r22 = r62.getResponseCode();     // Catch: Exception -> L12
        if (r22 >= 200) goto L17;
    L26:
        r62.disconnect();     // Catch: Exception -> L12
        return null;
    L17:
        if (r22 >= 300) goto L26;
        if (r62.getContentType() == null) goto L25;
        if (r62.getContentType().contains("svg") == false) goto L25;
        return SvgHelper.getBitmap(new BufferedInputStream(r62.getInputStream()), 64, 64, false);
    L25:
        return BitmapFactory.decodeStream(new BufferedInputStream(r62.getInputStream()));
    L12:
        e = move-exception;
        this.exception = e;
        return null;
    }

    @Override // android.os.AsyncTask
    public /* bridge */ /* synthetic */ Bitmap doInBackground(String[] r1) {
        return doInBackground2(r1);
    }

    /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
    public void onPostExecute2(Bitmap r2) {
        Utilities.Callback<Bitmap> r0 = this.callback;
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
    public /* bridge */ /* synthetic */ void onPostExecute(Bitmap r1) {
        onPostExecute2(r1);
    }
}
