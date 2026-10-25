package org.telegram.messenger;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.view.TextureView;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.jpeg.MotionPhotoDescription;
import androidx.media3.extractor.jpeg.XmpMotionPhotoDescriptionParser;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.SystemUtils;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.common.images.WebImage;
import j$.io.DesugarFile;
import j$.io.FileRetargetInterface;
import j$.net.URLEncoder;
import j$.nio.file.Path;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.chromecast.ChromecastController;
import org.telegram.messenger.chromecast.ChromecastFileServer;
import org.telegram.messenger.chromecast.ChromecastMedia;
import org.telegram.messenger.chromecast.ChromecastMediaVariations;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.AspectRatioFrameLayout;
import org.telegram.ui.CastSync;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.EmbedBottomSheet;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.Components.PhotoFilterView;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes.dex */
public class MediaController implements AudioManager.OnAudioFocusChangeListener, NotificationCenter.NotificationCenterDelegate, SensorEventListener {
    private static final int AUDIO_FOCUSED = 2;
    public static final String AUDIO_MIME_TYPE = "audio/mp4a-latm";
    private static final int AUDIO_NO_FOCUS_CAN_DUCK = 1;
    private static final int AUDIO_NO_FOCUS_NO_DUCK = 0;
    private static volatile MediaController Instance = null;
    public static final int VIDEO_BITRATE_1080 = 14000000;
    public static final int VIDEO_BITRATE_360 = 3000000;
    public static final int VIDEO_BITRATE_480 = 5000000;
    public static final int VIDEO_BITRATE_720 = 9000000;
    public static final String VIDEO_MIME_TYPE = "video/avc";
    private static final float VOLUME_DUCK = 0.2f;
    private static final float VOLUME_NORMAL = 1.0f;
    public static AlbumEntry allMediaAlbumEntry;
    public static ArrayList<AlbumEntry> allMediaAlbums;
    public static ArrayList<AlbumEntry> allPhotoAlbums;
    public static AlbumEntry allPhotosAlbumEntry;
    public static AlbumEntry allVideosAlbumEntry;
    private static Runnable broadcastPhotosRunnable;
    private static final ConcurrentHashMap<String, Integer> cachedEncoderBitrates = null;
    public static boolean forceBroadcastNewPhotos;
    private static final String[] projectionPhotos = null;
    private static final String[] projectionVideo = null;
    private static Runnable refreshGalleryRunnable;
    private static long volumeBarLastTimeShown;
    private Sensor accelerometerSensor;
    private boolean accelerometerVertical;
    private boolean allowStartRecord;
    private int audioFocus;
    private AudioInfo audioInfo;
    private VideoPlayer audioPlayer;
    AudioManager.OnAudioFocusChangeListener audioRecordFocusChangedListener;
    private AudioRecord audioRecorder;
    private boolean audioRecorderPaused;
    private float audioVolume;
    private ValueAnimator audioVolumeAnimator;
    private final ValueAnimator.AnimatorUpdateListener audioVolumeUpdateListener;
    private Activity baseActivity;
    private boolean callInProgress;
    private int countLess;
    private AspectRatioFrameLayout currentAspectRatioFrameLayout;
    private float currentAspectRatioFrameLayoutRatio;
    private boolean currentAspectRatioFrameLayoutReady;
    private int currentAspectRatioFrameLayoutRotation;
    private VideoConvertMessage currentForegroundConvertingVideo;
    private float currentMusicPlaybackSpeed;
    private float currentPlaybackSpeed;
    private int currentPlaylistNum;
    public MessagesController.SavedMusicList currentSavedMusicList;
    private TextureView currentTextureView;
    private FrameLayout currentTextureViewContainer;
    private boolean downloadingCurrentMessage;
    private VideoPlayer emojiSoundPlayer;
    private int emojiSoundPlayerNum;
    private ExternalObserver externalObserver;
    private float fastMusicPlaybackSpeed;
    private float fastPlaybackSpeed;
    private View feedbackView;
    private ByteBuffer fileBuffer;
    private DispatchQueue fileEncodingQueue;
    private BaseFragment flagSecureFragment;
    private boolean forceLoopCurrentPlaylist;
    private ArrayList<VideoConvertMessage> foregroundConvertingMessages;
    private HashMap<String, MessageObject> generatingWaveform;
    private MessageObject goingToShowMessageObject;
    private float[] gravity;
    private float[] gravityFast;
    private Sensor gravitySensor;
    private int hasAudioFocus;
    private boolean hasRecordAudioFocus;
    private boolean ignoreOnPause;
    private boolean ignorePlayerUpdate;
    private boolean ignoreProximity;
    private boolean inputFieldHasText;
    private InternalObserver internalObserver;
    private boolean isDrawingWasReady;
    private boolean isPaused;
    public boolean isSilent;
    private boolean isStreamingCurrentAudio;
    private long lastAccelerometerDetected;
    private int lastChatAccount;
    private long lastChatEnterTime;
    private long lastChatLeaveTime;
    private ArrayList<Long> lastChatVisibleMessages;
    private long lastMediaCheckTime;
    private int lastMessageId;
    private long lastProgress;
    private float lastProximityValue;
    private long lastSaveTime;
    private TLRPC.EncryptedChat lastSecretChat;
    private long lastTimestamp;
    private TLRPC.User lastUser;
    private float[] linearAcceleration;
    private Sensor linearSensor;
    private boolean loadingPlaylist;
    private boolean manualRecording;
    private String[] mediaProjections;
    private PipRoundVideoView pipRoundVideoView;
    private int pipSwitchingState;
    private boolean playMusicAgain;
    private int playerNum;
    private boolean playerWasReady;
    private MessageObject playingMessageObject;
    private ArrayList<MessageObject> playlist;
    private int playlistClassGuid;
    private boolean[] playlistEndReached;
    private PlaylistGlobalSearchParams playlistGlobalSearchParams;
    private HashMap<Integer, MessageObject> playlistMap;
    private int[] playlistMaxId;
    private long playlistMergeDialogId;
    private float previousAccValue;
    private java.util.Timer progressTimer;
    private final Object progressTimerSync;
    private boolean proximityHasDifferentValues;
    private Sensor proximitySensor;
    private boolean proximityTouched;
    private PowerManager.WakeLock proximityWakeLock;
    private ChatActivity raiseChat;
    private boolean raiseToEarRecord;
    private int raisedToBack;
    private int raisedToTop;
    private int raisedToTopSign;
    public int recordBufferSize;
    private ArrayList<ByteBuffer> recordBuffers;
    private long recordDialogId;
    private long recordMonoForumPeerId;
    private MessageSuggestionParams recordMonoForumSuggestionParams;
    private DispatchQueue recordQueue;
    private MessageObject recordReplyingMsg;
    private TL_stories.StoryItem recordReplyingStory;
    private MessageObject recordReplyingTopMsg;
    private Runnable recordRunnable;
    public short[] recordSamples;
    private SendMessageChatArguments recordSendMessageChatArguments;
    private Runnable recordStartRunnable;
    private long recordStartTime;
    public long recordTimeCount;
    private long recordTopicId;
    public TLRPC.TL_document recordingAudio;
    private File recordingAudioFile;
    private int recordingCurrentAccount;
    private int recordingGuid;
    private File recordingPrevAudioFile;
    private MusicListenReporter reporter;
    private boolean resumeAudioOnFocusGain;
    public int sampleRate;
    public long samplesCount;
    private SavedMusicPlaylistState savedMusicPlaylistState;
    private float seekToProgressPending;
    private int sendAfterDone;
    private boolean sendAfterDoneNotify;
    private boolean sendAfterDoneOnce;
    private long sendAfterDonePayStars;
    private int sendAfterDoneScheduleDate;
    private SensorManager sensorManager;
    private boolean sensorsStarted;
    private Runnable setLoadingRunnable;
    private String shouldSavePositionForCurrentAudio;
    private ArrayList<MessageObject> shuffledPlaylist;
    private int startObserverToken;
    private StopMediaObserverRunnable stopMediaObserverRunnable;
    private final Object sync;
    private long timeSinceRaise;
    private boolean useFrontSpeaker;
    private ArrayList<VideoConvertMessage> videoConvertQueue;
    private final Object videoConvertSync;
    private VideoPlayer videoPlayer;
    private final Object videoQueueSync;
    private ArrayList<MessageObject> voiceMessagesPlaylist;
    private SparseArray<MessageObject> voiceMessagesPlaylistMap;
    private boolean voiceMessagesPlaylistUnread;
    private boolean wasPlayingAudioBeforePause;
    public int writtenFrame;

    /* renamed from: org.telegram.messenger.MediaController$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass1(MediaController r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MediaController.m4309$$Nest$fgetplayingMessageObject(this.this$0) != null) goto L5;
            return;
        L5:
            FileLoader.getInstance(MediaController.m4309$$Nest$fgetplayingMessageObject(this.this$0).currentAccount).setLoadingVideo(MediaController.m4309$$Nest$fgetplayingMessageObject(this.this$0).getDocument(), true, false);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$10, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass10 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ MediaController this$0;
        final /* synthetic */ MessageObject val$messageObject;
        final /* synthetic */ int val$tag;

        public AnonymousClass10(MediaController r1, int r2, MessageObject r3) {
            this.this$0 = r1;
            this.val$tag = r2;
            this.val$messageObject = r3;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onAudioTrackChanged(int r1) {
            super.onAudioTrackChanged(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onCues(CueGroup r1) {
            super.onCues(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime r1) {
            super.onRenderedFirstFrame(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekFinished(AnalyticsListener.EventTime r1) {
            super.onSeekFinished(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime r1) {
            super.onSeekStarted(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r5, int r6) {
            if (this.val$tag == MediaController.m4308$$Nest$fgetplayerNum(this.this$0)) goto L6;
            return;
        L6:
            if (r6 == 4) goto L22;
            if (r6 != 1) goto L9;
        L10:
            if (r5 == true) goto L12;
        L15:
            if (MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0) == null) goto L33;
            if (MediaController.m4317$$Nest$fgetseekToProgressPending(this.this$0) == 0.0f) goto L33;
            if (r6 == 3) goto L21;
            if (r6 != 1) goto L33;
        L21:
            long r2 = (int) (MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0).getDuration() * MediaController.m4317$$Nest$fgetseekToProgressPending(this.this$0));
            MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0).seekTo(r2);
            MediaController.m4335$$Nest$fputlastProgress(this.this$0, r2);
            MediaController.m4339$$Nest$fputseekToProgressPending(this.this$0, 0.0f);
        L33:
            if (MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0) != null) goto L35;
            return;
        L35:
            if (CastSync.isActive() == false) goto L39;
            MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0).setMute(true);
            return;
        L39:
            return;
        L12:
            if (this.val$messageObject.audioProgress < 0.999f) goto L15;
        L9:
            if (r6 != 2) goto L15;
        L22:
            MessageObject r52 = this.val$messageObject;
            r52.audioProgress = 1.0f;
            NotificationCenter.getInstance(r52.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.val$messageObject.getId()), 0});
            if (MediaController.m4345$$Nest$mrestoreMusicPlaylistState(this.this$0) == true) goto L33;
            if (MediaController.m4310$$Nest$fgetplaylist(this.this$0).isEmpty() == false) goto L27;
        L31:
            MediaController r53 = this.this$0;
            r53.cleanupPlayer(true, r53.hasNoNextVoiceOrRoundVideoMessage(), this.val$messageObject.isVoice(), false);
            goto L33
        L27:
            if (MediaController.m4310$$Nest$fgetplaylist(this.this$0).size() <= 1) goto L29;
        L30:
            MediaController.m4343$$Nest$mplayNextMessageWithoutOrder(this.this$0, true);
            goto L33
        L29:
            if (this.val$messageObject.isVoice() == true) goto L31;
            goto L30
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ boolean onSurfaceDestroyed(SurfaceTexture r1) {
            return super.onSurfaceDestroyed(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSurfaceTextureUpdated(SurfaceTexture r1) {
            super.onSurfaceTextureUpdated(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$11, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass11 implements VideoPlayer.AudioVisualizerDelegate {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass11(MediaController r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.AudioVisualizerDelegate
        public boolean needUpdate() {
            if (Theme.getCurrentAudiVisualizerDrawable().getParentView() == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.AudioVisualizerDelegate
        public void onVisualizerUpdate(boolean r1, boolean r2, float[] r3) {
            Theme.getCurrentAudiVisualizerDrawable().setWaveform(r1, r2, r3);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$12, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass12(MediaController r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0) == null) goto L6;
            MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0).pause();
            return;
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$13, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass13 extends File implements FileRetargetInterface {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass13(MediaController r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // java.io.File
        public boolean delete() {
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.e("delete voice file");
        L6:
            return super.delete();
        }

        @Override // java.io.File, j$.io.FileRetargetInterface
        public /* synthetic */ Path toPath() {
            return DesugarFile.toPath(this);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$14, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass14 extends File implements FileRetargetInterface {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass14(MediaController r1, File r2, String r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // java.io.File
        public boolean delete() {
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.e("delete voice file (joined)");
        L6:
            return super.delete();
        }

        @Override // java.io.File, j$.io.FileRetargetInterface
        public /* synthetic */ Path toPath() {
            return DesugarFile.toPath(this);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$15, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass15 extends File implements FileRetargetInterface {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass15(MediaController r1, File r2, String r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // java.io.File
        public boolean delete() {
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.e("delete voice file (trimmed)");
        L6:
            return super.delete();
        }

        @Override // java.io.File, j$.io.FileRetargetInterface
        public /* synthetic */ Path toPath() {
            return DesugarFile.toPath(this);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$16, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass16 extends File implements FileRetargetInterface {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass16(MediaController r1, File r2, String r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // java.io.File
        public boolean delete() {
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.e("delete voice file (from resume)");
        L6:
            return super.delete();
        }

        @Override // java.io.File, j$.io.FileRetargetInterface
        public /* synthetic */ Path toPath() {
            return DesugarFile.toPath(this);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$17, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass17 extends File implements FileRetargetInterface {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass17(MediaController r1, File r2, String r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // java.io.File
        public boolean delete() {
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.e("delete voice file");
        L6:
            return super.delete();
        }

        @Override // java.io.File, j$.io.FileRetargetInterface
        public /* synthetic */ Path toPath() {
            return DesugarFile.toPath(this);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$18, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass18 implements VideoConvertorListener {
        private long lastAvailableSize;
        final /* synthetic */ MediaController this$0;
        final /* synthetic */ File val$cacheFile;
        final /* synthetic */ VideoConvertMessage val$convertMessage;
        final /* synthetic */ VideoEditedInfo val$info;

        public AnonymousClass18(MediaController r1, VideoEditedInfo r2, File r3, VideoConvertMessage r4) {
            this.this$0 = r1;
            this.val$info = r2;
            this.val$cacheFile = r3;
            this.val$convertMessage = r4;
            this.lastAvailableSize = 0;
        }

        @Override // org.telegram.messenger.MediaController.VideoConvertorListener
        public boolean checkConversionCanceled() {
            return this.val$info.canceled;
        }

        @Override // org.telegram.messenger.MediaController.VideoConvertorListener
        public void didWriteData(long r11, float r13) {
            if (this.val$info.canceled == false) goto L6;
            return;
        L6:
            if (r11 >= 0) goto L8;
            r11 = this.val$cacheFile.length();
        L8:
            long r6 = r11;
            if (this.val$info.needUpdateProgress == false) goto L11;
        L13:
            this.lastAvailableSize = r6;
            MediaController.m4342$$Nest$mdidWriteData(this.this$0, this.val$convertMessage, this.val$cacheFile, false, 0, r6, false, r13);
            return;
        L11:
            if (this.lastAvailableSize != r6) goto L13;
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ MediaController this$0;

        /* renamed from: $r8$lambda$-iNmJZtqFkBAbMYfBMt5K30en7Q, reason: not valid java name */
        public static /* synthetic */ void m4354$r8$lambda$iNmJZtqFkBAbMYfBMt5K30en7Q(AnonymousClass2 r0, ByteBuffer r1) {
            r0.lambda$run$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$O9h0pEoQ881xa3hPWXcD0Ky46aA(AnonymousClass2 r0, ByteBuffer r1, boolean r2) {
            r0.lambda$run$1(r1, r2);
        }

        /* renamed from: $r8$lambda$mJta6F-lE-rFT7oTDoklwVaMGlE, reason: not valid java name */
        public static /* synthetic */ void m4355$r8$lambda$mJta6FlErFT7oTDoklwVaMGlE(AnonymousClass2 r0, double r1) {
            r0.lambda$run$2(r1);
        }

        public AnonymousClass2(MediaController r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0(ByteBuffer r1) {
            MediaController.m4311$$Nest$fgetrecordBuffers(this.this$0).add(r1);
        }

        private /* synthetic */ void lambda$run$1(ByteBuffer r10, boolean r11) {
        L3:
            if (r10.hasRemaining() == false) goto L21;
            if (r10.remaining() <= MediaController.m4299$$Nest$fgetfileBuffer(this.this$0).remaining()) goto L7;
            int r0 = r10.limit();
            r10.limit(MediaController.m4299$$Nest$fgetfileBuffer(this.this$0).remaining() + r10.position());
        L8:
            MediaController.m4299$$Nest$fgetfileBuffer(this.this$0).put(r10);
            if (MediaController.m4299$$Nest$fgetfileBuffer(this.this$0).position() == MediaController.m4299$$Nest$fgetfileBuffer(this.this$0).limit()) goto L11;
            if (r11 == true) goto L11;
        L19:
            if (r0 == (-1)) goto L3;
            r10.limit(r0);
        L11:
            MediaController r1 = this.this$0;
            ByteBuffer r3 = MediaController.m4299$$Nest$fgetfileBuffer(r1);
            if (r11 == true) goto L14;
            int r4 = MediaController.m4299$$Nest$fgetfileBuffer(this.this$0).limit();
        L16:
            if (MediaController.m4349$$Nest$mwriteFrame(r1, r3, r4) == 0) goto L18;
            MediaController.m4299$$Nest$fgetfileBuffer(this.this$0).rewind();
            MediaController r12 = this.this$0;
            long r32 = r12.recordTimeCount;
            int r5 = MediaController.m4299$$Nest$fgetfileBuffer(r12).limit() / 2;
            MediaController r6 = this.this$0;
            r12.recordTimeCount = r32 + (r5 / (r6.sampleRate / 1000));
            r6.writtenFrame++;
            goto L19
        L18:
            FileLog.e("writing frame failed");
            goto L19
        L14:
            r4 = r10.position();
            goto L16
        L7:
            r0 = -1;
            goto L8
        L21:
            MediaController.m4312$$Nest$fgetrecordQueue(this.this$0).postRunnable(new MediaController$2$$ExternalSyntheticLambda2(this, r10));
        }

        private /* synthetic */ void lambda$run$2(double r3) {
            NotificationCenter.getInstance(MediaController.m4315$$Nest$fgetrecordingCurrentAccount(this.this$0)).postNotificationName(NotificationCenter.recordProgressChanged, new Object[]{Integer.valueOf(MediaController.m4316$$Nest$fgetrecordingGuid(this.this$0)), Double.valueOf(r3)});
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MediaController.m4289$$Nest$fgetaudioRecorder(this.this$0) == null) goto L49;
            boolean r0 = MediaController.m4311$$Nest$fgetrecordBuffers(this.this$0).isEmpty();
            MediaController r1 = this.this$0;
            boolean r2 = false;
            if (r0 == true) goto L8;
            ByteBuffer r02 = (ByteBuffer) MediaController.m4311$$Nest$fgetrecordBuffers(r1).get(0);
            MediaController.m4311$$Nest$fgetrecordBuffers(this.this$0).remove(0);
        L7:
            ByteBuffer r12 = r02;
            r12.rewind();
            int r3 = MediaController.m4289$$Nest$fgetaudioRecorder(this.this$0).read(r12, r12.capacity());
            if (r3 <= 0) goto L36;
            r12.limit(r3);
            double r4 = 0.0d;
            MediaController r03 = this.this$0;     // Catch: Exception -> L17
            long r6 = r03.samplesCount;     // Catch: Exception -> L17
            long r8 = (r3 / 2) + r6;     // Catch: Exception -> L17
            int r62 = (int) ((r6 / r8) * r0.length);     // Catch: Exception -> L17
            int r7 = r03.recordSamples.length - r62;     // Catch: Exception -> L17
            float r10 = 0.0f;
            if (r62 == 0) goto L19;
            float r04 = r0.length / r62;     // Catch: Exception -> L17
            int r11 = 0;
            float r122 = 0.0f;
        L15:
            if (r11 >= r62) goto L19;
            short[] r13 = this.this$0.recordSamples;     // Catch: Exception -> L17
            r13[r11] = r13[(int) r122];     // Catch: Exception -> L17
            r122 = r122 + r04;     // Catch: Exception -> L17
            r11 = r11 + 1;     // Catch: Exception -> L17
        L19:
            float r05 = (r3 / 2.0f) / r7;     // Catch: Exception -> L17
            int r72 = 0;
        L21:
            if (r72 >= (r3 / 2)) goto L28;
            short r112 = r12.getShort();     // Catch: Exception -> L17
            r4 = r4 + (r112 * r112);     // Catch: Exception -> L17
            if (r72 != ((int) r10)) goto L27;
            short[] r123 = this.this$0.recordSamples;     // Catch: Exception -> L17
            if (r62 >= r123.length) goto L27;
            r123[r62] = r112;     // Catch: Exception -> L17
            r10 = r10 + r05;     // Catch: Exception -> L17
            r62 = r62 + 1;     // Catch: Exception -> L17
        L27:
            r72 = r72 + 1;     // Catch: Exception -> L17
            goto L21
        L28:
            this.this$0.samplesCount = r8;     // Catch: Exception -> L17
        L31:
            r12.position(0);
            double r42 = Math.sqrt((r4 / r3) / 2.0d);
            if (r3 == r12.capacity()) goto L34;
            r2 = true;
        L34:
            MediaController.m4300$$Nest$fgetfileEncodingQueue(this.this$0).postRunnable(new MediaController$2$$ExternalSyntheticLambda0(this, r12, r2));
            MediaController.m4312$$Nest$fgetrecordQueue(this.this$0).postRunnable(MediaController.m4313$$Nest$fgetrecordRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(new MediaController$2$$ExternalSyntheticLambda1(this, r42));
            return;
        L17:
            e = move-exception;
            FileLog.e(e);
            goto L31
        L36:
            MediaController.m4311$$Nest$fgetrecordBuffers(this.this$0).add(r12);
            if (MediaController.m4318$$Nest$fgetsendAfterDone(this.this$0) != 3) goto L39;
            return;
        L39:
            if (MediaController.m4318$$Nest$fgetsendAfterDone(this.this$0) == 4) goto L51;
            MediaController r22 = this.this$0;
            MediaController.m4347$$Nest$mstopRecordingInternal(r22, MediaController.m4318$$Nest$fgetsendAfterDone(r22), MediaController.m4319$$Nest$fgetsendAfterDoneNotify(this.this$0), MediaController.m4322$$Nest$fgetsendAfterDoneScheduleDate(this.this$0), MediaController.m4320$$Nest$fgetsendAfterDoneOnce(this.this$0), MediaController.m4321$$Nest$fgetsendAfterDonePayStars(this.this$0));
            return;
        L51:
            return;
        L8:
            r02 = ByteBuffer.allocateDirect(r1.recordBufferSize);
            r02.order(ByteOrder.nativeOrder());
            goto L7
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$3, reason: invalid class name */
    public class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ MediaController this$0;

        public AnonymousClass3(MediaController r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            MediaController.m4327$$Nest$fputaudioVolume(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            MediaController.m4346$$Nest$msetPlayerVolume(this.this$0);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$4, reason: invalid class name */
    public class AnonymousClass4 extends PhoneStateListener {
        final /* synthetic */ MediaController this$0;

        public static /* synthetic */ void $r8$lambda$HJGfcVlEIVkV70W83OFGLK9mVJk(AnonymousClass4 r0, int r1) {
            r0.lambda$onCallStateChanged$0(r1);
        }

        public AnonymousClass4(MediaController r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onCallStateChanged$0(int r9) {
            if (r9 != 1) goto L19;
            MediaController r92 = this.this$0;
            if (r92.isPlayingMessage(MediaController.m4309$$Nest$fgetplayingMessageObject(r92)) == false) goto L10;
            if (this.this$0.isMessagePaused() == true) goto L10;
            MediaController r93 = this.this$0;
            r93.pauseMessage(MediaController.m4309$$Nest$fgetplayingMessageObject(r93));
        L14:
            EmbedBottomSheet r94 = EmbedBottomSheet.getInstance();
            if (r94 == null) goto L17;
            r94.pause();
        L17:
            MediaController.m4328$$Nest$fputcallInProgress(this.this$0, true);
            return;
        L10:
            if (MediaController.m4314$$Nest$fgetrecordStartRunnable(this.this$0) == null) goto L12;
        L13:
            this.this$0.stopRecording(2, false, 0, false, 0);
            goto L14
        L12:
            if (this.this$0.recordingAudio == null) goto L14;
        L19:
            if (r9 != 0) goto L23;
            MediaController.m4328$$Nest$fputcallInProgress(this.this$0, false);
            return;
        L23:
            if (r9 != 2) goto L29;
            EmbedBottomSheet r95 = EmbedBottomSheet.getInstance();
            if (r95 == null) goto L27;
            r95.pause();
        L27:
            MediaController.m4328$$Nest$fputcallInProgress(this.this$0, true);
            return;
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int r1, String r2) {
            AndroidUtilities.runOnUIThread(new MediaController$4$$ExternalSyntheticLambda0(this, r1));
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$5, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass5 extends TimerTask {
        final /* synthetic */ MediaController this$0;
        final /* synthetic */ MessageObject val$currentPlayingMessageObject;

        public static /* synthetic */ void $r8$lambda$JrzgFkVbJ1bDtonMTN3B4NSCrKk(String r3, float r4) {
            ApplicationLoader.applicationContext.getSharedPreferences("media_saved_pos", 0).edit().putFloat(r3, r4).apply();
        }

        public static /* synthetic */ void $r8$lambda$eGTjyf4X0wU0EXtTPd88grFMXxw(AnonymousClass5 r0, MessageObject r1) {
            r0.lambda$run$1(r1);
        }

        public AnonymousClass5(MediaController r1, MessageObject r2) {
            this.this$0 = r1;
            this.val$currentPlayingMessageObject = r2;
        }

        private /* synthetic */ void lambda$run$1(MessageObject r12) {
            if (MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0) != null) goto L7;
            if (MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0) != null) goto L7;
            return;
        L7:
            if (MediaController.m4303$$Nest$fgetisPaused(this.this$0) == true) goto L46;
            VideoPlayer r0 = MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0);     // Catch: Exception -> L40
            MediaController r1 = this.this$0;
            if (r0 == null) goto L19;
            long r02 = MediaController.m4326$$Nest$fgetvideoPlayer(r1).getDuration();     // Catch: Exception -> L40
            long r5 = MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).getCurrentPosition();     // Catch: Exception -> L40
            if (r5 >= 0) goto L14;
            return;
        L14:
            if (r02 <= 0) goto L48;
            float r4 = r02;
            float r3 = MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).getBufferedPosition() / r4;     // Catch: Exception -> L40
            float r7 = r5 / r4;     // Catch: Exception -> L40
            if (r7 >= 1.0f) goto L49;
        L31:
            MediaController.m4335$$Nest$fputlastProgress(this.this$0, r5);     // Catch: Exception -> L40
            r12.audioPlayerDuration = (int) (r02 / 1000);     // Catch: Exception -> L40
            r12.audioProgress = r7;     // Catch: Exception -> L40
            r12.audioProgressSec = (int) (MediaController.m4304$$Nest$fgetlastProgress(this.this$0) / 1000);     // Catch: Exception -> L40
            r12.bufferedProgress = r3;     // Catch: Exception -> L40
            if (r7 >= 0.0f) goto L34;
        L38:
            NotificationCenter.getInstance(r12.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(r12.getId()), Float.valueOf(r7)});     // Catch: Exception -> L40
            return;
        L34:
            if (MediaController.m4323$$Nest$fgetshouldSavePositionForCurrentAudio(this.this$0) == null) goto L38;
            if ((SystemClock.elapsedRealtime() - MediaController.m4305$$Nest$fgetlastSaveTime(this.this$0)) < 1000) goto L38;
            String r03 = MediaController.m4323$$Nest$fgetshouldSavePositionForCurrentAudio(this.this$0);     // Catch: Exception -> L40
            MediaController.m4336$$Nest$fputlastSaveTime(this.this$0, SystemClock.elapsedRealtime());     // Catch: Exception -> L40
            Utilities.globalQueue.postRunnable(new MediaController$5$$ExternalSyntheticLambda1(r03, r7));     // Catch: Exception -> L40
            goto L38
        L49:
            return;
        L48:
            return;
        L19:
            r02 = MediaController.m4288$$Nest$fgetaudioPlayer(r1).getDuration();     // Catch: Exception -> L40
            r5 = MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0).getCurrentPosition();     // Catch: Exception -> L40
            if (r02 < 0) goto L22;
            r7 = r5 / r02;     // Catch: Exception -> L40
        L23:
            float r8 = MediaController.m4288$$Nest$fgetaudioPlayer(this.this$0).getBufferedPosition() / r02;     // Catch: Exception -> L40
            if (r02 != (-9223372036854775807L)) goto L26;
            return;
        L26:
            if (r5 >= 0) goto L28;
            return;
        L28:
            if (MediaController.m4317$$Nest$fgetseekToProgressPending(this.this$0) != 0.0f) goto L52;
            r3 = r8;
            goto L31
        L52:
            return;
        L22:
            r7 = 0.0f;
        L40:
            e = move-exception;
            FileLog.e(e);
            return;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Object r0 = MediaController.m4325$$Nest$fgetsync(this.this$0);
            monitor-enter(r0);
            AndroidUtilities.runOnUIThread(new MediaController$5$$ExternalSyntheticLambda0(this, this.val$currentPlayingMessageObject));     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$6, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ MediaController this$0;
        final /* synthetic */ VideoPlayer val$playerFinal;

        public AnonymousClass6(MediaController r1, VideoPlayer r2) {
            this.this$0 = r1;
            this.val$playerFinal = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$playerFinal.releasePlayer(true);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$7, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass7 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ MediaController this$0;
        final /* synthetic */ boolean val$destroyAtEnd;
        final /* synthetic */ MessageObject val$messageObject;
        final /* synthetic */ int[] val$playCount;
        final /* synthetic */ int val$tag;

        public static /* synthetic */ void $r8$lambda$e0oDxUC8CtwDvlGBPvTo1XbM3IE(AnonymousClass7 r0) {
            r0.lambda$onSurfaceDestroyed$0();
        }

        public AnonymousClass7(MediaController r1, int r2, MessageObject r3, int[] r4, boolean r5) {
            this.this$0 = r1;
            this.val$tag = r2;
            this.val$messageObject = r3;
            this.val$playCount = r4;
            this.val$destroyAtEnd = r5;
        }

        private /* synthetic */ void lambda$onSurfaceDestroyed$0() {
            this.this$0.cleanupPlayer(true, true);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onAudioTrackChanged(int r3) {
            if (this.val$tag == MediaController.m4308$$Nest$fgetplayerNum(this.this$0)) goto L5;
            return;
        L5:
            if (MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0) != null) goto L7;
            return;
        L7:
            if (MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).getAudioTrackState() != r3) goto L19;
            MediaController r0 = this.this$0;
            MessageObject r2 = this.val$messageObject;
            boolean r1 = true;
            if (r3 == 1) goto L12;
        L14:
            r1 = false;
        L15:
            MediaController.m4340$$Nest$mcheckAudioFocus(r0, r2, r1);
            return;
        L12:
            if (MediaController.m4326$$Nest$fgetvideoPlayer(r0).isPlaying() == false) goto L14;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onCues(CueGroup r1) {
            super.onCues(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
            FileLog.e(r2);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0) != null) goto L5;
            return;
        L5:
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).isDrawingReady() == true) goto L9;
            MediaController.m4334$$Nest$fputisDrawingWasReady(this.this$0, true);
            MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).setDrawingReady(true);
            MediaController.m4295$$Nest$fgetcurrentTextureViewContainer(this.this$0).setTag(1);
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime r1) {
            super.onRenderedFirstFrame(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekFinished(AnalyticsListener.EventTime r1) {
            super.onSeekFinished(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime r1) {
            super.onSeekStarted(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r9, int r10) {
            if (this.val$tag == MediaController.m4308$$Nest$fgetplayerNum(this.this$0)) goto L5;
            return;
        L5:
            MediaController.m4348$$Nest$mupdateVideoState(this.this$0, this.val$messageObject, this.val$playCount, this.val$destroyAtEnd, r9, r10);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public boolean onSurfaceDestroyed(SurfaceTexture r6) {
            if (MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0) != null) goto L5;
            return false;
        L5:
            int r0 = MediaController.m4307$$Nest$fgetpipSwitchingState(this.this$0);
            MediaController r2 = this.this$0;
            if (r0 != 2) goto L22;
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(r2) != null) goto L10;
        L19:
            MediaController.m4338$$Nest$fputpipSwitchingState(this.this$0, 0);
            return true;
        L10:
            if (MediaController.m4302$$Nest$fgetisDrawingWasReady(this.this$0) == false) goto L13;
            MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).setDrawingReady(true);
        L13:
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).getParent() != null) goto L16;
            MediaController.m4295$$Nest$fgetcurrentTextureViewContainer(this.this$0).addView(MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0));
        L16:
            if (MediaController.m4294$$Nest$fgetcurrentTextureView(this.this$0).getSurfaceTexture() == r6) goto L18;
            MediaController.m4294$$Nest$fgetcurrentTextureView(this.this$0).setSurfaceTexture(r6);
        L18:
            MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).setTextureView(MediaController.m4294$$Nest$fgetcurrentTextureView(this.this$0));
            goto L19
        L22:
            if (MediaController.m4307$$Nest$fgetpipSwitchingState(r2) != 1) goto L39;
            if (MediaController.m4290$$Nest$fgetbaseActivity(this.this$0) != null) goto L26;
        L36:
            MediaController.m4338$$Nest$fputpipSwitchingState(this.this$0, 0);
            return true;
        L26:
            if (MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0) != null) goto L31;
            MediaController.m4337$$Nest$fputpipRoundVideoView(this.this$0, new PipRoundVideoView());     // Catch: Exception -> L29
            MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0).show(MediaController.m4290$$Nest$fgetbaseActivity(this.this$0), new MediaController$7$$ExternalSyntheticLambda0(this));     // Catch: Exception -> L29
        L29:
            MediaController.m4337$$Nest$fputpipRoundVideoView(this.this$0, null);
        L31:
            if (MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0) == null) goto L36;
            if (MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0).getTextureView().getSurfaceTexture() == r6) goto L35;
            MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0).getTextureView().setSurfaceTexture(r6);
        L35:
            MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).setTextureView(MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0).getTextureView());
            goto L36
        L39:
            if (PhotoViewer.hasInstance() == true) goto L41;
        L44:
            return false;
        L41:
            if (PhotoViewer.getInstance().isInjectingVideoPlayer() == false) goto L44;
            PhotoViewer.getInstance().injectVideoPlayerSurface(r6);
            return true;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSurfaceTextureUpdated(SurfaceTexture r1) {
            super.onSurfaceTextureUpdated(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r3, int r4, int r5, float r6) {
            MediaController.m4330$$Nest$fputcurrentAspectRatioFrameLayoutRotation(this.this$0, r5);
            if (r5 != 90) goto L5;
        L8:
            MediaController r52 = this.this$0;
            if (r3 != 0) goto L11;
            float r32 = 1.0f;
        L12:
            MediaController.m4329$$Nest$fputcurrentAspectRatioFrameLayoutRatio(r52, r32);
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0) == null) goto L16;
            MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).setAspectRatio(MediaController.m4292$$Nest$fgetcurrentAspectRatioFrameLayoutRatio(this.this$0), MediaController.m4293$$Nest$fgetcurrentAspectRatioFrameLayoutRotation(this.this$0));
            return;
        L16:
            return;
        L11:
            r32 = (r4 * r6) / r3;
            goto L12
        L5:
            if (r5 == 270) goto L8;
            r4 = r3;
            r3 = r4;
            goto L8
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$8, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass8 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ MediaController this$0;
        final /* synthetic */ int val$tag;

        /* renamed from: $r8$lambda$wdnKOHpz-giMqv7r9d8OwzyIHM4, reason: not valid java name */
        public static /* synthetic */ void m4356$r8$lambda$wdnKOHpzgiMqv7r9d8OwzyIHM4(AnonymousClass8 r0, int r1, int r2) {
            r0.lambda$onStateChanged$0(r1, r2);
        }

        public AnonymousClass8(MediaController r1, int r2) {
            this.this$0 = r1;
            this.val$tag = r2;
        }

        private /* synthetic */ void lambda$onStateChanged$0(int r2, int r3) {
            if (r2 == MediaController.m4297$$Nest$fgetemojiSoundPlayerNum(this.this$0)) goto L6;
            return;
        L6:
            if (r3 == 4) goto L8;
            return;
        L8:
            if (MediaController.m4296$$Nest$fgetemojiSoundPlayer(this.this$0) == null) goto L17;
            MediaController.m4296$$Nest$fgetemojiSoundPlayer(this.this$0).releasePlayer(true);     // Catch: Exception -> L11
            MediaController.m4331$$Nest$fputemojiSoundPlayer(this.this$0, null);     // Catch: Exception -> L11
            return;
        L11:
            e = move-exception;
            FileLog.e(e);
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onAudioTrackChanged(int r1) {
            super.onAudioTrackChanged(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onCues(CueGroup r1) {
            super.onCues(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime r1) {
            super.onRenderedFirstFrame(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekFinished(AnalyticsListener.EventTime r1) {
            super.onSeekFinished(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime r1) {
            super.onSeekStarted(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r2, int r3) {
            AndroidUtilities.runOnUIThread(new MediaController$8$$ExternalSyntheticLambda0(this, this.val$tag, r3));
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ boolean onSurfaceDestroyed(SurfaceTexture r1) {
            return super.onSurfaceDestroyed(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSurfaceTextureUpdated(SurfaceTexture r1) {
            super.onSurfaceTextureUpdated(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
        }
    }

    /* renamed from: org.telegram.messenger.MediaController$9, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass9 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ MediaController this$0;
        final /* synthetic */ boolean val$destroyAtEnd;
        final /* synthetic */ MessageObject val$messageObject;
        final /* synthetic */ int[] val$playCount;
        final /* synthetic */ int val$tag;

        public static /* synthetic */ void $r8$lambda$0YHtEUo5zCE8ATVeFVJxq4ILBpc(AnonymousClass9 r0) {
            r0.lambda$onSurfaceDestroyed$0();
        }

        public AnonymousClass9(MediaController r1, int r2, MessageObject r3, int[] r4, boolean r5) {
            this.this$0 = r1;
            this.val$tag = r2;
            this.val$messageObject = r3;
            this.val$playCount = r4;
            this.val$destroyAtEnd = r5;
        }

        private /* synthetic */ void lambda$onSurfaceDestroyed$0() {
            this.this$0.cleanupPlayer(true, true);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onAudioTrackChanged(int r3) {
            if (this.val$tag == MediaController.m4308$$Nest$fgetplayerNum(this.this$0)) goto L5;
            return;
        L5:
            if (MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0) != null) goto L7;
            return;
        L7:
            if (MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).getAudioTrackState() != r3) goto L19;
            MediaController r0 = this.this$0;
            MessageObject r2 = this.val$messageObject;
            boolean r1 = true;
            if (r3 == 1) goto L12;
        L14:
            r1 = false;
        L15:
            MediaController.m4340$$Nest$mcheckAudioFocus(r0, r2, r1);
            return;
        L12:
            if (MediaController.m4326$$Nest$fgetvideoPlayer(r0).isPlaying() == false) goto L14;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onCues(CueGroup r1) {
            super.onCues(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
            FileLog.e(r2);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0) == null) goto L8;
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).isDrawingReady() == true) goto L8;
            MediaController.m4334$$Nest$fputisDrawingWasReady(this.this$0, true);
            MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).setDrawingReady(true);
            MediaController.m4295$$Nest$fgetcurrentTextureViewContainer(this.this$0).setTag(1);
        L8:
            if (MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0) != null) goto L10;
            return;
        L10:
            if (CastSync.isActive() == false) goto L14;
            MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).setMute(true);
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime r1) {
            super.onRenderedFirstFrame(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekFinished(AnalyticsListener.EventTime r1) {
            super.onSeekFinished(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime r1) {
            super.onSeekStarted(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r9, int r10) {
            if (this.val$tag == MediaController.m4308$$Nest$fgetplayerNum(this.this$0)) goto L5;
            return;
        L5:
            MediaController.m4348$$Nest$mupdateVideoState(this.this$0, this.val$messageObject, this.val$playCount, this.val$destroyAtEnd, r9, r10);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public boolean onSurfaceDestroyed(SurfaceTexture r6) {
            if (MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0) != null) goto L5;
            return false;
        L5:
            int r0 = MediaController.m4307$$Nest$fgetpipSwitchingState(this.this$0);
            MediaController r2 = this.this$0;
            if (r0 != 2) goto L22;
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(r2) != null) goto L10;
        L19:
            MediaController.m4338$$Nest$fputpipSwitchingState(this.this$0, 0);
            return true;
        L10:
            if (MediaController.m4302$$Nest$fgetisDrawingWasReady(this.this$0) == false) goto L13;
            MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).setDrawingReady(true);
        L13:
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).getParent() != null) goto L16;
            MediaController.m4295$$Nest$fgetcurrentTextureViewContainer(this.this$0).addView(MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0));
        L16:
            if (MediaController.m4294$$Nest$fgetcurrentTextureView(this.this$0).getSurfaceTexture() == r6) goto L18;
            MediaController.m4294$$Nest$fgetcurrentTextureView(this.this$0).setSurfaceTexture(r6);
        L18:
            MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).setTextureView(MediaController.m4294$$Nest$fgetcurrentTextureView(this.this$0));
            goto L19
        L22:
            if (MediaController.m4307$$Nest$fgetpipSwitchingState(r2) != 1) goto L39;
            if (MediaController.m4290$$Nest$fgetbaseActivity(this.this$0) != null) goto L26;
        L36:
            MediaController.m4338$$Nest$fputpipSwitchingState(this.this$0, 0);
            return true;
        L26:
            if (MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0) != null) goto L31;
            MediaController.m4337$$Nest$fputpipRoundVideoView(this.this$0, new PipRoundVideoView());     // Catch: Exception -> L29
            MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0).show(MediaController.m4290$$Nest$fgetbaseActivity(this.this$0), new MediaController$9$$ExternalSyntheticLambda0(this));     // Catch: Exception -> L29
        L29:
            MediaController.m4337$$Nest$fputpipRoundVideoView(this.this$0, null);
        L31:
            if (MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0) == null) goto L36;
            if (MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0).getTextureView().getSurfaceTexture() == r6) goto L35;
            MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0).getTextureView().setSurfaceTexture(r6);
        L35:
            MediaController.m4326$$Nest$fgetvideoPlayer(this.this$0).setTextureView(MediaController.m4306$$Nest$fgetpipRoundVideoView(this.this$0).getTextureView());
            goto L36
        L39:
            if (PhotoViewer.hasInstance() == true) goto L41;
        L44:
            return false;
        L41:
            if (PhotoViewer.getInstance().isInjectingVideoPlayer() == false) goto L44;
            PhotoViewer.getInstance().injectVideoPlayerSurface(r6);
            return true;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSurfaceTextureUpdated(SurfaceTexture r1) {
            super.onSurfaceTextureUpdated(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r3, int r4, int r5, float r6) {
            MediaController.m4330$$Nest$fputcurrentAspectRatioFrameLayoutRotation(this.this$0, r5);
            if (r5 != 90) goto L5;
        L8:
            MediaController r52 = this.this$0;
            if (r3 != 0) goto L11;
            float r32 = 1.0f;
        L12:
            MediaController.m4329$$Nest$fputcurrentAspectRatioFrameLayoutRatio(r52, r32);
            if (MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0) == null) goto L16;
            MediaController.m4291$$Nest$fgetcurrentAspectRatioFrameLayout(this.this$0).setAspectRatio(MediaController.m4292$$Nest$fgetcurrentAspectRatioFrameLayoutRatio(this.this$0), MediaController.m4293$$Nest$fgetcurrentAspectRatioFrameLayoutRotation(this.this$0));
            return;
        L16:
            return;
        L11:
            r32 = (r4 * r6) / r3;
            goto L12
        L5:
            if (r5 == 270) goto L8;
            r4 = r3;
            r3 = r4;
            goto L8
        }
    }

    /* loaded from: classes5.dex */
    public static class AlbumEntry {
        public int bucketId;
        public String bucketName;
        public PhotoEntry coverPhoto;
        public ArrayList<PhotoEntry> photos;
        public SparseArray<PhotoEntry> photosByIds;
        public boolean videoOnly;

        public AlbumEntry(int r2, String r3, PhotoEntry r4) {
            this.photos = new ArrayList();
            this.photosByIds = new SparseArray();
            this.bucketId = r2;
            this.bucketName = r3;
            this.coverPhoto = r4;
        }

        public void addPhoto(PhotoEntry r2) {
            this.photos.add(r2);
            this.photosByIds.put(r2.imageId, r2);
        }
    }

    /* loaded from: classes5.dex */
    public static class AudioBuffer {
        ByteBuffer buffer;
        byte[] bufferBytes;
        int finished;
        long pcmOffset;
        int size;

        public AudioBuffer(int r2) {
            this.buffer = ByteBuffer.allocateDirect(r2);
            this.bufferBytes = new byte[r2];
        }
    }

    /* loaded from: classes5.dex */
    public static class AudioEntry {
        public String author;
        public int duration;
        public String genre;
        public long id;
        public MessageObject messageObject;
        public String path;
        public String title;

        public AudioEntry() {
        }
    }

    /* loaded from: classes5.dex */
    public static class CropState extends TLObject {
        public static final int constructor = 1151577037;
        public float cropPh;
        public float cropPw;
        public float cropPx;
        public float cropPy;
        public float cropRotate;
        public float cropScale;
        public boolean freeform;
        public int height;
        public boolean initied;
        public float lockedAspectRatio;
        public Matrix matrix;
        public boolean mirrored;
        public int orientation;
        public float scale;
        public float stateScale;
        public int transformHeight;
        public int transformRotation;
        public int transformWidth;
        public Matrix useMatrix;
        public int width;

        public CropState() {
            this.cropScale = 1.0f;
            this.cropPw = 1.0f;
            this.cropPh = 1.0f;
        }

        public /* bridge */ /* synthetic */ Object clone() {
            return m4357clone();
        }

        /* renamed from: clone, reason: collision with other method in class */
        public CropState m4357clone() {
            CropState r0 = new CropState();
            r0.cropPx = this.cropPx;
            r0.cropPy = this.cropPy;
            r0.cropScale = this.cropScale;
            r0.cropRotate = this.cropRotate;
            r0.cropPw = this.cropPw;
            r0.cropPh = this.cropPh;
            r0.transformWidth = this.transformWidth;
            r0.transformHeight = this.transformHeight;
            r0.transformRotation = this.transformRotation;
            r0.mirrored = this.mirrored;
            r0.stateScale = this.stateScale;
            r0.scale = this.scale;
            r0.matrix = this.matrix;
            r0.width = this.width;
            r0.height = this.height;
            r0.freeform = this.freeform;
            r0.lockedAspectRatio = this.lockedAspectRatio;
            r0.orientation = this.orientation;
            r0.initied = this.initied;
            r0.useMatrix = this.useMatrix;
            return r0;
        }

        public boolean isEmpty() {
            Matrix r0 = this.matrix;
            if (r0 != null) goto L5;
        L6:
            Matrix r02 = this.useMatrix;
            if (r02 == null) goto L11;
            if (r02.isIdentity() == true) goto L11;
            return false;
        L11:
            if (this.cropPw == 1.0f) goto L13;
            return false;
        L13:
            if (this.cropPh == 1.0f) goto L15;
            return false;
        L15:
            if (this.cropScale == 1.0f) goto L17;
            return false;
        L17:
            if (this.cropRotate == 0.0f) goto L19;
            return false;
        L19:
            if (this.transformWidth == 0) goto L21;
            return false;
        L21:
            if (this.transformHeight == 0) goto L23;
            return false;
        L23:
            if (this.transformRotation == 0) goto L25;
            return false;
        L25:
            if (this.mirrored == false) goto L27;
            return false;
        L27:
            if (this.stateScale == 0.0f) goto L29;
            return false;
        L29:
            if (this.scale == 0.0f) goto L31;
            return false;
        L31:
            if (this.width == 0) goto L33;
            return false;
        L33:
            if (this.height == 0) goto L35;
            return false;
        L35:
            if (this.freeform == false) goto L37;
            return false;
        L37:
            if (this.lockedAspectRatio != 0.0f) goto L56;
            return true;
        L56:
            return false;
        L5:
            if (r0.isIdentity() == true) goto L6;
            return false;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r6, boolean r7) {
            this.cropPx = r6.readFloat(r7);
            this.cropPy = r6.readFloat(r7);
            this.cropScale = r6.readFloat(r7);
            this.cropRotate = r6.readFloat(r7);
            this.cropPw = r6.readFloat(r7);
            this.cropPh = r6.readFloat(r7);
            this.transformWidth = r6.readInt32(r7);
            this.transformHeight = r6.readInt32(r7);
            this.transformRotation = r6.readInt32(r7);
            this.mirrored = r6.readBool(r7);
            this.stateScale = r6.readFloat(r7);
            this.scale = r6.readFloat(r7);
            float[] r1 = new float[9];
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= 9) goto L5;
            r1[r3] = r6.readFloat(r7);
            r3 = r3 + 1;
            goto L3
        L5:
            Matrix r32 = new Matrix();
            this.matrix = r32;
            r32.setValues(r1);
            this.width = r6.readInt32(r7);
            this.height = r6.readInt32(r7);
            this.freeform = r6.readBool(r7);
            this.lockedAspectRatio = r6.readFloat(r7);
            if (r6.readInt32(r7) != 178403937) goto L10;
        L7:
            if (r2 >= 9) goto L9;
            r1[r2] = r6.readFloat(r7);
            r2 = r2 + 1;
            goto L7
        L9:
            Matrix r0 = new Matrix();
            this.useMatrix = r0;
            r0.setValues(r1);
        L10:
            this.initied = r6.readBool(r7);
            this.orientation = r6.readInt32(r7);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            r6.writeFloat(this.cropPx);
            r6.writeFloat(this.cropPy);
            r6.writeFloat(this.cropScale);
            r6.writeFloat(this.cropRotate);
            r6.writeFloat(this.cropPw);
            r6.writeFloat(this.cropPh);
            r6.writeInt32(this.transformWidth);
            r6.writeInt32(this.transformHeight);
            r6.writeInt32(this.transformRotation);
            r6.writeBool(this.mirrored);
            r6.writeFloat(this.stateScale);
            r6.writeFloat(this.scale);
            float[] r1 = new float[9];
            Matrix r2 = this.matrix;
            int r3 = 0;
            if (r2 == null) goto L5;
            r2.getValues(r1);
        L8:
            int r22 = 0;
        L9:
            if (r22 >= 9) goto L11;
            r6.writeFloat(r1[r22]);
            r22 = r22 + 1;
            goto L9
        L11:
            r6.writeInt32(this.width);
            r6.writeInt32(this.height);
            r6.writeBool(this.freeform);
            r6.writeFloat(this.lockedAspectRatio);
            if (this.useMatrix != null) goto L14;
            r6.writeInt32(TLRPC.TL_null.constructor);
        L17:
            r6.writeBool(this.initied);
            r6.writeInt32(this.orientation);
            return;
        L14:
            r6.writeInt32(178403937);
            this.useMatrix.getValues(r1);
        L15:
            if (r3 >= 9) goto L17;
            r6.writeFloat(r1[r3]);
            r3 = r3 + 1;
            goto L15
        L5:
            int r23 = 0;
        L6:
            if (r23 >= 9) goto L8;
            r1[r23] = 0.0f;
            r23 = r23 + 1;
            goto L6
        }
    }

    /* loaded from: classes5.dex */
    public class ExternalObserver extends ContentObserver {
        final /* synthetic */ MediaController this$0;

        public ExternalObserver(MediaController r1) {
            this.this$0 = r1;
            super(null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean r1) {
            super.onChange(r1);
            MediaController.m4344$$Nest$mprocessMediaObserver(this.this$0, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
    }

    public static class GalleryObserverExternal extends ContentObserver {
        public static /* synthetic */ void $r8$lambda$RXdudxX90yFWybAMOEn80RyiCv0() {
            MediaController.m4351$$Nest$sfputrefreshGalleryRunnable(null);
            MediaController.loadGalleryPhotosAlbums(0);
        }

        public GalleryObserverExternal() {
            super(null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean r3) {
            super.onChange(r3);
            if (MediaController.m4350$$Nest$sfgetrefreshGalleryRunnable() == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(MediaController.m4350$$Nest$sfgetrefreshGalleryRunnable());
        L5:
            MediaController$GalleryObserverExternal$$ExternalSyntheticLambda0 r2 = new MediaController$GalleryObserverExternal$$ExternalSyntheticLambda0();
            MediaController.m4351$$Nest$sfputrefreshGalleryRunnable(r2);
            AndroidUtilities.runOnUIThread(r2, 2000);
        }
    }

    public static class GalleryObserverInternal extends ContentObserver {
        /* renamed from: $r8$lambda$yZvxpN2l5dXoSU1LAe029q-Ycg4, reason: not valid java name */
        public static /* synthetic */ void m4358$r8$lambda$yZvxpN2l5dXoSU1LAe029qYcg4(GalleryObserverInternal r0) {
            r0.lambda$scheduleReloadRunnable$0();
        }

        public GalleryObserverInternal() {
            super(null);
        }

        private /* synthetic */ void lambda$scheduleReloadRunnable$0() {
            if (PhotoViewer.getInstance().isVisible() == false) goto L6;
            scheduleReloadRunnable();
            return;
        L6:
            MediaController.m4351$$Nest$sfputrefreshGalleryRunnable(null);
            MediaController.loadGalleryPhotosAlbums(0);
        }

        private void scheduleReloadRunnable() {
            MediaController$GalleryObserverInternal$$ExternalSyntheticLambda0 r0 = new MediaController$GalleryObserverInternal$$ExternalSyntheticLambda0(this);
            MediaController.m4351$$Nest$sfputrefreshGalleryRunnable(r0);
            AndroidUtilities.runOnUIThread(r0, 2000);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean r1) {
            super.onChange(r1);
            if (MediaController.m4350$$Nest$sfgetrefreshGalleryRunnable() == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(MediaController.m4350$$Nest$sfgetrefreshGalleryRunnable());
        L5:
            scheduleReloadRunnable();
        }
    }

    /* loaded from: classes5.dex */
    public class InternalObserver extends ContentObserver {
        final /* synthetic */ MediaController this$0;

        public InternalObserver(MediaController r1) {
            this.this$0 = r1;
            super(null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean r1) {
            super.onChange(r1);
            MediaController.m4344$$Nest$mprocessMediaObserver(this.this$0, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        }
    }

    /* loaded from: classes5.dex */
    public static class MediaEditState {
        public long averageDuration;
        public CharSequence caption;
        public String coverPath;
        public TLRPC.Photo coverPhoto;
        public Object coverPhotoParentObject;
        public long coverSavedPosition;
        public CropState cropState;
        public ArrayList<VideoEditedInfo.MediaEntity> croppedMediaEntities;
        public String croppedPaintPath;
        public VideoEditedInfo editedInfo;
        public long effectId;
        public ArrayList<TLRPC.MessageEntity> entities;
        public String filterPath;
        public String fullPaintPath;
        public Boolean highQuality;
        public String imagePath;
        public boolean isCropped;
        public boolean isFiltered;
        public boolean isPainted;
        public boolean isVideo;
        public long livePhotoVideoOffset;
        public ArrayList<VideoEditedInfo.MediaEntity> mediaEntities;
        public String paintPath;
        public SavedFilterState savedFilterState;
        public ArrayList<TLRPC.InputDocument> stickers;
        public String thumbPath;
        public int ttl;

        public MediaEditState() {
        }

        public void copyFrom(MediaEditState r3) {
            this.caption = r3.caption;
            this.thumbPath = r3.thumbPath;
            this.imagePath = r3.imagePath;
            this.filterPath = r3.filterPath;
            this.paintPath = r3.paintPath;
            this.croppedPaintPath = r3.croppedPaintPath;
            this.fullPaintPath = r3.fullPaintPath;
            this.entities = r3.entities;
            this.savedFilterState = r3.savedFilterState;
            this.mediaEntities = r3.mediaEntities;
            this.croppedMediaEntities = r3.croppedMediaEntities;
            this.stickers = r3.stickers;
            this.editedInfo = r3.editedInfo;
            this.averageDuration = r3.averageDuration;
            this.isFiltered = r3.isFiltered;
            this.isPainted = r3.isPainted;
            this.isCropped = r3.isCropped;
            this.livePhotoVideoOffset = r3.livePhotoVideoOffset;
            this.ttl = r3.ttl;
            this.cropState = r3.cropState;
            this.coverPath = r3.coverPath;
            this.highQuality = r3.highQuality;
        }

        public String getPath() {
            return null;
        }

        public boolean isHighQuality() {
            Boolean r0 = this.highQuality;
            if (r0 != null) goto L14;
            if (ExteraConfig.getAlwaysSendInHD() == false) goto L7;
            return true;
        L7:
            if (SharedConfig.photoHighQualityDefault == true) goto L15;
            return false;
        L15:
            return true;
        L14:
            return r0.booleanValue();
        }

        public boolean isLivePhoto() {
            if ((this instanceof PhotoEntry) == true) goto L5;
            return false;
        L5:
            if (((PhotoEntry) this).isLivePhoto() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public void reset() {
            this.caption = null;
            this.coverPath = null;
            this.coverPhoto = null;
            this.coverPhotoParentObject = null;
            this.thumbPath = null;
            this.filterPath = null;
            this.imagePath = null;
            this.paintPath = null;
            this.fullPaintPath = null;
            this.croppedPaintPath = null;
            this.isFiltered = false;
            this.isPainted = false;
            this.isCropped = false;
            this.ttl = 0;
            this.mediaEntities = null;
            this.editedInfo = null;
            this.entities = null;
            this.savedFilterState = null;
            this.stickers = null;
            this.cropState = null;
            this.highQuality = null;
        }

        public void resetEdit() {
            this.thumbPath = null;
            this.filterPath = null;
            this.imagePath = null;
            this.paintPath = null;
            this.croppedPaintPath = null;
            this.isFiltered = false;
            this.isPainted = false;
            this.isCropped = false;
            this.mediaEntities = null;
            this.editedInfo = null;
            this.entities = null;
            this.savedFilterState = null;
            this.stickers = null;
            this.cropState = null;
        }
    }

    /* loaded from: classes5.dex */
    public static class MediaLoader implements NotificationCenter.NotificationCenterDelegate {
        private boolean cancelled;
        private int copiedFiles;
        private AccountInstance currentAccount;
        private boolean finished;
        private float finishedProgress;
        private boolean isMusic;
        private HashMap<String, MessageObject> loadingMessageObjects;
        private ArrayList<MessageObject> messageObjects;
        private MessagesStorage.IntCallback onFinishRunnable;
        private AlertDialog progressDialog;
        private CountDownLatch waitingForFile;

        public static /* synthetic */ void $r8$lambda$9OsHS30S80wJRekNsYnxmCgU7oY(MediaLoader r0) {
            r0.lambda$checkIfFinished$4();
        }

        public static /* synthetic */ void $r8$lambda$EpYWVLUEGn1rrARLfqNA0ENJXic(MediaLoader r0) {
            r0.lambda$copyFile$8();
        }

        /* renamed from: $r8$lambda$R_GBeaMiCD7aitM7p4L0pYR0-4I, reason: not valid java name */
        public static /* synthetic */ void m4359$r8$lambda$R_GBeaMiCD7aitM7p4L0pYR04I(MediaLoader r0, int r1) {
            r0.lambda$copyFile$9(r1);
        }

        public static /* synthetic */ void $r8$lambda$V5MGDxUC9eAiCc1AK6BTie577zg(MediaLoader r0, DialogInterface r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$WTQinZyjd5v3VV2PV5dsTWEC6g4(MediaLoader r0, int r1) {
            r0.lambda$copyFile$10(r1);
        }

        public static /* synthetic */ void $r8$lambda$XeWHCycIkxnymMk4Dh0BHCWOOcw(MediaLoader r0, MessageObject r1) {
            r0.lambda$addMessageToLoad$7(r1);
        }

        public static /* synthetic */ void $r8$lambda$Y3on9DKKkwwaTx0jU7VUcuYzMvs(MediaLoader r0) {
            r0.lambda$start$1();
        }

        /* renamed from: $r8$lambda$Z2buFTBafvQxovkE-PXz8nxbPhk, reason: not valid java name */
        public static /* synthetic */ void m4360$r8$lambda$Z2buFTBafvQxovkEPXz8nxbPhk(MediaLoader r0, int r1) {
            r0.lambda$didReceivedNotification$11(r1);
        }

        /* renamed from: $r8$lambda$cVNiNE-a6wOXobZoMUmVwIuqlMw, reason: not valid java name */
        public static /* synthetic */ void m4361$r8$lambda$cVNiNEa6wOXobZoMUmVwIuqlMw(MediaLoader r0, int r1) {
            r0.lambda$processLivePhotoMessage$6(r1);
        }

        /* renamed from: $r8$lambda$g56jRdVgGl-_F7sjueYqj67w5Vg, reason: not valid java name */
        public static /* synthetic */ void m4362$r8$lambda$g56jRdVgGl_F7sjueYqj67w5Vg(MediaLoader r0, boolean r1, TLRPC.PhotoSize r2, MessageObject r3, TLRPC.Photo r4, boolean r5, TLRPC.Document r6) {
            r0.lambda$processLivePhotoMessage$5(r1, r2, r3, r4, r5, r6);
        }

        /* renamed from: $r8$lambda$hKSm6jYUXc8QlnmbEO-hDSIV1yQ, reason: not valid java name */
        public static /* synthetic */ void m4363$r8$lambda$hKSm6jYUXc8QlnmbEOhDSIV1yQ(MediaLoader r0) {
            r0.lambda$start$2();
        }

        public static /* synthetic */ void $r8$lambda$k8J8sjAGTU89m1NNaVMTTwJyrW0(MediaLoader r0) {
            r0.lambda$checkIfFinished$3();
        }

        public MediaLoader(Context r2, AccountInstance r3, ArrayList<MessageObject> r4, MessagesStorage.IntCallback r5) {
            this.loadingMessageObjects = new HashMap();
            this.currentAccount = r3;
            this.messageObjects = r4;
            this.onFinishRunnable = r5;
            this.isMusic = r4.get(0).isMusic();
            this.currentAccount.getNotificationCenter().addObserver(this, NotificationCenter.fileLoaded);
            this.currentAccount.getNotificationCenter().addObserver(this, NotificationCenter.fileLoadProgressChanged);
            this.currentAccount.getNotificationCenter().addObserver(this, NotificationCenter.fileLoadFailed);
            if (PhotoViewer.getInstance().isVisible() == false) goto L5;
            DarkThemeResourceProvider r32 = new DarkThemeResourceProvider();
        L6:
            AlertDialog r42 = new AlertDialog(r2, 2, r32);
            this.progressDialog = r42;
            r42.setMessage(LocaleController.getString(R.string.Loading));
            this.progressDialog.setCancelable(true);
            this.progressDialog.setCancelDialog(true);
            this.progressDialog.setOnCancelListener(new MediaController$MediaLoader$$ExternalSyntheticLambda9(this));
            return;
        L5:
            r32 = null;
            goto L6
        }

        private void addMessageToLoad(MessageObject r2) {
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda8(this, r2));
        }

        private void checkIfFinished() {
            if (this.loadingMessageObjects.isEmpty() == true) goto L5;
            return;
        L5:
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda7(this));
        }

        private boolean copyFile(File r28, File r29, String r30) {
            boolean r2 = false;
            if (AndroidUtilities.isInternalUri(Uri.fromFile(r28)) == false) goto L134;
            return false;
        L134:
            FileInputStream r3 = new FileInputStream(r28);     // Catch: Exception -> L109
            FileChannel r5 = r3.getChannel();     // Catch: Throwable -> L102
            File r10 = r29;
            FileChannel r4 = new FileOutputStream(r10).getChannel();     // Catch: Throwable -> L92
            long r11 = r5.size();     // Catch: Throwable -> L48
        L17:
            th = move-exception;
            FileLog.e(th);     // Catch: Throwable -> L48
        L32:
            long r6 = 0;
            long r13 = 0;
        L34:
            if (r6 >= r11) goto L37;
            if (this.cancelled == true) goto L37;
            boolean r16 = r2;
            FileInputStream r17 = r3;
            r4.transferFrom(r5, r6, Math.min(MediaStatus.COMMAND_EDIT_TRACKS, r11 - r6));     // Catch: Throwable -> L44
            long r22 = MediaStatus.COMMAND_EDIT_TRACKS + r6;     // Catch: Throwable -> L44
            if (r22 < r11) goto L42;
        L46:
            long r8 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L44
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda2(this, (int) (this.finishedProgress + (((100.0f / this.messageObjects.size()) * r6) / r11))));     // Catch: Throwable -> L44
            r13 = r8;
        L47:
            r6 = r22;
            r2 = r16;
            r3 = r17;
            goto L34
        L42:
            if (r13 > (SystemClock.elapsedRealtime() - 500)) goto L47;
        L44:
            th = th;
        L45:
            Throwable r1 = th;
            if (r4 == null) goto L144;
            r4.close();     // Catch: Throwable -> L89
        L145:
            throw r1;     // Catch: Throwable -> L72
        L89:
            th = move-exception;
            r1.addSuppressed(th);     // Catch: Throwable -> L72
            throw r1;     // Catch: Throwable -> L72
        L144:
            throw r1;     // Catch: Throwable -> L72
        L37:
            r16 = r2;
            r17 = r3;
            if (this.cancelled == false) goto L53;
            if (r4 == null) goto L83;
            r4.close();     // Catch: Throwable -> L72
        L83:
            r5.close();     // Catch: Throwable -> L79
            r17.close();     // Catch: Exception -> L77
        L112:
            r10.delete();
            return r16;
        L53:
            if (this.isMusic == false) goto L55;
            AndroidUtilities.addMediaToGallery(r10);     // Catch: Throwable -> L44
        L68:
            float r0 = this.finishedProgress + (100.0f / this.messageObjects.size());     // Catch: Throwable -> L44
            this.finishedProgress = r0;     // Catch: Throwable -> L44
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda3(this, (int) r0));     // Catch: Throwable -> L44
            if (r4 == null) goto L74;
            r4.close();     // Catch: Throwable -> L72
        L74:
            r5.close();     // Catch: Throwable -> L79
            r17.close();     // Catch: Exception -> L77
            return true;
        L55:
            DownloadManager r18 = (DownloadManager) ApplicationLoader.applicationContext.getSystemService("download");     // Catch: Throwable -> L44
            if (TextUtils.isEmpty(r30) == false) goto L66;
            MimeTypeMap r02 = MimeTypeMap.getSingleton();     // Catch: Throwable -> L44
            String r32 = r10.getName();     // Catch: Throwable -> L44
            int r62 = r32.lastIndexOf(46);     // Catch: Throwable -> L44
            if (r62 != (-1)) goto L61;
            String r222 = "text/plain";
        L67:
            r18.addCompletedDownload(r10.getName(), r10.getName(), false, r222, r10.getAbsolutePath(), r10.length(), true);     // Catch: Throwable -> L44
            goto L68
        L61:
            String r03 = r02.getMimeTypeFromExtension(r32.substring(r62 + 1).toLowerCase());     // Catch: Throwable -> L44
            if (TextUtils.isEmpty(r03) == false) goto L64;
            r03 = "text/plain";
        L64:
            r222 = r03;
            goto L67
        L66:
            r222 = r30;
            goto L67
        L12:
            if (AndroidUtilities.isInternalUri(((Integer) FileDescriptor.class.getDeclaredMethod("getInt$", null).invoke(r3.getFD(), null)).intValue()) == false) goto L32;
            if (this.progressDialog == null) goto L19;
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda1(this));     // Catch: Throwable -> L17
        L19:
            if (r4 != null) goto L136;
        L125:
            r5.close();     // Catch: Throwable -> L29
            r3.close();     // Catch: Exception -> L27
            return false;
        L27:
            e = e;
        L28:
            r16 = false;
        L111:
            FileLog.e(e);
        L29:
            th = move-exception;
            Throwable r12 = th;
            r16 = false;
            r17 = r3;
        L116:
            r17.close();     // Catch: Throwable -> L106
        L148:
            throw r12;     // Catch: Exception -> L77
        L106:
            th = move-exception;
            r12.addSuppressed(th);     // Catch: Exception -> L77
            throw r12;     // Catch: Exception -> L77
        L136:
            r4.close();     // Catch: Throwable -> L22
        L22:
            th = move-exception;
            Throwable r14 = th;
            r16 = false;
            r17 = r3;
        L96:
            if (r5 == null) goto L146;
            r5.close();     // Catch: Throwable -> L99
        L147:
            throw r14;     // Catch: Throwable -> L79
        L99:
            th = move-exception;
            r14.addSuppressed(th);     // Catch: Throwable -> L79
            throw r14;     // Catch: Throwable -> L79
        L146:
            throw r14;     // Catch: Throwable -> L79
        L48:
            th = th;
            r16 = r2;
            r17 = r3;
        L92:
            th = th;
        L93:
            r16 = false;
            r17 = r3;
        L73:
            r14 = th;
        L72:
            th = th;
        L94:
            th = th;
            r10 = r29;
        L79:
            th = th;
        L80:
            r12 = th;
        L102:
            th = th;
            r10 = r29;
            r16 = false;
            r17 = r3;
        L109:
            e = e;
            r10 = r29;
        L77:
            e = e;
            goto L111
        }

        private /* synthetic */ void lambda$addMessageToLoad$7(MessageObject r4) {
            TLRPC.Document r0 = r4.getDocument();
            TLRPC.Document r1 = r4.qualityToSave;
            if (r1 == null) goto L5;
            r0 = r1;
        L5:
            if (r0 != null) goto L7;
            return;
        L7:
            String r12 = FileLoader.getAttachFileName(r0);
            this.loadingMessageObjects.put(r12, r4);
            FileLoader r3 = this.currentAccount.getFileLoader();
            if (r4.shouldEncryptPhotoOrVideo() == false) goto L10;
            int r13 = 2;
        L11:
            r3.loadFile(r0, r4, 3, r13);
            return;
        L10:
            r13 = 0;
            goto L11
        }

        private /* synthetic */ void lambda$checkIfFinished$3() {
            this.onFinishRunnable.run(this.copiedFiles);
        }

        private /* synthetic */ void lambda$checkIfFinished$4() {
        L5:
            e = move-exception;
            FileLog.e(e);
        L13:
            this.currentAccount.getNotificationCenter().removeObserver(this, NotificationCenter.fileLoaded);
            this.currentAccount.getNotificationCenter().removeObserver(this, NotificationCenter.fileLoadProgressChanged);
            this.currentAccount.getNotificationCenter().removeObserver(this, NotificationCenter.fileLoadFailed);
            return;
        L3:
            if (this.progressDialog.isShowing() == false) goto L7;
            this.progressDialog.dismiss();     // Catch: Exception -> L5
        L9:
            if (this.onFinishRunnable == null) goto L13;
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda4(this));     // Catch: Exception -> L5
            goto L13
        L7:
            this.finished = true;     // Catch: Exception -> L5
            goto L9
        }

        private /* synthetic */ void lambda$copyFile$10(int r1) {
            this.progressDialog.setProgress(r1);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$copyFile$8() {
            this.progressDialog.dismiss();     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$copyFile$9(int r1) {
            this.progressDialog.setProgress(r1);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$didReceivedNotification$11(int r1) {
            this.progressDialog.setProgress(r1);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$new$0(DialogInterface r1) {
            this.cancelled = true;
        }

        private /* synthetic */ void lambda$processLivePhotoMessage$5(boolean r7, TLRPC.PhotoSize r8, MessageObject r9, TLRPC.Photo r10, boolean r11, TLRPC.Document r12) {
            if (r7 == false) goto L4;
            String r72 = FileLoader.getAttachFileName(r8);
            this.loadingMessageObjects.put(r72, r9);
            MessageObject r2 = r9;
            this.currentAccount.getFileLoader().loadFile(ImageLocation.getForPhoto(r8, r10), r2, "jpg", 3, 0);
        L5:
            if (r11 == false) goto L8;
            String r73 = FileLoader.getAttachFileName(r12);
            this.loadingMessageObjects.put(r73, r2);
            this.currentAccount.getFileLoader().loadFile(r12, r2, 3, 0);
            return;
        L8:
            return;
        L4:
            r2 = r9;
            goto L5
        }

        private /* synthetic */ void lambda$processLivePhotoMessage$6(int r1) {
            this.progressDialog.setProgress(r1);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$start$1() {
            if (this.finished == true) goto L6;
            this.progressDialog.show();
            return;
        }

        private /* synthetic */ void lambda$start$2() {
        L120:
            e = move-exception;
            FileLog.e(e);
            return;
        L4:
            Object r5 = null;
            if (Build.VERSION.SDK_INT < 29) goto L60;
            int r1 = this.messageObjects.size();     // Catch: Exception -> L120
            int r2 = 0;
        L7:
            if (r2 >= r1) goto L118;
            MessageObject r8 = this.messageObjects.get(r2);     // Catch: Exception -> L120
            if (processLivePhotoMessage(r8) == true) goto L58;
            String r9 = r8.messageOwner.attachPath;     // Catch: Exception -> L120
            TLRPC.Document r10 = r8.getDocument();     // Catch: Exception -> L120
            TLRPC.Document r11 = r8.qualityToSave;     // Catch: Exception -> L120
            if (r11 == null) goto L14;
            r9 = null;
            r10 = r11;
        L14:
            String r102 = FileLoader.getDocumentFileName(r10);     // Catch: Exception -> L120
            if (TextUtils.isEmpty(r102) == true) goto L17;
        L19:
            if (r9 == null) goto L26;
            if (r9.length() <= 0) goto L26;
            if (new File(r9).exists() == true) goto L26;
            r9 = null;
        L26:
            if (TextUtils.isEmpty(r9) == false) goto L37;
            FileLoader r92 = FileLoader.getInstance(this.currentAccount.getCurrentAccount());     // Catch: Exception -> L120
            TLRPC.MessageMedia r112 = MessageObject.getMedia(r8);     // Catch: Exception -> L120
            TLRPC.Document r12 = r8.qualityToSave;     // Catch: Exception -> L120
            if (r12 == null) goto L30;
            File r93 = r92.getPathToAttach(r12, null, false, true);     // Catch: Exception -> L120
        L36:
            r9 = r93.toString();     // Catch: Exception -> L120
            goto L37
        L30:
            File r122 = r92.getPathToMessage(r8.messageOwner, true);     // Catch: Exception -> L120
            if ((r112 instanceof TLRPC.TL_messageMediaDocument) == false) goto L35;
            TLRPC.TL_messageMediaDocument r113 = (TLRPC.TL_messageMediaDocument) r112;     // Catch: Exception -> L120
            if (r113.alt_documents.isEmpty() == true) goto L35;
            r93 = r92.getPathToAttach(r113.alt_documents.get(0), null, false, true);     // Catch: Exception -> L120
        L35:
            r93 = r122;
        L37:
            File r114 = new File(r9);     // Catch: Exception -> L120
            if (r114.exists() == true) goto L41;
            this.waitingForFile = new CountDownLatch(1);     // Catch: Exception -> L120
            addMessageToLoad(r8);     // Catch: Exception -> L120
            this.waitingForFile.await();     // Catch: Exception -> L120
        L41:
            if (this.cancelled == true) goto L118;
            if (r114.exists() == true) goto L50;
            r114 = FileLoader.getInstance(this.currentAccount.getCurrentAccount()).getPathToAttach(r8.messageOwner, true);     // Catch: Exception -> L120
            StringBuilder r82 = new StringBuilder();     // Catch: Exception -> L120
            r82.append("saving file: correcting path from ");     // Catch: Exception -> L120
            r82.append(r9);     // Catch: Exception -> L120
            r82.append(" to ");     // Catch: Exception -> L120
            if (r114 != null) goto L48;
            String r94 = null;
        L49:
            r82.append(r94);     // Catch: Exception -> L120
            FileLog.d(r82.toString());     // Catch: Exception -> L120
            goto L50
        L48:
            r94 = r114.getAbsolutePath();     // Catch: Exception -> L120
        L50:
            if (r114 == null) goto L58;
            if (r114.exists() == false) goto L58;
            if (this.isMusic == false) goto L56;
            int r83 = 3;
        L57:
            MediaController.m4352$$Nest$smsaveFileInternal(r83, r114, r102);     // Catch: Exception -> L120
            this.copiedFiles++;
            goto L58
        L56:
            r83 = 2;
            goto L57
        L17:
            if (r8.isVoice() == false) goto L19;
            r102 = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss", Locale.US).format(new Date()) + ".ogg";     // Catch: Exception -> L120
        L58:
            r2 = r2 + 1;     // Catch: Exception -> L120
        L118:
            checkIfFinished();     // Catch: Exception -> L120
            return;
        L60:
            if (this.isMusic == false) goto L62;
            File r13 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);     // Catch: Exception -> L120
        L63:
            r13.mkdir();     // Catch: Exception -> L120
            int r22 = this.messageObjects.size();     // Catch: Exception -> L120
            int r84 = 0;
        L64:
            if (r84 >= r22) goto L118;
            MessageObject r95 = this.messageObjects.get(r84);     // Catch: Exception -> L120
            if (processLivePhotoMessage(r95) == false) goto L68;
            Object r123 = r5;
        L117:
            r84 = r84 + 1;     // Catch: Exception -> L120
            r5 = r123;
            goto L64
        L68:
            TLRPC.Document r103 = r95.getDocument();     // Catch: Exception -> L120
            TLRPC.Document r115 = r95.qualityToSave;     // Catch: Exception -> L120
            if (r115 == null) goto L71;
            r103 = r115;
        L71:
            String r104 = FileLoader.getDocumentFileName(r103);     // Catch: Exception -> L120
            if (TextUtils.isEmpty(r104) == true) goto L74;
        L76:
            File r116 = new File(r13, r104);     // Catch: Exception -> L120
            if (r116.exists() == false) goto L91;
            int r124 = r104.lastIndexOf(46);     // Catch: Exception -> L120
            int r132 = 0;
        L81:
            if (r132 >= 10) goto L91;
            if (r124 == (-1)) goto L85;
            String r52 = r104.substring(0, r124) + "(" + (r132 + 1) + ")" + r104.substring(r124);     // Catch: Exception -> L120
        L86:
            r116 = new File(r13, r52);     // Catch: Exception -> L120
            if (r116.exists() == false) goto L91;
            r132 = r132 + 1;     // Catch: Exception -> L120
            goto L81
        L85:
            r52 = r104 + "(" + (r132 + 1) + ")";     // Catch: Exception -> L120
        L91:
            if (r116.exists() == true) goto L93;
            r116.createNewFile();     // Catch: Exception -> L120
        L93:
            String r53 = r95.messageOwner.attachPath;     // Catch: Exception -> L120
            if (r95.qualityToSave == null) goto L96;
            r53 = null;
        L96:
            if (r53 == null) goto L103;
            if (r53.length() <= 0) goto L103;
            if (new File(r53).exists() == true) goto L103;
            r53 = null;
        L103:
            if (r95.qualityToSave == null) goto L105;
            r123 = null;
            File r54 = FileLoader.getInstance(this.currentAccount.getCurrentAccount()).getPathToAttach(r95.qualityToSave, null, false, true);     // Catch: Exception -> L120
        L112:
            if (r54.exists() == true) goto L115;
            this.waitingForFile = new CountDownLatch(1);     // Catch: Exception -> L120
            addMessageToLoad(r95);     // Catch: Exception -> L120
            this.waitingForFile.await();     // Catch: Exception -> L120
        L115:
            if (r54.exists() == false) goto L117;
            copyFile(r54, r116, r95.getMimeType());     // Catch: Exception -> L120
            this.copiedFiles++;
            goto L117
        L105:
            r123 = null;
            if (r53 != null) goto L108;
        L109:
            r53 = FileLoader.getInstance(this.currentAccount.getCurrentAccount()).getPathToMessage(r95.messageOwner).toString();     // Catch: Exception -> L120
        L110:
            r54 = new File(r53);     // Catch: Exception -> L120
            goto L112
        L108:
            if (r53.length() != 0) goto L110;
        L74:
            if (r95.isVoice() == false) goto L76;
            r104 = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss", Locale.US).format(new Date()) + ".ogg";     // Catch: Exception -> L120
            goto L76
        L62:
            r13 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);     // Catch: Exception -> L120
            goto L63
        }

        /* JADX WARN: Type inference failed for: r2v18 */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v3, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v1, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r6v5 */
        private boolean processLivePhotoMessage(MessageObject r25) {
            boolean r8 = false;
            if (r25.isLivePhoto() == true) goto L5;
            return false;
        L5:
            TLRPC.MessageMedia r9 = MessageObject.getMedia(r25.messageOwner);
            if (r9 == null) goto L95;
            TLRPC.Photo r0 = r9.photo;
            if (r0 == null) goto L95;
            if (r9.document == null) goto L95;
            TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r0.sizes, AndroidUtilities.getPhotoSize(true), false, null, true);
            if (r3 != null) goto L15;
            return false;
        L15:
            FileLoader r12 = FileLoader.getInstance(this.currentAccount.getCurrentAccount());
            File r13 = r12.getPathToAttach(r3, null, false, true);
            File r14 = r12.getPathToAttach(r9.document, null, false, true);
            if (r13 != null) goto L18;
        L21:
            ?? r2 = 1;
        L22:
            if (r14 != null) goto L24;
        L27:
            ?? r6 = 1;
        L28:
            int r02 = r2 + r6;
            if (r02 <= 0) goto L32;
            this.waitingForFile = new CountDownLatch(r02);
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda5(this, r2, r3, r25, r9.photo, r6, r9.document));
            this.waitingForFile.await();
        L32:
            if (this.cancelled == false) goto L34;
            return true;
        L34:
            if (r13 != null) goto L36;
        L37:
            r13 = r12.getPathToAttach(r3, null, true, true);
        L38:
            if (r14 != null) goto L40;
        L41:
            r14 = r12.getPathToAttach(r9.document, null, true, true);
        L42:
            if (r13 != null) goto L44;
        L94:
            return true;
        L44:
            if (r13.exists() == false) goto L94;
            if (r14 == null) goto L94;
            if (r14.exists() == false) goto L94;
            String r03 = FileLoader.getFileExtension(r13);
            if (TextUtils.isEmpty(r03) == false) goto L52;
            r03 = "jpg";
        L52:
            String r22 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(r03.toLowerCase());
            if (TextUtils.isEmpty(r22) == false) goto L55;
            r22 = "image/jpeg";
        L55:
            String r04 = AndroidUtilities.generateFileName(0, r03);
            if (Build.VERSION.SDK_INT < 29) goto L76;
            ContentValues r32 = new ContentValues();
            Uri r4 = MediaStore.Downloads.getContentUri("external_primary");
            r32.put("relative_path", new File(Environment.DIRECTORY_DOWNLOADS, "Telegram") + File.separator);
            r32.put("_display_name", r04);
            r32.put("mime_type", r22);
            Uri r05 = ApplicationLoader.applicationContext.getContentResolver().insert(r4, r32);
            if (r05 == null) goto L85;
            OutputStream r23 = ApplicationLoader.applicationContext.getContentResolver().openOutputStream(r05);
            if (r23 != null) goto L99;
        L70:
            if (r23 == null) goto L72;
            r23.close();
        L72:
            if (r8 == false) goto L97;
            this.copiedFiles++;
            goto L85
        L97:
            ApplicationLoader.applicationContext.getContentResolver().delete(r05, null, null);     // Catch: Exception -> L96
            goto L85
        L99:
            MediaController.m4353$$Nest$smwriteMotionPhoto(r13, r14, r23, null);     // Catch: Throwable -> L63
            r8 = !this.cancelled;
        L63:
            th = move-exception;
            r23.close();     // Catch: Throwable -> L67
            throw th;
        L67:
            th = move-exception;
            th.addSuppressed(th);
            throw th;
        L85:
            float r06 = this.finishedProgress + (100.0f / this.messageObjects.size());
            this.finishedProgress = r06;
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda6(this, (int) r06));
            return true;
        L76:
            File r33 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Telegram");
            r33.mkdirs();
            File r42 = new File(r33, r04);
            if (r42.exists() == true) goto L79;
            r42.createNewFile();
        L79:
            FileOutputStream r34 = new FileOutputStream(r42);
            MediaController.m4353$$Nest$smwriteMotionPhoto(r13, r14, r34, null);     // Catch: Throwable -> L87
            r34.close();
            if (this.cancelled == false) goto L84;
            r42.delete();
            goto L85
        L84:
            ((DownloadManager) ApplicationLoader.applicationContext.getSystemService("download")).addCompletedDownload(r42.getName(), r42.getName(), false, r22, r42.getAbsolutePath(), r42.length(), true);
            this.copiedFiles++;
        L87:
            th = move-exception;
            r34.close();     // Catch: Throwable -> L91
            throw th;
        L91:
            th = move-exception;
            th.addSuppressed(th);
            throw th;
        L40:
            if (r14.exists() == true) goto L42;
        L36:
            if (r13.exists() == true) goto L38;
        L24:
            if (r14.exists() == false) goto L27;
            r6 = 0;
            goto L28
        L18:
            if (r13.exists() == false) goto L21;
            r2 = 0;
        L95:
            return false;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            if (r3 == NotificationCenter.fileLoaded) goto L14;
            if (r3 == NotificationCenter.fileLoadFailed) goto L14;
            if (r3 == NotificationCenter.fileLoadProgressChanged) goto L10;
            return;
        L10:
            if (this.loadingMessageObjects.containsKey((String) r5[0]) == false) goto L18;
            Long r32 = (Long) r5[1];
            Long r42 = (Long) r5[2];
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda0(this, (int) (this.finishedProgress + (((r32.longValue() / r42.longValue()) / this.messageObjects.size()) * 100.0f))));
            return;
        L18:
            return;
        L14:
            if (this.loadingMessageObjects.remove((String) r5[0]) == null) goto L19;
            this.waitingForFile.countDown();
            return;
        }

        public void start() {
            AndroidUtilities.runOnUIThread(new MediaController$MediaLoader$$ExternalSyntheticLambda10(this), 250);
            new Thread(new MediaController$MediaLoader$$ExternalSyntheticLambda11(this)).start();
        }
    }

    /* loaded from: classes5.dex */
    public static class MusicListenReporter {
        private TLRPC.InputDocument audio;
        public final int currentAccount;
        private long rangeStart;
        private final ArrayList<Pair<Long, Long>> ranges;
        private final Runnable reportRunnable;

        /* renamed from: org.telegram.messenger.MediaController$MusicListenReporter$1, reason: invalid class name */
        public class AnonymousClass1 implements Player.Listener {
            final /* synthetic */ MusicListenReporter this$0;
            final /* synthetic */ ExoPlayer val$player;

            public AnonymousClass1(MusicListenReporter r1, ExoPlayer r2) {
                this.this$0 = r1;
                this.val$player = r2;
            }

            private void closeRange() {
                long r0 = this.val$player.getCurrentPosition();
                if (MusicListenReporter.m4364$$Nest$fgetrangeStart(this.this$0) != (-9223372036854775807L)) goto L5;
            L7:
                MusicListenReporter.m4366$$Nest$fputrangeStart(this.this$0, -9223372036854775807L);
                return;
            L5:
                if (r0 <= MusicListenReporter.m4364$$Nest$fgetrangeStart(this.this$0)) goto L7;
                MusicListenReporter r2 = this.this$0;
                MusicListenReporter.m4367$$Nest$mlistenedRange(r2, MusicListenReporter.m4364$$Nest$fgetrangeStart(r2), r0);
                goto L7
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onAudioAttributesChanged(AudioAttributes r1) {
                super.onAudioAttributesChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onAudioSessionIdChanged(int r1) {
                super.onAudioSessionIdChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onAvailableCommandsChanged(Player.Commands r1) {
                super.onAvailableCommandsChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onCues(CueGroup r1) {
                super.onCues(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            @Deprecated
            public /* bridge */ /* synthetic */ void onCues(List r1) {
                super.onCues(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onDeviceInfoChanged(DeviceInfo r1) {
                super.onDeviceInfoChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onDeviceVolumeChanged(int r1, boolean r2) {
                super.onDeviceVolumeChanged(r1, r2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onEvents(Player r1, Player.Events r2) {
                super.onEvents(r1, r2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onIsLoadingChanged(boolean r1) {
                super.onIsLoadingChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onIsPlayingChanged(boolean r4) {
                if (r4 == false) goto L4;
                MusicListenReporter.m4366$$Nest$fputrangeStart(this.this$0, this.val$player.getCurrentPosition());
            L5:
                AndroidUtilities.cancelRunOnUIThread(MusicListenReporter.m4365$$Nest$fgetreportRunnable(this.this$0));
                if (r4 == true) goto L9;
                AndroidUtilities.runOnUIThread(MusicListenReporter.m4365$$Nest$fgetreportRunnable(this.this$0), 60000);
                return;
            L9:
                return;
            L4:
                closeRange();
                goto L5
            }

            @Override // androidx.media3.common.Player.Listener
            @Deprecated
            public /* bridge */ /* synthetic */ void onLoadingChanged(boolean r1) {
                super.onLoadingChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onMaxSeekToPreviousPositionChanged(long r1) {
                super.onMaxSeekToPreviousPositionChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onMediaItemTransition(MediaItem r1, int r2) {
                super.onMediaItemTransition(r1, r2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onMediaMetadataChanged(MediaMetadata r1) {
                super.onMediaMetadataChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onMetadata(Metadata r1) {
                super.onMetadata(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onPlayWhenReadyChanged(boolean r1, int r2) {
                super.onPlayWhenReadyChanged(r1, r2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters r1) {
                super.onPlaybackParametersChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onPlaybackStateChanged(int r1) {
                super.onPlaybackStateChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onPlaybackSuppressionReasonChanged(int r1) {
                super.onPlaybackSuppressionReasonChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onPlayerError(PlaybackException r1) {
                super.onPlayerError(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onPlayerErrorChanged(PlaybackException r1) {
                super.onPlayerErrorChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            @Deprecated
            public /* bridge */ /* synthetic */ void onPlayerStateChanged(boolean r1, int r2) {
                super.onPlayerStateChanged(r1, r2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata r1) {
                super.onPlaylistMetadataChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            @Deprecated
            public /* bridge */ /* synthetic */ void onPositionDiscontinuity(int r1) {
                super.onPositionDiscontinuity(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPositionDiscontinuity(Player.PositionInfo r7, Player.PositionInfo r8, int r9) {
                if (r9 != 1) goto L12;
                long r2 = -9223372036854775807L;
                if (MusicListenReporter.m4364$$Nest$fgetrangeStart(this.this$0) == (-9223372036854775807L)) goto L7;
                MusicListenReporter r92 = this.this$0;
                MusicListenReporter.m4367$$Nest$mlistenedRange(r92, MusicListenReporter.m4364$$Nest$fgetrangeStart(r92), r7.positionMs);
            L7:
                MusicListenReporter r72 = this.this$0;
                if (this.val$player.isPlaying() == false) goto L10;
                r2 = r8.positionMs;
            L10:
                MusicListenReporter.m4366$$Nest$fputrangeStart(r72, r2);
                AndroidUtilities.cancelRunOnUIThread(MusicListenReporter.m4365$$Nest$fgetreportRunnable(this.this$0));
                return;
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onRenderedFirstFrame() {
                super.onRenderedFirstFrame();
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onRepeatModeChanged(int r1) {
                super.onRepeatModeChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onSeekBackIncrementChanged(long r1) {
                super.onSeekBackIncrementChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onSeekForwardIncrementChanged(long r1) {
                super.onSeekForwardIncrementChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onShuffleModeEnabledChanged(boolean r1) {
                super.onShuffleModeEnabledChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onSkipSilenceEnabledChanged(boolean r1) {
                super.onSkipSilenceEnabledChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onSurfaceSizeChanged(int r1, int r2) {
                super.onSurfaceSizeChanged(r1, r2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onTimelineChanged(Timeline r1, int r2) {
                super.onTimelineChanged(r1, r2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters r1) {
                super.onTrackSelectionParametersChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onTracksChanged(Tracks r1) {
                super.onTracksChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onVideoSizeChanged(VideoSize r1) {
                super.onVideoSizeChanged(r1);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* bridge */ /* synthetic */ void onVolumeChanged(float r1) {
                super.onVolumeChanged(r1);
            }
        }

        public static /* synthetic */ void $r8$lambda$kA47e4H5dEjSfot6AWPpsxdcegQ(MusicListenReporter r0) {
            r0.report();
        }

        /* renamed from: -$$Nest$fgetrangeStart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4364$$Nest$fgetrangeStart(MusicListenReporter r2) {
            return r2.rangeStart;
        }

        /* renamed from: -$$Nest$fgetreportRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m4365$$Nest$fgetreportRunnable(MusicListenReporter r0) {
            return r0.reportRunnable;
        }

        /* renamed from: -$$Nest$fputrangeStart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4366$$Nest$fputrangeStart(MusicListenReporter r0, long r1) {
            r0.rangeStart = r1;
        }

        /* renamed from: -$$Nest$mlistenedRange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4367$$Nest$mlistenedRange(MusicListenReporter r0, long r1, long r3) {
            r0.listenedRange(r1, r3);
        }

        public MusicListenReporter(int r3) {
            this.rangeStart = -9223372036854775807L;
            this.ranges = new ArrayList();
            this.reportRunnable = new MediaController$MusicListenReporter$$ExternalSyntheticLambda0(this);
            this.currentAccount = r3;
        }

        private long getTotalListened() {
            ArrayList<Pair<Long, Long>> r9 = this.ranges;
            int r0 = r9.size();
            long r1 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r0) goto L5;
            Pair<Long, Long> r4 = r9.get(r3);
            r3 = r3 + 1;
            Pair<Long, Long> r42 = r4;
            r1 = r1 + (((Long) r42.second).longValue() - ((Long) r42.first).longValue());
            goto L3
        L5:
            return r1;
        }

        private void listenedRange(long r6, long r8) {
            int r1 = 0;
        L4:
            if (r1 >= this.ranges.size()) goto L8;
            if (((Long) this.ranges.get(r1).first).longValue() > r6) goto L8;
            r1 = r1 + 1;
        L8:
            this.ranges.add(r1, new Pair(Long.valueOf(r6), Long.valueOf(r8)));
            int r62 = Math.max(0, r1 - 1);
        L10:
            if (r62 >= (this.ranges.size() - 1)) goto L15;
            Pair<Long, Long> r7 = this.ranges.get(r62);
            int r9 = r62 + 1;
            Pair<Long, Long> r82 = this.ranges.get(r9);
            if (((Long) r7.second).longValue() >= ((Long) r82.first).longValue()) goto L13;
            r62 = r9;
            goto L10
        L13:
            this.ranges.set(r62, new Pair((Long) r7.first, Long.valueOf(Math.max(((Long) r7.second).longValue(), ((Long) r82.second).longValue()))));
            this.ranges.remove(r9);
            goto L10
        }

        private void report() {
            AndroidUtilities.cancelRunOnUIThread(this.reportRunnable);
            if (this.audio != null) goto L6;
            return;
        L6:
            if (getTotalListened() >= 3000) goto L8;
            return;
        L8:
            TLRPC.TL_messages_reportMusicListen r0 = new TLRPC.TL_messages_reportMusicListen();
            r0.id = this.audio;
            r0.listened_duration = (int) Math.round(getTotalListened() / 1000.0d);
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
            this.rangeStart = -9223372036854775807L;
            this.ranges.clear();
        }

        public void destroy() {
            if (this.audio != null) goto L5;
            return;
        L5:
            report();
            this.audio = null;
        }

        public Player.Listener getPlayerListener(ExoPlayer r2) {
            return new AnonymousClass1(this, r2);
        }

        public void setup(TLRPC.InputDocument r5) {
            AndroidUtilities.cancelRunOnUIThread(this.reportRunnable);
            if (r5 != null) goto L5;
        L7:
            this.audio = r5;
            this.rangeStart = -9223372036854775807L;
            this.ranges.clear();
            return;
        L5:
            if (r5.id != 0) goto L7;
            r5 = null;
            goto L7
        }
    }

    /* loaded from: classes5.dex */
    public static class PhotoEntry extends MediaEditState {
        private static DispatchQueue livePhotoQueue;
        public int bucketId;
        public boolean canDeleteAfter;
        public long dateTaken;
        public Boolean discardLivePhoto;
        public int duration;
        public String emoji;
        public TLRPC.VideoSize emojiMarkup;
        public int gradientBottomColor;
        public int gradientTopColor;
        public boolean hasSpoiler;
        public int height;
        public int imageId;
        public int invert;
        public boolean isAttachSpoilerRevealed;
        public boolean isChatPreviewSpoilerRevealed;
        public boolean isLivePhoto;
        public boolean isMuted;
        private Runnable livePhotoParseRunnable;
        public long livePhotoTimestampUs;
        public int orientation;
        private boolean parsedXmp;
        public String path;
        public long size;
        public long starsAmount;
        public BitmapDrawable thumb;
        public int videoOrientation;
        public int width;

        public static /* synthetic */ void $r8$lambda$00rTUQG4eGysbgHZO8BUAfWCv4Y(PhotoEntry r0, long[] r1, Runnable r2) {
            r0.lambda$isLivePhotoAsync$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$5uL1E1cylqV2HhYfXapshlkeIGI(PhotoEntry r0, String r1, Runnable r2) {
            r0.lambda$isLivePhotoAsync$1(r1, r2);
        }

        public static /* synthetic */ Bitmap $r8$lambda$Dg9GKh3ZBT33Nm4pT0eG3r8mFP8(PhotoEntry r0, BitmapFactory.Options r1) {
            return r0.lambda$rebuildPhoto$2(r1);
        }

        public PhotoEntry(int r2, int r3, long r4, String r6, int r7, int r8, boolean r9, int r10, int r11, long r12) {
            this.videoOrientation = -1;
            this.bucketId = r2;
            this.imageId = r3;
            this.dateTaken = r4;
            this.path = r6;
            this.width = r10;
            this.height = r11;
            this.size = r12;
            this.duration = r8;
            this.orientation = r7;
            this.isVideo = r9;
        }

        public PhotoEntry(int r2, int r3, long r4, String r6, int r7, boolean r8, int r9, int r10, long r11) {
            this.videoOrientation = -1;
            this.bucketId = r2;
            this.imageId = r3;
            this.dateTaken = r4;
            this.path = r6;
            this.width = r9;
            this.height = r10;
            this.size = r11;
            if (r8 == false) goto L5;
            this.duration = r7;
        L6:
            this.isVideo = r8;
            return;
        L5:
            this.orientation = r7;
            goto L6
        }

        private void applyLivePhoto(long[] r4) {
            this.parsedXmp = true;
            if (r4 == null) goto L6;
            this.isVideo = true;
            this.isLivePhoto = true;
            this.livePhotoVideoOffset = r4[0];
            this.livePhotoTimestampUs = r4[1];
            return;
        L6:
            this.isLivePhoto = false;
        }

        private /* synthetic */ void lambda$isLivePhotoAsync$0(long[] r2, Runnable r3) {
            this.livePhotoParseRunnable = null;
            if (this.parsedXmp == true) goto L5;
            applyLivePhoto(r2);
        L5:
            r3.run();
        }

        private /* synthetic */ void lambda$isLivePhotoAsync$1(String r2, Runnable r3) {
            AndroidUtilities.runOnUIThread(new MediaController$PhotoEntry$$ExternalSyntheticLambda0(this, parseLivePhoto(r2), r3));
        }

        private /* synthetic */ Bitmap lambda$rebuildPhoto$2(BitmapFactory.Options r2) {
            String r0 = this.filterPath;
            if (r0 != null) goto L7;
            r0 = this.path;
        L7:
            return BitmapFactory.decodeFile(r0, r2);
        }

        private static long[] parseLivePhoto(String r12) {
            long r0 = System.currentTimeMillis();
            boolean r2 = true;
            long[] r4 = null;
            String r5 = new ExifInterface(new File(r12)).getAttribute("Xmp");     // Catch: Exception -> L17
            if (r5 == null) goto L30;
            MotionPhotoDescription r52 = XmpMotionPhotoDescriptionParser.parse(r5);     // Catch: Exception -> L17
            if (r52 == null) goto L30;
            int r6 = 0;
            MotionPhotoDescription.ContainerItem r7 = null;
            MotionPhotoDescription.ContainerItem r8 = null;
        L9:
            if (r6 >= r52.items.size()) goto L19;
            MotionPhotoDescription.ContainerItem r9 = r52.items.get(r6);     // Catch: Exception -> L17
            if ("Primary".equalsIgnoreCase(r9.semantic) == false) goto L14;
            r7 = r9;
        L16:
            r6 = r6 + 1;     // Catch: Exception -> L17
            goto L9
        L14:
            if ("MotionPhoto".equalsIgnoreCase(r9.semantic) == false) goto L16;
            r8 = r9;
            goto L16
        L19:
            if (r7 == null) goto L30;
            if (r8 == null) goto L30;
            if (r8.length <= 0) goto L30;
            r4 = new long[]{new File(r12).length() - r8.length, r52.photoPresentationTimestampUs};     // Catch: Exception -> L26
            goto L30
        L26:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L17
        L30:
            StringBuilder r122 = new StringBuilder("parsed isLivePhoto()=");
            if (r4 != null) goto L34;
            r2 = false;
        L34:
            r122.append(r2);
            r122.append(" in ");
            r122.append(System.currentTimeMillis() - r0);
            r122.append("ms");
            FileLog.d(r122.toString());
            return r4;
        L17:
            e = move-exception;
            FileLog.e(e);
            goto L30
        }

        public void cancelLivePhotoParse() {
            Runnable r0 = this.livePhotoParseRunnable;
            if (r0 == null) goto L6;
            livePhotoQueue.cancelRunnable(r0);
            this.livePhotoParseRunnable = null;
            return;
        }

        public /* bridge */ /* synthetic */ Object clone() {
            return m4368clone();
        }

        /* renamed from: clone, reason: collision with other method in class */
        public PhotoEntry m4368clone() {
            PhotoEntry r0 = new PhotoEntry(this.bucketId, this.imageId, this.dateTaken, this.path, this.orientation, this.duration, this.isVideo, this.width, this.height, this.size);
            r0.invert = this.invert;
            r0.isMuted = this.isMuted;
            r0.canDeleteAfter = this.canDeleteAfter;
            r0.hasSpoiler = this.hasSpoiler;
            r0.starsAmount = this.starsAmount;
            r0.isChatPreviewSpoilerRevealed = this.isChatPreviewSpoilerRevealed;
            r0.isAttachSpoilerRevealed = this.isAttachSpoilerRevealed;
            r0.emojiMarkup = this.emojiMarkup;
            r0.gradientTopColor = this.gradientTopColor;
            r0.gradientBottomColor = this.gradientBottomColor;
            r0.discardLivePhoto = this.discardLivePhoto;
            r0.copyFrom(this);
            return r0;
        }

        @Override // org.telegram.messenger.MediaController.MediaEditState
        public void copyFrom(MediaEditState r8) {
            super.copyFrom(r8);
            boolean r0 = r8 instanceof PhotoEntry;
            boolean r1 = false;
            if (r0 == true) goto L5;
        L7:
            boolean r3 = false;
        L8:
            this.hasSpoiler = r3;
            long r32 = 0;
            if (r0 == false) goto L11;
            long r5 = ((PhotoEntry) r8).starsAmount;
        L12:
            this.starsAmount = r5;
            if (r0 == true) goto L15;
        L17:
            boolean r52 = false;
        L18:
            this.parsedXmp = r52;
            if (r0 == true) goto L21;
        L23:
            this.isLivePhoto = r1;
            if (r0 == false) goto L26;
            long r12 = ((PhotoEntry) r8).livePhotoVideoOffset;
        L27:
            this.livePhotoVideoOffset = r12;
            if (r0 == false) goto L30;
            r32 = ((PhotoEntry) r8).livePhotoTimestampUs;
        L30:
            this.livePhotoTimestampUs = r32;
            return;
        L26:
            r12 = 0;
            goto L27
        L21:
            if (((PhotoEntry) r8).isLivePhoto == false) goto L23;
            r1 = true;
            goto L23
        L15:
            if (((PhotoEntry) r8).parsedXmp == false) goto L17;
            r52 = true;
            goto L18
        L11:
            r5 = 0;
            goto L12
        L5:
            if (((PhotoEntry) r8).hasSpoiler == false) goto L7;
            r3 = true;
            goto L8
        }

        public void deleteAll() {
            if (this.path == null) goto L6;
            new File(this.path).delete();     // Catch: Exception -> L21
        L6:
            if (this.fullPaintPath == null) goto L9;
            new File(this.fullPaintPath).delete();     // Catch: Exception -> L22
        L9:
            if (this.paintPath == null) goto L12;
            new File(this.paintPath).delete();     // Catch: Exception -> L23
        L12:
            if (this.imagePath == null) goto L15;
            new File(this.imagePath).delete();     // Catch: Exception -> L24
        L15:
            if (this.filterPath == null) goto L18;
            new File(this.filterPath).delete();     // Catch: Exception -> L25
        L18:
            if (this.croppedPaintPath == null) goto L39;
            new File(this.croppedPaintPath).delete();     // Catch: Exception -> L26
            return;
        L40:
            return;
        }

        @Override // org.telegram.messenger.MediaController.MediaEditState
        public String getPath() {
            return this.path;
        }

        @Override // org.telegram.messenger.MediaController.MediaEditState
        public boolean isLivePhoto() {
            if (this.isVideo == true) goto L10;
            if (this.parsedXmp == true) goto L10;
            applyLivePhoto(parseLivePhoto(this.path));
            return this.isLivePhoto;
        L10:
            return this.isLivePhoto;
        }

        public boolean isLivePhotoAsync(Runnable r3) {
            if (this.isVideo == true) goto L16;
            if (this.parsedXmp == true) goto L16;
            if (this.livePhotoParseRunnable == null) goto L10;
            return false;
        L10:
            if (livePhotoQueue != null) goto L12;
            livePhotoQueue = new DispatchQueue("livePhotoQueue");
        L12:
            MediaController$PhotoEntry$$ExternalSyntheticLambda1 r1 = new MediaController$PhotoEntry$$ExternalSyntheticLambda1(this, this.path, r3);
            this.livePhotoParseRunnable = r1;
            livePhotoQueue.postRunnable(r1);
            return false;
        L16:
            return this.isLivePhoto;
        }

        public boolean isUnalivePhoto() {
            Boolean r0 = this.discardLivePhoto;
            if (r0 != null) goto L7;
            return !SharedConfig.photoLiveDefault;
        L7:
            return r0.booleanValue();
        }

        public void rebuildPhoto(boolean r19) {
            String r1 = this.filterPath;
            if (r1 != null) goto L6;
            r1 = this.path;
        L6:
            Pair<Integer, Integer> r12 = AndroidUtilities.getImageOrientation(r1);
            Bitmap.CompressFormat r3 = Bitmap.CompressFormat.JPEG;
            Bitmap r11 = StoryEntry.getScaledBitmap(new MediaController$PhotoEntry$$ExternalSyntheticLambda2(this), AndroidUtilities.getPhotoSize(r19), AndroidUtilities.getPhotoSize(r19), false, true);
            if (this.imagePath == null) goto L9;
            new File(this.imagePath).delete();
            this.imagePath = null;
        L9:
            CropState r2 = this.cropState;
            if (r2 == null) goto L14;
            Bitmap r13 = PhotoViewer.createCroppedBitmap(r11, r2, new int[]{((Integer) r12.first).intValue(), ((Integer) r12.second).intValue()}, true);
            AndroidUtilities.recycleBitmap(r11);
        L12:
            Bitmap r22 = r13;
        L23:
            String r14 = this.fullPaintPath;
            int r5 = 87;
            if (r14 != null) goto L31;
            float r4 = AndroidUtilities.getPhotoSize(r19);
            float r15 = AndroidUtilities.getPhotoSize(r19);
            if (r19 == false) goto L28;
            int r6 = 99;
        L29:
            this.imagePath = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(ImageLoader.scaleAndSaveImage(r22, r3, r4, r15, r6, false, 101, 101), true).toString();
        L43:
            if (r22 == null) goto L48;
            r22.recycle();
            return;
        L48:
            return;
        L28:
            r6 = 87;
            goto L29
        L31:
            if (this.cropState == null) goto L33;
            Bitmap r16 = BitmapFactory.decodeFile(r14);
            Bitmap r42 = PhotoViewer.createCroppedBitmap(r16, this.cropState, null, false);
            AndroidUtilities.recycleBitmap(r16);
        L46:
            Paint r17 = new Paint(3);     // Catch: Exception -> L39
            Bitmap r62 = Bitmap.createBitmap(r22.getWidth(), r22.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Exception -> L39
            Canvas r8 = new Canvas(r62);     // Catch: Exception -> L39
            r8.drawBitmap(r22, 0.0f, 0.0f, r17);     // Catch: Exception -> L39
            r8.scale(r22.getWidth() / r42.getWidth(), r22.getHeight() / r42.getHeight());     // Catch: Exception -> L39
            r8.drawBitmap(r42, 0.0f, 0.0f, r17);     // Catch: Exception -> L39
            this.imagePath = PhotoViewer.getTempFileAbsolutePath();     // Catch: Exception -> L39
            if (r19 == false) goto L37;
            r5 = 99;
        L37:
            r62.compress(r3, r5, new FileOutputStream(this.imagePath));     // Catch: Exception -> L39
        L41:
            if (r42 == null) goto L43;
            r42.recycle();
        L39:
            e = move-exception;
            FileLog.e(e);
            goto L41
        L33:
            r42 = BitmapFactory.decodeFile(r14);
            goto L46
        L14:
            if (((Integer) r12.first).intValue() == 0) goto L22;
            Matrix r23 = new Matrix();
            r23.postRotate(((Integer) r12.first).intValue());
            if (((Integer) r12.second).intValue() != 1) goto L19;
            r23.postScale(-1.0f, 1.0f);
        L21:
            r13 = Bitmaps.createBitmap(r11, 0, 0, r11.getWidth(), r11.getHeight(), r23, true);
            AndroidUtilities.recycleBitmap(r11);
            goto L12
        L19:
            if (((Integer) r12.second).intValue() != 2) goto L21;
            r23.postScale(1.0f, -1.0f);
            goto L21
        L22:
            r22 = r11;
            goto L23
        }

        @Override // org.telegram.messenger.MediaController.MediaEditState
        public void reset() {
            if (this.isVideo == true) goto L5;
        L7:
            this.hasSpoiler = false;
            this.starsAmount = 0;
            super.reset();
            return;
        L5:
            if (this.filterPath == null) goto L7;
            new File(this.filterPath).delete();
            this.filterPath = null;
            goto L7
        }

        public PhotoEntry setOrientation(int r1, int r2) {
            this.orientation = r1;
            this.invert = r2;
            return this;
        }

        public PhotoEntry setOrientation(Pair<Integer, Integer> r2) {
            this.orientation = ((Integer) r2.first).intValue();
            this.invert = ((Integer) r2.second).intValue();
            return this;
        }
    }

    /* loaded from: classes5.dex */
    public static class PlaylistGlobalSearchParams {
        final long dialogId;
        public boolean endReached;
        final FiltersView.MediaFilterData filter;
        public int folderId;
        final long maxDate;
        final long minDate;
        public int nextSearchRate;
        final String query;
        public ReactionsLayoutInBubble.VisibleReaction reaction;
        public long topicId;
        public int totalCount;

        public PlaylistGlobalSearchParams(String r1, long r2, long r4, long r6, FiltersView.MediaFilterData r8) {
            this.filter = r8;
            this.query = r1;
            this.dialogId = r2;
            this.minDate = r4;
            this.maxDate = r6;
        }
    }

    /* loaded from: classes5.dex */
    public static class SavedFilterState {
        public float blurAngle;
        public float blurExcludeBlurSize;
        public PointF blurExcludePoint;
        public float blurExcludeSize;
        public int blurType;
        public float contrastValue;
        public PhotoFilterView.CurvesToolValue curvesToolValue;
        public float enhanceValue;
        public float exposureValue;
        public float fadeValue;
        public float grainValue;
        public float highlightsValue;
        public float saturationValue;
        public float shadowsValue;
        public float sharpenValue;
        public float softenSkinValue;
        public int tintHighlightsColor;
        public int tintShadowsColor;
        public float vignetteValue;
        public float warmthValue;

        public SavedFilterState() {
            this.curvesToolValue = new PhotoFilterView.CurvesToolValue();
        }

        public boolean isEmpty() {
            if (Math.abs(this.enhanceValue) < 0.1f) goto L5;
            return false;
        L5:
            if (Math.abs(this.softenSkinValue) < 0.1f) goto L7;
            return false;
        L7:
            if (Math.abs(this.exposureValue) < 0.1f) goto L9;
            return false;
        L9:
            if (Math.abs(this.contrastValue) < 0.1f) goto L11;
            return false;
        L11:
            if (Math.abs(this.warmthValue) < 0.1f) goto L13;
            return false;
        L13:
            if (Math.abs(this.saturationValue) < 0.1f) goto L15;
            return false;
        L15:
            if (Math.abs(this.fadeValue) < 0.1f) goto L17;
            return false;
        L17:
            if (this.tintShadowsColor == 0) goto L19;
            return false;
        L19:
            if (this.tintHighlightsColor == 0) goto L21;
            return false;
        L21:
            if (Math.abs(this.highlightsValue) < 0.1f) goto L23;
            return false;
        L23:
            if (Math.abs(this.shadowsValue) < 0.1f) goto L25;
            return false;
        L25:
            if (Math.abs(this.vignetteValue) < 0.1f) goto L27;
            return false;
        L27:
            if (Math.abs(this.grainValue) < 0.1f) goto L29;
            return false;
        L29:
            if (this.blurType == 0) goto L31;
            return false;
        L31:
            if (Math.abs(this.sharpenValue) >= 0.1f) goto L49;
            return true;
        L49:
            return false;
        }

        public void readParams(InputSerializedData r3, boolean r4) {
            this.enhanceValue = r3.readFloat(r4);
            this.softenSkinValue = r3.readFloat(r4);
            this.exposureValue = r3.readFloat(r4);
            this.contrastValue = r3.readFloat(r4);
            this.warmthValue = r3.readFloat(r4);
            this.saturationValue = r3.readFloat(r4);
            this.fadeValue = r3.readFloat(r4);
            this.tintShadowsColor = r3.readInt32(r4);
            this.tintHighlightsColor = r3.readInt32(r4);
            this.highlightsValue = r3.readFloat(r4);
            this.shadowsValue = r3.readFloat(r4);
            this.vignetteValue = r3.readFloat(r4);
            this.grainValue = r3.readFloat(r4);
            this.blurType = r3.readInt32(r4);
            this.sharpenValue = r3.readFloat(r4);
            this.curvesToolValue.readParams(r3, r4);
            this.blurExcludeSize = r3.readFloat(r4);
            if (r3.readInt32(r4) != 1450380236) goto L6;
            this.blurExcludePoint = null;
        L9:
            this.blurExcludeBlurSize = r3.readFloat(r4);
            this.blurAngle = r3.readFloat(r4);
            return;
        L6:
            if (this.blurExcludePoint != null) goto L8;
            this.blurExcludePoint = new PointF();
        L8:
            PointF r0 = this.blurExcludePoint;
            r0.x = r3.readFloat(r4);
            PointF r02 = this.blurExcludePoint;
            r02.y = r3.readFloat(r4);
            goto L9
        }

        public void serializeToStream(OutputSerializedData r2) {
            r2.writeFloat(this.enhanceValue);
            r2.writeFloat(this.softenSkinValue);
            r2.writeFloat(this.exposureValue);
            r2.writeFloat(this.contrastValue);
            r2.writeFloat(this.warmthValue);
            r2.writeFloat(this.saturationValue);
            r2.writeFloat(this.fadeValue);
            r2.writeInt32(this.tintShadowsColor);
            r2.writeInt32(this.tintHighlightsColor);
            r2.writeFloat(this.highlightsValue);
            r2.writeFloat(this.shadowsValue);
            r2.writeFloat(this.vignetteValue);
            r2.writeFloat(this.grainValue);
            r2.writeInt32(this.blurType);
            r2.writeFloat(this.sharpenValue);
            this.curvesToolValue.serializeToStream(r2);
            r2.writeFloat(this.blurExcludeSize);
            if (this.blurExcludePoint != null) goto L5;
            r2.writeInt32(TLRPC.TL_null.constructor);
        L6:
            r2.writeFloat(this.blurExcludeBlurSize);
            r2.writeFloat(this.blurAngle);
            return;
        L5:
            r2.writeInt32(-559038737);
            r2.writeFloat(this.blurExcludePoint.x);
            r2.writeFloat(this.blurExcludePoint.y);
            goto L6
        }
    }

    /* loaded from: classes5.dex */
    public static class SavedMusicPlaylistState {
        public final MessageObject playingMessage;
        public final float progress;
        public final int progressMs;
        public final int progressSec;

        public SavedMusicPlaylistState(MessageObject r2) {
            this.playingMessage = r2;
            this.progress = r2.audioProgress;
            this.progressMs = r2.audioProgressMs;
            this.progressSec = r2.audioProgressSec;
        }
    }

    /* loaded from: classes5.dex */
    public static class SearchImage extends MediaEditState {
        public CharSequence caption;
        public int date;
        public TLRPC.Document document;
        public int height;
        public String id;
        public String imageUrl;
        public TLRPC.BotInlineResult inlineResult;
        public HashMap<String, String> params;
        public TLRPC.Photo photo;
        public TLRPC.PhotoSize photoSize;
        public int size;
        public TLRPC.PhotoSize thumbPhotoSize;
        public String thumbUrl;
        public int type;
        public int width;

        public SearchImage() {
        }

        public /* bridge */ /* synthetic */ Object clone() {
            return m4369clone();
        }

        /* renamed from: clone, reason: collision with other method in class */
        public SearchImage m4369clone() {
            SearchImage r0 = new SearchImage();
            r0.id = this.id;
            r0.imageUrl = this.imageUrl;
            r0.thumbUrl = this.thumbUrl;
            r0.width = this.width;
            r0.height = this.height;
            r0.size = this.size;
            r0.type = this.type;
            r0.date = this.date;
            r0.caption = this.caption;
            r0.document = this.document;
            r0.photo = this.photo;
            r0.photoSize = this.photoSize;
            r0.thumbPhotoSize = this.thumbPhotoSize;
            r0.inlineResult = this.inlineResult;
            r0.params = this.params;
            return r0;
        }

        public String getAttachName() {
            TLRPC.PhotoSize r0 = this.photoSize;
            if (r0 != null) goto L5;
            TLRPC.Document r02 = this.document;
            if (r02 == null) goto L11;
            return FileLoader.getAttachFileName(r02);
        L11:
            return Utilities.MD5(this.imageUrl) + "." + ImageLoader.getHttpUrlExtension(this.imageUrl, "jpg");
        L5:
            return FileLoader.getAttachFileName(r0);
        }

        @Override // org.telegram.messenger.MediaController.MediaEditState
        public String getPath() {
            if (this.photoSize == null) goto L7;
            return FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(this.photoSize, true).getAbsolutePath();
        L7:
            if (this.document == null) goto L11;
            return FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(this.document, true).getAbsolutePath();
        L11:
            return ImageLoader.getHttpFilePath(this.imageUrl, "jpg").getAbsolutePath();
        }

        public String getPathToAttach() {
            if (this.photoSize == null) goto L7;
            return FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(this.photoSize, true).getAbsolutePath();
        L7:
            if (this.document == null) goto L11;
            return FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(this.document, true).getAbsolutePath();
        L11:
            return this.imageUrl;
        }

        @Override // org.telegram.messenger.MediaController.MediaEditState
        public void reset() {
            super.reset();
        }
    }

    /* loaded from: classes5.dex */
    public final class StopMediaObserverRunnable implements Runnable {
        public int currentObserverToken;
        final /* synthetic */ MediaController this$0;

        private StopMediaObserverRunnable(MediaController r1) {
            this.this$0 = r1;
            this.currentObserverToken = 0;
        }

        public /* synthetic */ StopMediaObserverRunnable(MediaController r1, MediaControllerIA r2) {
            this(r1);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.currentObserverToken == MediaController.m4324$$Nest$fgetstartObserverToken(this.this$0)) goto L20;
            return;
        L20:
        L9:
            e = move-exception;
            FileLog.e(e);
        L18:
        L15:
            e = move-exception;
            FileLog.e(e);
            return;
        L12:
            if (MediaController.m4298$$Nest$fgetexternalObserver(this.this$0) == null) goto L23;
            ApplicationLoader.applicationContext.getContentResolver().unregisterContentObserver(MediaController.m4298$$Nest$fgetexternalObserver(this.this$0));     // Catch: Exception -> L15
            MediaController.m4332$$Nest$fputexternalObserver(this.this$0, null);     // Catch: Exception -> L15
            return;
        L23:
            return;
        L6:
            if (MediaController.m4301$$Nest$fgetinternalObserver(this.this$0) == null) goto L18;
            ApplicationLoader.applicationContext.getContentResolver().unregisterContentObserver(MediaController.m4301$$Nest$fgetinternalObserver(this.this$0));     // Catch: Exception -> L9
            MediaController.m4333$$Nest$fputinternalObserver(this.this$0, null);     // Catch: Exception -> L9
            goto L18
        }
    }

    /* loaded from: classes5.dex */
    public static class VideoConvertMessage {
        public int currentAccount;
        public boolean foreground;
        public boolean foregroundConversion;
        public MessageObject messageObject;
        public VideoEditedInfo videoEditedInfo;

        public VideoConvertMessage(MessageObject r1, VideoEditedInfo r2, boolean r3, boolean r4) {
            this.messageObject = r1;
            this.currentAccount = r1.currentAccount;
            this.videoEditedInfo = r2;
            this.foreground = r3;
            this.foregroundConversion = r4;
        }
    }

    /* loaded from: classes5.dex */
    public static class VideoConvertRunnable implements Runnable {
        private VideoConvertMessage convertMessage;

        public static /* synthetic */ void $r8$lambda$Crg9xK2aVHTQWtPt_7wiWv_ZWpY(VideoConvertMessage r2) {
            Thread r22 = new Thread(new VideoConvertRunnable(r2), "VideoConvertRunnable");     // Catch: Exception -> L4
            r22.start();     // Catch: Exception -> L4
            r22.join();     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private VideoConvertRunnable(VideoConvertMessage r1) {
            this.convertMessage = r1;
        }

        public static void runConversion(VideoConvertMessage r2) {
            new Thread(new MediaController$VideoConvertRunnable$$ExternalSyntheticLambda0(r2)).start();
        }

        @Override // java.lang.Runnable
        public void run() {
            MediaController.m4341$$Nest$mconvertVideo(MediaController.getInstance(), this.convertMessage);
        }
    }

    /* loaded from: classes5.dex */
    public interface VideoConvertorListener {
        boolean checkConversionCanceled();

        void didWriteData(long r1, float r3);
    }

    /* renamed from: $r8$lambda$00LAc-te_MyuDD_hStma3pa7wwQ, reason: not valid java name */
    public static /* synthetic */ void m4262$r8$lambda$00LActe_MyuDD_hStma3pa7wwQ(MediaController r0, File r1, File r2, TLRPC.TL_document r3, int r4, boolean r5, int r6, boolean r7, long r8) {
        r0.lambda$stopRecordingInternal$41(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$057gPge8C_rPr9A5B_esidXfdWk(MediaController r0, boolean r1) {
        r0.lambda$toggleRecordingPause$28(r1);
    }

    /* renamed from: $r8$lambda$36O-PDBkPqVgFg07j9c9MatZfKg, reason: not valid java name */
    public static /* synthetic */ void m4263$r8$lambda$36OPDBkPqVgFg07j9c9MatZfKg(MediaController r0) {
        r0.lambda$toggleRecordingPause$30();
    }

    public static /* synthetic */ void $r8$lambda$3m60tvf41CYbaMQV_jiFM5aG9k8(MediaController r0, int r1, long r2) {
        r0.lambda$prepareResumedRecording$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4QdWAKyR54BA9VB5AgarSBeLEdg(MediaController r0, int r1) {
        r0.lambda$onAudioFocusChange$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$78qytVABInrbJVlVhaJWay85dYM(MediaController r0, VideoPlayer r1, ValueAnimator r2) {
        r0.lambda$cleanupPlayer$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7OHGGBExaQI90Xs3roJefq9yTgA(Utilities.Callback r0, Uri r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$ADl2rW_JCC0MM-rdGTS0XP0ann8, reason: not valid java name */
    public static /* synthetic */ void m4264$r8$lambda$ADl2rW_JCC0MMrdGTS0XP0ann8(MediaController r0, File r1, TLRPC.TL_document r2, int r3, boolean r4, int r5, boolean r6, long r7) {
        r0.lambda$stopRecordingInternal$40(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$BJxC2Dv3ZM_IbgkHpYDZ7MMbWSg(Utilities.Callback r0, Uri r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$By-l_0Dl0Qq7DuRICzkQUsh3CnQ, reason: not valid java name */
    public static /* synthetic */ void m4265$r8$lambda$Byl_0Dl0Qq7DuRICzkQUsh3CnQ(MessageObject r2, File r3) {
        NotificationCenter.getInstance(r2.currentAccount).postNotificationName(NotificationCenter.fileLoaded, new Object[]{FileLoader.getAttachFileName(r2.getDocument()), r3});
    }

    public static /* synthetic */ void $r8$lambda$CYnv33aKAuKoQQyMiCiclxwC8cc(MediaController r0, boolean r1, boolean r2, VideoConvertMessage r3, File r4, float r5, long r6, boolean r8, long r9) {
        r0.lambda$didWriteData$59(r1, r2, r3, r4, r5, r6, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$CjXRLtZA08b43nTFXIRvNYi28tE(boolean[] r1, DialogInterface r2) {
        r1[0] = true;
    }

    public static /* synthetic */ void $r8$lambda$D4VdAMCfM5dMojmJEN4x3u3qAwI(MediaController r0, File r1, long r2, long r4, Runnable r6) {
        r0.lambda$trimCurrentRecording$26(r1, r2, r4, r6);
    }

    /* renamed from: $r8$lambda$EfyOJx3WBhrqa3-GlFXXf0iTCYI, reason: not valid java name */
    public static /* synthetic */ void m4266$r8$lambda$EfyOJx3WBhrqa3GlFXXf0iTCYI(MediaController r0, File r1) {
        r0.lambda$playEmojiSound$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$EnNDqSGKQ6l2vRYLinPye9dmXvM(MediaController r0, int r1, int r2, long r3, long r5, MessageSuggestionParams r7, MessageObject r8, MessageObject r9, TL_stories.StoryItem r10, SendMessageChatArguments r11) {
        r0.lambda$startRecording$37(r1, r2, r3, r5, r7, r8, r9, r10, r11);
    }

    /* renamed from: $r8$lambda$Exw5ym4vdWZIM8T1-MLNrMoyw5M, reason: not valid java name */
    public static /* synthetic */ void m4267$r8$lambda$Exw5ym4vdWZIM8T1MLNrMoyw5M(MediaController r0, int r1, boolean r2, int r3, boolean r4, long r5) {
        r0.lambda$stopRecording$43(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$HfAXr5RDjOOWnF9N0AQW_IQE3uM(MediaController r0) {
        r0.lambda$playMessage$20();
    }

    public static /* synthetic */ void $r8$lambda$I5n5O3fhxSGp8aHEIaMfpeoOmaI(int r12) {
    L14:
        th = th;
        Cursor r4 = null;
    L55:
        FileLog.e(th);     // Catch: Throwable -> L50
        if (r4 == null) goto L23;
        r4.close();
    L23:
        Cursor r5 = r4;
        int r42 = 0;
    L61:
    L32:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L45
        if (r5 != null) goto L35;
    L39:
        if (r12 == r42) goto L65;
        Runnable r122 = refreshGalleryRunnable;
        if (r122 == null) goto L43;
        AndroidUtilities.cancelRunOnUIThread(r122);
        refreshGalleryRunnable = null;
    L43:
        loadGalleryPhotosAlbums(0);
        return;
    L65:
        return;
    L35:
        r5.close();
    L45:
        th = move-exception;
        if (r5 == null) goto L66;
        r5.close();
        throw th;
    L66:
        throw th;
    L25:
        if (SystemUtils.isVideoPermissionGranted() == false) goto L34;
        r5 = MediaStore.Images.Media.query(ApplicationLoader.applicationContext.getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"COUNT(_id)"}, null, null, null);     // Catch: Throwable -> L32
        if (r5 == null) goto L34;
        if (r5.moveToNext() == false) goto L34;
        r42 = r42 + r5.getInt(0);
    L34:
        if (r5 == null) goto L39;
    L50:
        th = move-exception;
        if (r4 == null) goto L67;
        r4.close();
        throw th;
    L67:
        throw th;
    L4:
        if (SystemUtils.isImagesPermissionGranted() == false) goto L16;
        r4 = MediaStore.Images.Media.query(ApplicationLoader.applicationContext.getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"COUNT(_id)"}, null, null, null);     // Catch: Throwable -> L14
        if (r4 != null) goto L59;
    L13:
        int r0 = 0;
    L17:
        if (r4 == null) goto L19;
        r4.close();
    L19:
        r5 = r4;
        r42 = r0;
        goto L61
    L59:
    L11:
        th = th;
        goto L55
    L8:
        if (r4.moveToNext() == false) goto L13;
        r0 = r4.getInt(0);     // Catch: Throwable -> L11
        goto L17
    L16:
        r0 = 0;
        r4 = null;
        goto L17
    }

    public static /* synthetic */ void $r8$lambda$Ith757hCJBQtjsnPqkBJYmcJDD8(MediaController r0, int r1, int r2) {
        r0.lambda$startRecording$33(r1, r2);
    }

    /* renamed from: $r8$lambda$IuG4X-oF80Pc-ZLe6Twx9LTUq-8, reason: not valid java name */
    public static /* synthetic */ void m4268$r8$lambda$IuG4XoF80PcZLe6Twx9LTUq8(AccountInstance r2, TLRPC.Document r3) {
        r2.getFileLoader().loadFile(r3, null, 1, 1);
    }

    /* renamed from: $r8$lambda$Iw__r2i1TFcSX1J2MWUisW-w0cI, reason: not valid java name */
    public static /* synthetic */ void m4269$r8$lambda$Iw__r2i1TFcSX1J2MWUisWw0cI(boolean[] r1, AlertDialog r2) {
        if (r1[0] == true) goto L6;
        r2.show();
        return;
    }

    /* renamed from: $r8$lambda$IxLSrgy81FsFV2gg-4FCTlN9Q2g, reason: not valid java name */
    public static /* synthetic */ void m4270$r8$lambda$IxLSrgy81FsFV2gg4FCTlN9Q2g(MediaController r0) {
        r0.lambda$toggleRecordingPause$29();
    }

    /* renamed from: $r8$lambda$J-7vMw33DU1i23_XxAxhzhmphus, reason: not valid java name */
    public static /* synthetic */ void m4271$r8$lambda$J7vMw33DU1i23_XxAxhzhmphus(MediaController r0) {
        r0.lambda$setTextureView$15();
    }

    /* renamed from: $r8$lambda$JBpr5lqshJvBHQX2OB36J-nxQAE, reason: not valid java name */
    public static /* synthetic */ void m4272$r8$lambda$JBpr5lqshJvBHQX2OB36JnxQAE(MediaController r0, File r1, TLRPC.TL_document r2, MediaDataController.DraftVoice r3) {
        r0.lambda$prepareResumedRecording$24(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MiHmKQV9eVHsTGARmUNU2zz87Uw(MediaController r0, int r1, int r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadMoreMusic$12(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$OQsxk9XIl050Oj7GW5MwSMV4U0M(boolean[] r1, DialogInterface r2) {
        r1[0] = true;
    }

    public static /* synthetic */ void $r8$lambda$Q5N9slVmdEuD_WaVRbkRVosrKVc(AlertDialog r0, int r1) {
        r0.setProgress(r1);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$QWe00RaIbvpbhYrPaZcDeBgI2ug(MediaController r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$RCjkv10UfZV_5nVQWOVc_wVrlV8(MediaController r0, boolean r1) {
        r0.lambda$toggleRecordingPause$32(r1);
    }

    /* renamed from: $r8$lambda$S1-eYA6ZVUkGUDD_YKo2_vGv2_k, reason: not valid java name */
    public static /* synthetic */ int m4273$r8$lambda$S1eYA6ZVUkGUDD_YKo2_vGv2_k(PhotoEntry r2, PhotoEntry r3) {
        long r0 = r2.dateTaken;
        long r22 = r3.dateTaken;
        if (r0 >= r22) goto L6;
        return 1;
    L6:
        if (r0 <= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    /* renamed from: $r8$lambda$UtsV44o-cAZ5LGQgMhKcUlhq-DU, reason: not valid java name */
    public static /* synthetic */ void m4274$r8$lambda$UtsV44ocAZ5LGQgMhKcUlhqDU(File r16, File r17, boolean[] r18, Utilities.Callback r19, AlertDialog r20, boolean[] r21) {
        Uri r7 = null;
        boolean r8 = false;
    L19:
        e = move-exception;
        FileLog.e(e);
    L45:
        if (r8 == false) goto L48;
        if (r19 == null) goto L48;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda7(r19, r7));
    L48:
        if (r20 == null) goto L65;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda8(r20, r21));
        return;
    L65:
        return;
    L5:
        if (Build.VERSION.SDK_INT < 29) goto L27;
        String r9 = AndroidUtilities.generateFileName(0, "jpg");     // Catch: Exception -> L19
        ContentValues r10 = new ContentValues();     // Catch: Exception -> L19
        Uri r13 = MediaStore.Images.Media.getContentUri("external_primary");     // Catch: Exception -> L19
        r10.put("relative_path", new File(Environment.DIRECTORY_PICTURES, "Telegram") + File.separator);     // Catch: Exception -> L19
        r10.put("_display_name", r9);     // Catch: Exception -> L19
        r10.put("mime_type", "image/jpeg");     // Catch: Exception -> L19
        r10.put("mime_type", "image/jpeg");     // Catch: Exception -> L19
        Uri r5 = ApplicationLoader.applicationContext.getContentResolver().insert(r13, r10);     // Catch: Exception -> L19
        if (r5 == null) goto L45;
        OutputStream r6 = ApplicationLoader.applicationContext.getContentResolver().openOutputStream(r5);     // Catch: Exception -> L19
        if (r6 != null) goto L54;
    L21:
        if (r6 == null) goto L23;
        r6.close();     // Catch: Exception -> L19
    L23:
        if (r8 == false) goto L60;
        r7 = r5;
        goto L45
    L60:
        ApplicationLoader.applicationContext.getContentResolver().delete(r5, null, null);     // Catch: Exception -> L51
        goto L45
    L54:
        writeMotionPhoto(r16, r17, r6, r18);     // Catch: Throwable -> L12
        r8 = !r18[0];
        goto L21
    L12:
        th = move-exception;
        r6.close();     // Catch: Throwable -> L16
    L63:
        throw th;     // Catch: Exception -> L19
    L16:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L19
        throw th;     // Catch: Exception -> L19
    L27:
        File r52 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Telegram");     // Catch: Exception -> L19
        r52.mkdirs();     // Catch: Exception -> L19
        File r62 = new File(r52, AndroidUtilities.generateFileName(0, "jpg"));     // Catch: Exception -> L19
        if (r62.exists() == true) goto L30;
        r62.createNewFile();     // Catch: Exception -> L19
    L30:
        FileOutputStream r53 = new FileOutputStream(r62);     // Catch: Exception -> L19
        writeMotionPhoto(r16, r17, r53, r18);     // Catch: Throwable -> L37
        r53.close();     // Catch: Exception -> L19
        if (r18[0] == false) goto L35;
        r62.delete();     // Catch: Exception -> L19
        goto L45
    L35:
        AndroidUtilities.addMediaToGallery(r62.getAbsoluteFile());     // Catch: Exception -> L19
        r7 = Uri.fromFile(r62);     // Catch: Exception -> L19
        r8 = true;
        goto L45
    L37:
        th = move-exception;
        r53.close();     // Catch: Throwable -> L41
    L64:
        throw th;     // Catch: Exception -> L19
    L41:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L19
        throw th;     // Catch: Exception -> L19
    }

    public static /* synthetic */ void $r8$lambda$XUdQVVi3TAXjCfSSKuaYI6atyas(MediaController r0, String r1, byte[] r2, MessageObject r3) {
        r0.lambda$generateWaveform$38(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$XbG-4eciNwgTTBCqvOCvchtVEfw, reason: not valid java name */
    public static /* synthetic */ void m4275$r8$lambda$XbG4eciNwgTTBCqvOCvchtVEfw(MediaController r0, int r1, int r2) {
        r0.lambda$startRecording$36(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Y7057WfctkUG2DyxKKPTzzj29pw(MediaController r0) {
        r0.lambda$stopRaiseToEarSensors$9();
    }

    /* renamed from: $r8$lambda$YzZESmkHTPChdR-MgW-MVorXRpc, reason: not valid java name */
    public static /* synthetic */ void m4276$r8$lambda$YzZESmkHTPChdRMgWMVorXRpc(MediaController r0) {
        r0.lambda$new$4();
    }

    public static /* synthetic */ int $r8$lambda$Z0Erp2L8slKvvC3qPEU0fm69vKk(MessageObject r6, MessageObject r7) {
        int r0 = r6.getId();
        int r1 = r7.getId();
        long r2 = r6.messageOwner.grouped_id;
        long r62 = r7.messageOwner.grouped_id;
        if (r0 >= 0) goto L15;
        if (r1 >= 0) goto L15;
        if (r2 == 0) goto L13;
        if (r2 != r62) goto L13;
        int r63 = Integer.compare(r0, r1);
    L11:
        return -r63;
    L13:
        return Integer.compare(r1, r0);
    L15:
        if (r2 == 0) goto L20;
        if (r2 != r62) goto L20;
        r63 = Integer.compare(r1, r0);
    L20:
        return Integer.compare(r0, r1);
    }

    /* renamed from: $r8$lambda$ZHv9PpmmBv9GE4-sgOSGeTdEU60, reason: not valid java name */
    public static /* synthetic */ void m4277$r8$lambda$ZHv9PpmmBv9GE4sgOSGeTdEU60(AlertDialog r1, boolean[] r2) {
    L8:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (r1.isShowing() == false) goto L6;
        r1.dismiss();     // Catch: Exception -> L8
        return;
    L6:
        r2[0] = true;     // Catch: Exception -> L8
    }

    public static /* synthetic */ void $r8$lambda$ZZKFiIuM6xSPtrZT0xrAANwnNb4(MediaController r0) {
        r0.lambda$setCurrentVideoVisible$14();
    }

    public static /* synthetic */ void $r8$lambda$ZZhLyOXmn1whQwXUqsGUjqYAaww(MediaController r0, int r1, MediaDataController.DraftVoice r2, int r3, long r4, long r6, MessageSuggestionParams r8, MessageObject r9, MessageObject r10, TL_stories.StoryItem r11) {
        r0.lambda$prepareResumedRecording$25(r1, r2, r3, r4, r6, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$ZsZkcgMMK5FxVCFJRMifVFmnh7g(int r9, ArrayList r10, ArrayList r11, Integer r12, AlbumEntry r13, AlbumEntry r14, AlbumEntry r15) {
        if (PhotoViewer.getInstance().isVisible() == true) goto L5;
    L8:
        allMediaAlbums = r10;
        allPhotoAlbums = r11;
        broadcastPhotosRunnable = null;
        allPhotosAlbumEntry = r14;
        allMediaAlbumEntry = r13;
        allVideosAlbumEntry = r15;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.albumsDidLoad, new Object[]{Integer.valueOf(r9), r10, r11, r12});
        return;
    L5:
        if (forceBroadcastNewPhotos == true) goto L8;
        broadcastNewPhotos(r9, r10, r11, r12, r13, r14, r15, 1000);
    }

    public static /* synthetic */ void $r8$lambda$byEPNLpvSxIW0zdApievlt74AmU(AlertDialog r0) {
        r0.dismiss();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    /* renamed from: $r8$lambda$dTYFK2ZhvYg7saSSt7xvPakX-gI, reason: not valid java name */
    public static /* synthetic */ void m4278$r8$lambda$dTYFK2ZhvYg7saSSt7xvPakXgI(MediaController r0, MessageObject r1) {
        r0.lambda$startAudioAgain$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$dVsIbUq1IEVA1yps_jU5hTGKhsM(MediaController r0) {
        r0.lambda$new$2();
    }

    /* renamed from: $r8$lambda$dXdA9YelbIgj-hhGsAPXOOWGMUw, reason: not valid java name */
    public static /* synthetic */ void m4279$r8$lambda$dXdA9YelbIgjhhGsAPXOOWGMUw(MediaController r0, MessageObject r1, float r2) {
        r0.lambda$setPlaybackSpeed$16(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$doNHYtZrDNy_eO2ZJKlHW8D5L8U(MediaController r0, String r1, String r2, MessageObject r3) {
        r0.lambda$generateWaveform$39(r1, r2, r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v11 */
    /* JADX WARN: Type inference failed for: r20v12 */
    /* JADX WARN: Type inference failed for: r20v3 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /* renamed from: $r8$lambda$fBsru5e_YyNhMhNroeamuu0p-qA, reason: not valid java name */
    public static /* synthetic */ void m4280$r8$lambda$fBsru5e_YyNhMhNroeamuu0pqA(int r23, File r24, String r25, AlertDialog r26, boolean[] r27, String r28, Utilities.Callback r29, boolean[] r30) {
        boolean r8 = true;
        boolean r9 = false;
        if (Build.VERSION.SDK_INT < 29) goto L10;
        Uri r0 = saveFileInternal(r23, r24, null);     // Catch: Exception -> L8
        if (r0 != null) goto L131;
    L130:
        r8 = r9;
    L131:
        if (r8 == false) goto L136;
        if (r29 == null) goto L136;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda34(r29, r0));     // Catch: Exception -> L8
    L136:
        if (r26 == null) goto L168;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda35(r26, r30));
        return;
    L168:
        return;
    L10:
        String r5 = ExteraConfig.getCustomSavePath();     // Catch: Exception -> L8
        if (r23 != 0) goto L16;
        File r2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);     // Catch: Exception -> L8
        if (TextUtils.isEmpty(r5) == true) goto L15;
        r2 = new File(r2, r5);     // Catch: Exception -> L8
    L15:
        r2.mkdirs();     // Catch: Exception -> L8
        File r52 = new File(r2, AndroidUtilities.generateFileName(0, FileLoader.getFileExtension(r24)));     // Catch: Exception -> L8
    L42:
        if (r52.exists() == true) goto L45;
        r52.createNewFile();     // Catch: Exception -> L8
    L45:
        long r10 = System.currentTimeMillis() - 500;
        FileInputStream r22 = new FileInputStream(r24);     // Catch: Exception -> L117
        FileChannel r15 = r22.getChannel();     // Catch: Throwable -> L110
        FileChannel r14 = new FileOutputStream(r52).getChannel();     // Catch: Throwable -> L102
        boolean r20 = false;
        long r82 = r15.size();     // Catch: Throwable -> L87
    L58:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L87
    L75:
        long r16 = 0;
    L77:
        if (r16 >= r82) goto L91;
        if (r27[0] == true) goto L91;
        r14.transferFrom(r15, r16, Math.min(MediaStatus.COMMAND_EDIT_TRACKS, r82 - r16));     // Catch: Throwable -> L87
        long r12 = r16;
        if (r26 != null) goto L84;
    L89:
        long r242 = 4096;
    L90:
        r16 = r12 + r242;
        goto L77
    L84:
        if (r10 > (System.currentTimeMillis() - 500)) goto L89;
        r10 = System.currentTimeMillis();     // Catch: Throwable -> L87
        r242 = 4096;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda33(r26, (int) ((r12 / r82) * 100.0f)));     // Catch: Throwable -> L87
        goto L90
    L91:
        if (r14 == null) goto L93;
        r14.close();     // Catch: Throwable -> L63
    L93:
        r15.close();     // Catch: Throwable -> L70
        r22.close();     // Catch: Exception -> L68
        boolean r83 = true;
        ?? r202 = r20;
    L121:
        if (r27[r202] == false) goto L123;
        r52.delete();     // Catch: Exception -> L8
        r9 = r202;
    L124:
        if (r9 == true) goto L126;
    L129:
        r0 = Uri.fromFile(r52);     // Catch: Exception -> L8
        goto L130
    L126:
        if (r23 != 2) goto L128;
        ((DownloadManager) ApplicationLoader.applicationContext.getSystemService("download")).addCompletedDownload(r52.getName(), r52.getName(), false, r28, r52.getAbsolutePath(), r52.length(), true);     // Catch: Exception -> L8
        goto L129
    L128:
        AndroidUtilities.addMediaToGallery(r52.getAbsoluteFile());     // Catch: Exception -> L8
        goto L129
    L123:
        r9 = r83;
        goto L124
    L54:
        if (AndroidUtilities.isInternalUri(((Integer) FileDescriptor.class.getDeclaredMethod("getInt$", null).invoke(r22.getFD(), null)).intValue()) == false) goto L75;
        if (r26 == null) goto L60;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda32(r26));     // Catch: Throwable -> L58
    L60:
        if (r14 == null) goto L65;
        r14.close();     // Catch: Throwable -> L63
    L65:
        r15.close();     // Catch: Throwable -> L70
        r22.close();     // Catch: Exception -> L68
        return;
    L87:
        th = move-exception;
        if (r14 == null) goto L163;
        r14.close();     // Catch: Throwable -> L99
    L164:
        throw th;     // Catch: Throwable -> L63
    L99:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L63
        throw th;     // Catch: Throwable -> L63
    L163:
        throw th;     // Catch: Throwable -> L63
    L63:
        th = th;
    L64:
        Throwable r6 = th;
        if (r15 == null) goto L165;
        r15.close();     // Catch: Throwable -> L107
    L166:
        throw r6;     // Catch: Throwable -> L70
    L107:
        th = move-exception;
        r6.addSuppressed(th);     // Catch: Throwable -> L70
        throw r6;     // Catch: Throwable -> L70
    L165:
        throw r6;     // Catch: Throwable -> L70
    L102:
        th = th;
        r20 = false;
    L110:
        th = th;
        r20 = false;
    L71:
        Throwable r62 = th;
        r22.close();     // Catch: Throwable -> L114
    L167:
        throw r62;     // Catch: Exception -> L68
    L114:
        th = move-exception;
        r62.addSuppressed(th);     // Catch: Exception -> L68
        throw r62;     // Catch: Exception -> L68
    L70:
        th = th;
    L117:
        e = e;
        r20 = false;
    L119:
        FileLog.e(e);     // Catch: Exception -> L8
        r83 = r20 ? 1 : 0;
        r202 = r20;
    L68:
        e = e;
        goto L119
    L16:
        if (r23 != 1) goto L21;
        File r210 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);     // Catch: Exception -> L8
        if (TextUtils.isEmpty(r5) == true) goto L20;
        r210 = new File(r210, r5);     // Catch: Exception -> L8
    L20:
        r210.mkdirs();     // Catch: Exception -> L8
        r52 = new File(r210, AndroidUtilities.generateFileName(1, FileLoader.getFileExtension(r24)));     // Catch: Exception -> L8
        goto L42
    L21:
        if (r23 != 2) goto L23;
        File r102 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);     // Catch: Exception -> L8
    L25:
        if (TextUtils.isEmpty(r5) == true) goto L27;
        r102 = new File(r102, r5);     // Catch: Exception -> L8
    L27:
        r102.mkdirs();     // Catch: Exception -> L8
        r52 = new File(r102, r25);     // Catch: Exception -> L8
        if (r52.exists() == false) goto L42;
        int r11 = r25.lastIndexOf(46);     // Catch: Exception -> L8
        int r122 = 0;
    L32:
        if (r122 >= 10) goto L42;
        if (r11 == (-1)) goto L36;
        String r53 = r25.substring(0, r11) + "(" + (r122 + 1) + ")" + r25.substring(r11);     // Catch: Exception -> L8
    L37:
        File r13 = new File(r102, r53);     // Catch: Exception -> L8
        if (r13.exists() == false) goto L39;
        r122 = r122 + 1;     // Catch: Exception -> L8
        r52 = r13;
        goto L32
    L39:
        r52 = r13;
        goto L42
    L36:
        r53 = r25 + "(" + (r122 + 1) + ")";     // Catch: Exception -> L8
        goto L37
    L23:
        r102 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L136
    }

    /* renamed from: $r8$lambda$h8K3nl62-bh4CBq8i8yv2ar1las, reason: not valid java name */
    public static /* synthetic */ void m4281$r8$lambda$h8K3nl62bh4CBq8i8yv2ar1las(MediaController r0, int r1, int r2) {
        r0.lambda$startRecording$34(r1, r2);
    }

    /* renamed from: $r8$lambda$k3Q2bgk00ytQWhGk-zuFk6lxu5c, reason: not valid java name */
    public static /* synthetic */ void m4282$r8$lambda$k3Q2bgk00ytQWhGkzuFk6lxu5c(MediaController r0, ArrayList r1) {
        r0.lambda$processMediaObserver$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$kD8rnB1_mTZWEvZDeZ1CKkyc57U(MediaController r0, int r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$l85JNUP7Xwy5Q6jIZFv7nYJQwuk(MediaController r0, int r1) {
        r0.lambda$stopRecording$42(r1);
    }

    public static /* synthetic */ void $r8$lambda$na_DDr_fOREiABHHxXhWpa0I0ss(MediaController r0, File r1, boolean r2, TLRPC.TL_document r3) {
        r0.lambda$toggleRecordingPause$27(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$qHbaaIKdhhZYGCS2CrGRRIvHcD4(MediaController r0, int r1, TLRPC.TL_error r2, TLObject r3, int r4) {
        r0.lambda$loadMoreMusic$11(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$v1eTx8j8wX-zAoaDxod6BKJHP8Y, reason: not valid java name */
    public static /* synthetic */ void m4283$r8$lambda$v1eTx8j8wXzAoaDxod6BKJHP8Y(MediaController r0, int r1, int r2) {
        r0.lambda$startRecording$35(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vsrzBSbUg4z_sGkfhdRpmmpYFm4(MessageObject r2, File r3) {
        NotificationCenter.getInstance(r2.currentAccount).postNotificationName(NotificationCenter.fileLoaded, new Object[]{FileLoader.getAttachFileName(r2.getDocument()), r3});
    }

    public static /* synthetic */ void $r8$lambda$vvfC6sxpu0MpknvdNjn5EQF4z4I(MediaController r0) {
        r0.lambda$startRaiseToEarSensors$8();
    }

    /* renamed from: $r8$lambda$xtrxa-7kAAADjJpoj0HDnR0g7dU, reason: not valid java name */
    public static /* synthetic */ void m4284$r8$lambda$xtrxa7kAAADjJpoj0HDnR0g7dU(AlertDialog r1, boolean[] r2) {
    L8:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (r1.isShowing() == false) goto L6;
        r1.dismiss();     // Catch: Exception -> L8
        return;
    L6:
        r2[0] = true;     // Catch: Exception -> L8
    }

    /* renamed from: $r8$lambda$y7-iKXd5zYSJ6rhlxrLQVi9wJQQ, reason: not valid java name */
    public static /* synthetic */ void m4285$r8$lambda$y7iKXd5zYSJ6rhlxrLQVi9wJQQ(MediaController r0) {
        r0.lambda$toggleRecordingPause$31();
    }

    public static /* synthetic */ void $r8$lambda$z0VjKuIoHlcFvI7UbC2tu6gmm_4(boolean[] r1, AlertDialog r2) {
        if (r1[0] == true) goto L6;
        r2.show();
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$z9B-4BHdB6vIEakOD5HH5zsOx40, reason: not valid java name */
    public static /* synthetic */ void m4286$r8$lambda$z9B4BHdB6vIEakOD5HH5zsOx40(int r47) {
        ArrayList r13 = new ArrayList();
        ArrayList r14 = new ArrayList();
        SparseArray r12 = new SparseArray();
        SparseArray r15 = new SparseArray();
        AlbumEntry r16 = null;
        StringBuilder r0 = new StringBuilder();     // Catch: Exception -> L10
        String r18 = "datetaken";
        r0.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());     // Catch: Exception -> L8
        r0.append("/Camera/");     // Catch: Exception -> L8
        String r9 = r0.toString();     // Catch: Exception -> L8
    L215:
        Context r02 = ApplicationLoader.applicationContext;     // Catch: Throwable -> L105
        if (SystemUtils.isImagesPermissionGranted() == false) goto L107;
        ContentResolver r21 = r02.getContentResolver();     // Catch: Throwable -> L105
        Uri r22 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;     // Catch: Throwable -> L105
        String[] r23 = projectionPhotos;     // Catch: Throwable -> L105
        int r03 = Build.VERSION.SDK_INT;     // Catch: Throwable -> L105
        if (r03 <= 28) goto L18;
        String r10 = "date_modified";
    L19:
        Cursor r102 = MediaStore.Images.Media.query(r21, r22, r23, null, null, r10.concat(" DESC"));     // Catch: Throwable -> L105
        if (r102 == null) goto L103;
        String r212 = " DESC";
        int r11 = r102.getColumnIndex("_id");     // Catch: Throwable -> L101
        String r222 = "_id";
        int r8 = r102.getColumnIndex("bucket_id");     // Catch: Throwable -> L99
        String r232 = "bucket_id";
        int r7 = r102.getColumnIndex("bucket_display_name");     // Catch: Throwable -> L97
        String r24 = "bucket_display_name";
        int r6 = r102.getColumnIndex("_data");     // Catch: Throwable -> L95
        String r25 = "_data";
        if (r03 <= 28) goto L32;
        String r04 = "date_modified";
    L245:
        int r05 = r102.getColumnIndex(r04);     // Catch: Throwable -> L93
        int r5 = r102.getColumnIndex("orientation");     // Catch: Throwable -> L93
        String r26 = "orientation";
        int r4 = r102.getColumnIndex("width");     // Catch: Throwable -> L91
        String r27 = "width";
        int r3 = r102.getColumnIndex("height");     // Catch: Throwable -> L89
        String r28 = "height";
        int r2 = r102.getColumnIndex("_size");     // Catch: Throwable -> L86
        AlbumEntry r29 = null;
        AlbumEntry r30 = null;
        Object r31 = null;
        Integer r32 = null;
    L243:
        if (r102.moveToNext() == false) goto L108;
        String r39 = r102.getString(r6);     // Catch: Throwable -> L52
        if (TextUtils.isEmpty(r39) == true) goto L243;
        int r36 = r102.getInt(r11);     // Catch: Throwable -> L52
        int r35 = r102.getInt(r8);     // Catch: Throwable -> L52
        int r33 = r6;
        String r62 = r102.getString(r7);     // Catch: Throwable -> L52
        PhotoEntry r34 = new PhotoEntry(r35, r36, r102.getLong(r05), r39, r102.getInt(r5), 0, false, r102.getInt(r4), r102.getInt(r3), r102.getLong(r2));     // Catch: Throwable -> L52
        int r342 = r2;
        int r352 = r3;
        int r362 = r05;
        if (r29 != null) goto L54;
        int r37 = r4;
        int r392 = r5;
        int r38 = r7;
        AlbumEntry r42 = new AlbumEntry(0, LocaleController.getString(R.string.AllPhotos), r34);     // Catch: Throwable -> L52
        r14.add(0, r42);     // Catch: Throwable -> L50
    L55:
        if (r30 != null) goto L60;
        int r40 = r8;
        AlbumEntry r52 = new AlbumEntry(0, LocaleController.getString(R.string.AllMedia), r34);     // Catch: Throwable -> L50
        r13.add(0, r52);     // Catch: Throwable -> L58
    L61:
        if (r42.photos.size() >= 15) goto L64;
        r34.isLivePhoto();     // Catch: Throwable -> L58
    L64:
        r42.addPhoto(r34);     // Catch: Throwable -> L58
        r52.addPhoto(r34);     // Catch: Throwable -> L58
        AlbumEntry r72 = (AlbumEntry) r12.get(r35);     // Catch: Throwable -> L58
        if (r72 != null) goto L74;
        r72 = new AlbumEntry(r35, r62, r34);     // Catch: Throwable -> L58
        r12.put(r35, r72);     // Catch: Throwable -> L58
        if (r31 != null) goto L73;
        if (r9 == null) goto L73;
        if (r39 == null) goto L73;
        if (r39.startsWith(r9) == false) goto L73;
        r13.add(0, r72);     // Catch: Throwable -> L58
        r31 = Integer.valueOf(r35);     // Catch: Throwable -> L58
    L73:
        r13.add(r72);     // Catch: Throwable -> L58
    L74:
        r72.addPhoto(r34);     // Catch: Throwable -> L58
        AlbumEntry r73 = (AlbumEntry) r15.get(r35);     // Catch: Throwable -> L58
        if (r73 != null) goto L84;
        r73 = new AlbumEntry(r35, r62, r34);     // Catch: Throwable -> L58
        r15.put(r35, r73);     // Catch: Throwable -> L58
        if (r32 != null) goto L83;
        if (r9 == null) goto L83;
        if (r39 == null) goto L83;
        if (r39.startsWith(r9) == false) goto L83;
        r14.add(0, r73);     // Catch: Throwable -> L58
        r32 = Integer.valueOf(r35);     // Catch: Throwable -> L58
    L83:
        r14.add(r73);     // Catch: Throwable -> L58
    L84:
        r73.addPhoto(r34);     // Catch: Throwable -> L58
        r29 = r42;
        r30 = r52;
        r6 = r33;
        r2 = r342;
        r3 = r352;
        r05 = r362;
        r4 = r37;
        r7 = r38;
        r5 = r392;
        r8 = r40;
    L58:
        th = th;
        r29 = r42;
        r30 = r52;
    L235:
        FileLog.e(th);     // Catch: Throwable -> L201
        if (r102 != null) goto L233;
    L225:
    L148:
        th = th;
    L147:
        int r63 = 0;
    L257:
        FileLog.e(th);     // Catch: Throwable -> L193
        if (r102 != null) goto L219;
    L183:
        AlbumEntry r182 = r16;
        AlbumEntry r162 = r30;
        Integer r152 = r31;
        int r103 = r63;
    L189:
        if (r103 >= r13.size()) goto L191;
        Collections.sort(((AlbumEntry) r13.get(r103)).photos, new MediaController$$ExternalSyntheticLambda36());
        r103 = r103 + 1;
        goto L189
    L191:
        broadcastNewPhotos(r47, r13, r14, r152, r162, r29, r182, 0);
        return;
    L219:
        r102.close();     // Catch: Exception -> L181
    L181:
        e = move-exception;
        FileLog.e(e);
    L193:
        th = move-exception;
        if (r102 == null) goto L272;
        r102.close();     // Catch: Exception -> L198
        throw th;
    L198:
        e = move-exception;
        FileLog.e(e);
        throw th;
    L272:
        throw th;
    L117:
        if (SystemUtils.isVideoPermissionGranted() == false) goto L177;
        ContentResolver r210 = ApplicationLoader.applicationContext.getContentResolver();     // Catch: Throwable -> L148
        Uri r310 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;     // Catch: Throwable -> L148
        String[] r43 = projectionVideo;     // Catch: Throwable -> L148
        StringBuilder r06 = new StringBuilder();     // Catch: Throwable -> L148
        int r82 = Build.VERSION.SDK_INT;     // Catch: Throwable -> L148
        if (r82 <= 28) goto L121;
        String r53 = "date_modified";
    L122:
        r06.append(r53);     // Catch: Throwable -> L148
        r06.append(r212);     // Catch: Throwable -> L148
        r102 = MediaStore.Images.Media.query(r210, r310, r43, null, null, r06.toString());     // Catch: Throwable -> L148
        if (r102 == null) goto L177;
        int r07 = r102.getColumnIndex(r222);     // Catch: Throwable -> L148
        int r211 = r102.getColumnIndex(r232);     // Catch: Throwable -> L148
        int r311 = r102.getColumnIndex(r24);     // Catch: Throwable -> L148
        int r44 = r102.getColumnIndex(r25);     // Catch: Throwable -> L148
        if (r82 <= 28) goto L127;
        String r54 = "date_modified";
    L128:
        int r55 = r102.getColumnIndex(r54);     // Catch: Throwable -> L148
        int r64 = r102.getColumnIndex("duration");     // Catch: Throwable -> L148
        int r74 = r102.getColumnIndex(r27);     // Catch: Throwable -> L148
        int r83 = r102.getColumnIndex(r28);     // Catch: Throwable -> L148
        int r1 = r102.getColumnIndex("_size");     // Catch: Throwable -> L148
        r102.getColumnIndex(r26);     // Catch: Throwable -> L148
    L129:
        if (r102.moveToNext() == false) goto L177;
        String r372 = r102.getString(r44);     // Catch: Throwable -> L148
        if (TextUtils.isEmpty(r372) == true) goto L129;
        int r343 = r102.getInt(r07);     // Catch: Throwable -> L148
        int r332 = r102.getInt(r211);     // Catch: Throwable -> L148
        String r112 = r102.getString(r311);     // Catch: Throwable -> L148
        int r153 = r07;
        int r19 = r1;
        PhotoEntry r322 = new PhotoEntry(r332, r343, r102.getLong(r55), r372, 0, (int) (r102.getLong(r64) / 1000), true, r102.getInt(r74), r102.getInt(r83), r102.getLong(r1));     // Catch: Throwable -> L148
        int r17 = r211;
        if (r16 != null) goto L150;
        int r183 = r311;
        int r223 = r44;
        int r213 = r55;
        AlbumEntry r312 = new AlbumEntry(0, LocaleController.getString(R.string.AllVideos), r322);     // Catch: Throwable -> L148
        int r45 = 1;
        r312.videoOnly = true;     // Catch: Throwable -> L145
        if (r30 != null) goto L142;
        r45 = 0;
    L142:
        if (r29 == null) goto L144;
        r45 = r45 + 1;     // Catch: Throwable -> L145
    L144:
        r13.add(r45, r312);     // Catch: Throwable -> L145
    L151:
        if (r30 != null) goto L157;
        int r233 = r64;
        AlbumEntry r46 = new AlbumEntry(0, LocaleController.getString(R.string.AllMedia), r322);     // Catch: Throwable -> L145
        r13.add(0, r46);     // Catch: Throwable -> L155
    L217:
        r312.addPhoto(r322);     // Catch: Throwable -> L171
        r46.addPhoto(r322);     // Catch: Throwable -> L171
        AlbumEntry r56 = (AlbumEntry) r12.get(r332);     // Catch: Throwable -> L171
        if (r56 != null) goto L174;
        r56 = new AlbumEntry(r332, r112, r322);     // Catch: Throwable -> L171
        r12.put(r332, r56);     // Catch: Throwable -> L171
        if (r31 != null) goto L170;
        if (r9 == null) goto L170;
        if (r372 == null) goto L170;
        if (r372.startsWith(r9) == false) goto L170;
        r63 = 0;
        r13.add(0, r56);     // Catch: Throwable -> L168
        r31 = Integer.valueOf(r332);     // Catch: Throwable -> L168
    L175:
        r56.addPhoto(r322);     // Catch: Throwable -> L168
        r16 = r312;
        r30 = r46;
        r07 = r153;
        r211 = r17;
        r311 = r183;
        r1 = r19;
        r55 = r213;
        r44 = r223;
        r64 = r233;
    L168:
        th = th;
    L169:
        r16 = r312;
        r30 = r46;
    L170:
        r63 = 0;
        r13.add(r56);     // Catch: Throwable -> L168
        goto L175
    L174:
        r63 = 0;
    L171:
        th = th;
        r63 = 0;
    L155:
        th = th;
        r16 = r312;
        r30 = r46;
        goto L147
    L157:
        r233 = r64;
        r46 = r30;
    L145:
        th = th;
        r16 = r312;
        goto L147
    L150:
        r183 = r311;
        r223 = r44;
        r213 = r55;
        r312 = r16;
        goto L151
    L127:
        r54 = r18;
        goto L128
    L121:
        r53 = r18;
    L177:
        r63 = 0;
        if (r102 == null) goto L183;
    L233:
        r102.close();     // Catch: Exception -> L111
    L111:
        e = move-exception;
        FileLog.e(e);
    L201:
        th = move-exception;
        if (r102 == null) goto L274;
        r102.close();     // Catch: Exception -> L206
        throw th;
    L206:
        e = move-exception;
        FileLog.e(e);
        throw th;
    L274:
        throw th;
    L60:
        r40 = r8;
        r52 = r30;
    L50:
        th = th;
        r29 = r42;
        goto L235
    L54:
        r37 = r4;
        r392 = r5;
        r38 = r7;
        r42 = r29;
    L108:
        if (r102 == null) goto L225;
    L52:
        th = th;
    L86:
        th = th;
    L87:
        r29 = null;
    L88:
        r30 = r29;
        r31 = r30;
    L89:
        th = th;
        r28 = "height";
    L91:
        th = th;
        r28 = "height";
        r27 = "width";
    L93:
        th = th;
        r28 = "height";
        r27 = "width";
        r26 = "orientation";
        goto L87
    L32:
        r04 = r18;
    L95:
        th = th;
        r28 = "height";
        r27 = "width";
        r26 = "orientation";
        r25 = "_data";
    L97:
        th = th;
        r28 = "height";
        r27 = "width";
        r26 = "orientation";
        r25 = "_data";
        r24 = "bucket_display_name";
    L99:
        th = th;
        r28 = "height";
        r27 = "width";
        r26 = "orientation";
        r25 = "_data";
        r24 = "bucket_display_name";
        r232 = "bucket_id";
    L101:
        th = th;
        r28 = "height";
        r27 = "width";
        r26 = "orientation";
        r25 = "_data";
        r24 = "bucket_display_name";
        r232 = "bucket_id";
        r222 = "_id";
        goto L87
    L103:
        r28 = "height";
        r27 = "width";
        r26 = "orientation";
        r25 = "_data";
        r24 = "bucket_display_name";
        r232 = "bucket_id";
        r222 = "_id";
        r212 = " DESC";
        r29 = null;
    L104:
        r30 = r29;
        r31 = r30;
        goto L108
    L18:
        r10 = r18;
        goto L19
    L107:
        r28 = "height";
        r27 = "width";
        r26 = "orientation";
        r25 = "_data";
        r24 = "bucket_display_name";
        r232 = "bucket_id";
        r222 = "_id";
        r212 = " DESC";
        r102 = null;
        r29 = null;
    L105:
        th = th;
        r28 = "height";
        r27 = "width";
        r26 = "orientation";
        r25 = "_data";
        r24 = "bucket_display_name";
        r232 = "bucket_id";
        r222 = "_id";
        r212 = " DESC";
        r102 = null;
        r29 = null;
    L8:
        e = e;
    L12:
        FileLog.e(e);
        r9 = null;
    L10:
        e = e;
        r18 = "datetaken";
        goto L12
    }

    /* renamed from: $r8$lambda$zK1OOeN53-B8biJdC6dvxsF4wbE, reason: not valid java name */
    public static /* synthetic */ void m4287$r8$lambda$zK1OOeN53B8biJdC6dvxsF4wbE(MediaController r0, MessagesController.EmojiSound r1, AccountInstance r2, boolean r3) {
        r0.lambda$playEmojiSound$19(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetaudioPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m4288$$Nest$fgetaudioPlayer(MediaController r0) {
        return r0.audioPlayer;
    }

    /* renamed from: -$$Nest$fgetaudioRecorder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioRecord m4289$$Nest$fgetaudioRecorder(MediaController r0) {
        return r0.audioRecorder;
    }

    /* renamed from: -$$Nest$fgetbaseActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m4290$$Nest$fgetbaseActivity(MediaController r0) {
        return r0.baseActivity;
    }

    /* renamed from: -$$Nest$fgetcurrentAspectRatioFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AspectRatioFrameLayout m4291$$Nest$fgetcurrentAspectRatioFrameLayout(MediaController r0) {
        return r0.currentAspectRatioFrameLayout;
    }

    /* renamed from: -$$Nest$fgetcurrentAspectRatioFrameLayoutRatio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m4292$$Nest$fgetcurrentAspectRatioFrameLayoutRatio(MediaController r0) {
        return r0.currentAspectRatioFrameLayoutRatio;
    }

    /* renamed from: -$$Nest$fgetcurrentAspectRatioFrameLayoutRotation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4293$$Nest$fgetcurrentAspectRatioFrameLayoutRotation(MediaController r0) {
        return r0.currentAspectRatioFrameLayoutRotation;
    }

    /* renamed from: -$$Nest$fgetcurrentTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m4294$$Nest$fgetcurrentTextureView(MediaController r0) {
        return r0.currentTextureView;
    }

    /* renamed from: -$$Nest$fgetcurrentTextureViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m4295$$Nest$fgetcurrentTextureViewContainer(MediaController r0) {
        return r0.currentTextureViewContainer;
    }

    /* renamed from: -$$Nest$fgetemojiSoundPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m4296$$Nest$fgetemojiSoundPlayer(MediaController r0) {
        return r0.emojiSoundPlayer;
    }

    /* renamed from: -$$Nest$fgetemojiSoundPlayerNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4297$$Nest$fgetemojiSoundPlayerNum(MediaController r0) {
        return r0.emojiSoundPlayerNum;
    }

    /* renamed from: -$$Nest$fgetexternalObserver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ExternalObserver m4298$$Nest$fgetexternalObserver(MediaController r0) {
        return r0.externalObserver;
    }

    /* renamed from: -$$Nest$fgetfileBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ByteBuffer m4299$$Nest$fgetfileBuffer(MediaController r0) {
        return r0.fileBuffer;
    }

    /* renamed from: -$$Nest$fgetfileEncodingQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueue m4300$$Nest$fgetfileEncodingQueue(MediaController r0) {
        return r0.fileEncodingQueue;
    }

    /* renamed from: -$$Nest$fgetinternalObserver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InternalObserver m4301$$Nest$fgetinternalObserver(MediaController r0) {
        return r0.internalObserver;
    }

    /* renamed from: -$$Nest$fgetisDrawingWasReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4302$$Nest$fgetisDrawingWasReady(MediaController r0) {
        return r0.isDrawingWasReady;
    }

    /* renamed from: -$$Nest$fgetisPaused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4303$$Nest$fgetisPaused(MediaController r0) {
        return r0.isPaused;
    }

    /* renamed from: -$$Nest$fgetlastProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m4304$$Nest$fgetlastProgress(MediaController r2) {
        return r2.lastProgress;
    }

    /* renamed from: -$$Nest$fgetlastSaveTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m4305$$Nest$fgetlastSaveTime(MediaController r2) {
        return r2.lastSaveTime;
    }

    /* renamed from: -$$Nest$fgetpipRoundVideoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PipRoundVideoView m4306$$Nest$fgetpipRoundVideoView(MediaController r0) {
        return r0.pipRoundVideoView;
    }

    /* renamed from: -$$Nest$fgetpipSwitchingState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4307$$Nest$fgetpipSwitchingState(MediaController r0) {
        return r0.pipSwitchingState;
    }

    /* renamed from: -$$Nest$fgetplayerNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4308$$Nest$fgetplayerNum(MediaController r0) {
        return r0.playerNum;
    }

    /* renamed from: -$$Nest$fgetplayingMessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m4309$$Nest$fgetplayingMessageObject(MediaController r0) {
        return r0.playingMessageObject;
    }

    /* renamed from: -$$Nest$fgetplaylist, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m4310$$Nest$fgetplaylist(MediaController r0) {
        return r0.playlist;
    }

    /* renamed from: -$$Nest$fgetrecordBuffers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m4311$$Nest$fgetrecordBuffers(MediaController r0) {
        return r0.recordBuffers;
    }

    /* renamed from: -$$Nest$fgetrecordQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueue m4312$$Nest$fgetrecordQueue(MediaController r0) {
        return r0.recordQueue;
    }

    /* renamed from: -$$Nest$fgetrecordRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m4313$$Nest$fgetrecordRunnable(MediaController r0) {
        return r0.recordRunnable;
    }

    /* renamed from: -$$Nest$fgetrecordStartRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m4314$$Nest$fgetrecordStartRunnable(MediaController r0) {
        return r0.recordStartRunnable;
    }

    /* renamed from: -$$Nest$fgetrecordingCurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4315$$Nest$fgetrecordingCurrentAccount(MediaController r0) {
        return r0.recordingCurrentAccount;
    }

    /* renamed from: -$$Nest$fgetrecordingGuid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4316$$Nest$fgetrecordingGuid(MediaController r0) {
        return r0.recordingGuid;
    }

    /* renamed from: -$$Nest$fgetseekToProgressPending, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m4317$$Nest$fgetseekToProgressPending(MediaController r0) {
        return r0.seekToProgressPending;
    }

    /* renamed from: -$$Nest$fgetsendAfterDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4318$$Nest$fgetsendAfterDone(MediaController r0) {
        return r0.sendAfterDone;
    }

    /* renamed from: -$$Nest$fgetsendAfterDoneNotify, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4319$$Nest$fgetsendAfterDoneNotify(MediaController r0) {
        return r0.sendAfterDoneNotify;
    }

    /* renamed from: -$$Nest$fgetsendAfterDoneOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4320$$Nest$fgetsendAfterDoneOnce(MediaController r0) {
        return r0.sendAfterDoneOnce;
    }

    /* renamed from: -$$Nest$fgetsendAfterDonePayStars, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m4321$$Nest$fgetsendAfterDonePayStars(MediaController r2) {
        return r2.sendAfterDonePayStars;
    }

    /* renamed from: -$$Nest$fgetsendAfterDoneScheduleDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4322$$Nest$fgetsendAfterDoneScheduleDate(MediaController r0) {
        return r0.sendAfterDoneScheduleDate;
    }

    /* renamed from: -$$Nest$fgetshouldSavePositionForCurrentAudio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m4323$$Nest$fgetshouldSavePositionForCurrentAudio(MediaController r0) {
        return r0.shouldSavePositionForCurrentAudio;
    }

    /* renamed from: -$$Nest$fgetstartObserverToken, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4324$$Nest$fgetstartObserverToken(MediaController r0) {
        return r0.startObserverToken;
    }

    /* renamed from: -$$Nest$fgetsync, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m4325$$Nest$fgetsync(MediaController r0) {
        return r0.sync;
    }

    /* renamed from: -$$Nest$fgetvideoPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m4326$$Nest$fgetvideoPlayer(MediaController r0) {
        return r0.videoPlayer;
    }

    /* renamed from: -$$Nest$fputaudioVolume, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4327$$Nest$fputaudioVolume(MediaController r0, float r1) {
        r0.audioVolume = r1;
    }

    /* renamed from: -$$Nest$fputcallInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4328$$Nest$fputcallInProgress(MediaController r0, boolean r1) {
        r0.callInProgress = r1;
    }

    /* renamed from: -$$Nest$fputcurrentAspectRatioFrameLayoutRatio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4329$$Nest$fputcurrentAspectRatioFrameLayoutRatio(MediaController r0, float r1) {
        r0.currentAspectRatioFrameLayoutRatio = r1;
    }

    /* renamed from: -$$Nest$fputcurrentAspectRatioFrameLayoutRotation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4330$$Nest$fputcurrentAspectRatioFrameLayoutRotation(MediaController r0, int r1) {
        r0.currentAspectRatioFrameLayoutRotation = r1;
    }

    /* renamed from: -$$Nest$fputemojiSoundPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4331$$Nest$fputemojiSoundPlayer(MediaController r0, VideoPlayer r1) {
        r0.emojiSoundPlayer = r1;
    }

    /* renamed from: -$$Nest$fputexternalObserver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4332$$Nest$fputexternalObserver(MediaController r0, ExternalObserver r1) {
        r0.externalObserver = r1;
    }

    /* renamed from: -$$Nest$fputinternalObserver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4333$$Nest$fputinternalObserver(MediaController r0, InternalObserver r1) {
        r0.internalObserver = r1;
    }

    /* renamed from: -$$Nest$fputisDrawingWasReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4334$$Nest$fputisDrawingWasReady(MediaController r0, boolean r1) {
        r0.isDrawingWasReady = r1;
    }

    /* renamed from: -$$Nest$fputlastProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4335$$Nest$fputlastProgress(MediaController r0, long r1) {
        r0.lastProgress = r1;
    }

    /* renamed from: -$$Nest$fputlastSaveTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4336$$Nest$fputlastSaveTime(MediaController r0, long r1) {
        r0.lastSaveTime = r1;
    }

    /* renamed from: -$$Nest$fputpipRoundVideoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4337$$Nest$fputpipRoundVideoView(MediaController r0, PipRoundVideoView r1) {
        r0.pipRoundVideoView = r1;
    }

    /* renamed from: -$$Nest$fputpipSwitchingState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4338$$Nest$fputpipSwitchingState(MediaController r0, int r1) {
        r0.pipSwitchingState = r1;
    }

    /* renamed from: -$$Nest$fputseekToProgressPending, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4339$$Nest$fputseekToProgressPending(MediaController r0, float r1) {
        r0.seekToProgressPending = r1;
    }

    /* renamed from: -$$Nest$mcheckAudioFocus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4340$$Nest$mcheckAudioFocus(MediaController r0, MessageObject r1, boolean r2) {
        r0.checkAudioFocus(r1, r2);
    }

    /* renamed from: -$$Nest$mconvertVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4341$$Nest$mconvertVideo(MediaController r0, VideoConvertMessage r1) {
        return r0.convertVideo(r1);
    }

    /* renamed from: -$$Nest$mdidWriteData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4342$$Nest$mdidWriteData(MediaController r0, VideoConvertMessage r1, File r2, boolean r3, long r4, long r6, boolean r8, float r9) {
        r0.didWriteData(r1, r2, r3, r4, r6, r8, r9);
    }

    /* renamed from: -$$Nest$mplayNextMessageWithoutOrder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4343$$Nest$mplayNextMessageWithoutOrder(MediaController r0, boolean r1) {
        r0.playNextMessageWithoutOrder(r1);
    }

    /* renamed from: -$$Nest$mprocessMediaObserver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4344$$Nest$mprocessMediaObserver(MediaController r0, Uri r1) {
        r0.processMediaObserver(r1);
    }

    /* renamed from: -$$Nest$mrestoreMusicPlaylistState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4345$$Nest$mrestoreMusicPlaylistState(MediaController r0) {
        return r0.restoreMusicPlaylistState();
    }

    /* renamed from: -$$Nest$msetPlayerVolume, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4346$$Nest$msetPlayerVolume(MediaController r0) {
        r0.setPlayerVolume();
    }

    /* renamed from: -$$Nest$mstopRecordingInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4347$$Nest$mstopRecordingInternal(MediaController r0, int r1, boolean r2, int r3, boolean r4, long r5) {
        r0.stopRecordingInternal(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$mupdateVideoState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4348$$Nest$mupdateVideoState(MediaController r0, MessageObject r1, int[] r2, boolean r3, boolean r4, int r5) {
        r0.updateVideoState(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$mwriteFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4349$$Nest$mwriteFrame(MediaController r0, ByteBuffer r1, int r2) {
        return r0.writeFrame(r1, r2);
    }

    /* renamed from: -$$Nest$sfgetrefreshGalleryRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m4350$$Nest$sfgetrefreshGalleryRunnable() {
        return refreshGalleryRunnable;
    }

    /* renamed from: -$$Nest$sfputrefreshGalleryRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4351$$Nest$sfputrefreshGalleryRunnable(Runnable r0) {
        refreshGalleryRunnable = r0;
    }

    /* renamed from: -$$Nest$smsaveFileInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Uri m4352$$Nest$smsaveFileInternal(int r0, File r1, String r2) {
        return saveFileInternal(r0, r1, r2);
    }

    /* renamed from: -$$Nest$smwriteMotionPhoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4353$$Nest$smwriteMotionPhoto(File r0, File r1, OutputStream r2, boolean[] r3) {
        writeMotionPhoto(r0, r1, r2, r3);
    }

    static {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 <= 28) goto L5;
        String r8 = "date_modified";
    L6:
        projectionPhotos = new String[]{"_id", "bucket_id", "bucket_display_name", "_data", r8, "orientation", "width", "height", "_size"};
        if (r0 <= 28) goto L9;
        String r9 = "date_modified";
    L10:
        projectionVideo = new String[]{"_id", "bucket_id", "bucket_display_name", "_data", r9, "duration", "width", "height", "_size"};
        cachedEncoderBitrates = new ConcurrentHashMap();
        allMediaAlbums = new ArrayList();
        allPhotoAlbums = new ArrayList();
        return;
    L9:
        r9 = "datetaken";
        goto L10
    L5:
        r8 = "datetaken";
        goto L6
    }

    public MediaController() {
        this.audioRecordFocusChangedListener = new MediaController$$ExternalSyntheticLambda44(this);
        this.videoConvertSync = new Object();
        this.lastTimestamp = 0;
        this.lastProximityValue = -100.0f;
        this.gravity = new float[3];
        this.gravityFast = new float[3];
        this.linearAcceleration = new float[3];
        this.audioFocus = 0;
        this.foregroundConvertingMessages = new ArrayList();
        this.videoConvertQueue = new ArrayList();
        this.videoQueueSync = new Object();
        this.generatingWaveform = new HashMap();
        this.isSilent = false;
        this.isPaused = false;
        this.wasPlayingAudioBeforePause = false;
        this.audioPlayer = null;
        this.emojiSoundPlayer = null;
        this.emojiSoundPlayerNum = 0;
        this.currentPlaybackSpeed = 1.0f;
        this.currentMusicPlaybackSpeed = 1.0f;
        this.fastPlaybackSpeed = 1.0f;
        this.fastMusicPlaybackSpeed = 1.0f;
        this.lastProgress = 0;
        this.progressTimer = null;
        this.progressTimerSync = new Object();
        this.playlist = new ArrayList();
        this.playlistMap = new HashMap();
        this.shuffledPlaylist = new ArrayList();
        this.playlistEndReached = new boolean[]{false, false};
        this.playlistMaxId = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.setLoadingRunnable = new AnonymousClass1(this);
        this.recordingGuid = -1;
        this.recordSamples = new short[1024];
        this.sync = new Object();
        this.recordBuffers = new ArrayList();
        this.recordBufferSize = 1280;
        this.sampleRate = 48000;
        this.recordRunnable = new AnonymousClass2(this);
        this.audioVolumeUpdateListener = new AnonymousClass3(this);
        DispatchQueue r0 = new DispatchQueue("recordQueue");
        this.recordQueue = r0;
        r0.setPriority(10);
        DispatchQueue r02 = new DispatchQueue("fileEncodingQueue");
        this.fileEncodingQueue = r02;
        r02.setPriority(10);
        this.recordQueue.postRunnable(new MediaController$$ExternalSyntheticLambda45(this));
        Utilities.globalQueue.postRunnable(new MediaController$$ExternalSyntheticLambda46(this));
        this.fileBuffer = ByteBuffer.allocateDirect(1920);
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda47(this));
        if (Build.VERSION.SDK_INT <= 28) goto L6;
        String r03 = "date_modified";
    L7:
        this.mediaProjections = new String[]{"_data", "_display_name", "bucket_display_name", r03, "title", "width", "height"};
        ContentResolver r8 = ApplicationLoader.applicationContext.getContentResolver();
        r8.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, new GalleryObserverExternal());     // Catch: Exception -> L10
    L27:
        r8.registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, true, new GalleryObserverInternal());     // Catch: Exception -> L14
    L29:
        r8.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, new GalleryObserverExternal());     // Catch: Exception -> L18
    L31:
        r8.registerContentObserver(MediaStore.Video.Media.INTERNAL_CONTENT_URI, true, new GalleryObserverInternal());     // Catch: Exception -> L22
        return;
    L22:
        e = move-exception;
        FileLog.e(e);
        return;
    L18:
        e = move-exception;
        FileLog.e(e);
    L14:
        e = move-exception;
        FileLog.e(e);
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L27
    L6:
        r03 = "datetaken";
        goto L7
    }

    private static void broadcastNewPhotos(int r9, ArrayList<AlbumEntry> r10, ArrayList<AlbumEntry> r11, Integer r12, AlbumEntry r13, AlbumEntry r14, AlbumEntry r15, int r16) {
        Runnable r0 = broadcastPhotosRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        MediaController$$ExternalSyntheticLambda6 r1 = new MediaController$$ExternalSyntheticLambda6(r9, r10, r11, r12, r13, r14, r15);
        broadcastPhotosRunnable = r1;
        AndroidUtilities.runOnUIThread(r1, r16);
    }

    private static String buildMotionPhotoXmp(long r2) {
        return "<?xpacket begin=\"\ufeff\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?><x:xmpmeta xmlns:x=\"adobe:ns:meta/\"><rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><rdf:Description rdf:about=\"\" xmlns:GCamera=\"http://ns.google.com/photos/1.0/camera/\" xmlns:Container=\"http://ns.google.com/photos/1.0/container/\" xmlns:Item=\"http://ns.google.com/photos/1.0/container/item/\" GCamera:MotionPhoto=\"1\" GCamera:MotionPhotoVersion=\"1\" GCamera:MotionPhotoPresentationTimestampUs=\"0\"><Container:Directory><rdf:Seq><rdf:li rdf:parseType=\"Resource\"><Container:Item Item:Mime=\"image/jpeg\" Item:Semantic=\"Primary\" Item:Length=\"0\" Item:Padding=\"0\"/></rdf:li><rdf:li rdf:parseType=\"Resource\"><Container:Item Item:Mime=\"video/mp4\" Item:Semantic=\"MotionPhoto\" Item:Length=\"" + r2 + "\" Item:Padding=\"0\"/></rdf:li></rdf:Seq></Container:Directory></rdf:Description></rdf:RDF></x:xmpmeta><?xpacket end=\"w\"?>";
    }

    private void buildShuffledPlayList() {
        if (this.playlist.isEmpty() == true) goto L19;
        ArrayList r0 = new ArrayList(this.playlist);
        this.shuffledPlaylist.clear();
        int r1 = this.currentPlaylistNum;
        if (r1 >= 0) goto L8;
    L10:
        MessageObject r12 = null;
    L11:
        int r2 = r0.size();
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L14;
        int r4 = Utilities.random.nextInt(r0.size());
        this.shuffledPlaylist.add((MessageObject) r0.get(r4));
        r0.remove(r4);
        r3 = r3 + 1;
        goto L12
    L14:
        if (r12 == null) goto L18;
        this.shuffledPlaylist.add(r12);
        this.currentPlaylistNum = this.shuffledPlaylist.size() - 1;
        return;
    L18:
        return;
    L8:
        if (r1 >= this.playlist.size()) goto L10;
        r12 = this.playlist.get(this.currentPlaylistNum);
        r0.remove(this.currentPlaylistNum);
        goto L11
    }

    private boolean canStartMusicPlayerService() {
        MessageObject r0 = this.playingMessageObject;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isMusic() == true) goto L11;
        if (this.playingMessageObject.isVoice() == true) goto L11;
        if (this.playingMessageObject.isRoundVideo() == true) goto L11;
        return false;
    L11:
        if (this.playingMessageObject.isVoiceOnce() == false) goto L13;
        return false;
    L13:
        if (this.playingMessageObject.isRoundOnce() == true) goto L20;
        return true;
    L20:
        return false;
    }

    private void checkAudioFocus(MessageObject r6, boolean r7) {
        if (r7 == false) goto L15;
        if (r6.isVideo() == false) goto L15;
        VideoPlayer r3 = this.videoPlayer;
        if (r3 == null) goto L15;
        if (r3.getAudioTrackState() == 1) goto L15;
        if (this.videoPlayer.getAudioTrackState() != 2) goto L46;
        checkAudioFocus(r6, false);
        return;
    L46:
        return;
    L15:
        if (r6.isVoice() == true) goto L21;
        if (r6.isRoundVideo() == true) goto L21;
        int r62 = 1;
    L24:
        int r32 = this.hasAudioFocus;
        if (r32 == r62) goto L40;
        if (r7 == false) goto L40;
        this.hasAudioFocus = r62;
        if (r62 != 3) goto L30;
        int r63 = NotificationsController.audioManager.requestAudioFocus(this, 0, 1);
    L37:
        if (r63 != 1) goto L47;
        this.audioFocus = 2;
        return;
    L47:
        return;
    L30:
        AudioManager r72 = NotificationsController.audioManager;
        if (r62 == 2) goto L33;
    L35:
        int r64 = 2;
    L36:
        r63 = r72.requestAudioFocus(this, 3, r64);
        goto L37
    L33:
        if (SharedConfig.pauseMusicOnMedia == true) goto L35;
        r64 = 3;
    L40:
        if (r32 == 0) goto L48;
        if (r7 == false) goto L43;
        return;
    L43:
        if (NotificationsController.audioManager.abandonAudioFocus(this) != 1) goto L50;
        this.audioFocus = 0;
        this.hasAudioFocus = 0;
        return;
    L50:
        return;
    L48:
        return;
    L21:
        if (this.useFrontSpeaker == false) goto L23;
        r62 = 3;
        goto L24
    L23:
        r62 = 2;
        goto L24
    }

    private void checkForegroundConvertMessage(boolean r3) {
        if (this.foregroundConvertingMessages.isEmpty() == true) goto L5;
        this.currentForegroundConvertingVideo = this.foregroundConvertingMessages.get(0);
    L7:
        if (this.currentForegroundConvertingVideo != null) goto L11;
        if (r3 == true) goto L11;
        return;
    L11:
        VideoEncodingService.start(r3);
        return;
    L5:
        this.currentForegroundConvertingVideo = null;
        goto L7
    }

    public static void checkGallery() {
        AlbumEntry r0 = allPhotosAlbumEntry;
        if (r0 != null) goto L5;
        return;
    L5:
        int r02 = r0.photos.size();
        Utilities.globalQueue.postRunnable(new MediaController$$ExternalSyntheticLambda25(r02), 2000);
    }

    private void checkIsNextMusicFileDownloaded(int r6) {
        if (DownloadController.getInstance(r6).canDownloadNextTrack() == true) goto L6;
        return;
    L6:
        if (SharedConfig.shuffleMusic == false) goto L8;
        ArrayList<MessageObject> r0 = this.shuffledPlaylist;
    L9:
        if (r0 == null) goto L46;
        int r2 = 2;
        if (r0.size() < 2) goto L52;
        boolean r1 = SharedConfig.playOrderReversed;
        int r5 = this.currentPlaylistNum;
        if (r1 == false) goto L18;
        int r52 = r5 + 1;
        if (r52 < r0.size()) goto L21;
        r52 = 0;
    L21:
        if (r52 >= 0) goto L23;
        return;
    L23:
        if (r52 >= r0.size()) goto L53;
        MessageObject r53 = r0.get(r52);
        File r12 = null;
        if (TextUtils.isEmpty(r53.messageOwner.attachPath) == true) goto L31;
        File r02 = new File(r53.messageOwner.attachPath);
        if (r02.exists() == false) goto L31;
        r12 = r02;
    L31:
        if (r12 == null) goto L33;
        File r03 = r12;
    L34:
        r03.exists();
        if (r03 != r12) goto L37;
        return;
    L37:
        if (r03.exists() == false) goto L39;
        return;
    L39:
        if (r53.isMusic() == false) goto L50;
        FileLoader r62 = FileLoader.getInstance(r6);
        TLRPC.Document r04 = r53.getDocument();
        if (r53.shouldEncryptPhotoOrVideo() == true) goto L44;
        r2 = 0;
    L44:
        r62.loadFile(r04, r53, 0, r2);
        return;
    L50:
        return;
    L33:
        r03 = FileLoader.getInstance(r6).getPathToMessage(r53.messageOwner);
        goto L34
    L53:
        return;
    L18:
        r52 = r5 - 1;
        if (r52 >= 0) goto L21;
        r52 = r0.size() - 1;
        goto L21
    L52:
        return;
    L46:
        return;
    L8:
        r0 = this.playlist;
        goto L9
    }

    private void checkIsNextVoiceFileDownloaded(int r5) {
        ArrayList<MessageObject> r0 = this.voiceMessagesPlaylist;
        if (r0 == null) goto L28;
        int r1 = 2;
        if (r0.size() < 2) goto L31;
        MessageObject r4 = this.voiceMessagesPlaylist.get(1);
        String r02 = r4.messageOwner.attachPath;
        File r2 = null;
        if (r02 != null) goto L10;
    L15:
        if (r2 == null) goto L17;
        File r03 = r2;
    L18:
        r03.exists();
        if (r03 != r2) goto L21;
        return;
    L21:
        if (r03.exists() == true) goto L30;
        FileLoader r52 = FileLoader.getInstance(r5);
        TLRPC.Document r04 = r4.getDocument();
        if (r4.shouldEncryptPhotoOrVideo() == true) goto L26;
        r1 = 0;
    L26:
        r52.loadFile(r04, r4, 0, r1);
        return;
    L30:
        return;
    L17:
        r03 = FileLoader.getInstance(r5).getPathToMessage(r4.messageOwner);
        goto L18
    L10:
        if (r02.length() <= 0) goto L15;
        File r05 = new File(r4.messageOwner.attachPath);
        if (r05.exists() == false) goto L15;
        r2 = r05;
        goto L15
    L31:
        return;
    }

    private void checkScreenshots(ArrayList<Long> r12) {
        if (r12 != null) goto L4;
        return;
    L4:
        if (r12.isEmpty() == false) goto L6;
        return;
    L6:
        if (this.lastChatEnterTime != 0) goto L8;
        return;
    L8:
        if (this.lastUser == null) goto L10;
    L12:
        int r0 = 0;
        boolean r1 = false;
    L14:
        if (r0 >= r12.size()) goto L28;
        Long r4 = r12.get(r0);
        if (this.lastMediaCheckTime == 0) goto L21;
        if (r4.longValue() > this.lastMediaCheckTime) goto L21;
    L27:
        r0 = r0 + 1;
    L21:
        if (r4.longValue() < this.lastChatEnterTime) goto L27;
        if (this.lastChatLeaveTime != 0) goto L25;
    L26:
        this.lastMediaCheckTime = Math.max(this.lastMediaCheckTime, r4.longValue());
        r1 = true;
        goto L27
    L25:
        if (r4.longValue() > (this.lastChatLeaveTime + 2000)) goto L27;
    L28:
        if (r1 == false) goto L43;
        TLRPC.EncryptedChat r122 = this.lastSecretChat;
        int r02 = this.lastChatAccount;
        if (r122 == null) goto L33;
        SecretChatHelper.getInstance(r02).sendScreenshotMessage(this.lastSecretChat, this.lastChatVisibleMessages, null);
        return;
    L33:
        SendMessagesHelper.getInstance(r02).sendScreenshotMessage(this.lastUser, this.lastMessageId, null);
        return;
    L43:
        return;
    L10:
        if ((this.lastSecretChat instanceof TLRPC.TL_encryptedChat) == true) goto L12;
    }

    private void clearMusicPlaylistState() {
        this.savedMusicPlaylistState = null;
    }

    private void clearPlaylist() {
        this.currentSavedMusicList = null;
        this.playlist.clear();
        this.playlistMap.clear();
        this.shuffledPlaylist.clear();
        this.playlistClassGuid = 0;
        boolean[] r2 = this.playlistEndReached;
        r2[1] = false;
        r2[0] = false;
        this.playlistMergeDialogId = 0;
        int[] r22 = this.playlistMaxId;
        r22[1] = Integer.MAX_VALUE;
        r22[0] = Integer.MAX_VALUE;
        this.loadingPlaylist = false;
        this.playlistGlobalSearchParams = null;
        this.savedMusicPlaylistState = null;
    }

    private boolean convertVideo(VideoConvertMessage r37) {
        MessageObject r2 = r37.messageObject;
        VideoEditedInfo r3 = r37.videoEditedInfo;
        if (r2 == null) goto L5;
        if (r3 == null) goto L101;
        String r5 = r3.originalPath;
        long r6 = r3.videoOffset;
        long r8 = r3.startTime;
        long r10 = r3.avatarStartTime;
        long r12 = r3.endTime;
        int r14 = r3.resultWidth;
        int r15 = r3.resultHeight;
        int r7 = r3.rotationValue;
        int r62 = r3.originalWidth;
        int r4 = r3.originalHeight;
        int r0 = r3.framerate;
        int r1 = r3.bitrate;
        int r13 = r3.originalBitrate;
        if (DialogObject.isEncryptedDialog(r2.getDialogId()) == false) goto L9;
    L12:
        boolean r20 = true;
    L13:
        File r16 = new File(r2.messageOwner.attachPath);
        if (r16.exists() == false) goto L17;
        r16.delete();
    L17:
        if (BuildVars.LOGS_ENABLED == false) goto L19;
        File r22 = r16;
        StringBuilder r23 = new StringBuilder("begin convert ");
        r23.append(r5);
        r23.append(" startTime = ");
        r23.append(r8);
        r23.append(" avatarStartTime = ");
        r23.append(r10);
        r23.append(" endTime ");
        r23.append(r12);
        r23.append(" rWidth = ");
        r23.append(r14);
        r23.append(" rHeight = ");
        r23.append(r15);
        r23.append(" rotation = ");
        r23.append(r7);
        r23.append(" oWidth = ");
        r23.append(r62);
        r23.append(" oHeight = ");
        r23.append(r4);
        r23.append(" framerate = ");
        r23.append(r0);
        r23.append(" bitrate = ");
        r23.append(r1);
        r23.append(" originalBitrate = ");
        int r17 = r13;
        r23.append(r17);
        FileLog.d(r23.toString());
    L20:
        if (r5 != null) goto L23;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L23:
        if (r8 <= 0) goto L28;
        if (r12 <= 0) goto L28;
        long r232 = r12 - r8;
        int r21 = r17;
    L34:
        if (r0 != 0) goto L37;
        r0 = 25;
    L40:
        if (r7 != 90) goto L42;
    L46:
        long r33 = r10;
        int r11 = r15;
    L45:
        boolean r82 = r20;
        int r152 = r21;
        long r202 = r33;
        if (r3.shouldLimitFps == true) goto L55;
        if (r0 <= 40) goto L55;
        if (Math.min(r14, r11) > 480) goto L55;
        r0 = 30;
    L55:
        if (r202 == (-1)) goto L57;
    L75:
        File r9 = r22;
        boolean r222 = true;
    L76:
        int r25 = r0;
        SharedPreferences r02 = ApplicationLoader.applicationContext.getSharedPreferences("videoconvert", 0);
        long r30 = System.currentTimeMillis();
        File r42 = r9;
        AnonymousClass18 r24 = new AnonymousClass18(this, r3, r42, r37);
        r3.videoConvertFirstWrite = true;
        MediaCodecVideoConvertor r03 = new MediaCodecVideoConvertor();
        MediaCodecVideoConvertor.ConvertVideoParams r32 = MediaCodecVideoConvertor.ConvertVideoParams.of(r5, r42, r6, r7, r82, r62, r4, r11, r14, r25, r1, r152, r8, r12, r202, r222, r232, r24, r3);
        r32.soundInfos.addAll(r3.mixedSoundInfos);
        boolean r34 = r03.convertVideo(r32);
        boolean r52 = r3.canceled;
        if (r52 == true) goto L87;
        Object r63 = this.videoConvertSync;
        monitor-enter(r63);
        r52 = r3.canceled;     // Catch: Throwable -> L83
        monitor-exit(r63);     // Catch: Throwable -> L83
    L83:
        th = move-exception;
        throw th;
    L87:
        if (BuildVars.LOGS_ENABLED == false) goto L89;
        FileLog.d("time=" + (System.currentTimeMillis() - r30) + " canceled=" + r52);
    L89:
        r02.edit().putBoolean("isPreviousOk", true).apply();
        long r83 = r03.getLastFrameTimestamp();
        long r64 = r42.length();
        if (r34 == true) goto L95;
        if (r52 == true) goto L95;
        boolean r43 = false;
    L96:
        didWriteData(r37, r42, true, r83, r64, r43, 1.0f);
        return true;
    L95:
        r43 = true;
        goto L96
    L57:
        if (r3.cropState != null) goto L75;
        if (r3.mediaEntities != null) goto L75;
        if (r3.paintPath != null) goto L75;
        if (r3.filterState != null) goto L75;
        if (r11 != r62) goto L75;
        if (r14 != r4) goto L75;
        if (r7 != 0) goto L75;
        if (r3.roundVideo == true) goto L75;
        if (r8 != (-1)) goto L75;
        if (r3.mixedSoundInfos.isEmpty() == false) goto L75;
        r9 = r22;
        r222 = false;
        goto L76
    L42:
        if (r7 == 270) goto L46;
        r33 = r10;
        r11 = r14;
        r14 = r15;
        goto L45
    L37:
        if (r0 <= 59) goto L40;
        r0 = 59;
    L28:
        if (r12 <= 0) goto L30;
        r21 = r17;
        r232 = r12;
        goto L34
    L30:
        r21 = r17;
        long r18 = r3.originalDuration;
        if (r8 <= 0) goto L33;
        r232 = r18 - r8;
        goto L34
    L33:
        r232 = r18;
        goto L34
    L19:
        r22 = r16;
        r17 = r13;
        goto L20
    L9:
        if (r3.forceFragmenting == true) goto L12;
        r20 = false;
        goto L13
    L101:
        return false;
    L5:
        return false;
    }

    public static String copyFileToCache(Uri r2, String r3) {
        return copyFileToCache(r2, r3, -1);
    }

    @SuppressLint({"DiscouragedPrivateApi"})
    public static String copyFileToCache(Uri r12, String r13, long r14) {
        InputStream r3 = null;
        int r4 = 0;
        String r0 = FileLoader.fixFileName(getFileName(r12));     // Catch: Throwable -> L6 Exception -> L8
        if (r0 != null) goto L10;
        int r02 = SharedConfig.getLastLocalId();     // Catch: Throwable -> L6 Exception -> L8
        SharedConfig.saveConfig();     // Catch: Throwable -> L6 Exception -> L8
        r0 = String.format(Locale.US, "%d.%s", new Object[]{Integer.valueOf(r02), r13});     // Catch: Throwable -> L6 Exception -> L8
    L10:
        File r132 = AndroidUtilities.getSharingDirectory();     // Catch: Throwable -> L6 Exception -> L8
    L172:
        r132.mkdirs();     // Catch: Throwable -> L24 Exception -> L26
        if (AndroidUtilities.isInternalUri(Uri.fromFile(r132)) == true) goto L14;
        int r5 = 0;
    L20:
        File r133 = AndroidUtilities.getSharingDirectory();     // Catch: Throwable -> L24 Exception -> L26
        if (r5 != 0) goto L28;
        File r6 = new File(r133, r0);     // Catch: Throwable -> L24 Exception -> L26
    L23:
        r132 = r6;
    L33:
        r5 = r5 + 1;     // Catch: Throwable -> L24 Exception -> L26
        if (r132.exists() == true) goto L20;
        InputStream r122 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r12);     // Catch: Throwable -> L24 Exception -> L26
    L56:
        e = e;
        FileOutputStream r52 = null;
    L58:
        int r62 = 0;
    L59:
        File r42 = r132;
        InputStream r134 = r122;
        Exception r123 = e;
    L144:
        FileLog.e(r123);     // Catch: Throwable -> L124
        if (r134 != null) goto L160;
    L113:
        if (r52 == null) goto L119;
        r52.close();     // Catch: Exception -> L116
    L116:
        e = move-exception;
        FileLog.e(e);
    L119:
        if (r14 > 0) goto L121;
    L123:
        return null;
    L121:
        if (r62 <= r14) goto L123;
        r42.delete();
        goto L123
    L160:
        r134.close();     // Catch: Exception -> L111
    L111:
        e = move-exception;
        FileLog.e(e);
    L124:
        th = move-exception;
        Throwable r124 = th;
        r3 = r134;
        r132 = r42;
    L85:
        r4 = r62;
    L126:
        if (r3 != null) goto L142;
    L131:
        if (r52 == null) goto L137;
        r52.close();     // Catch: Exception -> L134
    L134:
        e = move-exception;
        FileLog.e(e);
    L137:
        if (r14 > 0) goto L139;
        throw r124;
    L139:
        if (r4 <= r14) goto L182;
        r132.delete();
        throw r124;
    L182:
        throw r124;
    L142:
        r3.close();     // Catch: Exception -> L129
    L129:
        e = move-exception;
        FileLog.e(e);
    L53:
        th = th;
        r52 = null;
    L55:
        r3 = r122;
        r124 = th;
        goto L126
    L37:
        if ((r122 instanceof FileInputStream) == true) goto L146;
    L60:
        r52 = new FileOutputStream(r132);     // Catch: Throwable -> L53 Exception -> L56
    L170:
        byte[] r03 = new byte[20480];     // Catch: Throwable -> L103 Exception -> L105
        r62 = 0;
    L168:
        int r7 = r122.read(r03);     // Catch: Throwable -> L83 Exception -> L86
        if (r7 == (-1)) goto L88;
        r52.write(r03, 0, r7);     // Catch: Throwable -> L83 Exception -> L86
        r62 = r62 + r7;
        if (r14 <= 0) goto L168;
        long r8 = r62;
        if (r8 <= r14) goto L168;
        r122.close();     // Catch: Exception -> L73
    L162:
        r52.close();     // Catch: Exception -> L77
    L79:
        if (r14 <= 0) goto L82;
        if (r8 <= r14) goto L82;
        r132.delete();
    L82:
        return null;
    L77:
        e = move-exception;
        FileLog.e(e);
    L73:
        e = move-exception;
        FileLog.e(e);
        goto L162
    L88:
        String r32 = r132.getAbsolutePath();     // Catch: Throwable -> L83 Exception -> L86
        r122.close();     // Catch: Exception -> L91
    L158:
        r52.close();     // Catch: Exception -> L95
    L98:
        if (r14 > 0) goto L100;
    L102:
        return r32;
    L100:
        if (r62 <= r14) goto L102;
        r132.delete();
    L95:
        e = move-exception;
        FileLog.e(e);
    L91:
        e = move-exception;
        FileLog.e(e);
        goto L158
    L86:
        e = e;
    L83:
        th = move-exception;
        r3 = r122;
        r124 = th;
        goto L85
    L105:
        e = e;
    L103:
        th = th;
        goto L55
    L146:
        if (AndroidUtilities.isInternalUri(((Integer) FileDescriptor.class.getDeclaredMethod("getInt$", null).invoke(((FileInputStream) r122).getFD(), null)).intValue()) == false) goto L60;
        r122.close();     // Catch: Exception -> L43
    L46:
        if (r14 > 0) goto L48;
    L50:
        return null;
    L48:
        if (0 <= r14) goto L50;
        r132.delete();
    L43:
        e = move-exception;
        FileLog.e(e);
    L51:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L53 Exception -> L56
        goto L60
    L28:
        int r63 = r0.lastIndexOf(".");     // Catch: Throwable -> L24 Exception -> L26
        if (r63 <= 0) goto L32;
        r132 = new File(r133, r0.substring(0, r63) + " (" + r5 + ")" + r0.substring(r63));     // Catch: Throwable -> L24 Exception -> L26
        goto L33
    L32:
        r6 = new File(r133, r0 + " (" + r5 + ")");     // Catch: Throwable -> L24 Exception -> L26
        goto L23
    L14:
        if (r14 > 0) goto L16;
    L18:
        return null;
    L16:
        if (0 <= r14) goto L18;
        r132.delete();
    L26:
        e = move-exception;
        r123 = e;
        r52 = null;
        r62 = 0;
        r42 = r132;
        r134 = null;
    L24:
        th = move-exception;
        r124 = th;
        r52 = null;
    L8:
        e = move-exception;
        r123 = e;
        r134 = null;
        r52 = null;
        r62 = 0;
        r42 = null;
    L6:
        th = move-exception;
        r124 = th;
        r132 = null;
        r52 = null;
        goto L126
    }

    public static File createFileInCache(String r9, String r10) {
        File r0 = AndroidUtilities.getSharingDirectory();     // Catch: Exception -> L10
        r0.mkdirs();     // Catch: Exception -> L10
        if (AndroidUtilities.isInternalUri(Uri.fromFile(r0)) == false) goto L6;
        return null;
    L6:
        int r1 = 0;
    L7:
        File r2 = AndroidUtilities.getSharingDirectory();     // Catch: Exception -> L10
        if (r1 != 0) goto L12;
        File r3 = new File(r2, r9);     // Catch: Exception -> L10
    L17:
        r1 = r1 + 1;     // Catch: Exception -> L10
        if (r3.exists() == true) goto L7;
        return r3;
    L12:
        int r32 = r9.lastIndexOf(".");     // Catch: Exception -> L10
        if (r32 <= 0) goto L16;
        r3 = new File(r2, r9.substring(0, r32) + " (" + r1 + ")" + r9.substring(r32));     // Catch: Exception -> L10
        goto L17
    L16:
        r3 = new File(r2, r9 + " (" + r1 + ")");     // Catch: Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static native boolean cropOpusFile(String r0, String r1, long r2, long r4);

    private void didWriteData(VideoConvertMessage r13, File r14, boolean r15, long r16, long r18, boolean r20, float r21) {
        VideoEditedInfo r0 = r13.videoEditedInfo;
        boolean r9 = r0.videoConvertFirstWrite;
        if (r9 == false) goto L5;
        r0.videoConvertFirstWrite = false;
    L5:
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda43(this, r20, r15, r13, r14, r21, r16, r9, r18));
    }

    public static int extractRealEncoderBitrate(int r4, int r5, int r6, boolean r7) {
        String r1 = r4 + _UrlKt.FRAGMENT_ENCODE_SET + r5 + _UrlKt.FRAGMENT_ENCODE_SET + r6;
        Integer r2 = cachedEncoderBitrates.get(r1);
        if (r2 == null) goto L7;
        return r2.intValue();
    L7:
        if (r7 == true) goto L20;
    L10:
        MediaCodec r72 = null;
    L12:
        if (r72 == null) goto L18;
    L14:
        MediaFormat r42 = MediaFormat.createVideoFormat(VIDEO_MIME_TYPE, r4, r5);     // Catch: Exception -> L16
        r42.setInteger("color-format", 2130708361);     // Catch: Exception -> L16
        r42.setInteger("max-bitrate", r6);     // Catch: Exception -> L16
        r42.setInteger("bitrate", r6);     // Catch: Exception -> L16
        r42.setInteger("frame-rate", 30);     // Catch: Exception -> L16
        r42.setInteger("i-frame-interval", 1);     // Catch: Exception -> L16
        r72.configure(r42, null, null, 1);     // Catch: Exception -> L16
        int r43 = r72.getOutputFormat().getInteger("bitrate");     // Catch: Exception -> L16
        cachedEncoderBitrates.put(r1, Integer.valueOf(r43));     // Catch: Exception -> L16
        r72.release();     // Catch: Exception -> L16
        return r43;
    L16:
        return r6;
    L18:
        r72 = MediaCodec.createEncoderByType(VIDEO_MIME_TYPE);     // Catch: Exception -> L16
        goto L14
    L20:
        r72 = MediaCodec.createEncoderByType("video/hevc");     // Catch: Exception -> L17
        goto L12
    }

    public static int findTrack(MediaExtractor r4, boolean r5) {
        int r0 = r4.getTrackCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L13;
        String r2 = r4.getTrackFormat(r1).getString("mime");
        if (r5 == false) goto L10;
        if (r2.startsWith("audio/") == false) goto L12;
    L11:
        return r1;
    L12:
        r1 = r1 + 1;
        goto L3
    L10:
        if (r2.startsWith("video/") == false) goto L12;
    L13:
        return -5;
    }

    private boolean forbidRaiseToListen() {
        return SystemUtils.hasExternalAudioOutput(NotificationsController.audioManager);
    }

    public static String getFileName(Uri r10) {
        if (r10 != null) goto L48;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L48:
    L31:
        e = move-exception;
        FileLog.e(e);
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        String r3 = null;
        if (r10.getScheme().equals("content") == true) goto L46;
        Uri r5 = r10;
    L34:
        if (r3 != null) goto L52;
        String r32 = r5.getPath();     // Catch: Exception -> L31
        int r102 = r32.lastIndexOf(47);     // Catch: Exception -> L31
        if (r102 == (-1)) goto L53;
        return r32.substring(r102 + 1);
    L53:
        return r32;
    L52:
        return r3;
    L46:
        r5 = r10;
        Cursor r103 = ApplicationLoader.applicationContext.getContentResolver().query(r5, new String[]{"_display_name"}, null, null, null);     // Catch: Exception -> L19
    L15:
        th = move-exception;
        if (r103 == null) goto L50;
        r103.close();     // Catch: Throwable -> L24
    L51:
        throw th;     // Catch: Exception -> L19
    L24:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L19
        throw th;     // Catch: Exception -> L19
    L50:
        throw th;     // Catch: Exception -> L19
    L12:
        if (r103.moveToFirst() == false) goto L17;
        r3 = r103.getString(r103.getColumnIndex("_display_name"));     // Catch: Throwable -> L15
    L17:
        r103.close();     // Catch: Exception -> L19
    L19:
        e = e;
    L30:
        FileLog.e(e);     // Catch: Exception -> L31
    L28:
        e = e;
        r5 = r10;
        goto L30
    }

    public static MediaController getInstance() {
        MediaController r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(MediaController.class);
        MediaController r02 = Instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new MediaController();     // Catch: Throwable -> L9
        Instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(MediaController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static String getStickerExt(Uri r8) {
        InputStream r1 = null;
        InputStream r2 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r8);     // Catch: Throwable -> L5 Exception -> L7
    L8:
        if (r2 == null) goto L77;
    L16:
        byte[] r3 = new byte[12];     // Catch: Throwable -> L12 Exception -> L14
        if (r2.read(r3, 0, 12) != 12) goto L81;
        byte r82 = r3[0];     // Catch: Throwable -> L12 Exception -> L14
        if (r82 != (-119)) goto L41;
        if (r3[1] != 80) goto L41;
        if (r3[2] != 78) goto L41;
        if (r3[3] != 71) goto L41;
        if (r3[4] != 13) goto L41;
        if (r3[5] != 10) goto L41;
        if (r3[6] != 26) goto L41;
        if (r3[7] != 10) goto L41;
        r2.close();     // Catch: Exception -> L37
    L39:
        return "png";
    L37:
        e = move-exception;
        FileLog.e(e);
    L41:
        if (r82 == 31) goto L43;
    L50:
        String r83 = new String(r3).toLowerCase();     // Catch: Throwable -> L12 Exception -> L14
        if (r83.startsWith("riff") == false) goto L81;
        if (r83.endsWith("webp") == false) goto L81;
        r2.close();     // Catch: Exception -> L56
    L58:
        return "webp";
    L56:
        e = move-exception;
        FileLog.e(e);
        goto L58
    L14:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L12
        if (r2 != null) goto L81;
    L66:
        return null;
    L12:
        th = th;
        r1 = r2;
    L67:
        if (r1 != null) goto L79;
    L72:
        throw th;
    L79:
        r1.close();     // Catch: Exception -> L70
    L70:
        e = move-exception;
        FileLog.e(e);
        goto L72
    L43:
        if (r3[1] != (-117)) goto L50;
        r2.close();     // Catch: Exception -> L47
    L49:
        return "tgs";
    L47:
        e = move-exception;
        FileLog.e(e);
    L81:
        r2.close();     // Catch: Exception -> L61
    L61:
        e = move-exception;
        FileLog.e(e);
        goto L66
    L77:
        File r32 = new File(r8.getPath());     // Catch: Throwable -> L12 Exception -> L14
        if (r32.exists() == false) goto L16;
        r2 = new FileInputStream(r32);     // Catch: Throwable -> L12 Exception -> L14
        goto L16
    L7:
        r2 = null;
    L5:
        th = th;
        goto L67
    }

    public static int getVideoBitrate(String r1) {
        MediaMetadataRetriever r0 = new MediaMetadataRetriever();
        r0.setDataSource(r1);     // Catch: Exception -> L5
        int r12 = Integer.parseInt(r0.extractMetadata(20));     // Catch: Exception -> L5
    L12:
        r0.release();     // Catch: Throwable -> L9
    L11:
        return r12;
    L9:
        th = move-exception;
        FileLog.e(th);
    L5:
        e = move-exception;
        FileLog.e(e);
        r12 = 0;
        goto L12
    }

    private static int getVideoBitrateWithFactor(float r1) {
        return (int) (((r1 * 2000.0f) * 1000.0f) * 1.13f);
    }

    public static native byte[] getWaveform(String r0);

    public static boolean isGif(Uri r4) {
        InputStream r1 = null;
        r1 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r4);     // Catch: Throwable -> L13 Exception -> L15
        byte[] r2 = new byte[3];     // Catch: Throwable -> L13 Exception -> L15
        if (r1.read(r2, 0, 3) != 3) goto L37;
        if (new String(r2).equalsIgnoreCase("gif") == false) goto L37;
        r1.close();     // Catch: Exception -> L10
        return true;
    L10:
        e = move-exception;
        FileLog.e(e);
        return true;
    L37:
        r1.close();     // Catch: Exception -> L19
    L24:
        return false;
    L19:
        e = move-exception;
        FileLog.e(e);
    L15:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13
        if (r1 == null) goto L24;
    L13:
        th = move-exception;
        if (r1 != null) goto L35;
    L30:
        throw th;
    L35:
        r1.close();     // Catch: Exception -> L28
    L28:
        e = move-exception;
        FileLog.e(e);
        goto L30
    }

    public static boolean isH264Video(String r3) {
        MediaExtractor r0 = new MediaExtractor();
        boolean r1 = false;
        r0.setDataSource(r3);     // Catch: Throwable -> L8 Exception -> L10
        int r32 = findTrack(r0, false);     // Catch: Throwable -> L8 Exception -> L10
        if (r32 < 0) goto L12;
        if (r0.getTrackFormat(r32).getString("mime").equals(VIDEO_MIME_TYPE) == false) goto L12;
        r1 = true;
        goto L12
    L12:
        r0.release();
        return r1;
    L8:
        th = move-exception;
        r0.release();
        throw th;
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        r0.release();
        return false;
    }

    private boolean isNearToSensor(float r2) {
        if (r2 < 5.0f) goto L5;
        return false;
    L5:
        if (r2 == this.proximitySensor.getMaximumRange()) goto L10;
        return true;
    L10:
        return false;
    }

    public static native int isOpusFile(String r0);

    private static boolean isRecognizedFormat(int r1) {
        if (r1 != 39) goto L5;
        return true;
    L5:
        if (r1 == 2130706688) goto L11;
        switch(r1) {
            case 19: goto L12;
            case 20: goto L12;
            case 21: goto L12;
            default: goto L7;
        };
    L7:
        return false;
    L12:
        return true;
    L11:
        return true;
    }

    private boolean isSamePlayingMessage(MessageObject r7) {
        MessageObject r0 = this.playingMessageObject;
        if (r0 == null) goto L19;
        if (r7 == null) goto L19;
        if (r0.getDialogId() != r7.getDialogId()) goto L19;
        if (this.playingMessageObject.getFeedRealId() != r7.getFeedRealId()) goto L19;
        if (this.playingMessageObject.eventId != 0) goto L12;
        boolean r6 = true;
    L14:
        if (r7.eventId != 0) goto L16;
        boolean r72 = true;
    L17:
        if (r6 != r72) goto L19;
        return true;
    L16:
        r72 = false;
        goto L17
    L12:
        r6 = false;
    L19:
        return false;
    }

    public static boolean isWebp(Uri r4) {
        InputStream r1 = null;
        r1 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r4);     // Catch: Throwable -> L15 Exception -> L17
        byte[] r2 = new byte[12];     // Catch: Throwable -> L15 Exception -> L17
        if (r1.read(r2, 0, 12) != 12) goto L40;
        String r42 = new String(r2).toLowerCase();     // Catch: Throwable -> L15 Exception -> L17
        if (r42.startsWith("riff") == false) goto L40;
        if (r42.endsWith("webp") == false) goto L40;
        r1.close();     // Catch: Exception -> L12
        return true;
    L12:
        e = move-exception;
        FileLog.e(e);
        return true;
    L40:
        r1.close();     // Catch: Exception -> L21
    L26:
        return false;
    L21:
        e = move-exception;
        FileLog.e(e);
    L17:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L15
        if (r1 == null) goto L26;
    L15:
        th = move-exception;
        if (r1 != null) goto L37;
    L32:
        throw th;
    L37:
        r1.close();     // Catch: Exception -> L30
    L30:
        e = move-exception;
        FileLog.e(e);
        goto L32
    }

    public static native boolean joinOpusFiles(String r0, String r1, String r2);

    private File joinRecord() {
        return joinRecord(this.recordingPrevAudioFile, this.recordingAudioFile, this.recordingAudio);
    }

    private File joinRecord(File r6, File r7, TLRPC.TL_document r8) {
        if (r6 == null) goto L13;
        if (r7 == null) goto L13;
        AnonymousClass14 r0 = new AnonymousClass14(this, FileLoader.getDirectory(1), System.currentTimeMillis() + "_" + FileLoader.getAttachFileName(r8));
        if (joinOpusFiles(r6.getAbsolutePath(), r7.getAbsolutePath(), r0.getAbsolutePath()) == false) goto L10;
        r7.delete();
        if (r7 != this.recordingAudioFile) goto L9;
        this.recordingAudioFile = r0;
    L9:
        r7 = r0;
    L10:
        r6.delete();
        if (r6 != this.recordingPrevAudioFile) goto L13;
        this.recordingPrevAudioFile = null;
    L13:
        return r7;
    }

    private /* synthetic */ void lambda$cleanupPlayer$10(VideoPlayer r2, ValueAnimator r3) {
        if (this.audioFocus == 1) goto L5;
        float r1 = 1.0f;
    L6:
        r2.setVolume(r1 * ((Float) r3.getAnimatedValue()).floatValue());
        return;
    L5:
        r1 = VOLUME_DUCK;
        goto L6
    }

    private /* synthetic */ void lambda$didWriteData$59(boolean r5, boolean r6, VideoConvertMessage r7, File r8, float r9, long r10, boolean r12, long r13) {
        if (r5 == true) goto L4;
        if (r6 == true) goto L4;
    L13:
        if (r5 == false) goto L16;
        NotificationCenter.getInstance(r7.currentAccount).postNotificationName(NotificationCenter.filePreparingFailed, new Object[]{r7.messageObject, r8.toString(), Float.valueOf(r9), Long.valueOf(r10)});
        return;
    L16:
        if (r12 == false) goto L18;
        NotificationCenter.getInstance(r7.currentAccount).postNotificationName(NotificationCenter.filePreparingStarted, new Object[]{r7.messageObject, r8.toString(), Float.valueOf(r9), Long.valueOf(r10)});
    L18:
        NotificationCenter r4 = NotificationCenter.getInstance(r7.currentAccount);
        int r52 = NotificationCenter.fileNewChunkAvailable;
        MessageObject r72 = r7.messageObject;
        String r73 = r8.toString();
        Long r132 = Long.valueOf(r13);
        if (r6 == false) goto L21;
        long r0 = r8.length();
    L22:
        r4.postNotificationName(r52, new Object[]{r72, r73, r132, Long.valueOf(r0), Float.valueOf(r9), Long.valueOf(r10)});
        return;
    L21:
        r0 = 0;
    L4:
        boolean r02 = r7.videoEditedInfo.canceled;
        Object r1 = this.videoConvertSync;
        monitor-enter(r1);
        boolean r3 = false;
        r7.videoEditedInfo.canceled = false;     // Catch: Throwable -> L24
        monitor-exit(r1);     // Catch: Throwable -> L24
        this.videoConvertQueue.remove(r7);
        this.foregroundConvertingMessages.remove(r7);
        if (r02 == true) goto L11;
        if (r5 == true) goto L11;
    L12:
        checkForegroundConvertMessage(r3);
        startVideoConvertFromQueue();
    L11:
        r3 = true;
    L24:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$generateWaveform$38(String r11, byte[] r12, MessageObject r13) {
        MessageObject r10 = this.generatingWaveform.remove(r11);
        if (r10 == null) goto L21;
        if (r12 != null) goto L7;
        return;
    L7:
        if (r10.getDocument() == null) goto L20;
        int r112 = 0;
    L10:
        if (r112 >= r10.getDocument().attributes.size()) goto L15;
        TLRPC.DocumentAttribute r0 = r10.getDocument().attributes.get(r112);
        if ((r0 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L13;
        r112 = r112 + 1;
        goto L10
    L13:
        r0.waveform = r12;
        r0.flags |= 4;
    L15:
        TLRPC.TL_messages_messages r1 = new TLRPC.TL_messages_messages();
        r1.messages.add(r10.messageOwner);
        MessagesStorage.getInstance(r10.currentAccount).putMessages(r1, r10.getDialogId(), -1, 0, false, r13.scheduled ? 1 : 0, 0);
        ArrayList r113 = new ArrayList();
        r113.add(r10);
        NotificationCenter.getInstance(r10.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(r10.getDialogId()), r113});
        return;
    L20:
        return;
    }

    private /* synthetic */ void lambda$generateWaveform$39(String r2, String r3, MessageObject r4) {
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda3(this, r3, getWaveform(r2), r4));
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadMoreMusic$11(int r7, TLRPC.TL_error r8, TLObject r9, int r10) {
        if (this.playlistClassGuid != r7) goto L34;
        PlaylistGlobalSearchParams r72 = this.playlistGlobalSearchParams;
        if (r72 != null) goto L7;
        return;
    L7:
        if (this.playingMessageObject == null) goto L37;
        if (r8 != null) goto L38;
        boolean r82 = false;
        this.loadingPlaylist = false;
        TLRPC.messages_Messages r92 = (TLRPC.messages_Messages) r9;
        r72.nextSearchRate = r92.next_rate;
        MessagesStorage.getInstance(r10).putUsersAndChats(r92.users, r92.chats, true, true);
        MessagesController.getInstance(r10).putUsers(r92.users, false);
        MessagesController.getInstance(r10).putChats(r92.chats, false);
        int r73 = r92.messages.size();
        int r0 = 0;
        int r1 = 0;
    L12:
        if (r0 >= r73) goto L21;
        MessageObject r3 = new MessageObject(r10, r92.messages.get(r0), false, true);
        if (r3.isVoiceOnce() == true) goto L20;
        if (this.playlistMap.containsKey(Integer.valueOf(r3.getId())) == true) goto L20;
        this.playlist.add(0, r3);
        this.playlistMap.put(Integer.valueOf(r3.getId()), r3);
        r1 = r1 + 1;
    L20:
        r0 = r0 + 1;
        goto L12
    L21:
        sortPlaylist();
        this.loadingPlaylist = false;
        PlaylistGlobalSearchParams r74 = this.playlistGlobalSearchParams;
        if (this.playlist.size() != this.playlistGlobalSearchParams.totalCount) goto L24;
        r82 = true;
    L24:
        r74.endReached = r82;
        if (SharedConfig.shuffleMusic == false) goto L27;
        buildShuffledPlayList();
    L27:
        if (r1 == 0) goto L36;
        NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.moreMusicDidLoad, new Object[]{Integer.valueOf(r1)});
        return;
    L36:
        return;
    L38:
        return;
    L37:
        return;
    }

    private /* synthetic */ void lambda$loadMoreMusic$12(int r7, int r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda59(this, r7, r10, r9, r8));
    }

    private /* synthetic */ void lambda$new$0(int r2) {
        if (r2 == 1) goto L6;
        this.hasRecordAudioFocus = false;
        return;
    }

    private /* synthetic */ void lambda$new$2() {
        this.sampleRate = 48000;     // Catch: Exception -> L12
        int r0 = AudioRecord.getMinBufferSize(48000, 16, 2);     // Catch: Exception -> L12
        if (r0 > 0) goto L6;
        r0 = 1280;
    L6:
        this.recordBufferSize = r0;     // Catch: Exception -> L12
        int r02 = 0;
    L8:
        if (r02 >= 5) goto L11;
        ByteBuffer r1 = ByteBuffer.allocateDirect(this.recordBufferSize);     // Catch: Exception -> L12
        r1.order(ByteOrder.nativeOrder());     // Catch: Exception -> L12
        this.recordBuffers.add(r1);     // Catch: Exception -> L12
        r02 = r02 + 1;
        goto L8
    L11:
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$new$3() {
        this.currentPlaybackSpeed = MessagesController.getGlobalMainSettings().getFloat("playbackSpeed", 1.0f);     // Catch: Exception -> L9
        this.currentMusicPlaybackSpeed = MessagesController.getGlobalMainSettings().getFloat("musicPlaybackSpeed", 1.0f);     // Catch: Exception -> L9
        this.fastPlaybackSpeed = MessagesController.getGlobalMainSettings().getFloat("fastPlaybackSpeed", 1.8f);     // Catch: Exception -> L9
        this.fastMusicPlaybackSpeed = MessagesController.getGlobalMainSettings().getFloat("fastMusicPlaybackSpeed", 1.8f);     // Catch: Exception -> L9
        SensorManager r1 = (SensorManager) ApplicationLoader.applicationContext.getSystemService("sensor");     // Catch: Exception -> L9
        this.sensorManager = r1;     // Catch: Exception -> L9
        this.linearSensor = r1.getDefaultSensor(10);     // Catch: Exception -> L9
        Sensor r12 = this.sensorManager.getDefaultSensor(9);     // Catch: Exception -> L9
        this.gravitySensor = r12;     // Catch: Exception -> L9
        if (this.linearSensor == null) goto L7;
        if (r12 == null) goto L7;
    L12:
        this.proximitySensor = this.sensorManager.getDefaultSensor(8);     // Catch: Exception -> L9
        this.proximityWakeLock = ((PowerManager) ApplicationLoader.applicationContext.getSystemService("power")).newWakeLock(32, "telegram:proximity_lock");     // Catch: Exception -> L9
    L24:
        AnonymousClass4 r13 = new AnonymousClass4(this);     // Catch: Exception -> L19
        TelephonyManager r4 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");     // Catch: Exception -> L19
        if (r4 == null) goto L26;
        r4.listen(r13, 32);     // Catch: Exception -> L19
        return;
    L26:
        return;
    L19:
        e = move-exception;
        FileLog.e(e);
        return;
    L7:
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("gravity or linear sensor not found");     // Catch: Exception -> L9
    L11:
        this.accelerometerSensor = this.sensorManager.getDefaultSensor(1);     // Catch: Exception -> L9
        this.linearSensor = null;     // Catch: Exception -> L9
        this.gravitySensor = null;     // Catch: Exception -> L9
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L24
    }

    private /* synthetic */ void lambda$new$4() {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.httpFileDidLoad);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.didReceiveNewMessages);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.messagesDeleted);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.removeAllMessagesFromDialog);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.musicDidLoad);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.mediaDidLoad);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.musicListLoaded);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.playerDidStartPlaying);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.stopAllHeavyOperations);
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$onAudioFocusChange$5(int r4) {
        if (r4 != (-1)) goto L11;
        if (isPlayingMessage(getPlayingMessageObject()) == true) goto L7;
    L9:
        this.hasAudioFocus = 0;
        this.audioFocus = 0;
    L34:
        setPlayerVolume();
        return;
    L7:
        if (isMessagePaused() == true) goto L9;
        pauseMessage(this.playingMessageObject);
        goto L9
    L11:
        if (r4 != 1) goto L20;
        this.audioFocus = 2;
        if (this.resumeAudioOnFocusGain == false) goto L34;
        this.resumeAudioOnFocusGain = false;
        if (isPlayingMessage(getPlayingMessageObject()) == false) goto L34;
        if (isMessagePaused() == false) goto L34;
        playMessage(getPlayingMessageObject());
        goto L34
    L20:
        if (r4 != (-3)) goto L23;
        this.audioFocus = 1;
        goto L34
    L23:
        if (r4 != (-2)) goto L34;
        this.audioFocus = 0;
        if (isPlayingMessage(getPlayingMessageObject()) == false) goto L34;
        if (isMessagePaused() == true) goto L34;
        if (this.playingMessageObject.isMusic() == false) goto L31;
    L32:
        this.wasPlayingAudioBeforePause = true;
    L33:
        pauseMessage(this.playingMessageObject);
        this.resumeAudioOnFocusGain = true;
        goto L34
    L31:
        if (this.playingMessageObject.isVoice() == false) goto L33;
        goto L32
    }

    private /* synthetic */ void lambda$playEmojiSound$17(File r5) {
        int r1 = this.emojiSoundPlayerNum + 1;     // Catch: Exception -> L6
        this.emojiSoundPlayerNum = r1;     // Catch: Exception -> L6
        VideoPlayer r2 = this.emojiSoundPlayer;     // Catch: Exception -> L6
        if (r2 == null) goto L8;
        r2.releasePlayer(true);     // Catch: Exception -> L6
    L8:
        VideoPlayer r22 = new VideoPlayer(false, false);     // Catch: Exception -> L6
        this.emojiSoundPlayer = r22;     // Catch: Exception -> L6
        r22.setDelegate(new AnonymousClass8(this, r1));     // Catch: Exception -> L6
        this.emojiSoundPlayer.preparePlayer(Uri.fromFile(r5), "other");     // Catch: Exception -> L6
        this.emojiSoundPlayer.setStreamType(3);     // Catch: Exception -> L6
        this.emojiSoundPlayer.play();     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        VideoPlayer r52 = this.emojiSoundPlayer;
        if (r52 == null) goto L16;
        r52.releasePlayer(true);
        this.emojiSoundPlayer = null;
        return;
    }

    private /* synthetic */ void lambda$playEmojiSound$19(MessagesController.EmojiSound r4, AccountInstance r5, boolean r6) {
        TLRPC.TL_document r0 = new TLRPC.TL_document();
        r0.access_hash = r4.accessHash;
        r0.id = r4.id;
        r0.mime_type = "sound/ogg";
        r0.file_reference = r4.fileReference;
        r0.dc_id = r5.getConnectionsManager().getCurrentDatacenterId();
        File r42 = FileLoader.getInstance(r5.getCurrentAccount()).getPathToAttach(r0, true);
        if (r42.exists() == false) goto L8;
        if (r6 == false) goto L6;
        return;
    L6:
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda0(this, r42));
        return;
    L8:
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda1(r5, r0));
    }

    private /* synthetic */ void lambda$playMessage$20() {
        cleanupPlayer(true, true);
    }

    private /* synthetic */ void lambda$prepareResumedRecording$23(int r7, long r8) {
        MediaDataController.getInstance(r7).pushDraftVoiceMessage(r8, this.recordTopicId, null);
        this.recordStartRunnable = null;
    }

    private /* synthetic */ void lambda$prepareResumedRecording$24(File r9, TLRPC.TL_document r10, MediaDataController.DraftVoice r11) {
        if (r9.exists() == false) goto L5;
    L7:
        r10.date = ConnectionsManager.getInstance(this.recordingCurrentAccount).getCurrentTime();
        r10.size = (int) r9.length();
        TLRPC.TL_documentAttributeAudio r0 = new TLRPC.TL_documentAttributeAudio();
        r0.voice = true;
        short[] r1 = this.recordSamples;
        byte[] r12 = getWaveform2(r1, r1.length);
        r0.waveform = r12;
        if (r12 == null) goto L10;
        r0.flags |= 4;
    L10:
        r0.duration = this.recordTimeCount / 1000.0d;
        r10.attributes.clear();
        r10.attributes.add(r0);
        NotificationCenter.getInstance(this.recordingCurrentAccount).postNotificationName(NotificationCenter.recordPaused, new Object[0]);
        NotificationCenter.getInstance(this.recordingCurrentAccount).postNotificationName(NotificationCenter.audioDidSent, new Object[]{Integer.valueOf(this.recordingGuid), r10, r9.getAbsolutePath(), Boolean.TRUE, Float.valueOf(r11.left), Float.valueOf(r11.right)});
        return;
    L5:
        if (BuildVars.DEBUG_VERSION == false) goto L7;
        FileLog.e(new RuntimeException("file not found :( recordTimeCount " + this.recordTimeCount + " writedFrames" + this.writtenFrame));
        goto L7
    }

    private /* synthetic */ void lambda$prepareResumedRecording$25(int r7, MediaDataController.DraftVoice r8, int r9, long r10, long r12, MessageSuggestionParams r14, MessageObject r15, MessageObject r16, TL_stories.StoryItem r17) {
        setBluetoothScoOn(true);
        this.sendAfterDone = 0;
        TLRPC.TL_document r3 = new TLRPC.TL_document();
        this.recordingAudio = r3;
        this.recordingGuid = r7;
        r3.dc_id = Integer.MIN_VALUE;
        r3.id = r8.id;
        r3.user_id = UserConfig.getInstance(r9).getClientUserId();
        TLRPC.TL_document r72 = this.recordingAudio;
        r72.mime_type = "audio/ogg";
        r72.file_reference = new byte[0];
        SharedConfig.saveConfig();
        this.recordingAudioFile = new AnonymousClass13(this, r8.path);
        FileLoader.getDirectory(4).mkdirs();
        AutoDeleteMediaTask.lockFile(this.recordingAudioFile);
        this.audioRecorderPaused = true;     // Catch: Exception -> L10
        this.recordTimeCount = r8.recordTimeCount;     // Catch: Exception -> L10
        this.writtenFrame = r8.writedFrame;     // Catch: Exception -> L10
        this.samplesCount = r8.samplesCount;     // Catch: Exception -> L10
        this.recordSamples = r8.recordSamples;     // Catch: Exception -> L10
        this.recordDialogId = r10;     // Catch: Exception -> L10
        this.recordMonoForumPeerId = r12;     // Catch: Exception -> L10
        this.recordMonoForumSuggestionParams = r14;     // Catch: Exception -> L10
        if (r15 != null) goto L6;
        long r32 = 0;
    L7:
        this.recordTopicId = r32;     // Catch: Exception -> L10
        this.recordingCurrentAccount = r9;     // Catch: Exception -> L10
        this.recordReplyingMsg = r16;     // Catch: Exception -> L10
        this.recordReplyingTopMsg = r15;     // Catch: Exception -> L10
        this.recordReplyingStory = r17;     // Catch: Exception -> L10
        this.recordSendMessageChatArguments = this.recordSendMessageChatArguments;     // Catch: Exception -> L10
        TLRPC.TL_document r73 = this.recordingAudio;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda27(this, this.recordingAudioFile, r73, r8));
        return;
    L6:
        r32 = MessageObject.getTopicId(this.recordingCurrentAccount, r15.messageOwner, false);     // Catch: Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);
        this.recordingAudio = null;
        AutoDeleteMediaTask.unlockFile(this.recordingAudioFile);
        this.recordingAudioFile.delete();
        this.recordingAudioFile = null;
        this.audioRecorder.release();     // Catch: Exception -> L14
        this.audioRecorder = null;     // Catch: Exception -> L14
    L16:
        setBluetoothScoOn(false);
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda26(this, r9, r10));
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L16
    }

    private /* synthetic */ void lambda$processMediaObserver$6(ArrayList r4) {
        NotificationCenter.getInstance(this.lastChatAccount).postNotificationName(NotificationCenter.screenshotTook, new Object[0]);
        checkScreenshots(r4);
    }

    private /* synthetic */ void lambda$setCurrentVideoVisible$14() {
        cleanupPlayer(true, true);
    }

    private /* synthetic */ void lambda$setPlaybackSpeed$16(MessageObject r2, float r3) {
        if (this.audioPlayer != null) goto L5;
        return;
    L5:
        if (this.playingMessageObject != null) goto L7;
        return;
    L7:
        if (this.isPaused == false) goto L9;
        return;
    L9:
        if (isSamePlayingMessage(r2) == false) goto L11;
        seekToProgress(this.playingMessageObject, r3);
    L11:
        this.audioPlayer.play();
    }

    private /* synthetic */ void lambda$setTextureView$15() {
        cleanupPlayer(true, true);
    }

    private /* synthetic */ void lambda$startAudioAgain$7(MessageObject r1) {
        pauseMessage(r1);
    }

    private /* synthetic */ void lambda$startRaiseToEarSensors$8() {
        Sensor r0 = this.gravitySensor;
        if (r0 == null) goto L5;
        this.sensorManager.registerListener(this, r0, 30000);
    L5:
        Sensor r02 = this.linearSensor;
        if (r02 == null) goto L8;
        this.sensorManager.registerListener(this, r02, 30000);
    L8:
        Sensor r03 = this.accelerometerSensor;
        if (r03 == null) goto L11;
        this.sensorManager.registerListener(this, r03, 30000);
    L11:
        this.sensorManager.registerListener(this, this.proximitySensor, 3);
    }

    private /* synthetic */ void lambda$startRecording$33(int r2, int r3) {
        this.recordStartRunnable = null;
        NotificationCenter.getInstance(r2).postNotificationName(NotificationCenter.recordStartError, new Object[]{Integer.valueOf(r3)});
    }

    private /* synthetic */ void lambda$startRecording$34(int r2, int r3) {
        this.recordStartRunnable = null;
        NotificationCenter.getInstance(r2).postNotificationName(NotificationCenter.recordStartError, new Object[]{Integer.valueOf(r3)});
    }

    private /* synthetic */ void lambda$startRecording$35(int r2, int r3) {
        this.recordStartRunnable = null;
        NotificationCenter.getInstance(r2).postNotificationName(NotificationCenter.recordStartError, new Object[]{Integer.valueOf(r3)});
    }

    private /* synthetic */ void lambda$startRecording$36(int r2, int r3) {
        this.recordStartRunnable = null;
        NotificationCenter.getInstance(r2).postNotificationName(NotificationCenter.recordStarted, new Object[]{Integer.valueOf(r3), Boolean.TRUE});
    }

    private /* synthetic */ void lambda$startRecording$37(int r10, int r11, long r12, long r14, MessageSuggestionParams r16, MessageObject r17, MessageObject r18, TL_stories.StoryItem r19, SendMessageChatArguments r20) {
        if (this.audioRecorder == null) goto L6;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda49(this, r10, r11));
        return;
    L6:
        setBluetoothScoOn(true);
        this.sendAfterDone = 0;
        TLRPC.TL_document r3 = new TLRPC.TL_document();
        this.recordingAudio = r3;
        this.recordingGuid = r11;
        r3.file_reference = new byte[0];
        r3.dc_id = Integer.MIN_VALUE;
        r3.id = SharedConfig.getLastLocalId();
        this.recordingAudio.user_id = UserConfig.getInstance(r10).getClientUserId();
        TLRPC.TL_document r32 = this.recordingAudio;
        r32.mime_type = "audio/ogg";
        r32.file_reference = new byte[0];
        SharedConfig.saveConfig();
        this.recordingAudioFile = new AnonymousClass17(this, FileLoader.getDirectory(1), System.currentTimeMillis() + "_" + FileLoader.getAttachFileName(this.recordingAudio));
        FileLoader.getDirectory(4).mkdirs();
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        FileLog.d("start recording internal " + this.recordingAudioFile.getPath() + " " + this.recordingAudioFile.exists());
    L9:
        AutoDeleteMediaTask.lockFile(this.recordingAudioFile);
    L16:
        e = move-exception;
        FileLog.e(e);
        this.recordingAudio = null;
        stopRecord();
        AutoDeleteMediaTask.unlockFile(this.recordingAudioFile);
        this.recordingAudioFile.delete();
        this.recordingAudioFile = null;
        File r13 = this.recordingPrevAudioFile;
        if (r13 == null) goto L35;
        r13.delete();
        this.recordingPrevAudioFile = null;
    L35:
        this.audioRecorder.release();     // Catch: Exception -> L31
        this.audioRecorder = null;     // Catch: Exception -> L31
    L33:
        setBluetoothScoOn(false);
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda51(this, r10, r11));
        return;
    L31:
        e = move-exception;
        FileLog.e(e);
        goto L33
    L11:
        if (startRecord(this.recordingAudioFile.getPath(), this.sampleRate) != 0) goto L19;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda50(this, r10, r11));     // Catch: Exception -> L16
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        FileLog.d("cant init encoder");     // Catch: Exception -> L16
        return;
    L18:
        return;
    L19:
        this.audioRecorderPaused = false;     // Catch: Exception -> L16
        this.audioRecorder = new AudioRecord(0, this.sampleRate, 16, 2, this.recordBufferSize);     // Catch: Exception -> L16
        this.recordStartTime = System.currentTimeMillis();     // Catch: Exception -> L16
        long r33 = 0;
        this.recordTimeCount = 0;     // Catch: Exception -> L16
        this.writtenFrame = 0;     // Catch: Exception -> L16
        this.samplesCount = 0;     // Catch: Exception -> L16
        this.recordDialogId = r12;     // Catch: Exception -> L16
        this.recordMonoForumPeerId = r14;     // Catch: Exception -> L16
        this.recordMonoForumSuggestionParams = r16;     // Catch: Exception -> L16
        if (r17 == null) goto L23;
        r33 = MessageObject.getTopicId(this.recordingCurrentAccount, r17.messageOwner, false);     // Catch: Exception -> L16
    L23:
        this.recordTopicId = r33;     // Catch: Exception -> L16
        this.recordingCurrentAccount = r10;     // Catch: Exception -> L16
        this.recordReplyingMsg = r18;     // Catch: Exception -> L16
        this.recordReplyingTopMsg = r17;     // Catch: Exception -> L16
        this.recordReplyingStory = r19;     // Catch: Exception -> L16
        this.recordSendMessageChatArguments = r20;     // Catch: Exception -> L16
        this.fileBuffer.rewind();     // Catch: Exception -> L16
        this.audioRecorder.startRecording();     // Catch: Exception -> L16
        this.recordQueue.postRunnable(this.recordRunnable);
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda52(this, r10, r11));
    }

    private /* synthetic */ void lambda$stopRaiseToEarSensors$9() {
        Sensor r0 = this.linearSensor;
        if (r0 == null) goto L5;
        this.sensorManager.unregisterListener(this, r0);
    L5:
        Sensor r02 = this.gravitySensor;
        if (r02 == null) goto L8;
        this.sensorManager.unregisterListener(this, r02);
    L8:
        Sensor r03 = this.accelerometerSensor;
        if (r03 == null) goto L11;
        this.sensorManager.unregisterListener(this, r03);
    L11:
        this.sensorManager.unregisterListener(this, this.proximitySensor);
    }

    private /* synthetic */ void lambda$stopRecording$42(int r4) {
        NotificationCenter r0 = NotificationCenter.getInstance(this.recordingCurrentAccount);
        int r1 = NotificationCenter.recordStopped;
        Integer r3 = Integer.valueOf(this.recordingGuid);
        if (r4 != 2) goto L5;
        int r42 = 1;
    L6:
        r0.postNotificationName(r1, new Object[]{r3, Integer.valueOf(r42)});
        return;
    L5:
        r42 = 0;
        goto L6
    }

    private /* synthetic */ void lambda$stopRecording$43(int r5, boolean r6, int r7, boolean r8, long r9) {
        if (this.sendAfterDone != 3) goto L6;
        this.sendAfterDone = 0;
        stopRecordingInternal(r5, r6, r7, r8, r9);
        return;
    L6:
        AudioRecord r52 = this.audioRecorder;
        if (r52 != null) goto L25;
        this.recordingAudio = null;
        this.manualRecording = false;
        this.raiseToEarRecord = false;
        this.ignoreOnPause = false;
        return;
    L25:
        this.sendAfterDone = r5;     // Catch: Exception -> L12
        this.sendAfterDoneNotify = r6;     // Catch: Exception -> L12
        this.sendAfterDoneScheduleDate = r7;     // Catch: Exception -> L12
        this.sendAfterDoneOnce = r8;     // Catch: Exception -> L12
        this.sendAfterDonePayStars = r9;     // Catch: Exception -> L12
        r52.stop();     // Catch: Exception -> L12
        setBluetoothScoOn(false);     // Catch: Exception -> L12
    L19:
        if (r5 != 0) goto L27;
        stopRecordingInternal(0, false, 0, false, 0);
    L27:
        this.feedbackView.performHapticFeedback(3, 2);     // Catch: Exception -> L24
    L22:
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda53(this, r5));
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        if (this.recordingAudioFile == null) goto L19;
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        FileLog.e("delete voice file");
    L18:
        this.recordingAudioFile.delete();
        goto L19
    }

    private /* synthetic */ void lambda$stopRecordingInternal$40(File r20, TLRPC.TL_document r21, int r22, boolean r23, int r24, boolean r25, long r26) {
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        if (r20 != null) goto L6;
        String r3 = "null";
    L7:
        FileLog.d("stop recording internal ".concat(r3));
        goto L8
    L6:
        r3 = r20.exists() + " " + r20.length() + "  recordTimeCount " + this.recordTimeCount + " writedFrames" + this.writtenFrame;
    L8:
        if (r20 == null) goto L13;
        if (r20.exists() == false) goto L13;
    L15:
        MediaDataController.getInstance(this.recordingCurrentAccount).pushDraftVoiceMessage(this.recordDialogId, this.recordTopicId, null);
        r21.date = ConnectionsManager.getInstance(this.recordingCurrentAccount).getCurrentTime();
        if (r20 != null) goto L18;
        long r32 = 0;
    L19:
        r21.size = r32;
        TLRPC.TL_documentAttributeAudio r33 = new TLRPC.TL_documentAttributeAudio();
        r33.voice = true;
        byte[] r5 = getWaveform(r20.getAbsolutePath());
        r33.waveform = r5;
        if (r5 == null) goto L22;
        r33.flags |= 4;
    L22:
        long r52 = this.recordTimeCount;
        r33.duration = r52 / 1000.0d;
        r21.attributes.clear();
        r21.attributes.add(r33);
        if (r52 <= 700) goto L38;
        if (r22 != 1) goto L30;
        String r34 = r20.getAbsolutePath();
        long r4 = this.recordDialogId;
        MessageObject r6 = this.recordReplyingMsg;
        MessageObject r7 = this.recordReplyingTopMsg;
        if (r25 == false) goto L28;
        int r15 = Integer.MAX_VALUE;
    L29:
        SendMessagesHelper.SendMessageParams r2 = SendMessagesHelper.SendMessageParams.of(r21, null, r34, r4, r6, r7, null, null, null, null, r23, r24, 0, r15, null, null, false);
        r2.monoForumPeer = this.recordMonoForumPeerId;
        r2.suggestionParams = this.recordMonoForumSuggestionParams;
        r2.replyToStoryItem = this.recordReplyingStory;
        r2.sendMessageChatArguments = this.recordSendMessageChatArguments;
        r2.payStars = r26;
        SendMessagesHelper.getInstance(this.recordingCurrentAccount).sendMessage(r2);
        goto L30
    L28:
        r15 = 0;
    L30:
        NotificationCenter r1 = NotificationCenter.getInstance(this.recordingCurrentAccount);
        int r27 = NotificationCenter.audioDidSent;
        Integer r35 = Integer.valueOf(this.recordingGuid);
        String r42 = null;
        if (r22 != 2) goto L33;
        TLRPC.TL_document r72 = r21;
    L34:
        if (r22 != 2) goto L36;
        r42 = r20.getAbsolutePath();
    L36:
        r1.postNotificationName(r27, new Object[]{r35, r72, r42});
    L39:
        requestRecordAudioFocus(false);
        return;
    L33:
        r72 = null;
        goto L34
    L38:
        NotificationCenter.getInstance(this.recordingCurrentAccount).postNotificationName(NotificationCenter.audioRecordTooShort, new Object[]{Integer.valueOf(this.recordingGuid), Boolean.FALSE, Integer.valueOf((int) r52)});
        AutoDeleteMediaTask.unlockFile(r20);
        r20.delete();
        goto L39
    L18:
        r32 = (int) r20.length();
    L13:
        if (BuildVars.DEBUG_VERSION == false) goto L15;
        FileLog.e(new RuntimeException("file not found :( recordTimeCount " + this.recordTimeCount + " writedFrames" + this.writtenFrame));
        goto L15
    }

    private /* synthetic */ void lambda$stopRecordingInternal$41(File r3, File r4, TLRPC.TL_document r5, int r6, boolean r7, int r8, boolean r9, long r10) {
        stopRecord();
        File r42 = joinRecord(r3, r4, r5);
        if (r42 != null) goto L9;
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.d("stop recording recordingAudioFileToSend == null in queue");
        return;
    L13:
        return;
    L9:
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("stop recording internal in queue " + r42.exists() + " " + r42.length());
    L11:
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda54(this, r42, r5, r6, r7, r8, r9, r10));
    }

    private /* synthetic */ void lambda$toggleRecordingPause$27(File r9, boolean r10, TLRPC.TL_document r11) {
        boolean r0 = r9.exists();
        if (r0 == false) goto L5;
    L7:
        if (r0 == false) goto L9;
        MediaDataController.getInstance(this.recordingCurrentAccount).pushDraftVoiceMessage(this.recordDialogId, this.recordTopicId, MediaDataController.DraftVoice.of(this, r9.getAbsolutePath(), r10, 0.0f, 1.0f));
    L9:
        r11.date = ConnectionsManager.getInstance(this.recordingCurrentAccount).getCurrentTime();
        r11.size = (int) r9.length();
        TLRPC.TL_documentAttributeAudio r102 = new TLRPC.TL_documentAttributeAudio();
        r102.voice = true;
        byte[] r02 = getWaveform(r9.getAbsolutePath());
        r102.waveform = r02;
        if (r02 == null) goto L12;
        r102.flags |= 4;
    L12:
        r102.duration = this.recordTimeCount / 1000.0d;
        r11.attributes.clear();
        r11.attributes.add(r102);
        NotificationCenter.getInstance(this.recordingCurrentAccount).postNotificationName(NotificationCenter.recordPaused, new Object[0]);
        NotificationCenter.getInstance(this.recordingCurrentAccount).postNotificationName(NotificationCenter.audioDidSent, new Object[]{Integer.valueOf(this.recordingGuid), r11, r9.getAbsolutePath()});
        requestRecordAudioFocus(false);
        return;
    L5:
        if (BuildVars.DEBUG_VERSION == false) goto L7;
        FileLog.e(new RuntimeException("file not found :( recordTimeCount " + this.recordTimeCount + " writedFrames" + this.writtenFrame));
        goto L7
    }

    private /* synthetic */ void lambda$toggleRecordingPause$28(boolean r4) {
        stopRecord();
        TLRPC.TL_document r0 = this.recordingAudio;
        File r1 = joinRecord(this.recordingPrevAudioFile, this.recordingAudioFile, r0);
        if (r0 == null) goto L8;
        if (r1 == null) goto L9;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda24(this, r1, r4, r0));
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$toggleRecordingPause$29() {
        this.recordStartRunnable = null;
        NotificationCenter.getInstance(this.recordingCurrentAccount).postNotificationName(NotificationCenter.recordStartError, new Object[]{Integer.valueOf(this.recordingGuid)});
    }

    private /* synthetic */ void lambda$toggleRecordingPause$30() {
        requestRecordAudioFocus(true);
        this.audioRecorder = new AudioRecord(0, this.sampleRate, 16, 2, this.recordBufferSize);
        this.recordStartTime = System.currentTimeMillis();
        this.writtenFrame = 0;
        this.samplesCount = 0;
        this.fileBuffer.rewind();
        this.audioRecorder.startRecording();
        this.recordQueue.postRunnable(this.recordRunnable);
        NotificationCenter.getInstance(this.recordingCurrentAccount).postNotificationName(NotificationCenter.recordResumed, new Object[0]);
    }

    private /* synthetic */ void lambda$toggleRecordingPause$31() {
        this.recordingPrevAudioFile = this.recordingAudioFile;
        AnonymousClass16 r0 = new AnonymousClass16(this, FileLoader.getDirectory(1), System.currentTimeMillis() + "_" + FileLoader.getAttachFileName(this.recordingAudio));
        this.recordingAudioFile = r0;
        if (startRecord(r0.getPath(), this.sampleRate) != 0) goto L8;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda30(this));
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        FileLog.d("cant resume audio encoder");
        return;
    L10:
        return;
    L8:
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda31(this));
    }

    private /* synthetic */ void lambda$toggleRecordingPause$32(boolean r3) {
        if (this.recordingAudio != null) goto L5;
        return;
    L5:
        if (this.recordingAudioFile == null) goto L17;
        boolean r0 = this.audioRecorderPaused;
        this.audioRecorderPaused = !r0;
        if (r0 == true) goto L14;
        AudioRecord r02 = this.audioRecorder;
        if (r02 == null) goto L18;
        this.sendAfterDone = 4;
        r02.stop();
        this.audioRecorder.release();
        this.audioRecorder = null;
        this.recordQueue.postRunnable(new MediaController$$ExternalSyntheticLambda55(this, r3));
        return;
    L18:
        return;
    L14:
        this.recordQueue.cancelRunnable(this.recordRunnable);
        this.recordQueue.postRunnable(new MediaController$$ExternalSyntheticLambda56(this));
        return;
    }

    private /* synthetic */ void lambda$trimCurrentRecording$26(File r8, long r9, long r11, Runnable r13) {
        if (cropOpusFile(this.recordingAudioFile.getAbsolutePath(), r8.getAbsolutePath(), r9, r11) == false) goto L11;
        File r92 = this.recordingAudioFile;
        if (r92 == null) goto L7;
        r92.delete();
    L7:
        this.recordingAudioFile = r8;
        this.recordTimeCount = r11 - r9;
        if (r13 == null) goto L12;
        AndroidUtilities.runOnUIThread(r13);
        return;
    L12:
        return;
    }

    public static void loadGalleryPhotosAlbums(int r2) {
        Thread r0 = new Thread(new MediaController$$ExternalSyntheticLambda20(r2));
        r0.setPriority(1);
        r0.start();
    }

    public static int makeVideoBitrate(int r3, int r4, int r5, int r6, int r7) {
        if (Math.min(r6, r7) < 2160) goto L6;
        int r0 = 62000000;
    L21:
        int r32 = (int) (r5 / Math.min(r3 / r6, r4 / r7));
        int r42 = (int) (getVideoBitrateWithFactor(1.0f) / (921600.0f / (r7 * r6)));
        if (r5 >= r42) goto L24;
        return r32;
    L24:
        if (r32 <= r0) goto L27;
        return r0;
    L27:
        return Math.max(r32, r42);
    L6:
        if (Math.min(r6, r7) < 1440) goto L9;
        r0 = 24000000;
        goto L21
    L9:
        if (Math.min(r6, r7) < 1080) goto L12;
        r0 = 12000000;
        goto L21
    L12:
        if (Math.min(r6, r7) < 720) goto L15;
        r0 = 7500000;
        goto L21
    L15:
        if (Math.min(r6, r7) < 480) goto L18;
        r0 = 4000000;
        goto L21
    L18:
        if (Math.min(r6, r7) < 360) goto L20;
        r0 = 1500000;
        goto L21
    L20:
        r0 = DurationKt.NANOS_IN_MILLIS;
        goto L21
    }

    private void pauseInBackgroundIfNeeded() {
        MessageObject r0 = getPlayingMessageObject();
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isVoice() == false) goto L10;
        if (ExteraConfig.getPauseOnMinimizeVoice() == false) goto L10;
    L14:
        if (isPlayingMessage(r0) == true) goto L16;
        return;
    L16:
        if (isMessagePaused() == true) goto L22;
        pauseMessage(r0);
        return;
    L22:
        return;
    L10:
        if (r0.isRoundVideo() == true) goto L12;
        return;
    L12:
        if (ExteraConfig.getPauseOnMinimizeRound() == true) goto L14;
    }

    private void playNextMessageWithoutOrder(boolean r7) {
        if (SharedConfig.shuffleMusic == false) goto L5;
        ArrayList<MessageObject> r0 = this.shuffledPlaylist;
    L7:
        if (r7 == false) goto L23;
        int r4 = SharedConfig.repeatMode;
        if (r4 == 2) goto L14;
        if (r4 != 1) goto L23;
        if (r0.size() != 1) goto L23;
    L14:
        if (this.forceLoopCurrentPlaylist == true) goto L23;
        cleanupPlayer(false, false);
        int r72 = this.currentPlaylistNum;
        if (r72 >= 0) goto L18;
        return;
    L18:
        if (r72 >= r0.size()) goto L72;
        MessageObject r73 = r0.get(this.currentPlaylistNum);
        r73.audioProgress = 0.0f;
        r73.audioProgressSec = 0;
        playMessage(r73);
        return;
    L72:
        return;
    L23:
        if (SharedConfig.playOrderReversed == false) goto L25;
        int r42 = 1;
    L27:
        if (traversePlaylist(r0, r42) == false) goto L55;
        if (r7 == false) goto L55;
        if (SharedConfig.repeatMode != 0) goto L55;
        if (this.forceLoopCurrentPlaylist == true) goto L55;
        VideoPlayer r74 = this.audioPlayer;
        if (r74 != null) goto L38;
        if (this.videoPlayer != null) goto L38;
        return;
    L38:
        if (r74 == null) goto L47;
        MusicListenReporter r75 = this.reporter;
        if (r75 == null) goto L65;
        r75.destroy();
        this.reporter = null;
    L65:
        this.audioPlayer.releasePlayer(true);     // Catch: Exception -> L44
    L46:
        this.audioPlayer = null;
        Theme.unrefAudioVisualizeDrawable(this.playingMessageObject);
    L53:
        stopProgressTimer();
        this.lastProgress = 0;
        this.isPaused = true;
        MessageObject r76 = this.playingMessageObject;
        r76.audioProgress = 0.0f;
        r76.audioProgressSec = 0;
        NotificationCenter.getInstance(r76.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), 0});
        NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
        return;
    L44:
        e = move-exception;
        FileLog.e(e);
        goto L46
    L47:
        this.currentAspectRatioFrameLayout = null;
        this.currentTextureViewContainer = null;
        this.currentAspectRatioFrameLayoutReady = false;
        this.currentTextureView = null;
        this.videoPlayer.releasePlayer(true);
        this.videoPlayer = null;
        this.baseActivity.getWindow().clearFlags(128);     // Catch: Exception -> L50
    L52:
        AndroidUtilities.cancelRunOnUIThread(this.setLoadingRunnable);
        FileLoader.getInstance(this.playingMessageObject.currentAccount).removeLoadingVideo(this.playingMessageObject.getDocument(), true, false);
    L50:
        e = move-exception;
        FileLog.e(e);
    L55:
        int r77 = this.currentPlaylistNum;
        if (r77 >= 0) goto L58;
        return;
    L58:
        if (r77 >= r0.size()) goto L73;
        MessageObject r78 = this.playingMessageObject;
        if (r78 == null) goto L63;
        r78.resetPlayingProgress();
    L63:
        this.playMusicAgain = true;
        playMessage(r0.get(this.currentPlaylistNum));
        return;
    L73:
        return;
    L25:
        r42 = -1;
        goto L27
    L5:
        r0 = this.playlist;
        goto L7
    }

    private void processMediaObserver(Uri r14) {
        Cursor r1 = null;
        Point r0 = AndroidUtilities.getRealScreenSize();     // Catch: Throwable -> L13 Exception -> L15
        r1 = ApplicationLoader.applicationContext.getContentResolver().query(r14, this.mediaProjections, null, null, "date_added DESC LIMIT 1");     // Catch: Throwable -> L13 Exception -> L15
        ArrayList r142 = new ArrayList();     // Catch: Throwable -> L13 Exception -> L15
        if (r1 == null) goto L43;
    L6:
        if (r1.moveToNext() == false) goto L41;
        String r2 = r1.getString(0);     // Catch: Throwable -> L13 Exception -> L15
        String r4 = r1.getString(1);     // Catch: Throwable -> L13 Exception -> L15
        String r5 = r1.getString(2);     // Catch: Throwable -> L13 Exception -> L15
        long r6 = r1.getLong(3);     // Catch: Throwable -> L13 Exception -> L15
        String r8 = r1.getString(4);     // Catch: Throwable -> L13 Exception -> L15
        int r9 = r1.getInt(5);     // Catch: Throwable -> L13 Exception -> L15
        int r10 = r1.getInt(6);     // Catch: Throwable -> L13 Exception -> L15
        if (r2 != null) goto L11;
    L17:
        if (r4 != null) goto L19;
    L20:
        if (r5 != null) goto L22;
    L23:
        if (r8 == null) goto L6;
        if (r8.toLowerCase().contains("screenshot") == false) goto L6;
    L26:
        if (r9 == 0) goto L28;
        if (r10 == 0) goto L28;
    L29:
        if (r9 <= 0) goto L38;
        if (r10 <= 0) goto L38;
        int r22 = r0.x;     // Catch: Throwable -> L13 Exception -> L40
        if (r9 == r22) goto L34;
    L35:
        if (r10 != r22) goto L6;
        if (r9 != r0.y) goto L6;
    L34:
        if (r10 == r0.y) goto L38;
    L40:
        r142.add(Long.valueOf(r6));     // Catch: Throwable -> L13 Exception -> L15
    L38:
        r142.add(Long.valueOf(r6));     // Catch: Throwable -> L13 Exception -> L40
    L28:
        BitmapFactory.Options r42 = new BitmapFactory.Options();     // Catch: Throwable -> L13 Exception -> L40
        r42.inJustDecodeBounds = true;     // Catch: Throwable -> L13 Exception -> L40
        BitmapFactory.decodeFile(r2, r42);     // Catch: Throwable -> L13 Exception -> L40
        r9 = r42.outWidth;     // Catch: Throwable -> L13 Exception -> L40
        r10 = r42.outHeight;     // Catch: Throwable -> L13 Exception -> L40
        goto L29
    L22:
        if (r5.toLowerCase().contains("screenshot") == true) goto L26;
    L19:
        if (r4.toLowerCase().contains("screenshot") == true) goto L26;
    L11:
        if (r2.toLowerCase().contains("screenshot") == true) goto L26;
    L41:
        r1.close();     // Catch: Throwable -> L13 Exception -> L15
    L43:
        if (r142.isEmpty() == true) goto L45;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda29(this, r142));     // Catch: Throwable -> L13 Exception -> L15
    L45:
        if (r1 != null) goto L64;
        return;
    L64:
        r1.close();     // Catch: Exception -> L55
        return;
    L87:
        return;
    L15:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13
        if (r1 != null) goto L64;
        return;
    L13:
        th = move-exception;
        if (r1 == null) goto L88;
        r1.close();     // Catch: Exception -> L56
        throw th;
    L89:
        throw th;
    L88:
        throw th;
    }

    private void raiseToSpeakUpdated(boolean r14) {
        if (this.recordingAudio == null) goto L6;
        toggleRecordingPause(false);
        return;
    L6:
        if (r14 == false) goto L20;
        int r1 = this.raiseChat.getCurrentAccount();
        long r2 = this.raiseChat.getDialogId();
        MessageObject r5 = this.raiseChat.getThreadMessage();
        int r7 = this.raiseChat.getClassGuid();
        ChatActivity r4 = this.raiseChat;
        MessageSuggestionParams r6 = null;
        if (r4 == null) goto L10;
        SendMessageChatArguments r9 = r4.getMessageChatSendParams();
    L11:
        ChatActivity r42 = this.raiseChat;
        if (r42 == null) goto L14;
        long r10 = r42.getSendMonoForumPeerId();
    L15:
        ChatActivity r43 = this.raiseChat;
        if (r43 == null) goto L18;
        r6 = r43.getSendMessageSuggestionParams();
    L18:
        startRecording(r1, r2, null, r5, null, r7, false, r9, r10, r6);
        return;
    L14:
        r10 = 0;
        goto L15
    L10:
        r9 = null;
        goto L11
    L20:
        stopRecording(2, false, 0, false, 0);
    }

    private boolean restoreMusicPlaylistState() {
        SavedMusicPlaylistState r0 = this.savedMusicPlaylistState;
        if (r0 != null) goto L5;
        return false;
    L5:
        this.savedMusicPlaylistState = null;
        if (SharedConfig.shuffleMusic == false) goto L8;
        ArrayList<MessageObject> r2 = this.shuffledPlaylist;
    L9:
        if (r2 != null) goto L11;
        return false;
    L11:
        int r3 = this.currentPlaylistNum;
        if (r3 >= 0) goto L14;
    L26:
        return false;
    L14:
        if (r3 >= r2.size()) goto L26;
        MessageObject r22 = r2.get(this.currentPlaylistNum);
        if (r22 != null) goto L20;
        return false;
    L20:
        if (r22.getDialogId() != r0.playingMessage.getDialogId()) goto L26;
        if (r22.getId() != r0.playingMessage.getId()) goto L26;
        this.playMusicAgain = false;
        float r32 = r0.progress;
        r22.forceSeekTo = r32;
        r22.audioProgress = r32;
        r22.audioProgressMs = r0.progressMs;
        r22.audioProgressSec = r0.progressSec;
        playMessage(r22);
        pauseMessage(r22, false);
        return true;
    L8:
        r2 = this.playlist;
        goto L9
    }

    private boolean resumeAudio(MessageObject r7) {
        if (this.audioPlayer == null) goto L5;
    L6:
        if (r7 != null) goto L8;
    L38:
        return false;
    L8:
        if (this.playingMessageObject == null) goto L38;
        if (isSamePlayingMessage(r7) == false) goto L38;
        startProgressTimer(this.playingMessageObject);     // Catch: Exception -> L15
        ValueAnimator r0 = this.audioVolumeAnimator;     // Catch: Exception -> L15
        if (r0 == null) goto L18;
        r0.removeAllListeners();     // Catch: Exception -> L15
        this.audioVolumeAnimator.cancel();     // Catch: Exception -> L15
    L18:
        if (r7.isVoice() == false) goto L20;
    L22:
        this.audioVolume = 1.0f;     // Catch: Exception -> L15
        setPlayerVolume();     // Catch: Exception -> L15
    L23:
        VideoPlayer r02 = this.audioPlayer;     // Catch: Exception -> L15
        if (r02 == null) goto L26;
        r02.play();     // Catch: Exception -> L15
    L29:
        checkAudioFocus(r7, true);     // Catch: Exception -> L15
        this.isPaused = false;     // Catch: Exception -> L15
        NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});     // Catch: Exception -> L15
        CastSync.check(1);     // Catch: Exception -> L34
        if (this.ignorePlayerUpdate == true) goto L36;
        CastSync.setPlaying(true);     // Catch: Exception -> L34
    L36:
        return true;
    L34:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L15
        goto L36
    L26:
        VideoPlayer r03 = this.videoPlayer;     // Catch: Exception -> L15
        if (r03 == null) goto L29;
        r03.play();     // Catch: Exception -> L15
        goto L29
    L20:
        if (r7.isRoundVideo() == true) goto L22;
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{this.audioVolume, 1.0f});     // Catch: Exception -> L15
        this.audioVolumeAnimator = r04;     // Catch: Exception -> L15
        r04.addUpdateListener(this.audioVolumeUpdateListener);     // Catch: Exception -> L15
        this.audioVolumeAnimator.setDuration(300);     // Catch: Exception -> L15
        this.audioVolumeAnimator.start();     // Catch: Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L38
    L5:
        if (this.videoPlayer == null) goto L38;
        goto L6
    }

    public static void saveFile(String r6, Context r7, int r8, String r9, String r10) {
        saveFile(r6, r7, r8, r9, r10, null);
    }

    public static void saveFile(String r7, Context r8, int r9, String r10, String r11, Utilities.Callback<Uri> r12) {
        saveFile(r7, r8, r9, r10, r11, r12, true);
    }

    public static void saveFile(String r11, Context r12, int r13, String r14, String r15, Utilities.Callback<Uri> r16, boolean r17) {
        if (r11 == null) goto L29;
        if (r12 != null) goto L6;
        return;
    L6:
        if (TextUtils.isEmpty(r11) == true) goto L13;
        File r0 = new File(r11);
        if (r0.exists() == false) goto L13;
        if (AndroidUtilities.isInternalUri(Uri.fromFile(r0)) == true) goto L13;
        File r4 = r0;
    L14:
        if (r4 == null) goto L32;
        boolean[] r7 = {false};
        if (r4.exists() == false) goto L30;
        boolean[] r10 = new boolean[1];
        if (r13 != 0) goto L27;
    L24:
        AlertDialog r6 = null;
    L25:
        new Thread(new MediaController$$ExternalSyntheticLambda12(r13, r4, r14, r6, r7, r15, r16, r10)).start();
        return;
    L27:
        AlertDialog r2 = new AlertDialog(r12, 2);     // Catch: Exception -> L22
        r2.setMessage(LocaleController.getString(R.string.Loading));     // Catch: Exception -> L22
        r2.setCanceledOnTouchOutside(false);     // Catch: Exception -> L22
        r2.setCancelable(true);     // Catch: Exception -> L22
        r2.setOnCancelListener(new MediaController$$ExternalSyntheticLambda10(r7));     // Catch: Exception -> L22
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda11(r10, r2), 250);     // Catch: Exception -> L22
        r6 = r2;
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L30:
        return;
    L32:
        return;
    L13:
        r4 = null;
        goto L14
    }

    public static void saveFile(String r8, String r9, Context r10, Utilities.Callback<Uri> r11) {
        if (TextUtils.isEmpty(r8) == false) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r9) == true) goto L30;
        if (r10 == null) goto L32;
        File r2 = new File(r8);
        File r3 = new File(r9);
        if (r2.exists() == false) goto L25;
        if (r3.exists() == false) goto L25;
        if (AndroidUtilities.isInternalUri(Uri.fromFile(r2)) == false) goto L16;
        return;
    L16:
        if (AndroidUtilities.isInternalUri(Uri.fromFile(r3)) == true) goto L33;
        boolean[] r4 = {false};
        boolean[] r7 = new boolean[1];
        AlertDialog r0 = new AlertDialog(r10, 2);     // Catch: Exception -> L21
        r0.setMessage(LocaleController.getString(R.string.Loading));     // Catch: Exception -> L21
        r0.setCanceledOnTouchOutside(false);     // Catch: Exception -> L21
        r0.setCancelable(true);     // Catch: Exception -> L21
        r0.setOnCancelListener(new MediaController$$ExternalSyntheticLambda37(r4));     // Catch: Exception -> L21
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda38(r7, r0), 250);     // Catch: Exception -> L21
    L23:
        new Thread(new MediaController$$ExternalSyntheticLambda39(r2, r3, r4, r11, r0, r7)).start();
        return;
    L21:
        e = move-exception;
        FileLog.e(e);
        r0 = null;
        goto L23
    L33:
        return;
    L25:
        saveFile(r8, r10, 0, null, null, r11);
        return;
    L32:
        return;
    }

    private static Uri saveFileInternal(int r11, File r12, String r13) {
        String r1 = ExteraConfig.getCustomSavePath();     // Catch: Exception -> L6
        ContentValues r2 = new ContentValues();     // Catch: Exception -> L6
        String r3 = FileLoader.getFileExtension(r12);     // Catch: Exception -> L6
        if (r3 == null) goto L8;
        String r4 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(r3);     // Catch: Exception -> L6
    L10:
        if (r11 == 0) goto L12;
        if (r11 == 1) goto L12;
    L20:
        if (r11 != 0) goto L28;
        if (r13 != null) goto L23;
        r13 = AndroidUtilities.generateFileName(0, r3);     // Catch: Exception -> L6
    L23:
        Uri r112 = MediaStore.Images.Media.getContentUri("external_primary");     // Catch: Exception -> L6
        if (TextUtils.isEmpty(r1) == false) goto L26;
        String r14 = Environment.DIRECTORY_PICTURES;     // Catch: Exception -> L6
    L27:
        r2.put("relative_path", r14 + File.separator);     // Catch: Exception -> L6
        r2.put("_display_name", r13);     // Catch: Exception -> L6
        r2.put("mime_type", r4);     // Catch: Exception -> L6
    L52:
        r2.put("mime_type", r4);     // Catch: Exception -> L6
        Uri r113 = ApplicationLoader.applicationContext.getContentResolver().insert(r112, r2);     // Catch: Exception -> L6
        if (r113 == null) goto L55;
        FileInputStream r132 = new FileInputStream(r12);     // Catch: Exception -> L6
        AndroidUtilities.copyFile(r132, ApplicationLoader.applicationContext.getContentResolver().openOutputStream(r113));     // Catch: Exception -> L6
        r132.close();     // Catch: Exception -> L6
    L55:
        return r113;
    L26:
        r14 = new File(Environment.DIRECTORY_PICTURES, r1).getPath();     // Catch: Exception -> L6
        goto L27
    L28:
        if (r11 != 1) goto L37;
        if (r13 != null) goto L32;
        r13 = AndroidUtilities.generateFileName(1, r3);     // Catch: Exception -> L6
    L32:
        if (TextUtils.isEmpty(r1) == false) goto L34;
        String r114 = Environment.DIRECTORY_MOVIES;     // Catch: Exception -> L6
    L35:
        r2.put("relative_path", r114 + File.separator);     // Catch: Exception -> L6
        r112 = MediaStore.Video.Media.getContentUri("external_primary");     // Catch: Exception -> L6
        r2.put("_display_name", r13);     // Catch: Exception -> L6
        goto L52
    L34:
        r114 = new File(Environment.DIRECTORY_MOVIES, r1).getPath();     // Catch: Exception -> L6
        goto L35
    L37:
        if (r11 != 2) goto L45;
        if (r13 != null) goto L41;
        r13 = r12.getName();     // Catch: Exception -> L6
    L41:
        if (TextUtils.isEmpty(r1) == false) goto L43;
        String r115 = Environment.DIRECTORY_DOWNLOADS;     // Catch: Exception -> L6
    L44:
        r2.put("relative_path", r115 + File.separator);     // Catch: Exception -> L6
        r112 = MediaStore.Downloads.getContentUri("external_primary");     // Catch: Exception -> L6
        r2.put("_display_name", r13);     // Catch: Exception -> L6
        goto L52
    L43:
        r115 = new File(Environment.DIRECTORY_DOWNLOADS, r1).getPath();     // Catch: Exception -> L6
        goto L44
    L45:
        if (r13 != null) goto L48;
        r13 = r12.getName();     // Catch: Exception -> L6
    L48:
        if (TextUtils.isEmpty(r1) == false) goto L50;
        String r116 = Environment.DIRECTORY_MUSIC;     // Catch: Exception -> L6
    L51:
        r2.put("relative_path", r116 + File.separator);     // Catch: Exception -> L6
        r112 = MediaStore.Audio.Media.getContentUri("external_primary");     // Catch: Exception -> L6
        r2.put("_display_name", r13);     // Catch: Exception -> L6
        goto L52
    L50:
        r116 = new File(Environment.DIRECTORY_MUSIC, r1).getPath();     // Catch: Exception -> L6
    L12:
        if (r4 == null) goto L20;
        if (r4.startsWith("image") == false) goto L17;
        r11 = 0;
    L17:
        if (r4.startsWith(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L20;
        r11 = 1;
        goto L20
    L8:
        r4 = null;
    L6:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static void saveFilesFromMessages(Context r1, AccountInstance r2, ArrayList<MessageObject> r3, MessagesStorage.IntCallback r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.isEmpty() == true) goto L9;
        new MediaLoader(r1, r2, r3, r4).start();
        return;
    }

    private boolean saveMusicPlaylistStateIfNeeded() {
        MessageObject r0 = this.playingMessageObject;
        if (r0 == null) goto L11;
        if (r0.isMusic() == false) goto L11;
        if (this.playlist.isEmpty() == true) goto L11;
        this.savedMusicPlaylistState = new SavedMusicPlaylistState(this.playingMessageObject);
        return true;
    L11:
        if (this.savedMusicPlaylistState == null) goto L13;
        return true;
    L13:
        return false;
    }

    @SuppressLint({"NewApi"})
    public static MediaCodecInfo selectCodec(String r9) {
        int r0 = MediaCodecList.getCodecCount();
        MediaCodecInfo r1 = null;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L22;
        MediaCodecInfo r4 = MediaCodecList.getCodecInfoAt(r3);
        if (r4.isEncoder() == false) goto L21;
        String[] r5 = r4.getSupportedTypes();
        int r6 = r5.length;
        int r7 = 0;
    L8:
        if (r7 >= r6) goto L21;
        if (r5[r7].equalsIgnoreCase(r9) == false) goto L20;
        String r12 = r4.getName();
        if (r12 != null) goto L14;
    L19:
        r1 = r4;
        goto L20
    L14:
        if (r12.equals("OMX.SEC.avc.enc") == false) goto L18;
        if (r12.equals("OMX.SEC.AVC.Encoder") == false) goto L19;
    L18:
        return r4;
    L20:
        r7 = r7 + 1;
    L21:
        r3 = r3 + 1;
        goto L3
    L22:
        return r1;
    }

    @SuppressLint({"NewApi"})
    public static int selectColorFormat(MediaCodecInfo r4, String r5) {
        MediaCodecInfo.CodecCapabilities r52 = r4.getCapabilitiesForType(r5);
        int r0 = 0;
        int r1 = 0;
    L3:
        int[] r2 = r52.colorFormats;
        if (r0 >= r2.length) goto L15;
        int r22 = r2[r0];
        if (isRecognizedFormat(r22) == false) goto L14;
        if (r4.getName().equals("OMX.SEC.AVC.Encoder") == false) goto L13;
        if (r22 != 19) goto L13;
        r1 = r22;
    L13:
        return r22;
    L14:
        r0 = r0 + 1;
        goto L3
    L15:
        return r1;
    }

    private void setBluetoothScoOn(boolean r3) {
        AudioManager r2 = (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (SharedConfig.recordViaSco == false) goto L8;
        if (PermissionRequest.hasPermission("android.permission.BLUETOOTH_CONNECT") == true) goto L8;
        SharedConfig.recordViaSco = false;
        SharedConfig.saveConfig();
    L8:
        if (r2.isBluetoothScoAvailableOffCall() == true) goto L10;
    L11:
        if (r3 == true) goto L33;
    L12:
        BluetoothAdapter r0 = BluetoothAdapter.getDefaultAdapter();
        if (r0 != null) goto L32;
    L17:
        if (r3 == true) goto L34;
    L18:
        if (r3 == true) goto L20;
    L23:
        if (r3 == false) goto L25;
        return;
    L25:
        if (r2.isBluetoothScoOn() == false) goto L36;
        r2.stopBluetoothSco();     // Catch: Throwable -> L28 SecurityException -> L31
        return;
    L36:
        return;
    L20:
        if (r2.isBluetoothScoOn() == true) goto L23;
        r2.startBluetoothSco();     // Catch: Throwable -> L28 SecurityException -> L31
        return;
    L34:
        return;
    L32:
    L28:
        th = move-exception;
        FileLog.e(th);
        return;
    L16:
        if (r0.getProfileConnectionState(1) == 2) goto L18;
    L37:
        return;
    L33:
        return;
    L10:
        if (SharedConfig.recordViaSco == true) goto L12;
        goto L11
    }

    private void setPlayerVolume() {
        float r1 = 0.0f;
        if (this.isSilent == false) goto L6;
        float r0 = 0.0f;
    L9:
        VideoPlayer r2 = this.audioPlayer;     // Catch: Exception -> L25
        if (r2 != null) goto L12;
        VideoPlayer r4 = this.videoPlayer;     // Catch: Exception -> L25
        if (r4 != null) goto L20;
        return;
    L20:
        if (CastSync.isActive() == true) goto L23;
        r1 = r0;
    L23:
        r4.setVolume(r1);     // Catch: Exception -> L25
        return;
    L12:
        if (CastSync.isActive() == true) goto L15;
        r1 = r0 * this.audioVolume;     // Catch: Exception -> L25
    L15:
        r2.setVolume(r1);     // Catch: Exception -> L25
        return;
    L6:
        if (this.audioFocus == 1) goto L8;
        r0 = 1.0f;
        goto L9
    L8:
        r0 = VOLUME_DUCK;
    L25:
        e = move-exception;
        FileLog.e(e);
    }

    private void setUseFrontSpeaker(boolean r1) {
        this.useFrontSpeaker = r1;
        AudioManager r0 = NotificationsController.audioManager;
        if (r1 == false) goto L6;
        r0.setBluetoothScoOn(false);
        r0.setSpeakerphoneOn(false);
        return;
    L6:
        r0.setSpeakerphoneOn(true);
    }

    private void sortPlaylist() {
        Collections.sort(this.playlist, new MediaController$$ExternalSyntheticLambda41());
    }

    private void startAudioAgain(boolean r8) {
        MessageObject r0 = this.playingMessageObject;
        if (r0 == null) goto L42;
        NotificationCenter.getInstance(r0.currentAccount).postNotificationName(NotificationCenter.audioRouteChanged, new Object[]{Boolean.valueOf(this.useFrontSpeaker)});
        VideoPlayer r02 = this.videoPlayer;
        int r1 = 0;
        if (r02 != null) goto L8;
        VideoPlayer r03 = this.audioPlayer;
        if (r03 == null) goto L23;
        boolean r3 = true;
    L24:
        MessageObject r4 = this.playingMessageObject;
        float r5 = r4.audioProgress;
        int r6 = r4.audioPlayerDuration;
        if (r8 == true) goto L33;
        if (r03 == null) goto L33;
        if (r03.isPlaying() == false) goto L33;
        if ((r6 * r5) > 1.0f) goto L33;
        r4.audioProgress = 0.0f;
    L34:
        cleanupPlayer(false, true);
        playMessage(r4);
        if (r8 == false) goto L41;
        if (r3 == false) goto L39;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda42(this, r4), 100);
        return;
    L39:
        pauseMessage(r4);
        return;
    L41:
        return;
    L33:
        r4.audioProgress = r5;
        goto L34
    L23:
        r3 = false;
        goto L24
    L8:
        if (this.useFrontSpeaker == true) goto L11;
        r1 = 3;
    L11:
        r02.setStreamType(r1);
        if (r8 == false) goto L14;
        pauseMessage(this.playingMessageObject);
        return;
    L14:
        if (this.videoPlayer.getCurrentPosition() >= 1000) goto L16;
        this.videoPlayer.seekTo(0);
    L16:
        this.videoPlayer.play();
        return;
    }

    private void startProgressTimer(MessageObject r9) {
        Object r1 = this.progressTimerSync;
        monitor-enter(r1);
        java.util.Timer r0 = this.progressTimer;     // Catch: Throwable -> L8
        if (r0 != null) goto L19;
    L12:
        r9.getFileName();     // Catch: Throwable -> L8
        java.util.Timer r2 = new java.util.Timer();     // Catch: Throwable -> L8
        this.progressTimer = r2;     // Catch: Throwable -> L8
        r2.schedule(new AnonymousClass5(this, r9), 0, 17);     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L19:
        r0.cancel();     // Catch: Throwable -> L8 Exception -> L10
        this.progressTimer = null;     // Catch: Throwable -> L8 Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    private native int startRecord(String r1, int r2);

    private boolean startVideoConvertFromQueue() {
        if (this.videoConvertQueue.isEmpty() == true) goto L15;
        VideoConvertMessage r0 = this.videoConvertQueue.get(0);
        VideoEditedInfo r2 = r0.videoEditedInfo;
        Object r3 = this.videoConvertSync;
        monitor-enter(r3);
        if (r2 != null) goto L16;
    L10:
        monitor-exit(r3);     // Catch: Throwable -> L8
        VideoConvertRunnable.runConversion(r0);
        return true;
    L8:
        th = move-exception;
        throw th;
    L16:
        r2.canceled = false;     // Catch: Throwable -> L8
        goto L10
    L15:
        return false;
    }

    private void stopProgressTimer() {
        Object r0 = this.progressTimerSync;
        monitor-enter(r0);
        java.util.Timer r1 = this.progressTimer;     // Catch: Throwable -> L8
        if (r1 != null) goto L16;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L16:
        r1.cancel();     // Catch: Throwable -> L8 Exception -> L10
        this.progressTimer = null;     // Catch: Throwable -> L8 Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    private native void stopRecord();

    private void stopRecordingInternal(int r14, boolean r15, int r16, boolean r17, long r18) {
        if (r14 == 0) goto L10;
        File r5 = this.recordingAudioFile;
        if (r5 == null) goto L10;
        TLRPC.TL_document r6 = this.recordingAudio;
        File r4 = this.recordingPrevAudioFile;
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        FileLog.d("stop recording internal filename " + this.recordingAudioFile.getPath());
    L9:
        this.fileEncodingQueue.postRunnable(new MediaController$$ExternalSyntheticLambda23(this, r4, r5, r6, r14, r15, r16, r17, r18));
    L23:
        AudioRecord r152 = this.audioRecorder;     // Catch: Exception -> L19
        if (r152 == null) goto L21;
        r152.release();     // Catch: Exception -> L19
        this.audioRecorder = null;     // Catch: Exception -> L19
    L21:
        this.recordingAudio = null;
        this.recordingPrevAudioFile = null;
        this.recordingAudioFile = null;
        this.manualRecording = false;
        this.raiseToEarRecord = false;
        this.ignoreOnPause = false;
        return;
    L19:
        e = move-exception;
        FileLog.e(e);
    L10:
        AutoDeleteMediaTask.unlockFile(this.recordingAudioFile);
        File r142 = this.recordingAudioFile;
        if (r142 == null) goto L13;
        r142.delete();
    L13:
        requestRecordAudioFocus(false);
        goto L23
    }

    private boolean traversePlaylist(ArrayList<MessageObject> r7, int r8) {
        int r0 = this.currentPlaylistNum;
        int r4 = 0;
        if (ConnectionsManager.getInstance(UserConfig.selectedAccount).getConnectionState() != 2) goto L5;
        boolean r1 = true;
    L6:
        this.currentPlaylistNum += r8;
        if (r1 == false) goto L19;
    L9:
        if (this.currentPlaylistNum >= r7.size()) goto L19;
        int r2 = this.currentPlaylistNum;
        if (r2 < 0) goto L19;
        MessageObject r22 = r7.get(r2);
        if (r22 == null) goto L17;
        if (r22.mediaExists == true) goto L19;
    L17:
        this.currentPlaylistNum += r8;
    L19:
        if (this.currentPlaylistNum >= r7.size()) goto L25;
        if (this.currentPlaylistNum < 0) goto L25;
        return false;
    L25:
        if (this.currentPlaylistNum < r7.size()) goto L27;
        int r23 = 0;
    L28:
        this.currentPlaylistNum = r23;
        if (r1 == false) goto L54;
    L30:
        int r12 = this.currentPlaylistNum;
        if (r12 < 0) goto L46;
        if (r12 >= r7.size()) goto L46;
        int r13 = this.currentPlaylistNum;
        if (r8 <= 0) goto L38;
        if (r13 > r0) goto L46;
    L39:
        MessageObject r14 = r7.get(this.currentPlaylistNum);
        if (r14 == null) goto L44;
        if (r14.mediaExists == true) goto L46;
    L44:
        this.currentPlaylistNum += r8;
        goto L30
    L38:
        if (r13 >= r0) goto L39;
    L46:
        if (this.currentPlaylistNum >= r7.size()) goto L50;
        if (this.currentPlaylistNum >= 0) goto L54;
    L50:
        if (this.currentPlaylistNum >= r7.size()) goto L53;
        r4 = r7.size() - 1;
    L53:
        this.currentPlaylistNum = r4;
    L54:
        return true;
    L27:
        r23 = r7.size() - 1;
        goto L28
    L5:
        r1 = false;
        goto L6
    }

    private void updateVideoState(MessageObject r5, int[] r6, boolean r7, boolean r8, int r9) {
        if (this.videoPlayer != null) goto L6;
        return;
    L6:
        if (r9 == 4) goto L59;
        if (r9 == 1) goto L59;
        this.baseActivity.getWindow().addFlags(128);     // Catch: Exception -> L10
    L17:
        if (r9 != 3) goto L27;
        this.playerWasReady = true;
        MessageObject r62 = this.playingMessageObject;
        if (r62 != null) goto L21;
    L25:
        this.currentAspectRatioFrameLayoutReady = true;
        return;
    L21:
        if (r62.isVideo() == false) goto L23;
    L24:
        AndroidUtilities.cancelRunOnUIThread(this.setLoadingRunnable);
        FileLoader.getInstance(r5.currentAccount).removeLoadingVideo(this.playingMessageObject.getDocument(), true, false);
        goto L25
    L23:
        if (this.playingMessageObject.isRoundVideo() == false) goto L25;
    L27:
        if (r9 != 2) goto L40;
        if (r8 == false) goto L62;
        MessageObject r52 = this.playingMessageObject;
        if (r52 != null) goto L32;
        return;
    L32:
        if (r52.isVideo() == false) goto L34;
    L35:
        boolean r53 = this.playerWasReady;
        Runnable r4 = this.setLoadingRunnable;
        if (r53 == false) goto L38;
        r4.run();
        return;
    L38:
        AndroidUtilities.runOnUIThread(r4, 1000);
        return;
    L34:
        if (this.playingMessageObject.isRoundVideo() == true) goto L35;
        return;
    L62:
        return;
    L40:
        if (this.videoPlayer.isPlaying() == false) goto L67;
        if (r9 != 4) goto L68;
        MessageObject r54 = this.playingMessageObject;
        if (r54 == null) goto L54;
        if (r54.isVideo() == false) goto L54;
        if (r7 == true) goto L54;
        if (r6 != null) goto L49;
    L50:
        this.videoPlayer.seekTo(0);
        if (r6 == null) goto L69;
        r6[0] = r6[0] + 1;
        return;
    L69:
        return;
    L49:
        if (r6[0] < 4) goto L50;
    L54:
        if (restoreMusicPlaylistState() == true) goto L71;
        cleanupPlayer(true, hasNoNextVoiceOrRoundVideoMessage(), true, false);
        return;
    L71:
        return;
    L68:
        return;
    L67:
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
    L59:
        this.baseActivity.getWindow().clearFlags(128);     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    private native int writeFrame(ByteBuffer r1, int r2);

    private static void writeMotionPhoto(File r8, File r9, OutputStream r10, boolean[] r11) {
        String r1 = buildMotionPhotoXmp(r9.length());
        byte[] r2 = "http://ns.adobe.com/xap/1.0/\u0000".getBytes("UTF-8");
        byte[] r12 = r1.getBytes("UTF-8");
        int r3 = (r2.length + r12.length) + 2;
        if (r3 > 65535) goto L48;
        FileInputStream r4 = new FileInputStream(r8);
        int r5 = r4.read();     // Catch: Throwable -> L17
        int r6 = r4.read();     // Catch: Throwable -> L17
        if (r5 != 255) goto L42;
        if (r6 != 216) goto L42;
        r10.write(255);     // Catch: Throwable -> L17
        r10.write(Opcodes.ADD_INT_LIT8);     // Catch: Throwable -> L17
        r10.write(255);     // Catch: Throwable -> L17
        r10.write(Opcodes.SHR_INT_LIT8);     // Catch: Throwable -> L17
        r10.write((r3 >> 8) & 255);     // Catch: Throwable -> L17
        r10.write(r3 & 255);     // Catch: Throwable -> L17
        r10.write(r2);     // Catch: Throwable -> L17
        r10.write(r12);     // Catch: Throwable -> L17
        byte[] r0 = new byte[65536];     // Catch: Throwable -> L17
    L10:
        int r13 = r4.read(r0);     // Catch: Throwable -> L17
        if (r13 <= 0) goto L21;
        if (r11 == null) goto L19;
        if (r11[0] == false) goto L19;
        r4.close();
        return;
    L19:
        r10.write(r0, 0, r13);     // Catch: Throwable -> L17
        goto L10
    L21:
        r4.close();
        FileInputStream r02 = new FileInputStream(r9);
        byte[] r82 = new byte[65536];     // Catch: Throwable -> L30
    L23:
        int r92 = r02.read(r82);     // Catch: Throwable -> L30
        if (r92 <= 0) goto L34;
        if (r11 == null) goto L32;
        if (r11[0] == false) goto L32;
        r02.close();
        return;
    L32:
        r10.write(r82, 0, r92);     // Catch: Throwable -> L30
        goto L23
    L34:
        r02.close();
        return;
    L30:
        th = move-exception;
        r02.close();     // Catch: Throwable -> L38
    L40:
        throw th;
    L38:
        th = move-exception;
        th.addSuppressed(th);
    L42:
        throw new IOException("Not a JPEG: " + r8);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        r4.close();     // Catch: Throwable -> L45
    L47:
        throw th;
    L45:
        th = move-exception;
        th.addSuppressed(th);
        goto L47
    L48:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("XMP segment too large: ", r3);
    }

    public void cancelVideoConvert(MessageObject r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (this.videoConvertQueue.isEmpty() == true) goto L32;
        int r0 = 0;
    L7:
        if (r0 >= this.videoConvertQueue.size()) goto L33;
        VideoConvertMessage r1 = this.videoConvertQueue.get(r0);
        MessageObject r2 = r1.messageObject;
        if (r2.equals(r5) == false) goto L24;
        if (r2.currentAccount != r5.currentAccount) goto L24;
        if (r0 != 0) goto L22;
        Object r22 = this.videoConvertSync;
        monitor-enter(r22);
        r1.videoEditedInfo.canceled = true;     // Catch: Throwable -> L19
        monitor-exit(r22);     // Catch: Throwable -> L19
        return;
    L19:
        th = move-exception;
        throw th;
    L22:
        VideoConvertMessage r02 = this.videoConvertQueue.remove(r0);
        this.foregroundConvertingMessages.remove(r02);
        checkForegroundConvertMessage(true);
        return;
    L24:
        r0 = r0 + 1;
        goto L7
    L33:
        return;
    }

    public void checkIsNextMediaFileDownloaded() {
        MessageObject r0 = this.playingMessageObject;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isMusic() == false) goto L10;
        checkIsNextMusicFileDownloaded(this.playingMessageObject.currentAccount);
        return;
    }

    public void checkVolumeBarUI() {
        if (this.isSilent == true) goto L21;
        long r0 = System.currentTimeMillis();     // Catch: Exception -> L16
        if (Math.abs(r0 - volumeBarLastTimeShown) < 5000) goto L19;
        AudioManager r2 = (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);     // Catch: Exception -> L16
        if (this.useFrontSpeaker == false) goto L11;
        int r6 = 0;
    L12:
        int r3 = r2.getStreamVolume(r6);     // Catch: Exception -> L16
        if (r3 != 0) goto L20;
        r2.adjustStreamVolume(r6, r3, 1);     // Catch: Exception -> L16
        volumeBarLastTimeShown = r0;     // Catch: Exception -> L16
        return;
    L20:
        return;
    L11:
        r6 = 3;
        goto L12
    L19:
        return;
    L22:
        return;
    }

    public void cleanRecording(boolean r3) {
        this.recordingAudio = null;
        AutoDeleteMediaTask.unlockFile(this.recordingAudioFile);
        if (r3 == false) goto L10;
        File r1 = this.recordingAudioFile;
        if (r1 == null) goto L10;
        r1.delete();     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
    L10:
        this.recordingAudioFile = null;
        if (r3 == false) goto L15;
        File r32 = this.recordingPrevAudioFile;
        if (r32 == null) goto L15;
        r32.delete();
    L15:
        this.recordingPrevAudioFile = null;
        this.manualRecording = false;
        this.raiseToEarRecord = false;
        this.ignoreOnPause = false;
    }

    public void cleanup() {
        cleanupPlayer(true, true);
        this.audioInfo = null;
        int r1 = 0;
        this.playMusicAgain = false;
    L4:
        if (r1 >= 16) goto L6;
        DownloadController.getInstance(r1).cleanup();
        r1 = r1 + 1;
        goto L4
    L6:
        this.videoConvertQueue.clear();
        this.generatingWaveform.clear();
        this.savedMusicPlaylistState = null;
        this.voiceMessagesPlaylist = null;
        this.voiceMessagesPlaylistMap = null;
        clearPlaylist();
        cancelVideoConvert(null);
    }

    public void cleanupPlayer(boolean r2, boolean r3) {
        cleanupPlayer(r2, r3, false, false);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.coroutines.Continuation, org.telegram.ui.Components.VideoPlayer] */
    public void cleanupPlayer(boolean r10, boolean r11, boolean r12, boolean r13) {
        if (r11 == true) goto L4;
    L6:
        ?? r0 = this.audioPlayer;
        if (r0 == 0) goto L28;
        MusicListenReporter r132 = this.reporter;
        if (r132 == null) goto L11;
        r132.destroy();
        this.reporter = null;
    L11:
        ValueAnimator r133 = this.audioVolumeAnimator;
        if (r133 == null) goto L15;
        r133.probeCoroutineSuspended$kotlinx_coroutines_core(r0);
        this.audioVolumeAnimator.cancel();
    L15:
        if (CastSync.isActive() == false) goto L17;
    L107:
        this.audioPlayer.releasePlayer(true);     // Catch: Exception -> L25
    L27:
        this.audioPlayer = null;
        Theme.unrefAudioVisualizeDrawable(this.playingMessageObject);
    L49:
        stopProgressTimer();
        this.lastProgress = 0;
        this.isPaused = false;
        MessageObject r134 = this.playingMessageObject;
        if (r134 != null) goto L52;
        boolean r102 = false;
    L99:
        if (r102 == true) goto L104;
        if (r12 == false) goto L104;
        if (SharedConfig.enabledRaiseTo(true) == true) goto L104;
        ChatActivity r103 = this.raiseChat;
        stopRaiseToEarSensors(r103, false, false);
        this.raiseChat = r103;
    L104:
        if (r11 == false) goto L111;
        CastSync.stop();
        return;
    L111:
        return;
    L52:
        if (this.downloadingCurrentMessage == false) goto L54;
        FileLoader.getInstance(r134.currentAccount).cancelLoadFile(this.playingMessageObject.getDocument());
    L54:
        MessageObject r135 = this.playingMessageObject;
        if (r10 == false) goto L57;
        r135.resetPlayingProgress();
        NotificationCenter.getInstance(r135.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), 0});
    L57:
        this.playingMessageObject = null;
        this.downloadingCurrentMessage = false;
        if (r10 == false) goto L91;
        ArrayList<MessageObject> r104 = this.voiceMessagesPlaylist;
        int r02 = -1;
        if (r104 == null) goto L68;
        if (r12 == false) goto L67;
        r02 = r104.indexOf(r135);
        if (r02 < 0) goto L67;
        this.voiceMessagesPlaylist.remove(r02);
        this.voiceMessagesPlaylistMap.remove(r135.getId());
        if (this.voiceMessagesPlaylist.isEmpty() == false) goto L68;
        this.voiceMessagesPlaylist = null;
        this.voiceMessagesPlaylistMap = null;
    L67:
        this.voiceMessagesPlaylist = null;
        this.voiceMessagesPlaylistMap = null;
    L68:
        ArrayList<MessageObject> r105 = this.voiceMessagesPlaylist;
        if (r105 == null) goto L79;
        if (r02 >= r105.size()) goto L79;
        MessageObject r106 = this.voiceMessagesPlaylist.get(r02);
        playMessage(r106);
        if (r106.isRoundVideo() == true) goto L77;
        PipRoundVideoView r107 = this.pipRoundVideoView;
        if (r107 == null) goto L77;
        r107.close(true);
        this.pipRoundVideoView = null;
    L77:
        r102 = true;
    L89:
        if (r102 == true) goto L92;
        checkAudioFocus(r135, false);
    L92:
        if (r11 == false) goto L99;
        ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
        if (r102 == true) goto L99;
        TelegramMediaSession r136 = TelegramMediaSession.peekInstance();
        if (r136 == null) goto L99;
        r136.clearPlaybackState();
    L79:
        if (r135.isVoice() == true) goto L83;
        if (r135.isRoundVideo() == true) goto L83;
    L85:
        NotificationCenter.getInstance(r135.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidReset, new Object[]{Integer.valueOf(r135.getId()), Boolean.valueOf(r11)});
        this.pipSwitchingState = 0;
        PipRoundVideoView r108 = this.pipRoundVideoView;
        if (r108 == null) goto L88;
        r108.close(true);
        this.pipRoundVideoView = null;
    L88:
        r102 = false;
    L83:
        if (r135.getId() == 0) goto L85;
        startRecordingIfFromSpeaker();
        goto L85
    L91:
        r102 = false;
    L25:
        e = move-exception;
        FileLog.e(e);
        goto L27
    L17:
        if (this.audioPlayer.isPlaying() == false) goto L107;
        MessageObject r137 = this.playingMessageObject;
        if (r137 == null) goto L107;
        if (r137.isVoice() == true) goto L107;
        VideoPlayer r138 = this.audioPlayer;
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{this.audioVolume, 0.0f});
        r03.addUpdateListener(new MediaController$$ExternalSyntheticLambda14(this, r138));
        r03.addListener(new AnonymousClass6(this, r138));
        r03.setDuration(300);
        r03.start();
        goto L27
    L28:
        VideoPlayer r04 = this.videoPlayer;
        if (r04 == null) goto L49;
        this.currentAspectRatioFrameLayout = null;
        this.currentTextureViewContainer = null;
        this.currentAspectRatioFrameLayoutReady = false;
        this.isDrawingWasReady = false;
        this.currentTextureView = null;
        this.goingToShowMessageObject = null;
        if (r13 == false) goto L33;
        PhotoViewer.getInstance().injectVideoPlayer(this.videoPlayer);
        MessageObject r05 = this.playingMessageObject;
        this.goingToShowMessageObject = r05;
        NotificationCenter.getInstance(r05.currentAccount).postNotificationName(NotificationCenter.messagePlayingGoingToStop, new Object[]{this.playingMessageObject, Boolean.TRUE});
    L109:
        this.baseActivity.getWindow().clearFlags(128);     // Catch: Exception -> L43
    L46:
        if (this.playingMessageObject == null) goto L49;
        if (r13 == true) goto L49;
        AndroidUtilities.cancelRunOnUIThread(this.setLoadingRunnable);
        FileLoader.getInstance(this.playingMessageObject.currentAccount).removeLoadingVideo(this.playingMessageObject.getDocument(), true, false);
    L43:
        e = move-exception;
        FileLog.e(e);
        goto L46
    L33:
        long r6 = r04.getCurrentPosition();
        MessageObject r06 = this.playingMessageObject;
        if (r06 != null) goto L36;
    L40:
        this.videoPlayer.releasePlayer(true);
        this.videoPlayer = null;
        goto L109
    L36:
        if (r06.isVideo() == false) goto L40;
        if (r6 <= 0) goto L40;
        MessageObject r07 = this.playingMessageObject;
        r07.audioProgressMs = (int) r6;
        NotificationCenter.getInstance(r07.currentAccount).postNotificationName(NotificationCenter.messagePlayingGoingToStop, new Object[]{this.playingMessageObject, Boolean.FALSE});
        goto L40
    L4:
        if (restoreMusicPlaylistState() == false) goto L6;
    }

    public void clearRaiseChat(ChatActivity r2) {
        if (this.raiseChat != r2) goto L6;
        this.raiseChat = null;
        return;
    }

    public boolean currentPlaylistIsGlobalSearch() {
        if (this.playlistGlobalSearchParams == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r6, int r7, Object... r8) {
        char r1 = 1;
        int r2 = 0;
        if (r6 != NotificationCenter.fileLoaded) goto L5;
    L190:
        String r62 = (String) r8[0];
        MessageObject r82 = this.playingMessageObject;
        if (r82 != null) goto L193;
        return;
    L193:
        if (r82.currentAccount == r7) goto L195;
        return;
    L195:
        if (FileLoader.getAttachFileName(r82.getDocument()).equals(r62) == true) goto L197;
        return;
    L197:
        if (this.downloadingCurrentMessage == false) goto L200;
        this.playMusicAgain = true;
        playMessage(this.playingMessageObject);
        return;
    L200:
        if (this.audioInfo != null) goto L250;
        this.audioInfo = AudioInfo.getAudioInfo(FileLoader.getInstance(UserConfig.selectedAccount).getPathToMessage(this.playingMessageObject.messageOwner));     // Catch: Exception -> L203
        return;
    L203:
        e = move-exception;
        FileLog.e(e);
        return;
    L250:
        return;
    L5:
        if (r6 == NotificationCenter.httpFileDidLoad) goto L190;
        if (r6 != NotificationCenter.messagesDeleted) goto L32;
        if (((Boolean) r8[2]).booleanValue() == true) goto L251;
        long r63 = ((Long) r8[1]).longValue();
        ArrayList r83 = (ArrayList) r8[0];
        MessageObject r0 = this.playingMessageObject;
        if (r0 != null) goto L15;
    L19:
        ArrayList<MessageObject> r02 = this.voiceMessagesPlaylist;
        if (r02 != null) goto L22;
        return;
    L22:
        if (r02.isEmpty() == false) goto L24;
        return;
    L24:
        if (r63 == this.voiceMessagesPlaylist.get(0).messageOwner.peer_id.channel_id) goto L26;
        return;
    L26:
        if (r2 >= r83.size()) goto L254;
        Integer r64 = (Integer) r83.get(r2);
        MessageObject r72 = this.voiceMessagesPlaylistMap.get(r64.intValue());
        this.voiceMessagesPlaylistMap.remove(r64.intValue());
        if (r72 == null) goto L30;
        this.voiceMessagesPlaylist.remove(r72);
    L30:
        r2 = r2 + 1;
        goto L26
    L254:
        return;
    L15:
        if (r63 != r0.messageOwner.peer_id.channel_id) goto L19;
        if (r83.contains(Integer.valueOf(r0.getId())) == false) goto L19;
        cleanupPlayer(true, true);
        goto L19
    L251:
        return;
    L32:
        if (r6 != NotificationCenter.removeAllMessagesFromDialog) goto L40;
        long r65 = ((Long) r8[0]).longValue();
        MessageObject r84 = this.playingMessageObject;
        if (r84 != null) goto L36;
        return;
    L36:
        if (r84.getDialogId() != r65) goto L227;
        cleanupPlayer(false, true);
        return;
    L227:
        return;
    L40:
        if (r6 != NotificationCenter.musicDidLoad) goto L63;
        long r66 = ((Long) r8[0]).longValue();
        MessageObject r3 = this.playingMessageObject;
        if (r3 != null) goto L44;
        return;
    L44:
        if (r3.isMusic() == true) goto L46;
        return;
    L46:
        if (this.playingMessageObject.getDialogId() == r66) goto L48;
        return;
    L48:
        if (this.playingMessageObject.scheduled == true) goto L231;
        ArrayList r67 = (ArrayList) r8[1];
        ArrayList r73 = (ArrayList) r8[2];
        this.playlist.addAll(0, r67);
        this.playlist.addAll(r73);
        int r68 = this.playlist.size();
        int r74 = 0;
    L50:
        if (r74 >= r68) goto L52;
        MessageObject r85 = this.playlist.get(r74);
        this.playlistMap.put(Integer.valueOf(r85.getId()), r85);
        int[] r03 = this.playlistMaxId;
        r03[0] = Math.min(r03[0], r85.getId());
        r74 = r74 + 1;
        goto L50
    L52:
        sortPlaylist();
        if (SharedConfig.shuffleMusic == false) goto L55;
        buildShuffledPlayList();
    L60:
        this.playlistClassGuid = ConnectionsManager.generateClassGuid();
        return;
    L55:
        MessageObject r69 = this.playingMessageObject;
        if (r69 == null) goto L60;
        int r610 = this.playlist.indexOf(r69);
        if (r610 < 0) goto L60;
        this.currentPlaylistNum = r610;
        goto L60
    L231:
        return;
    L63:
        if (r6 != NotificationCenter.mediaDidLoad) goto L96;
        if (((Integer) r8[3]).intValue() == this.playlistClassGuid) goto L67;
        return;
    L67:
        if (this.playingMessageObject == null) goto L233;
        long r611 = ((Long) r8[0]).longValue();
        ((Integer) r8[4]).getClass();
        ArrayList r04 = (ArrayList) r8[2];
        DialogObject.isEncryptedDialog(r611);
        if (r611 == this.playlistMergeDialogId) goto L73;
        r1 = 0;
    L73:
        if (r04.isEmpty() == true) goto L75;
        this.playlistEndReached[r1] = ((Boolean) r8[5]).booleanValue();
    L75:
        int r612 = 0;
        int r75 = 0;
    L77:
        if (r612 >= r04.size()) goto L86;
        MessageObject r86 = (MessageObject) r04.get(r612);
        if (r86.isVoiceOnce() == true) goto L85;
        if (this.playlistMap.containsKey(Integer.valueOf(r86.getId())) == true) goto L85;
        r75 = r75 + 1;
        this.playlist.add(0, r86);
        this.playlistMap.put(Integer.valueOf(r86.getId()), r86);
        int[] r32 = this.playlistMaxId;
        r32[r1] = Math.min(r32[r1], r86.getId());
    L85:
        r612 = r612 + 1;
        goto L77
    L86:
        sortPlaylist();
        int r613 = this.playlist.indexOf(this.playingMessageObject);
        if (r613 < 0) goto L89;
        this.currentPlaylistNum = r613;
    L89:
        this.loadingPlaylist = false;
        if (SharedConfig.shuffleMusic == false) goto L92;
        buildShuffledPlayList();
    L92:
        if (r75 == 0) goto L234;
        NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.moreMusicDidLoad, new Object[]{Integer.valueOf(r75)});
        return;
    L234:
        return;
    L233:
        return;
    L96:
        if (r6 != NotificationCenter.didReceiveNewMessages) goto L126;
        if (((Boolean) r8[2]).booleanValue() == true) goto L252;
        ArrayList<MessageObject> r614 = this.voiceMessagesPlaylist;
        if (r614 != null) goto L103;
        return;
    L103:
        if (r614.isEmpty() == false) goto L105;
        return;
    L105:
        if (((Long) r8[0]).longValue() != this.voiceMessagesPlaylist.get(0).getDialogId()) goto L237;
        ArrayList r615 = (ArrayList) r8[1];
    L108:
        if (r2 >= r615.size()) goto L255;
        MessageObject r76 = (MessageObject) r615.get(r2);
        if (r76.isVoice() == true) goto L114;
        if (r76.isRoundVideo() == true) goto L114;
    L124:
        r2 = r2 + 1;
    L114:
        if (r76.isVoiceOnce() == true) goto L124;
        if (r76.isRoundOnce() == true) goto L124;
        if (this.voiceMessagesPlaylistUnread == true) goto L120;
    L123:
        this.voiceMessagesPlaylist.add(r76);
        this.voiceMessagesPlaylistMap.put(r76.getId(), r76);
        goto L124
    L120:
        if (r76.isContentUnread() == false) goto L124;
        if (r76.isOut() == true) goto L124;
    L255:
        return;
    L237:
        return;
    L252:
        return;
    L126:
        if (r6 != NotificationCenter.playerDidStartPlaying) goto L155;
        if (isCurrentPlayer((VideoPlayer) r8[0]) == true) goto L151;
        MessageObject r616 = getPlayingMessageObject();
        if (r616 != null) goto L132;
    L140:
        if (r2 != 0) goto L142;
    L146:
        if (r2 == 0) goto L148;
        this.wasPlayingAudioBeforePause = true;
    L148:
        pauseMessage(r616);
        return;
    L142:
        if (r616.isMusic() == false) goto L146;
        if (SharedConfig.pauseMusicOnMedia == true) goto L146;
        return;
    L132:
        if (isPlayingMessage(r616) == false) goto L140;
        if (isMessagePaused() == true) goto L140;
        if (r616.isMusic() == false) goto L138;
    L139:
        r2 = 1;
        goto L140
    L138:
        if (r616.isVoice() == false) goto L140;
    L151:
        if (LaunchActivity.isResumed == true) goto L238;
        pauseInBackgroundIfNeeded();
        return;
    L238:
        return;
    L155:
        if (r6 != NotificationCenter.stopAllHeavyOperations) goto L163;
        if (((Integer) r8[0]).intValue() == 4096) goto L159;
        return;
    L159:
        if (LaunchActivity.isResumed == true) goto L240;
        pauseInBackgroundIfNeeded();
        return;
    L240:
        return;
    L163:
        if (r6 != NotificationCenter.musicListLoaded) goto L241;
        MessagesController.SavedMusicList r617 = this.currentSavedMusicList;
        if (r617 != null) goto L167;
        return;
    L167:
        if (r8[0] != r617) goto L243;
        int r618 = r617.list.size() - this.playlist.size();
        this.playlist.clear();
        this.playlist.addAll(this.currentSavedMusicList.list);
        sortPlaylist();
        if (SharedConfig.shuffleMusic == false) goto L171;
        buildShuffledPlayList();
    L185:
        if (r618 == 0) goto L244;
        MessageObject r5 = this.playingMessageObject;
        if (r5 == null) goto L245;
        NotificationCenter.getInstance(r5.currentAccount).postNotificationName(NotificationCenter.moreMusicDidLoad, new Object[]{Integer.valueOf(r618)});
        return;
    L245:
        return;
    L244:
        return;
    L171:
        MessageObject r77 = this.playingMessageObject;
        if (r77 == null) goto L185;
        int r78 = this.playlist.indexOf(r77);
        if (r78 < 0) goto L176;
        this.currentPlaylistNum = r78;
        goto L185
    L176:
        int r79 = this.currentPlaylistNum;
        if (r79 >= 0) goto L179;
    L180:
        this.currentPlaylistNum = 0;
    L182:
        if (this.playlist.size() != 0) goto L184;
        cleanup();
        goto L185
    L184:
        playMessage(this.playlist.get(0));
        goto L185
    L179:
        if (r79 < this.playlist.size()) goto L182;
    L243:
        return;
    }

    public boolean findMessageInPlaylistAndPlay(MessageObject r3) {
        int r0 = this.playlist.indexOf(r3);
        if (r0 == (-1)) goto L5;
        playMessageAtIndex(r0);
        return true;
    L5:
        return playMessage(r3);
    }

    public void generateWaveform(MessageObject r5) {
        String r0 = r5.getId() + "_" + r5.getDialogId();
        String r1 = FileLoader.getInstance(r5.currentAccount).getPathToMessage(r5.messageOwner).getAbsolutePath();
        if (this.generatingWaveform.containsKey(r0) == false) goto L5;
        return;
    L5:
        this.generatingWaveform.put(r0, r5);
        Utilities.globalQueue.postRunnable(new MediaController$$ExternalSyntheticLambda13(this, r1, r0, r5));
    }

    public AudioInfo getAudioInfo() {
        return this.audioInfo;
    }

    public ChromecastMediaVariations getCurrentChromecastMedia() {
        MessageObject r0 = this.playingMessageObject;
        FileOutputStream r1 = null;
        File r12 = null;
        r12 = null;
        r12 = null;
        if (r0 != null) goto L5;
        return null;
    L5:
        String r02 = r0.getMusicTitle();
        String r2 = this.playingMessageObject.getMusicAuthor();
        TLRPC.Document r3 = this.playingMessageObject.getDocument();
        if (this.playingMessageObject.isRoundVideo() == false) goto L8;
    L11:
        MessageObject r4 = this.playingMessageObject;
        if (r4.attachPathExists == true) goto L14;
    L16:
        File r42 = null;
    L17:
        if (r42 != null) goto L19;
    L20:
        r42 = FileLoader.getInstance(this.playingMessageObject.currentAccount).getPathToMessage(this.playingMessageObject.messageOwner);
    L21:
        if (r42 != null) goto L23;
    L89:
        VideoPlayer r43 = this.videoPlayer;
        if (r43 == null) goto L97;
        StringBuilder r13 = new StringBuilder();
        if (r3 == null) goto L94;
        long r6 = r3.id;
    L95:
        r13.append(r6);
        r13.append(_UrlKt.FRAGMENT_ENCODE_SET);
        return r43.getCurrentChromecastMedia(r13.toString(), r02, r2);
    L94:
        r6 = this.playingMessageObject.getId();
        goto L95
    L97:
        VideoPlayer r44 = this.audioPlayer;
        if (r44 == null) goto L105;
        StringBuilder r14 = new StringBuilder();
        if (r3 == null) goto L102;
        long r62 = r3.id;
    L103:
        r14.append(r62);
        r14.append(_UrlKt.FRAGMENT_ENCODE_SET);
        return r44.getCurrentChromecastMedia(r14.toString(), r02, r2);
    L102:
        r62 = this.playingMessageObject.getId();
        goto L103
    L105:
        return null;
    L23:
        if (r42.exists() == false) goto L89;
        String r32 = this.playingMessageObject.getMimeType();
        Uri r45 = Uri.parse("file://" + r42.getAbsolutePath());
        com.google.android.gms.cast.MediaMetadata r5 = new com.google.android.gms.cast.MediaMetadata();
        AudioInfo r63 = this.audioInfo;
        if (r63 == null) goto L88;
        if (TextUtils.isEmpty(r63.getTitle()) == true) goto L30;
        r5.putString(com.google.android.gms.cast.MediaMetadata.KEY_TITLE, this.audioInfo.getTitle());
    L30:
        if (TextUtils.isEmpty(this.audioInfo.getArtist()) == true) goto L33;
        r5.putString(com.google.android.gms.cast.MediaMetadata.KEY_ARTIST, this.audioInfo.getArtist());
    L33:
        if (TextUtils.isEmpty(this.audioInfo.getAlbum()) == true) goto L36;
        r5.putString(com.google.android.gms.cast.MediaMetadata.KEY_ALBUM_TITLE, this.audioInfo.getAlbum());
    L36:
        if (TextUtils.isEmpty(this.audioInfo.getAlbumArtist()) == true) goto L39;
        r5.putString(com.google.android.gms.cast.MediaMetadata.KEY_ALBUM_ARTIST, this.audioInfo.getAlbumArtist());
    L39:
        if (TextUtils.isEmpty(this.audioInfo.getComposer()) == true) goto L42;
        r5.putString(com.google.android.gms.cast.MediaMetadata.KEY_COMPOSER, this.audioInfo.getComposer());
    L42:
        if (this.audioInfo.getDisc() == 0) goto L45;
        r5.putInt(com.google.android.gms.cast.MediaMetadata.KEY_DISC_NUMBER, this.audioInfo.getDisc());
    L45:
        if (this.audioInfo.getTrack() == 0) goto L48;
        r5.putInt(com.google.android.gms.cast.MediaMetadata.KEY_TRACK_NUMBER, this.audioInfo.getTrack());
    L48:
        if (this.audioInfo.getCover() == null) goto L88;
        File r64 = this.audioInfo.getCoverFile();
        if (r64 != null) goto L52;
    L53:
        File r65 = StoryEntry.makeCacheFile(UserConfig.selectedAccount, "jpg");
        Bitmap r7 = this.audioInfo.getCover();     // Catch: Throwable -> L66 Exception -> L68
        Bitmap.CompressFormat r8 = Bitmap.CompressFormat.JPEG;     // Catch: Throwable -> L66 Exception -> L68
        FileOutputStream r9 = new FileOutputStream(r65);     // Catch: Throwable -> L66 Exception -> L68
    L106:
        r7.compress(r8, 80, r9);     // Catch: Throwable -> L62 Exception -> L64
        r9.close();     // Catch: Exception -> L59
    L61:
        r12 = r65;
    L76:
        this.audioInfo.setCoverFile(r12);
        r64 = r12;
    L77:
        if (r64 == null) goto L88;
        if (r64.exists() == false) goto L88;
        r5.addImage(new WebImage(Uri.parse(ChromecastFileServer.getUrlToSource(ChromecastFileServer.getHost(), ChromecastController.getInstance().setCover(r64)))));
    L59:
        e = move-exception;
        FileLog.e(e);
    L64:
        e = e;
    L70:
        FileLog.e(e);     // Catch: Throwable -> L62
        if (r9 == null) goto L76;
        r9.close();     // Catch: Exception -> L74
    L74:
        e = move-exception;
        FileLog.e(e);
    L62:
        th = th;
        r1 = r9;
    L81:
        if (r1 != null) goto L113;
    L86:
        throw th;
    L113:
        r1.close();     // Catch: Exception -> L84
    L84:
        e = move-exception;
        FileLog.e(e);
    L68:
        e = e;
        r9 = null;
    L66:
        th = th;
        goto L81
    L52:
        if (r64.exists() == true) goto L77;
    L88:
        return ChromecastMediaVariations.of(ChromecastMedia.Builder.fromUri(r45, "/player_" + this.playingMessageObject.getId(), r32).setTitle(r02).setSubtitle(r2).setMetadata(r5).build());
    L19:
        if (r42.exists() == true) goto L21;
    L14:
        if (r4.messageOwner == null) goto L16;
        r42 = new File(this.playingMessageObject.messageOwner.attachPath);
        goto L17
    L8:
        if (this.playingMessageObject.isVideo() == true) goto L11;
        if (this.playingMessageObject.isMusic() == false) goto L89;
        goto L11
    }

    public VideoConvertMessage getCurrentForegroundConverMessage() {
        return this.currentForegroundConvertingVideo;
    }

    public long getCurrentPosition() {
        MessageObject r0 = this.playingMessageObject;
        if (r0 != null) goto L7;
        return -1;
    L7:
        return getProgressMs(r0);
    }

    public long getDuration() {
        VideoPlayer r2 = this.audioPlayer;
        if (r2 != null) goto L7;
        return 0;
    L7:
        return r2.getDuration();
    }

    public float getFastPlaybackSpeed(boolean r1) {
        if (r1 == false) goto L6;
        return this.fastMusicPlaybackSpeed;
    L6:
        return this.fastPlaybackSpeed;
    }

    public MessagesController.SavedMusicList getMusicList() {
        return this.currentSavedMusicList;
    }

    public float getPlaybackSpeed(boolean r1) {
        if (r1 == false) goto L6;
        return this.currentMusicPlaybackSpeed;
    L6:
        return this.currentPlaybackSpeed;
    }

    public MessageObject getPlayingMessageObject() {
        return this.playingMessageObject;
    }

    public int getPlayingMessageObjectNum() {
        return this.currentPlaylistNum;
    }

    public ArrayList<MessageObject> getPlaylist() {
        return this.playlist;
    }

    public long getProgressMs(MessageObject r5) {
        MessageObject r0 = this.playingMessageObject;
        if (this.audioPlayer == null) goto L5;
    L6:
        if (r5 == null) goto L19;
        if (r0 == null) goto L19;
        if (isSamePlayingMessage(r5) == false) goto L19;
        VideoPlayer r52 = this.audioPlayer;     // Catch: Exception -> L20
        if (r52 != null) goto L14;
        VideoPlayer r4 = this.videoPlayer;     // Catch: Exception -> L20
        if (r4 == null) goto L19;
        return r4.getCurrentPosition();
    L14:
        return r52.getCurrentPosition();
    L19:
        return -1;
    L5:
        if (this.videoPlayer == null) goto L19;
        goto L6
    }

    public VideoPlayer getVideoPlayer() {
        return this.videoPlayer;
    }

    public native byte[] getWaveform2(short[] r1, int r2);

    public boolean hasNoNextVoiceOrRoundVideoMessage() {
        MessageObject r0 = this.playingMessageObject;
        if (r0 != null) goto L5;
    L19:
        return true;
    L5:
        if (r0.isVoice() == false) goto L7;
    L8:
        ArrayList<MessageObject> r02 = this.voiceMessagesPlaylist;
        if (r02 == null) goto L19;
        if (r02.size() <= 1) goto L19;
        if (this.voiceMessagesPlaylist.contains(this.playingMessageObject) == false) goto L19;
        if (this.voiceMessagesPlaylist.indexOf(this.playingMessageObject) >= (this.voiceMessagesPlaylist.size() - 1)) goto L19;
        return false;
    L7:
        if (this.playingMessageObject.isRoundVideo() == false) goto L19;
        goto L8
    }

    public void injectVideoPlayer(VideoPlayer r10, MessageObject r11) {
        if (r10 == null) goto L13;
        if (r11 == null) goto L14;
        FileLoader.getInstance(r11.currentAccount).setLoadingVideoForPlayer(r11.getDocument(), true);
        this.playerWasReady = false;
        clearPlaylist();
        this.videoPlayer = r10;
        this.playingMessageObject = r11;
        int r5 = this.playerNum + 1;
        this.playerNum = r5;
        r10.setDelegate(new AnonymousClass7(this, r5, r11, null, true));
        this.currentAspectRatioFrameLayoutReady = false;
        TextureView r9 = this.currentTextureView;
        if (r9 == null) goto L8;
        this.videoPlayer.setTextureView(r9);
    L8:
        checkAudioFocus(r11, true);
        setPlayerVolume();
        this.isPaused = false;
        this.lastProgress = 0;
        MessageObject r92 = this.playingMessageObject;
        this.playingMessageObject = r11;
        if (SharedConfig.enabledRaiseTo(true) == true) goto L11;
        startRaiseToEarSensors(this.raiseChat);
    L11:
        startProgressTimer(this.playingMessageObject);
        NotificationCenter.getInstance(r11.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidStart, new Object[]{r11, r92});
        return;
    L14:
        return;
    }

    public boolean isBuffering() {
        VideoPlayer r0 = this.audioPlayer;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.isBuffering();
    }

    public boolean isCurrentPlayer(VideoPlayer r2) {
        if (this.videoPlayer != r2) goto L5;
        return true;
    L5:
        if (this.audioPlayer == r2) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isDownloadingCurrentMessage() {
        return this.downloadingCurrentMessage;
    }

    public boolean isGoingToShowMessageObject(MessageObject r1) {
        if (this.goingToShowMessageObject != r1) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isMessagePaused() {
        if (this.isPaused == false) goto L5;
        return true;
    L5:
        if (this.downloadingCurrentMessage == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public boolean isPiPShown() {
        if (this.pipRoundVideoView == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isPlayingMessage(MessageObject r6) {
        if (r6 == null) goto L8;
        if (r6.isRepostPreview == false) goto L8;
        return false;
    L8:
        if (this.audioPlayer == null) goto L10;
    L11:
        if (r6 == null) goto L25;
        MessageObject r1 = this.playingMessageObject;
        if (r1 == null) goto L25;
        long r12 = r1.eventId;
        if (r12 == 0) goto L23;
        if (r12 != r6.eventId) goto L23;
        boolean r5 = this.downloadingCurrentMessage;
    L21:
        return !r5;
    L23:
        if (isSamePlayingMessage(r6) == false) goto L25;
        r5 = this.downloadingCurrentMessage;
    L25:
        return false;
    L10:
        if (this.videoPlayer == null) goto L25;
        goto L11
    }

    public boolean isPlayingMessageAndReadyToDraw(MessageObject r2) {
        if (this.isDrawingWasReady == true) goto L5;
        return false;
    L5:
        if (isPlayingMessage(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isRecordingAudio() {
        if (this.recordStartRunnable == null) goto L5;
        return true;
    L5:
        if (this.recordingAudio != null) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isRecordingOrListeningByProximity() {
        if (this.proximityTouched == true) goto L5;
        return false;
    L5:
        if (isRecordingAudio() == true) goto L12;
        MessageObject r0 = this.playingMessageObject;
        if (r0 != null) goto L9;
        return false;
    L9:
        if (r0.isVoice() == false) goto L11;
        return true;
    L11:
        if (this.playingMessageObject.isRoundVideo() == false) goto L19;
        return true;
    L19:
        return false;
    L12:
        return true;
    }

    public boolean isRecordingPaused() {
        return this.audioRecorderPaused;
    }

    public boolean isStreamingCurrentAudio() {
        return this.isStreamingCurrentAudio;
    }

    public boolean isVideoDrawingReady() {
        AspectRatioFrameLayout r0 = this.currentAspectRatioFrameLayout;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isDrawingReady() == false) goto L10;
        return true;
    L10:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void loadMoreMusic() {
        MessagesController.SavedMusicList r1 = this.currentSavedMusicList;
        if (r1 == null) goto L7;
        r1.load();
        return;
    L7:
        if (this.loadingPlaylist == true) goto L60;
        MessageObject r12 = this.playingMessageObject;
        if (r12 != null) goto L11;
        return;
    L11:
        if (r12.scheduled == false) goto L13;
        return;
    L13:
        if (DialogObject.isEncryptedDialog(r12.getDialogId()) == true) goto L63;
        int r13 = this.playlistClassGuid;
        if (r13 == 0) goto L68;
        PlaylistGlobalSearchParams r2 = this.playlistGlobalSearchParams;
        if (r2 != null) goto L20;
        boolean[] r14 = this.playlistEndReached;
        if (r14[0] == true) goto L55;
        this.loadingPlaylist = true;
        AccountInstance.getInstance(this.playingMessageObject.currentAccount).getMediaDataController().loadMedia(this.playingMessageObject.getDialogId(), 50, this.playlistMaxId[0], 0, 4, 0, 1, this.playlistClassGuid, 0, null, null);
        return;
    L55:
        if (this.playlistMergeDialogId != 0) goto L57;
        return;
    L57:
        if (r14[1] == true) goto L67;
        this.loadingPlaylist = true;
        AccountInstance.getInstance(this.playingMessageObject.currentAccount).getMediaDataController().loadMedia(this.playlistMergeDialogId, 50, this.playlistMaxId[0], 0, 4, 0, 1, this.playlistClassGuid, 0, null, null);
        return;
    L67:
        return;
    L20:
        if (r2.endReached == false) goto L22;
        return;
    L22:
        if (this.playlist.isEmpty() == true) goto L65;
        int r22 = this.playlist.get(0).currentAccount;
        if (this.playlistGlobalSearchParams.dialogId == 0) goto L35;
        TLRPC.TL_messages_search r3 = new TLRPC.TL_messages_search();
        PlaylistGlobalSearchParams r10 = this.playlistGlobalSearchParams;
        r3.q = r10.query;
        r3.limit = 20;
        FiltersView.MediaFilterData r7 = r10.filter;
        if (r7 != null) goto L28;
        TLRPC.MessagesFilter r72 = new TLRPC.TL_inputMessagesFilterEmpty();
    L29:
        r3.filter = r72;
        r3.peer = AccountInstance.getInstance(r22).getMessagesController().getInputPeer(this.playlistGlobalSearchParams.dialogId);
        ArrayList<MessageObject> r73 = this.playlist;
        r3.offset_id = r73.get(r73.size() - 1).getId();
        PlaylistGlobalSearchParams r74 = this.playlistGlobalSearchParams;
        long r102 = r74.minDate;
        if (r102 <= 0) goto L32;
        r3.min_date = (int) (r102 / 1000);
    L32:
        long r103 = r74.maxDate;
        TLRPC.TL_messages_searchGlobal r32 = r3;
        if (r103 <= 0) goto L48;
        r3.min_date = (int) (r103 / 1000);
        r32 = r3;
    L48:
        this.loadingPlaylist = true;
        ConnectionsManager.getInstance(r22).sendRequest(r32, new MediaController$$ExternalSyntheticLambda21(this, r13, r22));
        return;
    L28:
        r72 = r7.filter;
        goto L29
    L35:
        TLRPC.TL_messages_searchGlobal r33 = new TLRPC.TL_messages_searchGlobal();
        r33.limit = 20;
        PlaylistGlobalSearchParams r75 = this.playlistGlobalSearchParams;
        r33.q = r75.query;
        r33.filter = r75.filter.filter;
        ArrayList<MessageObject> r76 = this.playlist;
        MessageObject r77 = r76.get(r76.size() - 1);
        r33.offset_id = r77.getId();
        PlaylistGlobalSearchParams r104 = this.playlistGlobalSearchParams;
        r33.offset_rate = r104.nextSearchRate;
        r33.flags |= 1;
        r33.folder_id = r104.folderId;
        TLRPC.Peer r78 = r77.messageOwner.peer_id;
        long r105 = r78.channel_id;
        if (r105 == 0) goto L38;
    L37:
        long r106 = -r105;
    L42:
        r33.offset_peer = MessagesController.getInstance(r22).getInputPeer(r106);
        PlaylistGlobalSearchParams r79 = this.playlistGlobalSearchParams;
        long r107 = r79.minDate;
        if (r107 <= 0) goto L45;
        r33.min_date = (int) (r107 / 1000);
    L45:
        long r108 = r79.maxDate;
        r32 = r33;
        if (r108 <= 0) goto L48;
        r33.min_date = (int) (r108 / 1000);
        r32 = r33;
        goto L48
    L38:
        r105 = r78.chat_id;
        if (r105 != 0) goto L37;
        r106 = r78.user_id;
        goto L42
    L65:
        return;
    L68:
        return;
    L63:
        return;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor r1, int r2) {
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int r2) {
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda15(this, r2));
    }

    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r18v3 */
    /* JADX WARN: Type inference failed for: r18v4 */
    /* JADX WARN: Type inference failed for: r18v5 */
    /* JADX WARN: Type inference failed for: r18v6 */
    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent r20) {
        if (this.sensorsStarted == true) goto L5;
        return;
    L5:
        if (VoIPService.getSharedInstance() == null) goto L8;
        return;
    L8:
        if (r20.sensor.getType() == 8) goto L10;
        Sensor r2 = r20.sensor;
        if (r2 == this.accelerometerSensor) goto L22;
        long r16 = 0;
        boolean r18 = true;
        int r15 = 2;
        if (r2 != this.linearSensor) goto L29;
        float[] r22 = this.linearAcceleration;
        float[] r3 = r20.values;
        r22[0] = r3[0];
        r22[1] = r3[1];
        r22[2] = r3[2];
        ?? r182 = r18;
    L32:
        Sensor r1 = r20.sensor;
        if (r1 != this.linearSensor) goto L35;
    L38:
        float[] r12 = this.gravity;
        float r23 = r12[0];
        float[] r4 = this.linearAcceleration;
        float r24 = ((r23 * r4[0]) + (r12[r182] * r4[r182])) + (r12[r15] * r4[r15]);
        int r13 = this.raisedToBack;
        if (r13 != 6) goto L41;
    L100:
        this.previousAccValue = r24;
        float[] r14 = this.gravityFast;
        if (r14[r182] > 2.5f) goto L103;
    L107:
        boolean r17 = false;
    L108:
        this.accelerometerVertical = r17;
    L110:
        if (this.raisedToBack != 6) goto L112;
    L113:
        this.lastAccelerometerDetected = System.currentTimeMillis();
    L115:
        if (this.manualRecording == false) goto L117;
    L131:
        boolean r19 = false;
    L133:
        if (SharedConfig.enabledRaiseTo(false) == false) goto L141;
        MessageObject r25 = this.playingMessageObject;
        if (r25 == null) goto L141;
        if (r25.isVoice() == false) goto L139;
    L140:
        boolean r26 = r182;
    L142:
        boolean r42 = this.proximityTouched;
        if (this.raisedToBack != 6) goto L145;
    L150:
        boolean r5 = r182;
    L152:
        if (this.useFrontSpeaker == false) goto L154;
    L157:
        boolean r6 = r182;
    L158:
        if (r5 == true) goto L161;
        if (r6 == true) goto L161;
    L169:
        boolean r7 = false;
    L170:
        PowerManager.WakeLock r9 = this.proximityWakeLock;
        if (r9 == null) goto L185;
        boolean r92 = r9.isHeld();
        if (r92 == false) goto L179;
        if (r7 == true) goto L179;
        if (BuildVars.LOGS_ENABLED == false) goto L178;
        FileLog.d("wake lock releasing (proximityDetected=" + r42 + ", accelerometerDetected=" + r5 + ", alreadyPlaying=" + r6 + ")");
    L178:
        this.proximityWakeLock.release();
    L179:
        if (r92 == true) goto L185;
        if (r7 == false) goto L185;
        if (BuildVars.LOGS_ENABLED == false) goto L184;
        FileLog.d("wake lock acquiring (proximityDetected=" + r42 + ", accelerometerDetected=" + r5 + ", alreadyPlaying=" + r6 + ")");
    L184:
        this.proximityWakeLock.acquire();
    L185:
        boolean r43 = this.proximityTouched;
        if (r43 == false) goto L212;
        if (r7 == false) goto L212;
        if (r19 == true) goto L190;
    L204:
        if (r26 == true) goto L206;
    L211:
        this.raisedToBack = 0;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
    L253:
        if (this.timeSinceRaise != r16) goto L255;
        return;
    L255:
        if (this.raisedToBack == 6) goto L257;
        return;
    L257:
        if (Math.abs(System.currentTimeMillis() - this.timeSinceRaise) <= 1000) goto L263;
        this.raisedToBack = 0;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
        this.timeSinceRaise = r16;
        return;
    L263:
        return;
    L206:
        if (this.useFrontSpeaker == true) goto L211;
        if (BuildVars.LOGS_ENABLED == false) goto L210;
        FileLog.d("start listen");
    L210:
        setUseFrontSpeaker(true);
        startAudioAgain(false);
        goto L211
    L190:
        if (this.recordStartRunnable != null) goto L204;
        if (this.raiseToEarRecord == true) goto L211;
        if (BuildVars.LOGS_ENABLED == false) goto L196;
        FileLog.d("start record");
    L196:
        boolean r110 = r182;
        this.useFrontSpeaker = r110;
        if (this.recordingAudio == null) goto L199;
    L200:
        this.raiseToEarRecord = r110;
        this.useFrontSpeaker = false;
        raiseToSpeakUpdated(r110);
    L202:
        if (this.useFrontSpeaker == false) goto L211;
        setUseFrontSpeaker(r110);
        goto L211
    L199:
        if (this.raiseChat.playFirstUnreadVoiceMessage() == true) goto L202;
    L212:
        if (r43 == false) goto L237;
        if (this.accelerometerSensor == null) goto L218;
        if (this.linearSensor != null) goto L237;
    L218:
        if (this.gravitySensor != null) goto L237;
        if (VoIPService.isAnyKindOfCallActive() == true) goto L237;
        if (this.playingMessageObject == null) goto L253;
        if (ApplicationLoader.mainInterfacePaused == true) goto L253;
        if (r26 == false) goto L253;
        if (this.useFrontSpeaker == true) goto L253;
        if (this.manualRecording == true) goto L253;
        if (forbidRaiseToListen() == true) goto L253;
        if (BuildVars.LOGS_ENABLED == false) goto L235;
        FileLog.d("start listen by proximity only");
    L235:
        setUseFrontSpeaker(true);
        startAudioAgain(false);
    L237:
        if (this.proximityTouched == true) goto L253;
        if (this.manualRecording == true) goto L253;
        if (this.raiseToEarRecord == false) goto L247;
        if (BuildVars.LOGS_ENABLED == false) goto L245;
        FileLog.d("stop record");
    L245:
        raiseToSpeakUpdated(false);
        this.raiseToEarRecord = false;
        this.ignoreOnPause = false;
        goto L253
    L247:
        if (this.useFrontSpeaker == false) goto L253;
        if (BuildVars.LOGS_ENABLED == false) goto L251;
        FileLog.d("stop listen");
    L251:
        this.useFrontSpeaker = false;
        startAudioAgain(true);
        this.ignoreOnPause = false;
    L161:
        if (forbidRaiseToListen() == true) goto L169;
        if (VoIPService.isAnyKindOfCallActive() == true) goto L169;
        if (r19 == true) goto L167;
        if (r26 == false) goto L169;
    L167:
        if (PhotoViewer.getInstance().isVisible() == true) goto L169;
        r7 = r182;
        goto L170
    L154:
        if (this.raiseToEarRecord == true) goto L157;
        r6 = false;
        goto L158
    L145:
        if (this.accelerometerVertical == true) goto L150;
        if ((System.currentTimeMillis() - this.lastAccelerometerDetected) < 60) goto L150;
        r5 = false;
        goto L152
    L139:
        if (this.playingMessageObject.isRoundVideo() == true) goto L140;
    L141:
        r26 = false;
        goto L142
    L117:
        if (this.playingMessageObject != null) goto L131;
        if (SharedConfig.enabledRaiseTo(r182) == false) goto L131;
        if (ApplicationLoader.isScreenOn == false) goto L131;
        if (this.inputFieldHasText == true) goto L131;
        if (this.allowStartRecord == false) goto L131;
        if (this.raiseChat == null) goto L131;
        if (this.callInProgress == true) goto L131;
        r19 = r182;
        goto L133
    L112:
        if (this.accelerometerVertical == false) goto L115;
    L103:
        if (Math.abs(r14[r15]) >= 4.0f) goto L107;
        if (Math.abs(this.gravityFast[0]) <= 1.5f) goto L107;
        r17 = r182;
        goto L108
    L41:
        if (r24 <= 0.0f) goto L45;
        if (this.previousAccValue <= 0.0f) goto L45;
    L48:
        if (r24 <= 0.0f) goto L55;
        if (r24 <= 15.0f) goto L52;
        boolean r44 = r182;
    L53:
        int r52 = r182;
    L59:
        int r62 = this.raisedToTopSign;
        if (r62 == 0) goto L80;
        if (r62 == r52) goto L80;
        int r53 = this.raisedToTop;
        if (r53 != 6) goto L73;
        if (r44 == false) goto L73;
        if (r13 >= 6) goto L100;
        int r111 = r13 + 1;
        this.raisedToBack = r111;
        if (r111 != 6) goto L100;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
        this.timeSinceRaise = System.currentTimeMillis();
        if (BuildVars.LOGS_ENABLED == false) goto L100;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L100;
        FileLog.d("motion detected");
    L73:
        if (r44 == true) goto L76;
        this.countLess++;
    L76:
        if (this.countLess == 10) goto L79;
        if (r53 != 6) goto L79;
        if (r13 == 0) goto L100;
    L79:
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.raisedToBack = 0;
        this.countLess = 0;
    L80:
        if (r44 == false) goto L91;
        if (r13 != 0) goto L91;
        if (r62 == 0) goto L84;
        if (r62 != r52) goto L91;
    L84:
        int r112 = this.raisedToTop;
        if (r112 >= 6) goto L100;
        if (this.proximityTouched == true) goto L100;
        this.raisedToTopSign = r52;
        int r113 = r112 + 1;
        this.raisedToTop = r113;
        if (r113 != 6) goto L100;
        this.countLess = 0;
    L91:
        if (r44 == true) goto L93;
        this.countLess++;
    L93:
        if (r62 == r52) goto L95;
    L99:
        this.raisedToBack = 0;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
        goto L100
    L95:
        if (this.countLess == 10) goto L99;
        if (this.raisedToTop != 6) goto L99;
        if (r13 == 0) goto L100;
    L52:
        r44 = false;
        goto L53
    L55:
        if (r24 >= (-15.0f)) goto L57;
        r44 = r182;
    L58:
        r52 = r15;
        goto L59
    L57:
        r44 = false;
    L45:
        if (r24 >= 0.0f) goto L100;
        if (this.previousAccValue >= 0.0f) goto L100;
    L35:
        if (r1 == this.gravitySensor) goto L38;
        if (r1 != this.accelerometerSensor) goto L110;
    L29:
        r182 = r18;
        if (r2 != this.gravitySensor) goto L32;
        float[] r27 = this.gravityFast;
        float[] r32 = this.gravity;
        float[] r45 = r20.values;
        float r54 = r45[0];
        r32[0] = r54;
        r27[0] = r54;
        float r55 = r45[1];
        r32[1] = r55;
        r27[1] = r55;
        float r46 = r45[2];
        r32[2] = r46;
        r27[2] = r46;
        r182 = r18;
        goto L32
    L22:
        if (this.lastTimestamp != 0) goto L24;
        double r28 = 0.9800000190734863d;
    L25:
        this.lastTimestamp = r20.timestamp;
        float[] r93 = this.gravity;
        double r10 = 1.0d - r28;
        float[] r142 = r20.values;
        r16 = 0;
        float r47 = (float) ((r93[0] * r28) + (r142[0] * r10));
        r93[0] = r47;
        r182 = 1;
        r15 = 2;
        float r56 = (float) ((r93[1] * r28) + (r142[1] * r10));
        r93[1] = r56;
        float r29 = (float) ((r28 * r93[2]) + (r10 * r142[2]));
        r93[2] = r29;
        float[] r33 = this.gravityFast;
        r33[0] = (r47 * 0.8f) + (r142[0] * 0.19999999f);
        r33[1] = (r56 * 0.8f) + (r142[1] * 0.19999999f);
        r33[2] = (r29 * 0.8f) + (r142[2] * 0.19999999f);
        float[] r210 = this.linearAcceleration;
        r210[0] = r142[0] - r93[0];
        r210[1] = r142[1] - r93[1];
        r210[2] = r142[2] - r93[2];
        goto L32
    L24:
        r28 = 1.0d / (((r20.timestamp - r2) / 1.0E9d) + 1.0d);
        goto L25
    L10:
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("proximity changed to " + r20.values[0] + " max value = " + r20.sensor.getMaximumRange());
    L12:
        float r211 = this.lastProximityValue;
        float r34 = r20.values[0];
        if (r211 == r34) goto L15;
        this.proximityHasDifferentValues = true;
    L15:
        this.lastProximityValue = r34;
        if (this.proximityHasDifferentValues == false) goto L18;
        this.proximityTouched = isNearToSensor(r34);
    L18:
        r16 = 0;
        r182 = 1;
        r15 = 2;
        goto L32
    }

    public void pauseByRewind() {
        VideoPlayer r0 = this.audioPlayer;
        if (r0 == null) goto L6;
        r0.pause();
        return;
    }

    public boolean pauseMessage(MessageObject r2) {
        return pauseMessage(r2, true);
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [float, kotlin.coroutines.Continuation] */
    public boolean pauseMessage(MessageObject r7, boolean r8) {
        if (this.audioPlayer == null) goto L5;
    L6:
        if (r7 != null) goto L8;
    L47:
        return false;
    L8:
        if (this.playingMessageObject == null) goto L47;
        if (isSamePlayingMessage(r7) == false) goto L47;
        stopProgressTimer();
    L27:
        e = move-exception;
        FileLog.e(e);
        this.isPaused = false;
        goto L47
    L14:
        if (this.audioPlayer == null) goto L31;
        if (r8 == true) goto L17;
    L30:
        this.audioPlayer.pause();     // Catch: Exception -> L27
    L34:
        checkAudioFocus(r7, false);     // Catch: Exception -> L27
        this.isPaused = true;     // Catch: Exception -> L27
        NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});     // Catch: Exception -> L27
        CastSync.check(1);     // Catch: Exception -> L40
        if (this.ignorePlayerUpdate == false) goto L38;
    L45:
        return true;
    L38:
        if (ChromecastController.getInstance().isCasting() == false) goto L42;
        ChromecastController.getInstance().setCurrentMediaAndCastIfNeeded(getCurrentChromecastMedia());     // Catch: Exception -> L40
    L42:
        CastSync.setPlaying(false);     // Catch: Exception -> L40
    L40:
        e = move-exception;
        FileLog.e(e);
        goto L45
    L17:
        if (CastSync.isActive() == true) goto L30;
        if (this.playingMessageObject.isVoice() == true) goto L30;
        double r2 = this.playingMessageObject.getDuration();     // Catch: Exception -> L27
        ?? r0 = 1.0f - this.playingMessageObject.audioProgress;     // Catch: Exception -> L27
        if ((r2 * ((double) r0)) <= 1.0d) goto L30;
        if (LaunchActivity.isResumed == false) goto L30;
        ValueAnimator r82 = this.audioVolumeAnimator;     // Catch: Exception -> L27
        if (r82 == null) goto L29;
        r82.probeCoroutineSuspended$kotlinx_coroutines_core(r0);     // Catch: Exception -> L27
        this.audioVolumeAnimator.cancel();     // Catch: Exception -> L27
    L29:
        ValueAnimator r83 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});     // Catch: Exception -> L27
        this.audioVolumeAnimator = r83;     // Catch: Exception -> L27
        r83.addUpdateListener(this.audioVolumeUpdateListener);     // Catch: Exception -> L27
        this.audioVolumeAnimator.setDuration(300);     // Catch: Exception -> L27
        this.audioVolumeAnimator.addListener(new AnonymousClass12(this));     // Catch: Exception -> L27
        this.audioVolumeAnimator.start();     // Catch: Exception -> L27
        goto L34
    L31:
        VideoPlayer r84 = this.videoPlayer;     // Catch: Exception -> L27
        if (r84 == null) goto L34;
        r84.pause();     // Catch: Exception -> L27
        goto L34
    L5:
        if (this.videoPlayer == null) goto L47;
        goto L6
    }

    public void playEmojiSound(AccountInstance r2, String r3, MessagesController.EmojiSound r4, boolean r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        Utilities.stageQueue.postRunnable(new MediaController$$ExternalSyntheticLambda5(this, r4, r2, r5));
    }

    public boolean playMessage(MessageObject r2) {
        return playMessage(r2, false);
    }

    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24, types: [org.telegram.messenger.MessageObject, org.telegram.ui.Components.VideoPlayer] */
    /* JADX WARN: Type inference failed for: r5v25 */
    public boolean playMessage(MessageObject r41, boolean r42) {
        if (r41 != null) goto L5;
        return false;
    L5:
        this.isSilent = r42;
        checkVolumeBarUI();
        if (this.audioPlayer != null) goto L10;
        if (this.videoPlayer != null) goto L10;
    L19:
        if (r41.isOut() == false) goto L21;
    L23:
        boolean r2 = !this.playMusicAgain;
        MessageObject r9 = this.playingMessageObject;
        if (r9 != null) goto L26;
        boolean r4 = false;
    L41:
        cleanupPlayer(r2, false);
        this.shouldSavePositionForCurrentAudio = null;
        this.lastSaveTime = 0;
        this.playMusicAgain = false;
        this.seekToProgressPending = 0.0f;
        String r22 = r41.messageOwner.attachPath;
        if (r22 != null) goto L44;
    L49:
        boolean r5 = false;
    L47:
        File r14 = null;
    L50:
        if (r14 == null) goto L52;
        File r15 = r14;
    L54:
        if (SharedConfig.streamMedia == true) goto L56;
    L69:
        boolean r23 = false;
    L71:
        if (r15 == r14) goto L89;
        r5 = r15.exists();
        if (r5 == true) goto L89;
        if (r23 == true) goto L89;
        FileLoader r0 = FileLoader.getInstance(r41.currentAccount);
        TLRPC.Document r24 = r41.getDocument();
        if (r41.shouldEncryptPhotoOrVideo() == false) goto L78;
        int r43 = 2;
    L79:
        r0.loadFile(r24, r41, 0, r43);
        this.downloadingCurrentMessage = true;
        this.isPaused = false;
        this.lastProgress = 0;
        this.audioInfo = null;
        this.playingMessageObject = r41;
        if (canStartMusicPlayerService() == true) goto L348;
        ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
    L87:
        NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
        return true;
    L348:
        ApplicationLoader.applicationContext.startService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));     // Catch: Throwable -> L84
    L84:
        th = move-exception;
        FileLog.e(th);
        goto L87
    L78:
        r43 = 0;
    L89:
        boolean r18 = r5;
        this.downloadingCurrentMessage = false;
        boolean r25 = r41.isMusic();
        int r52 = r41.currentAccount;
        if (r25 == false) goto L92;
        checkIsNextMusicFileDownloaded(r52);
    L93:
        AspectRatioFrameLayout r26 = this.currentAspectRatioFrameLayout;
        if (r26 == null) goto L96;
        this.isDrawingWasReady = false;
        r26.setDrawingReady(false);
    L96:
        boolean r27 = r41.isVideo();
        boolean r28 = r4;
        if (r41.isRoundVideo() == true) goto L99;
        if (r27 == true) goto L99;
        PipRoundVideoView r53 = this.pipRoundVideoView;
        if (r53 == null) goto L339;
        r53.close(true);
        this.pipRoundVideoView = null;
    L339:
        VideoPlayer r54 = new VideoPlayer();     // Catch: Exception -> L108
        this.audioPlayer = r54;     // Catch: Exception -> L108
        if (r41.isMusic() == true) goto L106;
    L110:
        boolean r11 = false;
    L111:
        r54.allowMultipleInstances = r11;     // Catch: Exception -> L108
        int r55 = this.playerNum + 1;     // Catch: Exception -> L108
        this.playerNum = r55;     // Catch: Exception -> L108
        this.audioPlayer.setDelegate(new AnonymousClass10(this, r55, r41));     // Catch: Exception -> L108
        this.audioPlayer.setAudioVisualizerDelegate(new AnonymousClass11(this));     // Catch: Exception -> L108
        if (r18 == true) goto L114;
        int r56 = FileLoader.getInstance(r41.currentAccount).getFileReference(r41);     // Catch: Exception -> L108
        TLRPC.Document r112 = r41.getDocument();     // Catch: Exception -> L108
        StringBuilder r12 = new StringBuilder("?account=");     // Catch: Exception -> L108
        r12.append(r41.currentAccount);     // Catch: Exception -> L108
        r12.append("&id=");     // Catch: Exception -> L108
        File r34 = r15;
        r12.append(r112.id);     // Catch: Exception -> L108
        r12.append("&hash=");     // Catch: Exception -> L108
        r12.append(r112.access_hash);     // Catch: Exception -> L108
        r12.append("&dc=");     // Catch: Exception -> L108
        r12.append(r112.dc_id);     // Catch: Exception -> L108
        r12.append("&size=");     // Catch: Exception -> L108
        r12.append(r112.size);     // Catch: Exception -> L108
        r12.append("&mime=");     // Catch: Exception -> L108
        r12.append(URLEncoder.encode(r112.mime_type, "UTF-8"));     // Catch: Exception -> L108
        r12.append("&rid=");     // Catch: Exception -> L108
        r12.append(r56);     // Catch: Exception -> L108
        r12.append("&name=");     // Catch: Exception -> L108
        r12.append(URLEncoder.encode(FileLoader.getDocumentFileName(r112), "UTF-8"));     // Catch: Exception -> L108
        r12.append("&reference=");     // Catch: Exception -> L108
        byte[] r02 = r112.file_reference;     // Catch: Exception -> L108
        if (r02 != null) goto L122;
        r02 = new byte[0];     // Catch: Exception -> L108
    L122:
        r12.append(Utilities.bytesToHex(r02));     // Catch: Exception -> L108
        this.audioPlayer.preparePlayer(Uri.parse("tg://" + r41.getFileName() + r12.toString()), "other");     // Catch: Exception -> L108
        this.isStreamingCurrentAudio = true;     // Catch: Exception -> L108
    L125:
        if (r41.isVoice() == false) goto L340;
        String r03 = r41.getFileName();     // Catch: Exception -> L108
        if (r03 == null) goto L137;
        if (r41.getDuration() < 300.0d) goto L137;
        float r29 = ApplicationLoader.applicationContext.getSharedPreferences("media_saved_pos", 0).getFloat(r03, -1.0f);     // Catch: Exception -> L108
        if (r29 > 0.0f) goto L133;
    L135:
        this.shouldSavePositionForCurrentAudio = r03;     // Catch: Exception -> L108
        goto L137
    L133:
        if (r29 >= 0.99f) goto L135;
        this.seekToProgressPending = r29;     // Catch: Exception -> L108
        r41.audioProgress = r29;     // Catch: Exception -> L108
    L137:
        if (Math.abs(this.currentPlaybackSpeed - 1.0f) <= 0.001f) goto L139;
        this.audioPlayer.setPlaybackSpeed(Math.round(this.currentPlaybackSpeed * 10.0f) / 10.0f);     // Catch: Exception -> L108
    L139:
        this.audioInfo = null;     // Catch: Exception -> L108
        if (r28 == true) goto L159;
        clearPlaylist();     // Catch: Exception -> L108
    L159:
        float r04 = r41.forceSeekTo;     // Catch: Exception -> L108
        if (r04 < 0.0f) goto L163;
        this.seekToProgressPending = r04;     // Catch: Exception -> L108
        r41.audioProgress = r04;     // Catch: Exception -> L108
        r41.forceSeekTo = -1.0f;     // Catch: Exception -> L108
    L163:
        if (r41.isMusic() == true) goto L165;
    L179:
        MusicListenReporter r05 = this.reporter;     // Catch: Exception -> L108
        if (r05 == null) goto L182;
        r05.destroy();     // Catch: Exception -> L108
        this.reporter = null;     // Catch: Exception -> L108
    L182:
        ExoPlayer r06 = this.audioPlayer.player;     // Catch: Exception -> L108
        if (r06 == null) goto L187;
        MusicListenReporter r210 = this.reporter;     // Catch: Exception -> L108
        if (r210 == null) goto L187;
        r06.addListener(r210.getPlayerListener(r06));     // Catch: Exception -> L108
    L187:
        VideoPlayer r07 = this.audioPlayer;     // Catch: Exception -> L108
        if (this.useFrontSpeaker == false) goto L190;
        int r211 = 0;
    L191:
        r07.setStreamType(r211);     // Catch: Exception -> L108
        this.audioPlayer.play();     // Catch: Exception -> L108
        if (r41.isVoice() == true) goto L197;
        ValueAnimator r08 = this.audioVolumeAnimator;     // Catch: Exception -> L108
        if (r08 == null) goto L196;
        r08.removeAllListeners();     // Catch: Exception -> L108
        this.audioVolumeAnimator.cancel();     // Catch: Exception -> L108
    L196:
        ValueAnimator r09 = ValueAnimator.ofFloat(new float[]{this.audioVolume, 1.0f});     // Catch: Exception -> L108
        this.audioVolumeAnimator = r09;     // Catch: Exception -> L108
        r09.addUpdateListener(this.audioVolumeUpdateListener);     // Catch: Exception -> L108
        this.audioVolumeAnimator.setDuration(300);     // Catch: Exception -> L108
        this.audioVolumeAnimator.start();     // Catch: Exception -> L108
    L272:
        checkAudioFocus(r41, true);
        setPlayerVolume();
        this.isPaused = false;
        this.lastProgress = 0;
        this.playingMessageObject = r41;
        if (SharedConfig.enabledRaiseTo(true) == true) goto L276;
        startRaiseToEarSensors(this.raiseChat);
    L276:
        if (ApplicationLoader.mainInterfacePaused == true) goto L286;
        PowerManager.WakeLock r010 = this.proximityWakeLock;
        if (r010 == null) goto L286;
        if (r010.isHeld() == true) goto L286;
        if (this.playingMessageObject.isVoice() == false) goto L284;
    L285:
        SharedConfig.enabledRaiseTo(false);
        goto L286
    L284:
        if (this.playingMessageObject.isRoundVideo() == true) goto L285;
    L286:
        startProgressTimer(this.playingMessageObject);
        NotificationCenter.getInstance(r41.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidStart, new Object[]{r41, r9});
        VideoPlayer r011 = this.videoPlayer;
        if (r011 != null) goto L346;
        VideoPlayer r012 = this.audioPlayer;
        if (r012 == null) goto L317;
    L309:
        e = move-exception;
        this.playingMessageObject.resetPlayingProgress();
        NotificationCenter.getInstance(r41.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), 0});
        FileLog.e(e);
        goto L317
    L305:
        if (this.playingMessageObject.audioProgress == 0.0f) goto L317;
        long r92 = r012.getDuration();     // Catch: Exception -> L309
        if (r92 != (-9223372036854775807L)) goto L311;
        r92 = ((long) this.playingMessageObject.getDuration()) * 1000;     // Catch: Exception -> L309
    L311:
        long r44 = (int) (r92 * this.playingMessageObject.audioProgress);     // Catch: Exception -> L309
        this.audioPlayer.seekTo(r44);     // Catch: Exception -> L309
        if (this.ignorePlayerUpdate == true) goto L317;
        CastSync.seekTo(r44);     // Catch: Exception -> L309
    L317:
        if (canStartMusicPlayerService() == true) goto L350;
        ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
    L352:
        CastSync.check(1);     // Catch: Exception -> L331
        if (this.ignorePlayerUpdate == false) goto L328;
        return true;
    L328:
        if (ChromecastController.getInstance().isCasting() == false) goto L333;
        ChromecastController.getInstance().setCurrentMediaAndCastIfNeeded(getCurrentChromecastMedia());     // Catch: Exception -> L331
    L333:
        CastSync.setPlaying(true);     // Catch: Exception -> L331
        return true;
    L331:
        e = move-exception;
        FileLog.e(e);
        return true;
    L350:
        ApplicationLoader.applicationContext.startService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));     // Catch: Throwable -> L321
    L321:
        th = move-exception;
        FileLog.e(th);
        goto L352
    L346:
    L293:
        e = move-exception;
        MessageObject r212 = this.playingMessageObject;
        r212.audioProgress = 0.0f;
        r212.audioProgressSec = 0;
        NotificationCenter.getInstance(r41.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), 0});
        FileLog.e(e);
    L301:
        this.videoPlayer.play();
        goto L317
    L289:
        if (this.playingMessageObject.audioProgress == 0.0f) goto L301;
        long r8 = r011.getDuration();     // Catch: Exception -> L293
        if (r8 != (-9223372036854775807L)) goto L295;
        r8 = ((long) this.playingMessageObject.getDuration()) * 1000;     // Catch: Exception -> L293
    L295:
        MessageObject r213 = this.playingMessageObject;     // Catch: Exception -> L293
        int r013 = (int) (r8 * r213.audioProgress);     // Catch: Exception -> L293
        int r45 = r213.audioProgressMs;     // Catch: Exception -> L293
        if (r45 == 0) goto L298;
        r213.audioProgressMs = 0;     // Catch: Exception -> L293
        r013 = r45;
    L298:
        this.videoPlayer.seekTo(r013);     // Catch: Exception -> L293
        goto L301
    L197:
        this.audioVolume = 1.0f;     // Catch: Exception -> L108
        setPlayerVolume();     // Catch: Exception -> L108
        goto L272
    L190:
        r211 = 3;
        goto L191
    L165:
        if (r41.getDocument() == null) goto L179;
        if (DialogObject.isEncryptedDialog(r41.getDialogId()) == true) goto L179;
        MusicListenReporter r014 = this.reporter;     // Catch: Exception -> L108
        if (r014 != null) goto L171;
    L172:
        if (r014 == null) goto L174;
        r014.destroy();     // Catch: Exception -> L108
    L174:
        this.reporter = new MusicListenReporter(r41.currentAccount);     // Catch: Exception -> L108
    L175:
        TLRPC.Document r015 = r41.getDocument();     // Catch: Exception -> L108
        TLRPC.TL_inputDocument r214 = new TLRPC.TL_inputDocument();     // Catch: Exception -> L108
        r214.id = r015.id;     // Catch: Exception -> L108
        r214.access_hash = r015.access_hash;     // Catch: Exception -> L108
        byte[] r016 = r015.file_reference;     // Catch: Exception -> L108
        r214.file_reference = r016;     // Catch: Exception -> L108
        if (r016 != null) goto L178;
        r214.file_reference = new byte[0];     // Catch: Exception -> L108
    L178:
        this.reporter.setup(r214);     // Catch: Exception -> L108
        goto L182
    L171:
        if (r014.currentAccount == r41.currentAccount) goto L175;
    L340:
        this.audioInfo = AudioInfo.getAudioInfo(r34);     // Catch: Exception -> L145
    L147:
        String r017 = r41.getFileName();     // Catch: Exception -> L108
        if (TextUtils.isEmpty(r017) == true) goto L159;
        if (r41.getDuration() < 600.0d) goto L159;
        float r215 = ApplicationLoader.applicationContext.getSharedPreferences("media_saved_pos", 0).getFloat(r017, -1.0f);     // Catch: Exception -> L108
        if (r215 > 0.0f) goto L154;
    L156:
        this.shouldSavePositionForCurrentAudio = r017;     // Catch: Exception -> L108
        if (Math.abs(this.currentMusicPlaybackSpeed - 1.0f) <= 0.001f) goto L159;
        this.audioPlayer.setPlaybackSpeed(Math.round(this.currentMusicPlaybackSpeed * 10.0f) / 10.0f);     // Catch: Exception -> L108
        goto L159
    L154:
        if (r215 >= 0.999f) goto L156;
        this.seekToProgressPending = r215;     // Catch: Exception -> L108
        r41.audioProgress = r215;     // Catch: Exception -> L108
        goto L156
    L145:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L108
        goto L147
    L114:
        if (r41.mediaExists == true) goto L117;
        if (r15 == r14) goto L117;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda19(r41, r15));     // Catch: Exception -> L108
    L117:
        this.audioPlayer.preparePlayer(Uri.fromFile(r15), "other");     // Catch: Exception -> L108
        this.isStreamingCurrentAudio = false;     // Catch: Exception -> L108
        r34 = r15;
        goto L125
    L106:
        if (SharedConfig.pauseMusicOnMedia == true) goto L110;
        r11 = true;
    L108:
        e = move-exception;
        FileLog.e(e);
        NotificationCenter r018 = NotificationCenter.getInstance(r41.currentAccount);
        int r216 = NotificationCenter.messagePlayingPlayStateChanged;
        MessageObject r3 = this.playingMessageObject;
        if (r3 == null) goto L202;
        int r32 = r3.getId();
    L203:
        r018.postNotificationName(r216, new Object[]{Integer.valueOf(r32)});
        if (this.audioPlayer == null) goto L210;
        MusicListenReporter r019 = this.reporter;
        if (r019 == null) goto L208;
        r019.destroy();
        ?? r57 = 0;
        this.reporter = null;
    L209:
        this.audioPlayer.releasePlayer(true);
        this.audioPlayer = r57;
        Theme.unrefAudioVisualizeDrawable(this.playingMessageObject);
        this.isPaused = false;
        this.playingMessageObject = r57;
        this.downloadingCurrentMessage = false;
        return false;
    L208:
        r57 = 0;
        goto L209
    L210:
        return false;
    L202:
        r32 = 0;
    L99:
        File r342 = r15;
        FileLoader.getInstance(r41.currentAccount).setLoadingVideoForPlayer(r41.getDocument(), true);
        this.playerWasReady = false;
        if (r27 == false) goto L220;
        String r217 = "other";
        String r322 = "&reference=";
        if (r41.messageOwner.peer_id.channel_id == 0) goto L217;
    L219:
        boolean r58 = false;
    L222:
        if (r27 == true) goto L224;
    L226:
        int[] r46 = null;
    L227:
        if (r28 == true) goto L229;
        clearPlaylist();
    L229:
        VideoPlayer r020 = new VideoPlayer();
        this.videoPlayer = r020;
        r020.setLooping(r42);
        int r021 = this.playerNum + 1;
        this.playerNum = r021;
        String r262 = r217;
        this.videoPlayer.setDelegate(new AnonymousClass9(this, r021, r41, r46, r58));
        this.currentAspectRatioFrameLayoutReady = false;
        if (this.pipRoundVideoView != null) goto L238;
        if (MessagesController.getInstance(r41.currentAccount).isDialogVisible(r41.getDialogId(), r41.scheduled) == false) goto L238;
        TextureView r022 = this.currentTextureView;
        if (r022 == null) goto L245;
        this.videoPlayer.setTextureView(r022);
    L245:
        if (r18 == true) goto L247;
        int r023 = FileLoader.getInstance(r41.currentAccount).getFileReference(r41);     // Catch: Exception -> L257
        TLRPC.Document r218 = r41.getDocument();     // Catch: Exception -> L257
        StringBuilder r47 = new StringBuilder("?account=");     // Catch: Exception -> L257
        r47.append(r41.currentAccount);     // Catch: Exception -> L257
        r47.append("&id=");     // Catch: Exception -> L257
        r47.append(r218.id);     // Catch: Exception -> L257
        r47.append("&hash=");     // Catch: Exception -> L257
        r47.append(r218.access_hash);     // Catch: Exception -> L257
        r47.append("&dc=");     // Catch: Exception -> L257
        r47.append(r218.dc_id);     // Catch: Exception -> L257
        r47.append("&size=");     // Catch: Exception -> L257
        r47.append(r218.size);     // Catch: Exception -> L257
        r47.append("&mime=");     // Catch: Exception -> L257
        r47.append(URLEncoder.encode(r218.mime_type, "UTF-8"));     // Catch: Exception -> L257
        r47.append("&rid=");     // Catch: Exception -> L257
        r47.append(r023);     // Catch: Exception -> L257
        r47.append("&name=");     // Catch: Exception -> L257
        r47.append(URLEncoder.encode(FileLoader.getDocumentFileName(r218), "UTF-8"));     // Catch: Exception -> L257
        r47.append(r322);     // Catch: Exception -> L257
        byte[] r024 = r218.file_reference;     // Catch: Exception -> L257
        if (r024 != null) goto L255;
        r024 = new byte[0];     // Catch: Exception -> L257
    L255:
        r47.append(Utilities.bytesToHex(r024));     // Catch: Exception -> L257
        this.videoPlayer.preparePlayer(Uri.parse("tg://" + r41.getFileName() + r47.toString()), r262);     // Catch: Exception -> L257
    L259:
        boolean r025 = r41.isRoundVideo();
        VideoPlayer r219 = this.videoPlayer;
        if (r025 == true) goto L262;
        r219.setStreamType(3);
        goto L272
    L262:
        if (this.useFrontSpeaker == false) goto L264;
        int r026 = 0;
    L265:
        r219.setStreamType(r026);
        if (Math.abs(this.currentPlaybackSpeed - 1.0f) <= 0.001f) goto L268;
        this.videoPlayer.setPlaybackSpeed(Math.round(this.currentPlaybackSpeed * 10.0f) / 10.0f);
    L268:
        float r027 = r41.forceSeekTo;
        if (r027 < 0.0f) goto L272;
        this.seekToProgressPending = r027;
        r41.audioProgress = r027;
        r41.forceSeekTo = -1.0f;
        goto L272
    L264:
        r026 = 3;
    L257:
        e = move-exception;
        FileLog.e(e);
        goto L259
    L247:
        if (r41.mediaExists == true) goto L250;
        if (r342 == r14) goto L250;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda18(r41, r342));
    L250:
        this.videoPlayer.preparePlayer(Uri.fromFile(r342), r262);
    L238:
        if (this.pipRoundVideoView == null) goto L342;
    L242:
        PipRoundVideoView r028 = this.pipRoundVideoView;
        if (r028 == null) goto L245;
        this.videoPlayer.setTextureView(r028.getTextureView());
        goto L245
    L342:
        PipRoundVideoView r029 = new PipRoundVideoView();     // Catch: Exception -> L241
        this.pipRoundVideoView = r029;     // Catch: Exception -> L241
        r029.show(this.baseActivity, new MediaController$$ExternalSyntheticLambda17(this));     // Catch: Exception -> L241
    L241:
        this.pipRoundVideoView = null;
        goto L242
    L224:
        if (r41.getDuration() > 30.0d) goto L226;
        r46 = new int[]{1};
        goto L227
    L217:
        if (r41.audioProgress > 0.1f) goto L219;
    L221:
        r58 = true;
        goto L222
    L220:
        r217 = "other";
        r322 = "&reference=";
        goto L221
    L92:
        checkIsNextVoiceFileDownloaded(r52);
        goto L93
    L56:
        if (r41.isMusic() == true) goto L64;
        if (r41.isRoundVideo() == true) goto L64;
        if (r41.isVideo() == false) goto L69;
        if (r41.canStreamVideo() == false) goto L69;
    L64:
        if (r41.shouldEncryptPhotoOrVideo() == true) goto L69;
        if (DialogObject.isEncryptedDialog(r41.getDialogId()) == true) goto L69;
        r23 = true;
        goto L71
    L52:
        r15 = FileLoader.getInstance(r41.currentAccount).getPathToMessage(r41.messageOwner);
        goto L54
    L44:
        if (r22.length() <= 0) goto L49;
        File r220 = new File(r41.messageOwner.attachPath);
        r5 = r220.exists();
        if (r5 == false) goto L47;
        r14 = r220;
        goto L50
    L26:
        if (r9.isMusic() == false) goto L30;
        if (r41.isVoice() == false) goto L30;
    L35:
        boolean r221 = saveMusicPlaylistStateIfNeeded();
    L37:
        if (this.playMusicAgain == true) goto L39;
        this.playingMessageObject.resetPlayingProgress();
        NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), 0});
    L39:
        r4 = r221;
        r2 = false;
    L30:
        if (r41.isRoundVideo() == true) goto L35;
        if (r41.isVideo() == true) goto L35;
        r221 = false;
        goto L37
    L21:
        if (r41.isContentUnread() == false) goto L23;
        MessagesController.getInstance(r41.currentAccount).markMessageContentAsRead(r41);
    L10:
        if (isSamePlayingMessage(r41) == false) goto L19;
        if (this.isPaused == false) goto L15;
        resumeAudio(r41);
    L15:
        if (SharedConfig.enabledRaiseTo(true) == true) goto L17;
        startRaiseToEarSensors(this.raiseChat);
    L17:
        return true;
    }

    public void playMessageAtIndex(int r3) {
        int r0 = this.currentPlaylistNum;
        if (r0 >= 0) goto L5;
        return;
    L5:
        if (r0 >= this.playlist.size()) goto L15;
        this.currentPlaylistNum = r3;
        this.playMusicAgain = true;
        MessageObject r32 = this.playlist.get(r3);
        if (this.playingMessageObject != null) goto L10;
    L12:
        playMessage(r32);
        return;
    L10:
        if (isSamePlayingMessage(r32) == true) goto L12;
        this.playingMessageObject.resetPlayingProgress();
        goto L12
    }

    public void playNextMessage() {
        playNextMessageWithoutOrder(false);
    }

    public void playPreviousMessage() {
        if (SharedConfig.shuffleMusic == false) goto L5;
        ArrayList<MessageObject> r0 = this.shuffledPlaylist;
    L7:
        if (r0.isEmpty() == true) goto L26;
        int r1 = this.currentPlaylistNum;
        if (r1 >= 0) goto L11;
        return;
    L11:
        if (r1 >= r0.size()) goto L28;
        MessageObject r12 = r0.get(this.currentPlaylistNum);
        if (r12.audioProgressSec <= 10) goto L18;
        seekToProgress(r12, 0.0f);
        return;
    L18:
        if (SharedConfig.playOrderReversed == false) goto L20;
        int r13 = -1;
    L21:
        traversePlaylist(r0, r13);
        if (this.currentPlaylistNum >= r0.size()) goto L29;
        this.playMusicAgain = true;
        playMessage(r0.get(this.currentPlaylistNum));
        return;
    L29:
        return;
    L20:
        r13 = 1;
        goto L21
    L28:
        return;
    L26:
        return;
    L5:
        r0 = this.playlist;
        goto L7
    }

    public void prepareResumedRecording(int r15, MediaDataController.DraftVoice r16, long r17, MessageObject r19, MessageObject r20, TL_stories.StoryItem r21, int r22, SendMessageChatArguments r23, long r24, MessageSuggestionParams r26) {
        this.manualRecording = false;
        requestRecordAudioFocus(true);
        this.recordQueue.cancelRunnable(this.recordStartRunnable);
        this.recordQueue.postRunnable(new MediaController$$ExternalSyntheticLambda48(this, r22, r16, r15, r17, r24, r26, r20, r19, r21));
    }

    public void requestRecordAudioFocus(boolean r4) {
        boolean r0 = this.hasRecordAudioFocus;
        if (r4 == false) goto L11;
        if (r0 == false) goto L6;
        return;
    L6:
        if (SharedConfig.pauseMusicOnRecord == true) goto L8;
        return;
    L8:
        if (NotificationsController.audioManager.requestAudioFocus(this.audioRecordFocusChangedListener, 3, 2) != 1) goto L16;
        this.hasRecordAudioFocus = true;
        return;
    L16:
        return;
    L11:
        if (r0 == false) goto L17;
        NotificationsController.audioManager.abandonAudioFocus(this.audioRecordFocusChangedListener);
        this.hasRecordAudioFocus = false;
        return;
    }

    public void resetGoingToShowMessageObject() {
        this.goingToShowMessageObject = null;
    }

    public void resumeByRewind() {
        VideoPlayer r0 = this.audioPlayer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.playingMessageObject != null) goto L7;
        return;
    L7:
        if (this.isPaused == false) goto L9;
        return;
    L9:
        if (r0.isBuffering() == false) goto L12;
        MessageObject r02 = this.playingMessageObject;
        cleanupPlayer(false, false);
        playMessage(r02);
        return;
    L12:
        this.audioPlayer.play();
    }

    public void scheduleVideoConvert(MessageObject r3) {
        scheduleVideoConvert(r3, false, true, false);
    }

    public void scheduleVideoConvert(MessageObject r7, VideoEditedInfo r8) {
        scheduleVideoConvert(r7, r8, false, true, false);
    }

    public boolean scheduleVideoConvert(MessageObject r3, VideoEditedInfo r4, boolean r5, boolean r6, boolean r7) {
        if (r3 == null) goto L19;
        if (r4 == null) goto L19;
        if (r5 == true) goto L8;
    L10:
        if (r5 == false) goto L12;
        new File(r3.messageOwner.attachPath).delete();
    L12:
        VideoConvertMessage r52 = new VideoConvertMessage(r3, r4, r6, r7);
        this.videoConvertQueue.add(r52);
        if (r52.foreground == false) goto L16;
        this.foregroundConvertingMessages.add(r52);
        checkForegroundConvertMessage(false);
    L16:
        if (this.videoConvertQueue.size() != 1) goto L18;
        startVideoConvertFromQueue();
    L18:
        return true;
    L8:
        if (this.videoConvertQueue.isEmpty() == true) goto L10;
        return false;
    L19:
        return false;
    }

    public boolean scheduleVideoConvert(MessageObject r8, boolean r9, boolean r10, boolean r11) {
        if (r8 == null) goto L5;
        VideoEditedInfo r0 = r8.videoEditedInfo;
    L7:
        return scheduleVideoConvert(r8, r0, r9, r10, r11);
    L5:
        r0 = null;
        goto L7
    }

    public boolean seekToProgress(MessageObject r8, float r9) {
        MessageObject r0 = this.playingMessageObject;
        if (this.audioPlayer == null) goto L5;
    L6:
        if (r8 == null) goto L29;
        if (r0 == null) goto L29;
        if (isSamePlayingMessage(r8) == false) goto L29;
        VideoPlayer r1 = this.audioPlayer;     // Catch: Exception -> L16
        if (r1 == null) goto L21;
        long r3 = r1.getDuration();     // Catch: Exception -> L16
        if (r3 != (-9223372036854775807L)) goto L18;
        this.seekToProgressPending = r9;     // Catch: Exception -> L16
    L26:
        NotificationCenter.getInstance(r8.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidSeek, new Object[]{Integer.valueOf(r0.getId()), Float.valueOf(r9)});
        return true;
    L18:
        r0.audioProgress = r9;     // Catch: Exception -> L16
        long r4 = (int) (r3 * r9);     // Catch: Exception -> L16
        this.audioPlayer.seekTo(r4);     // Catch: Exception -> L16
        this.lastProgress = r4;     // Catch: Exception -> L16
        if (this.ignorePlayerUpdate == true) goto L26;
        CastSync.seekTo(r4);     // Catch: Exception -> L16
        goto L26
    L21:
        VideoPlayer r12 = this.videoPlayer;     // Catch: Exception -> L16
        if (r12 == null) goto L26;
        r12.seekTo((long) (r12.getDuration() * r9));     // Catch: Exception -> L16
        if (this.ignorePlayerUpdate == true) goto L26;
        CastSync.seekTo((long) (this.videoPlayer.getDuration() * r9));     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
    L29:
        return false;
    L5:
        if (this.videoPlayer == null) goto L29;
        goto L6
    }

    public boolean seekToProgressMs(MessageObject r8, long r9) {
        MessageObject r0 = this.playingMessageObject;
        if (this.audioPlayer == null) goto L5;
    L6:
        if (r8 == null) goto L34;
        if (r0 == null) goto L34;
        if (isSamePlayingMessage(r8) == false) goto L34;
        VideoPlayer r1 = this.audioPlayer;     // Catch: Exception -> L16
        if (r1 == null) goto L21;
        long r3 = r1.getDuration();     // Catch: Exception -> L16
        if (r3 == (-9223372036854775807L)) goto L18;
        r0.audioProgress = Utilities.clamp01(r9 / r3);     // Catch: Exception -> L16
    L18:
        this.audioPlayer.seekTo(r9);     // Catch: Exception -> L16
        this.lastProgress = r9;     // Catch: Exception -> L16
        if (this.ignorePlayerUpdate == true) goto L29;
        CastSync.seekTo(r9);     // Catch: Exception -> L16
    L29:
        if (r3 == 0) goto L36;
        NotificationCenter.getInstance(r8.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidSeek, new Object[]{Integer.valueOf(r0.getId()), Float.valueOf(Utilities.clamp01(r9 / r3))});
        return true;
    L36:
        return true;
    L21:
        VideoPlayer r12 = this.videoPlayer;     // Catch: Exception -> L16
        if (r12 == null) goto L27;
        r3 = r12.getDuration();     // Catch: Exception -> L16
        this.videoPlayer.seekTo(r9);     // Catch: Exception -> L16
        if (this.ignorePlayerUpdate == true) goto L29;
        CastSync.seekTo(r9);     // Catch: Exception -> L16
        goto L29
    L27:
        r3 = 1;
    L16:
        e = move-exception;
        FileLog.e(e);
    L34:
        return false;
    L5:
        if (this.videoPlayer == null) goto L34;
        goto L6
    }

    public void setAllowStartRecord(boolean r1) {
        this.allowStartRecord = r1;
    }

    public void setBaseActivity(Activity r1, boolean r2) {
        if (r2 == false) goto L6;
        this.baseActivity = r1;
        return;
    L6:
        if (this.baseActivity != r1) goto L9;
        this.baseActivity = null;
        return;
    }

    public void setCurrentVideoVisible(boolean r4) {
        AspectRatioFrameLayout r0 = this.currentAspectRatioFrameLayout;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r4 == false) goto L17;
        PipRoundVideoView r42 = this.pipRoundVideoView;
        if (r42 == null) goto L12;
        this.pipSwitchingState = 2;
        r42.close(true);
        this.pipRoundVideoView = null;
        return;
    L12:
        if (r0.getParent() != null) goto L14;
        this.currentTextureViewContainer.addView(this.currentAspectRatioFrameLayout);
    L14:
        this.videoPlayer.setTextureView(this.currentTextureView);
        return;
    L17:
        if (r0.getParent() == null) goto L21;
        this.pipSwitchingState = 1;
        this.currentTextureViewContainer.removeView(this.currentAspectRatioFrameLayout);
        return;
    L21:
        if (this.pipRoundVideoView == null) goto L29;
    L25:
        PipRoundVideoView r43 = this.pipRoundVideoView;
        if (r43 == null) goto L31;
        this.videoPlayer.setTextureView(r43.getTextureView());
        return;
    L31:
        return;
    L29:
        PipRoundVideoView r44 = new PipRoundVideoView();     // Catch: Exception -> L24
        this.pipRoundVideoView = r44;     // Catch: Exception -> L24
        r44.show(this.baseActivity, new MediaController$$ExternalSyntheticLambda4(this));     // Catch: Exception -> L24
    L24:
        this.pipRoundVideoView = null;
        goto L25
    }

    public void setFeedbackView(View r1, boolean r2) {
        if (r2 == false) goto L6;
        this.feedbackView = r1;
        return;
    L6:
        if (this.feedbackView != r1) goto L9;
        this.feedbackView = null;
        return;
    }

    public void setInputFieldHasText(boolean r1) {
        this.inputFieldHasText = r1;
    }

    public void setLastVisibleMessageIds(int r1, long r2, long r4, TLRPC.User r6, TLRPC.EncryptedChat r7, ArrayList<Long> r8, int r9) {
        this.lastChatEnterTime = r2;
        this.lastChatLeaveTime = r4;
        this.lastChatAccount = r1;
        this.lastSecretChat = r7;
        this.lastUser = r6;
        this.lastMessageId = r9;
        this.lastChatVisibleMessages = r8;
    }

    public void setPlaybackOrderType(int r2) {
        boolean r0 = SharedConfig.shuffleMusic;
        SharedConfig.setPlaybackOrderType(r2);
        boolean r22 = SharedConfig.shuffleMusic;
        if (r0 == r22) goto L13;
        if (r22 == false) goto L7;
        buildShuffledPlayList();
        return;
    L7:
        MessageObject r23 = this.playingMessageObject;
        if (r23 == null) goto L14;
        int r24 = this.playlist.indexOf(r23);
        this.currentPlaylistNum = r24;
        if (r24 != (-1)) goto L15;
        clearPlaylist();
        cleanupPlayer(true, true);
        return;
    L15:
        return;
    L14:
        return;
    }

    public void setPlaybackSpeed(boolean r6, float r7) {
        if (r6 == true) goto L5;
        this.currentPlaybackSpeed = r7;
        if (Math.abs(r7 - 1.0f) <= 0.001f) goto L17;
        this.fastPlaybackSpeed = r7;
    L17:
        VideoPlayer r0 = this.audioPlayer;
        if (r0 == null) goto L20;
        r0.setPlaybackSpeed(Math.round(r7 * 10.0f) / 10.0f);
    L23:
        SharedPreferences.Editor r02 = MessagesController.getGlobalMainSettings().edit();
        if (r6 == false) goto L26;
        String r1 = "musicPlaybackSpeed";
    L27:
        SharedPreferences.Editor r03 = r02.putFloat(r1, r7);
        if (r6 == false) goto L30;
        String r12 = "fastMusicPlaybackSpeed";
    L31:
        if (r6 == false) goto L33;
        float r62 = this.fastMusicPlaybackSpeed;
    L34:
        r03.putFloat(r12, r62).apply();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.messagePlayingSpeedChanged, new Object[0]);
        if (this.ignorePlayerUpdate == true) goto L38;
        CastSync.setSpeed(r7);
        return;
    L38:
        return;
    L33:
        r62 = this.fastPlaybackSpeed;
        goto L34
    L30:
        r12 = "fastPlaybackSpeed";
        goto L31
    L26:
        r1 = "playbackSpeed";
        goto L27
    L20:
        VideoPlayer r04 = this.videoPlayer;
        if (r04 == null) goto L23;
        r04.setPlaybackSpeed(Math.round(r7 * 10.0f) / 10.0f);
        goto L23
    L5:
        if (this.currentMusicPlaybackSpeed >= 6.0f) goto L7;
    L11:
        this.currentMusicPlaybackSpeed = r7;
        if (Math.abs(r7 - 1.0f) <= 0.001f) goto L17;
        this.fastMusicPlaybackSpeed = r7;
        goto L17
    L7:
        if (r7 != 1.0f) goto L11;
        if (this.playingMessageObject == null) goto L11;
        this.audioPlayer.pause();
        MessageObject r2 = this.playingMessageObject;
        AndroidUtilities.runOnUIThread(new MediaController$$ExternalSyntheticLambda16(this, r2, r2.audioProgress), 50);
        goto L11
    }

    public boolean setPlaylist(ArrayList<MessageObject> r8, MessageObject r9, long r10) {
        return setPlaylist(r8, r9, r10, true, null);
    }

    public boolean setPlaylist(ArrayList<MessageObject> r8, MessageObject r9, long r10, PlaylistGlobalSearchParams r12) {
        return setPlaylist(r8, r9, r10, true, r12);
    }

    public boolean setPlaylist(ArrayList<MessageObject> r9, MessageObject r10, long r11, boolean r13, PlaylistGlobalSearchParams r14) {
        if (this.playingMessageObject != r10) goto L9;
        int r92 = this.playlist.indexOf(r10);
        if (r92 < 0) goto L8;
        this.currentPlaylistNum = r92;
    L8:
        return playMessage(r10);
    L9:
        this.forceLoopCurrentPlaylist = !r13;
        this.playlistMergeDialogId = r11;
        this.playMusicAgain = !this.playlist.isEmpty();
        clearPlaylist();
        this.playlistGlobalSearchParams = r14;
        boolean r142 = false;
        if (r9.isEmpty() == false) goto L12;
    L14:
        int r112 = r9.size() - 1;
        int r12 = Integer.MAX_VALUE;
        int r0 = Integer.MIN_VALUE;
    L15:
        if (r112 < 0) goto L24;
        MessageObject r1 = r9.get(r112);
        if (r1.isMusic() == false) goto L23;
        int r2 = r1.getId();
        if (r2 > 0) goto L21;
        if (r142 == true) goto L21;
    L22:
        this.playlist.add(r1);
        this.playlistMap.put(Integer.valueOf(r2), r1);
    L21:
        r12 = Math.min(r12, r2);
        r0 = Math.max(r0, r2);
    L23:
        r112 = r112 - 1;
        goto L15
    L24:
        sortPlaylist();
        int r93 = this.playlist.indexOf(r10);
        this.currentPlaylistNum = r93;
        if (r93 != (-1)) goto L28;
        clearPlaylist();
        this.currentPlaylistNum = this.playlist.size();
        this.playlist.add(r10);
        this.playlistMap.put(Integer.valueOf(r10.getId()), r10);
    L28:
        if (r10.isMusic() == false) goto L40;
        if (r10.scheduled == true) goto L40;
        if (SharedConfig.shuffleMusic == false) goto L34;
        buildShuffledPlayList();
    L34:
        if (r13 == false) goto L40;
        if (this.playlistGlobalSearchParams != null) goto L38;
        MediaDataController.getInstance(r10.currentAccount).loadMusic(r10.getDialogId(), r12, r0);
        goto L40
    L38:
        this.playlistClassGuid = ConnectionsManager.generateClassGuid();
    L40:
        return playMessage(r10);
    L12:
        if (DialogObject.isEncryptedDialog(r9.get(0).getDialogId()) == false) goto L14;
        r142 = true;
        goto L14
    }

    public void setReplyingMessage(MessageObject r1, MessageObject r2, TL_stories.StoryItem r3) {
        this.recordReplyingMsg = r1;
        this.recordReplyingTopMsg = r2;
        this.recordReplyingStory = r3;
    }

    public void setTextureView(TextureView r7, AspectRatioFrameLayout r8, FrameLayout r9, boolean r10) {
        setTextureView(r7, r8, r9, r10, null);
    }

    public void setTextureView(TextureView r3, AspectRatioFrameLayout r4, FrameLayout r5, boolean r6, Runnable r7) {
        if (r3 == null) goto L41;
        boolean r0 = true;
        if (r6 == true) goto L11;
        if (this.currentTextureView != r3) goto L11;
        this.pipSwitchingState = 1;
        this.currentTextureView = null;
        this.currentAspectRatioFrameLayout = null;
        this.currentTextureViewContainer = null;
        return;
    L11:
        if (this.videoPlayer != null) goto L13;
        return;
    L13:
        if (r3 == this.currentTextureView) goto L42;
        if (r4 != null) goto L17;
    L19:
        r0 = false;
    L20:
        this.isDrawingWasReady = r0;
        this.currentTextureView = r3;
        if (r7 != null) goto L23;
    L27:
        PipRoundVideoView r32 = this.pipRoundVideoView;
        VideoPlayer r62 = this.videoPlayer;
        if (r32 == null) goto L30;
        r62.setTextureView(r32.getTextureView());
    L31:
        this.currentAspectRatioFrameLayout = r4;
        this.currentTextureViewContainer = r5;
        if (this.currentAspectRatioFrameLayoutReady == false) goto L39;
        if (r4 == null) goto L40;
        r4.setAspectRatio(this.currentAspectRatioFrameLayoutRatio, this.currentAspectRatioFrameLayoutRotation);
        return;
    L40:
        return;
    L39:
        return;
    L30:
        r62.setTextureView(this.currentTextureView);
        goto L31
    L23:
        if (this.pipRoundVideoView != null) goto L27;
        PipRoundVideoView r33 = new PipRoundVideoView();     // Catch: Exception -> L26
        this.pipRoundVideoView = r33;     // Catch: Exception -> L26
        r33.show(this.baseActivity, new MediaController$$ExternalSyntheticLambda22(this));     // Catch: Exception -> L26
    L26:
        this.pipRoundVideoView = null;
        goto L27
    L17:
        if (r4.isDrawingReady() == false) goto L19;
    L42:
        return;
    }

    public void setVoiceMessagesPlaylist(ArrayList<MessageObject> r3, boolean r4) {
        if (r3 == null) goto L4;
        ArrayList<MessageObject> r0 = new ArrayList(r3);
    L5:
        this.voiceMessagesPlaylist = r0;
        if (r0 == null) goto L11;
        this.voiceMessagesPlaylistUnread = r4;
        this.voiceMessagesPlaylistMap = new SparseArray();
        int r32 = 0;
    L9:
        if (r32 >= this.voiceMessagesPlaylist.size()) goto L13;
        MessageObject r42 = this.voiceMessagesPlaylist.get(r32);
        this.voiceMessagesPlaylistMap.put(r42.getId(), r42);
        r32 = r32 + 1;
        goto L9
    L13:
        return;
    L11:
        return;
    L4:
        r0 = null;
        goto L5
    }

    public void startMediaObserver() {
        ApplicationLoader.applicationHandler.removeCallbacks(this.stopMediaObserverRunnable);
        this.startObserverToken++;
    L7:
        e = move-exception;
        FileLog.e(e);
    L16:
    L13:
        e = move-exception;
        FileLog.e(e);
        return;
    L10:
        if (this.externalObserver != null) goto L20;
        ContentResolver r1 = ApplicationLoader.applicationContext.getContentResolver();     // Catch: Exception -> L13
        Uri r2 = MediaStore.Images.Media.INTERNAL_CONTENT_URI;     // Catch: Exception -> L13
        InternalObserver r3 = new InternalObserver(this);     // Catch: Exception -> L13
        this.internalObserver = r3;     // Catch: Exception -> L13
        r1.registerContentObserver(r2, false, r3);     // Catch: Exception -> L13
        return;
    L20:
        return;
    L4:
        if (this.internalObserver != null) goto L16;
        ContentResolver r12 = ApplicationLoader.applicationContext.getContentResolver();     // Catch: Exception -> L7
        Uri r22 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;     // Catch: Exception -> L7
        ExternalObserver r32 = new ExternalObserver(this);     // Catch: Exception -> L7
        this.externalObserver = r32;     // Catch: Exception -> L7
        r12.registerContentObserver(r22, false, r32);     // Catch: Exception -> L7
        goto L16
    }

    public void startRaiseToEarSensors(ChatActivity r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (this.accelerometerSensor != null) goto L10;
        if (this.gravitySensor != null) goto L8;
        return;
    L8:
        if (this.linearAcceleration != null) goto L10;
        return;
    L10:
        if (this.proximitySensor != null) goto L13;
        return;
    L13:
        if (SharedConfig.enabledRaiseTo(false) == true) goto L21;
        MessageObject r1 = this.playingMessageObject;
        if (r1 != null) goto L17;
        return;
    L17:
        if (r1.isVoice() == true) goto L21;
        if (this.playingMessageObject.isRoundVideo() == true) goto L21;
        return;
    L21:
        this.raiseChat = r7;
        if (this.sensorsStarted == true) goto L29;
        float[] r72 = this.gravity;
        r72[2] = 0.0f;
        r72[1] = 0.0f;
        r72[0] = 0.0f;
        float[] r73 = this.linearAcceleration;
        r73[2] = 0.0f;
        r73[1] = 0.0f;
        r73[0] = 0.0f;
        float[] r74 = this.gravityFast;
        r74[2] = 0.0f;
        r74[1] = 0.0f;
        r74[0] = 0.0f;
        this.lastTimestamp = 0;
        this.previousAccValue = 0.0f;
        this.raisedToTop = 0;
        this.raisedToTopSign = 0;
        this.countLess = 0;
        this.raisedToBack = 0;
        Utilities.globalQueue.postRunnable(new MediaController$$ExternalSyntheticLambda9(this));
        this.sensorsStarted = true;
        return;
    }

    public void startRecording(int r16, long r17, MessageObject r19, MessageObject r20, TL_stories.StoryItem r21, int r22, boolean r23, SendMessageChatArguments r24, long r25, MessageSuggestionParams r27) {
        MessageObject r0 = this.playingMessageObject;
        if (r0 != null) goto L5;
    L10:
        boolean r02 = false;
    L11:
        this.manualRecording = r23;
        requestRecordAudioFocus(true);
        this.feedbackView.performHapticFeedback(3, 2);     // Catch: Exception -> L19
    L13:
        DispatchQueue r1 = this.recordQueue;
        MediaController$$ExternalSyntheticLambda58 r2 = new MediaController$$ExternalSyntheticLambda58(this, r16, r22, r17, r25, r27, r20, r19, r21, r24);
        this.recordStartRunnable = r2;
        if (r02 == false) goto L16;
        long r3 = 500;
    L17:
        r1.postRunnable(r2, r3);
        return;
    L16:
        r3 = 50;
        goto L17
    L5:
        if (isPlayingMessage(r0) == false) goto L10;
        if (isMessagePaused() == true) goto L10;
        r02 = true;
        goto L11
    }

    public void startRecordingIfFromSpeaker() {
        if (this.useFrontSpeaker == true) goto L5;
        return;
    L5:
        if (this.raiseChat != null) goto L7;
        return;
    L7:
        if (this.allowStartRecord == true) goto L9;
        return;
    L9:
        if (SharedConfig.enabledRaiseTo(true) == false) goto L27;
        this.raiseToEarRecord = true;
        int r3 = this.raiseChat.getCurrentAccount();
        long r4 = this.raiseChat.getDialogId();
        MessageObject r7 = this.raiseChat.getThreadMessage();
        int r9 = this.raiseChat.getClassGuid();
        ChatActivity r1 = this.raiseChat;
        MessageSuggestionParams r2 = null;
        if (r1 == null) goto L14;
        SendMessageChatArguments r11 = r1.getMessageChatSendParams();
    L15:
        ChatActivity r12 = this.raiseChat;
        if (r12 == null) goto L18;
        long r122 = r12.getSendMonoForumPeerId();
    L19:
        ChatActivity r13 = this.raiseChat;
        if (r13 == null) goto L22;
        r2 = r13.getSendMessageSuggestionParams();
    L22:
        startRecording(r3, r4, null, r7, null, r9, false, r11, r122, r2);
        this.ignoreOnPause = true;
        return;
    L18:
        r122 = 0;
        goto L19
    L14:
        r11 = null;
        goto L15
    }

    public void stopMediaObserver() {
        if (this.stopMediaObserverRunnable != null) goto L5;
        this.stopMediaObserverRunnable = new StopMediaObserverRunnable(this, null);
    L5:
        StopMediaObserverRunnable r0 = this.stopMediaObserverRunnable;
        r0.currentObserverToken = this.startObserverToken;
        ApplicationLoader.applicationHandler.postDelayed(this.stopMediaObserverRunnable, 5000);
    }

    public void stopRaiseToEarSensors(ChatActivity r10, boolean r11, boolean r12) {
        if (this.ignoreOnPause == false) goto L6;
        this.ignoreOnPause = false;
        return;
    L6:
        if (r12 == true) goto L8;
    L12:
        MediaController r2 = this;
    L18:
        if (r2.sensorsStarted == true) goto L20;
        return;
    L20:
        if (r2.ignoreOnPause == false) goto L22;
        return;
    L22:
        if (r2.accelerometerSensor != null) goto L28;
        if (r2.gravitySensor != null) goto L26;
        return;
    L26:
        if (r2.linearAcceleration != null) goto L28;
        return;
    L28:
        if (r2.proximitySensor != null) goto L30;
        return;
    L30:
        if (r2.raiseChat != r10) goto L45;
        r2.raiseChat = null;
        r2.sensorsStarted = false;
        r2.accelerometerVertical = false;
        r2.proximityTouched = false;
        r2.raiseToEarRecord = false;
        r2.useFrontSpeaker = false;
        Utilities.globalQueue.postRunnable(new MediaController$$ExternalSyntheticLambda40(r2));
        PowerManager.WakeLock r9 = r2.proximityWakeLock;
        if (r9 != null) goto L35;
        return;
    L35:
        if (r9.isHeld() == false) goto L44;
        r2.proximityWakeLock.release();
        return;
    L44:
        return;
    L45:
        return;
    L8:
        if (this.recordingAudio != null) goto L10;
    L13:
        if (r11 == false) goto L15;
        int r3 = 2;
    L16:
        r2 = this;
        r2.stopRecording(r3, false, 0, false, 0);
        goto L18
    L15:
        r3 = 0;
        goto L16
    L10:
        if (isRecordingPaused() == true) goto L13;
        toggleRecordingPause(false);
        goto L12
    }

    public void stopRecording(int r10, boolean r11, int r12, boolean r13, long r14) {
        Runnable r0 = this.recordStartRunnable;
        if (r0 == null) goto L5;
        this.recordQueue.cancelRunnable(r0);
        this.recordStartRunnable = null;
    L5:
        this.recordQueue.postRunnable(new MediaController$$ExternalSyntheticLambda28(this, r10, r11, r12, r13, r14));
    }

    public void syncCastedPlayer() {
        if (this.playingMessageObject != null) goto L5;
        return;
    L5:
        this.ignorePlayerUpdate = true;
        if (CastSync.isActive() == true) goto L8;
    L21:
        setPlayerVolume();
        this.ignorePlayerUpdate = false;
        return;
    L8:
        if (CastSync.isUpdatePending() == true) goto L21;
        long r1 = CastSync.getPosition();
        long r3 = getProgressMs(this.playingMessageObject);
        if (r3 >= 0) goto L12;
    L16:
        boolean r12 = CastSync.isPlaying();
        MessageObject r2 = this.playingMessageObject;
        if (r12 == false) goto L19;
        playMessage(r2);
    L20:
        setPlaybackSpeed(true, CastSync.getSpeed());
        goto L21
    L19:
        pauseMessage(r2);
        goto L20
    L12:
        if (r1 < 0) goto L16;
        if (Math.abs(r3 - r1) <= 1000) goto L16;
        seekToProgressMs(this.playingMessageObject, r1);
        goto L16
    }

    public void toggleRecordingPause(boolean r3) {
        this.recordQueue.postRunnable(new MediaController$$ExternalSyntheticLambda57(this, r3));
    }

    public void trimCurrentRecording(long r10, long r12, Runnable r14) {
        if (this.recordingAudioFile != null) goto L7;
        if (r14 == null) goto L9;
        AndroidUtilities.runOnUIThread(r14);
        return;
    L9:
        return;
    L7:
        this.recordQueue.postRunnable(new MediaController$$ExternalSyntheticLambda2(this, new AnonymousClass15(this, FileLoader.getDirectory(1), System.currentTimeMillis() + "_" + FileLoader.getAttachFileName(this.recordingAudio)), r10, r12, r14));
    }

    public void tryResumePausedAudio() {
        MessageObject r0 = getPlayingMessageObject();
        if (r0 != null) goto L5;
    L13:
        this.wasPlayingAudioBeforePause = false;
        return;
    L5:
        if (isMessagePaused() == false) goto L13;
        if (this.wasPlayingAudioBeforePause == false) goto L13;
        if (r0.isVoice() == false) goto L11;
    L12:
        playMessage(r0);
        goto L13
    L11:
        if (r0.isMusic() == false) goto L13;
        goto L12
    }

    public void updateSilent(boolean r2) {
        this.isSilent = r2;
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L5;
        r0.setLooping(r2);
    L5:
        setPlayerVolume();
        checkVolumeBarUI();
        MessageObject r22 = this.playingMessageObject;
        if (r22 == null) goto L13;
        NotificationCenter r23 = NotificationCenter.getInstance(r22.currentAccount);
        int r02 = NotificationCenter.messagePlayingPlayStateChanged;
        MessageObject r1 = this.playingMessageObject;
        if (r1 == null) goto L10;
        int r12 = r1.getId();
    L11:
        r23.postNotificationName(r02, new Object[]{Integer.valueOf(r12)});
        return;
    L10:
        r12 = 0;
        goto L11
    }
}
