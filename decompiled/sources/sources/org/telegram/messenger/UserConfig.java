package org.telegram.messenger;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Base64;
import android.util.LongSparseArray;
import java.util.Arrays;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.SaveToGallerySettingsHelper;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes.dex */
public class UserConfig extends BaseController {
    private static volatile UserConfig[] Instance = null;
    public static final int MAX_ACCOUNT_COUNT = 16;
    public static final int MAX_ACCOUNT_DEFAULT_COUNT = 8;
    public static final int i_dialogsLoadOffsetAccess = 5;
    public static final int i_dialogsLoadOffsetChannelId = 4;
    public static final int i_dialogsLoadOffsetChatId = 3;
    public static final int i_dialogsLoadOffsetDate = 1;
    public static final int i_dialogsLoadOffsetId = 0;
    public static final int i_dialogsLoadOffsetUserId = 2;
    public static int selectedAccount;
    public long autoDownloadConfigLoadTime;
    public int botGuestRatingLoadTime;
    public int botRatingLoadTime;
    LongSparseArray<SaveToGallerySettingsHelper.DialogException> chanelSaveGalleryExceptions;
    public long clientUserId;
    private volatile boolean configLoaded;
    public boolean contactsReimported;
    public int contactsSavedCount;
    private TLRPC.User currentUser;
    public String defaultTopicIcons;
    public boolean draftsLoaded;
    public boolean filtersLoaded;
    public String genericAnimationsStickerPack;
    int globalTtl;
    LongSparseArray<SaveToGallerySettingsHelper.DialogException> groupsSaveGalleryExceptions;
    public boolean hasSecureData;
    public boolean hasValidDialogLoadIds;
    public int lastBroadcastId;
    public int lastContactsSyncTime;
    public int lastHintsSyncTime;
    long lastLoadingTime;
    public int lastMyLocationShareTime;
    public int lastSendMessageId;
    public long lastUpdatedDefaultTopicIcons;
    public long lastUpdatedGenericAnimations;
    public long lastUpdatedPremiumGiftsStickerPack;
    public long lastUpdatedTonGiftsStickerPack;
    public int loginTime;
    public long migrateOffsetAccess;
    public long migrateOffsetChannelId;
    public long migrateOffsetChatId;
    public int migrateOffsetDate;
    public int migrateOffsetId;
    public long migrateOffsetUserId;
    public boolean notificationsSettingsLoaded;
    public boolean notificationsSignUpSettingsLoaded;
    public String premiumGiftsStickerPack;
    public String premiumTonStickerPack;
    public int ratingLoadTime;
    public boolean registeredForPush;
    public volatile byte[] savedPasswordHash;
    public volatile long savedPasswordTime;
    public volatile byte[] savedSaltedPassword;
    public int sharingMyLocationUntil;
    public boolean showCallsTab;
    public boolean showContactsTab;
    public boolean suggestContacts;
    private final Object sync;
    public boolean syncContacts;
    public TL_account.tmpPassword tmpPassword;
    boolean ttlIsLoading;
    public TLRPC.TL_help_termsOfService unacceptedTermsOfService;
    public boolean unreadDialogsLoaded;
    LongSparseArray<SaveToGallerySettingsHelper.DialogException> userSaveGalleryExceptions;
    public int webappRatingLoadTime;

