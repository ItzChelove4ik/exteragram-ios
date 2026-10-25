.class public final Lcom/google/android/recaptcha/internal/zzaal;
.super Lcom/google/android/recaptcha/internal/zzaax;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzaah;

.field private final zzb:Ljava/math/BigInteger;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzady;

.field private final zzd:Ljava/lang/Integer;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzaah;Ljava/math/BigInteger;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaax;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaal;->zza:Lcom/google/android/recaptcha/internal/zzaah;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzaal;->zzb:Ljava/math/BigInteger;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzaal;->zzc:Lcom/google/android/recaptcha/internal/zzady;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzaal;->zzd:Ljava/lang/Integer;

    return-void
.end method

.method public static zzc()Lcom/google/android/recaptcha/internal/zzaak;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzaak;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzaak;-><init>([B)V

    return-object v0
.end method


# virtual methods
.method public final synthetic zza()Lcom/google/android/recaptcha/internal/zzsl;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaal;->zza:Lcom/google/android/recaptcha/internal/zzaah;

    return-object p0
.end method

.method public final zzb()Ljava/lang/Integer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaal;->zzd:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzd()Ljava/math/BigInteger;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaal;->zzb:Ljava/math/BigInteger;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzady;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaal;->zzc:Lcom/google/android/recaptcha/internal/zzady;

    return-object p0
.end method

.method public final zzf()Lcom/google/android/recaptcha/internal/zzaah;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaal;->zza:Lcom/google/android/recaptcha/internal/zzaah;

    return-object p0
.end method
