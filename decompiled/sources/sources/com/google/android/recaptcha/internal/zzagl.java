package com.google.android.recaptcha.internal;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import kotlin.UByte;

/* loaded from: classes5.dex */
final class zzagl extends InputStream implements InputStreamRetargetInterface {
    private Iterator zza;
    private ByteBuffer zzb;
    private int zzc;
    private int zzd;
    private int zze;
    private boolean zzf;
    private byte[] zzg;
    private int zzh;

    public zzagl(Iterable r3) {
        this.zza = r3.iterator();
        this.zzc = 0;
        Iterator r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        ByteBuffer r1 = (ByteBuffer) r32.next();
        this.zzc++;
        goto L4
    L6:
        this.zzd = -1;
        if (zza() == true) goto L11;
        this.zzb = zzagf.zzb;
        this.zze = 0;
        return;
    }

    private final boolean zza() {
    L2:
        this.zzd++;
        if (this.zza.hasNext() == false) goto L4;
        ByteBuffer r0 = (ByteBuffer) this.zza.next();
        this.zzb = r0;
        if (r0.hasRemaining() == false) goto L2;
        this.zze = this.zzb.position();
        if (this.zzb.hasArray() == false) goto L10;
        this.zzf = true;
        this.zzg = this.zzb.array();
        this.zzh = this.zzb.arrayOffset();
    L11:
        return true;
    L10:
        this.zzf = false;
        this.zzg = null;
        goto L11
    L4:
        return false;
    }

    private final void zzb(int r2) {
        int r0 = this.zze + r2;
        this.zze = r0;
        if (r0 != this.zzb.limit()) goto L6;
        zza();
        return;
    }

    @Override // java.io.InputStream
    public final int read() {
        if (this.zzd != this.zzc) goto L7;
        return -1;
    L7:
        if (this.zzf == false) goto L10;
        int r0 = this.zzg[this.zze + this.zzh] & UByte.MAX_VALUE;
        zzb(1);
        return r0;
    L10:
        int r02 = this.zzb.get(this.zze) & UByte.MAX_VALUE;
        zzb(1);
        return r02;
    }

    @Override // java.io.InputStream
    public final int read(byte[] r4, int r5, int r6) {
        if (this.zzd != this.zzc) goto L6;
        return -1;
    L6:
        int r0 = this.zzb.limit();
        int r1 = this.zze;
        int r02 = r0 - r1;
        if (r6 <= r02) goto L10;
        r6 = r02;
    L10:
        if (this.zzf == false) goto L13;
        System.arraycopy(this.zzg, r1 + this.zzh, r4, r5, r6);
        zzb(r6);
        return r6;
    L13:
        int r03 = this.zzb.position();
        this.zzb.position(this.zze);
        this.zzb.get(r4, r5, r6);
        this.zzb.position(r03);
        zzb(r6);
        return r6;
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
