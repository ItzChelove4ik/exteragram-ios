package org.telegram.ui.Stars;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.LongSparseArray;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.QueryProductDetailsParams;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AppGlobalConfig;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileRefController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.TopicsController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.TON.TONIntroActivity;
import org.telegram.ui.bots.BotWebViewSheet;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class StarsController {
    private static volatile StarsController[][] Instance;
    private static final Object[][] lockObjects = null;
    public TL_stars.StarsAmount balance;
    private boolean balanceLoaded;
    private boolean balanceLoading;
    public final ArrayList<TL_stars.StarGift> birthdaySortedGifts;
    public final int currentAccount;
    private PaidMessagesToast currentPaidMessagesToast;
    public PendingPaidReactions currentPendingReactions;
    private final boolean[] endReached;
    public final LongSparseArray<GiftsCollections> giftCollections;
    public final LongSparseArray<GiftsList> giftLists;
    private ArrayList<TL_stars.TL_starsGiftOption> giftOptions;
    private boolean giftOptionsLoaded;
    private boolean giftOptionsLoading;
    private ConcurrentHashMap<Long, TL_stars.starGiftUpgradePreview> giftPreviews;
    public final ArrayList<TL_stars.StarGift> gifts;
    private boolean giftsCacheLoaded;
    public int giftsHash;
    public boolean giftsLoaded;
    public boolean giftsLoading;
    public long giftsRemoteTime;
    private ArrayList<TL_stars.TL_starsGiveawayOption> giveawayOptions;
    private boolean giveawayOptionsLoaded;
    private boolean giveawayOptionsLoading;
    public final ArrayList<TL_stars.StarsSubscription> insufficientSubscriptions;
    public final ConcurrentHashMap<Long, Long> justAgreedToNotAskDialogs;
    private long lastBalanceLoaded;
    private final boolean[] loading;
    public long minus;
    private final String[] offset;
    private ArrayList<TL_stars.TL_starsTopupOption> options;
    private boolean optionsLoaded;
    private boolean optionsLoading;
    private boolean paymentFormOpened;
    private final ConcurrentHashMap<Integer, Runnable> postponedPaidMessages;
    public final ConcurrentHashMap<Long, Integer> sendingMessagesCount;
    private final Set<Integer> sendingPaidMessagesIds;
    public final ArrayList<TL_stars.StarGift> sortedGifts;
    public final ArrayList<TL_stars.StarsSubscription> subscriptions;
    public boolean subscriptionsEndReached;
    public boolean subscriptionsLoading;
    public String subscriptionsOffset;
    public final boolean ton;
    public final ArrayList<TL_stars.StarsTransaction>[] transactions;
    public final boolean[] transactionsExist;

    public static class GiftsCollections {
        public GiftsList all;
        private ArrayList<TL_stars.TL_starGiftCollection> collections;
        public boolean creating;
        public final int currentAccount;
        public int currentRequestId;
        public final long dialogId;
        private ArrayList<TL_stars.TL_starGiftCollection> filteredCollections;
        public HashMap<Integer, GiftsList> gifts;
        public boolean loaded;
        public boolean loading;
        public boolean shown;

        /* renamed from: $r8$lambda$3DcNoxECVkEJ87EZyBr3Mu8fn-c, reason: not valid java name */
        public static /* synthetic */ void m19145$r8$lambda$3DcNoxECVkEJ87EZyBr3Mu8fnc(GiftsCollections r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$load$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$8QrrZoQF4CmusYMir7jaH_2YsGU(GiftsCollections r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$removeGifts$7(r1, r2);
        }

        /* renamed from: $r8$lambda$bTmgNTCLoBnN6adC5Ai-WG8zh6U, reason: not valid java name */
        public static /* synthetic */ void m19146$r8$lambda$bTmgNTCLoBnN6adC5AiWG8zh6U(GiftsCollections r0, TLObject r1) {
            r0.lambda$removeGifts$6(r1);
        }

        /* renamed from: $r8$lambda$gfhZCB5yxKoqgvILqFd9-1Z7XSY, reason: not valid java name */
        public static /* synthetic */ void m19147$r8$lambda$gfhZCB5yxKoqgvILqFd91Z7XSY(GiftsCollections r0, TL_stars.TL_starGiftCollection r1, GiftsList r2, Utilities.Callback r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$createCollection$3(r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$lrgihLBSwGp_0X7XkYxV5-3L1YE, reason: not valid java name */
        public static /* synthetic */ void m19148$r8$lambda$lrgihLBSwGp_0X7XkYxV53L1YE(GiftsCollections r0, TLObject r1) {
            r0.lambda$load$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$pzK0WKds46Ub4gQRY8uK7BXFA14(GiftsCollections r0, TLObject r1, TL_stars.TL_starGiftCollection r2, GiftsList r3, Utilities.Callback r4, TLRPC.TL_error r5) {
            r0.lambda$createCollection$2(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$qTEQtRx4l_gxmFCPF7XTCcSRhIM(GiftsCollections r0, TLObject r1) {
            r0.lambda$addGifts$4(r1);
        }

        /* renamed from: $r8$lambda$xtRbGB-TvRJCDlbzJXD6YG3TpZ8, reason: not valid java name */
        public static /* synthetic */ void m19149$r8$lambda$xtRbGBTvRJCDlbzJXD6YG3TpZ8(GiftsCollections r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$addGifts$5(r1, r2);
        }

        public GiftsCollections(int r2, long r3) {
            this(r2, r3, true);
        }

        public GiftsCollections(int r2, long r3, boolean r5) {
            this.collections = new ArrayList();
            this.filteredCollections = new ArrayList();
            this.gifts = new HashMap();
            this.currentRequestId = -1;
            this.currentAccount = r2;
            this.dialogId = r3;
            if (r5 == false) goto L6;
            load();
            return;
        }

        private long getHash(ArrayList<TL_stars.TL_starGiftCollection> r6) {
            int r5 = r6.size();
            long r0 = 0;
            int r2 = 0;
        L3:
            if (r2 >= r5) goto L5;
            TL_stars.TL_starGiftCollection r3 = r6.get(r2);
            r2 = r2 + 1;
            r0 = MediaDataController.calcHash(r0, r3.hash);
            goto L3
        L5:
            return r0;
        }

        private /* synthetic */ void lambda$addGifts$4(TLObject r2) {
            if ((r2 instanceof TL_stars.TL_starGiftCollection) == false) goto L8;
            TL_stars.TL_starGiftCollection r22 = (TL_stars.TL_starGiftCollection) r2;
            int r0 = indexOf(r22.collection_id);
            if (r0 < 0) goto L9;
            this.collections.set(r0, r22);
            return;
        L9:
            return;
        }

        private /* synthetic */ void lambda$addGifts$5(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new StarsController$GiftsCollections$$ExternalSyntheticLambda4(this, r1));
        }

        private /* synthetic */ void lambda$createCollection$2(TLObject r3, TL_stars.TL_starGiftCollection r4, GiftsList r5, Utilities.Callback r6, TLRPC.TL_error r7) {
            this.creating = false;
            if ((r3 instanceof TL_stars.TL_starGiftCollection) == false) goto L8;
            TL_stars.TL_starGiftCollection r32 = (TL_stars.TL_starGiftCollection) r3;
            this.collections.remove(r4);
            this.collections.add(r32);
            this.gifts.remove(-1);
            int r42 = r32.collection_id;
            r5.collectionId = r42;
            this.gifts.put(Integer.valueOf(r42), r5);
            refilterCollections();
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftCollectionsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            if (r6 == null) goto L14;
            r6.run(r32);
            return;
        L14:
            return;
        L8:
            if (r7 == null) goto L12;
            BaseFragment r33 = LaunchActivity.getSafeLastFragment();
            if (r33 == null) goto L12;
            BulletinFactory.of(r33).showForError(r7);
        L12:
            this.collections.remove(r4);
            this.gifts.remove(-1);
            refilterCollections();
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftCollectionsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
        }

        private /* synthetic */ void lambda$createCollection$3(TL_stars.TL_starGiftCollection r8, GiftsList r9, Utilities.Callback r10, TLObject r11, TLRPC.TL_error r12) {
            AndroidUtilities.runOnUIThread(new StarsController$GiftsCollections$$ExternalSyntheticLambda6(this, r11, r8, r9, r10, r12));
        }

        private /* synthetic */ void lambda$load$0(TLObject r10) {
            if ((r10 instanceof TL_stars.TL_starGiftCollections) == false) goto L13;
            this.collections.clear();
            this.collections.addAll(((TL_stars.TL_starGiftCollections) r10).collections);
            refilterCollections();
            ArrayList<TL_stars.TL_starGiftCollection> r102 = this.collections;
            int r0 = r102.size();
            int r3 = 0;
        L5:
            if (r3 >= r0) goto L10;
            TL_stars.TL_starGiftCollection r4 = r102.get(r3);
            r3 = r3 + 1;
            TL_stars.TL_starGiftCollection r42 = r4;
            if (getListById(r42.collection_id) != null) goto L5;
            GiftsList r5 = new GiftsList(this.currentAccount, this.dialogId, false);
            r5.setCollectionId(r42.collection_id);
            this.gifts.put(Integer.valueOf(r42.collection_id), r5);
            goto L5
        L10:
            this.loaded = true;
            this.loading = false;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftCollectionsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            return;
        L13:
            if ((r10 instanceof TL_stars.TL_starGiftCollectionsNotModified) == false) goto L21;
            refilterCollections();
            this.loaded = true;
            this.loading = false;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftCollectionsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            return;
        }

        private /* synthetic */ void lambda$load$1(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new StarsController$GiftsCollections$$ExternalSyntheticLambda1(this, r1));
        }

        private /* synthetic */ void lambda$removeGifts$6(TLObject r2) {
            if ((r2 instanceof TL_stars.TL_starGiftCollection) == false) goto L8;
            TL_stars.TL_starGiftCollection r22 = (TL_stars.TL_starGiftCollection) r2;
            int r0 = indexOf(r22.collection_id);
            if (r0 < 0) goto L9;
            this.collections.set(r0, r22);
            return;
        L9:
            return;
        }

        private /* synthetic */ void lambda$removeGifts$7(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new StarsController$GiftsCollections$$ExternalSyntheticLambda7(this, r1));
        }

        private void refilterCollections() {
            this.filteredCollections.clear();
            int r0 = 0;
        L4:
            if (r0 >= this.collections.size()) goto L10;
            TL_stars.TL_starGiftCollection r1 = this.collections.get(r0);
            if (r1.gifts_count <= 0) goto L9;
            this.filteredCollections.add(r1);
        L9:
            r0 = r0 + 1;
            goto L4
        }

        public void addGift(int r2, TL_stars.SavedStarGift r3, boolean r4) {
            ArrayList<TL_stars.SavedStarGift> r0 = new ArrayList();
            r0.add(r3);
            addGifts(r2, r0, r4);
        }

        public void addGifts(int r8, ArrayList<TL_stars.SavedStarGift> r9, boolean r10) {
            if (r9.isEmpty() == false) goto L5;
            return;
        L5:
            GiftsList r0 = getListById(r8);
            int r1 = 0;
            if (r0 == null) goto L9;
            if (r10 == false) goto L9;
            r0.gifts.addAll(0, r9);
            r0.totalCount += r9.size();
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), r0});
            updateIcon(r8);
        L9:
            TL_stars.updateStarGiftCollection r102 = new TL_stars.updateStarGiftCollection();
            r102.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r102.collection_id = r8;
            r102.flags |= 4;
            int r02 = r9.size();
        L10:
            if (r1 >= r02) goto L18;
            TL_stars.SavedStarGift r2 = r9.get(r1);
            r1 = r1 + 1;
            TL_stars.SavedStarGift r22 = r2;
            updateGiftsCollections(r22, r8, true);
            if (r22.msg_id > 0) goto L13;
            if (r22.saved_id != 0) goto L16;
            FileLog.w("can't convert gift to inputgift to add into the collection");
            goto L10
        L16:
            TL_stars.TL_inputSavedStarGiftChat r3 = new TL_stars.TL_inputSavedStarGiftChat();
            r3.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r3.saved_id = r22.saved_id;
            r102.add_stargift.add(r3);
            goto L10
        L13:
            TL_stars.TL_inputSavedStarGiftUser r32 = new TL_stars.TL_inputSavedStarGiftUser();
            r32.msg_id = r22.msg_id;
            r102.add_stargift.add(r32);
            goto L10
        L18:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r102, new StarsController$GiftsCollections$$ExternalSyntheticLambda2(this));
        }

        public void createCollection(String r9, Utilities.Callback<TL_stars.TL_starGiftCollection> r10) {
            if (this.creating == false) goto L5;
            return;
        L5:
            this.creating = true;
            TL_stars.TL_starGiftCollection r1 = new TL_stars.TL_starGiftCollection();
            r1.collection_id = -1;
            r1.title = r9;
            this.collections.add(r1);
            refilterCollections();
            GiftsList r3 = new GiftsList(this.currentAccount, this.dialogId, false);
            r3.setCollectionId(-1);
            r3.totalCount = 0;
            r3.endReached = true;
            this.gifts.put(-1, r3);
            TL_stars.createStarGiftCollection r0 = new TL_stars.createStarGiftCollection();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.title = r9;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$GiftsCollections$$ExternalSyntheticLambda3(this, r1, r3, r10));
        }

        public TL_stars.TL_starGiftCollection findById(int r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.collections.size()) goto L9;
            TL_stars.TL_starGiftCollection r1 = this.collections.get(r0);
            if (r4 == r1.collection_id) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return r1;
        L9:
            return null;
        }

        public ArrayList<TL_stars.TL_starGiftCollection> getCollections() {
            if (isMine() == false) goto L7;
            return this.collections;
        L7:
            return this.filteredCollections;
        }

        public GiftsList getListById(int r1) {
            return this.gifts.get(Integer.valueOf(r1));
        }

        public GiftsList getListByIndex(int r2) {
            if (r2 >= 0) goto L4;
            return null;
        L4:
            if (r2 < getCollections().size()) goto L7;
            return null;
        L7:
            return getListById(getCollections().get(r2).collection_id);
        }

        public int indexOf(int r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.collections.size()) goto L9;
            if (r3 == this.collections.get(r0).collection_id) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return r0;
        L9:
            return -1;
        }

        public void invalidate(boolean r5) {
            if (this.currentRequestId == (-1)) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.currentRequestId, true);
            this.currentRequestId = -1;
        L5:
            this.loading = false;
            this.loaded = false;
            if (r5 == false) goto L8;
        L11:
            load();
            return;
        L8:
            if (this.shown == true) goto L11;
        }

        public boolean isMine() {
            long r0 = this.dialogId;
            if (r0 < 0) goto L14;
            if (r0 != 0) goto L7;
            return true;
        L7:
            if (r0 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L15;
            return false;
        L15:
            return true;
        L14:
            return ChatObject.canUserDoAction(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId)), 5);
        }

        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.loaded == true) goto L10;
            this.loading = true;
            TL_stars.getStarGiftCollections r0 = new TL_stars.getStarGiftCollections();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.hash = getHash(this.collections);
            this.currentRequestId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$GiftsCollections$$ExternalSyntheticLambda0(this));
            return;
        }

        public void removeCollection(int r5) {
            int r52 = indexOf(r5);
            if (r52 != (-1)) goto L5;
            return;
        L5:
            TL_stars.TL_starGiftCollection r53 = this.collections.remove(r52);
            this.gifts.remove(Integer.valueOf(r53.collection_id));
            TL_stars.deleteStarGiftCollection r0 = new TL_stars.deleteStarGiftCollection();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.collection_id = r53.collection_id;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
        }

        public void removeGift(int r2, TL_stars.SavedStarGift r3) {
            ArrayList<TL_stars.SavedStarGift> r0 = new ArrayList();
            r0.add(r3);
            removeGifts(r2, r0);
        }

        public void removeGifts(int r11, ArrayList<TL_stars.SavedStarGift> r12) {
            if (r12.isEmpty() == false) goto L5;
            return;
        L5:
            GiftsList r0 = getListById(r11);
            if (r0 != null) goto L8;
        L20:
            updateIcon(r11);
            TL_stars.updateStarGiftCollection r2 = new TL_stars.updateStarGiftCollection();
            r2.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r2.collection_id = r11;
            r2.flags |= 2;
            int r3 = r12.size();
            int r4 = 0;
        L21:
            if (r4 >= r3) goto L29;
            TL_stars.SavedStarGift r5 = r12.get(r4);
            r4 = r4 + 1;
            TL_stars.SavedStarGift r52 = r5;
            updateGiftsCollections(r52, r11, false);
            if (r52.msg_id > 0) goto L24;
            if (r52.saved_id != 0) goto L27;
            FileLog.w("can't convert gift to inputgift to add into the collection");
            goto L21
        L27:
            TL_stars.TL_inputSavedStarGiftChat r6 = new TL_stars.TL_inputSavedStarGiftChat();
            r6.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r6.saved_id = r52.saved_id;
            r2.delete_stargift.add(r6);
            goto L21
        L24:
            TL_stars.TL_inputSavedStarGiftUser r62 = new TL_stars.TL_inputSavedStarGiftUser();
            r62.msg_id = r52.msg_id;
            r2.delete_stargift.add(r62);
            goto L21
        L29:
            r2.delete_stargift.size();
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new StarsController$GiftsCollections$$ExternalSyntheticLambda5(this));
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), r0});
            return;
        L8:
            if (r0.gifts.isEmpty() == true) goto L20;
            int r22 = 0;
        L11:
            if (r22 >= r0.gifts.size()) goto L20;
            TL_stars.SavedStarGift r32 = r0.gifts.get(r22);
            int r42 = 0;
        L14:
            if (r42 >= r12.size()) goto L19;
            if (StarsController.eq(r32, r12.get(r42)) == true) goto L17;
            r42 = r42 + 1;
            goto L14
        L17:
            r0.gifts.remove(r22);
            r0.totalCount = Math.max(0, r0.totalCount - 1);
            r22 = r22 - 1;
        L19:
            r22 = r22 + 1;
            goto L11
        }

        public void rename(int r5, String r6) {
            TL_stars.updateStarGiftCollection r0 = new TL_stars.updateStarGiftCollection();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.collection_id = r5;
            r0.flags |= 1;
            r0.title = r6;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
        }

        public void reorder(ArrayList<Integer> r8) {
            HashMap r0 = new HashMap();
            ArrayList<TL_stars.TL_starGiftCollection> r1 = this.collections;
            int r2 = r1.size();
            int r3 = 0;
            int r4 = 0;
        L3:
            if (r4 >= r2) goto L5;
            TL_stars.TL_starGiftCollection r5 = r1.get(r4);
            r4 = r4 + 1;
            TL_stars.TL_starGiftCollection r52 = r5;
            r0.put(Integer.valueOf(r52.collection_id), r52);
            goto L3
        L5:
            ArrayList r12 = new ArrayList();
            int r22 = r8.size();
        L6:
            if (r3 >= r22) goto L10;
            Integer r42 = r8.get(r3);
            r3 = r3 + 1;
            Integer r43 = r42;
            r43.intValue();
            TL_stars.TL_starGiftCollection r44 = (TL_stars.TL_starGiftCollection) r0.get(r43);
            if (r44 == null) goto L6;
            r12.add(r44);
            goto L6
        L10:
            this.collections.clear();
            this.collections.addAll(r12);
            refilterCollections();
        }

        public void sendOrder() {
            TL_stars.reorderStarGiftCollections r0 = new TL_stars.reorderStarGiftCollections();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            ArrayList<TL_stars.TL_starGiftCollection> r1 = this.collections;
            int r2 = r1.size();
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L5;
            TL_stars.TL_starGiftCollection r4 = r1.get(r3);
            r3 = r3 + 1;
            r0.order.add(Integer.valueOf(r4.collection_id));
            goto L3
        L5:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
            refilterCollections();
        }

        public void updateGiftsCollections(TL_stars.SavedStarGift r3, int r4, boolean r5) {
            Iterator<GiftsList> r0 = this.gifts.values().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            r0.next().updateGiftsCollections(r3, r4, r5);
            goto L4
        L6:
            GiftsList r2 = this.all;
            if (r2 == null) goto L11;
            r2.updateGiftsCollections(r3, r4, r5);
            return;
        }

        public void updateGiftsUnsaved(TL_stars.SavedStarGift r3, boolean r4) {
            Iterator<GiftsList> r0 = this.gifts.values().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            r0.next().updateGiftsUnsaved(r3, r4);
            goto L4
        L6:
            GiftsList r2 = this.all;
            if (r2 == null) goto L11;
            r2.updateGiftsUnsaved(r3, r4);
            return;
        }

        public void updateIcon(int r4) {
            GiftsList r0 = getListById(r4);
            TL_stars.TL_starGiftCollection r42 = findById(r4);
            if (r0 == null) goto L16;
            if (r42 != null) goto L7;
            return;
        L7:
            if (r0.gifts.isEmpty() == false) goto L9;
            TL_stars.SavedStarGift r02 = null;
        L10:
            int r1 = r42.flags;
            if (r02 != null) goto L13;
            r42.flags = r1 & (-2);
            r42.icon = null;
        L14:
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftCollectionsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            return;
        L13:
            r42.flags = r1 | 1;
            r42.icon = r02.gift.getDocument();
            goto L14
        L9:
            r02 = r0.gifts.get(0);
            goto L10
        }
    }

    public static class GiftsList implements IGiftsList {
        public Boolean chat_notifications_enabled;
        public int collectionId;
        private long craftingGiftId;
        public final int currentAccount;
        public int currentRequestId;
        public final long dialogId;
        public boolean endReached;
        public ArrayList<TL_stars.SavedStarGift> gifts;
        private int includeFlags;
        public boolean isCollection;
        public String lastOffset;
        public boolean loading;
        public boolean peer_color_available;
        private ArrayList<TL_stars.SavedStarGift> savedPinnedState;
        public boolean shown;
        public boolean sort_by_date;
        public int totalCount;

        public static /* synthetic */ int $r8$lambda$0NrJlscDpM24Kyu4MXSRqJVLA4I(TL_stars.SavedStarGift r0, TL_stars.SavedStarGift r1) {
            return r1.date - r0.date;
        }

        public static /* synthetic */ void $r8$lambda$MadElTAJuHwaYYhGZiGalP5gfbQ(GiftsList r0, TLObject r1) {
            r0.lambda$processCrafting$2(r1);
        }

        /* renamed from: $r8$lambda$e78YbGIubqcy-GaOdw3hmk91zpA, reason: not valid java name */
        public static /* synthetic */ void m19150$r8$lambda$e78YbGIubqcyGaOdw3hmk91zpA(GiftsList r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$processCrafting$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$meQZFqBuIDvvRknmnyTCHDOjfJI(GiftsList r0, int[] r1, TLObject r2, boolean r3) {
            r0.lambda$load$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$o_kNtECv5tdzurkuOWUGKNeeFVE(TLObject r0, TLRPC.TL_error r1) {
        }

        public static /* synthetic */ void $r8$lambda$tMKXcJCxS_WwOpBsxNLl9vdYpQ8(GiftsList r0, int[] r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$load$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ int $r8$lambda$wnJT01tEmt5i9wNF1FR_FImlPC4(TL_stars.SavedStarGift r0, TL_stars.SavedStarGift r1) {
            return r1.date - r0.date;
        }

        public GiftsList(int r2, long r3) {
            this(r2, r3, true);
        }

        public GiftsList(int r3, long r4, boolean r6) {
            this.isCollection = false;
            this.sort_by_date = true;
            this.peer_color_available = false;
            this.includeFlags = 783;
            this.gifts = new ArrayList();
            this.currentRequestId = -1;
            this.craftingGiftId = 0;
            this.currentAccount = r3;
            this.dialogId = r4;
            if (r6 == false) goto L6;
            load();
            return;
        }

        private int getMask(int r1) {
            if ((r1 & 15) == 0) goto L7;
            return 15;
        L7:
            if ((r1 & 768) == 0) goto L9;
            return 768;
        L9:
            return 0;
        }

        private /* synthetic */ void lambda$load$0(int[] r4, TLObject r5, boolean r6) {
            boolean r0 = false;
            if (r4[0] == this.currentRequestId) goto L5;
            return;
        L5:
            this.loading = false;
            this.currentRequestId = -1;
            if ((r5 instanceof TL_stars.TL_payments_savedStarGifts) == false) goto L20;
            TL_stars.TL_payments_savedStarGifts r52 = (TL_stars.TL_payments_savedStarGifts) r5;
            MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
            if (r6 == false) goto L10;
            this.gifts.clear();
        L10:
            this.gifts.addAll(r52.gifts);
            this.lastOffset = r52.next_offset;
            this.totalCount = r52.count;
            if ((r52.flags & 2) == 0) goto L13;
            Boolean r42 = Boolean.valueOf(r52.chat_notifications_enabled);
        L14:
            this.chat_notifications_enabled = r42;
            if (this.gifts.size() <= this.totalCount) goto L17;
        L18:
            r0 = true;
        L19:
            this.endReached = r0;
        L21:
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            return;
        L17:
            if (this.lastOffset != null) goto L19;
        L13:
            r42 = null;
            goto L14
        L20:
            this.endReached = true;
            goto L21
        }

        private /* synthetic */ void lambda$load$1(int[] r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new StarsController$GiftsList$$ExternalSyntheticLambda1(this, r1, r3, r2));
        }

        private /* synthetic */ void lambda$processCrafting$2(TLObject r4) {
            if ((r4 instanceof TL_stars.TL_payments_savedStarGifts) == false) goto L16;
            TL_stars.TL_payments_savedStarGifts r42 = (TL_stars.TL_payments_savedStarGifts) r4;
            int r2 = 0;
            MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r42.chats, false);
            if (r42.gifts.size() <= 0) goto L17;
            TL_stars.SavedStarGift r43 = r42.gifts.get(0);
        L8:
            if (r2 >= this.gifts.size()) goto L12;
            if (this.gifts.get(r2).pinned_to_top == false) goto L12;
            r2 = r2 + 1;
        L12:
            this.gifts.add(r2, r43);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            return;
        L17:
            return;
        }

        private /* synthetic */ void lambda$processCrafting$3(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new StarsController$GiftsList$$ExternalSyntheticLambda6(this, r1));
        }

        public void cancel() {
            if (this.currentRequestId == (-1)) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.currentRequestId, true);
            this.currentRequestId = -1;
        L5:
            this.loading = false;
        }

        public boolean contains(TL_stars.SavedStarGift r5) {
            ArrayList<TL_stars.SavedStarGift> r4 = this.gifts;
            int r0 = r4.size();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L8;
            TL_stars.SavedStarGift r3 = r4.get(r2);
            r2 = r2 + 1;
            if (StarsController.eq(r3, r5) == false) goto L3;
            return true;
        L8:
            return false;
        }

        public boolean eq(ArrayList<TL_stars.SavedStarGift> r5, ArrayList<TL_stars.SavedStarGift> r6) {
            if (r5 != null) goto L7;
            if (r6 != null) goto L7;
            return true;
        L7:
            if (r5 == null) goto L21;
            if (r6 == null) goto L21;
            if (r5.size() == r6.size()) goto L13;
            return false;
        L13:
            int r1 = 0;
        L15:
            if (r1 >= r5.size()) goto L20;
            if (r5.get(r1) != r6.get(r1)) goto L18;
            r1 = r1 + 1;
            goto L15
        L18:
            return false;
        L20:
            return true;
        L21:
            return false;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public int findGiftToUpgrade(int r4) {
            if (StarGiftSheet.isMineWithActions(this.currentAccount, this.dialogId) == true) goto L5;
            return -1;
        L5:
            int r0 = r4 + 1;
        L7:
            if (r0 >= this.gifts.size()) goto L12;
            if (this.gifts.get(r0).can_upgrade == true) goto L10;
            r0 = r0 + 1;
            goto L7
        L10:
            return r0;
        L12:
            int r42 = r4 - 1;
        L13:
            if (r42 < 0) goto L18;
            if (this.gifts.get(r42).can_upgrade == true) goto L16;
            r42 = r42 - 1;
            goto L13
        L16:
            return r42;
        L18:
            return -1;
        }

        public void forCrafting(long r1) {
            this.craftingGiftId = r1;
        }

        public void forceTypeIncludeFlag(int r3, boolean r4) {
            int r0 = getMask(r3);
            int r1 = this.includeFlags;
            int r32 = r3 | ((~r0) & r1);
            if (r1 == r32) goto L8;
            this.includeFlags = r32;
            if (r4 == false) goto L9;
            invalidate(true);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public Object get(int r2) {
            if (r2 >= 0) goto L4;
            return null;
        L4:
            if (r2 < this.gifts.size()) goto L7;
            return null;
        L7:
            return this.gifts.get(r2);
        }

        public TL_stars.InputSavedStarGift getInput(TL_stars.SavedStarGift r5) {
            if (r5 != null) goto L6;
            return null;
        L6:
            if ((r5.flags & 8) == 0) goto L9;
            TL_stars.TL_inputSavedStarGiftUser r4 = new TL_stars.TL_inputSavedStarGiftUser();
            r4.msg_id = r5.msg_id;
            return r4;
        L9:
            TL_stars.TL_inputSavedStarGiftChat r0 = new TL_stars.TL_inputSavedStarGiftChat();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.saved_id = r5.saved_id;
            return r0;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public int getLoadedCount() {
            return this.gifts.size();
        }

        public ArrayList<TL_stars.SavedStarGift> getPinned() {
            ArrayList<TL_stars.SavedStarGift> r0 = new ArrayList();
            int r1 = 0;
        L4:
            if (r1 >= this.gifts.size()) goto L11;
            TL_stars.SavedStarGift r2 = this.gifts.get(r1);
            if (r2.pinned_to_top == false) goto L10;
            if (r2.unsaved == true) goto L10;
            r0.add(r2);
        L10:
            r1 = r1 + 1;
            goto L4
        L11:
            return r0;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public int getTotalCount() {
            return this.totalCount;
        }

        public boolean hasFilters() {
            if (this.sort_by_date == true) goto L5;
            return true;
        L5:
            if (this.includeFlags != 783) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public int indexOf(Object r1) {
            return this.gifts.indexOf(r1);
        }

        public void invalidate(boolean r5) {
            if (this.currentRequestId == (-1)) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.currentRequestId, true);
            this.currentRequestId = -1;
        L5:
            this.loading = false;
            this.gifts.clear();
            this.lastOffset = null;
            this.endReached = false;
            if (r5 == false) goto L8;
        L11:
            load();
            return;
        L8:
            if (this.shown == true) goto L11;
        }

        public boolean isInclude_displayed() {
            return TLObject.hasFlag(this.includeFlags, 256);
        }

        public boolean isInclude_hidden() {
            return TLObject.hasFlag(this.includeFlags, 512);
        }

        public boolean isInclude_limited() {
            return TLObject.hasFlag(this.includeFlags, 2);
        }

        public boolean isInclude_unique() {
            return TLObject.hasFlag(this.includeFlags, 8);
        }

        public boolean isInclude_unlimited() {
            return TLObject.hasFlag(this.includeFlags, 1);
        }

        public boolean isInclude_upgradable() {
            return TLObject.hasFlag(this.includeFlags, 4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.endReached == false) goto L8;
            return;
        L8:
            if (this.lastOffset != null) goto L10;
            boolean r0 = true;
        L11:
            this.loading = true;
            long r3 = this.craftingGiftId;
            int r4 = 30;
            String r8 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r3 == 0) goto L21;
            TL_stars.getCraftStarGifts r32 = new TL_stars.getCraftStarGifts();
            r32.gift_id = this.craftingGiftId;
            if (r0 == true) goto L17;
            r8 = this.lastOffset;
        L17:
            r32.offset = r8;
            if (r0 == false) goto L20;
            r4 = 15;
        L20:
            r32.limit = r4;
            TL_stars.getSavedStarGifts r33 = r32;
        L34:
            int r02 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r33, new StarsController$GiftsList$$ExternalSyntheticLambda0(this, r2, r0));
            this.currentRequestId = r02;
            int[] r2 = {r02};
            return;
        L21:
            TL_stars.getSavedStarGifts r34 = new TL_stars.getSavedStarGifts();
            r34.sort_by_value = !this.sort_by_date;
            r34.exclude_unupgradable = !isInclude_limited();
            r34.exclude_upgradable = !isInclude_upgradable();
            r34.exclude_unlimited = !isInclude_unlimited();
            r34.exclude_unique = !isInclude_unique();
            r34.exclude_saved = !isInclude_displayed();
            r34.exclude_unsaved = !isInclude_hidden();
            r34.peer_color_available = this.peer_color_available;
            if (this.dialogId != 0) goto L24;
            r34.peer = new TLRPC.TL_inputPeerSelf();
        L25:
            if (r0 == true) goto L28;
            r8 = this.lastOffset;
        L28:
            r34.offset = r8;
            if (r0 == false) goto L31;
            r4 = Math.max(MessagesController.getInstance(this.currentAccount).stargiftsPinnedToTopLimit, 15);
        L31:
            r34.limit = r4;
            r33 = r34;
            if (this.isCollection == false) goto L34;
            r34.flags |= 64;
            r34.collection_id = this.collectionId;
            r33 = r34;
            goto L34
        L24:
            r34.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            goto L25
        L10:
            r0 = false;
            goto L11
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public void notifyUpdate() {
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
        }

        public void processCrafting(ArrayList<TL_stars.StarGift> r11, TL_stars.StarGift r12) {
            if (r11 != null) goto L4;
        L18:
            if (r12 == null) goto L28;
            TL_stars.getSavedStarGift r112 = new TL_stars.getSavedStarGift();
            TL_stars.TL_inputSavedStarGiftSlug r0 = new TL_stars.TL_inputSavedStarGiftSlug();
            r0.slug = r12.slug;
            r112.stargift.add(r0);
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r112, new StarsController$GiftsList$$ExternalSyntheticLambda4(this));
            return;
        L28:
            return;
        L4:
            if (r11.isEmpty() == true) goto L18;
            int r02 = r11.size();
            boolean r2 = false;
            int r3 = 0;
        L6:
            if (r3 >= r02) goto L16;
            TL_stars.StarGift r4 = r11.get(r3);
            r3 = r3 + 1;
            TL_stars.StarGift r42 = r4;
            int r5 = 0;
        L9:
            if (r5 >= this.gifts.size()) goto L6;
            TL_stars.StarGift r6 = this.gifts.get(r5).gift;
            if (r6 == null) goto L15;
            if (r6.id != r42.id) goto L15;
            this.gifts.remove(r5);
            this.totalCount = Math.max(0, this.totalCount - 1);
            r2 = true;
        L15:
            r5 = r5 + 1;
            goto L9
        L16:
            if (r2 == false) goto L18;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            goto L18
        }

        public void reorder(int r3, int r4) {
            int r32 = Utilities.clamp(r3, this.gifts.size() - 1, 0);
            if (r32 >= 0) goto L5;
            return;
        L5:
            if (r32 >= this.gifts.size()) goto L16;
            TL_stars.SavedStarGift r33 = this.gifts.remove(r32);
            int r42 = Utilities.clamp(r4, this.gifts.size() - 1, 0);
            if (r42 >= 0) goto L10;
            return;
        L10:
            if (r42 >= this.gifts.size()) goto L17;
            this.gifts.add(r42, r33);
            return;
        L17:
            return;
        }

        public void reorderDone() {
            ArrayList<TL_stars.SavedStarGift> r0 = this.savedPinnedState;
            if (r0 != null) goto L5;
        L9:
            this.savedPinnedState = null;
            return;
        L5:
            if (eq(r0, getPinned()) == true) goto L9;
            sendPinnedOrder();
            this.savedPinnedState = null;
        }

        public void reorderPinned(int r2, int r3) {
            if (this.savedPinnedState != null) goto L5;
            this.savedPinnedState = getPinned();
        L5:
            reorder(r2, r3);
        }

        public void resetFilters() {
            if (hasFilters() == true) goto L5;
            return;
        L5:
            this.includeFlags = 783;
            this.sort_by_date = true;
            invalidate(true);
        }

        public void sendPinnedOrder() {
            int r2 = 0;
            if (this.isCollection == false) goto L9;
            TL_stars.updateStarGiftCollection r0 = new TL_stars.updateStarGiftCollection();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.collection_id = this.collectionId;
            r0.flags |= 8;
            ArrayList<TL_stars.SavedStarGift> r3 = this.gifts;
            int r4 = r3.size();
        L5:
            if (r2 >= r4) goto L7;
            TL_stars.SavedStarGift r5 = r3.get(r2);
            r2 = r2 + 1;
            r0.order.add(getInput(r5));
            goto L5
        L7:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null, 64);
            return;
        L9:
            TL_stars.toggleStarGiftsPinnedToTop r02 = new TL_stars.toggleStarGiftsPinnedToTop();
            r02.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            ArrayList<TL_stars.SavedStarGift> r32 = getPinned();
            int r42 = r32.size();
        L10:
            if (r2 >= r42) goto L12;
            TL_stars.SavedStarGift r52 = r32.get(r2);
            r2 = r2 + 1;
            r02.stargift.add(getInput(r52));
            goto L10
        L12:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new StarsController$GiftsList$$ExternalSyntheticLambda2(), 64);
        }

        public void setCollectionId(int r2) {
            this.isCollection = true;
            this.collectionId = r2;
        }

        public void setFilters(int r1) {
            this.includeFlags = r1;
            this.sort_by_date = true;
            invalidate(true);
        }

        public void setPinned(ArrayList<TL_stars.SavedStarGift> r4) {
            this.gifts.removeAll(r4);
            if (this.sort_by_date == true) goto L5;
        L7:
            this.gifts.addAll(0, r4);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            sendPinnedOrder();
            return;
        L5:
            if (this.isCollection == true) goto L7;
            Collections.sort(this.gifts, new StarsController$GiftsList$$ExternalSyntheticLambda5());
            goto L7
        }

        public boolean togglePinned(TL_stars.SavedStarGift r6, boolean r7, boolean r8) {
            if (r6 != null) goto L5;
            return false;
        L5:
            ArrayList<TL_stars.SavedStarGift> r1 = getPinned();
            if (r1.contains(r6) == false) goto L10;
            if (r7 == false) goto L9;
            return false;
        L9:
            r1.remove(r6);
            boolean r3 = false;
        L23:
            r6.pinned_to_top = r7;
            this.gifts.removeAll(r1);
            if (this.sort_by_date == true) goto L26;
        L28:
            this.gifts.addAll(0, r1);
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            sendPinnedOrder();
            return r3;
        L26:
            if (this.isCollection == true) goto L28;
            Collections.sort(this.gifts, new StarsController$GiftsList$$ExternalSyntheticLambda3());
            goto L28
        L10:
            if (r7 == true) goto L12;
            return false;
        L12:
            r3 = true;
            if ((r1.size() + 1) <= MessagesController.getInstance(this.currentAccount).stargiftsPinnedToTopLimit) goto L21;
            if (r8 == true) goto L16;
            return true;
        L16:
            if (r1.size() <= 0) goto L22;
            if ((r1.size() + 1) <= MessagesController.getInstance(this.currentAccount).stargiftsPinnedToTopLimit) goto L22;
            r1.remove(r1.size() - 1).pinned_to_top = false;
        L22:
            r1.add(r6);
            goto L23
        L21:
            r3 = false;
            goto L22
        }

        public void toggleTypeIncludeFlag(int r5) {
            int r0 = getMask(r5);
            int r1 = TLObject.setFlag(this.includeFlags & r0, r5, !TLObject.hasFlag(r1, r5));
            if (r1 != 0) goto L5;
            r1 = r0 & (~r5);
        L5:
            int r52 = this.includeFlags;
            int r02 = ((~r0) & r52) | r1;
            if (r52 == r02) goto L9;
            this.includeFlags = r02;
            invalidate(true);
            return;
        }

        public void updateGiftsCollections(TL_stars.SavedStarGift r6, int r7, boolean r8) {
            ArrayList<TL_stars.SavedStarGift> r5 = this.gifts;
            int r0 = r5.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L11;
            TL_stars.SavedStarGift r2 = r5.get(r1);
            r1 = r1 + 1;
            TL_stars.SavedStarGift r22 = r2;
            if (StarsController.eq(r22, r6) == false) goto L3;
            if (r8 == true) goto L8;
            r22.collection_id.remove(Integer.valueOf(r7));
            goto L3
        L8:
            if (r22.collection_id.contains(Integer.valueOf(r7)) == true) goto L3;
            r22.collection_id.add(Integer.valueOf(r7));
            goto L3
        }

        public void updateGiftsUnsaved(TL_stars.SavedStarGift r7, boolean r8) {
            ArrayList<TL_stars.SavedStarGift> r0 = this.gifts;
            int r1 = r0.size();
            boolean r2 = false;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L9;
            TL_stars.SavedStarGift r4 = r0.get(r3);
            r3 = r3 + 1;
            TL_stars.SavedStarGift r42 = r4;
            if (StarsController.eq(r42, r7) == false) goto L3;
            if (r42.unsaved == r8) goto L3;
            r42.unsaved = r8;
            r2 = true;
            goto L3
        L9:
            if (r2 == false) goto L19;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starUserGiftsLoaded, new Object[]{Long.valueOf(this.dialogId), this});
            return;
        }
    }

    public interface IGiftsList {
        int findGiftToUpgrade(int r1);

        Object get(int r1);

        int getLoadedCount();

        int getTotalCount();

        int indexOf(Object r1);

        void load();

        void notifyUpdate();
    }

    /* loaded from: classes7.dex */
    public static class MessageId {
        public long did;
        public int mid;

        private MessageId(long r1, int r3) {
            this.did = r1;
            this.mid = r3;
        }

        public static MessageId from(long r1, int r3) {
            return new MessageId(r1, r3);
        }

        public static MessageId from(MessageObject r3) {
            if (r3 != null) goto L5;
            return null;
        L5:
            TLRPC.Message r0 = r3.messageOwner;
            if (r0 == null) goto L16;
            if (r0.isThreadMessage == true) goto L12;
            if (r3.isForwardedChannelPost() == false) goto L16;
        L12:
            if (r3.messageOwner.fwd_from == null) goto L16;
            return new MessageId(r3.getFromChatId(), r3.messageOwner.fwd_from.saved_from_msg_id);
        L16:
            return new MessageId(r3.getDialogId(), r3.getId());
        }

        public boolean equals(Object r7) {
            if ((r7 instanceof MessageId) == false) goto L10;
            MessageId r72 = (MessageId) r7;
            if (r72.did != this.did) goto L10;
            if (r72.mid != this.mid) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.did), Integer.valueOf(this.mid)});
        }
    }

    /* loaded from: classes7.dex */
    public class PaidMessagesToast {
        public final Bulletin bulletin;
        public final Bulletin.UndoButton bulletinButton;
        public final Bulletin.TwoLineAnimatedLottieLayout bulletinLayout;
        public final long dialogId;
        public final BaseFragment fragment;
        public final HashSet<MessageObject> messages;
        private final Runnable sendRunnable;
        private boolean sent;
        public long startTime;
        final /* synthetic */ StarsController this$0;
        public final Bulletin.TimerView timerView;
        public int totalMessagesCount;
        public final ArrayList<Runnable> totalSendListeners;
        public long totalStars;
        public Utilities.Callback<HashSet<MessageObject>> undoListener;
        public boolean undoRunning;
        private boolean undone;

        /* renamed from: -$$Nest$fgetsent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19151$$Nest$fgetsent(PaidMessagesToast r0) {
            return r0.sent;
        }

        /* renamed from: -$$Nest$fgetundone, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19152$$Nest$fgetundone(PaidMessagesToast r0) {
            return r0.undone;
        }

        public PaidMessagesToast(StarsController r19, BaseFragment r20, long r21) {
            this.this$0 = r19;
            this.totalSendListeners = new ArrayList();
            this.messages = new HashSet();
            this.startTime = System.currentTimeMillis();
            this.undoRunning = true;
            StarsController$PaidMessagesToast$$ExternalSyntheticLambda0 r3 = new StarsController$PaidMessagesToast$$ExternalSyntheticLambda0(this);
            this.sendRunnable = r3;
            this.fragment = r20;
            this.dialogId = r21;
            Context r1 = r19.getContext(r20);
            Bulletin.TwoLineAnimatedLottieLayout r5 = new Bulletin.TwoLineAnimatedLottieLayout(r1, r20.getResourceProvider());
            this.bulletinLayout = r5;
            r5.setAnimation(R.raw.stars_topup, new String[0]);
            Bulletin.TimerView r6 = new Bulletin.TimerView(r1, r20.getResourceProvider());
            this.timerView = r6;
            r6.timeLeft = 3000;
            r6.setColor(Theme.getColor(Theme.key_undo_cancelColor, r20.getResourceProvider()));
            Bulletin.UndoButton r10 = new Bulletin.UndoButton(r1, true, false, r20.getResourceProvider());
            this.bulletinButton = r10;
            r10.setText(LocaleController.getString(R.string.StarsSentUndo));
            r10.setUndoAction(new StarsController$PaidMessagesToast$$ExternalSyntheticLambda1(this));
            r10.addView(r6, LayoutHelper.createFrame(20, 20.0f, 21, 0.0f, 0.0f, 12.0f, 0.0f));
            r10.undoTextView.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(30.0f), AndroidUtilities.dp(8.0f));
            r5.setButton(r10);
            Bulletin r12 = BulletinFactory.of(r20).create(r5, -1);
            this.bulletin = r12;
            r12.hideAfterBottomSheet = false;
            r12.show(true);
            r12.setOnHideListener(new StarsController$PaidMessagesToast$$ExternalSyntheticLambda0(this));
            AndroidUtilities.cancelRunOnUIThread(r3);
            AndroidUtilities.runOnUIThread(r3, 3000);
        }

        public CharSequence getSubtitle() {
            return AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("PaidMessageSentSubtitle", Math.max(0, (int) this.totalStars)));
        }

        public CharSequence getTitle() {
            int r2 = this.totalMessagesCount;
            if (r2 != 1) goto L7;
            return LocaleController.getString(R.string.PaidMessageSentTitleOne);
        L7:
            return LocaleController.formatPluralString("PaidMessageSentTitle", r2, new Object[0]);
        }

        public boolean isUndoRunning() {
            if (this.totalMessagesCount > 0) goto L5;
            return false;
        L5:
            if (this.undoRunning == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isVisible() {
            if (this.undone == false) goto L5;
            return false;
        L5:
            if (this.sent == true) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean pop(int r7) {
            if (this.undone == false) goto L5;
        L27:
            return false;
        L5:
            if (this.sent == true) goto L27;
            Iterator<MessageObject> r0 = this.messages.iterator();
        L9:
            if (r0.hasNext() == false) goto L13;
            MessageObject r2 = r0.next();
            if (r2.getId() != r7) goto L9;
            this.messages.remove(r2);
        L15:
            if (this.messages.isEmpty() == false) goto L18;
            this.undone = true;
            this.bulletin.hide();
            return true;
        L18:
            this.totalMessagesCount--;
            if (r2 == null) goto L23;
            TLRPC.Message r72 = r2.messageOwner;
            if (r72 == null) goto L23;
            this.totalStars -= r72.paid_message_stars;
        L23:
            Bulletin.TimerView r73 = this.timerView;
            if (r73 == null) goto L26;
            r73.timeLeft = 3000;
            AndroidUtilities.cancelRunOnUIThread(this.sendRunnable);
            AndroidUtilities.runOnUIThread(this.sendRunnable, 3000);
        L26:
            this.bulletinLayout.titleTextView.setText(getTitle());
            this.bulletinLayout.subtitleTextView.setText(getSubtitle());
            this.bulletinLayout.imageView.playAnimation();
            goto L27
        L13:
            r2 = null;
            goto L15
        }

        public boolean push(MessageObject r6, long r7, Utilities.Callback<HashSet<MessageObject>> r9, Runnable r10, boolean r11) {
            if (this.undone == false) goto L5;
        L24:
            return false;
        L5:
            if (this.sent == true) goto L24;
            this.totalMessagesCount++;
            this.messages.add(r6);
            this.totalStars += r7;
            this.undoListener = r9;
            if (r10 == null) goto L11;
            this.totalSendListeners.add(r10);
        L11:
            if (this.undoRunning == false) goto L17;
            if (r11 == true) goto L17;
            this.undoRunning = false;
            AndroidUtilities.cancelRunOnUIThread(this.sendRunnable);
            this.bulletin.setDuration(5000);
            this.bulletin.setCanHide(true);
            long r62 = System.currentTimeMillis() - this.startTime;
            Bulletin.UndoButton r72 = this.bulletinButton;
            if (r62 <= 500) goto L16;
            r72.animate().alpha(0.0f).scaleX(0.3f).scaleY(0.3f).start();
            goto L17
        L16:
            r72.setAlpha(0.0f);
            this.bulletinButton.setVisibility(8);
        L17:
            Bulletin.TimerView r63 = this.timerView;
            if (r63 != null) goto L20;
        L22:
            this.bulletinLayout.titleTextView.setText(getTitle());
            this.bulletinLayout.subtitleTextView.setText(getSubtitle());
            this.bulletinLayout.imageView.playAnimation();
            return true;
        L20:
            if (this.undoRunning == false) goto L22;
            r63.timeLeft = 3000;
            AndroidUtilities.cancelRunOnUIThread(this.sendRunnable);
            AndroidUtilities.runOnUIThread(this.sendRunnable, 3000);
            goto L22
        }

        public void send() {
            if (this.undone == false) goto L5;
            return;
        L5:
            if (this.sent == true) goto L17;
            this.sent = true;
            ArrayList<Runnable> r0 = this.totalSendListeners;
            int r1 = r0.size();
            int r2 = 0;
        L8:
            if (r2 >= r1) goto L11;
            Runnable r3 = r0.get(r2);
            r2 = r2 + 1;
            r3.run();
            goto L8
        L11:
            if (this.bulletinButton == null) goto L16;
            this.bulletin.hide();
            return;
        L16:
            return;
        }

        public void undo() {
            if (this.undone == false) goto L5;
            return;
        L5:
            if (this.sent == false) goto L7;
            return;
        L7:
            if (this.undoRunning == false) goto L19;
            this.undone = true;
            Utilities.Callback<HashSet<MessageObject>> r0 = this.undoListener;
            if (r0 == null) goto L13;
            r0.run(this.messages);
        L13:
            if (this.bulletinButton == null) goto L18;
            this.bulletin.hide();
            return;
        L18:
            return;
        }
    }

    /* loaded from: classes7.dex */
    public class PendingPaidReactions {
        public long amount;
        public boolean applied;
        public Bulletin bulletin;
        public Bulletin.UndoButton bulletinButton;
        public Bulletin.TwoLineAnimatedLottieLayout bulletinLayout;
        public final Runnable cancelRunnable;
        public boolean cancelled;
        public ChatActivity chatActivity;
        public final Runnable closeRunnable;
        public boolean committed;
        public long lastTime;
        public MessageId message;
        public MessageObject messageObject;
        public long not_added;
        public StarReactionsOverlay overlay;
        public Long peer;
        public boolean shownBulletin;
        final /* synthetic */ StarsController this$0;
        public Bulletin.TimerView timerView;
        public boolean wasChosen;

        public static /* synthetic */ void $r8$lambda$22a4Rcq8lm6EA8FHishZoRqqwSI(PendingPaidReactions r0, long r1) {
            r0.lambda$commit$2(r1);
        }

        /* renamed from: $r8$lambda$keWct6GSWiI-WiowXyY1ptwxujs, reason: not valid java name */
        public static /* synthetic */ void m19153$r8$lambda$keWct6GSWiIWiowXyY1ptwxujs(PendingPaidReactions r0, TLObject r1, MessagesController r2, TLRPC.TL_error r3, long r4) {
            r0.lambda$commit$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$kijp3R4HHE7Ta17aLoMBIisboSE(MessagesController r1, TLObject r2) {
            r1.processUpdates((TLRPC.Updates) r2, false);
        }

        public static /* synthetic */ void $r8$lambda$miHB1V9gQ71dvjpzYpRAkcdbcNs(PendingPaidReactions r0, long r1) {
            r0.lambda$commit$0(r1);
        }

        /* renamed from: $r8$lambda$opaa03-BD6n4U0m5NpxpofrLgxI, reason: not valid java name */
        public static /* synthetic */ void m19154$r8$lambda$opaa03BD6n4U0m5NpxpofrLgxI(PendingPaidReactions r0, MessagesController r1, long r2, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$commit$4(r1, r2, r4, r5);
        }

        public PendingPaidReactions(StarsController r17, MessageId r18, MessageObject r19, ChatActivity r20, long r21, boolean r23) {
            this.this$0 = r17;
            this.committed = false;
            this.cancelled = false;
            this.peer = null;
            StarsController$PendingPaidReactions$$ExternalSyntheticLambda0 r4 = new StarsController$PendingPaidReactions$$ExternalSyntheticLambda0(this);
            this.closeRunnable = r4;
            this.cancelRunnable = new StarsController$PendingPaidReactions$$ExternalSyntheticLambda1(this);
            this.message = r18;
            this.messageObject = r19;
            this.chatActivity = r20;
            Context r1 = r17.getContext(r20);
            Bulletin.TwoLineAnimatedLottieLayout r6 = new Bulletin.TwoLineAnimatedLottieLayout(r1, r20.themeDelegate);
            this.bulletinLayout = r6;
            r6.setAnimation(R.raw.stars_topup, new String[0]);
            this.bulletinLayout.titleTextView.setText(getToastTitle());
            Bulletin.UndoButton r62 = new Bulletin.UndoButton(r1, true, false, r20.themeDelegate);
            this.bulletinButton = r62;
            r62.setText(LocaleController.getString(R.string.StarsSentUndo));
            this.bulletinButton.setUndoAction(new StarsController$PendingPaidReactions$$ExternalSyntheticLambda1(this));
            Bulletin.TimerView r63 = new Bulletin.TimerView(r1, r20.themeDelegate);
            this.timerView = r63;
            r63.timeLeft = 5000;
            r63.setColor(Theme.getColor(Theme.key_undo_cancelColor, r20.themeDelegate));
            this.bulletinButton.addView(this.timerView, LayoutHelper.createFrame(20, 20.0f, 21, 0.0f, 0.0f, 12.0f, 0.0f));
            this.bulletinButton.undoTextView.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(30.0f), AndroidUtilities.dp(8.0f));
            this.bulletinLayout.setButton(this.bulletinButton);
            Bulletin r12 = BulletinFactory.of(r20).create(this.bulletinLayout, -1);
            this.bulletin = r12;
            r12.hideAfterBottomSheet = false;
            if (r23 == false) goto L5;
            r12.show(true);
            this.shownBulletin = true;
        L5:
            this.bulletin.setOnHideListener(r4);
            this.amount = 0;
            this.lastTime = System.currentTimeMillis();
            this.wasChosen = r19.isPaidReactionChosen();
        }

        private /* synthetic */ void lambda$commit$0(long r9) {
            this.this$0.sendPaidReaction(this.messageObject, this.chatActivity, r9, true, true, this.peer);
        }

        private /* synthetic */ void lambda$commit$2(long r9) {
            this.this$0.sendPaidReaction(this.messageObject, this.chatActivity, r9, true, true, this.peer);
        }

        private /* synthetic */ void lambda$commit$3(TLObject r11, MessagesController r12, TLRPC.TL_error r13, long r14) {
            if (r11 == null) goto L5;
            Utilities.stageQueue.postRunnable(new StarsController$PendingPaidReactions$$ExternalSyntheticLambda5(r12, r11));
            return;
        L5:
            if (r13 == null) goto L24;
            this.messageObject.addPaidReactions((int) (-this.amount), this.wasChosen, getPeerId());
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.didUpdateReactions, new Object[]{Long.valueOf(this.messageObject.getDialogId()), Integer.valueOf(this.messageObject.getId()), this.messageObject.messageOwner.reactions});
            if ("BALANCE_TOO_LOW".equals(r13.text) == false) goto L22;
            long r112 = this.message.did;
            ChatActivity r122 = this.chatActivity;
            if (r112 < 0) goto L12;
            String r113 = UserObject.getForcedFirstName(r122.getMessagesController().getUser(Long.valueOf(this.message.did)));
        L11:
            String r6 = r113;
            Context r114 = this.chatActivity.getContext();
            if (r114 != null) goto L19;
            r114 = LaunchActivity.instance;
        L19:
            if (r114 != null) goto L21;
            r114 = ApplicationLoader.applicationContext;
        L21:
            new StarsIntroActivity.StarsNeededSheet(r114, this.chatActivity.getResourceProvider(), r14, 5, r6, new StarsController$PendingPaidReactions$$ExternalSyntheticLambda6(this, r14), 0).show();
            goto L22
        L12:
            TLRPC.Chat r115 = r122.getMessagesController().getChat(Long.valueOf(-this.message.did));
            if (r115 != null) goto L15;
            r113 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L11
        L15:
            r113 = r115.title;
        L22:
            this.this$0.invalidateTransactions(false);
            this.this$0.invalidateBalance();
            return;
        }

        private /* synthetic */ void lambda$commit$4(MessagesController r8, long r9, TLObject r11, TLRPC.TL_error r12) {
            AndroidUtilities.runOnUIThread(new StarsController$PendingPaidReactions$$ExternalSyntheticLambda4(this, r11, r8, r12, r9));
        }

        public void add(long r7, boolean r9) {
            if (this.committed == true) goto L19;
            if (this.cancelled == true) goto L19;
            this.amount += r7;
            this.lastTime = System.currentTimeMillis();
            this.bulletinLayout.subtitleTextView.cancelAnimation();
            this.bulletinLayout.subtitleTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsSentText", (int) this.amount, new Object[0])), true);
            if (this.shownBulletin == false) goto L10;
            this.timerView.timeLeft = 5000;
            AndroidUtilities.cancelRunOnUIThread(this.closeRunnable);
            AndroidUtilities.runOnUIThread(this.closeRunnable, 5000);
        L10:
            if (r9 == false) goto L12;
            this.applied = true;
            this.messageObject.addPaidReactions((int) r7, true, getPeerId());
            StarsController r92 = this.this$0;
            r92.minus += r7;
            NotificationCenter.getInstance(r92.currentAccount).postNotificationName(NotificationCenter.didUpdateReactions, new Object[]{Long.valueOf(this.messageObject.getDialogId()), Integer.valueOf(this.messageObject.getId()), this.messageObject.messageOwner.reactions});
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.starBalanceUpdated, new Object[0]);
        L16:
            this.bulletinLayout.titleTextView.setText(getToastTitle());
            return;
        L12:
            this.applied = false;
            if (this.messageObject.ensurePaidReactionsExist(true) == false) goto L15;
            this.not_added--;
        L15:
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.didUpdateReactions, new Object[]{Long.valueOf(this.messageObject.getDialogId()), Integer.valueOf(this.messageObject.getId()), this.messageObject.messageOwner.reactions});
            this.not_added += r7;
        L19:
            if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L21;
            return;
        L21:
            GlShader$$ExternalSyntheticBUOutline1.m("adding more amount to committed reactions");
        }

        public void apply() {
            if (this.applied == true) goto L6;
            this.applied = true;
            this.messageObject.addPaidReactions((int) this.not_added, true, getPeerId());
            StarsController r0 = this.this$0;
            r0.minus += this.not_added;
            NotificationCenter.getInstance(r0.currentAccount).postNotificationName(NotificationCenter.starBalanceUpdated, new Object[0]);
            this.not_added = 0;
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.didUpdateReactions, new Object[]{Long.valueOf(this.messageObject.getDialogId()), Integer.valueOf(this.messageObject.getId()), this.messageObject.messageOwner.reactions});
        L6:
            if (this.shownBulletin == true) goto L8;
            this.shownBulletin = true;
            this.timerView.timeLeft = 5000;
            AndroidUtilities.cancelRunOnUIThread(this.closeRunnable);
            AndroidUtilities.runOnUIThread(this.closeRunnable, 5000);
            this.bulletin.show(true);
            this.bulletin.setOnHideListener(this.closeRunnable);
        L8:
            this.bulletinLayout.titleTextView.setText(getToastTitle());
        }

        public void cancel() {
            AndroidUtilities.cancelRunOnUIThread(this.closeRunnable);
            this.cancelled = true;
            this.bulletin.hide();
            StarReactionsOverlay r0 = this.overlay;
            if (r0 == null) goto L5;
            r0.hide();
        L5:
            this.messageObject.addPaidReactions((int) (-this.amount), this.wasChosen, getPeerId());
            StarsController r02 = this.this$0;
            r02.minus -= this.amount;
            NotificationCenter.getInstance(r02.currentAccount).postNotificationName(NotificationCenter.starBalanceUpdated, new Object[0]);
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.didUpdateReactions, new Object[]{Long.valueOf(this.messageObject.getDialogId()), Integer.valueOf(this.messageObject.getId()), this.messageObject.messageOwner.reactions});
            StarsController r03 = this.this$0;
            if (r03.currentPendingReactions != this) goto L9;
            r03.currentPendingReactions = null;
            return;
        }

        public void close() {
            AndroidUtilities.cancelRunOnUIThread(this.closeRunnable);
            if (this.applied == false) goto L5;
            commit();
        L6:
            this.bulletin.hide();
            StarReactionsOverlay r0 = this.overlay;
            if (r0 != null) goto L9;
        L11:
            StarsController r02 = this.this$0;
            if (r02.currentPendingReactions != this) goto L15;
            r02.currentPendingReactions = null;
            return;
        L15:
            return;
        L9:
            if (r0.isShowing(this.messageObject) == false) goto L11;
            this.overlay.hide();
            goto L11
        L5:
            this.cancelled = true;
            this.messageObject.addPaidReactions((int) (-this.amount), this.wasChosen, getPeerId());
            StarsController r03 = this.this$0;
            r03.minus -= this.amount;
            NotificationCenter.getInstance(r03.currentAccount).postNotificationName(NotificationCenter.starBalanceUpdated, new Object[0]);
            goto L6
        }

        public void commit() {
            if (this.committed == false) goto L5;
            return;
        L5:
            if (this.cancelled == true) goto L39;
            StarsController r0 = StarsController.getInstance(this.this$0.currentAccount);
            MessagesController r1 = MessagesController.getInstance(this.this$0.currentAccount);
            ConnectionsManager r2 = ConnectionsManager.getInstance(this.this$0.currentAccount);
            long r6 = this.amount;
            if (r0.balanceAvailable() == true) goto L10;
        L26:
            this.committed = true;
            TLRPC.TL_messages_sendPaidReaction r02 = new TLRPC.TL_messages_sendPaidReaction();
            r02.peer = r1.getInputPeer(this.message.did);
            r02.msg_id = this.message.mid;
            r02.random_id = (Utilities.random.nextLong() & 4294967295L) | (r2.getCurrentTime() << 32);
            r02.count = (int) this.amount;
            r02.flags |= 1;
            long r8 = getPeerId();
            if (r8 != 0) goto L29;
        L35:
            r02.privacy = new TL_stars.paidReactionPrivacyDefault();
        L36:
            this.this$0.invalidateBalance();
            r2.sendRequest(r02, new StarsController$PendingPaidReactions$$ExternalSyntheticLambda3(this, r1, r6));
            return;
        L29:
            if (r8 == UserConfig.getInstance(this.this$0.currentAccount).getClientUserId()) goto L35;
            if (r8 != UserObject.ANONYMOUS) goto L34;
            r02.privacy = new TL_stars.paidReactionPrivacyAnonymous();
            goto L36
        L34:
            TL_stars.paidReactionPrivacyPeer r3 = new TL_stars.paidReactionPrivacyPeer();
            r02.privacy = r3;
            r3.peer = r1.getInputPeer(r8);
            goto L36
        L10:
            if (r0.getBalance(false) >= r6) goto L26;
            this.cancelled = true;
            this.messageObject.addPaidReactions((int) (-this.amount), this.wasChosen, getPeerId());
            StarsController r03 = this.this$0;
            r03.minus = 0;
            NotificationCenter.getInstance(r03.currentAccount).postNotificationName(NotificationCenter.starBalanceUpdated, new Object[0]);
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.didUpdateReactions, new Object[]{Long.valueOf(this.messageObject.getDialogId()), Integer.valueOf(this.messageObject.getId()), this.messageObject.messageOwner.reactions});
            long r04 = this.message.did;
            ChatActivity r12 = this.chatActivity;
            if (r04 < 0) goto L15;
            String r05 = UserObject.getForcedFirstName(r12.getMessagesController().getUser(Long.valueOf(this.message.did)));
        L14:
            String r9 = r05;
            Context r06 = this.chatActivity.getContext();
            if (r06 != null) goto L22;
            r06 = LaunchActivity.instance;
        L22:
            if (r06 != null) goto L24;
            r06 = ApplicationLoader.applicationContext;
        L24:
            new StarsIntroActivity.StarsNeededSheet(r06, this.chatActivity.getResourceProvider(), r6, 5, r9, new StarsController$PendingPaidReactions$$ExternalSyntheticLambda2(this, r6), 0).show();
            return;
        L15:
            TLRPC.Chat r07 = r12.getMessagesController().getChat(Long.valueOf(-this.message.did));
            if (r07 != null) goto L18;
            r05 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L14
        L18:
            r05 = r07.title;
            goto L14
        }

        public long getPeerId() {
            Long r0 = this.peer;
            if (r0 == null) goto L7;
            return r0.longValue();
        L7:
            return this.this$0.getPaidReactionsDialogId(this.messageObject);
        }

        public String getToastTitle() {
            if (isAnonymous() == false) goto L7;
            return LocaleController.getString(R.string.StarsSentAnonymouslyTitle);
        L7:
            if (getPeerId() == 0) goto L13;
            if (getPeerId() == UserConfig.getInstance(this.this$0.currentAccount).getClientUserId()) goto L13;
            return LocaleController.formatString(R.string.StarsSentTitleChannel, new Object[]{DialogObject.getShortName(getPeerId())});
        L13:
            return LocaleController.getString(R.string.StarsSentTitle);
        }

        public boolean isAnonymous() {
            if (getPeerId() != UserObject.ANONYMOUS) goto L6;
            return true;
        L6:
            return false;
        }

        public void setOverlay(StarReactionsOverlay r1) {
            this.overlay = r1;
        }
    }

    /* renamed from: $r8$lambda$-RZjEMxvYCtyuSt_61udmFKLPfw, reason: not valid java name */
    public static /* synthetic */ void m19099$r8$lambda$RZjEMxvYCtyuSt_61udmFKLPfw(Utilities.Callback r0, Boolean r1) {
        if (r0 == null) goto L5;
        r0.run(r1);
        return;
    }

    /* renamed from: $r8$lambda$-hmVs_uGIXG_p_qmTK77otHeDcg, reason: not valid java name */
    public static /* synthetic */ void m19100$r8$lambda$hmVs_uGIXG_p_qmTK77otHeDcg(StarsController r0, TLRPC.TL_payments_paymentResult r1) {
        r0.lambda$buyStarGift$126(r1);
    }

    public static /* synthetic */ void $r8$lambda$0CbP5pj7F7P1HDfgSiiePMUv7Lo(Utilities.Callback2 r2, PaymentFormActivity.InvoiceStatus r3) {
        if (r3 != PaymentFormActivity.InvoiceStatus.PAID) goto L8;
        if (r2 == null) goto L12;
        r2.run(Boolean.TRUE, null);
        return;
    L12:
        return;
    L8:
        if (r3 == PaymentFormActivity.InvoiceStatus.PENDING) goto L13;
        if (r2 == null) goto L14;
        r2.run(Boolean.FALSE, null);
        return;
    L14:
        return;
    }

    public static /* synthetic */ void $r8$lambda$0LvvsjFfcjQWR7EfCOiSNq7lOqE(StarsController r0, Utilities.Callback2 r1, TL_stars.StarGift r2, boolean r3, boolean r4, long r5, TLRPC.TL_textWithEntities r7) {
        r0.lambda$buyStarGift$123(r1, r2, r3, r4, r5, r7);
    }

    public static /* synthetic */ void $r8$lambda$0MIrQ4hOjskUqK0aCGLN6bj_eXg(StarsController r0, boolean r1, HashSet r2) {
        r0.lambda$beforeSendingMessage$151(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$0fDOcStockQm92A3BQZI73kXjHk(StarsController r0, List r1, Utilities.Callback2 r2, TLRPC.TL_inputStorePaymentStarsGift r3, TL_stars.TL_starsGiftOption r4, BillingResult r5, Activity r6) {
        r0.lambda$buyGift$48(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$0t_OXs7j5DKnBN5gtJx7zK22GfE(StarsController r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$getBalance$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$0vO27LI6NyrDBVyGgLBFY3qzgVI(StarsController r0, TLObject r1, TLRPC.TL_error r2, Utilities.Callback2 r3, Context r4, Theme.ResourcesProvider r5, long r6, String r8, TLRPC.TL_payments_paymentFormStarGift r9, TL_stars.StarGift r10, long r11) {
        r0.lambda$buyResellingGift$141(r1, r2, r3, r4, r5, r6, r8, r9, r10, r11);
    }

    /* renamed from: $r8$lambda$1GWU5hksJMuM5xX-6Rx-3SbX6vo, reason: not valid java name */
    public static /* synthetic */ void m19101$r8$lambda$1GWU5hksJMuM5xX6Rx3SbX6vo(StarsController r0, int r1, Utilities.Callback r2, boolean[] r3, Utilities.Callback r4, Boolean r5) {
        r0.lambda$openPaymentForm$70(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$1GixD7BOQjEgPjPYv2ZLAb41z2g(StarsController r0, Utilities.Callback2 r1, long r2, Object r4, TLRPC.TL_textWithEntities r5) {
        r0.lambda$buyPremiumGift$115(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$211mvrx7ArvFtulECoovrvPVcAI(StarsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadSubscriptions$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2KQw2PV0WuZ3TF98eM_EBhcQgSo(StarsController r0, Utilities.Callback2 r1, TLRPC.TL_inputInvoiceStars r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$buy$28(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$3pUoPbVGZsjlMj5QTo2KToh9AEc(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, "PRODUCT_NO_ONETIME_OFFER_DETAILS");
    }

    /* renamed from: $r8$lambda$44z_3SL-VZr4EKa7G7LwtXFazJE, reason: not valid java name */
    public static /* synthetic */ void m19102$r8$lambda$44z_3SLVZr4EKa7G7LwtXFazJE(StarsController r0, ArrayList r1, Integer r2, Long r3, ArrayList r4, ArrayList r5) {
        r0.lambda$loadStarGifts$103(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$4N2EMp1bWgnkKHL8WX9a8ax88o0(Utilities.Callback2 r0, boolean r1, String r2) {
        r0.run(Boolean.valueOf(r1), r2);
    }

    public static /* synthetic */ void $r8$lambda$4lsX7SSqtstrM0vD2KRuA8x8Sps(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, "PRODUCT_NOT_FOUND");
    }

    /* renamed from: $r8$lambda$63KVsSc3t5nOsexIfDzJz4N-mAQ, reason: not valid java name */
    public static /* synthetic */ void m19103$r8$lambda$63KVsSc3t5nOsexIfDzJz4NmAQ(StarsController r0, Utilities.Callback r1, boolean[] r2, boolean[] r3, Utilities.Callback2 r4, DialogInterface r5) {
        r0.lambda$subscribeTo$75(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$67sUnhKcsc6RgeV2cBQjiis7CxQ(Utilities.Callback r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda72(r1, r0));
    }

    public static /* synthetic */ void $r8$lambda$6ZIlzjBmuuT4BPX8O0DBpRQVNQQ(StarsController r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$getResellingGiftForm$136(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$6oxdMonxiNsfmY_HYNH-PjtUF7I, reason: not valid java name */
    public static /* synthetic */ void m19104$r8$lambda$6oxdMonxiNsfmY_HYNHPjtUF7I(Utilities.Callback2 r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == true) goto L8;
        r0.run(0L, Boolean.FALSE);
        return;
    }

    public static /* synthetic */ void $r8$lambda$6w1SftkhUIw6M92RElIffKZxkV8(StarsController r0, long r1, long r3, boolean r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$stopPaidMessages$150(r1, r3, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$708s2yGG1F0t87r4h_KD1lSSa-k, reason: not valid java name */
    public static /* synthetic */ void m19105$r8$lambda$708s2yGG1F0t87r4h_KD1lSSak(StarsController r0, TLRPC.TL_error r1, Utilities.Callback2 r2, TLObject r3, TLRPC.TL_inputInvoiceStars r4) {
        r0.lambda$buyGiveaway$51(r1, r2, r3, r4);
    }

    public static /* synthetic */ int $r8$lambda$71M4iF328AGMmc4MleP8xrfpq1Q(TL_stars.StarGift r0) {
        return r0.sold_out ? 1 : 0;
    }

    /* renamed from: $r8$lambda$752T9Mh6o4wMPwnBEB-FH1HB7kY, reason: not valid java name */
    public static /* synthetic */ void m19106$r8$lambda$752T9Mh6o4wMPwnBEBFH1HB7kY(StarsController r0, AlertDialog r1, TLObject r2, TL_stars.InputSavedStarGift r3, Utilities.Callback r4) {
        r0.lambda$getUserStarGift$145(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$7GzBUcZ6Cvp-4lUjewfeMTqwdxE, reason: not valid java name */
    public static /* synthetic */ void m19107$r8$lambda$7GzBUcZ6Cvp4lUjewfeMTqwdxE(StarsController r0, Utilities.Callback2 r1, long r2, String r4, Context r5, Theme.ResourcesProvider r6, TLRPC.ChatInvite r7, String r8, TLObject r9, TLRPC.TL_error r10) {
        r0.lambda$payAfterConfirmed$92(r1, r2, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$7t0UCu1D9uhX0buYD0mhttuRISk(StarsController r0, TLRPC.TL_payments_paymentResult r1) {
        r0.lambda$payAfterConfirmed$79(r1);
    }

    /* renamed from: $r8$lambda$7tMCd1Qu-yGYTgMVQ-ayBB0xiH4, reason: not valid java name */
    public static /* synthetic */ void m19108$r8$lambda$7tMCd1QuyGYTgMVQayBB0xiH4(StarsController r0, TLRPC.TL_payments_paymentResult r1) {
        r0.lambda$buyPremiumGift$118(r1);
    }

    public static /* synthetic */ int $r8$lambda$8RIADxFUJE6nR9745iR3r7cnYBg(TL_stars.StarGift r0) {
        return r0.sold_out ? 1 : 0;
    }

    public static /* synthetic */ void $r8$lambda$91WsDiYUJPjolDzHHV177eWySrw(BillingResult r1, Utilities.Callback2 r2, BillingResult r3) {
        if (r1.getResponseCode() != 0) goto L5;
        boolean r32 = true;
    L6:
        if (r32 == false) goto L8;
        String r12 = null;
    L9:
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda7(r2, r32, r12));
        return;
    L8:
        r12 = BillingController.getResponseCodeString(r1.getResponseCode());
        goto L9
    L5:
        r32 = false;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$9_sKfZIxU4nB2WcYAzpSXUAMAkE(StarsController r0, TLObject r1, TLRPC.TL_error r2, Utilities.Callback2 r3, Context r4, Theme.ResourcesProvider r5, long r6, String r8, long r9, Object r11, TLRPC.TL_textWithEntities r12) {
        r0.lambda$buyPremiumGift$119(r1, r2, r3, r4, r5, r6, r8, r9, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$AiCex0_8mXs5rrEJlwlXq4AJ3FQ(StarsController r0, TLObject r1, MessageObject r2, long r3, Runnable r5) {
        r0.lambda$updateMediaPrice$94(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$BsMauLOcbyhrhsDCcNl4lgwEei0(MessagesStorage r5, ArrayList r6, int r7, long r8) {
        SQLiteDatabase r52 = r5.getDatabase();
        SQLitePreparedStatement r0 = null;
        r52.executeFast("DELETE FROM star_gifts2").stepThis().dispose();     // Catch: Throwable -> L10 Exception -> L12
        if (r6 == null) goto L14;
        r0 = r52.executeFast("REPLACE INTO star_gifts2 VALUES(?, ?, ?, ?, ?)");     // Catch: Throwable -> L10 Exception -> L12
        int r53 = 0;
    L6:
        if (r53 >= r6.size()) goto L14;
        TL_stars.StarGift r1 = (TL_stars.StarGift) r6.get(r53);     // Catch: Throwable -> L10 Exception -> L12
        r0.requery();     // Catch: Throwable -> L10 Exception -> L12
        r0.bindLong(1, r1.id);     // Catch: Throwable -> L10 Exception -> L12
        NativeByteBuffer r2 = new NativeByteBuffer(r1.getObjectSize());     // Catch: Throwable -> L10 Exception -> L12
        r1.serializeToStream(r2);     // Catch: Throwable -> L10 Exception -> L12
        r0.bindByteBuffer(2, r2);     // Catch: Throwable -> L10 Exception -> L12
        r0.bindLong(3, r7);     // Catch: Throwable -> L10 Exception -> L12
        r0.bindLong(4, r8);     // Catch: Throwable -> L10 Exception -> L12
        r0.bindInteger(5, r53);     // Catch: Throwable -> L10 Exception -> L12
        r0.step();     // Catch: Throwable -> L10 Exception -> L12
        r2.reuse();     // Catch: Throwable -> L10 Exception -> L12
        r53 = r53 + 1;
        goto L6
    L14:
        if (r0 == null) goto L27;
        r0.dispose();
        return;
    L27:
        return;
    L10:
        th = move-exception;
        if (r0 == null) goto L23;
        r0.dispose();
    L23:
        throw th;
    L12:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L10
        if (r0 == null) goto L28;
        r0.dispose();
        return;
    }

    /* renamed from: $r8$lambda$C-cLwyQlUOBgZicCyoucAZ88w-c, reason: not valid java name */
    public static /* synthetic */ void m19109$r8$lambda$CcLwyQlUOBgZicCyoucAZ88wc(StarsController r0, TLObject r1, Runnable r2, TLRPC.TL_error r3, boolean r4, long r5, int r7, MessageObject r8, long r9) {
        r0.lambda$updateMediaPrice$96(r1, r2, r3, r4, r5, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$D9zaekOhYgOHpMdVNwRySe0LA7E(StarsController r0, Utilities.Callback2 r1, TLRPC.TL_inputInvoiceStarGift r2, Context r3, Theme.ResourcesProvider r4, String r5, TL_stars.StarGift r6, boolean r7, boolean r8, long r9, TLRPC.TL_textWithEntities r11, TLObject r12, TLRPC.TL_error r13) {
        r0.lambda$buyStarGift$133(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r13);
    }

    public static /* synthetic */ void $r8$lambda$DErIybbYfQ854cklBZI8aDA5518(List r8, Utilities.Callback2 r9, TLRPC.TL_inputStorePaymentStarsTopup r10, TL_stars.TL_starsTopupOption r11, Activity r12) {
        if (r8.isEmpty() == false) goto L6;
        FileLog.d("StarsController.buy queryProductDetails done: no products");
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda97(r9));
        return;
    L6:
        ProductDetails r1 = (ProductDetails) r8.get(0);
        ProductDetails.OneTimePurchaseOfferDetails r2 = r1.getOneTimePurchaseOfferDetails();
        if (r2 != null) goto L10;
        FileLog.d("StarsController.buy queryProductDetails done: no details");
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda98(r9));
        return;
    L10:
        r10.currency = r2.getPriceCurrencyCode();
        r10.amount = (long) ((r2.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r11.currency)));
        BillingController.getInstance().addResultListener(r1.getProductId(), new StarsController$$ExternalSyntheticLambda99(r9));
        BillingController.getInstance().setOnCanceled(new StarsController$$ExternalSyntheticLambda100(r9));
        FileLog.d("StarsController.buy launchBillingFlow");
        BillingController.getInstance().launchBillingFlow(r12, AccountInstance.getInstance(UserConfig.selectedAccount), r10, Collections.singletonList(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails((ProductDetails) r8.get(0)).build()));
    }

    /* renamed from: $r8$lambda$DNhuGZWEZWq6-wC_xzv7hDvBEQw, reason: not valid java name */
    public static /* synthetic */ void m19110$r8$lambda$DNhuGZWEZWq6wC_xzv7hDvBEQw(StarsController r0, TLObject r1, Utilities.Callback2 r2, long r3, String r5, TLRPC.TL_error r6, Context r7, Theme.ResourcesProvider r8, TLRPC.ChatInvite r9, String r10) {
        r0.lambda$payAfterConfirmed$91(r1, r2, r3, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$DcBbmFVSJsVnxgiDBFN8kRSx43Q(StarsController r0, Utilities.Callback2 r1, TLRPC.TL_inputStorePaymentStarsGift r2, TL_stars.TL_starsGiftOption r3, Activity r4, BillingResult r5, List r6) {
        r0.lambda$buyGift$49(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$Dzs5ofZzo0Xys-hmvRVMEp8qWws, reason: not valid java name */
    public static /* synthetic */ void m19111$r8$lambda$Dzs5ofZzo0XyshmvRVMEp8qWws(StarsController r0, TL_stars.StarGifts r1) {
        r0.lambda$loadStarGifts$107(r1);
    }

    /* renamed from: $r8$lambda$EJhu83W63w6xD1W-kC0YsMeiAgg, reason: not valid java name */
    public static /* synthetic */ void m19112$r8$lambda$EJhu83W63w6xD1WkC0YsMeiAgg(StarsController r0, MessageObject r1, ChatActivity r2, long r3, Long r5) {
        r0.lambda$sendPaidReaction$98(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$ELZfW1FgLHetd9NKqKhsEWRaRmI(StarsController r0, Utilities.Callback r1, MessageObject r2, Context r3, long r4, String r6, int r7, String r8, TLRPC.InputInvoice r9, long r10, Theme.ResourcesProvider r12, TLRPC.TL_payments_paymentFormStars r13, TLObject r14, TLRPC.TL_error r15) {
        r0.lambda$payAfterConfirmed$86(r1, r2, r3, r4, r6, r7, r8, r9, r10, r12, r13, r14, r15);
    }

    /* renamed from: $r8$lambda$EOwRo-6RAibLoJMYmUqnoGU6u10, reason: not valid java name */
    public static /* synthetic */ void m19113$r8$lambda$EOwRo6RAibLoJMYmUqnoGU6u10(TLObject r1, Utilities.Callback r2) {
        if ((r1 instanceof TL_stars.StarGifts) == false) goto L6;
        r2.run((TL_stars.StarGifts) r1);
        return;
    L6:
        r2.run(null);
    }

    public static /* synthetic */ void $r8$lambda$EVfuKGOwIrJsEskQfh3TxrejZUw(StarsController r0, boolean[] r1, long r2, NotificationCenter.NotificationCenterDelegate[] r4, Utilities.Callback r5, int r6, int r7, Object[] r8) {
        r0.lambda$getStarGift$113(r1, r2, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$F374kPRNOoLvk2Twb1HpXaQkPJ4(StarsController r0, MessageObject r1, ChatActivity r2, long r3, Long r5) {
        r0.lambda$sendPaidReaction$99(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$F6l3fwSPf2CkbQCTsutdnQACo7I(StarsController r0, boolean[] r1, int r2, Utilities.Callback r3, Utilities.Callback r4, Boolean r5) {
        r0.lambda$openPaymentForm$67(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$FuvGxynQAvGXHXhtpGAdEV-iGqo, reason: not valid java name */
    public static /* synthetic */ void m19114$r8$lambda$FuvGxynQAvGXHXhtpGAdEViGqo(StarsController r0, int r1) {
        r0.lambda$pay$65(r1);
    }

    public static /* synthetic */ void $r8$lambda$GMjBzqzFpE7g_DyWiSoxekf5Anw(StarsController r0, boolean[] r1, long r2, Object r4, TLRPC.TL_textWithEntities r5, Utilities.Callback2 r6) {
        r0.lambda$buyPremiumGift$116(r1, r2, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$GU6eooh1RQsjm2A9ue07t4ruWvQ(Utilities.Callback2 r1) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda6(r1));
    }

    public static /* synthetic */ void $r8$lambda$GVqWnHWIjEmLvTniSF7wHbjMeo8() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.presentFragment(new StarsIntroActivity());
    }

    public static /* synthetic */ void $r8$lambda$Gz8WXjXfi_ny69Yznw8xuV_y2JM(StarsController r0, MessageObject r1, TLRPC.TL_inputInvoiceMessage r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$pay$64(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$HCtA3TdX7eM04KqxaNoeip1j8wg(StarsController r0, boolean r1, int r2) {
        r0.lambda$beforeSendingMessage$152(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HWn2u8BUYnNcfcAHVVah92tcs4Y(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, "PRODUCT_NO_ONETIME_OFFER_DETAILS");
    }

    public static /* synthetic */ void $r8$lambda$HXx1EzrECNvylgkPWkHrqZOEGNY(Utilities.Callback2 r0, boolean r1, String r2) {
        r0.run(Boolean.valueOf(r1), r2);
    }

    public static /* synthetic */ void $r8$lambda$IkZLdQGnMoBlO4kw7LQkpSbq4kY(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, "PRODUCT_NO_ONETIME_OFFER_DETAILS");
    }

    public static /* synthetic */ void $r8$lambda$J0ZkbdIi5Y54h4YVOhoaviEdiLE(StarsController r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadTransactions$18(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$K6rkSGrubSEbX_i6BgFNyH2BZTk(StarsController r0, ArrayList r1, BillingResult r2, List r3) {
        r0.lambda$getOptions$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$L0JypI0vgNWvJb7cltQeaf7ypDo(StarsController r0, ArrayList r1) {
        r0.lambda$getGiveawayOptions$14(r1);
    }

    /* renamed from: $r8$lambda$LJA61Htjvl--sb7cft-FmMy3Rd8, reason: not valid java name */
    public static /* synthetic */ int m19115$r8$lambda$LJA61Htjvlsb7cftFmMy3Rd8(TL_stars.StarGift r0) {
        if (r0.birthday == false) goto L6;
        return -1;
    L6:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$LJYDPBim4bmVx1r6VTziltdUj4w(ChatActivity r1, TL_stars.StarGift r2, CharSequence r3, long r4) {
        BulletinFactory r12 = BulletinFactory.of(r1);
        TLRPC.Document r22 = r2.sticker;
        String r0 = LocaleController.getString(R.string.StarsGiftCompleted);
        if (r3 != null) goto L6;
        r3 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsGiftCompletedText", (int) r4, new Object[0]));
    L6:
        r12.createEmojiBulletin(r22, r0, r3).show(true);
    }

    public static /* synthetic */ void $r8$lambda$Ldtw7jCqG_FC_pi9idOvf3i9oYA(Utilities.Callback r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda65(r1, r0));
    }

    /* renamed from: $r8$lambda$LycbN3r_ngPcRG4hiURwPlLHN-Y, reason: not valid java name */
    public static /* synthetic */ void m19116$r8$lambda$LycbN3r_ngPcRG4hiURwPlLHNY(StarsController r0, TLRPC.TL_error r1, Utilities.Callback2 r2, TLObject r3, TLRPC.TL_inputInvoiceStars r4) {
        r0.lambda$buyGift$38(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$MHGjUjmYimNYlRY24ssqQN6V47Q(StarsController r0, Utilities.Callback2 r1, Context r2, Theme.ResourcesProvider r3, long r4, String r6, TL_stars.StarGift r7, boolean r8, boolean r9, long r10, TLRPC.TL_textWithEntities r12, TLObject r13, TLRPC.TL_error r14) {
        r0.lambda$buyStarGift$131(r1, r2, r3, r4, r6, r7, r8, r9, r10, r12, r13, r14);
    }

    public static /* synthetic */ int $r8$lambda$MMWtfYJBPiAUTyzKvw5X7ukwj6I(TL_stars.StarGift r0) {
        return r0.sold_out ? 1 : 0;
    }

    public static /* synthetic */ void $r8$lambda$MxtQIOKi6zZbcXkPksy2wZcQzJc(StarsController r0, Utilities.Callback r1, boolean[] r2, boolean[] r3, Utilities.Callback r4, DialogInterface r5) {
        r0.lambda$openPaymentForm$69(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$N4Q4pfKNiiMlcRy-DII8I9qqz4Y, reason: not valid java name */
    public static /* synthetic */ void m19117$r8$lambda$N4Q4pfKNiiMlcRyDII8I9qqz4Y(Utilities.Callback2 r1) {
        FileLog.d("StarsController.buy onCanceled");
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda118(r1));
    }

    public static /* synthetic */ void $r8$lambda$NTy5GP6rsOsutnouSOpVsvjaBCE(StarsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$getOptions$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NmzP04uKlI6aUI9IW1aocwMcmCo(Utilities.Callback2 r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == true) goto L8;
        r0.run(Boolean.FALSE, null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$OPmEMTqK6ThW5eY8TyhdjP8ULNI(StarsController r0, Utilities.Callback2 r1, TLRPC.TL_inputStorePaymentStarsGiveaway r2, Activity r3, BillingResult r4, List r5) {
        r0.lambda$buyGiveaway$62(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$OgkuN-qCEbaVooVvuyUQJGWK-Wk, reason: not valid java name */
    public static /* synthetic */ void m19118$r8$lambda$OgkuNqCEbaVooVvuyUQJGWKWk(StarsController r0, HashSet r1, Runnable r2) {
        r0.lambda$beforeSendingFinalRequest$153(r1, r2);
    }

    /* renamed from: $r8$lambda$QGlcRjAL-WWhkhWPn-8jpXUTozQ, reason: not valid java name */
    public static /* synthetic */ void m19119$r8$lambda$QGlcRjALWWhkhWPn8jpXUTozQ(StarsController r0, boolean[] r1, NotificationCenter.NotificationCenterDelegate[] r2) {
        r0.lambda$getStarGift$114(r1, r2);
    }

    /* renamed from: $r8$lambda$RY7-o0xHkRyTmLUlhpPeR0M4Jps, reason: not valid java name */
    public static /* synthetic */ void m19120$r8$lambda$RY7o0xHkRyTmLUlhpPeR0M4Jps(StarsController r0, Utilities.Callback2 r1, TLRPC.TL_inputInvoiceStars r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$buyGiveaway$52(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$RyiiO5OPJfbsyuT8GTUU6GiDtNE(StarsController r0, TLObject r1) {
        r0.lambda$getGiftOptions$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$S7goGININ1Ll80nHTtolrYdOEKI(StarsController r0, TLRPC.TL_payments_paymentResult r1) {
        r0.lambda$payAfterConfirmed$87(r1);
    }

    /* renamed from: $r8$lambda$SLpkDP8Hjg7FAu1-7cS6iZwNx8Q, reason: not valid java name */
    public static /* synthetic */ void m19121$r8$lambda$SLpkDP8Hjg7FAu17cS6iZwNx8Q(StarsController r0, ArrayList r1, BillingResult r2, List r3) {
        r0.lambda$getGiveawayOptions$13(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$THMz2LmQwYNPlVSJyL7mrwLGn_A(StarsController r0, Utilities.Callback2 r1, TLRPC.TL_inputInvoiceStars r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$buyGift$39(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Ule_wszzFKoAau6BDrXPpC2leo8(ProductDetails r9, BillingResult r10, Utilities.Callback2 r11, Activity r12, TLRPC.TL_inputStorePaymentStarsGiveaway r13, List r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda140(r15, r9, r10, r11, r12, r13, r14, r16));
    }

    public static /* synthetic */ int $r8$lambda$Us9pnX4ICTEIGHoYchoexwVRQpM(TL_stars.StarGift r0) {
        return r0.sold_out ? 1 : 0;
    }

    public static /* synthetic */ void $r8$lambda$UykCEEPChVwGE3i07RyGQcf0aII(StarsController r0, long r1, boolean[] r3, Utilities.Callback r4, Context r5, Theme.ResourcesProvider r6, boolean r7, String r8, MessageObject r9, TLRPC.InputInvoice r10, TLRPC.TL_payments_paymentFormStars r11, int r12, long r13, Utilities.Callback r15) {
        r0.lambda$openPaymentForm$71(r1, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r15);
    }

    public static /* synthetic */ void $r8$lambda$Vxfk4KnhRmRPZEGyEHwrmciO22g(StarsController r0, boolean[] r1, Utilities.Callback2 r2) {
        r0.lambda$subscribeTo$78(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WCmMNKVKUxmHwLLEnVnHmxKk_Rk(StarsController r0, MessageObject r1, long r2, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$updateMediaPrice$95(r1, r2, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$WEdY_xElKD8DGcMMEXBXDyQgIHA(StarsController r0, List r1, Utilities.Callback2 r2, TLRPC.TL_inputStorePaymentStarsGiveaway r3, BillingResult r4, Activity r5) {
        r0.lambda$buyGiveaway$61(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$XDnXZv4s8DWXgcLBZ9sQ_-ulN1I, reason: not valid java name */
    public static /* synthetic */ void m19122$r8$lambda$XDnXZv4s8DWXgcLBZ9sQ_ulN1I(StarsController r0, Runnable r1, MessageObject r2, TLRPC.InputInvoice r3, TLRPC.TL_payments_paymentFormStars r4, Utilities.Callback r5) {
        r0.lambda$openPaymentForm$66(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ZDqvcsV_FdnCRcyjFsP8gvpYUqM(StarsController r0, TLObject r1) {
        r0.lambda$loadSubscriptions$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZHYZOMS4eAs0Ttd0VV1OF6XD8Cw(MessagesStorage r14, ArrayList r15, ArrayList r16, ArrayList r17, Utilities.Callback5 r18) {
        int r1 = 0;
        long r3 = 0;
        SQLiteCursor r5 = null;
        r5 = r14.getDatabase().queryFinalized("SELECT data, hash, time FROM star_gifts2 ORDER BY pos ASC", new Object[0]);     // Catch: Throwable -> L12 Exception -> L54
        int r6 = 0;
        long r7 = 0;
    L71:
    L52:
        e = e;
        ArrayList r9 = r16;
    L51:
        ArrayList r10 = r17;
    L47:
        Exception r142 = e;
    L17:
        r1 = r6;
        r3 = r7;
    L56:
        FileLog.e(r142);     // Catch: Throwable -> L12
        if (r5 == null) goto L59;
        r5.dispose();
    L59:
        long r4 = r3;
        int r32 = r1;
    L60:
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda64(r18, r15, r32, r4, r10, r9));
        return;
    L6:
        if (r5.next() == false) goto L20;
        NativeByteBuffer r0 = r5.byteBufferValue(0);     // Catch: Throwable -> L12 Exception -> L14
        if (r0 == null) goto L71;
        TL_stars.StarGift r92 = TL_stars.StarGift.TLdeserialize(r0, r0.readInt32(false), false);     // Catch: Throwable -> L12 Exception -> L14
        if (r92 == null) goto L18;
        r15.add(r92);     // Catch: Throwable -> L12 Exception -> L14
    L18:
        r0.reuse();     // Catch: Throwable -> L12 Exception -> L14
        r6 = (int) r5.longValue(1);     // Catch: Throwable -> L12 Exception -> L14
        r7 = r5.longValue(2);     // Catch: Throwable -> L12 Exception -> L14
    L14:
        e = move-exception;
        r142 = e;
        r9 = r16;
    L16:
        r10 = r17;
        goto L17
    L20:
        ArrayList<Long> r02 = new ArrayList();     // Catch: Throwable -> L12 Exception -> L52
        ArrayList r93 = new ArrayList();     // Catch: Throwable -> L12 Exception -> L52
        int r102 = r15.size();     // Catch: Throwable -> L12 Exception -> L52
    L21:
        if (r1 >= r102) goto L30;
        Object r11 = r15.get(r1);     // Catch: Throwable -> L12 Exception -> L14
        r1 = r1 + 1;     // Catch: Throwable -> L12 Exception -> L14
        TLRPC.Peer r112 = ((TL_stars.StarGift) r11).released_by;     // Catch: Throwable -> L12 Exception -> L14
        if (r112 == null) goto L21;
        long r113 = DialogObject.getPeerDialogId(r112);     // Catch: Throwable -> L12 Exception -> L14
        if (r113 > 0) goto L26;
        if (r113 >= 0) goto L21;
        r93.add(Long.valueOf(-r113));     // Catch: Throwable -> L12 Exception -> L14
        goto L21
    L26:
        r02.add(Long.valueOf(r113));     // Catch: Throwable -> L12 Exception -> L14
        goto L21
    L30:
        if (r93.isEmpty() == false) goto L73;
        r9 = r16;
    L76:
        if (r02.isEmpty() == true) goto L48;
        r10 = r17;
        r14.getUsersInternal(r02, r10);     // Catch: Throwable -> L12 Exception -> L46
    L49:
        r5.dispose();
        r32 = r6;
        r4 = r7;
    L46:
        e = e;
        goto L47
    L48:
        r10 = r17;
    L50:
        e = e;
        goto L51
    L73:
        String r12 = TextUtils.join(",", r93);     // Catch: Throwable -> L12 Exception -> L38
        r9 = r16;
        r14.getChatsInternal(r12, r9);     // Catch: Throwable -> L12 Exception -> L36
    L36:
        e = e;
    L37:
        r142 = e;
    L38:
        e = e;
        r9 = r16;
    L54:
        e = move-exception;
        r9 = r16;
        r10 = r17;
        r142 = e;
    L12:
        th = move-exception;
        if (r5 == null) goto L95;
        r5.dispose();
        throw th;
    L95:
        throw th;
    }

    /* renamed from: $r8$lambda$_-huASLe6ImEC6o8mmfNNzQ5AkQ, reason: not valid java name */
    public static /* synthetic */ void m19123$r8$lambda$_huASLe6ImEC6o8mmfNNzQ5AkQ(StarsController r0, TLObject r1, Runnable r2) {
        r0.lambda$getBalance$0(r1, r2);
    }

    /* renamed from: $r8$lambda$_CaaQE60qTIO-9s-LAkjRqiU8go, reason: not valid java name */
    public static /* synthetic */ void m19124$r8$lambda$_CaaQE60qTIO9sLAkjRqiU8go() {
    }

    /* renamed from: $r8$lambda$_Do1-Vf1-yIL6PW-RsChVQmHYSI, reason: not valid java name */
    public static /* synthetic */ void m19125$r8$lambda$_Do1Vf1yIL6PWRsChVQmHYSI(StarsController r0, Utilities.Callback2 r1, TLRPC.TL_payments_paymentFormStarGift r2, TL_stars.StarGift r3, long r4) {
        r0.lambda$buyResellingGift$137(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$_OePFk45mIlUiWyDOFwiSEUoRqM(StarsController r0, int r1, TLObject r2) {
        r0.lambda$loadTransactions$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_XzaFSk1iL8Dtx16PzFMsDeBNYA(StarsController r0, boolean[] r1, String r2, TLRPC.ChatInvite r3, boolean[] r4, Utilities.Callback2 r5, Utilities.Callback r6) {
        r0.lambda$subscribeTo$74(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$_nNwiF8oKfPLwtXgfz-c8GTvU2Y, reason: not valid java name */
    public static /* synthetic */ void m19126$r8$lambda$_nNwiF8oKfPLwtXgfzc8GTvU2Y(StarsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$getGiftOptions$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_vZmy4C79M0c7wIDLm_GVRmcgOs(StarsController r0, boolean[] r1, String r2, TLRPC.ChatInvite r3, Utilities.Callback2 r4) {
        r0.lambda$payAfterConfirmed$89(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$_xLyrYswcPMrlE9pCmD51wG-2WU, reason: not valid java name */
    public static /* synthetic */ void m19127$r8$lambda$_xLyrYswcPMrlE9pCmD51wG2WU(StarsController r0, Utilities.Callback2 r1, Context r2, Theme.ResourcesProvider r3, long r4, String r6, long r7, Object r9, TLRPC.TL_textWithEntities r10, TLObject r11, TLRPC.TL_error r12) {
        r0.lambda$buyPremiumGift$120(r1, r2, r3, r4, r6, r7, r9, r10, r11, r12);
    }

    public static /* synthetic */ int $r8$lambda$a2qcG_CF3BQhoiP7hSQh56K6RdQ(TL_stars.StarGift r0) {
        if (r0.birthday == false) goto L6;
        return -1;
    L6:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$aUXHaEjBJoTRLw17pXrAHfsMUDo(Utilities.Callback2 r2, PaymentFormActivity.InvoiceStatus r3) {
        if (r3 != PaymentFormActivity.InvoiceStatus.PAID) goto L8;
        if (r2 == null) goto L12;
        r2.run(Boolean.TRUE, null);
        return;
    L12:
        return;
    L8:
        if (r3 == PaymentFormActivity.InvoiceStatus.PENDING) goto L13;
        if (r2 == null) goto L14;
        r2.run(Boolean.FALSE, null);
        return;
    L14:
        return;
    }

    public static /* synthetic */ void $r8$lambda$aYsmyMnEjMg7MiWuBLzaPs9t8Bg(ProductDetails r9, BillingResult r10, Utilities.Callback2 r11, Activity r12, TLRPC.TL_inputStorePaymentStarsGift r13, List r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda151(r15, r9, r10, r11, r12, r13, r14, r16));
    }

    public static /* synthetic */ void $r8$lambda$awga3ygleA5XaS5AGIopEjK9oDU(StarsController r0, Runnable r1, boolean r2, long r3, int r5, MessageObject r6, long r7, TLObject r9, TLRPC.TL_error r10) {
        r0.lambda$updateMediaPrice$97(r1, r2, r3, r5, r6, r7, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$b7n4Xkga8tF3q667jc3QykwqdNI(TLObject r1, ProductDetails r2, BillingResult r3, Utilities.Callback2 r4, Activity r5, TLRPC.TL_inputStorePaymentStarsGift r6, List r7, TLRPC.TL_error r8) {
        if ((r1 instanceof TLRPC.TL_boolTrue) == false) goto L7;
        BillingController.getInstance().addResultListener(r2.getProductId(), new StarsController$$ExternalSyntheticLambda9(r3, r4));
        BillingController.getInstance().setOnCanceled(new StarsController$$ExternalSyntheticLambda10(r4));
        BillingController.getInstance().launchBillingFlow(r5, AccountInstance.getInstance(UserConfig.selectedAccount), r6, Collections.singletonList(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails((ProductDetails) r7.get(0)).build()));
        return;
    L7:
        if ((r1 instanceof TLRPC.TL_boolFalse) == false) goto L11;
        if (r4 == null) goto L18;
        r4.run(Boolean.FALSE, "PURCHASE_FORBIDDEN");
        return;
    L18:
        return;
    L11:
        if (r4 == null) goto L19;
        Boolean r12 = Boolean.FALSE;
        if (r8 == null) goto L15;
        String r22 = r8.text;
    L16:
        r4.run(r12, r22);
        return;
    L15:
        r22 = "SERVER_ERROR";
        goto L16
    }

    /* renamed from: $r8$lambda$bWHEmLu_auBJwl0IG-bM-zuFxgc, reason: not valid java name */
    public static /* synthetic */ void m19128$r8$lambda$bWHEmLu_auBJwl0IGbMzuFxgc(StarsController r0, boolean[] r1, TLRPC.TL_payments_paymentFormStarGift r2, TL_stars.StarGift r3, long r4, Utilities.Callback2 r6) {
        r0.lambda$buyResellingGift$138(r1, r2, r3, r4, r6);
    }

    public static /* synthetic */ void $r8$lambda$deJyltirXToTnmOnXJMSLHPFZRA(Utilities.Callback r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == true) goto L8;
        r0.run(Boolean.FALSE);
        return;
    }

    public static /* synthetic */ void $r8$lambda$e83p9TWjyjvsRKqy9X3iTHDnbLk(StarsController r0, MessageObject r1, TLRPC.InputInvoice r2, Utilities.Callback r3, BulletinFactory r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$payAfterConfirmed$84(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$eZZ3YfnlrB8Rhdh9_JE3qe4QHrA(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, "PRODUCT_NOT_FOUND");
    }

    /* renamed from: $r8$lambda$epfC-rww75uDDR5hCE_nn3t6abA, reason: not valid java name */
    public static /* synthetic */ void m19129$r8$lambda$epfCrww75uDDR5hCE_nn3t6abA(StarsController r0, BillingResult r1, List r2, ArrayList r3) {
        r0.lambda$getGiveawayOptions$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$fRQzQCOIx20WDslf6AR0j5Yo4F4(Utilities.Callback2 r1) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda12(r1));
    }

    public static /* synthetic */ void $r8$lambda$fWK7pRtgbDU4eEQo4WaFH2Dh_Hc(ProfileActivity r3, TL_stars.StarGift r4, CharSequence r5, long r6, String r8) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda21(r3), 200);
        BulletinFactory r32 = BulletinFactory.of(r3);
        TLRPC.Document r42 = r4.sticker;
        String r0 = LocaleController.getString(R.string.StarsGiftCompleted);
        if (r5 != null) goto L6;
        r5 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsGiftCompletedChannelText", (int) r6, new Object[]{r8}));
    L6:
        r32.createEmojiBulletin(r42, r0, r5).show(false);
    }

    public static /* synthetic */ void $r8$lambda$g2Yu4hA79lPjHRDzxDUjcEH5u2I(Utilities.Callback2 r0, boolean r1, String r2) {
        r0.run(Boolean.valueOf(r1), r2);
    }

    public static /* synthetic */ void $r8$lambda$gFpgIzIuyrk3mmdsthuyvBmQnk0(StarsController r0, boolean[] r1, TL_stars.StarGift r2, boolean r3, boolean r4, long r5, TLRPC.TL_textWithEntities r7, Utilities.Callback2 r8) {
        r0.lambda$buyStarGift$124(r1, r2, r3, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$hF5jE61w44C3qcPHxhBDaeGitf8(StarsController r0, TLObject r1, Utilities.Callback r2, MessageObject r3, Context r4, long r5, String r7, int r8, String r9, TLRPC.InputInvoice r10, long r11, TLRPC.TL_error r13, Theme.ResourcesProvider r14, TLRPC.TL_payments_paymentFormStars r15) {
        r0.lambda$payAfterConfirmed$85(r1, r2, r3, r4, r5, r7, r8, r9, r10, r11, r13, r14, r15);
    }

    public static /* synthetic */ void $r8$lambda$hP7Y_brKP_GbMOZBNY1XknNMC1g(StarsController r0, TLObject r1, TLRPC.TL_error r2, Utilities.Callback2 r3, TLRPC.TL_inputInvoiceStarGift r4, Context r5, Theme.ResourcesProvider r6, String r7, TL_stars.StarGift r8, boolean r9, boolean r10, long r11, TLRPC.TL_textWithEntities r13) {
        r0.lambda$buyStarGift$132(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r13);
    }

    public static /* synthetic */ void $r8$lambda$hjjcuf_sMV5ySwJuJeCXc8Y6WfM(StarsController r0, boolean[] r1, Utilities.Callback r2) {
        r0.lambda$openPaymentForm$72(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hsqTHx_ys9IlI4ex42YSj7USV5M(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, null);
    }

    /* renamed from: $r8$lambda$iE3dgDKCU5xkDcHGs6ag-S397A0, reason: not valid java name */
    public static /* synthetic */ void m19130$r8$lambda$iE3dgDKCU5xkDcHGs6agS397A0(StarsController r0, TLObject r1) {
        r0.lambda$updateMediaPrice$93(r1);
    }

    public static /* synthetic */ void $r8$lambda$imNd1FBD77YBVJWTQVARHnJuTxA(StarsController r0, ArrayList r1) {
        r0.lambda$getOptions$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$jN2MiA9TgV5tTGrsJAeUuKmFu64(Utilities.Callback5 r0, ArrayList r1, int r2, long r3, ArrayList r5, ArrayList r6) {
        r0.run(r1, Integer.valueOf(r2), Long.valueOf(r3), r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$kLnKldnCemQOWk5c6bcBevZ_kEY(StarsController r0, TLObject r1, TLRPC.TL_error r2, Utilities.Callback r3) {
        r0.lambda$getResellingGiftForm$135(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$kdsBADDR-HT0H4MsqYCEBJkPNro, reason: not valid java name */
    public static /* synthetic */ void m19131$r8$lambda$kdsBADDRHT0H4MsqYCEBJkPNro(StarsController r0, TLObject r1, MessageObject r2, TLRPC.InputInvoice r3, Utilities.Callback r4, BulletinFactory r5, TLRPC.TL_error r6) {
        r0.lambda$payAfterConfirmed$83(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$l4Ak5TbI28YCEvSS0P3gdb_btHI(StarsController r0, Utilities.Callback2 r1, Context r2, Theme.ResourcesProvider r3, long r4, String r6, TLRPC.TL_payments_paymentFormStarGift r7, TL_stars.StarGift r8, long r9, TLObject r11, TLRPC.TL_error r12) {
        r0.lambda$buyResellingGift$142(r1, r2, r3, r4, r6, r7, r8, r9, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$l9XQCydCvvFxf1kRO2aFJ3of77Q(StarsController r0, Utilities.Callback2 r1, TLRPC.TL_inputInvoicePremiumGiftStars r2, Context r3, Theme.ResourcesProvider r4, String r5, long r6, Object r8, TLRPC.TL_textWithEntities r9, TLObject r10, TLRPC.TL_error r11) {
        r0.lambda$buyPremiumGift$122(r1, r2, r3, r4, r5, r6, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$lEK2pd8FljJSg5scvwmsjUWhUGw(StarsController r0, BillingResult r1, List r2, ArrayList r3) {
        r0.lambda$getGiftOptions$7(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$lc2-i9YfGREYynrKMVCvfrwfs-o, reason: not valid java name */
    public static /* synthetic */ void m19132$r8$lambda$lc2i9YfGREYynrKMVCvfrwfso(StarsController r0, TLObject r1, TLRPC.TL_error r2, Utilities.Callback2 r3, TLRPC.TL_inputInvoicePremiumGiftStars r4, Context r5, Theme.ResourcesProvider r6, String r7, long r8, Object r10, TLRPC.TL_textWithEntities r11) {
        r0.lambda$buyPremiumGift$121(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$m85uJl3v1_BSPk6FFkbr_9NYCYU(StarsController r0, ArrayList r1, BillingResult r2, List r3) {
        r0.lambda$getGiftOptions$8(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$mem-VUVYpyKqXZjQLg0vcRW7zVY, reason: not valid java name */
    public static /* synthetic */ void m19133$r8$lambda$memVUVYpyKqXZjQLg0vcRW7zVY(StarsController r0, Utilities.Callback r1, TL_stars.StarGift r2, long r3) {
        r0.lambda$getResellingGiftForm$134(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$n3MoO2ER5Tq2R9Y9OwCy9_utRUg(StarsController r0, boolean[] r1, MessageObject r2, TLRPC.InputInvoice r3, TLRPC.TL_payments_paymentFormStars r4, Utilities.Callback r5) {
        r0.lambda$payAfterConfirmed$81(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$nG0zK4QkjDCJo3pETctNU0vmKhI(BillingResult r1, Utilities.Callback2 r2, BillingResult r3) {
        if (r1.getResponseCode() != 0) goto L5;
        boolean r32 = true;
    L6:
        if (r32 == false) goto L8;
        String r12 = null;
    L9:
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda11(r2, r32, r12));
        return;
    L8:
        r12 = BillingController.getResponseCodeString(r1.getResponseCode());
        goto L9
    L5:
        r32 = false;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$nLuT8_cgufDRPkSjwvgvOEF2QjI(StarsController r0, TLRPC.TL_payments_paymentResult r1) {
        r0.lambda$buyResellingGift$140(r1);
    }

    /* renamed from: $r8$lambda$nRfoIKOW1uUiubBP0NLAXmyl--o, reason: not valid java name */
    public static /* synthetic */ void m19134$r8$lambda$nRfoIKOW1uUiubBP0NLAXmylo(StarsController r0, TLObject r1, long r2, long r4, boolean r6) {
        r0.lambda$stopPaidMessages$149(r1, r2, r4, r6);
    }

    /* renamed from: $r8$lambda$nadFl9CkQFfHIYek7Zj3-B51CGo, reason: not valid java name */
    public static /* synthetic */ void m19135$r8$lambda$nadFl9CkQFfHIYek7Zj3B51CGo(Utilities.Callback2 r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == true) goto L8;
        r0.run(Boolean.FALSE, null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$pCPEhGzb_GFPjgRpCoofiz0w4wE(StarsController r0, AlertDialog r1, TL_stars.InputSavedStarGift r2, Utilities.Callback r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$getUserStarGift$146(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$pPSrl09zBOQ4jtTLaXCO-arNLEE, reason: not valid java name */
    public static /* synthetic */ void m19136$r8$lambda$pPSrl09zBOQ4jtTLaXCOarNLEE(StarsController r0, Activity r1, long r2, String r4) {
        r0.lambda$showStarsTopup$23(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$pqPBb8P-bq0BWXSSiFVXBffG9o8, reason: not valid java name */
    public static /* synthetic */ void m19137$r8$lambda$pqPBb8Pbq0BWXSSiFVXBffG9o8(StarsController r0, TLRPC.TL_error r1, Utilities.Callback2 r2, TLObject r3, TLRPC.TL_inputInvoiceStars r4) {
        r0.lambda$buy$27(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$qE9Zs7WyGEKPaMBs3-dblGFThSk, reason: not valid java name */
    public static /* synthetic */ void m19138$r8$lambda$qE9Zs7WyGEKPaMBs3dblGFThSk(TLObject r1, ProductDetails r2, BillingResult r3, Utilities.Callback2 r4, Activity r5, TLRPC.TL_inputStorePaymentStarsGiveaway r6, List r7, TLRPC.TL_error r8) {
        if ((r1 instanceof TLRPC.TL_boolTrue) == false) goto L7;
        BillingController.getInstance().addResultListener(r2.getProductId(), new StarsController$$ExternalSyntheticLambda149(r3, r4));
        BillingController.getInstance().setOnCanceled(new StarsController$$ExternalSyntheticLambda150(r4));
        BillingController.getInstance().launchBillingFlow(r5, AccountInstance.getInstance(UserConfig.selectedAccount), r6, Collections.singletonList(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails((ProductDetails) r7.get(0)).build()));
        return;
    L7:
        if ((r1 instanceof TLRPC.TL_boolFalse) == false) goto L11;
        if (r4 == null) goto L18;
        r4.run(Boolean.FALSE, "PURCHASE_FORBIDDEN");
        return;
    L18:
        return;
    L11:
        if (r4 == null) goto L19;
        Boolean r12 = Boolean.FALSE;
        if (r8 == null) goto L15;
        String r22 = r8.text;
    L16:
        r4.run(r12, r22);
        return;
    L15:
        r22 = "SERVER_ERROR";
        goto L16
    }

    public static /* synthetic */ void $r8$lambda$qQaSoh4tsDlXULA6tHmBOxJ7qqw(Utilities.Callback2 r3, BillingResult r4) {
        if (r4.getResponseCode() != 0) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
        String r42 = null;
    L9:
        FileLog.d("StarsController.buy onResult " + r0 + " " + r42);
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda113(r3, r0, r42));
        return;
    L8:
        r42 = BillingController.getResponseCodeString(r4.getResponseCode());
        goto L9
    L5:
        r0 = false;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$qaZhNMyaBRwavXx4O2exj4oyI3g(StarsController r0, TLObject r1) {
        r0.lambda$getGiveawayOptions$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$r4b2cuGA4duXl6gnBi77d8Tcdtw(Utilities.Callback2 r1, TLRPC.TL_inputStorePaymentStarsTopup r2, TL_stars.TL_starsTopupOption r3, Activity r4, BillingResult r5, List r6) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda74(r6, r1, r2, r3, r4));
    }

    public static /* synthetic */ void $r8$lambda$siQ4nEhnubXG2m33E6fPnGuzL5s(Utilities.Callback2 r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == true) goto L8;
        r0.run(Boolean.FALSE, null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$swQ3YDSqIPiDQl_fSK_zOEXtQpE(StarsController r0, TLObject r1, TLRPC.TL_error r2, Utilities.Callback2 r3, Context r4, Theme.ResourcesProvider r5, long r6, String r8, TL_stars.StarGift r9, boolean r10, boolean r11, long r12, TLRPC.TL_textWithEntities r14) {
        r0.lambda$buyStarGift$130(r1, r2, r3, r4, r5, r6, r8, r9, r10, r11, r12, r14);
    }

    public static /* synthetic */ void $r8$lambda$t_lbeMJU6DfTseFmGxNfMyobj0Q(StarsController r0, TLObject r1, MessageObject r2, TLRPC.TL_inputInvoiceMessage r3, Runnable r4, TLRPC.TL_error r5) {
        r0.lambda$pay$63(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$tj1ESstrX9Zd2qx9NoxPX6tjQQ4(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, null);
    }

    public static /* synthetic */ void $r8$lambda$tqy42fpvzOBY6_NH5yWEoodkogw(TLObject r2, Utilities.Callback r3) {
        if ((r2 instanceof TL_account.paidMessagesRevenue) == false) goto L6;
        r3.run(Long.valueOf(((TL_account.paidMessagesRevenue) r2).stars_amount));
        return;
    L6:
        r3.run(0L);
    }

    /* renamed from: $r8$lambda$vvnJe4bye-_RFWwmu2QObpAyGpU, reason: not valid java name */
    public static /* synthetic */ void m19139$r8$lambda$vvnJe4bye_RFWwmu2QObpAyGpU(StarsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$getGiveawayOptions$16(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$w3uCbCgIVymCvMeyjOt1SK6qFro(StarsController r0, long r1, int r3, boolean[] r4, Utilities.Callback2 r5, Context r6, Theme.ResourcesProvider r7, TLRPC.ChatInvite r8, String r9, Utilities.Callback r10) {
        r0.lambda$subscribeTo$77(r1, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$wJQar96j6YtvG_3jhJiROy7mhts(Utilities.Callback2 r2, PaymentFormActivity.InvoiceStatus r3) {
        if (r3 != PaymentFormActivity.InvoiceStatus.PAID) goto L8;
        if (r2 == null) goto L12;
        r2.run(Boolean.TRUE, null);
        return;
    L12:
        return;
    L8:
        if (r3 == PaymentFormActivity.InvoiceStatus.PENDING) goto L13;
        if (r2 == null) goto L14;
        r2.run(Boolean.FALSE, null);
        return;
    L14:
        return;
    }

    /* renamed from: $r8$lambda$wNJonyjdy-9Z4FZ9RC6-80nAQhM, reason: not valid java name */
    public static /* synthetic */ void m19140$r8$lambda$wNJonyjdy9Z4FZ9RC680nAQhM(Utilities.Callback2 r0, Long r1, Boolean r2) {
        if (r0 == null) goto L5;
        r0.run(r1, r2);
        return;
    }

    /* renamed from: $r8$lambda$xDn315W0NC2tnKniVlDn--ecfB8, reason: not valid java name */
    public static /* synthetic */ void m19141$r8$lambda$xDn315W0NC2tnKniVlDnecfB8(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, null);
    }

    public static /* synthetic */ void $r8$lambda$xrNbFiW4oXBBye9c7f_zWP1XqeA(StarsController r0, TLObject r1) {
        r0.lambda$getOptions$5(r1);
    }

    /* renamed from: $r8$lambda$y7shBcRNfPCmNZGOmag_-kvKwQ4, reason: not valid java name */
    public static /* synthetic */ void m19142$r8$lambda$y7shBcRNfPCmNZGOmag_kvKwQ4(StarsController r0, ArrayList r1) {
        r0.lambda$getGiftOptions$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$yDuRaK8CL63KxHsj1QOfp5yqfyU(StarsController r0, BillingResult r1, List r2, ArrayList r3) {
        r0.lambda$getOptions$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$yE40G0iQ6nMoeT79S7W0VJZXm2I(StarsController r0, long r1, Utilities.Callback r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$getStarGiftPreview$144(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$yV6q7CdJK6WXDtnzZld0gtRFjbE(Utilities.Callback r1, boolean[] r2, Utilities.Callback2 r3, Long r4, Boolean r5) {
        if (r1 == null) goto L4;
        r1.run(Boolean.TRUE);
    L4:
        r2[0] = true;
        if (r3 != null) goto L7;
        return;
    L7:
        if (r5.booleanValue() == false) goto L9;
        String r12 = "paid";
    L10:
        r3.run(r12, r4);
        return;
    L9:
        r12 = "failed";
        goto L10
    }

    public static /* synthetic */ void $r8$lambda$z2Cz6VDLPoS8iBsgp4ssRzpFdH4(ProfileActivity r2) {
        SharedMediaLayout r0 = r2.sharedMediaLayout;
        if (r0 == null) goto L6;
        r0.scrollToPage(14);
        r2.scrollToSharedMedia();
        return;
    }

    public static /* synthetic */ void $r8$lambda$zKHmMPyFpZ3ZGocqVqOpug9MBVg(Utilities.Callback2 r2) {
        r2.run(Boolean.FALSE, "PRODUCT_NOT_FOUND");
    }

    public static /* synthetic */ void $r8$lambda$zKrkLwop5mhiqhGWaPaYwWTOJVE(StarsController r0, TLObject r1, long r2, Utilities.Callback r4) {
        r0.lambda$getStarGiftPreview$143(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$zeDj-woRmH29ug1SxAE-dPkheeI, reason: not valid java name */
    public static /* synthetic */ void m19143$r8$lambda$zeDjwoRmH29ug1SxAEdPkheeI(StarsController r0, boolean[] r1, MessageObject r2, TLRPC.InputInvoice r3, TLRPC.TL_payments_paymentFormStars r4, boolean[] r5, int r6, Utilities.Callback r7, Utilities.Callback r8) {
        r0.lambda$openPaymentForm$68(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$znJy1AHu4aZaErIcGD--MC8oLPo, reason: not valid java name */
    public static /* synthetic */ void m19144$r8$lambda$znJy1AHu4aZaErIcGDMC8oLPo(boolean[] r2, Utilities.Callback2 r3, Utilities.Callback r4, Long r5, Boolean r6) {
        r2[0] = true;
        if (r3 != null) goto L5;
    L9:
        if (r4 == null) goto L12;
        r4.run(Boolean.TRUE);
        return;
    L12:
        return;
    L5:
        if (r6.booleanValue() == false) goto L7;
        String r22 = "paid";
    L8:
        r3.run(r22, r5);
        goto L9
    L7:
        r22 = "failed";
        goto L8
    }

    static {
        Instance = (StarsController[][]) Array.newInstance(StarsController.class, new int[]{2, 16});
        lockObjects = (Object[][]) Array.newInstance(Object.class, new int[]{2, 16});
        int r1 = 0;
    L3:
        if (r1 >= 2) goto L8;
        int r2 = 0;
    L5:
        if (r2 >= 16) goto L7;
        lockObjects[r1][r2] = new Object();
        r2 = r2 + 1;
        goto L5
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private StarsController(int r4, boolean r5) {
        this.balance = TL_stars.StarsAmount.ofStars(0);
        this.transactions = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList()};
        this.transactionsExist = new boolean[3];
        this.offset = new String[3];
        this.loading = new boolean[3];
        this.endReached = new boolean[3];
        this.subscriptions = new ArrayList();
        this.insufficientSubscriptions = new ArrayList();
        this.gifts = new ArrayList();
        this.sortedGifts = new ArrayList();
        this.birthdaySortedGifts = new ArrayList();
        this.giftCollections = new LongSparseArray();
        this.giftLists = new LongSparseArray();
        this.giftPreviews = new ConcurrentHashMap();
        this.justAgreedToNotAskDialogs = new ConcurrentHashMap();
        this.sendingMessagesCount = new ConcurrentHashMap();
        this.sendingPaidMessagesIds = Collections.newSetFromMap(new ConcurrentHashMap());
        this.postponedPaidMessages = new ConcurrentHashMap();
        this.currentAccount = r4;
        this.ton = r5;
    }

    private void bulletinError(String r3) {
        BaseFragment r2 = LaunchActivity.getLastFragment();
        if (r2 != null) goto L5;
    L7:
        BulletinFactory r22 = BulletinFactory.global();
    L8:
        r22.createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r3})).show();
        return;
    L5:
        if (r2.visibleDialog != null) goto L7;
        r22 = BulletinFactory.of(r2);
        goto L8
    }

    private void bulletinError(TLRPC.TL_error r1, String r2) {
        if (r1 == null) goto L5;
        r2 = r1.text;
    L5:
        bulletinError(r2);
    }

    public static boolean eq(TL_stars.SavedStarGift r5, TL_stars.SavedStarGift r6) {
        int r0 = r5.flags;
        if ((r0 & 2048) == 0) goto L9;
        if ((r6.flags & 2048) == 0) goto L9;
        if (r5.saved_id != r6.saved_id) goto L9;
        return true;
    L9:
        if ((r0 & 8) != 0) goto L11;
        return false;
    L11:
        if ((r6.flags & 8) != 0) goto L13;
        return false;
    L13:
        if (r5.msg_id != r6.msg_id) goto L20;
        return true;
    L20:
        return false;
    }

    public static <T extends TL_stars.StarGiftAttribute> T findAttribute(ArrayList<TL_stars.StarGiftAttribute> r5, Class<T> r6) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        TL_stars.StarGiftAttribute r3 = r5.get(r2);
        r2 = r2 + 1;
        TL_stars.StarGiftAttribute r32 = r3;
        if (r6.isInstance(r32) == false) goto L6;
        return r6.cast(r32);
    L11:
        return null;
    }

    public static <T extends TL_stars.StarGiftAttribute> ArrayList<T> findAttributes(ArrayList<TL_stars.StarGiftAttribute> r5, Class<T> r6) {
        ArrayList<T> r0 = new ArrayList();
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        TL_stars.StarGiftAttribute r3 = r5.get(r2);
        r2 = r2 + 1;
        TL_stars.StarGiftAttribute r32 = r3;
        if (r6.isInstance(r32) == false) goto L3;
        r0.add(r6.cast(r32));
        goto L3
    L7:
        return r0;
    }

    public static long getAllowedPaidStars(TLObject r4) {
        if ((r4 instanceof TLRPC.TL_messages_sendMessage) == false) goto L7;
        return ((TLRPC.TL_messages_sendMessage) r4).allow_paid_stars;
    L7:
        if ((r4 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L11;
        return ((TLRPC.TL_messages_sendMultiMedia) r4).allow_paid_stars;
    L11:
        if ((r4 instanceof TLRPC.TL_messages_sendInlineBotResult) == false) goto L15;
        return ((TLRPC.TL_messages_sendInlineBotResult) r4).allow_paid_stars;
    L15:
        if ((r4 instanceof TLRPC.TL_messages_forwardMessages) == false) goto L19;
        return ((TLRPC.TL_messages_forwardMessages) r4).allow_paid_stars / r4.id.size();
    L19:
        if ((r4 instanceof TLRPC.TL_messages_sendMedia) == true) goto L21;
        return 0;
    L21:
        return ((TLRPC.TL_messages_sendMedia) r4).allow_paid_stars;
    }

    public static long getFormStarsPrice(TLRPC.PaymentForm r6) {
        long r0 = 0;
        if (r6 == null) goto L7;
        ArrayList<TLRPC.TL_labeledPrice> r62 = r6.invoice.prices;
        int r2 = r62.size();
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L7;
        TLRPC.TL_labeledPrice r4 = r62.get(r3);
        r3 = r3 + 1;
        r0 = r0 + r4.amount;
    L7:
        return r0;
    }

    public static StarsController getInstance(int r1) {
        return getInstance(r1, false);
    }

    public static StarsController getInstance(int r1, AmountUtils$Currency r2) {
        if (r2 != AmountUtils$Currency.TON) goto L5;
        boolean r22 = true;
    L7:
        return getInstance(r1, r22);
    L5:
        r22 = false;
        goto L7
    }

    public static StarsController getInstance(int r3, boolean r4) {
        StarsController r0 = Instance[r4 ? 1 : 0][r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r4 ? 1 : 0][r3];
        monitor-enter(r1);
        StarsController r02 = Instance[r4 ? 1 : 0][r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        StarsController[] r03 = Instance[r4 ? 1 : 0];     // Catch: Throwable -> L9
        StarsController r2 = new StarsController(r3, r4);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r0;
    }

    public static long getPeer(TLObject r2) {
        if ((r2 instanceof TLRPC.TL_messages_sendMessage) == false) goto L7;
        return DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendMessage) r2).peer);
    L7:
        if ((r2 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L11;
        return DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendMultiMedia) r2).peer);
    L11:
        if ((r2 instanceof TLRPC.TL_messages_sendInlineBotResult) == false) goto L15;
        return DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendInlineBotResult) r2).peer);
    L15:
        if ((r2 instanceof TLRPC.TL_messages_forwardMessages) == false) goto L19;
        return DialogObject.getPeerDialogId(((TLRPC.TL_messages_forwardMessages) r2).to_peer);
    L19:
        if ((r2 instanceof TLRPC.TL_messages_sendMedia) == true) goto L21;
        return 0;
    L21:
        return DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendMedia) r2).peer);
    }

    private void getStarGiftsCached(Utilities.Callback5<ArrayList<TL_stars.StarGift>, Integer, Long, ArrayList<TLRPC.User>, ArrayList<TLRPC.Chat>> r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        ArrayList r4 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList r2 = new ArrayList();
        MessagesStorage r1 = MessagesStorage.getInstance(this.currentAccount);
        r1.getStorageQueue().postRunnable(new StarsController$$ExternalSyntheticLambda48(r1, r2, r3, r4, r7));
    }

    private void getStarGiftsRemote(int r2, Utilities.Callback<TL_stars.StarGifts> r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        TL_stars.getStarGifts r0 = new TL_stars.getStarGifts();
        r0.hash = r2;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$$ExternalSyntheticLambda41(r3));
    }

    public static StarsController getTonInstance(int r1) {
        return getInstance(r1, true);
    }

    public static boolean isEnoughAmount(int r3, AmountUtils$Amount r4) {
        if (r4 != null) goto L6;
        return true;
    L6:
        if (getInstance(r3, r4.currency).getBalanceAmount().asNano() < r4.asNano()) goto L8;
        return true;
    L8:
        return false;
    }

    private boolean isInvoiceBillingDisabled(TLRPC.InputPeer r1) {
        if (AppGlobalConfig.getInstance(this.currentAccount).starsSpendTopUpInvoiceDisabled.get() == false) goto L7;
        if (r1 == null) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    private /* synthetic */ void lambda$beforeSendingFinalRequest$153(HashSet r3, Runnable r4) {
        Iterator r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Integer r0 = (Integer) r32.next();
        r0.intValue();
        this.sendingPaidMessagesIds.remove(r0);
        this.postponedPaidMessages.remove(r0);
        goto L4
    L6:
        r4.run();
    }

    private /* synthetic */ void lambda$beforeSendingMessage$151(boolean r1, HashSet r2) {
        if (r1 == true) goto L4;
        return;
    L4:
        SendMessagesHelper.getInstance(this.currentAccount).cancelSendingMessage(new ArrayList(r2));
    }

    private /* synthetic */ void lambda$beforeSendingMessage$152(boolean r2, int r3) {
        if (r2 == false) goto L9;
        this.sendingPaidMessagesIds.remove(Integer.valueOf(r3));
        Runnable r1 = this.postponedPaidMessages.remove(Integer.valueOf(r3));
        if (r1 == null) goto L8;
        r1.run();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$buy$27(TLRPC.TL_error r4, Utilities.Callback2 r5, TLObject r6, TLRPC.TL_inputInvoiceStars r7) {
        if (r4 == null) goto L6;
        if (r5 == null) goto L25;
        r5.run(Boolean.FALSE, r4.text);
        return;
    L25:
        return;
    L6:
        PaymentFormActivity r2 = null;
        if ((r6 instanceof TLRPC.PaymentForm) == false) goto L10;
        TLRPC.PaymentForm r62 = (TLRPC.PaymentForm) r6;
        r62.invoice.recurring = true;
        MessagesController.getInstance(this.currentAccount).putUsers(r62.users, false);
        r2 = new PaymentFormActivity(r62, r7, null);
    L12:
        if (r2 == null) goto L22;
        r2.setPaymentFormCallback(new StarsController$$ExternalSyntheticLambda108(r5));
        BaseFragment r3 = LaunchActivity.getLastFragment();
        if (r3 != null) goto L17;
        return;
    L17:
        if (AndroidUtilities.hasDialogOnTop(r3) == false) goto L20;
        BaseFragment.BottomSheetParams r42 = new BaseFragment.BottomSheetParams();
        r42.transitionFromLeft = true;
        r42.allowNestedScroll = false;
        r3.showAsSheet(r2, r42);
        return;
    L20:
        r3.presentFragment(r2);
        return;
    L22:
        if (r5 == null) goto L26;
        r5.run(Boolean.FALSE, "UNKNOWN_RESPONSE");
        return;
    L26:
        return;
    L10:
        if ((r6 instanceof TLRPC.PaymentReceipt) == false) goto L12;
        r2 = new PaymentFormActivity((TLRPC.PaymentReceipt) r6);
        goto L12
    }

    private /* synthetic */ void lambda$buy$28(Utilities.Callback2 r7, TLRPC.TL_inputInvoiceStars r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda73(this, r10, r7, r9, r8));
    }

    private /* synthetic */ void lambda$buyGift$38(TLRPC.TL_error r4, Utilities.Callback2 r5, TLObject r6, TLRPC.TL_inputInvoiceStars r7) {
        if (r4 == null) goto L6;
        if (r5 == null) goto L25;
        r5.run(Boolean.FALSE, r4.text);
        return;
    L25:
        return;
    L6:
        PaymentFormActivity r2 = null;
        if ((r6 instanceof TLRPC.PaymentForm) == false) goto L10;
        TLRPC.PaymentForm r62 = (TLRPC.PaymentForm) r6;
        r62.invoice.recurring = true;
        MessagesController.getInstance(this.currentAccount).putUsers(r62.users, false);
        r2 = new PaymentFormActivity(r62, r7, null);
    L12:
        if (r2 == null) goto L22;
        r2.setPaymentFormCallback(new StarsController$$ExternalSyntheticLambda132(r5));
        BaseFragment r3 = LaunchActivity.getLastFragment();
        if (r3 != null) goto L17;
        return;
    L17:
        if (AndroidUtilities.hasDialogOnTop(r3) == false) goto L20;
        BaseFragment.BottomSheetParams r42 = new BaseFragment.BottomSheetParams();
        r42.transitionFromLeft = true;
        r42.allowNestedScroll = false;
        r3.showAsSheet(r2, r42);
        return;
    L20:
        r3.presentFragment(r2);
        return;
    L22:
        if (r5 == null) goto L26;
        r5.run(Boolean.FALSE, "UNKNOWN_RESPONSE");
        return;
    L26:
        return;
    L10:
        if ((r6 instanceof TLRPC.PaymentReceipt) == false) goto L12;
        r2 = new PaymentFormActivity((TLRPC.PaymentReceipt) r6);
        goto L12
    }

    private /* synthetic */ void lambda$buyGift$39(Utilities.Callback2 r7, TLRPC.TL_inputInvoiceStars r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda124(this, r10, r7, r9, r8));
    }

    private /* synthetic */ void lambda$buyGift$48(List r8, Utilities.Callback2 r9, TLRPC.TL_inputStorePaymentStarsGift r10, TL_stars.TL_starsGiftOption r11, BillingResult r12, Activity r13) {
        if (r8.isEmpty() == false) goto L6;
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda134(r9));
        return;
    L6:
        ProductDetails r0 = (ProductDetails) r8.get(0);
        ProductDetails.OneTimePurchaseOfferDetails r1 = r0.getOneTimePurchaseOfferDetails();
        if (r1 != null) goto L10;
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda135(r9));
        return;
    L10:
        r10.currency = r1.getPriceCurrencyCode();
        r10.amount = (long) ((r1.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r11.currency)));
        TLRPC.TL_payments_canPurchaseStore r14 = new TLRPC.TL_payments_canPurchaseStore();
        r14.purpose = r10;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r14, new StarsController$$ExternalSyntheticLambda136(r0, r12, r9, r13, r10, r8));
    }

    private /* synthetic */ void lambda$buyGift$49(Utilities.Callback2 r9, TLRPC.TL_inputStorePaymentStarsGift r10, TL_stars.TL_starsGiftOption r11, Activity r12, BillingResult r13, List r14) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda129(this, r14, r9, r10, r11, r13, r12));
    }

    private /* synthetic */ void lambda$buyGiveaway$51(TLRPC.TL_error r4, Utilities.Callback2 r5, TLObject r6, TLRPC.TL_inputInvoiceStars r7) {
        if (r4 == null) goto L6;
        if (r5 == null) goto L25;
        r5.run(Boolean.FALSE, r4.text);
        return;
    L25:
        return;
    L6:
        PaymentFormActivity r2 = null;
        if ((r6 instanceof TLRPC.PaymentForm) == false) goto L10;
        TLRPC.PaymentForm r62 = (TLRPC.PaymentForm) r6;
        r62.invoice.recurring = true;
        MessagesController.getInstance(this.currentAccount).putUsers(r62.users, false);
        r2 = new PaymentFormActivity(r62, r7, null);
    L12:
        if (r2 == null) goto L22;
        r2.setPaymentFormCallback(new StarsController$$ExternalSyntheticLambda119(r5));
        BaseFragment r3 = LaunchActivity.getLastFragment();
        if (r3 != null) goto L17;
        return;
    L17:
        if (AndroidUtilities.hasDialogOnTop(r3) == false) goto L20;
        BaseFragment.BottomSheetParams r42 = new BaseFragment.BottomSheetParams();
        r42.transitionFromLeft = true;
        r42.allowNestedScroll = false;
        r3.showAsSheet(r2, r42);
        return;
    L20:
        r3.presentFragment(r2);
        return;
    L22:
        if (r5 == null) goto L26;
        r5.run(Boolean.FALSE, "UNKNOWN_RESPONSE");
        return;
    L26:
        return;
    L10:
        if ((r6 instanceof TLRPC.PaymentReceipt) == false) goto L12;
        r2 = new PaymentFormActivity((TLRPC.PaymentReceipt) r6);
        goto L12
    }

    private /* synthetic */ void lambda$buyGiveaway$52(Utilities.Callback2 r7, TLRPC.TL_inputInvoiceStars r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda102(this, r10, r7, r9, r8));
    }

    private /* synthetic */ void lambda$buyGiveaway$61(List r9, Utilities.Callback2 r10, TLRPC.TL_inputStorePaymentStarsGiveaway r11, BillingResult r12, Activity r13) {
        if (r9.isEmpty() == false) goto L6;
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda121(r10));
        return;
    L6:
        ProductDetails r2 = (ProductDetails) r9.get(0);
        if (r2.getOneTimePurchaseOfferDetails() != null) goto L10;
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda122(r10));
        return;
    L10:
        TLRPC.TL_payments_canPurchaseStore r0 = new TLRPC.TL_payments_canPurchaseStore();
        r0.purpose = r11;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$$ExternalSyntheticLambda123(r2, r12, r10, r13, r11, r9));
    }

    private /* synthetic */ void lambda$buyGiveaway$62(Utilities.Callback2 r8, TLRPC.TL_inputStorePaymentStarsGiveaway r9, Activity r10, BillingResult r11, List r12) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda107(this, r12, r8, r9, r11, r10));
    }

    private /* synthetic */ void lambda$buyPremiumGift$115(Utilities.Callback2 r7, long r8, Object r10, TLRPC.TL_textWithEntities r11) {
        if (balanceAvailable() == true) goto L8;
        bulletinError("NO_BALANCE");
        if (r7 == null) goto L10;
        r7.run(Boolean.FALSE, null);
        return;
    L10:
        return;
    L8:
        buyPremiumGift(r8, r10, r11, r7);
    }

    private /* synthetic */ void lambda$buyPremiumGift$116(boolean[] r3, long r4, Object r6, TLRPC.TL_textWithEntities r7, Utilities.Callback2 r8) {
        r3[0] = true;
        buyPremiumGift(r4, r6, r7, r8);
    }

    private /* synthetic */ void lambda$buyPremiumGift$118(TLRPC.TL_payments_paymentResult r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2.updates, false);
    }

    private /* synthetic */ void lambda$buyPremiumGift$119(TLObject r17, TLRPC.TL_error r18, Utilities.Callback2 r19, Context r20, Theme.ResourcesProvider r21, long r22, String r24, long r25, Object r27, TLRPC.TL_textWithEntities r28) {
        BaseFragment r3 = LaunchActivity.getLastFragment();
        if (r3 != null) goto L5;
    L7:
        BulletinFactory r32 = BulletinFactory.global();
    L9:
        if ((r17 instanceof TLRPC.TL_payments_paymentResult) == true) goto L36;
        if (r18 == null) goto L22;
        if ("BALANCE_TOO_LOW".equals(r18.text) == false) goto L22;
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L19;
        if (r19 == null) goto L17;
        r19.run(Boolean.FALSE, null);
    L17:
        showNoSupportDialog(r20, r21);
        return;
    L19:
        boolean[] r2 = {false};
        StarsIntroActivity.StarsNeededSheet r8 = new StarsIntroActivity.StarsNeededSheet(r20, r21, r22, 6, r24, new StarsController$$ExternalSyntheticLambda18(this, r2, r25, r27, r28, r19), 0);
        r8.setOnDismissListener(new StarsController$$ExternalSyntheticLambda19(r19, r2));
        r8.show();
        return;
    L22:
        if (r18 != null) goto L24;
    L28:
        if (r19 == null) goto L30;
        r19.run(Boolean.FALSE, null);
    L30:
        int r0 = R.raw.error;
        int r1 = R.string.UnknownErrorCode;
        if (r18 == null) goto L33;
        String r23 = r18.text;
    L34:
        r32.createSimpleBulletin(r0, LocaleController.formatString(r1, new Object[]{r23})).show();
        return;
    L33:
        r23 = "FAILED_SEND_STARS";
        goto L34
    L24:
        if ("STARGIFT_USAGE_LIMITED".equals(r18.text) == false) goto L28;
        if (r19 == null) goto L48;
        r19.run(Boolean.FALSE, "STARGIFT_USAGE_LIMITED");
        return;
    L48:
        return;
    L36:
        Utilities.stageQueue.postRunnable(new StarsController$$ExternalSyntheticLambda20(this, (TLRPC.TL_payments_paymentResult) r17));
        invalidateTransactions(true);
        if (r19 == null) goto L40;
        r19.run(Boolean.TRUE, null);
    L40:
        if (BirthdayController.getInstance(this.currentAccount).contains(r25) == false) goto L42;
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putBoolean(Calendar.getInstance().get(1) + "bdayhint_" + r25, false).apply();
    L42:
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putBoolean("show_gift_for_" + r25, true).putBoolean(Calendar.getInstance().get(1) + "show_gift_for_" + r25, true).apply();
        LaunchActivity r02 = LaunchActivity.instance;
        if (r02 != null) goto L45;
        return;
    L45:
        if (r02.getFireworksOverlay() == null) goto L50;
        LaunchActivity.instance.getFireworksOverlay().start(true);
        return;
    L50:
        return;
    L5:
        if (r3.visibleDialog != null) goto L7;
        r32 = BulletinFactory.of(r3);
        goto L9
    }

    private /* synthetic */ void lambda$buyPremiumGift$120(Utilities.Callback2 r15, Context r16, Theme.ResourcesProvider r17, long r18, String r20, long r21, Object r23, TLRPC.TL_textWithEntities r24, TLObject r25, TLRPC.TL_error r26) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda1(this, r25, r26, r15, r16, r17, r18, r20, r21, r23, r24));
    }

    private /* synthetic */ void lambda$buyPremiumGift$121(TLObject r17, TLRPC.TL_error r18, Utilities.Callback2 r19, TLRPC.TL_inputInvoicePremiumGiftStars r20, Context r21, Theme.ResourcesProvider r22, String r23, long r24, Object r26, TLRPC.TL_textWithEntities r27) {
        if ((r17 instanceof TLRPC.TL_payments_paymentFormStars) == true) goto L6;
        bulletinError(r18, "NO_PAYMENT_FORM");
        r19.run(Boolean.FALSE, null);
        return;
    L6:
        TLRPC.TL_payments_paymentFormStars r0 = (TLRPC.TL_payments_paymentFormStars) r17;
        TL_stars.TL_payments_sendStarsForm r12 = new TL_stars.TL_payments_sendStarsForm();
        r12.form_id = r0.form_id;
        r12.invoice = r20;
        ArrayList<TLRPC.TL_labeledPrice> r02 = r0.invoice.prices;
        int r3 = r02.size();
        int r4 = 0;
        long r5 = 0;
    L7:
        if (r4 >= r3) goto L9;
        TLRPC.TL_labeledPrice r7 = r02.get(r4);
        r4 = r4 + 1;
        r5 = r5 + r7.amount;
        goto L7
    L9:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new StarsController$$ExternalSyntheticLambda147(this, r19, r21, r22, r5, r23, r24, r26, r27));
    }

    private /* synthetic */ void lambda$buyPremiumGift$122(Utilities.Callback2 r14, TLRPC.TL_inputInvoicePremiumGiftStars r15, Context r16, Theme.ResourcesProvider r17, String r18, long r19, Object r21, TLRPC.TL_textWithEntities r22, TLObject r23, TLRPC.TL_error r24) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda138(this, r23, r24, r14, r15, r16, r17, r18, r19, r21, r22));
    }

    private /* synthetic */ void lambda$buyResellingGift$137(Utilities.Callback2 r7, TLRPC.TL_payments_paymentFormStarGift r8, TL_stars.StarGift r9, long r10) {
        if (balanceAvailable() == true) goto L8;
        bulletinError("NO_BALANCE");
        if (r7 == null) goto L10;
        r7.run(Boolean.FALSE, null);
        return;
    L10:
        return;
    L8:
        buyResellingGift(r8, r9, r10, r7);
    }

    private /* synthetic */ void lambda$buyResellingGift$138(boolean[] r3, TLRPC.TL_payments_paymentFormStarGift r4, TL_stars.StarGift r5, long r6, Utilities.Callback2 r8) {
        r3[0] = true;
        buyResellingGift(r4, r5, r6, r8);
    }

    private /* synthetic */ void lambda$buyResellingGift$140(TLRPC.TL_payments_paymentResult r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2.updates, false);
    }

    private /* synthetic */ void lambda$buyResellingGift$141(TLObject r17, TLRPC.TL_error r18, Utilities.Callback2 r19, Context r20, Theme.ResourcesProvider r21, long r22, String r24, TLRPC.TL_payments_paymentFormStarGift r25, TL_stars.StarGift r26, long r27) {
        BaseFragment r3 = LaunchActivity.getLastFragment();
        if (r3 != null) goto L5;
    L7:
        BulletinFactory r32 = BulletinFactory.global();
    L9:
        if ((r17 instanceof TLRPC.TL_payments_paymentResult) == true) goto L36;
        if (r18 == null) goto L22;
        if ("BALANCE_TOO_LOW".equals(r18.text) == false) goto L22;
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L19;
        if (r19 == null) goto L17;
        r19.run(Boolean.FALSE, null);
    L17:
        showNoSupportDialog(r20, r21);
        return;
    L19:
        boolean[] r2 = {false};
        StarsIntroActivity.StarsNeededSheet r8 = new StarsIntroActivity.StarsNeededSheet(r20, r21, r22, 6, r24, new StarsController$$ExternalSyntheticLambda3(this, r2, r25, r26, r27, r19), 0);
        r8.setOnDismissListener(new StarsController$$ExternalSyntheticLambda4(r19, r2));
        r8.show();
        return;
    L22:
        if (r18 != null) goto L24;
    L28:
        if (r19 == null) goto L30;
        r19.run(Boolean.FALSE, null);
    L30:
        int r0 = R.raw.error;
        int r1 = R.string.UnknownErrorCode;
        if (r18 == null) goto L33;
        String r23 = r18.text;
    L34:
        r32.createSimpleBulletin(r0, LocaleController.formatString(r1, new Object[]{r23})).show();
        return;
    L33:
        r23 = "FAILED_SEND_STARS";
        goto L34
    L24:
        if ("STARGIFT_USAGE_LIMITED".equals(r18.text) == false) goto L28;
        if (r19 == null) goto L48;
        r19.run(Boolean.FALSE, "STARGIFT_USAGE_LIMITED");
        return;
    L48:
        return;
    L36:
        Utilities.stageQueue.postRunnable(new StarsController$$ExternalSyntheticLambda5(this, (TLRPC.TL_payments_paymentResult) r17));
        invalidateStarGifts();
        invalidateProfileGifts(r27);
        invalidateTransactions(true);
        if (r19 == null) goto L40;
        r19.run(Boolean.TRUE, null);
    L40:
        if (BirthdayController.getInstance(this.currentAccount).contains(r27) == false) goto L42;
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putBoolean(Calendar.getInstance().get(1) + "bdayhint_" + r27, false).apply();
    L42:
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putBoolean("show_gift_for_" + r27, true).putBoolean(Calendar.getInstance().get(1) + "show_gift_for_" + r27, true).apply();
        LaunchActivity r02 = LaunchActivity.instance;
        if (r02 != null) goto L45;
        return;
    L45:
        if (r02.getFireworksOverlay() == null) goto L50;
        LaunchActivity.instance.getFireworksOverlay().start(true);
        return;
    L50:
        return;
    L5:
        if (r3.visibleDialog != null) goto L7;
        r32 = BulletinFactory.of(r3);
        goto L9
    }

    private /* synthetic */ void lambda$buyResellingGift$142(Utilities.Callback2 r15, Context r16, Theme.ResourcesProvider r17, long r18, String r20, TLRPC.TL_payments_paymentFormStarGift r21, TL_stars.StarGift r22, long r23, TLObject r25, TLRPC.TL_error r26) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda143(this, r25, r26, r15, r16, r17, r18, r20, r21, r22, r23));
    }

    private /* synthetic */ void lambda$buyStarGift$123(Utilities.Callback2 r9, TL_stars.StarGift r10, boolean r11, boolean r12, long r13, TLRPC.TL_textWithEntities r15) {
        if (balanceAvailable() == true) goto L8;
        bulletinError("NO_BALANCE");
        if (r9 == null) goto L10;
        r9.run(Boolean.FALSE, null);
        return;
    L10:
        return;
    L8:
        buyStarGift(r10, r11, r12, r13, r15, r9);
    }

    private /* synthetic */ void lambda$buyStarGift$124(boolean[] r3, TL_stars.StarGift r4, boolean r5, boolean r6, long r7, TLRPC.TL_textWithEntities r9, Utilities.Callback2 r10) {
        r3[0] = true;
        buyStarGift(r4, r5, r6, r7, r9, r10);
    }

    private /* synthetic */ void lambda$buyStarGift$126(TLRPC.TL_payments_paymentResult r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2.updates, false);
    }

    private /* synthetic */ void lambda$buyStarGift$130(TLObject r19, TLRPC.TL_error r20, Utilities.Callback2 r21, Context r22, Theme.ResourcesProvider r23, long r24, String r26, TL_stars.StarGift r27, boolean r28, boolean r29, long r30, TLRPC.TL_textWithEntities r32) {
        BaseFragment r7 = LaunchActivity.getLastFragment();
        if (r7 != null) goto L5;
    L7:
        BulletinFactory r3 = BulletinFactory.global();
    L8:
        SpannableStringBuilder r5 = null;
        if ((r19 instanceof TLRPC.TL_payments_paymentResult) == true) goto L42;
        if (r20 == null) goto L22;
        if ("BALANCE_TOO_LOW".equals(r20.text) == false) goto L22;
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L19;
        if (r21 == null) goto L17;
        r21.run(Boolean.FALSE, null);
    L17:
        showNoSupportDialog(r22, r23);
        return;
    L19:
        boolean[] r2 = {false};
        StarsIntroActivity.StarsNeededSheet r10 = new StarsIntroActivity.StarsNeededSheet(r22, r23, r24, 6, r26, new StarsController$$ExternalSyntheticLambda13(this, r2, r27, r28, r29, r30, r32, r21), 0);
        r10.setOnDismissListener(new StarsController$$ExternalSyntheticLambda14(r21, r2));
        r10.show();
        return;
    L22:
        if (r20 != null) goto L24;
    L28:
        if (r20 != null) goto L30;
    L34:
        if (r21 == null) goto L36;
        r21.run(Boolean.FALSE, null);
    L36:
        int r0 = R.raw.error;
        int r1 = R.string.UnknownErrorCode;
        if (r20 == null) goto L39;
        String r25 = r20.text;
    L40:
        r3.createSimpleBulletin(r0, LocaleController.formatString(r1, new Object[]{r25})).show();
        return;
    L39:
        r25 = "FAILED_SEND_STARS";
        goto L40
    L30:
        if ("STARGIFT_USER_USAGE_LIMITED".equals(r20.text) == false) goto L34;
        if (r21 == null) goto L87;
        r21.run(Boolean.FALSE, "STARGIFT_USER_USAGE_LIMITED");
        return;
    L87:
        return;
    L24:
        if ("STARGIFT_USAGE_LIMITED".equals(r20.text) == false) goto L28;
        if (r21 == null) goto L86;
        r21.run(Boolean.FALSE, "STARGIFT_USAGE_LIMITED");
        return;
    L86:
        return;
    L42:
        Utilities.stageQueue.postRunnable(new StarsController$$ExternalSyntheticLambda15(this, (TLRPC.TL_payments_paymentResult) r19));
        invalidateStarGifts();
        invalidateProfileGifts(r30);
        invalidateTransactions(true);
        if (r21 == null) goto L46;
        r21.run(Boolean.TRUE, null);
    L46:
        if (BirthdayController.getInstance(this.currentAccount).contains(r30) == false) goto L48;
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putBoolean(Calendar.getInstance().get(1) + "bdayhint_" + r30, false).apply();
    L48:
        if (r27 == null) goto L53;
        if (r27.limited_per_user == false) goto L53;
        int r02 = r27.per_user_remains - 1;
        r27.per_user_remains = r02;
        r5 = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2SentRemainsLimit", Math.max(0, r02)));
    L53:
        if (r30 >= 0) goto L70;
        long r102 = -r30;
        TLRPC.ChatFull r03 = MessagesController.getInstance(this.currentAccount).getChatFull(r102);
        if (r03 == null) goto L58;
        r03.stargifts_count++;
        r03.flags2 |= 262144;
        MessagesController.getInstance(this.currentAccount).putChatFull(r03);
    L58:
        if ((r7 instanceof ProfileActivity) == false) goto L69;
        ProfileActivity r04 = (ProfileActivity) r7;
        if (r04.getDialogId() != r30) goto L69;
        SharedMediaLayout r12 = r04.sharedMediaLayout;
        if (r12 == null) goto L64;
        r12.updateTabs(true);
        r04.sharedMediaLayout.scrollToPage(14);
        r04.scrollToSharedMedia();
    L64:
        BulletinFactory r05 = BulletinFactory.of(r7);
        TLRPC.Document r13 = r27.sticker;
        String r210 = LocaleController.getString(R.string.StarsGiftCompleted);
        if (r5 != null) goto L68;
        r5 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsGiftCompletedChannelText", (int) r24, new Object[]{r26}));
    L68:
        r05.createEmojiBulletin(r13, r210, r5).show(false);
    L80:
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putBoolean("show_gift_for_" + r30, true).putBoolean(Calendar.getInstance().get(1) + "show_gift_for_" + r30, true).apply();
        LaunchActivity r06 = LaunchActivity.instance;
        if (r06 != null) goto L83;
        return;
    L83:
        if (r06.getFireworksOverlay() == null) goto L89;
        LaunchActivity.instance.getFireworksOverlay().start(true);
        return;
    L89:
        return;
    L69:
        Bundle r07 = new Bundle();
        r07.putLong("chat_id", r102);
        r07.putBoolean("open_gifts", true);
        ProfileActivity r14 = new ProfileActivity(r07);
        r14.whenFullyVisible(new StarsController$$ExternalSyntheticLambda16(r14, r27, r5, r24, r26));
        r7.presentFragment(r14);
        goto L80
    L70:
        SpannableStringBuilder r33 = r5;
        if ((r7 instanceof ChatActivity) == true) goto L73;
    L79:
        NotificationCenter r08 = NotificationCenter.getInstance(this.currentAccount);
        int r15 = NotificationCenter.closeProfileActivity;
        Long r6 = Long.valueOf(r30);
        Boolean r103 = Boolean.FALSE;
        r08.postNotificationName(r15, new Object[]{r6, r103});
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChatActivity, new Object[]{Long.valueOf(r30), r103});
        ChatActivity r16 = ChatActivity.of(r30);
        r16.whenFullyVisible(new StarsController$$ExternalSyntheticLambda17(r16, r27, r33, r24));
        r7.presentFragment(r16);
        goto L80
    L73:
        if (((ChatActivity) r7).getDialogId() != r30) goto L79;
        BulletinFactory r09 = BulletinFactory.of(r7);
        TLRPC.Document r17 = r27.sticker;
        String r211 = LocaleController.getString(R.string.StarsGiftCompleted);
        if (r33 == null) goto L77;
        SpannableStringBuilder r52 = r33;
    L78:
        r09.createEmojiBulletin(r17, r211, r52).show(true);
        goto L80
    L77:
        r52 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsGiftCompletedText", (int) r24, new Object[0]));
        goto L78
    L5:
        if (r7.visibleDialog != null) goto L7;
        r3 = BulletinFactory.of(r7);
        goto L8
    }

    private /* synthetic */ void lambda$buyStarGift$131(Utilities.Callback2 r17, Context r18, Theme.ResourcesProvider r19, long r20, String r22, TL_stars.StarGift r23, boolean r24, boolean r25, long r26, TLRPC.TL_textWithEntities r28, TLObject r29, TLRPC.TL_error r30) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda8(this, r29, r30, r17, r18, r19, r20, r22, r23, r24, r25, r26, r28));
    }

    private /* synthetic */ void lambda$buyStarGift$132(TLObject r19, TLRPC.TL_error r20, Utilities.Callback2 r21, TLRPC.TL_inputInvoiceStarGift r22, Context r23, Theme.ResourcesProvider r24, String r25, TL_stars.StarGift r26, boolean r27, boolean r28, long r29, TLRPC.TL_textWithEntities r31) {
        if ((r19 instanceof TLRPC.TL_payments_paymentFormStarGift) == true) goto L6;
        bulletinError(r20, "NO_PAYMENT_FORM");
        r21.run(Boolean.FALSE, null);
        return;
    L6:
        TLRPC.TL_payments_paymentFormStarGift r0 = (TLRPC.TL_payments_paymentFormStarGift) r19;
        TL_stars.TL_payments_sendStarsForm r14 = new TL_stars.TL_payments_sendStarsForm();
        r14.form_id = r0.form_id;
        r14.invoice = r22;
        ArrayList<TLRPC.TL_labeledPrice> r02 = r0.invoice.prices;
        int r3 = r02.size();
        int r4 = 0;
        long r5 = 0;
    L7:
        if (r4 >= r3) goto L9;
        TLRPC.TL_labeledPrice r7 = r02.get(r4);
        r4 = r4 + 1;
        r5 = r5 + r7.amount;
        goto L7
    L9:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r14, new StarsController$$ExternalSyntheticLambda148(this, r21, r23, r24, r5, r25, r26, r27, r28, r29, r31));
    }

    private /* synthetic */ void lambda$buyStarGift$133(Utilities.Callback2 r16, TLRPC.TL_inputInvoiceStarGift r17, Context r18, Theme.ResourcesProvider r19, String r20, TL_stars.StarGift r21, boolean r22, boolean r23, long r24, TLRPC.TL_textWithEntities r26, TLObject r27, TLRPC.TL_error r28) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda139(this, r27, r28, r16, r17, r18, r19, r20, r21, r22, r23, r24, r26));
    }

    private /* synthetic */ void lambda$getBalance$0(TLObject r13, Runnable r14) {
        boolean r0 = !this.balanceLoaded;
        this.lastBalanceLoaded = System.currentTimeMillis();
        if ((r13 instanceof TL_stars.StarsStatus) == false) goto L49;
        TL_stars.StarsStatus r132 = (TL_stars.StarsStatus) r13;
        MessagesController.getInstance(this.currentAccount).putUsers(r132.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r132.chats, false);
        if (this.transactions[0].isEmpty() == false) goto L36;
        ArrayList<TL_stars.StarsTransaction> r2 = r132.history;
        int r6 = r2.size();
        int r7 = 0;
    L7:
        if (r7 >= r6) goto L13;
        TL_stars.StarsTransaction r8 = r2.get(r7);
        r7 = r7 + 1;
        TL_stars.StarsTransaction r82 = r8;
        this.transactions[0].add(r82);
        ArrayList<TL_stars.StarsTransaction>[] r9 = this.transactions;
        if (r82.amount.amount <= 0) goto L11;
        char r10 = 1;
    L12:
        r9[r10].add(r82);
        goto L7
    L11:
        r10 = 2;
        goto L12
    L13:
        int r22 = 0;
    L15:
        if (r22 >= 3) goto L35;
        boolean[] r62 = this.transactionsExist;
        if (this.transactions[r22].isEmpty() == true) goto L19;
    L22:
        boolean r72 = true;
    L23:
        r62[r22] = r72;
        boolean[] r63 = this.endReached;
        if ((r132.flags & 1) != 0) goto L26;
        boolean r73 = true;
    L27:
        r63[r22] = r73;
        if (r73 == false) goto L30;
        this.loading[r22] = false;
    L30:
        String[] r74 = this.offset;
        if (r63[r22] == false) goto L33;
        String r64 = null;
    L34:
        r74[r22] = r64;
        r22 = r22 + 1;
        goto L15
    L33:
        r64 = r132.next_offset;
        goto L34
    L26:
        r73 = false;
        goto L27
    L19:
        if (this.transactionsExist[r22] == true) goto L22;
        r72 = false;
        goto L23
    L35:
        boolean r23 = true;
    L38:
        if (this.subscriptions.isEmpty() == false) goto L44;
        this.subscriptions.addAll(r132.subscriptions);
        this.subscriptionsLoading = false;
        this.subscriptionsOffset = r132.subscriptions_next_offset;
        if ((r132.flags & 4) != 0) goto L42;
        boolean r65 = true;
    L43:
        this.subscriptionsEndReached = r65;
        boolean r66 = true;
    L45:
        long r75 = this.balance.amount;
        TL_stars.StarsAmount r133 = r132.balance;
        if (r75 == r133.amount) goto L48;
        r0 = true;
    L48:
        this.balance = r133;
        this.minus = 0;
    L50:
        this.balanceLoading = false;
        this.balanceLoaded = true;
        if (r0 == false) goto L53;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starBalanceUpdated, new Object[0]);
    L53:
        if (r23 == false) goto L55;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starTransactionsLoaded, new Object[0]);
    L55:
        if (r66 == false) goto L57;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starSubscriptionsLoaded, new Object[0]);
    L57:
        if (r14 == null) goto L66;
        r14.run();
        return;
    L66:
        return;
    L42:
        r65 = false;
        goto L43
    L44:
        r66 = false;
        goto L45
    L36:
        r23 = false;
        goto L38
    L49:
        r23 = false;
        r66 = false;
        goto L50
    }

    private /* synthetic */ void lambda$getBalance$1(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda32(this, r2, r1));
    }

    private /* synthetic */ void lambda$getGiftOptions$10(TLObject r9) {
        ArrayList<TL_stars.TL_starsGiftOption> r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        if ((r9 instanceof Vector) == false) goto L15;
        ArrayList<T> r92 = ((Vector) r9).objects;
        int r2 = r92.size();
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L14;
        Object r6 = r92.get(r4);
        r4 = r4 + 1;
        if ((r6 instanceof TL_stars.TL_starsGiftOption) == false) goto L6;
        TL_stars.TL_starsGiftOption r62 = (TL_stars.TL_starsGiftOption) r6;
        r0.add(r62);
        if (r62.store_product == null) goto L6;
        if (BuildVars.useInvoiceBilling() == true) goto L6;
        r1.add(r62);
        r62.loadingStorePrice = true;
        goto L6
    L14:
        this.giftOptionsLoaded = true;
    L15:
        this.giftOptions = r0;
        this.giftOptionsLoading = false;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starGiftOptionsLoaded, new Object[0]);
        if (r1.isEmpty() == true) goto L32;
        StarsController$$ExternalSyntheticLambda81 r93 = new StarsController$$ExternalSyntheticLambda81(this, r1);
        if (BillingController.getInstance().isReady() == true) goto L21;
        BillingController.getInstance().whenSetuped(r93);
        return;
    L21:
        r93.run();
        return;
    }

    private /* synthetic */ void lambda$getGiftOptions$11(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda66(this, r1));
    }

    private /* synthetic */ void lambda$getGiftOptions$7(BillingResult r10, List r11, ArrayList r12) {
        if (r10.getResponseCode() == 0) goto L7;
        bulletinError("BILLING_" + BillingController.getResponseCodeString(r10.getResponseCode()));
        return;
    L7:
        if (r11 == null) goto L26;
        int r0 = 0;
    L10:
        if (r0 >= r11.size()) goto L26;
        ProductDetails r1 = (ProductDetails) r11.get(r0);
        int r2 = 0;
    L13:
        if (r2 >= r12.size()) goto L18;
        if (((TL_stars.TL_starsGiftOption) r12.get(r2)).store_product.equals(r1.getProductId()) == true) goto L16;
        r2 = r2 + 1;
        goto L13
    L16:
        TL_stars.TL_starsGiftOption r22 = (TL_stars.TL_starsGiftOption) r12.get(r2);
    L19:
        if (r22 == null) goto L24;
        ProductDetails.OneTimePurchaseOfferDetails r13 = r1.getOneTimePurchaseOfferDetails();
        if (r13 == null) goto L24;
        r22.currency = r13.getPriceCurrencyCode();
        r22.amount = (long) ((r13.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r22.currency)));
        r22.loadingStorePrice = false;
    L24:
        r0 = r0 + 1;
        goto L10
    L18:
        r22 = null;
    L26:
        if (this.giftOptions == null) goto L36;
        int r112 = 0;
    L29:
        if (r112 >= this.giftOptions.size()) goto L36;
        TL_stars.TL_starsGiftOption r122 = this.giftOptions.get(r112);
        if (r122 == null) goto L35;
        if (r122.loadingStorePrice == false) goto L35;
        r122.missingStorePrice = true;
    L35:
        r112 = r112 + 1;
    L36:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starGiftOptionsLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$getGiftOptions$8(ArrayList r2, BillingResult r3, List r4) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda125(this, r3, r4, r2));
    }

    private /* synthetic */ void lambda$getGiftOptions$9(ArrayList r5) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r5.size()) goto L6;
        r0.add(QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(((TL_stars.TL_starsGiftOption) r5.get(r1)).store_product).build());
        r1 = r1 + 1;
        goto L4
    L6:
        BillingController.getInstance().queryProductDetails(r0, new StarsController$$ExternalSyntheticLambda88(this, r5));
    }

    private /* synthetic */ void lambda$getGiveawayOptions$12(BillingResult r10, List r11, ArrayList r12) {
        if (r10.getResponseCode() == 0) goto L7;
        bulletinError("BILLING_" + BillingController.getResponseCodeString(r10.getResponseCode()));
        return;
    L7:
        if (r11 == null) goto L26;
        int r0 = 0;
    L10:
        if (r0 >= r11.size()) goto L26;
        ProductDetails r1 = (ProductDetails) r11.get(r0);
        int r2 = 0;
    L13:
        if (r2 >= r12.size()) goto L18;
        if (((TL_stars.TL_starsGiveawayOption) r12.get(r2)).store_product.equals(r1.getProductId()) == true) goto L16;
        r2 = r2 + 1;
        goto L13
    L16:
        TL_stars.TL_starsGiveawayOption r22 = (TL_stars.TL_starsGiveawayOption) r12.get(r2);
    L19:
        if (r22 == null) goto L24;
        ProductDetails.OneTimePurchaseOfferDetails r13 = r1.getOneTimePurchaseOfferDetails();
        if (r13 == null) goto L24;
        r22.currency = r13.getPriceCurrencyCode();
        r22.amount = (long) ((r13.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r22.currency)));
        r22.loadingStorePrice = false;
    L24:
        r0 = r0 + 1;
        goto L10
    L18:
        r22 = null;
    L26:
        if (this.giveawayOptions == null) goto L36;
        int r112 = 0;
    L29:
        if (r112 >= this.giveawayOptions.size()) goto L36;
        TL_stars.TL_starsGiveawayOption r122 = this.giveawayOptions.get(r112);
        if (r122 == null) goto L35;
        if (r122.loadingStorePrice == false) goto L35;
        r122.missingStorePrice = true;
    L35:
        r112 = r112 + 1;
    L36:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starGiveawayOptionsLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$getGiveawayOptions$13(ArrayList r2, BillingResult r3, List r4) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda133(this, r3, r4, r2));
    }

    private /* synthetic */ void lambda$getGiveawayOptions$14(ArrayList r5) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r5.size()) goto L6;
        r0.add(QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(((TL_stars.TL_starsGiveawayOption) r5.get(r1)).store_product).build());
        r1 = r1 + 1;
        goto L4
    L6:
        BillingController.getInstance().queryProductDetails(r0, new StarsController$$ExternalSyntheticLambda120(this, r5));
    }

    private /* synthetic */ void lambda$getGiveawayOptions$15(TLObject r9) {
        ArrayList<TL_stars.TL_starsGiveawayOption> r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        if ((r9 instanceof Vector) == false) goto L15;
        ArrayList<T> r92 = ((Vector) r9).objects;
        int r2 = r92.size();
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L14;
        Object r6 = r92.get(r4);
        r4 = r4 + 1;
        if ((r6 instanceof TL_stars.TL_starsGiveawayOption) == false) goto L6;
        TL_stars.TL_starsGiveawayOption r62 = (TL_stars.TL_starsGiveawayOption) r6;
        r0.add(r62);
        if (r62.store_product == null) goto L6;
        if (BuildVars.useInvoiceBilling() == true) goto L6;
        r1.add(r62);
        r62.loadingStorePrice = true;
        goto L6
    L14:
        this.giveawayOptionsLoaded = true;
    L15:
        this.giveawayOptions = r0;
        this.giveawayOptionsLoading = false;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starGiveawayOptionsLoaded, new Object[0]);
        if (r1.isEmpty() == true) goto L32;
        StarsController$$ExternalSyntheticLambda89 r93 = new StarsController$$ExternalSyntheticLambda89(this, r1);
        if (BillingController.getInstance().isReady() == true) goto L21;
        BillingController.getInstance().whenSetuped(r93);
        return;
    L21:
        r93.run();
        return;
    }

    private /* synthetic */ void lambda$getGiveawayOptions$16(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda85(this, r1));
    }

    private /* synthetic */ void lambda$getOptions$2(BillingResult r10, List r11, ArrayList r12) {
        if (r10.getResponseCode() == 0) goto L7;
        bulletinError("BILLING_" + BillingController.getResponseCodeString(r10.getResponseCode()));
        return;
    L7:
        if (r11 == null) goto L26;
        int r0 = 0;
    L10:
        if (r0 >= r11.size()) goto L26;
        ProductDetails r1 = (ProductDetails) r11.get(r0);
        int r2 = 0;
    L13:
        if (r2 >= r12.size()) goto L18;
        if (((TL_stars.TL_starsTopupOption) r12.get(r2)).store_product.equals(r1.getProductId()) == true) goto L16;
        r2 = r2 + 1;
        goto L13
    L16:
        TL_stars.TL_starsTopupOption r22 = (TL_stars.TL_starsTopupOption) r12.get(r2);
    L19:
        if (r22 == null) goto L24;
        ProductDetails.OneTimePurchaseOfferDetails r13 = r1.getOneTimePurchaseOfferDetails();
        if (r13 == null) goto L24;
        r22.currency = r13.getPriceCurrencyCode();
        r22.amount = (long) ((r13.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r22.currency)));
        r22.loadingStorePrice = false;
    L24:
        r0 = r0 + 1;
        goto L10
    L18:
        r22 = null;
    L26:
        if (this.options == null) goto L36;
        int r112 = 0;
    L29:
        if (r112 >= this.options.size()) goto L36;
        TL_stars.TL_starsTopupOption r122 = this.options.get(r112);
        if (r122 == null) goto L35;
        if (r122.loadingStorePrice == false) goto L35;
        r122.missingStorePrice = true;
    L35:
        r112 = r112 + 1;
    L36:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starOptionsLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$getOptions$3(ArrayList r2, BillingResult r3, List r4) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda87(this, r3, r4, r2));
    }

    private /* synthetic */ void lambda$getOptions$4(ArrayList r5) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r5.size()) goto L6;
        r0.add(QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(((TL_stars.TL_starsTopupOption) r5.get(r1)).store_product).build());
        r1 = r1 + 1;
        goto L4
    L6:
        BillingController.getInstance().queryProductDetails(r0, new StarsController$$ExternalSyntheticLambda80(this, r5));
    }

    private /* synthetic */ void lambda$getOptions$5(TLObject r9) {
        ArrayList<TL_stars.TL_starsTopupOption> r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        if ((r9 instanceof Vector) == false) goto L15;
        ArrayList<T> r92 = ((Vector) r9).objects;
        int r2 = r92.size();
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L14;
        Object r6 = r92.get(r4);
        r4 = r4 + 1;
        if ((r6 instanceof TL_stars.TL_starsTopupOption) == false) goto L6;
        TL_stars.TL_starsTopupOption r62 = (TL_stars.TL_starsTopupOption) r6;
        r0.add(r62);
        if (r62.store_product == null) goto L6;
        if (BuildVars.useInvoiceBilling() == true) goto L6;
        r1.add(r62);
        r62.loadingStorePrice = true;
        goto L6
    L14:
        this.optionsLoaded = true;
    L15:
        this.options = r0;
        this.optionsLoading = false;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starOptionsLoaded, new Object[0]);
        if (r1.isEmpty() == true) goto L32;
        StarsController$$ExternalSyntheticLambda56 r93 = new StarsController$$ExternalSyntheticLambda56(this, r1);
        if (BillingController.getInstance().isReady() == true) goto L21;
        BillingController.getInstance().whenSetuped(r93);
        return;
    L21:
        r93.run();
        return;
    }

    private /* synthetic */ void lambda$getOptions$6(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda54(this, r1));
    }

    private /* synthetic */ void lambda$getResellingGiftForm$134(Utilities.Callback r2, TL_stars.StarGift r3, long r4) {
        if (balanceAvailable() == true) goto L8;
        bulletinError("NO_BALANCE");
        if (r2 == null) goto L10;
        r2.run(null);
        return;
    L10:
        return;
    L8:
        getResellingGiftForm(r3, r4, r2);
    }

    private /* synthetic */ void lambda$getResellingGiftForm$135(TLObject r2, TLRPC.TL_error r3, Utilities.Callback r4) {
        if ((r2 instanceof TLRPC.TL_payments_paymentFormStarGift) == true) goto L6;
        bulletinError(r3, "NO_PAYMENT_FORM");
        r4.run(null);
        return;
    L6:
        r4.run((TLRPC.TL_payments_paymentFormStarGift) r2);
    }

    private /* synthetic */ void lambda$getResellingGiftForm$136(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda127(this, r3, r4, r2));
    }

    private /* synthetic */ void lambda$getStarGift$113(boolean[] r1, long r2, NotificationCenter.NotificationCenterDelegate[] r4, Utilities.Callback r5, int r6, int r7, Object[] r8) {
        if (r1[0] == true) goto L13;
        int r82 = NotificationCenter.starGiftsLoaded;
        if (r6 != r82) goto L11;
        TL_stars.StarGift r22 = getStarGift(r2);
        if (r22 == null) goto L12;
        r1[0] = true;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(r4[0], r82);
        r5.run(r22);
        return;
    L12:
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$getStarGift$114(boolean[] r3, NotificationCenter.NotificationCenterDelegate[] r4) {
        r3[0] = true;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(r4[0], NotificationCenter.starGiftsLoaded);
    }

    private /* synthetic */ void lambda$getStarGiftPreview$143(TLObject r2, long r3, Utilities.Callback r5) {
        if ((r2 instanceof TL_stars.starGiftUpgradePreview) == false) goto L6;
        TL_stars.starGiftUpgradePreview r22 = (TL_stars.starGiftUpgradePreview) r2;
        this.giftPreviews.put(Long.valueOf(r3), r22);
        r5.run(r22);
        return;
    L6:
        r5.run(null);
    }

    private /* synthetic */ void lambda$getStarGiftPreview$144(long r3, Utilities.Callback r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda101(this, r6, r3, r5));
    }

    private /* synthetic */ void lambda$getUserStarGift$145(AlertDialog r7, TLObject r8, TL_stars.InputSavedStarGift r9, Utilities.Callback r10) {
        r7.dismiss();
        if ((r8 instanceof TL_stars.TL_payments_savedStarGifts) == false) goto L17;
        TL_stars.TL_payments_savedStarGifts r82 = (TL_stars.TL_payments_savedStarGifts) r8;
        int r1 = 0;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
    L6:
        if (r1 >= r82.gifts.size()) goto L17;
        TL_stars.SavedStarGift r6 = r82.gifts.get(r1);
        if ((r9 instanceof TL_stars.TL_inputSavedStarGiftUser) == false) goto L12;
        if (((TL_stars.TL_inputSavedStarGiftUser) r9).msg_id != r6.msg_id) goto L12;
    L18:
        r10.run(r6);
        return;
    L12:
        if ((r9 instanceof TL_stars.TL_inputSavedStarGiftChat) == false) goto L16;
        if (((TL_stars.TL_inputSavedStarGiftChat) r9).saved_id == r6.saved_id) goto L18;
    L16:
        r1 = r1 + 1;
    L17:
        r6 = null;
        goto L18
    }

    private /* synthetic */ void lambda$getUserStarGift$146(AlertDialog r1, TL_stars.InputSavedStarGift r2, Utilities.Callback r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda68(this, r1, r4, r2, r3));
    }

    private /* synthetic */ void lambda$loadStarGifts$103(ArrayList r3, Integer r4, Long r5, ArrayList r6, ArrayList r7) {
        MessagesController.getInstance(this.currentAccount).putUsers(r6, true);
        MessagesController.getInstance(this.currentAccount).putChats(r7, true);
        this.giftsCacheLoaded = true;
        this.gifts.clear();
        this.gifts.addAll(r3);
        this.birthdaySortedGifts.clear();
        this.birthdaySortedGifts.addAll(this.gifts);
        Collections.sort(this.birthdaySortedGifts, Comparator.comparingInt(new StarsController$$ExternalSyntheticLambda35()).thenComparingInt(new StarsController$$ExternalSyntheticLambda36()));
        this.sortedGifts.clear();
        this.sortedGifts.addAll(this.gifts);
        Collections.sort(this.sortedGifts, Comparator.comparingInt(new StarsController$$ExternalSyntheticLambda37()));
        this.giftsHash = r4.intValue();
        this.giftsRemoteTime = r5.longValue();
        this.giftsLoading = false;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starGiftsLoaded, new Object[0]);
        loadStarGifts();
    }

    private /* synthetic */ void lambda$loadStarGifts$107(TL_stars.StarGifts r6) {
        this.giftsLoading = false;
        this.giftsLoaded = true;
        if ((r6 instanceof TL_stars.TL_starGifts) == false) goto L7;
        TL_stars.TL_starGifts r62 = (TL_stars.TL_starGifts) r6;
        MessagesController.getInstance(this.currentAccount).putUsers(r62.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r62.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r62.users, r62.chats, true, true);
        this.gifts.clear();
        this.gifts.addAll(r62.gifts);
        this.birthdaySortedGifts.clear();
        this.birthdaySortedGifts.addAll(this.gifts);
        Collections.sort(this.birthdaySortedGifts, Comparator.comparingInt(new StarsController$$ExternalSyntheticLambda45()).thenComparingInt(new StarsController$$ExternalSyntheticLambda46()));
        this.sortedGifts.clear();
        this.sortedGifts.addAll(this.gifts);
        Collections.sort(this.sortedGifts, Comparator.comparingInt(new StarsController$$ExternalSyntheticLambda47()));
        this.giftsHash = r62.hash;
        this.giftsRemoteTime = System.currentTimeMillis();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starGiftsLoaded, new Object[0]);
        saveStarGiftsCached(r62.gifts, this.giftsHash, this.giftsRemoteTime);
        return;
    L7:
        if ((r6 instanceof TL_stars.TL_starGiftsNotModified) == false) goto L10;
        ArrayList<TL_stars.StarGift> r63 = this.gifts;
        int r0 = this.giftsHash;
        long r1 = System.currentTimeMillis();
        this.giftsRemoteTime = r1;
        saveStarGiftsCached(r63, r0, r1);
        return;
    }

    private /* synthetic */ void lambda$loadSubscriptions$19(TLObject r4) {
        this.subscriptionsLoading = false;
        if ((r4 instanceof TL_stars.StarsStatus) == false) goto L10;
        TL_stars.StarsStatus r42 = (TL_stars.StarsStatus) r4;
        MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r42.chats, false);
        this.subscriptions.addAll(r42.subscriptions);
        if ((r42.flags & 4) != 0) goto L7;
        boolean r1 = true;
    L8:
        this.subscriptionsEndReached = r1;
        this.subscriptionsOffset = r42.subscriptions_next_offset;
        updateBalance(r42.balance);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starSubscriptionsLoaded, new Object[0]);
        return;
    L7:
        r1 = false;
        goto L8
    }

    private /* synthetic */ void lambda$loadSubscriptions$20(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda52(this, r1));
    }

    private /* synthetic */ void lambda$loadTransactions$17(int r5, TLObject r6) {
        this.loading[r5] = false;
        if ((r6 instanceof TL_stars.StarsStatus) == false) goto L21;
        TL_stars.StarsStatus r62 = (TL_stars.StarsStatus) r6;
        MessagesController.getInstance(this.currentAccount).putUsers(r62.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r62.chats, false);
        this.transactions[r5].addAll(r62.history);
        boolean[] r0 = this.transactionsExist;
        boolean r3 = true;
        if (this.transactions[r5].isEmpty() == true) goto L7;
    L10:
        boolean r2 = true;
    L11:
        r0[r5] = r2;
        boolean[] r02 = this.endReached;
        if ((r62.flags & 1) == 0) goto L15;
        r3 = false;
    L15:
        r02[r5] = r3;
        String[] r03 = this.offset;
        if (r3 == false) goto L18;
        String r22 = null;
    L19:
        r03[r5] = r22;
        updateBalance(r62.balance);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starTransactionsLoaded, new Object[0]);
        return;
    L18:
        r22 = r62.next_offset;
        goto L19
    L7:
        if (this.transactionsExist[r5] == true) goto L10;
        r2 = false;
        goto L11
    }

    private /* synthetic */ void lambda$loadTransactions$18(int r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda50(this, r1, r2));
    }

    private /* synthetic */ void lambda$openPaymentForm$66(Runnable r7, MessageObject r8, TLRPC.InputInvoice r9, TLRPC.TL_payments_paymentFormStars r10, Utilities.Callback r11) {
        if (balanceAvailable() == true) goto L8;
        bulletinError("NO_BALANCE");
        if (r7 == null) goto L10;
        r7.run();
        return;
    L10:
        return;
    L8:
        openPaymentForm(r8, r9, r10, r7, r11);
    }

    private /* synthetic */ void lambda$openPaymentForm$67(boolean[] r3, int r4, Utilities.Callback r5, Utilities.Callback r6, Boolean r7) {
        r3[0] = true;
        if (r4 <= 0) goto L5;
        invalidateSubscriptions(true);
    L5:
        if (r5 != null) goto L7;
    L11:
        if (r6 == null) goto L14;
        r6.run(Boolean.TRUE);
        return;
    L14:
        return;
    L7:
        if (r7.booleanValue() == false) goto L9;
        String r2 = "paid";
    L10:
        r5.run(r2);
        goto L11
    L9:
        r2 = "failed";
        goto L10
    }

    private /* synthetic */ void lambda$openPaymentForm$68(boolean[] r9, MessageObject r10, TLRPC.InputInvoice r11, TLRPC.TL_payments_paymentFormStars r12, boolean[] r13, int r14, Utilities.Callback r15, Utilities.Callback r16) {
        r9[0] = true;
        payAfterConfirmed(r10, r11, r12, new StarsController$$ExternalSyntheticLambda61(this, r13, r14, r15, r16));
    }

    private /* synthetic */ void lambda$openPaymentForm$69(Utilities.Callback r1, boolean[] r2, boolean[] r3, Utilities.Callback r4, DialogInterface r5) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r2[0] == true) goto L11;
        r1.run(Boolean.FALSE);
        this.paymentFormOpened = false;
        if (r3[0] == true) goto L12;
        if (r4 == null) goto L13;
        r4.run("cancelled");
        r3[0] = true;
        return;
    L13:
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$openPaymentForm$70(int r2, Utilities.Callback r3, boolean[] r4, Utilities.Callback r5, Boolean r6) {
        if (r2 <= 0) goto L5;
        invalidateSubscriptions(true);
    L5:
        if (r3 == null) goto L7;
        r3.run(Boolean.TRUE);
    L7:
        r4[0] = true;
        if (r5 != null) goto L10;
        return;
    L10:
        if (r6.booleanValue() == false) goto L12;
        String r1 = "paid";
    L13:
        r5.run(r1);
        return;
    L12:
        r1 = "failed";
        goto L13
    }

    private /* synthetic */ void lambda$openPaymentForm$71(long r14, boolean[] r16, Utilities.Callback r17, Context r18, Theme.ResourcesProvider r19, boolean r20, String r21, MessageObject r22, TLRPC.InputInvoice r23, TLRPC.TL_payments_paymentFormStars r24, int r25, long r26, Utilities.Callback r28) {
        if (this.balance.amount >= r14) goto L20;
        int r2 = 0;
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L15;
        this.paymentFormOpened = false;
        if (r28 == null) goto L10;
        r28.run(Boolean.FALSE);
    L10:
        if (r16[0] == true) goto L13;
        if (r17 == null) goto L13;
        r17.run("cancelled");
        r16[0] = true;
    L13:
        showNoSupportDialog(r18, r19);
        return;
    L15:
        boolean[] r0 = {false};
        if (r20 == false) goto L18;
        r2 = 9;
    L18:
        StarsIntroActivity.StarsNeededSheet r10 = new StarsIntroActivity.StarsNeededSheet(r18, r19, r14, r2, r21, new StarsController$$ExternalSyntheticLambda42(this, r0, r22, r23, r24, r16, r25, r17, r28), r26);
        r10.setOnDismissListener(new StarsController$$ExternalSyntheticLambda43(this, r28, r0, r16, r17));
        r10.show();
        return;
    L20:
        payAfterConfirmed(r22, r23, r24, new StarsController$$ExternalSyntheticLambda44(this, r25, r28, r16, r17));
    }

    private /* synthetic */ void lambda$openPaymentForm$72(boolean[] r2, Utilities.Callback r3) {
        this.paymentFormOpened = false;
        if (r2[0] == true) goto L7;
        if (r3 == null) goto L8;
        r3.run("cancelled");
        r2[0] = true;
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$pay$63(TLObject r3, MessageObject r4, TLRPC.TL_inputInvoiceMessage r5, Runnable r6, TLRPC.TL_error r7) {
        if ((r3 instanceof TLRPC.TL_payments_paymentFormStars) == false) goto L5;
        openPaymentForm(r4, r5, (TLRPC.TL_payments_paymentFormStars) r3, r6, null);
    L6:
        if (r6 == null) goto L9;
        r6.run();
        return;
    L9:
        return;
    L5:
        bulletinError(r7, "NO_PAYMENT_FORM");
        goto L6
    }

    private /* synthetic */ void lambda$pay$64(MessageObject r8, TLRPC.TL_inputInvoiceMessage r9, Runnable r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda96(this, r11, r8, r9, r10, r12));
    }

    private /* synthetic */ void lambda$pay$65(int r2) {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r2, true);
    }

    private /* synthetic */ void lambda$payAfterConfirmed$79(TLRPC.TL_payments_paymentResult r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2.updates, false);
    }

    private /* synthetic */ void lambda$payAfterConfirmed$81(boolean[] r3, MessageObject r4, TLRPC.InputInvoice r5, TLRPC.TL_payments_paymentFormStars r6, Utilities.Callback r7) {
        r3[0] = true;
        payAfterConfirmed(r4, r5, r6, new StarsController$$ExternalSyntheticLambda128(r7));
    }

    private /* synthetic */ void lambda$payAfterConfirmed$83(TLObject r2, MessageObject r3, TLRPC.InputInvoice r4, Utilities.Callback r5, BulletinFactory r6, TLRPC.TL_error r7) {
        if ((r2 instanceof TLRPC.TL_payments_paymentFormStars) == false) goto L6;
        payAfterConfirmed(r3, r4, (TLRPC.TL_payments_paymentFormStars) r2, r5);
        return;
    L6:
        if (r5 == null) goto L8;
        r5.run(Boolean.FALSE);
    L8:
        int r1 = R.raw.error;
        int r22 = R.string.UnknownErrorCode;
        if (r7 == null) goto L11;
        String r32 = r7.text;
    L12:
        r6.createSimpleBulletin(r1, LocaleController.formatString(r22, new Object[]{r32})).show();
        return;
    L11:
        r32 = "FAILED_GETTING_FORM";
        goto L12
    }

    private /* synthetic */ void lambda$payAfterConfirmed$84(MessageObject r9, TLRPC.InputInvoice r10, Utilities.Callback r11, BulletinFactory r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda112(this, r13, r9, r10, r11, r12, r14));
    }

    private /* synthetic */ void lambda$payAfterConfirmed$85(TLObject r18, Utilities.Callback r19, MessageObject r20, Context r21, long r22, String r24, int r25, String r26, TLRPC.InputInvoice r27, long r28, TLRPC.TL_error r30, Theme.ResourcesProvider r31, TLRPC.TL_payments_paymentFormStars r32) {
        this.paymentFormOpened = false;
        BaseFragment r13 = LaunchActivity.getLastFragment();
        if (r13 != null) goto L5;
    L7:
        BulletinFactory r132 = BulletinFactory.global();
    L9:
        if ((r18 instanceof TLRPC.TL_payments_paymentResult) == false) goto L36;
        if (r19 == null) goto L12;
        r19.run(Boolean.TRUE);
    L12:
        Utilities.stageQueue.postRunnable(new StarsController$$ExternalSyntheticLambda90(this, (TLRPC.TL_payments_paymentResult) r18));
        if (r20 == null) goto L19;
        TLRPC.Message r0 = r20.messageOwner;
        if (r0 == null) goto L19;
        if ((r0.media instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L19;
        r132.createSimpleBulletin(r21.getResources().getDrawable(R.drawable.star_small_inner).mutate(), LocaleController.getString(R.string.StarsMediaPurchaseCompleted), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsMediaPurchaseCompletedInfo", (int) r22, new Object[]{r24}))).show();
    L22:
        LaunchActivity r02 = LaunchActivity.instance;
        if (r02 == null) goto L28;
        if (r02.getFireworksOverlay() == null) goto L28;
        LaunchActivity.instance.getFireworksOverlay().start(true);
    L28:
        if ((r27 instanceof TLRPC.TL_inputInvoiceStars) == true) goto L30;
    L32:
        invalidateTransactions(true);
    L33:
        if (r20 == null) goto L67;
        TLRPC.TL_messages_getExtendedMedia r03 = new TLRPC.TL_messages_getExtendedMedia();
        r03.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r28);
        r03.id.add(Integer.valueOf(r20.getId()));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, null);
        return;
    L67:
        return;
    L30:
        if ((((TLRPC.TL_inputInvoiceStars) r27).purpose instanceof TLRPC.TL_inputStorePaymentStarsGift) == false) goto L32;
    L19:
        if (r25 <= 0) goto L21;
        r132.createSimpleBulletin(R.raw.stars_send, LocaleController.getString(R.string.StarsBotSubscriptionCompleted), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsBotSubscriptionCompletedInfo", (int) r22, new Object[]{r26, r24}))).show();
        goto L22
    L21:
        r132.createSimpleBulletin(R.raw.stars_send, LocaleController.getString(R.string.StarsPurchaseCompleted), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsPurchaseCompletedInfo", (int) r22, new Object[]{r26, r24}))).show();
        goto L22
    L36:
        if (r30 == null) goto L48;
        if ("BALANCE_TOO_LOW".equals(r30.text) == false) goto L48;
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L45;
        if (r19 == null) goto L43;
        r19.run(Boolean.FALSE);
    L43:
        showNoSupportDialog(r21, r31);
        return;
    L45:
        boolean[] r2 = {false};
        StarsIntroActivity.StarsNeededSheet r12 = new StarsIntroActivity.StarsNeededSheet(r21, r31, r22, 0, r24, new StarsController$$ExternalSyntheticLambda91(this, r2, r20, r27, r32, r19), r28);
        r12.setOnDismissListener(new StarsController$$ExternalSyntheticLambda92(r19, r2));
        r12.show();
        return;
    L48:
        if (r30 == null) goto L57;
        if ("FORM_EXPIRED".equals(r30.text) == false) goto L57;
        TLRPC.TL_payments_getPaymentForm r04 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r23 = BotWebViewSheet.makeThemeParams(r31);
        if (r23 == null) goto L54;
        TLRPC.TL_dataJSON r3 = new TLRPC.TL_dataJSON();
        r04.theme_params = r3;
        r3.data = r23.toString();
        r04.flags |= 1;
    L54:
        r04.invoice = r27;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r04, new StarsController$$ExternalSyntheticLambda93(this, r20, r27, r19, r132));
        return;
    L57:
        if (r19 == null) goto L59;
        r19.run(Boolean.FALSE);
    L59:
        int r05 = R.raw.error;
        int r29 = R.string.UnknownErrorCode;
        if (r30 == null) goto L62;
        String r33 = r30.text;
    L63:
        r132.createSimpleBulletin(r05, LocaleController.formatString(r29, new Object[]{r33})).show();
        if (r20 == null) goto L68;
        TLRPC.TL_messages_getExtendedMedia r06 = new TLRPC.TL_messages_getExtendedMedia();
        r06.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r28);
        r06.id.add(Integer.valueOf(r20.getId()));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r06, null);
        return;
    L68:
        return;
    L62:
        r33 = "FAILED_SEND_STARS";
        goto L63
    L5:
        if (r13.visibleDialog != null) goto L7;
        r132 = BulletinFactory.of(r13);
        goto L9
    }

    private /* synthetic */ void lambda$payAfterConfirmed$86(Utilities.Callback r18, MessageObject r19, Context r20, long r21, String r23, int r24, String r25, TLRPC.InputInvoice r26, long r27, Theme.ResourcesProvider r29, TLRPC.TL_payments_paymentFormStars r30, TLObject r31, TLRPC.TL_error r32) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda79(this, r31, r18, r19, r20, r21, r23, r24, r25, r26, r27, r32, r29, r30));
    }

    private /* synthetic */ void lambda$payAfterConfirmed$87(TLRPC.TL_payments_paymentResult r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2.updates, false);
    }

    private /* synthetic */ void lambda$payAfterConfirmed$89(boolean[] r3, String r4, TLRPC.ChatInvite r5, Utilities.Callback2 r6) {
        r3[0] = true;
        payAfterConfirmed(r4, r5, new StarsController$$ExternalSyntheticLambda2(r6));
    }

    private /* synthetic */ void lambda$payAfterConfirmed$91(TLObject r22, Utilities.Callback2 r23, long r24, String r26, TLRPC.TL_error r27, Context r28, Theme.ResourcesProvider r29, TLRPC.ChatInvite r30, String r31) {
        int r7 = 0;
        this.paymentFormOpened = false;
        BaseFragment r8 = LaunchActivity.getLastFragment();
        if (AndroidUtilities.hasDialogOnTop(r8) == true) goto L5;
        BulletinFactory r82 = BulletinFactory.of(r8);
    L7:
        if ((r22 instanceof TLRPC.TL_payments_paymentResult) == false) goto L33;
        TLRPC.TL_payments_paymentResult r0 = (TLRPC.TL_payments_paymentResult) r22;
        Utilities.stageQueue.postRunnable(new StarsController$$ExternalSyntheticLambda144(this, r0));
        TLRPC.Updates r2 = r0.updates;
        TLRPC.Update r6 = r2.update;
        if ((r6 instanceof TL_update.TL_updateChannel) == false) goto L11;
        long r11 = -((TL_update.TL_updateChannel) r6).channel_id;
    L13:
        if (r2.updates != null) goto L15;
    L20:
        if (r23 == null) goto L23;
        r23.run(Long.valueOf(r11), Boolean.TRUE);
    L23:
        if (r11 != 0) goto L25;
        r82.createSimpleBulletin(R.raw.stars_send, LocaleController.getString(R.string.StarsSubscriptionCompleted), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsSubscriptionCompletedText", (int) r24, new Object[]{r26}))).show();
    L25:
        LaunchActivity r02 = LaunchActivity.instance;
        if (r02 != null) goto L28;
    L30:
        invalidateTransactions(true);
        invalidateSubscriptions(true);
        return;
    L28:
        if (r02.getFireworksOverlay() == null) goto L30;
        LaunchActivity.instance.getFireworksOverlay().start(true);
    L15:
        if (r7 >= r0.updates.updates.size()) goto L20;
        if ((r0.updates.updates.get(r7) instanceof TL_update.TL_updateChannel) == false) goto L19;
        r11 = -((TL_update.TL_updateChannel) r0.updates.updates.get(r7)).channel_id;
    L19:
        r7 = r7 + 1;
        goto L15
    L11:
        r11 = 0;
        goto L13
    L33:
        if (r27 != null) goto L35;
    L44:
        if (r23 == null) goto L46;
        r23.run(0L, Boolean.FALSE);
    L46:
        int r03 = R.raw.error;
        int r1 = R.string.UnknownErrorCode;
        if (r27 == null) goto L49;
        String r25 = r27.text;
    L50:
        r82.createSimpleBulletin(r03, LocaleController.formatString(r1, new Object[]{r25})).show();
        return;
    L49:
        r25 = "FAILED_SEND_STARS";
        goto L50
    L35:
        if ("BALANCE_TOO_LOW".equals(r27.text) == false) goto L44;
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L42;
        if (r23 == null) goto L40;
        r23.run(0L, Boolean.FALSE);
    L40:
        showNoSupportDialog(r28, r29);
        return;
    L42:
        boolean[] r210 = {false};
        StarsIntroActivity.StarsNeededSheet r112 = new StarsIntroActivity.StarsNeededSheet(r28, r29, r24, 1, r30.title, new StarsController$$ExternalSyntheticLambda145(this, r210, r31, r30, r23), 0);
        r112.setOnDismissListener(new StarsController$$ExternalSyntheticLambda146(r23, r210));
        r112.show();
        return;
    L5:
        r82 = BulletinFactory.global();
        goto L7
    }

    private /* synthetic */ void lambda$payAfterConfirmed$92(Utilities.Callback2 r13, long r14, String r16, Context r17, Theme.ResourcesProvider r18, TLRPC.ChatInvite r19, String r20, TLObject r21, TLRPC.TL_error r22) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda137(this, r21, r13, r14, r16, r22, r17, r18, r19, r20));
    }

    private /* synthetic */ void lambda$sendPaidReaction$98(MessageObject r9, ChatActivity r10, long r11, Long r13) {
        sendPaidReaction(r9, r10, r11, true, true, r13);
    }

    private /* synthetic */ void lambda$sendPaidReaction$99(MessageObject r9, ChatActivity r10, long r11, Long r13) {
        sendPaidReaction(r9, r10, r11, true, true, r13);
    }

    private /* synthetic */ void lambda$showStarsTopup$23(Activity r1, long r2, String r4) {
        showStarsTopupInternal(r1, r2, r4);
    }

    private /* synthetic */ void lambda$stopPaidMessages$149(TLObject r9, long r10, long r12, boolean r14) {
        if ((r9 instanceof TLRPC.TL_boolTrue) == true) goto L5;
        return;
    L5:
        if (r10 == 0) goto L8;
        processUpdateMonoForumNoPaidException(-r10, r12, r14);
        return;
    L8:
        TLRPC.UserFull r8 = MessagesController.getInstance(this.currentAccount).getUserFull(r12);
        if (r8 == null) goto L13;
        TLRPC.PeerSettings r82 = r8.settings;
        if (r82 == null) goto L13;
        r82.flags &= -16385;
        r82.charge_paid_message_stars = 0;
    L13:
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putLong("dialog_bar_paying_" + r12, 0).apply();
        MessagesController.getInstance(this.currentAccount).loadPeerSettings(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r12)), MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r12)), true);
        ContactsController.getInstance(this.currentAccount).loadPrivacySettings(true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messagesFeeUpdated, new Object[]{Long.valueOf(r12)});
    }

    private /* synthetic */ void lambda$stopPaidMessages$150(long r3, long r5, boolean r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda57(this, r8, r3, r5, r7));
    }

    private /* synthetic */ void lambda$subscribeTo$74(boolean[] r3, String r4, TLRPC.ChatInvite r5, boolean[] r6, Utilities.Callback2 r7, Utilities.Callback r8) {
        r3[0] = true;
        payAfterConfirmed(r4, r5, new StarsController$$ExternalSyntheticLambda126(r6, r7, r8));
    }

    private /* synthetic */ void lambda$subscribeTo$75(Utilities.Callback r1, boolean[] r2, boolean[] r3, Utilities.Callback2 r4, DialogInterface r5) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r2[0] == true) goto L11;
        r1.run(Boolean.FALSE);
        this.paymentFormOpened = false;
        if (r3[0] == true) goto L12;
        if (r4 == null) goto L13;
        r4.run("cancelled", 0L);
        r3[0] = true;
        return;
    L13:
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$subscribeTo$77(long r15, int r17, boolean[] r18, Utilities.Callback2 r19, Context r20, Theme.ResourcesProvider r21, TLRPC.ChatInvite r22, String r23, Utilities.Callback r24) {
        if (this.balance.amount < r15) goto L5;
        payAfterConfirmed(r23, r22, new StarsController$$ExternalSyntheticLambda111(r24, r18, r19));
        return;
    L5:
        if (MessagesController.getInstance(r17).starsPurchaseAvailable() == true) goto L15;
        this.paymentFormOpened = false;
        if (r24 == null) goto L10;
        r24.run(Boolean.FALSE);
    L10:
        if (r18[0] == true) goto L13;
        if (r19 == null) goto L13;
        r19.run("cancelled", 0L);
        r18[0] = true;
    L13:
        showNoSupportDialog(r20, r21);
        return;
    L15:
        boolean[] r3 = {false};
        StarsIntroActivity.StarsNeededSheet r8 = new StarsIntroActivity.StarsNeededSheet(r20, r21, r15, 1, r22.title, new StarsController$$ExternalSyntheticLambda109(this, r3, r23, r22, r18, r19, r24), 0);
        r8.setOnDismissListener(new StarsController$$ExternalSyntheticLambda110(this, r24, r3, r18, r19));
        r8.show();
    }

    private /* synthetic */ void lambda$subscribeTo$78(boolean[] r4, Utilities.Callback2 r5) {
        this.paymentFormOpened = false;
        if (r4[0] == true) goto L7;
        if (r5 == null) goto L8;
        r5.run("cancelled", 0L);
        r4[0] = true;
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$updateMediaPrice$93(TLObject r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r2, false);
    }

    private /* synthetic */ void lambda$updateMediaPrice$94(TLObject r7, MessageObject r8, long r9, Runnable r11) {
        if ((r7 instanceof TLRPC.TL_messages_messages) == false) goto L14;
        TLRPC.TL_messages_messages r72 = (TLRPC.TL_messages_messages) r7;
        MessagesController.getInstance(this.currentAccount).putUsers(r72.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r72.chats, false);
        if (r72.messages.size() == 1) goto L7;
    L12:
        r11.run();
        return;
    L7:
        if ((r72.messages.get(0) instanceof TLRPC.TL_message) == false) goto L12;
        if ((r72.messages.get(0).media instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L12;
        r8.messageOwner = r72.messages.get(0);
        updateMediaPrice(r8, r9, r11, true);
        return;
    L14:
        r11.run();
    }

    private /* synthetic */ void lambda$updateMediaPrice$95(MessageObject r4, long r5, Runnable r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda117(this, r8, r4, r5, r7));
    }

    private /* synthetic */ void lambda$updateMediaPrice$96(TLObject r2, Runnable r3, TLRPC.TL_error r4, boolean r5, long r6, int r8, MessageObject r9, long r10) {
        if ((r2 instanceof TLRPC.Updates) == false) goto L6;
        Utilities.stageQueue.postRunnable(new StarsController$$ExternalSyntheticLambda94(this, r2));
        r3.run();
        return;
    L6:
        if (r4 != null) goto L8;
    L12:
        r3.run();
        return;
    L8:
        if (FileRefController.isFileRefError(r4.text) == false) goto L12;
        if (r5 == true) goto L12;
        TLRPC.TL_messages_getScheduledMessages r22 = new TLRPC.TL_messages_getScheduledMessages();
        r22.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r6);
        r22.id.add(Integer.valueOf(r8));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new StarsController$$ExternalSyntheticLambda95(this, r9, r10, r3));
    }

    private /* synthetic */ void lambda$updateMediaPrice$97(Runnable r13, boolean r14, long r15, int r17, MessageObject r18, long r19, TLObject r21, TLRPC.TL_error r22) {
        AndroidUtilities.runOnUIThread(new StarsController$$ExternalSyntheticLambda86(this, r21, r13, r22, r14, r15, r17, r18, r19));
    }

    private boolean needsUndoButton(MessageObject r8, long r9) {
        PaidMessagesToast r0 = this.currentPaidMessagesToast;
        if (r0 == null) goto L10;
        if (r0.isUndoRunning() == false) goto L10;
        if (this.currentPaidMessagesToast.isVisible() == false) goto L10;
        return true;
    L10:
        if (AlertsCreator.needsPaidMessageAlert(this.currentAccount, r8.getDialogId()) == false) goto L12;
        return false;
    L12:
        Long r02 = this.justAgreedToNotAskDialogs.get(Long.valueOf(r8.getDialogId()));
        if (r02 != null) goto L15;
    L17:
        Integer r7 = this.sendingMessagesCount.get(Long.valueOf(r8.getDialogId()));
        if (r7 == null) goto L23;
        if (r7.intValue() < 3) goto L23;
        return true;
    L23:
        if (r9 >= 100) goto L25;
        return false;
    L25:
        return true;
    L15:
        if ((System.currentTimeMillis() - r02.longValue()) <= 5000) goto L17;
        return false;
    }

    private void payAfterConfirmed(String r13, TLRPC.ChatInvite r14, Utilities.Callback2<Long, Boolean> r15) {
        if (r14 != null) goto L4;
        return;
    L4:
        if (r14.subscription_pricing == null) goto L12;
        Context r7 = ApplicationLoader.applicationContext;
        Theme.ResourcesProvider r8 = getResourceProvider();
        if (r7 == null) goto L13;
        long r4 = r14.subscription_pricing.amount;
        String r6 = r14.title;
        TLRPC.TL_inputInvoiceChatInviteSubscription r0 = new TLRPC.TL_inputInvoiceChatInviteSubscription();
        r0.hash = r13;
        TL_stars.TL_payments_sendStarsForm r11 = new TL_stars.TL_payments_sendStarsForm();
        r11.form_id = r14.subscription_form_id;
        r11.invoice = r0;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r11, new StarsController$$ExternalSyntheticLambda116(this, r15, r4, r6, r7, r8, r14, r13));
        return;
    L13:
        return;
    }

    private void saveStarGiftsCached(ArrayList<TL_stars.StarGift> r7, int r8, long r9) {
        MessagesStorage r1 = MessagesStorage.getInstance(this.currentAccount);
        r1.getStorageQueue().postRunnable(new StarsController$$ExternalSyntheticLambda67(r1, r7, r8, r9));
    }

    public static void showNoSupportDialog(Context r1, Theme.ResourcesProvider r2) {
        new AlertDialog.Builder(r1, r2).setTitle(LocaleController.getString(R.string.StarsNotAvailableTitle)).setMessage(LocaleController.getString(R.string.StarsNotAvailableText)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
    }

    private void showStarsTopupInternal(Activity r11, long r12, String r14) {
        if (getBalance().amount < r12) goto L5;
    L9:
        BaseFragment r10 = LaunchActivity.getSafeLastFragment();
        if (r10 != null) goto L12;
        return;
    L12:
        BulletinFactory.of(r10).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.StarsTopupLinkEnough), LocaleController.getString(R.string.StarsTopupLinkTopupAnyway), new StarsController$$ExternalSyntheticLambda39()).setDuration(5000).show(true);
        return;
    L5:
        if (r12 <= 0) goto L9;
        new StarsIntroActivity.StarsNeededSheet(r11, null, r12, 4, r14, new StarsController$$ExternalSyntheticLambda40(), 0).show();
    }

    private void updateMediaPrice(MessageObject r17, long r18, Runnable r20, boolean r21) {
        if (r17 != null) goto L6;
        r20.run();
        return;
    L6:
        long r4 = r17.getDialogId();
        int r6 = r17.getId();
        TLRPC.TL_messageMediaPaidMedia r0 = (TLRPC.TL_messageMediaPaidMedia) r17.messageOwner.media;
        TLRPC.TL_messages_editMessage r10 = new TLRPC.TL_messages_editMessage();
        r10.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r4);
        int r2 = r10.flags;
        r10.flags = 32768 | r2;
        r10.schedule_date = r17.messageOwner.date;
        r10.id = r6;
        r10.flags = r2 | 49152;
        TLRPC.TL_inputMediaPaidMedia r22 = new TLRPC.TL_inputMediaPaidMedia();
        r22.stars_amount = r18;
        int r3 = 0;
    L8:
        if (r3 >= r0.extended_media.size()) goto L20;
        TLRPC.MessageExtendedMedia r11 = r0.extended_media.get(r3);
        if ((r11 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L11;
        TLRPC.MessageMedia r112 = ((TLRPC.TL_messageExtendedMedia) r11).media;
        if ((r112 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L17;
        TLRPC.TL_inputMediaPhoto r12 = new TLRPC.TL_inputMediaPhoto();
        TLRPC.TL_inputPhoto r13 = new TLRPC.TL_inputPhoto();
        TLRPC.Photo r113 = ((TLRPC.TL_messageMediaPhoto) r112).photo;
        r13.id = r113.id;
        r13.access_hash = r113.access_hash;
        r13.file_reference = r113.file_reference;
        r12.id = r13;
        r22.extended_media.add(r12);
    L19:
        r3 = r3 + 1;
        goto L8
    L17:
        if ((r112 instanceof TLRPC.TL_messageMediaDocument) == false) goto L19;
        TLRPC.TL_inputMediaDocument r122 = new TLRPC.TL_inputMediaDocument();
        TLRPC.TL_inputDocument r132 = new TLRPC.TL_inputDocument();
        TLRPC.Document r114 = ((TLRPC.TL_messageMediaDocument) r112).document;
        r132.id = r114.id;
        r132.access_hash = r114.access_hash;
        r132.file_reference = r114.file_reference;
        r122.id = r132;
        r22.extended_media.add(r122);
        goto L19
    L11:
        r20.run();
        return;
    L20:
        r10.media = r22;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r10, new StarsController$$ExternalSyntheticLambda60(this, r20, r21, r4, r6, r17, r18));
    }

    public boolean balanceAvailable() {
        return this.balanceLoaded;
    }

    public boolean beforeSendingFinalRequest(TLObject r8, ArrayList<MessageObject> r9, Runnable r10) {
        if (r9 != null) goto L5;
    L17:
        return true;
    L5:
        if (r9.isEmpty() == true) goto L17;
        if (getAllowedPaidStars(r8) > 0) goto L10;
        return true;
    L10:
        HashSet r82 = new HashSet();
        int r1 = r9.size();
        boolean r2 = false;
        int r3 = 0;
    L11:
        if (r3 >= r1) goto L16;
        MessageObject r4 = r9.get(r3);
        r3 = r3 + 1;
        int r42 = r4.getId();
        r82.add(Integer.valueOf(r42));
        if (this.sendingPaidMessagesIds.remove(Integer.valueOf(r42)) == false) goto L11;
        this.postponedPaidMessages.put(Integer.valueOf(r42), new StarsController$$ExternalSyntheticLambda34(this, r82, r10));
        r2 = true;
        goto L11
    L16:
        return !r2;
    }

    public boolean beforeSendingFinalRequest(TLObject r6, MessageObject r7, Runnable r8) {
        if (r7 != null) goto L6;
        return true;
    L6:
        if (r7.messageOwner != null) goto L8;
        return true;
    L8:
        int r72 = r7.getId();
        if (getAllowedPaidStars(r6) > 0) goto L12;
        return true;
    L12:
        if (this.sendingPaidMessagesIds.remove(Integer.valueOf(r72)) == false) goto L15;
        this.postponedPaidMessages.put(Integer.valueOf(r72), r8);
        return false;
    L15:
        return true;
    }

    public void beforeSendingMessage(MessageObject r11) {
        if (r11 == null) goto L18;
        TLRPC.Message r0 = r11.messageOwner;
        if (r0 == null) goto L19;
        long r5 = r0.paid_message_stars;
        if (r5 > 0) goto L10;
        return;
    L10:
        if (r11.isEphemeral() == true) goto L20;
        boolean r9 = needsUndoButton(r11, r5);
        int r02 = r11.getId();
        if (r9 == false) goto L15;
        this.sendingPaidMessagesIds.add(Integer.valueOf(r02));
    L15:
        showPaidMessageToast(r11.getDialogId(), r11, r5, new StarsController$$ExternalSyntheticLambda30(this, r9), new StarsController$$ExternalSyntheticLambda31(this, r9, r02), r9);
        return;
    L20:
        return;
    L19:
        return;
    }

    public void buy(Activity r3, TL_stars.TL_starsTopupOption r4, Utilities.Callback2<Boolean, String> r5, TLRPC.InputPeer r6) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L14;
        BaseFragment r2 = LaunchActivity.getLastFragment();
        if (r2 != null) goto L9;
    L12:
        showNoSupportDialog(r3, null);
        return;
    L9:
        if (r2.getContext() == null) goto L12;
        showNoSupportDialog(r2.getContext(), r2.getResourceProvider());
        return;
    L14:
        boolean r0 = isInvoiceBillingDisabled(r6);
        if (BuildVars.useInvoiceBilling() == false) goto L17;
    L18:
        if (r0 == true) goto L25;
        TLRPC.TL_inputStorePaymentStarsTopup r32 = new TLRPC.TL_inputStorePaymentStarsTopup();
        r32.stars = r4.stars;
        r32.amount = r4.amount;
        r32.currency = r4.currency;
        r32.spend_purpose_peer = r6;
        TLRPC.TL_inputInvoiceStars r42 = new TLRPC.TL_inputInvoiceStars();
        r42.purpose = r32;
        TLRPC.TL_payments_getPaymentForm r33 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r62 = BotWebViewSheet.makeThemeParams(getResourceProvider());
        if (r62 == null) goto L22;
        TLRPC.TL_dataJSON r02 = new TLRPC.TL_dataJSON();
        r33.theme_params = r02;
        r02.data = r62.toString();
        r33.flags |= 1;
    L22:
        r33.invoice = r42;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r33, new StarsController$$ExternalSyntheticLambda62(this, r5, r42));
        return;
    L25:
        if (BillingController.getInstance().isReady() == true) goto L29;
        if (r5 == null) goto L31;
        r5.run(Boolean.FALSE, "INVOICE DISABLED");
        return;
    L31:
        return;
    L29:
        TLRPC.TL_inputStorePaymentStarsTopup r22 = new TLRPC.TL_inputStorePaymentStarsTopup();
        r22.stars = r4.stars;
        r22.currency = r4.currency;
        r22.amount = r4.amount;
        QueryProductDetailsParams.Product r63 = QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(r4.store_product).build();
        FileLog.d("StarsController.buy starts queryProductDetails");
        BillingController.getInstance().queryProductDetails(Arrays.asList(new QueryProductDetailsParams.Product[]{r63}), new StarsController$$ExternalSyntheticLambda63(r5, r22, r4, r3));
        return;
    L17:
        if (BillingController.getInstance().isReady() == true) goto L25;
        goto L18
    }

    public void buyGift(Activity r5, TL_stars.TL_starsGiftOption r6, long r7, Utilities.Callback2<Boolean, String> r9) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L15;
        BaseFragment r4 = LaunchActivity.getLastFragment();
        if (r4 != null) goto L9;
    L12:
        showNoSupportDialog(r5, null);
        return;
    L9:
        if (r4.getContext() == null) goto L12;
        showNoSupportDialog(r4.getContext(), r4.getResourceProvider());
        return;
    L15:
        if (BuildVars.useInvoiceBilling() == true) goto L21;
        if (BillingController.getInstance().isReady() == false) goto L21;
        TLRPC.TL_inputStorePaymentStarsGift r72 = new TLRPC.TL_inputStorePaymentStarsGift();
        r72.stars = r6.stars;
        r72.currency = r6.currency;
        r72.amount = r6.amount;
        r72.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r7);
        BillingController.getInstance().queryProductDetails(Arrays.asList(new QueryProductDetailsParams.Product[]{QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(r6.store_product).build()}), new StarsController$$ExternalSyntheticLambda104(this, r9, r72, r6, r5));
        return;
    L21:
        TLRPC.TL_inputStorePaymentStarsGift r42 = new TLRPC.TL_inputStorePaymentStarsGift();
        r42.stars = r6.stars;
        r42.amount = r6.amount;
        r42.currency = r6.currency;
        r42.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r7);
        TLRPC.TL_inputInvoiceStars r73 = new TLRPC.TL_inputInvoiceStars();
        r73.purpose = r42;
        TLRPC.TL_payments_getPaymentForm r43 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r8 = BotWebViewSheet.makeThemeParams(getResourceProvider());
        if (r8 == null) goto L24;
        TLRPC.TL_dataJSON r92 = new TLRPC.TL_dataJSON();
        r43.theme_params = r92;
        r92.data = r8.toString();
        r43.flags |= 1;
    L24:
        r43.invoice = r73;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r43, new StarsController$$ExternalSyntheticLambda103(this, r9, r73));
    }

    public void buyGiveaway(Activity r2, TLRPC.Chat r3, List<TLObject> r4, TL_stars.TL_starsGiveawayOption r5, int r6, List<TLObject> r7, int r8, boolean r9, boolean r10, boolean r11, String r12, Utilities.Callback2<Boolean, String> r13) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L14;
        BaseFragment r1 = LaunchActivity.getLastFragment();
        if (r1 != null) goto L9;
    L12:
        showNoSupportDialog(r2, null);
        return;
    L9:
        if (r1.getContext() == null) goto L12;
        showNoSupportDialog(r1.getContext(), r1.getResourceProvider());
        return;
    L14:
        TLRPC.TL_inputStorePaymentStarsGiveaway r0 = new TLRPC.TL_inputStorePaymentStarsGiveaway();
        r0.only_new_subscribers = r10;
        r0.winners_are_visible = r9;
        r0.stars = r5.stars;
        MessagesController.getInstance(this.currentAccount);
        r0.boost_peer = MessagesController.getInputPeer(r3);
        if (r4 != null) goto L17;
    L22:
        Iterator<TLObject> r32 = r7.iterator();
    L24:
        if (r32.hasNext() == false) goto L27;
        r0.countries_iso2.add(((TLRPC.TL_help_country) r32.next()).iso2);
        goto L24
    L27:
        if (r0.countries_iso2.isEmpty() == true) goto L29;
        r0.flags |= 4;
    L29:
        if (r11 == false) goto L31;
        r0.flags |= 16;
        r0.prize_description = r12;
    L31:
        r0.random_id = SendMessagesHelper.getInstance(this.currentAccount).getNextRandomId();
        r0.until_date = r8;
        r0.currency = r5.currency;
        r0.amount = r5.amount;
        r0.users = r6;
        if (BuildVars.useInvoiceBilling() == false) goto L34;
    L40:
        TLRPC.TL_inputInvoiceStars r22 = new TLRPC.TL_inputInvoiceStars();
        r22.purpose = r0;
        TLRPC.TL_payments_getPaymentForm r33 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r42 = BotWebViewSheet.makeThemeParams(getResourceProvider());
        if (r42 == null) goto L43;
        TLRPC.TL_dataJSON r52 = new TLRPC.TL_dataJSON();
        r33.theme_params = r52;
        r52.data = r42.toString();
        r33.flags |= 1;
    L43:
        r33.invoice = r22;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r33, new StarsController$$ExternalSyntheticLambda83(this, r13, r22));
        return;
    L34:
        if (BillingController.getInstance().isReady() == false) goto L40;
        if (r5.store_product == null) goto L40;
        BillingController.getInstance().queryProductDetails(Arrays.asList(new QueryProductDetailsParams.Product[]{QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(r5.store_product).build()}), new StarsController$$ExternalSyntheticLambda84(this, r13, r0, r2));
        return;
    L17:
        if (r4.isEmpty() == true) goto L22;
        r0.flags |= 2;
        Iterator<TLObject> r34 = r4.iterator();
    L20:
        if (r34.hasNext() == false) goto L22;
        TLObject r43 = r34.next();
        ArrayList<TLRPC.InputPeer> r92 = r0.additional_peers;
        MessagesController.getInstance(this.currentAccount);
        r92.add(MessagesController.getInputPeer(r43));
        goto L20
    }

    public void buyPremiumGift(long r14, Object r16, TLRPC.TL_textWithEntities r17, Utilities.Callback2<Boolean, String> r18) {
        Context r0 = LaunchActivity.instance;
        if (r0 == null) goto L5;
    L4:
        Context r4 = r0;
        Theme.ResourcesProvider r02 = getResourceProvider();
        boolean r1 = r16 instanceof TLRPC.TL_premiumGiftOption;
        if (r1 == false) goto L9;
    L10:
        if (r4 != null) goto L13;
        return;
    L13:
        if (balanceAvailable() == true) goto L17;
        getBalance(new StarsController$$ExternalSyntheticLambda130(this, r18, r14, r16, r17));
        return;
    L17:
        if (r1 == false) goto L20;
        int r12 = ((TLRPC.TL_premiumGiftOption) r16).months;
    L22:
        String r6 = DialogObject.getName(this.currentAccount, r14);
        TLRPC.TL_inputInvoicePremiumGiftStars r3 = new TLRPC.TL_inputInvoicePremiumGiftStars();
        r3.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r14);
        r3.months = r12;
        if (r17 != null) goto L25;
    L27:
        TLRPC.TL_payments_getPaymentForm r11 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r13 = BotWebViewSheet.makeThemeParams(r02);
        if (r13 == null) goto L30;
        TLRPC.TL_dataJSON r9 = new TLRPC.TL_dataJSON();
        r11.theme_params = r9;
        r9.data = r13.toString();
        r11.flags |= 1;
    L30:
        r11.invoice = r3;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r11, new StarsController$$ExternalSyntheticLambda131(this, r18, r3, r4, r02, r6, r14, r16, r17));
        return;
    L25:
        if (TextUtils.isEmpty(r17.text) == true) goto L27;
        r3.flags |= 1;
        r3.message = r17;
        goto L27
    L20:
        if ((r16 instanceof TLRPC.TL_premiumGiftCodeOption) == false) goto L33;
        r12 = ((TLRPC.TL_premiumGiftCodeOption) r16).months;
        goto L22
    L33:
        return;
    L9:
        if ((r16 instanceof TLRPC.TL_premiumGiftCodeOption) == true) goto L10;
        return;
    L5:
        r0 = ApplicationLoader.applicationContext;
        goto L4
    }

    @Deprecated
    public void buyResellingGift(TLRPC.TL_payments_paymentFormStarGift r9, TL_stars.StarGift r10, long r11, Utilities.Callback2<Boolean, String> r13) {
        buyResellingGift(r9, r10, r11, null, true, r13);
    }

    public void buyResellingGift(TLRPC.TL_payments_paymentFormStarGift r17, TL_stars.StarGift r18, long r19, TLRPC.TL_textWithEntities r21, boolean r22, Utilities.Callback2<Boolean, String> r23) {
        Context r0 = LaunchActivity.instance;
        if (r0 == null) goto L5;
    L4:
        Context r3 = r0;
        Theme.ResourcesProvider r4 = getResourceProvider();
        if (r18 == null) goto L23;
        if (r3 != null) goto L11;
        return;
    L11:
        if (balanceAvailable() == true) goto L14;
        getBalance(new StarsController$$ExternalSyntheticLambda141(this, r23, r17, r18, r19));
        return;
    L14:
        String r7 = DialogObject.getName(this.currentAccount, r19);
        TLRPC.TL_inputInvoiceStarGiftResale r02 = new TLRPC.TL_inputInvoiceStarGiftResale();
        r02.slug = r18.slug;
        r02.to_id = MessagesController.getInstance(this.currentAccount).getInputPeer(r19);
        r02.ton = this.ton;
        r02.message = r21;
        r02.show_name = !r22;
        TLRPC.TL_payments_getPaymentForm r2 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r5 = BotWebViewSheet.makeThemeParams(r4);
        if (r5 == null) goto L17;
        TLRPC.TL_dataJSON r6 = new TLRPC.TL_dataJSON();
        r2.theme_params = r6;
        r6.data = r5.toString();
        r2.flags |= 1;
    L17:
        r2.invoice = r02;
        TL_stars.TL_payments_sendStarsForm r12 = new TL_stars.TL_payments_sendStarsForm();
        r12.form_id = r17.form_id;
        r12.invoice = r02;
        ArrayList<TLRPC.TL_labeledPrice> r03 = r17.invoice.prices;
        int r24 = r03.size();
        long r52 = 0;
        int r13 = 0;
    L18:
        if (r13 >= r24) goto L20;
        TLRPC.TL_labeledPrice r14 = r03.get(r13);
        r13 = r13 + 1;
        r52 = r52 + r14.amount;
        goto L18
    L20:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new StarsController$$ExternalSyntheticLambda142(this, r23, r3, r4, r52, r7, r17, r18, r19));
        return;
    L23:
        return;
    L5:
        r0 = ApplicationLoader.applicationContext;
        goto L4
    }

    public void buyStarGift(TL_stars.StarGift r16, boolean r17, boolean r18, long r19, TLRPC.TL_textWithEntities r21, Utilities.Callback2<Boolean, String> r22) {
        Context r0 = LaunchActivity.instance;
        if (r0 == null) goto L5;
    L4:
        Context r4 = r0;
        Theme.ResourcesProvider r5 = getResourceProvider();
        if (r16 == null) goto L24;
        if (r4 != null) goto L11;
        return;
    L11:
        if (balanceAvailable() == true) goto L14;
        getBalance(new StarsController$$ExternalSyntheticLambda114(this, r22, r16, r17, r18, r19, r21));
        return;
    L14:
        String r6 = DialogObject.getName(this.currentAccount, r19);
        TLRPC.TL_inputInvoiceStarGift r02 = new TLRPC.TL_inputInvoiceStarGift();
        r02.hide_name = r17;
        r02.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r19);
        r02.gift_id = r16.id;
        r02.include_upgrade = r18;
        if (r21 != null) goto L17;
    L19:
        TLRPC.TL_payments_getPaymentForm r13 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r7 = BotWebViewSheet.makeThemeParams(r5);
        if (r7 == null) goto L22;
        TLRPC.TL_dataJSON r12 = new TLRPC.TL_dataJSON();
        r13.theme_params = r12;
        r12.data = r7.toString();
        r13.flags |= 1;
    L22:
        r13.invoice = r02;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r13, new StarsController$$ExternalSyntheticLambda115(this, r22, r02, r4, r5, r6, r16, r17, r18, r19, r21));
        return;
    L17:
        if (TextUtils.isEmpty(r21.text) == true) goto L19;
        r02.flags |= 2;
        r02.message = r21;
        goto L19
    L24:
        return;
    L5:
        r0 = ApplicationLoader.applicationContext;
        goto L4
    }

    public boolean canBuy(TLRPC.InputPeer r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if (isInvoiceBillingDisabled(r1) == true) goto L6;
        return true;
    L6:
        return BillingController.getInstance().isReady();
    }

    public boolean canUseTon() {
        if (this.ton == true) goto L6;
        return false;
    L6:
        if (TONIntroActivity.allowTopUp() == false) goto L8;
        return true;
    L8:
        TL_stars.StarsAmount r7 = getBalance();
        if (r7.nanos == 0) goto L11;
    L14:
        return true;
    L11:
        if (r7.amount != 0) goto L14;
        return false;
    }

    public void commitPaidReaction() {
        PendingPaidReactions r0 = this.currentPendingReactions;
        if (r0 == null) goto L6;
        r0.close();
        return;
    }

    public boolean didFullyLoadSubscriptions() {
        return this.subscriptionsEndReached;
    }

    public boolean didFullyLoadTransactions(int r1) {
        return this.endReached[r1];
    }

    public TL_stars.SavedStarGift findUserStarGift(long r8) {
        int r1 = 0;
    L4:
        if (r1 >= this.giftLists.size()) goto L17;
        GiftsList r2 = this.giftLists.valueAt(r1);
        int r3 = 0;
    L7:
        if (r3 >= r2.gifts.size()) goto L16;
        TL_stars.SavedStarGift r4 = r2.gifts.get(r3);
        if (r4 == null) goto L15;
        TL_stars.StarGift r5 = r4.gift;
        if (r5 == null) goto L15;
        if (r5.id != r8) goto L15;
        return r4;
    L15:
        r3 = r3 + 1;
        goto L7
    L16:
        r1 = r1 + 1;
        goto L4
    L17:
        return null;
    }

    public long getBalance(boolean r3) {
        return getBalance(r3, null, false).amount;
    }

    public TL_stars.StarsAmount getBalance() {
        return getBalance(null);
    }

    public TL_stars.StarsAmount getBalance(Runnable r3) {
        return getBalance(true, r3, false);
    }

    public TL_stars.StarsAmount getBalance(boolean r5, Runnable r6, boolean r7) {
        if (this.balanceLoaded == false) goto L7;
        if ((System.currentTimeMillis() - this.lastBalanceLoaded) > 60000) goto L7;
    L8:
        if (r7 == false) goto L10;
    L9:
        this.balanceLoading = true;
        TL_stars.TL_payments_getStarsStatus r72 = new TL_stars.TL_payments_getStarsStatus();
        r72.ton = this.ton;
        r72.peer = new TLRPC.TL_inputPeerSelf();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r72, new StarsController$$ExternalSyntheticLambda0(this, r6));
    L10:
        if (r5 == false) goto L16;
        if (this.minus <= 0) goto L16;
        AmountUtils$Amount r52 = AmountUtils$Amount.ofSafe(this.balance);
        return AmountUtils$Amount.fromDecimal(Math.max(0, r52.asDecimal() - this.minus), r52.currency).toTl();
    L16:
        return this.balance;
    L7:
        if (this.balanceLoading == false) goto L9;
        goto L8
    }

    public AmountUtils$Amount getBalanceAmount() {
        AmountUtils$Amount r0 = AmountUtils$Amount.of(getBalance());
        if (r0 == null) goto L5;
        return r0;
    L5:
        if (this.ton == false) goto L7;
        AmountUtils$Currency r2 = AmountUtils$Currency.TON;
    L9:
        return AmountUtils$Amount.fromNano(0, r2);
    L7:
        r2 = AmountUtils$Currency.STARS;
        goto L9
    }

    public Context getContext(BaseFragment r1) {
        if (r1 != null) goto L4;
    L7:
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L14;
        if (r0.isFinishing() == true) goto L14;
        return LaunchActivity.instance;
    L14:
        if (ApplicationLoader.applicationContext != null) goto L16;
        return null;
    L16:
        return ApplicationLoader.applicationContext;
    L4:
        if (r1.getContext() == null) goto L7;
        return r1.getContext();
    }

    public ArrayList<TL_stars.TL_starsGiftOption> getGiftOptions() {
        if (this.giftOptionsLoading == true) goto L10;
        if (this.giftOptionsLoaded == true) goto L10;
        this.giftOptionsLoading = true;
        TL_stars.TL_payments_getStarsGiftOptions r0 = new TL_stars.TL_payments_getStarsGiftOptions();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$$ExternalSyntheticLambda53(this));
        return this.giftOptions;
    L10:
        return this.giftOptions;
    }

    public ArrayList<TL_stars.TL_starsGiveawayOption> getGiveawayOptions() {
        if (this.giveawayOptionsLoading == true) goto L10;
        if (this.giveawayOptionsLoaded == true) goto L10;
        this.giveawayOptionsLoading = true;
        TL_stars.TL_payments_getStarsGiveawayOptions r0 = new TL_stars.TL_payments_getStarsGiveawayOptions();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$$ExternalSyntheticLambda55(this));
        return this.giveawayOptions;
    L10:
        return this.giveawayOptions;
    }

    public ArrayList<TL_stars.TL_starsTopupOption> getOptions() {
        if (this.optionsLoading == true) goto L10;
        if (this.optionsLoaded == true) goto L10;
        this.optionsLoading = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_stars.TL_payments_getStarsTopupOptions(), new StarsController$$ExternalSyntheticLambda28(this));
        return this.options;
    L10:
        return this.options;
    }

    public long getPaidReactionsDialogId(MessageObject r3) {
        PendingPaidReactions r0 = this.currentPendingReactions;
        if (r0 != null) goto L5;
    L10:
        if (r3 != null) goto L12;
        Long r32 = null;
    L13:
        if (r32 != null) goto L15;
        Long r2 = MessagesController.getInstance(this.currentAccount).getPaidReactionsDialogId();
        if (r2 != null) goto L19;
        return 0;
    L19:
        return r2.longValue();
    L15:
        return r32.longValue();
    L12:
        r32 = r3.getMyPaidReactionPeer();
        goto L13
    L5:
        if (r0.message.equals(MessageId.from(r3)) == false) goto L10;
        Long r02 = this.currentPendingReactions.peer;
        if (r02 == null) goto L10;
        return r02.longValue();
    }

    public long getPaidReactionsDialogId(MessageId r2, TLRPC.MessageReactions r3) {
        PendingPaidReactions r0 = this.currentPendingReactions;
        if (r0 != null) goto L5;
    L10:
        Long r22 = MessageObject.getMyPaidReactionPeer(r3);
        if (r22 != null) goto L13;
        Long r1 = MessagesController.getInstance(this.currentAccount).getPaidReactionsDialogId();
        if (r1 != null) goto L17;
        return 0;
    L17:
        return r1.longValue();
    L13:
        return r22.longValue();
    L5:
        if (r0.message.equals(r2) == false) goto L10;
        Long r23 = this.currentPendingReactions.peer;
        if (r23 == null) goto L10;
        return r23.longValue();
    }

    public void getPaidRevenue(long r3, long r5, Utilities.Callback<Long> r7) {
        TL_account.getPaidMessagesRevenue r0 = new TL_account.getPaidMessagesRevenue();
        r0.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r3);
        if (r5 == 0) goto L5;
        r0.parent_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r5);
    L5:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$$ExternalSyntheticLambda49(r7));
    }

    public long getPendingPaidReactions(long r6, int r8) {
        PendingPaidReactions r5 = this.currentPendingReactions;
        if (r5 != null) goto L5;
        return 0;
    L5:
        MessageId r2 = r5.message;
        if (r2.did == r6) goto L8;
    L15:
        return 0;
    L8:
        if (r2.mid != r8) goto L15;
        if (r5.applied == true) goto L14;
        return 0;
    L14:
        return r5.amount;
    }

    public long getPendingPaidReactions(MessageObject r3) {
        if (r3 == null) goto L16;
        TLRPC.Message r0 = r3.messageOwner;
        if (r0 != null) goto L7;
        return 0;
    L7:
        if (r0.isThreadMessage == true) goto L11;
        if (r3.isForwardedChannelPost() == true) goto L11;
    L15:
        return getPendingPaidReactions(r3.getDialogId(), r3.getId());
    L11:
        if (r3.messageOwner.fwd_from == null) goto L15;
        return getPendingPaidReactions(r3.getFromChatId(), r3.messageOwner.fwd_from.saved_from_msg_id);
    L16:
        return 0;
    }

    public GiftsCollections getProfileGiftCollectionsList(long r2, boolean r4) {
        GiftsCollections r0 = this.giftCollections.get(r2);
        if (r0 != null) goto L7;
        if (r4 == false) goto L8;
        LongSparseArray<GiftsCollections> r42 = this.giftCollections;
        GiftsCollections r02 = new GiftsCollections(this.currentAccount, r2);
        r42.put(r2, r02);
        return r02;
    L8:
        return r0;
    L7:
        return r0;
    }

    public GiftsList getProfileGiftsList(long r2) {
        return getProfileGiftsList(r2, true);
    }

    public GiftsList getProfileGiftsList(long r2, boolean r4) {
        GiftsList r0 = this.giftLists.get(r2);
        if (r0 != null) goto L7;
        if (r4 == false) goto L8;
        LongSparseArray<GiftsList> r42 = this.giftLists;
        GiftsList r02 = new GiftsList(this.currentAccount, r2);
        r42.put(r2, r02);
        return r02;
    L8:
        return r0;
    L7:
        return r0;
    }

    @Deprecated
    public void getResellingGiftForm(TL_stars.StarGift r8, long r9, Utilities.Callback<TLRPC.TL_payments_paymentFormStarGift> r11) {
        getResellingGiftForm(r8, r9, null, true, r11);
    }

    public void getResellingGiftForm(TL_stars.StarGift r6, long r7, TLRPC.TL_textWithEntities r9, boolean r10, Utilities.Callback<TLRPC.TL_payments_paymentFormStarGift> r11) {
        Context r0 = LaunchActivity.instance;
        if (r0 != null) goto L6;
        r0 = ApplicationLoader.applicationContext;
    L6:
        Theme.ResourcesProvider r1 = getResourceProvider();
        if (r6 == null) goto L19;
        if (r0 != null) goto L11;
        return;
    L11:
        if (balanceAvailable() == true) goto L14;
        getBalance(new StarsController$$ExternalSyntheticLambda105(this, r11, r6, r7));
        return;
    L14:
        TLRPC.TL_inputInvoiceStarGiftResale r02 = new TLRPC.TL_inputInvoiceStarGiftResale();
        r02.slug = r6.slug;
        r02.to_id = MessagesController.getInstance(this.currentAccount).getInputPeer(r7);
        r02.ton = this.ton;
        r02.message = r9;
        r02.show_name = !r10;
        TLRPC.TL_payments_getPaymentForm r5 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r72 = BotWebViewSheet.makeThemeParams(r1);
        if (r72 == null) goto L17;
        TLRPC.TL_dataJSON r8 = new TLRPC.TL_dataJSON();
        r5.theme_params = r8;
        r8.data = r72.toString();
        r5.flags |= 1;
    L17:
        r5.invoice = r02;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r5, new StarsController$$ExternalSyntheticLambda106(this, r11));
        return;
    }

    public Theme.ResourcesProvider getResourceProvider() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getResourceProvider();
    }

    public Runnable getStarGift(long r10, Utilities.Callback<TL_stars.StarGift> r12) {
        boolean[] r3 = {false};
        NotificationCenter.NotificationCenterDelegate[] r6 = new NotificationCenter.NotificationCenterDelegate[1];
        r6[0] = new StarsController$$ExternalSyntheticLambda58(this, r3, r10, r6, r12);
        NotificationCenter r9 = NotificationCenter.getInstance(this.currentAccount);
        NotificationCenter.NotificationCenterDelegate r102 = r6[0];
        int r11 = NotificationCenter.starGiftsLoaded;
        r9.addObserver(r102, r11);
        TL_stars.StarGift r92 = getStarGift(r10);
        if (r92 == null) goto L6;
        r3[0] = true;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(r6[0], r11);
        r12.run(r92);
    L6:
        return new StarsController$$ExternalSyntheticLambda59(this, r3, r6);
    }

    public TL_stars.StarGift getStarGift(long r5) {
        loadStarGifts();
        int r0 = 0;
    L4:
        if (r0 >= this.gifts.size()) goto L9;
        TL_stars.StarGift r1 = this.gifts.get(r0);
        if (r1.id == r5) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    public void getStarGiftPreview(long r4, Utilities.Callback<TL_stars.starGiftUpgradePreview> r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        TL_stars.starGiftUpgradePreview r0 = this.giftPreviews.get(Long.valueOf(r4));
        if (r0 == null) goto L8;
        r6.run(r0);
        return;
    L8:
        TL_stars.getStarGiftUpgradePreview r02 = new TL_stars.getStarGiftUpgradePreview();
        r02.gift_id = r4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new StarsController$$ExternalSyntheticLambda82(this, r4, r6));
    }

    public void getUserStarGift(TL_stars.InputSavedStarGift r5, Utilities.Callback<TL_stars.SavedStarGift> r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        AlertDialog r0 = new AlertDialog(ApplicationLoader.applicationContext, 3);
        r0.showDelayed(200);
        TL_stars.getSavedStarGift r1 = new TL_stars.getSavedStarGift();
        r1.stargift.add(r5);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new StarsController$$ExternalSyntheticLambda51(this, r0, r5, r6));
    }

    public boolean hasSubscriptions() {
        if (balanceAvailable() == true) goto L5;
        return false;
    L5:
        if (this.subscriptions.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasTransactions() {
        return hasTransactions(0);
    }

    public boolean hasTransactions(int r2) {
        if (balanceAvailable() == true) goto L5;
        return false;
    L5:
        if (this.transactions[r2].isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public void hidePaidMessageToast(MessageObject r5) {
        if (r5 == null) goto L15;
        PaidMessagesToast r0 = this.currentPaidMessagesToast;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.dialogId == r5.getDialogId()) goto L9;
        return;
    L9:
        if (this.currentPaidMessagesToast.pop(r5.getId()) == false) goto L14;
        this.currentPaidMessagesToast = null;
        return;
    L14:
        return;
    }

    public void invalidateBalance() {
        this.balanceLoaded = false;
        getBalance();
        this.balanceLoaded = true;
    }

    public void invalidateBalance(Runnable r3) {
        this.balanceLoaded = false;
        getBalance(false, r3, true);
        this.balanceLoaded = true;
    }

    public void invalidateProfileGifts(long r3) {
        GiftsList r1 = getProfileGiftsList(r3, false);
        if (r1 == null) goto L5;
        r1.invalidate(false);
    L5:
        GiftsCollections r2 = this.giftCollections.get(r3);
        if (r2 == null) goto L9;
        r2.invalidate(false);
        return;
    }

    public void invalidateProfileGifts(TLRPC.UserFull r6) {
        if (r6 == null) goto L14;
        long r0 = r6.id;
        GiftsList r3 = getProfileGiftsList(r0, false);
        if (r3 != null) goto L7;
    L9:
        GiftsCollections r5 = this.giftCollections.get(r0);
        if (r5 == null) goto L13;
        r5.invalidate(false);
        return;
    L13:
        return;
    L7:
        if (r3.totalCount == r6.stargifts_count) goto L9;
        r3.invalidate(false);
        goto L9
    }

    public void invalidateStarGifts() {
        this.giftsLoaded = false;
        this.giftsCacheLoaded = true;
        this.giftsRemoteTime = 0;
        loadStarGifts();
    }

    public void invalidateSubscriptions(boolean r2) {
        if (this.subscriptionsLoading == true) goto L10;
        this.subscriptions.clear();
        this.subscriptionsOffset = null;
        this.subscriptionsLoading = false;
        this.subscriptionsEndReached = false;
        if (r2 == false) goto L9;
        loadSubscriptions();
        return;
    L9:
        return;
    }

    public void invalidateTransactions(boolean r5) {
        int r1 = 0;
    L4:
        if (r1 >= 3) goto L12;
        if (this.loading[r1] == true) goto L11;
        this.transactions[r1].clear();
        this.offset[r1] = null;
        this.loading[r1] = false;
        this.endReached[r1] = false;
        if (r5 == false) goto L11;
        loadTransactions(r1);
    L11:
        r1 = r1 + 1;
        goto L4
    }

    public boolean isLoadingSubscriptions() {
        return this.subscriptionsLoading;
    }

    public void loadStarGifts() {
        if (this.giftsLoading == false) goto L5;
        return;
    L5:
        if (this.giftsLoaded == true) goto L7;
    L9:
        this.giftsLoading = true;
        if (this.giftsCacheLoaded == true) goto L13;
        getStarGiftsCached(new StarsController$$ExternalSyntheticLambda22(this));
        return;
    L13:
        getStarGiftsRemote(this.giftsHash, new StarsController$$ExternalSyntheticLambda23(this));
        return;
    L7:
        if ((System.currentTimeMillis() - this.giftsRemoteTime) >= 60000) goto L9;
    }

    public void loadSubscriptions() {
        if (this.ton == false) goto L5;
        return;
    L5:
        if (this.subscriptionsLoading == false) goto L7;
        return;
    L7:
        if (this.subscriptionsEndReached == true) goto L16;
        this.subscriptionsLoading = true;
        TL_stars.TL_getStarsSubscriptions r0 = new TL_stars.TL_getStarsSubscriptions();
        r0.peer = new TLRPC.TL_inputPeerSelf();
        String r1 = this.subscriptionsOffset;
        r0.offset = r1;
        if (r1 != null) goto L12;
        r0.offset = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$$ExternalSyntheticLambda24(this));
        return;
    }

    public void loadTransactions(int r5) {
        boolean[] r0 = this.loading;
        if (r0[r5] == false) goto L5;
        return;
    L5:
        if (this.endReached[r5] == true) goto L21;
        boolean r1 = true;
        r0[r5] = true;
        TL_stars.TL_payments_getStarsTransactions r02 = new TL_stars.TL_payments_getStarsTransactions();
        r02.ton = this.ton;
        r02.peer = new TLRPC.TL_inputPeerSelf();
        if (r5 != 1) goto L10;
        boolean r3 = true;
    L11:
        r02.inbound = r3;
        if (r5 == 2) goto L15;
        r1 = false;
    L15:
        r02.outbound = r1;
        String r12 = this.offset[r5];
        r02.offset = r12;
        if (r12 != null) goto L18;
        r02.offset = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new StarsController$$ExternalSyntheticLambda33(this, r5));
        return;
    L10:
        r3 = false;
        goto L11
    }

    public void makeStarGiftSoldOut(TL_stars.StarGift r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (this.giftsLoaded == false) goto L9;
        r5.availability_remains = 0;
        saveStarGiftsCached(this.gifts, this.giftsHash, this.giftsRemoteTime);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starGiftSoldOut, new Object[]{r5});
        return;
    }

    public void openPaymentForm(MessageObject r23, TLRPC.InputInvoice r24, TLRPC.TL_payments_paymentFormStars r25, Runnable r26, Utilities.Callback<String> r27) {
        if (r25 != null) goto L5;
        return;
    L5:
        if (r25.invoice != null) goto L7;
        return;
    L7:
        if (this.paymentFormOpened == true) goto L51;
        MessagesController.getInstance(this.currentAccount).putUsers(r25.users, false);
        Context r0 = LaunchActivity.instance;
        if (r0 != null) goto L13;
        r0 = ApplicationLoader.applicationContext;
    L13:
        Theme.ResourcesProvider r7 = getResourceProvider();
        if (r0 != null) goto L17;
        return;
    L17:
        if (balanceAvailable() == true) goto L20;
        getBalance(new StarsController$$ExternalSyntheticLambda25(this, r26, r23, r24, r25, r27));
        return;
    L20:
        ArrayList<TLRPC.TL_labeledPrice> r2 = r25.invoice.prices;
        int r4 = r2.size();
        int r11 = 0;
        long r8 = 0;
    L21:
        if (r11 >= r4) goto L23;
        TLRPC.TL_labeledPrice r13 = r2.get(r11);
        r11 = r11 + 1;
        r8 = r8 + r13.amount;
        goto L21
    L23:
        if (r23 != null) goto L25;
    L33:
        long r132 = r25.bot_id;
    L31:
        long r14 = r132;
        int r42 = this.currentAccount;
        if (r14 < 0) goto L37;
        TLRPC.User r22 = MessagesController.getInstance(r42).getUser(Long.valueOf(r14));
        String r43 = UserObject.getUserName(r22);
        UserObject.isBot(r22);
        boolean r28 = !UserObject.isBot(r22);
        boolean r6 = false;
    L43:
        String r3 = r25.title;
        if (r26 == null) goto L46;
        r26.run();
    L46:
        int r133 = r25.invoice.subscription_period;
        boolean[] r5 = new boolean[1];
        r5[r6 ? 1 : 0] = r6;
        Context r62 = r0;
        long r20 = r8;
        StarsIntroActivity.openConfirmPurchaseSheet(r62, r7, this.currentAccount, r23, r14, r3, r20, r25.photo, r133, new StarsController$$ExternalSyntheticLambda26(this, r20, r5, r27, r62, r7, r28, r43, r23, r24, r25, r133, r14), new StarsController$$ExternalSyntheticLambda27(this, r5, r27));
        return;
    L37:
        r6 = false;
        TLRPC.Chat r29 = MessagesController.getInstance(r42).getChat(Long.valueOf(-r14));
        if (r29 != null) goto L41;
        String r210 = _UrlKt.FRAGMENT_ENCODE_SET;
    L40:
        r43 = r210;
        r28 = false;
        goto L43
    L41:
        r210 = r29.title;
        goto L40
    L25:
        if (r23.type != 29) goto L33;
        TLRPC.MessageFwdHeader r211 = r23.messageOwner.fwd_from;
        if (r211 == null) goto L32;
        TLRPC.Peer r212 = r211.from_id;
        if (r212 == null) goto L32;
        r132 = DialogObject.getPeerDialogId(r212);
    L32:
        r132 = r23.getDialogId();
        goto L31
    }

    public Runnable pay(MessageObject r7, Runnable r8) {
        Context r0 = LaunchActivity.instance;
        if (r0 != null) goto L6;
        r0 = ApplicationLoader.applicationContext;
    L6:
        Theme.ResourcesProvider r1 = getResourceProvider();
        if (r7 == null) goto L15;
        if (r0 == null) goto L17;
        long r2 = r7.getDialogId();
        int r02 = r7.getId();
        TLRPC.TL_inputInvoiceMessage r4 = new TLRPC.TL_inputInvoiceMessage();
        r4.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r2);
        r4.msg_id = r02;
        TLRPC.TL_payments_getPaymentForm r03 = new TLRPC.TL_payments_getPaymentForm();
        JSONObject r12 = BotWebViewSheet.makeThemeParams(r1);
        if (r12 == null) goto L13;
        TLRPC.TL_dataJSON r22 = new TLRPC.TL_dataJSON();
        r03.theme_params = r22;
        r22.data = r12.toString();
        r03.flags |= 1;
    L13:
        r03.invoice = r4;
        return new StarsController$$ExternalSyntheticLambda76(this, ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, new StarsController$$ExternalSyntheticLambda75(this, r7, r4, r8)));
    L17:
        return null;
    L15:
        return null;
    }

    public void payAfterConfirmed(MessageObject r19, TLRPC.InputInvoice r20, TLRPC.TL_payments_paymentFormStars r21, Utilities.Callback<Boolean> r22) {
        if (r21 == null) goto L42;
        Context r4 = ApplicationLoader.applicationContext;
        Theme.ResourcesProvider r13 = getResourceProvider();
        if (r4 != null) goto L8;
        return;
    L8:
        ArrayList<TLRPC.TL_labeledPrice> r0 = r21.invoice.prices;
        int r2 = r0.size();
        long r5 = 0;
        int r7 = 0;
    L9:
        if (r7 >= r2) goto L11;
        TLRPC.TL_labeledPrice r10 = r0.get(r7);
        r7 = r7 + 1;
        r5 = r5 + r10.amount;
        goto L9
    L11:
        if (r19 == null) goto L30;
        TLRPC.Message r02 = r19.messageOwner;
        if (r02 == null) goto L19;
        TLRPC.MessageFwdHeader r03 = r02.fwd_from;
        if (r03 == null) goto L19;
        TLRPC.Peer r04 = r03.from_id;
        if (r04 == null) goto L19;
        long r102 = DialogObject.getPeerDialogId(r04);
    L21:
        if (r102 < 0) goto L23;
    L29:
        long r11 = r102;
        int r23 = this.currentAccount;
        if (r11 < 0) goto L35;
        String r05 = UserObject.getUserName(MessagesController.getInstance(r23).getUser(Long.valueOf(r11)));
    L34:
        String r72 = r05;
        String r9 = r21.title;
        int r8 = r21.invoice.subscription_period;
        TL_stars.TL_payments_sendStarsForm r15 = new TL_stars.TL_payments_sendStarsForm();
        r15.form_id = r21.form_id;
        r15.invoice = r20;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r15, new StarsController$$ExternalSyntheticLambda69(this, r22, r19, r4, r5, r72, r8, r9, r20, r11, r13, r21));
        return;
    L35:
        TLRPC.Chat r06 = MessagesController.getInstance(r23).getChat(Long.valueOf(-r11));
        if (r06 != null) goto L38;
        r05 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L34
    L38:
        r05 = r06.title;
        goto L34
    L23:
        if (r19.getFromChatId() <= 0) goto L29;
        TLRPC.User r07 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r19.getFromChatId()));
        if (r07 == null) goto L29;
        if (r07.bot == false) goto L29;
        r102 = r07.id;
    L19:
        r102 = r19.getDialogId();
        goto L21
    L30:
        r102 = r21.bot_id;
        goto L29
    }

    public void processUpdateMonoForumNoPaidException(long r3, long r5, boolean r7) {
        TopicsController r0 = MessagesController.getInstance(this.currentAccount).getTopicsController();
        TLRPC.TL_forumTopic r1 = r0.findTopic(r3, r5);
        if (r1 == null) goto L6;
        r1.nopaid_messages_exception = r7;
        r0.saveTopics(r3);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messagesFeeUpdated, new Object[]{Long.valueOf(r5)});
        return;
    }

    public PendingPaidReactions sendPaidReaction(MessageObject r21, ChatActivity r22, long r23, boolean r25, boolean r26, Long r27) {
        MessageId r2 = MessageId.from(r21);
        StarsController r8 = getInstance(this.currentAccount);
        Context r10 = getContext(r22);
        if (r10 != null) goto L5;
        return null;
    L5:
        String r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        boolean r13 = false;
        if (r26 == true) goto L8;
    L20:
        PendingPaidReactions r0 = this.currentPendingReactions;
        if (r0 != null) goto L23;
    L24:
        PendingPaidReactions r02 = this.currentPendingReactions;
        if (r02 == null) goto L27;
        r02.close();
    L27:
        PendingPaidReactions r03 = new PendingPaidReactions(this, r2, r21, r22, ConnectionsManager.getInstance(this.currentAccount).getCurrentTime(), r25);
        this.currentPendingReactions = r03;
        r03.peer = r27;
    L29:
        if ((this.currentPendingReactions.amount + r23) <= MessagesController.getInstance(this.currentAccount).starsPaidReactionAmountMax) goto L31;
        this.currentPendingReactions.close();
        this.currentPendingReactions = new PendingPaidReactions(this, r2, r21, r22, ConnectionsManager.getInstance(this.currentAccount).getCurrentTime(), r25);
    L31:
        long r4 = this.currentPendingReactions.amount + r23;
        if (r26 == true) goto L34;
    L47:
        PendingPaidReactions r04 = this.currentPendingReactions;
        if (r21 != null) goto L50;
    L51:
        if (r25 == false) goto L53;
    L52:
        r13 = true;
    L53:
        r04.add(r23, r13);
        PendingPaidReactions r05 = this.currentPendingReactions;
        r05.peer = r27;
        return r05;
    L50:
        if (r21.doesPaidReactionExist() == false) goto L52;
    L34:
        if (r8.balanceAvailable() == false) goto L47;
        if (r8.getBalance(false) >= r4) goto L47;
        this.currentPendingReactions.cancel();
        long r24 = r22.getDialogId();
        if (r24 < 0) goto L40;
        String r15 = UserObject.getForcedFirstName(r22.getMessagesController().getUser(Long.valueOf(r24)));
    L45:
        new StarsIntroActivity.StarsNeededSheet(r10, r22.getResourceProvider(), r4, 5, r15, new StarsController$$ExternalSyntheticLambda71(this, r21, r22, r4, r27), 0).show();
        return null;
    L40:
        TLRPC.Chat r06 = r22.getMessagesController().getChat(Long.valueOf(-r24));
        if (r06 == null) goto L44;
        r9 = r06.title;
    L44:
        r15 = r9;
        goto L45
    L23:
        if (r0.message.equals(r2) == true) goto L29;
    L8:
        if (r8.balanceAvailable() == false) goto L20;
        if (r8.getBalance(false) > 0) goto L20;
        long r42 = r22.getDialogId();
        if (r42 < 0) goto L14;
        r9 = UserObject.getForcedFirstName(r22.getMessagesController().getUser(Long.valueOf(r42)));
    L18:
        new StarsIntroActivity.StarsNeededSheet(r10, r22.getResourceProvider(), r23, 5, r9, new StarsController$$ExternalSyntheticLambda70(this, r21, r22, r23, r27), 0).show();
        return null;
    L14:
        TLRPC.Chat r07 = r22.getMessagesController().getChat(Long.valueOf(-r42));
        if (r07 == null) goto L18;
        r9 = r07.title;
        goto L18
    }

    public void showPaidMessageToast(long r6, MessageObject r8, long r9, Utilities.Callback<HashSet<MessageObject>> r11, Runnable r12, boolean r13) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        PaidMessagesToast r1 = this.currentPaidMessagesToast;
        if (r1 == null) goto L10;
        if (PaidMessagesToast.m19151$$Nest$fgetsent(r1) == false) goto L7;
    L8:
        this.currentPaidMessagesToast = null;
        goto L10
    L7:
        if (PaidMessagesToast.m19152$$Nest$fgetundone(this.currentPaidMessagesToast) == true) goto L8;
    L10:
        if (this.currentPaidMessagesToast == null) goto L19;
        if (r0 != null) goto L13;
    L18:
        this.currentPaidMessagesToast.send();
        this.currentPaidMessagesToast = null;
        goto L19
    L13:
        if (r0.isRemovingFromStack() == true) goto L18;
        PaidMessagesToast r14 = this.currentPaidMessagesToast;
        if (r14.dialogId != r6) goto L18;
        if (r14.fragment != r0) goto L18;
    L19:
        if (r0 == null) goto L31;
        if (r0.isRemovingFromStack() == true) goto L31;
        if (this.currentPaidMessagesToast != null) goto L27;
        this.currentPaidMessagesToast = new PaidMessagesToast(this, r0, r6);
    L27:
        if (this.currentPaidMessagesToast.push(r8, r9, r11, r12, r13) == true) goto L34;
        if (r12 == null) goto L35;
        r12.run();
        return;
    L35:
        return;
    L34:
        return;
    L31:
        if (r12 == null) goto L36;
        r12.run();
        return;
    }

    public void showPriceChangedToast(List<MessageObject> r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (r7.isEmpty() == true) goto L13;
        MessageObject r72 = r7.get(0);
        long r1 = r72.getDialogId();
        int r4 = this.currentAccount;
        if (r1 < 0) goto L9;
        MessagesController.getInstance(r4).loadFullUser(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1)), 0, true);
    L10:
        BulletinFactory.of(LaunchActivity.getSafeLastFragment()).createSimpleBulletin(R.raw.error, StarsIntroActivity.replaceStars(TextUtils.concat(new CharSequence[]{LocaleController.formatPluralString("PaidMessagesSendErrorToast1", (int) r72.messageOwner.errorAllowedPriceStars, new Object[0]), " ", LocaleController.formatPluralString("PaidMessagesSendErrorToast2", (int) r72.messageOwner.errorNewPriceStars, new Object[0])}))).show();
        return;
    L9:
        MessagesController.getInstance(r4).loadFullChat(-r1, 0, true);
        goto L10
    }

    public void showStarsTopup(Activity r8, long r9, String r11) {
        if (balanceAvailable() == true) goto L6;
        getBalance(new StarsController$$ExternalSyntheticLambda29(this, r8, r9, r11));
        return;
    L6:
        showStarsTopupInternal(r8, r9, r11);
    }

    public void stopPaidMessages(long r9, long r11, boolean r13, boolean r14) {
        TL_account.toggleNoPaidMessagesException r0 = new TL_account.toggleNoPaidMessagesException();
        r0.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r9);
        if (r11 == 0) goto L5;
        r0.parent_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r11);
    L5:
        r0.refund_charged = r13;
        r0.require_payment = !r14;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarsController$$ExternalSyntheticLambda38(this, r11, r9, r14));
    }

    public void subscribeTo(String r13, TLRPC.ChatInvite r14, Utilities.Callback2<String, Long> r15) {
        if (r14 != null) goto L4;
        return;
    L4:
        if (r14.subscription_pricing == null) goto L16;
        Context r0 = LaunchActivity.instance;
        if (r0 == null) goto L9;
    L8:
        Context r1 = r0;
        Theme.ResourcesProvider r2 = getResourceProvider();
        long r3 = r14.subscription_pricing.amount;
        if (r1 == null) goto L17;
        int r5 = UserConfig.selectedAccount;
        boolean[] r6 = {false};
        StarsIntroActivity.openStarsChannelInviteSheet(r1, r2, r5, r14, new StarsController$$ExternalSyntheticLambda77(this, r3, r5, r6, r15, r1, r2, r14, r13), new StarsController$$ExternalSyntheticLambda78(this, r6, r15));
        return;
    L17:
        return;
    L9:
        r0 = ApplicationLoader.applicationContext;
        goto L8
    }

    public void undoPaidReaction() {
        PendingPaidReactions r0 = this.currentPendingReactions;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }

    public void updateBalance(TL_stars.StarsAmount r7) {
        if (this.balance.equals(r7) == true) goto L7;
        this.balance = r7;
        this.minus = 0;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starBalanceUpdated, new Object[0]);
        return;
    L7:
        if (this.minus == 0) goto L10;
        this.minus = 0;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.starBalanceUpdated, new Object[0]);
        return;
    }

    public void updateMediaPrice(MessageObject r7, long r8, Runnable r10) {
        updateMediaPrice(r7, r8, r10, false);
    }
}
