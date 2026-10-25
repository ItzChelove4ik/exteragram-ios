.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;
.source "SourceFile"


# instance fields
.field private final transient zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

.field private final transient zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-void
.end method


# virtual methods
.method public final contains(Ljava/lang/Object;)Z
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final synthetic iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    invoke-interface {p0}, Ljava/util/Map;->size()I

    move-result p0

    return p0
.end method

.method public final zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzns;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object p0

    return-object p0
.end method

.method public final zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzg([Ljava/lang/Object;I)I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zzg([Ljava/lang/Object;I)I

    move-result p0

    return p0
.end method
