package org.telegram.ui.Stories;

import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$UploadingStory$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ StoriesController.UploadingStory f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;

    public /* synthetic */ StoriesController$UploadingStory$$ExternalSyntheticLambda4(StoriesController.UploadingStory r1, long r2, TL_stories.StoryItem r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoriesController.UploadingStory.$r8$lambda$DE8wlfevaumwzT_eKON2R7Kxmzs(this.f$0, this.f$1, this.f$2);
    }
}
