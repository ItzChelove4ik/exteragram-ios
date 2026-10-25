.class public final Lcom/google/android/recaptcha/internal/zzalz;
.super Lcom/google/android/recaptcha/internal/zzafq;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzama;->zze()Lcom/google/android/recaptcha/internal/zzama;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzafq;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method


# virtual methods
.method public final zza(Z)Lcom/google/android/recaptcha/internal/zzalz;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzk()V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 2
    check-cast p1, Lcom/google/android/recaptcha/internal/zzama;

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Lcom/google/android/recaptcha/internal/zzama;->zzb(Z)V

    return-object p0
.end method

.method public final zzb(J)Lcom/google/android/recaptcha/internal/zzalz;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzk()V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 2
    check-cast v0, Lcom/google/android/recaptcha/internal/zzama;

    invoke-virtual {v0, p1, p2}, Lcom/google/android/recaptcha/internal/zzama;->zzc(J)V

    return-object p0
.end method

.method public final zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzalz;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzk()V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 2
    check-cast v0, Lcom/google/android/recaptcha/internal/zzama;

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzama;->zzd(Ljava/lang/String;)V

    return-object p0
.end method
