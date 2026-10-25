.class public abstract Lorg/telegram/utils/settings/SharedSettings;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final experimentalSettingsAllowed:Lorg/telegram/utils/settings/BooleanSetting;

.field public static final roundVideoCamera2Enabled:Lorg/telegram/utils/settings/BooleanSetting;

.field public static final roundVideoCameraResolution:Lorg/telegram/utils/settings/EnumSetting;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lorg/telegram/utils/settings/EnumSetting<",
            "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;",
            ">;"
        }
    .end annotation
.end field

.field public static final roundVideoComposition:Lorg/telegram/utils/settings/BooleanSetting;

.field public static final roundVideoFrameRate:Lorg/telegram/utils/settings/EnumSetting;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lorg/telegram/utils/settings/EnumSetting<",
            "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;",
            ">;"
        }
    .end annotation
.end field

.field public static final roundVideoLastCamera:Lorg/telegram/utils/settings/EnumSetting;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lorg/telegram/utils/settings/EnumSetting<",
            "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;",
            ">;"
        }
    .end annotation
.end field

.field public static final roundVideoOutputResolution:Lorg/telegram/utils/settings/EnumSetting;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lorg/telegram/utils/settings/EnumSetting<",
            "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;",
            ">;"
        }
    .end annotation
.end field

.field public static final roundVideoVideoBitrate:Lorg/telegram/utils/settings/IntSetting;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 9
    const-string v0, "experimental_settings_allowed"

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lorg/telegram/utils/settings/BooleanSetting;->of(Ljava/lang/String;Z)Lorg/telegram/utils/settings/BooleanSetting;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SharedSettings;->experimentalSettingsAllowed:Lorg/telegram/utils/settings/BooleanSetting;

    .line 11
    const-string v0, "round_video_camera2_enabled"

    .line 12
    invoke-static {v0, v1}, Lorg/telegram/utils/settings/BooleanSetting;->of(Ljava/lang/String;Z)Lorg/telegram/utils/settings/BooleanSetting;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SharedSettings;->roundVideoCamera2Enabled:Lorg/telegram/utils/settings/BooleanSetting;

    .line 14
    const-string v0, "round_video_output_resolution"

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->P480:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    .line 15
    invoke-static {v0, v1}, Lorg/telegram/utils/settings/EnumSetting;->of(Ljava/lang/String;Ljava/lang/Enum;)Lorg/telegram/utils/settings/EnumSetting;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SharedSettings;->roundVideoOutputResolution:Lorg/telegram/utils/settings/EnumSetting;

    .line 17
    const-string v0, "round_video_camera_resolution"

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->HIGH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 18
    invoke-static {v0, v1}, Lorg/telegram/utils/settings/EnumSetting;->of(Ljava/lang/String;Ljava/lang/Enum;)Lorg/telegram/utils/settings/EnumSetting;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SharedSettings;->roundVideoCameraResolution:Lorg/telegram/utils/settings/EnumSetting;

    .line 20
    const-string v0, "round_video_frame_rate"

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 21
    invoke-static {v0, v1}, Lorg/telegram/utils/settings/EnumSetting;->of(Ljava/lang/String;Ljava/lang/Enum;)Lorg/telegram/utils/settings/EnumSetting;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SharedSettings;->roundVideoFrameRate:Lorg/telegram/utils/settings/EnumSetting;

    .line 23
    const-string v0, "round_video_video_bitrate"

    const v1, 0xf4240

    .line 24
    invoke-static {v0, v1}, Lorg/telegram/utils/settings/IntSetting;->of(Ljava/lang/String;I)Lorg/telegram/utils/settings/IntSetting;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SharedSettings;->roundVideoVideoBitrate:Lorg/telegram/utils/settings/IntSetting;

    .line 26
    const-string v0, "round_video_composition"

    const/4 v1, 0x1

    .line 27
    invoke-static {v0, v1}, Lorg/telegram/utils/settings/BooleanSetting;->of(Ljava/lang/String;Z)Lorg/telegram/utils/settings/BooleanSetting;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SharedSettings;->roundVideoComposition:Lorg/telegram/utils/settings/BooleanSetting;

    .line 29
    const-string v0, "round_video_last_camera"

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 30
    invoke-static {v0, v1}, Lorg/telegram/utils/settings/EnumSetting;->of(Ljava/lang/String;Ljava/lang/Enum;)Lorg/telegram/utils/settings/EnumSetting;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/settings/SharedSettings;->roundVideoLastCamera:Lorg/telegram/utils/settings/EnumSetting;

    return-void
.end method
