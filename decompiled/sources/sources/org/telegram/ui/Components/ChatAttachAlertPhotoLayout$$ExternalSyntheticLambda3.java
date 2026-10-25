package org.telegram.ui.Components;

import java.util.ArrayList;
import java.util.Comparator;
import org.telegram.messenger.MediaController;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda3 implements Comparator {
    public final /* synthetic */ ArrayList f$0;

    public /* synthetic */ ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda3(ArrayList r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ChatAttachAlertPhotoLayout.m9687$r8$lambda$GQg7L31KvldWhCi96qPcDzTIG8(this.f$0, (MediaController.AlbumEntry) r1, (MediaController.AlbumEntry) r2);
    }
}
