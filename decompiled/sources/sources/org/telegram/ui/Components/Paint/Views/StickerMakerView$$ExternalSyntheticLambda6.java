package org.telegram.ui.Components.Paint.Views;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentationResult;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerMakerView$$ExternalSyntheticLambda6 implements OnSuccessListener {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ StickerMakerView$$ExternalSyntheticLambda6(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r1) {
        StickerMakerView.$r8$lambda$N34q7RjOP0nAX0Rvuf8jtIHGY0g(this.f$0, (SubjectSegmentationResult) r1);
    }
}
