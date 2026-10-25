.class public final synthetic Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda10;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroidx/media3/exoplayer/analytics/MediaMetricsListener;

.field public final synthetic f$1:Landroid/media/metrics/PlaybackMetrics;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/exoplayer/analytics/MediaMetricsListener;Landroid/media/metrics/PlaybackMetrics;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda10;->f$0:Landroidx/media3/exoplayer/analytics/MediaMetricsListener;

    iput-object p2, p0, Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda10;->f$1:Landroid/media/metrics/PlaybackMetrics;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda10;->f$0:Landroidx/media3/exoplayer/analytics/MediaMetricsListener;

    iget-object p0, p0, Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda10;->f$1:Landroid/media/metrics/PlaybackMetrics;

    invoke-static {v0, p0}, Landroidx/media3/exoplayer/analytics/MediaMetricsListener;->$r8$lambda$Ln4o32G-snHjxLveqPrb6nd4DFU(Landroidx/media3/exoplayer/analytics/MediaMetricsListener;Landroid/media/metrics/PlaybackMetrics;)V

    return-void
.end method
