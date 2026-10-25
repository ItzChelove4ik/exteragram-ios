package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class MethodInvocation extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MethodInvocation> CREATOR = null;
    private final int zaa;
    private final int zab;
    private final int zac;
    private final long zad;
    private final long zae;
    private final String zaf;
    private final String zag;
    private final int zah;
    private final int zai;

    static {
        CREATOR = new zaq();
    }

    @Deprecated
    public MethodInvocation(int r13, int r14, int r15, long r16, long r18, String r20, String r21, int r22) {
        this(r13, r14, r15, r16, r18, r20, r21, r22, -1);
    }

    public MethodInvocation(int r1, int r2, int r3, long r4, long r6, String r8, String r9, int r10, int r11) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
        this.zad = r4;
        this.zae = r6;
        this.zaf = r8;
        this.zag = r9;
        this.zah = r10;
        this.zai = r11;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = this.zaa;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 1, r52);
        SafeParcelWriter.writeInt(r4, 2, this.zab);
        SafeParcelWriter.writeInt(r4, 3, this.zac);
        SafeParcelWriter.writeLong(r4, 4, this.zad);
        SafeParcelWriter.writeLong(r4, 5, this.zae);
        SafeParcelWriter.writeString(r4, 6, this.zaf, false);
        SafeParcelWriter.writeString(r4, 7, this.zag, false);
        SafeParcelWriter.writeInt(r4, 8, this.zah);
        SafeParcelWriter.writeInt(r4, 9, this.zai);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
