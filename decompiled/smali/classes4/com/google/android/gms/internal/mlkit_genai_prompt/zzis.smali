.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;
.source "SourceFile"


# instance fields
.field private zza:Ljava/lang/String;

.field private zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;->zza:Ljava/lang/String;

    return-object p0

    :cond_0
    const-string p0, "Null name"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzb(Ljava/util/Map;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzix;
    .locals 0

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->zzd(Ljava/util/Map;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zziy;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;->zza:Ljava/lang/String;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    if-nez v2, :cond_0

    goto :goto_0

    .line 4
    :cond_0
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzit;

    invoke-direct {p0, v0, v2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzit;-><init>(Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;[B)V

    return-object p0

    .line 1
    :cond_1
    :goto_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;->zza:Ljava/lang/String;

    if-nez v2, :cond_2

    const-string v2, " name"

    .line 2
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzis;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    if-nez p0, :cond_3

    const-string p0, " args"

    .line 3
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v0, "Missing required properties:"

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 4
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
