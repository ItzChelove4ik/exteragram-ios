.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda14;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$ErrorListener;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda14;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    return-void
.end method


# virtual methods
.method public final onError(Ljava/lang/Exception;)V
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda14;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    invoke-interface {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;->onError(Ljava/lang/Exception;)V

    return-void
.end method
