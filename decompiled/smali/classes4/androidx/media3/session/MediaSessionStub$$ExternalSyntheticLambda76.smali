.class public final synthetic Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda76;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/util/Consumer;


# instance fields
.field public final synthetic f$0:Landroidx/media3/session/MediaSession$ControllerInfo;

.field public final synthetic f$1:I


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/session/MediaSession$ControllerInfo;I)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda76;->f$0:Landroidx/media3/session/MediaSession$ControllerInfo;

    iput p2, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda76;->f$1:I

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda76;->f$0:Landroidx/media3/session/MediaSession$ControllerInfo;

    iget p0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda76;->f$1:I

    check-cast p1, Lcom/google/common/util/concurrent/ListenableFuture;

    invoke-static {v0, p0, p1}, Landroidx/media3/session/MediaSessionStub;->$r8$lambda$2isH75fLe4J0Dtj3oqE-3ggOXeg(Landroidx/media3/session/MediaSession$ControllerInfo;ILcom/google/common/util/concurrent/ListenableFuture;)V

    return-void
.end method
