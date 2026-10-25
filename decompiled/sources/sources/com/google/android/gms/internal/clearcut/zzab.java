package com.google.android.gms.internal.clearcut;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public final class zzab {
    private static final ConcurrentHashMap<Uri, zzab> zzde = null;
    private static final String[] zzdl = null;
    private final Uri uri;
    private final ContentResolver zzdf;
    private final ContentObserver zzdg;
    private final Object zzdh;
    private volatile Map<String, String> zzdi;
    private final Object zzdj;
    private final List<Object> zzdk;

    static {
        zzde = new ConcurrentHashMap();
        zzdl = new String[]{PluginsConstants.Settings.KEY, "value"};
    }

    private zzab(ContentResolver r2, Uri r3) {
        this.zzdh = new Object();
        this.zzdj = new Object();
        this.zzdk = new ArrayList();
        this.zzdf = r2;
        this.uri = r3;
        this.zzdg = new zzac(this, null);
    }

    public static zzab zza(ContentResolver r3, Uri r4) {
        ConcurrentHashMap<Uri, zzab> r0 = zzde;
        zzab r1 = r0.get(r4);
        if (r1 != null) goto L9;
        zzab r12 = new zzab(r3, r4);
        zzab r32 = r0.putIfAbsent(r4, r12);
        if (r32 != null) goto L8;
        r12.zzdf.registerContentObserver(r12.uri, false, r12.zzdg);
        return r12;
    L8:
        return r32;
    L9:
        return r1;
    }

    public static /* synthetic */ void zza(zzab r0) {
        r0.zzj();
    }

    private final Map<String, String> zzi() {
        HashMap r0 = new HashMap();     // Catch: Throwable -> L15
        Cursor r7 = this.zzdf.query(this.uri, zzdl, null, null, null);     // Catch: Throwable -> L15
        if (r7 != null) goto L18;
        return r0;
    L18:
        if (r7.moveToNext() == false) goto L10;
        r0.put(r7.getString(0), r7.getString(1));     // Catch: Throwable -> L8
        goto L18
    L10:
        r7.close();     // Catch: Throwable -> L15 Throwable -> L15
        return r0;
    L8:
        th = move-exception;
        r7.close();     // Catch: Throwable -> L15 Throwable -> L15
        throw th;     // Catch: Throwable -> L15 Throwable -> L15
    L15:
        Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
        return null;
    }

    private final void zzj() {
        Object r0 = this.zzdj;
        monitor-enter(r0);
        Iterator<Object> r2 = this.zzdk.iterator();     // Catch: Throwable -> L8
        if (r2.hasNext() == true) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.next());     // Catch: Throwable -> L8
        throw null;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public final Map<String, String> zzg() {
        if (zzae.zza("gms:phenotype:phenotype_flag:debug_disable_caching", false) == false) goto L5;
        Map<String, String> r0 = zzi();
    L6:
        if (r0 != null) goto L18;
        Object r1 = this.zzdh;
        monitor-enter(r1);
        r0 = this.zzdi;     // Catch: Throwable -> L12
        if (r0 != null) goto L14;
        r0 = zzi();     // Catch: Throwable -> L12
        this.zzdi = r0;     // Catch: Throwable -> L12
    L14:
        monitor-exit(r1);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    L18:
        if (r0 == null) goto L21;
        return r0;
    L21:
        return Collections.EMPTY_MAP;
    L5:
        r0 = this.zzdi;
        goto L6
    }

    public final void zzh() {
        Object r0 = this.zzdh;
        monitor-enter(r0);
        this.zzdi = null;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
