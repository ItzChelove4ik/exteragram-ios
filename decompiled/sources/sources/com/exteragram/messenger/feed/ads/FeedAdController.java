package com.exteragram.messenger.feed.ads;

import android.os.SystemClock;
import android.text.TextUtils;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.network.RemoteUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final class FeedAdController {
    private static final FeedAdController[] instances = null;
    private static final Object[] locks = null;
    private final ArrayList<FeedAd> allAds;
    public final int currentAccount;
    private ArrayList<FeedAd> eligibleAds;
    private boolean lastLoadFailed;
    private long lastLoadTime;
    private boolean loading;
    private final ArrayList<Runnable> pendingLoadCallbacks;
    private final ArrayList<FeedAd> rotation;
    private int rotationIndex;

    public static /* synthetic */ void $r8$lambda$8hucFROLudfrxYOpOPN8_6OECqs(Utilities.Callback2 r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new FeedAdController$$ExternalSyntheticLambda4(r3, r2, r1));
    }

    public static /* synthetic */ void $r8$lambda$F74A0h3yQjwWcCURFdXXPBFC7aY(TLRPC.TL_messages_getHistory r4, Runnable r5, Utilities.Callback2 r6, TLRPC.Chat r7) {
        if (r7 != null) goto L4;
    L7:
        r6.run(null, null);
        return;
    L4:
        if (r7.id != 3514621311L) goto L7;
        TLRPC.TL_inputPeerChannel r62 = new TLRPC.TL_inputPeerChannel();
        r62.channel_id = r7.id;
        r62.access_hash = r7.access_hash;
        r4.peer = r62;
        r5.run();
    }

    public static /* synthetic */ void $r8$lambda$UyQJ6hKfkjrDAb5zA2Vrmdbgx6I(FeedAdController r0, TLRPC.messages_Messages r1, TLRPC.TL_error r2) {
        r0.lambda$ensureLoaded$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$r4x3bpNjl9gEMOc_86Ljc5rjyUQ(AccountInstance r1, TLRPC.TL_messages_getHistory r2, Utilities.Callback2 r3) {
        r1.getConnectionsManager().sendRequest(r2, new FeedAdController$$ExternalSyntheticLambda3(r3));
    }

    /* renamed from: $r8$lambda$suri1kp-T0eJ8sN4FqCkAUB1c0c, reason: not valid java name */
    public static /* synthetic */ void m1424$r8$lambda$suri1kpT0eJ8sN4FqCkAUB1c0c(TLRPC.TL_error r2, TLObject r3, Utilities.Callback2 r4) {
        if (r2 == null) goto L5;
    L9:
        r4.run(null, r2);
        return;
    L5:
        if ((r3 instanceof TLRPC.messages_Messages) == false) goto L9;
        r4.run((TLRPC.messages_Messages) r3, null);
    }

    static {
        instances = new FeedAdController[16];
        locks = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        locks[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private FeedAdController(int r2) {
        this.allAds = new ArrayList();
        this.eligibleAds = new ArrayList();
        this.rotation = new ArrayList();
        this.pendingLoadCallbacks = new ArrayList();
        this.currentAccount = r2;
    }

    private static boolean contains(Set<String> r0, String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r0.contains(r1.toLowerCase()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    private void fetchHistory(Utilities.Callback2<TLRPC.messages_Messages, TLRPC.TL_error> r8) {
        AccountInstance r0 = AccountInstance.getInstance(this.currentAccount);
        TLRPC.TL_messages_getHistory r1 = new TLRPC.TL_messages_getHistory();
        r1.peer = r0.getMessagesController().getInputPeer(-3514621311L);
        r1.offset_id = 0;
        r1.limit = 75;
        FeedAdController$$ExternalSyntheticLambda1 r2 = new FeedAdController$$ExternalSyntheticLambda1(r0, r1, r8);
        TLRPC.InputPeer r02 = r1.peer;
        if (r02 != null) goto L5;
    L8:
        ChatUtils.getInstance(this.currentAccount).resolveChannel("exteraFeedAds", new FeedAdController$$ExternalSyntheticLambda2(r1, r2, r8));
        return;
    L5:
        if (r02.access_hash == 0) goto L8;
        r2.run();
    }

    public static FeedAdController getInstance(int r3) {
        FeedAdController[] r0 = instances;
        FeedAdController r1 = r0[r3];
        if (r1 != null) goto L15;
        Object r2 = locks[r3];
        monitor-enter(r2);
        FeedAdController r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new FeedAdController(r3);     // Catch: Throwable -> L9
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

    private boolean isEligible(FeedAd r5) {
        if (matchesLocale(r5.locales) == true) goto L5;
        return false;
    L5:
        boolean r4 = UserConfig.getInstance(this.currentAccount).isPremium();
        int r0 = r5.premium;
        if (r0 != 1) goto L9;
        if (r4 == true) goto L9;
    L11:
        return false;
    L9:
        if (r0 != 2) goto L12;
        if (r4 == true) goto L11;
    L12:
        boolean r42 = BadgesController.INSTANCE.hasBadge();
        int r52 = r5.badge;
        if (r52 != 1) goto L15;
        if (r42 == true) goto L15;
    L17:
        return false;
    L15:
        if (r52 != 2) goto L18;
        if (r42 == true) goto L17;
    L18:
        return true;
    }

    private /* synthetic */ void lambda$ensureLoaded$0(TLRPC.messages_Messages r4, TLRPC.TL_error r5) {
        int r0 = 0;
        this.loading = false;
        this.lastLoadTime = SystemClock.elapsedRealtime();
        if (r5 != null) goto L7;
        if (r4 == null) goto L7;
        boolean r52 = false;
    L8:
        this.lastLoadFailed = r52;
        if (r52 == true) goto L11;
        ArrayList<FeedAd> r42 = FeedAdParser.parse(r4);
        this.allAds.clear();
        this.allAds.addAll(r42);
        recomputeEligible();
    L11:
        ArrayList r43 = new ArrayList(this.pendingLoadCallbacks);
        this.pendingLoadCallbacks.clear();
    L13:
        if (r0 >= r43.size()) goto L15;
        ((Runnable) r43.get(r0)).run();
        r0 = r0 + 1;
        goto L13
    L15:
        return;
    L7:
        r52 = true;
        goto L8
    }

    private boolean matchesLocale(Set<String> r3) {
        if (r3 != null) goto L5;
        return true;
    L5:
        if (r3.isEmpty() == true) goto L22;
        LocaleController.LocaleInfo r0 = LocaleController.getInstance().getCurrentLocaleInfo();
        if (r0 != null) goto L11;
        return true;
    L11:
        if (contains(r3, r0.getLangCode()) == false) goto L13;
        return true;
    L13:
        if (contains(r3, r0.shortName) == false) goto L15;
        return true;
    L15:
        if (contains(r3, r0.baseLangCode) == true) goto L23;
        return false;
    L23:
        return true;
    L22:
        return true;
    }

    private void reshuffleRotation() {
        this.rotation.clear();
        ArrayList<FeedAd> r0 = this.eligibleAds;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        FeedAd r4 = r0.get(r3);
        r3 = r3 + 1;
        FeedAd r42 = r4;
        int r5 = 0;
    L6:
        if (r5 >= Math.max(1, r42.weight)) goto L3;
        this.rotation.add(r42);
        r5 = r5 + 1;
        goto L6
    L8:
        Collections.shuffle(this.rotation);
        this.rotationIndex = 0;
    }

    public void ensureLoaded(Runnable r5) {
        if (this.loading == false) goto L5;
    L16:
        if (r5 == null) goto L19;
        this.pendingLoadCallbacks.add(r5);
    L19:
        if (this.loading == true) goto L23;
        this.loading = true;
        fetchHistory(new FeedAdController$$ExternalSyntheticLambda0(this));
        return;
    L23:
        return;
    L5:
        if (this.lastLoadTime == 0) goto L16;
        long r0 = SystemClock.elapsedRealtime() - this.lastLoadTime;
        if (this.lastLoadFailed == false) goto L9;
        long r2 = 60000;
    L11:
        if (r0 >= r2) goto L16;
        recomputeEligible();
        if (r5 == null) goto L20;
        r5.run();
        return;
    L20:
        return;
    L9:
        r2 = 1800000;
        goto L11
    }

    public int getBaseEvery() {
        return Math.max(1, RemoteUtils.getIntConfigValue("feed_ad_every", 30).intValue());
    }

    public int getEffectiveEvery() {
        int r0 = getBaseEvery();
        int r3 = this.eligibleAds.size();
        if (r3 > 1) goto L8;
        int r32 = Math.max(1, RemoteUtils.getIntConfigValue("feed_ad_spacing_pool1", 3).intValue());
    L6:
        return r0 * r32;
    L8:
        if (r3 != 2) goto L10;
        r32 = Math.max(1, RemoteUtils.getIntConfigValue("feed_ad_spacing_pool2", 2).intValue());
        goto L6
    L10:
        return r0;
    }

    public int getFirstAfter() {
        return Math.max(1, RemoteUtils.getIntConfigValue("feed_ad_first_after", 30).intValue());
    }

    public int getMinTrailing() {
        return Math.max(0, RemoteUtils.getIntConfigValue("feed_ad_min_trailing", 2).intValue());
    }

    public boolean isEnabled() {
        if (RemoteUtils.getBooleanConfigValue("feed_ads_enabled", true).booleanValue() == true) goto L5;
        return false;
    L5:
        if (this.eligibleAds.isEmpty() == true) goto L9;
        return true;
    L9:
        return false;
    }

    public FeedAd nextAd() {
        if (this.eligibleAds.isEmpty() == false) goto L7;
        return null;
    L7:
        if (this.rotation.isEmpty() == false) goto L9;
    L10:
        reshuffleRotation();
    L11:
        ArrayList<FeedAd> r0 = this.rotation;
        int r1 = this.rotationIndex;
        this.rotationIndex = r1 + 1;
        return r0.get(r1);
    L9:
        if (this.rotationIndex < this.rotation.size()) goto L11;
        goto L10
    }

    public void recomputeEligible() {
        ArrayList<FeedAd> r0 = new ArrayList(this.allAds.size());
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= this.allAds.size()) goto L10;
        if (isEligible(this.allAds.get(r2)) == false) goto L8;
        r0.add(this.allAds.get(r2));
    L8:
        r2 = r2 + 1;
        goto L4
    L10:
        if (r0.size() != this.eligibleAds.size()) goto L12;
        boolean r22 = true;
    L13:
        int r3 = 0;
    L14:
        if (r22 == false) goto L24;
        if (r3 >= r0.size()) goto L24;
        if (TextUtils.equals(r0.get(r3).id, this.eligibleAds.get(r3).id) == true) goto L20;
    L22:
        r22 = false;
    L23:
        r3 = r3 + 1;
        goto L14
    L20:
        if (r0.get(r3).weight != this.eligibleAds.get(r3).weight) goto L22;
        r22 = true;
    L24:
        this.eligibleAds = r0;
        ArrayList<FeedAd> r32 = this.rotation;
        if (r22 == true) goto L29;
        r32.clear();
        this.rotationIndex = 0;
        return;
    L29:
        if (r32.isEmpty() == true) goto L40;
        HashMap r23 = new HashMap();
        int r33 = 0;
    L32:
        if (r33 >= r0.size()) goto L35;
        r23.put(r0.get(r33).id, r0.get(r33));
        r33 = r33 + 1;
    L35:
        if (r1 >= this.rotation.size()) goto L52;
        FeedAd r02 = (FeedAd) r23.get(this.rotation.get(r1).id);
        if (r02 == null) goto L39;
        this.rotation.set(r1, r02);
    L39:
        r1 = r1 + 1;
        goto L35
    L52:
        return;
    L40:
        return;
    L12:
        r22 = false;
        goto L13
    }
}
