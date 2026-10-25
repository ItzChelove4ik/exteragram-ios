.class final Lcom/google/android/recaptcha/internal/zzacw;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzst;


# instance fields
.field private final zza:[B

.field private final zzb:[B

.field private final zzc:[B


# direct methods
.method public synthetic constructor <init>([B[B[B[B)V
    .locals 2

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p4, 0x1

    invoke-static {p4}, Lcom/google/android/recaptcha/internal/zzte;->zza(I)Z

    move-result p4

    const/4 v0, 0x0

    if-eqz p4, :cond_1

    .line 2
    array-length p4, p1

    const/16 v1, 0x20

    if-ne p4, v1, :cond_0

    .line 4
    invoke-virtual {p1}, [B->clone()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, [B

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzacw;->zza:[B

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzacw;->zzb:[B

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzacw;->zzc:[B

    .line 5
    invoke-static {}, Lcom/google/android/recaptcha/internal/zztp;->zzd()V

    return-void

    .line 3
    :cond_0
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string p1, "Given public key\'s length is not %s."

    invoke-static {p1, p0}, Lcom/google/protobuf/FieldSet$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[Ljava/lang/Object;)V

    throw v0

    .line 2
    :cond_1
    new-instance p0, Ljava/security/GeneralSecurityException;

    const-string p1, "Can not use Ed25519 in FIPS-mode."

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    invoke-static {p0}, Lcom/google/protobuf/UnsafeUtil$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    throw v0
.end method

.method private final zzb([B[B)V
    .locals 2

    .line 1
    array-length v0, p1

    const/16 v1, 0x40

    if-ne v0, v1, :cond_1

    .line 3
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzacw;->zza:[B

    .line 4
    invoke-static {p2, p1, p0}, Lcom/google/android/recaptcha/internal/zztp;->zzc([B[B[B)Z

    move-result p0

    if-eqz p0, :cond_0

    return-void

    .line 5
    :cond_0
    const-string p0, "Signature check failed."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 2
    :cond_1
    new-instance p0, Ljava/security/GeneralSecurityException;

    .line 3
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string p2, "The length of the signature is not %s."

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0
.end method


# virtual methods
.method public final zza([B[B)V
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzacw;->zzb:[B

    array-length v1, v0

    if-nez v1, :cond_0

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzacw;->zzc:[B

    array-length v2, v2

    if-nez v2, :cond_0

    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzacw;->zzb([B[B)V

    return-void

    .line 2
    :cond_0
    invoke-static {v0, p1}, Lcom/google/android/recaptcha/internal/zzwc;->zzc([B[B)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 3
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzacw;->zzc:[B

    array-length v2, v0

    if-eqz v2, :cond_1

    filled-new-array {p2, v0}, [[B

    move-result-object p2

    .line 4
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzacu;->zza([[B)[B

    move-result-object p2

    .line 5
    :cond_1
    array-length v0, p1

    invoke-static {p1, v1, v0}, Ljava/util/Arrays;->copyOfRange([BII)[B

    move-result-object p1

    .line 6
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzacw;->zzb([B[B)V

    return-void

    .line 3
    :cond_2
    const-string p0, "Invalid signature (output prefix mismatch)"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method
