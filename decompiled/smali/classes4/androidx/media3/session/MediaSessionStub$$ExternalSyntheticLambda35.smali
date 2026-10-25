.class public final synthetic Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda35;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/util/Consumer;


# instance fields
.field public final synthetic f$0:Landroidx/media3/session/MediaSessionStub;

.field public final synthetic f$1:Landroidx/media3/session/MediaSession$ControllerInfo;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/session/MediaSessionStub;Landroidx/media3/session/MediaSession$ControllerInfo;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda35;->f$0:Landroidx/media3/session/MediaSessionStub;

    iput-object p2, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda35;->f$1:Landroidx/media3/session/MediaSession$ControllerInfo;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda35;->f$0:Landroidx/media3/session/MediaSessionStub;

    iget-object p0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda35;->f$1:Landroidx/media3/session/MediaSession$ControllerInfo;

    check-cast p1, Landroidx/media3/session/PlayerWrapper;

    invoke-static {v0, p0, p1}, Landroidx/media3/session/MediaSessionStub;->$r8$lambda$wMocS4AH4xsCwAU3JbOpgcmZu-g(Landroidx/media3/session/MediaSessionStub;Landroidx/media3/session/MediaSession$ControllerInfo;Landroidx/media3/session/PlayerWrapper;)V

    return-void
.end method
