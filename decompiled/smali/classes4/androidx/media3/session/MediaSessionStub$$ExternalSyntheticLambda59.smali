.class public final synthetic Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda59;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/util/Consumer;


# instance fields
.field public final synthetic f$0:Z

.field public final synthetic f$1:I


# direct methods
.method public synthetic constructor <init>(ZI)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-boolean p1, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda59;->f$0:Z

    iput p2, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda59;->f$1:I

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 1

    .line 0
    iget-boolean v0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda59;->f$0:Z

    iget p0, p0, Landroidx/media3/session/MediaSessionStub$$ExternalSyntheticLambda59;->f$1:I

    check-cast p1, Landroidx/media3/session/PlayerWrapper;

    invoke-static {v0, p0, p1}, Landroidx/media3/session/MediaSessionStub;->$r8$lambda$DZ1ZMTWdb6K-hKSLz3T89Fewt48(ZILandroidx/media3/session/PlayerWrapper;)V

    return-void
.end method
