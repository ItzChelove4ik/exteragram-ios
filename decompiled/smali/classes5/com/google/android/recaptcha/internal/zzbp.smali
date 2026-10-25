.class public final Lcom/google/android/recaptcha/internal/zzbp;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzbk;
.implements Lcom/google/android/recaptcha/internal/zzbq;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzalc;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzbr;

.field private final zzc:Lkotlinx/coroutines/CoroutineScope;

.field private final zzd:Lkotlinx/coroutines/CompletableDeferred;

.field private zze:Landroid/webkit/WebView;

.field private zzf:Landroid/widget/ProgressBar;

.field private zzg:Lkotlin/jvm/functions/Function0;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzalc;Lcom/google/android/recaptcha/internal/zzbr;Lkotlinx/coroutines/CoroutineScope;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbp;->zza:Lcom/google/android/recaptcha/internal/zzalc;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzb:Lcom/google/android/recaptcha/internal/zzbr;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzc:Lkotlinx/coroutines/CoroutineScope;

    const/4 p1, 0x0

    const/4 p2, 0x1

    invoke-static {p1, p2, p1}, Lkotlinx/coroutines/CompletableDeferredKt;->CompletableDeferred$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzd:Lkotlinx/coroutines/CompletableDeferred;

    return-void
.end method

.method public static final synthetic zzi(Lcom/google/android/recaptcha/internal/zzbp;Landroid/webkit/WebView;)V
    .locals 0

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzbp;->zzo(Landroid/webkit/WebView;)V

    return-void
.end method

.method private final zzn(Ljava/lang/Exception;Landroid/webkit/WebView;)V
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzd:Lkotlinx/coroutines/CompletableDeferred;

    invoke-interface {v0, p1}, Lkotlinx/coroutines/CompletableDeferred;->completeExceptionally(Ljava/lang/Throwable;)Z

    .line 2
    new-instance v4, Lcom/google/android/recaptcha/internal/zzbm;

    const/4 p1, 0x0

    invoke-direct {v4, p0, p2, p1}, Lcom/google/android/recaptcha/internal/zzbm;-><init>(Lcom/google/android/recaptcha/internal/zzbp;Landroid/webkit/WebView;Lkotlin/coroutines/Continuation;)V

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzc:Lkotlinx/coroutines/CoroutineScope;

    const/4 v5, 0x3

    const/4 v6, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v1 .. v6}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method private final zzo(Landroid/webkit/WebView;)V
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzb:Lcom/google/android/recaptcha/internal/zzbr;

    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzbr;->zzc()V

    .line 2
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzbp;->zzp()V

    const/4 v0, 0x0

    if-eqz p1, :cond_5

    .line 3
    :try_start_0
    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    instance-of v2, v1, Landroid/content/MutableContextWrapper;

    if-eqz v2, :cond_0

    check-cast v1, Landroid/content/MutableContextWrapper;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_2

    :cond_0
    move-object v1, v0

    :goto_0
    if-eqz v1, :cond_1

    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/content/MutableContextWrapper;->setBaseContext(Landroid/content/Context;)V

    sget-object v1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 4
    :cond_1
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    instance-of v2, v1, Landroid/view/ViewGroup;

    if-eqz v2, :cond_2

    check-cast v1, Landroid/view/ViewGroup;

    goto :goto_1

    :cond_2
    move-object v1, v0

    :goto_1
    if-eqz v1, :cond_5

    invoke-virtual {v1, p1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    goto :goto_3

    :goto_2
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    instance-of v2, v1, Landroid/view/ViewGroup;

    if-eqz v2, :cond_3

    move-object v0, v1

    check-cast v0, Landroid/view/ViewGroup;

    :cond_3
    if-eqz v0, :cond_4

    invoke-virtual {v0, p1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    :cond_4
    throw p0

    :cond_5
    :goto_3
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzbp;->zze:Landroid/webkit/WebView;

    if-ne p1, v1, :cond_6

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzbp;->zze:Landroid/webkit/WebView;

    :cond_6
    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzg:Lkotlin/jvm/functions/Function0;

    if-eqz p1, :cond_7

    .line 5
    invoke-interface {p1}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    :cond_7
    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzg:Lkotlin/jvm/functions/Function0;

    return-void
.end method

.method private final zzp()V
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzf:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    instance-of v2, v1, Landroid/view/ViewGroup;

    const/4 v3, 0x0

    if-eqz v2, :cond_0

    check-cast v1, Landroid/view/ViewGroup;

    goto :goto_0

    :cond_0
    move-object v1, v3

    :goto_0
    if-eqz v1, :cond_1

    invoke-virtual {v1, v0}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    :cond_1
    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzf:Landroid/widget/ProgressBar;

    :cond_2
    return-void
.end method


# virtual methods
.method public final zza(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbp;->zzd:Lkotlinx/coroutines/CompletableDeferred;

    invoke-interface {p0, p1}, Lkotlinx/coroutines/Deferred;->await(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzc()V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbp;->zze:Landroid/webkit/WebView;

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzbp;->zzo(Landroid/webkit/WebView;)V

    return-void
.end method

.method public final zzg()V
    .locals 7

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzb:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzaC:Lcom/google/android/recaptcha/internal/zzhe;

    const/16 v5, 0x8

    const/4 v6, 0x0

    const-string v3, "WebView render process is gone."

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzbp;->zze:Landroid/webkit/WebView;

    .line 2
    invoke-direct {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzbp;->zzn(Ljava/lang/Exception;Landroid/webkit/WebView;)V

    return-void
.end method
