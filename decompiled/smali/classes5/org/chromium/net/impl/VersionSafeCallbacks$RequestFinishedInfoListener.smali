.class public final Lorg/chromium/net/impl/VersionSafeCallbacks$RequestFinishedInfoListener;
.super Lorg/chromium/net/RequestFinishedInfo$Listener;
.source "SourceFile"


# instance fields
.field private final mWrappedListener:Lorg/chromium/net/RequestFinishedInfo$Listener;


# direct methods
.method public constructor <init>(Lorg/chromium/net/RequestFinishedInfo$Listener;)V
    .locals 1

    .line 183
    invoke-virtual {p1}, Lorg/chromium/net/RequestFinishedInfo$Listener;->getExecutor()Ljava/util/concurrent/Executor;

    move-result-object v0

    invoke-direct {p0, v0}, Lorg/chromium/net/RequestFinishedInfo$Listener;-><init>(Ljava/util/concurrent/Executor;)V

    .line 184
    iput-object p1, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$RequestFinishedInfoListener;->mWrappedListener:Lorg/chromium/net/RequestFinishedInfo$Listener;

    return-void
.end method


# virtual methods
.method public getExecutor()Ljava/util/concurrent/Executor;
    .locals 0

    .line 194
    iget-object p0, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$RequestFinishedInfoListener;->mWrappedListener:Lorg/chromium/net/RequestFinishedInfo$Listener;

    invoke-virtual {p0}, Lorg/chromium/net/RequestFinishedInfo$Listener;->getExecutor()Ljava/util/concurrent/Executor;

    move-result-object p0

    return-object p0
.end method

.method public onRequestFinished(Lorg/chromium/net/RequestFinishedInfo;)V
    .locals 0

    .line 189
    iget-object p0, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$RequestFinishedInfoListener;->mWrappedListener:Lorg/chromium/net/RequestFinishedInfo$Listener;

    invoke-virtual {p0, p1}, Lorg/chromium/net/RequestFinishedInfo$Listener;->onRequestFinished(Lorg/chromium/net/RequestFinishedInfo;)V

    return-void
.end method
