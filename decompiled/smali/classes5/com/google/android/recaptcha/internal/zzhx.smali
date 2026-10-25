.class public final Lcom/google/android/recaptcha/internal/zzhx;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzaer;

.field private final zzb:[B


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzhx;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    sget p1, Lcom/google/android/recaptcha/internal/zzair;->$r8$clinit:I

    const/16 p1, 0xc

    new-array p1, p1, [B

    new-instance v0, Ljava/security/SecureRandom;

    .line 2
    invoke-direct {v0}, Ljava/security/SecureRandom;-><init>()V

    invoke-virtual {v0, p1}, Ljava/security/SecureRandom;->nextBytes([B)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzhx;->zzb:[B

    return-void
.end method


# virtual methods
.method public final zza()[B
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzhx;->zzb:[B

    return-object p0
.end method

.method public final zzb([BI)[B
    .locals 4

    int-to-long v0, p2

    .line 1
    invoke-static {v0, v1}, Ljava/math/BigInteger;->valueOf(J)Ljava/math/BigInteger;

    move-result-object p2

    invoke-virtual {p2}, Ljava/math/BigInteger;->toByteArray()[B

    move-result-object p2

    .line 2
    array-length v0, p2

    const/16 v1, 0xc

    rsub-int/lit8 v0, v0, 0xc

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzhx;->zzb:[B

    const/4 v3, 0x0

    invoke-static {v2, v3, v0}, Lkotlin/collections/ArraysKt;->copyOfRange([BII)[B

    move-result-object v0

    invoke-static {v0, p2}, Lkotlin/collections/ArraysKt;->plus([B[B)[B

    move-result-object p2

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzhx;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p0

    invoke-static {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzair;->zzb([B[B[B)[B

    move-result-object p0

    .line 4
    invoke-static {p0, v1}, Lkotlin/collections/ArraysKt;->drop([BI)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/util/Collection;

    .line 5
    invoke-static {p0}, Lkotlin/collections/CollectionsKt;->toByteArray(Ljava/util/Collection;)[B

    move-result-object p0

    return-object p0
.end method
