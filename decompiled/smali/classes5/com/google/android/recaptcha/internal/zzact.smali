.class public abstract Lcom/google/android/recaptcha/internal/zzact;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza(Lcom/google/android/recaptcha/internal/zzsk;Lcom/google/android/recaptcha/internal/zzvt;)Lcom/google/android/recaptcha/internal/zzst;
    .locals 5

    .line 1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzvk;

    invoke-direct {p1}, Lcom/google/android/recaptcha/internal/zzvk;-><init>()V

    const/4 v0, 0x0

    .line 2
    :goto_0
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzsk;->zzc()I

    move-result v1

    if-ge v0, v1, :cond_7

    move-object v1, p0

    check-cast v1, Lcom/google/android/recaptcha/internal/zzsj;

    .line 3
    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzsj;->zzd(I)Lcom/google/android/recaptcha/internal/zzsi;

    move-result-object v1

    .line 4
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zzb()Lcom/google/android/recaptcha/internal/zzsg;

    move-result-object v2

    sget-object v3, Lcom/google/android/recaptcha/internal/zzsg;->zza:Lcom/google/android/recaptcha/internal/zzsg;

    invoke-virtual {v2, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_6

    .line 5
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zza()Lcom/google/android/recaptcha/internal/zzse;

    move-result-object v2

    instance-of v3, v2, Lcom/google/android/recaptcha/internal/zzyb;

    if-eqz v3, :cond_0

    .line 6
    check-cast v2, Lcom/google/android/recaptcha/internal/zzyb;

    .line 7
    sget-object v3, Lcom/google/android/recaptcha/internal/zzabm;->zza:Lcom/google/android/recaptcha/internal/zztv;

    .line 8
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzti;->zza()Ljava/security/Provider;

    move-result-object v3

    .line 9
    invoke-static {v2, v3}, Lcom/google/android/recaptcha/internal/zzabm;->zzb(Lcom/google/android/recaptcha/internal/zzyb;Ljava/security/Provider;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object v2

    goto :goto_1

    .line 16
    :cond_0
    instance-of v3, v2, Lcom/google/android/recaptcha/internal/zzaal;

    if-eqz v3, :cond_1

    .line 10
    check-cast v2, Lcom/google/android/recaptcha/internal/zzaal;

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzadv;->zzb(Lcom/google/android/recaptcha/internal/zzaal;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object v2

    goto :goto_1

    :cond_1
    instance-of v3, v2, Lcom/google/android/recaptcha/internal/zzzz;

    if-eqz v3, :cond_2

    .line 11
    check-cast v2, Lcom/google/android/recaptcha/internal/zzzz;

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzadr;->zzb(Lcom/google/android/recaptcha/internal/zzzz;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object v2

    goto :goto_1

    :cond_2
    instance-of v3, v2, Lcom/google/android/recaptcha/internal/zzyo;

    if-eqz v3, :cond_5

    .line 12
    check-cast v2, Lcom/google/android/recaptcha/internal/zzyo;

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzacx;->zzb(Lcom/google/android/recaptcha/internal/zzyo;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object v2

    .line 14
    :goto_1
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zza()Lcom/google/android/recaptcha/internal/zzse;

    move-result-object v3

    instance-of v4, v3, Lcom/google/android/recaptcha/internal/zzaax;

    if-eqz v4, :cond_3

    .line 15
    check-cast v3, Lcom/google/android/recaptcha/internal/zzaax;

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaax;->zze()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v3

    goto :goto_2

    .line 18
    :cond_3
    instance-of v4, v3, Lcom/google/android/recaptcha/internal/zzui;

    if-eqz v4, :cond_4

    .line 16
    check-cast v3, Lcom/google/android/recaptcha/internal/zzui;

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzui;->zzd()Lcom/google/android/recaptcha/internal/zzady;

    move-result-object v3

    .line 15
    :goto_2
    new-instance v4, Lcom/google/android/recaptcha/internal/zzacs;

    .line 17
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zzc()I

    move-result v1

    invoke-direct {v4, v2, v1}, Lcom/google/android/recaptcha/internal/zzacs;-><init>(Lcom/google/android/recaptcha/internal/zzst;I)V

    .line 18
    invoke-virtual {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzvk;->zza(Lcom/google/android/recaptcha/internal/zzady;Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzvk;

    goto :goto_3

    .line 43
    :cond_4
    invoke-virtual {v3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    .line 19
    new-instance p1, Ljava/security/GeneralSecurityException;

    .line 20
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    .line 21
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzse;->zza()Lcom/google/android/recaptcha/internal/zzsl;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, 0x3b

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr v1, v2

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "Cannot get output prefix for key of class "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " with parameters "

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 28
    :cond_5
    invoke-virtual {v2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    .line 38
    new-instance p1, Ljava/security/GeneralSecurityException;

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "Unknown key class: "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_6
    :goto_3
    add-int/lit8 v0, v0, 0x1

    goto/16 :goto_0

    .line 12
    :cond_7
    const-class v0, Lcom/google/android/recaptcha/internal/zzul;

    .line 39
    invoke-interface {p0, v0}, Lcom/google/android/recaptcha/internal/zzsk;->zze(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzsb;

    .line 43
    sget-object p0, Lcom/google/android/recaptcha/internal/zzup;->zza:Lcom/google/android/recaptcha/internal/zzum;

    .line 42
    new-instance v0, Lcom/google/android/recaptcha/internal/zzacr;

    .line 43
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzvk;->zzb()Lcom/google/android/recaptcha/internal/zzvm;

    move-result-object p1

    invoke-direct {v0, p1, p0}, Lcom/google/android/recaptcha/internal/zzacr;-><init>(Lcom/google/android/recaptcha/internal/zzvm;Lcom/google/android/recaptcha/internal/zzum;)V

    return-object v0
.end method
