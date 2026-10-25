.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;
.source "SourceFile"


# instance fields
.field private final transient zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

.field private final transient zzb:[Ljava/lang/Object;

.field private final transient zzc:I


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;[Ljava/lang/Object;II)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;->zzb:[Ljava/lang/Object;

    iput p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;->zzc:I

    return-void
.end method


# virtual methods
.method public final contains(Ljava/lang/Object;)Z
    .locals 2

    .line 1
    instance-of v0, p1, Ljava/util/Map$Entry;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 2
    check-cast p1, Ljava/util/Map$Entry;

    .line 3
    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    .line 4
    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    .line 5
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    return v1
.end method

.method public final synthetic iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    const/4 v0, 0x0

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;->zzc:I

    return p0
.end method

.method public final zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzns;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    const/4 v0, 0x0

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzs(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zznt;

    move-result-object p0

    return-object p0
.end method

.method public final zzg([Ljava/lang/Object;I)I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmg;->zzg([Ljava/lang/Object;I)I

    move-result p0

    return p0
.end method

.method public final zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznh;

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznh;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;)V

    return-object v0
.end method

.method public final synthetic zzl()[Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;->zzb:[Ljava/lang/Object;

    return-object p0
.end method

.method public final synthetic zzm()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzni;->zzc:I

    return p0
.end method
