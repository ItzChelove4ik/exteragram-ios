.class public final Lcom/google/android/recaptcha/internal/zzyr;
.super Lcom/google/android/recaptcha/internal/zzaav;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzyp;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzyq;


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzyp;Lcom/google/android/recaptcha/internal/zzyq;)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaav;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzyr;->zza:Lcom/google/android/recaptcha/internal/zzyp;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzyr;->zzb:Lcom/google/android/recaptcha/internal/zzyq;

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzyp;Lcom/google/android/recaptcha/internal/zzyq;)Lcom/google/android/recaptcha/internal/zzyr;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzyr;

    invoke-direct {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzyr;-><init>(Lcom/google/android/recaptcha/internal/zzyp;Lcom/google/android/recaptcha/internal/zzyq;)V

    return-object v0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzyr;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzyr;

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzyr;->zza:Lcom/google/android/recaptcha/internal/zzyp;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzyr;->zza:Lcom/google/android/recaptcha/internal/zzyp;

    if-ne v0, v2, :cond_1

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzyr;->zzb:Lcom/google/android/recaptcha/internal/zzyq;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyr;->zzb:Lcom/google/android/recaptcha/internal/zzyq;

    if-ne p1, p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method public final hashCode()I
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzyr;->zza:Lcom/google/android/recaptcha/internal/zzyp;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyr;->zzb:Lcom/google/android/recaptcha/internal/zzyq;

    const-class v1, Lcom/google/android/recaptcha/internal/zzyr;

    filled-new-array {v1, v0, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzyr;->zza:Lcom/google/android/recaptcha/internal/zzyp;

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyr;->zzb:Lcom/google/android/recaptcha/internal/zzyq;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v1, v1, 0x2f

    add-int/2addr v1, v2

    new-instance v2, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x1

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "ML-DSA Parameters (ML-DSA instance: "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", variant: "

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
