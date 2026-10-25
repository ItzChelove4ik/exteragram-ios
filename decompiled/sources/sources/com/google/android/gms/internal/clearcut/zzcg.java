package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import com.google.android.gms.internal.clearcut.zzcg.zza;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzcg<MessageType extends zzcg<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzas<MessageType, BuilderType> {
    private static Map<Object, zzcg<?, ?>> zzjr;
    protected zzey zzjp;
    private int zzjq;

    public static abstract class zza<MessageType extends zzcg<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzat<MessageType, BuilderType> {
        private final MessageType zzjs;
        protected MessageType zzjt;
        protected boolean zzju;

        public zza(MessageType r3) {
            this.zzjs = r3;
            this.zzjt = (MessageType) r3.zza(zzg.zzkg, null, null);
            this.zzju = false;
        }

        private static void zza(MessageType r1, MessageType r2) {
            zzea.zzcm().zzp(r1).zzc(r1, r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public /* synthetic */ Object clone() {
            zza r0 = (zza) this.zzjs.zza(zzg.zzkh, null, null);
            r0.zza((zzcg) zzbi());
            return r0;
        }

        @Override // com.google.android.gms.internal.clearcut.zzat
        public final /* synthetic */ zzat zza(zzas r1) {
            return zza((zzcg) r1);
        }

        public final BuilderType zza(MessageType r2) {
            zzbf();
            zza(this.zzjt, r2);
            return this;
        }

        @Override // com.google.android.gms.internal.clearcut.zzdq
        public final /* synthetic */ zzdo zzbe() {
            return this.zzjs;
        }

        public void zzbf() {
            if (this.zzju == false) goto L6;
            MessageType r0 = (MessageType) this.zzjt.zza(zzg.zzkg, null, null);
            zza(r0, this.zzjt);
            this.zzjt = r0;
            this.zzju = false;
            return;
        }

        public MessageType zzbg() {
            boolean r0 = this.zzju;
            MessageType r1 = this.zzjt;
            if (r0 == false) goto L5;
            return r1;
        L5:
            zzea.zzcm().zzp(r1).zzc(r1);
            this.zzju = true;
            return this.zzjt;
        }

        public final MessageType zzbh() {
            MessageType r4 = (MessageType) zzbi();
            byte r0 = ((Byte) r4.zza(zzg.zzkd, null, null)).byteValue();
            boolean r2 = true;
            if (r0 == 1) goto L12;
            if (r0 != 0) goto L7;
            r2 = false;
            goto L12
        L7:
            r2 = zzea.zzcm().zzp(r4).zzo(r4);
            int r02 = zzg.zzke;
            if (r2 == false) goto L10;
            MessageType r3 = r4;
        L11:
            r4.zza(r02, r3, null);
            goto L12
        L10:
            r3 = null;
        L12:
            if (r2 == false) goto L15;
            return r4;
        L15:
            throw new zzew(r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzdp
        public /* synthetic */ zzdo zzbi() {
            return zzbg();
        }
    }

    public static class zzb<T extends zzcg<T, ?>> extends zzau<T> {
        private T zzjs;

        public zzb(T r1) {
            this.zzjs = r1;
        }
    }

    public enum zzg {
        public static final int zzkd = 1;
        public static final int zzke = 2;
        public static final int zzkf = 3;
        public static final int zzkg = 4;
        public static final int zzkh = 5;
        public static final int zzki = 6;
        public static final int zzkj = 7;
        private static final /* synthetic */ int[] zzkk = null;
        public static final int zzkl = 0;
        public static final int zzkm = 0;
        private static final /* synthetic */ int[] zzkn = null;
        public static final int zzko = 0;
        public static final int zzkp = 0;
        private static final /* synthetic */ int[] zzkq = null;

        static {
            zzkk = new int[]{1, 2, 3, 4, 5, 6, 7};
            zzkl = 1;
            zzkm = 2;
            zzkn = new int[]{1, 2};
            zzko = 1;
            zzkp = 2;
            zzkq = new int[]{1, 2};
        }

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzkk.clone();
        }
    }

    static {
        zzjr = new ConcurrentHashMap();
    }

    public zzcg() {
        this.zzjp = zzey.zzea();
        this.zzjq = -1;
    }

    private static <T extends zzcg<T, ?>> T zza(T r6, byte[] r7) {
        T r1 = (T) r6.zza(zzg.zzkg, null, null);
        zzea.zzcm().zzp(r1).zza(r1, r7, 0, r7.length, new zzay());     // Catch: IOException -> L8 IndexOutOfBoundsException -> L10
        zzea.zzcm().zzp(r1).zzc(r1);     // Catch: IOException -> L8 IndexOutOfBoundsException -> L10
        if (r1.zzex != 0) goto L7;
        return r1;
    L7:
        throw new RuntimeException();     // Catch: IOException -> L8 IndexOutOfBoundsException -> L10
    L8:
        e = move-exception;
        if ((e.getCause() instanceof zzco) == false) goto L17;
        throw ((zzco) e.getCause());
    L17:
        throw new zzco(e.getMessage()).zzg(r1);
    L11:
        throw zzco.zzbl().zzg(r1);
    }

    public static Object zza(zzdo r1, String r2, Object[] r3) {
        return new zzec(r1, r2, r3);
    }

    public static Object zza(Method r1, Object r2, Object... r3) {
        return r1.invoke(r2, r3);
    L17:
        Throwable e = e;
        String r22 = "Couldn't use Java reflection to implement protocol message reflection.";
    L11:
        Make$Map$$ExternalSyntheticBUOutline0.m(r22, e);
        return null;
    L5:
        e = move-exception;
        e = e.getCause();
        if ((e instanceof RuntimeException) == true) goto L16;
        if ((e instanceof Error) == true) goto L14;
        r22 = "Unexpected exception thrown by generated accessor method.";
        goto L11
    L14:
        throw ((Error) e);
    L16:
        throw ((RuntimeException) e);
    }

    public static <T extends zzcg<?, ?>> void zza(Class<T> r1, T r2) {
        zzjr.put(r1, r2);
    }

    public static <T extends zzcg<T, ?>> T zzb(T r3, byte[] r4) {
        T r32 = (T) zza(r3, r4);
        if (r32 == null) goto L18;
        byte r42 = ((Byte) r32.zza(zzg.zzkd, null, null)).byteValue();
        boolean r1 = true;
        if (r42 == 1) goto L14;
        if (r42 != 0) goto L9;
        r1 = false;
        goto L14
    L9:
        r1 = zzea.zzcm().zzp(r32).zzo(r32);
        int r43 = zzg.zzke;
        if (r1 == false) goto L12;
        Object r2 = r32;
    L13:
        r32.zza(r43, r2, null);
        goto L14
    L12:
        r2 = null;
    L14:
        if (r1 == true) goto L18;
        throw new zzco(new zzew(r32).getMessage()).zzg(r32);
    L18:
        return r32;
    }

    public static <E> zzcn<E> zzbb() {
        return zzeb.zzcn();
    }

    public static <T extends zzcg<?, ?>> T zzc(Class<T> r3) {
        T r0 = (T) zzjr.get(r3);
        if (r0 == null) goto L17;
    L9:
        if (r0 != null) goto L16;
        String r32 = r3.getName();
        if (r32.length() == 0) goto L13;
        String r33 = "Unable to get default instance for: ".concat(r32);
    L15:
        throw new IllegalStateException(r33);
    L13:
        r33 = new String("Unable to get default instance for: ");
        goto L15
    L16:
        return r0;
    L17:
        Class.forName(r3.getName(), true, r3.getClassLoader());     // Catch: ClassNotFoundException -> L6
        r0 = (T) zzjr.get(r3);
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Class initialization cannot fail.", e);
        return null;
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L6;
        return true;
    L6:
        if (((zzcg) zza(zzg.zzki, null, null)).getClass().isInstance(r3) == true) goto L10;
        return false;
    L10:
        return zzea.zzcm().zzp(this).equals(this, (zzcg) r3);
    }

    public int hashCode() {
        int r0 = this.zzex;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        int r02 = zzea.zzcm().zzp(this).hashCode(this);
        this.zzex = r02;
        return r02;
    }

    public String toString() {
        return zzdr.zza(this, super.toString());
    }

    public abstract Object zza(int r1, Object r2, Object r3);

    @Override // com.google.android.gms.internal.clearcut.zzdo
    public final int zzas() {
        if (this.zzjq != (-1)) goto L6;
        this.zzjq = zzea.zzcm().zzp(this).zzm(this);
    L6:
        return this.zzjq;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdo
    public final void zzb(zzbn r3) {
        zzea.zzcm().zze(getClass()).zza(this, zzbp.zza(r3));
    }

    @Override // com.google.android.gms.internal.clearcut.zzdo
    public final /* synthetic */ zzdp zzbc() {
        zza r0 = (zza) zza(zzg.zzkh, null, null);
        r0.zza(this);
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdo
    public final /* synthetic */ zzdp zzbd() {
        return (zza) zza(zzg.zzkh, null, null);
    }

    @Override // com.google.android.gms.internal.clearcut.zzdq
    public final /* synthetic */ zzdo zzbe() {
        return (zzcg) zza(zzg.zzki, null, null);
    }

    @Override // com.google.android.gms.internal.clearcut.zzas
    public final void zzf(int r1) {
        this.zzjq = r1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzas
    public final int zzs() {
        return this.zzjq;
    }
}
