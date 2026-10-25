package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes5.dex */
final class zzln implements Runnable {
    final /* synthetic */ zzlo zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzll zzd;
    private final String zze;
    private final Map zzf;

    public zzln(zzlo r1, String r2, URL r3, byte[] r4, Map r5, zzll r6) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        Preconditions.checkNotEmpty(r2);
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r6);
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r6;
        this.zze = r2;
        this.zzf = r5;
    }

    private final void zzb(int r8, Exception r9, byte[] r10, Map r11) {
        this.zza.zzu.zzaX().zzj(new zzlm(this, r8, r9, r10, r11));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.OutputStream] */
    @Override // java.lang.Runnable
    public final void run() {
        zzlo r1 = this.zza;
        r1.zzaY();
        int r2 = 0;
        URL r4 = this.zzb;     // Catch: Throwable -> L64 IOException -> L66
        int r5 = com.google.android.gms.internal.measurement.zzch.$r8$clinit;     // Catch: Throwable -> L64 IOException -> L66
        URLConnection r42 = r4.openConnection();     // Catch: Throwable -> L64 IOException -> L66
        if ((r42 instanceof HttpURLConnection) == false) goto L69;
        HttpURLConnection r43 = (HttpURLConnection) r42;     // Catch: Throwable -> L64 IOException -> L66
        r43.setDefaultUseCaches(false);     // Catch: Throwable -> L64 IOException -> L66
        zzic r12 = r1.zzu;     // Catch: Throwable -> L64 IOException -> L66
        r12.zzc();     // Catch: Throwable -> L64 IOException -> L66
        r43.setConnectTimeout(60000);     // Catch: Throwable -> L64 IOException -> L66
        r12.zzc();     // Catch: Throwable -> L64 IOException -> L66
        r43.setReadTimeout(61000);     // Catch: Throwable -> L64 IOException -> L66
        r43.setInstanceFollowRedirects(false);     // Catch: Throwable -> L64 IOException -> L66
        ?? r52 = 1;
        r43.setDoInput(true);     // Catch: Throwable -> L64 IOException -> L66
        Map r6 = this.zzf;     // Catch: Throwable -> L12 IOException -> L14
        if (r6 == null) goto L16;
        Iterator r62 = r6.entrySet().iterator();     // Catch: Throwable -> L12 IOException -> L14
    L10:
        if (r62.hasNext() == false) goto L16;
        Map.Entry r7 = (Map.Entry) r62.next();     // Catch: Throwable -> L12 IOException -> L14
        r43.addRequestProperty((String) r7.getKey(), (String) r7.getValue());     // Catch: Throwable -> L12 IOException -> L14
    L16:
        byte[] r63 = this.zzc;     // Catch: Throwable -> L12 IOException -> L14
        if (r63 != null) goto L99;
    L31:
        int r13 = r43.getResponseCode();     // Catch: Throwable -> L12 IOException -> L14
        Map<String, List<String>> r53 = r43.getHeaderFields();     // Catch: Throwable -> L56 IOException -> L60
        ByteArrayOutputStream r64 = new ByteArrayOutputStream();     // Catch: Throwable -> L49
        InputStream r72 = r43.getInputStream();     // Catch: Throwable -> L49
        byte[] r8 = new byte[1024];     // Catch: Throwable -> L39
    L36:
        int r9 = r72.read(r8);     // Catch: Throwable -> L39
        if (r9 <= 0) goto L41;
        r64.write(r8, 0, r9);     // Catch: Throwable -> L39
        goto L36
    L41:
        byte[] r22 = r64.toByteArray();     // Catch: Throwable -> L39
        r72.close();     // Catch: Throwable -> L45 IOException -> L47
        r43.disconnect();
        zzb(r13, null, r22, r53);
        return;
    L39:
        th = th;
    L51:
        if (r72 == null) goto L53;
        r72.close();     // Catch: Throwable -> L45 IOException -> L47
    L53:
        throw th;     // Catch: Throwable -> L45 IOException -> L47
    L49:
        th = th;
        r72 = null;
        goto L51
    L60:
        e = move-exception;
        r2 = r13;
        e = e;
    L62:
        Map r54 = null;
    L63:
        Map r65 = r54;
        ?? r55 = r54;
    L81:
        if (r55 != 0) goto L96;
    L86:
        if (r43 == null) goto L88;
        r43.disconnect();
    L88:
        zzb(r2, e, null, r65);
        return;
    L96:
        r55.close();     // Catch: IOException -> L84
    L84:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzc("Error closing HTTP compressed POST connection output stream. appId", zzgu.zzl(this.zze), e);
    L56:
        th = move-exception;
        r2 = r13;
        th = th;
    L58:
        Map r56 = null;
    L59:
        Map r66 = r56;
        ?? r57 = r56;
    L71:
        if (r57 != 0) goto L90;
    L76:
        if (r43 == null) goto L78;
        r43.disconnect();
    L78:
        zzb(r2, null, null, r66);
        throw th;
    L90:
        r57.close();     // Catch: IOException -> L74
    L74:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzc("Error closing HTTP compressed POST connection output stream. appId", zzgu.zzl(this.zze), e);
    L47:
        e = move-exception;
        r2 = r13;
        e = e;
        r65 = r52;
        r55 = 0;
    L45:
        th = move-exception;
        r2 = r13;
        th = th;
        r66 = r52;
        r57 = 0;
        goto L71
    L99:
        r12.zzaV();     // Catch: Throwable -> L12 IOException -> L28
        ByteArrayOutputStream r14 = new ByteArrayOutputStream();     // Catch: Throwable -> L12 IOException -> L28
        GZIPOutputStream r73 = new GZIPOutputStream(r14);     // Catch: Throwable -> L12 IOException -> L28
        r73.write(r63);     // Catch: Throwable -> L12 IOException -> L28
        r73.close();     // Catch: Throwable -> L12 IOException -> L28
        r14.close();     // Catch: Throwable -> L12 IOException -> L28
        byte[] r15 = r14.toByteArray();     // Catch: Throwable -> L12 IOException -> L28
        zzgs r67 = this.zza.zzu.zzaW().zzk();     // Catch: Throwable -> L12 IOException -> L14
        int r82 = r15.length;     // Catch: Throwable -> L12 IOException -> L14
        r67.zzb("Uploading data. size", Integer.valueOf(r82));     // Catch: Throwable -> L12 IOException -> L14
        r43.setDoOutput(true);     // Catch: Throwable -> L12 IOException -> L14
        r43.addRequestProperty("Content-Encoding", "gzip");     // Catch: Throwable -> L12 IOException -> L14
        r43.setFixedLengthStreamingMode(r82);     // Catch: Throwable -> L12 IOException -> L14
        r43.connect();     // Catch: Throwable -> L12 IOException -> L14
        OutputStream r58 = r43.getOutputStream();     // Catch: Throwable -> L12 IOException -> L14
        r58.write(r15);     // Catch: Throwable -> L22 IOException -> L24
        r58.close();     // Catch: Throwable -> L22 IOException -> L24
        r52 = r58;
    L24:
        e = e;
        r65 = null;
        r55 = r58;
    L22:
        th = th;
        r66 = null;
        r57 = r58;
    L28:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzb("Failed to gzip post request content", e);     // Catch: Throwable -> L12 IOException -> L14
        throw e;     // Catch: Throwable -> L12 IOException -> L14
    L14:
        e = e;
    L12:
        th = th;
        goto L58
    L69:
        throw new IOException("Failed to obtain HTTP connection");     // Catch: Throwable -> L64 IOException -> L66
    L66:
        e = e;
        r43 = null;
        r54 = null;
    L64:
        th = th;
        r43 = null;
        r56 = null;
        goto L59
    }

    public final /* synthetic */ void zza(int r7, Exception r8, byte[] r9, Map r10) {
        this.zzd.zza(this.zze, r7, r8, r9, r10);
    }
}
