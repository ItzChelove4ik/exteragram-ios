.class public final Lcom/google/android/recaptcha/internal/zzpv;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field zza:[Ljava/lang/Object;

.field zzb:I

.field zzc:Lcom/google/android/recaptcha/internal/zzpu;


# direct methods
.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x4

    .line 1
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzpv;-><init>(I)V

    return-void
.end method

.method public constructor <init>(I)V
    .locals 0

    .line 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    add-int/2addr p1, p1

    new-array p1, p1, [Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpv;->zza:[Ljava/lang/Object;

    const/4 p1, 0x0

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzpv;->zzb:I

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzpv;
    .locals 3

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzpv;->zzb:I

    add-int/lit8 v0, v0, 0x1

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzpv;->zza:[Ljava/lang/Object;

    array-length v2, v1

    add-int/2addr v0, v0

    if-le v0, v2, :cond_0

    .line 2
    invoke-static {v2, v0}, Lcom/google/android/recaptcha/internal/zzpp;->zzc(II)I

    move-result v0

    .line 3
    invoke-static {v1, v0}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzpv;->zza:[Ljava/lang/Object;

    .line 4
    :cond_0
    invoke-static {p1, p2}, Lcom/google/android/recaptcha/internal/zzpa;->zza(Ljava/lang/Object;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzpv;->zza:[Ljava/lang/Object;

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzpv;->zzb:I

    add-int v2, v1, v1

    .line 5
    aput-object p1, v0, v2

    add-int/lit8 v2, v2, 0x1

    .line 6
    aput-object p2, v0, v2

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzpv;->zzb:I

    return-object p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzpw;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzpv;->zzc:Lcom/google/android/recaptcha/internal/zzpu;

    if-nez v0, :cond_1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzpv;->zzb:I

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzpv;->zza:[Ljava/lang/Object;

    .line 2
    invoke-static {v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzqq;->zzf(I[Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzpv;)Lcom/google/android/recaptcha/internal/zzqq;

    move-result-object v0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpv;->zzc:Lcom/google/android/recaptcha/internal/zzpu;

    if-nez p0, :cond_0

    return-object v0

    .line 3
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpu;->zza()Ljava/lang/IllegalArgumentException;

    move-result-object p0

    throw p0

    .line 1
    :cond_1
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzpu;->zza()Ljava/lang/IllegalArgumentException;

    move-result-object p0

    throw p0
.end method
