.class public final Lcom/google/android/recaptcha/internal/zzl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzj;


# instance fields
.field private zza:I

.field private final zzb:[B

.field private final zzc:Lcom/google/android/recaptcha/internal/zzn;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzn;)V
    .locals 5

    const v0, 0x1b7e7b17

    not-int v1, v0

    const v2, 0xa62861c

    and-int/2addr v1, v2

    const v2, 0x1a961021

    or-int/2addr v1, v2

    const v2, -0xf8f79e2

    and-int/2addr v0, v2

    const v2, -0xc6d9f7e

    or-int/2addr v0, v2

    add-int/2addr v1, v0

    const v0, 0x19df080d

    sub-int/2addr v1, v0

    const v0, 0x22509110

    const v2, 0x7247c47d

    rem-int/2addr v2, v0

    const v0, 0x7547b1d6

    not-int v3, v0

    const v4, 0x4e394f36    # 7.77244E8f

    and-int/2addr v3, v4

    const v4, 0x358190f1

    or-int/2addr v3, v4

    const v4, 0x7a7a4f06

    and-int/2addr v0, v4

    const v4, 0x31c610b1

    or-int/2addr v0, v4

    add-int/2addr v3, v0

    const v0, -0x62cc0ff1

    sub-int/2addr v3, v0

    const v0, 0x3eab2035

    const v4, 0x52f720c6

    rem-int/2addr v4, v0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    xor-int v0, v1, v2

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzl;->zza:I

    xor-int v0, v3, v4

    new-array v0, v0, [B

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzl;->zzb:[B

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzl;->zzc:Lcom/google/android/recaptcha/internal/zzn;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzv;I)B
    .locals 7

    const v0, 0x1f337328    # 3.799998E-20f

    not-int v1, v0

    const v2, 0x268548cc

    and-int/2addr v1, v2

    const v2, 0xb84a12d

    or-int/2addr v1, v2

    const v2, 0x742148c0

    and-int/2addr v0, v2

    const v2, 0x5bb0b539

    or-int/2addr v0, v2

    add-int/2addr v1, v0

    const v0, -0x77adb835

    sub-int/2addr v1, v0

    const v0, 0x5205bdf3

    const v2, 0x54ea154b

    rem-int/2addr v2, v0

    const v0, 0x74d2c83f

    not-int v3, v0

    const v4, 0x5442ad35

    and-int/2addr v3, v4

    const v4, 0x73a4bb96

    or-int/2addr v3, v4

    const v4, 0x254254e9

    and-int/2addr v0, v4

    const v4, 0x79a552ce

    or-int/2addr v0, v4

    add-int/2addr v3, v0

    const v0, -0x1b2f1494

    sub-int/2addr v3, v0

    const v0, 0x27a6946f

    const v4, 0x5c084fef

    rem-int/2addr v4, v0

    xor-int v0, v1, v2

    const v1, 0xa7ad484

    not-int v2, v1

    const v5, 0x6899810b

    and-int/2addr v2, v5

    const v5, 0x55b62ab5

    or-int/2addr v2, v5

    const v5, 0x280f914a

    and-int/2addr v1, v5

    const v5, 0x17867ef0

    or-int/2addr v1, v5

    add-int/2addr v2, v1

    const v1, -0x72a8a2b9

    sub-int/2addr v2, v1

    const v1, 0x3e4d44e8

    const v5, 0x463c1258

    rem-int/2addr v5, v1

    ushr-int v0, p2, v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzl;->zza:I

    if-eq v0, v1, :cond_0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzl;->zzc:Lcom/google/android/recaptcha/internal/zzn;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzl;->zzb:[B

    invoke-virtual {v1, v0, v6}, Lcom/google/android/recaptcha/internal/zzn;->zza(I[B)V

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzl;->zza:I

    :cond_0
    xor-int v0, v2, v5

    xor-int v1, v3, v4

    invoke-virtual {p1, p2}, Lcom/google/android/recaptcha/internal/zzv;->zzb(I)B

    move-result p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzl;->zzb:[B

    rem-int/2addr p2, v1

    aget-byte p0, p0, p2

    xor-int/2addr p0, p1

    shl-int/2addr p0, v0

    shr-int/2addr p0, v0

    int-to-byte p0, p0

    return p0
.end method

.method public final zzb(Lcom/google/android/recaptcha/internal/zzv;II)Lcom/google/android/recaptcha/internal/zzv;
    .locals 3

    if-ltz p2, :cond_1

    if-gt p2, p3, :cond_1

    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zzv;->zza:[B

    array-length v0, v0

    if-gt p3, v0, :cond_1

    sub-int v0, p3, p2

    new-array v0, v0, [B

    const/4 v1, 0x0

    :goto_0
    if-ge p2, p3, :cond_0

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzl;->zza(Lcom/google/android/recaptcha/internal/zzv;I)B

    move-result v2

    aput-byte v2, v0, v1

    add-int/lit8 p2, p2, 0x1

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzv;->zze([B)Lcom/google/android/recaptcha/internal/zzv;

    move-result-object p0

    return-object p0

    :cond_1
    new-instance p0, Ljava/lang/IndexOutOfBoundsException;

    invoke-direct {p0}, Ljava/lang/IndexOutOfBoundsException;-><init>()V

    throw p0
.end method

.method public final bridge synthetic zzc()Lcom/google/android/recaptcha/internal/zzj;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzl;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzl;->zzc:Lcom/google/android/recaptcha/internal/zzn;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzl;-><init>(Lcom/google/android/recaptcha/internal/zzn;)V

    return-object v0
.end method
