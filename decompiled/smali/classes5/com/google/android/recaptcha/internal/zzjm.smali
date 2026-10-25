.class public final Lcom/google/android/recaptcha/internal/zzjm;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzjm;

.field private static zzb:Lcom/google/android/recaptcha/internal/zzjt;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjm;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzjm;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzjm;->zza:Lcom/google/android/recaptcha/internal/zzjm;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final zze(Landroid/app/Application;Ljava/lang/String;J)Lcom/google/android/gms/tasks/Task;
    .locals 8

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzik;->zza:Lcom/google/android/recaptcha/internal/zzik;

    const/4 v1, 0x0

    const/4 v2, 0x2

    invoke-static {v0, p0, v1, v2, v1}, Lcom/google/android/recaptcha/internal/zzik;->zza(Lcom/google/android/recaptcha/internal/zzik;Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzii;ILjava/lang/Object;)Lcom/google/android/recaptcha/internal/zzko;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzko;->zze()Lcom/google/android/recaptcha/internal/zzhu;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzhu;->zzc()Lkotlinx/coroutines/CoroutineScope;

    move-result-object v1

    .line 2
    new-instance v2, Lcom/google/android/recaptcha/internal/zzjl;

    const/4 v7, 0x0

    move-object v3, p0

    move-object v4, p1

    move-wide v5, p2

    invoke-direct/range {v2 .. v7}, Lcom/google/android/recaptcha/internal/zzjl;-><init>(Landroid/app/Application;Ljava/lang/String;JLkotlin/coroutines/Continuation;)V

    const/4 v5, 0x3

    const/4 v6, 0x0

    move-object v4, v2

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v1 .. v6}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object p0

    .line 3
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzhz;->zza(Lkotlinx/coroutines/Deferred;)Lcom/google/android/gms/tasks/Task;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final zza(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzko;)Lcom/google/android/recaptcha/internal/zzjt;
    .locals 1

    .line 1
    monitor-enter p0

    :try_start_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzjm;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    if-nez v0, :cond_0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzjt;

    invoke-direct {v0, p1, p2}, Lcom/google/android/recaptcha/internal/zzjt;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzko;)V

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    :cond_0
    :goto_0
    sget-object p1, Lcom/google/android/recaptcha/internal/zzjm;->zzb:Lcom/google/android/recaptcha/internal/zzjt;

    if-nez p1, :cond_1

    sput-object v0, Lcom/google/android/recaptcha/internal/zzjm;->zzb:Lcom/google/android/recaptcha/internal/zzjt;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 2
    :cond_1
    monitor-exit p0

    return-object v0

    :goto_1
    monitor-exit p0

    throw p1
.end method

.method public final zzc(Landroid/app/Application;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 9

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzik;->zza:Lcom/google/android/recaptcha/internal/zzik;

    const/4 v1, 0x0

    const/4 v2, 0x2

    invoke-static {v0, p1, v1, v2, v1}, Lcom/google/android/recaptcha/internal/zzik;->zza(Lcom/google/android/recaptcha/internal/zzik;Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzii;ILjava/lang/Object;)Lcom/google/android/recaptcha/internal/zzko;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzjm;->zza(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzko;)Lcom/google/android/recaptcha/internal/zzjt;

    move-result-object v1

    const/4 v7, 0x4

    const/4 v8, 0x0

    const/4 v5, 0x0

    move-object v2, p2

    move-wide v3, p3

    move-object v6, p5

    invoke-static/range {v1 .. v8}, Lcom/google/android/recaptcha/internal/zzjt;->zza(Lcom/google/android/recaptcha/internal/zzjt;Ljava/lang/String;JLcom/google/android/recaptcha/internal/zziw;Lkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
