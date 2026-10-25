.class public abstract Lcom/google/android/recaptcha/internal/zzacq;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza(Lcom/google/android/recaptcha/internal/zzsk;Lcom/google/android/recaptcha/internal/zzvt;)Lcom/google/android/recaptcha/internal/zzss;
    .locals 4

    .line 1
    sget p1, Lcom/google/android/recaptcha/internal/zzwc;->$r8$clinit:I

    const/4 p1, 0x0

    .line 2
    :goto_0
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzsk;->zzc()I

    move-result v0

    if-ge p1, v0, :cond_5

    move-object v0, p0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzsj;

    .line 3
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzsj;->zzd(I)Lcom/google/android/recaptcha/internal/zzsi;

    move-result-object v1

    .line 4
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zzd()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zzb()Lcom/google/android/recaptcha/internal/zzsg;

    move-result-object v1

    sget-object v2, Lcom/google/android/recaptcha/internal/zzsg;->zza:Lcom/google/android/recaptcha/internal/zzsg;

    invoke-virtual {v1, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    const-class p1, Lcom/google/android/recaptcha/internal/zzul;

    .line 5
    invoke-interface {p0, p1}, Lcom/google/android/recaptcha/internal/zzsk;->zze(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzsb;

    .line 15
    sget-object p0, Lcom/google/android/recaptcha/internal/zzup;->zza:Lcom/google/android/recaptcha/internal/zzum;

    .line 8
    new-instance p1, Lcom/google/android/recaptcha/internal/zzaco;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzacp;

    .line 9
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzsj;->zzb()Lcom/google/android/recaptcha/internal/zzsi;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzsi;->zza()Lcom/google/android/recaptcha/internal/zzse;

    move-result-object v2

    instance-of v3, v2, Lcom/google/android/recaptcha/internal/zzxz;

    if-eqz v3, :cond_0

    .line 10
    check-cast v2, Lcom/google/android/recaptcha/internal/zzxz;

    .line 11
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzabl;->zzb(Lcom/google/android/recaptcha/internal/zzxz;)Lcom/google/android/recaptcha/internal/zzss;

    move-result-object v2

    goto :goto_1

    .line 9
    :cond_0
    instance-of v3, v2, Lcom/google/android/recaptcha/internal/zzaaj;

    if-eqz v3, :cond_1

    .line 12
    check-cast v2, Lcom/google/android/recaptcha/internal/zzaaj;

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzadt;->zzb(Lcom/google/android/recaptcha/internal/zzaaj;)Lcom/google/android/recaptcha/internal/zzss;

    move-result-object v2

    goto :goto_1

    :cond_1
    instance-of v3, v2, Lcom/google/android/recaptcha/internal/zzzx;

    if-eqz v3, :cond_2

    .line 13
    check-cast v2, Lcom/google/android/recaptcha/internal/zzzx;

    .line 14
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzacd;->zzb(Lcom/google/android/recaptcha/internal/zzzx;)Lcom/google/android/recaptcha/internal/zzss;

    move-result-object v2

    goto :goto_1

    :cond_2
    instance-of v3, v2, Lcom/google/android/recaptcha/internal/zzyi;

    if-eqz v3, :cond_3

    .line 15
    check-cast v2, Lcom/google/android/recaptcha/internal/zzyi;

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzacv;->zzb(Lcom/google/android/recaptcha/internal/zzyi;)Lcom/google/android/recaptcha/internal/zzss;

    move-result-object v2

    .line 9
    :goto_1
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzsj;->zzb()Lcom/google/android/recaptcha/internal/zzsi;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzsi;->zzc()I

    move-result v0

    invoke-direct {v1, v2, v0}, Lcom/google/android/recaptcha/internal/zzacp;-><init>(Lcom/google/android/recaptcha/internal/zzss;I)V

    invoke-direct {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzaco;-><init>(Lcom/google/android/recaptcha/internal/zzacp;Lcom/google/android/recaptcha/internal/zzum;)V

    return-object p1

    .line 21
    :cond_3
    invoke-virtual {v2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    .line 33
    new-instance p1, Ljava/security/GeneralSecurityException;

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "Unknown key class: "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_4
    add-int/lit8 p1, p1, 0x1

    goto/16 :goto_0

    .line 34
    :cond_5
    const-string p0, "keyset doesn\'t contain a valid primary key"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method
