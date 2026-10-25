package org.telegram.ui.Components.Paint.Views;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentationResult;

/* loaded from: classes7.dex */
public final /* synthetic */ class PhotoView$$ExternalSyntheticLambda1 implements OnSuccessListener {
    public final /* synthetic */ PhotoView f$0;

    public /* synthetic */ PhotoView$$ExternalSyntheticLambda1(PhotoView r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r1) {
        PhotoView.m11575$r8$lambda$2__OhrsCFQ2SMrJbw_u0rzA50(this.f$0, (SubjectSegmentationResult) r1);
    }
}
