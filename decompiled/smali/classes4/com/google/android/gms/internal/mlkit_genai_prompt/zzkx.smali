.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

.field private zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;[B)V
    .locals 0

    .line 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    invoke-direct {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;-><init>()V

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zza:Ljava/lang/String;

    return-void
.end method

.method private final zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;-><init>()V

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    iput-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    return-object v0
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 4

    .line 1
    new-instance v0, Ljava/lang/StringBuilder;

    const/16 v1, 0x20

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zza:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v1, 0x7b

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    const-string v1, ""

    :goto_0
    if-eqz p0, :cond_2

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zzb:Ljava/lang/Object;

    .line 2
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zza:Ljava/lang/String;

    if-eqz v1, :cond_0

    .line 3
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v1, 0x3d

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    :cond_0
    if-eqz v2, :cond_1

    invoke-virtual {v2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    .line 4
    invoke-virtual {v1}, Ljava/lang/Class;->isArray()Z

    move-result v1

    if-eqz v1, :cond_1

    filled-new-array {v2}, [Ljava/lang/Object;

    move-result-object v1

    .line 5
    invoke-static {v1}, Ljava/util/Arrays;->deepToString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    .line 6
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    const/4 v3, 0x1

    invoke-virtual {v0, v1, v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;II)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 7
    :cond_1
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 6
    :goto_1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    const-string v1, ", "

    goto :goto_0

    :cond_2
    const/16 p0, 0x7d

    .line 8
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zza(Ljava/lang/String;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    move-result-object v0

    iput-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zzb:Ljava/lang/Object;

    .line 2
    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb(Ljava/lang/String;I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;
    .locals 2

    .line 1
    invoke-static {p2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkv;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkv;-><init>([B)V

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    .line 2
    iput-object v0, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    iput-object p2, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zzb:Ljava/lang/Object;

    .line 3
    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;

    move-result-object v0

    iput-object p1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkw;->zzb:Ljava/lang/Object;

    return-object p0
.end method
