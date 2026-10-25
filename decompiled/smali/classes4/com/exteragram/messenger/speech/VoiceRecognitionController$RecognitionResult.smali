.class public Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionResult;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/speech/VoiceRecognitionController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "RecognitionResult"
.end annotation


# instance fields
.field private final text:Ljava/lang/String;

.field private final timestamp:J


# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 2

    .line 418
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 419
    iput-object p1, p0, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionResult;->text:Ljava/lang/String;

    .line 420
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionResult;->timestamp:J

    return-void
.end method
