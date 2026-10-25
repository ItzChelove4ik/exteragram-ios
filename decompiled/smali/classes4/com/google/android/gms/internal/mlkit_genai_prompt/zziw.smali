.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zziw;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zziw;
    .locals 4

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zza()I

    move-result v0

    if-eqz v0, :cond_6

    const/4 v1, 0x1

    if-eq v0, v1, :cond_5

    const/4 v1, 0x2

    if-eq v0, v1, :cond_4

    const/4 v1, 0x3

    if-eq v0, v1, :cond_3

    const/4 v1, 0x4

    if-eq v0, v1, :cond_1

    const/4 v1, 0x5

    if-ne v0, v1, :cond_0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzg()J

    move-result-wide v0

    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzip;

    invoke-direct {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzip;-><init>(J)V

    return-object p0

    .line 15
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zza()I

    move-result p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, 0x18

    const-string v1, "Unsupported value kind: "

    invoke-static {v0, v1, p0}, Lcom/google/android/gms/cast/internal/zzar$$ExternalSyntheticBUOutline1;->m(ILjava/lang/Object;I)V

    const/4 p0, 0x0

    return-object p0

    .line 3
    :cond_1
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->$r8$clinit:I

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    .line 4
    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;-><init>()V

    .line 5
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_2

    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;

    .line 6
    invoke-static {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zziw;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zziw;

    move-result-object v3

    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zzd(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 7
    :cond_2
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    .line 8
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    .line 9
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzio;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzio;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V

    return-object v0

    .line 10
    :cond_3
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zze()D

    move-result-wide v0

    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzim;

    invoke-direct {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzim;-><init>(D)V

    return-object p0

    .line 11
    :cond_4
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzd()I

    move-result p0

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzin;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzin;-><init>(I)V

    return-object v0

    .line 12
    :cond_5
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzc()Z

    move-result p0

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzil;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzil;-><init>(Z)V

    return-object v0

    .line 13
    :cond_6
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzb()Ljava/lang/String;

    move-result-object p0

    .line 14
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zziq;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zziq;-><init>(Ljava/lang/String;)V

    return-object v0
.end method


# virtual methods
.method public abstract zza()Z
.end method

.method public abstract zzb()I
.end method

.method public abstract zzc()D
.end method

.method public abstract zzd()I
.end method

.method public abstract zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method

.method public abstract zzf()J
.end method

.method public abstract zzg()Ljava/lang/String;
.end method
