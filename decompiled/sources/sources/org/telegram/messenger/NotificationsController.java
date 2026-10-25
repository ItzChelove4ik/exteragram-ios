package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.collection.LongSparseArray;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;
import androidx.core.content.FileProvider;
import androidx.core.content.LocusIdCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.IconCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.notifications.AccountNotifications;
import com.exteragram.messenger.utils.AppUtils;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.messenger.voip.VoIPGroupNotification;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PopupNotificationActivity;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes.dex */
public class NotificationsController extends BaseController implements NotificationCenter.NotificationCenterDelegate {
    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
    private static volatile NotificationsController[] Instance = null;
    public static String OTHER_NOTIFICATIONS_CHANNEL = null;
    public static final int SETTING_MUTE_2_DAYS = 2;
    public static final int SETTING_MUTE_8_HOURS = 1;
    public static final int SETTING_MUTE_CUSTOM = 5;
    public static final int SETTING_MUTE_FOREVER = 3;
    public static final int SETTING_MUTE_HOUR = 0;
    public static final int SETTING_MUTE_UNMUTE = 4;
    public static final int SETTING_SOUND_OFF = 1;
    public static final int SETTING_SOUND_ON = 0;
    public static final int TYPE_CHANNEL = 2;
    public static final int TYPE_GROUP = 0;
    public static final int TYPE_PRIVATE = 1;
    public static final int TYPE_REACTIONS_MESSAGES = 4;
    public static final int TYPE_REACTIONS_STORIES = 5;
    public static final int TYPE_STORIES = 3;
    protected static AudioManager audioManager;
    public static long globalSecretChatId;
    private static final Object[] lockObjects = null;
    private static NotificationManagerCompat notificationManager;
    private static final DispatchQueue notificationsQueue = null;
    private static final LongSparseArray<String> sharedPrefCachedKeys = null;
    private static NotificationManager systemNotificationManager;
    private AlarmManager alarmManager;
    private boolean channelGroupsCreated;
    private Runnable checkStoryPushesRunnable;
    private final ArrayList<MessageObject> delayedPushMessages;
    NotificationsSettingsFacade dialogsNotificationsFacade;
    private final LongSparseArray<MessageObject> fcmRandomMessagesDict;
    private Boolean groupsCreated;
    private boolean inChatSoundEnabled;
    private int lastBadgeCount;
    private int lastButtonId;
    public long lastNotificationChannelCreateTime;
    private int lastOnlineFromOtherDevice;
    private long lastSoundOutPlay;
    private long lastSoundPlay;
    private final LongSparseArray<Integer> lastWearNotifiedMessageId;
    private String launcherClassName;
    private SpoilerEffect mediaSpoilerEffect;
    private Runnable notificationDelayRunnable;
    private PowerManager.WakeLock notificationDelayWakelock;
    private String notificationGroup;
    private int notificationId;
    private boolean notifyCheck;
    private long openedDialogId;
    private final HashSet<Long> openedInBubbleDialogs;
    private long openedTopicId;
    private final HashSet<String> pendingVoiceLoads;
    private int personalCount;
    public final ArrayList<MessageObject> popupMessages;
    public ArrayList<MessageObject> popupReplyMessages;
    private final LongSparseArray<Integer> pushDialogs;
    private final LongSparseArray<Integer> pushDialogsOverrideMention;
    private final ArrayList<MessageObject> pushMessages;
    private final LongSparseArray<SparseArray<MessageObject>> pushMessagesDict;
    public boolean showBadgeMessages;
    public boolean showBadgeMuted;
    public boolean showBadgeNumber;
    private final LongSparseArray<Point> smartNotificationsDialogs;
    private int soundIn;
    private boolean soundInLoaded;
    private int soundOut;
    private boolean soundOutLoaded;
    private SoundPool soundPool;
    private int soundRecord;
    private boolean soundRecordLoaded;
    char[] spoilerChars;
    private final ArrayList<StoryNotification> storyPushMessages;
    private final LongSparseArray<StoryNotification> storyPushMessagesDict;
    private int total_unread_count;
    private final LongSparseArray<Integer> wearNotificationsIds;

    /* renamed from: org.telegram.messenger.NotificationsController$1NotificationHolder, reason: invalid class name */
    public class C1NotificationHolder {
        TLRPC.Chat chat;
        long dialogId;
        int id;
        String name;
        NotificationCompat.Builder notification;
        boolean story;
        final /* synthetic */ NotificationsController this$0;
        long topicId;
        TLRPC.User user;
        final /* synthetic */ String val$chatName;
        final /* synthetic */ int val$chatType;
        final /* synthetic */ int val$importance;
        final /* synthetic */ boolean val$isDefault;
        final /* synthetic */ boolean val$isInApp;
        final /* synthetic */ boolean val$isSilent;
        final /* synthetic */ long val$lastTopicId;
        final /* synthetic */ int val$ledColor;
        final /* synthetic */ Uri val$sound;
        final /* synthetic */ long[] val$vibrationPattern;

        public C1NotificationHolder(NotificationsController r1, int r2, long r3, boolean r5, long r6, String r8, TLRPC.User r9, TLRPC.Chat r10, NotificationCompat.Builder r11, long r12, String r14, long[] r15, int r16, Uri r17, int r18, boolean r19, boolean r20, boolean r21, int r22) {
            this.this$0 = r1;
            this.val$lastTopicId = r12;
            this.val$chatName = r14;
            this.val$vibrationPattern = r15;
            this.val$ledColor = r16;
            this.val$sound = r17;
            this.val$importance = r18;
            this.val$isDefault = r19;
            this.val$isInApp = r20;
            this.val$isSilent = r21;
            this.val$chatType = r22;
            this.id = r2;
            this.name = r8;
            this.user = r9;
            this.chat = r10;
            this.notification = r11;
            this.dialogId = r3;
            this.story = r5;
            this.topicId = r6;
        }

        public void call() {
            if (BuildVars.LOGS_ENABLED == false) goto L10;
            FileLog.w("show dialog notification with id " + this.id + " " + this.dialogId + " user=" + this.user + " chat=" + this.chat);
        L10:
            NotificationsController.m4809$$Nest$sfgetnotificationManager().notify(this.id, this.notification.build());     // Catch: SecurityException -> L7
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
            NotificationsController.m4808$$Nest$mresetNotificationSound(this.this$0, this.notification, this.dialogId, this.val$lastTopicId, this.val$chatName, this.val$vibrationPattern, this.val$ledColor, this.val$sound, this.val$importance, this.val$isDefault, this.val$isInApp, this.val$isSilent, this.val$chatType);
        }
    }

    public static class DialogKey {
        final long dialogId;
        final boolean story;
        final long topicId;

        private DialogKey(long r1, long r3, boolean r5) {
            this.dialogId = r1;
            this.topicId = r3;
            this.story = r5;
        }

        public /* synthetic */ DialogKey(long r1, long r3, boolean r5, NotificationsControllerIA r6) {
            this(r1, r3, r5);
        }
    }

    public static class DialogNotificationSettings {
        int chatType;
        int importance;
        boolean isDefault;
        boolean isInApp;
        boolean isInternalSoundFile;
        int ledColor;
        boolean notifyDisabled;
        String soundPath;
        int vibrate;

        private DialogNotificationSettings() {
        }

        public /* synthetic */ DialogNotificationSettings(NotificationsControllerIA r1) {
            this();
        }
    }

    public static class StoryNotification {
        public long date;
        final HashMap<Integer, Pair<Long, Long>> dateByIds;
        final long dialogId;
        boolean hidden;
        String localName;

        public StoryNotification(long r12, String r14, int r15, long r16) {
            this(r12, r14, r15, r16, r16 + DurationKt.MILLIS_IN_DAY);
        }

        public StoryNotification(long r2, String r4, int r5, long r6, long r8) {
            HashMap<Integer, Pair<Long, Long>> r0 = new HashMap();
            this.dateByIds = r0;
            this.dialogId = r2;
            this.localName = r4;
            r0.put(Integer.valueOf(r5), new Pair(Long.valueOf(r6), Long.valueOf(r8)));
            this.date = r6;
        }

        public long getLeastDate() {
            Iterator<Pair<Long, Long>> r7 = this.dateByIds.values().iterator();
            long r2 = -1;
        L4:
            if (r7.hasNext() == false) goto L10;
            Pair<Long, Long> r4 = r7.next();
            if (r2 == (-1)) goto L9;
            if (r2 <= ((Long) r4.first).longValue()) goto L4;
        L9:
            r2 = ((Long) r4.first).longValue();
            goto L4
        L10:
            return r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$0eVyoh8JHq7LPwIOUYAEtiamEhc(NotificationsController r0) {
        r0.lambda$updateBadge$34();
    }

    public static /* synthetic */ void $r8$lambda$0yRVnGEDHblOdXvVn_zMBSQ7X7Y(NotificationsController r0, ArrayList r1) {
        r0.lambda$removeDeletedMessagesFromNotifications$8(r1);
    }

    /* renamed from: $r8$lambda$1cjyZx4DC-zMYyZWaNGHfqA386U, reason: not valid java name */
    public static /* synthetic */ void m4789$r8$lambda$1cjyZx4DCzMYyZWaNGHfqA386U(NotificationsController r0, LongSparseArray r1) {
        r0.lambda$processEditedMessages$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$1ieJFVCeWTJ4ArYmncvaNwbqISE(NotificationsController r0, long r1, Consumer r3) {
        r0.lambda$loadTopicsNotificationsExceptions$54(r1, r3);
    }

    /* renamed from: $r8$lambda$1t1axbSYGQIU_GMVkHn-zrj3Llc, reason: not valid java name */
    public static /* synthetic */ void m4790$r8$lambda$1t1axbSYGQIU_GMVkHnzrj3Llc(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$54DFX0xEqcyXIHY5Q9Y_Z8LMIRk(NotificationsController r0, int r1) {
        r0.lambda$removeDeletedMessagesFromNotifications$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$5IrtwtsnHKWqAtj_SsvxNGhvSVo(NotificationsController r0, int r1, int r2) {
        r0.lambda$deleteNotificationChannelGlobal$43(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$5_XUFtYnDbWNs14Fm4xg9lNI3OQ(NotificationsController r0, ArrayList r1) {
        r0.lambda$processReadMessages$20(r1);
    }

    /* renamed from: $r8$lambda$6-5Vq6L5RugYNIf0rfxTUxyks5A, reason: not valid java name */
    public static /* synthetic */ void m4791$r8$lambda$65Vq6L5RugYNIf0rfxTUxyks5A(NotificationsController r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$7n_PLpM0jcPV0mVTzOdTr28W3To(NotificationsController r0, ArrayList r1, ArrayList r2, boolean r3, boolean r4, CountDownLatch r5) {
        r0.lambda$processNewMessages$27(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$7xcsR5KyqDw7U0XTi0H9Nk1Xb4U(NotificationsController r0, String r1) {
        r0.lambda$didReceivedNotification$38(r1);
    }

    /* renamed from: $r8$lambda$8h-_buoJHhCMpHqH072SpNn91ao, reason: not valid java name */
    public static /* synthetic */ void m4792$r8$lambda$8h_buoJHhCMpHqH072SpNn91ao(NotificationsController r0, int r1) {
        r0.lambda$processLoadedUnreadMessages$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$BAjRwY5KIbSfcncn4XbqDiD9T5c(NotificationsController r0, ArrayList r1, int r2) {
        r0.lambda$processNewMessages$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$DwBWBOA2LoV6UDkIGz7vFoH5uuU(NotificationsController r0, int r1) {
        r0.lambda$removeDeletedHisoryFromNotifications$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$FC_HwUM3QBEiCRgKZAe2XqW3hzc(SoundPool r7, int r8, int r9) {
        if (r9 == 0) goto L9;
        return;
    L9:
        r7.play(r8, 1.0f, 1.0f, 1, 0, 1.0f);     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }

    /* renamed from: $r8$lambda$FOk6qvBYfOBgje-mz3Y1XIWl4Ls, reason: not valid java name */
    public static /* synthetic */ void m4793$r8$lambda$FOk6qvBYfOBgjemz3Y1XIWl4Ls(NotificationsController r0, int r1) {
        r0.lambda$processNewMessages$26(r1);
    }

    /* renamed from: $r8$lambda$FdDeWXYrl7K-Sb_T1jQDAVk9qFk, reason: not valid java name */
    public static /* synthetic */ void m4794$r8$lambda$FdDeWXYrl7KSb_T1jQDAVk9qFk(NotificationsController r0) {
        r0.lambda$processIgnoreStoryReactions$18();
    }

    public static /* synthetic */ void $r8$lambda$FyZA8njft8Dl8DVMac9m6VUp8ZY(NotificationsController r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$GAoezEwADTtL1XUbi_ZMoEkd6Gg(NotificationsController r0) {
        r0.lambda$showNotifications$35();
    }

    /* renamed from: $r8$lambda$H_bZLJEQVx9OdWW6-ZrpVB2xjp0, reason: not valid java name */
    public static /* synthetic */ void m4795$r8$lambda$H_bZLJEQVx9OdWW6ZrpVB2xjp0(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$I7QnY782wkoATRAC-afFr1RyhlQ, reason: not valid java name */
    public static /* synthetic */ void m4796$r8$lambda$I7QnY782wkoATRACafFr1RyhlQ(NotificationsController r0, LongSparseArray r1, boolean r2, ArrayList r3) {
        r0.lambda$removeDeletedMessagesFromNotifications$10(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$ISWJjQv6CrJ1YUAm69e6C-5w56M, reason: not valid java name */
    public static /* synthetic */ void m4797$r8$lambda$ISWJjQv6CrJ1YUAm69e6C5w56M(NotificationsController r0, boolean r1, long r2) {
        r0.lambda$setOpenedInBubble$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JggQPIMskmf3i48K8lRecOuHDWQ(NotificationsController r0, int r1) {
        r0.lambda$processSeenStoryReactions$14(r1);
    }

    /* renamed from: $r8$lambda$KgyI3m-9QCFeEP60yCdo7e9LCQ8, reason: not valid java name */
    public static /* synthetic */ void m4798$r8$lambda$KgyI3m9QCFeEP60yCdo7e9LCQ8(NotificationsController r0, int r1) {
        r0.lambda$processDialogsUpdateRead$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$LakEptqhCv9OhMJsIqb7LZKKyO8(Consumer r0, HashSet r1) {
        if (r0 == null) goto L5;
        r0.accept(r1);
        return;
    }

    public static /* synthetic */ void $r8$lambda$LeGl5cNxWbGW3UxIo8OIIbIGrNU(NotificationsController r0, int r1) {
        r0.lambda$processNewMessages$24(r1);
    }

    /* renamed from: $r8$lambda$Lyc8B4MesOdNzsUhV18-u-PjO9M, reason: not valid java name */
    public static /* synthetic */ void m4799$r8$lambda$Lyc8B4MesOdNzsUhV18uPjO9M(NotificationsController r0) {
        r0.lambda$playInChatSound$40();
    }

    public static /* synthetic */ void $r8$lambda$MreB1MzJLP6Pl1UUGFmLVvK9Rkg(NotificationsController r0, LongSparseIntArray r1, ArrayList r2) {
        r0.lambda$processDialogsUpdateRead$30(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NJXZO2d4JQNuJgkVoidsa0CpX88(NotificationsController r0, LongSparseIntArray r1, ArrayList r2) {
        r0.lambda$removeDeletedHisoryFromNotifications$13(r1, r2);
    }

    /* renamed from: $r8$lambda$NPfzXDiAQZAt-xHbzAj8IZMHAVw, reason: not valid java name */
    public static /* synthetic */ void m4800$r8$lambda$NPfzXDiAQZAtxHbzAj8IZMHAVw(NotificationsController r0, long r1, int r3) {
        r0.lambda$processReadStories$16(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$NSc9wPEodFIIfXZP9bJRDXmPByA(NotificationsController r0) {
        r0.lambda$forceShowPopupForReply$7();
    }

    public static /* synthetic */ void $r8$lambda$PXT_XVJmkbAEW8HNUEK4VVLkv44(NotificationsController r0) {
        r0.lambda$repeatNotificationMaybe$41();
    }

    public static /* synthetic */ void $r8$lambda$PgDuHeTdMSghR8HuVC2CsaGpUNc(NotificationsController r0, ArrayList r1) {
        r0.lambda$forceShowPopupForReply$6(r1);
    }

    /* renamed from: $r8$lambda$RYpAirwJir-1M6tnnWL2eXdiK28, reason: not valid java name */
    public static /* synthetic */ void m4801$r8$lambda$RYpAirwJir1M6tnnWL2eXdiK28(NotificationsController r0) {
        r0.lambda$deleteAllNotificationChannels$44();
    }

    public static /* synthetic */ void $r8$lambda$SlXtVnC8I4XPvCikBdPU6H_lfKc(NotificationsController r0) {
        r0.lambda$cleanup$2();
    }

    public static /* synthetic */ void $r8$lambda$TMEsjTkj9lYdR59uaNuAf1n8IoU(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$Tz1KIj6UmoFnbaHi31Si44wnses(NotificationsController r0) {
        r0.checkStoryPushes();
    }

    /* renamed from: $r8$lambda$W-GSSVqDQfBqHak2wNsfKX94Y14, reason: not valid java name */
    public static /* synthetic */ long m4802$r8$lambda$WGSSVqDQfBqHak2wNsfKX94Y14(StoryNotification r2) {
        return r2.date;
    }

    public static /* synthetic */ void $r8$lambda$XbF2_Nf_zfv_7WfdwyBLoa0af2o(NotificationsController r0, long r1, long r3, int r5) {
        r0.lambda$deleteNotificationChannel$42(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$Yb3trvkYB80yLqYACLI0baZgZ9o(NotificationsController r0, long r1) {
        r0.lambda$processIgnoreStories$19(r1);
    }

    /* renamed from: $r8$lambda$Yq7keLeUDCJ-URoh85UQ_NSnMJU, reason: not valid java name */
    public static /* synthetic */ void m4803$r8$lambda$Yq7keLeUDCJURoh85UQ_NSnMJU(NotificationsController r0, ArrayList r1, LongSparseArray r2, ArrayList r3, Collection r4) {
        r0.lambda$processLoadedUnreadMessages$33(r1, r2, r3, r4);
    }

    public static /* synthetic */ int $r8$lambda$Z_KcXEDiTLz__0aNAPod1LbZsYY(Canvas r8) {
        Path r0 = new Path();
        r0.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        int r1 = r8.getWidth();
        float r5 = r1 / 2;
        r0.addRoundRect(0.0f, 0.0f, r1, r8.getHeight(), r5, r5, Path.Direction.CW);
        Paint r12 = new Paint();
        r12.setAntiAlias(true);
        r12.setColor(0);
        r12.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        r8.drawPath(r0, r12);
        return -3;
    }

    /* renamed from: $r8$lambda$dPY-kZgBIm4S2oGRbiycpSKgoM0, reason: not valid java name */
    public static /* synthetic */ void m4804$r8$lambda$dPYkZgBIm4S2oGRbiycpSKgoM0(ImageDecoder r0, ImageDecoder.ImageInfo r1, ImageDecoder.Source r2) {
        r0.setPostProcessor(new NotificationsController$$ExternalSyntheticLambda43());
    }

    public static /* synthetic */ void $r8$lambda$el0xJHhpeENCEpDJ0w96gt65p0Q(SoundPool r7, int r8, int r9) {
        if (r9 == 0) goto L9;
        return;
    L9:
        r7.play(r8, 1.0f, 1.0f, 1, 0, 1.0f);     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$f4WA1qs_cwhRzIXCGHsODlivDw4() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$gQghIUHs5ZKeIK8dHwFQ_2pzquE(Uri r2, File r3) {
        ApplicationLoader.applicationContext.revokeUriPermission(r2, 1);     // Catch: Exception -> L4
    L6:
        if (r3 == null) goto L16;
        r3.delete();     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        return;
    L16:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$gkp2DUEooDkPx8Y75H2Mp8bmD64(NotificationsController r0, LongSparseIntArray r1, ArrayList r2, long r3, int r5, int r6, boolean r7) {
        r0.lambda$processReadMessages$21(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$hw716cNB9oSE2DWHeMRZR_6whQg(NotificationsController r0) {
        r0.lambda$processIgnoreStories$17();
    }

    /* renamed from: $r8$lambda$ijNzp_-tmBitqP8K6Xfd5cEbxe8, reason: not valid java name */
    public static /* synthetic */ void m4805$r8$lambda$ijNzp_tmBitqP8K6Xfd5cEbxe8(NotificationsController r0) {
        r0.lambda$hideNotifications$36();
    }

    /* renamed from: $r8$lambda$npOt3wyxhkKzKiJddAQ6VZzN--g, reason: not valid java name */
    public static /* synthetic */ void m4806$r8$lambda$npOt3wyxhkKzKiJddAQ6VZzNg(NotificationsController r0, int r1) {
        r0.lambda$setLastOnlineFromOtherDevice$5(r1);
    }

    public static /* synthetic */ long $r8$lambda$rmTbZDqMwzzEB9LwbZKI0lgMikw(StoryNotification r2) {
        return r2.date;
    }

    public static /* synthetic */ void $r8$lambda$sLeEgeaXYLn5BrxIrp0TjRA_49s(NotificationsController r0, long r1, int r3) {
        r0.lambda$processDeleteStory$15(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$tPwabsvbvzVl4WRmLhZvQzf2LJc(NotificationsController r0, ArrayList r1) {
        r0.lambda$processDialogsUpdateRead$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$teDBm6qOJ4ohKXlfYr1OyIDnc5Y(NotificationsController r0, ArrayList r1) {
        r0.lambda$removeDeletedHisoryFromNotifications$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$wYCfk_8nXwu8vbsN9_srvLEBEvM(NotificationsController r0, long r1, long r3) {
        r0.lambda$setOpenedDialogId$3(r1, r3);
    }

    /* renamed from: $r8$lambda$wwBrcBO3Yet-K_hsY36uI2RG0fc, reason: not valid java name */
    public static /* synthetic */ void m4807$r8$lambda$wwBrcBO3YetK_hsY36uI2RG0fc(NotificationsController r0) {
        r0.lambda$playOutChatSound$49();
    }

    /* renamed from: -$$Nest$mresetNotificationSound, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4808$$Nest$mresetNotificationSound(NotificationsController r0, NotificationCompat.Builder r1, long r2, long r4, String r6, long[] r7, int r8, Uri r9, int r10, boolean r11, boolean r12, boolean r13, int r14) {
        r0.resetNotificationSound(r1, r2, r4, r6, r7, r8, r9, r10, r11, r12, r13, r14);
    }

    /* renamed from: -$$Nest$sfgetnotificationManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NotificationManagerCompat m4809$$Nest$sfgetnotificationManager() {
        return notificationManager;
    }

    static {
        notificationsQueue = new DispatchQueue("notificationsQueue");
        notificationManager = null;
        systemNotificationManager = null;
        globalSecretChatId = DialogObject.makeEncryptedDialogId(1);
        if (Build.VERSION.SDK_INT >= 26) goto L5;
    L7:
        audioManager = (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        Instance = new NotificationsController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L8:
        if (r1 >= 16) goto L10;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L8
    L10:
        sharedPrefCachedKeys = new LongSparseArray();
        return;
    L5:
        if (ApplicationLoader.applicationContext == null) goto L7;
        notificationManager = NotificationManagerCompat.from(ApplicationLoader.applicationContext);
        systemNotificationManager = (NotificationManager) ApplicationLoader.applicationContext.getSystemService("notification");
        checkOtherNotificationsChannel();
        goto L7
    }

    public NotificationsController(int r4) {
        super(r4);
        this.pushMessages = new ArrayList();
        this.delayedPushMessages = new ArrayList();
        this.pushMessagesDict = new LongSparseArray();
        this.fcmRandomMessagesDict = new LongSparseArray();
        this.smartNotificationsDialogs = new LongSparseArray();
        this.pushDialogs = new LongSparseArray();
        this.wearNotificationsIds = new LongSparseArray();
        this.lastWearNotifiedMessageId = new LongSparseArray();
        this.pushDialogsOverrideMention = new LongSparseArray();
        this.pendingVoiceLoads = new HashSet();
        this.popupMessages = new ArrayList();
        this.popupReplyMessages = new ArrayList();
        this.openedInBubbleDialogs = new HashSet();
        this.storyPushMessages = new ArrayList();
        this.storyPushMessagesDict = new LongSparseArray();
        this.openedDialogId = 0;
        this.openedTopicId = 0;
        this.lastButtonId = 5000;
        this.total_unread_count = 0;
        this.personalCount = 0;
        this.notifyCheck = false;
        this.lastOnlineFromOtherDevice = 0;
        this.lastBadgeCount = -1;
        this.mediaSpoilerEffect = new SpoilerEffect();
        this.spoilerChars = new char[]{10252, 10338, 10385, 10280, 10277, 10286, 10321};
        this.checkStoryPushesRunnable = new NotificationsController$$ExternalSyntheticLambda47(this);
        this.notificationId = this.currentAccount + 1;
        StringBuilder r0 = new StringBuilder("messages");
        int r2 = this.currentAccount;
        if (r2 != 0) goto L5;
        Object r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.append(r22);
        this.notificationGroup = r0.toString();
        SharedPreferences r02 = getAccountInstance().getNotificationsSettings();
        this.inChatSoundEnabled = r02.getBoolean("EnableInChatSound", true);
        this.showBadgeNumber = r02.getBoolean("badgeNumber", true);
        this.showBadgeMuted = r02.getBoolean("badgeNumberMuted", false);
        this.showBadgeMessages = r02.getBoolean("badgeNumberMessages", true);
        notificationManager = NotificationManagerCompat.from(ApplicationLoader.applicationContext);
        systemNotificationManager = (NotificationManager) ApplicationLoader.applicationContext.getSystemService("notification");
        audioManager = (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);     // Catch: Exception -> L9
    L21:
        this.alarmManager = (AlarmManager) ApplicationLoader.applicationContext.getSystemService("alarm");     // Catch: Exception -> L13
    L25:
        PowerManager.WakeLock r03 = ((PowerManager) ApplicationLoader.applicationContext.getSystemService("power")).newWakeLock(1, "telegram:notification_delay_lock");     // Catch: Exception -> L17
        this.notificationDelayWakelock = r03;     // Catch: Exception -> L17
        r03.setReferenceCounted(false);     // Catch: Exception -> L17
    L19:
        this.notificationDelayRunnable = new NotificationsController$$ExternalSyntheticLambda48(this);
        this.dialogsNotificationsFacade = new NotificationsSettingsFacade(this.currentAccount);
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda49(this));
        return;
    L17:
        e = move-exception;
        FileLog.e(e);
    L13:
        e = move-exception;
        FileLog.e(e);
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L21
    L5:
        r22 = Integer.valueOf(r2);
        goto L6
    }

    private int addToPopupMessages(ArrayList<MessageObject> r3, MessageObject r4, long r5, boolean r7, SharedPreferences r8) {
        if (r4.isStoryReactionPush == false) goto L6;
        return 0;
    L6:
        if (DialogObject.isEncryptedDialog(r5) == false) goto L8;
    L24:
        int r2 = 0;
    L25:
        if (r2 != 0) goto L27;
    L31:
        if (r2 == 0) goto L33;
        r3.add(0, r4);
    L33:
        return r2;
    L27:
        if (r4.messageOwner.peer_id.channel_id == 0) goto L31;
        if (r4.isSupergroup() == true) goto L31;
        r2 = 0;
        goto L31
    L8:
        if (r8.getBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r5, false) == false) goto L10;
        r2 = r8.getInt("popup_" + r5, 0);
    L11:
        if (r2 != 0) goto L20;
        if (r7 == false) goto L15;
        r2 = r8.getInt("popupChannel", 0);
        goto L25
    L15:
        if (DialogObject.isChatDialog(r5) == false) goto L17;
        String r22 = "popupGroup";
    L18:
        r2 = r8.getInt(r22, 0);
        goto L25
    L17:
        r22 = "popupAll";
        goto L18
    L20:
        if (r2 != 1) goto L23;
        r2 = 3;
        goto L25
    L23:
        if (r2 != 2) goto L25;
    L10:
        r2 = 0;
        goto L11
    }

    private void appendMessage(MessageObject r7) {
        int r1 = 0;
    L3:
        int r2 = this.pushMessages.size();
        ArrayList<MessageObject> r3 = this.pushMessages;
        if (r1 >= r2) goto L13;
        if (r3.get(r1).getId() != r7.getId()) goto L12;
        if (this.pushMessages.get(r1).getDialogId() != r7.getDialogId()) goto L12;
        if (this.pushMessages.get(r1).isStoryPush != r7.isStoryPush) goto L12;
        return;
    L12:
        r1 = r1 + 1;
        goto L3
    L13:
        r3.add(0, r7);
    }

    public static void checkOtherNotificationsChannel() {
        if (Build.VERSION.SDK_INT >= 26) goto L6;
        return;
    L6:
        if (OTHER_NOTIFICATIONS_CHANNEL != null) goto L8;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0);
        OTHER_NOTIFICATIONS_CHANNEL = r0.getString("OtherKey", "Other3");
    L9:
        NotificationChannel r5 = systemNotificationManager.getNotificationChannel(OTHER_NOTIFICATIONS_CHANNEL);
        if (r5 == null) goto L19;
        if (r5.getImportance() != 0) goto L19;
        systemNotificationManager.deleteNotificationChannel(OTHER_NOTIFICATIONS_CHANNEL);     // Catch: Exception -> L15
    L17:
        OTHER_NOTIFICATIONS_CHANNEL = null;
        r5 = null;
    L15:
        e = move-exception;
        FileLog.e(e);
    L19:
        if (OTHER_NOTIFICATIONS_CHANNEL != null) goto L23;
        if (r0 != null) goto L22;
        r0 = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0);
    L22:
        OTHER_NOTIFICATIONS_CHANNEL = "Other" + Utilities.random.nextLong();
        r0.edit().putString("OtherKey", OTHER_NOTIFICATIONS_CHANNEL).apply();
    L23:
        if (r5 != null) goto L34;
        CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1.m();
        NotificationChannel r02 = CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m(OTHER_NOTIFICATIONS_CHANNEL, "Internal notifications", 3);
        r02.enableLights(false);
        r02.enableVibration(false);
        r02.setSound(null, null);
        systemNotificationManager.createNotificationChannel(r02);     // Catch: Exception -> L27
        return;
    L27:
        e = move-exception;
        FileLog.e(e);
        return;
    L34:
        return;
    L8:
        r0 = null;
        goto L9
    }

    private void checkStoryPushes() {
        long r0 = System.currentTimeMillis();
        int r3 = 0;
        boolean r4 = false;
    L4:
        if (r3 >= this.storyPushMessages.size()) goto L17;
        StoryNotification r5 = this.storyPushMessages.get(r3);
        Iterator<Map.Entry<Integer, Pair<Long, Long>>> r6 = r5.dateByIds.entrySet().iterator();
    L7:
        if (r6.hasNext() == false) goto L11;
        if (r0 < ((Long) r6.next().getValue().second).longValue()) goto L7;
        r6.remove();
        r4 = true;
        goto L7
    L11:
        if (r4 == false) goto L16;
        if (r5.dateByIds.isEmpty() == false) goto L15;
        getMessagesStorage().deleteStoryPushMessage(r5.dialogId);
        this.storyPushMessages.remove(r3);
        r3 = r3 - 1;
        goto L16
    L15:
        getMessagesStorage().putStoryPushMessage(r5);
    L16:
        r3 = r3 + 1;
        goto L4
    L17:
        if (r4 == false) goto L19;
        showOrUpdateNotification(false);
    L19:
        updateStoryPushesRunnable();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DialogNotificationSettings computeDialogNotificationSettings(SharedPreferences r20, long r21, long r23, boolean r25, boolean r26, boolean r27, long r28, long r30, boolean r32, boolean r33) {
        if (r33 == false) goto L5;
    L7:
        boolean r3 = r33;
    L8:
        String r4 = Settings.System.DEFAULT_NOTIFICATION_URI.getPath();
        boolean r5 = ApplicationLoader.mainInterfacePaused;
        boolean r6 = !r5;
        if (this.dialogsNotificationsFacade.getProperty(NotificationsSettingsFacade.PROPERTY_CUSTOM, r21, r23, false) == false) goto L20;
        boolean r332 = r3;
        int r7 = 0;
        long r2 = 0;
        int r16 = this.dialogsNotificationsFacade.getProperty("vibrate_", r21, r23, 0);
        int r17 = this.dialogsNotificationsFacade.getProperty("priority_", r21, r23, 3);
        long r8 = this.dialogsNotificationsFacade.getProperty("sound_document_id_", r21, r23, 0);
        if (r8 == 0) goto L14;
        String r82 = getMediaDataController().ringtoneDataStore.getSoundPath(r8);
        boolean r15 = true;
    L13:
        String r18 = r82;
        int r83 = this.dialogsNotificationsFacade.getProperty("color_", r21, r23, 0);
        if (r83 == 0) goto L18;
        Integer r11 = Integer.valueOf(r83);
    L19:
        Integer r9 = r11;
        boolean r10 = r15;
        int r84 = r16;
        int r152 = r17;
        String r112 = r18;
    L21:
        int r14 = -16776961;
        if (r26 == true) goto L56;
        if (r27 == true) goto L56;
        if (r28 == r2) goto L40;
        if (r32 == false) goto L34;
        long r12 = r20.getLong("ChannelSoundDocId", r2);
        if (r12 == r2) goto L31;
        String r22 = getMediaDataController().ringtoneDataStore.getSoundPath(r12);
        int r0 = 1;
    L32:
        int r34 = r20.getInt("vibrate_channel", r7);
        int r42 = r20.getInt("priority_channel", 1);
        r14 = r20.getInt("ChannelLed", -16776961);
        int r122 = 2;
    L64:
        if (r34 != 4) goto L66;
        r34 = r7;
        int r13 = 1;
    L68:
        if (TextUtils.isEmpty(r112) == false) goto L70;
    L73:
        boolean r102 = r0;
        r112 = r22;
        int r02 = 1;
    L74:
        if (r152 == 3) goto L77;
        if (r42 == r152) goto L77;
        r02 = r7;
        r42 = r152;
    L77:
        if (r9 != null) goto L79;
    L81:
        if (r84 != 0) goto L83;
    L86:
        boolean r24 = r02;
        r84 = r34;
    L87:
        if (r5 == false) goto L89;
    L98:
        if (r13 != 0) goto L100;
    L110:
        if (r332 == false) goto L112;
        r42 = r7;
        r14 = r42;
        r112 = null;
    L113:
        DialogNotificationSettings r03 = new DialogNotificationSettings(null);
        r03.notifyDisabled = r332;
        r03.vibrate = r7;
        r03.soundPath = r112;
        r03.isInternalSoundFile = r102;
        r03.ledColor = r14;
        r03.importance = r42;
        r03.isDefault = r24;
        r03.isInApp = r6;
        r03.chatType = r122;
        return r03;
    L112:
        r7 = r84;
        goto L113
    L100:
        if (r84 == 2) goto L110;
        int r1 = audioManager.getRingerMode();     // Catch: Exception -> L108
        if (r1 != 0) goto L104;
    L106:
        int r132 = r84;
    L107:
        r84 = r132;
        goto L110
    L104:
        if (r1 == 1) goto L106;
        r132 = 2;
    L108:
        e = move-exception;
        FileLog.e(e);
        goto L110
    L89:
        if (r20.getBoolean("EnableInAppSounds", true) == true) goto L92;
        r112 = null;
    L92:
        if (r20.getBoolean("EnableInAppVibrate", true) == true) goto L95;
        r84 = 2;
    L95:
        if (r20.getBoolean("EnableInAppPopup", true) == false) goto L97;
        r42 = 2;
        goto L98
    L97:
        r42 = r7;
        goto L98
    L83:
        if (r84 == 4) goto L86;
        if (r84 == r34) goto L86;
        r24 = r7;
        goto L87
    L79:
        if (r9.intValue() == r14) goto L81;
        r14 = r9.intValue();
        r02 = r7;
        goto L81
    L70:
        if (TextUtils.equals(r22, r112) == true) goto L73;
        r02 = r7;
        r102 = r10;
        goto L74
    L66:
        r13 = r7;
        goto L68
    L31:
        r22 = r20.getString("ChannelSoundPath", r4);
        r0 = r7;
        goto L32
    L34:
        long r123 = r20.getLong("GroupSoundDocId", r2);
        if (r123 == r2) goto L37;
        r22 = getMediaDataController().ringtoneDataStore.getSoundPath(r123);
        r0 = 1;
    L38:
        r34 = r20.getInt("vibrate_group", r7);
        r42 = r20.getInt("priority_group", 1);
        r14 = r20.getInt("GroupLed", -16776961);
        r122 = r7;
        goto L64
    L37:
        r22 = r20.getString("GroupSoundPath", r4);
        r0 = r7;
        goto L38
    L40:
        if (r30 == r2) goto L54;
        if (r25 == false) goto L43;
        String r124 = "StoriesSoundDocId";
    L44:
        long r125 = r20.getLong(r124, r2);
        if (r125 == r2) goto L47;
        r22 = getMediaDataController().ringtoneDataStore.getSoundPath(r125);
        r0 = 1;
    L51:
        r34 = r20.getInt("vibrate_messages", r7);
        r42 = r20.getInt("priority_messages", 1);
        r14 = r20.getInt("MessagesLed", -16776961);
        if (r25 == false) goto L55;
        r122 = 3;
    L55:
        r122 = 1;
        goto L64
    L47:
        if (r25 == false) goto L49;
        String r04 = "StoriesSoundPath";
    L50:
        r22 = r20.getString(r04, r4);
        r0 = r7;
        goto L51
    L49:
        r04 = "GlobalSoundPath";
        goto L50
    L43:
        r124 = "GlobalSoundDocId";
        goto L44
    L54:
        r0 = r7;
        r34 = r0;
        r42 = r34;
        r22 = null;
    L56:
        long r126 = r20.getLong("ReactionSoundDocId", r2);
        if (r126 == r2) goto L59;
        r22 = getMediaDataController().ringtoneDataStore.getSoundPath(r126);
        r0 = 1;
    L60:
        r34 = r20.getInt("vibrate_react", r7);
        r42 = r20.getInt("priority_react", 1);
        r14 = r20.getInt("ReactionsLed", -16776961);
        if (r27 == false) goto L63;
        r122 = 5;
        goto L64
    L63:
        r122 = 4;
        goto L64
    L59:
        r22 = r20.getString("ReactionSoundPath", r4);
        r0 = r7;
        goto L60
    L18:
        r11 = null;
        goto L19
    L14:
        r82 = this.dialogsNotificationsFacade.getPropertyString("sound_path_", r21, r23, null);
        r15 = false;
        goto L13
    L20:
        r332 = r3;
        r2 = 0;
        r7 = 0;
        r84 = 0;
        r10 = false;
        r9 = null;
        r112 = null;
        r152 = 3;
        goto L21
    L5:
        if (r20.getBoolean("sound_enabled_" + getSharedPrefKey(r21, r23), true) == true) goto L7;
        r3 = true;
        goto L8
    }

    @SuppressLint({"RestrictedApi"})
    private String createNotificationShortcut(NotificationCompat.Builder r17, long r18, String r20, TLRPC.User r21, TLRPC.Chat r22, Person r23, boolean r24) {
        if (unsupportedNotificationShortcut() == false) goto L5;
    L8:
        return null;
    L5:
        if (ChatObject.isChannel(r22) == true) goto L7;
    L51:
        String r8 = "ndid_" + r18;     // Catch: Exception -> L13
        Intent r9 = new Intent(ApplicationLoader.applicationContext, OpenChatReceiver.class);     // Catch: Exception -> L13
        r9.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);     // Catch: Exception -> L13
        if (r18 <= 0) goto L15;
        r9.putExtra("userId", r18);     // Catch: Exception -> L13
    L16:
        ShortcutInfoCompat.Builder r10 = new ShortcutInfoCompat.Builder(ApplicationLoader.applicationContext, r8);     // Catch: Exception -> L13
        if (r22 == null) goto L19;
        String r5 = r20;
    L20:
        ShortcutInfoCompat.Builder r52 = r10.setShortLabel(r5).setLongLabel(r20).setIntent(new Intent("android.intent.action.VIEW")).setIntent(r9);     // Catch: Exception -> L13
        boolean r92 = true;
        ShortcutInfoCompat.Builder r53 = r52.setLongLived(true).setLocusId(new LocusIdCompat(r8));     // Catch: Exception -> L13
        if (r23 == null) goto L25;
        r53.setPerson(r23);     // Catch: Exception -> L13
        r53.setIcon(r23.getIcon());     // Catch: Exception -> L13
        if (r23.getIcon() == null) goto L25;
        Bitmap r6 = r23.getIcon().getBitmap();     // Catch: Exception -> L13
    L26:
        ShortcutInfoCompat r54 = r53.build();     // Catch: Exception -> L13
        ShortcutManagerCompat.pushDynamicShortcut(ApplicationLoader.applicationContext, r54);     // Catch: Exception -> L13
        r17.setShortcutInfo(r54);     // Catch: Exception -> L13
        Intent r55 = new Intent(ApplicationLoader.applicationContext, BubbleActivity.class);     // Catch: Exception -> L13
        r55.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);     // Catch: Exception -> L13
        if (DialogObject.isUserDialog(r18) == false) goto L29;
        r55.putExtra("userId", r18);     // Catch: Exception -> L13
    L30:
        r55.putExtra("currentAccount", this.currentAccount);     // Catch: Exception -> L13
        if (r6 == null) goto L33;
        IconCompat r4 = IconCompat.createWithAdaptiveBitmap(r6);     // Catch: Exception -> L13
    L40:
        if (r24 == false) goto L47;
        NotificationCompat.BubbleMetadata.Builder r62 = new NotificationCompat.BubbleMetadata.Builder(PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, r55, 167772160), r4);     // Catch: Exception -> L13
        if (this.openedDialogId == r18) goto L45;
        r92 = false;
    L45:
        r62.setSuppressNotification(r92);     // Catch: Exception -> L13
        r62.setAutoExpandBubble(false);     // Catch: Exception -> L13
        r62.setDesiredHeight(AndroidUtilities.dp(640.0f));     // Catch: Exception -> L13
        r17.setBubbleMetadata(r62.build());     // Catch: Exception -> L13
        return r8;
    L47:
        r17.setBubbleMetadata(null);     // Catch: Exception -> L13
        return r8;
    L33:
        if (r21 == null) goto L39;
        Context r63 = ApplicationLoader.applicationContext;     // Catch: Exception -> L13
        if (r21.bot == false) goto L37;
        int r42 = R.drawable.book_bot;     // Catch: Exception -> L13
    L38:
        r4 = IconCompat.createWithResource(r63, r42);     // Catch: Exception -> L13
        goto L40
    L37:
        r42 = R.drawable.book_user;     // Catch: Exception -> L13
        goto L38
    L39:
        r4 = IconCompat.createWithResource(ApplicationLoader.applicationContext, R.drawable.book_group);     // Catch: Exception -> L13
        goto L40
    L29:
        r55.putExtra("chatId", -r18);     // Catch: Exception -> L13
    L25:
        r6 = null;
        goto L26
    L19:
        r5 = UserObject.getFirstName(r21);     // Catch: Exception -> L13
        goto L20
    L15:
        r9.putExtra("chatId", -r18);     // Catch: Exception -> L13
    L13:
        e = move-exception;
        FileLog.e(e);
        return null;
    L7:
        if (r22.megagroup == true) goto L51;
        goto L8
    }

    private String cutLastName(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        int r1 = r2.indexOf(32);
        if (r1 < 0) goto L13;
        String r12 = r2.substring(0, r1);
        String r0 = "…";
        if (r2.endsWith("…") == true) goto L12;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return r12.concat(r0);
    L13:
        return r2;
    }

    private void deleteNotificationChannelInternal(long r10, long r12, int r14) {
        if (Build.VERSION.SDK_INT < 26) goto L46;
        SharedPreferences r9 = getAccountInstance().getNotificationsSettings();     // Catch: Exception -> L37
        SharedPreferences.Editor r2 = r9.edit();     // Catch: Exception -> L37
        if (r14 == 0) goto L9;
        if (r14 == (-1)) goto L9;
    L23:
        if (r14 == 1) goto L25;
        if (r14 == (-1)) goto L25;
    L35:
        r2.apply();     // Catch: Exception -> L37
        return;
    L25:
        String r102 = "org.telegram.keyia" + r10;     // Catch: Exception -> L37
        String r92 = r9.getString(r102, null);     // Catch: Exception -> L37
        if (r92 == null) goto L35;
        r2.remove(r102).remove(r102.concat("_s"));     // Catch: Exception -> L37
        systemNotificationManager.deleteNotificationChannel(r92);     // Catch: Exception -> L30
    L33:
        if (BuildVars.LOGS_ENABLED == false) goto L35;
        FileLog.d("delete channel internal ".concat(r92));     // Catch: Exception -> L37
    L30:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L37
    L9:
        String r1 = "org.telegram.key" + r10;     // Catch: Exception -> L37
        if (r12 == 0) goto L12;
        r1 = r1 + ".topic" + r12;     // Catch: Exception -> L37
    L12:
        String r122 = r9.getString(r1, null);     // Catch: Exception -> L37
        if (r122 == null) goto L23;
        r2.remove(r1).remove(r1.concat("_s"));     // Catch: Exception -> L37
        systemNotificationManager.deleteNotificationChannel(r122);     // Catch: Exception -> L17
    L20:
        if (BuildVars.LOGS_ENABLED == false) goto L23;
        FileLog.d("delete channel internal ".concat(r122));     // Catch: Exception -> L37
    L17:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L37
    L37:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private void dismissNotification() {
        FileLog.d("NotificationsController dismissNotification");
        notificationManager.cancel(this.notificationId);     // Catch: Exception -> L14
        this.pushMessages.clear();     // Catch: Exception -> L14
        this.pushMessagesDict.clear();     // Catch: Exception -> L14
        this.lastWearNotifiedMessageId.clear();     // Catch: Exception -> L14
        int r0 = 0;
    L4:
        int r1 = this.wearNotificationsIds.size();     // Catch: Exception -> L14
        LongSparseArray<Integer> r2 = this.wearNotificationsIds;
        if (r0 >= r1) goto L12;
        long r12 = r2.keyAt(r0);     // Catch: Exception -> L14
        if (this.openedInBubbleDialogs.contains(Long.valueOf(r12)) == true) goto L11;
        notificationManager.cancel(this.wearNotificationsIds.valueAt(r0).intValue());     // Catch: Exception -> L14
    L11:
        r0 = r0 + 1;     // Catch: Exception -> L14
        goto L4
    L12:
        r2.clear();     // Catch: Exception -> L14
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda17());     // Catch: Exception -> L14
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
    }

    public static String getGlobalNotificationsKey(int r1) {
        if (r1 != 0) goto L6;
        return "EnableGroup2";
    L6:
        if (r1 != 1) goto L9;
        return "EnableAll2";
    L9:
        return "EnableChannel2";
    }

    private TLRPC.NotificationSound getInputSound(SharedPreferences r5, String r6, String r7, String r8) {
        long r2 = r5.getLong(r7, 0);
        String r72 = r5.getString(r8, "NoSound");
        if (r2 == 0) goto L6;
        TLRPC.TL_notificationSoundRingtone r4 = new TLRPC.TL_notificationSoundRingtone();
        r4.id = r2;
        return r4;
    L6:
        if (r72 == null) goto L14;
        if (r72.equalsIgnoreCase("NoSound") == true) goto L10;
        TLRPC.TL_notificationSoundLocal r42 = new TLRPC.TL_notificationSoundLocal();
        r42.title = r5.getString(r6, null);
        r42.data = r72;
        return r42;
    L10:
        return new TLRPC.TL_notificationSoundNone();
    L14:
        return new TLRPC.TL_notificationSoundDefault();
    }

    public static NotificationsController getInstance(int r3) {
        NotificationsController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        NotificationsController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        NotificationsController[] r03 = Instance;     // Catch: Throwable -> L9
        NotificationsController r2 = new NotificationsController(r3);     // Catch: Throwable -> L9
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

    private int getNotificationColor() {
        return AppUtils.getNotificationColor();
    }

    private int getNotifyOverride(SharedPreferences r8, long r9, long r11) {
        int r82 = this.dialogsNotificationsFacade.getProperty(NotificationsSettingsFacade.PROPERTY_NOTIFY, r9, r11, -1);
        if (r82 == 3) goto L5;
    L8:
        return r82;
    L5:
        if (this.dialogsNotificationsFacade.getProperty(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL, r9, r11, 0) < getConnectionsManager().getCurrentTime()) goto L8;
        return 2;
    }

    public static String getSharedPrefKey(long r1, long r3) {
        return getSharedPrefKey(r1, r3, false);
    }

    public static String getSharedPrefKey(long r6, long r8, boolean r10) {
        if (r10 == true) goto L5;
        long r3 = (r8 << 12) + r6;
        LongSparseArray<String> r102 = sharedPrefCachedKeys;
        int r5 = r102.indexOfKey(r3);
        if (r5 < 0) goto L15;
        return r102.valueAt(r5);
    L15:
        if (r8 == 0) goto L17;
        String r62 = String.format(Locale.US, "%d_%d", new Object[]{Long.valueOf(r6), Long.valueOf(r8)});
    L18:
        r102.put(r3, r62);
        return r62;
    L17:
        r62 = String.valueOf(r6);
        goto L18
    L5:
        if (r8 == 0) goto L9;
        return String.format(Locale.US, "%d_%d", new Object[]{Long.valueOf(r6), Long.valueOf(r8)});
    L9:
        return String.valueOf(r6);
    }

    private String getStringForMessage(MessageObject r26, boolean r27, boolean[] r28, boolean[] r29) {
        if (AndroidUtilities.needShowPasscode() == true) goto L1032;
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L1032;
        if (r26.isStoryPush == true) goto L1030;
        if (r26.isStoryMentionPush == true) goto L1030;
        TLRPC.Message r2 = r26.messageOwner;
        long r3 = r2.dialog_id;
        TLRPC.Peer r22 = r2.peer_id;
        long r5 = r22.chat_id;
        if (r5 != 0) goto L16;
        r5 = r22.channel_id;
    L16:
        long r9 = r22.user_id;
        if (r29 == null) goto L20;
        r29[0] = true;
    L20:
        if (r26.getDialogId() == UserObject.VERIFY) goto L22;
    L27:
        SharedPreferences r12 = getAccountInstance().getNotificationsSettings();
        boolean r13 = r12.getBoolean(NotificationsSettingsFacade.PROPERTY_CONTENT_PREVIEW + r3, true);
        if (r26.isFcmMessage() == true) goto L30;
        long r18 = getUserConfig().getClientUserId();
        if (r9 != 0) goto L68;
        r9 = r26.getFromChatId();
        if (r9 != 0) goto L71;
        r9 = -r5;
    L71:
        if (r3 == 0) goto L73;
    L78:
        String r20 = null;
        if (r26.getDialogId() != UserObject.OAUTH) goto L81;
    L82:
        long r222 = r3;
        String r23 = LocaleController.getString(R.string.BotAuthNotificationTitle);
    L101:
        if (r23 != null) goto L104;
        return null;
    L104:
        if (r5 == 0) goto L108;
        TLRPC.Chat r4 = getMessagesController().getChat(Long.valueOf(r5));
        if (r4 != null) goto L110;
        return null;
    L110:
        if (DialogObject.isEncryptedDialog(r222) == false) goto L114;
        return LocaleController.getString(R.string.YouHaveNewMessage);
    L114:
        if (r5 == 0) goto L116;
    L356:
        if (r5 != 0) goto L358;
    L1028:
        return null;
    L358:
        if (ChatObject.isChannel(r4) == true) goto L360;
    L362:
        boolean r32 = false;
    L364:
        if (r13 == true) goto L366;
    L1012:
        if (r29 == null) goto L1015;
        r29[0] = false;
    L1015:
        if (ChatObject.isChannel(r4) == false) goto L1021;
        if (r4.megagroup == true) goto L1021;
        return LocaleController.formatString(R.string.ChannelMessageNoText, new Object[]{r23});
    L1021:
        if (r26.type != 29) goto L1027;
        if ((MessageObject.getMedia(r26) instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L1027;
        return LocaleController.formatPluralString("NotificationMessagePaidMedia", (int) ((TLRPC.TL_messageMediaPaidMedia) MessageObject.getMedia(r26)).stars_amount, new Object[]{r23});
    L1027:
        return LocaleController.formatString(R.string.NotificationMessageGroupNoText, new Object[]{r23, getTitle(r4)});
    L366:
        if (r32 == false) goto L368;
    L369:
        if (r32 == false) goto L1012;
        if (r12.getBoolean("EnablePreviewChannel", true) == false) goto L1012;
    L372:
        TLRPC.Message r33 = r26.messageOwner;
        if ((r33 instanceof TLRPC.TL_messageService) == false) goto L777;
        TLRPC.MessageAction r7 = r33.action;
        if ((r7 instanceof TLRPC.TL_messageActionChatAddUser) == false) goto L419;
        long r52 = r7.user_id;
        if (r52 != 0) goto L382;
        if (r7.users.size() != 1) goto L382;
        r52 = r26.messageOwner.action.users.get(0).longValue();
    L382:
        if (r52 != 0) goto L384;
        StringBuilder r34 = new StringBuilder();
        int r11 = 0;
    L408:
        if (r11 >= r26.messageOwner.action.users.size()) goto L417;
        TLRPC.User r53 = getMessagesController().getUser(r26.messageOwner.action.users.get(r11));
        if (r53 == null) goto L415;
        String r54 = UserObject.getUserName(r53);
        if (r34.length() == 0) goto L414;
        r34.append(", ");
    L414:
        r34.append(r54);
    L415:
        r11 = r11 + 1;
        goto L408
    L417:
        return LocaleController.formatString(R.string.NotificationGroupAddMember, new Object[]{r23, getTitle(r4), r34.toString()});
    L384:
        if (r26.messageOwner.peer_id.channel_id == 0) goto L390;
        if (r4.megagroup == true) goto L390;
        return LocaleController.formatString(R.string.ChannelAddedByNotification, new Object[]{r23, getTitle(r4)});
    L390:
        if (r52 == r18) goto L392;
        TLRPC.User r1 = getMessagesController().getUser(Long.valueOf(r52));
        if (r1 != null) goto L397;
        return null;
    L397:
        if (r9 != r1.id) goto L405;
        if (r4.megagroup == false) goto L403;
        return LocaleController.formatString(R.string.NotificationGroupAddSelfMega, new Object[]{r23, getTitle(r4)});
    L403:
        return LocaleController.formatString(R.string.NotificationGroupAddSelf, new Object[]{r23, getTitle(r4)});
    L405:
        return LocaleController.formatString(R.string.NotificationGroupAddMember, new Object[]{r23, getTitle(r4), UserObject.getUserName(r1)});
    L392:
        return LocaleController.formatString(R.string.NotificationInvitedToGroup, new Object[]{r23, getTitle(r4)});
    L419:
        if ((r7 instanceof TLRPC.TL_messageActionGroupCall) == false) goto L427;
        if (r7.duration == 0) goto L425;
        return LocaleController.formatString(R.string.NotificationGroupEndedCall, new Object[]{r23, getTitle(r4)});
    L425:
        return LocaleController.formatString(R.string.NotificationGroupCreatedCall, new Object[]{r23, getTitle(r4)});
    L427:
        if ((r7 instanceof TLRPC.TL_messageActionGroupCallScheduled) == false) goto L431;
        return r26.messageText.toString();
    L431:
        if ((r7 instanceof TLRPC.TL_messageActionInviteToGroupCall) == false) goto L461;
        long r55 = r7.user_id;
        if (r55 != 0) goto L438;
        if (r7.users.size() != 1) goto L438;
        r55 = r26.messageOwner.action.users.get(0).longValue();
    L438:
        if (r55 != 0) goto L440;
        StringBuilder r35 = new StringBuilder();
        int r112 = 0;
    L450:
        if (r112 >= r26.messageOwner.action.users.size()) goto L459;
        TLRPC.User r56 = getMessagesController().getUser(r26.messageOwner.action.users.get(r112));
        if (r56 == null) goto L457;
        String r57 = UserObject.getUserName(r56);
        if (r35.length() == 0) goto L456;
        r35.append(", ");
    L456:
        r35.append(r57);
    L457:
        r112 = r112 + 1;
        goto L450
    L459:
        return LocaleController.formatString(R.string.NotificationGroupInvitedToCall, new Object[]{r23, getTitle(r4), r35.toString()});
    L440:
        if (r55 == r18) goto L442;
        TLRPC.User r14 = getMessagesController().getUser(Long.valueOf(r55));
        if (r14 != null) goto L447;
        return null;
    L447:
        return LocaleController.formatString(R.string.NotificationGroupInvitedToCall, new Object[]{r23, getTitle(r4), UserObject.getUserName(r14)});
    L442:
        return LocaleController.formatString(R.string.NotificationGroupInvitedYouToCall, new Object[]{r23, getTitle(r4)});
    L461:
        if ((r7 instanceof TLRPC.TL_messageActionGiftCode) == false) goto L472;
        TLRPC.TL_messageActionGiftCode r72 = (TLRPC.TL_messageActionGiftCode) r7;
        TLRPC.Chat r15 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-DialogObject.getPeerDialogId(r72.boost_peer)));
        if (r15 != null) goto L465;
    L464:
        String r0 = r20;
        if (r0 != null) goto L470;
        return LocaleController.getString(R.string.BoostingReceivedGiftNoName);
    L470:
        return LocaleController.formatString(R.string.NotificationMessageGiftCode, new Object[]{r0, LocaleController.formatPluralString("Months", r72.months, new Object[0])});
    L465:
        r20 = getTitle(r15);
        goto L464
    L472:
        if ((r7 instanceof TLRPC.TL_messageActionChatJoinedByLink) == false) goto L476;
        return LocaleController.formatString(R.string.NotificationInvitedToGroupByLink, new Object[]{r23, getTitle(r4)});
    L476:
        if ((r7 instanceof TLRPC.TL_messageActionChatEditTitle) == false) goto L480;
        return LocaleController.formatString(R.string.NotificationEditedGroupName, new Object[]{r23, r7.title});
    L480:
        if ((r7 instanceof TLRPC.TL_messageActionTodoCompletions) == false) goto L484;
        return r26.messageText.toString();
    L484:
        if ((r7 instanceof TLRPC.TL_messageActionTodoAppendTasks) == false) goto L488;
        return r26.messageText.toString();
    L488:
        if ((r7 instanceof TLRPC.TL_messageActionChatEditPhoto) == true) goto L761;
        if ((r7 instanceof TLRPC.TL_messageActionChatDeletePhoto) == true) goto L761;
        if ((r7 instanceof TLRPC.TL_messageActionChatDeleteUser) == false) goto L508;
        long r58 = r7.user_id;
        if (r58 != r18) goto L499;
        return LocaleController.formatString(R.string.NotificationGroupKickYou, new Object[]{r23, getTitle(r4)});
    L499:
        if (r58 == r9) goto L501;
        TLRPC.User r16 = getMessagesController().getUser(Long.valueOf(r26.messageOwner.action.user_id));
        if (r16 != null) goto L506;
        return null;
    L506:
        return LocaleController.formatString(R.string.NotificationGroupKickMember, new Object[]{r23, getTitle(r4), UserObject.getUserName(r16)});
    L501:
        return LocaleController.formatString(R.string.NotificationGroupLeftMember, new Object[]{r23, getTitle(r4)});
    L508:
        if ((r7 instanceof TLRPC.TL_messageActionChatCreate) == false) goto L512;
        return r26.messageText.toString();
    L512:
        if ((r7 instanceof TLRPC.TL_messageActionChannelCreate) == false) goto L516;
        return r26.messageText.toString();
    L516:
        if ((r7 instanceof TLRPC.TL_messageActionChatMigrateTo) == false) goto L520;
        return LocaleController.formatString(R.string.ActionMigrateFromGroupNotify, new Object[]{getTitle(r4)});
    L520:
        if ((r7 instanceof TLRPC.TL_messageActionChannelMigrateFrom) == false) goto L524;
        return LocaleController.formatString(R.string.ActionMigrateFromGroupNotify, new Object[]{r7.title});
    L524:
        if ((r7 instanceof TLRPC.TL_messageActionScreenshotTaken) == false) goto L528;
        return r26.messageText.toString();
    L528:
        if ((r7 instanceof TLRPC.TL_messageActionPinMessage) == false) goto L737;
        if (ChatObject.isChannel(r4) == true) goto L532;
    L635:
        MessageObject r36 = r26.replyMessageObject;
        if (r36 != null) goto L640;
        return LocaleController.formatString(R.string.NotificationActionPinnedNoText, new Object[]{r23, getTitle(r4)});
    L640:
        if (r36.isMusic() == false) goto L644;
        return LocaleController.formatString(R.string.NotificationActionPinnedMusic, new Object[]{r23, getTitle(r4)});
    L644:
        if (r36.isVideo() == false) goto L652;
        if (TextUtils.isEmpty(r36.messageOwner.message) == true) goto L650;
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r23, "📹 " + r36.messageOwner.message, getTitle(r4)});
    L650:
        return LocaleController.formatString(R.string.NotificationActionPinnedVideo, new Object[]{r23, getTitle(r4)});
    L652:
        if (r36.isGif() == false) goto L660;
        if (TextUtils.isEmpty(r36.messageOwner.message) == true) goto L658;
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r23, "🎬 " + r36.messageOwner.message, getTitle(r4)});
    L658:
        return LocaleController.formatString(R.string.NotificationActionPinnedGif, new Object[]{r23, getTitle(r4)});
    L660:
        if (r36.isVoice() == false) goto L664;
        return LocaleController.formatString(R.string.NotificationActionPinnedVoice, new Object[]{r23, getTitle(r4)});
    L664:
        if (r36.isRoundVideo() == false) goto L668;
        return LocaleController.formatString(R.string.NotificationActionPinnedRound, new Object[]{r23, getTitle(r4)});
    L668:
        if (r36.isSticker() == false) goto L670;
    L730:
        String r17 = r36.getStickerEmoji();
        if (r17 == null) goto L735;
        return LocaleController.formatString(R.string.NotificationActionPinnedStickerEmoji, new Object[]{r23, getTitle(r4), r17});
    L735:
        return LocaleController.formatString(R.string.NotificationActionPinnedSticker, new Object[]{r23, getTitle(r4)});
    L670:
        if (r36.isAnimatedSticker() == true) goto L730;
        TLRPC.Message r6 = r36.messageOwner;
        TLRPC.MessageMedia r92 = r6.media;
        if ((r92 instanceof TLRPC.TL_messageMediaDocument) == false) goto L681;
        if (TextUtils.isEmpty(r6.message) == true) goto L679;
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r23, "📎 " + r36.messageOwner.message, getTitle(r4)});
    L679:
        return LocaleController.formatString(R.string.NotificationActionPinnedFile, new Object[]{r23, getTitle(r4)});
    L681:
        if ((r92 instanceof TLRPC.TL_messageMediaGeo) == true) goto L729;
        if ((r92 instanceof TLRPC.TL_messageMediaVenue) == true) goto L729;
        if ((r92 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L690;
        return LocaleController.formatString(R.string.NotificationActionPinnedGeoLive, new Object[]{r23, getTitle(r4)});
    L690:
        if ((r92 instanceof TLRPC.TL_messageMediaContact) == false) goto L694;
        TLRPC.TL_messageMediaContact r19 = (TLRPC.TL_messageMediaContact) r26.messageOwner.media;
        return LocaleController.formatString(R.string.NotificationActionPinnedContact2, new Object[]{r23, getTitle(r4), ContactsController.formatName(r19.first_name, r19.last_name)});
    L694:
        if ((r92 instanceof TLRPC.TL_messageMediaPoll) == false) goto L702;
        TLRPC.TL_messageMediaPoll r93 = (TLRPC.TL_messageMediaPoll) r92;
        if (r93.poll.quiz == false) goto L700;
        return LocaleController.formatString(R.string.NotificationActionPinnedQuiz2, new Object[]{r23, getTitle(r4), r93.poll.question.text});
    L700:
        return LocaleController.formatString(R.string.NotificationActionPinnedPoll2, new Object[]{r23, getTitle(r4), r93.poll.question.text});
    L702:
        if ((r92 instanceof TLRPC.TL_messageMediaToDo) == false) goto L706;
        return LocaleController.formatString(R.string.NotificationActionPinnedTodo2, new Object[]{r23, getTitle(r4), ((TLRPC.TL_messageMediaToDo) r92).todo.title.text});
    L706:
        if ((r92 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L714;
        if (TextUtils.isEmpty(r6.message) == true) goto L712;
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r23, "🖼 " + r36.messageOwner.message, getTitle(r4)});
    L712:
        return LocaleController.formatString(R.string.NotificationActionPinnedPhoto, new Object[]{r23, getTitle(r4)});
    L714:
        if ((r92 instanceof TLRPC.TL_messageMediaGame) == true) goto L716;
        CharSequence r110 = r36.messageText;
        if (r110 == null) goto L727;
        if (r110.length() <= 0) goto L727;
        CharSequence r111 = r36.messageText;
        if (r111.length() <= 20) goto L725;
        r111 = r111.subSequence(0, 20) + "...";
    L725:
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r23, r111, getTitle(r4)});
    L727:
        return LocaleController.formatString(R.string.NotificationActionPinnedNoText, new Object[]{r23, getTitle(r4)});
    L716:
        return LocaleController.formatString(R.string.NotificationActionPinnedGame, new Object[]{r23, getTitle(r4)});
    L729:
        return LocaleController.formatString(R.string.NotificationActionPinnedGeo, new Object[]{r23, getTitle(r4)});
    L532:
        if (r4.megagroup == true) goto L635;
        MessageObject r24 = r26.replyMessageObject;
        if (r24 != null) goto L539;
        return LocaleController.formatString(R.string.NotificationActionPinnedNoTextChannel, new Object[]{getTitle(r4)});
    L539:
        if (r24.isMusic() == false) goto L543;
        return LocaleController.formatString(R.string.NotificationActionPinnedMusicChannel, new Object[]{getTitle(r4)});
    L543:
        if (r24.isVideo() == false) goto L551;
        if (TextUtils.isEmpty(r24.messageOwner.message) == true) goto L549;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), "📹 " + r24.messageOwner.message});
    L549:
        return LocaleController.formatString(R.string.NotificationActionPinnedVideoChannel, new Object[]{getTitle(r4)});
    L551:
        if (r24.isGif() == false) goto L559;
        if (TextUtils.isEmpty(r24.messageOwner.message) == true) goto L557;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), "🎬 " + r24.messageOwner.message});
    L557:
        return LocaleController.formatString(R.string.NotificationActionPinnedGifChannel, new Object[]{getTitle(r4)});
    L559:
        if (r24.isVoice() == false) goto L563;
        return LocaleController.formatString(R.string.NotificationActionPinnedVoiceChannel, new Object[]{getTitle(r4)});
    L563:
        if (r24.isRoundVideo() == false) goto L567;
        return LocaleController.formatString(R.string.NotificationActionPinnedRoundChannel, new Object[]{getTitle(r4)});
    L567:
        if (r24.isSticker() == false) goto L569;
    L629:
        String r113 = r24.getStickerEmoji();
        if (r113 == null) goto L634;
        return LocaleController.formatString(R.string.NotificationActionPinnedStickerEmojiChannel, new Object[]{getTitle(r4), r113});
    L634:
        return LocaleController.formatString(R.string.NotificationActionPinnedStickerChannel, new Object[]{getTitle(r4)});
    L569:
        if (r24.isAnimatedSticker() == true) goto L629;
        TLRPC.Message r37 = r24.messageOwner;
        TLRPC.MessageMedia r62 = r37.media;
        if ((r62 instanceof TLRPC.TL_messageMediaDocument) == false) goto L580;
        if (TextUtils.isEmpty(r37.message) == true) goto L578;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), "📎 " + r24.messageOwner.message});
    L578:
        return LocaleController.formatString(R.string.NotificationActionPinnedFileChannel, new Object[]{getTitle(r4)});
    L580:
        if ((r62 instanceof TLRPC.TL_messageMediaGeo) == true) goto L628;
        if ((r62 instanceof TLRPC.TL_messageMediaVenue) == true) goto L628;
        if ((r62 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L589;
        return LocaleController.formatString(R.string.NotificationActionPinnedGeoLiveChannel, new Object[]{getTitle(r4)});
    L589:
        if ((r62 instanceof TLRPC.TL_messageMediaContact) == false) goto L593;
        TLRPC.TL_messageMediaContact r114 = (TLRPC.TL_messageMediaContact) r26.messageOwner.media;
        return LocaleController.formatString(R.string.NotificationActionPinnedContactChannel2, new Object[]{getTitle(r4), ContactsController.formatName(r114.first_name, r114.last_name)});
    L593:
        if ((r62 instanceof TLRPC.TL_messageMediaPoll) == false) goto L601;
        TLRPC.TL_messageMediaPoll r63 = (TLRPC.TL_messageMediaPoll) r62;
        if (r63.poll.quiz == false) goto L599;
        return LocaleController.formatString(R.string.NotificationActionPinnedQuizChannel2, new Object[]{getTitle(r4), r63.poll.question.text});
    L599:
        return LocaleController.formatString(R.string.NotificationActionPinnedPollChannel2, new Object[]{getTitle(r4), r63.poll.question.text});
    L601:
        if ((r62 instanceof TLRPC.TL_messageMediaToDo) == false) goto L605;
        return LocaleController.formatString(R.string.NotificationActionPinnedTodoChannel2, new Object[]{getTitle(r4), ((TLRPC.TL_messageMediaToDo) r62).todo.title.text});
    L605:
        if ((r62 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L613;
        if (TextUtils.isEmpty(r37.message) == true) goto L611;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), "🖼 " + r24.messageOwner.message});
    L611:
        return LocaleController.formatString(R.string.NotificationActionPinnedPhotoChannel, new Object[]{getTitle(r4)});
    L613:
        if ((r62 instanceof TLRPC.TL_messageMediaGame) == true) goto L615;
        CharSequence r115 = r24.messageText;
        if (r115 == null) goto L626;
        if (r115.length() <= 0) goto L626;
        CharSequence r116 = r24.messageText;
        if (r116.length() <= 20) goto L624;
        r116 = r116.subSequence(0, 20) + "...";
    L624:
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), r116});
    L626:
        return LocaleController.formatString(R.string.NotificationActionPinnedNoTextChannel, new Object[]{getTitle(r4)});
    L615:
        return LocaleController.formatString(R.string.NotificationActionPinnedGameChannel, new Object[]{getTitle(r4)});
    L628:
        return LocaleController.formatString(R.string.NotificationActionPinnedGeoChannel, new Object[]{getTitle(r4)});
    L737:
        if ((r7 instanceof TLRPC.TL_messageActionGameScore) == false) goto L741;
        return r26.messageText.toString();
    L741:
        if ((r7 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L757;
        String r02 = TlUtils.getThemeEmoticonOrGiftTitle(((TLRPC.TL_messageActionSetChatTheme) r7).theme);
        if (TextUtils.isEmpty(r02) == false) goto L751;
        if (r222 != r18) goto L749;
        return LocaleController.formatString(R.string.ChatThemeDisabledYou, new Object[0]);
    L749:
        return LocaleController.formatString("ChatThemeDisabled", R.string.ChatThemeDisabled, new Object[]{r23, r02});
    L751:
        if (r222 != r18) goto L755;
        return LocaleController.formatString(R.string.ChatThemeChangedYou, new Object[]{r02});
    L755:
        return LocaleController.formatString(R.string.ChatThemeChangedTo, new Object[]{r23, r02});
    L757:
        if ((r7 instanceof TLRPC.TL_messageActionChatJoinedByRequest) == false) goto L1028;
        return r26.messageText.toString();
    L761:
        if (r33.peer_id.channel_id == 0) goto L771;
        if (r4.megagroup == true) goto L771;
        if (r26.isVideoAvatar() == false) goto L769;
        return LocaleController.formatString(R.string.ChannelVideoEditNotification, new Object[]{getTitle(r4)});
    L769:
        return LocaleController.formatString(R.string.ChannelPhotoEditNotification, new Object[]{getTitle(r4)});
    L771:
        if (r26.isVideoAvatar() == false) goto L775;
        return LocaleController.formatString(R.string.NotificationEditedGroupVideo, new Object[]{r23, getTitle(r4)});
    L775:
        return LocaleController.formatString(R.string.NotificationEditedGroupPhoto, new Object[]{r23, getTitle(r4)});
    L777:
        if (ChatObject.isChannel(r4) == false) goto L893;
        if (r4.megagroup == true) goto L893;
        if (r26.isMediaEmpty() == false) goto L790;
        if (r27 == true) goto L788;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L788;
        String r03 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, r26.messageOwner.message});
        r28[0] = true;
        return r03;
    L788:
        return LocaleController.formatString(R.string.ChannelMessageNoText, new Object[]{r23});
    L790:
        if (r26.type == 29) goto L792;
    L795:
        TLRPC.Message r73 = r26.messageOwner;
        if ((r73.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L805;
        if (r27 == true) goto L803;
        if (TextUtils.isEmpty(r73.message) == true) goto L803;
        String r04 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, "🖼 " + r26.messageOwner.message});
        r28[0] = true;
        return r04;
    L803:
        return LocaleController.formatString(R.string.ChannelMessagePhoto, new Object[]{r23});
    L805:
        if (r26.isVideo() == false) goto L814;
        if (r27 == true) goto L812;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L812;
        String r05 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, "📹 " + r26.messageOwner.message});
        r28[0] = true;
        return r05;
    L812:
        return LocaleController.formatString(R.string.ChannelMessageVideo, new Object[]{r23});
    L814:
        if (r26.isVoice() == false) goto L818;
        return LocaleController.formatString(R.string.ChannelMessageAudio, new Object[]{r23});
    L818:
        if (r26.isRoundVideo() == false) goto L822;
        return LocaleController.formatString(R.string.ChannelMessageRound, new Object[]{r23});
    L822:
        if (r26.isMusic() == true) goto L824;
        TLRPC.MessageMedia r38 = r26.messageOwner.media;
        if ((r38 instanceof TLRPC.TL_messageMediaContact) == false) goto L830;
        TLRPC.TL_messageMediaContact r39 = (TLRPC.TL_messageMediaContact) r38;
        return LocaleController.formatString(R.string.ChannelMessageContact2, new Object[]{r23, ContactsController.formatName(r39.first_name, r39.last_name)});
    L830:
        if ((r38 instanceof TLRPC.TL_messageMediaPoll) == false) goto L838;
        TLRPC.Poll r06 = ((TLRPC.TL_messageMediaPoll) r38).poll;
        if (r06.quiz == false) goto L836;
        return LocaleController.formatString(R.string.ChannelMessageQuiz2, new Object[]{r23, r06.question.text});
    L836:
        return LocaleController.formatString(R.string.ChannelMessagePoll2, new Object[]{r23, r06.question.text});
    L838:
        if ((r38 instanceof TLRPC.TL_messageMediaToDo) == false) goto L842;
        return LocaleController.formatString(R.string.ChannelMessageTodo2, new Object[]{r23, ((TLRPC.TL_messageMediaToDo) r38).todo.title.text});
    L842:
        if ((r38 instanceof TLRPC.TL_messageMediaGiveaway) == false) goto L846;
        TLRPC.TL_messageMediaGiveaway r310 = (TLRPC.TL_messageMediaGiveaway) r38;
        return LocaleController.formatString(R.string.NotificationMessageChannelGiveaway, new Object[]{getTitle(r4), Integer.valueOf(r310.quantity), Integer.valueOf(r310.months)});
    L846:
        if ((r38 instanceof TLRPC.TL_messageMediaGeo) == true) goto L891;
        if ((r38 instanceof TLRPC.TL_messageMediaVenue) == true) goto L891;
        if ((r38 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L855;
        return LocaleController.formatString(R.string.ChannelMessageLiveLocation, new Object[]{r23});
    L855:
        if ((r38 instanceof TLRPC.TL_messageMediaDocument) == true) goto L857;
        if (r27 == true) goto L889;
        if (TextUtils.isEmpty(r26.messageText) == true) goto L889;
        String r07 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, r26.messageText});
        r28[0] = true;
        return r07;
    L889:
        return LocaleController.formatString(R.string.ChannelMessageNoText, new Object[]{r23});
    L857:
        if (r26.isSticker() == false) goto L859;
    L877:
        String r08 = r26.getStickerEmoji();
        if (r08 == null) goto L882;
        return LocaleController.formatString(R.string.ChannelMessageStickerEmoji, new Object[]{r23, r08});
    L882:
        return LocaleController.formatString(R.string.ChannelMessageSticker, new Object[]{r23});
    L859:
        if (r26.isAnimatedSticker() == true) goto L877;
        if (r26.isGif() == false) goto L870;
        if (r27 == true) goto L869;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L869;
        String r09 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, "🎬 " + r26.messageOwner.message});
        r28[0] = true;
        return r09;
    L869:
        return LocaleController.formatString(R.string.ChannelMessageGIF, new Object[]{r23});
    L870:
        if (r27 == true) goto L876;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L876;
        String r010 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, "📎 " + r26.messageOwner.message});
        r28[0] = true;
        return r010;
    L876:
        return LocaleController.formatString(R.string.ChannelMessageDocument, new Object[]{r23});
    L891:
        return LocaleController.formatString(R.string.ChannelMessageMap, new Object[]{r23});
    L824:
        return LocaleController.formatString(R.string.ChannelMessageMusic, new Object[]{r23});
    L792:
        if ((MessageObject.getMedia(r26) instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L795;
        return LocaleController.formatPluralString("NotificationChannelMessagePaidMedia", (int) ((TLRPC.TL_messageMediaPaidMedia) MessageObject.getMedia(r26)).stars_amount, new Object[]{getTitle(r4)});
    L893:
        if (r26.isMediaEmpty() == false) goto L902;
        if (r27 == true) goto L900;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L900;
        return LocaleController.formatString(R.string.NotificationMessageGroupText, new Object[]{r23, getTitle(r4), r26.messageOwner.message});
    L900:
        return LocaleController.formatString(R.string.NotificationMessageGroupNoText, new Object[]{r23, getTitle(r4)});
    L902:
        if (r26.type == 29) goto L904;
    L907:
        TLRPC.Message r311 = r26.messageOwner;
        if ((r311.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L917;
        if (r27 == true) goto L915;
        if (TextUtils.isEmpty(r311.message) == true) goto L915;
        return LocaleController.formatString(R.string.NotificationMessageGroupText, new Object[]{r23, getTitle(r4), "🖼 " + r26.messageOwner.message});
    L915:
        return LocaleController.formatString(R.string.NotificationMessageGroupPhoto, new Object[]{r23, getTitle(r4)});
    L917:
        if (r26.isVideo() == false) goto L926;
        if (r27 == true) goto L924;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L924;
        return LocaleController.formatString(R.string.NotificationMessageGroupText, new Object[]{r23, getTitle(r4), "📹 " + r26.messageOwner.message});
    L924:
        return LocaleController.formatString(R.string.NotificationMessageGroupVideo, new Object[]{r23, getTitle(r4)});
    L926:
        if (r26.isVoice() == false) goto L930;
        return LocaleController.formatString(R.string.NotificationMessageGroupAudio, new Object[]{r23, getTitle(r4)});
    L930:
        if (r26.isRoundVideo() == false) goto L934;
        return LocaleController.formatString(R.string.NotificationMessageGroupRound, new Object[]{r23, getTitle(r4)});
    L934:
        if (r26.isMusic() == true) goto L936;
        TLRPC.MessageMedia r312 = r26.messageOwner.media;
        if ((r312 instanceof TLRPC.TL_messageMediaContact) == false) goto L942;
        TLRPC.TL_messageMediaContact r313 = (TLRPC.TL_messageMediaContact) r312;
        return LocaleController.formatString(R.string.NotificationMessageGroupContact2, new Object[]{r23, getTitle(r4), ContactsController.formatName(r313.first_name, r313.last_name)});
    L942:
        if ((r312 instanceof TLRPC.TL_messageMediaPoll) == false) goto L950;
        TLRPC.TL_messageMediaPoll r314 = (TLRPC.TL_messageMediaPoll) r312;
        if (r314.poll.quiz == false) goto L948;
        return LocaleController.formatString(R.string.NotificationMessageGroupQuiz2, new Object[]{r23, getTitle(r4), r314.poll.question.text});
    L948:
        return LocaleController.formatString(R.string.NotificationMessageGroupPoll2, new Object[]{r23, getTitle(r4), r314.poll.question.text});
    L950:
        if ((r312 instanceof TLRPC.TL_messageMediaToDo) == false) goto L954;
        return LocaleController.formatString(R.string.NotificationMessageGroupTodo2, new Object[]{r23, getTitle(r4), ((TLRPC.TL_messageMediaToDo) r312).todo.title.text});
    L954:
        if ((r312 instanceof TLRPC.TL_messageMediaGame) == false) goto L958;
        return LocaleController.formatString(R.string.NotificationMessageGroupGame, new Object[]{r23, getTitle(r4), r26.messageOwner.media.game.title});
    L958:
        if ((r312 instanceof TLRPC.TL_messageMediaGiveaway) == false) goto L962;
        TLRPC.TL_messageMediaGiveaway r315 = (TLRPC.TL_messageMediaGiveaway) r312;
        return LocaleController.formatString(R.string.NotificationMessageChannelGiveaway, new Object[]{getTitle(r4), Integer.valueOf(r315.quantity), Integer.valueOf(r315.months)});
    L962:
        if ((r312 instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L966;
        return LocaleController.formatString(R.string.BoostingGiveawayResults, new Object[0]);
    L966:
        if ((r312 instanceof TLRPC.TL_messageMediaGeo) == true) goto L1011;
        if ((r312 instanceof TLRPC.TL_messageMediaVenue) == true) goto L1011;
        if ((r312 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L975;
        return LocaleController.formatString(R.string.NotificationMessageGroupLiveLocation, new Object[]{r23, getTitle(r4)});
    L975:
        if ((r312 instanceof TLRPC.TL_messageMediaDocument) == true) goto L977;
        if (r27 == true) goto L1009;
        if (TextUtils.isEmpty(r26.messageText) == true) goto L1009;
        return LocaleController.formatString(R.string.NotificationMessageGroupText, new Object[]{r23, getTitle(r4), r26.messageText});
    L1009:
        return LocaleController.formatString(R.string.NotificationMessageGroupNoText, new Object[]{r23, getTitle(r4)});
    L977:
        if (r26.isSticker() == false) goto L979;
    L997:
        String r117 = r26.getStickerEmoji();
        if (r117 == null) goto L1002;
        return LocaleController.formatString(R.string.NotificationMessageGroupStickerEmoji, new Object[]{r23, getTitle(r4), r117});
    L1002:
        return LocaleController.formatString(R.string.NotificationMessageGroupSticker, new Object[]{r23, getTitle(r4)});
    L979:
        if (r26.isAnimatedSticker() == true) goto L997;
        if (r26.isGif() == false) goto L990;
        if (r27 == true) goto L989;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L989;
        return LocaleController.formatString(R.string.NotificationMessageGroupText, new Object[]{r23, getTitle(r4), "🎬 " + r26.messageOwner.message});
    L989:
        return LocaleController.formatString(R.string.NotificationMessageGroupGif, new Object[]{r23, getTitle(r4)});
    L990:
        if (r27 == true) goto L996;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L996;
        return LocaleController.formatString(R.string.NotificationMessageGroupText, new Object[]{r23, getTitle(r4), "📎 " + r26.messageOwner.message});
    L996:
        return LocaleController.formatString(R.string.NotificationMessageGroupDocument, new Object[]{r23, getTitle(r4)});
    L1011:
        return LocaleController.formatString("NotificationMessageGroupMap", R.string.NotificationMessageGroupMap, new Object[]{r23, getTitle(r4)});
    L936:
        return LocaleController.formatString(R.string.NotificationMessageGroupMusic, new Object[]{r23, getTitle(r4)});
    L904:
        if ((MessageObject.getMedia(r26) instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L907;
        return LocaleController.formatPluralString("NotificationChatMessagePaidMedia", (int) ((TLRPC.TL_messageMediaPaidMedia) MessageObject.getMedia(r26)).stars_amount, new Object[]{r23, getTitle(r4)});
    L368:
        if (r12.getBoolean("EnablePreviewGroup", true) == true) goto L372;
    L360:
        if (r4.megagroup == true) goto L362;
        r32 = true;
        goto L364
    L116:
        if (r9 == 0) goto L356;
        if (r13 == true) goto L119;
    L352:
        if (r29 == null) goto L355;
        r29[0] = false;
    L355:
        return LocaleController.formatString(R.string.NotificationMessageNoText, new Object[]{r23});
    L119:
        if (r12.getBoolean("EnablePreviewAll", true) == false) goto L352;
        TLRPC.Message r42 = r26.messageOwner;
        if ((r42 instanceof TLRPC.TL_messageService) == false) goto L229;
        TLRPC.MessageAction r43 = r42.action;
        if ((r43 instanceof TLRPC.TL_messageActionChangeCreator) == true) goto L227;
        if ((r43 instanceof TLRPC.TL_messageActionNewCreatorPending) == true) goto L227;
        if ((r43 instanceof TLRPC.TL_messageActionSetSameChatWallPaper) == false) goto L132;
        return LocaleController.getString(R.string.WallpaperSameNotification);
    L132:
        if ((r43 instanceof TLRPC.TL_messageActionSetChatWallPaper) == false) goto L136;
        return LocaleController.getString(R.string.WallpaperNotification);
    L136:
        if ((r43 instanceof TLRPC.TL_messageActionGeoProximityReached) == false) goto L140;
        return r26.messageText.toString();
    L140:
        if ((r43 instanceof TLRPC.TL_messageActionTodoCompletions) == false) goto L144;
        return r26.messageText.toString();
    L144:
        if ((r43 instanceof TLRPC.TL_messageActionTodoAppendTasks) == false) goto L148;
        return r26.messageText.toString();
    L148:
        if ((r43 instanceof TLRPC.TL_messageActionUserJoined) == true) goto L225;
        if ((r43 instanceof TLRPC.TL_messageActionContactSignUp) == true) goto L225;
        if ((r43 instanceof TLRPC.TL_messageActionUserUpdatedPhoto) == false) goto L157;
        return LocaleController.formatString(R.string.NotificationContactNewPhoto, new Object[]{r23});
    L157:
        if ((r43 instanceof TLRPC.TL_messageActionLoginUnknownLocation) == false) goto L161;
        String r25 = LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterYear().format(r26.messageOwner.date * 1000), LocaleController.getInstance().getFormatterDay().format(r26.messageOwner.date * 1000)});
        int r316 = R.string.NotificationUnrecognizedDevice;
        String r011 = getUserConfig().getCurrentUser().first_name;
        TLRPC.MessageAction r118 = r26.messageOwner.action;
        return LocaleController.formatString(r316, new Object[]{r011, r25, r118.title, r118.address});
    L161:
        if ((r43 instanceof TLRPC.TL_messageActionGameScore) == true) goto L223;
        if ((r43 instanceof TLRPC.TL_messageActionPaymentSent) == true) goto L223;
        if ((r43 instanceof TLRPC.TL_messageActionPaymentSentMe) == true) goto L223;
        if ((r43 instanceof TLRPC.TL_messageActionStarGift) == true) goto L221;
        if ((r43 instanceof TLRPC.TL_messageActionGiftPremium) == true) goto L221;
        if ((r43 instanceof TLRPC.TL_messageActionGiftTon) == true) goto L221;
        if ((r43 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L179;
        return r26.messageText.toString();
    L179:
        if ((r43 instanceof TLRPC.TL_messageActionSuggestBirthday) == false) goto L183;
        return r26.messageText.toString();
    L183:
        if ((r43 instanceof TLRPC.TL_messageActionPaidMessagesRefunded) == true) goto L219;
        if ((r43 instanceof TLRPC.TL_messageActionPaidMessagesPrice) == true) goto L219;
        if ((r43 instanceof TLRPC.TL_messageActionPhoneCall) == false) goto L196;
        if (r43.video == false) goto L194;
        return LocaleController.getString(R.string.CallMessageVideoIncomingMissed);
    L194:
        return LocaleController.getString(R.string.CallMessageIncomingMissed);
    L196:
        if ((r43 instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L204;
        if (r43.video == false) goto L202;
        return LocaleController.getString(R.string.CallMessageVideoIncomingConferenceMissed);
    L202:
        return LocaleController.getString(R.string.CallMessageIncomingConferenceMissed);
    L204:
        if ((r43 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L1028;
        String r012 = TlUtils.getThemeEmoticonOrGiftTitle(((TLRPC.TL_messageActionSetChatTheme) r43).theme);
        if (TextUtils.isEmpty(r012) == false) goto L213;
        if (r222 != r18) goto L210;
        String r013 = LocaleController.formatString(R.string.ChatThemeDisabledYou, new Object[0]);
    L216:
        r28[0] = true;
        return r013;
    L210:
        r013 = LocaleController.formatString(R.string.ChatThemeDisabled, new Object[]{r23, r012});
        goto L216
    L213:
        if (r222 != r18) goto L215;
        r013 = LocaleController.formatString(R.string.ChatThemeChangedYou, new Object[]{r012});
        goto L216
    L215:
        r013 = LocaleController.formatString(R.string.ChatThemeChangedTo, new Object[]{r23, r012});
    L219:
        return r26.messageText.toString();
    L221:
        return r26.messageText.toString();
    L223:
        return r26.messageText.toString();
    L225:
        return LocaleController.formatString(R.string.NotificationContactJoined, new Object[]{r23});
    L227:
        return r26.messageText.toString();
    L229:
        if (r26.isMediaEmpty() == false) goto L239;
        if (r27 == true) goto L238;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L236;
        String r014 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, r26.messageOwner.message});
        r28[0] = true;
        return r014;
    L236:
        return LocaleController.formatString(R.string.NotificationMessageNoText, new Object[]{r23});
    L238:
        return LocaleController.formatString(R.string.NotificationMessageNoText, new Object[]{r23});
    L239:
        TLRPC.Message r015 = r26.messageOwner;
        if ((r015.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L253;
        if (r27 == true) goto L247;
        if (TextUtils.isEmpty(r015.message) == true) goto L247;
        String r016 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, "🖼 " + r26.messageOwner.message});
        r28[0] = true;
        return r016;
    L247:
        if (r26.messageOwner.media.ttl_seconds == 0) goto L251;
        return LocaleController.formatString(R.string.NotificationMessageSDPhoto, new Object[]{r23});
    L251:
        return LocaleController.formatString(R.string.NotificationMessagePhoto, new Object[]{r23});
    L253:
        if (r26.isVideo() == false) goto L266;
        if (r27 == true) goto L260;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L260;
        String r017 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, "📹 " + r26.messageOwner.message});
        r28[0] = true;
        return r017;
    L260:
        if (r26.messageOwner.media.ttl_seconds == 0) goto L264;
        return LocaleController.formatString(R.string.NotificationMessageSDVideo, new Object[]{r23});
    L264:
        return LocaleController.formatString(R.string.NotificationMessageVideo, new Object[]{r23});
    L266:
        if (r26.isGame() == false) goto L270;
        return LocaleController.formatString(R.string.NotificationMessageGame, new Object[]{r23, r26.messageOwner.media.game.title});
    L270:
        if (r26.isVoice() == false) goto L274;
        return LocaleController.formatString(R.string.NotificationMessageAudio, new Object[]{r23});
    L274:
        if (r26.isRoundVideo() == false) goto L278;
        return LocaleController.formatString(R.string.NotificationMessageRound, new Object[]{r23});
    L278:
        if (r26.isMusic() == true) goto L280;
        TLRPC.MessageMedia r018 = r26.messageOwner.media;
        if ((r018 instanceof TLRPC.TL_messageMediaContact) == false) goto L286;
        TLRPC.TL_messageMediaContact r019 = (TLRPC.TL_messageMediaContact) r018;
        return LocaleController.formatString(R.string.NotificationMessageContact2, new Object[]{r23, ContactsController.formatName(r019.first_name, r019.last_name)});
    L286:
        if ((r018 instanceof TLRPC.TL_messageMediaGiveaway) == false) goto L290;
        TLRPC.TL_messageMediaGiveaway r020 = (TLRPC.TL_messageMediaGiveaway) r018;
        return LocaleController.formatString(R.string.NotificationMessageChannelGiveaway, new Object[]{r23, Integer.valueOf(r020.quantity), Integer.valueOf(r020.months)});
    L290:
        if ((r018 instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L294;
        return LocaleController.formatString(R.string.BoostingGiveawayResults, new Object[0]);
    L294:
        if ((r018 instanceof TLRPC.TL_messageMediaPoll) == false) goto L302;
        TLRPC.Poll r021 = ((TLRPC.TL_messageMediaPoll) r018).poll;
        if (r021.quiz == false) goto L300;
        return LocaleController.formatString(R.string.NotificationMessageQuiz2, new Object[]{r23, r021.question.text});
    L300:
        return LocaleController.formatString(R.string.NotificationMessagePoll2, new Object[]{r23, r021.question.text});
    L302:
        if ((r018 instanceof TLRPC.TL_messageMediaToDo) == false) goto L306;
        return LocaleController.formatString(R.string.NotificationMessageTodo2, new Object[]{r23, ((TLRPC.TL_messageMediaToDo) r018).todo.title.text});
    L306:
        if ((r018 instanceof TLRPC.TL_messageMediaGeo) == true) goto L351;
        if ((r018 instanceof TLRPC.TL_messageMediaVenue) == true) goto L351;
        if ((r018 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L315;
        return LocaleController.formatString(R.string.NotificationMessageLiveLocation, new Object[]{r23});
    L315:
        if ((r018 instanceof TLRPC.TL_messageMediaDocument) == true) goto L317;
        if (r27 == true) goto L349;
        if (TextUtils.isEmpty(r26.messageText) == true) goto L349;
        String r022 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, r26.messageText});
        r28[0] = true;
        return r022;
    L349:
        return LocaleController.formatString(R.string.NotificationMessageNoText, new Object[]{r23});
    L317:
        if (r26.isSticker() == false) goto L319;
    L337:
        String r023 = r26.getStickerEmoji();
        if (r023 == null) goto L342;
        return LocaleController.formatString(R.string.NotificationMessageStickerEmoji, new Object[]{r23, r023});
    L342:
        return LocaleController.formatString(R.string.NotificationMessageSticker, new Object[]{r23});
    L319:
        if (r26.isAnimatedSticker() == true) goto L337;
        if (r26.isGif() == false) goto L330;
        if (r27 == true) goto L329;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L329;
        String r024 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, "🎬 " + r26.messageOwner.message});
        r28[0] = true;
        return r024;
    L329:
        return LocaleController.formatString(R.string.NotificationMessageGif, new Object[]{r23});
    L330:
        if (r27 == true) goto L336;
        if (TextUtils.isEmpty(r26.messageOwner.message) == true) goto L336;
        String r025 = LocaleController.formatString(R.string.NotificationMessageText, new Object[]{r23, "📎 " + r26.messageOwner.message});
        r28[0] = true;
        return r025;
    L336:
        return LocaleController.formatString(R.string.NotificationMessageDocument, new Object[]{r23});
    L351:
        return LocaleController.formatString(R.string.NotificationMessageMap, new Object[]{r23});
    L280:
        return LocaleController.formatString(R.string.NotificationMessageMusic, new Object[]{r23});
    L108:
        r4 = null;
        goto L110
    L81:
        if (r26.isOauthPush == true) goto L82;
        if (r9 > 0) goto L86;
        r222 = r3;
        TLRPC.Chat r210 = getMessagesController().getChat(Long.valueOf(-r9));
        if (r210 == null) goto L99;
        r23 = getTitle(r210);
        goto L101
    L99:
        r23 = null;
        goto L101
    L86:
        if (r26.messageOwner.from_scheduled == true) goto L88;
        TLRPC.User r119 = getMessagesController().getUser(Long.valueOf(r9));
        if (r119 == null) goto L95;
        String r142 = UserObject.getUserName(r119);
    L90:
        r222 = r3;
        r23 = r142;
        goto L101
    L95:
        r142 = null;
        goto L90
    L88:
        if (r3 != r18) goto L91;
        r142 = LocaleController.getString(R.string.MessageScheduledReminderNotification);
        goto L90
    L91:
        r142 = LocaleController.getString(R.string.NotificationMessageScheduledName);
        goto L90
    L73:
        if (r5 == 0) goto L76;
        r3 = -r5;
        goto L78
    L76:
        if (r9 == 0) goto L78;
        r3 = r9;
        goto L78
    L68:
        if (r9 != r18) goto L71;
        r9 = r26.getFromChatId();
        goto L71
    L30:
        if (r5 == 0) goto L32;
    L40:
        if (r5 == 0) goto L60;
        if (r13 == true) goto L43;
    L50:
        if (r29 == null) goto L53;
        r29[0] = false;
    L53:
        if (r26.messageOwner.peer_id.channel_id == 0) goto L59;
        if (r26.isSupergroup() == true) goto L59;
        return LocaleController.formatString(R.string.ChannelMessageNoText, new Object[]{r26.localName});
    L59:
        return LocaleController.formatString(R.string.NotificationMessageGroupNoText, new Object[]{r26.localUserName, r26.localName});
    L43:
        if (r26.localChannel == true) goto L47;
        if (r12.getBoolean("EnablePreviewGroup", true) == false) goto L50;
    L47:
        if (r26.localChannel == false) goto L60;
        if (r12.getBoolean("EnablePreviewChannel", true) == false) goto L50;
    L60:
        r28[0] = true;
        return (String) r26.messageText;
    L32:
        if (r9 == 0) goto L40;
        if (r13 == true) goto L35;
    L36:
        if (r29 == null) goto L39;
        r29[0] = false;
    L39:
        return LocaleController.formatString(R.string.NotificationMessageNoText, new Object[]{r26.localName});
    L35:
        if (r12.getBoolean("EnablePreviewAll", true) == true) goto L60;
    L22:
        if (r26.getForwardedFromId() == null) goto L27;
        r9 = r26.getForwardedFromId().longValue();
        if (r9 >= 0) goto L26;
        r5 = -r9;
        goto L27
    L26:
        r5 = 0;
    L1030:
        return "!" + r26.messageOwner.message;
    L1032:
        return LocaleController.getString(R.string.YouHaveNewMessage);
    }

    private String getTitle(TLRPC.Chat r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if (r2.monoforum == false) goto L10;
        return ForumUtilities.getMonoForumTitle(this.currentAccount, r2);
    L10:
        return r2.title;
    }

    private int getTotalAllUnreadCount() {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= 16) goto L56;
        if (UserConfig.getInstance(r1).isClientActivated() == false) goto L55;
        if (AccountNotifications.shouldShow(r1) == false) goto L55;
        NotificationsController r3 = getInstance(r1);
        if (r3.showBadgeNumber == false) goto L55;
        boolean r4 = r3.showBadgeMessages;
        boolean r5 = r3.showBadgeMuted;
        if (r4 == false) goto L37;
        if (r5 == true) goto L57;
        int r32 = r3.total_unread_count;
    L36:
        r2 = r2 + r32;
        goto L55
    L57:
        ArrayList r33 = new ArrayList(MessagesController.getInstance(r1).allDialogs);     // Catch: Exception -> L28
        int r42 = r33.size();     // Catch: Exception -> L28
        int r52 = 0;
    L18:
        if (r52 >= r42) goto L55;
        TLRPC.Dialog r6 = (TLRPC.Dialog) r33.get(r52);     // Catch: Exception -> L28
        if (r6 != null) goto L22;
    L30:
        if (r6 == null) goto L33;
        r2 = r2 + MessagesController.getInstance(r1).getDialogUnreadCount(r6);
    L33:
        r52 = r52 + 1;
        goto L18
    L22:
        if (DialogObject.isChatDialog(r6.id) == false) goto L30;
        TLRPC.Chat r7 = getMessagesController().getChat(Long.valueOf(-r6.id));     // Catch: Exception -> L28
        if (ChatObject.isNotInChat(r7) == true) goto L33;
        if (ChatObject.isCommunity(r7) == false) goto L30;
    L28:
        e = move-exception;
        FileLog.e(e);
        goto L55
    L37:
        if (r5 == true) goto L59;
        r32 = r3.pushDialogs.size();
        goto L36
    L59:
        int r34 = MessagesController.getInstance(r1).allDialogs.size();     // Catch: Exception -> L47
        int r43 = 0;
    L39:
        if (r43 >= r34) goto L55;
        TLRPC.Dialog r53 = MessagesController.getInstance(r1).allDialogs.get(r43);     // Catch: Exception -> L47
        if (DialogObject.isChatDialog(r53.id) == false) goto L50;
        TLRPC.Chat r62 = getMessagesController().getChat(Long.valueOf(-r53.id));     // Catch: Exception -> L47
        if (ChatObject.isNotInChat(r62) == true) goto L52;
        if (ChatObject.isCommunity(r62) == false) goto L50;
    L52:
        r43 = r43 + 1;
    L50:
        if (MessagesController.getInstance(r1).getDialogUnreadCount(r53) == 0) goto L52;
        r2 = r2 + 1;
    L47:
        e = move-exception;
        FileLog.e(e);
    L55:
        r1 = r1 + 1;
        goto L4
    L56:
        return r2;
    }

    private boolean isEmptyVibration(long[] r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        if (r6.length == 0) goto L19;
        int r0 = 0;
    L9:
        if (r0 >= r6.length) goto L14;
        if (r6[r0] != 0) goto L12;
        r0 = r0 + 1;
        goto L9
    L12:
        return false;
    L14:
        return true;
    L19:
        return false;
    }

    private boolean isPersonalMessage(MessageObject r6) {
        TLRPC.Message r5 = r6.messageOwner;
        TLRPC.Peer r0 = r5.peer_id;
        if (r0 == null) goto L13;
        if (r0.chat_id != 0) goto L13;
        if (r0.channel_id != 0) goto L13;
        TLRPC.MessageAction r52 = r5.action;
        if (r52 != null) goto L11;
        return true;
    L11:
        if ((r52 instanceof TLRPC.TL_messageActionEmpty) == false) goto L13;
        return true;
    L13:
        if (r6.isStoryReactionPush == true) goto L19;
        return false;
    L19:
        return true;
    }

    private boolean isSilentMessage(MessageObject r1) {
        if (r1.messageOwner.silent == false) goto L5;
        return true;
    L5:
        if (r1.isReactionPush == true) goto L11;
        return false;
    L11:
        return true;
    }

    private /* synthetic */ void lambda$cleanup$2() {
        this.openedDialogId = 0;
        this.openedTopicId = 0;
        int r5 = 0;
        this.total_unread_count = 0;
        this.personalCount = 0;
        this.pushMessages.clear();
        this.pushMessagesDict.clear();
        this.fcmRandomMessagesDict.clear();
        this.pushDialogs.clear();
        this.wearNotificationsIds.clear();
        this.lastWearNotifiedMessageId.clear();
        this.openedInBubbleDialogs.clear();
        this.delayedPushMessages.clear();
        this.notifyCheck = false;
        this.lastBadgeCount = 0;
    L7:
        e = move-exception;
        FileLog.e(e);
    L9:
        dismissNotification();
        setBadge(getTotalAllUnreadCount());
        SharedPreferences.Editor r6 = getAccountInstance().getNotificationsSettings().edit();
        r6.clear();
        r6.apply();
        if (Build.VERSION.SDK_INT < 26) goto L36;
        systemNotificationManager.deleteNotificationChannelGroup("channels" + this.currentAccount);     // Catch: Throwable -> L23
        systemNotificationManager.deleteNotificationChannelGroup("groups" + this.currentAccount);     // Catch: Throwable -> L23
        systemNotificationManager.deleteNotificationChannelGroup("private" + this.currentAccount);     // Catch: Throwable -> L23
        systemNotificationManager.deleteNotificationChannelGroup("stories" + this.currentAccount);     // Catch: Throwable -> L23
        systemNotificationManager.deleteNotificationChannelGroup("other" + this.currentAccount);     // Catch: Throwable -> L23
        String r8 = this.currentAccount + "channel";     // Catch: Throwable -> L23
        List<NotificationChannel> r0 = systemNotificationManager.getNotificationChannels();     // Catch: Throwable -> L23
        int r1 = r0.size();     // Catch: Throwable -> L23
    L12:
        if (r5 >= r1) goto L37;
        String r2 = NotificationsController$$ExternalSyntheticApiModelOutline2.m(r0.get(r5)).getId();     // Catch: Throwable -> L23
        if (r2.startsWith(r8) == false) goto L22;
        systemNotificationManager.deleteNotificationChannel(r2);     // Catch: Exception -> L17 Throwable -> L23
    L20:
        if (BuildVars.LOGS_ENABLED == false) goto L22;
        FileLog.d("delete channel cleanup " + r2);     // Catch: Throwable -> L23
    L17:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L23
    L22:
        r5 = r5 + 1;
        goto L12
    L37:
        return;
    L23:
        th = move-exception;
        FileLog.e(th);
        return;
    L36:
        return;
    L4:
        if (this.notificationDelayWakelock.isHeld() == false) goto L9;
        this.notificationDelayWakelock.release();     // Catch: Exception -> L7
        goto L9
    }

    private /* synthetic */ void lambda$deleteAllNotificationChannels$44() {
        SharedPreferences r5 = getAccountInstance().getNotificationsSettings();     // Catch: Exception -> L15
        Map<String, ?> r0 = r5.getAll();     // Catch: Exception -> L15
        SharedPreferences.Editor r52 = r5.edit();     // Catch: Exception -> L15
        Iterator<Map.Entry<String, ?>> r02 = r0.entrySet().iterator();     // Catch: Exception -> L15
    L4:
        if (r02.hasNext() == false) goto L13;
        Map.Entry<String, ?> r1 = r02.next();     // Catch: Exception -> L15
        String r2 = r1.getKey();     // Catch: Exception -> L15
        if (r2.startsWith("org.telegram.key") == false) goto L4;
        if (r2.endsWith("_s") == true) goto L12;
        String r12 = (String) r1.getValue();     // Catch: Exception -> L15
        systemNotificationManager.deleteNotificationChannel(r12);     // Catch: Exception -> L15
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("delete all channel " + r12);     // Catch: Exception -> L15
    L12:
        r52.remove(r2);     // Catch: Exception -> L15
        goto L4
    L13:
        r52.apply();     // Catch: Exception -> L15
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$deleteNotificationChannel$42(long r1, long r3, int r5) {
        deleteNotificationChannelInternal(r1, r3, r5);
    }

    private /* synthetic */ void lambda$deleteNotificationChannelGlobal$43(int r1, int r2) {
        deleteNotificationChannelGlobalInternal(r1, r2);
    }

    private /* synthetic */ void lambda$didReceivedNotification$38(String r2) {
        if (this.pendingVoiceLoads.remove(r2) == false) goto L6;
        showOrUpdateNotification(true);
        return;
    }

    private /* synthetic */ void lambda$forceShowPopupForReply$6(ArrayList r3) {
        this.popupReplyMessages = r3;
        Intent r32 = new Intent(ApplicationLoader.applicationContext, PopupNotificationActivity.class);
        r32.putExtra("force", true);
        r32.putExtra("currentAccount", this.currentAccount);
        r32.setFlags(268763140);
        ApplicationLoader.applicationContext.startActivity(r32);
        Intent r2 = new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        ApplicationLoader.applicationContext.sendBroadcast(r2);
    }

    private /* synthetic */ void lambda$forceShowPopupForReply$7() {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= this.pushMessages.size()) goto L19;
        MessageObject r3 = this.pushMessages.get(r2);
        long r4 = r3.getDialogId();
        TLRPC.Message r6 = r3.messageOwner;
        if (r6.mentioned == false) goto L10;
        if ((r6.action instanceof TLRPC.TL_messageActionPinMessage) == false) goto L10;
    L17:
        r2 = r2 + 1;
    L10:
        if (DialogObject.isEncryptedDialog(r4) == true) goto L17;
        if (r3.messageOwner.peer_id.channel_id != 0) goto L14;
    L16:
        r0.add(0, r3);
        goto L17
    L14:
        if (r3.isSupergroup() == true) goto L16;
    L19:
        if (r0.isEmpty() == false) goto L21;
        return;
    L21:
        if (AndroidUtilities.needShowPasscode() == false) goto L23;
        return;
    L23:
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L33;
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda45(this, r0));
        return;
    }

    private /* synthetic */ void lambda$hideNotifications$36() {
        notificationManager.cancel(this.notificationId);
        this.lastWearNotifiedMessageId.clear();
        int r0 = 0;
    L3:
        int r1 = this.wearNotificationsIds.size();
        LongSparseArray<Integer> r2 = this.wearNotificationsIds;
        if (r0 >= r1) goto L6;
        notificationManager.cancel(r2.valueAt(r0).intValue());
        r0 = r0 + 1;
        goto L3
    L6:
        r2.clear();
    }

    private /* synthetic */ void lambda$loadTopicsNotificationsExceptions$54(long r8, Consumer r10) {
        HashSet r0 = new HashSet();
        Iterator<Map.Entry<String, ?>> r1 = MessagesController.getNotificationsSettings(this.currentAccount).getAll().entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L15;
        String r2 = r1.next().getKey();
        if (r2 == null) goto L4;
        if (r2.startsWith(NotificationsSettingsFacade.PROPERTY_NOTIFY + r8) == false) goto L4;
        int r22 = Utilities.parseInt(r2.replace(NotificationsSettingsFacade.PROPERTY_NOTIFY + r8, _UrlKt.FRAGMENT_ENCODE_SET));
        if (r22 == 0) goto L4;
        if (getMessagesController().isDialogMuted(r8, r22) == getMessagesController().isDialogMuted(r8, 0)) goto L4;
        r0.add(Integer.valueOf(r22));
        goto L4
    L15:
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda46(r10, r0));
    }

    private /* synthetic */ void lambda$new$0() {
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.d("delay reached");
    L6:
        if (this.delayedPushMessages.isEmpty() == true) goto L15;
        showOrUpdateNotification(true);
        this.delayedPushMessages.clear();
    L15:
    L12:
        e = move-exception;
        FileLog.e(e);
        return;
    L9:
        if (this.notificationDelayWakelock.isHeld() == false) goto L17;
        this.notificationDelayWakelock.release();     // Catch: Exception -> L12
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        getNotificationCenter().addObserver(this, NotificationCenter.fileLoaded);
    }

    private /* synthetic */ void lambda$playInChatSound$40() {
        if (Math.abs(SystemClock.elapsedRealtime() - this.lastSoundPlay) <= 500) goto L29;
    L21:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        if (this.soundPool != null) goto L9;
        SoundPool r0 = new SoundPool(3, 1, 0);     // Catch: Exception -> L21
        this.soundPool = r0;     // Catch: Exception -> L21
        r0.setOnLoadCompleteListener(new NotificationsController$$ExternalSyntheticLambda56());     // Catch: Exception -> L21
    L9:
        if (this.soundIn == 0) goto L11;
    L13:
        int r1 = this.soundIn;     // Catch: Exception -> L21
        if (r1 == 0) goto L28;
        this.soundPool.play(r1, 1.0f, 1.0f, 1, 0, 1.0f);     // Catch: Exception -> L17
    L30:
        return;
    L17:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L21
        return;
    L28:
        return;
    L11:
        if (this.soundInLoaded == true) goto L13;
        this.soundInLoaded = true;     // Catch: Exception -> L21
        this.soundIn = this.soundPool.load(ApplicationLoader.applicationContext, R.raw.sound_in, 1);     // Catch: Exception -> L21
        goto L13
    }

    private /* synthetic */ void lambda$playOutChatSound$49() {
    L21:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (Math.abs(SystemClock.elapsedRealtime() - this.lastSoundOutPlay) <= 100) goto L27;
        this.lastSoundOutPlay = SystemClock.elapsedRealtime();     // Catch: Exception -> L21
        if (this.soundPool != null) goto L9;
        SoundPool r0 = new SoundPool(3, 1, 0);     // Catch: Exception -> L21
        this.soundPool = r0;     // Catch: Exception -> L21
        r0.setOnLoadCompleteListener(new NotificationsController$$ExternalSyntheticLambda22());     // Catch: Exception -> L21
    L9:
        if (this.soundOut == 0) goto L11;
    L13:
        int r1 = this.soundOut;     // Catch: Exception -> L21
        if (r1 == 0) goto L28;
        this.soundPool.play(r1, 1.0f, 1.0f, 1, 0, 1.0f);     // Catch: Exception -> L17
    L29:
        return;
    L17:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L21
        return;
    L28:
        return;
    L11:
        if (this.soundOutLoaded == true) goto L13;
        this.soundOutLoaded = true;     // Catch: Exception -> L21
        this.soundOut = this.soundPool.load(ApplicationLoader.applicationContext, R.raw.sound_out, 1);     // Catch: Exception -> L21
        goto L13
    }

    private /* synthetic */ void lambda$processDeleteStory$15(long r6, int r8) {
        StoryNotification r0 = this.storyPushMessagesDict.get(r6);
        if (r0 == null) goto L8;
        r0.dateByIds.remove(Integer.valueOf(r8));
        if (r0.dateByIds.isEmpty() == false) goto L7;
        this.storyPushMessagesDict.remove(r6);
        this.storyPushMessages.remove(r0);
        getMessagesStorage().deleteStoryPushMessage(r6);
        boolean r62 = true;
    L9:
        int r7 = 0;
    L11:
        if (r7 >= this.pushMessages.size()) goto L27;
        MessageObject r02 = this.pushMessages.get(r7);
        if (r02 == null) goto L26;
        if (r02.isLiveStoryPush == false) goto L26;
        if (r02.getId() != r8) goto L26;
        this.pushMessages.remove(r7);
        r7 = r7 - 1;
        SparseArray<MessageObject> r63 = this.pushMessagesDict.get(r02.getDialogId());
        if (r63 == null) goto L21;
        r63.remove(r02.getId());
    L21:
        if (r63 != null) goto L23;
    L25:
        r62 = true;
        goto L26
    L23:
        if (r63.size() > 0) goto L25;
        this.pushMessagesDict.remove(r02.getDialogId());
    L26:
        r7 = r7 + 1;
        goto L11
    L27:
        if (r62 == false) goto L35;
        showOrUpdateNotification(false);
        return;
    L35:
        return;
    L7:
        getMessagesStorage().putStoryPushMessage(r0);
    L8:
        r62 = false;
        goto L9
    }

    private /* synthetic */ void lambda$processDialogsUpdateRead$28(ArrayList r6) {
        int r0 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.popupMessages.remove(r6.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$processDialogsUpdateRead$29(int r4) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(this.currentAccount)});
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(r4)});
    }

    private /* synthetic */ void lambda$processDialogsUpdateRead$30(LongSparseIntArray r18, ArrayList r19) {
        int r8 = this.total_unread_count;
        SharedPreferences r1 = getAccountInstance().getNotificationsSettings();
        int r10 = 0;
    L3:
        boolean r11 = true;
        int r112 = 1;
        if (r10 >= r18.size()) goto L90;
        long r2 = r18.keyAt(r10);
        Integer r12 = this.pushDialogs.get(r2);
        int r4 = r18.get(r2);
        if (DialogObject.isChatDialog(r2) == false) goto L18;
        TLRPC.Chat r5 = getMessagesController().getChat(Long.valueOf(-r2));
        if (r5 != null) goto L10;
    L15:
        r4 = 0;
    L16:
        if (r5 == null) goto L18;
        boolean r52 = r5.forum;
        int r13 = r4;
    L19:
        if (r52 == true) goto L27;
        int r42 = getNotifyOverride(r1, r2, 0);
        if (r42 != (-1)) goto L24;
        boolean r43 = isGlobalNotificationsEnabled(r2, false, false);
    L29:
        if (this.notifyCheck == false) goto L36;
        if (r43 == true) goto L36;
        Integer r53 = this.pushDialogsOverrideMention.get(r2);
        if (r53 == null) goto L36;
        if (r53.intValue() == 0) goto L36;
        r13 = r53.intValue();
        r43 = true;
    L36:
        if (r13 != 0) goto L38;
        this.smartNotificationsDialogs.remove(r2);
    L38:
        if (r13 >= 0) goto L42;
        if (r12 == null) goto L88;
        r13 = r13 + r12.intValue();
    L88:
        r10 = r10 + 1;
    L42:
        if (r43 == true) goto L44;
        if (r13 == 0) goto L44;
    L53:
        if (r13 != 0) goto L76;
        this.pushDialogs.remove(r2);
        this.pushDialogsOverrideMention.remove(r2);
        int r44 = 0;
    L56:
        if (r44 >= this.pushMessages.size()) goto L88;
        MessageObject r54 = this.pushMessages.get(r44);
        if (r54.messageOwner.from_scheduled == true) goto L75;
        if (r54.getDialogId() != r2) goto L75;
        if (r54.isStoryReactionPush == true) goto L75;
        if (isPersonalMessage(r54) == false) goto L66;
        this.personalCount--;
    L66:
        this.pushMessages.remove(r44);
        r44 = r44 - 1;
        this.delayedPushMessages.remove(r54);
        long r122 = r54.messageOwner.peer_id.channel_id;
        long r14 = 0;
        if (r122 == 0) goto L69;
        r14 = -r122;
    L69:
        SparseArray<MessageObject> r123 = this.pushMessagesDict.get(r14);
        if (r123 == null) goto L74;
        r123.remove(r54.getId());
        if (r123.size() != 0) goto L74;
        this.pushMessagesDict.remove(r14);
    L74:
        r19.add(r54);
    L75:
        r44 = r44 + 1;
        goto L56
    L76:
        if (r43 == false) goto L88;
        if (getMessagesController().isCommunity(r2) == true) goto L87;
        boolean r45 = getMessagesController().isForum(r2);
        int r55 = this.total_unread_count;
        if (r45 == false) goto L86;
        if (r13 > 0) goto L85;
        r112 = 0;
    L85:
        this.total_unread_count = r55 + r112;
        goto L87
    L86:
        this.total_unread_count = r55 + r13;
    L87:
        this.pushDialogs.put(r2, Integer.valueOf(r13));
    L44:
        if (r12 == null) goto L53;
        boolean r56 = getMessagesController().isForum(r2);
        int r142 = this.total_unread_count;
        if (r56 == true) goto L48;
        this.total_unread_count = r142 - r12.intValue();
        goto L53
    L48:
        if (r12.intValue() <= 0) goto L50;
        int r57 = 1;
    L51:
        this.total_unread_count = r142 - r57;
        goto L53
    L50:
        r57 = 0;
        goto L51
    L24:
        if (r42 != 2) goto L27;
        r43 = false;
    L27:
        r43 = true;
        goto L29
    L10:
        if (r5.min == true) goto L15;
        if (ChatObject.isNotInChat(r5) == true) goto L15;
        if (ChatObject.isCommunity(r5) == false) goto L16;
    L18:
        r13 = r4;
        r52 = false;
        goto L19
    L90:
        if (r19.isEmpty() == true) goto L93;
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda9(this, r19));
    L93:
        if (r8 != this.total_unread_count) goto L95;
    L103:
        this.notifyCheck = false;
        if (this.showBadgeNumber == false) goto L116;
        setBadge(getTotalAllUnreadCount());
        return;
    L116:
        return;
    L95:
        if (this.notifyCheck == true) goto L98;
        this.delayedPushMessages.clear();
        showOrUpdateNotification(this.notifyCheck);
    L102:
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda10(this, this.pushDialogs.size()));
        goto L103
    L98:
        if (this.lastOnlineFromOtherDevice > getConnectionsManager().getCurrentTime()) goto L101;
        r11 = false;
    L101:
        scheduleNotificationDelay(r11);
        goto L102
    }

    private /* synthetic */ void lambda$processEditedMessages$22(LongSparseArray r14) {
        int r0 = r14.size();
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 >= r0) goto L33;
        r14.keyAt(r2);
        ArrayList r4 = (ArrayList) r14.valueAt(r2);
        int r5 = r4.size();
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L32;
        MessageObject r7 = (MessageObject) r4.get(r6);
        if (r7.isStoryReactionPush == false) goto L9;
        long r8 = r7.getDialogId();
    L13:
        SparseArray<MessageObject> r82 = this.pushMessagesDict.get(r8);
        if (r82 == null) goto L32;
        MessageObject r9 = r82.get(r7.getId());
        if (r9 != null) goto L19;
    L23:
        if (r9 == null) goto L31;
        r82.put(r7.getId(), r7);
        int r32 = this.pushMessages.indexOf(r9);
        if (r32 < 0) goto L27;
        this.pushMessages.set(r32, r7);
    L27:
        int r33 = this.delayedPushMessages.indexOf(r9);
        if (r33 < 0) goto L30;
        this.delayedPushMessages.set(r33, r7);
    L30:
        r3 = true;
    L31:
        r6 = r6 + 1;
        goto L5
    L19:
        if (r9.isReactionPush == false) goto L21;
    L22:
        r9 = null;
        goto L23
    L21:
        if (r9.isStoryReactionPush == false) goto L23;
    L9:
        long r83 = r7.messageOwner.peer_id.channel_id;
        if (r83 == 0) goto L12;
        r8 = -r83;
        goto L13
    L12:
        r8 = 0;
    L32:
        r2 = r2 + 1;
        goto L3
    L33:
        if (r3 == false) goto L41;
        showOrUpdateNotification(false);
        return;
    }

    private /* synthetic */ void lambda$processIgnoreStories$17() {
        boolean r0 = this.storyPushMessages.isEmpty();
        this.storyPushMessages.clear();
        this.storyPushMessagesDict.clear();
        getMessagesStorage().deleteAllStoryPushMessages();
        if (r0 == true) goto L6;
        showOrUpdateNotification(false);
        return;
    }

    private /* synthetic */ void lambda$processIgnoreStories$19(long r3) {
        boolean r0 = this.storyPushMessages.isEmpty();
        this.storyPushMessages.clear();
        this.storyPushMessagesDict.clear();
        getMessagesStorage().deleteStoryPushMessage(r3);
        if (r0 == true) goto L6;
        showOrUpdateNotification(false);
        return;
    }

    private /* synthetic */ void lambda$processIgnoreStoryReactions$18() {
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= this.pushMessages.size()) goto L18;
        MessageObject r3 = this.pushMessages.get(r1);
        if (r3 == null) goto L17;
        if (r3.isStoryReactionPush == false) goto L17;
        this.pushMessages.remove(r1);
        r1 = r1 - 1;
        SparseArray<MessageObject> r22 = this.pushMessagesDict.get(r3.getDialogId());
        if (r22 == null) goto L12;
        r22.remove(r3.getId());
    L12:
        if (r22 != null) goto L14;
    L16:
        r2 = true;
        goto L17
    L14:
        if (r22.size() > 0) goto L16;
        this.pushMessagesDict.remove(r3.getDialogId());
    L17:
        r1 = r1 + 1;
        goto L4
    L18:
        getMessagesStorage().deleteAllStoryReactionPushMessages();
        if (r2 == false) goto L26;
        showOrUpdateNotification(false);
        return;
    }

    private /* synthetic */ void lambda$processLoadedUnreadMessages$32(int r4) {
        if (this.total_unread_count != 0) goto L5;
        this.popupMessages.clear();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(this.currentAccount)});
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(r4)});
    }

    private /* synthetic */ void lambda$processLoadedUnreadMessages$33(ArrayList r27, LongSparseArray r28, ArrayList r29, Collection r30) {
        NotificationsController r0 = this;
        r0.pushDialogs.clear();
        r0.pushMessages.clear();
        r0.pushMessagesDict.clear();
        r0.storyPushMessages.clear();
        r0.storyPushMessagesDict.clear();
        boolean r9 = false;
        r0.total_unread_count = 0;
        r0.personalCount = 0;
        SharedPreferences r1 = r0.getAccountInstance().getNotificationsSettings();
        LongSparseArray r10 = new LongSparseArray();
        long r13 = 0;
        if (r27 == null) goto L71;
        int r2 = 0;
    L6:
        if (r2 >= r27.size()) goto L71;
        TLRPC.Message r3 = (TLRPC.Message) r27.get(r2);
        if (r3 != null) goto L11;
    L9:
        SharedPreferences r5 = r1;
        int r20 = r2;
        long r16 = r13;
    L70:
        r2 = r20 + 1;
        r1 = r5;
        r13 = r16;
        r9 = false;
        goto L6
    L11:
        TLRPC.MessageFwdHeader r4 = r3.fwd_from;
        if (r4 != null) goto L14;
    L15:
        TLRPC.MessageAction r42 = r3.action;
        if ((r42 instanceof TLRPC.TL_messageActionSetMessagesTTL) == true) goto L9;
        if (r3.silent == true) goto L20;
    L24:
        long r43 = r3.peer_id.channel_id;
        if (r43 == r13) goto L27;
        long r44 = -r43;
        r16 = r13;
    L28:
        SparseArray<MessageObject> r132 = r0.pushMessagesDict.get(r44);
        if (r132 != null) goto L31;
    L33:
        MessageObject r14 = new MessageObject(r0.currentAccount, r3, r9, r9);
        if (r0.isPersonalMessage(r14) == false) goto L36;
        r0.personalCount++;
    L36:
        long r11 = r14.getDialogId();
        long r202 = MessageObject.getTopicId(r0.currentAccount, r14.messageOwner, getMessagesController().isForum(r14));
        if (r14.messageOwner.mentioned == false) goto L39;
        int r92 = r2;
        TLRPC.Message r02 = r3;
        long r22 = r14.getFromChatId();
    L40:
        int r15 = r10.indexOfKey(r22);
        if (r15 >= 0) goto L43;
    L45:
        r20 = r92;
        long r8 = r44;
        TLRPC.Message r152 = r02;
        r0 = this;
        int r45 = r0.getNotifyOverride(r1, r22, r202);
        if (r45 != (-1)) goto L50;
        boolean r52 = r0.isGlobalNotificationsEnabled(r22, r14.isReactionPush, r14.isStoryReactionPush);
    L48:
        TLRPC.Message r46 = r152;
        r10.put(r22, Boolean.valueOf(r52));
        boolean r153 = r52;
    L54:
        r5 = r1;
        if (r153 == false) goto L70;
        long r21 = r22;
        if (r21 == r0.openedDialogId) goto L59;
    L61:
        if (r132 != null) goto L63;
        r132 = new SparseArray();
        r0.pushMessagesDict.put(r8, r132);
    L63:
        r132.put(r46.id, r14);
        r0.appendMessage(r14);
        if (r11 == r21) goto L70;
        Integer r12 = r0.pushDialogsOverrideMention.get(r11);
        LongSparseArray<Integer> r23 = r0.pushDialogsOverrideMention;
        if (r12 != null) goto L68;
        int r17 = 1;
    L69:
        r23.put(r11, Integer.valueOf(r17));
        goto L70
    L68:
        r17 = r12.intValue() + 1;
        goto L69
    L59:
        if (ApplicationLoader.isScreenOn == false) goto L61;
    L50:
        if (r45 == 2) goto L52;
        r52 = true;
        goto L48
    L52:
        r52 = false;
        goto L48
    L43:
        if (r202 != r16) goto L45;
        r153 = ((Boolean) r10.valueAt(r15)).booleanValue();
        r20 = r92;
        r8 = r44;
        r46 = r02;
        r0 = this;
        goto L54
    L39:
        r92 = r2;
        r02 = r3;
        r22 = r11;
        goto L40
    L31:
        if (r132.indexOfKey(r3.id) < 0) goto L33;
        r5 = r1;
        r20 = r2;
        goto L70
    L27:
        r44 = r13;
        r16 = r44;
        goto L28
    L20:
        if ((r42 instanceof TLRPC.TL_messageActionContactSignUp) == true) goto L9;
        if ((r42 instanceof TLRPC.TL_messageActionUserJoined) == false) goto L24;
    L14:
        if (r4.imported == true) goto L9;
    L71:
        SharedPreferences r53 = r1;
        long r162 = r13;
        int r6 = 0;
    L73:
        if (r6 >= r28.size()) goto L98;
        long r24 = r28.keyAt(r6);
        int r18 = r10.indexOfKey(r24);
        if (r18 < 0) goto L77;
        boolean r47 = ((Boolean) r10.valueAt(r18)).booleanValue();
        SharedPreferences r19 = r53;
        int r82 = 0;
    L85:
        if (r47 == false) goto L97;
        Integer r48 = (Integer) r28.valueAt(r6);
        int r54 = r48.intValue();
        r0.pushDialogs.put(r24, r48);
        if (r0.getMessagesController().isCommunity(r24) == true) goto L97;
        boolean r25 = r0.getMessagesController().isForum(r24);
        int r32 = r0.total_unread_count;
        if (r25 == false) goto L96;
        if (r54 <= 0) goto L94;
        int r26 = 1;
    L95:
        r0.total_unread_count = r32 + r26;
        goto L97
    L94:
        r26 = r82;
        goto L95
    L96:
        r0.total_unread_count = r32 + r54;
    L97:
        r6 = r6 + 1;
        r53 = r19;
        goto L73
    L77:
        r19 = r53;
        int r49 = r0.getNotifyOverride(r19, r24, 0);
        if (r49 != (-1)) goto L80;
        r82 = 0;
        r47 = r0.isGlobalNotificationsEnabled(r24, false, false);
    L84:
        r10.put(r24, Boolean.valueOf(r47));
        goto L85
    L80:
        r82 = 0;
        if (r49 == 2) goto L83;
        r47 = true;
        goto L84
    L83:
        r47 = false;
        goto L84
    L98:
        SharedPreferences r110 = r53;
        if (r29 == null) goto L179;
        int r62 = 0;
    L102:
        if (r62 >= r29.size()) goto L179;
        MessageObject r93 = (MessageObject) r29.get(r62);
        int r112 = r93.getId();
        if (r0.pushMessagesDict.indexOfKey(r112) < 0) goto L107;
    L178:
        r62 = r62 + 1;
        goto L102
    L107:
        if (r0.isPersonalMessage(r93) == false) goto L109;
        r0.personalCount++;
    L109:
        long r122 = r93.getDialogId();
        long r410 = MessageObject.getTopicId(r0.currentAccount, r93.messageOwner, r0.getMessagesController().isForum(r93));
        TLRPC.Message r210 = r93.messageOwner;
        long r83 = r210.random_id;
        if (r210.mentioned == false) goto L112;
        long r211 = r93.getFromChatId();
    L113:
        int r142 = r10.indexOfKey(r211);
        if (r142 >= 0) goto L116;
    L118:
        int r411 = r0.getNotifyOverride(r110, r211, r410);
        if (r411 != (-1)) goto L121;
        MessageObject r143 = r93;
        boolean r412 = r0.isGlobalNotificationsEnabled(r211, r143.isReactionPush, r143.isStoryReactionPush);
    L125:
        r10.put(r211, Boolean.valueOf(r412));
    L126:
        if (r412 == false) goto L178;
        if (r211 == r0.openedDialogId) goto L130;
    L132:
        if (r112 == 0) goto L145;
        if (r143.isStoryReactionPush == false) goto L136;
        long r413 = r143.getDialogId();
    L140:
        SparseArray<MessageObject> r84 = r0.pushMessagesDict.get(r413);
        if (r84 != null) goto L143;
        r84 = new SparseArray();
        r0.pushMessagesDict.put(r413, r84);
    L143:
        r84.put(r112, r143);
    L147:
        r0.appendMessage(r143);
        if (r122 == r211) goto L154;
        Integer r414 = r0.pushDialogsOverrideMention.get(r122);
        LongSparseArray<Integer> r55 = r0.pushDialogsOverrideMention;
        if (r414 != null) goto L152;
        int r415 = 1;
    L153:
        r55.put(r122, Integer.valueOf(r415));
        goto L154
    L152:
        r415 = r414.intValue() + 1;
    L154:
        Integer r416 = r0.pushDialogs.get(r211);
        if (r416 == null) goto L157;
        int r56 = r416.intValue() + 1;
    L159:
        if (r0.getMessagesController().isCommunity(r211) == false) goto L162;
    L177:
        r0.pushDialogs.put(r211, Integer.valueOf(r56));
        goto L178
    L162:
        if (r0.getMessagesController().isForum(r211) == false) goto L174;
        if (r416 == null) goto L169;
        int r85 = r0.total_unread_count;
        if (r416.intValue() <= 0) goto L167;
        int r417 = 1;
    L168:
        r0.total_unread_count = r85 - r417;
        goto L169
    L167:
        r417 = 0;
    L169:
        int r418 = r0.total_unread_count;
        if (r56 <= 0) goto L172;
        int r86 = 1;
    L173:
        r0.total_unread_count = r418 + r86;
        goto L177
    L172:
        r86 = 0;
        goto L173
    L174:
        if (r416 == null) goto L176;
        r0.total_unread_count -= r416.intValue();
    L176:
        r0.total_unread_count += r56;
        goto L177
    L157:
        r56 = 1;
        goto L159
    L136:
        long r419 = r143.messageOwner.peer_id.channel_id;
        if (r419 == r162) goto L139;
        r413 = -r419;
        goto L140
    L139:
        r413 = r162;
        goto L140
    L145:
        if (r83 == r162) goto L147;
        r0.fcmRandomMessagesDict.put(r83, r143);
        goto L147
    L130:
        if (ApplicationLoader.isScreenOn == false) goto L132;
    L121:
        r143 = r93;
        if (r411 == 2) goto L124;
        r412 = true;
        goto L125
    L124:
        r412 = false;
        goto L125
    L116:
        if (r410 != r162) goto L118;
        r412 = ((Boolean) r10.valueAt(r142)).booleanValue();
        r143 = r93;
        goto L126
    L112:
        r211 = r122;
    L179:
        if (r30 == null) goto L188;
        Iterator r111 = r30.iterator();
    L182:
        if (r111.hasNext() == false) goto L187;
        StoryNotification r212 = (StoryNotification) r111.next();
        long r33 = r212.dialogId;
        StoryNotification r57 = r0.storyPushMessagesDict.get(r33);
        if (r57 != null) goto L185;
        r0.storyPushMessages.add(r212);
        r0.storyPushMessagesDict.put(r33, r212);
        goto L182
    L185:
        r57.dateByIds.putAll(r212.dateByIds);
        goto L182
    L187:
        Collections.sort(r0.storyPushMessages, Comparator.comparingLong(new NotificationsController$$ExternalSyntheticLambda34()));
    L188:
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda35(r0, r0.pushDialogs.size()));
        if ((SystemClock.elapsedRealtime() / 1000) >= 60) goto L191;
        boolean r94 = true;
    L192:
        r0.showOrUpdateNotification(r94);
        if (r0.showBadgeNumber == false) goto L218;
        r0.setBadge(r0.getTotalAllUnreadCount());
        return;
    L218:
        return;
    L191:
        r94 = false;
        goto L192
    }

    private /* synthetic */ void lambda$processNewMessages$24(int r4) {
        LongSparseArray<ArrayList<Integer>> r0 = new LongSparseArray();
        r0.put(0, Lists.newArrayList(new Integer[]{Integer.valueOf(r4)}));
        removeDeletedMessagesFromNotifications(r0, false);
    }

    private /* synthetic */ void lambda$processNewMessages$25(ArrayList r2, int r3) {
        this.popupMessages.addAll(0, r2);
        if (ApplicationLoader.mainInterfacePaused == true) goto L7;
        if (ApplicationLoader.isScreenOn == false) goto L7;
        return;
    L7:
        if (r3 != 3) goto L9;
    L16:
        Intent r1 = new Intent(ApplicationLoader.applicationContext, PopupNotificationActivity.class);
        r1.setFlags(268763140);
        ApplicationLoader.applicationContext.startActivity(r1);     // Catch: Throwable -> L19
        return;
    L25:
        return;
    L9:
        if (r3 != 1) goto L13;
        if (ApplicationLoader.isScreenOn == true) goto L16;
    L13:
        if (r3 == 2) goto L15;
        return;
    L15:
        if (ApplicationLoader.isScreenOn == false) goto L16;
    }

    private /* synthetic */ void lambda$processNewMessages$26(int r4) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(this.currentAccount)});
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(r4)});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v5, types: [int] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r24v4 */
    /* JADX WARN: Type inference failed for: r24v5, types: [int] */
    /* JADX WARN: Type inference failed for: r24v6 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [int] */
    /* JADX WARN: Type inference failed for: r9v4 */
    private /* synthetic */ void lambda$processNewMessages$27(ArrayList r40, ArrayList r41, boolean r42, boolean r43, CountDownLatch r44) {
        NotificationsController r0 = this;
        ArrayList r7 = r40;
        LongSparseArray r8 = new LongSparseArray();
        SharedPreferences r1 = r0.getAccountInstance().getNotificationsSettings();
        boolean r9 = true;
        boolean r10 = r1.getBoolean("PinnedMessages", true);
        int r12 = 0;
        boolean r13 = false;
        int r14 = 0;
        boolean r15 = false;
        boolean r16 = false;
        boolean r17 = false;
    L4:
        if (r12 >= r7.size()) goto L188;
        MessageObject r2 = (MessageObject) r7.get(r12);
        if (r2.messageOwner == null) goto L18;
        if (r2.isImportedForward() == true) goto L20;
        TLRPC.Message r5 = r2.messageOwner;
        TLRPC.MessageAction r3 = r5.action;
        if ((r3 instanceof TLRPC.TL_messageActionSetMessagesTTL) == true) goto L20;
        if (r5.silent == false) goto L18;
        if ((r3 instanceof TLRPC.TL_messageActionContactSignUp) == true) goto L20;
        if ((r3 instanceof TLRPC.TL_messageActionUserJoined) == false) goto L18;
    L20:
        if (BuildVars.LOGS_ENABLED == false) goto L22;
        FileLog.d("skipped message because 1");
    L22:
        SharedPreferences r26 = r1;
    L38:
        boolean r22 = r10;
        int r23 = r12;
    L187:
        r12 = r23 + 1;
        r7 = r40;
        r10 = r22;
        r1 = r26;
        r9 = true;
    L18:
        if (MessageObject.isTopicActionMessage(r2) == true) goto L20;
        if (r2.isStoryPush == false) goto L40;
        if (r2.messageOwner != null) goto L29;
        long r32 = System.currentTimeMillis();
    L28:
        long r232 = r32;
        long r33 = r2.getDialogId();
        int r222 = r2.getId();
        StoryNotification r52 = r0.storyPushMessagesDict.get(r33);
        if (r52 == null) goto L36;
        r52.dateByIds.put(Integer.valueOf(r222), new Pair(Long.valueOf(r232), Long.valueOf(r232 + DurationKt.MILLIS_IN_DAY)));
        boolean r34 = r52.hidden;
        boolean r24 = r2.isStoryPushHidden;
        if (r34 == r24) goto L35;
        r52.hidden = r24;
        r17 = r9;
    L35:
        r52.date = r52.getLeastDate();
        r0.getMessagesStorage().putStoryPushMessage(r52);
        r15 = r9;
    L37:
        Collections.sort(r0.storyPushMessages, Comparator.comparingLong(new NotificationsController$$ExternalSyntheticLambda26()));
        r26 = r1;
        goto L38
    L36:
        StoryNotification r18 = new StoryNotification(r33, r2.localName, r222, r232);
        r18.hidden = r2.isStoryPushHidden;
        r0.storyPushMessages.add(r18);
        r0.storyPushMessagesDict.put(r33, r18);
        r0.getMessagesStorage().putStoryPushMessage(r18);
        r13 = r9;
        r17 = r13;
        goto L37
    L29:
        r32 = r3.date * 1000;
        goto L28
    L40:
        if (r2.isOauthPush == false) goto L48;
        TLRPC.Message r35 = r2.messageOwner;
        if (r35 == null) goto L22;
        int r4 = r35.id;
        boolean r11 = r9;
        r22 = r10;
        long r92 = r35.date + 60;
        boolean r242 = r11;
        r23 = r12;
        long r112 = ConnectionsManager.getInstance(r0.currentAccount).getCurrentTime();
        if (r112 > r92) goto L103;
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda27(r0, r4), (r92 - r112) * 1000);
    L49:
        int r93 = r2.getId();
        if (r2.isFcmMessage() == false) goto L53;
        long r36 = r2.messageOwner.random_id;
    L54:
        long r102 = r2.getDialogId();
        if (r2.isFcmMessage() == false) goto L58;
        boolean r53 = r2.localChannel;
    L66:
        if (r2.isStoryReactionPush == false) goto L68;
        long r6 = r2.getDialogId();
    L72:
        SparseArray<MessageObject> r122 = r0.pushMessagesDict.get(r6);
        if (r122 == null) goto L75;
        MessageObject r19 = r122.get(r93);
    L76:
        SharedPreferences r262 = r1;
        if (r19 != null) goto L84;
        long r27 = r36;
        long r37 = r2.messageOwner.random_id;
        if (r37 == 0) goto L83;
        r19 = r0.fcmRandomMessagesDict.get(r37);
        if (r19 == null) goto L83;
        r0.fcmRandomMessagesDict.remove(r2.messageOwner.random_id);
    L83:
        MessageObject r110 = r19;
        if (r110 != null) goto L87;
        r1 = r262;
        long r103 = r6;
        boolean r72 = r53;
        if (r15 == true) goto L107;
        if (r42 == true) goto L111;
    L113:
        r26 = r1;
        long r322 = MessageObject.getTopicId(r0.currentAccount, r2.messageOwner, r0.getMessagesController().isForum(r2));
        if (r102 == r0.openedDialogId) goto L116;
    L126:
        TLRPC.Message r111 = r2.messageOwner;
        if (r111.mentioned == false) goto L135;
        if (r22 == false) goto L130;
    L134:
        String r113 = ")";
        long r45 = r102;
        long r25 = r2.getFromChatId();
    L137:
        if (r0.isPersonalMessage(r2) == false) goto L139;
        r0.personalCount++;
    L139:
        DialogObject.isChatDialog(r25);
        int r132 = r8.indexOfKey(r25);
        if (r132 >= 0) goto L142;
    L144:
        String r133 = r113;
        long r352 = r45;
        int r30 = r14;
        boolean r182 = r15;
        int r142 = r0.getNotifyOverride(r26, r25, r322);
        r26 = r26;
        long r114 = r25;
        r322 = r322;
        if (r142 != (-1)) goto L151;
        boolean r38 = isGlobalNotificationsEnabled(r114, Boolean.valueOf(r72), r2.isReactionPush, r2.isStoryReactionPush);
        if (BuildVars.LOGS_ENABLED == false) goto L149;
        FileLog.d("NotificationsController: process new messages, isGlobalNotificationsEnabled(" + r114 + ", " + r72 + ", " + r2.isReactionPush + ", " + r2.isStoryReactionPush + ") = " + r38);
    L149:
        boolean r02 = r38;
    L154:
        r8.put(r114, Boolean.valueOf(r02));
    L156:
        if (BuildVars.LOGS_ENABLED == false) goto L158;
        FileLog.d("NotificationsController: process new messages, value is " + r02 + " (" + r114 + ", " + r72 + ", " + r2.isReactionPush + ", " + r2.isStoryReactionPush + r133);
    L158:
        if (r02 == false) goto L182;
        r0 = this;
        long r39 = r114;
        MessageObject r28 = r2;
        if (r42 == true) goto L162;
        r14 = r0.addToPopupMessages(r41, r28, r39, r72, r26);
    L163:
        if (r16 == true) goto L165;
        r16 = r28.messageOwner.from_scheduled;
    L165:
        r0.delayedPushMessages.add(r28);
        r0.appendMessage(r28);
        if (r93 == 0) goto L173;
        if (r122 != null) goto L169;
        SparseArray<MessageObject> r123 = new SparseArray();
        r0.pushMessagesDict.put(r103, r123);
    L170:
        r123.put(r93, r28);
    L171:
        long r54 = r352;
        if (r54 == r39) goto L184;
        Integer r115 = r0.pushDialogsOverrideMention.get(r54);
        LongSparseArray<Integer> r73 = r0.pushDialogsOverrideMention;
        if (r115 != null) goto L180;
        ?? r116 = r242;
    L181:
        r73.put(r54, Integer.valueOf(r116));
        goto L184
    L180:
        r116 = r115.intValue() + 1;
    L184:
        if (r28.isReactionPush == false) goto L186;
        SparseBooleanArray r117 = new SparseBooleanArray();
        r117.put(r93, r242);
        r0.getMessagesController().checkUnreadReactions(r39, r322, r117);
    L186:
        r15 = r182;
        r13 = true;
        goto L187
    L169:
        r123 = r122;
        goto L170
    L173:
        if (r27 == 0) goto L171;
        r0.fcmRandomMessagesDict.put(r27, r28);
        goto L171
    L162:
        r14 = r30;
        goto L163
    L182:
        r0 = this;
        r39 = r114;
        r28 = r2;
        r14 = r30;
        goto L184
    L151:
        if (r142 == 2) goto L153;
        r02 = r242;
        goto L154
    L153:
        r02 = false;
        goto L154
    L142:
        if (r322 != 0) goto L144;
        r352 = r45;
        r02 = ((Boolean) r8.valueAt(r132)).booleanValue();
        r30 = r14;
        r182 = r15;
        r133 = r113;
        r114 = r25;
        goto L156
    L130:
        if ((r111.action instanceof TLRPC.TL_messageActionPinMessage) == false) goto L134;
        if (BuildVars.LOGS_ENABLED == false) goto L187;
        FileLog.d("skipped message because message is mention of pinned");
        goto L187
    L135:
        r113 = ")";
        r25 = r102;
        r45 = r25;
        goto L137
    L116:
        if (ApplicationLoader.isScreenOn == false) goto L126;
        if (r2.isStoryReactionPush == true) goto L126;
        if (r2.isOauthPush == true) goto L126;
        if (r42 == true) goto L124;
        r0.playInChatSound();
    L124:
        if (BuildVars.LOGS_ENABLED == false) goto L187;
        FileLog.d("skipped message because chat is already opened (openedDialogId = " + r0.openedDialogId + ")");
        goto L187
    L111:
        if (r2.isOauthPush == true) goto L113;
        r0.getMessagesStorage().putPushMessage(r2);
        goto L113
    L107:
        if (BuildVars.LOGS_ENABLED == false) goto L103;
        FileLog.d("skipped message because edited");
        goto L103
    L87:
        if (r110.isFcmMessage() == false) goto L99;
        if (r122 != null) goto L90;
        r122 = new SparseArray();
        r0.pushMessagesDict.put(r6, r122);
    L90:
        r122.put(r93, r2);
        int r118 = r0.pushMessages.indexOf(r110);
        if (r118 < 0) goto L94;
        r0.pushMessages.set(r118, r2);
        long r104 = r6;
        r14 = r0.addToPopupMessages(r41, r2, r102, r53, r262);
        r1 = r262;
    L95:
        if (r42 == false) goto L101;
        r15 = r2.localEdit;
        if (r15 == false) goto L101;
        r0.getMessagesStorage().putPushMessage(r2);
    L101:
        if (BuildVars.LOGS_ENABLED == false) goto L103;
        FileLog.d("skipped message because old message with same dialog and message ids exist: did=" + r104 + ", mid=" + r93);
        goto L103
    L94:
        r104 = r6;
        r1 = r262;
        goto L95
    L99:
        r104 = r6;
        r1 = r262;
        goto L101
    L84:
        r27 = r36;
        goto L83
    L75:
        r19 = null;
        goto L76
    L68:
        long r62 = r2.messageOwner.peer_id.channel_id;
        if (r62 == 0) goto L71;
        r6 = -r62;
        goto L72
    L71:
        r6 = 0;
        goto L72
    L58:
        if (DialogObject.isChatDialog(r102) == false) goto L64;
        TLRPC.Chat r55 = r0.getMessagesController().getChat(Long.valueOf(-r102));
        if (ChatObject.isChannel(r55) == false) goto L64;
        if (r55.megagroup == true) goto L64;
        r53 = r242;
    L64:
        r53 = false;
        goto L66
    L53:
        r36 = 0;
    L103:
        r26 = r1;
        goto L187
    L48:
        r242 = r9;
        r22 = r10;
        r23 = r12;
        goto L49
    L188:
        SharedPreferences r263 = r1;
        int r302 = r14;
        boolean r183 = r15;
        if (r13 == false) goto L192;
        r0.notifyCheck = r43;
    L192:
        if (r41.isEmpty() == false) goto L194;
    L198:
        if (r42 == true) goto L200;
        if (r16 == true) goto L200;
    L267:
        if (r17 == false) goto L269;
        r0.updateStoryPushesRunnable();
    L269:
        if (r44 == null) goto L280;
        r44.countDown();
        return;
    L280:
        return;
    L200:
        if (r183 == true) goto L202;
        if (r13 == false) goto L267;
        if (BuildVars.LOGS_ENABLED == false) goto L209;
        FileLog.d("NotificationsController processNewMessages: added branch");
    L209:
        MessageObject r63 = (MessageObject) r40.get(0);
        long r119 = r63.getDialogId();
        long r46 = MessageObject.getTopicId(r0.currentAccount, r63.messageOwner, r0.getMessagesController().isForum(r119));
        if (r63.isFcmMessage() == false) goto L212;
        Boolean r192 = Boolean.valueOf(r63.localChannel);
    L213:
        int r74 = r0.total_unread_count;
        int r120 = r0.getNotifyOverride(r263, r119, r46);
        if (r120 != (-1)) goto L216;
        r0 = this;
        long r47 = r119;
        boolean r243 = r0.isGlobalNotificationsEnabled(r119, r192, r63.isReactionPush, r63.isStoryReactionPush);
    L220:
        Integer r121 = r0.pushDialogs.get(r47);
        if (r121 == null) goto L223;
        boolean r1110 = true;
        int r29 = r121.intValue() + 1;
    L225:
        if (r0.notifyCheck == false) goto L232;
        if (r243 == true) goto L232;
        Integer r310 = r0.pushDialogsOverrideMention.get(r47);
        if (r310 == null) goto L232;
        if (r310.intValue() == 0) goto L232;
        r29 = r310.intValue();
        r243 = r1110;
    L232:
        if (r243 == false) goto L256;
        if (r63.isStoryPush == true) goto L256;
        if (r0.getMessagesController().isCommunity(r47) == false) goto L239;
    L254:
        r0.pushDialogs.put(r47, Integer.valueOf(r29));
        goto L256
    L239:
        if (r0.getMessagesController().isForum(r47) == false) goto L251;
        int r311 = r0.total_unread_count;
        if (r121 != null) goto L243;
    L245:
        ?? r244 = 0;
    L246:
        int r312 = r311 - r244;
        r0.total_unread_count = r312;
        if (r29 <= 0) goto L249;
        ?? r94 = r1110;
    L250:
        r0.total_unread_count = r312 + r94;
        goto L254
    L249:
        r94 = 0;
        goto L250
    L243:
        if (r121.intValue() <= 0) goto L245;
        r244 = r1110;
        goto L246
    L251:
        if (r121 == null) goto L253;
        r0.total_unread_count -= r121.intValue();
    L253:
        r0.total_unread_count += r29;
    L256:
        if (r74 != r0.total_unread_count) goto L260;
        if (r17 == true) goto L260;
    L264:
        r0.notifyCheck = false;
        if (r0.showBadgeNumber == false) goto L267;
        r0.setBadge(r0.getTotalAllUnreadCount());
    L260:
        r0.delayedPushMessages.clear();
        if (BuildVars.LOGS_ENABLED == false) goto L263;
        FileLog.d("NotificationsController processNewMessages: added branch: " + r0.notifyCheck);
    L263:
        r0.showOrUpdateNotification(r0.notifyCheck);
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda29(r0, r0.pushDialogs.size()));
        goto L264
    L223:
        r1110 = true;
        r29 = 1;
        goto L225
    L216:
        r0 = this;
        r47 = r119;
        if (r120 == 2) goto L219;
        r243 = true;
        goto L220
    L219:
        r243 = false;
        goto L220
    L212:
        r192 = null;
        goto L213
    L202:
        if (BuildVars.LOGS_ENABLED == false) goto L204;
        FileLog.d("NotificationsController processNewMessages: edited branch, showOrUpdateNotification " + r0.notifyCheck);
    L204:
        r0.delayedPushMessages.clear();
        r0.showOrUpdateNotification(r0.notifyCheck);
        goto L267
    L194:
        if (AndroidUtilities.needShowPasscode() == true) goto L198;
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L198;
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda28(r0, r41, r302));
        goto L198
    }

    private /* synthetic */ void lambda$processReadMessages$20(ArrayList r6) {
        int r0 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.popupMessages.remove(r6.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$processReadMessages$21(LongSparseIntArray r20, ArrayList r21, long r22, int r24, int r25, boolean r26) {
        long r6 = 0;
        if (r20 == null) goto L37;
        int r8 = 0;
    L6:
        if (r8 >= r20.size()) goto L37;
        long r9 = r20.keyAt(r8);
        int r11 = r20.get(r9);
        int r12 = 0;
    L9:
        if (r12 >= this.pushMessages.size()) goto L36;
        MessageObject r13 = this.pushMessages.get(r12);
        if (r13.messageOwner.from_scheduled == false) goto L13;
    L34:
        long r17 = r6;
    L35:
        r12 = r12 + 1;
        r6 = r17;
        goto L9
    L13:
        if (r13.getDialogId() != r9) goto L34;
        if (r13.getId() > r11) goto L34;
        if (r13.isStoryReactionPush == true) goto L34;
        if (isPersonalMessage(r13) == false) goto L21;
        this.personalCount--;
    L21:
        r21.add(r13);
        if (r13.isStoryReactionPush == false) goto L24;
        long r14 = r13.getDialogId();
    L28:
        SparseArray<MessageObject> r5 = this.pushMessagesDict.get(r14);
        r17 = r6;
        if (r5 == null) goto L33;
        r5.remove(r13.getId());
        if (r5.size() != 0) goto L33;
        this.pushMessagesDict.remove(r14);
    L33:
        this.delayedPushMessages.remove(r13);
        this.pushMessages.remove(r12);
        r12 = r12 - 1;
        goto L35
    L24:
        long r142 = r13.messageOwner.peer_id.channel_id;
        if (r142 == r6) goto L27;
        r14 = -r142;
        goto L28
    L27:
        r14 = r6;
        goto L28
    L36:
        r8 = r8 + 1;
    L37:
        long r172 = r6;
        if (r22 == r172) goto L78;
        if (r24 != 0) goto L41;
        if (r25 == 0) goto L78;
    L41:
        int r52 = 0;
    L43:
        if (r52 >= this.pushMessages.size()) goto L78;
        MessageObject r1 = this.pushMessages.get(r52);
        if (r1.getDialogId() != r22) goto L76;
        if (r1.isStoryReactionPush == true) goto L76;
        if (r25 != 0) goto L50;
        if (r26 == true) goto L58;
        if (r1.getId() <= r24) goto L61;
        if (r24 >= 0) goto L76;
    L61:
        if (isPersonalMessage(r1) == false) goto L64;
        this.personalCount--;
    L64:
        if (r1.isStoryReactionPush == false) goto L66;
        long r62 = r1.getDialogId();
    L70:
        SparseArray<MessageObject> r82 = this.pushMessagesDict.get(r62);
        if (r82 == null) goto L75;
        r82.remove(r1.getId());
        if (r82.size() != 0) goto L75;
        this.pushMessagesDict.remove(r62);
    L75:
        this.pushMessages.remove(r52);
        this.delayedPushMessages.remove(r1);
        r21.add(r1);
        r52 = r52 - 1;
        goto L76
    L66:
        long r63 = r1.messageOwner.peer_id.channel_id;
        if (r63 == r172) goto L69;
        r62 = -r63;
        goto L70
    L69:
        r62 = r172;
        goto L70
    L58:
        if (r1.getId() == r24) goto L61;
        if (r24 >= 0) goto L76;
    L50:
        if (r1.messageOwner.date <= r25) goto L61;
    L76:
        r52 = r52 + 1;
    L78:
        if (r21.isEmpty() == true) goto L92;
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda18(this, r21));
        return;
    }

    private /* synthetic */ void lambda$processReadStories$16(long r6, int r8) {
        StoryNotification r0 = this.storyPushMessagesDict.get(r6);
        if (r0 == null) goto L5;
        this.storyPushMessagesDict.remove(r6);
        this.storyPushMessages.remove(r0);
        getMessagesStorage().deleteStoryPushMessage(r6);
        boolean r62 = true;
    L6:
        int r7 = 0;
    L8:
        if (r7 >= this.pushMessages.size()) goto L24;
        MessageObject r02 = this.pushMessages.get(r7);
        if (r02 == null) goto L23;
        if (r02.isLiveStoryPush == false) goto L23;
        if (r02.getId() > r8) goto L23;
        this.pushMessages.remove(r7);
        r7 = r7 - 1;
        SparseArray<MessageObject> r63 = this.pushMessagesDict.get(r02.getDialogId());
        if (r63 == null) goto L18;
        r63.remove(r02.getId());
    L18:
        if (r63 != null) goto L20;
    L22:
        r62 = true;
        goto L23
    L20:
        if (r63.size() > 0) goto L22;
        this.pushMessagesDict.remove(r02.getDialogId());
    L23:
        r7 = r7 + 1;
        goto L8
    L24:
        if (r62 == false) goto L32;
        showOrUpdateNotification(false);
        updateStoryPushesRunnable();
        return;
    L32:
        return;
    L5:
        r62 = false;
        goto L6
    }

    private /* synthetic */ void lambda$processSeenStoryReactions$14(int r9) {
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= this.pushMessages.size()) goto L18;
        MessageObject r3 = this.pushMessages.get(r1);
        if (r3.isStoryReactionPush == false) goto L17;
        if (Math.abs(r3.getId()) != r9) goto L17;
        this.pushMessages.remove(r1);
        SparseArray<MessageObject> r22 = this.pushMessagesDict.get(r3.getDialogId());
        if (r22 == null) goto L12;
        r22.remove(r3.getId());
    L12:
        if (r22 != null) goto L14;
    L16:
        ArrayList<Integer> r23 = new ArrayList();
        r23.add(Integer.valueOf(r3.getId()));
        getMessagesStorage().deletePushMessages(r3.getDialogId(), r23);
        r1 = r1 - 1;
        r2 = true;
        goto L17
    L14:
        if (r22.size() > 0) goto L16;
        this.pushMessagesDict.remove(r3.getDialogId());
    L17:
        r1 = r1 + 1;
        goto L4
    L18:
        if (r2 == false) goto L25;
        showOrUpdateNotification(false);
        return;
    }

    private /* synthetic */ void lambda$removeDeletedHisoryFromNotifications$11(ArrayList r6) {
        int r0 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.popupMessages.remove(r6.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$removeDeletedHisoryFromNotifications$12(int r4) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(this.currentAccount)});
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(r4)});
    }

    private /* synthetic */ void lambda$removeDeletedHisoryFromNotifications$13(LongSparseIntArray r20, ArrayList r21) {
        int r3 = this.total_unread_count;
        getAccountInstance().getNotificationsSettings();
        int r4 = 0;
        Integer r5 = 0;
        int r6 = 0;
    L4:
        if (r6 >= r20.size()) goto L52;
        long r9 = r20.keyAt(r6);
        long r11 = -r9;
        long r92 = r20.get(r9);
        Integer r7 = this.pushDialogs.get(r11);
        if (r7 != null) goto L8;
        r7 = r5;
    L8:
        int r13 = r4;
        Integer r14 = r7;
    L10:
        if (r13 >= this.pushMessages.size()) goto L26;
        MessageObject r15 = this.pushMessages.get(r13);
        if (r15.getDialogId() != r11) goto L24;
        Integer r18 = r5;
        if (r15.getId() > r92) goto L25;
        SparseArray<MessageObject> r42 = this.pushMessagesDict.get(r11);
        if (r42 == null) goto L20;
        r42.remove(r15.getId());
        if (r42.size() != 0) goto L20;
        this.pushMessagesDict.remove(r11);
    L20:
        this.delayedPushMessages.remove(r15);
        this.pushMessages.remove(r15);
        r13 = r13 - 1;
        if (isPersonalMessage(r15) == false) goto L23;
        this.personalCount--;
    L23:
        r21.add(r15);
        r14 = Integer.valueOf(r14.intValue() - 1);
    L25:
        r13 = r13 + 1;
        r5 = r18;
        goto L10
    L24:
        r18 = r5;
        goto L25
    L26:
        Integer r182 = r5;
        if (r14.intValue() > 0) goto L30;
        this.smartNotificationsDialogs.remove(r11);
        r14 = r182;
    L30:
        if (r14.equals(r7) == true) goto L48;
        if (getMessagesController().isCommunity(r11) == true) goto L46;
        boolean r43 = getMessagesController().isForum(r11);
        int r52 = this.total_unread_count;
        if (r43 == true) goto L37;
        int r53 = r52 - r7.intValue();
        this.total_unread_count = r53;
        this.total_unread_count = r53 + r14.intValue();
        goto L46
    L37:
        if (r7.intValue() <= 0) goto L39;
        int r44 = 1;
    L40:
        int r54 = r52 - r44;
        this.total_unread_count = r54;
        if (r14.intValue() <= 0) goto L43;
        int r8 = 1;
    L44:
        this.total_unread_count = r54 + r8;
        goto L46
    L43:
        r8 = 0;
        goto L44
    L39:
        r44 = 0;
    L46:
        this.pushDialogs.put(r11, r14);
    L48:
        if (r14.intValue() != 0) goto L50;
        this.pushDialogs.remove(r11);
        this.pushDialogsOverrideMention.remove(r11);
    L50:
        r6 = r6 + 1;
        r5 = r182;
        r4 = 0;
        goto L4
    L52:
        if (r21.isEmpty() == false) goto L55;
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda14(this, r21));
    L55:
        if (r3 != this.total_unread_count) goto L57;
    L65:
        this.notifyCheck = false;
        if (this.showBadgeNumber == false) goto L76;
        setBadge(getTotalAllUnreadCount());
        return;
    L76:
        return;
    L57:
        if (this.notifyCheck == true) goto L60;
        this.delayedPushMessages.clear();
        showOrUpdateNotification(this.notifyCheck);
    L64:
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda15(this, this.pushDialogs.size()));
        goto L65
    L60:
        if (this.lastOnlineFromOtherDevice <= getConnectionsManager().getCurrentTime()) goto L62;
        boolean r82 = true;
    L63:
        scheduleNotificationDelay(r82);
        goto L64
    L62:
        r82 = false;
        goto L63
    }

    private /* synthetic */ void lambda$removeDeletedMessagesFromNotifications$10(LongSparseArray r21, boolean r22, ArrayList r23) {
        LongSparseArray r1 = r21;
        int r3 = this.total_unread_count;
        getAccountInstance().getNotificationsSettings();
        int r4 = 0;
        Integer r5 = 0;
        int r6 = 0;
    L4:
        if (r6 >= r1.size()) goto L59;
        long r9 = r1.keyAt(r6);
        SparseArray<MessageObject> r7 = this.pushMessagesDict.get(r9);
        if (r7 != null) goto L8;
        Integer r17 = r5;
        int r18 = r6;
    L57:
        r6 = r18 + 1;
        r1 = r21;
        r5 = r17;
        r4 = 0;
        goto L4
    L8:
        ArrayList r11 = (ArrayList) r1.get(r9);
        int r12 = r11.size();
        int r13 = r4;
    L9:
        if (r13 >= r12) goto L54;
        int r14 = ((Integer) r11.get(r13)).intValue();
        MessageObject r15 = r7.get(r14);
        if (r15 != null) goto L13;
        Integer r172 = r5;
        int r182 = r6;
    L53:
        r13 = r13 + 1;
        r5 = r172;
        r6 = r182;
        goto L9
    L13:
        if (r15.isStoryReactionPush == false) goto L15;
    L14:
        r172 = r5;
        r182 = r6;
        goto L53
    L15:
        if (r22 == true) goto L17;
    L19:
        r172 = r5;
        long r42 = r15.getDialogId();
        Integer r8 = this.pushDialogs.get(r42);
        if (r8 != null) goto L22;
        r8 = r172;
    L22:
        int r183 = r8.intValue() - 1;
        Integer r19 = Integer.valueOf(r183);
        if (r183 > 0) goto L25;
        this.smartNotificationsDialogs.remove(r42);
        Integer r16 = r172;
    L27:
        if (r16.equals(r8) == true) goto L44;
        r182 = r6;
        if (getMessagesController().isCommunity(r42) == true) goto L43;
        boolean r62 = getMessagesController().isForum(r42);
        int r63 = this.total_unread_count;
        if (r62 == true) goto L34;
        int r64 = r63 - r8.intValue();
        this.total_unread_count = r64;
        this.total_unread_count = r64 + r16.intValue();
        goto L43
    L34:
        if (r8.intValue() <= 0) goto L36;
        int r82 = 1;
    L37:
        int r65 = r63 - r82;
        this.total_unread_count = r65;
        if (r16.intValue() <= 0) goto L40;
        int r83 = 1;
    L41:
        this.total_unread_count = r65 + r83;
        goto L43
    L40:
        r83 = 0;
        goto L41
    L36:
        r82 = 0;
    L43:
        this.pushDialogs.put(r42, r16);
    L46:
        if (r16.intValue() != 0) goto L48;
        this.pushDialogs.remove(r42);
        this.pushDialogsOverrideMention.remove(r42);
    L48:
        r7.remove(r14);
        this.delayedPushMessages.remove(r15);
        this.pushMessages.remove(r15);
        if (isPersonalMessage(r15) == false) goto L51;
        this.personalCount--;
    L51:
        r23.add(r15);
        goto L53
    L44:
        r182 = r6;
        goto L46
    L25:
        r16 = r19;
        goto L27
    L17:
        if (r15.isReactionPush == true) goto L19;
    L54:
        r17 = r5;
        r18 = r6;
        if (r7.size() != 0) goto L57;
        this.pushMessagesDict.remove(r9);
        goto L57
    L59:
        if (r23.isEmpty() == true) goto L62;
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda6(this, r23));
    L62:
        if (r3 != this.total_unread_count) goto L64;
    L72:
        this.notifyCheck = false;
        if (this.showBadgeNumber == false) goto L84;
        setBadge(getTotalAllUnreadCount());
        return;
    L84:
        return;
    L64:
        if (this.notifyCheck == true) goto L67;
        this.delayedPushMessages.clear();
        showOrUpdateNotification(this.notifyCheck);
    L71:
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda7(this, this.pushDialogs.size()));
        goto L72
    L67:
        if (this.lastOnlineFromOtherDevice <= getConnectionsManager().getCurrentTime()) goto L69;
        boolean r84 = true;
    L70:
        scheduleNotificationDelay(r84);
        goto L71
    L69:
        r84 = false;
        goto L70
    }

    private /* synthetic */ void lambda$removeDeletedMessagesFromNotifications$8(ArrayList r6) {
        int r0 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.popupMessages.remove(r6.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$removeDeletedMessagesFromNotifications$9(int r4) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(this.currentAccount)});
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(r4)});
    }

    private /* synthetic */ void lambda$repeatNotificationMaybe$41() {
        int r0 = Calendar.getInstance().get(11);
        if (r0 >= 11) goto L5;
    L8:
        scheduleNotificationRepeat();
        return;
    L5:
        if (r0 > 22) goto L8;
        notificationManager.cancel(this.notificationId);
        showOrUpdateNotification(true);
    }

    private /* synthetic */ void lambda$setLastOnlineFromOtherDevice$5(int r3) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("set last online from other device = " + r3);
    L5:
        this.lastOnlineFromOtherDevice = r3;
    }

    private /* synthetic */ void lambda$setOpenedDialogId$3(long r1, long r3) {
        this.openedDialogId = r1;
        this.openedTopicId = r3;
    }

    private /* synthetic */ void lambda$setOpenedInBubble$4(boolean r1, long r2) {
        HashSet<Long> r0 = this.openedInBubbleDialogs;
        if (r1 == false) goto L6;
        r0.add(Long.valueOf(r2));
        return;
    L6:
        r0.remove(Long.valueOf(r2));
    }

    private /* synthetic */ void lambda$showNotifications$35() {
        showOrUpdateNotification(false);
    }

    private /* synthetic */ void lambda$updateBadge$34() {
        setBadge(getTotalAllUnreadCount());
    }

    public static Bitmap loadMultipleAvatars(ArrayList<Object> r34) {
        ArrayList<Object> r0 = r34;
        if (Build.VERSION.SDK_INT < 28) goto L7;
        if (r0 != null) goto L6;
        return null;
    L6:
        if (r0.size() == 0) goto L126;
        int r1 = AndroidUtilities.dp(64.0f);
        Bitmap r2 = Bitmap.createBitmap(r1, r1, Bitmap.Config.ARGB_8888);
        Canvas r4 = new Canvas(r2);
        Matrix r5 = new Matrix();
        Paint r6 = new Paint(3);
        boolean r8 = true;
        Paint r7 = new Paint(1);
        Rect r9 = new Rect();
        r7.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        char r11 = 2;
        if (r0.size() != 1) goto L12;
        float r10 = 1.0f;
    L15:
        int r14 = 0;
        TextPaint r15 = null;
    L17:
        if (r14 >= r0.size()) goto L83;
        float r12 = r1;
        float r17 = (1.0f - r10) * r12;
        float r3 = (r17 / r0.size()) * ((r0.size() - 1) - r14);
        float r112 = r14 * (r17 / r0.size());
        float r13 = r12 * r10;
        float r82 = r13 / 2.0f;
        int r29 = r1;
        float r16 = r3 + r82;
        Bitmap r30 = r2;
        float r22 = r112 + r82;
        float r31 = r10;
        r4.drawCircle(r16, r22, AndroidUtilities.dp(2.0f) + r82, r7);     // Catch: Throwable -> L78
        Object r102 = r0.get(r14);     // Catch: Throwable -> L78
        Paint r32 = r7;
        if ((r102 instanceof File) == false) goto L45;
        String r72 = ((File) r0.get(r14)).getAbsolutePath();     // Catch: Throwable -> L89
        BitmapFactory.Options r103 = new BitmapFactory.Options();     // Catch: Throwable -> L89
        boolean r122 = true;
        r103.inJustDecodeBounds = true;     // Catch: Throwable -> L86
        BitmapFactory.decodeFile(r72, r103);     // Catch: Throwable -> L86
        int r123 = (int) r13;
        r103.inSampleSize = StoryEntry.calculateInSampleSize(r103, r123, r123);     // Catch: Throwable -> L89
        r103.inJustDecodeBounds = false;     // Catch: Throwable -> L42
        r103.inDither = true;     // Catch: Throwable -> L86
        Bitmap r73 = BitmapFactory.decodeFile(r72, r103);     // Catch: Throwable -> L89
        Shader.TileMode r124 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L89
        BitmapShader r104 = new BitmapShader(r73, r124, r124);     // Catch: Throwable -> L89
        r5.reset();     // Catch: Throwable -> L89
        r5.postScale(r13 / r73.getWidth(), r13 / r73.getHeight());     // Catch: Throwable -> L89
        r5.postTranslate(r3, r112);     // Catch: Throwable -> L89
        r104.setLocalMatrix(r5);     // Catch: Throwable -> L89
        r6.setShader(r104);     // Catch: Throwable -> L89
        r4.drawCircle(r16, r22, r82, r6);     // Catch: Throwable -> L89
        r73.recycle();     // Catch: Throwable -> L89
    L43:
        char r02 = 2;
    L39:
        r122 = true;
    L81:
        r14 = r14 + 1;
        r11 = r02;
        r8 = r122;
        r1 = r29;
        r2 = r30;
        r10 = r31;
        r7 = r32;
        r0 = r34;
    L40:
        r02 = 2;
        goto L81
    L45:
        if ((r102 instanceof TLRPC.User) == false) goto L37;
        TLRPC.User r105 = (TLRPC.User) r102;     // Catch: Throwable -> L89
        r02 = 2;
        int[] r74 = new int[2];     // Catch: Throwable -> L88
        r74[0] = Theme.getColor(Theme.keys_avatar_background[AvatarDrawable.getColorIndex(r105.id)]);     // Catch: Throwable -> L77
        r74[1] = Theme.getColor(Theme.keys_avatar_background2[AvatarDrawable.getColorIndex(r105.id)]);     // Catch: Throwable -> L76
        float r24 = r112 + r13;
        r02 = 2;
        float[] r113 = new float[2];     // Catch: Throwable -> L74
        r113[0] = 0.0f;     // Catch: Throwable -> L75
        r113[1] = 1.0f;     // Catch: Throwable -> L74
        r6.setShader(new LinearGradient(r3, r112, r3, r24, r74, r113, Shader.TileMode.CLAMP));     // Catch: Throwable -> L88
        r4.drawCircle(r16, r22, r82, r6);     // Catch: Throwable -> L88
        if (r15 == null) goto L102;
        r122 = true;
    L70:
        StringBuilder r33 = new StringBuilder();     // Catch: Throwable -> L87
        AvatarDrawable.getAvatarSymbols(r105.first_name, r105.last_name, null, r33);     // Catch: Throwable -> L87
        String r35 = r33.toString();     // Catch: Throwable -> L87
        r15.getTextBounds(r35, 0, r35.length(), r9);     // Catch: Throwable -> L85
        r4.drawText(r35, (r16 - (r9.width() / 2.0f)) - r9.left, (r22 - (r9.height() / 2.0f)) - r9.top, r15);     // Catch: Throwable -> L85
        goto L81
    L102:
        r122 = true;
        TextPaint r36 = new TextPaint(1);     // Catch: Throwable -> L87
        r36.setTypeface(AndroidUtilities.bold());     // Catch: Throwable -> L67
        r36.setTextSize(0.25f * r12);     // Catch: Throwable -> L67
        r36.setColor(-1);     // Catch: Throwable -> L67
        r15 = r36;
    L67:
        r15 = r36;
    L68:
        r122 = true;
    L75:
        r122 = true;
    L74:
        r122 = true;
    L76:
        r122 = true;
    L37:
        r02 = 2;
    L78:
        r32 = r7;
    L79:
        r29 = r1;
        r30 = r2;
        r32 = r7;
        r122 = r8;
        r31 = r10;
    L80:
        r29 = r1;
        r30 = r2;
        r32 = r7;
        r122 = r8;
        r31 = r10;
        r02 = r11;
        goto L81
    L83:
        return r2;
    L12:
        if (r0.size() != 2) goto L14;
        r10 = 0.65f;
        goto L15
    L14:
        r10 = 0.5f;
        goto L15
    L126:
        return null;
    L7:
        return null;
    }

    public static Person.Builder loadRoundAvatar(long r2, File r4, Person.Builder r5) {
        if (r2 != UserObject.OAUTH) goto L6;
        r5.setIcon(IconCompat.createWithResource(ApplicationLoader.applicationContext, R.drawable.ic_launcher_dr));
        return r5;
    L6:
        if (r4 != null) goto L8;
    L10:
        return r5;
    L8:
        if (Build.VERSION.SDK_INT < 28) goto L10;
        r5.setIcon(IconCompat.createWithBitmap(ImageDecoder.decodeBitmap(ImageDecoder.createSource(r4), new NotificationsController$$ExternalSyntheticLambda20())));     // Catch: Throwable -> L11
        goto L10
    }

    private Pair<Integer, Boolean> parseStoryPushes(ArrayList<String> r17, ArrayList<Object> r18) {
        int r2 = Math.min(3, this.storyPushMessages.size());
        int r4 = 0;
        boolean r5 = false;
        int r6 = 0;
    L3:
        if (r4 >= r2) goto L44;
        StoryNotification r7 = this.storyPushMessages.get(r4);
        r6 = r6 + r7.dateByIds.size();
        r5 = r5 | r7.hidden;
        TLRPC.User r8 = getMessagesController().getUser(Long.valueOf(r7.dialogId));
        if (r8 != null) goto L9;
        r8 = getMessagesStorage().getUserSync(r7.dialogId);
        if (r8 == null) goto L9;
        getMessagesController().putUser(r8, true);
    L9:
        Object r10 = null;
        if (r8 == null) goto L31;
        String r72 = UserObject.getUserName(r8);
        TLRPC.UserProfilePhoto r11 = r8.photo;
        if (r11 == null) goto L34;
        TLRPC.FileLocation r112 = r11.photo_small;
        if (r112 == null) goto L34;
        if (r112.volume_id == 0) goto L34;
        if (r112.local_id == 0) goto L34;
        File r113 = getFileLoader().getPathToAttach(r8.photo.photo_small, true);
        if (r113.exists() == false) goto L22;
    L29:
        if (r113 == null) goto L34;
        r10 = r113;
        goto L34
    L22:
        if (r8.photo.photo_big == null) goto L24;
        r113 = getFileLoader().getPathToAttach(r8.photo.photo_big, true);
    L25:
        if (r113 == null) goto L29;
        if (r113.exists() == true) goto L29;
        r113 = null;
        goto L29
    L24:
        r113 = null;
    L34:
        if (r72.length() <= 50) goto L36;
        r72 = r72.substring(0, 25).concat("…");
    L36:
        r17.add(r72);
        if (r10 != null) goto L40;
        if (r8 == null) goto L40;
        r18.add(r8);
    L43:
        r4 = r4 + 1;
    L40:
        if (r10 == null) goto L43;
        r18.add(r10);
        goto L43
    L31:
        r72 = r7.localName;
        if (r72 == null) goto L43;
    L44:
        if (r5 == false) goto L47;
        r18.clear();
    L47:
        return new Pair(Integer.valueOf(r6), Boolean.valueOf(r5));
    }

    private void playInChatSound() {
        if (this.inChatSoundEnabled == true) goto L5;
        return;
    L5:
        if (MediaController.getInstance().isRecordingAudio() == true) goto L26;
        if (audioManager.getRingerMode() == 0) goto L27;
    L20:
    L17:
        e = move-exception;
        FileLog.e(e);
        return;
    L13:
        if (getNotifyOverride(getAccountInstance().getNotificationsSettings(), this.openedDialogId, this.openedTopicId) == 2) goto L25;
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda8(this));     // Catch: Exception -> L17
        return;
    L25:
        return;
    L27:
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L20
    }

    private String replaceSpoilers(MessageObject r9) {
        if (r9 == null) goto L28;
        TLRPC.Message r1 = r9.messageOwner;
        if (r1 == null) goto L28;
        String r2 = r1.message;
        if (r2 == null) goto L28;
        if (r1.entities == null) goto L28;
        StringBuilder r0 = new StringBuilder(r2);
        if (r9.didSpoilLoginCode() == true) goto L15;
        int r22 = 0;
    L18:
        if (r22 >= r9.messageOwner.entities.size()) goto L27;
        if ((r9.messageOwner.entities.get(r22) instanceof TLRPC.TL_messageEntitySpoiler) == false) goto L25;
        TLRPC.TL_messageEntitySpoiler r3 = (TLRPC.TL_messageEntitySpoiler) r9.messageOwner.entities.get(r22);
        int r4 = 0;
    L23:
        if (r4 >= r3.length) goto L25;
        int r5 = r3.offset + r4;
        char[] r6 = this.spoilerChars;
        r0.setCharAt(r5, r6[r4 % r6.length]);
        r4 = r4 + 1;
    L25:
        r22 = r22 + 1;
        goto L18
    L27:
        return r0.toString();
    L15:
        return r0.toString();
    L28:
        return null;
    }

    private void resetNotificationSound(NotificationCompat.Builder r21, long r22, long r24, String r26, long[] r27, int r28, Uri r29, int r30, boolean r31, boolean r32, boolean r33, int r34) {
        FileLog.d("resetNotificationSound");
        Uri r8 = Settings.System.DEFAULT_RINGTONE_URI;
        if (r8 == null) goto L36;
        if (r29 != null) goto L6;
        return;
    L6:
        if (TextUtils.equals(r8.toString(), r29.toString()) == true) goto L38;
        SharedPreferences.Editor r0 = getAccountInstance().getNotificationsSettings().edit();
        String r1 = r8.toString();
        String r2 = LocaleController.getString(R.string.DefaultRingtone);
        if (r31 == true) goto L10;
        r0.putString("sound_" + getSharedPrefKey(r22, r24), r2);
        r0.putString("sound_path_" + getSharedPrefKey(r22, r24), r1);
        deleteNotificationChannelInternal(r22, r24, -1);
    L34:
        r0.apply();
        r21.setChannelId(validateChannelId(r22, r24, r26, r27, r28, r8, r30, r31, r32, r33, r34));
        notificationManager.notify(this.notificationId, r21.build());
        return;
    L10:
        if (r34 != 2) goto L12;
        r0.putString("ChannelSound", r2);
    L21:
        if (r34 != 2) goto L23;
        r0.putString("ChannelSoundPath", r1);
    L32:
        getNotificationsController().deleteNotificationChannelGlobalInternal(r34, -1);
        goto L34
    L23:
        if (r34 != 0) goto L25;
        r0.putString("GroupSoundPath", r1);
        goto L32
    L25:
        if (r34 != 1) goto L27;
        r0.putString("GlobalSoundPath", r1);
        goto L32
    L27:
        if (r34 != 3) goto L29;
        r0.putString("StoriesSoundPath", r1);
        goto L32
    L29:
        if (r34 == 4) goto L31;
        if (r34 != 5) goto L32;
    L31:
        r0.putString("ReactionSound", r1);
        goto L32
    L12:
        if (r34 != 0) goto L14;
        r0.putString("GroupSound", r2);
        goto L21
    L14:
        if (r34 != 1) goto L16;
        r0.putString("GlobalSound", r2);
        goto L21
    L16:
        if (r34 != 3) goto L18;
        r0.putString("StoriesSound", r2);
        goto L21
    L18:
        if (r34 == 4) goto L20;
        if (r34 != 5) goto L21;
    L20:
        r0.putString("ReactionSound", r2);
        goto L21
    L38:
        return;
    }

    private void scheduleNotificationDelay(boolean r5) {
        if (ExteraConfig.getDisableNotificationDelay() == true) goto L5;
    L11:
        e = move-exception;
        FileLog.e(e);
        showOrUpdateNotification(this.notifyCheck);
        return;
    L9:
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.d("delay notification start, onlineReason = " + r5);     // Catch: Exception -> L11
    L13:
        this.notificationDelayWakelock.acquire(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: Exception -> L11
        DispatchQueue r0 = notificationsQueue;     // Catch: Exception -> L11
        r0.cancelRunnable(this.notificationDelayRunnable);     // Catch: Exception -> L11
        Runnable r1 = this.notificationDelayRunnable;     // Catch: Exception -> L11
        if (r5 == false) goto L16;
        int r52 = 3000;
    L17:
        r0.postRunnable(r1, r52);     // Catch: Exception -> L11
        return;
    L16:
        r52 = 1000;
        goto L17
    L5:
        if (this.delayedPushMessages.isEmpty() == true) goto L23;
        showOrUpdateNotification(true);
        this.delayedPushMessages.clear();
        return;
    }

    private void scheduleNotificationRepeat() {
        Intent r0 = new Intent(ApplicationLoader.applicationContext, NotificationRepeat.class);     // Catch: Exception -> L10
        r0.putExtra("currentAccount", this.currentAccount);     // Catch: Exception -> L10
        PendingIntent r02 = PendingIntent.getService(ApplicationLoader.applicationContext, 0, r0, 33554432);     // Catch: Exception -> L10
        if (getAccountInstance().getNotificationsSettings().getInt("repeat_messages", 60) > 0) goto L5;
    L8:
        this.alarmManager.cancel(r02);     // Catch: Exception -> L10
        return;
    L5:
        if (this.personalCount <= 0) goto L8;
        this.alarmManager.set(2, SystemClock.elapsedRealtime() + (r1 * 60000), r02);     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
    }

    private void setBadge(int r3) {
        if (this.lastBadgeCount != r3) goto L5;
        return;
    L5:
        FileLog.d("setBadge " + r3);
        this.lastBadgeCount = r3;
        NotificationBadge.applyCount(r3);
    }

    @SuppressLint({"NewApi"})
    private void setNotificationChannel(Notification r1, NotificationCompat.Builder r2, boolean r3) {
        if (r3 == false) goto L5;
        r2.setChannelId(OTHER_NOTIFICATIONS_CHANNEL);
        return;
    L5:
        r2.setChannelId(r1.getChannelId());
    }

    @SuppressLint({"InlinedApi"})
    private void showExtraNotifications(NotificationCompat.Builder r84, String r85, long r86, long r88, String r90, long[] r91, int r92, Uri r93, int r94, boolean r95, boolean r96, boolean r97, int r98) {
        NotificationsController r1 = this;
        FileLog.d("showExtraNotifications pushMessages.size()=" + r1.pushMessages.size());
        if (Build.VERSION.SDK_INT < 26) goto L5;
        r84.setChannelId(r1.validateChannelId(r86, r88, r90, r91, r92, r93, r94, r95, r96, r97, r98));
    L5:
        Notification r2 = r84.build();
        SharedPreferences r3 = r1.getAccountInstance().getNotificationsSettings();
        ArrayList r4 = new ArrayList();
        if (r1.storyPushMessages.isEmpty() == true) goto L8;
        r4.add(new DialogKey(0, 0, true, null));
    L8:
        LongSparseArray r5 = new LongSparseArray();
        int r6 = 0;
        int r0 = 0;
    L10:
        if (r0 >= r1.pushMessages.size()) goto L21;
        MessageObject r7 = r1.pushMessages.get(r0);
        long r8 = r7.getDialogId();
        long r19 = MessageObject.getTopicId(r1.currentAccount, r7.messageOwner, r1.getMessagesController().isForum(r7));
        int r10 = r3.getInt("dismissDate" + r8, 0);
        if (r7.isStoryPush == false) goto L14;
    L16:
        ArrayList r102 = (ArrayList) r5.get(r8);
        if (r102 != null) goto L19;
        r102 = new ArrayList();
        r5.put(r8, r102);
        FileLog.d("showExtraNotifications: sortedDialogs += " + r8);
        r4.add(new DialogKey(r8, r19, false, null));
    L19:
        r102.add(r7);
    L20:
        r0 = r0 + 1;
        goto L10
    L14:
        if (r7.messageOwner.date > r10) goto L16;
        FileLog.d("showExtraNotifications: dialog " + r8 + " is skipped, message date (" + r7.messageOwner.date + " <= " + r10 + ")");
        goto L20
    L21:
        LongSparseArray r72 = new LongSparseArray();
        int r02 = 0;
    L22:
        int r82 = r1.wearNotificationsIds.size();
        LongSparseArray<Integer> r9 = r1.wearNotificationsIds;
        if (r02 >= r82) goto L25;
        r72.put(r9.keyAt(r02), r1.wearNotificationsIds.valueAt(r02));
        r02 = r02 + 1;
        goto L22
    L25:
        r9.clear();
        ArrayList r83 = new ArrayList();
        int r03 = Build.VERSION.SDK_INT;
        if (r03 <= 27) goto L34;
        int r12 = r4.size();
        if (r1.storyPushMessages.isEmpty() == false) goto L30;
        int r13 = 1;
    L31:
        if (r12 > r13) goto L34;
        boolean r122 = false;
    L36:
        if (r122 == false) goto L39;
        if (r03 < 26) goto L39;
        checkOtherNotificationsChannel();
    L39:
        long r132 = r1.getUserConfig().getClientUserId();
        if (AndroidUtilities.needShowPasscode() == false) goto L42;
    L45:
        boolean r99 = true;
    L47:
        if (SharedConfig.passcodeHash.length() <= 0) goto L49;
        boolean r04 = true;
    L50:
        FileLog.d("showExtraNotifications: passcode=" + r04 + " waitingForPasscode=" + r99 + " selfUserId=" + r132 + " useSummaryNotification=" + r122);
        LongSparseArray r103 = new LongSparseArray();
        int r11 = r4.size();
    L51:
        if (r6 >= r11) goto L868;
        Notification r20 = r2;
        if (r83.size() >= 7) goto L54;
        DialogKey r22 = (DialogKey) r4.get(r6);
        ArrayList r21 = r4;
        int r222 = r6;
        if (r22.story == false) goto L69;
        ArrayList<StoryNotification> r05 = new ArrayList();
        if (r1.storyPushMessages.isEmpty() == false) goto L63;
        StringBuilder r06 = new StringBuilder("showExtraNotifications: [");
        boolean r27 = r99;
        r06.append(r22.dialogId);
        r06.append("] continue; story but storyPushMessages is empty");
        FileLog.d(r06.toString());
        SharedPreferences r49 = r3;
        LongSparseArray r30 = r5;
        ArrayList r23 = r83;
        LongSparseArray r43 = r72;
        LongSparseArray r78 = r103;
        int r29 = r11;
        boolean r77 = r122;
        long r58 = r132;
        Notification r31 = r20;
        ArrayList r24 = r21;
        int r25 = r222;
    L867:
        r6 = r25 + 1;
        r83 = r23;
        r4 = r24;
        r99 = r27;
        r11 = r29;
        r5 = r30;
        r2 = r31;
        r72 = r43;
        r3 = r49;
        r132 = r58;
        r122 = r77;
        r103 = r78;
        goto L51
    L63:
        ArrayList r62 = r83;
        r27 = r99;
        r29 = r11;
        boolean r28 = r122;
        long r112 = r1.storyPushMessages.get(0).dialogId;
        Iterator<Integer> r87 = r1.storyPushMessages.get(0).dateByIds.keySet().iterator();
        int r910 = 0;
    L65:
        if (r87.hasNext() == false) goto L67;
        r910 = Math.max(r910, r87.next().intValue());
        r05 = r05;
        goto L65
    L67:
        r30 = r5;
        ArrayList<StoryNotification> r52 = r05;
        long r312 = r112;
        MessageObject r123 = null;
        int r113 = r910;
        long r89 = 0;
    L68:
        long r33 = r132;
        Integer r07 = (Integer) r72.get(r22.dialogId);
        if (r22.story == false) goto L73;
        Integer r14 = 2147483646;
    L78:
        int r08 = 0;
        int r15 = 0;
    L80:
        if (r08 >= r52.size()) goto L86;
        if (r15 >= ((MessageObject) r52.get(r08)).messageOwner.date) goto L84;
        r15 = ((MessageObject) r52.get(r08)).messageOwner.date;
    L84:
        r08 = r08 + 1;
        goto L80
    L86:
        if (r22.story == false) goto L107;
        ArrayList r42 = r62;
        TLRPC.User r09 = r1.getMessagesController().getUser(Long.valueOf(r312));
        r43 = r72;
        if (r1.storyPushMessages.size() != 1) goto L93;
        if (r09 == null) goto L92;
        String r63 = UserObject.getFirstName(r09);
    L91:
        Integer r44 = r14;
    L94:
        if (r09 == null) goto L105;
        TLRPC.UserProfilePhoto r73 = r09.photo;
        if (r73 == null) goto L105;
        TLRPC.FileLocation r74 = r73.photo_small;
        if (r74 == null) goto L105;
        int r45 = r15;
        if (r74.volume_id != 0) goto L102;
    L106:
        String r47 = "showExtraNotifications: [";
        ArrayList<StoryNotification> r48 = r52;
        long r142 = r312;
        TLRPC.Chat r16 = null;
        r74 = null;
    L104:
        boolean r313 = false;
        boolean r46 = false;
        boolean r50 = false;
        DialogKey r53 = r22;
        TLRPC.User r26 = r09;
    L238:
        if (r123 != null) goto L240;
    L244:
        if (r27 == true) goto L246;
    L251:
        if (r74 == null) goto L269;
        File r010 = getFileLoader().getPathToAttach(r74, true);
        r49 = r3;
        if (Build.VERSION.SDK_INT >= 28) goto L267;
        DialogKey r51 = r53;
        Bitmap r54 = null;
        BitmapDrawable r32 = ImageLoader.getInstance().getImageFromMemory(r74, null, "50_50");
        if (r32 == null) goto L918;
        Bitmap r34 = r32.getBitmap();
    L257:
        Bitmap r410 = r34;
        File r35 = r010;
    L270:
        if (r16 == null) goto L279;
        Person.Builder r011 = new Person.Builder().setName(r63);
        if (r35 != null) goto L274;
    L278:
        File r55 = r35;
        Bitmap r75 = r410;
        r103.put(-r16.id, r011.build());
    L280:
        File r522 = r55;
        if (r46 == false) goto L286;
        if (r313 == true) goto L286;
    L284:
        TLRPC.Chat r502 = r16;
        TLRPC.User r532 = r26;
        String r542 = "max_id";
        String r552 = "dialog_id";
        Bitmap r314 = r75;
        ArrayList<StoryNotification> r36 = r48;
        NotificationsController r76 = this;
    L285:
        int r482 = r113;
        NotificationCompat.Action r17 = null;
    L327:
        Integer r012 = r76.pushDialogs.get(r142);
        if (r012 != null) goto L330;
        r012 = 0;
    L330:
        DialogKey r210 = r51;
        if (r210.story == false) goto L334;
        int r013 = r76.storyPushMessages.size();
    L335:
        if (r013 > 1) goto L337;
    L341:
        String r411 = r63;
        String r114 = r411;
    L342:
        Person r342 = (Person) r103.get(r33);
        String r512 = r114;
        if (Build.VERSION.SDK_INT < 28) goto L375;
        if (r342 != null) goto L375;
        TLRPC.User r014 = r76.getMessagesController().getUser(Long.valueOf(r33));
        if (r014 != null) goto L348;
        r014 = r76.getUserConfig().getCurrentUser();
    L348:
        if (r014 != null) goto L928;
    L370:
        long r56 = r89;
    L369:
        String r115 = "Stories";
        long r582 = r142;
    L374:
        Person r015 = r342;
        if (r123 != null) goto L378;
    L381:
        boolean r810 = true;
    L382:
        String r911 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r015 == null) goto L386;
        if (r810 == false) goto L386;
        NotificationCompat.MessagingStyle r811 = new NotificationCompat.MessagingStyle(r015);
    L387:
        int r016 = Build.VERSION.SDK_INT;
        if (r016 >= 28) goto L390;
    L396:
        r811.setConversationTitle(r411);
    L397:
        if (r016 < 28) goto L405;
        if (r46 == true) goto L402;
        if (DialogObject.isChatDialog(r582) == true) goto L405;
    L402:
        if (UserObject.isReplyUser(r582) == true) goto L405;
        boolean r017 = false;
    L406:
        r811.setGroupConversation(r017);
        StringBuilder r412 = new StringBuilder();
        String[] r143 = new String[1];
        boolean[] r152 = new boolean[1];
        if (r210.story == false) goto L444;
        ArrayList<String> r018 = new ArrayList();
        ArrayList<Object> r912 = new ArrayList();
        Pair<Integer, Boolean> r144 = r76.parseStoryPushes(r018, r912);
        int r153 = ((Integer) r144.first).intValue();
        boolean r145 = ((Boolean) r144.second).booleanValue();
        if (r145 == false) goto L412;
        ArrayList<Object> r315 = r912;
        String r64 = r115;
        r412.append(LocaleController.formatPluralString("StoryNotificationHidden", r153, new Object[0]));
        long r81 = r582;
        long r583 = r33;
        long r57 = r81;
    L411:
        boolean r322 = r145;
    L429:
        long r133 = Long.MAX_VALUE;
        int r913 = 0;
    L431:
        if (r913 >= r76.storyPushMessages.size()) goto L433;
        r133 = Math.min(r76.storyPushMessages.get(r913).date, r133);
        r913 = r913 + 1;
        r17 = r17;
        r210 = r210;
        goto L431
    L433:
        NotificationCompat.Action r472 = r17;
        DialogKey r65 = r210;
        r811.setGroupConversation(false);
        if (r018.size() != 1) goto L437;
        if (r322 == true) goto L437;
        String r019 = r018.get(0);
    L438:
        r811.addMessage(r412, r133, new Person.Builder().setName(r019).build());
        if (r322 == true) goto L441;
        Bitmap r18 = loadMultipleAvatars(r315);
    L442:
        StringBuilder r722 = r412;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r211 = null;
        int r914 = 0;
        Bitmap r413 = r18;
        String r110 = r019;
    L443:
        ArrayList<StoryNotification> r68 = r36;
        MessageObject r69 = r123;
        long r732 = r56;
        Intent r020 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
        r020.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);
        r020.setFlags(67108864);
        r020.addCategory("android.intent.category.LAUNCHER");
        if (r69 == null) goto L680;
        if (r69.isOauthPush == false) goto L680;
        r020.putExtra("oauth_url", r69.localName);
    L678:
        DialogKey r116 = r65;
    L709:
        FileLog.d("show extra notifications chatId " + r57 + " topicId " + r732);
        if (r732 == 0) goto L712;
        r020.putExtra("topicId", r732);
    L712:
        r020.putExtra("currentAccount", r76.currentAccount);
        LongSparseArray r316 = r103;
        PendingIntent r021 = PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, r020, 1140850688);
        NotificationCompat.WearableExtender r104 = new NotificationCompat.WearableExtender();
        NotificationCompat.Action r124 = r472;
        if (r472 == null) goto L715;
        r104.addAction(r124);
    L715:
        long r562 = r732;
        Intent r134 = new Intent(ApplicationLoader.applicationContext, AutoMessageHeardReceiver.class);
        r134.addFlags(32);
        r134.setAction("org.telegram.messenger.ACTION_MESSAGE_HEARD");
        r134.putExtra(r552, r57);
        int r323 = r914;
        int r154 = r482;
        r134.putExtra(r542, r154);
        r134.putExtra("currentAccount", r76.currentAccount);
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r332 = r211;
        Bitmap r362 = r413;
        NotificationCompat.Action r212 = new NotificationCompat.Action.Builder(R.drawable.msg_markread, LocaleController.getString(R.string.MarkAsRead), PendingIntent.getBroadcast(ApplicationLoader.applicationContext, r44.intValue(), r134, 201326592)).setSemanticAction(2).setShowsUserInterface(false).build();
        if (DialogObject.isEncryptedDialog(r57) == false) goto L718;
        NotificationCompat.Action r146 = r212;
        MessageObject r692 = r69;
        if (r57 == globalSecretChatId) goto L724;
        String r414 = "tgenc" + DialogObject.getEncryptedChatId(r57) + "_" + r154;
    L725:
        if (r414 == null) goto L728;
        r104.setDismissalId(r414);
        NotificationCompat.WearableExtender r213 = new NotificationCompat.WearableExtender();
        r213.setDismissalId("summary_".concat(r414));
        r84.extend(r213);
    L728:
        StringBuilder r214 = new StringBuilder("tgaccount");
        long r37 = r583;
        r214.append(r37);
        r104.setBridgeTag(r214.toString());
        if (r116.story == false) goto L735;
        r58 = r37;
        long r38 = Long.MAX_VALUE;
        int r215 = 0;
    L732:
        if (r215 >= r76.storyPushMessages.size()) goto L734;
        r38 = Math.min(r76.storyPushMessages.get(r215).date, r38);
        r215 = r215 + 1;
        r146 = r146;
        goto L732
    L734:
        NotificationCompat.Action r372 = r146;
        long r147 = r38;
        ArrayList<StoryNotification> r39 = r68;
    L736:
        NotificationCompat.Builder r216 = new NotificationCompat.Builder(ApplicationLoader.applicationContext).setContentTitle(r110).setSmallIcon(IconManager.getNotificationIcon()).setContentText(r722.toString()).setAutoCancel(true);
        if (r116.story == false) goto L739;
        r39 = r76.storyPushMessages;
    L739:
        NotificationCompat.Builder r148 = r216.setNumber(r39.size()).setGroupSummary(false).setWhen(r147).setShowWhen(true).setStyle(r811).setContentIntent(r021).extend(r104).setSortKey(String.valueOf(LongCompanionObject.MAX_VALUE - r147)).setCategory("msg");
        if (Build.VERSION.SDK_INT >= 31) goto L934;
        r148.setColor(r76.getNotificationColor());
    L934:
        Intent r022 = new Intent(ApplicationLoader.applicationContext, NotificationDismissReceiver.class);     // Catch: Exception -> L746
        r022.putExtra("messageDate", r45);     // Catch: Exception -> L746
        r022.putExtra("dialogId", r57);     // Catch: Exception -> L746
        r022.putExtra("currentAccount", r76.currentAccount);     // Catch: Exception -> L746
        if (r116.story == false) goto L748;
        r022.putExtra("story", true);     // Catch: Exception -> L746
    L748:
        if (r692 == null) goto L755;
        MessageObject r310 = r692;
        if (r310.isStoryReactionPush == false) goto L756;
        r022.putExtra("storyReaction", true);     // Catch: Exception -> L753
    L756:
        r148.setDeleteIntent(PendingIntent.getBroadcast(ApplicationLoader.applicationContext, r44.intValue(), r022, 167772160));     // Catch: Exception -> L753
    L759:
        if (r28 == false) goto L761;
        r148.setGroup(r76.notificationGroup);
        r148.setGroupAlertBehavior(1);
    L761:
        if (r310 == null) goto L779;
        TLRPC.Message r023 = r310.messageOwner;
        if (r023 == null) goto L779;
        TLRPC.ReplyMarkup r024 = r023.reply_markup;
        if ((r024 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L779;
        TLRPC.TL_replyInlineMarkup r025 = (TLRPC.TL_replyInlineMarkup) r024;
        TL_keyboard.TL_inlineButtonTypeCopy r217 = null;
        int r415 = 0;
        TL_keyboard.KeyboardInlineButton r262 = null;
    L768:
        if (r415 >= r025.rows.size()) goto L777;
        int r812 = 0;
    L771:
        if (r812 >= r025.rows.get(r415).buttons.size()) goto L776;
        TL_keyboard.KeyboardInlineButton r218 = r025.rows.get(r415).buttons.get(r812);
        TL_keyboard.TL_inlineButtonTypeCopy r915 = (TL_keyboard.TL_inlineButtonTypeCopy) TLKeyboardHelper.getType(r218, TL_keyboard.TL_inlineButtonTypeCopy.class);
        if (r915 != null) goto L774;
        r812 = r812 + 1;
        r217 = r915;
        goto L771
    L774:
        r262 = r218;
        r217 = r915;
    L776:
        if (r262 != null) goto L777;
        r415 = r415 + 1;
    L777:
        TL_keyboard.KeyboardInlineButton r026 = r262;
    L780:
        if (r026 == null) goto L782;
        Intent r416 = new Intent(ApplicationLoader.applicationContext, CopyCodeReceiver.class);
        r416.addFlags(32);
        r416.setAction("org.telegram.messenger.ACTION_COPY_CODE");
        r416.putExtra("text", r217.copy_text);
        r148.addAction(new NotificationCompat.Action.Builder(R.drawable.msg_copy, r026.text, PendingIntent.getBroadcast(ApplicationLoader.applicationContext, r44.intValue(), r416, 167772160)).setShowsUserInterface(false).build());
    L782:
        long r813 = r116.dialogId;
        if (r813 == UserObject.VERIFY) goto L796;
        if (r813 == UserObject.OAUTH) goto L796;
        if (r124 == null) goto L788;
        r148.addAction(r124);
    L788:
        if (r27 == true) goto L796;
        if (r116.story == true) goto L796;
        if (r310 != null) goto L793;
    L794:
        r148.addAction(r372);
        goto L796
    L793:
        if (r310.isStoryReactionPush == false) goto L794;
    L796:
        if (r21.size() != 1) goto L804;
        if (TextUtils.isEmpty(r85) == true) goto L804;
        if (r116.story == true) goto L804;
        r148.setSubText(r85);
    L804:
        if (DialogObject.isEncryptedDialog(r57) == false) goto L806;
        r148.setLocalOnly(true);
    L806:
        if (r362 == null) goto L809;
        r148.setLargeIcon(r362);
    L809:
        if (AndroidUtilities.needShowPasscode(false) == false) goto L811;
    L826:
        String r333 = r110;
        MessageObject r693 = r310;
        if (r502 != null) goto L834;
        if (r532 == null) goto L834;
        TLRPC.User r916 = r532;
        String r027 = r916.phone;
        if (r027 == null) goto L836;
        if (r027.length() <= 0) goto L836;
        r148.addPerson("tel:+" + r916.phone);
    L836:
        if (Build.VERSION.SDK_INT < 26) goto L865;
        if (r28 == false) goto L864;
        if (r97 == true) goto L850;
        if (r116.story == false) goto L843;
        if (r98 != 3) goto L850;
    L848:
        boolean r028 = true;
    L851:
        if (r028 == false) goto L853;
        int r219 = 2;
    L854:
        r148.setGroupAlertBehavior(r219);
        r148.setOnlyAlertOnce(!r028);
        long r220 = r57;
        boolean r66 = r116.story;
        TLRPC.Chat r105 = r502;
        if (r502 == null) goto L857;
        long r417 = r105.id;
    L858:
        r502 = r105;
        DialogKey r79 = r116;
        if (r105 != null) goto L862;
        long r106 = r220;
        r1 = r76;
        TLRPC.User r533 = r916;
        Notification r155 = r20;
        r24 = r21;
        r25 = r222;
        boolean r772 = r28;
        r78 = r316;
        String r135 = r333;
        ArrayList r762 = r42;
        boolean r125 = r46;
        MessageObject r710 = r693;
        long r814 = r417;
    L863:
        String r029 = r1.validateChannelIdForDialog(r220, r562, r66, r710, r814, r106, r125, r135);
        r562 = r562;
        r333 = r135;
        r148.setChannelId(r029);
        boolean r418 = r772;
    L866:
        FileLog.d("showExtraNotifications: holders.add " + r220);
        long r59 = r220;
        r77 = r418;
        r31 = r155;
        long r67 = r562;
        r23 = r762;
        r23.add(new C1NotificationHolder(r1, r44.intValue(), r59, r79.story, r67, r333, r533, r502, r148, r88, r90, r91, r92, r93, r94, r95, r96, r97, r98));
        r1.wearNotificationsIds.put(r59, r44);
        goto L867
    L862:
        r106 = 0;
        r1 = r76;
        r533 = r916;
        r155 = r20;
        r772 = r28;
        r78 = r316;
        r135 = r333;
        r762 = r42;
        r125 = r46;
        r710 = r693;
        r814 = r417;
        r24 = r21;
        r25 = r222;
        goto L863
    L857:
        r417 = 0;
        goto L858
    L853:
        r219 = 1;
        goto L854
    L843:
        if (r98 == 3) goto L850;
        if (r57 != r86) goto L850;
        if (r562 == r88) goto L848;
    L850:
        r028 = false;
        goto L851
    L864:
        r220 = r57;
        r1 = r76;
        r533 = r916;
        r79 = r116;
        r155 = r20;
        r24 = r21;
        r25 = r222;
        r418 = r28;
        r78 = r316;
        r762 = r42;
        r1.setNotificationChannel(r155, r148, r418);
        goto L866
    L865:
        r220 = r57;
        r1 = r76;
        r533 = r916;
        r79 = r116;
        r155 = r20;
        r24 = r21;
        r25 = r222;
        r418 = r28;
        r78 = r316;
        r762 = r42;
    L834:
        r916 = r532;
        goto L836
    L811:
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L826;
        if (r332 == null) goto L826;
        int r030 = r332.size();
        int r917 = 0;
    L814:
        if (r917 >= r030) goto L826;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r221 = r332;
        TL_keyboard.KeyboardInlineButtonRow r815 = r221.get(r917);
        int r107 = r815.buttons.size();
        int r126 = 0;
    L816:
        if (r126 >= r107) goto L825;
        TL_keyboard.KeyboardInlineButton r419 = r815.buttons.get(r126);
        int r263 = r030;
        TL_keyboard.TL_inlineButtonTypeCallback r031 = (TL_keyboard.TL_inlineButtonTypeCallback) TLKeyboardHelper.getType(r419, TL_keyboard.TL_inlineButtonTypeCallback.class);
        String r334 = r110;
        if (r031 == null) goto L823;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r352 = r221;
        MessageObject r694 = r310;
        Intent r111 = new Intent(ApplicationLoader.applicationContext, NotificationCallbackReceiver.class);
        r111.putExtra("currentAccount", r76.currentAccount);
        r111.putExtra("did", r57);
        byte[] r032 = r031.data;
        if (r032 == null) goto L822;
        r111.putExtra("data", r032);
    L822:
        r111.putExtra("mid", r323);
        String r033 = r419.text;
        Context r311 = ApplicationLoader.applicationContext;
        int r420 = r76.lastButtonId;
        r76.lastButtonId = r420 + 1;
        r148.addAction(0, r033, PendingIntent.getBroadcast(r311, r420, r111, 201326592));
    L824:
        r126 = r126 + 1;
        r030 = r263;
        r110 = r334;
        r221 = r352;
        r310 = r694;
        goto L816
    L823:
        r352 = r221;
        r694 = r310;
        goto L824
    L825:
        r917 = r917 + 1;
        r332 = r221;
        r310 = r310;
    L779:
        r026 = null;
        r217 = null;
    L753:
        e = e;
    L758:
        FileLog.e(e);
        goto L759
    L755:
        r310 = r692;
    L746:
        e = e;
        r310 = r692;
        goto L758
    L735:
        r58 = r37;
        r372 = r146;
        r39 = r68;
        r147 = ((MessageObject) r39.get(0)).messageOwner.date * 1000;
        goto L736
    L724:
        r414 = null;
        goto L725
    L718:
        if (DialogObject.isUserDialog(r57) == false) goto L720;
        r414 = "tguser" + r57 + "_" + r154;
        r146 = r212;
        r692 = r69;
        goto L725
    L720:
        StringBuilder r421 = new StringBuilder("tgchat");
        r146 = r212;
        r692 = r69;
        r421.append(-r57);
        r421.append("_");
        r421.append(r154);
        r414 = r421.toString();
    L680:
        if (r69 == null) goto L685;
        if (r69.isStoryReactionPush == false) goto L685;
        r020.putExtra("storyId", Math.abs(r69.getId()));
    L685:
        if (r69 != null) goto L687;
    L695:
        r116 = r65;
        if (r116.story == false) goto L703;
        long[] r127 = new long[r76.storyPushMessages.size()];
        int r136 = 0;
    L699:
        if (r136 >= r76.storyPushMessages.size()) goto L701;
        r127[r136] = r76.storyPushMessages.get(r136).dialogId;
        r136 = r136 + 1;
        goto L699
    L701:
        r020.putExtra("storyDialogIds", r127);
        goto L709
    L703:
        if (DialogObject.isEncryptedDialog(r57) == false) goto L706;
        r020.putExtra("encId", DialogObject.getEncryptedChatId(r57));
        goto L709
    L706:
        if (DialogObject.isUserDialog(r57) == false) goto L708;
        r020.putExtra("userId", r57);
        goto L709
    L708:
        r020.putExtra("chatId", -r57);
        goto L709
    L687:
        if (r69.isLiveStoryPush == false) goto L695;
        if (r57 >= 0) goto L691;
        String r317 = "storyId";
        r020.putExtra("chatId", -r57);
    L694:
        r020.putExtra(r317, Math.abs(r69.getId()));
        goto L678
    L691:
        r317 = "storyId";
        if (r57 <= 0) goto L694;
        r020.putExtra("userId", r57);
        goto L694
    L441:
        r18 = null;
    L437:
        r019 = LocaleController.formatPluralString(r64, r153, new Object[0]);
        goto L438
    L412:
        r315 = r912;
        r64 = r115;
        if (r018.isEmpty() == false) goto L415;
        FileLog.d(r47 + r582 + "] continue; story but names is empty");
        r58 = r33;
        r1 = r76;
        r78 = r103;
        r31 = r20;
        r24 = r21;
        r25 = r222;
        r77 = r28;
    L130:
        r23 = r42;
        goto L867
    L415:
        long r816 = r582;
        r583 = r33;
        r57 = r816;
        if (r018.size() != 1) goto L421;
        if (r153 != 1) goto L419;
        r412.append(LocaleController.getString("StoryNotificationSingle"));
        goto L411
    L419:
        r412.append(LocaleController.formatPluralString("StoryNotification1", r153, new Object[]{r018.get(0)}));
        goto L411
    L421:
        if (r018.size() != 2) goto L423;
        r322 = r145;
        r412.append(LocaleController.formatString(R.string.StoryNotification2, new Object[]{r018.get(0), r018.get(1)}));
        goto L429
    L423:
        r322 = r145;
        if (r018.size() == 3) goto L426;
    L428:
        r412.append(LocaleController.formatPluralString("StoryNotification4", r76.storyPushMessages.size() - 2, new Object[]{r76.cutLastName(r018.get(0)), r76.cutLastName(r018.get(1))}));
        goto L429
    L426:
        if (r76.storyPushMessages.size() != 3) goto L428;
        r412.append(LocaleController.formatString(R.string.StoryNotification3, new Object[]{r76.cutLastName(r018.get(0)), r76.cutLastName(r018.get(1)), r76.cutLastName(r018.get(2))}));
        goto L429
    L444:
        long r817 = r582;
        r583 = r33;
        r57 = r817;
        r65 = r210;
        String r117 = r47;
        r472 = r17;
        int r118 = r36.size() - 1;
        int r223 = 0;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r137 = null;
    L445:
        if (r118 < 0) goto L672;
        int r642 = r118;
        MessageObject r119 = (MessageObject) r36.get(r118);
        int r662 = r223;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r672 = r137;
        ArrayList<StoryNotification> r682 = r36;
        long r224 = MessageObject.getTopicId(r76.currentAccount, r119.messageOwner, r76.getMessagesController().isForum(r119));
        if (r56 == r224) goto L450;
        StringBuilder r034 = new StringBuilder(r117);
        r034.append(r57);
        r034.append("] continue; topic id is not equal: topicId=");
        String r138 = r117;
        MessageObject r695 = r123;
        long r1110 = r56;
        r034.append(r1110);
        r034.append(" messageTopicId=");
        r034.append(r224);
        r034.append("; selfId=");
        r034.append(r76.getUserConfig().getClientUserId());
        FileLog.d(r034.toString());
        StringBuilder r723 = r412;
        long r733 = r1110;
        String r572 = r138;
        String[] r563 = r143;
    L449:
        boolean[] r70 = r152;
        String r156 = r911;
    L670:
        r223 = r662;
        r137 = r672;
    L671:
        r118 = r642 - 1;
        r911 = r156;
        r143 = r563;
        r117 = r572;
        r36 = r682;
        r123 = r695;
        r152 = r70;
        r412 = r723;
        r56 = r733;
        goto L445
    L450:
        String r139 = r117;
        r695 = r123;
        long r1111 = r56;
        String r035 = r76.getShortStringForMessage(r119, r143, r152);
        if (r57 != UserObject.OAUTH) goto L454;
        r143[0] = LocaleController.getString(R.string.BotAuthNotificationTitle);
        r572 = r139;
        r563 = r143;
    L466:
        if (r035 != null) goto L472;
        if (BuildVars.LOGS_ENABLED == false) goto L470;
        FileLog.w("message text is null for " + r119.getId() + " did = " + r119.getDialogId());
    L470:
        r723 = r412;
        r733 = r1111;
        goto L449
    L472:
        if (r412.length() <= 0) goto L475;
        r412.append("\n\n");
    L475:
        if (r57 != r583) goto L477;
    L482:
        String r225 = r563[0];
        if (r225 == null) goto L485;
        r412.append(String.format("%1$s: %2$s", new Object[]{r225, r035}));
    L481:
        String r226 = r035;
        if (r57 != UserObject.VERIFY) goto L492;
        if (r119.getForwardedFromId() == null) goto L492;
        long r1310 = r119.getForwardedFromId().longValue();
    L500:
        r723 = r412;
        Person r318 = (Person) r103.get(r1310 + (r1111 << 16));
        String r422 = r563[0];
        if (r422 != null) goto L515;
        if (r27 == true) goto L504;
        Person r702 = r318;
    L514:
        r422 = r911;
    L516:
        if (r702 != null) goto L518;
    L521:
        Person.Builder r319 = new Person.Builder().setName(r422);
        if (r152[0] == true) goto L524;
    L581:
        r733 = r1111;
        r70 = r152;
        r156 = r911;
    L580:
        NotificationCompat.MessagingStyle r918 = r811;
    L582:
        Person r320 = r319.build();
        r103.put(r1310, r320);
    L584:
        if (DialogObject.isEncryptedDialog(r57) == false) goto L586;
        r811 = r918;
        r811.addMessage(r226, r119.messageOwner.date * 1000, r320);
    L666:
        if (r57 != 777000) goto L670;
        TLRPC.ReplyMarkup r036 = r119.messageOwner.reply_markup;
        if ((r036 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L670;
        r137 = ((TLRPC.TL_replyInlineMarkup) r036).rows;
        r223 = r119.getId();
        goto L671
    L586:
        if (r70[0] == true) goto L588;
    L599:
        r811 = r918;
    L643:
        r811.addMessage(r226, r119.messageOwner.date * 1000, r320);
    L645:
        if (r70[0] == false) goto L666;
        if (r27 == true) goto L666;
        if (r119.isVoice() == false) goto L666;
        List<NotificationCompat.MessagingStyle.Message> r037 = r811.getMessages();
        if (r037.isEmpty() == true) goto L666;
        File r227 = r76.getFileLoader().getPathToMessage(r119.messageOwner);
        if (r227.exists() == false) goto L660;
        Uri r228 = FileProvider.getUriForFile(ApplicationLoader.applicationContext, ApplicationLoader.getApplicationId() + ".provider", r227);     // Catch: Exception -> L655
    L656:
        if (r228 == null) goto L666;
        r037.get(r037.size() - 1).setData("audio/ogg", r228);
    L655:
        r228 = null;
        goto L656
    L660:
        if (r119.getDocument() == null) goto L666;
        String r038 = FileLoader.getAttachFileName(r119.getDocument());
        if (r76.pendingVoiceLoads.contains(r038) == true) goto L666;
        r76.pendingVoiceLoads.add(r038);
        r76.getFileLoader().loadFile(r119.getDocument(), r119, 3, 0);
        goto L666
    L588:
        if (Build.VERSION.SDK_INT < 28) goto L599;
        if (((ActivityManager) ApplicationLoader.applicationContext.getSystemService("activity")).isLowRamDevice() == true) goto L599;
        if (r27 == true) goto L599;
        if (r119.isSecretMedia() == true) goto L599;
        if (r119.type != 1) goto L597;
    L600:
        File r818 = r76.getFileLoader().getPathToMessage(r119.messageOwner);
        if (r818.exists() == true) goto L603;
    L621:
        NotificationCompat.MessagingStyle r71 = r918;
        File r819 = r818;
        File r128 = null;
    L622:
        NotificationCompat.MessagingStyle.Message r919 = new NotificationCompat.MessagingStyle.Message(r226, r119.messageOwner.date * 1000, r320);
        if (r119.isSticker() == false) goto L626;
        String r039 = "image/webp";
    L625:
        String r1112 = r039;
        if (r819.exists() == false) goto L635;
        Uri r040 = FileProvider.getUriForFile(ApplicationLoader.applicationContext, ApplicationLoader.getApplicationId() + ".provider", r819);     // Catch: Exception -> L631
    L637:
        if (r040 == null) goto L642;
        r919.setData(r1112, r040);
        r811 = r71;
        r811.addMessage(r919);
        ApplicationLoader.applicationContext.grantUriPermission("com.android.systemui", r040, 1);
        AndroidUtilities.runOnUIThread(new NotificationsController$$ExternalSyntheticLambda33(r040, r128), 20000);
        if (TextUtils.isEmpty(r119.caption) == true) goto L645;
        r811.addMessage(r119.caption, r119.messageOwner.date * 1000, r320);
        goto L645
    L642:
        r811 = r71;
    L631:
        e = move-exception;
        FileLog.e(e);
    L633:
        r040 = null;
        goto L637
    L635:
        if (r76.getFileLoader().isLoadingFile(r819.getName()) == false) goto L633;
        r040 = new Uri.Builder().scheme("content").authority(NotificationImageProvider.getAuthority()).appendPath("msg_media_raw").appendPath(r76.currentAccount + r156).appendPath(r819.getName()).appendQueryParameter("final_path", r819.getAbsolutePath()).build();
        goto L637
    L626:
        r039 = "image/jpeg";
        goto L625
    L603:
        if (r119.hasMediaSpoilers() == false) goto L621;
        r128 = new File(r818.getParentFile(), r818.getName() + ".blur.jpg");
        if (r128.exists() == false) goto L932;
        File r752 = r818;
        r71 = r918;
    L620:
        r819 = r752;
        goto L622
    L932:
        Bitmap r041 = BitmapFactory.decodeFile(r818.getAbsolutePath());     // Catch: Exception -> L616
        Bitmap r1311 = Utilities.stackBlurBitmapMax(r041);     // Catch: Exception -> L616
        r041.recycle();     // Catch: Exception -> L616
        Bitmap r042 = Bitmap.createScaledBitmap(r1311, r041.getWidth(), r041.getHeight(), true);     // Catch: Exception -> L616
        Utilities.stackBlurBitmap(r042, 5);     // Catch: Exception -> L616
        r1311.recycle();     // Catch: Exception -> L616
        Canvas r1113 = new Canvas(r042);     // Catch: Exception -> L616
        r752 = r818;
        r76.mediaSpoilerEffect.setColor(ColorUtils.setAlphaComponent(-1, (int) (Color.alpha(-1) * 0.325f)));     // Catch: Exception -> L614
        r71 = r918;
        r76.mediaSpoilerEffect.setBounds(0, 0, r042.getWidth(), r042.getHeight());     // Catch: Exception -> L612
        r76.mediaSpoilerEffect.draw(r1113);     // Catch: Exception -> L612
        FileOutputStream r820 = new FileOutputStream(r128);     // Catch: Exception -> L612
        r042.compress(Bitmap.CompressFormat.JPEG, 100, r820);     // Catch: Exception -> L612
        r820.close();     // Catch: Exception -> L612
        r042.recycle();     // Catch: Exception -> L612
        r819 = r128;
    L612:
        e = e;
    L618:
        FileLog.e(e);
    L614:
        e = e;
    L615:
        r71 = r918;
    L616:
        e = e;
        r752 = r818;
        goto L615
    L597:
        if (r119.isSticker() == false) goto L599;
    L524:
        if (DialogObject.isEncryptedDialog(r57) == true) goto L581;
        r70 = r152;
        if (Build.VERSION.SDK_INT >= 28) goto L528;
        r156 = r911;
        r733 = r1111;
        goto L580
    L528:
        if (DialogObject.isUserDialog(r57) == true) goto L530;
        if (r46 == true) goto L530;
        r733 = r1111;
        long r1114 = r119.getSenderId();
        TLRPC.User r423 = r76.getMessagesController().getUser(Long.valueOf(r1114));
        if (r423 != null) goto L536;
        r423 = r76.getMessagesStorage().getUserSync(r1114);
        if (r423 == null) goto L536;
        r76.getMessagesController().putUser(r423, true);
    L536:
        if (r423 == null) goto L546;
        TLRPC.UserProfilePhoto r1115 = r423.photo;
        if (r1115 == null) goto L546;
        TLRPC.FileLocation r1116 = r1115.photo_small;
        if (r1116 == null) goto L546;
        NotificationCompat.MessagingStyle r129 = r811;
        r156 = r911;
        if (r1116.volume_id != 0) goto L544;
    L547:
        File r424 = null;
    L549:
        if (r424 != null) goto L566;
        if (r57 != UserObject.VERIFY) goto L566;
        if (r119.getForwardedFromId() == null) goto L566;
        if (r1310 < 0) goto L567;
        TLRPC.User r043 = r76.getMessagesController().getUser(Long.valueOf(r1310));
        if (r043 == null) goto L566;
        TLRPC.UserProfilePhoto r821 = r043.photo;
        if (r821 == null) goto L566;
        TLRPC.FileLocation r822 = r821.photo_small;
        if (r822 == null) goto L566;
        r918 = r129;
        if (r822.volume_id != 0) goto L564;
    L578:
        loadRoundAvatar(r57, r424, r319);
        goto L582
    L564:
        if (r822.local_id == 0) goto L578;
        r424 = r76.getFileLoader().getPathToAttach(r043.photo.photo_small, true);
        goto L578
    L567:
        r918 = r129;
        TLRPC.Chat r044 = r76.getMessagesController().getChat(Long.valueOf(-r1310));
        if (r044 == null) goto L578;
        TLRPC.ChatPhoto r823 = r044.photo;
        if (r823 == null) goto L578;
        TLRPC.FileLocation r824 = r823.photo_small;
        if (r824 == null) goto L578;
        if (r824.volume_id == 0) goto L578;
        if (r824.local_id == 0) goto L578;
        r424 = r76.getFileLoader().getPathToAttach(r044.photo.photo_small, true);
    L566:
        r918 = r129;
        goto L578
    L544:
        if (r1116.local_id == 0) goto L547;
        r424 = r76.getFileLoader().getPathToAttach(r423.photo.photo_small, true);
    L546:
        r129 = r811;
        r156 = r911;
    L530:
        r156 = r911;
        r733 = r1111;
        r129 = r811;
        r424 = r522;
        goto L549
    L518:
        if (TextUtils.equals(r702.getName(), r422) == false) goto L521;
        r733 = r1111;
        r320 = r702;
        r70 = r152;
        r156 = r911;
        r918 = r811;
        goto L584
    L504:
        if (DialogObject.isChatDialog(r57) == false) goto L510;
        if (r46 == false) goto L509;
        r702 = r318;
        if (Build.VERSION.SDK_INT <= 27) goto L514;
        r422 = LocaleController.getString(R.string.NotificationHiddenChatName);
        goto L516
    L509:
        r702 = r318;
        r422 = LocaleController.getString(R.string.NotificationHiddenChatUserName);
        goto L516
    L510:
        r702 = r318;
        if (Build.VERSION.SDK_INT <= 27) goto L514;
        r422 = LocaleController.getString(R.string.NotificationHiddenName);
        goto L516
    L515:
        r702 = r318;
    L492:
        if (DialogObject.isUserDialog(r57) == true) goto L499;
        if (r46 == false) goto L497;
        r1310 = -r57;
        goto L500
    L497:
        if (DialogObject.isChatDialog(r57) == false) goto L499;
        r1310 = r119.getSenderId();
    L499:
        r1310 = r57;
        goto L500
    L485:
        r412.append(r035);
        goto L481
    L477:
        if (r119.messageOwner.from_scheduled == false) goto L482;
        if (DialogObject.isUserDialog(r57) == false) goto L482;
        r035 = String.format("%1$s: %2$s", new Object[]{LocaleController.getString(R.string.NotificationMessageScheduledName), r035});
        r412.append(r035);
        goto L481
    L454:
        if (r57 == UserObject.VERIFY) goto L456;
    L458:
        r572 = r139;
        r563 = r143;
        if (r57 != r583) goto L462;
        r563[0] = r512;
        goto L466
    L462:
        if (DialogObject.isChatDialog(r57) == false) goto L466;
        if (r119.messageOwner.from_scheduled == false) goto L466;
        r563[0] = LocaleController.getString(R.string.NotificationMessageScheduledName);
        goto L466
    L456:
        if (r119.getForwardedFromId() == null) goto L458;
        r572 = r139;
        r563 = r143;
        r563[0] = r76.getMessagesController().getPeerName(r119.getForwardedFromId().longValue());
        goto L466
    L672:
        r722 = r412;
        r413 = r314;
        r110 = r512;
        r914 = r223;
        r211 = r137;
    L405:
        r017 = true;
        goto L406
    L390:
        if (DialogObject.isChatDialog(r582) == false) goto L393;
        if (r46 == false) goto L396;
    L393:
        if (UserObject.isReplyUser(r582) == false) goto L397;
    L386:
        r811 = new NotificationCompat.MessagingStyle(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L387
    L378:
        if ((r123.messageOwner.action instanceof TLRPC.TL_messageActionChatJoinedByRequest) == false) goto L381;
        r810 = false;
        goto L382
    L928:
        TLRPC.UserProfilePhoto r1117 = r014.photo;     // Catch: Throwable -> L371
        if (r1117 == null) goto L370;
        TLRPC.FileLocation r1118 = r1117.photo_small;     // Catch: Throwable -> L371
        if (r1118 == null) goto L370;
        r56 = r89;
        if (r1118.volume_id == 0) goto L369;
        if (r1118.local_id == 0) goto L369;
        Person.Builder r825 = new Person.Builder().setName(LocaleController.getString(R.string.FromYou));     // Catch: Throwable -> L367
        r115 = "Stories";
        r582 = r142;
        loadRoundAvatar(r76.getUserConfig().getClientUserId(), r76.getFileLoader().getPathToAttach(r014.photo.photo_small, true), r825);     // Catch: Throwable -> L365
        Person r826 = r825.build();     // Catch: Throwable -> L365
        r103.put(r33, r826);     // Catch: Throwable -> L363
        r342 = r826;
    L363:
        th = th;
        r342 = r826;
    L373:
        FileLog.e(th);
    L365:
        th = th;
    L367:
        th = th;
    L368:
        r115 = "Stories";
        r582 = r142;
    L371:
        th = th;
        r56 = r89;
    L375:
        r56 = r89;
        goto L369
    L337:
        if (Build.VERSION.SDK_INT >= 28) goto L341;
        r411 = String.format("%1$s (%2$d)", new Object[]{r63, Integer.valueOf(r013)});
        r114 = r63;
        goto L342
    L334:
        r013 = Math.max(r012.intValue(), r36.size());
    L286:
        if (r50 == false) goto L284;
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L284;
        if (r33 == r142) goto L284;
        if (UserObject.isReplyUser(r142) == true) goto L284;
        r314 = r75;
        r76 = this;
        if (MessagesController.getInstance(r76.currentAccount).getSendPaidMessagesStars(r142) > 0) goto L325;
        r502 = r16;
        r532 = r26;
        Intent r045 = new Intent(ApplicationLoader.applicationContext, WearReplyReceiver.class);
        r045.putExtra("dialog_id", r142);
        r045.putExtra("max_id", r113);
        r045.putExtra("topic_id", r89);
        r045.putExtra("currentAccount", r76.currentAccount);
        if (r48.isEmpty() == true) goto L319;
        ArrayList r120 = new ArrayList();
        r542 = "max_id";
        int r229 = 0;
    L299:
        if (r229 >= r48.size()) goto L311;
        ArrayList<StoryNotification> r321 = r48;
        MessageObject r483 = (MessageObject) r321.get(r229);
        if (r483 != null) goto L303;
    L309:
        int r553 = r229;
    L310:
        r229 = r553 + 1;
        r48 = r321;
        goto L299
    L303:
        if (r483.isVoice() == false) goto L309;
        if (r483.isContentUnread() == false) goto L309;
        if (r483.isOut() == true) goto L309;
        r553 = r229;
        r120.add(Integer.valueOf(r483.getId()));
        goto L310
    L311:
        r36 = r48;
        if (r120.isEmpty() == true) goto L317;
        int r230 = r120.size();
        r482 = r113;
        int[] r1119 = new int[r230];
        r552 = "dialog_id";
        int r425 = 0;
    L314:
        if (r425 >= r230) goto L316;
        r1119[r425] = ((Integer) r120.get(r425)).intValue();
        r425 = r425 + 1;
        goto L314
    L316:
        r045.putExtra("voice_msg_ids", r1119);
    L320:
        PendingIntent r046 = PendingIntent.getBroadcast(ApplicationLoader.applicationContext, r44.intValue(), r045, 167772160);
        RemoteInput r121 = new RemoteInput.Builder(EXTRA_VOICE_REPLY).setLabel(LocaleController.getString(R.string.Reply)).build();
        if (DialogObject.isChatDialog(r142) == false) goto L323;
        String r231 = LocaleController.formatString(R.string.ReplyToGroup, new Object[]{r63});
    L324:
        r17 = new NotificationCompat.Action.Builder(R.drawable.ic_reply_icon, r231, r046).setAllowGeneratedReplies(true).setSemanticAction(1).addRemoteInput(r121).setShowsUserInterface(false).build();
        goto L327
    L323:
        r231 = LocaleController.formatString(R.string.ReplyToUser, new Object[]{r63});
        goto L324
    L317:
        r552 = "dialog_id";
    L318:
        r482 = r113;
        goto L320
    L319:
        r542 = "max_id";
        r552 = "dialog_id";
        r36 = r48;
        goto L318
    L325:
        r502 = r16;
        r532 = r26;
        r542 = "max_id";
        r552 = "dialog_id";
        r36 = r48;
        goto L285
    L274:
        if (r35.exists() == false) goto L278;
        if (Build.VERSION.SDK_INT < 28) goto L278;
        loadRoundAvatar(r142, r35, r011);
        goto L278
    L279:
        r55 = r35;
        r75 = r410;
        goto L280
    L918:
        if (r010.exists() == false) goto L266;
        float r426 = 160.0f / AndroidUtilities.dp(50.0f);     // Catch: Throwable -> L915
        BitmapFactory.Options r324 = new BitmapFactory.Options();     // Catch: Throwable -> L915
        if (r426 >= 1.0f) goto L263;
        int r427 = 1;
    L264:
        r324.inSampleSize = r427;     // Catch: Throwable -> L915
        r34 = BitmapFactory.decodeFile(r010.getAbsolutePath(), r324);     // Catch: Throwable -> L915
        goto L257
    L263:
        r427 = (int) r426;     // Catch: Throwable -> L915
        goto L264
    L266:
        r34 = null;
    L268:
        r35 = r010;
        r410 = r54;
        goto L270
    L267:
        r51 = r53;
        r54 = null;
        goto L268
    L269:
        r49 = r3;
        r51 = r53;
        r35 = null;
        r410 = null;
        goto L270
    L246:
        if (DialogObject.isChatDialog(r142) == false) goto L249;
        String r047 = LocaleController.getString(R.string.NotificationHiddenChatName);
    L248:
        r63 = r047;
        r74 = null;
        r50 = false;
        goto L251
    L249:
        r047 = LocaleController.getString(R.string.NotificationHiddenName);
        goto L248
    L240:
        if (r123.isStoryReactionPush == false) goto L244;
        if (r3.getBoolean("EnableReactionsPreview", true) == true) goto L244;
        r63 = LocaleController.getString(R.string.NotificationHiddenChatName);
        r74 = null;
        r50 = false;
        goto L244
    L102:
        if (r74.local_id == 0) goto L106;
        r47 = "showExtraNotifications: [";
        r48 = r52;
        r142 = r312;
        r16 = null;
    L105:
        r45 = r15;
        goto L106
    L92:
        r63 = r1.storyPushMessages.get(0).localName;
        goto L91
    L93:
        r44 = r14;
        r63 = LocaleController.formatPluralString("Stories", r1.storyPushMessages.size(), new Object[0]);
        goto L94
    L107:
        r42 = r62;
        r43 = r72;
        r44 = r14;
        r45 = r15;
        if (DialogObject.isEncryptedDialog(r312) == true) goto L223;
        if (r123 != null) goto L111;
    L117:
        boolean r048 = false;
    L119:
        if (DialogObject.isUserDialog(r312) == false) goto L157;
        TLRPC.User r610 = r1.getMessagesController().getUser(Long.valueOf(r312));
        if (r610 == null) goto L123;
        r142 = r312;
        String r711 = UserObject.getUserName(r610);
        boolean r3110 = r048;
        TLRPC.UserProfilePhoto r049 = r610.photo;
        if (r049 == null) goto L140;
        TLRPC.FileLocation r050 = r049.photo_small;
        if (r050 == null) goto L140;
        TLRPC.User r325 = r610;
        String r462 = r711;
        if (r050.volume_id != 0) goto L138;
    L141:
        String r712 = r462;
    L125:
        r050 = null;
    L143:
        if (r142 != UserObject.OAUTH) goto L148;
        r63 = LocaleController.getString(R.string.BotAuthNotificationTitle);
    L145:
        r74 = r050;
        r47 = "showExtraNotifications: [";
        r48 = r52;
        boolean r130 = r3110;
        TLRPC.Chat r051 = null;
        r313 = false;
        r46 = false;
    L146:
        r53 = r22;
    L193:
        int r232 = (r142 > UserObject.VERIFY ? 1 : (r142 == UserObject.VERIFY ? 0 : -1));
        if (r232 != 0) goto L219;
        if (r123 == null) goto L219;
        if (r123.getForwardedFromId() == null) goto L219;
        Long r428 = r123.getForwardedFromId();
        TLRPC.Chat r492 = r051;
        r50 = r130;
        long r052 = r428.longValue();
        if (DialogObject.isUserDialog(r052) == false) goto L210;
        TLRPC.UserProfilePhoto r053 = getMessagesController().getUser(r428).photo;
        if (r053 == null) goto L209;
        TLRPC.FileLocation r054 = r053.photo_small;
        if (r054 == null) goto L209;
        int r429 = r232;
        if (r054.volume_id != 0) goto L207;
    L220:
        r26 = r325;
        r16 = r492;
        if (r429 != 0) goto L238;
    L222:
        r50 = false;
        goto L238
    L207:
        if (r054.local_id == 0) goto L220;
    L218:
        r74 = r054;
    L209:
        r429 = r232;
        goto L220
    L210:
        r429 = r232;
        TLRPC.ChatPhoto r055 = getMessagesController().getChat(Long.valueOf(-r052)).photo;
        if (r055 == null) goto L220;
        r054 = r055.photo_small;
        if (r054 == null) goto L220;
        if (r054.volume_id == 0) goto L220;
        if (r054.local_id == 0) goto L220;
    L219:
        r492 = r051;
        r50 = r130;
        goto L209
    L148:
        if (r142 != UserObject.VERIFY) goto L151;
        r63 = LocaleController.getString(R.string.VerifyCodesNotifications);
        goto L145
    L151:
        if (UserObject.isReplyUser(r142) == false) goto L154;
        r63 = LocaleController.getString(R.string.RepliesTitle);
        goto L145
    L154:
        if (r142 != r33) goto L156;
        r63 = LocaleController.getString(R.string.MessageScheduledReminderNotification);
        goto L145
    L156:
        r47 = "showExtraNotifications: [";
        r48 = r52;
        r63 = r712;
        r130 = r3110;
        r313 = false;
        r46 = false;
        r74 = r050;
        r53 = r22;
        r051 = null;
        goto L193
    L138:
        if (r050.local_id == 0) goto L141;
        r712 = r462;
    L140:
        r325 = r610;
        r462 = r711;
        goto L141
    L123:
        if (r123.isFcmMessage() == false) goto L127;
        r712 = r123.localName;
        r142 = r312;
        r3110 = r048;
        r325 = r610;
        goto L125
    L127:
        if (BuildVars.LOGS_ENABLED == false) goto L129;
        FileLog.w("not found user to show dialog notification " + r312);
    L129:
        r49 = r3;
        r78 = r103;
        r31 = r20;
        r24 = r21;
        r25 = r222;
        r77 = r28;
        r58 = r33;
        goto L130
    L157:
        r142 = r312;
        boolean r3111 = r048;
        r051 = r1.getMessagesController().getChat(Long.valueOf(-r142));
        if (r051 == null) goto L160;
        boolean r611 = r051.megagroup;
        if (ChatObject.isChannel(r051) == true) goto L168;
    L171:
        boolean r713 = false;
    L172:
        r63 = r1.getTitle(r051);
        r46 = r713;
        TLRPC.ChatPhoto r714 = r051.photo;
        if (r714 == null) goto L181;
        r74 = r714.photo_small;
        if (r74 == null) goto L181;
        r47 = "showExtraNotifications: [";
        r48 = r52;
        if (r74.volume_id != 0) goto L179;
    L182:
        r74 = null;
    L184:
        if (r89 == 0) goto L188;
        r53 = r22;
        TLRPC.TL_forumTopic r131 = r1.getMessagesController().getTopicsController().findTopic(r051.id, r89);
        if (r131 == null) goto L189;
        r63 = r131.title + " in " + r63;
    L189:
        if (r3111 == false) goto L192;
        r130 = ChatObject.canSendPlain(r051);
    L191:
        r313 = r611;
        r325 = null;
        goto L193
    L192:
        r130 = r3111;
        goto L191
    L188:
        r53 = r22;
        goto L189
    L179:
        if (r74.local_id == 0) goto L182;
    L181:
        r47 = "showExtraNotifications: [";
        r48 = r52;
        goto L182
    L168:
        if (r051.megagroup == true) goto L171;
        r713 = true;
        goto L172
    L160:
        if (r123.isFcmMessage() == false) goto L163;
        boolean r612 = r123.isSupergroup();
        String r715 = r123.localName;
        r313 = r612;
        r47 = "showExtraNotifications: [";
        r48 = r52;
        r46 = r123.localChannel;
        r63 = r715;
        r130 = false;
        r74 = null;
        r325 = null;
        goto L146
    L163:
        if (BuildVars.LOGS_ENABLED == false) goto L129;
        FileLog.w("not found chat to show dialog notification " + r142);
        goto L129
    L111:
        if (r123.isReactionPush == true) goto L117;
        if (r123.isStoryReactionPush == true) goto L117;
        if (r312 == 777000) goto L117;
        r048 = true;
        goto L119
    L223:
        r47 = "showExtraNotifications: [";
        r48 = r52;
        r142 = r312;
        r53 = r22;
        if (r142 == globalSecretChatId) goto L236;
        int r056 = DialogObject.getEncryptedChatId(r142);
        TLRPC.EncryptedChat r140 = getMessagesController().getEncryptedChat(Integer.valueOf(r056));
        if (r140 == null) goto L228;
        TLRPC.User r057 = getMessagesController().getUser(Long.valueOf(r140.user_id));
        if (r057 == null) goto L234;
    L237:
        r63 = LocaleController.getString(R.string.SecretChatName);
        r26 = r057;
        r16 = null;
        r74 = null;
        r313 = false;
        r46 = false;
        goto L222
    L234:
        if (BuildVars.LOGS_ENABLED == true) goto L235;
    L230:
        r1 = this;
        r49 = r3;
        r78 = r103;
        r31 = r20;
        r24 = r21;
        r25 = r222;
        r77 = r28;
        r58 = r33;
        r23 = r42;
        goto L867
    L235:
        FileLog.w("not found secret chat user to show dialog notification " + r140.user_id);
        goto L230
    L228:
        if (BuildVars.LOGS_ENABLED == false) goto L230;
        FileLog.w("not found secret chat to show dialog notification " + r056);
        goto L230
    L236:
        r057 = null;
        goto L237
    L73:
        long r149 = r22.dialogId;
        if (r07 != null) goto L77;
        r07 = Integer.valueOf(((int) r149) + ((int) (r149 >> 32)));
    L76:
        r14 = r07;
        goto L78
    L77:
        r72.remove(r149);
        goto L76
    L69:
        r62 = r83;
        r27 = r99;
        r29 = r11;
        r28 = r122;
        long r1120 = r22.dialogId;
        r89 = r22.topicId;
        ArrayList<StoryNotification> r058 = (ArrayList) r5.get(r1120);
        r30 = r5;
        r52 = r058;
        r113 = ((MessageObject) r058.get(0)).getId();
        r123 = (MessageObject) r058.get(0);
        r312 = r1120;
        goto L68
    L54:
        FileLog.d("showExtraNotifications: break from holders, count over 7");
        Notification r3112 = r20;
    L55:
        LongSparseArray r432 = r72;
        ArrayList r233 = r83;
        LongSparseArray r782 = r103;
        if (r122 == true) goto L871;
        ArrayList r4210 = r233;
        if (r1.openedInBubbleDialogs.isEmpty() == true) goto L884;
    L887:
        int r613 = 0;
    L889:
        if (r613 >= r432.size()) goto L898;
        LongSparseArray r234 = r432;
        if (r1.openedInBubbleDialogs.contains(Long.valueOf(r234.keyAt(r613))) == true) goto L897;
        Integer r059 = (Integer) r234.valueAt(r613);
        if (BuildVars.LOGS_ENABLED == false) goto L896;
        FileLog.d("cancel notification id " + r059);
    L896:
        notificationManager.cancel(r059.intValue());
    L897:
        r613 = r613 + 1;
        r432 = r234;
        goto L889
    L898:
        ArrayList r060 = new ArrayList(r4210.size());
        FileLog.d("showExtraNotifications: holders.size()=" + r4210.size());
        int r235 = r4210.size();
        int r614 = 0;
    L899:
        if (r614 >= r235) goto L914;
        ArrayList r326 = r4210;
        C1NotificationHolder r430 = (C1NotificationHolder) r326.get(r614);
        r060.clear();
        if (Build.VERSION.SDK_INT >= 29) goto L903;
    L907:
        LongSparseArray r1210 = r782;
    L908:
        FileLog.d("showExtraNotifications: holders[" + r614 + "].call()");
        r430.call();
        if (unsupportedNotificationShortcut() == true) goto L913;
        if (r060.isEmpty() == true) goto L913;
        ShortcutManagerCompat.removeDynamicShortcuts(ApplicationLoader.applicationContext, r060);
    L913:
        r614 = r614 + 1;
        r1 = this;
        r4210 = r326;
        r782 = r1210;
        goto L899
    L903:
        if (DialogObject.isEncryptedDialog(r430.dialogId) == true) goto L907;
        NotificationCompat.Builder r510 = r430.notification;
        long r716 = r430.dialogId;
        r1210 = r782;
        String r141 = r1.createNotificationShortcut(r510, r716, r430.name, r430.user, r430.chat, (Person) r1210.get(r716), !r430.story);
        if (r141 == null) goto L908;
        r060.add(r141);
        goto L908
    L914:
        return;
    L884:
        if (BuildVars.LOGS_ENABLED == false) goto L886;
        FileLog.d("cancel summary with id " + r1.notificationId);
    L886:
        notificationManager.cancel(r1.notificationId);
        goto L887
    L871:
        if (BuildVars.LOGS_ENABLED == false) goto L874;
        FileLog.d("show summary with id " + r1.notificationId);
    L874:
        if (Build.VERSION.SDK_INT < 26) goto L876;
        NotificationCompat.Builder r157 = r84;
        r157.setChannelId(OTHER_NOTIFICATIONS_CHANNEL);
        r157.setGroupAlertBehavior(2);
        Notification r061 = r157.build();
    L930:
        notificationManager.notify(r1.notificationId, r061);     // Catch: SecurityException -> L879
        r4210 = r233;
    L879:
        e = move-exception;
        FileLog.e(e);
        r4210 = r233;
        r1.resetNotificationSound(r157, r86, r88, r90, r91, r92, r93, r94, r95, r96, r97, r98);
        goto L887
    L876:
        r157 = r84;
        r061 = r3112;
        goto L930
    L868:
        r3112 = r2;
        goto L55
    L49:
        r04 = false;
        goto L50
    L42:
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L45;
        r99 = false;
        goto L47
    L30:
        r13 = 2;
    L34:
        r122 = true;
        goto L36
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v38, types: [org.telegram.messenger.FileLoader] */
    /* JADX WARN: Type inference failed for: r0v70, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r0v71, types: [android.content.Intent] */
    /* JADX WARN: Type inference failed for: r10v20, types: [androidx.core.app.NotificationCompat$Builder] */
    /* JADX WARN: Type inference failed for: r14v27 */
    /* JADX WARN: Type inference failed for: r14v28 */
    /* JADX WARN: Type inference failed for: r14v29 */
    /* JADX WARN: Type inference failed for: r14v30 */
    /* JADX WARN: Type inference failed for: r14v36 */
    /* JADX WARN: Type inference failed for: r14v37 */
    /* JADX WARN: Type inference failed for: r14v38 */
    /* JADX WARN: Type inference failed for: r1v3, types: [org.telegram.messenger.BaseController, org.telegram.messenger.NotificationsController] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v11, types: [android.content.SharedPreferences] */
    /* JADX WARN: Type inference failed for: r4v57, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v82, types: [org.telegram.messenger.MessageObject] */
    /* JADX WARN: Type inference failed for: r7v15, types: [androidx.core.app.NotificationCompat$Builder] */
    /* JADX WARN: Type inference failed for: r7v16, types: [androidx.core.app.NotificationCompat$Builder] */
    /* JADX WARN: Type inference failed for: r7v8, types: [androidx.core.app.NotificationCompat$Builder] */
    /* JADX WARN: Type inference failed for: r8v12, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v36 */
    private void showOrUpdateNotification(boolean r64) {
        if (getUserConfig().isClientActivated() == true) goto L5;
    L486:
        dismissNotification();
        return;
    L5:
        if (this.pushMessages.isEmpty() == false) goto L9;
        if (this.storyPushMessages.isEmpty() == true) goto L486;
    L9:
        if (AccountNotifications.shouldShow(this.currentAccount) == false) goto L486;
        getConnectionsManager().resumeNetworkMaybe();     // Catch: Exception -> L483
        int r0 = 0;
        long r2 = 0;
        StoryNotification r4 = null;
    L13:
        if (r0 >= this.pushMessages.size()) goto L18;
        MessageObject r5 = this.pushMessages.get(r0);     // Catch: Exception -> L483
        int r6 = r5.messageOwner.date;     // Catch: Exception -> L483
        if (r2 >= r6) goto L17;
        r2 = r6;
        r4 = r5;
    L17:
        r0 = r0 + 1;     // Catch: Exception -> L483
        goto L13
    L18:
        int r02 = 0;
    L20:
        if (r02 >= this.storyPushMessages.size()) goto L25;
        StoryNotification r52 = this.storyPushMessages.get(r02);     // Catch: Exception -> L483
        long r11 = r52.date;     // Catch: Exception -> L483
        if (r2 >= (r11 / 1000)) goto L24;
        r4 = r52;
        r2 = r11 / 1000;     // Catch: Exception -> L483
    L24:
        r02 = r02 + 1;     // Catch: Exception -> L483
        goto L20
    L25:
        if (r4 == null) goto L511;
        boolean r03 = r4 instanceof StoryNotification;     // Catch: Exception -> L483
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r03 == false) goto L68;
        StoryNotification r42 = r4;     // Catch: Exception -> L483
        TLRPC.TL_message r04 = new TLRPC.TL_message();     // Catch: Exception -> L483
        r04.date = (int) (System.currentTimeMillis() / 1000);     // Catch: Exception -> L483
        int r22 = 0;
        boolean r3 = false;
        int r53 = 0;
    L32:
        if (r22 >= this.storyPushMessages.size()) goto L34;
        r3 = r3 | this.storyPushMessages.get(r22).hidden;     // Catch: Exception -> L483
        r04.date = Math.min(r04.date, (int) (this.storyPushMessages.get(r22).date / 1000));     // Catch: Exception -> L483
        r53 = r53 + this.storyPushMessages.get(r22).dateByIds.size();     // Catch: Exception -> L483
        r22 = r22 + 1;     // Catch: Exception -> L483
        r12 = r12;
        goto L32
    L34:
        String r31 = r12;
        TLRPC.TL_peerUser r23 = new TLRPC.TL_peerUser();     // Catch: Exception -> L483
        long r112 = r42.dialogId;     // Catch: Exception -> L483
        r23.user_id = r112;     // Catch: Exception -> L483
        r04.dialog_id = r112;     // Catch: Exception -> L483
        r04.peer_id = r23;     // Catch: Exception -> L483
        ArrayList<String> r24 = new ArrayList();     // Catch: Exception -> L483
        ArrayList<Object> r43 = new ArrayList();     // Catch: Exception -> L483
        parseStoryPushes(r24, r43);     // Catch: Exception -> L483
        if (SharedConfig.getDevicePerformanceClass() < 1) goto L37;
        Bitmap r44 = loadMultipleAvatars(r43);     // Catch: Exception -> L483
    L38:
        if (r3 == false) goto L40;
    L46:
        String r62 = LocaleController.formatPluralString("Stories", r53, new Object[0]);     // Catch: Exception -> L483
    L45:
        String r242 = r62;
        if (r3 == false) goto L51;
        r04.message = LocaleController.formatPluralString("StoryNotificationHidden", r53, new Object[0]);     // Catch: Exception -> L483
        String r113 = r31;
    L67:
        MessageObject r20 = new MessageObject(this.currentAccount, r04, r04.message, r242, r242, false, false, false, false);     // Catch: Exception -> L483
        r20.isStoryPush = true;     // Catch: Exception -> L483
        MessageObject r25 = r20;
        Bitmap r15 = r44;
    L69:
        SharedPreferences r32 = getAccountInstance().getNotificationsSettings();     // Catch: Exception -> L483
        int r05 = r32.getInt("dismissDate", 0);     // Catch: Exception -> L483
        if (r25.isStoryPush == false) goto L72;
    L75:
        long r202 = r25.getDialogId();     // Catch: Exception -> L483
        long r54 = MessageObject.getTopicId(this.currentAccount, r25.messageOwner, getMessagesController().isForum(r25));     // Catch: Exception -> L483
        boolean r7 = r25.isStoryPush;     // Catch: Exception -> L483
        if (r25.messageOwner.mentioned == false) goto L78;
        long r232 = r25.getFromChatId();     // Catch: Exception -> L483
    L79:
        r25.getId();     // Catch: Exception -> L483
        TLRPC.Peer r45 = r25.messageOwner.peer_id;     // Catch: Exception -> L483
        long r9 = r45.chat_id;     // Catch: Exception -> L483
        if (r9 == 0) goto L82;
    L81:
        Bitmap r28 = r15;
        long r14 = r45.user_id;     // Catch: Exception -> L483
        if (r25.isFromUser() == false) goto L91;
        if (r14 != 0) goto L88;
    L89:
        r14 = r25.messageOwner.from_id.user_id;     // Catch: Exception -> L483
        goto L91
    L88:
        if (r14 == getUserConfig().getClientUserId()) goto L89;
    L91:
        if (r25.getDialogId() == UserObject.VERIFY) goto L93;
    L98:
        TLRPC.User r122 = getMessagesController().getUser(Long.valueOf(r14));     // Catch: Exception -> L483
        if (r9 == 0) goto L112;
        TLRPC.Chat r46 = getMessagesController().getChat(Long.valueOf(r9));     // Catch: Exception -> L483
        if (r46 != null) goto L107;
        if (r25.isFcmMessage() == false) goto L107;
        boolean r13 = r25.localChannel;     // Catch: Exception -> L483
    L105:
        boolean r322 = r7;
        long r61 = r14;
        MessageObject r152 = r25;
        SharedPreferences r26 = r32;
        boolean r142 = r13;
        TLRPC.Chat r132 = r46;
        long r33 = r232;
        long r233 = r61;
    L113:
        int r72 = getNotifyOverride(r26, r33, r54);     // Catch: Exception -> L483
        long r35 = r33;
        if (r72 != (-1)) goto L116;
        Boolean r47 = Boolean.valueOf(r142);     // Catch: Exception -> L483
        boolean r55 = r152.isReactionPush;     // Catch: Exception -> L483
        NotificationsController r1 = this;
        SharedPreferences r73 = r26;
        long r27 = r202;
        boolean r56 = r1.isGlobalNotificationsEnabled(r27, r47, r55, r55);     // Catch: Exception -> L483
        SharedPreferences r48 = r73;
        ?? r16 = r1;
    L120:
        if (r9 == 0) goto L122;
        if (r132 != null) goto L122;
    L124:
        if (r152.isFcmMessage() == false) goto L127;
        String r63 = r152.localName;     // Catch: Exception -> L483
    L126:
        String r203 = r63;
        if (AndroidUtilities.needShowPasscode() == false) goto L133;
    L136:
        boolean r65 = true;
    L137:
        boolean r21 = r56;
        boolean r57 = "samsung".equalsIgnoreCase(Build.MANUFACTURER);     // Catch: Exception -> L483
        if (DialogObject.isEncryptedDialog(r27) == true) goto L147;
        if (r57 == true) goto L143;
        boolean r37 = r57;
        if (r16.pushDialogs.size() <= 1) goto L144;
    L148:
        if (r65 == false) goto L152;
        if (r9 == 0) goto L151;
        String r58 = LocaleController.getString(R.string.NotificationHiddenChatName);     // Catch: Exception -> L483
    L153:
        boolean r66 = false;
    L155:
        if (r152.isReactionPush == false) goto L157;
    L160:
        String r38 = r58;
        if (r48.getBoolean("EnableReactionsPreview", true) == true) goto L163;
        String r59 = LocaleController.getString(R.string.NotificationHiddenName);     // Catch: Exception -> L483
    L164:
        if (r37 == true) goto L181;
        boolean r382 = r66;
        if (UserConfig.getActivatedAccountsCount() > 1) goto L168;
        String r67 = r113;
    L172:
        String r39 = "currentAccount";
        if (r16.pushDialogs.size() != 1) goto L177;
        long r40 = r9;
    L175:
        TLRPC.User r422 = r122;
        TLRPC.Chat r432 = r132;
    L182:
        ?? r74 = new NotificationCompat.Builder(ApplicationLoader.applicationContext);     // Catch: Exception -> L483
        int r133 = 1;
        if (r16.pushMessages.size() <= 1) goto L187;
        if (r37 == false) goto L188;
        r133 = 1;
        goto L187
    L188:
        r74.setContentText(r67);     // Catch: Exception -> L483
        NotificationCompat.InboxStyle r8 = new NotificationCompat.InboxStyle();     // Catch: Exception -> L483
        r8.setBigContentTitle(r59);     // Catch: Exception -> L483
        boolean r442 = r142;
        int r134 = Math.min(10, r16.pushMessages.size());     // Catch: Exception -> L483
        SharedPreferences r452 = r48;
        boolean[] r49 = new boolean[1];     // Catch: Exception -> L483
        long r462 = r27;
        int r29 = 0;
        String r34 = null;
        ?? r143 = 2;
    L189:
        if (r29 >= r134) goto L213;
        String r372 = r34;
        MessageObject r36 = r16.pushMessages.get(r29);     // Catch: Exception -> L483
        int r482 = r29;
        int r492 = r134;
        MessageObject r50 = r152;
        String r153 = r16.getStringForMessage(r36, false, r49, null);     // Catch: Exception -> L483
        if (r153 != null) goto L193;
    L211:
        r34 = r372;
    L212:
        r29 = r482 + 1;     // Catch: Exception -> L483
        r134 = r492;
        r152 = r50;
        r143 = r143;
        goto L189
    L193:
        if (r36.isStoryPush == true) goto L198;
        if (r36.messageOwner.date <= r05) goto L211;
    L198:
        if (r143 != 2) goto L200;
        boolean r144 = r16.isSilentMessage(r36);     // Catch: Exception -> L483
        r34 = r153;
        r143 = r144;
    L202:
        if (r16.pushDialogs.size() != 1) goto L210;
        if (r382 == false) goto L210;
        if (r432 == null) goto L207;
        r153 = r153.replace(" @ " + r59, r113);     // Catch: Exception -> L483
        goto L210
    L207:
        if (r49[0] == false) goto L209;
        r153 = r153.replace(r59 + ": ", r113);     // Catch: Exception -> L483
        goto L210
    L209:
        r153 = r153.replace(r59 + " ", r113);     // Catch: Exception -> L483
    L210:
        r8.addLine(r153);     // Catch: Exception -> L483
        goto L212
    L200:
        r34 = r372;
        r143 = r143;
        goto L202
    L213:
        r8.setSummaryText(r67);     // Catch: Exception -> L483
        r74.setStyle(r8);     // Catch: Exception -> L483
        String r310 = r34;
        MessageObject r154 = r152;
        boolean r145 = r143;
    L214:
        String r252 = r67;
        boolean r410 = r145;
        if (r64 == false) goto L239;
        if (r21 == false) goto L239;
        if (MediaController.getInstance().isRecordingAudio() == true) goto L239;
        if (r410 == true) goto L239;
        boolean r06 = false;
    L240:
        if (r06 == false) goto L242;
    L262:
        MessageObject r502 = r154;
        SharedPreferences r10 = r452;
        long r82 = r462;
    L263:
        boolean r155 = r06;
        MessageObject r210 = r502;
    L261:
        long r463 = r82;
        boolean r83 = r210.isReactionPush;     // Catch: Exception -> L483
        boolean r92 = r210.isStoryReactionPush;     // Catch: Exception -> L483
        MessageObject r542 = r210;
        String r51 = r310;
        String r562 = r59;
        ?? r211 = r10;
        String r60 = r113;
        long r123 = r233;
        String r532 = r39;
        long r102 = r40;
        TLRPC.Chat r07 = r432;
        DialogNotificationSettings r75 = r16.computeDialogNotificationSettings(r211, r463, r54, r322, r83, r92, r102, r123, r442, r155);     // Catch: Exception -> L483
        boolean r156 = r75.notifyDisabled;     // Catch: Exception -> L483
        int r84 = r75.vibrate;     // Catch: Exception -> L483
        String r93 = r75.soundPath;     // Catch: Exception -> L483
        boolean r146 = r75.isInternalSoundFile;     // Catch: Exception -> L483
        int r510 = r75.ledColor;     // Catch: Exception -> L483
        int r68 = r75.importance;     // Catch: Exception -> L483
        String r147 = Settings.System.DEFAULT_NOTIFICATION_URI.getPath();     // Catch: Exception -> L483
        boolean r157 = r75.isDefault;     // Catch: Exception -> L483
        boolean r158 = r75.isInApp;     // Catch: Exception -> L483
        int r76 = r75.chatType;     // Catch: Exception -> L483
        long r464 = r463;
        Intent r77 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);     // Catch: Exception -> L483
        r77.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);     // Catch: Exception -> L483
        r77.setFlags(67108864);     // Catch: Exception -> L483
        MessageObject r311 = r542;
        if (r311.isOauthPush == false) goto L269;
        r77.putExtra("oauth_url", r311.localName);     // Catch: Exception -> L483
    L269:
        if (r311.isStoryReactionPush == false) goto L273;
        r77.putExtra("storyId", Math.abs(r311.getId()));     // Catch: Exception -> L483
        int r272 = r84;
        int r282 = r510;
    L271:
        TLRPC.User r411 = r422;
        boolean r85 = true;
    L328:
        TLRPC.FileLocation r511 = null;
        ?? r86 = r85;
    L334:
        r77.putExtra(r532, r16.currentAccount);     // Catch: Exception -> L483
        r74.setContentTitle(r562).setSmallIcon(IconManager.getNotificationIcon()).setAutoCancel(r86).setNumber(r16.total_unread_count).setContentIntent(PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, r77, 1140850688)).setGroup(r16.notificationGroup).setGroupSummary(r86).setShowWhen(r86).setWhen(r311.messageOwner.date * 1000);     // Catch: Exception -> L483
        if (Build.VERSION.SDK_INT >= 31) goto L337;
        r74.setColor(r16.getNotificationColor());     // Catch: Exception -> L483
    L337:
        r74.setCategory("msg");     // Catch: Exception -> L483
        if (r07 != null) goto L494;
        if (r411 == null) goto L494;
        String r08 = r411.phone;     // Catch: Exception -> L483
        if (r08 == null) goto L494;
        if (r08.length() <= 0) goto L494;
        r74.addPerson("tel:+" + r411.phone);     // Catch: Exception -> L483
    L494:
        ?? r09 = new Intent(ApplicationLoader.applicationContext, NotificationDismissReceiver.class);     // Catch: Throwable -> L348
        r09.putExtra("messageDate", r311.messageOwner.date);     // Catch: Throwable -> L348
        r09.putExtra(r532, r16.currentAccount);     // Catch: Throwable -> L348
        if (r311.isStoryPush == false) goto L351;
        r09.putExtra("story", r86);     // Catch: Throwable -> L348
    L351:
        if (r311.isStoryReactionPush == false) goto L353;
        r09.putExtra("storyReaction", r86);     // Catch: Throwable -> L348
    L353:
        r74.setDeleteIntent(PendingIntent.getBroadcast(ApplicationLoader.applicationContext, r86, r09, 167772160));     // Catch: Throwable -> L348
    L356:
        if (r28 == null) goto L359;
        r74.setLargeIcon(r28);     // Catch: Exception -> L483
    L374:
        if (r64 == false) goto L376;
        if (r410 == r86) goto L376;
        if (r68 == 0) goto L379;
        int r512 = 2;
        if (r68 == r86) goto L395;
        if (r68 == 2) goto L395;
        if (r68 != 4) goto L391;
        r74.setPriority(-2);     // Catch: Exception -> L483
        if (Build.VERSION.SDK_INT < 26) goto L401;
        int r69 = r86;
    L402:
        if (r410 == r86) goto L459;
        if (r156 == true) goto L459;
        if (r158 == true) goto L406;
        String r212 = r51;
    L412:
        if (r212.length() <= 100) goto L414;
        r212 = r212.substring(0, 100).replace('\n', ' ').trim() + "...";     // Catch: Exception -> L483
    L414:
        r74.setTicker(r212);     // Catch: Exception -> L483
    L415:
        if (r93 != null) goto L417;
    L441:
        Uri r94 = null;
    L442:
        if (r282 == 0) goto L445;
        int r159 = r282;
        r74.setLights(r159, 1000, 1000);     // Catch: Exception -> L483
    L444:
        int r213 = r272;
        if (r213 != r512) goto L449;
        long[] r010 = new long[r512];     // Catch: Exception -> L483
        r010 = {x0b76: FILL_ARRAY_DATA , data: [0, 0} // fill-array;     // Catch: Exception -> L483
        r74.setVibrate(r010);     // Catch: Exception -> L483
    L448:
        Uri r522 = r94;
        long[] r95 = r010;
    L461:
        if (AndroidUtilities.needShowPasscode() == false) goto L463;
    L481:
        r16.showExtraNotifications(r74, r252, r464, r54, r203, r95, r159, r522, r69, r157, r158, r156, r76);     // Catch: Exception -> L483
        scheduleNotificationRepeat();     // Catch: Exception -> L483
        return;
    L463:
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L481;
        if (r311.getDialogId() != 777000) goto L481;
        TLRPC.ReplyMarkup r011 = r311.messageOwner.reply_markup;     // Catch: Exception -> L483
        if ((r011 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L481;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r012 = ((TLRPC.TL_replyInlineMarkup) r011).rows;     // Catch: Exception -> L483
        int r214 = r012.size();     // Catch: Exception -> L483
        int r103 = 0;
    L469:
        if (r103 >= r214) goto L481;
        TL_keyboard.KeyboardInlineButtonRow r412 = r012.get(r103);     // Catch: Exception -> L483
        int r513 = r412.buttons.size();     // Catch: Exception -> L483
        int r78 = 0;
    L471:
        if (r78 >= r513) goto L480;
        TL_keyboard.KeyboardInlineButton r87 = r412.buttons.get(r78);     // Catch: Exception -> L483
        TL_keyboard.TL_inlineButtonTypeCallback r135 = (TL_keyboard.TL_inlineButtonTypeCallback) TLKeyboardHelper.getType(r87, TL_keyboard.TL_inlineButtonTypeCallback.class);     // Catch: Exception -> L483
        if (r135 == null) goto L478;
        ArrayList<TL_keyboard.KeyboardInlineButtonRow> r162 = r012;
        int r642 = r214;
        Intent r148 = new Intent(ApplicationLoader.applicationContext, NotificationCallbackReceiver.class);     // Catch: Exception -> L483
        r148.putExtra(r532, r16.currentAccount);     // Catch: Exception -> L483
        MessageObject r503 = r311;
        long r215 = r464;
        r148.putExtra("did", r215);     // Catch: Exception -> L483
        byte[] r013 = r135.data;     // Catch: Exception -> L483
        if (r013 == null) goto L477;
        r148.putExtra("data", r013);     // Catch: Exception -> L483
    L477:
        r148.putExtra("mid", r503.getId());     // Catch: Exception -> L483
        String r014 = r87.text;     // Catch: Exception -> L483
        Context r88 = ApplicationLoader.applicationContext;     // Catch: Exception -> L483
        int r136 = r16.lastButtonId;     // Catch: Exception -> L483
        r464 = r215;
        r16.lastButtonId = r136 + 1;     // Catch: Exception -> L483
        r74.addAction(0, r014, PendingIntent.getBroadcast(r88, r136, r148, 201326592));     // Catch: Exception -> L483
    L479:
        r78 = r78 + 1;     // Catch: Exception -> L483
        r214 = r642;
        r012 = r162;
        r311 = r503;
        goto L471
    L478:
        r162 = r012;
        r642 = r214;
        r503 = r311;
        goto L479
    L480:
        r103 = r103 + 1;     // Catch: Exception -> L483
        goto L469
    L449:
        if (r213 != r86) goto L451;
        r010 = new long[]{0, 100, 0, 100};     // Catch: Exception -> L483
        r74.setVibrate(r010);     // Catch: Exception -> L483
        goto L448
    L451:
        if (r213 == 0) goto L458;
        if (r213 == 4) goto L458;
        if (r213 != 3) goto L457;
        r010 = new long[r512];     // Catch: Exception -> L483
        r010 = {x0b96: FILL_ARRAY_DATA , data: [0, 1000} // fill-array;     // Catch: Exception -> L483
        r74.setVibrate(r010);     // Catch: Exception -> L483
        goto L448
    L457:
        r522 = r94;
        r95 = null;
    L458:
        r74.setDefaults(r512);     // Catch: Exception -> L483
        r010 = new long[0];     // Catch: Exception -> L483
        goto L448
    L445:
        r159 = r282;
        goto L444
    L417:
        if (r93.equalsIgnoreCase("NoSound") == true) goto L441;
        if (Build.VERSION.SDK_INT < 26) goto L431;
        if (r93.equalsIgnoreCase("Default") == false) goto L424;
    L429:
        r94 = Settings.System.DEFAULT_NOTIFICATION_URI;     // Catch: Exception -> L483
        goto L442
    L424:
        if (r93.equals(r147) == true) goto L429;
        if (r146 == false) goto L428;
        r94 = FileProvider.getUriForFile(ApplicationLoader.applicationContext, ApplicationLoader.getApplicationId() + ".provider", new File(r93));     // Catch: Exception -> L483
        ApplicationLoader.applicationContext.grantUriPermission("com.android.systemui", r94, r86);     // Catch: Exception -> L483
        goto L442
    L428:
        r94 = Uri.parse(r93);     // Catch: Exception -> L483
        goto L442
    L431:
        if (r93.equals(r147) == false) goto L434;
        r74.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, 5);     // Catch: Exception -> L483
        goto L441
    L434:
        if (r93.startsWith("file://") == true) goto L436;
    L440:
        r74.setSound(Uri.parse(r93), 5);     // Catch: Exception -> L483
        goto L441
    L436:
        if (AndroidUtilities.isInternalUri(Uri.parse(r93)) == true) goto L440;
        Uri r216 = FileProvider.getUriForFile(ApplicationLoader.applicationContext, ApplicationLoader.getApplicationId() + ".provider", new File(r93.replace("file://", r60)));     // Catch: Exception -> L439
        ApplicationLoader.applicationContext.grantUriPermission("com.android.systemui", r216, r86);     // Catch: Exception -> L439
        r74.setSound(r216, 5);     // Catch: Exception -> L439
    L439:
        r74.setSound(Uri.parse(r93), 5);     // Catch: Exception -> L483
        goto L441
    L406:
        if (r211.getBoolean("EnableInAppPreview", r86) == false) goto L415;
        r212 = r51;
        if (r212 == null) goto L415;
    L459:
        r159 = r282;
        r95 = new long[r512];     // Catch: Exception -> L483
        r95 = {x0ba2: FILL_ARRAY_DATA , data: [0, 0} // fill-array;     // Catch: Exception -> L483
        r74.setVibrate(r95);     // Catch: Exception -> L483
        r522 = null;
    L401:
        r69 = 0;
        goto L402
    L391:
        if (r68 != 5) goto L401;
        r74.setPriority(-1);     // Catch: Exception -> L483
        if (Build.VERSION.SDK_INT < 26) goto L401;
    L394:
        r69 = r512;
    L395:
        r74.setPriority(r86);     // Catch: Exception -> L483
        if (Build.VERSION.SDK_INT < 26) goto L401;
        r69 = 4;
        goto L402
    L379:
        r74.setPriority(0);     // Catch: Exception -> L483
        if (Build.VERSION.SDK_INT < 26) goto L382;
        r512 = 2;
        r69 = 3;
        goto L402
    L382:
        r512 = 2;
    L376:
        r512 = 2;
        r74.setPriority(-1);     // Catch: Exception -> L483
        if (Build.VERSION.SDK_INT < 26) goto L401;
    L359:
        if (r511 == null) goto L374;
        BitmapDrawable r015 = ImageLoader.getInstance().getImageFromMemory(r511, null, "50_50");     // Catch: Exception -> L483
        if (r015 == null) goto L489;
        r74.setLargeIcon(r015.getBitmap());     // Catch: Exception -> L483
        goto L374
    L489:
        File r016 = r16.getFileLoader().getPathToAttach(r511, r86);     // Catch: Throwable -> L488
        if (r016.exists() == false) goto L374;
        float r514 = 160.0f / AndroidUtilities.dp(50.0f);     // Catch: Throwable -> L488
        BitmapFactory.Options r413 = new BitmapFactory.Options();     // Catch: Throwable -> L488
        if (r514 >= 1.0f) goto L369;
        int r149 = r86;
    L370:
        r413.inSampleSize = r149;     // Catch: Throwable -> L488
        Bitmap r017 = BitmapFactory.decodeFile(r016.getAbsolutePath(), r413);     // Catch: Throwable -> L488
        if (r017 == null) goto L374;
        r74.setLargeIcon(r017);     // Catch: Throwable -> L488
        goto L374
    L369:
        r149 = (int) r514;     // Catch: Throwable -> L488
    L348:
        th = move-exception;
        FileLog.e(th);     // Catch: Exception -> L483
        goto L356
    L273:
        r282 = r510;
        if (r311.isLiveStoryPush == false) goto L283;
        if (r9 == 0) goto L278;
        r77.putExtra("chatId", r102);     // Catch: Exception -> L483
    L280:
        r77.putExtra("storyId", Math.abs(r311.getId()));     // Catch: Exception -> L483
    L281:
        r272 = r84;
        goto L271
    L278:
        if (r123 == 0) goto L280;
        r77.putExtra("userId", r123);     // Catch: Exception -> L483
        goto L280
    L283:
        if (r311.isStoryPush == false) goto L289;
        long[] r414 = new long[r16.storyPushMessages.size()];     // Catch: Exception -> L483
        int r104 = 0;
    L286:
        if (r104 >= r16.storyPushMessages.size()) goto L288;
        r414[r104] = r16.storyPushMessages.get(r104).dialogId;     // Catch: Exception -> L483
        r104 = r104 + 1;     // Catch: Exception -> L483
        goto L286
    L288:
        r77.putExtra("storyDialogIds", r414);     // Catch: Exception -> L483
        goto L281
    L289:
        boolean r515 = DialogObject.isEncryptedDialog(r464);     // Catch: Exception -> L483
        LongSparseArray<Integer> r516 = r16.pushDialogs;
        if (r515 == true) goto L329;
        r272 = r84;
        r85 = true;
        r86 = 1;
        r86 = 1;
        r85 = true;
        r85 = true;
        r85 = true;
        r85 = true;
        if (r516.size() != 1) goto L300;
        if (r9 == 0) goto L297;
        r77.putExtra("chatId", r102);     // Catch: Exception -> L483
        goto L300
    L297:
        if (r123 == 0) goto L300;
        r77.putExtra("userId", r123);     // Catch: Exception -> L483
    L300:
        if (AndroidUtilities.needShowPasscode() == false) goto L302;
    L303:
        r411 = r422;
        goto L328
    L302:
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L303;
        if (r16.pushDialogs.size() != 1) goto L303;
        if (Build.VERSION.SDK_INT >= 28) goto L303;
        if (r07 == null) goto L318;
        TLRPC.ChatPhoto r415 = r07.photo;     // Catch: Exception -> L483
        if (r415 == null) goto L303;
        TLRPC.FileLocation r416 = r415.photo_small;     // Catch: Exception -> L483
        if (r416 == null) goto L303;
        if (r416.volume_id == 0) goto L303;
        if (r416.local_id == 0) goto L303;
        r511 = r416;
        r411 = r422;
        goto L334
    L318:
        if (r422 == null) goto L303;
        r411 = r422;
        TLRPC.UserProfilePhoto r517 = r411.photo;     // Catch: Exception -> L483
        if (r517 == null) goto L328;
        r511 = r517.photo_small;     // Catch: Exception -> L483
        if (r511 == null) goto L328;
        if (r511.volume_id == 0) goto L328;
        if (r511.local_id == 0) goto L328;
    L329:
        r272 = r84;
        r411 = r422;
        r85 = true;
        r85 = true;
        r85 = true;
        if (r516.size() != 1) goto L328;
        if (r464 == globalSecretChatId) goto L328;
        r77.putExtra("encId", DialogObject.getEncryptedChatId(r464));     // Catch: Exception -> L483
        goto L328
    L242:
        if (r462 != r35) goto L262;
        if (r432 == null) goto L262;
        StringBuilder r610 = new StringBuilder();     // Catch: Exception -> L483
        r610.append(NotificationsSettingsFacade.PROPERTY_CUSTOM);     // Catch: Exception -> L483
        r82 = r462;
        r610.append(r82);     // Catch: Exception -> L483
        r10 = r452;
        int r124 = 180;
        if (r10.getBoolean(r610.toString(), false) == false) goto L247;
        int r611 = r10.getInt("smart_max_count_" + r82, 2);     // Catch: Exception -> L483
        r124 = r10.getInt("smart_delay_" + r82, 180);     // Catch: Exception -> L483
    L248:
        if (r611 == 0) goto L252;
        Point r217 = r16.smartNotificationsDialogs.get(r82);     // Catch: Exception -> L483
        if (r217 != null) goto L255;
        r16.smartNotificationsDialogs.put(r82, new Point(1, (int) (SystemClock.elapsedRealtime() / 1000)));     // Catch: Exception -> L483
        goto L252
    L255:
        if ((r217.y + r124) >= (SystemClock.elapsedRealtime() / 1000)) goto L257;
        r217.set(1, (int) (SystemClock.elapsedRealtime() / 1000));     // Catch: Exception -> L483
        goto L252
    L257:
        int r125 = r217.x;     // Catch: Exception -> L483
        if (r125 >= r611) goto L260;
        r502 = r154;
        r217.set(r125 + 1, (int) (SystemClock.elapsedRealtime() / 1000));     // Catch: Exception -> L483
        goto L263
    L260:
        r210 = r154;
        r155 = true;
    L252:
        r502 = r154;
        goto L263
    L247:
        r611 = 2;
    L239:
        r06 = true;
    L187:
        r462 = r27;
        r452 = r48;
        r442 = r142;
        boolean[] r018 = new boolean[r133];     // Catch: Exception -> L483
        r154 = r152;
        r310 = r16.getStringForMessage(r154, false, r018, null);     // Catch: Exception -> L483
        boolean r1410 = r16.isSilentMessage(r154);     // Catch: Exception -> L483
        if (r310 == null) goto L512;
        if (r382 == false) goto L226;
        if (r432 == null) goto L223;
        if (r37 == true) goto L223;
        String r019 = r310.replace(" @ " + r59, r113);     // Catch: Exception -> L483
    L227:
        r74.setContentText(r019);     // Catch: Exception -> L483
        if (r37 == false) goto L230;
        r67 = r019;
    L230:
        r74.setStyle(new NotificationCompat.BigTextStyle().bigText(r019));     // Catch: Exception -> L483
        r145 = r1410;
    L223:
        if (r018[0] == false) goto L225;
        r019 = r310.replace(r59 + ": ", r113);     // Catch: Exception -> L483
        goto L227
    L225:
        r019 = r310.replace(r59 + " ", r113);     // Catch: Exception -> L483
        goto L227
    L226:
        r019 = r310;
        goto L227
    L512:
        return;
    L177:
        r40 = r9;
        if (r16.pushDialogs.size() != 1) goto L180;
        r67 = r67 + LocaleController.formatPluralString("NewMessages", r16.total_unread_count, new Object[0]);     // Catch: Exception -> L483
        goto L175
    L180:
        StringBuilder r79 = new StringBuilder();     // Catch: Exception -> L483
        r79.append(r67);     // Catch: Exception -> L483
        r422 = r122;
        r432 = r132;
        r79.append(LocaleController.formatString(R.string.NotificationMessagesPeopleDisplayOrder, new Object[]{LocaleController.formatPluralString("NewMessages", r16.total_unread_count, new Object[0]), LocaleController.formatPluralString("FromChats", r16.pushDialogs.size(), new Object[0])}));     // Catch: Exception -> L483
        r67 = r79.toString();     // Catch: Exception -> L483
        goto L182
    L168:
        if (r16.pushDialogs.size() != 1) goto L170;
        r67 = UserObject.getFirstName(r16.getUserConfig().getCurrentUser());     // Catch: Exception -> L483
        goto L172
    L170:
        r67 = UserObject.getFirstName(r16.getUserConfig().getCurrentUser()) + "・";     // Catch: Exception -> L483
        goto L172
    L181:
        r382 = r66;
        r39 = "currentAccount";
        r40 = r9;
        r422 = r122;
        r432 = r132;
        r67 = r113;
    L163:
        r59 = r38;
        goto L164
    L157:
        if (r152.isStoryReactionPush == true) goto L160;
        r38 = r58;
        goto L163
    L151:
        r58 = LocaleController.getString(R.string.NotificationHiddenName);     // Catch: Exception -> L483
        goto L153
    L152:
        r58 = LocaleController.getString(R.string.AppName);     // Catch: Exception -> L483
    L144:
        if (r65 == true) goto L148;
        r58 = r203;
        r66 = true;
        goto L155
    L143:
        r37 = r57;
        goto L144
    L147:
        r37 = r57;
        goto L148
    L133:
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L136;
        r65 = false;
    L127:
        if (r132 == null) goto L129;
        r63 = r16.getTitle(r132);     // Catch: Exception -> L483
        goto L126
    L129:
        r63 = UserObject.getUserName(r122);     // Catch: Exception -> L483
    L122:
        if (r122 != null) goto L127;
    L116:
        NotificationsController r17 = this;
        r48 = r26;
        r27 = r202;
        if (r72 == 2) goto L119;
        r56 = true;
        r16 = r17;
        goto L120
    L119:
        r56 = false;
        r16 = r17;
    L107:
        if (ChatObject.isChannel(r46) == true) goto L109;
    L111:
        r13 = false;
        goto L105
    L109:
        if (r46.megagroup == true) goto L111;
        r13 = true;
        goto L105
    L112:
        r152 = r25;
        r26 = r32;
        r33 = r232;
        r233 = r14;
        r322 = r7;
        r132 = null;
        r142 = false;
        goto L113
    L93:
        if (r25.getForwardedFromId() == null) goto L98;
        if (r25.getForwardedFromId().longValue() < 0) goto L97;
        r14 = r25.getForwardedFromId().longValue();     // Catch: Exception -> L483
        r9 = 0;
        goto L98
    L97:
        r9 = r25.getForwardedFromId().longValue();     // Catch: Exception -> L483
        r14 = 0;
        goto L98
    L82:
        r9 = r45.channel_id;     // Catch: Exception -> L483
        goto L81
    L78:
        r232 = r202;
        goto L79
    L72:
        if (r25.messageOwner.date > r05) goto L75;
        dismissNotification();     // Catch: Exception -> L483
        return;
    L51:
        if (r24.isEmpty() == false) goto L53;
        r113 = r31;
        r04.message = r113;     // Catch: Exception -> L483
        goto L67
    L53:
        r113 = r31;
        if (r24.size() != 1) goto L59;
        if (r53 != 1) goto L57;
        r04.message = LocaleController.getString("StoryNotificationSingle");     // Catch: Exception -> L483
        goto L67
    L57:
        r04.message = LocaleController.formatPluralString("StoryNotification1", r53, new Object[]{r24.get(0)});     // Catch: Exception -> L483
        goto L67
    L59:
        if (r24.size() != 2) goto L62;
        r04.message = LocaleController.formatString(R.string.StoryNotification2, new Object[]{r24.get(0), r24.get(1)});     // Catch: Exception -> L483
        goto L67
    L62:
        if (r24.size() == 3) goto L64;
    L66:
        r04.message = LocaleController.formatPluralString("StoryNotification4", this.storyPushMessages.size() - 2, new Object[]{cutLastName(r24.get(0)), cutLastName(r24.get(1))});     // Catch: Exception -> L483
        goto L67
    L64:
        if (this.storyPushMessages.size() != 3) goto L66;
        r04.message = LocaleController.formatString(R.string.StoryNotification3, new Object[]{cutLastName(r24.get(0)), cutLastName(r24.get(1)), cutLastName(r24.get(2))});     // Catch: Exception -> L483
        goto L67
    L40:
        if (this.storyPushMessages.size() >= 2) goto L46;
        if (r24.isEmpty() == true) goto L46;
        r62 = r24.get(0);     // Catch: Exception -> L483
        goto L45
    L37:
        r44 = null;
        goto L38
    L68:
        r113 = _UrlKt.FRAGMENT_ENCODE_SET;
        r25 = this.pushMessages.get(0);     // Catch: Exception -> L483
        r15 = null;
        goto L69
    L511:
        return;
    L483:
        e = move-exception;
        FileLog.e(e);
    }

    private boolean unsupportedNotificationShortcut() {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        return true;
    L5:
        if (SharedConfig.chatBubbles == false) goto L11;
        return false;
    L11:
        return true;
    }

    private void updateStoryPushesRunnable() {
        int r2 = 0;
        long r3 = Long.MAX_VALUE;
    L4:
        if (r2 >= this.storyPushMessages.size()) goto L10;
        Iterator<Pair<Long, Long>> r5 = this.storyPushMessages.get(r2).dateByIds.values().iterator();
    L7:
        if (r5.hasNext() == false) goto L9;
        r3 = Math.min(r3, ((Long) r5.next().second).longValue());
        goto L7
    L9:
        r2 = r2 + 1;
        goto L4
    L10:
        DispatchQueue r22 = notificationsQueue;
        r22.cancelRunnable(this.checkStoryPushesRunnable);
        long r52 = r3 - System.currentTimeMillis();
        if (r3 == LongCompanionObject.MAX_VALUE) goto L16;
        r22.postRunnable(this.checkStoryPushesRunnable, Math.max(0, r52));
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @TargetApi(26)
    private String validateChannelId(long r30, long r32, String r34, long[] r35, int r36, Uri r37, int r38, boolean r39, boolean r40, boolean r41, int r42) {
        ensureGroupsCreated();
        SharedPreferences r9 = getAccountInstance().getNotificationsSettings();
        String r6 = "stories";
        String r10 = "groups";
        String r11 = "channels";
        if (r41 == false) goto L5;
        String r14 = "other" + this.currentAccount;
        String r16 = "reactions";
        String r15 = null;
    L20:
        if (r39 == false) goto L22;
    L24:
        boolean r18 = false;
    L25:
        if (r40 == true) goto L30;
        if (r15 == null) goto L30;
        if (r9.getBoolean(r15, false) == false) goto L30;
        boolean r152 = true;
    L31:
        if (r37 != null) goto L34;
        String r19 = "NoSound2";
    L33:
        boolean r20 = true;
        String r4 = Utilities.MD5(r19);
        if (r4 == null) goto L41;
        if (r4.length() <= 5) goto L41;
        r4 = r4.substring(0, 5);
    L41:
        if (r41 == false) goto L43;
        r10 = "silent";
        int r21 = 0;
        String r13 = LocaleController.getString(R.string.NotificationsSilent);
    L81:
        String r102 = r10 + "_" + r4;
        String r112 = r9.getString(r102, null);
        String r62 = r9.getString(r102.concat("_s"), null);
        StringBuilder r43 = new StringBuilder();
        if (r112 == null) goto L262;
        SharedPreferences r162 = r9;
        NotificationChannel r92 = systemNotificationManager.getNotificationChannel(r112);
        String r33 = r13;
        if (BuildVars.LOGS_ENABLED == false) goto L86;
        boolean r17 = r152;
        FileLog.d("current channel for " + r112 + " = " + r92);
    L87:
        if (r92 == null) goto L261;
        if (r41 == true) goto L259;
        if (r17 == true) goto L259;
        int r7 = r92.getImportance();
        Uri r153 = r92.getSound();
        long[] r22 = r92.getVibrationPattern();
        boolean r93 = r92.shouldVibrate();
        String r23 = r14;
        if (r93 == true) goto L94;
        if (r22 != null) goto L94;
        long r25 = 0;
        long[] r154 = new long[2];
        r154[r21] = 0;
        r154[1] = 0;
    L95:
        int r142 = r92.getLightColor();
        String r342 = r102;
        String r222 = "_";
        if (r154 == null) goto L101;
        int r12 = r21;
    L98:
        boolean r27 = r93;
        if (r12 >= r154.length) goto L102;
        r43.append(r154[r12]);
        r12 = r12 + 1;
        r93 = r27;
    L102:
        r43.append(r142);
        if (r153 == null) goto L105;
        r43.append(r153.toString());
    L105:
        r43.append(r7);
        if (r39 == true) goto L110;
        if (r18 == false) goto L110;
        r43.append("secret");
    L110:
        if (BuildVars.LOGS_ENABLED == false) goto L112;
        FileLog.d("current channel settings for " + r112 + " = " + r43 + " old = " + r62);
    L112:
        String r94 = Utilities.MD5(r43.toString());
        r43.setLength(r21);
        if (r40 == false) goto L117;
        if (r38 == r7) goto L117;
        long[] r212 = r35;
        int r103 = r36;
        String r28 = "secret";
        String r24 = r62;
        boolean r5 = false;
        boolean r72 = false;
        StringBuilder r63 = r43;
        boolean r44 = true;
        String r202 = r112;
        String r113 = "_s";
        String r322 = r94;
    L258:
        boolean r155 = r44;
        boolean r122 = r72;
        String r73 = r202;
        long[] r95 = r212;
        String r0 = r24;
        String r45 = r322;
        boolean r52 = r5;
    L264:
        if (r122 == false) goto L269;
        if (r45 == null) goto L269;
        String r123 = r342;
        r162.edit().putString(r123, r73).putString(r123.concat(r113), r45).apply();
        if (BuildVars.LOGS_ENABLED == false) goto L275;
        FileLog.d("change edited channel " + r73);
    L275:
        String r02 = r45;
        String r46 = r73;
    L301:
        if (r46 != null) goto L329;
        int r47 = this.currentAccount;
        if (r39 == false) goto L306;
        String r2 = r47 + "channel_" + r123 + r222 + Utilities.random.nextLong();
    L305:
        r46 = r2;
        CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1.m();
        if (r18 == false) goto L310;
        String r132 = LocaleController.getString(R.string.SecretChatName);
    L311:
        NotificationChannel r26 = CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m(r46, r132, r38);
        r26.setGroup(r23);
        if (r103 == 0) goto L314;
        boolean r156 = true;
        r26.enableLights(true);
        r26.setLightColor(r103);
    L316:
        if (isEmptyVibration(r95) == true) goto L320;
        r26.enableVibration(r156);
        if (r95.length <= 0) goto L321;
        r26.setVibrationPattern(r95);
    L321:
        AudioAttributes.Builder r3 = new AudioAttributes.Builder();
        r3.setContentType(4);
        r3.setUsage(5);
        if (r37 == null) goto L324;
        r26.setSound(r37, r3.build());
    L326:
        if (BuildVars.LOGS_ENABLED == false) goto L328;
        FileLog.d("create new channel ".concat(r46));
    L328:
        this.lastNotificationChannelCreateTime = SystemClock.elapsedRealtime();
        systemNotificationManager.createNotificationChannel(r26);
        r162.edit().putString(r123, r46).putString(r123.concat(r113), r02).apply();
        goto L329
    L324:
        r26.setSound(null, r3.build());
        goto L326
    L320:
        r26.enableVibration(r52);
        goto L321
    L314:
        r156 = true;
        r26.enableLights(r52);
        goto L316
    L310:
        r132 = r33;
        goto L311
    L306:
        r2 = r47 + "channel_" + r30 + r222 + Utilities.random.nextLong();
    L329:
        return r46;
    L269:
        r123 = r342;
        if (r155 == true) goto L276;
        if (r45 == null) goto L276;
        if (r40 == false) goto L276;
        if (r39 == true) goto L275;
    L276:
        int r48 = r52;
    L278:
        if (r48 >= r95.length) goto L280;
        r63.append(r95[r48]);
        r48 = r48 + 1;
        goto L278
    L280:
        r63.append(r103);
        if (r37 == null) goto L283;
        r63.append(r37.toString());
    L283:
        r63.append(r38);
        if (r39 == true) goto L287;
        if (r18 == false) goto L287;
        r63.append(r28);
    L287:
        r45 = Utilities.MD5(r63.toString());
        if (r41 == true) goto L275;
        if (r73 == null) goto L275;
        if (r155 == false) goto L292;
    L330:
        systemNotificationManager.deleteNotificationChannel(r73);     // Catch: Exception -> L295
    L298:
        if (BuildVars.LOGS_ENABLED == false) goto L300;
        FileLog.d("delete channel by settings change ".concat(r73));
    L300:
        r02 = r45;
        r46 = null;
    L295:
        e = move-exception;
        FileLog.e(e);
        goto L298
    L292:
        if (r0.equals(r45) == true) goto L275;
    L117:
        if (r94.equals(r62) == true) goto L257;
        r28 = "secret";
        if (r7 != 0) goto L140;
        SharedPreferences.Editor r74 = r162.edit();
        if (r39 == false) goto L134;
        if (r40 == false) goto L124;
        String r124 = "_s";
    L131:
        StringBuilder r104 = r43;
        boolean r213 = false;
        r24 = r62;
    L139:
        SharedPreferences.Editor r49 = r74;
        r322 = r94;
        r63 = r104;
        r72 = r20;
        boolean r53 = r213;
        long[] r96 = r35;
        r202 = r112;
        r113 = r124;
        r212 = r154;
    L188:
        boolean r125 = r27;
        if ((!isEmptyVibration(r96)) == r125) goto L231;
        if (r40 == true) goto L229;
        if (r49 != null) goto L193;
        r49 = r162.edit();
    L193:
        if (r39 == true) goto L195;
        String r75 = "vibrate_" + r30;
        if (r125 == false) goto L227;
        int r97 = r53 ? 1 : 0;
    L228:
        r49.putInt(r75, r97);
        goto L229
    L227:
        r97 = 2;
        goto L228
    L195:
        if (r42 != 2) goto L200;
        if (r125 == false) goto L198;
        int r76 = r53 ? 1 : 0;
    L199:
        r49.putInt("vibrate_channel", r76);
        goto L229
    L198:
        r76 = 2;
        goto L199
    L200:
        if (r42 != 0) goto L206;
        if (r125 == false) goto L203;
        int r77 = r53 ? 1 : 0;
    L204:
        r49.putInt("vibrate_group", r77);
        goto L229
    L203:
        r77 = 2;
        goto L204
    L206:
        if (r42 != 3) goto L212;
        if (r125 == false) goto L209;
        int r78 = r53 ? 1 : 0;
    L210:
        r49.putInt("vibrate_stories", r78);
        goto L229
    L209:
        r78 = 2;
        goto L210
    L212:
        if (r42 != 4) goto L214;
    L220:
        if (r125 == false) goto L222;
        int r79 = r53 ? 1 : 0;
    L223:
        r49.putInt("vibrate_react", r79);
        goto L229
    L222:
        r79 = 2;
        goto L223
    L214:
        if (r42 == 5) goto L220;
        if (r125 == false) goto L218;
        int r710 = r53 ? 1 : 0;
    L219:
        r49.putInt("vibrate_messages", r710);
        goto L229
    L218:
        r710 = 2;
    L229:
        r72 = true;
    L230:
        r103 = r36;
        if (r142 == r103) goto L254;
        if (r40 == true) goto L253;
        if (r49 != null) goto L236;
        r49 = r162.edit();
    L236:
        if (r39 == true) goto L238;
        r49.putInt("color_" + r30, r142);
        goto L253
    L238:
        if (r42 != 2) goto L240;
        r49.putInt("ChannelLed", r142);
        goto L253
    L240:
        if (r42 != 0) goto L243;
        r49.putInt("GroupLed", r142);
        goto L253
    L243:
        if (r42 != 3) goto L246;
        r49.putInt("StoriesLed", r142);
        goto L253
    L246:
        if (r42 != 5) goto L248;
    L251:
        r49.putInt("ReactionsLed", r142);
        goto L253
    L248:
        if (r42 == 4) goto L251;
        r49.putInt("MessagesLed", r142);
    L253:
        r103 = r142;
        r72 = true;
    L254:
        if (r49 == null) goto L256;
        r49.apply();
    L256:
        r44 = r17;
        r5 = r53;
        goto L258
    L231:
        r212 = r96;
        goto L230
    L124:
        if (r42 != 3) goto L127;
        r74.putBoolean("EnableAllStories", false);
        boolean r54 = true;
    L130:
        updateServerNotificationsSettings(r42);
        r124 = "_s";
        r20 = r54;
        goto L131
    L127:
        if (r42 != 4) goto L129;
        r54 = true;
        r74.putBoolean("EnableReactionsMessages", true);
        r74.putBoolean("EnableReactionsStories", true);
        goto L130
    L129:
        r54 = true;
        r74.putInt(getGlobalNotificationsKey(r42), Integer.MAX_VALUE);
        goto L130
    L134:
        if (r42 != 3) goto L137;
        r74.putBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + getSharedPrefKey(r30, r25), false);
        r213 = false;
    L136:
        r20 = true;
        r24 = r62;
        r104 = r43;
        r124 = "_s";
        updateServerNotificationsSettings(r30, 0, true);
        goto L139
    L137:
        r213 = false;
        r74.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + getSharedPrefKey(r30, r25), 2);
        goto L136
    L140:
        r24 = r62;
        r63 = r43;
        r212 = r154;
        r202 = r112;
        r113 = "_s";
        r322 = r94;
        if (r7 == r38) goto L187;
        if (r40 == true) goto L185;
        SharedPreferences.Editor r157 = r162.edit();
        r28 = r28;
        if (r7 != 4) goto L146;
    L155:
        int r711 = 1;
    L156:
        if (r39 == true) goto L158;
        boolean r55 = false;
        r55 = false;
        if (r42 != 3) goto L183;
        r157.putBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r30, true);
    L184:
        r49 = r157;
        boolean r56 = r55;
    L186:
        r96 = r35;
        r72 = true;
        r53 = r56;
        goto L188
    L183:
        r157.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + r30, 0);
        r157.remove(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL + r30);
        r157.putInt("priority_" + r30, r711);
        goto L184
    L158:
        if (r42 != 3) goto L163;
        r157.putBoolean("EnableAllStories", true);
    L160:
        boolean r57 = false;
    L166:
        if (r42 != 2) goto L168;
        r157.putInt("priority_channel", r711);
        r55 = r57;
        goto L184
    L168:
        if (r42 != 0) goto L171;
        r157.putInt("priority_group", r711);
        r55 = r57;
        goto L184
    L171:
        if (r42 != 3) goto L174;
        r157.putInt("priority_stories", r711);
        r55 = r57;
        goto L184
    L174:
        if (r42 != 4) goto L176;
    L179:
        r157.putInt("priority_react", r711);
        r55 = r57;
        goto L184
    L176:
        if (r42 == 5) goto L179;
        r157.putInt("priority_messages", r711);
        r55 = r57;
        goto L184
    L163:
        if (r42 != 4) goto L165;
        r157.putBoolean("EnableReactionsMessages", true);
        r157.putBoolean("EnableReactionsStories", true);
        goto L160
    L165:
        r57 = false;
        r157.putInt(getGlobalNotificationsKey(r42), 0);
        goto L166
    L146:
        if (r7 == 5) goto L155;
        if (r7 != 1) goto L152;
        r711 = 4;
        goto L156
    L152:
        if (r7 != 2) goto L154;
        r711 = 5;
        goto L156
    L154:
        r711 = 0;
        goto L156
    L185:
        r28 = r28;
        r56 = false;
        r49 = null;
        goto L186
    L187:
        r28 = r28;
        r53 = false;
        r96 = r35;
        r72 = false;
        r49 = null;
        goto L188
    L257:
        r103 = r36;
        r28 = "secret";
        r24 = r62;
        r202 = r112;
        r53 = false;
        r113 = "_s";
        r63 = r43;
        r322 = r94;
        r72 = false;
        r212 = r35;
        goto L256
    L101:
        r27 = r93;
    L94:
        r25 = 0;
        r154 = r22;
    L259:
        r95 = r35;
        r28 = "secret";
        String r242 = r62;
        r342 = r102;
        String r203 = r112;
        r222 = "_";
        r23 = r14;
        int r58 = r21;
        r113 = "_s";
    L260:
        r103 = r36;
        r63 = r43;
        r122 = r58 == true ? 1 : 0;
        r155 = r17;
        r73 = r203;
        r0 = r242;
        r45 = null;
        r52 = r58;
        goto L264
    L261:
        r113 = "_s";
        r95 = r35;
        r63 = r43;
        r28 = "secret";
        r342 = r102;
        r222 = "_";
        r23 = r14;
        int r59 = r21;
        r103 = r36;
        r122 = r59 == true ? 1 : 0;
        r155 = r17;
        r0 = null;
        r45 = null;
        r73 = null;
        r52 = r59;
        goto L264
    L86:
        r17 = r152;
        goto L87
    L262:
        r28 = "secret";
        r242 = r62;
        r162 = r9;
        r342 = r102;
        r203 = r112;
        r222 = "_";
        r33 = r13;
        r23 = r14;
        r17 = r152;
        r58 = r21;
        r113 = "_s";
        r95 = r35;
        goto L260
    L43:
        if (r39 == false) goto L72;
        if (r40 == false) goto L47;
        int r133 = R.string.NotificationsInAppDefault;
    L46:
        r13 = LocaleController.getString(r133);
        r21 = 0;
        if (r42 != 2) goto L52;
        if (r40 == false) goto L51;
        r11 = "channels_ia";
    L51:
        r10 = r11;
        goto L81
    L52:
        if (r42 != 0) goto L56;
        if (r40 == false) goto L81;
        r10 = "groups_ia";
        goto L81
    L56:
        if (r42 != 3) goto L61;
        if (r40 == false) goto L59;
        r6 = "stories_ia";
    L59:
        r10 = r6;
        goto L81
    L61:
        if (r42 != 4) goto L63;
    L69:
        if (r40 == false) goto L71;
        r16 = "reactions_ia";
    L71:
        r10 = r16;
        goto L81
    L63:
        if (r42 == 5) goto L69;
        if (r40 == false) goto L67;
        String r510 = "private_ia";
    L68:
        r10 = r510;
        goto L81
    L67:
        r510 = "private";
        goto L68
    L47:
        r133 = R.string.NotificationsDefault;
        goto L46
    L72:
        r21 = 0;
        if (r40 == false) goto L75;
        String r511 = LocaleController.formatString(R.string.NotificationsChatInApp, new Object[]{r34});
    L76:
        StringBuilder r64 = new StringBuilder();
        if (r40 == false) goto L79;
        String r105 = "org.telegram.keyia";
    L80:
        r64.append(r105);
        r64.append(r30);
        r64.append("_");
        r64.append(r32);
        r10 = r64.toString();
        r13 = r511;
        goto L81
    L79:
        r105 = "org.telegram.key";
        goto L80
    L75:
        r511 = r34;
        goto L76
    L34:
        r19 = r37.toString();
    L30:
        r152 = false;
        goto L31
    L22:
        if (DialogObject.isEncryptedDialog(r30) == false) goto L24;
        r18 = true;
        goto L25
    L5:
        if (r42 != 2) goto L8;
        r14 = "channels" + this.currentAccount;
        r15 = "overwrite_channel";
    L7:
        r16 = "reactions";
        goto L20
    L8:
        if (r42 != 0) goto L10;
        r14 = "groups" + this.currentAccount;
        r15 = "overwrite_group";
        goto L7
    L10:
        if (r42 != 3) goto L13;
        r14 = "stories" + this.currentAccount;
        r15 = "overwrite_stories";
        goto L7
    L13:
        if (r42 != 4) goto L15;
    L18:
        r14 = "reactions" + this.currentAccount;
        r15 = "overwrite_reactions";
        goto L7
    L15:
        if (r42 == 5) goto L18;
        r14 = "private" + this.currentAccount;
        r15 = "overwrite_private";
        goto L7
    }

    private String validateChannelIdForDialog(long r25, long r27, boolean r29, MessageObject r30, long r31, long r33, boolean r35, String r36) {
        SharedPreferences r3 = getAccountInstance().getNotificationsSettings();     // Catch: Exception -> L69
        if (r30 != null) goto L6;
    L8:
        boolean r9 = false;
    L9:
        if (r30 != null) goto L11;
    L14:
        long r4 = r25;
    L15:
        int r42 = getNotifyOverride(r3, r4, r27);     // Catch: Exception -> L69
        if (r42 != (-1)) goto L18;
        boolean r6 = r9;
        boolean r5 = isGlobalNotificationsEnabled(r25, Boolean.valueOf(r35), r6, r9);     // Catch: Exception -> L69
    L22:
        if (r30 != null) goto L24;
    L26:
        SharedPreferences r32 = r3;
        boolean r10 = false;
    L27:
        DialogNotificationSettings r34 = computeDialogNotificationSettings(r32, r25, r27, r29, r6, r10, r31, r33, r35, !r5);     // Catch: Exception -> L69
        int r2 = r34.importance;     // Catch: Exception -> L69
        if (r2 == 1) goto L37;
        if (r2 == 2) goto L37;
        if (r2 != 4) goto L34;
        int r19 = 1;
    L38:
        Uri r22 = Settings.System.DEFAULT_NOTIFICATION_URI;     // Catch: Exception -> L69
        String r52 = r22.getPath();     // Catch: Exception -> L69
        if (r34.notifyDisabled == true) goto L54;
        String r62 = r34.soundPath;     // Catch: Exception -> L69
        if (r62 == null) goto L54;
        if (r62.equalsIgnoreCase("NoSound") == true) goto L54;
        if (r34.soundPath.equalsIgnoreCase("Default") == false) goto L47;
    L52:
        Uri r18 = r22;
        boolean r23 = r34.notifyDisabled;     // Catch: Exception -> L69
        if (r23 == true) goto L66;
        int r53 = r34.vibrate;     // Catch: Exception -> L69
        if (r53 == 2) goto L66;
        if (r53 != 1) goto L63;
        long[] r0 = {0, 100, 0, 100};     // Catch: Exception -> L69
    L67:
        return validateChannelId(r25, r27, r36, r0, r34.ledColor, r18, r19, r34.isDefault, r34.isInApp, r23, r34.chatType);
    L63:
        if (r53 != 3) goto L65;
        r0 = new long[]{0, 1000};     // Catch: Exception -> L69
        goto L67
    L65:
        r0 = new long[0];     // Catch: Exception -> L69
    L66:
        r0 = new long[]{0, 0};     // Catch: Exception -> L69
        goto L67
    L47:
        if (r34.soundPath.equals(r52) == true) goto L52;
        if (r34.isInternalSoundFile == false) goto L53;
        r22 = FileProvider.getUriForFile(ApplicationLoader.applicationContext, ApplicationLoader.getApplicationId() + ".provider", new File(r34.soundPath));     // Catch: Exception -> L69
        ApplicationLoader.applicationContext.grantUriPermission("com.android.systemui", r22, 1);     // Catch: Exception -> L69
        goto L52
    L53:
        r22 = Uri.parse(r34.soundPath);     // Catch: Exception -> L69
    L54:
        r22 = null;
        goto L52
    L34:
        if (r2 != 5) goto L36;
        r19 = 2;
        goto L38
    L36:
        r19 = 3;
    L37:
        r19 = 4;
        goto L38
    L24:
        if (r30.isStoryReactionPush == false) goto L26;
        r32 = r3;
        r10 = true;
        goto L27
    L18:
        r6 = r9;
        if (r42 == 2) goto L21;
        r5 = true;
        goto L22
    L21:
        r5 = false;
        goto L22
    L11:
        if (r30.messageOwner.mentioned == false) goto L14;
        r4 = r30.getFromChatId();     // Catch: Exception -> L69
        goto L15
    L6:
        if (r30.isReactionPush == false) goto L8;
        r9 = true;
    L69:
        e = move-exception;
        FileLog.e(e);
        return OTHER_NOTIFICATIONS_CHANNEL;
    }

    public void cleanup() {
        this.popupMessages.clear();
        this.popupReplyMessages.clear();
        this.channelGroupsCreated = false;
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda11(this));
    }

    public void clearDialogNotificationsSettings(long r10, long r12) {
        SharedPreferences.Editor r0 = getAccountInstance().getNotificationsSettings().edit();
        String r1 = getSharedPrefKey(r10, r12);
        r0.remove(NotificationsSettingsFacade.PROPERTY_NOTIFY + r1).remove(NotificationsSettingsFacade.PROPERTY_CUSTOM + r1);
        getMessagesStorage().setDialogFlags(r10, 0);
        TLRPC.Dialog r13 = getMessagesController().dialogs_dict.get(r10);
        if (r13 == null) goto L5;
        r13.notify_settings = new TLRPC.TL_peerNotifySettings();
    L5:
        r0.apply();
        getNotificationsController().updateServerNotificationsSettings(r10, r12, true);
    }

    public void deleteAllNotificationChannels() {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda40(this));
    }

    public void deleteNotificationChannel(long r7, long r9) {
        deleteNotificationChannel(r7, r9, -1);
    }

    public void deleteNotificationChannel(long r9, long r11, int r13) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda44(this, r9, r11, r13));
    }

    public void deleteNotificationChannelGlobal(int r2) {
        deleteNotificationChannelGlobal(r2, -1);
    }

    public void deleteNotificationChannelGlobal(int r3, int r4) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda39(this, r3, r4));
    }

    public void deleteNotificationChannelGlobalInternal(int r13, int r14) {
        if (Build.VERSION.SDK_INT < 26) goto L76;
        SharedPreferences r12 = getAccountInstance().getNotificationsSettings();     // Catch: Exception -> L67
        SharedPreferences.Editor r0 = r12.edit();     // Catch: Exception -> L67
        if (r14 == 0) goto L9;
        if (r14 == (-1)) goto L9;
    L31:
        if (r14 == 1) goto L33;
        if (r14 == (-1)) goto L33;
    L54:
        if (r13 != 2) goto L56;
        String r122 = "overwrite_channel";
    L65:
        r0.remove(r122);     // Catch: Exception -> L67
        r0.apply();     // Catch: Exception -> L67
        return;
    L56:
        if (r13 != 0) goto L58;
        r122 = "overwrite_group";
        goto L65
    L58:
        if (r13 != 3) goto L60;
        r122 = "overwrite_stories";
        goto L65
    L60:
        if (r13 == 4) goto L64;
        if (r13 == 5) goto L64;
        r122 = "overwrite_private";
    L64:
        r122 = "overwrite_reactions";
    L33:
        if (r13 != 2) goto L35;
        String r142 = "channels_ia";
    L44:
        String r123 = r12.getString(r142, null);     // Catch: Exception -> L67
        if (r123 == null) goto L54;
        r0.remove(r142).remove(r142.concat("_s"));     // Catch: Exception -> L67
        systemNotificationManager.deleteNotificationChannel(r123);     // Catch: Exception -> L49
    L52:
        if (BuildVars.LOGS_ENABLED == false) goto L54;
        FileLog.d("delete channel global internal ".concat(r123));     // Catch: Exception -> L67
    L49:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L67
        goto L52
    L35:
        if (r13 != 0) goto L37;
        r142 = "groups_ia";
        goto L44
    L37:
        if (r13 != 3) goto L39;
        r142 = "stories_ia";
        goto L44
    L39:
        if (r13 == 4) goto L43;
        if (r13 == 5) goto L43;
        r142 = "private_ia";
    L43:
        r142 = "reactions_ia";
    L9:
        if (r13 != 2) goto L11;
        String r9 = "channels";
    L20:
        String r10 = r12.getString(r9, null);     // Catch: Exception -> L67
        if (r10 == null) goto L31;
        r0.remove(r9).remove(r9.concat("_s"));     // Catch: Exception -> L67
        systemNotificationManager.deleteNotificationChannel(r10);     // Catch: Exception -> L25
    L28:
        if (BuildVars.LOGS_ENABLED == false) goto L31;
        FileLog.d("delete channel global internal ".concat(r10));     // Catch: Exception -> L67
    L25:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L67
        goto L28
    L11:
        if (r13 != 0) goto L13;
        r9 = "groups";
        goto L20
    L13:
        if (r13 != 3) goto L15;
        r9 = "stories";
        goto L20
    L15:
        if (r13 == 4) goto L19;
        if (r13 == 5) goto L19;
        r9 = "private";
    L19:
        r9 = "reactions";
    L67:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.fileLoaded) goto L6;
        String r12 = (String) r3[0];
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda51(this, r12));
        return;
    }

    @TargetApi(26)
    public void ensureGroupsCreated() {
        SharedPreferences r2 = getAccountInstance().getNotificationsSettings();
        if (this.groupsCreated != null) goto L6;
        this.groupsCreated = Boolean.valueOf(r2.getBoolean("groupsCreated5", false));
    L6:
        if (this.groupsCreated.booleanValue() == true) goto L54;
        String r0 = this.currentAccount + "channel";     // Catch: Exception -> L22
        List<NotificationChannel> r7 = systemNotificationManager.getNotificationChannels();     // Catch: Exception -> L22
        int r8 = r7.size();     // Catch: Exception -> L22
        int r9 = 0;
        SharedPreferences.Editor r10 = null;
    L8:
        if (r9 >= r8) goto L48;
        NotificationChannel r11 = NotificationsController$$ExternalSyntheticApiModelOutline2.m(r7.get(r9));     // Catch: Exception -> L22
        String r12 = r11.getId();     // Catch: Exception -> L22
        if (r12.startsWith(r0) == false) goto L47;
        int r112 = r11.getImportance();     // Catch: Exception -> L22
        if (r112 != 4) goto L14;
    L46:
        systemNotificationManager.deleteNotificationChannel(r12);     // Catch: Exception -> L22
        goto L47
    L14:
        if (r112 == 5) goto L46;
        if (r12.contains("_ia_") == true) goto L46;
        if (r12.contains("_channels_") == false) goto L26;
        if (r10 != null) goto L24;
        r10 = getAccountInstance().getNotificationsSettings().edit();     // Catch: Exception -> L22
    L24:
        r10.remove("priority_channel").remove("vibrate_channel").remove("ChannelSoundPath").remove("ChannelSound");     // Catch: Exception -> L22
        goto L46
    L26:
        if (r12.contains("_reactions_") == false) goto L32;
        if (r10 != null) goto L29;
        r10 = getAccountInstance().getNotificationsSettings().edit();     // Catch: Exception -> L22
    L29:
        r10.remove("priority_react").remove("vibrate_react").remove("ReactionSoundPath").remove("ReactionSound");     // Catch: Exception -> L22
        goto L46
    L32:
        if (r12.contains("_groups_") == false) goto L37;
        if (r10 != null) goto L35;
        r10 = getAccountInstance().getNotificationsSettings().edit();     // Catch: Exception -> L22
    L35:
        r10.remove("priority_group").remove("vibrate_group").remove("GroupSoundPath").remove("GroupSound");     // Catch: Exception -> L22
        goto L46
    L37:
        if (r12.contains("_private_") == false) goto L41;
        if (r10 != null) goto L40;
        r10 = getAccountInstance().getNotificationsSettings().edit();     // Catch: Exception -> L22
    L40:
        r10.remove("priority_messages");     // Catch: Exception -> L22
        r10.remove("priority_group").remove("vibrate_messages").remove("GlobalSoundPath").remove("GlobalSound");     // Catch: Exception -> L22
        goto L46
    L41:
        long r13 = Utilities.parseLong(r12.substring(9, r12.indexOf(95, 9))).longValue();     // Catch: Exception -> L22
        if (r13 == 0) goto L46;
        if (r10 != null) goto L45;
        r10 = getAccountInstance().getNotificationsSettings().edit();     // Catch: Exception -> L22
    L45:
        r10.remove("priority_" + r13).remove("vibrate_" + r13).remove("sound_path_" + r13).remove("sound_" + r13);     // Catch: Exception -> L22
    L47:
        r9 = r9 + 1;     // Catch: Exception -> L22
        goto L8
    L48:
        if (r10 == null) goto L52;
        r10.apply();     // Catch: Exception -> L22
    L52:
        r2.edit().putBoolean("groupsCreated5", true).apply();
        this.groupsCreated = Boolean.TRUE;
    L22:
        e = move-exception;
        FileLog.e(e);
    L54:
        if (this.channelGroupsCreated == true) goto L131;
        List<NotificationChannelGroup> r02 = systemNotificationManager.getNotificationChannelGroups();
        String r22 = "channels" + this.currentAccount;
        String r3 = "groups" + this.currentAccount;
        String r4 = "private" + this.currentAccount;
        String r72 = "stories" + this.currentAccount;
        String r82 = "reactions" + this.currentAccount;
        String r92 = "other" + this.currentAccount;
        int r102 = r02.size();
        String r113 = r92;
        String r93 = r82;
        String r83 = r72;
        String r73 = r4;
        int r42 = 0;
    L56:
        if (r42 >= r102) goto L90;
        String r122 = NotificationsController$$ExternalSyntheticApiModelOutline3.m(r02.get(r42)).getId();
        if (r22 != null) goto L60;
    L62:
        if (r3 != null) goto L64;
    L66:
        if (r83 != null) goto L68;
    L70:
        if (r93 != null) goto L72;
    L74:
        if (r73 != null) goto L76;
    L78:
        if (r113 != null) goto L80;
    L82:
        if (r22 != null) goto L89;
        if (r83 != null) goto L89;
        if (r93 != null) goto L89;
        if (r3 != null) goto L89;
        if (r73 != null) goto L89;
        if (r113 == null) goto L90;
    L89:
        r42 = r42 + 1;
        goto L56
    L80:
        if (r113.equals(r122) == false) goto L82;
        r113 = null;
        goto L82
    L76:
        if (r73.equals(r122) == false) goto L78;
        r73 = null;
        goto L82
    L72:
        if (r93.equals(r122) == false) goto L74;
        r93 = null;
        goto L82
    L68:
        if (r83.equals(r122) == false) goto L70;
        r83 = null;
        goto L82
    L64:
        if (r3.equals(r122) == false) goto L66;
        r3 = null;
        goto L82
    L60:
        if (r22.equals(r122) == false) goto L62;
        r22 = null;
    L90:
        if (r22 != null) goto L96;
        if (r3 != null) goto L96;
        if (r93 != null) goto L96;
        if (r83 != null) goto L96;
        if (r73 != null) goto L96;
        if (r113 != null) goto L96;
    L116:
        this.channelGroupsCreated = true;
        return;
    L96:
        TLRPC.User r03 = getMessagesController().getUser(Long.valueOf(getUserConfig().getClientUserId()));
        if (r03 != null) goto L99;
        getUserConfig().getCurrentUser();
    L99:
        if (r03 == null) goto L101;
        String r04 = " (" + ContactsController.formatName(r03.first_name, r03.last_name) + ")";
    L102:
        ArrayList r43 = new ArrayList();
        if (r22 == null) goto L105;
        NotificationsController$$ExternalSyntheticApiModelOutline1.m();
        r43.add(NotificationsController$$ExternalSyntheticApiModelOutline0.m(r22, LocaleController.getString(R.string.NotificationsChannels) + r04));
    L105:
        if (r3 == null) goto L107;
        NotificationsController$$ExternalSyntheticApiModelOutline1.m();
        r43.add(NotificationsController$$ExternalSyntheticApiModelOutline0.m(r3, LocaleController.getString(R.string.NotificationsGroups) + r04));
    L107:
        if (r83 == null) goto L109;
        NotificationsController$$ExternalSyntheticApiModelOutline1.m();
        r43.add(NotificationsController$$ExternalSyntheticApiModelOutline0.m(r83, LocaleController.getString(R.string.NotificationsStories) + r04));
    L109:
        if (r93 == null) goto L111;
        NotificationsController$$ExternalSyntheticApiModelOutline1.m();
        r43.add(NotificationsController$$ExternalSyntheticApiModelOutline0.m(r93, LocaleController.getString(R.string.NotificationsReactions) + r04));
    L111:
        if (r73 == null) goto L113;
        NotificationsController$$ExternalSyntheticApiModelOutline1.m();
        r43.add(NotificationsController$$ExternalSyntheticApiModelOutline0.m(r73, LocaleController.getString(R.string.NotificationsPrivateChats) + r04));
    L113:
        if (r113 == null) goto L115;
        NotificationsController$$ExternalSyntheticApiModelOutline1.m();
        r43.add(NotificationsController$$ExternalSyntheticApiModelOutline0.m(r113, LocaleController.getString(R.string.NotificationsOther) + r04));
    L115:
        systemNotificationManager.createNotificationChannelGroups(r43);
        goto L116
    L101:
        r04 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L102
    }

    public void forceShowPopupForReply() {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda58(this));
    }

    public NotificationsSettingsFacade getNotificationsSettingsFacade() {
        return this.dialogsNotificationsFacade;
    }

    public ArrayList<MessageObject> getPushMessagesSnapshot() {
        monitor-enter(this);
        ArrayList<MessageObject> r0 = new ArrayList(this.pushMessages);     // Catch: Throwable -> L6
        monitor-exit(this);     // Catch: Throwable -> L6
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public String getShortStringForMessage(MessageObject r34, String[] r35, boolean[] r36) {
        if (AndroidUtilities.needShowPasscode() == true) goto L909;
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L909;
        TLRPC.Message r2 = r34.messageOwner;
        long r3 = r2.dialog_id;
        TLRPC.Peer r22 = r2.peer_id;
        long r5 = r22.chat_id;
        if (r5 != 0) goto L11;
        r5 = r22.channel_id;
    L11:
        long r9 = r22.user_id;
        if (r36 == null) goto L14;
        r36[0] = true;
    L14:
        SharedPreferences r12 = getAccountInstance().getNotificationsSettings();
        boolean r13 = r12.getBoolean(NotificationsSettingsFacade.PROPERTY_CONTENT_PREVIEW + r3, true);
        if (r34.isFcmMessage() == true) goto L17;
        long r19 = getUserConfig().getClientUserId();
        if (r9 != 0) goto L67;
        r9 = r34.getFromChatId();
        if (r9 != 0) goto L70;
        r9 = -r5;
    L70:
        if (r3 != 0) goto L78;
        if (r5 == 0) goto L75;
        r3 = -r5;
        goto L78
    L75:
        if (r9 == 0) goto L78;
        r3 = r9;
    L78:
        if (UserObject.isReplyUser(r3) == false) goto L85;
        TLRPC.MessageFwdHeader r14 = r34.messageOwner.fwd_from;
        if (r14 == null) goto L85;
        TLRPC.Peer r142 = r14.from_id;
        if (r142 == null) goto L85;
        r9 = MessageObject.getPeerId(r142);
    L85:
        if (r9 <= 0) goto L98;
        TLRPC.User r23 = getMessagesController().getUser(Long.valueOf(r9));
        if (r23 == null) goto L96;
        String r24 = UserObject.getUserName(r23);
        if (r5 == 0) goto L91;
        r35[0] = r24;
        String r222 = r24;
    L95:
        String r25 = r222;
    L97:
        long r223 = r3;
    L102:
        if (r25 == null) goto L117;
        if (r9 <= 0) goto L117;
        if (UserObject.isReplyUser(r223) == false) goto L117;
        TLRPC.MessageFwdHeader r32 = r34.messageOwner.fwd_from;
        if (r32 == null) goto L117;
        TLRPC.Peer r33 = r32.saved_from_peer;
        if (r33 == null) goto L117;
        long r37 = MessageObject.getPeerId(r33);
        if (DialogObject.isChatDialog(r37) == false) goto L117;
        TLRPC.Chat r38 = getMessagesController().getChat(Long.valueOf(-r37));
        if (r38 == null) goto L117;
        r25 = r25 + " @ " + getTitle(r38);
        if (r35[0] == null) goto L117;
        r35[0] = r25;
    L117:
        if (r25 != null) goto L120;
        return null;
    L120:
        if (r5 == 0) goto L131;
        TLRPC.Chat r4 = getMessagesController().getChat(Long.valueOf(r5));
        if (r4 != null) goto L125;
        return null;
    L125:
        if (ChatObject.isChannel(r4) == false) goto L133;
        if (r4.megagroup == true) goto L133;
        if (Build.VERSION.SDK_INT > 27) goto L133;
        r35[0] = null;
    L133:
        if (DialogObject.isEncryptedDialog(r223) == false) goto L137;
        r35[0] = null;
        return LocaleController.getString(R.string.NotificationHiddenMessage);
    L137:
        if (ChatObject.isChannel(r4) == true) goto L139;
    L141:
        boolean r52 = false;
    L142:
        TLRPC.Message r6 = r34.messageOwner;
        if (r6 != null) goto L145;
    L148:
        if (r13 == false) goto L904;
        if (r5 != 0) goto L154;
        if (r9 == 0) goto L154;
        boolean r62 = true;
        if (r12.getBoolean("EnablePreviewAll", true) == false) goto L155;
    L162:
        TLRPC.Message r39 = r34.messageOwner;
        boolean r53 = r39 instanceof TLRPC.TL_messageService;
        String r63 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r53 == false) goto L734;
        r35[0] = null;
        TLRPC.MessageAction r310 = r39.action;
        if ((r310 instanceof TLRPC.TL_messageActionSetSameChatWallPaper) == false) goto L169;
        return LocaleController.getString(R.string.WallpaperSameNotification);
    L169:
        if ((r310 instanceof TLRPC.TL_messageActionSetChatWallPaper) == false) goto L173;
        return LocaleController.getString(R.string.WallpaperNotification);
    L173:
        if ((r310 instanceof TLRPC.TL_messageActionGeoProximityReached) == false) goto L177;
        return r34.messageText.toString();
    L177:
        if ((r310 instanceof TLRPC.TL_messageActionUserJoined) == true) goto L732;
        if ((r310 instanceof TLRPC.TL_messageActionContactSignUp) == true) goto L732;
        if ((r310 instanceof TLRPC.TL_messageActionUserUpdatedPhoto) == false) goto L186;
        return LocaleController.formatString(R.string.NotificationContactNewPhoto, new Object[]{r25});
    L186:
        if ((r310 instanceof TLRPC.TL_messageActionLoginUnknownLocation) == false) goto L190;
        String r26 = LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterYear().format(r34.messageOwner.date * 1000), LocaleController.getInstance().getFormatterDay().format(r34.messageOwner.date * 1000)});
        int r311 = R.string.NotificationUnrecognizedDevice;
        String r0 = getUserConfig().getCurrentUser().first_name;
        TLRPC.MessageAction r1 = r34.messageOwner.action;
        return LocaleController.formatString(r311, new Object[]{r0, r26, r1.title, r1.address});
    L190:
        if (TlUtils.isInstance(r310, new Class[]{TLRPC.TL_messageActionGameScore.class, TLRPC.TL_messageActionPaymentSent.class, TLRPC.TL_messageActionPaymentSentMe.class, TLRPC.TL_messageActionStarGift.class, TLRPC.TL_messageActionGiftPremium.class, TLRPC.TL_messageActionStarGiftUnique.class, TLRPC.TL_messageActionPaidMessagesPrice.class, TLRPC.TL_messageActionPaidMessagesRefunded.class, TLRPC.TL_messageActionGiftTon.class}) == true) goto L192;
        TLRPC.Message r312 = r34.messageOwner;
        TLRPC.MessageAction r54 = r312.action;
        if ((r54 instanceof TLRPC.TL_messageActionPhoneCall) == false) goto L202;
        if (r54.video == false) goto L200;
        return LocaleController.getString(R.string.CallMessageVideoIncomingMissed);
    L200:
        return LocaleController.getString(R.string.CallMessageIncomingMissed);
    L202:
        if ((r54 instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L210;
        if (r54.video == false) goto L208;
        return LocaleController.getString(R.string.CallMessageVideoIncomingConferenceMissed);
    L208:
        return LocaleController.getString(R.string.CallMessageIncomingConferenceMissed);
    L210:
        if ((r54 instanceof TLRPC.TL_messageActionChatAddUser) == false) goto L254;
        long r64 = r54.user_id;
        if (r64 != 0) goto L217;
        if (r54.users.size() != 1) goto L217;
        r64 = r34.messageOwner.action.users.get(0).longValue();
    L217:
        if (r64 != 0) goto L219;
        StringBuilder r313 = new StringBuilder();
        int r11 = 0;
    L243:
        if (r11 >= r34.messageOwner.action.users.size()) goto L252;
        TLRPC.User r55 = getMessagesController().getUser(r34.messageOwner.action.users.get(r11));
        if (r55 == null) goto L250;
        String r56 = UserObject.getUserName(r55);
        if (r313.length() == 0) goto L249;
        r313.append(", ");
    L249:
        r313.append(r56);
    L250:
        r11 = r11 + 1;
        goto L243
    L252:
        return LocaleController.formatString(R.string.NotificationGroupAddMember, new Object[]{r25, getTitle(r4), r313.toString()});
    L219:
        if (r34.messageOwner.peer_id.channel_id == 0) goto L225;
        if (r4.megagroup == true) goto L225;
        return LocaleController.formatString(R.string.ChannelAddedByNotification, new Object[]{r25, getTitle(r4)});
    L225:
        if (r64 == r19) goto L227;
        TLRPC.User r15 = getMessagesController().getUser(Long.valueOf(r64));
        if (r15 != null) goto L232;
        return null;
    L232:
        if (r9 != r15.id) goto L240;
        if (r4.megagroup == false) goto L238;
        return LocaleController.formatString(R.string.NotificationGroupAddSelfMega, new Object[]{r25, getTitle(r4)});
    L238:
        return LocaleController.formatString(R.string.NotificationGroupAddSelf, new Object[]{r25, getTitle(r4)});
    L240:
        return LocaleController.formatString(R.string.NotificationGroupAddMember, new Object[]{r25, getTitle(r4), UserObject.getUserName(r15)});
    L227:
        return LocaleController.formatString(R.string.NotificationInvitedToGroup, new Object[]{r25, getTitle(r4)});
    L254:
        if ((r54 instanceof TLRPC.TL_messageActionGroupCall) == false) goto L262;
        if (r54.duration == 0) goto L260;
        return LocaleController.formatString(R.string.NotificationGroupEndedCall, new Object[]{r25, getTitle(r4)});
    L260:
        return LocaleController.formatString(R.string.NotificationGroupCreatedCall, new Object[]{r25, getTitle(r4)});
    L262:
        if ((r54 instanceof TLRPC.TL_messageActionGroupCallScheduled) == false) goto L266;
        return r34.messageText.toString();
    L266:
        if ((r54 instanceof TLRPC.TL_messageActionInviteToGroupCall) == false) goto L296;
        long r65 = r54.user_id;
        if (r65 != 0) goto L273;
        if (r54.users.size() != 1) goto L273;
        r65 = r34.messageOwner.action.users.get(0).longValue();
    L273:
        if (r65 != 0) goto L275;
        StringBuilder r314 = new StringBuilder();
        int r112 = 0;
    L285:
        if (r112 >= r34.messageOwner.action.users.size()) goto L294;
        TLRPC.User r57 = getMessagesController().getUser(r34.messageOwner.action.users.get(r112));
        if (r57 == null) goto L292;
        String r58 = UserObject.getUserName(r57);
        if (r314.length() == 0) goto L291;
        r314.append(", ");
    L291:
        r314.append(r58);
    L292:
        r112 = r112 + 1;
        goto L285
    L294:
        return LocaleController.formatString(R.string.NotificationGroupInvitedToCall, new Object[]{r25, getTitle(r4), r314.toString()});
    L275:
        if (r65 == r19) goto L277;
        TLRPC.User r16 = getMessagesController().getUser(Long.valueOf(r65));
        if (r16 != null) goto L282;
        return null;
    L282:
        return LocaleController.formatString(R.string.NotificationGroupInvitedToCall, new Object[]{r25, getTitle(r4), UserObject.getUserName(r16)});
    L277:
        return LocaleController.formatString(R.string.NotificationGroupInvitedYouToCall, new Object[]{r25, getTitle(r4)});
    L296:
        if ((r54 instanceof TLRPC.TL_messageActionGiftCode) == false) goto L300;
        return LocaleController.getString(R.string.BoostingReceivedGiftNoName);
    L300:
        if ((r54 instanceof TLRPC.TL_messageActionChatJoinedByLink) == false) goto L304;
        return LocaleController.formatString(R.string.NotificationInvitedToGroupByLink, new Object[]{r25, getTitle(r4)});
    L304:
        if ((r54 instanceof TLRPC.TL_messageActionChatEditTitle) == false) goto L308;
        return LocaleController.formatString(R.string.NotificationEditedGroupName, new Object[]{r25, r54.title});
    L308:
        if ((r54 instanceof TLRPC.TL_messageActionChatEditPhoto) == true) goto L716;
        if ((r54 instanceof TLRPC.TL_messageActionChatDeletePhoto) == true) goto L716;
        if ((r54 instanceof TLRPC.TL_messageActionChatDeleteUser) == false) goto L328;
        long r59 = r54.user_id;
        if (r59 != r19) goto L319;
        return LocaleController.formatString(R.string.NotificationGroupKickYou, new Object[]{r25, getTitle(r4)});
    L319:
        if (r59 == r9) goto L321;
        TLRPC.User r17 = getMessagesController().getUser(Long.valueOf(r34.messageOwner.action.user_id));
        if (r17 != null) goto L326;
        return null;
    L326:
        return LocaleController.formatString(R.string.NotificationGroupKickMember, new Object[]{r25, getTitle(r4), UserObject.getUserName(r17)});
    L321:
        return LocaleController.formatString(R.string.NotificationGroupLeftMember, new Object[]{r25, getTitle(r4)});
    L328:
        if ((r54 instanceof TLRPC.TL_messageActionChatCreate) == false) goto L332;
        return r34.messageText.toString();
    L332:
        if ((r54 instanceof TLRPC.TL_messageActionChannelCreate) == false) goto L336;
        return r34.messageText.toString();
    L336:
        if ((r54 instanceof TLRPC.TL_messageActionChatMigrateTo) == false) goto L340;
        return LocaleController.formatString(R.string.ActionMigrateFromGroupNotify, new Object[]{getTitle(r4)});
    L340:
        if ((r54 instanceof TLRPC.TL_messageActionChannelMigrateFrom) == false) goto L344;
        return LocaleController.formatString(R.string.ActionMigrateFromGroupNotify, new Object[]{r54.title});
    L344:
        if ((r54 instanceof TLRPC.TL_messageActionScreenshotTaken) == false) goto L348;
        return r34.messageText.toString();
    L348:
        if ((r54 instanceof TLRPC.TL_messageActionGiveawayLaunch) == false) goto L352;
        return r34.messageText.toString();
    L352:
        if ((r54 instanceof TLRPC.TL_messageActionGiveawayResults) == false) goto L356;
        return r34.messageText.toString();
    L356:
        if ((r54 instanceof TLRPC.TL_messageActionSuggestBirthday) == false) goto L360;
        return r34.messageText.toString();
    L360:
        if ((r54 instanceof TLRPC.TL_messageActionPinMessage) == false) goto L671;
        if (r4 != null) goto L364;
    L468:
        MessageObject r18 = r34.replyMessageObject;
        if (r4 == null) goto L570;
        if (r18 != null) goto L474;
        return LocaleController.formatString(R.string.NotificationActionPinnedNoTextChannel, new Object[]{getTitle(r4)});
    L474:
        if (r18.isMusic() == false) goto L478;
        return LocaleController.formatString(R.string.NotificationActionPinnedMusicChannel, new Object[]{getTitle(r4)});
    L478:
        if (r18.isVideo() == false) goto L486;
        if (TextUtils.isEmpty(r18.messageOwner.message) == true) goto L484;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), "📹 " + r18.messageOwner.message});
    L484:
        return LocaleController.formatString(R.string.NotificationActionPinnedVideoChannel, new Object[]{getTitle(r4)});
    L486:
        if (r18.isGif() == false) goto L494;
        if (TextUtils.isEmpty(r18.messageOwner.message) == true) goto L492;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), "🎬 " + r18.messageOwner.message});
    L492:
        return LocaleController.formatString(R.string.NotificationActionPinnedGifChannel, new Object[]{getTitle(r4)});
    L494:
        if (r18.isVoice() == false) goto L498;
        return LocaleController.formatString(R.string.NotificationActionPinnedVoiceChannel, new Object[]{getTitle(r4)});
    L498:
        if (r18.isRoundVideo() == false) goto L502;
        return LocaleController.formatString(R.string.NotificationActionPinnedRoundChannel, new Object[]{getTitle(r4)});
    L502:
        if (r18.isSticker() == false) goto L504;
    L564:
        String r110 = r18.getStickerEmoji();
        if (r110 == null) goto L569;
        return LocaleController.formatString(R.string.NotificationActionPinnedStickerEmojiChannel, new Object[]{getTitle(r4), r110});
    L569:
        return LocaleController.formatString(R.string.NotificationActionPinnedStickerChannel, new Object[]{getTitle(r4)});
    L504:
        if (r18.isAnimatedSticker() == true) goto L564;
        TLRPC.Message r27 = r18.messageOwner;
        TLRPC.MessageMedia r66 = r27.media;
        if ((r66 instanceof TLRPC.TL_messageMediaDocument) == false) goto L515;
        if (TextUtils.isEmpty(r27.message) == true) goto L513;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), "📎 " + r18.messageOwner.message});
    L513:
        return LocaleController.formatString(R.string.NotificationActionPinnedFileChannel, new Object[]{getTitle(r4)});
    L515:
        if ((r66 instanceof TLRPC.TL_messageMediaGeo) == true) goto L563;
        if ((r66 instanceof TLRPC.TL_messageMediaVenue) == true) goto L563;
        if ((r66 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L524;
        return LocaleController.formatString(R.string.NotificationActionPinnedGeoLiveChannel, new Object[]{getTitle(r4)});
    L524:
        if ((r66 instanceof TLRPC.TL_messageMediaContact) == false) goto L528;
        TLRPC.TL_messageMediaContact r67 = (TLRPC.TL_messageMediaContact) r66;
        return LocaleController.formatString(R.string.NotificationActionPinnedContactChannel2, new Object[]{getTitle(r4), ContactsController.formatName(r67.first_name, r67.last_name)});
    L528:
        if ((r66 instanceof TLRPC.TL_messageMediaPoll) == false) goto L536;
        TLRPC.TL_messageMediaPoll r68 = (TLRPC.TL_messageMediaPoll) r66;
        if (r68.poll.quiz == false) goto L534;
        return LocaleController.formatString(R.string.NotificationActionPinnedQuizChannel2, new Object[]{getTitle(r4), r68.poll.question.text});
    L534:
        return LocaleController.formatString(R.string.NotificationActionPinnedPollChannel2, new Object[]{getTitle(r4), r68.poll.question.text});
    L536:
        if ((r66 instanceof TLRPC.TL_messageMediaToDo) == false) goto L540;
        return LocaleController.formatString(R.string.NotificationActionPinnedTodoChannel2, new Object[]{getTitle(r4), ((TLRPC.TL_messageMediaToDo) r66).todo.title.text});
    L540:
        if ((r66 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L548;
        if (TextUtils.isEmpty(r27.message) == true) goto L546;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), "🖼 " + r18.messageOwner.message});
    L546:
        return LocaleController.formatString(R.string.NotificationActionPinnedPhotoChannel, new Object[]{getTitle(r4)});
    L548:
        if ((r66 instanceof TLRPC.TL_messageMediaGame) == true) goto L550;
        CharSequence r28 = r18.messageText;
        if (r28 == null) goto L561;
        if (r28.length() <= 0) goto L561;
        CharSequence r111 = r18.messageText;
        if (r111.length() <= 20) goto L559;
        r111 = r111.subSequence(0, 20) + "...";
    L559:
        return LocaleController.formatString(R.string.NotificationActionPinnedTextChannel, new Object[]{getTitle(r4), r111});
    L561:
        return LocaleController.formatString(R.string.NotificationActionPinnedNoTextChannel, new Object[]{getTitle(r4)});
    L550:
        return LocaleController.formatString(R.string.NotificationActionPinnedGameChannel, new Object[]{getTitle(r4)});
    L563:
        return LocaleController.formatString(R.string.NotificationActionPinnedGeoChannel, new Object[]{getTitle(r4)});
    L570:
        if (r18 != null) goto L574;
        return LocaleController.formatString(R.string.NotificationActionPinnedNoTextUser, new Object[]{r25});
    L574:
        if (r18.isMusic() == false) goto L578;
        return LocaleController.formatString(R.string.NotificationActionPinnedMusicUser, new Object[]{r25});
    L578:
        if (r18.isVideo() == false) goto L586;
        if (TextUtils.isEmpty(r18.messageOwner.message) == true) goto L584;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextUser, new Object[]{r25, "📹 " + r18.messageOwner.message});
    L584:
        return LocaleController.formatString(R.string.NotificationActionPinnedVideoUser, new Object[]{r25});
    L586:
        if (r18.isGif() == false) goto L594;
        if (TextUtils.isEmpty(r18.messageOwner.message) == true) goto L592;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextUser, new Object[]{r25, "🎬 " + r18.messageOwner.message});
    L592:
        return LocaleController.formatString(R.string.NotificationActionPinnedGifUser, new Object[]{r25});
    L594:
        if (r18.isVoice() == false) goto L598;
        return LocaleController.formatString(R.string.NotificationActionPinnedVoiceUser, new Object[]{r25});
    L598:
        if (r18.isRoundVideo() == false) goto L602;
        return LocaleController.formatString(R.string.NotificationActionPinnedRoundUser, new Object[]{r25});
    L602:
        if (r18.isSticker() == false) goto L604;
    L664:
        String r02 = r18.getStickerEmoji();
        if (r02 == null) goto L669;
        return LocaleController.formatString(R.string.NotificationActionPinnedStickerEmojiUser, new Object[]{r25, r02});
    L669:
        return LocaleController.formatString(R.string.NotificationActionPinnedStickerUser, new Object[]{r25});
    L604:
        if (r18.isAnimatedSticker() == true) goto L664;
        TLRPC.Message r03 = r18.messageOwner;
        TLRPC.MessageMedia r42 = r03.media;
        if ((r42 instanceof TLRPC.TL_messageMediaDocument) == false) goto L615;
        if (TextUtils.isEmpty(r03.message) == true) goto L613;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextUser, new Object[]{r25, "📎 " + r18.messageOwner.message});
    L613:
        return LocaleController.formatString(R.string.NotificationActionPinnedFileUser, new Object[]{r25});
    L615:
        if ((r42 instanceof TLRPC.TL_messageMediaGeo) == true) goto L663;
        if ((r42 instanceof TLRPC.TL_messageMediaVenue) == true) goto L663;
        if ((r42 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L624;
        return LocaleController.formatString(R.string.NotificationActionPinnedGeoLiveUser, new Object[]{r25});
    L624:
        if ((r42 instanceof TLRPC.TL_messageMediaContact) == false) goto L628;
        TLRPC.TL_messageMediaContact r43 = (TLRPC.TL_messageMediaContact) r42;
        return LocaleController.formatString(R.string.NotificationActionPinnedContactUser, new Object[]{r25, ContactsController.formatName(r43.first_name, r43.last_name)});
    L628:
        if ((r42 instanceof TLRPC.TL_messageMediaPoll) == false) goto L636;
        TLRPC.Poll r04 = ((TLRPC.TL_messageMediaPoll) r42).poll;
        if (r04.quiz == false) goto L634;
        return LocaleController.formatString(R.string.NotificationActionPinnedQuizUser, new Object[]{r25, r04.question.text});
    L634:
        return LocaleController.formatString(R.string.NotificationActionPinnedPollUser, new Object[]{r25, r04.question.text});
    L636:
        if ((r42 instanceof TLRPC.TL_messageMediaToDo) == false) goto L640;
        return LocaleController.formatString(R.string.NotificationActionPinnedTodoUser, new Object[]{r25, ((TLRPC.TL_messageMediaToDo) r42).todo.title.text});
    L640:
        if ((r42 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L648;
        if (TextUtils.isEmpty(r03.message) == true) goto L646;
        return LocaleController.formatString(R.string.NotificationActionPinnedTextUser, new Object[]{r25, "🖼 " + r18.messageOwner.message});
    L646:
        return LocaleController.formatString(R.string.NotificationActionPinnedPhotoUser, new Object[]{r25});
    L648:
        if ((r42 instanceof TLRPC.TL_messageMediaGame) == true) goto L650;
        CharSequence r05 = r18.messageText;
        if (r05 == null) goto L661;
        if (r05.length() <= 0) goto L661;
        CharSequence r06 = r18.messageText;
        if (r06.length() <= 20) goto L659;
        r06 = r06.subSequence(0, 20) + "...";
    L659:
        return LocaleController.formatString(R.string.NotificationActionPinnedTextUser, new Object[]{r25, r06});
    L661:
        return LocaleController.formatString(R.string.NotificationActionPinnedNoTextUser, new Object[]{r25});
    L650:
        return LocaleController.formatString(R.string.NotificationActionPinnedGameUser, new Object[]{r25});
    L663:
        return LocaleController.formatString(R.string.NotificationActionPinnedGeoUser, new Object[]{r25});
    L364:
        if (ChatObject.isChannel(r4) == true) goto L366;
    L367:
        MessageObject r113 = r34.replyMessageObject;
        if (r113 != null) goto L372;
        return LocaleController.formatString(R.string.NotificationActionPinnedNoText, new Object[]{r25, getTitle(r4)});
    L372:
        if (r113.isMusic() == false) goto L376;
        return LocaleController.formatString(R.string.NotificationActionPinnedMusic, new Object[]{r25, getTitle(r4)});
    L376:
        if (r113.isVideo() == false) goto L384;
        if (TextUtils.isEmpty(r113.messageOwner.message) == true) goto L382;
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r25, "📹 " + r113.messageOwner.message, getTitle(r4)});
    L382:
        return LocaleController.formatString(R.string.NotificationActionPinnedVideo, new Object[]{r25, getTitle(r4)});
    L384:
        if (r113.isGif() == false) goto L392;
        if (TextUtils.isEmpty(r113.messageOwner.message) == true) goto L390;
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r25, "🎬 " + r113.messageOwner.message, getTitle(r4)});
    L390:
        return LocaleController.formatString(R.string.NotificationActionPinnedGif, new Object[]{r25, getTitle(r4)});
    L392:
        if (r113.isVoice() == false) goto L396;
        return LocaleController.formatString(R.string.NotificationActionPinnedVoice, new Object[]{r25, getTitle(r4)});
    L396:
        if (r113.isRoundVideo() == false) goto L400;
        return LocaleController.formatString(R.string.NotificationActionPinnedRound, new Object[]{r25, getTitle(r4)});
    L400:
        if (r113.isSticker() == false) goto L402;
    L462:
        String r114 = r113.getStickerEmoji();
        if (r114 == null) goto L467;
        return LocaleController.formatString(R.string.NotificationActionPinnedStickerEmoji, new Object[]{r25, getTitle(r4), r114});
    L467:
        return LocaleController.formatString(R.string.NotificationActionPinnedSticker, new Object[]{r25, getTitle(r4)});
    L402:
        if (r113.isAnimatedSticker() == true) goto L462;
        TLRPC.Message r69 = r113.messageOwner;
        TLRPC.MessageMedia r92 = r69.media;
        if ((r92 instanceof TLRPC.TL_messageMediaDocument) == false) goto L413;
        if (TextUtils.isEmpty(r69.message) == true) goto L411;
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r25, "📎 " + r113.messageOwner.message, getTitle(r4)});
    L411:
        return LocaleController.formatString(R.string.NotificationActionPinnedFile, new Object[]{r25, getTitle(r4)});
    L413:
        if ((r92 instanceof TLRPC.TL_messageMediaGeo) == true) goto L461;
        if ((r92 instanceof TLRPC.TL_messageMediaVenue) == true) goto L461;
        if ((r92 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L422;
        return LocaleController.formatString(R.string.NotificationActionPinnedGeoLive, new Object[]{r25, getTitle(r4)});
    L422:
        if ((r92 instanceof TLRPC.TL_messageMediaContact) == false) goto L426;
        TLRPC.TL_messageMediaContact r93 = (TLRPC.TL_messageMediaContact) r92;
        return LocaleController.formatString(R.string.NotificationActionPinnedContact2, new Object[]{r25, getTitle(r4), ContactsController.formatName(r93.first_name, r93.last_name)});
    L426:
        if ((r92 instanceof TLRPC.TL_messageMediaPoll) == false) goto L434;
        TLRPC.TL_messageMediaPoll r94 = (TLRPC.TL_messageMediaPoll) r92;
        if (r94.poll.quiz == false) goto L432;
        return LocaleController.formatString(R.string.NotificationActionPinnedQuiz2, new Object[]{r25, getTitle(r4), r94.poll.question.text});
    L432:
        return LocaleController.formatString(R.string.NotificationActionPinnedPoll2, new Object[]{r25, getTitle(r4), r94.poll.question.text});
    L434:
        if ((r92 instanceof TLRPC.TL_messageMediaToDo) == false) goto L438;
        return LocaleController.formatString(R.string.NotificationActionPinnedTodo2, new Object[]{r25, getTitle(r4), ((TLRPC.TL_messageMediaToDo) r92).todo.title.text});
    L438:
        if ((r92 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L446;
        if (TextUtils.isEmpty(r69.message) == true) goto L444;
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r25, "🖼 " + r113.messageOwner.message, getTitle(r4)});
    L444:
        return LocaleController.formatString(R.string.NotificationActionPinnedPhoto, new Object[]{r25, getTitle(r4)});
    L446:
        if ((r92 instanceof TLRPC.TL_messageMediaGame) == true) goto L448;
        CharSequence r610 = r113.messageText;
        if (r610 == null) goto L459;
        if (r610.length() <= 0) goto L459;
        CharSequence r115 = r113.messageText;
        if (r115.length() <= 20) goto L457;
        r115 = r115.subSequence(0, 20) + "...";
    L457:
        return LocaleController.formatString(R.string.NotificationActionPinnedText, new Object[]{r25, r115, getTitle(r4)});
    L459:
        return LocaleController.formatString(R.string.NotificationActionPinnedNoText, new Object[]{r25, getTitle(r4)});
    L448:
        return LocaleController.formatString(R.string.NotificationActionPinnedGame, new Object[]{r25, getTitle(r4)});
    L461:
        return LocaleController.formatString(R.string.NotificationActionPinnedGeo, new Object[]{r25, getTitle(r4)});
    L366:
        if (r4.megagroup == false) goto L468;
    L671:
        if ((r54 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L687;
        String r07 = TlUtils.getThemeEmoticonOrGiftTitle(((TLRPC.TL_messageActionSetChatTheme) r54).theme);
        if (TextUtils.isEmpty(r07) == false) goto L681;
        if (r223 != r19) goto L679;
        return LocaleController.formatString(R.string.ChatThemeDisabledYou, new Object[0]);
    L679:
        return LocaleController.formatString(R.string.ChatThemeDisabled, new Object[]{r25, r07});
    L681:
        if (r223 != r19) goto L685;
        return LocaleController.formatString(R.string.ChatThemeChangedYou, new Object[]{r07});
    L685:
        return LocaleController.formatString(R.string.ChatThemeChangedTo, new Object[]{r25, r07});
    L687:
        if ((r54 instanceof TLRPC.TL_messageActionChatJoinedByRequest) == false) goto L691;
        return r34.messageText.toString();
    L691:
        if ((r54 instanceof TLRPC.TL_messageActionPrizeStars) == false) goto L703;
        TLRPC.TL_messageActionPrizeStars r510 = (TLRPC.TL_messageActionPrizeStars) r54;
        long r116 = DialogObject.getPeerDialogId(r510.boost_peer);
        if (r116 < 0) goto L695;
        String r08 = UserObject.getForcedFirstName(getMessagesController().getUser(Long.valueOf(r116)));
    L701:
        return LocaleController.formatPluralStringComma("BoostingReceivedStars", (int) r510.stars, new Object[]{r08});
    L695:
        TLRPC.Chat r117 = getMessagesController().getChat(Long.valueOf(-r116));
        if (r117 == null) goto L699;
        r63 = getTitle(r117);
    L699:
        r08 = r63;
        goto L701
    L703:
        if ((r54 instanceof TLRPC.TL_messageActionPaymentRefunded) == false) goto L707;
        return r34.messageText.toString();
    L707:
        if ((r54 instanceof TLRPC.TL_messageActionTodoCompletions) == false) goto L711;
        return r34.messageText.toString();
    L711:
        if ((r54 instanceof TLRPC.TL_messageActionTodoAppendTasks) == true) goto L713;
        return null;
    L713:
        return r34.messageText.toString();
    L716:
        if (r312.peer_id.channel_id == 0) goto L726;
        if (r4.megagroup == true) goto L726;
        if (r34.isVideoAvatar() == false) goto L724;
        return LocaleController.formatString(R.string.ChannelVideoEditNotification, new Object[]{getTitle(r4)});
    L724:
        return LocaleController.formatString(R.string.ChannelPhotoEditNotification, new Object[]{getTitle(r4)});
    L726:
        if (r34.isVideoAvatar() == false) goto L730;
        return LocaleController.formatString(R.string.NotificationEditedGroupVideo, new Object[]{r25, getTitle(r4)});
    L730:
        return LocaleController.formatString(R.string.NotificationEditedGroupPhoto, new Object[]{r25, getTitle(r4)});
    L192:
        return r34.messageText.toString();
    L732:
        return LocaleController.formatString(R.string.NotificationContactJoined, new Object[]{r25});
    L734:
        if (r34.isMediaEmpty() == false) goto L742;
        if (TextUtils.isEmpty(r34.messageOwner.message) == true) goto L740;
        return replaceSpoilers(r34);
    L740:
        return LocaleController.getString(R.string.Message);
    L742:
        if (r34.type != 29) goto L777;
        if ((MessageObject.getMedia(r34) instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L777;
        TLRPC.TL_messageMediaPaidMedia r09 = (TLRPC.TL_messageMediaPaidMedia) MessageObject.getMedia(r34);
        int r118 = r09.extended_media.size();
        int r29 = 0;
        boolean r315 = false;
    L746:
        if (r29 >= r118) goto L763;
        TLRPC.MessageExtendedMedia r44 = r09.extended_media.get(r29);
        if ((r44 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L756;
        TLRPC.MessageMedia r316 = ((TLRPC.TL_messageExtendedMedia) r44).media;
        if ((r316 instanceof TLRPC.TL_messageMediaDocument) == true) goto L752;
    L754:
        r315 = false;
    L760:
        if (r315 == true) goto L763;
        r29 = r29 + 1;
        goto L746
    L752:
        if (MessageObject.isVideoDocument(r316.document) == false) goto L754;
    L753:
        r315 = true;
        goto L760
    L756:
        if ((r44 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L760;
        if ((((TLRPC.TL_messageExtendedMediaPreview) r44).flags & 4) == 0) goto L754;
    L763:
        int r010 = R.string.AttachPaidMedia;
        if (r118 != 1) goto L769;
        if (r315 == false) goto L767;
        int r119 = R.string.AttachVideo;
    L768:
        String r120 = LocaleController.getString(r119);
    L775:
        return LocaleController.formatString(r010, new Object[]{r120});
    L767:
        r119 = R.string.AttachPhoto;
        goto L768
    L769:
        if (r315 == false) goto L772;
        String r210 = "Media";
    L773:
        r120 = LocaleController.formatPluralString(r210, r118, new Object[0]);
        goto L775
    L772:
        r210 = "Photos";
    L777:
        if (r34.isVoiceOnce() == false) goto L781;
        return LocaleController.getString(R.string.AttachOnceAudio);
    L781:
        if (r34.isRoundOnce() == true) goto L783;
        TLRPC.Message r211 = r34.messageOwner;
        if ((r211.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L797;
        if (TextUtils.isEmpty(r211.message) == true) goto L791;
        return "🖼 " + replaceSpoilers(r34);
    L791:
        if (r34.messageOwner.media.ttl_seconds == 0) goto L795;
        return LocaleController.getString(R.string.AttachDestructingPhoto);
    L795:
        return LocaleController.getString(R.string.AttachPhoto);
    L797:
        if (r34.isVideo() == false) goto L809;
        if (TextUtils.isEmpty(r34.messageOwner.message) == true) goto L803;
        return "📹 " + replaceSpoilers(r34);
    L803:
        if (r34.messageOwner.media.ttl_seconds == 0) goto L807;
        return LocaleController.getString(R.string.AttachDestructingVideo);
    L807:
        return LocaleController.getString(R.string.AttachVideo);
    L809:
        if (r34.isGame() == false) goto L813;
        return LocaleController.getString(R.string.AttachGame);
    L813:
        if (r34.isVoice() == false) goto L817;
        return LocaleController.getString(R.string.AttachAudio);
    L817:
        if (r34.isRoundVideo() == false) goto L821;
        return LocaleController.getString(R.string.AttachRound);
    L821:
        if (r34.isMusic() == true) goto L823;
        TLRPC.MessageMedia r212 = r34.messageOwner.media;
        if ((r212 instanceof TLRPC.TL_messageMediaContact) == false) goto L829;
        return LocaleController.getString(R.string.AttachContact);
    L829:
        if ((r212 instanceof TLRPC.TL_messageMediaPoll) == false) goto L837;
        if (((TLRPC.TL_messageMediaPoll) r212).poll.quiz == false) goto L835;
        return LocaleController.getString(R.string.QuizPoll);
    L835:
        return LocaleController.getString(R.string.Poll);
    L837:
        if ((r212 instanceof TLRPC.TL_messageMediaToDo) == false) goto L841;
        return LocaleController.getString(R.string.Todo);
    L841:
        if ((r212 instanceof TLRPC.TL_messageMediaGiveaway) == false) goto L845;
        return LocaleController.getString(R.string.BoostingGiveaway);
    L845:
        if ((r212 instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L849;
        return LocaleController.getString(R.string.BoostingGiveawayResults);
    L849:
        if ((r212 instanceof TLRPC.TL_messageMediaGeo) == true) goto L903;
        if ((r212 instanceof TLRPC.TL_messageMediaVenue) == true) goto L903;
        if ((r212 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L858;
        return LocaleController.getString(R.string.AttachLiveLocation);
    L858:
        if ((r212 instanceof TLRPC.TL_messageMediaDocument) == false) goto L885;
        if (r34.isSticker() == false) goto L862;
    L878:
        String r011 = r34.getStickerEmoji();
        if (r011 == null) goto L883;
        return r011 + " " + LocaleController.getString(R.string.AttachSticker);
    L883:
        return LocaleController.getString(R.string.AttachSticker);
    L862:
        if (r34.isAnimatedSticker() == true) goto L878;
        boolean r213 = r34.isGif();
        TLRPC.Message r317 = r34.messageOwner;
        if (r213 == false) goto L873;
        if (TextUtils.isEmpty(r317.message) == true) goto L871;
        return "🎬 " + replaceSpoilers(r34);
    L871:
        return LocaleController.getString(R.string.AttachGif);
    L873:
        if (TextUtils.isEmpty(r317.message) == true) goto L877;
        return "📎 " + replaceSpoilers(r34);
    L877:
        return LocaleController.getString(R.string.AttachDocument);
    L885:
        if ((r212 instanceof TLRPC.TL_messageMediaStory) == false) goto L897;
        if (((TLRPC.TL_messageMediaStory) r212).via_mention == false) goto L895;
        int r012 = R.string.StoryNotificationMention;
        String r121 = r35[0];
        if (r121 == null) goto L893;
        r63 = r121;
    L893:
        return LocaleController.formatString(r012, new Object[]{r63});
    L895:
        return LocaleController.getString(R.string.Story);
    L897:
        if (TextUtils.isEmpty(r34.messageText) == true) goto L901;
        return replaceSpoilers(r34);
    L901:
        return LocaleController.getString(R.string.Message);
    L903:
        return LocaleController.getString(R.string.AttachLocation);
    L823:
        return LocaleController.getString(R.string.AttachMusic);
    L783:
        return LocaleController.getString(R.string.AttachOnceRound);
    L155:
        if (r5 == 0) goto L904;
        if (r52 == false) goto L158;
    L159:
        if (r52 == false) goto L904;
        if (r12.getBoolean("EnablePreviewChannel", r62) == false) goto L904;
    L158:
        if (r12.getBoolean("EnablePreviewGroup", r62) == true) goto L162;
    L154:
        r62 = true;
    L904:
        if (r36 == null) goto L907;
        r36[0] = false;
    L907:
        return LocaleController.getString(R.string.Message);
    L145:
        if (r6.rich_message == null) goto L148;
        return r34.messageText.toString();
    L139:
        if (r4.megagroup == true) goto L141;
        r52 = true;
        goto L142
    L131:
        r4 = null;
        goto L133
    L91:
        r222 = r24;
        if (Build.VERSION.SDK_INT <= 27) goto L94;
        r35[0] = r222;
        goto L95
    L94:
        r35[0] = null;
        goto L95
    L96:
        r25 = null;
        goto L97
    L98:
        r223 = r3;
        TLRPC.Chat r214 = getMessagesController().getChat(Long.valueOf(-r9));
        if (r214 == null) goto L101;
        r25 = getTitle(r214);
        r35[0] = r25;
        goto L102
    L101:
        r25 = null;
        goto L102
    L67:
        if (r9 != r19) goto L70;
        r9 = r34.getFromChatId();
        goto L70
    L17:
        if (r5 == 0) goto L19;
    L30:
        if (r5 != 0) goto L32;
    L60:
        return replaceSpoilers(r34);
    L32:
        if (r34.messageOwner.peer_id.channel_id != 0) goto L34;
    L39:
        r35[0] = r34.localUserName;
    L40:
        if (r13 == true) goto L42;
    L49:
        if (r36 == null) goto L52;
        r36[0] = false;
    L52:
        if (r34.messageOwner.peer_id.channel_id == 0) goto L58;
        if (r34.isSupergroup() == true) goto L58;
        return LocaleController.formatString(R.string.ChannelMessageNoText, new Object[]{r34.localName});
    L58:
        return LocaleController.formatString(R.string.NotificationMessageGroupNoText, new Object[]{r34.localUserName, r34.localName});
    L42:
        if (r34.localChannel == true) goto L46;
        if (r12.getBoolean("EnablePreviewGroup", true) == false) goto L49;
    L46:
        if (r34.localChannel == false) goto L60;
        if (r12.getBoolean("EnablePreviewChannel", true) == true) goto L60;
    L34:
        if (r34.isSupergroup() == true) goto L39;
        if (Build.VERSION.SDK_INT <= 27) goto L40;
        r35[0] = r34.localName;
        goto L40
    L19:
        if (r9 == 0) goto L30;
        if (Build.VERSION.SDK_INT <= 27) goto L23;
        r35[0] = r34.localName;
    L23:
        if (r13 == true) goto L25;
    L26:
        if (r36 == null) goto L29;
        r36[0] = false;
    L29:
        return LocaleController.getString(R.string.Message);
    L25:
        if (r12.getBoolean("EnablePreviewAll", true) == true) goto L60;
    L909:
        return LocaleController.getString(R.string.NotificationHiddenMessage);
    }

    public int getTotalUnreadCount() {
        return this.total_unread_count;
    }

    public boolean hasMessagesToReply() {
        int r1 = 0;
    L4:
        if (r1 >= this.pushMessages.size()) goto L25;
        MessageObject r2 = this.pushMessages.get(r1);
        long r3 = r2.getDialogId();
        if (r2.isReactionPush == true) goto L24;
        TLRPC.Message r5 = r2.messageOwner;
        if (r5.mentioned == false) goto L12;
        if ((r5.action instanceof TLRPC.TL_messageActionPinMessage) == true) goto L24;
    L12:
        if (DialogObject.isEncryptedDialog(r3) == true) goto L24;
        if (r2.messageOwner.peer_id.channel_id == 0) goto L18;
        if (r2.isSupergroup() == false) goto L24;
    L18:
        if (r3 == UserObject.VERIFY) goto L24;
        if (r3 == UserObject.OAUTH) goto L24;
        return true;
    L24:
        r1 = r1 + 1;
        goto L4
    L25:
        return false;
    }

    public void hideNotifications() {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda13(this));
    }

    public boolean isGlobalNotificationsEnabled(int r4) {
        if (r4 != 4) goto L7;
        return getAccountInstance().getNotificationsSettings().getBoolean("EnableReactionsMessages", true);
    L7:
        if (r4 != 5) goto L11;
        return getAccountInstance().getNotificationsSettings().getBoolean("EnableReactionsStories", true);
    L11:
        if (r4 != 3) goto L15;
        return getAccountInstance().getNotificationsSettings().getBoolean("EnableAllStories", true);
    L15:
        if (getAccountInstance().getNotificationsSettings().getInt(getGlobalNotificationsKey(r4), 0) >= getConnectionsManager().getCurrentTime()) goto L17;
        return true;
    L17:
        return false;
    }

    public boolean isGlobalNotificationsEnabled(long r1, Boolean r3, boolean r4, boolean r5) {
        if (r4 == false) goto L4;
        int r12 = 4;
    L21:
        return isGlobalNotificationsEnabled(r12);
    L4:
        if (r5 == false) goto L7;
        r12 = 5;
        goto L21
    L7:
        if (DialogObject.isChatDialog(r1) == true) goto L9;
        r12 = 1;
        goto L21
    L9:
        if (r3 != null) goto L11;
        TLRPC.Chat r13 = getMessagesController().getChat(Long.valueOf(-r1));
        if (ChatObject.isChannel(r13) == true) goto L17;
    L13:
        r12 = 0;
        goto L21
    L17:
        if (r13.megagroup == true) goto L13;
    L12:
        r12 = 2;
        goto L21
    L11:
        if (r3.booleanValue() == false) goto L13;
        goto L12
    }

    public boolean isGlobalNotificationsEnabled(long r7, boolean r9, boolean r10) {
        return isGlobalNotificationsEnabled(r7, null, r9, r10);
    }

    public void loadTopicsNotificationsExceptions(long r3, Consumer<HashSet<Integer>> r5) {
        getMessagesStorage().getStorageQueue().postRunnable(new NotificationsController$$ExternalSyntheticLambda54(this, r3, r5));
    }

    public void muteDialog(long r8, long r10, boolean r12) {
        int r0 = this.currentAccount;
        if (r12 == false) goto L6;
        getInstance(r0).muteUntil(r8, r10, Integer.MAX_VALUE);
        return;
    L6:
        boolean r122 = getInstance(r0).isGlobalNotificationsEnabled(r8, false, false);
        if (r10 == 0) goto L9;
        boolean r4 = true;
    L10:
        SharedPreferences.Editor r5 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        if (r122 == false) goto L14;
        if (r4 == true) goto L14;
        r5.remove(NotificationsSettingsFacade.PROPERTY_NOTIFY + getSharedPrefKey(r8, r10));
    L15:
        if (r10 != 0) goto L19;
        getMessagesStorage().setDialogFlags(r8, 0);
        TLRPC.Dialog r123 = getMessagesController().dialogs_dict.get(r8);
        if (r123 == null) goto L19;
        r123.notify_settings = new TLRPC.TL_peerNotifySettings();
    L19:
        r5.apply();
        updateServerNotificationsSettings(r8, r10);
        return;
    L14:
        r5.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + getSharedPrefKey(r8, r10), 0);
        goto L15
    L9:
        r4 = false;
        goto L10
    }

    public void muteUntil(long r17, long r19, int r21) {
        if (r17 == 0) goto L25;
        SharedPreferences.Editor r8 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        if (r19 == 0) goto L7;
        boolean r11 = true;
    L8:
        boolean r10 = getInstance(this.currentAccount).isGlobalNotificationsEnabled(r17, false, false);
        String r12 = getSharedPrefKey(r17, r19);
        if (r21 != Integer.MAX_VALUE) goto L14;
        if (r10 == true) goto L13;
        if (r11 == true) goto L13;
        r8.remove(NotificationsSettingsFacade.PROPERTY_NOTIFY + r12);
        long r6 = 0;
    L15:
        r8.apply();
        if (r19 != 0) goto L23;
        getInstance(this.currentAccount).removeNotificationsForDialog(r17);
        MessagesStorage.getInstance(this.currentAccount).setDialogFlags(r17, r6);
        TLRPC.Dialog r62 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(r17);
        if (r62 == null) goto L23;
        TLRPC.TL_peerNotifySettings r7 = new TLRPC.TL_peerNotifySettings();
        r62.notify_settings = r7;
        if (r21 != Integer.MAX_VALUE) goto L22;
        if (r10 == false) goto L23;
    L22:
        r7.mute_until = r21;
    L23:
        getInstance(this.currentAccount).updateServerNotificationsSettings(r17, r19);
        return;
    L13:
        r8.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + r12, 2);
        r6 = 1;
        goto L15
    L14:
        r8.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + r12, 3);
        r8.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL + r12, getConnectionsManager().getCurrentTime() + r21);
        r6 = (((long) r21) << 32) | 1;
        goto L15
    L7:
        r11 = false;
        goto L8
    }

    public void playOutChatSound() {
        if (this.inChatSoundEnabled == true) goto L5;
        return;
    L5:
        if (MediaController.getInstance().isRecordingAudio() == true) goto L17;
        if (audioManager.getRingerMode() == 0) goto L18;
    L12:
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda42(this));
        return;
    L18:
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    public void processDeleteStory(long r3, int r5) {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda23(this, r3, r5));
    }

    public void processDialogsUpdateRead(LongSparseIntArray r4) {
        ArrayList r0 = new ArrayList();
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda25(this, r4, r0));
    }

    public void processEditedMessages(LongSparseArray<ArrayList<MessageObject>> r8) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (r8.size() == 0) goto L38;
        int r1 = 0;
    L8:
        if (r1 >= r8.size()) goto L27;
        ArrayList<MessageObject> r2 = r8.valueAt(r1);
        if (r2 == null) goto L26;
        int r3 = 0;
    L13:
        if (r3 >= r2.size()) goto L26;
        MessageObject r4 = r2.get(r3);
        if (r4 == null) goto L25;
        TLRPC.Message r5 = r4.messageOwner;
        if (r5 == null) goto L25;
        TLRPC.MessageAction r52 = r5.action;
        if ((r52 instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L25;
        TLRPC.TL_messageActionConferenceCall r53 = (TLRPC.TL_messageActionConferenceCall) r52;
        if (r53.active == false) goto L23;
    L24:
        VoIPGroupNotification.hide(ApplicationLoader.applicationContext, this.currentAccount, r4.getId());
        goto L25
    L23:
        if (r53.missed == true) goto L24;
    L25:
        r3 = r3 + 1;
    L26:
        r1 = r1 + 1;
        goto L8
    L27:
        new ArrayList(0);
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda59(this, r8));
        return;
    }

    public void processIgnoreStories() {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda36(this));
    }

    public void processIgnoreStories(long r3) {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda19(this, r3));
    }

    public void processIgnoreStoryReactions() {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda5(this));
    }

    public void processLoadedUnreadMessages(LongSparseArray<Integer> r7, ArrayList<TLRPC.Message> r8, ArrayList<MessageObject> r9, ArrayList<TLRPC.User> r10, ArrayList<TLRPC.Chat> r11, ArrayList<TLRPC.EncryptedChat> r12, Collection<StoryNotification> r13) {
        getMessagesController().putUsers(r10, true);
        getMessagesController().putChats(r11, true);
        getMessagesController().putEncryptedChats(r12, true);
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda21(this, r8, r7, r9, r13));
    }

    public void processNewMessages(ArrayList<MessageObject> r21, boolean r22, boolean r23, CountDownLatch r24) {
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        StringBuilder r0 = new StringBuilder("NotificationsController: processNewMessages msgs.size()=");
        if (r21 != null) goto L7;
        Object r3 = "null";
    L8:
        r0.append(r3);
        r0.append(" isLast=");
        boolean r5 = r22;
        r0.append(r5);
        r0.append(" isFcm=");
        boolean r4 = r23;
        r0.append(r4);
        r0.append(")");
        FileLog.d(r0.toString());
    L11:
        if (r21 == null) goto L43;
        int r32 = 0;
    L14:
        if (r32 >= r21.size()) goto L43;
        MessageObject r6 = r21.get(r32);
        if (r6 == null) goto L41;
        if (r6.messageOwner == null) goto L41;
        if (r6.isOutOwner() == true) goto L41;
        TLRPC.MessageAction r7 = r6.messageOwner.action;
        if ((r7 instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L41;
        TLRPC.TL_messageActionConferenceCall r72 = (TLRPC.TL_messageActionConferenceCall) r7;
        if (r72.active == false) goto L26;
    L40:
        VoIPGroupNotification.hide(ApplicationLoader.applicationContext, this.currentAccount, r6.getId());
        goto L41
    L26:
        if (r72.missed == true) goto L40;
        if ((getConnectionsManager().getCurrentTime() - r6.messageOwner.date) >= (getMessagesController().callRingTimeout / 1000)) goto L40;
        HashSet r8 = new HashSet();
        r8.add(Long.valueOf(r6.getDialogId()));
        ArrayList<TLRPC.Peer> r9 = r72.other_participants;
        int r10 = r9.size();
        int r11 = 0;
    L30:
        if (r11 >= r10) goto L32;
        TLRPC.Peer r12 = r9.get(r11);
        r11 = r11 + 1;
        r8.add(Long.valueOf(DialogObject.getPeerDialogId(r12)));
        goto L30
    L32:
        StringBuilder r92 = new StringBuilder();
        Iterator r82 = r8.iterator();
    L34:
        if (r82.hasNext() == false) goto L39;
        long r102 = ((Long) r82.next()).longValue();
        if (r92.length() <= 0) goto L38;
        r92.append(", ");
    L38:
        r92.append(DialogObject.getShortName(this.currentAccount, r102));
        goto L34
    L39:
        VoIPGroupNotification.request(ApplicationLoader.applicationContext, this.currentAccount, r6.getDialogId(), r92.toString(), r72.call_id, r6.getId(), r72.video);
        r21.remove(r32);
        r32 = r32 - 1;
    L41:
        r32 = r32 + 1;
    L43:
        if (r21.isEmpty() == false) goto L47;
        if (r24 == null) goto L60;
        r24.countDown();
        return;
    L60:
        return;
    L47:
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda50(this, r21, new ArrayList(0), r4, r5, r24));
        return;
    L7:
        r3 = Integer.valueOf(r21.size());
        goto L8
    L9:
        r5 = r22;
        r4 = r23;
        goto L11
    }

    public void processReadMessages(LongSparseIntArray r11, long r12, int r14, int r15, boolean r16) {
        ArrayList r3 = new ArrayList(0);
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda52(this, r11, r3, r12, r15, r14, r16));
    }

    public void processReadStories() {
    }

    public void processReadStories(long r3, int r5) {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda41(this, r3, r5));
    }

    public void processSeenStoryReactions(long r3, int r5) {
        if (r3 == getUserConfig().getClientUserId()) goto L5;
        return;
    L5:
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda24(this, r5));
    }

    public void removeDeletedHisoryFromNotifications(LongSparseIntArray r4) {
        ArrayList r0 = new ArrayList(0);
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda4(this, r4, r0));
    }

    public void removeDeletedMessagesFromNotifications(LongSparseArray<ArrayList<Integer>> r4, boolean r5) {
        ArrayList r0 = new ArrayList(0);
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda53(this, r4, r5, r0));
    }

    public void removeNotificationsForDialog(long r8) {
        processReadMessages(null, r8, 0, Integer.MAX_VALUE, false);
        LongSparseIntArray r7 = new LongSparseIntArray();
        r7.put(r8, 0);
        processDialogsUpdateRead(r7);
    }

    public void repeatNotificationMaybe() {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda16(this));
    }

    public void setDialogNotificationsSettings(long r9, long r11, int r13) {
        SharedPreferences.Editor r0 = getAccountInstance().getNotificationsSettings().edit();
        TLRPC.Dialog r1 = MessagesController.getInstance(UserConfig.selectedAccount).dialogs_dict.get(r9);
        if (r13 == 4) goto L5;
        int r2 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
        if (r13 != 0) goto L15;
        r2 = r2 + 3600;
    L21:
        long r6 = 1;
        if (r13 != 3) goto L24;
        r0.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + getSharedPrefKey(r9, r11), 2);
    L25:
        getInstance(UserConfig.selectedAccount).removeNotificationsForDialog(r9);
        MessagesStorage.getInstance(UserConfig.selectedAccount).setDialogFlags(r9, r6);
        if (r1 == null) goto L28;
        TLRPC.TL_peerNotifySettings r132 = new TLRPC.TL_peerNotifySettings();
        r1.notify_settings = r132;
        r132.mute_until = r2;
    L28:
        r0.apply();
        updateServerNotificationsSettings(r9, r11);
        return;
    L24:
        r0.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + getSharedPrefKey(r9, r11), 3);
        r0.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL + getSharedPrefKey(r9, r11), r2);
        r6 = 1 | (((long) r2) << 32);
        goto L25
    L15:
        if (r13 != 1) goto L17;
        r2 = r2 + 28800;
        goto L21
    L17:
        if (r13 != 2) goto L19;
        r2 = r2 + 172800;
        goto L21
    L19:
        if (r13 != 3) goto L21;
        r2 = Integer.MAX_VALUE;
        goto L21
    L5:
        if (isGlobalNotificationsEnabled(r9, false, false) == false) goto L7;
        r0.remove(NotificationsSettingsFacade.PROPERTY_NOTIFY + getSharedPrefKey(r9, r11));
    L8:
        getMessagesStorage().setDialogFlags(r9, 0);
        if (r1 == null) goto L28;
        r1.notify_settings = new TLRPC.TL_peerNotifySettings();
        goto L28
    L7:
        r0.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + getSharedPrefKey(r9, r11), 0);
        goto L8
    }

    public void setGlobalNotificationsEnabled(int r3, int r4) {
        getAccountInstance().getNotificationsSettings().edit().putInt(getGlobalNotificationsKey(r3), r4).apply();
        updateServerNotificationsSettings(r3);
        getMessagesStorage().updateMutedDialogsFiltersCounters();
        deleteNotificationChannelGlobal(r3);
    }

    public void setInChatSoundEnabled(boolean r1) {
        this.inChatSoundEnabled = r1;
    }

    public void setLastOnlineFromOtherDevice(int r3) {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda32(this, r3));
    }

    public void setOpenedDialogId(long r8, long r10) {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda12(this, r8, r10));
    }

    public void setOpenedInBubble(long r3, boolean r5) {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda38(this, r5, r3));
    }

    public void showNotifications() {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda57(this));
    }

    public void updateBadge() {
        notificationsQueue.postRunnable(new NotificationsController$$ExternalSyntheticLambda37(this));
    }

    public void updateServerNotificationsSettings(int r7) {
        SharedPreferences r0 = getAccountInstance().getNotificationsSettings();
        if (r7 != 4) goto L5;
    L21:
        TL_account.setReactionsNotifySettings r72 = new TL_account.setReactionsNotifySettings();
        r72.settings = new TL_account.TL_reactionsNotifySettings();
        if (r0.getBoolean("EnableReactionsMessages", true) == false) goto L28;
        r72.settings.flags |= 1;
        boolean r1 = r0.getBoolean("EnableReactionsMessagesContacts", false);
        TL_account.TL_reactionsNotifySettings r4 = r72.settings;
        if (r1 == false) goto L26;
        r4.messages_notify_from = new TL_account.TL_reactionNotificationsFromContacts();
        goto L28
    L26:
        r4.messages_notify_from = new TL_account.TL_reactionNotificationsFromAll();
    L28:
        if (r0.getBoolean("EnableReactionsStories", true) == false) goto L33;
        r72.settings.flags |= 2;
        boolean r12 = r0.getBoolean("EnableReactionsStoriesContacts", false);
        TL_account.TL_reactionsNotifySettings r2 = r72.settings;
        if (r12 == false) goto L32;
        r2.stories_notify_from = new TL_account.TL_reactionNotificationsFromContacts();
        goto L33
    L32:
        r2.stories_notify_from = new TL_account.TL_reactionNotificationsFromAll();
    L33:
        r72.settings.show_previews = r0.getBoolean("EnableReactionsPreview", true);
        r72.settings.sound = getInputSound(r0, "ReactionSound", "ReactionSoundDocId", "ReactionSoundPath");
        getConnectionsManager().sendRequest(r72, new NotificationsController$$ExternalSyntheticLambda30());
        return;
    L5:
        if (r7 == 5) goto L21;
        TL_account.updateNotifySettings r42 = new TL_account.updateNotifySettings();
        TLRPC.TL_inputPeerNotifySettings r5 = new TLRPC.TL_inputPeerNotifySettings();
        r42.settings = r5;
        r5.flags = 5;
        if (r7 != 0) goto L10;
        r42.peer = new TLRPC.TL_inputNotifyChats();
        r42.settings.mute_until = r0.getInt("EnableGroup2", 0);
        r42.settings.show_previews = r0.getBoolean("EnablePreviewGroup", true);
        TLRPC.TL_inputPeerNotifySettings r73 = r42.settings;
        r73.flags |= 8;
        r73.sound = getInputSound(r0, "GroupSound", "GroupSoundDocId", "GroupSoundPath");
    L19:
        getConnectionsManager().sendRequest(r42, new NotificationsController$$ExternalSyntheticLambda31());
        return;
    L10:
        if (r7 != 1) goto L12;
    L15:
        r42.peer = new TLRPC.TL_inputNotifyUsers();
        r42.settings.mute_until = r0.getInt("EnableAll2", 0);
        r42.settings.show_previews = r0.getBoolean("EnablePreviewAll", true);
        TLRPC.TL_inputPeerNotifySettings r74 = r42.settings;
        r74.flags |= 128;
        r74.stories_hide_sender = r0.getBoolean("EnableHideStoriesSenders", false);
        if (r0.contains("EnableAllStories") == false) goto L18;
        TLRPC.TL_inputPeerNotifySettings r13 = r42.settings;
        r13.flags |= 64;
        r13.stories_muted = !r0.getBoolean("EnableAllStories", true);
    L18:
        TLRPC.TL_inputPeerNotifySettings r75 = r42.settings;
        r75.flags |= 8;
        r75.sound = getInputSound(r0, "GlobalSound", "GlobalSoundDocId", "GlobalSoundPath");
        TLRPC.TL_inputPeerNotifySettings r76 = r42.settings;
        r76.flags |= 256;
        r76.stories_sound = getInputSound(r0, "StoriesSound", "StoriesSoundDocId", "StoriesSoundPath");
        goto L19
    L12:
        if (r7 == 3) goto L15;
        r42.peer = new TLRPC.TL_inputNotifyBroadcasts();
        r42.settings.mute_until = r0.getInt("EnableChannel2", 0);
        r42.settings.show_previews = r0.getBoolean("EnablePreviewChannel", true);
        TLRPC.TL_inputPeerNotifySettings r77 = r42.settings;
        r77.flags |= 8;
        r77.sound = getInputSound(r0, "ChannelSound", "ChannelSoundDocId", "ChannelSoundPath");
        goto L19
    }

    public void updateServerNotificationsSettings(long r7, long r9) {
        updateServerNotificationsSettings(r7, r9, true);
    }

    public void updateServerNotificationsSettings(long r10, long r12, boolean r14) {
        int r0 = 0;
        if (r14 == false) goto L6;
        getNotificationCenter().postNotificationName(NotificationCenter.notificationsSettingsUpdated, new Object[0]);
    L6:
        if (DialogObject.isEncryptedDialog(r10) == false) goto L8;
        return;
    L8:
        SharedPreferences r142 = getAccountInstance().getNotificationsSettings();
        TL_account.updateNotifySettings r1 = new TL_account.updateNotifySettings();
        r1.settings = new TLRPC.TL_inputPeerNotifySettings();
        String r2 = getSharedPrefKey(r10, r12);
        TLRPC.TL_inputPeerNotifySettings r3 = r1.settings;
        r3.flags |= 1;
        r3.show_previews = r142.getBoolean(NotificationsSettingsFacade.PROPERTY_CONTENT_PREVIEW + r2, true);
        TLRPC.TL_inputPeerNotifySettings r32 = r1.settings;
        r32.flags = r32.flags | 2;
        r32.silent = r142.getBoolean(NotificationsSettingsFacade.PROPERTY_SILENT + r2, false);
        if (r142.contains(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r2) == false) goto L11;
        TLRPC.TL_inputPeerNotifySettings r33 = r1.settings;
        r33.flags |= 64;
        r33.stories_muted = !r142.getBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r2, true);
    L11:
        int r22 = r142.getInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + getSharedPrefKey(r10, r12), -1);
        if (r22 == (-1)) goto L20;
        TLRPC.TL_inputPeerNotifySettings r34 = r1.settings;
        r34.flags |= 4;
        if (r22 != 3) goto L16;
        r34.mute_until = r142.getInt(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL + getSharedPrefKey(r10, r12), 0);
        goto L20
    L16:
        if (r22 != 2) goto L19;
        r0 = Integer.MAX_VALUE;
    L19:
        r34.mute_until = r0;
    L20:
        long r4 = r142.getLong("sound_document_id_" + getSharedPrefKey(r10, r12), 0);
        String r02 = r142.getString("sound_path_" + getSharedPrefKey(r10, r12), null);
        TLRPC.TL_inputPeerNotifySettings r7 = r1.settings;
        r7.flags = r7.flags | 8;
        if (r4 == 0) goto L23;
        TLRPC.TL_notificationSoundRingtone r143 = new TLRPC.TL_notificationSoundRingtone();
        r143.id = r4;
        r1.settings.sound = r143;
    L30:
        if (r12 == 0) goto L35;
        if (r10 == getUserConfig().getClientUserId()) goto L35;
        TLRPC.TL_inputNotifyForumTopic r144 = new TLRPC.TL_inputNotifyForumTopic();
        r144.peer = getMessagesController().getInputPeer(r10);
        r144.top_msg_id = (int) r12;
        r1.peer = r144;
    L38:
        getConnectionsManager().sendRequest(r1, new NotificationsController$$ExternalSyntheticLambda55());
        return;
    L35:
        if (ChatObject.isCommunity(this.currentAccount, r10) == false) goto L37;
        TLRPC.TL_inputNotifyCommunity r122 = new TLRPC.TL_inputNotifyCommunity();
        r122.community = getMessagesController().getInputChannel(-r10);
        r1.peer = r122;
        goto L38
    L37:
        TLRPC.TL_inputNotifyPeer r123 = new TLRPC.TL_inputNotifyPeer();
        r123.peer = getMessagesController().getInputPeer(r10);
        r1.peer = r123;
        goto L38
    L23:
        if (r02 != null) goto L25;
        r7.sound = new TLRPC.TL_notificationSoundDefault();
        goto L30
    L25:
        if (r02.equalsIgnoreCase("NoSound") == false) goto L27;
        r1.settings.sound = new TLRPC.TL_notificationSoundNone();
        goto L30
    L27:
        TLRPC.TL_notificationSoundLocal r42 = new TLRPC.TL_notificationSoundLocal();
        r42.title = r142.getString("sound_" + getSharedPrefKey(r10, r12), null);
        r42.data = r02;
        r1.settings.sound = r42;
        goto L30
    }
}
