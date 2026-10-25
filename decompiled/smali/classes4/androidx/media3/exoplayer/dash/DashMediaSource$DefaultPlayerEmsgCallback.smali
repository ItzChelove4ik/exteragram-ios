.class final Landroidx/media3/exoplayer/dash/DashMediaSource$DefaultPlayerEmsgCallback;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/exoplayer/dash/PlayerEmsgHandler$PlayerEmsgCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/exoplayer/dash/DashMediaSource;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x11
    name = "DefaultPlayerEmsgCallback"
.end annotation


# instance fields
.field final synthetic this$0:Landroidx/media3/exoplayer/dash/DashMediaSource;


# direct methods
.method private constructor <init>(Landroidx/media3/exoplayer/dash/DashMediaSource;)V
    .locals 0

    .line 1435
    iput-object p1, p0, Landroidx/media3/exoplayer/dash/DashMediaSource$DefaultPlayerEmsgCallback;->this$0:Landroidx/media3/exoplayer/dash/DashMediaSource;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Landroidx/media3/exoplayer/dash/DashMediaSource;Landroidx/media3/exoplayer/dash/DashMediaSource$1;)V
    .locals 0

    .line 1435
    invoke-direct {p0, p1}, Landroidx/media3/exoplayer/dash/DashMediaSource$DefaultPlayerEmsgCallback;-><init>(Landroidx/media3/exoplayer/dash/DashMediaSource;)V

    return-void
.end method


# virtual methods
.method public onDashManifestPublishTimeExpired(J)V
    .locals 0

    .line 1444
    iget-object p0, p0, Landroidx/media3/exoplayer/dash/DashMediaSource$DefaultPlayerEmsgCallback;->this$0:Landroidx/media3/exoplayer/dash/DashMediaSource;

    invoke-virtual {p0, p1, p2}, Landroidx/media3/exoplayer/dash/DashMediaSource;->onDashManifestPublishTimeExpired(J)V

    return-void
.end method

.method public onDashManifestRefreshRequested()V
    .locals 0

    .line 1439
    iget-object p0, p0, Landroidx/media3/exoplayer/dash/DashMediaSource$DefaultPlayerEmsgCallback;->this$0:Landroidx/media3/exoplayer/dash/DashMediaSource;

    invoke-virtual {p0}, Landroidx/media3/exoplayer/dash/DashMediaSource;->onDashManifestRefreshRequested()V

    return-void
.end method
