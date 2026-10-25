.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Result"
.end annotation


# instance fields
.field final durationMs:J

.field final startMs:J


# direct methods
.method public constructor <init>(JJ)V
    .locals 0

    .line 20
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 21
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;->startMs:J

    .line 22
    iput-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;->durationMs:J

    return-void
.end method
