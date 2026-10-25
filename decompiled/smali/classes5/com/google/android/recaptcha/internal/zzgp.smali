.class public final Lcom/google/android/recaptcha/internal/zzgp;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzd;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzd;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzgp;->zza:Lcom/google/android/recaptcha/internal/zzd;

    return-void
.end method

.method public static final zzb()Ljava/lang/String;
    .locals 13

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzrv;->zzi()Lcom/google/android/recaptcha/internal/zzrv;

    move-result-object v0

    const v1, 0x100f8fca

    not-int v2, v1

    const v3, 0x61107243

    and-int/2addr v2, v3

    const v3, 0x1e4e0fd0

    or-int/2addr v2, v3

    const v3, 0x697e7107

    and-int/2addr v1, v3

    const v3, 0x8ee014c

    or-int/2addr v1, v3

    add-int/2addr v2, v1

    const v1, 0x7c8518bd

    sub-int/2addr v2, v1

    const v1, 0x1381823a

    const v3, 0x1f48eaa1

    rem-int/2addr v3, v1

    const v1, 0x14e17e33

    not-int v4, v1

    const v5, 0x44acc0ab

    and-int/2addr v4, v5

    const v5, 0x6ccf0572

    or-int/2addr v4, v5

    const v5, 0x121d099

    and-int/2addr v1, v5

    const v5, 0x2dd71650

    or-int/2addr v1, v5

    add-int/2addr v4, v1

    const v1, 0x6c2f7bcb

    sub-int/2addr v4, v1

    const v1, 0x4c04a8af    # 3.477574E7f

    const v5, 0x76272110

    rem-int/2addr v5, v1

    xor-int v1, v4, v5

    xor-int/2addr v2, v3

    const/16 v3, 0x9

    new-array v3, v3, [I

    fill-array-data v3, :array_0

    const/4 v4, 0x0

    aget v5, v3, v4

    const/4 v6, 0x1

    aget v6, v3, v6

    const/4 v7, 0x2

    aget v7, v3, v7

    const/4 v8, 0x3

    aget v8, v3, v8

    const/4 v9, 0x4

    aget v9, v3, v9

    const/4 v10, 0x5

    aget v10, v3, v10

    const/4 v11, 0x6

    aget v11, v3, v11

    const/4 v12, 0x7

    aget v3, v3, v12

    not-int v12, v5

    and-int/2addr v6, v12

    or-int/2addr v6, v7

    and-int/2addr v5, v8

    or-int/2addr v5, v9

    add-int/2addr v6, v5

    sub-int/2addr v6, v10

    add-int/2addr v11, v6

    const v5, 0x6a2342ec

    rem-int/2addr v3, v5

    xor-int/2addr v3, v11

    .line 2
    invoke-static {v3}, Ljava/nio/ByteBuffer;->allocate(I)Ljava/nio/ByteBuffer;

    move-result-object v3

    sget-object v5, Ljava/nio/ByteOrder;->LITTLE_ENDIAN:Ljava/nio/ByteOrder;

    invoke-virtual {v3, v5}, Ljava/nio/ByteBuffer;->order(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;

    int-to-short v2, v2

    invoke-virtual {v3, v2}, Ljava/nio/ByteBuffer;->putShort(S)Ljava/nio/ByteBuffer;

    invoke-virtual {v3, v1}, Ljava/nio/ByteBuffer;->putInt(I)Ljava/nio/ByteBuffer;

    invoke-virtual {v3}, Ljava/nio/ByteBuffer;->array()[B

    move-result-object v1

    .line 3
    array-length v2, v1

    invoke-virtual {v0, v1, v4, v2}, Lcom/google/android/recaptcha/internal/zzrv;->zzg([BII)Ljava/lang/String;

    move-result-object v0

    return-object v0

    nop

    :array_0
    .array-data 4
        0x1d4ed43b
        0x30ca86e8
        0x47a4c807
        0x304b07e8
        0x4a258914    # 2712133.0f
        -0x235ea091
        0x211012a4
        0x70a64e2a
        0x6a2342ec
    .end array-data
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzd;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzgp;->zza:Lcom/google/android/recaptcha/internal/zzd;

    return-object p0
.end method
