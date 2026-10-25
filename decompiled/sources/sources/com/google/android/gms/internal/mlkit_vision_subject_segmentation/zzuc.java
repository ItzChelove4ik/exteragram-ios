package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzuc extends zza implements zzue {
    public zzuc(IBinder r2) {
        super(r2, "com.google.mlkit.vision.segmentation.subject.aidls.ISubjectSegmenterCreator");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzue
    public final zzub zzd(IObjectWrapper r2, zzuj r3) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zza(r0, r3);
        Parcel r1 = zzb(1, r0);
        IBinder r22 = r1.readStrongBinder();
        if (r22 != null) goto L5;
        zzub r23 = null;
    L9:
        r1.recycle();
        return r23;
    L5:
        IInterface r32 = r22.queryLocalInterface("com.google.mlkit.vision.segmentation.subject.aidls.ISubjectSegmenter");
        if ((r32 instanceof zzub) == false) goto L8;
        r23 = (zzub) r32;
        goto L9
    L8:
        r23 = new zzub(r22);
        goto L9
    }
}
