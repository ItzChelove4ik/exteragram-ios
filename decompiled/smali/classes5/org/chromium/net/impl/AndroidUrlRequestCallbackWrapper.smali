.class Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/net/http/UrlRequest$Callback;


# instance fields
.field private final mBackend:Lorg/chromium/net/UrlRequest$Callback;

.field private mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;


# direct methods
.method public static synthetic $r8$lambda$0YSUpRBhWItOzjE5XTPKTjltwwU(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;Ljava/nio/ByteBuffer;)Ljava/lang/Object;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->lambda$onReadCompleted$2(Landroid/net/http/UrlResponseInfo;Ljava/nio/ByteBuffer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$g4UEVjfEGVPQs1LW7r7pKONVtkI(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;)Ljava/lang/Object;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->lambda$onResponseStarted$1(Landroid/net/http/UrlResponseInfo;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$lfnCnZ7eGSbsnvDQaHpEwevkPZ0(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;Ljava/lang/String;)Ljava/lang/Object;
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->lambda$onRedirectReceived$0(Landroid/net/http/UrlResponseInfo;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public constructor <init>(Lorg/chromium/net/UrlRequest$Callback;)V
    .locals 1

    .line 28
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 29
    const-string v0, "Callback is required."

    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    iput-object p1, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mBackend:Lorg/chromium/net/UrlRequest$Callback;

    return-void
.end method

.method private synthetic lambda$onReadCompleted$2(Landroid/net/http/UrlResponseInfo;Ljava/nio/ByteBuffer;)Ljava/lang/Object;
    .locals 1

    .line 77
    invoke-static {p1}, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->createForUrlRequest(Landroid/net/http/UrlResponseInfo;)Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;

    move-result-object p1

    .line 78
    iget-object v0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mBackend:Lorg/chromium/net/UrlRequest$Callback;

    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {v0, p0, p1, p2}, Lorg/chromium/net/UrlRequest$Callback;->onReadCompleted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private synthetic lambda$onRedirectReceived$0(Landroid/net/http/UrlResponseInfo;Ljava/lang/String;)Ljava/lang/Object;
    .locals 1

    .line 46
    invoke-static {p1}, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->createForUrlRequest(Landroid/net/http/UrlResponseInfo;)Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;

    move-result-object p1

    .line 47
    iget-object v0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mBackend:Lorg/chromium/net/UrlRequest$Callback;

    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {v0, p0, p1, p2}, Lorg/chromium/net/UrlRequest$Callback;->onRedirectReceived(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private synthetic lambda$onResponseStarted$1(Landroid/net/http/UrlResponseInfo;)Ljava/lang/Object;
    .locals 1

    .line 61
    invoke-static {p1}, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->createForUrlRequest(Landroid/net/http/UrlResponseInfo;)Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;

    move-result-object p1

    .line 62
    iget-object v0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mBackend:Lorg/chromium/net/UrlRequest$Callback;

    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {v0, p0, p1}, Lorg/chromium/net/UrlRequest$Callback;->onResponseStarted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V

    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public onCanceled(Landroid/net/http/UrlRequest;Landroid/net/http/UrlResponseInfo;)V
    .locals 3

    .line 125
    invoke-static {p2}, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->createForUrlRequest(Landroid/net/http/UrlResponseInfo;)Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;

    move-result-object p1

    const/4 p2, 0x0

    const/4 v0, 0x2

    .line 127
    :try_start_0
    iget-object v1, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mBackend:Lorg/chromium/net/UrlRequest$Callback;

    iget-object v2, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {v1, v2, p1}, Lorg/chromium/net/UrlRequest$Callback;->onCanceled(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 130
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {p0, v0, p1, p2}, Lorg/chromium/net/impl/AndroidUrlRequestWrapper;->maybeReportMetrics(ILorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;Lorg/chromium/net/CronetException;)V

    return-void

    :catchall_0
    move-exception v1

    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {p0, v0, p1, p2}, Lorg/chromium/net/impl/AndroidUrlRequestWrapper;->maybeReportMetrics(ILorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;Lorg/chromium/net/CronetException;)V

    .line 132
    throw v1
.end method

.method public onFailed(Landroid/net/http/UrlRequest;Landroid/net/http/UrlResponseInfo;Landroid/net/http/HttpException;)V
    .locals 2

    .line 108
    invoke-static {p2}, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->createForUrlRequest(Landroid/net/http/UrlResponseInfo;)Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;

    move-result-object p1

    .line 110
    invoke-static {p3}, Lorg/chromium/net/impl/CronetExceptionTranslationUtils;->translateCheckedAndroidCronetException(Ljava/lang/Exception;)Lorg/chromium/net/CronetException;

    move-result-object p2

    const/4 p3, 0x1

    .line 112
    :try_start_0
    iget-object v0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mBackend:Lorg/chromium/net/UrlRequest$Callback;

    iget-object v1, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {v0, v1, p1, p2}, Lorg/chromium/net/UrlRequest$Callback;->onFailed(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 115
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {p0, p3, p1, p2}, Lorg/chromium/net/impl/AndroidUrlRequestWrapper;->maybeReportMetrics(ILorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;Lorg/chromium/net/CronetException;)V

    return-void

    :catchall_0
    move-exception v0

    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {p0, p3, p1, p2}, Lorg/chromium/net/impl/AndroidUrlRequestWrapper;->maybeReportMetrics(ILorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;Lorg/chromium/net/CronetException;)V

    .line 117
    throw v0
.end method

.method public onReadCompleted(Landroid/net/http/UrlRequest;Landroid/net/http/UrlResponseInfo;Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 74
    new-instance p1, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;

    invoke-direct {p1, p0, p2, p3}, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda1;-><init>(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;Ljava/nio/ByteBuffer;)V

    const-class p0, Ljava/lang/Exception;

    invoke-static {p1, p0}, Lorg/chromium/net/impl/CronetExceptionTranslationUtils;->executeTranslatingCronetExceptions(Lorg/chromium/net/impl/CronetExceptionTranslationUtils$CronetWork;Ljava/lang/Class;)Ljava/lang/Object;

    return-void
.end method

.method public onRedirectReceived(Landroid/net/http/UrlRequest;Landroid/net/http/UrlResponseInfo;Ljava/lang/String;)V
    .locals 0

    .line 43
    new-instance p1, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda2;

    invoke-direct {p1, p0, p2, p3}, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda2;-><init>(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;Ljava/lang/String;)V

    const-class p0, Ljava/lang/Exception;

    invoke-static {p1, p0}, Lorg/chromium/net/impl/CronetExceptionTranslationUtils;->executeTranslatingCronetExceptions(Lorg/chromium/net/impl/CronetExceptionTranslationUtils$CronetWork;Ljava/lang/Class;)Ljava/lang/Object;

    return-void
.end method

.method public onResponseStarted(Landroid/net/http/UrlRequest;Landroid/net/http/UrlResponseInfo;)V
    .locals 0

    .line 58
    new-instance p1, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0, p2}, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0;-><init>(Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;Landroid/net/http/UrlResponseInfo;)V

    const-class p0, Ljava/lang/Exception;

    invoke-static {p1, p0}, Lorg/chromium/net/impl/CronetExceptionTranslationUtils;->executeTranslatingCronetExceptions(Lorg/chromium/net/impl/CronetExceptionTranslationUtils$CronetWork;Ljava/lang/Class;)Ljava/lang/Object;

    return-void
.end method

.method public onSucceeded(Landroid/net/http/UrlRequest;Landroid/net/http/UrlResponseInfo;)V
    .locals 3

    .line 88
    invoke-static {p2}, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->createForUrlRequest(Landroid/net/http/UrlResponseInfo;)Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;

    move-result-object p1

    const/4 p2, 0x0

    const/4 v0, 0x0

    .line 90
    :try_start_0
    iget-object v1, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mBackend:Lorg/chromium/net/UrlRequest$Callback;

    iget-object v2, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {v1, v2, p1}, Lorg/chromium/net/UrlRequest$Callback;->onSucceeded(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 97
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {p0, v0, p1, p2}, Lorg/chromium/net/impl/AndroidUrlRequestWrapper;->maybeReportMetrics(ILorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;Lorg/chromium/net/CronetException;)V

    return-void

    :catchall_0
    move-exception v1

    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    invoke-virtual {p0, v0, p1, p2}, Lorg/chromium/net/impl/AndroidUrlRequestWrapper;->maybeReportMetrics(ILorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;Lorg/chromium/net/CronetException;)V

    .line 99
    throw v1
.end method

.method public setRequest(Lorg/chromium/net/impl/AndroidUrlRequestWrapper;)V
    .locals 0

    .line 136
    iput-object p1, p0, Lorg/chromium/net/impl/AndroidUrlRequestCallbackWrapper;->mWrappedRequest:Lorg/chromium/net/impl/AndroidUrlRequestWrapper;

    return-void
.end method
