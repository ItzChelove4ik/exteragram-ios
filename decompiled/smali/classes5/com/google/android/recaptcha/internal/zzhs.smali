.class public abstract Lcom/google/android/recaptcha/internal/zzhs;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static final zza(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzri;->zzb()Lcom/google/android/recaptcha/internal/zzrf;

    move-result-object v0

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-interface {v0, p0, v1}, Lcom/google/android/recaptcha/internal/zzrf;->zza(Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)Lcom/google/android/recaptcha/internal/zzre;

    move-result-object p0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzre;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
