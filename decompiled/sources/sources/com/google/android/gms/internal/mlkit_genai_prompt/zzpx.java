package com.google.android.gms.internal.mlkit_genai_prompt;

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
final class zzpx implements ObjectEncoderContext {
    private static final Charset zza = null;
    private static final FieldDescriptor zzg = null;
    private static final FieldDescriptor zzh = null;
    private static final ObjectEncoder zzi = null;
    private OutputStream zzb;
    private final Map zzc;
    private final Map zzd;
    private final ObjectEncoder zze;
    private final zzqb zzf;

    static {
        zza = Charset.forName("UTF-8");
        FieldDescriptor.Builder r0 = FieldDescriptor.builder(PluginsConstants.Settings.KEY);
        zzpr r1 = new zzpr();
        r1.zza(1);
        zzg = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("value");
        zzpr r12 = new zzpr();
        r12.zza(2);
        zzh = r02.withProperty(r12.zzb()).build();
        zzi = zzpw.zza;
    }

    public zzpx(OutputStream r2, Map r3, Map r4, ObjectEncoder r5) {
        this.zzf = new zzqb(this);
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    public static /* synthetic */ void zzg(Map.Entry r2, ObjectEncoderContext r3) {
        r3.add(zzg, r2.getKey());
        r3.add(zzh, r2.getValue());
    }

    private final zzpx zzh(ObjectEncoder r5, FieldDescriptor r6, Object r7, boolean r8) {
        long r0 = zzi(r5, r7);
        if (r8 == true) goto L5;
    L7:
        zzn((zzl(r6) << 3) | 2);
        zzo(r0);
        r5.encode(r7, this);
        return this;
    L5:
        if (r0 != 0) goto L7;
        return this;
    }

    private final long zzi(ObjectEncoder r3, Object r4) {
        zzps r0 = new zzps();
        OutputStream r1 = this.zzb;     // Catch: Throwable -> L8
        this.zzb = r0;     // Catch: Throwable -> L8
        r3.encode(r4, this);     // Catch: Throwable -> L10
        this.zzb = r1;     // Catch: Throwable -> L8
        long r2 = r0.zza();     // Catch: Throwable -> L8
        r0.close();
        return r2;
    L10:
        th = move-exception;
        this.zzb = r1;     // Catch: Throwable -> L8
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L15
    L17:
        throw th;
    L15:
        th = move-exception;
        th.addSuppressed(th);
        goto L17
    }

    private final zzpx zzj(ValueEncoder r2, FieldDescriptor r3, Object r4, boolean r5) {
        zzqb r0 = this.zzf;
        r0.zza(r3, r5);
        r2.encode(r4, r0);
        return this;
    }

    private static ByteBuffer zzk(int r1) {
        return ByteBuffer.allocate(r1).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static int zzl(FieldDescriptor r1) {
        zzpv r12 = (zzpv) r1.getProperty(zzpv.class);
        if (r12 == null) goto L7;
        return r12.zza();
    L7:
        throw new EncodingException("Field has no @Protobuf config");
    }

    private static zzpv zzm(FieldDescriptor r1) {
        zzpv r12 = (zzpv) r1.getProperty(zzpv.class);
        if (r12 == null) goto L6;
        return r12;
    L6:
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final void zzn(int r5) {
    L2:
        long r0 = r5 & (-128);
        int r1 = r5 & 127;
        OutputStream r2 = this.zzb;
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
        OutputStream r2 = this.zzb;
        if (r0 == 0) goto L5;
        r2.write(r1 | 128);
        r6 = r6 >>> 7;
        goto L2
    L5:
        r2.write(r1);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final ObjectEncoderContext add(FieldDescriptor r2, double r3) {
        zzb(r2, r3, true);
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
        zza(r2, r3, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final /* synthetic */ ObjectEncoderContext add(FieldDescriptor r2, boolean r3) {
        zzd(r2, r3 ? 1 : 0, true);
        return this;
    }

    public final ObjectEncoderContext zza(FieldDescriptor r3, Object r4, boolean r5) {
        if (r4 != null) goto L5;
    L47:
        return this;
    L5:
        if ((r4 instanceof CharSequence) == false) goto L13;
        CharSequence r42 = (CharSequence) r4;
        if (r5 == true) goto L9;
    L10:
        zzn((zzl(r3) << 3) | 2);
        byte[] r32 = r42.toString().getBytes(zza);
        zzn(r32.length);
        this.zzb.write(r32);
        return this;
    L9:
        if (r42.length() == 0) goto L47;
    L13:
        if ((r4 instanceof Collection) == false) goto L19;
        Iterator r43 = ((Collection) r4).iterator();
    L16:
        if (r43.hasNext() == false) goto L47;
        zza(r3, r43.next(), false);
        goto L16
    L19:
        if ((r4 instanceof Map) == false) goto L25;
        Iterator r44 = ((Map) r4).entrySet().iterator();
    L22:
        if (r44.hasNext() == false) goto L47;
        Map.Entry r52 = (Map.Entry) r44.next();
        zzh(zzi, r3, r52, false);
        goto L22
    L25:
        if ((r4 instanceof Double) == false) goto L29;
        zzb(r3, ((Double) r4).doubleValue(), r5);
        return this;
    L29:
        if ((r4 instanceof Float) == false) goto L33;
        zzc(r3, ((Float) r4).floatValue(), r5);
        return this;
    L33:
        if ((r4 instanceof Number) == false) goto L37;
        zze(r3, ((Number) r4).longValue(), r5);
        return this;
    L37:
        if ((r4 instanceof Boolean) == false) goto L41;
        zzd(r3, ((Boolean) r4).booleanValue() ? 1 : 0, r5);
        return this;
    L41:
        if ((r4 instanceof byte[]) == false) goto L50;
        byte[] r45 = (byte[]) r4;
        if (r5 == true) goto L45;
    L48:
        zzn((zzl(r3) << 3) | 2);
        zzn(r45.length);
        this.zzb.write(r45);
        return this;
    L45:
        if (r45.length == 0) goto L47;
    L50:
        ObjectEncoder r0 = (ObjectEncoder) this.zzc.get(r4.getClass());
        if (r0 == null) goto L54;
        zzh(r0, r3, r4, r5);
        return this;
    L54:
        ValueEncoder r02 = (ValueEncoder) this.zzd.get(r4.getClass());
        if (r02 == null) goto L59;
        zzj(r02, r3, r4, r5);
        return this;
    L59:
        if ((r4 instanceof zzpt) == false) goto L63;
        zzd(r3, ((zzpt) r4).zza(), true);
        return this;
    L63:
        if ((r4 instanceof Enum) == false) goto L66;
        zzd(r3, ((Enum) r4).ordinal(), true);
        return this;
    L66:
        zzh(this.zze, r3, r4, r5);
        return this;
    }

    public final ObjectEncoderContext zzb(FieldDescriptor r3, double r4, boolean r6) {
        if (r6 == true) goto L4;
    L6:
        zzn((zzl(r3) << 3) | 1);
        this.zzb.write(zzk(8).putDouble(r4).array());
        return this;
    L4:
        if (r4 != 0.0d) goto L6;
        return this;
    }

    public final ObjectEncoderContext zzc(FieldDescriptor r1, float r2, boolean r3) {
        if (r3 == true) goto L4;
    L6:
        zzn((zzl(r1) << 3) | 5);
        this.zzb.write(zzk(4).putFloat(r2).array());
        return this;
    L4:
        if (r2 != 0.0f) goto L6;
        return this;
    }

    public final zzpx zzd(FieldDescriptor r2, int r3, boolean r4) {
        if (r4 == false) goto L4;
        if (r3 != 0) goto L4;
    L10:
        return this;
    L4:
        zzpv r22 = zzm(r2);
        int r42 = r22.zzb().ordinal();
        if (r42 != 0) goto L7;
        zzn(r22.zza() << 3);
        zzn(r3);
        return this;
    L7:
        if (r42 != 1) goto L9;
        zzn(r22.zza() << 3);
        zzn((r3 + r3) ^ (r3 >> 31));
        return this;
    L9:
        if (r42 != 2) goto L10;
        zzn((r22.zza() << 3) | 5);
        this.zzb.write(zzk(4).putInt(r3).array());
        return this;
    }

    public final zzpx zze(FieldDescriptor r3, long r4, boolean r6) {
        if (r6 == true) goto L4;
    L5:
        zzpv r32 = zzm(r3);
        int r62 = r32.zzb().ordinal();
        if (r62 != 0) goto L8;
        zzn(r32.zza() << 3);
        zzo(r4);
        return this;
    L8:
        if (r62 != 1) goto L10;
        zzn(r32.zza() << 3);
        long r33 = r4 >> 63;
        zzo(r33 ^ (r4 + r4));
        return this;
    L10:
        if (r62 == 2) goto L12;
    L11:
        return this;
    L12:
        zzn((r32.zza() << 3) | 1);
        this.zzb.write(zzk(8).putLong(r4).array());
        return this;
    L4:
        if (r4 == 0) goto L11;
        goto L5
    }

    public final zzpx zzf(Object r3) {
        if (r3 != null) goto L4;
        return this;
    L4:
        ObjectEncoder r0 = (ObjectEncoder) this.zzc.get(r3.getClass());
        if (r0 == null) goto L9;
        r0.encode(r3, this);
        return this;
    L9:
        throw new EncodingException("No encoder for ".concat(String.valueOf(r3.getClass())));
    }
}
