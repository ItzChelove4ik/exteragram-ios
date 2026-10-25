package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.MediaDescription;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import androidx.collection.LongSparseArray;
import androidx.mediarouter.media.MediaItemMetadata;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LaunchActivity;

@SuppressLint({"StaticFieldLeak"})
/* loaded from: classes5.dex */
public class TelegramMediaSession {
    private static final String CONTENT_STYLE_BROWSABLE_HINT = "android.media.browse.CONTENT_STYLE_BROWSABLE_HINT";
    private static final int CONTENT_STYLE_GRID_ITEM_HINT_VALUE = 2;
    private static final int CONTENT_STYLE_LIST_ITEM_HINT_VALUE = 1;
    private static final String CONTENT_STYLE_PLAYABLE_HINT = "android.media.browse.CONTENT_STYLE_PLAYABLE_HINT";
    private static final String CONTENT_STYLE_SUPPORTED = "android.media.browse.CONTENT_STYLE_SUPPORTED";
    private static final String MEDIA_ID_CHAT_PREFIX = "__CHAT_";
    private static final String MEDIA_ID_ROOT = "__ROOT__";
    private static final String SESSION_TAG = "TelegramMediaSession";
    private static final String SLOT_RESERVATION_QUEUE = "com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_QUEUE";
    private static final String SLOT_RESERVATION_SKIP_TO_NEXT = "com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT";
    private static final String SLOT_RESERVATION_SKIP_TO_PREV = "com.google.android.gms.car.media.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS";
    private static volatile TelegramMediaSession instance;
    private final Context appContext;
    private RectF bitmapRect;
    private final LongSparseArray<TLRPC.Chat> chats;
    private boolean chatsLoaded;
    private int currentAccount;
    private final ArrayList<Long> dialogs;
    private long lastSelectedDialog;
    private boolean loadingChats;
    private boolean mirrorPlayback;
    private final LongSparseArray<ArrayList<MessageObject>> musicObjects;
    private final LongSparseArray<ArrayList<MediaSessionCompat.QueueItem>> musicQueues;
    private final ArrayList<PendingBrowseRequest> pendingBrowseRequests;
    private final LongSparseArray<ArrayList<Runnable>> pendingMusicLoads;
    private Paint roundPaint;
    private final MediaSessionCompat session;
    private final LongSparseArray<TLRPC.User> users;

    public interface BrowseChildrenCallback {
        void onResult(List<MediaBrowser.MediaItem> r1);
    }

    public static final class PendingBrowseRequest {
        final BrowseChildrenCallback callback;
        final String parentMediaId;

        public PendingBrowseRequest(String r1, BrowseChildrenCallback r2) {
            this.parentMediaId = r1;
            this.callback = r2;
        }
    }

    public final class SessionCallback extends MediaSessionCompat.Callback {
        final /* synthetic */ TelegramMediaSession this$0;

