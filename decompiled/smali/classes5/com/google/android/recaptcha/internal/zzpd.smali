.class final Lcom/google/android/recaptcha/internal/zzpd;
.super Lcom/google/android/recaptcha/internal/zzpf;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzpj;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzpj;)V
    .locals 1

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzpd;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzpf;-><init>(Lcom/google/android/recaptcha/internal/zzpj;[B)V

    return-void
.end method


# virtual methods
.method public final zza(I)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzpd;->zza:Lcom/google/android/recaptcha/internal/zzpj;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzpj;->zzo(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
