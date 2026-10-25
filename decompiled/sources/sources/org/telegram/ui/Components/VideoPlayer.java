package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.SurfaceTexture;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.LongSparseArray;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.ViewGroup;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoListener;
import androidx.media3.common.VideoSize;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.text.CueGroup;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.audio.TeeAudioProcessor;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.source.LoopingMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MergingMediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.source.SingleSampleMediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.exoplayer.video.SurfaceNotValidException;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.utils.VideoSubtitlesHelper$SubtitleState$$ExternalSyntheticRecord0;
import com.google.android.gms.cast.CastStatusCodes;
import j$.net.URLEncoder;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatThemeController$$ExternalSyntheticLambda6;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FourierTransform;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.chromecast.ChromecastMedia;
import org.telegram.messenger.chromecast.ChromecastMediaVariations;
import org.telegram.messenger.secretmedia.ExtendedDefaultDataSourceFactory;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.StoryEntry;

@SuppressLint({"NewApi"})
/* loaded from: classes3.dex */
public class VideoPlayer implements Player.Listener, VideoListener, AnalyticsListener, NotificationCenter.NotificationCenterDelegate {
    public static final HashSet<Integer> activePlayers = null;
    private static HashMap<String, Boolean> cachedSupportedCodec;
    private static int lastPlayerId;
    static int playerCounter;
    public boolean allowMultipleInstances;
    boolean audioDisabled;
    private ExoPlayer audioPlayer;
    private boolean audioPlayerReady;
    private volatile int audioTrackState;
    private String audioType;
    Handler audioUpdateHandler;
    private Uri audioUri;
    private AudioVisualizerDelegate audioVisualizerDelegate;
    private boolean autoIsOriginal;
    private boolean autoplay;
    private ExternalSubtitle currentExternalSubtitle;
    private boolean currentStreamIsHls;
    private Uri currentUri;
    private long currentVideoByteOffset;
    MediaSource.Factory dashMediaSourceFactory;
    private VideoPlayerDelegate delegate;
    private long fallbackDuration;
    private long fallbackPosition;
    private boolean handleAudioFocus;
    HlsMediaSource.Factory hlsMediaSourceFactory;
    private boolean isStory;
    private boolean isStreaming;
    private boolean lastReportedPlayWhenReady;
    private int lastReportedPlaybackState;
    private Looper looper;
    private boolean looping;
    private boolean loopingMediaSource;
    private ArrayList<VideoUri> manifestUris;
    private ExtendedDefaultDataSourceFactory mediaDataSourceFactory;
    private boolean mixedAudio;
    private boolean mixedPlayWhenReady;
    private Runnable onQualityChangeListener;
    public ExoPlayer player;
    public final int playerId;
    ProgressiveMediaSource.Factory progressiveMediaSourceFactory;
    private int repeatCount;
    private final ArrayList<Runnable> seekFinishedListeners;
    private int selectedQualityIndex;
    private boolean shouldPauseOther;
    private Surface surface;
    private SurfaceView surfaceView;
    private TextureView textureView;
    private MappingTrackSelector trackSelector;
    private boolean triedReinit;
    private boolean videoPlayerReady;
    private ArrayList<Quality> videoQualities;
    private Quality videoQualityToSelect;
    private String videoType;
    private Uri videoUri;
    private DispatchQueue workerQueue;

    /* renamed from: org.telegram.ui.Components.VideoPlayer$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements Player.Listener {
        final /* synthetic */ VideoPlayer this$0;

        public AnonymousClass1(VideoPlayer r1) {
            this.this$0 = r1;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerStateChanged(boolean r1, int r2) {
            if (VideoPlayer.m13507$$Nest$fgetaudioPlayerReady(this.this$0) == false) goto L5;
            return;
        L5:
            if (r2 != 3) goto L9;
            VideoPlayer.m13510$$Nest$fputaudioPlayerReady(this.this$0, true);
            VideoPlayer.m13511$$Nest$mcheckPlayersReady(this.this$0);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public interface AudioVisualizerDelegate {
        boolean needUpdate();

        void onVisualizerUpdate(boolean r1, boolean r2, float[] r3);
    }

    /* loaded from: classes7.dex */
    public class AudioVisualizerRenderersFactory extends DefaultRenderersFactory {
        final /* synthetic */ VideoPlayer this$0;

        public AudioVisualizerRenderersFactory(VideoPlayer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.media3.exoplayer.DefaultRenderersFactory
        public AudioSink buildAudioSink(Context r2, boolean r3, boolean r4) {
            return new DefaultAudioSink.Builder(r2).setEnableFloatOutput(r3).setEnableAudioTrackPlaybackParams(r4).setAudioProcessors(new AudioProcessor[]{new TeeAudioProcessor(new VisualizerBufferSink(this.this$0))}).build();
        }
    }

    /* loaded from: classes7.dex */
    public static final class ExternalSubtitle extends RecordTag {
        private final String label;
        private final String mimeType;
        private final Uri uri;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof ExternalSubtitle) == false) goto L12;
            ExternalSubtitle r32 = (ExternalSubtitle) r3;
            if (Objects.equals(this.uri, r32.uri) == true) goto L7;
            return false;
        L7:
            if (Objects.equals(this.mimeType, r32.mimeType) == true) goto L9;
            return false;
        L9:
            if (Objects.equals(this.label, r32.label) == false) goto L16;
            return true;
        L16:
            return false;
        L12:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.uri, this.mimeType, this.label};
        }