        /* renamed from: $r8$lambda$6bdmqF1A8ktUCj_9tn6ZA9JV-2U, reason: not valid java name */
        public static /* synthetic */ void m5006$r8$lambda$6bdmqF1A8ktUCj_9tn6ZA9JV2U(SessionCallback r0, String r1, Bundle r2) {
            r0.lambda$onPlayFromMediaId$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$VmAdSdLqcvDdNoBCP9hDLCn8oIc(SessionCallback r0) {
            r0.lambda$notifyPlayStateForNotificationRefresh$0();
        }

        private SessionCallback(TelegramMediaSession r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ SessionCallback(TelegramMediaSession r1, TelegramMediaSessionIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$notifyPlayStateForNotificationRefresh$0() {
            NotificationCenter.getInstance(TelegramMediaSession.m4998$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{0});
        }

        private /* synthetic */ void lambda$onPlayFromMediaId$1(String r1, Bundle r2) {
            onPlayFromMediaId(r1, r2);
        }

        private void notifyPlayStateForNotificationRefresh() {
            AndroidUtilities.runOnUIThread(new TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda1(this));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onCustomAction(String r1, Bundle r2) {
            if (MusicPlayerService.NOTIFY_REPEAT.equals(r1) == false) goto L7;
            SharedConfig.setRepeatMode((SharedConfig.repeatMode + 1) % 3);
            this.this$0.updateRepeatMode();
            return;
        L7:
            if (MusicPlayerService.NOTIFY_SHUFFLE.equals(r1) == false) goto L14;
            MediaController r12 = MediaController.getInstance();
            if (SharedConfig.shuffleMusic == false) goto L11;
            int r22 = 0;
        L12:
            r12.setPlaybackOrderType(r22);
            this.this$0.updateShuffleMode();
            return;
        L11:
            r22 = 2;
            goto L12
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPause() {
            MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlay() {
            MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
            if (r0 == null) goto L5;
            MediaController.getInstance().playMessage(r0);
            return;
        L5:
            if (TelegramMediaSession.m5000$$Nest$fgetlastSelectedDialog(this.this$0) == 0) goto L10;
            onPlayFromMediaId(TelegramMediaSession.m5000$$Nest$fgetlastSelectedDialog(this.this$0) + "_0", null);
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlayFromMediaId(String r12, Bundle r13) {
            if (TextUtils.isEmpty(r12) == true) goto L40;
            String[] r0 = r12.split("_");
            if (r0.length == 2) goto L37;
            return;
        L37:
            long r1 = Long.parseLong(r0[0]);     // Catch: Exception -> L34
            int r02 = Integer.parseInt(r0[1]);     // Catch: Exception -> L34
            ArrayList<MessageObject> r5 = (ArrayList) TelegramMediaSession.m5001$$Nest$fgetmusicObjects(this.this$0).get(r1);     // Catch: Exception -> L34
            TelegramMediaSession r3 = this.this$0;
            if (r5 != null) goto L14;
            r3.loadMusicForDialog(r1, new TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda0(this, r12, r13));     // Catch: Exception -> L34
            return;
        L14:
            ArrayList r122 = (ArrayList) TelegramMediaSession.m5002$$Nest$fgetmusicQueues(r3).get(r1);     // Catch: Exception -> L34
            if (r02 >= 0) goto L17;
            return;
        L17:
            if (r02 >= r5.size()) goto L39;
            TelegramMediaSession.m5005$$Nest$fputlastSelectedDialog(this.this$0, r1);     // Catch: Exception -> L34
            MessagesController.getNotificationsSettings(TelegramMediaSession.m4998$$Nest$fgetcurrentAccount(this.this$0)).edit().putLong("auto_lastSelectedDialog", r1).apply();     // Catch: Exception -> L34
            MediaController.getInstance().setPlaylist(r5, r5.get(r02), 0, false, null);     // Catch: Exception -> L34
            TelegramMediaSession.m5003$$Nest$fgetsession(this.this$0).setQueue(r122);     // Catch: Exception -> L34
            boolean r123 = DialogObject.isUserDialog(r1);     // Catch: Exception -> L34
            TelegramMediaSession r132 = this.this$0;
            if (r123 == false) goto L28;
            TLRPC.User r124 = (TLRPC.User) TelegramMediaSession.m5004$$Nest$fgetusers(r132).get(r1);     // Catch: Exception -> L34
            MediaSessionCompat r11 = TelegramMediaSession.m5003$$Nest$fgetsession(this.this$0);     // Catch: Exception -> L34
            if (r124 == null) goto L25;
            String r125 = ContactsController.formatName(r124.first_name, r124.last_name);     // Catch: Exception -> L34
        L26:
            r11.setQueueTitle(r125);     // Catch: Exception -> L34
            return;
        L25:
            r125 = "DELETED USER";
            goto L26
        L28:
            TLRPC.Chat r126 = (TLRPC.Chat) TelegramMediaSession.m4997$$Nest$fgetchats(r132).get(-r1);     // Catch: Exception -> L34
            MediaSessionCompat r112 = TelegramMediaSession.m5003$$Nest$fgetsession(this.this$0);     // Catch: Exception -> L34
            if (r126 == null) goto L31;
            String r127 = r126.title;     // Catch: Exception -> L34
        L32:
            r112.setQueueTitle(r127);     // Catch: Exception -> L34
            return;
        L31:
            r127 = "DELETED CHAT";
            goto L32
        L39:
            return;
        L34:
            e = move-exception;
            FileLog.e(e);
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlayFromSearch(String r9, Bundle r10) {
            if (r9 != null) goto L4;
            return;
        L4:
            if (r9.length() == 0) goto L51;
            String r92 = r9.toLowerCase();
            int r102 = 0;
        L8:
            if (r102 >= TelegramMediaSession.m4999$$Nest$fgetdialogs(this.this$0).size()) goto L52;
            long r0 = ((Long) TelegramMediaSession.m4999$$Nest$fgetdialogs(this.this$0).get(r102)).longValue();
            boolean r2 = DialogObject.isUserDialog(r0);
            TelegramMediaSession r3 = this.this$0;
            if (r2 == false) goto L30;
            TLRPC.User r22 = (TLRPC.User) TelegramMediaSession.m5004$$Nest$fgetusers(r3).get(r0);
            if (r22 == null) goto L39;
            String r32 = r22.first_name;
            if (r32 == null) goto L17;
            String r33 = r32.toLowerCase();
        L18:
            String r23 = r22.last_name;
            if (r23 == null) goto L21;
            String r24 = r23.toLowerCase();
        L22:
            if (r33 != null) goto L24;
        L25:
            if (r24 == null) goto L39;
            if (r24.contains(r92) == false) goto L39;
        L28:
            onPlayFromMediaId(r0 + "_0", null);
            return;
        L24:
            if (r33.contains(r92) == true) goto L28;
        L21:
            r24 = null;
            goto L22
        L17:
            r33 = null;
        L39:
            r102 = r102 + 1;
            goto L8
        L30:
            TLRPC.Chat r25 = (TLRPC.Chat) TelegramMediaSession.m4997$$Nest$fgetchats(r3).get(-r0);
            if (r25 == null) goto L39;
            String r26 = r25.title;
            if (r26 == null) goto L39;
            if (r26.toLowerCase().contains(r92) == false) goto L39;
            onPlayFromMediaId(r0 + "_0", null);
            return;
        L52:
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPrepare() {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPrepareFromMediaId(String r1, Bundle r2) {
            onPlayFromMediaId(r1, r2);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPrepareFromSearch(String r1, Bundle r2) {
            onPlayFromSearch(r1, r2);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSeekTo(long r4) {
            MessageObject r3 = MediaController.getInstance().getPlayingMessageObject();
            if (r3 == null) goto L6;
            MediaController.getInstance().seekToProgress(r3, (float) ((r4 / 1000.0d) / r3.getDuration()));
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSetRepeatMode(int r3) {
            int r0 = 2;
            if (r3 == 1) goto L9;
            if (r3 != 2) goto L6;
        L8:
            r0 = 1;
            goto L9
        L6:
            if (r3 == 3) goto L8;
            r0 = 0;
        L9:
            SharedConfig.setRepeatMode(r0);
            this.this$0.updateRepeatMode();
            notifyPlayStateForNotificationRefresh();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSetShuffleMode(int r4) {
            int r0 = 0;
            boolean r2 = true;
            if (r4 == 1) goto L8;
            if (r4 == 2) goto L8;
            r2 = false;
        L8:
            if (r2 == SharedConfig.shuffleMusic) goto L13;
            MediaController r42 = MediaController.getInstance();
            if (r2 == false) goto L12;
            r0 = 2;
        L12:
            r42.setPlaybackOrderType(r0);
        L13:
            this.this$0.updateShuffleMode();
            notifyPlayStateForNotificationRefresh();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToNext() {
            MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isMusic() == false) goto L9;
            MediaController.getInstance().playNextMessage();
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToPrevious() {
            MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isMusic() == false) goto L9;
            MediaController.getInstance().playPreviousMessage();
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToQueueItem(long r1) {
            MediaController.getInstance().playMessageAtIndex((int) r1);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onStop() {
        }
    }

    public static /* synthetic */ void $r8$lambda$4uZUEr5G_uVIwgo8hdzGlYOR00w(TelegramMediaSession r0, MessagesStorage r1, int r2) {
        r0.lambda$loadChats$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$5bNU2z0rLDU5DW0KYv6tGuAVISM(TelegramMediaSession r0) {
        r0.onAccountSwitched();
    }

    /* renamed from: $r8$lambda$9938PNr-Tm9YZYIwXIhemH_p1tM, reason: not valid java name */
    public static /* synthetic */ void m4994$r8$lambda$9938PNrTm9YZYIwXIhemH_p1tM(TelegramMediaSession r0, int r1, int r2, Object[] r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$DeReF65Eo6na__3QKRxJynoL1y8(HashMap r2, Long r3, Long r4) {
        Integer r0 = (Integer) r2.get(r3);
        Integer r22 = (Integer) r2.get(r4);
        if (r0 != null) goto L7;
        if (r22 != null) goto L7;
        return Long.compare(r3.longValue(), r4.longValue());
    L7:
        if (r0 != null) goto L10;
        return 1;
    L10:
        if (r22 != null) goto L14;
        return -1;
    L14:
        return Integer.compare(r0.intValue(), r22.intValue());
    }

    public static /* synthetic */ void $r8$lambda$HqFyU4OGTLMS_RPZKDAAjxLer5w() {
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        if (r0 == null) goto L6;
        NotificationCenter.getInstance(r0.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(r0.getId())});
        return;
    }

    /* renamed from: $r8$lambda$J62UF-LjBTJiTLF9VrvaZ0hzzAk, reason: not valid java name */
    public static /* synthetic */ void m4995$r8$lambda$J62UFLjBTJiTLF9VrvaZ0hzzAk(TelegramMediaSession r0, int r1, long r2, ArrayList r4, ArrayList r5) {
        r0.lambda$loadMusicForDialog$7(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ckZaVMev8liDDqTbcNodSJ4rQ0M(TelegramMediaSession r0, MessagesStorage r1, long r2, int r4) {
        r0.lambda$loadMusicForDialog$8(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$n-6arz7B2lQmEQiBjxjPjVnaB40, reason: not valid java name */
    public static /* synthetic */ void m4996$r8$lambda$n6arz7B2lQmEQiBjxjPjVnaB40(TelegramMediaSession r0, int r1, ArrayList r2, LongSparseArray r3, LongSparseArray r4) {
        r0.lambda$loadChats$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$pad2mHyQ_qUylk4z3JBvyzYQelM(Runnable r0, List r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$uX68mE44j3LjHG4Sz5xlMtMIW2g(TelegramMediaSession r0, BrowseChildrenCallback r1, String r2) {
        r0.lambda$loadBrowseChildren$4(r1, r2);
    }

    /* renamed from: -$$Nest$fgetchats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m4997$$Nest$fgetchats(TelegramMediaSession r0) {
        return r0.chats;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4998$$Nest$fgetcurrentAccount(TelegramMediaSession r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdialogs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m4999$$Nest$fgetdialogs(TelegramMediaSession r0) {
        return r0.dialogs;
    }

    /* renamed from: -$$Nest$fgetlastSelectedDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5000$$Nest$fgetlastSelectedDialog(TelegramMediaSession r2) {
        return r2.lastSelectedDialog;
    }

    /* renamed from: -$$Nest$fgetmusicObjects, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m5001$$Nest$fgetmusicObjects(TelegramMediaSession r0) {
        return r0.musicObjects;
    }

    /* renamed from: -$$Nest$fgetmusicQueues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m5002$$Nest$fgetmusicQueues(TelegramMediaSession r0) {
        return r0.musicQueues;
    }

    /* renamed from: -$$Nest$fgetsession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaSessionCompat m5003$$Nest$fgetsession(TelegramMediaSession r0) {
        return r0.session;
    }

    /* renamed from: -$$Nest$fgetusers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m5004$$Nest$fgetusers(TelegramMediaSession r0) {
        return r0.users;
    }

    /* renamed from: -$$Nest$fputlastSelectedDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5005$$Nest$fputlastSelectedDialog(TelegramMediaSession r0, long r1) {
        r0.lastSelectedDialog = r1;
    }

    private TelegramMediaSession(Context r7) {
        this.dialogs = new ArrayList();
        this.users = new LongSparseArray();
        this.chats = new LongSparseArray();
        this.musicObjects = new LongSparseArray();
        this.musicQueues = new LongSparseArray();
        this.pendingMusicLoads = new LongSparseArray();
        this.pendingBrowseRequests = new ArrayList();
        this.appContext = r7;
        int r0 = UserConfig.selectedAccount;
        this.currentAccount = r0;
        this.lastSelectedDialog = AndroidUtilities.getPrefIntOrLong(MessagesController.getNotificationsSettings(r0), "auto_lastSelectedDialog", 0);
        MediaSessionCompat r02 = new MediaSessionCompat(r7, SESSION_TAG);
        this.session = r02;
        r02.setFlags(3);
        r02.setCallback(new SessionCallback(this, null));
        r02.setSessionActivity(PendingIntent.getActivity(r7, 99, new Intent(r7, LaunchActivity.class), 167772160));
        Bundle r72 = new Bundle();
        r72.putBoolean(SLOT_RESERVATION_QUEUE, true);
        r72.putBoolean(SLOT_RESERVATION_SKIP_TO_PREV, true);
        r72.putBoolean(SLOT_RESERVATION_SKIP_TO_NEXT, true);
        r02.setExtras(r72);
        r02.setActive(true);
        r02.setPlaybackState(new PlaybackStateCompat.Builder().setState(0, 0, 1.0f).setActions(getAvailableActions()).build());
        updateRepeatMode();
        updateShuffleMode();
        NotificationCenter.getGlobalInstance().addObserver(new TelegramMediaSession$$ExternalSyntheticLambda3(this), NotificationCenter.activeAccountChanged);
    }

    private void applyQueueFor(long r5) {
        if (r5 == 0) goto L27;
        ArrayList<MessageObject> r0 = this.musicObjects.get(r5);
        ArrayList<MediaSessionCompat.QueueItem> r1 = this.musicQueues.get(r5);
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.isEmpty() == true) goto L26;
        if (r1 == null) goto L28;
        this.session.setQueue(r1);
        if (DialogObject.isUserDialog(r5) == false) goto L18;
        TLRPC.User r52 = this.users.get(r5);
        MediaSessionCompat r6 = this.session;
        if (r52 == null) goto L16;
        String r53 = ContactsController.formatName(r52.first_name, r52.last_name);
    L17:
        r6.setQueueTitle(r53);
    L23:
        MessageObject r54 = r0.get(0);
        MediaMetadataCompat.Builder r55 = new MediaMetadataCompat.Builder().putLong(MediaItemMetadata.KEY_DURATION, (long) (r54.getDuration() * 1000.0d)).putString(MediaItemMetadata.KEY_ARTIST, r54.getMusicAuthor()).putString(MediaItemMetadata.KEY_TITLE, r54.getMusicTitle());
        this.session.setMetadata(r55.build());
        return;
    L16:
        r53 = "DELETED USER";
        goto L17
    L18:
        TLRPC.Chat r56 = this.chats.get(-r5);
        MediaSessionCompat r62 = this.session;
        if (r56 == null) goto L21;
        String r57 = r56.title;
    L22:
        r62.setQueueTitle(r57);
        goto L23
    L21:
        r57 = "DELETED CHAT";
        goto L22
    L28:
        return;
    L26:
        return;
    }

    private Bitmap createRoundBitmap(File r7) {
        BitmapFactory.Options r0 = new BitmapFactory.Options();     // Catch: Throwable -> L9
        r0.inSampleSize = 2;     // Catch: Throwable -> L9
        Bitmap r72 = BitmapFactory.decodeFile(r7.toString(), r0);     // Catch: Throwable -> L9
        if (r72 == null) goto L15;
        Bitmap r02 = Bitmap.createBitmap(r72.getWidth(), r72.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L9
        r02.eraseColor(0);     // Catch: Throwable -> L9
        Canvas r1 = new Canvas(r02);     // Catch: Throwable -> L9
        Shader.TileMode r3 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L9
        BitmapShader r2 = new BitmapShader(r72, r3, r3);     // Catch: Throwable -> L9
        if (this.roundPaint != null) goto L7;
        this.roundPaint = new Paint(1);     // Catch: Throwable -> L9
        this.bitmapRect = new RectF();     // Catch: Throwable -> L9
    L7:
        this.roundPaint.setShader(r2);     // Catch: Throwable -> L9
        this.bitmapRect.set(0.0f, 0.0f, r72.getWidth(), r72.getHeight());     // Catch: Throwable -> L9
        r1.drawRoundRect(this.bitmapRect, r72.getWidth(), r72.getHeight(), this.roundPaint);     // Catch: Throwable -> L9
        return r02;
    L15:
        return null;
    L9:
        th = move-exception;
        FileLog.e(th);
        return null;
    }

    private long getDialogIdFromMediaId(String r3) {
        if (r3 != null) goto L5;
    L12:
        return 0;
    L5:
        if (r3.startsWith(MEDIA_ID_CHAT_PREFIX) == false) goto L12;
        return Long.parseLong(r3.substring(7));
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    public static TelegramMediaSession getInstance(Context r2) {
        if (instance != null) goto L16;
        monitor-enter(TelegramMediaSession.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (instance != null) goto L11;
        instance = new TelegramMediaSession(r2.getApplicationContext());     // Catch: Throwable -> L9
    L11:
        monitor-exit(TelegramMediaSession.class);     // Catch: Throwable -> L9
    L16:
        return instance;
    }

    private /* synthetic */ void lambda$loadBrowseChildren$4(BrowseChildrenCallback r1, String r2) {
        r1.onResult(loadChildrenSync(r2));
    }

    private /* synthetic */ void lambda$loadChats$5(int r5, ArrayList r6, LongSparseArray r7, LongSparseArray r8) {
        if (r5 != this.currentAccount) goto L22;
        this.dialogs.clear();
        this.dialogs.addAll(r6);
        this.users.clear();
        int r52 = 0;
        int r62 = 0;
    L7:
        if (r62 >= r7.size()) goto L9;
        this.users.put(r7.keyAt(r62), (TLRPC.User) r7.valueAt(r62));
        r62 = r62 + 1;
        goto L7
    L9:
        this.chats.clear();
        int r63 = 0;
    L11:
        if (r63 >= r8.size()) goto L13;
        this.chats.put(r8.keyAt(r63), (TLRPC.Chat) r8.valueAt(r63));
        r63 = r63 + 1;
        goto L11
    L13:
        this.chatsLoaded = true;
        this.loadingChats = false;
        if (this.lastSelectedDialog == 0) goto L16;
    L18:
        ArrayList r64 = new ArrayList(this.pendingBrowseRequests);
        this.pendingBrowseRequests.clear();
    L20:
        if (r52 >= r64.size()) goto L26;
        PendingBrowseRequest r72 = (PendingBrowseRequest) r64.get(r52);
        loadBrowseChildren(r72.parentMediaId, r72.callback);
        r52 = r52 + 1;
        goto L20
    L26:
        return;
    L16:
        if (this.dialogs.isEmpty() == true) goto L18;
        this.lastSelectedDialog = this.dialogs.get(0).longValue();
        goto L18
    }

    private /* synthetic */ void lambda$loadChats$6(MessagesStorage r12, int r13) {
        ArrayList r3 = new ArrayList();
        LongSparseArray r4 = new LongSparseArray();
        LongSparseArray r5 = new LongSparseArray();
        ArrayList<Long> r0 = new ArrayList();     // Catch: Exception -> L12
        ArrayList r1 = new ArrayList();     // Catch: Exception -> L12
        int r7 = 0;
        SQLiteCursor r2 = r12.getDatabase().queryFinalized(String.format(Locale.US, "SELECT DISTINCT uid FROM media_v4 WHERE uid != 0 AND mid > 0 AND type = %d", new Object[]{4}), new Object[0]);     // Catch: Exception -> L12
    L5:
        if (r2.next() == false) goto L15;
        long r8 = r2.longValue(0);     // Catch: Exception -> L12
        if (DialogObject.isEncryptedDialog(r8) == true) goto L5;
        r3.add(Long.valueOf(r8));     // Catch: Exception -> L12
        if (DialogObject.isUserDialog(r8) == true) goto L11;
        r1.add(Long.valueOf(-r8));     // Catch: Exception -> L12
        goto L5
    L11:
        r0.add(Long.valueOf(r8));     // Catch: Exception -> L12
        goto L5
    L15:
        r2.dispose();     // Catch: Exception -> L12
        if (r0.isEmpty() == true) goto L21;
        ArrayList<TLRPC.User> r22 = new ArrayList();     // Catch: Exception -> L12
        r12.getUsersInternal(r0, r22);     // Catch: Exception -> L12
        int r02 = r22.size();     // Catch: Exception -> L12
        int r6 = 0;
    L18:
        if (r6 >= r02) goto L21;
        TLRPC.User r82 = r22.get(r6);     // Catch: Exception -> L12
        r6 = r6 + 1;     // Catch: Exception -> L12
        TLRPC.User r83 = r82;     // Catch: Exception -> L12
        r4.put(r83.id, r83);     // Catch: Exception -> L12
    L21:
        if (r1.isEmpty() == true) goto L27;
        ArrayList<TLRPC.Chat> r03 = new ArrayList();     // Catch: Exception -> L12
        r12.getChatsInternal(TextUtils.join(",", r1), r03);     // Catch: Exception -> L12
        int r122 = r03.size();     // Catch: Exception -> L12
    L23:
        if (r7 >= r122) goto L27;
        TLRPC.Chat r14 = r03.get(r7);     // Catch: Exception -> L12
        r7 = r7 + 1;     // Catch: Exception -> L12
        TLRPC.Chat r15 = r14;     // Catch: Exception -> L12
        r5.put(r15.id, r15);     // Catch: Exception -> L12
    L27:
        AndroidUtilities.runOnUIThread(new TelegramMediaSession$$ExternalSyntheticLambda5(this, r13, r3, r4, r5));
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L27
    }

    private /* synthetic */ void lambda$loadMusicForDialog$7(int r2, long r3, ArrayList r5, ArrayList r6) {
        if (r2 != this.currentAccount) goto L15;
        this.musicObjects.put(r3, r5);
        this.musicQueues.put(r3, r6);
        ArrayList<Runnable> r22 = this.pendingMusicLoads.get(r3);
        this.pendingMusicLoads.remove(r3);
        if (r3 != this.lastSelectedDialog) goto L8;
        applyQueueFor(r3);
    L8:
        if (r22 == null) goto L13;
        int r1 = 0;
    L11:
        if (r1 >= r22.size()) goto L16;
        r22.get(r1).run();
        r1 = r1 + 1;
        goto L11
    L16:
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$loadMusicForDialog$8(MessagesStorage r8, long r9, int r11) {
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        SQLiteCursor r82 = r8.getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, mid FROM media_v4 WHERE uid = %d AND mid > 0 AND type = %d ORDER BY date DESC, mid DESC", new Object[]{Long.valueOf(r9), 4}), new Object[0]);     // Catch: Exception -> L13
    L5:
        if (r82.next() == false) goto L15;
        NativeByteBuffer r0 = r82.byteBufferValue(0);     // Catch: Exception -> L13
        if (r0 == null) goto L5;
        TLRPC.Message r2 = TLRPC.Message.TLdeserialize(r0, r0.readInt32(false), false);     // Catch: Exception -> L13
        r2.readAttachPath(r0, UserConfig.getInstance(r11).clientUserId);     // Catch: Exception -> L13
        r0.reuse();     // Catch: Exception -> L13
        if (MessageObject.isMusicMessage(r2) == false) goto L5;
        r2.id = r82.intValue(1);     // Catch: Exception -> L13
        r2.dialog_id = r9;     // Catch: Exception -> L13
        MessageObject r3 = new MessageObject(r11, r2, false, true);     // Catch: Exception -> L13
        r5.add(r3);     // Catch: Exception -> L13
        r6.add(new MediaSessionCompat.QueueItem(new MediaDescriptionCompat.Builder().setMediaId(r9 + "_" + r5.size()).setTitle(r3.getMusicTitle()).setSubtitle(r3.getMusicAuthor()).build(), r6.size()));     // Catch: Exception -> L13
        goto L5
    L15:
        r82.dispose();     // Catch: Exception -> L13
        Collections.reverse(r5);     // Catch: Exception -> L13
        Collections.reverse(r6);     // Catch: Exception -> L13
    L18:
        AndroidUtilities.runOnUIThread(new TelegramMediaSession$$ExternalSyntheticLambda9(this, r11, r9, r5, r6));
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L18
    }

    private /* synthetic */ void lambda$new$0(int r1, int r2, Object[] r3) {
        if (r1 != NotificationCenter.activeAccountChanged) goto L6;
        AndroidUtilities.runOnUIThread(new TelegramMediaSession$$ExternalSyntheticLambda1(this));
        return;
    }

    private void loadChats() {
        if (this.loadingChats == false) goto L5;
        return;
    L5:
        this.loadingChats = true;
        int r0 = this.currentAccount;
        MessagesStorage r1 = MessagesStorage.getInstance(r0);
        r1.getStorageQueue().postRunnable(new TelegramMediaSession$$ExternalSyntheticLambda8(this, r1, r0));
    }

    private List<MediaBrowser.MediaItem> loadChildrenSync(String r8) {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
        if (MEDIA_ID_ROOT.equals(r8) == true) goto L5;
        if (r8 != null) goto L36;
    L46:
        return r0;
    L36:
        if (r8.startsWith(MEDIA_ID_CHAT_PREFIX) == false) goto L46;
        long r3 = Long.parseLong(r8.replace(MEDIA_ID_CHAT_PREFIX, _UrlKt.FRAGMENT_ENCODE_SET));     // Catch: Exception -> L39
    L41:
        ArrayList<MessageObject> r7 = this.musicObjects.get(r3);
        if (r7 == null) goto L46;
    L44:
        if (r2 >= r7.size()) goto L46;
        MessageObject r82 = r7.get(r2);
        MediaDescription.Builder r1 = new MediaDescription.Builder().setMediaId(r3 + "_" + r2);
        r1.setTitle(r82.getMusicTitle());
        r1.setSubtitle(r82.getMusicAuthor());
        r0.add(new MediaBrowser.MediaItem(r1.build(), 2));
        r2 = r2 + 1;
    L39:
        e = move-exception;
        FileLog.e(e);
        r3 = 0;
    L5:
        if (r2 >= this.dialogs.size()) goto L46;
        long r4 = this.dialogs.get(r2).longValue();
        MediaDescription.Builder r83 = new MediaDescription.Builder().setMediaId(MEDIA_ID_CHAT_PREFIX + r4);
        Bitmap r6 = null;
        if (DialogObject.isUserDialog(r4) == false) goto L16;
        TLRPC.User r12 = this.users.get(r4);
        if (r12 == null) goto L15;
        r83.setTitle(ContactsController.formatName(r12.first_name, r12.last_name));
        TLRPC.UserProfilePhoto r13 = r12.photo;
        if (r13 == null) goto L24;
        TLRPC.FileLocation r14 = r13.photo_small;
        if ((r14 instanceof TLRPC.TL_fileLocationUnavailable) == true) goto L24;
    L26:
        if (r14 == null) goto L30;
        r6 = createRoundBitmap(FileLoader.getInstance(this.currentAccount).getPathToAttach(r14, true));
        if (r6 == null) goto L30;
        r83.setIconBitmap(r6);
    L30:
        if (r14 == null) goto L32;
        if (r6 == null) goto L32;
    L33:
        r0.add(new MediaBrowser.MediaItem(r83.build(), 1));
        r2 = r2 + 1;
    L32:
        r83.setIconUri(Uri.parse("android.resource://" + this.appContext.getPackageName() + "/drawable/contact_blue"));
    L24:
        r14 = null;
        goto L26
    L15:
        r83.setTitle("DELETED USER");
        goto L24
    L16:
        TLRPC.Chat r15 = this.chats.get(-r4);
        if (r15 == null) goto L23;
        r83.setTitle(r15.title);
        TLRPC.ChatPhoto r16 = r15.photo;
        if (r16 == null) goto L24;
        r14 = r16.photo_small;
        if ((r14 instanceof TLRPC.TL_fileLocationUnavailable) == true) goto L24;
    L23:
        r83.setTitle("DELETED CHAT");
        goto L24
    }

    private void onAccountSwitched() {
        int r0 = UserConfig.selectedAccount;
        this.currentAccount = r0;
        this.lastSelectedDialog = AndroidUtilities.getPrefIntOrLong(MessagesController.getNotificationsSettings(r0), "auto_lastSelectedDialog", 0);
        this.chatsLoaded = false;
        this.loadingChats = false;
        this.dialogs.clear();
        this.users.clear();
        this.chats.clear();
        this.musicObjects.clear();
        this.musicQueues.clear();
        this.pendingMusicLoads.clear();
        this.session.setQueue(null);     // Catch: Throwable -> L5
        this.session.setQueueTitle(null);     // Catch: Throwable -> L5
        return;
    }

    public static TelegramMediaSession peekInstance() {
        return instance;
    }

    public Bundle buildRootHints() {
        Bundle r3 = new Bundle();
        r3.putBoolean(CONTENT_STYLE_SUPPORTED, true);
        r3.putInt(CONTENT_STYLE_BROWSABLE_HINT, 2);
        r3.putInt(CONTENT_STYLE_PLAYABLE_HINT, 1);
        return r3;
    }

    public void clearPlaybackState() {
        this.session.setPlaybackState(new PlaybackStateCompat.Builder().setState(0, 0, 1.0f).setActions(0).build());     // Catch: Throwable -> L4
        this.session.setMetadata(null);     // Catch: Throwable -> L4
        this.session.setActive(false);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        FileLog.e(th);
    }

    public void enablePlaybackMirroring() {
        if (this.mirrorPlayback == false) goto L5;
        return;
    L5:
        this.mirrorPlayback = true;
        AndroidUtilities.runOnUIThread(new TelegramMediaSession$$ExternalSyntheticLambda4());
    }

    public void ensureLoaded(Runnable r2) {
        if (this.chatsLoaded == false) goto L7;
        if (r2 == null) goto L9;
        AndroidUtilities.runOnUIThread(r2);
        return;
    L9:
        return;
    L7:
        loadBrowseChildren(MEDIA_ID_ROOT, new TelegramMediaSession$$ExternalSyntheticLambda7(r2));
    }

    public long getAvailableActions() {
        MessageObject r4 = MediaController.getInstance().getPlayingMessageObject();
        long r0 = 2477828;
        if (r4 != null) goto L5;
        return 2477828;
    L5:
        if (MediaController.getInstance().isMessagePaused() == true) goto L8;
        r0 = 2477830;
    L8:
        if (r4.isMusic() == true) goto L10;
        return r0;
    L10:
        return r0 | 48;
    }

    public int getCurrentAccount() {
        return this.currentAccount;
    }

    public MediaSession.Token getFrameworkSessionToken() {
        return (MediaSession.Token) this.session.getSessionToken().getToken();
    }

    public TLRPC.Chat getMusicChat(long r1) {
        return this.chats.get(r1);
    }

    public ArrayList<Long> getMusicDialogs() {
        return this.dialogs;
    }

    public ArrayList<Long> getMusicDialogsSortedByVisibleOrder() {
        ArrayList<Long> r0 = new ArrayList(this.dialogs);
        ArrayList<TLRPC.Dialog> r5 = MessagesController.getInstance(this.currentAccount).getAllDialogs();
        HashMap r1 = new HashMap();
        int r2 = 0;
    L4:
        if (r2 >= r5.size()) goto L9;
        TLRPC.Dialog r3 = r5.get(r2);
        if (r3 == null) goto L8;
        r1.put(Long.valueOf(r3.id), Integer.valueOf(r2));
    L8:
        r2 = r2 + 1;
        goto L4
    L9:
        Collections.sort(r0, new TelegramMediaSession$$ExternalSyntheticLambda0(r1));
        return r0;
    }

    public ArrayList<MessageObject> getMusicMessages(long r1) {
        return this.musicObjects.get(r1);
    }

    public TLRPC.User getMusicUser(long r1) {
        return this.users.get(r1);
    }

    public Bitmap getRoundedAvatar(File r1) {
        return createRoundBitmap(r1);
    }

    public MediaSessionCompat getSession() {
        return this.session;
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.session.getSessionToken();
    }

    public boolean isChatsLoaded() {
        return this.chatsLoaded;
    }

    public boolean isPasscodeLocked() {
        int r4 = (int) (SystemClock.elapsedRealtime() / 1000);
        if (SharedConfig.passcodeHash.length() > 0) goto L5;
        return false;
    L5:
        if (SharedConfig.appLocked == false) goto L7;
        return true;
    L7:
        if (SharedConfig.autoLockIn == 0) goto L13;
        int r0 = SharedConfig.lastPauseTime;
        if (r0 == 0) goto L13;
        if ((r0 + SharedConfig.autoLockIn) > r4) goto L13;
        return true;
    L13:
        if ((r4 + 5) >= SharedConfig.lastPauseTime) goto L20;
        return true;
    L20:
        return false;
    }

    public void loadBrowseChildren(String r5, BrowseChildrenCallback r6) {
        if (this.chatsLoaded == true) goto L6;
        this.pendingBrowseRequests.add(new PendingBrowseRequest(r5, r6));
        loadChats();
        return;
    L6:
        long r0 = getDialogIdFromMediaId(r5);
        if (r0 != 0) goto L9;
    L12:
        r6.onResult(loadChildrenSync(r5));
        return;
    L9:
        if (this.musicObjects.get(r0) != null) goto L12;
        loadMusicForDialog(r0, new TelegramMediaSession$$ExternalSyntheticLambda2(this, r6, r5));
    }

    public void loadMusicForDialog(long r8, Runnable r10) {
        ArrayList<Runnable> r0 = this.pendingMusicLoads.get(r8);
        if (r0 == null) goto L7;
        if (r10 == null) goto L12;
        r0.add(r10);
        return;
    L12:
        return;
    L7:
        ArrayList<Runnable> r02 = new ArrayList();
        if (r10 == null) goto L10;
        r02.add(r10);
    L10:
        this.pendingMusicLoads.put(r8, r02);
        int r6 = this.currentAccount;
        MessagesStorage r3 = MessagesStorage.getInstance(r6);
        r3.getStorageQueue().postRunnable(new TelegramMediaSession$$ExternalSyntheticLambda6(this, r3, r8, r6));
    }

    public void publishMetadata(MediaMetadataCompat r2) {
        if (this.mirrorPlayback == true) goto L5;
        return;
    L5:
        this.session.setMetadata(r2);
    }

    public void publishMetadata(MessageObject r6, AudioInfo r7, Bitmap r8) {
        if (r6 != null) goto L4;
        return;
    L4:
        MediaMetadataCompat.Builder r0 = new MediaMetadataCompat.Builder().putString(MediaItemMetadata.KEY_ALBUM_ARTIST, r6.getMusicAuthor()).putString(MediaItemMetadata.KEY_ARTIST, r6.getMusicAuthor()).putLong(MediaItemMetadata.KEY_DURATION, (long) (r6.getDuration() * 1000.0d)).putString(MediaItemMetadata.KEY_TITLE, r6.getMusicTitle());
        if (r7 != null) goto L7;
    L9:
        String r62 = null;
    L10:
        MediaMetadataCompat.Builder r63 = r0.putString("android.media.metadata.ALBUM", r62);
        if (r8 != null) goto L13;
    L15:
        this.session.setMetadata(r63.build());
        return;
    L13:
        if (r8.isRecycled() == true) goto L15;
        r63.putBitmap("android.media.metadata.ALBUM_ART", r8);
        goto L15
    L7:
        if (r6.isMusic() == false) goto L9;
        r62 = r7.getAlbum();
        goto L10
    }

    public void publishPlaybackState(PlaybackStateCompat r5) {
        if (this.mirrorPlayback == true) goto L6;
        return;
    L6:
        if (this.session.isActive() == true) goto L8;
        this.session.setActive(true);
    L8:
        MediaSessionCompat r0 = this.session;
        PlaybackStateCompat.Builder r1 = new PlaybackStateCompat.Builder(r5);
        long r2 = r5.getActions();
        r0.setPlaybackState(r1.setActions(getAvailableActions() | r2).build());
    }

    public void release() {
        MediaSessionCompat r0 = this.session;
        if (r0 == null) goto L6;
        r0.setCallback(null);
        this.session.release();
        return;
    }

    public void updateRepeatMode() {
        int r0 = SharedConfig.repeatMode;
        int r1 = 2;
        if (r0 == 1) goto L7;
        if (r0 == 2) goto L6;
        r1 = 0;
        goto L7
    L6:
        r1 = 1;
    L7:
        this.session.setRepeatMode(r1);
    }

    public void updateShuffleMode() {
        this.session.setShuffleMode(SharedConfig.shuffleMusic ? 1 : 0);
    }
}
