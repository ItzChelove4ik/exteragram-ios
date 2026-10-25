.class public final synthetic Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda13;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroidx/media3/session/MediaSessionStub;

.field public final synthetic f$1:Landroidx/media3/session/IMediaController;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/session/MediaSessionStub;Landroidx/media3/session/IMediaController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda13;->f$0:Landroidx/media3/session/MediaSessionStub;

    iput-object p2, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda13;->f$1:Landroidx/media3/session/IMediaController;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda13;->f$0:Landroidx/media3/session/MediaSessionStub;

    iget-object p0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda13;->f$1:Landroidx/media3/session/IMediaController;

    invoke-static {v0, p0}, Landroidx/media3/session/MediaSessionStub;->$r8$lambda$1vN0gC2ZaTovIExjX5xTnwlbne8(Landroidx/media3/session/MediaSessionStub;Landroidx/media3/session/IMediaController;)V

    return-void
.end method
