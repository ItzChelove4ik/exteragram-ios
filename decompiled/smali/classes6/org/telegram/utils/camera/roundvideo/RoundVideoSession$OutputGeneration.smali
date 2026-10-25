.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "OutputGeneration"
.end annotation


# instance fields
.field volatile availableSize:J

.field volatile completed:Z

.field final file:Ljava/io/File;

.field final id:J

.field volatile invalidated:Z


# direct methods
.method public constructor <init>(JLjava/io/File;)V
    .locals 0

    .line 1542
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1543
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    .line 1544
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->file:Ljava/io/File;

    return-void
.end method
