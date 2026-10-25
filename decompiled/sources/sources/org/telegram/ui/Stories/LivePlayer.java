package org.telegram.ui.Stories;

import android.content.Context;
import android.media.AudioManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.messenger.voip.Instance;
import org.telegram.messenger.voip.NativeInstance;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.messenger.voip.VoIPService$$ExternalSyntheticLambda72;
import org.telegram.messenger.voip.VoipAudioManager;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.Stories.LiveCommentsView;
import org.webrtc.MediaStreamTrack;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;
import org.webrtc.voiceengine.WebRtcAudioTrack;

/* loaded from: classes3.dex */
public class LivePlayer implements NotificationCenter.NotificationCenterDelegate, AudioManager.OnAudioFocusChangeListener {
    public static LivePlayer recording;
    private TLRPC.GroupCall call;
    private int connectionState;
    public final Context context;
    public final int currentAccount;
    private final HashMap<String, Integer> currentStreamRequestTimestamp;
    public boolean destroyed;
    public final long dialogId;
    private VideoSink displaySink;
    private boolean emptyStream;
    private boolean hasAudioFocus;
    public final TLRPC.InputGroupCall inputCall;
    private NativeInstance instance;
    private VoIPService.ProxyVideoSink instanceSink;
    private boolean isFront;
    private boolean isMuted;
    public final boolean isRtmpStream;
    private boolean joined;
    private boolean listeningToAudioFocus;
    public ArrayList<LiveCommentsView.Message> messages;
    private int mySource;
    public boolean outgoing;
    private TLRPC.GroupCallParticipant participant;
    private Runnable poll2Runnable;
    private Runnable pollRunnable;
    private boolean polling;
    private int polling2RequestId;
    private int pollingRequestId;
    private long recordingVideoCapturer;
    private final HashSet<Integer> srcs;
    public final int storyId;
    public TL_stories.StoryItem storyItem;
    public ArrayList<LiveCommentsView.TopSender> topMessages;
    private float volume;

    /* renamed from: org.telegram.ui.Stories.LivePlayer$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends VoIPService.ProxyVideoSink {
        final /* synthetic */ LivePlayer this$0;

        public static /* synthetic */ void $r8$lambda$2v7sLQ2UE4bQgDCPwI_Sb05Q158(AnonymousClass1 r0) {
            r0.lambda$onFrame$0();
        }

