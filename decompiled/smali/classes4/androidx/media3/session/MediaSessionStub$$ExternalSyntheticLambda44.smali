.class public final synthetic Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda44;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/session/MediaSessionStub$SessionTask;


# instance fields
.field public final synthetic f$0:Ljava/lang/String;

.field public final synthetic f$1:Landroidx/media3/common/Rating;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Landroidx/media3/common/Rating;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda44;->f$0:Ljava/lang/String;

    iput-object p2, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda44;->f$1:Landroidx/media3/common/Rating;

    return-void
.end method


# virtual methods
.method public final run(Landroidx/media3/session/MediaSessionImpl;Landroidx/media3/session/MediaSession$ControllerInfo;I)Ljava/lang/Object;
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda44;->f$0:Ljava/lang/String;

    iget-object p0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda44;->f$1:Landroidx/media3/common/Rating;

    invoke-static {v0, p0, p1, p2, p3}, Landroidx/media3/session/MediaSessionStub;->$r8$lambda$8VI4pJat_48yTFY_991wSULRUdw(Ljava/lang/String;Landroidx/media3/common/Rating;Landroidx/media3/session/MediaSessionImpl;Landroidx/media3/session/MediaSession$ControllerInfo;I)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
