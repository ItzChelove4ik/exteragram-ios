package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public final class zzty {
    private static final Logger zza = null;
    private static final zzty zzd = null;
    private final ConcurrentMap zzb;
    private final ConcurrentMap zzc;

    static {
        zza = Logger.getLogger(zzty.class.getName());
        zzd = new zzty();
    }

    public zzty() {
        this.zzb = new ConcurrentHashMap();
        this.zzc = new ConcurrentHashMap();
    }

    public static zzty zza() {
        return zzd;
    }

    private final synchronized void zzd(zzsf r6, boolean r7, boolean r8) {
        monitor-enter(this);
        String r72 = r6.zza();     // Catch: Throwable -> L12
        if (r8 == false) goto L14;
        ConcurrentMap r0 = this.zzc;     // Catch: Throwable -> L12
        if (r0.containsKey(r72) == false) goto L14;
        if (((Boolean) r0.get(r72)).booleanValue() == true) goto L14;
        throw new GeneralSecurityException("New keys are already disallowed for key type ".concat(r72));     // Catch: Throwable -> L12
    L14:
        ConcurrentMap r02 = this.zzb;     // Catch: Throwable -> L12
        zzsf r1 = (zzsf) r02.get(r72);     // Catch: Throwable -> L12
        if (r1 != null) goto L17;
    L21:
        r02.putIfAbsent(r72, r6);     // Catch: Throwable -> L12
        this.zzc.put(r72, Boolean.valueOf(r8));     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L17:
        if (r1.getClass().equals(r6.getClass()) == true) goto L21;
        String r82 = "Attempted overwrite of a registered key manager for key type ".concat(r72);     // Catch: Throwable -> L12
        zza.logp(Level.WARNING, "com.google.crypto.tink.internal.KeyManagerRegistry", "insertKeyManager", r82);     // Catch: Throwable -> L12
        throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{r72, r1.getClass().getName(), r6.getClass().getName()}));     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    }

    public final synchronized void zzb(zzsf r2, boolean r3) {
        monitor-enter(this);
        zzc(r2, 1, r3);     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final synchronized void zzc(zzsf r1, int r2, boolean r3) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (zzte.zza(r2) == false) goto L11;
        zzd(r1, false, r3);     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L11:
        throw new GeneralSecurityException("Cannot register key manager: FIPS compatibility insufficient");     // Catch: Throwable -> L8
    }
}
