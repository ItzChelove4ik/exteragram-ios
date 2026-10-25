package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzcf implements ObjectEncoderContext {
    private static final Charset zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final ObjectEncoder zzd = null;
    private OutputStream zze;
    private final Map zzf;
    private final Map zzg;
    private final ObjectEncoder zzh;
    private final zzcj zzi;

    static {
        zza = Charset.forName("UTF-8");
        FieldDescriptor.Builder r0 = FieldDescriptor.builder(PluginsConstants.Settings.KEY);
        zzbz r1 = new zzbz();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("value");
        zzbz r12 = new zzbz();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        zzd = zzce.zza;
    }

    public zzcf(OutputStream r2, Map r3, Map r4, ObjectEncoder r5) {
        this.zzi = new zzcj(this);
        this.zze = r2;
        this.zzf = r3;
        this.zzg = r4;
        this.zzh = r5;
    }

    public static /* synthetic */ void zzg(Map.Entry r2, ObjectEncoderContext r3) {
        r3.add(zzb, r2.getKey());
        r3.add(zzc, r2.getValue());
    }

    private static int zzh(FieldDescriptor r1) {
        zzcd r12 = (zzcd) r1.getProperty(zzcd.class);
        if (r12 == null) goto L7;
        return r12.zza();
    L7:
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final long zzi(ObjectEncoder r4, Object r5) {
        zzca r1 = new zzca();
        OutputStream r2 = this.zze;     // Catch: Throwable -> L8
        this.zze = r1;     // Catch: Throwable -> L8
        r4.encode(r5, this);     // Catch: Throwable -> L10
        this.zze = r2;     // Catch: Throwable -> L8
        long r3 = r1.zza();     // Catch: Throwable -> L8
        r1.close();
        return r3;
    L10:
        th = move-exception;
        this.zze = r2;     // Catch: Throwable -> L8
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L15
    L17:
        throw th;
    L15:
        th = move-exception;
        Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class}).invoke(th, new Object[]{th});     // Catch: Exception -> L18
        goto L17
    }

    private static zzcd zzj(FieldDescriptor r1) {
        zzcd r12 = (zzcd) r1.getProperty(zzcd.class);
        if (r12 == null) goto L6;
        return r12;
    L6:
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final zzcf zzk(ObjectEncoder r5, FieldDescriptor r6, Object r7, boolean r8) {
        long r0 = zzi(r5, r7);
        if (r8 == true) goto L5;
    L7:
        zzn((zzh(r6) << 3) | 2);
        zzo(r0);
        r5.encode(r7, this);
        return this;
    L5:
        if (r0 != 0) goto L7;
        return this;
    }

    private final zzcf zzl(ValueEncoder r2, FieldDescriptor r3, Object r4, boolean r5) {
        this.zzi.zza(r3, r5);
        r2.encode(r4, this.zzi);
        return this;
    }

    private static ByteBuffer zzm(int r1) {
        return ByteBuffer.allocate(r1).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void zzn(int r5) {
    L2:
        long r0 = r5 & (-128);
        int r1 = r5 & 127;
        OutputStream r2 = this.zze;
        if (r0 == 0) goto L5;
        r2.write(r1 | 128);
        r5 = r5 >>> 7;
        goto L2
    L5:
        r2.write(r1);
    }

    private final void zzo(long r6) {
    L2:
        long r0 = (-128) & r6;
        int r1 = ((int) r6) & 127;
        OutputStream r2 = this.zze;
        if (r0 == 0) goto L5;
        r2.write(r1 | 128);
        r6 = r6 >>> 7;
        goto L2
    L5:
        r2.write(r1);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final ObjectEncoderContext add(FieldDescriptor r2, double r3) {
        zza(r2, r3, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final /* synthetic */ ObjectEncoderContext add(FieldDescriptor r2, int r3) {
        zzd(r2, r3, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final /* synthetic */ ObjectEncoderContext add(FieldDescriptor r2, long r3) {
        zze(r2, r3, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final ObjectEncoderContext add(FieldDescriptor r2, Object r3) {
        zzc(r2, r3, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final /* synthetic */ ObjectEncoderContext add(FieldDescriptor r2, boolean r3) {
        zzd(r2, r3 ? 1 : 0, true);
        return this;
    }

    public final ObjectEncoderContext zza(FieldDescriptor r3, double r4, boolean r6) {
        if (r6 == true) goto L4;
    L6:
        zzn((zzh(r3) << 3) | 1);
        this.zze.write(zzm(8).putDouble(r4).array());
        return this;
    L4:
        if (r4 != 0.0d) goto L6;
        return this;
    }

    public final ObjectEncoderContext zzb(FieldDescriptor r1, float r2, boolean r3) {
        if (r3 == true) goto L4;
    L6:
        zzn((zzh(r1) << 3) | 5);
        this.zze.write(zzm(4).putFloat(r2).array());
        return this;
    L4:
        if (r2 != 0.0f) goto L6;
        return this;
    }

    public final ObjectEncoderContext zzc(FieldDescriptor r3, Object r4, boolean r5) {
        if (r4 != null) goto L5;
    L47:
        return this;
    L5:
        if ((r4 instanceof CharSequence) == false) goto L14;
        CharSequence r42 = (CharSequence) r4;
        if (r5 == true) goto L9;
    L11:
        zzn((zzh(r3) << 3) | 2);
        byte[] r32 = r42.toString().getBytes(zza);
        zzn(r32.length);
        this.zze.write(r32);
        return this;
    L9:
        if (r42.length() != 0) goto L11;
    L14:
        if ((r4 instanceof Collection) == false) goto L20;
        Iterator r43 = ((Collection) r4).iterator();
    L17:
        if (r43.hasNext() == false) goto L47;
        zzc(r3, r43.next(), false);
        goto L17
    L20:
        if ((r4 instanceof Map) == false) goto L26;
        Iterator r44 = ((Map) r4).entrySet().iterator();
    L23:
        if (r44.hasNext() == false) goto L47;
        Map.Entry r52 = (Map.Entry) r44.next();
        zzk(zzd, r3, r52, false);
        goto L23
    L26:
        if ((r4 instanceof Double) == false) goto L30;
        zza(r3, ((Double) r4).doubleValue(), r5);
        return this;
    L30:
        if ((r4 instanceof Float) == false) goto L34;
        zzb(r3, ((Float) r4).floatValue(), r5);
        return this;
    L34:
        if ((r4 instanceof Number) == false) goto L38;
        zze(r3, ((Number) r4).longValue(), r5);
        return this;
    L38:
        if ((r4 instanceof Boolean) == false) goto L42;
        zzd(r3, ((Boolean) r4).booleanValue() ? 1 : 0, r5);
        return this;
    L42:
        if ((r4 instanceof byte[]) == false) goto L50;
        byte[] r45 = (byte[]) r4;
        if (r5 == true) goto L46;
    L48:
        zzn((zzh(r3) << 3) | 2);
        zzn(r45.length);
        this.zze.write(r45);
        return this;
    L46:
        if (r45.length != 0) goto L48;
    L50:
        ObjectEncoder r0 = (ObjectEncoder) this.zzf.get(r4.getClass());
        if (r0 == null) goto L54;
        zzk(r0, r3, r4, r5);
        return this;
    L54:
        ValueEncoder r02 = (ValueEncoder) this.zzg.get(r4.getClass());
        if (r02 == null) goto L59;
        zzl(r02, r3, r4, r5);
        return this;
    L59:
        if ((r4 instanceof zzcb) == false) goto L63;
        zzd(r3, ((zzcb) r4).zza(), true);
        return this;
    L63:
        if ((r4 instanceof Enum) == false) goto L66;
        zzd(r3, ((Enum) r4).ordinal(), true);
        return this;
    L66:
        zzk(this.zzh, r3, r4, r5);
        return this;
    }

    public final zzcf zzd(FieldDescriptor r2, int r3, boolean r4) {
        if (r4 == false) goto L4;
        if (r3 != 0) goto L4;
    L10:
        return this;
    L4:
        zzcd r22 = zzj(r2);
        zzcc r42 = zzcc.zza;
        int r43 = r22.zzb().ordinal();
        if (r43 != 0) goto L7;
        zzn(r22.zza() << 3);
        zzn(r3);
        return this;
    L7:
        if (r43 != 1) goto L9;
        zzn(r22.zza() << 3);
        zzn((r3 + r3) ^ (r3 >> 31));
        return this;
    L9:
        if (r43 != 2) goto L10;
        zzn((r22.zza() << 3) | 5);
        this.zze.write(zzm(4).putInt(r3).array());
        return this;
    }

    public final zzcf zze(FieldDescriptor r3, long r4, boolean r6) {
        if (r6 == true) goto L4;
    L5:
        zzcd r32 = zzj(r3);
        zzcc r62 = zzcc.zza;
        int r63 = r32.zzb().ordinal();
        if (r63 != 0) goto L8;
        zzn(r32.zza() << 3);
        zzo(r4);
        return this;
    L8:
        if (r63 != 1) goto L10;
        zzn(r32.zza() << 3);
        long r33 = r4 >> 63;
        zzo(r33 ^ (r4 + r4));
        return this;
    L10:
        if (r63 == 2) goto L12;
    L11:
        return this;
    L12:
        zzn((r32.zza() << 3) | 1);
        this.zze.write(zzm(8).putLong(r4).array());
        return this;
    L4:
        if (r4 == 0) goto L11;
        goto L5
    }

    public final zzcf zzf(Object r3) {
        if (r3 != null) goto L4;
        return this;
    L4:
        ObjectEncoder r0 = (ObjectEncoder) this.zzf.get(r3.getClass());
        if (r0 == null) goto L9;
        r0.encode(r3, this);
        return this;
    L9:
        throw new EncodingException("No encoder for ".concat(String.valueOf(r3.getClass())));
    }
}
