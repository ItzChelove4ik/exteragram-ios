.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

.field public final synthetic f$1:Landroid/os/HandlerThread;

.field public final synthetic f$2:Ljava/util/concurrent/CountDownLatch;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/os/HandlerThread;Ljava/util/concurrent/CountDownLatch;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;->f$1:Landroid/os/HandlerThread;

    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;->f$2:Ljava/util/concurrent/CountDownLatch;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;->f$1:Landroid/os/HandlerThread;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;->f$2:Ljava/util/concurrent/CountDownLatch;

    invoke-static {v0, v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->$r8$lambda$rt7ow4nR2CaQWIcuh_2PxTqRnOM(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/os/HandlerThread;Ljava/util/concurrent/CountDownLatch;)V

    return-void
.end method
