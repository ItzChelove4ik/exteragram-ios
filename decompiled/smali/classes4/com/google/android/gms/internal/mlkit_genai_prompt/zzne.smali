.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/io/Serializable;


# static fields
.field public static final synthetic $r8$clinit:I

.field private static final zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;


# instance fields
.field final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

.field final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzma;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzma;

    move-result-object v1

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzly;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzly;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    return-void
.end method

.method private constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    .line 2
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    .line 3
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)I

    move-result p0

    if-gtz p0, :cond_0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzly;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzly;

    move-result-object p0

    if-eq p1, p0, :cond_0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzma;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzma;

    move-result-object p0

    if-eq p2, p0, :cond_0

    return-void

    .line 4
    :cond_0
    invoke-static {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Invalid range: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public static zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    return-object v0
.end method

.method private static zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)Ljava/lang/String;
    .locals 2

    .line 1
    new-instance v0, Ljava/lang/StringBuilder;

    const/16 v1, 0x10

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zza(Ljava/lang/StringBuilder;)V

    const-string p0, ".."

    .line 3
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 4
    invoke-virtual {p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzb(Ljava/lang/StringBuilder;)V

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    .line 2
    iget-object v2, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    iget-object p1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    return v1
.end method

.method public final hashCode()I
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->hashCode()I

    move-result v0

    mul-int/lit8 v0, v0, 0x1f

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->hashCode()I

    move-result p0

    add-int/2addr v0, p0

    return v0
.end method

.method public final toString()Ljava/lang/String;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-static {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()Z
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public final zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;
    .locals 6

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    iget-object v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)I

    move-result v2

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    .line 2
    iget-object v4, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)I

    move-result v5

    if-ltz v2, :cond_1

    if-lez v5, :cond_0

    goto :goto_0

    :cond_0
    return-object p0

    :cond_1
    :goto_0
    if-gtz v2, :cond_3

    if-gez v5, :cond_2

    goto :goto_1

    :cond_2
    return-object p1

    :cond_3
    :goto_1
    if-gez v2, :cond_4

    move-object v0, v1

    :cond_4
    if-gtz v5, :cond_5

    goto :goto_2

    :cond_5
    move-object v3, v4

    .line 3
    :goto_2
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)I

    move-result v1

    if-gtz v1, :cond_6

    const/4 v1, 0x1

    goto :goto_3

    :cond_6
    const/4 v1, 0x0

    :goto_3
    const-string v2, "intersection is undefined for disconnected ranges %s and %s"

    .line 4
    invoke-static {v1, v2, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzla;->zzb(ZLjava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    .line 5
    invoke-direct {p0, v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)V

    return-object p0
.end method

.method public final zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;
    .locals 6

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    iget-object v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)I

    move-result v2

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    .line 2
    iget-object v4, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;

    invoke-virtual {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)I

    move-result v5

    if-gtz v2, :cond_1

    if-gez v5, :cond_0

    goto :goto_0

    :cond_0
    return-object p0

    :cond_1
    :goto_0
    if-ltz v2, :cond_3

    if-lez v5, :cond_2

    goto :goto_1

    :cond_2
    return-object p1

    :cond_3
    :goto_1
    if-gtz v2, :cond_4

    goto :goto_2

    :cond_4
    move-object v0, v1

    :goto_2
    if-ltz v5, :cond_5

    goto :goto_3

    :cond_5
    move-object v3, v4

    :goto_3
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;

    .line 3
    invoke-direct {p0, v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzne;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmb;)V

    return-object p0
.end method
