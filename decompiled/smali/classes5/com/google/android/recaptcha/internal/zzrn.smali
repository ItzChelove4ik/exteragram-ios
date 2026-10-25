.class abstract Lcom/google/android/recaptcha/internal/zzrn;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final synthetic $r8$clinit:I

.field private static final zzb:Lcom/google/android/recaptcha/internal/zzrk;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    :try_start_0
    const-string v0, "os.arch"

    invoke-static {v0}, Ljava/lang/System;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "amd64"

    .line 2
    invoke-static {v0, v1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 3
    invoke-static {}, Ljava/nio/ByteOrder;->nativeOrder()Ljava/nio/ByteOrder;

    move-result-object v0

    sget-object v1, Ljava/nio/ByteOrder;->LITTLE_ENDIAN:Ljava/nio/ByteOrder;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 4
    sget-object v0, Lcom/google/android/recaptcha/internal/zzrm;->zza:Lcom/google/android/recaptcha/internal/zzrm;

    goto :goto_0

    .line 5
    :cond_0
    sget-object v0, Lcom/google/android/recaptcha/internal/zzrm;->zzb:Lcom/google/android/recaptcha/internal/zzrm;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    :cond_1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzrj;->zza:Lcom/google/android/recaptcha/internal/zzrj;

    .line 4
    :goto_0
    sput-object v0, Lcom/google/android/recaptcha/internal/zzrn;->zzb:Lcom/google/android/recaptcha/internal/zzrk;

    return-void
.end method

.method public static zza([BI)J
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzrn;->zzb:Lcom/google/android/recaptcha/internal/zzrk;

    invoke-interface {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzrk;->zza([BI)J

    move-result-wide p0

    return-wide p0
.end method
