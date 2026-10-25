.class public abstract Lcom/google/android/recaptcha/internal/zzaat;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzsc;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzaas;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzaas;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaat;->zza:Lcom/google/android/recaptcha/internal/zzsc;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzsc;
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zztf;->zza()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaat;->zza:Lcom/google/android/recaptcha/internal/zzsc;

    return-object v0

    :cond_0
    const-string v0, "Cannot use non-FIPS-compliant SignatureConfig2026 in FIPS mode"

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 v0, 0x0

    return-object v0
.end method
