.class public final Lcom/google/android/recaptcha/internal/zzyh;
.super Lcom/google/android/recaptcha/internal/zzaav;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzyg;


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzyg;)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaav;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzyh;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzyg;)Lcom/google/android/recaptcha/internal/zzyh;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzyh;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzyh;-><init>(Lcom/google/android/recaptcha/internal/zzyg;)V

    return-object v0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 2

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzyh;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzyh;

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzyh;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyh;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    if-ne p1, p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method public final hashCode()I
    .locals 1

    .line 1
    const-class v0, Lcom/google/android/recaptcha/internal/zzyh;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyh;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    filled-new-array {v0, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 2

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyh;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    new-instance v1, Ljava/lang/StringBuilder;

    add-int/lit8 v0, v0, 0x1e

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v0, "Ed25519 Parameters (variant: "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzyg;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyh;->zza:Lcom/google/android/recaptcha/internal/zzyg;

    return-object p0
.end method
