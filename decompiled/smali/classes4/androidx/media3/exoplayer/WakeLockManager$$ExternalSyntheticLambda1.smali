.class public final synthetic Landroidx/media3/exoplayer/WakeLockManager$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroidx/media3/exoplayer/WakeLockManager;

.field public final synthetic f$1:Z

.field public final synthetic f$2:Z


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/exoplayer/WakeLockManager;ZZ)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/exoplayer/WakeLockManager$$ExternalSyntheticLambda1;->f$0:Landroidx/media3/exoplayer/WakeLockManager;

    iput-boolean p2, p0, Landroidx/media3/exoplayer/WakeLockManager$$ExternalSyntheticLambda1;->f$1:Z

    iput-boolean p3, p0, Landroidx/media3/exoplayer/WakeLockManager$$ExternalSyntheticLambda1;->f$2:Z

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Landroidx/media3/exoplayer/WakeLockManager$$ExternalSyntheticLambda1;->f$0:Landroidx/media3/exoplayer/WakeLockManager;

    iget-boolean v1, p0, Landroidx/media3/exoplayer/WakeLockManager$$ExternalSyntheticLambda1;->f$1:Z

    iget-boolean p0, p0, Landroidx/media3/exoplayer/WakeLockManager$$ExternalSyntheticLambda1;->f$2:Z

    invoke-static {v0, v1, p0}, Landroidx/media3/exoplayer/WakeLockManager;->$r8$lambda$Je_LlpXVeFIMb6podN33_FfRN0s(Landroidx/media3/exoplayer/WakeLockManager;ZZ)V

    return-void
.end method
