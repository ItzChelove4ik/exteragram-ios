.class public abstract Lcom/google/android/recaptcha/internal/zzrv;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzrv;

.field private static final zzb:Lcom/google/android/recaptcha/internal/zzrv;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzrs;

    const/16 v1, 0x3d

    .line 2
    invoke-static {v1}, Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;

    move-result-object v1

    const-string v2, "base64()"

    const-string v3, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

    invoke-direct {v0, v2, v3, v1}, Lcom/google/android/recaptcha/internal/zzrs;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Character;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzrv;->zza:Lcom/google/android/recaptcha/internal/zzrv;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzrs;

    const-string v2, "base64Url()"

    const-string v3, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_"

    .line 3
    invoke-direct {v0, v2, v3, v1}, Lcom/google/android/recaptcha/internal/zzrs;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Character;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzrv;->zzb:Lcom/google/android/recaptcha/internal/zzrv;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzru;

    const-string v2, "base32()"

    const-string v3, "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567"

    .line 4
    invoke-direct {v0, v2, v3, v1}, Lcom/google/android/recaptcha/internal/zzru;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Character;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzru;

    const-string v2, "base32Hex()"

    const-string v3, "0123456789ABCDEFGHIJKLMNOPQRSTUV"

    .line 5
    invoke-direct {v0, v2, v3, v1}, Lcom/google/android/recaptcha/internal/zzru;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Character;)V

    .line 6
    sget v0, Lcom/google/android/recaptcha/internal/zzrr;->$r8$clinit:I

    new-instance v0, Lcom/google/android/recaptcha/internal/zzrq;

    const-string v1, "base16()"

    .line 7
    const-string v2, "0123456789ABCDEF"

    invoke-virtual {v2}, Ljava/lang/String;->toCharArray()[C

    move-result-object v2

    invoke-direct {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzrq;-><init>(Ljava/lang/String;[C)V

    new-instance v1, Lcom/google/android/recaptcha/internal/zzru;

    const/4 v2, 0x0

    .line 8
    invoke-direct {v1, v0, v2}, Lcom/google/android/recaptcha/internal/zzru;-><init>(Lcom/google/android/recaptcha/internal/zzrq;Ljava/lang/Character;)V

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzrq;->zze()[C

    move-result-object v1

    array-length v1, v1

    const/16 v2, 0x10

    const/4 v3, 0x0

    if-ne v1, v2, :cond_0

    const/4 v1, 0x1

    goto :goto_0

    :cond_0
    move v1, v3

    :goto_0
    const/16 v2, 0x200

    new-array v2, v2, [C

    .line 9
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzoq;->zza(Z)V

    :goto_1
    const/16 v1, 0x100

    if-ge v3, v1, :cond_1

    ushr-int/lit8 v1, v3, 0x4

    .line 10
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzrq;->zza(I)C

    move-result v1

    aput-char v1, v2, v3

    or-int/lit16 v1, v3, 0x100

    and-int/lit8 v4, v3, 0xf

    .line 11
    invoke-virtual {v0, v4}, Lcom/google/android/recaptcha/internal/zzrq;->zza(I)C

    move-result v4

    aput-char v4, v2, v1

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    :cond_1
    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzi()Lcom/google/android/recaptcha/internal/zzrv;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzrv;->zza:Lcom/google/android/recaptcha/internal/zzrv;

    return-object v0
.end method

.method public static zzj()Lcom/google/android/recaptcha/internal/zzrv;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzrv;->zzb:Lcom/google/android/recaptcha/internal/zzrv;

    return-object v0
.end method


# virtual methods
.method public abstract zza(Ljava/lang/Appendable;[BII)V
.end method

.method public abstract zzb([BLjava/lang/CharSequence;)I
.end method

.method public abstract zzc(I)I
.end method

.method public abstract zze(I)I
.end method

.method public abstract zzf(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
.end method

.method public final zzg([BII)Ljava/lang/String;
    .locals 2

    .line 1
    array-length p2, p1

    const/4 v0, 0x0

    invoke-static {v0, p3, p2}, Lcom/google/android/recaptcha/internal/zzoq;->zzg(III)V

    new-instance p2, Ljava/lang/StringBuilder;

    .line 2
    invoke-virtual {p0, p3}, Lcom/google/android/recaptcha/internal/zzrv;->zzc(I)I

    move-result v1

    invoke-direct {p2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 3
    :try_start_0
    invoke-virtual {p0, p2, p1, v0, p3}, Lcom/google/android/recaptcha/internal/zzrv;->zza(Ljava/lang/Appendable;[BII)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 4
    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception p0

    invoke-static {p0}, Lokio/Buffer$$ExternalSyntheticBUOutline2;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzh(Ljava/lang/CharSequence;)[B
    .locals 2

    .line 1
    :try_start_0
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzrv;->zzf(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    .line 2
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzrv;->zze(I)I

    move-result v0

    new-array v1, v0, [B

    .line 3
    invoke-virtual {p0, v1, p1}, Lcom/google/android/recaptcha/internal/zzrv;->zzb([BLjava/lang/CharSequence;)I

    move-result p0

    if-ne p0, v0, :cond_0

    return-object v1

    .line 4
    :cond_0
    new-array p1, p0, [B

    const/4 v0, 0x0

    .line 5
    invoke-static {v1, v0, p1, v0, p0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzrt; {:try_start_0 .. :try_end_0} :catch_0

    return-object p1

    :catch_0
    move-exception p0

    .line 6
    invoke-static {p0}, Landroidx/core/app/NavUtils$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method
