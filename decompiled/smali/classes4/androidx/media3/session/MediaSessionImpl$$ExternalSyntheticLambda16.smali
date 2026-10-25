.class public final synthetic Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroidx/media3/session/MediaSessionImpl;

.field public final synthetic f$1:Z

.field public final synthetic f$2:Landroidx/media3/session/MediaSession$ControllerInfo;

.field public final synthetic f$3:Ljava/lang/Runnable;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/session/MediaSessionImpl;ZLandroidx/media3/session/MediaSession$ControllerInfo;Ljava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;->f$0:Landroidx/media3/session/MediaSessionImpl;

    iput-boolean p2, p0, Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;->f$1:Z

    iput-object p3, p0, Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;->f$2:Landroidx/media3/session/MediaSession$ControllerInfo;

    iput-object p4, p0, Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;->f$3:Ljava/lang/Runnable;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;->f$0:Landroidx/media3/session/MediaSessionImpl;

    iget-boolean v1, p0, Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;->f$1:Z

    iget-object v2, p0, Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;->f$2:Landroidx/media3/session/MediaSession$ControllerInfo;

    iget-object p0, p0, Landroidx/media3/session/MediaSessionImpl$$ExternalSyntheticLambda16;->f$3:Ljava/lang/Runnable;

    invoke-static {v0, v1, v2, p0}, Landroidx/media3/session/MediaSessionImpl;->$r8$lambda$6vLrA69rUhTS0iG4bgjxSBvDRAQ(Landroidx/media3/session/MediaSessionImpl;ZLandroidx/media3/session/MediaSession$ControllerInfo;Ljava/lang/Runnable;)V

    return-void
.end method
