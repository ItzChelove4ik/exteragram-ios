.class public final Lcom/google/android/recaptcha/internal/zzakr;
.super Lcom/google/android/recaptcha/internal/zzafq;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaks;->zzj()Lcom/google/android/recaptcha/internal/zzaks;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzafq;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzako;)Lcom/google/android/recaptcha/internal/zzakr;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzk()V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 2
    check-cast v0, Lcom/google/android/recaptcha/internal/zzaks;

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzaks;->zzg(Lcom/google/android/recaptcha/internal/zzako;)V

    return-object p0
.end method

.method public final zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakr;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzk()V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaks;

    const-string v0, "18.9.2"

    invoke-virtual {p1, v0}, Lcom/google/android/recaptcha/internal/zzaks;->zzh(Ljava/lang/String;)V

    return-object p0
.end method

.method public final zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzakr;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzk()V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 2
    check-cast v0, Lcom/google/android/recaptcha/internal/zzaks;

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzaks;->zzi(Ljava/lang/String;)V

    return-object p0
.end method
