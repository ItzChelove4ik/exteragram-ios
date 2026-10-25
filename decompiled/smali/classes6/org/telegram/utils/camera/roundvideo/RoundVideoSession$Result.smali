.class public final Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Result"
.end annotation


# instance fields
.field private final durationMs:J

.field private final file:Ljava/io/File;

.field private final frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field private final hasAudio:Z

.field private final outputId:J


# direct methods
.method private constructor <init>(JLjava/io/File;JZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;)V
    .locals 0

    .line 470
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 471
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->outputId:J

    .line 472
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->file:Ljava/io/File;

    .line 473
    iput-wide p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->durationMs:J

    .line 474
    iput-boolean p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->hasAudio:Z

    .line 475
    iput-object p7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-void
.end method

.method public synthetic constructor <init>(JLjava/io/File;JZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession-IA;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;-><init>(JLjava/io/File;JZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;)V

    return-void
.end method


# virtual methods
.method public getDurationMs()J
    .locals 2

    .line 485
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->durationMs:J

    return-wide v0
.end method

.method public getFile()Ljava/io/File;
    .locals 0

    .line 482
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->file:Ljava/io/File;

    return-object p0
.end method

.method public getOutputId()J
    .locals 2

    .line 479
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->outputId:J

    return-wide v0
.end method

.method public hasAudio()Z
    .locals 0

    .line 488
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;->hasAudio:Z

    return p0
.end method
