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

/* loaded from: classes5.dex */
final class zzgy implements Runnable {
    final /* synthetic */ zzgz zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzgw zzd;
    private final String zze;
    private final Map zzf;

    public zzgy(zzgz r1, String r2, URL r3, byte[] r4, Map r5, zzgw r6) {
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

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x00fe: MOVE (r9 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:51:0x00fc */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0101: MOVE (r10 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:52:0x0100 */
    @Override // java.lang.Runnable
    public final void run() {
        zzgz r0 = this.zza;
        r0.zzaY();
        OutputStream r3 = null;
        URL r4 = this.zzb;     // Catch: Throwable -> L60 IOException -> L62
        int r5 = com.google.android.gms.internal.measurement.zzch.$r8$clinit;     // Catch: Throwable -> L60 IOException -> L62
        URLConnection r42 = r4.openConnection();     // Catch: Throwable -> L60 IOException -> L62
        if ((r42 instanceof HttpURLConnection) == false) goto L65;
        HttpURLConnection r43 = (HttpURLConnection) r42;     // Catch: Throwable -> L60 IOException -> L62
        r43.setDefaultUseCaches(false);     // Catch: Throwable -> L60 IOException -> L62
        zzic r52 = r0.zzu;     // Catch: Throwable -> L60 IOException -> L62
        r52.zzc();     // Catch: Throwable -> L60 IOException -> L62
        r43.setConnectTimeout(60000);     // Catch: Throwable -> L60 IOException -> L62
        r52.zzc();     // Catch: Throwable -> L60 IOException -> L62
        r43.setReadTimeout(61000);     // Catch: Throwable -> L60 IOException -> L62
        r43.setInstanceFollowRedirects(false);     // Catch: Throwable -> L60 IOException -> L62
        r43.setDoInput(true);     // Catch: Throwable -> L60 IOException -> L62
        Map r7 = this.zzf;     // Catch: Throwable -> L12 IOException -> L14
        if (r7 == null) goto L16;
        Iterator r72 = r7.entrySet().iterator();     // Catch: Throwable -> L12 IOException -> L14
    L10:
        if (r72.hasNext() == false) goto L16;
        Map.Entry r8 = (Map.Entry) r72.next();     // Catch: Throwable -> L12 IOException -> L14
        r43.addRequestProperty((String) r8.getKey(), (String) r8.getValue());     // Catch: Throwable -> L12 IOException -> L14
    L16:
        byte[] r73 = this.zzc;     // Catch: Throwable -> L12 IOException -> L14
        if (r73 == null) goto L28;
        byte[] r02 = r0.zzg.zzp().zzv(r73);     // Catch: Throwable -> L12 IOException -> L14
        zzgs r53 = r52.zzaW().zzk();     // Catch: Throwable -> L12 IOException -> L14
        int r82 = r02.length;     // Catch: Throwable -> L12 IOException -> L14
        r53.zzb("Uploading data. size", Integer.valueOf(r82));     // Catch: Throwable -> L12 IOException -> L14
        r43.setDoOutput(true);     // Catch: Throwable -> L12 IOException -> L14
        r43.addRequestProperty("Content-Encoding", "gzip");     // Catch: Throwable -> L12 IOException -> L14
        r43.setFixedLengthStreamingMode(r82);     // Catch: Throwable -> L12 IOException -> L14
        r43.connect();     // Catch: Throwable -> L12 IOException -> L14
        OutputStream r54 = r43.getOutputStream();     // Catch: Throwable -> L12 IOException -> L14
        r54.write(r02);     // Catch: Throwable -> L21 IOException -> L23
        r54.close();     // Catch: Throwable -> L21 IOException -> L23
    L23:
        e = move-exception;
        IOException r83 = e;
        int r74 = 0;
        Map r10 = null;
        r3 = r54;
    L77:
        if (r3 != null) goto L90;
    L82:
        if (r43 == null) goto L84;
        r43.disconnect();
    L84:
        this.zza.zzu.zzaX().zzj(new zzgx(this.zze, this.zzd, r74, r83, null, r10, null));
        return;
    L90:
        r3.close();     // Catch: IOException -> L80
    L80:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzc("Error closing HTTP compressed POST connection output stream. appId", zzgu.zzl(this.zze), e);
    L21:
        th = th;
        int r6 = 0;
        Map r9 = null;
        r3 = r54;
    L26:
        Throwable r2 = th;
    L67:
        if (r3 != null) goto L88;
    L72:
        if (r43 == null) goto L74;
        r43.disconnect();
    L74:
        this.zza.zzu.zzaX().zzj(new zzgx(this.zze, this.zzd, r6, null, null, r9, null));
        throw r2;
    L88:
        r3.close();     // Catch: IOException -> L70
    L70:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzc("Error closing HTTP compressed POST connection output stream. appId", zzgu.zzl(this.zze), e);
    L28:
        int r84 = r43.getResponseCode();     // Catch: Throwable -> L12 IOException -> L14
        Map<String, List<String>> r11 = r43.getHeaderFields();     // Catch: Throwable -> L54 IOException -> L56
        ByteArrayOutputStream r03 = new ByteArrayOutputStream();     // Catch: Throwable -> L46
        InputStream r55 = r43.getInputStream();     // Catch: Throwable -> L46
        byte[] r62 = new byte[1024];     // Catch: Throwable -> L36
    L33:
        int r75 = r55.read(r62);     // Catch: Throwable -> L36
        if (r75 <= 0) goto L38;
        r03.write(r62, 0, r75);     // Catch: Throwable -> L36
        goto L33
    L38:
        byte[] r102 = r03.toByteArray();     // Catch: Throwable -> L36
        r55.close();     // Catch: Throwable -> L42 IOException -> L44
        r43.disconnect();
        this.zza.zzu.zzaX().zzj(new zzgx(this.zze, this.zzd, r84, null, r102, r11, null));
        return;
    L36:
        th = th;
    L48:
        if (r55 == null) goto L50;
        r55.close();     // Catch: Throwable -> L42 IOException -> L44
    L50:
        throw th;     // Catch: Throwable -> L42 IOException -> L44
    L46:
        th = th;
        r55 = null;
        goto L48
    L56:
        e = e;
        r10 = null;
        r74 = r84;
    L53:
        r83 = e;
    L54:
        th = move-exception;
        r2 = th;
        r9 = null;
        r6 = r84;
    L44:
        e = e;
        r74 = r84;
        r10 = r11;
    L42:
        th = move-exception;
        r2 = th;
        r6 = r84;
        r9 = r11;
        goto L67
    L14:
        e = move-exception;
        r83 = e;
        r74 = 0;
        r10 = null;
    L12:
        th = th;
        r6 = 0;
        r9 = null;
        goto L26
    L65:
        throw new IOException("Failed to obtain HTTP connection");     // Catch: Throwable -> L60 IOException -> L62
    L62:
        e = move-exception;
        r83 = e;
        r74 = 0;
        r43 = null;
        r10 = null;
    L60:
        th = th;
        r6 = 0;
        r43 = null;
        r9 = null;
        goto L26
    }
}
