.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;
.super Ljava/lang/Exception;
.source "SourceFile"


# instance fields
.field private final zza:I

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;


# direct methods
.method private constructor <init>(IILjava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;)V
    .locals 5

    .line 1
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const/4 v1, 0x1

    const-string v2, "UNKNOWN"

    if-eq p1, v1, :cond_4

    const/4 v1, 0x2

    if-eq p1, v1, :cond_3

    const/4 v1, 0x3

    if-eq p1, v1, :cond_2

    const/4 v1, 0x4

    if-eq p1, v1, :cond_1

    const/4 v1, 0x7

    if-eq p1, v1, :cond_0

    move-object p1, v2

    goto :goto_0

    .line 4
    :cond_0
    const-string p1, "METADATA_ERROR"

    goto :goto_0

    :cond_1
    const-string p1, "CONNECTION_ERROR"

    goto :goto_0

    :cond_2
    const-string p1, "PREPARATION_ERROR"

    goto :goto_0

    :cond_3
    const-string p1, "INFERENCE_ERROR"

    goto :goto_0

    :cond_4
    const-string p1, "DOWNLOAD_ERROR"

    .line 2
    :goto_0
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const/16 v3, 0x12

    const-string v4, "INTERNAL_ERROR"

    if-eq p2, v3, :cond_5

    const/16 v3, 0x14

    if-eq p2, v3, :cond_5

    const/16 v3, 0x1f5

    if-eq p2, v3, :cond_7

    const/16 v3, 0x1d

    if-eq p2, v3, :cond_5

    const/16 v3, 0x1e

    if-eq p2, v3, :cond_6

    packed-switch p2, :pswitch_data_0

    packed-switch p2, :pswitch_data_1

    packed-switch p2, :pswitch_data_2

    goto :goto_1

    .line 4
    :pswitch_0
    const-string v2, "FEATURE_NOT_FOUND"

    goto :goto_1

    :pswitch_1
    const-string v2, "NULL_BINDING"

    goto :goto_1

    :pswitch_2
    const-string v2, "NEEDS_SYSTEM_UPDATE"

    goto :goto_1

    :pswitch_3
    const-string v2, "BINDING_DIED"

    goto :goto_1

    :pswitch_4
    const-string v2, "SERVICE_DISCONNECTED"

    goto :goto_1

    :pswitch_5
    const-string v2, "BINDING_FAILURE"

    goto :goto_1

    :pswitch_6
    const-string v2, "NOT_SUPPORTED"

    goto :goto_1

    :pswitch_7
    const-string v2, "RESPONSE_GENERATION_ERROR"

    goto :goto_1

    :cond_5
    :pswitch_8
    move-object v2, v4

    goto :goto_1

    :pswitch_9
    const-string v2, "REQUEST_TOO_LARGE"

    goto :goto_1

    :pswitch_a
    const-string v2, "RESPONSE_PROCESSING_ERROR"

    goto :goto_1

    :pswitch_b
    const-string v2, "SERVICE_PROCESSING_ERROR"

    goto :goto_1

    :pswitch_c
    const-string v2, "BUSY"

    goto :goto_1

    :pswitch_d
    const-string v2, "NOT_AVAILABLE"

    goto :goto_1

    :pswitch_e
    const-string v2, "CANCELLED"

    goto :goto_1

    :pswitch_f
    const-string v2, "IPC_ERROR"

    goto :goto_1

    :pswitch_10
    const-string v2, "COMPUTE_ERROR"

    goto :goto_1

    :pswitch_11
    const-string v2, "REQUEST_PROCESSING_ERROR"

    goto :goto_1

    :pswitch_12
    const-string v2, "BAD_REQUEST"

    goto :goto_1

    :pswitch_13
    const-string v2, "BAD_DATA"

    goto :goto_1

    :cond_6
    const-string v2, "BACKGROUND_USE_BLOCKED"

    goto :goto_1

    :cond_7
    const-string v2, "NOT_ENOUGH_DISK_SPACE"

    .line 2
    :goto_1
    filled-new-array {v0, p1, v1, v2, p3}, [Ljava/lang/Object;

    move-result-object p1

    const-string p3, "AICore failed with error type %s-%s and error code %s-%s: %s"

    .line 3
    invoke-static {p3, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    .line 4
    invoke-direct {p0, p1, p4}, Ljava/lang/Exception;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zza:I

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;

    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0xe
        :pswitch_8
        :pswitch_7
        :pswitch_6
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x259
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static zza(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    const/4 v1, 0x1

    const/4 v5, 0x0

    move v2, p0

    move-object v3, p1

    move-object v4, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;-><init>(IILjava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;)V

    return-object v0
.end method

.method public static zzb(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    const/4 v1, 0x2

    const/4 v5, 0x0

    move v2, p0

    move-object v3, p1

    move-object v4, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;-><init>(IILjava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;)V

    return-object v0
.end method

.method public static zzc(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    const/4 v1, 0x3

    const/4 v5, 0x0

    move v2, p0

    move-object v3, p1

    move-object v4, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;-><init>(IILjava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;)V

    return-object v0
.end method

.method public static zzd(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    const/4 v1, 0x5

    const/4 v5, 0x0

    move v2, p0

    move-object v3, p1

    move-object v4, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;-><init>(IILjava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;)V

    return-object v0
.end method

.method public static zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    const/4 v1, 0x4

    const/4 v5, 0x0

    move v2, p0

    move-object v3, p1

    move-object v4, p2

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;-><init>(IILjava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;)V

    return-object v0
.end method

.method public static zzf(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v1, 0x3

    const/16 v2, 0x25e

    move-object v3, p0

    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;-><init>(IILjava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;)V

    return-object v0
.end method

.method public static zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;->zza()I

    move-result v1

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;->zzb()I

    move-result v2

    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;

    move-result-object v5

    .line 2
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;-><init>(IILjava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;)V

    return-object v0
.end method


# virtual methods
.method public final zzg()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zza:I

    return p0
.end method

.method public final zzh()Lj$/time/Duration;
    .locals 2

    .line 2
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbq;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcm;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcm;->zza()F

    move-result p0

    float-to-double v0, p0

    invoke-static {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznz;->zza(D)Lj$/time/Duration;

    move-result-object p0

    return-object p0

    .line 1
    :cond_0
    sget-object p0, Lj$/time/Duration;->ZERO:Lj$/time/Duration;

    return-object p0
.end method