        public AnonymousClass1(LivePlayer r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onFrame$0() {
            this.this$0.setEmptyStream(false);
        }

        @Override // org.telegram.messenger.voip.VoIPService.ProxyVideoSink, org.webrtc.VideoSink
        public synchronized void onFrame(VideoFrame r1) {
            monitor-enter(this);
            super.onFrame(r1);     // Catch: Throwable -> L7
            if (LivePlayer.m19369$$Nest$fgetemptyStream(this.this$0) == false) goto L9;
            AndroidUtilities.runOnUIThread(new LivePlayer$1$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L7
        L9:
            monitor-exit(this);
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    /* renamed from: org.telegram.ui.Stories.LivePlayer$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements Instance.OnStateUpdatedListener {
        final /* synthetic */ LivePlayer this$0;

        /* renamed from: $r8$lambda$uh77ahS-tKhGLJUZrE03QX6_UZc, reason: not valid java name */
        public static /* synthetic */ void m19371$r8$lambda$uh77ahStKhGLJUZrE03QX6_UZc(AnonymousClass2 r0) {
            r0.lambda$onStateUpdated$0();
        }

        public AnonymousClass2(LivePlayer r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onStateUpdated$0() {
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(this.this$0.getCallId())});
        }

        @Override // org.telegram.messenger.voip.Instance.OnStateUpdatedListener
        public void onStateUpdated(int r3, boolean r4) {
            boolean r42 = this.this$0.isConnected();
            LivePlayer.m19370$$Nest$fputconnectionState(this.this$0, r3);
            FileLog.d("[LivePlayer] connectionState = " + r3);
            if (r42 == this.this$0.isConnected()) goto L6;
            AndroidUtilities.runOnUIThread(new LivePlayer$2$$ExternalSyntheticLambda0(this));
            return;
        }
    }

    /* renamed from: $r8$lambda$-FOxmsRidFY4v7ocS1xdTht2jdo, reason: not valid java name */
    public static /* synthetic */ void m19359$r8$lambda$FOxmsRidFY4v7ocS1xdTht2jdo(LivePlayer r0) {
        r0.lambda$init$5();
    }

    public static /* synthetic */ void $r8$lambda$0QdTLs94jpzgB6pKnkabWZEwEwU(LivePlayer r0) {
        r0.lambda$init$21();
    }

    public static /* synthetic */ void $r8$lambda$0dNr4K9nM99v1lC5zibe2NVqWAY(LivePlayer r0, String r1) {
        r0.lambda$init$14(r1);
    }

    /* renamed from: $r8$lambda$132Ty-K82ewnLvEpbqdfXMkuVFk, reason: not valid java name */
    public static /* synthetic */ void m19360$r8$lambda$132TyK82ewnLvEpbqdfXMkuVFk(LivePlayer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$end$34(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$403aG1FlZXXzWJQivEPbAq9dVIo(LivePlayer r0) {
        r0.lambda$init$7();
    }

    /* renamed from: $r8$lambda$4jd8vuc-Ek5L-ff7QkPj8Q_wrDs, reason: not valid java name */
    public static /* synthetic */ void m19361$r8$lambda$4jd8vucEk5Lff7QkPj8Q_wrDs(LivePlayer r0, int r1, String r2) {
        r0.lambda$init$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7DeRdDB_J75XjRnYcsVuYta_bNA(LivePlayer r0) {
        r0.lambda$setPolling$26();
    }

    public static /* synthetic */ void $r8$lambda$7nJPpMf7f1M2m39VwHamKWy2Q9I(LivePlayer r0, long r1, long r3, int r5, int r6) {
        r0.lambda$init$18(r1, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$9xjKR4KqDKANwSbdTMZDv7EJ2Dw(LivePlayer r0, long r1, long r3, int r5, int r6) {
        r0.lambda$init$20(r1, r3, r5, r6);
    }

    /* renamed from: $r8$lambda$AtwHtG1vpi37agmofVdT-AO2jNs, reason: not valid java name */
    public static /* synthetic */ void m19362$r8$lambda$AtwHtG1vpi37agmofVdTAO2jNs(LivePlayer r0, int r1, long r2, int r4) {
        r0.lambda$init$19(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$IHv_UkOb7zvAogJJAt-tKRH2Zdo, reason: not valid java name */
    public static /* synthetic */ void m19363$r8$lambda$IHv_UkOb7zvAogJJAttKRH2Zdo(LivePlayer r0, String r1, int r2) {
        r0.lambda$init$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$KjcSE3MXPtF8DYGBdqNvYuzXOS4(LivePlayer r0) {
        r0.lambda$init$15();
    }

    public static /* synthetic */ void $r8$lambda$Ld03Dg1aCA7cYSxNisIf48OqXcA(LivePlayer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$poll$32(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MYy6EjmUtRHByrlh5F72Fb2f6mY(LivePlayer r0) {
        r0.lambda$init$8();
    }

    public static /* synthetic */ void $r8$lambda$N4nqFS_XECxcX0uAp6yuHQ5TuX0(LivePlayer r0, long r1, TLObject r3, TLRPC.TL_error r4, long r5) {
        r0.lambda$init$22(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$QlgEAYFrsBfEI8I_MIPUX4PEmRg(LivePlayer r0, int[] r1, long r2, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$init$12(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$TfpBDZ6lZmej21SGljYrUGD8VsY(LivePlayer r0, TLObject r1, TLRPC.TL_error r2, long r3) {
        r0.lambda$init$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$W4vXXvt5IG2x_FDakSnGMRHyfH8(LivePlayer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$init$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WHNGXoexvaQBtUVf26spAPLuRo0(LivePlayer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$poll2$29(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YfTFvy1VfLQAkfGXr9rqjBf4Wps(LivePlayer r0, long r1, int[] r3) {
        r0.lambda$init$13(r1, r3);
    }

    /* renamed from: $r8$lambda$Zf-53pjibK-njUHxoDqBPPYdZKM, reason: not valid java name */
    public static /* synthetic */ void m19364$r8$lambda$Zf53pjibKnjUHxoDqBPPYdZKM(int[] r0, float[] r1, boolean[] r2) {
    }

    /* renamed from: $r8$lambda$_uN914DrwE6DZcOxT3-jwfQlZx0, reason: not valid java name */
    public static /* synthetic */ void m19365$r8$lambda$_uN914DrwE6DZcOxT3jwfQlZx0(LivePlayer r0, long r1) {
        r0.lambda$init$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$aNm8YXD3rKIb5bTiMacRTEn1Q8U(LivePlayer r0) {
        r0.lambda$poll$31();
    }

    public static /* synthetic */ void $r8$lambda$bukYYc3ywdM5fKgcAK_FQ7fIPhQ(LivePlayer r0, TLObject r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$init$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$fJ19PODBKQW3vWYevM6Te8fVn2I(LivePlayer r0) {
        r0.lambda$poll2$27();
    }

    public static /* synthetic */ void $r8$lambda$fY8GBYzQGR6Rrb3JzV80x37M2pA(LivePlayer r0) {
        r0.lambda$setPolling$25();
    }

    public static /* synthetic */ void $r8$lambda$iE_k93Xg62hcY8rmibrhXg_glYc(LivePlayer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$poll2$28(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$iQczI35Fq6nc0q6xvAAvbIbnKDc(LivePlayer r0, Boolean r1) {
        r0.lambda$continueStreaming$0(r1);
    }

    /* renamed from: $r8$lambda$pKlFJXH7vedGr8x-jSWCClbHf_g, reason: not valid java name */
    public static /* synthetic */ void m19366$r8$lambda$pKlFJXH7vedGr8xjSWCClbHf_g(LivePlayer r0, String r1, long r2, long r4, long r6, int r8, int r9, TLObject r10, TLRPC.TL_error r11, long r12) {
        r0.lambda$init$16(r1, r2, r4, r6, r8, r9, r10, r11, r12);
    }

    /* renamed from: $r8$lambda$peBGsD0SrqD1ePHQZc8wt5A9-PI, reason: not valid java name */
    public static /* synthetic */ void m19367$r8$lambda$peBGsD0SrqD1ePHQZc8wt5A9PI(LivePlayer r0) {
        r0.lambda$poll$30();
    }

    public static /* synthetic */ void $r8$lambda$phALpIFrf5hAAzr9wWEitutUthQ(LivePlayer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$init$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sJUzdzvYZWkoBFaypR2ZPMIY5iQ(LivePlayer r0, ArrayList r1) {
        r0.lambda$init$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$tTBJD6rQmewx07WM4MqPIvVS1_c(LivePlayer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$destroy$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$was7hvfdHstp24BvKX4r26WavSA(LivePlayer r0) {
        r0.lambda$init$3();
    }

    /* renamed from: $r8$lambda$x-HND7nC8MtTdEVls5Poy7KEcdE, reason: not valid java name */
    public static /* synthetic */ void m19368$r8$lambda$xHND7nC8MtTdEVls5Poy7KEcdE(LivePlayer r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$poll$33(r1, r2);
    }

    /* renamed from: -$$Nest$fgetemptyStream, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19369$$Nest$fgetemptyStream(LivePlayer r0) {
        return r0.emptyStream;
    }

    /* renamed from: -$$Nest$fputconnectionState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19370$$Nest$fputconnectionState(LivePlayer r0, int r1) {
        r0.connectionState = r1;
    }

    public LivePlayer(Context r12, int r13, TL_stories.StoryItem r14, long r15, int r17, boolean r18, TLRPC.InputGroupCall r19) {
        this(r12, r13, r14, r15, r17, r18, r19, false, false);
    }

    public LivePlayer(Context r3, int r4, TL_stories.StoryItem r5, long r6, int r8, boolean r9, TLRPC.InputGroupCall r10, boolean r11, boolean r12) {
        this.isMuted = false;
        this.emptyStream = false;
        this.destroyed = false;
        this.currentStreamRequestTimestamp = new HashMap();
        this.isFront = false;
        this.srcs = new HashSet();
        this.volume = 1.0f;
        this.pollingRequestId = -1;
        this.polling2RequestId = -1;
        this.context = r3;
        this.currentAccount = r4;
        this.inputCall = r10;
        this.storyItem = r5;
        this.dialogId = r6;
        this.storyId = r8;
        this.isRtmpStream = r9;
        this.outgoing = r11;
        this.isFront = r12;
        this.instanceSink = new AnonymousClass1(this);
        FileLog.d("[LivePlayer] setup to call " + r10.id);
        NotificationCenter.getInstance(r4).addObserver(this, NotificationCenter.storyGroupCallUpdated);
        if (r11 == false) goto L5;
        this.recordingVideoCapturer = NativeInstance.createVideoCapturer(this.instanceSink, r12 ? 1 : 0);
    L5:
        configureAudio();
        init();
    }

    private void configureAudio() {
        WebRtcAudioTrack.setAudioTrackUsageAttribute(1);
        WebRtcAudioTrack.setAudioStreamType(Integer.MIN_VALUE);
        AudioManager r1 = (AudioManager) this.context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (this.isRtmpStream == false) goto L7;
        r1.setMode(0);
        r1.setBluetoothScoOn(false);
        return;
    L7:
        if (this.outgoing == false) goto L14;
        r1.setMode(3);
        if (r1.requestAudioFocus(this, 0, 2) != 1) goto L11;
        boolean r2 = true;
    L12:
        this.hasAudioFocus = r2;
        this.listeningToAudioFocus = r2;
        VoipAudioManager r4 = VoipAudioManager.get();
        r1.setBluetoothScoOn(false);
        r4.setSpeakerphoneOn(true);
        return;
    L11:
        r2 = false;
        goto L12
    }

    private NativeInstance.SsrcGroup[] createSsrcGroups(TLRPC.TL_groupCallParticipantVideo r8) {
        if (r8.source_groups.isEmpty() == false) goto L6;
        return null;
    L6:
        int r7 = r8.source_groups.size();
        NativeInstance.SsrcGroup[] r0 = new NativeInstance.SsrcGroup[r7];
        int r2 = 0;
    L7:
        if (r2 >= r7) goto L13;
        r0[r2] = new NativeInstance.SsrcGroup();
        TLRPC.TL_groupCallParticipantVideoSourceGroup r3 = r8.source_groups.get(r2);
        NativeInstance.SsrcGroup r4 = r0[r2];
        r4.semantics = r3.semantics;
        r4.ssrcs = new int[r3.sources.size()];
        int r42 = 0;
    L9:
        int[] r5 = r0[r2].ssrcs;
        if (r42 >= r5.length) goto L12;
        r5[r42] = r3.sources.get(r42).intValue();
        r42 = r42 + 1;
        goto L9
    L12:
        r2 = r2 + 1;
        goto L7
    L13:
        return r0;
    }

    private int getCallStreamDatacenterId() {
        TLRPC.GroupCall r2 = this.call;
        if (r2 != null) goto L6;
        return Integer.MAX_VALUE;
    L6:
        if ((r2.flags & 16) != 0) goto L8;
        return Integer.MAX_VALUE;
    L8:
        return r2.stream_dc_id;
    }

    private void init() {
        if (this.destroyed == false) goto L5;
        return;
    L5:
        NativeInstance r0 = NativeInstance.makeGroup(VoIPHelper.getLogFilePath("live_" + this.inputCall.id), 0, false, SharedConfig.noiseSupression, new LivePlayer$$ExternalSyntheticLambda2(this), new LivePlayer$$ExternalSyntheticLambda3(), new LivePlayer$$ExternalSyntheticLambda4(this), new LivePlayer$$ExternalSyntheticLambda5(this), new LivePlayer$$ExternalSyntheticLambda6(this), new LivePlayer$$ExternalSyntheticLambda7(this), false);
        this.instance = r0;
        r0.setOnStateUpdatedListener(new AnonymousClass2(this));
        this.instance.resetGroupInstance(false, false);
    }

    private /* synthetic */ void lambda$continueStreaming$0(Boolean r4) {
        if (r4.booleanValue() == true) goto L6;
        return;
    L6:
        if (this.destroyed == false) goto L8;
        return;
    L8:
        this.outgoing = true;
        this.isFront = true;
        setPolling(false);
        recording = this;
        this.recordingVideoCapturer = NativeInstance.createVideoCapturer(this.instanceSink, this.isFront ? 1 : 0);
        if (this.instance == null) goto L11;
        DispatchQueue r42 = Utilities.globalQueue;
        NativeInstance r0 = this.instance;
        Objects.requireNonNull(r0);
        r42.postRunnable(new VoIPService$$ExternalSyntheticLambda72(r0));
        this.srcs.clear();
        this.instance = null;
    L11:
        configureAudio();
        init();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(this.inputCall.id)});
    }

    private /* synthetic */ void lambda$destroy$24(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$end$34(TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.Updates) == false) goto L6;
        TLRPC.Updates r32 = (TLRPC.Updates) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r32.chats, false);
        MessagesController.getInstance(this.currentAccount).processUpdates(r32, false);
        return;
    L6:
        if (r4 != null) goto L8;
        return;
    L8:
        if ("GROUPCALL_ALREADY_DISCARDED".equalsIgnoreCase(r4.text) == false) goto L12;
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda1(this));
        return;
    }

    private /* synthetic */ void lambda$init$1(ArrayList r8) {
        int r0 = r8.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Object r2 = r8.get(r1);
        r1 = r1 + 1;
        TL_update.TL_updateGroupCallMessage r22 = (TL_update.TL_updateGroupCallMessage) r2;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryMessageUpdate, new Object[]{Long.valueOf(r22.call.id), r22, Boolean.TRUE});
        goto L3
    }

    private /* synthetic */ void lambda$init$10(int r3, String r4) {
        this.mySource = r3;
        TL_phone.joinGroupCall r32 = new TL_phone.joinGroupCall();
        boolean r0 = this.outgoing;
        r32.muted = !r0;
        r32.video_stopped = !r0;
        r32.call = this.inputCall;
        TLRPC.TL_dataJSON r02 = new TLRPC.TL_dataJSON();
        r32.params = r02;
        r02.data = r4;
        TLRPC.TL_inputPeerUser r42 = new TLRPC.TL_inputPeerUser();
        r32.join_as = r42;
        r42.user_id = AccountInstance.getInstance(this.currentAccount).getUserConfig().getClientUserId();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new LivePlayer$$ExternalSyntheticLambda12(this));
    }

    private /* synthetic */ void lambda$init$12(int[] r9, long r10, TLObject r12, TLRPC.TL_error r13) {
        if ((r12 instanceof TL_phone.groupParticipants) == false) goto L23;
        TL_phone.groupParticipants r122 = (TL_phone.groupParticipants) r12;
        MessagesController.getInstance(this.currentAccount).putUsers(r122.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r122.chats, false);
        if (this.instance == null) goto L24;
        UserConfig.getInstance(this.currentAccount).getClientUserId();
        ArrayList r132 = new ArrayList();
        int r0 = 0;
    L9:
        if (r0 >= r9.length) goto L16;
        int r2 = r9[r0];
        ArrayList<TLRPC.GroupCallParticipant> r3 = r122.participants;
        int r4 = r3.size();
        int r5 = 0;
    L11:
        if (r5 >= r4) goto L15;
        TLRPC.GroupCallParticipant r6 = r3.get(r5);
        r5 = r5 + 1;
        TLRPC.GroupCallParticipant r62 = r6;
        if (r62.source != r2) goto L11;
        r132.add(new VoIPService.RequestedParticipant(r62, r2));
    L15:
        r0 = r0 + 1;
        goto L9
    L16:
        this.instance.onMediaDescriptionAvailable(r10, (VoIPService.RequestedParticipant[]) r132.toArray(new VoIPService.RequestedParticipant[0]));
        return;
    L24:
        return;
    }

    private /* synthetic */ void lambda$init$13(long r5, int[] r7) {
        if (this.instance != null) goto L5;
        return;
    L5:
        TL_phone.getGroupParticipants r0 = new TL_phone.getGroupParticipants();
        r0.call = this.inputCall;
        r0.offset = _UrlKt.FRAGMENT_ENCODE_SET;
        int r1 = 0;
    L7:
        if (r1 >= r7.length) goto L9;
        r0.sources.add(Integer.valueOf(r7[r1]));
        r1 = r1 + 1;
        goto L7
    L9:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new LivePlayer$$ExternalSyntheticLambda17(this, r7, r5));
    }

    private /* synthetic */ void lambda$init$14(String r1) {
        this.currentStreamRequestTimestamp.remove(r1);
    }

    private /* synthetic */ void lambda$init$15() {
        if (this.instance == null) goto L5;
        DispatchQueue r0 = Utilities.globalQueue;
        NativeInstance r1 = this.instance;
        Objects.requireNonNull(r1);
        r0.postRunnable(new VoIPService$$ExternalSyntheticLambda72(r1));
        this.srcs.clear();
        this.instance = null;
    L5:
        init();
    }

    private /* synthetic */ void lambda$init$16(String r21, long r22, long r24, long r26, int r28, int r29, TLObject r30, TLRPC.TL_error r31, long r32) {
        if (this.destroyed == false) goto L5;
        return;
    L5:
        if (this.instance == null) goto L38;
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda27(this, r21));
        String r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r30 == null) goto L15;
        TLRPC.TL_upload_file r3 = (TLRPC.TL_upload_file) r30;
        StringBuilder r15 = new StringBuilder("[LivePlayer] received in ");
        r15.append(System.currentTimeMillis() - r22);
        r15.append("ms getFile{time_ms=");
        r15.append(r24);
        if (r26 != 500) goto L12;
        r9 = ", scale = 1";
    L12:
        r15.append(r9);
        r15.append(", video_channel = ");
        r15.append(r28);
        r15.append(", video_quality = ");
        r15.append(r29);
        r15.append("}: ");
        r15.append(r3.bytes.limit());
        r15.append(" bytes");
        FileLog.d(r15.toString());
        NativeInstance r0 = this.instance;
        NativeByteBuffer r33 = r3.bytes;
        r0.onStreamPartAvailable(r24, r33.buffer, r33.limit(), r32, r28, r29);
        return;
    L15:
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r31.text) == false) goto L19;
        this.instance.onStreamPartAvailable(r24, null, -1, r32, r28, r29);
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda1(this));
        return;
    L19:
        if ("GROUPCALL_JOIN_MISSING".equals(r31.text) == false) goto L26;
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda28(this));
        StringBuilder r02 = new StringBuilder("[LivePlayer] received in ");
        r02.append(System.currentTimeMillis() - r22);
        r02.append("ms getFile{time_ms=");
        r02.append(r24);
        if (r26 != 500) goto L23;
        r9 = ", scale = 1";
    L23:
        r02.append(r9);
        r02.append(", video_channel = ");
        r02.append(r28);
        r02.append(", video_quality = ");
        r02.append(r29);
        r02.append("}: ");
        r02.append(r31.text);
        r02.append(" => rejoining");
        FileLog.d(r02.toString());
        return;
    L26:
        if ("TIME_TOO_BIG".equals(r31.text) == false) goto L28;
    L31:
        int r11 = 0;
    L32:
        StringBuilder r12 = new StringBuilder("[LivePlayer] received in ");
        r12.append(System.currentTimeMillis() - r22);
        r12.append("ms getFile{time_ms=");
        r12.append(r24);
        if (r26 != 500) goto L35;
        r9 = ", scale = 1";
    L35:
        r12.append(r9);
        r12.append(", video_channel = ");
        r12.append(r28);
        r12.append(", video_quality = ");
        r12.append(r29);
        r12.append("}: ");
        r12.append(r31.text);
        r12.append(" => ");
        r12.append(r11);
        FileLog.d(r12.toString());
        this.instance.onStreamPartAvailable(r24, null, r11, r32, r28, r29);
        return;
    L28:
        if (r31.text.startsWith("FLOOD_WAIT") == true) goto L31;
        r11 = -1;
        goto L32
    }

    private /* synthetic */ void lambda$init$17(String r1, int r2) {
        this.currentStreamRequestTimestamp.put(r1, Integer.valueOf(r2));
    }

    private /* synthetic */ void lambda$init$18(long r14, long r16, int r18, int r19) {
        if (this.call != null) goto L5;
        return;
    L5:
        StringBuilder r0 = new StringBuilder("[LivePlayer] sending getFile time_ms=");
        r0.append(r14);
        if (r16 != 500) goto L8;
        String r3 = ", scale = 1";
    L9:
        r0.append(r3);
        r0.append(", video_channel = ");
        r0.append(r18);
        r0.append(", video_quality = ");
        r0.append(r19);
        FileLog.d(r0.toString());
        long r32 = System.currentTimeMillis();
        TLRPC.TL_upload_getFile r11 = new TLRPC.TL_upload_getFile();
        r11.limit = 131072;
        TLRPC.TL_inputGroupCallStream r02 = new TLRPC.TL_inputGroupCallStream();
        r02.call = this.inputCall;
        r02.time_ms = r14;
        if (r16 != 500) goto L12;
        r02.scale = 1;
    L12:
        if (r18 == 0) goto L14;
        r02.flags |= 1;
        r02.video_channel = r18;
        r02.video_quality = r19;
    L14:
        r11.location = r02;
        if (r18 != 0) goto L18;
        StringBuilder r03 = new StringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        r03.append(r14);
    L17:
        String r04 = r03.toString();
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda14(this, r04, AccountInstance.getInstance(this.currentAccount).getConnectionsManager().sendRequest(r11, new LivePlayer$$ExternalSyntheticLambda13(this, r04, r32, r14, r16, r18, r19), 2, 2, getCallStreamDatacenterId())));
        return;
    L18:
        r03 = new StringBuilder();
        r03.append(r18);
        r03.append("_");
        r03.append(r14);
        r03.append("_");
        r03.append(r19);
        goto L17
    L8:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    private /* synthetic */ void lambda$init$19(int r2, long r3, int r5) {
        if (r2 != 0) goto L4;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET + r3;
    L5:
        Integer r32 = this.currentStreamRequestTimestamp.get(r22);
        if (r32 == null) goto L9;
        AccountInstance.getInstance(this.currentAccount).getConnectionsManager().cancelRequest(r32.intValue(), true);
        this.currentStreamRequestTimestamp.remove(r22);
        return;
    L9:
        return;
    L4:
        r22 = r2 + "_" + r3 + "_" + r5;
        goto L5
    }

    private /* synthetic */ void lambda$init$2(TLObject r1, TLObject r2, TLRPC.TL_error r3) {
        if ((r2 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$init$20(long r7, long r9, int r11, int r12) {
        StringBuilder r0 = new StringBuilder("[LivePlayer] cancelling getFile time_ms=");
        r0.append(r7);
        String r10 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r9 != 500) goto L5;
        String r92 = ", scale = 1";
    L6:
        r0.append(r92);
        if (r11 == 0) goto L9;
        r10 = ", video_channel = " + r11 + ", video_quality = " + r12;
    L9:
        r0.append(r10);
        FileLog.d(r0.toString());
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda11(this, r11, r7, r12));
        return;
    L5:
        r92 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private /* synthetic */ void lambda$init$21() {
        setEmptyStream(true);
    }

    private /* synthetic */ void lambda$init$22(long r9, TLObject r11, TLRPC.TL_error r12, long r13) {
        long r132 = 0;
        if (r12 == null) goto L5;
    L23:
        NativeInstance r8 = this.instance;
        if (r8 == null) goto L29;
        r8.onRequestTimeComplete(r9, r132);
        return;
    L29:
        return;
    L5:
        if (this.instance != null) goto L7;
        return;
    L7:
        if (this.destroyed == true) goto L30;
        TL_phone.groupCallStreamChannels r112 = (TL_phone.groupCallStreamChannels) r11;
        int r0 = 0;
        if (r112.channels.isEmpty() == true) goto L13;
        r132 = r112.channels.get(0).last_timestamp_ms;
    L13:
        if (r112.channels.isEmpty() == false) goto L16;
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda26(this));
    L16:
        if (this.participant != null) goto L23;
        if (r112.channels.isEmpty() == true) goto L23;
        TLRPC.TL_groupCallParticipant r122 = new TLRPC.TL_groupCallParticipant();
        this.participant = r122;
        r122.peer = MessagesController.getInstance(this.currentAccount).getPeer(this.dialogId);
        TLRPC.GroupCallParticipant r123 = this.participant;
        r123.video = new TLRPC.TL_groupCallParticipantVideo();
        TLRPC.TL_groupCallParticipantVideoSourceGroup r124 = new TLRPC.TL_groupCallParticipantVideoSourceGroup();
        r124.semantics = "SIM";
        ArrayList<TL_phone.TL_groupCallStreamChannel> r113 = r112.channels;
        int r1 = r113.size();
    L20:
        if (r0 >= r1) goto L22;
        TL_phone.TL_groupCallStreamChannel r2 = r113.get(r0);
        r0 = r0 + 1;
        r124.sources.add(Integer.valueOf(r2.channel));
        goto L20
    L22:
        this.participant.video.source_groups.add(r124);
        TLRPC.GroupCallParticipant r114 = this.participant;
        TLRPC.TL_groupCallParticipantVideo r125 = r114.video;
        r125.endpoint = "unified";
        r114.videoEndpoint = "unified";
        this.instance.addIncomingVideoOutput(2, "unified", pushSources(createSsrcGroups(r125)), this.instanceSink, DialogObject.getPeerDialogId(this.participant.peer));
        goto L23
    }

    private /* synthetic */ void lambda$init$23(long r8) {
        TLRPC.GroupCall r0 = this.call;
        if (r0 != null) goto L5;
    L13:
        NativeInstance r02 = this.instance;
        if (r02 == null) goto L18;
        r02.onRequestTimeComplete(r8, ConnectionsManager.getInstance(this.currentAccount).getCurrentTimeMillis());
        return;
    L18:
        return;
    L5:
        if (r0.rtmp_stream == false) goto L13;
        TL_phone.getGroupCallStreamChannels r2 = new TL_phone.getGroupCallStreamChannels();
        r2.call = this.inputCall;
        if (this.call != null) goto L9;
        return;
    L9:
        if (this.instance == null) goto L19;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new LivePlayer$$ExternalSyntheticLambda10(this, r8), 65536, 2, getCallStreamDatacenterId());
        return;
    }

    private /* synthetic */ void lambda$init$3() {
        setEmptyStream(true);
    }

    private /* synthetic */ void lambda$init$4(TLObject r8, TLRPC.TL_error r9, long r10) {
        if (r9 == null) goto L4;
        return;
    L4:
        if (this.instance != null) goto L6;
        return;
    L6:
        if (this.destroyed == true) goto L25;
        TL_phone.groupCallStreamChannels r82 = (TL_phone.groupCallStreamChannels) r8;
        int r102 = 0;
        if (r82.channels.isEmpty() == true) goto L12;
        long r0 = r82.channels.get(0).last_timestamp_ms;
    L12:
        if (r82.channels.isEmpty() == false) goto L15;
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda31(this));
    L15:
        if (this.participant != null) goto L24;
        TLRPC.TL_groupCallParticipant r92 = new TLRPC.TL_groupCallParticipant();
        this.participant = r92;
        r92.peer = MessagesController.getInstance(this.currentAccount).getPeer(this.dialogId);
        TLRPC.GroupCallParticipant r93 = this.participant;
        r93.video = new TLRPC.TL_groupCallParticipantVideo();
        TLRPC.TL_groupCallParticipantVideoSourceGroup r94 = new TLRPC.TL_groupCallParticipantVideoSourceGroup();
        r94.semantics = "SIM";
        ArrayList<TL_phone.TL_groupCallStreamChannel> r83 = r82.channels;
        int r11 = r83.size();
    L17:
        if (r102 >= r11) goto L19;
        TL_phone.TL_groupCallStreamChannel r02 = r83.get(r102);
        r102 = r102 + 1;
        r94.sources.add(Integer.valueOf(r02.channel));
        goto L17
    L19:
        this.participant.video.source_groups.add(r94);
        TLRPC.GroupCallParticipant r84 = this.participant;
        TLRPC.TL_groupCallParticipantVideo r95 = r84.video;
        r95.endpoint = "unified";
        r84.videoEndpoint = "unified";
        this.instance.addIncomingVideoOutput(2, "unified", pushSources(createSsrcGroups(r95)), this.instanceSink, DialogObject.getPeerDialogId(this.participant.peer));
        return;
    L24:
        return;
    }

    private /* synthetic */ void lambda$init$5() {
        setEmptyStream(true);
    }

    private /* synthetic */ void lambda$init$6(TLObject r8, TLRPC.TL_error r9) {
        if ((r8 instanceof TL_phone.groupCall) == false) goto L25;
        TL_phone.groupCall r82 = (TL_phone.groupCall) r8;
        int r1 = 0;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
        if (this.instance != null) goto L7;
        return;
    L7:
        if (this.destroyed == false) goto L10;
        return;
    L10:
        if (r1 >= r82.participants.size()) goto L15;
        if (DialogObject.getPeerDialogId(r82.participants.get(r1).peer) == this.dialogId) goto L13;
        r1 = r1 + 1;
        goto L10
    L13:
        this.participant = r82.participants.get(r1);
    L15:
        TLRPC.GroupCallParticipant r83 = this.participant;
        if (r83 == null) goto L21;
        TLRPC.TL_groupCallParticipantVideo r84 = r83.video;
        if (r84 == null) goto L21;
        this.instance.addIncomingVideoOutput(2, r84.endpoint, pushSources(createSsrcGroups(r84)), this.instanceSink, DialogObject.getPeerDialogId(this.participant.peer));
        return;
    L21:
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda30(this));
        return;
    }

    private /* synthetic */ void lambda$init$7() {
        setEmptyStream(true);
    }

    private /* synthetic */ void lambda$init$8() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(this.call.id)});
        setPolling(true);
    }

    private /* synthetic */ void lambda$init$9(TLObject r13, TLRPC.TL_error r14) {
        if ((r13 instanceof TLRPC.Updates) == false) goto L56;
        TLRPC.Updates r142 = (TLRPC.Updates) r13;
        int r2 = 0;
        MessagesController.getInstance(this.currentAccount).putUsers(r142.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r142.chats, false);
        ArrayList r0 = MessagesController.findUpdates(r142, TL_update.TL_updateGroupCall.class);
        int r1 = r0.size();
        int r3 = 0;
    L5:
        if (r3 >= r1) goto L7;
        Object r4 = r0.get(r3);
        r3 = r3 + 1;
        this.call = ((TL_update.TL_updateGroupCall) r4).call;
        goto L5
    L7:
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda19(this, MessagesController.findUpdatesAndRemove(r142, TL_update.TL_updateGroupCallMessage.class)));
        MessagesController.getInstance(this.currentAccount).processUpdates(r142, false);
        TLRPC.GroupCall r02 = this.call;
        if (r02 != null) goto L10;
    L12:
        boolean r03 = false;
    L13:
        ArrayList r32 = MessagesController.findUpdates(r142, TL_update.TL_updateGroupCallParticipants.class);
        int r42 = r32.size();
        int r5 = 0;
    L14:
        if (r5 >= r42) goto L27;
        Object r6 = r32.get(r5);
        r5 = r5 + 1;
        TL_update.TL_updateGroupCallParticipants r62 = (TL_update.TL_updateGroupCallParticipants) r6;
        if (r62.call.id != getCallId()) goto L14;
        if (r03 == true) goto L14;
        int r7 = 0;
    L20:
        if (r7 >= r62.participants.size()) goto L26;
        if (DialogObject.getPeerDialogId(r62.participants.get(r7).peer) == this.dialogId) goto L23;
        r7 = r7 + 1;
        goto L20
    L23:
        this.participant = r62.participants.get(r7);
    L26:
        if (this.participant == null) goto L14;
    L27:
        ArrayList r143 = MessagesController.findUpdates(r142, TL_update.TL_updateGroupCallConnection.class);
        int r33 = r143.size();
        TLRPC.TL_dataJSON r43 = null;
    L28:
        if (r2 >= r33) goto L30;
        Object r44 = r143.get(r2);
        r2 = r2 + 1;
        r43 = ((TL_update.TL_updateGroupCallConnection) r44).params;
        goto L28
    L30:
        FileLog.d("[LivePlayer] joined call " + this.inputCall.id);
        this.joined = true;
        if (this.destroyed == false) goto L33;
    L54:
        TL_phone.leaveGroupCall r144 = new TL_phone.leaveGroupCall();
        r144.call = this.inputCall;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r144, new LivePlayer$$ExternalSyntheticLambda20(this, r13));
        return;
    L33:
        if (this.instance == null) goto L54;
        if (r43 != null) goto L37;
    L39:
        this.instance.prepareForStream(r03);
    L41:
        if (this.outgoing == false) goto L43;
        this.instance.setMuteMicrophone(this.isMuted);
        this.instance.activateVideoCapturer(this.recordingVideoCapturer);
        this.instance.setupOutgoingVideoCreated(this.recordingVideoCapturer);
    L52:
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda24(this));
        return;
    L43:
        TLRPC.GroupCallParticipant r132 = this.participant;
        if (r132 != null) goto L48;
        if (r03 == false) goto L47;
        TL_phone.getGroupCallStreamChannels r22 = new TL_phone.getGroupCallStreamChannels();
        r22.call = this.inputCall;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new LivePlayer$$ExternalSyntheticLambda21(this), 65536, 2, getCallStreamDatacenterId());
        goto L52
    L47:
        TL_phone.getGroupCall r133 = new TL_phone.getGroupCall();
        r133.call = this.inputCall;
        r133.limit = 10;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r133, new LivePlayer$$ExternalSyntheticLambda22(this));
        goto L52
    L48:
        TLRPC.TL_groupCallParticipantVideo r134 = r132.video;
        if (r134 == null) goto L51;
        this.instance.addIncomingVideoOutput(2, r134.endpoint, pushSources(createSsrcGroups(r134)), this.instanceSink, DialogObject.getPeerDialogId(this.participant.peer));
        goto L52
    L51:
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda23(this));
        goto L52
    L37:
        if (r43.data.startsWith("{\"stream\":true") == true) goto L39;
        this.instance.setJoinResponsePayload(r43.data);
        goto L41
    L10:
        if (r02.rtmp_stream == false) goto L12;
        r03 = true;
        goto L13
    L56:
        if (r14 != null) goto L58;
        return;
    L58:
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r14.text) == false) goto L74;
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda1(this));
        return;
    }

    private /* synthetic */ void lambda$poll$30() {
        if (this.instance == null) goto L5;
        DispatchQueue r0 = Utilities.globalQueue;
        NativeInstance r1 = this.instance;
        Objects.requireNonNull(r1);
        r0.postRunnable(new VoIPService$$ExternalSyntheticLambda72(r1));
        this.srcs.clear();
        this.instance = null;
    L5:
        init();
    }

    private /* synthetic */ void lambda$poll$31() {
        poll();
    }

    private /* synthetic */ void lambda$poll$32(TLObject r3, TLRPC.TL_error r4) {
        if (this.destroyed == false) goto L6;
        return;
    L6:
        if ((r3 instanceof Vector) == false) goto L14;
        if (((Vector) r3).toIntArray().contains(Integer.valueOf(this.mySource)) == true) goto L24;
        if (this.instance == null) goto L12;
        DispatchQueue r32 = Utilities.globalQueue;
        NativeInstance r42 = this.instance;
        Objects.requireNonNull(r42);
        r32.postRunnable(new VoIPService$$ExternalSyntheticLambda72(r42));
        this.srcs.clear();
        this.instance = null;
    L12:
        init();
    L24:
        if (this.polling == false) goto L30;
        Runnable r33 = this.pollRunnable;
        if (r33 == null) goto L28;
        AndroidUtilities.cancelRunOnUIThread(r33);
    L28:
        LivePlayer$$ExternalSyntheticLambda34 r34 = new LivePlayer$$ExternalSyntheticLambda34(this);
        this.pollRunnable = r34;
        AndroidUtilities.runOnUIThread(r34, 4000);
        return;
    L30:
        return;
    L14:
        if ((r3 instanceof TL_phone.groupCall) == false) goto L16;
        TL_phone.groupCall r35 = (TL_phone.groupCall) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r35.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r35.chats, false);
        this.call = r35.call;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(this.call.id)});
        goto L24
    L16:
        if (r4 == null) goto L24;
        if ("GROUPCALL_JOIN_MISSING".equals(r4.text) == false) goto L21;
        FileLog.d("[LivePlayer] received GROUPCALL_JOIN_MISSING on checkGroupCall => rejoining");
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda33(this));
        goto L24
    L21:
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r4.text) == false) goto L24;
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda1(this));
        goto L24
    }

    private /* synthetic */ void lambda$poll$33(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda32(this, r2, r3));
    }

    private /* synthetic */ void lambda$poll2$27() {
        poll2();
    }

    private /* synthetic */ void lambda$poll2$28(TLObject r3, TLRPC.TL_error r4) {
        if (this.destroyed == false) goto L6;
        return;
    L6:
        if ((r3 instanceof TL_phone.groupCall) == false) goto L8;
        TL_phone.groupCall r32 = (TL_phone.groupCall) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r32.chats, false);
        this.call = r32.call;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(this.call.id)});
    L13:
        if (this.polling == false) goto L19;
        Runnable r33 = this.poll2Runnable;
        if (r33 == null) goto L17;
        AndroidUtilities.cancelRunOnUIThread(r33);
    L17:
        LivePlayer$$ExternalSyntheticLambda35 r34 = new LivePlayer$$ExternalSyntheticLambda35(this);
        this.poll2Runnable = r34;
        AndroidUtilities.runOnUIThread(r34, pollingGroupCallInterval());
        return;
    L19:
        return;
    L8:
        if (r4 == null) goto L13;
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r4.text) == false) goto L13;
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda1(this));
        goto L13
    }

    private /* synthetic */ void lambda$poll2$29(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LivePlayer$$ExternalSyntheticLambda29(this, r2, r3));
    }

    private /* synthetic */ void lambda$setPolling$25() {
        poll();
    }

    private /* synthetic */ void lambda$setPolling$26() {
        poll2();
    }

    private void poll() {
        this.pollRunnable = null;
        if (this.destroyed == false) goto L5;
        return;
    L5:
        TL_phone.checkGroupCall r0 = new TL_phone.checkGroupCall();
        r0.call = this.inputCall;
        r0.sources.add(Integer.valueOf(this.mySource));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new LivePlayer$$ExternalSyntheticLambda25(this));
    }

    private void poll2() {
        this.poll2Runnable = null;
        if (this.destroyed == false) goto L5;
        return;
    L5:
        TL_phone.getGroupCall r0 = new TL_phone.getGroupCall();
        r0.call = this.inputCall;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new LivePlayer$$ExternalSyntheticLambda18(this));
    }

    private int pollingGroupCallInterval() {
        if (isAdmin() == false) goto L6;
        return 5000;
    L6:
        return 20000;
    }

    private NativeInstance.SsrcGroup[] pushSources(NativeInstance.SsrcGroup[] r6) {
        int r1 = 0;
    L3:
        if (r6 != null) goto L5;
        int r2 = 0;
    L6:
        if (r1 >= r2) goto L12;
        int r22 = 0;
    L8:
        int[] r3 = r6[r1].ssrcs;
        if (r22 >= r3.length) goto L11;
        this.srcs.add(Integer.valueOf(r3[r22]));
        r22 = r22 + 1;
        goto L8
    L11:
        r1 = r1 + 1;
        goto L3
    L12:
        updateVolumes();
        return r6;
    L5:
        r2 = r6.length;
        goto L6
    }

    private void setPolling(boolean r4) {
        if (this.destroyed == false) goto L6;
        r4 = false;
    L6:
        if (this.polling == r4) goto L32;
        this.polling = r4;
        if (r4 == false) goto L11;
        Runnable r42 = this.pollRunnable;
        if (r42 == null) goto L26;
        AndroidUtilities.cancelRunOnUIThread(r42);
    L26:
        LivePlayer$$ExternalSyntheticLambda15 r43 = new LivePlayer$$ExternalSyntheticLambda15(this);
        this.pollRunnable = r43;
        AndroidUtilities.runOnUIThread(r43, 4000);
        Runnable r44 = this.poll2Runnable;
        if (r44 == null) goto L29;
        AndroidUtilities.cancelRunOnUIThread(r44);
    L29:
        LivePlayer$$ExternalSyntheticLambda16 r45 = new LivePlayer$$ExternalSyntheticLambda16(this);
        this.poll2Runnable = r45;
        AndroidUtilities.runOnUIThread(r45, pollingGroupCallInterval());
        return;
    L11:
        if (this.pollingRequestId == (-1)) goto L14;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.pollingRequestId, true);
        this.pollingRequestId = -1;
    L14:
        if (this.polling2RequestId == (-1)) goto L16;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.polling2RequestId, true);
        this.polling2RequestId = -1;
    L16:
        Runnable r46 = this.pollRunnable;
        if (r46 == null) goto L19;
        AndroidUtilities.cancelRunOnUIThread(r46);
        this.pollRunnable = null;
    L19:
        Runnable r47 = this.poll2Runnable;
        if (r47 == null) goto L31;
        AndroidUtilities.cancelRunOnUIThread(r47);
        this.poll2Runnable = null;
        return;
    L31:
        return;
    }

    private void updateVolumes() {
        if (this.destroyed == false) goto L5;
        return;
    L5:
        if (this.instance == null) goto L13;
        Iterator<Integer> r0 = this.srcs.iterator();
    L9:
        if (r0.hasNext() == false) goto L14;
        this.instance.setVolume(r0.next().intValue(), this.volume);
        goto L9
    L14:
        return;
    }

    public boolean areMessagesEnabled() {
        TLRPC.GroupCall r0 = this.call;
        if (r0 != null) goto L7;
        return true;
    L7:
        return r0.messages_enabled;
    }

    public boolean canContinueEmptyStream() {
        if (this.destroyed == false) goto L5;
        return false;
    L5:
        if (this.outgoing == false) goto L7;
        return false;
    L7:
        if (this.emptyStream == true) goto L9;
        return false;
    L9:
        if (recording != null) goto L22;
        TLRPC.GroupCall r1 = this.call;
        if (r1 != null) goto L13;
        return false;
    L13:
        if (r1.rtmp_stream == false) goto L15;
        return false;
    L15:
        if (r1.creator == false) goto L25;
        return true;
    L25:
        return false;
    L22:
        return false;
    }

    public boolean commentsDisabled() {
        if (this.call != null) goto L6;
        return false;
    L6:
        if (isAdmin() == false) goto L9;
        return false;
    L9:
        return !this.call.messages_enabled;
    }

    public void continueStreaming() {
        if (this.outgoing == false) goto L5;
        return;
    L5:
        PermissionRequest.ensureAllPermissions(R.raw.permission_request_camera, R.string.PermissionNoCameraMicVideo, new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}, new LivePlayer$$ExternalSyntheticLambda9(this));
    }

    public void destroy() {
        if (this.destroyed == true) goto L25;
        this.destroyed = true;
        setPolling(false);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storyGroupCallUpdated);
        FileLog.d("[LivePlayer] destroyed");
        if (this.joined == false) goto L9;
        TL_phone.leaveGroupCall r1 = new TL_phone.leaveGroupCall();
        r1.call = this.inputCall;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new LivePlayer$$ExternalSyntheticLambda8(this));
    L9:
        if (this.outgoing == false) goto L12;
        this.instanceSink.setTarget(null);
        NativeInstance.destroyVideoCapturer(this.recordingVideoCapturer);
    L12:
        if (this.instance == null) goto L15;
        DispatchQueue r12 = Utilities.globalQueue;
        NativeInstance r3 = this.instance;
        Objects.requireNonNull(r3);
        r12.postRunnable(new VoIPService$$ExternalSyntheticLambda72(r3));
        this.srcs.clear();
        this.instance = null;
    L15:
        if (this.listeningToAudioFocus == false) goto L18;
        ((AudioManager) this.context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).abandonAudioFocus(this);
        this.hasAudioFocus = false;
        this.listeningToAudioFocus = false;
    L18:
        if (this.outgoing == false) goto L21;
        VoipAudioManager.get().setSpeakerphoneOn(false);
    L21:
        if (recording != this) goto L24;
        recording = null;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(getCallId())});
        return;
    L24:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.storyGroupCallUpdated) goto L8;
        long r32 = ((Long) r5[0]).longValue();
        TLRPC.GroupCall r52 = (TLRPC.GroupCall) r5[1];
        if (this.dialogId != r32) goto L9;
        this.call = TlUtils.applyGroupCallUpdate(this.call, r52);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(r52.id)});
        return;
    L9:
        return;
    }

    public void end() {
        if (this.destroyed == false) goto L5;
        return;
    L5:
        TL_phone.discardGroupCall r0 = new TL_phone.discardGroupCall();
        r0.call = this.inputCall;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new LivePlayer$$ExternalSyntheticLambda0(this));
        destroy();
    }

    public boolean equals(TLRPC.InputGroupCall r3) {
        TLRPC.InputGroupCall r2 = this.inputCall;
        if (r2 != r3) goto L5;
        return true;
    L5:
        if (r2.id == r3.id) goto L11;
        return false;
    L11:
        return true;
    }

    public long getCallId() {
        TLRPC.GroupCall r0 = this.call;
        if (r0 != null) goto L5;
        TLRPC.InputGroupCall r2 = this.inputCall;
        if (r2 != null) goto L9;
        return 0;
    L9:
        return r2.id;
    L5:
        return r0.id;
    }

    public TLRPC.Peer getDefaultSendAs() {
        TLRPC.GroupCall r0 = this.call;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.default_send_as;
    }

    public VideoSink getDisplaySink() {
        return this.displaySink;
    }

    public long getSendPaidMessagesStars() {
        TLRPC.GroupCall r2 = this.call;
        if (r2 != null) goto L7;
        return 0;
    L7:
        return r2.send_paid_messages_stars;
    }

    public int getWatchersCount() {
        TLRPC.GroupCall r1 = this.call;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return Math.max(1, r12);
    L5:
        r12 = r1.participants_count;
        goto L7
    }

    public boolean isAdmin() {
        return isAdmin(14);
    }

    public boolean isAdmin(int r7) {
        if (isCreator() == false) goto L5;
        return true;
    L5:
        long r2 = this.dialogId;
        int r22 = this.currentAccount;
        if (r2 < 0) goto L13;
        if (UserConfig.getInstance(r22).getClientUserId() != this.dialogId) goto L10;
        return true;
    L10:
        return false;
    L13:
        return ChatObject.canUserDoAction(MessagesController.getInstance(r22).getChat(Long.valueOf(-this.dialogId)), r7);
    }

    public boolean isConnected() {
        int r2 = this.connectionState;
        if (r2 == 3) goto L10;
        if (r2 == 1) goto L10;
        if (r2 == 2) goto L10;
        return false;
    L10:
        return true;
    }

    public boolean isCreator() {
        TLRPC.GroupCall r0 = this.call;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.creator == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isEmptyStream() {
        if (this.destroyed == false) goto L5;
        return false;
    L5:
        if (this.emptyStream == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isMuted() {
        if (this.outgoing == true) goto L5;
        return false;
    L5:
        if (this.isMuted == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int r2) {
        boolean r0 = true;
        if (r2 == 1) goto L6;
        r0 = false;
    L6:
        this.hasAudioFocus = r0;
    }

    public boolean sendAsDisabled() {
        if (this.call != null) goto L7;
        return false;
    L7:
        return !r0.messages_enabled;
    }

    public void setDefaultSendAs(TLRPC.Peer r5) {
        TLRPC.GroupCall r0 = this.call;
        if (r0 != null) goto L5;
        return;
    L5:
        int r1 = r0.flags;
        if (r5 == null) goto L8;
        boolean r2 = true;
    L9:
        r0.flags = TLObject.setFlag(r1, TLObject.FLAG_21, r2);
        this.call.default_send_as = r5;
        return;
    L8:
        r2 = false;
        goto L9
    }

    public void setDisplaySink(VideoSink r2) {
        if (this.displaySink != r2) goto L5;
        return;
    L5:
        VoIPService.ProxyVideoSink r0 = this.instanceSink;
        this.displaySink = r2;
        r0.setTarget(r2);
    }

    public void setEmptyStream(boolean r4) {
        if (this.destroyed == false) goto L6;
        return;
    L6:
        if (this.emptyStream != r4) goto L9;
        return;
    L9:
        if (this.outgoing == false) goto L12;
        if (r4 == false) goto L12;
        return;
    L12:
        this.emptyStream = r4;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(getCallId())});
    }

    public void setMuted(boolean r2) {
        if (this.outgoing == true) goto L5;
        return;
    L5:
        if (this.isMuted == r2) goto L13;
        this.isMuted = r2;
        NativeInstance r1 = this.instance;
        if (r1 == null) goto L12;
        r1.setMuteMicrophone(r2);
        return;
    L12:
        return;
    }

    public void setVolume(float r3) {
        float r32 = Utilities.clamp01(r3);
        if (LiveStoryPipOverlay.isVisible(this) == false) goto L5;
        r32 = 1.0f;
    L5:
        FileLog.d("setVolume(" + r32 + ")");
        if (Math.abs(r32 - this.volume) >= 0.01f) goto L8;
        return;
    L8:
        this.volume = r32;
        updateVolumes();
    }

    public void storyDeleted() {
        TL_stories.TL_updateStory r0 = new TL_stories.TL_updateStory();
        r0.peer = MessagesController.getInstance(this.currentAccount).getPeer(this.dialogId);
        TL_stories.TL_storyItemDeleted r1 = new TL_stories.TL_storyItemDeleted();
        r0.story = r1;
        r1.id = this.storyId;
        MessagesController.getInstance(this.currentAccount).getStoriesController().processUpdate(r0);
        destroy();
    }

    public void switchCamera() {
        if (this.outgoing == true) goto L5;
        return;
    L5:
        long r0 = this.recordingVideoCapturer;
        boolean r2 = !this.isFront;
        this.isFront = r2;
        NativeInstance.switchCameraCapturer(r0, r2);
    }
}
