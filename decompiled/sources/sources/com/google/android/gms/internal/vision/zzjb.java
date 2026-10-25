package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import com.google.android.gms.internal.vision.zzjb.zzb;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzjb<MessageType extends zzjb<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzhf<MessageType, BuilderType> {
    private static Map<Object, zzjb<?, ?>> zzd;
    protected zzlx zzb;
    private int zzc;

    public static class zza<T extends zzjb<T, ?>> extends zzhg<T> {
        private final T zza;

        public zza(T r1) {
            this.zza = r1;
        }
    }

    public static abstract class zzb<MessageType extends zzjb<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzhe<MessageType, BuilderType> {
        protected MessageType zza;
        protected boolean zzb;
        private final MessageType zzc;

        public zzb(MessageType r3) {
            this.zzc = r3;
            this.zza = (MessageType) r3.zza(zzg.zzd, null, null);
            this.zzb = false;
        }

        private static void zza(MessageType r1, MessageType r2) {
            zzky.zza().zza(r1).zzb(r1, r2);
        }

        private final BuilderType zzb(byte[] r8, int r9, int r10, zzio r11) {
            if (this.zzb == false) goto L15;
            zzb();
            this.zzb = false;
        L15:
            zzky.zza().zza(this.zza).zza(this.zza, r8, 0, r10, new zzhn(r11));     // Catch: IOException -> L7 IndexOutOfBoundsException -> L10 zzjk -> L12
            return this;
        L12:
            e = move-exception;
            throw e;
        L7:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("Reading from byte array should not throw IOException.", e);
            return null;
        L11:
            throw zzjk.zza();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public /* synthetic */ Object clone() {
            zzb r0 = (zzb) this.zzc.zza(zzg.zze, null, null);
            r0.zza((zzjb) zze());
            return r0;
        }

        @Override // com.google.android.gms.internal.vision.zzhe
        public final /* synthetic */ zzhe zza(zzhf r1) {
            return zza((zzjb) r1);
        }

        @Override // com.google.android.gms.internal.vision.zzhe
        public final /* synthetic */ zzhe zza(byte[] r1, int r2, int r3, zzio r4) {
            return zzb(r1, 0, r3, r4);
        }

        public final BuilderType zza(MessageType r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zza(this.zza, r2);
            return this;
        }

        public void zzb() {
            MessageType r0 = (MessageType) this.zza.zza(zzg.zzd, null, null);
            zza(r0, this.zza);
            this.zza = r0;
        }

        public MessageType zzc() {
            boolean r0 = this.zzb;
            MessageType r1 = this.zza;
            if (r0 == false) goto L5;
            return r1;
        L5:
            zzky.zza().zza(r1).zzc(r1);
            this.zzb = true;
            return this.zza;
        }

        public final MessageType zzd() {
            MessageType r1 = (MessageType) zze();
            if (r1.zzk() == false) goto L6;
            return r1;
        L6:
            throw new zzlv(r1);
        }

        @Override // com.google.android.gms.internal.vision.zzkn
        public /* synthetic */ zzkk zze() {
            return zzc();
        }

        public /* synthetic */ zzkk zzf() {
            return zzd();
        }

        @Override // com.google.android.gms.internal.vision.zzkm
        public final /* synthetic */ zzkk zzr() {
            return this.zzc;
        }
    }

    public static class zze<ContainingType extends zzkk, Type> extends zzim<ContainingType, Type> {
    }

    public enum zzg {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzh = null;

        static {
            zzh = new int[]{1, 2, 3, 4, 5, 6, 7};
        }

        public static int[] zza() {
            return (int[]) zzh.clone();
        }
    }

    static {
        zzd = new ConcurrentHashMap();
    }

    public zzjb() {
        this.zzb = zzlx.zza();
        this.zzc = -1;
    }

    public static <T extends zzjb<?, ?>> T zza(Class<T> r4) {
        T r0 = (T) zzd.get(r4);
        if (r0 == null) goto L17;
    L9:
        if (r0 != null) goto L16;
        T r02 = (T) ((zzjb) zzma.zza(r4)).zza(zzg.zzf, null, null);
        if (r02 == null) goto L14;
        zzd.put(r4, r02);
        return r02;
    L14:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L16:
        return r0;
    L17:
        Class.forName(r4.getName(), true, r4.getClassLoader());     // Catch: ClassNotFoundException -> L6
        r0 = (T) zzd.get(r4);
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Class initialization cannot fail.", e);
        return null;
    }

    public static <E> zzjl<E> zza(zzjl<E> r1) {
        int r0 = r1.size();
        if (r0 != 0) goto L5;
        int r02 = 10;
    L7:
        return r1.zza(r02);
    L5:
        r02 = r0 << 1;
        goto L7
    }

    public static Object zza(zzkk r1, String r2, Object[] r3) {
        return new zzla(r1, r2, r3);
    }

    public static Object zza(Method r1, Object r2, Object... r3) {
        return r1.invoke(r2, r3);
    L16:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Couldn't use Java reflection to implement protocol message reflection.", e);
        return null;
    L5:
        e = move-exception;
        Throwable r12 = e.getCause();
        if ((r12 instanceof RuntimeException) == true) goto L15;
        if ((r12 instanceof Error) == true) goto L13;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unexpected exception thrown by generated accessor method.", r12);
        return null;
    L13:
        throw ((Error) r12);
    L15:
        throw ((RuntimeException) r12);
    }

    public static <T extends zzjb<?, ?>> void zza(Class<T> r1, T r2) {
        zzd.put(r1, r2);
    }

    public static final <T extends zzjb<T, ?>> boolean zza(T r3, boolean r4) {
        byte r0 = ((Byte) r3.zza(zzg.zza, null, null)).byteValue();
        if (r0 != 1) goto L5;
        return true;
    L5:
        if (r0 != 0) goto L8;
        return false;
    L8:
        boolean r02 = zzky.zza().zza(r3).zzd(r3);
        if (r4 == false) goto L15;
        int r42 = zzg.zzb;
        if (r02 == false) goto L13;
        Object r2 = r3;
    L14:
        r3.zza(r42, r2, null);
        goto L15
    L13:
        r2 = null;
    L15:
        return r02;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzjd, com.google.android.gms.internal.vision.zzjj] */
    public static zzjj zzn() {
        return zzjd.zzd();
    }

    public static <E> zzjl<E> zzo() {
        return zzlb.zzd();
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L9;
        return false;
    L9:
        if (getClass() == r4.getClass()) goto L12;
        return false;
    L12:
        return zzky.zza().zza(this).zza(this, (zzjb) r4);
    }

    public int hashCode() {
        int r0 = this.zza;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        int r02 = zzky.zza().zza(this).zza(this);
        this.zza = r02;
        return r02;
    }

    public String toString() {
        return zzkp.zza(this, super.toString());
    }

    public abstract Object zza(int r1, Object r2, Object r3);

    @Override // com.google.android.gms.internal.vision.zzkk
    public final void zza(zzii r2) {
        zzky.zza().zza(this).zza(this, zzil.zza(r2));
    }

    @Override // com.google.android.gms.internal.vision.zzhf
    public final void zzb(int r1) {
        this.zzc = r1;
    }

    @Override // com.google.android.gms.internal.vision.zzhf
    public final int zzi() {
        return this.zzc;
    }

    public final <MessageType extends zzjb<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzj() {
        return (BuilderType) zza(zzg.zze, null, null);
    }

    public final boolean zzk() {
        return zza(this, true);
    }

    @Override // com.google.android.gms.internal.vision.zzkk
    public final int zzm() {
        if (this.zzc != (-1)) goto L6;
        this.zzc = zzky.zza().zza(this).zzb(this);
    L6:
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.vision.zzkk
    public final /* synthetic */ zzkn zzp() {
        zzb r0 = (zzb) zza(zzg.zze, null, null);
        r0.zza(this);
        return r0;
    }

    @Override // com.google.android.gms.internal.vision.zzkk
    public final /* synthetic */ zzkn zzq() {
        return (zzb) zza(zzg.zze, null, null);
    }

    @Override // com.google.android.gms.internal.vision.zzkm
    public final /* synthetic */ zzkk zzr() {
        return (zzjb) zza(zzg.zzf, null, null);
    }
}
