.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda5;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda5;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    invoke-interface {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onCameraSwitchFirstFrame()V

    return-void
.end method
