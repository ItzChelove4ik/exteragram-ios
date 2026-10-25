package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzub extends zza implements IInterface {
    public zzub(IBinder r2) {
        super(r2, "com.google.mlkit.vision.segmentation.subject.aidls.ISubjectSegmenter");
    }

    public final zzuh zzd(IObjectWrapper r2, zztz r3) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zza(r0, r3);
        Parcel r1 = zzb(3, r0);
        Parcelable.Creator<zzuh> r22 = zzuh.CREATOR;
        if (r1.readInt() != 0) goto L5;
        zzuh r23 = null;
    L6:
        zzuh r24 = r23;
        r1.recycle();
        return r24;
    L5:
        r23 = r22.createFromParcel(r1);
        goto L6
    }

    public final void zze() {
        zzc(1, zza());
    }

    public final void zzf() {
        zzc(2, zza());
    }
}
