package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.ContentPreviewViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class ContentPreviewViewer$1$2$$ExternalSyntheticLambda0 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ContentPreviewViewer.ContentPreviewViewerDelegate f$0;
    public final /* synthetic */ TLRPC.Document f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Object f$3;

    public /* synthetic */ ContentPreviewViewer$1$2$$ExternalSyntheticLambda0(ContentPreviewViewer.ContentPreviewViewerDelegate r1, TLRPC.Document r2, String r3, Object r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r8, int r9, int r10) {
        ContentPreviewViewer.AnonymousClass1.AnonymousClass2.$r8$lambda$WAv1zb1nofkSl4f4cXds_KrE1ZY(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9, r10);
    }
}
