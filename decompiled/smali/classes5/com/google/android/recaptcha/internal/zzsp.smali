.class public final Lcom/google/android/recaptcha/internal/zzsp;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzaer;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzsn;

.field private final zzd:Lcom/google/android/recaptcha/internal/zzso;

.field private final zze:Ljava/lang/Integer;


# direct methods
.method private constructor <init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzsn;Lcom/google/android/recaptcha/internal/zzso;Ljava/lang/Integer;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzsp;->zza:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzsp;->zzb:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzsp;->zzc:Lcom/google/android/recaptcha/internal/zzsn;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzsp;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzsp;->zze:Ljava/lang/Integer;

    return-void
.end method

.method public static zza(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzsn;Lcom/google/android/recaptcha/internal/zzso;Ljava/lang/Integer;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 6

    const/4 v0, 0x0

    if-eqz p4, :cond_1

    .line 1
    sget-object v1, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    invoke-virtual {p3, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 2
    :cond_0
    const-string p0, "Cannot set idRequirement for OutputPrefixType RAW"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    :cond_1
    :goto_0
    const/4 v1, 0x0

    if-nez p4, :cond_3

    sget-object v2, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    .line 3
    invoke-virtual {p3, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto :goto_1

    .line 7
    :cond_2
    const-string p0, "Cannot have null idRequirement unless OutputPrefixType is RAW"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    .line 4
    :cond_3
    :goto_1
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    if-ge v1, v0, :cond_5

    .line 5
    invoke-virtual {p0, v1}, Ljava/lang/String;->charAt(I)C

    move-result v0

    const/16 v2, 0x21

    if-lt v0, v2, :cond_4

    const/16 v2, 0x7e

    if-gt v0, v2, :cond_4

    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 6
    :cond_4
    new-instance p1, Ljava/security/GeneralSecurityException;

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p2

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p3

    add-int/lit8 p2, p2, 0x32

    invoke-virtual {p3}, Ljava/lang/String;->length()I

    move-result p3

    add-int/2addr p2, p3

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3, p2}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string/jumbo p2, "typeURL "

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " contains non-ascii character at position "

    invoke-virtual {p3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_5
    new-instance v0, Lcom/google/android/recaptcha/internal/zzsp;

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    move-object v4, p3

    move-object v5, p4

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzsp;-><init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzsn;Lcom/google/android/recaptcha/internal/zzso;Ljava/lang/Integer;)V

    return-object v0
.end method


# virtual methods
.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsp;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsp;->zzb:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzsn;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsp;->zzc:Lcom/google/android/recaptcha/internal/zzsn;

    return-object p0
.end method

.method public final zze()Lcom/google/android/recaptcha/internal/zzso;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsp;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    return-object p0
.end method

.method public final zzf()Ljava/lang/Integer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsp;->zze:Ljava/lang/Integer;

    return-object p0
.end method
