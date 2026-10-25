package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.io.IOException;
import java.util.Iterator;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class zzda extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzda> CREATOR = null;
    private final String zza;
    private final Bundle zzb;
    private transient zzmo zzc;

    static {
        zzco r0 = zzcp.zzu();
        r0.zzn("FunctionCall");
        r0.zza(6);
        r0.zzc("The function call schema");
        zzco r2 = zzcp.zzu();
        r2.zza(1);
        r2.zzc("The function name");
        zzcp r22 = r2.zzp();
        zzco r4 = zzcp.zzu();
        r4.zza(5);
        r4.zzc("An array of function arguments in an OBJECT format, e.g. [{'argName': 'argName1', 'argValue': 'argValue1'}, {'argName': 'argName2', 'argValue': 123.0}]");
        zzco r5 = zzcp.zzu();
        r5.zza(6);
        r5.zzc("The function argument root schema, which contains the argument name and its value.");
        zzco r1 = zzcp.zzu();
        r1.zza(1);
        r1.zzc("The argument name");
        r5.zzo(zzmo.zzc("argName", r1.zzp(), "argValue", zzcy.zza));
        r5.zzm(zzml.zzm("argName", "argValue"));
        r5.zzi(zzml.zzm("argName", "argValue"));
        r4.zzf(r5.zzp());
        r0.zzo(zzmo.zzc("name", r22, "args", r4.zzp()));
        r0.zzm(zzml.zzm("name", "args"));
        r0.zzi(zzml.zzm("name", "args"));
        r0.zzp();
        CREATOR = new zzdb();
    }

    public zzda(String r2, Bundle r3) {
        this.zzc = null;
        this.zza = r2;
        this.zzb = r3;
    }

    public final String toString() {
        zzmo r0 = zzb();
        if (r0 == null) goto L15;
        if (r0.isEmpty() == true) goto L15;
        String r4 = this.zza;
        zzku r1 = zzku.zza(", ");
        Iterator<E> r02 = r0.zze().iterator();
        StringBuilder r3 = new StringBuilder();
        zzkt.zza(r3, r02, r1, "=");     // Catch: IOException -> L11
        String r03 = r3.toString();
        StringBuilder r2 = new StringBuilder(((String.valueOf(r4).length() + 1) + r03.length()) + 1);
        r2.append(r4);
        r2.append("(");
        r2.append(r03);
        r2.append(")");
        return r2.toString();
    L11:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    L15:
        return String.valueOf(this.zza).concat("()");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        SafeParcelWriter.writeBundle(r4, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }

    public final String zza() {
        return this.zza;
    }

    public final zzmo zzb() {
        if (this.zzc != null) goto L19;
        Bundle r0 = this.zzb;
        if (r0 == null) goto L19;
        zzmn r1 = new zzmn();
        Iterator<String> r2 = r0.keySet().iterator();
    L8:
        if (r2.hasNext() == false) goto L17;
        String r3 = r2.next();
        Parcelable.Creator<zzcy> r4 = zzcy.CREATOR;
        byte[] r5 = r0.getByteArray(r3);
        r5.getClass();
        Parcel r6 = Parcel.obtain();
        r6.unmarshall(r5, 0, r5.length);     // Catch: Throwable -> L14
        r6.setDataPosition(0);     // Catch: Throwable -> L14
        zzcy r42 = r4.createFromParcel(r6);     // Catch: Throwable -> L14
        r6.recycle();
        zzcy r43 = r42;
        if (r43 == null) goto L8;
        r1.zza(r3, r43);
    L14:
        th = move-exception;
        r6.recycle();
        throw th;
    L17:
        this.zzc = r1.zzc();
    L19:
        return this.zzc;
    }
}
