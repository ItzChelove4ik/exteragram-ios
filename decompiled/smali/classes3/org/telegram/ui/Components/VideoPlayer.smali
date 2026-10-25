.class public Lorg/telegram/ui/Components/VideoPlayer;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/Player$Listener;
.implements Landroidx/media3/common/VideoListener;
.implements Landroidx/media3/exoplayer/analytics/AnalyticsListener;
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "NewApi"
    }
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;,
        Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerRenderersFactory;,
        Lorg/telegram/ui/Components/VideoPlayer$Quality;,
        Lorg/telegram/ui/Components/VideoPlayer$VideoUri;,
        Lorg/telegram/ui/Components/VideoPlayer$ExternalSubtitle;,
        Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;,
        Lorg/telegram/ui/Components/VideoPlayer$OffsetDataSource;,
        Lorg/telegram/ui/Components/VideoPlayer$VisualizerBufferSink;
    }
.end annotation


# static fields
.field public static final activePlayers:Ljava/util/HashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static cachedSupportedCodec:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field

.field private static lastPlayerId:I

.field static playerCounter:I


# instance fields
.field public allowMultipleInstances:Z

.field audioDisabled:Z

.field private audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

.field private audioPlayerReady:Z

.field private volatile audioTrackState:I

.field private audioType:Ljava/lang/String;

.field audioUpdateHandler:Landroid/os/Handler;

.field private audioUri:Landroid/net/Uri;

.field private audioVisualizerDelegate:Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;

.field private autoIsOriginal:Z

.field private autoplay:Z

.field private currentExternalSubtitle:Lorg/telegram/ui/Components/VideoPlayer$ExternalSubtitle;

.field private currentStreamIsHls:Z

.field private currentUri:Landroid/net/Uri;

.field private currentVideoByteOffset:J

.field dashMediaSourceFactory:Landroidx/media3/exoplayer/source/MediaSource$Factory;

.field private delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

.field private fallbackDuration:J

.field private fallbackPosition:J

.field private handleAudioFocus:Z

.field hlsMediaSourceFactory:Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;

.field private isStory:Z

.field private isStreaming:Z

.field private lastReportedPlayWhenReady:Z

.field private lastReportedPlaybackState:I

.field private looper:Landroid/os/Looper;

.field private looping:Z

.field private loopingMediaSource:Z

.field private manifestUris:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$VideoUri;",
            ">;"
        }
    .end annotation
.end field

.field private mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

.field private mixedAudio:Z

.field private mixedPlayWhenReady:Z

.field private onQualityChangeListener:Ljava/lang/Runnable;

.field public player:Landroidx/media3/exoplayer/ExoPlayer;

.field public final playerId:I

.field progressiveMediaSourceFactory:Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;

.field private repeatCount:I

.field private final seekFinishedListeners:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Runnable;",
            ">;"
        }
    .end annotation
.end field

.field private selectedQualityIndex:I

.field private shouldPauseOther:Z

.field private surface:Landroid/view/Surface;

.field private surfaceView:Landroid/view/SurfaceView;

.field private textureView:Landroid/view/TextureView;

.field private trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

.field private triedReinit:Z

.field private videoPlayerReady:Z

.field private videoQualities:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;"
        }
    .end annotation
.end field

.field private videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

.field private videoType:Ljava/lang/String;

.field private videoUri:Landroid/net/Uri;

.field private workerQueue:Lorg/telegram/messenger/DispatchQueue;


