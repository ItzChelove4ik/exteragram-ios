package org.telegram.ui.web;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public class HttpGetTask extends AsyncTask<String, Void, String> {
    private final Utilities.Callback<String> callback;
    private Exception exception;
    private final HashMap<String, String> headers;

    public HttpGetTask(Utilities.Callback<String> r2) {
        this.headers = new HashMap();
        this.callback = r2;
    }

    @Override // android.os.AsyncTask
    public /* bridge */ /* synthetic */ String doInBackground(String[] r1) {
        return doInBackground2(r1);
    }

    /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
    public String doInBackground2(String... r4) {
        HttpURLConnection r42 = (HttpURLConnection) new URL(r4[0]).openConnection();     // Catch: Exception -> L12
        Iterator<Map.Entry<String, String>> r0 = this.headers.entrySet().iterator();     // Catch: Exception -> L12
    L5:
        if (r0.hasNext() == false) goto L14;
        Map.Entry<String, String> r1 = r0.next();     // Catch: Exception -> L12
        if (r1.getKey() == null) goto L5;
        if (r1.getValue() == null) goto L5;
        r42.setRequestProperty(r1.getKey(), r1.getValue());     // Catch: Exception -> L12
        goto L5
    L14:
        r42.setRequestMethod("GET");     // Catch: Exception -> L12
        r42.setDoInput(true);     // Catch: Exception -> L12
        int r02 = r42.getResponseCode();     // Catch: Exception -> L12
        if (r02 >= 200) goto L17;
    L19:
        BufferedReader r03 = new BufferedReader(new InputStreamReader(r42.getErrorStream()));     // Catch: Exception -> L12
    L20:
        StringBuilder r43 = new StringBuilder();     // Catch: Exception -> L12
    L21:
        String r12 = r03.readLine();     // Catch: Exception -> L12
        if (r12 == null) goto L24;
        r43.append(r12);     // Catch: Exception -> L12
        goto L21
    L24:
        r03.close();     // Catch: Exception -> L12
        return r43.toString();
    L17:
        if (r02 >= 300) goto L19;
        r03 = new BufferedReader(new InputStreamReader(r42.getInputStream()));     // Catch: Exception -> L12
    L12:
        e = move-exception;
        this.exception = e;
        return null;
    }

    @Override // android.os.AsyncTask
    public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
        onPostExecute2(r1);
    }

    /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
    public void onPostExecute2(String r2) {
        Utilities.Callback<String> r0 = this.callback;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.exception != null) goto L8;
        r0.run(r2);
        return;
    L8:
        r0.run(null);
    }
}
