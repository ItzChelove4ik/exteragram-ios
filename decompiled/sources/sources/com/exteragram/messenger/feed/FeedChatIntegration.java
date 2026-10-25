package com.exteragram.messenger.feed;

import androidx.collection.LongSparseArray;
import com.exteragram.messenger.feed.FeedController;
import com.exteragram.messenger.feed.ads.FeedAdController;
import com.exteragram.messenger.feed.ads.FeedAdInjector;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public class FeedChatIntegration {
    private static final int NEAR_NEWEST_THRESHOLD = 0;
    private static final int PAGEDOWN_SCROLL_THRESHOLD = 0;
    private final FeedAdInjector adInjector;
    private Runnable channelsChangedCallback;
    private final int currentAccount;
    private boolean destroyed;
    private final Host host;
    private boolean initialScrollApplied;
    private int lastPagedownCount;
    private boolean pagedownShownByScroll;
    private boolean pendingDividerScroll;
    private long pendingHideDialogId;
    private ScrollAnchor pendingInitialScrollRestore;
    private final LongSparseArray<ArrayList<Integer>> pendingReactionIds;
    private int preserveScrollLoadIndex;
    private final Runnable reactionsRefreshRunnable;
    private boolean reactionsRefreshScheduled;
    private final int reactionsRequestGuid;
    private boolean readyToMarkAsRead;
    private final boolean restoreDrawerScrollPosition;
    private boolean scrollPreservedNewerToUnread;
    private final Runnable settleAtNewestRunnable;
    private boolean settleAtNewestScheduled;
    private int totalScrollDy;
    private MessageObject unreadDivider;
    private boolean viewportActive;

    public interface Host {
        boolean canScrollToNewer();

        ScrollAnchor captureScrollAnchor();

        void deleteRows(ArrayList<Integer> r1);

        void dematerializeRow(MessageObject r1);

        int getDistanceToNewerPx();

        BaseFragment getFragment();

        int getLastVisibleMessageIndex();

        ArrayList<MessageObject> getMessages();

        int getNewestVisibleMessageIndex();

        void invalidateVisiblePart();

        boolean isFirstLoadComplete();

        boolean isListReady();

        boolean isListScrollIdle();

        boolean isPagedownButtonVisible();

        boolean isScrollAnimationRunning();

        void materializeRow(MessageObject r1);

        int nextStableId();

        void notifyAllMessagesChanged();

        void notifyMessageInserted(int r1);

        void notifyMessageRemoved(int r1);

        void onFeedListChanged();

        void reloadFeed();

        void requestOlderFeedPage();

        void restoreScrollAnchor(ScrollAnchor r1);

        void scrollToMessage(int r1, int r2);

        void scrollToMessageAnimated(int r1, int r2);

        void setPagedownButtonVisible(boolean r1);

        void setPagedownCount(int r1);

        void showEmptyFeedProgress();

        void showEmptyFeedState();

        int stableIdForDateHeader(int r1);
    }

    public static final class ScrollAnchor {
        public final int offsetTop;
        public final MessageObject row;

        public ScrollAnchor(MessageObject r1, int r2) {
            this.row = r1;
            this.offsetTop = r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$CGOSNoWHRFPXFKpqw1r2pO3NZI8(FeedChatIntegration r0, long r1) {
        r0.lambda$hideChannelWithUndo$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$L4nlIQiHEc9gYYSmygLQsAcdXhM(FeedChatIntegration r0) {
        r0.flushReactionsRefresh();
    }

    public static /* synthetic */ void $r8$lambda$NecFS5xxy5E587cVeHEGhFwA_Bs(FeedChatIntegration r0) {
        r0.undoHideChannel();
    }

    public static /* synthetic */ void $r8$lambda$gHBSJTMboFh6vfFPA1QzTJV6ZZg(FeedChatIntegration r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$flushReactionsRefresh$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zSwajio1WFs2OLziJKFooAT_5zA(FeedChatIntegration r0) {
        r0.settleAtNewestNow();
    }

    static {
        PAGEDOWN_SCROLL_THRESHOLD = AndroidUtilities.dp(100.0f);
        NEAR_NEWEST_THRESHOLD = AndroidUtilities.dp(160.0f);
    }

    public FeedChatIntegration(int r2, Host r3, boolean r4) {
        this.preserveScrollLoadIndex = -1;
        this.lastPagedownCount = -1;
        this.settleAtNewestRunnable = new FeedChatIntegration$$ExternalSyntheticLambda0(this);
        this.reactionsRequestGuid = ConnectionsManager.generateClassGuid();
        this.pendingReactionIds = new LongSparseArray();
        this.reactionsRefreshRunnable = new FeedChatIntegration$$ExternalSyntheticLambda1(this);
        this.currentAccount = r2;
        this.host = r3;
        this.restoreDrawerScrollPosition = r4;
        this.adInjector = new FeedAdInjector(r2, r3);
    }

    private void applyUnreadDivider(boolean r8, boolean r9) {
        if (this.host.isListReady() == false) goto L51;
        ArrayList<MessageObject> r0 = this.host.getMessages();
        if (r0.isEmpty() == false) goto L9;
        this.unreadDivider = null;
        this.readyToMarkAsRead = false;
        return;
    L9:
        MessageObject r1 = this.unreadDivider;
        if (r1 != null) goto L12;
        int r12 = -1;
    L13:
        if (r12 < 0) goto L15;
        MessageObject r4 = this.unreadDivider;
    L16:
        if (r12 < 0) goto L18;
        r0.remove(r12);
    L18:
        this.unreadDivider = null;
        int r3 = FeedController.getInstance(this.currentAccount).findFirstUnreadIndex(r0);
        if (r3 >= 0) goto L28;
        this.pendingDividerScroll = false;
        if (r12 < 0) goto L26;
        if (r8 == false) goto L25;
        if (r9 == true) goto L25;
        this.host.notifyAllMessagesChanged();
    L25:
        this.host.notifyMessageRemoved(r12);
        this.host.invalidateVisiblePart();
    L26:
        this.readyToMarkAsRead = true;
        return;
    L28:
        int r32 = findDividerInsertIndex(r0, r3);
        if (r4 != null) goto L32;
        r4 = FeedMessageUtils.createUnreadDivider(this.currentAccount, this.host.nextStableId());
    L32:
        r0.add(r32, r4);
        this.unreadDivider = r4;
        if (r8 == false) goto L43;
        this.readyToMarkAsRead = false;
        if (r9 == false) goto L40;
        if (r12 >= 0) goto L38;
        this.host.notifyMessageInserted(r32);
    L41:
        this.pendingDividerScroll = true;
        requestPendingInitialPosition();
        this.host.invalidateVisiblePart();
        return;
    L38:
        if (r12 == r32) goto L41;
        this.host.notifyMessageRemoved(r12);
        this.host.notifyMessageInserted(r32);
        goto L41
    L40:
        this.host.notifyAllMessagesChanged();
        goto L41
    L43:
        this.readyToMarkAsRead = true;
        if (r12 >= 0) goto L47;
        this.host.notifyMessageInserted(r32);
        this.host.invalidateVisiblePart();
        return;
    L47:
        if (r12 == r32) goto L50;
        this.host.notifyMessageRemoved(r12);
        this.host.notifyMessageInserted(r32);
        this.host.invalidateVisiblePart();
        return;
    L50:
        return;
    L15:
        r4 = null;
        goto L16
    L12:
        r12 = r0.indexOf(r1);
        goto L13
    }

    private void cancelPendingReactionsRefresh() {
        AndroidUtilities.cancelRunOnUIThread(this.reactionsRefreshRunnable);
        this.reactionsRefreshScheduled = false;
        this.pendingReactionIds.clear();
    }

    private MessageObject createDateHeader(MessageObject r3) {
        return FeedMessageUtils.createDateHeader(this.currentAccount, r3, this.host.stableIdForDateHeader(r3.dateKeyInt));
    }

    private static int findDividerInsertIndex(ArrayList<MessageObject> r7, int r8) {
        MessageObject r0 = r7.get(r8);
        long r1 = r0.getGroupId();
        if (r1 == 0) goto L5;
        long r3 = r0.getDialogId();
    L7:
        r8 = r8 + 1;
        if (r8 >= r7.size()) goto L17;
        MessageObject r02 = r7.get(r8);
        if (r02 == null) goto L17;
        if (r02.getGroupId() != r1) goto L17;
        if (r02.getDialogId() == r3) goto L7;
    L17:
        return r8;
    L5:
        return r8 + 1;
    }

    private void flushReactionsRefresh() {
        int r0 = 0;
        this.reactionsRefreshScheduled = false;
        if (this.destroyed == false) goto L5;
    L12:
        this.pendingReactionIds.clear();
        return;
    L5:
        if (this.viewportActive == false) goto L12;
    L8:
        if (r0 >= this.pendingReactionIds.size()) goto L10;
        TLRPC.TL_messages_getMessagesReactions r1 = new TLRPC.TL_messages_getMessagesReactions();
        r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.pendingReactionIds.keyAt(r0));
        r1.id.addAll(this.pendingReactionIds.valueAt(r0));
        int r12 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new FeedChatIntegration$$ExternalSyntheticLambda3(this));
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r12, this.reactionsRequestGuid);
        r0 = r0 + 1;
        goto L8
    L10:
        this.pendingReactionIds.clear();
    }

    private static int getInsertIndex(ArrayList<MessageObject> r1, MessageObject r2, ArrayList<MessageObject> r3, int r4, int r5) {
        int r52 = Math.min(r5, r1.size());
        if (r4 > 0) goto L5;
        return r52;
    L5:
        if (r52 >= r1.size()) goto L18;
        MessageObject r32 = r3.get(r4 - 1);
        MessageObject r12 = r1.get(r52);
        if (r32 == null) goto L19;
        if (r12 != null) goto L10;
        return r52;
    L10:
        if (r12.isDateObject == false) goto L21;
        int r33 = r32.dateKeyInt;
        if (r33 != r2.dateKeyInt) goto L14;
        return r52;
    L14:
        if (r12.dateKeyInt == r33) goto L16;
        return r52;
    L16:
        return r52 + 1;
    L21:
        return r52;
    L19:
        return r52;
    L18:
        return r52;
    }

    private boolean hasMaterializedPostRows() {
        ArrayList<MessageObject> r3 = this.host.getMessages();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L10;
        if (FeedMessageUtils.isPostRow(r3.get(r1)) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private boolean hasPendingInitialPosition() {
        if (this.pendingInitialScrollRestore == null) goto L5;
        return true;
    L5:
        if (this.pendingDividerScroll == true) goto L11;
        return false;
    L11:
        return true;
    }

    private /* synthetic */ void lambda$flushReactionsRefresh$0(TLObject r4, TLRPC.TL_error r5) {
        if ((r4 instanceof TLRPC.Updates) == false) goto L16;
        TLRPC.Updates r42 = (TLRPC.Updates) r4;
        int r0 = 0;
    L6:
        if (r0 >= r42.updates.size()) goto L11;
        TLRPC.Update r1 = r42.updates.get(r0);
        if ((r1 instanceof TL_update.TL_updateMessageReactions) == false) goto L10;
        ((TL_update.TL_updateMessageReactions) r1).updateUnreadState = false;
    L10:
        r0 = r0 + 1;
        goto L6
    L11:
        MessagesController.getInstance(this.currentAccount).processUpdates(r42, false);
        return;
    }

    private /* synthetic */ void lambda$hideChannelWithUndo$1(long r3) {
        if (this.pendingHideDialogId != r3) goto L6;
        this.pendingHideDialogId = 0;
        return;
    }

    private void maybeScrollToDivider() {
        if (this.pendingDividerScroll == true) goto L6;
        return;
    L6:
        if (this.host.isListReady() == true) goto L8;
    L19:
        this.pendingDividerScroll = false;
        this.readyToMarkAsRead = true;
        return;
    L8:
        if (this.unreadDivider == null) goto L19;
        int r0 = this.host.getLastVisibleMessageIndex();
        if (r0 != Integer.MIN_VALUE) goto L13;
        return;
    L13:
        int r3 = this.host.getMessages().indexOf(this.unreadDivider);
        if (r3 < 0) goto L17;
        if (r3 <= r0) goto L17;
        this.host.scrollToMessage(r3, AndroidUtilities.dp(48.0f));
    L17:
        this.pendingDividerScroll = false;
        this.readyToMarkAsRead = true;
    }

    public static void mergeDeletedIds(ArrayList<Integer> r2, ArrayList<Integer> r3) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L9;
        if (r2.contains(r3.get(r0)) == true) goto L8;
        r2.add(r3.get(r0));
    L8:
        r0 = r0 + 1;
        goto L4
    }

    private boolean normalizeDateHeaders(ArrayList<MessageObject> r11) {
        int r2 = 0;
        boolean r3 = false;
        boolean r5 = false;
    L3:
        MessageObject r4 = null;
    L5:
        if (r2 >= r11.size()) goto L29;
        MessageObject r6 = r11.get(r2);
        if (r6 == null) goto L28;
        if (r6.type == 6) goto L28;
        if (r6.isSponsored() == true) goto L28;
        if (r6.isDateObject == true) goto L15;
        if (r4 == null) goto L27;
        if (r4.dateKeyInt == r6.dateKeyInt) goto L27;
        r11.add(r2, createDateHeader(r4));
        this.host.notifyMessageInserted(r2);
    L21:
        r2 = r2 + 1;
        r3 = false;
        r4 = null;
    L17:
        r5 = true;
    L27:
        r2 = r2 + 1;
        r4 = r6;
        r3 = true;
        goto L5
    L15:
        if (r3 == true) goto L19;
        r11.remove(r2);
        this.host.notifyMessageRemoved(r2);
        goto L17
    L19:
        if (r4.dateKeyInt == r6.dateKeyInt) goto L22;
        r11.add(r2, createDateHeader(r4));
        this.host.notifyMessageInserted(r2);
        goto L21
    L22:
        r2 = r2 + 1;
        r3 = false;
    L28:
        r2 = r2 + 1;
        goto L5
    L29:
        if (r3 == false) goto L32;
        r11.add(createDateHeader(r4));
        this.host.notifyMessageInserted(r11.size() - 1);
        return true;
    L32:
        return r5;
    }

    private void requestPendingInitialPosition() {
        if (this.host.getFragment().isPaused() == false) goto L5;
        return;
    L5:
        if (this.host.isListReady() == false) goto L24;
        ScrollAnchor r0 = this.pendingInitialScrollRestore;
        if (r0 == null) goto L12;
        this.host.restoreScrollAnchor(r0);
        return;
    L12:
        if (this.pendingDividerScroll == true) goto L15;
        return;
    L15:
        if (this.unreadDivider != null) goto L17;
        int r02 = -1;
    L18:
        if (r02 >= 0) goto L21;
        this.pendingDividerScroll = false;
        this.readyToMarkAsRead = true;
        return;
    L21:
        this.host.scrollToMessage(r02, AndroidUtilities.dp(48.0f));
        return;
    L17:
        r02 = this.host.getMessages().indexOf(this.unreadDivider);
        goto L18
    }

    private void requestReactionsRefresh(MessageObject r10) {
        if (this.destroyed == false) goto L5;
        return;
    L5:
        if (this.viewportActive == true) goto L7;
        return;
    L7:
        if (r10.messageOwner == null) goto L33;
        int r0 = r10.getRealId();
        long r1 = r10.getDialogId();
        if (r0 > 0) goto L12;
        return;
    L12:
        if (r1 != 0) goto L15;
        return;
    L15:
        if (r10.messageOwner.action != null) goto L17;
    L19:
        long r3 = System.currentTimeMillis();
        if ((r3 - r10.reactionsLastCheckTime) <= 15000) goto L36;
        r10.reactionsLastCheckTime = r3;
        ArrayList<Integer> r102 = this.pendingReactionIds.get(r1);
        if (r102 != null) goto L25;
        r102 = new ArrayList();
        this.pendingReactionIds.put(r1, r102);
    L25:
        r102.add(Integer.valueOf(r0));
        if (this.reactionsRefreshScheduled == true) goto L32;
        this.reactionsRefreshScheduled = true;
        AndroidUtilities.runOnUIThread(this.reactionsRefreshRunnable);
        return;
    L32:
        return;
    L36:
        return;
    L17:
        if (r10.canSetReaction() == true) goto L19;
        return;
    }

    private void resetMetadataRefresh() {
        cancelPendingReactionsRefresh();
        ConnectionsManager.getInstance(this.currentAccount).cancelRequestsForGuid(this.reactionsRequestGuid);
    }

    private void settleAtNewestNow() {
        this.settleAtNewestScheduled = false;
        if (this.destroyed == false) goto L5;
        return;
    L5:
        if (this.viewportActive == true) goto L7;
        return;
    L7:
        if (this.host.isListReady() == true) goto L9;
        return;
    L9:
        if (this.host.isScrollAnimationRunning() == false) goto L11;
        return;
    L11:
        if (this.host.canScrollToNewer() == true) goto L18;
        settleUnreadDivider();
        return;
    }

    private void undoHideChannel() {
        long r0 = this.pendingHideDialogId;
        if (r0 != 0) goto L5;
        return;
    L5:
        this.pendingHideDialogId = 0;
        FeedController r2 = FeedController.getInstance(this.currentAccount);
        FeedConfig.getInstance(this.currentAccount).setExcluded(r0, false);
        r2.markConfigApplied();
        r2.getStore().setHidden(r0, false);
        reconcileWithStore();
        onFeedExclusionsChanged();
        notifyChannelsChanged();
    }

    private void updatePagedownCounter() {
        if (this.host.isListReady() == true) goto L6;
        return;
    L6:
        if (this.host.isScrollAnimationRunning() == true) goto L24;
        int r0 = this.host.getNewestVisibleMessageIndex();
        if (r0 != Integer.MIN_VALUE) goto L11;
        int r02 = 0;
    L13:
        if (r02 == this.lastPagedownCount) goto L15;
        this.lastPagedownCount = r02;
        this.host.setPagedownCount(r02);
    L15:
        if (r02 <= 0) goto L19;
        this.pagedownShownByScroll = false;
        this.host.setPagedownButtonVisible(true);
        return;
    L19:
        if (this.host.canScrollToNewer() == true) goto L22;
        this.pagedownShownByScroll = false;
        this.host.setPagedownButtonVisible(false);
        return;
    L22:
        return;
    L11:
        r02 = FeedController.getInstance(this.currentAccount).countUnreadBelow(this.host.getMessages(), r0);
        goto L13
    }

    public boolean afterPreservedNewerMessagesInserted() {
        boolean r0 = this.scrollPreservedNewerToUnread;
        applyUnreadDivider(r0, true);
        this.scrollPreservedNewerToUnread = false;
        return r0;
    }

    public void applyUnreadDivider(boolean r2) {
        applyUnreadDivider(r2, false);
    }

    public void beforePreservedNewerMessagesInserted() {
        if (this.host.isListScrollIdle() == true) goto L5;
    L9:
        boolean r0 = false;
    L10:
        this.scrollPreservedNewerToUnread = r0;
        return;
    L5:
        if (this.host.isScrollAnimationRunning() == true) goto L9;
        if (this.host.getDistanceToNewerPx() > NEAR_NEWEST_THRESHOLD) goto L9;
        r0 = true;
        goto L10
    }

    public boolean canMarkVisibleAsRead() {
        if (this.viewportActive == true) goto L5;
        return false;
    L5:
        if (this.host.getFragment().isPaused() == false) goto L7;
        return false;
    L7:
        if (this.initialScrollApplied == true) goto L9;
        return false;
    L9:
        if (this.readyToMarkAsRead == true) goto L11;
        return false;
    L11:
        if (this.pendingDividerScroll == false) goto L13;
        return false;
    L13:
        if (this.pendingInitialScrollRestore == null) goto L15;
        return false;
    L15:
        if (BaseFragment.hasSheets(this.host.getFragment()) == true) goto L25;
        return true;
    L25:
        return false;
    }

    public ArrayList<Integer> collectLocalRowIds(long r7, ArrayList<Integer> r9, int r10) {
        ArrayList<Integer> r0 = new ArrayList();
        if (r9 == null) goto L5;
        HashSet r1 = new HashSet(r9);
    L6:
        ArrayList<MessageObject> r6 = this.host.getMessages();
        int r2 = 0;
    L8:
        if (r2 >= r6.size()) goto L24;
        MessageObject r3 = r6.get(r2);
        if (FeedMessageUtils.isPostRow(r3) == false) goto L23;
        if (r3.getDialogId() != r7) goto L23;
        int r4 = r3.getRealId();
        if (r1 == null) goto L17;
        boolean r42 = r1.contains(Integer.valueOf(r4));
    L21:
        if (r42 == false) goto L23;
        r0.add(Integer.valueOf(r3.getId()));
        goto L23
    L17:
        if (r4 <= 0) goto L20;
        if (r4 > r10) goto L20;
        r42 = true;
    L20:
        r42 = false;
    L23:
        r2 = r2 + 1;
        goto L8
    L24:
        return r0;
    L5:
        r1 = null;
        goto L6
    }

    public boolean consumePreserveScrollLoad(int r2) {
        if (this.preserveScrollLoadIndex == r2) goto L6;
        return false;
    L6:
        this.preserveScrollLoadIndex = -1;
        return true;
    }

    public void destroy() {
        this.destroyed = true;
        resetMetadataRefresh();
        if (this.settleAtNewestScheduled == false) goto L5;
        AndroidUtilities.cancelRunOnUIThread(this.settleAtNewestRunnable);
        this.settleAtNewestScheduled = false;
    L5:
        this.pendingInitialScrollRestore = null;
    }

    public void hideChannelWithUndo(long r4, CharSequence r6) {
        FeedConfig r0 = FeedConfig.getInstance(this.currentAccount);
        FeedController r1 = FeedController.getInstance(this.currentAccount);
        r0.setExcluded(r4, true);
        r1.markConfigApplied();
        r1.getStore().setHidden(r4, true);
        this.pendingHideDialogId = r4;
        reconcileWithStore();
        onFeedExclusionsChanged();
        notifyChannelsChanged();
        BulletinFactory.of(this.host.getFragment()).createUndoBulletin(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.FeedChannelHidden, new Object[]{r6})), new FeedChatIntegration$$ExternalSyntheticLambda4(this), new FeedChatIntegration$$ExternalSyntheticLambda5(this, r4)).show();
    }

    public void loadReplyMessages(ArrayList<MessageObject> r13, int r14, int r15) {
        if (r13 != null) goto L4;
        return;
    L4:
        if (r13.isEmpty() == true) goto L32;
        LongSparseArray r0 = new LongSparseArray();
        int r1 = 0;
        int r2 = 0;
    L8:
        if (r2 >= r13.size()) goto L23;
        MessageObject r3 = r13.get(r2);
        if (r3 == null) goto L21;
        if (r3.isDateObject == true) goto L21;
        long r4 = r3.getDialogId();
        if (r4 == 0) goto L21;
        ArrayList r6 = (ArrayList) r0.get(r4);
        if (r6 != null) goto L20;
        r6 = new ArrayList();
        r0.put(r4, r6);
    L20:
        r6.add(r3);
    L21:
        r2 = r2 + 1;
    L23:
        if (r1 >= r0.size()) goto L33;
        MediaDataController.getInstance(this.currentAccount).loadReplyMessagesForMessages((ArrayList) r0.valueAt(r1), r0.keyAt(r1), r14, 0, null, r15, null);
        r1 = r1 + 1;
        goto L23
    L33:
        return;
    }

    public void markAllRead() {
        FeedController.getInstance(this.currentAccount).markAllRead();
        applyUnreadDivider(false);
        refreshAds();
        this.host.invalidateVisiblePart();
    }

    public void notifyChannelsChanged() {
        Runnable r0 = this.channelsChangedCallback;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    public void onFeedExclusionsChanged() {
        this.lastPagedownCount = -1;
        updatePagedownCounter();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_READ_DIALOG_MESSAGE)});
    }

    public void onHostResumed() {
        if (this.host.getMessages().isEmpty() == true) goto L5;
        onMessagesLoaded();
    L5:
        requestPendingInitialPosition();
    }

    public void onMessagesDeleted() {
        if (this.unreadDivider == null) goto L21;
        ArrayList<MessageObject> r0 = this.host.getMessages();
        int r1 = r0.indexOf(this.unreadDivider);
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        if (FeedMessageUtils.isPostRow(r0.get(r2)) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return;
    L11:
        this.unreadDivider = null;
        if (r1 >= 0) goto L14;
        return;
    L14:
        if (this.host.isListReady() == false) goto L20;
        r0.remove(r1);
        this.host.notifyMessageRemoved(r1);
        return;
    L20:
        return;
    }

    public void onMessagesLoaded() {
        if (this.host.getFragment().isPaused() == false) goto L6;
        return;
    L6:
        if (this.host.isListReady() == true) goto L8;
        return;
    L8:
        if (hasMaterializedPostRows() == true) goto L11;
        return;
    L11:
        if (this.initialScrollApplied == true) goto L29;
        boolean r0 = true;
        this.initialScrollApplied = true;
        FeedController r1 = FeedController.getInstance(this.currentAccount);
        boolean r2 = r1.consumeInitialUnreadScroll();
        MessageObject r4 = null;
        if (this.restoreDrawerScrollPosition == false) goto L15;
        FeedController.SavedScrollPosition r3 = r1.getDrawerScrollPosition();
    L16:
        if (r3 == null) goto L19;
        r4 = r1.getMessage(r3.dialogId, r3.messageId);
    L19:
        if (r4 != null) goto L21;
    L23:
        if (r2 == false) goto L25;
    L27:
        r0 = r2;
    L28:
        applyUnreadDivider(r0);
        goto L29
    L25:
        if (this.host.getDistanceToNewerPx() > NEAR_NEWEST_THRESHOLD) goto L27;
    L21:
        if (this.host.getMessages().contains(r4) == false) goto L23;
        applyUnreadDivider(false);
        refreshAds();
        this.pendingInitialScrollRestore = new ScrollAnchor(r4, r3.offsetTop);
        requestPendingInitialPosition();
        goto L29
    L15:
        r3 = null;
    L29:
        FeedAdController.getInstance(this.currentAccount).ensureLoaded(new FeedChatIntegration$$ExternalSyntheticLambda2(this));
    }

    public void onPostCellVisible(MessageObject r2, boolean r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isSponsored() == true) goto L15;
        requestReactionsRefresh(r2);
        if (canMarkVisibleAsRead() == false) goto L14;
        if (r3 == true) goto L11;
        if (r4 == true) goto L11;
        return;
    L11:
        FeedController.getInstance(this.currentAccount).onPostSeen(r2.getDialogId(), r2.getRealId());
        return;
    L14:
        return;
    }

    public void onPreserveScrollLoadStarted(int r1) {
        this.preserveScrollLoadIndex = r1;
    }

    public void onReadStateRefreshed() {
        ScrollAnchor r0 = this.host.captureScrollAnchor();
        boolean r1 = hasPendingInitialPosition();
        applyUnreadDivider(false);
        refreshAds();
        if (r1 == true) goto L5;
    L6:
        this.host.restoreScrollAnchor(r0);
    L7:
        this.lastPagedownCount = -1;
        updatePagedownCounter();
        this.host.invalidateVisiblePart();
        return;
    L5:
        if (hasPendingInitialPosition() == true) goto L7;
        goto L6
    }

    public void onScrollAnimationFinished() {
        if (this.destroyed == false) goto L5;
        return;
    L5:
        if (this.viewportActive == true) goto L7;
        return;
    L7:
        if (this.settleAtNewestScheduled == true) goto L12;
        this.settleAtNewestScheduled = true;
        AndroidUtilities.runOnUIThread(this.settleAtNewestRunnable);
        return;
    }

    public void onScrolled(int r4) {
        if (this.viewportActive == true) goto L5;
        return;
    L5:
        if (this.host.isListReady() == true) goto L7;
        return;
    L7:
        if (this.host.isScrollAnimationRunning() == false) goto L10;
        return;
    L10:
        if (this.host.canScrollToNewer() == true) goto L18;
        this.totalScrollDy = 0;
        this.pagedownShownByScroll = false;
        this.host.setPagedownButtonVisible(false);
        if (r4 > 0) goto L14;
        return;
    L14:
        if (this.settleAtNewestScheduled == true) goto L38;
        this.settleAtNewestScheduled = true;
        AndroidUtilities.runOnUIThread(this.settleAtNewestRunnable);
        return;
    L38:
        return;
    L18:
        if (this.lastPagedownCount > 0) goto L46;
        boolean r0 = this.host.isPagedownButtonVisible();
        if (r4 <= 0) goto L27;
        if (r0 == true) goto L39;
        int r02 = this.totalScrollDy + r4;
        this.totalScrollDy = r02;
        if (r02 <= PAGEDOWN_SCROLL_THRESHOLD) goto L40;
        this.totalScrollDy = 0;
        this.pagedownShownByScroll = true;
        this.host.setPagedownButtonVisible(true);
        return;
    L40:
        return;
    L39:
        return;
    L27:
        if (r4 < 0) goto L29;
        return;
    L29:
        if (this.pagedownShownByScroll == false) goto L42;
        if (r0 == false) goto L43;
        int r03 = this.totalScrollDy + r4;
        this.totalScrollDy = r03;
        if (r03 >= (-PAGEDOWN_SCROLL_THRESHOLD)) goto L44;
        this.totalScrollDy = 0;
        this.host.setPagedownButtonVisible(false);
        return;
    L44:
        return;
    L43:
        return;
    L42:
        return;
    }

    public void onVisiblePartInvalidated() {
        if (this.viewportActive == true) goto L5;
        return;
    L5:
        if (this.host.getFragment().isPaused() == true) goto L13;
        ScrollAnchor r0 = this.pendingInitialScrollRestore;
        if (r0 == null) goto L10;
        this.host.restoreScrollAnchor(r0);
        this.pendingInitialScrollRestore = null;
    L10:
        maybeScrollToDivider();
        updatePagedownCounter();
        return;
    }

    public void reconcileWithStore() {
        if (this.host.isListReady() == false) goto L134;
        FeedStore r0 = FeedController.getInstance(this.currentAccount).getStore();
        ArrayList<MessageObject> r1 = this.host.getMessages();
        int r3 = 0;
        int r4 = 0;
    L7:
        if (r3 >= r1.size()) goto L12;
        if (FeedMessageUtils.isPostRow(r1.get(r3)) == false) goto L11;
        r4 = r4 + 1;
    L11:
        r3 = r3 + 1;
        goto L7
    L12:
        ArrayList<MessageObject> r32 = r0.getVisibleMessages();
        if (r4 != 0) goto L27;
        if (r32.isEmpty() == true) goto L21;
        if (this.host.isFirstLoadComplete() == false) goto L21;
        this.host.reloadFeed();
        return;
    L21:
        if (r0.isEmpty() == false) goto L23;
        return;
    L23:
        if (r0.isEndReached() == true) goto L132;
        this.host.requestOlderFeedPage();
        return;
    L132:
        return;
    L27:
        if (r0.isEmpty() == false) goto L30;
        this.host.reloadFeed();
        return;
    L30:
        HashSet r5 = new HashSet(r32);
        ArrayList<Integer> r6 = null;
        int r8 = 0;
        ArrayList r7 = null;
    L32:
        if (r8 >= r1.size()) goto L47;
        MessageObject r9 = r1.get(r8);
        if (FeedMessageUtils.isPostRow(r9) == false) goto L46;
        if (r5.contains(r9) == true) goto L46;
        if (r0.getMessage(r9.getDialogId(), r9.getId()) != r9) goto L43;
        if (r7 != null) goto L42;
        r7 = new ArrayList();
    L42:
        r7.add(r9);
        goto L46
    L43:
        if (r6 != null) goto L45;
        r6 = new ArrayList();
    L45:
        r6.add(Integer.valueOf(r9.getId()));
    L46:
        r8 = r8 + 1;
        goto L32
    L47:
        if (r6 != null) goto L49;
        int r52 = 0;
    L50:
        if (r7 != null) goto L52;
        int r82 = 0;
    L53:
        int r53 = r52 + r82;
        if (r53 == 0) goto L56;
    L58:
        ScrollAnchor r42 = this.host.captureScrollAnchor();
        boolean r83 = hasPendingInitialPosition();
        if (r6 == null) goto L61;
        this.host.deleteRows(r6);
    L61:
        if (r7 == null) goto L69;
        int r62 = 0;
    L64:
        if (r62 >= r7.size()) goto L69;
        int r92 = r1.indexOf(r7.get(r62));
        if (r92 < 0) goto L68;
        r1.remove(r92);
        this.host.dematerializeRow((MessageObject) r7.get(r62));
        this.host.notifyMessageRemoved(r92);
    L68:
        r62 = r62 + 1;
    L69:
        HashSet r63 = new HashSet();
        int r72 = 0;
    L71:
        if (r72 >= r1.size()) goto L76;
        if (FeedMessageUtils.isPostRow(r1.get(r72)) == false) goto L75;
        r63.add(r1.get(r72));
    L75:
        r72 = r72 + 1;
        goto L71
    L76:
        int r73 = 0;
        boolean r93 = false;
        int r10 = 0;
    L77:
        boolean r12 = true;
        if (r73 >= r32.size()) goto L91;
        MessageObject r11 = r32.get(r73);
        if (r63.contains(r11) == true) goto L82;
        this.host.materializeRow(r11);
        int r94 = getInsertIndex(r1, r11, r32, r73, r10);
        r1.add(r94, r11);
        this.host.notifyMessageInserted(r94);
        r10 = r94 + 1;
        r93 = true;
    L90:
        r73 = r73 + 1;
    L82:
        if (r10 >= r1.size()) goto L87;
        if (r1.get(r10) == r11) goto L87;
        r10 = r10 + 1;
    L87:
        if (r10 >= r1.size()) goto L90;
        r10 = r10 + 1;
        goto L90
    L91:
        if (r53 > 0) goto L96;
        if (r93 == true) goto L96;
        r12 = false;
    L96:
        if ((normalizeDateHeaders(r1) | r12) == false) goto L136;
        this.host.onFeedListChanged();
        applyUnreadDivider(false);
        refreshAds();
        onFeedExclusionsChanged();
        if (r83 == true) goto L101;
    L102:
        this.host.restoreScrollAnchor(r42);
    L103:
        this.host.invalidateVisiblePart();
        if (r0.getVisibleCount() != 0) goto L133;
        boolean r02 = r0.isEndReached();
        Host r13 = this.host;
        if (r02 == false) goto L109;
        r13.showEmptyFeedState();
        return;
    L109:
        r13.showEmptyFeedProgress();
        this.host.requestOlderFeedPage();
        return;
    L133:
        return;
    L101:
        if (hasPendingInitialPosition() == true) goto L103;
    L136:
        return;
    L56:
        if (r4 != r32.size()) goto L58;
        return;
    L52:
        r82 = r7.size();
        goto L53
    L49:
        r52 = r6.size();
        goto L50
    }

    public void refreshAds() {
        this.adInjector.refresh(this.unreadDivider);
        requestPendingInitialPosition();
    }

    public void resetUiState() {
        resetMetadataRefresh();
        this.initialScrollApplied = false;
        this.readyToMarkAsRead = false;
        this.pendingDividerScroll = false;
        this.pendingInitialScrollRestore = null;
        this.scrollPreservedNewerToUnread = false;
        this.preserveScrollLoadIndex = -1;
        this.unreadDivider = null;
        this.lastPagedownCount = -1;
        this.pagedownShownByScroll = false;
        this.totalScrollDy = 0;
        this.pendingHideDialogId = 0;
        if (this.settleAtNewestScheduled == false) goto L5;
        AndroidUtilities.cancelRunOnUIThread(this.settleAtNewestRunnable);
        this.settleAtNewestScheduled = false;
    L5:
        this.adInjector.clear();
    }

    public void saveDrawerScrollPosition() {
        ScrollAnchor r0 = this.host.captureScrollAnchor();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.row == null) goto L9;
        FeedController.getInstance(this.currentAccount).saveDrawerScrollPosition(r0.row.getDialogId(), r0.row.getRealId(), r0.offsetTop);
        return;
    }

    public boolean scrollToUnreadDividerIfAbove() {
        if (this.host.isListReady() == true) goto L5;
        return false;
    L5:
        ArrayList<MessageObject> r0 = this.host.getMessages();
        MessageObject r2 = this.unreadDivider;
        if (r2 != null) goto L8;
    L9:
        applyUnreadDivider(false);
        refreshAds();
        r0 = this.host.getMessages();
    L10:
        int r02 = r0.indexOf(this.unreadDivider);
        if (r02 >= 0) goto L13;
        return false;
    L13:
        int r22 = this.host.getNewestVisibleMessageIndex();
        if (r22 == Integer.MIN_VALUE) goto L19;
        if (r02 >= r22) goto L19;
        this.host.scrollToMessageAnimated(r02, AndroidUtilities.dp(48.0f));
        this.host.invalidateVisiblePart();
        return true;
    L19:
        return false;
    L8:
        if (r0.contains(r2) == true) goto L10;
        goto L9
    }

    public void setChannelsChangedCallback(Runnable r1) {
        this.channelsChangedCallback = r1;
    }

    public void setViewportActive(boolean r2) {
        if (this.viewportActive == r2) goto L17;
        this.viewportActive = r2;
        if (r2 == false) goto L8;
        onHostResumed();
        if (this.host.getMessages().isEmpty() == true) goto L16;
        onVisiblePartInvalidated();
        return;
    L16:
        return;
    L8:
        if (this.settleAtNewestScheduled == false) goto L10;
        AndroidUtilities.cancelRunOnUIThread(this.settleAtNewestRunnable);
        this.settleAtNewestScheduled = false;
    L10:
        cancelPendingReactionsRefresh();
        return;
    }

    public void settleUnreadDivider() {
        if (canMarkVisibleAsRead() == true) goto L5;
        return;
    L5:
        if (this.host.isListReady() == false) goto L38;
        int r0 = this.host.getLastVisibleMessageIndex();
        if (r0 == Integer.MIN_VALUE) goto L39;
        ArrayList<MessageObject> r1 = this.host.getMessages();
        if (this.host.canScrollToNewer() == false) goto L13;
        int r02 = Math.min(r0, r1.size() - 1);
    L15:
        if (r02 < 0) goto L29;
        FeedController r3 = FeedController.getInstance(this.currentAccount);
        int r4 = 0;
    L17:
        if (r4 > r02) goto L29;
        MessageObject r5 = r1.get(r4);
        if (r5 == null) goto L28;
        if (r5.isDateObject == true) goto L28;
        if (r5.type == 6) goto L28;
        if (r5.isSponsored() == true) goto L28;
        r3.onPostSeen(r5.getDialogId(), r5.getRealId());
    L28:
        r4 = r4 + 1;
    L29:
        applyUnreadDivider(false);
        refreshAds();
        updatePagedownCounter();
        return;
    L13:
        r02 = r1.size() - 1;
        goto L15
    L39:
        return;
    }
}
