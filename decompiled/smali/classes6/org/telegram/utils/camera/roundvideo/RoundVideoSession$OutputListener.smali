.class public interface abstract Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "OutputListener"
.end annotation


# virtual methods
.method public abstract onBytesAvailable(JLjava/io/File;JJ)V
.end method

.method public abstract onOutputCompleted(JLjava/io/File;JJZ)V
.end method

.method public abstract onOutputError(JLjava/lang/Exception;)V
.end method

.method public abstract onOutputInvalidated(JLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V
.end method

.method public abstract onOutputStarted(JLjava/io/File;)V
.end method
