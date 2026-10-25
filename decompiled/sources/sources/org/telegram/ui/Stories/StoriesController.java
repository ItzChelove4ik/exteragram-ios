package org.telegram.ui.Stories;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import android.webkit.MimeTypeMap;
import androidx.collection.LongSparseArray;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileRefController;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.Timer;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Reactions.ReactionImageHolder;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.Stories.SelfStoryViewsPage;
import org.telegram.ui.Stories.bots.BotPreviewsEditContainer;
import org.telegram.ui.Stories.recorder.DraftsController;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;
import org.telegram.ui.Stories.recorder.StoryRecorder;
import org.telegram.ui.Stories.recorder.StoryUploadingService;

/* loaded from: classes3.dex */
public class StoriesController {
    public static final Comparator<TL_stories.StoryItem> storiesComparator = null;
    boolean allHiddenStoriesLoaded;
    boolean allStoriesLoaded;
    HashSet<Long> allStoriesLoading;
    private LongSparseArray<TL_stories.PeerStories> allStoriesMap;
    public final ArrayList<SearchStoriesList> attachedSearchLists;
    private LongSparseArray<Boolean> blockedOverride;
    public HashSet<Long> blocklist;
    private int blocklistCount;
    public boolean blocklistFull;
    private boolean blocklistLoading;
    private boolean blocklistLoadingReset;
    private int blocklistReqId;
    private final int currentAccount;
    public LongSparseIntArray dialogIdToMaxReadId;
    private ArrayList<TL_stories.PeerStories> dialogListStories;
    private final DraftsController draftsController;
    private final LongSparseArray<HashMap<Integer, UploadingStory>> editingStories;
    private boolean firstLoad;
    boolean hasMore;
    private boolean hasMoreHidden;
    private ArrayList<TL_stories.PeerStories> hiddenListStories;
    private long lastBlocklistRequested;
    private boolean loadedSendAs;
    HashSet<Long> loadingAllStories;
    private LongSparseIntArray loadingDialogsStories;
    boolean loadingFromDatabase;
    private boolean loadingFromServer;
    private boolean loadingFromServerHidden;
    private boolean loadingSendAs;
    SharedPreferences mainSettings;
    private final Comparator<TL_stories.PeerStories> peerStoriesComparator;
    final LongSparseArray<ViewsForPeerStoriesRequester> pollingViewsForSelfStoriesRequester;
    private final HashSet<String> requestingUnsupportedStories;
    LongSparseArray<TL_stories.StoryItem> resolvedStories;
    public LongSparseArray<SparseArray<SelfStoryViewsPage.ViewsModel>> selfViewsModel;
    public final ArrayList<TLRPC.InputPeer> sendAs;
    final Runnable sortStoriesRunnable;
    String state;
    private String stateHidden;
    private TL_stories.TL_storiesStealthMode stealthMode;
    private final HashMap<Long, HashMap<Integer, StoriesList>> storiesAlbumsLists;
    private final LongSparseArray<StoriesCollections> storiesCollections;
    private final HashMap<Long, StoriesList>[] storiesLists;
    private boolean storiesReadLoaded;
    StoriesStorage storiesStorage;
    private StoryLimit storyLimitCached;
    private boolean storyLimitFetched;
    private int totalStoriesCount;
    private int totalStoriesCountHidden;
    private final HashSet<String> unsupportedStoriesChecked;
    public int uploadedStories;
    private final LongSparseArray<ArrayList<UploadingStory>> uploadingAndEditingStories;
    private final LongSparseArray<ArrayList<UploadingStory>> uploadingStoriesByDialogId;

    /* renamed from: org.telegram.ui.Stories.StoriesController$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements RequestDelegate {
        final /* synthetic */ StoriesController this$0;
        final /* synthetic */ Utilities.Callback val$consumer;
        final /* synthetic */ long val$hash;

        public static /* synthetic */ void $r8$lambda$Mggk_Q63XsXbyPG3jD8wnlO09IA(AnonymousClass1 r0, TLObject r1, long r2, Utilities.Callback r4) {
            r0.lambda$run$0(r1, r2, r4);
        }

        public AnonymousClass1(StoriesController r1, long r2, Utilities.Callback r4) {
            this.this$0 = r1;
            this.val$hash = r2;
            this.val$consumer = r4;
        }

