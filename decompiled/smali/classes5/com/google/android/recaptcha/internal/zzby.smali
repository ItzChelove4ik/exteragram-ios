.class public final Lcom/google/android/recaptcha/internal/zzby;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzbr;


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzhq;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzbi;

.field private final zzd:Landroid/content/Context;

.field private final zze:Lcom/google/android/recaptcha/internal/zzcb;

.field private final zzf:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private final zzg:Lcom/google/android/recaptcha/internal/zzhu;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzbi;Landroid/content/Context;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzby;->zza:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzby;->zzg:Lcom/google/android/recaptcha/internal/zzhu;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzby;->zzb:Lcom/google/android/recaptcha/internal/zzhq;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzby;->zzc:Lcom/google/android/recaptcha/internal/zzbi;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzby;->zzd:Landroid/content/Context;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzcb;

    invoke-direct {p1}, Lcom/google/android/recaptcha/internal/zzcb;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzby;->zze:Lcom/google/android/recaptcha/internal/zzcb;

    new-instance p1, Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 p2, 0x0

    invoke-direct {p1, p2}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzby;->zzf:Ljava/util/concurrent/atomic/AtomicBoolean;

    return-void
.end method

.method public static final synthetic zze(Lcom/google/android/recaptcha/internal/zzby;Lkotlinx/coroutines/CompletableDeferred;)Landroid/webkit/WebView;
    .locals 5

    .line 1
    new-instance v0, Landroid/content/MutableContextWrapper;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzby;->zzd:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/content/MutableContextWrapper;-><init>(Landroid/content/Context;)V

    .line 3
    new-instance v1, Landroid/webkit/WebView;

    invoke-direct {v1, v0}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;)V

    .line 5
    invoke-virtual {v1}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v0

    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Landroid/webkit/WebSettings;->setJavaScriptEnabled(Z)V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzby;->zzc:Lcom/google/android/recaptcha/internal/zzbi;

    const-string v2, "recaptchaMobileBridge"

    .line 6
    invoke-virtual {v1, v0, v2}, Landroid/webkit/WebView;->addJavascriptInterface(Ljava/lang/Object;Ljava/lang/String;)V

    .line 7
    new-instance v0, Lcom/google/android/recaptcha/internal/zzbu;

    invoke-direct {v0, p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzbu;-><init>(Lkotlinx/coroutines/CompletableDeferred;Landroid/webkit/WebView;Lcom/google/android/recaptcha/internal/zzby;)V

    .line 8
    invoke-virtual {v1, v0}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    .line 9
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/Locale;->toLanguageTag()Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzby;->zzb:Lcom/google/android/recaptcha/internal/zzhq;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzhq;->zzf()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    .line 10
    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzby;->zza:Ljava/lang/String;

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v4

    add-int/lit8 v2, v2, 0x4

    add-int/2addr v2, v3

    new-instance v3, Ljava/lang/StringBuilder;

    add-int/lit8 v2, v2, 0x3

    add-int/2addr v2, v4

    invoke-direct {v3, v2}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "?hl="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "&k="

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 11
    invoke-virtual {v1, p0}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    return-object v1
.end method

.method public static final synthetic zzf(Lcom/google/android/recaptcha/internal/zzby;)Lcom/google/android/recaptcha/internal/zzcb;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzby;->zze:Lcom/google/android/recaptcha/internal/zzcb;

    return-object p0
.end method

.method public static final synthetic zzg(Lcom/google/android/recaptcha/internal/zzby;)Lcom/google/android/recaptcha/internal/zzbi;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzby;->zzc:Lcom/google/android/recaptcha/internal/zzbi;

    return-object p0
.end method

.method private final zzh()Lkotlinx/coroutines/CompletableDeferred;
    .locals 11

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzby;->zzf:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzby;->zzd:Landroid/content/Context;

    .line 2
    invoke-virtual {v0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    new-instance v1, Lcom/google/android/recaptcha/internal/zzbt;

    invoke-direct {v1, p0}, Lcom/google/android/recaptcha/internal/zzbt;-><init>(Lcom/google/android/recaptcha/internal/zzby;)V

    invoke-virtual {v0, v1}, Landroid/content/Context;->registerComponentCallbacks(Landroid/content/ComponentCallbacks;)V

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzby;->zze:Lcom/google/android/recaptcha/internal/zzcb;

    .line 3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzcb;->zzb()Lcom/google/android/recaptcha/internal/zzcf;

    move-result-object v1

    instance-of v2, v1, Lcom/google/android/recaptcha/internal/zzcd;

    if-eqz v2, :cond_1

    .line 4
    check-cast v1, Lcom/google/android/recaptcha/internal/zzcd;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzcd;->zza()Landroid/webkit/WebView;

    move-result-object p0

    invoke-static {p0}, Lkotlinx/coroutines/CompletableDeferredKt;->CompletableDeferred(Ljava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;

    move-result-object p0

    return-object p0

    :cond_1
    instance-of v2, v1, Lcom/google/android/recaptcha/internal/zzcc;

    if-eqz v2, :cond_2

    .line 5
    check-cast v1, Lcom/google/android/recaptcha/internal/zzcc;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzcc;->zza()Lkotlinx/coroutines/CompletableDeferred;

    move-result-object p0

    return-object p0

    :cond_2
    instance-of v2, v1, Lcom/google/android/recaptcha/internal/zzce;

    const/4 v3, 0x0

    if-eqz v2, :cond_3

    .line 6
    check-cast v1, Lcom/google/android/recaptcha/internal/zzce;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzce;->zza()Lkotlinx/coroutines/CompletableDeferred;

    move-result-object v2

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzby;->zzg:Lcom/google/android/recaptcha/internal/zzhu;

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzhu;->zza()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v5

    .line 7
    new-instance v8, Lcom/google/android/recaptcha/internal/zzbx;

    invoke-direct {v8, p0, v2, v3}, Lcom/google/android/recaptcha/internal/zzbx;-><init>(Lcom/google/android/recaptcha/internal/zzby;Lkotlinx/coroutines/CompletableDeferred;Lkotlin/coroutines/Continuation;)V

    const/4 v9, 0x3

    const/4 v10, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    .line 8
    invoke-static/range {v5 .. v10}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p0

    .line 9
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzcb;->zze(Lkotlinx/coroutines/Job;)V

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzce;->zza()Lkotlinx/coroutines/CompletableDeferred;

    move-result-object p0

    return-object p0

    .line 10
    :cond_3
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-object v3
.end method


# virtual methods
.method public final zza()V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzby;->zzh()Lkotlinx/coroutines/CompletableDeferred;

    return-void
.end method

.method public final zzc()V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzby;->zzc:Lcom/google/android/recaptcha/internal/zzbi;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzbi;->zzb()V

    return-void
.end method

.method public final zzd()V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzby;->zze:Lcom/google/android/recaptcha/internal/zzcb;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzcb;->zza()V

    return-void
.end method