    /* renamed from: $r8$lambda$0qkbol_Y4WJ6kX9-RlnPfv-8DgM, reason: not valid java name */
    public static /* synthetic */ void m5044$r8$lambda$0qkbol_Y4WJ6kX9RlnPfv8DgM(UserConfig r0, TLRPC.User r1) {
        r0.lambda$checkPremiumSelf$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$V8rhOHW_w5bDu4oNGFOZ0Ycv8Nw(UserConfig r0, BaseFragment r1, boolean r2) {
        r0.lambda$setShowContactsTab$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$aAhsBRTvO6qi7tvBO9UXCgLCUko(UserConfig r0, boolean r1) {
        r0.lambda$saveConfig$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$o1MhdqkxuoADK0z6jjMzmCGNbjM(UserConfig r0, TLRPC.User r1) {
        r0.lambda$checkPremiumSelf$1(r1);
    }

    /* renamed from: $r8$lambda$tf31beb1yoOCoGzOH0e7JHYa8-Y, reason: not valid java name */
    public static /* synthetic */ void m5045$r8$lambda$tf31beb1yoOCoGzOH0e7JHYa8Y(UserConfig r0, TLObject r1) {
        r0.lambda$loadGlobalTTl$4(r1);
    }

    /* renamed from: $r8$lambda$wpOzUHShsS49buaQyP9qWO-sJK8, reason: not valid java name */
    public static /* synthetic */ void m5046$r8$lambda$wpOzUHShsS49buaQyP9qWOsJK8(UserConfig r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadGlobalTTl$5(r1, r2);
    }

    static {
        Instance = new UserConfig[16];
    }

    public UserConfig(int r4) {
        super(r4);
        this.sync = new Object();
        this.lastSendMessageId = -210000;
        this.lastBroadcastId = -1;
        this.unreadDialogsLoaded = true;
        this.migrateOffsetId = -1;
        this.migrateOffsetDate = -1;
        this.migrateOffsetUserId = -1;
        this.migrateOffsetChatId = -1;
        this.migrateOffsetChannelId = -1;
        this.migrateOffsetAccess = -1;
        this.syncContacts = false;
        this.suggestContacts = false;
        this.showContactsTab = true;
        this.globalTtl = 0;
        this.ttlIsLoading = false;
    }

    private void checkPremiumSelf(TLRPC.User r3, TLRPC.User r4) {
        if (r3 == null) goto L8;
        if (r4 == null) goto L8;
        if (r3.premium == r4.premium) goto L8;
        AndroidUtilities.runOnUIThread(new UserConfig$$ExternalSyntheticLambda1(this, r4));
        return;
    L8:
        if (r3 != null) goto L11;
        AndroidUtilities.runOnUIThread(new UserConfig$$ExternalSyntheticLambda2(this, r4));
        return;
    }

    public static int getActivatedAccountsCount() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= 16) goto L9;
        if (AccountInstance.getInstance(r0).getUserConfig().isClientActivated() == false) goto L8;
        r1 = r1 + 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r1;
    }

    public static UserConfig getInstance(int r3) {
        UserConfig r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(UserConfig.class);
        UserConfig r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        UserConfig[] r03 = Instance;     // Catch: Throwable -> L9
        UserConfig r2 = new UserConfig(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(UserConfig.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static int getMaxAccountCount() {
        if (hasPremiumOnAccounts() == false) goto L6;
        return 5;
    L6:
        return 3;
    }

    public static int getProductionAccount() {
        int r0 = -1;
    L4:
        if (r0 >= 16) goto L15;
        if (r0 >= 0) goto L7;
        int r1 = selectedAccount;
    L9:
        if (getInstance(r1).isClientActivated() == false) goto L13;
        if (ConnectionsManager.getInstance(r1).isTestBackend() == true) goto L13;
        return r1;
    L13:
        r0 = r0 + 1;
        goto L4
    L7:
        r1 = r0;
        goto L9
    L15:
        return selectedAccount;
    }

    public static boolean hasPremiumOnAccounts() {
        int r1 = 0;
    L4:
        if (r1 >= 16) goto L12;
        if (AccountInstance.getInstance(r1).getUserConfig().isClientActivated() == false) goto L11;
        if (AccountInstance.getInstance(r1).getUserConfig().getUserConfig().isPremium() == false) goto L11;
        return true;
    L11:
        r1 = r1 + 1;
        goto L4
    L12:
        return false;
    }

    public static boolean isValidAccount(int r1) {
        if (r1 >= 0) goto L4;
        return false;
    L4:
        if (r1 < 16) goto L6;
        return false;
    L6:
        if (getInstance(r1).isClientActivated() == false) goto L12;
        return true;
    L12:
        return false;
    }

    private /* synthetic */ void lambda$checkPremiumSelf$1(TLRPC.User r4) {
        getMessagesController().updatePremium(r4.premium);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.currentUserPremiumStatusChanged, new Object[0]);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.premiumStatusChangedGlobal, new Object[0]);
        getMediaDataController().loadPremiumPromo(false);
        getMediaDataController().loadReactions(false, null);
        getMessagesController().getStoriesController().invalidateStoryLimit();
    }

    private /* synthetic */ void lambda$checkPremiumSelf$2(TLRPC.User r3) {
        getMessagesController().updatePremium(r3.premium);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.currentUserPremiumStatusChanged, new Object[0]);
        getMediaDataController().loadPremiumPromo(true);
    }

    private /* synthetic */ void lambda$loadGlobalTTl$4(TLObject r4) {
        if (r4 == null) goto L5;
        this.globalTtl = ((TLRPC.TL_defaultHistoryTTL) r4).period / 60;
        getNotificationCenter().postNotificationName(NotificationCenter.didUpdateGlobalAutoDeleteTimer, new Object[0]);
        this.ttlIsLoading = false;
        this.lastLoadingTime = System.currentTimeMillis();
        return;
    }

    private /* synthetic */ void lambda$loadGlobalTTl$5(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new UserConfig$$ExternalSyntheticLambda4(this, r1));
    }