        public ExternalSubtitle(Uri r1, String r2, String r3) {
            this.uri = r1;
            this.mimeType = r2;
            this.label = r3;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return VideoSubtitlesHelper$SubtitleState$$ExternalSyntheticRecord0.m(this.uri, this.mimeType, this.label);
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ExternalSubtitle.class, "uri;mimeType;label");
        }

        public MediaItem.SubtitleConfiguration toSubtitleConfiguration() {
            return new MediaItem.SubtitleConfiguration.Builder(this.uri).setMimeType(this.mimeType).setLabel(this.label).setSelectionFlags(1).build();
        }
    }

    /* loaded from: classes7.dex */
    public static class OffsetDataSource implements DataSource {
        private final long byteOffset;
        private final DataSource upstream;

        public OffsetDataSource(DataSource r1, long r2) {
            this.upstream = r1;
            this.byteOffset = r2;
        }

        @Override // androidx.media3.datasource.DataSource
        public void addTransferListener(TransferListener r1) {
            this.upstream.addTransferListener(r1);
        }

        @Override // androidx.media3.datasource.DataSource
        public void close() {
            this.upstream.close();
        }

        @Override // androidx.media3.datasource.DataSource
        public Map<String, List<String>> getResponseHeaders() {
            return this.upstream.getResponseHeaders();
        }

        @Override // androidx.media3.datasource.DataSource
        public Uri getUri() {
            return this.upstream.getUri();
        }

        @Override // androidx.media3.datasource.DataSource
        public long open(DataSpec r6) {
            DataSpec r62 = r6.buildUpon().setPosition(r6.position + this.byteOffset).build();
            return this.upstream.open(r62);
        }

        @Override // androidx.media3.common.DataReader
        public int read(byte[] r1, int r2, int r3) {
            return this.upstream.read(r1, r2, r3);
        }
    }

    public static class Quality {
        public int height;
        public boolean original;
        public final ArrayList<VideoUri> uris;
        public int width;

        public Quality(VideoUri r3) {
            ArrayList<VideoUri> r0 = new ArrayList();
            this.uris = r0;
            this.original = r3.original;
            this.width = r3.width;
            this.height = r3.height;
            r0.add(r3);
        }

        public static ArrayList<Quality> filterByCodec(ArrayList<Quality> r6) {
            if (r6 != null) goto L5;
            return null;
        L5:
            int r1 = 0;
        L7:
            if (r1 >= r6.size()) goto L21;
            Quality r2 = r6.get(r1);
            int r3 = 0;
        L9:
            int r4 = r2.uris.size();
            ArrayList<VideoUri> r5 = r2.uris;
            if (r3 >= r4) goto L18;
            VideoUri r42 = r5.get(r3);
            if (TextUtils.isEmpty(r42.codec) == true) goto L16;
            if (VideoPlayer.supportsHardwareDecoder(r42.codec) == true) goto L16;
            r2.uris.remove(r3);
            r3 = r3 - 1;
        L16:
            r3 = r3 + 1;
            goto L9
        L18:
            if (r5.isEmpty() == false) goto L20;
            r6.remove(r1);
            r1 = r1 - 1;
        L20:
            r1 = r1 + 1;
            goto L7
        L21:
            return r6;
        }

        public static ArrayList<Quality> group(ArrayList<VideoUri> r10) {
            ArrayList<Quality> r0 = new ArrayList();
            int r1 = r10.size();
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L22;
            VideoUri r4 = r10.get(r3);
            r3 = r3 + 1;
            VideoUri r42 = r4;
            if (r42.original == true) goto L6;
            int r5 = r0.size();
            int r6 = 0;
        L8:
            if (r6 >= r5) goto L16;
            Quality r7 = r0.get(r6);
            r6 = r6 + 1;
            Quality r72 = r7;
            if (r72.original == true) goto L8;
            if (r72.width != r42.width) goto L8;
            if (r72.height != r42.height) goto L8;
        L17:
            if (r72 == null) goto L21;
            if (SharedConfig.debugVideoQualities == true) goto L21;
            r72.uris.add(r42);
        L21:
            r0.add(new Quality(r42));
            goto L3
        L16:
            r72 = null;
            goto L17
        L6:
            r0.add(new Quality(r42));
            goto L3
        L22:
            return r0;
        }

        public TLRPC.Document getDownloadDocument() {
            VideoUri r1 = null;
            if (this.uris.isEmpty() == false) goto L5;
            return null;
        L5:
            ArrayList<VideoUri> r0 = this.uris;
            int r2 = r0.size();
            int r4 = 0;
        L6:
            if (r4 >= r2) goto L11;
            VideoUri r5 = r0.get(r4);
            r4 = r4 + 1;
            VideoUri r52 = r5;
            if (r52.isCached() == false) goto L6;
            return r52.document;
        L11:
            long r42 = LongCompanionObject.MAX_VALUE;
            int r02 = 0;
        L13:
            if (r02 >= this.uris.size()) goto L20;
            VideoUri r22 = this.uris.get(r02);
            if (r22.size >= r42) goto L19;
            if (VideoPlayer.supportsHardwareDecoder(r22.codec) == false) goto L19;
            r42 = r22.size;
            r1 = r22;
        L19:
            r02 = r02 + 1;
            goto L13
        L20:
            if (r1 == null) goto L24;
            return r1.document;
        L24:
            return this.uris.get(0).document;
        }

        public VideoUri getDownloadUri() {
            VideoUri r1 = null;
            if (this.uris.isEmpty() == false) goto L5;
            return null;
        L5:
            ArrayList<VideoUri> r0 = this.uris;
            int r2 = r0.size();
            int r4 = 0;
        L6:
            if (r4 >= r2) goto L10;
            VideoUri r5 = r0.get(r4);
            r4 = r4 + 1;
            VideoUri r52 = r5;
            if (r52.isCached() == false) goto L6;
            return r52;
        L10:
            long r42 = LongCompanionObject.MAX_VALUE;
            int r02 = 0;
        L12:
            if (r02 >= this.uris.size()) goto L19;
            VideoUri r22 = this.uris.get(r02);
            if (r22.size >= r42) goto L18;
            if (VideoPlayer.supportsHardwareDecoder(r22.codec) == false) goto L18;
            r42 = r22.size;
            r1 = r22;
        L18:
            r02 = r02 + 1;
            goto L12
        L19:
            if (r1 == null) goto L22;
            return r1;
        L22:
            return this.uris.get(0);
        }

        public int p() {
            int r2 = Math.min(this.width, this.height);
            if (Math.abs(r2 - 2160) >= 55) goto L7;
            return 2160;
        L7:
            if (Math.abs(r2 - 1440) >= 55) goto L11;
            return 1440;
        L11:
            if (Math.abs(r2 - 1080) >= 55) goto L15;
            return 1080;
        L15:
            if (Math.abs(r2 - 720) >= 55) goto L19;
            return 720;
        L19:
            if (Math.abs(r2 - 480) >= 55) goto L23;
            return 480;
        L23:
            if (Math.abs(r2 - 360) >= 55) goto L27;
            return 360;
        L27:
            if (Math.abs(r2 - 240) >= 55) goto L31;
            return 240;
        L31:
            if (Math.abs(r2 - 144) >= 55) goto L34;
            return 144;
        L34:
            return r2;
        }

        public String toString() {
            boolean r0 = SharedConfig.debugVideoQualities;
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r0 == false) goto L13;
            StringBuilder r02 = new StringBuilder();
            r02.append(this.width);
            r02.append("x");
            r02.append(this.height);
            if (this.original == false) goto L7;
            String r1 = " (" + LocaleController.getString(R.string.QualitySource) + ")";
        L8:
            r02.append(r1);
            r02.append("\n");
            r02.append(AndroidUtilities.formatFileSize((long) this.uris.get(0).bitrate).replace(" ", _UrlKt.FRAGMENT_ENCODE_SET));
            r02.append("/s");
            if (this.uris.get(0).codec == null) goto L11;
            r3 = ", " + this.uris.get(0).codec;
        L11:
            r02.append(r3);
            return r02.toString();
        L7:
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L8
        L13:
            StringBuilder r03 = new StringBuilder();
            r03.append(p());
            r03.append("p");
            if (this.original == false) goto L16;
            r3 = " (" + LocaleController.getString(R.string.QualitySource) + ")";
        L16:
            r03.append(r3);
            return r03.toString();
        }
    }

    /* loaded from: classes7.dex */
    public interface VideoPlayerDelegate {
        default void onAudioTrackChanged(int r1) {
        }

        default void onCues(CueGroup r1) {
        }

        void onError(VideoPlayer r1, Exception r2);

        void onRenderedFirstFrame();

        default void onRenderedFirstFrame(AnalyticsListener.EventTime r1) {
        }

        default void onSeekFinished(AnalyticsListener.EventTime r1) {
        }

        default void onSeekStarted(AnalyticsListener.EventTime r1) {
        }

        void onStateChanged(boolean r1, int r2);

        default boolean onSurfaceDestroyed(SurfaceTexture r1) {
            return false;
        }

        default void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }

        void onVideoSizeChanged(int r1, int r2, int r3, float r4);
    }

    public static class VideoUri {
        public double bitrate;
        public String codec;
        public int currentAccount;
        public long docId;
        public TLRPC.Document document;
        public double duration;
        public long fileVideoOffset;
        public int height;
        public Uri m3u8uri;
        public long manifestDocId;
        public TLRPC.Document manifestDocument;
        public boolean original;
        public long size;
        public Uri uri;
        public int width;

        public VideoUri() {
        }

        public static Uri getUri(int r3, TLRPC.Document r4, int r5) {
            StringBuilder r0 = new StringBuilder("?account=");
            r0.append(r3);
            r0.append("&id=");
            r0.append(r4.id);
            r0.append("&hash=");
            r0.append(r4.access_hash);
            r0.append("&dc=");
            r0.append(r4.dc_id);
            r0.append("&size=");
            r0.append(r4.size);
            r0.append("&mime=");
            r0.append(URLEncoder.encode(r4.mime_type, "UTF-8"));
            r0.append("&rid=");
            r0.append(r5);
            r0.append("&name=");
            r0.append(URLEncoder.encode(FileLoader.getDocumentFileName(r4), "UTF-8"));
            r0.append("&reference=");
            byte[] r32 = r4.file_reference;
            if (r32 != null) goto L6;
            r32 = new byte[0];
        L6:
            r0.append(Utilities.bytesToHex(r32));
            return Uri.parse("tg://" + MessageObject.getFileName(r4) + r0.toString());
        }

        public static VideoUri of(int r8, TLRPC.Document r9, TLRPC.Document r10, int r11, boolean r12) {
            VideoUri r0 = new VideoUri();
            int r2 = 0;
        L4:
            if (r2 >= r9.attributes.size()) goto L9;
            TLRPC.DocumentAttribute r3 = r9.attributes.get(r2);
            if ((r3 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L7;
            r2 = r2 + 1;
            goto L4
        L7:
            TLRPC.TL_documentAttributeVideo r32 = (TLRPC.TL_documentAttributeVideo) r3;
        L10:
            if (r32 == null) goto L15;
            String r22 = r32.video_codec;
            if (r22 == null) goto L15;
            String r23 = r22.toLowerCase();
        L16:
            r0.currentAccount = r8;
            r0.document = r9;
            r0.docId = r9.id;
            r0.uri = getUri(r8, r9, r11);
            if (r10 == null) goto L28;
            r0.manifestDocument = r10;
            r0.manifestDocId = r10.id;
            r0.m3u8uri = getUri(r8, r10, r11);
            File r112 = FileLoader.getInstance(r8).getPathToAttach(r10, null, false, r12);
            if (r112 != null) goto L21;
        L23:
            File r102 = FileLoader.getInstance(r8).getPathToAttach(r10, null, true, r12);
            if (r102 == null) goto L28;
            if (r102.exists() == false) goto L28;
            r0.m3u8uri = Uri.fromFile(r102);
            goto L28
        L21:
            if (r112.exists() == false) goto L23;
            r0.m3u8uri = Uri.fromFile(r112);
        L28:
            r0.codec = r23;
            long r103 = r9.size;
            r0.size = r103;
            if (r32 == null) goto L31;
            double r6 = r32.duration;
            r0.duration = r6;
            r0.width = r32.w;
            r0.height = r32.h;
            r0.bitrate = r103 / r6;
        L31:
            File r104 = FileLoader.getInstance(r8).getPathToAttach(r9, null, false, r12);
            if (r104 != null) goto L34;
        L37:
            File r82 = FileLoader.getInstance(r8).getPathToAttach(r9, null, true, r12);
            if (r82 != null) goto L40;
        L42:
            return r0;
        L40:
            if (r82.exists() == false) goto L42;
            r0.uri = Uri.fromFile(r82);
            goto L42
        L34:
            if (r104.exists() == false) goto L37;
            r0.uri = Uri.fromFile(r104);
            return r0;
        L15:
            r23 = null;
            goto L16
        L9:
            r32 = null;
            goto L10
        }

        public boolean isCached() {
            Uri r1 = this.uri;
            if (r1 != null) goto L5;
            return false;
        L5:
            if ("file".equalsIgnoreCase(r1.getScheme()) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isManifestCached() {
            Uri r1 = this.m3u8uri;
            if (r1 != null) goto L5;
            return false;
        L5:
            if ("file".equalsIgnoreCase(r1.getScheme()) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public void updateCached(boolean r6) {
            if (isCached() == true) goto L17;
            if (this.document == null) goto L17;
            File r0 = FileLoader.getInstance(this.currentAccount).getPathToAttach(this.document, null, false, r6);
            if (r0 != null) goto L9;
        L11:
            File r02 = FileLoader.getInstance(this.currentAccount).getPathToAttach(this.document, null, true, r6);
            if (r02 == null) goto L17;
            if (r02.exists() == false) goto L17;
            this.uri = Uri.fromFile(r02);
            goto L17
        L9:
            if (r0.exists() == false) goto L11;
            this.uri = Uri.fromFile(r0);
        L17:
            if (isManifestCached() == false) goto L19;
            return;
        L19:
            if (this.manifestDocument == null) goto L33;
            File r03 = FileLoader.getInstance(this.currentAccount).getPathToAttach(this.manifestDocument, null, false, r6);
            if (r03 != null) goto L23;
        L26:
            File r62 = FileLoader.getInstance(this.currentAccount).getPathToAttach(this.manifestDocument, null, true, r6);
            if (r62 != null) goto L29;
            return;
        L29:
            if (r62.exists() == false) goto L35;
            this.m3u8uri = Uri.fromFile(r62);
            return;
        L35:
            return;
        L23:
            if (r03.exists() == false) goto L26;
            this.m3u8uri = Uri.fromFile(r03);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public class VisualizerBufferSink implements TeeAudioProcessor.AudioBufferSink {
        private final int BUFFER_SIZE;
        private final int MAX_BUFFER_SIZE;
        ByteBuffer byteBuffer;
        FourierTransform.FFT fft;
        long lastUpdateTime;
        int position;
        float[] real;
        final /* synthetic */ VideoPlayer this$0;

        public static /* synthetic */ void $r8$lambda$WjbHEFvOdRr3a6gqhHWWQrkpvdY(VisualizerBufferSink r0) {
            r0.lambda$handleBuffer$0();
        }

        public static /* synthetic */ void $r8$lambda$iC0fmDSxVtlDpo7Gci9xOUhCv7k(VisualizerBufferSink r0, float[] r1) {
            r0.lambda$handleBuffer$1(r1);
        }

        public VisualizerBufferSink(VideoPlayer r4) {
            this.this$0 = r4;
            this.BUFFER_SIZE = 1024;
            this.MAX_BUFFER_SIZE = 8192;
            this.fft = new FourierTransform.FFT(1024, 48000.0f);
            this.real = new float[1024];
            this.position = 0;
            ByteBuffer r0 = ByteBuffer.allocateDirect(8192);
            this.byteBuffer = r0;
            r0.position(0);
        }

        private /* synthetic */ void lambda$handleBuffer$0() {
            this.this$0.audioUpdateHandler.removeCallbacksAndMessages(null);
            VideoPlayer.m13508$$Nest$fgetaudioVisualizerDelegate(this.this$0).onVisualizerUpdate(false, true, null);
        }

        private /* synthetic */ void lambda$handleBuffer$1(float[] r2) {
            VideoPlayer.m13508$$Nest$fgetaudioVisualizerDelegate(this.this$0).onVisualizerUpdate(true, true, r2);
        }

        @Override // androidx.media3.exoplayer.audio.TeeAudioProcessor.AudioBufferSink
        public void flush(int r1, int r2, int r3) {
        }

        @Override // androidx.media3.exoplayer.audio.TeeAudioProcessor.AudioBufferSink
        public void handleBuffer(ByteBuffer r9) {
            if (VideoPlayer.m13508$$Nest$fgetaudioVisualizerDelegate(this.this$0) != null) goto L6;
            return;
        L6:
            if (r9 != AudioProcessor.EMPTY_BUFFER) goto L8;
        L50:
            this.this$0.audioUpdateHandler.postDelayed(new VideoPlayer$VisualizerBufferSink$$ExternalSyntheticLambda0(this), 80);
            return;
        L8:
            if (VideoPlayer.m13509$$Nest$fgetmixedPlayWhenReady(this.this$0) == false) goto L50;
            if (VideoPlayer.m13508$$Nest$fgetaudioVisualizerDelegate(this.this$0).needUpdate() == false) goto L64;
            int r0 = r9.limit();
            int r2 = 0;
            if (r0 <= 8192) goto L17;
            this.this$0.audioUpdateHandler.removeCallbacksAndMessages(null);
            VideoPlayer.m13508$$Nest$fgetaudioVisualizerDelegate(this.this$0).onVisualizerUpdate(false, true, null);
            return;
        L17:
            this.byteBuffer.put(r9);
            int r92 = this.position + r0;
            this.position = r92;
            if (r92 < 1024) goto L62;
            this.byteBuffer.position(0);
            int r93 = 0;
        L20:
            if (r93 >= 1024) goto L22;
            this.real[r93] = this.byteBuffer.getShort() / 32768.0f;
            r93 = r93 + 1;
            goto L20
        L22:
            this.byteBuffer.rewind();
            this.position = 0;
            this.fft.forward(this.real);
            float r3 = 0.0f;
            int r1 = 0;
        L23:
            float r4 = 1.0f;
            if (r1 >= 1024) goto L33;
            float r5 = this.fft.getSpectrumReal()[r1];
            float r6 = this.fft.getSpectrumImaginary()[r1];
            float r52 = ((float) Math.sqrt((r5 * r5) + (r6 * r6))) / 30.0f;
            if (r52 > 1.0f) goto L32;
            if (r52 >= 0.0f) goto L31;
            r4 = 0.0f;
            goto L32
        L31:
            r4 = r52;
        L32:
            r3 = r3 + (r4 * r4);
            r1 = r1 + 1;
            goto L23
        L33:
            float r02 = (float) Math.sqrt(r3 / 1024.0f);
            float[] r32 = new float[7];
            r32[6] = r02;
            if (r02 >= 0.4f) goto L37;
        L35:
            if (r2 >= 7) goto L46;
            r32[r2] = 0.0f;
            r2 = r2 + 1;
        L46:
            if ((System.currentTimeMillis() - this.lastUpdateTime) < 64) goto L65;
            this.lastUpdateTime = System.currentTimeMillis();
            this.this$0.audioUpdateHandler.postDelayed(new VideoPlayer$VisualizerBufferSink$$ExternalSyntheticLambda1(this, r32), 130);
            return;
        L65:
            return;
        L37:
            if (r2 >= 6) goto L46;
            int r03 = 170 * r2;
            float r12 = this.fft.getSpectrumReal()[r03];
            float r04 = this.fft.getSpectrumImaginary()[r03];
            float r05 = (float) (Math.sqrt((r12 * r12) + (r04 * r04)) / 30.0d);
            r32[r2] = r05;
            if (r05 <= 1.0f) goto L42;
            r32[r2] = 1.0f;
        L44:
            r2 = r2 + 1;
            goto L37
        L42:
            if (r05 >= 0.0f) goto L44;
            r32[r2] = 0.0f;
            goto L44
        L62:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$7rCbITW00VAJMikLErzek1ELoHs(VideoPlayer r0, PlaybackException r1) {
        r0.lambda$onPlayerError$3(r1);
    }

    public static /* synthetic */ DataSource $r8$lambda$jWh9mJDcbrkxazS0DTsXGT26cNw(VideoPlayer r0, long r1) {
        return r0.lambda$mediaSourceFromUri$0(r1);
    }

    /* renamed from: $r8$lambda$l-WcqUYvWpJVWuzrcGpf3lI_qL8, reason: not valid java name */
    public static /* synthetic */ void m13506$r8$lambda$lWcqUYvWpJVWuzrcGpf3lI_qL8(VideoPlayer r0, CueGroup r1) {
        r0.lambda$onCues$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$xsEBWC2rRnwEncfRebHEd4DD2Ek(VideoPlayer r0) {
        r0.lambda$onPlayerError$2();
    }

    /* renamed from: -$$Nest$fgetaudioPlayerReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13507$$Nest$fgetaudioPlayerReady(VideoPlayer r0) {
        return r0.audioPlayerReady;
    }

    /* renamed from: -$$Nest$fgetaudioVisualizerDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioVisualizerDelegate m13508$$Nest$fgetaudioVisualizerDelegate(VideoPlayer r0) {
        return r0.audioVisualizerDelegate;
    }

    /* renamed from: -$$Nest$fgetmixedPlayWhenReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13509$$Nest$fgetmixedPlayWhenReady(VideoPlayer r0) {
        return r0.mixedPlayWhenReady;
    }

    /* renamed from: -$$Nest$fputaudioPlayerReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13510$$Nest$fputaudioPlayerReady(VideoPlayer r0, boolean r1) {
        r0.audioPlayerReady = r1;
    }

    /* renamed from: -$$Nest$mcheckPlayersReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13511$$Nest$mcheckPlayersReady(VideoPlayer r0) {
        r0.checkPlayersReady();
    }

    static {
        activePlayers = new HashSet();
        playerCounter = 0;
    }

    public VideoPlayer() {
        this(true, false);
    }

    public VideoPlayer(boolean r4, boolean r5) {
        int r0 = lastPlayerId;
        lastPlayerId = r0 + 1;
        this.playerId = r0;
        this.audioTrackState = 0;
        this.audioUpdateHandler = new Handler(Looper.getMainLooper());
        this.autoIsOriginal = false;
        this.selectedQualityIndex = -1;
        this.fallbackDuration = -9223372036854775807L;
        this.fallbackPosition = -9223372036854775807L;
        this.seekFinishedListeners = new ArrayList();
        this.handleAudioFocus = false;
        this.audioDisabled = r5;
        this.mediaDataSourceFactory = new ExtendedDefaultDataSourceFactory(ApplicationLoader.applicationContext, "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20150101 Firefox/47.0 (Chrome)");
        DefaultTrackSelector r02 = new DefaultTrackSelector(ApplicationLoader.applicationContext, new AdaptiveTrackSelection.Factory());
        this.trackSelector = r02;
        if (r5 == false) goto L5;
        r02.setParameters(r02.getParameters().buildUpon().setTrackTypeDisabled(1, true).build());
    L5:
        this.lastReportedPlaybackState = 1;
        this.shouldPauseOther = r4;
        if (r4 == false) goto L8;
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.playerDidStartPlaying);
    L8:
        playerCounter++;
    }

    private void checkPlayersReady() {
        if (this.audioPlayerReady == true) goto L5;
        return;
    L5:
        if (this.videoPlayerReady == true) goto L7;
        return;
    L7:
        if (this.mixedPlayWhenReady == false) goto L12;
        play();
        return;
    }

    private void ensurePlayerCreated() {
        DefaultLoadControl.Builder r0 = new DefaultLoadControl.Builder().setAllocator(new DefaultAllocator(true, 65536));
        boolean r1 = this.isStory;
        int r2 = 1000;
        if (r1 == false) goto L5;
        int r4 = 1000;
    L6:
        if (r1 == true) goto L9;
        r2 = CastStatusCodes.AUTHENTICATION_FAILED;
    L9:
        int r12 = 0;
        DefaultLoadControl r02 = r0.setBufferDurationsMs(50000, 50000, r4, r2).setTargetBufferBytes(-1).setPrioritizeTimeOverSizeThresholds(false).setBackBuffer(0, false).build();
        if (this.player != null) goto L32;
        if (this.audioVisualizerDelegate == null) goto L14;
        DefaultRenderersFactory r22 = new AudioVisualizerRenderersFactory(this, ApplicationLoader.applicationContext);
    L15:
        r22.setExtensionRendererMode(1);
        ExoPlayer.Builder r23 = new ExoPlayer.Builder(ApplicationLoader.applicationContext).setRenderersFactory(r22).setTrackSelector(this.trackSelector).setLoadControl(r02);
        Looper r3 = this.looper;
        if (r3 == null) goto L18;
        r23.setLooper(r3);
    L18:
        ExoPlayer r24 = r23.build();
        this.player = r24;
        r24.addAnalyticsListener(this);
        this.player.addListener(this);
        this.player.addVideoListener(this);
        TextureView r25 = this.textureView;
        if (r25 == null) goto L21;
        this.player.setVideoTextureView(r25);
    L27:
        this.player.setPlayWhenReady(this.autoplay);
        ExoPlayer r26 = this.player;
        if (this.looping == false) goto L30;
        r12 = 2;
    L30:
        r26.setRepeatMode(r12);
        goto L32
    L21:
        Surface r27 = this.surface;
        if (r27 == null) goto L24;
        this.player.setVideoSurface(r27);
        goto L27
    L24:
        SurfaceView r28 = this.surfaceView;
        if (r28 == null) goto L27;
        this.player.setVideoSurfaceView(r28);
        goto L27
    L14:
        r22 = new DefaultRenderersFactory(ApplicationLoader.applicationContext);
    L32:
        if (this.mixedAudio == true) goto L34;
        return;
    L34:
        if (this.audioPlayer != null) goto L38;
        ExoPlayer r03 = new ExoPlayer.Builder(ApplicationLoader.applicationContext).setTrackSelector(this.trackSelector).setLoadControl(r02).build();
        this.audioPlayer = r03;
        r03.addListener(new AnonymousClass1(this));
        this.audioPlayer.setPlayWhenReady(this.autoplay);
        return;
    L38:
        return;
    L5:
        r4 = 100;
        goto L6
    }

    public static VideoUri getCachedQuality(ArrayList<Quality> r9) {
        if (r9 != null) goto L5;
        return null;
    L5:
        int r1 = r9.size();
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L12;
        Quality r4 = r9.get(r3);
        r3 = r3 + 1;
        ArrayList<VideoUri> r42 = r4.uris;
        int r5 = r42.size();
        int r6 = 0;
    L8:
        if (r6 >= r5) goto L6;
        VideoUri r7 = r42.get(r6);
        r6 = r6 + 1;
        VideoUri r72 = r7;
        if (r72.isCached() == false) goto L8;
        return r72;
    L12:
        return null;
    }

    public static Boolean getLooping(MessageObject r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        SharedPreferences r1 = ApplicationLoader.applicationContext.getSharedPreferences("media_saved_pos", 0);
        String r62 = r6.getDialogId() + "_" + r6.getId() + "loop";
        if (r1.contains(r62) == true) goto L9;
        return null;
    L9:
        return Boolean.valueOf(r1.getBoolean(r62, false));
    }

    public static ArrayList<Quality> getQualities(int r6, TLRPC.Document r7, ArrayList<TLRPC.Document> r8, int r9, boolean r10) {
        return getQualities(r6, r7, r8, r9, r10, true);
    }

    public static ArrayList<Quality> getQualities(int r9, TLRPC.Document r10, ArrayList<TLRPC.Document> r11, int r12, boolean r13, boolean r14) {
        ArrayList r0 = new ArrayList();
        if (r10 == null) goto L6;
        r0.add(r10);
    L6:
        if (MessagesController.getInstance(r9).videoIgnoreAltDocuments == true) goto L9;
        if (r11 == null) goto L9;
        r0.addAll(r11);
    L9:
        LongSparseArray r112 = new LongSparseArray();
        int r1 = 0;
        int r2 = 0;
    L11:
        if (r2 >= r0.size()) goto L24;
        TLRPC.Document r3 = (TLRPC.Document) r0.get(r2);
        if ("application/x-mpegurl".equalsIgnoreCase(r3.mime_type) == false) goto L23;
        String r4 = r3.file_name_fixed;
        if (r4 == null) goto L23;
        if (r4.startsWith("mtproto") == false) goto L23;
        r112.put(Long.parseLong(r3.file_name_fixed.substring(7)), r3);     // Catch: Exception -> L21
        r0.remove(r2);     // Catch: Exception -> L21
        r2 = r2 - 1;
    L21:
        e = move-exception;
        FileLog.e(e);
    L23:
        r2 = r2 + 1;
        goto L11
    L24:
        ArrayList r22 = new ArrayList();
        int r32 = 0;
    L26:
        if (r32 >= r0.size()) goto L47;
        TLRPC.Document r6 = (TLRPC.Document) r0.get(r32);     // Catch: Exception -> L41
        if ("application/x-mpegurl".equalsIgnoreCase(r6.mime_type) == true) goto L46;
        if ("application/x-tgstoryboard".equalsIgnoreCase(r6.mime_type) == true) goto L46;
        if ("application/x-tgstoryboardmap".equalsIgnoreCase(r6.mime_type) == true) goto L46;
        VideoUri r7 = VideoUri.of(r9, r6, (TLRPC.Document) r112.get(r6.id), r12, r14);     // Catch: Exception -> L41
        if (r7.width <= 0) goto L46;
        if (r7.height <= 0) goto L46;
        if (r6 != r10) goto L43;
        r7.original = true;     // Catch: Exception -> L41
    L43:
        r22.add(r7);     // Catch: Exception -> L41
    L46:
        r32 = r32 + 1;
    L41:
        e = move-exception;
        FileLog.e(e);
        goto L46
    L47:
        ArrayList r92 = new ArrayList();
    L49:
        if (r1 >= r22.size()) goto L84;
        VideoUri r102 = (VideoUri) r22.get(r1);
        String r113 = r102.codec;
        if (r113 != null) goto L53;
    L82:
        r92.add(r102);
    L83:
        r1 = r1 + 1;
        goto L49
    L53:
        if (r13 == false) goto L70;
        if ("avc".equals(r113) == true) goto L82;
        if ("h264".equals(r102.codec) == true) goto L82;
        if ("vp9".equals(r102.codec) == true) goto L82;
        if ("vp8".equals(r102.codec) == true) goto L82;
        if ("av1".equals(r102.codec) == true) goto L67;
        if ("av01".equals(r102.codec) == false) goto L83;
    L67:
        if (supportsHardwareDecoder(r102.codec) == true) goto L82;
    L70:
        if ("av1".equals(r113) == true) goto L80;
        if ("av01".equals(r102.codec) == true) goto L80;
        if ("hevc".equals(r102.codec) == true) goto L80;
        if ("h265".equals(r102.codec) == true) goto L80;
        if ("vp9".equals(r102.codec) == false) goto L82;
    L80:
        if (supportsHardwareDecoder(r102.codec) == true) goto L82;
    L84:
        ArrayList r103 = new ArrayList();
        if (r92.isEmpty() == false) goto L87;
        r103.addAll(r22);
    L89:
        return Quality.group(r103);
    L87:
        r103.addAll(r92);
        goto L89
    }

    public static ArrayList<Quality> getQualities(int r6, TLRPC.MessageMedia r7, boolean r8) {
        if ((r7 instanceof TLRPC.TL_messageMediaDocument) == true) goto L7;
        return new ArrayList();
    L7:
        return getQualities(r6, r7.document, r7.alt_documents, 0, false, r8);
    }

    public static VideoUri getQualityForPlayer(ArrayList<Quality> r14) {
        int r0 = r14.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        Quality r3 = r14.get(r2);
        r2 = r2 + 1;
        ArrayList<VideoUri> r32 = r3.uris;
        int r4 = r32.size();
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L3;
        VideoUri r6 = r32.get(r5);
        r5 = r5 + 1;
        VideoUri r62 = r6;
        if (r62.original == false) goto L5;
        if (r62.isCached() == false) goto L5;
        return r62;
    L11:
        int r02 = r14.size();
        VideoUri r22 = null;
        int r33 = 0;
    L12:
        if (r33 >= r02) goto L27;
        Quality r42 = r14.get(r33);
        r33 = r33 + 1;
        ArrayList<VideoUri> r43 = r42.uris;
        int r52 = r43.size();
        int r63 = 0;
    L14:
        if (r63 >= r52) goto L12;
        VideoUri r7 = r43.get(r63);
        r63 = r63 + 1;
        VideoUri r72 = r7;
        if (r72.original == true) goto L14;
        if (supportsHardwareDecoder(r72.codec) == false) goto L14;
        if (r22 == null) goto L26;
        int r8 = r72.width;
        int r9 = r72.height;
        int r10 = r8 * r9;
        int r11 = r22.width;
        int r12 = r22.height;
        if (r10 > (r11 * r12)) goto L26;
        if ((r8 * r9) != (r11 * r12)) goto L14;
        if (r72.bitrate >= r22.bitrate) goto L14;
    L26:
        r22 = r72;
        goto L14
    L27:
        if (r22 != null) goto L39;
        int r03 = r14.size();
        int r34 = 0;
    L29:
        if (r34 >= r03) goto L39;
        Quality r44 = r14.get(r34);
        r34 = r34 + 1;
        ArrayList<VideoUri> r45 = r44.uris;
        int r53 = r45.size();
        int r64 = 0;
    L31:
        if (r64 >= r53) goto L29;
        VideoUri r73 = r45.get(r64);
        r64 = r64 + 1;
        VideoUri r74 = r73;
        if (r22 == null) goto L38;
        if ((r22.width * r22.height) > (r74.width * r74.height)) goto L38;
        if (r74.bitrate >= r22.bitrate) goto L31;
    L38:
        r22 = r74;
    L39:
        return r22;
    }

    public static VideoUri getQualityForThumb(ArrayList<Quality> r12) {
        int r0 = r12.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        Quality r3 = r12.get(r2);
        r2 = r2 + 1;
        ArrayList<VideoUri> r32 = r3.uris;
        int r4 = r32.size();
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L3;
        VideoUri r6 = r32.get(r5);
        r5 = r5 + 1;
        VideoUri r62 = r6;
        if (r62.isCached() == false) goto L5;
        return r62;
    L9:
        int r02 = r12.size();
        VideoUri r22 = null;
        int r33 = 0;
    L10:
        if (r33 >= r02) goto L25;
        Quality r42 = r12.get(r33);
        r33 = r33 + 1;
        ArrayList<VideoUri> r43 = r42.uris;
        int r52 = r43.size();
        int r63 = 0;
    L12:
        if (r63 >= r52) goto L10;
        VideoUri r7 = r43.get(r63);
        r63 = r63 + 1;
        VideoUri r72 = r7;
        if (r72.original == true) goto L12;
        if (r22 == null) goto L21;
        if ((r22.width * r22.height) > (r72.width * r72.height)) goto L21;
        if (r72.bitrate >= r22.bitrate) goto L12;
    L21:
        if (r72.width > 900) goto L12;
        if (r72.height > 900) goto L12;
        r22 = r72;
        goto L12
    L25:
        if (r22 != null) goto L37;
        int r03 = r12.size();
        int r34 = 0;
    L27:
        if (r34 >= r03) goto L37;
        Quality r44 = r12.get(r34);
        r34 = r34 + 1;
        ArrayList<VideoUri> r45 = r44.uris;
        int r53 = r45.size();
        int r64 = 0;
    L29:
        if (r64 >= r53) goto L27;
        VideoUri r73 = r45.get(r64);
        r64 = r64 + 1;
        VideoUri r74 = r73;
        if (r22 == null) goto L36;
        if ((r22.width * r22.height) > (r74.width * r74.height)) goto L36;
        if (r74.bitrate >= r22.bitrate) goto L29;
    L36:
        r22 = r74;
    L37:
        return r22;
    }

    private TrackSelectionOverride getQualityTrackSelection(VideoUri r11) {
        int r0 = this.manifestUris.indexOf(r11);     // Catch: Exception -> L28
        MappingTrackSelector.MappedTrackInfo r10 = this.trackSelector.getCurrentMappedTrackInfo();     // Catch: Exception -> L28
        int r2 = 0;
    L4:
        if (r2 >= r10.getRendererCount()) goto L42;
        TrackGroupArray r3 = r10.getTrackGroups(r2);     // Catch: Exception -> L28
        int r4 = 0;
    L7:
        if (r4 >= r3.length) goto L27;
        TrackGroup r5 = r3.get(r4);     // Catch: Exception -> L28
        int r6 = 0;
    L10:
        if (r6 >= r5.length) goto L26;
        Format r7 = r5.getFormat(r6);     // Catch: Exception -> L28
        int r8 = Integer.parseInt(r7.id);     // Catch: Exception -> L14
    L15:
        if (r8 < 0) goto L20;
        if (r0 != r8) goto L20;
        return new TrackSelectionOverride(r5, r6);
    L20:
        if (r7.width != r11.width) goto L25;
        if (r7.height != r11.height) goto L25;
        return new TrackSelectionOverride(r5, r6);
    L25:
        r6 = r6 + 1;
    L14:
        r8 = -1;
        goto L15
    L26:
        r4 = r4 + 1;
        goto L7
    L27:
        r2 = r2 + 1;
        goto L4
    L42:
        return null;
    L28:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Quality getSavedQuality(ArrayList<Quality> r5, long r6, int r8) {
        int r2 = 0;
        String r62 = ApplicationLoader.applicationContext.getSharedPreferences("media_saved_pos", 0).getString(r6 + "_" + r8 + "q2", _UrlKt.FRAGMENT_ENCODE_SET);
        if (TextUtils.isEmpty(r62) == false) goto L5;
        return null;
    L5:
        int r82 = r5.size();
    L6:
        if (r2 >= r82) goto L14;
        Quality r1 = r5.get(r2);
        r2 = r2 + 1;
        Quality r12 = r1;
        StringBuilder r3 = new StringBuilder();
        r3.append(r12.width);
        r3.append("x");
        r3.append(r12.height);
        if (r12.original == false) goto L10;
        String r4 = "s";
    L11:
        r3.append(r4);
        if (TextUtils.equals(r62, r3.toString()) == false) goto L6;
        return r12;
    L10:
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L11
    L14:
        return null;
    }

    public static Quality getSavedQuality(ArrayList<Quality> r4, MessageObject r5) {
        if (ExteraConfig.getPreferOriginalQuality() == false) goto L9;
        int r0 = r4.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L9;
        Quality r2 = r4.get(r1);
        r1 = r1 + 1;
        Quality r22 = r2;
        if (r22.original == false) goto L5;
        return r22;
    L9:
        if (r5 != null) goto L13;
        return null;
    L13:
        return getSavedQuality(r4, r5.getDialogId(), r5.getId());
    }

    private /* synthetic */ DataSource lambda$mediaSourceFromUri$0(long r2) {
        return new OffsetDataSource(this.mediaDataSourceFactory.createDataSource(), r2);
    }

    private /* synthetic */ void lambda$onCues$1(CueGroup r1) {
        VideoPlayerDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onCues(r1);
        return;
    }

    private /* synthetic */ void lambda$onPlayerError$2() {
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L13;
        r0.clearVideoTextureView(this.textureView);
        this.player.setVideoTextureView(this.textureView);
        ArrayList<Quality> r02 = this.videoQualities;
        if (r02 == null) goto L7;
        preparePlayer(r02, this.videoQualityToSelect);
    L11:
        play();
        return;
    L7:
        boolean r03 = this.loopingMediaSource;
        Uri r1 = this.videoUri;
        if (r03 == false) goto L10;
        preparePlayerLoop(r1, this.videoType, this.audioUri, this.audioType);
        goto L11
    L10:
        preparePlayer(r1, this.videoType);
        goto L11
    }

    private /* synthetic */ void lambda$onPlayerError$3(PlaybackException r5) {
        Throwable r0 = r5.getCause();
        if ((r0 instanceof MediaCodecDecoderException) == true) goto L5;
    L15:
        TextureView r1 = this.textureView;
        if (r1 != null) goto L18;
    L41:
        VideoPlayerDelegate r02 = this.delegate;
        if (r02 == null) goto L47;
        r02.onError(this, r5);
        return;
    L47:
        return;
    L18:
        if (this.triedReinit == true) goto L22;
        if ((r0 instanceof MediaCodecRenderer.DecoderInitializationException) == false) goto L22;
    L23:
        this.triedReinit = true;
        if (this.player == null) goto L46;
        ViewGroup r52 = (ViewGroup) r1.getParent();
        if (r52 == null) goto L28;
        int r03 = r52.indexOfChild(this.textureView);
        r52.removeView(this.textureView);
        r52.addView(this.textureView, r03);
    L28:
        DispatchQueue r53 = this.workerQueue;
        if (r53 == null) goto L32;
        r53.postRunnable(new VideoPlayer$$ExternalSyntheticLambda4(this));
        return;
    L32:
        this.player.clearVideoTextureView(this.textureView);
        this.player.setVideoTextureView(this.textureView);
        ArrayList<Quality> r54 = this.videoQualities;
        if (r54 == null) goto L35;
        preparePlayer(r54, this.videoQualityToSelect);
    L39:
        play();
        return;
    L35:
        boolean r55 = this.loopingMediaSource;
        Uri r04 = this.videoUri;
        if (r55 == false) goto L38;
        preparePlayerLoop(r04, this.videoType, this.audioUri, this.audioType);
        goto L39
    L38:
        preparePlayer(r04, this.videoType);
        goto L39
    L46:
        return;
    L22:
        if ((r0 instanceof SurfaceNotValidException) == false) goto L41;
    L5:
        if (r0.toString().contains("av1") == false) goto L7;
    L8:
        FileLog.e(r5);
        FileLog.e("av1 codec failed, we think this codec is not supported");
        MessagesController.getGlobalMainSettings().edit().putBoolean("unsupport_video/av01", true).commit();
        HashMap<String, Boolean> r56 = cachedSupportedCodec;
        if (r56 == null) goto L11;
        r56.clear();
    L11:
        ArrayList<Quality> r57 = Quality.filterByCodec(this.videoQualities);
        this.videoQualities = r57;
        if (r57 == null) goto L45;
        preparePlayer(r57, this.videoQualityToSelect);
        return;
    L45:
        return;
    L7:
        if (r0.toString().contains("av01") == false) goto L15;
        goto L8
    }

    private void maybeReportPlayerState() {
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L13;
        boolean r02 = r0.getPlayWhenReady();
        int r1 = this.player.getPlaybackState();
        if (this.lastReportedPlayWhenReady == r02) goto L8;
    L11:
        this.delegate.onStateChanged(r02, r1);
        this.lastReportedPlayWhenReady = r02;
        this.lastReportedPlaybackState = r1;
        return;
    L8:
        if (this.lastReportedPlaybackState != r1) goto L11;
        return;
    }

    private MediaSource maybeWrapWithExternalSubtitle(MediaSource r4) {
        if (this.currentExternalSubtitle != null) goto L6;
        return r4;
    L6:
        return new MergingMediaSource(new MediaSource[]{r4, new SingleSampleMediaSource.Factory(this.mediaDataSourceFactory).createMediaSource(this.currentExternalSubtitle.toSubtitleConfiguration(), -9223372036854775807L)});
    }

    private MediaSource mediaSourceFromUri(Uri r7, long r8, String r10) {
        return mediaSourceFromUri(r7, r8, r10, true);
    }

    private MediaSource mediaSourceFromUri(Uri r3, long r4, String r6, boolean r7) {
        MediaItem r32 = new MediaItem.Builder().setUri(r3).build();
        if (r4 == 0) goto L9;
        ProgressiveMediaSource r33 = new ProgressiveMediaSource.Factory(new VideoPlayer$$ExternalSyntheticLambda1(this, r4)).createMediaSource(r32);
        if (r7 == true) goto L7;
        return r33;
    L7:
        return maybeWrapWithExternalSubtitle(r33);
    L9:
        r6.getClass();
        if (r6.equals("hls") == true) goto L22;
        if (r6.equals("dash") == true) goto L18;
        if (this.progressiveMediaSourceFactory != null) goto L16;
        this.progressiveMediaSourceFactory = new ProgressiveMediaSource.Factory(this.mediaDataSourceFactory);
    L16:
        MediaSource r34 = this.progressiveMediaSourceFactory.createMediaSource(r32);
    L25:
        if (r7 == true) goto L27;
        return r34;
    L27:
        return maybeWrapWithExternalSubtitle(r34);
    L18:
        if (this.dashMediaSourceFactory != null) goto L20;
        this.dashMediaSourceFactory = new DashMediaSource.Factory(this.mediaDataSourceFactory);
    L20:
        r34 = this.dashMediaSourceFactory.createMediaSource(r32);
        goto L25
    L22:
        if (this.hlsMediaSourceFactory != null) goto L24;
        this.hlsMediaSourceFactory = new HlsMediaSource.Factory(this.mediaDataSourceFactory);
    L24:
        r34 = this.hlsMediaSourceFactory.createMediaSource(r32);
        goto L25
    }

    private MediaSource mediaSourceFromUri(VideoUri r7, String r8) {
        return mediaSourceFromUri(r7.uri, r7.fileVideoOffset, r8, true);
    }

    public static void saveLooping(boolean r4, MessageObject r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("media_saved_pos", 0);
        r0.edit().putBoolean(r5.getDialogId() + "_" + r5.getId() + "loop", r4).apply();
    }

    public static void saveQuality(Quality r4, long r5, int r7) {
        SharedPreferences.Editor r0 = ApplicationLoader.applicationContext.getSharedPreferences("media_saved_pos", 0).edit();
        if (r4 != null) goto L5;
        r0.remove(r5 + "_" + r7 + "q2");
    L10:
        r0.apply();
        return;
    L5:
        String r52 = r5 + "_" + r7 + "q2";
        StringBuilder r6 = new StringBuilder();
        r6.append(r4.width);
        r6.append("x");
        r6.append(r4.height);
        if (r4.original == false) goto L8;
        String r42 = "s";
    L9:
        r6.append(r42);
        r0.putString(r52, r6.toString());
        goto L10
    L8:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    public static void saveQuality(Quality r2, MessageObject r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        saveQuality(r2, r3.getDialogId(), r3.getId());
    }

    private void setAudioTrackState(int r2) {
        if (this.audioTrackState == r2) goto L10;
        this.audioTrackState = r2;
        VideoPlayerDelegate r1 = this.delegate;
        if (r1 == null) goto L9;
        r1.onAudioTrackChanged(r2);
        return;
    L9:
        return;
    }

    private void setSelectedQuality(boolean r12, Quality r13) {
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L73;
        boolean r02 = r0.isPlaying();
        long r1 = this.player.getCurrentPosition();
        if (r12 == true) goto L8;
        this.fallbackPosition = r1;
        this.fallbackDuration = this.player.getDuration();
    L8:
        this.videoQualityToSelect = r13;
        boolean r7 = true;
        int r8 = 0;
        if (r13 != null) goto L30;
        Uri r132 = makeManifest(this.videoQualities);
        Quality r9 = getOriginalQuality();
        if (r9 != null) goto L13;
    L17:
        if (r132 == null) goto L22;
        this.autoIsOriginal = false;
        MappingTrackSelector r6 = this.trackSelector;
        r6.setParameters(r6.getParameters().buildUpon().clearOverrides().build());
        if (this.currentStreamIsHls == true) goto L21;
        this.currentStreamIsHls = true;
        this.player.setMediaSource(mediaSourceFromUri(r132, 0, "hls"), false);
    L55:
        if (r7 == false) goto L72;
        this.player.prepare();
        if (r12 == true) goto L61;
        this.player.seekTo(r1);
        if (r02 == false) goto L61;
        this.player.play();
    L61:
        Runnable r122 = this.onQualityChangeListener;
        if (r122 == null) goto L64;
        AndroidUtilities.runOnUIThread(r122);
    L64:
        activePlayers.add(Integer.valueOf(this.playerId));
        return;
    L72:
        return;
    L21:
        r7 = false;
        goto L55
    L22:
        Quality r133 = getHighestQuality(Boolean.TRUE);
        if (r133 != null) goto L25;
        r133 = getHighestQuality(Boolean.FALSE);
    L25:
        if (r133 != null) goto L27;
        return;
    L27:
        if (r133.uris.isEmpty() == true) goto L74;
        this.currentStreamIsHls = false;
        this.videoQualityToSelect = r133;
        this.autoIsOriginal = r133.original;
        this.player.setMediaSource(mediaSourceFromUri(r133.getDownloadUri(), "other"), false);
        goto L55
    L74:
        return;
    L13:
        if (r9.uris.size() != 1) goto L17;
        if (r9.uris.get(0).isCached() == false) goto L17;
        this.currentStreamIsHls = false;
        this.autoIsOriginal = true;
        this.videoQualityToSelect = r9;
        this.player.setMediaSource(mediaSourceFromUri(r9.getDownloadUri(), "other"), false);
        goto L55
    L30:
        this.autoIsOriginal = false;
        if (r13.uris.isEmpty() == false) goto L34;
        return;
    L34:
        if (r13.uris.size() <= 1) goto L36;
        Uri r92 = makeManifest(this.videoQualities);
    L37:
        if (r92 != null) goto L39;
    L54:
        this.currentStreamIsHls = false;
        this.player.setMediaSource(mediaSourceFromUri(r13.getDownloadUri(), "other"), false);
        goto L55
    L39:
        if (r13.uris.size() == 1) goto L54;
        if (this.trackSelector.getCurrentMappedTrackInfo() == null) goto L54;
        if (this.currentStreamIsHls == true) goto L46;
        this.currentStreamIsHls = true;
        this.player.setMediaSource(mediaSourceFromUri(r92, 0, "hls"), false);
    L47:
        TrackSelectionParameters.Builder r3 = this.trackSelector.getParameters().buildUpon().clearOverrides();
        ArrayList<VideoUri> r134 = r13.uris;
        int r4 = r134.size();
    L48:
        if (r8 >= r4) goto L53;
        VideoUri r5 = r134.get(r8);
        r8 = r8 + 1;
        TrackSelectionOverride r52 = getQualityTrackSelection(r5);
        if (r52 == null) goto L48;
        r3.addOverride(r52);
        goto L48
    L53:
        this.trackSelector.setParameters(r3.build());
        goto L55
    L46:
        r7 = false;
        goto L47
    L36:
        r92 = null;
        goto L37
    }

    public static boolean supportsHardwareDecoder(String r6) {
        String r62 = toMime(r6);     // Catch: Exception -> L9
        if (r62 != null) goto L7;
        return false;
    L7:
        if (cachedSupportedCodec != null) goto L11;
        cachedSupportedCodec = new HashMap();     // Catch: Exception -> L9
    L11:
        Boolean r2 = cachedSupportedCodec.get(r62);     // Catch: Exception -> L9
        if (r2 == null) goto L16;
        return r2.booleanValue();
    L16:
        if (MessagesController.getGlobalMainSettings().getBoolean("unsupport_".concat(r62), false) == false) goto L18;
        return false;
    L18:
        int r0 = MediaCodecList.getCodecCount();     // Catch: Exception -> L9
        int r22 = 0;
    L19:
        if (r22 >= r0) goto L35;
        MediaCodecInfo r3 = MediaCodecList.getCodecInfoAt(r22);     // Catch: Exception -> L9
        if (r3.isEncoder() == false) goto L24;
    L34:
        r22 = r22 + 1;     // Catch: Exception -> L9
        goto L19
    L24:
        if (MediaCodecUtil.isHardwareAccelerated(r3, r62) == false) goto L34;
        String[] r32 = r3.getSupportedTypes();     // Catch: Exception -> L9
        int r4 = 0;
    L28:
        if (r4 >= r32.length) goto L34;
        if (r32[r4].equalsIgnoreCase(r62) == true) goto L31;
        r4 = r4 + 1;     // Catch: Exception -> L9
        goto L28
    L31:
        cachedSupportedCodec.put(r62, Boolean.TRUE);     // Catch: Exception -> L9
        return true;
    L35:
        cachedSupportedCodec.put(r62, Boolean.FALSE);     // Catch: Exception -> L9
        return false;
    L9:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static String toMime(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        char r1 = 65535;
        switch(r2.hashCode()) {
            case 96924: goto L37;
            case 96974: goto L33;
            case 116926: goto L29;
            case 116927: goto L25;
            case 3004662: goto L21;
            case 3148040: goto L17;
            case 3148041: goto L13;
            case 3199082: goto L9;
            default: goto L40;
        };
    L40:
        switch(r1) {
            case 0: goto L51;
            case 1: goto L49;
            case 2: goto L47;
            case 3: goto L45;
            case 4: goto L51;
            case 5: goto L49;
            case 6: goto L43;
            case 7: goto L43;
            default: goto L42;
        };
    L43:
        return "video/hevc";
    L45:
        return "video/x-vnd.on2.vp9";
    L47:
        return "video/x-vnd.on2.vp8";
    L49:
        return MediaController.VIDEO_MIME_TYPE;
    L51:
        return "video/av01";
    L42:
        return "video/".concat(r2);
    L9:
        if (r2.equals("hevc") == false) goto L40;
        r1 = 7;
        goto L40
    L13:
        if (r2.equals("h265") == false) goto L40;
        r1 = 6;
        goto L40
    L17:
        if (r2.equals("h264") == false) goto L40;
        r1 = 5;
        goto L40
    L21:
        if (r2.equals("av01") == false) goto L40;
        r1 = 4;
        goto L40
    L25:
        if (r2.equals("vp9") == false) goto L40;
        r1 = 3;
        goto L40
    L29:
        if (r2.equals("vp8") == false) goto L40;
        r1 = 2;
        goto L40
    L33:
        if (r2.equals("avc") == false) goto L40;
        r1 = 1;
        goto L40
    L37:
        if (r2.equals("av1") == false) goto L40;
        r1 = 0;
        goto L40
    }

    public boolean createdWithAudioTrack() {
        return !this.audioDisabled;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.playerDidStartPlaying) goto L5;
        return;
    L5:
        if (((VideoPlayer) r3[0]) != this) goto L7;
        return;
    L7:
        if (isPlaying() == true) goto L9;
        return;
    L9:
        if (this.allowMultipleInstances == true) goto L15;
        pause();
        return;
    }

    public int getAudioTrackState() {
        return this.audioTrackState;
    }

    public long getBufferedPosition() {
        ExoPlayer r0 = this.player;
        if (r0 != null) goto L5;
        return 0;
    L5:
        if (this.isStreaming == false) goto L9;
        return r0.getBufferedPosition();
    L9:
        return r0.getDuration();
    }

    public ChromecastMediaVariations getCurrentChromecastMedia(String r16, String r17, String r18) {
        String r4 = "video/mp4";
        if (this.videoQualities == null) goto L5;
        ChromecastMediaVariations.Builder r3 = new ChromecastMediaVariations.Builder();
        ArrayList<Quality> r0 = this.videoQualities;
        int r7 = r0.size();
        int r9 = 0;
    L14:
        if (r9 >= r7) goto L26;
        Quality r10 = r0.get(r9);
        r9 = r9 + 1;
        ArrayList<VideoUri> r102 = r10.uris;
        int r11 = r102.size();
        int r12 = 0;
    L16:
        if (r12 >= r11) goto L14;
        VideoUri r13 = r102.get(r12);
        r12 = r12 + 1;
        VideoUri r132 = r13;
        StringBuilder r14 = new StringBuilder("/mtproto_");
        int r15 = r7;
        r14.append(r132.docId);
        String r6 = r14.toString();
        TLRPC.Document r72 = r132.document;
        if (r72 == null) goto L20;
        String r73 = r72.mime_type;
    L22:
        if (TextUtils.isEmpty(r73) == false) goto L24;
        r73 = "video/mp4";
    L24:
        r3.add(ChromecastMedia.Builder.fromUri(r132.uri, r6, r73).setTitle(r17).setSubtitle(r18).setSize(r132.width, r132.height).build());
        r7 = r15;
        goto L16
    L20:
        r73 = null;
        goto L22
    L26:
        return r3.build();
    L5:
        if (this.videoUri != null) goto L7;
        return null;
    L7:
        String r32 = "/mtproto_" + r16;
        String r5 = this.videoUri.getQueryParameter("mime");
        if (TextUtils.isEmpty(r5) == true) goto L12;
        r4 = r5;
    L12:
        return ChromecastMediaVariations.of(ChromecastMedia.Builder.fromUri(this.videoUri, r32, r4).setTitle(r17).setSubtitle(r18).build());
    }

    public TLRPC.Document getCurrentDocument() {
        ExoPlayer r0 = this.player;
        if (r0 != null) goto L5;
        return null;
    L5:
        Format r02 = r0.getVideoFormat();
        if (r02 != null) goto L8;
    L20:
        return null;
    L8:
        if (r02.documentId == 0) goto L20;
        ArrayList<Quality> r13 = this.videoQualities;
        if (r13 == null) goto L20;
        int r2 = r13.size();
        int r4 = 0;
    L13:
        if (r4 >= r2) goto L20;
        Quality r5 = r13.get(r4);
        r4 = r4 + 1;
        ArrayList<VideoUri> r52 = r5.uris;
        int r6 = r52.size();
        int r7 = 0;
    L15:
        if (r7 >= r6) goto L13;
        VideoUri r8 = r52.get(r7);
        r7 = r7 + 1;
        VideoUri r82 = r8;
        if (r82.docId != r02.documentId) goto L15;
        return r82.document;
    }

    public long getCurrentPosition() {
        long r0 = this.fallbackPosition;
        if (r0 == (-9223372036854775807L)) goto L5;
        return r0;
    L5:
        ExoPlayer r4 = this.player;
        if (r4 != null) goto L8;
        return 0;
    L8:
        return r4.getCurrentPosition();
    }

    public Quality getCurrentQuality() {
        int r0 = getCurrentQualityIndex();
        if (r0 >= 0) goto L5;
        return null;
    L5:
        if (r0 < getQualitiesCount()) goto L8;
        return null;
    L8:
        return getQuality(r0);
    }

    public int getCurrentQualityIndex() {
        if (this.selectedQualityIndex != (-1)) goto L38;
    L13:
        e = move-exception;
        FileLog.e(e);
        return -1;
    L5:
        if (this.autoIsOriginal == false) goto L15;
        int r0 = 0;
    L8:
        if (r0 >= getQualitiesCount()) goto L15;
        if (getQuality(r0).original == true) goto L11;
        r0 = r0 + 1;     // Catch: Exception -> L13
        goto L8
    L11:
        return r0;
    L15:
        ExoPlayer r02 = this.player;     // Catch: Exception -> L13
        if (r02 != null) goto L18;
        return -1;
    L18:
        Format r03 = r02.getVideoFormat();     // Catch: Exception -> L13
        if (r03 != null) goto L21;
        return -1;
    L21:
        int r3 = 0;
    L22:
        if (r3 >= getQualitiesCount()) goto L38;
        Quality r4 = getQuality(r3);     // Catch: Exception -> L13
        if (r4.original == true) goto L34;
        if (r03.width != r4.width) goto L34;
        if (r03.height != r4.height) goto L34;
        if (r03.bitrate != ((int) Math.floor(r4.uris.get(0).bitrate * 8.0d))) goto L34;
        return r3;
    L34:
        r3 = r3 + 1;
    L38:
        return this.selectedQualityIndex;
    }

    public Uri getCurrentUri() {
        return this.currentUri;
    }

    public long getDuration() {
        long r0 = this.fallbackDuration;
        if (r0 == (-9223372036854775807L)) goto L5;
        return r0;
    L5:
        ExoPlayer r4 = this.player;
        if (r4 != null) goto L8;
        return 0;
    L8:
        return r4.getDuration();
    }

    public StoryEntry.HDRInfo getHDRStaticInfo(StoryEntry.HDRInfo r6) {
        if (r6 != null) goto L20;
        r6 = new StoryEntry.HDRInfo();
    L20:
        MediaFormat r5 = ((MediaCodecRenderer) this.player.getRenderer(0)).getCodecOutputMediaFormat();     // Catch: Exception -> L18
        ByteBuffer r3 = r5.getByteBuffer("hdr-static-info");     // Catch: Exception -> L18
        r3.order(ByteOrder.LITTLE_ENDIAN);     // Catch: Exception -> L18
        if (r3.get() != 0) goto L9;
        r6.maxlum = r3.getShort(17);     // Catch: Exception -> L18
        r6.minlum = r3.getShort(19) * 1.0E-4f;     // Catch: Exception -> L18
    L9:
        if (r5.containsKey("color-transfer") == false) goto L12;
        r6.colorTransfer = r5.getInteger("color-transfer");     // Catch: Exception -> L18
    L12:
        if (r5.containsKey("color-standard") == false) goto L15;
        r6.colorStandard = r5.getInteger("color-standard");     // Catch: Exception -> L18
    L15:
        if (r5.containsKey("color-range") == false) goto L17;
        r6.colorRange = r5.getInteger("color-range");     // Catch: Exception -> L18
    L17:
        return r6;
    L18:
        r6.minlum = 0.0f;
        r6.maxlum = 0.0f;
        return r6;
    }

    public Quality getHighestQuality(Boolean r7) {
        Quality r0 = null;
        int r1 = 0;
    L4:
        if (r1 >= getQualitiesCount()) goto L15;
        Quality r2 = getQuality(r1);
        if (r7 != null) goto L8;
    L10:
        if (r0 != null) goto L12;
    L13:
        r0 = r2;
    L14:
        r1 = r1 + 1;
        goto L4
    L12:
        if ((r0.width * r0.height) >= (r2.width * r2.height)) goto L14;
    L8:
        if (r2.original == r7.booleanValue()) goto L10;
    L15:
        return r0;
    }

    public File getLowestFile() {
        ArrayList<Quality> r0 = this.videoQualities;
        if (r0 == null) goto L16;
        int r02 = r0.size() - 1;
    L5:
        if (r02 < 0) goto L16;
        ArrayList<VideoUri> r2 = this.videoQualities.get(r02).uris;
        int r3 = r2.size();
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L15;
        VideoUri r5 = r2.get(r4);
        r4 = r4 + 1;
        VideoUri r52 = r5;
        if (r52.isCached() == true) goto L12;
        r52.updateCached(true);
    L12:
        if (r52.isCached() == false) goto L7;
        return new File(r52.uri.getPath());
    L15:
        r02 = r02 - 1;
    L16:
        Uri r03 = this.videoUri;
        if (r03 != null) goto L19;
        return null;
    L19:
        if ("file".equalsIgnoreCase(r03.getScheme()) == true) goto L21;
        return null;
    L21:
        return new File(this.videoUri.getPath());
    }

    public Quality getOriginalQuality() {
        int r0 = 0;
    L4:
        if (r0 >= getQualitiesCount()) goto L9;
        Quality r1 = getQuality(r0);
        if (r1.original == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    public boolean getPlayWhenReady() {
        return this.player.getPlayWhenReady();
    }

    public float getPlaybackSpeed() {
        ExoPlayer r1 = this.player;
        if (r1 != null) goto L5;
        return 1.0f;
    L5:
        PlaybackParameters r12 = r1.getPlaybackParameters();
        if (r12 != null) goto L9;
        return 1.0f;
    L9:
        return r12.speed;
    }

    public int getPlaybackState() {
        return this.player.getPlaybackState();
    }

    public int getQualitiesCount() {
        ArrayList<Quality> r0 = this.videoQualities;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.size();
    }

    public Quality getQuality(int r2) {
        ArrayList<Quality> r0 = this.videoQualities;
        if (r0 == null) goto L5;
        if (r2 < 0) goto L13;
        if (r2 >= r0.size()) goto L13;
        return this.videoQualities.get(r2);
    L13:
        return getHighestQuality(Boolean.FALSE);
    L5:
        return getHighestQuality(Boolean.FALSE);
    }

    public int getRepeatCount() {
        return this.repeatCount;
    }

    public int getSelectedQuality() {
        return this.selectedQualityIndex;
    }

    public boolean hasAudioTrack() {
        if (this.audioTrackState != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isBuffering() {
        if (this.player != null) goto L5;
        return false;
    L5:
        if (this.lastReportedPlaybackState != 2) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isHDR() {
        ExoPlayer r2 = this.player;
        if (r2 != null) goto L21;
        return false;
    L21:
        Format r22 = r2.getVideoFormat();     // Catch: Exception -> L20
        if (r22 == null) goto L19;
        ColorInfo r23 = r22.colorInfo;     // Catch: Exception -> L20
        if (r23 == null) goto L19;
        int r24 = r23.colorTransfer;     // Catch: Exception -> L20
        if (r24 != 6) goto L14;
        return true;
    L14:
        if (r24 == 7) goto L23;
        return false;
    L23:
        return true;
    L19:
        return false;
    }

    public boolean isLooping() {
        return this.looping;
    }

    public boolean isMuted() {
        ExoPlayer r1 = this.player;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getVolume() != 0.0f) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPlayerPrepared() {
        if (this.player == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isPlaying() {
        if (this.mixedAudio == true) goto L5;
    L6:
        ExoPlayer r1 = this.player;
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.getPlayWhenReady() == false) goto L15;
        return true;
    L15:
        return false;
    L5:
        if (this.mixedPlayWhenReady == false) goto L6;
        return true;
    }

    public Uri makeManifest(ArrayList<Quality> r15) {
        StringBuilder r0 = new StringBuilder("#EXTM3U\n#EXT-X-VERSION:6\n#EXT-X-INDEPENDENT-SEGMENTS\n\n");
        this.manifestUris = new ArrayList();
        ArrayList r1 = new ArrayList();
        int r2 = r15.size();
        boolean r4 = false;
        int r5 = 0;
    L3:
        if (r5 >= r2) goto L21;
        Quality r6 = r15.get(r5);
        r5 = r5 + 1;
        ArrayList<VideoUri> r62 = r6.uris;
        int r7 = r62.size();
        int r8 = 0;
    L5:
        if (r8 >= r7) goto L3;
        VideoUri r9 = r62.get(r8);
        r8 = r8 + 1;
        VideoUri r92 = r9;
        this.mediaDataSourceFactory.putDocumentUri(r92.docId, r92.uri);
        this.mediaDataSourceFactory.putDocumentUri(r92.manifestDocId, r92.m3u8uri);
        if (r92.m3u8uri == null) goto L5;
        this.manifestUris.add(r92);
        StringBuilder r42 = new StringBuilder("#EXT-X-STREAM-INF:BANDWIDTH=");
        r42.append((int) Math.floor(r92.bitrate * 8.0d));
        r42.append(",RESOLUTION=");
        r42.append(r92.width);
        r42.append("x");
        r42.append(r92.height);
        String r10 = toMime(r92.codec);
        if (r10 == null) goto L12;
        r42.append(",MIME=\"");
        r42.append(r10);
        r42.append("\"");
    L12:
        if (r92.isCached() == true) goto L14;
    L16:
        r42.append(",DOCID=\"");
        r42.append(r92.docId);
        r42.append("\",ACCOUNT=\"");
        r42.append(r92.currentAccount);
        r42.append("\"\n");
        if (r92.isManifestCached() == false) goto L19;
        r42.append(r92.m3u8uri);
        r42.append("\n\n");
    L20:
        r1.add(r42.toString());
        r4 = true;
        goto L5
    L19:
        r42.append("mtproto:");
        r42.append(r92.manifestDocId);
        r42.append("\n\n");
        goto L20
    L14:
        if (r92.isManifestCached() == false) goto L16;
        r42.append(",CACHED=\"true\"");
        goto L16
    L21:
        if (r4 == true) goto L24;
        return null;
    L24:
        Collections.reverse(r1);
        r0.append(TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, r1));
        return Uri.parse("data:application/x-mpegurl;base64," + Base64.encodeToString(r0.toString().getBytes(), 2));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onCues(CueGroup r2) {
        super.onCues(r2);
        if (this.delegate == null) goto L6;
        AndroidUtilities.runOnUIThread(new VideoPlayer$$ExternalSyntheticLambda2(this, r2));
        return;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlaybackParametersChanged(PlaybackParameters r1) {
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlayerError(PlaybackException r2) {
        AndroidUtilities.runOnUIThread(new VideoPlayer$$ExternalSyntheticLambda3(this, r2));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlayerStateChanged(boolean r4, int r5) {
        maybeReportPlayerState();
        if (r4 == false) goto L13;
        if (r5 != 3) goto L13;
        if (isMuted() == true) goto L13;
        if (this.shouldPauseOther == false) goto L13;
        if (hasAudioTrack() == false) goto L13;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.playerDidStartPlaying, new Object[]{this});
    L13:
        if (this.videoPlayerReady == true) goto L16;
        if (r5 != 3) goto L16;
        this.videoPlayerReady = true;
        checkPlayersReady();
    L16:
        if (r5 == 3) goto L21;
        this.audioUpdateHandler.removeCallbacksAndMessages(null);
        AudioVisualizerDelegate r3 = this.audioVisualizerDelegate;
        if (r3 == null) goto L22;
        r3.onVisualizerUpdate(false, true, null);
        return;
    L22:
        return;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPositionDiscontinuity(Player.PositionInfo r1, Player.PositionInfo r2, int r3) {
        if (r3 != 0) goto L5;
        this.repeatCount++;
        return;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPositionDiscontinuity(AnalyticsListener.EventTime r1, Player.PositionInfo r2, Player.PositionInfo r3, int r4) {
        if (r4 != 1) goto L13;
        VideoPlayerDelegate r22 = this.delegate;
        if (r22 == null) goto L7;
        r22.onSeekFinished(r1);
    L7:
        ArrayList<Runnable> r12 = this.seekFinishedListeners;
        int r23 = r12.size();
        int r32 = 0;
    L8:
        if (r32 >= r23) goto L10;
        Runnable r42 = r12.get(r32);
        r32 = r32 + 1;
        r42.run();
        goto L8
    L10:
        this.seekFinishedListeners.clear();
        return;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onRenderedFirstFrame() {
        this.delegate.onRenderedFirstFrame();
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onRenderedFirstFrame(AnalyticsListener.EventTime r1, Object r2, long r3) {
        this.fallbackPosition = -9223372036854775807L;
        this.fallbackDuration = -9223372036854775807L;
        VideoPlayerDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onRenderedFirstFrame(r1);
        return;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onRepeatModeChanged(int r1) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onSeekStarted(AnalyticsListener.EventTime r1) {
        VideoPlayerDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onSeekStarted(r1);
        return;
    }

    @Override // androidx.media3.common.VideoListener
    public boolean onSurfaceDestroyed(SurfaceTexture r1) {
        return this.delegate.onSurfaceDestroyed(r1);
    }

    @Override // androidx.media3.common.VideoListener
    public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        this.delegate.onSurfaceTextureUpdated(r1);
    }

    @Override // androidx.media3.common.Player.Listener
    public void onTrackSelectionParametersChanged(TrackSelectionParameters r1) {
        super.onTrackSelectionParametersChanged(r1);
        Runnable r0 = this.onQualityChangeListener;
        if (r0 == null) goto L6;
        AndroidUtilities.runOnUIThread(r0);
        return;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onTracksChanged(Tracks r3) {
        super.onTracksChanged(r3);
        int r1 = 1;
        if (this.mixedAudio == false) goto L6;
        setAudioTrackState(1);
    L13:
        Runnable r2 = this.onQualityChangeListener;
        if (r2 == null) goto L17;
        AndroidUtilities.runOnUIThread(r2);
        return;
    L17:
        return;
    L6:
        if (r3.getGroups().isEmpty() == false) goto L9;
        setAudioTrackState(0);
        goto L13
    L9:
        if (r3.isTypeSelected(1) == true) goto L12;
        r1 = 2;
    L12:
        setAudioTrackState(r1);
        goto L13
    }

    @Override // androidx.media3.common.Player.Listener
    public void onVideoSizeChanged(VideoSize r6) {
        if (Objects.equals(r6, VideoSize.UNKNOWN) == true) goto L5;
        this.delegate.onVideoSizeChanged(r6.width, r6.height, r6.unappliedRotationDegrees, r6.pixelWidthHeightRatio);
    L5:
        super.onVideoSizeChanged(r6);
    }

    public void pause() {
        this.mixedPlayWhenReady = false;
        ExoPlayer r1 = this.player;
        if (r1 == null) goto L5;
        r1.setPlayWhenReady(false);
    L5:
        ExoPlayer r12 = this.audioPlayer;
        if (r12 == null) goto L9;
        r12.setPlayWhenReady(false);
    L9:
        if (this.audioVisualizerDelegate == null) goto L12;
        this.audioUpdateHandler.removeCallbacksAndMessages(null);
        this.audioVisualizerDelegate.onVisualizerUpdate(false, true, null);
        return;
    }

    public void play() {
        this.mixedPlayWhenReady = true;
        if (this.mixedAudio == true) goto L5;
    L15:
        ExoPlayer r1 = this.player;
        if (r1 == null) goto L18;
        r1.setPlayWhenReady(true);
    L18:
        ExoPlayer r2 = this.audioPlayer;
        if (r2 == null) goto L23;
        r2.setPlayWhenReady(true);
        return;
    L23:
        return;
    L5:
        if (this.audioPlayerReady == true) goto L7;
    L8:
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L11;
        r0.setPlayWhenReady(false);
    L11:
        ExoPlayer r22 = this.audioPlayer;
        if (r22 == null) goto L22;
        r22.setPlayWhenReady(false);
        return;
    L22:
        return;
    L7:
        if (this.videoPlayerReady == true) goto L15;
        goto L8
    }

    public void preparePlayer(Uri r7, String r8) {
        preparePlayer(r7, r8, 3, 0);
    }

    public void preparePlayer(Uri r9, String r10, int r11, long r12) {
        boolean r112 = false;
        setAudioTrackState(0);
        String r0 = null;
        this.videoQualities = null;
        this.videoQualityToSelect = null;
        this.videoUri = r9;
        this.videoType = r10;
        this.audioUri = null;
        this.audioType = null;
        this.currentVideoByteOffset = r12;
        this.loopingMediaSource = false;
        this.autoIsOriginal = false;
        this.currentStreamIsHls = false;
        this.videoPlayerReady = false;
        this.mixedAudio = false;
        this.currentUri = r9;
        if (r9 == null) goto L6;
        r0 = r9.getScheme();
    L6:
        if (r0 != null) goto L8;
    L10:
        this.isStreaming = r112;
        ensurePlayerCreated();
        this.player.setMediaSource(mediaSourceFromUri(r9, r12, r10, true), true);
        this.player.prepare();
        return;
    L8:
        if (r0.startsWith("file") == true) goto L10;
        r112 = true;
        goto L10
    }

    public void preparePlayer(ArrayList<Quality> r3, Quality r4) {
        setAudioTrackState(0);
        this.videoQualities = r3;
        this.videoQualityToSelect = r4;
        this.videoUri = null;
        this.videoType = "hls";
        this.audioUri = null;
        this.audioType = null;
        this.loopingMediaSource = false;
        this.autoIsOriginal = false;
        this.videoPlayerReady = false;
        this.mixedAudio = false;
        this.currentUri = null;
        this.isStreaming = true;
        ensurePlayerCreated();
        this.currentStreamIsHls = false;
        if (r4 == null) goto L8;
        ArrayList<Quality> r1 = this.videoQualities;
        if (r1 == null) goto L8;
        int r12 = r1.indexOf(r4);
    L9:
        this.selectedQualityIndex = r12;
        setSelectedQuality(true, r4);
        if (this.autoIsOriginal == false) goto L13;
        this.selectedQualityIndex = -1;
        return;
    L13:
        return;
    L8:
        r12 = -1;
        goto L9
    }

    public void preparePlayerLoop(Uri r16, String r17, Uri r18, String r19) {
        setAudioTrackState(0);
        this.videoQualities = null;
        this.videoQualityToSelect = null;
        this.videoUri = r16;
        this.audioUri = r18;
        this.videoType = r17;
        this.audioType = r19;
        this.loopingMediaSource = true;
        this.currentStreamIsHls = false;
        this.mixedAudio = true;
        this.audioPlayerReady = false;
        this.videoPlayerReady = false;
        ensurePlayerCreated();
        LoopingMediaSource r12 = null;
        LoopingMediaSource r13 = null;
        int r14 = 0;
    L4:
        if (r14 >= 2) goto L16;
        if (r14 != 0) goto L7;
        Uri r1 = r16;
        String r4 = r17;
    L8:
        if (r14 != 0) goto L10;
        boolean r5 = true;
    L11:
        LoopingMediaSource r2 = new LoopingMediaSource(mediaSourceFromUri(r1, 0, r4, r5));
        if (r14 != 0) goto L14;
        r12 = r2;
    L15:
        r14 = r14 + 1;
        goto L4
    L14:
        r13 = r2;
        goto L15
    L10:
        r5 = false;
        goto L11
    L7:
        r1 = r18;
        r4 = r19;
        goto L8
    L16:
        this.player.setMediaSource(r12, true);
        this.player.prepare();
        this.audioPlayer.setMediaSource(r13, true);
        this.audioPlayer.prepare();
        activePlayers.add(Integer.valueOf(this.playerId));
    }

    public void releasePlayer(boolean r2) {
        activePlayers.remove(Integer.valueOf(this.playerId));
        ExoPlayer r22 = this.player;
        if (r22 == null) goto L5;
        r22.release();
        this.player = null;
    L5:
        ExoPlayer r23 = this.audioPlayer;
        if (r23 == null) goto L9;
        r23.release();
        this.audioPlayer = null;
    L9:
        if (this.shouldPauseOther == false) goto L11;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.playerDidStartPlaying);
    L11:
        playerCounter--;
    }

    public void releasePlayerDeferred(long r3) {
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L5;
        this.player = null;
        r0.removeAnalyticsListener(this);
        r0.removeListener(this);
        r0.removeVideoListener(this);
        r0.stop();
        AndroidUtilities.runOnUIThread(new VideoPlayer$$ExternalSyntheticLambda0(r0), r3);
    L5:
        releasePlayer(true);
    }

    public boolean reloadCurrentSource() {
        if (this.player != null) goto L6;
        return false;
    L6:
        if (this.videoQualities == null) goto L10;
        setSelectedQuality(false, this.videoQualityToSelect);
        return true;
    L10:
        if (this.videoUri != null) goto L12;
        return false;
    L12:
        boolean r0 = getPlayWhenReady();
        long r3 = Math.max(0, getCurrentPosition());
        if (this.loopingMediaSource == true) goto L15;
    L23:
        this.player.setMediaSource(mediaSourceFromUri(this.videoUri, this.currentVideoByteOffset, this.videoType), false);
        this.player.prepare();
        if (r3 <= 0) goto L26;
        this.player.seekTo(r3);
    L26:
        setPlayWhenReady(r0);
        activePlayers.add(Integer.valueOf(this.playerId));
        return true;
    L15:
        if (this.audioUri == null) goto L23;
        if (this.audioPlayer == null) goto L23;
        LoopingMediaSource r7 = new LoopingMediaSource(mediaSourceFromUri(this.videoUri, this.currentVideoByteOffset, this.videoType));
        LoopingMediaSource r8 = new LoopingMediaSource(mediaSourceFromUri(this.audioUri, 0, this.audioType, false));
        this.player.setMediaSource(r7, false);
        this.player.prepare();
        this.audioPlayer.setMediaSource(r8, false);
        this.audioPlayer.prepare();
        if (r3 <= 0) goto L21;
        this.player.seekTo(r3);
        this.audioPlayer.seekTo(r3);
    L21:
        setPlayWhenReady(r0);
        activePlayers.add(Integer.valueOf(this.playerId));
        return true;
    }

    public void seekTo(long r2) {
        seekTo(r2, false);
    }

    public void seekTo(long r2, boolean r4) {
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L9;
        if (r4 == false) goto L6;
        SeekParameters r42 = SeekParameters.CLOSEST_SYNC;
    L7:
        r0.setSeekParameters(r42);
        this.player.seekTo(r2);
        return;
    L6:
        r42 = SeekParameters.EXACT;
        goto L7
    }

    public void seekTo(long r2, boolean r4, Runnable r5) {
        if (this.player == null) goto L12;
        if (r5 == null) goto L6;
        this.seekFinishedListeners.add(r5);
    L6:
        ExoPlayer r52 = this.player;
        if (r4 == false) goto L9;
        SeekParameters r42 = SeekParameters.CLOSEST_SYNC;
    L10:
        r52.setSeekParameters(r42);
        this.player.seekTo(r2);
        return;
    L9:
        r42 = SeekParameters.EXACT;
        goto L10
    }

    public void setAudioVisualizerDelegate(AudioVisualizerDelegate r1) {
        this.audioVisualizerDelegate = r1;
    }

    public void setDelegate(VideoPlayerDelegate r1) {
        this.delegate = r1;
    }

    public void setExternalSubtitle(ExternalSubtitle r1) {
        this.currentExternalSubtitle = r1;
    }

    public void setIsStory() {
        this.isStory = true;
    }

    public void setLooping(boolean r2) {
        if (this.looping == r2) goto L11;
        this.looping = r2;
        ExoPlayer r1 = this.player;
        if (r1 == null) goto L12;
        if (r2 == false) goto L8;
        int r22 = 2;
    L9:
        r1.setRepeatMode(r22);
        return;
    L8:
        r22 = 0;
        goto L9
    L12:
        return;
    }

    public void setMute(boolean r5) {
        ExoPlayer r0 = this.player;
        float r1 = 1.0f;
        if (r0 == null) goto L8;
        if (r5 == false) goto L6;
        float r3 = 0.0f;
    L7:
        r0.setVolume(r3);
        goto L8
    L6:
        r3 = 1.0f;
    L8:
        ExoPlayer r4 = this.audioPlayer;
        if (r4 == null) goto L14;
        if (r5 == false) goto L12;
        r1 = 0.0f;
    L12:
        r4.setVolume(r1);
        return;
    }

    public void setOnQualityChangeListener(Runnable r1) {
        this.onQualityChangeListener = r1;
    }

    public void setPlayWhenReady(boolean r2) {
        this.mixedPlayWhenReady = r2;
        if (r2 == true) goto L5;
    L17:
        this.autoplay = r2;
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L20;
        r0.setPlayWhenReady(r2);
    L20:
        ExoPlayer r1 = this.audioPlayer;
        if (r1 == null) goto L25;
        r1.setPlayWhenReady(r2);
        return;
    L25:
        return;
    L5:
        if (this.mixedAudio == false) goto L17;
        if (this.audioPlayerReady == true) goto L9;
    L10:
        ExoPlayer r22 = this.player;
        if (r22 == null) goto L13;
        r22.setPlayWhenReady(false);
    L13:
        ExoPlayer r12 = this.audioPlayer;
        if (r12 == null) goto L24;
        r12.setPlayWhenReady(false);
        return;
    L24:
        return;
    L9:
        if (this.videoPlayerReady == true) goto L17;
        goto L10
    }

    public void setPlaybackSpeed(float r4) {
        ExoPlayer r3 = this.player;     // Catch: Exception -> L9
        if (r3 == null) goto L12;
        float r1 = 1.0f;
        if (r4 <= 1.0f) goto L7;
        r1 = 0.98f;
    L7:
        r3.setPlaybackParameters(new PlaybackParameters(r4, r1));     // Catch: Exception -> L9
        return;
    L12:
        return;
    }

    public void setSelectedQuality(int r2) {
        if (this.player != null) goto L6;
        return;
    L6:
        if (r2 == this.selectedQualityIndex) goto L16;
        this.selectedQualityIndex = r2;
        ArrayList<Quality> r0 = this.videoQualities;
        if (r0 == null) goto L13;
        if (r2 < 0) goto L13;
        if (r2 >= r0.size()) goto L13;
        Quality r22 = this.videoQualities.get(r2);
    L14:
        setSelectedQuality(false, r22);
        return;
    L13:
        r22 = null;
        goto L14
    }

    public void setStreamType(int r6) {
        ExoPlayer r0 = this.player;
        int r1 = 2;
        if (r0 == null) goto L9;
        AudioAttributes.Builder r3 = new AudioAttributes.Builder();
        if (r6 != 0) goto L7;
        int r4 = 2;
    L8:
        r0.setAudioAttributes(r3.setUsage(r4).build(), this.handleAudioFocus);
        goto L9
    L7:
        r4 = 1;
    L9:
        ExoPlayer r5 = this.audioPlayer;
        if (r5 == null) goto L17;
        AudioAttributes.Builder r02 = new AudioAttributes.Builder();
        if (r6 == 0) goto L15;
        r1 = 1;
    L15:
        r5.setAudioAttributes(r02.setUsage(r1).build(), true);
        return;
    }

    public void setSurface(Surface r2) {
        if (this.surface == r2) goto L10;
        this.surface = r2;
        ExoPlayer r1 = this.player;
        if (r1 != null) goto L8;
        return;
    L8:
        r1.setVideoSurface(r2);
        return;
    }

    public void setSurfaceView(SurfaceView r2) {
        if (this.surfaceView == r2) goto L10;
        this.surfaceView = r2;
        ExoPlayer r1 = this.player;
        if (r1 != null) goto L8;
        return;
    L8:
        r1.setVideoSurfaceView(r2);
        return;
    }

    public void setTextureView(TextureView r2) {
        if (this.textureView == r2) goto L10;
        this.textureView = r2;
        ExoPlayer r1 = this.player;
        if (r1 != null) goto L8;
        return;
    L8:
        r1.setVideoTextureView(r2);
        return;
    }

    public void setVolume(float r2) {
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L5;
        r0.setVolume(r2);
    L5:
        ExoPlayer r1 = this.audioPlayer;
        if (r1 == null) goto L9;
        r1.setVolume(r2);
        return;
    }

    public void setWorkerQueue(DispatchQueue r2) {
        this.workerQueue = r2;
        ExoPlayer r1 = this.player;
        if (r2 == null) goto L6;
        r1.setWorkerQueue(new ChatThemeController$$ExternalSyntheticLambda6(r2));
        return;
    L6:
        r1.setWorkerQueue(null);
    }
}
