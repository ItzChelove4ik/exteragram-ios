package com.exteragram.messenger.feed;

import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.collection.LongSparseArray;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.feed.FeedTimelineLoader;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class FeedController implements NotificationCenter.NotificationCenterDelegate {
    private static final FeedController[] Instance = null;
    private static final Object[] lockObjects = null;
    private int attemptRounds;
    private final FeedBackfillCoordinator backfill;
    private int cachedIncludedChannelCount;
    private final int closedRefreshGuid;
    private final Runnable closedRefreshRunnable;
    private boolean closedRefreshScheduled;
    private int configGeneration;
    public final int currentAccount;
    private SavedScrollPosition drawerScrollPosition;
    private boolean hasChannels;
    private boolean hasIncludedChannels;
    private int heldGuid;
    private int heldLoadIndex;
    private final ArrayList<int[]> initialLoadWaiters;
    private boolean initialUnreadScrollPending;
    private final FeedTimelineLoader loader;
    private boolean loading;
    private boolean loadingNewer;
    private boolean newerPagingBoundsDirty;
    private boolean olderPagingBoundsDirty;
    private int resumedUiClients;
    private int sessionGeneration;
    private int staleEnumerationRetries;
    private final FeedStore store;
    private int uiActiveClients;
    private final FeedUnreadTracker unreadTracker;

    public interface ChannelsCallback {
        void onChannels(ArrayList<TLRPC.Chat> r1, int r2, boolean r3, int r4);
    }

    /* loaded from: classes4.dex */
    public static final class SavedScrollPosition {
        public final long dialogId;
        public final int messageId;
        public final int offsetTop;

        private SavedScrollPosition(long r1, int r3, int r4) {
            this.dialogId = r1;
            this.messageId = r3;
            this.offsetTop = r4;
        }

        public /* synthetic */ SavedScrollPosition(long r1, int r3, int r4, FeedControllerIA r5) {
            this(r1, r3, r4);
        }
    }

    /* renamed from: $r8$lambda$-3lQc68sE2IgbZONPLyoZk0oyJI, reason: not valid java name */
    public static /* synthetic */ void m1409$r8$lambda$3lQc68sE2IgbZONPLyoZk0oyJI(FeedController r0, FeedConfig r1, int r2, int r3, int r4, int r5, int r6, FeedTimelineLoader.Cursor r7, HashSet r8, boolean r9) {
        r0.lambda$runAttempt$15(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$13ql2FBXRDNDE5aujYYvj0LoL_o(FeedController r0, HashSet r1, HashSet r2) {
        r0.onFeedChannelsChanged(r1, r2);
    }

    /* renamed from: $r8$lambda$1IAN-YOVM7jyy2I2NssWyw_PCuc, reason: not valid java name */
    public static /* synthetic */ void m1410$r8$lambda$1IANYOVM7jyy2I2NssWyw_PCuc(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, int r6, int r7) {
        r0.lambda$runAttempt$12(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$59zbgfNAS_7ys66RzqFGDpX7RnI(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, int r6, int r7, FeedTimelineLoader.NewerPage r8, ArrayList r9) {
        r0.lambda$runLoadNewer$18(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$5K-4ZuG4F8KNH9SqevS74JIGqXM, reason: not valid java name */
    public static /* synthetic */ void m1411$r8$lambda$5K4ZuG4F8KNH9SqevS74JIGqXM(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, int r6, int r7, ArrayList r8) {
        r0.lambda$loadInitial$2(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$6rP1S5i23O-UJ6MfdD-F5imZBmw, reason: not valid java name */
    public static /* synthetic */ void m1412$r8$lambda$6rP1S5i23OUJ6MfdDF5imZBmw(FeedController r0, Boolean r1) {
        r0.lambda$ensureCurrentConfig$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$80f6qIorWi7Ki2VD2Vgpql5UupY(FeedController r0, FeedConfig r1, int r2, int r3, int r4, int r5, int r6, FeedTimelineLoader.Cursor r7) {
        r0.lambda$runLoadNewer$19(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$A56NAwS2dt2RaeGhYNm56NBcRNM(FeedController r0, FeedConfig r1, int r2, int r3, int r4, Runnable r5) {
        r0.lambda$refreshReadState$11(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$Evg948fNpCDkvAkcf3Cs_5Ce8VQ(FeedController r0) {
        r0.runClosedRefresh();
    }

    public static /* synthetic */ void $r8$lambda$FV9vDaTZQ9nZj6tglC_H620m9mw(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, ChannelsCallback r6) {
        r0.lambda$loadChannels$20(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$Fa4Nv6LHiQzZT38gzQVJ56SF0qw(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, int r6, int r7) {
        r0.lambda$runLoadNewer$17(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$FbJDfm8z7XFnDlt2pC-D7dLfe9M, reason: not valid java name */
    public static /* synthetic */ void m1413$r8$lambda$FbJDfm8z7XFnDlt2pCD7dLfe9M(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, Utilities.Callback r6) {
        r0.lambda$reconcileChannelSet$7(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$JIGOcC2Y5T4W9OdUVYyIRKq5l_8(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, int r6, int r7) {
        r0.lambda$runLoadNewer$16(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$Lk7cd9ira87wiRIcJ22rKBH804E(FeedController r0, int r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$SXawjZ6giUNjfcoYKxnsaHEftNY(FeedController r0, FeedConfig r1, int r2, int r3, int r4, int r5, int r6, ArrayList r7) {
        r0.lambda$loadInitial$3(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$XtHUOrwV_3jeF0i3oWnMcUDZGIU(FeedController r0, Utilities.Callback r1, ArrayList r2, int r3, boolean r4, int r5) {
        r0.lambda$reconcileChannelSet$5(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$_m3f9_8TpegAocntUJ8LkcqrU9A(FeedController r0, Boolean r1) {
        r0.lambda$onFeedChannelsChanged$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$cGU6sSVMBgt7E7TVw9o5lmz42Cg(FeedController r0) {
        r0.onBackfillRoundFinished();
    }

    /* renamed from: $r8$lambda$irIamXoRepDMUvWs4UWqdr-qhHM, reason: not valid java name */
    public static /* synthetic */ void m1414$r8$lambda$irIamXoRepDMUvWs4UWqdrqhHM(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, int r6, int r7) {
        r0.lambda$runAttempt$13(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$k60__JEnmTULzjsDJkSwMFz6so4(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, FeedTimelineLoader.OlderPage r6, int r7, int r8, boolean r9, ArrayList r10) {
        r0.lambda$runAttempt$14(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: $r8$lambda$lncMaFgDrZna1XgzGblMz-t5XAY, reason: not valid java name */
    public static /* synthetic */ void m1415$r8$lambda$lncMaFgDrZna1XgzGblMzt5XAY(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, Utilities.Callback r6) {
        r0.lambda$reconcileChannelSet$6(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$nhEXTMi2wPg6Y_g5oHB6wB1SLrY(FeedController r0, FeedConfig r1, int r2, int r3, int r4, Utilities.Callback r5, HashSet r6, HashSet r7, FeedTimelineLoader.Cursor r8, FeedTimelineLoader.Cursor r9) {
        r0.lambda$reconcileChannelSet$9(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$oBBHS0UJWQ3NzqnXmlulRPD8U1c(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, Runnable r6) {
        r0.lambda$refreshReadState$10(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$w9CL4EALJ05HXL_tvxuRXMl07AU(FeedController r0, FeedConfig r1, int r2, boolean r3, int r4, int r5, ChannelsCallback r6) {
        r0.lambda$loadChannels$21(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$zrzKOoigMK7OCGcMLwEe5eQu-ls, reason: not valid java name */
    public static /* synthetic */ void m1416$r8$lambda$zrzKOoigMK7OCGcMLwEe5eQuls(FeedController r0, int r1, FeedTimelineLoader.ChannelEnumeration r2, FeedConfig r3, int r4, int r5, Utilities.Callback r6, FeedTimelineLoader.WindowPage r7, ArrayList r8, boolean r9) {
        r0.lambda$reconcileChannelSet$8(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    static {
        Instance = new FeedController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private FeedController(int r3) {
        FeedStore r0 = new FeedStore();
        this.store = r0;
        this.cachedIncludedChannelCount = -1;
        this.initialUnreadScrollPending = true;
        this.initialLoadWaiters = new ArrayList();
        this.closedRefreshGuid = ConnectionsManager.generateClassGuid();
        this.closedRefreshRunnable = new FeedController$$ExternalSyntheticLambda0(this);
        this.currentAccount = r3;
        this.unreadTracker = new FeedUnreadTracker(r3, r0.getMessages());
        this.loader = new FeedTimelineLoader(r3);
        this.backfill = new FeedBackfillCoordinator(r3, new FeedController$$ExternalSyntheticLambda1(this));
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda2(this, r3));
    }

    private static void addUpdated(ArrayList<MessageObject> r1, MessageObject r2) {
        if (r1.contains(r2) == true) goto L6;
        r1.add(r2);
        return;
    }

    private void applyEnumeration(FeedTimelineLoader.ChannelEnumeration r8) {
        if (r8.failed == true) goto L13;
        this.hasChannels = r8.hasChannels;
        this.hasIncludedChannels = !r8.included.isEmpty();
        this.cachedIncludedChannelCount = r8.included.size();
        ArrayList<FeedTimelineLoader.ChannelSnapshot> r82 = r8.included;
        int r0 = r82.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L18;
        FeedTimelineLoader.ChannelSnapshot r2 = r82.get(r1);
        r1 = r1 + 1;
        FeedTimelineLoader.ChannelSnapshot r22 = r2;
        int r3 = r22.readInboxMax;
        if (r3 > 0) goto L12;
        if (r22.unreadCount > 0) goto L12;
        r3 = r22.topMessage;
    L12:
        this.unreadTracker.applyReadInboxMax(r22.dialogId, r3);
        goto L6
    L18:
        return;
    }

    private boolean canRetryStaleEnumeration() {
        int r0 = this.staleEnumerationRetries;
        if (r0 < 3) goto L6;
        this.staleEnumerationRetries = 0;
        return false;
    L6:
        this.staleEnumerationRetries = r0 + 1;
        return true;
    }

    private ArrayList<MessageObject> createMessageObjects(ArrayList<TLRPC.Message> r21, ArrayList<TLRPC.User> r22, ArrayList<TLRPC.Chat> r23) {
        HashMap r4 = new HashMap();
        HashMap r5 = new HashMap();
        int r0 = r22.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        TLRPC.User r6 = r22.get(r2);
        r2 = r2 + 1;
        TLRPC.User r62 = r6;
        r4.put(Long.valueOf(r62.id), r62);
        goto L3
    L5:
        int r02 = r23.size();
        int r24 = 0;
    L6:
        if (r24 >= r02) goto L8;
        TLRPC.Chat r63 = r23.get(r24);
        r24 = r24 + 1;
        TLRPC.Chat r64 = r63;
        r5.put(Long.valueOf(r64.id), r64);
        goto L6
    L8:
        ArrayList<MessageObject> r03 = new ArrayList(r21.size());
        int r25 = r21.size();
    L9:
        if (r1 >= r25) goto L12;
        int r16 = r1 + 1;
        TLRPC.Message r65 = r21.get(r1);
        ArrayList<MessageObject> r12 = r03;
        r12.add(new MessageObject(this.currentAccount, r65, null, r4, r5, null, null, true, true, 0, false, false, false, 4));
        r03 = r12;
        r1 = r16;
        r25 = r25;
        goto L9
    L12:
        return r03;
    }

    private void ensureCurrentConfig() {
        if (this.configGeneration == FeedConfig.getInstance(this.currentAccount).getGeneration()) goto L6;
        applyConfigChange(new FeedController$$ExternalSyntheticLambda3(this));
        return;
    }

    private void flushInitialLoadWaiters() {
        flushInitialLoadWaiters(false);
    }

    private void flushInitialLoadWaiters(boolean r13) {
        if (this.initialLoadWaiters.isEmpty() == true) goto L8;
        ArrayList r0 = new ArrayList(this.initialLoadWaiters);
        this.initialLoadWaiters.clear();
        ArrayList<MessageObject> r5 = this.store.getVisibleMessages();
        int r1 = r0.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L10;
        int r10 = r2 + 1;
        int[] r11 = (int[]) r0.get(r2);
        FeedController r22 = this;
        r22.postFeedResults(r11[0], r11[1], r5, 0, false, r13);
        r22.postFeedCount(r11[0]);
        FeedController this = r22;
        r2 = r10;
        goto L6
    L10:
        return;
    }

    private static int getInactiveCacheCap() {
        int r0 = SharedConfig.getDevicePerformanceClass();
        if (r0 != 0) goto L5;
        return 300;
    L5:
        if (r0 == 2) goto L8;
        return MediaError.DetailedErrorCode.TEXT_UNKNOWN;
    L8:
        return 1000;
    }

    public static FeedController getInstance(int r3) {
        FeedController[] r0 = Instance;
        FeedController r1 = r0[r3];
        if (r1 != null) goto L15;
        Object r2 = lockObjects[r3];
        monitor-enter(r2);
        FeedController r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new FeedController(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(r2);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r1;
    }

    public static boolean isEligibleChannel(TLRPC.Chat r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (ChatObject.isChannelAndNotMegaGroup(r1) == true) goto L6;
        return false;
    L6:
        if (ChatObject.isCommunity(r1) == false) goto L8;
        return false;
    L8:
        if (ChatObject.isNotInChat(r1) == true) goto L15;
        return true;
    L15:
        return false;
    }

    private boolean isEnumerationCurrent(FeedTimelineLoader.ChannelEnumeration r3, FeedConfig r4, int r5, int r6) {
        if (this.loader.isEnumerationCurrent(r3) == true) goto L5;
    L11:
        boolean r32 = false;
    L12:
        if (r32 == false) goto L14;
        this.staleEnumerationRetries = 0;
    L14:
        return r32;
    L5:
        if (r3.configGeneration != r4.getGeneration()) goto L11;
        if (r3.configGeneration != r5) goto L11;
        if (r3.cacheEpoch != r6) goto L11;
        r32 = true;
        goto L12
    }

    private boolean isUiActive() {
        if (this.uiActiveClients <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$ensureCurrentConfig$4(Boolean r2) {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.feedNeedReload, new Object[]{r2});
    }

    private /* synthetic */ void lambda$loadChannels$20(int r3, FeedTimelineLoader.ChannelEnumeration r4, FeedConfig r5, int r6, int r7, ChannelsCallback r8) {
        if (r3 == this.sessionGeneration) goto L5;
    L13:
        if (r8 == null) goto L17;
        r8.onChannels(new ArrayList(), 0, true, r4.configGeneration);
        return;
    L17:
        return;
    L5:
        if (isEnumerationCurrent(r4, r5, r6, r7) == false) goto L13;
        applyEnumeration(r4);
        if (r4.failed == true) goto L10;
        MessagesController.getInstance(this.currentAccount).putChats(r4.channels, true);
    L10:
        if (r8 == null) goto L16;
        r8.onChannels(r4.channels, r4.included.size(), r4.failed, r4.configGeneration);
        return;
    }

    private /* synthetic */ void lambda$loadChannels$21(FeedConfig r10, int r11, boolean r12, int r13, int r14, ChannelsCallback r15) {
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda14(this, r11, this.loader.enumerateChannels(r10, r11, r12), r10, r13, r14, r15));
    }

    private /* synthetic */ void lambda$loadInitial$2(int r8, FeedTimelineLoader.ChannelEnumeration r9, FeedConfig r10, int r11, int r12, int r13, int r14, ArrayList r15) {
        if (r8 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r9, r10, r11, r12) == true) goto L9;
        postFeedResults(r13, r14, new ArrayList(), 0, false, true);
        postFeedCount(r13);
        return;
    L9:
        applyEnumeration(r9);
        postFeedResults(r13, r14, r15, 0, false, r9.failed);
        postFeedCount(r13);
    }

    private /* synthetic */ void lambda$loadInitial$3(FeedConfig r11, int r12, int r13, int r14, int r15, int r16, ArrayList r17) {
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda24(this, r12, this.loader.enumerateChannels(r11, r12, true), r11, r13, r14, r15, r16, r17));
    }

    private /* synthetic */ void lambda$new$0(int r3) {
        NotificationCenter.getInstance(r3).addObserver(this, NotificationCenter.messagesDidLoad);
        NotificationCenter.getInstance(r3).addObserver(this, NotificationCenter.loadingMessagesFailed);
        NotificationCenter.getInstance(r3).addObserver(this, NotificationCenter.messagesDeleted);
        NotificationCenter.getInstance(r3).addObserver(this, NotificationCenter.historyCleared);
        NotificationCenter.getInstance(r3).addObserver(this, NotificationCenter.didReceiveNewMessages);
        FeedChannelRegistry.getInstance(r3).addListener(new FeedController$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ void lambda$onFeedChannelsChanged$1(Boolean r2) {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.feedNeedReload, new Object[]{r2});
    }

    private /* synthetic */ void lambda$reconcileChannelSet$5(Utilities.Callback r1, ArrayList r2, int r3, boolean r4, int r5) {
        if (r4 == true) goto L4;
        this.configGeneration = r5;
    L4:
        if (r1 == null) goto L7;
        r1.run(Boolean.FALSE);
        return;
    }

    private /* synthetic */ void lambda$reconcileChannelSet$6(int r2, FeedTimelineLoader.ChannelEnumeration r3, FeedConfig r4, int r5, int r6, Utilities.Callback r7) {
        if (r2 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r3, r4, r5, r6) == false) goto L8;
    L11:
        if (r7 == null) goto L14;
        r7.run(Boolean.FALSE);
        return;
    L14:
        return;
    L8:
        if (canRetryStaleEnumeration() == false) goto L11;
        reconcileChannelSet(r7);
    }

    private /* synthetic */ void lambda$reconcileChannelSet$7(int r2, FeedTimelineLoader.ChannelEnumeration r3, FeedConfig r4, int r5, int r6, Utilities.Callback r7) {
        if (r2 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r3, r4, r5, r6) == false) goto L8;
    L11:
        if (r7 == null) goto L14;
        r7.run(Boolean.FALSE);
        return;
    L14:
        return;
    L8:
        if (canRetryStaleEnumeration() == false) goto L11;
        reconcileChannelSet(r7);
    }

    private /* synthetic */ void lambda$reconcileChannelSet$8(int r3, FeedTimelineLoader.ChannelEnumeration r4, FeedConfig r5, int r6, int r7, Utilities.Callback r8, FeedTimelineLoader.WindowPage r9, ArrayList r10, boolean r11) {
        if (r3 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r4, r5, r6, r7) == false) goto L8;
    L11:
        applyEnumeration(r4);
        this.configGeneration = r4.configGeneration;
        HashSet<Long> r32 = new HashSet();
        ArrayList<FeedTimelineLoader.ChannelSnapshot> r42 = r4.included;
        int r52 = r42.size();
        int r72 = 0;
    L12:
        if (r72 >= r52) goto L14;
        FeedTimelineLoader.ChannelSnapshot r0 = r42.get(r72);
        r72 = r72 + 1;
        r32.add(Long.valueOf(r0.dialogId));
        goto L12
    L14:
        this.store.applyIncludedDialogs(r32);
        if (r9 != null) goto L17;
    L19:
        boolean r43 = false;
    L20:
        if (r9 == null) goto L25;
        if (r43 == true) goto L25;
        if (r10.isEmpty() == true) goto L25;
        MessagesController r53 = MessagesController.getInstance(this.currentAccount);
        r53.putUsers(r9.users, true);
        r53.putChats(r9.chats, true);
        this.store.mergeRows(r10);
    L25:
        if (r11 == false) goto L32;
        this.store.setEndReached(false);
        if (this.loading == false) goto L30;
        this.olderPagingBoundsDirty = true;
    L30:
        if (this.loadingNewer == false) goto L32;
        this.newerPagingBoundsDirty = true;
    L32:
        if (r8 == null) goto L36;
        r8.run(Boolean.valueOf(r43));
        return;
    L36:
        return;
    L17:
        if (r9.truncated == false) goto L19;
        r43 = true;
        goto L20
    L8:
        if (canRetryStaleEnumeration() == false) goto L11;
        reconcileChannelSet(r8);
    }

    private /* synthetic */ void lambda$reconcileChannelSet$9(FeedConfig r13, int r14, int r15, int r16, Utilities.Callback r17, HashSet r18, HashSet r19, FeedTimelineLoader.Cursor r20, FeedTimelineLoader.Cursor r21) {
        FeedTimelineLoader.ChannelEnumeration r0 = this.loader.enumerateChannels(r13, r14, true);
        if (r0.failed == false) goto L6;
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda21(this, r14, r0, r13, r15, r16, r17));
        return;
    L6:
        ArrayList<Long> r02 = new ArrayList();
        ArrayList<FeedTimelineLoader.ChannelSnapshot> r4 = r0.included;
        int r5 = r4.size();
        int r6 = 0;
    L7:
        if (r6 >= r5) goto L15;
        FeedTimelineLoader.ChannelSnapshot r7 = r4.get(r6);
        r6 = r6 + 1;
        FeedTimelineLoader.ChannelSnapshot r72 = r7;
        if (r18.contains(Long.valueOf(r72.dialogId)) == false) goto L14;
        if (r19.contains(Long.valueOf(r72.dialogId)) == false) goto L7;
    L14:
        r02.add(Long.valueOf(r72.dialogId));
        goto L7
    L15:
        ArrayList<MessageObject> r52 = null;
        if (r02.isEmpty() == false) goto L18;
        FeedTimelineLoader.WindowPage r8 = null;
    L19:
        if (r8 != null) goto L21;
    L24:
        if (r8 == null) goto L27;
        r52 = createMessageObjects(r8.messages, r8.users, r8.chats);
    L27:
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda23(this, r14, r0, r13, r15, r16, r17, r8, r52, !r02.isEmpty()));
        return;
    L21:
        if (r8.failed == false) goto L24;
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda22(this, r14, r0, r13, r15, r16, r17));
        return;
    L18:
        r8 = this.loader.loadChannelWindow(r02, r20, r21);
        goto L19
    }

    private /* synthetic */ void lambda$refreshReadState$10(int r2, FeedTimelineLoader.ChannelEnumeration r3, FeedConfig r4, int r5, int r6, Runnable r7) {
        if (r2 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r3, r4, r5, r6) == false) goto L8;
        applyEnumeration(r3);
    L8:
        if (r7 == null) goto L11;
        r7.run();
        return;
    }

    private /* synthetic */ void lambda$refreshReadState$11(FeedConfig r11, int r12, int r13, int r14, Runnable r15) {
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda20(this, r12, this.loader.enumerateChannels(r11, r12, true), r11, r13, r14, r15));
    }

    private /* synthetic */ void lambda$runAttempt$12(int r8, FeedTimelineLoader.ChannelEnumeration r9, FeedConfig r10, int r11, int r12, int r13, int r14) {
        if (r8 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r9, r10, r11, r12) == false) goto L8;
    L11:
        this.loading = false;
        postFeedResults(r13, r14, new ArrayList(), 2, false, true);
        postFeedCount(r13);
        flushInitialLoadWaiters(true);
        return;
    L8:
        if (canRetryStaleEnumeration() == false) goto L11;
        this.attemptRounds = 0;
        runAttempt();
    }

    private /* synthetic */ void lambda$runAttempt$13(int r8, FeedTimelineLoader.ChannelEnumeration r9, FeedConfig r10, int r11, int r12, int r13, int r14) {
        if (r8 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r9, r10, r11, r12) == false) goto L8;
        applyEnumeration(r9);
        this.olderPagingBoundsDirty = false;
        this.unreadTracker.clear();
        this.loading = false;
        this.store.setEndReached(true);
        postFeedResults(r13, r14, new ArrayList(), 2);
        postFeedCount(r13);
        flushInitialLoadWaiters();
        return;
    L8:
        if (canRetryStaleEnumeration() == false) goto L11;
        this.olderPagingBoundsDirty = false;
        this.attemptRounds = 0;
        runAttempt();
        return;
    L11:
        this.loading = false;
        postFeedResults(r13, r14, new ArrayList(), 2, false, true);
        postFeedCount(r13);
        flushInitialLoadWaiters(true);
    }

    private /* synthetic */ void lambda$runAttempt$14(int r8, FeedTimelineLoader.ChannelEnumeration r9, FeedConfig r10, int r11, int r12, FeedTimelineLoader.OlderPage r13, int r14, int r15, boolean r16, ArrayList r17) {
        if (r8 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r9, r10, r11, r12) == true) goto L12;
        if (canRetryStaleEnumeration() == false) goto L12;
        this.olderPagingBoundsDirty = false;
        this.attemptRounds = 0;
        runAttempt();
        return;
    L12:
        if (this.olderPagingBoundsDirty == false) goto L15;
        this.olderPagingBoundsDirty = false;
        this.attemptRounds = 0;
        runAttempt();
        return;
    L15:
        applyEnumeration(r9);
        MessagesController r82 = MessagesController.getInstance(this.currentAccount);
        pruneStaleExclusions(FeedConfig.getInstance(this.currentAccount), r82);
        if (r13.failed == false) goto L19;
        this.loading = false;
        postFeedResults(r14, r15, new ArrayList(), 2, false, true);
        postFeedCount(r14);
        flushInitialLoadWaiters(true);
        return;
    L19:
        FeedTimelineLoader.Cursor r2 = this.store.getOldestCursor();
        FeedTimelineLoader.Cursor r3 = r13.last;
        r2.set(r3.date, r3.uid, r3.mid);
        if (r16 == true) goto L22;
    L24:
        r82.putUsers(r13.users, true);
        r82.putChats(r13.chats, true);
        ArrayList<MessageObject> r83 = this.store.appendMessages(r17, false);
        if (r83.isEmpty() == false) goto L31;
        if (r13.lastChunkRowCount != 30) goto L31;
        runAttempt();
        return;
    L31:
        if (r13.hasIncomplete == false) goto L33;
    L35:
        boolean r22 = false;
    L37:
        if (r83.isEmpty() == false) goto L46;
        if (r22 == true) goto L46;
        if (r13.backfillCandidates.isEmpty() == true) goto L46;
        int r32 = this.attemptRounds;
        if (r32 >= 3) goto L46;
        this.attemptRounds = r32 + 1;
        this.backfill.startRound(r13.backfillCandidates);
        return;
    L46:
        this.loading = false;
        this.store.setEndReached(r22);
        postFeedResults(r14, r15, r83, 2);
        postFeedCount(r14);
        flushInitialLoadWaiters();
        return;
    L33:
        if (r13.lastChunkRowCount >= 30) goto L35;
        r22 = true;
        goto L37
    L22:
        if (r13.first.isEmpty() == true) goto L24;
        FeedTimelineLoader.Cursor r23 = this.store.getNewestCursor();
        FeedTimelineLoader.Cursor r33 = r13.first;
        r23.set(r33.date, r33.uid, r33.mid);
        goto L24
    }

    private /* synthetic */ void lambda$runAttempt$15(FeedConfig r13, int r14, int r15, int r16, int r17, int r18, FeedTimelineLoader.Cursor r19, HashSet r20, boolean r21) {
        FeedTimelineLoader.ChannelEnumeration r0 = this.loader.enumerateChannels(r13, r14, false);
        ArrayList<FeedTimelineLoader.ChannelSnapshot> r2 = r0.included;
        if (r0.failed == false) goto L7;
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda15(this, r14, r0, r13, r15, r16, r17, r18));
        return;
    L7:
        if (r2.isEmpty() == false) goto L10;
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda16(this, r14, r0, r13, r15, r16, r17, r18));
        return;
    L10:
        FeedTimelineLoader.OlderPage r7 = this.loader.loadOlderPage(r2, r19, r20);
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda17(this, r14, r0, r13, r15, r16, r7, r17, r18, r21, createMessageObjects(r7.messages, r7.users, r7.chats)));
    }

    private /* synthetic */ void lambda$runLoadNewer$16(int r2, FeedTimelineLoader.ChannelEnumeration r3, FeedConfig r4, int r5, int r6, int r7, int r8) {
        if (r2 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r3, r4, r5, r6) == false) goto L8;
    L11:
        this.loadingNewer = false;
        postNewerMessagesLoaded(r7, r8, null, false, true);
        return;
    L8:
        if (canRetryStaleEnumeration() == false) goto L11;
        runLoadNewer(r7, r8);
    }

    private /* synthetic */ void lambda$runLoadNewer$17(int r2, FeedTimelineLoader.ChannelEnumeration r3, FeedConfig r4, int r5, int r6, int r7, int r8) {
        if (r2 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r3, r4, r5, r6) == false) goto L8;
    L11:
        this.newerPagingBoundsDirty = false;
        this.loadingNewer = false;
        postNewerMessagesLoaded(r7, r8, null, false);
        postFeedCount(r7);
        return;
    L8:
        if (canRetryStaleEnumeration() == false) goto L11;
        this.newerPagingBoundsDirty = false;
        runLoadNewer(r7, r8);
    }

    private /* synthetic */ void lambda$runLoadNewer$18(int r7, FeedTimelineLoader.ChannelEnumeration r8, FeedConfig r9, int r10, int r11, int r12, int r13, FeedTimelineLoader.NewerPage r14, ArrayList r15) {
        if (r7 == this.sessionGeneration) goto L6;
        return;
    L6:
        if (isEnumerationCurrent(r8, r9, r10, r11) == true) goto L12;
        if (canRetryStaleEnumeration() == false) goto L12;
        this.newerPagingBoundsDirty = false;
        runLoadNewer(r12, r13);
        return;
    L12:
        if (this.newerPagingBoundsDirty == false) goto L19;
        this.newerPagingBoundsDirty = false;
        if (this.store.getNewestCursor().isEmpty() == false) goto L17;
        this.loadingNewer = false;
        postNewerMessagesLoaded(r12, r13, null, false);
        postFeedCount(r12);
        return;
    L17:
        runLoadNewer(r12, r13);
        return;
    L19:
        this.loadingNewer = false;
        applyEnumeration(r8);
        if (r14.failed == false) goto L23;
        postNewerMessagesLoaded(r12, r13, null, false, true);
        return;
    L23:
        FeedTimelineLoader.Cursor r6 = this.store.getNewestCursor();
        FeedTimelineLoader.Cursor r72 = r14.first;
        r6.set(r72.date, r72.uid, r72.mid);
        if (r14.messages.isEmpty() == false) goto L29;
        postNewerMessagesLoaded(r12, r13, null, r14.hasMore);
        if (r14.hasMore == true) goto L34;
        postFeedCount(r12);
        return;
    L34:
        return;
    L29:
        MessagesController r62 = MessagesController.getInstance(this.currentAccount);
        r62.putUsers(r14.users, true);
        r62.putChats(r14.chats, true);
        postNewerMessagesLoaded(r12, r13, this.store.appendMessages(r15, true), r14.hasMore);
        if (r14.hasMore == true) goto L32;
        postFeedCount(r12);
    L32:
        trimForInactiveCache();
    }

    private /* synthetic */ void lambda$runLoadNewer$19(FeedConfig r12, int r13, int r14, int r15, int r16, int r17, FeedTimelineLoader.Cursor r18) {
        FeedTimelineLoader.ChannelEnumeration r0 = this.loader.enumerateChannels(r12, r13, false);
        ArrayList<FeedTimelineLoader.ChannelSnapshot> r2 = r0.included;
        if (r0.failed == false) goto L7;
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda9(this, r13, r0, r12, r14, r15, r16, r17));
        return;
    L7:
        if (r2.isEmpty() == false) goto L10;
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda10(this, r13, r0, r12, r14, r15, r16, r17));
        return;
    L10:
        FeedTimelineLoader.NewerPage r9 = this.loader.loadNewerPage(r2, r18);
        AndroidUtilities.runOnUIThread(new FeedController$$ExternalSyntheticLambda11(this, r13, r0, r12, r14, r15, r16, r17, r9, createMessageObjects(r9.messages, r9.users, r9.chats)));
    }

    private void onBackfillRoundFinished() {
        if (this.loading == false) goto L6;
        runAttempt();
        return;
    }

    private void onFeedChannelsChanged(HashSet<Long> r4, HashSet<Long> r5) {
        this.loader.invalidateChannelCache();
        Iterator<Long> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L7;
        deleteHistory(r52.next().longValue(), Integer.MAX_VALUE);
        goto L4
    L7:
        if (r4.isEmpty() == false) goto L10;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.feedNeedReload, new Object[]{Boolean.FALSE});
        return;
    L10:
        reconcileChannelSet(new FeedController$$ExternalSyntheticLambda19(this));
    }

    private void onFeedRowsRemoved() {
        if (this.loading == false) goto L6;
        this.olderPagingBoundsDirty = true;
    L6:
        if (this.loadingNewer == false) goto L9;
        this.newerPagingBoundsDirty = true;
        return;
    }

    public static FeedController peekInstance(int r1) {
        return Instance[r1];
    }

    private void postFeedCount(int r9) {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.hashtagSearchUpdated, new Object[]{Integer.valueOf(r9), Integer.valueOf(this.store.getCount()), Boolean.valueOf(this.store.isEndReached()), 0, 0, 0});
    }

    private void postFeedResults(int r8, int r9, ArrayList<MessageObject> r10, int r11) {
        postFeedResults(r8, r9, r10, r11, false, false);
    }

    private void postFeedResults(int r22, int r23, ArrayList<MessageObject> r24, int r25, boolean r26, boolean r27) {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messagesDidLoad, new Object[]{0L, Integer.valueOf(r24.size()), r24, Boolean.FALSE, 0, 0, 0, 0, Integer.valueOf(r25), Boolean.TRUE, Integer.valueOf(r22), Integer.valueOf(r23), 0, 0, 7, Boolean.valueOf(r26), Boolean.valueOf(r27)});
    }

    private void postNewerMessagesLoaded(int r7, int r8, ArrayList<MessageObject> r9, boolean r10) {
        postNewerMessagesLoaded(r7, r8, r9, r10, false);
    }

    private void postNewerMessagesLoaded(int r8, int r9, ArrayList<MessageObject> r10, boolean r11, boolean r12) {
        ArrayList<MessageObject> r3 = new ArrayList();
        if (r10 != null) goto L5;
    L8:
        int r102 = 0;
    L9:
        postFeedResults(r8, r9, r3, r102, r11, r12);
        return;
    L5:
        if (r10.isEmpty() == true) goto L8;
        r3.addAll(r10);
        Collections.reverse(r3);
        r102 = 1;
        goto L9
    }

    private void pruneStaleExclusions(FeedConfig r6, MessagesController r7) {
        Iterator<Long> r0 = r6.getExcludedSnapshot().iterator();
        HashSet r1 = null;
    L4:
        if (r0.hasNext() == false) goto L12;
        Long r2 = r0.next();
        TLRPC.Chat r3 = r7.getChat(Long.valueOf(-r2.longValue()));
        if (r3 == null) goto L4;
        if (isEligibleChannel(r3) == true) goto L4;
        if (r1 != null) goto L11;
        r1 = new HashSet();
    L11:
        r1.add(r2);
        goto L4
    L12:
        if (r1 == null) goto L22;
        r6.removeExcluded(r1);
        markConfigApplied();
        return;
    }

    private void reconcileChannelSet(Utilities.Callback<Boolean> r14) {
        int r3 = this.sessionGeneration;
        FeedConfig r2 = FeedConfig.getInstance(this.currentAccount);
        int r4 = r2.getGeneration();
        int r5 = this.loader.getChannelCacheEpoch();
        if (this.store.isEmpty() == false) goto L6;
        loadChannels(new FeedController$$ExternalSyntheticLambda12(this, r14));
        return;
    L6:
        HashSet<Long> r7 = this.store.getLoadedDialogIds();
        HashSet<Long> r8 = this.store.getHiddenSnapshot();
        FeedTimelineLoader.Cursor r9 = new FeedTimelineLoader.Cursor();
        FeedTimelineLoader.Cursor r10 = new FeedTimelineLoader.Cursor();
        r9.set(this.store.getNewestCursor().date, this.store.getNewestCursor().uid, this.store.getNewestCursor().mid);
        r10.set(this.store.getOldestCursor().date, this.store.getOldestCursor().uid, this.store.getOldestCursor().mid);
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new FeedController$$ExternalSyntheticLambda13(this, r2, r3, r4, r5, r14, r7, r8, r9, r10));
    }

    private void runAttempt() {
        int r6 = this.heldGuid;
        int r7 = this.heldLoadIndex;
        int r3 = this.sessionGeneration;
        FeedConfig r2 = FeedConfig.getInstance(this.currentAccount);
        int r4 = r2.getGeneration();
        int r5 = this.loader.getChannelCacheEpoch();
        boolean r10 = this.store.getOldestCursor().isEmpty();
        FeedTimelineLoader.Cursor r8 = new FeedTimelineLoader.Cursor();
        r8.set(this.store.getOldestCursor().date, this.store.getOldestCursor().uid, this.store.getOldestCursor().mid);
        HashSet<Long> r9 = this.backfill.getExhaustedSnapshot();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new FeedController$$ExternalSyntheticLambda4(this, r2, r3, r4, r5, r6, r7, r8, r9, r10));
    }

    private void runClosedRefresh() {
        this.closedRefreshScheduled = false;
        if (isUiActive() == false) goto L5;
        return;
    L5:
        if (this.loadingNewer == false) goto L7;
        return;
    L7:
        if (this.store.isEmpty() == false) goto L9;
        return;
    L9:
        if (this.store.getNewestCursor().isEmpty() == true) goto L16;
        loadNewer(this.closedRefreshGuid, 0);
        return;
    }

    private void runLoadNewer(int r11, int r12) {
        int r3 = this.sessionGeneration;
        FeedConfig r2 = FeedConfig.getInstance(this.currentAccount);
        int r4 = r2.getGeneration();
        int r5 = this.loader.getChannelCacheEpoch();
        FeedTimelineLoader.Cursor r8 = new FeedTimelineLoader.Cursor();
        r8.set(this.store.getNewestCursor().date, this.store.getNewestCursor().uid, this.store.getNewestCursor().mid);
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new FeedController$$ExternalSyntheticLambda6(this, r2, r3, r4, r5, r11, r12, r8));
    }

    private void scheduleClosedRefresh() {
        if (this.closedRefreshScheduled == false) goto L5;
        return;
    L5:
        this.closedRefreshScheduled = true;
        AndroidUtilities.runOnUIThread(this.closedRefreshRunnable, 1000);
    }

    private void updateCounters(LongSparseArray<SparseIntArray> r11, boolean r12, ArrayList<MessageObject> r13) {
        if (r11 == null) goto L26;
        int r1 = 0;
    L6:
        if (r1 >= r11.size()) goto L33;
        long r2 = r11.keyAt(r1);
        SparseIntArray r4 = r11.valueAt(r1);
        int r5 = 0;
    L9:
        if (r5 >= r4.size()) goto L25;
        MessageObject r6 = getMessage(r2, r4.keyAt(r5));
        if (r6 == null) goto L24;
        int r7 = r4.valueAt(r5);
        TLRPC.Message r8 = r6.messageOwner;
        if (r12 == false) goto L20;
        if (r7 <= r8.views) goto L24;
        r8.views = r7;
    L23:
        addUpdated(r13, r6);
        goto L24
    L20:
        if (r7 <= r8.forwards) goto L24;
        r8.forwards = r7;
    L24:
        r5 = r5 + 1;
        goto L9
    L25:
        r1 = r1 + 1;
        goto L6
    L33:
        return;
    }

    private void updateReplies(LongSparseArray<SparseArray<TLRPC.MessageReplies>> r13, boolean r14, ArrayList<MessageObject> r15) {
        if (r13 == null) goto L40;
        int r1 = 0;
    L6:
        if (r1 >= r13.size()) goto L49;
        long r2 = r13.keyAt(r1);
        SparseArray<TLRPC.MessageReplies> r4 = r13.valueAt(r1);
        int r5 = 0;
    L9:
        if (r5 >= r4.size()) goto L39;
        MessageObject r6 = getMessage(r2, r4.keyAt(r5));
        TLRPC.MessageReplies r7 = r4.valueAt(r5);
        if (r6 == null) goto L38;
        if (r7 == null) goto L38;
        TLRPC.Message r8 = r6.messageOwner;
        if (r14 == true) goto L17;
        TLRPC.MessageReplies r9 = r8.replies;
        if (r9 != null) goto L30;
    L36:
        r8.replies = r7;
    L37:
        r6.animateComments = true;
        addUpdated(r15, r6);
        goto L38
    L30:
        if (r7.replies_pts > r9.replies_pts) goto L36;
        if (r7.read_max_id > r9.read_max_id) goto L36;
        if (r7.max_id > r9.max_id) goto L36;
    L17:
        if (r8.replies != null) goto L19;
        r8.replies = new TLRPC.TL_messageReplies();
    L19:
        r6.messageOwner.replies.replies += r7.replies;
        int r82 = 0;
    L20:
        int r92 = r7.recent_repliers.size();
        TLRPC.Message r10 = r6.messageOwner;
        if (r82 >= r92) goto L23;
        r10.replies.recent_repliers.remove(r7.recent_repliers.get(r82));
        r82 = r82 + 1;
        goto L20
    L23:
        r10.replies.recent_repliers.addAll(0, r7.recent_repliers);
    L25:
        if (r6.messageOwner.replies.recent_repliers.size() <= 3) goto L37;
        r6.messageOwner.replies.recent_repliers.remove(0);
    L38:
        r5 = r5 + 1;
        goto L9
    L39:
        r1 = r1 + 1;
        goto L6
    L49:
        return;
    }

    public void applyConfigChange(Utilities.Callback<Boolean> r1) {
        reconcileChannelSet(r1);
    }

    public void cancelLoads() {
        this.sessionGeneration++;
        this.loading = false;
        this.loadingNewer = false;
        this.olderPagingBoundsDirty = false;
        this.newerPagingBoundsDirty = false;
        this.attemptRounds = 0;
        this.staleEnumerationRetries = 0;
        this.initialLoadWaiters.clear();
        this.backfill.cancel();
    }

    public void clear() {
        this.sessionGeneration++;
        this.configGeneration = FeedConfig.getInstance(this.currentAccount).getGeneration();
        this.unreadTracker.clear();
        this.drawerScrollPosition = null;
        this.store.clear();
        this.loading = false;
        this.loadingNewer = false;
        this.olderPagingBoundsDirty = false;
        this.newerPagingBoundsDirty = false;
        this.attemptRounds = 0;
        this.staleEnumerationRetries = 0;
        this.initialLoadWaiters.clear();
        this.backfill.cancel();
        this.backfill.clearExhausted();
        if (this.closedRefreshScheduled == false) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.closedRefreshRunnable);
        this.closedRefreshScheduled = false;
        return;
    }

    public boolean consumeInitialUnreadScroll() {
        boolean r0 = this.initialUnreadScrollPending;
        this.initialUnreadScrollPending = false;
        return r0;
    }

    public int countUnreadBelow(ArrayList<MessageObject> r1, int r2) {
        return this.unreadTracker.countUnreadBelow(r1, r2);
    }

    public ArrayList<Integer> deleteHistory(long r3, int r5) {
        boolean[] r0 = new boolean[1];
        ArrayList<Integer> r32 = this.store.deleteHistory(r3, r5, r0);
        if (r0[0] == false) goto L5;
        onFeedRowsRemoved();
    L5:
        return r32;
    }

    public ArrayList<Integer> deleteMessages(long r3, ArrayList<Integer> r5) {
        boolean[] r0 = new boolean[1];
        ArrayList<Integer> r32 = this.store.deleteMessages(r3, r5, r0);
        if (r0[0] == false) goto L5;
        onFeedRowsRemoved();
    L5:
        return r32;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.messagesDidLoad) goto L7;
        this.backfill.onMessagesDidLoad(r6);
        return;
    L7:
        if (r4 != NotificationCenter.loadingMessagesFailed) goto L11;
        this.backfill.onLoadingMessagesFailed(r6);
        return;
    L11:
        if (r4 != NotificationCenter.messagesDeleted) goto L25;
        if (isUiActive() == false) goto L15;
        return;
    L15:
        if (((Boolean) r6[2]).booleanValue() == true) goto L55;
        long r42 = ((Long) r6[1]).longValue();
        if (r42 == 0) goto L56;
        if (r42 <= 0) goto L22;
        r42 = -r42;
    L22:
        deleteMessages(r42, (ArrayList) r6[0]);
        return;
    L56:
        return;
    L55:
        return;
    L25:
        if (r4 != NotificationCenter.historyCleared) goto L35;
        if (isUiActive() == true) goto L57;
        long r43 = ((Long) r6[0]).longValue();
        if (DialogObject.isChatDialog(r43) == false) goto L58;
        deleteHistory(r43, ((Integer) r6[1]).intValue());
        return;
    L58:
        return;
    L57:
        return;
    L35:
        if (r4 == NotificationCenter.didReceiveNewMessages) goto L37;
        return;
    L37:
        if (isUiActive() == false) goto L39;
        return;
    L39:
        if (((Boolean) r6[2]).booleanValue() == false) goto L42;
        return;
    L42:
        if (this.store.isEmpty() == false) goto L44;
        return;
    L44:
        if (this.store.getNewestCursor().isEmpty() == false) goto L46;
        return;
    L46:
        if (isIncludedChannelPost(((Long) r6[0]).longValue()) == false) goto L60;
        scheduleClosedRefresh();
        return;
    }

    public int findFirstUnreadIndex(ArrayList<MessageObject> r1) {
        return this.unreadTracker.findFirstUnreadIndex(r1);
    }

    public SavedScrollPosition getDrawerScrollPosition() {
        return this.drawerScrollPosition;
    }

    public int getIncludedChannelCount() {
        return this.cachedIncludedChannelCount;
    }

    public MessageObject getMessage(long r1, int r3) {
        return this.store.getMessage(r1, r3);
    }

    public ArrayList<MessageObject> getMessages() {
        return this.store.getMessages();
    }

    public FeedStore getStore() {
        return this.store;
    }

    public int getUnreadCount() {
        if (ExteraConfig.getShowFeedUnreadCounter() == true) goto L5;
        return 0;
    L5:
        return this.unreadTracker.getUnreadCount();
    }

    public boolean hasChannels() {
        return this.hasChannels;
    }

    public boolean hasIncludedChannels() {
        return this.hasIncludedChannels;
    }

    public boolean hasMessagesForDialog(long r1) {
        return this.store.hasMessagesForDialog(r1);
    }

    public boolean hasNoSyntheticIds() {
        return this.store.hasNoSyntheticIds();
    }

    public boolean isIncludedChannelPost(long r2) {
        if (DialogObject.isChatDialog(r2) == true) goto L5;
        return false;
    L5:
        if (FeedConfig.getInstance(this.currentAccount).isExcluded(r2) == false) goto L8;
        return false;
    L8:
        return isEligibleChannel(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r2)));
    }

    public boolean isLoading() {
        if (this.loading == false) goto L5;
        return true;
    L5:
        if (this.loadingNewer == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void loadChannels(ChannelsCallback r2) {
        loadChannels(false, r2);
    }

    public void loadChannels(boolean r10, ChannelsCallback r11) {
        FeedConfig r3 = FeedConfig.getInstance(this.currentAccount);
        int r4 = this.sessionGeneration;
        int r6 = r3.getGeneration();
        int r7 = this.loader.getChannelCacheEpoch();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new FeedController$$ExternalSyntheticLambda5(this, r3, r4, r10, r6, r7, r11));
    }

    public boolean loadInitial(int r11, int r12) {
        ensureCurrentConfig();
        FeedConfig r3 = FeedConfig.getInstance(this.currentAccount);
        int r5 = r3.getGeneration();
        int r6 = this.loader.getChannelCacheEpoch();
        if (this.store.isEmpty() == true) goto L5;
        ArrayList<MessageObject> r9 = this.store.getVisibleMessages();
        int r0 = r9.size();
        int r2 = 0;
    L9:
        if (r2 >= r0) goto L12;
        MessageObject r4 = r9.get(r2);
        r2 = r2 + 1;
        r4.viewsReloaded = false;
        goto L9
    L12:
        if (r9.isEmpty() == true) goto L14;
    L19:
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new FeedController$$ExternalSyntheticLambda18(this, r3, this.sessionGeneration, r5, r6, r11, r12, r9));
        return true;
    L14:
        if (this.store.isEndReached() == true) goto L19;
        if (loadMore(r11, r12) == true) goto L18;
        this.initialLoadWaiters.add(new int[]{r11, r12});
    L18:
        return false;
    L5:
        if (loadMore(r11, r12) == true) goto L7;
        this.initialLoadWaiters.add(new int[]{r11, r12});
    L7:
        return false;
    }

    public boolean loadMore(int r3, int r4) {
        ensureCurrentConfig();
        if (this.loading == false) goto L5;
    L11:
        return false;
    L5:
        if (this.store.isEndReached() == true) goto L7;
    L9:
        this.loading = true;
        this.heldGuid = r3;
        this.heldLoadIndex = r4;
        this.attemptRounds = 0;
        runAttempt();
        return true;
    L7:
        if (this.store.getOldestCursor().isEmpty() == true) goto L9;
        goto L9
    }

    public boolean loadNewer(int r2, int r3) {
        ensureCurrentConfig();
        if (this.loadingNewer == false) goto L5;
        return false;
    L5:
        if (this.store.getNewestCursor().isEmpty() == true) goto L11;
        this.loadingNewer = true;
        runLoadNewer(r2, r3);
        return true;
    L11:
        return false;
    }

    public void markAllRead() {
        this.unreadTracker.markAllRead();
    }

    public void markConfigApplied() {
        this.configGeneration = FeedConfig.getInstance(this.currentAccount).getGeneration();
    }

    public void onPostSeen(long r1, int r3) {
        this.unreadTracker.onPostSeen(r1, r3);
    }

    public void refreshReadState(Runnable r9) {
        int r3 = this.sessionGeneration;
        FeedConfig r2 = FeedConfig.getInstance(this.currentAccount);
        int r4 = r2.getGeneration();
        int r5 = this.loader.getChannelCacheEpoch();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new FeedController$$ExternalSyntheticLambda8(this, r2, r3, r4, r5, r9));
    }

    public void replaceMessage(MessageObject r1, MessageObject r2) {
        this.store.replaceMessage(r1, r2);
    }

    public long resolveRealDialogId(int r1) {
        return this.store.resolveRealDialogId(r1);
    }

    public int resolveRealMessageId(long r1, int r3) {
        return this.store.resolveRealMessageId(r1, r3);
    }

    public void saveDrawerScrollPosition(long r8, int r10, int r11) {
        if (r8 == 0) goto L7;
        if (r10 <= 0) goto L8;
        this.drawerScrollPosition = new SavedScrollPosition(r8, r10, r11, null);
        return;
    L8:
        return;
    }

    public void setUiActive(boolean r3) {
        int r0 = this.uiActiveClients;
        if (r3 == false) goto L14;
        int r02 = r0 + 1;
        this.uiActiveClients = r02;
        if (r02 <= 1) goto L8;
        return;
    L8:
        if (this.closedRefreshScheduled == false) goto L11;
        AndroidUtilities.cancelRunOnUIThread(this.closedRefreshRunnable);
        this.closedRefreshScheduled = false;
    L11:
        if (this.loadingNewer == false) goto L20;
        cancelLoads();
        return;
    L20:
        return;
    L14:
        if (r0 == 0) goto L23;
        int r03 = r0 - 1;
        this.uiActiveClients = r03;
        if (r03 != 0) goto L21;
        cancelLoads();
        trimForInactiveCache();
        return;
    L21:
        return;
    }

    public void setUiResumed(boolean r2) {
        int r0 = this.resumedUiClients;
        if (r2 == false) goto L6;
        this.resumedUiClients = r0 + 1;
        return;
    L6:
        if (r0 <= 0) goto L9;
        this.resumedUiClients = r0 - 1;
        return;
    }

    public void trimForInactiveCache() {
        if (isUiActive() == false) goto L5;
        return;
    L5:
        if (this.store.isEmpty() == true) goto L10;
        this.store.trim(getInactiveCacheCap());
        return;
    }

    public ArrayList<MessageObject> updateViews(LongSparseArray<SparseIntArray> r3, LongSparseArray<SparseIntArray> r4, LongSparseArray<SparseArray<TLRPC.MessageReplies>> r5, boolean r6) {
        ArrayList<MessageObject> r0 = new ArrayList();
        updateCounters(r3, true, r0);
        updateCounters(r4, false, r0);
        updateReplies(r5, r6, r0);
        return r0;
    }
}
