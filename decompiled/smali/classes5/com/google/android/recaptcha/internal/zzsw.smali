.class final Lcom/google/android/recaptcha/internal/zzsw;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzsq;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzse;
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzvb;->zza()Lcom/google/android/recaptcha/internal/zzvb;

    move-result-object p0

    .line 2
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzvb;->zzf(Lcom/google/android/recaptcha/internal/zzsp;)Z

    move-result v0

    if-nez v0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzui;

    .line 3
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzui;-><init>(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)V

    return-object p0

    .line 4
    :cond_0
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzvb;->zzg(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzse;

    move-result-object p0

    return-object p0
.end method

.method public final zzb(Lcom/google/android/recaptcha/internal/zzse;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 0

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzvb;->zza()Lcom/google/android/recaptcha/internal/zzvb;

    move-result-object p0

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzvb;->zzh(Lcom/google/android/recaptcha/internal/zzse;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object p0

    return-object p0
.end method
