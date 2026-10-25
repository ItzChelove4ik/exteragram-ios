.class public final Lcom/google/android/recaptcha/internal/zzahw;
.super Lcom/google/android/recaptcha/internal/zzafq;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahx;->zzg()Lcom/google/android/recaptcha/internal/zzahx;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzafq;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method


# virtual methods
.method public final zza(J)Lcom/google/android/recaptcha/internal/zzahw;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzl()V

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 3
    check-cast v0, Lcom/google/android/recaptcha/internal/zzahx;

    invoke-virtual {v0, p1, p2}, Lcom/google/android/recaptcha/internal/zzahx;->zzd(J)V

    return-object p0
.end method

.method public final zzb(I)Lcom/google/android/recaptcha/internal/zzahw;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzl()V

    :cond_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 3
    check-cast v0, Lcom/google/android/recaptcha/internal/zzahx;

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzahx;->zze(I)V

    return-object p0
.end method
