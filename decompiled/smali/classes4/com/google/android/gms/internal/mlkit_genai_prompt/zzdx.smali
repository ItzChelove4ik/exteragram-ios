.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdx;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;
.source "SourceFile"


# direct methods
.method public synthetic constructor <init>(Z[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;-><init>()V

    return-void
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 1

    const/4 v0, 0x1

    if-ne p1, p0, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of p0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;

    if-eqz p0, :cond_1

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;->zza()Z

    return v0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public final hashCode()I
    .locals 0

    const p0, 0xf4696

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 0

    const-string p0, "UserProperties{isMinorUser=false}"

    return-object p0
.end method

.method public final zza()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method
