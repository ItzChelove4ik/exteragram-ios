package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzud extends zzb implements zzue {
    public static zzue zza(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.mlkit.vision.segmentation.subject.aidls.ISubjectSegmenterCreator");
        if ((r0 instanceof zzue) == false) goto L10;
        return (zzue) r0;
    L10:
        return new zzuc(r2);
    }
}
