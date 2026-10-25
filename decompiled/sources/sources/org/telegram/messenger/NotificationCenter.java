package org.telegram.messenger;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.collection.MutableIntList;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.http.HttpStatusCodesKt;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class NotificationCenter {
    private static final long EXPIRE_NOTIFICATIONS_TIME = 5017;
    private static final NotificationCenter[] Instance = null;
    public static final int activeAccountChanged = 0;
    public static final int activeAuctionsUpdated = 0;
    public static final int activeGroupCallsUpdated = 0;
    public static final int activityPermissionsGranted = 0;
    public static final int adminedChannelsLoaded = 0;
    public static final int albumsDidLoad = 0;
    public static boolean alreadyLogged = false;
    public static final int animatedEmojiDocumentLoaded = 0;
    public static final int appConfigUpdated = 0;
    public static final int appDidLogout = 0;
    public static final int appUpdateAvailable = 0;
    public static final int appUpdateLoading = 0;
    public static final int applyGroupCallVisibleParticipants = 0;
    public static final int archivedStickersCountDidLoad = 0;
    public static final int articleClosed = 0;
    public static final int attachMenuBotsDidLoad = 0;
    public static final int audioDidSent = 0;
    public static final int audioRecordTooShort = 0;
    public static final int audioRouteChanged = 0;
    public static final int availableEffectsUpdate = 0;
    public static final int billingConfirmPurchaseError = 0;
    public static final int billingProductDetailsUpdated = 0;
    public static final int blockedUsersDidLoad = 0;
    public static final int bookmarkAdded = 0;
    public static final int boostByChannelCreated = 0;
    public static final int boostedChannelByUser = 0;
    public static final int botDownloadsUpdate = 0;
    public static final int botForumDraftDelete = 0;
    public static final int botForumDraftUpdate = 0;
    public static final int botForumTopicDidCreate = 0;
    public static final int botInfoDidLoad = 0;
    public static final int botKeyboardDidLoad = 0;
    public static final int botStarsTransactionsLoaded = 0;
    public static final int botStarsUpdated = 0;
    public static final int businessLinkCreated = 0;
    public static final int businessLinksUpdated = 0;
    public static final int businessMessagesUpdated = 0;
    public static final int callTabsVisibleToggled = 0;
    public static final int cameraInitied = 0;
    public static final int changeRepliesCounter = 0;
    public static final int channelConnectedBotsUpdate = 0;
    public static final int channelRecommendationsLoaded = 0;
    public static final int channelRightsUpdated = 0;
    public static final int channelStarsUpdated = 0;
    public static final int channelSuggestedBotsUpdate = 0;
    public static final int chatAvailableReactionsUpdated = 0;
    public static final int chatDidCreated = 0;
    public static final int chatDidFailCreate = 0;
    public static final int chatInfoCantLoad = 0;
    public static final int chatInfoDidLoad = 0;
    public static final int chatOnlineCountDidLoad = 0;
    public static final int chatSearchResultsAvailable = 0;
    public static final int chatSearchResultsLoading = 0;
    public static final int chatSwitchedForum = 0;
    public static final int chatWasBoostedByUser = 0;
    public static final int chatlistFolderUpdate = 0;
    public static final int closeChatActivity = 0;
    public static final int closeChats = 0;
    public static final int closeInCallActivity = 0;
    public static final int closeOtherAppActivities = 0;
    public static final int closeProfileActivity = 0;
    public static final int closeSearchByActiveAction = 0;
    public static final int commentsRead = 0;
    public static final int commonChatsLoaded = 0;
    public static final int communityPendingRequestsUpdate = 0;
    public static final int communitySwitchedCollapsed = 0;
    public static final int conferenceEmojiUpdated = 0;
    public static final int configLoaded = 0;
    public static final int contactsDidLoad = 0;
    public static final int contactsImported = 0;
    public static final int contactsPermissionBadgeCheck = 0;
    public static final int contactsTabVisibleToggled = 0;
    public static final int contentSettingsLoaded = 0;
    public static final int currentUserPremiumStatusChanged = 0;
    public static final int currentUserShowLimitReachedDialog = 0;
    public static final int customStickerCreated = 0;
    public static final int customTypefacesLoaded = 0;
    public static final int dialogDeleted = 0;
    public static final int dialogFiltersUpdated = 0;
    public static final int dialogIsTranslatable = 0;
    public static final int dialogPhotosLoaded = 0;
    public static final int dialogPhotosUpdate = 0;
    public static final int dialogTranslate = 0;
    public static final int dialogsNeedReload = 0;
    public static final int dialogsUnreadCounterChanged = 0;
    public static final int dialogsUnreadPollVotesCounterChanged = 0;
    public static final int dialogsUnreadReactionsCounterChanged = 0;
    public static final int diceStickersDidLoad = 0;
    public static final int didApplyNewTheme = 0;
    public static final int didClearDatabase = 0;
    public static final int didCreatedNewDeleteTask = 0;
    public static final int didEndCall = 0;
    public static final int didGenerateFingerprintKeyPair = 0;
    public static final int didLoadChatAdmins = 0;
    public static final int didLoadChatInviter = 0;
    public static final int didLoadPinnedMessages = 0;
    public static final int didLoadSendAsPeers = 0;
    public static final int didLoadSponsoredMessages = 0;
    public static final int didReceiveCall = 0;
    public static final int didReceiveNewMessages = 1;
    public static final int didReceiveSmsCode = 0;
    public static final int didReceivedWebpages = 0;
    public static final int didReceivedWebpagesInUpdates = 0;
    public static final int didRemoveTwoStepPassword = 0;
    public static final int didReplacedPhotoInMemCache = 0;
    public static final int didSetNewTheme = 0;
    public static final int didSetNewWallpapper = 0;
    public static final int didSetOrRemoveTwoStepPassword = 0;
    public static final int didSetPasscode = 0;
    public static final int didStartedCall = 0;
    public static final int didStartedMultiGiftsSelector = 0;
    public static final int didUpdateConnectionState = 0;
    public static final int didUpdateExtendedMedia = 0;
    public static final int didUpdateGlobalAutoDeleteTimer = 0;
    public static final int didUpdateMessagesViews = 0;
    public static final int didUpdatePollResults = 0;
    public static final int didUpdatePremiumGiftFieldIcon = 0;
    public static final int didUpdatePremiumGiftStickers = 0;
    public static final int didUpdateReactions = 0;
    public static final int didUpdateTonGiftStickers = 0;
    public static final int didVerifyMessagesStickers = 0;
    public static final int emojiKeywordsLoaded = 0;
    public static final int emojiLoaded = 0;
    public static final int emojiPreviewThemesChanged = 0;
    public static final int encryptedChatCreated = 0;
    public static final int encryptedChatUpdated = 0;
    public static final int factCheckLoaded = 0;
    public static final int featuredEmojiDidLoad = 0;
    public static final int featuredStickersDidLoad = 0;
    public static final int feedNeedReload = 0;
    public static final int feedTabVisibleToggled = 0;
    public static final int fileLoadFailed = 0;
    public static final int fileLoadProgressChanged = 0;
    public static final int fileLoaded = 0;
    public static final int fileNewChunkAvailable = 0;
    public static final int filePreparingFailed = 0;
    public static final int filePreparingStarted = 0;
    public static final int fileUploadFailed = 0;
    public static final int fileUploadProgressChanged = 0;
    public static final int fileUploaded = 0;
    public static final int filterSettingsUpdated = 0;
    public static final int folderBecomeEmpty = 0;
    public static final int forceImportContactsStart = 0;
    public static final int giftsToUserSent = 0;
    private static volatile NotificationCenter globalInstance;
    public static final int goingToPreviewTheme = 0;
    public static final int groupCallScreencastStateChanged = 0;
    public static final int groupCallSpeakingUsersUpdated = 0;
    public static final int groupCallTypingsUpdated = 0;
    public static final int groupCallUpdated = 0;
    public static final int groupCallVisibilityChanged = 0;
    public static final int groupPackUpdated = 0;
    public static final int groupRestrictionsUnlockedByBoosts = 0;
    public static final int groupStickersDidLoad = 0;
    public static final int guardBotDecisionResult = 0;
    public static final int hasNewContactsToImport = 0;
    public static final int hashtagSearchUpdated = 0;
    public static final int historyCleared = 0;
    public static final int historyImportProgressChanged = 0;
    public static final int httpFileDidFailedLoad = 0;
    public static final int httpFileDidLoad = 0;
    public static int iconPackUpdated;
    public static final int invalidateMotionBackground = 0;
    public static final int joinedGroup = 0;
    public static final int liveLocationsCacheChanged = 0;
    public static final int liveLocationsChanged = 0;
    public static final int liveStoryMessageUpdate = 0;
    public static final int liveStoryUpdated = 0;
    public static final int loadedAiComposeTones = 0;
    public static final int loadingMessagesFailed = 0;
    public static final int locationPermissionDenied = 0;
    public static final int locationPermissionGranted = 0;
    public static final int mainUserInfoChanged = 0;
    public static final int mediaCountDidLoad = 0;
    public static final int mediaCountsDidLoad = 0;
    public static final int mediaDidLoad = 0;
    public static final int memoryLeakFoundException = 0;
    public static final int messagePlayingDidReset = 0;
    public static final int messagePlayingDidSeek = 0;
    public static final int messagePlayingDidStart = 0;
    public static final int messagePlayingGoingToStop = 0;
    public static final int messagePlayingPlayStateChanged = 0;
    public static final int messagePlayingProgressDidChanged = 0;
    public static final int messagePlayingSpeedChanged = 0;
    public static final int messageReceivedByAck = 0;
    public static final int messageReceivedByServer = 0;
    public static final int messageReceivedByServer2 = 0;
    public static final int messageSendError = 0;
    public static final int messageTranslated = 0;
    public static final int messageTranslating = 0;
    public static final int messagesDeleted = 0;
    public static final int messagesDidLoad = 0;
    public static final int messagesDidLoadWithoutProcess = 0;
    public static final int messagesFeeUpdated = 0;
    public static final int messagesRead = 0;
    public static final int messagesReadContent = 0;
    public static final int messagesReadEncrypted = 0;
    public static final int monoForumMessagesRead = 0;
    public static final int moreMusicDidLoad = 0;
    public static final int musicDidLoad = 0;
    public static final int musicIdsLoaded = 0;
    public static final int musicListLoaded = 0;
    public static final int nearEarEvent = 0;
    public static final int needAddArchivedStickers = 0;
    public static final int needCheckSystemBarColors = 0;
    public static final int needDeleteBusinessLink = 0;
    public static final int needDeleteDialog = 0;
    public static final int needReloadRecentDialogsSearch = 0;
    public static final int needSetDayNightTheme = 0;
    public static final int needShareTheme = 0;
    public static final int needShowAlert = 0;
    public static final int needShowPlayServicesAlert = 0;
    public static final int newDraftReceived = 0;
    public static final int newEmojiSuggestionsAvailable = 0;
    public static final int newLocationAvailable = 0;
    public static final int newSessionReceived = 0;
    public static final int newSuggestionsAvailable = 0;
    public static final int notificationsCountUpdated = 0;
    public static final int notificationsSettingsUpdated = 0;
    public static int nowPlayingUpdated;
    public static final int onActivityResultReceived = 0;
    public static final int onDatabaseMigration = 0;
    public static final int onDatabaseOpened = 0;
    public static final int onDatabaseReset = 0;
    public static final int onDownloadingFilesChanged = 0;
    public static final int onEmojiInteractionsReceived = 0;
    public static final int onReceivedChannelDifference = 0;
    public static final int onRequestPermissionResultReceived = 0;
    public static final int onUpdateLoginToken = 0;
    public static final int onUserRingtonesUpdated = 0;
    public static final int openArticle = 0;
    public static final int openBoostForUsersDialog = 0;
    public static final int openedChatChanged = 0;
    public static final int passcodeDismissed = 0;
    public static final int paymentFinished = 0;
    public static final int peerSettingsDidLoad = 0;
    public static final int permissionsGranted = 0;
    public static int pillStackLayoutChanged;
    public static int pillStackSettingsChanged;
    public static final int pinnedInfoDidLoad = 0;
    public static final int playerDidStartPlaying = 0;
    public static int pluginIsNotResponding;
    public static int pluginMenuItemsUpdated;
    public static int pluginSettingsRegistered;
    public static int pluginSettingsUnregistered;
    public static int pluginsPySdkInfoChanged;
    public static int pluginsUpdated;
    public static final int premiumFloodWaitReceived = 0;
    public static final int premiumPromoUpdated = 0;
    public static final int premiumStatusChangedGlobal = 0;
    public static final int premiumStickersPreviewLoaded = 0;
    public static final int privacyRulesUpdated = 0;
    public static final int profileMusicUpdated = 0;
    public static final int proxyChangedByRotation = 0;
    public static final int proxyCheckDone = 0;
    public static final int proxyPingUpdated = 0;
    public static final int proxySettingsChanged = 0;
    public static final int pushMessagesUpdated = 0;
    public static final int quickRepliesDeleted = 0;
    public static final int quickRepliesUpdated = 0;
    public static final int reactionsDidLoad = 0;
    public static final int recentDocumentsDidLoad = 0;
    public static final int recentEmojiStatusesUpdate = 0;
    public static final int recordPaused = 0;
    public static final int recordProgressChanged = 0;
    public static final int recordResumed = 0;
    public static final int recordStartError = 0;
    public static final int recordStarted = 0;
    public static final int recordStopped = 0;
    public static final int reloadDialogPhotos = 0;
    public static final int reloadGuestBotHints = 0;
    public static final int reloadHints = 0;
    public static final int reloadInlineHints = 0;
    public static final int reloadInterface = 0;
    public static final int reloadWebappsHints = 0;
    public static final int removeAllMessagesFromDialog = 0;
    public static final int replaceMessagesObjects = 0;
    public static final int replyMessagesDidLoad = 0;
    public static final int requestPermissions = 0;
    public static int rolesUpdated;
    public static final int savedMessagesDialogsUpdate = 0;
    public static final int savedMessagesForwarded = 0;
    public static final int savedReactionTagsUpdate = 0;
    public static final int scheduledMessagesUpdated = 0;
    public static final int screenStateChanged = 0;
    public static final int screenshotTook = 0;
    public static final int sendingMessagesChanged = 0;
    public static int servicesUpdated;
    public static final int showBulletin = 0;
    public static final int smsJobStatusUpdate = 0;
    public static final int starBalanceUpdated = 0;
    public static final int starGiftOptionsLoaded = 0;
    public static final int starGiftSoldOut = 0;
    public static final int starGiftsLoaded = 0;
    public static final int starGiveawayOptionsLoaded = 0;
    public static final int starOptionsLoaded = 0;
    public static final int starReactionAnonymousUpdate = 0;
    public static final int starSubscriptionsLoaded = 0;
    public static final int starTransactionsLoaded = 0;
    public static final int starUserGiftCollectionsLoaded = 0;
    public static final int starUserGiftsLoaded = 0;
    public static final int startAllHeavyOperations = 0;
    public static final int startSpoilers = 0;
    public static final int stealthModeChanged = 0;
    public static final int stickersDidLoad = 0;
    public static final int stickersImportComplete = 0;
    public static final int stickersImportProgressChanged = 0;
    public static final int stopAllHeavyOperations = 0;
    public static final int stopSpoilers = 0;
    public static final int storiesBlocklistUpdate = 0;
    public static final int storiesDraftsUpdated = 0;
    public static final int storiesEnabledUpdate = 0;
    public static final int storiesLimitUpdate = 0;
    public static final int storiesListUpdated = 0;
    public static final int storiesReadUpdated = 0;
    public static final int storiesSendAsUpdate = 0;
    public static final int storiesUpdated = 0;
    public static final int storyAlbumsCollectionsUpdate = 0;
    public static final int storyDeleted = 0;
    public static final int storyGroupCallUpdated = 0;
    public static final int storyQualityUpdate = 0;
    public static final int suggestedFiltersLoaded = 0;
    public static final int suggestedLangpack = 0;
    public static final int themeAccentListUpdated = 0;
    public static final int themeListUpdated = 0;
    public static final int themeUploadError = 0;
    public static final int themeUploadedToServer = 0;
    public static final int threadMessagesRead = 0;
    public static final int timezonesUpdated = 0;
    public static final int tlSchemeParseException = 0;
    public static final int topicsDidLoaded = 0;
    private static int totalEvents;
    public static final int translationModelDownloaded = 0;
    public static final int translationModelDownloading = 0;
    public static final int twoStepPasswordChanged = 0;
    public static final int unconfirmedAuthUpdate = 0;
    public static final int updateAllMessages = 0;
    public static final int updateBotMenuButton = 0;
    public static final int updateDefaultSendAsPeer = 0;
    public static final int updateInterfaces = 0;
    public static final int updateMentionsCount = 0;
    public static final int updateMessageMedia = 0;
    public static final int updateSearchSettings = 0;
    public static final int updateStories = 0;
    public static final int updateTranscriptionLock = 0;
    public static final int updatedChatRanks = 0;
    public static final int updatedChatbot = 0;
    public static final int uploadStoryEnd = 0;
    public static final int uploadStoryProgress = 0;
    public static final int userEmojiStatusUpdated = 0;
    public static final int userInfoDidLoad = 0;
    public static final int userIsPremiumBlockedUpadted = 0;
    public static final int videoLoadingStateChanged = 0;
    public static final int voiceTranscriptionUpdate = 0;
    public static final int voipServiceCreated = 0;
    public static final int walletPendingTransactionsChanged = 0;
    public static final int walletSyncProgressChanged = 0;
    public static final int wallpaperSettedToUser = 0;
    public static final int wallpapersDidLoad = 0;
    public static final int wallpapersNeedReload = 0;
    public static final int wasUnableToFindCurrentLocation = 0;
    public static final int webBrowserSettingsUpdate = 0;
    public static final int webRtcMicAmplitudeEvent = 0;
    public static final int webRtcSpeakerAmplitudeEvent = 0;
    public static final int webViewResultSent = 0;
    private final SparseArray<ArrayList<NotificationCenterDelegate>> addAfterBroadcast;
    private final SparseArray<AllowedNotifications> allowedNotifications;
    SparseArray<Runnable> alreadyPostedRunnubles;
    private int animationInProgressCount;
    private int animationInProgressPointer;
    private int broadcasting;
    private Runnable checkForExpiredNotifications;
    private final int currentAccount;
    private int currentHeavyOperationFlags;
    private final ArrayList<DelayedPost> delayedPosts;
    private final ArrayList<DelayedPost> delayedPostsTmp;
    private final ArrayList<Runnable> delayedRunnables;
    private final ArrayList<Runnable> delayedRunnablesTmp;
    HashSet<Integer> heavyOperationsCounter;
    private final SparseArray<ArrayList<NotificationCenterDelegate>> observers;
    private final ArrayList<PostponeNotificationCallback> postponeCallbackList;
    private final SparseArray<ArrayList<NotificationCenterDelegate>> removeAfterBroadcast;

    /* renamed from: org.telegram.messenger.NotificationCenter$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ NotificationCenter this$0;
        final /* synthetic */ NotificationCenterDelegate val$delegate;
        final /* synthetic */ int val$id;

        public AnonymousClass1(NotificationCenter r1, NotificationCenterDelegate r2, int r3) {
            this.this$0 = r1;
            this.val$delegate = r2;
            this.val$id = r3;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r2) {
            NotificationCenter.m4786$$Nest$maddObserverInternal(this.this$0, this.val$delegate, this.val$id);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            this.this$0.removeObserver(this.val$delegate, this.val$id);
        }
    }

    public static class AllowedNotifications {
        int[] allowedIds;
        final long time;

        private AllowedNotifications() {
            this.time = SystemClock.elapsedRealtime();
        }

        public /* synthetic */ AllowedNotifications(NotificationCenterIA r1) {
            this();
        }
    }

    public static class DelayedPost {
        private final Object[] args;
        private final int id;

        /* renamed from: -$$Nest$fgetargs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Object[] m4787$$Nest$fgetargs(DelayedPost r0) {
            return r0.args;
        }

        /* renamed from: -$$Nest$fgetid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4788$$Nest$fgetid(DelayedPost r0) {
            return r0.id;
        }

        private DelayedPost(int r1, Object[] r2) {
            this.id = r1;
            this.args = r2;
        }

        public /* synthetic */ DelayedPost(int r1, Object[] r2, NotificationCenterIA r3) {
            this(r1, r2);
        }
    }

    public interface NotificationCenterDelegate {
        void didReceivedNotification(int r1, int r2, Object... r3);
    }

    public interface ObserversGroup {
        ObserversGroup add(int r1);

        ObserversGroup addGlobal(int r1);

        void removeAllObservers();
    }

    public static final class ObserversGroupImpl implements ObserversGroup {
        private NotificationCenterDelegate delegate;
        private ObserversGroupImpl globalGroup;
        private final MutableIntList ids;
        private NotificationCenter notificationCenter;

        private ObserversGroupImpl(NotificationCenter r2, NotificationCenterDelegate r3) {
            this.ids = new MutableIntList();
            this.notificationCenter = r2;
            this.delegate = r3;
        }

        public /* synthetic */ ObserversGroupImpl(NotificationCenter r1, NotificationCenterDelegate r2, NotificationCenterIA r3) {
            this(r1, r2);
        }

        @Override // org.telegram.messenger.NotificationCenter.ObserversGroup
        public ObserversGroup add(int r3) {
            if (this.delegate != null) goto L5;
            return this;
        L5:
            this.ids.add(r3);
            NotificationCenter.m4786$$Nest$maddObserverInternal(this.notificationCenter, this.delegate, r3);
            return this;
        }

        @Override // org.telegram.messenger.NotificationCenter.ObserversGroup
        public ObserversGroup addGlobal(int r4) {
            if (this.delegate != null) goto L6;
            return this;
        L6:
            if (this.globalGroup != null) goto L8;
            this.globalGroup = new ObserversGroupImpl(NotificationCenter.getGlobalInstance(), this.delegate);
        L8:
            this.globalGroup.add(r4);
            return this;
        }

        @Override // org.telegram.messenger.NotificationCenter.ObserversGroup
        public void removeAllObservers() {
            if (this.delegate != null) goto L5;
            return;
        L5:
            ObserversGroupImpl r0 = this.globalGroup;
            if (r0 == null) goto L8;
            r0.removeAllObservers();
            this.globalGroup = null;
        L8:
            int r02 = this.ids.getSize();
            int r2 = 0;
        L9:
            if (r2 >= r02) goto L11;
            this.notificationCenter.removeObserver(this.delegate, this.ids.get(r2));
            r2 = r2 + 1;
            goto L9
        L11:
            this.ids.clear();
            this.notificationCenter = null;
            this.delegate = null;
        }
    }

    public interface PostponeNotificationCallback {
        boolean needPostpone(int r1, int r2, Object[] r3);
    }

    public class UniqArrayList<T> extends ArrayList<T> {
        HashSet<T> set;
        final /* synthetic */ NotificationCenter this$0;

        private UniqArrayList(NotificationCenter r1) {
            this.this$0 = r1;
            this.set = new HashSet();
        }

        public /* synthetic */ UniqArrayList(NotificationCenter r1, NotificationCenterIA r2) {
            this(r1);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public void add(int r2, T r3) {
            if (this.set.add(r3) == false) goto L6;
            super.add(r2, r3);
            return;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(T r2) {
            if (this.set.add(r2) == true) goto L5;
            return false;
        L5:
            return super.add(r2);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public boolean addAll(int r1, Collection<? extends T> r2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends T> r3) {
            Iterator<? extends T> r32 = r3.iterator();
            boolean r0 = false;
        L4:
            if (r32.hasNext() == false) goto L8;
            if (add(r32.next()) == false) goto L4;
            r0 = true;
            goto L4
        L8:
            return r0;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.set.clear();
            super.clear();
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object r1) {
            return this.set.contains(r1);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public T remove(int r1) {
            T r12 = (T) super.remove(r1);
            if (r12 == null) goto L5;
            this.set.remove(r12);
        L5:
            return r12;
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean remove(Object r2) {
            if (this.set.remove(r2) == true) goto L5;
            return false;
        L5:
            return super.remove(r2);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean removeAll(Collection<?> r1) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes5.dex */
    public static final class WeakObserversGroupImpl implements ObserversGroup, NotificationCenterDelegate {
        private final ObserversGroupImpl observersGroup;
        private final WeakReference<NotificationCenterDelegate> reference;

        private WeakObserversGroupImpl(NotificationCenter r3, NotificationCenterDelegate r4) {
            this.observersGroup = new ObserversGroupImpl(r3, this, null);
            this.reference = new WeakReference(r4);
        }

        public /* synthetic */ WeakObserversGroupImpl(NotificationCenter r1, NotificationCenterDelegate r2, NotificationCenterIA r3) {
            this(r1, r2);
        }

        @Override // org.telegram.messenger.NotificationCenter.ObserversGroup
        public ObserversGroup add(int r1) {
            return this.observersGroup.add(r1);
        }

        @Override // org.telegram.messenger.NotificationCenter.ObserversGroup
        public ObserversGroup addGlobal(int r1) {
            return this.observersGroup.addGlobal(r1);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r2, int r3, Object... r4) {
            NotificationCenterDelegate r0 = this.reference.get();
            if (r0 == null) goto L6;
            r0.didReceivedNotification(r2, r3, r4);
            return;
        L6:
            FileLog.e("MEMORY_LEAK observer " + r2 + " with destroyed WeakReference");
            removeAllObservers();
        }

        @Override // org.telegram.messenger.NotificationCenter.ObserversGroup
        public void removeAllObservers() {
            this.observersGroup.removeAllObservers();
        }
    }

    /* renamed from: $r8$lambda$0kUClKQNr-pk_aXMibpcEvSXelk, reason: not valid java name */
    public static /* synthetic */ void m4785$r8$lambda$0kUClKQNrpk_aXMibpcEvSXelk(NotificationCenter r0, View r1, View.OnAttachStateChangeListener r2, NotificationCenterDelegate r3, int r4) {
        r0.lambda$listen$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ABjPTDLHveMkSss4hjfoi4fGbXg(NotificationCenter r0) {
        r0.lambda$checkForExpiredNotifications$0();
    }

    public static /* synthetic */ void $r8$lambda$F5nKyKOGs2lrY4SWsFyNnZil9e8(NotificationCenter r0, int r1, Object[] r2) {
        r0.lambda$postNotificationNameOnUIThread$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VGHAYRtonj4FbETBz8XLZzMdH_8(int r0, Utilities.Callback r1, int r2, int r3, Object[] r4) {
        if (r2 != r0) goto L5;
        r1.run(r4);
        return;
    }

    public static /* synthetic */ void $r8$lambda$VudP1pisRhGN0jwJx9KkPPITT3c(NotificationCenter r0, int r1, Object[] r2, int r3) {
        r0.lambda$postNotificationDebounced$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$a6bxL9Cq6SeZvyKqsik1q_Kf9TU(NotificationCenter r0) {
        r0.checkForExpiredNotifications();
    }

    public static /* synthetic */ void $r8$lambda$mbyb400lxNv6eoEsmM15b_0BZKI(View r0, Object[] r1) {
        r0.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$x0wyvvU9O011L3KAnB6Ogh8o99k() {
    }

    /* renamed from: -$$Nest$maddObserverInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4786$$Nest$maddObserverInternal(NotificationCenter r0, NotificationCenterDelegate r1, int r2) {
        r0.addObserverInternal(r1, r2);
    }

    static {
        int r0 = 1 + 1;
        onUpdateLoginToken = r0;
        updateInterfaces = r0 + 1;
        dialogsNeedReload = r0 + 2;
        closeChats = r0 + 3;
        closeChatActivity = r0 + 4;
        closeProfileActivity = r0 + 5;
        messagesDeleted = r0 + 6;
        historyCleared = r0 + 7;
        messagesRead = r0 + 8;
        threadMessagesRead = r0 + 9;
        monoForumMessagesRead = r0 + 10;
        commentsRead = r0 + 11;
        changeRepliesCounter = r0 + 12;
        messagesDidLoad = r0 + 13;
        didLoadSponsoredMessages = r0 + 14;
        didLoadSendAsPeers = r0 + 15;
        updateDefaultSendAsPeer = r0 + 16;
        messagesDidLoadWithoutProcess = r0 + 17;
        loadingMessagesFailed = r0 + 18;
        messageReceivedByAck = r0 + 19;
        messageReceivedByServer = r0 + 20;
        messageReceivedByServer2 = r0 + 21;
        messageSendError = r0 + 22;
        forceImportContactsStart = r0 + 23;
        contactsDidLoad = r0 + 24;
        contactsImported = r0 + 25;
        hasNewContactsToImport = r0 + 26;
        chatDidCreated = r0 + 27;
        chatDidFailCreate = r0 + 28;
        chatInfoDidLoad = r0 + 29;
        chatInfoCantLoad = r0 + 30;
        mediaDidLoad = r0 + 31;
        mediaCountDidLoad = r0 + 32;
        mediaCountsDidLoad = r0 + 33;
        encryptedChatUpdated = r0 + 34;
        messagesReadEncrypted = r0 + 35;
        encryptedChatCreated = r0 + 36;
        dialogPhotosLoaded = r0 + 37;
        reloadDialogPhotos = r0 + 38;
        folderBecomeEmpty = r0 + 39;
        removeAllMessagesFromDialog = r0 + 40;
        notificationsSettingsUpdated = r0 + 41;
        blockedUsersDidLoad = r0 + 42;
        openedChatChanged = r0 + 43;
        didCreatedNewDeleteTask = r0 + 44;
        mainUserInfoChanged = r0 + 45;
        privacyRulesUpdated = r0 + 46;
        updateMessageMedia = r0 + 47;
        replaceMessagesObjects = r0 + 48;
        didSetPasscode = r0 + 49;
        passcodeDismissed = r0 + 50;
        twoStepPasswordChanged = r0 + 51;
        didSetOrRemoveTwoStepPassword = r0 + 52;
        didRemoveTwoStepPassword = r0 + 53;
        replyMessagesDidLoad = r0 + 54;
        didLoadPinnedMessages = r0 + 55;
        newSessionReceived = r0 + 56;
        didReceivedWebpages = r0 + 57;
        didReceivedWebpagesInUpdates = r0 + 58;
        stickersDidLoad = r0 + 59;
        diceStickersDidLoad = r0 + 60;
        featuredStickersDidLoad = r0 + 61;
        featuredEmojiDidLoad = r0 + 62;
        groupStickersDidLoad = r0 + 63;
        messagesReadContent = r0 + 64;
        botInfoDidLoad = r0 + 65;
        userInfoDidLoad = r0 + 66;
        pinnedInfoDidLoad = r0 + 67;
        botKeyboardDidLoad = r0 + 68;
        chatSearchResultsAvailable = r0 + 69;
        hashtagSearchUpdated = r0 + 70;
        chatSearchResultsLoading = r0 + 71;
        musicDidLoad = r0 + 72;
        moreMusicDidLoad = r0 + 73;
        needShowAlert = r0 + 74;
        needShowPlayServicesAlert = r0 + 75;
        didUpdateMessagesViews = r0 + 76;
        needReloadRecentDialogsSearch = r0 + 77;
        peerSettingsDidLoad = r0 + 78;
        wasUnableToFindCurrentLocation = r0 + 79;
        reloadHints = r0 + 80;
        reloadInlineHints = r0 + 81;
        reloadGuestBotHints = r0 + 82;
        reloadWebappsHints = r0 + 83;
        newDraftReceived = r0 + 84;
        recentDocumentsDidLoad = r0 + 85;
        needAddArchivedStickers = r0 + 86;
        archivedStickersCountDidLoad = r0 + 87;
        paymentFinished = r0 + 88;
        channelRightsUpdated = r0 + 89;
        openArticle = r0 + 90;
        articleClosed = r0 + 91;
        updateMentionsCount = r0 + 92;
        didUpdatePollResults = r0 + 93;
        chatOnlineCountDidLoad = r0 + 94;
        videoLoadingStateChanged = r0 + 95;
        stopAllHeavyOperations = r0 + 96;
        startAllHeavyOperations = r0 + 97;
        stopSpoilers = r0 + 98;
        startSpoilers = r0 + 99;
        sendingMessagesChanged = r0 + 100;
        didUpdateReactions = r0 + 101;
        didUpdateExtendedMedia = r0 + 102;
        didVerifyMessagesStickers = r0 + 103;
        scheduledMessagesUpdated = r0 + 104;
        newSuggestionsAvailable = r0 + 105;
        didLoadChatInviter = r0 + 106;
        didLoadChatAdmins = r0 + 107;
        historyImportProgressChanged = r0 + 108;
        stickersImportProgressChanged = r0 + 109;
        stickersImportComplete = r0 + 110;
        dialogDeleted = r0 + 111;
        webViewResultSent = r0 + 112;
        voiceTranscriptionUpdate = r0 + 113;
        animatedEmojiDocumentLoaded = r0 + 114;
        recentEmojiStatusesUpdate = r0 + 115;
        updateSearchSettings = r0 + 116;
        updateTranscriptionLock = r0 + 117;
        businessMessagesUpdated = r0 + 118;
        quickRepliesUpdated = r0 + 119;
        quickRepliesDeleted = r0 + 120;
        bookmarkAdded = r0 + 121;
        starReactionAnonymousUpdate = r0 + 122;
        businessLinksUpdated = r0 + 123;
        businessLinkCreated = r0 + 124;
        needDeleteBusinessLink = r0 + 125;
        messageTranslated = r0 + 126;
        messageTranslating = r0 + 127;
        dialogIsTranslatable = r0 + 128;
        dialogTranslate = r0 + 129;
        didGenerateFingerprintKeyPair = r0 + 130;
        walletPendingTransactionsChanged = r0 + 131;
        walletSyncProgressChanged = r0 + 132;
        httpFileDidLoad = r0 + 133;
        httpFileDidFailedLoad = r0 + 134;
        didUpdateConnectionState = r0 + 135;
        fileUploaded = r0 + 136;
        fileUploadFailed = r0 + 137;
        fileUploadProgressChanged = r0 + 138;
        fileLoadProgressChanged = r0 + 139;
        fileLoaded = r0 + 140;
        fileLoadFailed = r0 + 141;
        filePreparingStarted = r0 + 142;
        fileNewChunkAvailable = r0 + 143;
        filePreparingFailed = r0 + 144;
        dialogsUnreadCounterChanged = r0 + 145;
        messagePlayingProgressDidChanged = r0 + 146;
        messagePlayingDidReset = r0 + 147;
        messagePlayingPlayStateChanged = r0 + 148;
        messagePlayingDidStart = r0 + 149;
        messagePlayingDidSeek = r0 + 150;
        messagePlayingGoingToStop = r0 + 151;
        recordProgressChanged = r0 + 152;
        recordStarted = r0 + 153;
        recordStartError = r0 + 154;
        recordStopped = r0 + 155;
        recordPaused = r0 + 156;
        recordResumed = r0 + 157;
        screenshotTook = r0 + 158;
        albumsDidLoad = r0 + 159;
        audioDidSent = r0 + 160;
        audioRecordTooShort = r0 + 161;
        audioRouteChanged = r0 + 162;
        didStartedCall = r0 + 163;
        groupCallUpdated = r0 + 164;
        storyGroupCallUpdated = r0 + 165;
        groupCallSpeakingUsersUpdated = r0 + 166;
        groupCallScreencastStateChanged = r0 + 167;
        activeGroupCallsUpdated = r0 + 168;
        applyGroupCallVisibleParticipants = r0 + 169;
        groupCallTypingsUpdated = r0 + 170;
        didEndCall = r0 + 171;
        closeInCallActivity = r0 + 172;
        groupCallVisibilityChanged = r0 + 173;
        liveStoryUpdated = r0 + 174;
        liveStoryMessageUpdate = r0 + 175;
        appDidLogout = r0 + 176;
        configLoaded = r0 + 177;
        needDeleteDialog = r0 + 178;
        newEmojiSuggestionsAvailable = r0 + 179;
        themeUploadedToServer = r0 + 180;
        themeUploadError = r0 + 181;
        dialogFiltersUpdated = r0 + 182;
        filterSettingsUpdated = r0 + 183;
        suggestedFiltersLoaded = r0 + 184;
        updateBotMenuButton = r0 + 185;
        giftsToUserSent = r0 + 186;
        didStartedMultiGiftsSelector = r0 + 187;
        boostedChannelByUser = r0 + 188;
        boostByChannelCreated = r0 + 189;
        didUpdatePremiumGiftStickers = r0 + 190;
        didUpdateTonGiftStickers = r0 + 191;
        didUpdatePremiumGiftFieldIcon = r0 + 192;
        storiesEnabledUpdate = r0 + 193;
        storiesBlocklistUpdate = r0 + 194;
        storiesLimitUpdate = r0 + 195;
        storiesSendAsUpdate = r0 + 196;
        unconfirmedAuthUpdate = r0 + 197;
        dialogPhotosUpdate = r0 + 198;
        channelRecommendationsLoaded = r0 + 199;
        savedMessagesDialogsUpdate = r0 + 200;
        savedReactionTagsUpdate = r0 + 201;
        userIsPremiumBlockedUpadted = r0 + 202;
        int r2 = r0 + Opcodes.SUB_DOUBLE_2ADDR;
        storyAlbumsCollectionsUpdate = r0 + 203;
        int r1 = r0 + Opcodes.MUL_DOUBLE_2ADDR;
        savedMessagesForwarded = r2;
        int r22 = r0 + Opcodes.DIV_DOUBLE_2ADDR;
        emojiKeywordsLoaded = r1;
        int r12 = r0 + Opcodes.REM_DOUBLE_2ADDR;
        smsJobStatusUpdate = r22;
        int r23 = r0 + Opcodes.ADD_INT_LIT16;
        storyQualityUpdate = r12;
        int r13 = r0 + Opcodes.RSUB_INT;
        openBoostForUsersDialog = r23;
        int r24 = r0 + Opcodes.MUL_INT_LIT16;
        groupRestrictionsUnlockedByBoosts = r13;
        int r14 = r0 + Opcodes.DIV_INT_LIT16;
        chatWasBoostedByUser = r24;
        int r25 = r0 + Opcodes.REM_INT_LIT16;
        groupPackUpdated = r14;
        int r15 = r0 + Opcodes.AND_INT_LIT16;
        timezonesUpdated = r25;
        int r26 = r0 + Opcodes.OR_INT_LIT16;
        customStickerCreated = r15;
        int r16 = r0 + Opcodes.XOR_INT_LIT16;
        premiumFloodWaitReceived = r26;
        int r27 = r0 + Opcodes.ADD_INT_LIT8;
        availableEffectsUpdate = r16;
        int r17 = r0 + Opcodes.RSUB_INT_LIT8;
        starOptionsLoaded = r27;
        int r28 = r0 + Opcodes.MUL_INT_LIT8;
        starGiftOptionsLoaded = r17;
        int r18 = r0 + Opcodes.DIV_INT_LIT8;
        starGiveawayOptionsLoaded = r28;
        int r29 = r0 + Opcodes.REM_INT_LIT8;
        starBalanceUpdated = r18;
        int r19 = r0 + Opcodes.AND_INT_LIT8;
        starTransactionsLoaded = r29;
        int r210 = r0 + Opcodes.OR_INT_LIT8;
        starSubscriptionsLoaded = r19;
        int r110 = r0 + Opcodes.XOR_INT_LIT8;
        factCheckLoaded = r210;
        int r211 = r0 + Opcodes.SHL_INT_LIT8;
        botStarsUpdated = r110;
        int r111 = r0 + Opcodes.SHR_INT_LIT8;
        botStarsTransactionsLoaded = r211;
        int r212 = r0 + Opcodes.USHR_INT_LIT8;
        channelStarsUpdated = r111;
        updateAllMessages = r212;
        starGiftsLoaded = r0 + 227;
        int r112 = r0 + TLRPC.LAYER;
        starUserGiftsLoaded = r0 + 228;
        starUserGiftCollectionsLoaded = r112;
        starGiftSoldOut = r0 + 230;
        updateStories = r0 + 231;
        botDownloadsUpdate = r0 + 232;
        channelSuggestedBotsUpdate = r0 + 233;
        channelConnectedBotsUpdate = r0 + 234;
        adminedChannelsLoaded = r0 + 235;
        messagesFeeUpdated = r0 + 236;
        commonChatsLoaded = r0 + 237;
        appConfigUpdated = r0 + 238;
        activeAuctionsUpdated = r0 + 239;
        conferenceEmojiUpdated = r0 + 240;
        contentSettingsLoaded = r0 + 241;
        musicListLoaded = r0 + 242;
        musicIdsLoaded = r0 + 243;
        profileMusicUpdated = r0 + 244;
        updatedChatRanks = r0 + 245;
        joinedGroup = r0 + 246;
        loadedAiComposeTones = r0 + 247;
        updatedChatbot = r0 + 248;
        activeAccountChanged = r0 + 249;
        int r113 = r0 + Opcodes.INVOKE_POLYMORPHIC_RANGE;
        pushMessagesUpdated = r0 + 250;
        int r213 = r0 + Opcodes.INVOKE_CUSTOM;
        wallpapersDidLoad = r113;
        int r114 = r0 + Opcodes.INVOKE_CUSTOM_RANGE;
        wallpapersNeedReload = r213;
        int r214 = r0 + Opcodes.CONST_METHOD_HANDLE;
        didReceiveSmsCode = r114;
        didReceiveCall = r214;
        emojiLoaded = r0 + 255;
        int r115 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
        invalidateMotionBackground = r0 + 256;
        int r215 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED;
        closeOtherAppActivities = r115;
        int r116 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
        cameraInitied = r215;
        int r216 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED;
        didReplacedPhotoInMemCache = r116;
        int r117 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED;
        didSetNewTheme = r216;
        int r217 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED;
        themeListUpdated = r117;
        int r118 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED;
        didApplyNewTheme = r217;
        int r218 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED;
        themeAccentListUpdated = r118;
        int r119 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED;
        needCheckSystemBarColors = r218;
        int r219 = r0 + GlobalMediaRouter.CallbackHandler.MSG_ROUTE_DISCONNECTED;
        needShareTheme = r119;
        needSetDayNightTheme = r219;
        goingToPreviewTheme = r0 + 267;
        locationPermissionGranted = r0 + 268;
        locationPermissionDenied = r0 + 269;
        reloadInterface = r0 + 270;
        suggestedLangpack = r0 + 271;
        didSetNewWallpapper = r0 + 272;
        proxySettingsChanged = r0 + 273;
        proxyCheckDone = r0 + 274;
        proxyChangedByRotation = r0 + 275;
        proxyPingUpdated = r0 + 276;
        liveLocationsChanged = r0 + 277;
        newLocationAvailable = r0 + 278;
        liveLocationsCacheChanged = r0 + 279;
        notificationsCountUpdated = r0 + 280;
        playerDidStartPlaying = r0 + 281;
        closeSearchByActiveAction = r0 + 282;
        messagePlayingSpeedChanged = r0 + 283;
        screenStateChanged = r0 + 284;
        didClearDatabase = r0 + 285;
        voipServiceCreated = r0 + 286;
        webRtcMicAmplitudeEvent = r0 + 287;
        webRtcSpeakerAmplitudeEvent = r0 + 288;
        showBulletin = r0 + 289;
        appUpdateAvailable = r0 + 290;
        appUpdateLoading = r0 + 291;
        onDatabaseMigration = r0 + 292;
        onEmojiInteractionsReceived = r0 + 293;
        emojiPreviewThemesChanged = r0 + 294;
        reactionsDidLoad = r0 + 295;
        attachMenuBotsDidLoad = r0 + 296;
        chatAvailableReactionsUpdated = r0 + 297;
        dialogsUnreadReactionsCounterChanged = r0 + 298;
        dialogsUnreadPollVotesCounterChanged = r0 + 299;
        int r120 = r0 + MediaError.DetailedErrorCode.SEGMENT_NETWORK;
        onDatabaseOpened = r0 + 300;
        onDownloadingFilesChanged = r120;
        onActivityResultReceived = r0 + 302;
        onRequestPermissionResultReceived = r0 + 303;
        onUserRingtonesUpdated = r0 + 304;
        currentUserPremiumStatusChanged = r0 + 305;
        int r121 = r0 + HttpStatusCodesKt.HTTP_TEMP_REDIRECT;
        premiumPromoUpdated = r0 + 306;
        int r220 = r0 + HttpStatusCodesKt.HTTP_PERM_REDIRECT;
        premiumStatusChangedGlobal = r121;
        currentUserShowLimitReachedDialog = r220;
        billingProductDetailsUpdated = r0 + 309;
        int r122 = r0 + MediaError.DetailedErrorCode.HLS_NETWORK_MASTER_PLAYLIST;
        billingConfirmPurchaseError = r0 + 310;
        int r221 = r0 + MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST;
        premiumStickersPreviewLoaded = r122;
        int r123 = r0 + MediaError.DetailedErrorCode.HLS_NETWORK_NO_KEY_RESPONSE;
        userEmojiStatusUpdated = r221;
        int r222 = r0 + MediaError.DetailedErrorCode.HLS_NETWORK_KEY_LOAD;
        requestPermissions = r123;
        int r124 = r0 + MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT;
        permissionsGranted = r222;
        int r223 = r0 + MediaError.DetailedErrorCode.HLS_SEGMENT_PARSING;
        activityPermissionsGranted = r124;
        topicsDidLoaded = r223;
        chatSwitchedForum = r0 + 317;
        didUpdateGlobalAutoDeleteTimer = r0 + 318;
        onDatabaseReset = r0 + 319;
        int r125 = r0 + MediaError.DetailedErrorCode.DASH_NETWORK;
        wallpaperSettedToUser = r0 + 320;
        int r224 = r0 + MediaError.DetailedErrorCode.DASH_NO_INIT;
        storiesUpdated = r125;
        storyDeleted = r224;
        storiesListUpdated = r0 + 323;
        storiesDraftsUpdated = r0 + 324;
        chatlistFolderUpdate = r0 + 325;
        uploadStoryProgress = r0 + 326;
        uploadStoryEnd = r0 + 327;
        customTypefacesLoaded = r0 + 328;
        stealthModeChanged = r0 + 329;
        int r126 = r0 + MediaError.DetailedErrorCode.SMOOTH_NETWORK;
        onReceivedChannelDifference = r0 + 330;
        int r225 = r0 + MediaError.DetailedErrorCode.SMOOTH_NO_MEDIA_DATA;
        storiesReadUpdated = r126;
        nearEarEvent = r225;
        translationModelDownloading = r0 + 333;
        translationModelDownloaded = r0 + 334;
        botForumTopicDidCreate = r0 + 335;
        botForumDraftUpdate = r0 + 336;
        botForumDraftDelete = r0 + 337;
        tlSchemeParseException = r0 + 338;
        memoryLeakFoundException = r0 + 339;
        callTabsVisibleToggled = r0 + 340;
        contactsTabVisibleToggled = r0 + 341;
        feedTabVisibleToggled = r0 + 342;
        feedNeedReload = r0 + 343;
        contactsPermissionBadgeCheck = r0 + 344;
        guardBotDecisionResult = r0 + 345;
        webBrowserSettingsUpdate = r0 + 346;
        communityPendingRequestsUpdate = r0 + 347;
        communitySwitchedCollapsed = r0 + 348;
        nowPlayingUpdated = r0 + 349;
        rolesUpdated = r0 + 350;
        servicesUpdated = r0 + 351;
        pluginsUpdated = r0 + 352;
        pluginIsNotResponding = r0 + 353;
        pluginSettingsRegistered = r0 + 354;
        pluginSettingsUnregistered = r0 + 355;
        pluginMenuItemsUpdated = r0 + 356;
        iconPackUpdated = r0 + 357;
        pillStackSettingsChanged = r0 + 358;
        int r226 = r0 + 360;
        pillStackLayoutChanged = r0 + 359;
        totalEvents = r0 + 361;
        pluginsPySdkInfoChanged = r226;
        Instance = new NotificationCenter[16];
    }

    public NotificationCenter(int r3) {
        this.observers = new SparseArray();
        this.removeAfterBroadcast = new SparseArray();
        this.addAfterBroadcast = new SparseArray();
        this.delayedPosts = new ArrayList(10);
        this.delayedRunnables = new ArrayList(10);
        this.delayedRunnablesTmp = new ArrayList(10);
        this.delayedPostsTmp = new ArrayList(10);
        this.postponeCallbackList = new ArrayList(10);
        this.broadcasting = 0;
        this.animationInProgressPointer = 1;
        this.heavyOperationsCounter = new HashSet();
        this.allowedNotifications = new SparseArray();
        this.alreadyPostedRunnubles = new SparseArray();
        this.currentAccount = r3;
    }

    private void addObserverInternal(NotificationCenterDelegate r3, int r4) {
        if (BuildVars.DEBUG_VERSION == false) goto L10;
        if (Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("addObserver allowed only from MAIN thread");
        return;
    L10:
        if (this.broadcasting == 0) goto L16;
        ArrayList<NotificationCenterDelegate> r0 = this.addAfterBroadcast.get(r4);
        if (r0 != null) goto L14;
        r0 = new ArrayList();
        this.addAfterBroadcast.put(r4, r0);
    L14:
        r0.add(r3);
        return;
    L16:
        ArrayList<NotificationCenterDelegate> r02 = this.observers.get(r4);
        if (r02 != null) goto L20;
        SparseArray<ArrayList<NotificationCenterDelegate>> r03 = this.observers;
        ArrayList<NotificationCenterDelegate> r2 = createArrayForId(r4);
        r03.put(r4, r2);
        r02 = r2;
    L20:
        if (r02.contains(r3) == true) goto L33;
        r02.add(r3);
        if (BuildVars.DEBUG_VERSION == true) goto L25;
        return;
    L25:
        if (alreadyLogged == false) goto L27;
        return;
    L27:
        if (r02.size() <= 1000) goto L32;
        alreadyLogged = true;
        FileLog.e(new RuntimeException("Total observers more than 1000, need check for memory leak. " + r4));
        return;
    L32:
        return;
    }

    private void checkForExpiredNotifications() {
        ArrayList r0 = null;
        this.checkForExpiredNotifications = null;
        if (this.allowedNotifications.size() == 0) goto L28;
        long r1 = SystemClock.elapsedRealtime();
        int r3 = 0;
        int r6 = 0;
        long r7 = Long.MAX_VALUE;
    L7:
        if (r6 >= this.allowedNotifications.size()) goto L15;
        long r9 = this.allowedNotifications.valueAt(r6).time;
        if ((r1 - r9) <= 1000) goto L13;
        if (r0 != null) goto L12;
        r0 = new ArrayList();
    L12:
        r0.add(Integer.valueOf(this.allowedNotifications.keyAt(r6)));
    L14:
        r6 = r6 + 1;
        goto L7
    L13:
        r7 = Math.min(r9, r7);
        goto L14
    L15:
        if (r0 == null) goto L20;
    L17:
        if (r3 >= r0.size()) goto L20;
        onAnimationFinish(((Integer) r0.get(r3)).intValue());
        r3 = r3 + 1;
    L20:
        if (r7 == LongCompanionObject.MAX_VALUE) goto L27;
        AndroidUtilities.runOnUIThread(new NotificationCenter$$ExternalSyntheticLambda1(this), Math.max(17, EXPIRE_NOTIFICATIONS_TIME - (r1 - r7)));
        return;
    L27:
        return;
    }

    private ArrayList<NotificationCenterDelegate> createArrayForId(int r2) {
        if (r2 == didReplacedPhotoInMemCache) goto L12;
        if (r2 == stopAllHeavyOperations) goto L12;
        if (r2 == startAllHeavyOperations) goto L12;
        return new ArrayList();
    L12:
        return new UniqArrayList(this, null);
    }

    public static void diffObserverDumps(SparseArray<Integer> r10, SparseArray<Integer> r11) {
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r10.size()) goto L12;
        int r4 = r10.keyAt(r3);
        int r8 = r10.valueAt(r3).intValue();
        int r9 = r11.get(r4, -1).intValue();
        if (r9 != (-1)) goto L8;
        Log.i("ObserverDiff", "key=" + r4 + " REMOVED (was " + r8 + ")");
    L10:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r8 == r9) goto L10;
        Log.i("ObserverDiff", "key=" + r4 + " CHANGED: " + r8 + " -> " + r9);
    L12:
        if (r2 >= r11.size()) goto L17;
        int r32 = r11.keyAt(r2);
        if (r10.get(r32, -1).intValue() != (-1)) goto L16;
        Log.i("ObserverDiff", "key=" + r32 + " ADDED (size=" + r11.valueAt(r2) + ")");
    L16:
        r2 = r2 + 1;
        goto L12
    }

    public static NotificationCenter getGlobalInstance() {
        NotificationCenter r0 = globalInstance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(NotificationCenter.class);
        NotificationCenter r02 = globalInstance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new NotificationCenter(-1);     // Catch: Throwable -> L9
        globalInstance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(NotificationCenter.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static NotificationCenter getInstance(int r3) {
        NotificationCenter[] r0 = Instance;
        NotificationCenter r1 = r0[r3];
        if (r1 == null) goto L5;
        return r1;
    L5:
        monitor-enter(NotificationCenter.class);
        NotificationCenter r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new NotificationCenter(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(NotificationCenter.class);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    private static boolean isContextDestroyed(Context r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if ((r2 instanceof Activity) == false) goto L10;
        return ((Activity) r2).isDestroyed();
    L10:
        if ((r2 instanceof ContextWrapper) == true) goto L12;
        return false;
    L12:
        return isContextDestroyed(((ContextWrapper) r2).getBaseContext());
    }

    private /* synthetic */ void lambda$checkForExpiredNotifications$0() {
        this.checkForExpiredNotifications = new NotificationCenter$$ExternalSyntheticLambda5(this);
    }

    private /* synthetic */ void lambda$listen$5(View r1, View.OnAttachStateChangeListener r2, NotificationCenterDelegate r3, int r4) {
        r1.removeOnAttachStateChangeListener(r2);
        removeObserver(r3, r4);
    }

    private /* synthetic */ void lambda$postNotificationDebounced$2(int r2, Object[] r3, int r4) {
        postNotificationNameInternal(r2, false, r3);
        this.alreadyPostedRunnubles.remove(r4);
    }

    private /* synthetic */ void lambda$postNotificationNameOnUIThread$1(int r1, Object[] r2) {
        postNotificationName(r1, r2);
    }

    public static void listenEmojiLoading(View r3) {
        getGlobalInstance().listen(r3, emojiLoaded, new NotificationCenter$$ExternalSyntheticLambda6(r3));
    }

    private void postNotificationDebounced(int r3, Object[] r4) {
        int r0 = (Arrays.hashCode(r4) << 16) + r3;
        if (this.alreadyPostedRunnubles.indexOfKey(r0) < 0) goto L5;
        return;
    L5:
        NotificationCenter$$ExternalSyntheticLambda0 r1 = new NotificationCenter$$ExternalSyntheticLambda0(this, r3, r4, r0);
        this.alreadyPostedRunnubles.put(r0, r1);
        AndroidUtilities.runOnUIThread(r1, 250);
    }

    public static void sanitize() {
        sanitizeInternal(globalInstance);
        NotificationCenter[] r0 = Instance;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        sanitizeInternal(r0[r2]);
        r2 = r2 + 1;
        goto L3
    }

    private static void sanitizeInternal(NotificationCenter r7) {
        if (r7 == null) goto L37;
        int r0 = 0;
    L6:
        if (r0 >= r7.observers.size()) goto L51;
        int r1 = r7.observers.keyAt(r0);
        ArrayList<NotificationCenterDelegate> r2 = r7.observers.valueAt(r0);
        int r3 = r2.size() - 1;
    L8:
        if (r3 < 0) goto L36;
        Object r4 = (NotificationCenterDelegate) r2.get(r3);
        if ((r4 instanceof WeakObserversGroupImpl) == true) goto L35;
        if ((r4 instanceof BaseController) == true) goto L35;
        if ((r4 instanceof Context) == false) goto L21;
        if (isContextDestroyed((Context) r4) == false) goto L35;
        FileLog.e("MEMORY_LEAK observer " + r1 + " with destroyed Context");
        r2.remove(r3);
        goto L35
    L21:
        if ((r4 instanceof View) == false) goto L26;
        if (isContextDestroyed(((View) r4).getContext()) == false) goto L35;
        FileLog.e("MEMORY_LEAK observer " + r1 + " with View with destroyed Context");
        r2.remove(r3);
        goto L35
    L26:
        if ((r4 instanceof BaseFragment) == false) goto L31;
        if (((BaseFragment) r4).isFinished == false) goto L35;
        FileLog.e("MEMORY_LEAK observer " + r1 + " with destroyed BaseFragment");
        r2.remove(r3);
        goto L35
    L31:
        if ((r4 instanceof BottomSheet) == false) goto L35;
        if (((BottomSheet) r4).isDismissed() == false) goto L35;
        FileLog.e("MEMORY_LEAK observer " + r1 + " with destroyed BottomSheet");
        r2.remove(r3);
    L35:
        r3 = r3 - 1;
        goto L8
    L36:
        r0 = r0 + 1;
        goto L6
    L51:
        return;
    }

    private boolean shouldDebounce(int r1, Object[] r2) {
        if (r1 != updateInterfaces) goto L6;
        return true;
    L6:
        return false;
    }

    @Deprecated(since = "use createWeakObserversGroup or createObserversGroup")
    public void addObserver(NotificationCenterDelegate r1, int r2) {
        addObserverInternal(r1, r2);
    }

    public void addPostponeNotificationsCallback(PostponeNotificationCallback r3) {
        if (BuildVars.DEBUG_VERSION == false) goto L10;
        if (Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("PostponeNotificationsCallback allowed only from MAIN thread");
        return;
    L10:
        if (this.postponeCallbackList.contains(r3) == true) goto L13;
        this.postponeCallbackList.add(r3);
        return;
    }

    public ObserversGroup createObserversGroup(NotificationCenterDelegate r3) {
        return new ObserversGroupImpl(this, r3, null);
    }

    public ObserversGroup createWeakObserversGroup(NotificationCenterDelegate r3) {
        return new WeakObserversGroupImpl(this, r3, null);
    }

    public void doOnIdle(Runnable r2) {
        if (isAnimationInProgress() == false) goto L6;
        this.delayedRunnables.add(r2);
        return;
    L6:
        r2.run();
    }

    public SparseArray<Integer> dumpObservers() {
        SparseArray<Integer> r0 = new SparseArray();
        int r2 = 0;
    L4:
        if (r2 >= this.observers.size()) goto L10;
        int r3 = this.observers.keyAt(r2);
        ArrayList<NotificationCenterDelegate> r4 = this.observers.valueAt(r2);
        if (r4 == null) goto L8;
        int r42 = r4.size();
    L9:
        r0.put(r3, Integer.valueOf(r42));
        r2 = r2 + 1;
        goto L4
    L8:
        r42 = 0;
        goto L9
    L10:
        return r0;
    }

    public int getCurrentHeavyOperationFlags() {
        return this.currentHeavyOperationFlags;
    }

    public ArrayList<NotificationCenterDelegate> getObservers(int r1) {
        return this.observers.get(r1);
    }

    public int getObserversSize() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= this.observers.size()) goto L9;
        ArrayList<NotificationCenterDelegate> r2 = this.observers.valueAt(r0);
        if (r2 == null) goto L8;
        r1 = r1 + r2.size();
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r1;
    }

    public boolean hasObservers(int r1) {
        if (this.observers.indexOfKey(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isAnimationInProgress() {
        if (this.animationInProgressCount <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public Runnable listen(View r7, int r8, Utilities.Callback<Object[]> r9) {
        if (r7 == null) goto L8;
        if (r9 == null) goto L8;
        NotificationCenter$$ExternalSyntheticLambda3 r4 = new NotificationCenter$$ExternalSyntheticLambda3(r8, r9);
        AnonymousClass1 r3 = new AnonymousClass1(this, r4, r8);
        r7.addOnAttachStateChangeListener(r3);
        return new NotificationCenter$$ExternalSyntheticLambda4(this, r7, r3, r4, r8);
    L8:
        return new NotificationCenter$$ExternalSyntheticLambda2();
    }

    public void onAnimationFinish(int r3) {
        AllowedNotifications r0 = this.allowedNotifications.get(r3);
        this.allowedNotifications.delete(r3);
        if (r0 == null) goto L13;
        this.animationInProgressCount--;
        if (this.heavyOperationsCounter.isEmpty() == true) goto L10;
        this.heavyOperationsCounter.remove(Integer.valueOf(r3));
        if (this.heavyOperationsCounter.isEmpty() == false) goto L10;
        getGlobalInstance().postNotificationName(startAllHeavyOperations, new Object[]{512});
    L10:
        if (this.animationInProgressCount != 0) goto L13;
        runDelayedNotifications();
    L13:
        if (this.checkForExpiredNotifications != null) goto L15;
        return;
    L15:
        if (this.allowedNotifications.size() != 0) goto L19;
        AndroidUtilities.cancelRunOnUIThread(this.checkForExpiredNotifications);
        this.checkForExpiredNotifications = null;
        return;
    }

    public void postNotificationName(int r14, Object... r15) {
        boolean r1 = true;
        int r2 = 0;
        if (r14 != startAllHeavyOperations) goto L5;
    L18:
        boolean r0 = true;
    L19:
        ArrayList r3 = null;
        if (r0 == true) goto L46;
        if (this.allowedNotifications.size() <= 0) goto L46;
        int r02 = this.allowedNotifications.size();
        long r4 = SystemClock.elapsedRealtime();
        int r6 = 0;
        int r7 = 0;
    L25:
        if (r6 >= this.allowedNotifications.size()) goto L41;
        AllowedNotifications r8 = this.allowedNotifications.valueAt(r6);
        if ((r4 - r8.time) <= EXPIRE_NOTIFICATIONS_TIME) goto L31;
        if (r3 != null) goto L30;
        r3 = new ArrayList();
    L30:
        r3.add(Integer.valueOf(this.allowedNotifications.keyAt(r6)));
    L31:
        int[] r82 = r8.allowedIds;
        if (r82 == null) goto L41;
        int r9 = 0;
    L35:
        if (r9 >= r82.length) goto L40;
        if (r82[r9] == r14) goto L38;
        r9 = r9 + 1;
        goto L35
    L38:
        r7 = r7 + 1;
    L40:
        r6 = r6 + 1;
    L41:
        if (r02 == r7) goto L44;
        r1 = false;
    L44:
        r0 = r1;
    L46:
        if (r14 != startAllHeavyOperations) goto L49;
        Integer r12 = (Integer) r15[0];
        this.currentHeavyOperationFlags = (~r12.intValue()) & this.currentHeavyOperationFlags;
    L52:
        if (shouldDebounce(r14, r15) == true) goto L54;
    L56:
        postNotificationNameInternal(r14, r0, r15);
    L57:
        if (r3 != null) goto L59;
        return;
    L59:
        if (r2 >= r3.size()) goto L68;
        onAnimationFinish(((Integer) r3.get(r2)).intValue());
        r2 = r2 + 1;
        goto L59
    L68:
        return;
    L54:
        if (BuildVars.DEBUG_VERSION == false) goto L56;
        postNotificationDebounced(r14, r15);
        goto L57
    L49:
        if (r14 != stopAllHeavyOperations) goto L52;
        Integer r13 = (Integer) r15[0];
        this.currentHeavyOperationFlags = r13.intValue() | this.currentHeavyOperationFlags;
        goto L52
    L5:
        if (r14 == stopAllHeavyOperations) goto L18;
        if (r14 == didReplacedPhotoInMemCache) goto L18;
        if (r14 == closeChats) goto L18;
        if (r14 == invalidateMotionBackground) goto L18;
        if (r14 == needCheckSystemBarColors) goto L18;
        if (r14 == messageReceivedByServer2) goto L18;
        r0 = false;
        goto L19
    }

    public void postNotificationNameInternal(int r5, boolean r6, Object... r7) {
        if (BuildVars.DEBUG_VERSION == false) goto L10;
        if (Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("postNotificationName allowed only from MAIN thread");
        return;
    L10:
        if (r6 == true) goto L16;
        if (isAnimationInProgress() == false) goto L16;
        this.delayedPosts.add(new DelayedPost(r5, r7, null));
        return;
    L16:
        if (this.postponeCallbackList.isEmpty() == true) goto L25;
        int r62 = 0;
    L19:
        if (r62 >= this.postponeCallbackList.size()) goto L25;
        if (this.postponeCallbackList.get(r62).needPostpone(r5, this.currentAccount, r7) == true) goto L22;
        r62 = r62 + 1;
        goto L19
    L22:
        this.delayedPosts.add(new DelayedPost(r5, r7, null));
        return;
    L25:
        this.broadcasting++;
        ArrayList<NotificationCenterDelegate> r63 = this.observers.get(r5);
        if (r63 != null) goto L28;
    L36:
        int r52 = this.broadcasting - 1;
        this.broadcasting = r52;
        if (r52 == 0) goto L39;
        return;
    L39:
        if (this.removeAfterBroadcast.size() == 0) goto L50;
        int r53 = 0;
    L41:
        int r64 = this.removeAfterBroadcast.size();
        SparseArray<ArrayList<NotificationCenterDelegate>> r72 = this.removeAfterBroadcast;
        if (r53 >= r64) goto L48;
        int r65 = r72.keyAt(r53);
        ArrayList<NotificationCenterDelegate> r73 = this.removeAfterBroadcast.get(r65);
        int r0 = 0;
    L45:
        if (r0 >= r73.size()) goto L47;
        removeObserver(r73.get(r0), r65);
        r0 = r0 + 1;
        goto L45
    L47:
        r53 = r53 + 1;
        goto L41
    L48:
        r72.clear();
    L50:
        if (this.addAfterBroadcast.size() == 0) goto L71;
        int r54 = 0;
    L52:
        int r66 = this.addAfterBroadcast.size();
        SparseArray<ArrayList<NotificationCenterDelegate>> r74 = this.addAfterBroadcast;
        if (r54 >= r66) goto L59;
        int r67 = r74.keyAt(r54);
        ArrayList<NotificationCenterDelegate> r75 = this.addAfterBroadcast.get(r67);
        int r02 = 0;
    L56:
        if (r02 >= r75.size()) goto L58;
        addObserverInternal(r75.get(r02), r67);
        r02 = r02 + 1;
        goto L56
    L58:
        r54 = r54 + 1;
        goto L52
    L59:
        r74.clear();
        return;
    L71:
        return;
    L28:
        if (r63.isEmpty() == true) goto L36;
        int r03 = 0;
    L31:
        if (r03 >= r63.size()) goto L36;
        NotificationCenterDelegate r2 = r63.get(r03);
        if (r2 == null) goto L35;
        r2.didReceivedNotification(r5, this.currentAccount, r7);
    L35:
        r03 = r03 + 1;
        goto L31
    }

    public void postNotificationNameOnUIThread(int r2, Object... r3) {
        AndroidUtilities.runOnUIThread(new NotificationCenter$$ExternalSyntheticLambda7(this, r2, r3));
    }

    public void removeDelayed(Runnable r1) {
        this.delayedRunnables.remove(r1);
    }

    public void removeObserver(NotificationCenterDelegate r3, int r4) {
        if (BuildVars.DEBUG_VERSION == false) goto L10;
        if (Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("removeObserver allowed only from MAIN thread");
        return;
    L10:
        if (this.broadcasting == 0) goto L16;
        ArrayList<NotificationCenterDelegate> r0 = this.removeAfterBroadcast.get(r4);
        if (r0 != null) goto L14;
        r0 = new ArrayList();
        this.removeAfterBroadcast.put(r4, r0);
    L14:
        r0.add(r3);
        return;
    L16:
        ArrayList<NotificationCenterDelegate> r2 = this.observers.get(r4);
        if (r2 == null) goto L20;
        r2.remove(r3);
        return;
    }

    public void removePostponeNotificationsCallback(PostponeNotificationCallback r3) {
        if (BuildVars.DEBUG_VERSION == false) goto L10;
        if (Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("removePostponeNotificationsCallback allowed only from MAIN thread");
        return;
    L10:
        if (this.postponeCallbackList.remove(r3) == false) goto L13;
        runDelayedNotifications();
        return;
    }

    public void runDelayedNotifications() {
        int r1 = 0;
        if (this.delayedPosts.isEmpty() == true) goto L10;
        this.delayedPostsTmp.clear();
        this.delayedPostsTmp.addAll(this.delayedPosts);
        this.delayedPosts.clear();
        int r0 = 0;
    L5:
        int r2 = this.delayedPostsTmp.size();
        ArrayList<DelayedPost> r3 = this.delayedPostsTmp;
        if (r0 >= r2) goto L8;
        DelayedPost r22 = r3.get(r0);
        postNotificationNameInternal(DelayedPost.m4788$$Nest$fgetid(r22), true, DelayedPost.m4787$$Nest$fgetargs(r22));
        r0 = r0 + 1;
        goto L5
    L8:
        r3.clear();
    L10:
        if (this.delayedRunnables.isEmpty() == true) goto L19;
        this.delayedRunnablesTmp.clear();
        this.delayedRunnablesTmp.addAll(this.delayedRunnables);
        this.delayedRunnables.clear();
    L12:
        int r02 = this.delayedRunnablesTmp.size();
        ArrayList<Runnable> r23 = this.delayedRunnablesTmp;
        if (r1 >= r02) goto L15;
        AndroidUtilities.runOnUIThread(r23.get(r1));
        r1 = r1 + 1;
        goto L12
    L15:
        r23.clear();
        return;
    }

    public int setAnimationInProgress(int r2, int[] r3) {
        return setAnimationInProgress(r2, r3, true);
    }

    public int setAnimationInProgress(int r3, int[] r4, boolean r5) {
        onAnimationFinish(r3);
        if (this.heavyOperationsCounter.isEmpty() == false) goto L6;
        if (r5 == false) goto L6;
        getGlobalInstance().postNotificationName(stopAllHeavyOperations, new Object[]{512});
    L6:
        this.animationInProgressCount++;
        int r32 = this.animationInProgressPointer + 1;
        this.animationInProgressPointer = r32;
        if (r5 == false) goto L9;
        this.heavyOperationsCounter.add(Integer.valueOf(r32));
    L9:
        AllowedNotifications r33 = new AllowedNotifications(null);
        r33.allowedIds = r4;
        this.allowedNotifications.put(this.animationInProgressPointer, r33);
        if (this.checkForExpiredNotifications != null) goto L13;
        NotificationCenter$$ExternalSyntheticLambda5 r34 = new NotificationCenter$$ExternalSyntheticLambda5(this);
        this.checkForExpiredNotifications = r34;
        AndroidUtilities.runOnUIThread(r34, EXPIRE_NOTIFICATIONS_TIME);
    L13:
        return this.animationInProgressPointer;
    }

    public void updateAllowedNotifications(int r1, int[] r2) {
        AllowedNotifications r0 = this.allowedNotifications.get(r1);
        if (r0 == null) goto L6;
        r0.allowedIds = r2;
        return;
    }

    public void updateObserver(boolean r1, NotificationCenterDelegate r2, int r3) {
        if (r1 == false) goto L5;
        addObserver(r2, r3);
        return;
    L5:
        removeObserver(r2, r3);
    }
}
