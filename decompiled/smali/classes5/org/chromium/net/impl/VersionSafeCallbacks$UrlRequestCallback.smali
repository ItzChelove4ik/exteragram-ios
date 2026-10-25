.class public final Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;
.super Lorg/chromium/net/UrlRequest$Callback;
.source "SourceFile"


# instance fields
.field private final mWrappedCallback:Lorg/chromium/net/UrlRequest$Callback;


# direct methods
.method public constructor <init>(Lorg/chromium/net/UrlRequest$Callback;)V
    .locals 0

    .line 37
    invoke-direct {p0}, Lorg/chromium/net/UrlRequest$Callback;-><init>()V

    .line 38
    iput-object p1, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->mWrappedCallback:Lorg/chromium/net/UrlRequest$Callback;

    return-void
.end method


# virtual methods
.method public onCanceled(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 70
    iget-object p0, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->mWrappedCallback:Lorg/chromium/net/UrlRequest$Callback;

    invoke-virtual {p0, p1, p2}, Lorg/chromium/net/UrlRequest$Callback;->onCanceled(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method

.method public onFailed(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V
    .locals 0

    .line 65
    iget-object p0, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->mWrappedCallback:Lorg/chromium/net/UrlRequest$Callback;

    invoke-virtual {p0, p1, p2, p3}, Lorg/chromium/net/UrlRequest$Callback;->onFailed(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V

    return-void
.end method

.method public onReadCompleted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 55
    iget-object p0, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->mWrappedCallback:Lorg/chromium/net/UrlRequest$Callback;

    invoke-virtual {p0, p1, p2, p3}, Lorg/chromium/net/UrlRequest$Callback;->onReadCompleted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V

    return-void
.end method

.method public onRedirectReceived(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V
    .locals 0

    .line 44
    iget-object p0, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->mWrappedCallback:Lorg/chromium/net/UrlRequest$Callback;

    invoke-virtual {p0, p1, p2, p3}, Lorg/chromium/net/UrlRequest$Callback;->onRedirectReceived(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V

    return-void
.end method

.method public onResponseStarted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 49
    iget-object p0, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->mWrappedCallback:Lorg/chromium/net/UrlRequest$Callback;

    invoke-virtual {p0, p1, p2}, Lorg/chromium/net/UrlRequest$Callback;->onResponseStarted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method

.method public onSucceeded(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 60
    iget-object p0, p0, Lorg/chromium/net/impl/VersionSafeCallbacks$UrlRequestCallback;->mWrappedCallback:Lorg/chromium/net/UrlRequest$Callback;

    invoke-virtual {p0, p1, p2}, Lorg/chromium/net/UrlRequest$Callback;->onSucceeded(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V

    return-void
.end method
