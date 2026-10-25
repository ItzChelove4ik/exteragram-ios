.class public final Lcom/google/android/recaptcha/internal/zzyb;
.super Lcom/google/android/recaptcha/internal/zzaax;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzxx;

.field private final zzb:Ljava/security/spec/ECPoint;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzady;

.field private final zzd:Ljava/lang/Integer;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzxx;Ljava/security/spec/ECPoint;Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Integer;[B)V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaax;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzyb;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzyb;->zzb:Ljava/security/spec/ECPoint;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzyb;->zzc:Lcom/google/android/recaptcha/internal/zzady;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzyb;->zzd:Ljava/lang/Integer;

    return-void
.end method

.method public static zzc()Lcom/google/android/recaptcha/internal/zzya;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzya;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzya;-><init>([B)V

    return-object v0
.end method


# virtual methods
.method public final synthetic zza()Lcom/google/android/recaptcha/internal/zzsl;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyb;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    return-object p0
.end method

.method public final zzb()Ljava/lang/Integer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyb;->zzd:Ljava/lang/Integer;

    return-object p0
.end method

.method public final zzd()Ljava/security/spec/ECPoint;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyb;->zzb:Ljava/security/spec/ECPoint;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzady;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyb;->zzc:Lcom/google/android/recaptcha/internal/zzady;

    return-object p0
.end method

.method public final zzf()Lcom/google/android/recaptcha/internal/zzxx;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzyb;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    return-object p0
.end method
