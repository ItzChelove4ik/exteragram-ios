package org.telegram.ui.Stories;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public class ViewsForPeerStoriesRequester {
    private static long lastRequestTime;
    final int currentAccount;
    int currentReqId;
    final long dialogId;
    boolean isRunning;
    final Runnable scheduleRequestRunnable;
    final StoriesController storiesController;

    public static /* synthetic */ void $r8$lambda$3XURm3xmb9eZiTQ4XBPCAOBgiZQ(ViewsForPeerStoriesRequester r0, TLObject r1, TL_stories.TL_stories_getStoriesViews r2) {
        r0.lambda$requestInternal$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4o8Nf3D5a4YN_320_cxCQFhnLyU(ViewsForPeerStoriesRequester r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$e9eVLu_Y904viuIv-l9dVr9FZRo, reason: not valid java name */
    public static /* synthetic */ void m19836$r8$lambda$e9eVLu_Y904viuIvl9dVr9FZRo(ViewsForPeerStoriesRequester r0, TL_stories.TL_stories_getStoriesViews r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$requestInternal$2(r1, r2, r3);
    }

    public ViewsForPeerStoriesRequester(StoriesController r2, long r3, int r5) {
        this.scheduleRequestRunnable = new ViewsForPeerStoriesRequester$$ExternalSyntheticLambda1(this);
        this.currentAccount = r5;
        this.storiesController = r2;
        this.dialogId = r3;
    }

    private /* synthetic */ void lambda$new$0() {
        step();
    }

    private /* synthetic */ void lambda$requestInternal$1(TLObject r4, TL_stories.TL_stories_getStoriesViews r5) {
        lastRequestTime = System.currentTimeMillis();
        if (r4 == null) goto L9;
        TL_stories.TL_stories_storyViews r42 = (TL_stories.TL_stories_storyViews) r4;
        MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
        if (updateStories(r5.id, r42) == true) goto L8;
        this.currentReqId = 0;
        this.isRunning = false;
        return;
    L8:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
    L9:
        this.currentReqId = 0;
        if (this.isRunning == false) goto L13;
        AndroidUtilities.cancelRunOnUIThread(this.scheduleRequestRunnable);
        AndroidUtilities.runOnUIThread(this.scheduleRequestRunnable, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        return;
    }

    private /* synthetic */ void lambda$requestInternal$2(TL_stories.TL_stories_getStoriesViews r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ViewsForPeerStoriesRequester$$ExternalSyntheticLambda2(this, r2, r1));
    }

    private boolean requestInternal() {
        if (this.currentReqId == 0) goto L5;
        return false;
    L5:
        TL_stories.TL_stories_getStoriesViews r0 = new TL_stories.TL_stories_getStoriesViews();
        getStoryIds(r0.id);
        if (r0.id.isEmpty() == false) goto L8;
        return false;
    L8:
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        this.currentReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ViewsForPeerStoriesRequester$$ExternalSyntheticLambda0(this, r0));
        return true;
    }

    private void step() {
        if (this.isRunning == false) goto L14;
        long r2 = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS - (System.currentTimeMillis() - lastRequestTime);
        if (r2 <= 0) goto L10;
        AndroidUtilities.cancelRunOnUIThread(this.scheduleRequestRunnable);
        AndroidUtilities.runOnUIThread(this.scheduleRequestRunnable, r2);
        return;
    L10:
        if (requestInternal() == true) goto L13;
        this.currentReqId = 0;
        this.isRunning = false;
        return;
    L13:
        return;
    }

    public void getStoryIds(ArrayList<Integer> r4) {
        TL_stories.PeerStories r3 = this.storiesController.getStories(this.dialogId);
        if (r3 != null) goto L5;
        return;
    L5:
        if (r3.stories == null) goto L12;
        int r0 = 0;
    L8:
        if (r0 >= r3.stories.size()) goto L13;
        r4.add(Integer.valueOf(r3.stories.get(r0).id));
        r0 = r0 + 1;
        goto L8
    L13:
        return;
    }

    public void start(boolean r3) {
        if (this.isRunning != r3) goto L5;
        return;
    L5:
        if (r3 == false) goto L8;
        this.isRunning = true;
        step();
        return;
    L8:
        this.isRunning = false;
        AndroidUtilities.cancelRunOnUIThread(this.scheduleRequestRunnable);
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.currentReqId, false);
        this.currentReqId = 0;
    }

    public boolean updateStories(ArrayList<Integer> r7, TL_stories.TL_stories_storyViews r8) {
        if (r8 != null) goto L5;
    L25:
        return false;
    L5:
        if (r8.views == null) goto L25;
        TL_stories.PeerStories r1 = this.storiesController.getStories(this.dialogId);
        if (r1 == null) goto L25;
        if (r1.stories.isEmpty() == true) goto L25;
        int r2 = 0;
    L14:
        if (r2 >= r8.views.size()) goto L23;
        int r3 = 0;
    L17:
        if (r3 >= r1.stories.size()) goto L22;
        if (r1.stories.get(r3).id != r7.get(r2).intValue()) goto L21;
        r1.stories.get(r3).views = r8.views.get(r2);
    L21:
        r3 = r3 + 1;
        goto L17
    L22:
        r2 = r2 + 1;
        goto L14
    L23:
        this.storiesController.storiesStorage.updateStories(r1);
        return true;
    }
}
