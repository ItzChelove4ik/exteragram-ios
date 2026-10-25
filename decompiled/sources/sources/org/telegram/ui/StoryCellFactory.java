package org.telegram.ui;

import android.content.Context;
import android.view.View;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class StoryCellFactory extends UItem.UItemFactory<SharedPhotoVideoCell2> {
    private SharedPhotoVideoCell2.SharedResources sharedResources;

    static {
        UItem.UItemFactory.setup(new StoryCellFactory());
    }

    public StoryCellFactory() {
    }

    public static UItem asStory(int r2, MessageObject r3, int r4, boolean r5) {
        UItem r0 = UItem.ofFactory(StoryCellFactory.class).setSpanCount(1);
        r0.intValue = r2;
        r0.object = r3;
        if (r3 == null) goto L7;
        TL_stories.StoryItem r22 = r3.storyItem;
        if (r22 == null) goto L7;
        long r23 = r22.id;
    L8:
        r0.longValue = r23;
        r0.collapsed = r5;
        r0.parentSpanCount = r4;
        return r0;
    L7:
        r23 = -1;
        goto L8
    }

    @Override // org.telegram.ui.Components.UItem.UItemFactory
    public void attachedView(RecyclerListView r1, View r2, UItem r3) {
        ((SharedPhotoVideoCell2) r2).setReordering(r3.reordering, false);
    }

    @Override // org.telegram.ui.Components.UItem.UItemFactory
    public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
        SharedPhotoVideoCell2 r12 = (SharedPhotoVideoCell2) r1;
        r12.setMessageObject((MessageObject) r2.object, r2.parentSpanCount);
        r12.setChecked(r2.checked, false);
        r12.setReordering(r2.reordering, false);
    }

    @Override // org.telegram.ui.Components.UItem.UItemFactory
    public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
        return createView(r1, r2, r3, r4, r5);
    }

    @Override // org.telegram.ui.Components.UItem.UItemFactory
    public SharedPhotoVideoCell2 createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
        if (this.sharedResources != null) goto L5;
        this.sharedResources = new SharedPhotoVideoCell2.SharedResources(r1, r5);
    L5:
        SharedPhotoVideoCell2 r22 = new SharedPhotoVideoCell2(r1, this.sharedResources, r3);
        r22.setCheck2();
        r22.isStory = true;
        return r22;
    }

    @Override // org.telegram.ui.Components.UItem.UItemFactory
    public boolean equals(UItem r5, UItem r6) {
        if (r5.accent == r6.accent) goto L6;
        return false;
    L6:
        if (r5.checked == r6.checked) goto L9;
        return false;
    L9:
        if (r5.longValue != r6.longValue) goto L12;
        return true;
    L12:
        return false;
    }
}
