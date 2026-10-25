package org.telegram.messenger.voip;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRouter;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.LruCache;
import android.view.KeyEvent;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.json.JSONObject;
import org.mvel2.MVEL;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FilesMigrationService$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.FilesMigrationService$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.NotificationsSettingsFacade;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.StatsController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.XiaomiUtilities;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.messenger.voip.Instance;
import org.telegram.messenger.voip.NativeInstance;
import org.telegram.messenger.voip.VoIPGroupNotification;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.JoinCallAlert;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.DarkBlueThemeResourcesProvider;
import org.telegram.ui.GroupCallActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.VoIPFeedbackActivity;
import org.telegram.ui.VoIPFragment;
import org.telegram.ui.VoIPPermissionActivity;
import org.webrtc.MediaStreamTrack;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;
import org.webrtc.voiceengine.WebRtcAudioTrack;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class VoIPService extends Service implements SensorEventListener, AudioManager.OnAudioFocusChangeListener, NotificationCenter.NotificationCenterDelegate, VoIPServiceState {
    public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
    public static final int AUDIO_ROUTE_BLUETOOTH = 2;
    public static final int AUDIO_ROUTE_EARPIECE = 0;
    public static final int AUDIO_ROUTE_SPEAKER = 1;
    public static final int CALL_MIN_LAYER = 65;
    public static final int CAPTURE_DEVICE_CAMERA = 0;
    public static final int CAPTURE_DEVICE_SCREEN = 1;
    public static final int DISCARD_REASON_CONVERT = 5;
    public static final int DISCARD_REASON_DISCONNECT = 2;
    public static final int DISCARD_REASON_HANGUP = 1;
    public static final int DISCARD_REASON_LINE_BUSY = 4;
    public static final int DISCARD_REASON_MISSED = 3;
    private static final int ID_INCOMING_CALL_NOTIFICATION = 202;
    public static final int ID_INCOMING_CALL_PRENOTIFICATION = 203;
    private static final int ID_ONGOING_CALL_NOTIFICATION = 201;
    private static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
    public static final int QUALITY_FULL = 2;
    public static final int QUALITY_MEDIUM = 1;
    public static final int QUALITY_SMALL = 0;
    public static final int STATE_BUSY = 17;
    public static final int STATE_CREATING = 6;
    public static final int STATE_ENDED = 11;
    public static final int STATE_ESTABLISHED = 3;
    public static final int STATE_EXCHANGING_KEYS = 12;
    public static final int STATE_FAILED = 4;
    public static final int STATE_HANGING_UP = 10;
    public static final int STATE_RECONNECTING = 5;
    public static final int STATE_REQUESTING = 14;
    public static final int STATE_RINGING = 16;
    public static final int STATE_WAITING = 13;
    public static final int STATE_WAITING_INCOMING = 15;
    public static final int STATE_WAIT_INIT = 1;
    public static final int STATE_WAIT_INIT_ACK = 2;
    public static final boolean USE_CONNECTION_SERVICE = false;
    public static NativeInstance.AudioLevelsCallback audioLevelsCallback;
    public static TL_phone.PhoneCall callIShouldHavePutIntoIntent;
    private static Runnable setModeRunnable;
    private static VoIPService sharedInstance;
    private static final Object sync = null;
    private byte[] a_or_b;
    private Runnable afterSoundRunnable;
    private boolean audioConfigured;
    private AudioDeviceCallback audioDeviceCallback;
    private int audioRouteToSet;
    private byte[] authKey;
    private boolean bluetoothScoActive;
    private boolean bluetoothScoConnecting;
    private BluetoothAdapter btAdapter;
    private int callDiscardReason;
    private int callReqId;
    private long callStartTime;
    private long[] captureDevice;
    private TLRPC.Chat chat;
    private int checkRequestId;
    private int classGuid;
    public ConferenceCall conference;
    private Runnable connectingSoundRunnable;
    public long convertingFromCallWithUserId;
    private NativeInstance convertingVoip;
    private PowerManager.WakeLock cpuWakelock;
    private boolean createGroupCall;
    private int currentAccount;
    private String[] currentBackgroundEndpointId;
    private ProxyVideoSink[] currentBackgroundSink;
    public String currentBluetoothDeviceName;
    public boolean currentGroupModeStreaming;
    private int currentState;
    private HashMap<String, Integer> currentStreamRequestTimestamp;
    private Runnable delayedStartOutgoingCall;
    private boolean[] destroyCaptureDevice;
    private final Runnable destroyConvertingRunnable;
    private boolean didDeleteConnectionServiceContact;
    private boolean endCallAfterRequest;
    boolean fetchingBluetoothDeviceName;
    private boolean forceRating;
    private int foregroundId;
    private Notification foregroundNotification;
    private boolean foregroundStarted;
    private byte[] g_a;
    private byte[] g_a_hash;
    private boolean gotMediaProjection;
    public ChatObject.Call groupCall;
    private volatile CountDownLatch groupCallBottomSheetLatch;
    private TLRPC.InputPeer groupCallPeer;
    private boolean hasAudioFocus;
    public boolean hasFewPeers;
    private boolean instantAccept;
    private long[] inviteUsers;
    private HashSet<Long> inviteUsersToConference;
    private boolean inviteUsersToConferenceVideo;
    private boolean isBtHeadsetConnected;
    private volatile boolean isCallEnded;
    private boolean isFrontFaceCamera;
    private boolean isHeadsetPlugged;
    private boolean isOutgoing;
    private boolean isPrivateScreencast;
    private boolean isProximityNear;
    public boolean isRtmpStream;
    private boolean isVideoAvailable;
    private TLRPC.InputGroupCall joinConference;
    private TLRPC.GroupCall joinConferenceCall;
    private String joinHash;
    private long keyFingerprint;
    private String lastError;
    private int lastForegroundType;
    private TLRPC.GroupCall lastGroupCallUpdate;
    private String lastLogFilePath;
    private NetworkInfo lastNetInfo;
    private SensorEvent lastSensorEvent;
    private long lastTypingTimeSend;
    private ProxyVideoSink[] localSink;
    private Boolean mHasEarpiece;
    private boolean micMute;
    public boolean micSwitching;
    private Boolean muteOnStart;
    private TLRPC.TL_dataJSON myParams;
    private int[] mySource;
    private boolean needPlayEndSound;
    private boolean needRateCall;
    private boolean needSendDebugLog;
    private boolean needSwitchToBluetoothAfterScoActivates;
    private boolean notificationsDisabled;
    private Runnable onDestroyRunnable;
    private ArrayList<TL_phone.PhoneCall> pendingUpdates;
    private boolean playedConnectedSound;
    private boolean playingSound;
    private Instance.TrafficStats prevTrafficStats;
    private int previousAudioOutput;
    public TL_phone.PhoneCall privateCall;
    private PowerManager.WakeLock proximityWakelock;
    private final LruCache<String, ProxyVideoSink> proxyVideoSinkLruCache;
    private BroadcastReceiver receiver;
    private boolean reconnectScreenCapture;
    private int remoteAudioState;
    private ProxyVideoSink[] remoteSink;
    private HashMap<String, ProxyVideoSink> remoteSinks;
    private int remoteVideoState;
    private MediaPlayer ringtonePlayer;
    private int scheduleDate;
    private BluetoothProfile.ServiceListener serviceListener;
    public final SharedUIParams sharedUIParams;
    private Runnable shortPollRunnable;
    private int signalBarCount;
    private SoundPool soundPool;
    private int spAllowTalkId;
    private int spBusyId;
    private int spConnectingId;
    private int spEndId;
    private int spFailedID;
    private int spPlayId;
    private int spRingbackID;
    private int spStartRecordId;
    private int spVoiceChatConnecting;
    private int spVoiceChatEndId;
    private int spVoiceChatStartId;
    private boolean speakerphoneStateToSet;
    private boolean startedRinging;
    private ArrayList<StateListener> stateListeners;
    private boolean switchingAccount;
    private boolean switchingCamera;
    private boolean switchingStream;
    private Runnable switchingStreamTimeoutRunnable;
    private CallConnection systemCallConnection;
    private NativeInstance[] tgVoip;
    private Runnable timeoutRunnable;
    private boolean unmutedByHold;
    private Runnable updateNotificationRunnable;
    private TLRPC.User user;
    private Vibrator vibrator;
    public boolean videoCall;
    private int[] videoState;
    private final HashMap<String, TLRPC.GroupCallParticipant> waitingFrameParticipant;
    private boolean wasConnected;
    private boolean wasEstablished;

    /* renamed from: org.telegram.messenger.voip.VoIPService$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ VoIPService this$0;

        /* renamed from: $r8$lambda$QF6524YhGOiGaRR-EKlf9eNG-8I, reason: not valid java name */
        public static /* synthetic */ void m5370$r8$lambda$QF6524YhGOiGaRREKlf9eNG8I(AnonymousClass1 r0) {
            r0.lambda$run$0();
        }

        public static /* synthetic */ void $r8$lambda$qXIvjgGbxhfIMsMFKKrqpX5Uls4(AudioManager r2) {
            Object r0 = VoIPService.m5368$$Nest$sfgetsync();
            monitor-enter(r0);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (VoIPService.m5366$$Nest$sfgetsetModeRunnable() != null) goto L10;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L10:
            VoIPService.m5369$$Nest$sfputsetModeRunnable(null);     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            r2.setMode(0);     // Catch: SecurityException -> L15
            return;
        L15:
            e = move-exception;
            if (BuildVars.LOGS_ENABLED == false) goto L25;
            FileLog.e("Error setting audio more to normal", e);
            return;
        }

        public AnonymousClass1(VoIPService r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0() {
            VoIPService.m5341$$Nest$fgetsoundPool(this.this$0).release();
        }

        @Override // java.lang.Runnable
        public void run() {
            AudioManager r0 = (AudioManager) this.this$0.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            VoipAudioManager r1 = VoipAudioManager.get();
            r0.abandonAudioFocus(this.this$0);
            r0.unregisterMediaButtonEventReceiver(new ComponentName(this.this$0, VoIPMediaButtonReceiver.class));
            if (VoIPService.m5330$$Nest$fgetaudioDeviceCallback(this.this$0) == null) goto L6;
            r0.unregisterAudioDeviceCallback(VoIPService.m5330$$Nest$fgetaudioDeviceCallback(this.this$0));
        L6:
            if (VoIPService.USE_CONNECTION_SERVICE == false) goto L8;
        L13:
            Utilities.globalQueue.postRunnable(new VoIPService$1$$ExternalSyntheticLambda0(this));
            DispatchQueue r5 = Utilities.globalQueue;
            VoIPService$1$$ExternalSyntheticLambda1 r12 = new VoIPService$1$$ExternalSyntheticLambda1(r0);
            VoIPService.m5369$$Nest$sfputsetModeRunnable(r12);
            r5.postRunnable(r12);
            return;
        L8:
            if (VoIPService.m5367$$Nest$sfgetsharedInstance() != null) goto L13;
            if (VoIPService.m5334$$Nest$fgetisBtHeadsetConnected(this.this$0) == false) goto L12;
            r0.stopBluetoothSco();
            r0.setBluetoothScoOn(false);
            VoIPService.m5347$$Nest$fputbluetoothScoActive(this.this$0, false);
            VoIPService.m5348$$Nest$fputbluetoothScoConnecting(this.this$0, false);
        L12:
            r1.setSpeakerphoneOn(false);
            goto L13
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends AudioDeviceCallback {
        final /* synthetic */ VoIPService this$0;

        public AnonymousClass10(VoIPService r1) {
            this.this$0 = r1;
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesAdded(AudioDeviceInfo[] r1) {
            VoIPService.m5359$$Nest$mcheckUpdateBluetoothHeadset(this.this$0);
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesRemoved(AudioDeviceInfo[] r1) {
            VoIPService.m5359$$Nest$mcheckUpdateBluetoothHeadset(this.this$0);
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$11, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass11 implements Runnable {
        final /* synthetic */ VoIPService this$0;

        public AnonymousClass11(VoIPService r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VoIPService.m5345$$Nest$fgettgVoip(this.this$0)[0] == null) goto L6;
            StatsController.getInstance(VoIPService.m5333$$Nest$fgetcurrentAccount(this.this$0)).incrementTotalCallsTime(VoIPService.m5361$$Nest$mgetStatsNetworkType(this.this$0), 5);
            AndroidUtilities.runOnUIThread(this, 5000);
            return;
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 implements BluetoothProfile.ServiceListener {
        final /* synthetic */ VoIPService this$0;

        public AnonymousClass2(VoIPService r1) {
            this.this$0 = r1;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int r5, BluetoothProfile r6) {
        L13:
            th = move-exception;
            FileLog.e(th);
            return;
        L3:
            if (Build.VERSION.SDK_INT >= 31) goto L11;
            Iterator<BluetoothDevice> r0 = r6.getConnectedDevices().iterator();     // Catch: Throwable -> L13
        L6:
            if (r0.hasNext() == false) goto L11;
            BluetoothDevice r1 = r0.next();     // Catch: Throwable -> L13
            if (r6.getConnectionState(r1) != 2) goto L6;
            VoIPService r02 = this.this$0;     // Catch: Throwable -> L13
            r02.currentBluetoothDeviceName = r1.getName();     // Catch: Throwable -> L13
        L11:
            BluetoothAdapter.getDefaultAdapter().closeProfileProxy(r5, r6);     // Catch: Throwable -> L13
            this.this$0.fetchingBluetoothDeviceName = false;     // Catch: Throwable -> L13
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int r1) {
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends BroadcastReceiver {
        final /* synthetic */ VoIPService this$0;

        public AnonymousClass3(VoIPService r1) {
            this.this$0 = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r7, Intent r8) {
            boolean r3 = true;
            int r4 = 0;
            if (VoIPService.ACTION_HEADSET_PLUG.equals(r8.getAction()) == false) goto L31;
            VoIPService r72 = this.this$0;
            if (r8.getIntExtra("state", 0) != 1) goto L7;
            boolean r82 = true;
        L8:
            VoIPService.m5351$$Nest$fputisHeadsetPlugged(r72, r82);
            if (VoIPService.m5335$$Nest$fgetisHeadsetPlugged(this.this$0) == true) goto L11;
        L15:
            boolean r73 = VoIPService.m5335$$Nest$fgetisHeadsetPlugged(this.this$0);
            VoIPService r83 = this.this$0;
            if (r73 == false) goto L26;
            AudioManager r74 = (AudioManager) r83.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            if (VoipAudioManager.get().isSpeakerphoneOn() == false) goto L20;
            VoIPService.m5355$$Nest$fputpreviousAudioOutput(this.this$0, 0);
        L24:
            this.this$0.setAudioOutput(1);
        L28:
            VoIPService.m5352$$Nest$fputisProximityNear(this.this$0, false);
            this.this$0.updateOutputGainControlState();
            return;
        L20:
            boolean r75 = r74.isBluetoothScoOn();
            VoIPService r84 = this.this$0;
            if (r75 == false) goto L23;
            VoIPService.m5355$$Nest$fputpreviousAudioOutput(r84, 2);
            goto L24
        L23:
            VoIPService.m5355$$Nest$fputpreviousAudioOutput(r84, 1);
            goto L24
        L26:
            if (VoIPService.m5337$$Nest$fgetpreviousAudioOutput(r83) < 0) goto L28;
            VoIPService r76 = this.this$0;
            r76.setAudioOutput(VoIPService.m5337$$Nest$fgetpreviousAudioOutput(r76));
            VoIPService.m5355$$Nest$fputpreviousAudioOutput(this.this$0, -1);
            goto L28
        L11:
            if (VoIPService.m5338$$Nest$fgetproximityWakelock(this.this$0) == null) goto L15;
            if (VoIPService.m5338$$Nest$fgetproximityWakelock(this.this$0).isHeld() == false) goto L15;
            VoIPService.m5338$$Nest$fgetproximityWakelock(this.this$0).release();
            goto L15
        L7:
            r82 = false;
            goto L8
        L31:
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(r8.getAction()) == false) goto L35;
            VoIPService.m5364$$Nest$mupdateNetworkType(this.this$0);
            return;
        L35:
            if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(r8.getAction()) == false) goto L46;
            if (BuildVars.LOGS_ENABLED == false) goto L39;
            FileLog.e("bt headset state = " + r8.getIntExtra("android.bluetooth.profile.extra.STATE", 0));
        L39:
            VoIPService r6 = this.this$0;
            if (r8.getIntExtra("android.bluetooth.profile.extra.STATE", 0) == 2) goto L43;
            r3 = false;
        L43:
            VoIPService.m5363$$Nest$mupdateBluetoothHeadsetState(r6, r3);
            return;
        L46:
            if ("android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(r8.getAction()) == false) goto L78;
            int r77 = r8.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0);
            if (BuildVars.LOGS_ENABLED == false) goto L50;
            FileLog.e("Bluetooth SCO state updated: " + r77);
        L50:
            if (r77 == 0) goto L52;
        L61:
            VoIPService r85 = this.this$0;
            if (r77 != 2) goto L64;
            boolean r1 = true;
        L65:
            VoIPService.m5348$$Nest$fputbluetoothScoConnecting(r85, r1);
            VoIPService r86 = this.this$0;
            if (r77 != 1) goto L68;
            boolean r78 = true;
        L69:
            VoIPService.m5347$$Nest$fputbluetoothScoActive(r86, r78);
            if (VoIPService.m5331$$Nest$fgetbluetoothScoActive(this.this$0) == false) goto L74;
            VoIPService.m5360$$Nest$mfetchBluetoothDeviceName(this.this$0);
            if (VoIPService.m5336$$Nest$fgetneedSwitchToBluetoothAfterScoActivates(this.this$0) == false) goto L74;
            VoIPService.m5354$$Nest$fputneedSwitchToBluetoothAfterScoActivates(this.this$0, false);
            AudioManager r79 = (AudioManager) this.this$0.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            VoipAudioManager.get().setSpeakerphoneOn(false);
            r79.setBluetoothScoOn(true);
        L74:
            ArrayList r62 = VoIPService.m5344$$Nest$fgetstateListeners(this.this$0);
            int r710 = r62.size();
        L75:
            if (r4 >= r710) goto L99;
            Object r87 = r62.get(r4);
            r4 = r4 + 1;
            ((StateListener) r87).onAudioSettingsChanged();
            goto L75
        L99:
            return;
        L68:
            r78 = false;
            goto L69
        L64:
            r1 = false;
            goto L65
        L52:
            if (VoIPService.m5334$$Nest$fgetisBtHeadsetConnected(this.this$0) == false) goto L61;
            if (VoIPService.m5332$$Nest$fgetbtAdapter(this.this$0).isEnabled() == true) goto L56;
        L59:
            VoIPService.m5363$$Nest$mupdateBluetoothHeadsetState(this.this$0, false);
            return;
        L56:
            if (PermissionRequest.hasPermission("android.permission.BLUETOOTH_CONNECT") == false) goto L59;
            if (VoIPService.m5332$$Nest$fgetbtAdapter(this.this$0).getProfileConnectionState(1) == 2) goto L61;
        L78:
            if ("android.intent.action.PHONE_STATE".equals(r8.getAction()) == false) goto L84;
            if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(r8.getStringExtra("state")) == false) goto L94;
            this.this$0.hangUp();
            return;
        L94:
            return;
        L84:
            if ("android.intent.action.SCREEN_ON".equals(r8.getAction()) == false) goto L89;
        L86:
            if (r4 >= VoIPService.m5344$$Nest$fgetstateListeners(this.this$0).size()) goto L100;
            ((StateListener) VoIPService.m5344$$Nest$fgetstateListeners(this.this$0).get(r4)).onScreenOnChange(true);
            r4 = r4 + 1;
            goto L86
        L100:
            return;
        L89:
            if ("android.intent.action.SCREEN_OFF".equals(r8.getAction()) == false) goto L98;
            int r711 = 0;
        L92:
            if (r711 >= VoIPService.m5344$$Nest$fgetstateListeners(this.this$0).size()) goto L101;
            ((StateListener) VoIPService.m5344$$Nest$fgetstateListeners(this.this$0).get(r711)).onScreenOnChange(false);
            r711 = r711 + 1;
            goto L92
        L101:
            return;
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends LruCache<String, ProxyVideoSink> {
        final /* synthetic */ VoIPService this$0;

        public AnonymousClass4(VoIPService r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.util.LruCache
        public /* bridge */ /* synthetic */ void entryRemoved(boolean r1, String r2, ProxyVideoSink r3, ProxyVideoSink r4) {
            entryRemoved2(r1, r2, r3, r4);
        }

        /* renamed from: entryRemoved, reason: avoid collision after fix types in other method */
        public void entryRemoved2(boolean r1, String r2, ProxyVideoSink r3, ProxyVideoSink r4) {
            super.entryRemoved(r1, r2, r3, r4);
            VoIPService.m5345$$Nest$fgettgVoip(this.this$0)[0].removeIncomingVideoOutput(ProxyVideoSink.m5373$$Nest$fgetnativeInstance(r3));
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 implements VideoSink {
        final /* synthetic */ VoIPService this$0;
        final /* synthetic */ String val$endpointId;
        final /* synthetic */ boolean val$screencast;

        /* renamed from: $r8$lambda$O8ceM1ykJXQC-mJ7DpVdotMQkGk, reason: not valid java name */
        public static /* synthetic */ void m5371$r8$lambda$O8ceM1ykJXQCmJ7DpVdotMQkGk(AnonymousClass5 r0, String r1, VideoSink r2, boolean r3) {
            r0.lambda$onFrame$0(r1, r2, r3);
        }

        public AnonymousClass5(VoIPService r1, String r2, boolean r3) {
            this.this$0 = r1;
            this.val$endpointId = r2;
            this.val$screencast = r3;
        }

        private /* synthetic */ void lambda$onFrame$0(String r4, VideoSink r5, boolean r6) {
            TLRPC.GroupCallParticipant r0 = (TLRPC.GroupCallParticipant) VoIPService.m5346$$Nest$fgetwaitingFrameParticipant(this.this$0).remove(r4);
            ProxyVideoSink r1 = (ProxyVideoSink) VoIPService.m5340$$Nest$fgetremoteSinks(this.this$0).get(r4);
            if (r1 != null) goto L5;
        L7:
            if (r0 != null) goto L9;
        L12:
            ChatObject.Call r3 = this.this$0.groupCall;
            if (r3 == null) goto L16;
            r3.updateVisibleParticipants();
            return;
        L16:
            return;
        L9:
            if (r6 == false) goto L11;
            r0.hasPresentationFrame = 2;
            goto L12
        L11:
            r0.hasCameraFrame = 2;
            goto L12
        L5:
            if (ProxyVideoSink.m5374$$Nest$fgettarget(r1) != r5) goto L7;
            VoIPService.m5339$$Nest$fgetproxyVideoSinkLruCache(this.this$0).put(r4, r1);
            VoIPService.m5340$$Nest$fgetremoteSinks(this.this$0).remove(r4);
            r1.setTarget(null);
            goto L7
        }

        @Override // org.webrtc.VideoSink
        public void onFrame(VideoFrame r3) {
            if (r3 != null) goto L4;
            return;
        L4:
            if (r3.getBuffer().getHeight() != 0) goto L6;
            return;
        L6:
            if (r3.getBuffer().getWidth() == 0) goto L11;
            AndroidUtilities.runOnUIThread(new VoIPService$5$$ExternalSyntheticLambda0(this, this.val$endpointId, this, this.val$screencast));
            return;
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends ConferenceCall {
        final /* synthetic */ VoIPService this$0;

        public AnonymousClass6(VoIPService r1, int r2, long r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.messenger.voip.ConferenceCall
        public void gotCallId(long r3) {
            int r0 = 0;
        L4:
            if (r0 >= 2) goto L9;
            if (VoIPService.m5345$$Nest$fgettgVoip(this.this$0)[r0] == null) goto L8;
            VoIPService.m5345$$Nest$fgettgVoip(this.this$0)[r0].setConferenceCallId(r3);
        L8:
            r0 = r0 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends ConferenceCall {
        final /* synthetic */ VoIPService this$0;

        public AnonymousClass7(VoIPService r1, int r2, long r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.messenger.voip.ConferenceCall
        public void gotCallId(long r3) {
            int r0 = 0;
        L4:
            if (r0 >= 2) goto L9;
            if (VoIPService.m5345$$Nest$fgettgVoip(this.this$0)[r0] == null) goto L8;
            VoIPService.m5345$$Nest$fgettgVoip(this.this$0)[r0].setConferenceCallId(r3);
        L8:
            r0 = r0 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ VoIPService this$0;

        public AnonymousClass8(VoIPService r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VoIPService.m5345$$Nest$fgettgVoip(this.this$0)[0] == null) goto L6;
            VoIPService r0 = this.this$0;
            VoIPService.m5365$$Nest$mupdateTrafficStats(r0, VoIPService.m5345$$Nest$fgettgVoip(r0)[0], null);
            AndroidUtilities.runOnUIThread(this, 5000);
            return;
        }
    }

    /* renamed from: org.telegram.messenger.voip.VoIPService$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 implements Runnable {
        final /* synthetic */ VoIPService this$0;

        /* renamed from: $r8$lambda$uWfvA8tAH4UudA_CxCj-xiz00nk, reason: not valid java name */
        public static /* synthetic */ void m5372$r8$lambda$uWfvA8tAH4UudA_CxCjxiz00nk(AnonymousClass9 r0) {
            r0.lambda$run$0();
        }

        public AnonymousClass9(VoIPService r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0() {
            if (VoIPService.m5343$$Nest$fgetspPlayId(this.this$0) != 0) goto L6;
            VoIPService r0 = this.this$0;
            VoIPService.m5356$$Nest$fputspPlayId(r0, VoIPService.m5341$$Nest$fgetsoundPool(r0).play(VoIPService.m5342$$Nest$fgetspConnectingId(this.this$0), 1.0f, 1.0f, 0, -1, 1.0f));
        L6:
            if (VoIPService.m5343$$Nest$fgetspPlayId(this.this$0) != 0) goto L9;
            AndroidUtilities.runOnUIThread(this, 100);
            return;
        L9:
            VoIPService.m5349$$Nest$fputconnectingSoundRunnable(this.this$0, null);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VoIPService.m5367$$Nest$sfgetsharedInstance() != null) goto L5;
            return;
        L5:
            Utilities.globalQueue.postRunnable(new VoIPService$9$$ExternalSyntheticLambda0(this));
        }
    }

    /* loaded from: classes6.dex */
    public class CallConnection extends Connection {
        final /* synthetic */ VoIPService this$0;

        public CallConnection(VoIPService r1) {
            this.this$0 = r1;
            setConnectionProperties(128);
            setAudioModeIsVoip(true);
        }

        @Override // android.telecom.Connection
        public void onAnswer() {
            VoIPService.m5358$$Nest$macceptIncomingCallFromNotification(this.this$0);
        }

        @Override // android.telecom.Connection
        public void onCallAudioStateChanged(CallAudioState r3) {
            if (BuildVars.LOGS_ENABLED == false) goto L5;
            FileLog.d("ConnectionService call audio state changed: " + r3);
        L5:
            ArrayList r2 = VoIPService.m5344$$Nest$fgetstateListeners(this.this$0);
            int r32 = r2.size();
            int r0 = 0;
        L6:
            if (r0 >= r32) goto L8;
            Object r1 = r2.get(r0);
            r0 = r0 + 1;
            ((StateListener) r1).onAudioSettingsChanged();
            goto L6
        }

        @Override // android.telecom.Connection
        public void onCallEvent(String r1, Bundle r2) {
            super.onCallEvent(r1, r2);
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.d("ConnectionService onCallEvent " + r1);
            return;
        }

        @Override // android.telecom.Connection
        public void onDisconnect() {
            if (BuildVars.LOGS_ENABLED == false) goto L5;
            FileLog.d("ConnectionService onDisconnect");
        L5:
            setDisconnected(new DisconnectCause(2));
            destroy();
            VoIPService.m5357$$Nest$fputsystemCallConnection(this.this$0, null);
            this.this$0.hangUp();
        }

        @Override // android.telecom.Connection
        public void onReject() {
            VoIPService.m5353$$Nest$fputneedPlayEndSound(this.this$0, false);
            this.this$0.declineIncomingCall(1, null);
        }

        @Override // android.telecom.Connection
        public void onShowIncomingCallUi() {
            VoIPService.m5362$$Nest$mstartRinging(this.this$0);
        }

        @Override // android.telecom.Connection
        public void onSilence() {
            if (BuildVars.LOGS_ENABLED == false) goto L5;
            FileLog.d("onSlience");
        L5:
            this.this$0.stopRinging();
        }

        @Override // android.telecom.Connection
        public void onStateChanged(int r3) {
            super.onStateChanged(r3);
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.d("ConnectionService onStateChanged " + Connection.stateToString(r3));
        L6:
            if (r3 != 4) goto L9;
            ContactsController.getInstance(VoIPService.m5333$$Nest$fgetcurrentAccount(this.this$0)).deleteConnectionServiceContact();
            VoIPService.m5350$$Nest$fputdidDeleteConnectionServiceContact(this.this$0, true);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class ProxyVideoSink implements VideoSink {
        private VideoSink background;
        private long nativeInstance;
        private VideoSink target;

        /* renamed from: -$$Nest$fgetnativeInstance, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m5373$$Nest$fgetnativeInstance(ProxyVideoSink r2) {
            return r2.nativeInstance;
        }

        /* renamed from: -$$Nest$fgettarget, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VideoSink m5374$$Nest$fgettarget(ProxyVideoSink r0) {
            return r0.target;
        }

        /* renamed from: -$$Nest$fputnativeInstance, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5375$$Nest$fputnativeInstance(ProxyVideoSink r0, long r1) {
            r0.nativeInstance = r1;
        }

        public ProxyVideoSink() {
        }

        @Override // org.webrtc.VideoSink
        public synchronized void onFrame(VideoFrame r2) {
            monitor-enter(this);
            VideoSink r0 = this.target;     // Catch: Throwable -> L6
            if (r0 == null) goto L8;
            r0.onFrame(r2);     // Catch: Throwable -> L6
        L8:
            VideoSink r02 = this.background;     // Catch: Throwable -> L6
            if (r02 == null) goto L11;
            r02.onFrame(r2);     // Catch: Throwable -> L6
        L11:
            monitor-exit(this);
            return;
        L6:
            th = move-exception;
            throw th;
        }

        public synchronized void removeBackground(VideoSink r2) {
            monitor-enter(this);
        L7:
            th = move-exception;
            throw th;
        L4:
            if (this.background != r2) goto L9;
            this.background = null;     // Catch: Throwable -> L7
        L9:
            monitor-exit(this);
        }

        public synchronized void removeTarget(VideoSink r2) {
            monitor-enter(this);
        L7:
            th = move-exception;
            throw th;
        L4:
            if (this.target != r2) goto L9;
            this.target = null;     // Catch: Throwable -> L7
        L9:
            monitor-exit(this);
        }

        public synchronized void setBackground(VideoSink r3) {
            monitor-enter(this);
            VideoSink r0 = this.background;     // Catch: Throwable -> L6
            if (r0 == null) goto L8;
            r0.setParentSink(null);     // Catch: Throwable -> L6
        L8:
            this.background = r3;     // Catch: Throwable -> L6
            if (r3 == null) goto L11;
            r3.setParentSink(this);     // Catch: Throwable -> L6
        L11:
            monitor-exit(this);
            return;
        L6:
            th = move-exception;
            throw th;
        }

        public synchronized void setTarget(VideoSink r3) {
            monitor-enter(this);
            VideoSink r0 = this.target;     // Catch: Throwable -> L7
            if (r0 == r3) goto L12;
            if (r0 == null) goto L9;
            r0.setParentSink(null);     // Catch: Throwable -> L7
        L9:
            this.target = r3;     // Catch: Throwable -> L7
            if (r3 == null) goto L12;
            r3.setParentSink(this);     // Catch: Throwable -> L7
        L12:
            monitor-exit(this);
            return;
        L7:
            th = move-exception;
            throw th;
        }

        public synchronized void swap() {
            monitor-enter(this);
        L9:
            th = move-exception;
            throw th;
        L4:
            if (this.target == null) goto L11;
            VideoSink r0 = this.background;     // Catch: Throwable -> L9
            if (r0 == null) goto L11;
            this.target = r0;     // Catch: Throwable -> L9
            this.background = null;     // Catch: Throwable -> L9
        L11:
            monitor-exit(this);
        }
    }

    /* loaded from: classes6.dex */
    public static class RequestedParticipant {
        public int audioSsrc;
        public TLRPC.GroupCallParticipant participant;
        public long userId;

        public RequestedParticipant(TLRPC.GroupCallParticipant r1, int r2) {
            this.participant = r1;
            this.audioSsrc = r2;
            if (r1 != null) goto L5;
            long r12 = 0;
        L6:
            this.userId = r12;
            return;
        L5:
            r12 = DialogObject.getPeerDialogId(r1.peer);
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class SharedUIParams {
        public boolean cameraAlertWasShowed;
        public boolean tapToVideoTooltipWasShowed;
        public boolean wasVideoCall;

        public SharedUIParams() {
        }
    }

    public interface StateListener {
        default void onAudioSettingsChanged() {
        }

        default void onCameraFirstFrameAvailable() {
        }

        default void onCameraSwitch(boolean r1) {
        }

        default void onMediaStateUpdated(int r1, int r2) {
        }

        default void onScreenOnChange(boolean r1) {
        }

        default void onSignalBarsCountChanged(int r1) {
        }

        default void onStateChanged(int r1) {
        }

        default void onVideoAvailableChange(boolean r1) {
        }
    }

    /* renamed from: $r8$lambda$-LMM4VTggXedA1I93-CbEOkk4oM, reason: not valid java name */
    public static /* synthetic */ void m5290$r8$lambda$LMM4VTggXedA1I93CbEOkk4oM(VoIPService r0) {
        r0.lambda$callEnded$122();
    }

    /* renamed from: $r8$lambda$-TGzS3de2y2GXOnJjtWIv6Qfp3s, reason: not valid java name */
    public static /* synthetic */ void m5291$r8$lambda$TGzS3de2y2GXOnJjtWIv6Qfp3s(VoIPService r0) {
        r0.lambda$startOutgoingCall$8();
    }

    /* renamed from: $r8$lambda$-byv7mZ2Kot_Tc_VtFvu_ZfobvQ, reason: not valid java name */
    public static /* synthetic */ void m5292$r8$lambda$byv7mZ2Kot_Tc_VtFvu_ZfobvQ(VoIPService r0) {
        r0.lambda$callEnded$125();
    }

    /* renamed from: $r8$lambda$-c5BB_eYeJTsYOlmaC4XfUN1L3Y, reason: not valid java name */
    public static /* synthetic */ void m5293$r8$lambda$c5BB_eYeJTsYOlmaC4XfUN1L3Y(VoIPService r0, TLObject r1, TLRPC.TL_error r2, boolean r3) {
        r0.lambda$acknowledgeCall$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$06YRuYKKM3tlqdIa3vozWSDip78(VoIPService r0) {
        r0.lambda$startGroupCheckShortpoll$65();
    }

    /* renamed from: $r8$lambda$1-UBYe_43GELGdc9ogCao9Gm5gQ, reason: not valid java name */
    public static /* synthetic */ void m5294$r8$lambda$1UBYe_43GELGdc9ogCao9Gm5gQ(VoIPService r0) {
        r0.lambda$setMicMute$0();
    }

    public static /* synthetic */ void $r8$lambda$1ly1jmHlhLuc9kuiLLpLbG8zSJo(VoIPService r0) {
        r0.lambda$loadResources$108();
    }

    public static /* synthetic */ void $r8$lambda$27W1KRAozv94PnFOae_9RlTLvGQ(VoIPService r0, int r1, long r2) {
        r0.lambda$createGroupInstance$79(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$29luzWQvnlZJk_3OHYI50FY9E0M(VoIPService r0, int r1) {
        r0.lambda$startScreenCapture$57(r1);
    }

    /* renamed from: $r8$lambda$2T2NcP6lV_shD2UpykOoyD-_UMQ, reason: not valid java name */
    public static /* synthetic */ void m5295$r8$lambda$2T2NcP6lV_shD2UpykOoyD_UMQ(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$declineIncomingCall$105(r1, r2);
    }

    /* renamed from: $r8$lambda$2gxa-WkHDapj3Uwc8609F6-Aoz0, reason: not valid java name */
    public static /* synthetic */ void m5296$r8$lambda$2gxaWkHDapj3Uwc8609F6Aoz0(VoIPService r0, HashSet r1, String r2) {
        r0.lambda$startConferenceGroupCall$50(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3REtvzLrtKUQk5xXlWXZdADrrRo(VoIPService r0, int r1) {
        r0.lambda$createGroupInstance$72(r1);
    }

    /* renamed from: $r8$lambda$4xpdNSKJx3Kxc-KibUmDtkDxFEE, reason: not valid java name */
    public static /* synthetic */ void m5297$r8$lambda$4xpdNSKJx3KxcKibUmDtkDxFEE(VoIPService r0, TLRPC.TL_error r1) {
        r0.lambda$startGroupCall$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$5JBRIYmGQmbXzSkfU9FZA9Ri2_g(VoIPService r0) {
        r0.lambda$onStartCommand$2();
    }

    public static /* synthetic */ void $r8$lambda$6GVqzEJMWq3WiObgCgfMD2HDXrU(VoIPService r0, TLRPC.TL_error r1, TLObject r2, byte[] r3) {
        r0.lambda$startOutgoingCall$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$6P4y55lb4CHVSJNpimBSZpuEf1s(VoIPService r0, ArrayList r1, ArrayList r2, ArrayList r3, String r4) {
        r0.lambda$startConferenceGroupCall$39(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$7S2yJveJT7prIA3NBnGfQRhiWOQ(VoIPService r0, int r1, int r2) {
        r0.lambda$initiateActualEncryptedCall$87(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$87ZJl7q4GLseCatNMkNKwv977oM(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$hangUp$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8flR6LY2Un51Ga4nG2urXHgqpcI(VoIPService r0) {
        r0.destroyConverting();
    }

    public static /* synthetic */ void $r8$lambda$9GHNw81Xk6LvUhSQUwiwCoj_Pm4(VoIPService r0, long r1, HashSet r3, AtomicInteger r4, int r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$startConferenceGroupCall$43(r1, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$9Jqe34Oi-rU03GhAmkqxkTihwC4, reason: not valid java name */
    public static /* synthetic */ void m5298$r8$lambda$9Jqe34OirU03GhAmkqxkTihwC4(VoIPService r0) {
        r0.lambda$playAllowTalkSound$120();
    }

    /* renamed from: $r8$lambda$A-yS__D7hvOqjWCAFn0_FYB9LwM, reason: not valid java name */
    public static /* synthetic */ void m5299$r8$lambda$AyS__D7hvOqjWCAFn0_FYB9LwM(VoIPService r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$startScreenCapture$60(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$ANJ0NlBXaBr-a-sqBSfsbJds8h8, reason: not valid java name */
    public static /* synthetic */ void m5300$r8$lambda$ANJ0NlBXaBrasqBSfsbJds8h8(VoIPService r0, int r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$startGroupCall$29(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$AyTVPfsD5dZmnHuNE58dIC6PWQU(VoIPService r0) {
        r0.lambda$initiateActualEncryptedCall$84();
    }

    public static /* synthetic */ void $r8$lambda$BTWdBaT0phzcw5DajmHWR108Fwk(VoIPService r0) {
        r0.lambda$endConnectionServiceCall$126();
    }

    /* renamed from: $r8$lambda$CdsTH6ytWYiqx3bxu-IoGuYtNsM, reason: not valid java name */
    public static /* synthetic */ void m5301$r8$lambda$CdsTH6ytWYiqx3bxuIoGuYtNsM(VoIPService r0, AudioManager r1) {
        r0.lambda$configureDeviceForCall$111(r1);
    }

    public static /* synthetic */ void $r8$lambda$D6iMJw_ox5Eogc8CiI_jQgMjtiA() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didStartedCall, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$DAE0FNhUor_NoQQBBvxJ9pXxnzk(VoIPService r0, TL_phone.PhoneCall r1, TL_phone.exportGroupCallInvite r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$startConferenceGroupCall$45(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$DuFfJzB4YhcpLlNOVXV8-nozA9Y, reason: not valid java name */
    public static /* synthetic */ void m5302$r8$lambda$DuFfJzB4YhcpLlNOVXV8nozA9Y(VoIPService r0, TLRPC.TL_error r1) {
        r0.lambda$startScreenCapture$59(r1);
    }

    public static /* synthetic */ void $r8$lambda$E5rDrTp9Td2_AhnvyRtiSASzdL0(AudioManager r2) {
        Object r0 = sync;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (setModeRunnable != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        setModeRunnable = null;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        r2.setMode(0);     // Catch: SecurityException -> L15
        return;
    L15:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L25;
        FileLog.e("Error setting audio more to normal", e);
        return;
    }

    public static /* synthetic */ void $r8$lambda$Eb1XQeks7UeZC1PpkYM1KvezHig(VoIPService r0, int r1) {
        r0.lambda$startGroupCall$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$ErHQ4ozuYCQb8dlkkO5Y5wROLuw(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$startOutgoingCall$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Exg2vDPsIfjuAeEtpXIz_LPKTbQ(ArrayList r0, ArrayList r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda0(r3, r0, r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$FzV2DSw3y93_6YhO3oMErqiB7vM(VoIPService r0, TLObject r1, AccountInstance r2, TLRPC.TL_error r3) {
        r0.lambda$startConferenceGroupCall$31(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$GKxMpf0Eq1Q3YX1HRh0kfcvDlYU(VoIPService r0, int r1) {
        r0.lambda$onSignalBarCountChanged$121(r1);
    }

    /* renamed from: $r8$lambda$HIX0weVwPpoX9zNFJY-XIS5UHwk, reason: not valid java name */
    public static /* synthetic */ void m5303$r8$lambda$HIX0weVwPpoX9zNFJYXIS5UHwk(VoIPService r0, TLRPC.Updates r1, long r2) {
        r0.lambda$startConferenceGroupCall$38(r1, r2);
    }

    /* renamed from: $r8$lambda$I0BW4PrKezi-ctal1T2tx-gLZeg, reason: not valid java name */
    public static /* synthetic */ void m5304$r8$lambda$I0BW4PrKezictal1T2txgLZeg(VoIPService r0, HashSet r1, String r2) {
        r0.lambda$startConferenceGroupCall$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IFIcuop7FQaZMOPy_I39ScNq7f4(VoIPService r0, int r1, long r2, int r4) {
        r0.lambda$createGroupInstance$76(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$JY507Z2mOyF1Ul504LfPvBc7T8s(VoIPService r0, TLRPC.Updates r1) {
        r0.lambda$startScreenCapture$58(r1);
    }

    /* renamed from: $r8$lambda$KcZNzNgeE-vihwdYGwW6fSntRxA, reason: not valid java name */
    public static /* synthetic */ void m5305$r8$lambda$KcZNzNgeEvihwdYGwW6fSntRxA() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didStartedCall, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$LEgsqGsxwUoNpD5sCXFNkphK3nE(VoIPService r0) {
        r0.lambda$setupCaptureDevice$14();
    }

    public static /* synthetic */ void $r8$lambda$LyeHwxqXJo9WxUQwu2f5NYM_tEw(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$startGroupCheckShortpoll$62(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MKcNtjRK5UH7zVqEW5hdMniMNXw(VoIPService r0) {
        r0.lambda$playStartRecordSound$119();
    }

    public static /* synthetic */ void $r8$lambda$MlcvCdr25EFwFXZaNB8l7yzQbsQ(VoIPService r0) {
        r0.lambda$onDestroy$99();
    }

    public static /* synthetic */ void $r8$lambda$NErILFI89a3UdFutyC9BL4n66LQ(VoIPService r0) {
        r0.lambda$declineIncomingCall$104();
    }

    public static /* synthetic */ void $r8$lambda$P8XeT5XImmvS6mi6z5zzcp8LAVk(TLObject r3, ArrayList r4, ArrayList r5, Runnable r6) {
        if ((r3 instanceof Vector) == false) goto L11;
        Vector r32 = (Vector) r3;
        int r0 = 0;
    L6:
        if (r0 >= Math.min(r4.size(), r32.objects.size())) goto L11;
        if ((r32.objects.get(r0) instanceof TL_account.requirementToContactPremium) == false) goto L10;
        r5.add(Long.valueOf(((TLRPC.User) r4.get(r0)).id));
    L10:
        r0 = r0 + 1;
    L11:
        r6.run();
    }

    public static /* synthetic */ void $r8$lambda$PMdSfYxBjtSkVPxjvzGjxxMbHqw(VoIPService r0, int[] r1, float[] r2, boolean[] r3) {
        r0.lambda$initiateActualEncryptedCall$85(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$PaP-BzqvxL3xvJckB30htjhFQH0, reason: not valid java name */
    public static /* synthetic */ void m5306$r8$lambda$PaPBzqvxL3xvJckB30htjhFQH0(TLObject r3, ArrayList r4, ArrayList r5, Runnable r6) {
        if ((r3 instanceof Vector) == false) goto L11;
        Vector r32 = (Vector) r3;
        int r0 = 0;
    L6:
        if (r0 >= Math.min(r4.size(), r32.objects.size())) goto L11;
        if ((r32.objects.get(r0) instanceof TL_account.requirementToContactPremium) == false) goto L10;
        r5.add(Long.valueOf(((TLRPC.User) r4.get(r0)).id));
    L10:
        r0 = r0 + 1;
    L11:
        r6.run();
    }

    /* renamed from: $r8$lambda$PrtkR3Cutn72ujUfBu2yrl-Mi2Y, reason: not valid java name */
    public static /* synthetic */ void m5307$r8$lambda$PrtkR3Cutn72ujUfBu2yrlMi2Y(VoIPService r0) {
        r0.lambda$playConnectedSound$88();
    }

    /* renamed from: $r8$lambda$PuCZxzce8swtU-pKnTfkdGhCMtI, reason: not valid java name */
    public static /* synthetic */ void m5308$r8$lambda$PuCZxzce8swtUpKnTfkdGhCMtI(VoIPService r0, TLRPC.GroupCallParticipant r1) {
        r0.lambda$startConferenceGroupCall$37(r1);
    }

    public static /* synthetic */ void $r8$lambda$Q3cDNlNBUBK592xAlGGazwfbMkI(VoIPService r0) {
        r0.lambda$onConnectionStateChanged$116();
    }

    public static /* synthetic */ void $r8$lambda$R0RWQTd4j2SM8liI7TXJVbAWJ7E(ArrayList r0, ArrayList r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda116(r3, r0, r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$R3gjGblQyvVRBILMM3ra5l1skG4(VoIPService r0) {
        r0.lambda$callEnded$123();
    }

    public static /* synthetic */ void $r8$lambda$RUsHnqb518fy1hGjkFXNU8CvzLk(VoIPService r0, int r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$startConferenceGroupCall$34(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$TJyjMZU4nhpSa8LXneFTqKThVT0(VoIPService r0) {
        r0.lambda$callFailed$115();
    }

    public static /* synthetic */ void $r8$lambda$Th4iaD1c4X1DsGtbwwD7IR8H2pA() {
        if (MediaController.getInstance().isMessagePaused() == true) goto L6;
        MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    }

    /* renamed from: $r8$lambda$Tiqcc5XI9h3XlrC-BWkbWhlt9TQ, reason: not valid java name */
    public static /* synthetic */ void m5309$r8$lambda$Tiqcc5XI9h3XlrCBWkbWhlt9TQ(VoIPService r0, Boolean r1, Boolean r2) {
        r0.lambda$toggleSpeakerphoneOrShowRouteSheet$95(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$U8Yc7OZ1_b2lEjfpsAMio7i0k6Q(VoIPService r0, TLRPC.GroupCallParticipant r1) {
        r0.lambda$startGroupCall$26(r1);
    }

    public static /* synthetic */ void $r8$lambda$Uidj1P3stPhlo1WRSQ3lEbFj0Vk(VoIPService r0, TLObject r1, TL_phone.PhoneCall r2, TL_phone.exportGroupCallInvite r3) {
        r0.lambda$startConferenceGroupCall$44(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$UuJdQ0YDkgUUoumGVbWpiiGFMVA(VoIPService r0, MediaPlayer r1) {
        r0.lambda$startRingtoneAndVibration$96(r1);
    }

    /* renamed from: $r8$lambda$VCq2r4K4Hgj3QFJS-VewPs9UUgw, reason: not valid java name */
    public static /* synthetic */ void m5310$r8$lambda$VCq2r4K4Hgj3QFJSVewPs9UUgw(VoIPService r0, TLObject r1, int r2, String r3, TLRPC.TL_error r4) {
        r0.lambda$startConferenceGroupCall$33(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$VRTrTQwre7LAsx1UqmpXaKl7yE4() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didStartedCall, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$W5h6GgU3i7de6Ngr2ZwjJQb1Fek(VoIPService r0, Boolean r1, Boolean r2) {
        r0.lambda$switchToSpeaker$91(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WgcCr_wFZXL2_IjAp7oWQkDW35A(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$hangUp$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XKkPF1dGRKUqZJSuAz7Z2p9Y4vc(VoIPService r0, int r1) {
        r0.lambda$startConferenceGroupCall$36(r1);
    }

    /* renamed from: $r8$lambda$XufQx4k_OXvrPNGioYM-1748SsM, reason: not valid java name */
    public static /* synthetic */ void m5311$r8$lambda$XufQx4k_OXvrPNGioYM1748SsM(VoIPService r0, long r1, HashSet r3, AtomicInteger r4, int r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$startConferenceGroupCall$51(r1, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$Xv9Dim96d0f3v8pDpABX3DCWf8A(VoIPService r0, int r1, long r2, long r4, int r6, int r7) {
        r0.lambda$createGroupInstance$75(r1, r2, r4, r6, r7);
    }

    /* renamed from: $r8$lambda$Y-t9XPVsR3i49ymkh2fmq4QovNo, reason: not valid java name */
    public static /* synthetic */ void m5312$r8$lambda$Yt9XPVsR3i49ymkh2fmq4QovNo(VoIPService r0, int r1, int r2) {
        r0.lambda$initiateActualEncryptedCall$86(r1, r2);
    }

    /* renamed from: $r8$lambda$Y22RmUQBKXm_Togaq8vm-tskQ-c, reason: not valid java name */
    public static /* synthetic */ void m5313$r8$lambda$Y22RmUQBKXm_Togaq8vmtskQc(VoIPService r0, TLRPC.TL_error r1, TLObject r2, TL_phone.checkGroupCall r3) {
        r0.lambda$startGroupCheckShortpoll$63(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$YoNN3N6wsYwvfTmw_NCmTkFH56U(VoIPService r0, int r1, int r2, boolean r3) {
        r0.lambda$createGroupInstance$80(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Z16fd4labPVpeU66f9xb5LJc3dQ(VoIPService r0) {
        r0.lambda$convertToConferenceCall$30();
    }

    public static /* synthetic */ void $r8$lambda$ZHRNPE8VXUx_YTUc6WSWfNoIECg(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$stopScreenCapture$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZhwP3P56SiX4nUzlHAQjJCcSbG8(VoIPService r0) {
        r0.lambda$callFailed$114();
    }

    /* renamed from: $r8$lambda$__mwNE_FIW7LC18Sm3bBkRzE-00, reason: not valid java name */
    public static /* synthetic */ void m5314$r8$lambda$__mwNE_FIW7LC18Sm3bBkRzE00(VoIPService r0, TL_update.TL_updateGroupCall r1) {
        r0.lambda$startGroupCall$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$b1b4JQ7Bu6x3Ct0HoggGBR07xdc(VoIPService r0) {
        r0.lambda$getConnectionAndStartCall$106();
    }

    public static /* synthetic */ void $r8$lambda$b4DlqMVKgr233LhboIWL7iCqnmc(VoIPService r0, MessagesStorage r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$acceptIncomingCall$103(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$bRVI5mHd3plFsVISrxbGUcjMxrg(VoIPService r0, DialogInterface r1, int r2) {
        r0.lambda$toggleSpeakerphoneOrShowRouteSheet$93(r1, r2);
    }

    /* renamed from: $r8$lambda$bVYKwYl9CZaYuDDNdm6RzN-j1C4, reason: not valid java name */
    public static /* synthetic */ void m5315$r8$lambda$bVYKwYl9CZaYuDDNdm6RzNj1C4(VoIPService r0) {
        r0.lambda$onConnectionStateChanged$117();
    }

    public static /* synthetic */ void $r8$lambda$bmGBbHnoBGx3lXj5xBcH53TuSVg(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$acceptIncomingCall$102(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$c59EoZqSaKuauUww2BxbBhO7qPE(VoIPService r0, int r1) {
        r0.lambda$onConnectionStateChanged$118(r1);
    }

    /* renamed from: $r8$lambda$e-hpzHbar0wrE5t2DJBpcihOqlU, reason: not valid java name */
    public static /* synthetic */ void m5316$r8$lambda$ehpzHbar0wrE5t2DJBpcihOqlU(BottomSheet r2, Integer r3, DialogInterface r4) {
        int r42 = 0;
    L4:
        if (r42 >= r2.getItemViews().size()) goto L6;
        int r0 = Theme.key_dialogTextBlack;
        r2.setItemColor(r42, Theme.getColor(r0), Theme.getColor(r0));
        r42 = r42 + 1;
        goto L4
    L6:
        if (r3 == null) goto L10;
        int r43 = Theme.getColor(Theme.key_dialogTextLink);
        r2.setItemColor(r3.intValue(), r43, r43);
        return;
    }

    /* renamed from: $r8$lambda$eN-Kh-IeT7JmsCoHCpo43PWKWk4, reason: not valid java name */
    public static /* synthetic */ void m5317$r8$lambda$eNKhIeT7JmsCoHCpo43PWKWk4(VoIPService r0, byte[] r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$startOutgoingCall$10(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$eNhqe29y97b2bEBmhx-iZ0ndLVw, reason: not valid java name */
    public static /* synthetic */ void m5318$r8$lambda$eNhqe29y97b2bEBmhxiZ0ndLVw(VoIPService r0, ArrayList r1, ArrayList r2, ArrayList r3, String r4) {
        r0.lambda$startConferenceGroupCall$47(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$f69uEnJq2c4csRDfAYQao7eLYk0(VoIPService r0) {
        r0.lambda$startConnectingSound$89();
    }

    public static /* synthetic */ void $r8$lambda$fZzfGuC9ONaZh0JjhJFNELCM1RI() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didStartedCall, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$feY0LVblzovkT7_O03BJwx5nPMg(VoIPService r0, String r1) {
        r0.lambda$createGroupInstance$71(r1);
    }

    public static /* synthetic */ void $r8$lambda$fv1sqAhZ2aUYVg0HRmQOuLqFHl4(VoIPService r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$acknowledgeCall$13(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$gz5oEAgXgL-aizd1CtMBet4pAgM, reason: not valid java name */
    public static /* synthetic */ void m5319$r8$lambda$gz5oEAgXgLaizd1CtMBet4pAgM(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$startGroupCall$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hPDB1E04mMX0qn86iuYDNMPDhe0(VoIPService r0, int r1, long r2, long r4, int r6, int r7) {
        r0.lambda$createGroupInstance$77(r1, r2, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$iESHRsor6F1hLqlZoe5bmkSpYYQ(VoIPService r0) {
        r0.lambda$updateConnectionState$83();
    }

    public static /* synthetic */ void $r8$lambda$ihf_TgQlMZ9hVpHd61S65tYUha4(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$ingKR-mMCA2HuWFq0wUx1N71Y6Q, reason: not valid java name */
    public static /* synthetic */ void m5320$r8$lambda$ingKRmMCA2HuWFq0wUx1N71Y6Q(VoIPService r0) {
        r0.callFailed();
    }

    public static /* synthetic */ void $r8$lambda$iqEDHjWly5k2UDVwkZYvvNthBqg(VoIPService r0, AudioManager r1) {
        r0.lambda$configureDeviceForCall$110(r1);
    }

    /* renamed from: $r8$lambda$ir5EBfx5tDQC9-FGS808GiweaYc, reason: not valid java name */
    public static /* synthetic */ void m5321$r8$lambda$ir5EBfx5tDQC9FGS808GiweaYc(VoIPService r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$processAcceptedCall$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jHiCYeiotYjgxopRDBic2cf3fJ0(VoIPService r0, Runnable r1) {
        r0.lambda$startConferenceGroupCall$56(r1);
    }

    public static /* synthetic */ void $r8$lambda$jZFehqzaUIesqewFFJiPs9l1GiY(VoIPService r0, int r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$editCallMember$90(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$jqdn58WfB0C10sYgHCGkJQkmLw0(VoIPService r0, ArrayList r1, boolean r2) {
        r0.lambda$startConferenceGroupCall$52(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kCANyNPK7miaGEPKE1XFGNoxLLE(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processAcceptedCall$20(r1, r2);
    }

    /* renamed from: $r8$lambda$kv7ys1G-9IeL53MlauHSeLn-Pe0, reason: not valid java name */
    public static /* synthetic */ void m5322$r8$lambda$kv7ys1G9IeL53MlauHSeLnPe0(VoIPService r0) {
        r0.lambda$onCallUpdated$17();
    }

    public static /* synthetic */ void $r8$lambda$ky0Ni3_eN3vaYNjuGuhVV0p6TQM(SharedPreferences r0, TLObject r1, TLRPC.TL_error r2) {
        if (r2 != null) goto L5;
        String r12 = ((TLRPC.TL_dataJSON) r1).data;
        Instance.setGlobalServerConfig(r12);
        r0.edit().putString("voip_server_config", r12).apply();
        return;
    }

    public static /* synthetic */ void $r8$lambda$lBjBsnAWPGbEFErKrf_eL4LyAE4(VoIPService r0) {
        r0.lambda$onStartCommand$3();
    }

    public static /* synthetic */ void $r8$lambda$m0f3dcDoangzh_Y1l7FfqObqFRU(VoIPService r0) {
        r0.lambda$onCallUpdated$16();
    }

    public static /* synthetic */ void $r8$lambda$mfaJHHH9FVeIVu3zDcLxJtiKe0o() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didEndCall, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$mi8HrxrwBjm3axO5vTSUpYeEd1w(VoIPService r0, int r1, long r2, int[] r4) {
        r0.lambda$createGroupInstance$70(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$nPa5udOQVYYVUKhSjbmn4PJZH74(VoIPService r0) {
        r0.lambda$callEnded$124();
    }

    /* renamed from: $r8$lambda$oAUDV6KeNWBEOl-GjOEHsMuncig, reason: not valid java name */
    public static /* synthetic */ void m5323$r8$lambda$oAUDV6KeNWBEOlGjOEHsMuncig(VoIPService r0, String r1, int r2, boolean r3) {
        r0.lambda$startConferenceGroupCall$55(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$qN6HCjTTJAV3pwMNo1mQyFRCfdk(TLObject r1, TLRPC.TL_error r2) {
        if (r2 == null) goto L8;
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.e("error on phone.discardCall: " + r2);
        return;
    L11:
        return;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("phone.discardCall " + r1);
        return;
    }

    /* renamed from: $r8$lambda$qqAIsdDet-_LNigi29u8llEimts, reason: not valid java name */
    public static /* synthetic */ void m5324$r8$lambda$qqAIsdDet_LNigi29u8llEimts(VoIPService r0, TLRPC.TL_error r1, String r2) {
        r0.lambda$startConferenceGroupCall$53(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rXckID538pwHsuKPUZps7wFdJCA(VoIPService r0, int r1) {
        r0.lambda$updateConnectionState$81(r1);
    }

    public static /* synthetic */ void $r8$lambda$rYsF6VFmW855vl0SlJRT16dM_y4(VoIPService r0, Boolean r1) {
        r0.lambda$onStartCommand$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$s686SG3ThYJ6h4utNwRS1s4uaBk(VoIPService r0) {
        r0.lambda$onCallUpdated$18();
    }

    /* renamed from: $r8$lambda$sFBbVN-B55Q64k4SagsvLNYkdwA, reason: not valid java name */
    public static /* synthetic */ void m5325$r8$lambda$sFBbVNB55Q64k4SagsvLNYkdwA(VoIPService r0) {
        r0.lambda$switchToSpeaker$92();
    }

    public static /* synthetic */ void $r8$lambda$sWHejkW7LP_rqBkIE2qYvoLg03k(VoIPService r0) {
        r0.lambda$updateConnectionState$82();
    }

    public static /* synthetic */ void $r8$lambda$t5a3VbjYfTUHBFqJYi3QA4lX7fQ(VoIPService r0, String r1, int r2) {
        r0.lambda$createGroupInstance$74(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tPV9733HwegDcXm_LqQG5ezlsQE(VoIPService r0, String r1, int r2, long r3, int r5, int r6, TLObject r7, TLRPC.TL_error r8, long r9) {
        r0.lambda$createGroupInstance$73(r1, r2, r3, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$trjMpOX_YYAAn-3ONat1EUaFojo, reason: not valid java name */
    public static /* synthetic */ void m5326$r8$lambda$trjMpOX_YYAAn3ONat1EUaFojo(VoIPService r0, MessagesStorage r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$startOutgoingCall$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$tx8C1bdO84VTtH_Xi4lNv8piiI4(VoIPService r0, int r1, boolean r2, String r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$startConferenceGroupCall$54(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$txW2W0IbOfDRAb7E4Jq2LhSQWI8(VoIPService r0, long r1, int[] r3, ArrayList r4) {
        r0.lambda$createGroupInstance$69(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$u1aa8110w_z_9PugOju3re3X5zo(VoIPService r0, AccountInstance r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$startConferenceGroupCall$32(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$uIuer4Vp1L9JAdBLZHHvhthmxkE(VoIPService r0, boolean r1) {
        r0.lambda$startGroupCall$27(r1);
    }

    /* renamed from: $r8$lambda$upqZLW-xDUYpkCk9z7VNVsNyu6E, reason: not valid java name */
    public static /* synthetic */ void m5327$r8$lambda$upqZLWxDUYpkCk9z7VNVsNyu6E(AudioManager r0) {
        r0.startBluetoothSco();     // Catch: Throwable -> L4
        return;
    }

    /* renamed from: $r8$lambda$vU-ja21ssNK4Lh-eXNmu3Z2DoPk, reason: not valid java name */
    public static /* synthetic */ void m5328$r8$lambda$vUja21ssNK4LheXNmu3Z2DoPk(VoIPService r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$startGroupCheckShortpoll$61(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wuP5F8kDIisuicXB2OkG0Gjh2Ug(VoIPService r0, TLRPC.Updates r1, long r2) {
        r0.lambda$startConferenceGroupCall$46(r1, r2);
    }

    /* renamed from: $r8$lambda$x1-h7GKPOdb9CIPz45j-TbzlaUg, reason: not valid java name */
    public static /* synthetic */ void m5329$r8$lambda$x1h7GKPOdb9CIPz45jTbzlaUg(VoIPService r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$acceptIncomingCall$101(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$x31hxO9DKR8ogXII_qhgh9Mv56U(VoIPService r0, int r1, int[] r2, float[] r3, boolean[] r4) {
        r0.lambda$createGroupInstance$68(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$yC4JEJgzOiuaYpwH4DJ7EpTxX_M(VoIPService r0, TL_phone.checkGroupCall r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$startGroupCheckShortpoll$64(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ydnD09NTI5JDQa2Iy9Zrlnme1Ig(VoIPService r0, int r1, boolean[] r2, int r3, String r4) {
        r0.lambda$createGroupInstance$66(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$zh8PENAnLlxMFG_ON5Z72Pc3QJ8(VoIPService r0, TLRPC.TL_error r1) {
        r0.lambda$startGroupCall$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$zkH3SpyycjE2jFzZrPKfN1FUano(VoIPService r0, int r1, long r2, TLObject r4, TLRPC.TL_error r5, long r6) {
        r0.lambda$createGroupInstance$78(r1, r2, r4, r5, r6);
    }

    /* renamed from: -$$Nest$fgetaudioDeviceCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioDeviceCallback m5330$$Nest$fgetaudioDeviceCallback(VoIPService r0) {
        return r0.audioDeviceCallback;
    }

    /* renamed from: -$$Nest$fgetbluetoothScoActive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5331$$Nest$fgetbluetoothScoActive(VoIPService r0) {
        return r0.bluetoothScoActive;
    }

    /* renamed from: -$$Nest$fgetbtAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BluetoothAdapter m5332$$Nest$fgetbtAdapter(VoIPService r0) {
        return r0.btAdapter;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5333$$Nest$fgetcurrentAccount(VoIPService r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetisBtHeadsetConnected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5334$$Nest$fgetisBtHeadsetConnected(VoIPService r0) {
        return r0.isBtHeadsetConnected;
    }

    /* renamed from: -$$Nest$fgetisHeadsetPlugged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5335$$Nest$fgetisHeadsetPlugged(VoIPService r0) {
        return r0.isHeadsetPlugged;
    }

    /* renamed from: -$$Nest$fgetneedSwitchToBluetoothAfterScoActivates, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5336$$Nest$fgetneedSwitchToBluetoothAfterScoActivates(VoIPService r0) {
        return r0.needSwitchToBluetoothAfterScoActivates;
    }

    /* renamed from: -$$Nest$fgetpreviousAudioOutput, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5337$$Nest$fgetpreviousAudioOutput(VoIPService r0) {
        return r0.previousAudioOutput;
    }

    /* renamed from: -$$Nest$fgetproximityWakelock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PowerManager.WakeLock m5338$$Nest$fgetproximityWakelock(VoIPService r0) {
        return r0.proximityWakelock;
    }

    /* renamed from: -$$Nest$fgetproxyVideoSinkLruCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LruCache m5339$$Nest$fgetproxyVideoSinkLruCache(VoIPService r0) {
        return r0.proxyVideoSinkLruCache;
    }

    /* renamed from: -$$Nest$fgetremoteSinks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m5340$$Nest$fgetremoteSinks(VoIPService r0) {
        return r0.remoteSinks;
    }

    /* renamed from: -$$Nest$fgetsoundPool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SoundPool m5341$$Nest$fgetsoundPool(VoIPService r0) {
        return r0.soundPool;
    }

    /* renamed from: -$$Nest$fgetspConnectingId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5342$$Nest$fgetspConnectingId(VoIPService r0) {
        return r0.spConnectingId;
    }

    /* renamed from: -$$Nest$fgetspPlayId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5343$$Nest$fgetspPlayId(VoIPService r0) {
        return r0.spPlayId;
    }

    /* renamed from: -$$Nest$fgetstateListeners, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5344$$Nest$fgetstateListeners(VoIPService r0) {
        return r0.stateListeners;
    }

    /* renamed from: -$$Nest$fgettgVoip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NativeInstance[] m5345$$Nest$fgettgVoip(VoIPService r0) {
        return r0.tgVoip;
    }

    /* renamed from: -$$Nest$fgetwaitingFrameParticipant, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m5346$$Nest$fgetwaitingFrameParticipant(VoIPService r0) {
        return r0.waitingFrameParticipant;
    }

    /* renamed from: -$$Nest$fputbluetoothScoActive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5347$$Nest$fputbluetoothScoActive(VoIPService r0, boolean r1) {
        r0.bluetoothScoActive = r1;
    }

    /* renamed from: -$$Nest$fputbluetoothScoConnecting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5348$$Nest$fputbluetoothScoConnecting(VoIPService r0, boolean r1) {
        r0.bluetoothScoConnecting = r1;
    }

    /* renamed from: -$$Nest$fputconnectingSoundRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5349$$Nest$fputconnectingSoundRunnable(VoIPService r0, Runnable r1) {
        r0.connectingSoundRunnable = r1;
    }

    /* renamed from: -$$Nest$fputdidDeleteConnectionServiceContact, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5350$$Nest$fputdidDeleteConnectionServiceContact(VoIPService r0, boolean r1) {
        r0.didDeleteConnectionServiceContact = r1;
    }

    /* renamed from: -$$Nest$fputisHeadsetPlugged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5351$$Nest$fputisHeadsetPlugged(VoIPService r0, boolean r1) {
        r0.isHeadsetPlugged = r1;
    }

    /* renamed from: -$$Nest$fputisProximityNear, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5352$$Nest$fputisProximityNear(VoIPService r0, boolean r1) {
        r0.isProximityNear = r1;
    }

    /* renamed from: -$$Nest$fputneedPlayEndSound, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5353$$Nest$fputneedPlayEndSound(VoIPService r0, boolean r1) {
        r0.needPlayEndSound = r1;
    }

    /* renamed from: -$$Nest$fputneedSwitchToBluetoothAfterScoActivates, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5354$$Nest$fputneedSwitchToBluetoothAfterScoActivates(VoIPService r0, boolean r1) {
        r0.needSwitchToBluetoothAfterScoActivates = r1;
    }

    /* renamed from: -$$Nest$fputpreviousAudioOutput, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5355$$Nest$fputpreviousAudioOutput(VoIPService r0, int r1) {
        r0.previousAudioOutput = r1;
    }

    /* renamed from: -$$Nest$fputspPlayId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5356$$Nest$fputspPlayId(VoIPService r0, int r1) {
        r0.spPlayId = r1;
    }

    /* renamed from: -$$Nest$fputsystemCallConnection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5357$$Nest$fputsystemCallConnection(VoIPService r0, CallConnection r1) {
        r0.systemCallConnection = r1;
    }

    /* renamed from: -$$Nest$macceptIncomingCallFromNotification, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5358$$Nest$macceptIncomingCallFromNotification(VoIPService r0) {
        r0.acceptIncomingCallFromNotification();
    }

    /* renamed from: -$$Nest$mcheckUpdateBluetoothHeadset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5359$$Nest$mcheckUpdateBluetoothHeadset(VoIPService r0) {
        r0.checkUpdateBluetoothHeadset();
    }

    /* renamed from: -$$Nest$mfetchBluetoothDeviceName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5360$$Nest$mfetchBluetoothDeviceName(VoIPService r0) {
        r0.fetchBluetoothDeviceName();
    }

    /* renamed from: -$$Nest$mgetStatsNetworkType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5361$$Nest$mgetStatsNetworkType(VoIPService r0) {
        return r0.getStatsNetworkType();
    }

    /* renamed from: -$$Nest$mstartRinging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5362$$Nest$mstartRinging(VoIPService r0) {
        r0.startRinging();
    }

    /* renamed from: -$$Nest$mupdateBluetoothHeadsetState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5363$$Nest$mupdateBluetoothHeadsetState(VoIPService r0, boolean r1) {
        r0.updateBluetoothHeadsetState(r1);
    }

    /* renamed from: -$$Nest$mupdateNetworkType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5364$$Nest$mupdateNetworkType(VoIPService r0) {
        r0.updateNetworkType();
    }

    /* renamed from: -$$Nest$mupdateTrafficStats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5365$$Nest$mupdateTrafficStats(VoIPService r0, NativeInstance r1, Instance.TrafficStats r2) {
        r0.updateTrafficStats(r1, r2);
    }

    /* renamed from: -$$Nest$sfgetsetModeRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5366$$Nest$sfgetsetModeRunnable() {
        return setModeRunnable;
    }

    /* renamed from: -$$Nest$sfgetsharedInstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPService m5367$$Nest$sfgetsharedInstance() {
        return sharedInstance;
    }

    /* renamed from: -$$Nest$sfgetsync, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m5368$$Nest$sfgetsync() {
        return sync;
    }

    /* renamed from: -$$Nest$sfputsetModeRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5369$$Nest$sfputsetModeRunnable(Runnable r0) {
        setModeRunnable = r0;
    }

    static {
        USE_CONNECTION_SERVICE = isDeviceCompatibleWithConnectionServiceAPI();
        sync = new Object();
    }

    public VoIPService() {
        this.currentAccount = -1;
        this.currentState = 0;
        this.isFrontFaceCamera = true;
        this.previousAudioOutput = -1;
        this.stateListeners = new ArrayList();
        this.remoteVideoState = 0;
        this.mySource = new int[2];
        this.tgVoip = new NativeInstance[2];
        this.captureDevice = new long[2];
        this.destroyCaptureDevice = new boolean[]{true, true};
        this.videoState = new int[]{0, 0};
        this.remoteAudioState = 1;
        this.audioRouteToSet = 2;
        this.sharedUIParams = new SharedUIParams();
        this.pendingUpdates = new ArrayList();
        this.currentStreamRequestTimestamp = new HashMap();
        this.afterSoundRunnable = new AnonymousClass1(this);
        this.serviceListener = new AnonymousClass2(this);
        this.receiver = new AnonymousClass3(this);
        this.waitingFrameParticipant = new HashMap();
        this.proxyVideoSinkLruCache = new AnonymousClass4(this, 6);
        this.localSink = new ProxyVideoSink[2];
        this.remoteSink = new ProxyVideoSink[2];
        this.currentBackgroundSink = new ProxyVideoSink[2];
        this.currentBackgroundEndpointId = new String[2];
        this.remoteSinks = new HashMap();
        this.destroyConvertingRunnable = new VoIPService$$ExternalSyntheticLambda90(this);
    }

    private void acceptIncomingCallFromNotification() {
        showNotification();
        if (checkSelfPermission("android.permission.RECORD_AUDIO") == 0) goto L5;
    L23:
        PendingIntent.getActivity(this, 0, new Intent(this, VoIPPermissionActivity.class).addFlags(268435456), 1107296256).send();     // Catch: Exception -> L18
        return;
    L18:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L29;
        FileLog.e("Error starting permission activity", e);
        return;
    L29:
        return;
    L5:
        if (this.privateCall.video == true) goto L7;
    L9:
        acceptIncomingCall();
        PendingIntent.getActivity(this, 0, new Intent(this, getUIActivityClass()).setAction("voip"), 33554432).send();     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L27;
        FileLog.e("Error starting incall activity", e);
        return;
    L27:
        return;
    L7:
        if (checkSelfPermission("android.permission.CAMERA") == 0) goto L9;
        goto L9
    }

    private void acknowledgeCall(boolean r6) {
        TL_phone.PhoneCall r0 = this.privateCall;
        if (r0 != null) goto L10;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.w("Call is null, wtf");
    L7:
        stopSelf();
        return;
    L10:
        if ((r0 instanceof TL_phone.TL_phoneCallDiscarded) == false) goto L17;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.w("Call " + this.privateCall.id + " was discarded before the service started, stopping");
    L14:
        stopSelf();
        return;
    L17:
        if (XiaomiUtilities.isMIUI() == true) goto L19;
    L27:
        TL_phone.receivedCall r02 = new TL_phone.receivedCall();
        TLRPC.TL_inputPhoneCall r1 = new TLRPC.TL_inputPhoneCall();
        r02.peer = r1;
        TL_phone.PhoneCall r2 = this.privateCall;
        r1.id = r2.id;
        r1.access_hash = r2.access_hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new VoIPService$$ExternalSyntheticLambda94(this, r6), 65536);
        return;
    L19:
        if (XiaomiUtilities.isCustomPermissionGranted(XiaomiUtilities.OP_SHOW_WHEN_LOCKED) == true) goto L27;
        if (((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode() == false) goto L27;
        if (BuildVars.LOGS_ENABLED == false) goto L25;
        FileLog.e("MIUI: no permission to show when locked but the screen is locked. ¯\\_(ツ)_/¯");
    L25:
        stopSelf();
    }

    @TargetApi(26)
    private PhoneAccountHandle addAccountToTelecomManager() {
        TelecomManager r0 = (TelecomManager) getSystemService("telecom");
        TLRPC.User r1 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        PhoneAccountHandle r2 = new PhoneAccountHandle(new ComponentName(this, TelegramConnectionService.class), _UrlKt.FRAGMENT_ENCODE_SET + r1.id);
        r0.registerPhoneAccount(new PhoneAccount.Builder(r2, ContactsController.formatName(r1.first_name, r1.last_name)).setCapabilities(2048).setIcon(Icon.createWithResource(this, R.drawable.ic_launcher_dr)).setHighlightColor(-13851168).addSupportedUriScheme("sip").build());
        return r2;
    }

    private void broadcastUnknownParticipants(long r10, int[] r12) {
        if (this.groupCall != null) goto L5;
        return;
    L5:
        if (this.tgVoip[0] == null) goto L42;
        long r2 = getSelfId();
        int r0 = r12.length;
        ArrayList r4 = null;
        int r5 = 0;
    L8:
        if (r5 >= r0) goto L24;
        TLRPC.GroupCallParticipant r6 = this.groupCall.participantsBySources.get(r12[r5]);
        if (r6 != null) goto L14;
        r6 = this.groupCall.participantsByVideoSources.get(r12[r5]);
        if (r6 != null) goto L14;
        r6 = this.groupCall.participantsByPresentationSources.get(r12[r5]);
    L14:
        if (r6 == null) goto L23;
        if (MessageObject.getPeerId(r6.peer) == r2) goto L23;
        if (r6.source == 0) goto L23;
        if (r4 != null) goto L22;
        r4 = new ArrayList();
    L22:
        r4.add(new RequestedParticipant(r6, r12[r5]));
    L23:
        r5 = r5 + 1;
        goto L8
    L24:
        if (r4 == null) goto L41;
        this.tgVoip[0].onMediaDescriptionAvailable(r10, (RequestedParticipant[]) r4.toArray(new RequestedParticipant[0]));
        int r102 = r4.size();
        int r11 = 0;
    L26:
        if (r11 >= r102) goto L43;
        RequestedParticipant r122 = (RequestedParticipant) r4.get(r11);
        boolean r22 = r122.participant.muted_by_you;
        NativeInstance[] r3 = this.tgVoip;
        if (r22 == false) goto L30;
        r3[0].setVolume(r122.audioSsrc, 0.0d);
    L31:
        r11 = r11 + 1;
        goto L26
    L30:
        r3[0].setVolume(r122.audioSsrc, ChatObject.getParticipantVolume(r0) / 10000.0d);
        goto L31
    L43:
        return;
    L41:
        return;
    }

    private void callEnded() {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("Call " + getCallID() + " ended");
    L5:
        this.destroyCaptureDevice[0] = true;
        this.isCallEnded = true;
        if (this.groupCall != null) goto L8;
    L12:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda125(this));
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda126(this));
        Runnable r0 = this.connectingSoundRunnable;
        if (r0 == null) goto L15;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.connectingSoundRunnable = null;
    L15:
        int r3 = 700;
        if (this.needPlayEndSound == false) goto L22;
        this.playingSound = true;
        if (this.groupCall != null) goto L20;
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda127(this));
    L21:
        AndroidUtilities.runOnUIThread(this.afterSoundRunnable, r3);
        goto L22
    L20:
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda128(this), 100);
        r3 = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
    L22:
        Runnable r02 = this.timeoutRunnable;
        if (r02 == null) goto L26;
        AndroidUtilities.cancelRunOnUIThread(r02);
        this.timeoutRunnable = null;
    L26:
        if (this.needPlayEndSound == false) goto L28;
        long r03 = r3;
    L29:
        endConnectionServiceCall(r03);
        stopSelf();
        return;
    L28:
        r03 = 0;
        goto L29
    L8:
        if (this.playedConnectedSound == true) goto L10;
    L11:
        this.needPlayEndSound = false;
        goto L12
    L10:
        if (this.onDestroyRunnable == null) goto L12;
        goto L11
    }

    private void callFailed() {
        NativeInstance r0 = this.tgVoip[0];
        if (r0 == null) goto L5;
        String r02 = r0.getLastError();
    L6:
        callFailed(r02);
        return;
    L5:
        r02 = Instance.ERROR_UNKNOWN;
        goto L6
    }

    private void callFailed(String r9) {
        if (this.privateCall != null) goto L5;
    L27:
    L14:
        e = move-exception;
        FileLog.e(e);
        this.lastError = r9;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda120(this));
        if (TextUtils.equals(r9, Instance.ERROR_LOCALIZED) == false) goto L21;
        if (this.soundPool == null) goto L21;
        this.playingSound = true;
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda121(this));
        AndroidUtilities.runOnUIThread(this.afterSoundRunnable, 1000);
    L21:
        if (USE_CONNECTION_SERVICE == false) goto L25;
        CallConnection r92 = this.systemCallConnection;
        if (r92 == null) goto L25;
        r92.setDisconnected(new DisconnectCause(1));
        this.systemCallConnection.destroy();
        this.systemCallConnection = null;
    L25:
        stopSelf();
        return;
    L13:
        throw new Exception("Call " + getCallID() + " failed with error: " + r9);     // Catch: Exception -> L14
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("Discarding failed call");
    L7:
        TL_phone.discardCall r1 = new TL_phone.discardCall();
        TLRPC.TL_inputPhoneCall r4 = new TLRPC.TL_inputPhoneCall();
        r1.peer = r4;
        TL_phone.PhoneCall r5 = this.privateCall;
        r4.access_hash = r5.access_hash;
        r4.id = r5.id;
        r1.duration = (int) (getCallDuration() / 1000);
        NativeInstance r42 = this.tgVoip[0];
        if (r42 == null) goto L10;
        long r43 = r42.getPreferredRelayId();
    L11:
        r1.connection_id = r43;
        r1.reason = new TLRPC.TL_phoneCallDiscardReasonDisconnect();
        FileLog.e("discardCall " + r1.reason);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new VoIPService$$ExternalSyntheticLambda119());
        goto L27
    L10:
        r43 = 0;
        goto L11
    }

    private void cancelGroupCheckShortPoll() {
        int[] r0 = this.mySource;
        if (r0[1] == 0) goto L5;
        return;
    L5:
        if (r0[0] == 0) goto L8;
        return;
    L8:
        if (this.checkRequestId == 0) goto L10;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.checkRequestId, false);
        this.checkRequestId = 0;
    L10:
        Runnable r02 = this.shortPollRunnable;
        if (r02 == null) goto L15;
        AndroidUtilities.cancelRunOnUIThread(r02);
        this.shortPollRunnable = null;
        return;
    }

    private void checkIsNear() {
        if (this.remoteVideoState != 2) goto L5;
    L8:
        checkIsNear(false);
        return;
    L5:
        if (this.videoState[0] == 2) goto L8;
    }

    private void checkIsNear(boolean r3) {
        if (r3 != this.isProximityNear) goto L5;
        return;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("proximity " + r3);
    L7:
        this.isProximityNear = r3;
        PowerManager.WakeLock r2 = this.proximityWakelock;
        if (r3 == false) goto L11;
        r2.acquire();     // Catch: Exception -> L13
        return;
    L11:
        r2.release(1);     // Catch: Exception -> L13
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
    }

    private void checkUpdateBluetoothHeadset() {
        if (USE_CONNECTION_SERVICE == true) goto L26;
        BluetoothAdapter r0 = this.btAdapter;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isEnabled() == false) goto L28;
        MediaRouter r02 = (MediaRouter) getSystemService("media_router");     // Catch: Throwable -> L21
        AudioManager r1 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);     // Catch: Throwable -> L21
        boolean r2 = true;
        MediaRouter.RouteInfo r03 = r02.getSelectedRoute(1);     // Catch: Throwable -> L21
        if (PermissionRequest.hasPermission("android.permission.BLUETOOTH_CONNECT") == true) goto L11;
    L19:
        updateBluetoothHeadsetState(r1.isBluetoothA2dpOn());     // Catch: Throwable -> L21
        return;
    L11:
        if (r03.getDeviceType() != 3) goto L19;
        int r3 = 0;
        if (this.btAdapter.getProfileConnectionState(1) == 2) goto L16;
        r2 = false;
    L16:
        updateBluetoothHeadsetState(r2);     // Catch: Throwable -> L21
        ArrayList<StateListener> r4 = this.stateListeners;     // Catch: Throwable -> L21
        int r04 = r4.size();     // Catch: Throwable -> L21
    L17:
        if (r3 >= r04) goto L29;
        StateListener r12 = r4.get(r3);     // Catch: Throwable -> L21
        r3 = r3 + 1;     // Catch: Throwable -> L21
        r12.onAudioSettingsChanged();     // Catch: Throwable -> L21
        goto L17
    L29:
        return;
    L21:
        th = move-exception;
        FileLog.e(th);
        return;
    L28:
        return;
    }

    @SuppressLint({"InvalidWakeLockTag"})
    private void configureDeviceForCall() {
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.d("configureDeviceForCall, route to set = " + this.audioRouteToSet);
    L6:
        if (hasRtmpStream() == false) goto L8;
        int r0 = 1;
    L9:
        WebRtcAudioTrack.setAudioTrackUsageAttribute(r0);
        if (hasRtmpStream() == false) goto L12;
        int r02 = Integer.MIN_VALUE;
    L13:
        WebRtcAudioTrack.setAudioStreamType(r02);
        this.needPlayEndSound = true;
        AudioManager r03 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (USE_CONNECTION_SERVICE == true) goto L16;
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda86(this, r03));
    L16:
        SensorManager r04 = (SensorManager) ApplicationLoader.applicationContext.getSystemService("sensor");
        Sensor r1 = r04.getDefaultSensor(8);
        if (r1 == null) goto L27;
        this.proximityWakelock = ((PowerManager) getSystemService("power")).newWakeLock(32, "telegram-voip-prx");     // Catch: Exception -> L20
        r04.registerListener(this, r1, 3);     // Catch: Exception -> L20
        return;
    L20:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L28;
        FileLog.e("Error initializing proximity sensor", e);
        return;
    L28:
        return;
    L27:
        return;
    L12:
        r02 = 0;
        goto L13
    L8:
        r0 = 2;
        goto L9
    }

    private int convertDataSavingMode(int r1) {
        if (r1 == 3) goto L6;
        return r1;
    L6:
        return ApplicationLoader.isRoaming() ? 1 : 0;
    }

    public static String convertStreamToString(InputStream r2) {
        BufferedReader r0 = new BufferedReader(new InputStreamReader(r2));
        StringBuilder r22 = new StringBuilder();
    L3:
        String r1 = r0.readLine();
        if (r1 == null) goto L6;
        r22.append(r1);
        r22.append("\n");
        goto L3
    L6:
        r0.close();
        return r22.toString();
    }

    private void createGroupInstance(int r19, boolean r20, boolean r21) {
        if (r20 == false) goto L7;
        this.mySource[r19] = 0;
        if (r19 != 0) goto L7;
        this.switchingAccount = r20;
    L7:
        cancelGroupCheckShortPoll();
        if (r19 != 0) goto L11;
        this.wasConnected = false;
    L15:
        if (this.tgVoip[r19] == null) goto L17;
        int r202 = 1;
        boolean r2 = false;
    L47:
        this.tgVoip[r19].resetGroupInstance(!r2, false);
        ConferenceCall r22 = this.conference;
        if (r22 == null) goto L53;
        if (r22.getCallId() == (-1)) goto L53;
        this.tgVoip[r19].setConferenceCallId(this.conference.getCallId());
    L53:
        if (this.captureDevice[r19] == 0) goto L55;
        this.destroyCaptureDevice[r19] = false;
    L55:
        if (r19 != 0) goto L58;
        dispatchStateChanged(r202);
        return;
    L58:
        return;
    L17:
        if (this.groupCall == null) goto L19;
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET + this.groupCall.call.id;
    L26:
        boolean[] r5 = {r21};
        if (BuildVars.DEBUG_VERSION == false) goto L30;
        String r42 = VoIPHelper.getLogFilePath("voip_" + r19 + "_" + r4);
    L29:
        String r6 = r42;
        NativeInstance[] r43 = this.tgVoip;
        long r8 = this.captureDevice[r19];
        if (r19 != 1) goto L34;
        boolean r9 = true;
    L35:
        if (r19 == 0) goto L37;
    L39:
        boolean r10 = false;
    L40:
        VoIPService$$ExternalSyntheticLambda75 r11 = new VoIPService$$ExternalSyntheticLambda75(this, r19, r5);
        VoIPService$$ExternalSyntheticLambda76 r12 = new VoIPService$$ExternalSyntheticLambda76(this, r19);
        VoIPService$$ExternalSyntheticLambda77 r13 = new VoIPService$$ExternalSyntheticLambda77(this, r19);
        VoIPService$$ExternalSyntheticLambda78 r14 = new VoIPService$$ExternalSyntheticLambda78(this, r19);
        VoIPService$$ExternalSyntheticLambda79 r15 = new VoIPService$$ExternalSyntheticLambda79(this, r19);
        VoIPService$$ExternalSyntheticLambda80 r52 = new VoIPService$$ExternalSyntheticLambda80(this, r19);
        r202 = 1;
        if (this.conference == null) goto L44;
        boolean r17 = true;
    L45:
        r43[r19] = NativeInstance.makeGroup(r6, r8, r9, r10, r11, r12, r13, r14, r15, r52, r17);
        this.tgVoip[r19].setOnStateUpdatedListener(new VoIPService$$ExternalSyntheticLambda81(this, r19));
        r2 = true;
        goto L47
    L44:
        r17 = false;
        goto L45
    L37:
        if (SharedConfig.noiseSupression == false) goto L39;
        r10 = true;
        goto L40
    L34:
        r9 = false;
        goto L35
    L30:
        r42 = VoIPHelper.getLogFilePath(r4, false);
        goto L29
    L19:
        TLRPC.InputGroupCall r44 = this.joinConference;
        if ((r44 instanceof TLRPC.TL_inputGroupCallSlug) == false) goto L23;
        r4 = r44.slug;
        goto L26
    L23:
        if ((r44 instanceof TLRPC.TL_inputGroupCall) == false) goto L25;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET + this.joinConference.id;
        goto L26
    L25:
        r4 = MVEL.VERSION_SUB;
        goto L26
    L11:
        if (this.wasConnected == true) goto L15;
        this.reconnectScreenCapture = true;
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

    private void destroyConverting() {
        AndroidUtilities.cancelRunOnUIThread(this.destroyConvertingRunnable);
        if (this.convertingVoip == null) goto L15;
        FileLog.e("destroyConverting!!", new Exception());
        this.callStartTime = 0;
        StatsController.getInstance(this.currentAccount).incrementTotalCallsTime(getStatsNetworkType(), ((int) (getCallDuration() / 1000)) % 5);
        onTgVoipPreStop();
        Instance.FinalState r0 = this.convertingVoip.stop();
        updateTrafficStats(this.convertingVoip, r0.trafficStats);
        onTgVoipStop(r0);
        Instance.destroyInstance();
        this.convertingVoip = null;
        int r1 = 0;
    L7:
        if (r1 >= this.stateListeners.size()) goto L9;
        this.stateListeners.get(r1).onStateChanged(getCallState());
        r1 = r1 + 1;
        goto L7
    L9:
        NativeInstance r02 = this.tgVoip[0];
        if (r02 == null) goto L14;
        r02.setMuteMicrophone(this.micMute);
        return;
    L14:
        return;
    }

    private void dispatchStateChanged(int r4) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("== Call " + getCallID() + " state changed to " + r4 + " ==");
    L5:
        this.currentState = r4;
        if (r4 != 3) goto L9;
        destroyConverting();
    L9:
        if (USE_CONNECTION_SERVICE == false) goto L14;
        if (r4 != 3) goto L14;
        CallConnection r0 = this.systemCallConnection;
        if (r0 == null) goto L14;
        r0.setActive();
    L14:
        int r02 = 0;
    L16:
        if (r02 >= this.stateListeners.size()) goto L18;
        this.stateListeners.get(r02).onStateChanged(r4);
        r02 = r02 + 1;
        goto L16
    }

    private void endConnectionServiceCall(long r4) {
        if (USE_CONNECTION_SERVICE == false) goto L10;
        VoIPService$$ExternalSyntheticLambda1 r0 = new VoIPService$$ExternalSyntheticLambda1(this);
        if (r4 <= 0) goto L8;
        AndroidUtilities.runOnUIThread(r0, r4);
        return;
    L8:
        r0.run();
        return;
    }

    private void fetchBluetoothDeviceName() {
        if (this.fetchingBluetoothDeviceName == false) goto L11;
        return;
    L11:
        this.currentBluetoothDeviceName = null;     // Catch: Throwable -> L8
        this.fetchingBluetoothDeviceName = true;     // Catch: Throwable -> L8
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(this, this.serviceListener, 1);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        FileLog.e(th);
    }

    private NetworkInfo getActiveNetworkInfo() {
        return ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
    }

    private int getCurrentForegroundType() {
        return getCurrentForegroundType(this, this.gotMediaProjection);
    }

    private static int getCurrentForegroundType(ContextWrapper r2, boolean r3) {
        if (Build.VERSION.SDK_INT >= 29) goto L7;
        return Opcodes.USHR_INT_LIT8;
    L7:
        if (r2.checkSelfPermission("android.permission.CAMERA") != 0) goto L9;
        int r0 = 64;
    L11:
        if (r2.checkSelfPermission("android.permission.RECORD_AUDIO") != 0) goto L13;
        r0 = r0 | 128;
    L13:
        if (r3 == false) goto L16;
        r0 = r0 | 32;
    L16:
        return r0 | 2;
    L9:
        r0 = 0;
        goto L11
    }

    private String[] getEmoji() {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        r0.write(this.authKey);     // Catch: IOException -> L6
        r0.write(this.g_a);     // Catch: IOException -> L6
    L5:
        return EncryptionKeyEmojifier.emojifyForCall(Utilities.computeSHA256(r0.toByteArray(), 0, r0.size()));
    }

    private int getNetworkType() {
        NetworkInfo r0 = getActiveNetworkInfo();
        this.lastNetInfo = r0;
        if (r0 == null) goto L27;
        int r2 = r0.getType();
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L8;
        return 6;
    L8:
        if (r2 != 9) goto L29;
        return 7;
    L29:
        return 0;
    L15:
        switch(r0.getSubtype()) {
            case 1: goto L26;
            case 2: goto L24;
            case 3: goto L22;
            case 4: goto L16;
            case 5: goto L22;
            case 6: goto L20;
            case 7: goto L24;
            case 8: goto L20;
            case 9: goto L20;
            case 10: goto L20;
            case 11: goto L16;
            case 12: goto L20;
            case 13: goto L18;
            case 14: goto L16;
            case 15: goto L20;
            default: goto L16;
        };
    L16:
        return 11;
    L18:
        return 5;
    L20:
        return 4;
    L22:
        return 3;
    L24:
        return 2;
    L26:
        return 1;
    L27:
        return 0;
    }

    public static Bitmap getRoundAvatarBitmap(Context r6, int r7, TLObject r8) {
        Bitmap r1 = null;
    L14:
        th = move-exception;
        FileLog.e(th);
    L36:
        if (r1 != null) goto L42;
        Theme.createDialogsResources(r6);
        if ((r8 instanceof TLRPC.User) == false) goto L40;
        AvatarDrawable r62 = new AvatarDrawable((TLRPC.User) r8);
    L41:
        r1 = Bitmap.createBitmap(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f), Bitmap.Config.ARGB_8888);
        r62.setBounds(0, 0, r1.getWidth(), r1.getHeight());
        r62.draw(new Canvas(r1));
        goto L42
    L40:
        r62 = new AvatarDrawable((TLRPC.Chat) r8);
    L42:
        Canvas r63 = new Canvas(r1);
        Path r72 = new Path();
        r72.addCircle(r1.getWidth() / 2, r1.getHeight() / 2, r1.getWidth() / 2, Path.Direction.CW);
        r72.toggleInverseFillType();
        Paint r82 = new Paint(1);
        r82.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r63.drawPath(r72, r82);
        return r1;
    L5:
        if ((r8 instanceof TLRPC.User) == false) goto L20;
        TLRPC.User r2 = (TLRPC.User) r8;     // Catch: Throwable -> L14
        TLRPC.UserProfilePhoto r4 = r2.photo;     // Catch: Throwable -> L14
        if (r4 != null) goto L9;
    L48:
        goto L36
    L9:
        if (r4.photo_small == null) goto L48;
        BitmapDrawable r3 = ImageLoader.getInstance().getImageFromMemory(r2.photo.photo_small, null, "50_50");     // Catch: Throwable -> L14
        if (r3 == null) goto L46;
        r1 = r3.getBitmap().copy(Bitmap.Config.ARGB_8888, true);     // Catch: Throwable -> L14
        goto L48
    L46:
        BitmapFactory.Options r32 = new BitmapFactory.Options();     // Catch: Throwable -> L18
        r32.inMutable = true;     // Catch: Throwable -> L18
        r1 = BitmapFactory.decodeFile(FileLoader.getInstance(r7).getPathToAttach(r2.photo.photo_small, true).toString(), r32);     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L14
        goto L48
    L20:
        TLRPC.Chat r22 = (TLRPC.Chat) r8;     // Catch: Throwable -> L14
        if (r22 == null) goto L48;
        TLRPC.ChatPhoto r42 = r22.photo;     // Catch: Throwable -> L14
        if (r42 == null) goto L48;
        if (r42.photo_small == null) goto L48;
        BitmapDrawable r33 = ImageLoader.getInstance().getImageFromMemory(r22.photo.photo_small, null, "50_50");     // Catch: Throwable -> L14
        if (r33 == null) goto L44;
        r1 = r33.getBitmap().copy(Bitmap.Config.ARGB_8888, true);     // Catch: Throwable -> L14
        goto L48
    L44:
        BitmapFactory.Options r34 = new BitmapFactory.Options();     // Catch: Throwable -> L32
        r34.inMutable = true;     // Catch: Throwable -> L32
        r1 = BitmapFactory.decodeFile(FileLoader.getInstance(r7).getPathToAttach(r22.photo.photo_small, true).toString(), r34);     // Catch: Throwable -> L32
    L32:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L14
        goto L48
    }

    public static VoIPService getSharedInstance() {
        return sharedInstance;
    }

    public static VoIPServiceState getSharedState() {
        VoIPService r0 = sharedInstance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        VoIPGroupNotification.State r02 = VoIPGroupNotification.currentState;
        if (r02 == null) goto L9;
        return r02;
    L9:
        if (Build.VERSION.SDK_INT >= 33) goto L11;
        return null;
    L11:
        return VoIPPreNotificationService.getState();
    }

    private int getStatsNetworkType() {
        NetworkInfo r0 = this.lastNetInfo;
        if (r0 != null) goto L5;
        return 1;
    L5:
        if (r0.getType() == 0) goto L7;
        return 1;
    L7:
        if (this.lastNetInfo.isRoaming() == false) goto L10;
        return 2;
    L10:
        return 0;
    }

    public static String getStringFromFile(String r1) {
        FileInputStream r12 = new FileInputStream(new File(r1));
        String r0 = convertStreamToString(r12);
        r12.close();
        return r0;
    }

    private Class<? extends Activity> getUIActivityClass() {
        return LaunchActivity.class;
    }

    public static boolean hasRtmpStream() {
        if (getSharedInstance() != null) goto L5;
        return false;
    L5:
        if (getSharedInstance().groupCall != null) goto L7;
        return false;
    L7:
        if (getSharedInstance().groupCall.call.rtmp_stream == false) goto L13;
        return true;
    L13:
        return false;
    }

    private void initializeAccountRelatedThings() {
        updateServerConfig();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.appDidLogout);
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(false, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void initiateActualEncryptedCall() {
        Runnable r4 = this.timeoutRunnable;
        if (r4 == null) goto L116;
        AndroidUtilities.cancelRunOnUIThread(r4);
        this.timeoutRunnable = null;
    L116:
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L110;
        FileLog.e("error starting call", e);
    L110:
        callFailed();
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        FileLog.d("InitCall: keyID=" + this.keyFingerprint);     // Catch: Exception -> L8
    L10:
        SharedPreferences r3 = MessagesController.getNotificationsSettings(this.currentAccount);     // Catch: Exception -> L8
        Set<String> r42 = r3.getStringSet("calls_access_hashes", null);     // Catch: Exception -> L8
        if (r42 == null) goto L13;
        HashSet r6 = new HashSet(r42);     // Catch: Exception -> L8
    L14:
        r6.add(this.privateCall.id + " " + this.privateCall.access_hash + " " + System.currentTimeMillis());     // Catch: Exception -> L8
    L15:
        int r8 = 2;
        if (r6.size() <= 20) goto L31;
        Iterator r43 = r6.iterator();     // Catch: Exception -> L8
        long r9 = LongCompanionObject.MAX_VALUE;
        String r7 = null;
    L19:
        if (r43.hasNext() == false) goto L29;
        String r11 = (String) r43.next();     // Catch: Exception -> L8
        String[] r12 = r11.split(" ");     // Catch: Exception -> L8
        if (r12.length < 2) goto L22;
        long r122 = Long.parseLong(r12[2]);     // Catch: Exception -> L28
    L26:
        if (r122 >= r9) goto L19;
        r7 = r11;
        r9 = r122;
    L28:
        r43.remove();     // Catch: Exception -> L8
        goto L19
    L22:
        r43.remove();     // Catch: Exception -> L8
        goto L19
    L29:
        if (r7 == null) goto L15;
        r6.remove(r7);     // Catch: Exception -> L8
        goto L15
    L31:
        r3.edit().putStringSet("calls_access_hashes", r6).apply();     // Catch: Exception -> L8
        int r0 = 0;
        boolean r2 = AcousticEchoCanceler.isAvailable();     // Catch: Exception -> L35
    L118:
        boolean r32 = NoiseSuppressor.isAvailable();     // Catch: Exception -> L38
    L39:
        SharedPreferences r44 = MessagesController.getGlobalMainSettings();     // Catch: Exception -> L8
        MessagesController r5 = MessagesController.getInstance(this.currentAccount);     // Catch: Exception -> L8
        double r123 = r5.callConnectTimeout / 1000.0d;     // Catch: Exception -> L8
        double r14 = r5.callPacketTimeout / 1000.0d;     // Catch: Exception -> L8
        int r16 = convertDataSavingMode(r44.getInt("VoipDataSaving", VoIPHelper.getDataSavingDefault()));     // Catch: Exception -> L8
        Instance.ServerConfig r52 = Instance.getGlobalServerConfig();     // Catch: Exception -> L8
        if (r2 == true) goto L42;
    L45:
        boolean r18 = true;
    L46:
        if (r32 == true) goto L48;
    L51:
        boolean r19 = true;
    L52:
        boolean r22 = BuildVars.DEBUG_VERSION;     // Catch: Exception -> L8
        String r33 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r22 == false) goto L57;
        String r23 = VoIPHelper.getLogFilePath("voip" + this.privateCall.id);     // Catch: Exception -> L8
    L56:
        String r232 = r23;
        String r24 = VoIPHelper.getLogFilePath(_UrlKt.FRAGMENT_ENCODE_SET + this.privateCall.id, true);     // Catch: Exception -> L8
        TL_phone.PhoneCall r25 = this.privateCall;     // Catch: Exception -> L8
        boolean r72 = r25.p2p_allowed;     // Catch: Exception -> L8
        boolean r53 = r52.enableStunMarking;     // Catch: Exception -> L8
        int r92 = r25.protocol.max_layer;     // Catch: Exception -> L8
        TLRPC.TL_dataJSON r26 = r25.custom_parameters;     // Catch: Exception -> L8
        if (r26 == null) goto L62;
        r33 = r26.data;     // Catch: Exception -> L8
    L62:
        Instance.Config r262 = new Instance.Config(r123, r14, r16, r72, r18, r19, true, false, r53, r232, r24, r92, r33);     // Catch: Exception -> L8
        this.lastLogFilePath = r232;     // Catch: Exception -> L8
        String r27 = new File(ApplicationLoader.applicationContext.getCacheDir(), "voip_persistent_state.json").getAbsolutePath();     // Catch: Exception -> L8
        boolean r28 = r44.getBoolean("dbg_force_tcp_in_calls", false);     // Catch: Exception -> L8
        if (r28 == false) goto L65;
        int r162 = 3;
    L66:
        int r34 = this.privateCall.connections.size();     // Catch: Exception -> L8
        Instance.Endpoint[] r45 = new Instance.Endpoint[r34];     // Catch: Exception -> L8
        ArrayList r54 = new ArrayList();     // Catch: Exception -> L8
        int r73 = 0;
    L67:
        if (r73 >= r34) goto L72;
        TLRPC.PhoneConnection r93 = this.privateCall.connections.get(r73);     // Catch: Exception -> L8
        int r242 = r8;
        int r36 = r0;
        r45[r73] = new Instance.Endpoint(r93 instanceof TLRPC.TL_phoneConnectionWebrtc, r93.id, r93.ip, r93.ipv6, r93.port, r162, r93.peer_tag, r93.turn, r93.stun, r93.username, r93.password, r93.tcp);     // Catch: Exception -> L8
        if ((r93 instanceof TLRPC.TL_phoneConnection) == false) goto L71;
        r54.add(Long.valueOf(((TLRPC.TL_phoneConnection) r93).id));     // Catch: Exception -> L8
    L71:
        r73 = r73 + 1;     // Catch: Exception -> L8
        r8 = r242;
        r0 = r36;
        goto L67
    L72:
        int r362 = r0;
        int r243 = r8;
        if (r54.isEmpty() == true) goto L81;
        Collections.sort(r54);     // Catch: Exception -> L8
        HashMap r02 = new HashMap();     // Catch: Exception -> L8
        int r62 = r362;
    L76:
        if (r62 >= r54.size()) goto L78;
        Long r74 = (Long) r54.get(r62);     // Catch: Exception -> L8
        r62 = r62 + 1;     // Catch: Exception -> L8
        r02.put(r74, Integer.valueOf(r62));     // Catch: Exception -> L8
        goto L76
    L78:
        int r55 = r362;
    L79:
        if (r55 >= r34) goto L81;
        Instance.Endpoint r63 = r45[r55];     // Catch: Exception -> L8
        r63.reflectorId = ((Integer) r02.getOrDefault(Long.valueOf(r63.id), Integer.valueOf(r362))).intValue();     // Catch: Exception -> L8
        r55 = r55 + 1;     // Catch: Exception -> L8
    L81:
        if (r28 == false) goto L83;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda2(this));     // Catch: Exception -> L8
    L83:
        Instance.EncryptionKey r03 = new Instance.EncryptionKey(this.authKey, this.isOutgoing);     // Catch: Exception -> L8
        if ("2.7.7".compareTo(this.privateCall.protocol.library_versions.get(r362)) > 0) goto L86;
        boolean r64 = 1;
    L87:
        long r75 = this.captureDevice[r362];     // Catch: Exception -> L8
        if (r75 == 0) goto L92;
        if (r64 != 0) goto L92;
        NativeInstance.destroyVideoCapturer(r75);     // Catch: Exception -> L8
        this.captureDevice[r362] = 0;     // Catch: Exception -> L8
        this.videoState[r362] = r362;     // Catch: Exception -> L8
    L92:
        if (this.isOutgoing == false) goto L94;
    L99:
        this.tgVoip[0] = Instance.makeInstance(this.privateCall.protocol.library_versions.get(0), r262, r27, r45, null, getNetworkType(), r03, this.remoteSink[0], this.captureDevice[0], new VoIPService$$ExternalSyntheticLambda3(this));     // Catch: Exception -> L8
        this.tgVoip[0].setOnStateUpdatedListener(new VoIPService$$ExternalSyntheticLambda4(this));     // Catch: Exception -> L8
        this.tgVoip[0].setOnSignalBarsUpdatedListener(new VoIPService$$ExternalSyntheticLambda5(this));     // Catch: Exception -> L8
        this.tgVoip[0].setOnSignalDataListener(new VoIPService$$ExternalSyntheticLambda6(this));     // Catch: Exception -> L8
        this.tgVoip[0].setOnRemoteMediaStateUpdatedListener(new VoIPService$$ExternalSyntheticLambda7(this));     // Catch: Exception -> L8
        this.tgVoip[0].setMuteMicrophone(this.micMute);     // Catch: Exception -> L8
        if (r64 == this.isVideoAvailable) goto L105;
        this.isVideoAvailable = r64;     // Catch: Exception -> L8
        int r56 = 0;
    L103:
        if (r56 >= this.stateListeners.size()) goto L105;
        this.stateListeners.get(r56).onVideoAvailableChange(this.isVideoAvailable);     // Catch: Exception -> L8
        r56 = r56 + 1;     // Catch: Exception -> L8
    L105:
        this.destroyCaptureDevice[0] = false;     // Catch: Exception -> L8
        AndroidUtilities.runOnUIThread(new AnonymousClass8(this), 5000);     // Catch: Exception -> L8
        return;
    L94:
        if (this.videoCall == true) goto L96;
    L98:
        this.videoState[0] = 0;     // Catch: Exception -> L8
        goto L99
    L96:
        if (checkSelfPermission("android.permission.CAMERA") != 0) goto L98;
        this.captureDevice[0] = NativeInstance.createVideoCapturer(this.localSink[0], this.isFrontFaceCamera ? 1 : 0);     // Catch: Exception -> L8
        this.videoState[0] = r243;     // Catch: Exception -> L8
        goto L99
    L86:
        r64 = r362;
        goto L87
    L65:
        r162 = 2;
        goto L66
    L57:
        r23 = VoIPHelper.getLogFilePath(_UrlKt.FRAGMENT_ENCODE_SET + this.privateCall.id, false);     // Catch: Exception -> L8
        goto L56
    L48:
        if (r52.useSystemNs == false) goto L51;
        r19 = false;
        goto L52
    L42:
        if (r52.useSystemAec == false) goto L45;
        r18 = false;
    L38:
        r32 = false;
    L35:
        r2 = false;
        goto L118
    L13:
        r6 = new HashSet();     // Catch: Exception -> L8
        goto L14
    }

    public static boolean isAnyKindOfCallActive() {
        if (getSharedInstance() != null) goto L5;
    L8:
        return false;
    L5:
        if (getSharedInstance().getCallState() == 15) goto L8;
        return true;
    }

    private static boolean isDeviceCompatibleWithConnectionServiceAPI() {
        return false;
    }

    private boolean isFinished() {
        int r1 = this.currentState;
        if (r1 != 11) goto L5;
        return true;
    L5:
        if (r1 == 4) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isRinging() {
        if (this.currentState != 15) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$acceptIncomingCall$101(TLRPC.TL_error r2, TLObject r3) {
        if (r2 != null) goto L11;
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.w("accept call ok! " + r3);
    L6:
        TL_phone.PhoneCall r22 = ((TL_phone.TL_phone_phoneCall) r3).phone_call;
        this.privateCall = r22;
        if ((r22 instanceof TL_phone.TL_phoneCallDiscarded) == false) goto L15;
        onCallUpdated(r22);
        return;
    L15:
        return;
    L11:
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.e("Error on phone.acceptCall: " + r2);
    L13:
        callFailed();
    }

    private /* synthetic */ void lambda$acceptIncomingCall$102(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda46(this, r3, r2));
    }

    private /* synthetic */ void lambda$acceptIncomingCall$103(MessagesStorage r8, TLObject r9, TLRPC.TL_error r10) {
        if (r10 != null) goto L28;
        TLRPC.messages_DhConfig r102 = (TLRPC.messages_DhConfig) r9;
        if ((r9 instanceof TLRPC.TL_messages_dhConfig) == true) goto L6;
    L13:
        byte[] r0 = new byte[256];
        int r2 = 0;
    L14:
        if (r2 >= 256) goto L17;
        r0[r2] = (byte) (((byte) (Utilities.random.nextDouble() * 256.0d)) ^ r102.random[r2]);
        r2 = r2 + 1;
        goto L14
    L17:
        if (this.privateCall == null) goto L19;
        this.a_or_b = r0;
        BigInteger r82 = BigInteger.valueOf(r8.getSecretG()).modPow(new BigInteger(1, r0), new BigInteger(1, r8.getSecretPBytes()));
        this.g_a_hash = this.privateCall.g_a_hash;
        byte[] r83 = r82.toByteArray();
        if (r83.length <= 256) goto L26;
        byte[] r103 = new byte[256];
        System.arraycopy(r83, 1, r103, 0, 256);
        r83 = r103;
    L26:
        TL_phone.acceptCall r92 = new TL_phone.acceptCall();
        r92.g_b = r83;
        TLRPC.TL_inputPhoneCall r84 = new TLRPC.TL_inputPhoneCall();
        r92.peer = r84;
        TL_phone.PhoneCall r104 = this.privateCall;
        r84.id = r104.id;
        r84.access_hash = r104.access_hash;
        TL_phone.TL_phoneCallProtocol r85 = new TL_phone.TL_phoneCallProtocol();
        r92.protocol = r85;
        r85.udp_reflector = true;
        r85.udp_p2p = true;
        r85.min_layer = 65;
        r85.max_layer = Instance.getConnectionMaxLayer();
        Collections.addAll(r92.protocol.library_versions, NativeInstance.getAllVersions());
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r92, new VoIPService$$ExternalSyntheticLambda87(this), 65536);
        return;
    L19:
        if (BuildVars.LOGS_ENABLED == false) goto L21;
        FileLog.e("call is null");
    L21:
        callFailed();
        return;
    L6:
        if (Utilities.isGoodPrime(r102.p, r102.g) == false) goto L8;
        r8.setSecretPBytes(r102.p);
        r8.setSecretG(r102.g);
        r8.setLastSecretVersion(r102.version);
        MessagesStorage.getInstance(this.currentAccount).saveSecretParams(r8.getLastSecretVersion(), r8.getSecretG(), r8.getSecretPBytes());
        goto L13
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        FileLog.e("stopping VoIP service, bad prime");
    L10:
        callFailed();
        return;
    L28:
        callFailed();
    }

    private /* synthetic */ void lambda$acknowledgeCall$12(TLObject r4, TLRPC.TL_error r5, boolean r6) {
        if (sharedInstance != null) goto L6;
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.w("receivedCall response = " + r4);
    L8:
        if (r5 == null) goto L15;
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.e("error on receivedCall: " + r5);
    L12:
        stopSelf();
        return;
    L15:
        if (USE_CONNECTION_SERVICE == false) goto L17;
        ContactsController r42 = ContactsController.getInstance(this.currentAccount);
        TLRPC.User r52 = this.user;
        r42.createOrUpdateConnectionServiceContact(r52.id, r52.first_name, r52.last_name);
        TelecomManager r43 = (TelecomManager) getSystemService("telecom");
        Bundle r53 = new Bundle();
        r53.putInt("call_type", 1);
        r43.addNewIncomingCall(addAccountToTelecomManager(), r53);
    L17:
        if (r6 == false) goto L20;
        startRinging();
        return;
    }

    private /* synthetic */ void lambda$acknowledgeCall$13(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda19(this, r3, r4, r2));
    }

    private /* synthetic */ void lambda$callEnded$122() {
        dispatchStateChanged(11);
    }

    private /* synthetic */ void lambda$callEnded$123() {
        int r0 = this.spPlayId;
        if (r0 == 0) goto L6;
        this.soundPool.stop(r0);
        this.spPlayId = 0;
        return;
    }

    private /* synthetic */ void lambda$callEnded$124() {
        this.soundPool.play(this.spEndId, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    private /* synthetic */ void lambda$callEnded$125() {
        this.soundPool.play(this.spVoiceChatEndId, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    private /* synthetic */ void lambda$callFailed$114() {
        dispatchStateChanged(4);
    }

    private /* synthetic */ void lambda$callFailed$115() {
        this.soundPool.play(this.spFailedID, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    private /* synthetic */ void lambda$configureDeviceForCall$110(AudioManager r6) {
        if (r6.requestAudioFocus(this, 0, 2) != 1) goto L5;
        boolean r2 = true;
    L6:
        this.hasAudioFocus = r2;
        VoipAudioManager r22 = VoipAudioManager.get();
        if (isBluetoothHeadsetConnected() == true) goto L9;
    L25:
        boolean r1 = isBluetoothHeadsetConnected();
        boolean r4 = this.speakerphoneStateToSet;
        if (r1 == false) goto L28;
        r6.setBluetoothScoOn(r4);
    L35:
        updateOutputGainControlState();
        this.audioConfigured = true;
        return;
    L28:
        r22.setSpeakerphoneOn(r4);
        if (this.speakerphoneStateToSet == false) goto L31;
        this.audioRouteToSet = 1;
    L32:
        SensorEvent r62 = this.lastSensorEvent;
        if (r62 == null) goto L35;
        onSensorChanged(r62);
        goto L35
    L31:
        this.audioRouteToSet = 0;
        goto L32
    L9:
        if (hasEarpiece() == false) goto L25;
        int r42 = this.audioRouteToSet;
        if (r42 == 0) goto L24;
        if (r42 == 1) goto L23;
        if (r42 != 2) goto L35;
        if (this.bluetoothScoActive == true) goto L22;
        this.needSwitchToBluetoothAfterScoActivates = true;
        r6.startBluetoothSco();     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        FileLog.e(th);
        goto L35
    L22:
        r6.setBluetoothScoOn(true);
        r22.setSpeakerphoneOn(false);
        goto L35
    L23:
        r6.setBluetoothScoOn(false);
        r22.setSpeakerphoneOn(true);
        goto L35
    L24:
        r6.setBluetoothScoOn(false);
        r22.setSpeakerphoneOn(false);
        goto L35
    L5:
        r2 = false;
        goto L6
    }

    private /* synthetic */ void lambda$configureDeviceForCall$111(AudioManager r2) {
    L6:
        e = move-exception;
        FileLog.e(e);
    L11:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda99(this, r2));
        return;
    L3:
        if (hasRtmpStream() == false) goto L8;
        r2.setMode(0);     // Catch: Exception -> L6
        r2.setBluetoothScoOn(false);     // Catch: Exception -> L6
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda98());     // Catch: Exception -> L6
        return;
    L8:
        r2.setMode(3);     // Catch: Exception -> L6
        goto L11
    }

    private /* synthetic */ void lambda$convertToConferenceCall$30() {
        startConferenceGroupCall(true, 0, null, false);
    }

    private /* synthetic */ void lambda$createGroupInstance$66(int r3, boolean[] r4, int r5, String r6) {
        if (r3 == 0) goto L4;
        startScreenCapture(r5, r6);
        return;
    L4:
        if (this.conference == null) goto L6;
        startConferenceGroupCall(false, r5, r6, !r4[0]);
    L7:
        r4[0] = false;
        return;
    L6:
        startGroupCall(r5, r6, true);
        goto L7
    }

    private /* synthetic */ void lambda$createGroupInstance$68(int r10, int[] r11, float[] r12, boolean[] r13) {
        if (sharedInstance == null) goto L33;
        ChatObject.Call r0 = this.groupCall;
        if (r0 == null) goto L34;
        if (r10 != 0) goto L37;
        r0.processVoiceLevelsUpdate(r11, r12, r13);
        int r102 = 0;
        float r1 = 0.0f;
        boolean r02 = false;
    L10:
        if (r102 >= r11.length) goto L25;
        if (r11[r102] == 0) goto L14;
        r1 = Math.max(r1, r12[r102]);
        r02 = true;
    L24:
        r102 = r102 + 1;
        goto L10
    L14:
        if (this.chat != null) goto L16;
    L22:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.webRtcMicAmplitudeEvent, new Object[]{Float.valueOf(r12[r102])});
        goto L24
    L16:
        if (this.lastTypingTimeSend >= (SystemClock.uptimeMillis() - 5000)) goto L22;
        if (r12[r102] <= 0.1f) goto L22;
        if (r13[r102] == false) goto L22;
        this.lastTypingTimeSend = SystemClock.uptimeMillis();
        TLRPC.TL_messages_setTyping r2 = new TLRPC.TL_messages_setTyping();
        r2.action = new TLRPC.TL_speakingInGroupCallAction();
        r2.peer = MessagesController.getInputPeer(this.chat);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new VoIPService$$ExternalSyntheticLambda85());
        goto L22
    L25:
        if (r02 == false) goto L35;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.webRtcSpeakerAmplitudeEvent, new Object[]{Float.valueOf(r1)});
        NativeInstance.AudioLevelsCallback r9 = audioLevelsCallback;
        if (r9 == null) goto L36;
        r9.run(r11, r12, r13);
        return;
    L36:
        return;
    L35:
        return;
    L37:
        return;
    L34:
        return;
    }

    private /* synthetic */ void lambda$createGroupInstance$69(long r1, int[] r3, ArrayList r4) {
        if (sharedInstance != null) goto L5;
        return;
    L5:
        if (this.groupCall == null) goto L10;
        broadcastUnknownParticipants(r1, r3);
        return;
    }

    private /* synthetic */ void lambda$createGroupInstance$70(int r2, long r3, int[] r5) {
        if (sharedInstance == null) goto L10;
        ChatObject.Call r0 = this.groupCall;
        if (r0 == null) goto L11;
        if (r2 != 0) goto L12;
        r0.processUnknownVideoParticipants(r5, new VoIPService$$ExternalSyntheticLambda122(this, r3, r5));
        return;
    L12:
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$createGroupInstance$71(String r1) {
        this.currentStreamRequestTimestamp.remove(r1);
    }

    private /* synthetic */ void lambda$createGroupInstance$72(int r3) {
        createGroupInstance(r3, false, true);
    }

    private /* synthetic */ void lambda$createGroupInstance$73(String r19, int r20, long r21, int r23, int r24, TLObject r25, TLRPC.TL_error r26, long r27) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda47(this, r19));
        NativeInstance r4 = this.tgVoip[r20];
        if (r4 != null) goto L5;
        return;
    L5:
        if (r25 == null) goto L9;
        NativeByteBuffer r0 = ((TLRPC.TL_upload_file) r25).bytes;
        r4.onStreamPartAvailable(r21, r0.buffer, r0.limit(), r27, r23, r24);
        return;
    L9:
        if ("GROUPCALL_JOIN_MISSING".equals(r26.text) == false) goto L13;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda48(this, r20));
        return;
    L13:
        if ("TIME_TOO_BIG".equals(r26.text) == false) goto L15;
    L19:
        int r2 = 0;
    L18:
        int r13 = r2;
        this.tgVoip[r20].onStreamPartAvailable(r21, null, r13, r27, r23, r24);
        return;
    L15:
        if (r26.text.startsWith("FLOOD_WAIT") == true) goto L19;
        r2 = -1;
        goto L18
    }

    private /* synthetic */ void lambda$createGroupInstance$74(String r1, int r2) {
        this.currentStreamRequestTimestamp.put(r1, Integer.valueOf(r2));
    }

    private /* synthetic */ void lambda$createGroupInstance$75(int r9, long r10, long r12, int r14, int r15) {
        if (r9 == 0) goto L4;
        return;
    L4:
        TLRPC.TL_upload_getFile r92 = new TLRPC.TL_upload_getFile();
        r92.limit = 131072;
        TLRPC.TL_inputGroupCallStream r0 = new TLRPC.TL_inputGroupCallStream();
        r0.call = this.groupCall.getInputGroupCall();
        r0.time_ms = r10;
        if (r12 != 500) goto L7;
        r0.scale = 1;
    L7:
        if (r14 == 0) goto L9;
        r0.flags |= 1;
        r0.video_channel = r14;
        r0.video_quality = r15;
    L9:
        r92.location = r0;
        if (r14 != 0) goto L13;
        StringBuilder r122 = new StringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        r122.append(r10);
    L12:
        String r123 = r122.toString();
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda17(this, r123, AccountInstance.getInstance(this.currentAccount).getConnectionsManager().sendRequest(r92, new VoIPService$$ExternalSyntheticLambda16(this, r123, r9, r10, r14, r15), 65536, 2, this.groupCall.call.stream_dc_id)));
        return;
    L13:
        r122 = new StringBuilder();
        r122.append(r14);
        r122.append("_");
        r122.append(r10);
        r122.append("_");
        r122.append(r15);
        goto L12
    }

    private /* synthetic */ void lambda$createGroupInstance$76(int r2, long r3, int r5) {
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

    private /* synthetic */ void lambda$createGroupInstance$77(int r1, long r2, long r4, int r6, int r7) {
        if (r1 == 0) goto L4;
        return;
    L4:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda107(this, r6, r2, r7));
    }

    private /* synthetic */ void lambda$createGroupInstance$78(int r2, long r3, TLObject r5, TLRPC.TL_error r6, long r7) {
        long r72 = 0;
        if (r6 != null) goto L10;
        TL_phone.groupCallStreamChannels r52 = (TL_phone.groupCallStreamChannels) r5;
        if (r52.channels.isEmpty() == true) goto L7;
        r72 = r52.channels.get(0).last_timestamp_ms;
    L7:
        ChatObject.Call r62 = this.groupCall;
        if (r62.loadedRtmpStreamParticipant == true) goto L10;
        r62.createRtmpStreamParticipant(r52.channels);
        this.groupCall.loadedRtmpStreamParticipant = true;
    L10:
        NativeInstance r1 = this.tgVoip[r2];
        if (r1 == null) goto L14;
        r1.onRequestTimeComplete(r3, r72);
        return;
    }

    private /* synthetic */ void lambda$createGroupInstance$79(int r8, long r9) {
        ChatObject.Call r0 = this.groupCall;
        if (r0 == null) goto L21;
        TLRPC.GroupCall r02 = r0.call;
        if (r02 == null) goto L21;
        if (r02.rtmp_stream == false) goto L21;
        TL_phone.getGroupCallStreamChannels r2 = new TL_phone.getGroupCallStreamChannels();
        r2.call = this.groupCall.getInputGroupCall();
        ChatObject.Call r03 = this.groupCall;
        if (r03 != null) goto L11;
    L17:
        NativeInstance r7 = this.tgVoip[r8];
        if (r7 == null) goto L25;
        r7.onRequestTimeComplete(r9, 0);
        return;
    L25:
        return;
    L11:
        if (r03.call == null) goto L17;
        if (this.tgVoip[r8] == null) goto L17;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new VoIPService$$ExternalSyntheticLambda35(this, r8, r9), 65536, 2, this.groupCall.call.stream_dc_id);
        return;
    L21:
        NativeInstance r82 = this.tgVoip[r8];
        if (r82 == null) goto L26;
        r82.onRequestTimeComplete(r9, ConnectionsManager.getInstance(this.currentAccount).getCurrentTimeMillis());
        return;
    }

    private /* synthetic */ void lambda$createGroupInstance$80(int r1, int r2, boolean r3) {
        updateConnectionState(r1, r2, r3);
    }

    private /* synthetic */ void lambda$declineIncomingCall$104() {
        if (this.currentState != 10) goto L6;
        callEnded();
        return;
    }

    private /* synthetic */ void lambda$declineIncomingCall$105(TLObject r2, TLRPC.TL_error r3) {
        if (r3 == null) goto L8;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("error on phone.discardCall: " + r3);
        return;
    L14:
        return;
    L8:
        if ((r2 instanceof TLRPC.TL_updates) == false) goto L11;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.TL_updates) r2, false);
    L11:
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.d("phone.discardCall " + r2);
        return;
    }

    private /* synthetic */ void lambda$editCallMember$90(int r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        if (r3 == null) goto L4;
        AccountInstance.getInstance(r1).getMessagesController().processUpdates((TLRPC.Updates) r3, false);
    L8:
        if (r2 == null) goto L11;
        AndroidUtilities.runOnUIThread(r2);
        return;
    L11:
        return;
    L4:
        if (r4 == null) goto L8;
        if ("GROUPCALL_VIDEO_TOO_MUCH".equals(r4.text) == false) goto L8;
        this.groupCall.reloadGroupCall();
        goto L8
    }

    private /* synthetic */ void lambda$endConnectionServiceCall$126() {
        CallConnection r0 = this.systemCallConnection;
        if (r0 == null) goto L25;
        int r1 = this.callDiscardReason;
        int r2 = 2;
        if (r1 == 1) goto L19;
        if (r1 == 2) goto L17;
        int r22 = 4;
        if (r1 == 3) goto L13;
        if (r1 == 4) goto L11;
        r0.setDisconnected(new DisconnectCause(3));
    L23:
        this.systemCallConnection.destroy();
        this.systemCallConnection = null;
        return;
    L11:
        r0.setDisconnected(new DisconnectCause(7));
        goto L23
    L13:
        if (this.isOutgoing == true) goto L16;
        r22 = 5;
    L16:
        r0.setDisconnected(new DisconnectCause(r22));
        goto L23
    L17:
        r0.setDisconnected(new DisconnectCause(1));
        goto L23
    L19:
        if (this.isOutgoing == true) goto L22;
        r2 = 6;
    L22:
        r0.setDisconnected(new DisconnectCause(r2));
        goto L23
    }

    private /* synthetic */ void lambda$getConnectionAndStartCall$106() {
        this.delayedStartOutgoingCall = null;
        startOutgoingCall();
    }

    private /* synthetic */ void lambda$hangUp$4(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.TL_updates) == false) goto L6;
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        r0.processUpdates((TLRPC.TL_updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$hangUp$5(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.TL_updates) == false) goto L6;
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        r0.processUpdates((TLRPC.TL_updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$initiateActualEncryptedCall$84() {
        Toast.makeText(this, "This call uses TCP which will degrade its quality.", 0).show();
    }

    private /* synthetic */ void lambda$initiateActualEncryptedCall$85(int[] r1, float[] r2, boolean[] r3) {
        if (sharedInstance != null) goto L5;
        return;
    L5:
        if (this.privateCall == null) goto L10;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.webRtcMicAmplitudeEvent, new Object[]{Float.valueOf(r2[0])});
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.webRtcSpeakerAmplitudeEvent, new Object[]{Float.valueOf(r2[1])});
        return;
    }

    private /* synthetic */ void lambda$initiateActualEncryptedCall$86(int r3, int r4) {
        this.remoteAudioState = r3;
        this.remoteVideoState = r4;
        checkIsNear();
        int r0 = 0;
    L4:
        if (r0 >= this.stateListeners.size()) goto L6;
        this.stateListeners.get(r0).onMediaStateUpdated(r3, r4);
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$initiateActualEncryptedCall$87(int r2, int r3) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda43(this, r2, r3));
    }

    private /* synthetic */ void lambda$loadResources$108() {
        SoundPool r0 = new SoundPool(1, 0, 0);
        this.soundPool = r0;
        this.spConnectingId = r0.load(this, R.raw.voip_connecting, 1);
        this.spRingbackID = this.soundPool.load(this, R.raw.voip_ringback, 1);
        this.spFailedID = this.soundPool.load(this, R.raw.voip_failed, 1);
        this.spEndId = this.soundPool.load(this, R.raw.voip_end, 1);
        this.spBusyId = this.soundPool.load(this, R.raw.voip_busy, 1);
        this.spVoiceChatEndId = this.soundPool.load(this, R.raw.voicechat_leave, 1);
        this.spVoiceChatStartId = this.soundPool.load(this, R.raw.voicechat_join, 1);
        this.spVoiceChatConnecting = this.soundPool.load(this, R.raw.voicechat_connecting, 1);
        this.spAllowTalkId = this.soundPool.load(this, R.raw.voip_onallowtalk, 1);
        this.spStartRecordId = this.soundPool.load(this, R.raw.voip_recordstart, 1);
    }

    private /* synthetic */ void lambda$onCallUpdated$16() {
        this.soundPool.play(this.spBusyId, 1.0f, 1.0f, 0, -1, 1.0f);
    }

    private /* synthetic */ void lambda$onCallUpdated$17() {
        int r0 = this.spPlayId;
        if (r0 == 0) goto L5;
        this.soundPool.stop(r0);
    L5:
        this.spPlayId = this.soundPool.play(this.spRingbackID, 1.0f, 1.0f, 0, -1, 1.0f);
    }

    private /* synthetic */ void lambda$onCallUpdated$18() {
        this.timeoutRunnable = null;
        declineIncomingCall(3, null);
    }

    private /* synthetic */ void lambda$onConnectionStateChanged$116() {
        int r0 = this.spPlayId;
        if (r0 == 0) goto L6;
        this.soundPool.stop(r0);
        this.spPlayId = 0;
        return;
    }

    private /* synthetic */ void lambda$onConnectionStateChanged$117() {
        int r0 = this.spPlayId;
        if (r0 == 0) goto L5;
        this.soundPool.stop(r0);
    L5:
        SoundPool r2 = this.soundPool;
        if (this.groupCall == null) goto L9;
        int r02 = this.spVoiceChatConnecting;
    L10:
        this.spPlayId = r2.play(r02, 1.0f, 1.0f, 0, -1, 1.0f);
        return;
    L9:
        r02 = this.spConnectingId;
        goto L10
    }

    private /* synthetic */ void lambda$onConnectionStateChanged$118(int r6) {
        if (this.convertingVoip == null) goto L6;
        return;
    L6:
        if (r6 != 3) goto L11;
        if (this.callStartTime != 0) goto L11;
        this.callStartTime = SystemClock.elapsedRealtime();
    L11:
        if (r6 != 4) goto L14;
        callFailed();
        return;
    L14:
        if (r6 != 3) goto L32;
        Runnable r1 = this.connectingSoundRunnable;
        if (r1 == null) goto L18;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.connectingSoundRunnable = null;
    L18:
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda20(this));
        if (this.groupCall != null) goto L32;
        if (this.wasEstablished == true) goto L32;
        this.wasEstablished = true;
        if (this.isProximityNear == false) goto L25;
    L27:
        AndroidUtilities.runOnUIThread(new AnonymousClass11(this), 5000);
        boolean r0 = this.isOutgoing;
        int r2 = this.currentAccount;
        if (r0 == false) goto L30;
        StatsController.getInstance(r2).incrementSentItemsCount(getStatsNetworkType(), 0, 1);
        goto L32
    L30:
        StatsController.getInstance(r2).incrementReceivedItemsCount(getStatsNetworkType(), 0, 1);
        goto L32
    L25:
        if (this.privateCall.video == true) goto L27;
        LaunchActivity.getLastFragment().getFragmentView().performHapticFeedback(3, 2);     // Catch: Exception -> L38
    L32:
        if (r6 == 5) goto L34;
    L36:
        dispatchStateChanged(r6);
        return;
    L34:
        if (this.isCallEnded == true) goto L36;
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda21(this));
        goto L36
    }

    private /* synthetic */ void lambda$onDestroy$99() {
        SoundPool r0 = this.soundPool;
        if (r0 == null) goto L6;
        r0.release();
        return;
    }

    private /* synthetic */ void lambda$onSignalBarCountChanged$121(int r3) {
        this.signalBarCount = r3;
        int r0 = 0;
    L4:
        if (r0 >= this.stateListeners.size()) goto L6;
        this.stateListeners.get(r0).onSignalBarsCountChanged(r3);
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$onStartCommand$1(Boolean r2) {
        if (sharedInstance != null) goto L6;
        return;
    L6:
        if (r2.booleanValue() == true) goto L8;
        return;
    L8:
        setMicMute(false, false, true);
    }

    private /* synthetic */ void lambda$onStartCommand$2() {
        this.delayedStartOutgoingCall = null;
        startOutgoingCall();
    }

    private /* synthetic */ void lambda$onStartCommand$3() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.voipServiceCreated, new Object[0]);
    }

    private /* synthetic */ void lambda$playAllowTalkSound$120() {
        this.soundPool.play(this.spAllowTalkId, 0.5f, 0.5f, 0, 0, 1.0f);
    }

    private /* synthetic */ void lambda$playConnectedSound$88() {
        this.soundPool.play(this.spVoiceChatStartId, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    private /* synthetic */ void lambda$playStartRecordSound$119() {
        this.soundPool.play(this.spStartRecordId, 0.5f, 0.5f, 0, 0, 1.0f);
    }

    private /* synthetic */ void lambda$processAcceptedCall$19(TLRPC.TL_error r1, TLObject r2) {
        if (r1 == null) goto L5;
        callFailed();
        return;
    L5:
        this.privateCall = ((TL_phone.TL_phone_phoneCall) r2).phone_call;
        initiateActualEncryptedCall();
    }

    private /* synthetic */ void lambda$processAcceptedCall$20(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda45(this, r3, r2));
    }

    private /* synthetic */ void lambda$setMicMute$0() {
        if (this.updateNotificationRunnable != null) goto L5;
        return;
    L5:
        this.updateNotificationRunnable = null;
        showNotification(getTitle(), getRoundAvatarBitmap(this, this.currentAccount, this.chat));
    }

    private /* synthetic */ void lambda$setupCaptureDevice$14() {
        this.micSwitching = false;
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$31(TLObject r11, AccountInstance r12, TLRPC.TL_error r13) {
        boolean r0 = r11 instanceof TLRPC.Updates;
        TimeUnit r1 = TimeUnit.MILLISECONDS;
        if (r0 == false) goto L26;
        TLRPC.Updates r112 = (TLRPC.Updates) r11;
        MessagesController.getInstance(this.currentAccount).putUsers(r112.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r112.chats, false);
        ArrayList r113 = MessagesController.findUpdatesAndRemove(r112, TL_update.TL_updateGroupCall.class);
        int r132 = r113.size();
        TLRPC.GroupCall r9 = null;
        int r02 = 0;
    L5:
        if (r02 >= r132) goto L7;
        Object r92 = r113.get(r02);
        r02 = r02 + 1;
        r9 = ((TL_update.TL_updateGroupCall) r92).call;
        goto L5
    L7:
        if (r9 == null) goto L62;
        this.groupCallBottomSheetLatch.await(800, r1);     // Catch: InterruptedException -> L11
    L13:
        this.groupCall.setCall(r12, 0, r9);
        this.groupCall.loadMembers(true);
        MessagesController.getInstance(this.currentAccount).putGroupCall(0, this.groupCall);
        this.conference.inputGroupCall = new TLRPC.TL_inputGroupCall();
        ConferenceCall r114 = this.conference;
        TLRPC.InputGroupCall r122 = r114.inputGroupCall;
        r122.id = r9.id;
        r122.access_hash = r9.access_hash;
        r114.groupCall = TlUtils.applyGroupCallUpdate(r114.groupCall, r9);
        startConferenceGroupCall(false, 0, null, false);
        HashSet<Long> r115 = this.inviteUsersToConference;
        if (r115 == null) goto L22;
        Iterator<Long> r116 = r115.iterator();
    L17:
        if (r116.hasNext() == false) goto L22;
        long r123 = r116.next().longValue();
        ChatObject.Call r03 = this.groupCall;
        if (r03 == null) goto L17;
        r03.addInvitedUser(r123);
    L22:
        if (VoIPFragment.getInstance() == null) goto L24;
        VoIPFragment.getInstance().finish();
    L24:
        GroupCallActivity.create(LaunchActivity.instance, AccountInstance.getInstance(this.currentAccount), null, null, false, null);
        return;
    L11:
        e = move-exception;
        FileLog.e(e);
        goto L13
    L62:
        return;
    L26:
        if ((r11 instanceof TL_phone.groupCall) == true) goto L46;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.needShowAlert, new Object[]{6, r13.text});
        hangUp(0);
        return;
    L46:
        this.groupCallBottomSheetLatch.await(800, r1);     // Catch: InterruptedException -> L29
    L31:
        TL_phone.groupCall r117 = (TL_phone.groupCall) r11;
        this.groupCall.setCall(r12, 0, r117);
        this.groupCall.loadMembers(true);
        MessagesController.getInstance(this.currentAccount).putGroupCall(0, this.groupCall);
        this.conference.inputGroupCall = new TLRPC.TL_inputGroupCall();
        ConferenceCall r124 = this.conference;
        TLRPC.InputGroupCall r133 = r124.inputGroupCall;
        TLRPC.GroupCall r118 = r117.call;
        r133.id = r118.id;
        r133.access_hash = r118.access_hash;
        r124.groupCall = r118;
        startConferenceGroupCall(false, 0, null, false);
        HashSet<Long> r119 = this.inviteUsersToConference;
        if (r119 == null) goto L40;
        Iterator<Long> r1110 = r119.iterator();
    L35:
        if (r1110.hasNext() == false) goto L40;
        long r125 = r1110.next().longValue();
        ChatObject.Call r04 = this.groupCall;
        if (r04 == null) goto L35;
        r04.addInvitedUser(r125);
    L40:
        if (VoIPFragment.getInstance() == null) goto L42;
        VoIPFragment.getInstance().finish();
    L42:
        GroupCallActivity.create(LaunchActivity.instance, AccountInstance.getInstance(this.currentAccount), null, null, false, null);
        return;
    L29:
        e = move-exception;
        FileLog.e(e);
        goto L31
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$32(AccountInstance r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda8(this, r3, r2, r4));
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$33(TLObject r5, int r6, String r7, TLRPC.TL_error r8) {
        if ((r5 instanceof TL_phone.groupCall) == false) goto L6;
        TL_phone.groupCall r52 = (TL_phone.groupCall) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        this.groupCall.setCall(AccountInstance.getInstance(this.currentAccount), 0, r52);
        ConferenceCall r82 = this.conference;
        r82.groupCall = r52.call;
        r82.inputGroupCall = this.joinConference;
        startConferenceGroupCall(false, r6, r7, false);
        return;
    L6:
        if (r8 == null) goto L9;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.needShowAlert, new Object[]{6, r8.text});
        hangUp(0);
        return;
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$34(int r7, String r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda114(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$36(int r2) {
        this.mySource[0] = r2;
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$37(TLRPC.GroupCallParticipant r2) {
        this.mySource[0] = r2.source;
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$38(TLRPC.Updates r5, long r6) {
        TLRPC.Update r0 = r5.update;
        int r2 = 0;
        if ((r0 instanceof TL_update.TL_updateNewMessage) == false) goto L10;
        TLRPC.Message r52 = ((TL_update.TL_updateNewMessage) r0).message;
        if (r52 != null) goto L7;
    L28:
        ChatObject.Call r53 = this.groupCall;
        if (r53 == null) goto L39;
        if (r2 == 0) goto L40;
        r53.invitedUsersMessageIds.put(Long.valueOf(r6), ChatObject.Call.InvitedUser.make(r2));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{0L, Long.valueOf(this.groupCall.call.id), Boolean.FALSE});
        return;
    L40:
        return;
    L39:
        return;
    L7:
        if ((r52.action instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L28;
        r2 = r52.id;
        goto L28
    L10:
        if ((r0 instanceof TL_update.TL_updateMessageID) == false) goto L13;
        r2 = ((TL_update.TL_updateMessageID) r0).id;
        goto L28
    L13:
        if (r5.updates == null) goto L28;
        int r02 = 0;
    L16:
        if (r02 >= r5.updates.size()) goto L28;
        TLRPC.Update r1 = r5.updates.get(r02);
        if ((r1 instanceof TL_update.TL_updateNewMessage) == false) goto L25;
        TLRPC.Message r12 = ((TL_update.TL_updateNewMessage) r1).message;
        if (r12 == null) goto L27;
        if ((r12.action instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L27;
        r2 = r12.id;
    L27:
        r02 = r02 + 1;
        goto L16
    L25:
        if ((r1 instanceof TL_update.TL_updateMessageID) == false) goto L27;
        r2 = ((TL_update.TL_updateMessageID) r1).id;
        goto L28
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$39(ArrayList r9, ArrayList r10, ArrayList r11, String r12) {
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 != null) goto L5;
        return;
    L5:
        LimitReachedBottomSheet r0 = new LimitReachedBottomSheet(r1, r1.getContext(), 34, this.currentAccount, new DarkBlueThemeResourcesProvider());
        r0.setRestrictedUsers(null, r9, r10, r11, r12);
        r0.show();
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$42(HashSet r9, String r10) {
        TL_account.getRequirementsToContact r0 = new TL_account.getRequirementsToContact();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        Iterator r92 = r9.iterator();
    L4:
        if (r92.hasNext() == false) goto L9;
        Long r1 = (Long) r92.next();
        long r6 = r1.longValue();
        ChatObject.Call r2 = this.groupCall;
        if (r2 == null) goto L8;
        r2.removeInvitedUser(r6);
    L8:
        r3.add(MessagesController.getInstance(this.currentAccount).getUser(r1));
        r0.id.add(MessagesController.getInstance(this.currentAccount).getInputUser(r6));
        goto L4
    L9:
        VoIPService$$ExternalSyntheticLambda31 r12 = new VoIPService$$ExternalSyntheticLambda31(this, r3, r4, r5, r10);
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L13;
        r12.run();
        return;
    L13:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new VoIPService$$ExternalSyntheticLambda32(r3, r4, r12));
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$43(long r2, HashSet r4, AtomicInteger r5, int r6, String r7, TLObject r8, TLRPC.TL_error r9) {
        if ((r8 instanceof TLRPC.Updates) == false) goto L5;
        TLRPC.Updates r82 = (TLRPC.Updates) r8;
        MessagesController.getInstance(this.currentAccount).processUpdates(r82, false);
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda105(this, r82, r2));
    L10:
        if (r5.incrementAndGet() == r6) goto L12;
        return;
    L12:
        if (r4.isEmpty() == true) goto L16;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda106(this, r4, r7));
        return;
    L16:
        return;
    L5:
        if (r9 == null) goto L10;
        if ("USER_PRIVACY_RESTRICTED".equalsIgnoreCase(r9.text) == false) goto L10;
        r4.add(Long.valueOf(r2));
        goto L10
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$44(TLObject r13, TL_phone.PhoneCall r14, TL_phone.exportGroupCallInvite r15) {
        if ((r13 instanceof TL_phone.exportedGroupCallInvite) == false) goto L11;
        TL_phone.exportedGroupCallInvite r132 = (TL_phone.exportedGroupCallInvite) r13;
        String r0 = Uri.parse(r132.link).getPathSegments().get(r0.getPathSegments().size() - 1);
        TL_phone.discardCall r1 = new TL_phone.discardCall();
        TLRPC.TL_inputPhoneCall r2 = new TLRPC.TL_inputPhoneCall();
        r1.peer = r2;
        r2.access_hash = r14.access_hash;
        r2.id = r14.id;
        TLRPC.TL_phoneCallDiscardReasonMigrateConferenceCall r142 = new TLRPC.TL_phoneCallDiscardReasonMigrateConferenceCall();
        r1.reason = r142;
        r142.slug = r0;
        r1.duration = (int) (getCallDuration() / 1000);
        r1.connection_id = 0;
        r1.video = this.inviteUsersToConferenceVideo;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, null);
        String r11 = r132.link;
        int r10 = this.inviteUsersToConference.size();
        AtomicInteger r9 = new AtomicInteger(0);
        HashSet r8 = new HashSet();
        Iterator<Long> r133 = this.inviteUsersToConference.iterator();
    L6:
        if (r133.hasNext() == false) goto L8;
        long r6 = r133.next().longValue();
        TL_phone.inviteConferenceCallParticipant r143 = new TL_phone.inviteConferenceCallParticipant();
        r143.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r6);
        r143.call = r15.call;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r143, new VoIPService$$ExternalSyntheticLambda66(this, r6, r8, r9, r10, r11));
        goto L6
    L8:
        this.privateCall = null;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{0L, Long.valueOf(this.groupCall.call.id), Boolean.FALSE});
        return;
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$45(TL_phone.PhoneCall r1, TL_phone.exportGroupCallInvite r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda115(this, r3, r1, r2));
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$46(TLRPC.Updates r5, long r6) {
        TLRPC.Update r0 = r5.update;
        int r2 = 0;
        if ((r0 instanceof TL_update.TL_updateNewMessage) == false) goto L10;
        TLRPC.Message r52 = ((TL_update.TL_updateNewMessage) r0).message;
        if (r52 != null) goto L7;
    L28:
        ChatObject.Call r53 = this.groupCall;
        if (r53 == null) goto L39;
        if (r2 == 0) goto L40;
        r53.invitedUsersMessageIds.put(Long.valueOf(r6), ChatObject.Call.InvitedUser.make(r2));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{0L, Long.valueOf(this.groupCall.call.id), Boolean.FALSE});
        return;
    L40:
        return;
    L39:
        return;
    L7:
        if ((r52.action instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L28;
        r2 = r52.id;
        goto L28
    L10:
        if ((r0 instanceof TL_update.TL_updateMessageID) == false) goto L13;
        r2 = ((TL_update.TL_updateMessageID) r0).id;
        goto L28
    L13:
        if (r5.updates == null) goto L28;
        int r02 = 0;
    L16:
        if (r02 >= r5.updates.size()) goto L28;
        TLRPC.Update r1 = r5.updates.get(r02);
        if ((r1 instanceof TL_update.TL_updateNewMessage) == false) goto L25;
        TLRPC.Message r12 = ((TL_update.TL_updateNewMessage) r1).message;
        if (r12 == null) goto L27;
        if ((r12.action instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L27;
        r2 = r12.id;
    L27:
        r02 = r02 + 1;
        goto L16
    L25:
        if ((r1 instanceof TL_update.TL_updateMessageID) == false) goto L27;
        r2 = ((TL_update.TL_updateMessageID) r1).id;
        goto L28
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$47(ArrayList r9, ArrayList r10, ArrayList r11, String r12) {
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 != null) goto L5;
        return;
    L5:
        LimitReachedBottomSheet r0 = new LimitReachedBottomSheet(r1, r1.getContext(), 34, this.currentAccount, new DarkBlueThemeResourcesProvider());
        r0.setRestrictedUsers(null, r9, r10, r11, r12);
        r0.show();
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$50(HashSet r9, String r10) {
        TL_account.getRequirementsToContact r0 = new TL_account.getRequirementsToContact();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        Iterator r92 = r9.iterator();
    L4:
        if (r92.hasNext() == false) goto L9;
        Long r1 = (Long) r92.next();
        long r6 = r1.longValue();
        ChatObject.Call r2 = this.groupCall;
        if (r2 == null) goto L8;
        r2.removeInvitedUser(r6);
    L8:
        r3.add(MessagesController.getInstance(this.currentAccount).getUser(r1));
        r0.id.add(MessagesController.getInstance(this.currentAccount).getInputUser(r6));
        goto L4
    L9:
        VoIPService$$ExternalSyntheticLambda108 r12 = new VoIPService$$ExternalSyntheticLambda108(this, r3, r4, r5, r10);
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L13;
        r12.run();
        return;
    L13:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new VoIPService$$ExternalSyntheticLambda109(r3, r4, r12));
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$51(long r2, HashSet r4, AtomicInteger r5, int r6, String r7, TLObject r8, TLRPC.TL_error r9) {
        if ((r8 instanceof TLRPC.Updates) == false) goto L5;
        TLRPC.Updates r82 = (TLRPC.Updates) r8;
        MessagesController.getInstance(this.currentAccount).processUpdates(r82, false);
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda39(this, r82, r2));
    L10:
        if (r5.incrementAndGet() == r6) goto L12;
        return;
    L12:
        if (r4.isEmpty() == true) goto L16;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda40(this, r4, r7));
        return;
    L16:
        return;
    L5:
        if (r9 == null) goto L10;
        if ("USER_PRIVACY_RESTRICTED".equalsIgnoreCase(r9.text) == false) goto L10;
        r4.add(Long.valueOf(r2));
        goto L10
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$52(ArrayList r17, boolean r18) {
        ConferenceCall r0 = this.conference;
        if (r0 == null) goto L39;
        r0.joined();
        int r02 = r17.size();
        int r3 = 0;
    L7:
        if (r3 >= r02) goto L9;
        Object r6 = r17.get(r3);
        r3 = r3 + 1;
        this.conference.applyUpdate(0, (TL_update.TL_updateGroupCallChainBlocks) r6, true, null);
        goto L7
    L9:
        this.conference.forcePoll();
        this.groupCall.loadMembers(r18);
        startGroupCheckShortpoll();
        if (this.captureDevice[1] == 0) goto L12;
        createGroupInstance(1, false, true);
    L12:
        int r03 = 0;
    L14:
        if (r03 >= 2) goto L19;
        NativeInstance r32 = this.tgVoip[r03];
        if (r32 == null) goto L18;
        r32.setConferenceCallId(this.conference.getCallId());
    L18:
        r03 = r03 + 1;
        goto L14
    L19:
        AndroidUtilities.runOnUIThread(this.destroyConvertingRunnable, 5000);
        if (this.inviteUsersToConference == null) goto L26;
        TL_phone.PhoneCall r04 = this.privateCall;
        if (r04 == null) goto L26;
        if (this.groupCall == null) goto L26;
        TL_phone.exportGroupCallInvite r33 = new TL_phone.exportGroupCallInvite();
        TLRPC.TL_inputGroupCall r4 = new TLRPC.TL_inputGroupCall();
        r33.call = r4;
        TLRPC.GroupCall r5 = this.groupCall.call;
        r4.id = r5.id;
        r4.access_hash = r5.access_hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r33, new VoIPService$$ExternalSyntheticLambda112(this, r04, r33));
    L26:
        long[] r05 = this.inviteUsers;
        if (r05 == null) goto L38;
        String r7 = this.groupCall.call.invite_link;
        int r62 = r05.length;
        AtomicInteger r52 = new AtomicInteger(0);
        HashSet r42 = new HashSet();
        long[] r11 = this.inviteUsers;
        int r12 = r11.length;
        int r13 = 0;
    L29:
        if (r13 >= r12) goto L31;
        long r2 = r11[r13];
        this.groupCall.addInvitedUser(r2);
        TL_phone.inviteConferenceCallParticipant r14 = new TL_phone.inviteConferenceCallParticipant();
        r14.call = this.groupCall.getInputGroupCall();
        r14.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r2);
        r14.video = this.videoCall;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r14, new VoIPService$$ExternalSyntheticLambda113(this, r2, r42, r52, r62, r7));
        r13 = r13 + 1;
        goto L29
    L31:
        this.inviteUsers = null;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{0L, Long.valueOf(this.groupCall.call.id), Boolean.FALSE});
        return;
    L38:
        return;
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$53(TLRPC.TL_error r4, String r5) {
        if ("JOIN_AS_PEER_INVALID".equals(r4.text) == false) goto L7;
        hangUp(2);
        return;
    L7:
        if ("GROUPCALL_SSRC_DUPLICATE_MUCH".equals(r4.text) == false) goto L11;
        startConferenceGroupCall(false, 0, r5, false);
        return;
    L11:
        if (r4.text.startsWith("CONF_WRITE_CHAIN_INVALID") == false) goto L15;
        stopSelf();
        return;
    L15:
        if ("GROUPCALL_INVALID".equals(r4.text) == false) goto L21;
        BaseFragment r42 = LaunchActivity.getSafeLastFragment();
        if (r42 == null) goto L19;
        BulletinFactory.of(r42).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.ConferenceClosed)).show().hideAfterBottomSheet = false;
    L19:
        hangUp(0);
        return;
    L21:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.needShowAlert, new Object[]{6, r4.text});
        hangUp(0);
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$54(int r11, boolean r12, String r13, TLObject r14, TLRPC.TL_error r15) {
        if (r14 == null) goto L28;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda11(this, r11));
        TLRPC.Updates r142 = (TLRPC.Updates) r14;
        long r0 = getSelfId();
        ArrayList r112 = MessagesController.findUpdatesAndRemove(r142, TL_update.TL_updateGroupCallChainBlocks.class);
        ArrayList r132 = MessagesController.findUpdates(r142, TL_update.TL_updateGroupCall.class);
        int r152 = r132.size();
        int r3 = 0;
    L4:
        if (r3 >= r152) goto L11;
        Object r4 = r132.get(r3);
        r3 = r3 + 1;
        TLRPC.GroupCall r42 = ((TL_update.TL_updateGroupCall) r4).call;
        ChatObject.Call r5 = this.groupCall;
        if (r5 == null) goto L8;
        r5.setCall(AccountInstance.getInstance(this.currentAccount), 0, r42);
    L8:
        ConferenceCall r52 = this.conference;
        if (r52 == null) goto L4;
        r52.groupCall = TlUtils.applyGroupCallUpdate(r52.groupCall, r42);
        goto L4
    L11:
        ArrayList r133 = MessagesController.findUpdates(r142, TL_update.TL_updateGroupCallParticipants.class);
        int r153 = r133.size();
        int r32 = 0;
    L12:
        if (r32 >= r153) goto L21;
        Object r43 = r133.get(r32);
        r32 = r32 + 1;
        TL_update.TL_updateGroupCallParticipants r44 = (TL_update.TL_updateGroupCallParticipants) r43;
        int r53 = r44.participants.size();
        int r6 = 0;
    L14:
        if (r6 >= r53) goto L12;
        TLRPC.GroupCallParticipant r7 = r44.participants.get(r6);
        if (MessageObject.getPeerId(r7.peer) == r0) goto L17;
        r6 = r6 + 1;
        goto L14
    L17:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda12(this, r7));
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("join source = " + r7.source);
        goto L12
    L21:
        ArrayList r134 = MessagesController.findUpdates(r142, TL_update.TL_updateGroupCallConnection.class);
        int r154 = r134.size();
        int r02 = 0;
    L22:
        if (r02 >= r154) goto L26;
        Object r1 = r134.get(r02);
        r02 = r02 + 1;
        TL_update.TL_updateGroupCallConnection r16 = (TL_update.TL_updateGroupCallConnection) r1;
        if (r16.presentation == true) goto L22;
        this.myParams = r16.params;
        goto L22
    L26:
        MessagesController.getInstance(this.currentAccount).processUpdates(r142, false);
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda13(this, r112, r12));
        return;
    L28:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda14(this, r15, r13));
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$55(String r5, int r6, boolean r7) {
        TL_phone.joinGroupCall r0 = new TL_phone.joinGroupCall();
        r0.muted = this.micMute;
        boolean r2 = false;
        if (this.videoState[0] == 2) goto L5;
        r2 = true;
    L5:
        r0.video_stopped = r2;
        ConferenceCall r1 = this.conference;
        if (r1 == null) goto L8;
        r0.call = r1.inputGroupCall;
        r0.flags |= 8;
        r0.public_key = r1.getMyPublicKey();
        r0.block = this.conference.getLastBlock();
    L11:
        TLRPC.TL_dataJSON r12 = new TLRPC.TL_dataJSON();
        r0.params = r12;
        r12.data = r5;
        if (TextUtils.isEmpty(this.joinHash) == true) goto L14;
        r0.invite_hash = this.joinHash;
        r0.flags |= 2;
    L14:
        TLRPC.InputPeer r13 = this.groupCallPeer;
        if (r13 == null) goto L17;
        r0.join_as = r13;
    L18:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new VoIPService$$ExternalSyntheticLambda44(this, r6, r7, r5));
        return;
    L17:
        TLRPC.TL_inputPeerUser r14 = new TLRPC.TL_inputPeerUser();
        r0.join_as = r14;
        r14.user_id = AccountInstance.getInstance(this.currentAccount).getUserConfig().getClientUserId();
        goto L18
    L8:
        ChatObject.Call r15 = this.groupCall;
        if (r15 == null) goto L11;
        r0.call = r15.getInputGroupCall();
        goto L11
    }

    private /* synthetic */ void lambda$startConferenceGroupCall$56(Runnable r4) {
        ConferenceCall r0 = this.conference;
        if (r0 == null) goto L12;
        r0.generateAddSelfBlock();
        r4.run();
        int r42 = 0;
    L7:
        if (r42 >= 2) goto L16;
        NativeInstance r02 = this.tgVoip[r42];
        if (r02 == null) goto L11;
        r02.setConferenceCallId(this.conference.getCallId());
    L11:
        r42 = r42 + 1;
        goto L7
    L16:
        return;
    }

    private /* synthetic */ void lambda$startConnectingSound$89() {
        int r0 = this.spPlayId;
        if (r0 == 0) goto L5;
        this.soundPool.stop(r0);
    L5:
        int r02 = this.soundPool.play(this.spConnectingId, 1.0f, 1.0f, 0, -1, 1.0f);
        this.spPlayId = r02;
        if (r02 != 0) goto L9;
        AnonymousClass9 r03 = new AnonymousClass9(this);
        this.connectingSoundRunnable = r03;
        AndroidUtilities.runOnUIThread(r03, 100);
        return;
    }

    private /* synthetic */ void lambda$startGroupCall$21(TL_update.TL_updateGroupCall r4) {
        if (sharedInstance != null) goto L5;
        return;
    L5:
        TLRPC.GroupCall r0 = this.groupCall.call;
        TLRPC.GroupCall r42 = r4.call;
        r0.access_hash = r42.access_hash;
        r0.id = r42.id;
        MessagesController r43 = MessagesController.getInstance(this.currentAccount);
        ChatObject.Call r02 = this.groupCall;
        r43.putGroupCall(r02.chatId, r02);
        startGroupCall(0, null, false);
    }

    private /* synthetic */ void lambda$startGroupCall$22(TLRPC.TL_error r4) {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.needShowAlert, new Object[]{6, r4.text});
        hangUp(0);
    }

    private /* synthetic */ void lambda$startGroupCall$23(TLObject r4, TLRPC.TL_error r5) {
        if (r4 != null) goto L18;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda28(this, r5));
        return;
    L18:
        this.groupCallBottomSheetLatch.await(800, TimeUnit.MILLISECONDS);     // Catch: InterruptedException -> L5
    L7:
        TLRPC.Updates r42 = (TLRPC.Updates) r4;
        int r0 = 0;
    L9:
        if (r0 >= r42.updates.size()) goto L14;
        TLRPC.Update r1 = r42.updates.get(r0);
        if ((r1 instanceof TL_update.TL_updateGroupCall) == true) goto L12;
        r0 = r0 + 1;
        goto L9
    L12:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda27(this, (TL_update.TL_updateGroupCall) r1));
    L14:
        MessagesController.getInstance(this.currentAccount).processUpdates(r42, false);
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$startGroupCall$25(int r2) {
        this.mySource[0] = r2;
    }

    private /* synthetic */ void lambda$startGroupCall$26(TLRPC.GroupCallParticipant r2) {
        this.mySource[0] = r2.source;
    }

    private /* synthetic */ void lambda$startGroupCall$27(boolean r1) {
        this.groupCall.loadMembers(r1);
    }

    private /* synthetic */ void lambda$startGroupCall$28(TLRPC.TL_error r6) {
        if ("JOIN_AS_PEER_INVALID".equals(r6.text) == false) goto L16;
        if (this.chat == null) goto L13;
        TLRPC.ChatFull r62 = MessagesController.getInstance(this.currentAccount).getChatFull(this.chat.id);
        if (r62 == null) goto L13;
        boolean r0 = r62 instanceof TLRPC.TL_chatFull;
        int r1 = r62.flags;
        if (r0 == false) goto L11;
        r62.flags = (-32769) & r1;
    L12:
        r62.groupcall_default_join_as = null;
        JoinCallAlert.resetCache();
        goto L13
    L11:
        r62.flags = (-67108865) & r1;
    L13:
        hangUp(2);
        return;
    L16:
        if ("GROUPCALL_SSRC_DUPLICATE_MUCH".equals(r6.text) == false) goto L20;
        createGroupInstance(0, false, false);
        return;
    L20:
        if ("GROUPCALL_INVALID".equals(r6.text) == true) goto L22;
    L24:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.needShowAlert, new Object[]{6, r6.text});
        hangUp(0);
        return;
    L22:
        if (this.chat == null) goto L24;
        MessagesController.getInstance(this.currentAccount).loadFullChat(this.chat.id, 0, true);
        goto L24
    }

    private /* synthetic */ void lambda$startGroupCall$29(int r10, boolean r11, TLObject r12, TLRPC.TL_error r13) {
        if (r12 == null) goto L23;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda22(this, r10));
        TLRPC.Updates r122 = (TLRPC.Updates) r12;
        long r0 = getSelfId();
        int r102 = r122.updates.size();
        int r2 = 0;
    L4:
        if (r2 >= r102) goto L21;
        TLRPC.Update r3 = r122.updates.get(r2);
        if ((r3 instanceof TL_update.TL_updateGroupCallParticipants) == false) goto L16;
        TL_update.TL_updateGroupCallParticipants r32 = (TL_update.TL_updateGroupCallParticipants) r3;
        int r4 = r32.participants.size();
        int r5 = 0;
    L8:
        if (r5 >= r4) goto L20;
        TLRPC.GroupCallParticipant r6 = r32.participants.get(r5);
        if (MessageObject.getPeerId(r6.peer) == r0) goto L11;
        r5 = r5 + 1;
        goto L8
    L11:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda23(this, r6));
        if (BuildVars.LOGS_ENABLED == false) goto L20;
        FileLog.d("join source = " + r6.source);
    L20:
        r2 = r2 + 1;
        goto L4
    L16:
        if ((r3 instanceof TL_update.TL_updateGroupCallConnection) == false) goto L20;
        TL_update.TL_updateGroupCallConnection r33 = (TL_update.TL_updateGroupCallConnection) r3;
        if (r33.presentation == true) goto L20;
        this.myParams = r33.params;
        goto L20
    L21:
        MessagesController.getInstance(this.currentAccount).processUpdates(r122, false);
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda24(this, r11));
        startGroupCheckShortpoll();
        return;
    L23:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda25(this, r13));
    }

    private /* synthetic */ void lambda$startGroupCheckShortpoll$61(TLObject r5, TLRPC.TL_error r6) {
        if (this.shortPollRunnable != null) goto L5;
        return;
    L5:
        if (sharedInstance != null) goto L7;
        return;
    L7:
        if (this.groupCall != null) goto L10;
        return;
    L10:
        if ((r5 instanceof TL_phone.groupCall) == false) goto L13;
        TL_phone.groupCall r52 = (TL_phone.groupCall) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        this.groupCall.setCall(AccountInstance.getInstance(this.currentAccount), 0, r52.call);
        this.groupCall.loadMembers(true);
        this.shortPollRunnable = null;
        this.checkRequestId = 0;
        startGroupCheckShortpoll();
        return;
    L13:
        if (r6 != null) goto L15;
        return;
    L15:
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r6.text) == false) goto L21;
        stopSelf();
        return;
    }

    private /* synthetic */ void lambda$startGroupCheckShortpoll$62(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda123(this, r2, r3));
    }

    private /* synthetic */ void lambda$startGroupCheckShortpoll$63(TLRPC.TL_error r5, TLObject r6, TL_phone.checkGroupCall r7) {
        if (this.shortPollRunnable != null) goto L5;
        return;
    L5:
        if (sharedInstance != null) goto L7;
        return;
    L7:
        if (this.groupCall != null) goto L10;
        return;
    L10:
        if (this.conference == null) goto L20;
        if (r5 == null) goto L20;
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r5.text) == false) goto L20;
        if (this.groupCall.getInputGroupCall(true) == null) goto L20;
        if (this.groupCall.getInputGroupCall(false) == null) goto L20;
        TL_phone.getGroupCall r52 = new TL_phone.getGroupCall();
        r52.call = this.groupCall.getInputGroupCall(false);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new VoIPService$$ExternalSyntheticLambda41(this));
        return;
    L20:
        this.shortPollRunnable = null;
        this.checkRequestId = 0;
        if ((r6 instanceof Vector) == false) goto L37;
        ArrayList<Integer> r53 = ((Vector) r6).toIntArray();
        int r62 = this.mySource[0];
        if (r62 != 0) goto L25;
    L29:
        boolean r63 = false;
    L30:
        int r0 = this.mySource[1];
        if (r0 != 0) goto L33;
    L47:
        boolean r54 = false;
    L49:
        if (r63 == false) goto L51;
        createGroupInstance(0, false, false);
    L51:
        if (r54 == false) goto L53;
        createGroupInstance(1, false, false);
    L53:
        int[] r55 = this.mySource;
        if (r55[1] == 0) goto L56;
    L61:
        startGroupCheckShortpoll();
        return;
    L56:
        if (r55[0] != 0) goto L61;
        TLRPC.GroupCall r56 = this.groupCall.call;
        if (r56 != null) goto L60;
        return;
    L60:
        if (r56.rtmp_stream == true) goto L61;
        return;
    L33:
        if (r7.sources.contains(Integer.valueOf(r0)) == false) goto L47;
        if (r53.contains(Integer.valueOf(this.mySource[1])) == true) goto L47;
        r54 = true;
        goto L49
    L25:
        if (r7.sources.contains(Integer.valueOf(r62)) == false) goto L29;
        if (r53.contains(Integer.valueOf(this.mySource[0])) == true) goto L29;
        r63 = true;
        goto L30
    L37:
        if (r5 != null) goto L39;
    L48:
        r54 = false;
    L45:
        r63 = r54;
        goto L49
    L39:
        if (r5.code != 400) goto L48;
        int r57 = this.mySource[1];
        if (r57 != 0) goto L43;
    L46:
        r63 = true;
        goto L47
    L43:
        if (r7.sources.contains(Integer.valueOf(r57)) == false) goto L46;
        r54 = true;
        goto L45
    }

    private /* synthetic */ void lambda$startGroupCheckShortpoll$64(TL_phone.checkGroupCall r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda89(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$startGroupCheckShortpoll$65() {
        if (this.shortPollRunnable != null) goto L5;
        return;
    L5:
        if (sharedInstance == null) goto L34;
        ChatObject.Call r0 = this.groupCall;
        if (r0 == null) goto L35;
        int[] r1 = this.mySource;
        int r2 = 0;
        if (r1[0] == 0) goto L11;
    L17:
        TL_phone.checkGroupCall r02 = new TL_phone.checkGroupCall();
        TLRPC.InputGroupCall r12 = this.groupCall.getInputGroupCall(true);
        ChatObject.Call r3 = this.groupCall;
        if (r12 == null) goto L20;
        r02.call = r3.getInputGroupCall(true);
    L21:
        int[] r13 = this.mySource;
        if (r2 >= r13.length) goto L27;
        int r14 = r13[r2];
        if (r14 == 0) goto L26;
        r02.sources.add(Integer.valueOf(r14));
    L26:
        r2 = r2 + 1;
        goto L21
    L27:
        this.checkRequestId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new VoIPService$$ExternalSyntheticLambda104(this, r02));
        return;
    L20:
        r02.call = r3.getInputGroupCall(false);
        goto L21
    L11:
        if (r1[1] != 0) goto L17;
        TLRPC.GroupCall r03 = r0.call;
        if (r03 != null) goto L15;
        return;
    L15:
        if (r03.rtmp_stream == true) goto L17;
        return;
    L35:
        return;
    }

    private /* synthetic */ void lambda$startOutgoingCall$10(byte[] r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda65(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$startOutgoingCall$11(MessagesStorage r8, TLObject r9, TLRPC.TL_error r10) {
        this.callReqId = 0;
        if (this.endCallAfterRequest == false) goto L6;
        callEnded();
        return;
    L6:
        if (r10 != null) goto L23;
        TLRPC.messages_DhConfig r102 = (TLRPC.messages_DhConfig) r9;
        if ((r9 instanceof TLRPC.TL_messages_dhConfig) == true) goto L10;
    L14:
        byte[] r1 = new byte[256];
        int r2 = 0;
    L15:
        if (r2 >= 256) goto L17;
        r1[r2] = (byte) (((byte) (Utilities.random.nextDouble() * 256.0d)) ^ r102.random[r2]);
        r2 = r2 + 1;
        goto L15
    L17:
        byte[] r82 = BigInteger.valueOf(r8.getSecretG()).modPow(new BigInteger(1, r1), new BigInteger(1, r8.getSecretPBytes())).toByteArray();
        if (r82.length <= 256) goto L20;
        byte[] r103 = new byte[256];
        System.arraycopy(r82, 1, r103, 0, 256);
        r82 = r103;
    L20:
        TL_phone.requestCall r92 = new TL_phone.requestCall();
        r92.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(this.user);
        TL_phone.TL_phoneCallProtocol r104 = new TL_phone.TL_phoneCallProtocol();
        r92.protocol = r104;
        r92.video = this.videoCall;
        r104.udp_p2p = true;
        r104.udp_reflector = true;
        r104.min_layer = 65;
        r104.max_layer = Instance.getConnectionMaxLayer();
        Collections.addAll(r92.protocol.library_versions, NativeInstance.getAllVersions());
        this.g_a = r82;
        r92.g_a_hash = Utilities.computeSHA256(r82, 0, r82.length);
        r92.random_id = Utilities.random.nextInt();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r92, new VoIPService$$ExternalSyntheticLambda49(this, r1), 65536);
        return;
    L10:
        if (Utilities.isGoodPrime(r102.p, r102.g) == true) goto L13;
        callFailed();
        return;
    L13:
        r8.setSecretPBytes(r102.p);
        r8.setSecretG(r102.g);
        r8.setLastSecretVersion(r102.version);
        r8.saveSecretParams(r8.getLastSecretVersion(), r8.getSecretG(), r8.getSecretPBytes());
        goto L14
    L23:
        if (BuildVars.LOGS_ENABLED == false) goto L25;
        FileLog.e("Error on getDhConfig " + r10);
    L25:
        callFailed();
    }

    private /* synthetic */ void lambda$startOutgoingCall$7(TLObject r2, TLRPC.TL_error r3) {
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        if (r3 == null) goto L6;
        FileLog.e("error on phone.discardCall: " + r3);
        goto L7
    L6:
        FileLog.d("phone.discardCall " + r2);
    L7:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda60(this));
    }

    private /* synthetic */ void lambda$startOutgoingCall$8() {
        this.timeoutRunnable = null;
        TL_phone.discardCall r0 = new TL_phone.discardCall();
        TLRPC.TL_inputPhoneCall r1 = new TLRPC.TL_inputPhoneCall();
        r0.peer = r1;
        TL_phone.PhoneCall r2 = this.privateCall;
        r1.access_hash = r2.access_hash;
        r1.id = r2.id;
        r0.reason = new TLRPC.TL_phoneCallDiscardReasonMissed();
        FileLog.e("discardCall " + r0.reason);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new VoIPService$$ExternalSyntheticLambda29(this), 65536);
    }

    private /* synthetic */ void lambda$startOutgoingCall$9(TLRPC.TL_error r2, TLObject r3, byte[] r4) {
        if (r2 != null) goto L18;
        this.privateCall = ((TL_phone.TL_phone_phoneCall) r3).phone_call;
        this.a_or_b = r4;
        dispatchStateChanged(13);
        if (this.endCallAfterRequest == false) goto L8;
        hangUp();
        return;
    L8:
        if (this.pendingUpdates.size() > 0) goto L10;
    L15:
        VoIPService$$ExternalSyntheticLambda34 r22 = new VoIPService$$ExternalSyntheticLambda34(this);
        this.timeoutRunnable = r22;
        AndroidUtilities.runOnUIThread(r22, MessagesController.getInstance(this.currentAccount).callReceiveTimeout);
        return;
    L10:
        if (this.privateCall == null) goto L15;
        ArrayList<TL_phone.PhoneCall> r23 = this.pendingUpdates;
        int r32 = r23.size();
        int r42 = 0;
    L12:
        if (r42 >= r32) goto L14;
        TL_phone.PhoneCall r0 = r23.get(r42);
        r42 = r42 + 1;
        onCallUpdated(r0);
        goto L12
    L14:
        this.pendingUpdates.clear();
        goto L15
    L18:
        if (r2.code == 400) goto L20;
    L23:
        int r33 = r2.code;
        if (r33 != 403) goto L28;
        callFailed(Instance.ERROR_PRIVACY);
        return;
    L28:
        if (r33 != 406) goto L32;
        callFailed(Instance.ERROR_LOCALIZED);
        return;
    L32:
        if (BuildVars.LOGS_ENABLED == false) goto L34;
        FileLog.e("Error on phone.requestCall: " + r2);
    L34:
        callFailed();
        return;
    L20:
        if ("PARTICIPANT_VERSION_OUTDATED".equals(r2.text) == false) goto L23;
        callFailed(Instance.ERROR_PEER_OUTDATED);
    }

    private /* synthetic */ void lambda$startRingtoneAndVibration$96(MediaPlayer r1) {
        this.ringtonePlayer.start();     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        FileLog.e(th);
    }

    private /* synthetic */ void lambda$startScreenCapture$57(int r2) {
        this.mySource[1] = r2;
    }

    private /* synthetic */ void lambda$startScreenCapture$58(TLRPC.Updates r13) {
        if (this.tgVoip[1] == null) goto L30;
        long r2 = getSelfId();
        int r0 = r13.updates.size();
        int r5 = 0;
    L5:
        if (r5 >= r0) goto L42;
        TLRPC.Update r6 = r13.updates.get(r5);
        if ((r6 instanceof TL_update.TL_updateGroupCallConnection) == false) goto L12;
        TL_update.TL_updateGroupCallConnection r62 = (TL_update.TL_updateGroupCallConnection) r6;
        if (r62.presentation == false) goto L29;
        this.tgVoip[1].setJoinResponsePayload(r62.params.data);
    L29:
        r5 = r5 + 1;
        goto L5
    L12:
        if ((r6 instanceof TL_update.TL_updateGroupCallParticipants) == false) goto L29;
        TL_update.TL_updateGroupCallParticipants r63 = (TL_update.TL_updateGroupCallParticipants) r6;
        int r7 = r63.participants.size();
        int r8 = 0;
    L14:
        if (r8 >= r7) goto L29;
        TLRPC.GroupCallParticipant r9 = r63.participants.get(r8);
        if (MessageObject.getPeerId(r9.peer) == r2) goto L17;
        r8 = r8 + 1;
        goto L14
    L17:
        TLRPC.TL_groupCallParticipantVideo r64 = r9.presentation;
        if (r64 == null) goto L29;
        if ((r64.flags & 2) == 0) goto L22;
        this.mySource[1] = r64.audio_source;
        goto L29
    L22:
        int r65 = r64.source_groups.size();
        int r72 = 0;
    L23:
        if (r72 >= r65) goto L29;
        TLRPC.TL_groupCallParticipantVideoSourceGroup r82 = r9.presentation.source_groups.get(r72);
        if (r82.sources.size() <= 0) goto L27;
        this.mySource[1] = r82.sources.get(0).intValue();
    L27:
        r72 = r72 + 1;
        goto L23
    L42:
        return;
    }

    private /* synthetic */ void lambda$startScreenCapture$59(TLRPC.TL_error r6) {
        if ("GROUPCALL_VIDEO_TOO_MUCH".equals(r6.text) == false) goto L7;
        this.groupCall.reloadGroupCall();
        return;
    L7:
        if ("JOIN_AS_PEER_INVALID".equals(r6.text) == false) goto L20;
        if (this.chat == null) goto L17;
        TLRPC.ChatFull r62 = MessagesController.getInstance(this.currentAccount).getChatFull(this.chat.id);
        if (r62 == null) goto L17;
        boolean r0 = r62 instanceof TLRPC.TL_chatFull;
        int r1 = r62.flags;
        if (r0 == false) goto L15;
        r62.flags = (-32769) & r1;
    L16:
        r62.groupcall_default_join_as = null;
        JoinCallAlert.resetCache();
        goto L17
    L15:
        r62.flags = (-67108865) & r1;
    L17:
        hangUp(2);
        return;
    L20:
        if ("GROUPCALL_SSRC_DUPLICATE_MUCH".equals(r6.text) == false) goto L24;
        createGroupInstance(1, false, false);
        return;
    L24:
        if ("GROUPCALL_INVALID".equals(r6.text) == true) goto L26;
        return;
    L26:
        if (this.chat == null) goto L30;
        MessagesController.getInstance(this.currentAccount).loadFullChat(this.chat.id, 0, true);
        return;
    }

    private /* synthetic */ void lambda$startScreenCapture$60(int r1, TLObject r2, TLRPC.TL_error r3) {
        if (r2 == null) goto L5;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda68(this, r1));
        TLRPC.Updates r22 = (TLRPC.Updates) r2;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda69(this, r22));
        MessagesController.getInstance(this.currentAccount).processUpdates(r22, false);
        startGroupCheckShortpoll();
        return;
    L5:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda70(this, r3));
    }

    private /* synthetic */ void lambda$stopScreenCapture$15(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        r0.processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$switchToSpeaker$91(Boolean r2, Boolean r3) {
        updateOutputGainControlState();
        ArrayList<StateListener> r1 = this.stateListeners;
        int r22 = r1.size();
        int r32 = 0;
    L3:
        if (r32 >= r22) goto L5;
        StateListener r0 = r1.get(r32);
        r32 = r32 + 1;
        r0.onAudioSettingsChanged();
        goto L3
    }

    private /* synthetic */ void lambda$switchToSpeaker$92() {
        VoipAudioManager r0 = VoipAudioManager.get();
        if (isBluetoothHeadsetConnected() == false) goto L7;
        if (hasEarpiece() == false) goto L7;
        return;
    L7:
        if (this.isHeadsetPlugged == false) goto L9;
        return;
    L9:
        if (isSpeakerphoneOn() == true) goto L15;
        r0.setSpeakerphoneOn(true);
        r0.isBluetoothAndSpeakerOnAsync(new VoIPService$$ExternalSyntheticLambda100(this));
        return;
    }

    private /* synthetic */ void lambda$toggleSpeakerphoneOrShowRouteSheet$93(DialogInterface r1, int r2) {
        if (getSharedInstance() != null) goto L5;
        return;
    L5:
        setAudioOutput(r2);
    }

    private /* synthetic */ void lambda$toggleSpeakerphoneOrShowRouteSheet$95(Boolean r2, Boolean r3) {
        updateOutputGainControlState();
        ArrayList<StateListener> r1 = this.stateListeners;
        int r22 = r1.size();
        int r32 = 0;
    L3:
        if (r32 >= r22) goto L5;
        StateListener r0 = r1.get(r32);
        r32 = r32 + 1;
        r0.onAudioSettingsChanged();
        goto L3
    }

    private /* synthetic */ void lambda$updateConnectionState$81(int r3) {
        if (this.switchingStreamTimeoutRunnable != null) goto L5;
        return;
    L5:
        this.switchingStream = false;
        updateConnectionState(r3, 0, true);
        this.switchingStreamTimeoutRunnable = null;
    }

    private /* synthetic */ void lambda$updateConnectionState$82() {
        int r0 = this.spPlayId;
        if (r0 == 0) goto L5;
        this.soundPool.stop(r0);
    L5:
        this.spPlayId = this.soundPool.play(this.spVoiceChatConnecting, 1.0f, 1.0f, 0, -1, 1.0f);
    }

    private /* synthetic */ void lambda$updateConnectionState$83() {
        int r0 = this.spPlayId;
        if (r0 == 0) goto L6;
        this.soundPool.stop(r0);
        this.spPlayId = 0;
        return;
    }

    private void loadResources() {
        WebRtcAudioTrack.setAudioTrackUsageAttribute(2);
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda30(this));
    }

    private void onTgVoipPreStop() {
    }

    private void onTgVoipStop(Instance.FinalState r11) {
        if (this.user != null) goto L5;
        return;
    L5:
        if (this.privateCall == null) goto L24;
        if (r11 != null) goto L9;
        return;
    L9:
        if (TextUtils.isEmpty(r11.debugLog) == true) goto L21;
    L14:
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        if (r0.voipDebug != null) goto L17;
        r0.voipDebug = new VoIPDebugToSend(this.currentAccount);
    L17:
        VoIPDebugToSend r3 = r0.voipDebug;
        TL_phone.PhoneCall r1 = this.privateCall;
        r3.push(r1.id, r1.access_hash, r11, this.lastLogFilePath);
        this.lastLogFilePath = null;
        boolean r112 = this.needSendDebugLog;
        if (r112 == false) goto L25;
        r0.voipDebug.done(this.privateCall.id, r112);
        this.needSendDebugLog = false;
        return;
    L25:
        return;
    L21:
        r11.debugLog = getStringFromFile(VoIPHelper.getLogFilePath(_UrlKt.FRAGMENT_ENCODE_SET + this.privateCall.id, true));     // Catch: Exception -> L12
    L12:
        e = move-exception;
        e.printStackTrace();
        goto L14
    }

    private void processAcceptedCall() {
        dispatchStateChanged(12);
        BigInteger r0 = new BigInteger(1, MessagesStorage.getInstance(this.currentAccount).getSecretPBytes());
        BigInteger r1 = new BigInteger(1, this.privateCall.g_b);
        if (Utilities.isGoodGaAndGb(r1, r0) == false) goto L5;
        byte[] r02 = r1.modPow(new BigInteger(1, this.a_or_b), r0).toByteArray();
        if (r02.length <= 256) goto L14;
        byte[] r12 = new byte[256];
        System.arraycopy(r02, r02.length - 256, r12, 0, 256);
    L12:
        r02 = r12;
    L19:
        byte[] r13 = Utilities.computeSHA1(r02);
        byte[] r5 = new byte[8];
        System.arraycopy(r13, r13.length - 8, r5, 0, 8);
        long r3 = Utilities.bytesToLong(r5);
        this.authKey = r02;
        this.keyFingerprint = r3;
        TL_phone.confirmCall r03 = new TL_phone.confirmCall();
        r03.g_a = this.g_a;
        r03.key_fingerprint = r3;
        TLRPC.TL_inputPhoneCall r14 = new TLRPC.TL_inputPhoneCall();
        r03.peer = r14;
        TL_phone.PhoneCall r32 = this.privateCall;
        r14.id = r32.id;
        r14.access_hash = r32.access_hash;
        TL_phone.TL_phoneCallProtocol r15 = new TL_phone.TL_phoneCallProtocol();
        r03.protocol = r15;
        r15.max_layer = Instance.getConnectionMaxLayer();
        TL_phone.TL_phoneCallProtocol r16 = r03.protocol;
        r16.min_layer = 65;
        r16.udp_reflector = true;
        r16.udp_p2p = true;
        Collections.addAll(r16.library_versions, NativeInstance.getAllVersions());
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, new VoIPService$$ExternalSyntheticLambda52(this));
        return;
    L14:
        if (r02.length >= 256) goto L19;
        r12 = new byte[256];
        System.arraycopy(r02, 0, r12, 256 - r02.length, r02.length);
        int r52 = 0;
    L17:
        if (r52 >= (256 - r02.length)) goto L12;
        r12[r52] = 0;
        r52 = r52 + 1;
        goto L17
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.w("stopping VoIP service, bad Ga and Gb");
    L7:
        callFailed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v4 */
    private void showIncomingNotification(String r17, TLObject r18, boolean r19, int r20) {
        Intent r4 = new Intent(this, LaunchActivity.class);
        r4.setAction("voip");
        Notification.Builder r0 = new Notification.Builder(this);
        if (r19 == false) goto L6;
        int r5 = R.string.VoipInVideoCallBranding;
    L7:
        Notification.Builder r7 = r0.setContentTitle(LocaleController.getString(r5)).setSmallIcon(R.drawable.call).setContentIntent(PendingIntent.getActivity(this, 0, r4, 33554432));
        if (Build.VERSION.SDK_INT < 26) goto L41;
        SharedPreferences r02 = MessagesController.getGlobalNotificationsSettings();
        int r12 = r02.getInt("calls_notification_channel", 0);
        NotificationManager r13 = (NotificationManager) getSystemService("notification");
        NotificationChannel r14 = r13.getNotificationChannel("incoming_calls2" + r12);
        if (r14 == null) goto L12;
        r13.deleteNotificationChannel(r14.getId());
    L12:
        NotificationChannel r142 = r13.getNotificationChannel("incoming_calls3" + r12);
        if (r142 == null) goto L15;
        r13.deleteNotificationChannel(r142.getId());
    L15:
        NotificationChannel r143 = r13.getNotificationChannel("incoming_calls4" + r12);
        if (r143 != null) goto L18;
    L27:
        boolean r03 = true;
    L28:
        if (r03 == false) goto L40;
        AudioAttributes r04 = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(2).setUsage(2).build();
        CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1.m();
        NotificationChannel r6 = CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m("incoming_calls4" + r12, LocaleController.getString(R.string.IncomingCallsSystemSetting), 4);
        r6.setSound(null, r04);     // Catch: Exception -> L32
    L34:
        r6.setDescription(LocaleController.getString(R.string.IncomingCallsSystemSettingDescription));
        r6.enableVibration(false);
        r6.enableLights(false);
        r6.setBypassDnd(true);
        r13.createNotificationChannel(r6);     // Catch: Exception -> L37
    L37:
        e = move-exception;
        FileLog.e(e);
        stopSelf();
        return;
    L32:
        e = move-exception;
        FileLog.e(e);
    L40:
        r7.setChannelId("incoming_calls4" + r12);
    L42:
        Intent r05 = new Intent(this, VoIPActionsReceiver.class);
        r05.setAction(getPackageName() + ".DECLINE_CALL");
        r05.putExtra("call_id", getCallID());
        String r8 = LocaleController.getString(R.string.VoipDeclineCall);
        int r9 = Build.VERSION.SDK_INT;
        if (r9 >= 31) goto L45;
        SpannableString r132 = new SpannableString(r8);
        r132.setSpan(new ForegroundColorSpan(-769226), 0, r132.length(), 0);
        r8 = r132;
    L45:
        PendingIntent r06 = PendingIntent.getBroadcast(this, 0, r05, 301989888);
        Intent r144 = new Intent(this, VoIPActionsReceiver.class);
        r144.setAction(getPackageName() + ".ANSWER_CALL");
        r144.putExtra("call_id", getCallID());
        String r52 = LocaleController.getString(R.string.VoipAnswerCall);
        if (r9 >= 31) goto L48;
        SpannableString r62 = new SpannableString(r52);
        int r15 = 0;
        r62.setSpan(new ForegroundColorSpan(-16733696), 0, r62.length(), 0);
        r52 = r62;
    L49:
        PendingIntent r63 = PendingIntent.getBroadcast(this, r15, r144, 301989888);
        r7.setPriority(2);
        r7.setShowWhen(r15);
        r7.setColor(-13851168);
        r7.setVibrate(new long[r15]);
        r7.setCategory("call");
        r7.setFullScreenIntent(PendingIntent.getActivity(this, r15, r4, 33554432), true);
        if ((r18 instanceof TLRPC.User) == false) goto L54;
        TLRPC.User r42 = (TLRPC.User) r18;
        if (TextUtils.isEmpty(r42.phone) == true) goto L54;
        r7.addPerson("tel:" + r42.phone);
    L54:
        if (r9 < 31) goto L60;
        Bitmap r2 = getRoundAvatarBitmap(this, this.currentAccount, r18);
        String r3 = ContactsController.formatName(r18);
        if (TextUtils.isEmpty(r3) == false) goto L58;
        r3 = "___";
    L58:
        r7.setStyle(Notification.CallStyle.forIncomingCall(VoIPGroupNotification$$ExternalSyntheticApiModelOutline0.m().setName(r3).setIcon(Icon.createWithAdaptiveBitmap(r2)).build(), r06, r63));
        Notification r07 = r7.build();
    L78:
        this.foregroundStarted = true;
        if (r9 < 33) goto L81;
        this.foregroundId = 202;
        this.foregroundNotification = r07;
        int r22 = getCurrentForegroundType();
        this.lastForegroundType = r22;
        startForeground(202, r07, r22);
    L82:
        startRingtoneAndVibration();
        return;
    L81:
        this.foregroundId = 202;
        this.foregroundNotification = r07;
        startForeground(202, r07);
        goto L82
    L60:
        r7.addAction(R.drawable.ic_call_end_white_24dp, r8, r06);
        r7.addAction(R.drawable.call, r52, r63);
        r7.setContentText(r17);
        String r53 = getPackageName();
        if (LocaleController.isRTL == false) goto L63;
        int r82 = R.layout.call_notification_rtl;
    L64:
        RemoteViews r43 = new RemoteViews(r53, r82);
        r43.setTextViewText(R.id.name, r17);
        r43.setViewVisibility(R.id.subtitle, 8);
        if (UserConfig.getActivatedAccountsCount() <= 1) goto L71;
        TLRPC.User r23 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        int r54 = R.id.title;
        if (r19 == false) goto L69;
        String r24 = LocaleController.formatString("VoipInVideoCallBrandingWithName", R.string.VoipInVideoCallBrandingWithName, new Object[]{ContactsController.formatName(r23.first_name, r23.last_name)});
    L70:
        r43.setTextViewText(r54, r24);
    L77:
        Bitmap r25 = getRoundAvatarBitmap(this, this.currentAccount, r18);
        r43.setTextViewText(R.id.answer_text, LocaleController.getString(R.string.VoipAnswerCall));
        r43.setTextViewText(R.id.decline_text, LocaleController.getString(R.string.VoipDeclineCall));
        r43.setImageViewBitmap(R.id.photo, r25);
        r43.setOnClickPendingIntent(R.id.answer_btn, r63);
        r43.setOnClickPendingIntent(R.id.decline_btn, r06);
        r7.setLargeIcon(r25);
        r07 = r7.getNotification();
        r07.bigContentView = r43;
        r07.headsUpContentView = r43;
        goto L78
    L69:
        r24 = LocaleController.formatString("VoipInCallBrandingWithName", R.string.VoipInCallBrandingWithName, new Object[]{ContactsController.formatName(r23.first_name, r23.last_name)});
        goto L70
    L71:
        int r26 = R.id.title;
        if (r19 == false) goto L75;
        int r55 = R.string.VoipInVideoCallBranding;
    L76:
        r43.setTextViewText(r26, LocaleController.getString(r55));
        goto L77
    L75:
        r55 = R.string.VoipInCallBranding;
        goto L76
    L63:
        r82 = R.layout.call_notification;
        goto L64
    L48:
        r15 = 0;
        goto L49
    L18:
        if (r143.getImportance() < 4) goto L24;
        if (r143.getSound() != null) goto L24;
        r03 = false;
    L24:
        if (BuildVars.LOGS_ENABLED == false) goto L26;
        FileLog.d("User messed up the notification channel; deleting it and creating a proper one");
    L26:
        r13.deleteNotificationChannel("incoming_calls4" + r12);
        r12 = r12 + 1;
        r02.edit().putInt("calls_notification_channel", r12).apply();
        goto L27
    L41:
        r7.setSound(null);
        goto L42
    L6:
        r5 = R.string.VoipInCallBranding;
        goto L7
    }

    private void showNotification() {
        if (this.user == null) goto L6;
        showNotification(getTitle(), getRoundAvatarBitmap(this, this.currentAccount, this.user));
        return;
    L6:
        showNotification(getTitle(), getRoundAvatarBitmap(this, this.currentAccount, this.chat));
    }

    private void showNotification(String r8, Bitmap r9) {
        Intent r0 = new Intent(this, LaunchActivity.class);
        if (this.groupCall == null) goto L5;
        String r1 = "voip_chat";
    L6:
        Intent r02 = r0.setAction(r1);
        if (this.groupCall == null) goto L9;
        r02.putExtra("currentAccount", this.currentAccount);
    L9:
        Notification.Builder r03 = new Notification.Builder(this).setContentText(r8).setContentIntent(PendingIntent.getActivity(this, 50, r02, 33554432));
        if (isConference() == false) goto L17;
        r03.setContentTitle(LocaleController.getString(R.string.VoipConference));
        if (isMicMute() == false) goto L14;
        int r12 = R.drawable.voicechat_muted;
    L15:
        r03.setSmallIcon(r12);
    L29:
        Intent r13 = new Intent(this, VoIPActionsReceiver.class);
        r13.setAction(getPackageName() + ".END_CALL");
        if (isConference() == false) goto L33;
        r03.addAction(R.drawable.ic_call_end_white_24dp, LocaleController.getString(R.string.VoipConferenceLeave), PendingIntent.getBroadcast(this, 0, r13, 167772160));
    L41:
        r03.setPriority(2);
        r03.setShowWhen(false);
        int r14 = Build.VERSION.SDK_INT;
        if (r14 < 26) goto L44;
        r03.setColor(-14143951);
        r03.setColorized(true);
    L45:
        if (r14 < 26) goto L48;
        NotificationsController.checkOtherNotificationsChannel();
        r03.setChannelId(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
    L48:
        if (isConference() == true) goto L71;
        if (r9 == null) goto L71;
        r03.setLargeIcon(r9);
    L71:
    L57:
        e = move-exception;
        if (r9 != null) goto L66;
        return;
    L66:
        if ((e instanceof IllegalArgumentException) == false) goto L74;
        showNotification(r8, null);
        return;
    L74:
        return;
    L52:
        if (this.foregroundStarted == true) goto L69;
    L59:
        this.foregroundStarted = true;     // Catch: Exception -> L57
        if (Build.VERSION.SDK_INT < 33) goto L62;
        this.foregroundId = 201;     // Catch: Exception -> L57
        Notification r04 = r03.getNotification();     // Catch: Exception -> L57
        this.foregroundNotification = r04;     // Catch: Exception -> L57
        int r15 = getCurrentForegroundType();     // Catch: Exception -> L57
        this.lastForegroundType = r15;     // Catch: Exception -> L57
        startForeground(201, r04, r15);     // Catch: Exception -> L57
        return;
    L62:
        this.foregroundId = 201;     // Catch: Exception -> L57
        Notification r05 = r03.getNotification();     // Catch: Exception -> L57
        this.foregroundNotification = r05;     // Catch: Exception -> L57
        startForeground(201, r05);     // Catch: Exception -> L57
        return;
    L69:
        stopForeground(true);     // Catch: Exception -> L55
    L55:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L57
        goto L59
    L44:
        r03.setColor(-13851168);
        goto L45
    L33:
        if (this.groupCall == null) goto L40;
        int r3 = R.drawable.ic_call_end_white_24dp;
        if (ChatObject.isChannelOrGiga(this.chat) == false) goto L38;
        int r6 = R.string.VoipChannelLeaveAlertTitle;
    L39:
        r03.addAction(r3, LocaleController.getString(r6), PendingIntent.getBroadcast(this, 0, r13, 167772160));
        goto L41
    L38:
        r6 = R.string.VoipGroupLeaveAlertTitle;
        goto L39
    L40:
        r03.addAction(R.drawable.ic_call_end_white_24dp, LocaleController.getString(R.string.VoipEndCall), PendingIntent.getBroadcast(this, 0, r13, 167772160));
        goto L41
    L14:
        r12 = R.drawable.voicechat_active;
        goto L15
    L17:
        if (this.groupCall != null) goto L19;
        r03.setContentTitle(LocaleController.getString(R.string.VoipOutgoingCall));
        r03.setSmallIcon(R.drawable.call);
        r03.setOngoing(true);
        goto L29
    L19:
        if (ChatObject.isChannelOrGiga(this.chat) == false) goto L22;
        int r16 = R.string.VoipLiveStream;
    L23:
        r03.setContentTitle(LocaleController.getString(r16));
        if (isMicMute() == false) goto L26;
        int r17 = R.drawable.voicechat_muted;
    L27:
        r03.setSmallIcon(r17);
        goto L29
    L26:
        r17 = R.drawable.voicechat_active;
        goto L27
    L22:
        r16 = R.string.VoipVoiceChat;
        goto L23
    L5:
        r1 = "voip";
        goto L6
    }

    private void startConferenceGroupCall(boolean r10, int r11, String r12, boolean r13) {
        if (sharedInstance == this) goto L6;
        return;
    L6:
        if (this.groupCallPeer != null) goto L8;
        this.groupCallPeer = MessagesController.getInputPeer(UserConfig.getInstance(this.currentAccount).getCurrentUser());
    L8:
        int r3 = 0;
        if (r10 == false) goto L13;
        AccountInstance r102 = AccountInstance.getInstance(this.currentAccount);
        this.conference = new AnonymousClass6(this, this.currentAccount, r102.getUserConfig().getClientUserId());
        this.chat = null;
        ChatObject.Call r112 = new ChatObject.Call();
        this.groupCall = r112;
        r112.call = new TLRPC.TL_groupCall();
        ChatObject.Call r113 = this.groupCall;
        TLRPC.GroupCall r122 = r113.call;
        r122.participants_count = 0;
        r122.version = 1;
        r122.can_start_video = true;
        r122.can_change_join_muted = true;
        r122.rtmp_stream = this.isRtmpStream;
        r113.chatId = 0;
        r113.currentAccount = AccountInstance.getInstance(this.currentAccount);
        this.groupCall.setSelfPeer(this.groupCallPeer);
        this.groupCall.createNoVideoParticipant();
        dispatchStateChanged(6);
        TL_phone.createConferenceCall r114 = new TL_phone.createConferenceCall();
        r114.random_id = Utilities.random.nextInt();
        this.groupCallBottomSheetLatch = new CountDownLatch(1);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r114, new VoIPService$$ExternalSyntheticLambda53(this, r102), 65536);
        return;
    L13:
        if (this.conference != null) goto L16;
        int r6 = this.currentAccount;
        AnonymousClass7 r5 = new AnonymousClass7(this, r6, UserConfig.getInstance(r6).getClientUserId());
        this.conference = r5;
        r5.inputGroupCall = this.joinConference;
    L16:
        if (this.groupCall != null) goto L24;
        ChatObject.Call r52 = new ChatObject.Call();
        this.groupCall = r52;
        r52.isConference = true;
        r52.call = new TLRPC.TL_groupCall();
        TLRPC.GroupCall r53 = this.groupCall.call;
        r53.participants_count = 0;
        r53.version = 1;
        r53.can_start_video = true;
        r53.can_change_join_muted = true;
        r53.rtmp_stream = this.isRtmpStream;
        if ((this.joinConference instanceof TLRPC.TL_inputGroupCallSlug) == false) goto L20;
        r53.invite_link = "https://t.me/call/" + this.joinConference.slug;
    L20:
        ChatObject.Call r54 = this.groupCall;
        r54.chatId = 0;
        r54.currentAccount = AccountInstance.getInstance(this.currentAccount);
        this.groupCall.setSelfPeer(this.groupCallPeer);
        this.groupCall.createNoVideoParticipant();
        TLRPC.GroupCall r55 = this.joinConferenceCall;
        if (r55 == null) goto L24;
        this.conference.groupCall = r55;
        this.groupCall.setCall(AccountInstance.getInstance(this.currentAccount), 0, this.joinConferenceCall);
        this.joinConferenceCall = null;
    L24:
        if (this.conference.groupCall != null) goto L27;
        TL_phone.getGroupCall r103 = new TL_phone.getGroupCall();
        r103.call = this.joinConference;
        r103.limit = 10;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r103, new VoIPService$$ExternalSyntheticLambda54(this, r11, r12));
        return;
    L27:
        if (r12 != null) goto L44;
        configureDeviceForCall();
        showNotification();
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda55());
        if (this.convertingVoip != null) goto L31;
    L33:
        createGroupInstance(0, false, true);
        long[] r104 = this.inviteUsers;
        if (r104 == null) goto L39;
        int r115 = r104.length;
    L36:
        if (r3 >= r115) goto L39;
        this.groupCall.addInvitedUser(r104[r3]);
        r3 = r3 + 1;
    L39:
        if (VoIPFragment.getInstance() == null) goto L41;
        VoIPFragment.getInstance().finish();
    L41:
        GroupCallActivity.create(LaunchActivity.instance, AccountInstance.getInstance(UserConfig.selectedAccount), null, null, false, null);
        return;
    L31:
        if (this.isPrivateScreencast == false) goto L33;
        long[] r105 = this.captureDevice;
        r105[1] = r105[0];
        r105[0] = 0;
        boolean[] r106 = this.destroyCaptureDevice;
        r106[1] = true;
        r106[0] = true;
        setVideoState(true, this.videoState[0]);
        setVideoState(false, 0);
        this.videoState[0] = 0;
        goto L33
    L44:
        if (getSharedInstance() != null) goto L46;
        return;
    L46:
        if (this.groupCall == null) goto L48;
    L50:
        dispatchStateChanged(1);
        if (BuildVars.LOGS_ENABLED == false) goto L53;
        FileLog.d("initital source = " + r11);
    L53:
        VoIPService$$ExternalSyntheticLambda56 r0 = new VoIPService$$ExternalSyntheticLambda56(this, r12, r11, r10);
        if (r13 == false) goto L56;
        this.conference.reset();
    L56:
        this.conference.requestLastBlock(new VoIPService$$ExternalSyntheticLambda57(this, r0));
        return;
    L48:
        if (this.conference != null) goto L50;
    }

    private void startConnectingSound() {
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda88(this));
    }

    private void startGroupCall(int r6, String r7, boolean r8) {
        if (sharedInstance != this) goto L47;
        boolean r2 = true;
        if (this.createGroupCall == false) goto L15;
        ChatObject.Call r62 = new ChatObject.Call();
        this.groupCall = r62;
        r62.call = new TLRPC.TL_groupCall();
        ChatObject.Call r63 = this.groupCall;
        TLRPC.GroupCall r72 = r63.call;
        r72.participants_count = 0;
        r72.version = 1;
        r72.can_start_video = true;
        r72.can_change_join_muted = true;
        r72.rtmp_stream = this.isRtmpStream;
        r63.chatId = this.chat.id;
        r63.currentAccount = AccountInstance.getInstance(this.currentAccount);
        this.groupCall.setSelfPeer(this.groupCallPeer);
        this.groupCall.createNoVideoParticipant();
        dispatchStateChanged(6);
        TL_phone.createGroupCall r64 = new TL_phone.createGroupCall();
        r64.peer = MessagesController.getInputPeer(this.chat);
        r64.random_id = Utilities.random.nextInt();
        int r73 = this.scheduleDate;
        if (r73 == 0) goto L11;
        r64.schedule_date = r73;
        r64.flags |= 2;
    L11:
        if (this.isRtmpStream == false) goto L13;
        r64.flags |= 4;
    L13:
        this.groupCallBottomSheetLatch = new CountDownLatch(1);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r64, new VoIPService$$ExternalSyntheticLambda62(this), 65536);
        this.createGroupCall = false;
        return;
    L15:
        if (r7 != null) goto L26;
        if (this.groupCall == null) goto L19;
    L23:
        configureDeviceForCall();
        showNotification();
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda63());
        createGroupInstance(0, false, true);
        return;
    L19:
        if (this.chat == null) goto L23;
        ChatObject.Call r65 = MessagesController.getInstance(this.currentAccount).getGroupCall(this.chat.id, false);
        this.groupCall = r65;
        if (r65 == null) goto L23;
        r65.setSelfPeer(this.groupCallPeer);
        goto L23
    L26:
        if (getSharedInstance() != null) goto L28;
        return;
    L28:
        if (this.groupCall == null) goto L48;
        dispatchStateChanged(1);
        if (BuildVars.LOGS_ENABLED == false) goto L33;
        FileLog.d("initital source = " + r6);
    L33:
        TL_phone.joinGroupCall r0 = new TL_phone.joinGroupCall();
        r0.muted = this.micMute;
        if (this.videoState[0] != 2) goto L37;
        r2 = false;
    L37:
        r0.video_stopped = r2;
        r0.call = this.groupCall.getInputGroupCall();
        TLRPC.TL_dataJSON r22 = new TLRPC.TL_dataJSON();
        r0.params = r22;
        r22.data = r7;
        if (TextUtils.isEmpty(this.joinHash) == true) goto L40;
        r0.invite_hash = this.joinHash;
        r0.flags |= 2;
    L40:
        TLRPC.InputPeer r74 = this.groupCallPeer;
        if (r74 == null) goto L43;
        r0.join_as = r74;
    L44:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new VoIPService$$ExternalSyntheticLambda64(this, r6, r8));
        return;
    L43:
        TLRPC.TL_inputPeerUser r75 = new TLRPC.TL_inputPeerUser();
        r0.join_as = r75;
        r75.user_id = AccountInstance.getInstance(this.currentAccount).getUserConfig().getClientUserId();
        goto L44
    L48:
        return;
    }

    private void startGroupCheckShortpoll() {
        if (this.shortPollRunnable == null) goto L5;
        return;
    L5:
        if (sharedInstance == null) goto L20;
        ChatObject.Call r0 = this.groupCall;
        if (r0 == null) goto L21;
        int[] r1 = this.mySource;
        if (r1[0] == 0) goto L11;
    L17:
        VoIPService$$ExternalSyntheticLambda61 r02 = new VoIPService$$ExternalSyntheticLambda61(this);
        this.shortPollRunnable = r02;
        AndroidUtilities.runOnUIThread(r02, 4000);
        return;
    L11:
        if (r1[1] != 0) goto L17;
        TLRPC.GroupCall r03 = r0.call;
        if (r03 != null) goto L15;
        return;
    L15:
        if (r03.rtmp_stream == true) goto L17;
        return;
    L21:
        return;
    }

    private void startOutgoingCall() {
        if (USE_CONNECTION_SERVICE == false) goto L7;
        CallConnection r0 = this.systemCallConnection;
        if (r0 == null) goto L7;
        r0.setDialing();
    L7:
        configureDeviceForCall();
        showNotification();
        startConnectingSound();
        dispatchStateChanged(14);
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda58());
        SecureRandom r2 = Utilities.random;
        r2.nextBytes(new byte[256]);
        TLRPC.TL_messages_getDhConfig r1 = new TLRPC.TL_messages_getDhConfig();
        r1.random_length = 256;
        MessagesStorage r02 = MessagesStorage.getInstance(this.currentAccount);
        r1.version = r02.getLastSecretVersion();
        this.callReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new VoIPService$$ExternalSyntheticLambda59(this, r02), 65536);
    }

    private void startRatingActivity() {
        PendingIntent.getActivity(this, 0, new Intent(this, VoIPFeedbackActivity.class).putExtra("call_id", this.privateCall.id).putExtra("call_access_hash", this.privateCall.access_hash).putExtra("call_video", this.privateCall.video).putExtra("account", this.currentAccount).addFlags(805306368), 33554432).send();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.e("Error starting incall activity", e);
        return;
    }

    private void startRinging() {
        if (this.currentState != 15) goto L6;
        return;
    L6:
        if (USE_CONNECTION_SERVICE == false) goto L11;
        CallConnection r0 = this.systemCallConnection;
        if (r0 == null) goto L11;
        r0.setRinging();
    L11:
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.d("starting ringing for call " + this.privateCall.id);
    L13:
        dispatchStateChanged(15);
        boolean r02 = this.notificationsDisabled;
        TLRPC.User r1 = this.user;
        if (r02 == true) goto L19;
        showIncomingNotification(ContactsController.formatName(r1.first_name, r1.last_name), this.user, this.privateCall.video, 0);
        if (BuildVars.LOGS_ENABLED == false) goto L31;
        FileLog.d("Showing incoming call notification");
        return;
    L31:
        return;
    L19:
        startRingtoneAndVibration(r1.id);
        if (BuildVars.LOGS_ENABLED == false) goto L29;
        FileLog.d("Starting incall activity for incoming call");
    L29:
        PendingIntent.getActivity(this, 12345, new Intent(this, LaunchActivity.class).setAction("voip"), 33554432).send();     // Catch: Exception -> L24
        return;
    L24:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L32;
        FileLog.e("Error starting incall activity", e);
        return;
    }

    private void startRingtoneAndVibration(long r14) {
        SharedPreferences r5 = MessagesController.getNotificationsSettings(this.currentAccount);
        AudioManager r6 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r6.getRingerMode() != 0) goto L5;
        return;
    L5:
        if (this.ringtonePlayer != null) goto L76;
        Object r7 = sync;
        monitor-enter(r7);
    L13:
        th = move-exception;
        throw th;
    L10:
        if (this.ringtonePlayer == null) goto L15;
        monitor-exit(r7);     // Catch: Throwable -> L13
        return;
    L15:
        MediaPlayer r8 = new MediaPlayer();     // Catch: Throwable -> L13
        this.ringtonePlayer = r8;     // Catch: Throwable -> L13
        r8.setOnPreparedListener(new VoIPService$$ExternalSyntheticLambda42(this));     // Catch: Throwable -> L13
        this.ringtonePlayer.setLooping(true);     // Catch: Throwable -> L13
        boolean r82 = this.isHeadsetPlugged;     // Catch: Throwable -> L13
        MediaPlayer r10 = this.ringtonePlayer;
        if (r82 == false) goto L19;
        r10.setAudioStreamType(0);     // Catch: Throwable -> L13
    L73:
    L30:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13
        MediaPlayer r2 = this.ringtonePlayer;     // Catch: Throwable -> L13
        if (r2 == null) goto L48;
        r2.release();     // Catch: Throwable -> L13
        this.ringtonePlayer = null;     // Catch: Throwable -> L13
    L48:
        if (r5.getBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r14, false) == false) goto L50;
        int r142 = r5.getInt("calls_vibrate_" + r14, 0);     // Catch: Throwable -> L13
    L52:
        if (r142 == 2) goto L58;
        if (r142 == 4) goto L58;
        if (r6.getRingerMode() != 1) goto L57;
    L61:
        Vibrator r15 = (Vibrator) getSystemService("vibrator");     // Catch: Throwable -> L13
        this.vibrator = r15;     // Catch: Throwable -> L13
        if (r142 != 1) goto L64;
        long r0 = 350;
    L67:
        r15.vibrate(new long[]{0, r0, 500}, 0);     // Catch: Throwable -> L13
    L68:
        monitor-exit(r7);     // Catch: Throwable -> L13
        return;
    L64:
        if (r142 != 3) goto L66;
        r0 = 1400;
        goto L67
    L66:
        r0 = 700;
        goto L67
    L57:
        if (r6.getRingerMode() == 2) goto L61;
    L58:
        if (r142 != 4) goto L68;
        if (r6.getRingerMode() != 1) goto L68;
    L50:
        r142 = r5.getInt("vibrate_calls", 0);     // Catch: Throwable -> L13
        goto L52
    L28:
        if (r5.getBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r14, false) == false) goto L32;
        String r3 = r5.getString("ringtone_path_" + r14, null);     // Catch: Throwable -> L13 Exception -> L30
    L33:
        if (r3 != null) goto L36;
        Uri r32 = RingtoneManager.getDefaultUri(1);     // Catch: Throwable -> L13 Exception -> L30
    L35:
        boolean r4 = true;
    L42:
        FileLog.d("start ringtone with " + r4 + " " + r32);     // Catch: Throwable -> L13 Exception -> L30
        this.ringtonePlayer.setDataSource(this, r32);     // Catch: Throwable -> L13 Exception -> L30
        this.ringtonePlayer.prepareAsync();     // Catch: Throwable -> L13 Exception -> L30
    L36:
        Uri r42 = Settings.System.DEFAULT_RINGTONE_URI;     // Catch: Throwable -> L13 Exception -> L30
        if (r42 != null) goto L39;
    L41:
        r32 = Uri.parse(r3);     // Catch: Throwable -> L13 Exception -> L30
        r4 = false;
        goto L42
    L39:
        if (r3.equalsIgnoreCase(r42.getPath()) == false) goto L41;
        r32 = RingtoneManager.getDefaultUri(1);     // Catch: Throwable -> L13 Exception -> L30
        goto L35
    L32:
        r3 = r5.getString("CallsRingtonePath", null);     // Catch: Throwable -> L13 Exception -> L30
        goto L33
    L19:
        r10.setAudioStreamType(2);     // Catch: Throwable -> L13
        if (USE_CONNECTION_SERVICE == true) goto L73;
        if (r6.requestAudioFocus(this, 2, 2) != 1) goto L24;
        boolean r83 = true;
    L25:
        this.hasAudioFocus = r83;     // Catch: Throwable -> L13
        goto L73
    L24:
        r83 = false;
        goto L25
    }

    private void startScreenCapture(int r4, String r5) {
        if (getSharedInstance() != null) goto L5;
        return;
    L5:
        if (this.groupCall == null) goto L10;
        this.mySource[1] = 0;
        TL_phone.joinGroupCallPresentation r0 = new TL_phone.joinGroupCallPresentation();
        r0.call = this.groupCall.getInputGroupCall();
        TLRPC.TL_dataJSON r1 = new TLRPC.TL_dataJSON();
        r0.params = r1;
        r1.data = r5;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new VoIPService$$ExternalSyntheticLambda132(this, r4));
        return;
    }

    private void updateBluetoothHeadsetState(boolean r5) {
        if (r5 != this.isBtHeadsetConnected) goto L6;
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d("updateBluetoothHeadsetState: " + r5);
    L8:
        this.isBtHeadsetConnected = r5;
        AudioManager r0 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        int r1 = 0;
        if (r5 == true) goto L11;
    L28:
        this.bluetoothScoActive = false;
        this.bluetoothScoConnecting = false;
        r0.setBluetoothScoOn(false);
    L29:
        ArrayList<StateListener> r4 = this.stateListeners;
        int r52 = r4.size();
    L30:
        if (r1 >= r52) goto L34;
        StateListener r02 = r4.get(r1);
        r1 = r1 + 1;
        r02.onAudioSettingsChanged();
        goto L30
    L34:
        return;
    L11:
        if (isRinging() == true) goto L28;
        if (this.currentState == 0) goto L28;
        if (this.bluetoothScoActive == false) goto L23;
        if (BuildVars.LOGS_ENABLED == false) goto L20;
        FileLog.d("SCO already active, setting audio routing");
    L20:
        if (hasRtmpStream() == true) goto L29;
        r0.setSpeakerphoneOn(false);
        r0.setBluetoothScoOn(true);
        goto L29
    L23:
        if (BuildVars.LOGS_ENABLED == false) goto L26;
        FileLog.d("startBluetoothSco");
    L26:
        if (hasRtmpStream() == true) goto L29;
        this.needSwitchToBluetoothAfterScoActivates = true;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda82(r0), 500);
        goto L29
    }

    private void updateConnectionState(int r5, int r6, boolean r7) {
        if (r5 == 0) goto L5;
        return;
    L5:
        if (r6 != 1) goto L7;
    L10:
        int r1 = 3;
    L11:
        dispatchStateChanged(r1);
        if (this.switchingStream == false) goto L17;
        if (r6 == 0) goto L16;
        if (r6 != 1) goto L17;
        if (r7 == false) goto L17;
    L16:
        VoIPService$$ExternalSyntheticLambda95 r12 = new VoIPService$$ExternalSyntheticLambda95(this, r5);
        this.switchingStreamTimeoutRunnable = r12;
        AndroidUtilities.runOnUIThread(r12, 3000);
    L17:
        if (r6 != 0) goto L28;
        startGroupCheckShortpoll();
        if (this.playedConnectedSound == true) goto L21;
        return;
    L21:
        if (this.spPlayId == 0) goto L23;
        return;
    L23:
        if (this.switchingStream == false) goto L25;
        return;
    L25:
        if (this.switchingAccount == true) goto L55;
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda96(this));
        return;
    L55:
        return;
    L28:
        cancelGroupCheckShortPoll();
        if (r7 == true) goto L31;
        this.switchingStream = false;
        this.switchingAccount = false;
    L31:
        Runnable r62 = this.switchingStreamTimeoutRunnable;
        if (r62 == null) goto L35;
        AndroidUtilities.cancelRunOnUIThread(r62);
        this.switchingStreamTimeoutRunnable = null;
    L35:
        if (this.playedConnectedSound == false) goto L39;
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda97(this));
        Runnable r63 = this.connectingSoundRunnable;
        if (r63 == null) goto L41;
        AndroidUtilities.cancelRunOnUIThread(r63);
        this.connectingSoundRunnable = null;
    L41:
        if (this.wasConnected == true) goto L56;
        this.wasConnected = true;
        if (this.reconnectScreenCapture == false) goto L45;
        createGroupInstance(1, false, true);
        this.reconnectScreenCapture = false;
    L45:
        NativeInstance r64 = this.tgVoip[0];
        if (r64 != null) goto L48;
    L50:
        setParticipantsVolume();
        return;
    L48:
        if (this.micMute == true) goto L50;
        r64.setMuteMicrophone(false);
        goto L50
    L56:
        return;
    L39:
        playConnectedSound();
        goto L41
    L7:
        if (this.switchingStream == true) goto L10;
        r1 = 5;
        goto L11
    }

    private void updateNetworkType() {
        NativeInstance r0 = this.tgVoip[0];
        if (r0 != null) goto L5;
        this.lastNetInfo = getActiveNetworkInfo();
        return;
    L5:
        if (r0.isGroup() == false) goto L7;
        return;
    L7:
        this.tgVoip[0].setNetworkType(getNetworkType());
    }

    private void updateServerConfig() {
        SharedPreferences r0 = MessagesController.getMainSettings(this.currentAccount);
        Instance.setGlobalServerConfig(r0.getString("voip_server_config", "{}"));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_phone.getCallConfig(), new VoIPService$$ExternalSyntheticLambda84(r0));
    }

    private void updateTrafficStats(NativeInstance r13, Instance.TrafficStats r14) {
        if (r13 == null) goto L50;
        if (r14 != null) goto L6;
        r14 = r13.getTrafficStats();
    L6:
        if (r14 == null) goto L51;
        long r0 = r14.bytesSentWifi;
        Instance.TrafficStats r132 = this.prevTrafficStats;
        if (r132 == null) goto L11;
        long r4 = r132.bytesSentWifi;
    L12:
        long r02 = r0 - r4;
        long r42 = r14.bytesReceivedWifi;
        if (r132 == null) goto L15;
        long r6 = r132.bytesReceivedWifi;
    L16:
        long r43 = r42 - r6;
        long r62 = r14.bytesSentMobile;
        if (r132 == null) goto L19;
        long r8 = r132.bytesSentMobile;
    L20:
        long r63 = r62 - r8;
        long r82 = r14.bytesReceivedMobile;
        if (r132 == null) goto L23;
        long r10 = r132.bytesReceivedMobile;
    L24:
        long r83 = r82 - r10;
        this.prevTrafficStats = r14;
        if (r02 <= 0) goto L28;
        StatsController.getInstance(this.currentAccount).incrementSentBytesCount(1, 0, r02);
    L28:
        if (r43 <= 0) goto L30;
        StatsController.getInstance(this.currentAccount).incrementReceivedBytesCount(1, 0, r43);
    L30:
        int r142 = 2;
        if (r63 <= 0) goto L40;
        StatsController r133 = StatsController.getInstance(this.currentAccount);
        NetworkInfo r03 = this.lastNetInfo;
        if (r03 != null) goto L35;
    L37:
        int r04 = 0;
    L38:
        r133.incrementSentBytesCount(r04, 0, r63);
        goto L40
    L35:
        if (r03.isRoaming() == false) goto L37;
        r04 = 2;
    L40:
        if (r83 <= 0) goto L49;
        StatsController r134 = StatsController.getInstance(this.currentAccount);
        NetworkInfo r12 = this.lastNetInfo;
        if (r12 != null) goto L44;
    L46:
        r142 = 0;
    L47:
        r134.incrementReceivedBytesCount(r142, 0, r83);
        return;
    L44:
        if (r12.isRoaming() == false) goto L46;
    L49:
        return;
    L23:
        r10 = 0;
        goto L24
    L19:
        r8 = 0;
        goto L20
    L15:
        r6 = 0;
        goto L16
    L11:
        r4 = 0;
        goto L12
    L51:
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public void acceptIncomingCall() {
        updateCurrentForegroundType();
        MessagesController.getInstance(this.currentAccount).ignoreSetOnline = false;
        stopRinging();
        showNotification();
        configureDeviceForCall();
        startConnectingSound();
        dispatchStateChanged(12);
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda9());
        MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
        TLRPC.TL_messages_getDhConfig r1 = new TLRPC.TL_messages_getDhConfig();
        r1.random_length = 256;
        r1.version = r0.getLastSecretVersion();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new VoIPService$$ExternalSyntheticLambda10(this, r0));
    }

    public ProxyVideoSink addRemoteSink(TLRPC.GroupCallParticipant r11, boolean r12, VideoSink r13, VideoSink r14) {
        if (this.tgVoip[0] != null) goto L5;
        return null;
    L5:
        if (r12 == false) goto L8;
        String r0 = r11.presentationEndpoint;
    L7:
        String r5 = r0;
        if (r5 != null) goto L11;
        return null;
    L11:
        ProxyVideoSink r02 = this.remoteSinks.get(r5);
        if (r02 != null) goto L14;
    L16:
        if (r02 != null) goto L18;
        r02 = this.proxyVideoSinkLruCache.remove(r5);
    L18:
        if (r02 != null) goto L20;
        r02 = new ProxyVideoSink();
    L20:
        ProxyVideoSink r7 = r02;
        if (r13 == null) goto L23;
        r7.setTarget(r13);
    L23:
        if (r14 == null) goto L25;
        r7.setBackground(r14);
    L25:
        this.remoteSinks.put(r5, r7);
        NativeInstance r3 = this.tgVoip[0];
        if (r12 == false) goto L28;
        TLRPC.TL_groupCallParticipantVideo r122 = r11.presentation;
    L29:
        ProxyVideoSink.m5375$$Nest$fputnativeInstance(r7, r3.addIncomingVideoOutput(1, r5, createSsrcGroups(r122), r7, DialogObject.getPeerDialogId(r11.peer)));
        return r7;
    L28:
        r122 = r11.video;
        goto L29
    L14:
        if (ProxyVideoSink.m5374$$Nest$fgettarget(r02) != r13) goto L16;
        return r02;
    L8:
        r0 = r11.videoEndpoint;
        goto L7
    }

    public void callFailedFromConnectionService() {
        if (this.isOutgoing == false) goto L6;
        callFailed(Instance.ERROR_CONNECTION_SERVICE);
        return;
    L6:
        hangUp();
    }

    public void checkVideoFrame(TLRPC.GroupCallParticipant r4, boolean r5) {
        if (r5 == false) goto L4;
        String r0 = r4.presentationEndpoint;
    L5:
        if (r0 == null) goto L41;
        if (r5 == true) goto L9;
    L10:
        if (r5 == true) goto L15;
        if (r4.hasCameraFrame == 0) goto L15;
        return;
    L15:
        if (this.proxyVideoSinkLruCache.get(r0) != null) goto L35;
        if (this.remoteSinks.get(r0) == null) goto L22;
        if (this.waitingFrameParticipant.get(r0) == null) goto L35;
    L22:
        if (this.waitingFrameParticipant.containsKey(r0) == false) goto L29;
        this.waitingFrameParticipant.put(r0, r4);
        if (r5 == false) goto L27;
        r4.hasPresentationFrame = 1;
        return;
    L27:
        r4.hasCameraFrame = 1;
        return;
    L29:
        if (r5 == false) goto L31;
        r4.hasPresentationFrame = 1;
    L32:
        this.waitingFrameParticipant.put(r0, r4);
        addRemoteSink(r4, r5, new AnonymousClass5(this, r0, r5), null);
        return;
    L31:
        r4.hasCameraFrame = 1;
    L35:
        if (r5 == false) goto L38;
        r4.hasPresentationFrame = 2;
        return;
    L38:
        r4.hasCameraFrame = 2;
        return;
    L9:
        if (r4.hasPresentationFrame == 0) goto L10;
        return;
    L41:
        return;
    L4:
        r0 = r4.videoEndpoint;
        goto L5
    }

    public void clearCamera() {
        NativeInstance r0 = this.tgVoip[0];
        if (r0 == null) goto L5;
        r0.clearVideoCapturer();
    L5:
        long r2 = this.captureDevice[0];
        if (r2 == 0) goto L9;
        NativeInstance.destroyVideoCapturer(r2);
        this.captureDevice[0] = 0;
        return;
    }

    public void clearRemoteSinks() {
        this.proxyVideoSinkLruCache.evictAll();
    }

    public void convertToConferenceCall(boolean r3, HashSet<Long> r4) {
        TLRPC.User r0 = this.user;
        if (r0 == null) goto L5;
        long r02 = r0.id;
    L6:
        this.convertingFromCallWithUserId = r02;
        this.inviteUsersToConferenceVideo = r3;
        this.inviteUsersToConference = r4;
        stopRinging();
        NativeInstance[] r32 = this.tgVoip;
        NativeInstance r03 = r32[0];
        if (r03 == null) goto L9;
        this.prevTrafficStats = null;
        this.convertingVoip = r03;
        r32[0] = null;
    L9:
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda26(this));
        return;
    L5:
        r02 = 0;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    public void createCaptureDevice(boolean r10) {
        if (r10 == false) goto L6;
        this.gotMediaProjection = true;
        updateCurrentForegroundType();
    L6:
        if (r10 == false) goto L8;
        ?? r2 = 2;
    L10:
        if (this.groupCall == null) goto L12;
    L21:
        if (r10 == true) goto L23;
        long r6 = this.captureDevice[r10 ? 1 : 0];
        if (r6 == 0) goto L36;
    L37:
        NativeInstance r0 = this.tgVoip[r10 ? 1 : 0];
        if (r0 == null) goto L43;
        if (r6 == 0) goto L43;
        r0.activateVideoCapturer(r6);
    L43:
        if (this.captureDevice[r10 ? 1 : 0] != 0) goto L47;
    L45:
        this.captureDevice[r10 ? 1 : 0] = NativeInstance.createVideoCapturer(this.localSink[r10 ? 1 : 0], r2);
        return;
    L47:
        return;
    L36:
        if (this.tgVoip[r10 ? 1 : 0] != null) goto L45;
    L23:
        if (this.groupCall == null) goto L29;
        long[] r3 = this.captureDevice;
        if (r3[r10 ? 1 : 0] != 0) goto L48;
        r3[r10 ? 1 : 0] = NativeInstance.createVideoCapturer(this.localSink[r10 ? 1 : 0], r2);
        createGroupInstance(1, false, true);
        setVideoState(true, 2);
        AccountInstance.getInstance(this.currentAccount).getNotificationCenter().postNotificationName(NotificationCenter.groupCallScreencastStateChanged, new Object[0]);
        return;
    L48:
        return;
    L29:
        requestVideoCall(true);
        setVideoState(true, 2);
        if (VoIPFragment.getInstance() == null) goto L44;
        VoIPFragment.getInstance().onScreenCastStart();
        return;
    L44:
        return;
    L12:
        if (this.isPrivateScreencast == true) goto L15;
        if (r10 == false) goto L15;
        setVideoState(false, 0);
    L15:
        this.isPrivateScreencast = r10;
        NativeInstance r32 = this.tgVoip[0];
        if (r32 == null) goto L18;
        r32.clearVideoCapturer();
    L18:
        long r7 = this.captureDevice[0];
        if (r7 == 0) goto L21;
        NativeInstance.destroyVideoCapturer(r7);
        this.captureDevice[0] = 0;
        goto L21
    L8:
        r2 = this.isFrontFaceCamera;
        goto L10
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public void declineIncomingCall() {
        declineIncomingCall(1, null);
    }

    public void declineIncomingCall(int r7, Runnable r8) {
        if (this.groupCall == null) goto L5;
        stopScreenCapture();
    L5:
        stopRinging();
        this.callDiscardReason = r7;
        int r0 = this.currentState;
        if (r0 != 14) goto L13;
        Runnable r72 = this.delayedStartOutgoingCall;
        if (r72 == null) goto L11;
        AndroidUtilities.cancelRunOnUIThread(r72);
        callEnded();
        return;
    L11:
        dispatchStateChanged(10);
        this.endCallAfterRequest = true;
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda50(this), 5000);
        return;
    L13:
        if (r0 != 10) goto L15;
        return;
    L15:
        if (r0 == 11) goto L44;
        dispatchStateChanged(10);
        if (this.privateCall != null) goto L23;
        this.onDestroyRunnable = r8;
        callEnded();
        if (this.callReqId == 0) goto L43;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.callReqId, false);
        this.callReqId = 0;
        return;
    L43:
        return;
    L23:
        TL_phone.discardCall r02 = new TL_phone.discardCall();
        TLRPC.TL_inputPhoneCall r2 = new TLRPC.TL_inputPhoneCall();
        r02.peer = r2;
        TL_phone.PhoneCall r3 = this.privateCall;
        r2.access_hash = r3.access_hash;
        r2.id = r3.id;
        r02.duration = (int) (getCallDuration() / 1000);
        NativeInstance r1 = this.tgVoip[0];
        if (r1 == null) goto L26;
        long r12 = r1.getPreferredRelayId();
    L27:
        r02.connection_id = r12;
        if (r7 != 2) goto L30;
        r02.reason = new TLRPC.TL_phoneCallDiscardReasonDisconnect();
    L40:
        FileLog.e("discardCall " + r02.reason);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new VoIPService$$ExternalSyntheticLambda51(this), 65536);
        this.onDestroyRunnable = r8;
        callEnded();
        return;
    L30:
        if (r7 != 3) goto L32;
        r02.reason = new TLRPC.TL_phoneCallDiscardReasonMissed();
        goto L40
    L32:
        if (r7 != 4) goto L34;
        r02.reason = new TLRPC.TL_phoneCallDiscardReasonBusy();
        goto L40
    L34:
        if (r7 == 5) goto L36;
        r02.reason = new TLRPC.TL_phoneCallDiscardReasonHangup();
        goto L40
    L36:
        TLRPC.TL_phoneCallDiscardReasonMigrateConferenceCall r73 = new TLRPC.TL_phoneCallDiscardReasonMigrateConferenceCall();
        r02.reason = r73;
        r73.slug = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L40
    L26:
        r12 = 0;
        goto L27
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.appDidLogout) goto L6;
        callEnded();
        return;
    }

    public void editCallMember(TLObject r8, Boolean r9, Boolean r10, Integer r11, Boolean r12, Runnable r13) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (this.groupCall == null) goto L39;
        TL_phone.editGroupCallParticipant r0 = new TL_phone.editGroupCallParticipant();
        r0.call = this.groupCall.getInputGroupCall();
        if ((r8 instanceof TLRPC.User) == false) goto L17;
        TLRPC.User r82 = (TLRPC.User) r8;
        if (UserObject.isUserSelf(r82) == false) goto L13;
        TLRPC.InputPeer r1 = this.groupCallPeer;
        if (r1 == null) goto L13;
        r0.participant = r1;
    L25:
        if (r9 == null) goto L27;
        r0.muted = r9.booleanValue();
        r0.flags |= 1;
    L27:
        if (r11 == null) goto L29;
        r0.volume = r11.intValue();
        r0.flags |= 2;
    L29:
        if (r12 == null) goto L31;
        r0.raise_hand = r12.booleanValue();
        r0.flags |= 4;
    L31:
        if (r10 == null) goto L34;
        r0.video_stopped = r10.booleanValue();
        r0.flags |= 8;
    L34:
        if (BuildVars.LOGS_ENABLED == false) goto L36;
        FileLog.d("edit group call flags = " + r0.flags);
    L36:
        int r83 = this.currentAccount;
        AccountInstance.getInstance(r83).getConnectionsManager().sendRequest(r0, new VoIPService$$ExternalSyntheticLambda67(this, r83, r13));
        return;
    L13:
        r0.participant = MessagesController.getInputPeer(r82);
        if (BuildVars.LOGS_ENABLED == false) goto L25;
        FileLog.d("edit group call part id = " + r0.participant.user_id + " access_hash = " + r0.participant.user_id);
        goto L25
    L17:
        if ((r8 instanceof TLRPC.Chat) == false) goto L25;
        r0.participant = MessagesController.getInputPeer((TLRPC.Chat) r8);
        if (BuildVars.LOGS_ENABLED == false) goto L25;
        StringBuilder r84 = new StringBuilder("edit group call part id = ");
        TLRPC.InputPeer r14 = r0.participant;
        long r3 = r14.chat_id;
        if (r3 != 0) goto L24;
        r3 = r14.channel_id;
    L24:
        r84.append(r3);
        r84.append(" access_hash = ");
        r84.append(r0.participant.access_hash);
        FileLog.d(r84.toString());
        goto L25
    }

    public void forceRating() {
        this.forceRating = true;
    }

    public int getAccount() {
        return this.currentAccount;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public long getCallDuration() {
        if (this.callStartTime != 0) goto L6;
        return 0;
    L6:
        return SystemClock.elapsedRealtime() - this.callStartTime;
    }

    public long getCallID() {
        TL_phone.PhoneCall r2 = this.privateCall;
        if (r2 != null) goto L5;
        return 0;
    L5:
        return r2.id;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public int getCallState() {
        return this.currentState;
    }

    public long getCallerId() {
        TLRPC.User r0 = this.user;
        if (r0 != null) goto L5;
        TLRPC.Chat r2 = this.chat;
        if (r2 != null) goto L9;
        return 0;
    L9:
        return -r2.id;
    L5:
        return r0.id;
    }

    public TLRPC.Chat getChat() {
        return this.chat;
    }

    @TargetApi(26)
    public CallConnection getConnectionAndStartCall() {
        if (this.systemCallConnection != null) goto L12;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("creating call connection");
    L7:
        CallConnection r0 = new CallConnection(this);
        this.systemCallConnection = r0;
        r0.setInitializing();
        if (this.isOutgoing == false) goto L10;
        VoIPService$$ExternalSyntheticLambda117 r02 = new VoIPService$$ExternalSyntheticLambda117(this);
        this.delayedStartOutgoingCall = r02;
        AndroidUtilities.runOnUIThread(r02, 2000);
    L10:
        this.systemCallConnection.setAddress(Uri.fromParts("tel", "+99084" + this.user.id, null), 1);
        CallConnection r03 = this.systemCallConnection;
        TLRPC.User r1 = this.user;
        r03.setCallerDisplayName(ContactsController.formatName(r1.first_name, r1.last_name), 1);
    L12:
        return this.systemCallConnection;
    }

    public int getCurrentAudioRoute() {
        if (USE_CONNECTION_SERVICE == false) goto L22;
        CallConnection r0 = this.systemCallConnection;
        if (r0 == null) goto L20;
        if (r0.getCallAudioState() == null) goto L20;
        int r02 = this.systemCallConnection.getCallAudioState().getRoute();
        if (r02 == 1) goto L18;
        if (r02 != 2) goto L12;
        return 2;
    L12:
        if (r02 == 4) goto L18;
        if (r02 != 8) goto L20;
        return 1;
    L18:
        return 0;
    L20:
        return this.audioRouteToSet;
    L22:
        if (this.audioConfigured == false) goto L31;
        AudioManager r4 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        VoipAudioManager r03 = VoipAudioManager.get();
        if (r4.isBluetoothScoOn() == false) goto L27;
        return 2;
    L27:
        if (r03.isSpeakerphoneOn() == false) goto L29;
        return 1;
    L29:
        return 0;
    L31:
        return this.audioRouteToSet;
    }

    public String getDebugString() {
        NativeInstance r1 = this.tgVoip[0];
        if (r1 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r1.getDebugInfo();
    }

    public byte[] getEncryptionKey() {
        return this.authKey;
    }

    public byte[] getGA() {
        return this.g_a;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public TLRPC.GroupCall getGroupCall() {
        ChatObject.Call r0 = this.groupCall;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.call;
    }

    public CountDownLatch getGroupCallBottomSheetLatch() {
        return this.groupCallBottomSheetLatch;
    }

    public long getGroupCallID() {
        ChatObject.Call r2 = this.groupCall;
        if (r2 == null) goto L8;
        TLRPC.GroupCall r22 = r2.call;
        if (r22 != null) goto L7;
        return 0;
    L7:
        return r22.id;
    L8:
        return 0;
    }

    public TLRPC.InputPeer getGroupCallPeer() {
        return this.groupCallPeer;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public ArrayList<TLRPC.GroupCallParticipant> getGroupParticipants() {
        ChatObject.Call r0 = this.groupCall;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.sortedParticipants;
    }

    public String getLastError() {
        return this.lastError;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public TL_phone.PhoneCall getPrivateCall() {
        return this.privateCall;
    }

    public int getRemoteAudioState() {
        return this.remoteAudioState;
    }

    public int getRemoteVideoState() {
        return this.remoteVideoState;
    }

    public long getSelfId() {
        TLRPC.InputPeer r0 = this.groupCallPeer;
        if (r0 != null) goto L7;
        return UserConfig.getInstance(this.currentAccount).clientUserId;
    L7:
        if ((r0 instanceof TLRPC.TL_inputPeerUser) == false) goto L11;
        return r0.user_id;
    L11:
        if ((r0 instanceof TLRPC.TL_inputPeerChannel) == false) goto L15;
        return -r0.channel_id;
    L15:
        return -r0.chat_id;
    }

    public String getTitle() {
        TLRPC.User r0 = this.user;
        if (r0 != null) goto L5;
        TLRPC.Chat r1 = this.chat;
        if (r1 != null) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return r1.title;
    L5:
        return ContactsController.formatName(r0.first_name, r0.last_name);
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public TLRPC.User getUser() {
        return this.user;
    }

    public int getVideoState(boolean r1) {
        return this.videoState[r1 ? 1 : 0];
    }

    public void handleNotificationAction(Intent r4) {
        if ((getPackageName() + ".END_CALL").equals(r4.getAction()) == false) goto L7;
        stopForeground(true);
        hangUp();
        return;
    L7:
        if ((getPackageName() + ".DECLINE_CALL").equals(r4.getAction()) == false) goto L11;
        stopForeground(true);
        declineIncomingCall(4, null);
        return;
    L11:
        if ((getPackageName() + ".ANSWER_CALL").equals(r4.getAction()) == false) goto L14;
        acceptIncomingCallFromNotification();
        return;
    }

    public void hangUp() {
        hangUp(0, null);
    }

    public void hangUp(int r2) {
        hangUp(r2, null);
    }

    public void hangUp(int r4, Runnable r5) {
        int r0 = this.currentState;
        if (r0 != 16) goto L5;
    L10:
        int r02 = 3;
    L11:
        declineIncomingCall(r02, r5);
        if (this.groupCall != null) goto L14;
        return;
    L14:
        if (r4 == 2) goto L27;
        if (r4 == 1) goto L18;
        TL_phone.leaveGroupCall r42 = new TL_phone.leaveGroupCall();
        r42.call = this.groupCall.getInputGroupCall();
        r42.source = this.mySource[0];
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r42, new VoIPService$$ExternalSyntheticLambda103(this));
        return;
    L18:
        if (this.chat == null) goto L22;
        TLRPC.ChatFull r43 = MessagesController.getInstance(this.currentAccount).getChatFull(this.chat.id);
        if (r43 == null) goto L22;
        r43.flags &= -2097153;
        r43.call = null;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chat.id), Long.valueOf(this.groupCall.call.id), Boolean.FALSE});
    L22:
        TL_phone.discardGroupCall r44 = new TL_phone.discardGroupCall();
        r44.call = this.groupCall.getInputGroupCall();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r44, new VoIPService$$ExternalSyntheticLambda102(this));
        return;
    L27:
        return;
    L5:
        if (r0 == 13) goto L7;
    L9:
        r02 = 1;
        goto L11
    L7:
        if (this.isOutgoing == false) goto L9;
        goto L9
    }

    public void hangUp(Runnable r2) {
        hangUp(0, r2);
    }

    public boolean hasEarpiece() {
        if (USE_CONNECTION_SERVICE == false) goto L13;
        CallConnection r1 = this.systemCallConnection;
        if (r1 == null) goto L13;
        if (r1.getCallAudioState() == null) goto L13;
        if ((this.systemCallConnection.getCallAudioState().getSupportedRouteMask() & 5) == 0) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (((TelephonyManager) getSystemService("phone")).getPhoneType() == 0) goto L15;
        return true;
    L15:
        Boolean r12 = this.mHasEarpiece;
        if (r12 != null) goto L18;
        AudioManager r13 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);     // Catch: Throwable -> L22
        Method r3 = AudioManager.class.getMethod("getDevicesForStream", new Class[]{Integer.TYPE});     // Catch: Throwable -> L22
        int r0 = AudioManager.class.getField("DEVICE_OUT_EARPIECE").getInt(null);     // Catch: Throwable -> L22
        if ((((Integer) r3.invoke(r13, new Object[]{0})).intValue() & r0) != r0) goto L24;
        this.mHasEarpiece = Boolean.TRUE;     // Catch: Throwable -> L22
    L32:
    L31:
        return this.mHasEarpiece.booleanValue();
    L24:
        this.mHasEarpiece = Boolean.FALSE;     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L29;
        FileLog.e("Error while checking earpiece! ", th);
    L29:
        this.mHasEarpiece = Boolean.TRUE;
        goto L31
    L18:
        return r12.booleanValue();
    }

    public boolean hasRate() {
        if (this.needRateCall == false) goto L5;
        return true;
    L5:
        if (this.forceRating == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean hasVideoCapturer() {
        if (this.captureDevice[0] == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isBluetoothHeadsetConnected() {
        if (USE_CONNECTION_SERVICE == false) goto L15;
        CallConnection r0 = this.systemCallConnection;
        if (r0 == null) goto L15;
        if (r0.getCallAudioState() == null) goto L15;
        if ((this.systemCallConnection.getCallAudioState().getSupportedRouteMask() & 2) == 0) goto L12;
        return true;
    L12:
        return false;
    L15:
        return this.isBtHeadsetConnected;
    }

    public boolean isBluetoothOn() {
        return ((AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).isBluetoothScoOn();
    }

    public boolean isBluetoothWillOn() {
        return this.needSwitchToBluetoothAfterScoActivates;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public boolean isCallingVideo() {
        TL_phone.PhoneCall r0 = this.privateCall;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.video;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public boolean isConference() {
        if (this.joinConference == null) goto L5;
        return true;
    L5:
        if (this.conference != null) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isConverting() {
        if (this.convertingVoip == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isFrontFaceCamera() {
        return this.isFrontFaceCamera;
    }

    public boolean isFullscreen(TLRPC.GroupCallParticipant r2, boolean r3) {
        if (this.currentBackgroundSink[r3 ? 1 : 0] == null) goto L12;
        String r1 = this.currentBackgroundEndpointId[r3 ? 1 : 0];
        if (r3 == false) goto L7;
        String r22 = r2.presentationEndpoint;
    L9:
        if (TextUtils.equals(r1, r22) == false) goto L14;
        return true;
    L14:
        return false;
    L7:
        r22 = r2.videoEndpoint;
        goto L9
    L12:
        return false;
    }

    public boolean isHangingUp() {
        if (this.currentState != 10) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isHeadsetPlugged() {
        return this.isHeadsetPlugged;
    }

    public boolean isJoined() {
        int r2 = this.currentState;
        if (r2 != 1) goto L5;
        return false;
    L5:
        if (r2 == 6) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean isMicMute() {
        return this.micMute;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public boolean isOutgoing() {
        return this.isOutgoing;
    }

    public boolean isScreencast() {
        return this.isPrivateScreencast;
    }

    public boolean isSpeakerphoneOn() {
        boolean r0 = USE_CONNECTION_SERVICE;
        if (r0 == false) goto L19;
        CallConnection r1 = this.systemCallConnection;
        if (r1 == null) goto L19;
        if (r1.getCallAudioState() == null) goto L19;
        int r02 = this.systemCallConnection.getCallAudioState().getRoute();
        if (hasEarpiece() == false) goto L15;
        if (r02 != 8) goto L13;
        return true;
    L13:
        return false;
    L15:
        if (r02 != 2) goto L17;
        return true;
    L17:
        return false;
    L19:
        if (this.audioConfigured == false) goto L28;
        if (r0 == true) goto L28;
        AudioManager r03 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        VoipAudioManager r12 = VoipAudioManager.get();
        if (hasEarpiece() == false) goto L26;
        return r12.isSpeakerphoneOn();
    L26:
        return r03.isBluetoothScoOn();
    L28:
        return this.speakerphoneStateToSet;
    }

    public boolean isSwitchingCamera() {
        return this.switchingCamera;
    }

    public boolean isSwitchingStream() {
        return this.switchingStream;
    }

    public boolean isVideoAvailable() {
        return this.isVideoAvailable;
    }

    public void migrateToChat(TLRPC.Chat r1) {
        this.chat = r1;
    }

    public boolean mutedByAdmin() {
        ChatObject.Call r0 = this.groupCall;
        if (r0 == null) goto L14;
        long r1 = getSelfId();
        TLRPC.GroupCallParticipant r02 = r0.participants.get(r1);
        if (r02 != null) goto L7;
        return false;
    L7:
        if (r02.can_self_unmute == false) goto L9;
        return false;
    L9:
        if (r02.muted == true) goto L11;
        return false;
    L11:
        if (ChatObject.canManageCalls(this.chat) == true) goto L19;
        return true;
    L19:
        return false;
    L14:
        return false;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor r1, int r2) {
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int r2) {
        if (r2 != 1) goto L6;
        this.hasAudioFocus = true;
        return;
    L6:
        this.hasAudioFocus = false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return null;
    }

    public void onCallUpdated(TL_phone.PhoneCall r8) {
        if (this.user == null) goto L113;
        TL_phone.PhoneCall r0 = this.privateCall;
        if (r0 != null) goto L9;
        this.pendingUpdates.add(r8);
        return;
    L9:
        if (r8 != null) goto L12;
        return;
    L12:
        if (r8.id == r0.id) goto L18;
        if (BuildVars.LOGS_ENABLED == false) goto L110;
        FileLog.w("onCallUpdated called with wrong call id (got " + r8.id + ", expected " + this.privateCall.id + ")");
        return;
    L110:
        return;
    L18:
        if (r8.access_hash != 0) goto L21;
        r8.access_hash = r0.access_hash;
    L21:
        if (BuildVars.LOGS_ENABLED == false) goto L23;
        FileLog.d("Call updated: " + r8);
    L23:
        long r02 = this.privateCall.key_fingerprint;
        this.privateCall = r8;
        if ((r8 instanceof TL_phone.TL_phoneCallDiscarded) == false) goto L45;
        this.needSendDebugLog = r8.need_debug;
        this.needRateCall = r8.need_rating;
        if (BuildVars.LOGS_ENABLED == false) goto L28;
        FileLog.d("call discarded, stopping service");
    L28:
        VoIPDebugToSend r03 = MessagesController.getInstance(this.currentAccount).voipDebug;
        if (r03 == null) goto L31;
        r03.done(r8.id, r8.need_debug);
    L31:
        TLRPC.PhoneCallDiscardReason r82 = r8.reason;
        if ((r82 instanceof TLRPC.TL_phoneCallDiscardReasonMigrateConferenceCall) == false) goto L39;
        TLRPC.TL_inputGroupCallSlug r04 = new TLRPC.TL_inputGroupCallSlug();
        this.joinConference = r04;
        r04.slug = ((TLRPC.TL_phoneCallDiscardReasonMigrateConferenceCall) r82).slug;
        stopRinging();
        NativeInstance[] r83 = this.tgVoip;
        NativeInstance r05 = r83[0];
        if (r05 == null) goto L36;
        this.prevTrafficStats = null;
        this.convertingVoip = r05;
        r83[0] = null;
    L36:
        startConferenceGroupCall(false, 0, null, false);
        return;
    L39:
        if ((r82 instanceof TLRPC.TL_phoneCallDiscardReasonBusy) == false) goto L42;
        dispatchStateChanged(17);
        this.playingSound = true;
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda129(this));
        AndroidUtilities.runOnUIThread(this.afterSoundRunnable, 1500);
        endConnectionServiceCall(1500);
        stopSelf();
        return;
    L42:
        callEnded();
        return;
    L45:
        if ((r8 instanceof TL_phone.TL_phoneCall) == false) goto L89;
        if (this.authKey != null) goto L89;
        byte[] r06 = r8.g_a_or_b;
        if (r06 != null) goto L56;
        if (BuildVars.LOGS_ENABLED == false) goto L53;
        FileLog.w("stopping VoIP service, Ga == null");
    L53:
        callFailed();
        return;
    L56:
        if (Arrays.equals(this.g_a_hash, Utilities.computeSHA256(r06, 0, r06.length)) == false) goto L58;
        this.g_a = r8.g_a_or_b;
        BigInteger r07 = new BigInteger(1, r8.g_a_or_b);
        BigInteger r2 = new BigInteger(1, MessagesStorage.getInstance(this.currentAccount).getSecretPBytes());
        if (Utilities.isGoodGaAndGb(r07, r2) == false) goto L65;
        byte[] r08 = r07.modPow(new BigInteger(1, this.a_or_b), r2).toByteArray();
        if (r08.length <= 256) goto L74;
        byte[] r1 = new byte[256];
        System.arraycopy(r08, r08.length - 256, r1, 0, 256);
    L72:
        r08 = r1;
    L79:
        byte[] r12 = Utilities.computeSHA1(r08);
        byte[] r4 = new byte[8];
        System.arraycopy(r12, r12.length - 8, r4, 0, 8);
        this.authKey = r08;
        long r09 = Utilities.bytesToLong(r4);
        this.keyFingerprint = r09;
        if (r09 != r8.key_fingerprint) goto L82;
        initiateActualEncryptedCall();
        return;
    L82:
        if (BuildVars.LOGS_ENABLED == false) goto L84;
        FileLog.w("key fingerprints don't match");
    L84:
        callFailed();
        return;
    L74:
        if (r08.length >= 256) goto L79;
        r1 = new byte[256];
        System.arraycopy(r08, 0, r1, 256 - r08.length, r08.length);
        int r42 = 0;
    L77:
        if (r42 >= (256 - r08.length)) goto L72;
        r1[r42] = 0;
        r42 = r42 + 1;
        goto L77
    L65:
        if (BuildVars.LOGS_ENABLED == false) goto L67;
        FileLog.w("stopping VoIP service, bad Ga and Gb (accepting)");
    L67:
        callFailed();
        return;
    L58:
        if (BuildVars.LOGS_ENABLED == false) goto L60;
        FileLog.w("stopping VoIP service, Ga hash doesn't match");
    L60:
        callFailed();
        return;
    L89:
        if ((r8 instanceof TL_phone.TL_phoneCallAccepted) == false) goto L95;
        if (this.authKey != null) goto L95;
        processAcceptedCall();
        return;
    L95:
        if (this.currentState == 13) goto L97;
        return;
    L97:
        if (r8.receive_date == 0) goto L112;
        dispatchStateChanged(16);
        if (BuildVars.LOGS_ENABLED == false) goto L101;
        FileLog.d("!!!!!! CALL RECEIVED");
    L101:
        Runnable r84 = this.connectingSoundRunnable;
        if (r84 == null) goto L104;
        AndroidUtilities.cancelRunOnUIThread(r84);
        this.connectingSoundRunnable = null;
    L104:
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda130(this));
        Runnable r85 = this.timeoutRunnable;
        if (r85 == null) goto L107;
        AndroidUtilities.cancelRunOnUIThread(r85);
        this.timeoutRunnable = null;
    L107:
        VoIPService$$ExternalSyntheticLambda131 r86 = new VoIPService$$ExternalSyntheticLambda131(this);
        this.timeoutRunnable = r86;
        AndroidUtilities.runOnUIThread(r86, MessagesController.getInstance(this.currentAccount).callRingTimeout);
        return;
    L112:
        return;
    }

    public void onCameraFirstFrameAvailable() {
        int r0 = 0;
    L4:
        if (r0 >= this.stateListeners.size()) goto L6;
        this.stateListeners.get(r0).onCameraFirstFrameAvailable();
        r0 = r0 + 1;
        goto L4
    }

    public void onConnectionStateChanged(int r1, boolean r2) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda124(this, r1));
    }

    @Override // android.app.Service
    @SuppressLint({"InvalidWakeLockTag"})
    public void onCreate() {
        super.onCreate();
        if (BuildVars.LOGS_ENABLED == false) goto L60;
        FileLog.d("=============== VoIPService STARTING ===============");
    L60:
        AudioManager r2 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);     // Catch: Exception -> L9
        if (r2.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER") == null) goto L11;
        Instance.setBufferSize(Integer.parseInt(r2.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")));     // Catch: Exception -> L9
    L12:
        PowerManager.WakeLock r0 = ((PowerManager) getSystemService("power")).newWakeLock(1, "telegram-voip");     // Catch: Exception -> L9
        this.cpuWakelock = r0;     // Catch: Exception -> L9
        r0.acquire();     // Catch: Exception -> L9
        if (r2.isBluetoothScoAvailableOffCall() == false) goto L15;
        BluetoothAdapter r02 = BluetoothAdapter.getDefaultAdapter();     // Catch: Exception -> L9
    L16:
        this.btAdapter = r02;     // Catch: Exception -> L9
        IntentFilter r03 = new IntentFilter();     // Catch: Exception -> L9
        r03.addAction("android.net.conn.CONNECTIVITY_CHANGE");     // Catch: Exception -> L9
        if (USE_CONNECTION_SERVICE == true) goto L22;
        r03.addAction(ACTION_HEADSET_PLUG);     // Catch: Exception -> L9
        if (this.btAdapter == null) goto L21;
        r03.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");     // Catch: Exception -> L9
        r03.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");     // Catch: Exception -> L9
    L21:
        r03.addAction("android.intent.action.PHONE_STATE");     // Catch: Exception -> L9
        r03.addAction("android.intent.action.SCREEN_ON");     // Catch: Exception -> L9
        r03.addAction("android.intent.action.SCREEN_OFF");     // Catch: Exception -> L9
    L22:
        registerReceiver(this.receiver, r03);     // Catch: Exception -> L9
        fetchBluetoothDeviceName();     // Catch: Exception -> L9
        if (this.audioDeviceCallback == null) goto L58;
    L28:
        AudioDeviceCallback r04 = this.audioDeviceCallback;     // Catch: Exception -> L9
        if (r04 == null) goto L31;
        r2.registerAudioDeviceCallback(r04, new Handler(Looper.getMainLooper()));     // Catch: Exception -> L9
    L31:
        r2.registerMediaButtonEventReceiver(new ComponentName(this, VoIPMediaButtonReceiver.class));     // Catch: Exception -> L9
        checkUpdateBluetoothHeadset();     // Catch: Exception -> L9
    L37:
        int r05 = Build.VERSION.SDK_INT;
        if (r05 >= 26) goto L40;
        return;
    L40:
        if (callIShouldHavePutIntoIntent == null) goto L53;
        NotificationsController.checkOtherNotificationsChannel();
        FilesMigrationService$$ExternalSyntheticApiModelOutline1.m();
        Notification.Builder r22 = FilesMigrationService$$ExternalSyntheticApiModelOutline0.m(this, NotificationsController.OTHER_NOTIFICATIONS_CHANNEL).setContentTitle(LocaleController.getString(R.string.VoipOutgoingCall)).setShowWhen(false);
        if (this.groupCall != null) goto L44;
        r22.setSmallIcon(R.drawable.call);
    L49:
        this.foregroundStarted = true;
        if (r05 < 33) goto L52;
        this.foregroundId = 201;
        Notification r06 = r22.build();
        this.foregroundNotification = r06;
        int r1 = getCurrentForegroundType();
        this.lastForegroundType = r1;
        startForeground(201, r06, r1);
        return;
    L52:
        this.foregroundId = 201;
        Notification r07 = r22.build();
        this.foregroundNotification = r07;
        startForeground(201, r07);
        return;
    L44:
        if (isMicMute() == false) goto L46;
        int r4 = R.drawable.voicechat_muted;
    L47:
        r22.setSmallIcon(r4);
        goto L49
    L46:
        r4 = R.drawable.voicechat_active;
        goto L47
    L53:
        NotificationsController.checkOtherNotificationsChannel();
        FilesMigrationService$$ExternalSyntheticApiModelOutline1.m();
        Notification.Builder r23 = FilesMigrationService$$ExternalSyntheticApiModelOutline0.m(this, NotificationsController.OTHER_NOTIFICATIONS_CHANNEL).setContentTitle(LocaleController.getString(R.string.VoipCallEnded)).setShowWhen(false);
        r23.setSmallIcon(R.drawable.call);
        this.foregroundStarted = true;
        if (r05 < 33) goto L56;
        this.foregroundId = 201;
        Notification r08 = r23.build();
        this.foregroundNotification = r08;
        int r12 = getCurrentForegroundType();
        this.lastForegroundType = r12;
        startForeground(201, r08, r12);
        return;
    L56:
        this.foregroundId = 201;
        Notification r09 = r23.build();
        this.foregroundNotification = r09;
        startForeground(201, r09);
        return;
    L58:
        this.audioDeviceCallback = new AnonymousClass10(this);     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        FileLog.e(th);     // Catch: Exception -> L9
        this.audioDeviceCallback = null;     // Catch: Exception -> L9
        goto L28
    L15:
        r02 = null;
        goto L16
    L11:
        Instance.setBufferSize(AudioTrack.getMinBufferSize(48000, 4, 2) / 2);     // Catch: Exception -> L9
    L9:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L36;
        FileLog.e("error initializing voip controller", e);
    L36:
        callFailed();
        goto L37
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("=============== VoIPService STOPPING ===============");
    L5:
        stopForeground(true);
        stopRinging();
        if (this.currentAccount >= 0) goto L8;
    L13:
        SensorManager r1 = (SensorManager) ApplicationLoader.applicationContext.getSystemService("sensor");
        if (r1.getDefaultSensor(8) == null) goto L16;
        r1.unregisterListener(this);
    L16:
        PowerManager.WakeLock r12 = this.proximityWakelock;
        if (r12 == null) goto L22;
        if (r12.isHeld() == false) goto L22;
        this.proximityWakelock.release();
    L22:
        if (this.updateNotificationRunnable == null) goto L24;
        Utilities.globalQueue.cancelRunnable(this.updateNotificationRunnable);
        this.updateNotificationRunnable = null;
    L24:
        Runnable r13 = this.switchingStreamTimeoutRunnable;
        if (r13 == null) goto L27;
        AndroidUtilities.cancelRunOnUIThread(r13);
        this.switchingStreamTimeoutRunnable = null;
    L27:
        unregisterReceiver(this.receiver);
        Runnable r14 = this.timeoutRunnable;
        if (r14 == null) goto L30;
        AndroidUtilities.cancelRunOnUIThread(r14);
        this.timeoutRunnable = null;
    L30:
        ConferenceCall r15 = this.conference;
        if (r15 == null) goto L33;
        r15.destroy();
    L33:
        super.onDestroy();
        sharedInstance = null;
        FileLog.e("(5) set sharedInstance = null");
        Arrays.fill(this.mySource, 0);
        cancelGroupCheckShortPoll();
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda71());
        if (this.tgVoip[0] == null) goto L44;
        StatsController.getInstance(this.currentAccount).incrementTotalCallsTime(getStatsNetworkType(), ((int) (getCallDuration() / 1000)) % 5);
        onTgVoipPreStop();
        boolean r16 = this.tgVoip[0].isGroup();
        NativeInstance[] r6 = this.tgVoip;
        if (r16 == false) goto L42;
        NativeInstance r17 = r6[0];
        DispatchQueue r62 = Utilities.globalQueue;
        Objects.requireNonNull(r17);
        r62.postRunnable(new VoIPService$$ExternalSyntheticLambda72(r17));
        Iterator<Map.Entry<String, Integer>> r18 = this.currentStreamRequestTimestamp.entrySet().iterator();
    L39:
        if (r18.hasNext() == false) goto L41;
        AccountInstance.getInstance(this.currentAccount).getConnectionsManager().cancelRequest(r18.next().getValue().intValue(), true);
        goto L39
    L41:
        this.currentStreamRequestTimestamp.clear();
    L43:
        this.prevTrafficStats = null;
        this.callStartTime = 0;
        this.tgVoip[0] = null;
        Instance.destroyInstance();
        goto L44
    L42:
        Instance.FinalState r19 = r6[0].stop();
        updateTrafficStats(this.tgVoip[0], r19.trafficStats);
        onTgVoipStop(r19);
    L44:
        destroyConverting();
        NativeInstance r110 = this.tgVoip[1];
        if (r110 == null) goto L47;
        DispatchQueue r63 = Utilities.globalQueue;
        Objects.requireNonNull(r110);
        r63.postRunnable(new VoIPService$$ExternalSyntheticLambda72(r110));
        this.tgVoip[1] = null;
    L47:
        int r111 = 0;
    L48:
        long[] r64 = this.captureDevice;
        if (r111 >= r64.length) goto L57;
        long r7 = r64[r111];
        if (r7 == 0) goto L56;
        if (this.destroyCaptureDevice[r111] == false) goto L55;
        NativeInstance.destroyVideoCapturer(r7);
    L55:
        this.captureDevice[r111] = 0;
    L56:
        r111 = r111 + 1;
        goto L48
    L57:
        this.cpuWakelock.release();
        AudioManager r112 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (this.playingSound == true) goto L81;
        VoipAudioManager r4 = VoipAudioManager.get();
        if (USE_CONNECTION_SERVICE == false) goto L62;
    L102:
        r112.unregisterMediaButtonEventReceiver(new ComponentName(this, VoIPMediaButtonReceiver.class));     // Catch: Exception -> L74
    L76:
        AudioDeviceCallback r42 = this.audioDeviceCallback;
        if (r42 == null) goto L79;
        r112.unregisterAudioDeviceCallback(r42);
    L79:
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda74(this));
    L74:
        e = move-exception;
        FileLog.e(e);
        goto L76
    L62:
        if (this.isBtHeadsetConnected == false) goto L64;
    L67:
        r112.stopBluetoothSco();
        r112.setBluetoothScoOn(false);
        r4.setSpeakerphoneOn(false);
        this.bluetoothScoActive = false;
        this.bluetoothScoConnecting = false;
    L69:
        if (this.onDestroyRunnable != null) goto L71;
        DispatchQueue r43 = Utilities.globalQueue;
        VoIPService$$ExternalSyntheticLambda73 r5 = new VoIPService$$ExternalSyntheticLambda73(r112);
        setModeRunnable = r5;
        r43.postRunnable(r5);
    L71:
        r112.abandonAudioFocus(this);
        goto L102
    L64:
        if (this.bluetoothScoActive == true) goto L67;
        if (this.bluetoothScoConnecting == false) goto L69;
    L81:
        if (this.hasAudioFocus == false) goto L84;
        r112.abandonAudioFocus(this);
    L84:
        if (USE_CONNECTION_SERVICE == true) goto L86;
    L93:
        VoIPHelper.lastCallTime = SystemClock.elapsedRealtime();
        setSinks(null, null);
        Runnable r113 = this.onDestroyRunnable;
        if (r113 == null) goto L96;
        r113.run();
    L96:
        int r114 = this.currentAccount;
        if (r114 < 0) goto L108;
        ConnectionsManager.getInstance(r114).setAppPaused(true, false);
        if (ChatObject.isChannel(this.chat) == false) goto L109;
        MessagesController.getInstance(this.currentAccount).startShortPoll(this.chat, this.classGuid, true);
        return;
    L109:
        return;
    L108:
        return;
    L86:
        if (this.didDeleteConnectionServiceContact == true) goto L88;
        ContactsController.getInstance(this.currentAccount).deleteConnectionServiceContact();
    L88:
        CallConnection r115 = this.systemCallConnection;
        if (r115 == null) goto L93;
        if (this.playingSound == true) goto L93;
        r115.destroy();
        goto L93
    L8:
        if (ApplicationLoader.mainInterfacePaused == false) goto L10;
    L11:
        MessagesController.getInstance(this.currentAccount).ignoreSetOnline = false;
    L12:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.appDidLogout);
        goto L13
    L10:
        if (ApplicationLoader.isScreenOn == true) goto L12;
        goto L11
    }

    public void onGroupCallParticipantsUpdate(TL_update.TL_updateGroupCallParticipants r12) {
        ChatObject.Call r0 = this.groupCall;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.call.id != r12.call.id) goto L69;
        r0.shadyLeftParticipants.clear();
        this.groupCall.shadyJoinParticipants.clear();
        if (this.conference == null) goto L14;
        this.groupCall.processParticipantsUpdate(r12, false);
        this.conference.updateParticipants(this.groupCall.sortedParticipants, true);
        int r02 = 0;
    L11:
        if (r02 >= this.stateListeners.size()) goto L13;
        this.stateListeners.get(r02).onStateChanged(getCallState());
        r02 = r02 + 1;
        goto L11
    L13:
        ChatObject.Call r03 = this.groupCall;
        r03.shadyLeftParticipants.addAll(this.conference.getShadyLeftParticipants(r03.sortedParticipants));
        ChatObject.Call r04 = this.groupCall;
        r04.shadyJoinParticipants.addAll(this.conference.getShadyJoiningParticipants(r04.sortedParticipants));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{0L, Long.valueOf(this.groupCall.call.id), Boolean.FALSE});
    L14:
        long r3 = getSelfId();
        int r05 = r12.participants.size();
        int r5 = 0;
    L15:
        if (r5 >= r05) goto L70;
        TLRPC.GroupCallParticipant r6 = r12.participants.get(r5);
        if (r6.left == false) goto L34;
        int r62 = r6.source;
        if (r62 == 0) goto L54;
        if (r62 != this.mySource[0]) goto L54;
        int r63 = 0;
        int r7 = 0;
    L23:
        if (r63 >= r05) goto L30;
        TLRPC.GroupCallParticipant r9 = r12.participants.get(r63);
        if (r9.self == false) goto L27;
    L28:
        r7 = r7 + 1;
    L29:
        r63 = r63 + 1;
        goto L23
    L27:
        if (r9.source != this.mySource[0]) goto L29;
    L30:
        if (r7 <= 1) goto L54;
        hangUp(2);
        return;
    L54:
        r5 = r5 + 1;
        goto L15
    L34:
        if (MessageObject.getPeerId(r6.peer) != r3) goto L54;
        int r72 = r6.source;
        int r92 = this.mySource[0];
        if (r72 == r92) goto L45;
        if (r92 == 0) goto L45;
        if (r72 == 0) goto L45;
        if (BuildVars.LOGS_ENABLED == false) goto L42;
        FileLog.d("source mismatch my = " + this.mySource[0] + " psrc = " + r6.source);
    L42:
        hangUp(2);
        return;
    L45:
        if (ChatObject.isChannel(this.chat) == false) goto L52;
        if (this.currentGroupModeStreaming == false) goto L52;
        if (r6.can_self_unmute == false) goto L52;
        this.switchingStream = true;
        createGroupInstance(0, false, true);
    L52:
        if (r6.muted == false) goto L54;
        setMicMute(true, false, false);
        goto L54
    L70:
        return;
    }

    public void onGroupCallUpdated(TLRPC.GroupCall r7) {
        ChatObject.Call r0 = this.groupCall;
        if (r0 == null) goto L50;
        TLRPC.GroupCall r02 = r0.call;
        if (r02.id != r7.id) goto L50;
        if ((r02 instanceof TLRPC.TL_groupCallDiscarded) == false) goto L11;
        hangUp(2);
        return;
    L11:
        boolean r2 = false;
        if (this.myParams != null) goto L52;
    L17:
        boolean r03 = false;
    L19:
        if (this.conference == null) goto L22;
        this.groupCall.processGroupCallUpdate(r7);
    L22:
        if (this.currentState != 1) goto L24;
    L25:
        TLRPC.TL_dataJSON r3 = this.myParams;
        if (r3 == null) goto L57;
        NativeInstance r5 = this.tgVoip[0];
        if (r5 != null) goto L32;
        this.lastGroupCallUpdate = r7;
        return;
    L32:
        if (this.playedConnectedSound == true) goto L34;
    L36:
        this.currentGroupModeStreaming = r03;
        if (r03 == false) goto L44;
        TLRPC.GroupCall r72 = this.groupCall.call;     // Catch: Exception -> L47
        if (r72 != null) goto L41;
    L43:
        r5.prepareForStream(r2);     // Catch: Exception -> L47
    L45:
        dispatchStateChanged(2);     // Catch: Exception -> L47
        return;
    L41:
        if (r72.rtmp_stream == false) goto L43;
        r2 = true;
        goto L43
    L44:
        r5.setJoinResponsePayload(r3.data);     // Catch: Exception -> L47
    L47:
        e = move-exception;
        FileLog.e(e);
        return;
    L34:
        if (r03 == this.currentGroupModeStreaming) goto L36;
        this.switchingStream = true;
        goto L36
    L57:
        return;
    L24:
        if (r03 != this.currentGroupModeStreaming) goto L25;
        return;
    L52:
        r03 = new JSONObject(this.myParams.data).optBoolean("stream");     // Catch: Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
    L50:
        this.lastGroupCallUpdate = r7;
    }

    public void onMediaButtonEvent(KeyEvent r3) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (r3.getKeyCode() == 79) goto L11;
        if (r3.getKeyCode() == 127) goto L11;
        if (r3.getKeyCode() == 85) goto L11;
        return;
    L11:
        if (r3.getAction() == 1) goto L13;
        return;
    L13:
        if (this.currentState != 15) goto L16;
        acceptIncomingCall();
        return;
    L16:
        setMicMute(!isMicMute(), false, true);
    }

    @Override // android.hardware.SensorEventListener
    @SuppressLint({"NewApi"})
    public void onSensorChanged(SensorEvent r5) {
        this.lastSensorEvent = r5;
        if (this.unmutedByHold == false) goto L5;
        return;
    L5:
        if (this.remoteVideoState == 2) goto L28;
        boolean r2 = false;
        if (this.videoState[0] != 2) goto L10;
        return;
    L10:
        if (r5.sensor.getType() != 8) goto L29;
        AudioManager r0 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        VoipAudioManager r1 = VoipAudioManager.get();
        if (this.audioRouteToSet == 0) goto L14;
        return;
    L14:
        if (this.isHeadsetPlugged == false) goto L16;
        return;
    L16:
        if (r1.isSpeakerphoneOn() == false) goto L18;
        return;
    L18:
        if (isBluetoothHeadsetConnected() == false) goto L23;
        if (r0.isBluetoothScoOn() == false) goto L23;
        return;
    L23:
        if (r5.values[0] >= Math.min(r5.sensor.getMaximumRange(), 3.0f)) goto L25;
        r2 = true;
    L25:
        checkIsNear(r2);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.nearEarEvent, new Object[]{Boolean.valueOf(r2)});
        return;
    L29:
        return;
    }

    public void onSignalBarCountChanged(int r2) {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda118(this, r2));
    }

    public void onSignalingData(TL_update.TL_updatePhoneCallSignalingData r7) {
        if (this.user == null) goto L13;
        NativeInstance r0 = this.tgVoip[0];
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isGroup() == false) goto L9;
        return;
    L9:
        if (getCallID() != r7.phone_call_id) goto L16;
        this.tgVoip[0].onSignalingDataReceive(r7.data);
        return;
    L16:
        return;
    }

    public void onSignalingData(byte[] r6) {
        if (this.privateCall != null) goto L5;
        return;
    L5:
        TL_phone.sendSignalingData r0 = new TL_phone.sendSignalingData();
        TLRPC.TL_inputPhoneCall r1 = new TLRPC.TL_inputPhoneCall();
        r0.peer = r1;
        TL_phone.PhoneCall r2 = this.privateCall;
        r1.access_hash = r2.access_hash;
        r1.id = r2.id;
        r0.data = r6;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
    }

    @Override // android.app.Service
    @SuppressLint({"MissingPermission", "InlinedApi"})
    public int onStartCommand(Intent r17, int r18, int r19) {
        if (sharedInstance != null) goto L5;
        int r0 = r17.getIntExtra("account", -1);
        this.currentAccount = r0;
        if (r0 == (-1)) goto L155;
        this.classGuid = ConnectionsManager.generateClassGuid();
        long r8 = r17.getLongExtra("user_id", 0);
        long r10 = r17.getLongExtra("chat_id", 0);
        this.createGroupCall = r17.getBooleanExtra("createGroupCall", false);
        byte[] r02 = r17.getByteArrayExtra("joinConference");
        if (r02 == null) goto L14;
        SerializedData r12 = new SerializedData(r02);
        this.joinConference = TLRPC.InputGroupCall.TLdeserialize(r12, r12.readInt32(true), true);
    L14:
        if (r17.hasExtra("mute_on_start") == false) goto L16;
        this.muteOnStart = Boolean.valueOf(r17.getBooleanExtra("mute_on_start", false));
    L16:
        byte[] r03 = r17.getByteArrayExtra("joinConferenceCall");
        if (r03 == null) goto L19;
        SerializedData r122 = new SerializedData(r03);
        this.joinConferenceCall = TLRPC.GroupCall.TLdeserialize(r122, r122.readInt32(true), true);
    L19:
        this.inviteUsers = r17.getLongArrayExtra("inviteUsers");
        this.hasFewPeers = r17.getBooleanExtra("hasFewPeers", false);
        this.isRtmpStream = r17.getBooleanExtra("isRtmpStream", false);
        this.joinHash = r17.getStringExtra("hash");
        long r123 = r17.getLongExtra("peerChannelId", 0);
        long r14 = r17.getLongExtra("peerChatId", 0);
        long r3 = r17.getLongExtra("peerUserId", 0);
        if (r14 == 0) goto L23;
        TLRPC.TL_inputPeerChat r04 = new TLRPC.TL_inputPeerChat();
        this.groupCallPeer = r04;
        r04.chat_id = r14;
        r04.access_hash = r17.getLongExtra("peerAccessHash", 0);
    L28:
        this.scheduleDate = r17.getIntExtra("scheduleDate", 0);
        this.isOutgoing = r17.getBooleanExtra("is_outgoing", false);
        this.videoCall = r17.getBooleanExtra("video_call", false);
        this.isVideoAvailable = r17.getBooleanExtra("can_video_call", false);
        this.notificationsDisabled = r17.getBooleanExtra("notifications_disabled", false);
        this.instantAccept = r17.getBooleanExtra("accept", false);
        boolean r32 = r17.getBooleanExtra("openFragment", false);
        if (r8 == 0) goto L32;
        this.user = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r8));
    L32:
        if (r10 == 0) goto L36;
        TLRPC.Chat r05 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r10));
        this.chat = r05;
        if (ChatObject.isChannel(r05) == false) goto L36;
        MessagesController.getInstance(this.currentAccount).startShortPoll(this.chat, this.classGuid, false);
    L36:
        loadResources();
        int r06 = 0;
    L37:
        ProxyVideoSink[] r6 = this.localSink;
        if (r06 >= r6.length) goto L157;
        r6[r06] = new ProxyVideoSink();
        this.remoteSink[r06] = new ProxyVideoSink();
        r06 = r06 + 1;
        goto L37
    L157:
        this.isHeadsetPlugged = ((AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).isWiredHeadsetOn();     // Catch: Exception -> L42
    L45:
        if (this.chat == null) goto L53;
        if (this.createGroupCall == true) goto L53;
        if (MessagesController.getInstance(this.currentAccount).getGroupCall(this.chat.id, false) != null) goto L53;
        FileLog.w("VoIPService: trying to open group call without call " + this.chat.id);
        stopSelf();
        return 2;
    L53:
        if (this.videoCall == false) goto L67;
        if (checkSelfPermission("android.permission.CAMERA") != 0) goto L60;
        this.captureDevice[0] = NativeInstance.createVideoCapturer(this.localSink[0], this.isFrontFaceCamera ? 1 : 0);
        int[] r07 = this.videoState;
        if (r10 == 0) goto L59;
        r07[0] = 1;
    L62:
        if (this.isBtHeadsetConnected == true) goto L67;
        if (this.isHeadsetPlugged == true) goto L67;
        setAudioOutput(0);
        goto L67
    L59:
        r07[0] = 2;
        goto L62
    L60:
        this.videoState[0] = 1;
    L67:
        if (this.user == null) goto L69;
    L77:
        sharedInstance = this;
        FileLog.e("(4) set sharedInstance = this");
        Object r4 = sync;
        monitor-enter(r4);
    L82:
        th = move-exception;
        throw th;
    L80:
        if (setModeRunnable == null) goto L84;
        Utilities.globalQueue.cancelRunnable(setModeRunnable);     // Catch: Throwable -> L82
        setModeRunnable = null;     // Catch: Throwable -> L82
    L84:
        monitor-exit(r4);     // Catch: Throwable -> L82
        VoIPGroupNotification.hide(this);
        if (this.joinConference == null) goto L99;
        if (MessagesController.getGlobalMainSettings().getBoolean("callmiconstart", true) == true) goto L91;
        this.micMute = true;
    L93:
        startConferenceGroupCall(false, 0, null, false);
        if (this.isBtHeadsetConnected == false) goto L96;
    L151:
        initializeAccountRelatedThings();
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda38(this));
        return 2;
    L96:
        if (this.isHeadsetPlugged == true) goto L151;
        setAudioOutput(0);
        goto L151
    L91:
        if (PermissionRequest.hasPermission("android.permission.RECORD_AUDIO") == true) goto L93;
        this.micMute = true;
        PermissionRequest.requestPermission("android.permission.RECORD_AUDIO", new VoIPService$$ExternalSyntheticLambda36(this));
        goto L93
    L99:
        if (this.isOutgoing == true) goto L101;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.closeInCallActivity, new Object[0]);
        TL_phone.PhoneCall r08 = callIShouldHavePutIntoIntent;
        this.privateCall = r08;
        if (r08 != null) goto L124;
    L126:
        boolean r09 = false;
    L127:
        this.videoCall = r09;
        if (r09 == false) goto L130;
        this.isVideoAvailable = true;
    L130:
        if (r09 == true) goto L132;
    L136:
        callIShouldHavePutIntoIntent = null;
        FileLog.e("(3) set VoIPService.callIShouldHavePutIntoIntent = null");
        if (this.instantAccept == false) goto L140;
        this.instantAccept = false;
        acceptIncomingCall();
    L143:
        if (r32 == false) goto L151;
        Activity r010 = AndroidUtilities.findActivity(this);
        if (r010 != null) goto L147;
        r010 = LaunchActivity.instance;
    L147:
        if (r010 != null) goto L149;
        r010 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    L149:
        if (r010 == null) goto L151;
        VoIPFragment.show(r010, this.currentAccount);
        goto L151
    L140:
        if (USE_CONNECTION_SERVICE == false) goto L142;
        acknowledgeCall(false);
        showNotification();
        goto L143
    L142:
        acknowledgeCall(true);
        goto L143
    L132:
        if (this.isBtHeadsetConnected == true) goto L136;
        if (this.isHeadsetPlugged == true) goto L136;
        setAudioOutput(0);
        goto L136
    L124:
        if (r08.video == false) goto L126;
        r09 = true;
        goto L127
    L101:
        if (this.user == null) goto L106;
        dispatchStateChanged(14);
        if (USE_CONNECTION_SERVICE == false) goto L105;
        TelecomManager r011 = (TelecomManager) getSystemService("telecom");
        Bundle r33 = new Bundle();
        Bundle r42 = new Bundle();
        r33.putParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE", addAccountToTelecomManager());
        r42.putInt("call_type", 1);
        r33.putBundle("android.telecom.extra.OUTGOING_CALL_EXTRAS", r42);
        ContactsController r43 = ContactsController.getInstance(this.currentAccount);
        TLRPC.User r7 = this.user;
        r43.createOrUpdateConnectionServiceContact(r7.id, r7.first_name, r7.last_name);
        r011.placeCall(Uri.fromParts("tel", "+99084" + this.user.id, null), r33);
    L112:
        if (r17.getBooleanExtra("start_incall_activity", false) == false) goto L151;
        Intent r012 = new Intent(this, LaunchActivity.class);
        if (this.user == null) goto L116;
        String r2 = "voip";
    L117:
        Intent r013 = r012.setAction(r2).addFlags(268435456);
        if (this.chat == null) goto L120;
        r013.putExtra("currentAccount", this.currentAccount);
    L120:
        startActivity(r013);
        goto L151
    L116:
        r2 = "voip_chat";
        goto L117
    L105:
        VoIPService$$ExternalSyntheticLambda37 r014 = new VoIPService$$ExternalSyntheticLambda37(this);
        this.delayedStartOutgoingCall = r014;
        AndroidUtilities.runOnUIThread(r014, 2000);
        goto L112
    L106:
        this.micMute = true;
        startGroupCall(0, null, false);
        if (this.isBtHeadsetConnected == true) goto L112;
        if (this.isHeadsetPlugged == true) goto L112;
        setAudioOutput(0);
        goto L112
    L69:
        if (this.chat != null) goto L77;
        if (this.joinConference != null) goto L77;
        if (BuildVars.LOGS_ENABLED == false) goto L75;
        FileLog.w("VoIPService: user == null AND chat == null");
    L75:
        stopSelf();
        return 2;
    L42:
        e = move-exception;
        FileLog.e(e);
        goto L45
    L23:
        if (r123 == 0) goto L26;
        TLRPC.TL_inputPeerChannel r015 = new TLRPC.TL_inputPeerChannel();
        this.groupCallPeer = r015;
        r015.channel_id = r123;
        r015.access_hash = r17.getLongExtra("peerAccessHash", 0);
        goto L28
    L26:
        if (r3 == 0) goto L28;
        TLRPC.TL_inputPeerUser r016 = new TLRPC.TL_inputPeerUser();
        this.groupCallPeer = r016;
        r016.user_id = r3;
        r016.access_hash = r17.getLongExtra("peerAccessHash", 0);
        goto L28
    L155:
        Segment$$ExternalSyntheticBUOutline1.m("No account specified when starting VoIP service");
        return 0;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.e("Tried to start the VoIP service when it's already started");
    L7:
        return 2;
    }

    public void playAllowTalkSound() {
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda111(this));
    }

    public void playConnectedSound() {
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda101(this));
        this.playedConnectedSound = true;
    }

    public void playStartRecordSound() {
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda110(this));
    }

    public void processMessageUpdate(MessageObject r6) {
        if (r6 == null) goto L30;
        TLRPC.Message r0 = r6.messageOwner;
        if (r0 != null) goto L8;
        return;
    L8:
        if ((r0.action instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L25;
        long r02 = r6.getDialogId();
        int r2 = r6.getId();
        TLRPC.TL_messageActionConferenceCall r62 = (TLRPC.TL_messageActionConferenceCall) r6.messageOwner.action;
        ChatObject.Call r3 = this.groupCall;
        if (r3 == null) goto L26;
        ChatObject.Call.InvitedUser r32 = r3.invitedUsersMessageIds.get(Long.valueOf(r02));
        if (r32 != null) goto L14;
        return;
    L14:
        if (r32.msg_id == r2) goto L16;
        return;
    L16:
        if (r62.missed == false) goto L18;
    L20:
        boolean r63 = false;
    L22:
        if (r32.isCalling() == r63) goto L29;
        r32.calling = r63;
        this.groupCall.invitedUsersMessageIds.put(Long.valueOf(r02), r32);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{0L, Long.valueOf(this.groupCall.call.id), Boolean.FALSE});
        return;
    L29:
        return;
    L18:
        if (r62.active == true) goto L20;
        r63 = true;
        goto L22
    L26:
        return;
    L25:
        return;
    }

    public void registerStateListener(StateListener r2) {
        if (this.stateListeners.contains(r2) == true) goto L13;
        this.stateListeners.add(r2);
        int r0 = this.currentState;
        if (r0 == 0) goto L8;
        r2.onStateChanged(r0);
    L8:
        int r1 = this.signalBarCount;
        if (r1 == 0) goto L12;
        r2.onSignalBarsCountChanged(r1);
        return;
    L12:
        return;
    }

    public void removeRemoteSink(TLRPC.GroupCallParticipant r3, boolean r4) {
        HashMap<String, ProxyVideoSink> r0 = this.remoteSinks;
        if (r4 == false) goto L8;
        ProxyVideoSink r32 = r0.remove(r3.presentationEndpoint);
        if (r32 == null) goto L12;
        this.tgVoip[0].removeIncomingVideoOutput(ProxyVideoSink.m5373$$Nest$fgetnativeInstance(r32));
        return;
    L12:
        return;
    L8:
        ProxyVideoSink r33 = r0.remove(r3.videoEndpoint);
        if (r33 == null) goto L13;
        this.tgVoip[0].removeIncomingVideoOutput(ProxyVideoSink.m5373$$Nest$fgetnativeInstance(r33));
        return;
    }

    public void requestFullScreen(TLRPC.GroupCallParticipant r1, boolean r2, boolean r3) {
        if (r3 == false) goto L4;
        String r12 = r1.presentationEndpoint;
    L5:
        if (r12 != null) goto L7;
        return;
    L7:
        NativeInstance[] r0 = this.tgVoip;
        if (r2 == false) goto L11;
        r0[0].setVideoEndpointQuality(r12, 2);
        return;
    L11:
        r0[0].setVideoEndpointQuality(r12, 1);
        return;
    L4:
        r12 = r1.videoEndpoint;
        goto L5
    }

    public void requestVideoCall(boolean r8) {
        int r1 = 0;
        NativeInstance r0 = this.tgVoip[0];
        if (r0 != null) goto L5;
        return;
    L5:
        if (r8 == true) goto L9;
        long r3 = this.captureDevice[0];
        if (r3 == 0) goto L9;
        r0.setupOutgoingVideoCreated(r3);
        this.destroyCaptureDevice[0] = false;
    L16:
        this.isPrivateScreencast = r8;
        return;
    L9:
        ProxyVideoSink r2 = this.localSink[0];
        if (r8 == false) goto L13;
        r1 = 2;
    L15:
        r0.setupOutgoingVideo(r2, r1);
        goto L16
    L13:
        if (this.isFrontFaceCamera == false) goto L15;
        r1 = 1;
        goto L15
    }

    public void sendCallRating(int r8) {
        TL_phone.PhoneCall r0 = this.privateCall;
        VoIPHelper.sendCallRating(r0.id, r0.access_hash, this.currentAccount, r8);
    }

    public void setAudioOutput(int r8) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("setAudioOutput " + r8);
    L5:
        AudioManager r0 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        VoipAudioManager r1 = VoipAudioManager.get();
        boolean r2 = USE_CONNECTION_SERVICE;
        int r5 = 0;
        if (r2 == false) goto L17;
        CallConnection r6 = this.systemCallConnection;
        if (r6 == null) goto L17;
        if (r8 == 0) goto L15;
        if (r8 == 1) goto L14;
        if (r8 != 2) goto L52;
        r6.setAudioRoute(2);
    L52:
        ArrayList<StateListener> r7 = this.stateListeners;
        int r82 = r7.size();
    L53:
        if (r5 >= r82) goto L55;
        StateListener r02 = r7.get(r5);
        r5 = r5 + 1;
        r02.onAudioSettingsChanged();
        goto L53
    L55:
        return;
    L14:
        r6.setAudioRoute(5);
        goto L52
    L15:
        r6.setAudioRoute(8);
    L17:
        if (this.audioConfigured == false) goto L45;
        if (r2 == true) goto L45;
        if (r8 == 0) goto L38;
        if (r8 == 1) goto L32;
        if (r8 == 2) goto L24;
    L44:
        updateOutputGainControlState();
        goto L52
    L24:
        if (this.bluetoothScoActive == true) goto L30;
        this.needSwitchToBluetoothAfterScoActivates = true;
        r0.startBluetoothSco();     // Catch: Throwable -> L28
    L31:
        this.audioRouteToSet = 2;
    L28:
        th = move-exception;
        FileLog.e(th);
        goto L31
    L30:
        r0.setBluetoothScoOn(true);
        r1.setSpeakerphoneOn(false);
        goto L31
    L32:
        this.needSwitchToBluetoothAfterScoActivates = false;
        if (this.bluetoothScoActive == false) goto L35;
    L36:
        r0.stopBluetoothSco();
        this.bluetoothScoActive = false;
        this.bluetoothScoConnecting = false;
    L37:
        r1.setSpeakerphoneOn(false);
        r0.setBluetoothScoOn(false);
        this.audioRouteToSet = 0;
        goto L44
    L35:
        if (this.bluetoothScoConnecting == false) goto L37;
    L38:
        this.needSwitchToBluetoothAfterScoActivates = false;
        if (this.bluetoothScoActive == false) goto L41;
    L42:
        r0.stopBluetoothSco();
        this.bluetoothScoActive = false;
        this.bluetoothScoConnecting = false;
    L43:
        r0.setBluetoothScoOn(false);
        r1.setSpeakerphoneOn(true);
        this.audioRouteToSet = 1;
        goto L44
    L41:
        if (this.bluetoothScoConnecting == false) goto L43;
    L45:
        if (r8 == 0) goto L51;
        if (r8 == 1) goto L50;
        if (r8 != 2) goto L52;
        this.audioRouteToSet = 2;
        this.speakerphoneStateToSet = false;
        goto L52
    L50:
        this.audioRouteToSet = 0;
        this.speakerphoneStateToSet = false;
        goto L52
    L51:
        this.audioRouteToSet = 1;
        this.speakerphoneStateToSet = true;
        goto L52
    }

    public void setAudioRoute(int r2) {
        if (r2 != 1) goto L6;
        setAudioOutput(0);
        return;
    L6:
        if (r2 != 0) goto L10;
        setAudioOutput(1);
        return;
    L10:
        if (r2 != 2) goto L13;
        setAudioOutput(2);
        return;
    }

    public void setBackgroundSinks(VideoSink r3, VideoSink r4) {
        this.localSink[0].setBackground(r3);
        this.remoteSink[0].setBackground(r4);
    }

    public void setGroupCallHash(String r2) {
        if (this.currentGroupModeStreaming == true) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r2) == false) goto L7;
        return;
    L7:
        if (r2.equals(this.joinHash) == true) goto L13;
        this.joinHash = r2;
        createGroupInstance(0, false, true);
        return;
    }

    public void setGroupCallPeer(TLRPC.InputPeer r3) {
        ChatObject.Call r0 = this.groupCall;
        if (r0 == null) goto L22;
        this.groupCallPeer = r3;
        r0.setSelfPeer(r3);
        TLRPC.ChatFull r32 = MessagesController.getInstance(this.currentAccount).getChatFull(this.groupCall.chatId);
        if (r32 == null) goto L17;
        TLRPC.Peer r02 = this.groupCall.selfPeer;
        r32.groupcall_default_join_as = r02;
        if (r02 == null) goto L13;
        boolean r03 = r32 instanceof TLRPC.TL_chatFull;
        int r1 = r32.flags;
        if (r03 == false) goto L12;
        r32.flags = 32768 | r1;
        goto L17
    L12:
        r32.flags = 67108864 | r1;
        goto L17
    L13:
        boolean r04 = r32 instanceof TLRPC.TL_chatFull;
        int r12 = r32.flags;
        if (r04 == false) goto L16;
        r32.flags = (-32769) & r12;
        goto L17
    L16:
        r32.flags = (-67108865) & r12;
    L17:
        createGroupInstance(0, true, true);
        if (this.videoState[1] != 2) goto L21;
        createGroupInstance(1, true, true);
        return;
    L21:
        return;
    }

    public void setLocalSink(VideoSink r1, boolean r2) {
        if (r2 == false) goto L4;
        return;
    L4:
        this.localSink[0].setTarget(r1);
    }

    public void setMicMute(boolean r10, boolean r11, boolean r12) {
        if (this.micMute != r10) goto L5;
        return;
    L5:
        if (this.micSwitching == true) goto L36;
        this.micMute = r10;
        ChatObject.Call r0 = this.groupCall;
        boolean r1 = true;
        if (r0 == null) goto L19;
        if (r12 == true) goto L17;
        TLRPC.GroupCallParticipant r02 = r0.participants.get(getSelfId());
        if (r02 == null) goto L17;
        if (r02.muted == false) goto L17;
        if (r02.can_self_unmute == true) goto L17;
        r12 = true;
    L17:
        if (r12 == false) goto L19;
        VoIPService r2 = this;
        r2.editCallMember(UserConfig.getInstance(this.currentAccount).getCurrentUser(), Boolean.valueOf(r10), null, null, null, null);
        DispatchQueue r9 = Utilities.globalQueue;
        VoIPService$$ExternalSyntheticLambda15 r122 = new VoIPService$$ExternalSyntheticLambda15(r2);
        r2.updateNotificationRunnable = r122;
        r9.postRunnable(r122);
    L20:
        int r123 = 0;
        if (r2.micMute == true) goto L24;
        if (r11 == false) goto L24;
    L25:
        r2.unmutedByHold = r1;
        NativeInstance r92 = r2.convertingVoip;
        if (r92 == null) goto L28;
        r92.setMuteMicrophone(r10);
    L31:
        ArrayList<StateListener> r93 = r2.stateListeners;
        int r102 = r93.size();
    L32:
        if (r123 >= r102) goto L37;
        StateListener r112 = r93.get(r123);
        r123 = r123 + 1;
        r112.onAudioSettingsChanged();
        goto L32
    L37:
        return;
    L28:
        NativeInstance r94 = r2.tgVoip[0];
        if (r94 == null) goto L31;
        r94.setMuteMicrophone(r10);
    L24:
        r1 = false;
    L19:
        r2 = this;
        goto L20
    }

    public void setNoiseSupressionEnabled(boolean r2) {
        NativeInstance r1 = this.tgVoip[0];
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setNoiseSuppressionEnabled(r2);
    }

    public void setParticipantVolume(TLRPC.GroupCallParticipant r8, int r9) {
        double r3 = r9 / 10000.0d;
        this.tgVoip[0].setVolume(r8.source, r3);
        TLRPC.TL_groupCallParticipantVideo r82 = r8.presentation;
        if (r82 == null) goto L8;
        int r83 = r82.audio_source;
        if (r83 == 0) goto L9;
        this.tgVoip[0].setVolume(r83, r3);
        return;
    L9:
        return;
    }

    public void setParticipantsVolume() {
        if (this.tgVoip[0] == null) goto L20;
        int r0 = this.groupCall.participants.size();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L27;
        TLRPC.GroupCallParticipant r3 = this.groupCall.participants.valueAt(r2);
        if (r3.self == true) goto L19;
        if (r3.source == 0) goto L19;
        if (r3.can_self_unmute == true) goto L16;
        if (r3.muted == true) goto L19;
    L16:
        if (r3.muted_by_you == false) goto L18;
        setParticipantVolume(r3, 0);
        goto L19
    L18:
        setParticipantVolume(r3, ChatObject.getParticipantVolume(r3));
    L19:
        r2 = r2 + 1;
        goto L5
    L27:
        return;
    }

    public void setRemoteSink(VideoSink r1, boolean r2) {
        this.remoteSink[r2 ? 1 : 0].setTarget(r1);
    }

    public void setSinks(VideoSink r2, VideoSink r3) {
        setSinks(r2, false, r3);
    }

    public void setSinks(VideoSink r2, boolean r3, VideoSink r4) {
        ProxyVideoSink r0 = this.localSink[r3 ? 1 : 0];
        ProxyVideoSink r1 = this.remoteSink[r3 ? 1 : 0];
        if (r0 == null) goto L5;
        r0.setTarget(r2);
    L5:
        if (r1 == null) goto L8;
        r1.setTarget(r4);
        return;
    }

    public void setSwitchingCamera(boolean r2, boolean r3) {
        this.switchingCamera = r2;
        if (r2 == true) goto L8;
        this.isFrontFaceCamera = r3;
        int r22 = 0;
    L6:
        if (r22 >= this.stateListeners.size()) goto L10;
        this.stateListeners.get(r22).onCameraSwitch(this.isFrontFaceCamera);
        r22 = r22 + 1;
        goto L6
    L10:
        return;
    }

    public void setVideoState(boolean r12, int r13) {
        boolean r3 = false;
        int r32 = 0;
        if (this.groupCall == null) goto L5;
        char r2 = r12 ? 1 : 0;
    L6:
        NativeInstance r4 = this.tgVoip[r2];
        if (r4 != null) goto L25;
        long[] r42 = this.captureDevice;
        long r9 = r42[r12 ? 1 : 0];
        if (r9 == 0) goto L12;
        this.videoState[r2] = r13;
        NativeInstance.setVideoStateCapturer(r9, r13);
        return;
    L12:
        if (r13 != 2) goto L37;
        int r1 = this.currentState;
        if (r1 != 17) goto L16;
        return;
    L16:
        if (r1 == 11) goto L39;
        ProxyVideoSink r14 = this.localSink[r2];
        if (r12 == false) goto L21;
        r32 = 2;
    L23:
        r42[r12 ? 1 : 0] = NativeInstance.createVideoCapturer(r14, r32);
        this.videoState[r2] = 2;
        return;
    L21:
        if (this.isFrontFaceCamera == false) goto L23;
        r32 = 1;
        goto L23
    L39:
        return;
    L37:
        return;
    L25:
        this.videoState[r2] = r13;
        r4.setVideoState(r13);
        long r92 = this.captureDevice[r12 ? 1 : 0];
        if (r92 == 0) goto L28;
        NativeInstance.setVideoStateCapturer(r92, this.videoState[r2]);
    L28:
        if (r12 == false) goto L30;
        return;
    L30:
        if (this.groupCall == null) goto L35;
        TLRPC.User r15 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (this.videoState[0] == 2) goto L34;
        r3 = true;
    L34:
        editCallMember(r15, null, Boolean.valueOf(r3), null, null, null);
    L35:
        checkIsNear();
        return;
    L5:
        r2 = 0;
        goto L6
    }

    public void setupCaptureDevice(boolean r11, boolean r12) {
        boolean r1 = false;
        if (r11 == true) goto L11;
        long r3 = this.captureDevice[r11 ? 1 : 0];
        if (r3 == 0) goto L20;
        NativeInstance r2 = this.tgVoip[r11 ? 1 : 0];
        if (r2 == null) goto L22;
        r2.setupOutgoingVideoCreated(r3);
        this.destroyCaptureDevice[r11 ? 1 : 0] = false;
        this.videoState[r11 ? 1 : 0] = 2;
        goto L11
    L22:
        return;
    L20:
        return;
    L11:
        if (this.micMute != r12) goto L14;
        setMicMute(!r12, false, false);
        this.micSwitching = true;
    L14:
        if (this.groupCall == null) goto L21;
        TLRPC.User r4 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        Boolean r5 = Boolean.valueOf(!r12);
        if (this.videoState[0] == 2) goto L18;
        r1 = true;
    L18:
        editCallMember(r4, r5, Boolean.valueOf(r1), null, null, new VoIPService$$ExternalSyntheticLambda33(this));
        return;
    }

    public void startRingtoneAndVibration() {
        if (this.startedRinging == true) goto L6;
        startRingtoneAndVibration(this.user.id);
        this.startedRinging = true;
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPServiceState
    public void stopRinging() {
        Object r0 = sync;
        monitor-enter(r0);
        MediaPlayer r1 = this.ringtonePlayer;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.stop();     // Catch: Throwable -> L7
        this.ringtonePlayer.release();     // Catch: Throwable -> L7
        this.ringtonePlayer = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        Vibrator r02 = this.vibrator;
        if (r02 == null) goto L18;
        r02.cancel();
        this.vibrator = null;
        return;
    L18:
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void stopScreenCapture() {
        if (this.groupCall != null) goto L5;
        return;
    L5:
        if (this.videoState[1] != 2) goto L13;
        TL_phone.leaveGroupCallPresentation r0 = new TL_phone.leaveGroupCallPresentation();
        r0.call = this.groupCall.getInputGroupCall();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new VoIPService$$ExternalSyntheticLambda83(this));
        NativeInstance r02 = this.tgVoip[1];
        if (r02 == null) goto L10;
        Utilities.globalQueue.postRunnable(new VoIPService$$ExternalSyntheticLambda72(r02));
    L10:
        this.mySource[1] = 0;
        this.tgVoip[1] = null;
        this.destroyCaptureDevice[1] = true;
        this.captureDevice[1] = 0;
        this.videoState[1] = 0;
        AccountInstance.getInstance(this.currentAccount).getNotificationCenter().postNotificationName(NotificationCenter.groupCallScreencastStateChanged, new Object[0]);
        return;
    }

    public void swapSinks() {
        this.localSink[0].swap();
        this.remoteSink[0].swap();
    }

    public void switchCamera() {
        NativeInstance r0 = this.tgVoip[0];
        if (r0 != null) goto L5;
    L11:
        long r3 = this.captureDevice[0];
        if (r3 != 0) goto L14;
        return;
    L14:
        if (this.switchingCamera == true) goto L18;
        NativeInstance.switchCameraCapturer(r3, !this.isFrontFaceCamera);
        return;
    L18:
        return;
    L5:
        if (r0.hasVideoCapturer() == false) goto L11;
        if (this.switchingCamera == true) goto L11;
        this.switchingCamera = true;
        this.tgVoip[0].switchCamera(!this.isFrontFaceCamera);
    }

    public void switchToSpeaker() {
        AndroidUtilities.runOnUIThread(new VoIPService$$ExternalSyntheticLambda18(this), 500);
    }

    public void toggleSpeakerphoneOrShowRouteSheet(Context r2, boolean r3) {
        toggleSpeakerphoneOrShowRouteSheet(r2, r3, null);
    }

    public void toggleSpeakerphoneOrShowRouteSheet(Context r8, boolean r9, Integer r10) {
        int r1 = 2;
        int r2 = 0;
        if (isBluetoothHeadsetConnected() == true) goto L5;
    L31:
        boolean r82 = USE_CONNECTION_SERVICE;
        if (r82 == false) goto L49;
        CallConnection r92 = this.systemCallConnection;
        if (r92 == null) goto L49;
        if (r92.getCallAudioState() == null) goto L49;
        boolean r83 = hasEarpiece();
        CallConnection r93 = this.systemCallConnection;
        int r102 = 5;
        if (r83 == false) goto L45;
        if (r93.getCallAudioState().getRoute() == 8) goto L43;
        r102 = 8;
    L43:
        r93.setAudioRoute(r102);
    L58:
        ArrayList<StateListener> r7 = this.stateListeners;
        int r84 = r7.size();
    L59:
        if (r2 >= r84) goto L61;
        StateListener r94 = r7.get(r2);
        r2 = r2 + 1;
        r94.onAudioSettingsChanged();
        goto L59
    L61:
        return;
    L45:
        if (r93.getCallAudioState().getRoute() != 2) goto L47;
        r1 = 5;
    L47:
        r93.setAudioRoute(r1);
    L49:
        if (this.audioConfigured == false) goto L57;
        if (r82 == true) goto L57;
        AudioManager r85 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        VoipAudioManager r95 = VoipAudioManager.get();
        if (hasEarpiece() == false) goto L54;
        r95.setSpeakerphoneOn(!r95.isSpeakerphoneOn());
    L55:
        r95.isBluetoothAndSpeakerOnAsync(new VoIPService$$ExternalSyntheticLambda93(this));
        return;
    L54:
        r85.setBluetoothScoOn(!r85.isBluetoothScoOn());
    L57:
        this.speakerphoneStateToSet = !this.speakerphoneStateToSet;
        goto L58
    L5:
        if (hasEarpiece() == false) goto L31;
        BottomSheet.Builder r86 = new BottomSheet.Builder(r8).setTitle(LocaleController.getString(R.string.VoipOutputDevices), true).selectedPos(r10);
        if (r10 == null) goto L9;
        int r0 = BottomSheet.Builder.CELL_TYPE_CALL;
    L10:
        BottomSheet.Builder r87 = r86.setCellType(r0);
        String r02 = LocaleController.getString(R.string.VoipAudioRoutingSpeaker);
        if (this.isHeadsetPlugged == false) goto L14;
        int r4 = R.string.VoipAudioRoutingHeadset;
    L13:
        String r42 = LocaleController.getString(r4);
        String r5 = this.currentBluetoothDeviceName;
        if (r5 != null) goto L19;
        r5 = LocaleController.getString(R.string.VoipAudioRoutingBluetooth);
    L19:
        CharSequence[] r6 = {r02, r42, r5};
        int r03 = R.drawable.msg_call_speaker;
        if (this.isHeadsetPlugged == false) goto L22;
        int r12 = R.drawable.calls_menu_headset;
    L23:
        BottomSheet.Builder r72 = r87.setItems(r6, new int[]{r03, r12, R.drawable.msg_call_bluetooth}, new VoIPService$$ExternalSyntheticLambda91(this));
        BottomSheet r88 = r72.create();
        r88.setOnShowListener(new VoIPService$$ExternalSyntheticLambda92(r88, r10));
        if (r9 == true) goto L26;
    L29:
        r72.show();
        return;
    L26:
        if (Build.VERSION.SDK_INT < 26) goto L28;
        r88.getWindow().setType(2038);
        goto L29
    L28:
        r88.getWindow().setType(CastStatusCodes.NOT_ALLOWED);
        goto L29
    L22:
        r12 = R.drawable.msg_call_earpiece;
        goto L23
    L14:
        r4 = R.string.VoipAudioRoutingEarpiece;
        goto L13
    L9:
        r0 = 0;
        goto L10
    }

    public void unregisterStateListener(StateListener r1) {
        this.stateListeners.remove(r1);
    }

    public void updateCurrentForegroundType() {
        if (this.lastForegroundType != getCurrentForegroundType()) goto L5;
        return;
    L5:
        if (this.foregroundStarted == false) goto L13;
        stopForeground(true);
        int r0 = Build.VERSION.SDK_INT;
        int r1 = this.foregroundId;
        if (r0 < 33) goto L10;
        Notification r02 = this.foregroundNotification;
        int r2 = getCurrentForegroundType();
        this.lastForegroundType = r2;
        startForeground(r1, r02, r2);
        return;
    L10:
        startForeground(r1, this.foregroundNotification);
        return;
    }

    public void updateOutputGainControlState() {
        hasRtmpStream();
    }
}
