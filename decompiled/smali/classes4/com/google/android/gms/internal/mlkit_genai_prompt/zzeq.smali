.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaw;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzer;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaw;-><init>()V

    return-void
.end method


# virtual methods
.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzah;)V
    .locals 8

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzo()I

    move-result v1

    const/4 v2, 0x1

    if-eq v1, v2, :cond_2

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzo()I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    goto :goto_1

    .line 4
    :cond_0
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->$r8$clinit:I

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    .line 5
    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;-><init>()V

    .line 6
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzah;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v2

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    invoke-interface {v1, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzac;

    .line 7
    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzac;->zza()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzac;->zzb()F

    move-result v6

    iget v4, v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzac;->zza:I

    new-instance v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzee;

    .line 8
    invoke-direct {v7, v5, v6, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzee;-><init>(Ljava/lang/String;FI)V

    .line 9
    invoke-virtual {v0, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zzd(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 10
    :cond_1
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzah;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    move-result-object p1

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeh;

    .line 11
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    const/4 v2, -0x1

    invoke-direct {v1, v0, p1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeh;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;I)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;

    .line 12
    invoke-interface {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;->zza(Ljava/lang/Object;)V

    return-void

    .line 1
    :cond_2
    :goto_1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzah;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    move-result-object v0

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzah;->zzc()I

    move-result p1

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeh;

    .line 2
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v2

    invoke-direct {v1, v2, v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeh;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;I)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;

    .line 3
    invoke-interface {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;->zza(Ljava/lang/Object;)V

    return-void
.end method

.method public final zzc(I)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;

    invoke-interface {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;->zzb(I)V

    return-void
.end method
