.class public final Lcom/google/android/recaptcha/internal/zzaea;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzady;


# direct methods
.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzady;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaea;->zza:Lcom/google/android/recaptcha/internal/zzady;

    return-void
.end method

.method public static zza([BLcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzaea;
    .locals 0

    .line 1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzaea;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzady;->zza([B)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object p0

    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzaea;-><init>(Lcom/google/android/recaptcha/internal/zzady;)V

    return-object p1
.end method


# virtual methods
.method public final zzb(Lcom/google/android/recaptcha/internal/zzsy;)[B
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaea;->zza:Lcom/google/android/recaptcha/internal/zzady;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzady;->zzc()[B

    move-result-object p0

    return-object p0
.end method

.method public final zzc()I
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaea;->zza:Lcom/google/android/recaptcha/internal/zzady;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzady;->zzd()I

    move-result p0

    return p0
.end method
