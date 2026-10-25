.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:Ljava/lang/Integer;

.field private zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;

.field private zzc:Ljava/lang/Boolean;

.field private zzd:Ljava/lang/Integer;

.field private zze:Ljava/lang/Integer;

.field private zzf:Ljava/lang/Long;

.field private zzg:Ljava/lang/Long;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zza:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;

    return-object p0
.end method

.method public final zzc(Ljava/lang/Boolean;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzc:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final zzd(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzd:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zze(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zze:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzf(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;
    .locals 4

    .line 1
    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    const-wide v2, 0x7fffffffffffffffL

    and-long/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzf:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzg(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;
    .locals 4

    .line 1
    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    const-wide v2, 0x7fffffffffffffffL

    and-long/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzg:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;
    .locals 2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;[B)V

    return-object v0
.end method

.method public final synthetic zzi()Ljava/lang/Integer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zza:Ljava/lang/Integer;

    return-object p0
.end method

.method public final synthetic zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;

    return-object p0
.end method

.method public final synthetic zzk()Ljava/lang/Boolean;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzc:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final synthetic zzl()Ljava/lang/Integer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzd:Ljava/lang/Integer;

    return-object p0
.end method

.method public final synthetic zzm()Ljava/lang/Integer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zze:Ljava/lang/Integer;

    return-object p0
.end method

.method public final synthetic zzn()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzf:Ljava/lang/Long;

    return-object p0
.end method

.method public final synthetic zzo()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzg:Ljava/lang/Long;

    return-object p0
.end method
