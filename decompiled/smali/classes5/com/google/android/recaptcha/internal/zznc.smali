.class public final Lcom/google/android/recaptcha/internal/zznc;
.super Lorg/chromium/net/UrlRequest$Callback;
.source "SourceFile"


# instance fields
.field private final zza:Lkotlinx/coroutines/CancellableContinuation;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzaeq;


# direct methods
.method public constructor <init>(Lkotlinx/coroutines/CancellableContinuation;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lorg/chromium/net/UrlRequest$Callback;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zznc;->zza:Lkotlinx/coroutines/CancellableContinuation;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaer;->zzA()Lcom/google/android/recaptcha/internal/zzaeq;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zznc;->zzb:Lcom/google/android/recaptcha/internal/zzaeq;

    return-void
.end method


# virtual methods
.method public final onFailed(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Lorg/chromium/net/CronetException;)V
    .locals 0

    .line 1
    invoke-virtual {p2}, Lorg/chromium/net/UrlResponseInfo;->getUrl()Ljava/lang/String;

    .line 2
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p3}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p1

    invoke-static {p1}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zznc;->zza:Lkotlinx/coroutines/CancellableContinuation;

    invoke-interface {p0, p1}, Lkotlin/coroutines/Continuation;->resumeWith(Ljava/lang/Object;)V

    return-void
.end method

.method public final onReadCompleted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/nio/ByteBuffer;)V
    .locals 0

    .line 1
    invoke-virtual {p2}, Lorg/chromium/net/UrlResponseInfo;->getUrl()Ljava/lang/String;

    .line 2
    invoke-virtual {p3}, Ljava/nio/ByteBuffer;->flip()Ljava/nio/Buffer;

    .line 3
    invoke-virtual {p3}, Ljava/nio/Buffer;->remaining()I

    move-result p2

    new-array p2, p2, [B

    .line 4
    invoke-virtual {p3, p2}, Ljava/nio/ByteBuffer;->get([B)Ljava/nio/ByteBuffer;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zznc;->zzb:Lcom/google/android/recaptcha/internal/zzaeq;

    .line 5
    invoke-virtual {p0, p2}, Ljava/io/OutputStream;->write([B)V

    .line 6
    invoke-virtual {p3}, Ljava/nio/ByteBuffer;->clear()Ljava/nio/Buffer;

    .line 7
    invoke-virtual {p1, p3}, Lorg/chromium/net/UrlRequest;->read(Ljava/nio/ByteBuffer;)V

    return-void
.end method

.method public final onRedirectReceived(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-virtual {p2}, Lorg/chromium/net/UrlResponseInfo;->getUrl()Ljava/lang/String;

    .line 2
    invoke-virtual {p1}, Lorg/chromium/net/UrlRequest;->followRedirect()V

    return-void
.end method

.method public final onResponseStarted(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 0

    .line 1
    invoke-virtual {p2}, Lorg/chromium/net/UrlResponseInfo;->getUrl()Ljava/lang/String;

    const/high16 p0, 0x10000

    .line 2
    invoke-static {p0}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object p0

    .line 3
    invoke-virtual {p1, p0}, Lorg/chromium/net/UrlRequest;->read(Ljava/nio/ByteBuffer;)V

    return-void
.end method

.method public final onSucceeded(Lorg/chromium/net/UrlRequest;Lorg/chromium/net/UrlResponseInfo;)V
    .locals 1

    .line 1
    invoke-virtual {p2}, Lorg/chromium/net/UrlResponseInfo;->getUrl()Ljava/lang/String;

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zznc;->zzb:Lcom/google/android/recaptcha/internal/zzaeq;

    new-instance v0, Lcom/google/android/recaptcha/internal/zznb;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaeq;->zza()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p1

    invoke-direct {v0, p2, p1}, Lcom/google/android/recaptcha/internal/zznb;-><init>(Lorg/chromium/net/UrlResponseInfo;[B)V

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zznc;->zza:Lkotlinx/coroutines/CancellableContinuation;

    .line 3
    invoke-interface {p0, p1}, Lkotlin/coroutines/Continuation;->resumeWith(Ljava/lang/Object;)V

    return-void
.end method
