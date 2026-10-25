.class final Lcom/google/android/recaptcha/internal/zzcb;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/Object;

.field private zzb:Landroid/webkit/WebView;

.field private zzc:Lkotlinx/coroutines/CompletableDeferred;

.field private zzd:Lkotlinx/coroutines/Job;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzcb;->zza:Ljava/lang/Object;

    return-void
.end method


# virtual methods
.method public final zza()V
    .locals 5

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzcb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzb:Landroid/webkit/WebView;

    const/4 v2, 0x0

    if-eqz v1, :cond_4

    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v3

    instance-of v4, v3, Landroid/content/MutableContextWrapper;

    if-eqz v4, :cond_0

    check-cast v3, Landroid/content/MutableContextWrapper;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_2

    :cond_0
    move-object v3, v2

    :goto_0
    if-eqz v3, :cond_1

    .line 2
    invoke-virtual {v1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v4

    .line 1
    invoke-virtual {v3, v4}, Landroid/content/MutableContextWrapper;->setBaseContext(Landroid/content/Context;)V

    .line 3
    :cond_1
    invoke-virtual {v1}, Landroid/webkit/WebView;->stopLoading()V

    .line 4
    invoke-virtual {v1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v3

    instance-of v4, v3, Landroid/view/ViewGroup;

    if-eqz v4, :cond_2

    check-cast v3, Landroid/view/ViewGroup;

    goto :goto_1

    :cond_2
    move-object v3, v2

    :goto_1
    if-eqz v3, :cond_3

    invoke-virtual {v3, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 5
    :cond_3
    invoke-virtual {v1}, Landroid/webkit/WebView;->destroy()V

    :cond_4
    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzb:Landroid/webkit/WebView;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    const/4 v3, 0x1

    if-eqz v1, :cond_5

    .line 6
    invoke-static {v1, v2, v3, v2}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    :cond_5
    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzd:Lkotlinx/coroutines/Job;

    if-eqz v1, :cond_6

    .line 7
    invoke-static {v1, v2, v3, v2}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    :cond_6
    iput-object v2, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzd:Lkotlinx/coroutines/Job;

    .line 8
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-void

    :goto_2
    monitor-exit v0

    throw p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzcf;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzcb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzb:Landroid/webkit/WebView;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    if-eqz v1, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzcd;

    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzcd;-><init>(Landroid/webkit/WebView;)V

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    if-eqz v2, :cond_1

    .line 5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzcc;

    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzcc;-><init>(Lkotlinx/coroutines/CompletableDeferred;)V

    goto :goto_0

    :cond_1
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 3
    invoke-static {v2, v1, v2}, Lkotlinx/coroutines/CompletableDeferredKt;->CompletableDeferred$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;

    move-result-object v1

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    .line 5
    new-instance p0, Lcom/google/android/recaptcha/internal/zzce;

    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzce;-><init>(Lkotlinx/coroutines/CompletableDeferred;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :goto_0
    monitor-exit v0

    return-object p0

    :goto_1
    monitor-exit v0

    throw p0
.end method

.method public final zzc(Landroid/webkit/WebView;)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzcb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzb:Landroid/webkit/WebView;

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzd:Lkotlinx/coroutines/Job;

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0

    throw p0
.end method

.method public final zzd(Lkotlinx/coroutines/CompletableDeferred;)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzcb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    if-ne v1, p1, :cond_0

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzd:Lkotlinx/coroutines/Job;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0

    throw p0
.end method

.method public final zze(Lkotlinx/coroutines/Job;)V
    .locals 2

    .line 2
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzcb;->zza:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzc:Lkotlinx/coroutines/CompletableDeferred;

    if-eqz v1, :cond_0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcb;->zzd:Lkotlinx/coroutines/Job;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    const/4 p0, 0x1

    const/4 v1, 0x0

    .line 1
    invoke-static {p1, v1, p0, v1}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    .line 2
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0

    throw p0
.end method
