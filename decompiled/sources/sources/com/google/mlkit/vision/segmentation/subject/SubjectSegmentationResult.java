package com.google.mlkit.vision.segmentation.subject;

import android.graphics.Bitmap;
import java.nio.FloatBuffer;
import java.util.List;

/* loaded from: classes5.dex */
public class SubjectSegmentationResult {
    private final List zza;
    private final FloatBuffer zzb;
    private final Bitmap zzc;

    public SubjectSegmentationResult(List r1, FloatBuffer r2, Bitmap r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public List<Subject> getSubjects() {
        return this.zza;
    }
}