        private /* synthetic */ void lambda$run$0(TLObject r4, long r5, Utilities.Callback r7) {
            if (r4 == null) goto L13;
            TL_stories.TL_stories_peerStories r42 = (TL_stories.TL_stories_peerStories) r4;
            int r2 = 0;
            MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).putUsers(r42.users, false);
            MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).putChats(r42.chats, false);
            TL_stories.PeerStories r43 = r42.stories;
            if (r43 == null) goto L13;
        L6:
            if (r2 >= r43.stories.size()) goto L13;
            if ((r43.stories.get(r2).media instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L12;
            if ((r43.stories.get(r2) instanceof TL_stories.TL_storyItemSkipped) == true) goto L12;
            LongSparseArray<TL_stories.StoryItem> r3 = this.this$0.resolvedStories;
            TL_stories.StoryItem r44 = r43.stories.get(r2);
            r3.put(r5, r44);
        L14:
            r7.run(r44);
            return;
        L12:
            r2 = r2 + 1;
        L13:
            r44 = null;
            goto L14
        }

        @Override // org.telegram.tgnet.RequestDelegate
        public void run(TLObject r7, TLRPC.TL_error r8) {
            AndroidUtilities.runOnUIThread(new StoriesController$1$$ExternalSyntheticLambda0(this, r7, this.val$hash, this.val$consumer));
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoriesController$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements RequestDelegate {
        final /* synthetic */ StoriesController this$0;
        final /* synthetic */ Utilities.Callback val$consumer;
        final /* synthetic */ long val$hash;

        public static /* synthetic */ void $r8$lambda$8e6vm6oX1QkpQzdNXLrCUtW0Eos(AnonymousClass2 r0, TLObject r1, long r2, Utilities.Callback r4) {
            r0.lambda$run$0(r1, r2, r4);
        }

        public AnonymousClass2(StoriesController r1, long r2, Utilities.Callback r4) {
            this.this$0 = r1;
            this.val$hash = r2;
            this.val$consumer = r4;
        }

        private /* synthetic */ void lambda$run$0(TLObject r4, long r5, Utilities.Callback r7) {
            if (r4 == null) goto L6;
            TL_stories.TL_stories_stories r42 = (TL_stories.TL_stories_stories) r4;
            MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).putUsers(r42.users, false);
            MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).putChats(r42.chats, false);
            if (r42.stories.size() <= 0) goto L6;
            LongSparseArray<TL_stories.StoryItem> r3 = this.this$0.resolvedStories;
            TL_stories.StoryItem r43 = r42.stories.get(0);
            r3.put(r5, r43);
        L7:
            r7.run(r43);
            return;
        L6:
            r43 = null;
            goto L7
        }

        @Override // org.telegram.tgnet.RequestDelegate
        public void run(TLObject r7, TLRPC.TL_error r8) {
            AndroidUtilities.runOnUIThread(new StoriesController$2$$ExternalSyntheticLambda0(this, r7, this.val$hash, this.val$consumer));
        }
    }

    public static class BotPreview extends TL_stories.StoryItem {
        public final BotPreviewsList list;

        public BotPreview(BotPreviewsList r1, long r2, TL_bots.botPreviewMedia r4) {
            this.list = r1;
            this.dialogId = r2;
            TLRPC.MessageMedia r12 = r4.media;
            this.media = r12;
            TLRPC.Document r0 = r12.document;
            if (r0 == null) goto L6;
            r0.date = r4.date;
            return;
        L6:
            TLRPC.Photo r02 = r12.photo;
            if (r02 == null) goto L10;
            r02.date = r4.date;
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static class BotPreviewsList extends StoriesList {
        private final ArrayList<ArrayList<Integer>> fakeDays;
        public final String lang_code;
        public final ArrayList<String> lang_codes;
        private int lastId;
        private boolean loaded;
        private boolean loading;
        private int reqId;

        public static /* synthetic */ void $r8$lambda$PRl21LZk4veM5Fgt_ZAwVEjjmAg(BotPreviewsList r0, BotPreview r1, Utilities.Callback r2) {
            r0.lambda$requestReference$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$PiS81gAIwD88dPNC3eyRQ__bDso(BotPreviewsList r0, TLObject r1, Runnable r2) {
            r0.lambda$loadInternal$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$mDs5_1SbFLxyKpSZQJh8_9apNA4(BotPreviewsList r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadInternal$1(r1, r2, r3);
        }

        public BotPreviewsList(int r9, long r10, String r12, Utilities.Callback<StoriesList> r13) {
            super(r9, r10, 4, -1, r13, null);
            this.lang_codes = new ArrayList();
            this.fakeDays = new ArrayList();
            this.lastId = 0;
            this.lang_code = r12;
        }

        private /* synthetic */ void lambda$loadInternal$0(TLObject r11, Runnable r12) {
            this.reqId = 0;
            this.loading = false;
            this.loaded = true;
            this.done = true;
            ArrayList r1 = new ArrayList();
            if ((r11 instanceof Vector) == false) goto L8;
            ArrayList<T> r112 = ((Vector) r11).objects;
            int r2 = r112.size();
            int r3 = 0;
        L5:
            if (r3 >= r2) goto L10;
            Object r4 = r112.get(r3);
            r3 = r3 + 1;
            r1.add((TL_bots.botPreviewMedia) r4);
        L10:
            ArrayList r113 = new ArrayList(this.messageObjects);
            this.messageObjects.clear();
            this.fakeDays.clear();
            int r22 = r1.size();
            int r32 = 0;
        L11:
            if (r32 >= r22) goto L28;
            Object r42 = r1.get(r32);
            r32 = r32 + 1;
            TL_bots.botPreviewMedia r43 = (TL_bots.botPreviewMedia) r42;
            MessageObject r5 = new MessageObject(this.currentAccount, new BotPreview(this, this.dialogId, r43));
            int r6 = 0;
        L14:
            if (r6 >= r113.size()) goto L19;
            if (MessagesController.equals(((MessageObject) r113.get(r6)).storyItem.media, r43.media) == true) goto L17;
            r6 = r6 + 1;
            goto L14
        L17:
            MessageObject r44 = (MessageObject) r113.get(r6);
        L20:
            TL_stories.StoryItem r62 = r5.storyItem;
            TLRPC.Message r7 = r5.messageOwner;
            if (r44 != null) goto L23;
            int r45 = this.lastId;
            this.lastId = r45 + 1;
        L24:
            r7.id = r45;
            r62.id = r45;
            r5.parentStoriesList = this;
            r5.generateThumbs(false);
            if (this.fakeDays.isEmpty() == false) goto L27;
            this.fakeDays.add(new ArrayList());
        L27:
            this.fakeDays.get(0).add(Integer.valueOf(r5.getId()));
            this.messageObjects.add(r5);
            goto L11
        L23:
            r45 = r44.getId();
            goto L24
        L19:
            r44 = null;
            goto L20
        L28:
            AndroidUtilities.cancelRunOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
            AndroidUtilities.runOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
            if (r12 == null) goto L39;
            AndroidUtilities.runOnUIThread(r12);
            return;
        L39:
            return;
        L8:
            if ((r11 instanceof TL_bots.previewInfo) == false) goto L38;
            TL_bots.previewInfo r114 = (TL_bots.previewInfo) r11;
            this.lang_codes.clear();
            this.lang_codes.addAll(r114.lang_codes);
            r1.addAll(r114.media);
            goto L10
        }

        private /* synthetic */ void lambda$loadInternal$1(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new StoriesController$BotPreviewsList$$ExternalSyntheticLambda2(this, r2, r1));
        }

        private /* synthetic */ void lambda$requestReference$2(BotPreview r10, Utilities.Callback r11) {
            int r0 = 0;
        L4:
            if (r0 >= this.messageObjects.size()) goto L31;
            MessageObject r1 = this.messageObjects.get(r0);
            if (r1 == null) goto L30;
            TL_stories.StoryItem r12 = r1.storyItem;
            if (r12 == null) goto L30;
            TLRPC.MessageMedia r2 = r12.media;
            if (r2 == null) goto L30;
            TLRPC.MessageMedia r3 = r10.media;
            TLRPC.Document r4 = r3.document;
            if (r4 == null) goto L21;
            TLRPC.Document r5 = r2.document;
            if (r5 == null) goto L30;
            if (r5.id != r4.id) goto L21;
            r11.run((BotPreview) r12);
            return;
        L21:
            TLRPC.Photo r32 = r3.photo;
            if (r32 == null) goto L30;
            TLRPC.Photo r22 = r2.photo;
            if (r22 == null) goto L30;
            if (r22.id != r32.id) goto L30;
            r11.run((BotPreview) r12);
            return;
        L30:
            r0 = r0 + 1;
            goto L4
        L31:
            r11.run(null);
        }

        public void delete(ArrayList<TLRPC.MessageMedia> r7) {
            if (r7 != null) goto L4;
            return;
        L4:
            int r0 = 0;
            int r1 = 0;
        L6:
            if (r1 >= this.messageObjects.size()) goto L20;
            MessageObject r2 = this.messageObjects.get(r1);
            int r3 = 0;
        L9:
            if (r3 >= r7.size()) goto L19;
            if (MessagesController.equals(r2.storyItem.media, r7.get(r3)) == true) goto L12;
            r3 = r3 + 1;
            goto L9
        L12:
            this.messageObjects.remove(r1);
            if (this.fakeDays.isEmpty() == false) goto L15;
        L17:
            r1 = r1 - 1;
            goto L19
        L15:
            if (r2.getId() >= this.fakeDays.get(0).size()) goto L17;
            this.fakeDays.get(0).remove(r2.getId());
        L19:
            r1 = r1 + 1;
            goto L6
        L20:
            TL_bots.deletePreviewMedia r12 = new TL_bots.deletePreviewMedia();
            r12.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.dialogId);
        L22:
            if (r0 >= r7.size()) goto L24;
            r12.media.add(MessagesController.toInputMedia(r7.get(r0)));
            r0 = r0 + 1;
            goto L22
        L24:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, null);
            AndroidUtilities.cancelRunOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
            AndroidUtilities.runOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
        }

        public void delete(TLRPC.MessageMedia r2) {
            delete(new ArrayList(Arrays.asList(new TLRPC.MessageMedia[]{r2})));
        }

        public void edit(TLRPC.InputMedia r8, TL_bots.botPreviewMedia r9) {
            int r1 = 0;
        L4:
            if (r1 >= this.messageObjects.size()) goto L28;
            MessageObject r2 = this.messageObjects.get(r1);
            if (r8 == null) goto L27;
            TL_stories.StoryItem r3 = r2.storyItem;
            if (r3 == null) goto L27;
            TLRPC.MessageMedia r32 = r3.media;
            if (r32 == null) goto L27;
            if ((r8 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L20;
            TLRPC.Photo r33 = r32.photo;
            if (r33 == null) goto L27;
            if (r33.id != ((TLRPC.TL_inputMediaPhoto) r8).id.id) goto L27;
        L29:
            if (r2 == null) goto L37;
            this.messageObjects.remove(r2);
            if (this.fakeDays.isEmpty() == false) goto L33;
            this.fakeDays.add(new ArrayList());
        L33:
            if (r1 <= 0) goto L37;
            if (r1 >= this.fakeDays.get(0).size()) goto L37;
            this.fakeDays.get(0).remove(r1);
        L37:
            MessageObject r82 = new MessageObject(this.currentAccount, new BotPreview(this, this.dialogId, r9));
            TL_stories.StoryItem r92 = r82.storyItem;
            TLRPC.Message r34 = r82.messageOwner;
            if (r2 != null) goto L40;
            int r22 = this.lastId;
            this.lastId = r22 + 1;
        L41:
            r34.id = r22;
            r92.id = r22;
            r82.parentStoriesList = this;
            r82.generateThumbs(false);
            if (this.fakeDays.isEmpty() == false) goto L44;
            this.fakeDays.add(new ArrayList());
        L44:
            this.fakeDays.get(0).add(r1, Integer.valueOf(r82.getId()));
            this.messageObjects.add(r1, r82);
            notifyUpdate();
            return;
        L40:
            r22 = r2.getId();
            goto L41
        L20:
            if ((r8 instanceof TLRPC.TL_inputMediaDocument) == false) goto L27;
            TLRPC.Document r35 = r32.document;
            if (r35 == null) goto L27;
            if (r35.id == ((TLRPC.TL_inputMediaDocument) r8).id.id) goto L29;
        L27:
            r1 = r1 + 1;
            goto L4
        L28:
            r2 = null;
            r1 = 0;
            goto L29
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public MessageObject findMessageObject(int r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.messageObjects.size()) goto L10;
            if (this.messageObjects.get(r0).getId() == r3) goto L8;
            r0 = r0 + 1;
            goto L4
        L8:
            return this.messageObjects.get(r0);
        L10:
            return null;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public int getCount() {
            return this.messageObjects.size();
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public ArrayList<ArrayList<Integer>> getDays() {
            return this.fakeDays;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public int getLoadedCount() {
            return this.messageObjects.size();
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void invalidateCache() {
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean isLoading() {
            return this.loading;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean isOnlyCache() {
            return false;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean load(boolean r1, int r2, List<Integer> r3) {
            return loadInternal(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean loadInternal(Runnable r5) {
            if (this.loading == false) goto L5;
            return false;
        L5:
            if (this.loaded == false) goto L8;
            return false;
        L8:
            if (this.lang_code == null) goto L10;
            TL_bots.getPreviewInfo r0 = new TL_bots.getPreviewInfo();
            r0.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.dialogId);
            r0.lang_code = this.lang_code;
            TL_bots.getPreviewMedias r02 = r0;
        L11:
            this.loading = true;
            this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new StoriesController$BotPreviewsList$$ExternalSyntheticLambda1(this, r5));
            return true;
        L10:
            TL_bots.getPreviewMedias r03 = new TL_bots.getPreviewMedias();
            r03.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.dialogId);
            r02 = r03;
            goto L11
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean markAsRead(int r1) {
            return false;
        }

        public void notifyUpdate() {
            AndroidUtilities.cancelRunOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
            AndroidUtilities.runOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void preloadCache() {
        }

        public void push(TL_bots.botPreviewMedia r6) {
            MessageObject r0 = new MessageObject(this.currentAccount, new BotPreview(this, this.dialogId, r6));
            TL_stories.StoryItem r62 = r0.storyItem;
            TLRPC.Message r1 = r0.messageOwner;
            int r2 = this.lastId;
            this.lastId = r2 + 1;
            r1.id = r2;
            r62.id = r2;
            r0.parentStoriesList = this;
            r0.generateThumbs(false);
            if (this.fakeDays.isEmpty() == false) goto L5;
            this.fakeDays.add(new ArrayList());
        L5:
            this.fakeDays.get(0).add(0, Integer.valueOf(r0.getId()));
            this.messageObjects.add(0, r0);
            notifyUpdate();
        }

        public void reload(Runnable r5) {
            if (this.reqId == 0) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
            this.reqId = 0;
        L5:
            this.loading = false;
            this.loaded = false;
            loadInternal(r5);
        }

        public void requestReference(BotPreview r2, Utilities.Callback<BotPreview> r3) {
            reload(new StoriesController$BotPreviewsList$$ExternalSyntheticLambda0(this, r2, r3));
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void saveCache() {
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void updatePinnedOrder(ArrayList<Integer> r10, boolean r11) {
            ArrayList r112 = new ArrayList();
            ArrayList r0 = new ArrayList();
            TL_bots.reorderPreviewMedias r1 = new TL_bots.reorderPreviewMedias();
            r1.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.dialogId);
            r1.lang_code = this.lang_code;
            int r2 = r10.size();
            int r4 = 0;
        L3:
            if (r4 >= r2) goto L8;
            Integer r5 = r10.get(r4);
            r4 = r4 + 1;
            Integer r52 = r5;
            MessageObject r6 = findMessageObject(r52.intValue());
            if (r6 == null) goto L3;
            r1.order.add(MessagesController.toInputMedia(r6.storyItem.media));
            r112.add(r6);
            r0.add(r52);
            goto L3
        L8:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, null);
            if (this.fakeDays.isEmpty() == false) goto L11;
            this.fakeDays.add(new ArrayList());
        L11:
            this.fakeDays.get(0).clear();
            this.fakeDays.get(0).addAll(r0);
            this.messageObjects.clear();
            this.messageObjects.addAll(r112);
        }
    }

    /* loaded from: classes7.dex */
    public static class SearchStoriesList extends StoriesList {
        private int count;
        private final ArrayList<ArrayList<Integer>> fakeDays;
        private String last_offset;
        private boolean loading;
        public final String query;
        public final TL_stories.MediaArea queryArea;
        private int reqId;
        public final String username;

        public static /* synthetic */ void $r8$lambda$AEFCDCjUtgDO8oGaU53Ay1iMLjg(SearchStoriesList r0, TLObject r1) {
            r0.lambda$load$1(r1);
        }

        /* renamed from: $r8$lambda$Svy-F2SVbeABitKmxbIXjWxyLLQ, reason: not valid java name */
        public static /* synthetic */ void m19708$r8$lambda$SvyF2SVbeABitKmxbIXjWxyLLQ(SearchStoriesList r0, boolean r1, int r2, List r3, Long r4) {
            r0.lambda$load$0(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$U03vayNwVsEQYqjtQFhi_86W5MQ(SearchStoriesList r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$load$2(r1, r2);
        }

        public SearchStoriesList(int r9, String r10, String r11) {
            super(r9, 0, 3, -1, null, null);
            this.fakeDays = new ArrayList();
            this.last_offset = _UrlKt.FRAGMENT_ENCODE_SET;
            this.query = r11;
            this.username = r10;
            this.queryArea = null;
        }

        public SearchStoriesList(int r9, TL_stories.MediaArea r10) {
            super(r9, 0, 3, -1, null, null);
            this.fakeDays = new ArrayList();
            this.last_offset = _UrlKt.FRAGMENT_ENCODE_SET;
            this.query = null;
            this.username = null;
            this.queryArea = r10;
        }

        private /* synthetic */ void lambda$load$0(boolean r2, int r3, List r4, Long r5) {
            TLObject r52 = MessagesController.getInstance(this.currentAccount).getUserOrChat(this.username);
            this.loading = false;
            if (r52 == null) goto L6;
            load(r2, r3, r4);
            return;
        L6:
            this.count = 0;
            this.last_offset = _UrlKt.FRAGMENT_ENCODE_SET;
            AndroidUtilities.cancelRunOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
            AndroidUtilities.runOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
        }

        private /* synthetic */ void lambda$load$1(TLObject r9) {
            this.reqId = 0;
            if ((r9 instanceof TL_stories.TL_foundStories) == false) goto L20;
            TL_stories.TL_foundStories r92 = (TL_stories.TL_foundStories) r9;
            MessagesController.getInstance(this.currentAccount).putUsers(r92.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r92.chats, false);
            ArrayList<TL_stories.TL_foundStory> r1 = r92.stories;
            int r2 = r1.size();
            int r3 = 0;
        L5:
            if (r3 >= r2) goto L7;
            TL_stories.TL_foundStory r4 = r1.get(r3);
            r3 = r3 + 1;
            TL_stories.TL_foundStory r42 = r4;
            r42.storyItem.dialogId = DialogObject.getPeerDialogId(r42.peer);
            r42.storyItem.messageId = this.messageObjects.size();
            MessageObject r5 = new MessageObject(this.currentAccount, r42.storyItem);
            r5.generateThumbs(false);
            ArrayList<Integer> r43 = new ArrayList();
            r43.add(Integer.valueOf(this.messageObjects.size()));
            this.fakeDays.add(r43);
            this.messageObjects.add(r5);
            goto L5
        L7:
            this.count = Math.max(this.messageObjects.size(), r92.count);
            if (r92.stories.isEmpty() == false) goto L11;
            this.count = this.messageObjects.size();
        L11:
            if (this.messageObjects.size() < r92.count) goto L13;
        L16:
            String r93 = null;
        L17:
            this.last_offset = r93;
            this.loading = false;
            AndroidUtilities.cancelRunOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
            AndroidUtilities.runOnUIThread(StoriesList.m19716$$Nest$fgetnotify(this));
            return;
        L13:
            if (r92.stories.isEmpty() == true) goto L16;
            r93 = r92.next_offset;
            goto L17
        }

        private /* synthetic */ void lambda$load$2(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new StoriesController$SearchStoriesList$$ExternalSyntheticLambda2(this, r1));
        }

        public void cancel() {
            if (this.reqId == 0) goto L6;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
            this.reqId = 0;
            return;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public MessageObject findMessageObject(int r2) {
            if (r2 >= 0) goto L4;
            return null;
        L4:
            if (r2 < this.messageObjects.size()) goto L7;
            return null;
        L7:
            return this.messageObjects.get(r2);
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public int getCount() {
            return this.count;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public ArrayList<ArrayList<Integer>> getDays() {
            return this.fakeDays;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public int getLoadedCount() {
            return this.messageObjects.size();
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void invalidateCache() {
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean isLoading() {
            return this.loading;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean isOnlyCache() {
            return false;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean load(boolean r5, int r6, List<Integer> r7) {
            if (this.loading == false) goto L6;
            return false;
        L6:
            if (this.last_offset != null) goto L8;
            return false;
        L8:
            TL_stories.TL_stories_searchPosts r0 = new TL_stories.TL_stories_searchPosts();
            r0.offset = this.last_offset;
            r0.limit = r6;
            String r1 = this.query;
            if (r1 == null) goto L11;
            r0.flags |= 1;
            r0.hashtag = r1;
        L11:
            TL_stories.MediaArea r12 = this.queryArea;
            if (r12 == null) goto L14;
            r0.flags |= 2;
            r0.area = r12;
        L14:
            this.loading = true;
            if (TextUtils.isEmpty(this.username) == true) goto L20;
            TLObject r13 = MessagesController.getInstance(this.currentAccount).getUserOrChat(this.username);
            if (r13 != null) goto L21;
            MessagesController.getInstance(this.currentAccount).getUserNameResolver().resolve(this.username, new StoriesController$SearchStoriesList$$ExternalSyntheticLambda0(this, r5, r6, r7));
            return true;
        L21:
            if (r13 == null) goto L23;
            r0.flags |= 4;
            r0.peer = MessagesController.getInputPeer(r13);
        L23:
            this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$SearchStoriesList$$ExternalSyntheticLambda1(this));
            return true;
        L20:
            r13 = null;
            goto L21
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean markAsRead(int r1) {
            return false;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void preloadCache() {
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void saveCache() {
        }
    }

    /* loaded from: classes7.dex */
    public class StoriesCollections {
        public ArrayList<StoryAlbum> collections;
        public boolean creating;
        public final int currentAccount;
        public int currentRequestId;
        public final long dialogId;
        public final boolean isSelf;
        private ArrayList<StoryAlbum> lastCollections;
        public boolean loaded;
        private boolean loadedCache;
        public boolean loading;
        final /* synthetic */ StoriesController this$0;

        public static /* synthetic */ void $r8$lambda$EjwPCvVHg1OdqsSWaYmZgALK3p4(StoriesCollections r0, List r1) {
            r0.lambda$load$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$Pqh_viu1ivnpOd51WOAEMNnwbw8(StoriesCollections r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$createCollection$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$VCeUGGr2GZ3ceowPtrSEpZdH3AU(StoriesCollections r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$load$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$dNWDaulBCDVU9GkGHWt5mlqV1Is(StoriesCollections r0, TLObject r1, Utilities.Callback r2, TLRPC.TL_error r3) {
            r0.lambda$createCollection$4(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$gfm2UPehN4dX3BgyEYhLjM_LjNo(StoriesCollections r0, List r1) {
            r0.lambda$load$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$uvFFfvs_KHXuIe2qQkvEjw5ayEQ(StoriesCollections r0, TLObject r1) {
            r0.lambda$load$2(r1);
        }

        private StoriesCollections(StoriesController r7, int r8, long r9) {
            this(r7, r8, r9, true);
        }

        public /* synthetic */ StoriesCollections(StoriesController r1, int r2, long r3, StoriesControllerIA r5) {
            this(r1, r2, r3);
        }

        private StoriesCollections(StoriesController r1, int r2, long r3, boolean r5) {
            this.this$0 = r1;
            this.lastCollections = new ArrayList();
            this.collections = new ArrayList();
            this.currentRequestId = -1;
            this.currentAccount = r2;
            this.dialogId = r3;
            if (r3 != UserConfig.getInstance(r2).getClientUserId()) goto L5;
            boolean r12 = true;
        L6:
            this.isSelf = r12;
            if (r5 == false) goto L10;
            load();
            return;
        L10:
            return;
        L5:
            r12 = false;
            goto L6
        }

        private /* synthetic */ void lambda$createCollection$4(TLObject r3, Utilities.Callback r4, TLRPC.TL_error r5) {
            this.creating = false;
            if ((r3 instanceof TL_stories.TL_storyAlbum) == false) goto L8;
            StoryAlbum r32 = StoryAlbum.from((TL_stories.TL_storyAlbum) r3);
            this.collections.add(r32);
            updateAlbumsListCache(true);
            if (r4 == null) goto L14;
            r4.run(r32);
            return;
        L14:
            return;
        L8:
            if (r5 == null) goto L12;
            BaseFragment r33 = LaunchActivity.getSafeLastFragment();
            if (r33 == null) goto L12;
            BulletinFactory.of(r33).showForError(r5);
        L12:
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storyAlbumsCollectionsUpdate, new Object[]{Long.valueOf(this.dialogId), this});
        }

        private /* synthetic */ void lambda$createCollection$5(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesCollections$$ExternalSyntheticLambda5(this, r3, r2, r4));
        }

        private /* synthetic */ void lambda$load$0(List r4) {
            this.collections.clear();
            this.collections.addAll(r4);
            this.loadedCache = true;
            this.loading = false;
            load();
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storyAlbumsCollectionsUpdate, new Object[]{Long.valueOf(this.dialogId), this});
        }

        private /* synthetic */ void lambda$load$1(List r2) {
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesCollections$$ExternalSyntheticLambda2(this, r2));
        }

        private /* synthetic */ void lambda$load$2(TLObject r7) {
            if ((r7 instanceof TL_stories.TL_albums) == false) goto L10;
            TL_stories.TL_albums r72 = (TL_stories.TL_albums) r7;
            ArrayList r0 = new ArrayList(r72.albums.size());
            ArrayList<TL_stories.TL_storyAlbum> r73 = r72.albums;
            int r3 = r73.size();
            int r4 = 0;
        L5:
            if (r4 >= r3) goto L7;
            TL_stories.TL_storyAlbum r5 = r73.get(r4);
            r4 = r4 + 1;
            r0.add(StoryAlbum.from(r5));
            goto L5
        L7:
            this.collections.clear();
            this.collections.addAll(r0);
            this.lastCollections.clear();
            this.lastCollections.addAll(r0);
            this.loaded = true;
            this.loading = false;
            updateAlbumsListCache(true);
            return;
        L10:
            if ((r7 instanceof TL_stories.TL_albumsNotModified) == false) goto L14;
            this.collections.clear();
            this.collections.addAll(this.lastCollections);
            this.loaded = true;
            this.loading = false;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storyAlbumsCollectionsUpdate, new Object[]{Long.valueOf(this.dialogId), this});
            return;
        }

        private /* synthetic */ void lambda$load$3(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesCollections$$ExternalSyntheticLambda3(this, r1));
        }

        private void updateAlbumsListCache(boolean r5) {
            MessagesStorage.getInstance(this.currentAccount).saveStoryAlbumsCache(this.dialogId, this.collections);
            if (r5 == false) goto L6;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storyAlbumsCollectionsUpdate, new Object[]{Long.valueOf(this.dialogId), this});
            return;
        }

        public void addStories(int r17, ArrayList<TL_stories.StoryItem> r18) {
            TL_stories.TL_updateAlbum r8 = new TL_stories.TL_updateAlbum();
            r8.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r8.album_id = r17;
            r8.add_stories = new ArrayList(r18.size());
            int r1 = r18.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            TL_stories.StoryItem r3 = r18.get(r2);
            r2 = r2 + 1;
            r8.add_stories.add(Integer.valueOf(r3.id));
            goto L3
        L5:
            int r12 = r18.size();
            int r22 = 0;
        L6:
            if (r22 >= r12) goto L13;
            TL_stories.StoryItem r32 = r18.get(r22);
            r22 = r22 + 1;
            TL_stories.StoryItem r33 = r32;
            ArrayList<Integer> r4 = r33.albums;
            if (r4 == null) goto L9;
            if (r4.contains(Integer.valueOf(r17)) == true) goto L6;
            r33.albums.add(Integer.valueOf(r17));
            goto L6
        L9:
            ArrayList<Integer> r42 = new ArrayList();
            r33.albums = r42;
            r42.add(Integer.valueOf(r17));
            goto L6
        L13:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r8, null);
            StoriesList r13 = StoriesController.m19704$$Nest$mgetStoriesList(this.this$0, this.dialogId, 0, r17, false);
            if (r13 == null) goto L16;
            r13.updateStories(r18, true);
        L16:
            ArrayList<StoryAlbum> r14 = this.collections;
            int r23 = r14.size();
            int r34 = 0;
        L17:
            if (r34 >= r23) goto L22;
            StoryAlbum r43 = r14.get(r34);
            r34 = r34 + 1;
            StoriesList r44 = StoriesController.m19704$$Nest$mgetStoriesList(this.this$0, this.dialogId, 0, r43.album_id, false);
            if (r44 == null) goto L17;
            r44.updateStoryItemsAlbums(r17, r8.add_stories, false);
            goto L17
        }

        public boolean canCreateNewAlbum() {
            if (this.isSelf == true) goto L8;
            if (this.this$0.canEditStoryAlbums(this.dialogId) == true) goto L8;
            return false;
        L8:
            if (this.loaded == true) goto L11;
            return false;
        L11:
            if (this.collections.size() >= MessagesController.getInstance(this.currentAccount).config.storiesAlbumsLimit.get()) goto L14;
            return true;
        L14:
            return false;
        }

        public void createCollection(String r5, Utilities.Callback<StoryAlbum> r6) {
            if (this.creating == false) goto L5;
            return;
        L5:
            this.creating = true;
            TL_stories.TL_createAlbum r0 = new TL_stories.TL_createAlbum();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.title = r5;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$StoriesCollections$$ExternalSyntheticLambda4(this, r6));
        }

        public StoryAlbum findById(int r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.collections.size()) goto L9;
            StoryAlbum r1 = this.collections.get(r0);
            if (r4 == r1.album_id) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return r1;
        L9:
            return null;
        }

        public int indexOf(int r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.collections.size()) goto L9;
            if (r3 == this.collections.get(r0).album_id) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return r0;
        L9:
            return -1;
        }

        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.loaded == true) goto L14;
            this.loading = true;
            if (this.loadedCache == true) goto L11;
            MessagesStorage.getInstance(this.currentAccount).loadStoryAlbumsCache(this.dialogId, new StoriesController$StoriesCollections$$ExternalSyntheticLambda0(this));
            return;
        L11:
            TL_stories.TL_getAlbums r0 = new TL_stories.TL_getAlbums();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            this.currentRequestId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$StoriesCollections$$ExternalSyntheticLambda1(this));
            return;
        }

        public void removeCollection(int r5) {
            int r52 = indexOf(r5);
            if (r52 != (-1)) goto L5;
            return;
        L5:
            StoryAlbum r53 = this.collections.remove(r52);
            TL_stories.TL_deleteAlbum r0 = new TL_stories.TL_deleteAlbum();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.album_id = r53.album_id;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
            updateAlbumsListCache(true);
        }

        public void removeStories(int r12, ArrayList<TL_stories.StoryItem> r13) {
            TL_stories.TL_updateAlbum r0 = new TL_stories.TL_updateAlbum();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.album_id = r12;
            r0.delete_stories = new ArrayList(r13.size());
            int r1 = r13.size();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L5;
            TL_stories.StoryItem r4 = r13.get(r3);
            r3 = r3 + 1;
            r0.delete_stories.add(Integer.valueOf(r4.id));
            goto L3
        L5:
            int r14 = r13.size();
            int r32 = 0;
        L7:
            if (r32 >= r14) goto L13;
            TL_stories.StoryItem r5 = r13.get(r32);
            r32 = r32 + 1;
            TL_stories.StoryItem r52 = r5;
            ArrayList<Integer> r6 = r52.albums;
            if (r6 == null) goto L7;
            r6.remove(Integer.valueOf(r12));
            if (r52.albums.isEmpty() == false) goto L7;
            r52.albums = null;
            goto L7
        L13:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
            StoriesList r122 = StoriesController.m19704$$Nest$mgetStoriesList(this.this$0, this.dialogId, 0, r12, false);
            if (r122 == null) goto L16;
            r122.updateDeletedStories(r13);
        L16:
            ArrayList<StoryAlbum> r123 = this.collections;
            int r132 = r123.size();
        L17:
            if (r2 >= r132) goto L22;
            StoryAlbum r15 = r123.get(r2);
            r2 = r2 + 1;
            StoriesList r16 = StoriesController.m19704$$Nest$mgetStoriesList(this.this$0, this.dialogId, 0, r15.album_id, false);
            if (r16 == null) goto L17;
            r16.updateStoryItemsAlbums(r12, r0.delete_stories, true);
            goto L17
        }

        public void renameCollection(int r5, String r6) {
            int r0 = indexOf(r5);
            if (r0 != (-1)) goto L5;
            return;
        L5:
            this.collections.get(r0).title = r6;
            TL_stories.TL_updateAlbum r02 = new TL_stories.TL_updateAlbum();
            r02.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r02.album_id = r5;
            r02.title = r6;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, null);
            updateAlbumsListCache(true);
        }

        public void reorderComplete(boolean r1) {
            sendOrder();
            updateAlbumsListCache(r1);
        }

        public void reorderStep(ArrayList<Integer> r8) {
            HashMap r0 = new HashMap();
            ArrayList<StoryAlbum> r1 = this.collections;
            int r2 = r1.size();
            int r3 = 0;
            int r4 = 0;
        L3:
            if (r4 >= r2) goto L5;
            StoryAlbum r5 = r1.get(r4);
            r4 = r4 + 1;
            StoryAlbum r52 = r5;
            r0.put(Integer.valueOf(r52.album_id), r52);
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
            StoryAlbum r44 = (StoryAlbum) r0.get(r43);
            if (r44 == null) goto L6;
            r12.add(r44);
            goto L6
        L10:
            this.collections.clear();
            this.collections.addAll(r12);
        }

        public void sendOrder() {
            TL_stories.TL_reorderAlbums r0 = new TL_stories.TL_reorderAlbums();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.order = new ArrayList();
            ArrayList<StoryAlbum> r1 = this.collections;
            int r2 = r1.size();
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L5;
            StoryAlbum r4 = r1.get(r3);
            r3 = r3 + 1;
            r0.order.add(Integer.valueOf(r4.album_id));
            goto L3
        L5:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
        }
    }

    /* loaded from: classes7.dex */
    public static class StoriesList {
        private static HashMap<Integer, Long> lastLoadTime;
        public final int albumId;
        private final Set<Integer> cachedObjects;
        public final int currentAccount;
        private final Runnable destroyRunnable;
        public final long dialogId;
        public boolean done;
        private boolean error;
        public final HashMap<Long, TreeSet<Integer>> groupedByDay;
        private boolean invalidateAfterPreload;
        private final ArrayList<Integer> links;
        private final Set<Integer> loadedObjects;
        private boolean loading;
        private int maxLinkId;
        public final ArrayList<MessageObject> messageObjects;
        private final HashMap<Integer, MessageObject> messageObjectsMap;
        private final Runnable notify;
        public final ArrayList<Integer> pinnedIds;
        protected boolean preloading;
        private int reqId;
        private boolean saving;
        public final HashSet<Integer> seenStories;
        private boolean showPhotos;
        private boolean showVideos;
        private final ArrayList<MessageObject> tempArr;
        protected Utilities.CallbackReturn<Integer, Boolean> toLoad;
        private int totalCount;
        public final int type;

        public static /* synthetic */ void $r8$lambda$1_q_sMZouFpxHKDFCxK9ssrUrvE(TLObject r0, TLRPC.TL_error r1) {
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesList$$ExternalSyntheticLambda15());
        }

        /* renamed from: $r8$lambda$ER-DSccnQVwIBZwM8k6ng6uq9Ek, reason: not valid java name */
        public static /* synthetic */ void m19709$r8$lambda$ERDSccnQVwIBZwM8k6ng6uq9Ek(StoriesList r0, ArrayList r1, MessagesStorage r2, ArrayList r3) {
            r0.lambda$saveCache$8(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$I2mzpsXgJt70KibSxQrcG7HZlLs() {
        }

        public static /* synthetic */ void $r8$lambda$Ls00n55VlySGDxnygMGgDPx8abg(StoriesList r0, MessagesStorage r1) {
            r0.lambda$invalidateCache$6(r1);
        }

        public static /* synthetic */ Boolean $r8$lambda$MvWIKKn6MmN4SaFju2OUq1KcgL0(StoriesList r0, boolean r1, int r2, List r3, Integer r4) {
            return r0.lambda$load$10(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$RUgScZo5Ys-TZe5WJNOnDjHnHxQ, reason: not valid java name */
        public static /* synthetic */ void m19710$r8$lambda$RUgScZo5YsTZe5WJNOnDjHnHxQ(StoriesList r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$TKnqxEKEgk38EzGfL8VHVl_SqZc(StoriesList r0) {
            r0.lambda$saveCache$7();
        }

        public static /* synthetic */ void $r8$lambda$WvBQkQDroJM9z3yymNmJZVsqNjY(StoriesList r0, Utilities.Callback r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$XY5tuYON9BOj8BqKbdORqc0JrIg(StoriesList r0, int r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$load$13(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$akEYkEYKJUaXEY8atyQeA-HHM98, reason: not valid java name */
        public static /* synthetic */ void m19711$r8$lambda$akEYkEYKJUaXEY8atyQeAHHM98(StoriesList r0, ArrayList r1, ArrayList r2, ArrayList r3, ArrayList r4, HashSet r5) {
            r0.lambda$preloadCache$2(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$c_6_nSJdlQ_z6z8lNqLJ8iFk8qQ(StoriesList r0) {
            r0.lambda$invalidateCache$5();
        }

        public static /* synthetic */ void $r8$lambda$djkXNozxsI7NcmZ8S6qYljgpwEI(StoriesList r0, ArrayList r1, TL_stories.TL_stories_stories r2, int r3) {
            r0.lambda$load$11(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$gJ3kJ5LzpL6UzbdwoosGHx6lf3E() {
        }

        public static /* synthetic */ void $r8$lambda$ox89gapFu5P2nL8JM7RhsolrZjQ(StoriesList r0) {
            r0.lambda$load$12();
        }

        /* renamed from: $r8$lambda$p509TyV4xK3Z7-Fg5n4CU4UIas8, reason: not valid java name */
        public static /* synthetic */ int m19712$r8$lambda$p509TyV4xK3Z7Fg5n4CU4UIas8(Long r2, Long r3) {
            return (int) (r3.longValue() - r2.longValue());
        }

        /* renamed from: $r8$lambda$pz9_-XhZakPvbeoowVNsqmPzqUQ, reason: not valid java name */
        public static /* synthetic */ void m19713$r8$lambda$pz9_XhZakPvbeoowVNsqmPzqUQ(TLObject r0, TLRPC.TL_error r1) {
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesList$$ExternalSyntheticLambda14());
        }

        /* renamed from: $r8$lambda$rjB-3kREfFvzIQ048CwBedVRyZU, reason: not valid java name */
        public static /* synthetic */ void m19714$r8$lambda$rjB3kREfFvzIQ048CwBedVRyZU(StoriesList r0, MessagesStorage r1) {
            r0.lambda$preloadCache$3(r1);
        }

        /* renamed from: $r8$lambda$wPVWlmQxR7Fe-aHAIEf-7YfYwlM, reason: not valid java name */
        public static /* synthetic */ void m19715$r8$lambda$wPVWlmQxR7FeaHAIEf7YfYwlM(TLObject r0, TLRPC.TL_error r1) {
        }

        /* renamed from: -$$Nest$fgetnotify, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m19716$$Nest$fgetnotify(StoriesList r0) {
            return r0.notify;
        }

        private StoriesList(int r2, long r3, int r5, int r6, Utilities.Callback<StoriesList> r7) {
            this.maxLinkId = 0;
            this.links = new ArrayList();
            this.pinnedIds = new ArrayList();
            this.groupedByDay = new HashMap();
            this.messageObjects = new ArrayList();
            this.messageObjectsMap = new HashMap();
            this.seenStories = new HashSet();
            this.showPhotos = true;
            this.showVideos = true;
            this.tempArr = new ArrayList();
            this.notify = new StoriesController$StoriesList$$ExternalSyntheticLambda1(this);
            this.totalCount = -1;
            this.reqId = -1;
            this.currentAccount = r2;
            this.dialogId = r3;
            this.type = r5;
            this.albumId = r6;
            this.destroyRunnable = new StoriesController$StoriesList$$ExternalSyntheticLambda2(this, r7);
            if (r5 != 0) goto L6;
            if (r6 <= 0) goto L6;
            this.cachedObjects = new LinkedHashSet();
            this.loadedObjects = new LinkedHashSet();
        L7:
            preloadCache();
            return;
        L6:
            this.cachedObjects = new TreeSet(Comparator.reverseOrder());
            this.loadedObjects = new TreeSet(Comparator.reverseOrder());
            goto L7
        }

        public /* synthetic */ StoriesList(int r1, long r2, int r4, int r5, Utilities.Callback r6, StoriesControllerIA r7) {
            this(r1, r2, r4, r5, r6);
        }

        public static long day(MessageObject r6) {
            if (r6 != null) goto L5;
            return 0;
        L5:
            long r0 = r6.messageOwner.date;
            Calendar r62 = Calendar.getInstance();
            r62.setTimeInMillis(r0 * 1000);
            int r02 = r62.get(1);
            int r1 = r62.get(2);
            return ((r02 * NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) + (r1 * 100)) + r62.get(5);
        }

        private boolean filter(MessageObject r1, boolean r2, boolean r3) {
            if (r1 != null) goto L4;
            return false;
        L4:
            if (r1.isStory() == false) goto L19;
            if (r2 == true) goto L7;
        L8:
            if (r3 == false) goto L12;
            if (r1.isVideo() == false) goto L12;
            return true;
        L12:
            if ((r1.storyItem.media instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L20;
            return true;
        L20:
            return false;
        L7:
            if (r1.isPhoto() == false) goto L8;
            return true;
        L19:
            return false;
        }

        private /* synthetic */ void lambda$invalidateCache$5() {
            this.cachedObjects.clear();
            fill(true);
        }

        private /* synthetic */ void lambda$invalidateCache$6(MessagesStorage r6) {
            r6.getDatabase().executeFast(String.format(Locale.US, "DELETE FROM profile_stories WHERE dialog_id = %d AND type = %d", new Object[]{Long.valueOf(this.dialogId), Integer.valueOf(this.type)})).stepThis().dispose();     // Catch: Throwable -> L4
        L6:
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesList$$ExternalSyntheticLambda12(this));
            return;
        L4:
            th = move-exception;
            r6.checkSQLException(th);
            goto L6
        }

        private /* synthetic */ Boolean lambda$load$10(boolean r1, int r2, List r3, Integer r4) {
            return Boolean.valueOf(load(r1, r2, r3));
        }

        private /* synthetic */ void lambda$load$11(ArrayList r7, TL_stories.TL_stories_stories r8, int r9) {
            this.reqId = -1;
            FileLog.d("StoriesList " + this.type + "{" + this.dialogId + "} loaded {" + StoriesController.m19707$$Nest$smstoryItemMessageIds(r7) + "}");
            this.pinnedIds.clear();
            this.pinnedIds.addAll(r8.pinned_to_top);
            MessagesController.getInstance(this.currentAccount).putUsers(r8.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r8.chats, false);
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r8.users, r8.chats, true, true);
            this.loading = false;
            this.totalCount = r8.count;
            int r82 = 0;
        L4:
            if (r82 >= r7.size()) goto L7;
            pushObject((MessageObject) r7.get(r82), false);
            r82 = r82 + 1;
            goto L4
        L7:
            if (this.loadedObjects.size() < this.totalCount) goto L9;
            boolean r72 = true;
        L10:
            this.done = r72;
            if (r72 == false) goto L13;
            Iterator<Integer> r73 = this.cachedObjects.iterator();
        L26:
            if (r73.hasNext() == false) goto L30;
            Integer r83 = r73.next();
            int r92 = r83.intValue();
            if (this.loadedObjects.contains(r83) == true) goto L26;
            r73.remove();
            removeObject(r92, false);
        L30:
            fill(true);
            if (this.done == true) goto L33;
            resetCanLoad();
        L37:
            saveCache();
            return;
        L33:
            if (lastLoadTime != null) goto L35;
            lastLoadTime = new HashMap();
        L35:
            lastLoadTime.put(Integer.valueOf(Objects.hash(new Object[]{Integer.valueOf(this.currentAccount), Integer.valueOf(this.type), Long.valueOf(this.dialogId), Integer.valueOf(this.albumId)})), Long.valueOf(System.currentTimeMillis()));
            goto L37
        L13:
            if (this.albumId > 0) goto L30;
            if (r9 != (-1)) goto L16;
            r9 = firstLoadedId();
        L16:
            int r74 = lastLoadedId();
            Iterator<Integer> r84 = this.cachedObjects.iterator();
        L18:
            if (r84.hasNext() == false) goto L30;
            Integer r0 = r84.next();
            int r1 = r0.intValue();
            if (this.loadedObjects.contains(r0) == true) goto L18;
            if (r1 < r9) goto L18;
            if (r1 > r74) goto L18;
            r84.remove();
            removeObject(r1, false);
            goto L18
        L9:
            r72 = false;
            goto L10
        }

        private /* synthetic */ void lambda$load$12() {
            this.loading = false;
            this.error = true;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesListUpdated, new Object[]{this, Boolean.FALSE});
        }

        private /* synthetic */ void lambda$load$13(int r3, TLObject r4, TLRPC.TL_error r5) {
            if ((r4 instanceof TL_stories.TL_stories_stories) == false) goto L10;
            ArrayList r52 = new ArrayList();
            TL_stories.TL_stories_stories r42 = (TL_stories.TL_stories_stories) r4;
            int r0 = 0;
        L6:
            if (r0 >= r42.stories.size()) goto L8;
            r52.add(toMessageObject(r42.stories.get(r0), r42));
            r0 = r0 + 1;
            goto L6
        L8:
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesList$$ExternalSyntheticLambda8(this, r52, r42, r3));
            return;
        L10:
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesList$$ExternalSyntheticLambda9(this));
        }

        private /* synthetic */ void lambda$new$0() {
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesListUpdated, new Object[]{this});
        }

        private /* synthetic */ void lambda$new$1(Utilities.Callback r1) {
            r1.run(this);
        }

        private /* synthetic */ void lambda$preloadCache$2(ArrayList r4, ArrayList r5, ArrayList r6, ArrayList r7, HashSet r8) {
            FileLog.d("StoriesList " + this.type + "{" + this.dialogId + "} preloadCache {" + StoriesController.m19707$$Nest$smstoryItemMessageIds(r4) + "}");
            this.pinnedIds.clear();
            this.pinnedIds.addAll(r5);
            this.preloading = false;
            MessagesController.getInstance(this.currentAccount).putUsers(r6, true);
            MessagesController.getInstance(this.currentAccount).putChats(r7, true);
            if (this.invalidateAfterPreload == false) goto L6;
            this.invalidateAfterPreload = false;
            this.toLoad = null;
            invalidateCache();
            return;
        L6:
            this.seenStories.addAll(r8);
            this.cachedObjects.clear();
            int r62 = 0;
        L8:
            if (r62 >= r4.size()) goto L10;
            pushObject((MessageObject) r4.get(r62), true);
            r62 = r62 + 1;
            goto L8
        L10:
            fill(false);
            Utilities.CallbackReturn<Integer, Boolean> r42 = this.toLoad;
            if (r42 == null) goto L13;
            r42.run(0);
            this.toLoad = null;
        L13:
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesListUpdated, new Object[]{this});
        }

        private /* synthetic */ void lambda$preloadCache$3(MessagesStorage r20) {
            StoriesList r1 = this;
            ArrayList r3 = new ArrayList();
            HashSet r6 = new HashSet();
            HashSet<Long> r0 = new HashSet();
            HashSet r4 = new HashSet();
            ArrayList r5 = new ArrayList();
            ArrayList<TLRPC.User> r7 = new ArrayList();
            ArrayList<TLRPC.Chat> r8 = new ArrayList();
            SQLiteCursor r9 = null;
            r20.getDatabase();     // Catch: Throwable -> L13
            r9 = r20.createLoadStoriesCursor(r1.dialogId, r1.albumId, r1.type);     // Catch: Throwable -> L13
        L5:
            if (r9.next() == false) goto L49;
            int r10 = 0;
            NativeByteBuffer r11 = r9.byteBufferValue(0);     // Catch: Throwable -> L13
            if (r11 == null) goto L48;
            TL_stories.StoryItem r13 = TL_stories.StoryItem.TLdeserialize(r11, r11.readInt32(true), true);     // Catch: Throwable -> L13
            r13.dialogId = r1.dialogId;     // Catch: Throwable -> L13
            r13.messageId = r13.id;     // Catch: Throwable -> L13
            MessageObject r14 = new MessageObject(r1.currentAccount, r13);     // Catch: Throwable -> L13
            ArrayList<TLRPC.PrivacyRule> r15 = r13.privacy;     // Catch: Throwable -> L13
            int r12 = r15.size();     // Catch: Throwable -> L13
        L9:
            if (r10 >= r12) goto L19;
            TLRPC.PrivacyRule r17 = r15.get(r10);     // Catch: Throwable -> L13
            int r102 = r10 + 1;     // Catch: Throwable -> L13
            TLRPC.PrivacyRule r16 = r17;     // Catch: Throwable -> L13
            if ((r16 instanceof TLRPC.TL_privacyValueDisallowUsers) == false) goto L16;
            r0.addAll(((TLRPC.TL_privacyValueDisallowUsers) r16).users);     // Catch: Throwable -> L13
        L18:
            r10 = r102;
            goto L9
        L16:
            if ((r16 instanceof TLRPC.TL_privacyValueAllowUsers) == false) goto L18;
            r0.addAll(((TLRPC.TL_privacyValueAllowUsers) r16).users);     // Catch: Throwable -> L13
            goto L18
        L19:
            TL_stories.StoryFwdHeader r18 = r13.fwd_from;     // Catch: Throwable -> L13
            if (r18 == null) goto L27;
            TLRPC.Peer r19 = r18.from;     // Catch: Throwable -> L13
            if (r19 == null) goto L27;
            NativeByteBuffer r122 = r11;
            long r103 = DialogObject.getPeerDialogId(r19);     // Catch: Throwable -> L13
            if (r103 < 0) goto L26;
            r0.add(Long.valueOf(r103));     // Catch: Throwable -> L13
        L28:
            int r110 = 0;
        L30:
            if (r110 >= r13.media_areas.size()) goto L35;
            if ((r13.media_areas.get(r110) instanceof TL_stories.TL_mediaAreaChannelPost) == false) goto L34;
            r4.add(Long.valueOf(((TL_stories.TL_mediaAreaChannelPost) r13.media_areas.get(r110)).channel_id));     // Catch: Throwable -> L13
        L34:
            r110 = r110 + 1;     // Catch: Throwable -> L13
            goto L30
        L35:
            TLRPC.Peer r111 = r13.from_id;     // Catch: Throwable -> L13
            if (r111 == null) goto L42;
            long r104 = DialogObject.getPeerDialogId(r111);     // Catch: Throwable -> L13
            if (r104 < 0) goto L41;
            r0.add(Long.valueOf(r104));     // Catch: Throwable -> L13
            goto L42
        L41:
            r4.add(Long.valueOf(-r104));     // Catch: Throwable -> L13
        L42:
            r14.generateThumbs(false);     // Catch: Throwable -> L13
            r5.add(r14);     // Catch: Throwable -> L13
            r122.reuse();     // Catch: Throwable -> L13
            if (r9.intValue(1) != 1) goto L45;
            r6.add(Integer.valueOf(r13.id));     // Catch: Throwable -> L13
        L45:
            int r112 = r9.intValue(2);     // Catch: Throwable -> L13
            if (r112 <= 0) goto L48;
            r3.add(Utilities.clamp(r112, r3.size() - 1, 0), Integer.valueOf(r13.id));     // Catch: Throwable -> L13
            goto L48
        L26:
            r4.add(Long.valueOf(-r103));     // Catch: Throwable -> L13
        L27:
            r122 = r11;
        L48:
            r1 = this;
            goto L5
        L49:
            r9.dispose();     // Catch: Throwable -> L13
            if (r0.isEmpty() == true) goto L53;
            r20.getUsersInternal(r0, r7);     // Catch: Throwable -> L13
        L53:
            if (r4.isEmpty() == true) goto L55;
            r20.getChatsInternal(TextUtils.join(",", r4), r8);     // Catch: Throwable -> L13
        L55:
            r9.dispose();
        L59:
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesList$$ExternalSyntheticLambda16(this, r5, r3, r7, r8, r6));
            return;
        L13:
            th = move-exception;
            r20.checkSQLException(th);     // Catch: Throwable -> L61
            if (r9 == null) goto L59;
        L61:
            th = move-exception;
            if (r9 == null) goto L64;
            r9.dispose();
        L64:
            throw th;
        }

        private /* synthetic */ void lambda$saveCache$7() {
            this.saving = false;
        }

        private /* synthetic */ void lambda$saveCache$8(ArrayList r13, MessagesStorage r14, ArrayList r15) {
            FileLog.d("StoriesList " + this.type + "{" + this.dialogId + "} saveCache {" + StoriesController.m19707$$Nest$smstoryItemMessageIds(r13) + "}");
            SQLitePreparedStatement r0 = null;
            SQLiteDatabase r1 = r14.getDatabase();     // Catch: Throwable -> L18
            SQLitePreparedStatement r2 = r1.executeFast("REPLACE INTO profile_stories VALUES(?, ?, ?, ?, ?, ?)");     // Catch: Throwable -> L18
            r1.executeFast(String.format(Locale.US, "DELETE FROM profile_stories_albums_links WHERE dialog_id = %d AND album_id = %d", new Object[]{Long.valueOf(this.dialogId), Integer.valueOf(this.albumId)})).stepThis().dispose();     // Catch: Throwable -> L12
            r0 = r1.executeFast("REPLACE INTO profile_stories_albums_links VALUES(?, ?, ?, ?)");     // Catch: Throwable -> L12
            int r12 = 0;
        L5:
            if (r12 >= r13.size()) goto L14;
            TL_stories.StoryItem r3 = ((MessageObject) r13.get(r12)).storyItem;     // Catch: Throwable -> L12
            if (r3 == null) goto L11;
            NativeByteBuffer r4 = new NativeByteBuffer(r3.getObjectSize());     // Catch: Throwable -> L12
            r3.serializeToStream(r4);     // Catch: Throwable -> L12
            r2.requery();     // Catch: Throwable -> L12
            r2.bindLong(1, this.dialogId);     // Catch: Throwable -> L12
            r2.bindInteger(2, r3.id);     // Catch: Throwable -> L12
            r2.bindByteBuffer(3, r4);     // Catch: Throwable -> L12
            r2.bindInteger(4, this.type);     // Catch: Throwable -> L12
            r2.bindInteger(5, this.seenStories.contains(Integer.valueOf(r3.id)) ? 1 : 0);     // Catch: Throwable -> L12
            r2.bindInteger(6, r15.indexOf(Integer.valueOf(r3.id)) + 1);     // Catch: Throwable -> L12
            r2.step();     // Catch: Throwable -> L12
            r4.reuse();     // Catch: Throwable -> L12
            r0.requery();     // Catch: Throwable -> L12
            r0.bindLong(1, this.dialogId);     // Catch: Throwable -> L12
            r0.bindInteger(2, this.albumId);     // Catch: Throwable -> L12
            r0.bindInteger(3, r3.id);     // Catch: Throwable -> L12
            r0.bindInteger(4, r12);     // Catch: Throwable -> L12
            r0.step();     // Catch: Throwable -> L12
        L11:
            r12 = r12 + 1;
            goto L5
        L14:
            if (r2 == null) goto L16;
            r2.dispose();
        L16:
            if (r0 == null) goto L25;
            r0.dispose();
        L25:
            AndroidUtilities.runOnUIThread(new StoriesController$StoriesList$$ExternalSyntheticLambda10(this));
            return;
        L12:
            th = th;
            SQLitePreparedStatement r152 = r0;
            r0 = r2;
        L33:
            r14.checkSQLException(th);     // Catch: Throwable -> L27
            if (r0 == null) goto L23;
            r0.dispose();
        L23:
            if (r152 == null) goto L25;
            r152.dispose();
        L27:
            th = move-exception;
            if (r0 == null) goto L30;
            r0.dispose();
        L30:
            if (r152 == null) goto L32;
            r152.dispose();
        L32:
            throw th;
        L18:
            th = th;
            r152 = null;
            goto L33
        }

        private void pushObject(MessageObject r4, boolean r5) {
            if (r4 != null) goto L4;
            return;
        L4:
            this.messageObjectsMap.put(Integer.valueOf(r4.getId()), r4);
            if (r5 == false) goto L7;
            Set<Integer> r52 = this.cachedObjects;
        L8:
            r52.add(Integer.valueOf(r4.getId()));
            long r0 = day(r4);
            TreeSet<Integer> r53 = this.groupedByDay.get(Long.valueOf(r0));
            if (r53 != null) goto L11;
            HashMap<Long, TreeSet<Integer>> r3 = this.groupedByDay;
            Long r54 = Long.valueOf(r0);
            TreeSet<Integer> r02 = new TreeSet(Comparator.reverseOrder());
            r3.put(r54, r02);
            r53 = r02;
        L11:
            r53.add(Integer.valueOf(r4.getId()));
            return;
        L7:
            r52 = this.loadedObjects;
            goto L8
        }

        private boolean removeObject(int r4, boolean r5) {
            MessageObject r0 = this.messageObjectsMap.remove(Integer.valueOf(r4));
            if (r5 == false) goto L5;
            this.cachedObjects.remove(Integer.valueOf(r4));
        L5:
            this.loadedObjects.remove(Integer.valueOf(r4));
            this.pinnedIds.remove(Integer.valueOf(r4));
            if (r0 == null) goto L14;
            long r02 = day(r0);
            TreeSet<Integer> r52 = this.groupedByDay.get(Long.valueOf(r02));
            if (r52 == null) goto L16;
            r52.remove(Integer.valueOf(r4));
            if (r52.isEmpty() == false) goto L17;
            this.groupedByDay.remove(Long.valueOf(r02));
            return true;
        L17:
            return true;
        L16:
            return true;
        L14:
            return false;
        }

        private static void reorder(Set<Integer> r3, List<Integer> r4) {
            LinkedHashSet r0 = new LinkedHashSet();
            Iterator<Integer> r42 = r4.iterator();
        L4:
            if (r42.hasNext() == false) goto L8;
            Integer r1 = r42.next();
            if (r3.contains(r1) == false) goto L4;
            r0.add(r1);
            goto L4
        L8:
            Iterator<Integer> r43 = r3.iterator();
        L10:
            if (r43.hasNext() == false) goto L14;
            Integer r12 = r43.next();
            if (r0.contains(r12) == true) goto L10;
            r0.add(r12);
            goto L10
        L14:
            r3.clear();
            r3.addAll(r0);
        }

        private MessageObject toMessageObject(TL_stories.StoryItem r3, TL_stories.TL_stories_stories r4) {
            r3.dialogId = this.dialogId;
            r3.messageId = r3.id;
            MessageObject r42 = new MessageObject(this.currentAccount, r3);
            r42.generateThumbs(false);
            return r42;
        }

        public boolean canLoad() {
            if (lastLoadTime != null) goto L5;
            return true;
        L5:
            int r6 = Objects.hash(new Object[]{Integer.valueOf(this.currentAccount), Integer.valueOf(this.type), Long.valueOf(this.dialogId), Integer.valueOf(this.albumId)});
            Long r62 = lastLoadTime.get(Integer.valueOf(r6));
            if (r62 != null) goto L9;
            return true;
        L9:
            if ((System.currentTimeMillis() - r62.longValue()) <= 120000) goto L11;
            return true;
        L11:
            return false;
        }

        public boolean equal(TL_stories.StoryItem r4, TL_stories.StoryItem r5) {
            if (r4 != null) goto L7;
            if (r5 != null) goto L7;
            return true;
        L7:
            if (r4 != null) goto L9;
            boolean r1 = true;
        L10:
            if (r5 != null) goto L12;
            boolean r2 = true;
        L13:
            if (r1 == r2) goto L15;
            return false;
        L15:
            if (r4 != r5) goto L17;
        L24:
            return true;
        L17:
            if (r4.id == r5.id) goto L19;
        L23:
            return false;
        L19:
            if (r4.media != r5.media) goto L23;
            if (TextUtils.equals(r4.caption, r5.caption) == false) goto L23;
        L12:
            r2 = false;
            goto L13
        L9:
            r1 = false;
            goto L10
        }

        public void fill(ArrayList<MessageObject> r8, boolean r9, boolean r10) {
            this.tempArr.clear();
            if (this.type == 0) goto L5;
        L11:
            Iterator<Integer> r0 = this.loadedObjects.iterator();
            int r2 = Integer.MAX_VALUE;
        L13:
            if (r0.hasNext() == false) goto L27;
            Integer r3 = r0.next();
            int r4 = r3.intValue();
            MessageObject r5 = this.messageObjectsMap.get(r3);
            if (this.type != 0) goto L22;
            if (this.albumId > 0) goto L22;
            if (this.pinnedIds.contains(r3) == true) goto L13;
        L22:
            if (filter(r5, r9, r10) == false) goto L24;
            this.tempArr.add(r5);
        L24:
            if (r4 >= r2) goto L13;
            r2 = r4;
            goto L13
        L27:
            if (this.done == true) goto L47;
            Iterator<Integer> r02 = this.cachedObjects.iterator();
        L30:
            if (r02.hasNext() == false) goto L47;
            if (this.totalCount != (-1)) goto L34;
        L35:
            Integer r32 = r02.next();
            int r42 = r32.intValue();
            if (this.type != 0) goto L42;
            if (this.albumId > 0) goto L42;
            if (this.pinnedIds.contains(r32) == true) goto L30;
        L42:
            if (r2 == Integer.MAX_VALUE) goto L44;
            if (r42 >= r2) goto L30;
        L44:
            MessageObject r33 = this.messageObjectsMap.get(r32);
            if (filter(r33, r9, r10) == false) goto L30;
            this.tempArr.add(r33);
            goto L30
        L34:
            if (this.tempArr.size() < this.totalCount) goto L35;
        L47:
            r8.clear();
            r8.addAll(this.tempArr);
            return;
        L5:
            if (this.albumId > 0) goto L11;
            ArrayList<Integer> r03 = this.pinnedIds;
            int r1 = r03.size();
            int r22 = 0;
        L7:
            if (r22 >= r1) goto L11;
            Integer r34 = r03.get(r22);
            r22 = r22 + 1;
            Integer r35 = r34;
            r35.intValue();
            MessageObject r36 = this.messageObjectsMap.get(r35);
            if (filter(r36, r9, r10) == false) goto L7;
            this.tempArr.add(r36);
            goto L7
        }

        public void fill(boolean r4) {
            fill(this.messageObjects, this.showPhotos, this.showVideos);
            if (r4 == false) goto L6;
            AndroidUtilities.cancelRunOnUIThread(this.notify);
            AndroidUtilities.runOnUIThread(this.notify);
            return;
        }

        public MessageObject findMessageObject(int r1) {
            return this.messageObjectsMap.get(Integer.valueOf(r1));
        }

        public int firstLoadedId() {
            if (this.loadedObjects.isEmpty() == false) goto L5;
            return -1;
        L5:
            ArrayList r0 = new ArrayList(this.loadedObjects);
            int r2 = 0;
        L7:
            if (r2 >= r0.size()) goto L12;
            Integer r3 = (Integer) r0.get(r2);
            int r4 = r3.intValue();
            if (this.pinnedIds.contains(r3) == false) goto L11;
            r2 = r2 + 1;
            goto L7
        L11:
            return r4;
        L12:
            return -1;
        }

        public int getCount() {
            if (this.showVideos == false) goto L13;
            if (this.showPhotos == false) goto L13;
            int r0 = this.totalCount;
            ArrayList<MessageObject> r1 = this.messageObjects;
            if (r0 >= 0) goto L11;
            return r1.size();
        L11:
            return Math.max(r1.size(), this.totalCount);
        L13:
            return this.messageObjects.size();
        }

        public ArrayList<ArrayList<Integer>> getDays() {
            ArrayList r0 = new ArrayList(this.groupedByDay.keySet());
            Collections.sort(r0, new StoriesController$StoriesList$$ExternalSyntheticLambda3());
            ArrayList<ArrayList<Integer>> r1 = new ArrayList();
            int r2 = this.type;
            int r3 = 0;
            if (r2 == 0) goto L5;
        L11:
            if (r2 == 0) goto L13;
        L15:
            int r22 = r0.size();
            int r4 = 0;
        L16:
            if (r4 >= r22) goto L29;
            Object r5 = r0.get(r4);
            r4 = r4 + 1;
            TreeSet<Integer> r52 = this.groupedByDay.get((Long) r5);
            if (r52 == null) goto L16;
            ArrayList<Integer> r6 = new ArrayList(r52);
            if (this.type != 0) goto L27;
            if (this.pinnedIds.isEmpty() == true) goto L27;
            ArrayList<Integer> r53 = this.pinnedIds;
            int r7 = r53.size();
            int r8 = 0;
        L24:
            if (r8 >= r7) goto L27;
            Integer r9 = r53.get(r8);
            r8 = r8 + 1;
            Integer r92 = r9;
            r92.intValue();
            r6.remove(r92);
        L27:
            if (r6.isEmpty() == true) goto L16;
            r1.add(r6);
            goto L16
        L29:
            return r1;
        L13:
            if (this.pinnedIds.isEmpty() == true) goto L15;
            r1.add(new ArrayList(this.pinnedIds));
            goto L15
        L5:
            if (this.albumId <= 0) goto L11;
            ArrayList<Integer> r02 = new ArrayList();
            ArrayList<MessageObject> r10 = this.messageObjects;
            int r23 = r10.size();
        L7:
            if (r3 >= r23) goto L9;
            MessageObject r42 = r10.get(r3);
            r3 = r3 + 1;
            r02.add(Integer.valueOf(r42.storyItem.id));
            goto L7
        L9:
            r1.add(r02);
            return r1;
        }

        public int getLoadedCount() {
            return this.loadedObjects.size();
        }

        public void invalidateCache() {
            if (this.preloading == false) goto L6;
            this.invalidateAfterPreload = true;
            return;
        L6:
            resetCanLoad();
            MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
            r0.getStorageQueue().postRunnable(new StoriesController$StoriesList$$ExternalSyntheticLambda6(this, r0));
        }

        public boolean isFull() {
            return this.done;
        }

        public boolean isLoading() {
            if (this.preloading == false) goto L5;
            return true;
        L5:
            if (this.loading == true) goto L11;
            return false;
        L11:
            return true;
        }

        public boolean isOnlyCache() {
            if (this.loadedObjects.isEmpty() == true) goto L5;
            return false;
        L5:
            if (canLoad() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isPinned(int r2) {
            if (this.type == 0) goto L5;
            return false;
        L5:
            if (this.albumId <= 0) goto L8;
            return false;
        L8:
            return this.pinnedIds.contains(Integer.valueOf(r2));
        }

        public int lastLoadedId() {
            if (this.loadedObjects.isEmpty() == false) goto L5;
            return -1;
        L5:
            ArrayList r0 = new ArrayList(this.loadedObjects);
            int r2 = r0.size() - 1;
        L6:
            if (r2 < 0) goto L11;
            Integer r3 = (Integer) r0.get(r2);
            int r4 = r3.intValue();
            if (this.pinnedIds.contains(r3) == false) goto L10;
            r2 = r2 - 1;
            goto L6
        L10:
            return r4;
        L11:
            return -1;
        }

        public int link() {
            int r0 = this.maxLinkId;
            this.maxLinkId = r0 + 1;
            this.links.add(Integer.valueOf(r0));
            Runnable r3 = this.destroyRunnable;
            if (r3 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r3);
        L5:
            return r0;
        }

        public boolean load(List<Integer> r5) {
            Iterator<Integer> r0 = r5.iterator();
        L4:
            if (r0.hasNext() == false) goto L8;
            if (this.messageObjectsMap.containsKey(r0.next()) == true) goto L4;
            boolean r02 = true;
        L10:
            return load(r02, 0, r5);
        L8:
            r02 = false;
            goto L10
        }

        public boolean load(boolean r2, int r3) {
            return load(r2, r3, Collections.EMPTY_LIST);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean load(boolean r3, int r4, List<Integer> r5) {
            if (this.loading == false) goto L5;
        L29:
            return false;
        L5:
            if (this.done == false) goto L7;
        L10:
            if (r3 == false) goto L29;
        L13:
            if (this.preloading == false) goto L16;
            this.toLoad = new StoriesController$StoriesList$$ExternalSyntheticLambda4(this, r3, r4, r5);
            return false;
        L16:
            int r32 = this.type;
            if (r32 == 0) goto L19;
        L21:
            if (r32 != 0) goto L24;
            TL_stories.TL_stories_getPinnedStories r33 = new TL_stories.TL_stories_getPinnedStories();
            r33.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            int r52 = lastLoadedId();
            r33.offset_id = r52;
            r33.limit = r4;
            TL_stories.TL_stories_getStoriesArchive r34 = r33;
        L27:
            FileLog.d("StoriesList " + this.type + "{" + this.dialogId + "} load");
            this.loading = true;
            this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r34, new StoriesController$StoriesList$$ExternalSyntheticLambda5(this, r52));
            return true;
        L24:
            if (r32 != 2) goto L26;
            TL_stories.TL_stories_getStoriesByID r35 = new TL_stories.TL_stories_getStoriesByID();
            r35.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r35.id.addAll(r5);
            r52 = -1;
            r34 = r35;
            goto L27
        L26:
            TL_stories.TL_stories_getStoriesArchive r36 = new TL_stories.TL_stories_getStoriesArchive();
            r36.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r52 = lastLoadedId();
            r36.offset_id = r52;
            r36.limit = r4;
            r34 = r36;
            goto L27
        L19:
            if (this.albumId <= 0) goto L21;
            TL_stories.TL_stories_getAlbumStories r37 = new TL_stories.TL_stories_getAlbumStories();
            r37.album_id = this.albumId;
            r37.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r52 = this.loadedObjects.size();
            r37.offset = r52;
            r37.limit = r4;
            r34 = r37;
            goto L27
        L7:
            if (this.error == true) goto L10;
            if (canLoad() == true) goto L13;
            goto L10
        }

        public boolean markAsRead(int r6) {
            if (this.seenStories.contains(Integer.valueOf(r6)) == false) goto L5;
            return false;
        L5:
            this.seenStories.add(Integer.valueOf(r6));
            saveCache();
            TL_stories.TL_stories_incrementStoryViews r0 = new TL_stories.TL_stories_incrementStoryViews();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r0.id.add(Integer.valueOf(r6));
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$StoriesList$$ExternalSyntheticLambda17());
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesReadUpdated, new Object[0]);
            return true;
        }

        public void preloadCache() {
            if (this.preloading == false) goto L5;
            return;
        L5:
            if (this.loading == false) goto L7;
            return;
        L7:
            if (this.error == true) goto L13;
            this.preloading = true;
            MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
            r0.getStorageQueue().postRunnable(new StoriesController$StoriesList$$ExternalSyntheticLambda11(this, r0));
            return;
        }

        public void reload() {
            if (this.reqId == (-1)) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
            this.reqId = -1;
        L5:
            resetCanLoad();
            int r0 = this.messageObjects.size();
            this.messageObjectsMap.clear();
            this.loadedObjects.clear();
            this.cachedObjects.clear();
            invalidateCache();
            this.done = false;
            this.error = false;
            load(true, Utilities.clamp(r0, 50, 10));
        }

        public void resetCanLoad() {
            HashMap<Integer, Long> r0 = lastLoadTime;
            if (r0 == null) goto L6;
            r0.remove(Integer.valueOf(Objects.hash(new Object[]{Integer.valueOf(this.currentAccount), Integer.valueOf(this.type), Long.valueOf(this.dialogId), Integer.valueOf(this.albumId)})));
            return;
        }

        public void saveCache() {
            if (this.saving == false) goto L5;
            return;
        L5:
            this.saving = true;
            ArrayList<MessageObject> r1 = new ArrayList();
            ArrayList r2 = new ArrayList(this.pinnedIds);
            fill(r1, true, true);
            MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
            r0.getStorageQueue().postRunnable(new StoriesController$StoriesList$$ExternalSyntheticLambda7(this, r1, r0, r2));
        }

        public boolean showPhotos() {
            return this.showPhotos;
        }

        public boolean showVideos() {
            return this.showVideos;
        }

        public void unlink(int r3) {
            this.links.remove(Integer.valueOf(r3));
            if (this.links.isEmpty() == false) goto L8;
            Runnable r32 = this.destroyRunnable;
            if (r32 == null) goto L9;
            AndroidUtilities.cancelRunOnUIThread(r32);
            AndroidUtilities.runOnUIThread(this.destroyRunnable, 300000);
            return;
        L9:
            return;
        }

        public void updateDeletedStories(List<TL_stories.StoryItem> r7) {
            FileLog.d("StoriesList " + this.type + "{" + this.dialogId + "} updateDeletedStories {" + StoriesController.m19706$$Nest$smstoryItemIds(r7) + "}");
            if (r7 == null) goto L28;
            int r0 = 0;
            boolean r1 = false;
        L7:
            if (r0 >= r7.size()) goto L21;
            TL_stories.StoryItem r2 = r7.get(r0);
            if (r2 == null) goto L20;
            if (this.loadedObjects.contains(Integer.valueOf(r2.id)) == false) goto L14;
        L15:
            this.loadedObjects.remove(Integer.valueOf(r2.id));
            this.cachedObjects.remove(Integer.valueOf(r2.id));
            int r12 = this.totalCount;
            if (r12 == (-1)) goto L18;
            this.totalCount = r12 - 1;
        L18:
            r1 = true;
        L19:
            removeObject(r2.id, true);
            goto L20
        L14:
            if (this.cachedObjects.contains(Integer.valueOf(r2.id)) == false) goto L19;
        L20:
            r0 = r0 + 1;
            goto L7
        L21:
            if (r1 == false) goto L27;
            fill(true);
            saveCache();
            return;
        L27:
            return;
        }

        public void updateFilters(boolean r1, boolean r2) {
            this.showPhotos = r1;
            this.showVideos = r2;
            fill(true);
        }

        public void updateOrderInAlbum(List<Integer> r4, boolean r5) {
            reorder(this.cachedObjects, r4);
            reorder(this.loadedObjects, r4);
            fill(false);
            if (r5 == false) goto L6;
            TL_stories.TL_updateAlbum r52 = new TL_stories.TL_updateAlbum();
            r52.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r52.album_id = this.albumId;
            r52.order = new ArrayList(r4);
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, null);
            return;
        }

        public boolean updatePinned(ArrayList<Integer> r7, boolean r8) {
            ArrayList r0 = new ArrayList(this.pinnedIds);
            int r1 = r7.size() - 1;
        L3:
            int r3 = 0;
            if (r1 < 0) goto L16;
            Integer r4 = r7.get(r1);
            r4.intValue();
            if (r8 == true) goto L8;
        L10:
            if (r8 == true) goto L14;
            if (r0.contains(r4) == false) goto L14;
            r0.remove(r4);
        L14:
            r1 = r1 - 1;
            goto L3
        L8:
            if (r0.contains(r4) == true) goto L10;
            r0.add(0, r4);
            goto L14
        L16:
            if (r0.size() <= MessagesController.getInstance(this.currentAccount).storiesPinnedToTopCountMax) goto L18;
            boolean r72 = true;
        L19:
            if (r72 == false) goto L22;
            return true;
        L22:
            if (this.pinnedIds.size() == r0.size()) goto L24;
            boolean r82 = true;
        L25:
            if (r82 == false) goto L27;
        L32:
            if (r82 == false) goto L34;
            this.pinnedIds.clear();
            this.pinnedIds.addAll(r0);
            fill(true);
            TL_stories.TL_togglePinnedToTop r83 = new TL_stories.TL_togglePinnedToTop();
            r83.id.addAll(this.pinnedIds);
            r83.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r83, new StoriesController$StoriesList$$ExternalSyntheticLambda13());
        L34:
            return r72;
        L27:
            if (r3 >= this.pinnedIds.size()) goto L32;
            if (this.pinnedIds.get(r3) != r0.get(r3)) goto L30;
            r3 = r3 + 1;
            goto L27
        L30:
            r82 = true;
            goto L32
        L24:
            r82 = false;
            goto L25
        L18:
            r72 = false;
            goto L19
        }

        public void updatePinnedOrder(ArrayList<Integer> r5, boolean r6) {
            if (this.albumId <= 0) goto L6;
            updateOrderInAlbum(r5, r6);
            return;
        L6:
            ArrayList r0 = new ArrayList(r5);
            int r52 = MessagesController.getInstance(this.currentAccount).storiesPinnedToTopCountMax;
            if (r0.size() <= r52) goto L10;
            r0.subList(r52, r0.size()).clear();
        L10:
            if (this.pinnedIds.size() != r0.size()) goto L19;
            int r53 = 0;
        L14:
            if (r53 >= this.pinnedIds.size()) goto L19;
            if (this.pinnedIds.get(r53) != r0.get(r53)) goto L19;
            r53 = r53 + 1;
        L19:
            this.pinnedIds.clear();
            this.pinnedIds.addAll(r0);
            fill(false);
            if (r6 == false) goto L25;
            TL_stories.TL_togglePinnedToTop r54 = new TL_stories.TL_togglePinnedToTop();
            r54.id.addAll(this.pinnedIds);
            r54.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r54, new StoriesController$StoriesList$$ExternalSyntheticLambda0());
            return;
        }

        public void updateStories(List<TL_stories.StoryItem> r10, boolean r11) {
            FileLog.d("StoriesList " + this.type + "{" + this.dialogId + "} updateStories {" + StoriesController.m19706$$Nest$smstoryItemIds(r10) + "}");
            if (r10 == null) goto L76;
            ArrayList r0 = new ArrayList();
            int r2 = 0;
            boolean r3 = false;
        L7:
            if (r2 >= r10.size()) goto L62;
            TL_stories.StoryItem r4 = r10.get(r2);
            if (r4 == null) goto L60;
            if (this.loadedObjects.contains(Integer.valueOf(r4.id)) == false) goto L14;
        L17:
            boolean r6 = true;
        L19:
            if (this.type != 1) goto L21;
        L24:
            boolean r7 = true;
        L25:
            int r8 = this.albumId;
            if (r8 <= 0) goto L34;
            ArrayList<Integer> r72 = r4.albums;
            if (r72 != null) goto L30;
        L32:
            r7 = false;
            goto L34
        L30:
            if (r72.contains(Integer.valueOf(r8)) == false) goto L32;
            r7 = true;
        L34:
            if ((r4 instanceof TL_stories.TL_storyItemDeleted) == false) goto L37;
            r7 = false;
        L37:
            if (r6 != r7) goto L39;
            if (r6 == false) goto L60;
            if (r7 == false) goto L60;
            MessageObject r62 = this.messageObjectsMap.get(Integer.valueOf(r4.id));
            if (r62 == null) goto L59;
            if (r11 == true) goto L59;
            if (equal(r62.storyItem, r4) == true) goto L60;
        L59:
            FileLog.d("StoriesList update story " + r4.id);
            this.messageObjectsMap.put(Integer.valueOf(r4.id), toMessageObject(r4, null));
        L51:
            r3 = true;
            goto L60
        L39:
            if (r7 == true) goto L44;
            FileLog.d("StoriesList remove story " + r4.id);
            removeObject(r4.id, true);
            int r42 = this.totalCount;
            if (r42 == (-1)) goto L51;
            this.totalCount = r42 - 1;
            goto L51
        L44:
            if (this.done == false) goto L49;
            FileLog.d("StoriesList put story " + r4.id);
            pushObject(toMessageObject(r4, null), false);
            r0.add(Integer.valueOf(r4.id));
            int r43 = this.totalCount;
            if (r43 == (-1)) goto L51;
            this.totalCount = r43 + 1;
            goto L51
        L49:
            if (this.loading == true) goto L51;
            FileLog.d("StoriesList cannot put story " + r4.id + " -> reload");
            reload();
            goto L51
        L21:
            if (r4.pinned == true) goto L24;
            r7 = false;
            goto L25
        L14:
            if (this.cachedObjects.contains(Integer.valueOf(r4.id)) == true) goto L17;
            r6 = false;
        L60:
            r2 = r2 + 1;
            goto L7
        L62:
            if (this.albumId > 0) goto L64;
        L66:
            if (r3 == false) goto L75;
            fill(true);
            saveCache();
            return;
        L75:
            return;
        L64:
            if (r0.isEmpty() == true) goto L66;
            updateOrderInAlbum(r0, false);
            goto L66
        }

        public void updateStoryItemsAlbums(int r4, List<Integer> r5, boolean r6) {
            Iterator<Integer> r52 = r5.iterator();
            boolean r0 = false;
        L4:
            if (r52.hasNext() == false) goto L11;
            Integer r1 = r52.next();
            r1.intValue();
            MessageObject r12 = this.messageObjectsMap.get(r1);
            if (r12 == null) goto L4;
            TL_stories.StoryItem r13 = r12.storyItem;
            if (r13 == null) goto L4;
            r0 = r0 | StoriesController.addOrRemoveStoryItemAlbum(r13, r4, r6);
            goto L4
        L11:
            if (r0 == false) goto L21;
            fill(true);
            saveCache();
            return;
        }

        public void updateStoryViews(List<Integer> r6, ArrayList<TL_stories.StoryViews> r7) {
            if (r6 == null) goto L25;
            if (r7 == null) goto L27;
            int r0 = 0;
            boolean r1 = false;
        L7:
            if (r0 >= r6.size()) goto L17;
            Integer r2 = r6.get(r0);
            r2.intValue();
            if (r0 >= r7.size()) goto L17;
            TL_stories.StoryViews r3 = r7.get(r0);
            MessageObject r22 = this.messageObjectsMap.get(r2);
            if (r22 == null) goto L16;
            TL_stories.StoryItem r23 = r22.storyItem;
            if (r23 == null) goto L16;
            r23.views = r3;
            r1 = true;
        L16:
            r0 = r0 + 1;
        L17:
            if (r1 == false) goto L26;
            saveCache();
            return;
        L26:
            return;
        L27:
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static class StoryAlbum {
        public int album_id;
        public TLRPC.Photo icon_photo;
        public TLRPC.Document icon_video;
        public String title;

        private StoryAlbum() {
        }

        public static StoryAlbum from(TL_stories.TL_storyAlbum r2) {
            StoryAlbum r0 = new StoryAlbum();
            r0.album_id = r2.album_id;
            r0.title = r2.title;
            r0.icon_photo = r2.icon_photo;
            r0.icon_video = r2.icon_video;
            return r0;
        }

        public TL_stories.TL_storyAlbum toTl() {
            TL_stories.TL_storyAlbum r0 = new TL_stories.TL_storyAlbum();
            r0.album_id = this.album_id;
            r0.title = this.title;
            r0.icon_photo = this.icon_photo;
            r0.icon_video = this.icon_video;
            return r0;
        }
    }

    /* loaded from: classes7.dex */
    public static class StoryLimit {
        public int remains_count;
        public int type;
        public long until;

        public StoryLimit(int r1, int r2, long r3) {
            this.type = r1;
            this.until = r3;
            this.remains_count = r2;
        }

        public boolean active(int r2) {
            return active(r2, 1);
        }

        public boolean active(int r6, int r7) {
            int r0 = this.type;
            if (r0 == 1) goto L14;
            if (r0 == 2) goto L10;
            if (r0 == 3) goto L10;
            return true;
        L10:
            if (ConnectionsManager.getInstance(r6).getCurrentTime() >= this.until) goto L12;
            return true;
        L12:
            return false;
        L14:
            if (this.remains_count >= r7) goto L16;
            return true;
        L16:
            return false;
        }

        public int getLimitReachedType() {
            int r1 = this.type;
            if (r1 != 2) goto L5;
            return 15;
        L5:
            if (r1 == 3) goto L8;
            return 14;
        L8:
            return 16;
        }
    }

    /* loaded from: classes7.dex */
    public static class StoryRepostsList extends StoriesList {
        private final ArrayList<ArrayList<Integer>> fakeDays;

        public StoryRepostsList(int r9, ArrayList<TL_stories.StoryItem> r10) {
            super(r9, 0, 3, -1, null, null);
            this.fakeDays = new ArrayList();
            append(r10);
        }

        public int append(ArrayList<TL_stories.StoryItem> r8) {
            if (r8 != null) goto L5;
            return -1;
        L5:
            int r0 = this.messageObjects.size();
            int r2 = 0;
            int r3 = 0;
        L7:
            if (r2 >= r8.size()) goto L13;
            TL_stories.StoryItem r4 = r8.get(r2);
            if (r4 == null) goto L12;
            r4.messageId = this.messageObjects.size();
            MessageObject r5 = new MessageObject(this.currentAccount, r4);
            r5.generateThumbs(false);
            ArrayList<Integer> r42 = new ArrayList();
            r42.add(Integer.valueOf(this.messageObjects.size()));
            this.fakeDays.add(r42);
            this.messageObjects.add(r5);
            r3 = r3 + 1;
        L12:
            r2 = r2 + 1;
            goto L7
        L13:
            if (r3 <= 0) goto L15;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesListUpdated, new Object[]{this});
        L15:
            return r0;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public MessageObject findMessageObject(int r2) {
            if (r2 >= 0) goto L4;
            return null;
        L4:
            if (r2 < this.messageObjects.size()) goto L7;
            return null;
        L7:
            return this.messageObjects.get(r2);
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public int getCount() {
            return this.messageObjects.size();
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public ArrayList<ArrayList<Integer>> getDays() {
            return new ArrayList(this.fakeDays);
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public int getLoadedCount() {
            return this.messageObjects.size();
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void invalidateCache() {
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean isLoading() {
            return false;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean isOnlyCache() {
            return false;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean load(boolean r1, int r2, List<Integer> r3) {
            return false;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public boolean markAsRead(int r1) {
            return false;
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void preloadCache() {
        }

        @Override // org.telegram.ui.Stories.StoriesController.StoriesList
        public void saveCache() {
        }
    }

    /* loaded from: classes7.dex */
    public class UploadingStory implements NotificationCenter.NotificationCenterDelegate {
        boolean canceled;
        float convertingProgress;
        private int currentRequest;
        long dialogId;
        private long duration;
        public final boolean edit;
        public final StoryEntry entry;
        private boolean entryDestroyed;
        public boolean failed;
        public String firstFramePath;
        private long firstSecondSize;
        public boolean hadFailed;
        public VideoEditedInfo info;
        public boolean isCloseFriends;
        boolean isVideo;
        public MessageObject messageObject;
        String path;
        private TL_bots.botPreviewMedia previewMedia;
        public float progress;
        public boolean putMessages;
        public final long random_id;
        boolean ready;
        public MessageObject sharedMessageObject;
        final /* synthetic */ StoriesController this$0;
        float uploadProgress;

        /* renamed from: $r8$lambda$2etscVXSVV-VWTpPyax8taFgfO8, reason: not valid java name */
        public static /* synthetic */ void m19717$r8$lambda$2etscVXSVVVWTpPyax8taFgfO8(UploadingStory r0, TLRPC.TL_error r1) {
            r0.lambda$sendUploadedRequest$7(r1);
        }

        public static /* synthetic */ void $r8$lambda$DE8wlfevaumwzT_eKON2R7Kxmzs(UploadingStory r0, long r1, TL_stories.StoryItem r3) {
            r0.lambda$sendUploadedRequest$5(r1, r3);
        }

        public static /* synthetic */ void $r8$lambda$MmeK65JXoyPyfRpYhp8rjfY3ikA(UploadingStory r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$sendUploadedRequest$8(r1, r2);
        }

        /* renamed from: $r8$lambda$aHICgBUDOSbEk3nKlwBrdigy-WI, reason: not valid java name */
        public static /* synthetic */ void m19718$r8$lambda$aHICgBUDOSbEk3nKlwBrdigyWI(UploadingStory r0, TLRPC.Document r1) {
            r0.lambda$sendUploadedRequest$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$gN3T1m8w71aMvc3xYrC53uXszsc(UploadingStory r0, File r1) {
            r0.lambda$start$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$n0vIsGgrXdXPgQ8K2mOmGMa1tN0(UploadingStory r0, TL_stories.TL_updateStory r1) {
            r0.lambda$sendUploadedRequest$4(r1);
        }

        /* renamed from: $r8$lambda$r3LIx6tDhzj-7Ch0TOQ57Lwm1jA, reason: not valid java name */
        public static /* synthetic */ void m19719$r8$lambda$r3LIx6tDhzj7Ch0TOQ57Lwm1jA(UploadingStory r0, VideoEditedInfo r1) {
            r0.lambda$start$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$rzvVVbhC7k_xGAe60Iwq41o0kec(UploadingStory r0) {
            r0.lambda$start$1();
        }

        public static /* synthetic */ void $r8$lambda$vFUVMS5GoOIoMuiWsCcoZKzXh_U(UploadingStory r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$sendUploadedRequest$3(r1, r2);
        }

        public UploadingStory(StoriesController r5, StoryEntry r6) {
            this.this$0 = r5;
            this.firstSecondSize = -1;
            this.entry = r6;
            this.random_id = Utilities.random.nextLong();
            this.edit = r6.isEdit;
            File r0 = r6.uploadThumbFile;
            if (r0 == null) goto L5;
            this.firstFramePath = r0.getAbsolutePath();
        L5:
            boolean r02 = r6.isError;
            this.hadFailed = r02;
            this.failed = r02;
            long r03 = r6.botId;
            if (r03 == 0) goto L10;
            this.dialogId = r03;
            return;
        L10:
            if (r6.isEdit == false) goto L13;
            this.dialogId = r6.editStoryPeerId;
            return;
        L13:
            TLRPC.InputPeer r62 = r6.peer;
            if (r62 != null) goto L16;
        L20:
            this.dialogId = UserConfig.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(r5)).clientUserId;
            return;
        L16:
            if ((r62 instanceof TLRPC.TL_inputPeerSelf) == true) goto L20;
            this.dialogId = DialogObject.getPeerDialogId(r62);
        }

        private /* synthetic */ void lambda$sendUploadedRequest$3(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda6(this.this$0));
        }

        private /* synthetic */ void lambda$sendUploadedRequest$4(TL_stories.TL_updateStory r1) {
            MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getStoriesController().processUpdate(r1);
        }

        private /* synthetic */ void lambda$sendUploadedRequest$5(long r3, TL_stories.StoryItem r5) {
            this.entryDestroyed = true;
            if (this.entry.isError == false) goto L5;
            this.this$0.getDraftsController().delete(this.entry);
        L5:
            StoryEntry r0 = this.entry;
            r0.isError = false;
            r0.error = null;
            if (r0.isEditingCover == true) goto L9;
            this.this$0.getDraftsController().saveForEdit(this.entry, r3, r5);
        L9:
            if (this.edit == true) goto L12;
            this.this$0.invalidateStoryLimit();
            return;
        }

        private /* synthetic */ void lambda$sendUploadedRequest$6(TLRPC.Document r2) {
            this.entry.editingCoverDocument = r2;
            TLRPC.TL_inputFileStoryDocument r22 = new TLRPC.TL_inputFileStoryDocument();
            r22.doc = MessagesController.toInputDocument(this.entry.editingCoverDocument);
            sendUploadedRequest(r22);
        }

        private /* synthetic */ void lambda$sendUploadedRequest$7(TLRPC.TL_error r4) {
            this.entry.isError = true;
            boolean r0 = this.this$0.checkStoryError(r4);
            StoryEntry r2 = this.entry;
            if (r0 == false) goto L5;
            r2.error = null;
        L6:
            this.entryDestroyed = true;
            this.failed = true;
            this.hadFailed = true;
            this.this$0.getDraftsController().edit(this.entry);
            return;
        L5:
            r2.error = r4;
            goto L6
        }

        private /* synthetic */ void lambda$sendUploadedRequest$8(TLObject r11, TLRPC.TL_error r12) {
            if ((r11 instanceof TLRPC.Updates) == false) goto L53;
            this.failed = false;
            TLRPC.Updates r112 = (TLRPC.Updates) r11;
            if (this.entry.isEditingCover == false) goto L8;
            MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).processUpdates(r112, false);
            AndroidUtilities.runOnUIThread(new StoriesController$UploadingStory$$ExternalSyntheticLambda1(this));
            return;
        L8:
            int r0 = 0;
            int r2 = 0;
            TL_stories.StoryItem r3 = null;
        L10:
            if (r0 >= r112.updates.size()) goto L31;
            if ((r112.updates.get(r0) instanceof TL_stories.TL_updateStory) == false) goto L19;
            TL_stories.StoryItem r22 = ((TL_stories.TL_updateStory) r112.updates.get(r0)).story;
            r22.attachPath = this.path;
            r22.firstFramePath = this.firstFramePath;
            r22.justUploaded = !this.edit;
            int r4 = r22.id;
            if (r3 != null) goto L17;
            r3 = r22;
        L16:
            r2 = r4;
            goto L19
        L17:
            r3.media = r22.media;
        L19:
            if ((r112.updates.get(r0) instanceof TL_update.TL_updateStoryID) == false) goto L30;
            TL_update.TL_updateStoryID r42 = (TL_update.TL_updateStoryID) r112.updates.get(r0);
            if (r3 != null) goto L30;
            r3 = new TL_stories.TL_storyItem();
            int r7 = ConnectionsManager.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getCurrentTime();
            r3.date = r7;
            StoryEntry r8 = this.entry;
            int r9 = r8.period;
            if (r9 != Integer.MAX_VALUE) goto L25;
            r9 = 86400;
        L25:
            r3.expire_date = r7 + r9;
            r3.parsedPrivacy = null;
            r3.privacy = StoryPrivacyBottomSheet.StoryPrivacy.toOutput(r8.privacyRules);
            if (this.entry.period != Integer.MAX_VALUE) goto L28;
            boolean r5 = true;
        L29:
            r3.pinned = r5;
            r3.dialogId = UserConfig.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).clientUserId;
            r3.attachPath = this.path;
            r3.firstFramePath = this.firstFramePath;
            r3.id = r42.id;
            r3.justUploaded = !this.edit;
            goto L30
        L28:
            r5 = false;
        L30:
            r0 = r0 + 1;
            goto L10
        L31:
            long r02 = this.dialogId;
            if (this.canceled == false) goto L36;
            TL_stories.TL_stories_deleteStories r113 = new TL_stories.TL_stories_deleteStories();
            TLRPC.InputPeer r122 = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getInputPeer(this.dialogId);
            r113.peer = r122;
            if (r122 == null) goto L68;
            r113.id.add(Integer.valueOf(r2));
            ConnectionsManager.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r113, new StoriesController$UploadingStory$$ExternalSyntheticLambda2(this));
        L68:
            AndroidUtilities.runOnUIThread(new StoriesController$UploadingStory$$ExternalSyntheticLambda1(this));
            return;
        L36:
            if (r2 != 0) goto L38;
        L39:
            if (r3 == null) goto L41;
            TL_stories.TL_updateStory r123 = new TL_stories.TL_updateStory();
            r123.peer = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getPeer(r02);
            r123.story = r3;
            AndroidUtilities.runOnUIThread(new StoriesController$UploadingStory$$ExternalSyntheticLambda3(this, r123));
        L41:
            TLRPC.MessageMedia r124 = r3.media;
            if (r124 != null) goto L44;
        L51:
            AndroidUtilities.runOnUIThread(new StoriesController$UploadingStory$$ExternalSyntheticLambda4(this, r02, r3));
            MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).processUpdateArray(r112.updates, r112.users, r112.chats, false, r112.date);
            goto L68
        L44:
            if (r3.attachPath == null) goto L51;
            if (r124.document == null) goto L48;
            FileLoader.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).setLocalPathTo(r3.media.document, r3.attachPath);
            goto L51
        L48:
            TLRPC.Photo r125 = r124.photo;
            if (r125 == null) goto L51;
            FileLoader.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).setLocalPathTo(FileLoader.getClosestPhotoSizeWithSize(r125.sizes, Integer.MAX_VALUE), r3.attachPath);
            goto L51
        L38:
            if (this.edit == false) goto L41;
        L53:
            if ((r11 instanceof TL_bots.botPreviewMedia) == false) goto L55;
            this.previewMedia = (TL_bots.botPreviewMedia) r11;
            goto L68
        L55:
            if (r12 != null) goto L57;
        L64:
            if (r12 == null) goto L68;
            if (this.edit == true) goto L68;
            AndroidUtilities.runOnUIThread(new StoriesController$UploadingStory$$ExternalSyntheticLambda6(this, r12));
            goto L68
        L57:
            if (FileRefController.isFileRefError(r12.text) == false) goto L64;
            StoryEntry r114 = this.entry;
            if (r114.editingCoverDocument == null) goto L64;
            Utilities.Callback<Utilities.Callback<TLRPC.Document>> r115 = r114.updateDocumentRef;
            if (r115 == null) goto L64;
            r115.run(new StoriesController$UploadingStory$$ExternalSyntheticLambda5(this));
            this.entry.updateDocumentRef = null;
        }

        private /* synthetic */ void lambda$start$0(VideoEditedInfo r5) {
            this.info = r5;
            this.messageObject.videoEditedInfo = r5;
            this.duration = r5.estimatedDuration / 1000;
            if (r5.needConvert() == false) goto L7;
            MediaController.getInstance().scheduleVideoConvert(this.messageObject, false, false, false);
            return;
        L7:
            if (new File(this.messageObject.videoEditedInfo.originalPath).renameTo(new File(this.path)) == false) goto L10;
            FileLoader.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).uploadFile(this.path, false, false, 33554432);
            return;
        }

        private /* synthetic */ void lambda$start$1() {
            this.ready = true;
            upload();
        }

        private /* synthetic */ void lambda$start$2(File r2) {
            this.entry.buildPhoto(r2);
            AndroidUtilities.runOnUIThread(new StoriesController$UploadingStory$$ExternalSyntheticLambda9(this));
        }

        private void putMessages() {
            ArrayList<Long> r1 = this.entry.shareUserIds;
            if (r1 != null) goto L5;
            return;
        L5:
            if (this.putMessages == true) goto L28;
            int r12 = r1.size();
            CharSequence r2 = this.entry.caption;
            ArrayList<TLRPC.MessageEntity> r3 = null;
            if (r2 != null) goto L10;
            String r23 = null;
        L11:
            boolean r5 = true;
            if (this.entry.caption != null) goto L14;
        L13:
            ArrayList<TLRPC.MessageEntity> r15 = r3;
            int r22 = 0;
        L16:
            if (r22 >= r12) goto L22;
            long r8 = this.entry.shareUserIds.get(r22).longValue();
            boolean r32 = this.entry.wouldBeVideo();
            StoriesController r4 = this.this$0;
            if (r32 == false) goto L20;
            boolean r33 = r5;
            SendMessagesHelper.prepareSendingVideo(AccountInstance.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(r4)), this.path, null, null, null, r8, null, null, null, null, r15, 0, null, !r6.silent, this.entry.scheduleDate, 0, false, false, r23, null, 0, 0);
        L21:
            r22 = r22 + 1;
            r5 = r33;
            goto L16
        L20:
            r33 = r5;
            ArrayList<TLRPC.MessageEntity> r14 = r15;
            String r24 = r23;
            SendMessagesHelper.prepareSendingPhoto(AccountInstance.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(r4)), this.path, null, null, r8, null, null, null, null, r14, null, null, 0, null, null, !r6.silent, this.entry.scheduleDate, 0, false, r24, null, 0, 0);
            r15 = r14;
            r23 = r24;
            goto L21
        L22:
            this.putMessages = r5;
            return;
        L14:
            r3 = MediaDataController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getEntities(new CharSequence[]{this.entry.caption}, true);
            goto L13
        L10:
            r23 = r2.toString();
            goto L11
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void sendUploadedRequest(TLRPC.InputFile r14) {
            if (this.canceled == true) goto L191;
            StoryEntry r0 = this.entry;
            if (r0.shareUserIds == null) goto L8;
            return;
        L8:
            int r4 = 0;
            if (r0.isRepost == true) goto L11;
        L21:
            TLRPC.TL_inputMediaPhoto r02 = null;
            boolean r1 = false;
        L22:
            TLRPC.InputMedia r03 = r02;
            r03 = r02;
            if (r02 != null) goto L77;
            if (r14 == null) goto L77;
            if (this.entry.wouldBeVideo() == false) goto L61;
            TLRPC.TL_inputMediaUploadedDocument r04 = new TLRPC.TL_inputMediaUploadedDocument();
            r04.file = r14;
            TLRPC.TL_documentAttributeVideo r142 = new TLRPC.TL_documentAttributeVideo();
            if (this.entry.editingCoverDocument == null) goto L36;
            int r7 = 0;
        L31:
            if (r7 >= this.entry.editingCoverDocument.attributes.size()) goto L37;
            if ((this.entry.editingCoverDocument.attributes.get(r7) instanceof TLRPC.TL_documentAttributeVideo) == true) goto L34;
            r7 = r7 + 1;
            goto L31
        L34:
            r142 = (TLRPC.TL_documentAttributeVideo) this.entry.editingCoverDocument.attributes.get(r7);
        L37:
            r04.attributes.add(r142);
            r142.supports_streaming = true;
            int r72 = r142.flags;
            r142.flags = r72 | 4;
            r142.preload_prefix_size = (int) this.firstSecondSize;
            StoryEntry r8 = this.entry;
            if (r8.cover < 0) goto L40;
            r142.flags = r72 | 20;
            r142.video_start_ts = (r9 - (r8.left * r8.duration)) / 1000.0d;
        L40:
            List<TLRPC.InputDocument> r143 = r8.stickers;
            if (r143 != null) goto L43;
        L52:
            StoryEntry r144 = this.entry;
            if (r144.audioPath == null) goto L55;
        L59:
            boolean r145 = false;
        L60:
            r04.nosound_video = r145;
            r04.mime_type = "video/mp4";
            r03 = r04;
            goto L77
        L55:
            if (r144.muted == false) goto L57;
        L58:
            r145 = true;
            goto L60
        L57:
            if (r144.isVideo == true) goto L59;
        L43:
            if (r143.isEmpty() == false) goto L48;
            List<TLRPC.InputDocument> r146 = this.entry.editStickers;
            if (r146 == null) goto L52;
            if (r146.isEmpty() == true) goto L52;
        L48:
            r04.flags |= 1;
            ArrayList<TLRPC.InputDocument> r147 = new ArrayList(this.entry.stickers);
            r04.stickers = r147;
            List<TLRPC.InputDocument> r73 = this.entry.editStickers;
            if (r73 == null) goto L51;
            r147.addAll(r73);
        L51:
            r04.attributes.add(new TLRPC.TL_documentAttributeHasStickers());
            goto L52
        L36:
            SendMessagesHelper.fillVideoAttribute(this.path, r142, null);
            goto L37
        L61:
            TLRPC.TL_inputMediaUploadedPhoto r05 = new TLRPC.TL_inputMediaUploadedPhoto();
            r05.file = r14;
            MimeTypeMap r148 = MimeTypeMap.getSingleton();
            int r74 = this.path.lastIndexOf(46);
            if (r74 == (-1)) goto L64;
            String r75 = this.path.substring(r74 + 1).toLowerCase();
        L65:
            r05.mime_type = r148.getMimeTypeFromExtension(r75);
            List<TLRPC.InputDocument> r149 = this.entry.stickers;
            r03 = r05;
            if (r149 == null) goto L77;
            if (r149.isEmpty() == false) goto L73;
            List<TLRPC.InputDocument> r1410 = this.entry.editStickers;
            r03 = r05;
            if (r1410 == null) goto L77;
            r03 = r05;
            if (r1410.isEmpty() == true) goto L77;
        L73:
            r05.flags |= 1;
            List<TLRPC.InputDocument> r1411 = this.entry.editStickers;
            if (r1411 == null) goto L76;
            r05.stickers.addAll(r1411);
        L76:
            r05.stickers = new ArrayList(this.entry.stickers);
            r03 = r05;
            goto L77
        L64:
            r75 = "txt";
        L77:
            boolean r1412 = UserConfig.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).isPremium();
            StoriesController r76 = this.this$0;
            if (r1412 == false) goto L80;
            int r1413 = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(r76)).storyCaptionLengthLimitPremium;
        L81:
            boolean r77 = this.edit;
            StoryEntry r82 = this.entry;
            if (r77 == false) goto L128;
            if (r82.botId == 0) goto L86;
            TL_bots.editPreviewMedia r1414 = new TL_bots.editPreviewMedia();
            r1414.bot = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getInputUser(this.entry.botId);
            StoryEntry r12 = this.entry;
            r1414.media = r12.editingBotPreview;
            r1414.new_media = r03;
            r1414.lang_code = r12.botLang;
            TL_stories.TL_stories_sendStory r1415 = r1414;
        L169:
            StoriesController$UploadingStory$$ExternalSyntheticLambda0 r06 = new StoriesController$UploadingStory$$ExternalSyntheticLambda0(this);
            if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L172;
        L181:
            this.currentRequest = ConnectionsManager.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r1415, r06, 64);
            return;
        L172:
            if (this.edit == true) goto L181;
            CharSequence r13 = this.entry.caption;
            if (r13 == null) goto L181;
            if (r13.toString().contains("#failtest") == false) goto L181;
            if (this.hadFailed == true) goto L181;
            TLRPC.TL_error r132 = new TLRPC.TL_error();
            r132.code = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
            r132.text = "FORCED_TO_FAIL";
            r06.run(null, r132);
            return;
        L86:
            TL_stories.TL_stories_editStory r15 = new TL_stories.TL_stories_editStory();
            r15.id = this.entry.editStoryId;
            r15.peer = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getInputPeer(this.dialogId);
            r15.flags |= 16;
            TLRPC.InputDocument r5 = this.entry.audioDocument;
            if (r5 == null) goto L89;
            r15.music = r5;
        L90:
            if (r03 != null) goto L92;
        L94:
            StoryEntry r07 = this.entry;
            if (r07.editedCaption == false) goto L109;
            CharSequence r08 = r07.caption;
            if (r08 == null) goto L109;
            r15.flags |= 2;
            CharSequence[] r52 = {r08};
            if (r08.length() <= r1413) goto L102;
            r52[0] = r52[0].subSequence(0, r1413);
        L102:
            if (MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).storyEntitiesAllowed() == false) goto L104;
            r15.entities = MediaDataController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getEntities(r52, true);
        L106:
            if (r52[0].length() <= r1413) goto L108;
            r52[0] = r52[0].subSequence(0, r1413);
        L108:
            r15.caption = r52[0].toString();
            goto L109
        L104:
            r15.entities.clear();
        L109:
            StoryEntry r1416 = this.entry;
            if (r1416.editedPrivacy == false) goto L112;
            r15.flags |= 4;
            r15.privacy_rules.addAll(r1416.privacyRules);
        L112:
            ArrayList<TL_stories.MediaArea> r1417 = this.entry.editedMediaAreas;
            if (r1417 == null) goto L116;
            r15.media_areas.addAll(r1417);
        L116:
            if (this.entry.mediaEntities == null) goto L124;
        L118:
            if (r4 >= this.entry.mediaEntities.size()) goto L124;
            TL_stories.MediaArea r1418 = this.entry.mediaEntities.get(r4).mediaArea;
            if (r1418 == null) goto L122;
            r15.media_areas.add(r1418);
        L122:
            r4 = r4 + 1;
        L124:
            if (r15.media_areas.isEmpty() == true) goto L126;
            r15.flags |= 8;
        L126:
            r1415 = r15;
            goto L169
        L92:
            if (this.entry.editedMedia == false) goto L94;
            r15.flags |= 1;
            r15.media = r03;
            goto L94
        L89:
            r15.music = new TLRPC.TL_inputDocumentEmpty();
            goto L90
        L128:
            if (r82.botId == 0) goto L130;
            TL_bots.addPreviewMedia r1419 = new TL_bots.addPreviewMedia();
            r1419.bot = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getInputUser(this.entry.botId);
            r1419.media = r03;
            r1419.lang_code = this.entry.botLang;
            r1415 = r1419;
            goto L169
        L130:
            TL_stories.TL_stories_sendStory r53 = new TL_stories.TL_stories_sendStory();
            r53.random_id = this.random_id;
            r53.peer = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getInputPeer(this.dialogId);
            r53.media = r03;
            r53.privacy_rules.addAll(this.entry.privacyRules);
            StoryEntry r09 = this.entry;
            r53.pinned = r09.pinned;
            r53.noforwards = !r09.allowScreenshots;
            if (r09.albums == null) goto L133;
            ArrayList<Integer> r010 = new ArrayList(this.entry.albums);
        L134:
            r53.albums = r010;
            StoryEntry r011 = this.entry;
            TLRPC.InputDocument r6 = r011.audioDocument;
            if (r6 == null) goto L137;
            r53.flags |= 512;
            r53.music = r6;
        L137:
            CharSequence r012 = r011.caption;
            if (r012 == null) goto L151;
            r53.flags |= 3;
            CharSequence[] r62 = {r012};
            if (r012.length() <= r1413) goto L143;
            r62[0] = r62[0].subSequence(0, r1413);
        L143:
            if (MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).storyEntitiesAllowed() == false) goto L145;
            r53.entities = MediaDataController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getEntities(r62, true);
        L147:
            if (r62[0].length() <= r1413) goto L149;
            r62[0] = r62[0].subSequence(0, r1413);
        L149:
            r53.caption = r62[0].toString();
            goto L151
        L145:
            r53.entities.clear();
        L151:
            if (this.entry.isRepost == false) goto L153;
            r53.flags |= 64;
            r53.fwd_from_id = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).getInputPeer(this.entry.repostPeer);
            r53.fwd_from_story = this.entry.repostStoryId;
            r53.fwd_modified = !r1;
        L153:
            StoryEntry r1420 = this.entry;
            int r013 = r1420.period;
            if (r013 != Integer.MAX_VALUE) goto L156;
            r53.pinned = true;
        L158:
            if (r1420.mediaEntities != null) goto L160;
        L168:
            r1415 = r53;
        L160:
            if (r4 >= this.entry.mediaEntities.size()) goto L166;
            TL_stories.MediaArea r1421 = this.entry.mediaEntities.get(r4).mediaArea;
            if (r1421 == null) goto L164;
            r53.media_areas.add(r1421);
        L164:
            r4 = r4 + 1;
            goto L160
        L166:
            if (r53.media_areas.isEmpty() == true) goto L168;
            r53.flags |= 32;
            goto L168
        L156:
            r53.flags |= 8;
            r53.period = r013;
            goto L158
        L133:
            r010 = null;
            goto L134
        L80:
            r1413 = MessagesController.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(r76)).storyCaptionLengthLimitDefault;
            goto L81
        L11:
            if (r0.editedMedia == true) goto L21;
            TLRPC.MessageMedia r014 = r0.repostMedia;
            if (r014 == null) goto L21;
            if ((r014 instanceof TLRPC.TL_messageMediaDocument) == false) goto L19;
            TLRPC.TL_inputMediaDocument r015 = new TLRPC.TL_inputMediaDocument();
            TLRPC.TL_inputDocument r16 = new TLRPC.TL_inputDocument();
            TLRPC.MessageMedia r54 = this.entry.repostMedia;
            TLRPC.Document r63 = r54.document;
            r16.id = r63.id;
            r16.access_hash = r63.access_hash;
            r16.file_reference = r63.file_reference;
            r015.id = r16;
            r015.spoiler = r54.spoiler;
            TLRPC.TL_inputMediaPhoto r016 = r015;
        L17:
            r1 = true;
            r02 = r016;
            goto L22
        L19:
            if ((r014 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L21;
            TLRPC.TL_inputMediaPhoto r017 = new TLRPC.TL_inputMediaPhoto();
            TLRPC.TL_inputPhoto r17 = new TLRPC.TL_inputPhoto();
            TLRPC.Photo r55 = this.entry.repostMedia.photo;
            r17.id = r55.id;
            r17.access_hash = r55.access_hash;
            r17.file_reference = r55.file_reference;
            r017.id = r17;
            r016 = r017;
            goto L17
        }

        private void startForeground() {
            Intent r0 = new Intent(ApplicationLoader.applicationContext, StoryUploadingService.class);
            r0.putExtra("path", this.path);
            r0.putExtra("currentAccount", StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0));
            ApplicationLoader.applicationContext.startService(r0);     // Catch: Throwable -> L5
            return;
        L5:
            th = move-exception;
            FileLog.e(th);
        }

        private void upload() {
            if (this.entry.shareUserIds == null) goto L6;
            putMessages();
            return;
        L6:
            FileLoader r1 = FileLoader.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0));
            String r2 = this.path;
            boolean r4 = !this.entry.isVideo;
            long r5 = 0;
            if (this.isVideo == false) goto L13;
            VideoEditedInfo r0 = this.info;
            if (r0 == null) goto L11;
            r5 = r0.estimatedSize;
        L11:
            r5 = Math.max(1, (int) r5);
        L13:
            if (this.entry.isVideo == false) goto L16;
            int r9 = 33554432;
        L17:
            r1.uploadFile(r2, false, r4, r5, r9, true);
            return;
        L16:
            r9 = 16777216;
            goto L17
        }

        public void cancel() {
            if (this.failed == false) goto L5;
            this.this$0.getDraftsController().delete(this.entry);
            ((ArrayList) StoriesController.m19703$$Nest$fgetuploadingStoriesByDialogId(this.this$0).get(this.dialogId)).remove(this);
        L5:
            this.canceled = true;
            if (this.entry.wouldBeVideo() == false) goto L8;
            MediaController.getInstance().cancelVideoConvert(this.messageObject);
        L8:
            FileLoader.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).cancelFileUpload(this.path, false);
            if (this.currentRequest < 0) goto L11;
            ConnectionsManager.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).cancelRequest(this.currentRequest, true);
        L11:
            cleanup();
        }

        public void cleanup() {
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).removeObserver(this, NotificationCenter.fileUploaded);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).removeObserver(this, NotificationCenter.fileUploadFailed);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).removeObserver(this, NotificationCenter.fileUploadProgressChanged);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).removeObserver(this, NotificationCenter.filePreparingFailed);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).removeObserver(this, NotificationCenter.filePreparingStarted);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).removeObserver(this, NotificationCenter.fileNewChunkAvailable);
            if (this.failed == true) goto L7;
            ArrayList r0 = (ArrayList) StoriesController.m19703$$Nest$fgetuploadingStoriesByDialogId(this.this$0).get(this.dialogId);
            if (r0 == null) goto L7;
            r0.remove(this);
        L7:
            ArrayList r02 = (ArrayList) StoriesController.m19702$$Nest$fgetuploadingAndEditingStories(this.this$0).get(this.dialogId);
            if (r02 == null) goto L14;
            r02.remove(this);
            boolean r03 = r02.isEmpty();
            StoriesController r3 = this.this$0;
            if (r03 == true) goto L12;
            r3.uploadedStories++;
            goto L14
        L12:
            r3.uploadedStories = 0;
        L14:
            if (this.edit == false) goto L19;
            HashMap r04 = (HashMap) StoriesController.m19701$$Nest$fgeteditingStories(this.this$0).get(this.dialogId);
            if (r04 == null) goto L19;
            r04.remove(Integer.valueOf(this.entry.editStoryId));
        L19:
            if (this.previewMedia == null) goto L33;
            StoriesList r05 = StoriesController.m19705$$Nest$mgetStoriesList(this.this$0, this.dialogId, 4, false);
            StoryEntry r32 = this.entry;
            if (r32 == null) goto L29;
            if (r32.isEdit == false) goto L29;
            if ((r05 instanceof BotPreviewsList) == false) goto L27;
            ((BotPreviewsList) r05).edit(r32.editingBotPreview, this.previewMedia);
        L27:
            int r33 = StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0);
            long r4 = this.dialogId;
            StoryEntry r06 = this.entry;
            BotPreviewsEditContainer.edit(r33, r4, r06.botLang, r06.editingBotPreview, this.previewMedia);
        L32:
            this.previewMedia = null;
        L29:
            if ((r05 instanceof BotPreviewsList) == false) goto L31;
            ((BotPreviewsList) r05).push(this.previewMedia);
        L31:
            BotPreviewsEditContainer.push(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0), this.dialogId, this.entry.botLang, this.previewMedia);
        L33:
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
            StoryEntry r07 = this.entry;
            if (r07 != null) goto L36;
        L40:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.uploadStoryEnd, new Object[]{this.path});
            return;
        L36:
            if (r07.isEditSaved == true) goto L40;
            if (this.entryDestroyed == true) goto L40;
            r07.destroy(false);
            this.entryDestroyed = true;
            goto L40
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r19, int r20, Object... r21) {
            if (r19 != NotificationCenter.filePreparingStarted) goto L9;
            if (r21[0] != this.messageObject) goto L55;
            this.path = (String) r21[1];
            upload();
            return;
        L55:
            return;
        L9:
            if (r19 != NotificationCenter.fileNewChunkAvailable) goto L25;
            if (r21[0] != this.messageObject) goto L56;
            String r9 = (String) r21[1];
            long r1 = ((Long) r21[2]).longValue();
            long r13 = ((Long) r21[3]).longValue();
            float r3 = ((Float) r21[4]).floatValue();
            this.convertingProgress = r3;
            this.progress = (r3 * 0.3f) + (this.uploadProgress * 0.7f);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.uploadStoryProgress, new Object[]{this.path, Float.valueOf(this.progress)});
            if (this.firstSecondSize < 0) goto L15;
        L17:
            FileLoader.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).checkUploadNewDataAvailable(r9, false, Math.max(1, r1), r13, Float.valueOf(this.convertingProgress));
            if (r13 > 0) goto L20;
            return;
        L20:
            if (this.firstSecondSize >= 0) goto L22;
            this.firstSecondSize = r13;
        L22:
            this.ready = true;
            return;
        L15:
            if ((this.convertingProgress * this.duration) < 1000.0f) goto L17;
            this.firstSecondSize = r1;
            goto L17
        L56:
            return;
        L25:
            if (r19 != NotificationCenter.filePreparingFailed) goto L34;
            if (r21[0] == this.messageObject) goto L29;
            return;
        L29:
            if (this.edit == true) goto L31;
            StoryEntry r12 = this.entry;
            r12.isError = true;
            r12.error = new TLRPC.TL_error();
            TLRPC.TL_error r14 = this.entry.error;
            r14.code = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
            r14.text = "FILE_PREPARE_FAILED";
            this.entryDestroyed = true;
            this.failed = true;
            this.hadFailed = true;
            this.this$0.getDraftsController().edit(this.entry);
        L31:
            cleanup();
            return;
        L34:
            if (r19 != NotificationCenter.fileUploaded) goto L42;
            String r15 = (String) r21[0];
            String r2 = this.path;
            if (r2 != null) goto L38;
            return;
        L38:
            if (r15.equals(r2) == false) goto L60;
            sendUploadedRequest((TLRPC.InputFile) r21[1]);
            return;
        L60:
            return;
        L42:
            if (r19 != NotificationCenter.fileUploadFailed) goto L50;
            String r16 = (String) r21[0];
            String r22 = this.path;
            if (r22 != null) goto L46;
            return;
        L46:
            if (r16.equals(r22) == false) goto L62;
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.StoryUploadError)});
            cleanup();
            return;
        L62:
            return;
        L50:
            if (r19 == NotificationCenter.fileUploadProgressChanged) goto L52;
            return;
        L52:
            if (((String) r21[0]).equals(this.path) == false) goto L64;
            Long r17 = (Long) r21[1];
            Long r23 = (Long) r21[2];
            float r18 = Math.min(1.0f, r17.longValue() / r23.longValue());
            this.uploadProgress = r18;
            this.progress = (this.convertingProgress * 0.3f) + (r18 * 0.7f);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.uploadStoryProgress, new Object[]{this.path, Float.valueOf(this.progress)});
            return;
        }

        public boolean isCloseFriends() {
            return this.isCloseFriends;
        }

        public void start() {
            StoryEntry r0 = this.entry;
            if (r0.isEditingCover == false) goto L6;
            TLRPC.TL_inputFileStoryDocument r02 = new TLRPC.TL_inputFileStoryDocument();
            r02.doc = MessagesController.toInputDocument(this.entry.editingCoverDocument);
            sendUploadedRequest(r02);
        L17:
            StoryPrivacyBottomSheet.StoryPrivacy r03 = this.entry.privacy;
            if (r03 != null) goto L20;
        L22:
            boolean r04 = false;
        L23:
            this.isCloseFriends = r04;
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).addObserver(this, NotificationCenter.fileUploaded);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).addObserver(this, NotificationCenter.fileUploadFailed);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).addObserver(this, NotificationCenter.fileUploadProgressChanged);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).addObserver(this, NotificationCenter.filePreparingFailed);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).addObserver(this, NotificationCenter.filePreparingStarted);
            NotificationCenter.getInstance(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0)).addObserver(this, NotificationCenter.fileNewChunkAvailable);
            boolean r05 = this.entry.wouldBeVideo();
            this.isVideo = r05;
            if (r05 == false) goto L26;
            TLRPC.TL_message r5 = new TLRPC.TL_message();
            r5.id = 1;
            String r06 = StoryEntry.makeCacheFile(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0), true).getAbsolutePath();
            r5.attachPath = r06;
            this.path = r06;
            this.messageObject = new MessageObject(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0), r5, null, false, false);
            this.entry.getVideoEditedInfo(new StoriesController$UploadingStory$$ExternalSyntheticLambda7(this));
        L27:
            startForeground();
            return;
        L26:
            File r07 = StoryEntry.makeCacheFile(StoriesController.m19700$$Nest$fgetcurrentAccount(this.this$0), false);
            this.path = r07.getAbsolutePath();
            Utilities.themeQueue.postRunnable(new StoriesController$UploadingStory$$ExternalSyntheticLambda8(this, r07));
            goto L27
        L20:
            if (r03.isCloseFriends() == false) goto L22;
            r04 = true;
            goto L23
        L6:
            if (r0.isEdit == true) goto L12;
            if (r0.isRepost == false) goto L17;
            if (r0.repostMedia == null) goto L17;
        L12:
            if (r0.editedMedia == true) goto L17;
            if (r0.round != null) goto L17;
            sendUploadedRequest(null);
        }

        public void tryAgain() {
            this.failed = false;
            this.entryDestroyed = false;
            this.progress = 0.0f;
            this.uploadProgress = 0.0f;
            this.convertingProgress = 0.0f;
            if (this.path != null) goto L8;
        L5:
            start();
            return;
        L8:
            new File(this.path).delete();     // Catch: Exception -> L7
            this.path = null;     // Catch: Exception -> L7
            goto L5
        }
    }

    /* renamed from: $r8$lambda$-Epwi9ioz3XePFuBETyXHYtHooY, reason: not valid java name */
    public static /* synthetic */ void m19682$r8$lambda$Epwi9ioz3XePFuBETyXHYtHooY(StoriesController r0, boolean r1, TL_stories.TL_stories_getAllStories r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$loadFromServer$8(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$08BSPZWUbMbdCx1Bm883tfSzyXk(StoriesController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadBlocklist$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$0alT7ok9HDD5gkxo3MxrVStVv4E(StoriesController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$deleteStories$14(r1, r2);
    }

    /* renamed from: $r8$lambda$1XZ5eKuEet5exAusr8FwAjM1-3M, reason: not valid java name */
    public static /* synthetic */ int m19683$r8$lambda$1XZ5eKuEet5exAusr8FwAjM13M(TL_stories.StoryItem r0) {
        return r0.date;
    }

    /* renamed from: $r8$lambda$3-p1BTrOnH_JNbNGahI9jdTopaM, reason: not valid java name */
    public static /* synthetic */ void m19684$r8$lambda$3p1BTrOnH_JNbNGahI9jdTopaM(StoriesController r0, int r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$5K2JLy0hG6eCW4OXirUJjmxFt-A, reason: not valid java name */
    public static /* synthetic */ void m19685$r8$lambda$5K2JLy0hG6eCW4OXirUJjmxFtA(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$5s6kDtF3MTfRqkYKGGZ_hCj2KGs(StoriesController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkStoryLimit$28(r1, r2);
    }

    /* renamed from: $r8$lambda$7o7Nu_V2cGPqy-qCZ2wvVTQMxr8, reason: not valid java name */
    public static /* synthetic */ void m19686$r8$lambda$7o7Nu_V2cGPqyqCZ2wvVTQMxr8(StoriesController r0, TLObject r1) {
        r0.lambda$loadBlocklist$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$8k5p4yxKBY6m_lK0S44v9dBPlzs(StoriesController r0, long r1, int r3) {
        r0.lambda$markStoriesAsReadFromServer$17(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$B__RWNSG6JLDKd9PfMqAGYg4tdE(StoriesController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadAllStoriesForDialog$12(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$D4kefphCsB8Z3cr0rqyRBF8ht1o(TLObject r4, int r5, Utilities.Callback r6) {
        if ((r4 instanceof TL_stories.TL_albums) == false) goto L10;
        ArrayList<TL_stories.TL_storyAlbum> r42 = ((TL_stories.TL_albums) r4).albums;
        int r0 = r42.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L10;
        TL_stories.TL_storyAlbum r2 = r42.get(r1);
        r1 = r1 + 1;
        TL_stories.TL_storyAlbum r22 = r2;
        if (r22.album_id != r5) goto L5;
        r6.run(r22);
        return;
    L10:
        r6.run(null);
    }

    public static /* synthetic */ void $r8$lambda$F0H_U8jWVCUqF3PJrEnhQ2WujlM(StoriesController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadStoriesRead$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FDReLXvkuVMpZP1ESK9p3bSxCTk() {
    }

    /* renamed from: $r8$lambda$G2O6l9H-r4Go5O9xxhY9RL6YO_0, reason: not valid java name */
    public static /* synthetic */ void m19687$r8$lambda$G2O6l9Hr4Go5O9xxhY9RL6YO_0(Runnable r0) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$G4dzkWYU4PWFP_XrV-PQtMJNBPo, reason: not valid java name */
    public static /* synthetic */ void m19688$r8$lambda$G4dzkWYU4PWFP_XrVPQtMJNBPo(StoriesController r0, boolean r1, long r2, Utilities.Callback r4, Theme.ResourcesProvider r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$canSendStoryFor$34(r1, r2, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$Hm5GPhEUlFuv5gQEaY200LPxOhY(StoriesController r0, long r1, TL_stories.TL_updateStory r3, TLRPC.User r4) {
        r0.lambda$processUpdate$10(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$JCmocK-eeq3A6THwQA65rn92ahg, reason: not valid java name */
    public static /* synthetic */ void m19689$r8$lambda$JCmocKeeq3A6THwQA65rn92ahg(StoriesController r0, long r1, TLObject r3) {
        r0.lambda$loadAllStoriesForDialog$11(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$JS7TGB1Kw2y81SB0ed1kqe1jNKY(StoriesController r0) {
        r0.lambda$loadStoriesRead$3();
    }

    public static /* synthetic */ void $r8$lambda$NCyR60WygK6lNEk99vHyDETVG4U(StoriesController r0, TL_stories.TL_stories_allStories r1) {
        r0.lambda$loadStories$5(r1);
    }

    /* renamed from: $r8$lambda$OLCiP3VXd9goUoP_-bdddvmdojc, reason: not valid java name */
    public static /* synthetic */ void m19690$r8$lambda$OLCiP3VXd9goUoP_bdddvmdojc(StoriesController r0, int r1, String r2, long r3, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$checkUnsupportedStory$38(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$Ocr8ImeJ81BiRms34CtBrXEHODo(StoriesController r0, Utilities.Callback r1, long r2, TL_stories.TL_premium_boostsStatus r4, ChannelBoostsController.CanApplyBoost r5) {
        r0.lambda$canSendStoryFor$31(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$OgNuqxwD50h7v9qbUXAuJyCA7xw(StoriesController r0, boolean r1, TL_stories.TL_stories_getAllStories r2, TLObject r3, boolean r4) {
        r0.lambda$loadFromServer$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Owir6VpucWpW_OEQO03M6UcNEes(Runnable r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda41(r0));
    }

    public static /* synthetic */ void $r8$lambda$PPqUCPmEc2t9yD1zd4q6Hoahwgk(StoriesController r0, Utilities.Callback r1, MessagesController r2, long r3, TL_stories.TL_premium_boostsStatus r5) {
        r0.lambda$canSendStoryFor$32(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$VB7DWfLVnc07NV5ZJqSDLs1afrU(StoriesController r0, LongSparseIntArray r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$Yv74YCSPWPBJ8yZk_3-h0opLQF4, reason: not valid java name */
    public static /* synthetic */ int m19691$r8$lambda$Yv74YCSPWPBJ8yZk_3h0opLQF4(StoriesController r0, TL_stories.PeerStories r1, TL_stories.PeerStories r2) {
        return r0.lambda$new$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Z08eppSsM_W6suoD9FF3t5wlXkg(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$b7OkWgFOuLvuxCAhP7cd-mXUtw8, reason: not valid java name */
    public static /* synthetic */ void m19692$r8$lambda$b7OkWgFOuLvuxCAhP7cdmXUtw8(Utilities.Callback r0, TLRPC.TL_error r1) {
        if (r0 == null) goto L8;
        if (r1 != null) goto L5;
        boolean r12 = true;
    L6:
        r0.run(Boolean.valueOf(r12));
        return;
    L5:
        r12 = false;
        goto L6
    }

    /* renamed from: $r8$lambda$jkBQ9GJj-Mr9NIG_lLFTRHIJK_I, reason: not valid java name */
    public static /* synthetic */ void m19693$r8$lambda$jkBQ9GJjMr9NIG_lLFTRHIJK_I(StoriesController r0, long r1) {
        r0.lambda$canSendStoryFor$30(r1);
    }

    /* renamed from: $r8$lambda$kFHmdF96mXj7-aTT4RVP3dixjJo, reason: not valid java name */
    public static /* synthetic */ void m19694$r8$lambda$kFHmdF96mXj7aTT4RVP3dixjJo(StoriesController r0, TLObject r1, int r2, String r3, long r4) {
        r0.lambda$checkUnsupportedStory$37(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$lkWkL8h-apZIHHEiTe46xgTpAK8, reason: not valid java name */
    public static /* synthetic */ void m19695$r8$lambda$lkWkL8hapZIHHEiTe46xgTpAK8(int r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda34(r2, r0, r1));
    }

    /* renamed from: $r8$lambda$lxF5jb-EjuvpZn999N_LW_o2WIQ, reason: not valid java name */
    public static /* synthetic */ void m19696$r8$lambda$lxF5jbEjuvpZn999N_LW_o2WIQ(StoriesController r0, TLObject r1) {
        r0.lambda$loadSendAs$35(r1);
    }

    public static /* synthetic */ void $r8$lambda$pfzLklN9GYMeKn17Ir14nQvixDU(StoriesController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$deleteStory$13(r1, r2);
    }

    /* renamed from: $r8$lambda$rAMIYNKIaFpg0hdZbhj3hrU0c-A, reason: not valid java name */
    public static /* synthetic */ void m19697$r8$lambda$rAMIYNKIaFpg0hdZbhj3hrU0cA(Utilities.Callback r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda30(r0, r2));
    }

    public static /* synthetic */ void $r8$lambda$tUVfSdFGz8AzyoBWho26EkVVd7U(StoriesController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkStoryLimit$29(r1, r2);
    }

    /* renamed from: $r8$lambda$trdgmIXA0feSLBYcFeHbyr-RTmU, reason: not valid java name */
    public static /* synthetic */ void m19698$r8$lambda$trdgmIXA0feSLBYcFeHbyrRTmU(StoriesController r0, TLRPC.TL_error r1, boolean r2, long r3, Utilities.Callback r5, Theme.ResourcesProvider r6) {
        r0.lambda$canSendStoryFor$33(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$vIUaSQGpIHzAQe8o0Y_zMeS1blY(StoriesController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadSendAs$36(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vjQ5tOourCa7hLmJTYIqzyFPNX0(StoriesController r0, long r1, boolean r3, TL_stories.PeerStories r4, long r5, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$loadSkippedStories$19(r1, r3, r4, r5, r7, r8);
    }

    /* renamed from: $r8$lambda$yfMZw-5CjPdbbUAyNkPP80Ux2R4, reason: not valid java name */
    public static /* synthetic */ void m19699$r8$lambda$yfMZw5CjPdbbUAyNkPP80Ux2R4(StoriesController r0, long r1, boolean r3, TL_stories.PeerStories r4, long r5, TLObject r7) {
        r0.lambda$loadSkippedStories$18(r1, r3, r4, r5, r7);
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19700$$Nest$fgetcurrentAccount(StoriesController r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgeteditingStories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m19701$$Nest$fgeteditingStories(StoriesController r0) {
        return r0.editingStories;
    }

    /* renamed from: -$$Nest$fgetuploadingAndEditingStories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m19702$$Nest$fgetuploadingAndEditingStories(StoriesController r0) {
        return r0.uploadingAndEditingStories;
    }

    /* renamed from: -$$Nest$fgetuploadingStoriesByDialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m19703$$Nest$fgetuploadingStoriesByDialogId(StoriesController r0) {
        return r0.uploadingStoriesByDialogId;
    }

    /* renamed from: -$$Nest$mgetStoriesList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesList m19704$$Nest$mgetStoriesList(StoriesController r0, long r1, int r3, int r4, boolean r5) {
        return r0.getStoriesList(r1, r3, r4, r5);
    }

    /* renamed from: -$$Nest$mgetStoriesList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesList m19705$$Nest$mgetStoriesList(StoriesController r0, long r1, int r3, boolean r4) {
        return r0.getStoriesList(r1, r3, r4);
    }

    /* renamed from: -$$Nest$smstoryItemIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m19706$$Nest$smstoryItemIds(List r0) {
        return storyItemIds(r0);
    }

    /* renamed from: -$$Nest$smstoryItemMessageIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m19707$$Nest$smstoryItemMessageIds(List r0) {
        return storyItemMessageIds(r0);
    }

    static {
        storiesComparator = Comparator.comparingInt(new StoriesController$$ExternalSyntheticLambda3());
    }

    public StoriesController(int r5) {
        this.uploadingStoriesByDialogId = new LongSparseArray();
        this.uploadingAndEditingStories = new LongSparseArray();
        this.uploadedStories = 0;
        this.editingStories = new LongSparseArray();
        this.dialogIdToMaxReadId = new LongSparseIntArray();
        this.dialogListStories = new ArrayList();
        this.hiddenListStories = new ArrayList();
        this.allStoriesMap = new LongSparseArray();
        this.loadingDialogsStories = new LongSparseIntArray();
        this.pollingViewsForSelfStoriesRequester = new LongSparseArray();
        this.state = _UrlKt.FRAGMENT_ENCODE_SET;
        this.selfViewsModel = new LongSparseArray();
        this.hasMoreHidden = true;
        this.firstLoad = true;
        this.allStoriesLoading = new HashSet();
        this.loadingAllStories = new HashSet();
        this.resolvedStories = new LongSparseArray();
        this.storiesCollections = new LongSparseArray();
        this.storiesLists = new HashMap[5];
        this.storiesAlbumsLists = new HashMap();
        this.attachedSearchLists = new ArrayList();
        this.peerStoriesComparator = new StoriesController$$ExternalSyntheticLambda0(this);
        this.blocklist = new HashSet();
        this.blockedOverride = new LongSparseArray();
        this.blocklistFull = false;
        this.blocklistLoadingReset = false;
        this.blocklistLoading = false;
        this.lastBlocklistRequested = 0;
        ArrayList<TLRPC.InputPeer> r2 = new ArrayList();
        this.sendAs = r2;
        r2.add(new TLRPC.TL_inputPeerSelf());
        this.loadingSendAs = false;
        this.loadedSendAs = false;
        this.requestingUnsupportedStories = new HashSet();
        this.currentAccount = r5;
        this.storiesStorage = new StoriesStorage(r5);
        SharedPreferences r22 = MessagesController.getInstance(r5).getMainSettings();
        this.mainSettings = r22;
        this.state = r22.getString("last_stories_state", _UrlKt.FRAGMENT_ENCODE_SET);
        this.stateHidden = this.mainSettings.getString("last_stories_state_hidden", _UrlKt.FRAGMENT_ENCODE_SET);
        this.totalStoriesCountHidden = this.mainSettings.getInt("total_stores_hidden", 0);
        this.totalStoriesCount = this.mainSettings.getInt("total_stores", 0);
        this.storiesReadLoaded = this.mainSettings.getBoolean("read_loaded", false);
        this.unsupportedStoriesChecked = new HashSet(this.mainSettings.getStringSet("unsupported_stories_checked", new HashSet()));
        this.stealthMode = readStealthMode(this.mainSettings.getString("stories_stealth_mode", null));
        this.storiesStorage.getMaxReadIds(new StoriesController$$ExternalSyntheticLambda1(this));
        this.sortStoriesRunnable = new StoriesController$$ExternalSyntheticLambda2(this, r5);
        this.draftsController = new DraftsController(r5);
    }

    public static boolean addOrRemoveStoryItemAlbum(TL_stories.StoryItem r2, int r3, boolean r4) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.albums == null) goto L8;
        HashSet r0 = new HashSet(r2.albums);
    L9:
        Integer r32 = Integer.valueOf(r3);
        if (r4 == false) goto L12;
        boolean r33 = r0.remove(r32);
    L14:
        if (r0.isEmpty() == true) goto L16;
        ArrayList<Integer> r42 = new ArrayList(r0);
    L17:
        r2.albums = r42;
        return r33;
    L16:
        r42 = null;
        goto L17
    L12:
        r33 = r0.add(r32);
        goto L14
    L8:
        r0 = new HashSet();
        goto L9
    }

    private void addUploadingStoryToList(long r2, UploadingStory r4, LongSparseArray<ArrayList<UploadingStory>> r5, boolean r6) {
        ArrayList<UploadingStory> r0 = r5.get(r2);
        if (r6 == false) goto L8;
        if (r0 != null) goto L6;
    L7:
        this.uploadedStories = 0;
        goto L8
    L6:
        if (r0.isEmpty() == true) goto L7;
    L8:
        if (r0 != null) goto L10;
        r0 = new ArrayList();
        r5.put(r2, r0);
    L10:
        r0.add(r4);
    }

    private void addUserToHiddenList(TL_stories.PeerStories r7) {
        long r0 = DialogObject.getPeerDialogId(r7.peer);
        if (r0 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L5;
        return;
    L5:
        int r2 = 0;
        boolean r3 = false;
    L7:
        if (r2 >= this.hiddenListStories.size()) goto L12;
        if (DialogObject.getPeerDialogId(this.hiddenListStories.get(r2).peer) != r0) goto L11;
        r3 = true;
    L11:
        r2 = r2 + 1;
        goto L7
    L12:
        if (r3 == true) goto L14;
        this.hiddenListStories.add(r7);
    L14:
        MessagesController.getInstance(this.currentAccount).checkArchiveFolder();
    }

    private void applyNewStories(TL_stories.PeerStories r5) {
        long r0 = DialogObject.getPeerDialogId(r5.peer);
        putToAllStories(r0, r5);
        if (r0 == UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L9;
        TLRPC.User r2 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r0));
        applyToList(r5);
        if (r2 == null) goto L9;
        if (r2.stories_hidden == true) goto L9;
        preloadUserStories(r5);
    L9:
        FileLog.d("StoriesController applyNewStories " + r0);
        updateStoriesInLists(r0, r5.stories);
    }

    public static TL_stories.StoryItem applyStoryUpdate(TL_stories.StoryItem r1, TL_stories.StoryItem r2) {
        if (r2 != null) goto L4;
        return r1;
    L4:
        if (r1 != null) goto L7;
    L18:
        return r2;
    L7:
        if (r2.min == false) goto L18;
        r1.pinned = r2.pinned;
        r1.isPublic = r2.isPublic;
        r1.close_friends = r2.close_friends;
        int r0 = r2.date;
        if (r0 == 0) goto L11;
        r1.date = r0;
    L11:
        int r02 = r2.expire_date;
        if (r02 == 0) goto L14;
        r1.expire_date = r02;
    L14:
        r1.caption = r2.caption;
        r1.entities = r2.entities;
        TLRPC.MessageMedia r22 = r2.media;
        if (r22 == null) goto L17;
        r1.media = r22;
    L17:
        return r1;
    }

    private void applyToList(TL_stories.PeerStories r12) {
        long r0 = DialogObject.getPeerDialogId(r12.peer);
        int r3 = this.currentAccount;
        TLRPC.User r4 = null;
        if (r0 <= 0) goto L9;
        TLRPC.User r2 = MessagesController.getInstance(r3).getUser(Long.valueOf(r0));
        if (r2 != null) goto L8;
        FileLog.d("StoriesController can't apply story user == null");
        return;
    L8:
        r4 = r2;
        TLRPC.Chat r22 = null;
    L13:
        int r5 = 0;
    L14:
        boolean r7 = true;
        if (r5 >= this.dialogListStories.size()) goto L20;
        if (DialogObject.getPeerDialogId(this.dialogListStories.get(r5).peer) == r0) goto L18;
        r5 = r5 + 1;
        goto L14
    L18:
        this.dialogListStories.remove(r5);
        boolean r52 = true;
    L21:
        int r6 = 0;
    L23:
        if (r6 >= this.hiddenListStories.size()) goto L28;
        if (DialogObject.getPeerDialogId(this.hiddenListStories.get(r6).peer) == r0) goto L26;
        r6 = r6 + 1;
        goto L23
    L26:
        this.hiddenListStories.remove(r6);
        r52 = true;
    L28:
        if (r4 != null) goto L30;
    L31:
        if (r22 != null) goto L33;
    L35:
        r7 = false;
    L37:
        if (BuildVars.LOGS_ENABLED == false) goto L39;
        FileLog.d("StoriesController move user stories to first hidden=" + r7 + " did=" + r0);
    L39:
        if (r7 == false) goto L41;
        this.hiddenListStories.add(0, r12);
    L42:
        if (r52 == true) goto L44;
        loadAllStoriesForDialog(r0);
    L44:
        MessagesController.getInstance(this.currentAccount).checkArchiveFolder();
        return;
    L41:
        this.dialogListStories.add(0, r12);
        goto L42
    L33:
        if (r22.stories_hidden == false) goto L35;
    L30:
        if (r4.stories_hidden == true) goto L37;
    L20:
        r52 = false;
        goto L21
    L9:
        r22 = MessagesController.getInstance(r3).getChat(Long.valueOf(-r0));
        if (r22 != null) goto L13;
        FileLog.d("StoriesController can't apply story chat == null");
    }

    private void checkExpireStories(ArrayList<TL_stories.PeerStories> r11) {
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= r11.size()) goto L18;
        TL_stories.PeerStories r3 = r11.get(r1);
        long r4 = DialogObject.getPeerDialogId(r3.peer);
        int r6 = 0;
    L7:
        if (r6 >= r3.stories.size()) goto L13;
        if (StoriesUtilities.isExpired(this.currentAccount, r3.stories.get(r6)) == false) goto L11;
        r3.stories.remove(r6);
        r6 = r6 - 1;
    L11:
        r6 = r6 + 1;
        goto L7
    L13:
        if (r3.stories.isEmpty() == false) goto L17;
        if (hasUploadingStories(r4) == true) goto L17;
        this.allStoriesMap.remove(r4);
        r11.remove(r3);
        r2 = true;
    L17:
        r1 = r1 + 1;
        goto L4
    L18:
        if (r2 == false) goto L28;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        return;
    }

    private void checkExpireStories(TL_stories.PeerStories r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.stories == null) goto L19;
        int r0 = 0;
    L8:
        if (r0 >= r5.stories.size()) goto L13;
        if (StoriesUtilities.isExpired(this.currentAccount, r5.stories.get(r0)) == false) goto L12;
        r5.stories.remove(r0);
        r0 = r0 - 1;
    L12:
        r0 = r0 + 1;
        goto L8
    L13:
        r5.checkedExpired = true;
        return;
    }

    private void fixDeletedAndNonContactsStories(ArrayList<TL_stories.PeerStories> r10) {
        int r1 = 0;
    L4:
        if (r1 >= r10.size()) goto L27;
        TL_stories.PeerStories r2 = r10.get(r1);
        long r3 = DialogObject.getPeerDialogId(r2.peer);
        if (r3 <= 0) goto L12;
        TLRPC.User r5 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r3));
        if (r5 == null) goto L12;
        if (isContactOrService(r5) == true) goto L12;
        r10.remove(r1);
        r1 = r1 - 1;
        boolean r52 = true;
    L13:
        int r7 = 0;
    L15:
        if (r7 >= r2.stories.size()) goto L20;
        if ((r2.stories.get(r7) instanceof TL_stories.TL_storyItemDeleted) == false) goto L19;
        r2.stories.remove(r7);
        r7 = r7 - 1;
    L19:
        r7 = r7 + 1;
        goto L15
    L20:
        if (r52 == true) goto L26;
        if (r2.stories.isEmpty() == false) goto L26;
        if (hasUploadingStories(r3) == true) goto L26;
        r10.remove(r1);
        r1 = r1 - 1;
    L26:
        r1 = r1 + 1;
    L12:
        r52 = false;
        goto L13
    }

    private long getSelfUserId() {
        return UserConfig.getInstance(this.currentAccount).getClientUserId();
    }

    private StoriesList getStoriesList(long r11, int r13, int r14, boolean r15) {
        if (r13 != 0) goto L13;
        if (r14 <= 0) goto L13;
        HashMap<Integer, StoriesList> r1 = this.storiesAlbumsLists.get(Long.valueOf(r11));
        if (r1 != null) goto L7;
        r1 = new HashMap();
        this.storiesAlbumsLists.put(Long.valueOf(r11), r1);
    L7:
        HashMap<Integer, StoriesList> r8 = r1;
        StoriesList r12 = r8.get(Integer.valueOf(r14));
        if (r12 != null) goto L12;
        if (r15 == false) goto L12;
        StoriesList r16 = new StoriesList(this.currentAccount, r11, r13, r14, new StoriesController$$ExternalSyntheticLambda11(this), null);
        r8.put(Integer.valueOf(r14), r16);
        return r16;
    L12:
        return r12;
    L13:
        HashMap<Long, StoriesList>[] r17 = this.storiesLists;
        if (r17[r13] != null) goto L16;
        r17[r13] = new HashMap();
    L16:
        StoriesList r18 = this.storiesLists[r13].get(Long.valueOf(r11));
        if (r18 != null) goto L25;
        if (r15 == false) goto L25;
        HashMap<Long, StoriesList>[] r19 = this.storiesLists;
        if (r13 != 4) goto L23;
        HashMap<Long, StoriesList> r110 = r19[r13];
        Long r82 = Long.valueOf(r11);
        BotPreviewsList r2 = new BotPreviewsList(this.currentAccount, r11, null, new StoriesController$$ExternalSyntheticLambda11(this));
        r110.put(r82, r2);
        return r2;
    L23:
        HashMap<Long, StoriesList> r83 = r19[r13];
        Long r9 = Long.valueOf(r11);
        StoriesList r111 = new StoriesList(this.currentAccount, r11, r13, r14, new StoriesController$$ExternalSyntheticLambda11(this), null);
        r83.put(r9, r111);
        return r111;
    L25:
        return r18;
    }

    private StoriesList getStoriesList(long r7, int r9, boolean r10) {
        return getStoriesList(r7, r9, -1, r10);
    }

    private boolean isContactOrService(TLRPC.User r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.contact == false) goto L6;
        return true;
    L6:
        if (r3.id != MessagesController.getInstance(this.currentAccount).storiesChangelogUserId) goto L12;
        return true;
    L12:
        return false;
    }

    private boolean isPremium(long r1) {
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1));
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.premium;
    }

    private /* synthetic */ void lambda$canSendStoryFor$30(long r2) {
        BaseFragment r1 = StatisticActivity.create(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r2)));
        BaseFragment r22 = LaunchActivity.getLastFragment();
        if (r22 != null) goto L5;
        return;
    L5:
        if (StoryRecorder.isVisible() == false) goto L8;
        BaseFragment.BottomSheetParams r3 = new BaseFragment.BottomSheetParams();
        r3.transitionFromLeft = true;
        r22.showAsSheet(r1, r3);
        return;
    L8:
        r22.presentFragment(r1);
    }

    private /* synthetic */ void lambda$canSendStoryFor$31(Utilities.Callback r7, long r8, TL_stories.TL_premium_boostsStatus r10, ChannelBoostsController.CanApplyBoost r11) {
        if (r11 != null) goto L5;
        r7.run(Boolean.FALSE);
        return;
    L5:
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (canPostStories(r8) == false) goto L9;
        StoriesController$$ExternalSyntheticLambda40 r1 = new StoriesController$$ExternalSyntheticLambda40(this, r8);
    L10:
        LimitReachedBottomSheet.openBoostsForPostingStories(r0, r8, r11, r10, r1);
        r7.run(Boolean.FALSE);
        return;
    L9:
        r1 = null;
        goto L10
    }

    private /* synthetic */ void lambda$canSendStoryFor$32(Utilities.Callback r7, MessagesController r8, long r9, TL_stories.TL_premium_boostsStatus r11) {
        if (r11 != null) goto L5;
        r7.run(Boolean.FALSE);
        return;
    L5:
        r8.getBoostsController().userCanBoostChannel(r9, r11, new StoriesController$$ExternalSyntheticLambda38(this, r7, r9, r11));
        r7.run(Boolean.FALSE);
    }

    private /* synthetic */ void lambda$canSendStoryFor$33(TLRPC.TL_error r7, boolean r8, long r9, Utilities.Callback r11, Theme.ResourcesProvider r12) {
        if (r7 != null) goto L4;
        r11.run(Boolean.TRUE);
        return;
    L4:
        if (r7.text.contains("BOOSTS_REQUIRED") == false) goto L11;
        if (r8 == false) goto L8;
        MessagesController r3 = MessagesController.getInstance(this.currentAccount);
        r3.getBoostsController().getBoostsStats(r9, new StoriesController$$ExternalSyntheticLambda31(this, r11, r3, r9));
        return;
    L8:
        r11.run(Boolean.FALSE);
        return;
    L11:
        if (r7.text.startsWith("STORY_LIVE_ALREADY_") == false) goto L19;
        BaseFragment r6 = LaunchActivity.getLastFragment();
        if (r8 == false) goto L16;
        if (r6 == null) goto L16;
        new AlertDialog.Builder(r6.getContext(), r12).setTitle(LocaleController.getString(R.string.LiveStoryAlreadyStreamingTitle)).setMessage(LocaleController.getString(R.string.LiveStoryAlreadyStreaming)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
    L16:
        r11.run(Boolean.FALSE);
        return;
    L19:
        if (r7.text.equalsIgnoreCase(MediaError.ERROR_REASON_PREMIUM_ACCOUNT_REQUIRED) == false) goto L26;
        BaseFragment r62 = LaunchActivity.getLastFragment();
        if (r8 == false) goto L24;
        if (r62 == null) goto L24;
        r62.showDialog(new PremiumFeatureBottomSheet(r62, 14, true));
    L24:
        r11.run(Boolean.FALSE);
        return;
    L26:
        BulletinFactory r63 = BulletinFactory.global();
        if (r63 == null) goto L29;
        r63.showForError(r7);
    L29:
        r11.run(Boolean.FALSE);
    }

    private /* synthetic */ void lambda$canSendStoryFor$34(boolean r4, long r5, Utilities.Callback r7, Theme.ResourcesProvider r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda29(this, r10, r4, r5, r7, r8));
    }

    private /* synthetic */ void lambda$checkStoryLimit$28(TLObject r6, TLRPC.TL_error r7) {
        this.storyLimitFetched = true;
        if ((r6 instanceof TLRPC.TL_boolTrue) == false) goto L7;
        this.storyLimitCached = null;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesLimitUpdate, new Object[0]);
        return;
    L7:
        if ((r6 instanceof TL_stories.canSendStoryCount) == false) goto L10;
        this.storyLimitCached = new StoryLimit(1, ((TL_stories.canSendStoryCount) r6).count_remains, -1);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesLimitUpdate, new Object[0]);
        return;
    L10:
        checkStoryError(r7);
    }

    private /* synthetic */ void lambda$checkStoryLimit$29(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda18(this, r2, r3));
    }

    private /* synthetic */ void lambda$checkUnsupportedStory$37(TLObject r4, int r5, String r6, long r7) {
        if (r4 == null) goto L10;
        TL_stories.TL_stories_stories r42 = (TL_stories.TL_stories_stories) r4;
        int r2 = 0;
        MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r42.chats, false);
    L5:
        if (r2 >= r42.stories.size()) goto L10;
        if (r42.stories.get(r2).id == r5) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        TL_stories.StoryItem r43 = r42.stories.get(r2);
    L11:
        this.requestingUnsupportedStories.remove(r6);
        if (r43 == null) goto L15;
        r43.dialogId = r7;
        TL_stories.TL_updateStory r52 = new TL_stories.TL_updateStory();
        r52.peer = MessagesController.getInstance(this.currentAccount).getPeer(r7);
        r52.story = r43;
        processUpdate(r52);
        return;
    L15:
        Iterator<String> r44 = this.unsupportedStoriesChecked.iterator();
    L17:
        if (r44.hasNext() == false) goto L21;
        String r0 = r44.next();
        if (r0.endsWith(":" + r7 + ":" + r5) == false) goto L17;
        this.unsupportedStoriesChecked.remove(r0);
    L21:
        this.unsupportedStoriesChecked.add(r6);
        this.mainSettings.edit().putStringSet("unsupported_stories_checked", this.unsupportedStoriesChecked).apply();
        return;
    L10:
        r43 = null;
        goto L11
    }

    private /* synthetic */ void lambda$checkUnsupportedStory$38(int r3, String r4, long r5, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda37(this, r7, r3, r4, r5));
    }

    private /* synthetic */ void lambda$deleteStories$14(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda6(this));
    }

    private /* synthetic */ void lambda$deleteStory$13(TLObject r1, TLRPC.TL_error r2) {
        if (r2 != null) goto L5;
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda6(this));
        return;
    }

    private /* synthetic */ void lambda$loadAllStoriesForDialog$11(long r7, TLObject r9) {
        this.allStoriesLoading.remove(Long.valueOf(r7));
        if (r9 != null) goto L5;
        return;
    L5:
        TL_stories.TL_stories_peerStories r92 = (TL_stories.TL_stories_peerStories) r9;
        MessagesController.getInstance(this.currentAccount).putUsers(r92.users, false);
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r7));
        TL_stories.PeerStories r1 = r92.stories;
        this.allStoriesMap.put(DialogObject.getPeerDialogId(r1.peer), r1);
        if (r0 != null) goto L8;
    L12:
        FileLog.d("StoriesController processAllStoriesResponse dialogId=" + r7 + " overwrite stories " + r92.stories.stories.size());
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        return;
    L8:
        if (isContactOrService(r0) == false) goto L10;
    L11:
        applyToList(r1);
        this.storiesStorage.putPeerStories(r1);
        goto L12
    L10:
        if (r0.self == false) goto L12;
        goto L11
    }

    private /* synthetic */ void lambda$loadAllStoriesForDialog$12(long r1, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda24(this, r1, r3));
    }

    private /* synthetic */ void lambda$loadBlocklist$24(TLObject r9) {
        boolean r1 = true;
        if ((r9 instanceof TLRPC.TL_contacts_blocked) == false) goto L9;
        TLRPC.TL_contacts_blocked r92 = (TLRPC.TL_contacts_blocked) r9;
        MessagesController.getInstance(this.currentAccount).putUsers(r92.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r92.chats, false);
        this.blocklist.clear();
        ArrayList<TLRPC.TL_peerBlocked> r0 = r92.blocked;
        int r3 = r0.size();
        int r4 = 0;
    L5:
        if (r4 >= r3) goto L7;
        TLRPC.TL_peerBlocked r5 = r0.get(r4);
        r4 = r4 + 1;
        this.blocklist.add(Long.valueOf(DialogObject.getPeerDialogId(r5.peer_id)));
        goto L5
    L7:
        this.blocklistCount = Math.max(this.blocklist.size(), r92.count);
        this.blocklistFull = true;
    L18:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesBlocklistUpdate, new Object[0]);
        this.blocklistLoading = false;
        this.lastBlocklistRequested = System.currentTimeMillis();
        return;
    L9:
        if ((r9 instanceof TLRPC.TL_contacts_blockedSlice) == false) goto L22;
        TLRPC.TL_contacts_blockedSlice r93 = (TLRPC.TL_contacts_blockedSlice) r9;
        MessagesController.getInstance(this.currentAccount).putUsers(r93.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r93.chats, false);
        ArrayList<TLRPC.TL_peerBlocked> r02 = r93.blocked;
        int r32 = r02.size();
        int r42 = 0;
    L11:
        if (r42 >= r32) goto L13;
        TLRPC.TL_peerBlocked r52 = r02.get(r42);
        r42 = r42 + 1;
        this.blocklist.add(Long.valueOf(DialogObject.getPeerDialogId(r52.peer_id)));
        goto L11
    L13:
        this.blocklistCount = r93.count;
        if (this.blocklist.size() >= this.blocklistCount) goto L17;
        r1 = false;
    L17:
        this.blocklistFull = r1;
        goto L18
    }

    private /* synthetic */ void lambda$loadBlocklist$25(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda25(this, r1));
    }

    private /* synthetic */ void lambda$loadFromServer$7(boolean r9, TL_stories.TL_stories_getAllStories r10, TLObject r11, boolean r12) {
        if (r9 == false) goto L5;
        this.loadingFromServerHidden = false;
    L6:
        FileLog.d("StoriesController loaded stories from server state=" + r10.state + " more=" + r10.next + "  " + r11);
        if ((r11 instanceof TL_stories.TL_stories_allStories) == false) goto L15;
        TL_stories.TL_stories_allStories r112 = (TL_stories.TL_stories_allStories) r11;
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r112.users, null, true, true);
        int r102 = r112.count;
        if (r9 == true) goto L11;
        this.totalStoriesCount = r102;
        this.hasMore = r112.has_more;
        this.state = r112.state;
        this.mainSettings.edit().putString("last_stories_state", this.state).putBoolean("last_stories_has_more", this.hasMore).putInt("total_stores", this.totalStoriesCount).apply();
    L12:
        processAllStoriesResponse(r112, r9, false, r12);
        return;
    L11:
        this.totalStoriesCountHidden = r102;
        this.hasMoreHidden = r112.has_more;
        this.stateHidden = r112.state;
        this.mainSettings.edit().putString("last_stories_state_hidden", this.stateHidden).putBoolean("last_stories_has_more_hidden", this.hasMoreHidden).putInt("total_stores_hidden", this.totalStoriesCountHidden).apply();
        goto L12
    L15:
        if ((r11 instanceof TL_stories.TL_stories_allStoriesNotModified) == false) goto L26;
        SharedPreferences r103 = this.mainSettings;
        if (r9 == true) goto L19;
        this.hasMore = r103.getBoolean("last_stories_has_more", false);
        this.state = ((TL_stories.TL_stories_allStoriesNotModified) r11).state;
        this.mainSettings.edit().putString("last_stories_state", this.state).apply();
    L20:
        if (r9 == false) goto L22;
        boolean r92 = this.hasMoreHidden;
    L23:
        if (r92 == false) goto L27;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        return;
    L27:
        return;
    L22:
        r92 = this.hasMore;
        goto L23
    L19:
        this.hasMoreHidden = r103.getBoolean("last_stories_has_more_hidden", false);
        this.stateHidden = ((TL_stories.TL_stories_allStoriesNotModified) r11).state;
        this.mainSettings.edit().putString("last_stories_state_hidden", this.stateHidden).apply();
        goto L20
    L26:
        return;
    L5:
        this.loadingFromServer = false;
        goto L6
    }

    private /* synthetic */ void lambda$loadFromServer$8(boolean r1, TL_stories.TL_stories_getAllStories r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda22(this, r1, r2, r4, r3));
    }

    private /* synthetic */ void lambda$loadSendAs$35(TLObject r6) {
        this.sendAs.clear();
        this.sendAs.add(new TLRPC.TL_inputPeerSelf());
        if ((r6 instanceof TLRPC.TL_messages_chats) == false) goto L7;
        ArrayList<TLRPC.Chat> r62 = ((TLRPC.TL_messages_chats) r6).chats;
        MessagesController.getInstance(this.currentAccount).putChats(r62, false);
        int r0 = r62.size();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L7;
        TLRPC.Chat r3 = r62.get(r2);
        r2 = r2 + 1;
        this.sendAs.add(MessagesController.getInputPeer(r3));
    L7:
        this.loadingSendAs = false;
        this.loadedSendAs = true;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesSendAsUpdate, new Object[0]);
    }

    private /* synthetic */ void lambda$loadSendAs$36(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda28(this, r1));
    }

    private /* synthetic */ void lambda$loadSkippedStories$18(long r4, boolean r6, TL_stories.PeerStories r7, long r8, TLObject r10) {
        this.loadingAllStories.remove(Long.valueOf(r4));
        if (r6 == true) goto L6;
        r7 = getStories(r8);
    L6:
        if (r7 != null) goto L9;
        return;
    L9:
        if ((r10 instanceof TL_stories.TL_stories_stories) == false) goto L23;
        TL_stories.TL_stories_stories r102 = (TL_stories.TL_stories_stories) r10;
        int r42 = 0;
    L12:
        if (r42 >= r102.stories.size()) goto L21;
        int r0 = 0;
    L15:
        if (r0 >= r7.stories.size()) goto L20;
        if (r7.stories.get(r0).id != r102.stories.get(r42).id) goto L19;
        r7.stories.set(r0, r102.stories.get(r42));
        preloadStory(r8, r102.stories.get(r42));
    L19:
        r0 = r0 + 1;
        goto L15
    L20:
        r42 = r42 + 1;
        goto L12
    L21:
        if (r6 == true) goto L23;
        this.storiesStorage.updateStories(r7);
    L23:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$loadSkippedStories$19(long r1, boolean r3, TL_stories.PeerStories r4, long r5, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda35(this, r1, r3, r4, r5, r7));
    }

    private /* synthetic */ void lambda$loadStories$5(TL_stories.TL_stories_allStories r3) {
        this.loadingFromDatabase = false;
        if (r3 == null) goto L6;
        processAllStoriesResponse(r3, false, true, false);
        loadFromServer(false);
        loadFromServer(true);
        return;
    L6:
        cleanup();
        loadStories();
    }

    private /* synthetic */ void lambda$loadStoriesRead$3() {
        this.storiesReadLoaded = true;
        this.mainSettings.edit().putBoolean("read_loaded", true).apply();
    }

    private /* synthetic */ void lambda$loadStoriesRead$4(TLObject r7, TLRPC.TL_error r8) {
        TLRPC.Updates r72 = (TLRPC.Updates) r7;
        if (r72 != null) goto L5;
        return;
    L5:
        MessagesController.getInstance(this.currentAccount).processUpdateArray(r72.updates, r72.users, r72.chats, false, r72.date);
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda15(this));
    }

    private /* synthetic */ void lambda$markStoriesAsReadFromServer$17(long r4, int r6) {
        int r0 = Math.max(this.dialogIdToMaxReadId.get(r4, 0), r6);
        this.dialogIdToMaxReadId.put(r4, r0);
        this.storiesStorage.updateMaxReadId(r4, r0);
        TL_stories.PeerStories r42 = getStories(r4);
        if (r42 != null) goto L6;
        return;
    L6:
        if (r6 <= r42.max_read_id) goto L9;
        r42.max_read_id = r6;
        Collections.sort(this.dialogListStories, this.peerStoriesComparator);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$new$1(LongSparseIntArray r1) {
        this.dialogIdToMaxReadId = r1;
    }

    private /* synthetic */ void lambda$new$2(int r2) {
        sortDialogStories(this.dialogListStories);
        sortDialogStories(this.hiddenListStories);
        NotificationCenter.getInstance(r2).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
    }

    private /* synthetic */ int lambda$new$23(TL_stories.PeerStories r11, TL_stories.PeerStories r12) {
        long r0 = DialogObject.getPeerDialogId(r11.peer);
        long r2 = DialogObject.getPeerDialogId(r12.peer);
        boolean r4 = hasUploadingStories(r0);
        boolean r5 = hasUploadingStories(r2);
        boolean r6 = hasUnreadStories(r0);
        boolean r7 = hasUnreadStories(r2);
        boolean r8 = hasLiveStory(r0);
        boolean r9 = hasLiveStory(r2);
        if (r8 != r9) goto L5;
        if (r4 != r5) goto L29;
        if (r6 != r7) goto L27;
        boolean r42 = UserObject.isService(r0);
        boolean r52 = UserObject.isService(r2);
        if (r42 != r52) goto L25;
        boolean r02 = isPremium(r0);
        boolean r10 = isPremium(r2);
        if (r02 != r10) goto L23;
        int r03 = 0;
        if (r11.stories.isEmpty() == false) goto L15;
        int r102 = 0;
    L17:
        if (r12.stories.isEmpty() == true) goto L21;
        r03 = r12.stories.get(r11.size() - 1).date;
    L21:
        return r03 - r102;
    L15:
        r102 = r11.stories.get(r10.size() - 1).date;
        goto L17
    L23:
        return (r10 ? 1 : 0) - (r02 ? 1 : 0);
    L25:
        return (r52 ? 1 : 0) - (r42 ? 1 : 0);
    L27:
        return (r7 ? 1 : 0) - (r6 ? 1 : 0);
    L29:
        return (r5 ? 1 : 0) - (r4 ? 1 : 0);
    L5:
        return (r9 ? 1 : 0) - (r8 ? 1 : 0);
    }

    private /* synthetic */ void lambda$processUpdate$10(long r12, TL_stories.TL_updateStory r14, TLRPC.User r15) {
        FileLog.d("StoriesController update stories for dialog " + r12);
        updateStoriesInLists(r12, Collections.singletonList(r14.story));
        boolean r1 = true;
        updateStoriesForFullPeer(r12, Collections.singletonList(r14.story), true);
        TL_stories.PeerStories r0 = this.allStoriesMap.get(r12);
        ArrayList r2 = new ArrayList();
        int r3 = this.totalStoriesCount;
        TL_stories.StoryItem r4 = r14.story;
        if (r0 == null) goto L54;
        if ((r4 instanceof TL_stories.TL_storyItemDeleted) == false) goto L7;
        NotificationsController.getInstance(this.currentAccount).processDeleteStory(r12, r4.id);
    L7:
        int r8 = 0;
    L9:
        if (r8 >= r0.stories.size()) goto L24;
        if (r0.stories.get(r8).id == r4.id) goto L12;
        r8 = r8 + 1;
        goto L9
    L12:
        boolean r9 = r4 instanceof TL_stories.TL_storyItemDeleted;
        ArrayList<TL_stories.StoryItem> r10 = r0.stories;
        if (r9 == false) goto L16;
        r10.remove(r8);
        FileLog.d("StoriesController remove story id=" + r4.id);
        boolean r82 = true;
    L15:
        boolean r92 = r82;
    L25:
        if (r82 == false) goto L27;
        boolean r152 = false;
    L45:
        if (r92 == true) goto L47;
        r1 = r152;
    L72:
        if (r3 == this.totalStoriesCount) goto L74;
        this.mainSettings.edit().putInt("total_stores", this.totalStoriesCount).apply();
    L74:
        fixDeletedAndNonContactsStories(this.dialogListStories);
        fixDeletedAndNonContactsStories(this.hiddenListStories);
        if (r1 == true) goto L77;
    L80:
        MessagesController.getInstance(this.currentAccount).checkArchiveFolder();
        return;
    L77:
        if ((r14.story instanceof TL_stories.TL_storyItemDeleted) == false) goto L79;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storyDeleted, new Object[]{Long.valueOf(r12), Integer.valueOf(r14.story.id)});
    L79:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        goto L80
    L47:
        if (r0.stories.isEmpty() == true) goto L49;
    L51:
        Collections.sort(r0.stories, storiesComparator);
        goto L72
    L49:
        if (hasUploadingStories(r12) == true) goto L51;
        this.dialogListStories.remove(r0);
        this.hiddenListStories.remove(r0);
        this.allStoriesMap.remove(DialogObject.getPeerDialogId(r0.peer));
        this.totalStoriesCount--;
        goto L72
    L27:
        if ((r4 instanceof TL_stories.TL_storyItemDeleted) == false) goto L31;
        FileLog.d("StoriesController can't add new story DELETED");
        return;
    L31:
        if (StoriesUtilities.isExpired(this.currentAccount, r4) == false) goto L35;
        FileLog.d("StoriesController can't add new story isExpired");
        return;
    L35:
        if (r12 <= 0) goto L43;
        if (r15 != null) goto L38;
    L41:
        FileLog.d("StoriesController can't add new story user is not contact");
        return;
    L38:
        if (r15.self == true) goto L43;
        if (isContactOrService(r15) == false) goto L41;
    L43:
        r2.add(r4);
        r0.stories.add(r4);
        FileLog.d("StoriesController add new story id=" + r4.id + " total stories count " + r0.stories.size());
        preloadStory(r12, r4);
        applyToList(r0);
        r152 = true;
        r92 = true;
        goto L45
    L16:
        TL_stories.StoryItem r93 = r10.get(r8);
        r4 = applyStoryUpdate(r93, r4);
        r2.add(r4);
        r0.stories.set(r8, r4);
        if (r4.attachPath != null) goto L20;
        r4.attachPath = r93.attachPath;
    L20:
        if (r4.firstFramePath != null) goto L22;
        r4.firstFramePath = r93.firstFramePath;
    L22:
        FileLog.d("StoriesController update story id=" + r4.id);
        r82 = true;
        r92 = false;
        goto L25
    L24:
        r82 = false;
        goto L15
    L54:
        if ((r4 instanceof TL_stories.TL_storyItemDeleted) == false) goto L58;
        FileLog.d("StoriesController can't add user " + r12 + " with new story DELETED");
        return;
    L58:
        if (StoriesUtilities.isExpired(this.currentAccount, r4) == false) goto L62;
        FileLog.d("StoriesController can't add user " + r12 + " with new story isExpired");
        return;
    L62:
        if (r12 <= 0) goto L70;
        if (r15 != null) goto L65;
    L68:
        FileLog.d("StoriesController can't add user cause is not contact");
        return;
    L65:
        if (r15.self == true) goto L70;
        if (isContactOrService(r15) == false) goto L68;
    L70:
        TL_stories.TL_peerStories r153 = new TL_stories.TL_peerStories();
        r153.peer = r14.peer;
        r153.stories.add(r14.story);
        FileLog.d("StoriesController add new user with story id=" + r14.story.id);
        applyNewStories(r153);
        this.totalStoriesCount = this.totalStoriesCount + 1;
        loadAllStoriesForDialog(r12);
        goto L72
    }

    private void loadAllStoriesForDialog(long r4) {
        if (this.allStoriesLoading.contains(Long.valueOf(r4)) == false) goto L5;
        return;
    L5:
        this.allStoriesLoading.add(Long.valueOf(r4));
        FileLog.d("StoriesController loadAllStoriesForDialog " + r4);
        TL_stories.TL_stories_getPeerStories r0 = new TL_stories.TL_stories_getPeerStories();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r4);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda14(this, r4));
    }

    private void loadFromServer(boolean r6) {
        if (r6 == true) goto L4;
    L5:
        if (r6 == true) goto L9;
        if (this.loadingFromServer == false) goto L9;
        return;
    L9:
        if (this.loadingFromDatabase == true) goto L33;
        boolean r0 = true;
        if (r6 == false) goto L14;
        this.loadingFromServerHidden = true;
    L15:
        TL_stories.TL_stories_getAllStories r1 = new TL_stories.TL_stories_getAllStories();
        if (r6 == false) goto L18;
        String r2 = this.stateHidden;
    L19:
        if (r6 == false) goto L21;
        boolean r3 = this.hasMoreHidden;
    L23:
        if (TextUtils.isEmpty(r2) == true) goto L25;
        r1.state = r2;
        r1.flags |= 1;
    L25:
        if (r3 == true) goto L27;
    L29:
        r0 = false;
    L30:
        r1.include_hidden = r6;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new StoriesController$$ExternalSyntheticLambda9(this, r6, r1, r0));
        return;
    L27:
        if (TextUtils.isEmpty(r2) == true) goto L29;
        r1.next = true;
        goto L30
    L21:
        r3 = this.hasMore;
        goto L23
    L18:
        r2 = this.state;
        goto L19
    L14:
        this.loadingFromServer = true;
        goto L15
    L33:
        return;
    L4:
        if (this.loadingFromServerHidden == false) goto L5;
    }

    private void loadStoriesRead() {
        if (this.storiesReadLoaded == false) goto L5;
        return;
    L5:
        TL_stories.TL_stories_getAllReadPeerStories r0 = new TL_stories.TL_stories_getAllReadPeerStories();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda7(this));
    }

    private void preloadStory(long r8, TL_stories.StoryItem r10) {
        if (r10.attachPath == null) goto L6;
        return;
    L6:
        if (DownloadController.getInstance(this.currentAccount).canPreloadStories() == false) goto L37;
        TLRPC.MessageMedia r0 = r10.media;
        int r6 = 0;
        if (r0 != null) goto L11;
    L13:
        boolean r02 = false;
    L14:
        r10.dialogId = r8;
        TLRPC.MessageMedia r3 = r10.media;
        if (r02 == false) goto L17;
        TLRPC.PhotoSize r03 = FileLoader.getClosestPhotoSizeWithSize(r3.getDocument().thumbs, 1000);
        FileLoader.getInstance(this.currentAccount).loadFile(r10.media.getDocument(), r10, 0, 1);
        FileLoader.getInstance(this.currentAccount).loadFile(ImageLocation.getForDocument(r03, r10.media.getDocument()), r10, "jpg", 0, 1);
    L25:
        if (r10.media_areas != null) goto L27;
        return;
    L27:
        if (r6 >= Math.min(2, r10.media_areas.size())) goto L38;
        if ((r10.media_areas.get(r6) instanceof TL_stories.TL_mediaAreaSuggestedReaction) == false) goto L31;
        ReactionImageHolder.preload(this.currentAccount, ReactionsLayoutInBubble.VisibleReaction.fromTL(((TL_stories.TL_mediaAreaSuggestedReaction) r10.media_areas.get(r6)).reaction));
    L31:
        r6 = r6 + 1;
        goto L27
    L38:
        return;
    L17:
        if (r3 != null) goto L19;
        TLRPC.Photo r04 = null;
    L20:
        if (r04 == null) goto L25;
        ArrayList<TLRPC.PhotoSize> r1 = r04.sizes;
        if (r1 == null) goto L25;
        FileLoader.getInstance(this.currentAccount).loadFile(ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r1, Integer.MAX_VALUE), r04), r10, "jpg", 0, 1);
        goto L25
    L19:
        r04 = r3.photo;
        goto L20
    L11:
        if (MessageObject.isVideoDocument(r0.getDocument()) == false) goto L13;
        r02 = true;
        goto L14
    }

    private void processAllStoriesResponse(TL_stories.TL_stories_allStories r12, boolean r13, boolean r14, boolean r15) {
        if (r15 == true) goto L7;
        if (r13 == true) goto L5;
        this.dialogListStories.clear();
        goto L7
    L5:
        this.hiddenListStories.clear();
    L7:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        StringBuilder r0 = new StringBuilder();
        int r2 = 0;
    L10:
        if (r2 >= r12.peer_stories.size()) goto L15;
        if (r0.length() == 0) goto L14;
        r0.append(", ");
    L14:
        r0.append(DialogObject.getPeerDialogId(r12.peer_stories.get(r2).peer));
        r2 = r2 + 1;
        goto L10
    L15:
        FileLog.d("StoriesController cache=" + r14 + " hidden=" + r13 + " processAllStoriesResponse {" + r0 + "}");
    L16:
        MessagesController.getInstance(this.currentAccount).putUsers(r12.users, r14);
        MessagesController.getInstance(this.currentAccount).putChats(r12.chats, r14);
        int r02 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        int r22 = 0;
    L18:
        if (r22 >= r12.peer_stories.size()) goto L66;
        TL_stories.PeerStories r3 = r12.peer_stories.get(r22);
        long r4 = DialogObject.getPeerDialogId(r3.peer);
        int r6 = 0;
    L20:
        int r7 = r3.stories.size();
        ArrayList<TL_stories.StoryItem> r8 = r3.stories;
        if (r6 >= r7) goto L33;
        TL_stories.StoryItem r72 = r8.get(r6);
        if ((r72 instanceof TL_stories.TL_storyItemDeleted) == false) goto L25;
    L30:
        NotificationsController.getInstance(this.currentAccount).processDeleteStory(r4, r72.id);
        r3.stories.remove(r6);
        r6 = r6 - 1;
    L31:
        r6 = r6 + 1;
        goto L20
    L25:
        if ((r72 instanceof TL_stories.TL_storyItem) == false) goto L31;
        if (r02 <= r72.expire_date) goto L31;
        if ((r72.media instanceof TLRPC.TL_messageMediaVideoStream) == true) goto L31;
    L33:
        if (r8.isEmpty() == true) goto L64;
        putToAllStories(r4, r3);
        int r62 = 0;
    L36:
        if (r62 >= 2) goto L48;
        if (r62 != 0) goto L39;
        ArrayList<TL_stories.PeerStories> r73 = this.hiddenListStories;
    L40:
        int r82 = 0;
    L42:
        if (r82 >= r73.size()) goto L47;
        if (DialogObject.getPeerDialogId(r73.get(r82).peer) == r4) goto L45;
        r82 = r82 + 1;
        goto L42
    L45:
        r73.remove(r82);
    L47:
        r62 = r62 + 1;
        goto L36
    L39:
        r73 = this.dialogListStories;
        goto L40
    L48:
        int r74 = this.currentAccount;
        if (r4 <= 0) goto L57;
        TLRPC.User r42 = MessagesController.getInstance(r74).getUser(Long.valueOf(r4));
        if (r42 == null) goto L65;
        if (r42.stories_hidden == false) goto L56;
        addUserToHiddenList(r3);
        goto L65
    L56:
        this.dialogListStories.add(r3);
        preloadUserStories(r3);
    L65:
        r22 = r22 + 1;
        goto L18
    L57:
        TLRPC.Chat r43 = MessagesController.getInstance(r74).getChat(Long.valueOf(-r4));
        if (r43 == null) goto L65;
        if (r43.stories_hidden == false) goto L63;
        addUserToHiddenList(r3);
        goto L65
    L63:
        this.dialogListStories.add(r3);
        preloadUserStories(r3);
        goto L65
    L64:
        this.allStoriesMap.remove(r4);
        goto L65
    L66:
        if (r14 == true) goto L68;
        this.storiesStorage.saveAllStories(r12.peer_stories, r15, r13, new StoriesController$$ExternalSyntheticLambda19());
    L68:
        sortUserStories();
    }

    private void putToAllStories(long r7, TL_stories.PeerStories r9) {
        TL_stories.PeerStories r0 = this.allStoriesMap.get(r7);
        if (r0 == null) goto L28;
        ArrayList<TL_stories.StoryItem> r1 = r0.stories;
        if (r1 == null) goto L28;
        if (r1.isEmpty() == true) goto L28;
        if (r9 == null) goto L28;
        ArrayList<TL_stories.StoryItem> r12 = r9.stories;
        if (r12 == null) goto L28;
        if (r12.isEmpty() == true) goto L28;
        int r2 = 0;
    L15:
        if (r2 >= r9.stories.size()) goto L28;
        if ((r9.stories.get(r2) instanceof TL_stories.TL_storyItemSkipped) == false) goto L27;
        int r3 = r9.stories.get(r2).id;
        int r4 = 0;
    L20:
        if (r4 >= r0.stories.size()) goto L27;
        if (r0.stories.get(r4).id != r3) goto L26;
        if ((r0.stories.get(r4) instanceof TL_stories.TL_storyItem) == false) goto L26;
        r9.stories.set(r2, r0.stories.get(r4));
    L26:
        r4 = r4 + 1;
    L27:
        r2 = r2 + 1;
    L28:
        this.allStoriesMap.put(r7, r9);
    }

    private TL_stories.TL_storiesStealthMode readStealthMode(String r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        SerializedData r0 = new SerializedData(Utilities.hexToBytes(r3));
        return TL_stories.TL_storiesStealthMode.TLdeserialize(r0, r0.readInt32(true), true);
    L8:
        th = move-exception;
        FileLog.e(th);
        return null;
    }

    private void sortDialogStories(ArrayList<TL_stories.PeerStories> r1) {
        fixDeletedAndNonContactsStories(r1);
        Collections.sort(r1, this.peerStoriesComparator);
    }

    private void sortUserStories() {
        AndroidUtilities.cancelRunOnUIThread(this.sortStoriesRunnable);
        this.sortStoriesRunnable.run();
    }

    private static String storyItemIds(List<TL_stories.StoryItem> r5) {
        if (r5 != null) goto L5;
        return "null";
    L5:
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r1 = 0;
    L15:
        if (r1 >= r5.size()) goto L12;
        if (r1 <= 0) goto L10;
        r0 = r0 + ", ";     // Catch: Exception -> L13
    L10:
        r0 = r0 + r5.get(r1).id + "@" + r5.get(r1).dialogId;     // Catch: Exception -> L13
        r1 = r1 + 1;
        goto L15
    L12:
        return r0;
    L13:
        return "err";
    }

    private static String storyItemMessageIds(List<MessageObject> r7) {
        if (r7 != null) goto L5;
        return "null";
    L5:
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r2 = 0;
    L18:
        if (r2 >= r7.size()) goto L15;
        if (r2 <= 0) goto L10;
        r1 = r1 + ", ";     // Catch: Exception -> L16
    L10:
        TL_stories.StoryItem r3 = r7.get(r2).storyItem;     // Catch: Exception -> L16
        if (r3 != null) goto L13;
        r1 = r1 + "null";     // Catch: Exception -> L16
    L14:
        r2 = r2 + 1;
        goto L18
    L13:
        r1 = r1 + r3.id + "@" + r3.dialogId;     // Catch: Exception -> L16
        goto L14
    L15:
        return r1;
    L16:
        return "err";
    }

    private void updateStoriesForFullPeer(long r6, List<TL_stories.StoryItem> r8, boolean r9) {
        int r1 = this.currentAccount;
        if (r6 <= 0) goto L13;
        TLRPC.UserFull r0 = MessagesController.getInstance(r1).getUserFull(r6);
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.stories != null) goto L12;
        if (r9 == false) goto L58;
        TL_stories.TL_peerStories r12 = new TL_stories.TL_peerStories();
        r0.stories = r12;
        r12.peer = MessagesController.getInstance(this.currentAccount).getPeer(r6);
        r0.stories.max_read_id = getMaxStoriesReadId(r6);
        goto L12
    L58:
        return;
    L12:
        TL_stories.PeerStories r5 = r0.stories;
    L22:
        int r7 = 0;
    L24:
        if (r7 >= r8.size()) goto L61;
        TL_stories.StoryItem r02 = r8.get(r7);
        int r13 = 0;
    L27:
        if (r13 >= r5.stories.size()) goto L41;
        if (r5.stories.get(r13).id == r02.id) goto L30;
        r13 = r13 + 1;
        goto L27
    L30:
        boolean r2 = r02 instanceof TL_stories.TL_storyItemDeleted;
        ArrayList<TL_stories.StoryItem> r3 = r5.stories;
        boolean r4 = true;
        if (r2 == false) goto L33;
        r3.remove(r13);
    L42:
        if (r4 == true) goto L49;
        if ((r02 instanceof TL_stories.TL_storyItemDeleted) == false) goto L46;
        FileLog.d("StoriesController story is not found, but already deleted storyId=" + r02.id);
        goto L49
    L46:
        if (r9 == false) goto L49;
        FileLog.d("StoriesController add new story for full peer storyId=" + r02.id);
        r5.stories.add(r02);
        r5.checkedExpired = false;
    L49:
        r7 = r7 + 1;
        goto L24
    L33:
        TL_stories.StoryItem r22 = r3.get(r13);
        r02 = applyStoryUpdate(r22, r02);
        r5.stories.set(r13, r02);
        if (r02.attachPath != null) goto L37;
        r02.attachPath = r22.attachPath;
    L37:
        if (r02.firstFramePath != null) goto L39;
        r02.firstFramePath = r22.firstFramePath;
    L39:
        FileLog.d("StoriesController update story for full peer storyId=" + r02.id);
        goto L42
    L41:
        r4 = false;
        goto L42
    L61:
        return;
    L13:
        TLRPC.ChatFull r03 = MessagesController.getInstance(r1).getChatFull(-r6);
        if (r03 != null) goto L17;
        return;
    L17:
        if (r03.stories != null) goto L21;
        if (r9 == false) goto L60;
        TL_stories.TL_peerStories r14 = new TL_stories.TL_peerStories();
        r03.stories = r14;
        r14.peer = MessagesController.getInstance(this.currentAccount).getPeer(r6);
        r03.stories.max_read_id = getMaxStoriesReadId(r6);
        goto L21
    L60:
        return;
    L21:
        r5 = r03.stories;
        goto L22
    }

    private void writeStealthMode(TL_stories.TL_storiesStealthMode r4) {
        SharedPreferences.Editor r3 = MessagesController.getInstance(this.currentAccount).getMainSettings().edit();
        if (r4 != null) goto L6;
        r3.remove("stories_stealth_mode").apply();
        return;
    L6:
        SerializedData r1 = new SerializedData(r4.getObjectSize());
        r4.serializeToStream(r1);
        r3.putString("stories_stealth_mode", Utilities.bytesToHex(r1.toByteArray())).apply();
    }

    public void addStoriesToAlbum(long r1, int r3, ArrayList<TL_stories.StoryItem> r4) {
        getStoryAlbumsList(r1).addStories(r3, r4);
    }

    public void addStoryToAlbum(long r3, int r5, TL_stories.StoryItem r6) {
        ArrayList<TL_stories.StoryItem> r0 = new ArrayList(1);
        r0.add(r6);
        addStoriesToAlbum(r3, r5, r0);
    }

    public void applyStoryViewsBlocked(TL_stories.StoryViewsList r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (r6.views == null) goto L17;
        int r0 = 0;
    L8:
        if (r0 >= r6.views.size()) goto L18;
        TL_stories.StoryView r1 = r6.views.get(r0);
        if (this.blockedOverride.containsKey(r1.user_id) == false) goto L12;
        this.blockedOverride.put(r1.user_id, Boolean.valueOf(r1.blocked_my_stories_from));
    L12:
        r0 = r0 + 1;
        goto L8
    L18:
        return;
    }

    public boolean canCreateNewAlbum(long r2) {
        StoriesCollections r1 = getStoryAlbumsList(r2, false);
        if (r1 != null) goto L5;
    L8:
        return false;
    L5:
        if (r1.canCreateNewAlbum() == false) goto L8;
        return true;
    }

    public boolean canDeleteStory(TL_stories.StoryItem r8) {
        if (r8 != null) goto L6;
        return false;
    L6:
        if (r8.dialogId != getSelfUserId()) goto L9;
        return false;
    L9:
        if (r8.dialogId <= 0) goto L18;
        TLRPC.User r1 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r8.dialogId));
        if (r1 == null) goto L18;
        if (r1.bot == false) goto L18;
        if (r1.bot_can_edit == false) goto L18;
        return true;
    L18:
        if (r8.dialogId >= 0) goto L40;
        TLRPC.Chat r7 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r8.dialogId));
        if (r7 != null) goto L23;
        return false;
    L23:
        if (r7.creator == false) goto L25;
        return true;
    L25:
        boolean r82 = r8.out;
        if (r82 == false) goto L34;
        TLRPC.TL_chatAdminRights r12 = r7.admin_rights;
        if (r12 == null) goto L34;
        if (r12.post_stories == false) goto L32;
    L33:
        return true;
    L32:
        if (r12.delete_stories == true) goto L33;
    L34:
        if (r82 == true) goto L40;
        TLRPC.TL_chatAdminRights r72 = r7.admin_rights;
        if (r72 == null) goto L40;
        if (r72.delete_stories == false) goto L40;
        return true;
    L40:
        return false;
    }

    public boolean canEditStories(long r3) {
        if (r3 >= 0) goto L17;
        TLRPC.Chat r2 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r3));
        if (r2 != null) goto L8;
        return false;
    L8:
        if (r2.creator == true) goto L15;
        TLRPC.TL_chatAdminRights r22 = r2.admin_rights;
        if (r22 != null) goto L12;
    L14:
        return false;
    L12:
        if (r22.edit_stories == false) goto L14;
        return true;
    L15:
        return true;
    L17:
        return false;
    }

    public boolean canEditStories(TLRPC.Chat r2) {
        if (r2 != null) goto L5;
        return false;
    L5:
        if (ChatObject.isBoostSupported(r2) == true) goto L8;
        return false;
    L8:
        if (r2.creator == true) goto L15;
        TLRPC.TL_chatAdminRights r22 = r2.admin_rights;
        if (r22 != null) goto L12;
    L14:
        return false;
    L12:
        if (r22.edit_stories == false) goto L14;
    L15:
        return true;
    }

    public boolean canEditStory(TL_stories.StoryItem r8) {
        if (r8 != null) goto L6;
        return false;
    L6:
        if (r8.dialogId != getSelfUserId()) goto L9;
        return false;
    L9:
        if (r8.dialogId <= 0) goto L18;
        TLRPC.User r1 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r8.dialogId));
        if (r1 == null) goto L18;
        if (r1.bot == false) goto L18;
        if (r1.bot_can_edit == false) goto L18;
        return true;
    L18:
        if (r8.dialogId >= 0) goto L40;
        TLRPC.Chat r7 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r8.dialogId));
        if (r7 != null) goto L23;
        return false;
    L23:
        if (r7.creator == false) goto L25;
        return true;
    L25:
        boolean r82 = r8.out;
        if (r82 == false) goto L34;
        TLRPC.TL_chatAdminRights r12 = r7.admin_rights;
        if (r12 == null) goto L34;
        if (r12.post_stories == false) goto L32;
    L33:
        return true;
    L32:
        if (r12.edit_stories == true) goto L33;
    L34:
        if (r82 == true) goto L40;
        TLRPC.TL_chatAdminRights r72 = r7.admin_rights;
        if (r72 == null) goto L40;
        if (r72.edit_stories == false) goto L40;
        return true;
    L40:
        return false;
    }

    public boolean canEditStoryAlbums(long r3) {
        if (UserConfig.getInstance(this.currentAccount).getClientUserId() != r3) goto L5;
        return true;
    L5:
        if (canEditStories(r3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean canPostStories(long r4) {
        if (r4 >= 0) goto L19;
        TLRPC.Chat r3 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r4));
        if (r3 != null) goto L7;
    L18:
        return false;
    L7:
        if (ChatObject.isBoostSupported(r3) == false) goto L18;
        if (r3.creator == true) goto L17;
        TLRPC.TL_chatAdminRights r32 = r3.admin_rights;
        if (r32 != null) goto L14;
    L16:
        return false;
    L14:
        if (r32.post_stories == false) goto L16;
    L17:
        return true;
    L19:
        if (r4 <= 0) goto L27;
        TLRPC.User r33 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r4));
        if (r33 == null) goto L27;
        if (r33.bot == false) goto L27;
        if (r33.bot_can_edit == false) goto L27;
        return true;
    L27:
        return false;
    }

    public boolean canPostStories(TLRPC.Chat r2) {
        if (r2 != null) goto L5;
        return false;
    L5:
        if (ChatObject.isBoostSupported(r2) == true) goto L8;
        return false;
    L8:
        if (r2.creator == true) goto L15;
        TLRPC.TL_chatAdminRights r22 = r2.admin_rights;
        if (r22 != null) goto L12;
    L14:
        return false;
    L12:
        if (r22.post_stories == false) goto L14;
    L15:
        return true;
    }

    public void canSendStoryFor(long r10, Utilities.Callback<Boolean> r12, boolean r13, Theme.ResourcesProvider r14) {
        TL_stories.TL_stories_canSendStory r0 = new TL_stories.TL_stories_canSendStory();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r10);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda16(this, r13, r10, r12, r14), 1024);
    }

    public void checkExpiredStories() {
        checkExpireStories(this.dialogListStories);
        checkExpireStories(this.hiddenListStories);
    }

    public void checkExpiredStories(long r6) {
        TL_stories.PeerStories r0 = getStories(r6);
        if (r0 == null) goto L23;
        int r2 = 0;
    L7:
        if (r2 >= r0.stories.size()) goto L13;
        if (StoriesUtilities.isExpired(this.currentAccount, r0.stories.get(r2)) == false) goto L11;
        r0.stories.remove(r2);
        r2 = r2 - 1;
    L11:
        r2 = r2 + 1;
        goto L7
    L13:
        if (r0.stories.isEmpty() == true) goto L15;
        return;
    L15:
        if (hasUnreadStories(r6) == true) goto L22;
        this.dialogListStories.remove(r0);
        this.hiddenListStories.remove(r0);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        return;
    L22:
        return;
    }

    public boolean checkStoryError(TLRPC.TL_error r7) {
        if (r7 == null) goto L24;
        String r1 = r7.text;
        if (r1 == null) goto L24;
        boolean r12 = r1.startsWith("STORY_SEND_FLOOD_WEEKLY_");
        String r2 = r7.text;
        long r3 = 0;
        boolean r5 = true;
        if (r12 == true) goto L30;
        boolean r13 = r2.startsWith("STORY_SEND_FLOOD_MONTHLY_");
        String r22 = r7.text;
        if (r13 == false) goto L17;
        r3 = Long.parseLong(r22.substring(25));     // Catch: Exception -> L29
    L15:
        this.storyLimitCached = new StoryLimit(3, 0, r3);
    L25:
        if (r5 == false) goto L27;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesLimitUpdate, new Object[0]);
    L27:
        return r5;
    L17:
        if (r22.equals("STORIES_TOO_MUCH") == false) goto L20;
        this.storyLimitCached = new StoryLimit(1, 0, 0);
        goto L25
    L20:
        if (r7.text.equals(MediaError.ERROR_REASON_PREMIUM_ACCOUNT_REQUIRED) == false) goto L24;
        MessagesController r72 = MessagesController.getInstance(this.currentAccount);
        if ("enabled".equals(r72.storiesPosting) == false) goto L25;
        SharedPreferences.Editor r14 = r72.getMainSettings().edit();
        r72.storiesPosting = "premium";
        r14.putString("storiesPosting", "premium").apply();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesEnabledUpdate, new Object[0]);
        goto L25
    L30:
        r3 = Long.parseLong(r2.substring(24));     // Catch: Exception -> L28
    L10:
        this.storyLimitCached = new StoryLimit(2, 0, r3);
    L24:
        r5 = false;
        goto L25
    }

    public StoryLimit checkStoryLimit() {
        boolean r0 = UserConfig.getInstance(this.currentAccount).isPremium();
        int r1 = this.currentAccount;
        if (r0 == false) goto L5;
        int r02 = MessagesController.getInstance(r1).storyExpiringLimitPremium;
    L7:
        if (getMyStoriesCount() < r02) goto L11;
        return new StoryLimit(1, 0, 0);
    L11:
        if (this.storyLimitFetched == true) goto L13;
        TL_stories.TL_stories_canSendStory r03 = new TL_stories.TL_stories_canSendStory();
        r03.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, new StoriesController$$ExternalSyntheticLambda10(this), 1024);
        return null;
    L13:
        return this.storyLimitCached;
    L5:
        r02 = MessagesController.getInstance(r1).storyExpiringLimitDefault;
        goto L7
    }

    public void checkUnsupportedStory(long r9, int r11) {
        String r5 = "229:" + r9 + ":" + r11;
        if (this.requestingUnsupportedStories.contains(r5) == false) goto L6;
        return;
    L6:
        if (this.unsupportedStoriesChecked.contains(r5) == false) goto L8;
        return;
    L8:
        this.requestingUnsupportedStories.add(r5);
        TL_stories.TL_stories_getStoriesByID r0 = new TL_stories.TL_stories_getStoriesByID();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r9);
        r0.id.add(Integer.valueOf(r11));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda33(this, r11, r5, r9));
    }

    public void cleanup() {
        this.allStoriesLoaded = false;
        this.allHiddenStoriesLoaded = false;
        this.storiesReadLoaded = false;
        this.stateHidden = _UrlKt.FRAGMENT_ENCODE_SET;
        this.state = _UrlKt.FRAGMENT_ENCODE_SET;
        this.mainSettings.edit().putBoolean("stories_loaded", false).remove("last_stories_state").putBoolean("stories_loaded_hidden", false).remove("last_stories_state_hidden").putBoolean("read_loaded", false).apply();
        DraftsController r0 = this.draftsController;
        Objects.requireNonNull(r0);
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda4(r0));
        loadStories();
        loadStoriesRead();
    }

    public void createAlbum(long r1, String r3, Utilities.Callback<StoryAlbum> r4) {
        getStoryAlbumsList(r1).createCollection(r3, r4);
    }

    public void deleteStories(long r9, ArrayList<TL_stories.StoryItem> r11) {
        if (r11 == null) goto L65;
        TL_stories.TL_stories_deleteStories r0 = new TL_stories.TL_stories_deleteStories();
        TLRPC.InputPeer r1 = MessagesController.getInstance(this.currentAccount).getInputPeer(r9);
        r0.peer = r1;
        if (r1 != null) goto L7;
        return;
    L7:
        TL_stories.PeerStories r12 = this.allStoriesMap.get(r9);
        int r3 = 0;
    L9:
        if (r3 >= r11.size()) goto L25;
        TL_stories.StoryItem r4 = r11.get(r3);
        if ((r4 instanceof TL_stories.TL_storyItemDeleted) == true) goto L24;
        if (r12 == null) goto L23;
        int r5 = 0;
    L16:
        if (r5 >= r12.stories.size()) goto L23;
        if (r12.stories.get(r5).id == r4.id) goto L19;
        r5 = r5 + 1;
        goto L16
    L19:
        r12.stories.remove(r5);
        if (r12.stories.isEmpty() == false) goto L23;
        this.allStoriesMap.remove(r9);
    L23:
        r0.id.add(Integer.valueOf(r4.id));
    L24:
        r3 = r3 + 1;
        goto L9
    L25:
        int r42 = this.currentAccount;
        if (r9 < 0) goto L32;
        TLRPC.UserFull r32 = MessagesController.getInstance(r42).getUserFull(r9);
        if (r32 == null) goto L37;
        TL_stories.PeerStories r33 = r32.stories;
        if (r33 == null) goto L37;
    L36:
        r12 = r33;
    L37:
        int r34 = 0;
    L39:
        if (r34 >= r11.size()) goto L52;
        TL_stories.StoryItem r43 = r11.get(r34);
        if ((r43 instanceof TL_stories.TL_storyItemDeleted) == true) goto L51;
        if (r12 == null) goto L51;
        int r52 = 0;
    L46:
        if (r52 >= r12.stories.size()) goto L51;
        if (r12.stories.get(r52).id == r43.id) goto L49;
        r52 = r52 + 1;
        goto L46
    L49:
        r12.stories.remove(r52);
    L51:
        r34 = r34 + 1;
        goto L39
    L52:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda26(this));
        updateDeletedStoriesInLists(r9, r11);
        this.storiesStorage.deleteStories(r9, r0.id);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        return;
    L32:
        TLRPC.ChatFull r35 = MessagesController.getInstance(r42).getChatFull(-r9);
        if (r35 == null) goto L37;
        r33 = r35.stories;
        if (r33 == null) goto L37;
    }

    public void deleteStory(long r12, TL_stories.StoryItem r14) {
        if (r14 != null) goto L4;
        return;
    L4:
        if ((r14 instanceof TL_stories.TL_storyItemDeleted) == false) goto L6;
        return;
    L6:
        int r1 = 0;
    L8:
        if (r1 >= 2) goto L47;
        TL_stories.PeerStories r4 = null;
        if (r1 != 0) goto L12;
        TLRPC.ChatFull r6 = null;
        r4 = this.allStoriesMap.get(r12);
        TLRPC.UserFull r5 = null;
    L22:
        if (r4 == null) goto L42;
        int r7 = 0;
    L25:
        if (r7 >= r4.stories.size()) goto L42;
        if (r4.stories.get(r7).id == r14.id) goto L28;
        r7 = r7 + 1;
        goto L25
    L28:
        r4.stories.remove(r7);
        if (r4.stories.size() != 0) goto L42;
        if (hasUploadingStories(r12) == true) goto L33;
        this.allStoriesMap.remove(r12);
        this.dialogListStories.remove(r4);
        this.hiddenListStories.remove(r4);
    L33:
        int r3 = this.currentAccount;
        if (r12 <= 0) goto L38;
        TLRPC.User r2 = MessagesController.getInstance(r3).getUser(Long.valueOf(r12));
        if (r2 == null) goto L42;
        r2.stories_unavailable = true;
        goto L42
    L38:
        TLRPC.Chat r22 = MessagesController.getInstance(r3).getChat(Long.valueOf(-r12));
        if (r22 == null) goto L42;
        r22.stories_unavailable = true;
    L42:
        if (r6 == null) goto L44;
        MessagesStorage.getInstance(this.currentAccount).updateChatInfo(r6, false);
    L44:
        if (r5 == null) goto L46;
        MessagesStorage.getInstance(this.currentAccount).updateUserInfo(r5, false);
    L46:
        r1 = r1 + 1;
        goto L8
    L12:
        int r62 = this.currentAccount;
        if (r12 < 0) goto L18;
        r5 = MessagesController.getInstance(r62).getUserFull(r12);
        if (r5 == null) goto L17;
        r6 = null;
        r4 = r5.stories;
        goto L22
    L17:
        r6 = null;
        goto L22
    L18:
        TLRPC.ChatFull r52 = MessagesController.getInstance(r62).getChatFull(-r12);
        if (r52 == null) goto L21;
        TL_stories.PeerStories r63 = r52.stories;
        r5 = null;
        r4 = r63;
        r6 = r52;
        goto L22
    L21:
        r6 = r52;
        r5 = null;
        goto L22
    L47:
        TL_stories.TL_stories_deleteStories r13 = new TL_stories.TL_stories_deleteStories();
        r13.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r12);
        r13.id.add(Integer.valueOf(r14.id));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r13, new StoriesController$$ExternalSyntheticLambda5(this));
        this.storiesStorage.deleteStory(r12, r14.id);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        MessagesController.getInstance(this.currentAccount).checkArchiveFolder();
        updateDeletedStoriesInLists(r12, Arrays.asList(new TL_stories.StoryItem[]{r14}));
    }

    public void destroyStoryList(StoriesList r4) {
        int r0 = r4.type;
        if (r0 == 0) goto L5;
    L13:
        HashMap<Long, StoriesList> r3 = this.storiesLists[r0];
        if (r3 == null) goto L18;
        r3.remove(Long.valueOf(r4.dialogId));
        return;
    L18:
        return;
    L5:
        if (r4.albumId <= 0) goto L13;
        HashMap<Integer, StoriesList> r02 = this.storiesAlbumsLists.get(Long.valueOf(r4.dialogId));
        if (r02 == null) goto L19;
        r02.remove(Integer.valueOf(r4.albumId));
        if (r02.isEmpty() == false) goto L17;
        this.storiesAlbumsLists.remove(Long.valueOf(r4.dialogId));
        return;
    L17:
        return;
    }

    public void fillMessagesWithStories(LongSparseArray<ArrayList<MessageObject>> r1, Runnable r2, int r3, Timer r4) {
        this.storiesStorage.fillMessagesWithStories(r1, r2, r3, r4);
    }

    public UploadingStory findEditingStory(long r2, TL_stories.StoryItem r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        HashMap<Integer, UploadingStory> r1 = this.editingStories.get(r2);
        if (r1 != null) goto L8;
    L12:
        return null;
    L8:
        if (r1.isEmpty() == true) goto L12;
        return r1.get(Integer.valueOf(r4.id));
    }

    public TL_stories.StoryItem findStory(long r1, int r3) {
        TL_stories.PeerStories r0 = this.allStoriesMap.get(r1);
        if (r0 == null) goto L12;
        int r12 = 0;
    L6:
        if (r12 >= r0.stories.size()) goto L16;
        if (r0.stories.get(r12).id == r3) goto L10;
        r12 = r12 + 1;
        goto L6
    L10:
        return r0.stories.get(r12);
    L16:
        return null;
    L12:
        return null;
    }

    public String getAlbumName(long r2, int r4) {
        StoriesCollections r1 = getStoryAlbumsList(r2, false);
        if (r1 == null) goto L8;
        StoryAlbum r12 = r1.findById(r4);
        if (r12 != null) goto L7;
        return null;
    L7:
        return r12.title;
    L8:
        return null;
    }

    public int getBlocklistCount() {
        return this.blocklistCount;
    }

    public ArrayList<TL_stories.PeerStories> getDialogListStories() {
        return this.dialogListStories;
    }

    public DraftsController getDraftsController() {
        return this.draftsController;
    }

    public UploadingStory getEditingStory(long r1) {
        HashMap<Integer, UploadingStory> r0 = this.editingStories.get(r1);
        if (r0 != null) goto L5;
    L12:
        return null;
    L5:
        if (r0.isEmpty() == true) goto L12;
        Collection<UploadingStory> r02 = r0.values();
        if (r02.isEmpty() == false) goto L11;
        return null;
    L11:
        return r02.iterator().next();
    }

    public ArrayList<TL_stories.PeerStories> getHiddenList() {
        return this.hiddenListStories;
    }

    public int getMaxStoriesReadId(long r3) {
        TL_stories.PeerStories r0 = getStories(r3);
        if (r0 != null) goto L6;
        r0 = getStoriesFromFullPeer(r3);
    L6:
        if (r0 == null) goto L10;
        return Math.max(r0.max_read_id, this.dialogIdToMaxReadId.get(r3, 0));
    L10:
        return this.dialogIdToMaxReadId.get(r3, 0);
    }

    public int getMyStoriesCount() {
        ArrayList<UploadingStory> r0 = this.uploadingAndEditingStories.get(getSelfUserId());
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        TL_stories.PeerStories r3 = getStories(getSelfUserId());
        if (r3 == null) goto L12;
        ArrayList<TL_stories.StoryItem> r32 = r3.stories;
        if (r32 != null) goto L11;
        return r02;
    L11:
        return r02 + r32.size();
    L12:
        return r02;
    L5:
        r02 = r0.size();
        goto L6
    }

    public int getSelfStoriesCount() {
        TL_stories.PeerStories r0 = this.allStoriesMap.get(UserConfig.getInstance(this.currentAccount).clientUserId);
        if (r0 == null) goto L5;
        int r02 = r0.stories.size();
    L7:
        return r02 + this.uploadingStoriesByDialogId.size();
    L5:
        r02 = 0;
        goto L7
    }

    public TL_stories.TL_storiesStealthMode getStealthMode() {
        return this.stealthMode;
    }

    public TL_stories.PeerStories getStories(long r1) {
        return this.allStoriesMap.get(r1);
    }

    public TL_stories.PeerStories getStoriesFromFullPeer(long r4) {
        int r1 = this.currentAccount;
        if (r4 <= 0) goto L15;
        TLRPC.UserFull r42 = MessagesController.getInstance(r1).getUserFull(r4);
        if (r42 == null) goto L11;
        TL_stories.PeerStories r5 = r42.stories;
        if (r5 == null) goto L11;
        if (r5.checkedExpired == true) goto L11;
        checkExpireStories(r5);
    L11:
        if (r42 != null) goto L14;
        return null;
    L14:
        return r42.stories;
    L15:
        TLRPC.ChatFull r43 = MessagesController.getInstance(r1).getChatFull(-r4);
        if (r43 == null) goto L22;
        TL_stories.PeerStories r52 = r43.stories;
        if (r52 == null) goto L22;
        if (r52.checkedExpired == true) goto L22;
        checkExpireStories(r52);
    L22:
        if (r43 != null) goto L25;
        return null;
    L25:
        return r43.stories;
    }

    public StoriesList getStoriesList(long r7, int r9) {
        return getStoriesList(r7, r9, -1, true);
    }

    public StoriesList getStoriesList(long r7, int r9, int r10) {
        return getStoriesList(r7, r9, r10, true);
    }

    public StoriesStorage getStoriesStorage() {
        return this.storiesStorage;
    }

    public StoriesCollections getStoryAlbumsList(long r2) {
        return getStoryAlbumsList(r2, true);
    }

    public StoriesCollections getStoryAlbumsList(long r7, boolean r9) {
        StoriesCollections r0 = this.storiesCollections.get(r7);
        if (r0 != null) goto L7;
        if (r9 == false) goto L8;
        LongSparseArray<StoriesCollections> r92 = this.storiesCollections;
        StoriesCollections r02 = new StoriesCollections(this, this.currentAccount, r7, null);
        r92.put(r7, r02);
        return r02;
    L8:
        return r0;
    L7:
        return r0;
    }

    public int getTotalStoriesCount(boolean r2) {
        if (r2 == false) goto L11;
        if (this.hasMoreHidden == false) goto L9;
        return Math.max(1, this.totalStoriesCountHidden);
    L9:
        return this.hiddenListStories.size();
    L11:
        if (this.hasMore == false) goto L15;
        return Math.max(1, this.totalStoriesCount);
    L15:
        return this.dialogListStories.size();
    }

    public int getUnreadState(long r2) {
        return getUnreadState(r2, 0);
    }

    public int getUnreadState(long r8, int r10) {
        if (r8 != 0) goto L5;
        return 0;
    L5:
        TL_stories.PeerStories r0 = this.allStoriesMap.get(r8);
        if (r0 != null) goto L8;
        r0 = getStoriesFromFullPeer(r8);
    L8:
        if (r0 != null) goto L11;
        return 0;
    L11:
        if (r8 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L13;
    L15:
        int r2 = Math.max(r0.max_read_id, this.dialogIdToMaxReadId.get(r8, 0));
        int r4 = 0;
        boolean r5 = false;
    L17:
        if (r4 >= r0.stories.size()) goto L34;
        if ((r0.stories.get(r4).media instanceof TLRPC.TL_messageMediaVideoStream) == true) goto L20;
        if (r10 == 0) goto L26;
        if (r0.stories.get(r4).id == r10) goto L26;
    L32:
        r4 = r4 + 1;
    L26:
        if (r0.stories.get(r4).id <= r2) goto L32;
        if (r0.stories.get(r4).close_friends == true) goto L29;
        r5 = true;
        goto L32
    L29:
        return 2;
    L20:
        return 3;
    L34:
        if (isLastUploadingFailed(r8) == false) goto L36;
        return 0;
    L36:
        if (r5 == false) goto L38;
        return 1;
    L38:
        return 0;
    L13:
        if (Utilities.isNullOrEmpty(this.uploadingStoriesByDialogId.get(r8)) == true) goto L15;
        return 1;
    }

    public ArrayList<UploadingStory> getUploadingAndEditingStories(long r1) {
        return this.uploadingAndEditingStories.get(r1);
    }

    public ArrayList<UploadingStory> getUploadingStories(long r1) {
        return this.uploadingStoriesByDialogId.get(r1);
    }

    public boolean hasHiddenStories() {
        if (this.hiddenListStories.isEmpty() == false) goto L5;
        return false;
    L5:
        if (ExteraConfig.getHideStories() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasLiveStory(long r3) {
        TL_stories.PeerStories r0 = this.allStoriesMap.get(r3);
        if (r0 != null) goto L6;
        r0 = getStoriesFromFullPeer(r3);
    L6:
        if (r0 != null) goto L8;
        return false;
    L8:
        int r32 = r0.stories.size() - 1;
    L9:
        if (r32 < 0) goto L17;
        TL_stories.StoryItem r1 = r0.stories.get(r32);
        if (r1 == null) goto L16;
        if ((r1.media instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L16;
        return true;
    L16:
        r32 = r32 - 1;
        goto L9
    L17:
        return false;
    }

    public boolean hasLoadingStories() {
        if (this.loadingDialogsStories.size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasOnlySelfStories() {
        if (ExteraConfig.getHideStories() == false) goto L6;
        return false;
    L6:
        if (hasSelfStories() == true) goto L8;
    L14:
        return false;
    L8:
        if (getDialogListStories().isEmpty() == false) goto L10;
    L13:
        return true;
    L10:
        if (getDialogListStories().size() != 1) goto L14;
        if (DialogObject.getPeerDialogId(getDialogListStories().get(0).peer) != UserConfig.getInstance(this.currentAccount).clientUserId) goto L14;
        goto L13
    }

    public boolean hasSelfStories() {
        long r0 = UserConfig.getInstance(this.currentAccount).clientUserId;
        TL_stories.PeerStories r2 = this.allStoriesMap.get(r0);
        if (r2 == null) goto L8;
        if (r2.stories.isEmpty() == true) goto L8;
        return true;
    L8:
        if (Utilities.isNullOrEmpty(this.uploadingStoriesByDialogId.get(r0)) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasStories() {
        if (ExteraConfig.getHideStories() == false) goto L5;
        return false;
    L5:
        ArrayList<TL_stories.PeerStories> r0 = this.dialogListStories;
        if (r0 == null) goto L10;
        if (r0.size() <= 0) goto L10;
        return true;
    L10:
        if (hasSelfStories() == true) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean hasStories(long r4) {
        if (r4 != 0) goto L5;
    L20:
        return false;
    L5:
        if (ExteraConfig.getHideStories() == true) goto L20;
        if (hasUploadingStories(r4) == false) goto L11;
        return true;
    L11:
        if (isLastUploadingFailed(r4) == false) goto L13;
        return true;
    L13:
        TL_stories.PeerStories r0 = this.allStoriesMap.get(r4);
        if (r0 != null) goto L16;
        r0 = getStoriesFromFullPeer(r4);
    L16:
        if (r0 == null) goto L20;
        if (r0.stories.isEmpty() == true) goto L20;
        return true;
    }

    public boolean hasStoryLimit(int r2) {
        StoryLimit r0 = checkStoryLimit();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.active(this.currentAccount, r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasUnreadStories(long r5) {
        TL_stories.PeerStories r0 = this.allStoriesMap.get(r5);
        if (r0 != null) goto L6;
        r0 = getStoriesFromFullPeer(r5);
    L6:
        if (r0 != null) goto L9;
        return false;
    L9:
        if (r5 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L11;
    L13:
        int r4 = 0;
    L15:
        if (r4 >= r0.stories.size()) goto L23;
        TL_stories.StoryItem r52 = r0.stories.get(r4);
        if (r52 == null) goto L22;
        if (r52.id <= r0.max_read_id) goto L22;
        return true;
    L22:
        r4 = r4 + 1;
        goto L15
    L23:
        return false;
    L11:
        if (Utilities.isNullOrEmpty(this.uploadingStoriesByDialogId.get(r5)) == true) goto L13;
        return true;
    }

    public int hasUnreadStoriesLive(long r5) {
        TL_stories.PeerStories r0 = this.allStoriesMap.get(r5);
        if (r0 != null) goto L6;
        r0 = getStoriesFromFullPeer(r5);
    L6:
        if (r0 != null) goto L9;
        return 0;
    L9:
        if (r5 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L11;
    L13:
        int r4 = 0;
    L15:
        if (r4 >= r0.stories.size()) goto L27;
        TL_stories.StoryItem r52 = r0.stories.get(r4);
        if (r52 == null) goto L26;
        if ((r52.media instanceof TLRPC.TL_messageMediaVideoStream) == true) goto L21;
        if (r52.id <= r0.max_read_id) goto L26;
        return 1;
    L21:
        return 2;
    L26:
        r4 = r4 + 1;
        goto L15
    L27:
        return 0;
    L11:
        if (Utilities.isNullOrEmpty(this.uploadingStoriesByDialogId.get(r5)) == true) goto L13;
        return 1;
    }

    public boolean hasUploadingStories(long r2) {
        ArrayList<UploadingStory> r0 = this.uploadingStoriesByDialogId.get(r2);
        HashMap<Integer, UploadingStory> r1 = this.editingStories.get(r2);
        if (r0 != null) goto L5;
    L6:
        if (r1 != null) goto L8;
        return false;
    L8:
        if (r1.isEmpty() == true) goto L14;
        return true;
    L14:
        return false;
    L5:
        if (r0.isEmpty() == true) goto L6;
        return true;
    }

    public void invalidateStoryLimit() {
        this.storyLimitFetched = false;
        this.storyLimitCached = null;
    }

    public boolean isBlocked(TL_stories.StoryView r6) {
        if (r6 != null) goto L6;
        return false;
    L6:
        if (this.blockedOverride.containsKey(r6.user_id) == false) goto L10;
        return this.blockedOverride.get(r6.user_id).booleanValue();
    L10:
        if (this.lastBlocklistRequested != 0) goto L19;
        if (r6.blocked_my_stories_from == false) goto L14;
    L17:
        return true;
    L14:
        if (r6.blocked == true) goto L17;
        return false;
    L19:
        if (this.blocklist.contains(Long.valueOf(r6.user_id)) == false) goto L22;
        return true;
    L22:
        if (r6.blocked_my_stories_from == false) goto L24;
    L27:
        return true;
    L24:
        if (r6.blocked == true) goto L27;
        return false;
    }

    public boolean isLastUploadingFailed(long r1) {
        ArrayList<UploadingStory> r0 = this.uploadingStoriesByDialogId.get(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == false) goto L7;
        return false;
    L7:
        return r0.get(r0.size() - 1).failed;
    }

    public boolean isLoading(long r2) {
        if (this.loadingDialogsStories.get(r2, 0) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public void loadAllStories() {
        if (this.firstLoad == true) goto L6;
        loadStories();
        loadStoriesRead();
        return;
    }

    public void loadBlocklist(boolean r8) {
        if (this.blocklistLoading == false) goto L8;
        if (r8 == true) goto L6;
        return;
    L6:
        if (this.blocklistLoadingReset == true) goto L22;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.blocklistReqId, true);
        this.blocklistReqId = 0;
        this.blocklistLoadingReset = false;
        this.blocklistLoading = false;
        goto L8
    L22:
        return;
    L8:
        if (r8 == true) goto L10;
    L12:
        if (r8 == false) goto L14;
    L16:
        this.blocklistLoading = true;
        this.blocklistLoadingReset = r8;
        TLRPC.TL_contacts_getBlocked r0 = new TLRPC.TL_contacts_getBlocked();
        r0.my_stories_from = true;
        if (r8 == false) goto L19;
        r0.offset = 0;
        r0.limit = 100;
        this.blocklistFull = false;
    L20:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda20(this));
        return;
    L19:
        r0.offset = this.blocklist.size();
        r0.limit = 25;
        goto L20
    L14:
        if (this.blocklistFull == false) goto L16;
        return;
    L10:
        if ((System.currentTimeMillis() - this.lastBlocklistRequested) >= 1800000) goto L12;
    }

    public void loadBlocklistAtFirst() {
        if (this.lastBlocklistRequested != 0) goto L6;
        loadBlocklist(false);
        return;
    }

    public void loadHiddenStories() {
        if (ExteraConfig.getHideStories() == false) goto L7;
        this.hiddenListStories.clear();
        return;
    L7:
        if (this.hasMoreHidden == false) goto L10;
        loadFromServer(true);
        return;
    }

    public void loadNextStories(boolean r2) {
        if (r2 == false) goto L7;
        if (this.hasMoreHidden == false) goto L10;
    L8:
        loadFromServer(r2);
        return;
    L10:
        return;
    L7:
        if (this.hasMore == true) goto L8;
    }

    public void loadSendAs() {
        if (this.loadingSendAs == false) goto L5;
        return;
    L5:
        if (this.loadedSendAs == true) goto L10;
        this.loadingSendAs = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_stories.TL_stories_getChatsToSend(), new StoriesController$$ExternalSyntheticLambda17(this));
        return;
    }

    public void loadSkippedStories(long r2) {
        TL_stories.PeerStories r0 = getStories(r2);
        if (r0 != null) goto L5;
        r0 = getStoriesFromFullPeer(r2);
        boolean r22 = true;
    L6:
        loadSkippedStories(r0, r22);
        return;
    L5:
        r22 = false;
        goto L6
    }

    public void loadSkippedStories(TL_stories.PeerStories r11, boolean r12) {
        if (r11 == null) goto L32;
        long r7 = DialogObject.getPeerDialogId(r11.peer);
        if (r12 == false) goto L7;
        int r0 = -1;
    L8:
        long r3 = r7 * r0;
        if (this.loadingAllStories.contains(Long.valueOf(r3)) == true) goto L33;
        ArrayList<Integer> r02 = null;
        int r1 = 0;
    L13:
        if (r1 >= r11.stories.size()) goto L24;
        if ((r11.stories.get(r1) instanceof TL_stories.TL_storyItemSkipped) == false) goto L19;
        if (r02 != null) goto L18;
        r02 = new ArrayList();
    L18:
        r02.add(Integer.valueOf(r11.stories.get(r1).id));
    L19:
        if (r02 == null) goto L23;
        if (r02.size() > 14) goto L24;
    L23:
        r1 = r1 + 1;
    L24:
        if (r02 == null) goto L31;
        this.loadingAllStories.add(Long.valueOf(r3));
        TL_stories.TL_stories_getStoriesByID r9 = new TL_stories.TL_stories_getStoriesByID();
        r9.id = r02;
        r9.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r7);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r9, new StoriesController$$ExternalSyntheticLambda23(this, r3, r12, r11, r7));
        return;
    L31:
        return;
    L33:
        return;
    L7:
        r0 = 1;
        goto L8
    }

    public void loadStories() {
        if (ExteraConfig.getHideStories() == false) goto L7;
        this.dialogListStories.clear();
        this.hiddenListStories.clear();
        this.allStoriesMap.clear();
        this.loadingDialogsStories.clear();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        return;
    L7:
        if (this.firstLoad == false) goto L9;
        this.loadingFromDatabase = true;
        this.storiesStorage.getAllStories(new StoriesController$$ExternalSyntheticLambda13(this));
    L10:
        this.firstLoad = false;
        return;
    L9:
        loadFromServer(false);
        loadFromServer(true);
        goto L10
    }

    public void markStoriesAsReadFromServer(long r2, int r4) {
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda12(this, r2, r4));
    }

    public boolean markStoryAsRead(long r2, TL_stories.StoryItem r4) {
        TL_stories.PeerStories r0 = getStories(r2);
        if (r0 != null) goto L6;
        r0 = getStoriesFromFullPeer(r2);
    L6:
        return markStoryAsRead(r0, r4, false);
    }

    public boolean markStoryAsRead(TL_stories.PeerStories r7, TL_stories.StoryItem r8, boolean r9) {
        if (r8 == null) goto L15;
        if (r7 == null) goto L15;
        long r1 = DialogObject.getPeerDialogId(r7.peer);
        if (r8.justUploaded == false) goto L9;
        r8.justUploaded = false;
    L9:
        int r3 = this.dialogIdToMaxReadId.get(r1);
        int r4 = Math.max(r7.max_read_id, Math.max(r3, r8.id));
        NotificationsController.getInstance(this.currentAccount).processReadStories(r1, r4);
        r7.max_read_id = r4;
        this.dialogIdToMaxReadId.put(r1, r4);
        if (r4 <= r3) goto L15;
        if (r9 == true) goto L13;
        this.storiesStorage.updateMaxReadId(r1, r4);
    L13:
        TL_stories.TL_stories_readStories r72 = new TL_stories.TL_stories_readStories();
        r72.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r1);
        r72.max_id = r8.id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r72, null);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesReadUpdated, new Object[0]);
        return true;
    L15:
        return false;
    }

    public void onPremiumChanged() {
        this.selfViewsModel.clear();
    }

    public void pollViewsForSelfStories(long r3, boolean r5) {
        ViewsForPeerStoriesRequester r0 = this.pollingViewsForSelfStoriesRequester.get(r3);
        if (r0 != null) goto L5;
        r0 = new ViewsForPeerStoriesRequester(this, r3, this.currentAccount);
        this.pollingViewsForSelfStoriesRequester.put(r3, r0);
    L5:
        r0.start(r5);
    }

    public void preloadUserStories(TL_stories.PeerStories r6) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r6.stories.size()) goto L10;
        if (r6.stories.get(r1).id > r6.max_read_id) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        r0 = r1;
    L10:
        if (r6.stories.isEmpty() == true) goto L22;
        long r12 = DialogObject.getPeerDialogId(r6.peer);
        preloadStory(r12, r6.stories.get(r0));
        if (r0 <= 0) goto L16;
        preloadStory(r12, r6.stories.get(r0 - 1));
    L16:
        if (r0 >= (r6.stories.size() - 1)) goto L21;
        preloadStory(r12, r6.stories.get(r0 + 1));
        return;
    L21:
        return;
    }

    public void processUpdate(TL_stories.TL_updateStory r8) {
        if (r8.story != null) goto L5;
        return;
    L5:
        long r3 = DialogObject.getPeerDialogId(r8.peer);
        if (r3 != 0) goto L9;
        FileLog.d("StoriesController can't update story dialogId == 0");
        return;
    L9:
        if (r3 <= 0) goto L18;
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r3));
        if (r0 == null) goto L19;
        if (isContactOrService(r0) == false) goto L15;
    L16:
        this.storiesStorage.processUpdate(r8);
        goto L19
    L15:
        if (r0.self == true) goto L16;
    L19:
        AndroidUtilities.runOnUIThread(new StoriesController$$ExternalSyntheticLambda8(this, r3, r8, r0));
        return;
    L18:
        this.storiesStorage.processUpdate(r8);
        r0 = null;
        goto L19
    }

    public void putStories(long r3, TL_stories.PeerStories r5) {
        putToAllStories(r3, r5);
        int r1 = this.currentAccount;
        if (r3 <= 0) goto L11;
        TLRPC.User r32 = MessagesController.getInstance(r1).getUser(Long.valueOf(r3));
        if (isContactOrService(r32) == false) goto L7;
    L8:
        this.storiesStorage.putPeerStories(r5);
        return;
    L7:
        if (r32.self == true) goto L8;
        return;
    L11:
        if (ChatObject.isInChat(MessagesController.getInstance(r1).getChat(Long.valueOf(-r3))) == false) goto L15;
        this.storiesStorage.putPeerStories(r5);
        return;
    }

    public void putUploadingDrafts(ArrayList<StoryEntry> r11) {
        int r0 = r11.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        StoryEntry r3 = r11.get(r2);
        r2 = r2 + 1;
        UploadingStory r7 = new UploadingStory(this, r3);
        addUploadingStoryToList(r7.dialogId, r7, this.uploadingStoriesByDialogId, false);
        goto L3
    L5:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
    }

    public void removeAlbum(long r1, int r3) {
        getStoryAlbumsList(r1).removeCollection(r3);
    }

    public void removeContact(long r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.dialogListStories.size()) goto L9;
        if (DialogObject.getPeerDialogId(this.dialogListStories.get(r1).peer) == r5) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        this.dialogListStories.remove(r1);
    L9:
        int r12 = 0;
    L11:
        if (r12 >= this.hiddenListStories.size()) goto L16;
        if (DialogObject.getPeerDialogId(this.hiddenListStories.get(r12).peer) == r5) goto L14;
        r12 = r12 + 1;
        goto L11
    L14:
        this.hiddenListStories.remove(r12);
    L16:
        this.storiesStorage.deleteAllUserStories(r5);
        MessagesController.getInstance(this.currentAccount).checkArchiveFolder();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
    }

    public void removeStoriesFromAlbum(long r1, int r3, ArrayList<TL_stories.StoryItem> r4) {
        getStoryAlbumsList(r1).removeStories(r3, r4);
    }

    public void removeStoryFromAlbum(long r3, int r5, TL_stories.StoryItem r6) {
        ArrayList<TL_stories.StoryItem> r0 = new ArrayList(1);
        r0.add(r6);
        removeStoriesFromAlbum(r3, r5, r0);
    }

    public void renameAlbum(long r1, int r3, String r4) {
        getStoryAlbumsList(r1).renameCollection(r3, r4);
    }

    public void resolveLiveStoryLink(long r5, Utilities.Callback<TL_stories.StoryItem> r7) {
        TL_stories.PeerStories r0 = getStories(r5);
        if (r0 == null) goto L14;
        int r1 = 0;
    L6:
        if (r1 >= r0.stories.size()) goto L14;
        if ((r0.stories.get(r1).media instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L13;
        if ((r0.stories.get(r1) instanceof TL_stories.TL_storyItemSkipped) == true) goto L13;
        r7.run(r0.stories.get(r1));
        return;
    L13:
        r1 = r1 + 1;
    L14:
        long r02 = ((-93321425) + r5) << 12;
        TL_stories.StoryItem r2 = this.resolvedStories.get(r02);
        if (r2 == null) goto L18;
        r7.run(r2);
        return;
    L18:
        TL_stories.TL_stories_getPeerStories r22 = new TL_stories.TL_stories_getPeerStories();
        r22.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r5);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new AnonymousClass1(this, r02, r7));
    }

    public void resolveStoryAlbumLink(long r3, int r5, Utilities.Callback<TL_stories.TL_storyAlbum> r6) {
        StoriesCollections r0 = getStoryAlbumsList(r3, false);
        if (r0 == null) goto L12;
        StoryAlbum r1 = r0.findById(r5);
        if (r1 == null) goto L9;
        r6.run(r1.toTl());
        return;
    L9:
        if (r0.loaded == false) goto L12;
        r6.run(null);
        return;
    L12:
        TL_stories.TL_getAlbums r02 = new TL_stories.TL_getAlbums();
        r02.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r3);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new StoriesController$$ExternalSyntheticLambda27(r5, r6));
    }

    public void resolveStoryLink(long r5, int r7, Utilities.Callback<TL_stories.StoryItem> r8) {
        TL_stories.PeerStories r0 = getStories(r5);
        if (r0 == null) goto L14;
        int r1 = 0;
    L6:
        if (r1 >= r0.stories.size()) goto L14;
        if (r0.stories.get(r1).id != r7) goto L13;
        if ((r0.stories.get(r1) instanceof TL_stories.TL_storyItemSkipped) == true) goto L13;
        r8.run(r0.stories.get(r1));
        return;
    L13:
        r1 = r1 + 1;
    L14:
        long r02 = (r7 + r5) << 12;
        TL_stories.StoryItem r2 = this.resolvedStories.get(r02);
        if (r2 == null) goto L18;
        r8.run(r2);
        return;
    L18:
        TL_stories.TL_stories_getStoriesByID r22 = new TL_stories.TL_stories_getStoriesByID();
        r22.id.add(Integer.valueOf(r7));
        r22.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r5);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new AnonymousClass2(this, r02, r8));
    }

    public void scheduleSort() {
        AndroidUtilities.cancelRunOnUIThread(this.sortStoriesRunnable);
        this.sortStoriesRunnable.run();
    }

    public void setLoading(long r1, boolean r3) {
        LongSparseIntArray r0 = this.loadingDialogsStories;
        if (r3 == false) goto L6;
        r0.put(r1, 1);
        return;
    L6:
        r0.delete(r1);
    }

    public void setStealthMode(TL_stories.TL_storiesStealthMode r4) {
        this.stealthMode = r4;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.stealthModeChanged, new Object[0]);
        writeStealthMode(r4);
    }

    public void setStoryReaction(long r6, TL_stories.StoryItem r8, ReactionsLayoutInBubble.VisibleReaction r9) {
        if (r8 != null) goto L4;
        return;
    L4:
        TL_stories.TL_stories_sendReaction r0 = new TL_stories.TL_stories_sendReaction();
        r0.story_id = r8.id;
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r6);
        if (r9 != null) goto L8;
        r0.reaction = new TLRPC.TL_reactionEmpty();
        r8.flags &= -32769;
        r8.sent_reaction = null;
    L13:
        updateStoryItem(r6, r8, true);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda36());
        return;
    L8:
        if (r9.documentId == 0) goto L11;
        TLRPC.TL_reactionCustomEmoji r1 = new TLRPC.TL_reactionCustomEmoji();
        r1.document_id = r9.documentId;
        r0.reaction = r1;
        r8.flags |= 32768;
        r8.sent_reaction = r1;
        goto L13
    L11:
        if (r9.emojicon == null) goto L13;
        TLRPC.TL_reactionEmoji r12 = new TLRPC.TL_reactionEmoji();
        r12.emoticon = r9.emojicon;
        r0.reaction = r12;
        r8.flags |= 32768;
        r8.sent_reaction = r12;
        goto L13
    }

    public void sortHiddenStories() {
        sortDialogStories(this.hiddenListStories);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
    }

    public void stopAllPollers() {
        int r1 = 0;
    L4:
        if (r1 >= this.pollingViewsForSelfStoriesRequester.size()) goto L6;
        this.pollingViewsForSelfStoriesRequester.valueAt(r1).start(false);
        r1 = r1 + 1;
        goto L4
    }

    public void toggleHidden(long r9, boolean r11, boolean r12, boolean r13) {
        if (r11 == false) goto L4;
        ArrayList<TL_stories.PeerStories> r0 = this.dialogListStories;
        ArrayList<TL_stories.PeerStories> r1 = this.hiddenListStories;
    L5:
        int r3 = 0;
    L7:
        if (r3 >= r0.size()) goto L12;
        if (DialogObject.getPeerDialogId(r0.get(r3).peer) == r9) goto L10;
        r3 = r3 + 1;
        goto L7
    L10:
        TL_stories.PeerStories r02 = r0.remove(r3);
    L13:
        if (r02 == null) goto L22;
        int r32 = 0;
    L16:
        if (r32 >= r1.size()) goto L21;
        if (DialogObject.getPeerDialogId(r1.get(r32).peer) == r9) goto L22;
        r32 = r32 + 1;
        goto L16
    L21:
        r1.add(0, r02);
        AndroidUtilities.cancelRunOnUIThread(this.sortStoriesRunnable);
        this.sortStoriesRunnable.run();
    L22:
        if (r13 == false) goto L24;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
    L24:
        MessagesController.getInstance(this.currentAccount).checkArchiveFolder();
        if (r12 == false) goto L36;
        int r132 = this.currentAccount;
        if (r9 < 0) goto L29;
        TLRPC.User r122 = MessagesController.getInstance(r132).getUser(Long.valueOf(r9));
        r122.stories_hidden = r11;
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(Collections.singletonList(r122), null, false, true);
        MessagesController.getInstance(this.currentAccount).putUser(r122, false);
    L30:
        TL_stories.TL_stories_togglePeerStoriesHidden r123 = new TL_stories.TL_stories_togglePeerStoriesHidden();
        r123.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r9);
        r123.hidden = r11;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r123, new StoriesController$$ExternalSyntheticLambda32());
        return;
    L29:
        TLRPC.Chat r124 = MessagesController.getInstance(r132).getChat(Long.valueOf(-r9));
        r124.stories_hidden = r11;
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(null, Collections.singletonList(r124), false, true);
        MessagesController.getInstance(this.currentAccount).putChat(r124, false);
        goto L30
    L36:
        return;
    L12:
        r02 = null;
        goto L13
    L4:
        r0 = this.hiddenListStories;
        r1 = this.dialogListStories;
        goto L5
    }

    public void updateBlockUser(long r2, boolean r4) {
        updateBlockUser(r2, r4, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateBlockUser(long r4, boolean r6, boolean r7) {
        TLRPC.InputPeer r0 = MessagesController.getInstance(this.currentAccount).getInputPeer(r4);
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0 instanceof TLRPC.TL_inputPeerEmpty) == true) goto L21;
        this.blockedOverride.put(r4, Boolean.valueOf(r6));
        if (this.blocklist.contains(Long.valueOf(r4)) == r6) goto L13;
        HashSet<Long> r1 = this.blocklist;
        if (r6 == false) goto L12;
        r1.add(Long.valueOf(r4));
        this.blocklistCount++;
        goto L13
    L12:
        r1.remove(Long.valueOf(r4));
        this.blocklistCount--;
    L13:
        if (r7 == false) goto L18;
        if (r6 == false) goto L16;
        TLRPC.TL_contacts_block r42 = new TLRPC.TL_contacts_block();
        r42.my_stories_from = true;
        r42.id = r0;
        TLRPC.TL_contacts_unblock r43 = r42;
    L17:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r43, null);
        goto L18
    L16:
        TLRPC.TL_contacts_unblock r44 = new TLRPC.TL_contacts_unblock();
        r44.my_stories_from = true;
        r44.id = r0;
        r43 = r44;
    L18:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesBlocklistUpdate, new Object[0]);
        return;
    }

    public void updateBlockedUsers(HashSet<Long> r6, Runnable r7) {
        TLRPC.TL_contacts_setBlocked r0 = new TLRPC.TL_contacts_setBlocked();
        r0.my_stories_from = true;
        r0.limit = this.blocklist.size();
        int r1 = this.blocklistCount - this.blocklist.size();
        this.blocklistCount = r1;
        if (r1 >= 0) goto L5;
        this.blocklistCount = 0;
    L5:
        this.blocklist.clear();
        Iterator<Long> r62 = r6.iterator();
    L7:
        if (r62.hasNext() == false) goto L14;
        Long r12 = r62.next();
        TLRPC.InputPeer r2 = MessagesController.getInstance(this.currentAccount).getInputPeer(r12.longValue());
        if (r2 == null) goto L7;
        if ((r2 instanceof TLRPC.TL_inputPeerEmpty) == true) goto L7;
        this.blocklist.add(r12);
        r0.id.add(r2);
        goto L7
    L14:
        this.blocklistCount += this.blocklist.size();
        r0.limit = Math.max(r0.limit, this.blocklist.size());
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda39(r7));
    }

    public void updateDeletedStoriesInLists(long r11, List<TL_stories.StoryItem> r13) {
        FileLog.d("updateDeletedStoriesInLists " + r11 + " storyItems[" + r13.size() + "] {" + storyItemIds(r13) + "}");
        int r0 = 0;
        StoriesList r1 = getStoriesList(r11, 0, false);
        StoriesList r2 = getStoriesList(r11, 1, false);
        if (r1 == null) goto L5;
        r1.updateDeletedStories(r13);
    L5:
        if (r2 == null) goto L7;
        r2.updateDeletedStories(r13);
    L7:
        StoriesCollections r12 = getStoryAlbumsList(r11, false);
        if (r12 == null) goto L17;
        ArrayList<StoryAlbum> r14 = r12.collections;
        if (r14 == null) goto L21;
        int r22 = r14.size();
    L12:
        if (r0 >= r22) goto L22;
        StoryAlbum r3 = r14.get(r0);
        r0 = r0 + 1;
        StoriesController r4 = this;
        long r5 = r11;
        StoriesList r10 = r4.getStoriesList(r5, 0, r3.album_id, false);
        if (r10 == null) goto L15;
        r10.updateDeletedStories(r13);
    L15:
        StoriesController this = r4;
        r11 = r5;
        goto L12
    L22:
        return;
    L21:
        return;
    }

    public void updateStoriesFromFullPeer(long r4, TL_stories.PeerStories r6) {
        if (r6 == null) goto L22;
        TL_stories.PeerStories r3 = this.allStoriesMap.get(r4);
        if (r3 == null) goto L30;
        FileLog.d("StoriesController update stories from full peer " + r4);
        int r5 = 0;
    L9:
        if (r5 >= r3.stories.size()) goto L31;
        if ((r3.stories.get(r5) instanceof TL_stories.TL_storyItemSkipped) == false) goto L21;
        int r0 = r3.stories.get(r5).id;
        int r1 = 0;
    L14:
        if (r1 >= r6.stories.size()) goto L21;
        if (r6.stories.get(r1).id != r0) goto L20;
        if ((r6.stories.get(r1) instanceof TL_stories.TL_storyItem) == false) goto L20;
        r3.stories.set(r5, r6.stories.get(r1));
    L20:
        r1 = r1 + 1;
    L21:
        r5 = r5 + 1;
        goto L9
    L31:
        return;
    L30:
        return;
    }

    public void updateStoriesInLists(long r2, List<TL_stories.StoryItem> r4) {
        updateStoriesInLists(r2, r4, false);
    }

    public void updateStoriesInLists(long r11, List<TL_stories.StoryItem> r13, boolean r14) {
        FileLog.d("updateStoriesInLists " + r11 + " storyItems[" + r13.size() + "] {" + storyItemIds(r13) + "}");
        int r0 = 0;
        StoriesList r1 = getStoriesList(r11, 0, false);
        StoriesList r2 = getStoriesList(r11, 1, false);
        if (r1 == null) goto L5;
        r1.updateStories(r13, r14);
    L5:
        if (r2 == null) goto L7;
        r2.updateStories(r13, r14);
    L7:
        ArrayList<SearchStoriesList> r12 = this.attachedSearchLists;
        int r22 = r12.size();
        int r3 = 0;
    L8:
        if (r3 >= r22) goto L10;
        SearchStoriesList r4 = r12.get(r3);
        r3 = r3 + 1;
        r4.updateStories(r13, r14);
        goto L8
    L10:
        StoriesCollections r15 = getStoryAlbumsList(r11, false);
        if (r15 == null) goto L20;
        ArrayList<StoryAlbum> r16 = r15.collections;
        if (r16 == null) goto L25;
        int r23 = r16.size();
    L15:
        if (r0 >= r23) goto L26;
        StoryAlbum r32 = r16.get(r0);
        r0 = r0 + 1;
        StoriesController r42 = this;
        long r5 = r11;
        StoriesList r10 = r42.getStoriesList(r5, 0, r32.album_id, false);
        if (r10 == null) goto L18;
        r10.updateStories(r13, r14);
    L18:
        StoriesController this = r42;
        r11 = r5;
        goto L15
    L26:
        return;
    L25:
        return;
    }

    public void updateStoriesPinned(long r9, ArrayList<TL_stories.StoryItem> r11, boolean r12, Utilities.Callback<Boolean> r13) {
        TL_stories.togglePinned r0 = new TL_stories.togglePinned();
        TL_stories.PeerStories r1 = getStories(r9);
        int r3 = 0;
    L4:
        if (r3 >= r11.size()) goto L18;
        TL_stories.StoryItem r4 = r11.get(r3);
        if ((r4 instanceof TL_stories.TL_storyItemDeleted) == true) goto L17;
        r4.pinned = r12;
        r0.id.add(Integer.valueOf(r4.id));
        if (r1 == null) goto L17;
        int r5 = 0;
    L12:
        if (r5 >= r1.stories.size()) goto L17;
        if (r1.stories.get(r5).id != r4.id) goto L16;
        r1.stories.get(r5).pinned = r12;
        this.storiesStorage.updateStoryItem(r9, r4);
    L16:
        r5 = r5 + 1;
    L17:
        r3 = r3 + 1;
        goto L4
    L18:
        FileLog.d("StoriesController updateStoriesPinned");
        updateStoriesInLists(r9, r11);
        updateStoriesForFullPeer(r9, r11, false);
        r0.pinned = r12;
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r9);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoriesController$$ExternalSyntheticLambda21(r13));
    }

    public void updateStoryItem(long r7, TL_stories.StoryItem r9, boolean r10) {
        updateStoryItem(r7, r9, false, r10);
    }

    public void updateStoryItem(long r5, TL_stories.StoryItem r7, boolean r8, boolean r9) {
        StringBuilder r0 = new StringBuilder("StoriesController updateStoryItem ");
        r0.append(r5);
        r0.append(" ");
        if (r7 != null) goto L5;
        String r1 = "null";
    L6:
        r0.append(r1);
        FileLog.d(r0.toString());
        updateStoriesInLists(r5, Collections.singletonList(r7), r8);
        this.storiesStorage.updateStoryItem(r5, r7);
        updateStoriesForFullPeer(r5, Collections.singletonList(r7), !r9);
        return;
    L5:
        r1 = r7.id + "@" + r7.dialogId;
        goto L6
    }

    public void updateStoryReaction(long r2, int r4, TLRPC.Reaction r5) {
        TL_stories.StoryItem r42 = findStory(r2, r4);
        if (r42 == null) goto L10;
        r42.sent_reaction = r5;
        int r0 = r42.flags;
        if (r5 == null) goto L7;
        r42.flags = 32768 | r0;
    L8:
        updateStoryItem(r2, r42, true);
        return;
    L7:
        r42.flags = (-32769) & r0;
        goto L8
    }

    public void uploadStory(StoryEntry r8, boolean r9) {
        UploadingStory r3 = new UploadingStory(this, r8);
        if (r9 == false) goto L32;
        long r1 = r3.dialogId;
        if (r8.isEdit == false) goto L10;
        HashMap<Integer, UploadingStory> r92 = this.editingStories.get(r1);
        if (r92 != null) goto L9;
        r92 = new HashMap();
        this.editingStories.put(r1, r92);
    L9:
        r92.put(Integer.valueOf(r8.editStoryId), r3);
        StoriesController r0 = this;
    L11:
        r0.addUploadingStoryToList(r1, r3, r0.uploadingAndEditingStories, true);
        if (r1 == UserConfig.getInstance(r0.currentAccount).clientUserId) goto L33;
        int r7 = 0;
    L14:
        boolean r93 = true;
        if (r7 >= r0.dialogListStories.size()) goto L20;
        if (DialogObject.getPeerDialogId(r0.dialogListStories.get(r7).peer) == r1) goto L18;
        r7 = r7 + 1;
        goto L14
    L18:
        r0.dialogListStories.add(0, r0.dialogListStories.remove(r7));
        boolean r72 = true;
    L21:
        if (r72 == true) goto L29;
        int r82 = 0;
    L24:
        if (r82 >= r0.hiddenListStories.size()) goto L29;
        if (DialogObject.getPeerDialogId(r0.hiddenListStories.get(r82).peer) == r1) goto L27;
        r82 = r82 + 1;
        goto L24
    L27:
        r0.hiddenListStories.add(0, r0.hiddenListStories.remove(r82));
    L30:
        if (r93 == true) goto L33;
        TL_stories.TL_peerStories r73 = new TL_stories.TL_peerStories();
        r73.peer = MessagesController.getInstance(r0.currentAccount).getPeer(r1);
        r0.putToAllStories(r1, r73);
        r0.dialogListStories.add(0, r73);
        r0.loadAllStoriesForDialog(r1);
    L29:
        r93 = r72;
        goto L30
    L20:
        r72 = false;
    L33:
        r3.start();
        NotificationCenter.getInstance(r0.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        return;
    L10:
        r0 = this;
        r0.addUploadingStoryToList(r1, r3, this.uploadingStoriesByDialogId, false);
        goto L11
    L32:
        r0 = this;
        goto L33
    }
}
