.class public final Lcom/google/android/recaptcha/internal/zzbu;
.super Landroid/webkit/WebViewClient;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lkotlinx/coroutines/CompletableDeferred;

.field final synthetic zzb:Landroid/webkit/WebView;

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzby;


# direct methods
.method public constructor <init>(Lkotlinx/coroutines/CompletableDeferred;Landroid/webkit/WebView;Lcom/google/android/recaptcha/internal/zzby;)V
    .locals 0

    .line 1
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbu;->zza:Lkotlinx/coroutines/CompletableDeferred;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzbu;->zzb:Landroid/webkit/WebView;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzbu;->zzc:Lcom/google/android/recaptcha/internal/zzby;

    invoke-direct {p0}, Landroid/webkit/WebViewClient;-><init>()V

    return-void
.end method


# virtual methods
.method public final onPageFinished(Landroid/webkit/WebView;Ljava/lang/String;)V
    .locals 0

    .line 1
    invoke-super {p0, p1, p2}, Landroid/webkit/WebViewClient;->onPageFinished(Landroid/webkit/WebView;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzbu;->zza:Lkotlinx/coroutines/CompletableDeferred;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbu;->zzb:Landroid/webkit/WebView;

    .line 2
    invoke-interface {p1, p0}, Lkotlinx/coroutines/CompletableDeferred;->complete(Ljava/lang/Object;)Z

    return-void
.end method

.method public final onReceivedError(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;Landroid/webkit/WebResourceError;)V
    .locals 7

    if-eqz p2, :cond_1

    .line 1
    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result p1

    const/4 p2, 0x1

    if-ne p1, p2, :cond_1

    if-eqz p3, :cond_0

    .line 2
    invoke-virtual {p3}, Landroid/webkit/WebResourceError;->getErrorCode()I

    move-result p1

    goto :goto_0

    :cond_0
    const/4 p1, -0x1

    :goto_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbu;->zza:Lkotlinx/coroutines/CompletableDeferred;

    .line 3
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzhe;

    invoke-direct {v2, p1}, Lcom/google/android/recaptcha/internal/zzhe;-><init>(I)V

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 4
    invoke-interface {p0, v0}, Lkotlinx/coroutines/CompletableDeferred;->completeExceptionally(Ljava/lang/Throwable;)Z

    :cond_1
    return-void
.end method

.method public final onRenderProcessGone(Landroid/webkit/WebView;Landroid/webkit/RenderProcessGoneDetail;)Z
    .locals 7

    .line 1
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzbu;->zza:Lkotlinx/coroutines/CompletableDeferred;

    invoke-interface {p1}, Lkotlinx/coroutines/Job;->isCompleted()Z

    move-result p2

    if-eqz p2, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbu;->zzc:Lcom/google/android/recaptcha/internal/zzby;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzby;->zzd()V

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzby;->zzg(Lcom/google/android/recaptcha/internal/zzby;)Lcom/google/android/recaptcha/internal/zzbi;

    move-result-object p0

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzbi;->zzd()V

    goto :goto_0

    .line 4
    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzaC:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0xc

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 5
    invoke-interface {p1, v0}, Lkotlinx/coroutines/CompletableDeferred;->completeExceptionally(Ljava/lang/Throwable;)Z

    :goto_0
    const/4 p0, 0x1

    return p0
.end method
