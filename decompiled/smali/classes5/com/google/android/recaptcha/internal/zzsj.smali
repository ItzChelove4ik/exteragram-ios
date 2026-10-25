.class public final Lcom/google/android/recaptcha/internal/zzsj;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzsk;


# instance fields
.field private final zza:Ljava/util/List;

.field private final zzb:Ljava/util/Map;


# direct methods
.method private constructor <init>(Ljava/util/List;Ljava/util/Map;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzsj;->zza:Ljava/util/List;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzsj;->zzb:Ljava/util/Map;

    return-void
.end method

.method public static final zzf([B)Lcom/google/android/recaptcha/internal/zzsj;
    .locals 7
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    :try_start_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsx;->zza()Lcom/google/android/recaptcha/internal/zzsc;

    move-result-object v0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafh;->zzb()Lcom/google/android/recaptcha/internal/zzafh;

    move-result-object v1

    invoke-static {p0, v1}, Lcom/google/android/recaptcha/internal/zzwy;->zzd([BLcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzwy;

    move-result-object p0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwy;->zzb()Ljava/util/List;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_7

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzwx;

    .line 3
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzwr;->zzi()I

    move-result v3

    const/4 v4, 0x4

    const/4 v5, 0x3

    const/4 v6, 0x2

    if-eq v3, v6, :cond_1

    .line 4
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzwr;->zzi()I

    move-result v3

    if-eq v3, v5, :cond_1

    .line 5
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzwr;->zzi()I

    move-result v3

    if-ne v3, v4, :cond_0

    .line 19
    :cond_1
    new-instance p0, Ljava/security/GeneralSecurityException;

    const-string v0, "keyset contains key material of type %s for type url %s"

    .line 20
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzwr;->zzi()I

    move-result v1

    if-eq v1, v6, :cond_6

    if-eq v1, v5, :cond_5

    if-eq v1, v4, :cond_4

    const/4 v3, 0x5

    if-eq v1, v3, :cond_3

    const/4 v3, 0x6

    if-eq v1, v3, :cond_2

    const-string v1, "UNRECOGNIZED"

    goto :goto_0

    .line 22
    :cond_2
    const-string v1, "REMOTE"

    goto :goto_0

    :cond_3
    const-string v1, "ASYMMETRIC_PUBLIC"

    goto :goto_0

    :cond_4
    const-string v1, "ASYMMETRIC_PRIVATE"

    goto :goto_0

    :cond_5
    const-string v1, "SYMMETRIC"

    goto :goto_0

    .line 18
    :cond_6
    const-string v1, "UNKNOWN_KEYMATERIAL"

    .line 20
    :goto_0
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzwr;->zza()Ljava/lang/String;

    move-result-object v2

    filled-new-array {v1, v2}, [Ljava/lang/Object;

    move-result-object v1

    .line 21
    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 6
    :cond_7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwy;->zzc()I

    move-result v1

    if-lez v1, :cond_c

    .line 7
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzsj;->zzi(Lcom/google/android/recaptcha/internal/zzwy;Lcom/google/android/recaptcha/internal/zzsc;)Ljava/util/List;

    move-result-object p0

    .line 8
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzsj;->zzh(Lcom/google/android/recaptcha/internal/zzsc;)Z

    move-result v0

    if-eqz v0, :cond_b

    const-string v0, "KeyID "

    const-string v1, " is duplicated in the keyset, and Tink is configured to reject such keysets with the flag validateKeysetsOnParsing."

    new-instance v2, Ljava/util/HashSet;

    .line 9
    invoke-direct {v2}, Ljava/util/HashSet;-><init>()V

    .line 10
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    const/4 v4, 0x0

    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_9

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzsi;

    .line 11
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzsi;->zzc()I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v2, v6}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v6

    if-nez v6, :cond_8

    .line 12
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzsi;->zzc()I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v2, v6}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 13
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzsi;->zzd()Z

    move-result v5

    or-int/2addr v4, v5

    goto :goto_1

    .line 14
    :cond_8
    new-instance p0, Ljava/security/GeneralSecurityException;

    .line 15
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzsi;->zzc()I

    move-result v2

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    add-int/lit8 v3, v3, 0x79

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v3}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    :cond_9
    if-eqz v4, :cond_a

    goto :goto_2

    .line 16
    :cond_a
    new-instance p0, Ljava/security/GeneralSecurityException;

    const-string v0, "Primary key id not found in keyset, and Tink is configured to reject such keysets with the flag validateKeysetsOnParsing."

    invoke-direct {p0, v0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 13
    :cond_b
    :goto_2
    new-instance v0, Lcom/google/android/recaptcha/internal/zzsj;

    new-instance v1, Ljava/util/HashMap;

    .line 17
    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    invoke-direct {v0, p0, v1}, Lcom/google/android/recaptcha/internal/zzsj;-><init>(Ljava/util/List;Ljava/util/Map;)V

    return-object v0

    .line 18
    :cond_c
    new-instance p0, Ljava/security/GeneralSecurityException;

    const-string v0, "empty keyset"

    invoke-direct {p0, v0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    .line 22
    :catch_0
    const-string p0, "invalid keyset"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private static zzh(Lcom/google/android/recaptcha/internal/zzsc;)Z
    .locals 1

    .line 1
    const-class v0, Lcom/google/android/recaptcha/internal/zzvz;

    invoke-interface {p0, v0}, Lcom/google/android/recaptcha/internal/zzsc;->zzb(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static zzi(Lcom/google/android/recaptcha/internal/zzwy;Lcom/google/android/recaptcha/internal/zzsc;)Ljava/util/List;
    .locals 12

    .line 1
    new-instance v1, Ljava/util/ArrayList;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwy;->zzc()I

    move-result v0

    invoke-direct {v1, v0}, Ljava/util/ArrayList;-><init>(I)V

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwy;->zzb()Ljava/util/List;

    move-result-object v0

    .line 2
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    move-object v3, v0

    check-cast v3, Lcom/google/android/recaptcha/internal/zzwx;

    .line 3
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzwx;->zzc()I

    move-result v7

    const/4 v4, 0x1

    const/4 v5, 0x0

    .line 4
    :try_start_0
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzsj;->zzj(Lcom/google/android/recaptcha/internal/zzwx;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object v0

    const-class v6, Lcom/google/android/recaptcha/internal/zzsq;

    .line 5
    invoke-interface {p1, v6}, Lcom/google/android/recaptcha/internal/zzsc;->zzb(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/google/android/recaptcha/internal/zzsq;

    if-eqz v6, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsy;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v8

    .line 6
    invoke-interface {v6, v0, v8}, Lcom/google/android/recaptcha/internal/zzsq;->zza(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzse;

    move-result-object v0

    move v9, v5

    goto :goto_2

    :catch_0
    move-exception v0

    goto :goto_1

    .line 7
    :cond_0
    new-instance v0, Ljava/security/GeneralSecurityException;

    const-string v6, "Passed in configuration cannot be used to serialize into proto keyset format."

    invoke-direct {v0, v6}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_0
    .catch Ljava/security/GeneralSecurityException; {:try_start_0 .. :try_end_0} :catch_0

    .line 8
    :goto_1
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzsj;->zzh(Lcom/google/android/recaptcha/internal/zzsc;)Z

    move-result v6

    if-nez v6, :cond_4

    .line 7
    new-instance v0, Lcom/google/android/recaptcha/internal/zzui;

    .line 9
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzsj;->zzj(Lcom/google/android/recaptcha/internal/zzwx;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object v6

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsy;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v8

    invoke-direct {v0, v6, v8}, Lcom/google/android/recaptcha/internal/zzui;-><init>(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)V

    move v9, v4

    .line 10
    :goto_2
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzsj;->zzh(Lcom/google/android/recaptcha/internal/zzsc;)Z

    move-result v6

    if-eqz v6, :cond_1

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzwx;->zzi()I

    move-result v6

    invoke-static {v6}, Lcom/google/android/recaptcha/internal/zzsj;->zzk(I)Z

    move-result v6

    if-eqz v6, :cond_2

    :cond_1
    move v6, v4

    goto :goto_3

    .line 14
    :cond_2
    const-string p0, "Parsing of a single key failed (wrong status) and Tink is configured via validateKeysetsOnParsing to reject such keysets."

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :goto_3
    new-instance v4, Lcom/google/android/recaptcha/internal/zzsi;

    .line 11
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzwx;->zzi()I

    move-result v3

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwy;->zza()I

    move-result v8

    if-ne v7, v8, :cond_3

    move v8, v6

    goto :goto_4

    :cond_3
    move v8, v5

    :goto_4
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsi;->zzg()Lcom/google/android/recaptcha/internal/zzsh;

    move-result-object v10

    const/4 v11, 0x0

    move-object v5, v0

    move v6, v3

    invoke-direct/range {v4 .. v11}, Lcom/google/android/recaptcha/internal/zzsi;-><init>(Lcom/google/android/recaptcha/internal/zzse;IIZZLcom/google/android/recaptcha/internal/zzsh;[B)V

    .line 12
    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 13
    :cond_4
    throw v0

    .line 15
    :cond_5
    invoke-static {v1}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method private static zzj(Lcom/google/android/recaptcha/internal/zzwx;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 9

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwx;->zzc()I

    move-result v0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwx;->zzj()I

    move-result v1

    const/4 v2, 0x5

    if-ne v1, v2, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    .line 3
    :goto_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzwr;->zza()Ljava/lang/String;

    move-result-object v1

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzwr;->zzb()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v3

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzwr;->zzi()I

    move-result v4

    add-int/lit8 v4, v4, -0x2

    const/4 v5, 0x4

    const/4 v6, 0x3

    const/4 v7, 0x2

    const/4 v8, 0x1

    if-eqz v4, :cond_5

    if-eq v4, v8, :cond_4

    if-eq v4, v7, :cond_3

    if-eq v4, v6, :cond_2

    if-ne v4, v5, :cond_1

    .line 11
    sget-object v4, Lcom/google/android/recaptcha/internal/zzsn;->zze:Lcom/google/android/recaptcha/internal/zzsn;

    goto :goto_1

    .line 6
    :cond_1
    new-instance p0, Ljava/security/GeneralSecurityException;

    .line 7
    invoke-static {v4}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "Unknown KeyMaterialType: "

    invoke-virtual {v1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 6
    invoke-direct {p0, v0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 11
    :cond_2
    sget-object v4, Lcom/google/android/recaptcha/internal/zzsn;->zzd:Lcom/google/android/recaptcha/internal/zzsn;

    goto :goto_1

    :cond_3
    sget-object v4, Lcom/google/android/recaptcha/internal/zzsn;->zzc:Lcom/google/android/recaptcha/internal/zzsn;

    goto :goto_1

    :cond_4
    sget-object v4, Lcom/google/android/recaptcha/internal/zzsn;->zzb:Lcom/google/android/recaptcha/internal/zzsn;

    goto :goto_1

    .line 6
    :cond_5
    sget-object v4, Lcom/google/android/recaptcha/internal/zzsn;->zza:Lcom/google/android/recaptcha/internal/zzsn;

    .line 8
    :goto_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwx;->zzj()I

    move-result p0

    add-int/lit8 p0, p0, -0x2

    if-eqz p0, :cond_b

    if-eq p0, v8, :cond_a

    if-eq p0, v7, :cond_9

    if-eq p0, v6, :cond_8

    if-eq p0, v5, :cond_7

    if-ne p0, v2, :cond_6

    .line 11
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzf:Lcom/google/android/recaptcha/internal/zzso;

    goto :goto_2

    .line 9
    :cond_6
    new-instance v0, Ljava/security/GeneralSecurityException;

    .line 10
    invoke-static {p0}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object p0

    const-string v1, "Unknown OutputPrefixType: "

    invoke-virtual {v1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 9
    invoke-direct {v0, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 11
    :cond_7
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zze:Lcom/google/android/recaptcha/internal/zzso;

    goto :goto_2

    :cond_8
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    goto :goto_2

    :cond_9
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzc:Lcom/google/android/recaptcha/internal/zzso;

    goto :goto_2

    :cond_a
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    goto :goto_2

    .line 9
    :cond_b
    sget-object p0, Lcom/google/android/recaptcha/internal/zzso;->zza:Lcom/google/android/recaptcha/internal/zzso;

    .line 11
    :goto_2
    invoke-static {v1, v3, v4, p0, v0}, Lcom/google/android/recaptcha/internal/zzsp;->zza(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzsn;Lcom/google/android/recaptcha/internal/zzso;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object p0

    return-object p0
.end method

.method private static zzk(I)Z
    .locals 2

    add-int/lit8 p0, p0, -0x2

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v1, 0x2

    if-eq p0, v1, :cond_0

    const/4 v1, 0x3

    if-eq p0, v1, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 4

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsj;->zza()Lcom/google/android/recaptcha/internal/zzwy;

    move-result-object p0

    .line 2
    sget v0, Lcom/google/android/recaptcha/internal/zzta;->$r8$clinit:I

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxc;->zza()Lcom/google/android/recaptcha/internal/zzwz;

    move-result-object v0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwy;->zza()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzwz;->zza(I)Lcom/google/android/recaptcha/internal/zzwz;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzwy;->zzb()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzwx;

    .line 5
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzxb;->zza()Lcom/google/android/recaptcha/internal/zzxa;

    move-result-object v2

    .line 6
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzwx;->zzb()Lcom/google/android/recaptcha/internal/zzwr;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzwr;->zza()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzxa;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzxa;

    .line 7
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzwx;->zzi()I

    move-result v3

    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzxa;->zzc(I)Lcom/google/android/recaptcha/internal/zzxa;

    .line 8
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzwx;->zzj()I

    move-result v3

    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzxa;->zzd(I)Lcom/google/android/recaptcha/internal/zzxa;

    .line 9
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzwx;->zzc()I

    move-result v1

    invoke-virtual {v2, v1}, Lcom/google/android/recaptcha/internal/zzxa;->zzb(I)Lcom/google/android/recaptcha/internal/zzxa;

    .line 10
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzxb;

    .line 11
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzwz;->zzb(Lcom/google/android/recaptcha/internal/zzxb;)Lcom/google/android/recaptcha/internal/zzwz;

    goto :goto_0

    .line 12
    :cond_0
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzxc;

    .line 13
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zza()Lcom/google/android/recaptcha/internal/zzwy;
    .locals 17

    .line 1
    :try_start_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsx;->zza()Lcom/google/android/recaptcha/internal/zzsc;

    move-result-object v0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwy;->zze()Lcom/google/android/recaptcha/internal/zzwv;

    move-result-object v1

    move-object/from16 v2, p0

    iget-object v2, v2, Lcom/google/android/recaptcha/internal/zzsj;->zza:Ljava/util/List;

    .line 2
    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_f

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/google/android/recaptcha/internal/zzsi;

    .line 3
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzsi;->zza()Lcom/google/android/recaptcha/internal/zzse;

    move-result-object v4

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzsi;->zzh()I

    move-result v5

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzsi;->zzc()I

    move-result v6

    const-class v7, Lcom/google/android/recaptcha/internal/zzsq;

    .line 4
    invoke-interface {v0, v7}, Lcom/google/android/recaptcha/internal/zzsc;->zzb(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/google/android/recaptcha/internal/zzsq;

    if-eqz v7, :cond_e

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsy;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object v8

    .line 5
    invoke-interface {v7, v4, v8}, Lcom/google/android/recaptcha/internal/zzsq;->zzb(Lcom/google/android/recaptcha/internal/zzse;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object v7

    .line 6
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzse;->zzb()Ljava/lang/Integer;

    move-result-object v4

    if-eqz v4, :cond_2

    .line 7
    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    if-ne v4, v6, :cond_1

    goto :goto_1

    .line 34
    :cond_1
    new-instance v0, Ljava/security/GeneralSecurityException;

    const-string v1, "Wrong ID set for key with ID requirement"

    invoke-direct {v0, v1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 8
    :cond_2
    :goto_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwx;->zzd()Lcom/google/android/recaptcha/internal/zzww;

    move-result-object v4

    .line 9
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzwr;->zzc()Lcom/google/android/recaptcha/internal/zzwq;

    move-result-object v8

    invoke-virtual {v7}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object v9

    .line 10
    invoke-virtual {v8, v9}, Lcom/google/android/recaptcha/internal/zzwq;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzwq;

    invoke-virtual {v7}, Lcom/google/android/recaptcha/internal/zzsp;->zzc()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v9

    .line 11
    invoke-virtual {v8, v9}, Lcom/google/android/recaptcha/internal/zzwq;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzwq;

    invoke-virtual {v7}, Lcom/google/android/recaptcha/internal/zzsp;->zzd()Lcom/google/android/recaptcha/internal/zzsn;

    move-result-object v9

    const-string v10, "Unknown KeyMaterialType: "

    sget-object v11, Lcom/google/android/recaptcha/internal/zzsn;->zza:Lcom/google/android/recaptcha/internal/zzsn;

    .line 12
    invoke-virtual {v9, v11}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v11

    const/4 v12, 0x6

    const/4 v13, 0x5

    const/4 v14, 0x4

    const/4 v15, 0x3

    const/16 v16, 0x2

    if-eqz v11, :cond_3

    move/from16 v9, v16

    goto :goto_2

    .line 26
    :cond_3
    sget-object v11, Lcom/google/android/recaptcha/internal/zzsn;->zzb:Lcom/google/android/recaptcha/internal/zzsn;

    if-eq v9, v11, :cond_7

    .line 13
    sget-object v11, Lcom/google/android/recaptcha/internal/zzsn;->zzc:Lcom/google/android/recaptcha/internal/zzsn;

    if-eq v9, v11, :cond_6

    .line 14
    sget-object v11, Lcom/google/android/recaptcha/internal/zzsn;->zzd:Lcom/google/android/recaptcha/internal/zzsn;

    if-eq v9, v11, :cond_5

    .line 15
    sget-object v11, Lcom/google/android/recaptcha/internal/zzsn;->zze:Lcom/google/android/recaptcha/internal/zzsn;

    if-ne v9, v11, :cond_4

    move v9, v12

    goto :goto_2

    .line 33
    :cond_4
    new-instance v0, Ljava/security/GeneralSecurityException;

    invoke-virtual {v9}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v10, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_5
    move v9, v13

    goto :goto_2

    :cond_6
    move v9, v14

    goto :goto_2

    :cond_7
    move v9, v15

    .line 17
    :goto_2
    invoke-virtual {v8, v9}, Lcom/google/android/recaptcha/internal/zzwq;->zzc(I)Lcom/google/android/recaptcha/internal/zzwq;

    .line 18
    invoke-virtual {v4, v8}, Lcom/google/android/recaptcha/internal/zzww;->zza(Lcom/google/android/recaptcha/internal/zzwq;)Lcom/google/android/recaptcha/internal/zzww;

    .line 19
    invoke-virtual {v4, v5}, Lcom/google/android/recaptcha/internal/zzww;->zzc(I)Lcom/google/android/recaptcha/internal/zzww;

    .line 20
    invoke-virtual {v4, v6}, Lcom/google/android/recaptcha/internal/zzww;->zzb(I)Lcom/google/android/recaptcha/internal/zzww;

    invoke-virtual {v7}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v5

    const-string v6, "Unknown OutputPrefixType: "

    sget-object v7, Lcom/google/android/recaptcha/internal/zzso;->zza:Lcom/google/android/recaptcha/internal/zzso;

    .line 21
    invoke-virtual {v5, v7}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_8

    move/from16 v12, v16

    goto :goto_3

    .line 31
    :cond_8
    sget-object v7, Lcom/google/android/recaptcha/internal/zzso;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    if-eq v5, v7, :cond_c

    .line 22
    sget-object v7, Lcom/google/android/recaptcha/internal/zzso;->zzc:Lcom/google/android/recaptcha/internal/zzso;

    if-eq v5, v7, :cond_b

    .line 23
    sget-object v7, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    if-eq v5, v7, :cond_a

    .line 24
    sget-object v7, Lcom/google/android/recaptcha/internal/zzso;->zze:Lcom/google/android/recaptcha/internal/zzso;

    if-eq v5, v7, :cond_d

    .line 25
    sget-object v7, Lcom/google/android/recaptcha/internal/zzso;->zzf:Lcom/google/android/recaptcha/internal/zzso;

    if-ne v5, v7, :cond_9

    const/4 v12, 0x7

    goto :goto_3

    .line 32
    :cond_9
    new-instance v0, Ljava/security/GeneralSecurityException;

    invoke-virtual {v5}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_a
    move v12, v13

    goto :goto_3

    :cond_b
    move v12, v14

    goto :goto_3

    :cond_c
    move v12, v15

    .line 27
    :cond_d
    :goto_3
    invoke-virtual {v4, v12}, Lcom/google/android/recaptcha/internal/zzww;->zzd(I)Lcom/google/android/recaptcha/internal/zzww;

    .line 28
    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v4

    check-cast v4, Lcom/google/android/recaptcha/internal/zzwx;

    .line 29
    invoke-virtual {v1, v4}, Lcom/google/android/recaptcha/internal/zzwv;->zzb(Lcom/google/android/recaptcha/internal/zzwx;)Lcom/google/android/recaptcha/internal/zzwv;

    .line 30
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzsi;->zzd()Z

    move-result v4

    if-eqz v4, :cond_0

    .line 31
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzsi;->zzc()I

    move-result v3

    invoke-virtual {v1, v3}, Lcom/google/android/recaptcha/internal/zzwv;->zza(I)Lcom/google/android/recaptcha/internal/zzwv;

    goto/16 :goto_0

    .line 35
    :cond_e
    new-instance v0, Ljava/security/GeneralSecurityException;

    const-string v1, "Passed in configuration cannot be used to parse and serialize proto keysets."

    invoke-direct {v0, v1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 36
    :cond_f
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzwy;
    :try_end_0
    .catch Ljava/security/GeneralSecurityException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    .line 33
    new-instance v1, Lcom/google/android/recaptcha/internal/zzwb;

    .line 37
    invoke-direct {v1, v0}, Lcom/google/android/recaptcha/internal/zzwb;-><init>(Ljava/lang/Throwable;)V

    throw v1
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzsi;
    .locals 3

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsj;->zza:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzsi;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzsi;->zzd()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzsi;->zzb()Lcom/google/android/recaptcha/internal/zzsg;

    move-result-object p0

    sget-object v2, Lcom/google/android/recaptcha/internal/zzsg;->zza:Lcom/google/android/recaptcha/internal/zzsg;

    if-ne p0, v2, :cond_1

    return-object v0

    :cond_1
    const-string p0, "Keyset has primary which isn\'t enabled"

    .line 2
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :cond_2
    const-string p0, "Keyset has no valid primary"

    .line 3
    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method

.method public final zzc()I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsj;->zza:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p0

    return p0
.end method

.method public final zzd(I)Lcom/google/android/recaptcha/internal/zzsi;
    .locals 4

    const/4 v0, 0x0

    if-ltz p1, :cond_2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsj;->zzc()I

    move-result v1

    if-ge p1, v1, :cond_2

    .line 2
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsj;->zza:Ljava/util/List;

    .line 3
    invoke-interface {p0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzsi;

    .line 4
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zzh()I

    move-result v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzsj;->zzk(I)Z

    move-result v2

    const-string v3, "Keyset-Entry at position "

    if-eqz v2, :cond_1

    .line 6
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zzf()Z

    move-result v1

    if-nez v1, :cond_0

    .line 8
    invoke-interface {p0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzsi;

    return-object p0

    .line 7
    :cond_0
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x30

    const-string v1, " didn\'t parse correctly"

    invoke-static {p0, v3, p1, v1}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;)V

    return-object v0

    .line 5
    :cond_1
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x2a

    const-string v1, " has wrong status"

    invoke-static {p0, v3, p1, v1}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;)V

    return-object v0

    .line 2
    :cond_2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsj;->zzc()I

    move-result p0

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    add-int/lit8 v1, v1, 0x22

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr v1, v2

    const-string v2, "Invalid index "

    const-string v3, " for keyset of size "

    invoke-static {v1, v2, p1, v3, p0}, Lcom/google/android/gms/internal/cast/zzxk$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;I)V

    return-object v0
.end method

.method public final zze(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzsb;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsj;->zzb:Ljava/util/Map;

    invoke-interface {p0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzg(Lcom/google/android/recaptcha/internal/zzsc;Ljava/lang/Class;)Ljava/lang/Object;
    .locals 9

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsj;->zza()Lcom/google/android/recaptcha/internal/zzwy;

    move-result-object v0

    .line 2
    sget v1, Lcom/google/android/recaptcha/internal/zzta;->$r8$clinit:I

    .line 3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzwy;->zza()I

    move-result v1

    .line 4
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzwy;->zzb()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    const/4 v2, 0x0

    move v3, v2

    move v4, v3

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    const/4 v6, 0x0

    if-eqz v5, :cond_6

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzwx;

    .line 5
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzwx;->zzi()I

    move-result v7

    const/4 v8, 0x3

    if-ne v7, v8, :cond_0

    .line 6
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzwx;->zza()Z

    move-result v7

    if-eqz v7, :cond_5

    .line 7
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzwx;->zzj()I

    move-result v7

    const/4 v8, 0x2

    if-eq v7, v8, :cond_4

    .line 8
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzwx;->zzi()I

    move-result v7

    if-eq v7, v8, :cond_3

    .line 9
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzwx;->zzc()I

    move-result v5

    if-ne v5, v1, :cond_2

    if-nez v4, :cond_1

    const/4 v4, 0x1

    goto :goto_1

    .line 13
    :cond_1
    const-string p0, "keyset contains multiple primary keys"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v6

    :cond_2
    :goto_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 14
    :cond_3
    new-instance p0, Ljava/security/GeneralSecurityException;

    .line 15
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzwx;->zzc()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string p2, "key %d has unknown status"

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 11
    :cond_4
    new-instance p0, Ljava/security/GeneralSecurityException;

    .line 12
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzwx;->zzc()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string p2, "key %d has unknown prefix"

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 10
    :cond_5
    new-instance p0, Ljava/security/GeneralSecurityException;

    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzwx;->zzc()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string p2, "key %d has no key data"

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p0

    :cond_6
    if-eqz v3, :cond_b

    .line 16
    :goto_2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsj;->zzc()I

    move-result v0

    if-ge v2, v0, :cond_a

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzsj;->zza:Ljava/util/List;

    .line 17
    invoke-interface {v0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzsi;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zzf()Z

    move-result v1

    if-nez v1, :cond_8

    invoke-interface {v0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzsi;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzsi;->zzh()I

    move-result v1

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzsj;->zzk(I)Z

    move-result v1

    if-nez v1, :cond_7

    goto :goto_3

    :cond_7
    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    .line 18
    :cond_8
    :goto_3
    invoke-interface {v0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzsi;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsi;->zze()Lcom/google/android/recaptcha/internal/zzse;

    move-result-object p0

    instance-of p1, p0, Lcom/google/android/recaptcha/internal/zzui;

    if-eqz p1, :cond_9

    .line 19
    check-cast p0, Lcom/google/android/recaptcha/internal/zzui;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzsy;->zza()Lcom/google/android/recaptcha/internal/zzsy;

    move-result-object p1

    .line 20
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzui;->zzc(Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object p0

    goto :goto_4

    .line 23
    :cond_9
    const-string p0, "(unknown)"

    .line 21
    :goto_4
    new-instance p1, Ljava/security/GeneralSecurityException;

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    add-int/lit8 p2, p2, 0x2c

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/2addr p2, v0

    add-int/lit8 p2, p2, 0x20

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, p2}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string p2, "Key parsing of key with index "

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p2, " and type_url "

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " failed, unable to get primitive"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 22
    :cond_a
    invoke-interface {p1, p0, p2}, Lcom/google/android/recaptcha/internal/zzsc;->zza(Lcom/google/android/recaptcha/internal/zzsk;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 23
    :cond_b
    const-string p0, "keyset must contain at least one ENABLED key"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v6
.end method
