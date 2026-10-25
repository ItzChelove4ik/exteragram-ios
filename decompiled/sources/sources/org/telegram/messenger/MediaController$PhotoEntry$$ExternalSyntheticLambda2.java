package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.telegram.messenger.MediaController;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaController$PhotoEntry$$ExternalSyntheticLambda2 implements StoryEntry.DecodeBitmap {
    public final /* synthetic */ MediaController.PhotoEntry f$0;

    public /* synthetic */ MediaController$PhotoEntry$$ExternalSyntheticLambda2(MediaController.PhotoEntry r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Stories.recorder.StoryEntry.DecodeBitmap
    public final Bitmap decode(BitmapFactory.Options r1) {
        return MediaController.PhotoEntry.$r8$lambda$Dg9GKh3ZBT33Nm4pT0eG3r8mFP8(this.f$0, r1);
    }
}