# direct methods
.method public static synthetic $r8$lambda$7rCbITW00VAJMikLErzek1ELoHs(Lorg/telegram/ui/Components/VideoPlayer;Landroidx/media3/common/PlaybackException;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->lambda$onPlayerError$3(Landroidx/media3/common/PlaybackException;)V

    return-void
.end method

.method public static synthetic $r8$lambda$jWh9mJDcbrkxazS0DTsXGT26cNw(Lorg/telegram/ui/Components/VideoPlayer;J)Landroidx/media3/datasource/DataSource;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Components/VideoPlayer;->lambda$mediaSourceFromUri$0(J)Landroidx/media3/datasource/DataSource;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$l-WcqUYvWpJVWuzrcGpf3lI_qL8(Lorg/telegram/ui/Components/VideoPlayer;Landroidx/media3/common/text/CueGroup;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->lambda$onCues$1(Landroidx/media3/common/text/CueGroup;)V

    return-void
.end method

.method public static synthetic $r8$lambda$xsEBWC2rRnwEncfRebHEd4DD2Ek(Lorg/telegram/ui/Components/VideoPlayer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/Components/VideoPlayer;->lambda$onPlayerError$2()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetaudioPlayerReady(Lorg/telegram/ui/Components/VideoPlayer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayerReady:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetaudioVisualizerDelegate(Lorg/telegram/ui/Components/VideoPlayer;)Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioVisualizerDelegate:Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmixedPlayWhenReady(Lorg/telegram/ui/Components/VideoPlayer;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedPlayWhenReady:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fputaudioPlayerReady(Lorg/telegram/ui/Components/VideoPlayer;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayerReady:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcheckPlayersReady(Lorg/telegram/ui/Components/VideoPlayer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/ui/Components/VideoPlayer;->checkPlayersReady()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 124
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    sput-object v0, Lorg/telegram/ui/Components/VideoPlayer;->activePlayers:Ljava/util/HashSet;

    const/4 v0, 0x0

    .line 231
    sput v0, Lorg/telegram/ui/Components/VideoPlayer;->playerCounter:I

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 228
    invoke-direct {p0, v0, v1}, Lorg/telegram/ui/Components/VideoPlayer;-><init>(ZZ)V

    return-void
.end method

.method public constructor <init>(ZZ)V
    .locals 3

    .line 232
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 123
    sget v0, Lorg/telegram/ui/Components/VideoPlayer;->lastPlayerId:I

    add-int/lit8 v1, v0, 0x1

    sput v1, Lorg/telegram/ui/Components/VideoPlayer;->lastPlayerId:I

    iput v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->playerId:I

    const/4 v0, 0x0

    .line 205
    iput v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioTrackState:I

    .line 223
    new-instance v1, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUpdateHandler:Landroid/os/Handler;

    .line 528
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    const/4 v1, -0x1

    .line 529
    iput v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->selectedQualityIndex:I

    const-wide v1, -0x7fffffffffffffffL    # -4.9E-324

    .line 764
    iput-wide v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->fallbackDuration:J

    .line 765
    iput-wide v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->fallbackPosition:J

    .line 1480
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->seekFinishedListeners:Ljava/util/ArrayList;

    .line 1762
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->handleAudioFocus:Z

    .line 233
    iput-boolean p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioDisabled:Z

    .line 234
    new-instance v0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v2, "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20150101 Firefox/47.0 (Chrome)"

    invoke-direct {v0, v1, v2}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    .line 235
    new-instance v0, Landroidx/media3/exoplayer/trackselection/DefaultTrackSelector;

    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    new-instance v2, Landroidx/media3/exoplayer/trackselection/AdaptiveTrackSelection$Factory;

    invoke-direct {v2}, Landroidx/media3/exoplayer/trackselection/AdaptiveTrackSelection$Factory;-><init>()V

    invoke-direct {v0, v1, v2}, Landroidx/media3/exoplayer/trackselection/DefaultTrackSelector;-><init>(Landroid/content/Context;Landroidx/media3/exoplayer/trackselection/ExoTrackSelection$Factory;)V

    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

    const/4 v1, 0x1

    if-eqz p2, :cond_0

    .line 237
    invoke-virtual {v0}, Landroidx/media3/exoplayer/trackselection/TrackSelector;->getParameters()Landroidx/media3/common/TrackSelectionParameters;

    move-result-object p2

    invoke-virtual {p2}, Landroidx/media3/common/TrackSelectionParameters;->buildUpon()Landroidx/media3/common/TrackSelectionParameters$Builder;

    move-result-object p2

    invoke-virtual {p2, v1, v1}, Landroidx/media3/common/TrackSelectionParameters$Builder;->setTrackTypeDisabled(IZ)Landroidx/media3/common/TrackSelectionParameters$Builder;

    move-result-object p2

    invoke-virtual {p2}, Landroidx/media3/common/TrackSelectionParameters$Builder;->build()Landroidx/media3/common/TrackSelectionParameters;

    move-result-object p2

    invoke-virtual {v0, p2}, Landroidx/media3/exoplayer/trackselection/TrackSelector;->setParameters(Landroidx/media3/common/TrackSelectionParameters;)V

    .line 239
    :cond_0
    iput v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->lastReportedPlaybackState:I

    .line 240
    iput-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->shouldPauseOther:Z

    if-eqz p1, :cond_1

    .line 242
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->playerDidStartPlaying:I

    invoke-virtual {p1, p0, p2}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 244
    :cond_1
    sget p0, Lorg/telegram/ui/Components/VideoPlayer;->playerCounter:I

    add-int/2addr p0, v1

    sput p0, Lorg/telegram/ui/Components/VideoPlayer;->playerCounter:I

    return-void
.end method

.method private checkPlayersReady()V
    .locals 1

    .line 1797
    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayerReady:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoPlayerReady:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedPlayWhenReady:Z

    if-eqz v0, :cond_0

    .line 1798
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->play()V

    :cond_0
    return-void
.end method

.method private ensurePlayerCreated()V
    .locals 5

    .line 263
    new-instance v0, Landroidx/media3/exoplayer/DefaultLoadControl$Builder;

    invoke-direct {v0}, Landroidx/media3/exoplayer/DefaultLoadControl$Builder;-><init>()V

    new-instance v1, Landroidx/media3/exoplayer/upstream/DefaultAllocator;

    const/high16 v2, 0x10000

    const/4 v3, 0x1

    invoke-direct {v1, v3, v2}, Landroidx/media3/exoplayer/upstream/DefaultAllocator;-><init>(ZI)V

    .line 264
    invoke-virtual {v0, v1}, Landroidx/media3/exoplayer/DefaultLoadControl$Builder;->setAllocator(Landroidx/media3/exoplayer/upstream/DefaultAllocator;)Landroidx/media3/exoplayer/DefaultLoadControl$Builder;

    move-result-object v0

    .line 268
    iget-boolean v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->isStory:Z

    const/16 v2, 0x3e8

    if-eqz v1, :cond_0

    move v4, v2

    goto :goto_0

    :cond_0
    const/16 v4, 0x64

    :goto_0
    if-eqz v1, :cond_1

    goto :goto_1

    :cond_1
    const/16 v2, 0x7d0

    :goto_1
    const v1, 0xc350

    .line 265
    invoke-virtual {v0, v1, v1, v4, v2}, Landroidx/media3/exoplayer/DefaultLoadControl$Builder;->setBufferDurationsMs(IIII)Landroidx/media3/exoplayer/DefaultLoadControl$Builder;

    move-result-object v0

    const/4 v1, -0x1

    .line 271
    invoke-virtual {v0, v1}, Landroidx/media3/exoplayer/DefaultLoadControl$Builder;->setTargetBufferBytes(I)Landroidx/media3/exoplayer/DefaultLoadControl$Builder;

    move-result-object v0

    const/4 v1, 0x0

    .line 272
    invoke-virtual {v0, v1}, Landroidx/media3/exoplayer/DefaultLoadControl$Builder;->setPrioritizeTimeOverSizeThresholds(Z)Landroidx/media3/exoplayer/DefaultLoadControl$Builder;

    move-result-object v0

    .line 274
    invoke-virtual {v0, v1, v1}, Landroidx/media3/exoplayer/DefaultLoadControl$Builder;->setBackBuffer(IZ)Landroidx/media3/exoplayer/DefaultLoadControl$Builder;

    move-result-object v0

    .line 277
    invoke-virtual {v0}, Landroidx/media3/exoplayer/DefaultLoadControl$Builder;->build()Landroidx/media3/exoplayer/DefaultLoadControl;

    move-result-object v0

    .line 279
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v2, :cond_8

    .line 281
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioVisualizerDelegate:Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;

    if-eqz v2, :cond_2

    .line 282
    new-instance v2, Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerRenderersFactory;

    sget-object v4, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-direct {v2, p0, v4}, Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerRenderersFactory;-><init>(Lorg/telegram/ui/Components/VideoPlayer;Landroid/content/Context;)V

    goto :goto_2

    .line 284
    :cond_2
    new-instance v2, Landroidx/media3/exoplayer/DefaultRenderersFactory;

    sget-object v4, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-direct {v2, v4}, Landroidx/media3/exoplayer/DefaultRenderersFactory;-><init>(Landroid/content/Context;)V

    .line 286
    :goto_2
    invoke-virtual {v2, v3}, Landroidx/media3/exoplayer/DefaultRenderersFactory;->setExtensionRendererMode(I)Landroidx/media3/exoplayer/DefaultRenderersFactory;

    .line 288
    new-instance v3, Landroidx/media3/exoplayer/ExoPlayer$Builder;

    sget-object v4, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-direct {v3, v4}, Landroidx/media3/exoplayer/ExoPlayer$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v3, v2}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->setRenderersFactory(Landroidx/media3/exoplayer/RenderersFactory;)Landroidx/media3/exoplayer/ExoPlayer$Builder;

    move-result-object v2

    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

    .line 289
    invoke-virtual {v2, v3}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->setTrackSelector(Landroidx/media3/exoplayer/trackselection/TrackSelector;)Landroidx/media3/exoplayer/ExoPlayer$Builder;

    move-result-object v2

    .line 290
    invoke-virtual {v2, v0}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->setLoadControl(Landroidx/media3/exoplayer/LoadControl;)Landroidx/media3/exoplayer/ExoPlayer$Builder;

    move-result-object v2

    .line 291
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->looper:Landroid/os/Looper;

    if-eqz v3, :cond_3

    .line 292
    invoke-virtual {v2, v3}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->setLooper(Landroid/os/Looper;)Landroidx/media3/exoplayer/ExoPlayer$Builder;

    .line 294
    :cond_3
    invoke-virtual {v2}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->build()Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object v2

    iput-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    .line 296
    invoke-interface {v2, p0}, Landroidx/media3/exoplayer/ExoPlayer;->addAnalyticsListener(Landroidx/media3/exoplayer/analytics/AnalyticsListener;)V

    .line 297
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v2, p0}, Landroidx/media3/common/Player;->addListener(Landroidx/media3/common/Player$Listener;)V

    .line 298
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v2, p0}, Landroidx/media3/exoplayer/ExoPlayer;->addVideoListener(Landroidx/media3/common/VideoListener;)V

    .line 299
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    if-eqz v2, :cond_4

    .line 300
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v3, v2}, Landroidx/media3/common/Player;->setVideoTextureView(Landroid/view/TextureView;)V

    goto :goto_3

    .line 301
    :cond_4
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->surface:Landroid/view/Surface;

    if-eqz v2, :cond_5

    .line 302
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v3, v2}, Landroidx/media3/common/Player;->setVideoSurface(Landroid/view/Surface;)V

    goto :goto_3

    .line 303
    :cond_5
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->surfaceView:Landroid/view/SurfaceView;

    if-eqz v2, :cond_6

    .line 304
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v3, v2}, Landroidx/media3/common/Player;->setVideoSurfaceView(Landroid/view/SurfaceView;)V

    .line 306
    :cond_6
    :goto_3
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-boolean v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoplay:Z

    invoke-interface {v2, v3}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    .line 307
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-boolean v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->looping:Z

    if-eqz v3, :cond_7

    const/4 v1, 0x2

    :cond_7
    invoke-interface {v2, v1}, Landroidx/media3/common/Player;->setRepeatMode(I)V

    .line 309
    :cond_8
    iget-boolean v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedAudio:Z

    if-eqz v1, :cond_9

    .line 310
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v1, :cond_9

    .line 311
    new-instance v1, Landroidx/media3/exoplayer/ExoPlayer$Builder;

    sget-object v2, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-direct {v1, v2}, Landroidx/media3/exoplayer/ExoPlayer$Builder;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

    .line 312
    invoke-virtual {v1, v2}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->setTrackSelector(Landroidx/media3/exoplayer/trackselection/TrackSelector;)Landroidx/media3/exoplayer/ExoPlayer$Builder;

    move-result-object v1

    .line 313
    invoke-virtual {v1, v0}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->setLoadControl(Landroidx/media3/exoplayer/LoadControl;)Landroidx/media3/exoplayer/ExoPlayer$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->build()Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    .line 314
    new-instance v1, Lorg/telegram/ui/Components/VideoPlayer$1;

    invoke-direct {v1, p0}, Lorg/telegram/ui/Components/VideoPlayer$1;-><init>(Lorg/telegram/ui/Components/VideoPlayer;)V

    invoke-interface {v0, v1}, Landroidx/media3/common/Player;->addListener(Landroidx/media3/common/Player$Listener;)V

    .line 324
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    iget-boolean p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoplay:Z

    invoke-interface {v0, p0}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    :cond_9
    return-void
.end method

.method public static getCachedQuality(Ljava/util/ArrayList;)Lorg/telegram/ui/Components/VideoPlayer$VideoUri;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;)",
            "Lorg/telegram/ui/Components/VideoPlayer$VideoUri;"
        }
    .end annotation

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 1045
    :cond_0
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    move v3, v2

    :cond_1
    if-ge v3, v1, :cond_3

    invoke-virtual {p0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 1046
    iget-object v4, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v5

    move v6, v2

    :cond_2
    if-ge v6, v5, :cond_1

    invoke-virtual {v4, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v6, v6, 0x1

    check-cast v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 1047
    invoke-virtual {v7}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isCached()Z

    move-result v8

    if-eqz v8, :cond_2

    return-object v7

    :cond_3
    return-object v0
.end method

.method public static getLooping(Lorg/telegram/messenger/MessageObject;)Ljava/lang/Boolean;
    .locals 6

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 521
    :cond_0
    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v2, "media_saved_pos"

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v1

    .line 522
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v4

    invoke-virtual {v2, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v4, "_"

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result p0

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, "loop"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 523
    invoke-interface {v1, p0}, Landroid/content/SharedPreferences;->contains(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_1

    return-object v0

    .line 524
    :cond_1
    invoke-interface {v1, p0, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method

.method public static getQualities(ILorg/telegram/tgnet/TLRPC$Document;Ljava/util/ArrayList;IZ)Ljava/util/ArrayList;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Lorg/telegram/tgnet/TLRPC$Document;",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/tgnet/TLRPC$Document;",
            ">;IZ)",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;"
        }
    .end annotation

    const/4 v5, 0x1

    move v0, p0

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    move v4, p4

    .line 917
    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/Components/VideoPlayer;->getQualities(ILorg/telegram/tgnet/TLRPC$Document;Ljava/util/ArrayList;IZZ)Ljava/util/ArrayList;

    move-result-object p0

    return-object p0
.end method

.method public static getQualities(ILorg/telegram/tgnet/TLRPC$Document;Ljava/util/ArrayList;IZZ)Ljava/util/ArrayList;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Lorg/telegram/tgnet/TLRPC$Document;",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/tgnet/TLRPC$Document;",
            ">;IZZ)",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;"
        }
    .end annotation

    .line 920
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    if-eqz p1, :cond_0

    .line 922
    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 924
    :cond_0
    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    iget-boolean v1, v1, Lorg/telegram/messenger/MessagesController;->videoIgnoreAltDocuments:Z

    if-nez v1, :cond_1

    if-eqz p2, :cond_1

    .line 925
    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 928
    :cond_1
    new-instance p2, Landroid/util/LongSparseArray;

    invoke-direct {p2}, Landroid/util/LongSparseArray;-><init>()V

    const/4 v1, 0x0

    move v2, v1

    .line 929
    :goto_0
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    const-string v4, "application/x-mpegurl"

    const/4 v5, 0x1

    if-ge v2, v3, :cond_4

    .line 930
    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/tgnet/TLRPC$Document;

    .line 931
    iget-object v6, v3, Lorg/telegram/tgnet/TLRPC$Document;->mime_type:Ljava/lang/String;

    invoke-virtual {v4, v6}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 932
    iget-object v4, v3, Lorg/telegram/tgnet/TLRPC$Document;->file_name_fixed:Ljava/lang/String;

    if-eqz v4, :cond_3

    const-string/jumbo v6, "mtproto"

    invoke-virtual {v4, v6}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v4

    if-nez v4, :cond_2

    goto :goto_1

    .line 934
    :cond_2
    :try_start_0
    iget-object v4, v3, Lorg/telegram/tgnet/TLRPC$Document;->file_name_fixed:Ljava/lang/String;

    const/4 v6, 0x7

    invoke-virtual {v4, v6}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v6

    .line 935
    invoke-virtual {p2, v6, v7, v3}, Landroid/util/LongSparseArray;->put(JLjava/lang/Object;)V

    .line 936
    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    add-int/lit8 v2, v2, -0x1

    goto :goto_1

    :catch_0
    move-exception v3

    .line 939
    invoke-static {v3}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_3
    :goto_1
    add-int/2addr v2, v5

    goto :goto_0

    .line 944
    :cond_4
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    move v3, v1

    .line 945
    :goto_2
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v6

    if-ge v3, v6, :cond_9

    .line 947
    :try_start_1
    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lorg/telegram/tgnet/TLRPC$Document;

    .line 948
    iget-object v7, v6, Lorg/telegram/tgnet/TLRPC$Document;->mime_type:Ljava/lang/String;

    .line 949
    invoke-virtual {v4, v7}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v7

    if-nez v7, :cond_8

    const-string v7, "application/x-tgstoryboard"

    iget-object v8, v6, Lorg/telegram/tgnet/TLRPC$Document;->mime_type:Ljava/lang/String;

    .line 950
    invoke-virtual {v7, v8}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v7

    if-nez v7, :cond_8

    const-string v7, "application/x-tgstoryboardmap"

    iget-object v8, v6, Lorg/telegram/tgnet/TLRPC$Document;->mime_type:Ljava/lang/String;

    .line 951
    invoke-virtual {v7, v8}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v7

    if-eqz v7, :cond_5

    goto :goto_5

    .line 955
    :cond_5
    iget-wide v7, v6, Lorg/telegram/tgnet/TLRPC$Document;->id:J

    invoke-virtual {p2, v7, v8}, Landroid/util/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lorg/telegram/tgnet/TLRPC$Document;

    invoke-static {p0, v6, v7, p3, p5}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->of(ILorg/telegram/tgnet/TLRPC$Document;Lorg/telegram/tgnet/TLRPC$Document;IZ)Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    move-result-object v7

    .line 956
    iget v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    if-lez v8, :cond_8

    iget v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    if-gtz v8, :cond_6

    goto :goto_5

    :cond_6
    if-ne v6, p1, :cond_7

    .line 960
    iput-boolean v5, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->original:Z

    goto :goto_3

    :catch_1
    move-exception v6

    goto :goto_4

    .line 962
    :cond_7
    :goto_3
    invoke-virtual {v2, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_5

    .line 964
    :goto_4
    invoke-static {v6}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_8
    :goto_5
    add-int/lit8 v3, v3, 0x1

    goto :goto_2

    .line 968
    :cond_9
    new-instance p0, Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    .line 969
    :goto_6
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result p1

    if-ge v1, p1, :cond_f

    .line 970
    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 971
    iget-object p2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    if-eqz p2, :cond_d

    .line 972
    const-string p3, "av01"

    const-string p5, "av1"

    const-string/jumbo v0, "vp9"

    if-eqz p4, :cond_b

    .line 973
    const-string v3, "avc"

    invoke-virtual {v3, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_d

    const-string p2, "h264"

    iget-object v3, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {p2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_d

    iget-object p2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {v0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_d

    const-string/jumbo p2, "vp8"

    iget-object v0, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {p2, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_d

    iget-object p2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {p5, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_a

    iget-object p2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {p3, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_e

    :cond_a
    iget-object p2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-static {p2}, Lorg/telegram/ui/Components/VideoPlayer;->supportsHardwareDecoder(Ljava/lang/String;)Z

    move-result p2

    if-nez p2, :cond_d

    goto :goto_7

    .line 977
    :cond_b
    invoke-virtual {p5, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_c

    iget-object p2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {p3, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_c

    const-string p2, "hevc"

    iget-object p3, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_c

    const-string p2, "h265"

    iget-object p3, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_c

    iget-object p2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-virtual {v0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_d

    :cond_c
    iget-object p2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-static {p2}, Lorg/telegram/ui/Components/VideoPlayer;->supportsHardwareDecoder(Ljava/lang/String;)Z

    move-result p2

    if-nez p2, :cond_d

    goto :goto_7

    .line 982
    :cond_d
    invoke-virtual {p0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_e
    :goto_7
    add-int/lit8 v1, v1, 0x1

    goto/16 :goto_6

    .line 985
    :cond_f
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    .line 986
    invoke-virtual {p0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p2

    if-eqz p2, :cond_10

    .line 987
    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    goto :goto_8

    .line 989
    :cond_10
    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 991
    :goto_8
    invoke-static {p1}, Lorg/telegram/ui/Components/VideoPlayer$Quality;->group(Ljava/util/ArrayList;)Ljava/util/ArrayList;

    move-result-object p0

    return-object p0
.end method

.method public static getQualities(ILorg/telegram/tgnet/TLRPC$MessageMedia;Z)Ljava/util/ArrayList;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Lorg/telegram/tgnet/TLRPC$MessageMedia;",
            "Z)",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;"
        }
    .end annotation

    .line 995
    instance-of v0, p1, Lorg/telegram/tgnet/TLRPC$TL_messageMediaDocument;

    if-nez v0, :cond_0

    .line 996
    new-instance p0, Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    return-object p0

    .line 997
    :cond_0
    iget-object v1, p1, Lorg/telegram/tgnet/TLRPC$MessageMedia;->document:Lorg/telegram/tgnet/TLRPC$Document;

    iget-object v2, p1, Lorg/telegram/tgnet/TLRPC$MessageMedia;->alt_documents:Ljava/util/ArrayList;

    const/4 v3, 0x0

    const/4 v4, 0x0

    move v0, p0

    move v5, p2

    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/Components/VideoPlayer;->getQualities(ILorg/telegram/tgnet/TLRPC$Document;Ljava/util/ArrayList;IZZ)Ljava/util/ArrayList;

    move-result-object p0

    return-object p0
.end method

.method public static getQualityForPlayer(Ljava/util/ArrayList;)Lorg/telegram/ui/Components/VideoPlayer$VideoUri;
    .locals 14
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;)",
            "Lorg/telegram/ui/Components/VideoPlayer$VideoUri;"
        }
    .end annotation

    .line 1053
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    move v2, v1

    :cond_0
    if-ge v2, v0, :cond_2

    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    check-cast v3, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 1054
    iget-object v3, v3, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v4

    move v5, v1

    :cond_1
    if-ge v5, v4, :cond_0

    invoke-virtual {v3, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    check-cast v6, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 1055
    iget-boolean v7, v6, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->original:Z

    if-eqz v7, :cond_1

    invoke-virtual {v6}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isCached()Z

    move-result v7

    if-eqz v7, :cond_1

    return-object v6

    .line 1062
    :cond_2
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v2, 0x0

    move v3, v1

    :cond_3
    if-ge v3, v0, :cond_6

    invoke-virtual {p0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 1063
    iget-object v4, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v5

    move v6, v1

    :cond_4
    :goto_0
    if-ge v6, v5, :cond_3

    invoke-virtual {v4, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v6, v6, 0x1

    check-cast v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 1064
    iget-boolean v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->original:Z

    if-nez v8, :cond_4

    iget-object v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-static {v8}, Lorg/telegram/ui/Components/VideoPlayer;->supportsHardwareDecoder(Ljava/lang/String;)Z

    move-result v8

    if-eqz v8, :cond_4

    if-eqz v2, :cond_5

    iget v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v9, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    mul-int v10, v8, v9

    iget v11, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v12, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    mul-int v13, v11, v12

    if-gt v10, v13, :cond_5

    mul-int/2addr v8, v9

    mul-int/2addr v11, v12

    if-ne v8, v11, :cond_4

    iget-wide v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    iget-wide v10, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    cmpg-double v8, v8, v10

    if-gez v8, :cond_4

    :cond_5
    move-object v2, v7

    goto :goto_0

    :cond_6
    if-nez v2, :cond_a

    .line 1071
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    move v3, v1

    :cond_7
    if-ge v3, v0, :cond_a

    invoke-virtual {p0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 1072
    iget-object v4, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v5

    move v6, v1

    :cond_8
    :goto_1
    if-ge v6, v5, :cond_7

    invoke-virtual {v4, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v6, v6, 0x1

    check-cast v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    if-eqz v2, :cond_9

    .line 1073
    iget v8, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v9, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    mul-int/2addr v8, v9

    iget v9, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v10, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    mul-int/2addr v9, v10

    if-gt v8, v9, :cond_9

    iget-wide v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    iget-wide v10, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    cmpg-double v8, v8, v10

    if-gez v8, :cond_8

    :cond_9
    move-object v2, v7

    goto :goto_1

    :cond_a
    return-object v2
.end method

.method public static getQualityForThumb(Ljava/util/ArrayList;)Lorg/telegram/ui/Components/VideoPlayer$VideoUri;
    .locals 12
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;)",
            "Lorg/telegram/ui/Components/VideoPlayer$VideoUri;"
        }
    .end annotation

    .line 1015
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    move v2, v1

    :cond_0
    if-ge v2, v0, :cond_2

    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    check-cast v3, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 1016
    iget-object v3, v3, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v4

    move v5, v1

    :cond_1
    if-ge v5, v4, :cond_0

    invoke-virtual {v3, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    check-cast v6, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 1017
    invoke-virtual {v6}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isCached()Z

    move-result v7

    if-eqz v7, :cond_1

    return-object v6

    .line 1024
    :cond_2
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v2, 0x0

    move v3, v1

    :cond_3
    if-ge v3, v0, :cond_6

    invoke-virtual {p0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 1025
    iget-object v4, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v5

    move v6, v1

    :cond_4
    :goto_0
    if-ge v6, v5, :cond_3

    invoke-virtual {v4, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v6, v6, 0x1

    check-cast v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 1026
    iget-boolean v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->original:Z

    if-nez v8, :cond_4

    if-eqz v2, :cond_5

    iget v8, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v9, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    mul-int/2addr v8, v9

    iget v9, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v10, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    mul-int/2addr v9, v10

    if-gt v8, v9, :cond_5

    iget-wide v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    iget-wide v10, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    cmpg-double v8, v8, v10

    if-gez v8, :cond_4

    :cond_5
    iget v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    const/16 v9, 0x384

    if-gt v8, v9, :cond_4

    iget v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    if-gt v8, v9, :cond_4

    move-object v2, v7

    goto :goto_0

    :cond_6
    if-nez v2, :cond_a

    .line 1032
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    move v3, v1

    :cond_7
    if-ge v3, v0, :cond_a

    invoke-virtual {p0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v3, v3, 0x1

    check-cast v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 1033
    iget-object v4, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v5

    move v6, v1

    :cond_8
    :goto_1
    if-ge v6, v5, :cond_7

    invoke-virtual {v4, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v6, v6, 0x1

    check-cast v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    if-eqz v2, :cond_9

    .line 1034
    iget v8, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v9, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    mul-int/2addr v8, v9

    iget v9, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v10, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    mul-int/2addr v9, v10

    if-gt v8, v9, :cond_9

    iget-wide v8, v7, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    iget-wide v10, v2, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    cmpg-double v8, v8, v10

    if-gez v8, :cond_8

    :cond_9
    move-object v2, v7

    goto :goto_1

    :cond_a
    return-object v2
.end method

.method private getQualityTrackSelection(Lorg/telegram/ui/Components/VideoPlayer$VideoUri;)Landroidx/media3/common/TrackSelectionOverride;
    .locals 10

    .line 712
    :try_start_0
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->manifestUris:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result v0

    .line 713
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

    invoke-virtual {p0}, Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;->getCurrentMappedTrackInfo()Landroidx/media3/exoplayer/trackselection/MappingTrackSelector$MappedTrackInfo;

    move-result-object p0

    const/4 v1, 0x0

    move v2, v1

    .line 714
    :goto_0
    invoke-virtual {p0}, Landroidx/media3/exoplayer/trackselection/MappingTrackSelector$MappedTrackInfo;->getRendererCount()I

    move-result v3

    if-ge v2, v3, :cond_4

    .line 715
    invoke-virtual {p0, v2}, Landroidx/media3/exoplayer/trackselection/MappingTrackSelector$MappedTrackInfo;->getTrackGroups(I)Landroidx/media3/exoplayer/source/TrackGroupArray;

    move-result-object v3

    move v4, v1

    .line 716
    :goto_1
    iget v5, v3, Landroidx/media3/exoplayer/source/TrackGroupArray;->length:I

    if-ge v4, v5, :cond_3

    .line 717
    invoke-virtual {v3, v4}, Landroidx/media3/exoplayer/source/TrackGroupArray;->get(I)Landroidx/media3/common/TrackGroup;

    move-result-object v5

    move v6, v1

    .line 718
    :goto_2
    iget v7, v5, Landroidx/media3/common/TrackGroup;->length:I

    if-ge v6, v7, :cond_2

    .line 719
    invoke-virtual {v5, v6}, Landroidx/media3/common/TrackGroup;->getFormat(I)Landroidx/media3/common/Format;

    move-result-object v7
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    .line 723
    :try_start_1
    iget-object v8, v7, Landroidx/media3/common/Format;->id:Ljava/lang/String;

    invoke-static {v8}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v8
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_3

    :catch_0
    const/4 v8, -0x1

    :goto_3
    if-ltz v8, :cond_0

    if-ne v0, v8, :cond_0

    .line 729
    :try_start_2
    new-instance p0, Landroidx/media3/common/TrackSelectionOverride;

    invoke-direct {p0, v5, v6}, Landroidx/media3/common/TrackSelectionOverride;-><init>(Landroidx/media3/common/TrackGroup;I)V

    return-object p0

    .line 732
    :cond_0
    iget v8, v7, Landroidx/media3/common/Format;->width:I

    iget v9, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    if-ne v8, v9, :cond_1

    iget v7, v7, Landroidx/media3/common/Format;->height:I

    iget v8, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    if-ne v7, v8, :cond_1

    .line 733
    new-instance p0, Landroidx/media3/common/TrackSelectionOverride;

    invoke-direct {p0, v5, v6}, Landroidx/media3/common/TrackSelectionOverride;-><init>(Landroidx/media3/common/TrackGroup;I)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    return-object p0

    :cond_1
    add-int/lit8 v6, v6, 0x1

    goto :goto_2

    :cond_2
    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    :cond_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :catch_1
    move-exception p0

    .line 739
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_4
    const/4 p0, 0x0

    return-object p0
.end method

.method public static getSavedQuality(Ljava/util/ArrayList;JI)Lorg/telegram/ui/Components/VideoPlayer$Quality;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;JI)",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;"
        }
    .end annotation

    .line 486
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v1, "media_saved_pos"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 487
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, "_"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string/jumbo p1, "q2"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, ""

    invoke-interface {v0, p1, p2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 488
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p3

    const/4 v0, 0x0

    if-eqz p3, :cond_0

    return-object v0

    .line 489
    :cond_0
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result p3

    :cond_1
    if-ge v2, p3, :cond_3

    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    add-int/lit8 v2, v2, 0x1

    check-cast v1, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 490
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    iget v4, v1, Lorg/telegram/ui/Components/VideoPlayer$Quality;->width:I

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string/jumbo v4, "x"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v4, v1, Lorg/telegram/ui/Components/VideoPlayer$Quality;->height:I

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    iget-boolean v4, v1, Lorg/telegram/ui/Components/VideoPlayer$Quality;->original:Z

    if-eqz v4, :cond_2

    const-string/jumbo v4, "s"

    goto :goto_0

    :cond_2
    move-object v4, p2

    :goto_0
    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    .line 491
    invoke-static {p1, v3}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_1

    return-object v1

    :cond_3
    return-object v0
.end method

.method public static getSavedQuality(Ljava/util/ArrayList;Lorg/telegram/messenger/MessageObject;)Lorg/telegram/ui/Components/VideoPlayer$Quality;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;",
            "Lorg/telegram/messenger/MessageObject;",
            ")",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;"
        }
    .end annotation

    .line 474
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferOriginalQuality()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 475
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    :cond_0
    if-ge v1, v0, :cond_1

    invoke-virtual {p0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1

    check-cast v2, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 476
    iget-boolean v3, v2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->original:Z

    if-eqz v3, :cond_0

    return-object v2

    :cond_1
    if-nez p1, :cond_2

    const/4 p0, 0x0

    return-object p0

    .line 482
    :cond_2
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v0

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result p1

    invoke-static {p0, v0, v1, p1}, Lorg/telegram/ui/Components/VideoPlayer;->getSavedQuality(Ljava/util/ArrayList;JI)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object p0

    return-object p0
.end method

.method private synthetic lambda$mediaSourceFromUri$0(J)Landroidx/media3/datasource/DataSource;
    .locals 1

    .line 384
    new-instance v0, Lorg/telegram/ui/Components/VideoPlayer$OffsetDataSource;

    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    invoke-virtual {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->createDataSource()Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;

    move-result-object p0

    invoke-direct {v0, p0, p1, p2}, Lorg/telegram/ui/Components/VideoPlayer$OffsetDataSource;-><init>(Landroidx/media3/datasource/DataSource;J)V

    return-object v0
.end method

.method private synthetic lambda$onCues$1(Landroidx/media3/common/text/CueGroup;)V
    .locals 0

    .line 757
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    if-eqz p0, :cond_0

    .line 758
    invoke-interface {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onCues(Landroidx/media3/common/text/CueGroup;)V

    :cond_0
    return-void
.end method

.method private synthetic lambda$onPlayerError$2()V
    .locals 4

    .line 1857
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_2

    .line 1858
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    invoke-interface {v0, v1}, Landroidx/media3/common/Player;->clearVideoTextureView(Landroid/view/TextureView;)V

    .line 1859
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    invoke-interface {v0, v1}, Landroidx/media3/common/Player;->setVideoTextureView(Landroid/view/TextureView;)V

    .line 1860
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    .line 1861
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/VideoPlayer;->preparePlayer(Ljava/util/ArrayList;Lorg/telegram/ui/Components/VideoPlayer$Quality;)V

    goto :goto_0

    .line 1862
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->loopingMediaSource:Z

    .line 1865
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    if-eqz v0, :cond_1

    .line 1863
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUri:Landroid/net/Uri;

    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioType:Ljava/lang/String;

    invoke-virtual {p0, v1, v0, v2, v3}, Lorg/telegram/ui/Components/VideoPlayer;->preparePlayerLoop(Landroid/net/Uri;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;)V

    goto :goto_0

    .line 1865
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/VideoPlayer;->preparePlayer(Landroid/net/Uri;Ljava/lang/String;)V

    .line 1867
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->play()V

    :cond_2
    return-void
.end method

.method private synthetic lambda$onPlayerError$3(Landroidx/media3/common/PlaybackException;)V
    .locals 4

    .line 1830
    invoke-virtual {p1}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object v0

    .line 1831
    instance-of v1, v0, Landroidx/media3/exoplayer/mediacodec/MediaCodecDecoderException;

    const/4 v2, 0x1

    if-eqz v1, :cond_2

    .line 1832
    invoke-virtual {v0}, Ljava/lang/Throwable;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v3, "av1"

    invoke-virtual {v1, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    invoke-virtual {v0}, Ljava/lang/Throwable;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v3, "av01"

    invoke-virtual {v1, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1833
    :cond_0
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 1834
    const-string p1, "av1 codec failed, we think this codec is not supported"

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    .line 1835
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    const-string/jumbo v0, "unsupport_video/av01"

    invoke-interface {p1, v0, v2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 1836
    sget-object p1, Lorg/telegram/ui/Components/VideoPlayer;->cachedSupportedCodec:Ljava/util/HashMap;

    if-eqz p1, :cond_1

    .line 1837
    invoke-virtual {p1}, Ljava/util/HashMap;->clear()V

    .line 1839
    :cond_1
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    invoke-static {p1}, Lorg/telegram/ui/Components/VideoPlayer$Quality;->filterByCodec(Ljava/util/ArrayList;)Ljava/util/ArrayList;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-eqz p1, :cond_a

    .line 1841
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/Components/VideoPlayer;->preparePlayer(Ljava/util/ArrayList;Lorg/telegram/ui/Components/VideoPlayer$Quality;)V

    return-void

    .line 1846
    :cond_2
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    if-eqz v1, :cond_9

    iget-boolean v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->triedReinit:Z

    if-nez v3, :cond_3

    instance-of v3, v0, Landroidx/media3/exoplayer/mediacodec/MediaCodecRenderer$DecoderInitializationException;

    if-nez v3, :cond_4

    :cond_3
    instance-of v0, v0, Landroidx/media3/exoplayer/video/SurfaceNotValidException;

    if-eqz v0, :cond_9

    .line 1847
    :cond_4
    iput-boolean v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->triedReinit:Z

    .line 1848
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p1, :cond_a

    .line 1849
    invoke-virtual {v1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p1

    check-cast p1, Landroid/view/ViewGroup;

    if-eqz p1, :cond_5

    .line 1851
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->indexOfChild(Landroid/view/View;)I

    move-result v0

    .line 1852
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    invoke-virtual {p1, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 1853
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    invoke-virtual {p1, v1, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;I)V

    .line 1855
    :cond_5
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->workerQueue:Lorg/telegram/messenger/DispatchQueue;

    if-eqz p1, :cond_6

    .line 1856
    new-instance v0, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda4;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/ui/Components/VideoPlayer;)V

    invoke-virtual {p1, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void

    .line 1871
    :cond_6
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    invoke-interface {p1, v0}, Landroidx/media3/common/Player;->clearVideoTextureView(Landroid/view/TextureView;)V

    .line 1872
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    invoke-interface {p1, v0}, Landroidx/media3/common/Player;->setVideoTextureView(Landroid/view/TextureView;)V

    .line 1873
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-eqz p1, :cond_7

    .line 1874
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/Components/VideoPlayer;->preparePlayer(Ljava/util/ArrayList;Lorg/telegram/ui/Components/VideoPlayer$Quality;)V

    goto :goto_0

    .line 1875
    :cond_7
    iget-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->loopingMediaSource:Z

    .line 1878
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    if-eqz p1, :cond_8

    .line 1876
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUri:Landroid/net/Uri;

    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioType:Ljava/lang/String;

    invoke-virtual {p0, v0, p1, v1, v2}, Lorg/telegram/ui/Components/VideoPlayer;->preparePlayerLoop(Landroid/net/Uri;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;)V

    goto :goto_0

    .line 1878
    :cond_8
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    invoke-virtual {p0, v0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->preparePlayer(Landroid/net/Uri;Ljava/lang/String;)V

    .line 1880
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->play()V

    return-void

    .line 1883
    :cond_9
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    if-eqz v0, :cond_a

    .line 1884
    invoke-interface {v0, p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onError(Lorg/telegram/ui/Components/VideoPlayer;Ljava/lang/Exception;)V

    :cond_a
    return-void
.end method

.method private maybeReportPlayerState()V
    .locals 3

    .line 1922
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    goto :goto_0

    .line 1925
    :cond_0
    invoke-interface {v0}, Landroidx/media3/common/Player;->getPlayWhenReady()Z

    move-result v0

    .line 1926
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v1}, Landroidx/media3/common/Player;->getPlaybackState()I

    move-result v1

    .line 1927
    iget-boolean v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->lastReportedPlayWhenReady:Z

    if-ne v2, v0, :cond_2

    iget v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->lastReportedPlaybackState:I

    if-eq v2, v1, :cond_1

    goto :goto_1

    :cond_1
    :goto_0
    return-void

    .line 1928
    :cond_2
    :goto_1
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    invoke-interface {v2, v0, v1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onStateChanged(ZI)V

    .line 1929
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->lastReportedPlayWhenReady:Z

    .line 1930
    iput v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->lastReportedPlaybackState:I

    return-void
.end method

.method private maybeWrapWithExternalSubtitle(Landroidx/media3/exoplayer/source/MediaSource;)Landroidx/media3/exoplayer/source/MediaSource;
    .locals 3

    .line 412
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentExternalSubtitle:Lorg/telegram/ui/Components/VideoPlayer$ExternalSubtitle;

    if-nez v0, :cond_0

    return-object p1

    .line 415
    :cond_0
    new-instance v0, Landroidx/media3/exoplayer/source/SingleSampleMediaSource$Factory;

    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    invoke-direct {v0, v1}, Landroidx/media3/exoplayer/source/SingleSampleMediaSource$Factory;-><init>(Landroidx/media3/datasource/DataSource$Factory;)V

    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentExternalSubtitle:Lorg/telegram/ui/Components/VideoPlayer$ExternalSubtitle;

    .line 416
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer$ExternalSubtitle;->toSubtitleConfiguration()Landroidx/media3/common/MediaItem$SubtitleConfiguration;

    move-result-object p0

    const-wide v1, -0x7fffffffffffffffL    # -4.9E-324

    invoke-virtual {v0, p0, v1, v2}, Landroidx/media3/exoplayer/source/SingleSampleMediaSource$Factory;->createMediaSource(Landroidx/media3/common/MediaItem$SubtitleConfiguration;J)Landroidx/media3/exoplayer/source/SingleSampleMediaSource;

    move-result-object p0

    .line 417
    new-instance v0, Landroidx/media3/exoplayer/source/MergingMediaSource;

    const/4 v1, 0x2

    new-array v1, v1, [Landroidx/media3/exoplayer/source/MediaSource;

    const/4 v2, 0x0

    aput-object p1, v1, v2

    const/4 p1, 0x1

    aput-object p0, v1, p1

    invoke-direct {v0, v1}, Landroidx/media3/exoplayer/source/MergingMediaSource;-><init>([Landroidx/media3/exoplayer/source/MediaSource;)V

    return-object v0
.end method

.method private mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;
    .locals 6

    const/4 v5, 0x1

    move-object v0, p0

    move-object v1, p1

    move-wide v2, p2

    move-object v4, p4

    .line 376
    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;Z)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p0

    return-object p0
.end method

.method private mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;Z)Landroidx/media3/exoplayer/source/MediaSource;
    .locals 2

    .line 380
    new-instance v0, Landroidx/media3/common/MediaItem$Builder;

    invoke-direct {v0}, Landroidx/media3/common/MediaItem$Builder;-><init>()V

    invoke-virtual {v0, p1}, Landroidx/media3/common/MediaItem$Builder;->setUri(Landroid/net/Uri;)Landroidx/media3/common/MediaItem$Builder;

    move-result-object p1

    invoke-virtual {p1}, Landroidx/media3/common/MediaItem$Builder;->build()Landroidx/media3/common/MediaItem;

    move-result-object p1

    const-wide/16 v0, 0x0

    cmp-long v0, p2, v0

    if-eqz v0, :cond_1

    .line 383
    new-instance p4, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;

    new-instance v0, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p2, p3}, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/ui/Components/VideoPlayer;J)V

    invoke-direct {p4, v0}, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;-><init>(Landroidx/media3/datasource/DataSource$Factory;)V

    .line 385
    invoke-virtual {p4, p1}, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;->createMediaSource(Landroidx/media3/common/MediaItem;)Landroidx/media3/exoplayer/source/ProgressiveMediaSource;

    move-result-object p1

    if-eqz p5, :cond_0

    .line 386
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->maybeWrapWithExternalSubtitle(Landroidx/media3/exoplayer/source/MediaSource;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p0

    return-object p0

    :cond_0
    return-object p1

    .line 388
    :cond_1
    invoke-virtual {p4}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    const-string p2, "hls"

    invoke-virtual {p4, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_5

    const-string p2, "dash"

    invoke-virtual {p4, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_3

    .line 402
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->progressiveMediaSourceFactory:Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;

    if-nez p2, :cond_2

    .line 403
    new-instance p2, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;

    iget-object p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    invoke-direct {p2, p3}, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;-><init>(Landroidx/media3/datasource/DataSource$Factory;)V

    iput-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->progressiveMediaSourceFactory:Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;

    .line 405
    :cond_2
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->progressiveMediaSourceFactory:Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;

    invoke-virtual {p2, p1}, Landroidx/media3/exoplayer/source/ProgressiveMediaSource$Factory;->createMediaSource(Landroidx/media3/common/MediaItem;)Landroidx/media3/exoplayer/source/ProgressiveMediaSource;

    move-result-object p1

    goto :goto_0

    .line 390
    :cond_3
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->dashMediaSourceFactory:Landroidx/media3/exoplayer/source/MediaSource$Factory;

    if-nez p2, :cond_4

    .line 391
    new-instance p2, Landroidx/media3/exoplayer/dash/DashMediaSource$Factory;

    iget-object p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    invoke-direct {p2, p3}, Landroidx/media3/exoplayer/dash/DashMediaSource$Factory;-><init>(Landroidx/media3/datasource/DataSource$Factory;)V

    iput-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->dashMediaSourceFactory:Landroidx/media3/exoplayer/source/MediaSource$Factory;

    .line 393
    :cond_4
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->dashMediaSourceFactory:Landroidx/media3/exoplayer/source/MediaSource$Factory;

    invoke-interface {p2, p1}, Landroidx/media3/exoplayer/source/MediaSource$Factory;->createMediaSource(Landroidx/media3/common/MediaItem;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p1

    goto :goto_0

    .line 396
    :cond_5
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->hlsMediaSourceFactory:Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;

    if-nez p2, :cond_6

    .line 397
    new-instance p2, Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;

    iget-object p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    invoke-direct {p2, p3}, Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;-><init>(Landroidx/media3/datasource/DataSource$Factory;)V

    iput-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->hlsMediaSourceFactory:Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;

    .line 399
    :cond_6
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->hlsMediaSourceFactory:Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;

    invoke-virtual {p2, p1}, Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;->createMediaSource(Landroidx/media3/common/MediaItem;)Landroidx/media3/exoplayer/hls/HlsMediaSource;

    move-result-object p1

    :goto_0
    if-eqz p5, :cond_7

    .line 408
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->maybeWrapWithExternalSubtitle(Landroidx/media3/exoplayer/source/MediaSource;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p0

    return-object p0

    :cond_7
    return-object p1
.end method

.method private mediaSourceFromUri(Lorg/telegram/ui/Components/VideoPlayer$VideoUri;Ljava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;
    .locals 6

    .line 372
    iget-object v1, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->uri:Landroid/net/Uri;

    iget-wide v2, p1, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->fileVideoOffset:J

    const/4 v5, 0x1

    move-object v0, p0

    move-object v4, p2

    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;Z)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p0

    return-object p0
.end method

.method public static saveLooping(ZLorg/telegram/messenger/MessageObject;)V
    .locals 4

    if-nez p1, :cond_0

    return-void

    .line 514
    :cond_0
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v1, "media_saved_pos"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 515
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, "_"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result p1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, "loop"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 516
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0, p1, p0}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static saveQuality(Lorg/telegram/ui/Components/VideoPlayer$Quality;JI)V
    .locals 4

    .line 502
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v1, "media_saved_pos"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 503
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 504
    const-string/jumbo v1, "q2"

    const-string v2, "_"

    if-nez p0, :cond_0

    .line 505
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-interface {v0, p0}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto :goto_1

    .line 507
    :cond_0
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    iget p3, p0, Lorg/telegram/ui/Components/VideoPlayer$Quality;->width:I

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string/jumbo p3, "x"

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p3, p0, Lorg/telegram/ui/Components/VideoPlayer$Quality;->height:I

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    iget-boolean p0, p0, Lorg/telegram/ui/Components/VideoPlayer$Quality;->original:Z

    if-eqz p0, :cond_1

    const-string/jumbo p0, "s"

    goto :goto_0

    :cond_1
    const-string p0, ""

    :goto_0
    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-interface {v0, p1, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 509
    :goto_1
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static saveQuality(Lorg/telegram/ui/Components/VideoPlayer$Quality;Lorg/telegram/messenger/MessageObject;)V
    .locals 2

    if-nez p1, :cond_0

    return-void

    .line 498
    :cond_0
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v0

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result p1

    invoke-static {p0, v0, v1, p1}, Lorg/telegram/ui/Components/VideoPlayer;->saveQuality(Lorg/telegram/ui/Components/VideoPlayer$Quality;JI)V

    return-void
.end method

.method private setAudioTrackState(I)V
    .locals 1

    .line 1728
    iget v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioTrackState:I

    if-ne v0, p1, :cond_0

    goto :goto_0

    .line 1731
    :cond_0
    iput p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioTrackState:I

    .line 1732
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    if-eqz p0, :cond_1

    .line 1733
    invoke-interface {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onAudioTrackChanged(I)V

    :cond_1
    :goto_0
    return-void
.end method

.method private setSelectedQuality(ZLorg/telegram/ui/Components/VideoPlayer$Quality;)V
    .locals 11

    .line 778
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    goto/16 :goto_5

    .line 780
    :cond_0
    invoke-interface {v0}, Landroidx/media3/common/Player;->isPlaying()Z

    move-result v0

    .line 781
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v1}, Landroidx/media3/common/Player;->getCurrentPosition()J

    move-result-wide v1

    if-nez p1, :cond_1

    .line 783
    iput-wide v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->fallbackPosition:J

    .line 784
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v3}, Landroidx/media3/common/Player;->getDuration()J

    move-result-wide v3

    iput-wide v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->fallbackDuration:J

    .line 789
    :cond_1
    iput-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 790
    const-string v3, "hls"

    const-wide/16 v4, 0x0

    const-string/jumbo v6, "other"

    const/4 v7, 0x1

    const/4 v8, 0x0

    if-nez p2, :cond_7

    .line 791
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/VideoPlayer;->makeManifest(Ljava/util/ArrayList;)Landroid/net/Uri;

    move-result-object p2

    .line 792
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getOriginalQuality()Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object v9

    if-eqz v9, :cond_2

    .line 793
    iget-object v10, v9, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v10}, Ljava/util/ArrayList;->size()I

    move-result v10

    if-ne v10, v7, :cond_2

    iget-object v10, v9, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v10, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    invoke-virtual {v10}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isCached()Z

    move-result v10

    if-eqz v10, :cond_2

    .line 794
    iput-boolean v8, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    .line 795
    iput-boolean v7, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    .line 797
    iput-object v9, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 798
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-virtual {v9}, Lorg/telegram/ui/Components/VideoPlayer$Quality;->getDownloadUri()Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    move-result-object v3

    invoke-direct {p0, v3, v6}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Lorg/telegram/ui/Components/VideoPlayer$VideoUri;Ljava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object v3

    invoke-interface {p2, v3, v8}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    goto/16 :goto_4

    :cond_2
    if-eqz p2, :cond_4

    .line 801
    iput-boolean v8, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    .line 802
    iget-object v6, p0, Lorg/telegram/ui/Components/VideoPlayer;->trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

    invoke-virtual {v6}, Landroidx/media3/exoplayer/trackselection/TrackSelector;->getParameters()Landroidx/media3/common/TrackSelectionParameters;

    move-result-object v9

    invoke-virtual {v9}, Landroidx/media3/common/TrackSelectionParameters;->buildUpon()Landroidx/media3/common/TrackSelectionParameters$Builder;

    move-result-object v9

    invoke-virtual {v9}, Landroidx/media3/common/TrackSelectionParameters$Builder;->clearOverrides()Landroidx/media3/common/TrackSelectionParameters$Builder;

    move-result-object v9

    invoke-virtual {v9}, Landroidx/media3/common/TrackSelectionParameters$Builder;->build()Landroidx/media3/common/TrackSelectionParameters;

    move-result-object v9

    invoke-virtual {v6, v9}, Landroidx/media3/exoplayer/trackselection/TrackSelector;->setParameters(Landroidx/media3/common/TrackSelectionParameters;)V

    .line 803
    iget-boolean v6, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    if-nez v6, :cond_3

    .line 804
    iput-boolean v7, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    .line 805
    iget-object v6, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-direct {p0, p2, v4, v5, v3}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p2

    invoke-interface {v6, p2, v8}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    goto/16 :goto_4

    :cond_3
    move v7, v8

    goto/16 :goto_4

    .line 809
    :cond_4
    sget-object p2, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/VideoPlayer;->getHighestQuality(Ljava/lang/Boolean;)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object p2

    if-nez p2, :cond_5

    .line 810
    sget-object p2, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/VideoPlayer;->getHighestQuality(Ljava/lang/Boolean;)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object p2

    :cond_5
    if-eqz p2, :cond_11

    .line 811
    iget-object v3, p2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_6

    goto/16 :goto_5

    .line 812
    :cond_6
    iput-boolean v8, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    .line 813
    iput-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 814
    iget-boolean v3, p2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->original:Z

    iput-boolean v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    .line 815
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-virtual {p2}, Lorg/telegram/ui/Components/VideoPlayer$Quality;->getDownloadUri()Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    move-result-object p2

    invoke-direct {p0, p2, v6}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Lorg/telegram/ui/Components/VideoPlayer$VideoUri;Ljava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p2

    invoke-interface {v3, p2, v8}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    goto/16 :goto_4

    .line 819
    :cond_7
    iput-boolean v8, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    .line 820
    iget-object v9, p2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v9}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v9

    if-eqz v9, :cond_8

    goto/16 :goto_5

    .line 822
    :cond_8
    iget-object v9, p2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v9}, Ljava/util/ArrayList;->size()I

    move-result v9

    if-le v9, v7, :cond_9

    .line 823
    iget-object v9, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    invoke-virtual {p0, v9}, Lorg/telegram/ui/Components/VideoPlayer;->makeManifest(Ljava/util/ArrayList;)Landroid/net/Uri;

    move-result-object v9

    goto :goto_0

    :cond_9
    const/4 v9, 0x0

    :goto_0
    if-eqz v9, :cond_e

    .line 825
    iget-object v10, p2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v10}, Ljava/util/ArrayList;->size()I

    move-result v10

    if-eq v10, v7, :cond_e

    iget-object v10, p0, Lorg/telegram/ui/Components/VideoPlayer;->trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

    invoke-virtual {v10}, Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;->getCurrentMappedTrackInfo()Landroidx/media3/exoplayer/trackselection/MappingTrackSelector$MappedTrackInfo;

    move-result-object v10

    if-nez v10, :cond_a

    goto :goto_3

    .line 830
    :cond_a
    iget-boolean v6, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    if-nez v6, :cond_b

    .line 831
    iput-boolean v7, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    .line 832
    iget-object v6, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-direct {p0, v9, v4, v5, v3}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object v3

    invoke-interface {v6, v3, v8}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    goto :goto_1

    :cond_b
    move v7, v8

    .line 835
    :goto_1
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

    invoke-virtual {v3}, Landroidx/media3/exoplayer/trackselection/TrackSelector;->getParameters()Landroidx/media3/common/TrackSelectionParameters;

    move-result-object v3

    invoke-virtual {v3}, Landroidx/media3/common/TrackSelectionParameters;->buildUpon()Landroidx/media3/common/TrackSelectionParameters$Builder;

    move-result-object v3

    invoke-virtual {v3}, Landroidx/media3/common/TrackSelectionParameters$Builder;->clearOverrides()Landroidx/media3/common/TrackSelectionParameters$Builder;

    move-result-object v3

    .line 836
    iget-object p2, p2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v4

    :goto_2
    if-ge v8, v4, :cond_d

    invoke-virtual {p2, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v8, v8, 0x1

    check-cast v5, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 837
    invoke-direct {p0, v5}, Lorg/telegram/ui/Components/VideoPlayer;->getQualityTrackSelection(Lorg/telegram/ui/Components/VideoPlayer$VideoUri;)Landroidx/media3/common/TrackSelectionOverride;

    move-result-object v5

    if-nez v5, :cond_c

    goto :goto_2

    .line 839
    :cond_c
    invoke-virtual {v3, v5}, Landroidx/media3/common/TrackSelectionParameters$Builder;->addOverride(Landroidx/media3/common/TrackSelectionOverride;)Landroidx/media3/common/TrackSelectionParameters$Builder;

    goto :goto_2

    .line 841
    :cond_d
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->trackSelector:Landroidx/media3/exoplayer/trackselection/MappingTrackSelector;

    invoke-virtual {v3}, Landroidx/media3/common/TrackSelectionParameters$Builder;->build()Landroidx/media3/common/TrackSelectionParameters;

    move-result-object v3

    invoke-virtual {p2, v3}, Landroidx/media3/exoplayer/trackselection/TrackSelector;->setParameters(Landroidx/media3/common/TrackSelectionParameters;)V

    goto :goto_4

    .line 826
    :cond_e
    :goto_3
    iput-boolean v8, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    .line 827
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-virtual {p2}, Lorg/telegram/ui/Components/VideoPlayer$Quality;->getDownloadUri()Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    move-result-object p2

    invoke-direct {p0, p2, v6}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Lorg/telegram/ui/Components/VideoPlayer$VideoUri;Ljava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p2

    invoke-interface {v3, p2, v8}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    :goto_4
    if-eqz v7, :cond_11

    .line 846
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p2}, Landroidx/media3/common/Player;->prepare()V

    if-nez p1, :cond_f

    .line 848
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p1, v1, v2}, Landroidx/media3/common/Player;->seekTo(J)V

    if-eqz v0, :cond_f

    .line 850
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p1}, Landroidx/media3/common/Player;->play()V

    .line 853
    :cond_f
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->onQualityChangeListener:Ljava/lang/Runnable;

    if-eqz p1, :cond_10

    .line 854
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 856
    :cond_10
    sget-object p1, Lorg/telegram/ui/Components/VideoPlayer;->activePlayers:Ljava/util/HashSet;

    iget p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->playerId:I

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    :cond_11
    :goto_5
    return-void
.end method

.method public static supportsHardwareDecoder(Ljava/lang/String;)Z
    .locals 6

    .line 1099
    const-string/jumbo v0, "unsupport_"

    const/4 v1, 0x0

    :try_start_0
    invoke-static {p0}, Lorg/telegram/ui/Components/VideoPlayer;->toMime(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_0

    return v1

    .line 1101
    :cond_0
    sget-object v2, Lorg/telegram/ui/Components/VideoPlayer;->cachedSupportedCodec:Ljava/util/HashMap;

    if-nez v2, :cond_1

    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    sput-object v2, Lorg/telegram/ui/Components/VideoPlayer;->cachedSupportedCodec:Ljava/util/HashMap;

    goto :goto_0

    :catch_0
    move-exception p0

    goto :goto_4

    .line 1102
    :cond_1
    :goto_0
    sget-object v2, Lorg/telegram/ui/Components/VideoPlayer;->cachedSupportedCodec:Ljava/util/HashMap;

    invoke-virtual {v2, p0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Boolean;

    if-eqz v2, :cond_2

    .line 1103
    invoke-virtual {v2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0

    .line 1104
    :cond_2
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object v2

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-interface {v2, v0, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_3

    return v1

    .line 1107
    :cond_3
    invoke-static {}, Landroid/media/MediaCodecList;->getCodecCount()I

    move-result v0

    move v2, v1

    :goto_1
    if-ge v2, v0, :cond_8

    .line 1109
    invoke-static {v2}, Landroid/media/MediaCodecList;->getCodecInfoAt(I)Landroid/media/MediaCodecInfo;

    move-result-object v3

    .line 1110
    invoke-virtual {v3}, Landroid/media/MediaCodecInfo;->isEncoder()Z

    move-result v4

    if-eqz v4, :cond_4

    goto :goto_3

    .line 1111
    :cond_4
    invoke-static {v3, p0}, Landroidx/media3/exoplayer/mediacodec/MediaCodecUtil;->isHardwareAccelerated(Landroid/media/MediaCodecInfo;Ljava/lang/String;)Z

    move-result v4

    if-nez v4, :cond_5

    goto :goto_3

    .line 1112
    :cond_5
    invoke-virtual {v3}, Landroid/media/MediaCodecInfo;->getSupportedTypes()[Ljava/lang/String;

    move-result-object v3

    move v4, v1

    .line 1113
    :goto_2
    array-length v5, v3

    if-ge v4, v5, :cond_7

    .line 1114
    aget-object v5, v3, v4

    invoke-virtual {v5, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_6

    .line 1115
    sget-object v0, Lorg/telegram/ui/Components/VideoPlayer;->cachedSupportedCodec:Ljava/util/HashMap;

    sget-object v2, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-virtual {v0, p0, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x1

    return p0

    :cond_6
    add-int/lit8 v4, v4, 0x1

    goto :goto_2

    :cond_7
    :goto_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 1120
    :cond_8
    sget-object v0, Lorg/telegram/ui/Components/VideoPlayer;->cachedSupportedCodec:Ljava/util/HashMap;

    sget-object v2, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-virtual {v0, p0, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return v1

    .line 1123
    :goto_4
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return v1
.end method

.method public static toMime(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 1084
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->hashCode()I

    move-result v0

    const/4 v1, -0x1

    sparse-switch v0, :sswitch_data_0

    goto/16 :goto_0

    :sswitch_0
    const-string v0, "hevc"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    :cond_1
    const/4 v1, 0x7

    goto :goto_0

    :sswitch_1
    const-string v0, "h265"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_0

    :cond_2
    const/4 v1, 0x6

    goto :goto_0

    :sswitch_2
    const-string v0, "h264"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    goto :goto_0

    :cond_3
    const/4 v1, 0x5

    goto :goto_0

    :sswitch_3
    const-string v0, "av01"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_4

    goto :goto_0

    :cond_4
    const/4 v1, 0x4

    goto :goto_0

    :sswitch_4
    const-string/jumbo v0, "vp9"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5

    goto :goto_0

    :cond_5
    const/4 v1, 0x3

    goto :goto_0

    :sswitch_5
    const-string/jumbo v0, "vp8"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_6

    goto :goto_0

    :cond_6
    const/4 v1, 0x2

    goto :goto_0

    :sswitch_6
    const-string v0, "avc"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_7

    goto :goto_0

    :cond_7
    const/4 v1, 0x1

    goto :goto_0

    :sswitch_7
    const-string v0, "av1"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_8

    goto :goto_0

    :cond_8
    const/4 v1, 0x0

    :goto_0
    packed-switch v1, :pswitch_data_0

    .line 1092
    const-string/jumbo v0, "video/"

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 1090
    :pswitch_0
    const-string/jumbo p0, "video/hevc"

    return-object p0

    .line 1088
    :pswitch_1
    const-string/jumbo p0, "video/x-vnd.on2.vp9"

    return-object p0

    .line 1087
    :pswitch_2
    const-string/jumbo p0, "video/x-vnd.on2.vp8"

    return-object p0

    .line 1086
    :pswitch_3
    const-string/jumbo p0, "video/avc"

    return-object p0

    .line 1091
    :pswitch_4
    const-string/jumbo p0, "video/av01"

    return-object p0

    :sswitch_data_0
    .sparse-switch
        0x17a9c -> :sswitch_7
        0x17ace -> :sswitch_6
        0x1c8be -> :sswitch_5
        0x1c8bf -> :sswitch_4
        0x2dd8f6 -> :sswitch_3
        0x300908 -> :sswitch_2
        0x300909 -> :sswitch_1
        0x30d06a -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_4
        :pswitch_3
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method


# virtual methods
.method public createdWithAudioTrack()Z
    .locals 0

    .line 130
    iget-boolean p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioDisabled:Z

    xor-int/lit8 p0, p0, 0x1

    return p0
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 249
    sget p2, Lorg/telegram/messenger/NotificationCenter;->playerDidStartPlaying:I

    if-ne p1, p2, :cond_0

    const/4 p1, 0x0

    .line 250
    aget-object p1, p3, p1

    check-cast p1, Lorg/telegram/ui/Components/VideoPlayer;

    if-eq p1, p0, :cond_0

    .line 251
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->isPlaying()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->allowMultipleInstances:Z

    if-nez p1, :cond_0

    .line 252
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->pause()V

    :cond_0
    return-void
.end method

.method public getAudioTrackState()I
    .locals 0

    .line 1720
    iget p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioTrackState:I

    return p0
.end method

.method public getBufferedPosition()J
    .locals 2

    .line 1746
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_1

    iget-boolean p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->isStreaming:Z

    if-eqz p0, :cond_0

    invoke-interface {v0}, Landroidx/media3/common/Player;->getBufferedPosition()J

    move-result-wide v0

    return-wide v0

    :cond_0
    invoke-interface {v0}, Landroidx/media3/common/Player;->getDuration()J

    move-result-wide v0

    return-wide v0

    :cond_1
    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public getCurrentChromecastMedia(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMediaVariations;
    .locals 15

    move-object/from16 v1, p2

    move-object/from16 v2, p3

    .line 2149
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    const-string/jumbo v4, "video/mp4"

    const-string v5, "/mtproto_"

    const/4 v6, 0x0

    if-nez v3, :cond_2

    .line 2150
    iget-object v3, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    if-nez v3, :cond_0

    return-object v6

    .line 2154
    :cond_0
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    move-object/from16 v5, p1

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    .line 2155
    iget-object v5, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    const-string/jumbo v6, "mime"

    invoke-virtual {v5, v6}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 2156
    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_1

    goto :goto_0

    :cond_1
    move-object v4, v5

    .line 2159
    :goto_0
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    invoke-static {v0, v3, v4}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->fromUri(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;

    move-result-object v0

    .line 2160
    invoke-virtual {v0, v1}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->setTitle(Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;

    move-result-object v0

    .line 2161
    invoke-virtual {v0, v2}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->setSubtitle(Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;

    move-result-object v0

    .line 2162
    invoke-virtual {v0}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->build()Lorg/telegram/messenger/chromecast/ChromecastMedia;

    move-result-object v0

    .line 2164
    invoke-static {v0}, Lorg/telegram/messenger/chromecast/ChromecastMediaVariations;->of(Lorg/telegram/messenger/chromecast/ChromecastMedia;)Lorg/telegram/messenger/chromecast/ChromecastMediaVariations;

    move-result-object v0

    return-object v0

    .line 2167
    :cond_2
    new-instance v3, Lorg/telegram/messenger/chromecast/ChromecastMediaVariations$Builder;

    invoke-direct {v3}, Lorg/telegram/messenger/chromecast/ChromecastMediaVariations$Builder;-><init>()V

    .line 2168
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v7

    const/4 v8, 0x0

    move v9, v8

    :cond_3
    if-ge v9, v7, :cond_6

    invoke-virtual {v0, v9}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v10

    add-int/lit8 v9, v9, 0x1

    check-cast v10, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 2169
    iget-object v10, v10, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v10}, Ljava/util/ArrayList;->size()I

    move-result v11

    move v12, v8

    :goto_1
    if-ge v12, v11, :cond_3

    invoke-virtual {v10, v12}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v13

    add-int/lit8 v12, v12, 0x1

    check-cast v13, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 2170
    new-instance v14, Ljava/lang/StringBuilder;

    invoke-direct {v14, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    move p0, v7

    iget-wide v6, v13, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->docId:J

    invoke-virtual {v14, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    .line 2172
    iget-object v7, v13, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->document:Lorg/telegram/tgnet/TLRPC$Document;

    if-eqz v7, :cond_4

    .line 2173
    iget-object v7, v7, Lorg/telegram/tgnet/TLRPC$Document;->mime_type:Ljava/lang/String;

    goto :goto_2

    :cond_4
    const/4 v7, 0x0

    .line 2175
    :goto_2
    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v14

    if-eqz v14, :cond_5

    move-object v7, v4

    .line 2178
    :cond_5
    iget-object v14, v13, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->uri:Landroid/net/Uri;

    invoke-static {v14, v6, v7}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->fromUri(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;

    move-result-object v6

    .line 2179
    invoke-virtual {v6, v1}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->setTitle(Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;

    move-result-object v6

    .line 2180
    invoke-virtual {v6, v2}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->setSubtitle(Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;

    move-result-object v6

    iget v7, v13, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    iget v13, v13, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    .line 2181
    invoke-virtual {v6, v7, v13}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->setSize(II)Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;

    move-result-object v6

    .line 2182
    invoke-virtual {v6}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->build()Lorg/telegram/messenger/chromecast/ChromecastMedia;

    move-result-object v6

    .line 2184
    invoke-virtual {v3, v6}, Lorg/telegram/messenger/chromecast/ChromecastMediaVariations$Builder;->add(Lorg/telegram/messenger/chromecast/ChromecastMedia;)Lorg/telegram/messenger/chromecast/ChromecastMediaVariations$Builder;

    move v7, p0

    const/4 v6, 0x0

    goto :goto_1

    .line 2188
    :cond_6
    invoke-virtual {v3}, Lorg/telegram/messenger/chromecast/ChromecastMediaVariations$Builder;->build()Lorg/telegram/messenger/chromecast/ChromecastMediaVariations;

    move-result-object v0

    return-object v0
.end method

.method public getCurrentDocument()Lorg/telegram/tgnet/TLRPC$Document;
    .locals 13

    .line 629
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return-object v1

    .line 630
    :cond_0
    invoke-interface {v0}, Landroidx/media3/exoplayer/ExoPlayer;->getVideoFormat()Landroidx/media3/common/Format;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 631
    iget-wide v2, v0, Landroidx/media3/common/Format;->documentId:J

    const-wide/16 v4, 0x0

    cmp-long v2, v2, v4

    if-nez v2, :cond_1

    goto :goto_0

    .line 633
    :cond_1
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-eqz p0, :cond_4

    .line 634
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v2

    const/4 v3, 0x0

    move v4, v3

    :cond_2
    if-ge v4, v2, :cond_4

    invoke-virtual {p0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    check-cast v5, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 635
    iget-object v5, v5, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v6

    move v7, v3

    :cond_3
    if-ge v7, v6, :cond_2

    invoke-virtual {v5, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    add-int/lit8 v7, v7, 0x1

    check-cast v8, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 636
    iget-wide v9, v8, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->docId:J

    iget-wide v11, v0, Landroidx/media3/common/Format;->documentId:J

    cmp-long v9, v9, v11

    if-nez v9, :cond_3

    .line 637
    iget-object p0, v8, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->document:Lorg/telegram/tgnet/TLRPC$Document;

    return-object p0

    :cond_4
    :goto_0
    return-object v1
.end method

.method public getCurrentPosition()J
    .locals 4

    .line 1634
    iget-wide v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->fallbackPosition:J

    const-wide v2, -0x7fffffffffffffffL    # -4.9E-324

    cmp-long v2, v0, v2

    if-eqz v2, :cond_0

    return-wide v0

    .line 1637
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_1

    invoke-interface {p0}, Landroidx/media3/common/Player;->getCurrentPosition()J

    move-result-wide v0

    return-wide v0

    :cond_1
    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public getCurrentQuality()Lorg/telegram/ui/Components/VideoPlayer$Quality;
    .locals 2

    .line 861
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getCurrentQualityIndex()I

    move-result v0

    if-ltz v0, :cond_1

    .line 862
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getQualitiesCount()I

    move-result v1

    if-lt v0, v1, :cond_0

    goto :goto_0

    .line 863
    :cond_0
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/VideoPlayer;->getQuality(I)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object p0

    return-object p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public getCurrentQualityIndex()I
    .locals 10

    .line 645
    iget v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->selectedQualityIndex:I

    const/4 v1, -0x1

    if-ne v0, v1, :cond_5

    .line 647
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    const/4 v2, 0x0

    if-eqz v0, :cond_1

    move v0, v2

    .line 648
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getQualitiesCount()I

    move-result v3

    if-ge v0, v3, :cond_1

    .line 649
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/VideoPlayer;->getQuality(I)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object v3

    .line 650
    iget-boolean v3, v3, Lorg/telegram/ui/Components/VideoPlayer$Quality;->original:Z

    if-eqz v3, :cond_0

    return v0

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :catch_0
    move-exception p0

    goto :goto_2

    .line 656
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_2

    return v1

    .line 657
    :cond_2
    invoke-interface {v0}, Landroidx/media3/exoplayer/ExoPlayer;->getVideoFormat()Landroidx/media3/common/Format;

    move-result-object v0

    if-nez v0, :cond_3

    return v1

    :cond_3
    move v3, v2

    .line 659
    :goto_1
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getQualitiesCount()I

    move-result v4

    if-ge v3, v4, :cond_5

    .line 660
    invoke-virtual {p0, v3}, Lorg/telegram/ui/Components/VideoPlayer;->getQuality(I)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object v4

    .line 661
    iget-boolean v5, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->original:Z

    if-nez v5, :cond_4

    iget v5, v0, Landroidx/media3/common/Format;->width:I

    iget v6, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->width:I

    if-ne v5, v6, :cond_4

    iget v5, v0, Landroidx/media3/common/Format;->height:I

    iget v6, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->height:I

    if-ne v5, v6, :cond_4

    iget v5, v0, Landroidx/media3/common/Format;->bitrate:I

    iget-object v4, v4, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v4, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    iget-wide v6, v4, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    const-wide/high16 v8, 0x4020000000000000L    # 8.0

    mul-double/2addr v6, v8

    invoke-static {v6, v7}, Ljava/lang/Math;->floor(D)D

    move-result-wide v6
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    double-to-int v4, v6

    if-ne v5, v4, :cond_4

    return v3

    :cond_4
    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    .line 703
    :goto_2
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return v1

    .line 707
    :cond_5
    iget p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->selectedQualityIndex:I

    return p0
.end method

.method public getCurrentUri()Landroid/net/Uri;
    .locals 0

    .line 1550
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentUri:Landroid/net/Uri;

    return-object p0
.end method

.method public getDuration()J
    .locals 4

    .line 1627
    iget-wide v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->fallbackDuration:J

    const-wide v2, -0x7fffffffffffffffL    # -4.9E-324

    cmp-long v2, v0, v2

    if-eqz v2, :cond_0

    return-wide v0

    .line 1630
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_1

    invoke-interface {p0}, Landroidx/media3/common/Player;->getDuration()J

    move-result-wide v0

    return-wide v0

    :cond_1
    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public getHDRStaticInfo(Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;)Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;
    .locals 5

    .line 2091
    const-string v0, "color-range"

    const-string v1, "color-standard"

    const-string v2, "color-transfer"

    if-nez p1, :cond_0

    .line 2092
    new-instance p1, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;

    invoke-direct {p1}, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;-><init>()V

    .line 2095
    :cond_0
    :try_start_0
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v3, 0x0

    invoke-interface {p0, v3}, Landroidx/media3/exoplayer/ExoPlayer;->getRenderer(I)Landroidx/media3/exoplayer/Renderer;

    move-result-object p0

    check-cast p0, Landroidx/media3/exoplayer/mediacodec/MediaCodecRenderer;

    invoke-virtual {p0}, Landroidx/media3/exoplayer/mediacodec/MediaCodecRenderer;->getCodecOutputMediaFormat()Landroid/media/MediaFormat;

    move-result-object p0

    .line 2096
    const-string v3, "hdr-static-info"

    invoke-virtual {p0, v3}, Landroid/media/MediaFormat;->getByteBuffer(Ljava/lang/String;)Ljava/nio/ByteBuffer;

    move-result-object v3

    .line 2097
    sget-object v4, Ljava/nio/ByteOrder;->LITTLE_ENDIAN:Ljava/nio/ByteOrder;

    invoke-virtual {v3, v4}, Ljava/nio/ByteBuffer;->order(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;

    .line 2098
    invoke-virtual {v3}, Ljava/nio/ByteBuffer;->get()B

    move-result v4

    if-nez v4, :cond_1

    const/16 v4, 0x11

    .line 2099
    invoke-virtual {v3, v4}, Ljava/nio/ByteBuffer;->getShort(I)S

    move-result v4

    int-to-float v4, v4

    iput v4, p1, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;->maxlum:F

    const/16 v4, 0x13

    .line 2100
    invoke-virtual {v3, v4}, Ljava/nio/ByteBuffer;->getShort(I)S

    move-result v3

    int-to-float v3, v3

    const v4, 0x38d1b717    # 1.0E-4f

    mul-float/2addr v3, v4

    iput v3, p1, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;->minlum:F

    .line 2104
    :cond_1
    invoke-virtual {p0, v2}, Landroid/media/MediaFormat;->containsKey(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 2105
    invoke-virtual {p0, v2}, Landroid/media/MediaFormat;->getInteger(Ljava/lang/String;)I

    move-result v2

    iput v2, p1, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;->colorTransfer:I

    .line 2107
    :cond_2
    invoke-virtual {p0, v1}, Landroid/media/MediaFormat;->containsKey(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 2108
    invoke-virtual {p0, v1}, Landroid/media/MediaFormat;->getInteger(Ljava/lang/String;)I

    move-result v1

    iput v1, p1, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;->colorStandard:I

    .line 2110
    :cond_3
    invoke-virtual {p0, v0}, Landroid/media/MediaFormat;->containsKey(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 2111
    invoke-virtual {p0, v0}, Landroid/media/MediaFormat;->getInteger(Ljava/lang/String;)I

    move-result p0

    iput p0, p1, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;->colorRange:I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_4
    return-object p1

    :catch_0
    const/4 p0, 0x0

    .line 2115
    iput p0, p1, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;->minlum:F

    iput p0, p1, Lorg/telegram/ui/Stories/recorder/StoryEntry$HDRInfo;->maxlum:F

    return-object p1
.end method

.method public getHighestQuality(Ljava/lang/Boolean;)Lorg/telegram/ui/Components/VideoPlayer$Quality;
    .locals 6

    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 548
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getQualitiesCount()I

    move-result v2

    if-ge v1, v2, :cond_3

    .line 549
    invoke-virtual {p0, v1}, Lorg/telegram/ui/Components/VideoPlayer;->getQuality(I)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object v2

    if-eqz p1, :cond_0

    .line 550
    iget-boolean v3, v2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->original:Z

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    if-eq v3, v4, :cond_0

    goto :goto_1

    :cond_0
    if-eqz v0, :cond_1

    .line 551
    iget v3, v0, Lorg/telegram/ui/Components/VideoPlayer$Quality;->width:I

    iget v4, v0, Lorg/telegram/ui/Components/VideoPlayer$Quality;->height:I

    mul-int/2addr v3, v4

    iget v4, v2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->width:I

    iget v5, v2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->height:I

    mul-int/2addr v4, v5

    if-ge v3, v4, :cond_2

    :cond_1
    move-object v0, v2

    :cond_2
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_3
    return-object v0
.end method

.method public getLowestFile()Ljava/io/File;
    .locals 7

    .line 605
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-eqz v0, :cond_3

    .line 606
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    :goto_0
    if-ltz v0, :cond_3

    .line 607
    iget-object v2, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 608
    iget-object v2, v2, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x0

    :cond_0
    if-ge v4, v3, :cond_2

    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    check-cast v5, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 609
    invoke-virtual {v5}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isCached()Z

    move-result v6

    if-nez v6, :cond_1

    .line 610
    invoke-virtual {v5, v1}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->updateCached(Z)V

    .line 611
    :cond_1
    invoke-virtual {v5}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isCached()Z

    move-result v6

    if-eqz v6, :cond_0

    .line 612
    new-instance p0, Ljava/io/File;

    iget-object v0, v5, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->uri:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    return-object p0

    :cond_2
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    .line 616
    :cond_3
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    if-eqz v0, :cond_4

    .line 617
    const-string v1, "file"

    invoke-virtual {v0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 618
    new-instance v0, Ljava/io/File;

    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    invoke-virtual {p0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    return-object v0

    :cond_4
    const/4 p0, 0x0

    return-object p0
.end method

.method public getOriginalQuality()Lorg/telegram/ui/Components/VideoPlayer$Quality;
    .locals 3

    const/4 v0, 0x0

    .line 539
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getQualitiesCount()I

    move-result v1

    if-ge v0, v1, :cond_1

    .line 540
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/VideoPlayer;->getQuality(I)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object v1

    .line 541
    iget-boolean v2, v1, Lorg/telegram/ui/Components/VideoPlayer$Quality;->original:Z

    if-eqz v2, :cond_0

    return-object v1

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public getPlayWhenReady()Z
    .locals 0

    .line 1542
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0}, Landroidx/media3/common/Player;->getPlayWhenReady()Z

    move-result p0

    return p0
.end method

.method public getPlaybackSpeed()F
    .locals 1

    .line 1598
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/high16 v0, 0x3f800000    # 1.0f

    if-nez p0, :cond_0

    return v0

    .line 1599
    :cond_0
    invoke-interface {p0}, Landroidx/media3/common/Player;->getPlaybackParameters()Landroidx/media3/common/PlaybackParameters;

    move-result-object p0

    if-nez p0, :cond_1

    return v0

    .line 1601
    :cond_1
    iget p0, p0, Landroidx/media3/common/PlaybackParameters;->speed:F

    return p0
.end method

.method public getPlaybackState()I
    .locals 0

    .line 1546
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0}, Landroidx/media3/common/Player;->getPlaybackState()I

    move-result p0

    return p0
.end method

.method public getQualitiesCount()I
    .locals 0

    .line 584
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return p0

    .line 585
    :cond_0
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result p0

    return p0
.end method

.method public getQuality(I)Lorg/telegram/ui/Components/VideoPlayer$Quality;
    .locals 1

    .line 533
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-nez v0, :cond_0

    sget-object p1, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->getHighestQuality(Ljava/lang/Boolean;)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object p0

    return-object p0

    :cond_0
    if-ltz p1, :cond_2

    .line 534
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lt p1, v0, :cond_1

    goto :goto_0

    .line 535
    :cond_1
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    invoke-virtual {p0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    return-object p0

    .line 534
    :cond_2
    :goto_0
    sget-object p1, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->getHighestQuality(Ljava/lang/Boolean;)Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-result-object p0

    return-object p0
.end method

.method public getRepeatCount()I
    .locals 0

    .line 1935
    iget p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->repeatCount:I

    return p0
.end method

.method public getSelectedQuality()I
    .locals 0

    .line 625
    iget p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->selectedQualityIndex:I

    return p0
.end method

.method public hasAudioTrack()Z
    .locals 1

    .line 1724
    iget p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioTrackState:I

    const/4 v0, 0x1

    if-ne p0, v0, :cond_0

    return v0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public isBuffering()Z
    .locals 1

    .line 1758
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_0

    iget p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->lastReportedPlaybackState:I

    const/4 v0, 0x2

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public isHDR()Z
    .locals 2

    .line 2073
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 2077
    :cond_0
    :try_start_0
    invoke-interface {p0}, Landroidx/media3/exoplayer/ExoPlayer;->getVideoFormat()Landroidx/media3/common/Format;

    move-result-object p0

    if-eqz p0, :cond_4

    .line 2078
    iget-object p0, p0, Landroidx/media3/common/Format;->colorInfo:Landroidx/media3/common/ColorInfo;

    if-nez p0, :cond_1

    goto :goto_1

    .line 2081
    :cond_1
    iget p0, p0, Landroidx/media3/common/ColorInfo;->colorTransfer:I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const/4 v1, 0x6

    if-eq p0, v1, :cond_3

    const/4 v1, 0x7

    if-ne p0, v1, :cond_2

    goto :goto_0

    :cond_2
    return v0

    :cond_3
    :goto_0
    const/4 p0, 0x1

    return p0

    :catch_0
    :cond_4
    :goto_1
    return v0
.end method

.method public isLooping()Z
    .locals 0

    .line 1793
    iget-boolean p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->looping:Z

    return p0
.end method

.method public isMuted()Z
    .locals 1

    .line 1641
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_0

    invoke-interface {p0}, Landroidx/media3/common/Player;->getVolume()F

    move-result p0

    const/4 v0, 0x0

    cmpl-float p0, p0, v0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public isPlayerPrepared()Z
    .locals 0

    .line 1441
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public isPlaying()Z
    .locals 1

    .line 1754
    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedAudio:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedPlayWhenReady:Z

    if-nez v0, :cond_1

    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_2

    invoke-interface {p0}, Landroidx/media3/common/Player;->getPlayWhenReady()Z

    move-result p0

    if-eqz p0, :cond_2

    :cond_1
    const/4 p0, 0x1

    return p0

    :cond_2
    const/4 p0, 0x0

    return p0
.end method

.method public makeManifest(Ljava/util/ArrayList;)Landroid/net/Uri;
    .locals 14
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;)",
            "Landroid/net/Uri;"
        }
    .end annotation

    .line 1129
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "#EXTM3U\n#EXT-X-VERSION:6\n#EXT-X-INDEPENDENT-SEGMENTS\n\n"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 1133
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->manifestUris:Ljava/util/ArrayList;

    .line 1135
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 1136
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v2

    const/4 v3, 0x0

    move v4, v3

    move v5, v4

    :cond_0
    if-ge v5, v2, :cond_5

    invoke-virtual {p1, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    check-cast v6, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 1137
    iget-object v6, v6, Lorg/telegram/ui/Components/VideoPlayer$Quality;->uris:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v7

    move v8, v3

    :cond_1
    :goto_0
    if-ge v8, v7, :cond_0

    invoke-virtual {v6, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v9

    add-int/lit8 v8, v8, 0x1

    check-cast v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;

    .line 1138
    iget-object v10, p0, Lorg/telegram/ui/Components/VideoPlayer;->mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    iget-wide v11, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->docId:J

    iget-object v13, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->uri:Landroid/net/Uri;

    invoke-virtual {v10, v11, v12, v13}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->putDocumentUri(JLandroid/net/Uri;)V

    .line 1139
    iget-object v10, p0, Lorg/telegram/ui/Components/VideoPlayer;->mediaDataSourceFactory:Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    iget-wide v11, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->manifestDocId:J

    iget-object v13, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->m3u8uri:Landroid/net/Uri;

    invoke-virtual {v10, v11, v12, v13}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;->putDocumentUri(JLandroid/net/Uri;)V

    .line 1140
    iget-object v10, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->m3u8uri:Landroid/net/Uri;

    if-eqz v10, :cond_1

    .line 1141
    iget-object v4, p0, Lorg/telegram/ui/Components/VideoPlayer;->manifestUris:Ljava/util/ArrayList;

    invoke-virtual {v4, v9}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 1142
    new-instance v4, Ljava/lang/StringBuilder;

    const-string v10, "#EXT-X-STREAM-INF:BANDWIDTH="

    invoke-direct {v4, v10}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 1143
    iget-wide v10, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->bitrate:D

    const-wide/high16 v12, 0x4020000000000000L    # 8.0

    mul-double/2addr v10, v12

    invoke-static {v10, v11}, Ljava/lang/Math;->floor(D)D

    move-result-wide v10

    double-to-int v10, v10

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v10, ",RESOLUTION="

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v10, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->width:I

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string/jumbo v10, "x"

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v10, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->height:I

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 1144
    iget-object v10, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->codec:Ljava/lang/String;

    invoke-static {v10}, Lorg/telegram/ui/Components/VideoPlayer;->toMime(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    if-eqz v10, :cond_2

    .line 1146
    const-string v11, ",MIME=\""

    invoke-virtual {v4, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, "\""

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1148
    :cond_2
    invoke-virtual {v9}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isCached()Z

    move-result v10

    if-eqz v10, :cond_3

    invoke-virtual {v9}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isManifestCached()Z

    move-result v10

    if-eqz v10, :cond_3

    .line 1149
    const-string v10, ",CACHED=\"true\""

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1151
    :cond_3
    const-string v10, ",DOCID=\""

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v10, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->docId:J

    invoke-virtual {v4, v10, v11}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v10, "\",ACCOUNT=\""

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1152
    iget v10, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->currentAccount:I

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v10, "\"\n"

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1154
    invoke-virtual {v9}, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->isManifestCached()Z

    move-result v10

    const-string v11, "\n\n"

    if-eqz v10, :cond_4

    .line 1155
    iget-object v9, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->m3u8uri:Landroid/net/Uri;

    invoke-virtual {v4, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 1157
    :cond_4
    const-string/jumbo v10, "mtproto:"

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v9, v9, Lorg/telegram/ui/Components/VideoPlayer$VideoUri;->manifestDocId:J

    invoke-virtual {v4, v9, v10}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1160
    :goto_1
    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    const/4 v4, 0x1

    goto/16 :goto_0

    :cond_5
    if-nez v4, :cond_6

    const/4 p0, 0x0

    return-object p0

    .line 1165
    :cond_6
    invoke-static {v1}, Ljava/util/Collections;->reverse(Ljava/util/List;)V

    .line 1166
    const-string p0, ""

    invoke-static {p0, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1167
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->getBytes()[B

    move-result-object p0

    const/4 p1, 0x2

    invoke-static {p0, p1}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object p0

    .line 1168
    new-instance p1, Ljava/lang/StringBuilder;

    const-string v0, "data:application/x-mpegurl;base64,"

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    return-object p0
.end method

.method public onCues(Landroidx/media3/common/text/CueGroup;)V
    .locals 1

    .line 754
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onCues(Landroidx/media3/common/text/CueGroup;)V

    .line 755
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    if-eqz v0, :cond_0

    .line 756
    new-instance v0, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/Components/VideoPlayer;Landroidx/media3/common/text/CueGroup;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method public onPlaybackParametersChanged(Landroidx/media3/common/PlaybackParameters;)V
    .locals 0

    return-void
.end method

.method public onPlayerError(Landroidx/media3/common/PlaybackException;)V
    .locals 1

    .line 1829
    new-instance v0, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/ui/Components/VideoPlayer;Landroidx/media3/common/PlaybackException;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public onPlayerStateChanged(ZI)V
    .locals 3

    .line 1804
    invoke-direct {p0}, Lorg/telegram/ui/Components/VideoPlayer;->maybeReportPlayerState()V

    const/4 v0, 0x3

    if-eqz p1, :cond_0

    if-ne p2, v0, :cond_0

    .line 1805
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->isMuted()Z

    move-result p1

    if-nez p1, :cond_0

    iget-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->shouldPauseOther:Z

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->hasAudioTrack()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 1806
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v1, Lorg/telegram/messenger/NotificationCenter;->playerDidStartPlaying:I

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {p1, v1, v2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 1808
    :cond_0
    iget-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoPlayerReady:Z

    const/4 v1, 0x1

    if-nez p1, :cond_1

    if-ne p2, v0, :cond_1

    .line 1809
    iput-boolean v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoPlayerReady:Z

    .line 1810
    invoke-direct {p0}, Lorg/telegram/ui/Components/VideoPlayer;->checkPlayersReady()V

    :cond_1
    if-eq p2, v0, :cond_2

    .line 1813
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUpdateHandler:Landroid/os/Handler;

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 1814
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioVisualizerDelegate:Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;

    if-eqz p0, :cond_2

    const/4 p1, 0x0

    .line 1815
    invoke-interface {p0, p1, v1, p2}, Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;->onVisualizerUpdate(ZZ[F)V

    :cond_2
    return-void
.end method

.method public onPositionDiscontinuity(Landroidx/media3/common/Player$PositionInfo;Landroidx/media3/common/Player$PositionInfo;I)V
    .locals 0

    if-nez p3, :cond_0

    .line 1823
    iget p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->repeatCount:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->repeatCount:I

    :cond_0
    return-void
.end method

.method public onPositionDiscontinuity(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Player$PositionInfo;Landroidx/media3/common/Player$PositionInfo;I)V
    .locals 0

    const/4 p2, 0x1

    if-ne p4, p2, :cond_2

    .line 1489
    iget-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    if-eqz p2, :cond_0

    .line 1490
    invoke-interface {p2, p1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onSeekFinished(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    .line 1492
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->seekFinishedListeners:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p2

    const/4 p3, 0x0

    :goto_0
    if-ge p3, p2, :cond_1

    invoke-virtual {p1, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p4

    add-int/lit8 p3, p3, 0x1

    check-cast p4, Ljava/lang/Runnable;

    .line 1493
    invoke-interface {p4}, Ljava/lang/Runnable;->run()V

    goto :goto_0

    .line 1495
    :cond_1
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->seekFinishedListeners:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->clear()V

    :cond_2
    return-void
.end method

.method public onRenderedFirstFrame()V
    .locals 0

    .line 1903
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    invoke-interface {p0}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onRenderedFirstFrame()V

    return-void
.end method

.method public onRenderedFirstFrame(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Object;J)V
    .locals 0

    const-wide p2, -0x7fffffffffffffffL    # -4.9E-324

    .line 1501
    iput-wide p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->fallbackPosition:J

    .line 1502
    iput-wide p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->fallbackDuration:J

    .line 1503
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    if-eqz p0, :cond_0

    .line 1504
    invoke-interface {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onRenderedFirstFrame(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    :cond_0
    return-void
.end method

.method public onRepeatModeChanged(I)V
    .locals 0

    return-void
.end method

.method public onSeekStarted(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V
    .locals 0

    .line 1475
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    if-eqz p0, :cond_0

    .line 1476
    invoke-interface {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onSeekStarted(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    :cond_0
    return-void
.end method

.method public onSurfaceDestroyed(Landroid/graphics/SurfaceTexture;)Z
    .locals 0

    .line 1908
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    invoke-interface {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onSurfaceDestroyed(Landroid/graphics/SurfaceTexture;)Z

    move-result p0

    return p0
.end method

.method public onSurfaceTextureUpdated(Landroid/graphics/SurfaceTexture;)V
    .locals 0

    .line 1913
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    invoke-interface {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onSurfaceTextureUpdated(Landroid/graphics/SurfaceTexture;)V

    return-void
.end method

.method public onTrackSelectionParametersChanged(Landroidx/media3/common/TrackSelectionParameters;)V
    .locals 0

    .line 746
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onTrackSelectionParametersChanged(Landroidx/media3/common/TrackSelectionParameters;)V

    .line 747
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->onQualityChangeListener:Ljava/lang/Runnable;

    if-eqz p0, :cond_0

    .line 748
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method public onTracksChanged(Landroidx/media3/common/Tracks;)V
    .locals 2

    .line 2135
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onTracksChanged(Landroidx/media3/common/Tracks;)V

    .line 2136
    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedAudio:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    .line 2137
    invoke-direct {p0, v1}, Lorg/telegram/ui/Components/VideoPlayer;->setAudioTrackState(I)V

    goto :goto_1

    .line 2138
    :cond_0
    invoke-virtual {p1}, Landroidx/media3/common/Tracks;->getGroups()Lcom/google/common/collect/ImmutableList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 p1, 0x0

    .line 2139
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->setAudioTrackState(I)V

    goto :goto_1

    .line 2141
    :cond_1
    invoke-virtual {p1, v1}, Landroidx/media3/common/Tracks;->isTypeSelected(I)Z

    move-result p1

    if-eqz p1, :cond_2

    goto :goto_0

    :cond_2
    const/4 v1, 0x2

    :goto_0
    invoke-direct {p0, v1}, Lorg/telegram/ui/Components/VideoPlayer;->setAudioTrackState(I)V

    .line 2143
    :goto_1
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->onQualityChangeListener:Ljava/lang/Runnable;

    if-eqz p0, :cond_3

    .line 2144
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_3
    return-void
.end method

.method public onVideoSizeChanged(Landroidx/media3/common/VideoSize;)V
    .locals 5

    .line 1895
    sget-object v0, Landroidx/media3/common/VideoSize;->UNKNOWN:Landroidx/media3/common/VideoSize;

    invoke-static {p1, v0}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 1896
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    iget v1, p1, Landroidx/media3/common/VideoSize;->width:I

    iget v2, p1, Landroidx/media3/common/VideoSize;->height:I

    iget v3, p1, Landroidx/media3/common/VideoSize;->unappliedRotationDegrees:I

    iget v4, p1, Landroidx/media3/common/VideoSize;->pixelWidthHeightRatio:F

    invoke-interface {v0, v1, v2, v3, v4}, Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;->onVideoSizeChanged(IIIF)V

    .line 1898
    :cond_0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onVideoSizeChanged(Landroidx/media3/common/VideoSize;)V

    return-void
.end method

.method public pause()V
    .locals 3

    const/4 v0, 0x0

    .line 1575
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedPlayWhenReady:Z

    .line 1576
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v1, :cond_0

    .line 1577
    invoke-interface {v1, v0}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    .line 1579
    :cond_0
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v1, :cond_1

    .line 1580
    invoke-interface {v1, v0}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    .line 1583
    :cond_1
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioVisualizerDelegate:Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;

    if-eqz v1, :cond_2

    .line 1584
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUpdateHandler:Landroid/os/Handler;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 1585
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioVisualizerDelegate:Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;

    const/4 v1, 0x1

    invoke-interface {p0, v0, v1, v2}, Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;->onVisualizerUpdate(ZZ[F)V

    :cond_2
    return-void
.end method

.method public play()V
    .locals 2

    const/4 v0, 0x1

    .line 1554
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedPlayWhenReady:Z

    .line 1555
    iget-boolean v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedAudio:Z

    if-eqz v1, :cond_2

    .line 1556
    iget-boolean v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayerReady:Z

    if-eqz v1, :cond_0

    iget-boolean v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoPlayerReady:Z

    if-nez v1, :cond_2

    .line 1557
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 1558
    invoke-interface {v0, v1}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    .line 1560
    :cond_1
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_4

    .line 1561
    invoke-interface {p0, v1}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    return-void

    .line 1566
    :cond_2
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v1, :cond_3

    .line 1567
    invoke-interface {v1, v0}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    .line 1569
    :cond_3
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_4

    .line 1570
    invoke-interface {p0, v0}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    :cond_4
    return-void
.end method

.method public preparePlayer(Landroid/net/Uri;Ljava/lang/String;)V
    .locals 6

    const/4 v3, 0x3

    const-wide/16 v4, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    .line 421
    invoke-virtual/range {v0 .. v5}, Lorg/telegram/ui/Components/VideoPlayer;->preparePlayer(Landroid/net/Uri;Ljava/lang/String;IJ)V

    return-void
.end method

.method public preparePlayer(Landroid/net/Uri;Ljava/lang/String;IJ)V
    .locals 8

    const/4 p3, 0x0

    .line 425
    invoke-direct {p0, p3}, Lorg/telegram/ui/Components/VideoPlayer;->setAudioTrackState(I)V

    const/4 v0, 0x0

    .line 426
    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    .line 427
    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    .line 428
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    .line 429
    iput-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    .line 430
    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUri:Landroid/net/Uri;

    .line 431
    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioType:Ljava/lang/String;

    .line 432
    iput-wide p4, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentVideoByteOffset:J

    .line 433
    iput-boolean p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->loopingMediaSource:Z

    .line 434
    iput-boolean p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    .line 435
    iput-boolean p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    .line 437
    iput-boolean p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoPlayerReady:Z

    .line 438
    iput-boolean p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedAudio:Z

    .line 439
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentUri:Landroid/net/Uri;

    if-eqz p1, :cond_0

    .line 440
    invoke-virtual {p1}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v0

    :cond_0
    const/4 v1, 0x1

    if-eqz v0, :cond_1

    .line 441
    const-string v2, "file"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    move p3, v1

    :cond_1
    iput-boolean p3, p0, Lorg/telegram/ui/Components/VideoPlayer;->isStreaming:Z

    .line 442
    invoke-direct {p0}, Lorg/telegram/ui/Components/VideoPlayer;->ensurePlayerCreated()V

    const/4 v7, 0x1

    move-object v2, p0

    move-object v3, p1

    move-object v6, p2

    move-wide v4, p4

    .line 443
    invoke-direct/range {v2 .. v7}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;Z)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p0

    .line 444
    iget-object p1, v2, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p1, p0, v1}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    .line 445
    iget-object p0, v2, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0}, Landroidx/media3/common/Player;->prepare()V

    return-void
.end method

.method public preparePlayer(Ljava/util/ArrayList;Lorg/telegram/ui/Components/VideoPlayer$Quality;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ">;",
            "Lorg/telegram/ui/Components/VideoPlayer$Quality;",
            ")V"
        }
    .end annotation

    const/4 v0, 0x0

    .line 449
    invoke-direct {p0, v0}, Lorg/telegram/ui/Components/VideoPlayer;->setAudioTrackState(I)V

    .line 450
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    .line 451
    iput-object p2, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    const/4 p1, 0x0

    .line 452
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    .line 453
    const-string v1, "hls"

    iput-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    .line 454
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUri:Landroid/net/Uri;

    .line 455
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioType:Ljava/lang/String;

    .line 456
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->loopingMediaSource:Z

    .line 457
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    .line 459
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoPlayerReady:Z

    .line 460
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedAudio:Z

    .line 461
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentUri:Landroid/net/Uri;

    const/4 p1, 0x1

    .line 462
    iput-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->isStreaming:Z

    .line 463
    invoke-direct {p0}, Lorg/telegram/ui/Components/VideoPlayer;->ensurePlayerCreated()V

    .line 465
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    const/4 v0, -0x1

    if-eqz p2, :cond_1

    .line 466
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {v1, p2}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result v1

    goto :goto_1

    :cond_1
    :goto_0
    move v1, v0

    :goto_1
    iput v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->selectedQualityIndex:I

    .line 467
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Components/VideoPlayer;->setSelectedQuality(ZLorg/telegram/ui/Components/VideoPlayer$Quality;)V

    .line 468
    iget-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoIsOriginal:Z

    if-eqz p1, :cond_2

    .line 469
    iput v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->selectedQualityIndex:I

    :cond_2
    return-void
.end method

.method public preparePlayerLoop(Landroid/net/Uri;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;)V
    .locals 15

    const/4 v6, 0x0

    .line 330
    invoke-direct {p0, v6}, Lorg/telegram/ui/Components/VideoPlayer;->setAudioTrackState(I)V

    const/4 v1, 0x0

    .line 331
    iput-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    .line 332
    iput-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    move-object/from16 v7, p1

    .line 333
    iput-object v7, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    move-object/from16 v8, p3

    .line 334
    iput-object v8, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUri:Landroid/net/Uri;

    move-object/from16 v9, p2

    .line 335
    iput-object v9, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    move-object/from16 v10, p4

    .line 336
    iput-object v10, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioType:Ljava/lang/String;

    const/4 v11, 0x1

    .line 337
    iput-boolean v11, p0, Lorg/telegram/ui/Components/VideoPlayer;->loopingMediaSource:Z

    .line 338
    iput-boolean v6, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentStreamIsHls:Z

    .line 340
    iput-boolean v11, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedAudio:Z

    .line 341
    iput-boolean v6, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayerReady:Z

    .line 342
    iput-boolean v6, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoPlayerReady:Z

    .line 343
    invoke-direct {p0}, Lorg/telegram/ui/Components/VideoPlayer;->ensurePlayerCreated()V

    move-object v12, v1

    move-object v13, v12

    move v14, v6

    :goto_0
    const/4 v1, 0x2

    if-ge v14, v1, :cond_3

    if-nez v14, :cond_0

    move-object v1, v7

    move-object v4, v9

    goto :goto_1

    :cond_0
    move-object v1, v8

    move-object v4, v10

    :goto_1
    if-nez v14, :cond_1

    move v5, v11

    goto :goto_2

    :cond_1
    move v5, v6

    :goto_2
    const-wide/16 v2, 0x0

    move-object v0, p0

    .line 356
    invoke-direct/range {v0 .. v5}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;Z)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object v1

    .line 357
    new-instance v2, Landroidx/media3/exoplayer/source/LoopingMediaSource;

    invoke-direct {v2, v1}, Landroidx/media3/exoplayer/source/LoopingMediaSource;-><init>(Landroidx/media3/exoplayer/source/MediaSource;)V

    if-nez v14, :cond_2

    move-object v12, v2

    goto :goto_3

    :cond_2
    move-object v13, v2

    :goto_3
    add-int/lit8 v14, v14, 0x1

    goto :goto_0

    .line 364
    :cond_3
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v1, v12, v11}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    .line 365
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v1}, Landroidx/media3/common/Player;->prepare()V

    .line 366
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v1, v13, v11}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    .line 367
    iget-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v1}, Landroidx/media3/common/Player;->prepare()V

    .line 368
    sget-object v1, Lorg/telegram/ui/Components/VideoPlayer;->activePlayers:Ljava/util/HashSet;

    iget v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->playerId:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public releasePlayer(Z)V
    .locals 1

    .line 1458
    sget-object p1, Lorg/telegram/ui/Components/VideoPlayer;->activePlayers:Ljava/util/HashSet;

    iget v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->playerId:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/HashSet;->remove(Ljava/lang/Object;)Z

    .line 1459
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    .line 1460
    invoke-interface {p1}, Landroidx/media3/exoplayer/ExoPlayer;->release()V

    .line 1461
    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    .line 1463
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p1, :cond_1

    .line 1464
    invoke-interface {p1}, Landroidx/media3/exoplayer/ExoPlayer;->release()V

    .line 1465
    iput-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    .line 1467
    :cond_1
    iget-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->shouldPauseOther:Z

    if-eqz p1, :cond_2

    .line 1468
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->playerDidStartPlaying:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 1470
    :cond_2
    sget p0, Lorg/telegram/ui/Components/VideoPlayer;->playerCounter:I

    add-int/lit8 p0, p0, -0x1

    sput p0, Lorg/telegram/ui/Components/VideoPlayer;->playerCounter:I

    return-void
.end method

.method public releasePlayerDeferred(J)V
    .locals 2

    .line 1445
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    .line 1447
    iput-object v1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    .line 1448
    invoke-interface {v0, p0}, Landroidx/media3/exoplayer/ExoPlayer;->removeAnalyticsListener(Landroidx/media3/exoplayer/analytics/AnalyticsListener;)V

    .line 1449
    invoke-interface {v0, p0}, Landroidx/media3/common/Player;->removeListener(Landroidx/media3/common/Player$Listener;)V

    .line 1450
    invoke-interface {v0, p0}, Landroidx/media3/exoplayer/ExoPlayer;->removeVideoListener(Landroidx/media3/common/VideoListener;)V

    .line 1451
    invoke-interface {v0}, Landroidx/media3/common/Player;->stop()V

    .line 1452
    new-instance v1, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda0;

    invoke-direct {v1, v0}, Lorg/telegram/ui/Components/VideoPlayer$$ExternalSyntheticLambda0;-><init>(Landroidx/media3/exoplayer/ExoPlayer;)V

    invoke-static {v1, p1, p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    :cond_0
    const/4 p1, 0x1

    .line 1454
    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->releasePlayer(Z)V

    return-void
.end method

.method public reloadCurrentSource()Z
    .locals 15

    .line 876
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    .line 879
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    .line 880
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualityToSelect:Lorg/telegram/ui/Components/VideoPlayer$Quality;

    invoke-direct {p0, v1, v0}, Lorg/telegram/ui/Components/VideoPlayer;->setSelectedQuality(ZLorg/telegram/ui/Components/VideoPlayer$Quality;)V

    return v2

    .line 883
    :cond_1
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    if-nez v0, :cond_2

    return v1

    .line 887
    :cond_2
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getPlayWhenReady()Z

    move-result v0

    .line 888
    invoke-virtual {p0}, Lorg/telegram/ui/Components/VideoPlayer;->getCurrentPosition()J

    move-result-wide v3

    const-wide/16 v5, 0x0

    invoke-static {v5, v6, v3, v4}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v3

    .line 890
    iget-boolean v7, p0, Lorg/telegram/ui/Components/VideoPlayer;->loopingMediaSource:Z

    if-eqz v7, :cond_4

    iget-object v7, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUri:Landroid/net/Uri;

    if-eqz v7, :cond_4

    iget-object v7, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v7, :cond_4

    .line 891
    new-instance v7, Landroidx/media3/exoplayer/source/LoopingMediaSource;

    iget-object v8, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    iget-wide v9, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentVideoByteOffset:J

    iget-object v11, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    invoke-direct {p0, v8, v9, v10, v11}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object v8

    invoke-direct {v7, v8}, Landroidx/media3/exoplayer/source/LoopingMediaSource;-><init>(Landroidx/media3/exoplayer/source/MediaSource;)V

    .line 892
    new-instance v8, Landroidx/media3/exoplayer/source/LoopingMediaSource;

    iget-object v10, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioUri:Landroid/net/Uri;

    iget-object v13, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioType:Ljava/lang/String;

    const/4 v14, 0x0

    const-wide/16 v11, 0x0

    move-object v9, p0

    invoke-direct/range {v9 .. v14}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;Z)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object p0

    invoke-direct {v8, p0}, Landroidx/media3/exoplayer/source/LoopingMediaSource;-><init>(Landroidx/media3/exoplayer/source/MediaSource;)V

    .line 893
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0, v7, v1}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    .line 894
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0}, Landroidx/media3/common/Player;->prepare()V

    .line 895
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0, v8, v1}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    .line 896
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0}, Landroidx/media3/common/Player;->prepare()V

    cmp-long p0, v3, v5

    if-lez p0, :cond_3

    .line 898
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0, v3, v4}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 899
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0, v3, v4}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 901
    :cond_3
    invoke-virtual {v9, v0}, Lorg/telegram/ui/Components/VideoPlayer;->setPlayWhenReady(Z)V

    .line 902
    sget-object p0, Lorg/telegram/ui/Components/VideoPlayer;->activePlayers:Ljava/util/HashSet;

    iget v0, v9, Lorg/telegram/ui/Components/VideoPlayer;->playerId:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    return v2

    :cond_4
    move-object v9, p0

    .line 906
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-object v7, v9, Lorg/telegram/ui/Components/VideoPlayer;->videoUri:Landroid/net/Uri;

    iget-wide v10, v9, Lorg/telegram/ui/Components/VideoPlayer;->currentVideoByteOffset:J

    iget-object v8, v9, Lorg/telegram/ui/Components/VideoPlayer;->videoType:Ljava/lang/String;

    invoke-direct {v9, v7, v10, v11, v8}, Lorg/telegram/ui/Components/VideoPlayer;->mediaSourceFromUri(Landroid/net/Uri;JLjava/lang/String;)Landroidx/media3/exoplayer/source/MediaSource;

    move-result-object v7

    invoke-interface {p0, v7, v1}, Landroidx/media3/exoplayer/ExoPlayer;->setMediaSource(Landroidx/media3/exoplayer/source/MediaSource;Z)V

    .line 907
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0}, Landroidx/media3/common/Player;->prepare()V

    cmp-long p0, v3, v5

    if-lez p0, :cond_5

    .line 909
    iget-object p0, v9, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0, v3, v4}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 911
    :cond_5
    invoke-virtual {v9, v0}, Lorg/telegram/ui/Components/VideoPlayer;->setPlayWhenReady(Z)V

    .line 912
    sget-object p0, Lorg/telegram/ui/Components/VideoPlayer;->activePlayers:Ljava/util/HashSet;

    iget v0, v9, Lorg/telegram/ui/Components/VideoPlayer;->playerId:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    return v2
.end method

.method public seekTo(J)V
    .locals 1

    const/4 v0, 0x0

    .line 1675
    invoke-virtual {p0, p1, p2, v0}, Lorg/telegram/ui/Components/VideoPlayer;->seekTo(JZ)V

    return-void
.end method

.method public seekTo(JZ)V
    .locals 1

    .line 1679
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_1

    if-eqz p3, :cond_0

    .line 1680
    sget-object p3, Landroidx/media3/exoplayer/SeekParameters;->CLOSEST_SYNC:Landroidx/media3/exoplayer/SeekParameters;

    goto :goto_0

    :cond_0
    sget-object p3, Landroidx/media3/exoplayer/SeekParameters;->EXACT:Landroidx/media3/exoplayer/SeekParameters;

    :goto_0
    invoke-interface {v0, p3}, Landroidx/media3/exoplayer/ExoPlayer;->setSeekParameters(Landroidx/media3/exoplayer/SeekParameters;)V

    .line 1681
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0, p1, p2}, Landroidx/media3/common/Player;->seekTo(J)V

    :cond_1
    return-void
.end method

.method public seekTo(JZLjava/lang/Runnable;)V
    .locals 1

    .line 1686
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_2

    if-eqz p4, :cond_0

    .line 1688
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->seekFinishedListeners:Ljava/util/ArrayList;

    invoke-virtual {v0, p4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 1690
    :cond_0
    iget-object p4, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p3, :cond_1

    sget-object p3, Landroidx/media3/exoplayer/SeekParameters;->CLOSEST_SYNC:Landroidx/media3/exoplayer/SeekParameters;

    goto :goto_0

    :cond_1
    sget-object p3, Landroidx/media3/exoplayer/SeekParameters;->EXACT:Landroidx/media3/exoplayer/SeekParameters;

    :goto_0
    invoke-interface {p4, p3}, Landroidx/media3/exoplayer/ExoPlayer;->setSeekParameters(Landroidx/media3/exoplayer/SeekParameters;)V

    .line 1691
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p0, p1, p2}, Landroidx/media3/common/Player;->seekTo(J)V

    :cond_2
    return-void
.end method

.method public setAudioVisualizerDelegate(Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;)V
    .locals 0

    .line 1738
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioVisualizerDelegate:Lorg/telegram/ui/Components/VideoPlayer$AudioVisualizerDelegate;

    return-void
.end method

.method public setDelegate(Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;)V
    .locals 0

    .line 1716
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->delegate:Lorg/telegram/ui/Components/VideoPlayer$VideoPlayerDelegate;

    return-void
.end method

.method public setExternalSubtitle(Lorg/telegram/ui/Components/VideoPlayer$ExternalSubtitle;)V
    .locals 0

    .line 872
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->currentExternalSubtitle:Lorg/telegram/ui/Components/VideoPlayer$ExternalSubtitle;

    return-void
.end method

.method public setIsStory()V
    .locals 1

    const/4 v0, 0x1

    .line 2130
    iput-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->isStory:Z

    return-void
.end method

.method public setLooping(Z)V
    .locals 1

    .line 1784
    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->looping:Z

    if-eq v0, p1, :cond_1

    .line 1785
    iput-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->looping:Z

    .line 1786
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_1

    if-eqz p1, :cond_0

    const/4 p1, 0x2

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    .line 1787
    :goto_0
    invoke-interface {p0, p1}, Landroidx/media3/common/Player;->setRepeatMode(I)V

    :cond_1
    return-void
.end method

.method public setMute(Z)V
    .locals 4

    .line 1645
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/high16 v1, 0x3f800000    # 1.0f

    const/4 v2, 0x0

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    move v3, v2

    goto :goto_0

    :cond_0
    move v3, v1

    .line 1646
    :goto_0
    invoke-interface {v0, v3}, Landroidx/media3/common/Player;->setVolume(F)V

    .line 1648
    :cond_1
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_3

    if-eqz p1, :cond_2

    move v1, v2

    .line 1649
    :cond_2
    invoke-interface {p0, v1}, Landroidx/media3/common/Player;->setVolume(F)V

    :cond_3
    return-void
.end method

.method public setOnQualityChangeListener(Ljava/lang/Runnable;)V
    .locals 0

    .line 868
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->onQualityChangeListener:Ljava/lang/Runnable;

    return-void
.end method

.method public setPlayWhenReady(Z)V
    .locals 1

    .line 1605
    iput-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedPlayWhenReady:Z

    if-eqz p1, :cond_2

    .line 1606
    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->mixedAudio:Z

    if-eqz v0, :cond_2

    .line 1607
    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayerReady:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoPlayerReady:Z

    if-nez v0, :cond_2

    .line 1608
    :cond_0
    iget-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    .line 1609
    invoke-interface {p1, v0}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    .line 1611
    :cond_1
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_4

    .line 1612
    invoke-interface {p0, v0}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    return-void

    .line 1617
    :cond_2
    iput-boolean p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->autoplay:Z

    .line 1618
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_3

    .line 1619
    invoke-interface {v0, p1}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    .line 1621
    :cond_3
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_4

    .line 1622
    invoke-interface {p0, p1}, Landroidx/media3/common/Player;->setPlayWhenReady(Z)V

    :cond_4
    return-void
.end method

.method public setPlaybackSpeed(F)V
    .locals 3

    .line 1591
    :try_start_0
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_1

    .line 1592
    new-instance v0, Landroidx/media3/common/PlaybackParameters;

    const/high16 v1, 0x3f800000    # 1.0f

    cmpl-float v2, p1, v1

    if-lez v2, :cond_0

    const v1, 0x3f7ae148    # 0.98f

    :cond_0
    invoke-direct {v0, p1, v1}, Landroidx/media3/common/PlaybackParameters;-><init>(FF)V

    invoke-interface {p0, v0}, Landroidx/media3/common/Player;->setPlaybackParameters(Landroidx/media3/common/PlaybackParameters;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :cond_1
    return-void
.end method

.method public setSelectedQuality(I)V
    .locals 1

    .line 768
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    goto :goto_1

    .line 769
    :cond_0
    iget v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->selectedQualityIndex:I

    if-eq p1, v0, :cond_2

    .line 770
    iput p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->selectedQualityIndex:I

    .line 772
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    if-eqz v0, :cond_1

    if-ltz p1, :cond_1

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge p1, v0, :cond_1

    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->videoQualities:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/ui/Components/VideoPlayer$Quality;

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    :goto_0
    const/4 v0, 0x0

    .line 773
    invoke-direct {p0, v0, p1}, Lorg/telegram/ui/Components/VideoPlayer;->setSelectedQuality(ZLorg/telegram/ui/Components/VideoPlayer$Quality;)V

    :cond_2
    :goto_1
    return-void
.end method

.method public setStreamType(I)V
    .locals 5

    .line 1771
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 v1, 0x2

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    .line 1772
    new-instance v3, Landroidx/media3/common/AudioAttributes$Builder;

    invoke-direct {v3}, Landroidx/media3/common/AudioAttributes$Builder;-><init>()V

    if-nez p1, :cond_0

    move v4, v1

    goto :goto_0

    :cond_0
    move v4, v2

    .line 1773
    :goto_0
    invoke-virtual {v3, v4}, Landroidx/media3/common/AudioAttributes$Builder;->setUsage(I)Landroidx/media3/common/AudioAttributes$Builder;

    move-result-object v3

    .line 1774
    invoke-virtual {v3}, Landroidx/media3/common/AudioAttributes$Builder;->build()Landroidx/media3/common/AudioAttributes;

    move-result-object v3

    iget-boolean v4, p0, Lorg/telegram/ui/Components/VideoPlayer;->handleAudioFocus:Z

    .line 1772
    invoke-interface {v0, v3, v4}, Landroidx/media3/common/Player;->setAudioAttributes(Landroidx/media3/common/AudioAttributes;Z)V

    .line 1776
    :cond_1
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_3

    .line 1777
    new-instance v0, Landroidx/media3/common/AudioAttributes$Builder;

    invoke-direct {v0}, Landroidx/media3/common/AudioAttributes$Builder;-><init>()V

    if-nez p1, :cond_2

    goto :goto_1

    :cond_2
    move v1, v2

    .line 1778
    :goto_1
    invoke-virtual {v0, v1}, Landroidx/media3/common/AudioAttributes$Builder;->setUsage(I)Landroidx/media3/common/AudioAttributes$Builder;

    move-result-object p1

    .line 1779
    invoke-virtual {p1}, Landroidx/media3/common/AudioAttributes$Builder;->build()Landroidx/media3/common/AudioAttributes;

    move-result-object p1

    .line 1777
    invoke-interface {p0, p1, v2}, Landroidx/media3/common/Player;->setAudioAttributes(Landroidx/media3/common/AudioAttributes;Z)V

    :cond_3
    return-void
.end method

.method public setSurface(Landroid/view/Surface;)V
    .locals 1

    .line 1531
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->surface:Landroid/view/Surface;

    if-ne v0, p1, :cond_0

    goto :goto_0

    .line 1534
    :cond_0
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->surface:Landroid/view/Surface;

    .line 1535
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez p0, :cond_1

    :goto_0
    return-void

    .line 1538
    :cond_1
    invoke-interface {p0, p1}, Landroidx/media3/common/Player;->setVideoSurface(Landroid/view/Surface;)V

    return-void
.end method

.method public setSurfaceView(Landroid/view/SurfaceView;)V
    .locals 1

    .line 1520
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->surfaceView:Landroid/view/SurfaceView;

    if-ne v0, p1, :cond_0

    goto :goto_0

    .line 1523
    :cond_0
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->surfaceView:Landroid/view/SurfaceView;

    .line 1524
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez p0, :cond_1

    :goto_0
    return-void

    .line 1527
    :cond_1
    invoke-interface {p0, p1}, Landroidx/media3/common/Player;->setVideoSurfaceView(Landroid/view/SurfaceView;)V

    return-void
.end method

.method public setTextureView(Landroid/view/TextureView;)V
    .locals 1

    .line 1509
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    if-ne v0, p1, :cond_0

    goto :goto_0

    .line 1512
    :cond_0
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->textureView:Landroid/view/TextureView;

    .line 1513
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez p0, :cond_1

    :goto_0
    return-void

    .line 1516
    :cond_1
    invoke-interface {p0, p1}, Landroidx/media3/common/Player;->setVideoTextureView(Landroid/view/TextureView;)V

    return-void
.end method

.method public setVolume(F)V
    .locals 1

    .line 1659
    iget-object v0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v0, :cond_0

    .line 1660
    invoke-interface {v0, p1}, Landroidx/media3/common/Player;->setVolume(F)V

    .line 1662
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->audioPlayer:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p0, :cond_1

    .line 1663
    invoke-interface {p0, p1}, Landroidx/media3/common/Player;->setVolume(F)V

    :cond_1
    return-void
.end method

.method public setWorkerQueue(Lorg/telegram/messenger/DispatchQueue;)V
    .locals 1

    .line 2121
    iput-object p1, p0, Lorg/telegram/ui/Components/VideoPlayer;->workerQueue:Lorg/telegram/messenger/DispatchQueue;

    .line 2125
    iget-object p0, p0, Lorg/telegram/ui/Components/VideoPlayer;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz p1, :cond_0

    .line 2123
    new-instance v0, Lorg/telegram/messenger/ChatThemeController$$ExternalSyntheticLambda6;

    invoke-direct {v0, p1}, Lorg/telegram/messenger/ChatThemeController$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/messenger/DispatchQueue;)V

    invoke-interface {p0, v0}, Landroidx/media3/exoplayer/ExoPlayer;->setWorkerQueue(Ljava/util/concurrent/Executor;)V

    return-void

    :cond_0
    const/4 p1, 0x0

    .line 2125
    invoke-interface {p0, p1}, Landroidx/media3/exoplayer/ExoPlayer;->setWorkerQueue(Ljava/util/concurrent/Executor;)V

    return-void
.end method
