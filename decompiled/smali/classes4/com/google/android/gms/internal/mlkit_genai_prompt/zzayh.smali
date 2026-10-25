.class abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final synthetic $r8$clinit:I

.field private static final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavq;->$r8$clinit:I

    .line 2
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzays;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzays;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    return-void
.end method

.method public static zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    return-object v0
.end method

.method public static zzb(Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 2

    const/4 v0, 0x1

    if-eq p0, p1, :cond_1

    const/4 v1, 0x0

    if-eqz p0, :cond_0

    .line 1
    invoke-virtual {p0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    return v0

    :cond_0
    return v1

    :cond_1
    return v0
.end method

.method public static zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 0

    .line 1
    invoke-static {p2}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public static zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 1

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 2
    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object p2, p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object v0

    .line 4
    invoke-virtual {v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object v0

    .line 5
    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 6
    invoke-static {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object p0

    goto :goto_0

    .line 7
    :cond_0
    invoke-virtual {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 9
    :cond_1
    :goto_0
    iput-object p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    return-void
.end method

.method public static zze(Ljava/lang/Object;IILjava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;)Ljava/lang/Object;
    .locals 2

    if-nez p3, :cond_0

    .line 1
    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object p4

    if-ne p3, p4, :cond_0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object p3

    .line 2
    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    :cond_0
    int-to-long v0, p2

    shl-int/lit8 p0, p1, 0x3

    .line 3
    move-object p1, p3

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 4
    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    invoke-virtual {p1, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzg(ILjava/lang/Object;)V

    return-object p3
.end method
