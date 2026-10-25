.class Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;


# direct methods
.method public static synthetic $r8$lambda$ItmFGDw5OkrDrtHygUF9YgfMCwc(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->lambda$onError$2(Ljava/lang/Exception;)V

    return-void
.end method

.method public static synthetic $r8$lambda$_4dJfQvAS6FobjN0vqbmQo3zSMU(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$mhandleRecordingStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    return-void
.end method

.method public static synthetic $r8$lambda$iMiBbiM_nkZoK-GCXxlfYT5pidQ(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->lambda$onConfigured$0(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$kKYFSfscYIzexiEky3RXUdmYbng(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->lambda$onCameraSwitchStarted$1(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    return-void
.end method

.method public static synthetic $r8$lambda$tjIIOPNxMhgHG35MEKIarpsBCw8(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$mhandleRecordingStopped(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 880
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private synthetic lambda$onCameraSwitchStarted$1(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 3

    .line 897
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetcameraSwitchCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)I

    move-result v1

    const/4 v2, 0x1

    add-int/2addr v1, v2

    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fputcameraSwitchCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;I)V

    .line 898
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fputcameraSwitching(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Z)V

    .line 899
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "camera switch started: target="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 900
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetlistener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    move-result-object v0

    invoke-interface {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onCameraSwitchStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    .line 901
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$mnotifyState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    return-void
.end method

.method private synthetic lambda$onConfigured$0(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V
    .locals 0

    .line 886
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$mhandleCameraConfigured(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V

    return-void
.end method

.method private synthetic lambda$onError$2(Ljava/lang/Exception;)V
    .locals 0

    .line 917
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$mfail(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V

    return-void
.end method


# virtual methods
.method public onCameraSwitchFirstFrame()V
    .locals 2

    .line 907
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetmainHandler(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroid/os/Handler;

    move-result-object v0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetlistener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public onCameraSwitchStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 2

    .line 896
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetmainHandler(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda2;

    invoke-direct {v1, p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public onConfigured(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V
    .locals 2

    .line 886
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetmainHandler(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public onError(Ljava/lang/Exception;)V
    .locals 2

    .line 917
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetmainHandler(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;Ljava/lang/Exception;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public onRecordingStarted()V
    .locals 2

    .line 891
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetmainHandler(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroid/os/Handler;

    move-result-object v0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public onRecordingStopped()V
    .locals 2

    .line 912
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetmainHandler(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroid/os/Handler;

    move-result-object v0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method
