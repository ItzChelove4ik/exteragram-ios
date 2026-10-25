.class public final Lcom/google/android/recaptcha/internal/zzlo;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:J

.field private final zzc:I


# direct methods
.method public constructor <init>(Ljava/lang/String;JI)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzlo;->zza:Ljava/lang/String;

    iput-wide p2, p0, Lcom/google/android/recaptcha/internal/zzlo;->zzb:J

    iput p4, p0, Lcom/google/android/recaptcha/internal/zzlo;->zzc:I

    return-void
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 4

    .line 1
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzlo;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/google/android/recaptcha/internal/zzlo;

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzlo;->zza:Ljava/lang/String;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzlo;->zza:Ljava/lang/String;

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2
    iget-wide v0, p1, Lcom/google/android/recaptcha/internal/zzlo;->zzb:J

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzlo;->zzb:J

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    .line 3
    iget p1, p1, Lcom/google/android/recaptcha/internal/zzlo;->zzc:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzlo;->zzc:I

    if-ne p1, p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlo;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzlo;->zzb:J

    return-wide v0
.end method

.method public final zzc()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzlo;->zzc:I

    return p0
.end method
