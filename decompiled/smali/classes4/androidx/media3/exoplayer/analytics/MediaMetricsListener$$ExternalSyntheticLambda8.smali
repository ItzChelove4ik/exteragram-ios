.class public final synthetic Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda8;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroidx/media3/exoplayer/analytics/MediaMetricsListener;

.field public final synthetic f$1:Landroid/media/metrics/NetworkEvent;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/exoplayer/analytics/MediaMetricsListener;Landroid/media/metrics/NetworkEvent;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda8;->f$0:Landroidx/media3/exoplayer/analytics/MediaMetricsListener;

    iput-object p2, p0, Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda8;->f$1:Landroid/media/metrics/NetworkEvent;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda8;->f$0:Landroidx/media3/exoplayer/analytics/MediaMetricsListener;

    iget-object p0, p0, Landroidx/media3/exoplayer/analytics/MediaMetricsListener$$ExternalSyntheticLambda8;->f$1:Landroid/media/metrics/NetworkEvent;

    invoke-static {v0, p0}, Landroidx/media3/exoplayer/analytics/MediaMetricsListener;->$r8$lambda$Z90VT2CXYz5ZKj6hhHP5A-mdjkg(Landroidx/media3/exoplayer/analytics/MediaMetricsListener;Landroid/media/metrics/NetworkEvent;)V

    return-void
.end method
