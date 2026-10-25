package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.util.SparseArray;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.collection.LongSparseArray;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.hooks.PluginsHooks;
import com.exteragram.messenger.utils.MediaUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.mvel2.MVEL;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.support.SparseLongArray;
import org.telegram.messenger.utils.EphemeralMessagesHelper;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_ephemeral;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.AnimatedFileNative;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.Reactions.ReactionsUtils;
import org.telegram.ui.Components.poll.PollAttachedMedia;
import org.telegram.ui.Components.poll.PollAttachedMediaPack;
import org.telegram.ui.Components.poll.PollSendParams;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaFile;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaGallery;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaLink;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaLocation;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaMusic;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaSticker;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.OAuthSheet;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.TON.TONIntroActivity;
import org.telegram.ui.TwoStepVerificationActivity;
import org.telegram.ui.TwoStepVerificationSetupActivity;
import org.telegram.ui.bots.BotWebViewSheet;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes.dex */
public class SendMessagesHelper extends BaseController implements NotificationCenter.NotificationCenterDelegate {
    private static final int ERROR_TYPE_FILE_TOO_LARGE = 2;
    private static final int ERROR_TYPE_UNSUPPORTED = 1;
    private static volatile SendMessagesHelper[] Instance = null;
    public static final int MEDIA_TYPE_DICE = 11;
    public static final int MEDIA_TYPE_RICH = 13;
    public static final int MEDIA_TYPE_STORY = 12;
    private static DispatchQueue mediaSendQueue;
    private static ThreadPoolExecutor mediaSendThreadPool;
    private final HashMap<String, ArrayList<DelayedMessage>> delayedMessages;
    private final SparseArray<TLRPC.Message> editingMessages;
    private final PluginsHooks hooks;
    private final HashMap<String, ImportingHistory> importingHistoryFiles;
    private final LongSparseArray<ImportingHistory> importingHistoryMap;
    private final HashMap<String, ImportingStickers> importingStickersFiles;
    private final HashMap<String, ImportingStickers> importingStickersMap;
    private LocationProvider locationProvider;
    private final SparseArray<TLRPC.Message> sendingMessages;
    private final LongSparseArray<Integer> sendingMessagesIdDialogs;
    private final SparseArray<MessageObject> unsentMessages;
    private final SparseArray<TLRPC.Message> uploadMessages;
    private final LongSparseArray<Integer> uploadingMessagesIdDialogs;
    private final LongSparseArray<Long> voteSendTime;
    private final HashMap<String, Boolean> waitingForCallback;
    private final HashMap<String, List<String>> waitingForCallbackMap;
    private final HashMap<String, MessageObject> waitingForLocation;
    private final HashMap<Integer, Boolean> waitingForTodoUpdate;
    private final HashMap<String, byte[]> waitingForVote;

    /* renamed from: org.telegram.messenger.SendMessagesHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements LocationProvider.LocationProviderDelegate {
        final /* synthetic */ SendMessagesHelper this$0;

        public AnonymousClass1(SendMessagesHelper r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.SendMessagesHelper.LocationProvider.LocationProviderDelegate
        public void onLocationAcquired(Location r2) {
            SendMessagesHelper.m4951$$Nest$msendLocation(this.this$0, r2);
            SendMessagesHelper.m4949$$Nest$fgetwaitingForLocation(this.this$0).clear();
        }

        @Override // org.telegram.messenger.SendMessagesHelper.LocationProvider.LocationProviderDelegate
        public void onUnableLocationAcquire() {
            HashMap r0 = new HashMap(SendMessagesHelper.m4949$$Nest$fgetwaitingForLocation(this.this$0));
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.wasUnableToFindCurrentLocation, new Object[]{r0});
            SendMessagesHelper.m4949$$Nest$fgetwaitingForLocation(this.this$0).clear();
        }
    }

    public class DelayedMessage {
        public TLRPC.InputFile coverFile;
        public TLRPC.PhotoSize coverPhotoSize;
        public TLRPC.EncryptedChat encryptedChat;
        public HashMap<Object, Object> extraHashMap;
        public int finalGroupMessage;
        public boolean forceReupload;
        public long groupId;
        public String httpLocation;
        public ArrayList<String> httpLocations;
        public ArrayList<TLRPC.InputMedia> inputMedias;
        public TLRPC.InputMedia inputUploadMedia;
        public boolean isLivePhoto;
        public ArrayList<Boolean> livePhotoIndexes;
        public TLObject locationParent;
        public ArrayList<TLRPC.PhotoSize> locations;
        public ArrayList<MessageObject> messageObjects;
        public ArrayList<TLRPC.Message> messages;
        public MessageObject obj;
        public String originalPath;
        public ArrayList<String> originalPaths;
        public boolean paidMedia;
        public Object parentObject;
        public ArrayList<Object> parentObjects;
        public long peer;
        public boolean performCoverUpload;
        public boolean performMediaUpload;
        public TLRPC.PhotoSize photoSize;
        public ArrayList<Integer> pollIndexes;
        public boolean pollMedia;
        ArrayList<DelayedMessageSendAfterRequest> requests;
        private boolean retriedToSend;
        public boolean[] retriedToSendArray;
        public boolean scheduled;
        public TLObject sendEncryptedRequest;
        public TLObject sendRequest;
        final /* synthetic */ SendMessagesHelper this$0;
        public int topMessageId;
        public int type;
        public VideoEditedInfo videoEditedInfo;
        public ArrayList<VideoEditedInfo> videoEditedInfos;

        public DelayedMessage(SendMessagesHelper r1, long r2) {
            this.this$0 = r1;
            this.peer = r2;
        }

        public void addDelayedRequest(TLObject r3, ArrayList<MessageObject> r4, ArrayList<String> r5, ArrayList<Object> r6, DelayedMessage r7, boolean r8) {
            DelayedMessageSendAfterRequest r0 = new DelayedMessageSendAfterRequest(this.this$0);
            r0.request = r3;
            r0.msgObjs = r4;
            r0.originalPaths = r5;
            r0.delayedMessage = r7;
            r0.parentObjects = r6;
            r0.scheduled = r8;
            if (this.requests != null) goto L5;
            this.requests = new ArrayList();
        L5:
            this.requests.add(r0);
        }

        public void addDelayedRequest(TLObject r3, MessageObject r4, String r5, Object r6, DelayedMessage r7, boolean r8) {
            DelayedMessageSendAfterRequest r0 = new DelayedMessageSendAfterRequest(this.this$0);
            r0.request = r3;
            r0.msgObj = r4;
            r0.originalPath = r5;
            r0.delayedMessage = r7;
            r0.parentObject = r6;
            r0.scheduled = r8;
            if (this.requests != null) goto L5;
            this.requests = new ArrayList();
        L5:
            this.requests.add(r0);
        }

        public boolean getRetriedToSend(int r3) {
            if (r3 < 0) goto L11;
            boolean[] r0 = this.retriedToSendArray;
            if (r0 == null) goto L11;
            if (r3 >= r0.length) goto L11;
            return r0[r3];
        L11:
            return this.retriedToSend;
        }

        public void initForGroup(long r2) {
            this.type = 4;
            this.groupId = r2;
            this.messageObjects = new ArrayList();
            this.messages = new ArrayList();
            this.inputMedias = new ArrayList();
            this.originalPaths = new ArrayList();
            this.parentObjects = new ArrayList();
            this.extraHashMap = new HashMap();
            this.locations = new ArrayList();
            this.httpLocations = new ArrayList();
            this.videoEditedInfos = new ArrayList();
            this.pollIndexes = new ArrayList();
            this.livePhotoIndexes = new ArrayList();
        }

        public void markAsError() {
            if (this.type != 4) goto L9;
            int r0 = 0;
        L6:
            if (r0 >= this.messageObjects.size()) goto L8;
            MessageObject r1 = this.messageObjects.get(r0);
            this.this$0.getMessagesStorage().markMessageAsSendError(r1.messageOwner, r1.scheduled ? 1 : 0);
            TLRPC.Message r5 = r1.messageOwner;
            r5.send_state = 2;
            r5.errorAllowedPriceStars = 0;
            r5.errorNewPriceStars = 0;
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(r1.getId())});
            this.this$0.processSentMessage(r1.getId());
            this.this$0.removeFromUploadingMessages(r1.getId(), this.scheduled);
            r0 = r0 + 1;
            goto L6
        L8:
            SendMessagesHelper.m4946$$Nest$fgetdelayedMessages(this.this$0).remove("group_" + this.groupId);
        L10:
            sendDelayedRequests();
            return;
        L9:
            MessagesStorage r02 = this.this$0.getMessagesStorage();
            MessageObject r12 = this.obj;
            r02.markMessageAsSendError(r12.messageOwner, r12.scheduled ? 1 : 0);
            TLRPC.Message r03 = this.obj.messageOwner;
            r03.send_state = 2;
            r03.errorAllowedPriceStars = 0;
            r03.errorNewPriceStars = 0;
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(this.obj.getId())});
            this.this$0.processSentMessage(this.obj.getId());
            this.this$0.removeFromUploadingMessages(this.obj.getId(), this.scheduled);
            goto L10
        }

        public void sendDelayedRequests() {
            ArrayList<DelayedMessageSendAfterRequest> r0 = this.requests;
            if (r0 == null) goto L36;
            int r1 = this.type;
            if (r1 == 4) goto L8;
            if (r1 == 0) goto L8;
            return;
        L8:
            int r02 = r0.size();
            int r12 = 0;
        L9:
            if (r12 >= r02) goto L28;
            DelayedMessageSendAfterRequest r2 = this.requests.get(r12);
            TLObject r4 = r2.request;
            if ((r4 instanceof TLRPC.TL_messages_sendEncryptedMultiMedia) == false) goto L14;
            this.this$0.getSecretChatHelper().performSendEncryptedRequest((TLRPC.TL_messages_sendEncryptedMultiMedia) r2.request, this);
        L27:
            r12 = r12 + 1;
            goto L9
        L14:
            if ((r4 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L17;
            this.this$0.performSendMessageRequestMulti((TLRPC.TL_messages_sendMultiMedia) r4, r2.msgObjs, r2.originalPaths, r2.parentObjects, r2.delayedMessage, r2.scheduled);
            goto L27
        L17:
            if ((r4 instanceof TLRPC.TL_messages_sendMedia) == false) goto L22;
            if ((((TLRPC.TL_messages_sendMedia) r4).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L22;
            this.this$0.performSendMessageRequestMulti((TLRPC.TL_messages_sendMedia) r4, r2.msgObjs, r2.originalPaths, r2.parentObjects, r2.delayedMessage, r2.scheduled);
        L22:
            if ((r4 instanceof TLRPC.TL_messages_sendMedia) == true) goto L24;
        L26:
            SendMessagesHelper.m4950$$Nest$mperformSendMessageRequest(this.this$0, r4, r2.msgObj, r2.originalPath, r2.delayedMessage, r2.parentObject, null, r2.scheduled);
            goto L27
        L24:
            if ((((TLRPC.TL_messages_sendMedia) r4).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L26;
            this.this$0.performSendMessageRequestMulti((TLRPC.TL_messages_sendMedia) r4, r2.msgObjs, r2.originalPaths, r2.parentObjects, r2.delayedMessage, r2.scheduled);
            goto L27
        L28:
            this.requests = null;
            return;
        }

        public void setRetriedToSend(int r2, boolean r3) {
            if (r2 >= 0) goto L6;
            this.retriedToSend = r3;
            return;
        L6:
            if (this.retriedToSendArray != null) goto L8;
            this.retriedToSendArray = new boolean[this.messageObjects.size()];
        L8:
            this.retriedToSendArray[r2] = r3;
        }
    }

    public class DelayedMessageSendAfterRequest {
        public DelayedMessage delayedMessage;
        public MessageObject msgObj;
        public ArrayList<MessageObject> msgObjs;
        public String originalPath;
        public ArrayList<String> originalPaths;
        public Object parentObject;
        public ArrayList<Object> parentObjects;
        public TLObject request;
        public boolean scheduled;
        final /* synthetic */ SendMessagesHelper this$0;

        public DelayedMessageSendAfterRequest(SendMessagesHelper r1) {
            this.this$0 = r1;
        }
    }

    public class ImportingHistory {
        public long dialogId;
        public double estimatedUploadSpeed;
        public String historyPath;
        public long importId;
        private long lastUploadSize;
        private long lastUploadTime;
        public ArrayList<Uri> mediaPaths;
        public TLRPC.InputPeer peer;
        final /* synthetic */ SendMessagesHelper this$0;
        public int timeUntilFinish;
        public long totalSize;
        public ArrayList<String> uploadMedia;
        public int uploadProgress;
        public HashMap<String, Float> uploadProgresses;
        public HashSet<String> uploadSet;
        public HashMap<String, Long> uploadSize;
        public long uploadedSize;

        /* renamed from: org.telegram.messenger.SendMessagesHelper$ImportingHistory$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public class AnonymousClass1 implements RequestDelegate {
            final /* synthetic */ ImportingHistory this$1;
            final /* synthetic */ TLRPC.TL_messages_initHistoryImport val$req;

            public static /* synthetic */ void $r8$lambda$yJtQqwUPRCOTkM9NBNtUAT8ddL4(AnonymousClass1 r0, TLObject r1, TLRPC.TL_messages_initHistoryImport r2, TLRPC.TL_error r3) {
                r0.lambda$run$0(r1, r2, r3);
            }

            public AnonymousClass1(ImportingHistory r1, TLRPC.TL_messages_initHistoryImport r2) {
                this.this$1 = r1;
                this.val$req = r2;
            }

            private /* synthetic */ void lambda$run$0(TLObject r5, TLRPC.TL_messages_initHistoryImport r6, TLRPC.TL_error r7) {
                boolean r0 = r5 instanceof TLRPC.TL_messages_historyImport;
                ImportingHistory r1 = this.this$1;
                if (r0 == false) goto L11;
                r1.importId = ((TLRPC.TL_messages_historyImport) r5).id;
                r1.uploadSet.remove(r1.historyPath);
                this.this$1.this$0.getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(this.this$1.dialogId)});
                if (this.this$1.uploadSet.isEmpty() == false) goto L7;
                ImportingHistory.m4957$$Nest$mstartImport(this.this$1);
            L7:
                ImportingHistory.m4952$$Nest$fputlastUploadTime(this.this$1, SystemClock.elapsedRealtime());
                int r52 = this.this$1.uploadMedia.size();
                int r72 = 0;
            L8:
                if (r72 >= r52) goto L10;
                this.this$1.this$0.getFileLoader().uploadFile(this.this$1.uploadMedia.get(r72), false, true, 67108864);
                r72 = r72 + 1;
                goto L8
            L10:
                return;
            L11:
                SendMessagesHelper.m4947$$Nest$fgetimportingHistoryMap(r1.this$0).remove(this.this$1.dialogId);
                this.this$1.this$0.getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(this.this$1.dialogId), r6, r7});
            }

            @Override // org.telegram.tgnet.RequestDelegate
            public void run(TLObject r3, TLRPC.TL_error r4) {
                AndroidUtilities.runOnUIThread(new SendMessagesHelper$ImportingHistory$1$$ExternalSyntheticLambda0(this, r3, this.val$req, r4));
            }
        }

        /* renamed from: org.telegram.messenger.SendMessagesHelper$ImportingHistory$2, reason: invalid class name */
        /* loaded from: classes5.dex */
        public class AnonymousClass2 implements RequestDelegate {
            final /* synthetic */ ImportingHistory this$1;
            final /* synthetic */ String val$path;

            public static /* synthetic */ void $r8$lambda$zxdwEcvuRK1Pk0NvJZAwgkGbDrE(AnonymousClass2 r0, String r1) {
                r0.lambda$run$0(r1);
            }

            public AnonymousClass2(ImportingHistory r1, String r2) {
                this.this$1 = r1;
                this.val$path = r2;
            }

            private /* synthetic */ void lambda$run$0(String r4) {
                this.this$1.uploadSet.remove(r4);
                this.this$1.this$0.getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(this.this$1.dialogId)});
                if (this.this$1.uploadSet.isEmpty() == false) goto L6;
                ImportingHistory.m4957$$Nest$mstartImport(this.this$1);
                return;
            }

            @Override // org.telegram.tgnet.RequestDelegate
            public void run(TLObject r1, TLRPC.TL_error r2) {
                AndroidUtilities.runOnUIThread(new SendMessagesHelper$ImportingHistory$2$$ExternalSyntheticLambda0(this, this.val$path));
            }
        }

        /* renamed from: org.telegram.messenger.SendMessagesHelper$ImportingHistory$3, reason: invalid class name */
        /* loaded from: classes5.dex */
        public class AnonymousClass3 implements RequestDelegate {
            final /* synthetic */ ImportingHistory this$1;
            final /* synthetic */ TLRPC.TL_messages_startHistoryImport val$req;

            public static /* synthetic */ void $r8$lambda$rrQUi2MD07VQn8a03laH48ec6M4(AnonymousClass3 r0, TLRPC.TL_error r1, TLRPC.TL_messages_startHistoryImport r2) {
                r0.lambda$run$0(r1, r2);
            }

            public AnonymousClass3(ImportingHistory r1, TLRPC.TL_messages_startHistoryImport r2) {
                this.this$1 = r1;
                this.val$req = r2;
            }

            private /* synthetic */ void lambda$run$0(TLRPC.TL_error r5, TLRPC.TL_messages_startHistoryImport r6) {
                SendMessagesHelper.m4947$$Nest$fgetimportingHistoryMap(this.this$1.this$0).remove(this.this$1.dialogId);
                ImportingHistory r0 = this.this$1;
                if (r5 != null) goto L6;
                r0.this$0.getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(this.this$1.dialogId)});
                return;
            L6:
                r0.this$0.getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(this.this$1.dialogId), r6, r5});
            }

            @Override // org.telegram.tgnet.RequestDelegate
            public void run(TLObject r2, TLRPC.TL_error r3) {
                AndroidUtilities.runOnUIThread(new SendMessagesHelper$ImportingHistory$3$$ExternalSyntheticLambda0(this, r3, this.val$req));
            }
        }

        /* renamed from: -$$Nest$fputlastUploadTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4952$$Nest$fputlastUploadTime(ImportingHistory r0, long r1) {
            r0.lastUploadTime = r1;
        }

        /* renamed from: -$$Nest$maddUploadProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4953$$Nest$maddUploadProgress(ImportingHistory r0, String r1, long r2, float r4) {
            r0.addUploadProgress(r1, r2, r4);
        }

        /* renamed from: -$$Nest$minitImport, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4954$$Nest$minitImport(ImportingHistory r0, TLRPC.InputFile r1) {
            r0.initImport(r1);
        }

        /* renamed from: -$$Nest$monFileFailedToUpload, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4955$$Nest$monFileFailedToUpload(ImportingHistory r0, String r1) {
            r0.onFileFailedToUpload(r1);
        }

        /* renamed from: -$$Nest$monMediaImport, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4956$$Nest$monMediaImport(ImportingHistory r0, String r1, long r2, TLRPC.InputFile r4) {
            r0.onMediaImport(r1, r2, r4);
        }

        /* renamed from: -$$Nest$mstartImport, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4957$$Nest$mstartImport(ImportingHistory r0) {
            r0.startImport();
        }

        public ImportingHistory(SendMessagesHelper r1) {
            this.this$0 = r1;
            this.mediaPaths = new ArrayList();
            this.uploadSet = new HashSet();
            this.uploadProgresses = new HashMap();
            this.uploadSize = new HashMap();
            this.uploadMedia = new ArrayList();
            this.timeUntilFinish = Integer.MAX_VALUE;
        }

        private void addUploadProgress(String r9, long r10, float r12) {
            this.uploadProgresses.put(r9, Float.valueOf(r12));
            this.uploadSize.put(r9, Long.valueOf(r10));
            this.uploadedSize = 0;
            Iterator<Map.Entry<String, Long>> r102 = this.uploadSize.entrySet().iterator();
        L4:
            if (r102.hasNext() == false) goto L6;
            this.uploadedSize += r102.next().getValue().longValue();
            goto L4
        L6:
            long r103 = SystemClock.elapsedRealtime();
            if (r9.equals(this.historyPath) == true) goto L17;
            long r0 = this.uploadedSize;
            long r2 = this.lastUploadSize;
            if (r0 == r2) goto L17;
            if (r103 == this.lastUploadTime) goto L17;
            double r22 = (r0 - r2) / ((r103 - r4) / 1000.0d);
            double r4 = this.estimatedUploadSpeed;
            if (r4 != 0.0d) goto L15;
            this.estimatedUploadSpeed = r22;
        L16:
            this.timeUntilFinish = (int) (((this.totalSize - r0) * 1000) / this.estimatedUploadSpeed);
            this.lastUploadSize = r0;
            this.lastUploadTime = r103;
            goto L17
        L15:
            this.estimatedUploadSpeed = (r22 * 0.01d) + (0.99d * r4);
        L17:
            int r92 = (int) ((getUploadedCount() / getTotalCount()) * 100.0f);
            if (this.uploadProgress == r92) goto L22;
            this.uploadProgress = r92;
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(this.dialogId)});
            return;
        }

        private void initImport(TLRPC.InputFile r3) {
            TLRPC.TL_messages_initHistoryImport r0 = new TLRPC.TL_messages_initHistoryImport();
            r0.file = r3;
            r0.media_count = this.mediaPaths.size();
            r0.peer = this.peer;
            this.this$0.getConnectionsManager().sendRequest(r0, new AnonymousClass1(this, r0), 2);
        }

        private void onFileFailedToUpload(String r5) {
            if (r5.equals(this.historyPath) == false) goto L6;
            SendMessagesHelper.m4947$$Nest$fgetimportingHistoryMap(this.this$0).remove(this.dialogId);
            TLRPC.TL_error r52 = new TLRPC.TL_error();
            r52.code = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
            r52.text = "IMPORT_UPLOAD_FAILED";
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(this.dialogId), new TLRPC.TL_messages_initHistoryImport(), r52});
            return;
        L6:
            this.uploadSet.remove(r5);
        }

        private void onMediaImport(String r3, long r4, TLRPC.InputFile r6) {
            addUploadProgress(r3, r4, 1.0f);
            TLRPC.TL_messages_uploadImportedMedia r42 = new TLRPC.TL_messages_uploadImportedMedia();
            r42.peer = this.peer;
            r42.import_id = this.importId;
            r42.file_name = new File(r3).getName();
            MimeTypeMap r5 = MimeTypeMap.getSingleton();
            int r0 = r42.file_name.lastIndexOf(46);
            if (r0 == (-1)) goto L5;
            String r02 = r42.file_name.substring(r0 + 1).toLowerCase();
        L6:
            String r52 = r5.getMimeTypeFromExtension(r02);
            if (r52 != null) goto L16;
            if ("opus".equals(r02) == false) goto L12;
            r52 = "audio/opus";
            goto L16
        L12:
            if ("webp".equals(r02) == false) goto L14;
            r52 = "image/webp";
            goto L16
        L14:
            r52 = "text/plain";
        L16:
            if (r52.equals("image/jpg") == false) goto L18;
        L21:
            TLRPC.TL_inputMediaUploadedPhoto r53 = new TLRPC.TL_inputMediaUploadedPhoto();
            r53.file = r6;
            r42.media = r53;
        L22:
            this.this$0.getConnectionsManager().sendRequest(r42, new AnonymousClass2(this, r3), 2);
            return;
        L18:
            if (r52.equals("image/jpeg") == true) goto L21;
            TLRPC.TL_inputMediaUploadedDocument r03 = new TLRPC.TL_inputMediaUploadedDocument();
            r03.file = r6;
            r03.mime_type = r52;
            r42.media = r03;
            goto L22
        L5:
            r02 = "txt";
            goto L6
        }

        private void startImport() {
            TLRPC.TL_messages_startHistoryImport r0 = new TLRPC.TL_messages_startHistoryImport();
            r0.peer = this.peer;
            r0.import_id = this.importId;
            this.this$0.getConnectionsManager().sendRequest(r0, new AnonymousClass3(this, r0));
        }

        public long getTotalCount() {
            return this.totalSize;
        }

        public long getUploadedCount() {
            return this.uploadedSize;
        }

        public void setImportProgress(int r4) {
            if (r4 != 100) goto L5;
            SendMessagesHelper.m4947$$Nest$fgetimportingHistoryMap(this.this$0).remove(this.dialogId);
        L5:
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(this.dialogId)});
        }
    }

    public static class ImportingSticker {
        public boolean animated;
        public String emoji;
        public TLRPC.TL_inputStickerSetItem item;
        public String mimeType;
        public String path;
        public boolean validated;
        public VideoEditedInfo videoEditedInfo;

        /* renamed from: org.telegram.messenger.SendMessagesHelper$ImportingSticker$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public class AnonymousClass1 implements RequestDelegate {
            final /* synthetic */ ImportingSticker this$0;
            final /* synthetic */ Runnable val$onFinish;

            public static /* synthetic */ void $r8$lambda$GmEgBPqVzA_T8vgJMA6ykn6uS5k(AnonymousClass1 r0, TLObject r1, Runnable r2) {
                r0.lambda$run$0(r1, r2);
            }

            public AnonymousClass1(ImportingSticker r1, Runnable r2) {
                this.this$0 = r1;
                this.val$onFinish = r2;
            }

            private /* synthetic */ void lambda$run$0(TLObject r5, Runnable r6) {
                boolean r0 = r5 instanceof TLRPC.TL_messageMediaDocument;
                ImportingSticker r1 = this.this$0;
                if (r0 == false) goto L10;
                r1.item = new TLRPC.TL_inputStickerSetItem();
                TLRPC.TL_inputStickerSetItem r02 = this.this$0.item;
                r02.document = new TLRPC.TL_inputDocument();
                ImportingSticker r4 = this.this$0;
                TLRPC.TL_inputStickerSetItem r03 = r4.item;
                TLRPC.InputDocument r12 = r03.document;
                TLRPC.Document r52 = ((TLRPC.TL_messageMediaDocument) r5).document;
                r12.id = r52.id;
                r12.access_hash = r52.access_hash;
                r12.file_reference = r52.file_reference;
                String r13 = r4.emoji;
                if (r13 != null) goto L8;
                r13 = _UrlKt.FRAGMENT_ENCODE_SET;
            L8:
                r03.emoji = r13;
                r4.mimeType = r52.mime_type;
            L12:
                r6.run();
                return;
            L10:
                if (r1.animated == false) goto L12;
                r1.mimeType = "application/x-bad-tgsticker";
                goto L12
            }

            @Override // org.telegram.tgnet.RequestDelegate
            public void run(TLObject r2, TLRPC.TL_error r3) {
                AndroidUtilities.runOnUIThread(new SendMessagesHelper$ImportingSticker$1$$ExternalSyntheticLambda0(this, r2, this.val$onFinish));
            }
        }

        public ImportingSticker() {
        }

        public void uploadMedia(int r3, TLRPC.InputFile r4, Runnable r5) {
            TLRPC.TL_messages_uploadMedia r0 = new TLRPC.TL_messages_uploadMedia();
            r0.peer = new TLRPC.TL_inputPeerSelf();
            TLRPC.TL_inputMediaUploadedDocument r1 = new TLRPC.TL_inputMediaUploadedDocument();
            r0.media = r1;
            r1.file = r4;
            r1.mime_type = this.mimeType;
            ConnectionsManager.getInstance(r3).sendRequest(r0, new AnonymousClass1(this, r5), 2);
        }
    }

    public class ImportingStickers {
        public double estimatedUploadSpeed;
        private long lastUploadSize;
        private long lastUploadTime;
        public String shortName;
        public String software;
        final /* synthetic */ SendMessagesHelper this$0;
        public int timeUntilFinish;
        public String title;
        public long totalSize;
        public ArrayList<ImportingSticker> uploadMedia;
        public int uploadProgress;
        public HashMap<String, Float> uploadProgresses;
        public HashMap<String, ImportingSticker> uploadSet;
        public HashMap<String, Long> uploadSize;
        public long uploadedSize;

        /* renamed from: org.telegram.messenger.SendMessagesHelper$ImportingStickers$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public class AnonymousClass1 implements RequestDelegate {
            final /* synthetic */ ImportingStickers this$1;
            final /* synthetic */ TLRPC.TL_stickers_createStickerSet val$req;

            public static /* synthetic */ void $r8$lambda$cv4tbiJSP7zbybwND9LPFGwcJv8(AnonymousClass1 r0, TLRPC.TL_error r1, TLRPC.TL_stickers_createStickerSet r2, TLObject r3) {
                r0.lambda$run$0(r1, r2, r3);
            }

            public AnonymousClass1(ImportingStickers r1, TLRPC.TL_stickers_createStickerSet r2) {
                this.this$1 = r1;
                this.val$req = r2;
            }

            private /* synthetic */ void lambda$run$0(TLRPC.TL_error r8, TLRPC.TL_stickers_createStickerSet r9, TLObject r10) {
                SendMessagesHelper.m4948$$Nest$fgetimportingStickersMap(this.this$1.this$0).remove(this.this$1.shortName);
                ImportingStickers r0 = this.this$1;
                if (r8 != null) goto L5;
                r0.this$0.getNotificationCenter().postNotificationName(NotificationCenter.stickersImportProgressChanged, new Object[]{this.this$1.shortName});
            L7:
                if ((r10 instanceof TLRPC.TL_messages_stickerSet) == false) goto L14;
                NotificationCenter r82 = this.this$1.this$0.getNotificationCenter();
                int r92 = NotificationCenter.stickersImportComplete;
                boolean r83 = r82.hasObservers(r92);
                ImportingStickers r7 = this.this$1;
                if (r83 == false) goto L12;
                r7.this$0.getNotificationCenter().postNotificationName(r92, new Object[]{r10});
                return;
            L12:
                r7.this$0.getMediaDataController().toggleStickerSet(null, r10, 2, null, false, false);
                return;
            L14:
                return;
            L5:
                r0.this$0.getNotificationCenter().postNotificationName(NotificationCenter.stickersImportProgressChanged, new Object[]{this.this$1.shortName, r9, r8});
                goto L7
            }

            @Override // org.telegram.tgnet.RequestDelegate
            public void run(TLObject r3, TLRPC.TL_error r4) {
                AndroidUtilities.runOnUIThread(new SendMessagesHelper$ImportingStickers$1$$ExternalSyntheticLambda0(this, r4, this.val$req, r3));
            }
        }

        /* renamed from: $r8$lambda$9lKyxdhI06Xzf-WkwjAcNW7AscM, reason: not valid java name */
        public static /* synthetic */ void m4958$r8$lambda$9lKyxdhI06XzfWkwjAcNW7AscM(ImportingStickers r0, String r1) {
            r0.lambda$onMediaImport$0(r1);
        }

        /* renamed from: -$$Nest$maddUploadProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4959$$Nest$maddUploadProgress(ImportingStickers r0, String r1, long r2, float r4) {
            r0.addUploadProgress(r1, r2, r4);
        }

        /* renamed from: -$$Nest$minitImport, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4960$$Nest$minitImport(ImportingStickers r0) {
            r0.initImport();
        }

        /* renamed from: -$$Nest$monFileFailedToUpload, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4961$$Nest$monFileFailedToUpload(ImportingStickers r0, String r1) {
            r0.onFileFailedToUpload(r1);
        }

        /* renamed from: -$$Nest$monMediaImport, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4962$$Nest$monMediaImport(ImportingStickers r0, String r1, long r2, TLRPC.InputFile r4) {
            r0.onMediaImport(r1, r2, r4);
        }

        /* renamed from: -$$Nest$mstartImport, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4963$$Nest$mstartImport(ImportingStickers r0) {
            r0.startImport();
        }

        public ImportingStickers(SendMessagesHelper r1) {
            this.this$0 = r1;
            this.uploadSet = new HashMap();
            this.uploadProgresses = new HashMap();
            this.uploadSize = new HashMap();
            this.uploadMedia = new ArrayList();
            this.timeUntilFinish = Integer.MAX_VALUE;
        }

        private void addUploadProgress(String r7, long r8, float r10) {
            this.uploadProgresses.put(r7, Float.valueOf(r10));
            this.uploadSize.put(r7, Long.valueOf(r8));
            this.uploadedSize = 0;
            Iterator<Map.Entry<String, Long>> r72 = this.uploadSize.entrySet().iterator();
        L4:
            if (r72.hasNext() == false) goto L6;
            this.uploadedSize += r72.next().getValue().longValue();
            goto L4
        L6:
            long r73 = SystemClock.elapsedRealtime();
            long r9 = this.uploadedSize;
            long r0 = this.lastUploadSize;
            if (r9 != r0) goto L9;
        L15:
            int r74 = (int) ((getUploadedCount() / getTotalCount()) * 100.0f);
            if (this.uploadProgress == r74) goto L20;
            this.uploadProgress = r74;
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.stickersImportProgressChanged, new Object[]{this.shortName});
            return;
        L20:
            return;
        L9:
            if (r73 == this.lastUploadTime) goto L15;
            double r02 = (r9 - r0) / ((r73 - r2) / 1000.0d);
            double r2 = this.estimatedUploadSpeed;
            if (r2 != 0.0d) goto L13;
            this.estimatedUploadSpeed = r02;
        L14:
            this.timeUntilFinish = (int) (((this.totalSize - r9) * 1000) / this.estimatedUploadSpeed);
            this.lastUploadSize = r9;
            this.lastUploadTime = r73;
            goto L15
        L13:
            this.estimatedUploadSpeed = (r02 * 0.01d) + (0.99d * r2);
            goto L14
        }

        private void initImport() {
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.stickersImportProgressChanged, new Object[]{this.shortName});
            this.lastUploadTime = SystemClock.elapsedRealtime();
            int r0 = this.uploadMedia.size();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L5;
            this.this$0.getFileLoader().uploadFile(this.uploadMedia.get(r2).path, false, true, 67108864);
            r2 = r2 + 1;
            goto L3
        }

        private /* synthetic */ void lambda$onMediaImport$0(String r3) {
            this.uploadSet.remove(r3);
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.stickersImportProgressChanged, new Object[]{this.shortName});
            if (this.uploadSet.isEmpty() == false) goto L6;
            startImport();
            return;
        }

        private void onFileFailedToUpload(String r2) {
            ImportingSticker r22 = this.uploadSet.remove(r2);
            if (r22 == null) goto L6;
            this.uploadMedia.remove(r22);
            return;
        }

        private void onMediaImport(String r2, long r3, TLRPC.InputFile r5) {
            addUploadProgress(r2, r3, 1.0f);
            ImportingSticker r32 = this.uploadSet.get(r2);
            if (r32 != null) goto L5;
            return;
        L5:
            r32.uploadMedia(this.this$0.currentAccount, r5, new SendMessagesHelper$ImportingStickers$$ExternalSyntheticLambda0(this, r2));
        }

        private void startImport() {
            TLRPC.TL_stickers_createStickerSet r0 = new TLRPC.TL_stickers_createStickerSet();
            r0.user_id = new TLRPC.TL_inputUserSelf();
            r0.title = this.title;
            r0.short_name = this.shortName;
            String r1 = this.software;
            if (r1 == null) goto L5;
            r0.software = r1;
            r0.flags |= 8;
        L5:
            int r12 = this.uploadMedia.size();
            int r2 = 0;
        L6:
            if (r2 >= r12) goto L12;
            TLRPC.TL_inputStickerSetItem r3 = this.uploadMedia.get(r2).item;
            if (r3 == null) goto L11;
            r0.stickers.add(r3);
        L11:
            r2 = r2 + 1;
            goto L6
        L12:
            this.this$0.getConnectionsManager().sendRequest(r0, new AnonymousClass1(this, r0));
        }

        public long getTotalCount() {
            return this.totalSize;
        }

        public long getUploadedCount() {
            return this.uploadedSize;
        }

        public void setImportProgress(int r2) {
            if (r2 != 100) goto L5;
            SendMessagesHelper.m4948$$Nest$fgetimportingStickersMap(this.this$0).remove(this.shortName);
        L5:
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.stickersImportProgressChanged, new Object[]{this.shortName});
        }
    }

    @SuppressLint({"MissingPermission"})
    public static class LocationProvider {
        private LocationProviderDelegate delegate;
        private GpsLocationListener gpsLocationListener;
        private Location lastKnownLocation;
        private LocationManager locationManager;
        private Runnable locationQueryCancelRunnable;
        private GpsLocationListener networkLocationListener;

        public class GpsLocationListener implements LocationListener {
            final /* synthetic */ LocationProvider this$0;

            private GpsLocationListener(LocationProvider r1) {
                this.this$0 = r1;
            }

            public /* synthetic */ GpsLocationListener(LocationProvider r1, SendMessagesHelperIA r2) {
                this(r1);
            }

            @Override // android.location.LocationListener
            public void onLocationChanged(Location r3) {
                if (r3 != null) goto L4;
                return;
            L4:
                if (LocationProvider.m4965$$Nest$fgetlocationQueryCancelRunnable(this.this$0) != null) goto L7;
                return;
            L7:
                if (BuildVars.LOGS_ENABLED == false) goto L9;
                FileLog.d("found location " + r3);
            L9:
                LocationProvider.m4966$$Nest$fputlastKnownLocation(this.this$0, r3);
                if (r3.getAccuracy() < 100.0f) goto L12;
                return;
            L12:
                if (LocationProvider.m4964$$Nest$fgetdelegate(this.this$0) == null) goto L15;
                LocationProvider.m4964$$Nest$fgetdelegate(this.this$0).onLocationAcquired(r3);
            L15:
                if (LocationProvider.m4965$$Nest$fgetlocationQueryCancelRunnable(this.this$0) == null) goto L17;
                AndroidUtilities.cancelRunOnUIThread(LocationProvider.m4965$$Nest$fgetlocationQueryCancelRunnable(this.this$0));
            L17:
                LocationProvider.m4967$$Nest$mcleanup(this.this$0);
            }

            @Override // android.location.LocationListener
            public void onProviderDisabled(String r1) {
            }

            @Override // android.location.LocationListener
            public void onProviderEnabled(String r1) {
            }

            @Override // android.location.LocationListener
            public void onStatusChanged(String r1, int r2, Bundle r3) {
            }
        }

        public interface LocationProviderDelegate {
            void onLocationAcquired(Location r1);

            void onUnableLocationAcquire();
        }

        public static /* synthetic */ void $r8$lambda$1GLllj2ugdy_2OOgIrvgkHFqOsM(LocationProvider r0) {
            r0.lambda$start$0();
        }

        /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LocationProviderDelegate m4964$$Nest$fgetdelegate(LocationProvider r0) {
            return r0.delegate;
        }

        /* renamed from: -$$Nest$fgetlocationQueryCancelRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m4965$$Nest$fgetlocationQueryCancelRunnable(LocationProvider r0) {
            return r0.locationQueryCancelRunnable;
        }

        /* renamed from: -$$Nest$fputlastKnownLocation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4966$$Nest$fputlastKnownLocation(LocationProvider r0, Location r1) {
            r0.lastKnownLocation = r1;
        }

        /* renamed from: -$$Nest$mcleanup, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4967$$Nest$mcleanup(LocationProvider r0) {
            r0.cleanup();
        }

        public LocationProvider() {
            this.gpsLocationListener = new GpsLocationListener(this, null);
            this.networkLocationListener = new GpsLocationListener(this, null);
        }

        public LocationProvider(LocationProviderDelegate r3) {
            this.gpsLocationListener = new GpsLocationListener(this, null);
            this.networkLocationListener = new GpsLocationListener(this, null);
            this.delegate = r3;
        }

        private void cleanup() {
            this.locationManager.removeUpdates(this.gpsLocationListener);
            this.locationManager.removeUpdates(this.networkLocationListener);
            this.lastKnownLocation = null;
            this.locationQueryCancelRunnable = null;
        }

        private /* synthetic */ void lambda$start$0() {
            LocationProviderDelegate r0 = this.delegate;
            if (r0 == null) goto L8;
            Location r1 = this.lastKnownLocation;
            if (r1 == null) goto L7;
            r0.onLocationAcquired(r1);
            goto L8
        L7:
            r0.onUnableLocationAcquire();
        L8:
            cleanup();
        }

        public void setDelegate(LocationProviderDelegate r1) {
            this.delegate = r1;
        }

        public void start() {
            if (this.locationManager != null) goto L24;
            this.locationManager = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");
        L24:
            this.locationManager.requestLocationUpdates("gps", 1, 0.0f, this.gpsLocationListener);     // Catch: Exception -> L7
        L28:
            this.locationManager.requestLocationUpdates("network", 1, 0.0f, this.networkLocationListener);     // Catch: Exception -> L11
        L26:
            Location r0 = this.locationManager.getLastKnownLocation("gps");     // Catch: Exception -> L17
            this.lastKnownLocation = r0;     // Catch: Exception -> L17
            if (r0 != null) goto L19;
            this.lastKnownLocation = this.locationManager.getLastKnownLocation("network");     // Catch: Exception -> L17
        L19:
            Runnable r02 = this.locationQueryCancelRunnable;
            if (r02 == null) goto L22;
            AndroidUtilities.cancelRunOnUIThread(r02);
        L22:
            SendMessagesHelper$LocationProvider$$ExternalSyntheticLambda0 r03 = new SendMessagesHelper$LocationProvider$$ExternalSyntheticLambda0(this);
            this.locationQueryCancelRunnable = r03;
            AndroidUtilities.runOnUIThread(r03, 5000);
            return;
        L17:
            e = move-exception;
            FileLog.e(e);
        L11:
            e = move-exception;
            FileLog.e(e);
        L7:
            e = move-exception;
            FileLog.e(e);
            goto L28
        }

        public void stop() {
            if (this.locationManager != null) goto L5;
            return;
        L5:
            Runnable r0 = this.locationQueryCancelRunnable;
            if (r0 == null) goto L8;
            AndroidUtilities.cancelRunOnUIThread(r0);
        L8:
            cleanup();
        }
    }

    public static class MediaSendPrepareWorker {
        public volatile String parentObject;
        public volatile TLRPC.TL_photo photo;
        public CountDownLatch sync;

        private MediaSendPrepareWorker() {
        }

        public /* synthetic */ MediaSendPrepareWorker(SendMessagesHelperIA r1) {
            this();
        }
    }

    public static class SendMessageParams {
        public String caption;
        public TLRPC.PhotoSize cover;
        public long dice_stake;
        public TLRPC.TL_document document;
        public long effect_id;
        public ArrayList<TLRPC.MessageEntity> entities;
        public long ephemeralReceiverBotId;
        public TLRPC.TL_game game;
        public boolean hasMediaSpoilers;
        public boolean invert_media;
        public TLRPC.TL_messageMediaInvoice invoice;
        public boolean isLivePhoto;
        public long livePhotoTimestamp;
        public TLRPC.MessageMedia location;
        public TLRPC.TL_messageMediaWebPage mediaWebPage;
        public String message;
        public long monoForumPeer;
        public boolean notify;
        public HashMap<String, String> params;
        public Object parentObject;
        public String path;
        public long payStars;
        public long peer;
        public TLRPC.TL_photo photo;
        public TLRPC.TL_messageMediaPoll poll;
        public int pollIndex;
        public PollSendParams pollSendParams;

        @Deprecated
        public String quick_reply_shortcut;

        @Deprecated
        public int quick_reply_shortcut_id;
        public TLRPC.ReplyMarkup replyMarkup;
        public ChatActivity.ReplyQuote replyQuote;
        public MessageObject replyToMsg;
        public TL_stories.StoryItem replyToStoryItem;
        public MessageObject replyToTopMsg;
        public MessageObject retryMessageObject;
        public TL_iv.RichMessage richMessage;
        public ArrayList<TLRPC.InputUser> richMessageInputUsers;
        public int scheduleDate;
        public int scheduleRepeatPeriod;
        public boolean searchLinks;
        public MessageObject.SendAnimationData sendAnimationData;
        public SendMessageChatArguments sendMessageChatArguments;
        public boolean sendingHighQuality;
        public TL_stories.StoryItem sendingStory;
        public long stars;
        public MessageSuggestionParams suggestionParams;
        public TLRPC.TL_messageMediaToDo todo;
        public int ttl;
        public boolean updateStickersOrder;
        public TLRPC.User user;
        public VideoEditedInfo videoEditedInfo;
        public TLRPC.WebPage webPage;

        public SendMessageParams() {
            this.searchLinks = true;
        }

        public static SendMessageParams of(String r29, long r30) {
            return of(r29, null, null, null, null, null, null, null, null, null, r30, null, null, null, null, true, null, null, null, null, false, 0, 0, 0, null, null, false);
        }

        public static SendMessageParams of(String r29, long r30, MessageObject r32, MessageObject r33, TLRPC.WebPage r34, boolean r35, ArrayList<TLRPC.MessageEntity> r36, TLRPC.ReplyMarkup r37, HashMap<String, String> r38, boolean r39, int r40, int r41, MessageObject.SendAnimationData r42, boolean r43) {
            return of(r29, null, null, null, null, null, null, null, null, null, r30, null, r32, r33, r34, r35, null, r36, r37, r38, r39, r40, r41, 0, null, r42, r43);
        }

        private static SendMessageParams of(String r30, String r31, TLRPC.MessageMedia r32, TLRPC.TL_photo r33, VideoEditedInfo r34, TLRPC.User r35, TLRPC.TL_document r36, TLRPC.TL_game r37, TLRPC.TL_messageMediaPoll r38, TLRPC.TL_messageMediaInvoice r39, long r40, String r42, MessageObject r43, MessageObject r44, TLRPC.WebPage r45, boolean r46, MessageObject r47, ArrayList<TLRPC.MessageEntity> r48, TLRPC.ReplyMarkup r49, HashMap<String, String> r50, boolean r51, int r52, int r53, int r54, Object r55, MessageObject.SendAnimationData r56, boolean r57) {
            return of(r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, false);
        }

        public static SendMessageParams of(String r1, String r2, TLRPC.MessageMedia r3, TLRPC.TL_photo r4, VideoEditedInfo r5, TLRPC.User r6, TLRPC.TL_document r7, TLRPC.TL_game r8, TLRPC.TL_messageMediaPoll r9, TLRPC.TL_messageMediaInvoice r10, long r11, String r13, MessageObject r14, MessageObject r15, TLRPC.WebPage r16, boolean r17, MessageObject r18, ArrayList<TLRPC.MessageEntity> r19, TLRPC.ReplyMarkup r20, HashMap<String, String> r21, boolean r22, int r23, int r24, int r25, Object r26, MessageObject.SendAnimationData r27, boolean r28, boolean r29) {
            SendMessageParams r0 = new SendMessageParams();
            r0.message = r1;
            r0.caption = r2;
            r0.location = r3;
            r0.photo = r4;
            r0.videoEditedInfo = r5;
            r0.user = r6;
            r0.document = r7;
            r0.game = r8;
            r0.poll = r9;
            r0.invoice = r10;
            r0.peer = r11;
            r0.path = r13;
            r0.replyToMsg = r14;
            r0.replyToTopMsg = r15;
            r0.webPage = r16;
            r0.searchLinks = r17;
            r0.retryMessageObject = r18;
            r0.entities = r19;
            r0.replyMarkup = r20;
            r0.params = r21;
            r0.notify = r22;
            r0.scheduleDate = r23;
            r0.scheduleRepeatPeriod = r24;
            r0.ttl = r25;
            r0.parentObject = r26;
            r0.sendAnimationData = r27;
            r0.updateStickersOrder = r28;
            r0.hasMediaSpoilers = r29;
            return r0;
        }

        public static SendMessageParams of(MessageObject r29) {
            long r11 = r29.getDialogId();
            TLRPC.Message r1 = r29.messageOwner;
            String r13 = r1.attachPath;
            TLRPC.ReplyMarkup r2 = r1.reply_markup;
            HashMap<String, String> r3 = r1.params;
            boolean r22 = !r1.silent;
            if (r29.scheduled == false) goto L6;
            int r12 = r1.date;
        L7:
            SendMessageParams r0 = of(null, null, null, null, null, null, null, null, null, null, r11, r13, null, null, null, true, r29, null, r2, r3, r22, r12, 0, 0, null, null, false);
            TLRPC.Message r23 = r29.messageOwner;
            if (r23 == null) goto L13;
            TLRPC.InputQuickReplyShortcut r24 = r23.quick_reply_shortcut;
            if ((r24 instanceof TLRPC.TL_inputQuickReplyShortcut) == false) goto L12;
            r0.quick_reply_shortcut = ((TLRPC.TL_inputQuickReplyShortcut) r24).shortcut;
        L12:
            r0.quick_reply_shortcut_id = r29.getQuickReplyId();
            r0.payStars = r29.messageOwner.paid_message_stars;
        L13:
            r0.ephemeralReceiverBotId = r29.getEphemeralReceiverBotId();
            return r0;
        L6:
            r12 = 0;
            goto L7
        }

        public static SendMessageParams of(TLRPC.MessageMedia r29, long r30, MessageObject r32, MessageObject r33, TLRPC.ReplyMarkup r34, HashMap<String, String> r35, boolean r36, int r37, int r38) {
            return of(null, null, r29, null, null, null, null, null, null, null, r30, null, r32, r33, null, true, null, null, r34, r35, r36, r37, r38, 0, null, null, false);
        }

        public static SendMessageParams of(TLRPC.TL_document r29, VideoEditedInfo r30, String r31, long r32, MessageObject r34, MessageObject r35, String r36, ArrayList<TLRPC.MessageEntity> r37, TLRPC.ReplyMarkup r38, HashMap<String, String> r39, boolean r40, int r41, int r42, int r43, Object r44, MessageObject.SendAnimationData r45, boolean r46) {
            return of(null, r36, null, null, r30, null, r29, null, null, null, r32, r31, r34, r35, null, true, null, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46);
        }

        public static SendMessageParams of(TLRPC.TL_document r30, VideoEditedInfo r31, String r32, long r33, MessageObject r35, MessageObject r36, String r37, ArrayList<TLRPC.MessageEntity> r38, TLRPC.ReplyMarkup r39, HashMap<String, String> r40, boolean r41, int r42, int r43, int r44, Object r45, MessageObject.SendAnimationData r46, boolean r47, boolean r48) {
            return of(null, r37, null, null, r31, null, r30, null, null, null, r33, r32, r35, r36, null, true, null, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48);
        }

        public static SendMessageParams of(TLRPC.TL_game r29, long r30, MessageObject r32, MessageObject r33, TLRPC.ReplyMarkup r34, HashMap<String, String> r35, boolean r36, int r37, int r38) {
            return of(null, null, null, null, null, null, null, r29, null, null, r30, null, r32, r33, null, true, null, null, r34, r35, r36, r37, r38, 0, null, null, false);
        }

        public static SendMessageParams of(TLRPC.TL_messageMediaInvoice r29, long r30, MessageObject r32, MessageObject r33, TLRPC.ReplyMarkup r34, HashMap<String, String> r35, boolean r36, int r37, int r38) {
            return of(null, null, null, null, null, null, null, null, null, r29, r30, null, r32, r33, null, true, null, null, r34, r35, r36, r37, r38, 0, null, null, false);
        }

        public static SendMessageParams of(TLRPC.TL_messageMediaPoll r29, long r30, MessageObject r32, MessageObject r33, TLRPC.ReplyMarkup r34, HashMap<String, String> r35, boolean r36, int r37, int r38) {
            return of(null, null, null, null, null, null, null, null, r29, null, r30, null, r32, r33, null, true, null, null, r34, r35, r36, r37, r38, 0, null, null, false);
        }

        public static SendMessageParams of(TLRPC.TL_photo r29, String r30, long r31, MessageObject r33, MessageObject r34, String r35, ArrayList<TLRPC.MessageEntity> r36, TLRPC.ReplyMarkup r37, HashMap<String, String> r38, boolean r39, int r40, int r41, int r42, Object r43, boolean r44) {
            return of(null, r35, null, r29, null, null, null, null, null, null, r31, r30, r33, r34, null, true, null, r36, r37, r38, r39, r40, r41, r42, r43, null, r44);
        }

        public static SendMessageParams of(TLRPC.TL_photo r30, String r31, long r32, MessageObject r34, MessageObject r35, String r36, ArrayList<TLRPC.MessageEntity> r37, TLRPC.ReplyMarkup r38, HashMap<String, String> r39, boolean r40, int r41, int r42, int r43, Object r44, boolean r45, boolean r46) {
            return of(null, r36, null, r30, null, null, null, null, null, null, r32, r31, r34, r35, null, true, null, r37, r38, r39, r40, r41, r42, r43, r44, null, r45, r46);
        }

        public static SendMessageParams of(TLRPC.User r29, long r30, MessageObject r32, MessageObject r33, TLRPC.ReplyMarkup r34, HashMap<String, String> r35, boolean r36, int r37, int r38) {
            return of(null, null, null, null, null, r29, null, null, null, null, r30, null, r32, r33, null, true, null, null, r34, r35, r36, r37, r38, 0, null, null, false);
        }

        public static SendMessageParams ofRichMessage(TL_iv.RichMessage r29, long r30, MessageObject r32, MessageObject r33, TLRPC.ReplyMarkup r34, HashMap<String, String> r35, boolean r36, int r37, int r38) {
            SendMessageParams r0 = of(null, null, null, null, null, null, null, null, null, null, r30, null, r32, r33, null, true, null, null, r34, r35, r36, r37, r38, 0, null, null, false);
            r0.richMessage = r29;
            return r0;
        }
    }

    public static class SendingMediaInfo {
        public boolean canDeleteAfter;
        public String caption;
        public String coverPath;
        public TLRPC.Photo coverPhoto;
        public boolean discardLivePhoto;
        public TLRPC.VideoSize emojiMarkup;
        public ArrayList<TLRPC.MessageEntity> entities;
        public boolean forceImage;
        public boolean hasMediaSpoilers;
        public boolean highQuality;
        public String imagePath;
        public TLRPC.BotInlineResult inlineResult;
        public boolean isLivePhoto;
        public boolean isVideo;
        public long livePhotoTimestampUs;
        public long livePhotoVideoOffset;
        public ArrayList<TLRPC.InputDocument> masks;
        public MediaController.PhotoEntry originalPhotoEntry;
        public String paintPath;
        public HashMap<String, String> params;
        public String path;
        public int pollIndex;
        public MediaController.SearchImage searchImage;
        public long stars;
        public String thumbPath;
        public int ttl;
        public boolean updateStickersOrder;
        public Uri uri;
        public VideoEditedInfo videoEditedInfo;

        public SendingMediaInfo() {
        }
    }

    /* renamed from: $r8$lambda$-dphkD-tQVPRASlbWGn1p2yd1_A, reason: not valid java name */
    public static /* synthetic */ void m4903$r8$lambda$dphkDtQVPRASlbWGn1p2yd1_A(SendMessagesHelper r0, TLObject r1, MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9) {
        r0.lambda$performSendMessageRequest$77(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$0zp27Z9-_-YXAND1kSdh5uyv_Rc, reason: not valid java name */
    public static /* synthetic */ void m4904$r8$lambda$0zp27Z9_YXAND1kSdh5uyv_Rc(SendMessagesHelper r0, TL_update.TL_updateNewChannelMessage r1) {
        r0.lambda$performSendMessageRequestMulti$67(r1);
    }

    public static /* synthetic */ void $r8$lambda$1hYx7Ctu9IErJDJ_3qj8M8VrUSo(SendMessagesHelper r0, int r1, boolean r2, Runnable r3) {
        r0.lambda$toggleTodo$33(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$1koEq5Ter5LWwvDUFOIQONgRMjQ(SendMessagesHelper r0, TL_update.TL_updateNewChannelMessage r1) {
        r0.lambda$performSendMessageRequest$92(r1);
    }

    public static /* synthetic */ void $r8$lambda$2mTmvWAJht25QzXC9AuMuHc9m2w(SendMessagesHelper r0, ArrayList r1, int r2, int r3, TLRPC.Message r4, int r5, TLRPC.Message r6, MessageObject r7, int r8) {
        r0.lambda$sendMessage$9(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$36P7739QbZAzwUqCyfBsOgh22aw(SendMessagesHelper r0, TLObject r1, MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9, TLRPC.Message r10, TLObject r11, TLRPC.TL_error r12) {
        r0.lambda$performSendMessageRequest$101(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    /* renamed from: $r8$lambda$448r-AArLZVzl3HFc4lm9Wg63vM, reason: not valid java name */
    public static /* synthetic */ void m4905$r8$lambda$448rAArLZVzl3HFc4lm9Wg63vM(SendMessagesHelper r0, TLRPC.TL_error r1, TLObject r2, boolean r3, ArrayList r4, ArrayList r5, TLObject r6) {
        r0.lambda$performSendMessageRequestMulti$73(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$45kmZvwdTEk0ydavREZFwXSKbk8(SendMessagesHelper r0, TLRPC.TL_messages_requestUrlAuth r1, ChatActivity r2, String r3, boolean r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$requestUrlAuth$37(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$4gFs7tsD8iPNc6x2hDiGLKj2fq8(SendMessagesHelper r0, String r1, String r2, String r3, ArrayList r4, MessagesStorage.StringCallback r5) {
        r0.lambda$prepareImportStickers$113(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$5y3i9_Xrp66V-XiK2Lmk7R2H8D8, reason: not valid java name */
    public static /* synthetic */ void m4906$r8$lambda$5y3i9_Xrp66VXiK2Lmk7R2H8D8(ArrayList r32, ArrayList r33, AccountInstance r34, ArrayList r35, long r36, MessageObject r38, MessageObject r39, TL_stories.StoryItem r40, ChatActivity.ReplyQuote r41, boolean r42, int r43, SendMessageChatArguments r44, long r45, long r47, MessageSuggestionParams r49, PollSendParams r50, ArrayList r51, ArrayList r52) {
        if (r32.isEmpty() == true) goto L5;
    L8:
        prepareSendingDocuments(r34, r32, r35, r33, null, null, null, r36, r38, r39, r40, r41, null, r42, r43, 0, null, r44, 0, false, r45, r47, r49, r50, r51, r52, false);
        return;
    L5:
        if (r33.isEmpty() == false) goto L8;
    }

    /* renamed from: $r8$lambda$6_ZeJYad8SKyQ-KzsnvhRwAJptc, reason: not valid java name */
    public static /* synthetic */ void m4907$r8$lambda$6_ZeJYad8SKyQKzsnvhRwAJptc(MessageObject r20, TLRPC.TL_inputPollAnswer r21, AccountInstance r22, TLRPC.TL_document r23, String r24, HashMap r25, SendingMediaInfo r26, String r27, long r28, MessageObject r30, MessageObject r31, boolean r32, int r33, int r34, TL_stories.StoryItem r35, ChatActivity.ReplyQuote r36, SendMessageChatArguments r37, boolean r38, long r39, boolean r41, long r42, long r44, MessageSuggestionParams r46, PollSendParams r47) {
        if (r20 != null) goto L11;
        if (r21 != null) goto L11;
        SendMessageParams r1 = SendMessageParams.of(r23, null, r24, r28, r30, r31, r26.caption, r26.entities, null, r25, r32, r33, r34, 0, r27, null, false, r26.hasMediaSpoilers);
        r1.replyToStoryItem = r35;
        r1.replyQuote = r36;
        r1.sendMessageChatArguments = r37;
        if (r38 == false) goto L9;
        r1.effect_id = r39;
    L9:
        r1.invert_media = r41;
        r1.payStars = r42;
        r1.monoForumPeer = r44;
        r1.suggestionParams = r46;
        r1.pollIndex = r26.pollIndex;
        r1.pollSendParams = r47;
        r22.getSendMessagesHelper().sendMessage(r1);
        return;
    L11:
        r22.getSendMessagesHelper().editMessage(r20, r21, null, null, r23, r24, null, r25, false, r26.hasMediaSpoilers, r27);
    }

    /* renamed from: $r8$lambda$6uVYkM2hX-EQGciWIRnsG_uqXnU, reason: not valid java name */
    public static /* synthetic */ void m4908$r8$lambda$6uVYkM2hXEQGciWIRnsG_uqXnU(Bitmap[] r22, String[] r23, MessageObject r24, TLRPC.TL_inputPollAnswer r25, AccountInstance r26, TLRPC.TL_photo r27, HashMap r28, SendingMediaInfo r29, String r30, long r31, MessageObject r33, MessageObject r34, boolean r35, int r36, int r37, boolean r38, TL_stories.StoryItem r39, ChatActivity.ReplyQuote r40, SendMessageChatArguments r41, long r42, boolean r44, long r45, long r47, MessageSuggestionParams r49, boolean r50, PollSendParams r51) {
        if (r22[0] != null) goto L5;
    L7:
        if (r24 != null) goto L12;
        if (r25 != null) goto L12;
        SendMessageParams r1 = SendMessageParams.of(r27, null, r31, r33, r34, r29.caption, r29.entities, null, r28, r35, r36, r37, r29.ttl, r30, r38, r29.hasMediaSpoilers);
        r1.replyToStoryItem = r39;
        r1.replyQuote = r40;
        r1.sendMessageChatArguments = r41;
        r1.effect_id = r42;
        r1.invert_media = r44;
        r1.stars = r29.stars;
        r1.pollIndex = r29.pollIndex;
        r1.payStars = r45;
        r1.monoForumPeer = r47;
        r1.suggestionParams = r49;
        r1.sendingHighQuality = r50;
        r1.pollSendParams = r51;
        r26.getSendMessagesHelper().sendMessage(r1);
        return;
    L12:
        r26.getSendMessagesHelper().editMessage(r24, r25, r27, null, null, null, null, r28, false, r29.hasMediaSpoilers, r30);
        return;
    L5:
        if (r23[0] == null) goto L7;
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r22[0]), r23[0], false);
        goto L7
    }

    /* renamed from: $r8$lambda$7AseFQN7NwF_O8j-uL4INW78Lsk, reason: not valid java name */
    public static /* synthetic */ void m4909$r8$lambda$7AseFQN7NwF_O8juL4INW78Lsk(SendMessagesHelper r0, long r1, ArrayList r3) {
        r0.lambda$sendMessage$11(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$7LfsY2XLBrXqg9cs3e_5lcGqFBc(SendMessagesHelper r0, TLObject r1, TLRPC.InputMedia r2, DelayedMessage r3, String r4, MessageObject r5) {
        r0.lambda$performSendDelayedMessage$55(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$7bLCZby4SmFfxwDUcfXoqoeEzqQ(SendMessagesHelper r0, ArrayList r1, boolean r2, boolean r3, TLRPC.Message r4, ArrayList r5, ArrayList r6, int r7) {
        r0.lambda$performSendMessageRequest$96(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$7vVGuq19u0HHHwtvAOxdEvFZux0(SendMessagesHelper r0, TLRPC.InputFile r1, TLRPC.InputMedia r2, DelayedMessage r3, int r4, String r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$performSendDelayedMessage$54(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$94ENb-JI5yVotafzEbvevl5Wb4I, reason: not valid java name */
    public static /* synthetic */ void m4910$r8$lambda$94ENbJI5yVotafzEbvevl5Wb4I(SendMessagesHelper r0, long r1, int r3, boolean r4, boolean r5, LongSparseArray r6, ArrayList r7, ArrayList r8, MessageObject r9, TLRPC.Peer r10, TLRPC.TL_messages_forwardMessages r11, TLObject r12, TLRPC.TL_error r13) {
        r0.lambda$sendMessage$17(r1, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    public static /* synthetic */ void $r8$lambda$97lvxm3ivGaFOSxtmVXnJkK04zw(MessagesStorage.StringCallback r1) {
        r1.run(null);
    }

    public static /* synthetic */ void $r8$lambda$9bw4yYeJKp2XYs_qszUlXvtNYAU(MessagesStorage.LongCallback r2) {
        r2.run(0);
    }

    /* renamed from: $r8$lambda$9ff8bOLGMKjkXa-9va_SGxUytCU, reason: not valid java name */
    public static /* synthetic */ void m4911$r8$lambda$9ff8bOLGMKjkXa9va_SGxUytCU(SendMessagesHelper r0, File r1, MessageObject r2, DelayedMessage r3, String r4) {
        r0.lambda$didReceivedNotification$2(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$9joJPsbyKKeUp3uzPh6hKbKAUsA(MessagesStorage.LongCallback r3) {
        Toast.makeText(ApplicationLoader.applicationContext, LocaleController.getString(R.string.ImportFileTooLarge), 0).show();
        r3.run(0);
    }

    public static /* synthetic */ void $r8$lambda$9vlo1d42TlmORpO6F4YYJ2njN_U(SendMessagesHelper r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$Aun3I_4fXe79IPJDezI3yNefMN4(SendMessagesHelper r0, TLRPC.InputMedia r1, DelayedMessage r2, String r3, MessageObject r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$performSendDelayedMessage$56(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$BzmtOnfU8xeH1qKusgtab8P5A9U(SendMessagesHelper r0, ArrayList r1, int r2, int r3, TLRPC.Message r4, int r5, TLRPC.Message r6, MessageObject r7, int r8) {
        r0.lambda$sendMessage$10(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$C0Q1yjbmkkEiN8Pt96vzpIBOvaA(MessagesStorage.LongCallback r2) {
        r2.run(0);
    }

    public static /* synthetic */ void $r8$lambda$C49wJf1IAMiA75zqldh3ybjOfVw(SendMessagesHelper r0, TL_update.TL_updateNewChannelMessage r1, long r2) {
        r0.lambda$performSendMessageRequestMulti$68(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$CRSl410TE4byQgne2Vq6k8w97Nk(SendMessagesHelper r0, SendMessageParams r1, Long r2) {
        r0.lambda$sendMessage$48(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$C_Q_CqdN20qvMfxtoB5dFGY3kso(SendMessagesHelper r0, String r1, List r2, boolean r3, TLObject r4, MessageObject r5, TL_keyboard.KeyboardButtonProto r6, ChatActivity r7, TwoStepVerificationActivity r8, TLObject[] r9, TLRPC.TL_error r10, TLRPC.InputCheckPasswordSRP r11, boolean r12) {
        r0.lambda$sendCallback$45(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$Cm55TOsTwG250aJg7z1T67z8jS4(SendMessagesHelper r0, TL_update.TL_updateNewChannelMessage r1, long r2) {
        r0.lambda$performSendMessageRequest$93(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$DAjYcVRLBiqC54_1T_gvANZor2w(MessageObject r26, AccountInstance r27, TLRPC.TL_document r28, MessageObject r29, HashMap r30, String r31, long r32, MessageObject r34, MessageObject r35, String r36, ArrayList r37, boolean r38, int r39, int r40, TL_stories.StoryItem r41, SendMessageChatArguments r42, long r43, boolean r45, long r46, PollSendParams r48, int r49) {
        if (r26 == null) goto L6;
        r27.getSendMessagesHelper().editMessage(r26, null, null, r28, r29.messageOwner.attachPath, null, r30, false, false, r31);
        return;
    L6:
        SendMessageParams r0 = SendMessageParams.of(r28, null, r29.messageOwner.attachPath, r32, r34, r35, r36, r37, null, r30, r38, r39, r40, 0, r31, null, false, false);
        r0.replyToStoryItem = r41;
        r0.sendMessageChatArguments = r42;
        r0.effect_id = r43;
        r0.invert_media = r45;
        r0.payStars = r46;
        r0.pollSendParams = r48;
        r0.pollIndex = r49;
        r27.getSendMessagesHelper().sendMessage(r0);
    }

    /* renamed from: $r8$lambda$E50e6TU_fK1C86-P0kKWKLpWQ-A, reason: not valid java name */
    public static /* synthetic */ void m4912$r8$lambda$E50e6TU_fK1C86P0kKWKLpWQA(SendMessagesHelper r0, boolean r1, TLRPC.Message r2, int r3, ArrayList r4, int[] r5, int[] r6, boolean r7, MessageObject r8, SparseArray r9, ArrayList r10, long r11, int r13) {
        r0.lambda$performSendMessageRequestMulti$71(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r13);
    }

    /* renamed from: $r8$lambda$EJ96-MAT1REydW502SxJG4UTpxk, reason: not valid java name */
    public static /* synthetic */ void m4913$r8$lambda$EJ96MAT1REydW502SxJG4UTpxk(SendMessagesHelper r0, long r1, ArrayList r3) {
        r0.lambda$performSendMessageRequestMulti$69(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$ELivVBWPrqNe2I1vsHTA46C9dIg(SendMessagesHelper r0, TwoStepVerificationActivity r1, boolean r2, MessageObject r3, TL_keyboard.KeyboardButtonProto r4, ChatActivity r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$sendCallback$44(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$EpxeE1_uL6azgzcPfa4j8IKdSGY(SendMessagesHelper r0, MessageObject r1) {
        r0.lambda$sendEditRichMessageRequest$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$Eu1MAcKHvakV6cOhRhEhJxFdS50(SendMessagesHelper r0, String r1, List r2) {
        r0.lambda$sendNotificationCallback$28(r1, r2);
    }

    /* renamed from: $r8$lambda$F4uqV5VeF_RmT-14JcziFw8B3xc, reason: not valid java name */
    public static /* synthetic */ void m4914$r8$lambda$F4uqV5VeF_RmT14JcziFw8B3xc(SendMessagesHelper r0, DelayedMessage r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$performSendDelayedMessage$58(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$F8qp4XHNCdM7ozayD3di8NQnV_w(SendMessagesHelper r0, HashMap r1, long r2, ImportingHistory r4, MessagesStorage.LongCallback r5) {
        r0.lambda$prepareImportHistory$109(r1, r2, r4, r5);
    }

    /* renamed from: $r8$lambda$FgKscfjKkN-oA6Co7GSK8oSHV8g, reason: not valid java name */
    public static /* synthetic */ void m4915$r8$lambda$FgKscfjKkNoA6Co7GSK8oSHV8g(SendMessagesHelper r0, TLRPC.Updates r1, TLRPC.Message r2, boolean r3) {
        r0.lambda$performSendMessageRequest$85(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$FoW3QnJ3FhGbMch-C1iq9d6901U, reason: not valid java name */
    public static /* synthetic */ void m4916$r8$lambda$FoW3QnJ3FhGbMchC1iq9d6901U(SendMessagesHelper r0, ArrayList r1, TLObject r2, ArrayList r3, ArrayList r4, DelayedMessage r5, boolean r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$performSendMessageRequestMulti$74(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$G0jWG4BQB0U5mtQEPY9eE97_ILY(Bitmap r22, String r23, MessageObject r24, AccountInstance r25, VideoEditedInfo r26, TLRPC.TL_document r27, String r28, TLRPC.PhotoSize r29, HashMap r30, boolean r31, String r32, long r33, MessageObject r35, MessageObject r36, String r37, ArrayList r38, boolean r39, int r40, int r41, int r42, TL_stories.StoryItem r43, ChatActivity.ReplyQuote r44, SendMessageChatArguments r45, long r46, long r48, long r50, MessageSuggestionParams r52, boolean r53) {
        if (r22 == null) goto L6;
        if (r23 == null) goto L6;
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r22), r23, false);
    L6:
        if (r24 == null) goto L9;
        r25.getSendMessagesHelper().editMessage(r24, null, r26, r27, r28, r29, r30, false, r31, r32);
        return;
    L9:
        SendMessageParams r0 = SendMessageParams.of(r27, r26, r28, r33, r35, r36, r37, r38, null, r30, r39, r40, r41, r42, r32, null, false, r31);
        r0.replyToStoryItem = r43;
        r0.replyQuote = r44;
        r0.sendMessageChatArguments = r45;
        r0.effect_id = r46;
        r0.cover = r29;
        r0.payStars = r48;
        r0.monoForumPeer = r50;
        r0.suggestionParams = r52;
        r0.invert_media = r53;
        r25.getSendMessagesHelper().sendMessage(r0);
    }

    public static /* synthetic */ void $r8$lambda$GQc6EqqqPEA9DR_xtbqFqzLuxUk(TLRPC.TL_document r19, Bitmap[] r20, String[] r21, String r22, long r23, MessageObject r25, MessageObject r26, TLRPC.BotInlineResult r27, HashMap r28, boolean r29, int r30, int r31, TLRPC.TL_photo r32, TLRPC.TL_game r33, SendMessageChatArguments r34, TL_stories.StoryItem r35, ChatActivity.ReplyQuote r36, long r37, long r39, AccountInstance r41) {
        if (r19 != null) goto L5;
        SendMessageParams r0 = null;
        String r02 = null;
        if (r32 == null) goto L16;
        TLRPC.WebDocument r1 = r27.content;
        if (r1 == null) goto L15;
        r02 = r1.url;
    L15:
        String r2 = r02;
        TLRPC.BotInlineMessage r03 = r27.send_message;
        r0 = SendMessageParams.of(r32, r2, r23, r25, r26, r03.message, r03.entities, r03.reply_markup, r28, r29, r30, r31, 0, r27, false);
    L18:
        if (r0 == null) goto L21;
        r0.sendMessageChatArguments = r34;
        r0.replyToStoryItem = r35;
        r0.replyQuote = r36;
        r0.payStars = r37;
        r0.monoForumPeer = r39;
        r41.getSendMessagesHelper().sendMessage(r0);
        return;
    L21:
        return;
    L16:
        if (r33 == null) goto L18;
        r0 = SendMessageParams.of(r33, r23, r25, r26, r27.send_message.reply_markup, r28, r29, r30, r31);
        goto L18
    L5:
        if (r20[0] != null) goto L7;
    L9:
        TLRPC.BotInlineMessage r04 = r27.send_message;
        r0 = SendMessageParams.of(r19, null, r22, r23, r25, r26, r04.message, r04.entities, r04.reply_markup, r28, r29, r30, r31, 0, r27, null, false);
        goto L18
    L7:
        if (r21[0] == null) goto L9;
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r20[0]), r21[0], false);
        goto L9
    }

    public static /* synthetic */ void $r8$lambda$HutbVuCqtplJd8YiRTARJAy6O2I(SendMessagesHelper r0, TLRPC.InputMedia r1, DelayedMessage r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$performSendDelayedMessage$52(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ISBM8M1eM3VRBGoGUuvWk8yCiAk(SendMessagesHelper r0, TLRPC.TL_messages_forwardMessages r1, ArrayList r2, Runnable r3) {
        r0.lambda$sendMessage$19(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$JQkEKlCePB4pTM1O4RwlyWCjHm8(int r2, AccountInstance r3) {
        if (r2 != 1) goto L7;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.UnsupportedAttachment)});     // Catch: Exception -> L10
        return;
    L7:
        if (r2 != 2) goto L14;
        NotificationCenter.getInstance(r3.getCurrentAccount()).postNotificationName(NotificationCenter.currentUserShowLimitReachedDialog, new Object[]{6});     // Catch: Exception -> L10
        return;
    L14:
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$JVvWTl4pxs6jIjGu74hnRbOk1Og(SendMessagesHelper r0, TLObject r1, MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9) {
        r0.lambda$performSendMessageRequest$82(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$JrIAGubflgwAaA2NhMpNIbzxrLg(SendMessagesHelper r0, TLRPC.Updates r1, TLRPC.Message r2, boolean r3) {
        r0.lambda$performSendMessageRequest$88(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Jz1UrWoYKCbKe3VVSHmXLGtQxiY(SendMessagesHelper r0, String r1, List r2, boolean r3, MessageObject r4, TL_keyboard.KeyboardButtonProto r5, ChatActivity r6, TwoStepVerificationActivity r7, TLObject[] r8, TLRPC.InputCheckPasswordSRP r9, boolean r10, TLObject r11, TLRPC.TL_error r12) {
        r0.lambda$sendCallback$46(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$K5a4fbp7aH6UorE1C62I_gMv3qA(SendMessagesHelper r0, TLObject r1, int r2, DelayedMessage r3, ArrayList r4, boolean r5) {
        r0.lambda$performSendMessageRequestMulti$65(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$KjkLJKova5Trs8XDa_d6hzTAwGc(MessageObject r24, AccountInstance r25, TLRPC.TL_document r26, String r27, HashMap r28, String r29, long r30, MessageObject r32, MessageObject r33, String r34, ArrayList r35, boolean r36, int r37, int r38, TL_stories.StoryItem r39, ChatActivity.ReplyQuote r40, SendMessageChatArguments r41, long r42, boolean r44, long r45, long r47, MessageSuggestionParams r49, int r50, PollSendParams r51) {
        if (r24 == null) goto L5;
        r25.getSendMessagesHelper().editMessage(r24, null, null, r26, r27, null, r28, false, false, r29);
        return;
    L5:
        SendMessageParams r0 = SendMessageParams.of(r26, null, r27, r30, r32, r33, r34, r35, null, r28, r36, r37, r38, 0, r29, null, false);
        r0.replyToStoryItem = r39;
        r0.replyQuote = r40;
        r0.sendMessageChatArguments = r41;
        r0.effect_id = r42;
        r0.invert_media = r44;
        r0.payStars = r45;
        r0.monoForumPeer = r47;
        r0.suggestionParams = r49;
        r0.pollIndex = r50;
        r0.pollSendParams = r51;
        r25.getSendMessagesHelper().sendMessage(r0);
    }

    public static /* synthetic */ void $r8$lambda$KkLqv6RZKIKkP_3TkiV8F0eF9h4(MessageObject r19, TLRPC.TL_inputPollAnswer r20, AccountInstance r21, TLRPC.TL_photo r22, boolean r23, SendingMediaInfo r24, HashMap r25, String r26, long r27, MessageObject r29, MessageObject r30, boolean r31, int r32, int r33, TL_stories.StoryItem r34, ChatActivity.ReplyQuote r35, SendMessageChatArguments r36, long r37, boolean r39, long r40, long r42, MessageSuggestionParams r44, PollSendParams r45) {
        String r1 = null;
        if (r19 != null) goto L10;
        if (r20 != null) goto L10;
        if (r23 == false) goto L8;
        r1 = r24.searchImage.imageUrl;
    L8:
        SendMessageParams r12 = SendMessageParams.of(r22, r1, r27, r29, r30, r24.caption, r24.entities, null, r25, r31, r32, r33, r24.ttl, r26, false, r24.hasMediaSpoilers);
        r12.replyToStoryItem = r34;
        r12.replyQuote = r35;
        r12.sendMessageChatArguments = r36;
        r12.effect_id = r37;
        r12.invert_media = r39;
        r12.payStars = r40;
        r12.monoForumPeer = r42;
        r12.suggestionParams = r44;
        r12.pollIndex = r24.pollIndex;
        r12.pollSendParams = r45;
        r21.getSendMessagesHelper().sendMessage(r12);
        return;
    L10:
        SendMessagesHelper r2 = r21.getSendMessagesHelper();
        if (r23 == false) goto L13;
        r1 = r24.searchImage.imageUrl;
    L13:
        String r332 = r1;
        r2.editMessage(r19, r20, r22, null, null, r332, null, r25, false, r24.hasMediaSpoilers, r26);
    }

    public static /* synthetic */ void $r8$lambda$L0VqxsBdrkPPwWir1djC3sdYljw(CharSequence r12, AccountInstance r13, long r14, long r16, boolean r18, int r19, int r20, long r21) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda10(r12, r13, r14, r16, r18, r19, r20, r21));
    }

    public static /* synthetic */ void $r8$lambda$L5qt1T7v1R99jOjTujek6AnlGug(SendMessagesHelper r0, TLRPC.Message r1, long r2, int r4, TLRPC.Message r5, int r6, int r7) {
        r0.lambda$sendMessage$12(r1, r2, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$LS-HTLbpcWgJ-nlVqhJl7IZxlP0, reason: not valid java name */
    public static /* synthetic */ void m4917$r8$lambda$LSHTLbpcWgJnlVqhJl7IZxlP0(SendMessagesHelper r0, TLRPC.Message r1, int r2, int r3, boolean r4) {
        r0.lambda$performSendMessageRequest$98(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$LU69N4z-hnvyP8RmHdT7BZfuwB0, reason: not valid java name */
    public static /* synthetic */ void m4918$r8$lambda$LU69N4zhnvyP8RmHdT7BZfuwB0(BaseFragment r2) {
        AlertsCreator.showSendMediaAlert(7, r2, null);
    }

    public static /* synthetic */ void $r8$lambda$LldrgMwurgfZtJuZu4jQseZTl8o(SendMessagesHelper r0, DelayedMessage r1, File r2, MessageObject r3) {
        r0.lambda$didReceivedNotification$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$NNP9suoT83UHwZ1_X4q25LO1jb0(SendMessagesHelper r0, long r1, int r3, byte[] r4) {
        r0.lambda$sendNotificationCallback$30(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$NNV5u1sNtx40abiOsYGczN49DG4(String r0) {
    }

    public static /* synthetic */ void $r8$lambda$NPU45zoaZ73lwqCTCgCHKwUKgoI(SendMessagesHelper r0, TLRPC.Message r1, int r2) {
        r0.lambda$sendMessage$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NcNRAo3lMaZTshI9Dsf_RfSrUfA(SendMessagesHelper r0, boolean r1, MessageObject r2, TL_keyboard.KeyboardButtonProto r3, TwoStepVerificationActivity r4, ChatActivity r5, TLRPC.InputCheckPasswordSRP r6) {
        r0.lambda$sendCallback$40(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$NlsDr2U4j2z56UjkBofhrz0FhwM(SendMessagesHelper r0, MessageObject r1) {
        r0.lambda$sendEditRichMessageRequest$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$OduDm7XnAQXae7yS8qTRO0ds0Aw(SendMessagesHelper r0, TLObject r1, ArrayList r2, ArrayList r3, ArrayList r4, DelayedMessage r5, boolean r6) {
        r0.lambda$performSendMessageRequestMulti$63(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$OeG7jValf4fnEtxydK6yYsOnpww(SendMessagesHelper r0, TLRPC.Message r1, boolean r2) {
        r0.lambda$performSendMessageRequest$87(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$PvysHO6u4V4q7aQREpA0atGilJk(SendMessagesHelper r0, TL_update.TL_updateNewMessage r1) {
        r0.lambda$performSendMessageRequest$91(r1);
    }

    /* renamed from: $r8$lambda$Q-zZ81a6exdLALDMmxCSurGR3fw, reason: not valid java name */
    public static /* synthetic */ void m4919$r8$lambda$QzZ81a6exdLALDMmxCSurGR3fw(SendMessagesHelper r0, TLRPC.Updates r1, TLRPC.TL_error r2) {
        r0.lambda$deletePollOption$27(r1, r2);
    }

    /* renamed from: $r8$lambda$RUhXUNrrtGspX5UB-ufOakCep-k, reason: not valid java name */
    public static /* synthetic */ void m4920$r8$lambda$RUhXUNrrtGspX5UBufOakCepk(SendMessagesHelper r0, int r1, TLRPC.Message r2, int r3, int r4, TLRPC.Message r5, MessageObject r6, int r7) {
        r0.lambda$sendMessage$8(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$RZGxbwPtM0d89D9JckTqcKfNOIM(Runnable r0) {
        AndroidUtilities.runOnUIThread(r0);
    }

    /* renamed from: $r8$lambda$T9StOUuA_8Q_YTzpHIC6-WeS6C0, reason: not valid java name */
    public static /* synthetic */ void m4921$r8$lambda$T9StOUuA_8Q_YTzpHIC6WeS6C0(SendMessagesHelper r0, BaseFragment r1, TLRPC.TL_inputMediaStakeDice r2, TLObject r3, MessageObject r4, String r5, DelayedMessage r6, boolean r7, DelayedMessage r8, Object r9, HashMap r10, boolean r11) {
        r0.lambda$performSendMessageRequest$83(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v27, types: [org.telegram.tgnet.TLObject, org.telegram.tgnet.TLRPC$Document] */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v13, types: [org.telegram.messenger.FileLoader] */
    /* JADX WARN: Type inference failed for: r4v19, types: [org.telegram.messenger.FileLoader] */
    public static /* synthetic */ void $r8$lambda$TEMEmB64MkaRJTk7fkPbuELgyE0(long r27, TLRPC.BotInlineResult r29, AccountInstance r30, HashMap r31, BaseFragment r32, MessageObject r33, MessageObject r34, boolean r35, int r36, int r37, SendMessageChatArguments r38, TL_stories.StoryItem r39, ChatActivity.ReplyQuote r40, long r41, long r43) {
        boolean r3 = DialogObject.isEncryptedDialog(r27);
        if ("game".equals(r29.type) == false) goto L16;
        if (r3 == true) goto L243;
        TLRPC.TL_game r0 = new TLRPC.TL_game();
        r0.title = r29.title;
        r0.description = r29.description;
        r0.short_name = r29.id;
        TLRPC.Photo r4 = r29.photo;
        r0.photo = r4;
        if (r4 != null) goto L9;
        r0.photo = new TLRPC.TL_photoEmpty();
    L9:
        TLRPC.Document r42 = r29.document;
        if ((r42 instanceof TLRPC.TL_document) == false) goto L12;
        r0.document = r42;
        r0.flags |= 1;
    L12:
        TLRPC.TL_photo r1 = null;
    L13:
        TLRPC.TL_photo r11 = r1;
        TLObject r12 = r1;
    L14:
        TLRPC.TL_photo r14 = r11;
        ?? r13 = r12;
    L190:
        if (r31 == null) goto L194;
        TLRPC.WebDocument r2 = r29.content;
        if (r2 == null) goto L194;
        r31.put("originalPath", r2.url);
    L194:
        Bitmap[] r122 = new Bitmap[1];
        String[] r132 = new String[1];
        if (r3 == false) goto L205;
        if (r13 == 0) goto L205;
        int r22 = 0;
    L199:
        if (r22 >= r13.attributes.size()) goto L205;
        if ((r13.attributes.get(r22) instanceof TLRPC.TL_documentAttributeVideo) == false) goto L203;
        TLRPC.TL_documentAttributeVideo r44 = (TLRPC.TL_documentAttributeVideo) r13.attributes.get(r22);
        TLRPC.TL_documentAttributeVideo_layer159 r5 = new TLRPC.TL_documentAttributeVideo_layer159();
        r5.flags = r44.flags;
        r5.round_message = r44.round_message;
        r5.supports_streaming = r44.supports_streaming;
        r5.duration = r44.duration;
        r5.w = r44.w;
        r5.h = r44.h;
        r13.attributes.set(r22, r5);
    L203:
        r22 = r22 + 1;
    L205:
        if (MessageObject.isGifDocument(r13) == false) goto L211;
        TLRPC.PhotoSize r23 = FileLoader.getClosestPhotoSizeWithSize(r13.thumbs, 320);
        File r45 = FileLoader.getInstance(r30.getCurrentAccount()).getPathToAttach(r13);
        if (r45.exists() == true) goto L209;
        boolean r52 = true;
        r45 = FileLoader.getInstance(r30.getCurrentAccount()).getPathToAttach(r13, true);
    L210:
        String r46 = r45.getAbsolutePath();
        boolean r15 = r52;
        TLRPC.TL_document r47 = r13;
        AccountInstance r24 = r30;
        ensureMediaThumbExists(r24, r3, r47, r46, null, 0);
        r132[0] = getKeyForPhotoSize(r24, r23, r122, r15, r15);
    L213:
        if (DialogObject.isEncryptedDialog(r27) == true) goto L215;
        long r53 = r27;
        TLRPC.InputPeer r152 = r24.getMessagesController().getInputPeer(r53);
    L216:
        if (r152 != null) goto L218;
    L230:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda65(r47, r122, r132, r11, r53, r33, r34, r29, r31, r35, r36, r37, r14, r0, r38, r39, r40, r41, r43, r24));
        return;
    L218:
        if (r152.user_id == 0) goto L230;
        if (r24.getMessagesController().getUserFull(r152.user_id) == null) goto L230;
        if (r24.getMessagesController().getUserFull(r152.user_id).voice_messages_forbidden == false) goto L230;
        if (r47 == null) goto L230;
        if (MessageObject.isVoiceDocument(r47) == false) goto L228;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda63(r32));
        return;
    L228:
        if (MessageObject.isRoundVideoDocument(r47) == false) goto L230;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda64(r32));
        return;
    L215:
        r53 = r27;
        r152 = null;
        goto L216
    L209:
        r52 = true;
        goto L210
    L211:
        r24 = r30;
        r47 = r13;
        goto L213
    L243:
        return;
    L16:
        if ((r29 instanceof TLRPC.TL_botInlineMediaResult) == false) goto L27;
        TLObject r02 = r29.document;
        if (r02 != null) goto L20;
        TLRPC.Photo r03 = r29.photo;
        if (r03 != null) goto L25;
    L189:
        r0 = null;
        r1 = null;
        goto L13
    L25:
        if ((r03 instanceof TLRPC.TL_photo) == false) goto L189;
        r14 = (TLRPC.TL_photo) r03;
        r0 = null;
        r13 = 0;
        r11 = null;
        goto L190
    L20:
        if ((r02 instanceof TLRPC.TL_document) == false) goto L189;
        r12 = (TLRPC.TL_document) r02;
        r0 = null;
        r11 = null;
        goto L14
    L27:
        TLRPC.WebDocument r04 = r29.content;
        if (r04 == null) goto L189;
        String r05 = ImageLoader.getHttpUrlExtension(r04.url, null);
        if (TextUtils.isEmpty(r05) == false) goto L32;
        String r06 = FileLoader.getExtensionByMimeType(r29.content.mime_type);
    L33:
        File r48 = new File(FileLoader.getDirectory(4), Utilities.MD5(r29.content.url) + r06);
        if (r48.exists() == false) goto L37;
        String r07 = r48.getAbsolutePath();
    L36:
        String r6 = r07;
        String r08 = r29.type;
        r08.getClass();
        char r17 = 5;
        switch(r08.hashCode()) {
            case -1890252483: goto L66;
            case 102340: goto L62;
            case 3143036: goto L58;
            case 93166550: goto L54;
            case 106642994: goto L50;
            case 112202875: goto L46;
            case 112386354: goto L42;
            default: goto L40;
        };
    L40:
        char r09 = 65535;
    L70:
        switch(r09) {
            case 0: goto L79;
            case 1: goto L79;
            case 2: goto L79;
            case 3: goto L79;
            case 4: goto L73;
            case 5: goto L79;
            case 6: goto L79;
            default: goto L71;
        };
    L71:
        r11 = r6;
        r0 = null;
        r13 = 0;
        r14 = null;
        goto L190
    L79:
        TLRPC.TL_document r49 = new TLRPC.TL_document();
        r49.id = 0;
        r49.size = 0;
        r49.dc_id = 0;
        r49.mime_type = r29.content.mime_type;
        r49.file_reference = new byte[0];
        r49.date = r30.getConnectionsManager().getCurrentTime();
        TLRPC.TL_documentAttributeFilename r62 = new TLRPC.TL_documentAttributeFilename();
        r49.attributes.add(r62);
        String r010 = r29.type;
        r010.getClass();
        switch(r010.hashCode()) {
            case -1890252483: goto L102;
            case 102340: goto L98;
            case 3143036: goto L94;
            case 93166550: goto L90;
            case 112202875: goto L86;
            case 112386354: goto L83;
            default: goto L81;
        };
    L81:
        r17 = 65535;
    L106:
        switch(r17) {
            case 0: goto L166;
            case 1: goto L130;
            case 2: goto L126;
            case 3: goto L122;
            case 4: goto L109;
            case 5: goto L108;
            default: goto L107;
        };
    L107:
        String r16 = r6;
    L180:
        if (r62.file_name != null) goto L183;
        r62.file_name = "file";
    L183:
        if (r49.mime_type != null) goto L186;
        r49.mime_type = "application/octet-stream";
    L186:
        if (r49.thumbs.isEmpty() == false) goto L188;
        TLRPC.TL_photoSize r011 = new TLRPC.TL_photoSize();
        int[] r25 = MessageObject.getInlineResultWidthAndHeight(r29);
        r011.w = r25[0];
        r011.h = r25[1];
        r011.size = 0;
        r011.location = new TLRPC.TL_fileLocationUnavailable();
        r011.type = "x";
        r49.thumbs.add(r011);
        r49.flags |= 1;
    L188:
        r11 = r16;
        r13 = r49;
        r0 = null;
        r14 = null;
        goto L190
    L108:
        TLRPC.TL_documentAttributeAudio r012 = new TLRPC.TL_documentAttributeAudio();
        r012.duration = MessageObject.getInlineResultDuration(r29);
        r012.voice = true;
        r62.file_name = "audio.ogg";
        r49.attributes.add(r012);
        goto L107
    L109:
        r62.file_name = "video.mp4";
        TLRPC.TL_documentAttributeVideo r7 = new TLRPC.TL_documentAttributeVideo();
        int[] r8 = MessageObject.getInlineResultWidthAndHeight(r29);
        r7.w = r8[0];
        r7.h = r8[1];
        r7.duration = MessageObject.getInlineResultDuration(r29);
        r7.supports_streaming = true;
        r49.attributes.add(r7);
    L117:
        th = move-exception;
        FileLog.e(th);
        goto L107
    L111:
        if (r29.thumb == null) goto L107;
        Bitmap r013 = ImageLoader.loadBitmap(new File(FileLoader.getDirectory(4), Utilities.MD5(r29.thumb.url) + "." + ImageLoader.getHttpUrlExtension(r29.thumb.url, "jpg")).getAbsolutePath(), null, 90.0f, 90.0f, true);     // Catch: Throwable -> L117
        if (r013 == null) goto L107;
        TLRPC.PhotoSize r26 = ImageLoader.scaleAndSaveImage(r013, 90.0f, 90.0f, 55, false);     // Catch: Throwable -> L117
        if (r26 == null) goto L119;
        r49.thumbs.add(r26);     // Catch: Throwable -> L117
        r49.flags |= 1;
    L119:
        r013.recycle();     // Catch: Throwable -> L117
        goto L107
    L122:
        TLRPC.TL_documentAttributeAudio r014 = new TLRPC.TL_documentAttributeAudio();
        r014.duration = MessageObject.getInlineResultDuration(r29);
        r014.title = r29.title;
        int r18 = r014.flags;
        r014.flags = r18 | 1;
        String r28 = r29.description;
        if (r28 == null) goto L125;
        r014.performer = r28;
        r014.flags = r18 | 3;
    L125:
        r62.file_name = "audio.mp3";
        r49.attributes.add(r014);
        goto L107
    L126:
        int r015 = r29.content.mime_type.lastIndexOf(47);
        if (r015 == (-1)) goto L129;
        r62.file_name = "file.".concat(r29.content.mime_type.substring(r015 + 1));
        goto L107
    L129:
        r62.file_name = "file";
        goto L107
    L130:
        r62.file_name = "animation.gif";
        r16 = r6;
        if (r16.endsWith("mp4") == false) goto L133;
        r49.mime_type = "video/mp4";
        r49.attributes.add(new TLRPC.TL_documentAttributeAnimated());
    L135:
        if (r3 == false) goto L137;
        int r82 = 90;
    L232:
    L149:
        th = move-exception;
        FileLog.e(th);
        goto L180
    L139:
        if (r16.endsWith("mp4") == false) goto L153;
        Bitmap r016 = createVideoThumbnail(r16, 1);     // Catch: Throwable -> L149
        if (r016 != null) goto L154;
        TLRPC.WebDocument r112 = r29.thumb;     // Catch: Throwable -> L149
        if ((r112 instanceof TLRPC.TL_webDocument) == false) goto L154;
        if ("video/mp4".equals(r112.mime_type) == false) goto L154;
        String r017 = ImageLoader.getHttpUrlExtension(r29.thumb.url, null);     // Catch: Throwable -> L149
        if (TextUtils.isEmpty(r017) == false) goto L151;
        String r018 = FileLoader.getExtensionByMimeType(r29.thumb.mime_type);     // Catch: Throwable -> L149
    L152:
        r016 = createVideoThumbnail(new File(FileLoader.getDirectory(4), Utilities.MD5(r29.thumb.url) + r018).getAbsolutePath(), 1);     // Catch: Throwable -> L149
        goto L154
    L151:
        r018 = "." + r017;     // Catch: Throwable -> L149
    L154:
        if (r016 == null) goto L180;
        float r54 = r82;
        if (r82 <= 90) goto L159;
        int r210 = 80;
    L160:
        TLRPC.PhotoSize r211 = ImageLoader.scaleAndSaveImage(r016, r54, r54, r210, false);     // Catch: Throwable -> L149
        if (r211 == null) goto L163;
        r49.thumbs.add(r211);     // Catch: Throwable -> L149
        r49.flags |= 1;
    L163:
        r016.recycle();     // Catch: Throwable -> L149
        goto L180
    L159:
        r210 = 55;
        goto L160
    L153:
        float r019 = r82;
        r016 = ImageLoader.loadBitmap(r16, null, r019, r019, true);     // Catch: Throwable -> L149
        goto L154
    L137:
        r82 = 320;
        goto L232
    L133:
        r49.mime_type = "image/gif";
        goto L135
    L166:
        r16 = r6;
        TLRPC.TL_documentAttributeSticker r020 = new TLRPC.TL_documentAttributeSticker();
        r020.alt = _UrlKt.FRAGMENT_ENCODE_SET;
        r020.stickerset = new TLRPC.TL_inputStickerSetEmpty();
        r49.attributes.add(r020);
        TLRPC.TL_documentAttributeImageSize r021 = new TLRPC.TL_documentAttributeImageSize();
        int[] r72 = MessageObject.getInlineResultWidthAndHeight(r29);
        r021.w = r72[0];
        r021.h = r72[1];
        r49.attributes.add(r021);
        r62.file_name = "sticker.webp";
    L174:
        th = move-exception;
        FileLog.e(th);
        goto L180
    L168:
        if (r29.thumb == null) goto L180;
        Bitmap r022 = ImageLoader.loadBitmap(new File(FileLoader.getDirectory(4), Utilities.MD5(r29.thumb.url) + "." + ImageLoader.getHttpUrlExtension(r29.thumb.url, "webp")).getAbsolutePath(), null, 90.0f, 90.0f, true);     // Catch: Throwable -> L174
        if (r022 == null) goto L180;
        TLRPC.PhotoSize r212 = ImageLoader.scaleAndSaveImage(r022, 90.0f, 90.0f, 55, false);     // Catch: Throwable -> L174
        if (r212 == null) goto L176;
        r49.thumbs.add(r212);     // Catch: Throwable -> L174
        r49.flags |= 1;
    L176:
        r022.recycle();     // Catch: Throwable -> L174
        goto L180
    L83:
        if (r010.equals("voice") == true) goto L106;
    L86:
        if (r010.equals(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L81;
        r17 = 4;
        goto L106
    L90:
        if (r010.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L81;
        r17 = 3;
        goto L106
    L94:
        if (r010.equals("file") == false) goto L81;
        r17 = 2;
        goto L106
    L98:
        if (r010.equals("gif") == false) goto L81;
        r17 = 1;
        goto L106
    L102:
        if (r010.equals("sticker") == false) goto L81;
        r17 = 0;
        goto L106
    L73:
        if (r48.exists() == false) goto L75;
        TLRPC.TL_photo r023 = r30.getSendMessagesHelper().generatePhotoSizes(r6, null);
    L76:
        if (r023 != null) goto L78;
        r023 = new TLRPC.TL_photo();
        r023.date = r30.getConnectionsManager().getCurrentTime();
        r023.file_reference = new byte[0];
        TLRPC.TL_photoSize r410 = new TLRPC.TL_photoSize();
        int[] r55 = MessageObject.getInlineResultWidthAndHeight(r29);
        r410.w = r55[0];
        r410.h = r55[1];
        r410.size = 1;
        r410.location = new TLRPC.TL_fileLocationUnavailable();
        r410.type = "x";
        r023.sizes.add(r410);
    L78:
        r14 = r023;
        r11 = r6;
        r0 = null;
        r13 = 0;
        goto L190
    L75:
        r023 = null;
        goto L76
    L42:
        if (r08.equals("voice") == false) goto L40;
        r09 = 6;
        goto L70
    L46:
        if (r08.equals(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L40;
        r09 = 5;
        goto L70
    L50:
        if (r08.equals("photo") == false) goto L40;
        r09 = 4;
        goto L70
    L54:
        if (r08.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L40;
        r09 = 3;
        goto L70
    L58:
        if (r08.equals("file") == false) goto L40;
        r09 = 2;
        goto L70
    L62:
        if (r08.equals("gif") == false) goto L40;
        r09 = 1;
        goto L70
    L66:
        if (r08.equals("sticker") == false) goto L40;
        r09 = 0;
        goto L70
    L37:
        r07 = r29.content.url;
        goto L36
    L32:
        r06 = "." + r05;
        goto L33
    }

    public static /* synthetic */ void $r8$lambda$TTz81_s7ZHjTOo0WgN1JR8VzNqE(SendMessagesHelper r0, TLRPC.Message r1, boolean r2) {
        r0.lambda$putToSendingMessages$61(r1, r2);
    }

    /* renamed from: $r8$lambda$UVbzgUcEbu_1p3-IGit00eh3C3A, reason: not valid java name */
    public static /* synthetic */ void m4922$r8$lambda$UVbzgUcEbu_1p3IGit00eh3C3A(SendMessagesHelper r0, TLRPC.Message r1, boolean r2) {
        r0.lambda$performSendMessageRequest$84(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ui3xRipElCUgMUqQFyaERr5VmfI(SendMessagesHelper r0, TLRPC.TL_error r1, TLRPC.Message r2, TLObject r3, MessageObject r4, String r5, HashMap r6, boolean r7, TLObject r8) {
        r0.lambda$performSendMessageRequest$86(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$UvvhRMwtKSSKzlnkuLrFyAsWCks(SendMessagesHelper r0, TLRPC.Message r1) {
        r0.lambda$performSendMessageRequest$103(r1);
    }

    /* renamed from: $r8$lambda$WLwg3ZFTi55qaSu4-Q-3dQwjMtY, reason: not valid java name */
    public static /* synthetic */ void m4923$r8$lambda$WLwg3ZFTi55qaSu4Q3dQwjMtY(SendMessagesHelper r0, TLRPC.TL_error r1, TLRPC.TL_messages_forwardMessages r2) {
        r0.lambda$sendMessage$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Yg4cZ17LA6RVARo7CEtG6pp2sY0(SendMessagesHelper r0, TLObject r1, DelayedMessage r2, String r3) {
        r0.lambda$performSendDelayedMessage$57(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$YrV7br_TS9XPOoR1ZmFTbAqJAP8(CharSequence r23, AccountInstance r24, long r25, long r27, boolean r29, int r30, int r31, long r32) {
        CharSequence r2 = getTrimmedString(r23);
        int r3 = r24.getMessagesController().getMaxMessageLength();
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.length() == 0) goto L27;
        int r4 = (int) Math.ceil(r2.length() / r3);
        if (r25 == 0) goto L14;
        long r9 = r27;
        TLRPC.TL_forumTopic r0 = r24.getMessagesController().getTopicsController().findTopic(-r9, r25);
        if (r0 != null) goto L11;
    L15:
        MessageObject r1 = null;
    L13:
        MessageObject r11 = r1;
        int r02 = 0;
    L17:
        if (r02 >= r4) goto L28;
        int r5 = r02 + 1;
        CharSequence[] r8 = {r2.subSequence(r02 * r3, Math.min(r5 * r3, r2.length()))};
        ArrayList<TLRPC.MessageEntity> r12 = r24.getMediaDataController().getEntities(r8, true);
        SendMessageParams r82 = SendMessageParams.of(r8[0].toString(), r9, r11, r11, null, true, null, null, null, r29, r30, r31, null, false);
        r82.entities = r12;
        if (r02 != 0) goto L22;
        r82.effect_id = r32;
    L22:
        r24.getSendMessagesHelper().sendMessage(r82);
        r9 = r27;
        r02 = r5;
        goto L17
    L28:
        return;
    L11:
        if (r0.topicStartMessage == null) goto L15;
        r1 = new MessageObject(r24.getCurrentAccount(), r0.topicStartMessage, false, false);
        r1.isTopicMainMessage = true;
        goto L13
    L14:
        r9 = r27;
        goto L15
    }

    /* renamed from: $r8$lambda$Z0mmRoe_3Z-qiIuyBCStRviR0tU, reason: not valid java name */
    public static /* synthetic */ void m4924$r8$lambda$Z0mmRoe_3ZqiIuyBCStRviR0tU(SendMessagesHelper r0, TLObject r1, DelayedMessage r2) {
        r0.lambda$performSendDelayedMessage$49(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZA8Huec7tQTOW3wYtfCmHF4ofUM(ChatActivity r1, AlertDialog r2, int r3) {
        r1.presentFragment(new TwoStepVerificationSetupActivity(6, null));
    }

    public static /* synthetic */ void $r8$lambda$ZCFe8DLrX3_tKzKZxsDKZM8tKIM(SendMessagesHelper r0, MessageObject r1, boolean r2, String r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$sendVote$32(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$ZNzWfW1SOO4CUaQIysCx5hiVXyc(SendMessagesHelper r0, TLRPC.TL_error r1, TLRPC.Message r2, TLObject r3, MessageObject r4, String r5, HashMap r6, boolean r7, TLObject r8) {
        r0.lambda$performSendMessageRequest$89(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$ZWdFPZNfPffosdT6-W-MBkZHR9o, reason: not valid java name */
    public static /* synthetic */ void m4925$r8$lambda$ZWdFPZNfPffosdT6WMBkZHR9o(PollSendParams r33, ArrayList r34, long r35, AccountInstance r37, CharSequence r38, boolean r39, ArrayList r40, MessageObject r41, MessageObject r42, MessageObject r43, boolean r44, int r45, int r46, TL_stories.StoryItem r47, SendMessageChatArguments r48, long r49, boolean r51, long r52, Runnable r54) {
        PollSendParams r0 = r33;
        ArrayList r1 = r40;
        long r2 = 0;
        if (r0 == null) goto L5;
        long r4 = r0.groupId;
    L6:
        int r6 = 1;
        if (r0 == null) goto L9;
        boolean r26 = true;
    L10:
        int r7 = r34.size();
        if (r4 == 0) goto L13;
        r2 = r4;
    L13:
        int r410 = 0;
        int r5 = 0;
    L14:
        if (r410 >= r7) goto L85;
        MessageObject r9 = (MessageObject) r34.get(r410);
        String r10 = r9.messageOwner.attachPath;
        File r11 = new File(r10);
        boolean r13 = DialogObject.isEncryptedDialog(r35);
        if (r13 == true) goto L22;
        if (r7 <= r6) goto L22;
        if ((r5 % 10) != 0) goto L22;
        if (r0 != null) goto L22;
        r2 = Utilities.random.nextLong();
        r5 = 0;
    L22:
        if (r10 != null) goto L24;
    L26:
        String r15 = r10;
        if (r13 == false) goto L29;
    L40:
        String r102 = null;
        TLRPC.TL_document r14 = null;
    L41:
        if (r14 != null) goto L43;
        r14 = (TLRPC.TL_document) r9.messageOwner.media.document;
    L43:
        if (r14 != null) goto L45;
    L55:
        int r16 = r6;
    L56:
        if (r13 == true) goto L58;
    L60:
        int r62 = r16;
        CharSequence[] r112 = new CharSequence[r62];
        r112[0] = r38;
        if (r410 != 0) goto L63;
        ArrayList<TLRPC.MessageEntity> r12 = r37.getMediaDataController().getEntities(r112, r62);
    L64:
        if (r410 != 0) goto L67;
        String r113 = r112[0].toString();
    L66:
        int r63 = r5;
        HashMap r53 = new HashMap();
        if (r15 == null) goto L71;
        r53.put("originalPath", r15);
    L71:
        if (r102 == null) goto L73;
        r53.put("parentObject", r102);
    L73:
        int r64 = r63 + 1;
        r53.put("groupId", _UrlKt.FRAGMENT_ENCODE_SET + r2);
        if (r39 == true) goto L81;
        if (r0 != null) goto L79;
        if (r64 != 10) goto L79;
    L80:
        r53.put("final", "1");
    L79:
        if (r410 == (r7 - 1)) goto L80;
    L81:
        if (r1 == null) goto L83;
        int r24 = ((Integer) r1.get(r410)).intValue();
    L84:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda2(r41, r37, r14, r9, r53, r102, r35, r42, r43, r113, r12, r44, r45, r46, r47, r48, r49, r51, r52, r33, r24));
        r410 = r410 + 1;
        r0 = r33;
        r1 = r40;
        r7 = r7;
        r2 = r2;
        r5 = r64;
        r6 = 1;
        goto L14
    L83:
        r24 = 0;
        goto L84
    L67:
        r113 = null;
        goto L66
    L63:
        r12 = null;
        goto L64
    L58:
        if (r37.getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r35))) != null) goto L60;
        return;
    L45:
        if (r14.thumbs.isEmpty() == false) goto L55;
        AudioInfo r114 = AudioInfo.getAudioInfo(r11);
        if (r114 == null) goto L49;
        Bitmap r115 = r114.getCover();
    L50:
        if (r115 == null) goto L55;
        r16 = r6;
        TLRPC.PhotoSize r65 = ImageLoader.scaleAndSaveImage(r115, 132.0f, 132.0f, 55, r13);
        if (r65 == null) goto L54;
        r14.thumbs.add(r65);
        r14.flags |= 1;
    L54:
        r115.recycle();
        goto L56
    L49:
        r115 = null;
        goto L50
    L29:
        if (TextUtils.isEmpty(r15) == true) goto L40;
        if (r26 == true) goto L40;
        MessagesStorage r122 = r37.getMessagesStorage();
        if (r13 == true) goto L34;
        int r142 = r6;
    L35:
        Object[] r123 = r122.getSentFile(r15, r142);
        if (r123 == null) goto L40;
        Object r143 = r123[0];
        if ((r143 instanceof TLRPC.TL_document) == false) goto L40;
        r14 = (TLRPC.TL_document) r143;
        r102 = (String) r123[r6];
        ensureMediaThumbExists(r37, r13, r14, r15, null, 0);
        goto L41
    L34:
        r142 = 4;
        goto L35
    L24:
        if (r10.length() <= 0) goto L26;
        r10 = r10 + MediaStreamTrack.AUDIO_TRACK_KIND + r11.length();
        goto L26
    L85:
        if (r54 == null) goto L92;
        AndroidUtilities.runOnUIThread(r54);
        return;
    L92:
        return;
    L9:
        r26 = false;
        goto L10
    L5:
        r4 = 0;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$ZeAJNpnXkYYmo0X6mCEafxmCwM4(SendMessagesHelper r0, String r1, Runnable r2) {
        r0.lambda$sendVote$31(r1, r2);
    }

    /* renamed from: $r8$lambda$ZuUj6zl1T_9kK7N-b5zDvm2aN9U, reason: not valid java name */
    public static /* synthetic */ void m4926$r8$lambda$ZuUj6zl1T_9kK7Nb5zDvm2aN9U(SendMessagesHelper r0, MessageObject r1, TLRPC.TodoItem r2, boolean r3, long r4, int r6, Runnable r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$toggleTodo$34(r1, r2, r3, r4, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$aHbsFeRnFkOCZgzz_s8vrPttiuY(SendMessagesHelper r0, TLObject r1, MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9) {
        r0.lambda$performSendMessageRequest$78(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$aXQ2OE6ys1m1CQTYxExn2sgeD3U(SendMessagesHelper r0, DelayedMessage r1, File r2, TLRPC.Document r3, MessageObject r4) {
        r0.lambda$didReceivedNotification$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$bi2Z3UWcIG2mumXgzFncexHVhGA(SendMessagesHelper r0, ArrayList r1) {
        r0.lambda$sendMessage$16(r1);
    }

    /* renamed from: $r8$lambda$cFEfd8IIC7CVQ-E-fQylG6BQY98, reason: not valid java name */
    public static /* synthetic */ void m4927$r8$lambda$cFEfd8IIC7CVQEfQylG6BQY98(SendMessagesHelper r0, boolean r1, TLRPC.Message r2, int r3, ArrayList r4, int r5) {
        r0.lambda$performSendMessageRequest$99(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$clfJ1Gx0Frr7DW6Xwo5QYQFLfUk(SendMessagesHelper r0, TLRPC.Updates r1) {
        r0.lambda$performSendMessageRequest$94(r1);
    }

    public static /* synthetic */ void $r8$lambda$ctcWfbj_9AVLJDgstmPeb5ZXHCU(SendMessagesHelper r0, ArrayList r1, ArrayList r2, ArrayList r3, ArrayList r4, ArrayList r5) {
        r0.lambda$processUnsentMessages$104(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$cwWncDiXCnrgxeZ0mUV2CAB-_CE, reason: not valid java name */
    public static /* synthetic */ void m4928$r8$lambda$cwWncDiXCnrgxeZ0mUV2CAB_CE(SendMessagesHelper r0, String r1, List r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$sendNotificationCallback$29(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$d8jcHpErRmjjK7rz9YXVNXkPvxg(SendMessagesHelper r0, TLObject r1, TLRPC.InputMedia r2, DelayedMessage r3) {
        r0.lambda$uploadMultiMedia$59(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$dIMXoHoGQzxQ8PQGP6SRtUHqWJI(SendMessagesHelper r0, ArrayList r1, ArrayList r2, ArrayList r3, DelayedMessage r4, boolean r5, TLObject r6) {
        r0.lambda$performSendMessageRequestMulti$64(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$dY8JxFH0aAiY70ZOWG1qLUhkQik(SendMessagesHelper r0, TLRPC.Message r1, int r2) {
        r0.lambda$performSendMessageRequest$102(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dm2JANBaN2MgF54vR_smZH6YbJE(SendMessagesHelper r0, TLObject r1, TLRPC.InputFile r2, TLRPC.InputMedia r3, DelayedMessage r4, int r5, String r6) {
        r0.lambda$performSendDelayedMessage$53(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$e40JzXTX4qX1ghLq5Mo7GK9ZHDc(SendMessagesHelper r0, DelayedMessage r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$performSendDelayedMessage$50(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$e4JsZR3XR0lf0ZFNfNCariyyWng(SendMessagesHelper r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$sendReaction$35(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$eRqTR7KRRS3qekdEb6Yv8-3YQbI, reason: not valid java name */
    public static /* synthetic */ void m4929$r8$lambda$eRqTR7KRRS3qekdEb6Yv83YQbI(Bitmap r24, String r25, MessageObject r26, TLRPC.TL_inputPollAnswer r27, AccountInstance r28, VideoEditedInfo r29, TLRPC.TL_document r30, String r31, HashMap r32, SendingMediaInfo r33, String r34, long r35, MessageObject r37, MessageObject r38, boolean r39, int r40, int r41, TL_stories.StoryItem r42, ChatActivity.ReplyQuote r43, SendMessageChatArguments r44, long r45, boolean r47, TLRPC.PhotoSize r48, long r49, long r51, MessageSuggestionParams r53, PollSendParams r54) {
        if (r24 == null) goto L6;
        if (r25 == null) goto L6;
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r24), r25, false);
    L6:
        if (r26 != null) goto L11;
        if (r27 != null) goto L11;
        SendMessageParams r0 = SendMessageParams.of(r30, r29, r31, r35, r37, r38, r33.caption, r33.entities, null, r32, r39, r40, r41, r33.ttl, r34, null, false, r33.hasMediaSpoilers);
        r0.replyToStoryItem = r42;
        r0.replyQuote = r43;
        r0.sendMessageChatArguments = r44;
        r0.effect_id = r45;
        r0.invert_media = r47;
        r0.stars = r33.stars;
        r0.pollIndex = r33.pollIndex;
        r0.cover = r48;
        r0.payStars = r49;
        r0.monoForumPeer = r51;
        r0.suggestionParams = r53;
        r0.isLivePhoto = r33.isLivePhoto;
        r0.livePhotoTimestamp = r33.livePhotoTimestampUs / 1000;
        r0.pollSendParams = r54;
        r28.getSendMessagesHelper().sendMessage(r0);
        return;
    L11:
        r28.getSendMessagesHelper().editMessage(r26, r27, null, r29, r30, r31, null, r32, false, r33.hasMediaSpoilers, r34);
    }

    /* renamed from: $r8$lambda$ed99C9PGwS6Vtkdh-reA3DQ2jXk, reason: not valid java name */
    public static /* synthetic */ void m4930$r8$lambda$ed99C9PGwS6VtkdhreA3DQ2jXk(SendMessagesHelper r0, TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3, boolean r4, MessageObject r5, TL_keyboard.KeyboardButtonProto r6, ChatActivity r7) {
        r0.lambda$sendCallback$43(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$eoSk-ERK-xsyBrxdmJ0F-2tZNT0, reason: not valid java name */
    public static /* synthetic */ void m4931$r8$lambda$eoSkERKxsyBrxdmJ0F2tZNT0(SendMessagesHelper r0, TLRPC.TL_updateShortSentMessage r1) {
        r0.lambda$performSendMessageRequest$90(r1);
    }

    /* renamed from: $r8$lambda$fBfgmUB1IRblzpNi_EqI0-98nUA, reason: not valid java name */
    public static /* synthetic */ void m4932$r8$lambda$fBfgmUB1IRblzpNi_EqI098nUA(SendMessagesHelper r0, TLRPC.TL_messages_editMessage r1, MessageObject r2, BaseFragment r3, boolean r4) {
        r0.lambda$sendEditRichMessageRequest$23(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$fDDXNOCkU3TBl68PMC4rIkQ3jfM(SendMessagesHelper r0, TLRPC.TL_error r1, BaseFragment r2, TLRPC.TL_messages_editMessage r3) {
        r0.lambda$sendEditRichMessageRequest$25(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$fZVClCS_DWvTInxmOEJZO4ZCd6o(ArrayList r32, int[] r33, AccountInstance r34, long r35, MessageObject r37, MessageObject r38, TL_stories.StoryItem r39, ChatActivity.ReplyQuote r40, boolean r41, int r42, SendMessageChatArguments r43, long r44, long r46, MessageSuggestionParams r48, PollSendParams r49, Runnable r50) {
        if (r32.isEmpty() == true) goto L10;
        int r1 = r33[0] - 1;
        r33[0] = r1;
        if (r1 <= 0) goto L7;
        boolean r31 = true;
    L8:
        prepareSendingMedia(r34, r32, r35, r37, r38, r39, r40, false, true, null, null, r41, r42, 0, 0, false, null, r43, 0, false, r44, r46, r48, r49, r31);
        mediaSendQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda69(r50));
        return;
    L7:
        r31 = false;
        goto L8
    L10:
        r50.run();
    }

    public static /* synthetic */ void $r8$lambda$gND_1JPntnGzFQFllhYDdCxQbQo(VideoEditedInfo r37, String r38, long r39, int r41, AccountInstance r42, String r43, TLRPC.Photo r44, CharSequence r45, MessageObject r46, boolean r47, MessageObject r48, MessageObject r49, ArrayList r50, boolean r51, int r52, int r53, TL_stories.StoryItem r54, ChatActivity.ReplyQuote r55, SendMessageChatArguments r56, long r57, long r59, long r61, MessageSuggestionParams r63, boolean r64, boolean r65) {
        if (r37 == null) goto L6;
        VideoEditedInfo r8 = r37;
    L7:
        boolean r1 = DialogObject.isEncryptedDialog(r39);
        if (r8 != null) goto L10;
    L12:
        boolean r11 = false;
    L13:
        if (r8 == null) goto L15;
    L20:
        File r12 = new File(r38);
        String r0 = r38 + r12.length() + "_" + r12.lastModified();
        if (r8 == null) goto L37;
        if (r11 == true) goto L30;
        StringBuilder r2 = new StringBuilder();
        r2.append(r0);
        long r15 = 0;
        r2.append(r8.estimatedDuration);
        r2.append("_");
        r2.append(r8.startTime);
        r2.append("_");
        r2.append(r8.endTime);
        if (r8.muted == false) goto L26;
        String r02 = "_m";
    L27:
        r2.append(r02);
        r0 = r2.toString();
        if (r8.resultWidth == r8.originalWidth) goto L31;
        r0 = r0 + "_" + r8.resultWidth;
    L31:
        long r4 = r8.startTime;
        if (r4 >= r15) goto L35;
        r4 = r15;
    L35:
        long r5 = r4;
    L36:
        String r152 = r0;
        if (r1 == true) goto L58;
        if (r41 != 0) goto L58;
        if (r8 != null) goto L42;
    L49:
        MessagesStorage r22 = r42.getMessagesStorage();
        if (r1 == true) goto L52;
        int r410 = 2;
    L53:
        Object[] r23 = r22.getSentFile(r152, r410);
        if (r23 == null) goto L58;
        Object r411 = r23[0];
        if ((r411 instanceof TLRPC.TL_document) == false) goto L58;
        TLRPC.TL_document r412 = (TLRPC.TL_document) r411;
        String r16 = (String) r23[1];
        ensureMediaThumbExists(r42, r1, r412, r38, null, r5);
        TLRPC.TL_document r03 = r412;
        String r24 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r14 = r16;
    L60:
        if (r03 != null) goto L130;
        if (r8 != null) goto L63;
    L65:
        Bitmap r04 = null;
    L66:
        if (r04 != null) goto L68;
        r04 = createVideoThumbnailAtTime(r38, r5);
    L68:
        if (r04 != null) goto L70;
        r04 = createVideoThumbnail(r38, 1);
    L70:
        if (r1 == true) goto L74;
        if (r41 != 0) goto L74;
        int r58 = 320;
    L75:
        float r6 = r58;
        if (r58 <= 90) goto L78;
        int r510 = 80;
    L79:
        TLRPC.PhotoSize r511 = ImageLoader.scaleAndSaveImage(r04, r6, r6, r510, r1);
        if (r04 == null) goto L88;
        if (r511 == null) goto L88;
        if (r11 == false) goto L86;
        if (r1 == false) goto L85;
        r04 = Bitmap.createScaledBitmap(r04, 90, 90, true);
        Utilities.blurBitmap(r04, 7);
        Utilities.blurBitmap(r04, 7);
        Utilities.blurBitmap(r04, 7);
        StringBuilder r413 = new StringBuilder();
        boolean r21 = r11;
        r413.append(r511.location.volume_id);
        r413.append("_");
        r413.append(r511.location.local_id);
        r413.append("@%d_%d_b2");
        String r414 = String.format(r413.toString(), new Object[]{Integer.valueOf((int) (AndroidUtilities.roundMessageSize / AndroidUtilities.density)), Integer.valueOf((int) (AndroidUtilities.roundMessageSize / AndroidUtilities.density))});
    L89:
        TLRPC.TL_document r62 = new TLRPC.TL_document();
        if (r511 == null) goto L92;
        r62.thumbs.add(r511);
        r62.flags |= 1;
    L92:
        r62.file_reference = new byte[0];
        r62.mime_type = "video/mp4";
        r42.getUserConfig().saveConfig(false);
        if (r1 == true) goto L95;
        TLRPC.TL_documentAttributeVideo r13 = new TLRPC.TL_documentAttributeVideo();
        r13.supports_streaming = true;
    L100:
        r13.round_message = r21;
        r62.attributes.add(r13);
        if (r8 != null) goto L103;
    L105:
        if (r8 == null) goto L125;
        if (r8.needConvert() == false) goto L125;
        if (r8.muted == false) goto L111;
        r62.attributes.add(new TLRPC.TL_documentAttributeAnimated());
        fillVideoAttribute(r38, r13, r8);
        r8.originalWidth = r13.w;
        r8.originalHeight = r13.h;
    L112:
        int r512 = r8.rotationValue;
        MediaController.CropState r9 = r8.cropState;
        if (r9 == null) goto L116;
        int r10 = r9.transformWidth;
        int r112 = r9.transformHeight;
        r512 = r512 + r9.transformRotation;
    L117:
        if (r512 != 90) goto L119;
    L122:
        r13.w = r112;
        r13.h = r10;
    L123:
        r62.size = r8.estimatedSize;
    L128:
        String r513 = r414;
        TLRPC.TL_document r92 = r62;
        boolean r17 = false;
        Bitmap r415 = r04;
    L164:
        TLRPC.PhotoSize r05 = ImageLoader.fileToSize(r43, r17);
        if (r05 != null) goto L168;
        if (r44 == null) goto L168;
        r05 = new ImageLoader.PhotoSizeFromPhoto(r44);
    L168:
        TLRPC.PhotoSize r113 = r05;
        if (r8 != null) goto L171;
    L173:
        String r102 = r38;
    L174:
        HashMap r122 = new HashMap();
        if (r45 == null) goto L177;
        String r19 = r45.toString();
    L178:
        r122.put("originalPath", r152);
        if (r14 == null) goto L181;
        r122.put("parentObject", r14);
    L181:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda110(r415, r513, r46, r42, r8, r92, r102, r113, r122, r47, r14, r39, r48, r49, r19, r50, r51, r52, r53, r41, r54, r55, r56, r57, r59, r61, r63, r64));
        return;
    L177:
        r19 = r24;
        goto L178
    L171:
        if (r8.needConvert() == false) goto L173;
        File r18 = new File(FileLoader.getDirectory(4), "-2147483648_" + SharedConfig.getLastLocalId() + ".mp4");
        SharedConfig.saveConfig();
        r102 = r18.getAbsolutePath();
        goto L174
    L119:
        if (r512 == 270) goto L122;
        r13.w = r10;
        r13.h = r112;
        goto L123
    L116:
        r10 = r8.resultWidth;
        r112 = r8.resultHeight;
        goto L117
    L111:
        r13.duration = r8.estimatedDuration / 1000.0d;
    L125:
        if (r12.exists() == false) goto L127;
        r62.size = (int) r12.length();
    L127:
        fillVideoAttribute(r38, r13, null);
        goto L128
    L103:
        if (r8.notReadyYet == false) goto L105;
        r13.w = r8.resultWidth;
        r13.h = r8.resultHeight;
        r13.duration = r8.estimatedDuration / 1000.0d;
        r62.size = r8.estimatedSize;
        goto L128
    L95:
        if (r42.getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r39))) != null) goto L97;
        return;
    L97:
        r13 = new TLRPC.TL_documentAttributeVideo_layer159();
        goto L100
    L85:
        r21 = r11;
        Utilities.blurBitmap(r04, 3);
        r414 = String.format(r511.location.volume_id + "_" + r511.location.local_id + "@%d_%d_b", new Object[]{Integer.valueOf((int) (AndroidUtilities.roundMessageSize / AndroidUtilities.density)), Integer.valueOf((int) (AndroidUtilities.roundMessageSize / AndroidUtilities.density))});
        goto L89
    L86:
        r21 = r11;
        r04 = null;
    L87:
        r414 = null;
    L88:
        r21 = r11;
        goto L87
    L78:
        r510 = 55;
    L74:
        r58 = 90;
        goto L75
    L63:
        if (r8.notReadyYet == false) goto L65;
        r04 = r8.thumb;
        goto L66
    L130:
        boolean r103 = r11;
        if (r03.thumbs.isEmpty() == false) goto L163;
        if (r8 != null) goto L134;
    L136:
        Bitmap r123 = null;
    L137:
        if (r123 != null) goto L139;
        r123 = createVideoThumbnailAtTime(r38, r5);
    L139:
        if (r123 != null) goto L141;
        Bitmap r66 = createVideoThumbnail(r38, 1);
    L142:
        if (r1 == true) goto L146;
        if (r41 != 0) goto L146;
        int r514 = 320;
    L147:
        float r124 = r514;
        if (r514 <= 90) goto L150;
        int r515 = 80;
    L151:
        TLRPC.PhotoSize r516 = ImageLoader.scaleAndSaveImage(r66, r124, r124, r515, r1);
        if (r66 == null) goto L159;
        if (r516 == null) goto L159;
        if (r103 == false) goto L158;
        if (r1 == false) goto L157;
        Bitmap r416 = Bitmap.createScaledBitmap(r66, 90, 90, true);
        Utilities.blurBitmap(r416, 7);
        Utilities.blurBitmap(r416, 7);
        Utilities.blurBitmap(r416, 7);
        String r36 = String.format(r516.location.volume_id + "_" + r516.location.local_id + "@%d_%d_b2", new Object[]{Integer.valueOf((int) (AndroidUtilities.roundMessageSize / AndroidUtilities.density)), Integer.valueOf((int) (AndroidUtilities.roundMessageSize / AndroidUtilities.density))});
        r66 = r416;
    L160:
        if (r516 == null) goto L162;
        r03.thumbs.add(r516);
        r03.flags |= 1;
    L162:
        r92 = r03;
        r415 = r66;
        r513 = r36;
        r17 = false;
        goto L164
    L157:
        Utilities.blurBitmap(r66, 3);
        r36 = String.format(r516.location.volume_id + "_" + r516.location.local_id + "@%d_%d_b", new Object[]{Integer.valueOf((int) (AndroidUtilities.roundMessageSize / AndroidUtilities.density)), Integer.valueOf((int) (AndroidUtilities.roundMessageSize / AndroidUtilities.density))});
        goto L160
    L158:
        r66 = null;
    L159:
        r36 = null;
        goto L160
    L150:
        r515 = 55;
    L146:
        r514 = 90;
        goto L147
    L141:
        r66 = r123;
        goto L142
    L134:
        if (r8.notReadyYet == false) goto L136;
        r123 = r8.thumb;
        goto L137
    L163:
        r92 = r03;
        r17 = false;
        r415 = null;
        r513 = null;
        goto L164
    L52:
        r410 = 5;
        goto L53
    L42:
        if (r8.filterState != null) goto L58;
        if (r8.paintPath != null) goto L58;
        if (r8.mediaEntities != null) goto L58;
        if (r8.cropState == null) goto L49;
    L58:
        r24 = _UrlKt.FRAGMENT_ENCODE_SET;
        r03 = null;
        r14 = null;
        goto L60
    L26:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L27
    L30:
        r15 = 0;
        goto L31
    L37:
        r5 = 0;
        goto L36
    L15:
        if (r38.endsWith("mp4") == true) goto L20;
        if (r11 == true) goto L20;
        prepareSendingDocumentInternal(r42, r38, r38, null, null, r39, r48, r49, r54, r55, r50, r46, null, false, r45, r51, r52, r53, null, r65, r56, 0, r64, r59, r61, r63, null, -1);
        return;
    L10:
        if (r8.roundVideo == false) goto L12;
        r11 = true;
        goto L13
    L6:
        r8 = createCompressionSettings(r38, 0);
        goto L7
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void $r8$lambda$gfrDu1OoqUf7rtBuo7dUWBUy18c(long r48, ArrayList r50, String r51, PollSendParams r52, AccountInstance r53, int r54, ArrayList r55, String r56, MessageObject r57, MessageObject r58, TL_stories.StoryItem r59, ChatActivity.ReplyQuote r60, ArrayList r61, MessageObject r62, boolean r63, boolean r64, int r65, InputContentInfoCompat r66, SendMessageChatArguments r67, long r68, boolean r70, long r71, long r73, MessageSuggestionParams r75, ArrayList r76, ArrayList r77, ArrayList r78) {
        ArrayList r0 = r50;
        int r2 = r54;
        ArrayList r3 = r76;
        int r4 = 1;
        long[] r14 = new long[1];
        Integer[] r5 = new Integer[1];
        boolean r33 = DialogObject.isEncryptedDialog(r48);
        int r6 = 10;
        if (r0 == null) goto L49;
        int r7 = r0.size();
        boolean r11 = true;
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
    L5:
        if (r8 >= r7) goto L47;
        if (r8 != 0) goto L8;
        String r16 = r51;
    L9:
        if (r33 == true) goto L18;
        if (r7 <= r4) goto L18;
        if ((r10 % 10) != 0) goto L18;
        if (r52 != null) goto L18;
        long r92 = r14[0];
        if (r92 == 0) goto L17;
        finishGroup(r53, r92, r2);
    L17:
        r14[0] = Utilities.random.nextLong();
        r10 = 0;
    L18:
        int r93 = r10 + 1;
        long r41 = r14[0];
        String r102 = (String) r0.get(r8);
        String r13 = (String) r55.get(r8);
        if (r8 != 0) goto L21;
        ArrayList r12 = r61;
    L22:
        if (r63 == true) goto L28;
        if (r52 != null) goto L26;
        if (r93 != r6) goto L26;
    L27:
        boolean r15 = r4;
    L29:
        if (r66 != null) goto L31;
        boolean r21 = r4;
    L32:
        if (r11 == false) goto L34;
        long r23 = r68;
    L35:
        if (r3 == null) goto L38;
        int r32 = ((Integer) r3.get(r8)).intValue();
    L39:
        int r18 = r2;
        Integer[] r20 = r5;
        int r44 = r7;
        int r45 = r8;
        int r22 = prepareSendingDocumentInternal(r53, r102, r13, null, r56, r48, r57, r58, r59, r60, r12, r62, r14, r15, r16, r64, r18, r65, r20, r21, r67, r23, r70, r71, r73, r75, r52, r32);
        long r42 = r14[0];
        if (r41 == r42) goto L42;
    L45:
        r10 = 1;
    L46:
        r8 = r45 + 1;
        r0 = r50;
        r9 = r22;
        r2 = r18;
        r5 = r20;
        r11 = false;
        r7 = r44;
        r4 = 1;
        r6 = 10;
        r3 = r76;
        goto L5
    L42:
        if (r42 == (-1)) goto L45;
        r10 = r93;
        goto L46
    L38:
        r32 = -1;
        goto L39
    L34:
        r23 = 0;
        goto L35
    L31:
        r21 = 0;
    L26:
        if (r8 == (r7 - 1)) goto L27;
    L28:
        r15 = 0;
        goto L29
    L21:
        r12 = null;
        goto L22
    L8:
        r16 = null;
        goto L9
    L47:
        boolean r43 = r11;
    L48:
        int r34 = r2;
        Integer[] r202 = r5;
        if (r77 == null) goto L103;
        r14[0] = 0;
        int r24 = r77.size();
        int r510 = 0;
        int r69 = 0;
    L53:
        if (r510 >= r77.size()) goto L103;
        if (r510 != 0) goto L59;
        if (r50 != null) goto L57;
    L58:
        String r162 = r51;
    L60:
        if (r510 != 0) goto L65;
        if (r50 != null) goto L63;
    L64:
        ArrayList r122 = r61;
    L66:
        if (r33 == false) goto L68;
    L76:
        int r610 = r69 + 1;
        long r412 = r14[0];
        Uri r72 = (Uri) r77.get(r510);
        if (r63 == true) goto L83;
        if (r52 != null) goto L81;
        if (r610 != 10) goto L81;
    L82:
        boolean r152 = true;
    L84:
        if (r66 != null) goto L86;
        boolean r212 = true;
    L87:
        if (r43 == false) goto L90;
        long r232 = r68;
    L91:
        if (r78 == null) goto L94;
        int r322 = ((Integer) r78.get(r510)).intValue();
    L93:
        int r94 = r24;
        int r452 = r510;
        int r25 = prepareSendingDocumentInternal(r53, null, null, r72, r56, r48, r57, r58, r59, r60, r122, r62, r14, r152, r162, r64, r54, r65, r202, r212, r67, r232, r70, r71, r73, r75, r52, r322);
        long r35 = r14[0];
        if (r412 == r35) goto L98;
    L101:
        r69 = 1;
    L102:
        r510 = r452 + 1;
        r34 = r54;
        r9 = r25;
        r43 = false;
        r24 = r94;
        goto L53
    L98:
        if (r35 == (-1)) goto L101;
        r69 = r610;
        goto L102
    L94:
        r322 = -1;
        goto L93
    L90:
        r232 = 0;
        goto L91
    L86:
        r212 = false;
    L81:
        if (r510 == (r24 - 1)) goto L82;
    L83:
        r152 = false;
        goto L84
    L68:
        if (r24 <= 1) goto L76;
        if ((r69 % 10) != 0) goto L76;
        if (r52 != null) goto L76;
        long r611 = r14[0];
        if (r611 == 0) goto L75;
        finishGroup(r53, r611, r34);
    L75:
        r14[0] = Utilities.random.nextLong();
        r69 = 0;
        goto L76
    L63:
        if (r50.size() == 0) goto L64;
    L65:
        r122 = null;
        goto L66
    L57:
        if (r50.size() == 0) goto L58;
    L59:
        r162 = null;
    L103:
        if (r66 == null) goto L105;
        r66.releasePermission();
    L105:
        handleError(r9, r53);
        return;
    L49:
        r9 = 0;
        r43 = true;
        goto L48
    }

    public static /* synthetic */ void $r8$lambda$gw7US38G8UV1S0yCRNvKDxd5Ng8(SendMessagesHelper r0, TLRPC.TL_error r1, BaseFragment r2, TLRPC.TL_messages_editMessage r3) {
        r0.lambda$editMessage$20(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$hfMvCRZMJ9t0YPB6TQA6h4IqfqY(SendMessagesHelper r0, TLObject r1, ArrayList r2, ArrayList r3, ArrayList r4, DelayedMessage r5, boolean r6) {
        r0.lambda$performSendMessageRequestMulti$62(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$hq_P9QudbRt-Y9mkkjGFy502nHY, reason: not valid java name */
    public static /* synthetic */ void m4933$r8$lambda$hq_P9QudbRtY9mkkjGFy502nHY(SendMessagesHelper r0, MessageObject r1, String r2, DelayedMessage r3, boolean r4, DelayedMessage r5, Object r6, HashMap r7, boolean r8, TLObject r9) {
        r0.lambda$performSendMessageRequest$79(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$iqfiX52t_A0OZlK8QUtY16dpSJU(ArrayList r0, long r1, boolean r3, boolean r4, boolean r5, AccountInstance r6, long r7, MessageObject r9, TLRPC.TL_inputPollAnswer r10, MessageObject r11, MessageObject r12, boolean r13, int r14, int r15, TL_stories.StoryItem r16, ChatActivity.ReplyQuote r17, SendMessageChatArguments r18, long r19, boolean r21, long r22, long r24, MessageSuggestionParams r26, PollSendParams r27, boolean r28, InputContentInfoCompat r29, boolean r30) {
        lambda$prepareSendingMedia$132(r0, r1, r3, r4, r5, r6, r7, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r21, r22, r24, r26, r27, r28, r29, r30);
    }

    /* renamed from: $r8$lambda$j6x-IectMBx4kuaPB1GQLnp6vyM, reason: not valid java name */
    public static /* synthetic */ void m4934$r8$lambda$j6xIectMBx4kuaPB1GQLnp6vyM(SendMessagesHelper r0, TLRPC.Message r1, boolean r2, TLObject r3, DelayedMessage r4) {
        r0.lambda$performSendMessageRequest$80(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$j7_k75OGQBNtdyt69Qpi_PQDRng(SendMessagesHelper r0, TLObject r1, TLRPC.InputMedia r2, DelayedMessage r3) {
        r0.lambda$performSendDelayedMessage$51(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$kL6bsNDnAfLuzpwWb8Tyhkn6PJY(SendMessagesHelper r0, boolean r1, MessageObject r2, TLRPC.TL_messages_editMessage r3, BaseFragment r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$sendEditRichMessageRequest$26(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$ldWNiCLtl9Ckk2pe1doFDEt7zGk(SendMessagesHelper r0, boolean r1, MessageObject r2, TL_keyboard.KeyboardButtonProto r3, ChatActivity r4, AlertDialog r5, int r6) {
        r0.lambda$sendCallback$41(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$lxVV_JJFqj_Es_sxjFnhBW-Er0U, reason: not valid java name */
    public static /* synthetic */ void m4935$r8$lambda$lxVV_JJFqj_Es_sxjFnhBWEr0U(SendMessagesHelper r0, ArrayList r1, long r2, boolean r4, boolean r5, boolean r6, int r7, int r8, MessageObject r9, int r10, long r11, MessageSuggestionParams r13, Long r14) {
        r0.lambda$sendMessage$7(r1, r2, r4, r5, r6, r7, r8, r9, r10, r11, r13, r14);
    }

    /* renamed from: $r8$lambda$o2JT4-jJiNXJT8pA7xA5R_ugP18, reason: not valid java name */
    public static /* synthetic */ void m4936$r8$lambda$o2JT4jJiNXJT8pA7xA5R_ugP18(SendMessagesHelper r0, int[] r1, int[] r2, boolean r3, boolean r4, MessageObject r5, SparseArray r6, ArrayList r7, TLRPC.Message r8, int r9, long r10, int r12) {
        r0.lambda$performSendMessageRequestMulti$70(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12);
    }

    public static /* synthetic */ void $r8$lambda$oN29P4yRtSspLEXmZGLyuDq9XHg(SendMessagesHelper r0, TLRPC.TL_messages_addPollAnswer r1, TLObject r2, MessageObject r3, String r4, DelayedMessage r5, boolean r6, DelayedMessage r7, Object r8, HashMap r9, boolean r10, TLObject r11, TLRPC.TL_error r12) {
        r0.lambda$performSendMessageRequest$76(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    /* renamed from: $r8$lambda$oVQfSns-HYyPC1jbIDg3Md8xveo, reason: not valid java name */
    public static /* synthetic */ void m4937$r8$lambda$oVQfSnsHYyPC1jbIDg3Md8xveo(SendMessagesHelper r0, boolean r1, TLRPC.Message r2, ArrayList r3, boolean r4, ArrayList r5, int r6) {
        r0.lambda$performSendMessageRequest$95(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$oX_cl05KblL8RXK72V4G5wvxLjU(SendMessagesHelper r0, int r1, TLRPC.Message r2, TLRPC.Message r3, TLRPC.Peer r4, int r5, ArrayList r6, long r7, int r9) {
        r0.lambda$sendMessage$13(r1, r2, r3, r4, r5, r6, r7, r9);
    }

    public static /* synthetic */ void $r8$lambda$ompGsQ8RMpvnNmUQE_inY7RopEw(SendMessagesHelper r0, TL_update.TL_updateNewMessage r1) {
        r0.lambda$performSendMessageRequestMulti$66(r1);
    }

    /* renamed from: $r8$lambda$oo1-C_KC83LNBHM10yZw1y34Cm0, reason: not valid java name */
    public static /* synthetic */ void m4938$r8$lambda$oo1C_KC83LNBHM10yZw1y34Cm0(SendMessagesHelper r0, TLObject r1, TLRPC.TL_messages_requestUrlAuth r2, ChatActivity r3, String r4, boolean r5) {
        r0.lambda$requestUrlAuth$36(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$p_seJI-TPHQxGHMCMqInGu5OWCQ, reason: not valid java name */
    public static /* synthetic */ void m4939$r8$lambda$p_seJITPHQxGHMCMqInGu5OWCQ(SendMessagesHelper r0, TLObject r1, TLRPC.TL_messages_addPollAnswer r2, TLObject r3, MessageObject r4, String r5, DelayedMessage r6, boolean r7, DelayedMessage r8, Object r9, HashMap r10, boolean r11) {
        r0.lambda$performSendMessageRequest$75(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    /* renamed from: $r8$lambda$pr8Op0zLd7orctSM_UeZIVA-uiQ, reason: not valid java name */
    public static /* synthetic */ void m4940$r8$lambda$pr8Op0zLd7orctSM_UeZIVAuiQ(SendMessagesHelper r0, TLObject r1, MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9, TLRPC.EmojiGameInfo r10, TLRPC.TL_error r11) {
        r0.lambda$performSendMessageRequest$81(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$qvHlAuCCZunSUbrThg1oZ5DwTLQ(SendMessagesHelper r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$sendGame$47(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$r6Io4elfBlW9dihbm1gSnA-8jMI, reason: not valid java name */
    public static /* synthetic */ void m4941$r8$lambda$r6Io4elfBlW9dihbm1gSnA8jMI(SendMessagesHelper r0, Bitmap[] r1, String[] r2, TLRPC.Document r3, VideoEditedInfo r4, long r5, MessageObject r7, MessageObject r8, boolean r9, int r10, int r11, Object r12, MessageObject.SendAnimationData r13, TL_stories.StoryItem r14, ChatActivity.ReplyQuote r15, SendMessageChatArguments r16, long r17, long r19, MessageSuggestionParams r21, CharSequence r22, boolean r23) {
        r0.lambda$sendSticker$5(r1, r2, r3, r4, r5, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r19, r21, r22, r23);
    }

    public static /* synthetic */ void $r8$lambda$ra7bvuYJKJGh7XqfvMSenpLp1WI(SendMessagesHelper r0, ArrayList r1, long r2, Uri r4, MessagesStorage.LongCallback r5) {
        r0.lambda$prepareImportHistory$110(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$s_okgcCi0MZEfNjX5mxWizFSbU8(SendMessagesHelper r0, TLRPC.Updates r1) {
        r0.lambda$performSendMessageRequestMulti$72(r1);
    }

    public static /* synthetic */ void $r8$lambda$sal4jpdiZ4r2Ces2BMbxSGFvspk(SendMessagesHelper r0, BaseFragment r1, TLRPC.TL_messages_editMessage r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$editMessage$21(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$u3GoCPWtFFRjrUHxJQnHaRHPVXU(BaseFragment r2) {
        AlertsCreator.showSendMediaAlert(8, r2, null);
    }

    public static /* synthetic */ void $r8$lambda$urHonJCDFAjnfy1Ai6rnNktqdkM(SendMessagesHelper r0, Uri r1, ArrayList r2, MessagesStorage.LongCallback r3, long r4) {
        r0.lambda$prepareImportHistory$105(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$v1uc-yyOvd0dtMxYJ8v7-HDdXrY, reason: not valid java name */
    public static /* synthetic */ void m4942$r8$lambda$v1ucyyOvd0dtMxYJ8v7HDdXrY(SendMessagesHelper r0, TLRPC.Document r1, VideoEditedInfo r2, long r3, MessageObject r5, MessageObject r6, boolean r7, int r8, int r9, Object r10, MessageObject.SendAnimationData r11, TL_stories.StoryItem r12, ChatActivity.ReplyQuote r13, SendMessageChatArguments r14, long r15, long r17, MessageSuggestionParams r19, CharSequence r20, boolean r21) {
        r0.lambda$sendSticker$6(r1, r2, r3, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r17, r19, r20, r21);
    }

    /* renamed from: $r8$lambda$v9qneWod6eXcbW7STyl4PF-TyW8, reason: not valid java name */
    public static /* synthetic */ void m4943$r8$lambda$v9qneWod6eXcbW7STyl4PFTyW8(MediaSendPrepareWorker r4, AccountInstance r5, SendingMediaInfo r6, boolean r7) {
        r4.photo = r5.getSendMessagesHelper().generatePhotoSizes(null, r6.path, r6.uri, r6.highQuality);
        if (r7 == true) goto L5;
    L7:
        r4.sync.countDown();
        return;
    L5:
        if (r6.canDeleteAfter == false) goto L7;
        new File(r6.path).delete();
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$vHE4e2ivIoMuOKnVMv9eYUvr24M(SendMessagesHelper r0, boolean r1, TLRPC.TL_error r2, TLRPC.Message r3, TLObject r4, MessageObject r5, HashMap r6, String r7, TLObject r8) {
        r0.lambda$performSendMessageRequest$100(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$vKqRYNMW1-cpZJcNU9wmahwhUYA, reason: not valid java name */
    public static /* synthetic */ void m4944$r8$lambda$vKqRYNMW1cpZJcNU9wmahwhUYA(SendMessagesHelper r0, long r1, ArrayList r3) {
        r0.lambda$performSendMessageRequest$97(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$wuTF71jWAQOF7XmDvt00Eimkiac(AccountInstance r18, long r19, int r21) {
        SendMessagesHelper r0 = r18.getSendMessagesHelper();
        ArrayList<DelayedMessage> r1 = r0.delayedMessages.get("group_" + r19);
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.isEmpty() == true) goto L18;
        DelayedMessage r12 = r1.get(0);
        ArrayList<MessageObject> r3 = r12.messageObjects;
        MessageObject r32 = r3.get(r3.size() - 1);
        r12.finalGroupMessage = r32.getId();
        r32.messageOwner.params.put("final", "1");
        TLRPC.TL_messages_messages r9 = new TLRPC.TL_messages_messages();
        r9.messages.add(r32.messageOwner);
        if (r12.paidMedia == false) goto L9;
    L15:
        r0.sendReadyToSendGroup(r12, true, true);
        return;
    L9:
        if (r12.pollMedia == true) goto L15;
        MessagesStorage r8 = r18.getMessagesStorage();
        long r10 = r12.peer;
        if (r21 == 0) goto L13;
        int r15 = 1;
    L14:
        r8.putMessages(r9, r10, -2, 0, false, r15, 0);
        goto L15
    L13:
        r15 = 0;
        goto L14
    }

    public static /* synthetic */ void $r8$lambda$x5jssyLCb_qqgISyBTfLlBFOtV0(SendMessagesHelper r0, TLRPC.TL_messages_forwardMessages r1, long r2, int r4, boolean r5, boolean r6, LongSparseArray r7, ArrayList r8, ArrayList r9, MessageObject r10, TLRPC.Peer r11) {
        r0.lambda$sendMessage$18(r1, r2, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$xJH05XNDiiTjIOe_RRjSO8gzaRM(SendMessagesHelper r0, String r1, List r2) {
        r0.lambda$sendCallback$38(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$yBKJn6U533mDrzowRIEZ6KfxQRw(SendMessagesHelper r0, TLRPC.InputMedia r1, DelayedMessage r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$uploadMultiMedia$60(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$yf6MqNpkoFs63ilEv_2LBYjr6jI(CharSequence r13, AccountInstance r14, long r15, long r17, boolean r19, int r20, int r21, long r22) {
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda68(r13, r14, r15, r17, r19, r20, r21, r22));
    }

    /* renamed from: $r8$lambda$yh2-ItvMZWbsbV7fhHfWl2WEuPI, reason: not valid java name */
    public static /* synthetic */ void m4945$r8$lambda$yh2ItvMZWbsbV7fhHfWl2WEuPI(SendMessagesHelper r0, TLRPC.TL_photo r1, MessageObject r2, File r3, DelayedMessage r4, String r5) {
        r0.lambda$didReceivedNotification$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ykX0u_PTZIxVumVKjfTjcZOgQh4(SendMessagesHelper r0, ImportingStickers r1, HashMap r2, String r3, MessagesStorage.StringCallback r4) {
        r0.lambda$prepareImportStickers$112(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetdelayedMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m4946$$Nest$fgetdelayedMessages(SendMessagesHelper r0) {
        return r0.delayedMessages;
    }

    /* renamed from: -$$Nest$fgetimportingHistoryMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m4947$$Nest$fgetimportingHistoryMap(SendMessagesHelper r0) {
        return r0.importingHistoryMap;
    }

    /* renamed from: -$$Nest$fgetimportingStickersMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m4948$$Nest$fgetimportingStickersMap(SendMessagesHelper r0) {
        return r0.importingStickersMap;
    }

    /* renamed from: -$$Nest$fgetwaitingForLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m4949$$Nest$fgetwaitingForLocation(SendMessagesHelper r0) {
        return r0.waitingForLocation;
    }

    /* renamed from: -$$Nest$mperformSendMessageRequest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4950$$Nest$mperformSendMessageRequest(SendMessagesHelper r0, TLObject r1, MessageObject r2, String r3, DelayedMessage r4, Object r5, HashMap r6, boolean r7) {
        r0.performSendMessageRequest(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: -$$Nest$msendLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4951$$Nest$msendLocation(SendMessagesHelper r0, Location r1) {
        r0.sendLocation(r1);
    }

    static {
        mediaSendQueue = new DispatchQueue("mediaSendQueue");
        int r2 = Runtime.getRuntime().availableProcessors();
        LinkedBlockingQueue r7 = new LinkedBlockingQueue();
        mediaSendThreadPool = new ThreadPoolExecutor(r2, r2, 60, TimeUnit.SECONDS, r7);
        Instance = new SendMessagesHelper[16];
    }

    public SendMessagesHelper(int r2) {
        super(r2);
        this.delayedMessages = new HashMap();
        this.unsentMessages = new SparseArray();
        this.sendingMessages = new SparseArray();
        this.editingMessages = new SparseArray();
        this.uploadMessages = new SparseArray();
        this.sendingMessagesIdDialogs = new LongSparseArray();
        this.uploadingMessagesIdDialogs = new LongSparseArray();
        this.waitingForLocation = new HashMap();
        this.waitingForCallback = new HashMap();
        this.waitingForCallbackMap = new HashMap();
        this.waitingForVote = new HashMap();
        this.voteSendTime = new LongSparseArray();
        this.importingHistoryFiles = new HashMap();
        this.importingHistoryMap = new LongSparseArray();
        this.importingStickersFiles = new HashMap();
        this.importingStickersMap = new HashMap();
        this.locationProvider = new LocationProvider(new AnonymousClass1(this));
        this.waitingForTodoUpdate = new HashMap();
        this.hooks = PluginsController.getInstance();
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda29(this));
    }

    private void applyMonoForumPeerId(TLRPC.TL_messages_forwardMessages r3, long r4) {
        if (r4 == 0) goto L12;
        TLRPC.InputPeer r2 = getMessagesController().getInputPeer(r4);
        TLRPC.InputReplyTo r42 = r3.reply_to;
        if (r42 != null) goto L7;
        TLRPC.TL_inputReplyToMonoForum r43 = new TLRPC.TL_inputReplyToMonoForum();
        r3.reply_to = r43;
        r43.monoforum_peer_id = r2;
        return;
    L7:
        if ((r42 instanceof TLRPC.TL_inputReplyToMessage) == false) goto L13;
        r42.monoforum_peer_id = r2;
        r42.flags |= 32;
        return;
    L13:
        return;
    }

    private void applyMonoForumPeerId(TLRPC.TL_messages_sendInlineBotResult r3, long r4) {
        if (r4 == 0) goto L12;
        TLRPC.InputPeer r2 = getMessagesController().getInputPeer(r4);
        TLRPC.InputReplyTo r42 = r3.reply_to;
        if (r42 != null) goto L7;
        TLRPC.TL_inputReplyToMonoForum r43 = new TLRPC.TL_inputReplyToMonoForum();
        r3.reply_to = r43;
        r43.monoforum_peer_id = r2;
        r3.flags |= 1;
        return;
    L7:
        if ((r42 instanceof TLRPC.TL_inputReplyToMessage) == false) goto L13;
        r42.monoforum_peer_id = r2;
        r42.flags |= 32;
        return;
    L13:
        return;
    }

    private void applyMonoForumPeerId(TLRPC.TL_messages_sendMedia r3, long r4) {
        if (r4 == 0) goto L12;
        TLRPC.InputPeer r2 = getMessagesController().getInputPeer(r4);
        TLRPC.InputReplyTo r42 = r3.reply_to;
        if (r42 != null) goto L7;
        TLRPC.TL_inputReplyToMonoForum r43 = new TLRPC.TL_inputReplyToMonoForum();
        r3.reply_to = r43;
        r43.monoforum_peer_id = r2;
        r3.flags |= 1;
        return;
    L7:
        if ((r42 instanceof TLRPC.TL_inputReplyToMessage) == false) goto L13;
        r42.monoforum_peer_id = r2;
        r42.flags |= 32;
        return;
    L13:
        return;
    }

    private void applyMonoForumPeerId(TLRPC.TL_messages_sendMessage r3, long r4) {
        if (r4 == 0) goto L12;
        TLRPC.InputPeer r2 = getMessagesController().getInputPeer(r4);
        TLRPC.InputReplyTo r42 = r3.reply_to;
        if (r42 != null) goto L7;
        TLRPC.TL_inputReplyToMonoForum r43 = new TLRPC.TL_inputReplyToMonoForum();
        r3.reply_to = r43;
        r43.monoforum_peer_id = r2;
        r3.flags |= 1;
        return;
    L7:
        if ((r42 instanceof TLRPC.TL_inputReplyToMessage) == false) goto L13;
        r42.monoforum_peer_id = r2;
        r42.flags |= 32;
        return;
    L13:
        return;
    }

    private void applyMonoForumPeerId(TLRPC.TL_messages_sendMultiMedia r3, long r4) {
        if (r4 == 0) goto L12;
        TLRPC.InputPeer r2 = getMessagesController().getInputPeer(r4);
        TLRPC.InputReplyTo r42 = r3.reply_to;
        if (r42 != null) goto L7;
        TLRPC.TL_inputReplyToMonoForum r43 = new TLRPC.TL_inputReplyToMonoForum();
        r3.reply_to = r43;
        r43.monoforum_peer_id = r2;
        r3.flags |= 1;
        return;
    L7:
        if ((r42 instanceof TLRPC.TL_inputReplyToMessage) == false) goto L13;
        r42.monoforum_peer_id = r2;
        r42.flags |= 32;
        return;
    L13:
        return;
    }

    public static int canSendMessageToChat(TLRPC.Chat r13, MessageObject r14) {
        boolean r0 = ChatObject.canSendStickers(r13);
        boolean r1 = ChatObject.canSendPhoto(r13);
        boolean r2 = ChatObject.canSendVideo(r13);
        boolean r3 = ChatObject.canSendDocument(r13);
        ChatObject.canSendEmbed(r13);
        boolean r4 = ChatObject.canSendPolls(r13);
        boolean r5 = ChatObject.canSendRoundVideo(r13);
        boolean r6 = ChatObject.canSendVoice(r13);
        boolean r7 = ChatObject.canSendMusic(r13);
        if (r14.isSticker() == false) goto L5;
    L12:
        boolean r8 = true;
    L13:
        if (r0 == true) goto L21;
        if (r8 == false) goto L21;
        if (ChatObject.isActionBannedByDefault(r13, 8) == false) goto L19;
        return 4;
    L19:
        return 1;
    L21:
        if (r1 == true) goto L33;
        if ((r14.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L33;
        if (r14.isVideo() == true) goto L33;
        if (r8 == true) goto L33;
        if (ChatObject.isActionBannedByDefault(r13, 16) == false) goto L30;
        return 10;
    L30:
        return 12;
    L33:
        if (r7 == true) goto L41;
        if (r14.isMusic() == false) goto L41;
        if (ChatObject.isActionBannedByDefault(r13, 18) == false) goto L39;
        return 19;
    L39:
        return 20;
    L41:
        if (r2 == false) goto L43;
    L51:
        if (r4 == true) goto L61;
        if ((r14.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) == false) goto L61;
        if (ChatObject.isActionBannedByDefault(r13, 10) == false) goto L58;
        return 6;
    L58:
        return 3;
    L61:
        if (r4 == false) goto L63;
    L69:
        if (r6 == false) goto L71;
    L78:
        if (r5 == false) goto L80;
    L86:
        if (r3 == false) goto L88;
    L94:
        return 0;
    L88:
        if ((r14.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) == false) goto L94;
        if (r8 == true) goto L94;
        if (ChatObject.isActionBannedByDefault(r13, 19) == false) goto L93;
        return 17;
    L93:
        return 18;
    L80:
        if (MessageObject.isRoundVideoMessage(r14.messageOwner) == false) goto L86;
        if (ChatObject.isActionBannedByDefault(r13, 21) == false) goto L85;
        return 15;
    L85:
        return 16;
    L71:
        if (MessageObject.isVoiceMessage(r14.messageOwner) == false) goto L78;
        if (ChatObject.isActionBannedByDefault(r13, 20) == false) goto L76;
        return 13;
    L76:
        return 14;
    L63:
        if ((r14.messageOwner.media instanceof TLRPC.TL_messageMediaToDo) == false) goto L69;
        if (ChatObject.isActionBannedByDefault(r13, 10) == false) goto L67;
        return 21;
    L67:
        return 22;
    L43:
        if (r14.isVideo() == false) goto L51;
        if (r8 == true) goto L51;
        if (ChatObject.isActionBannedByDefault(r13, 17) == false) goto L49;
        return 9;
    L49:
        return 11;
    L5:
        if (r14.isAnimatedSticker() == true) goto L12;
        if (r14.isGif() == true) goto L12;
        if (r14.isGame() == true) goto L12;
        r8 = false;
        goto L13
    }

    private static boolean checkFileSize(AccountInstance r10, Uri r11) {
        long r1 = 0;
        AssetFileDescriptor r3 = ApplicationLoader.applicationContext.getContentResolver().openAssetFileDescriptor(r11, "r", null);     // Catch: Exception -> L6
        if (r3 == null) goto L8;
        r3.getLength();     // Catch: Exception -> L6
    L8:
        Cursor r112 = ApplicationLoader.applicationContext.getContentResolver().query(r11, new String[]{"_size"}, null, null, null);     // Catch: Exception -> L6
        int r0 = r112.getColumnIndex("_size");     // Catch: Exception -> L6
        r112.moveToFirst();     // Catch: Exception -> L6
        r1 = r112.getLong(r0);     // Catch: Exception -> L6
        r112.close();     // Catch: Exception -> L6
    L12:
        if (FileLoader.checkUploadFileSize(r10.getCurrentAccount(), r1) == true) goto L15;
        return true;
    L15:
        return false;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    public static boolean checkUpdateStickersOrder(CharSequence r3) {
        if ((r3 instanceof Spannable) == false) goto L12;
        AnimatedEmojiSpan[] r32 = (AnimatedEmojiSpan[]) ((Spannable) r3).getSpans(0, r3.length(), AnimatedEmojiSpan.class);
        int r0 = 0;
    L6:
        if (r0 >= r32.length) goto L12;
        if (r32[r0].fromEmojiKeyboard == true) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private static void clearRichTextParents(TL_iv.RichText r3) {
        if (r3 == null) goto L14;
        r3.parentRichText = null;
        TL_iv.RichText r0 = r3.text;
        if (r0 == null) goto L7;
        clearRichTextParents(r0);
    L7:
        ArrayList<TL_iv.RichText> r32 = r3.texts;
        if (r32 == null) goto L12;
        int r02 = r32.size();
        int r1 = 0;
    L10:
        if (r1 >= r02) goto L15;
        TL_iv.RichText r2 = r32.get(r1);
        r1 = r1 + 1;
        clearRichTextParents(r2);
        goto L10
    L15:
        return;
    L12:
        return;
    }

    private static void clearRichTextParentsInBlock(TL_iv.PageBlock r12, IdentityHashMap<Object, Boolean> r13) {
        if (r12 != null) goto L4;
        return;
    L4:
        if (r13.put(r12, Boolean.TRUE) != null) goto L87;
        Field[] r0 = r12.getClass().getFields();
        int r1 = r0.length;
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L88;
        Field r4 = r0[r3];
        if (Modifier.isStatic(r4.getModifiers()) == true) goto L53;
        Object r42 = r4.get(r12);     // Catch: IllegalAccessException -> L56
        if ((r42 instanceof TL_iv.RichText) == false) goto L15;
        clearRichTextParents((TL_iv.RichText) r42);     // Catch: IllegalAccessException -> L56
        goto L53
    L15:
        if ((r42 instanceof TL_iv.PageBlock) == false) goto L18;
        clearRichTextParentsInBlock((TL_iv.PageBlock) r42, r13);     // Catch: IllegalAccessException -> L56
        goto L53
    L18:
        if ((r42 instanceof TL_iv.PageCaption) == false) goto L21;
        clearRichTextParents(((TL_iv.PageCaption) r42).text);     // Catch: IllegalAccessException -> L56
        clearRichTextParents(((TL_iv.PageCaption) r42).credit);     // Catch: IllegalAccessException -> L56
        goto L53
    L21:
        if ((r42 instanceof List) == false) goto L53;
        Iterator r43 = ((List) r42).iterator();     // Catch: IllegalAccessException -> L56
    L24:
        if (r43.hasNext() == false) goto L53;
        Object r5 = r43.next();     // Catch: IllegalAccessException -> L56
        if ((r5 instanceof TL_iv.PageBlock) == true) goto L27;
        if ((r5 instanceof TL_iv.RichText) == true) goto L30;
        if ((r5 instanceof TL_iv.PageListItem) == true) goto L35;
        if ((r5 instanceof TL_iv.PageListOrderedItem) == false) goto L24;
    L35:
        Field[] r6 = r5.getClass().getFields();     // Catch: IllegalAccessException -> L56
        int r7 = r6.length;     // Catch: IllegalAccessException -> L56
        int r8 = 0;
    L36:
        if (r8 >= r7) goto L24;
        Field r9 = r6[r8];     // Catch: IllegalAccessException -> L56
        if (Modifier.isStatic(r9.getModifiers()) == true) goto L52;
        Object r92 = r9.get(r5);     // Catch: IllegalAccessException -> L55
        if ((r92 instanceof TL_iv.RichText) == false) goto L44;
        clearRichTextParents((TL_iv.RichText) r92);     // Catch: IllegalAccessException -> L55
        goto L52
    L44:
        if ((r92 instanceof List) == false) goto L52;
        Iterator r93 = ((List) r92).iterator();     // Catch: IllegalAccessException -> L55
    L47:
        if (r93.hasNext() == false) goto L52;
        Object r10 = r93.next();     // Catch: IllegalAccessException -> L55
        if ((r10 instanceof TL_iv.PageBlock) == false) goto L47;
        clearRichTextParentsInBlock((TL_iv.PageBlock) r10, r13);     // Catch: IllegalAccessException -> L55
    L52:
        r8 = r8 + 1;
        goto L36
    L30:
        clearRichTextParents((TL_iv.RichText) r5);     // Catch: IllegalAccessException -> L56
        goto L24
    L27:
        clearRichTextParentsInBlock((TL_iv.PageBlock) r5, r13);     // Catch: IllegalAccessException -> L56
    L53:
        r3 = r3 + 1;
        goto L7
    L88:
        return;
    }

    private ArrayList<TLRPC.DocumentAttribute> copyAttributesForSecretChat(ArrayList<TLRPC.DocumentAttribute> r7) {
        ArrayList<TLRPC.DocumentAttribute> r6 = new ArrayList();
        if (r7 == null) goto L12;
        int r0 = r7.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L12;
        TLRPC.DocumentAttribute r2 = r7.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L10;
        TLRPC.TL_documentAttributeVideo_layer159 r3 = new TLRPC.TL_documentAttributeVideo_layer159();
        r3.flags = r2.flags;
        r3.round_message = r2.round_message;
        r3.supports_streaming = r2.supports_streaming;
        r3.duration = r2.duration;
        r3.w = r2.w;
        r3.h = r2.h;
        r6.add(r3);
    L11:
        r1 = r1 + 1;
        goto L6
    L10:
        r6.add(r2);
    L12:
        return r6;
    }

    private static void copyRange(File r2, long r3, long r5, File r7) {
        FileChannel r22 = new FileInputStream(r2).getChannel();
        FileChannel r72 = new FileOutputStream(r7).getChannel();     // Catch: Throwable -> L17
        r22.position(r3);     // Catch: Throwable -> L11
        ByteBuffer r32 = ByteBuffer.allocate(262144);     // Catch: Throwable -> L11
    L6:
        if (r5 <= 0) goto L13;
        r32.clear();     // Catch: Throwable -> L11
        r32.limit((int) Math.min(r32.capacity(), r5));     // Catch: Throwable -> L11
        int r4 = r22.read(r32);     // Catch: Throwable -> L11
        if (r4 <= 0) goto L13;
        r32.flip();     // Catch: Throwable -> L11
        r72.write(r32);     // Catch: Throwable -> L11
        r5 = r5 - r4;     // Catch: Throwable -> L11
    L13:
        r72.force(true);     // Catch: Throwable -> L11
        r72.close();     // Catch: Throwable -> L17
        r22.close();
        return;
    L11:
        th = move-exception;
        if (r72 != null) goto L35;
    L24:
        throw th;     // Catch: Throwable -> L17
    L35:
        r72.close();     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        if (r22 != null) goto L31;
    L30:
        throw th;
    L31:
        r22.close();     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        th.addSuppressed(th);
        goto L30
    }

    private static VideoEditedInfo createCompressionSettings(String r25, long r26) {
        int[] r3 = new int[11];
        AnimatedFileNative.getVideoInfo(r25, r3, r26);
        if (r3[0] == 0) goto L5;
        long r5 = new File(r25).length();
        int r7 = MediaController.getVideoBitrate(r25);
        if (r7 != (-1)) goto L12;
        r7 = r3[3];
    L12:
        float r10 = r3[4];
        long r12 = r3[5];
        int r15 = r3[7];
        VideoEditedInfo r8 = new VideoEditedInfo();
        r8.startTime = -1;
        r8.endTime = -1;
        r8.bitrate = r7;
        r8.originalPath = r25;
        r8.videoOffset = r26;
        r8.framerate = r15;
        r8.estimatedDuration = (long) Math.ceil(r10);
        boolean r1 = true;
        int r2 = r3[1];
        r8.originalWidth = r2;
        r8.resultWidth = r2;
        int r52 = r3[2];
        r8.originalHeight = r52;
        r8.resultHeight = r52;
        r8.rotationValue = r3[8];
        r8.originalDuration = (long) (r10 * 1000.0f);
        float r22 = Math.max(r2, r52);
        if (r22 <= 3840.0f) goto L16;
        int r23 = 7;
    L31:
        int r53 = Math.round(DownloadController.getInstance(UserConfig.selectedAccount).getMaxVideoBitrate() / (100.0f / r23));
        if (r53 <= r23) goto L35;
        r53 = r23;
    L35:
        if (new File(r25).length() >= 1048576000) goto L59;
        if (r53 == r23) goto L38;
    L41:
        if (r53 == 1) goto L51;
        if (r53 == 2) goto L50;
        if (r53 == 3) goto L49;
        if (r53 == 5) goto L48;
        if (r53 == 6) goto L47;
        float r54 = 1920.0f;
    L52:
        int r0 = r8.originalWidth;
        int r24 = r8.originalHeight;
        if (r0 <= r24) goto L56;
        float r27 = r0;
    L55:
        float r55 = r54 / r27;
        r8.resultWidth = Math.round((r0 * r55) / 2.0f) * 2;
        r8.resultHeight = Math.round((r8.originalHeight * r55) / 2.0f) * 2;
    L58:
        r7 = MediaController.makeVideoBitrate(r8.originalHeight, r8.originalWidth, r7, r8.resultHeight, r8.resultWidth);
    L60:
        if (r1 == true) goto L62;
        r8.resultWidth = r8.originalWidth;
        r8.resultHeight = r8.originalHeight;
        r8.bitrate = r7;
        r8.estimatedSize = r5;
    L64:
        if (r8.estimatedSize != 0) goto L66;
        r8.estimatedSize = 1;
    L66:
        return r8;
    L62:
        r8.bitrate = r7;
        r8.estimatedSize = (long) (r12 + (((r10 / 1000.0f) * MediaController.extractRealEncoderBitrate(r8.resultWidth, r8.resultHeight, r7, false)) / 8.0f));
        goto L64
    L56:
        r27 = r24;
        goto L55
    L47:
        r54 = 3840.0f;
        goto L52
    L48:
        r54 = 2560.0f;
        goto L52
    L49:
        r54 = 1280.0f;
        goto L52
    L50:
        r54 = 854.0f;
        goto L52
    L51:
        r54 = 480.0f;
        goto L52
    L38:
        if (Math.max(r8.originalWidth, r8.originalHeight) > 1280) goto L41;
        r1 = false;
        goto L58
    L59:
        r1 = false;
        goto L60
    L16:
        if (r22 <= 2560.0f) goto L19;
        r23 = 6;
        goto L31
    L19:
        if (r22 <= 1920.0f) goto L22;
        r23 = 5;
        goto L31
    L22:
        if (r22 <= 1280.0f) goto L25;
        r23 = 4;
        goto L31
    L25:
        if (r22 <= 854.0f) goto L28;
        r23 = 3;
        goto L31
    L28:
        if (r22 <= 640.0f) goto L30;
        r23 = 2;
        goto L31
    L30:
        r23 = 1;
        goto L31
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L67;
        FileLog.d("video hasn't avc1 atom");
        return null;
    L67:
        return null;
    }

    public static Bitmap createVideoThumbnail(String r4, int r5) {
        if (r5 != 2) goto L6;
        float r52 = 1920.0f;
    L9:
        Bitmap r42 = createVideoThumbnailAtTime(r4, 0);
        if (r42 == null) goto L17;
        int r0 = r42.getWidth();
        int r1 = r42.getHeight();
        float r2 = r0;
        if (r2 <= r52) goto L14;
    L15:
        float r02 = Math.max(r0, r1) / r52;
        return Bitmap.createScaledBitmap(r42, (int) (r2 / r02), (int) (r1 / r02), true);
    L14:
        if (r1 > r52) goto L15;
        return r42;
    L17:
        return r42;
    L6:
        if (r5 != 3) goto L8;
        r52 = 96.0f;
        goto L9
    L8:
        r52 = 512.0f;
        goto L9
    }

    public static Bitmap createVideoThumbnailAtTime(String r2, long r3) {
        return createVideoThumbnailAtTime(r2, r3, null, false);
    }

    public static Bitmap createVideoThumbnailAtTime(String r19, long r20, int[] r22, boolean r23) {
        if (r23 == false) goto L12;
        AnimatedFileDrawable r5 = new AnimatedFileDrawable(new File(r19), true, 0, 0, null, null, null, 0, 0, true, null);
        Bitmap r4 = r5.getFrameAtTime(r20, r23);
        if (r22 == null) goto L7;
        r22[0] = r5.getOrientation();
    L7:
        r5.recycle();
        if (r4 == null) goto L10;
        return r4;
    L10:
        return createVideoThumbnailAtTime(r19, r20, r22, false);
    L12:
        MediaMetadataRetriever r3 = new MediaMetadataRetriever();
        Bitmap r42 = null;
        r3.setDataSource(r19);     // Catch: Throwable -> L17 Exception -> L25
        r42 = r3.getFrameAtTime(r20, 1);     // Catch: Throwable -> L17 Exception -> L25
        if (r42 != null) goto L29;
        r42 = r3.getFrameAtTime(r20, 3);     // Catch: Throwable -> L17 Exception -> L25
    L17:
        th = move-exception;
        r3.release();     // Catch: Throwable -> L24
    L22:
        throw th;
    L29:
        r3.release();     // Catch: Throwable -> L26
    L23:
        return r42;
    }

    public static void ensureMediaThumbExists(AccountInstance r8, boolean r9, TLObject r10, String r11, Uri r12, long r13) {
        ensureMediaThumbExists(r8, r9, r10, r11, r12, r13, false);
    }

    public static void ensureMediaThumbExists(AccountInstance r20, boolean r21, TLObject r22, String r23, Uri r24, long r25, boolean r27) {
        if ((r22 instanceof TLRPC.TL_photo) == false) goto L30;
        TLRPC.TL_photo r0 = (TLRPC.TL_photo) r22;
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r0.sizes, 90);
        if ((r3 instanceof TLRPC.TL_photoStrippedSize) == false) goto L7;
    L10:
        boolean r4 = true;
    L11:
        TLRPC.PhotoSize r9 = FileLoader.getClosestPhotoSizeWithSize(r0.sizes, AndroidUtilities.getPhotoSize(r27));
        boolean r7 = FileLoader.getInstance(r20.getCurrentAccount()).getPathToAttach(r9, false).exists();
        if (r4 == false) goto L14;
        if (r7 == false) goto L14;
        return;
    L14:
        Bitmap r8 = ImageLoader.loadBitmap(r23, r24, AndroidUtilities.getPhotoSize(), AndroidUtilities.getPhotoSize(), true);
        if (r8 != null) goto L17;
        r8 = ImageLoader.loadBitmap(r23, r24, 800.0f, 800.0f, true);
    L17:
        Bitmap r10 = r8;
        if (r7 == true) goto L22;
        TLRPC.PhotoSize r1 = ImageLoader.scaleAndSaveImage(r9, r10, Bitmap.CompressFormat.JPEG, true, AndroidUtilities.getPhotoSize(), AndroidUtilities.getPhotoSize(), 80, false, 101, 101, false);
        if (r1 == r9) goto L22;
        r0.sizes.add(0, r1);
    L22:
        if (r4 == true) goto L26;
        TLRPC.PhotoSize r12 = ImageLoader.scaleAndSaveImage(r3, r10, 90.0f, 90.0f, 55, true, false);
        if (r12 == r3) goto L26;
        r0.sizes.add(0, r12);
    L26:
        if (r10 == null) goto L56;
        r10.recycle();
        return;
    L56:
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_photoPathSize) == true) goto L10;
        r4 = FileLoader.getInstance(r20.getCurrentAccount()).getPathToAttach(r3, true).exists();
        goto L11
    L30:
        if ((r22 instanceof TLRPC.TL_document) == false) goto L57;
        TLRPC.TL_document r02 = (TLRPC.TL_document) r22;
        if (MessageObject.isVideoDocument(r02) == true) goto L36;
        if (MessageObject.isNewGifDocument(r02) == true) goto L36;
        return;
    L36:
        if (MessageObject.isDocumentHasThumb(r02) == false) goto L59;
        int r32 = 320;
        TLRPC.PhotoSize r2 = FileLoader.getClosestPhotoSizeWithSize(r02.thumbs, 320);
        if ((r2 instanceof TLRPC.TL_photoStrippedSize) == false) goto L40;
        return;
    L40:
        if ((r2 instanceof TLRPC.TL_photoPathSize) == false) goto L43;
        return;
    L43:
        if (FileLoader.getInstance(r20.getCurrentAccount()).getPathToAttach(r2, true).exists() == true) goto L61;
        Bitmap r72 = createVideoThumbnailAtTime(r23, r25);
        if (r72 != null) goto L47;
        r72 = createVideoThumbnail(r23, 1);
    L47:
        if (r21 == false) goto L49;
        r32 = 90;
    L49:
        ArrayList<TLRPC.PhotoSize> r03 = r02.thumbs;
        float r13 = r32;
        if (r32 <= 90) goto L52;
        int r33 = 80;
    L53:
        r03.set(0, ImageLoader.scaleAndSaveImage(r2, r72, r13, r13, r33, false, true));
        return;
    L52:
        r33 = 55;
        goto L53
    L61:
        return;
    L59:
        return;
    }

    public static void fillVideoAttribute(String r6, TLRPC.TL_documentAttributeVideo r7, VideoEditedInfo r8) {
        MediaMetadataRetriever r2 = null;
        MediaMetadataRetriever r3 = new MediaMetadataRetriever();     // Catch: Throwable -> L31 Exception -> L33
        r3.setDataSource(r6);     // Catch: Throwable -> L7 Exception -> L9
        String r22 = r3.extractMetadata(18);     // Catch: Throwable -> L7 Exception -> L9
        if (r22 == null) goto L11;
        r7.w = Integer.parseInt(r22);     // Catch: Throwable -> L7 Exception -> L9
    L11:
        String r23 = r3.extractMetadata(19);     // Catch: Throwable -> L7 Exception -> L9
        if (r23 == null) goto L15;
        r7.h = Integer.parseInt(r23);     // Catch: Throwable -> L7 Exception -> L9
    L15:
        if (r3.extractMetadata(9) == null) goto L17;
        r7.duration = Long.parseLong(r2) / 1000.0d;     // Catch: Throwable -> L7 Exception -> L9
    L17:
        String r24 = r3.extractMetadata(24);     // Catch: Throwable -> L7 Exception -> L9
        if (r24 == null) goto L59;
        int r25 = Utilities.parseInt(r24);     // Catch: Throwable -> L7 Exception -> L9
        if (r8 == null) goto L23;
        r8.rotationValue = r25;     // Catch: Throwable -> L7 Exception -> L9
        goto L59
    L23:
        if (r25 != 90) goto L25;
    L26:
        int r82 = r7.w;     // Catch: Throwable -> L7 Exception -> L9
        r7.w = r7.h;     // Catch: Throwable -> L7 Exception -> L9
        r7.h = r82;     // Catch: Throwable -> L7 Exception -> L9
        goto L59
    L25:
        if (r25 == 270) goto L26;
    L59:
        r3.release();     // Catch: Exception -> L29
        return;
    L29:
        e = move-exception;
        FileLog.e(e);
        return;
    L9:
        e = e;
        r2 = r3;
    L34:
        FileLog.e(e);     // Catch: Throwable -> L31
        if (r2 == null) goto L61;
        r2.release();     // Catch: Exception -> L38
    L38:
        e = move-exception;
        FileLog.e(e);
    L61:
        MediaPlayer r62 = MediaPlayer.create(ApplicationLoader.applicationContext, Uri.fromFile(new File(r6)));     // Catch: Exception -> L44
        if (r62 == null) goto L66;
        r7.duration = r62.getDuration() / 1000.0d;     // Catch: Exception -> L44
        r7.w = r62.getVideoWidth();     // Catch: Exception -> L44
        r7.h = r62.getVideoHeight();     // Catch: Exception -> L44
        r62.release();     // Catch: Exception -> L44
        return;
    L66:
        return;
    L44:
        e = move-exception;
        FileLog.e(e);
        return;
    L7:
        th = th;
        r2 = r3;
    L47:
        if (r2 != null) goto L53;
    L52:
        throw th;
    L53:
        r2.release();     // Catch: Exception -> L50
    L50:
        e = move-exception;
        FileLog.e(e);
    L33:
        e = e;
    L31:
        th = th;
        goto L47
    }

    private DelayedMessage findMaxDelayedMessageForMessageId(int r10, long r11) {
        Iterator<Map.Entry<String, ArrayList<DelayedMessage>>> r9 = this.delayedMessages.entrySet().iterator();
        DelayedMessage r0 = null;
        int r1 = Integer.MIN_VALUE;
    L4:
        if (r9.hasNext() == false) goto L27;
        ArrayList<DelayedMessage> r2 = r9.next().getValue();
        int r3 = r2.size();
        int r5 = 0;
    L6:
        if (r5 >= r3) goto L4;
        DelayedMessage r6 = r2.get(r5);
        int r7 = r6.type;
        if (r7 == 4) goto L11;
        if (r7 == 0) goto L11;
    L26:
        r5 = r5 + 1;
    L11:
        if (r6.peer != r11) goto L26;
        MessageObject r72 = r6.obj;
        if (r72 == null) goto L15;
        int r73 = r72.getId();
    L21:
        if (r73 == 0) goto L26;
        if (r73 <= r10) goto L26;
        if (r0 != null) goto L26;
        if (r1 >= r73) goto L26;
        r0 = r6;
        r1 = r73;
        goto L26
    L15:
        ArrayList<MessageObject> r74 = r6.messageObjects;
        if (r74 != null) goto L18;
    L20:
        r73 = 0;
        goto L21
    L18:
        if (r74.isEmpty() == true) goto L20;
        r73 = r6.messageObjects.get(r7.size() - 1).getId();
        goto L21
    L27:
        return r0;
    }

    private static void finishGroup(AccountInstance r1, long r2, int r4) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda62(r1, r2, r4));
    }

    public static SendMessagesHelper getInstance(int r3) {
        SendMessagesHelper r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(SendMessagesHelper.class);
        SendMessagesHelper r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        SendMessagesHelper[] r03 = Instance;     // Catch: Throwable -> L9
        SendMessagesHelper r2 = new SendMessagesHelper(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(SendMessagesHelper.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static String getKeyForPhotoSize(AccountInstance r5, TLRPC.PhotoSize r6, Bitmap[] r7, boolean r8, boolean r9) {
        if (r6 != null) goto L5;
    L19:
        return null;
    L5:
        if (r6.location == null) goto L19;
        PointF r1 = ChatMessageCell.getMessageSize(r6.w, r6.h);
        if (r7 != null) goto L21;
    L13:
        Locale r52 = Locale.US;
        if (r8 == false) goto L16;
        String r72 = "%d_%d@%d_%d_b";
    L18:
        return String.format(r52, r72, new Object[]{Long.valueOf(r6.location.volume_id), Integer.valueOf(r6.location.local_id), Integer.valueOf((int) (r1.x / AndroidUtilities.density)), Integer.valueOf((int) (r1.y / AndroidUtilities.density))});
    L16:
        r72 = "%d_%d@%d_%d";
        goto L18
    L21:
        BitmapFactory.Options r2 = new BitmapFactory.Options();     // Catch: Throwable -> L20
        r2.inJustDecodeBounds = true;     // Catch: Throwable -> L20
        File r53 = FileLoader.getInstance(r5.getCurrentAccount()).getPathToAttach(r6, r9);     // Catch: Throwable -> L20
        FileInputStream r92 = new FileInputStream(r53);     // Catch: Throwable -> L20
        BitmapFactory.decodeStream(r92, null, r2);     // Catch: Throwable -> L20
        r92.close();     // Catch: Throwable -> L20
        float r93 = Math.max(r2.outWidth / r1.x, r2.outHeight / r1.y);     // Catch: Throwable -> L20
        if (r93 >= 1.0f) goto L12;
        r93 = 1.0f;
    L12:
        r2.inJustDecodeBounds = false;     // Catch: Throwable -> L20
        r2.inSampleSize = (int) r93;     // Catch: Throwable -> L20
        r2.inPreferredConfig = Bitmap.Config.RGB_565;     // Catch: Throwable -> L20
        FileInputStream r94 = new FileInputStream(r53);     // Catch: Throwable -> L20
        r7[0] = BitmapFactory.decodeStream(r94, null, r2);     // Catch: Throwable -> L20
        r94.close();     // Catch: Throwable -> L20
        goto L13
    }

    private TLRPC.PhotoSize getThumbForSecretChat(ArrayList<TLRPC.PhotoSize> r6) {
        if (r6 != null) goto L5;
    L27:
        return null;
    L5:
        if (r6.isEmpty() == true) goto L27;
        int r0 = r6.size();
        int r2 = 0;
    L8:
        if (r2 >= r0) goto L27;
        TLRPC.PhotoSize r3 = r6.get(r2);
        if (r3 == null) goto L26;
        if ((r3 instanceof TLRPC.TL_photoPathSize) == true) goto L26;
        if ((r3 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L26;
        if (r3.location == null) goto L26;
        if ((r3 instanceof TLRPC.TL_photoStrippedSize) == false) goto L21;
        return r3;
    L21:
        TLRPC.TL_photoSize_layer127 r5 = new TLRPC.TL_photoSize_layer127();
        r5.type = r3.type;
        r5.w = r3.w;
        r5.h = r3.h;
        r5.size = r3.size;
        byte[] r62 = r3.bytes;
        r5.bytes = r62;
        if (r62 != null) goto L24;
        r5.bytes = new byte[0];
    L24:
        TLRPC.TL_fileLocation_layer82 r63 = new TLRPC.TL_fileLocation_layer82();
        r5.location = r63;
        TLRPC.FileLocation r02 = r3.location;
        r63.dc_id = r02.dc_id;
        r63.volume_id = r02.volume_id;
        r63.local_id = r02.local_id;
        r63.secret = r02.secret;
        return r5;
    L26:
        r2 = r2 + 1;
        goto L8
    }

    public static CharSequence getTrimmedString(CharSequence r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        CharSequence r0 = AndroidUtilities.getTrimmedString(r4);
        if (r0.length() != 0) goto L9;
        return r0;
    L9:
        if (r4.length() <= 0) goto L14;
        if (r4.charAt(0) != '\n') goto L14;
        r4 = r4.subSequence(1, r4.length());
    L14:
        if (r4.length() <= 0) goto L18;
        if (r4.charAt(r4.length() - 1) != '\n') goto L18;
        r4 = r4.subSequence(0, r4.length() - 1);
    L18:
        return r4;
    }

    public static String getTrimmedString(String r4) {
        String r0 = r4.trim();
        if (r0.length() != 0) goto L6;
        return r0;
    L6:
        if (r4.startsWith("\n") == false) goto L9;
        r4 = r4.substring(1);
    L9:
        if (r4.endsWith("\n") == false) goto L11;
        r4 = r4.substring(0, r4.length() - 1);
        goto L9
    L11:
        return r4;
    }

    private static void handleError(int r1, AccountInstance r2) {
        if (r1 == 0) goto L5;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda57(r1, r2));
        return;
    }

    private /* synthetic */ void lambda$deletePollOption$27(TLRPC.Updates r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        getMessagesController().processUpdates(r1, false);
        return;
    }

    private /* synthetic */ void lambda$didReceivedNotification$1(TLRPC.TL_photo r10, MessageObject r11, File r12, DelayedMessage r13, String r14) {
        if (r10 == null) goto L10;
        TLRPC.Message r142 = r11.messageOwner;
        r142.media.photo = r10;
        r142.attachPath = r12.toString();
        ArrayList<TLRPC.Message> r1 = new ArrayList();
        r1.add(r11.messageOwner);
        getMessagesStorage().putMessages(r1, false, true, false, 0, r11.scheduled ? 1 : 0, 0);
        getNotificationCenter().postNotificationName(NotificationCenter.updateMessageMedia, new Object[]{r11.messageOwner});
        ArrayList<TLRPC.PhotoSize> r122 = r10.sizes;
        r13.photoSize = r122.get(r122.size() - 1);
        r13.locationParent = r10;
        r13.httpLocation = null;
        if (r13.type != 4) goto L7;
        r13.performMediaUpload = true;
        performSendDelayedMessage(r13, r13.messageObjects.indexOf(r11));
        return;
    L7:
        performSendDelayedMessage(r13);
        return;
    L10:
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.e("can't load image " + r14 + " to file " + r12.toString());
    L12:
        r13.markAsError();
    }

    private /* synthetic */ void lambda$didReceivedNotification$2(File r10, MessageObject r11, DelayedMessage r12, String r13) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda75(this, generatePhotoSizes(r10.toString(), null), r11, r10, r12, r13));
    }

    private /* synthetic */ void lambda$didReceivedNotification$3(DelayedMessage r11, File r12, TLRPC.Document r13, MessageObject r14) {
        r11.httpLocation = null;
        TLRPC.Message r0 = r11.obj.messageOwner;
        r0.attachPath = r12.toString();
        if (r13.thumbs.isEmpty() == true) goto L7;
        TLRPC.PhotoSize r122 = r13.thumbs.get(0);
        if ((r122 instanceof TLRPC.TL_photoStrippedSize) == true) goto L7;
        r11.photoSize = r122;
        r11.locationParent = r13;
    L7:
        ArrayList<TLRPC.Message> r2 = new ArrayList();
        r2.add(r14.messageOwner);
        getMessagesStorage().putMessages(r2, false, true, false, 0, r14.scheduled ? 1 : 0, 0);
        r11.performMediaUpload = true;
        performSendDelayedMessage(r11);
        getNotificationCenter().postNotificationName(NotificationCenter.updateMessageMedia, new Object[]{r11.obj.messageOwner});
    }

    private /* synthetic */ void lambda$didReceivedNotification$4(DelayedMessage r8, File r9, MessageObject r10) {
        TLRPC.Document r5 = r8.obj.getDocument();
        boolean r1 = false;
        if (r5.thumbs.isEmpty() == false) goto L5;
    L17:
        Bitmap r0 = ImageLoader.loadBitmap(r9.getAbsolutePath(), null, 90.0f, 90.0f, true);     // Catch: Exception -> L13
        if (r0 == null) goto L15;
        r5.thumbs.clear();     // Catch: Exception -> L13
        ArrayList<TLRPC.PhotoSize> r2 = r5.thumbs;     // Catch: Exception -> L13
        if (r8.sendEncryptedRequest == null) goto L11;
        r1 = true;
    L11:
        r2.add(ImageLoader.scaleAndSaveImage(r0, 90.0f, 90.0f, 55, r1));     // Catch: Exception -> L13
        r0.recycle();     // Catch: Exception -> L13
    L15:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda93(this, r8, r9, r5, r10));
        return;
    L13:
        e = move-exception;
        r5.thumbs.clear();
        FileLog.e(e);
        goto L15
    L5:
        if ((r5.thumbs.get(0).location instanceof TLRPC.TL_fileLocationUnavailable) == false) goto L15;
        goto L15
    }

    private /* synthetic */ void lambda$editMessage$20(TLRPC.TL_error r2, BaseFragment r3, TLRPC.TL_messages_editMessage r4) {
        AlertsCreator.processError(this.currentAccount, r2, r3, r4, new Object[0]);
    }

    private /* synthetic */ void lambda$editMessage$21(BaseFragment r1, TLRPC.TL_messages_editMessage r2, TLObject r3, TLRPC.TL_error r4) {
        if (r4 != null) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r3, false);
        return;
    L5:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda1(this, r4, r1, r2));
    }

    private /* synthetic */ void lambda$new$0() {
        getNotificationCenter().addObserver(this, NotificationCenter.fileUploaded);
        getNotificationCenter().addObserver(this, NotificationCenter.fileUploadProgressChanged);
        getNotificationCenter().addObserver(this, NotificationCenter.fileUploadFailed);
        getNotificationCenter().addObserver(this, NotificationCenter.filePreparingStarted);
        getNotificationCenter().addObserver(this, NotificationCenter.fileNewChunkAvailable);
        getNotificationCenter().addObserver(this, NotificationCenter.filePreparingFailed);
        getNotificationCenter().addObserver(this, NotificationCenter.httpFileDidFailedLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.httpFileDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.fileLoaded);
        getNotificationCenter().addObserver(this, NotificationCenter.fileLoadFailed);
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$49(TLObject r11, DelayedMessage r12) {
        if ((r11 instanceof TLRPC.TL_messageMediaDocument) == false) goto L16;
        TLRPC.TL_inputMediaUploadedPhoto r0 = new TLRPC.TL_inputMediaUploadedPhoto();
        r0.live_photo = true;
        r0.file = r12.coverFile;
        TLRPC.TL_inputDocument r1 = new TLRPC.TL_inputDocument();
        r0.video = r1;
        TLRPC.Document r112 = ((TLRPC.TL_messageMediaDocument) r11).document;
        r1.id = r112.id;
        r1.access_hash = r112.access_hash;
        r1.file_reference = r112.file_reference;
        TLObject r3 = r12.sendRequest;
        if ((r3 instanceof TLRPC.TL_messages_sendMedia) == false) goto L8;
        ((TLRPC.TL_messages_sendMedia) r3).media = r0;
    L14:
        performSendMessageRequest(r3, r12.obj, r12.originalPath, r12, r12.parentObject, null, r12.scheduled);
        return;
    L8:
        if ((r3 instanceof TL_ephemeral.TL_sendMessage) == false) goto L11;
        ((TL_ephemeral.TL_sendMessage) r3).media = r0;
        goto L14
    L11:
        if ((r3 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L13;
        ((TLRPC.TL_messages_addPollAnswer) r3).answer.input_media = r0;
        goto L14
    L13:
        ((TLRPC.TL_messages_editMessage) r3).media = r0;
        goto L14
    L16:
        r12.markAsError();
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$50(DelayedMessage r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda43(this, r2, r1));
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$51(TLObject r9, TLRPC.InputMedia r10, DelayedMessage r11) {
        if ((r9 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L31;
        TLRPC.Photo r92 = ((TLRPC.TL_messageMediaPhoto) r9).photo;
        TLRPC.TL_inputPhoto r0 = new TLRPC.TL_inputPhoto();
        r0.id = r92.id;
        r0.access_hash = r92.access_hash;
        r0.file_reference = r92.file_reference;
        if ((r10 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L8;
        r10.flags |= 64;
        r10.video_cover = r0;
    L10:
        TLRPC.InputMedia r93 = r11.inputUploadMedia;
        if ((r93 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L14;
        r93.flags |= 64;
        r93.video_cover = r0;
    L14:
        if (r11.performMediaUpload == true) goto L16;
    L29:
        performSendMessageRequest(r11.sendRequest, r11.obj, r11.originalPath, r11, r11.parentObject, null, r11.scheduled);
        return;
    L16:
        if (r10.thumb != null) goto L29;
        TLRPC.PhotoSize r94 = r11.photoSize;
        if (r94 == null) goto L29;
        if (r94.location == null) goto L29;
        MessageObject r95 = r11.obj;
        if (r95 == null) goto L27;
        VideoEditedInfo r96 = r95.videoEditedInfo;
        if (r96 == null) goto L27;
        if (r96.isSticker == true) goto L29;
    L27:
        performSendDelayedMessage(r11);
        return;
    L8:
        if ((r10 instanceof TLRPC.TL_inputMediaDocument) == false) goto L10;
        r10.flags |= 8;
        r10.video_cover = r0;
        goto L10
    L31:
        r11.markAsError();
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$52(TLRPC.InputMedia r1, DelayedMessage r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda44(this, r3, r1, r2));
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$53(TLObject r4, TLRPC.InputFile r5, TLRPC.InputMedia r6, DelayedMessage r7, int r8, String r9) {
        if ((r4 instanceof TLRPC.TL_messageMediaDocument) == false) goto L23;
        TLRPC.TL_inputMediaUploadedPhoto r0 = new TLRPC.TL_inputMediaUploadedPhoto();
        r0.live_photo = true;
        r0.file = r5;
        r0.spoiler = r6.spoiler;
        TLRPC.TL_inputDocument r52 = new TLRPC.TL_inputDocument();
        r0.video = r52;
        TLRPC.Document r42 = ((TLRPC.TL_messageMediaDocument) r4).document;
        r52.id = r42.id;
        r52.access_hash = r42.access_hash;
        byte[] r43 = r42.file_reference;
        r52.file_reference = r43;
        if (r43 != null) goto L7;
        r52.file_reference = new byte[0];
    L7:
        TLRPC.TL_messages_sendMultiMedia r44 = (TLRPC.TL_messages_sendMultiMedia) r7.sendRequest;
        if (r8 >= 0) goto L10;
    L12:
        ArrayList<TLRPC.InputMedia> r45 = r7.inputMedias;
        if (r45 == null) goto L18;
        if (r8 < 0) goto L18;
        if (r8 >= r45.size()) goto L18;
        r7.inputMedias.set(r8, r0);
    L18:
        r7.coverFile = null;
        r7.coverPhotoSize = null;
        HashMap<Object, Object> r53 = r7.extraHashMap;
        if (r53 == null) goto L21;
        r53.remove(r9 + "_ct");
    L21:
        uploadMultiMedia(r7, r0, null, r9);
        return;
    L10:
        if (r8 >= r44.multi_media.size()) goto L12;
        r44.multi_media.get(r8).media = r0;
        goto L12
    L23:
        r7.markAsError();
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$54(TLRPC.InputFile r2, TLRPC.InputMedia r3, DelayedMessage r4, int r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda95(this, r7, r2, r3, r4, r5, r6));
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$55(TLObject r5, TLRPC.InputMedia r6, DelayedMessage r7, String r8, MessageObject r9) {
        if ((r5 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L35;
        TLRPC.Photo r52 = ((TLRPC.TL_messageMediaPhoto) r5).photo;
        TLRPC.TL_inputPhoto r0 = new TLRPC.TL_inputPhoto();
        r0.id = r52.id;
        r0.access_hash = r52.access_hash;
        r0.file_reference = r52.file_reference;
        if ((r6 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L8;
        r6.flags |= 64;
        r6.video_cover = r0;
    L10:
        TLRPC.PhotoSize r53 = null;
        r7.coverFile = null;
        r7.coverPhotoSize = null;
        HashMap<Object, Object> r1 = r7.extraHashMap;
        if (r1 == null) goto L13;
        r1.remove(r8 + "_ct");
    L13:
        int r92 = r7.messageObjects.indexOf(r9);
        ArrayList<TLRPC.InputMedia> r12 = r7.inputMedias;
        if (r12 == null) goto L21;
        if (r92 < 0) goto L21;
        if (r92 >= r12.size()) goto L21;
        TLRPC.InputMedia r13 = r7.inputMedias.get(r92);
        if ((r13 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L21;
        r13.flags |= 64;
        r13.video_cover = r0;
    L21:
        HashMap<Object, Object> r02 = r7.extraHashMap;
        if (r02 != null) goto L24;
    L26:
        r7.photoSize = r53;
        if (r6.thumb != null) goto L33;
        if (r53 == null) goto L33;
        if (r53.location == null) goto L33;
        r7.performMediaUpload = true;
        performSendDelayedMessage(r7, r92);
        return;
    L33:
        sendReadyToSendGroup(r7, false, true);
        return;
    L24:
        if (r02.containsKey(r8 + "_t") == false) goto L26;
        r53 = (TLRPC.PhotoSize) r7.extraHashMap.get(r8 + "_t");
        goto L26
    L8:
        if ((r6 instanceof TLRPC.TL_inputMediaDocument) == false) goto L10;
        r6.flags |= 8;
        r6.video_cover = r0;
        goto L10
    L35:
        r7.markAsError();
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$56(TLRPC.InputMedia r1, DelayedMessage r2, String r3, MessageObject r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda0(this, r5, r1, r2, r3, r4));
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$57(TLObject r10, DelayedMessage r11, String r12) {
        if (r10 == null) goto L5;
        TLRPC.TL_messages_stickerSet r102 = (TLRPC.TL_messages_stickerSet) r10;
        getMediaDataController().storeTempStickerSet(r102);
        TLRPC.TL_documentAttributeSticker_layer55 r1 = (TLRPC.TL_documentAttributeSticker_layer55) r11.locationParent;
        TLRPC.TL_inputStickerSetShortName r2 = new TLRPC.TL_inputStickerSetShortName();
        r1.stickerset = r2;
        r2.short_name = r102.set.short_name;
        boolean r103 = true;
    L6:
        ArrayList<DelayedMessage> r122 = this.delayedMessages.remove(r12);
        if (r122 != null) goto L9;
        return;
    L9:
        if (r122.isEmpty() == true) goto L15;
        if (r103 == false) goto L12;
        getMessagesStorage().replaceMessageIfExists(r122.get(0).obj.messageOwner, null, null, false);
    L12:
        SecretChatHelper r22 = getSecretChatHelper();
        TLRPC.DecryptedMessage r3 = (TLRPC.DecryptedMessage) r11.sendEncryptedRequest;
        MessageObject r8 = r11.obj;
        r22.performSendEncryptedRequest(r3, r8.messageOwner, r11.encryptedChat, null, null, r8);
        return;
    L15:
        return;
    L5:
        r103 = false;
        goto L6
    }

    private /* synthetic */ void lambda$performSendDelayedMessage$58(DelayedMessage r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda136(this, r3, r1, r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v23 */
    private /* synthetic */ void lambda$performSendMessageRequest$100(boolean r27, TLRPC.TL_error r28, TLRPC.Message r29, TLObject r30, MessageObject r31, HashMap r32, String r33, TLObject r34) {
        TLRPC.Message r8 = r29;
        Long r12 = 0L;
        if (r28 != null) goto L168;
        int r15 = r8.id;
        ArrayList r2 = new ArrayList();
        if (r8.date != 2147483646) goto L7;
        boolean r3 = true;
    L9:
        if ((r30 instanceof TLRPC.TL_updateShortSentMessage) == false) goto L35;
        TLRPC.TL_updateShortSentMessage r0 = (TLRPC.TL_updateShortSentMessage) r30;
        long r17 = 0;
        ArrayList r10 = r2;
        updateMediaPaths(r31, null, r0.id, null, false, r32);
        int r02 = r31.getMediaExistanceFlags();
        int r1 = r0.id;
        r8.id = r1;
        r8.local_id = r1;
        r8.date = r0.date;
        r8.entities = r0.entities;
        r8.out = r0.out;
        if ((r0.flags & 33554432) == 0) goto L13;
        r8.ttl_period = r0.ttl_period;
        r8.flags |= 33554432;
    L13:
        TLRPC.MessageMedia r13 = r0.media;
        if (r13 == null) goto L16;
        r8.media = r13;
        r8.flags |= 512;
        ImageLoader.saveMessageThumbs(r8);
    L16:
        TLRPC.MessageMedia r14 = r0.media;
        if ((r14 instanceof TLRPC.TL_messageMediaGame) == true) goto L21;
        if ((r14 instanceof TLRPC.TL_messageMediaInvoice) == true) goto L21;
    L24:
        if (r8.entities.isEmpty() == true) goto L26;
        r8.flags |= 128;
    L26:
        r31.updateMessageText();
        r31.generateCaption();
        r31.resetLayout();
        Integer r16 = getMessagesController().dialogs_read_outbox_max.get(Long.valueOf(r8.dialog_id));
        if (r16 != null) goto L29;
        boolean r19 = true;
        r16 = Integer.valueOf(getMessagesStorage().getDialogReadMax(r8.out, r8.dialog_id));
        getMessagesController().dialogs_read_outbox_max.put(Long.valueOf(r8.dialog_id), r16);
    L31:
        if (r16.intValue() >= r8.id) goto L33;
        boolean r18 = r19;
    L34:
        r8.unread = r18;
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda127(this, r0));
        r10.add(r8);
        SendMessagesHelper r110 = this;
        MessageObject r11 = r31;
        Long r22 = r12;
        int r302 = r15;
        boolean r4 = false;
        boolean r132 = false;
        int r122 = r02;
    L155:
        if (MessageObject.isLiveLocationMessage(r8) == true) goto L157;
    L161:
        if (r132 == true) goto L166;
        r110.getStatsController().incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(), 1, 1);
        r8.send_state = 0;
        long r23 = r17;
        r8.errorNewPriceStars = r23;
        r8.errorAllowedPriceStars = r23;
        if (r27 == r4) goto L165;
        ArrayList r6 = new ArrayList();
        r6.add(Integer.valueOf(r302));
        ArrayList r7 = new ArrayList();
        r7.add(new MessageObject(r11.currentAccount, r11.messageOwner, true, true));
        r110.getMessagesStorage().getStorageQueue().postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda132(r110, r10, r27, r4, r8, r6, r7, r302));
        SendMessagesHelper r111 = this;
        r8 = r8;
        boolean r24 = r27;
    L167:
        TLObject r42 = r34;
        SendMessagesHelper r112 = r111;
        ?? r25 = r24;
    L169:
        if (r132 == false) goto L194;
        r112.getMessagesStorage().markMessageAsSendError(r8, r25);
        r8.send_state = 2;
        if (r28 == null) goto L177;
        String r03 = r28.text;
        if (r03 == null) goto L177;
        if (r03.startsWith("ALLOW_PAYMENT_REQUIRED_") == false) goto L177;
        StarsController.getInstance(r112.currentAccount);
        r8.errorAllowedPriceStars = StarsController.getAllowedPaidStars(r42);
        r8.errorNewPriceStars = Long.parseLong(r28.text.substring(23));
        StarsController.getInstance(r112.currentAccount).showPriceChangedToast(Arrays.asList(new MessageObject[]{r11}));
        r112.getMessagesStorage().updateMessageCustomParams(MessageObject.getDialogId(r8), r8);
    L177:
        r112.getNotificationCenter().postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(r8.id)});
        r112.processSentMessage(r8.id);
        r112.removeFromSendingMessages(r8.id, r25);
        return;
    L194:
        return;
    L165:
        int r82 = r302;
        Long r43 = r22;
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(r82), Integer.valueOf(r8.id), r8, Long.valueOf(r8.dialog_id), r43, Integer.valueOf(r122), Boolean.valueOf(r27)});
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer2, new Object[]{Integer.valueOf(r82), Integer.valueOf(r8.id), r8, Long.valueOf(r8.dialog_id), r43, Integer.valueOf(r122), Boolean.valueOf(r27)});
        DispatchQueue r72 = getMessagesStorage().getStorageQueue();
        SendMessagesHelper r113 = this;
        boolean r26 = r27;
        SendMessagesHelper$$ExternalSyntheticLambda133 r04 = new SendMessagesHelper$$ExternalSyntheticLambda133(r113, r26, r29, r82, r10, r122);
        r8 = r29;
        r72.postRunnable(r04);
        r111 = r113;
        r24 = r26;
        goto L167
    L166:
        r24 = r27;
        r111 = r110;
        goto L167
    L157:
        if (r8.via_bot_id != r17) goto L161;
        if (TextUtils.isEmpty(r8.via_bot_name) == false) goto L161;
        r110.getLocationController().addSharingLocation(r8);
        goto L161
    L33:
        r18 = false;
        goto L34
    L29:
        r19 = true;
    L21:
        if (TextUtils.isEmpty(r0.message) == true) goto L24;
        r8.message = r0.message;
        goto L24
    L35:
        r17 = 0;
        r10 = r2;
        if ((r30 instanceof TLRPC.Updates) == false) goto L153;
        TLRPC.Updates r114 = (TLRPC.Updates) r30;
        ArrayList<TLRPC.Update> r05 = r114.updates;
        ArrayList<TL_ephemeral.EphemeralMessage> r62 = new ArrayList();
        boolean r142 = r27;
        LongSparseArray<SparseArray<TLRPC.MessageReplies>> r44 = null;
        int r133 = 0;
        TLRPC.Message r20 = null;
    L39:
        if (r133 >= r05.size()) goto L114;
        TLRPC.Update r115 = r05.get(r133);
        boolean r21 = r3;
        if ((r115 instanceof TL_update.TL_updateNewMessage) == false) goto L47;
        TL_update.TL_updateNewMessage r116 = (TL_update.TL_updateNewMessage) r115;
        TLRPC.Message r35 = r116.message;
        Long r222 = r12;
        if (r35.action != null) goto L46;
        r10.add(r35);
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda128(this, r116));
        r05.remove(r133);
        r133 = r133 - 1;
        r20 = r35;
    L46:
        TLRPC.Updates r232 = r114;
        int r303 = r15;
    L113:
        r133 = r133 + 1;
        r15 = r303;
        r3 = r21;
        r12 = r222;
        r114 = r232;
        goto L39
    L47:
        r222 = r12;
        if ((r115 instanceof TL_update.TL_updateNewEphemeralMessage) == false) goto L51;
        TL_update.TL_updateNewEphemeralMessage r117 = (TL_update.TL_updateNewEphemeralMessage) r115;
        TLRPC.TL_message r36 = EphemeralMessagesHelper.convertEphemeralToFakeDefault(r117.message);
        r10.add(r36);
        r62.add(r117.message);
        r05.remove(r133);
        r133 = r133 - 1;
        r20 = r36;
        goto L46
    L51:
        if ((r115 instanceof TL_update.TL_updateNewChannelMessage) == false) goto L86;
        TL_update.TL_updateNewChannelMessage r118 = (TL_update.TL_updateNewChannelMessage) r115;
        long r143 = MessagesController.getUpdateChannelId(r118);
        r303 = r15;
        TLRPC.Chat r37 = getMessagesController().getChat(Long.valueOf(r143));
        if (r37 != null) goto L55;
    L58:
        TLRPC.MessageReplyHeader r38 = r118.message.reply_to;
        if (r38 != null) goto L61;
    L57:
        r232 = r114;
    L81:
        TLRPC.Message r39 = r118.message;
        r10.add(r39);
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda129(this, r118));
        r05.remove(r133);
        r133 = r133 - 1;
        if (r118.message.pinned == false) goto L84;
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda130(this, r118, r143));
    L84:
        r20 = r39;
    L85:
        r142 = false;
        goto L113
    L61:
        if (r38.reply_to_top_id == 0) goto L63;
    L64:
        if (r44 != null) goto L66;
        r44 = new LongSparseArray();
    L66:
        r232 = r114;
        long r119 = MessageObject.getDialogId(r118.message);
        SparseArray<TLRPC.MessageReplies> r310 = r44.get(r119);
        if (r310 != null) goto L69;
        r310 = new SparseArray();
        r44.put(r119, r310);
    L69:
        TLRPC.MessageReplyHeader r1110 = r118.message.reply_to;
        int r123 = r1110.reply_to_top_id;
        if (r123 != 0) goto L73;
        r123 = r1110.reply_to_msg_id;
    L73:
        TLRPC.MessageReplies r1111 = r310.get(r123);
        if (r1111 != null) goto L76;
        r1111 = new TLRPC.TL_messageReplies();
        r310.put(r123, r1111);
    L76:
        TLRPC.Peer r311 = r118.message.from_id;
        if (r311 == null) goto L79;
        LongSparseArray<SparseArray<TLRPC.MessageReplies>> r202 = r44;
        r1111.recent_repliers.add(0, r311);
    L80:
        r1111.replies++;
        r44 = r202;
        goto L81
    L79:
        r202 = r44;
        goto L80
    L63:
        if (r38.reply_to_msg_id == 0) goto L57;
    L55:
        if (r37.megagroup == false) goto L57;
    L86:
        r232 = r114;
        r303 = r15;
        if ((r115 instanceof TL_update.TL_updateNewScheduledMessage) == false) goto L97;
        TL_update.TL_updateNewScheduledMessage r120 = (TL_update.TL_updateNewScheduledMessage) r115;
        int r312 = 0;
    L90:
        if (r312 >= r10.size()) goto L95;
        if (((TLRPC.Message) r10.get(r312)).id == r120.message.id) goto L93;
        r312 = r312 + 1;
        goto L90
    L93:
        r10.remove(r312);
    L95:
        TLRPC.Message r121 = r120.message;
        r10.add(r121);
        r05.remove(r133);
        r133 = r133 - 1;
        r20 = r121;
        r142 = true;
        goto L113
    L97:
        if ((r115 instanceof TL_update.TL_updateQuickReplyMessage) == false) goto L100;
        QuickRepliesController.getInstance(this.currentAccount).processUpdate(r115, r31.getQuickReplyName(), r31.getQuickReplyId());
        TLRPC.Message r124 = ((TL_update.TL_updateQuickReplyMessage) r115).message;
        r10.add(r124);
        r05.remove(r133);
        r133 = r133 - 1;
        r20 = r124;
        goto L113
    L100:
        if ((r115 instanceof TL_update.TL_updateDeleteScheduledMessages) == false) goto L113;
        TL_update.TL_updateDeleteScheduledMessages r125 = (TL_update.TL_updateDeleteScheduledMessages) r115;
        if (r31.getDialogId() != DialogObject.getPeerDialogId(r125.peer)) goto L113;
        ArrayList<Integer> r126 = r125.messages;
        int r313 = r126.size();
        int r1112 = 0;
    L104:
        if (r1112 >= r313) goto L112;
        Integer r127 = r126.get(r1112);
        r1112 = r1112 + 1;
        int r128 = r127.intValue();
        int r144 = 0;
    L107:
        if (r144 >= r10.size()) goto L104;
        if (((TLRPC.Message) r10.get(r144)).id == r128) goto L110;
        r144 = r144 + 1;
        goto L107
    L110:
        r10.remove(r144);
        goto L104
    L112:
        r05.remove(r133);
        r133 = r133 - 1;
        goto L85
    L114:
        boolean r212 = r3;
        TLRPC.Updates r233 = r114;
        r22 = r12;
        r302 = r15;
        if (r62.isEmpty() == true) goto L117;
        boolean r314 = true;
        getMessagesStorage().putEphemeralMessages(r62, true);
    L118:
        if (r44 == null) goto L120;
        getMessagesStorage().putChannelViews(null, null, r44, r314);
        getNotificationCenter().postNotificationName(NotificationCenter.didUpdateMessagesViews, new Object[]{null, null, r44, Boolean.TRUE});
    L120:
        TLRPC.Message r129 = r20;
        if (r129 == null) goto L144;
        MessageObject.getDialogId(r129);
        if (r212 == true) goto L125;
    L127:
        ImageLoader.saveMessageThumbs(r129);
        if (r142 == true) goto L137;
        Integer r06 = getMessagesController().dialogs_read_outbox_max.get(Long.valueOf(r129.dialog_id));
        if (r06 != null) goto L133;
        r06 = Integer.valueOf(getMessagesStorage().getDialogReadMax(r129.out, r129.dialog_id));
        getMessagesController().dialogs_read_outbox_max.put(Long.valueOf(r129.dialog_id), r06);
    L133:
        if (r06.intValue() >= r129.id) goto L135;
        boolean r07 = true;
    L136:
        r129.unread = r07;
        goto L137
    L135:
        r07 = false;
    L137:
        TLRPC.Message r08 = r31.messageOwner;
        r08.post_author = r129.post_author;
        if ((r129.flags & 33554432) == 0) goto L140;
        r08.ttl_period = r129.ttl_period;
        r08.flags |= 33554432;
    L140:
        r08.entities = r129.entities;
        int r315 = r129.quick_reply_shortcut_id;
        r08.quick_reply_shortcut_id = r315;
        if (r315 == 0) goto L143;
        r08.flags |= TLObject.FLAG_30;
    L143:
        updateMediaPaths(r31, r129, r129.id, r33, false, r32);
        r11 = r31;
        SendMessagesHelper r130 = this;
        r11.updateMessageText();
        r11.generateCaption();
        r11.resetLayout();
        int r09 = r11.getMediaExistanceFlags();
        r8.id = r129.id;
        boolean r210 = false;
    L152:
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda131(r130, r233));
        r122 = r09;
        r132 = r210;
        r4 = r142;
        r110 = r130;
        goto L155
    L125:
        if (r129.date == 2147483646) goto L127;
        r142 = false;
        goto L127
    L144:
        r130 = this;
        r11 = r31;
        if (BuildVars.LOGS_ENABLED == false) goto L151;
        StringBuilder r211 = new StringBuilder();
        int r316 = 0;
    L148:
        if (r316 >= r05.size()) goto L150;
        r211.append(r05.get(r316).getClass().getSimpleName());
        r211.append(", ");
        r316 = r316 + 1;
        goto L148
    L150:
        FileLog.d("can't find message in updates " + r211);
    L151:
        r09 = 0;
        r210 = true;
        goto L152
    L117:
        r314 = true;
        goto L118
    L153:
        r110 = this;
        r11 = r31;
        r22 = r12;
        r302 = r15;
        r4 = r27;
        r122 = 0;
        r132 = false;
        goto L155
    L7:
        r3 = false;
        goto L9
    L168:
        SendMessagesHelper r131 = this;
        r11 = r31;
        r25 = r27;
        r42 = r34;
        AlertsCreator.processError(r131.currentAccount, r28, null, r42, new Object[0]);
        r132 = true;
        r112 = r131;
        goto L169
    }

    private /* synthetic */ void lambda$performSendMessageRequest$101(TLObject r4, MessageObject r5, String r6, DelayedMessage r7, boolean r8, DelayedMessage r9, Object r10, HashMap r11, boolean r12, TLRPC.Message r13, TLObject r14, TLRPC.TL_error r15) {
        if (r15 != null) goto L4;
    L12:
        SendMessagesHelper r42 = this;
        HashMap r3 = r11;
        boolean r112 = r8;
        MessageObject r82 = r5;
        boolean r52 = r12;
        TLObject r122 = r4;
        Object r0 = r10;
        DelayedMessage r102 = r9;
        String r92 = r6;
        DelayedMessage r62 = r7;
    L29:
        if (r15 != null) goto L31;
    L45:
        HashMap r103 = r3;
        TLRPC.Message r63 = r13;
        TLObject r72 = r14;
        TLRPC.TL_error r1 = r15;
    L47:
        if ((r122 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L51;
        boolean r113 = r52;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda6(r42, r1, r63, r72, r82, r92, r103, r113, r122));
        return;
    L51:
        if ((r122 instanceof TLRPC.TL_messages_editMessage) == false) goto L54;
        boolean r114 = r52;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda7(r42, r1, r63, r72, r82, r92, r103, r114, r122));
        return;
    L54:
        String r115 = r92;
        MessageObject r93 = r82;
        TLObject r83 = r72;
        TLRPC.Message r73 = r63;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda8(r42, r52, r1, r73, r83, r93, r103, r115, r122));
        return;
    L31:
        if ((r122 instanceof TLRPC.TL_messages_sendMedia) == false) goto L45;
        TLRPC.TL_messages_sendMedia r132 = (TLRPC.TL_messages_sendMedia) r122;
        if ((r132.media instanceof TLRPC.TL_inputMediaStakeDice) == false) goto L45;
        if ("GAME_HASH_INVALID".equalsIgnoreCase(r15.text) == false) goto L39;
        MessageObject r133 = r82;
        DelayedMessage r84 = r62;
        String r74 = r92;
        boolean r94 = r112;
        Object r116 = r0;
        r42.getConnectionsManager().sendRequestTyped(new TLRPC.TL_messages_getEmojiGameInfo(), new AiTonesController$$ExternalSyntheticLambda0(), new SendMessagesHelper$$ExternalSyntheticLambda4(r42, r122, r133, r74, r84, r94, r102, r116, r3, r52));
        return;
    L39:
        if ("BALANCE_TOO_LOW".equalsIgnoreCase(r15.text) == false) goto L45;
        TLRPC.TL_inputMediaStakeDice r134 = (TLRPC.TL_inputMediaStakeDice) r132.media;
        r1 = r15;
        boolean r152 = r52;
        BaseFragment r53 = LaunchActivity.getSafeLastFragment();
        if (r53 == null) goto L44;
        TLObject r2 = r122;
        DelayedMessage r123 = r102;
        DelayedMessage r104 = r62;
        Object r135 = r0;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda5(r42, r53, r134, r2, r82, r92, r104, r112, r123, r135, r3, r152));
        return;
    L44:
        r103 = r3;
        r63 = r13;
        r72 = r14;
        r52 = r152;
        goto L47
    L4:
        if ((r4 instanceof TLRPC.TL_messages_sendMedia) == true) goto L15;
        if ((r4 instanceof TL_ephemeral.TL_sendMessage) == true) goto L15;
        if ((r4 instanceof TLRPC.TL_messages_editMessage) == true) goto L15;
        if ((r4 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L12;
    L15:
        if (FileRefController.isFileRefError(r15.text) == false) goto L12;
        if (FileRefController.isFileRefErrorCover(r15.text) == true) goto L19;
        r42 = this;
        r3 = r11;
        r112 = r8;
        r82 = r5;
        r52 = r12;
        r122 = r4;
        r0 = r10;
        r102 = r9;
        r92 = r6;
        r62 = r7;
        if (r0 == null) goto L26;
        r42.getFileRefController().requestReference(r0, new Object[]{r122, r82, r92, r62, Boolean.valueOf(r112), r102, Boolean.valueOf(r52)});
        return;
    L26:
        if (r102 == null) goto L29;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda3(r42, r13, r52, r122, r102));
        return;
    L19:
        if (removeCoverFromRequest(r4) == false) goto L12;
        performSendMessageRequest(r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$102(TLRPC.Message r2, int r3) {
        r2.send_state = 0;
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByAck, new Object[]{Integer.valueOf(r3)});
    }

    private /* synthetic */ void lambda$performSendMessageRequest$103(TLRPC.Message r3) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda116(this, r3, r3.id));
    }

    private /* synthetic */ void lambda$performSendMessageRequest$75(TLObject r11, TLRPC.TL_messages_addPollAnswer r12, TLObject r13, MessageObject r14, String r15, DelayedMessage r16, boolean r17, DelayedMessage r18, Object r19, HashMap r20, boolean r21) {
        if ((r11 instanceof TLRPC.TL_messageMediaDocument) == false) goto L7;
        TLRPC.Document r112 = ((TLRPC.TL_messageMediaDocument) r11).document;
        TLRPC.TL_inputDocument r0 = new TLRPC.TL_inputDocument();
        r0.id = r112.id;
        r0.access_hash = r112.access_hash;
        r0.file_reference = r112.file_reference;
        TLRPC.TL_inputMediaDocument r113 = new TLRPC.TL_inputMediaDocument();
        r113.id = r0;
        r12.answer.input_media = r113;
        performSendMessageRequest(r13, r14, r15, r16, r17, r18, r19, r20, r21);
        return;
    L7:
        if ((r11 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L10;
        TLRPC.Photo r114 = ((TLRPC.TL_messageMediaPhoto) r11).photo;
        TLRPC.TL_inputPhoto r02 = new TLRPC.TL_inputPhoto();
        r02.id = r114.id;
        r02.access_hash = r114.access_hash;
        r02.file_reference = r114.file_reference;
        TLRPC.TL_inputMediaPhoto r115 = new TLRPC.TL_inputMediaPhoto();
        r115.id = r02;
        r12.answer.input_media = r115;
        performSendMessageRequest(r13, r14, r15, r16, r17, r18, r19, r20, r21);
        return;
    L10:
        if (r18 == null) goto L13;
        r18.markAsError();
        return;
    }

    private /* synthetic */ void lambda$performSendMessageRequest$76(TLRPC.TL_messages_addPollAnswer r1, TLObject r2, MessageObject r3, String r4, DelayedMessage r5, boolean r6, DelayedMessage r7, Object r8, HashMap r9, boolean r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda18(this, r11, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10));
    }

    private /* synthetic */ void lambda$performSendMessageRequest$77(TLObject r1, MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9) {
        performSendMessageRequest(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$78(TLObject r1, MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9) {
        performSendMessageRequest(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$79(MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9, TLObject r10) {
        performSendMessageRequest(r10, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$80(TLRPC.Message r2, boolean r3, TLObject r4, DelayedMessage r5) {
        removeFromSendingMessages(r2.id, r3);
        if ((r4 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L11;
        TLRPC.PollAnswer r22 = ((TLRPC.TL_messages_addPollAnswer) r4).answer;
        TLRPC.InputMedia r32 = r22.input_media;
        if ((r32 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L8;
        r22.input_media = r5.inputUploadMedia;
    L42:
        r5.performMediaUpload = true;
        performSendDelayedMessage(r5);
        return;
    L8:
        if ((r32 instanceof TLRPC.TL_inputMediaDocument) == false) goto L42;
        r22.input_media = r5.inputUploadMedia;
        goto L42
    L11:
        if ((r4 instanceof TLRPC.TL_messages_sendMedia) == false) goto L19;
        TLRPC.TL_messages_sendMedia r42 = (TLRPC.TL_messages_sendMedia) r4;
        TLRPC.InputMedia r23 = r42.media;
        if ((r23 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L16;
        r42.media = r5.inputUploadMedia;
        goto L42
    L16:
        if ((r23 instanceof TLRPC.TL_inputMediaDocument) == false) goto L42;
        r42.media = r5.inputUploadMedia;
        goto L42
    L19:
        if ((r4 instanceof TL_ephemeral.TL_sendMessage) == false) goto L35;
        TLObject r24 = r5.sendRequest;
        if ((r24 instanceof TLRPC.TL_messages_sendMedia) == false) goto L28;
        TLRPC.TL_messages_sendMedia r25 = (TLRPC.TL_messages_sendMedia) r24;
        TLRPC.InputMedia r33 = r25.media;
        if ((r33 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L26;
        r25.media = r5.inputUploadMedia;
        goto L28
    L26:
        if ((r33 instanceof TLRPC.TL_inputMediaDocument) == false) goto L28;
        r25.media = r5.inputUploadMedia;
    L28:
        TL_ephemeral.TL_sendMessage r43 = (TL_ephemeral.TL_sendMessage) r4;
        TLRPC.InputMedia r26 = r43.media;
        if ((r26 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L32;
        r43.media = r5.inputUploadMedia;
        goto L42
    L32:
        if ((r26 instanceof TLRPC.TL_inputMediaDocument) == false) goto L42;
        r43.media = r5.inputUploadMedia;
        goto L42
    L35:
        if ((r4 instanceof TLRPC.TL_messages_editMessage) == false) goto L42;
        TLRPC.TL_messages_editMessage r44 = (TLRPC.TL_messages_editMessage) r4;
        TLRPC.InputMedia r27 = r44.media;
        if ((r27 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L40;
        r44.media = r5.inputUploadMedia;
        goto L42
    L40:
        if ((r27 instanceof TLRPC.TL_inputMediaDocument) == false) goto L42;
        r44.media = r5.inputUploadMedia;
        goto L42
    }

    private /* synthetic */ void lambda$performSendMessageRequest$81(TLObject r2, MessageObject r3, String r4, DelayedMessage r5, boolean r6, DelayedMessage r7, Object r8, HashMap r9, boolean r10, TLRPC.EmojiGameInfo r11, TLRPC.TL_error r12) {
        if ((r11 instanceof TLRPC.TL_emojiGameDiceInfo) == false) goto L9;
        String r112 = ((TLRPC.TL_emojiGameDiceInfo) r11).game_hash;
        TLRPC.TL_messages_sendMedia r22 = (TLRPC.TL_messages_sendMedia) r2;
        TLRPC.InputMedia r122 = r22.media;
        if ((r122 instanceof TLRPC.TL_inputMediaStakeDice) == false) goto L7;
        ((TLRPC.TL_inputMediaStakeDice) r122).game_hash = r112;
    L7:
        performSendMessageRequest(r22, r3, r4, r5, r6, r7, r8, r9, r10);
        return;
    }

    private /* synthetic */ void lambda$performSendMessageRequest$82(TLObject r1, MessageObject r2, String r3, DelayedMessage r4, boolean r5, DelayedMessage r6, Object r7, HashMap r8, boolean r9) {
        performSendMessageRequest(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$83(BaseFragment r16, TLRPC.TL_inputMediaStakeDice r17, TLObject r18, MessageObject r19, String r20, DelayedMessage r21, boolean r22, DelayedMessage r23, Object r24, HashMap r25, boolean r26) {
        new TONIntroActivity.StarsNeededSheet(r16.getContext(), r16.getResourceProvider(), AmountUtils$Amount.fromNano(r17.ton_amount, AmountUtils$Currency.TON), false, new SendMessagesHelper$$ExternalSyntheticLambda135(this, r18, r19, r20, r21, r22, r23, r24, r25, r26)).show();
        ArrayList<MessageObject> r0 = new ArrayList();
        r0.add(r19);
        cancelSendingMessage(r0);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$84(TLRPC.Message r2, boolean r3) {
        processSentMessage(r2.id);
        removeFromSendingMessages(r2.id, r3);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$85(TLRPC.Updates r3, TLRPC.Message r4, boolean r5) {
        getMessagesController().processUpdates(r3, false);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda27(this, r4, r5));
    }

    private /* synthetic */ void lambda$performSendMessageRequest$86(TLRPC.TL_error r13, TLRPC.Message r14, TLObject r15, MessageObject r16, String r17, HashMap r18, boolean r19, TLObject r20) {
        if (r13 != null) goto L6;
        String r132 = r14.attachPath;
        TLRPC.Updates r133 = (TLRPC.Updates) r15;
        ArrayList<TLRPC.Update> r2 = r133.updates;
        r14.send_state = 0;
        ArrayList<TLRPC.Message> r4 = new ArrayList();
        r4.add(r14);
        getMessagesStorage().putMessages(r4, false, true, false, 0, 0, 0);
        getMessagesController().getTopicsController().processEditedMessage(r14);
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda90(this, r133, r14, r19));
        return;
    L6:
        AlertsCreator.processError(this.currentAccount, r13, null, r20, new Object[0]);
        removeFromSendingMessages(r14.id, r19);
        revertEditingMessageObject(r16);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$87(TLRPC.Message r2, boolean r3) {
        processSentMessage(r2.id);
        removeFromSendingMessages(r2.id, r3);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$88(TLRPC.Updates r3, TLRPC.Message r4, boolean r5) {
        getMessagesController().processUpdates(r3, false);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda72(this, r4, r5));
    }

    private /* synthetic */ void lambda$performSendMessageRequest$89(TLRPC.TL_error r10, TLRPC.Message r11, TLObject r12, MessageObject r13, String r14, HashMap r15, boolean r16, TLObject r17) {
        int r2 = 0;
        TLRPC.Message r3 = null;
        if (r10 != null) goto L25;
        String r1 = r11.attachPath;
        TLRPC.Updates r8 = (TLRPC.Updates) r12;
        ArrayList<TLRPC.Update> r18 = r8.updates;
    L6:
        if (r2 >= r18.size()) goto L10;
        TLRPC.Update r4 = r18.get(r2);
        if ((r4 instanceof TL_update.TL_updateEditMessage) == true) goto L9;
        if ((r4 instanceof TL_update.TL_updateEditChannelMessage) == true) goto L13;
        if ((r4 instanceof TL_update.TL_updateNewScheduledMessage) == true) goto L16;
        if ((r4 instanceof TL_update.TL_updateQuickReplyMessage) == true) goto L19;
        r2 = r2 + 1;
        goto L6
    L19:
        QuickRepliesController.getInstance(this.currentAccount).processUpdate(r4, MessageObject.getQuickReplyName(r11), MessageObject.getQuickReplyId(r11));
        r3 = ((TL_update.TL_updateQuickReplyMessage) r4).message;
        goto L10
    L16:
        r3 = ((TL_update.TL_updateNewScheduledMessage) r4).message;
        goto L10
    L13:
        r3 = ((TL_update.TL_updateEditChannelMessage) r4).message;
        goto L10
    L9:
        r3 = ((TL_update.TL_updateEditMessage) r4).message;
    L10:
        TLRPC.Message r22 = r3;
        if (r22 == null) goto L23;
        ImageLoader.saveMessageThumbs(r22);
        updateMediaPaths(r13, r22, r22.id, r14, false, r15);
    L23:
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda102(this, r8, r11, r16));
        return;
    L25:
        AlertsCreator.processError(this.currentAccount, r10, null, r17, new Object[0]);
        removeFromSendingMessages(r11.id, r16);
        revertEditingMessageObject(r13);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$90(TLRPC.TL_updateShortSentMessage r4) {
        getMessagesController().processNewDifferenceParams(-1, r4.pts, r4.date, r4.pts_count);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$91(TL_update.TL_updateNewMessage r3) {
        getMessagesController().processNewDifferenceParams(-1, r3.pts, -1, r3.pts_count);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$92(TL_update.TL_updateNewChannelMessage r5) {
        getMessagesController().processNewChannelDifferenceParams(r5.pts, r5.pts_count, r5.message.peer_id.channel_id);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$93(TL_update.TL_updateNewChannelMessage r10, long r11) {
        ArrayList<Integer> r3 = new ArrayList();
        r3.add(Integer.valueOf(r10.message.id));
        getMessagesStorage().updatePinnedMessages(-r11, r3, true, -1, 0, false, null);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$94(TLRPC.Updates r2) {
        getMessagesController().processUpdates(r2, false);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$95(boolean r19, TLRPC.Message r20, ArrayList r21, boolean r22, ArrayList r23, int r24) {
        if (r19 == false) goto L6;
        if (r20 == null) goto L6;
        int r17 = r20.id;
        boolean r4 = false;
    L7:
        MessagesController r3 = getMessagesController();
        long r7 = r20.dialog_id;
        if (r22 == true) goto L11;
        if (r19 == false) goto L11;
        r4 = true;
    L11:
        r3.deleteMessages(r21, null, null, r7, false, r22 ? 1 : 0, false, 0, null, 0, r4, r17);
        getMessagesController().updateInterfaceWithMessages(r20.dialog_id, r23, r19 ? 1 : 0);
        getMediaDataController().increasePeerRaiting(r20.dialog_id);
        processSentMessage(r24);
        removeFromSendingMessages(r24, r22);
        return;
    L6:
        r4 = false;
        r17 = 0;
        goto L7
    }

    private /* synthetic */ void lambda$performSendMessageRequest$96(ArrayList r19, boolean r20, boolean r21, TLRPC.Message r22, ArrayList r23, ArrayList r24, int r25) {
        getMessagesStorage().putMessages(r19, true, false, false, 0, false, !r20 ? 1 : 0, 0);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda17(this, r21, r22, r23, r20, r24, r25));
    }

    private /* synthetic */ void lambda$performSendMessageRequest$97(long r1, ArrayList r3) {
        getMessagesController().markDialogMessageAsDeleted(r1, r3);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$98(TLRPC.Message r10, int r11, int r12, boolean r13) {
        getMediaDataController().increasePeerRaiting(r10.dialog_id);
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(r11), Integer.valueOf(r10.id), r10, Long.valueOf(r10.dialog_id), 0L, Integer.valueOf(r12), Boolean.valueOf(r13)});
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer2, new Object[]{Integer.valueOf(r11), Integer.valueOf(r10.id), r10, Long.valueOf(r10.dialog_id), 0L, Integer.valueOf(r12), Boolean.valueOf(r13)});
        processSentMessage(r11);
        removeFromSendingMessages(r11, r13);
    }

    private /* synthetic */ void lambda$performSendMessageRequest$99(boolean r22, TLRPC.Message r23, int r24, ArrayList r25, int r26) {
        if (MessageObject.isWelcomeMessage(r23) == false) goto L7;
        int r0 = 9;
    L5:
        int r9 = r0;
    L13:
        getMessagesStorage().updateMessageStateAndId(r23.random_id, MessageObject.getPeerId(r23.peer_id), Integer.valueOf(r24), r23.id, 0, false, r22 ? 1 : 0, r23.quick_reply_shortcut_id);
        getMessagesStorage().putMessages(r25, true, false, false, 0, r9, r23.quick_reply_shortcut_id);
        if (MessageObject.isEphemeralAndNotWelcome(r23) == false) goto L16;
        long r11 = MessageObject.getPeerId(r23.peer_id);
        ArrayList<Integer> r15 = new ArrayList(1);
        r15.add(Integer.valueOf(r23.id));
        getMessagesStorage().markMessagesAsDeleted(r11, r15, false, false, r9, (int) MessageObject.getTopicId(this.currentAccount, r23, 0));
        getMessagesStorage().updateDialogsWithDeletedMessages(r11, -r11, r15, null);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda38(this, r11, r15));
    L16:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda39(this, r23, r24, r26, r22));
        return;
    L7:
        if (r23.quick_reply_shortcut_id == 0) goto L9;
    L12:
        r0 = 5;
        goto L5
    L9:
        if (r23.quick_reply_shortcut != null) goto L12;
        r9 = r22 ? 1 : 0;
        goto L13
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$62(TLObject r1, ArrayList r2, ArrayList r3, ArrayList r4, DelayedMessage r5, boolean r6) {
        performSendMessageRequestMulti(r1, r2, r3, r4, r5, r6);
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$63(TLObject r1, ArrayList r2, ArrayList r3, ArrayList r4, DelayedMessage r5, boolean r6) {
        performSendMessageRequestMulti(r1, r2, r3, r4, r5, r6);
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$64(ArrayList r2, ArrayList r3, ArrayList r4, DelayedMessage r5, boolean r6, TLObject r7) {
        performSendMessageRequestMulti(r7, r2, r3, r4, r5, r6);
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$65(TLObject r9, int r10, DelayedMessage r11, ArrayList r12, boolean r13) {
        int r2 = 0;
        if ((r9 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L25;
        TLRPC.TL_messages_sendMultiMedia r92 = (TLRPC.TL_messages_sendMultiMedia) r9;
        int r0 = r92.multi_media.size();
        int r3 = 0;
        boolean r4 = false;
    L5:
        if (r3 >= r0) goto L48;
        if (r10 < 0) goto L10;
        if (r10 != r3) goto L23;
    L12:
        removeFromSendingMessages(((MessageObject) r12.get(r3)).getId(), r13);
        TLRPC.TL_inputSingleMedia r5 = r92.multi_media.get(r3);
        TLRPC.InputMedia r6 = r5.media;
        if ((r6 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L16;
        r5.media = r11.inputMedias.get(r3);
    L18:
        r11.videoEditedInfo = r11.videoEditedInfos.get(r3);
        r11.httpLocation = r11.httpLocations.get(r3);
        TLRPC.PhotoSize r62 = r11.locations.get(r3);
        r11.photoSize = r62;
        r11.performMediaUpload = true;
        r4 = r4;
        if (r5.media.file == null) goto L21;
        if (r62 != null) goto L21;
    L22:
        performSendDelayedMessage(r11, r3);
    L21:
        r4 = true;
        goto L22
    L16:
        if ((r6 instanceof TLRPC.TL_inputMediaDocument) == false) goto L18;
        r5.media = r11.inputMedias.get(r3);
    L23:
        r3 = r3 + 1;
        r4 = r4;
        goto L5
    L10:
        if (r11.parentObjects.get(r3) != null) goto L12;
    L48:
        if (r4 == false) goto L50;
        return;
    L50:
        if (r2 >= r12.size()) goto L62;
        TLRPC.Message r93 = ((MessageObject) r12.get(r2)).messageOwner;
        getMessagesStorage().markMessageAsSendError(r93, r13 ? 1 : 0);
        r93.send_state = 2;
        r93.errorAllowedPriceStars = 0;
        r93.errorNewPriceStars = 0;
        getNotificationCenter().postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(r93.id)});
        processSentMessage(r93.id);
        removeFromSendingMessages(r93.id, r13);
        r2 = r2 + 1;
        goto L50
    L62:
        return;
    L25:
        if ((r9 instanceof TLRPC.TL_messages_sendMedia) == false) goto L47;
        TLRPC.TL_inputMediaPaidMedia r94 = (TLRPC.TL_inputMediaPaidMedia) ((TLRPC.TL_messages_sendMedia) r9).media;
        int r02 = r94.extended_media.size();
        int r32 = 0;
        r4 = false;
    L27:
        if (r32 >= r02) goto L48;
        if (r10 < 0) goto L32;
        if (r10 != r32) goto L46;
    L34:
        removeFromSendingMessages(((MessageObject) r12.get(r32)).getId(), r13);
        TLRPC.InputMedia r52 = r94.extended_media.get(r32);
        if ((r52 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L39;
        ArrayList<TLRPC.InputMedia> r53 = r94.extended_media;
        TLRPC.InputMedia r63 = r11.inputMedias.get(r32);
        r53.set(r32, r63);
    L37:
        r52 = r63;
    L41:
        r11.videoEditedInfo = r11.videoEditedInfos.get(r32);
        r11.httpLocation = r11.httpLocations.get(r32);
        TLRPC.PhotoSize r64 = r11.locations.get(r32);
        r11.photoSize = r64;
        r11.performMediaUpload = true;
        r4 = r4;
        if (r52.file == null) goto L44;
        if (r64 != null) goto L44;
    L45:
        performSendDelayedMessage(r11, r32);
    L44:
        r4 = true;
        goto L45
    L39:
        if ((r52 instanceof TLRPC.TL_inputMediaDocument) == false) goto L41;
        ArrayList<TLRPC.InputMedia> r54 = r94.extended_media;
        r63 = r11.inputMedias.get(r32);
        r54.set(r32, r63);
    L46:
        r32 = r32 + 1;
        r4 = r4;
        goto L27
    L32:
        if (r11.parentObjects.get(r32) != null) goto L34;
    L47:
        r4 = false;
        goto L48
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$66(TL_update.TL_updateNewMessage r3) {
        getMessagesController().processNewDifferenceParams(-1, r3.pts, -1, r3.pts_count);
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$67(TL_update.TL_updateNewChannelMessage r5) {
        getMessagesController().processNewChannelDifferenceParams(r5.pts, r5.pts_count, r5.message.peer_id.channel_id);
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$68(TL_update.TL_updateNewChannelMessage r10, long r11) {
        ArrayList<Integer> r3 = new ArrayList();
        r3.add(Integer.valueOf(r10.message.id));
        getMessagesStorage().updatePinnedMessages(-r11, r3, true, -1, 0, false, null);
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$69(long r1, ArrayList r3) {
        getMessagesController().markDialogMessageAsDeleted(r1, r3);
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$70(int[] r17, int[] r18, boolean r19, boolean r20, MessageObject r21, SparseArray r22, ArrayList r23, TLRPC.Message r24, int r25, long r26, int r28) {
        int r3 = r17[0] + 1;
        r17[0] = r3;
        if (r3 != r18[0]) goto L17;
        if (r19 == r20) goto L17;
        long r5 = r21.getDialogId();
        if (r20 == true) goto L8;
    L10:
        int r15 = 0;
    L11:
        MessagesController r1 = getMessagesController();
        if (r20 == false) goto L15;
        if (r19 == true) goto L15;
        boolean r14 = true;
    L16:
        TLRPC.Message r0 = r24;
        r1.deleteMessages(r23, null, null, r5, false, r19 ? 1 : 0, false, 0, null, 0, r14, r15);
    L18:
        getMediaDataController().increasePeerRaiting(r0.dialog_id);
        TLRPC.Message r32 = r0;
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(r25), Integer.valueOf(r0.id), r32, Long.valueOf(r0.dialog_id), Long.valueOf(r26), Integer.valueOf(r28), Boolean.valueOf(r20)});
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer2, new Object[]{Integer.valueOf(r25), Integer.valueOf(r32.id), r32, Long.valueOf(r32.dialog_id), Long.valueOf(r26), Integer.valueOf(r28), Boolean.valueOf(r20)});
        processSentMessage(r25);
        removeFromSendingMessages(r25, r19);
        return;
    L15:
        r14 = false;
        goto L16
    L8:
        if (r22.size() <= 1) goto L10;
        r15 = r22.keyAt(0);
    L17:
        r0 = r24;
        goto L18
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$71(boolean r22, TLRPC.Message r23, int r24, ArrayList r25, int[] r26, int[] r27, boolean r28, MessageObject r29, SparseArray r30, ArrayList r31, long r32, int r34) {
        if (MessageObject.isWelcomeMessage(r23) == false) goto L7;
        int r0 = 9;
    L5:
        int r16 = r0;
    L13:
        int r19 = r16;
        getMessagesStorage().updateMessageStateAndId(r23.random_id, MessageObject.getPeerId(r23.peer_id), Integer.valueOf(r24), r23.id, 0, false, r19, r23.quick_reply_shortcut_id);
        getMessagesStorage().putMessages(r25, true, false, false, 0, r19, r23.quick_reply_shortcut_id);
        if (MessageObject.isEphemeralAndNotWelcome(r23) == false) goto L16;
        long r3 = MessageObject.getPeerId(r23.peer_id);
        ArrayList<Integer> r7 = new ArrayList(1);
        r7.add(Integer.valueOf(r23.id));
        getMessagesStorage().markMessagesAsDeleted(r3, r7, false, false, r19, (int) MessageObject.getTopicId(this.currentAccount, r23, 0));
        getMessagesStorage().updateDialogsWithDeletedMessages(r3, -r3, r7, null);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda11(this, r3, r7));
    L16:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda12(this, r26, r27, r28, r22, r29, r30, r31, r23, r24, r32, r34));
        return;
    L7:
        if (r23.quick_reply_shortcut_id == 0) goto L9;
    L12:
        r0 = 5;
        goto L5
    L9:
        if (r23.quick_reply_shortcut != null) goto L12;
        r16 = r22 ? 1 : 0;
        goto L13
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$72(TLRPC.Updates r2) {
        getMessagesController().processUpdates(r2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v2, types: [boolean, int] */
    private /* synthetic */ void lambda$performSendMessageRequestMulti$73(TLRPC.TL_error r31, TLObject r32, boolean r33, ArrayList r34, ArrayList r35, TLObject r36) {
        SendMessagesHelper r0 = this;
        ArrayList r8 = r34;
        TLObject r9 = r36;
        if (r31 != null) goto L107;
        SparseArray r12 = new SparseArray();
        LongSparseArray r13 = new LongSparseArray();
        TLRPC.Updates r14 = (TLRPC.Updates) r32;
        ArrayList<TLRPC.Update> r2 = r14.updates;
        ArrayList<TL_ephemeral.EphemeralMessage> r3 = new ArrayList();
        boolean r16 = r33;
        int r4 = 0;
        LongSparseArray<SparseArray<TLRPC.MessageReplies>> r5 = null;
    L6:
        if (r4 >= r2.size()) goto L66;
        TLRPC.Update r6 = r2.get(r4);
        if ((r6 instanceof TL_update.TL_updateMessageID) == false) goto L12;
        TL_update.TL_updateMessageID r62 = (TL_update.TL_updateMessageID) r6;
        r13.put(r62.random_id, Integer.valueOf(r62.id));
        r2.remove(r4);
    L10:
        r4 = r4 - 1;
        TLRPC.Updates r322 = r14;
    L65:
        r4 = r4 + 1;
        r14 = r322;
        goto L6
    L12:
        if ((r6 instanceof TL_update.TL_updateNewMessage) == false) goto L16;
        TL_update.TL_updateNewMessage r63 = (TL_update.TL_updateNewMessage) r6;
        TLRPC.Message r7 = r63.message;
        r12.put(r7.id, r7);
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda30(r0, r63));
        r2.remove(r4);
        r4 = r4 - 1;
        r322 = r14;
    L14:
        r16 = false;
        goto L65
    L16:
        if ((r6 instanceof TL_update.TL_updateNewEphemeralMessage) == false) goto L19;
        TL_update.TL_updateNewEphemeralMessage r64 = (TL_update.TL_updateNewEphemeralMessage) r6;
        TLRPC.TL_message r72 = EphemeralMessagesHelper.convertEphemeralToFakeDefault(r64.message);
        r12.put(r72.id, r72);
        r3.add(r64.message);
        r2.remove(r4);
        goto L10
    L19:
        if ((r6 instanceof TL_update.TL_updateNewChannelMessage) == false) goto L51;
        TL_update.TL_updateNewChannelMessage r65 = (TL_update.TL_updateNewChannelMessage) r6;
        long r10 = MessagesController.getUpdateChannelId(r65);
        TLRPC.Chat r1 = r0.getMessagesController().getChat(Long.valueOf(r10));
        if (r1 != null) goto L23;
    L26:
        TLRPC.MessageReplyHeader r15 = r65.message.reply_to;
        if (r15 != null) goto L29;
    L25:
        r322 = r14;
    L48:
        TLRPC.Message r17 = r65.message;
        r12.put(r17.id, r17);
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda31(r0, r65));
        r2.remove(r4);
        r4 = r4 - 1;
        if (r65.message.pinned == false) goto L65;
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda32(r0, r65, r10));
        goto L65
    L29:
        if (r15.reply_to_top_id == 0) goto L31;
    L32:
        if (r5 != null) goto L34;
        r5 = new LongSparseArray();
    L34:
        r322 = r14;
        long r142 = MessageObject.getDialogId(r65.message);
        SparseArray<TLRPC.MessageReplies> r18 = r5.get(r142);
        if (r18 != null) goto L37;
        r18 = new SparseArray();
        r5.put(r142, r18);
    L37:
        TLRPC.MessageReplyHeader r73 = r65.message.reply_to;
        int r143 = r73.reply_to_top_id;
        if (r143 != 0) goto L41;
        r143 = r73.reply_to_msg_id;
    L41:
        TLRPC.MessageReplies r74 = r18.get(r143);
        if (r74 != null) goto L44;
        r74 = new TLRPC.TL_messageReplies();
        r18.put(r143, r74);
    L44:
        TLRPC.Peer r19 = r65.message.from_id;
        if (r19 == null) goto L47;
        r74.recent_repliers.add(0, r19);
    L47:
        r74.replies++;
        goto L48
    L31:
        if (r15.reply_to_msg_id == 0) goto L25;
    L23:
        if (r1.megagroup == false) goto L25;
    L51:
        r322 = r14;
        if ((r6 instanceof TL_update.TL_updateNewScheduledMessage) == false) goto L55;
        TLRPC.Message r110 = ((TL_update.TL_updateNewScheduledMessage) r6).message;
        r12.put(r110.id, r110);
        r2.remove(r4);
        r4 = r4 - 1;
        r16 = true;
        goto L65
    L55:
        if ((r6 instanceof TL_update.TL_updateQuickReplyMessage) == false) goto L65;
        QuickRepliesController r111 = QuickRepliesController.getInstance(r0.currentAccount);
        if (r8.isEmpty() == false) goto L59;
        String r75 = null;
        int r152 = 0;
    L61:
        if (r8.isEmpty() == false) goto L63;
        Integer r102 = null;
    L64:
        r111.processUpdate(r6, r75, r102.intValue());
        TLRPC.Message r112 = ((TL_update.TL_updateQuickReplyMessage) r6).message;
        r12.put(r112.id, r112);
        r2.remove(r4);
        r4 = r4 - 1;
        goto L14
    L63:
        r102 = Integer.valueOf(((MessageObject) r8.get(r152)).getQuickReplyId());
        goto L64
    L59:
        r152 = 0;
        r75 = ((MessageObject) r8.get(0)).getQuickReplyName();
        goto L61
    L66:
        TLRPC.Updates r323 = r14;
        if (r3.isEmpty() == true) goto L69;
        int r22 = 1;
        r0.getMessagesStorage().putEphemeralMessages(r3, true);
    L70:
        if (r5 == null) goto L72;
        r0.getMessagesStorage().putChannelViews(null, null, r5, r22);
        r0.getNotificationCenter().postNotificationName(NotificationCenter.didUpdateMessagesViews, new Object[]{null, null, r5, Boolean.TRUE});
    L72:
        int[] r103 = new int[r22];
        r103[0] = 0;
        int[] r144 = {0};
        ArrayList r11 = new ArrayList();
        int r153 = 0;
        SendMessagesHelper r02 = r0;
    L74:
        if (r153 >= r8.size()) goto L105;
        MessageObject r113 = (MessageObject) r8.get(r153);
        String r23 = (String) r35.get(r153);
        TLRPC.Message r37 = r113.messageOwner;
        int r52 = r37.id;
        ArrayList r66 = new ArrayList();
        Integer r42 = (Integer) r13.get(r37.random_id);
        if (r42 == null) goto L104;
        TLRPC.Message r43 = (TLRPC.Message) r12.get(r42.intValue());
        if (r43 == null) goto L104;
        MessageObject.getDialogId(r43);
        r66.add(r43);
        if ((r43.flags & 33554432) == 0) goto L83;
        TLRPC.Message r53 = r113.messageOwner;
        r53.ttl_period = r43.ttl_period;
        r53.flags |= 33554432;
    L83:
        if ((r9 instanceof TLRPC.TL_messages_sendMedia) == false) goto L86;
        int r26 = r52;
        ArrayList r27 = r66;
        TLRPC.Message r82 = r37;
        TLRPC.Message r24 = r43;
        updateMediaPaths((MessageObject) r8.get(0), r24, r43.id, r35, false, -1, r43.params);
        r113 = r113;
    L85:
        int[] r67 = r144;
        int r145 = r113.getMediaExistanceFlags();
        r82.id = r24.id;
        int r03 = r24.quick_reply_shortcut_id;
        r82.quick_reply_shortcut_id = r03;
        if (r03 == 0) goto L90;
        r82.flags |= TLObject.FLAG_30;
    L90:
        int[] r76 = r103;
        SparseArray r104 = r12;
        LongSparseArray r04 = r13;
        long r122 = r24.grouped_id;
        if (r33 == true) goto L102;
        Integer r38 = getMessagesController().dialogs_read_outbox_max.get(Long.valueOf(r24.dialog_id));
        if (r38 != null) goto L95;
        LongSparseArray r28 = r04;
        r38 = Integer.valueOf(getMessagesStorage().getDialogReadMax(r24.out, r24.dialog_id));
        r113 = r113;
        getMessagesController().dialogs_read_outbox_max.put(Long.valueOf(r24.dialog_id), r38);
    L97:
        if (r38.intValue() >= r24.id) goto L99;
        boolean r05 = true;
    L100:
        r24.unread = r05;
    L103:
        r76[0] = r76[0] + 1;
        r11.add(Integer.valueOf(r26));
        getStatsController().incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(), 1, 1);
        r82.send_state = 0;
        r82.errorAllowedPriceStars = 0;
        r82.errorNewPriceStars = 0;
        TLRPC.Message r21 = r82;
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer, new Object[]{Integer.valueOf(r26), Integer.valueOf(r82.id), r21, Long.valueOf(r82.dialog_id), Long.valueOf(r122), Integer.valueOf(r145), Boolean.valueOf(r16)});
        getNotificationCenter().postNotificationName(NotificationCenter.messageReceivedByServer2, new Object[]{Integer.valueOf(r26), Integer.valueOf(r82.id), r21, Long.valueOf(r82.dialog_id), Long.valueOf(r122), Integer.valueOf(r145), Boolean.valueOf(r16)});
        boolean r25 = r16;
        r02 = this;
        getMessagesStorage().getStorageQueue().postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda33(this, r25, r21, r26, r27, r67, r76, r33, r113, r104, r11, r122, r145));
        r153 = r153 + 1;
        r8 = r34;
        r9 = r36;
        r16 = r25;
        r144 = r67;
        r12 = r104;
        r13 = r28;
        r103 = r76;
        goto L74
    L99:
        r05 = false;
        goto L100
    L95:
        r28 = r04;
        goto L97
    L102:
        r28 = r04;
        goto L103
    L86:
        r82 = r37;
        r27 = r66;
        r26 = r52;
        r24 = r43;
        updateMediaPaths(r113, r24, r43.id, r23, false, r43.params);
    L104:
        boolean r83 = r33;
        boolean r105 = true;
    L106:
        Utilities.stageQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda34(r02, r323));
        TLRPC.TL_error r39 = r31;
        TLObject r92 = r36;
        int r154 = 0;
        SendMessagesHelper r06 = r02;
        ?? r84 = r83;
    L108:
        if (r105 == false) goto L121;
        int r114 = r154;
    L111:
        if (r114 >= r34.size()) goto L138;
        MessageObject r29 = (MessageObject) r34.get(r114);
        TLRPC.Message r44 = r29.messageOwner;
        r06.getMessagesStorage().markMessageAsSendError(r44, r84);
        r44.send_state = 2;
        if (r84 != 0) goto L120;
        if (r39 == null) goto L120;
        String r54 = r39.text;
        if (r54 == null) goto L120;
        if (r54.startsWith("ALLOW_PAYMENT_REQUIRED_") == false) goto L120;
        StarsController.getInstance(r06.currentAccount);
        r44.errorAllowedPriceStars = StarsController.getAllowedPaidStars(r92);
        r44.errorNewPriceStars = Long.parseLong(r39.text.substring(23));
        StarsController.getInstance(r06.currentAccount).showPriceChangedToast(Arrays.asList(new MessageObject[]{r29}));
        r06.getMessagesStorage().updateMessageCustomParams(MessageObject.getDialogId(r44), r44);
    L120:
        r06.getNotificationCenter().postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(r44.id)});
        r06.processSentMessage(r44.id);
        r06.removeFromSendingMessages(r44.id, r84);
        r114 = r114 + 1;
        goto L111
    L138:
        return;
    L121:
        return;
    L105:
        r83 = r33;
        r105 = false;
        goto L106
    L69:
        r22 = 1;
        goto L70
    L107:
        r84 = r33;
        r154 = 0;
        r39 = r31;
        r92 = r36;
        AlertsCreator.processError(r0.currentAccount, r39, null, r92, new Object[0]);
        r105 = true;
        r06 = r0;
        goto L108
    }

    private /* synthetic */ void lambda$performSendMessageRequestMulti$74(ArrayList r9, TLObject r10, ArrayList r11, ArrayList r12, DelayedMessage r13, boolean r14, TLObject r15, TLRPC.TL_error r16) {
        if (r16 != null) goto L5;
    L24:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda26(this, r16, r15, r14, r11, r12, r10));
        return;
    L5:
        if (FileRefController.isFileRefError(r16.text) == false) goto L24;
        int r3 = FileRefController.getFileRefErrorIndex(r16.text);
        if (r9 == null) goto L19;
        ArrayList r1 = new ArrayList(r9);
        if (r3 < 0) goto L17;
        int r0 = 0;
    L12:
        if (r0 >= r1.size()) goto L17;
        if (r3 != r0) goto L15;
        Object r2 = r1.get(r0);
    L16:
        r1.set(r0, r2);
        r0 = r0 + 1;
        goto L12
    L15:
        r2 = null;
    L17:
        getFileRefController().requestReference(r1, new Object[]{r10, r11, r12, r1, r13, Boolean.valueOf(r14)});
        return;
    L19:
        if (r13 == null) goto L24;
        if (r13.getRetriedToSend(r3) == true) goto L24;
        r13.setRetriedToSend(r3, true);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda25(this, r10, r3, r13, r11, r14));
    }

    private /* synthetic */ void lambda$prepareImportHistory$105(Uri r7, ArrayList r8, MessagesStorage.LongCallback r9, long r10) {
        if (r10 == 0) goto L6;
        prepareImportHistory(-r10, r7, r8, r9);
        return;
    L6:
        r9.run(0);
    }

    private /* synthetic */ void lambda$prepareImportHistory$109(HashMap r9, long r10, ImportingHistory r12, MessagesStorage.LongCallback r13) {
        this.importingHistoryFiles.putAll(r9);
        this.importingHistoryMap.put(r10, r12);
        getFileLoader().uploadFile(r12.historyPath, false, true, 0, 67108864, true);
        getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{Long.valueOf(r10)});
        r13.run(r10);
        Intent r8 = new Intent(ApplicationLoader.applicationContext, ImportingService.class);
        ApplicationLoader.applicationContext.startService(r8);     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        FileLog.e(th);
    }

    private /* synthetic */ void lambda$prepareImportHistory$110(ArrayList r17, long r18, Uri r20, MessagesStorage.LongCallback r21) {
        if (r17 == null) goto L5;
        ArrayList r1 = r17;
    L6:
        ImportingHistory r5 = new ImportingHistory(this);
        r5.mediaPaths = r1;
        r5.dialogId = r18;
        r5.peer = getMessagesController().getInputPeer(r18);
        HashMap r2 = new HashMap();
        int r7 = r1.size();
        int r9 = 0;
    L8:
        if (r9 >= (r7 + 1)) goto L86;
        if (r9 != 0) goto L11;
        Uri r0 = r20;
    L12:
        if (r0 != null) goto L14;
    L82:
        if (r9 != 0) goto L85;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda120(r21));
        return;
    L85:
        r9 = r9 + 1;
        goto L8
    L14:
        if (AndroidUtilities.isInternalUri(r0) == true) goto L82;
        String r10 = FileLoader.fixFileName(MediaController.getFileName(r20));
        if (r10 != null) goto L19;
    L21:
        String r102 = "txt";
    L22:
        String r13 = MediaController.copyFileToCache(r0, r102);
        if ("zip".equals(r102) == false) goto L63;
        File r103 = new File(r13);
        ZipInputStream r11 = new ZipInputStream(new FileInputStream(r103));     // Catch: Exception -> L47 IOException -> L49
        ZipEntry r02 = r11.getNextEntry();     // Catch: Throwable -> L31
    L27:
        if (r02 == null) goto L44;
        String r03 = r02.getName();     // Catch: Throwable -> L31
        if (r03 == null) goto L30;
        int r14 = r03.lastIndexOf("/");     // Catch: Throwable -> L31
        if (r14 < 0) goto L37;
        r03 = r03.substring(r14 + 1);     // Catch: Throwable -> L31
    L37:
        if (r03.endsWith(".txt") == true) goto L38;
        r02 = r11.getNextEntry();     // Catch: Throwable -> L31
        goto L27
    L38:
        File r04 = MediaController.createFileInCache(r03, "txt");     // Catch: Throwable -> L31
        r13 = r04.getAbsolutePath();     // Catch: Throwable -> L31
        FileOutputStream r12 = new FileOutputStream(r04);     // Catch: Throwable -> L31
        byte[] r05 = new byte[1024];     // Catch: Throwable -> L31
    L39:
        int r142 = r11.read(r05);     // Catch: Throwable -> L31
        if (r142 <= 0) goto L42;
        r12.write(r05, 0, r142);     // Catch: Throwable -> L31
        goto L39
    L42:
        r12.close();     // Catch: Throwable -> L31
        goto L44
    L30:
        r02 = r11.getNextEntry();     // Catch: Throwable -> L31
    L44:
        r11.closeEntry();     // Catch: Throwable -> L31
        r11.close();     // Catch: Exception -> L47 IOException -> L49
    L90:
        r103.delete();     // Catch: Exception -> L61
    L61:
        e = move-exception;
        FileLog.e(e);
    L31:
        th = move-exception;
        r11.close();     // Catch: Throwable -> L53
    L55:
        throw th;     // Catch: Exception -> L47 IOException -> L49
    L53:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L47 IOException -> L49
    L49:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L47
    L47:
        e = move-exception;
        FileLog.e(e);
    L63:
        if (r13 == null) goto L85;
        File r06 = new File(r13);
        if (r06.exists() == false) goto L79;
        long r104 = r06.length();
        if (r104 == 0) goto L79;
        r5.totalSize += r104;
        if (r9 == 0) goto L73;
        r5.uploadMedia.add(r13);
    L78:
        r5.uploadSet.add(r13);
        r2.put(r13, r5);
        goto L85
    L73:
        if (r104 > 33554432) goto L74;
        r5.historyPath = r13;
        goto L78
    L74:
        r06.delete();
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda122(r21));
        return;
    L79:
        if (r9 != 0) goto L85;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda121(r21));
        return;
    L19:
        if (r10.endsWith(".zip") == false) goto L21;
        r102 = "zip";
        goto L22
    L11:
        r0 = (Uri) r1.get(r9 - 1);
        goto L12
    L86:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda123(this, r2, r18, r5, r21));
        return;
    L5:
        r1 = new ArrayList();
        goto L6
    }

    private /* synthetic */ void lambda$prepareImportStickers$112(ImportingStickers r3, HashMap r4, String r5, MessagesStorage.StringCallback r6) {
        if (r3.uploadMedia.get(0).item == null) goto L5;
        ImportingStickers.m4963$$Nest$mstartImport(r3);
    L6:
        Intent r2 = new Intent(ApplicationLoader.applicationContext, ImportingService.class);
        ApplicationLoader.applicationContext.startService(r2);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        FileLog.e(th);
        return;
    L5:
        this.importingStickersFiles.putAll(r4);
        this.importingStickersMap.put(r5, r3);
        ImportingStickers.m4960$$Nest$minitImport(r3);
        getNotificationCenter().postNotificationName(NotificationCenter.historyImportProgressChanged, new Object[]{r5});
        r6.run(r5);
        goto L6
    }

    private /* synthetic */ void lambda$prepareImportStickers$113(String r9, String r10, String r11, ArrayList r12, MessagesStorage.StringCallback r13) {
        ImportingStickers r102 = new ImportingStickers(this);
        r102.title = r9;
        r102.shortName = r10;
        r102.software = r11;
        HashMap r112 = new HashMap();
        int r92 = r12.size();
        int r1 = 0;
    L3:
        if (r1 >= r92) goto L14;
        ImportingSticker r2 = (ImportingSticker) r12.get(r1);
        File r3 = new File(r2.path);
        if (r3.exists() == false) goto L10;
        long r32 = r3.length();
        if (r32 == 0) goto L10;
        r102.totalSize += r32;
        r102.uploadMedia.add(r2);
        r102.uploadSet.put(r2.path, r2);
        r112.put(r2.path, r102);
    L13:
        r1 = r1 + 1;
    L10:
        if (r1 != 0) goto L13;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda77(r13));
        return;
    L14:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda78(this, r102, r112, r10, r13));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v158 */
    /* JADX WARN: Type inference failed for: r0v159, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v172 */
    /* JADX WARN: Type inference failed for: r11v52 */
    /* JADX WARN: Type inference failed for: r11v53 */
    /* JADX WARN: Type inference failed for: r11v55 */
    /* JADX WARN: Type inference failed for: r11v57, types: [org.telegram.messenger.SendMessagesHelper-IA] */
    /* JADX WARN: Type inference failed for: r11v60 */
    /* JADX WARN: Type inference failed for: r11v68 */
    /* JADX WARN: Type inference failed for: r11v75 */
    /* JADX WARN: Type inference failed for: r11v77 */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v32 */
    /* JADX WARN: Type inference failed for: r13v67, types: [boolean] */
    /* JADX WARN: Type inference failed for: r13v72, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r21v18 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r46v13 */
    /* JADX WARN: Type inference failed for: r46v6 */
    /* JADX WARN: Type inference failed for: r46v7 */
    /* JADX WARN: Type inference failed for: r46v8 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v69, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v71 */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v108 */
    /* JADX WARN: Type inference failed for: r7v135 */
    /* JADX WARN: Type inference failed for: r7v136 */
    /* JADX WARN: Type inference failed for: r7v137 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v64 */
    /* JADX WARN: Type inference failed for: r7v65, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r7v70 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r7v90 */
    /* JADX WARN: Type inference failed for: r7v91, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r7v93 */
    /* JADX WARN: Type inference failed for: r7v95 */
    /* JADX WARN: Type inference failed for: r7v96 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v38 */
    /* JADX WARN: Type inference failed for: r8v39, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v40, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v41 */
    /* JADX WARN: Type inference failed for: r8v43 */
    /* JADX WARN: Type inference failed for: r8v44, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v45, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v46 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v50 */
    /* JADX WARN: Type inference failed for: r8v52 */
    /* JADX WARN: Type inference failed for: r8v54 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r9v50, types: [org.telegram.messenger.MediaController$PhotoEntry] */
    private static /* synthetic */ void lambda$prepareSendingMedia$132(ArrayList r87, long r88, boolean r90, boolean r91, boolean r92, AccountInstance r93, long r94, MessageObject r96, TLRPC.TL_inputPollAnswer r97, MessageObject r98, MessageObject r99, boolean r100, int r101, int r102, TL_stories.StoryItem r103, ChatActivity.ReplyQuote r104, SendMessageChatArguments r105, long r106, boolean r108, long r109, long r111, MessageSuggestionParams r113, PollSendParams r114, boolean r115, InputContentInfoCompat r116, boolean r117) {
        ArrayList r1 = r87;
        long r34 = System.currentTimeMillis();
        int r10 = r1.size();
        int r11 = 0;
        int r2 = 0;
    L3:
        String r14 = ".jpeg";
        String r15 = "-2147483648_";
        Uri r3 = null;
        ?? r4 = 1;
        if (r2 >= r10) goto L35;
        SendingMediaInfo r0 = (SendingMediaInfo) r1.get(r2);
        if (r0.path == null) goto L34;
        if (r0.isLivePhoto == false) goto L34;
        if (r0.discardLivePhoto == false) goto L34;
        File r5 = new File(r0.path);
        File r6 = new File(FileLoader.getDirectory(4), "-2147483648_" + SharedConfig.getLastLocalId() + ".jpeg");
        long r7 = r0.livePhotoVideoOffset;
        if (TextUtils.isEmpty(r0.imagePath) == true) goto L17;
        r0.path = r0.imagePath;     // Catch: Exception -> L15
    L31:
        r0.videoEditedInfo = null;     // Catch: Exception -> L15
        r0.isVideo = false;     // Catch: Exception -> L15
        r0.isLivePhoto = false;     // Catch: Exception -> L15
        goto L34
    L17:
        copyRange(r5, 0, r7, r6);     // Catch: Exception -> L15
        r0.path = r6.getAbsolutePath();     // Catch: Exception -> L15
        Pair<Integer, Integer> r52 = AndroidUtilities.getImageOrientation(r6);     // Catch: Exception -> L15
        if (r52 == null) goto L31;
        if (((Integer) r52.first).intValue() == 0) goto L31;
        Bitmap r22 = BitmapFactory.decodeFile(r6.getAbsolutePath());     // Catch: Exception -> L15
        if (r22 == null) goto L31;
        int r62 = ((Integer) r52.second).intValue();     // Catch: Exception -> L15
        Matrix r72 = new Matrix();     // Catch: Exception -> L15
        if (r62 != 1) goto L26;
        float r42 = -1.0f;
    L27:
        if (r62 != 2) goto L29;
        float r63 = -1.0f;
    L30:
        r72.postScale(r42, r63, r22.getWidth() / 2.0f, r22.getHeight() / 2.0f);     // Catch: Exception -> L15
        r72.postRotate(((Integer) r52.first).intValue());     // Catch: Exception -> L15
        Bitmap r43 = Bitmap.createBitmap(r22, 0, 0, r22.getWidth(), r22.getHeight(), r72, true);     // Catch: Exception -> L15
        File r53 = new File(FileLoader.getDirectory(4), "-2147483648_" + SharedConfig.getLastLocalId() + ".jpeg");     // Catch: Exception -> L15
        r43.compress(Bitmap.CompressFormat.JPEG, 97, new FileOutputStream(r53));     // Catch: Exception -> L15
        r0.path = r53.getAbsolutePath();     // Catch: Exception -> L15
        r6.delete();     // Catch: Exception -> L15
        goto L31
    L29:
        r63 = 1.0f;
        goto L30
    L26:
        r42 = 1.0f;
    L15:
        e = move-exception;
        FileLog.e(e);
    L34:
        r2 = r2 + 1;
        goto L3
    L35:
        boolean r19 = DialogObject.isEncryptedDialog(r88);
        String r23 = ".webp";
        String r54 = ".gif";
        String r64 = "_";
        if (r90 == true) goto L156;
        if (r91 == false) goto L156;
        HashMap r02 = new HashMap();
        int r73 = 0;
    L39:
        if (r73 >= r10) goto L155;
        SendingMediaInfo r8 = (SendingMediaInfo) r1.get(r73);
        if (r8.searchImage == null) goto L43;
    L153:
        String r432 = r23;
        String r44 = r54;
        String r45 = r64;
        int r18 = r11;
        ?? r112 = r3;
        boolean r32 = r19;
    L78:
        int r192 = r73;
    L154:
        r73 = r192 + 1;
        r19 = r32;
        r3 = r112;
        r11 = r18;
        r23 = r432;
        r54 = r44;
        r64 = r45;
        r4 = 1;
        goto L39
    L43:
        if (r8.isVideo == true) goto L153;
        if (r8.videoEditedInfo != null) goto L153;
        ?? r9 = r8.originalPhotoEntry;
        if (r9 != 0) goto L49;
    L53:
        String r95 = r8.path;
        if (r95 != null) goto L58;
        Uri r12 = r8.uri;
        if (r12 == null) goto L58;
        r95 = AndroidUtilities.getPath(r12);
        String r122 = r8.uri.toString();
    L59:
        if (r95 != null) goto L61;
    L80:
        r18 = r11;
        int r13 = r18;
    L82:
        if (ImageLoader.shouldSendImageAsDocument(r8.path, r8.uri) == false) goto L84;
        r432 = r23;
        r44 = r54;
        r45 = r64;
        r32 = r19;
        Uri r118 = null;
    L77:
        r112 = r118;
        goto L78
    L84:
        if (r95 != null) goto L92;
        Uri r33 = r8.uri;
        if (r33 == null) goto L92;
        int r132 = r13;
        if (MediaController.isGif(r33) == true) goto L94;
        ?? r133 = MediaController.isWebp(r8.uri);
        r132 = r133;
        if (r133 == 0) goto L92;
    L94:
        if (r1.size() > r4) goto L103;
        if (r132 == 0) goto L99;
        Uri r119 = null;
        Uri r1110 = null;
        if (shouldSendWebPAsSticker(null, r8.uri) == true) goto L100;
    L104:
        r8.forceImage = r4;
        r112 = r119;
    L105:
        if (r95 == null) goto L107;
        File r35 = new File(r95);
        String r36 = r122 + r35.length() + r64 + r35.lastModified();
    L108:
        if (r19 == false) goto L110;
    L148:
        r432 = r23;
        r44 = r54;
        r45 = r64;
        SendingMediaInfo r134 = r8;
        r32 = r19;
        AccountInstance r24 = r93;
        r192 = r73;
        TLRPC.TL_photo r46 = r112;
        ?? r123 = r46;
    L149:
        MediaSendPrepareWorker r55 = new MediaSendPrepareWorker(r112);
        r02.put(r134, r55);
        if (r46 == null) goto L152;
        r55.parentObject = r123;
        r55.photo = r46;
        goto L154
    L152:
        r55.sync = new CountDownLatch(1);
        mediaSendThreadPool.execute(new SendMessagesHelper$$ExternalSyntheticLambda45(r55, r24, r134, r32));
        goto L154
    L110:
        if (r8.ttl != 0) goto L148;
        if (r92 == true) goto L148;
        boolean r910 = r8.highQuality;
        if (r910 == false) goto L117;
        if (r19 == true) goto L116;
        int r124 = 6;
    L121:
        if (r8.discardLivePhoto == false) goto L127;
        if (r910 == false) goto L124;
        int r911 = 6;
    L125:
        r124 = r911;
    L131:
        Object[] r37 = r93.getMessagesStorage().getSentFile(r36, r124);
        if (r37 == null) goto L136;
        Object r912 = r37[r18];
        if ((r912 instanceof TLRPC.TL_photo) == false) goto L136;
        TLRPC.TL_photo r913 = (TLRPC.TL_photo) r912;
        Object r38 = (String) r37[r4];
    L137:
        if (r913 == null) goto L139;
    L146:
        Object r39 = r38;
    L145:
        r123 = r39;
        r46 = r913;
        r432 = r23;
        r44 = r54;
        r45 = r64;
        r32 = r19;
        r24 = r93;
        r192 = r73;
        r134 = r8;
        ensureMediaThumbExists(r24, r32, r46, r8.path, r8.uri, 0, r8.highQuality);
        goto L149
    L139:
        if (r8.uri == null) goto L146;
        boolean r20 = r4;
        Object[] r47 = r93.getMessagesStorage().getSentFile(AndroidUtilities.getPath(r8.uri), r124);
        r39 = r38;
        if (r47 == null) goto L145;
        Object r125 = r47[r18];
        r39 = r38;
        if ((r125 instanceof TLRPC.TL_photo) == false) goto L145;
        r913 = (TLRPC.TL_photo) r125;
        r39 = (String) r47[r20 ? 1 : 0];
    L136:
        TLRPC.TL_photo r310 = r112;
        r913 = r310;
        r38 = r310;
        goto L137
    L124:
        r911 = r18;
        goto L125
    L127:
        if (r8.isLivePhoto == false) goto L131;
        if (r910 == false) goto L130;
        r911 = 8;
        goto L125
    L130:
        r124 = 2;
        goto L131
    L116:
        r124 = 7;
        goto L121
    L117:
        if (r19 == true) goto L119;
        r124 = r18;
        goto L121
    L119:
        r124 = 3;
        goto L121
    L107:
        r36 = r112;
    L100:
        r119 = r1110;
        if (TextUtils.isEmpty(r8.caption) == false) goto L104;
        r432 = r23;
        Uri r1111 = r1110;
    L76:
        r44 = r54;
        r45 = r64;
        r32 = r19;
        r118 = r1111;
        goto L77
    L99:
        r1110 = null;
        goto L100
    L103:
        r119 = null;
    L92:
        r112 = 0;
        goto L105
    L61:
        if (r8.ttl > 0) goto L80;
        if (r95.endsWith(r54) == true) goto L67;
        ?? r135 = r95.endsWith(r23);
        r18 = r11;
        r13 = r135;
        int r136 = r135;
        if (r135 == 0) goto L82;
    L69:
        if (r1.size() > r4) goto L79;
        if (r136 == 0) goto L74;
        if (shouldSendWebPAsSticker(r95, r3) == false) goto L79;
    L74:
        if (TextUtils.isEmpty(r8.caption) == false) goto L79;
        r432 = r23;
        r1111 = r3;
    L79:
        r8.forceImage = r4;
        r112 = r3;
        goto L105
    L67:
        int r137 = r11;
        r18 = r137 == true ? 1 : 0;
        r136 = r137;
    L58:
        r122 = r95;
        goto L59
    L49:
        if (r8.highQuality == false) goto L53;
        r9.rebuildPhoto(r4);
        String r914 = r8.originalPhotoEntry.imagePath;
        if (r914 == null) goto L53;
        r8.path = r914;
        goto L53
    L155:
        String r433 = r23;
        String r442 = r54;
        String r452 = r64;
        int r182 = r11;
        AccountInstance r25 = r93;
        Uri r1112 = r3;
        boolean r311 = r19;
        HashMap r48 = r02;
    L157:
        long r56 = 0;
        long r21 = 0;
        long r472 = 0;
        Uri r74 = r1112;
        ?? r82 = r74;
        ArrayList r915 = r82;
        ArrayList r138 = r915;
        ArrayList r232 = r138;
        ArrayList r462 = r232;
        int r03 = r182;
        int r1113 = r03;
        Uri r75 = r74;
    L158:
        if (r1113 >= r10) goto L809;
        SendingMediaInfo r126 = (SendingMediaInfo) r1.get(r1113);
        long r26 = r21;
        if (r03 != 0) goto L162;
        ?? r212 = 1;
    L164:
        if (r94 == r56) goto L166;
        r26 = r94;
        r472 = r26;
        int r31 = r182;
    L173:
        MediaController.SearchImage r04 = r126.searchImage;
        String r222 = r15;
        Uri r242 = r75;
        Object r332 = r82;
        ArrayList r49 = r915;
        int r50 = r10;
        int r51 = r1113;
        if (r04 != null) goto L176;
    L286:
        HashMap r61 = r48;
        ArrayList r58 = r138;
        String r66 = r14;
        String r67 = r222;
        ArrayList r57 = r232;
        long r71 = r26;
        int r70 = r51;
        long r492 = 0;
        boolean r333 = r311;
        if (r126.isVideo == true) goto L291;
        if (r126.videoEditedInfo != null) goto L291;
    L295:
        String r05 = r126.path;
        if (r05 != null) goto L306;
        Uri r27 = r126.uri;
        if (r27 == null) goto L306;
        if (Build.VERSION.SDK_INT >= 30) goto L302;
    L304:
        String r312 = AndroidUtilities.getPath(r126.uri);
    L305:
        String r28 = r126.uri.toString();
    L307:
        if (r116 != null) goto L309;
    L348:
        String r16 = r28;
        String r1114 = r433;
        String r107 = r67;
    L349:
        if (r90 == false) goto L351;
    L352:
        String r410 = r442;
        if (r312 == null) goto L383;
        String r463 = FileLoader.getFileExtension(new File(r312));
    L366:
        String r17 = r312;
        String r29 = r16;
        ?? r464 = r463;
    L367:
        boolean r06 = true;
        r462 = r464;
    L384:
        if (r06 == false) goto L389;
        if (r242 != null) goto L387;
        ArrayList r76 = new ArrayList();
        r82 = new ArrayList();
        ArrayList r07 = new ArrayList();
        r138 = new ArrayList();
        r915 = new ArrayList();
        ?? r77 = r76;
    L388:
        r77.add(r17);
        r82.add(r29);
        r915.add(r126.uri);
        r07.add(r126.caption);
        r138.add(r126.entities);
        r25 = r93;
        r232 = r07;
        r442 = r410;
        r67 = r107;
        r03 = r31;
        boolean r522 = r333;
        String r732 = r452;
        HashMap r532 = r61;
        int r60 = r50;
        String r453 = r1114;
    L808:
        r1113 = r70 + 1;
        r1 = r87;
        r433 = r453;
        r56 = r492;
        r311 = r522;
        r48 = r532;
        r10 = r60;
        r14 = r66;
        r15 = r67;
        r21 = r71;
        r452 = r732;
        r182 = 0;
        r75 = r77;
        r462 = r462;
        goto L158
    L387:
        r77 = r242;
        r82 = r332;
        r915 = r49;
        r07 = r57;
        r138 = r58;
        goto L388
    L389:
        if (r17 == null) goto L392;
        File r08 = new File(r17);
        StringBuilder r313 = new StringBuilder();
        r313.append(r29);
        r442 = r410;
        r313.append(r08.length());
        String r210 = r452;
        r313.append(r210);
        r313.append(r08.lastModified());
        String r314 = r313.toString();
    L393:
        if (r61 == null) goto L403;
        MediaSendPrepareWorker r59 = (MediaSendPrepareWorker) r61.get(r126);
        TLRPC.TL_photo r09 = r59.photo;
        String r010 = r59.parentObject;
        if (r09 == null) goto L849;
        String r510 = r010;
        TLRPC.TL_photo r011 = r09;
    L402:
        String r183 = r17;
        r732 = r210;
        r532 = r61;
        Object r233 = "1";
        Object r110 = "groupId";
        String r223 = r107;
        String r162 = r1114;
        Object r193 = "final";
        int r152 = 1;
        AccountInstance r211 = r93;
        String r1010 = r314;
        Object r1115 = "originalPath";
        TLRPC.TL_photo r83 = r011;
    L450:
        if (r83 == null) goto L498;
        HashMap r315 = new HashMap();
        Bitmap[] r411 = new Bitmap[r152];
        String[] r65 = new String[r152];
        ArrayList<TLRPC.InputDocument> r012 = r126.masks;
        if (r012 != null) goto L454;
    L456:
        ?? r013 = 0;
    L457:
        r83.has_stickers = r013;
        if (r013 == 0) goto L464;
        SerializedData r014 = new SerializedData((r126.masks.size() * 20) + 4);
        r014.writeInt32(r126.masks.size());
        int r78 = 0;
    L461:
        if (r78 >= r126.masks.size()) goto L463;
        r126.masks.get(r78).serializeToStream(r014);
        r78 = r78 + 1;
        goto L461
    L463:
        r315.put("masks", Utilities.bytesToHex(r014.toByteArray()));
        r014.cleanup();
    L464:
        if (r1010 == null) goto L466;
        r315.put(r1115, r1010);
    L466:
        if (r510 == null) goto L468;
        r315.put("parentObject", r510);
    L468:
        if (r91 == true) goto L857;
        boolean r139 = true;
    L881:
        TLRPC.PhotoSize r015 = FileLoader.getClosestPhotoSizeWithSize(r83.sizes, AndroidUtilities.getPhotoSize(r126.highQuality));     // Catch: Exception -> L480
        if (r015 == null) goto L482;
        r65[0] = getKeyForPhotoSize(r211, r015, r411, false, false);     // Catch: Exception -> L480
    L482:
        if (r91 == false) goto L496;
        r03 = r31 + 1;
        StringBuilder r79 = new StringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        long r916 = r71;
        r79.append(r916);
        r315.put(r110, r79.toString());
        if (r115 == true) goto L495;
        if (r114 == null) goto L487;
    L491:
        r60 = r50;
        int r710 = r70;
        if (r710 == (r60 - 1)) goto L494;
    L497:
        r70 = r710;
        long r742 = r916;
        r453 = r162;
        r67 = r223;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda49(r411, r65, r96, r97, r93, r83, r315, r126, r510, r88, r98, r99, r100, r101, r102, r117, r103, r104, r105, r106, r108, r109, r111, r113, r126.highQuality, r114));
        r25 = r93;
        r77 = r242;
        r82 = r332;
        r915 = r49;
        r232 = r57;
        r138 = r58;
        r71 = r742;
        r522 = r333;
    L494:
        r315.put(r193, r233);
        r472 = 0;
        goto L497
    L487:
        if (r03 != 10) goto L491;
        r60 = r50;
        r710 = r70;
        goto L494
    L495:
        r60 = r50;
        r710 = r70;
        goto L497
    L496:
        r60 = r50;
        r710 = r70;
        r916 = r71;
        r03 = r31;
    L480:
        e = e;
    L481:
        FileLog.e(e);
        goto L482
    L857:
        r139 = true;
        if (r87.size() != 1) goto L482;
    L473:
        e = e;
        r139 = true;
        goto L481
    L454:
        if (r012.isEmpty() == true) goto L456;
        r013 = r152;
        goto L457
    L498:
        r453 = r162;
        r67 = r223;
        r60 = r50;
        if (r242 != null) goto L502;
        ArrayList r711 = new ArrayList();
        r82 = new ArrayList();
        ArrayList r016 = new ArrayList();
        r138 = new ArrayList();
        r915 = new ArrayList();
        r77 = r711;
    L503:
        r77.add(r183);
        r82.add(r1010);
        r915.add(r126.uri);
        r016.add(r126.caption);
        r138.add(r126.entities);
        r25 = r93;
        r232 = r016;
        r03 = r31;
        r522 = r333;
        r71 = r71;
        goto L808
    L502:
        r77 = r242;
        r82 = r332;
        r915 = r49;
        r016 = r57;
        r138 = r58;
        goto L503
    L849:
        r59.sync.await();     // Catch: Exception -> L398
    L400:
        r011 = r59.photo;
        r510 = r59.parentObject;
    L398:
        e = move-exception;
        FileLog.e(e);
        goto L400
    L403:
        MediaController.PhotoEntry r017 = r126.originalPhotoEntry;
        if (r017 != null) goto L406;
    L410:
        int r511 = 1;
    L411:
        if (r333 == false) goto L413;
    L440:
        r183 = r17;
        r732 = r210;
        r532 = r61;
        Object r234 = "1";
        Object r120 = "groupId";
        r223 = r107;
        r162 = r1114;
        Object r194 = "final";
        r211 = r93;
        r1010 = r314;
        r152 = r511;
        Object r1116 = "originalPath";
        TLRPC.TL_photo r316 = null;
        String r172 = null;
    L441:
        if (r316 != null) goto L449;
        TLRPC.TL_photo r018 = r211.getSendMessagesHelper().generatePhotoSizes(null, r126.path, r126.uri, r126.highQuality);
        if (r333 == true) goto L445;
    L447:
        r83 = r018;
    L448:
        r510 = r172;
        r110 = r120;
        r1115 = r1116;
        r193 = r194;
        r233 = r234;
        goto L450
    L445:
        if (r126.canDeleteAfter == false) goto L447;
        new File(r126.path).delete();
        goto L447
    L449:
        r83 = r316;
        goto L448
    L413:
        if (r126.ttl != 0) goto L440;
        if (r92 == true) goto L440;
        int r202 = r511;
        if (r126.highQuality == false) goto L420;
        if (r333 == true) goto L419;
        int r019 = 6;
    L423:
        Object[] r512 = r93.getMessagesStorage().getSentFile(r314, r019);
        String r454 = r210;
        if (r512 == null) goto L428;
        Object r213 = r512[0];
        String r163 = r314;
        if ((r213 instanceof TLRPC.TL_photo) == false) goto L429;
        TLRPC.TL_photo r317 = (TLRPC.TL_photo) r213;
        String r214 = (String) r512[r202];
    L430:
        if (r317 == null) goto L432;
    L438:
        r172 = r214;
    L439:
        r183 = r17;
        r120 = "groupId";
        r234 = "1";
        r532 = r61;
        r223 = r107;
        r1010 = r163;
        r732 = r454;
        r211 = r93;
        TLRPC.TL_photo r412 = r317;
        r162 = r1114;
        r1116 = "originalPath";
        r194 = "final";
        r152 = r202;
        ensureMediaThumbExists(r211, r333, r412, r126.path, r126.uri, 0);
        r316 = r412;
        goto L441
    L432:
        if (r126.uri == null) goto L438;
        r172 = r214;
        Object[] r020 = r93.getMessagesStorage().getSentFile(AndroidUtilities.getPath(r126.uri), r019);
        if (r020 == null) goto L439;
        Object r215 = r020[0];
        if ((r215 instanceof TLRPC.TL_photo) == false) goto L439;
        r317 = (TLRPC.TL_photo) r215;
        r172 = (String) r020[r202];
    L429:
        r214 = null;
        r317 = null;
        goto L430
    L428:
        r163 = r314;
        goto L429
    L419:
        r019 = 7;
        goto L423
    L420:
        if (r333 == true) goto L422;
        r019 = 0;
        goto L423
    L422:
        r019 = 3;
        goto L423
    L406:
        if (r126.highQuality == false) goto L410;
        r511 = 1;
        r017.rebuildPhoto(true);
        String r021 = r126.originalPhotoEntry.imagePath;
        if (r021 == null) goto L411;
        r126.path = r021;
        goto L411
    L392:
        r442 = r410;
        r210 = r452;
        r314 = null;
        goto L393
    L383:
        r463 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L366
    L351:
        if (ImageLoader.shouldSendImageAsDocument(r126.path, r126.uri) == true) goto L352;
        if (r126.forceImage == true) goto L368;
        if (r312 == null) goto L368;
        r410 = r442;
        if (r312.endsWith(r410) == true) goto L361;
        if (r312.endsWith(r1114) == true) goto L361;
    L370:
        if (r126.forceImage == true) goto L380;
        if (r312 != null) goto L380;
        Uri r022 = r126.uri;
        if (r022 == null) goto L380;
        boolean r023 = MediaController.isGif(r022);
        Uri r513 = r126.uri;
        if (r023 == false) goto L378;
        String r024 = r513.toString();
        r464 = "gif";
        r17 = MediaController.copyFileToCache(r126.uri, "gif");
        r29 = r024;
        goto L367
    L378:
        if (MediaController.isWebp(r513) == false) goto L380;
        r29 = r126.uri.toString();
        r464 = "webp";
        r17 = MediaController.copyFileToCache(r126.uri, "webp");
    L380:
        r17 = r312;
        r29 = r16;
        r06 = false;
        r462 = r462;
    L361:
        if (r126.ttl > 0) goto L370;
        if (r312.endsWith(r410) == false) goto L365;
        r463 = "gif";
        goto L366
    L365:
        r463 = "webp";
    L368:
        r410 = r442;
        goto L370
    L309:
        if (r126.uri == null) goto L348;
        if (r116.getDescription().hasMimeType("image/png") == false) goto L348;
        BitmapFactory.Options r025 = new BitmapFactory.Options();     // Catch: Throwable -> L335
        InputStream r413 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r126.uri);     // Catch: Throwable -> L335
        Bitmap r026 = BitmapFactory.decodeStream(r413, null, r025);     // Catch: Throwable -> L333
        StringBuilder r514 = new StringBuilder();     // Catch: Throwable -> L333
        r107 = r67;
        r514.append(r107);     // Catch: Throwable -> L331
        r514.append(SharedConfig.getLastLocalId());     // Catch: Throwable -> L331
        r1114 = r433;
        r514.append(r1114);     // Catch: Throwable -> L329
        r16 = r28;
        File r216 = new File(FileLoader.getDirectory(4), r514.toString());     // Catch: Throwable -> L327
        FileOutputStream r121 = new FileOutputStream(r216);     // Catch: Throwable -> L327
        r026.compress(Bitmap.CompressFormat.WEBP, 100, r121);     // Catch: Throwable -> L325
        SharedConfig.saveConfig();     // Catch: Throwable -> L325
        r126.uri = Uri.fromFile(r216);     // Catch: Throwable -> L325
        if (r413 != null) goto L895;
    L891:
        r121.close();     // Catch: Exception -> L845
        goto L349
    L895:
        r413.close();     // Catch: Exception -> L844
    L325:
        th = th;
    L871:
        FileLog.e(th);     // Catch: Throwable -> L342
        if (r413 != null) goto L855;
    L340:
        if (r121 == null) goto L349;
    L855:
        r413.close();     // Catch: Exception -> L846
    L342:
        th = move-exception;
        if (r413 != null) goto L863;
    L345:
        if (r121 == null) goto L918;
        r121.close();     // Catch: Exception -> L848
        throw th;
    L919:
        throw th;
    L918:
        throw th;
    L863:
        r413.close();     // Catch: Exception -> L847
    L327:
        th = th;
    L328:
        r121 = null;
    L329:
        th = th;
        r16 = r28;
    L331:
        th = th;
        r16 = r28;
        r1114 = r433;
    L333:
        th = th;
        r16 = r28;
        r1114 = r433;
        r107 = r67;
    L335:
        th = th;
        r16 = r28;
        r1114 = r433;
        r107 = r67;
        r121 = null;
        r413 = null;
        goto L871
    L302:
        if ("content".equals(r27.getScheme()) == false) goto L304;
        r312 = null;
    L306:
        r28 = r05;
        r312 = r28;
    L291:
        if (r126.isLivePhoto == true) goto L293;
    L294:
        r732 = r452;
        r532 = r61;
        r60 = r50;
        r453 = r433;
        if (r90 == false) goto L506;
        VideoEditedInfo r414 = null;
    L511:
        if (r90 == true) goto L516;
        if (r414 != null) goto L518;
        if (r126.path.endsWith("mp4") == false) goto L516;
    L518:
        if (r126.path != null) goto L525;
        MediaController.SearchImage r027 = r126.searchImage;
        if (r027 == null) goto L525;
        if ((r027.photo instanceof TLRPC.TL_photo) == false) goto L524;
        r126.path = FileLoader.getInstance(r93.getCurrentAccount()).getPathToAttach(r126.searchImage.photo, true).getAbsolutePath();
        goto L525
    L524:
        r126.path = new File(FileLoader.getDirectory(4), Utilities.MD5(r126.searchImage.imageUrl) + "." + ImageLoader.getHttpUrlExtension(r126.searchImage.imageUrl, "jpg")).getAbsolutePath();
    L525:
        String r217 = r126.path;
        File r68 = new File(r217);
        String r028 = r217 + r68.length() + r732 + r68.lastModified();
        if (r414 == null) goto L540;
        boolean r127 = r414.muted;
        StringBuilder r218 = new StringBuilder();
        r218.append(r028);
        r218.append(r414.estimatedDuration);
        r218.append(r732);
        r218.append(r414.startTime);
        r218.append(r732);
        r218.append(r414.endTime);
        if (r414.muted == false) goto L530;
        String r029 = "_m";
    L531:
        r218.append(r029);
        r028 = r218.toString();
        if (r414.resultWidth == r414.originalWidth) goto L534;
        r028 = r028 + r732 + r414.resultWidth;
    L534:
        long r128 = r414.startTime;
        if (r128 >= 0) goto L538;
        r128 = 0;
    L538:
        r732 = r732;
        boolean r252 = r127;
        String r219 = r217;
        long r712 = r128;
    L539:
        String r129 = r028;
        if (r333 == false) goto L543;
    L555:
        String r282 = "mp4";
        VideoEditedInfo r153 = r414;
        File r262 = r68;
        String r272 = _UrlKt.FRAGMENT_ENCODE_SET;
        Object r772 = "groupId";
        Object r762 = "1";
        boolean r318 = r333;
        String r235 = r219;
        String r917 = "video/mp4";
        AccountInstance r220 = r93;
        TLRPC.TL_document r415 = null;
        String r515 = null;
    L566:
        if (r415 == null) goto L568;
        String r542 = r129;
        String r292 = r515;
        SendingMediaInfo r918 = r126;
        Object r334 = "masks";
        VideoEditedInfo r69 = r153;
        Object r130 = "final";
        int r516 = r70;
        boolean r195 = r318;
        String r319 = r67;
        if (r415.thumbs.isEmpty() == true) goto L736;
        String r030 = r918.thumbPath;
        if (r030 == null) goto L703;
        Bitmap r031 = BitmapFactory.decodeFile(r030);
    L704:
        if (r031 != null) goto L708;
        r031 = createVideoThumbnailAtTime(r918.path, r712);
        if (r031 != null) goto L708;
        r031 = createVideoThumbnail(r918.path, 1);
    L708:
        Bitmap r1310 = r031;
        if (r1310 == null) goto L732;
        if (r195 == false) goto L712;
    L715:
        int r713 = 90;
    L716:
        if (r69 != null) goto L718;
    L721:
        Bitmap.CompressFormat r032 = Bitmap.CompressFormat.JPEG;
    L720:
        Bitmap.CompressFormat r142 = r032;
        float r033 = r713;
        if (r713 <= 90) goto L725;
        int r184 = 80;
    L726:
        Object r84 = "parentObject";
        TLRPC.PhotoSize r034 = ImageLoader.scaleAndSaveImage(null, r1310, r142, false, r033, r033, r184, r195, 0, 0, false);
        r522 = r195;
        if (r034 != null) goto L729;
    L731:
        int r154 = 1;
        String r1210 = null;
        Object r85 = r84;
    L733:
        if (r034 == null) goto L735;
        r415.thumbs.add(r034);
        r415.flags |= r154;
    L735:
        String r852 = r1210;
        TLRPC.TL_document r1211 = r415;
        String r416 = r852;
        Object r131 = r130;
        Object r86 = r85;
        Object r335 = r334;
    L737:
        TLRPC.PhotoSize r035 = ImageLoader.fileToSize(r918.coverPath, false);
        if (r035 == null) goto L740;
    L742:
        if (r69 != null) goto L744;
    L753:
        if (r69 != null) goto L755;
    L763:
        String r1011 = r235;
        HashMap r714 = new HashMap();
        r714.put("originalPath", r542);
        if (r918.isLivePhoto == true) goto L766;
    L768:
        String r1117 = r292;
        if (r292 == null) goto L771;
        r714.put(r86, r1117);
    L771:
        if (r252 == true) goto L785;
        if (r91 == false) goto L785;
        int r89 = r31 + 1;
        StringBuilder r143 = new StringBuilder(r272);
        String r224 = r319;
        long r221 = r71;
        r143.append(r221);
        r714.put(r772, r143.toString());
        if (r115 == true) goto L784;
        if (r114 != null) goto L781;
        if (r89 != 10) goto L781;
    L783:
        r714.put(r131, r762);
        int r140 = r89;
        r472 = 0;
    L786:
        if (r522 == true) goto L791;
        if (r69 != null) goto L789;
    L792:
        ArrayList<TLRPC.InputDocument> r810 = r918.masks;
        if (r810 == null) goto L791;
        if (r810.isEmpty() == true) goto L791;
        r1211.attributes.add(new TLRPC.TL_documentAttributeHasStickers());
        SerializedData r811 = new SerializedData((r918.masks.size() * 20) + 4);
        r811.writeInt32(r918.masks.size());
        int r144 = 0;
    L798:
        if (r144 >= r918.masks.size()) goto L800;
        r918.masks.get(r144).serializeToStream(r811);
        r144 = r144 + 1;
        goto L798
    L800:
        r714.put(r335, Utilities.bytesToHex(r811.toByteArray()));
        r811.cleanup();
        goto L791
    L789:
        if (r69.isSticker == false) goto L792;
    L791:
        r71 = r221;
        r70 = r516;
        VideoEditedInfo r812 = r69;
        Bitmap r320 = r1310;
        r67 = r224;
        r25 = r93;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda48(r320, r416, r96, r97, r93, r812, r1211, r1011, r714, r918, r1117, r88, r98, r99, r100, r101, r102, r103, r104, r105, r106, r108, r035, r109, r111, r113, r114));
        r31 = r140;
        r77 = r242;
        r82 = r332;
        r915 = r49;
        ArrayList r1212 = r57;
        r138 = r58;
    L807:
        r232 = r1212;
        r03 = r31;
    L781:
        if (r516 == (r60 - 1)) goto L783;
    L784:
        r140 = r89;
    L785:
        r224 = r319;
        r221 = r71;
        r140 = r31;
        goto L786
    L766:
        if (TextUtils.isEmpty(r918.coverPath) == true) goto L768;
        r714.put("imagePath", r918.coverPath);
        goto L768
    L755:
        if (r69.needConvert() == true) goto L759;
        if (r918.isVideo == true) goto L763;
    L759:
        if (r69.isSticker == false) goto L761;
        String r715 = "webm";
    L762:
        File r1012 = new File(FileLoader.getDirectory(4), r319 + SharedConfig.getLastLocalId() + "." + r715);
        SharedConfig.saveConfig();
        r235 = r1012.getAbsolutePath();
        goto L763
    L761:
        r715 = r282;
        goto L762
    L744:
        if (r69.muted == false) goto L753;
        int r145 = r1211.attributes.size();
        int r155 = 0;
    L746:
        ArrayList<TLRPC.DocumentAttribute> r716 = r1211.attributes;
        if (r155 >= r145) goto L752;
        if ((r716.get(r155) instanceof TLRPC.TL_documentAttributeAnimated) == true) goto L753;
        r155 = r155 + 1;
        goto L746
    L752:
        r716.add(new TLRPC.TL_documentAttributeAnimated());
        goto L753
    L740:
        if (r918.coverPhoto == null) goto L742;
        r035 = new ImageLoader.PhotoSizeFromPhoto(r918.coverPhoto);
        goto L742
    L729:
        if (r034.location == null) goto L731;
        r154 = 1;
        r1210 = getKeyForPhotoSize(r220, r034, null, true, false);
        r85 = r84;
        goto L733
    L725:
        r184 = 55;
        goto L726
    L718:
        if (r69.isSticker == false) goto L721;
        r032 = Bitmap.CompressFormat.WEBP;
        goto L720
    L712:
        if (r918.ttl != 0) goto L715;
        r713 = Math.max(r1310.getWidth(), r1310.getHeight());
        goto L716
    L732:
        r85 = "parentObject";
        r522 = r195;
        r154 = 1;
        r034 = null;
        r1210 = null;
        goto L733
    L703:
        r031 = null;
        goto L704
    L736:
        r86 = "parentObject";
        r522 = r195;
        r1211 = r415;
        r416 = null;
        r1310 = null;
        r131 = r130;
        r335 = r334;
        goto L737
    L568:
        if (r126.path != null) goto L570;
    L611:
        r542 = r129;
        boolean r336 = r318;
        r292 = r515;
        Object r164 = "parentObject";
        Object r203 = "masks";
        String r1311 = r66;
        r319 = r67;
    L612:
        String r036 = r126.thumbPath;
        if (r036 == null) goto L615;
        Bitmap r037 = BitmapFactory.decodeFile(r036);
    L616:
        if (r037 != null) goto L620;
        r037 = createVideoThumbnailAtTime(r126.path, r712);
        if (r037 != null) goto L620;
        r037 = createVideoThumbnail(r126.path, 1);
    L620:
        if (r037 == null) goto L644;
        if (r336 == false) goto L623;
    L626:
        int r141 = 90;
    L627:
        if (r153 != null) goto L629;
    L632:
        Bitmap.CompressFormat r417 = Bitmap.CompressFormat.JPEG;
    L631:
        Bitmap.CompressFormat r146 = r417;
        float r418 = r141;
        if (r141 <= 90) goto L636;
        int r185 = 80;
    L637:
        SendingMediaInfo r419 = r126;
        Object r813 = r164;
        r66 = r1311;
        r1310 = r037;
        VideoEditedInfo r717 = r153;
        Object r147 = "final";
        boolean r196 = r336;
        Object r337 = r203;
        r516 = r70;
        TLRPC.PhotoSize r038 = ImageLoader.scaleAndSaveImage(null, r1310, r146, false, r418, r418, r185, r196, 0, 0, false);
        if (r038 != null) goto L640;
    L642:
        int r148 = 0;
        Object r149 = r147;
        Object r814 = r813;
        Object r338 = r337;
    L643:
        String r165 = null;
        Object r150 = r149;
        Object r815 = r814;
        Object r339 = r338;
    L645:
        r1211 = new TLRPC.TL_document();
        r1211.file_reference = new byte[r148];
        if (r038 == null) goto L648;
        r1211.thumbs.add(r038);
        r1211.flags |= 1;
    L648:
        VideoEditedInfo r1410 = r419.videoEditedInfo;
        if (r1410 != null) goto L651;
    L653:
        r1211.mime_type = r917;
    L654:
        r220.getUserConfig().saveConfig(false);
        VideoEditedInfo r919 = r419.videoEditedInfo;
        if (r919 != null) goto L657;
    L663:
        VideoEditedInfo r156 = r717;
        String r039 = r165;
    L664:
        if (r196 == false) goto L666;
        TLRPC.TL_documentAttributeVideo r610 = new TLRPC.TL_documentAttributeVideo_layer159();
    L667:
        r1211.attributes.add(r610);
        if (r156 != null) goto L670;
    L674:
        Object r166 = r815;
        if (r262.exists() == false) goto L696;
        r1211.size = (int) r262.length();
    L696:
        fillVideoAttribute(r419.path, r610, null);
    L697:
        r918 = r419;
        r69 = r156;
        r86 = r166;
        r522 = r196;
        r416 = r039;
        r131 = r150;
        r335 = r339;
        goto L737
    L670:
        if (r156.needConvert() == true) goto L676;
        if (r419.isVideo == true) goto L674;
    L676:
        if (r419.isVideo == true) goto L678;
    L680:
        Object r167 = r815;
        r610.duration = (int) (r156.estimatedDuration / 1000);
    L681:
        int r718 = r156.rotationValue;
        MediaController.CropState r816 = r156.cropState;
        if (r816 == null) goto L685;
        int r920 = r816.transformWidth;
        int r817 = r816.transformHeight;
    L686:
        if (r718 != 90) goto L688;
    L691:
        r610.w = r817;
        r610.h = r920;
    L692:
        r1211.size = r156.estimatedSize;
        r166 = r167;
        goto L697
    L688:
        if (r718 == 270) goto L691;
        r610.w = r920;
        r610.h = r817;
        goto L692
    L685:
        r920 = r156.resultWidth;
        r817 = r156.resultHeight;
        goto L686
    L678:
        if (r156.muted == false) goto L680;
        fillVideoAttribute(r419.path, r610, r156);
        r156.originalWidth = r610.w;
        r156.originalHeight = r610.h;
        r167 = r815;
        goto L681
    L666:
        r610 = new TLRPC.TL_documentAttributeVideo();
        r610.supports_streaming = true;
        goto L667
    L657:
        if (r919.isSticker == false) goto L663;
        r1211.attributes.add(new TLRPC.TL_documentAttributeAnimated());
        TLRPC.TL_documentAttributeSticker r921 = new TLRPC.TL_documentAttributeSticker();
        r921.alt = "👍";
        r921.stickerset = new TLRPC.TL_inputStickerSetEmpty();
        r1211.attributes.add(r921);
        if (r038 == null) goto L663;
        TLRPC.FileLocation r922 = r038.location;
        if (r922 == null) goto L663;
        r156 = r717;
        r039 = String.format(Locale.US, "%d_%d@b1", new Object[]{Long.valueOf(r922.volume_id), Integer.valueOf(r038.location.local_id)});
        goto L664
    L651:
        if (r1410.isSticker == false) goto L653;
        r1211.mime_type = "video/webm";
        goto L654
    L640:
        if (r038.location == null) goto L642;
        r148 = 0;
        r165 = getKeyForPhotoSize(r220, r038, null, true, false);
        r150 = r147;
        r815 = r813;
        r339 = r337;
        goto L645
    L636:
        r185 = 55;
        goto L637
    L629:
        if (r153.isSticker == false) goto L632;
        r417 = Bitmap.CompressFormat.WEBP;
        goto L631
    L623:
        if (r126.ttl != 0) goto L626;
        r141 = Math.max(r037.getWidth(), r037.getHeight());
        goto L627
    L644:
        r419 = r126;
        r66 = r1311;
        r717 = r153;
        r814 = r164;
        r149 = "final";
        r196 = r336;
        r516 = r70;
        r148 = 0;
        r1310 = r037;
        r338 = r203;
        r038 = null;
        goto L643
    L615:
        r037 = null;
        goto L616
    L570:
        if (r126.isLivePhoto == false) goto L611;
        File r040 = new File(r126.path);
        File r611 = FileLoader.getDirectory(4);
        r336 = r318;
        r319 = r67;
        StringBuilder r041 = new StringBuilder(r319);
        r164 = "parentObject";
        r041.append(SharedConfig.getLastLocalId());
        r1311 = r66;
        r041.append(r1311);
        File r420 = new File(r611, r041.toString());
        File r612 = FileLoader.getDirectory(4);
        StringBuilder r421 = new StringBuilder(r319);
        r203 = "masks";
        r421.append(SharedConfig.getLastLocalId());
        r421.append(".mp4");
        File r042 = new File(r612, r421.toString());
        String r613 = r515;
        long r422 = r126.livePhotoVideoOffset;
        copyRange(r040, r422, r040.length(), r042);     // Catch: Exception -> L608
        String r423 = r042.getAbsolutePath();     // Catch: Exception -> L608
        r126.path = r423;     // Catch: Exception -> L577
        if (TextUtils.isEmpty(r126.imagePath) == true) goto L580;
        r126.coverPath = r126.imagePath;     // Catch: Exception -> L577
    L576:
        r542 = r129;
        String r2110 = r423;
        r292 = r613;
    L607:
        r235 = r2110;
        goto L612
    L580:
        copyRange(r040, 0, r422, r420);     // Catch: Exception -> L577
        r126.coverPath = r420.getAbsolutePath();     // Catch: Exception -> L577
        Pair<Integer, Integer> r043 = AndroidUtilities.getImageOrientation(r420);     // Catch: Exception -> L577
        if (r043 == null) goto L576;
        if (((Integer) r043.first).intValue() == 0) goto L576;
        Bitmap r782 = BitmapFactory.decodeFile(r420.getAbsolutePath());     // Catch: Exception -> L577
        if (r782 == null) goto L576;
        int r517 = ((Integer) r043.second).intValue();     // Catch: Exception -> L577
        Matrix r1411 = new Matrix();     // Catch: Exception -> L577
        r2110 = r423;
        if (r517 != 1) goto L591;
        float r424 = -1.0f;
    L590:
        r292 = r613;
        if (r517 != 2) goto L594;
        float r518 = -1.0f;
    L879:
        r542 = r129;
        r1411.postScale(r424, r518, r782.getWidth() / 2.0f, r782.getHeight() / 2.0f);     // Catch: Exception -> L603
        r1411.postRotate(((Integer) r043.first).intValue());     // Catch: Exception -> L603
        Bitmap r044 = Bitmap.createBitmap(r782, 0, 0, r782.getWidth(), r782.getHeight(), r1411, true);     // Catch: Exception -> L603
        File r151 = new File(FileLoader.getDirectory(4), r319 + SharedConfig.getLastLocalId() + r1311);     // Catch: Exception -> L603
        r044.compress(Bitmap.CompressFormat.JPEG, 97, new FileOutputStream(r151));     // Catch: Exception -> L601
        r126.coverPath = r151.getAbsolutePath();     // Catch: Exception -> L601
        r420.delete();     // Catch: Exception -> L601
    L601:
        e = e;
    L610:
        FileLog.e(e);
    L603:
        e = e;
    L605:
        e = e;
        r542 = r129;
        goto L610
    L594:
        r518 = 1.0f;
        goto L879
    L591:
        r424 = 1.0f;
    L577:
        e = e;
        r542 = r129;
        r2110 = r423;
        r292 = r613;
    L608:
        e = e;
        r542 = r129;
        r292 = r613;
        r2110 = r235;
        goto L610
    L543:
        if (r126.ttl != 0) goto L555;
        if (r92 == true) goto L555;
        if (r414 != null) goto L547;
    L556:
        MessagesStorage r045 = r93.getMessagesStorage();
        if (r333 == true) goto L559;
        int r157 = 2;
    L560:
        Object[] r046 = r045.getSentFile(r129, r157);
        if (r046 == null) goto L555;
        Object r158 = r046[0];
        if ((r158 instanceof TLRPC.TL_document) == false) goto L555;
        TLRPC.TL_document r159 = (TLRPC.TL_document) r158;
        String r047 = (String) r046[1];
        String r519 = r126.path;
        r153 = r414;
        r415 = r159;
        r282 = "mp4";
        r272 = _UrlKt.FRAGMENT_ENCODE_SET;
        r917 = "video/mp4";
        r772 = "groupId";
        r262 = r68;
        r762 = "1";
        r318 = r333;
        r235 = r219;
        r220 = r93;
        ensureMediaThumbExists(r220, r318, r415, r519, null, r712);
        r515 = r047;
        goto L566
    L559:
        r157 = 5;
        goto L560
    L547:
        if (r414.filterState != null) goto L555;
        if (r414.paintPath != null) goto L555;
        if (r414.mediaEntities != null) goto L555;
        if (r414.cropState != null) goto L555;
    L530:
        r029 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L531
    L540:
        r732 = r732;
        r219 = r217;
        r712 = 0;
        r252 = false;
    L516:
        if (r126.isLivePhoto == true) goto L518;
        r25 = r93;
        r522 = r333;
        r71 = r71;
        if (r242 != null) goto L805;
        ArrayList r048 = new ArrayList();
        r82 = new ArrayList();
        ArrayList r160 = new ArrayList();
        r138 = new ArrayList();
        r915 = new ArrayList();
        ?? r719 = r048;
    L806:
        r719.add(r126.path);
        r82.add(r126.path);
        r915.add(r126.uri);
        r160.add(r126.caption);
        r138.add(r126.entities);
        r1212 = r160;
        r77 = r719;
        goto L807
    L805:
        r719 = r242;
        r82 = r332;
        r915 = r49;
        r160 = r57;
        r138 = r58;
        goto L806
    L506:
        VideoEditedInfo r049 = r126.videoEditedInfo;
        if (r049 != null) goto L510;
        r049 = createCompressionSettings(r126.path, r126.livePhotoVideoOffset);
    L510:
        r414 = r049;
        goto L511
    L293:
        if (r126.discardLivePhoto == true) goto L295;
    L176:
        if (r126.videoEditedInfo != null) goto L286;
        r532 = r48;
        ArrayList r543 = r138;
        if (r04.type != 1) goto L247;
        HashMap r614 = new HashMap();
        TLRPC.Document r050 = r126.searchImage.document;
        if ((r050 instanceof TLRPC.TL_document) == false) goto L182;
        TLRPC.TL_document r051 = (TLRPC.TL_document) r050;
        File r720 = FileLoader.getInstance(r93.getCurrentAccount()).getPathToAttach(r051, true);
    L183:
        if (r051 != null) goto L237;
        TLRPC.TL_document r923 = new TLRPC.TL_document();
        String r1118 = r14;
        r923.id = 0;
        r923.file_reference = new byte[r182];
        r923.date = r93.getConnectionsManager().getCurrentTime();
        TLRPC.TL_documentAttributeFilename r052 = new TLRPC.TL_documentAttributeFilename();
        r052.file_name = "animation.gif";
        r923.attributes.add(r052);
        r923.size = r126.searchImage.size;
        r923.dc_id = 0;
        if (r90 == false) goto L187;
    L189:
        r923.mime_type = "image/gif";
    L191:
        if (r720.exists() == false) goto L193;
        File r520 = r720;
    L194:
        if (r720 != null) goto L198;
        File r225 = new File(FileLoader.getDirectory(4), Utilities.MD5(r126.searchImage.thumbUrl) + "." + ImageLoader.getHttpUrlExtension(r126.searchImage.thumbUrl, "jpg"));
        if (r225.exists() == true) goto L199;
        r225 = null;
    L199:
        if (r225 == null) goto L231;
        if (r311 == false) goto L867;
    L207:
        int r053 = 90;
    L209:
        if (r225.getAbsolutePath().endsWith("mp4") == true) goto L853;
        String r161 = r225.getAbsolutePath();     // Catch: Exception -> L205
        float r226 = r053;
        Object r1013 = null;
        Bitmap r168 = ImageLoader.loadBitmap(r161, null, r226, r226, true);     // Catch: Exception -> L226
    L218:
        if (r168 == null) goto L233;
        float r227 = r053;
        if (r053 <= 90) goto L222;
        int r054 = 80;
    L223:
        TLRPC.PhotoSize r055 = ImageLoader.scaleAndSaveImage(r168, r227, r227, r054, r311);     // Catch: Exception -> L226
        if (r055 == null) goto L228;
        r923.thumbs.add(r055);     // Catch: Exception -> L226
        r923.flags |= 1;
    L228:
        r168.recycle();     // Catch: Exception -> L226
        goto L233
    L222:
        r054 = 55;
    L226:
        e = e;
    L230:
        FileLog.e(e);
    L233:
        if (r923.thumbs.isEmpty() == false) goto L236;
        TLRPC.TL_photoSize r056 = new TLRPC.TL_photoSize();
        MediaController.SearchImage r169 = r126.searchImage;
        r056.w = r169.width;
        r056.h = r169.height;
        r056.size = 0;
        r056.location = new TLRPC.TL_fileLocationUnavailable();
        r056.type = "x";
        r923.thumbs.add(r056);
        r923.flags |= 1;
    L236:
        r720 = r520;
        r051 = r923;
    L238:
        String r170 = r126.searchImage.imageUrl;
        if (r720 != null) goto L241;
    L240:
        String r721 = r170;
        String r171 = r126.searchImage.imageUrl;
        if (r171 == null) goto L245;
        r614.put("originalPath", r171);
    L245:
        r66 = r1118;
        Object r173 = r1013;
        r67 = r222;
        r60 = r50;
        r492 = 0;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda46(r96, r97, r93, r051, r721, r614, r126, null, r88, r98, r99, r100, r101, r102, r103, r104, r105, r212, r106, r108, r109, r111, r113, r114));
        r25 = r93;
        r03 = r31;
        r77 = r242;
        r522 = r311;
        r732 = r452;
        r82 = r332;
        r915 = r49;
        r232 = r232;
        r138 = r543;
        r70 = r51;
        r71 = r26;
    L246:
        r453 = r433;
        goto L808
    L241:
        r170 = r720.toString();
        goto L240
    L853:
        r168 = createVideoThumbnail(r225.getAbsolutePath(), 1);     // Catch: Exception -> L205
        r1013 = null;
    L213:
        e = e;
    L206:
        r1013 = null;
        goto L230
    L867:
        if (r126.ttl != 0) goto L207;
        r053 = 320;
    L205:
        e = e;
        goto L206
    L231:
        r1013 = null;
        goto L233
    L198:
        r225 = r720;
        goto L199
    L193:
        r520 = null;
        r720 = null;
        goto L194
    L187:
        if (r720.toString().endsWith("mp4") == false) goto L189;
        r923.mime_type = "video/mp4";
        r923.attributes.add(new TLRPC.TL_documentAttributeAnimated());
        goto L191
    L237:
        r1118 = r14;
        r1013 = null;
        goto L238
    L182:
        r720 = new File(FileLoader.getDirectory(4), Utilities.MD5(r126.searchImage.imageUrl) + "." + ImageLoader.getHttpUrlExtension(r126.searchImage.imageUrl, "jpg"));
        r051 = null;
        goto L183
    L247:
        r66 = r14;
        r67 = r222;
        ArrayList r572 = r232;
        long r632 = r26;
        r60 = r50;
        r173 = null;
        r492 = 0;
        boolean r3310 = r311;
        TLRPC.Photo r057 = r04.photo;
        if ((r057 instanceof TLRPC.TL_photo) == false) goto L250;
        TLRPC.TL_photo r321 = (TLRPC.TL_photo) r057;
    L251:
        if (r321 != null) goto L267;
        File r521 = new File(FileLoader.getDirectory(4), Utilities.MD5(r126.searchImage.imageUrl) + "." + ImageLoader.getHttpUrlExtension(r126.searchImage.imageUrl, "jpg"));
        if (r521.exists() == true) goto L255;
    L259:
        boolean r1119 = true;
    L260:
        if (r321 != null) goto L266;
        File r228 = new File(FileLoader.getDirectory(4), Utilities.MD5(r126.searchImage.thumbUrl) + "." + ImageLoader.getHttpUrlExtension(r126.searchImage.thumbUrl, "jpg"));
        if (r228.exists() == false) goto L264;
        r321 = r93.getSendMessagesHelper().generatePhotoSizes(r228.toString(), null);
    L264:
        if (r321 != null) goto L266;
        r321 = new TLRPC.TL_photo();
        r321.date = r93.getConnectionsManager().getCurrentTime();
        r321.file_reference = new byte[0];
        TLRPC.TL_photoSize r058 = new TLRPC.TL_photoSize();
        MediaController.SearchImage r523 = r126.searchImage;
        r058.w = r523.width;
        r058.h = r523.height;
        r058.size = 0;
        r058.location = new TLRPC.TL_fileLocationUnavailable();
        r058.type = "x";
        r321.sizes.add(r058);
    L266:
        boolean r425 = r1119;
    L268:
        HashMap r059 = new HashMap();
        String r229 = r126.searchImage.imageUrl;
        if (r229 == null) goto L271;
        r059.put("originalPath", r229);
    L271:
        if (r91 == false) goto L284;
        int r230 = r31 + 1;
        StringBuilder r524 = new StringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        long r818 = r632;
        r524.append(r818);
        r059.put("groupId", r524.toString());
        if (r115 == true) goto L282;
        if (r114 == null) goto L276;
    L279:
        int r525 = r51;
        if (r525 != (r60 - 1)) goto L283;
    L281:
        r059.put("final", "1");
        r31 = r230;
        r472 = 0;
    L285:
        r70 = r525;
        r71 = r818;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda47(r96, r97, r93, r321, r425, r126, r059, null, r88, r98, r99, r100, r101, r102, r103, r104, r105, r106, r108, r109, r111, r113, r114));
        r25 = r93;
        r03 = r31;
        r77 = r242;
        r522 = r3310;
        r732 = r452;
        r82 = r332;
        r915 = r49;
        r232 = r572;
        r138 = r543;
        r532 = r532;
    L283:
        r31 = r230;
        goto L285
    L276:
        if (r230 != 10) goto L279;
        r525 = r51;
        goto L281
    L282:
        r525 = r51;
        goto L283
    L284:
        r525 = r51;
        r818 = r632;
        goto L285
    L255:
        if (r521.length() == 0) goto L259;
        r321 = r93.getSendMessagesHelper().generatePhotoSizes(r521.toString(), null);
        if (r321 == null) goto L259;
        r1119 = false;
        goto L260
    L267:
        r425 = true;
        goto L268
    L250:
        r321 = null;
        goto L251
    L166:
        if (r91 == true) goto L168;
    L172:
        r31 = r03;
        goto L173
    L168:
        if (r10 <= 1) goto L172;
        if ((r03 % 10) != 0) goto L172;
        r472 = Utilities.random.nextLong();
        r31 = r182;
        r26 = r472;
        goto L173
    L162:
        r212 = r182;
        goto L164
    L809:
        boolean r526 = r311;
        long r493 = r56;
        ?? r174 = r75;
        ArrayList r552 = r82;
        ArrayList r562 = r915;
        int r602 = r10;
        ArrayList r582 = r138;
        ArrayList r573 = r232;
        long r527 = r472;
        if (r527 == r493) goto L813;
        if (r115 == true) goto L813;
        int r322 = r101;
        finishGroup(r25, r527, r322);
    L814:
        if (r116 == null) goto L816;
        r116.releasePermission();
    L816:
        if (r174 == null) goto L841;
        if (r174.isEmpty() == true) goto L841;
        int r1312 = 1;
        long[] r1510 = new long[1];
        int r426 = r174.size();
        int r1120 = r03;
        int r060 = 0;
    L820:
        if (r060 >= r426) goto L841;
        if (r90 == false) goto L830;
        if (r526 == true) goto L830;
        int r528 = r602;
        if (r528 > r1312) goto L826;
    L829:
        boolean r5110 = false;
    L831:
        int r615 = r1120 + 1;
        String r722 = (String) r174.get(r060);
        ArrayList r819 = r552;
        String r924 = (String) r819.get(r060);
        ArrayList r1014 = r562;
        Uri r1121 = (Uri) r1014.get(r060);
        ArrayList r1213 = r582;
        ArrayList r1313 = (ArrayList) r1213.get(r060);
        if (r615 != 10) goto L834;
    L838:
        boolean r1610 = true;
    L837:
        ArrayList r1412 = r573;
        r602 = r528;
        r552 = r819;
        r573 = r1412;
        handleError(prepareSendingDocumentInternal(r25, r722, r924, r1121, r462, r88, r98, r99, r103, r104, r1313, r96, r1510, r1610, (CharSequence) r1412.get(r060), r100, r322, 0, null, r90, r105, r106, r108, r109, r111, r113, null, -1), r25);
        r060 = r060 + 1;
        r322 = r101;
        r426 = r426;
        r1120 = r615;
        r562 = r1014;
        r582 = r1213;
        r1312 = 1;
        goto L820
    L834:
        if (r060 == (r426 - 1)) goto L838;
        r1610 = r5110;
        goto L837
    L826:
        if ((r1120 % 10) != 0) goto L829;
        r5110 = false;
        r1510[0] = Utilities.random.nextLong();
        r1120 = 0;
    L830:
        r528 = r602;
    L841:
        if (BuildVars.LOGS_ENABLED == false) goto L920;
        FileLog.d("total send time = " + (System.currentTimeMillis() - r34));
        return;
    L920:
        return;
    L813:
        r322 = r101;
    L156:
        r433 = ".webp";
        r442 = ".gif";
        r452 = "_";
        r182 = 0;
        r25 = r93;
        r1112 = null;
        r311 = r19;
        r48 = null;
        goto L157
    }

    private /* synthetic */ void lambda$processUnsentMessages$104(ArrayList r11, ArrayList r12, ArrayList r13, ArrayList r14, ArrayList r15) {
        getMessagesController().putUsers(r11, true);
        getMessagesController().putChats(r12, true);
        getMessagesController().putEncryptedChats(r13, true);
        int r112 = r14.size();
        int r132 = 0;
    L4:
        if (r132 >= r112) goto L17;
        MessageObject r0 = new MessageObject(this.currentAccount, (TLRPC.Message) r14.get(r132), false, true);
        long r4 = r0.getGroupId();
        if (r4 == 0) goto L16;
        HashMap<String, String> r6 = r0.messageOwner.params;
        if (r6 == null) goto L16;
        if (r6.containsKey("final") == true) goto L16;
        if (r132 != (r112 - 1)) goto L14;
    L15:
        r0.messageOwner.params.put("final", "1");
        goto L16
    L14:
        if (((TLRPC.Message) r14.get(r132 + 1)).grouped_id != r4) goto L15;
    L16:
        retrySendMessage(r0, true, 0);
        r132 = r132 + 1;
        goto L4
    L17:
        if (r15 == null) goto L22;
        int r113 = 0;
    L20:
        if (r113 >= r15.size()) goto L30;
        MessageObject r133 = new MessageObject(this.currentAccount, (TLRPC.Message) r15.get(r113), false, true);
        r133.scheduled = true;
        retrySendMessage(r133, true, 0);
        r113 = r113 + 1;
        goto L20
    L30:
        return;
    }

    private /* synthetic */ void lambda$putToSendingMessages$61(TLRPC.Message r2, boolean r3) {
        putToSendingMessages(r2, r3, true);
    }

    private /* synthetic */ void lambda$requestUrlAuth$36(TLObject r3, TLRPC.TL_messages_requestUrlAuth r4, ChatActivity r5, String r6, boolean r7) {
        if (r3 != null) goto L5;
        AlertsCreator.showOpenUrlAlert(r5, r6, false, r7);
        return;
    L5:
        if ((r3 instanceof TLRPC.TL_urlAuthResultRequest) == false) goto L9;
        OAuthSheet.handle(false, this.currentAccount, r4, (TLRPC.TL_urlAuthResultRequest) r3);
        return;
    L9:
        if ((r3 instanceof TLRPC.TL_urlAuthResultAccepted) == false) goto L13;
        OAuthSheet.handle(false, this.currentAccount, r4, (TLRPC.TL_urlAuthResultAccepted) r3);
        return;
    L13:
        if ((r3 instanceof TLRPC.TL_urlAuthResultDefault) == false) goto L18;
        AlertsCreator.showOpenUrlAlert(r5, r6, false, r7);
        return;
    }

    private /* synthetic */ void lambda$requestUrlAuth$37(TLRPC.TL_messages_requestUrlAuth r1, ChatActivity r2, String r3, boolean r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda50(this, r5, r1, r2, r3, r4));
    }

    private /* synthetic */ void lambda$sendCallback$38(String r1, List r2) {
        this.waitingForCallback.remove(r1);
        r2.remove(r1);
    }

    private /* synthetic */ void lambda$sendCallback$40(boolean r2, MessageObject r3, TL_keyboard.KeyboardButtonProto r4, TwoStepVerificationActivity r5, ChatActivity r6, TLRPC.InputCheckPasswordSRP r7) {
        sendCallback(r2, r3, r4, r7, r5, r6);
    }

    private /* synthetic */ void lambda$sendCallback$41(boolean r1, MessageObject r2, TL_keyboard.KeyboardButtonProto r3, ChatActivity r4, AlertDialog r5, int r6) {
        TwoStepVerificationActivity r52 = new TwoStepVerificationActivity();
        r52.setDelegate(0, new SendMessagesHelper$$ExternalSyntheticLambda22(this, r1, r2, r3, r52, r4));
        r4.presentFragment(r52);
    }

    private /* synthetic */ void lambda$sendCallback$43(TLRPC.TL_error r8, TLObject r9, TwoStepVerificationActivity r10, boolean r11, MessageObject r12, TL_keyboard.KeyboardButtonProto r13, ChatActivity r14) {
        if (r8 != null) goto L5;
        TL_account.Password r92 = (TL_account.Password) r9;
        r10.setCurrentPasswordInfo(null, r92);
        TwoStepVerificationActivity.initPasswordNewAlgo(r92);
        sendCallback(r11, r12, r13, r10.getNewSrpPassword(), r10, r14);
        return;
    }

    private /* synthetic */ void lambda$sendCallback$44(TwoStepVerificationActivity r10, boolean r11, MessageObject r12, TL_keyboard.KeyboardButtonProto r13, ChatActivity r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda67(this, r16, r15, r10, r11, r12, r13, r14));
    }

    private /* synthetic */ void lambda$sendCallback$45(String r26, List r27, boolean r28, TLObject r29, MessageObject r30, TL_keyboard.KeyboardButtonProto r31, ChatActivity r32, TwoStepVerificationActivity r33, TLObject[] r34, TLRPC.TL_error r35, TLRPC.InputCheckPasswordSRP r36, boolean r37) {
        this.waitingForCallback.remove(r26);
        r27.remove(r26);
        if (r28 == false) goto L7;
        if (r29 != null) goto L7;
        sendCallback(false, r30, r31, r32);
        return;
    L7:
        TLRPC.TL_game r9 = null;
        if (r29 == null) goto L109;
        if (r33 == null) goto L11;
        r33.needHideProgress();
        r33.finishFragment();
    L11:
        long r11 = r30.getFromChatId();
        long r13 = r30.messageOwner.via_bot_id;
        if (r13 == 0) goto L15;
        r11 = r13;
    L15:
        if (r11 <= 0) goto L19;
        TLRPC.User r7 = getMessagesController().getUser(Long.valueOf(r11));
        if (r7 == null) goto L22;
        String r72 = ContactsController.formatName(r7.first_name, r7.last_name);
    L23:
        if (r72 != null) goto L25;
        r72 = "bot";
    L25:
        TL_keyboard.TL_inlineButtonTypeUrlAuth r132 = (TL_keyboard.TL_inlineButtonTypeUrlAuth) TLKeyboardHelper.getType(r31, TL_keyboard.TL_inlineButtonTypeUrlAuth.class);
        if (r132 == null) goto L40;
        if ((r29 instanceof TLRPC.TL_urlAuthResultRequest) == false) goto L32;
        OAuthSheet.handle(false, this.currentAccount, (TLRPC.TL_messages_requestUrlAuth) r34[0], (TLRPC.TL_urlAuthResultRequest) r29, r132.url, null, null, false, null);
        return;
    L32:
        if ((r29 instanceof TLRPC.TL_urlAuthResultAccepted) == false) goto L36;
        OAuthSheet.handle(false, this.currentAccount, (TLRPC.TL_messages_requestUrlAuth) r34[0], (TLRPC.TL_urlAuthResultAccepted) r29, r132.url, null, null, false, null);
        return;
    L36:
        if ((r29 instanceof TLRPC.TL_urlAuthResultDefault) == false) goto L180;
        AlertsCreator.showOpenUrlAlert(r32, r132.url, false, true);
        return;
    L180:
        return;
    L40:
        if (TLKeyboardHelper.isType(r31, TL_keyboard.TL_inlineButtonTypeBuy.class) == true) goto L42;
        TLRPC.TL_messages_botCallbackAnswer r2 = (TLRPC.TL_messages_botCallbackAnswer) r29;
        TL_keyboard.TL_inlineButtonTypeCallback r3 = (TL_keyboard.TL_inlineButtonTypeCallback) TLKeyboardHelper.getType(r31, TL_keyboard.TL_inlineButtonTypeCallback.class);
        if (r28 == false) goto L64;
    L69:
        String r0 = r2.message;
        if (r0 == null) goto L81;
        if (r2.alert == true) goto L74;
        r32.showAlert(r72, r0);
        return;
    L74:
        if (r32.getParentActivity() == null) goto L186;
        AlertDialog.Builder r02 = new AlertDialog.Builder(r32.getParentActivity());
        r02.setTitle(r72);
        r02.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r02.setMessage(r2.message);
        r32.showDialog(r02.create());
        return;
    L186:
        return;
    L81:
        if (r2.url != null) goto L83;
        return;
    L83:
        if (r32.getParentActivity() == null) goto L187;
        TLRPC.User r03 = getMessagesController().getUser(Long.valueOf(r11));
        if (r03 != null) goto L88;
    L90:
        boolean r04 = false;
    L92:
        if (TLKeyboardHelper.isType(r31, TL_keyboard.TL_inlineButtonTypeGame.class) == false) goto L107;
        TLRPC.MessageMedia r38 = r30.messageOwner.media;
        if ((r38 instanceof TLRPC.TL_messageMediaGame) == false) goto L96;
        r9 = r38.game;
    L96:
        if (r9 == null) goto L188;
        String r39 = r2.url;
        if (r04 == false) goto L101;
    L104:
        MessageObject r22 = r30;
        boolean r4 = false;
    L105:
        r32.showOpenGameAlert(r9, r22, r39, r4, r11);
        return;
    L101:
        if (MessagesController.getNotificationsSettings(this.currentAccount).getBoolean("askgame_" + r11, true) == false) goto L104;
        r22 = r30;
        r4 = true;
        goto L105
    L188:
        return;
    L107:
        AlertsCreator.showOpenUrlAlert(r32, r2.url, false, false);
        return;
    L88:
        if (r03.verified == false) goto L90;
        r04 = true;
        goto L92
    L187:
        return;
    L64:
        if (r2.cache_time == 0) goto L69;
        if (r3 != null) goto L67;
    L68:
        getMessagesStorage().saveBotCache(r26, r2);
        goto L69
    L67:
        if (r3.requires_password == true) goto L69;
    L42:
        if ((r29 instanceof TLRPC.TL_payments_paymentFormStars) == false) goto L46;
        StarsController.getInstance(this.currentAccount).openPaymentForm(r30, ((TLRPC.TL_payments_getPaymentForm) r34[0]).invoice, (TLRPC.TL_payments_paymentFormStars) r29, new SendMessagesHelper$$ExternalSyntheticLambda52(this, r26, r27), new SendMessagesHelper$$ExternalSyntheticLambda53());
        return;
    L46:
        if ((r29 instanceof TLRPC.PaymentForm) == false) goto L50;
        TLRPC.PaymentForm r05 = (TLRPC.PaymentForm) r29;
        getMessagesController().putUsers(r05.users, false);
        r32.presentFragment(new PaymentFormActivity(r05, r30, r32));
        return;
    L50:
        if ((r29 instanceof TLRPC.TL_payments_paymentReceiptStars) == false) goto L58;
        Context r06 = LaunchActivity.instance;
        if (r06 != null) goto L55;
        r06 = ApplicationLoader.applicationContext;
    L55:
        StarsIntroActivity.showTransactionSheet(r06, false, this.currentAccount, (TLRPC.TL_payments_paymentReceiptStars) r29, null);
        return;
    L58:
        if ((r29 instanceof TLRPC.PaymentReceipt) == false) goto L181;
        r32.presentFragment(new PaymentFormActivity((TLRPC.PaymentReceipt) r29));
        return;
    L181:
        return;
    L22:
        r72 = null;
        goto L23
    L19:
        TLRPC.Chat r73 = getMessagesController().getChat(Long.valueOf(-r11));
        if (r73 == null) goto L22;
        r72 = r73.title;
        goto L23
    L109:
        if (r35 != null) goto L111;
        return;
    L111:
        if (r32.getParentActivity() != null) goto L114;
        return;
    L114:
        if ("PASSWORD_HASH_INVALID".equals(r35.text) == false) goto L119;
        if (r36 != null) goto L184;
        AlertDialog.Builder r74 = new AlertDialog.Builder(r32.getParentActivity());
        r74.setTitle(LocaleController.getString(R.string.BotOwnershipTransfer));
        r74.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("BotOwnershipTransferReadyAlertText", R.string.BotOwnershipTransferReadyAlertText, new Object[0])));
        r74.setPositiveButton(LocaleController.getString(R.string.BotOwnershipTransferChangeOwner), new SendMessagesHelper$$ExternalSyntheticLambda54(this, r37, r30, r31, r32));
        r74.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r32.showDialog(r74.create());
        return;
    L184:
        return;
    L119:
        if ("PASSWORD_MISSING".equals(r35.text) == false) goto L121;
    L132:
        if (r33 == null) goto L134;
        r33.needHideProgress();
    L134:
        AlertDialog.Builder r07 = new AlertDialog.Builder(r32.getParentActivity());
        r07.setTitle(LocaleController.getString(R.string.EditAdminTransferAlertTitle));
        LinearLayout r1 = new LinearLayout(r32.getParentActivity());
        r1.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(24.0f), 0);
        r1.setOrientation(1);
        r07.setView(r1);
        TextView r310 = new TextView(r32.getParentActivity());
        int r42 = Theme.key_dialogTextBlack;
        r310.setTextColor(Theme.getColor(r42));
        r310.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L137;
        int r112 = 5;
    L138:
        r310.setGravity(r112 | 48);
        r310.setText(AndroidUtilities.replaceTags(LocaleController.formatString("BotOwnershipTransferAlertText", R.string.BotOwnershipTransferAlertText, new Object[0])));
        r1.addView(r310, LayoutHelper.createLinear(-1, -2));
        LinearLayout r311 = new LinearLayout(r32.getParentActivity());
        r311.setOrientation(0);
        r1.addView(r311, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r15 = new ImageView(r32.getParentActivity());
        r15.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L141;
        int r12 = AndroidUtilities.dp(11.0f);
    L142:
        int r92 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L145;
        int r133 = 0;
    L146:
        r15.setPadding(r12, r92, r133, 0);
        int r122 = Theme.getColor(r42);
        PorterDuff.Mode r134 = PorterDuff.Mode.MULTIPLY;
        r15.setColorFilter(new PorterDuffColorFilter(r122, r134));
        TextView r93 = new TextView(r32.getParentActivity());
        r93.setTextColor(Theme.getColor(r42));
        r93.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L149;
        int r123 = 5;
    L150:
        r93.setGravity(r123 | 48);
        r93.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText1)));
        if (LocaleController.isRTL == false) goto L153;
        r311.addView(r93, LayoutHelper.createLinear(-1, -2));
        r311.addView(r15, LayoutHelper.createLinear(-2, -2, 5));
    L154:
        LinearLayout r312 = new LinearLayout(r32.getParentActivity());
        r312.setOrientation(0);
        r1.addView(r312, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r94 = new ImageView(r32.getParentActivity());
        r94.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L157;
        int r124 = AndroidUtilities.dp(11.0f);
    L158:
        int r152 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L161;
        int r113 = 0;
    L162:
        r94.setPadding(r124, r152, r113, 0);
        r94.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r42), r134));
        TextView r8 = new TextView(r32.getParentActivity());
        r8.setTextColor(Theme.getColor(r42));
        r8.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L165;
        int r114 = 5;
    L166:
        r8.setGravity(r114 | 48);
        r8.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText2)));
        if (LocaleController.isRTL == false) goto L169;
        r312.addView(r8, LayoutHelper.createLinear(-1, -2));
        int r125 = 5;
        r312.addView(r94, LayoutHelper.createLinear(-2, -2, 5));
    L171:
        if ("PASSWORD_MISSING".equals(r35.text) == false) goto L173;
        r07.setPositiveButton(LocaleController.getString(R.string.EditAdminTransferSetPassword), new SendMessagesHelper$$ExternalSyntheticLambda55(r32));
        r07.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    L178:
        r32.showDialog(r07.create());
        return;
    L173:
        TextView r23 = new TextView(r32.getParentActivity());
        r23.setTextColor(Theme.getColor(r42));
        r23.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == true) goto L177;
        r125 = 3;
    L177:
        r23.setGravity(r125 | 48);
        r23.setText(LocaleController.getString(R.string.EditAdminTransferAlertText3));
        r1.addView(r23, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        r07.setNegativeButton(LocaleController.getString(R.string.OK), null);
        goto L178
    L169:
        r125 = 5;
        r312.addView(r94, LayoutHelper.createLinear(-2, -2));
        r312.addView(r8, LayoutHelper.createLinear(-1, -2));
        goto L171
    L165:
        r114 = 3;
        goto L166
    L161:
        r113 = AndroidUtilities.dp(11.0f);
        goto L162
    L157:
        r124 = 0;
        goto L158
    L153:
        r311.addView(r15, LayoutHelper.createLinear(-2, -2));
        r311.addView(r93, LayoutHelper.createLinear(-1, -2));
        goto L154
    L149:
        r123 = 3;
        goto L150
    L145:
        r133 = AndroidUtilities.dp(11.0f);
        goto L146
    L141:
        r12 = 0;
        goto L142
    L137:
        r112 = 3;
        goto L138
    L121:
        if (r35.text.startsWith("PASSWORD_TOO_FRESH_") == true) goto L132;
        if (r35.text.startsWith("SESSION_TOO_FRESH_") == true) goto L132;
        if ("SRP_ID_INVALID".equals(r35.text) == false) goto L129;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getPassword(), new SendMessagesHelper$$ExternalSyntheticLambda56(this, r33, r37, r30, r31, r32), 8);
        return;
    L129:
        if (r33 == null) goto L185;
        r33.needHideProgress();
        r33.finishFragment();
        return;
    }

    private /* synthetic */ void lambda$sendCallback$46(String r15, List r16, boolean r17, MessageObject r18, TL_keyboard.KeyboardButtonProto r19, ChatActivity r20, TwoStepVerificationActivity r21, TLObject[] r22, TLRPC.InputCheckPasswordSRP r23, boolean r24, TLObject r25, TLRPC.TL_error r26) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda41(this, r15, r16, r17, r25, r18, r19, r20, r21, r22, r26, r23, r24));
    }

    private /* synthetic */ void lambda$sendEditRichMessageRequest$22(MessageObject r2) {
        onRichEditFinished(r2, false);
    }

    private /* synthetic */ void lambda$sendEditRichMessageRequest$23(TLRPC.TL_messages_editMessage r1, MessageObject r2, BaseFragment r3, boolean r4) {
        sendEditRichMessageRequest(r1, r2, r3, r4);
    }

    private /* synthetic */ void lambda$sendEditRichMessageRequest$24(MessageObject r2) {
        onRichEditFinished(r2, true);
    }

    private /* synthetic */ void lambda$sendEditRichMessageRequest$25(TLRPC.TL_error r2, BaseFragment r3, TLRPC.TL_messages_editMessage r4) {
        AlertsCreator.processError(this.currentAccount, r2, r3, r4, new Object[0]);
    }

    private /* synthetic */ void lambda$sendEditRichMessageRequest$26(boolean r7, MessageObject r8, TLRPC.TL_messages_editMessage r9, BaseFragment r10, TLObject r11, TLRPC.TL_error r12) {
        if (r12 != null) goto L9;
        if (r7 == false) goto L6;
        if (r8 == null) goto L6;
        r8.richCheckboxEcho = true;
    L6:
        getMessagesController().processUpdates((TLRPC.Updates) r11, false);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda112(this, r8));
        return;
    L9:
        if (FileRefController.isFileRefError(r12.text) == false) goto L13;
        SendMessagesHelper r1 = this;
        MessageObject r3 = r8;
        TLRPC.TL_messages_editMessage r2 = r9;
        BaseFragment r4 = r10;
        if (r1.requestRichMessageFileReference(r3, r2, r12.text, new SendMessagesHelper$$ExternalSyntheticLambda113(r1, r2, r3, r4, r7)) == true) goto L19;
    L14:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda114(r1, r3));
        if (r4 == null) goto L18;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda115(r1, r12, r4, r2));
        return;
    L18:
        return;
    L19:
        return;
    L13:
        r1 = this;
        r3 = r8;
        r2 = r9;
        r4 = r10;
        goto L14
    }

    private /* synthetic */ void lambda$sendGame$47(long r2, TLObject r4, TLRPC.TL_error r5) {
        if (r5 != null) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r4, false);
    L5:
        if (r2 == 0) goto L8;
        getMessagesStorage().removePendingTask(r2);
        return;
    }

    private /* synthetic */ void lambda$sendMessage$10(ArrayList r12, int r13, int r14, TLRPC.Message r15, int r16, TLRPC.Message r17, MessageObject r18, int r19) {
        getMessagesStorage().getStorageQueue().postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda28(this, r12, r13, r14, r15, r16, r17, r18, r19));
    }

    private /* synthetic */ void lambda$sendMessage$11(long r1, ArrayList r3) {
        getMessagesController().markDialogMessageAsDeleted(r1, r3);
    }

    private /* synthetic */ void lambda$sendMessage$12(TLRPC.Message r14, long r15, int r17, TLRPC.Message r18, int r19, int r20) {
        boolean r8 = false;
        r14.send_state = 0;
        getMediaDataController().increasePeerRaiting(r15);
        NotificationCenter r142 = getNotificationCenter();
        int r11 = NotificationCenter.messageReceivedByServer;
        Integer r1 = Integer.valueOf(r17);
        Integer r2 = Integer.valueOf(r18.id);
        Long r4 = Long.valueOf(r15);
        Integer r6 = Integer.valueOf(r19);
        if (r20 == 0) goto L5;
        boolean r7 = true;
    L6:
        r142.postNotificationName(r11, new Object[]{r1, r2, r18, r4, 0L, r6, Boolean.valueOf(r7)});
        NotificationCenter r143 = getNotificationCenter();
        int r112 = NotificationCenter.messageReceivedByServer2;
        Integer r12 = Integer.valueOf(r17);
        Integer r22 = Integer.valueOf(r18.id);
        Long r42 = Long.valueOf(r15);
        Integer r62 = Integer.valueOf(r19);
        if (r20 == 0) goto L9;
        boolean r72 = true;
    L10:
        r143.postNotificationName(r112, new Object[]{r12, r22, r18, r42, 0L, r62, Boolean.valueOf(r72)});
        processSentMessage(r17);
        if (r20 == 0) goto L13;
        r8 = true;
    L13:
        removeFromSendingMessages(r17, r8);
        return;
    L9:
        r72 = false;
        goto L10
    L5:
        r7 = false;
        goto L6
    }

    private /* synthetic */ void lambda$sendMessage$13(int r26, TLRPC.Message r27, TLRPC.Message r28, TLRPC.Peer r29, int r30, ArrayList r31, long r32, int r34) {
        if (r26 == 0) goto L5;
        int r4 = 1;
    L7:
        if (MessageObject.isWelcomeMessage(r27) == false) goto L11;
        r4 = 9;
    L9:
        int r13 = r4;
        MessagesStorage r14 = getMessagesStorage();
        long r42 = r28.random_id;
        long r17 = MessageObject.getPeerId(r29);
        Integer r19 = Integer.valueOf(r30);
        int r7 = r28.id;
        if (r26 == 0) goto L18;
        int r23 = 1;
    L19:
        r14.updateMessageStateAndId(r42, r17, r19, r7, 0, false, r23, r27.quick_reply_shortcut_id);
        getMessagesStorage().putMessages(r31, true, false, false, 0, r13, r27.quick_reply_shortcut_id);
        if (MessageObject.isEphemeralAndNotWelcome(r28) == false) goto L22;
        long r8 = MessageObject.getPeerId(r28.peer_id);
        ArrayList<Integer> r10 = new ArrayList(1);
        r10.add(Integer.valueOf(r28.id));
        getMessagesStorage().markMessagesAsDeleted(r8, r10, false, false, r13, (int) MessageObject.getTopicId(this.currentAccount, r28, 0));
        getMessagesStorage().updateDialogsWithDeletedMessages(r8, -r8, r10, null);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda117(this, r8, r10));
    L22:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda118(this, r28, r32, r30, r27, r34, r26));
        return;
    L18:
        r23 = 0;
        goto L19
    L11:
        if (r27.quick_reply_shortcut_id == 0) goto L13;
    L14:
        r4 = 5;
        goto L9
    L13:
        if (r27.quick_reply_shortcut == null) goto L9;
    L5:
        r4 = 0;
        goto L7
    }

    private /* synthetic */ void lambda$sendMessage$14(TLRPC.TL_error r3, TLRPC.TL_messages_forwardMessages r4) {
        AlertsCreator.processError(this.currentAccount, r3, null, r4, new Object[0]);
    }

    private /* synthetic */ void lambda$sendMessage$15(TLRPC.Message r4, int r5) {
        r4.send_state = 2;
        getNotificationCenter().postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(r4.id)});
        processSentMessage(r4.id);
        int r42 = r4.id;
        if (r5 == 0) goto L5;
        boolean r52 = true;
    L6:
        removeFromSendingMessages(r42, r52);
        return;
    L5:
        r52 = false;
        goto L6
    }

    private /* synthetic */ void lambda$sendMessage$16(ArrayList r1) {
        StarsController.getInstance(this.currentAccount).showPriceChangedToast(r1);
    }

    private /* synthetic */ void lambda$sendMessage$17(long r24, int r26, boolean r27, boolean r28, LongSparseArray r29, ArrayList r30, ArrayList r31, MessageObject r32, TLRPC.Peer r33, TLRPC.TL_messages_forwardMessages r34, TLObject r35, TLRPC.TL_error r36) {
        SendMessagesHelper r1 = this;
        ArrayList r12 = r31;
        boolean r15 = true;
        if (r36 != null) goto L88;
        SparseLongArray r7 = new SparseLongArray();
        TLRPC.Updates r8 = (TLRPC.Updates) r35;
        int r0 = 0;
    L6:
        if (r0 >= r8.updates.size()) goto L11;
        TLRPC.Update r2 = r8.updates.get(r0);
        if ((r2 instanceof TL_update.TL_updateMessageID) == false) goto L10;
        TL_update.TL_updateMessageID r22 = (TL_update.TL_updateMessageID) r2;
        r7.put(r22.id, r22.random_id);
        r8.updates.remove(r0);
        r0 = r0 - 1;
    L10:
        r0 = r0 + 1;
        goto L6
    L11:
        r1.getNotificationCenter().postNotificationNameOnUIThread(NotificationCenter.savedMessagesForwarded, new Object[]{r7});
        Integer r02 = r1.getMessagesController().dialogs_read_outbox_max.get(Long.valueOf(r24));
        if (r02 != null) goto L14;
        r02 = Integer.valueOf(r1.getMessagesStorage().getDialogReadMax(true, r24));
        r1.getMessagesController().dialogs_read_outbox_max.put(Long.valueOf(r24), r02);
    L14:
        Integer r16 = r02;
        ArrayList<TL_ephemeral.EphemeralMessage> r9 = new ArrayList();
        int r03 = 0;
        int r4 = 0;
    L18:
        if (r03 >= r8.updates.size()) goto L80;
        TLRPC.Update r5 = r8.updates.get(r03);
        boolean r6 = r5 instanceof TL_update.TL_updateNewMessage;
        if (r6 == false) goto L22;
    L30:
        if (r26 == 0) goto L32;
        int r152 = 1;
    L33:
        r8.updates.remove(r03);
        int r18 = r03 - 1;
        if (r6 == false) goto L38;
        TL_update.TL_updateNewMessage r52 = (TL_update.TL_updateNewMessage) r5;
        TLRPC.Message r62 = r52.message;
        r1.getMessagesController().processNewDifferenceParams(-1, r52.pts, -1, r52.pts_count);
    L36:
        TLRPC.Message r23 = r62;
        int r04 = 0;
    L47:
        if (r27 == true) goto L49;
    L51:
        int r10 = r04;
    L52:
        ImageLoader.saveMessageThumbs(r23);
        if (r10 == 0) goto L55;
    L59:
        if (r28 == false) goto L62;
        r23.out = true;
        r23.unread = false;
        r23.media_unread = false;
    L62:
        long r53 = r7.get(r23.id);
        if (r53 == 0) goto L66;
        TLRPC.Message r3 = (TLRPC.Message) r29.get(r53);
        if (r3 == null) goto L66;
        int r54 = r30.indexOf(r3);
        if (r54 == (-1)) goto L66;
        MessageObject r63 = (MessageObject) r12.get(r54);
        r30.remove(r54);
        r12.remove(r54);
        int r55 = r3.id;
        int r352 = r4;
        ArrayList r42 = new ArrayList();
        r42.add(r23);
        TLRPC.Message r05 = r63.messageOwner;
        r05.post_author = r23.post_author;
        if ((r23.flags & 33554432) == 0) goto L73;
        r05.ttl_period = r23.ttl_period;
        r05.flags |= 33554432;
    L73:
        SparseLongArray r17 = r7;
        TLRPC.Updates r353 = r8;
        updateMediaPaths(r63, r23, r23.id, null, true, r23.params);
        int r06 = r63.getMediaExistanceFlags();
        r3.id = r23.id;
        int r222 = r352 + 1;
        if (r152 == r10) goto L76;
        r1 = this;
        int r64 = r152;
        TLRPC.Updates r153 = r353;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda79(r1, r42, r10, r55, r3, r64, r23, r32, r26));
        ArrayList<TL_ephemeral.EphemeralMessage> r354 = r9;
    L77:
        r03 = r18;
        r4 = r222;
    L79:
        r03 = r03 + 1;
        r12 = r31;
        r9 = r354;
        r8 = r153;
        r7 = r17;
        r15 = true;
        goto L18
    L76:
        r153 = r353;
        r354 = r9;
        r1 = this;
        getMessagesStorage().getStorageQueue().postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda80(r1, r26, r23, r3, r33, r55, r42, r24, r06));
    L66:
        r1 = this;
        r17 = r7;
        r153 = r8;
        r354 = r9;
        r4 = r4;
        r03 = r18;
        goto L79
    L55:
        if (r16.intValue() >= r23.id) goto L57;
        boolean r07 = true;
    L58:
        r23.unread = r07;
        goto L59
    L57:
        r07 = false;
        goto L58
    L49:
        if (r23.date == 2147483646) goto L51;
        r10 = 0;
        goto L52
    L38:
        if ((r5 instanceof TL_update.TL_updateNewScheduledMessage) == false) goto L41;
        r23 = ((TL_update.TL_updateNewScheduledMessage) r5).message;
        r04 = 1;
        goto L47
    L41:
        if ((r5 instanceof TL_update.TL_updateQuickReplyMessage) == false) goto L44;
        QuickRepliesController.getInstance(r1.currentAccount).processUpdate(r5, null, 0);
        r62 = ((TL_update.TL_updateQuickReplyMessage) r5).message;
        goto L36
    L44:
        if ((r5 instanceof TL_update.TL_updateNewEphemeralMessage) == false) goto L46;
        TL_update.TL_updateNewEphemeralMessage r56 = (TL_update.TL_updateNewEphemeralMessage) r5;
        r62 = EphemeralMessagesHelper.convertEphemeralToFakeDefault(r56.message);
        r9.add(r56.message);
        goto L36
    L46:
        TL_update.TL_updateNewChannelMessage r57 = (TL_update.TL_updateNewChannelMessage) r5;
        r62 = r57.message;
        r1.getMessagesController().processNewChannelDifferenceParams(r57.pts, r57.pts_count, r62.peer_id.channel_id);
        goto L36
    L32:
        r152 = 0;
        goto L33
    L22:
        if ((r5 instanceof TL_update.TL_updateNewChannelMessage) == true) goto L30;
        if ((r5 instanceof TL_update.TL_updateNewScheduledMessage) == true) goto L30;
        if ((r5 instanceof TL_update.TL_updateQuickReplyMessage) == true) goto L30;
        r17 = r7;
        r153 = r8;
        r354 = r9;
        goto L79
    L80:
        int r25 = r26;
        int r72 = r4;
        ArrayList<TL_ephemeral.EphemeralMessage> r355 = r9;
        boolean r37 = r15;
        TLRPC.Updates r154 = r8;
        if (r355.isEmpty() == true) goto L84;
        r1.getMessagesStorage().putEphemeralMessages(r355, r37);
    L84:
        if (r154.updates.isEmpty() == true) goto L86;
        int r65 = 0;
        r1.getMessagesController().processUpdates(r154, false);
    L87:
        int r43 = 1;
        r1.getStatsController().incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(), 1, r72);
    L89:
        int r102 = r65;
    L91:
        if (r102 >= r30.size()) goto L104;
        TLRPC.Message r08 = (TLRPC.Message) r30.get(r102);
        MessagesStorage r58 = r1.getMessagesStorage();
        if (r25 == 0) goto L95;
        int r73 = r43;
    L96:
        r58.markMessageAsSendError(r08, r73);
        if (r36 == null) goto L103;
        String r59 = r36.text;
        if (r59 == null) goto L103;
        if (r59.startsWith("ALLOW_PAYMENT_REQUIRED_") == false) goto L103;
        StarsController.getInstance(r1.currentAccount);
        r08.errorAllowedPriceStars = StarsController.getAllowedPaidStars(r34);
        r08.errorNewPriceStars = Long.parseLong(r36.text.substring(23)) / r34.id.size();
        r1.getMessagesStorage().updateMessageCustomParams(MessageObject.getDialogId(r08), r08);
    L103:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda82(r1, r08, r25));
        r102 = r102 + 1;
        r43 = 1;
        goto L91
    L95:
        r73 = r65;
        goto L96
    L104:
        if (r36 == null) goto L120;
        String r09 = r36.text;
        if (r09 != null) goto L108;
        return;
    L108:
        if (r09.startsWith("ALLOW_PAYMENT_REQUIRED_") == false) goto L122;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda83(r1, r31));
        return;
    L122:
        return;
    L120:
        return;
    L86:
        r65 = 0;
        goto L87
    L88:
        r25 = r26;
        r43 = 1;
        r65 = 0;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda81(r1, r36, r34));
        goto L89
    }

    private /* synthetic */ void lambda$sendMessage$18(TLRPC.TL_messages_forwardMessages r15, long r16, int r18, boolean r19, boolean r20, LongSparseArray r21, ArrayList r22, ArrayList r23, MessageObject r24, TLRPC.Peer r25) {
        getConnectionsManager().sendRequest(r15, new SendMessagesHelper$$ExternalSyntheticLambda71(this, r16, r18, r19, r20, r21, r22, r23, r24, r25, r15), 68);
    }

    private /* synthetic */ void lambda$sendMessage$19(TLRPC.TL_messages_forwardMessages r1, ArrayList r2, Runnable r3) {
        if (BotForumHelper.getInstance(this.currentAccount).beforeSendingFinalRequest(r1, r2, r3) == false) goto L6;
        r3.run();
        return;
    }

    private /* synthetic */ void lambda$sendMessage$48(SendMessageParams r3, Long r4) {
        r3.payStars = r4.longValue();
        sendMessage(r3);
    }

    private /* synthetic */ void lambda$sendMessage$7(ArrayList r17, long r18, boolean r20, boolean r21, boolean r22, int r23, int r24, MessageObject r25, int r26, long r27, MessageSuggestionParams r29, Long r30) {
        sendMessage(r17, r18, r20, r21, r22, r23, r24, r25, r26, r30.longValue(), r27, r29);
    }

    private /* synthetic */ void lambda$sendMessage$8(int r20, TLRPC.Message r21, int r22, int r23, TLRPC.Message r24, MessageObject r25, int r26) {
        ArrayList<Integer> r4 = new ArrayList();
        r4.add(Integer.valueOf(r20));
        MessagesController r3 = getMessagesController();
        long r7 = r21.dialog_id;
        if (r23 != 1) goto L6;
        boolean r16 = true;
    L7:
        boolean r1 = true;
        r3.deleteMessages(r4, null, null, r7, false, r22, false, 0, null, 0, r16, r24.id);
        ArrayList<MessageObject> r32 = new ArrayList();
        r32.add(new MessageObject(r25.currentAccount, r25.messageOwner, true, true));
        getMessagesController().updateInterfaceWithMessages(r21.dialog_id, r32, r23);
        getMediaDataController().increasePeerRaiting(r21.dialog_id);
        processSentMessage(r20);
        if (r26 != 0) goto L11;
        r1 = false;
    L11:
        removeFromSendingMessages(r20, r1);
        return;
    L6:
        r16 = false;
        goto L7
    }

    private /* synthetic */ void lambda$sendMessage$9(ArrayList r19, int r20, int r21, TLRPC.Message r22, int r23, TLRPC.Message r24, MessageObject r25, int r26) {
        getMessagesStorage().putMessages(r19, true, false, false, 0, r20, 0);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda92(this, r21, r22, r23, r20, r24, r25, r26));
    }

    private /* synthetic */ void lambda$sendNotificationCallback$28(String r1, List r2) {
        this.waitingForCallback.remove(r1);
        r2.remove(r1);
    }

    private /* synthetic */ void lambda$sendNotificationCallback$29(String r1, List r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda40(this, r1, r2));
    }

    private /* synthetic */ void lambda$sendNotificationCallback$30(long r13, int r15, byte[] r16) {
        String r5 = r13 + "_" + r15 + "_" + Utilities.bytesToHex(r16) + "_0";
        this.waitingForCallback.put(r5, Boolean.TRUE);
        List<String> r7 = this.waitingForCallbackMap.get(r13 + "_" + r15);
        if (r7 != null) goto L5;
        ArrayList r8 = new ArrayList();
        this.waitingForCallbackMap.put(r13 + "_" + r15, r8);
        r7 = r8;
    L5:
        r7.add(r5);
        if (DialogObject.isUserDialog(r13) == true) goto L8;
        long r9 = -r13;
        if (getMessagesController().getChat(Long.valueOf(r9)) != null) goto L17;
        TLRPC.Chat r6 = getMessagesStorage().getChatSync(r9);
        if (r6 == null) goto L17;
        getMessagesController().putChat(r6, true);
    L17:
        TLRPC.TL_messages_getBotCallbackAnswer r62 = new TLRPC.TL_messages_getBotCallbackAnswer();
        r62.peer = getMessagesController().getInputPeer(r13);
        r62.msg_id = r15;
        r62.game = false;
        if (r16 == null) goto L20;
        r62.flags = 1 | r62.flags;
        r62.data = r16;
    L20:
        getConnectionsManager().sendRequest(r62, new SendMessagesHelper$$ExternalSyntheticLambda36(this, r5, r7), 2);
        getMessagesController().markDialogAsRead(r13, r15, r15, 0, false, 0, 0, true, 0);
        return;
    L8:
        if (getMessagesController().getUser(Long.valueOf(r13)) != null) goto L17;
        TLRPC.User r63 = getMessagesStorage().getUserSync(r13);
        if (r63 == null) goto L17;
        getMessagesController().putUser(r63, true);
        goto L17
    }

    private /* synthetic */ void lambda$sendReaction$35(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        if (r2 == null) goto L7;
        getMessagesController().processUpdates((TLRPC.Updates) r2, false);
        if (r1 == null) goto L8;
        AndroidUtilities.runOnUIThread(r1);
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$sendSticker$5(Bitmap[] r23, String[] r24, TLRPC.Document r25, VideoEditedInfo r26, long r27, MessageObject r29, MessageObject r30, boolean r31, int r32, int r33, Object r34, MessageObject.SendAnimationData r35, TL_stories.StoryItem r36, ChatActivity.ReplyQuote r37, SendMessageChatArguments r38, long r39, long r41, MessageSuggestionParams r43, CharSequence r44, boolean r45) {
        if (r23[0] != null) goto L5;
    L7:
        SendMessageParams r0 = SendMessageParams.of((TLRPC.TL_document) r25, r26, null, r27, r29, r30, null, null, null, null, r31, r32, r33, 0, r34, r35, false);
        r0.replyToStoryItem = r36;
        r0.replyQuote = r37;
        r0.sendMessageChatArguments = r38;
        r0.payStars = r39;
        r0.monoForumPeer = r41;
        r0.suggestionParams = r43;
        if (r44 == null) goto L10;
        String r1 = r44.toString();
    L11:
        r0.caption = r1;
        r0.invert_media = r45;
        sendMessage(r0);
        return;
    L10:
        r1 = null;
        goto L11
    L5:
        if (r24[0] == null) goto L7;
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r23[0]), r24[0], false);
        goto L7
    }

    private /* synthetic */ void lambda$sendSticker$6(TLRPC.Document r26, VideoEditedInfo r27, long r28, MessageObject r30, MessageObject r31, boolean r32, int r33, int r34, Object r35, MessageObject.SendAnimationData r36, TL_stories.StoryItem r37, ChatActivity.ReplyQuote r38, SendMessageChatArguments r39, long r40, long r42, MessageSuggestionParams r44, CharSequence r45, boolean r46) {
        Bitmap[] r8 = new Bitmap[1];
        String r0 = ImageLocation.getForDocument(r26).getKey(null, null, false);
        if ("video/mp4".equals(r26.mime_type) == false) goto L6;
        String r1 = ".mp4";
    L9:
        File r3 = new File(FileLoader.getDirectory(3), r0 + r1);
        if (r3.exists() == true) goto L12;
        r3 = new File(FileLoader.getDirectory(2), r0 + r1);
    L12:
        ensureMediaThumbExists(getAccountInstance(), false, r26, r3.getAbsolutePath(), null, 0);
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda124(this, r8, new String[]{getKeyForPhotoSize(getAccountInstance(), FileLoader.getClosestPhotoSizeWithSize(r26.thumbs, 320), r8, true, true)}, r26, r27, r28, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r42, r44, r45, r46));
        return;
    L6:
        if ("video/x-matroska".equals(r26.mime_type) == false) goto L8;
        r1 = ".mkv";
        goto L9
    L8:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    private /* synthetic */ void lambda$sendVote$31(String r1, Runnable r2) {
        this.waitingForVote.remove(r1);
        if (r2 == null) goto L6;
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$sendVote$32(MessageObject r5, boolean r6, String r7, Runnable r8, TLObject r9, TLRPC.TL_error r10) {
        if (r10 != null) goto L7;
        this.voteSendTime.put(r5.getPollId(), 0L);
        if (r6 == false) goto L6;
        getMessagesController().processUpdates((TLRPC.Updates) r9, false);
    L6:
        this.voteSendTime.put(r5.getPollId(), Long.valueOf(SystemClock.elapsedRealtime()));
    L7:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda21(this, r7, r8));
    }

    private /* synthetic */ void lambda$toggleTodo$33(int r3, boolean r4, Runnable r5) {
        Boolean r0 = this.waitingForTodoUpdate.get(Integer.valueOf(r3));
        if (r0 != null) goto L5;
    L7:
        if (r5 == null) goto L10;
        r5.run();
        return;
    L10:
        return;
    L5:
        if (r0.booleanValue() != r4) goto L7;
        this.waitingForTodoUpdate.remove(Integer.valueOf(r3));
        goto L7
    }

    private /* synthetic */ void lambda$toggleTodo$34(MessageObject r9, TLRPC.TodoItem r10, boolean r11, long r12, int r14, Runnable r15, TLObject r16, TLRPC.TL_error r17) {
        if (r17 != null) goto L4;
        getMessagesStorage().toggleTodo(r9.getDialogId(), r9.getId(), r10.id, r11, r12);
        getMessagesController().processUpdates((TLRPC.Updates) r16, false);
    L4:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda91(this, r14, r11, r15));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$uploadMultiMedia$59(TLObject r7, TLRPC.InputMedia r8, DelayedMessage r9) {
        if (r7 == null) goto L26;
        TLRPC.MessageMedia r72 = (TLRPC.MessageMedia) r7;
        if ((r8 instanceof TLRPC.TL_inputMediaUploadedPhoto) == false) goto L17;
        if ((r72 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L17;
        TLRPC.TL_inputMediaUploadedPhoto r1 = (TLRPC.TL_inputMediaUploadedPhoto) r8;
        TLRPC.TL_inputMediaPhoto r2 = new TLRPC.TL_inputMediaPhoto();
        TLRPC.TL_inputPhoto r3 = new TLRPC.TL_inputPhoto();
        r2.id = r3;
        TLRPC.Photo r73 = r72.photo;
        r3.id = r73.id;
        r3.access_hash = r73.access_hash;
        r3.file_reference = r73.file_reference;
        r2.spoiler = r8.spoiler;
        if (r1.live_photo == false) goto L13;
        TLRPC.InputDocument r74 = r1.video;
        if (r74 == null) goto L13;
        r2.live_photo = true;
        r2.video = r74;
    L13:
        TLRPC.TL_inputMediaDocument r22 = r2;
        if (BuildVars.DEBUG_VERSION == false) goto L27;
        FileLog.d("set uploaded photo");
        r22 = r2;
    L27:
        if (r22 == null) goto L74;
        int r75 = r8.ttl_seconds;
        if (r75 == 0) goto L31;
        r22.ttl_seconds = r75;
        r22.flags |= 1;
    L31:
        TLObject r76 = r9.sendRequest;
        if ((r76 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L41;
        TLRPC.TL_messages_sendMultiMedia r77 = (TLRPC.TL_messages_sendMultiMedia) r76;
        int r12 = 0;
    L35:
        if (r12 >= r77.multi_media.size()) goto L72;
        if (r77.multi_media.get(r12).media == r8) goto L38;
        r12 = r12 + 1;
        goto L35
    L38:
        r77.multi_media.get(r12).media = r22;
    L72:
        sendReadyToSendGroup(r9, false, true);
        return;
    L41:
        if ((r76 instanceof TLRPC.TL_messages_sendMedia) == false) goto L52;
        if ((((TLRPC.TL_messages_sendMedia) r76).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L52;
        TLRPC.TL_inputMediaPaidMedia r78 = (TLRPC.TL_inputMediaPaidMedia) ((TLRPC.TL_messages_sendMedia) r76).media;
        int r13 = 0;
    L46:
        if (r13 >= r78.extended_media.size()) goto L72;
        if (r78.extended_media.get(r13) == r8) goto L49;
        r13 = r13 + 1;
        goto L46
    L49:
        r78.extended_media.set(r13, r22);
    L52:
        if ((r76 instanceof TLRPC.TL_messages_sendMedia) == false) goto L57;
        if ((((TLRPC.TL_messages_sendMedia) r76).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L57;
        TLRPC.TL_inputMediaPoll r79 = (TLRPC.TL_inputMediaPoll) ((TLRPC.TL_messages_sendMedia) r76).media;
        PollAttachedMediaPack.setInputMedia(r79, PollAttachedMediaPack.findInputMedia(r79, r8), r22);
    L57:
        if ((r76 instanceof TL_ephemeral.TL_sendMessage) == false) goto L68;
        if ((((TL_ephemeral.TL_sendMessage) r76).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L68;
        TLRPC.TL_inputMediaPaidMedia r710 = (TLRPC.TL_inputMediaPaidMedia) ((TL_ephemeral.TL_sendMessage) r76).media;
        int r14 = 0;
    L62:
        if (r14 >= r710.extended_media.size()) goto L72;
        if (r710.extended_media.get(r14) == r8) goto L65;
        r14 = r14 + 1;
        goto L62
    L65:
        r710.extended_media.set(r14, r22);
    L68:
        if ((r76 instanceof TL_ephemeral.TL_sendMessage) == false) goto L72;
        if ((((TL_ephemeral.TL_sendMessage) r76).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L72;
        TLRPC.TL_inputMediaPoll r711 = (TLRPC.TL_inputMediaPoll) ((TL_ephemeral.TL_sendMessage) r76).media;
        PollAttachedMediaPack.setInputMedia(r711, PollAttachedMediaPack.findInputMedia(r711, r8), r22);
        goto L72
    L74:
        r9.markAsError();
        return;
    L17:
        if ((r8 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L26;
        if ((r72 instanceof TLRPC.TL_messageMediaDocument) == false) goto L26;
        TLRPC.TL_inputMediaDocument r23 = new TLRPC.TL_inputMediaDocument();
        TLRPC.TL_inputDocument r15 = new TLRPC.TL_inputDocument();
        r23.id = r15;
        TLRPC.Document r32 = r72.document;
        r15.id = r32.id;
        r15.access_hash = r32.access_hash;
        r15.file_reference = r32.file_reference;
        r23.spoiler = r8.spoiler;
        TLRPC.Photo r712 = r72.video_cover;
        if (r712 == null) goto L23;
        TLRPC.TL_inputPhoto r16 = new TLRPC.TL_inputPhoto();
        r16.id = r712.id;
        r16.access_hash = r712.access_hash;
        r16.file_reference = r712.file_reference;
        r23.flags |= 8;
        r23.video_cover = r16;
    L23:
        r22 = r23;
        if (BuildVars.DEBUG_VERSION == false) goto L27;
        FileLog.d("set uploaded document");
        r22 = r23;
    L26:
        r22 = null;
        goto L27
    }

    private /* synthetic */ void lambda$uploadMultiMedia$60(TLRPC.InputMedia r1, DelayedMessage r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda35(this, r3, r1, r2));
    }

    private void notifyRichMessageEditing(MessageObject r5) {
        ArrayList r0 = new ArrayList();
        r0.add(r5);
        getNotificationCenter().postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(r5.getDialogId()), r0});
    }

    private void onRichEditFinished(MessageObject r4, boolean r5) {
        if (r4 == null) goto L12;
        TLRPC.Message r0 = r4.messageOwner;
        if (r0 == null) goto L15;
        r0.reqId = 0;
        if (r5 == true) goto L9;
        return;
    L9:
        if (r0.send_state != 3) goto L14;
        r0.send_state = 0;
        notifyRichMessageEditing(r4);
        return;
    L14:
        return;
    L15:
        return;
    }

    private void performSendDelayedMessage(DelayedMessage r2) {
        performSendDelayedMessage(r2, -1);
    }

    private void performSendDelayedMessage(DelayedMessage r28, int r29) {
        SendMessagesHelper r1 = this;
        DelayedMessage r3 = r28;
        int r0 = r3.type;
        boolean r7 = false;
        boolean r8 = true;
        if (r0 != 0) goto L28;
        String r02 = r3.httpLocation;
        if (r02 == null) goto L8;
        r1.putToDelayedMessages(r02, r3);
        ImageLoader.getInstance().loadHttpFile(r3.httpLocation, "file", r1.currentAccount);
        return;
    L8:
        TLObject r03 = r3.sendRequest;
        int r2 = r1.currentAccount;
        if (r03 == null) goto L12;
        String r04 = FileLoader.getInstance(r2).getPathToAttach(r3.photoSize).toString();
        r1.putToDelayedMessages(r04, r3);
        r1.getFileLoader().uploadFile(r04, false, true, 16777216);
        r1.putToUploadingMessages(r3.obj);
        return;
    L12:
        String r05 = FileLoader.getInstance(r2).getPathToAttach(r3.photoSize).toString();
        if (r3.sendEncryptedRequest == null) goto L25;
        TLRPC.PhotoSize r22 = r3.photoSize;
        if (r22 == null) goto L25;
        if (r22.location.dc_id == 0) goto L25;
        File r23 = new File(r05);
        if (r23.exists() == true) goto L22;
        r05 = FileLoader.getInstance(r1.currentAccount).getPathToAttach(r3.photoSize, true).toString();
        r23 = new File(r05);
    L22:
        if (r23.exists() == true) goto L25;
        r1.putToDelayedMessages(FileLoader.getAttachFileName(r3.photoSize), r3);
        r1.getFileLoader().loadFile(ImageLocation.getForObject(r3.photoSize, r3.locationParent), r3.parentObject, "jpg", 3, 0);
        return;
    L25:
        r1.putToDelayedMessages(r05, r3);
        r1.getFileLoader().uploadFile(r05, true, true, 16777216);
        r1.putToUploadingMessages(r3.obj);
        return;
    L28:
        if (r0 != 1) goto L190;
        VideoEditedInfo r06 = r3.videoEditedInfo;
        if (r06 != null) goto L32;
    L47:
        VideoEditedInfo r07 = r3.videoEditedInfo;
        if (r07 == null) goto L66;
        TLRPC.InputFile r15 = r07.file;
        if (r15 == null) goto L62;
        int r16 = 4;
        TLObject r13 = r3.sendRequest;
        if ((r13 instanceof TLRPC.TL_messages_sendMedia) == false) goto L55;
        TLRPC.InputMedia r5 = ((TLRPC.TL_messages_sendMedia) r13).media;
    L61:
        r5.file = r15;
        r07.file = null;
    L67:
        TLObject r08 = r3.sendRequest;
        if (r08 != null) goto L70;
        MessageObject r09 = r3.obj;
        String r24 = r09.messageOwner.attachPath;
        TLRPC.Document r010 = r09.getDocument();
        if (r24 != null) goto L151;
        r24 = FileLoader.getDirectory(r16) + "/" + r010.id + ".mp4";
    L151:
        if (r3.sendEncryptedRequest != null) goto L153;
    L175:
        String r9 = r24;
        r1.putToDelayedMessages(r9, r3);
        VideoEditedInfo r25 = r3.obj.videoEditedInfo;
        if (r25 != null) goto L181;
    L182:
        if (r25 != null) goto L184;
    L186:
        r1.getFileLoader().uploadFile(r9, true, false, 33554432);
    L187:
        r1.putToUploadingMessages(r3.obj);
        return;
    L184:
        if (r25.needConvert() == false) goto L186;
        r1.getFileLoader().uploadFile(r9, true, false, r010.size, 33554432, false);
        goto L187
    L181:
        if (r25.notReadyYet == true) goto L187;
    L153:
        if (r010.dc_id == 0) goto L175;
        File r4 = new File(r24);
        if (r4.exists() == true) goto L161;
        r4 = r1.getFileLoader().getPathToMessage(r3.obj.messageOwner);
        if (r4 == null) goto L161;
        if (r4.exists() == false) goto L161;
        TLRPC.Message r26 = r3.obj.messageOwner;
        String r52 = r4.getAbsolutePath();
        r26.attachPath = r52;
        r3.obj.attachPathExists = true;
        r24 = r52;
    L161:
        if (r4 != null) goto L163;
    L166:
        r4 = r1.getFileLoader().getPathToAttach(r3.obj.getDocument(), false);
        if (r4 != null) goto L169;
    L171:
        if (r4 != null) goto L173;
    L176:
        r1.putToDelayedMessages(FileLoader.getAttachFileName(r010), r3);
        r1.getFileLoader().loadFile(r010, r3.parentObject, 3, 0);
        return;
    L173:
        if (r4.exists() == true) goto L175;
    L169:
        if (r4.exists() == false) goto L171;
        TLRPC.Message r27 = r3.obj.messageOwner;
        String r53 = r4.getAbsolutePath();
        r27.attachPath = r53;
        r3.obj.attachPathExists = true;
        r24 = r53;
        goto L171
    L163:
        if (r4.exists() == true) goto L171;
        if (r3.obj.getDocument() == null) goto L171;
    L70:
        if ((r08 instanceof TLRPC.TL_messages_sendMedia) == false) goto L73;
        TLRPC.TL_messages_sendMedia r011 = (TLRPC.TL_messages_sendMedia) r08;
        TLRPC.InputMedia r54 = r011.media;
        TLRPC.InputPeer r012 = r011.peer;
    L80:
        if ((r54 instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L85;
        TLRPC.TL_inputMediaPaidMedia r132 = (TLRPC.TL_inputMediaPaidMedia) r54;
        if (r132.extended_media.isEmpty() == true) goto L85;
        r54 = r132.extended_media.get(0);
    L85:
        if ((r54 instanceof TLRPC.TL_inputMediaPoll) == false) goto L89;
        TLRPC.InputMedia r133 = PollAttachedMediaPack.getFirstInputMedia((TLRPC.TL_inputMediaPoll) r54);
        if (r133 == null) goto L89;
        r54 = r133;
    L89:
        TLRPC.InputFile r134 = r54.file;
        if (r134 == null) goto L92;
    L111:
        TLRPC.InputPhoto r6 = r54.video_cover;
        if (r6 != null) goto L122;
        if (r3.coverFile != null) goto L122;
        if (r3.coverPhotoSize == null) goto L122;
        if (r3.performCoverUpload == false) goto L122;
        String r013 = FileLoader.getDirectory(r16) + "/" + r3.coverPhotoSize.location.volume_id + "_" + r3.coverPhotoSize.location.local_id + ".jpg";
        r1.putToDelayedMessages(r013, r3);
        r1.getFileLoader().uploadFile(r013, false, true, 16777216);
        r1.putToUploadingMessages(r3.obj);
        return;
    L122:
        if (r3.isLivePhoto == true) goto L124;
    L130:
        if (r6 == null) goto L132;
    L137:
        MessageObject r014 = r3.obj;
        if (r014 == null) goto L144;
        VideoEditedInfo r015 = r014.videoEditedInfo;
        if (r015 == null) goto L144;
        if (r015.isSticker == false) goto L144;
        String r016 = "webp";
    L145:
        String r017 = FileLoader.getDirectory(r16) + "/" + r3.photoSize.location.volume_id + "_" + r3.photoSize.location.local_id + "." + r016;
        r1.putToDelayedMessages(r017, r3);
        r1.getFileLoader().uploadFile(r017, false, true, 16777216);
        r1.putToUploadingMessages(r3.obj);
        return;
    L144:
        r016 = "jpg";
        goto L145
    L132:
        if (r3.coverFile == null) goto L137;
        if (r3.performCoverUpload == false) goto L137;
        TLRPC.TL_messages_uploadMedia r210 = new TLRPC.TL_messages_uploadMedia();
        r210.peer = r012;
        TLRPC.TL_inputMediaUploadedPhoto r018 = new TLRPC.TL_inputMediaUploadedPhoto();
        r018.file = r3.coverFile;
        r210.media = r018;
        r1.getConnectionsManager().sendRequest(r210, new SendMessagesHelper$$ExternalSyntheticLambda98(r1, r54, r3));
        return;
    L124:
        if ((r54 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L130;
        if (r134 == null) goto L130;
        if (r3.coverFile == null) goto L130;
        TLRPC.TL_messages_uploadMedia r211 = new TLRPC.TL_messages_uploadMedia();
        r211.peer = r012;
        r54.flags &= -65;
        r54.video_cover = null;
        r211.media = r54;
        r1.getConnectionsManager().sendRequest(r211, new SendMessagesHelper$$ExternalSyntheticLambda97(r1, r3));
        return;
    L92:
        if ((r54 instanceof TLRPC.TL_inputMediaDocument) == true) goto L111;
        if (r3.performMediaUpload == false) goto L111;
        MessageObject r019 = r3.obj;
        String r212 = r019.messageOwner.attachPath;
        TLRPC.Document r020 = r019.getDocument();
        TLRPC.Document r42 = r3.obj.documentToPollAddOption;
        if (r42 == null) goto L98;
        r020 = r42;
    L98:
        if (r212 != null) goto L100;
        r212 = FileLoader.getDirectory(r16) + "/" + r020.id + ".mp4";
    L100:
        String r92 = r212;
        r1.putToDelayedMessages(r92, r3);
        VideoEditedInfo r213 = r3.obj.videoEditedInfo;
        if (r213 != null) goto L103;
    L104:
        if (r213 != null) goto L106;
    L108:
        r1.getFileLoader().uploadFile(r92, false, false, 33554432);
    L109:
        r1.putToUploadingMessages(r3.obj);
        return;
    L106:
        if (r213.needConvert() == false) goto L108;
        r1.getFileLoader().uploadFile(r92, false, false, r020.size, 33554432, false);
        goto L109
    L103:
        if (r213.notReadyYet == true) goto L109;
    L73:
        if ((r08 instanceof TL_ephemeral.TL_sendMessage) == false) goto L76;
        TL_ephemeral.TL_sendMessage r021 = (TL_ephemeral.TL_sendMessage) r08;
        r54 = r021.media;
        r012 = r021.peer;
        goto L80
    L76:
        if ((r08 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L78;
        TLRPC.TL_messages_addPollAnswer r022 = (TLRPC.TL_messages_addPollAnswer) r08;
        r54 = r022.answer.input_media;
        r012 = r022.peer;
        goto L80
    L78:
        TLRPC.TL_messages_editMessage r023 = (TLRPC.TL_messages_editMessage) r08;
        r54 = r023.media;
        r012 = r023.peer;
        goto L80
    L55:
        if ((r13 instanceof TL_ephemeral.TL_sendMessage) == false) goto L58;
        r5 = ((TL_ephemeral.TL_sendMessage) r13).media;
        goto L61
    L58:
        if ((r13 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L60;
        r5 = ((TLRPC.TL_messages_addPollAnswer) r13).answer.input_media;
        goto L61
    L60:
        r5 = ((TLRPC.TL_messages_editMessage) r13).media;
        goto L61
    L62:
        r16 = 4;
        if (r07.encryptedFile == null) goto L67;
        TLRPC.TL_decryptedMessage r55 = (TLRPC.TL_decryptedMessage) r3.sendEncryptedRequest;
        TLRPC.DecryptedMessageMedia r214 = r55.media;
        r214.size = r07.estimatedSize;
        r214.key = r07.key;
        r214.iv = r07.iv;
        SecretChatHelper r43 = r1.getSecretChatHelper();
        MessageObject r10 = r3.obj;
        r43.performSendEncryptedRequest(r55, r10.messageOwner, r3.encryptedChat, r3.videoEditedInfo.encryptedFile, r3.originalPath, r10);
        r3.videoEditedInfo.encryptedFile = null;
        return;
    L66:
        r16 = 4;
        goto L67
    L32:
        if (r06.needConvert() == false) goto L47;
        if (r3.performMediaUpload == false) goto L47;
        MessageObject r024 = r3.obj;
        String r44 = r024.messageOwner.attachPath;
        TLRPC.Document r025 = r024.getDocument();
        if (r44 != null) goto L42;
        StringBuilder r45 = new StringBuilder();
        r45.append(FileLoader.getDirectory(4));
        r45.append("/");
        r45.append(r025.id);
        r45.append(".");
        if (r3.videoEditedInfo.isSticker == false) goto L40;
        String r026 = "webm";
    L41:
        r45.append(r026);
        r44 = r45.toString();
        goto L42
    L40:
        r026 = "mp4";
    L42:
        r1.putToDelayedMessages(r44, r3);
        if (r3.videoEditedInfo.alreadyScheduledConverting == true) goto L45;
        MediaController.getInstance().scheduleVideoConvert(r3.obj);
    L45:
        r1.putToUploadingMessages(r3.obj);
        return;
    L190:
        if (r0 != 2) goto L252;
        String r027 = r3.httpLocation;
        if (r027 == null) goto L195;
        r1.putToDelayedMessages(r027, r3);
        ImageLoader.getInstance().loadHttpFile(r3.httpLocation, "gif", r1.currentAccount);
        return;
    L195:
        TLObject r028 = r3.sendRequest;
        if (r028 != null) goto L198;
        MessageObject r029 = r3.obj;
        String r46 = r029.messageOwner.attachPath;
        TLRPC.Document r030 = r029.getDocument();
        if (r3.sendEncryptedRequest != null) goto L226;
    L249:
        r1.putToDelayedMessages(r46, r3);
        r1.getFileLoader().uploadFile(r46, true, false, 67108864);
        r1.putToUploadingMessages(r3.obj);
        return;
    L226:
        if (r030.dc_id == 0) goto L249;
        File r56 = new File(r46);
        if (r56.exists() == true) goto L234;
        r56 = r1.getFileLoader().getPathToMessage(r3.obj.messageOwner);
        if (r56 == null) goto L234;
        if (r56.exists() == false) goto L234;
        TLRPC.Message r47 = r3.obj.messageOwner;
        String r62 = r56.getAbsolutePath();
        r47.attachPath = r62;
        r3.obj.attachPathExists = true;
        r46 = r62;
    L234:
        if (r56 != null) goto L236;
    L239:
        r56 = r1.getFileLoader().getPathToAttach(r3.obj.getDocument(), false);
        if (r56 != null) goto L242;
    L244:
        if (r56 != null) goto L246;
    L247:
        r1.putToDelayedMessages(FileLoader.getAttachFileName(r030), r3);
        r1.getFileLoader().loadFile(r030, r3.parentObject, 3, 0);
        return;
    L246:
        if (r56.exists() == true) goto L249;
    L242:
        if (r56.exists() == false) goto L244;
        TLRPC.Message r48 = r3.obj.messageOwner;
        String r63 = r56.getAbsolutePath();
        r48.attachPath = r63;
        r3.obj.attachPathExists = true;
        r46 = r63;
        goto L244
    L236:
        if (r56.exists() == true) goto L244;
        if (r3.obj.getDocument() == null) goto L244;
    L198:
        if ((r028 instanceof TLRPC.TL_messages_sendMedia) == false) goto L201;
        TLRPC.InputMedia r031 = ((TLRPC.TL_messages_sendMedia) r028).media;
    L208:
        if (r031.file != null) goto L216;
        String r032 = r3.obj.messageOwner.attachPath;
        r1.putToDelayedMessages(r032, r3);
        FileLoader r49 = r1.getFileLoader();
        if (r3.sendRequest == null) goto L213;
        r8 = false;
    L213:
        r49.uploadFile(r032, r8, false, 67108864);
        r1.putToUploadingMessages(r3.obj);
        return;
    L216:
        if (r031.thumb != null) goto L480;
        TLRPC.PhotoSize r033 = r3.photoSize;
        if (r033 != null) goto L220;
        return;
    L220:
        if ((r033 instanceof TLRPC.TL_photoStrippedSize) == true) goto L482;
        String r034 = FileLoader.getDirectory(4) + "/" + r3.photoSize.location.volume_id + "_" + r3.photoSize.location.local_id + ".jpg";
        r1.putToDelayedMessages(r034, r3);
        r1.getFileLoader().uploadFile(r034, false, true, 16777216);
        r1.putToUploadingMessages(r3.obj);
        return;
    L482:
        return;
    L480:
        return;
    L201:
        if ((r028 instanceof TL_ephemeral.TL_sendMessage) == false) goto L204;
        r031 = ((TL_ephemeral.TL_sendMessage) r028).media;
        goto L208
    L204:
        if ((r028 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L206;
        r031 = ((TLRPC.TL_messages_addPollAnswer) r028).answer.input_media;
        goto L208
    L206:
        r031 = ((TLRPC.TL_messages_editMessage) r028).media;
        goto L208
    L252:
        if (r0 != 3) goto L259;
        String r035 = r3.obj.messageOwner.attachPath;
        r1.putToDelayedMessages(r035, r3);
        FileLoader r215 = r1.getFileLoader();
        if (r3.sendRequest != null) goto L256;
        r7 = true;
    L256:
        r215.uploadFile(r035, r7, true, ConnectionsManager.FileTypeAudio);
        r1.putToUploadingMessages(r3.obj);
        return;
    L259:
        if (r0 != 4) goto L477;
        if (r29 >= 0) goto L262;
        boolean r135 = true;
    L264:
        if (r3.performMediaUpload == false) goto L266;
    L275:
        if (r29 >= 0) goto L277;
        int r57 = r3.messageObjects.size() - 1;
    L278:
        MessageObject r036 = r3.messageObjects.get(r57);
        TLRPC.Document r152 = r036.getDocument();
        if (r152 == null) goto L281;
    L293:
        if (r152 == null) goto L295;
    L301:
        if (r152 == null) goto L440;
        VideoEditedInfo r216 = r3.videoEditedInfo;
        if (r216 != null) goto L305;
    L327:
        String r217 = r036.messageOwner.attachPath;
        if (r217 != null) goto L331;
        StringBuilder r218 = new StringBuilder();
        r218.append(FileLoader.getDirectory(4));
        r218.append("/");
        String r292 = "_i";
        r218.append(r152.id);
        r218.append(".mp4");
        r217 = r218.toString();
    L330:
        String r64 = r217;
        TLObject r219 = r3.sendRequest;
        if (r219 != null) goto L335;
        boolean r262 = r135;
        TLRPC.TL_messages_sendEncryptedMultiMedia r220 = (TLRPC.TL_messages_sendEncryptedMultiMedia) r3.sendEncryptedRequest;
        r1.putToDelayedMessages(r64, r3);
        r3.extraHashMap.put(r036, r64);
        r3.extraHashMap.put(r64, r220.files.get(r57));
        r3.extraHashMap.put(r64.concat(r292), r036);
        TLRPC.PhotoSize r037 = r3.photoSize;
        if (r037 != null) goto L429;
    L431:
        VideoEditedInfo r038 = r036.videoEditedInfo;
        if (r038 != null) goto L434;
    L436:
        r1.getFileLoader().uploadFile(r64, true, false, 33554432);
    L437:
        r1.putToUploadingMessages(r036);
    L326:
        TLRPC.InputFile r65 = null;
        r3.videoEditedInfo = null;
        r3.photoSize = null;
        r3.coverPhotoSize = null;
    L439:
        boolean r72 = true;
    L473:
        r3.coverFile = r65;
        r3.performMediaUpload = false;
        r3.performCoverUpload = false;
        boolean r82 = r262;
    L474:
        r1.sendReadyToSendGroup(r3, r82, r72);
        return;
    L434:
        if (r038.needConvert() == false) goto L436;
        r1.getFileLoader().uploadFile(r64, true, false, r152.size, 33554432, false);
        goto L437
    L429:
        if (r037.location == null) goto L431;
        r3.extraHashMap.put(r64.concat("_t"), r3.photoSize);
        goto L431
    L335:
        if ((r219 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L338;
        TLRPC.TL_messages_sendMultiMedia r221 = (TLRPC.TL_messages_sendMultiMedia) r219;
        TLRPC.InputPeer r73 = r221.peer;
        TLRPC.InputMedia r222 = r221.multi_media.get(r57).media;
    L356:
        if (r222 != null) goto L358;
    L380:
        String r93 = r292;
        TLRPC.PhotoSize r66 = r3.coverPhotoSize;
        if (r66 != null) goto L383;
    L394:
        r262 = r135;
        if (r66 == null) goto L416;
        if (r3.coverFile == null) goto L416;
        if (r222 == null) goto L416;
        if (r222.video_cover != null) goto L416;
        if ((r222 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L416;
        if (r222.file == null) goto L416;
        if ((r3.sendRequest instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L416;
        ArrayList<Boolean> r67 = r3.livePhotoIndexes;
        if (r67 == null) goto L416;
        if (r57 < 0) goto L416;
        if (r57 >= r67.size()) goto L416;
        if (Boolean.TRUE.equals(r3.livePhotoIndexes.get(r57)) == false) goto L416;
        TLRPC.InputFile r039 = r3.coverFile;
        r222.flags &= -65;
        r222.video_cover = null;
        TLRPC.TL_messages_uploadMedia r83 = new TLRPC.TL_messages_uploadMedia();
        r83.peer = r73;
        r83.media = r222;
        r3 = r28;
        r1.getConnectionsManager().sendRequest(r83, new SendMessagesHelper$$ExternalSyntheticLambda99(r1, r039, r222, r28, r57, r64));
        r1 = this;
    L416:
        if (r3.coverPhotoSize != null) goto L418;
    L423:
        r1 = this;
        if (r3.photoSize == null) goto L326;
        String r040 = FileLoader.getDirectory(4) + "/" + r3.photoSize.location.volume_id + "_" + r3.photoSize.location.local_id + ".jpg";
        r1.putToDelayedMessages(r040, r3);
        r3.extraHashMap.put(r040.concat("_o"), r64);
        r3.extraHashMap.put(r036, r040);
        r3.extraHashMap.put(r040, r222);
        r1.getFileLoader().uploadFile(r040, false, true, 16777216);
        r1.putToUploadingMessages(r036);
        goto L326
    L418:
        if (r3.coverFile == null) goto L423;
        if (r222 == null) goto L423;
        if (r222.video_cover != null) goto L423;
        TLRPC.TL_messages_uploadMedia r68 = new TLRPC.TL_messages_uploadMedia();
        r68.peer = r73;
        TLRPC.TL_inputMediaUploadedPhoto r12 = new TLRPC.TL_inputMediaUploadedPhoto();
        r12.file = r3.coverFile;
        r68.media = r12;
        r1 = this;
        getConnectionsManager().sendRequest(r68, new SendMessagesHelper$$ExternalSyntheticLambda100(r1, r222, r3, r64, r036));
        goto L326
    L383:
        if (r3.coverFile != null) goto L394;
        if (r222 == null) goto L394;
        if (r222.video_cover != null) goto L394;
        String r58 = FileLoader.getDirectory(4) + "/" + r3.coverPhotoSize.location.volume_id + "_" + r3.coverPhotoSize.location.local_id + ".jpg";
        r1.putToDelayedMessages(r58, r3);
        TLRPC.PhotoSize r69 = r3.coverPhotoSize;
        if (r69 != null) goto L390;
    L392:
        r262 = r135;
    L393:
        r3.extraHashMap.put(r58.concat("_o"), r64);
        r3.extraHashMap.put(r64.concat(r93), r036);
        r3.extraHashMap.put(r036, r58);
        r3.extraHashMap.put(r58, r222);
        r1.getFileLoader().uploadFile(r58, false, true, 16777216);
        r1.putToUploadingMessages(r036);
        goto L326
    L390:
        if (r69.location == null) goto L392;
        StringBuilder r610 = new StringBuilder();
        r610.append(FileLoader.getDirectory(4));
        r610.append("/");
        r262 = r135;
        r610.append(r3.coverPhotoSize.location.volume_id);
        r610.append("_");
        r610.append(r3.coverPhotoSize.location.local_id);
        r610.append(".jpg");
        String r611 = r610.toString();
        r3.extraHashMap.put(r64.concat("_ct"), r3.coverPhotoSize);
        r3.extraHashMap.put(r611.concat("_doc"), r64);
        goto L393
    L358:
        if (r222.file != null) goto L380;
        if ((r222 instanceof TLRPC.TL_inputMediaDocument) == true) goto L380;
        if (r3.performMediaUpload == false) goto L380;
        r1.putToDelayedMessages(r64, r3);
        r3.extraHashMap.put(r036, r64);
        r3.extraHashMap.put(r64, r222);
        r3.extraHashMap.put(r64.concat(r292), r036);
        TLRPC.PhotoSize r223 = r3.photoSize;
        if (r223 != null) goto L366;
    L368:
        TLRPC.PhotoSize r224 = r3.coverPhotoSize;
        if (r224 != null) goto L371;
    L373:
        VideoEditedInfo r225 = r036.videoEditedInfo;
        if (r225 != null) goto L376;
    L378:
        r1.getFileLoader().uploadFile(r64, false, false, 33554432);
    L379:
        r1.putToUploadingMessages(r036);
    L325:
        r262 = r135;
        goto L326
    L376:
        if (r225.needConvert() == false) goto L378;
        r1.getFileLoader().uploadFile(r64, false, false, r152.size, 33554432, false);
        goto L379
    L371:
        if (r224.location == null) goto L373;
        String r226 = FileLoader.getDirectory(4) + "/" + r3.coverPhotoSize.location.volume_id + "_" + r3.coverPhotoSize.location.local_id + ".jpg";
        r3.extraHashMap.put(r64.concat("_ct"), r3.coverPhotoSize);
        r3.extraHashMap.put(r226.concat("_doc"), r64);
        goto L373
    L366:
        if (r223.location == null) goto L368;
        r3.extraHashMap.put(r64.concat("_t"), r3.photoSize);
        goto L368
    L338:
        if ((r219 instanceof TLRPC.TL_messages_sendMedia) == false) goto L346;
        TLRPC.TL_messages_sendMedia r227 = (TLRPC.TL_messages_sendMedia) r219;
        r73 = r227.peer;
        TLRPC.InputMedia r228 = r227.media;
        if ((r228 instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L343;
        r222 = ((TLRPC.TL_inputMediaPaidMedia) r228).extended_media.get(r57);
        goto L356
    L343:
        if ((r228 instanceof TLRPC.TL_inputMediaPoll) == false) goto L353;
        r222 = PollAttachedMediaPack.getInputMedia((TLRPC.TL_inputMediaPoll) r228, r3.pollIndexes.get(r57).intValue());
    L353:
        r222 = null;
        goto L356
    L346:
        if ((r219 instanceof TL_ephemeral.TL_sendMessage) == false) goto L354;
        TL_ephemeral.TL_sendMessage r229 = (TL_ephemeral.TL_sendMessage) r219;
        r73 = r229.peer;
        TLRPC.InputMedia r230 = r229.media;
        if ((r230 instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L351;
        r222 = ((TLRPC.TL_inputMediaPaidMedia) r230).extended_media.get(r57);
        goto L356
    L351:
        if ((r230 instanceof TLRPC.TL_inputMediaPoll) == false) goto L353;
        r222 = PollAttachedMediaPack.getInputMedia((TLRPC.TL_inputMediaPoll) r230, r3.pollIndexes.get(r57).intValue());
        goto L356
    L354:
        r222 = null;
        r73 = null;
        goto L356
    L331:
        r292 = "_i";
        goto L330
    L305:
        if (r216.needConvert() == false) goto L327;
        if (r3.performMediaUpload == false) goto L327;
        String r231 = r036.messageOwner.attachPath;
        if (r231 != null) goto L311;
        r231 = FileLoader.getDirectory(4) + "/" + r152.id + ".mp4";
    L311:
        r1.putToDelayedMessages(r231, r3);
        r3.extraHashMap.put(r036, r231);
        r3.extraHashMap.put(r231.concat("_i"), r036);
        TLRPC.PhotoSize r59 = r3.photoSize;
        if (r59 != null) goto L314;
    L316:
        TLRPC.PhotoSize r410 = r3.coverPhotoSize;
        if (r410 == null) goto L322;
        if (r410.location == null) goto L322;
        r3.extraHashMap.put(r231.concat("_ct"), r3.coverPhotoSize);
    L322:
        if (r3.videoEditedInfo.alreadyScheduledConverting == true) goto L324;
        MediaController.getInstance().scheduleVideoConvert(r036);
    L324:
        r3.obj = r036;
        r1.putToUploadingMessages(r036);
        goto L325
    L314:
        if (r59.location == null) goto L316;
        r3.extraHashMap.put(r231.concat("_t"), r3.photoSize);
        goto L316
    L440:
        int r263 = r57;
        r262 = r135;
        String r411 = r3.httpLocation;
        if (r411 == null) goto L443;
        r1.putToDelayedMessages(r411, r3);
        r3.extraHashMap.put(r036, r3.httpLocation);
        r3.extraHashMap.put(r3.httpLocation, r036);
        ImageLoader.getInstance().loadHttpFile(r3.httpLocation, "file", r1.currentAccount);
        r65 = null;
        r3.httpLocation = null;
        goto L439
    L443:
        TLObject r232 = r3.sendRequest;
        if ((r232 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L447;
        TLRPC.InputEncryptedFile r041 = ((TLRPC.TL_messages_sendMultiMedia) r232).multi_media.get(r263).media;
    L467:
        String r233 = FileLoader.getInstance(r1.currentAccount).getPathToAttach(r3.photoSize).toString();
        r1.putToDelayedMessages(r233, r3);
        r3.extraHashMap.put(r233, r041);
        r3.extraHashMap.put(r036, r233);
        FileLoader r042 = r1.getFileLoader();
        if (r3.sendEncryptedRequest == null) goto L471;
        boolean r612 = true;
    L470:
        r72 = true;
        r042.uploadFile(r233, r612, true, 16777216);
        r1.putToUploadingMessages(r036);
        r65 = null;
        r3.photoSize = null;
        goto L473
    L471:
        r612 = false;
        goto L470
    L447:
        if ((r232 instanceof TLRPC.TL_messages_sendMedia) == false) goto L452;
        if ((((TLRPC.TL_messages_sendMedia) r232).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L452;
        r041 = ((TLRPC.TL_inputMediaPaidMedia) ((TLRPC.TL_messages_sendMedia) r232).media).extended_media.get(r263);
    L452:
        if ((r232 instanceof TLRPC.TL_messages_sendMedia) == false) goto L457;
        if ((((TLRPC.TL_messages_sendMedia) r232).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L457;
        r041 = PollAttachedMediaPack.getInputMedia((TLRPC.TL_inputMediaPoll) ((TLRPC.TL_messages_sendMedia) r232).media, r3.pollIndexes.get(r263).intValue());
    L457:
        if ((r232 instanceof TL_ephemeral.TL_sendMessage) == false) goto L462;
        if ((((TL_ephemeral.TL_sendMessage) r232).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L462;
        r041 = ((TLRPC.TL_inputMediaPaidMedia) ((TL_ephemeral.TL_sendMessage) r232).media).extended_media.get(r263);
    L462:
        if ((r232 instanceof TL_ephemeral.TL_sendMessage) == true) goto L464;
    L466:
        r041 = ((TLRPC.TL_messages_sendEncryptedMultiMedia) r3.sendEncryptedRequest).files.get(r263);
        goto L467
    L464:
        if ((((TL_ephemeral.TL_sendMessage) r232).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L466;
        r041 = PollAttachedMediaPack.getInputMedia((TLRPC.TL_inputMediaPoll) ((TL_ephemeral.TL_sendMessage) r232).media, r3.pollIndexes.get(r263).intValue());
        goto L467
    L295:
        if ((MessageObject.getMedia(r036) instanceof TLRPC.TL_messageMediaPoll) == false) goto L301;
        TLRPC.MessageMedia r14 = PollAttachedMediaPack.getMedia((TLRPC.TL_messageMediaPoll) MessageObject.getMedia(r036), r3.pollIndexes.get(r57).intValue());
        if (r14 != null) goto L299;
        TLRPC.Document r142 = null;
    L300:
        r152 = r142;
        goto L301
    L299:
        r142 = r14.document;
        goto L300
    L281:
        if ((MessageObject.getMedia(r036) instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L293;
        TLRPC.TL_messageMediaPaidMedia r143 = (TLRPC.TL_messageMediaPaidMedia) MessageObject.getMedia(r036);
        if (r57 < r143.extended_media.size()) goto L285;
        TLRPC.MessageExtendedMedia r144 = null;
    L287:
        if ((r144 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L289;
        TLRPC.MessageMedia r145 = ((TLRPC.TL_messageExtendedMedia) r144).media;
    L290:
        if (r145 != null) goto L292;
        r152 = null;
        goto L293
    L292:
        r152 = r145.document;
        goto L293
    L289:
        r145 = null;
        goto L290
    L285:
        r144 = r143.extended_media.get(r57);
        goto L287
    L277:
        r57 = r29;
        goto L278
    L266:
        if (r3.performCoverUpload == true) goto L275;
        if (r3.messageObjects.isEmpty() == true) goto L274;
        ArrayList<MessageObject> r043 = r3.messageObjects;
        TLRPC.Message r044 = r043.get(r043.size() - 1).messageOwner;
        if (r3.finalGroupMessage == 0) goto L273;
        r7 = true;
    L273:
        r1.putToSendingMessages(r044, r7);
    L274:
        r72 = true;
        r82 = r135;
        goto L474
    L262:
        r135 = false;
        goto L264
    L477:
        if (r0 != 5) goto L483;
        String r045 = "stickerset_" + r3.obj.getId();
        TLRPC.TL_messages_getStickerSet r234 = new TLRPC.TL_messages_getStickerSet();
        r234.stickerset = (TLRPC.InputStickerSet) r3.parentObject;
        r1.getConnectionsManager().sendRequest(r234, new SendMessagesHelper$$ExternalSyntheticLambda101(r1, r3, r045));
        r1.putToDelayedMessages(r045, r3);
        return;
    }

    private void performSendMessageRequest(TLObject r11, MessageObject r12, String r13, DelayedMessage r14, Object r15, HashMap<String, String> r16, boolean r17) {
        performSendMessageRequest(r11, r12, r13, null, false, r14, r15, r16, r17);
    }

    public static void prepareEditingArticle(AccountInstance r5, MessageObject r6, ArrayList<TL_iv.PageBlock> r7, ArrayList<TLRPC.Photo> r8, ArrayList<TLRPC.Document> r9, ArrayList<TLRPC.InputUser> r10, boolean r11, BaseFragment r12) {
        if (r6 == null) goto L34;
        if (r7 != null) goto L5;
        return;
    L5:
        if (r7.isEmpty() == true) goto L36;
        TL_iv.RichMessage r72 = new TL_iv.RichMessage();
        r72.rtl = r11;
        int r112 = r7.size();
        int r1 = 0;
        int r2 = 0;
    L8:
        if (r2 >= r112) goto L13;
        TL_iv.PageBlock r3 = r7.get(r2);
        r2 = r2 + 1;
        TL_iv.PageBlock r32 = r3;
        if (r32 == null) goto L8;
        r72.blocks.add(r32);
        goto L8
    L13:
        if (r72.blocks.isEmpty() == true) goto L37;
        if (r8 != null) goto L17;
    L19:
        if (r9 != null) goto L21;
    L23:
        IdentityHashMap r82 = new IdentityHashMap();
        ArrayList<TL_iv.PageBlock> r92 = r72.blocks;
        int r113 = r92.size();
    L24:
        if (r1 >= r113) goto L26;
        TL_iv.PageBlock r0 = r92.get(r1);
        r1 = r1 + 1;
        clearRichTextParentsInBlock(r0, r82);
        goto L24
    L26:
        r5.getSendMessagesHelper().editRichMessage(r6, r72, r10, r12, false);
        return;
    L21:
        if (r9.isEmpty() == true) goto L23;
        r72.documents.addAll(r9);
        goto L23
    L17:
        if (r8.isEmpty() == true) goto L19;
        r72.photos.addAll(r8);
        goto L19
    L37:
        return;
    L36:
        return;
    }

    public static void prepareSendingArticle(AccountInstance r13, ArrayList<TL_iv.PageBlock> r14, ArrayList<TLRPC.Photo> r15, ArrayList<TLRPC.Document> r16, ArrayList<TLRPC.InputUser> r17, boolean r18, long r19, MessageObject r21, MessageObject r22, boolean r23, int r24, int r25, SendMessageChatArguments r26, long r27, long r29, long r31) {
        if (r14 != null) goto L5;
        return;
    L5:
        if (r14.isEmpty() == true) goto L35;
        TL_iv.RichMessage r3 = new TL_iv.RichMessage();
        r3.rtl = r18;
        int r2 = r14.size();
        int r4 = 0;
        int r5 = 0;
    L8:
        if (r5 >= r2) goto L13;
        TL_iv.PageBlock r6 = r14.get(r5);
        r5 = r5 + 1;
        TL_iv.PageBlock r62 = r6;
        if (r62 == null) goto L8;
        r3.blocks.add(r62);
        goto L8
    L13:
        if (r3.blocks.isEmpty() == true) goto L36;
        if (r15 != null) goto L17;
    L19:
        if (r16 != null) goto L21;
    L23:
        IdentityHashMap r142 = new IdentityHashMap();
        ArrayList<TL_iv.PageBlock> r0 = r3.blocks;
        int r1 = r0.size();
    L24:
        if (r4 >= r1) goto L26;
        TL_iv.PageBlock r28 = r0.get(r4);
        r4 = r4 + 1;
        clearRichTextParentsInBlock(r28, r142);
        goto L24
    L26:
        SendMessageParams r143 = SendMessageParams.ofRichMessage(r3, r19, r21, r22, null, null, r23, r24, r25);
        r143.richMessageInputUsers = r17;
        r143.sendMessageChatArguments = r26;
        r143.effect_id = r27;
        r143.monoForumPeer = r29;
        r143.payStars = r31;
        r13.getSendMessagesHelper().sendMessage(r143);
        return;
    L21:
        if (r16.isEmpty() == true) goto L23;
        r3.documents.addAll(r16);
        goto L23
    L17:
        if (r15.isEmpty() == true) goto L19;
        r3.photos.addAll(r15);
        goto L19
    L36:
        return;
    }

    public static void prepareSendingArticle(AccountInstance r20, ArrayList<TL_iv.PageBlock> r21, boolean r22, long r23, MessageObject r25, MessageObject r26, boolean r27, int r28, int r29, SendMessageChatArguments r30, long r31, long r33, long r35) {
        prepareSendingArticle(r20, r21, null, null, null, r22, r23, r25, r26, r27, r28, r29, r30, r31, r33, r35);
    }

    public static void prepareSendingAudioDocuments(AccountInstance r22, ArrayList<MessageObject> r23, CharSequence r24, long r25, MessageObject r27, MessageObject r28, TL_stories.StoryItem r29, boolean r30, int r31, int r32, MessageObject r33, SendMessageChatArguments r34, long r35, boolean r37, long r38) {
        prepareSendingAudioDocuments(r22, r23, r24, r25, r27, r28, r29, r30, r31, r32, r33, r34, r35, r37, r38, null, null, false, null);
    }

    public static void prepareSendingAudioDocuments(AccountInstance r24, ArrayList<MessageObject> r25, CharSequence r26, long r27, MessageObject r29, MessageObject r30, TL_stories.StoryItem r31, boolean r32, int r33, int r34, MessageObject r35, SendMessageChatArguments r36, long r37, boolean r39, long r40, PollSendParams r42, ArrayList<Integer> r43, boolean r44, Runnable r45) {
        new Thread(new SendMessagesHelper$$ExternalSyntheticLambda9(r42, r25, r27, r24, r26, r44, r43, r35, r29, r30, r32, r33, r34, r31, r36, r37, r39, r40, r45)).start();
    }

    public static void prepareSendingBotContextResult(BaseFragment r18, AccountInstance r19, TLRPC.BotInlineResult r20, HashMap<String, String> r21, long r22, MessageObject r24, MessageObject r25, TL_stories.StoryItem r26, ChatActivity.ReplyQuote r27, boolean r28, int r29, int r30, SendMessageChatArguments r31, long r32) {
        prepareSendingBotContextResult(r18, r19, r20, r21, r22, r24, r25, r26, r27, r28, r29, r30, r31, r32, 0);
    }

    public static void prepareSendingBotContextResult(BaseFragment r20, AccountInstance r21, TLRPC.BotInlineResult r22, HashMap<String, String> r23, long r24, MessageObject r26, MessageObject r27, TL_stories.StoryItem r28, ChatActivity.ReplyQuote r29, boolean r30, int r31, int r32, SendMessageChatArguments r33, long r34, long r36) {
        if (r22 == null) goto L71;
        TLRPC.BotInlineMessage r0 = r22.send_message;
        if ((r0 instanceof TLRPC.TL_botInlineMessageMediaAuto) == false) goto L10;
        new Thread(new SendMessagesHelper$$ExternalSyntheticLambda37(r24, r22, r21, r23, r20, r26, r27, r30, r31, r32, r33, r28, r29, r34, r36)).run();
        return;
    L10:
        if ((r0 instanceof TLRPC.TL_botInlineMessageText) == false) goto L25;
        if (DialogObject.isEncryptedDialog(r24) == false) goto L21;
        int r02 = 0;
    L15:
        if (r02 >= r22.send_message.entities.size()) goto L21;
        TLRPC.MessageEntity r1 = r22.send_message.entities.get(r02);
        if ((r1 instanceof TLRPC.TL_messageEntityUrl) == true) goto L18;
        r02 = r02 + 1;
        goto L15
    L18:
        TLRPC.TL_webPagePending r03 = new TLRPC.TL_webPagePending();
        String r2 = r22.send_message.message;
        int r4 = r1.offset;
        r03.url = r2.substring(r4, r1.length + r4);
    L19:
        TLRPC.TL_webPagePending r6 = r03;
        TLRPC.BotInlineMessage r04 = r22.send_message;
        SendMessageParams r12 = SendMessageParams.of(r04.message, r24, r26, r27, r6, !r04.no_webpage, r04.entities, r04.reply_markup, r23, r30, r31, r32, null, false);
        r12.sendMessageChatArguments = r33;
        r12.replyQuote = r29;
        r12.payStars = r34;
        r12.monoForumPeer = r36;
        r21.getSendMessagesHelper().sendMessage(r12);
        return;
    L21:
        r03 = null;
        goto L19
    L25:
        if ((r0 instanceof TLRPC.TL_botInlineMessageRichMessage) == false) goto L29;
        SendMessageParams r13 = SendMessageParams.ofRichMessage(r0.rich_message, r24, r26, r27, r0.reply_markup, r23, r30, r31, r32);
        r13.sendMessageChatArguments = r33;
        r13.replyQuote = r29;
        r13.payStars = r34;
        r13.monoForumPeer = r36;
        r21.getSendMessagesHelper().sendMessage(r13);
        return;
    L29:
        if ((r0 instanceof TLRPC.TL_botInlineMessageMediaVenue) == false) goto L36;
        TLRPC.TL_messageMediaVenue r14 = new TLRPC.TL_messageMediaVenue();
        TLRPC.BotInlineMessage r25 = r22.send_message;
        r14.geo = r25.geo;
        r14.address = r25.address;
        r14.title = r25.title;
        r14.provider = r25.provider;
        r14.venue_id = r25.venue_id;
        String r3 = r25.venue_type;
        r14.venue_id = r3;
        r14.venue_type = r3;
        if (r3 != null) goto L33;
        r14.venue_type = _UrlKt.FRAGMENT_ENCODE_SET;
    L33:
        SendMessageParams r15 = SendMessageParams.of(r14, r24, r26, r27, r25.reply_markup, r23, r30, r31, r32);
        r15.sendMessageChatArguments = r33;
        r15.replyQuote = r29;
        r15.payStars = r34;
        r15.monoForumPeer = r36;
        r21.getSendMessagesHelper().sendMessage(r15);
        return;
    L36:
        if ((r0 instanceof TLRPC.TL_botInlineMessageMediaGeo) == false) goto L51;
        if (r0.period == 0) goto L40;
    L43:
        TLRPC.TL_messageMediaGeoLive r16 = new TLRPC.TL_messageMediaGeoLive();
        TLRPC.BotInlineMessage r210 = r22.send_message;
        int r35 = r210.period;
        if (r35 != 0) goto L47;
        r35 = 900;
    L47:
        r16.period = r35;
        r16.geo = r210.geo;
        r16.heading = r210.heading;
        r16.proximity_notification_radius = r210.proximity_notification_radius;
        SendMessageParams r17 = SendMessageParams.of(r16, r24, r26, r27, r210.reply_markup, r23, r30, r31, r32);
    L48:
        r17.sendMessageChatArguments = r33;
        r17.replyQuote = r29;
        r17.payStars = r34;
        r17.monoForumPeer = r36;
        r21.getSendMessagesHelper().sendMessage(r17);
        return;
    L40:
        if (r0.proximity_notification_radius != 0) goto L43;
        TLRPC.TL_messageMediaGeo r18 = new TLRPC.TL_messageMediaGeo();
        TLRPC.BotInlineMessage r211 = r22.send_message;
        r18.geo = r211.geo;
        r18.heading = r211.heading;
        r17 = SendMessageParams.of(r18, r24, r26, r27, r211.reply_markup, r23, r30, r31, r32);
        goto L48
    L51:
        if ((r0 instanceof TLRPC.TL_botInlineMessageMediaContact) == false) goto L55;
        TLRPC.TL_user r19 = new TLRPC.TL_user();
        TLRPC.BotInlineMessage r212 = r22.send_message;
        r19.phone = r212.phone_number;
        r19.first_name = r212.first_name;
        r19.last_name = r212.last_name;
        TLRPC.RestrictionReason r213 = new TLRPC.RestrictionReason();
        r213.text = r22.send_message.vcard;
        r213.platform = _UrlKt.FRAGMENT_ENCODE_SET;
        r213.reason = _UrlKt.FRAGMENT_ENCODE_SET;
        r19.restriction_reason.add(r213);
        SendMessageParams r110 = SendMessageParams.of(r19, r24, r26, r27, r22.send_message.reply_markup, r23, r30, r31, r32);
        r110.sendMessageChatArguments = r33;
        r110.replyQuote = r29;
        r110.payStars = r34;
        r110.monoForumPeer = r36;
        r21.getSendMessagesHelper().sendMessage(r110);
        return;
    L55:
        if ((r0 instanceof TLRPC.TL_botInlineMessageMediaInvoice) == false) goto L65;
        if (DialogObject.isEncryptedDialog(r24) == true) goto L72;
        TLRPC.TL_botInlineMessageMediaInvoice r111 = (TLRPC.TL_botInlineMessageMediaInvoice) r22.send_message;
        TLRPC.TL_messageMediaInvoice r214 = new TLRPC.TL_messageMediaInvoice();
        r214.shipping_address_requested = r111.shipping_address_requested;
        r214.test = r111.test;
        r214.title = r111.title;
        r214.description = r111.description;
        TLRPC.WebDocument r5 = r111.photo;
        if (r5 == null) goto L62;
        r214.webPhoto = r5;
        r214.flags |= 1;
    L62:
        r214.currency = r111.currency;
        r214.total_amount = r111.total_amount;
        r214.start_param = _UrlKt.FRAGMENT_ENCODE_SET;
        SendMessageParams r112 = SendMessageParams.of(r214, r24, r26, r27, r22.send_message.reply_markup, r23, r30, r31, r32);
        r112.sendMessageChatArguments = r33;
        r112.replyQuote = r29;
        r112.payStars = r34;
        r112.monoForumPeer = r36;
        r21.getSendMessagesHelper().sendMessage(r112);
        return;
    L72:
        return;
    L65:
        if ((r0 instanceof TLRPC.TL_botInlineMessageMediaWebPage) == false) goto L70;
        TLRPC.TL_webPagePending r9 = new TLRPC.TL_webPagePending();
        r9.url = ((TLRPC.TL_botInlineMessageMediaWebPage) r0).url;
        TLRPC.BotInlineMessage r113 = r22.send_message;
        SendMessageParams r42 = SendMessageParams.of(r113.message, r24, r26, r27, r9, !r113.no_webpage, r113.entities, r113.reply_markup, r23, r30, r31, r32, null, false);
        r42.sendMessageChatArguments = r33;
        r42.replyQuote = r29;
        r42.payStars = r34;
        r42.monoForumPeer = r36;
        r42.invert_media = r22.send_message.invert_media;
        r21.getSendMessagesHelper().sendMessage(r42);
        return;
    L70:
        return;
    }

    public static void prepareSendingDocument(AccountInstance r22, String r23, String r24, Uri r25, String r26, String r27, long r28, MessageObject r30, MessageObject r31, TL_stories.StoryItem r32, ChatActivity.ReplyQuote r33, MessageObject r34, boolean r35, int r36, InputContentInfoCompat r37, SendMessageChatArguments r38, boolean r39) {
        if (r23 == null) goto L5;
        if (r24 == null) goto L5;
    L7:
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        if (r25 == null) goto L10;
        ArrayList r5 = new ArrayList();
        r5.add(r25);
    L11:
        if (r23 == null) goto L13;
        r3.add(r23);
        r4.add(r24);
    L13:
        prepareSendingDocuments(r22, r3, r4, r5, r26, r27, r28, r30, r31, r32, r33, r34, r35, r36, r37, r38, 0, r39, 0);
        return;
    L10:
        r5 = null;
    L5:
        if (r25 != null) goto L7;
    }

    private static int prepareSendingDocumentInternal(AccountInstance r38, String r39, String r40, Uri r41, String r42, long r43, MessageObject r45, MessageObject r46, TL_stories.StoryItem r47, ChatActivity.ReplyQuote r48, ArrayList<TLRPC.MessageEntity> r49, MessageObject r50, long[] r51, boolean r52, CharSequence r53, boolean r54, int r55, int r56, Integer[] r57, boolean r58, SendMessageChatArguments r59, long r60, boolean r62, long r63, long r65, MessageSuggestionParams r67, PollSendParams r68, int r69) {
        if (r68 == null) goto L5;
        long r13 = r68.groupId;
    L7:
        if (r39 != null) goto L9;
    L10:
        if (r41 != null) goto L12;
        return 1;
    L12:
        if (r41 != null) goto L14;
    L16:
        if (r39 != null) goto L18;
    L20:
        MimeTypeMap r6 = MimeTypeMap.getSingleton();
        if (r41 == null) goto L37;
        if (r39 != null) goto L37;
        if (checkFileSize(r38, r41) == false) goto L26;
        return 2;
    L26:
        if (r42 == null) goto L28;
        String r0 = r6.getExtensionFromMimeType(r42);
    L29:
        if (r0 != null) goto L31;
        r0 = "txt";
        boolean r4 = false;
    L32:
        String r3 = MediaController.copyFileToCache(r41, r0);
        if (r3 != null) goto L35;
        return 1;
    L35:
        if (r4 == true) goto L38;
    L36:
        r0 = null;
    L38:
        File r12 = new File(r3);
        if (r12.exists() == false) goto L370;
        if (r12.length() == 0) goto L370;
        if (FileLoader.checkUploadFileSize(r38.getCurrentAccount(), r12.length()) == true) goto L46;
        return 2;
    L46:
        boolean r26 = DialogObject.isEncryptedDialog(r43);
        String r11 = r12.getName();
        if (r0 == null) goto L50;
    L48:
        String r412 = r0;
    L54:
        String r122 = r412.toLowerCase();
        if (r122.equals(HlsSegmentFormat.MP3) == false) goto L57;
    L58:
        Object r20 = "opus";
        long r33 = r13;
        AudioInfo r02 = AudioInfo.getAudioInfo(r12);
        if (r02 == null) goto L144;
        long r132 = r02.getDuration();
        if (r132 == 0) goto L142;
        String r1 = r02.getArtist();
        String r7 = r02.getTitle();
        int r133 = (int) (r132 / 1000);
    L143:
        Bitmap r03 = r02.getCover();
    L145:
        int r14 = r133;
        Bitmap r134 = r03;
        int r04 = r14;
        String r142 = r7;
    L130:
        boolean r72 = false;
    L147:
        if (r04 == 0) goto L158;
        boolean r22 = r72;
        TLRPC.TL_documentAttributeAudio r73 = new TLRPC.TL_documentAttributeAudio();
        Object r24 = "m4a";
        Object r23 = "flac";
        r73.duration = r04;
        r73.title = r142;
        r73.performer = r1;
        if (r142 != null) goto L151;
        r73.title = _UrlKt.FRAGMENT_ENCODE_SET;
    L151:
        int r05 = r73.flags;
        r73.flags = r05 | 1;
        if (r1 != null) goto L154;
        r73.performer = _UrlKt.FRAGMENT_ENCODE_SET;
    L154:
        r73.flags = r05 | 3;
        if (r22 == false) goto L157;
        r73.voice = true;
    L157:
        TLRPC.TL_documentAttributeAudio r8 = r73;
    L159:
        if (r40 != null) goto L161;
        String r143 = r412;
        String r9 = r40;
    L166:
        boolean r15 = false;
    L169:
        String r2 = r6.getMimeTypeFromExtension(r143);
        if (r58 == false) goto L184;
        if (r2 == null) goto L184;
        if (r2.startsWith("image/") == false) goto L184;
        String r06 = MediaController.createFileInCache("cleaned_" + r11, r143).getAbsolutePath();     // Catch: Exception -> L178
        if (MediaUtils.removeGeolocation(r3, r06) == false) goto L184;
        r3 = r06;
    L179:
        if (r06 == null) goto L184;
        new File(r06).delete();     // Catch: Exception -> L182
    L182:
        e = move-exception;
        FileLog.e(e);
    L178:
        r06 = null;
    L184:
        if (r68 == null) goto L186;
        boolean r07 = true;
    L188:
        if (r15 == true) goto L217;
        if (r26 == true) goto L217;
        if (r07 == true) goto L217;
        MessagesStorage r08 = r38.getMessagesStorage();
        if (r26 == true) goto L194;
        int r16 = 1;
    L195:
        Object[] r09 = r08.getSentFile(r9, r16);
        if (r09 == null) goto L200;
        Object r17 = r09[0];
        if ((r17 instanceof TLRPC.TL_document) == false) goto L200;
        TLRPC.TL_document r18 = (TLRPC.TL_document) r17;
        String r010 = (String) r09[1];
    L201:
        if (r18 == null) goto L203;
    L214:
        String r413 = r010;
        TLRPC.TL_document r402 = r18;
    L215:
        TLRPC.TL_document r19 = r402;
        String r011 = r413;
    L216:
        int r35 = 2;
        String r414 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r403 = r9;
        String r27 = r143;
        Object r144 = r20;
        String r36 = "image/";
        String r92 = r2;
        String r44 = r3;
        boolean r25 = r26;
        TLRPC.TL_document r32 = r19;
        ensureMediaThumbExists(r38, r25, r32, r44, null, 0);
        String r64 = r011;
    L219:
        if (r32 != null) goto L310;
        r32 = new TLRPC.TL_document();
        String r37 = r64;
        r32.id = 0;
        r32.date = r38.getConnectionsManager().getCurrentTime();
        TLRPC.TL_documentAttributeFilename r012 = new TLRPC.TL_documentAttributeFilename();
        r012.file_name = r11;
        r32.file_reference = new byte[0];
        r32.attributes.add(r012);
        boolean r262 = r25;
        r32.size = r12.length();
        r32.dc_id = 0;
        if (r8 == null) goto L224;
        r32.attributes.add(r8);
    L224:
        if (r27.length() != 0) goto L226;
        r32.mime_type = "application/octet-stream";
    L264:
        if (r58 == false) goto L266;
    L271:
        boolean r74 = r262;
    L286:
        if (r134 == null) goto L292;
        TLRPC.PhotoSize r013 = ImageLoader.scaleAndSaveImage(r134, 132.0f, 132.0f, 55, r74);
        if (r013 == null) goto L290;
        r32.thumbs.add(r013);
        r32.flags |= 1;
    L290:
        r134.recycle();
    L292:
        if (r32.mime_type.equals("image/webp") == false) goto L309;
        if (r50 != null) goto L309;
        BitmapFactory.Options r110 = new BitmapFactory.Options();
        r110.inJustDecodeBounds = true;     // Catch: Exception -> L297
        BitmapFactory.decodeFile(r44, r110);     // Catch: Exception -> L297
    L299:
        int r014 = r110.outWidth;
        if (r014 == 0) goto L309;
        int r28 = r110.outHeight;
        if (r28 == 0) goto L309;
        if (r014 > 800) goto L309;
        if (r28 > 800) goto L309;
        TLRPC.TL_documentAttributeSticker r015 = new TLRPC.TL_documentAttributeSticker();
        String r29 = r414;
        r015.alt = r29;
        r015.stickerset = new TLRPC.TL_inputStickerSetEmpty();
        r32.attributes.add(r015);
        TLRPC.TL_documentAttributeImageSize r016 = new TLRPC.TL_documentAttributeImageSize();
        r016.w = r110.outWidth;
        r016.h = r110.outHeight;
        r32.attributes.add(r016);
        boolean r263 = r74;
        TLRPC.PhotoSize r017 = ImageLoader.scaleAndSaveImage(null, ImageLoader.loadBitmap(r12.getAbsolutePath(), null, 400.0f, 400.0f, true), Bitmap.CompressFormat.PNG, false, 400.0f, 400.0f, 100, r263, 0, 0, false);
        if (r017 == null) goto L311;
        r32.thumbs.add(r017);
        r32.flags |= 1;
    L311:
        if (r53 == null) goto L313;
        String r112 = r53.toString();
    L314:
        HashMap r018 = new HashMap();
        if (r403 == null) goto L318;
        r018.put("originalPath", r403);
    L318:
        if (r58 == false) goto L321;
        if (r8 != null) goto L321;
        r018.put("forceDocument", "1");
    L321:
        if (r37 == null) goto L323;
        String r75 = r37;
        r018.put("parentObject", r75);
    L324:
        int r31 = 0;
        if (r57 == null) goto L348;
        Integer r66 = r57[0];
        String r93 = r32.mime_type;
        if (r93 != null) goto L329;
    L331:
        String r5 = r32.mime_type;
        if (r5 == null) goto L340;
        if (r5.toLowerCase().startsWith(r36) == true) goto L342;
        if (r32.mime_type.toLowerCase().startsWith("video/mp4") == false) goto L340;
    L342:
        r31 = 0;
        r57[0] = 1;
    L347:
        Integer r10 = r66;
        int r152 = r31;
    L350:
        if (r263 == true) goto L364;
        if (r51 == null) goto L364;
        if (r57 == null) goto L358;
        if (r10 == null) goto L358;
        if (r10 == r57[r31]) goto L358;
        if (r68 != null) goto L358;
        AccountInstance r610 = r38;
        int r145 = r55;
        finishGroup(r610, r51[r31], r145);
        r51[r31] = Utilities.random.nextLong();
    L359:
        if (r152 != 0) goto L366;
        r018.put("groupId", r29 + r51[r31]);
        if (r52 == false) goto L366;
        r018.put("final", "1");
    L366:
        if (r33 == 0) goto L368;
        r018.put("groupId", r29 + r33);
    L368:
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda70(r50, r610, r32, r44, r018, r75, r43, r45, r46, r112, r49, r54, r145, r56, r47, r48, r59, r60, r62, r63, r65, r67, r69, r68));
        return r31;
    L358:
        r610 = r38;
        r145 = r55;
    L364:
        r610 = r38;
        r145 = r55;
    L340:
        if (MessageObject.canPreviewDocument(r32) == true) goto L342;
        r31 = 0;
        if (r8 == null) goto L346;
        r57[0] = Integer.valueOf(r35);
        goto L347
    L346:
        r57[0] = 0;
        goto L347
    L329:
        if (r93.toLowerCase().startsWith("image/webp") == false) goto L331;
        r57[0] = -1;
        r10 = r66;
        r152 = 1;
        r31 = 0;
        goto L350
    L348:
        r152 = 0;
        r10 = 0;
        goto L350
    L323:
        r75 = r37;
        goto L324
    L313:
        r112 = r29;
    L297:
        e = move-exception;
        FileLog.e(e);
    L309:
        r29 = r414;
        r263 = r74;
        goto L311
    L266:
        if (r32.mime_type.equals("image/gif") == false) goto L271;
        if (r50 != null) goto L269;
    L397:
        Bitmap r210 = ImageLoader.loadBitmap(r12.getAbsolutePath(), null, 90.0f, 90.0f, true);     // Catch: Exception -> L283
        if (r210 == null) goto L271;
        r012.file_name = "animation.gif";     // Catch: Exception -> L283
        r32.attributes.add(new TLRPC.TL_documentAttributeAnimated());     // Catch: Exception -> L283
        r74 = r262;
        TLRPC.PhotoSize r019 = ImageLoader.scaleAndSaveImage(r210, 90.0f, 90.0f, 55, r74);     // Catch: Exception -> L279
        if (r019 == null) goto L281;
        r32.thumbs.add(r019);     // Catch: Exception -> L279
        r32.flags |= 1;
    L281:
        r210.recycle();     // Catch: Exception -> L279
    L279:
        e = e;
    L285:
        FileLog.e(e);
    L283:
        e = e;
        r74 = r262;
        goto L285
    L269:
        if (r50.getGroupIdForUse() != 0) goto L271;
    L226:
        switch(r122.hashCode()) {
            case 106458: goto L249;
            case 108272: goto L245;
            case 109967: goto L241;
            case 3145576: goto L237;
            case 3418175: goto L233;
            case 3645340: goto L229;
            default: goto L227;
        };
    L227:
        int r76 = -1;
    L252:
        switch(r76) {
            case 0: goto L261;
            case 1: goto L260;
            case 2: goto L259;
            case 3: goto L258;
            case 4: goto L257;
            case 5: goto L256;
            default: goto L253;
        };
    L253:
        if (r92 == null) goto L255;
        r32.mime_type = r92;
        goto L264
    L255:
        r32.mime_type = "application/octet-stream";
        goto L264
    L256:
        r32.mime_type = "image/webp";
        goto L264
    L257:
        r32.mime_type = "audio/opus";
        goto L264
    L258:
        r32.mime_type = "audio/flac";
        goto L264
    L259:
        r32.mime_type = "audio/ogg";
        goto L264
    L260:
        r32.mime_type = "audio/mpeg";
        goto L264
    L261:
        r32.mime_type = "audio/m4a";
        goto L264
    L229:
        if (r122.equals("webp") == false) goto L227;
        r76 = 5;
        goto L252
    L233:
        if (r122.equals(r144) == false) goto L227;
        r76 = 4;
        goto L252
    L237:
        if (r122.equals(r23) == false) goto L227;
        r76 = 3;
        goto L252
    L241:
        if (r122.equals("ogg") == false) goto L227;
        r76 = r35;
        goto L252
    L245:
        if (r122.equals(HlsSegmentFormat.MP3) == false) goto L227;
        r76 = 1;
        goto L252
    L249:
        if (r122.equals(r24) == false) goto L227;
        r76 = 0;
        goto L252
    L310:
        r263 = r25;
        r37 = r64;
        r29 = r414;
        goto L311
    L203:
        if (r3.equals(r9) == true) goto L214;
        if (r26 == true) goto L214;
        MessagesStorage r510 = r38.getMessagesStorage();
        StringBuilder r77 = new StringBuilder();
        r77.append(r3);
        r413 = r010;
        r402 = r18;
        r77.append(r12.length());
        String r020 = r77.toString();
        if (r26 == true) goto L208;
        int r111 = 1;
    L209:
        Object[] r021 = r510.getSentFile(r020, r111);
        if (r021 == null) goto L215;
        Object r113 = r021[0];
        if ((r113 instanceof TLRPC.TL_document) == false) goto L215;
        r19 = (TLRPC.TL_document) r113;
        r011 = (String) r021[1];
        goto L216
    L208:
        r111 = 4;
    L200:
        r010 = null;
        r18 = null;
        goto L201
    L194:
        r16 = 4;
    L217:
        r414 = _UrlKt.FRAGMENT_ENCODE_SET;
        r36 = "image/";
        r403 = r9;
        r27 = r143;
        r144 = r20;
        r35 = 2;
        r92 = r2;
        r44 = r3;
        r25 = r26;
        r32 = null;
        r64 = null;
        goto L219
    L186:
        r07 = false;
        goto L188
    L161:
        if (r40.endsWith("attheme") == false) goto L163;
        r143 = r412;
        r9 = r40;
        r15 = true;
        goto L169
    L163:
        if (r8 == null) goto L167;
        String r022 = r40 + MediaStreamTrack.AUDIO_TRACK_KIND + r12.length();
    L165:
        r143 = r412;
        r9 = r022;
        goto L166
    L167:
        r022 = r40 + _UrlKt.FRAGMENT_ENCODE_SET + r12.length();
        goto L165
    L158:
        r24 = "m4a";
        r23 = "flac";
        r8 = null;
        goto L159
    L142:
        r1 = null;
        r7 = null;
        r133 = 0;
        goto L143
    L144:
        r03 = null;
        r1 = null;
        r7 = null;
        r133 = 0;
        goto L145
    L57:
        if (r122.equals("m4a") == true) goto L58;
        if (r122.equals("opus") == false) goto L62;
    L405:
        MediaMetadataRetriever r114 = new MediaMetadataRetriever();     // Catch: Throwable -> L119 Exception -> L121
        r114.setDataSource(r12.getAbsolutePath());     // Catch: Throwable -> L97 Exception -> L117
        if (r114.extractMetadata(9) == null) goto L86;
        r33 = r13;
        int r135 = (int) Math.ceil(Long.parseLong(r0) / 1000.0f);
        r142 = r114.extractMetadata(7);     // Catch: Throwable -> L77 Exception -> L81
        r20 = "opus";
        int r192 = r135;
        String r136 = r114.extractMetadata(2);     // Catch: Throwable -> L77 Exception -> L79
    L401:
        byte[] r023 = r114.getEmbeddedPicture();     // Catch: Throwable -> L97 Exception -> L99
        if (r023 == null) goto L101;
        MediaMetadataRetriever r222 = r114;
        Bitmap r115 = BitmapFactory.decodeByteArray(r023, 0, r023.length);     // Catch: Throwable -> L93 Exception -> L95
    L102:
        if (r50 == null) goto L382;
    L111:
        r72 = false;
    L395:
        r222.release();     // Catch: Exception -> L114
    L116:
        String r024 = r136;
        r134 = r115;
        r1 = r024;
        r04 = r192;
    L114:
        e = move-exception;
        FileLog.e(e);
        goto L116
    L382:
    L109:
        e = e;
    L123:
        FileLog.e(e);     // Catch: Throwable -> L93
        if (r222 != null) goto L378;
    L129:
        String r025 = r136;
        r134 = r115;
        r1 = r025;
        r04 = r192;
        goto L130
    L378:
        r222.release();     // Catch: Exception -> L127
    L127:
        e = move-exception;
        FileLog.e(e);
        goto L129
    L104:
        if (r122.equals("ogg") == false) goto L111;
        if (MediaController.isOpusFile(r12.getAbsolutePath()) != 1) goto L111;
        r72 = true;
    L95:
        e = e;
    L96:
        r115 = null;
    L93:
        Throwable th = th;
    L94:
        Throwable r116 = th;
        MediaMetadataRetriever r117 = r222;
    L131:
        if (r117 == null) goto L407;
        r117.release();     // Catch: Exception -> L134
        throw r116;
    L134:
        e = move-exception;
        FileLog.e(e);
        throw r116;
    L407:
        throw r116;
    L101:
        r222 = r114;
        r115 = null;
    L99:
        e = e;
        r222 = r114;
        goto L96
    L79:
        e = e;
        r222 = r114;
        r192 = r135;
        r115 = null;
        r136 = null;
    L81:
        e = e;
        r20 = "opus";
        r222 = r114;
        r192 = r135;
        r115 = null;
        r136 = null;
        r142 = null;
    L83:
        e = e;
        r20 = "opus";
        r222 = r114;
    L85:
        r115 = null;
        r136 = null;
        r142 = null;
        r192 = 0;
    L77:
        th = move-exception;
        r117 = r114;
        r116 = th;
        goto L131
    L86:
        r20 = "opus";
        r33 = r13;
        r136 = null;
        r142 = null;
        r192 = 0;
    L117:
        e = e;
        r222 = r114;
        r20 = "opus";
        r33 = r13;
    L97:
        th = th;
        r222 = r114;
        goto L94
    L121:
        e = e;
        r20 = "opus";
        r33 = r13;
        r115 = null;
        r136 = null;
        r142 = null;
        r192 = 0;
        r222 = null;
    L119:
        th = move-exception;
        r116 = th;
        r117 = null;
        goto L131
    L62:
        if (r122.equals("ogg") == true) goto L405;
        if (r122.equals("flac") == true) goto L405;
        r20 = "opus";
        r33 = r13;
        r04 = 0;
        r1 = null;
        r72 = false;
        r134 = null;
        r142 = null;
        goto L147
    L50:
        int r026 = r3.lastIndexOf(46);
        if (r026 == (-1)) goto L53;
        r0 = r3.substring(r026 + 1);
        goto L48
    L53:
        r412 = _UrlKt.FRAGMENT_ENCODE_SET;
    L370:
        return 1;
    L31:
        r4 = true;
        goto L32
    L28:
        r0 = null;
    L37:
        r3 = r39;
        goto L36
    L18:
        if (AndroidUtilities.isInternalUri(Uri.fromFile(new File(r39))) == false) goto L20;
        return 1;
    L14:
        if (AndroidUtilities.isInternalUri(r41) == false) goto L16;
        return 1;
    L9:
        if (r39.length() != 0) goto L12;
    L5:
        r13 = 0;
        goto L7
    }

    public static void prepareSendingDocuments(AccountInstance r30, ArrayList<String> r31, ArrayList<String> r32, ArrayList<Uri> r33, CharSequence r34, String r35, long r36, MessageObject r38, MessageObject r39, TL_stories.StoryItem r40, ChatActivity.ReplyQuote r41, MessageObject r42, boolean r43, int r44, InputContentInfoCompat r45, SendMessageChatArguments r46, long r47, boolean r49, long r50) {
        String r0 = null;
        if (r34 == null) goto L5;
        CharSequence[] r2 = {r34};
        ArrayList<TLRPC.MessageEntity> r1 = r30.getMediaDataController().getEntities(r2, true);
        CharSequence r22 = r2[0];
        ArrayList<TLRPC.MessageEntity> r8 = r1;
    L6:
        if (r22 == null) goto L9;
        r0 = r22.toString();
    L9:
        prepareSendingDocuments(r30, r31, r32, r33, r0, r8, r35, r36, r38, r39, r40, r41, r42, r43, r44, 0, r45, r46, r47, r49, r50, 0, null);
        return;
    L5:
        r22 = r34;
        r8 = null;
        goto L6
    }

    public static void prepareSendingDocuments(AccountInstance r27, ArrayList<String> r28, ArrayList<String> r29, ArrayList<Uri> r30, String r31, String r32, long r33, MessageObject r35, MessageObject r36, TL_stories.StoryItem r37, ChatActivity.ReplyQuote r38, MessageObject r39, boolean r40, int r41, InputContentInfoCompat r42, SendMessageChatArguments r43, long r44, boolean r46, long r47) {
        prepareSendingDocuments(r27, r28, r29, r30, r31, null, r32, r33, r35, r36, r37, r38, r39, r40, r41, 0, r42, r43, r44, r46, r47, 0, null);
    }

    public static void prepareSendingDocuments(AccountInstance r31, ArrayList<String> r32, ArrayList<String> r33, ArrayList<Uri> r34, String r35, ArrayList<TLRPC.MessageEntity> r36, String r37, long r38, MessageObject r40, MessageObject r41, TL_stories.StoryItem r42, ChatActivity.ReplyQuote r43, MessageObject r44, boolean r45, int r46, int r47, InputContentInfoCompat r48, SendMessageChatArguments r49, long r50, boolean r52, long r53, long r55, MessageSuggestionParams r57) {
        prepareSendingDocuments(r31, r32, r33, r34, r35, r36, r37, r38, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r52, r53, r55, r57, null, null, null, false);
    }

    public static void prepareSendingDocuments(AccountInstance r33, ArrayList<String> r34, ArrayList<String> r35, ArrayList<Uri> r36, String r37, ArrayList<TLRPC.MessageEntity> r38, String r39, long r40, MessageObject r42, MessageObject r43, TL_stories.StoryItem r44, ChatActivity.ReplyQuote r45, MessageObject r46, boolean r47, int r48, int r49, InputContentInfoCompat r50, SendMessageChatArguments r51, long r52, boolean r54, long r55, long r57, MessageSuggestionParams r59, PollSendParams r60, ArrayList<Integer> r61, ArrayList<Integer> r62, boolean r63) {
        if (r34 != null) goto L5;
        if (r35 != null) goto L5;
        if (r36 != null) goto L5;
        return;
    L5:
        if (r34 == null) goto L10;
        if (r35 == null) goto L10;
        if (r34.size() == r35.size()) goto L10;
        return;
    L10:
        Utilities.globalQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda125(r40, r34, r37, r60, r33, r48, r35, r39, r42, r43, r44, r45, r38, r46, r63, r47, r49, r50, r51, r52, r54, r55, r57, r59, r61, r36, r62));
    }

    public static void prepareSendingMedia(AccountInstance r29, ArrayList<SendingMediaInfo> r30, long r31, MessageObject r33, MessageObject r34, TL_stories.StoryItem r35, ChatActivity.ReplyQuote r36, boolean r37, boolean r38, MessageObject r39, TLRPC.TL_inputPollAnswer r40, boolean r41, int r42, int r43, int r44, boolean r45, InputContentInfoCompat r46, SendMessageChatArguments r47, long r48, boolean r50, long r51, long r53, MessageSuggestionParams r55) {
        prepareSendingMedia(r29, r30, r31, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r50, r51, r53, r55, null, false);
    }

    public static void prepareSendingMedia(AccountInstance r33, ArrayList<SendingMediaInfo> r34, long r35, MessageObject r37, MessageObject r38, TL_stories.StoryItem r39, ChatActivity.ReplyQuote r40, boolean r41, boolean r42, MessageObject r43, TLRPC.TL_inputPollAnswer r44, boolean r45, int r46, int r47, int r48, boolean r49, InputContentInfoCompat r50, SendMessageChatArguments r51, long r52, boolean r54, long r55, long r57, MessageSuggestionParams r59, PollSendParams r60, boolean r61) {
        if (r34.isEmpty() == false) goto L5;
        return;
    L5:
        int r1 = r34.size();
        boolean r2 = false;
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L12;
        if (r34.get(r3).ttl > 0) goto L10;
        r3 = r3 + 1;
        goto L7
    L10:
        boolean r5 = false;
    L14:
        if (r60 == null) goto L16;
        long r8 = r60.groupId;
    L18:
        if (r8 == 0) goto L20;
        r2 = true;
    L20:
        mediaSendQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda66(r34, r35, r41, r5, r2, r33, r8, r43, r44, r37, r38, r45, r46, r47, r39, r40, r51, r52, r54, r55, r57, r59, r60, r61, r50, r49));
        return;
    L16:
        r8 = 0;
        goto L18
    L12:
        r5 = r42;
        goto L14
    }

    public static void prepareSendingMedia(AccountInstance r27, ArrayList<SendingMediaInfo> r28, long r29, MessageObject r31, MessageObject r32, TL_stories.StoryItem r33, ChatActivity.ReplyQuote r34, boolean r35, boolean r36, MessageObject r37, boolean r38, int r39, int r40, int r41, boolean r42, InputContentInfoCompat r43, SendMessageChatArguments r44, long r45, boolean r47, long r48, long r50, MessageSuggestionParams r52) {
        prepareSendingMedia(r27, r28, r29, r31, r32, r33, r34, r35, r36, r37, null, r38, r39, r40, r41, r42, r43, r44, r45, r47, r48, r50, r52);
    }

    public static void prepareSendingPhoto(AccountInstance r30, String r31, Uri r32, long r33, MessageObject r35, MessageObject r36, ChatActivity.ReplyQuote r37, CharSequence r38, ArrayList<TLRPC.MessageEntity> r39, ArrayList<TLRPC.InputDocument> r40, InputContentInfoCompat r41, int r42, MessageObject r43, boolean r44, int r45, int r46, SendMessageChatArguments r47) {
        prepareSendingPhoto(r30, r31, null, r32, r33, r35, r36, null, null, r39, r40, r41, r42, r43, null, r44, r45, 0, r46, false, r38, r47, 0, 0, 0, null);
    }

    public static void prepareSendingPhoto(AccountInstance r28, String r29, String r30, Uri r31, long r32, MessageObject r34, MessageObject r35, TL_stories.StoryItem r36, ChatActivity.ReplyQuote r37, ArrayList<TLRPC.MessageEntity> r38, ArrayList<TLRPC.InputDocument> r39, InputContentInfoCompat r40, int r41, MessageObject r42, VideoEditedInfo r43, boolean r44, int r45, int r46, int r47, boolean r48, CharSequence r49, SendMessageChatArguments r50, long r51, long r53, long r55, MessageSuggestionParams r57) {
        SendingMediaInfo r1 = new SendingMediaInfo();
        r1.path = r29;
        r1.thumbPath = r30;
        r1.uri = r31;
        if (r49 == null) goto L5;
        r1.caption = r49.toString();
    L5:
        r1.entities = r38;
        r1.ttl = r41;
        if (r39 == null) goto L8;
        r1.masks = new ArrayList(r39);
    L8:
        r1.videoEditedInfo = r43;
        ArrayList r3 = new ArrayList();
        r3.add(r1);
        prepareSendingMedia(r28, r3, r32, r34, r35, null, r37, r48, false, r42, r44, r45, 0, r47, false, r40, r50, r51, false, r53, r55, r57);
    }

    public static void prepareSendingPhoto(AccountInstance r30, String r31, String r32, Uri r33, long r34, MessageObject r36, MessageObject r37, TL_stories.StoryItem r38, ChatActivity.ReplyQuote r39, ArrayList<TLRPC.MessageEntity> r40, ArrayList<TLRPC.InputDocument> r41, InputContentInfoCompat r42, int r43, MessageObject r44, VideoEditedInfo r45, boolean r46, int r47, int r48, boolean r49, CharSequence r50, SendMessageChatArguments r51, long r52, long r54) {
        prepareSendingPhoto(r30, r31, r32, r33, r34, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, 0, r48, r49, r50, r51, r52, r54, 0, null);
    }

    public static void prepareSendingPoll(AccountInstance r29, PollSendParams r30, long r31, MessageObject r33, MessageObject r34, TL_stories.StoryItem r35, ChatActivity.ReplyQuote r36, boolean r37, int r38, SendMessageChatArguments r39, long r40, long r42, MessageSuggestionParams r44) {
        if (r30.mediaPack == null) goto L44;
        ArrayList r1 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        ArrayList r7 = new ArrayList();
        ArrayList r8 = new ArrayList();
        ArrayList r9 = new ArrayList();
        int[] r10 = new int[1];
        int r11 = r30.mediaPack.medias.size();
        int r12 = 0;
    L5:
        if (r12 >= r11) goto L22;
        PollAttachedMedia r13 = r30.mediaPack.medias.valueAt(r12);
        int r14 = r30.mediaPack.medias.keyAt(r12);
        if ((r13 instanceof PollAttachedMediaMusic) == false) goto L10;
        r8.add(((PollAttachedMediaMusic) r13).messageObject);
        r9.add(Integer.valueOf(r14));
    L20:
        r12 = r12 + 1;
        goto L5
    L10:
        if ((r13 instanceof PollAttachedMediaGallery) == false) goto L13;
        SendingMediaInfo r132 = ((PollAttachedMediaGallery) r13).sendingMediaInfo;
        r132.pollIndex = r14;
        r1.add(r132);
        goto L20
    L13:
        if ((r13 instanceof PollAttachedMediaFile) == false) goto L20;
        PollAttachedMediaFile r133 = (PollAttachedMediaFile) r13;
        if (TextUtils.isEmpty(r133.path) == true) goto L17;
        r3.add(r133.path);
        r4.add(r133.path);
        r5.add(Integer.valueOf(r14));
        goto L20
    L17:
        Uri r134 = r133.uri;
        if (r134 == null) goto L20;
        r6.add(r134);
        r7.add(Integer.valueOf(r14));
        goto L20
    L22:
        if (r1.isEmpty() == true) goto L24;
        r10[0] = r10[0] + 1;
        boolean r112 = true;
    L26:
        if (r3.isEmpty() == true) goto L28;
    L29:
        r10[0] = r10[0] + 1;
        r112 = true;
    L31:
        if (r8.isEmpty() == true) goto L33;
        r10[0] = r10[0] + 1;
        boolean r23 = true;
    L34:
        SendMessagesHelper$$ExternalSyntheticLambda20 r21 = new SendMessagesHelper$$ExternalSyntheticLambda20(r1, r10, r29, r31, r33, r34, r35, r36, r37, r38, r39, r40, r42, r44, r30, new SendMessagesHelper$$ExternalSyntheticLambda19(r3, r6, r29, r4, r31, r33, r34, r35, r36, r37, r38, r39, r40, r42, r44, r30, r5, r7));
        if (r8.isEmpty() == true) goto L41;
        int r15 = r10[0] - 1;
        r10[0] = r15;
        if (r15 <= 0) goto L39;
        boolean r20 = true;
    L40:
        prepareSendingAudioDocuments(r29, r8, _UrlKt.FRAGMENT_ENCODE_SET, r31, r33, r34, r35, r37, r38, 0, null, r39, 0, false, r40, r30, r9, r20, r21);
        PollSendParams r102 = r30;
    L42:
        if (r23 == false) goto L45;
        return;
    L45:
        SendMessageParams r0 = SendMessageParams.of(r102.poll, r31, r33, r34, null, null, r37, r38, 0);
        r0.caption = r102.caption;
        r0.invert_media = true;
        r0.entities = r102.entities;
        r0.sendMessageChatArguments = r39;
        r0.payStars = r40;
        r0.monoForumPeer = r42;
        r0.suggestionParams = r44;
        r0.pollSendParams = r102;
        r29.getSendMessagesHelper().sendMessage(r0);
        return;
    L39:
        r20 = false;
        goto L40
    L41:
        r102 = r30;
        r21.run();
        goto L42
    L33:
        r23 = r112;
        goto L34
    L28:
        if (r6.isEmpty() == true) goto L31;
    L24:
        r112 = false;
        goto L26
    L44:
        r102 = r30;
        goto L45
    }

    public static void prepareSendingText(AccountInstance r13, CharSequence r14, long r15, long r17, boolean r19, int r20, int r21, long r22) {
        r13.getMessagesStorage().getStorageQueue().postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda111(r14, r13, r17, r15, r19, r20, r21, r22));
    }

    public static void prepareSendingText(AccountInstance r11, CharSequence r12, long r13, boolean r15, int r16, int r17, long r18) {
        prepareSendingText(r11, r12, r13, 0, r15, r16, r17, r18);
    }

    public static void prepareSendingVideo(AccountInstance r28, String r29, VideoEditedInfo r30, String r31, TLRPC.Photo r32, long r33, MessageObject r35, MessageObject r36, TL_stories.StoryItem r37, ChatActivity.ReplyQuote r38, ArrayList<TLRPC.MessageEntity> r39, int r40, MessageObject r41, boolean r42, int r43, int r44, boolean r45, boolean r46, CharSequence r47, SendMessageChatArguments r48, long r49, long r51) {
        prepareSendingVideo(r28, r29, r30, r31, r32, r33, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r51, 0, null);
    }

    public static void prepareSendingVideo(AccountInstance r29, String r30, VideoEditedInfo r31, String r32, TLRPC.Photo r33, long r34, MessageObject r36, MessageObject r37, TL_stories.StoryItem r38, ChatActivity.ReplyQuote r39, ArrayList<TLRPC.MessageEntity> r40, int r41, MessageObject r42, boolean r43, int r44, int r45, boolean r46, boolean r47, CharSequence r48, SendMessageChatArguments r49, long r50, long r52, long r54, MessageSuggestionParams r56) {
        prepareSendingVideo(r29, r30, r31, r32, r33, r34, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r52, r54, r56, false);
    }

    public static void prepareSendingVideo(AccountInstance r31, String r32, VideoEditedInfo r33, String r34, TLRPC.Photo r35, long r36, MessageObject r38, MessageObject r39, TL_stories.StoryItem r40, ChatActivity.ReplyQuote r41, ArrayList<TLRPC.MessageEntity> r42, int r43, MessageObject r44, boolean r45, int r46, int r47, boolean r48, boolean r49, CharSequence r50, SendMessageChatArguments r51, long r52, long r54, long r56, MessageSuggestionParams r58, boolean r59) {
        if (r32 != null) goto L4;
        return;
    L4:
        if (r32.length() == 0) goto L9;
        new Thread(new SendMessagesHelper$$ExternalSyntheticLambda58(r33, r32, r36, r43, r31, r34, r35, r50, r44, r49, r38, r39, r42, r45, r46, r47, r40, r41, r51, r52, r54, r56, r58, r59, r48)).start();
        return;
    }

    private void putToDelayedMessages(String r2, DelayedMessage r3) {
        ArrayList<DelayedMessage> r0 = this.delayedMessages.get(r2);
        if (r0 != null) goto L5;
        r0 = new ArrayList();
        this.delayedMessages.put(r2, r0);
    L5:
        r0.add(r3);
    }

    private boolean removeCoverFromRequest(TLObject r3) {
        if ((r3 instanceof TLRPC.TL_messages_sendMedia) == false) goto L17;
        TLRPC.InputMedia r2 = ((TLRPC.TL_messages_sendMedia) r3).media;
        if ((r2 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L9;
        TLRPC.TL_inputMediaUploadedDocument r22 = (TLRPC.TL_inputMediaUploadedDocument) r2;
        r22.video_cover = null;
        r22.flags &= -65;
        return true;
    L9:
        if ((r2 instanceof TLRPC.TL_inputMediaDocument) == false) goto L13;
        TLRPC.TL_inputMediaDocument r23 = (TLRPC.TL_inputMediaDocument) r2;
        r23.video_cover = null;
        r23.flags &= -9;
        return true;
    L13:
        if ((r2 instanceof TLRPC.TL_inputMediaDocumentExternal) == false) goto L58;
        TLRPC.TL_inputMediaDocumentExternal r24 = (TLRPC.TL_inputMediaDocumentExternal) r2;
        r24.video_cover = null;
        r24.flags &= -5;
        return true;
    L58:
        return false;
    L17:
        if ((r3 instanceof TL_ephemeral.TL_sendMessage) == false) goto L31;
        TLRPC.InputMedia r25 = ((TL_ephemeral.TL_sendMessage) r3).media;
        if ((r25 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L23;
        TLRPC.TL_inputMediaUploadedDocument r26 = (TLRPC.TL_inputMediaUploadedDocument) r25;
        r26.video_cover = null;
        r26.flags &= -65;
        return true;
    L23:
        if ((r25 instanceof TLRPC.TL_inputMediaDocument) == false) goto L27;
        TLRPC.TL_inputMediaDocument r27 = (TLRPC.TL_inputMediaDocument) r25;
        r27.video_cover = null;
        r27.flags &= -9;
        return true;
    L27:
        if ((r25 instanceof TLRPC.TL_inputMediaDocumentExternal) == false) goto L60;
        TLRPC.TL_inputMediaDocumentExternal r28 = (TLRPC.TL_inputMediaDocumentExternal) r25;
        r28.video_cover = null;
        r28.flags &= -5;
        return true;
    L60:
        return false;
    L31:
        if ((r3 instanceof TLRPC.TL_messages_editMessage) == false) goto L45;
        TLRPC.InputMedia r29 = ((TLRPC.TL_messages_editMessage) r3).media;
        if ((r29 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L37;
        TLRPC.TL_inputMediaUploadedDocument r210 = (TLRPC.TL_inputMediaUploadedDocument) r29;
        r210.video_cover = null;
        r210.flags &= -65;
        return true;
    L37:
        if ((r29 instanceof TLRPC.TL_inputMediaDocument) == false) goto L41;
        TLRPC.TL_inputMediaDocument r211 = (TLRPC.TL_inputMediaDocument) r29;
        r211.video_cover = null;
        r211.flags &= -9;
        return true;
    L41:
        if ((r29 instanceof TLRPC.TL_inputMediaDocumentExternal) == false) goto L61;
        TLRPC.TL_inputMediaDocumentExternal r212 = (TLRPC.TL_inputMediaDocumentExternal) r29;
        r212.video_cover = null;
        r212.flags &= -5;
        return true;
    L61:
        return false;
    L45:
        if ((r3 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L62;
        TLRPC.InputMedia r213 = ((TLRPC.TL_messages_addPollAnswer) r3).answer.input_media;
        if ((r213 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L51;
        TLRPC.TL_inputMediaUploadedDocument r214 = (TLRPC.TL_inputMediaUploadedDocument) r213;
        r214.video_cover = null;
        r214.flags &= -65;
        return true;
    L51:
        if ((r213 instanceof TLRPC.TL_inputMediaDocument) == false) goto L55;
        TLRPC.TL_inputMediaDocument r215 = (TLRPC.TL_inputMediaDocument) r213;
        r215.video_cover = null;
        r215.flags &= -9;
        return true;
    L55:
        if ((r213 instanceof TLRPC.TL_inputMediaDocumentExternal) == false) goto L63;
        TLRPC.TL_inputMediaDocumentExternal r216 = (TLRPC.TL_inputMediaDocumentExternal) r213;
        r216.video_cover = null;
        r216.flags &= -5;
        return true;
    L63:
        return false;
    L62:
        return false;
    }

    private boolean requestRichMessageFileReference(MessageObject r5, TLRPC.TL_messages_editMessage r6, String r7, Runnable r8) {
        int r0 = 0;
        if (r5 != null) goto L5;
    L18:
        return false;
    L5:
        if (r6.rich_message == null) goto L18;
        TL_iv.TL_inputRichMessage r62 = r6.rich_message;
        ArrayList r1 = new ArrayList(r62.photos.size() + r62.documents.size());
        r1.addAll(r62.photos);
        r1.addAll(r62.documents);
        if (r1.isEmpty() == false) goto L10;
        return false;
    L10:
        int r63 = FileRefController.getFileRefErrorIndex(r7);
        if (r63 >= 0) goto L13;
    L16:
        getFileRefController().requestReference(r5, new Object[]{r1.get(r0), r8});
        return true;
    L13:
        if (r63 >= r1.size()) goto L16;
        r0 = r63;
        goto L16
    }

    private void revertEditingMessageObject(MessageObject r11) {
        r11.cancelEditing = true;
        TLRPC.Message r0 = r11.messageOwner;
        r0.media = r11.previousMedia;
        r0.message = r11.previousMessage;
        ArrayList<TLRPC.MessageEntity> r1 = r11.previousMessageEntities;
        r0.entities = r1;
        r0.attachPath = r11.previousAttachPath;
        r0.send_state = 0;
        if (r1 == null) goto L5;
        r0.flags |= 128;
    L6:
        r11.previousMedia = null;
        r11.previousMessage = null;
        r11.previousMessageEntities = null;
        r11.previousAttachPath = null;
        r11.videoEditedInfo = null;
        r11.type = -1;
        r11.setType();
        r11.caption = null;
        if (r11.type == 0) goto L9;
        r11.generateCaption();
    L10:
        ArrayList<TLRPC.Message> r2 = new ArrayList();
        r2.add(r11.messageOwner);
        getMessagesStorage().putMessages(r2, false, true, false, 0, r11.scheduled ? 1 : 0, 0);
        ArrayList r02 = new ArrayList();
        r02.add(r11);
        getNotificationCenter().postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(r11.getDialogId()), r02});
        return;
    L9:
        r11.resetLayout();
        goto L10
    L5:
        r0.flags &= -129;
        goto L6
    }

    private static TL_iv.TL_inputRichMessage richMessageToInputRichMessage(TL_iv.RichMessage r9, ArrayList<TLRPC.InputUser> r10) {
        TL_iv.TL_inputRichMessage r0 = new TL_iv.TL_inputRichMessage();
        if (r9 == null) goto L35;
        r0.rtl = r9.rtl;
        r0.blocks = new ArrayList(r9.blocks.size());
        int r2 = 0;
    L7:
        if (r2 >= r9.blocks.size()) goto L9;
        r0.blocks.add(toInputPageBlock(r9.blocks.get(r2)));
        r2 = r2 + 1;
        goto L7
    L9:
        ArrayList<TLRPC.Photo> r22 = r9.photos;
        if (r22 != null) goto L12;
    L20:
        ArrayList<TLRPC.Document> r23 = r9.documents;
        if (r23 != null) goto L23;
    L31:
        if (r10 == null) goto L35;
        if (r10.isEmpty() == true) goto L35;
        r0.flags |= 16;
        r0.users.addAll(r10);
        goto L35
    L23:
        if (r23.isEmpty() == true) goto L31;
        r0.flags |= 8;
        ArrayList<TLRPC.Document> r92 = r9.documents;
        int r24 = r92.size();
        int r3 = 0;
    L25:
        if (r3 >= r24) goto L31;
        TLRPC.Document r4 = r92.get(r3);
        r3 = r3 + 1;
        TLRPC.Document r42 = r4;
        TLRPC.TL_inputDocument r5 = new TLRPC.TL_inputDocument();
        r5.id = r42.id;
        r5.access_hash = r42.access_hash;
        byte[] r43 = r42.file_reference;
        if (r43 != null) goto L30;
        r43 = new byte[0];
    L30:
        r5.file_reference = r43;
        r0.documents.add(r5);
        goto L25
    L12:
        if (r22.isEmpty() == true) goto L20;
        r0.flags |= 4;
        ArrayList<TLRPC.Photo> r25 = r9.photos;
        int r32 = r25.size();
        int r44 = 0;
    L14:
        if (r44 >= r32) goto L20;
        TLRPC.Photo r52 = r25.get(r44);
        r44 = r44 + 1;
        TLRPC.Photo r53 = r52;
        TLRPC.TL_inputPhoto r6 = new TLRPC.TL_inputPhoto();
        r6.id = r53.id;
        r6.access_hash = r53.access_hash;
        byte[] r54 = r53.file_reference;
        if (r54 != null) goto L19;
        r54 = new byte[0];
    L19:
        r6.file_reference = r54;
        r0.photos.add(r6);
    L35:
        return r0;
    }

    private int sendEditRichMessageRequest(TLRPC.TL_messages_editMessage r8, MessageObject r9, BaseFragment r10, boolean r11) {
        int r7 = getConnectionsManager().sendRequest(r8, new SendMessagesHelper$$ExternalSyntheticLambda76(this, r11, r9, r8, r10));
        if (r9 == null) goto L7;
        TLRPC.Message r82 = r9.messageOwner;
        if (r82 == null) goto L7;
        r82.reqId = r7;
    L7:
        return r7;
    }

    private void sendLocation(Location r11) {
        TLRPC.TL_messageMediaGeo r0 = new TLRPC.TL_messageMediaGeo();
        TLRPC.TL_geoPoint r1 = new TLRPC.TL_geoPoint();
        r0.geo = r1;
        r1.lat = AndroidUtilities.fixLocationCoord(r11.getLatitude());
        TLRPC.GeoPoint r12 = r0.geo;
        r12._long = AndroidUtilities.fixLocationCoord(r11.getLongitude());
        Iterator<Map.Entry<String, MessageObject>> r112 = this.waitingForLocation.entrySet().iterator();
    L4:
        if (r112.hasNext() == false) goto L6;
        MessageObject r3 = r112.next().getValue();
        sendMessage(SendMessageParams.of(r0, r3.getDialogId(), r3, null, null, null, true, 0, 0));
        goto L4
    }

    private void sendReadyToSendGroup(DelayedMessage r17, boolean r18, boolean r19) {
        if (r17.messageObjects.isEmpty() == false) goto L6;
        r17.markAsError();
        return;
    L6:
        String r1 = "group_" + r17.groupId;
        int r2 = r17.finalGroupMessage;
        ArrayList<MessageObject> r3 = r17.messageObjects;
        int r6 = 1;
        if (r2 == r3.get(r3.size() - 1).getId()) goto L18;
        if (r18 == false) goto L15;
        if (BuildVars.DEBUG_VERSION == false) goto L12;
        FileLog.d("final message not added, add");
    L12:
        putToDelayedMessages(r1, r17);
        return;
    L15:
        if (BuildVars.DEBUG_VERSION == false) goto L147;
        FileLog.d("final message not added");
        return;
    L147:
        return;
    L18:
        int r22 = 0;
        if (r18 == false) goto L54;
        this.delayedMessages.remove(r1);
        MessageObject r12 = r17.obj;
        if (r12 == null) goto L27;
        if (r12.isWelcomeMessage() == false) goto L27;
        r6 = 9;
    L25:
        int r13 = r6;
    L42:
        if (r17.paidMedia == false) goto L44;
    L47:
        ArrayList<MessageObject> r14 = new ArrayList();
        r14.add(r17.messageObjects.get(0));
        ArrayList<TLRPC.Message> r8 = new ArrayList();
        r8.add(r17.messages.get(0));
        getMessagesStorage().putMessages(r8, false, true, false, 0, r13, 0);
        getMessagesController().updateInterfaceWithMessages(r17.peer, r14, r13);
    L49:
        if (r17.scheduled == true) goto L52;
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
    L52:
        if (BuildVars.DEBUG_VERSION == false) goto L54;
        FileLog.d("add message");
        goto L54
    L44:
        if (r17.pollMedia == true) goto L47;
        getMessagesStorage().putMessages(r17.messages, false, true, false, 0, r13, 0);
        getMessagesController().updateInterfaceWithMessages(r17.peer, r17.messageObjects, r13);
    L27:
        if (r17.scheduled == true) goto L25;
        MessageObject r15 = r17.obj;
        if (r15 != null) goto L32;
    L33:
        ArrayList<MessageObject> r16 = r17.messageObjects;
        if (r16 != null) goto L36;
    L40:
        r13 = 0;
        goto L42
    L36:
        if (r16.isEmpty() == true) goto L40;
        if (r17.messageObjects.get(0).isQuickReply() == false) goto L40;
    L39:
        r6 = 5;
        goto L25
    L32:
        if (r15.isQuickReply() == true) goto L39;
    L54:
        TLObject r110 = r17.sendRequest;
        if ((r110 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L96;
        TLRPC.TL_messages_sendMultiMedia r111 = (TLRPC.TL_messages_sendMultiMedia) r110;
    L58:
        if (r22 >= r111.multi_media.size()) goto L84;
        TLRPC.InputMedia r32 = r111.multi_media.get(r22).media;
        if ((r32 instanceof TLRPC.TL_inputMediaUploadedPhoto) == true) goto L81;
        if ((r32 instanceof TLRPC.TL_inputMediaUploadedDocument) == true) goto L81;
        if ((r32 instanceof TLRPC.TL_inputMediaDocument) == false) goto L79;
        if (r22 >= r17.messageObjects.size()) goto L79;
        MessageObject r62 = r17.messageObjects.get(r22);
        String r82 = r62.messageOwner.attachPath;
        if (r82 != null) goto L72;
        r82 = FileLoader.getDirectory(4) + "/" + r62.getDocument().id + ".mp4";
    L72:
        if (r17.extraHashMap.containsKey(r82.concat("_ct")) == false) goto L79;
        if (r32.video_cover != null) goto L79;
        if (BuildVars.DEBUG_VERSION == false) goto L171;
        FileLog.d("cover media not ready");
        return;
    L171:
        return;
    L79:
        r22 = r22 + 1;
    L81:
        if (BuildVars.DEBUG_VERSION == false) goto L172;
        FileLog.d("multi media not ready");
        return;
    L172:
        return;
    L84:
        if (r19 == false) goto L149;
        DelayedMessage r112 = findMaxDelayedMessageForMessageId(r17.finalGroupMessage, r17.peer);
        if (r112 == null) goto L149;
        r112.addDelayedRequest(r17.sendRequest, r17.messageObjects, r17.originalPaths, r17.parentObjects, r17, r17.scheduled);
        ArrayList<DelayedMessageSendAfterRequest> r113 = r17.requests;
        if (r113 == null) goto L91;
        r112.requests.addAll(r113);
    L91:
        if (BuildVars.DEBUG_VERSION == false) goto L173;
        FileLog.d("has maxDelayedMessage, delay");
        return;
    L173:
        return;
    L149:
        TLObject r0 = r17.sendRequest;
        if ((r0 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L153;
        performSendMessageRequestMulti((TLRPC.TL_messages_sendMultiMedia) r0, r17.messageObjects, r17.originalPaths, r17.parentObjects, r17, r17.scheduled);
    L156:
        r17.sendDelayedRequests();
        return;
    L153:
        if ((r0 instanceof TLRPC.TL_messages_sendMedia) == false) goto L155;
        performSendMessageRequestMulti((TLRPC.TL_messages_sendMedia) r0, r17.messageObjects, r17.originalPaths, r17.parentObjects, r17, r17.scheduled);
        goto L156
    L155:
        getSecretChatHelper().performSendEncryptedRequest((TLRPC.TL_messages_sendEncryptedMultiMedia) r17.sendEncryptedRequest, r17);
        goto L156
    L96:
        if ((r110 instanceof TLRPC.TL_messages_sendMedia) == false) goto L116;
        if ((((TLRPC.TL_messages_sendMedia) r110).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L116;
        if (PollAttachedMediaPack.hasWrongInputMediaTypes((TLRPC.TL_inputMediaPoll) ((TLRPC.TL_messages_sendMedia) r110).media) == true) goto L102;
        if (r19 == false) goto L149;
        DelayedMessage r02 = findMaxDelayedMessageForMessageId(r17.finalGroupMessage, r17.peer);
        if (r02 == null) goto L149;
        r02.addDelayedRequest(r17.sendRequest, r17.messageObjects, r17.originalPaths, r17.parentObjects, r17, r17.scheduled);
        ArrayList<DelayedMessageSendAfterRequest> r114 = r17.requests;
        if (r114 == null) goto L112;
        r02.requests.addAll(r114);
    L112:
        if (BuildVars.DEBUG_VERSION == false) goto L175;
        FileLog.d("has maxDelayedMessage, delay");
        return;
    L175:
        return;
    L102:
        if (BuildVars.DEBUG_VERSION == false) goto L174;
        FileLog.d("multi media not ready");
        return;
    L174:
        return;
    L116:
        if ((r110 instanceof TLRPC.TL_messages_sendMedia) == true) goto L118;
    L142:
        TLRPC.TL_messages_sendEncryptedMultiMedia r03 = (TLRPC.TL_messages_sendEncryptedMultiMedia) r17.sendEncryptedRequest;
    L144:
        if (r22 >= r03.files.size()) goto L149;
        if ((r03.files.get(r22) instanceof TLRPC.TL_inputEncryptedFile) == true) goto L178;
        r22 = r22 + 1;
        goto L144
    L178:
        return;
    L118:
        if ((((TLRPC.TL_messages_sendMedia) r110).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L142;
        TLRPC.TL_inputMediaPaidMedia r04 = (TLRPC.TL_inputMediaPaidMedia) ((TLRPC.TL_messages_sendMedia) r110).media;
    L121:
        if (r22 >= r04.extended_media.size()) goto L132;
        TLRPC.InputMedia r115 = r04.extended_media.get(r22);
        if ((r115 instanceof TLRPC.TL_inputMediaUploadedPhoto) == true) goto L129;
        if ((r115 instanceof TLRPC.TL_inputMediaUploadedDocument) == true) goto L129;
        r22 = r22 + 1;
    L129:
        if (BuildVars.DEBUG_VERSION == false) goto L176;
        FileLog.d("multi media not ready");
        return;
    L176:
        return;
    L132:
        if (r19 == false) goto L149;
        DelayedMessage r05 = findMaxDelayedMessageForMessageId(r17.finalGroupMessage, r17.peer);
        if (r05 == null) goto L149;
        r05.addDelayedRequest(r17.sendRequest, r17.messageObjects, r17.originalPaths, r17.parentObjects, r17, r17.scheduled);
        ArrayList<DelayedMessageSendAfterRequest> r116 = r17.requests;
        if (r116 == null) goto L139;
        r05.requests.addAll(r116);
    L139:
        if (BuildVars.DEBUG_VERSION == false) goto L177;
        FileLog.d("has maxDelayedMessage, delay");
        return;
    }

    public static boolean shouldSendWebPAsSticker(String r2, Uri r3) {
        BitmapFactory.Options r0 = new BitmapFactory.Options();
        r0.inJustDecodeBounds = true;
        if (r2 == null) goto L33;
        BitmapFactory.decodeFile(r2, r0);     // Catch: Exception -> L6
    L22:
        if (r0.outWidth < 800) goto L24;
    L26:
        return false;
    L24:
        if (r0.outHeight >= 800) goto L26;
        return true;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L22
    L33:
        InputStream r22 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r3);     // Catch: Exception -> L28
        BitmapFactory.decodeStream(r22, null, r0);     // Catch: Throwable -> L14
        if (r22 == null) goto L22;
        r22.close();     // Catch: Exception -> L28
        goto L22
    L14:
        th = move-exception;
        if (r22 != null) goto L34;
    L20:
        throw th;     // Catch: Exception -> L28
    L34:
        r22.close();     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L28
        goto L20
    }

    private static TLRPC.InputGeoPoint toInputGeoPoint(TLRPC.GeoPoint r3) {
        if ((r3 instanceof TLRPC.TL_geoPoint) == false) goto L5;
        TLRPC.TL_inputGeoPoint r0 = new TLRPC.TL_inputGeoPoint();
        r0.lat = r3.lat;
        r0._long = r3._long;
        int r32 = r3.accuracy_radius;
        if (r32 == 0) goto L9;
        r0.flags |= 1;
        r0.accuracy_radius = r32;
    L9:
        return r0;
    L5:
        return new TLRPC.TL_inputGeoPointEmpty();
    }

    public static TL_iv.PageBlock toInputPageBlock(TL_iv.PageBlock r2) {
        if ((r2 instanceof TL_iv.pageBlockMap) == false) goto L6;
        TL_iv.pageBlockMap r22 = (TL_iv.pageBlockMap) r2;
        TL_iv.inputPageBlockMap r0 = new TL_iv.inputPageBlockMap();
        r0.geo = toInputGeoPoint(r22.geo);
        r0.zoom = r22.zoom;
        r0.w = r22.w;
        r0.h = r22.h;
        r0.caption = r22.caption;
        return r0;
    L6:
        return r2;
    }

    private static Integer tryParseInt(String r1) {
        if (r1 != null) goto L8;
        return null;
    L8:
        return Integer.valueOf(Integer.parseInt(r1));
    L7:
        return null;
    }

    private void updateMediaPaths(MessageObject r9, TLRPC.Message r10, int r11, String r12, boolean r13, HashMap<String, String> r14) {
        updateMediaPaths(r9, r10, r11, Collections.singletonList(r12), r13, -1, r14);
    }

    private void updateMediaPaths(MessageObject r26, TLRPC.Message r27, int r28, List<String> r29, boolean r30, int r31, HashMap<String, String> r32) {
        SendMessagesHelper r0 = this;
        TLRPC.Message r2 = r27;
        TLRPC.Message r6 = r26.messageOwner;
        if (r29.isEmpty() == false) goto L5;
    L6:
        List<String> r9 = r29;
        String r10 = null;
    L9:
        if (r2 != null) goto L11;
        TLRPC.MessageMedia r5 = null;
    L12:
        if (r6 != null) goto L14;
        TLRPC.MessageMedia r11 = null;
    L15:
        TLRPC.MessageMedia r12 = r6.media;
        if (r12 != null) goto L18;
        boolean r02 = r30;
        MessageObject r92 = r26;
        TLRPC.MessageMedia r122 = r11;
        TLRPC.TL_messageExtendedMedia r1 = null;
        TLRPC.PhotoSize r3 = null;
        TLRPC.TL_messageExtendedMedia r7 = null;
        boolean r15 = false;
        TLRPC.MessageMedia r112 = r5;
    L143:
        if (r2 == null) goto L597;
        boolean r292 = r15;
        TLRPC.TL_messageExtendedMedia r312 = r1;
        if ((r112 instanceof TLRPC.TL_messageMediaPhoto) == true) goto L148;
        TLRPC.TL_messageExtendedMedia r8 = r7;
        TLRPC.Message r72 = r2;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.Message r152 = r6;
        TLRPC.TL_messageExtendedMedia r62 = r8;
        SendMessagesHelper r82 = this;
    L301:
        if ((r112 instanceof TLRPC.TL_messageMediaDocument) == true) goto L303;
    L533:
        TLRPC.Message r23 = r72;
        TLRPC.MessageMedia r03 = r23.media;
        if ((r03 instanceof TLRPC.TL_messageMediaContact) == false) goto L540;
        if ((r152.media instanceof TLRPC.TL_messageMediaContact) == false) goto L540;
        r152.media = r03;
        return;
    L540:
        if ((r03 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L544;
        r152.media = r03;
        return;
    L544:
        if ((r03 instanceof TLRPC.TL_messageMediaGeo) == false) goto L548;
        TLRPC.GeoPoint r04 = r03.geo;
        TLRPC.GeoPoint r13 = r152.media.geo;
        r04.lat = r13.lat;
        r04._long = r13._long;
        return;
    L548:
        if ((r03 instanceof TLRPC.TL_messageMediaGame) == false) goto L550;
    L556:
        r152.media = r03;
        if (TextUtils.isEmpty(r23.message) == true) goto L559;
        r152.entities = r23.entities;
        r152.message = r23.message;
    L559:
        TLRPC.ReplyMarkup r05 = r23.reply_markup;
        if (r05 == null) goto L594;
        r152.reply_markup = r05;
        r152.flags |= 64;
        return;
    L594:
        return;
    L550:
        if ((r03 instanceof TLRPC.TL_messageMediaInvoice) == true) goto L556;
        if ((r03 instanceof TLRPC.TL_messageMediaPoll) == false) goto L593;
        r152.media = r03;
        return;
    L593:
        return;
    L303:
        if (r112.document == null) goto L533;
        if ((r122 instanceof TLRPC.TL_messageMediaDocument) == false) goto L533;
        if (r122.document == null) goto L533;
        if (r112.ttl_seconds != 0) goto L319;
        VideoEditedInfo r33 = r92.videoEditedInfo;
        if (r33 != null) goto L313;
    L320:
        boolean r34 = MessageObject.isVideoMessage(r72);
        if (r34 == false) goto L323;
    L327:
        String r17 = r22;
        if (MessageObject.isGifDocument(r112.document) == MessageObject.isGifDocument(r122.document)) goto L330;
    L326:
        String r21 = ".jpg";
        TLRPC.TL_messageExtendedMedia r222 = r62;
        if (MessageObject.isVoiceMessage(r72) == false) goto L340;
    L346:
        TLRPC.Photo r06 = r122.video_cover;
        if (r06 == null) goto L418;
        TLRPC.Photo r14 = r112.video_cover;
        if (r14 == null) goto L418;
        TLRPC.PhotoSize r24 = FileLoader.getClosestPhotoSizeWithSize(r06.sizes, 40);
        if (r14 == null) goto L354;
        r24 = FileLoader.getClosestPhotoSizeWithSize(r14.sizes, 40);
    L354:
        if (r06.sizes.size() == 1) goto L356;
    L359:
        int r35 = 0;
    L361:
        if (r35 >= r06.sizes.size()) goto L358;
        TLRPC.PhotoSize r4 = r06.sizes.get(r35);
        if (r4 != null) goto L365;
    L368:
        TLRPC.PhotoSize r282 = r24;
        int r52 = r35;
        String r232 = r10;
        String r102 = r21;
        TLRPC.Message r212 = r152;
    L416:
        r35 = r52 + 1;
        r82 = this;
        r24 = r282;
        r152 = r212;
        r21 = r102;
        r10 = r232;
        goto L361
    L365:
        if (r4.location == null) goto L368;
        if (r4.type == null) goto L368;
        int r53 = 0;
    L371:
        if (r53 >= r14.sizes.size()) goto L412;
        TLRPC.PhotoSize r63 = r14.sizes.get(r53);
        if (r63 == null) goto L410;
        int r16 = r53;
        if (r63.location != null) goto L377;
    L380:
        TLRPC.PhotoSize r283 = r24;
        int r313 = r35;
    L381:
        r232 = r10;
    L411:
        r53 = r16 + 1;
        r82 = this;
        r24 = r283;
        r35 = r313;
        r152 = r152;
        r21 = r21;
        r10 = r232;
        goto L371
    L377:
        if ((r63 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L380;
        String r54 = r63.type;
        if (r54 == null) goto L380;
        r232 = r10;
        if (r4.location.volume_id != (-2147483648L)) goto L388;
        if (r54.equals(r4.type) == false) goto L388;
    L391:
        String r55 = r4.location.volume_id + "_" + r4.location.local_id;
        StringBuilder r93 = new StringBuilder();
        r282 = r24;
        int r314 = r35;
        r93.append(r63.location.volume_id);
        r93.append("_");
        r93.append(r63.location.local_id);
        String r25 = r93.toString();
        if (r55.equals(r25) == false) goto L394;
        r102 = r21;
        r212 = r152;
    L408:
        r52 = r314;
        goto L416
    L394:
        r102 = r21;
        r212 = r152;
        File r36 = new File(FileLoader.getDirectory(4), r55.concat(r102));
        if (r112.ttl_seconds == 0) goto L397;
    L406:
        File r94 = new File(FileLoader.getDirectory(4), r25.concat(r102));
    L407:
        r36.renameTo(r94);
        ImageLoader.getInstance().replaceImageInCache(r55, r25, ImageLocation.getForPhoto(r63, r14), r30);
        r4.location = r63.location;
        r4.size = r63.size;
        goto L408
    L397:
        if (r14.sizes.size() == 1) goto L404;
        if (r63.w > 90) goto L404;
        if (r63.h <= 90) goto L406;
    L404:
        if (r292 == true) goto L406;
        r94 = FileLoader.getInstance(r82.currentAccount).getPathToAttach(r63, true);
    L388:
        if (r63.w == r4.w) goto L390;
    L409:
        r283 = r24;
        r313 = r35;
        goto L411
    L390:
        if (r63.h != r4.h) goto L409;
    L410:
        r283 = r24;
        r313 = r35;
        r16 = r53;
        goto L381
    L412:
        r282 = r24;
        r314 = r35;
        r232 = r10;
        r102 = r21;
        r212 = r152;
        String r210 = r4.location.volume_id + "_" + r4.location.local_id;
        new File(FileLoader.getDirectory(4), r210.concat(r102)).delete();
        if ("s".equals(r4.type) == false) goto L408;
        if (r282 == null) goto L408;
        r52 = r314;
        r06.sizes.set(r52, r282);
        ImageLocation r37 = ImageLocation.getForPhoto(r282, r14);
        ImageLoader.getInstance().replaceImageInCache(r210, r37.getKey(r72, null, false), r37, r30);
    L358:
        String r233 = r10;
        TLRPC.Message r213 = r152;
        byte[] r83 = null;
        r06.id = r14.id;
        r06.dc_id = r14.dc_id;
        r06.access_hash = r14.access_hash;
    L447:
        TLRPC.Document r07 = r122.document;
        TLRPC.Document r18 = r112.document;
        r07.dc_id = r18.dc_id;
        r07.id = r18.id;
        r07.access_hash = r18.access_hash;
        int r08 = 0;
    L449:
        if (r08 >= r122.document.attributes.size()) goto L454;
        TLRPC.DocumentAttribute r19 = r122.document.attributes.get(r08);
        if ((r19 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L452;
        r08 = r08 + 1;
        goto L449
    L452:
        r83 = r19.waveform;
    L454:
        r122.document.attributes = r112.document.attributes;
        if (r83 == null) goto L463;
        int r09 = 0;
    L458:
        if (r09 >= r122.document.attributes.size()) goto L463;
        TLRPC.DocumentAttribute r110 = r122.document.attributes.get(r09);
        if ((r110 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L462;
        r110.waveform = r83;
        r110.flags |= 4;
    L462:
        r09 = r09 + 1;
    L463:
        TLRPC.Document r010 = r122.document;
        TLRPC.Document r111 = r112.document;
        r010.size = r111.size;
        r010.mime_type = r111.mime_type;
        if ((r72.flags & 4) == 0) goto L466;
    L470:
        TLRPC.Message r211 = r72;
        String r64 = r17;
    L487:
        if (r222 == null) goto L489;
        TLRPC.TL_messageExtendedMedia r011 = r222;
        String r113 = r011.attachPath;
        TLRPC.Message r153 = r213;
    L490:
        if (r113 != null) goto L492;
    L528:
        if (r011 == null) goto L531;
        r011.attachPath = r113;
        return;
    L531:
        r211.attachPath = r113;
        r211.message = r153.message;
        return;
    L492:
        if (r113.startsWith(FileLoader.getDirectory(4).getAbsolutePath()) == false) goto L528;
        if (MessageObject.isGifDocument(r112.document) == true) goto L528;
        File r38 = new File(r113);
        FileLoader r42 = FileLoader.getInstance(this.currentAccount);
        TLRPC.Document r56 = r112.document;
        if (r112.ttl_seconds == 0) goto L498;
        boolean r73 = true;
    L499:
        File r43 = r42.getPathToAttach(r56, r73);
        if (r38.renameTo(r43) == true) goto L516;
        if (r38.exists() == false) goto L507;
        if (r011 == null) goto L506;
        r011.attachPath = r113;
    L505:
        MessageObject r95 = r26;
    L510:
        if (r011 != null) goto L513;
        r95.mediaExists = r43.exists();
    L513:
        r211.message = r153.message;
        return;
    L506:
        r211.attachPath = r113;
        goto L505
    L507:
        if (r011 != null) goto L505;
        r95 = r26;
        r95.attachPathExists = false;
        goto L510
    L516:
        if (MessageObject.isVideoMessage(r211) == false) goto L519;
        r26.attachPathExists = true;
        return;
    L519:
        r26.mediaExists = r26.attachPathExists;
        r26.attachPathExists = false;
        if (r011 == null) goto L522;
        r011.attachPath = r64;
    L523:
        if (r233 == null) goto L591;
        String r57 = r233;
        if (r57.startsWith("http") == false) goto L592;
        getMessagesStorage().addRecentLocalFile(r57, r43.toString(), r122.document);
        return;
    L592:
        return;
    L591:
        return;
    L522:
        r153.attachPath = r64;
        goto L523
    L498:
        r73 = false;
        goto L499
    L489:
        r153 = r213;
        r011 = r222;
        r113 = r153.attachPath;
        goto L490
    L466:
        if (MessageObject.isOut(r72) == true) goto L472;
        if (r72.dialog_id != getUserConfig().getClientUserId()) goto L470;
    L472:
        if (MessageObject.isQuickReply(r72) == true) goto L470;
        boolean r012 = MessageObject.isNewGifDocument(r112.document);
        TLRPC.Document r114 = r112.document;
        if (r012 == false) goto L483;
        if (MessageObject.isDocumentHasAttachedStickers(r114) == false) goto L478;
        boolean r013 = getMessagesController().saveGifsWithStickers;
    L479:
        if (r013 == false) goto L470;
        getMediaDataController().addRecentGif(r112.document, r72.date, true);
        goto L470
    L478:
        r013 = true;
        goto L479
    L483:
        if (MessageObject.isStickerDocument(r114) == false) goto L485;
    L486:
        r211 = r72;
        r64 = r17;
        getMediaDataController().addRecentSticker(0, r211, r112.document, r72.date, false);
        goto L487
    L485:
        if (MessageObject.isAnimatedStickerDocument(r112.document, true) == false) goto L470;
    L356:
        if ((r06.sizes.get(0).location instanceof TLRPC.TL_fileLocationUnavailable) == false) goto L359;
        r06.sizes = r14.sizes;
    L418:
        r233 = r10;
        String r103 = r21;
        r83 = null;
        r213 = r152;
        TLRPC.PhotoSize r014 = FileLoader.getClosestPhotoSizeWithSize(r122.document.thumbs, 320);
        TLRPC.PhotoSize r115 = FileLoader.getClosestPhotoSizeWithSize(r112.document.thumbs, 320);
        if (r014 == null) goto L434;
        TLRPC.FileLocation r214 = r014.location;
        if (r214 == null) goto L434;
        if (r214.volume_id != (-2147483648L)) goto L434;
        if (r115 == null) goto L434;
        if (r115.location == null) goto L434;
        if ((r115 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L434;
        if ((r014 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L434;
        String r215 = r014.location.volume_id + "_" + r014.location.local_id;
        String r39 = r115.location.volume_id + "_" + r115.location.local_id;
        if (r215.equals(r39) == true) goto L447;
        new File(FileLoader.getDirectory(4), r215.concat(r103)).renameTo(new File(FileLoader.getDirectory(4), r39.concat(r103)));
        ImageLoader.getInstance().replaceImageInCache(r215, r39, ImageLocation.getForDocument(r115, r112.document), r30);
        r014.location = r115.location;
        r014.size = r115.size;
    L434:
        if (r115 == null) goto L441;
        if (r014 == null) goto L441;
        if (MessageObject.isStickerMessage(r72) == false) goto L441;
        TLRPC.FileLocation r216 = r014.location;
        if (r216 == null) goto L441;
        r115.location = r216;
    L441:
        if (r014 != null) goto L443;
    L446:
        r122.document.thumbs = r112.document.thumbs;
        goto L447
    L443:
        if ((r014.location instanceof TLRPC.TL_fileLocationUnavailable) == true) goto L446;
        if ((r014 instanceof TLRPC.TL_photoSizeEmpty) == false) goto L447;
    L340:
        if (MessageObject.isRoundVideoMessage(r72) == true) goto L346;
        if (r92.scheduled == true) goto L346;
        if (MessageObject.isEphemeral(r72) == true) goto L346;
        MessageObject r015 = new MessageObject(r82.currentAccount, r72, false, false);
        r82.getMessagesStorage().putSentFile(r10, r112.document, 1, "sent_" + r72.peer_id.channel_id + "_" + r72.id + "_" + DialogObject.getPeerDialogId(r72.peer_id) + "_" + r015.type + "_" + r015.getSize());
        goto L346
    L330:
        if (r92.scheduled == false) goto L332;
    L334:
        r21 = ".jpg";
        boolean r315 = r34;
        r222 = r62;
    L335:
        if (r315 == false) goto L346;
        r72.attachPath = r152.attachPath;
        goto L346
    L332:
        if (MessageObject.isEphemeral(r72) == true) goto L334;
        r315 = r34;
        MessageObject r217 = new MessageObject(r82.currentAccount, r72, false, false);
        MessagesStorage r310 = r82.getMessagesStorage();
        TLRPC.Document r44 = r112.document;
        r222 = r62;
        StringBuilder r65 = new StringBuilder("sent_");
        r21 = ".jpg";
        r65.append(r72.peer_id.channel_id);
        r65.append("_");
        r65.append(r72.id);
        r65.append("_");
        r65.append(DialogObject.getPeerDialogId(r72.peer_id));
        r65.append("_");
        r65.append(r217.type);
        r65.append("_");
        r65.append(r217.getSize());
        r310.putSentFile(r10, r44, 2, r65.toString());
        goto L335
    L323:
        if (MessageObject.isGifMessage(r72) == true) goto L327;
        r17 = r22;
        goto L326
    L313:
        if (r33.mediaEntities != null) goto L319;
        if (TextUtils.isEmpty(r33.paintPath) == false) goto L319;
        if (r92.videoEditedInfo.cropState == null) goto L320;
    L319:
        r21 = ".jpg";
        r17 = r22;
        r222 = r62;
        goto L346
    L148:
        if (r112.photo != null) goto L150;
    L298:
        r82 = this;
        r152 = r6;
        r62 = r7;
        r72 = r2;
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L301
    L150:
        if ((r122 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L298;
        if (r122.photo == null) goto L298;
        if (r112.ttl_seconds == 0) goto L156;
    L172:
        TLRPC.Message r218 = r6;
        TLRPC.TL_messageExtendedMedia r223 = r7;
    L174:
        if (r122.photo.sizes.size() == 1) goto L176;
    L179:
        int r45 = 0;
    L181:
        if (r45 >= r122.photo.sizes.size()) goto L178;
        TLRPC.PhotoSize r58 = r122.photo.sizes.get(r45);
        if (r58 != null) goto L185;
    L188:
        TLRPC.Message r74 = r2;
    L232:
        r45 = r45 + 1;
        r2 = r74;
        goto L181
    L185:
        if (r58.location == null) goto L188;
        if (r58.type == null) goto L188;
        int r66 = 0;
    L191:
        if (r66 >= r112.photo.sizes.size()) goto L228;
        TLRPC.PhotoSize r75 = r112.photo.sizes.get(r66);
        if (r75 == null) goto L227;
        if (r75.location == null) goto L227;
        if ((r75 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L227;
        String r84 = r75.type;
        if (r84 == null) goto L227;
        if (r58.location.volume_id != (-2147483648L)) goto L206;
        if (r84.equals(r58.type) == false) goto L206;
    L209:
        String r67 = r58.location.volume_id + "_" + r58.location.local_id;
        String r85 = r75.location.volume_id + "_" + r75.location.local_id;
        if (r67.equals(r85) == true) goto L226;
        File r96 = new File(FileLoader.getDirectory(4), r67.concat(".jpg"));
        if (r112.ttl_seconds != 0) goto L224;
        if (r112.photo.sizes.size() != 1) goto L217;
    L222:
        if (r292 == true) goto L224;
        File r104 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r75);
    L225:
        r96.renameTo(r104);
        ImageLoader.getInstance().replaceImageInCache(r67, r85, ImageLocation.getForPhoto(r75, r112.photo), r02);
        r58.location = r75.location;
        r58.size = r75.size;
        goto L226
    L217:
        if (r75.w > 90) goto L222;
        if (r75.h > 90) goto L222;
    L224:
        r104 = new File(FileLoader.getDirectory(4), r85.concat(".jpg"));
    L226:
        r74 = r27;
    L206:
        if (r75.w != r58.w) goto L227;
        if (r75.h == r58.h) goto L209;
    L227:
        r66 = r66 + 1;
        goto L191
    L228:
        String r219 = r58.location.volume_id + "_" + r58.location.local_id;
        new File(FileLoader.getDirectory(4), r219.concat(".jpg")).delete();
        if ("s".equals(r58.type) == false) goto L226;
        if (r3 == null) goto L226;
        r122.photo.sizes.set(r45, r3);
        ImageLocation r59 = ImageLocation.getForPhoto(r3, r112.photo);
        r74 = r27;
        ImageLoader.getInstance().replaceImageInCache(r219, r59.getKey(r74, null, false), r59, r02);
    L178:
        TLRPC.Message r76 = r2;
        if (r26.isLivePhoto() == false) goto L290;
        String r016 = r26.messageOwner.attachPath;
        if (r32 == null) goto L238;
        String r116 = r32.getOrDefault("imagePath", null);
    L239:
        TLRPC.Photo r220 = r122.photo;
        TLRPC.Photo r311 = r112.photo;
        r220.id = r311.id;
        r220.dc_id = r311.dc_id;
        r220.access_hash = r311.access_hash;
        if (r116 != null) goto L242;
    L251:
        SendMessagesHelper r86 = this;
    L252:
        TLRPC.Document r117 = r76.media.document;
        if (r117 == null) goto L255;
        TLRPC.MessageMedia r221 = r26.messageOwner.media;
        r221.flags |= 16;
        r221.document = r117;
    L255:
        if (r016 != null) goto L257;
    L284:
        TLRPC.Message r154 = r218;
        TLRPC.TL_messageExtendedMedia r68 = r223;
        if (r68 == null) goto L288;
        r68.attachPath = r016;
        return;
    L288:
        r76.attachPath = r016;
        r76.message = r154.message;
        return;
    L257:
        if (r016.startsWith(FileLoader.getDirectory(4).getAbsolutePath()) == false) goto L284;
        File r118 = new File(r016);
        FileLoader r224 = FileLoader.getInstance(r86.currentAccount);
        TLRPC.Document r316 = r112.document;
        if (r112.ttl_seconds == 0) goto L261;
        boolean r155 = true;
    L262:
        File r225 = r224.getPathToAttach(r316, r155);
        if (r118.renameTo(r225) == false) goto L265;
        TLRPC.Message r156 = r218;
        TLRPC.TL_messageExtendedMedia r69 = r223;
        r26.mediaExists = r26.attachPathExists;
        r26.attachPathExists = false;
        if (r69 == null) goto L282;
        r69.attachPath = _UrlKt.FRAGMENT_ENCODE_SET;
        return;
    L282:
        r156.attachPath = _UrlKt.FRAGMENT_ENCODE_SET;
        return;
    L265:
        if (r118.exists() == false) goto L269;
        if (r223 == null) goto L268;
        TLRPC.TL_messageExtendedMedia r610 = r223;
        r610.attachPath = r016;
    L273:
        if (r610 != null) goto L276;
        r26.mediaExists = r225.exists();
    L276:
        r76.message = r218.message;
        return;
    L268:
        r610 = r223;
        r76.attachPath = r016;
        goto L273
    L269:
        r610 = r223;
        if (r610 != null) goto L273;
        r26.attachPathExists = false;
        goto L273
    L261:
        r155 = false;
        goto L262
    L242:
        if (r116.startsWith(FileLoader.getDirectory(4).getAbsolutePath()) == false) goto L251;
        File r226 = new File(r116);
        r86 = this;
        FileLoader r119 = FileLoader.getInstance(r86.currentAccount);
        TLRPC.Photo r317 = r112.photo;
        if (r112.ttl_seconds == 0) goto L246;
        boolean r46 = true;
    L248:
        if (r226.renameTo(r119.getPathToAttach(r317, r46)) == false) goto L252;
        r26.mediaExists = r26.attachPathExists;
        r26.attachPathExists = false;
        goto L252
    L246:
        r46 = false;
        goto L248
    L238:
        r116 = null;
        goto L239
    L290:
        TLRPC.Message r157 = r218;
        TLRPC.TL_messageExtendedMedia r611 = r223;
        if (r292 == true) goto L293;
        r157.message = r76.message;
        r76.attachPath = r157.attachPath;
    L296:
        TLRPC.Photo r017 = r122.photo;
        TLRPC.Photo r120 = r112.photo;
        r017.id = r120.id;
        r017.dc_id = r120.dc_id;
        r017.access_hash = r120.access_hash;
        return;
    L293:
        if (r611 == null) goto L296;
        if (r312 == null) goto L296;
        r611.attachPath = r312.attachPath;
        goto L296
    L176:
        if ((r122.photo.sizes.get(0).location instanceof TLRPC.TL_fileLocationUnavailable) == false) goto L179;
        r122.photo.sizes = r112.photo.sizes;
        goto L178
    L156:
        if (r92.scheduled == true) goto L172;
        if (MessageObject.isEphemeral(r2) == true) goto L172;
        boolean r158 = r92.sentHighQuality;
        if (r158 == false) goto L163;
        int r162 = 6;
    L165:
        if (r112.document == null) goto L170;
        if (r158 == false) goto L168;
        int r159 = 8;
    L169:
        r218 = r6;
        r223 = r7;
        getMessagesStorage().putSentFile(r10, r112.photo, r159, "sent_" + r2.peer_id.channel_id + "_" + r2.id + "_" + DialogObject.getPeerDialogId(r2.peer_id) + "_1_" + MessageObject.getMediaSize(r122));
        goto L174
    L168:
        r159 = 2;
        goto L169
    L170:
        r159 = r162;
        goto L169
    L163:
        r162 = 0;
        goto L165
    L597:
        return;
    L18:
        if (r12.storyItem == null) goto L22;
        r2.media = r12;
    L20:
        r02 = r30;
        r92 = r26;
        TLRPC.TL_messageExtendedMedia r293 = null;
        TLRPC.PhotoSize r318 = null;
        TLRPC.PhotoSize r77 = null;
        TLRPC.TL_messageExtendedMedia r123 = null;
        TLObject r132 = null;
        r15 = false;
    L132:
        if ((r77 instanceof TLRPC.TL_photoStrippedSize) == true) goto L134;
    L140:
        TLRPC.MessageMedia r319 = r5;
        TLRPC.MessageMedia r510 = r11;
        TLRPC.TL_messageExtendedMedia r172 = r123;
    L141:
        r112 = r319;
        r122 = r510;
        r3 = r77;
        r1 = r172;
        r7 = r293;
        goto L143
    L134:
        if ((r318 instanceof TLRPC.TL_photoStrippedSize) == false) goto L140;
        StringBuilder r121 = new StringBuilder("stripped");
        r319 = r5;
        r121.append(FileRefController.getKeyForParentObject(r92));
        String r124 = r121.toString();
        if (r2 == null) goto L138;
        String r320 = "stripped" + FileRefController.getKeyForParentObject(r2);
        r510 = r11;
        r172 = r123;
    L139:
        ImageLoader.getInstance().replaceImageInCache(r124, r320, ImageLocation.getForObject(r77, r132), r02);
        goto L141
    L138:
        StringBuilder r321 = new StringBuilder("strippedmessage");
        r321.append(r28);
        r321.append("_");
        r510 = r11;
        r172 = r123;
        r321.append(r92.getChannelId());
        r321.append("_");
        r321.append(r92.scheduled);
        r320 = r321.toString();
        goto L139
    L22:
        if (r26.isLiveLocation() == false) goto L26;
        TLRPC.MessageMedia r78 = r2.media;
        if ((r78 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L26;
        r6.media.period = r78.period;
    L26:
        boolean r79 = r26.isDice();
        TLRPC.MessageMedia r125 = r6.media;
        if (r79 == false) goto L29;
        TLRPC.TL_messageMediaDice r126 = (TLRPC.TL_messageMediaDice) r125;
        TLRPC.TL_messageMediaDice r322 = (TLRPC.TL_messageMediaDice) r2.media;
        r126.value = r322.value;
        r126.flags = r322.flags;
        r126.game_outcome = r322.game_outcome;
        StarsController.getInstance(r0.currentAccount, true).invalidateBalance();
        goto L20
    L29:
        TLRPC.Photo r710 = r125.photo;
        if (r710 == null) goto L41;
        r318 = FileLoader.getClosestPhotoSizeWithSize(r710.sizes, 40);
        if (r2 == null) goto L38;
        TLRPC.MessageMedia r711 = r2.media;
        if (r711 == null) goto L38;
        TLRPC.Photo r712 = r711.photo;
        if (r712 == null) goto L38;
        r77 = FileLoader.getClosestPhotoSizeWithSize(r712.sizes, 40);
    L39:
        TLObject r97 = r6.media.photo;
    L40:
        r02 = r30;
        r15 = false;
        r132 = r97;
        r293 = null;
        r123 = null;
        r92 = r26;
    L38:
        r77 = r318;
        goto L39
    L41:
        TLRPC.Document r713 = r125.document;
        if (r713 == null) goto L52;
        r318 = FileLoader.getClosestPhotoSizeWithSize(r713.thumbs, 40);
        if (r2 == null) goto L50;
        TLRPC.MessageMedia r714 = r2.media;
        if (r714 == null) goto L50;
        TLRPC.Document r715 = r714.document;
        if (r715 == null) goto L50;
        r77 = FileLoader.getClosestPhotoSizeWithSize(r715.thumbs, 40);
    L51:
        r97 = r6.media.document;
    L50:
        r77 = r318;
        goto L51
    L52:
        TLRPC.WebPage r716 = r125.webpage;
        if (r716 == null) goto L81;
        TLRPC.Photo r323 = r716.photo;
        if (r323 == null) goto L67;
        r318 = FileLoader.getClosestPhotoSizeWithSize(r323.sizes, 40);
        if (r2 == null) goto L65;
        TLRPC.MessageMedia r717 = r2.media;
        if (r717 == null) goto L65;
        TLRPC.WebPage r718 = r717.webpage;
        if (r718 == null) goto L65;
        TLRPC.Photo r719 = r718.photo;
        if (r719 == null) goto L65;
        r77 = FileLoader.getClosestPhotoSizeWithSize(r719.sizes, 40);
    L66:
        r97 = r6.media.webpage.photo;
    L65:
        r77 = r318;
        goto L66
    L67:
        TLRPC.Document r324 = r716.document;
        if (r324 == null) goto L20;
        r318 = FileLoader.getClosestPhotoSizeWithSize(r324.thumbs, 40);
        if (r2 == null) goto L78;
        TLRPC.MessageMedia r720 = r2.media;
        if (r720 == null) goto L78;
        TLRPC.WebPage r721 = r720.webpage;
        if (r721 == null) goto L78;
        TLRPC.Document r722 = r721.document;
        if (r722 == null) goto L78;
        r77 = FileLoader.getClosestPhotoSizeWithSize(r722.thumbs, 40);
    L79:
        r97 = r6.media.webpage.document;
    L78:
        r77 = r318;
        goto L79
    L81:
        if ((r125 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L20;
        if ((r5 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L20;
        TLRPC.TL_messageMediaPaidMedia r127 = (TLRPC.TL_messageMediaPaidMedia) r125;
        TLRPC.TL_messageMediaPaidMedia r723 = (TLRPC.TL_messageMediaPaidMedia) r5;
        if (r127.extended_media.isEmpty() == false) goto L87;
        return;
    L87:
        if (r723.extended_media.isEmpty() == false) goto L90;
        return;
    L90:
        if (r31 != (-1)) goto L95;
        int r612 = 0;
    L93:
        if (r612 >= r127.extended_media.size()) goto L598;
        r0.updateMediaPaths(r26, r2, r28, r9, r30, r612, r2.params);
        r612 = r612 + 1;
        r0 = this;
        r9 = r29;
        goto L93
    L598:
        return;
    L95:
        r02 = r30;
        r92 = r26;
        if (r723.extended_media.size() <= 1) goto L98;
        boolean r128 = true;
    L99:
        if (r31 >= 0) goto L101;
        return;
    L101:
        if (r31 >= r723.extended_media.size()) goto L596;
        TLRPC.MessageExtendedMedia r724 = r723.extended_media.get(r31);
        if ((r724 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L106;
        TLRPC.TL_messageExtendedMedia r511 = (TLRPC.TL_messageExtendedMedia) r724;
        TLRPC.TL_messageExtendedMedia r725 = r511;
        r5 = r511.media;
    L107:
        TLRPC.MessageExtendedMedia r325 = r127.extended_media.get(r31);
        if ((r325 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L129;
        TLRPC.TL_messageExtendedMedia r326 = (TLRPC.TL_messageExtendedMedia) r325;
        r11 = r326.media;
        TLRPC.Photo r129 = r11.photo;
        if (r129 == null) goto L118;
        TLRPC.PhotoSize r1210 = FileLoader.getClosestPhotoSizeWithSize(r129.sizes, 40);
        if (r5 == null) goto L116;
        TLRPC.Photo r1510 = r5.photo;
        if (r1510 == null) goto L116;
        TLRPC.PhotoSize r1511 = FileLoader.getClosestPhotoSizeWithSize(r1510.sizes, 40);
    L117:
        r132 = r11.photo;
    L128:
        r293 = r326;
        r318 = r1210;
        r123 = r725;
        r77 = r1511;
        r15 = r128;
    L116:
        r1511 = r1210;
        goto L117
    L118:
        TLRPC.Document r1211 = r11.document;
        if (r1211 == null) goto L127;
        r1210 = FileLoader.getClosestPhotoSizeWithSize(r1211.thumbs, 40);
        if (r5 == null) goto L125;
        TLRPC.Document r1512 = r5.document;
        if (r1512 == null) goto L125;
        r1511 = FileLoader.getClosestPhotoSizeWithSize(r1512.thumbs, 40);
    L126:
        r132 = r11.document;
    L125:
        r1511 = r1210;
        goto L126
    L127:
        r1210 = null;
        r132 = null;
        r1511 = null;
        goto L128
    L129:
        r15 = r128;
        r123 = r725;
        r293 = null;
        r318 = null;
        r77 = null;
        r132 = null;
        goto L132
    L106:
        r725 = null;
        goto L107
    L596:
        return;
    L98:
        r128 = false;
        goto L99
    L14:
        r11 = r6.media;
        goto L15
    L11:
        r5 = r2.media;
        goto L12
    L5:
        if (Math.max(0, r31) >= r29.size()) goto L6;
        r9 = r29;
        r10 = r9.get(Math.max(0, r31));
        goto L9
    }

    private void uploadMultiMedia(DelayedMessage r5, TLRPC.InputMedia r6, TLRPC.InputEncryptedFile r7, String r8) {
        int r1 = 0;
        if (r6 == null) goto L59;
        TLRPC.TL_messages_uploadMedia r72 = new TLRPC.TL_messages_uploadMedia();
        r72.media = r6;
        TLObject r2 = r5.sendRequest;
        if ((r2 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L14;
        TLRPC.TL_messages_sendMultiMedia r22 = (TLRPC.TL_messages_sendMultiMedia) r2;
        r72.peer = r22.peer;
    L8:
        if (r1 >= r22.multi_media.size()) goto L57;
        if (r22.multi_media.get(r1).media == r6) goto L11;
        r1 = r1 + 1;
        goto L8
    L11:
        putToSendingMessages(r5.messages.get(r1), r5.scheduled);
        getNotificationCenter().postNotificationName(NotificationCenter.fileUploadProgressChanged, new Object[]{r8, -1L, -1L, Boolean.FALSE});
    L57:
        getConnectionsManager().sendRequest(r72, new SendMessagesHelper$$ExternalSyntheticLambda61(this, r6, r5));
        return;
    L14:
        if ((r2 instanceof TLRPC.TL_messages_sendMedia) == false) goto L25;
        if ((((TLRPC.TL_messages_sendMedia) r2).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L25;
        TLRPC.TL_messages_sendMedia r23 = (TLRPC.TL_messages_sendMedia) r2;
        r72.peer = r23.peer;
        TLRPC.TL_inputMediaPaidMedia r24 = (TLRPC.TL_inputMediaPaidMedia) r23.media;
    L19:
        if (r1 >= r24.extended_media.size()) goto L57;
        if (r24.extended_media.get(r1) == r6) goto L22;
        r1 = r1 + 1;
        goto L19
    L22:
        putToSendingMessages(r5.messages.get(r1), r5.scheduled);
        getNotificationCenter().postNotificationName(NotificationCenter.fileUploadProgressChanged, new Object[]{r8, -1L, -1L, Boolean.FALSE});
    L25:
        if ((r2 instanceof TLRPC.TL_messages_sendMedia) == false) goto L36;
        if ((((TLRPC.TL_messages_sendMedia) r2).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L36;
        TLRPC.TL_messages_sendMedia r25 = (TLRPC.TL_messages_sendMedia) r2;
        r72.peer = r25.peer;
        int r26 = PollAttachedMediaPack.findInputMedia((TLRPC.TL_inputMediaPoll) r25.media, r6);
    L30:
        if (r1 >= r5.pollIndexes.size()) goto L57;
        if (r5.pollIndexes.get(r1).intValue() == r26) goto L33;
        r1 = r1 + 1;
        goto L30
    L33:
        putToSendingMessages(r5.messages.get(r1), r5.scheduled);
        getNotificationCenter().postNotificationName(NotificationCenter.fileUploadProgressChanged, new Object[]{r8, -1L, -1L, Boolean.FALSE});
    L36:
        if ((r2 instanceof TL_ephemeral.TL_sendMessage) == false) goto L47;
        if ((((TL_ephemeral.TL_sendMessage) r2).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L47;
        TL_ephemeral.TL_sendMessage r27 = (TL_ephemeral.TL_sendMessage) r2;
        r72.peer = r27.peer;
        TLRPC.TL_inputMediaPaidMedia r28 = (TLRPC.TL_inputMediaPaidMedia) r27.media;
    L41:
        if (r1 >= r28.extended_media.size()) goto L57;
        if (r28.extended_media.get(r1) == r6) goto L44;
        r1 = r1 + 1;
        goto L41
    L44:
        putToSendingMessages(r5.messages.get(r1), r5.scheduled);
        getNotificationCenter().postNotificationName(NotificationCenter.fileUploadProgressChanged, new Object[]{r8, -1L, -1L, Boolean.FALSE});
    L47:
        if ((r2 instanceof TL_ephemeral.TL_sendMessage) == false) goto L57;
        if ((((TL_ephemeral.TL_sendMessage) r2).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L57;
        TL_ephemeral.TL_sendMessage r29 = (TL_ephemeral.TL_sendMessage) r2;
        r72.peer = r29.peer;
        int r210 = PollAttachedMediaPack.findInputMedia((TLRPC.TL_inputMediaPoll) r29.media, r6);
    L52:
        if (r1 >= r5.pollIndexes.size()) goto L57;
        if (r5.pollIndexes.get(r1).intValue() == r210) goto L55;
        r1 = r1 + 1;
        goto L52
    L55:
        putToSendingMessages(r5.messages.get(r1), r5.scheduled);
        getNotificationCenter().postNotificationName(NotificationCenter.fileUploadProgressChanged, new Object[]{r8, -1L, -1L, Boolean.FALSE});
        goto L57
    L59:
        if (r7 == null) goto L81;
        TLRPC.TL_messages_sendEncryptedMultiMedia r62 = (TLRPC.TL_messages_sendEncryptedMultiMedia) r5.sendEncryptedRequest;
        int r211 = 0;
    L62:
        if (r211 >= r62.files.size()) goto L67;
        if (r62.files.get(r211) == r7) goto L65;
        r211 = r211 + 1;
        goto L62
    L65:
        putToSendingMessages(r5.messages.get(r211), r5.scheduled);
        getNotificationCenter().postNotificationName(NotificationCenter.fileUploadProgressChanged, new Object[]{r8, -1L, -1L, Boolean.FALSE});
    L67:
        sendReadyToSendGroup(r5, false, true);
        return;
    }

    private void writePreviousMessageData(TLRPC.Message r3, SerializedData r4) {
        TLRPC.MessageMedia r2 = r3.media;
        if (r2 != null) goto L5;
        new TLRPC.TL_messageMediaEmpty().serializeToStream(r4);
    L6:
        String r22 = r3.message;
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r22 != null) goto L10;
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r4.writeString(r22);
        String r23 = r3.attachPath;
        if (r23 == null) goto L13;
        r0 = r23;
    L13:
        r4.writeString(r0);
        int r24 = r3.entities.size();
        r4.writeInt32(r24);
        int r02 = 0;
    L14:
        if (r02 >= r24) goto L16;
        r3.entities.get(r02).serializeToStream(r4);
        r02 = r02 + 1;
        goto L14
    L16:
        return;
    L5:
        r2.serializeToStream(r4);
        goto L6
    }

    public void addPollOption(MessageObject r30, CharSequence r31, PollAttachedMedia r32) {
        if (r30 == null) goto L29;
        TLRPC.MessageMedia r1 = MessageObject.getMedia(r30);
        if ((r1 instanceof TLRPC.TL_messageMediaPoll) == true) goto L8;
        return;
    L8:
        long r4 = r30.getDialogId();
        r30.getId();
        TLRPC.TL_inputPollAnswer r8 = new TLRPC.TL_inputPollAnswer();
        r8.text = new TLRPC.TL_textWithEntities();
        r8.option = new byte[]{(byte) (((TLRPC.TL_messageMediaPoll) r1).poll.answers.size() + 48)};
        if (r31 == null) goto L12;
        CharSequence[] r12 = {r31};
        r8.text.entities = getMediaDataController().getEntities(r12, true);
        r8.text.text = r12[0].toString();
    L12:
        if ((r32 instanceof PollAttachedMediaGallery) == false) goto L16;
        ArrayList r3 = new ArrayList(1);
        r3.add(((PollAttachedMediaGallery) r32).sendingMediaInfo);
        prepareSendingMedia(getAccountInstance(), r3, r4, null, null, null, null, false, false, r30, r8, false, 0, 0, 0, false, null, null, 0, false, 0, 0, null);
        return;
    L16:
        if ((r32 instanceof PollAttachedMediaSticker) == false) goto L20;
        PollAttachedMediaSticker r0 = (PollAttachedMediaSticker) r32;
        editMessage(r30, r8, null, null, (TLRPC.TL_document) r0.sticker, null, null, null, false, false, r0.parent);
        return;
    L20:
        if ((r32 instanceof PollAttachedMediaLocation) == false) goto L24;
        r8.input_media = TlUtils.toInputMediaGeo(((PollAttachedMediaLocation) r32).media);
        editMessage(r30, r8, null, null, null, null, null, null, false, false, null);
        return;
    L24:
        if ((r32 instanceof PollAttachedMediaLink) == false) goto L27;
        TLRPC.TL_inputMediaWebPage r13 = new TLRPC.TL_inputMediaWebPage();
        r13.url = ((PollAttachedMediaLink) r32).url;
        r13.optional = true;
        r8.input_media = r13;
        editMessage(r30, r8, null, null, null, null, null, null, false, false, null);
        return;
    L27:
        editMessage(r30, r8, null, null, null, null, null, null, false, false, null);
        return;
    }

    public void cancelSendingMessage(ArrayList<MessageObject> r28) {
        ArrayList r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList<Integer> r5 = new ArrayList();
        long r8 = 0;
        int r6 = 0;
        boolean r7 = false;
        int r10 = 0;
        int r11 = 0;
    L3:
        boolean r13 = true;
        if (r6 >= r28.size()) goto L82;
        MessageObject r82 = r28.get(r6);
        if (r82.scheduled == false) goto L8;
        int r21 = 1;
    L9:
        long r9 = r82.getDialogId();
        r5.add(Integer.valueOf(r82.getId()));
        if (r82.isQuickReply() == false) goto L12;
        r11 = r82.getQuickReplyId();
    L12:
        TLRPC.Message r12 = removeFromSendingMessages(r82.getId(), r82.scheduled);
        if (r12 == null) goto L15;
        getConnectionsManager().cancelRequest(r12.reqId, true);
    L15:
        StarsController.getInstance(this.currentAccount).hidePaidMessageToast(r82);
        Iterator<Map.Entry<String, ArrayList<DelayedMessage>>> r122 = this.delayedMessages.entrySet().iterator();
    L17:
        if (r122.hasNext() == false) goto L81;
        Map.Entry<String, ArrayList<DelayedMessage>> r14 = r122.next();
        ArrayList<DelayedMessage> r15 = r14.getValue();
        boolean r24 = r13;
        int r4 = 0;
    L20:
        if (r4 >= r15.size()) goto L79;
        DelayedMessage r132 = r15.get(r4);
        ArrayList<Integer> r25 = r5;
        int r26 = r6;
        if (r132.type == 4) goto L23;
        if (r132.obj.getId() == r82.getId()) goto L73;
        r4 = r4 + 1;
        r5 = r25;
        r6 = r26;
        goto L20
    L73:
        removeFromUploadingMessages(r82.getId(), r82.scheduled);
        r15.remove(r4);
        r132.sendDelayedRequests();
        MediaController.getInstance().cancelVideoConvert(r132.obj);
        if (r15.size() != 0) goto L80;
        r2.add(r14.getKey());
        if (r132.sendEncryptedRequest == null) goto L80;
        r7 = r24;
    L80:
        r13 = r24;
        r5 = r25;
        r6 = r26;
        goto L17
    L23:
        MessageObject r42 = null;
        int r52 = 0;
    L25:
        if (r52 >= r132.messageObjects.size()) goto L30;
        r42 = r132.messageObjects.get(r52);
        if (r42.getId() == r82.getId()) goto L28;
        r52 = r52 + 1;
        goto L25
    L28:
        removeFromUploadingMessages(r82.getId(), r82.scheduled);
    L31:
        if (r52 < 0) goto L80;
        r132.messageObjects.remove(r52);
        r132.messages.remove(r52);
        r132.originalPaths.remove(r52);
        if (r132.parentObjects.isEmpty() == true) goto L35;
        r132.parentObjects.remove(r52);
    L35:
        TLObject r62 = r132.sendRequest;
        if ((r62 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L39;
        ((TLRPC.TL_messages_sendMultiMedia) r62).multi_media.remove(r52);
    L59:
        MediaController.getInstance().cancelVideoConvert(r82);
        String r43 = (String) r132.extraHashMap.get(r42);
        if (r43 == null) goto L63;
        r2.add(r43);
    L63:
        if (r132.messageObjects.isEmpty() == false) goto L66;
        r132.sendDelayedRequests();
        goto L80
    L66:
        if (r132.finalGroupMessage != r82.getId()) goto L69;
        MessageObject r44 = r132.messageObjects.get(r4.size() - 1);
        r132.finalGroupMessage = r44.getId();
        r44.messageOwner.params.put("final", "1");
        TLRPC.TL_messages_messages r152 = new TLRPC.TL_messages_messages();
        r152.messages.add(r44.messageOwner);
        getMessagesStorage().putMessages(r152, r132.peer, -2, 0, false, r21, 0);
    L69:
        if (r3.contains(r132) == true) goto L80;
        r3.add(r132);
        goto L80
    L39:
        if ((r62 instanceof TLRPC.TL_messages_sendMedia) == false) goto L44;
        if ((((TLRPC.TL_messages_sendMedia) r62).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L44;
        ((TLRPC.TL_inputMediaPaidMedia) ((TLRPC.TL_messages_sendMedia) r62).media).extended_media.remove(r52);
    L44:
        if ((r62 instanceof TLRPC.TL_messages_sendMedia) == false) goto L49;
        if ((((TLRPC.TL_messages_sendMedia) r62).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L49;
        PollAttachedMediaPack.removeInputMedia((TLRPC.TL_inputMediaPoll) ((TLRPC.TL_messages_sendMedia) r62).media, r132.pollIndexes.get(r52).intValue());
    L49:
        if ((r62 instanceof TL_ephemeral.TL_sendMessage) == false) goto L54;
        if ((((TL_ephemeral.TL_sendMessage) r62).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L54;
        ((TLRPC.TL_inputMediaPaidMedia) ((TL_ephemeral.TL_sendMessage) r62).media).extended_media.remove(r52);
    L54:
        if ((r62 instanceof TL_ephemeral.TL_sendMessage) == true) goto L56;
    L58:
        TLRPC.TL_messages_sendEncryptedMultiMedia r63 = (TLRPC.TL_messages_sendEncryptedMultiMedia) r132.sendEncryptedRequest;
        r63.messages.remove(r52);
        r63.files.remove(r52);
        goto L59
    L56:
        if ((((TL_ephemeral.TL_sendMessage) r62).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L58;
        PollAttachedMediaPack.removeInputMedia((TLRPC.TL_inputMediaPoll) ((TL_ephemeral.TL_sendMessage) r62).media, r132.pollIndexes.get(r52).intValue());
        goto L59
    L30:
        r52 = -1;
        goto L31
    L79:
        r25 = r5;
        r26 = r6;
        goto L80
    L81:
        r6 = r6 + 1;
        r8 = r9;
        r10 = r21;
        goto L3
    L8:
        r21 = r10;
        goto L9
    L82:
        ArrayList<Integer> r252 = r5;
        int r45 = 0;
    L84:
        if (r45 >= r2.size()) goto L90;
        String r53 = (String) r2.get(r45);
        if (r53.startsWith("http") == false) goto L88;
        ImageLoader.getInstance().cancelLoadHttpFile(r53);
    L89:
        this.delayedMessages.remove(r53);
        r45 = r45 + 1;
        goto L84
    L88:
        getFileLoader().cancelFileUpload(r53, r7);
        goto L89
    L90:
        int r22 = r3.size();
        int r46 = 0;
    L91:
        if (r46 >= r22) goto L94;
        sendReadyToSendGroup((DelayedMessage) r3.get(r46), false, true);
        r46 = r46 + 1;
        goto L91
    L94:
        if (r28.size() != 1) goto L102;
        if (r28.get(0).isEditing() == false) goto L102;
        if (r28.get(0).previousMedia == null) goto L102;
        revertEditingMessageObject(r28.get(0));
        return;
    L102:
        if (r28.size() != 1) goto L109;
        if (r28.get(0).isEditing() == false) goto L109;
        if (r28.get(0).isPoll() == false) goto L109;
        return;
    L109:
        if (r28.isEmpty() == true) goto L115;
        if (r28.get(0).isWelcomeMessage() == false) goto L115;
        int r47 = 9;
    L113:
        int r123 = r47;
    L122:
        getMessagesController().deleteMessages(r252, null, null, r8, r11, false, r123);
        return;
    L115:
        if (r28.isEmpty() == false) goto L117;
    L119:
        if (r10 == 0) goto L121;
        r123 = 1;
        goto L122
    L121:
        r123 = 0;
        goto L122
    L117:
        if (r28.get(0).isQuickReply() == false) goto L119;
        r47 = 5;
        goto L113
    }

    public void cancelSendingMessage(MessageObject r11) {
        ArrayList<MessageObject> r0 = new ArrayList();
        r0.add(r11);
        if (r11 != null) goto L5;
    L26:
        cancelSendingMessage(r0);
        return;
    L5:
        if (r11.type != 29) goto L26;
        Iterator<Map.Entry<String, ArrayList<DelayedMessage>>> r1 = this.delayedMessages.entrySet().iterator();
        DelayedMessage r2 = null;
    L8:
        if (r1.hasNext() == false) goto L24;
        ArrayList<DelayedMessage> r3 = r1.next().getValue();
        int r5 = 0;
    L11:
        if (r5 >= r3.size()) goto L8;
        DelayedMessage r6 = r3.get(r5);
        if (r6.type != 4) goto L21;
        int r7 = 0;
    L16:
        if (r7 >= r6.messageObjects.size()) goto L21;
        if (r6.messageObjects.get(r7).getId() == r11.getId()) goto L19;
        r7 = r7 + 1;
        goto L16
    L19:
        r2 = r6;
    L21:
        if (r2 != null) goto L8;
        r5 = r5 + 1;
        goto L11
    L24:
        if (r2 == null) goto L26;
        r0.clear();
        r0.addAll(r2.messageObjects);
        goto L26
    }

    public void checkUnsentMessages() {
        getMessagesStorage().getUnsentMessages(1000);
    }

    public void cleanup() {
        this.delayedMessages.clear();
        this.unsentMessages.clear();
        this.sendingMessages.clear();
        this.editingMessages.clear();
        this.sendingMessagesIdDialogs.clear();
        this.uploadMessages.clear();
        this.uploadingMessagesIdDialogs.clear();
        this.waitingForLocation.clear();
        this.waitingForCallback.clear();
        this.waitingForVote.clear();
        this.importingHistoryFiles.clear();
        this.importingHistoryMap.clear();
        this.importingStickersFiles.clear();
        this.importingStickersMap.clear();
        this.locationProvider.stop();
    }

    public TLRPC.InputReplyTo createReplyInput(int r3) {
        return createReplyInput(null, r3, 0, null);
    }

    public TLRPC.InputReplyTo createReplyInput(TLRPC.InputPeer r2, int r3, int r4, ChatActivity.ReplyQuote r5) {
        TLRPC.TL_inputReplyToMessage r0 = new TLRPC.TL_inputReplyToMessage();
        r0.reply_to_msg_id = r3;
        if (r4 == 0) goto L5;
        r0.flags |= 1;
        r0.top_msg_id = r4;
    L5:
        if (r5 != null) goto L7;
    L11:
        if (r5 != null) goto L13;
    L17:
        if (r5 != null) goto L19;
    L30:
        if (r5 == null) goto L38;
        MessageObject r32 = r5.message;
        if (r32 == null) goto L38;
        TLRPC.InputPeer r1 = getMessagesController().getInputPeer(r32.getDialogId());
        if (r1 == null) goto L38;
        if (MessageObject.peersEqual(r1, r2) == true) goto L38;
        r0.flags |= 2;
        r0.reply_to_peer_id = r1;
    L38:
        return r0;
    L19:
        if (r5.todo == true) goto L30;
        if (r5.poll == true) goto L30;
        String r33 = r5.getText();
        r0.quote_text = r33;
        if (TextUtils.isEmpty(r33) == true) goto L30;
        r0.flags |= 4;
        ArrayList<TLRPC.MessageEntity> r34 = r5.getFilteredEntities();
        r0.quote_entities = r34;
        if (r34 != null) goto L27;
    L29:
        r0.flags |= 16;
        r0.quote_offset = r5.start;
        goto L30
    L27:
        if (r34.isEmpty() == true) goto L29;
        r0.quote_entities = new ArrayList(r0.quote_entities);
        r0.flags |= 8;
        goto L29
    L13:
        if (r5.todo == false) goto L17;
        TLRPC.TodoItem r35 = r5.task;
        if (r35 == null) goto L17;
        r0.flags |= 64;
        r0.todo_item_id = r35.id;
        goto L30
    L7:
        if (r5.poll == false) goto L11;
        TLRPC.PollAnswer r36 = r5.answer;
        if (r36 == null) goto L11;
        r0.poll_option = r36.option;
        goto L30
    }

    public TLRPC.InputReplyTo createReplyInput(TLRPC.TL_messageReplyHeader r4) {
        TLRPC.TL_inputReplyToMessage r0 = new TLRPC.TL_inputReplyToMessage();
        r0.reply_to_msg_id = r4.reply_to_msg_id;
        int r1 = r4.flags;
        if ((r1 & 2) == 0) goto L6;
        r0.flags |= 1;
        r0.top_msg_id = r4.reply_to_top_id;
    L6:
        if ((r1 & 1) == 0) goto L9;
        r0.flags |= 2;
        r0.reply_to_peer_id = MessagesController.getInstance(this.currentAccount).getInputPeer(r4.reply_to_peer_id);
    L9:
        if (r4.quote == false) goto L20;
        int r3 = r4.flags;
        if ((r3 & 64) == 0) goto L14;
        r0.flags |= 4;
        r0.quote_text = r4.quote_text;
    L14:
        if ((r3 & 128) == 0) goto L17;
        r0.flags |= 8;
        r0.quote_entities = r4.quote_entities;
    L17:
        if ((r3 & 1024) == 0) goto L20;
        r0.flags |= 16;
        r0.quote_offset = r4.quote_offset;
    L20:
        if ((r4.flags & 2048) == 0) goto L22;
        r0.flags |= 64;
        r0.todo_item_id = r4.todo_item_id;
    L22:
        byte[] r32 = r4.poll_option;
        if (r32 == null) goto L25;
        r0.poll_option = r32;
    L25:
        return r0;
    }

    public TLRPC.InputReplyTo createReplyInput(TL_stories.StoryItem r4) {
        TLRPC.TL_inputReplyToStory r0 = new TLRPC.TL_inputReplyToStory();
        r0.story_id = r4.id;
        r0.peer = getMessagesController().getInputPeer(r4.dialogId);
        return r0;
    }

    public void deletePollOption(MessageObject r5, byte[] r6) {
        if (r5 != null) goto L5;
        return;
    L5:
        if ((MessageObject.getMedia(r5) instanceof TLRPC.TL_messageMediaPoll) == true) goto L7;
        return;
    L7:
        long r0 = r5.getDialogId();
        int r52 = r5.getId();
        TLRPC.TL_messages_deletePollAnswer r2 = new TLRPC.TL_messages_deletePollAnswer();
        r2.peer = getMessagesController().getInputPeer(r0);
        r2.msg_id = r52;
        r2.option = r6;
        ConnectionsManager r53 = getConnectionsManager();
        DispatchQueue r62 = Utilities.stageQueue;
        Objects.requireNonNull(r62);
        r53.sendRequestTyped(r2, new ChatThemeController$$ExternalSyntheticLambda6(r62), new SendMessagesHelper$$ExternalSyntheticLambda42(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r31, int r32, Object... r33) {
        SendMessagesHelper r0 = this;
        int r11 = 0;
        boolean r12 = 1;
        if (r31 != NotificationCenter.fileUploadProgressChanged) goto L11;
        String r1 = (String) r33[0];
        ImportingHistory r2 = r0.importingHistoryFiles.get(r1);
        if (r2 == null) goto L7;
        Long r3 = (Long) r33[1];
        Long r4 = (Long) r33[2];
        ImportingHistory.m4953$$Nest$maddUploadProgress(r2, r1, r3.longValue(), r3.longValue() / r4.longValue());
    L7:
        ImportingStickers r02 = r0.importingStickersFiles.get(r1);
        if (r02 == null) goto L442;
        Long r22 = (Long) r33[1];
        Long r34 = (Long) r33[2];
        ImportingStickers.m4959$$Nest$maddUploadProgress(r02, r1, r22.longValue(), r22.longValue() / r34.longValue());
        return;
    L442:
        return;
    L11:
        char r14 = 5;
        String r35 = "_t";
        int r5 = 4;
        if (r31 != NotificationCenter.fileUploaded) goto L228;
        String r13 = (String) r33[0];
        TLRPC.InputFile r23 = (TLRPC.InputFile) r33[1];
        TLRPC.InputEncryptedFile r20 = (TLRPC.InputEncryptedFile) r33[2];
        ImportingHistory r6 = r0.importingHistoryFiles.get(r13);
        if (r6 != null) goto L16;
    L19:
        ImportingStickers r62 = r0.importingStickersFiles.get(r13);
        if (r62 == null) goto L22;
        ImportingStickers.m4962$$Nest$monMediaImport(r62, r13, ((Long) r33[5]).longValue(), r23);
    L22:
        ArrayList<DelayedMessage> r63 = r0.delayedMessages.get(r13);
        if (r63 == null) goto L443;
        int r7 = 0;
    L26:
        if (r7 >= r63.size()) goto L224;
        DelayedMessage r8 = r63.get(r7);
        TLObject r9 = r8.sendRequest;
        if ((r9 instanceof TLRPC.TL_messages_sendMedia) == false) goto L42;
        TLRPC.InputMedia r42 = ((TLRPC.TL_messages_sendMedia) r9).media;
        if ((r42 instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L36;
        HashMap<Object, Object> r92 = r8.extraHashMap;
        if (r92 != null) goto L34;
        r42 = ((TLRPC.TL_inputMediaPaidMedia) r42).extended_media.get(r11);
    L65:
        if (r23 == null) goto L195;
        if (r42 == null) goto L195;
        int r93 = r8.type;
        if (r93 != 0) goto L71;
        r42.file = r23;
        int r232 = r11;
        char r322 = r14;
        String r27 = r35;
        int r24 = r7;
        TLRPC.InputFile r142 = r23;
        TLRPC.InputEncryptedFile r25 = r20;
        ArrayList<DelayedMessage> r26 = r63;
        String r112 = r13;
        r0.performSendMessageRequest(r8.sendRequest, r8.obj, r8.originalPath, r8, true, null, r8.parentObject, null, r8.scheduled);
    L70:
        int r28 = r24;
        ArrayList<DelayedMessage> r15 = r26;
        String r64 = r27;
    L194:
        r15.remove(r28);
        int r72 = r28 - 1;
        int r94 = r12;
        TLRPC.InputEncryptedFile r36 = r25;
    L223:
        r7 = r72 + r94;
        r20 = r36;
        r35 = r64;
        r12 = r94;
        r23 = r142;
        r5 = 4;
        r14 = r322;
        r63 = r15;
        r13 = r112;
        r11 = r232;
        goto L26
    L71:
        r27 = r35;
        int r132 = r5;
        r26 = r63;
        r24 = r7;
        r232 = r11;
        r322 = r14;
        r25 = r20;
        r112 = r13;
        r142 = r23;
        if (r93 == r12) goto L74;
        if (r93 != 2) goto L116;
        if (r42.file != null) goto L114;
        r42.file = r142;
        if (r42.thumb != null) goto L113;
        TLRPC.PhotoSize r16 = r8.photoSize;
        if (r16 == null) goto L113;
        if (r16.location == null) goto L113;
        r0.performSendDelayedMessage(r8);
    L113:
        r0.performSendMessageRequest(r8.sendRequest, r8.obj, r8.originalPath, null, r8.parentObject, null, r8.scheduled);
        r0 = this;
        goto L70
    L114:
        r42.thumb = r142;
        r42.flags |= r132;
        r0 = this;
        r0.performSendMessageRequest(r8.sendRequest, r8.obj, r8.originalPath, null, r8.parentObject, null, r8.scheduled);
        goto L70
    L116:
        if (r93 != 3) goto L118;
        r42.file = r142;
        r0 = this;
        r0.performSendMessageRequest(r8.sendRequest, r8.obj, r8.originalPath, null, r8.parentObject, null, r8.scheduled);
        goto L70
    L118:
        r0 = this;
        if (r93 == r132) goto L121;
        r64 = r27;
    L145:
        r28 = r24;
        r15 = r26;
        goto L194
    L121:
        if ((r42 instanceof TLRPC.TL_inputMediaUploadedDocument) == true) goto L123;
        r64 = r27;
        if ((r42 instanceof TLRPC.TL_inputMediaDocument) == false) goto L192;
        HashMap<Object, Object> r17 = r8.extraHashMap;
        if (r17 != null) goto L163;
    L165:
        int r18 = r232;
    L166:
        if (r18 == 0) goto L168;
        String r37 = (String) r8.extraHashMap.get(r112 + "_doc");
    L169:
        MessageObject r52 = (MessageObject) r8.extraHashMap.get(r37 + "_i");
        HashMap<Object, Object> r73 = r8.extraHashMap;
        if (r73 != null) goto L172;
    L174:
        HashMap<Object, Object> r74 = r8.extraHashMap;
        if (r74 != null) goto L177;
    L179:
        int r38 = r8.messageObjects.indexOf(r52);
        if (r18 == 0) goto L187;
        if (r8.coverFile != null) goto L187;
        if (r8.coverPhotoSize == null) goto L187;
        r8.coverFile = r142;
        r8.performCoverUpload = r12;
        r0.performSendDelayedMessage(r8, r38);
    L187:
        if (r8.photoSize != null) goto L189;
    L191:
        r42.file = r142;
        r0.uploadMultiMedia(r8, r42, null, r112);
        goto L145
    L189:
        if (r42.thumb != null) goto L191;
        r42.thumb = r142;
        r42.flags |= r132;
        r0.uploadMultiMedia(r8, r42, null, (String) r8.extraHashMap.get(r112 + "_o"));
        goto L145
    L177:
        if (r74.containsKey(r37 + "_ct") == false) goto L179;
        r8.coverPhotoSize = (TLRPC.PhotoSize) r8.extraHashMap.get(r37 + "_ct");
        goto L179
    L172:
        if (r73.containsKey(r37 + r64) == false) goto L174;
        r8.photoSize = (TLRPC.PhotoSize) r8.extraHashMap.get(r37 + r64);
        goto L174
    L168:
        r37 = r112;
        goto L169
    L163:
        if (r17.containsKey(r112 + "_doc") == false) goto L165;
        r18 = r12;
        goto L166
    L192:
        r42.file = r142;
        r0.uploadMultiMedia(r8, r42, null, r112);
        goto L145
    L123:
        if (r42.file != null) goto L146;
        r42.file = r142;
        int r19 = r8.messageObjects.indexOf((MessageObject) r8.extraHashMap.get(r112 + "_i"));
        HashMap<Object, Object> r29 = r8.extraHashMap;
        StringBuilder r39 = new StringBuilder();
        r39.append(r112);
        r64 = r27;
        r39.append(r64);
        if (r29.containsKey(r39.toString()) == false) goto L128;
        r8.photoSize = (TLRPC.PhotoSize) r8.extraHashMap.get(r112 + r64);
    L128:
        if (r8.extraHashMap.containsKey(r112 + "_ct") == false) goto L131;
        r8.coverPhotoSize = (TLRPC.PhotoSize) r8.extraHashMap.get(r112 + "_ct");
    L131:
        if (r42.video_cover != null) goto L138;
        TLRPC.PhotoSize r210 = r8.coverPhotoSize;
        if (r210 == null) goto L138;
        if (r210.location == null) goto L138;
        r8.performCoverUpload = r12;
        r0.performSendDelayedMessage(r8, r19);
    L138:
        if (r42.thumb != null) goto L144;
        TLRPC.PhotoSize r211 = r8.photoSize;
        if (r211 == null) goto L144;
        if (r211.location == null) goto L144;
        r8.performMediaUpload = r12;
        r0.performSendDelayedMessage(r8, r19);
    L144:
        r0.uploadMultiMedia(r8, r42, null, r112);
        goto L145
    L146:
        r64 = r27;
        String r110 = (String) r8.extraHashMap.get(r112 + "_doc");
        MessageObject r310 = (MessageObject) r8.extraHashMap.get(r110 + "_i");
        if (r8.extraHashMap.containsKey(r110 + r64) == false) goto L150;
        r8.photoSize = (TLRPC.PhotoSize) r8.extraHashMap.get(r110 + r64);
    L150:
        if (r8.extraHashMap.containsKey(r110 + "_ct") == false) goto L152;
        r8.coverPhotoSize = (TLRPC.PhotoSize) r8.extraHashMap.get(r110 + "_ct");
    L152:
        int r111 = r8.messageObjects.indexOf(r310);
        if (r8.coverFile == null) goto L155;
    L157:
        r42.thumb = r142;
        r42.flags |= r132;
        r0.uploadMultiMedia(r8, r42, null, (String) r8.extraHashMap.get(r112 + "_o"));
        goto L145
    L155:
        if (r8.coverPhotoSize == null) goto L157;
        r8.coverFile = r142;
        r8.performCoverUpload = r12;
        r0.performSendDelayedMessage(r8, r111);
        goto L145
    L74:
        if (r42.file != null) goto L98;
        TLRPC.PhotoSize r113 = r8.coverPhotoSize;
        if (r113 != null) goto L78;
    L79:
        r42.file = r142;
        if (r8.coverFile != null) goto L84;
        if (r113 == null) goto L84;
        r0.performSendDelayedMessage(r8);
    L84:
        if (r42.thumb != null) goto L96;
        TLRPC.PhotoSize r114 = r8.photoSize;
        if (r114 == null) goto L96;
        if (r114.location == null) goto L96;
        MessageObject r115 = r8.obj;
        if (r115 == null) goto L95;
        VideoEditedInfo r116 = r115.videoEditedInfo;
        if (r116 == null) goto L95;
        if (r116.isSticker == true) goto L96;
    L95:
        r0.performSendDelayedMessage(r8);
    L96:
        r0.performSendMessageRequest(r8.sendRequest, r8.obj, r8.originalPath, null, r8.parentObject, null, r8.scheduled);
        goto L70
    L78:
        if (r8.performMediaUpload == true) goto L79;
    L98:
        if (r8.coverFile == null) goto L100;
    L102:
        r42.thumb = r142;
        r42.flags |= r132;
        r0.performSendMessageRequest(r8.sendRequest, r8.obj, r8.originalPath, null, r8.parentObject, null, r8.scheduled);
        goto L70
    L100:
        if (r8.coverPhotoSize == null) goto L102;
        r8.coverFile = r142;
        r8.performCoverUpload = r12;
        r0.performSendDelayedMessage(r8);
    L195:
        int r133 = r5;
        r232 = r11;
        r322 = r14;
        r112 = r13;
        r142 = r23;
        r15 = r63;
        int r212 = r7;
        r64 = r35;
        r36 = r20;
        if (r36 == null) goto L222;
        TLObject r43 = r8.sendEncryptedRequest;
        if (r43 == null) goto L222;
        if (r8.type != r133) goto L208;
        TLRPC.TL_messages_sendEncryptedMultiMedia r44 = (TLRPC.TL_messages_sendEncryptedMultiMedia) r43;
        TLRPC.InputEncryptedFile r53 = (TLRPC.InputEncryptedFile) r8.extraHashMap.get(r112);
        int r75 = r44.files.indexOf(r53);
        if (r75 < 0) goto L207;
        r44.files.set(r75, r36);
        r94 = r12;
        int r242 = r133;
        if (r53.id != 1) goto L206;
        r8.photoSize = (TLRPC.PhotoSize) r8.extraHashMap.get(r112 + r64);
    L206:
        TLRPC.TL_decryptedMessage r45 = r44.messages.get(r75);
    L209:
        if (r45 == null) goto L221;
        TLRPC.DecryptedMessageMedia r54 = r45.media;
        if ((r54 instanceof TLRPC.TL_decryptedMessageMediaVideo) == false) goto L213;
    L216:
        r45.media.size = ((Long) r33[r322]).longValue();
    L217:
        TLRPC.DecryptedMessageMedia r55 = r45.media;
        r55.key = (byte[]) r33[3];
        r55.iv = (byte[]) r33[r242];
        if (r8.type != r242) goto L220;
        r0.uploadMultiMedia(r8, null, r36, r112);
        goto L221
    L220:
        SecretChatHelper r162 = r0.getSecretChatHelper();
        MessageObject r56 = r8.obj;
        r162.performSendEncryptedRequest(r45, r56.messageOwner, r8.encryptedChat, r36, r8.originalPath, r56);
        goto L221
    L213:
        if ((r54 instanceof TLRPC.TL_decryptedMessageMediaPhoto) == true) goto L216;
        if ((r54 instanceof TLRPC.TL_decryptedMessageMediaDocument) == false) goto L217;
    L221:
        r15.remove(r212);
        r72 = r212 - 1;
        goto L223
    L207:
        r94 = r12;
        r242 = r133;
        r45 = null;
        goto L209
    L208:
        r94 = r12;
        r242 = r133;
        r45 = (TLRPC.TL_decryptedMessage) r43;
    L222:
        r94 = r12;
        r72 = r212;
        goto L223
    L34:
        r42 = (TLRPC.InputMedia) r92.get(r13);
        goto L65
    L36:
        if ((r42 instanceof TLRPC.TL_inputMediaPoll) == false) goto L65;
        HashMap<Object, Object> r95 = r8.extraHashMap;
        if (r95 != null) goto L40;
        r42 = PollAttachedMediaPack.getFirstInputMedia((TLRPC.TL_inputMediaPoll) r42);
        goto L65
    L40:
        r42 = (TLRPC.InputMedia) r95.get(r13);
        goto L65
    L42:
        if ((r9 instanceof TL_ephemeral.TL_sendMessage) == false) goto L56;
        r42 = ((TL_ephemeral.TL_sendMessage) r9).media;
        if ((r42 instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L50;
        HashMap<Object, Object> r96 = r8.extraHashMap;
        if (r96 != null) goto L48;
        r42 = ((TLRPC.TL_inputMediaPaidMedia) r42).extended_media.get(r11);
        goto L65
    L48:
        r42 = (TLRPC.InputMedia) r96.get(r13);
        goto L65
    L50:
        if ((r42 instanceof TLRPC.TL_inputMediaPoll) == false) goto L65;
        HashMap<Object, Object> r97 = r8.extraHashMap;
        if (r97 != null) goto L54;
        r42 = PollAttachedMediaPack.getFirstInputMedia((TLRPC.TL_inputMediaPoll) r42);
        goto L65
    L54:
        r42 = (TLRPC.InputMedia) r97.get(r13);
        goto L65
    L56:
        if ((r9 instanceof TLRPC.TL_messages_editMessage) == false) goto L59;
        r42 = ((TLRPC.TL_messages_editMessage) r9).media;
        goto L65
    L59:
        if ((r9 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L62;
        r42 = (TLRPC.InputMedia) r8.extraHashMap.get(r13);
        goto L65
    L62:
        if ((r9 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L64;
        r42 = ((TLRPC.TL_messages_addPollAnswer) r9).answer.input_media;
        goto L65
    L64:
        r42 = null;
        goto L65
    L224:
        String r117 = r13;
        if (r63.isEmpty() == false) goto L444;
        r0.delayedMessages.remove(r117);
        return;
    L444:
        return;
    L443:
        return;
    L16:
        if (r13.equals(r6.historyPath) == false) goto L18;
        ImportingHistory.m4954$$Nest$minitImport(r6, r23);
        goto L19
    L18:
        ImportingHistory.m4956$$Nest$monMediaImport(r6, r13, ((Long) r33[5]).longValue(), r23);
        goto L19
    L228:
        boolean r98 = true;
        if (r31 != NotificationCenter.fileUploadFailed) goto L255;
        String r118 = (String) r33[0];
        boolean r213 = ((Boolean) r33[1]).booleanValue();
        ImportingHistory r311 = r0.importingHistoryFiles.get(r118);
        if (r311 == null) goto L233;
        ImportingHistory.m4955$$Nest$monFileFailedToUpload(r311, r118);
    L233:
        ImportingStickers r312 = r0.importingStickersFiles.get(r118);
        if (r312 == null) goto L236;
        ImportingStickers.m4961$$Nest$monFileFailedToUpload(r312, r118);
    L236:
        ArrayList<DelayedMessage> r313 = r0.delayedMessages.get(r118);
        if (r313 == null) goto L445;
        int r119 = 0;
    L240:
        if (r119 >= r313.size()) goto L251;
        DelayedMessage r46 = r313.get(r119);
        if (r213 == true) goto L244;
    L245:
        if (r213 == true) goto L249;
        if (r46.sendRequest == null) goto L249;
    L248:
        r46.markAsError();
        r313.remove(r119);
        r119 = r119 - 1;
    L249:
        r119 = r119 + 1;
        goto L240
    L244:
        if (r46.sendEncryptedRequest != null) goto L248;
    L251:
        if (r313.isEmpty() == false) goto L446;
        r0.delayedMessages.remove(r118);
        return;
    L446:
        return;
    L445:
        return;
    L255:
        if (r31 != NotificationCenter.filePreparingStarted) goto L279;
        MessageObject r120 = (MessageObject) r33[0];
        if (r120.getId() == 0) goto L457;
        String r214 = (String) r33[1];
        ArrayList<DelayedMessage> r215 = r0.delayedMessages.get(r120.messageOwner.attachPath);
        if (r215 == null) goto L447;
        int r1110 = 0;
    L263:
        if (r1110 >= r215.size()) goto L275;
        DelayedMessage r314 = r215.get(r1110);
        if (r314.type == 4) goto L266;
        if (r314.obj == r120) goto L272;
        r1110 = r1110 + 1;
        r98 = true;
        goto L263
    L272:
        r314.videoEditedInfo = null;
        r0.performSendDelayedMessage(r314);
        r215.remove(r1110);
        goto L275
    L266:
        int r47 = r314.messageObjects.indexOf(r120);
        r314.photoSize = (TLRPC.PhotoSize) r314.extraHashMap.get(r120.messageOwner.attachPath + "_t");
        if (r314.extraHashMap.containsKey(r120.messageOwner.attachPath + "_ct") == false) goto L269;
        r314.coverPhotoSize = (TLRPC.PhotoSize) r314.extraHashMap.get(r120.messageOwner.attachPath + "_ct");
    L269:
        r314.performMediaUpload = r98;
        r0.performSendDelayedMessage(r314, r47);
        r215.remove(r1110);
    L275:
        if (r215.isEmpty() == false) goto L448;
        r0.delayedMessages.remove(r120.messageOwner.attachPath);
        return;
    L448:
        return;
    L447:
        return;
    L457:
        return;
    L279:
        if (r31 != NotificationCenter.fileNewChunkAvailable) goto L337;
        MessageObject r121 = (MessageObject) r33[0];
        if (r121.getId() == 0) goto L458;
        String r122 = (String) r33[1];
        long r143 = ((Long) r33[2]).longValue();
        long r163 = ((Long) r33[3]).longValue();
        Float r182 = (Float) r33[4];
        r0.getFileLoader().checkUploadNewDataAvailable(r122, DialogObject.isEncryptedDialog(r121.getDialogId()), r143, r163, r182);
        if (r163 == 0) goto L449;
        ArrayList<DelayedMessage> r48 = r0.delayedMessages.get(r121.messageOwner.attachPath);
        if (r48 == null) goto L450;
        int r57 = 0;
    L289:
        if (r57 >= r48.size()) goto L460;
        DelayedMessage r65 = r48.get(r57);
        if (r65.type != 4) goto L318;
        int r76 = 0;
    L294:
        if (r76 >= r65.messageObjects.size()) goto L335;
        MessageObject r1111 = r65.messageObjects.get(r76);
        if (r1111 == r121) goto L297;
        r76 = r76 + 1;
        goto L294
    L297:
        r65.obj.shouldRemoveVideoEditedInfo = true;
        r1111.messageOwner.params.remove("ve");
        TLRPC.Document r10 = r65.obj.getDocument();
        if (r10 == null) goto L300;
        r10.size = r163;
    L300:
        ArrayList<TLRPC.Message> r134 = new ArrayList();
        r134.add(r1111.messageOwner);
        if (r1111.isWelcomeMessage() == false) goto L304;
        int r183 = 9;
        int r82 = 0;
    L311:
        if (r65.paidMedia == false) goto L313;
    L314:
        if (r76 != 0) goto L335;
    L316:
        r0.getMessagesStorage().putMessages(r134, false, true, false, 0, r183, r82);
        goto L335
    L313:
        if (r65.pollMedia == false) goto L316;
    L304:
        if (r1111.isQuickReply() == false) goto L307;
        r82 = r1111.getQuickReplyId();
        r183 = 5;
        goto L311
    L307:
        if (r1111.scheduled == false) goto L309;
        r82 = 0;
        r183 = 1;
        goto L311
    L309:
        r82 = 0;
        r183 = 0;
    L335:
        r57 = r57 + 1;
        goto L289
    L318:
        MessageObject r77 = r65.obj;
        if (r77 != r121) goto L335;
        r77.shouldRemoveVideoEditedInfo = true;
        r77.messageOwner.params.remove("ve");
        TLRPC.Document r123 = r65.obj.getDocument();
        if (r123 == null) goto L323;
        r123.size = r163;
    L323:
        ArrayList<TLRPC.Message> r1112 = new ArrayList();
        r1112.add(r65.obj.messageOwner);
        if (r65.obj.isWelcomeMessage() == false) goto L326;
        int r164 = 9;
        int r124 = 0;
    L333:
        r0.getMessagesStorage().putMessages(r1112, false, true, false, 0, r164, r124);
        return;
    L326:
        boolean r125 = r65.obj.isQuickReply();
        MessageObject r216 = r65.obj;
        if (r125 == false) goto L330;
        r124 = r216.getQuickReplyId();
        r164 = 5;
        goto L333
    L330:
        if (r216.scheduled == false) goto L332;
        r124 = 0;
        r164 = 1;
        goto L333
    L332:
        r124 = 0;
        r164 = 0;
        goto L333
    L460:
        return;
    L450:
        return;
    L449:
        return;
    L458:
        return;
    L337:
        if (r31 != NotificationCenter.filePreparingFailed) goto L366;
        MessageObject r126 = (MessageObject) r33[0];
        if (r126.getId() == 0) goto L459;
        String r217 = (String) r33[1];
        ArrayList<DelayedMessage> r315 = r0.delayedMessages.get(r217);
        if (r315 == null) goto L451;
        int r49 = 0;
    L345:
        if (r49 >= r315.size()) goto L362;
        DelayedMessage r58 = r315.get(r49);
        if (r58.type != 4) goto L357;
        int r66 = 0;
    L350:
        if (r66 >= r58.messages.size()) goto L360;
        if (r58.messageObjects.get(r66) == r126) goto L353;
        r66 = r66 + 1;
        goto L350
    L353:
        r58.markAsError();
        r315.remove(r49);
    L359:
        r49 = r49 - 1;
    L360:
        r49 = r49 + 1;
        goto L345
    L357:
        if (r58.obj != r126) goto L360;
        r58.markAsError();
        r315.remove(r49);
        goto L359
    L362:
        if (r315.isEmpty() == false) goto L452;
        r0.delayedMessages.remove(r217);
        return;
    L452:
        return;
    L451:
        return;
    L459:
        return;
    L366:
        if (r31 != NotificationCenter.httpFileDidLoad) goto L395;
        String r59 = (String) r33[0];
        ArrayList<DelayedMessage> r67 = r0.delayedMessages.get(r59);
        if (r67 == null) goto L453;
        int r83 = 0;
    L371:
        if (r83 >= r67.size()) goto L392;
        DelayedMessage r410 = r67.get(r83);
        int r127 = r410.type;
        if (r127 != 0) goto L376;
        MessageObject r128 = r410.obj;
    L375:
        MessageObject r316 = r128;
        char r129 = 0;
    L385:
        if (r129 != 0) goto L389;
        r0 = this;
        Utilities.globalQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda84(this, new File(FileLoader.getDirectory(4), Utilities.MD5(r59) + "." + ImageLoader.getHttpUrlExtension(r59, "file")), r316, r410, r59));
    L391:
        r83 = r83 + 1;
        goto L371
    L389:
        if (r129 != 1) goto L391;
        Utilities.globalQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda85(r0, r410, new File(FileLoader.getDirectory(4), Utilities.MD5(r59) + ".gif"), r316));
        goto L391
    L376:
        if (r127 != 2) goto L380;
        r128 = r410.obj;
    L378:
        r316 = r128;
        r129 = 1;
        goto L385
    L380:
        if (r127 != 4) goto L384;
        r128 = (MessageObject) r410.extraHashMap.get(r59);
        if (r128.getDocument() == null) goto L375;
    L384:
        r129 = 65535;
        r316 = null;
        goto L385
    L392:
        r0.delayedMessages.remove(r59);
        return;
    L453:
        return;
    L395:
        if (r31 != NotificationCenter.fileLoaded) goto L405;
        String r130 = (String) r33[0];
        ArrayList<DelayedMessage> r218 = r0.delayedMessages.get(r130);
        if (r218 == null) goto L454;
        int r1113 = 0;
    L400:
        if (r1113 >= r218.size()) goto L402;
        r0.performSendDelayedMessage(r218.get(r1113));
        r1113 = r1113 + 1;
        goto L400
    L402:
        r0.delayedMessages.remove(r130);
        return;
    L454:
        return;
    L405:
        if (r31 != NotificationCenter.httpFileDidFailedLoad) goto L407;
    L408:
        String r131 = (String) r33[0];
        ArrayList<DelayedMessage> r219 = r0.delayedMessages.get(r131);
        if (r219 == null) goto L456;
        int r1114 = 0;
    L412:
        if (r1114 >= r219.size()) goto L414;
        r219.get(r1114).markAsError();
        r1114 = r1114 + 1;
        goto L412
    L414:
        r0.delayedMessages.remove(r131);
        return;
    L456:
        return;
    L407:
        if (r31 == NotificationCenter.fileLoadFailed) goto L408;
    }

    public int editMessage(MessageObject r6, String r7, boolean r8, BaseFragment r9, ArrayList<TLRPC.MessageEntity> r10, int r11, int r12) {
        if (r9 != null) goto L4;
        return 0;
    L4:
        if (r9.getParentActivity() == null) goto L28;
        LocaleUtils.replaceCustomEmojis(this.currentAccount, r6.getDialogId(), r10);
        if (r6.isEphemeral() == false) goto L9;
        TL_ephemeral.TL_editMessage r0 = new TL_ephemeral.TL_editMessage();
        r0.id = MessageObject.ephemeralMessageIdUnpack(r6.getId());
        r0.receiver_id = new TLRPC.TL_inputUserEmpty();
        r0.welcome = true;
        TLRPC.TL_messages_editMessage r02 = r0;
    L10:
        r02.peer = getMessagesController().getInputPeer(r6.getDialogId());
        if (r7 == null) goto L13;
        r02.message = r7;
        r02.flags |= 2048;
        r02.no_webpage = !r8;
    L13:
        TLRPC.Message r62 = r6.messageOwner;
        if (r62 != null) goto L16;
    L18:
        if (r10 == null) goto L20;
        r02.entities = r10;
        r02.flags |= 8;
    L20:
        if (r11 == 0) goto L25;
        r02.schedule_date = r11;
        int r63 = r02.flags;
        r02.flags = 32768 | r63;
        if (r12 == 0) goto L25;
        r02.schedule_repeat_period = r12;
        r02.flags = r63 | 294912;
    L25:
        return getConnectionsManager().sendRequest(r02, new SendMessagesHelper$$ExternalSyntheticLambda74(this, r9, r02));
    L16:
        if ((r62.flags & TLObject.FLAG_30) == 0) goto L18;
        r02.quick_reply_shortcut_id = r62.quick_reply_shortcut_id;
        r02.flags |= 131072;
        goto L18
    L9:
        TLRPC.TL_messages_editMessage r03 = new TLRPC.TL_messages_editMessage();
        r03.id = r6.getId();
        r02 = r03;
        goto L10
    L28:
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v20, types: [org.telegram.tgnet.TLRPC$InputMedia, org.telegram.tgnet.TLRPC$TL_inputMediaPhoto] */
    /* JADX WARN: Type inference failed for: r3v10, types: [org.telegram.tgnet.TLRPC$InputMedia, org.telegram.tgnet.TLRPC$TL_inputMediaUploadedDocument] */
    /* JADX WARN: Type inference failed for: r3v12, types: [org.telegram.tgnet.TLRPC$InputMedia, org.telegram.tgnet.TLRPC$TL_inputMediaUploadedDocument] */
    /* JADX WARN: Type inference failed for: r3v26, types: [org.telegram.tgnet.TLRPC$InputMedia] */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r4v11, types: [org.telegram.tgnet.TLRPC$InputMedia, org.telegram.tgnet.TLRPC$TL_inputMediaUploadedPhoto] */
    /* JADX WARN: Type inference failed for: r4v3, types: [org.telegram.tgnet.TLRPC$TL_inputMediaTodo] */
    /* JADX WARN: Type inference failed for: r5v12, types: [org.telegram.tgnet.TLRPC$InputMedia, org.telegram.tgnet.TLRPC$TL_inputMediaDocument] */
    /* JADX WARN: Type inference failed for: r5v34, types: [org.telegram.tgnet.TLObject, org.telegram.tgnet.TLRPC$TL_messages_editMessage] */
    /* JADX WARN: Type inference failed for: r5v38 */
    /* JADX WARN: Type inference failed for: r5v39, types: [org.telegram.tgnet.TLObject, org.telegram.tgnet.TLRPC$TL_messages_addPollAnswer] */
    /* JADX WARN: Type inference failed for: r5v5, types: [org.telegram.tgnet.TLRPC$InputMedia, org.telegram.tgnet.TLRPC$TL_inputMediaDocument] */
    public void editMessage(MessageObject r34, TLRPC.TL_inputPollAnswer r35, TLRPC.TL_photo r36, VideoEditedInfo r37, TLRPC.TL_document r38, String r39, TLRPC.PhotoSize r40, HashMap<String, String> r41, boolean r42, boolean r43, Object r44) {
        TLRPC.TL_photo r3 = r36;
        TLRPC.TL_document r4 = r38;
        String r5 = r39;
        if (r34 == null) goto L438;
        if (r41 != null) goto L7;
        HashMap<String, String> r10 = new HashMap();
    L8:
        LocaleUtils.replaceCustomEmojis(this.currentAccount, r34.getDialogId(), r34.editingMessageEntities);
        TLRPC.Message r11 = r34.messageOwner;
        r34.cancelEditing = false;
        long r13 = r34.getDialogId();     // Catch: Exception -> L17
        if (DialogObject.isEncryptedDialog(r13) == false) goto L20;
        TLRPC.EncryptedChat r12 = getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r13)));     // Catch: Exception -> L17
        if (r12 != null) goto L15;
    L19:
        boolean r122 = false;
    L22:
        if (r42 == false) goto L55;
        TLRPC.MessageMedia r52 = r34.messageOwner.media;     // Catch: Exception -> L17
        if ((r52 instanceof TLRPC.TL_messageMediaWebPage) == true) goto L45;
        if (r52 == null) goto L45;
        if ((r52 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L45;
        if ((r52 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L33;
        r3 = (TLRPC.TL_photo) r52.photo;     // Catch: Exception -> L17
        VideoEditedInfo r102 = r37;
        char r53 = 2;
    L46:
        HashMap<String, String> r14 = r11.params;     // Catch: Exception -> L17
        if (r44 != null) goto L53;
        if (r14 == null) goto L53;
        if (r14.containsKey("parentObject") == false) goto L53;
        Object r9 = r14.get("parentObject");     // Catch: Exception -> L17
    L54:
        r34.editingMessage = r11.message;     // Catch: Exception -> L17
        r34.editingMessageEntities = r11.entities;     // Catch: Exception -> L17
        Object r23 = "originalPath";
        boolean r24 = r122;
        char r8 = r53;
        r5 = r11.attachPath;     // Catch: Exception -> L17
    L122:
        if (r11.attachPath != null) goto L124;
        r11.attachPath = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L17
    L124:
        r11.local_id = 0;     // Catch: Exception -> L17
        int r123 = r34.type;     // Catch: Exception -> L17
        if (r123 == 3) goto L130;
        if (r102 != null) goto L130;
        if (r123 == 2) goto L130;
    L132:
        VideoEditedInfo r124 = r34.videoEditedInfo;     // Catch: Exception -> L17
        if (r124 == null) goto L136;
        if (r102 != null) goto L136;
        r102 = r124;
    L136:
        if (r42 == true) goto L177;
        CharSequence r132 = r34.editingMessage;     // Catch: Exception -> L17
        if (r132 == null) goto L152;
        String r125 = r11.message;     // Catch: Exception -> L17
        String r133 = r132.toString();     // Catch: Exception -> L17
        r11.message = r133;     // Catch: Exception -> L17
        r34.caption = null;     // Catch: Exception -> L17
        ArrayList<TLRPC.MessageEntity> r6 = r34.editingMessageEntities;
        if (r8 != 1) goto L153;
        if (r6 == null) goto L145;
        r11.entities = new ArrayList(r34.editingMessageEntities);     // Catch: Exception -> L17
        r11.flags |= 128;
    L147:
        TLRPC.Message r62 = r34.messageOwner;     // Catch: Exception -> L17
        if (r62 == null) goto L152;
        if ((r62.media instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L152;
        r34.generateCaption();     // Catch: Exception -> L17
        goto L152
    L145:
        if (TextUtils.equals(r125, r133) == true) goto L147;
        r11.flags &= -129;
        goto L147
    L153:
        if (r6 == null) goto L155;
        r11.entities = new ArrayList(r34.editingMessageEntities);     // Catch: Exception -> L17
        r11.flags |= 128;
        VideoEditedInfo r372 = r102;
        boolean r103 = r24;
    L163:
        r34.generateCaption();     // Catch: Exception -> L17
    L164:
        ArrayList<TLRPC.Message> r63 = new ArrayList();     // Catch: Exception -> L17
        r63.add(r11);     // Catch: Exception -> L17
        getMessagesStorage().putMessages(r63, false, true, false, 0, r34.scheduled ? 1 : 0, 0);     // Catch: Exception -> L17
        getMessagesController().getTopicsController().processEditedMessage(r11);     // Catch: Exception -> L17
        r34.type = -1;     // Catch: Exception -> L17
        r34.setType();     // Catch: Exception -> L17
        if (r8 != 1) goto L173;
        TLRPC.MessageMedia r64 = r34.messageOwner.media;     // Catch: Exception -> L17
        if ((r64 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L169;
    L172:
        r34.generateCaption();     // Catch: Exception -> L17
        goto L173
    L169:
        if ((r64 instanceof TLRPC.TL_messageMediaDocument) == true) goto L172;
        r34.resetLayout();     // Catch: Exception -> L17
        r34.checkLayout();     // Catch: Exception -> L17
    L173:
        if (r35 == null) goto L175;
        boolean r65 = true;
    L176:
        r34.createMessageSendInfo(r65);     // Catch: Exception -> L17
        ArrayList r66 = new ArrayList();     // Catch: Exception -> L17
        r66.add(r34);     // Catch: Exception -> L17
        r24 = r103;
        getNotificationCenter().postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(r13), r66});     // Catch: Exception -> L17
    L178:
        if (r14 == null) goto L182;
        Object r67 = r23;
        if (r14.containsKey(r67) == false) goto L182;
        String r68 = r14.get(r67);     // Catch: Exception -> L17
    L184:
        if (r8 < 1) goto L190;
        if (r8 > 3) goto L190;
    L188:
        char r126 = 11;
    L196:
        if (r8 != r126) goto L201;
        Object r32 = r35.input_media;     // Catch: Exception -> L17
    L198:
        char r382 = '\b';
        HashMap<String, String> r442 = r14;
        long r127 = r13;
        Object r33 = r32;
    L199:
        boolean r45 = false;
        boolean r7 = false;
        DelayedMessage r112 = null;
        Object r310 = r33;
    L313:
        boolean r54 = r310 instanceof TLRPC.TL_inputMediaEmpty;     // Catch: Exception -> L17
        ?? r311 = r310;
        if (r54 == false) goto L320;
        int r55 = r34.type;     // Catch: Exception -> L17
        if (r55 == 0) goto L319;
        r311 = r310;
        if (r55 != 19) goto L320;
    L319:
        r311 = 0;
    L320:
        if (r35 == null) goto L326;
        TLObject r56 = new TLRPC.TL_messages_addPollAnswer();     // Catch: Exception -> L17
        r35.input_media = r311;     // Catch: Exception -> L17
        r56.answer = r35;     // Catch: Exception -> L17
        r56.msg_id = r34.getId();     // Catch: Exception -> L17
        r56.peer = getMessagesController().getInputPeer(r127);     // Catch: Exception -> L17
        if (r112 == null) goto L358;
        r112.sendRequest = r56;     // Catch: Exception -> L17
    L358:
        if (r8 == 1) goto L425;
        if (r8 == 11) goto L425;
        if (r8 != 2) goto L376;
        if (r7 == true) goto L374;
        if (r45 == true) goto L374;
        performSendMessageRequest(r56, r34, r68, null, true, r112, r9, r442, r34.scheduled);     // Catch: Exception -> L371
        return;
    L374:
        performSendDelayedMessage(r112);     // Catch: Exception -> L17
        return;
    L376:
        HashMap<String, String> r104 = r442;
        if (r8 != 3) goto L433;
        if (r7 == true) goto L385;
        if (r45 == true) goto L385;
        performSendMessageRequest(r56, r34, r68, r112, r9, r104, r34.scheduled);     // Catch: Exception -> L371
        return;
    L385:
        performSendDelayedMessage(r112);     // Catch: Exception -> L17
        return;
    L433:
        if (r8 != 6) goto L396;
        performSendMessageRequest(r56, r34, r68, r112, r9, r104, r34.scheduled);     // Catch: Exception -> L393
        return;
    L396:
        if (r8 != 7) goto L409;
        if (r7 == true) goto L406;
        if (r45 == true) goto L406;
        performSendMessageRequest(r56, r34, r68, r112, r9, r104, r34.scheduled);     // Catch: Exception -> L371
        return;
    L404:
        e = e;
    L427:
        FileLog.e(e);
        revertEditingMessageObject(r34);
        return;
    L406:
        performSendDelayedMessage(r112);     // Catch: Exception -> L17
        return;
    L409:
        if (r8 != r382) goto L420;
        if (r7 == true) goto L417;
        if (r45 == true) goto L417;
        performSendMessageRequest(r56, r34, r68, r112, r9, r104, r34.scheduled);     // Catch: Exception -> L371
        return;
    L417:
        performSendDelayedMessage(r112);     // Catch: Exception -> L17
        return;
    L420:
        if (r8 == '\n') goto L423;
        return;
    L423:
        performSendMessageRequest(r56, r34, r68, r112, r9, r104, r34.scheduled);     // Catch: Exception -> L393
        return;
    L393:
        e = e;
    L371:
        e = e;
    L425:
        performSendMessageRequest(r56, r34, null, r112, r9, r442, r34.scheduled);     // Catch: Exception -> L17
        return;
    L326:
        if (r34.isEphemeral() == false) goto L329;
        TL_ephemeral.TL_editMessage r0 = new TL_ephemeral.TL_editMessage();     // Catch: Exception -> L17
        r0.id = MessageObject.ephemeralMessageIdUnpack(r34.getId());     // Catch: Exception -> L17
        r0.receiver_id = new TLRPC.TL_inputUserEmpty();     // Catch: Exception -> L17
        r0.welcome = true;     // Catch: Exception -> L17
        TLRPC.TL_messages_editMessage r02 = r0;
    L328:
        r56 = r02;
        r56.peer = getMessagesController().getInputPeer(r127);     // Catch: Exception -> L17
        TLRPC.Message r03 = r34.messageOwner;     // Catch: Exception -> L17
        r56.invert_media = r03.invert_media;     // Catch: Exception -> L17
        if (r311 == 0) goto L334;
        r56.flags |= 16384;
        r56.media = r311;     // Catch: Exception -> L17
    L337:
        if (r34.scheduled == false) goto L342;
        r56.schedule_date = r03.date;     // Catch: Exception -> L17
        int r312 = r56.flags;     // Catch: Exception -> L17
        r56.flags = 32768 | r312;     // Catch: Exception -> L17
        int r105 = r03.schedule_repeat_period;     // Catch: Exception -> L17
        if (r105 == 0) goto L342;
        r56.schedule_repeat_period = r105;     // Catch: Exception -> L17
        r56.flags = r312 | 294912;     // Catch: Exception -> L17
    L342:
        if ((r03.flags & TLObject.FLAG_30) == 0) goto L344;
        r56.quick_reply_shortcut_id = r03.quick_reply_shortcut_id;     // Catch: Exception -> L17
        r56.flags |= 131072;
    L344:
        CharSequence r04 = r34.editingMessage;     // Catch: Exception -> L17
        if (r04 == null) goto L356;
        r56.message = r04.toString();     // Catch: Exception -> L17
        int r05 = r56.flags;     // Catch: Exception -> L17
        r56.flags = r05 | 2048;     // Catch: Exception -> L17
        r56.no_webpage = !r34.editingMessageSearchWebPage;     // Catch: Exception -> L17
        ArrayList<TLRPC.MessageEntity> r313 = r34.editingMessageEntities;     // Catch: Exception -> L17
        if (r313 == null) goto L350;
        r56.entities = r313;     // Catch: Exception -> L17
        r56.flags = r05 | 2056;     // Catch: Exception -> L17
    L355:
        r34.editingMessage = null;     // Catch: Exception -> L17
        r34.editingMessageEntities = null;     // Catch: Exception -> L17
        goto L356
    L350:
        ArrayList<TLRPC.MessageEntity> r06 = getMediaDataController().getEntities(new CharSequence[]{r34.editingMessage}, r24);     // Catch: Exception -> L17
        if (r06 == null) goto L355;
        if (r06.isEmpty() == true) goto L355;
        r56.entities = r06;     // Catch: Exception -> L17
        r56.flags |= 8;
    L356:
        if (r112 == null) goto L358;
        r112.sendRequest = r56;     // Catch: Exception -> L17
        goto L358
    L334:
        if (r34.editingMessageSearchWebPage == true) goto L337;
        r56.no_webpage = true;     // Catch: Exception -> L17
        goto L337
    L329:
        TLRPC.TL_messages_editMessage r07 = new TLRPC.TL_messages_editMessage();     // Catch: Exception -> L17
        r07.id = r34.getId();     // Catch: Exception -> L17
        r02 = r07;
        goto L328
    L201:
        if (r8 != 1) goto L219;
        TLRPC.MessageMedia r314 = r11.media;     // Catch: Exception -> L17
        if (r314 == null) goto L210;
        if ((r314 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L210;
        if (r314 != null) goto L208;
    L212:
        if (r314 != null) goto L214;
    L217:
        r382 = '\b';
        r442 = r14;
        r127 = r13;
    L312:
        r33 = null;
        goto L199
    L214:
        if (r314.webpage == null) goto L217;
        TLRPC.TL_inputMediaWebPage r315 = new TLRPC.TL_inputMediaWebPage();     // Catch: Exception -> L17
        TLRPC.MessageMedia r46 = r11.media;     // Catch: Exception -> L17
        r315.url = r46.webpage.url;     // Catch: Exception -> L17
        r315.force_small_media = r46.force_small_media;     // Catch: Exception -> L17
        r315.force_large_media = r46.force_large_media;     // Catch: Exception -> L17
        r32 = r315;
        goto L198
    L208:
        if ((r314.webpage instanceof TLRPC.TL_webPageEmpty) == false) goto L212;
    L210:
        if (r34.editingMessageSearchWebPage == true) goto L212;
        r32 = new TLRPC.TL_inputMediaEmpty();     // Catch: Exception -> L17
        goto L198
    L219:
        if (r8 != 2) goto L246;
        ?? r47 = new TLRPC.TL_inputMediaUploadedPhoto();     // Catch: Exception -> L17
        r47.spoiler = r43;     // Catch: Exception -> L17
        if (r14 == null) goto L228;
        String r113 = r14.get("masks");     // Catch: Exception -> L17
        if (r113 == null) goto L228;
        SerializedData r128 = new SerializedData(Utilities.hexToBytes(r113));     // Catch: Exception -> L17
        boolean r134 = false;
        int r114 = r128.readInt32(false);     // Catch: Exception -> L17
        r382 = '\b';
        int r106 = 0;
    L225:
        if (r106 >= r114) goto L227;
        r47.stickers.add(TLRPC.InputDocument.TLdeserialize(r128, r128.readInt32(r134), r134));     // Catch: Exception -> L17
        r106 = r106 + 1;     // Catch: Exception -> L17
        r114 = r114;
        r134 = false;
        goto L225
    L227:
        r47.flags |= 1;
        r128.cleanup();     // Catch: Exception -> L17
    L230:
        if (r3.access_hash != 0) goto L232;
        VideoEditedInfo r107 = r47;
        r7 = true;
    L236:
        r127 = r13;
        r112 = new DelayedMessage(this, r127);     // Catch: Exception -> L17
        r372 = r107;
        r112.type = 0;     // Catch: Exception -> L17
        r112.obj = r34;     // Catch: Exception -> L17
        r112.originalPath = r68;     // Catch: Exception -> L17
        r112.parentObject = r9;     // Catch: Exception -> L17
        r112.inputUploadMedia = r47;     // Catch: Exception -> L17
        r112.performMediaUpload = r7;     // Catch: Exception -> L17
        if (r5 != null) goto L239;
    L243:
        r112.photoSize = r3.sizes.get(r4.size() - 1);     // Catch: Exception -> L17
        r112.locationParent = r3;     // Catch: Exception -> L17
    L244:
        Object r316 = r372;
        r442 = r14;
    L245:
        r45 = false;
        r310 = r316;
        goto L313
    L239:
        if (r5.length() <= 0) goto L243;
        if (r5.startsWith("http") == false) goto L243;
        r112.httpLocation = r5;     // Catch: Exception -> L17
        goto L244
    L232:
        ?? r108 = new TLRPC.TL_inputMediaPhoto();     // Catch: Exception -> L17
        TLRPC.TL_inputPhoto r115 = new TLRPC.TL_inputPhoto();     // Catch: Exception -> L17
        r108.id = r115;     // Catch: Exception -> L17
        r115.id = r3.id;     // Catch: Exception -> L17
        r115.access_hash = r3.access_hash;     // Catch: Exception -> L17
        byte[] r129 = r3.file_reference;     // Catch: Exception -> L17
        r115.file_reference = r129;     // Catch: Exception -> L17
        if (r129 != null) goto L235;
        r115.file_reference = new byte[0];     // Catch: Exception -> L17
    L235:
        r108.spoiler = r43;     // Catch: Exception -> L17
        r7 = false;
        r107 = r108;
    L228:
        r382 = '\b';
        goto L230
    L246:
        r382 = '\b';
        r127 = r13;
        if (r8 != 3) goto L289;
        ?? r317 = new TLRPC.TL_inputMediaUploadedDocument();     // Catch: Exception -> L17
        r317.spoiler = r43;     // Catch: Exception -> L17
        if (r14 == null) goto L256;
        String r57 = r14.get("masks");     // Catch: Exception -> L17
        if (r57 == null) goto L256;
        SerializedData r109 = new SerializedData(Utilities.hexToBytes(r57));     // Catch: Exception -> L17
        boolean r58 = false;
        int r116 = r109.readInt32(false);     // Catch: Exception -> L17
        int r15 = 0;
    L253:
        if (r15 >= r116) goto L255;
        r317.stickers.add(TLRPC.InputDocument.TLdeserialize(r109, r109.readInt32(r58), r58));     // Catch: Exception -> L17
        r15 = r15 + 1;     // Catch: Exception -> L17
        r116 = r116;
        r14 = r14;
        r58 = false;
        goto L253
    L255:
        r442 = r14;
        r317.flags |= 1;
        r109.cleanup();     // Catch: Exception -> L17
    L257:
        r317.mime_type = r4.mime_type;     // Catch: Exception -> L17
        r317.attributes = r4.attributes;     // Catch: Exception -> L17
        if (r34.isGif() == true) goto L267;
        if (r372 == null) goto L263;
        VideoEditedInfo r1010 = r372;
        if (r1010.muted == true) goto L269;
    L264:
        r317.nosound_video = true;     // Catch: Exception -> L17
        if (BuildVars.DEBUG_VERSION == false) goto L269;
        FileLog.d("nosound_video = true");     // Catch: Exception -> L17
    L269:
        if (r4.access_hash != 0) goto L271;
        Object r72 = r317;
        boolean r59 = true;
    L275:
        r112 = new DelayedMessage(this, r127);     // Catch: Exception -> L17
        r112.type = 1;     // Catch: Exception -> L17
        r112.obj = r34;     // Catch: Exception -> L17
        r112.originalPath = r68;     // Catch: Exception -> L17
        r112.parentObject = r9;     // Catch: Exception -> L17
        r112.inputUploadMedia = r317;     // Catch: Exception -> L17
        if (r4.thumbs.isEmpty() == true) goto L280;
        TLRPC.PhotoSize r142 = r4.thumbs.get(0);     // Catch: Exception -> L17
        if ((r142 instanceof TLRPC.TL_photoStrippedSize) == true) goto L280;
        r112.photoSize = r142;     // Catch: Exception -> L17
        r112.locationParent = r4;     // Catch: Exception -> L17
    L280:
        r112.videoEditedInfo = r1010;     // Catch: Exception -> L17
        if ((r40 instanceof ImageLoader.PhotoSizeFromPhoto) == false) goto L283;
        r317.video_cover = ((ImageLoader.PhotoSizeFromPhoto) r40).inputPhoto;     // Catch: Exception -> L17
        r317.flags |= 64;
    L287:
        boolean r318 = false;
    L288:
        r112.performMediaUpload = r59;     // Catch: Exception -> L17
        r112.performCoverUpload = r318;     // Catch: Exception -> L17
        r45 = r318;
        r310 = r72;
        r7 = r59;
        goto L313
    L283:
        if (r40 == null) goto L287;
        if ((r40 instanceof TLRPC.TL_photoStrippedSize) == true) goto L287;
        r112.coverPhotoSize = r40;     // Catch: Exception -> L17
        r318 = true;
        goto L288
    L271:
        ?? r510 = new TLRPC.TL_inputMediaDocument();     // Catch: Exception -> L17
        TLRPC.TL_inputDocument r117 = new TLRPC.TL_inputDocument();     // Catch: Exception -> L17
        r510.id = r117;     // Catch: Exception -> L17
        r117.id = r4.id;     // Catch: Exception -> L17
        r117.access_hash = r4.access_hash;     // Catch: Exception -> L17
        byte[] r143 = r4.file_reference;     // Catch: Exception -> L17
        r117.file_reference = r143;     // Catch: Exception -> L17
        if (r143 != null) goto L274;
        r117.file_reference = new byte[0];     // Catch: Exception -> L17
    L274:
        r510.spoiler = r43;     // Catch: Exception -> L17
        r72 = r510;
        r59 = false;
        goto L275
    L263:
        r1010 = r372;
        goto L264
    L267:
        r1010 = r372;
    L256:
        r442 = r14;
        goto L257
    L289:
        VideoEditedInfo r1011 = r372;
        r442 = r14;
        if (r8 != 7) goto L308;
        ?? r319 = new TLRPC.TL_inputMediaUploadedDocument();     // Catch: Exception -> L17
        r319.mime_type = r4.mime_type;     // Catch: Exception -> L17
        r319.attributes = r4.attributes;     // Catch: Exception -> L17
        r319.spoiler = r43;     // Catch: Exception -> L17
        if (r4.access_hash != 0) goto L294;
        Object r511 = r319;
        r7 = true;
    L298:
        r112 = new DelayedMessage(this, r127);     // Catch: Exception -> L17
        r112.originalPath = r68;     // Catch: Exception -> L17
        r112.type = 2;     // Catch: Exception -> L17
        r112.obj = r34;     // Catch: Exception -> L17
        if (r4.thumbs.isEmpty() == true) goto L306;
        if (r1011 != null) goto L302;
    L303:
        TLRPC.PhotoSize r1012 = r4.thumbs.get(0);     // Catch: Exception -> L17
        if ((r1012 instanceof TLRPC.TL_photoStrippedSize) == true) goto L306;
        r112.photoSize = r1012;     // Catch: Exception -> L17
        r112.locationParent = r4;     // Catch: Exception -> L17
        goto L306
    L302:
        if (r1011.isSticker == false) goto L303;
    L306:
        r112.parentObject = r9;     // Catch: Exception -> L17
        r112.inputUploadMedia = r319;     // Catch: Exception -> L17
        r112.performMediaUpload = r7;     // Catch: Exception -> L17
        r316 = r511;
        goto L245
    L294:
        ?? r512 = new TLRPC.TL_inputMediaDocument();     // Catch: Exception -> L17
        TLRPC.TL_inputDocument r118 = new TLRPC.TL_inputDocument();     // Catch: Exception -> L17
        r512.id = r118;     // Catch: Exception -> L17
        r118.id = r4.id;     // Catch: Exception -> L17
        r118.access_hash = r4.access_hash;     // Catch: Exception -> L17
        byte[] r144 = r4.file_reference;     // Catch: Exception -> L17
        r118.file_reference = r144;     // Catch: Exception -> L17
        if (r144 != null) goto L297;
        r118.file_reference = new byte[0];     // Catch: Exception -> L17
    L297:
        r512.spoiler = r43;     // Catch: Exception -> L17
        r7 = false;
        r511 = r512;
        goto L298
    L308:
        if (r8 != '\n') goto L312;
        TLRPC.MessageMedia r320 = MessageObject.getMedia(r34.messageOwner);     // Catch: Exception -> L17
        if ((r320 instanceof TLRPC.TL_messageMediaToDo) == false) goto L312;
        ?? r48 = new TLRPC.TL_inputMediaTodo();     // Catch: Exception -> L17
        r48.todo = ((TLRPC.TL_messageMediaToDo) r320).todo;     // Catch: Exception -> L17
        r33 = r48;
    L190:
        if (r8 < 5) goto L193;
        if (r8 <= '\b') goto L188;
    L193:
        if (r8 == '\n') goto L188;
        r126 = 11;
        if (r8 == 11) goto L196;
        return;
    L182:
        r68 = null;
        goto L184
    L175:
        r65 = false;
        goto L176
    L155:
        r372 = r102;
        r103 = r24;
        ArrayList<TLRPC.MessageEntity> r69 = getMediaDataController().getEntities(new CharSequence[]{r34.editingMessage}, r103);     // Catch: Exception -> L17
        if (r69 == null) goto L161;
        if (r69.isEmpty() == true) goto L161;
        r11.entities = r69;     // Catch: Exception -> L17
        r11.flags |= 128;
    L161:
        if (TextUtils.equals(r125, r11.message) == true) goto L163;
        r11.flags &= -129;
    L152:
        r372 = r102;
        r103 = r24;
        goto L164
    L177:
        r372 = r102;
    L130:
        if (TextUtils.isEmpty(r11.attachPath) == true) goto L132;
        r34.attachPathExists = true;     // Catch: Exception -> L17
    L53:
        r9 = r44;
        goto L54
    L33:
        if ((r52 instanceof TLRPC.TL_messageMediaDocument) == false) goto L41;
        r4 = (TLRPC.TL_document) r52.document;     // Catch: Exception -> L17
        if (MessageObject.isVideoDocument(r4) == true) goto L39;
        if (r37 != null) goto L39;
        r53 = 7;
    L40:
        r102 = r34.videoEditedInfo;     // Catch: Exception -> L17
    L39:
        r53 = 3;
        goto L40
    L41:
        r102 = r37;
        if ((r52 instanceof TLRPC.TL_messageMediaToDo) == false) goto L44;
        r53 = '\n';
        goto L46
    L44:
        r53 = 65535;
    L45:
        r102 = r37;
        r53 = 1;
        goto L46
    L55:
        TLRPC.MessageMedia r92 = r11.media;     // Catch: Exception -> L17
        r34.previousMedia = r92;     // Catch: Exception -> L17
        r34.previousMessage = r11.message;     // Catch: Exception -> L17
        r34.previousMessageEntities = r11.entities;     // Catch: Exception -> L17
        r34.previousAttachPath = r11.attachPath;     // Catch: Exception -> L17
        if (r92 != null) goto L58;
        new TLRPC.TL_messageMediaEmpty();     // Catch: Exception -> L17
    L58:
        SerializedData r93 = new SerializedData(true);     // Catch: Exception -> L17
        writePreviousMessageData(r11, r93);     // Catch: Exception -> L17
        SerializedData r135 = new SerializedData(r93.length());     // Catch: Exception -> L17
        writePreviousMessageData(r11, r135);     // Catch: Exception -> L17
        r10.put("prevMedia", Base64.encodeToString(r135.toByteArray(), 0));     // Catch: Exception -> L17
        r135.cleanup();     // Catch: Exception -> L17
        if (r35 == null) goto L91;
        TLRPC.TL_messageMediaPoll r136 = (TLRPC.TL_messageMediaPoll) r11.media;     // Catch: Exception -> L17
        TLRPC.TL_pollAnswer r145 = new TLRPC.TL_pollAnswer();     // Catch: Exception -> L17
        r23 = "originalPath";
        r145.text = r35.text;     // Catch: Exception -> L17
        r145.option = r35.option;     // Catch: Exception -> L17
        int r82 = r136.poll.answers.size();     // Catch: Exception -> L17
        r136.poll.answers.add(r145);     // Catch: Exception -> L17
        r34.documentToPollAddOption = r4;     // Catch: Exception -> L17
        if (r3 == null) goto L72;
        TLRPC.TL_messageMediaPhoto r94 = new TLRPC.TL_messageMediaPhoto();     // Catch: Exception -> L17
        r145.media = r94;     // Catch: Exception -> L17
        r94.flags |= 3;
        r94.photo = r3;     // Catch: Exception -> L17
        r94.spoiler = r43;     // Catch: Exception -> L17
        if (r5 != null) goto L66;
    L70:
        ArrayList<TLRPC.PhotoSize> r95 = r3.sizes;     // Catch: Exception -> L17
        TLRPC.FileLocation r96 = r95.get(r95.size() - 1).location;     // Catch: Exception -> L17
        r11.attachPath = FileLoader.getInstance(this.currentAccount).getPathToAttach(r96, true).toString();     // Catch: Exception -> L17
        PollAttachedMediaPack.setAttachPath(r11, FileLoader.getInstance(this.currentAccount).getPathToAttach(r96, true).toString(), r82);     // Catch: Exception -> L17
    L71:
        r24 = r122;
        char r1210 = 2;
    L90:
        r8 = r1210;
    L120:
        r11.params = r10;     // Catch: Exception -> L17
        r11.send_state = 3;     // Catch: Exception -> L17
        r11.errorNewPriceStars = 0;     // Catch: Exception -> L17
        r11.errorAllowedPriceStars = 0;     // Catch: Exception -> L17
        r9 = r44;
        r14 = r10;
        r102 = r37;
        goto L122
    L66:
        if (r5.length() <= 0) goto L70;
        if (r5.startsWith("http") == false) goto L70;
        r11.attachPath = r5;     // Catch: Exception -> L17
        PollAttachedMediaPack.setAttachPath(r11, r5, r82);     // Catch: Exception -> L17
        goto L71
    L72:
        if (r4 == null) goto L86;
        TLRPC.TL_messageMediaDocument r137 = new TLRPC.TL_messageMediaDocument();     // Catch: Exception -> L17
        r145.media = r137;     // Catch: Exception -> L17
        r24 = r122;
        r137.flags |= 3;
        r137.document = r4;     // Catch: Exception -> L17
        r137.spoiler = r43;     // Catch: Exception -> L17
        if (MessageObject.isVideoDocument(r4) == true) goto L78;
        if (r37 != null) goto L78;
        r1210 = 7;
    L79:
        if (r37 == null) goto L81;
        r10.put("ve", r37.getString());     // Catch: Exception -> L17
    L81:
        r11.attachPath = r5;     // Catch: Exception -> L17
        PollAttachedMediaPack.setAttachPath(r11, r5, r82);     // Catch: Exception -> L17
        if ((r40 instanceof ImageLoader.PhotoSizeFromPhoto) == false) goto L84;
        TLRPC.MessageMedia r97 = r145.media;     // Catch: Exception -> L17
        r97.flags |= 512;
        r97.video_cover = ((ImageLoader.PhotoSizeFromPhoto) r40).photo;     // Catch: Exception -> L17
        goto L90
    L84:
        if (r40 == null) goto L90;
        TLRPC.TL_photo r83 = new TLRPC.TL_photo();     // Catch: Exception -> L17
        r83.date = getConnectionsManager().getCurrentTime();     // Catch: Exception -> L17
        r83.sizes.add(r40);     // Catch: Exception -> L17
        r83.file_reference = new byte[0];     // Catch: Exception -> L17
        TLRPC.MessageMedia r98 = r145.media;     // Catch: Exception -> L17
        r98.video_cover = r83;     // Catch: Exception -> L17
        r98.flags |= 512;
    L78:
        r1210 = 3;
        goto L79
    L86:
        r24 = r122;
        if (r35.input_media == null) goto L89;
        r1210 = 11;
        goto L90
    L89:
        r1210 = 1;
        goto L90
    L91:
        r23 = "originalPath";
        r24 = r122;
        if (r3 == null) goto L102;
        TLRPC.TL_messageMediaPhoto r84 = new TLRPC.TL_messageMediaPhoto();     // Catch: Exception -> L17
        r11.media = r84;     // Catch: Exception -> L17
        r84.flags |= 3;
        r84.photo = r3;     // Catch: Exception -> L17
        r84.spoiler = r43;     // Catch: Exception -> L17
        if (r5 != null) goto L96;
    L100:
        ArrayList<TLRPC.PhotoSize> r85 = r3.sizes;     // Catch: Exception -> L17
        r11.attachPath = FileLoader.getInstance(this.currentAccount).getPathToAttach(r85.get(r85.size() - 1).location, true).toString();     // Catch: Exception -> L17
    L101:
        r8 = 2;
        goto L120
    L96:
        if (r5.length() <= 0) goto L100;
        if (r5.startsWith("http") == false) goto L100;
        r11.attachPath = r5;     // Catch: Exception -> L17
        goto L101
    L102:
        if (r4 == null) goto L117;
        TLRPC.TL_messageMediaDocument r86 = new TLRPC.TL_messageMediaDocument();     // Catch: Exception -> L17
        r11.media = r86;     // Catch: Exception -> L17
        r86.flags |= 3;
        r86.document = r4;     // Catch: Exception -> L17
        r86.spoiler = r43;     // Catch: Exception -> L17
        if (MessageObject.isVideoDocument(r4) == true) goto L108;
        if (r37 != null) goto L108;
        r8 = 7;
    L109:
        if (r37 == null) goto L111;
        r10.put("ve", r37.getString());     // Catch: Exception -> L17
    L111:
        r11.attachPath = r5;     // Catch: Exception -> L17
        if ((r40 instanceof ImageLoader.PhotoSizeFromPhoto) == false) goto L114;
        TLRPC.MessageMedia r1211 = r11.media;     // Catch: Exception -> L17
        r1211.flags |= 512;
        r1211.video_cover = ((ImageLoader.PhotoSizeFromPhoto) r40).photo;     // Catch: Exception -> L17
        goto L120
    L114:
        if (r40 == null) goto L120;
        TLRPC.TL_photo r99 = new TLRPC.TL_photo();     // Catch: Exception -> L17
        r99.date = getConnectionsManager().getCurrentTime();     // Catch: Exception -> L17
        r99.sizes.add(r40);     // Catch: Exception -> L17
        r99.file_reference = new byte[0];     // Catch: Exception -> L17
        TLRPC.MessageMedia r1212 = r11.media;     // Catch: Exception -> L17
        r1212.video_cover = r99;     // Catch: Exception -> L17
        r1212.flags |= 512;
    L108:
        r8 = 3;
        goto L109
    L117:
        if ((r34.messageOwner.media instanceof TLRPC.TL_messageMediaToDo) == false) goto L119;
        r8 = '\n';
        goto L120
    L119:
        r8 = 1;
        goto L120
    L15:
        if (AndroidUtilities.getPeerLayerVersion(r12.layer) < 101) goto L19;
    L20:
        r122 = true;
    L17:
        e = e;
        goto L427
    L7:
        r10 = r41;
        goto L8
    }

    public void editMessage(MessageObject r13, TLRPC.TL_photo r14, VideoEditedInfo r15, TLRPC.TL_document r16, String r17, TLRPC.PhotoSize r18, HashMap<String, String> r19, boolean r20, boolean r21, Object r22) {
        editMessage(r13, null, r14, r15, r16, r17, r18, r19, r20, r21, r22);
    }

    public int editRichMessage(MessageObject r5, TL_iv.RichMessage r6, ArrayList<TLRPC.InputUser> r7, BaseFragment r8, boolean r9) {
        if (r5 == null) goto L25;
        if (r6 == null) goto L25;
        if (r5.messageOwner == null) goto L25;
        if (r5.isEditing() == false) goto L14;
        if (r5.messageOwner.reqId == 0) goto L14;
        getConnectionsManager().cancelRequest(r5.messageOwner.reqId, true);
        r5.messageOwner.reqId = 0;
    L14:
        if (r5.isEphemeral() == false) goto L16;
        TL_ephemeral.TL_editMessage r0 = new TL_ephemeral.TL_editMessage();
        r0.id = MessageObject.ephemeralMessageIdUnpack(r5.getId());
        r0.receiver_id = new TLRPC.TL_inputUserEmpty();
        r0.welcome = true;
        TLRPC.TL_messages_editMessage r02 = r0;
    L17:
        r02.peer = getMessagesController().getInputPeer(r5.getDialogId());
        r02.rich_message = richMessageToInputRichMessage(r6, r7);
        int r72 = r02.flags;
        r02.flags = 8388608 | r72;
        if (r5.scheduled == false) goto L20;
        r02.schedule_date = r5.messageOwner.date;
        r02.flags = r72 | 8421376;
    L20:
        TLRPC.Message r73 = r5.messageOwner;
        if ((r73.flags & TLObject.FLAG_30) == 0) goto L23;
        r02.quick_reply_shortcut_id = r73.quick_reply_shortcut_id;
        r02.flags |= 131072;
    L23:
        r73.rich_message = r6;
        r73.send_state = 3;
        int r62 = sendEditRichMessageRequest(r02, r5, r8, r9);
        notifyRichMessageEditing(r5);
        return r62;
    L16:
        TLRPC.TL_messages_editMessage r03 = new TLRPC.TL_messages_editMessage();
        r03.id = r5.getId();
        r02 = r03;
    L25:
        return 0;
    }

    public TLRPC.TL_photo generatePhotoSizes(String r3, Uri r4) {
        return generatePhotoSizes(null, r3, r4, false);
    }

    public TLRPC.TL_photo generatePhotoSizes(TLRPC.TL_photo r17, String r18, Uri r19, boolean r20) {
        Bitmap r2 = ImageLoader.loadBitmap(r18, r19, AndroidUtilities.getPhotoSize(r20), AndroidUtilities.getPhotoSize(r20), true);
        if (r2 != null) goto L5;
        r2 = ImageLoader.loadBitmap(r18, r19, 800.0f, 800.0f, true);
    L5:
        Bitmap r5 = r2;
        ArrayList<TLRPC.PhotoSize> r0 = new ArrayList();
        TLRPC.PhotoSize r1 = ImageLoader.scaleAndSaveImage(r5, 90.0f, 90.0f, 55, true);
        if (r1 == null) goto L8;
        r0.add(r1);
    L8:
        if (r20 == false) goto L10;
        Bitmap r6 = r5;
        TLRPC.PhotoSize r12 = ImageLoader.scaleAndSaveImage(null, r6, Bitmap.CompressFormat.JPEG, true, AndroidUtilities.getPhotoSize(r20), AndroidUtilities.getPhotoSize(r20), 99, false, 101, 101, false);
    L11:
        if (r12 == null) goto L13;
        r0.add(r12);
    L13:
        if (r6 == null) goto L16;
        r6.recycle();
    L16:
        if (r0.isEmpty() == false) goto L19;
        return null;
    L19:
        getUserConfig().saveConfig(false);
        if (r17 != null) goto L22;
        TLRPC.TL_photo r13 = new TLRPC.TL_photo();
    L23:
        r13.date = getConnectionsManager().getCurrentTime();
        r13.sizes = r0;
        r13.file_reference = new byte[0];
        return r13;
    L22:
        r13 = r17;
        goto L23
    L10:
        r12 = ImageLoader.scaleAndSaveImage(r5, AndroidUtilities.getPhotoSize(r20), AndroidUtilities.getPhotoSize(r20), true, 80, false, 101, 101);
        r6 = r5;
        goto L11
    }

    public ArrayList<DelayedMessage> getDelayedMessages(String r1) {
        return this.delayedMessages.get(r1);
    }

    public ImportingHistory getImportingHistory(long r1) {
        return this.importingHistoryMap.get(r1);
    }

    public ImportingStickers getImportingStickers(String r1) {
        return this.importingStickersMap.get(r1);
    }

    public long getNextRandomId() {
        long r2 = 0;
    L4:
        if (r2 != 0) goto L6;
        r2 = Utilities.random.nextLong();
        goto L4
    L6:
        return r2;
    }

    public int getSendingMessageId(long r6) {
        int r1 = 0;
    L4:
        if (r1 >= this.sendingMessages.size()) goto L10;
        TLRPC.Message r2 = this.sendingMessages.valueAt(r1);
        if (r2.dialog_id == r6) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        return r2.id;
    L10:
        int r12 = 0;
    L12:
        if (r12 >= this.uploadMessages.size()) goto L18;
        TLRPC.Message r22 = this.uploadMessages.valueAt(r12);
        if (r22.dialog_id == r6) goto L16;
        r12 = r12 + 1;
        goto L12
    L16:
        return r22.id;
    L18:
        return 0;
    }

    public Boolean getSendingTodoValue(MessageObject r3, TLRPC.TodoItem r4) {
        return this.waitingForTodoUpdate.get(Integer.valueOf(Objects.hash(new Object[]{Long.valueOf(r3.getDialogId()), Integer.valueOf(r3.getId()), Integer.valueOf(r4.id)})));
    }

    public long getVoteSendTime(long r3) {
        return this.voteSendTime.get(r3, 0L).longValue();
    }

    public boolean isImportingHistory() {
        if (this.importingHistoryMap.size() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isImportingStickers() {
        if (this.importingStickersMap.size() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isSendingCallback(MessageObject r5, TL_keyboard.KeyboardButtonProto r6) {
        int r0 = 0;
        if (r5 == null) goto L17;
        if (r6 == null) goto L17;
        if (TLKeyboardHelper.isType(r6, TL_keyboard.TL_inlineButtonTypeUrlAuth.class) == false) goto L10;
        r0 = 3;
    L16:
        return this.waitingForCallback.containsKey(r5.getDialogId() + "_" + r5.getId() + "_" + Utilities.bytesToHex(r6.getData()) + "_" + r0);
    L10:
        if (TLKeyboardHelper.isType(r6, TL_keyboard.TL_inlineButtonTypeGame.class) == false) goto L13;
        r0 = 1;
        goto L16
    L13:
        if (TLKeyboardHelper.isType(r6, TL_keyboard.TL_inlineButtonTypeBuy.class) == false) goto L16;
        r0 = 2;
    L17:
        return false;
    }

    public boolean isSendingCurrentLocation(MessageObject r4, TL_keyboard.KeyboardButtonProto r5) {
        if (r4 == null) goto L11;
        if (r5 == null) goto L13;
        StringBuilder r0 = new StringBuilder();
        r0.append(r4.getDialogId());
        r0.append("_");
        r0.append(r4.getId());
        r0.append("_");
        r0.append(Utilities.bytesToHex(r5.getData()));
        r0.append("_");
        if (TLKeyboardHelper.isType(r5, TL_keyboard.TL_inlineButtonTypeGame.class) == false) goto L8;
        String r42 = "1";
    L9:
        r0.append(r42);
        String r43 = r0.toString();
        return this.waitingForLocation.containsKey(r43);
    L8:
        r42 = MVEL.VERSION_SUB;
        goto L9
    L13:
        return false;
    L11:
        return false;
    }

    public boolean isSendingMessage(int r2) {
        if (this.sendingMessages.indexOfKey(r2) < 0) goto L5;
        return true;
    L5:
        if (this.editingMessages.indexOfKey(r2) >= 0) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isSendingMessageIdDialog(long r3) {
        if (this.sendingMessagesIdDialogs.get(r3, 0).intValue() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isSendingPaidMessage(int r12, int r13) {
        HashMap<String, ArrayList<DelayedMessage>> r0 = this.delayedMessages;
        DelayedMessage r2 = null;
        if (r0 == null) goto L24;
        Iterator<ArrayList<DelayedMessage>> r02 = r0.values().iterator();
    L6:
        if (r02.hasNext() == false) goto L24;
        ArrayList<DelayedMessage> r3 = r02.next();
        if (r3 == null) goto L6;
        int r4 = r3.size();
        int r5 = 0;
    L11:
        if (r5 >= r4) goto L23;
        DelayedMessage r6 = r3.get(r5);
        r5 = r5 + 1;
        DelayedMessage r62 = r6;
        ArrayList<TLRPC.Message> r7 = r62.messages;
        if (r7 == null) goto L11;
        int r8 = r7.size();
        int r9 = 0;
    L16:
        if (r9 >= r8) goto L22;
        TLRPC.Message r10 = r7.get(r9);
        r9 = r9 + 1;
        TLRPC.Message r102 = r10;
        if (r102 == null) goto L16;
        if (r102.id != r12) goto L16;
        r2 = r62;
    L22:
        if (r2 == null) goto L11;
    L23:
        if (r2 == null) goto L6;
    L24:
        if (r2 == null) goto L30;
        if (r13 < 0) goto L30;
        if (r13 >= r2.messages.size()) goto L30;
        r12 = r2.messages.get(r13).id;
    L30:
        if (this.sendingMessages.indexOfKey(r12) < 0) goto L32;
        return true;
    L32:
        if (this.editingMessages.indexOfKey(r12) >= 0) goto L55;
        return false;
    L55:
        return true;
    }

    public byte[] isSendingVote(MessageObject r4) {
        if (r4 != null) goto L6;
        return null;
    L6:
        return this.waitingForVote.get("poll_" + r4.getPollId());
    }

    public boolean isUploadingMessageIdDialog(long r3) {
        if (this.uploadingMessagesIdDialogs.get(r3, 0).intValue() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void onMessageEdited(TLRPC.Message r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.reply_markup == null) goto L13;
        List<String> r52 = this.waitingForCallbackMap.remove(r5.dialog_id + "_" + r5.id);
        if (r52 == null) goto L14;
        Iterator<String> r53 = r52.iterator();
    L9:
        if (r53.hasNext() == false) goto L15;
        this.waitingForCallback.remove(r53.next());
        goto L9
    L15:
        return;
    L14:
        return;
    }

    public void performSendMessageRequest(TLObject r15, MessageObject r16, String r17, DelayedMessage r18, boolean r19, DelayedMessage r20, Object r21, HashMap<String, String> r22, boolean r23) {
        if ((r15 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L12;
        TLRPC.TL_messages_addPollAnswer r2 = (TLRPC.TL_messages_addPollAnswer) r15;
        TLRPC.InputMedia r0 = r2.answer.input_media;
        if ((r0 instanceof TLRPC.TL_inputMediaUploadedDocument) == false) goto L7;
    L10:
        TLRPC.TL_messages_uploadMedia r12 = new TLRPC.TL_messages_uploadMedia();
        r12.peer = r2.peer;
        r12.media = r2.answer.input_media;
        getConnectionsManager().sendRequest(r12, new SendMessagesHelper$$ExternalSyntheticLambda104(this, r2, r15, r16, r17, r18, r19, r20, r21, r22, r23));
        return;
    L7:
        if ((r0 instanceof TLRPC.TL_inputMediaUploadedPhoto) == true) goto L10;
    L12:
        int r122 = 0;
        boolean r123 = false;
        if ((r15 instanceof TLRPC.TL_messages_editMessage) == true) goto L25;
        if (r19 == false) goto L25;
        DelayedMessage r02 = findMaxDelayedMessageForMessageId(r16.getId(), r16.getDialogId());
        if (r02 == null) goto L25;
        if (r18 == null) goto L19;
        r123 = r18.scheduled;
    L19:
        r02.addDelayedRequest(r15, r16, r17, r21, r20, r123);
        if (r18 == null) goto L41;
        ArrayList<DelayedMessageSendAfterRequest> r14 = r18.requests;
        if (r14 == null) goto L42;
        r02.requests.addAll(r14);
        return;
    L42:
        return;
    L41:
        return;
    L25:
        TLRPC.Message r11 = r16.messageOwner;
        putToSendingMessages(r11, r23);
        if (StarsController.getInstance(this.currentAccount).beforeSendingFinalRequest(r15, r16, new SendMessagesHelper$$ExternalSyntheticLambda105(this, r15, r16, r17, r18, r19, r20, r21, r22, r23)) == true) goto L29;
        return;
    L29:
        if (BotForumHelper.getInstance(this.currentAccount).beforeSendingFinalRequest(r15, r16, new SendMessagesHelper$$ExternalSyntheticLambda106(this, r15, r16, r17, r18, r19, r20, r21, r22, r23)) == true) goto L32;
        return;
    L32:
        if (EphemeralMessagesHelper.getInstance(this.currentAccount).beforeSendingFinalRequest(r15, r16, new SendMessagesHelper$$ExternalSyntheticLambda107(this, r16, r17, r18, r19, r20, r21, r22, r23)) == false) goto L46;
        ConnectionsManager r13 = getConnectionsManager();
        SendMessagesHelper$$ExternalSyntheticLambda108 r03 = new SendMessagesHelper$$ExternalSyntheticLambda108(this, r15, r16, r17, r18, r19, r20, r21, r22, r23, r11);
        SendMessagesHelper$$ExternalSyntheticLambda109 r3 = new SendMessagesHelper$$ExternalSyntheticLambda109(this, r11);
        if ((r15 instanceof TLRPC.TL_messages_sendMessage) == false) goto L37;
        r122 = 128;
    L37:
        r11.reqId = r13.sendRequest(r15, r03, r3, r122 | 68);
        if (r18 == null) goto L43;
        r18.sendDelayedRequests();
        return;
    L43:
        return;
    }

    public void performSendMessageRequestMulti(TLObject r10, ArrayList<MessageObject> r11, ArrayList<String> r12, ArrayList<Object> r13, DelayedMessage r14, boolean r15) {
        int r0 = r11.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L6;
        putToSendingMessages(r11.get(r2).messageOwner, r15);
        r2 = r2 + 1;
        goto L3
    L6:
        if (StarsController.getInstance(this.currentAccount).beforeSendingFinalRequest(r10, r11, new SendMessagesHelper$$ExternalSyntheticLambda86(this, r10, r11, r12, r13, r14, r15)) == true) goto L9;
        return;
    L9:
        if (BotForumHelper.getInstance(this.currentAccount).beforeSendingFinalRequest(r10, r11, new SendMessagesHelper$$ExternalSyntheticLambda87(this, r10, r11, r12, r13, r14, r15)) == true) goto L12;
        return;
    L12:
        if (EphemeralMessagesHelper.getInstance(this.currentAccount).beforeSendingFinalRequest(r10, r11, new SendMessagesHelper$$ExternalSyntheticLambda88(this, r11, r12, r13, r14, r15)) == true) goto L14;
        return;
    L14:
        getConnectionsManager().sendRequest(r10, new SendMessagesHelper$$ExternalSyntheticLambda89(this, r13, r10, r11, r12, r14, r15), null, 68);
    }

    public void prepareImportHistory(long r9, Uri r11, ArrayList<Uri> r12, MessagesStorage.LongCallback r13) {
        if (this.importingHistoryMap.get(r9) == null) goto L7;
        r13.run(0);
        return;
    L7:
        if (DialogObject.isChatDialog(r9) == false) goto L16;
        long r4 = r9;
        Uri r6 = r11;
        long r10 = -r4;
        TLRPC.Chat r92 = getMessagesController().getChat(Long.valueOf(r10));
        if (r92 != null) goto L11;
    L14:
        SendMessagesHelper r2 = this;
    L17:
        new Thread(new SendMessagesHelper$$ExternalSyntheticLambda60(r2, r12, r4, r6, r13)).start();
        return;
    L11:
        if (r92.megagroup == true) goto L14;
        getMessagesController().convertToMegaGroup(null, r10, null, new SendMessagesHelper$$ExternalSyntheticLambda59(this, r6, r12, r13));
        return;
    L16:
        r2 = this;
        r4 = r9;
        r6 = r11;
        goto L17
    }

    public void prepareImportStickers(String r9, String r10, String r11, ArrayList<ImportingSticker> r12, MessagesStorage.StringCallback r13) {
        if (this.importingStickersMap.get(r10) == null) goto L6;
        r13.run(null);
        return;
    L6:
        new Thread(new SendMessagesHelper$$ExternalSyntheticLambda24(this, r9, r10, r11, r12, r13)).start();
    }

    public void processForwardFromMyName(MessageObject r28, long r29, long r31, long r33, MessageSuggestionParams r35) {
        if (r28 == null) goto L87;
        TLRPC.Message r1 = r28.messageOwner;
        TLRPC.MessageMedia r2 = r1.media;
        ArrayList r3 = null;
        HashMap r32 = null;
        r32 = null;
        r32 = null;
        r3 = null;
        if (r2 == null) goto L48;
        if ((r2 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L48;
        if ((r2 instanceof TLRPC.TL_messageMediaWebPage) == true) goto L48;
        if ((r2 instanceof TLRPC.TL_messageMediaGame) == true) goto L48;
        if ((r2 instanceof TLRPC.TL_messageMediaInvoice) == true) goto L48;
        if (DialogObject.isEncryptedDialog(r29) == false) goto L24;
        TLRPC.Message r12 = r28.messageOwner;
        if (r12.peer_id == null) goto L24;
        TLRPC.MessageMedia r13 = r12.media;
        if ((r13.photo instanceof TLRPC.TL_photo) == false) goto L22;
    L23:
        r32 = new HashMap();
        r32.put("parentObject", "sent_" + r28.messageOwner.peer_id.channel_id + "_" + r28.getId() + "_" + r28.getDialogId() + "_" + r28.type + "_" + r28.getSize());
        goto L24
    L22:
        if ((r13.document instanceof TLRPC.TL_document) == true) goto L23;
    L24:
        HashMap r10 = r32;
        TLRPC.Message r14 = r28.messageOwner;
        TLRPC.MessageMedia r11 = r14.media;
        TLRPC.Photo r22 = r11.photo;
        if ((r22 instanceof TLRPC.TL_photo) == false) goto L28;
        SendMessageParams r15 = SendMessageParams.of((TLRPC.TL_photo) r22, null, r29, r28.replyMessageObject, null, r14.message, r14.entities, null, r10, true, 0, 0, r11.ttl_seconds, r28, false);
        r15.payStars = r31;
        r15.monoForumPeer = r33;
        r15.suggestionParams = r35;
        sendMessage(r15);
        return;
    L28:
        TLRPC.Document r7 = r11.document;
        if ((r7 instanceof TLRPC.TL_document) == false) goto L33;
        SendMessageParams r16 = SendMessageParams.of((TLRPC.TL_document) r7, null, r14.attachPath, r29, r28.replyMessageObject, null, r14.message, r14.entities, null, r10, true, 0, 0, r11.ttl_seconds, r28, null, false);
        r16.payStars = r31;
        r16.monoForumPeer = r33;
        r16.suggestionParams = r35;
        sendMessage(r16);
        return;
    L33:
        if ((r11 instanceof TLRPC.TL_messageMediaVenue) == true) goto L45;
        if ((r11 instanceof TLRPC.TL_messageMediaGeo) == true) goto L45;
        if (r11.phone_number == null) goto L42;
        TLRPC.TL_userContact_old2 r122 = new TLRPC.TL_userContact_old2();
        TLRPC.MessageMedia r17 = r28.messageOwner.media;
        r122.phone = r17.phone_number;
        r122.first_name = r17.first_name;
        r122.last_name = r17.last_name;
        r122.id = r17.user_id;
        SendMessageParams r18 = SendMessageParams.of(r122, r29, r28.replyMessageObject, null, null, null, true, 0, 0);
        r18.monoForumPeer = r33;
        r18.suggestionParams = r35;
        r18.payStars = r31;
        sendMessage(r18);
        return;
    L42:
        if (DialogObject.isEncryptedDialog(r29) == true) goto L85;
        ArrayList<MessageObject> r19 = new ArrayList();
        r19.add(r28);
        sendMessage(r19, r29, true, false, true, 0, 0, null, -1, r31, r33, r35);
        return;
    L85:
        return;
    L45:
        SendMessageParams r110 = SendMessageParams.of(r11, r29, r28.replyMessageObject, null, null, null, true, 0, 0);
        r110.payStars = r31;
        r110.monoForumPeer = r33;
        r110.suggestionParams = r35;
        sendMessage(r110);
        return;
    L48:
        if (r1.message == null) goto L79;
        if ((r2 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L52;
        TLRPC.WebPage r172 = r2.webpage;
    L53:
        ArrayList<TLRPC.MessageEntity> r111 = r1.entities;
        if (r111 != null) goto L56;
    L76:
        SendMessageParams r112 = SendMessageParams.of(r28.messageOwner.message, r29, r28.replyMessageObject, null, r172, true, r3, null, null, true, 0, 0, null, false);
        r112.payStars = r31;
        r112.monoForumPeer = r33;
        r112.suggestionParams = r35;
        sendMessage(r112);
        return;
    L56:
        if (r111.isEmpty() == true) goto L76;
        r3 = new ArrayList();
        int r113 = 0;
    L59:
        if (r113 >= r28.messageOwner.entities.size()) goto L76;
        TLRPC.MessageEntity r23 = r28.messageOwner.entities.get(r113);
        if ((r23 instanceof TLRPC.TL_messageEntityBold) == false) goto L63;
    L74:
        r3.add(r23);
    L75:
        r113 = r113 + 1;
        goto L59
    L63:
        if ((r23 instanceof TLRPC.TL_messageEntityItalic) == true) goto L74;
        if ((r23 instanceof TLRPC.TL_messageEntityPre) == true) goto L74;
        if ((r23 instanceof TLRPC.TL_messageEntityCode) == true) goto L74;
        if ((r23 instanceof TLRPC.TL_messageEntityTextUrl) == true) goto L74;
        if ((r23 instanceof TLRPC.TL_messageEntitySpoiler) == true) goto L74;
        if ((r23 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L75;
    L52:
        r172 = null;
        goto L53
    L79:
        if (DialogObject.isEncryptedDialog(r29) == false) goto L86;
        ArrayList<MessageObject> r114 = new ArrayList();
        r114.add(r28);
        sendMessage(r114, r29, true, false, true, 0, 0, null, -1, r31, r33, r35);
        return;
    L86:
        return;
    }

    public void processSentMessage(int r3) {
        int r0 = this.unsentMessages.size();
        this.unsentMessages.remove(r3);
        if (r0 != 0) goto L5;
        return;
    L5:
        if (this.unsentMessages.size() != 0) goto L9;
        checkUnsentMessages();
        return;
    }

    public void processUnsentMessages(ArrayList<TLRPC.Message> r8, ArrayList<TLRPC.Message> r9, ArrayList<TLRPC.User> r10, ArrayList<TLRPC.Chat> r11, ArrayList<TLRPC.EncryptedChat> r12) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda103(this, r10, r11, r12, r8, r9));
    }

    public void putToSendingMessages(TLRPC.Message r3, boolean r4) {
        if (Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) goto L6;
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda137(this, r3, r4));
        return;
    L6:
        putToSendingMessages(r3, r4, true);
    }

    public void putToSendingMessages(TLRPC.Message r6, boolean r7, boolean r8) {
        if (r6 == null) goto L25;
        int r0 = r6.id;
        if (r0 <= 0) goto L9;
        this.editingMessages.put(r0, r6);
        return;
    L9:
        if (this.sendingMessages.indexOfKey(r0) < 0) goto L11;
        boolean r02 = true;
    L12:
        removeFromUploadingMessages(r6.id, r7);
        this.sendingMessages.put(r6.id, r6);
        if (r7 == true) goto L21;
        if (r02 == false) goto L16;
        return;
    L16:
        if (MessageObject.isEphemeral(r6) == true) goto L23;
        long r62 = MessageObject.getDialogId(r6);
        LongSparseArray<Integer> r03 = this.sendingMessagesIdDialogs;
        r03.put(r62, Integer.valueOf(r03.get(r62, 0).intValue() + 1));
        if (r8 == false) goto L24;
        getNotificationCenter().postNotificationName(NotificationCenter.sendingMessagesChanged, new Object[0]);
        return;
    L24:
        return;
    L23:
        return;
    L21:
        return;
    L11:
        r02 = false;
        goto L12
    }

    public void putToUploadingMessages(MessageObject r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (r6.getId() <= 0) goto L6;
        return;
    L6:
        if (r6.scheduled == true) goto L22;
        TLRPC.Message r62 = r6.messageOwner;
        if (this.uploadMessages.indexOfKey(r62.id) < 0) goto L11;
        boolean r0 = true;
    L12:
        this.uploadMessages.put(r62.id, r62);
        if (r0 == false) goto L15;
        return;
    L15:
        if (MessageObject.isEphemeral(r62) == true) goto L21;
        long r3 = MessageObject.getDialogId(r62);
        LongSparseArray<Integer> r63 = this.uploadingMessagesIdDialogs;
        r63.put(r3, Integer.valueOf(r63.get(r3, 0).intValue() + 1));
        getNotificationCenter().postNotificationName(NotificationCenter.sendingMessagesChanged, new Object[0]);
        return;
    L21:
        return;
    L11:
        r0 = false;
        goto L12
    }

    public TLRPC.Message removeFromSendingMessages(int r4, boolean r5) {
        if (r4 <= 0) goto L7;
        TLRPC.Message r52 = this.editingMessages.get(r4);
        if (r52 == null) goto L6;
        this.editingMessages.remove(r4);
    L6:
        return r52;
    L7:
        TLRPC.Message r0 = this.sendingMessages.get(r4);
        if (r0 == null) goto L18;
        this.sendingMessages.remove(r4);
        if (r5 == true) goto L18;
        long r42 = MessageObject.getDialogId(r0);
        Integer r1 = this.sendingMessagesIdDialogs.get(r42);
        if (r1 == null) goto L18;
        int r12 = r1.intValue() - 1;
        LongSparseArray<Integer> r2 = this.sendingMessagesIdDialogs;
        if (r12 > 0) goto L16;
        r2.remove(r42);
    L17:
        getNotificationCenter().postNotificationName(NotificationCenter.sendingMessagesChanged, new Object[0]);
        goto L18
    L16:
        r2.put(r42, Integer.valueOf(r12));
    L18:
        return r0;
    }

    public void removeFromUploadingMessages(int r3, boolean r4) {
        if (r3 > 0) goto L15;
        if (r4 == true) goto L18;
        TLRPC.Message r42 = this.uploadMessages.get(r3);
        if (r42 == null) goto L16;
        this.uploadMessages.remove(r3);
        long r32 = MessageObject.getDialogId(r42);
        Integer r0 = this.uploadingMessagesIdDialogs.get(r32);
        if (r0 == null) goto L17;
        int r02 = r0.intValue() - 1;
        LongSparseArray<Integer> r1 = this.uploadingMessagesIdDialogs;
        if (r02 > 0) goto L12;
        r1.remove(r32);
    L13:
        getNotificationCenter().postNotificationName(NotificationCenter.sendingMessagesChanged, new Object[0]);
        return;
    L12:
        r1.put(r32, Integer.valueOf(r02));
        goto L13
    L17:
        return;
    L16:
        return;
    L18:
        return;
    }

    public void requestUrlAuth(String r8, ChatActivity r9, boolean r10) {
        TLRPC.TL_messages_requestUrlAuth r2 = new TLRPC.TL_messages_requestUrlAuth();
        r2.url = r8;
        r2.flags |= 4;
        getConnectionsManager().sendRequest(r2, new SendMessagesHelper$$ExternalSyntheticLambda119(this, r2, r9, r8, r10), 2);
    }

    public boolean retrySendMessage(MessageObject r13, boolean r14, long r15) {
        if (r13.getId() >= 0) goto L5;
        TLRPC.MessageAction r2 = r13.messageOwner.action;
        if ((r2 instanceof TLRPC.TL_messageEncryptedAction) == false) goto L58;
        int r22 = DialogObject.getEncryptedChatId(r13.getDialogId());
        TLRPC.EncryptedChat r23 = getMessagesController().getEncryptedChat(Integer.valueOf(r22));
        if (r23 != null) goto L14;
        getMessagesStorage().markMessageAsSendError(r13.messageOwner, r13.scheduled ? 1 : 0);
        r13.messageOwner.send_state = 2;
        getNotificationCenter().postNotificationName(NotificationCenter.messageSendError, new Object[]{Integer.valueOf(r13.getId())});
        processSentMessage(r13.getId());
        return false;
    L14:
        TLRPC.Message r3 = r13.messageOwner;
        if (r3.random_id != 0) goto L17;
        r3.random_id = getNextRandomId();
    L17:
        TLRPC.DecryptedMessageAction r32 = r13.messageOwner.action.encryptedAction;
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) == false) goto L21;
        getSecretChatHelper().sendTTLMessage(r23, r13.messageOwner);
    L56:
        return true;
    L21:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionDeleteMessages) == false) goto L24;
        getSecretChatHelper().sendMessagesDeleteMessage(r23, null, r13.messageOwner);
        goto L56
    L24:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionFlushHistory) == false) goto L27;
        getSecretChatHelper().sendClearHistoryMessage(r23, r13.messageOwner);
        goto L56
    L27:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionNotifyLayer) == false) goto L30;
        getSecretChatHelper().sendNotifyLayerMessage(r23, r13.messageOwner);
        goto L56
    L30:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionReadMessages) == false) goto L33;
        getSecretChatHelper().sendMessagesReadMessage(r23, null, r13.messageOwner);
        goto L56
    L33:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) == false) goto L36;
        getSecretChatHelper().sendScreenshotMessage(r23, null, r13.messageOwner);
        goto L56
    L36:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionTyping) == true) goto L56;
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionResend) == false) goto L42;
        getSecretChatHelper().sendResendMessage(r23, 0, 0, r13.messageOwner);
        goto L56
    L42:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionCommitKey) == false) goto L45;
        getSecretChatHelper().sendCommitKeyMessage(r23, r13.messageOwner);
        goto L56
    L45:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionAbortKey) == false) goto L48;
        getSecretChatHelper().sendAbortKeyMessage(r23, r13.messageOwner, 0);
        goto L56
    L48:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionRequestKey) == false) goto L51;
        getSecretChatHelper().sendRequestKeyMessage(r23, r13.messageOwner);
        goto L56
    L51:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionAcceptKey) == false) goto L54;
        getSecretChatHelper().sendAcceptKeyMessage(r23, r13.messageOwner);
        goto L56
    L54:
        if ((r32 instanceof TLRPC.TL_decryptedMessageActionNoop) == false) goto L56;
        getSecretChatHelper().sendNoopMessage(r23, r13.messageOwner);
        goto L56
    L58:
        if ((r2 instanceof TLRPC.TL_messageActionScreenshotTaken) == false) goto L60;
        sendScreenshotMessage(getMessagesController().getUser(Long.valueOf(r13.getDialogId())), r13.getReplyMsgId(), r13.messageOwner);
    L60:
        if (r14 == false) goto L62;
        this.unsentMessages.put(r13.getId(), r13);
    L62:
        SendMessageParams r1 = SendMessageParams.of(r13);
        r1.payStars = r15;
        sendMessage(r1);
        return true;
    L5:
        if (r13.isEditing() == false) goto L7;
        editMessage(r13, null, null, null, null, null, null, true, r13.hasMediaSpoilers(), r13);
    L7:
        return false;
    }

    public void sendCallback(boolean r19, MessageObject r20, TL_keyboard.KeyboardButtonProto r21, TLRPC.InputCheckPasswordSRP r22, TwoStepVerificationActivity r23, ChatActivity r24) {
        if (r20 == null) goto L57;
        if (r21 == null) goto L58;
        if (r24 != null) goto L8;
        return;
    L8:
        if (TLKeyboardHelper.isType(r21, TL_keyboard.TL_inlineButtonTypeUrlAuth.class) == false) goto L12;
        int r0 = 3;
    L10:
        boolean r4 = false;
    L18:
        String r02 = r20.getDialogId() + "_" + r20.getId() + "_" + Utilities.bytesToHex(r21.getData()) + "_" + r0;
        this.waitingForCallback.put(r02, Boolean.TRUE);
        List<String> r3 = this.waitingForCallbackMap.get(r20.getDialogId() + "_" + r20.getId());
        if (r3 != null) goto L21;
        HashMap<String, List<String>> r32 = this.waitingForCallbackMap;
        String r5 = r20.getDialogId() + "_" + r20.getId();
        ArrayList r7 = new ArrayList();
        r32.put(r5, r7);
        r3 = r7;
    L21:
        r3.add(r02);
        TLObject[] r9 = new TLObject[1];
        SendMessagesHelper$$ExternalSyntheticLambda96 r03 = new SendMessagesHelper$$ExternalSyntheticLambda96(this, r02, r3, r4, r20, r21, r24, r23, r9, r22, r19);
        if (r4 == false) goto L25;
        getMessagesStorage().getBotCache(r02, r03);
        return;
    L25:
        TL_keyboard.TL_inlineButtonTypeUrlAuth r1 = (TL_keyboard.TL_inlineButtonTypeUrlAuth) TLKeyboardHelper.getType(r21, TL_keyboard.TL_inlineButtonTypeUrlAuth.class);
        if (r1 == null) goto L30;
        TLRPC.TL_messages_requestUrlAuth r2 = new TLRPC.TL_messages_requestUrlAuth();
        r2.peer = getMessagesController().getInputPeer(r20.getDialogId());
        r2.msg_id = r20.getId();
        r2.button_id = r1.button_id;
        r2.flags |= 2;
        r9[0] = r2;
        getConnectionsManager().sendRequest(r2, r03, 2);
        return;
    L30:
        if (TLKeyboardHelper.isType(r21, TL_keyboard.TL_inlineButtonTypeBuy.class) == false) goto L41;
        if ((r20.messageOwner.media.flags & 4) != 0) goto L38;
        TLRPC.TL_payments_getPaymentForm r12 = new TLRPC.TL_payments_getPaymentForm();
        TLRPC.TL_inputInvoiceMessage r25 = new TLRPC.TL_inputInvoiceMessage();
        r25.msg_id = r20.getId();
        r25.peer = getMessagesController().getInputPeer(r20.messageOwner.peer_id);
        r12.invoice = r25;
        JSONObject r26 = BotWebViewSheet.makeThemeParams(null);
        if (r26 == null) goto L36;
        TLRPC.TL_dataJSON r33 = new TLRPC.TL_dataJSON();
        r12.theme_params = r33;
        r33.data = r26.toString();
        r12.flags |= 1;
    L36:
        r9[0] = r12;
        getConnectionsManager().sendRequest(r12, r03, 2);
        return;
    L38:
        TLRPC.TL_payments_getPaymentReceipt r13 = new TLRPC.TL_payments_getPaymentReceipt();
        r13.msg_id = r20.messageOwner.media.receipt_msg_id;
        r13.peer = getMessagesController().getInputPeer(r20.messageOwner.peer_id);
        r9[0] = r13;
        getConnectionsManager().sendRequest(r13, r03, 2);
        return;
    L41:
        if (r20.isEphemeral() == false) goto L44;
        TL_ephemeral.TL_getCallbackAnswer r14 = new TL_ephemeral.TL_getCallbackAnswer();
        r14.peer = getMessagesController().getInputPeer(r20.getDialogId());
        r14.id = r20.getEphemeralId();
        r14.data = r21.getData();
        getConnectionsManager().sendRequest(r14, r03, 2);
        return;
    L44:
        TLRPC.TL_messages_getBotCallbackAnswer r15 = new TLRPC.TL_messages_getBotCallbackAnswer();
        r15.peer = getMessagesController().getInputPeer(r20.getDialogId());
        r15.msg_id = r20.getId();
        r15.game = TLKeyboardHelper.isType(r21, TL_keyboard.TL_inlineButtonTypeGame.class);
        TL_keyboard.TL_inlineButtonTypeCallback r27 = (TL_keyboard.TL_inlineButtonTypeCallback) TLKeyboardHelper.getType(r21, TL_keyboard.TL_inlineButtonTypeCallback.class);
        if (r27 == null) goto L53;
        if (r27.requires_password == false) goto L53;
        if (r22 == null) goto L50;
        TLRPC.InputCheckPasswordSRP r28 = r22;
    L51:
        r15.password = r28;
        r15.flags |= 4;
        goto L53
    L50:
        r28 = new TLRPC.TL_inputCheckPasswordEmpty();
    L53:
        if (r21.getData() == null) goto L55;
        r15.flags |= 1;
        r15.data = r21.getData();
    L55:
        getConnectionsManager().sendRequest(r15, r03, 2);
        return;
    L12:
        if (TLKeyboardHelper.isType(r21, TL_keyboard.TL_inlineButtonTypeGame.class) == false) goto L15;
        r0 = 1;
        goto L10
    L15:
        if (TLKeyboardHelper.isType(r21, TL_keyboard.TL_inlineButtonTypeBuy.class) == false) goto L17;
        r4 = r19;
        r0 = 2;
        goto L18
    L17:
        r4 = r19;
        r0 = 0;
        goto L18
    L58:
        return;
    }

    public void sendCallback(boolean r8, MessageObject r9, TL_keyboard.KeyboardButtonProto r10, ChatActivity r11) {
        sendCallback(r8, r9, r10, null, null, r11);
    }

    public void sendCurrentLocation(MessageObject r4, TL_keyboard.KeyboardButtonProto r5) {
        if (r4 == null) goto L11;
        if (r5 == null) goto L12;
        StringBuilder r0 = new StringBuilder();
        r0.append(r4.getDialogId());
        r0.append("_");
        r0.append(r4.getId());
        r0.append("_");
        r0.append(Utilities.bytesToHex(r5.getData()));
        r0.append("_");
        if (TLKeyboardHelper.isType(r5, TL_keyboard.TL_inlineButtonTypeGame.class) == false) goto L8;
        String r52 = "1";
    L9:
        r0.append(r52);
        String r53 = r0.toString();
        this.waitingForLocation.put(r53, r4);
        this.locationProvider.start();
        return;
    L8:
        r52 = MVEL.VERSION_SUB;
        goto L9
    L12:
        return;
    }

    public void sendGame(TLRPC.InputPeer r8, TLRPC.TL_inputMediaGame r9, long r10, long r12) {
        if (r8 == null) goto L43;
        if (r9 == null) goto L44;
        TLRPC.TL_messages_sendMedia r0 = new TLRPC.TL_messages_sendMedia();
        r0.peer = r8;
        if ((r8 instanceof TLRPC.TL_inputPeerChannel) == false) goto L8;
        r0.silent = MessagesController.getNotificationsSettings(this.currentAccount).getBoolean(NotificationsSettingsFacade.PROPERTY_SILENT + (-r8.channel_id), false);
    L13:
        if (r10 == 0) goto L15;
        long r3 = r10;
    L16:
        r0.random_id = r3;
        r0.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.media = r9;
        long r32 = ChatObject.getSendAsPeerId(getMessagesController().getChat(Long.valueOf(r8.chat_id)), getMessagesController().getChatFull(r8.chat_id));
        if (r32 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L19;
        r0.send_as = getMessagesController().getInputPeer(r32);
    L19:
        long r33 = getMessagesController().getSendPaidMessagesStars(DialogObject.getPeerDialogId(r8));
        if (r33 > 0) goto L23;
        r33 = DialogObject.getMessagesStarsPrice(getMessagesController().isUserContactBlocked(DialogObject.getPeerDialogId(r8)));
    L23:
        if (r33 <= 0) goto L26;
        r0.flags |= TLObject.FLAG_21;
        r0.allow_paid_stars = r33;
    L26:
        if (r12 != 0) goto L37;
        NativeByteBuffer r122 = null;
        NativeByteBuffer r13 = new NativeByteBuffer((r8.getObjectSize() + r9.getObjectSize()) + 12);     // Catch: Exception -> L34
        r13.writeInt32(3);     // Catch: Exception -> L32
        r13.writeInt64(r10);     // Catch: Exception -> L32
        r8.serializeToStream(r13);     // Catch: Exception -> L32
        r9.serializeToStream(r13);     // Catch: Exception -> L32
    L36:
        r12 = getMessagesStorage().createPendingTask(r13);
    L32:
        e = e;
        r122 = r13;
    L35:
        FileLog.e(e);
        r13 = r122;
    L34:
        e = e;
    L37:
        getConnectionsManager().sendRequest(r0, new SendMessagesHelper$$ExternalSyntheticLambda134(this, r12));
        return;
    L15:
        r3 = getNextRandomId();
        goto L16
    L8:
        boolean r1 = r8 instanceof TLRPC.TL_inputPeerChat;
        int r4 = this.currentAccount;
        if (r1 == false) goto L11;
        r0.silent = MessagesController.getNotificationsSettings(r4).getBoolean(NotificationsSettingsFacade.PROPERTY_SILENT + (-r8.chat_id), false);
        goto L13
    L11:
        r0.silent = MessagesController.getNotificationsSettings(r4).getBoolean(NotificationsSettingsFacade.PROPERTY_SILENT + r8.user_id, false);
        goto L13
    L44:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v10, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18, types: [int] */
    /* JADX WARN: Type inference failed for: r12v22 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v20 */
    /* JADX WARN: Type inference failed for: r14v26 */
    /* JADX WARN: Type inference failed for: r14v27 */
    /* JADX WARN: Type inference failed for: r14v36 */
    /* JADX WARN: Type inference failed for: r14v37 */
    /* JADX WARN: Type inference failed for: r14v42 */
    /* JADX WARN: Type inference failed for: r14v43 */
    /* JADX WARN: Type inference failed for: r14v44 */
    /* JADX WARN: Type inference failed for: r14v45 */
    /* JADX WARN: Type inference failed for: r14v46 */
    /* JADX WARN: Type inference failed for: r1v106 */
    /* JADX WARN: Type inference failed for: r1v107, types: [int] */
    /* JADX WARN: Type inference failed for: r1v114 */
    /* JADX WARN: Type inference failed for: r5v47 */
    /* JADX WARN: Type inference failed for: r5v61 */
    /* JADX WARN: Type inference failed for: r5v62 */
    public int sendMessage(ArrayList<MessageObject> r67, long r68, boolean r70, boolean r71, boolean r72, int r73, int r74, MessageObject r75, int r76, long r77, long r79, MessageSuggestionParams r81) {
        long r3 = r68;
        if (r67 != null) goto L5;
        return 0;
    L5:
        if (r67.isEmpty() == true) goto L544;
        long r5 = getUserConfig().getClientUserId();
        if (DialogObject.isEncryptedDialog(r3) == true) goto L493;
        TLRPC.Peer r12 = getMessagesController().getPeer(r3);
        long r8 = getMessagesController().getSendPaidMessagesStars(r3);
        if (r8 > 0) goto L13;
        r8 = DialogObject.getMessagesStarsPrice(getMessagesController().isUserContactBlocked(r3));
    L13:
        if (r8 == r77) goto L16;
        AlertsCreator.ensurePaidMessageConfirmation(this.currentAccount, r3, Math.max(1, r67.size()), new SendMessagesHelper$$ExternalSyntheticLambda13(this, r67, r3, r70, r71, r72, r73, r74, r75, r76, r79, r81));
        return 0;
    L16:
        ArrayList<MessageObject> r15 = r67;
        boolean r19 = false;
        boolean r21 = true;
        if (DialogObject.isUserDialog(r3) == true) goto L19;
        TLRPC.Chat r2 = getMessagesController().getChat(Long.valueOf(-r3));
        if (ChatObject.isChannel(r2) == false) goto L38;
        boolean r7 = r2.signatures;
        boolean r9 = r2.megagroup;
        boolean r10 = !r9;
        if (r9 == false) goto L32;
    L36:
        boolean r23 = r7;
    L37:
        long r78 = 0;
    L39:
        if (r2 == null) goto L41;
        long r24 = r78;
        String r710 = getMessagesController().getAdminRank(r2.id, r5);
    L42:
        boolean r82 = ChatObject.canSendStickers(r2);
        boolean r92 = ChatObject.canSendPhoto(r2);
        boolean r26 = ChatObject.canSendVideo(r2);
        boolean r27 = ChatObject.canSendDocument(r2);
        boolean r28 = ChatObject.canSendEmbed(r2);
        boolean r29 = ChatObject.canSendPolls(r2);
        boolean r30 = ChatObject.canSendRoundVideo(r2);
        boolean r31 = ChatObject.canSendVoice(r2);
        boolean r33 = ChatObject.canSendMusic(r2);
        boolean r32 = r29;
        boolean r292 = r28;
        boolean r282 = r27;
        boolean r272 = r26;
        boolean r262 = r23;
        boolean r232 = r82;
        String r93 = r710;
        long r711 = r24;
        boolean r242 = r92;
        boolean r25 = r10;
    L43:
        LongSparseArray r102 = new LongSparseArray();
        ArrayList<MessageObject> r34 = new ArrayList();
        ArrayList r35 = new ArrayList();
        ArrayList<Long> r36 = new ArrayList();
        ArrayList<Integer> r37 = new ArrayList();
        LongSparseArray r38 = new LongSparseArray();
        TLRPC.InputPeer r0 = getMessagesController().getInputPeer(r3);
        if (r3 != r5) goto L46;
        boolean r40 = true;
    L47:
        int r14 = 0;
        ArrayList r41 = r35;
        ArrayList<Long> r11 = r36;
        ArrayList<Integer> r1 = r37;
        LongSparseArray r13 = r38;
        TLRPC.InputPeer r372 = r0;
        int r362 = 0;
        ArrayList<MessageObject> r352 = r34;
        boolean r342 = false;
    L49:
        if (r14 >= r15.size()) goto L492;
        MessageObject r02 = r15.get(r14);
        if (r02.getId() > 0) goto L53;
    L54:
        ArrayList<Integer> r44 = r1;
        TLRPC.Chat r382 = r2;
        long r46 = r5;
        long r54 = r711;
        LongSparseArray r43 = r102;
        TLRPC.Peer r42 = r12;
        LongSparseArray r48 = r13;
        boolean r45 = r40;
        ArrayList r49 = r41;
        ArrayList<MessageObject> r402 = r352;
        TLRPC.InputPeer r412 = r372;
        String r373 = r93;
        ArrayList<Long> r353 = r11;
        if (r02.type != 0) goto L475;
        if (TextUtils.isEmpty(r02.messageText) == true) goto L475;
        TLRPC.MessageMedia r22 = r02.messageOwner.media;
        if (r22 == null) goto L484;
        TLRPC.WebPage r52 = r22.webpage;
    L485:
        String r210 = r02.messageText.toString();
        if (r52 == null) goto L488;
        boolean r6 = r21;
    L489:
        int r50 = r14;
        SendMessageParams r03 = SendMessageParams.of(r210, r68, null, r75, r52, r6, r02.messageOwner.entities, null, null, r72, r73, r74, null, false);
        r03.suggestionParams = r81;
        r03.monoForumPeer = r79;
        r03.quick_reply_shortcut = r02.getQuickReplyName();
        r03.quick_reply_shortcut_id = r02.getQuickReplyId();
        sendMessage(r03);
    L476:
        r11 = r353;
        r352 = r402;
        r1 = r44;
        r13 = r48;
    L491:
        r14 = r50 + 1;
        r15 = r67;
        r93 = r373;
        r2 = r382;
        r372 = r412;
        r12 = r42;
        r102 = r43;
        r40 = r45;
        r5 = r46;
        r41 = r49;
        r711 = r54;
        r19 = false;
        goto L49
    L488:
        r6 = false;
        goto L489
    L484:
        r52 = null;
    L475:
        r50 = r14;
        goto L476
    L53:
        if (r02.needDrawBluredPreview() == true) goto L54;
        if (r02.isSticker() == false) goto L58;
    L66:
        boolean r383 = r21;
    L65:
        int r422 = r14;
        if (r232 == true) goto L79;
        if (r383 == false) goto L79;
        if (r362 == 0) goto L71;
    L77:
        r44 = r1;
        r382 = r2;
        r46 = r5;
        r54 = r711;
        r43 = r102;
        r48 = r13;
        r45 = r40;
        r49 = r41;
        r50 = r422;
        r42 = r12;
        r402 = r352;
        r412 = r372;
        r373 = r93;
        r353 = r11;
        goto L476
    L71:
        if (ChatObject.isActionBannedByDefault(r2, 8) == false) goto L73;
        ?? r142 = 4;
    L74:
        r382 = r2;
        r46 = r5;
        r54 = r711;
        r43 = r102;
        r362 = r142;
    L75:
        r45 = r40;
        r49 = r41;
        r50 = r422;
    L76:
        r42 = r12;
        r412 = r372;
        r373 = r93;
        goto L491
    L73:
        r142 = r21;
    L79:
        if (r242 == true) goto L91;
        if ((r02.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L91;
        if (r02.isVideo() == true) goto L91;
        if (r383 == true) goto L91;
        if (r362 != 0) goto L77;
        if (ChatObject.isActionBannedByDefault(r2, 16) == false) goto L89;
        r142 = 10;
        goto L74
    L89:
        r142 = 12;
    L91:
        if (r33 == false) goto L93;
    L99:
        if (r272 == true) goto L112;
        if ((r02.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L112;
        if (r02.isVideo() == false) goto L112;
        if (r383 == true) goto L112;
        if (r362 != 0) goto L77;
        if (ChatObject.isActionBannedByDefault(r2, 17) == false) goto L109;
        int r04 = 9;
    L110:
        r362 = r04;
        r382 = r2;
        r46 = r5;
        r54 = r711;
        r43 = r102;
        goto L75
    L109:
        r04 = 11;
    L112:
        if (r32 == false) goto L114;
    L120:
        if (r32 == false) goto L122;
    L128:
        if (r31 == false) goto L130;
    L139:
        if (r30 == false) goto L141;
    L150:
        if (r282 == false) goto L152;
    L159:
        TLRPC.TL_message r143 = new TLRPC.TL_message();
        if (r70 == false) goto L162;
        ArrayList<Integer> r442 = r1;
        TLRPC.Chat r384 = r2;
        LongSparseArray r482 = r13;
    L227:
        if (r02.isWelcomeAnchored() == false) goto L235;
        TLRPC.MessageFwdHeader r16 = r143.fwd_from;
        if (r16 == null) goto L235;
        if (r16.from_id == null) goto L235;
        r16.from_id = (TLRPC.Peer) TLObject.deepCopy(r02.messageOwner.peer_id, new MessagePreviewParams$$ExternalSyntheticLambda0());
        long r17 = DialogObject.getPeerDialogId(r02.messageOwner.from_id);
        if (r17 <= 0) goto L235;
        r143.via_bot_id = r17;
    L235:
        HashMap<String, String> r18 = new HashMap();
        r143.params = r18;
        r18.put("fwd_id", _UrlKt.FRAGMENT_ENCODE_SET + r02.getId());
        HashMap<String, String> r110 = r143.params;
        StringBuilder r211 = new StringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        long r462 = r5;
        r211.append(r02.getDialogId());
        r110.put("fwd_peer", r211.toString());
        if (r02.messageOwner.restriction_reason.isEmpty() == true) goto L238;
        r143.restriction_reason = r02.messageOwner.restriction_reason;
        r143.flags |= 4194304;
    L238:
        if (r292 == false) goto L240;
    L242:
        r143.media = r02.messageOwner.media;
    L243:
        TLRPC.Message r111 = r02.messageOwner;
        r143.invert_media = r111.invert_media;
        if (r143.media == null) goto L246;
        r143.flags |= 512;
    L246:
        long r112 = r111.via_bot_id;
        if (r112 == 0) goto L250;
        r143.via_bot_id = r112;
        r143.flags |= 2048;
    L250:
        if (r711 == 0) goto L252;
        TLRPC.TL_messageReplies r113 = new TLRPC.TL_messageReplies();
        r143.replies = r113;
        boolean r212 = r21;
        r113.comments = r212;
        r113.channel_id = r711;
        r113.flags |= r212 ? 1 : 0;
        r143.flags |= 8388608;
    L252:
        if (r71 == true) goto L254;
    L255:
        r143.message = r02.messageOwner.message;
    L256:
        TL_iv.RichMessage r114 = r02.messageOwner.rich_message;
        if (r114 == null) goto L260;
        r143.flags2 |= 8192;
        r143.rich_message = r114;
    L260:
        if (r143.message != null) goto L262;
        r143.message = _UrlKt.FRAGMENT_ENCODE_SET;
    L262:
        r143.fwd_msg_id = r02.getId();
        TLRPC.Message r115 = r02.messageOwner;
        r143.attachPath = r115.attachPath;
        r143.entities = r115.entities;
        TLRPC.ReplyMarkup r116 = r115.reply_markup;
        if ((r116 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L300;
        TLRPC.TL_replyInlineMarkup r117 = (TLRPC.TL_replyInlineMarkup) r116;
        TLRPC.TL_replyInlineMarkup r213 = new TLRPC.TL_replyInlineMarkup();
        r143.reply_markup = r213;
        int r4 = r117.rows.size();
        boolean r53 = r19;
        boolean r62 = r53;
        ?? r55 = r53;
    L265:
        if (r55 >= r4) goto L295;
        TL_keyboard.KeyboardInlineButtonRow r132 = r117.rows.get(r55 == true ? 1 : 0);
        int r152 = r132.buttons.size();
        TLRPC.TL_replyInlineMarkup r432 = r117;
        ?? r118 = r19;
        TL_keyboard.TL_keyboardInlineButtonRow r492 = null;
        int r56 = r55;
    L267:
        int r502 = r4;
        if (r118 >= r152) goto L291;
        TL_keyboard.KeyboardInlineButton r47 = r132.buttons.get(r118);
        int r51 = r118;
        TL_keyboard.TL_inlineButtonTypeUrlAuth r119 = (TL_keyboard.TL_inlineButtonTypeUrlAuth) TLKeyboardHelper.getType(r47, TL_keyboard.TL_inlineButtonTypeUrlAuth.class);
        int r522 = r56;
        if (r119 == null) goto L272;
    L280:
        if (r119 == null) goto L286;
        TL_keyboard.TL_keyboardInlineButton r57 = new TL_keyboard.TL_keyboardInlineButton();
        boolean r532 = r62;
        TL_keyboard.TL_inlineButtonTypeUrlAuth r63 = new TL_keyboard.TL_inlineButtonTypeUrlAuth();
        r57.type = r63;
        long r542 = r711;
        r63.flags = r119.flags;
        r57.style = r47.style;
        String r712 = r119.fwd_text;
        if (r712 == null) goto L284;
        r63.fwd_text = r712;
        r57.text = r712;
    L285:
        r63.url = r119.url;
        r63.button_id = r119.button_id;
        r47 = r57;
    L287:
        if (r492 != null) goto L289;
        TL_keyboard.TL_keyboardInlineButtonRow r120 = new TL_keyboard.TL_keyboardInlineButtonRow();
        r213.rows.add(r120);
    L290:
        r120.buttons.add(r47);
        r492 = r120;
        r118 = r51 + 1;
        r4 = r502;
        r56 = r522;
        r62 = r532;
        r711 = r542;
        goto L267
    L289:
        r120 = r492;
        goto L290
    L284:
        r57.text = r47.text;
        goto L285
    L286:
        r532 = r62;
        r542 = r711;
        goto L287
    L272:
        if (TLKeyboardHelper.isType(r47, TL_keyboard.TL_inlineButtonTypeUrl.class) == true) goto L280;
        if (TLKeyboardHelper.isType(r47, TL_keyboard.TL_inlineButtonTypeSwitchInline.class) == true) goto L280;
        if (TLKeyboardHelper.isType(r47, TL_keyboard.TL_inlineButtonTypeBuy.class) == true) goto L280;
        r62 = true;
    L279:
        r54 = r711;
        if (r62 == true) goto L296;
        r117 = r432;
        r4 = r502;
        r711 = r54;
        r55 = r522 + 1;
    L296:
        if (r62 == true) goto L299;
        r143.flags |= 64;
    L302:
        if (r143.entities.isEmpty() == true) goto L305;
        r143.flags |= 128;
    L305:
        if (r143.attachPath != null) goto L307;
        r143.attachPath = _UrlKt.FRAGMENT_ENCODE_SET;
    L307:
        int r121 = getUserConfig().getNewMessageId();
        r143.id = r121;
        r143.local_id = r121;
        r143.out = true;
        long r39 = r02.messageOwner.grouped_id;
        if (r39 == 0) goto L314;
        Long r122 = (Long) r102.get(r39);
        if (r122 != null) goto L312;
        r122 = Long.valueOf(Utilities.random.nextLong());
        r102.put(r02.messageOwner.grouped_id, r122);
    L312:
        r143.grouped_id = r122.longValue();
        r143.flags |= 131072;
    L314:
        if (r12.channel_id == 0) goto L321;
        if (r25 == false) goto L321;
        if (r262 == false) goto L319;
        TLRPC.TL_peerUser r123 = new TLRPC.TL_peerUser();
        r143.from_id = r123;
        long r310 = r462;
        r123.user_id = r310;
    L320:
        r143.post = true;
        long r64 = r68;
        r46 = r310;
        TLRPC.Chat r311 = r384;
    L328:
        if (r143.random_id != 0) goto L330;
        r143.random_id = getNextRandomId();
    L330:
        r11.add(Long.valueOf(r143.random_id));
        LongSparseArray r83 = r482;
        r83.put(r143.random_id, r143);
        if (r02.isEphemeral() == false) goto L333;
        ArrayList<Integer> r214 = r442;
        r214.add(Integer.valueOf(r02.getEphemeralId()));
        boolean r133 = true;
    L334:
        if (r73 == 0) goto L336;
        int r124 = r73;
    L337:
        r143.date = r124;
        TLRPC.InputPeer r125 = r372;
        boolean r410 = r125 instanceof TLRPC.TL_inputPeerChannel;
        if (r410 == false) goto L342;
        if (r25 == false) goto L342;
        if (r73 != 0) goto L347;
        r143.views = 1;
        r143.flags |= 1024;
    L347:
        r143.dialog_id = r64;
        r143.peer_id = r12;
        if (MessageObject.isVoiceMessage(r143) == false) goto L350;
    L351:
        if (r410 == true) goto L353;
    L355:
        r143.media_unread = true;
    L356:
        if (r75 != null) goto L369;
        if (r81 != null) goto L369;
        TLRPC.Message r411 = r02.messageOwner;
        TLRPC.MessageReplyHeader r58 = r411.reply_to;
        if (r58 == null) goto L369;
        TLRPC.Peer r59 = r58.reply_to_peer_id;
        if (r59 != null) goto L363;
    L364:
        TLRPC.MessageReplyHeader r413 = r02.messageOwner.reply_to;
        if ((r413.flags & 16) == 0) goto L369;
        if (r214.contains(Integer.valueOf(r413.reply_to_msg_id)) == false) goto L369;
    L368:
        r143.flags |= 8;
        r143.reply_to = r02.messageOwner.reply_to;
        goto L369
    L363:
        if (MessageObject.peersEqual(r59, r411.peer_id) == false) goto L368;
    L369:
        int r414 = (r77 > 0 ? 1 : (r77 == 0 ? 0 : -1));
        if (r414 <= 0) goto L373;
        r143.flags2 |= 64;
        r382 = r311;
        int r510 = r414;
        r143.paid_message_stars = r77;
    L375:
        if (r79 == 0) goto L377;
        int r343 = r510;
        r143.saved_peer_id = getMessagesController().getPeer(r79);
        r143.flags |= 268435456;
    L378:
        if (r81 == null) goto L380;
        r143.suggested_post = r81.toTl();
    L380:
        r48 = r83;
        r373 = r93;
        r43 = r102;
        MessageObject r511 = new MessageObject(this.currentAccount, r143, true, true);
        if (r73 == 0) goto L383;
        boolean r94 = true;
    L384:
        r511.scheduled = r94;
        r511.messageOwner.send_state = 1;
        r511.wasJustSent = true;
        ArrayList<MessageObject> r95 = r352;
        r95.add(r511);
        ArrayList r103 = r41;
        r103.add(r143);
        TLRPC.Peer r354 = r12;
        StarsController.getInstance(this.currentAccount).beforeSendingMessage(r511);
        if (r02.replyMessageObject == null) goto L393;
        ?? r126 = r19;
    L388:
        if (r126 >= r67.size()) goto L393;
        ArrayList<MessageObject> r153 = r67;
        int r443 = r126;
        if (r153.get(r126).getId() == r02.replyMessageObject.getId()) goto L391;
        r126 = r443 + 1;
        goto L388
    L391:
        TLRPC.Message r312 = r511.messageOwner;
        MessageObject r415 = r02.replyMessageObject;
        r312.replyMessage = r415.messageOwner;
        r511.replyMessageObject = r415;
    L394:
        if (r73 == 0) goto L396;
        boolean r313 = true;
    L397:
        putToSendingMessages(r143, r313);
        if (BuildVars.LOGS_ENABLED == false) goto L400;
        FileLog.d("forward message user_id = " + r125.user_id + " chat_id = " + r125.chat_id + " channel_id = " + r125.channel_id + " access_hash = " + r125.access_hash);
    L400:
        if (r75 == null) goto L408;
        if (r81 != null) goto L408;
        TLRPC.TL_messageReplyHeader r314 = new TLRPC.TL_messageReplyHeader();
        r143.reply_to = r314;
        r314.flags |= 16;
        r314.reply_to_msg_id = r75.getId();
        if (r75.isTopicMainMessage == false) goto L408;
        TLRPC.MessageReplyHeader r315 = r143.reply_to;
        r315.forum_topic = true;
        r315.flags |= 8;
    L408:
        if (r103.size() == 100) goto L417;
        r14 = r422;
        if (r14 != (r153.size() - 1)) goto L412;
    L418:
        MessagesStorage r562 = getMessagesStorage();
        ArrayList<TLRPC.Message> r316 = new ArrayList(r103);
        if (r73 == 0) goto L421;
        int r622 = 1;
    L422:
        r562.putMessages(r316, false, true, false, 0, r622, 0);
        MessagesController r317 = getMessagesController();
        if (r73 == 0) goto L425;
        int r512 = 1;
    L426:
        r317.updateInterfaceWithMessages(r64, r95, r512);
        int r127 = r19;
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[r127]);
        getUserConfig().saveConfig(r127);
        TLRPC.TL_messages_forwardMessages r318 = new TLRPC.TL_messages_forwardMessages();
        r318.to_peer = r125;
        if (r72 == true) goto L429;
    L432:
        boolean r416 = true;
    L433:
        r318.silent = r416;
        if (r75 == null) goto L437;
        r318.top_msg_id = r75.getId();
        r318.flags |= 512;
    L437:
        if (r73 == 0) goto L443;
        r318.schedule_date = r73;
        int r417 = r318.flags;
        r318.flags = r417 | 1024;
        if (r74 == 0) goto L443;
        r318.schedule_repeat_period = r74;
        r318.flags = r417 | 16778240;
    L443:
        if ((r02.messageOwner.peer_id instanceof TLRPC.TL_peerChannel) == false) goto L447;
        r412 = r125;
        TLRPC.Chat r128 = getMessagesController().getChat(Long.valueOf(r02.messageOwner.peer_id.channel_id));
        TLRPC.TL_inputPeerChannel r418 = new TLRPC.TL_inputPeerChannel();
        r318.from_peer = r418;
        r418.channel_id = r02.messageOwner.peer_id.channel_id;
        if (r128 == null) goto L448;
        r418.access_hash = r128.access_hash;
    L448:
        r318.random_id = r11;
        r318.id = r214;
        r318.drop_author = r70;
        r318.drop_media_captions = r71;
        if (r153.size() == 1) goto L451;
    L453:
        boolean r419 = false;
    L454:
        r318.with_my_score = r419;
        r318.from_ephemeral = r133;
        if (r76 < 0) goto L457;
        r318.flags |= 1048576;
        r318.video_timestamp = r76;
    L457:
        if (r343 <= 0) goto L459;
        r318.flags |= TLObject.FLAG_21;
        MessageObject r423 = r02;
        r318.allow_paid_stars = r318.id.size() * r77;
    L460:
        if (r81 == null) goto L464;
        r318.suggested_post = r81.toTl();
        if (r75 == null) goto L464;
        TLRPC.TL_inputReplyToMessage r05 = new TLRPC.TL_inputReplyToMessage();
        r318.reply_to = r05;
        r05.reply_to_msg_id = r75.getId();
    L464:
        applyMonoForumPeerId(r318, r79);
        ArrayList<MessageObject> r104 = new ArrayList(r95);
        if (r73 != 2147483646) goto L467;
        boolean r65 = true;
    L468:
        boolean r713 = r40;
        r402 = r95;
        r44 = r214;
        r21 = true;
        r353 = r11;
        SendMessagesHelper$$ExternalSyntheticLambda14 r06 = new SendMessagesHelper$$ExternalSyntheticLambda14(this, r318, r68, r73, r65, r713, r48, r103, r104, r423, r354);
        SendMessagesHelper r07 = this;
        r45 = r713;
        r49 = r103;
        r42 = r354;
        SendMessagesHelper$$ExternalSyntheticLambda15 r319 = new SendMessagesHelper$$ExternalSyntheticLambda15(r07, r318, r104, r06);
        if (StarsController.getInstance(r07.currentAccount).beforeSendingFinalRequest(r318, r104, r319) == false) goto L472;
        r319.run();
    L472:
        if (r14 == (r153.size() - 1)) goto L474;
        ArrayList<MessageObject> r129 = new ArrayList();
        ArrayList r215 = new ArrayList();
        ArrayList<Long> r320 = new ArrayList();
        r352 = r129;
        r49 = r215;
        r11 = r320;
        r1 = new ArrayList();
        r342 = r133;
        r50 = r14;
        r13 = new LongSparseArray();
    L474:
        r342 = r133;
        goto L475
    L467:
        r65 = false;
        goto L468
    L459:
        r423 = r02;
        goto L460
    L451:
        if (r153.get(0).messageOwner.with_my_score == false) goto L453;
        r419 = true;
        goto L454
    L447:
        r412 = r125;
        r318.from_peer = new TLRPC.TL_inputPeerEmpty();
        goto L448
    L429:
        if (MessagesController.getNotificationsSettings(this.currentAccount).getBoolean(NotificationsSettingsFacade.PROPERTY_SILENT + r64, false) == true) goto L432;
        r416 = false;
        goto L433
    L425:
        r512 = r19;
        goto L426
    L421:
        r622 = r19;
        goto L422
    L412:
        if (r14 != (r153.size() - 1)) goto L414;
    L416:
        r412 = r125;
        r44 = r214;
        r07 = this;
        r49 = r103;
        r42 = r354;
        r45 = r40;
        r21 = true;
        r402 = r95;
        r353 = r11;
        goto L474
    L414:
        if (r153.get(r14 + 1).getDialogId() == r02.getDialogId()) goto L416;
    L417:
        r14 = r422;
        goto L418
    L396:
        r313 = r19;
    L393:
        r153 = r67;
        goto L394
    L383:
        r94 = r19;
        goto L384
    L377:
        r343 = r510;
        goto L378
    L373:
        r382 = r311;
        r510 = r414;
        goto L375
    L353:
        if (r02.getChannelId() == 0) goto L355;
        r143.media_unread = r02.isContentUnread();
        goto L356
    L350:
        if (MessageObject.isRoundVideoMessage(r143) == false) goto L356;
    L342:
        TLRPC.Message r513 = r02.messageOwner;
        if ((r513.flags & 1024) == 0) goto L346;
        if (r73 != 0) goto L346;
        r143.views = r513.views;
        r143.flags |= 1024;
    L346:
        r143.unread = true;
        goto L347
    L336:
        r124 = getConnectionsManager().getCurrentTime();
        goto L337
    L333:
        r214 = r442;
        r214.add(Integer.valueOf(r143.fwd_msg_id));
        r133 = r342;
        goto L334
    L319:
        r310 = r462;
        r143.from_id = r12;
    L321:
        r64 = r68;
        r46 = r462;
        r311 = r384;
        long r420 = ChatObject.getSendAsPeerId(r311, getMessagesController().getChatFull(-r64), true);
        if (r420 != r46) goto L324;
        TLRPC.TL_peerUser r130 = new TLRPC.TL_peerUser();
        r143.from_id = r130;
        r130.user_id = r46;
        r143.flags |= 256;
        goto L328
    L324:
        r143.from_id = getMessagesController().getPeer(r420);
        if (r93 == null) goto L328;
        r143.post_author = r93;
        r143.flags |= 65536;
        goto L328
    L299:
        r02.messageOwner.reply_markup = null;
        r143.flags &= -65;
        goto L302
    L291:
        r522 = r56;
        goto L279
    L295:
        r54 = r711;
        goto L296
    L300:
        r54 = r711;
        goto L302
    L254:
        if (r143.media != null) goto L256;
    L240:
        if ((r02.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) == false) goto L242;
        r143.media = new TLRPC.TL_messageMediaEmpty();
        goto L243
    L162:
        if (r02.getDialogId() == r5) goto L164;
    L168:
        r442 = r1;
        r384 = r2;
    L169:
        boolean r131 = r19;
    L171:
        if (r02.isForwarded() == false) goto L194;
        TLRPC.TL_messageFwdHeader r134 = new TLRPC.TL_messageFwdHeader();
        r143.fwd_from = r134;
        TLRPC.MessageFwdHeader r216 = r02.messageOwner.fwd_from;
        if ((r216.flags & 1) == 0) goto L176;
        r134.flags |= 1;
        r134.from_id = r216.from_id;
    L176:
        if ((r216.flags & 32) == 0) goto L179;
        r134.flags |= 32;
        r134.from_name = r216.from_name;
    L179:
        if ((r216.flags & 4) == 0) goto L182;
        r134.flags |= 4;
        r134.channel_post = r216.channel_post;
    L182:
        if ((r216.flags & 8) == 0) goto L184;
        r134.flags |= 8;
        r134.post_author = r216.post_author;
    L184:
        if (r3 == r5) goto L187;
        if (r25 == true) goto L187;
    L191:
        r143.fwd_from.date = r02.messageOwner.fwd_from.date;
        r143.flags = 4;
    L192:
        r482 = r13;
    L219:
        if (r3 != r5) goto L227;
        TLRPC.MessageFwdHeader r135 = r143.fwd_from;
        if (r135 == null) goto L227;
        r135.flags |= 16;
        r135.saved_from_msg_id = r02.getId();
        TLRPC.MessageFwdHeader r136 = r143.fwd_from;
        TLRPC.Peer r217 = r02.messageOwner.peer_id;
        r136.saved_from_peer = r217;
        if (r217.user_id != r5) goto L227;
        r217.user_id = r02.getDialogId();
    L187:
        if ((r216.flags & 16) == 0) goto L191;
        if (UserObject.isReplyUser(r02.getDialogId()) == true) goto L191;
        TLRPC.MessageFwdHeader r137 = r143.fwd_from;
        r137.flags |= 16;
        TLRPC.MessageFwdHeader r218 = r02.messageOwner.fwd_from;
        r137.saved_from_peer = r218.saved_from_peer;
        r137.saved_from_msg_id = r218.saved_from_msg_id;
        goto L191
    L194:
        if (r131 == true) goto L192;
        long r463 = r02.getFromChatId();
        TLRPC.TL_messageFwdHeader r138 = new TLRPC.TL_messageFwdHeader();
        r143.fwd_from = r138;
        r138.channel_post = r02.getId();
        r143.fwd_from.flags |= 4;
        boolean r139 = r02.isFromUser();
        TLRPC.MessageFwdHeader r219 = r143.fwd_from;
        if (r139 == false) goto L198;
        r219.from_id = r02.messageOwner.from_id;
        r219.flags |= 1;
        r482 = r13;
    L207:
        if (r02.messageOwner.post_author == null) goto L210;
    L218:
        r143.fwd_from.date = r02.messageOwner.date;
        r143.flags = 4;
        goto L219
    L210:
        if (r02.isOutOwner() == true) goto L218;
        if (r463 <= 0) goto L218;
        if (r02.messageOwner.post == false) goto L218;
        TLRPC.User r140 = getMessagesController().getUser(Long.valueOf(r463));
        if (r140 == null) goto L218;
        r143.fwd_from.post_author = ContactsController.formatName(r140.first_name, r140.last_name);
        r143.fwd_from.flags |= 8;
        goto L218
    L198:
        r219.from_id = new TLRPC.TL_peerChannel();
        TLRPC.MessageFwdHeader r141 = r143.fwd_from;
        TLRPC.Peer r220 = r141.from_id;
        TLRPC.Message r154 = r02.messageOwner;
        r482 = r13;
        TLRPC.Peer r1310 = r154.peer_id;
        r220.channel_id = r1310.channel_id;
        r141.flags |= 1;
        if (r154.post == false) goto L207;
        if (r463 <= 0) goto L207;
        TLRPC.Peer r221 = r154.from_id;
        if (r221 == null) goto L205;
        r1310 = r221;
    L205:
        r141.from_id = r1310;
        goto L207
    L164:
        if (r02.isFromUser() == false) goto L168;
        r442 = r1;
        r384 = r2;
        if (r02.messageOwner.from_id.user_id != r5) goto L169;
        r131 = r21;
        goto L171
    L152:
        if ((r02.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) == false) goto L159;
        if (r383 == true) goto L159;
        if (r362 != 0) goto L77;
        if (ChatObject.isActionBannedByDefault(r2, 19) == false) goto L158;
        r142 = 17;
        goto L74
    L158:
        r142 = 18;
        goto L74
    L141:
        if (MessageObject.isRoundVideoMessage(r02.messageOwner) == false) goto L150;
        if (r2 == null) goto L148;
        if (r362 != 0) goto L77;
        if (ChatObject.isActionBannedByDefault(r2, 21) == false) goto L147;
        r142 = 15;
        goto L74
    L147:
        r142 = 16;
        goto L74
    L148:
        if (r362 != 0) goto L77;
        r382 = r2;
        r46 = r5;
        r54 = r711;
        r43 = r102;
        r45 = r40;
        r49 = r41;
        r50 = r422;
        r362 = 8;
        goto L76
    L130:
        if (MessageObject.isVoiceMessage(r02.messageOwner) == false) goto L139;
        if (r2 == null) goto L137;
        if (r362 != 0) goto L77;
        if (ChatObject.isActionBannedByDefault(r2, 20) == false) goto L136;
        r04 = 13;
        goto L110
    L136:
        r04 = 14;
        goto L110
    L137:
        if (r362 != 0) goto L77;
        r382 = r2;
        r46 = r5;
        r54 = r711;
        r43 = r102;
        r362 = 7;
        goto L75
    L122:
        if ((r02.messageOwner.media instanceof TLRPC.TL_messageMediaToDo) == false) goto L128;
        if (r362 != 0) goto L77;
        if (ChatObject.isActionBannedByDefault(r2, 10) == false) goto L127;
        r142 = 21;
        goto L74
    L127:
        r142 = 22;
        goto L74
    L114:
        if ((r02.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) == false) goto L120;
        if (r362 != 0) goto L77;
        if (ChatObject.isActionBannedByDefault(r2, 10) == false) goto L119;
        r04 = 6;
        goto L110
    L119:
        r04 = 3;
        goto L110
    L93:
        if (r02.isMusic() == false) goto L99;
        if (r362 != 0) goto L77;
        if (ChatObject.isActionBannedByDefault(r2, 18) == false) goto L98;
        r142 = 19;
        goto L74
    L98:
        r142 = 20;
        goto L74
    L58:
        if (r02.isAnimatedSticker() == true) goto L66;
        if (r02.isGif() == true) goto L66;
        if (r02.isGame() == true) goto L66;
        r383 = r19;
        goto L65
    L492:
        return r362;
    L46:
        r40 = false;
        goto L47
    L41:
        r24 = r78;
        r710 = null;
        goto L42
    L32:
        if (r2.has_link == false) goto L36;
        r23 = r7;
        TLRPC.ChatFull r714 = getMessagesController().getChatFull(r2.id);
        if (r714 == null) goto L37;
        r78 = r714.linked_chat_id;
        goto L39
    L38:
        r78 = 0;
        r10 = false;
        r23 = false;
        goto L39
    L19:
        if (getMessagesController().getUser(Long.valueOf(r3)) != null) goto L21;
        return 0;
    L21:
        TLRPC.UserFull r222 = getMessagesController().getUserFull(r3);
        if (r222 == null) goto L25;
        boolean r223 = !r222.voice_messages_forbidden;
    L26:
        r31 = r223;
        r30 = r223;
        r711 = 0;
        r25 = false;
        r262 = false;
        r232 = true;
        r242 = true;
        r272 = true;
        r282 = true;
        r292 = true;
        r32 = true;
        r33 = true;
        r2 = null;
        r93 = null;
        goto L43
    L25:
        r223 = true;
        goto L26
    L493:
        MessageSuggestionParams r84 = r81;
        SendMessagesHelper r155 = this;
        long r66 = r79;
        long r08 = r155.getMessagesController().getEncryptedChat(Integer.valueOf((int) r3)).user_id;
        if (DialogObject.isUserDialog(r08) == true) goto L496;
    L500:
        boolean r09 = true;
    L501:
        int r96 = 0;
        int r1210 = 0;
    L503:
        if (r1210 >= r67.size()) goto L517;
        MessageObject r144 = r67.get(r1210);
        if (r09 == false) goto L507;
    L510:
        if (r09 == true) goto L515;
        if (MessageObject.isRoundVideoMessage(r144.messageOwner) == false) goto L515;
        if (r96 != 0) goto L515;
        r96 = 8;
    L515:
        r1210 = r1210 + 1;
        goto L503
    L507:
        if (MessageObject.isVoiceMessage(r144.messageOwner) == false) goto L510;
        if (r96 != 0) goto L515;
        r96 = 7;
        goto L515
    L517:
        if (r96 != 0) goto L522;
        int r1110 = 0;
    L520:
        if (r1110 >= r67.size()) goto L522;
        r155.processForwardFromMyName(r67.get(r1110), r3, r77, r66, r84);
        r1110 = r1110 + 1;
        r155 = this;
        r3 = r68;
        r66 = r79;
        r84 = r81;
    L522:
        return r96;
    L496:
        if (r155.getMessagesController().getUser(Long.valueOf(r08)) == null) goto L500;
        TLRPC.UserFull r010 = r155.getMessagesController().getUserFull(r08);
        if (r010 == null) goto L500;
        r09 = !r010.voice_messages_forbidden;
        goto L501
    L544:
        return 0;
    }

    public int sendMessage(ArrayList<MessageObject> r13, long r14, boolean r16, boolean r17, boolean r18, int r19, long r20) {
        return sendMessage(r13, r14, r16, r17, r18, r19, null, -1, r20);
    }

    public int sendMessage(ArrayList<MessageObject> r18, long r19, boolean r21, boolean r22, boolean r23, int r24, MessageObject r25, int r26, long r27) {
        return sendMessage(r18, r19, r21, r22, r23, r24, 0, r25, r26, r27, 0, null);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    /* JADX WARN: Not initialized variable reg: 72, insn: 0x2451: MOVE (r3 I:??[OBJECT, ARRAY]) = (r72 I:??[OBJECT, ARRAY]), block:B:1823:0x2451 */
    public void sendMessage(org.telegram.messenger.SendMessagesHelper.SendMessageParams r100) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0000: MOVE (r1v0 ?? I:??[OBJECT, ARRAY]) = (r99v0 'this' ?? I:??[OBJECT, ARRAY] A[IMMUTABLE_TYPE, THIS]) A[DECLARE_VAR] in method: org.telegram.messenger.SendMessagesHelper.sendMessage(org.telegram.messenger.SendMessagesHelper$SendMessageParams):void, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            Method dump skipped, instructions count: 11597
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.messenger.SendMessagesHelper.sendMessage(org.telegram.messenger.SendMessagesHelper$SendMessageParams):void");
    }

    public void sendNotificationCallback(long r7, int r9, byte[] r10) {
        AndroidUtilities.runOnUIThread(new SendMessagesHelper$$ExternalSyntheticLambda51(this, r7, r9, r10));
    }

    public void sendReaction(MessageObject r5, ArrayList<ReactionsLayoutInBubble.VisibleReaction> r6, ReactionsLayoutInBubble.VisibleReaction r7, boolean r8, boolean r9, BaseFragment r10, Runnable r11) {
        if (r5 == null) goto L36;
        if (r10 == null) goto L37;
        TLRPC.TL_messages_sendReaction r102 = new TLRPC.TL_messages_sendReaction();
        TLRPC.Message r0 = r5.messageOwner;
        if (r0.isThreadMessage == true) goto L8;
    L10:
        r102.peer = getMessagesController().getInputPeer(r5.getDialogId());
        r102.msg_id = r5.getId();
    L11:
        r102.add_to_recent = r9;
        int r52 = 0;
        if (r9 == false) goto L16;
        if (r7 == null) goto L16;
        MediaDataController.getInstance(this.currentAccount).recentReactions.add(0, ReactionsUtils.toTLReaction(r7));
    L16:
        if (r6 != null) goto L18;
    L28:
        if (r8 == false) goto L30;
        r102.flags |= 2;
        r102.big = true;
    L30:
        getConnectionsManager().sendRequest(r102, new SendMessagesHelper$$ExternalSyntheticLambda126(this, r11));
        return;
    L18:
        if (r6.isEmpty() == true) goto L28;
    L20:
        if (r52 >= r6.size()) goto L28;
        ReactionsLayoutInBubble.VisibleReaction r92 = r6.get(r52);
        if (r92.documentId == 0) goto L25;
        TLRPC.TL_reactionCustomEmoji r02 = new TLRPC.TL_reactionCustomEmoji();
        r02.document_id = r92.documentId;
        r102.reaction.add(r02);
        r102.flags |= 1;
    L27:
        r52 = r52 + 1;
        goto L20
    L25:
        if (r92.emojicon == null) goto L27;
        TLRPC.TL_reactionEmoji r03 = new TLRPC.TL_reactionEmoji();
        r03.emoticon = r92.emojicon;
        r102.reaction.add(r03);
        r102.flags |= 1;
        goto L27
    L8:
        if (r0.fwd_from == null) goto L10;
        r102.peer = getMessagesController().getInputPeer(r5.getFromChatId());
        r102.msg_id = r5.messageOwner.fwd_from.saved_from_msg_id;
        goto L11
    L37:
        return;
    }

    public void sendScreenshotMessage(TLRPC.User r18, int r19, TLRPC.Message r20) {
        TLRPC.Message r3 = r20;
        if (r18 == null) goto L14;
        if (r19 != 0) goto L6;
        return;
    L6:
        if (r18.id == getUserConfig().getClientUserId()) goto L16;
        TLRPC.TL_messages_sendScreenshotNotification r4 = new TLRPC.TL_messages_sendScreenshotNotification();
        TLRPC.TL_inputPeerUser r5 = new TLRPC.TL_inputPeerUser();
        r4.peer = r5;
        r5.access_hash = r18.access_hash;
        r5.user_id = r18.id;
        if (r3 == null) goto L11;
        r4.reply_to = createReplyInput(r19);
        r4.random_id = r3.random_id;
    L12:
        r4.random_id = r3.random_id;
        MessageObject r2 = new MessageObject(this.currentAccount, r3, false, true);
        r2.messageOwner.send_state = 1;
        r2.wasJustSent = true;
        ArrayList<MessageObject> r1 = new ArrayList();
        r1.add(r2);
        getMessagesController().updateInterfaceWithMessages(r3.dialog_id, r1, 0);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        ArrayList<TLRPC.Message> r8 = new ArrayList();
        r8.add(r3);
        getMessagesStorage().putMessages(r8, false, true, false, 0, false, 0, 0);
        performSendMessageRequest(r4, r2, null, null, null, null, false);
        return;
    L11:
        r3 = new TLRPC.TL_messageService();
        r3.random_id = getNextRandomId();
        r3.dialog_id = r18.id;
        r3.unread = true;
        r3.out = true;
        int r7 = getUserConfig().getNewMessageId();
        r3.id = r7;
        r3.local_id = r7;
        TLRPC.TL_peerUser r72 = new TLRPC.TL_peerUser();
        r3.from_id = r72;
        r72.user_id = getUserConfig().getClientUserId();
        r3.flags |= GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED;
        TLRPC.TL_messageReplyHeader r73 = new TLRPC.TL_messageReplyHeader();
        r3.reply_to = r73;
        r73.flags |= 16;
        r73.reply_to_msg_id = r19;
        TLRPC.TL_peerUser r22 = new TLRPC.TL_peerUser();
        r3.peer_id = r22;
        r22.user_id = r18.id;
        r3.date = getConnectionsManager().getCurrentTime();
        r3.action = new TLRPC.TL_messageActionScreenshotTaken();
        getUserConfig().saveConfig(false);
        goto L12
    L16:
        return;
    }

    public void sendSticker(TLRPC.Document r25, String r26, long r27, CharSequence r29, VideoEditedInfo r30, MessageObject r31, MessageObject r32, TL_stories.StoryItem r33, ChatActivity.ReplyQuote r34, MessageObject.SendAnimationData r35, boolean r36, int r37, int r38, boolean r39, Object r40, SendMessageChatArguments r41, long r42, long r44, MessageSuggestionParams r46) {
        sendSticker(r25, r26, r27, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r44, r46, false);
    }

    public void sendSticker(TLRPC.Document r25, String r26, long r27, CharSequence r29, VideoEditedInfo r30, MessageObject r31, MessageObject r32, TL_stories.StoryItem r33, ChatActivity.ReplyQuote r34, MessageObject.SendAnimationData r35, boolean r36, int r37, int r38, boolean r39, Object r40, SendMessageChatArguments r41, long r42, long r44, MessageSuggestionParams r46, boolean r47) {
        if (r25 != null) goto L6;
        return;
    L6:
        if (DialogObject.isEncryptedDialog(r27) == true) goto L8;
        TLRPC.Document r2 = r25;
    L48:
        if (MessageObject.isGifDocument(r2) == false) goto L51;
        mediaSendQueue.postRunnable(new SendMessagesHelper$$ExternalSyntheticLambda23(this, r2, r30, r27, r31, r32, r36, r37, r38, r40, r35, r33, r34, r41, r42, r44, r46, r29, r47));
        return;
    L51:
        if (TextUtils.isEmpty(r26) == true) goto L54;
        HashMap r0 = new HashMap();
        r0.put("query", r26);
    L55:
        SendMessageParams r02 = SendMessageParams.of((TLRPC.TL_document) r2, null, null, r27, r31, r32, null, null, null, r0, r36, r37, r38, 0, r40, r35, r39);
        r02.replyToStoryItem = r33;
        r02.replyQuote = r34;
        r02.sendMessageChatArguments = r41;
        r02.payStars = r42;
        r02.monoForumPeer = r44;
        r02.suggestionParams = r46;
        r02.invert_media = r47;
        sendMessage(r02);
        return;
    L54:
        r0 = null;
        goto L55
    L8:
        if (getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r27))) == null) goto L64;
        r2 = new TLRPC.TL_document_layer82();
        r2.id = r25.id;
        r2.access_hash = r25.access_hash;
        r2.date = r25.date;
        r2.mime_type = r25.mime_type;
        byte[] r3 = r25.file_reference;
        r2.file_reference = r3;
        int r4 = 0;
        if (r3 != null) goto L13;
        r2.file_reference = new byte[0];
    L13:
        r2.size = r25.size;
        r2.dc_id = r25.dc_id;
        r2.attributes = new ArrayList();
    L15:
        if (r4 >= r25.attributes.size()) goto L22;
        TLRPC.DocumentAttribute r310 = r25.attributes.get(r4);
        if ((r310 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L19;
        TLRPC.TL_documentAttributeVideo_layer159 r5 = new TLRPC.TL_documentAttributeVideo_layer159();
        r5.flags = r310.flags;
        r5.round_message = r310.round_message;
        r5.supports_streaming = r310.supports_streaming;
        r5.duration = r310.duration;
        r5.w = r310.w;
        r5.h = r310.h;
        r2.attributes.add(r5);
    L20:
        r4 = r4 + 1;
        goto L15
    L19:
        r2.attributes.add(r310);
        goto L20
    L22:
        if (r2.mime_type != null) goto L24;
        r2.mime_type = _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        TLRPC.PhotoSize r03 = FileLoader.getClosestPhotoSizeWithSize(r25.thumbs, 10);
        if ((r03 instanceof TLRPC.TL_photoSize) == false) goto L27;
    L30:
        File r311 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r03, true);
        if ((r03 instanceof TLRPC.TL_photoStrippedSize) == false) goto L33;
    L57:
    L37:
        e = move-exception;
        FileLog.e(e);
    L44:
        if (r2.thumbs.isEmpty() == false) goto L48;
        TLRPC.TL_photoSizeEmpty r04 = new TLRPC.TL_photoSizeEmpty();
        r04.type = "s";
        r2.thumbs.add(r04);
        goto L48
    L35:
        if ((r03 instanceof TLRPC.TL_photoStrippedSize) == false) goto L39;
        TLRPC.PhotoSize r312 = new TLRPC.TL_photoStrippedSize();     // Catch: Exception -> L37
        byte[] r52 = r03.bytes;     // Catch: Exception -> L37
    L40:
        TLRPC.TL_fileLocation_layer82 r6 = new TLRPC.TL_fileLocation_layer82();     // Catch: Exception -> L37
        TLRPC.FileLocation r7 = r03.location;     // Catch: Exception -> L37
        r6.dc_id = r7.dc_id;     // Catch: Exception -> L37
        r6.volume_id = r7.volume_id;     // Catch: Exception -> L37
        r6.local_id = r7.local_id;     // Catch: Exception -> L37
        r6.secret = r7.secret;     // Catch: Exception -> L37
        r312.location = r6;     // Catch: Exception -> L37
        r312.size = r03.size;     // Catch: Exception -> L37
        r312.w = r03.w;     // Catch: Exception -> L37
        r312.h = r03.h;     // Catch: Exception -> L37
        r312.type = r03.type;     // Catch: Exception -> L37
        r312.bytes = r52;     // Catch: Exception -> L37
        r2.thumbs.add(r312);     // Catch: Exception -> L37
        r2.flags |= 1;
        goto L44
    L39:
        TLRPC.TL_photoCachedSize r53 = new TLRPC.TL_photoCachedSize();     // Catch: Exception -> L37
        r311.length();     // Catch: Exception -> L37
        byte[] r62 = new byte[(int) r311.length()];     // Catch: Exception -> L37
        new RandomAccessFile(r311, "r").readFully(r62);     // Catch: Exception -> L37
        r312 = r53;
        r52 = r62;
        goto L40
    L33:
        if (r311.exists() == false) goto L44;
    L27:
        if ((r03 instanceof TLRPC.TL_photoSizeProgressive) == true) goto L30;
        if ((r03 instanceof TLRPC.TL_photoStrippedSize) == false) goto L44;
    }

    public void sendSticker(TLRPC.Document r24, String r25, long r26, MessageObject r28, MessageObject r29, TL_stories.StoryItem r30, ChatActivity.ReplyQuote r31, MessageObject.SendAnimationData r32, boolean r33, int r34, int r35, boolean r36, Object r37, SendMessageChatArguments r38, long r39, long r41, MessageSuggestionParams r43) {
        sendSticker(r24, r25, r26, null, null, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r41, r43);
    }

    public int sendVote(MessageObject r2, ArrayList<TLRPC.PollAnswer> r3, Runnable r4) {
        return sendVote(r2, r3, r4, true);
    }

    public int sendVote(MessageObject r11, ArrayList<TLRPC.PollAnswer> r12, Runnable r13, boolean r14) {
        if (r11 != null) goto L5;
        return 0;
    L5:
        String r8 = "poll_" + r11.getPollId();
        if (this.waitingForCallback.containsKey(r8) == false) goto L8;
        return 0;
    L8:
        TLRPC.TL_messages_sendVote r1 = new TLRPC.TL_messages_sendVote();
        r1.msg_id = r11.getId();
        r1.peer = getMessagesController().getInputPeer(r11.getDialogId());
        if (r12 == null) goto L18;
        byte[] r2 = new byte[r12.size()];
        int r3 = 0;
    L12:
        if (r3 >= r12.size()) goto L19;
        TLRPC.PollAnswer r4 = r12.get(r3);
        if (r4 == null) goto L17;
        r1.options.add(r4.option);
        r2[r3] = r4.option[0];
    L17:
        r3 = r3 + 1;
    L19:
        this.waitingForVote.put(r8, r2);
        return getConnectionsManager().sendRequest(r1, new SendMessagesHelper$$ExternalSyntheticLambda73(this, r11, r14, r8, r13));
    L18:
        r2 = new byte[0];
        goto L19
    }

    public int toggleTodo(long r12, MessageObject r14, TLRPC.TodoItem r15, boolean r16, Runnable r17) {
        if (r14 != null) goto L5;
        return 0;
    L5:
        int r8 = Objects.hash(new Object[]{Long.valueOf(r14.getDialogId()), Integer.valueOf(r14.getId()), Integer.valueOf(r15.id)});
        this.waitingForTodoUpdate.put(Integer.valueOf(r8), Boolean.valueOf(r16));
        TLRPC.TL_messages_toggleTodoCompleted r0 = new TLRPC.TL_messages_toggleTodoCompleted();
        r0.msg_id = r14.getId();
        r0.peer = getMessagesController().getInputPeer(r14.getDialogId());
        if (r16 == false) goto L8;
        r0.completed.add(Integer.valueOf(r15.id));
    L10:
        return getConnectionsManager().sendRequest(r0, new SendMessagesHelper$$ExternalSyntheticLambda94(this, r14, r15, r16, r12, r8, r17));
    L8:
        r0.incompleted.add(Integer.valueOf(r15.id));
        goto L10
    }
}