    private /* synthetic */ void lambda$saveConfig$0(boolean r7) {
        if (this.configLoaded == false) goto L45;
        Object r0 = this.sync;
        monitor-enter(r0);
        SharedPreferences.Editor r1 = getPreferences().edit();     // Catch: Throwable -> L10 Exception -> L12
        if (this.currentAccount != 0) goto L14;
        r1.putInt("selectedAccount", selectedAccount);     // Catch: Throwable -> L10 Exception -> L12
    L14:
        r1.putBoolean("registeredForPush", this.registeredForPush);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("lastSendMessageId", this.lastSendMessageId);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("contactsSavedCount", this.contactsSavedCount);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("lastBroadcastId", this.lastBroadcastId);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("lastContactsSyncTime", this.lastContactsSyncTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("lastHintsSyncTime", this.lastHintsSyncTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("draftsLoaded", this.draftsLoaded);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("unreadDialogsLoaded", this.unreadDialogsLoaded);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("ratingLoadTime", this.ratingLoadTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("botRatingLoadTime", this.botRatingLoadTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("botGuestRatingLoadTime", this.botGuestRatingLoadTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("webappRatingLoadTime", this.webappRatingLoadTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("contactsReimported", this.contactsReimported);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("loginTime", this.loginTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("syncContacts", this.syncContacts);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("showCallsTab", this.showCallsTab);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("showContactsTab", this.showContactsTab);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("suggestContacts", this.suggestContacts);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("hasSecureData", this.hasSecureData);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("notificationsSettingsLoaded4", this.notificationsSettingsLoaded);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("notificationsSignUpSettingsLoaded", this.notificationsSignUpSettingsLoaded);     // Catch: Throwable -> L10 Exception -> L12
        r1.putLong("autoDownloadConfigLoadTime", this.autoDownloadConfigLoadTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("hasValidDialogLoadIds", this.hasValidDialogLoadIds);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("sharingMyLocationUntil", this.sharingMyLocationUntil);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("lastMyLocationShareTime", this.lastMyLocationShareTime);     // Catch: Throwable -> L10 Exception -> L12
        r1.putBoolean("filtersLoaded", this.filtersLoaded);     // Catch: Throwable -> L10 Exception -> L12
        r1.putString("premiumGiftsStickerPack", this.premiumGiftsStickerPack);     // Catch: Throwable -> L10 Exception -> L12
        r1.putLong("lastUpdatedPremiumGiftsStickerPack", this.lastUpdatedPremiumGiftsStickerPack);     // Catch: Throwable -> L10 Exception -> L12
        r1.putString("genericAnimationsStickerPack", this.genericAnimationsStickerPack);     // Catch: Throwable -> L10 Exception -> L12
        r1.putLong("lastUpdatedGenericAnimations", this.lastUpdatedGenericAnimations);     // Catch: Throwable -> L10 Exception -> L12
        r1.putInt("6migrateOffsetId", this.migrateOffsetId);     // Catch: Throwable -> L10 Exception -> L12
        if (this.migrateOffsetId == (-1)) goto L17;
        r1.putInt("6migrateOffsetDate", this.migrateOffsetDate);     // Catch: Throwable -> L10 Exception -> L12
        r1.putLong("6migrateOffsetUserId", this.migrateOffsetUserId);     // Catch: Throwable -> L10 Exception -> L12
        r1.putLong("6migrateOffsetChatId", this.migrateOffsetChatId);     // Catch: Throwable -> L10 Exception -> L12
        r1.putLong("6migrateOffsetChannelId", this.migrateOffsetChannelId);     // Catch: Throwable -> L10 Exception -> L12
        r1.putLong("6migrateOffsetAccess", this.migrateOffsetAccess);     // Catch: Throwable -> L10 Exception -> L12
    L17:
        TLRPC.TL_help_termsOfService r2 = this.unacceptedTermsOfService;     // Catch: Throwable -> L10 Exception -> L12
        if (r2 != null) goto L40;
        r1.remove("terms");     // Catch: Throwable -> L10 Exception -> L12
    L23:
        SharedConfig.saveConfig();     // Catch: Throwable -> L10 Exception -> L12
        if (this.tmpPassword == null) goto L26;
        SerializedData r22 = new SerializedData();     // Catch: Throwable -> L10 Exception -> L12
        this.tmpPassword.serializeToStream(r22);     // Catch: Throwable -> L10 Exception -> L12
        r1.putString("tmpPassword", Base64.encodeToString(r22.toByteArray(), 0));     // Catch: Throwable -> L10 Exception -> L12
        r22.cleanup();     // Catch: Throwable -> L10 Exception -> L12
    L28:
        if (this.currentUser == null) goto L31;
        if (r7 == false) goto L32;
        SerializedData r72 = new SerializedData();     // Catch: Throwable -> L10 Exception -> L12
        this.currentUser.serializeToStream(r72);     // Catch: Throwable -> L10 Exception -> L12
        r1.putString("user", Base64.encodeToString(r72.toByteArray(), 0));     // Catch: Throwable -> L10 Exception -> L12
        r72.cleanup();     // Catch: Throwable -> L10 Exception -> L12
    L32:
        r1.apply();     // Catch: Throwable -> L10 Exception -> L12
    L35:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L31:
        r1.remove("user");     // Catch: Throwable -> L10 Exception -> L12
        goto L32
    L26:
        r1.remove("tmpPassword");     // Catch: Throwable -> L10 Exception -> L12
        goto L28
    L40:
        SerializedData r4 = new SerializedData(r2.getObjectSize());     // Catch: Throwable -> L10 Exception -> L39
        this.unacceptedTermsOfService.serializeToStream(r4);     // Catch: Throwable -> L10 Exception -> L39
        r1.putString("terms", Base64.encodeToString(r4.toByteArray(), 0));     // Catch: Throwable -> L10 Exception -> L39
        r4.cleanup();     // Catch: Throwable -> L10 Exception -> L39
    L10:
        th = move-exception;
        throw th;
    L12:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L10
        goto L35
    }

    private /* synthetic */ void lambda$setShowContactsTab$3(BaseFragment r1, boolean r2) {
        getUserConfig().setShowContactsTab(r1, !r2);
    }

    public void checkSavedPassword() {
        if (this.savedSaltedPassword != null) goto L7;
        if (this.savedPasswordHash != null) goto L7;
        return;
    L7:
        if (Math.abs(SystemClock.elapsedRealtime() - this.savedPasswordTime) < 1800000) goto L11;
        resetSavedPassword();
        return;
    }

    public void clearConfig() {
        getPreferences().edit().clear().apply();
        int r0 = 0;
        this.sharingMyLocationUntil = 0;
        this.lastMyLocationShareTime = 0;
        this.currentUser = null;
        this.clientUserId = 0;
        this.registeredForPush = false;
        this.contactsSavedCount = 0;
        this.lastSendMessageId = -210000;
        this.lastBroadcastId = -1;
        this.notificationsSettingsLoaded = false;
        this.notificationsSignUpSettingsLoaded = false;
        this.migrateOffsetId = -1;
        this.migrateOffsetDate = -1;
        this.migrateOffsetUserId = -1;
        this.migrateOffsetChatId = -1;
        this.migrateOffsetChannelId = -1;
        this.migrateOffsetAccess = -1;
        this.ratingLoadTime = 0;
        this.botRatingLoadTime = 0;
        this.botGuestRatingLoadTime = 0;
        this.webappRatingLoadTime = 0;
        this.draftsLoaded = false;
        this.contactsReimported = true;
        this.syncContacts = true;
        this.showCallsTab = false;
        this.suggestContacts = true;
        this.unreadDialogsLoaded = true;
        this.hasValidDialogLoadIds = true;
        this.unacceptedTermsOfService = null;
        this.filtersLoaded = false;
        this.hasSecureData = false;
        this.loginTime = (int) (System.currentTimeMillis() / 1000);
        this.lastContactsSyncTime = ((int) (System.currentTimeMillis() / 1000)) - 82800;
        this.lastHintsSyncTime = ((int) (System.currentTimeMillis() / 1000)) - 90000;
        resetSavedPassword();
    L4:
        if (r0 >= 16) goto L9;
        if (AccountInstance.getInstance(r0).getUserConfig().isClientActivated() == true) goto L10;
        r0 = r0 + 1;
    L10:
        saveConfig(true);
        return;
    L9:
        SharedConfig.clearConfig();
        goto L10
    }

    public void clearFilters() {
        getPreferences().edit().remove("filtersLoaded").apply();
        this.filtersLoaded = false;
    }

    public void clearPinnedDialogsLoaded() {
        SharedPreferences.Editor r0 = getPreferences().edit();
        Iterator<String> r3 = getPreferences().getAll().keySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        String r1 = r3.next();
        if (r1.startsWith("2pinnedDialogsLoaded") == false) goto L4;
        r0.remove(r1);
        goto L4
    L8:
        r0.apply();
    }

    public String getClientPhone() {
        Object r0 = this.sync;
        monitor-enter(r0);
        TLRPC.User r1 = this.currentUser;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        String r12 = r1.phone;     // Catch: Throwable -> L9
        if (r12 == null) goto L11;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L11:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        th = move-exception;
        throw th;
    }

    public long getClientUserId() {
        Object r0 = this.sync;
        monitor-enter(r0);
        TLRPC.User r3 = this.currentUser;     // Catch: Throwable -> L7
        if (r3 == null) goto L9;
        long r1 = r3.id;     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L9:
        r1 = 0;
    L7:
        th = move-exception;
        throw th;
    }

    public TLRPC.User getCurrentUser() {
        Object r0 = this.sync;
        monitor-enter(r0);
        TLRPC.User r1 = this.currentUser;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public long[] getDialogLoadOffsets(int r18) {
        SharedPreferences r1 = getPreferences();
        StringBuilder r2 = new StringBuilder("2dialogsLoadOffsetId");
        Object r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r18 != 0) goto L5;
        Object r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r2.append(r4);
        String r22 = r2.toString();
        int r5 = -1;
        if (this.hasValidDialogLoadIds == false) goto L9;
        int r42 = 0;
    L10:
        int r23 = r1.getInt(r22, r42);
        StringBuilder r43 = new StringBuilder("2dialogsLoadOffsetDate");
        if (r18 != 0) goto L13;
        Object r7 = _UrlKt.FRAGMENT_ENCODE_SET;
    L14:
        r43.append(r7);
        String r44 = r43.toString();
        if (this.hasValidDialogLoadIds == false) goto L17;
        r5 = 0;
    L17:
        int r45 = r1.getInt(r44, r5);
        StringBuilder r52 = new StringBuilder("2dialogsLoadOffsetUserId");
        if (r18 != 0) goto L20;
        Object r72 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        r52.append(r72);
        String r53 = r52.toString();
        if (this.hasValidDialogLoadIds == false) goto L24;
        long r12 = 0;
    L25:
        long r122 = AndroidUtilities.getPrefIntOrLong(r1, r53, r12);
        StringBuilder r54 = new StringBuilder("2dialogsLoadOffsetChatId");
        if (r18 != 0) goto L28;
        Object r73 = _UrlKt.FRAGMENT_ENCODE_SET;
    L29:
        r54.append(r73);
        String r55 = r54.toString();
        if (this.hasValidDialogLoadIds == false) goto L32;
        long r14 = 0;
    L33:
        long r142 = AndroidUtilities.getPrefIntOrLong(r1, r55, r14);
        StringBuilder r56 = new StringBuilder("2dialogsLoadOffsetChannelId");
        if (r18 != 0) goto L36;
        Object r74 = _UrlKt.FRAGMENT_ENCODE_SET;
    L37:
        r56.append(r74);
        String r57 = r56.toString();
        if (this.hasValidDialogLoadIds == false) goto L41;
        long r6 = 0;
    L42:
        long r58 = AndroidUtilities.getPrefIntOrLong(r1, r57, r6);
        StringBuilder r75 = new StringBuilder("2dialogsLoadOffsetAccess");
        if (r18 == 0) goto L46;
        r3 = Integer.valueOf(r18);
    L46:
        r75.append(r3);
        String r32 = r75.toString();
        if (this.hasValidDialogLoadIds == false) goto L49;
        long r8 = 0;
    L51:
        return new long[]{r23, r45, r122, r142, r58, r1.getLong(r32, r8)};
    L49:
        r8 = -1;
        goto L51
    L41:
        r6 = -1;
        goto L42
    L36:
        r74 = Integer.valueOf(r18);
        goto L37
    L32:
        r14 = -1;
        goto L33
    L28:
        r73 = Integer.valueOf(r18);
        goto L29
    L24:
        r12 = -1;
        goto L25
    L20:
        r72 = Integer.valueOf(r18);
        goto L21
    L13:
        r7 = Integer.valueOf(r18);
        goto L14
    L9:
        r42 = -1;
        goto L10
    L5:
        r4 = Integer.valueOf(r18);
        goto L6
    }

    public Long getEmojiStatus() {
        return UserObject.getEmojiStatusDocumentId(this.currentUser);
    }

    public int getGlobalTTl() {
        return this.globalTtl;
    }

    public int getNewMessageId() {
        Object r0 = this.sync;
        monitor-enter(r0);
        int r1 = this.lastSendMessageId;     // Catch: Throwable -> L7
        this.lastSendMessageId = r1 - 1;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public SharedPreferences getPreferences() {
        if (this.currentAccount != 0) goto L7;
        return ApplicationLoader.applicationContext.getSharedPreferences("userconfing", 0);
    L7:
        return ApplicationLoader.applicationContext.getSharedPreferences("userconfig" + this.currentAccount, 0);
    }

    public LongSparseArray<SaveToGallerySettingsHelper.DialogException> getSaveGalleryExceptions(int r5) {
        if (r5 != 1) goto L10;
        if (this.userSaveGalleryExceptions != null) goto L8;
        this.userSaveGalleryExceptions = SaveToGallerySettingsHelper.loadExceptions(ApplicationLoader.applicationContext.getSharedPreferences(SaveToGallerySettingsHelper.USERS_PREF_NAME + "_" + this.currentAccount, 0));
    L8:
        return this.userSaveGalleryExceptions;
    L10:
        if (r5 != 2) goto L17;
        if (this.groupsSaveGalleryExceptions != null) goto L15;
        this.groupsSaveGalleryExceptions = SaveToGallerySettingsHelper.loadExceptions(ApplicationLoader.applicationContext.getSharedPreferences(SaveToGallerySettingsHelper.GROUPS_PREF_NAME + "_" + this.currentAccount, 0));
    L15:
        return this.groupsSaveGalleryExceptions;
    L17:
        if (r5 == 4) goto L19;
        return null;
    L19:
        if (this.chanelSaveGalleryExceptions != null) goto L22;
        this.chanelSaveGalleryExceptions = SaveToGallerySettingsHelper.loadExceptions(ApplicationLoader.applicationContext.getSharedPreferences(SaveToGallerySettingsHelper.CHANNELS_PREF_NAME + "_" + this.currentAccount, 0));
    L22:
        return this.chanelSaveGalleryExceptions;
    }

    public int getTotalDialogsCount(int r3) {
        SharedPreferences r2 = getPreferences();
        StringBuilder r0 = new StringBuilder("2totalDialogsLoadCount");
        if (r3 != 0) goto L5;
        Object r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.append(r32);
        return r2.getInt(r0.toString(), 0);
    L5:
        r32 = Integer.valueOf(r3);
        goto L6
    }

    public boolean isClientActivated() {
        Object r0 = this.sync;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.currentUser == null) goto L7;
        boolean r1 = true;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r1;
    L7:
        r1 = false;
        goto L8
    }

    public boolean isConfigLoaded() {
        return this.configLoaded;
    }

    public boolean isPinnedDialogsLoaded(int r3) {
        return getPreferences().getBoolean("2pinnedDialogsLoaded" + r3, false);
    }

    public boolean isPremium() {
        TLRPC.User r0 = this.currentUser;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.premium;
    }

    public void loadConfig() {
        Object r0 = this.sync;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.configLoaded == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        SharedPreferences r1 = getPreferences();     // Catch: Throwable -> L8
        if (this.currentAccount != 0) goto L13;
        selectedAccount = r1.getInt("selectedAccount", 0);     // Catch: Throwable -> L8
    L13:
        this.registeredForPush = r1.getBoolean("registeredForPush", false);     // Catch: Throwable -> L8
        this.lastSendMessageId = r1.getInt("lastSendMessageId", -210000);     // Catch: Throwable -> L8
        this.contactsSavedCount = r1.getInt("contactsSavedCount", 0);     // Catch: Throwable -> L8
        this.lastBroadcastId = r1.getInt("lastBroadcastId", -1);     // Catch: Throwable -> L8
        this.lastContactsSyncTime = r1.getInt("lastContactsSyncTime", ((int) (System.currentTimeMillis() / 1000)) - 82800);     // Catch: Throwable -> L8
        this.lastHintsSyncTime = r1.getInt("lastHintsSyncTime", ((int) (System.currentTimeMillis() / 1000)) - 90000);     // Catch: Throwable -> L8
        this.draftsLoaded = r1.getBoolean("draftsLoaded", false);     // Catch: Throwable -> L8
        this.unreadDialogsLoaded = r1.getBoolean("unreadDialogsLoaded", false);     // Catch: Throwable -> L8
        this.contactsReimported = r1.getBoolean("contactsReimported", false);     // Catch: Throwable -> L8
        this.ratingLoadTime = r1.getInt("ratingLoadTime", 0);     // Catch: Throwable -> L8
        this.botRatingLoadTime = r1.getInt("botRatingLoadTime", 0);     // Catch: Throwable -> L8
        this.botGuestRatingLoadTime = r1.getInt("botGuestRatingLoadTime", 0);     // Catch: Throwable -> L8
        this.webappRatingLoadTime = r1.getInt("webappRatingLoadTime", 0);     // Catch: Throwable -> L8
        this.loginTime = r1.getInt("loginTime", this.currentAccount);     // Catch: Throwable -> L8
        this.syncContacts = r1.getBoolean("syncContacts", true);     // Catch: Throwable -> L8
        this.showCallsTab = r1.getBoolean("showCallsTab", false);     // Catch: Throwable -> L8
        this.showContactsTab = r1.getBoolean("showContactsTab", true);     // Catch: Throwable -> L8
        this.suggestContacts = r1.getBoolean("suggestContacts", true);     // Catch: Throwable -> L8
        this.hasSecureData = r1.getBoolean("hasSecureData", false);     // Catch: Throwable -> L8
        this.notificationsSettingsLoaded = r1.getBoolean("notificationsSettingsLoaded4", false);     // Catch: Throwable -> L8
        this.notificationsSignUpSettingsLoaded = r1.getBoolean("notificationsSignUpSettingsLoaded", false);     // Catch: Throwable -> L8
        this.autoDownloadConfigLoadTime = r1.getLong("autoDownloadConfigLoadTime", 0);     // Catch: Throwable -> L8
        if (r1.contains("2dialogsLoadOffsetId") == false) goto L16;
    L19:
        boolean r2 = true;
    L20:
        this.hasValidDialogLoadIds = r2;     // Catch: Throwable -> L8
        this.sharingMyLocationUntil = r1.getInt("sharingMyLocationUntil", 0);     // Catch: Throwable -> L8
        this.lastMyLocationShareTime = r1.getInt("lastMyLocationShareTime", 0);     // Catch: Throwable -> L8
        this.filtersLoaded = r1.getBoolean("filtersLoaded", false);     // Catch: Throwable -> L8
        this.premiumGiftsStickerPack = r1.getString("premiumGiftsStickerPack", null);     // Catch: Throwable -> L8
        this.lastUpdatedPremiumGiftsStickerPack = r1.getLong("lastUpdatedPremiumGiftsStickerPack", 0);     // Catch: Throwable -> L8
        this.genericAnimationsStickerPack = r1.getString("genericAnimationsStickerPack", null);     // Catch: Throwable -> L8
        this.lastUpdatedGenericAnimations = r1.getLong("lastUpdatedGenericAnimations", 0);     // Catch: Throwable -> L8
        String r22 = r1.getString("terms", null);     // Catch: Throwable -> L8 Exception -> L27
        if (r22 == null) goto L29;
        byte[] r23 = Base64.decode(r22, 0);     // Catch: Throwable -> L8 Exception -> L27
        if (r23 == null) goto L29;
        SerializedData r9 = new SerializedData(r23);     // Catch: Throwable -> L8 Exception -> L27
        this.unacceptedTermsOfService = TLRPC.TL_help_termsOfService.TLdeserialize(r9, r9.readInt32(false), false);     // Catch: Throwable -> L8 Exception -> L27
        r9.cleanup();     // Catch: Throwable -> L8 Exception -> L27
    L29:
        int r24 = r1.getInt("6migrateOffsetId", 0);     // Catch: Throwable -> L8
        this.migrateOffsetId = r24;     // Catch: Throwable -> L8
        if (r24 == (-1)) goto L32;
        this.migrateOffsetDate = r1.getInt("6migrateOffsetDate", 0);     // Catch: Throwable -> L8
        this.migrateOffsetUserId = AndroidUtilities.getPrefIntOrLong(r1, "6migrateOffsetUserId", 0);     // Catch: Throwable -> L8
        this.migrateOffsetChatId = AndroidUtilities.getPrefIntOrLong(r1, "6migrateOffsetChatId", 0);     // Catch: Throwable -> L8
        this.migrateOffsetChannelId = AndroidUtilities.getPrefIntOrLong(r1, "6migrateOffsetChannelId", 0);     // Catch: Throwable -> L8
        this.migrateOffsetAccess = r1.getLong("6migrateOffsetAccess", 0);     // Catch: Throwable -> L8
    L32:
        String r25 = r1.getString("tmpPassword", null);     // Catch: Throwable -> L8
        if (r25 == null) goto L37;
        byte[] r26 = Base64.decode(r25, 0);     // Catch: Throwable -> L8
        if (r26 == null) goto L37;
        SerializedData r4 = new SerializedData(r26);     // Catch: Throwable -> L8
        this.tmpPassword = TL_account.tmpPassword.TLdeserialize(r4, r4.readInt32(false), false);     // Catch: Throwable -> L8
        r4.cleanup();     // Catch: Throwable -> L8
    L37:
        String r12 = r1.getString("user", null);     // Catch: Throwable -> L8
        if (r12 == null) goto L42;
        byte[] r13 = Base64.decode(r12, 0);     // Catch: Throwable -> L8
        if (r13 == null) goto L42;
        SerializedData r27 = new SerializedData(r13);     // Catch: Throwable -> L8
        this.currentUser = TLRPC.User.TLdeserialize(r27, r27.readInt32(false), false);     // Catch: Throwable -> L8
        r27.cleanup();     // Catch: Throwable -> L8
    L42:
        TLRPC.User r14 = this.currentUser;     // Catch: Throwable -> L8
        if (r14 == null) goto L45;
        checkPremiumSelf(null, r14);     // Catch: Throwable -> L8
        this.clientUserId = this.currentUser.id;     // Catch: Throwable -> L8
    L45:
        this.configLoaded = true;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L27:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        goto L29
    L16:
        if (r1.getBoolean("hasValidDialogLoadIds", false) == true) goto L19;
        r2 = false;
        goto L20
    }

    public void loadGlobalTTl() {
        if (this.ttlIsLoading == false) goto L5;
        return;
    L5:
        if ((System.currentTimeMillis() - this.lastLoadingTime) < 60000) goto L10;
        this.ttlIsLoading = true;
        TLRPC.TL_messages_getDefaultHistoryTTL r0 = new TLRPC.TL_messages_getDefaultHistoryTTL();
        getConnectionsManager().sendRequest(r0, new UserConfig$$ExternalSyntheticLambda0(this));
        return;
    }

    public void resetSavedPassword() {
        this.savedPasswordTime = 0;
        if (this.savedPasswordHash == null) goto L6;
        Arrays.fill(this.savedPasswordHash, (byte) 0);
        this.savedPasswordHash = null;
    L6:
        if (this.savedSaltedPassword == null) goto L9;
        Arrays.fill(this.savedSaltedPassword, (byte) 0);
        this.savedSaltedPassword = null;
        return;
    }

    public void saveConfig(boolean r3) {
        NotificationCenter.getInstance(this.currentAccount).doOnIdle(new UserConfig$$ExternalSyntheticLambda5(this, r3));
    }

    public void savePassword(byte[] r3, byte[] r4) {
        this.savedPasswordTime = SystemClock.elapsedRealtime();
        this.savedPasswordHash = r3;
        this.savedSaltedPassword = r4;
    }

    public void setCurrentUser(TLRPC.User r5) {
        Object r0 = this.sync;
        monitor-enter(r0);
        TLRPC.User r1 = this.currentUser;     // Catch: Throwable -> L7
        this.currentUser = r5;     // Catch: Throwable -> L7
        this.clientUserId = r5.id;     // Catch: Throwable -> L7
        checkPremiumSelf(r1, r5);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setDialogsLoadOffset(int r4, int r5, int r6, long r7, long r9, long r11, long r13) {
        SharedPreferences.Editor r3 = getPreferences().edit();
        StringBuilder r0 = new StringBuilder("2dialogsLoadOffsetId");
        Object r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r4 != 0) goto L5;
        Object r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.append(r2);
        r3.putInt(r0.toString(), r5);
        StringBuilder r52 = new StringBuilder("2dialogsLoadOffsetDate");
        if (r4 != 0) goto L9;
        Object r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r52.append(r02);
        r3.putInt(r52.toString(), r6);
        StringBuilder r53 = new StringBuilder("2dialogsLoadOffsetUserId");
        if (r4 != 0) goto L13;
        Object r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L14:
        r53.append(r62);
        r3.putLong(r53.toString(), r7);
        StringBuilder r54 = new StringBuilder("2dialogsLoadOffsetChatId");
        if (r4 != 0) goto L17;
        Object r63 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        r54.append(r63);
        r3.putLong(r54.toString(), r9);
        StringBuilder r55 = new StringBuilder("2dialogsLoadOffsetChannelId");
        if (r4 != 0) goto L21;
        Object r64 = _UrlKt.FRAGMENT_ENCODE_SET;
    L22:
        r55.append(r64);
        r3.putLong(r55.toString(), r11);
        StringBuilder r56 = new StringBuilder("2dialogsLoadOffsetAccess");
        if (r4 == 0) goto L26;
        r1 = Integer.valueOf(r4);
    L26:
        r56.append(r1);
        r3.putLong(r56.toString(), r13);
        r3.putBoolean("hasValidDialogLoadIds", true);
        r3.apply();
        return;
    L21:
        r64 = Integer.valueOf(r4);
        goto L22
    L17:
        r63 = Integer.valueOf(r4);
        goto L18
    L13:
        r62 = Integer.valueOf(r4);
        goto L14
    L9:
        r02 = Integer.valueOf(r4);
        goto L10
    L5:
        r2 = Integer.valueOf(r4);
        goto L6
    }

    public void setGlobalTtl(int r1) {
        this.globalTtl = r1;
    }

    public void setPinnedDialogsLoaded(int r3, boolean r4) {
        getPreferences().edit().putBoolean("2pinnedDialogsLoaded" + r3, r4).apply();
    }

    public void setShowCallsTab(boolean r2) {
        if (this.showCallsTab == r2) goto L6;
        this.showCallsTab = r2;
        saveConfig(false);
        return;
    }

    public void setShowContactsTab(BaseFragment r9, boolean r10) {
        if (r10 != this.showContactsTab) goto L5;
        return;
    L5:
        this.showContactsTab = r10;
        saveConfig(false);
        getNotificationCenter().postNotificationName(NotificationCenter.contactsTabVisibleToggled, new Object[0]);
        if (r9 == null) goto L9;
        BulletinFactory r0 = BulletinFactory.of(r9);
    L8:
        BulletinFactory r1 = r0;
        int r2 = R.raw.contact_check;
        if (r10 == false) goto L13;
        int r02 = R.string.ContactsTabWasShownTitle;
    L14:
        r1.createSimpleBulletin(r2, LocaleController.getString(r02), LocaleController.getString(R.string.UndoNoCaps), 5000, true, new UserConfig$$ExternalSyntheticLambda3(this, r9, r10)).setDuration(5000).show();
        return;
    L13:
        r02 = R.string.ContactsTabWasHiddenTitle;
        goto L14
    L9:
        r0 = BulletinFactory.global();
        goto L8
    }

    public void setTotalDialogsCount(int r3, int r4) {
        SharedPreferences.Editor r2 = getPreferences().edit();
        StringBuilder r0 = new StringBuilder("2totalDialogsLoadCount");
        if (r3 != 0) goto L5;
        Object r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.append(r32);
        r2.putInt(r0.toString(), r4).apply();
        return;
    L5:
        r32 = Integer.valueOf(r3);
        goto L6
    }

    public void updateSaveGalleryExceptions(int r4, LongSparseArray<SaveToGallerySettingsHelper.DialogException> r5) {
        if (r4 != 1) goto L7;
        this.userSaveGalleryExceptions = r5;
        SaveToGallerySettingsHelper.saveExceptions(ApplicationLoader.applicationContext.getSharedPreferences(SaveToGallerySettingsHelper.USERS_PREF_NAME + "_" + this.currentAccount, 0), this.userSaveGalleryExceptions);
        return;
    L7:
        if (r4 != 2) goto L11;
        this.groupsSaveGalleryExceptions = r5;
        SaveToGallerySettingsHelper.saveExceptions(ApplicationLoader.applicationContext.getSharedPreferences(SaveToGallerySettingsHelper.GROUPS_PREF_NAME + "_" + this.currentAccount, 0), this.groupsSaveGalleryExceptions);
        return;
    L11:
        if (r4 != 4) goto L14;
        this.chanelSaveGalleryExceptions = r5;
        SaveToGallerySettingsHelper.saveExceptions(ApplicationLoader.applicationContext.getSharedPreferences(SaveToGallerySettingsHelper.CHANNELS_PREF_NAME + "_" + this.currentAccount, 0), this.chanelSaveGalleryExceptions);
        return;
    }
}
