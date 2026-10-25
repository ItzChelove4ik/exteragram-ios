.class public final Lcom/google/android/recaptcha/internal/zzamj;
.super Lcom/google/android/recaptcha/internal/zzafq;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzagy;


# direct methods
.method public synthetic constructor <init>([B)V
    .locals 0

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzamk;->zzg()Lcom/google/android/recaptcha/internal/zzamk;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzafq;-><init>(Lcom/google/android/recaptcha/internal/zzafv;)V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzajh;)Lcom/google/android/recaptcha/internal/zzamj;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzk()V

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafq;->zza:Lcom/google/android/recaptcha/internal/zzafv;

    .line 2
    check-cast v0, Lcom/google/android/recaptcha/internal/zzamk;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzajj;

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzamk;->zze(Lcom/google/android/recaptcha/internal/zzajj;)V

    return-object p0
.end method
