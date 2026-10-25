.class public final Lcom/google/android/recaptcha/internal/zzam;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field public final zza:Lcom/google/android/recaptcha/internal/zzh;

.field public final zzb:Lcom/google/android/recaptcha/internal/zzai;

.field public final zzc:Lcom/google/android/recaptcha/internal/zzaf;

.field public final zzd:Lcom/google/android/recaptcha/internal/zzaa;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzh;Lcom/google/android/recaptcha/internal/zzai;Lcom/google/android/recaptcha/internal/zzaa;)V
    .locals 9

    const/16 v0, 0x9

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    const/4 v1, 0x0

    aget v1, v0, v1

    const/4 v2, 0x1

    aget v2, v0, v2

    const/4 v3, 0x2

    aget v3, v0, v3

    const/4 v4, 0x3

    aget v4, v0, v4

    const/4 v5, 0x4

    aget v5, v0, v5

    const/4 v6, 0x5

    aget v6, v0, v6

    const/4 v7, 0x6

    aget v7, v0, v7

    const/4 v8, 0x7

    aget v0, v0, v8

    not-int v8, v1

    and-int/2addr v2, v8

    or-int/2addr v2, v3

    and-int/2addr v1, v4

    or-int/2addr v1, v5

    add-int/2addr v2, v1

    sub-int/2addr v2, v6

    add-int/2addr v7, v2

    const v1, 0x16d2fd9a

    rem-int/2addr v0, v1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzam;->zza:Lcom/google/android/recaptcha/internal/zzh;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzam;->zzb:Lcom/google/android/recaptcha/internal/zzai;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzam;->zzd:Lcom/google/android/recaptcha/internal/zzaa;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzaf;

    xor-int p2, v7, v0

    invoke-direct {p1, p2}, Lcom/google/android/recaptcha/internal/zzaf;-><init>(I)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzam;->zzc:Lcom/google/android/recaptcha/internal/zzaf;

    return-void

    :array_0
    .array-data 4
        0x2240f4b2
        0xc1e92b3
        0x31644841
        0x1d1a96f2
        0x51806449
        0x7dfba8f1
        0x467d244
        0x70b76277
        0x16d2fd9a
    .end array-data
.end method


# virtual methods
.method public final zza()Ljava/util/Optional;
    .locals 9

    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzam;->zzc:Lcom/google/android/recaptcha/internal/zzaf;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzaf;->zza:Ljava/util/ArrayDeque;

    invoke-virtual {v0}, Ljava/util/ArrayDeque;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_2

    invoke-virtual {v0}, Ljava/util/ArrayDeque;->pop()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzac;

    iget-wide v1, v0, Lcom/google/android/recaptcha/internal/zzac;->zza:J

    iget-wide v3, v0, Lcom/google/android/recaptcha/internal/zzac;->zzb:J

    iget-wide v5, v0, Lcom/google/android/recaptcha/internal/zzac;->zzc:J

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzam;->zzb:Lcom/google/android/recaptcha/internal/zzai;

    iget v7, v0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    int-to-long v7, v7

    cmp-long v7, v7, v3

    if-gez v7, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzc;->zzG:Lcom/google/android/recaptcha/internal/zzc;

    invoke-static {p0}, Ljava/util/Optional;->of(Ljava/lang/Object;)Ljava/util/Optional;

    move-result-object p0

    return-object p0

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzam;->zzd:Lcom/google/android/recaptcha/internal/zzaa;

    invoke-virtual {p0, v1, v2}, Lcom/google/android/recaptcha/internal/zzaa;->zza(J)V

    const-wide/16 v1, 0x0

    cmp-long p0, v5, v1

    if-nez p0, :cond_1

    :goto_0
    iget p0, v0, Lcom/google/android/recaptcha/internal/zzai;->zzb:I

    int-to-long v1, p0

    cmp-long p0, v1, v3

    if-lez p0, :cond_1

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzai;->zzc()Lcom/google/android/recaptcha/internal/zzar;
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzae; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lcom/google/android/recaptcha/internal/zzag; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lcom/google/android/recaptcha/internal/zzy; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lcom/google/android/recaptcha/internal/zzz; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :cond_1
    invoke-static {}, Ljava/util/Optional;->empty()Ljava/util/Optional;

    move-result-object p0

    return-object p0

    :cond_2
    :try_start_1
    new-instance p0, Lcom/google/android/recaptcha/internal/zzae;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzae;-><init>()V

    throw p0
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzae; {:try_start_1 .. :try_end_1} :catch_2
    .catch Lcom/google/android/recaptcha/internal/zzag; {:try_start_1 .. :try_end_1} :catch_1
    .catch Lcom/google/android/recaptcha/internal/zzy; {:try_start_1 .. :try_end_1} :catch_0
    .catch Lcom/google/android/recaptcha/internal/zzz; {:try_start_1 .. :try_end_1} :catch_0

    :catch_0
    move-exception p0

    new-instance v0, Ljava/lang/AssertionError;

    const-string v1, "CEiv6BFfPnitUE+D"

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzt;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1, p0}, Ljava/lang/AssertionError;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v0

    :catch_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzc;->zzG:Lcom/google/android/recaptcha/internal/zzc;

    :goto_1
    invoke-static {p0}, Ljava/util/Optional;->of(Ljava/lang/Object;)Ljava/util/Optional;

    move-result-object p0

    return-object p0

    :catch_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzc;->zzw:Lcom/google/android/recaptcha/internal/zzc;

    goto :goto_1
.end method
