package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.util.AbstractList;

/* loaded from: classes4.dex */
public abstract class zzaed {
    public static final byte[] zza = null;

    public class zza<T> extends AbstractList<T> {
        private final zzaeb zza;
        private final zzaec<T> zzb;

        public zza(zzaeb r1, zzaec<T> r2) {
            this.zza = r1;
            this.zzb = r2;
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int r2) {
            zzaeb r0 = this.zza;
            return (T) this.zzb.zza(r0.zzf(r2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.zza.size();
        }
    }

    static {
        byte[] r1 = new byte[0];
        zza = r1;
        ByteBuffer.wrap(r1);
        zzacv.zzN(r1, 0, 0, false);
    }

    public static int zza(boolean r0) {
        if (r0 == false) goto L5;
        return 1231;
    L5:
        return 1237;
    }

    public static int zzb(int r2, byte[] r3, int r4, int r5) {
        int r0 = r4;
    L4:
        if (r0 >= (r4 + r5)) goto L6;
        r2 = (r2 * 31) + r3[r0];
        r0 = r0 + 1;
        goto L4
    L6:
        return r2;
    }
}
