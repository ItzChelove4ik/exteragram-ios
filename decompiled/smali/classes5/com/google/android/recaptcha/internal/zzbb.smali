.class public final Lcom/google/android/recaptcha/internal/zzbb;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Landroid/app/Application;

.field private final zzb:Lkotlinx/coroutines/CoroutineScope;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzav;

.field private zzd:Lcom/google/android/recaptcha/internal/zzay;

.field private zze:Z

.field private final zzf:Lcom/google/android/recaptcha/internal/zzas;


# direct methods
.method public constructor <init>(Landroid/app/Application;Lkotlinx/coroutines/CoroutineScope;Lcom/google/android/recaptcha/internal/zzav;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbb;->zza:Landroid/app/Application;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzb:Lkotlinx/coroutines/CoroutineScope;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzc:Lcom/google/android/recaptcha/internal/zzav;

    new-instance p2, Lcom/google/android/recaptcha/internal/zzas;

    invoke-direct {p2, p3}, Lcom/google/android/recaptcha/internal/zzas;-><init>(Lcom/google/android/recaptcha/internal/zzav;)V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzf:Lcom/google/android/recaptcha/internal/zzas;

    .line 2
    invoke-virtual {p1, p2}, Landroid/app/Application;->registerActivityLifecycleCallbacks(Landroid/app/Application$ActivityLifecycleCallbacks;)V

    return-void
.end method

.method public static synthetic zzf(Lcom/google/android/recaptcha/internal/zzbb;Ljava/lang/String;Landroid/view/MotionEvent;)Lkotlin/Unit;
    .locals 6

    .line 1
    invoke-static {p2}, Landroid/view/MotionEvent;->obtain(Landroid/view/MotionEvent;)Landroid/view/MotionEvent;

    move-result-object p2

    .line 2
    new-instance v3, Lcom/google/android/recaptcha/internal/zzba;

    const/4 v0, 0x0

    invoke-direct {v3, p0, p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzba;-><init>(Lcom/google/android/recaptcha/internal/zzbb;Ljava/lang/String;Landroid/view/MotionEvent;Lkotlin/coroutines/Continuation;)V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzb:Lkotlinx/coroutines/CoroutineScope;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    .line 3
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzay;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzd:Lcom/google/android/recaptcha/internal/zzay;

    return-object p0
.end method

.method public final zzb()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zze:Z

    return p0
.end method

.method public final zzc(IJ)V
    .locals 8

    .line 1
    iget-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zze:Z

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzd:Lcom/google/android/recaptcha/internal/zzay;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zza:Landroid/app/Application;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzay;

    invoke-virtual {v0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v2

    const/4 v6, 0x1

    const v7, 0x3e4ccccd    # 0.2f

    move v3, p1

    move-wide v4, p2

    .line 2
    invoke-direct/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzay;-><init>(Landroid/content/Context;IJZF)V

    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzd:Lcom/google/android/recaptcha/internal/zzay;

    :cond_0
    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzbb;->zze:Z

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzc:Lcom/google/android/recaptcha/internal/zzav;

    new-instance p2, Lcom/google/android/recaptcha/internal/zzaz;

    invoke-direct {p2, p0}, Lcom/google/android/recaptcha/internal/zzaz;-><init>(Lcom/google/android/recaptcha/internal/zzbb;)V

    .line 3
    invoke-virtual {p1, p2}, Lcom/google/android/recaptcha/internal/zzav;->zzb(Lkotlin/jvm/functions/Function2;)V

    :cond_1
    return-void
.end method

.method public final zzd(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzd:Lcom/google/android/recaptcha/internal/zzay;

    if-eqz p0, :cond_0

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzay;->zzc(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public final zze()V
    .locals 2

    .line 1
    iget-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zze:Z

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzc:Lcom/google/android/recaptcha/internal/zzav;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzav;->zzb(Lkotlin/jvm/functions/Function2;)V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zze:Z

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbb;->zzd:Lcom/google/android/recaptcha/internal/zzay;

    if-eqz p0, :cond_1

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzay;->zzb()V

    :cond_1
    :goto_0
    return-void
.end method
