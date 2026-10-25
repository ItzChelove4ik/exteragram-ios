.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;
.source "SourceFile"


# instance fields
.field private zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

.field private zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

.field private zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    return-object p0
.end method

.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    return-object p0
.end method

.method public final zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzet;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    return-object p0

    :cond_0
    const-string p0, "Null feature"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeu;
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    if-eqz v2, :cond_1

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    if-nez v3, :cond_0

    goto :goto_0

    .line 5
    :cond_0
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzej;

    invoke-direct {p0, v0, v2, v3, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzej;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;[B)V

    return-object p0

    .line 1
    :cond_1
    :goto_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    if-nez v2, :cond_2

    const-string v2, " aiCoreClient"

    .line 2
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    if-nez v2, :cond_3

    const-string v2, " downloadCallback"

    .line 3
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzei;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    if-nez p0, :cond_4

    const-string p0, " feature"

    .line 4
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_4
    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v0, "Missing required properties:"

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 5
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
