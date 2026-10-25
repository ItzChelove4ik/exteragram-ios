.class public final synthetic Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda60;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/util/Consumer;


# instance fields
.field public final synthetic f$0:Landroidx/media3/common/MediaMetadata;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/common/MediaMetadata;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda60;->f$0:Landroidx/media3/common/MediaMetadata;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda60;->f$0:Landroidx/media3/common/MediaMetadata;

    check-cast p1, Landroidx/media3/session/PlayerWrapper;

    invoke-static {p0, p1}, Landroidx/media3/session/MediaSessionStub;->$r8$lambda$0sp5GeU0WzI_qEuAy4ySukcYQuA(Landroidx/media3/common/MediaMetadata;Landroidx/media3/session/PlayerWrapper;)V

    return-void
.end method
