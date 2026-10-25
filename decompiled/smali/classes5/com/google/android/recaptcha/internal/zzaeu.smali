.class final Lcom/google/android/recaptcha/internal/zzaeu;
.super Lcom/google/android/recaptcha/internal/zzaev;
.source "SourceFile"


# instance fields
.field private final zzf:Ljava/io/InputStream;

.field private final zzg:[B

.field private zzh:I

.field private zzi:I

.field private zzj:I

.field private zzk:I

.field private zzl:I

.field private zzm:I


# direct methods
.method public synthetic constructor <init>(Ljava/io/InputStream;I[B)V
    .locals 0

    const/4 p2, 0x0

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaev;-><init>([B)V

    const p2, 0x7fffffff

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzm:I

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzf:Ljava/io/InputStream;

    const/16 p1, 0x1000

    new-array p1, p1, [B

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    const/4 p1, 0x0

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    return-void
.end method

.method private final zzI(I)V
    .locals 2

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzaeu;->zzJ(I)Z

    move-result v0

    if-nez v0, :cond_1

    const v0, 0x7fffffff

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    sub-int/2addr v0, v1

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    sub-int/2addr v0, p0

    if-le p1, v0, :cond_0

    const-string p0, "Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter()."

    .line 5
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    :cond_0
    const-string p0, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    .line 3
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    :cond_1
    return-void
.end method

.method private final zzJ(I)Z
    .locals 7

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    add-int v1, v0, p1

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    const/4 v3, 0x0

    if-le v1, v2, :cond_8

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int v4, v1, v0

    const v5, 0x7fffffff

    invoke-static {v4, p1, v5}, Lcom/google/android/recaptcha/internal/zzaeu;->zzN(III)Z

    move-result v6

    if-eqz v6, :cond_0

    return v3

    :cond_0
    iget v6, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzm:I

    invoke-static {v4, p1, v6}, Lcom/google/android/recaptcha/internal/zzaeu;->zzN(III)Z

    move-result v4

    if-eqz v4, :cond_1

    return v3

    :cond_1
    if-lez v0, :cond_3

    if-le v2, v0, :cond_2

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    sub-int/2addr v2, v0

    .line 2
    invoke-static {v1, v0, v1, v3, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    :cond_2
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v1, v0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int/2addr v2, v0

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    :cond_3
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzf:Ljava/io/InputStream;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    sub-int/2addr v5, v1

    rsub-int v1, v2, 0x1000

    sub-int/2addr v5, v2

    .line 3
    invoke-static {v1, v5}, Ljava/lang/Math;->min(II)I

    move-result v1

    .line 4
    :try_start_0
    invoke-virtual {v0, v4, v2, v1}, Ljava/io/InputStream;->read([BII)I

    move-result v0
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    if-eqz v0, :cond_7

    const/4 v1, -0x1

    if-lt v0, v1, :cond_7

    const/16 v1, 0x1000

    if-gt v0, v1, :cond_7

    if-lez v0, :cond_6

    .line 7
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    add-int/2addr v1, v0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    .line 8
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzz()V

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    if-ge v0, p1, :cond_5

    .line 9
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzaeu;->zzJ(I)Z

    move-result p0

    if-eqz p0, :cond_4

    goto :goto_0

    :cond_4
    return v3

    :cond_5
    :goto_0
    const/4 p0, 0x1

    return p0

    :cond_6
    return v3

    .line 4
    :cond_7
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzf:Ljava/io/InputStream;

    new-instance p1, Ljava/lang/IllegalStateException;

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    .line 7
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    add-int/lit8 v1, v1, 0x27

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr v1, v2

    add-int/lit8 v1, v1, 0x29

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "#read(byte[]) returned invalid result: "

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, "\nThe InputStream implementation is buggy."

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    :catch_0
    move-exception p0

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzagk;->zza()V

    .line 6
    throw p0

    .line 1
    :cond_8
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x42

    const-string v0, "refillBuffer() called when "

    const-string v1, " bytes were already available in buffer"

    invoke-static {p0, v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;)V

    return v3
.end method

.method private final zzK(IZ)[B
    .locals 3

    .line 1
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzaeu;->zzL(I)[B

    move-result-object p2

    if-eqz p2, :cond_0

    return-object p2

    :cond_0
    iget p2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int v1, v0, p2

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v2, v0

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int v2, p1, v1

    .line 2
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaeu;->zzM(I)Ljava/util/List;

    move-result-object v2

    new-array p1, p1, [B

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    .line 3
    invoke-static {p0, p2, p1, v0, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 4
    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, [B

    .line 5
    array-length v2, p2

    invoke-static {p2, v0, p1, v1, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    add-int/2addr v1, v2

    goto :goto_0

    :cond_1
    return-object p1
.end method

.method private final zzL(I)[B
    .locals 6

    if-nez p1, :cond_0

    .line 1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    return-object p0

    :cond_0
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    add-int/2addr v0, v1

    const v2, 0x7fffffff

    invoke-static {v0, p1, v2}, Lcom/google/android/recaptcha/internal/zzaeu;->zzN(III)Z

    move-result v2

    if-nez v2, :cond_7

    .line 3
    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzm:I

    invoke-static {v0, p1, v2}, Lcom/google/android/recaptcha/internal/zzaeu;->zzN(III)Z

    move-result v3

    const-string v4, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    if-eqz v3, :cond_2

    if-lt v2, v0, :cond_1

    sub-int/2addr v2, v0

    .line 4
    invoke-virtual {p0, v2}, Lcom/google/android/recaptcha/internal/zzaeu;->zzy(I)V

    .line 6
    :cond_1
    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    :goto_0
    const/4 p0, 0x0

    return-object p0

    :cond_2
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int/2addr v0, v1

    sub-int v1, p1, v0

    const/16 v2, 0x1000

    if-lt v1, v2, :cond_4

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzf:Ljava/io/InputStream;

    .line 7
    :try_start_0
    invoke-virtual {v2}, Ljava/io/InputStream;->available()I

    move-result v2
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    if-gt v1, v2, :cond_3

    goto :goto_1

    :cond_3
    const/4 p0, 0x0

    return-object p0

    :catch_0
    move-exception p0

    .line 8
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzagk;->zza()V

    .line 9
    throw p0

    .line 7
    :cond_4
    :goto_1
    new-array v1, p1, [B

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    iget v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    const/4 v5, 0x0

    .line 10
    invoke-static {v2, v3, v1, v5, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    iget v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    add-int/2addr v2, v3

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    :goto_2
    if-ge v0, p1, :cond_6

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzf:Ljava/io/InputStream;

    sub-int v3, p1, v0

    .line 11
    :try_start_1
    invoke-virtual {v2, v1, v0, v3}, Ljava/io/InputStream;->read([BII)I

    move-result v2
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_1 .. :try_end_1} :catch_1

    const/4 v3, -0x1

    if-eq v2, v3, :cond_5

    .line 15
    iget v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v3, v2

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v0, v2

    goto :goto_2

    :cond_5
    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    goto :goto_0

    :catch_1
    move-exception p0

    .line 12
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzagk;->zza()V

    .line 13
    throw p0

    :cond_6
    return-object v1

    .line 1
    :cond_7
    const-string p0, "Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter()."

    .line 3
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    goto :goto_0
.end method

.method private final zzM(I)Ljava/util/List;
    .locals 6

    .line 1
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    :goto_0
    if-lez p1, :cond_2

    const/16 v1, 0x1000

    .line 2
    invoke-static {p1, v1}, Ljava/lang/Math;->min(II)I

    move-result v1

    new-array v2, v1, [B

    const/4 v3, 0x0

    :goto_1
    if-ge v3, v1, :cond_1

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzf:Ljava/io/InputStream;

    sub-int v5, v1, v3

    .line 3
    :try_start_0
    invoke-virtual {v4, v2, v3, v5}, Ljava/io/InputStream;->read([BII)I

    move-result v4
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    const/4 v5, -0x1

    if-eq v4, v5, :cond_0

    .line 8
    iget v5, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v5, v4

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v3, v4

    goto :goto_1

    .line 3
    :cond_0
    const-string p0, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    .line 8
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :catch_0
    move-exception p0

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzagk;->zza()V

    .line 6
    throw p0

    :cond_1
    sub-int/2addr p1, v1

    .line 4
    invoke-interface {v0, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_2
    return-object v0
.end method

.method private static zzN(III)Z
    .locals 0

    if-lt p2, p0, :cond_1

    sub-int/2addr p2, p0

    if-le p1, p2, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private final zzz()V
    .locals 3

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzi:I

    add-int/2addr v0, v1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v1, v0

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzm:I

    if-le v1, v2, :cond_0

    sub-int/2addr v1, v2

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzi:I

    sub-int/2addr v0, v1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    return-void

    :cond_0
    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzi:I

    return-void
.end method


# virtual methods
.method public final zzA(I)I
    .locals 3

    if-ltz p1, :cond_2

    .line 2
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    add-int/2addr v0, v1

    const v1, 0x7fffffff

    sub-int/2addr v1, v0

    if-gt p1, v1, :cond_1

    .line 4
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzm:I

    invoke-static {v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzaeu;->zzN(III)Z

    move-result v2

    if-nez v2, :cond_0

    add-int/2addr v0, p1

    .line 6
    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzm:I

    .line 7
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzz()V

    return v1

    .line 4
    :cond_0
    const-string p0, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    .line 6
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    :goto_0
    const/4 p0, 0x0

    return p0

    .line 2
    :cond_1
    const-string p0, "Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter()."

    .line 4
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 1
    :cond_2
    const-string p0, "CodedInputStream encountered an embedded string or message which claimed to have negative size."

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public final zzB(I)V
    .locals 0

    .line 1
    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzm:I

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzz()V

    return-void
.end method

.method public final zzC()Z
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzJ(I)Z

    move-result p0

    if-nez p0, :cond_0

    return v0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzD()I
    .locals 1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    add-int/2addr v0, p0

    return v0
.end method

.method public final zza()I
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzC()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzk:I

    return v0

    .line 2
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzt()I

    move-result v0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzk:I

    ushr-int/lit8 p0, v0, 0x3

    if-eqz p0, :cond_1

    return v0

    :cond_1
    const-string p0, "Protocol message contained an invalid tag (zero)."

    .line 4
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0
.end method

.method public final zzb(I)V
    .locals 0

    .line 1
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzk:I

    if-nez p0, :cond_0

    return-void

    :cond_0
    const-string p0, "Protocol message end-group tag did not match expected tag."

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method

.method public final zzc()D
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzx()J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Double;->longBitsToDouble(J)D

    move-result-wide v0

    return-wide v0
.end method

.method public final zzd()F
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzw()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Float;->intBitsToFloat(I)F

    move-result p0

    return p0
.end method

.method public final zze()J
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzu()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzf()J
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzu()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzg()I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzt()I

    move-result p0

    return p0
.end method

.method public final zzh()J
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzx()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzi()I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzw()I

    move-result p0

    return p0
.end method

.method public final zzj()Z
    .locals 4

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzu()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long p0, v0, v2

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzk()Ljava/lang/String;
    .locals 5

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzt()I

    move-result v0

    if-lez v0, :cond_1

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    sub-int/2addr v1, v2

    if-le v0, v1, :cond_0

    goto :goto_0

    .line 6
    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    new-instance v3, Ljava/lang/String;

    .line 7
    sget-object v4, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v3, v1, v2, v0, v4}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    add-int/2addr v1, v0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    return-object v3

    :cond_1
    :goto_0
    if-nez v0, :cond_2

    .line 1
    const-string p0, ""

    return-object p0

    :cond_2
    if-ltz v0, :cond_4

    .line 3
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    if-gt v0, v1, :cond_3

    .line 4
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzI(I)V

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    new-instance v2, Ljava/lang/String;

    iget v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    .line 5
    sget-object v4, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v2, v1, v3, v0, v4}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    add-int/2addr v1, v0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    return-object v2

    :cond_3
    new-instance v1, Ljava/lang/String;

    const/4 v2, 0x0

    .line 6
    invoke-direct {p0, v0, v2}, Lcom/google/android/recaptcha/internal/zzaeu;->zzK(IZ)[B

    move-result-object p0

    sget-object v0, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, p0, v0}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v1

    .line 1
    :cond_4
    const-string p0, "CodedInputStream encountered an embedded string or message which claimed to have negative size."

    .line 3
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzl()Ljava/lang/String;
    .locals 4

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzt()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int v3, v2, v1

    if-gt v0, v3, :cond_0

    if-lez v0, :cond_0

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    add-int v3, v1, v0

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    goto :goto_0

    :cond_0
    if-nez v0, :cond_1

    .line 6
    const-string p0, ""

    return-object p0

    :cond_1
    if-ltz v0, :cond_3

    const/4 v1, 0x0

    if-gt v0, v2, :cond_2

    .line 4
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzI(I)V

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    goto :goto_0

    .line 5
    :cond_2
    invoke-direct {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzaeu;->zzK(IZ)[B

    move-result-object v2

    .line 6
    :goto_0
    invoke-static {v2, v1, v0}, Lcom/google/android/recaptcha/internal/zzail;->zzc([BII)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_3
    const-string p0, "CodedInputStream encountered an embedded string or message which claimed to have negative size."

    .line 3
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzm()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 6

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzt()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    sub-int/2addr v1, v2

    const/4 v3, 0x0

    if-gt v0, v1, :cond_0

    if-lez v0, :cond_0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    .line 12
    invoke-static {v1, v2, v0, v3}, Lcom/google/android/recaptcha/internal/zzaer;->zzt([BIIZ)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v1

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    add-int/2addr v2, v0

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    return-object v1

    :cond_0
    if-nez v0, :cond_1

    .line 2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    :cond_1
    if-ltz v0, :cond_4

    .line 5
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzL(I)[B

    move-result-object v1

    if-eqz v1, :cond_2

    array-length p0, v1

    .line 6
    invoke-static {v1, v3, p0, v3}, Lcom/google/android/recaptcha/internal/zzaer;->zzt([BIIZ)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0

    :cond_2
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int v4, v2, v1

    iget v5, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v5, v2

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int v2, v0, v4

    .line 7
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaeu;->zzM(I)Ljava/util/List;

    move-result-object v2

    new-array v0, v0, [B

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    .line 8
    invoke-static {p0, v1, v0, v3, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 9
    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [B

    .line 10
    array-length v2, v1

    invoke-static {v1, v3, v0, v4, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    add-int/2addr v4, v2

    goto :goto_0

    .line 11
    :cond_3
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzu([B)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0

    .line 2
    :cond_4
    const-string p0, "CodedInputStream encountered an embedded string or message which claimed to have negative size."

    .line 4
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzn()I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzt()I

    move-result p0

    return p0
.end method

.method public final zzo()I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzt()I

    move-result p0

    return p0
.end method

.method public final zzp()I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzw()I

    move-result p0

    return p0
.end method

.method public final zzq()J
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzx()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzr()I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzt()I

    move-result p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaev;->zzG(I)I

    move-result p0

    return p0
.end method

.method public final zzs()J
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzu()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzaev;->zzH(J)J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzt()I
    .locals 7

    .line 7
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    if-ne v1, v0, :cond_0

    goto/16 :goto_3

    :cond_0
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    add-int/lit8 v3, v0, 0x1

    .line 1
    aget-byte v4, v2, v0

    if-ltz v4, :cond_1

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    return v4

    :cond_1
    sub-int/2addr v1, v3

    const/16 v5, 0x9

    if-lt v1, v5, :cond_8

    add-int/lit8 v1, v0, 0x2

    .line 2
    aget-byte v3, v2, v3

    shl-int/lit8 v3, v3, 0x7

    xor-int/2addr v3, v4

    if-gez v3, :cond_2

    xor-int/lit8 v0, v3, -0x80

    goto :goto_2

    :cond_2
    add-int/lit8 v4, v0, 0x3

    .line 3
    aget-byte v1, v2, v1

    shl-int/lit8 v1, v1, 0xe

    xor-int/2addr v1, v3

    if-ltz v1, :cond_3

    xor-int/lit16 v0, v1, 0x3f80

    :goto_0
    move v1, v4

    goto :goto_2

    :cond_3
    add-int/lit8 v3, v0, 0x4

    .line 4
    aget-byte v4, v2, v4

    shl-int/lit8 v4, v4, 0x15

    xor-int/2addr v1, v4

    if-gez v1, :cond_4

    const v0, -0x1fc080

    xor-int/2addr v0, v1

    :goto_1
    move v1, v3

    goto :goto_2

    :cond_4
    add-int/lit8 v4, v0, 0x5

    .line 5
    aget-byte v3, v2, v3

    shl-int/lit8 v5, v3, 0x1c

    xor-int/2addr v1, v5

    const v5, 0xfe03f80

    xor-int/2addr v1, v5

    if-gez v3, :cond_6

    add-int/lit8 v3, v0, 0x6

    .line 6
    aget-byte v4, v2, v4

    if-gez v4, :cond_7

    add-int/lit8 v4, v0, 0x7

    aget-byte v3, v2, v3

    if-gez v3, :cond_6

    add-int/lit8 v3, v0, 0x8

    aget-byte v4, v2, v4

    if-gez v4, :cond_7

    add-int/lit8 v4, v0, 0x9

    aget-byte v3, v2, v3

    if-gez v3, :cond_6

    add-int/lit8 v0, v0, 0xa

    aget-byte v2, v2, v4

    if-gez v2, :cond_5

    goto :goto_3

    :cond_5
    move v6, v1

    move v1, v0

    move v0, v6

    goto :goto_2

    :cond_6
    move v0, v1

    goto :goto_0

    :cond_7
    move v0, v1

    goto :goto_1

    .line 2
    :goto_2
    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    return v0

    .line 7
    :cond_8
    :goto_3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzv()J

    move-result-wide v0

    long-to-int p0, v0

    return p0
.end method

.method public final zzu()J
    .locals 13

    .line 11
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    if-ne v1, v0, :cond_0

    goto/16 :goto_4

    :cond_0
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    add-int/lit8 v3, v0, 0x1

    .line 1
    aget-byte v4, v2, v0

    if-ltz v4, :cond_1

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    int-to-long v0, v4

    return-wide v0

    :cond_1
    sub-int/2addr v1, v3

    const/16 v5, 0x9

    if-lt v1, v5, :cond_a

    add-int/lit8 v1, v0, 0x2

    .line 2
    aget-byte v3, v2, v3

    shl-int/lit8 v3, v3, 0x7

    xor-int/2addr v3, v4

    if-gez v3, :cond_2

    xor-int/lit8 v0, v3, -0x80

    int-to-long v2, v0

    goto/16 :goto_3

    :cond_2
    add-int/lit8 v4, v0, 0x3

    .line 3
    aget-byte v1, v2, v1

    shl-int/lit8 v1, v1, 0xe

    xor-int/2addr v1, v3

    if-ltz v1, :cond_3

    xor-int/lit16 v0, v1, 0x3f80

    int-to-long v2, v0

    :goto_0
    move v1, v4

    goto/16 :goto_3

    :cond_3
    add-int/lit8 v3, v0, 0x4

    .line 4
    aget-byte v4, v2, v4

    shl-int/lit8 v4, v4, 0x15

    xor-int/2addr v1, v4

    if-gez v1, :cond_4

    const v0, -0x1fc080

    xor-int/2addr v0, v1

    int-to-long v0, v0

    move-wide v11, v0

    move v1, v3

    move-wide v2, v11

    goto/16 :goto_3

    :cond_4
    add-int/lit8 v4, v0, 0x5

    .line 5
    aget-byte v3, v2, v3

    int-to-long v5, v3

    int-to-long v7, v1

    const/16 v1, 0x1c

    shl-long/2addr v5, v1

    xor-long/2addr v5, v7

    const-wide/16 v7, 0x0

    cmp-long v1, v5, v7

    if-ltz v1, :cond_5

    const-wide/32 v0, 0xfe03f80

    xor-long v2, v5, v0

    goto :goto_0

    :cond_5
    add-int/lit8 v1, v0, 0x6

    .line 6
    aget-byte v3, v2, v4

    int-to-long v3, v3

    const/16 v9, 0x23

    shl-long/2addr v3, v9

    xor-long/2addr v3, v5

    cmp-long v5, v3, v7

    if-gez v5, :cond_6

    const-wide v5, -0x7f01fc080L

    :goto_1
    xor-long v2, v3, v5

    goto :goto_3

    :cond_6
    add-int/lit8 v5, v0, 0x7

    .line 7
    aget-byte v1, v2, v1

    int-to-long v9, v1

    const/16 v1, 0x2a

    shl-long/2addr v9, v1

    xor-long/2addr v3, v9

    cmp-long v1, v3, v7

    if-ltz v1, :cond_7

    const-wide v0, 0x3f80fe03f80L

    :goto_2
    xor-long v2, v3, v0

    move v1, v5

    goto :goto_3

    :cond_7
    add-int/lit8 v1, v0, 0x8

    .line 8
    aget-byte v5, v2, v5

    int-to-long v5, v5

    const/16 v9, 0x31

    shl-long/2addr v5, v9

    xor-long/2addr v3, v5

    cmp-long v5, v3, v7

    if-gez v5, :cond_8

    const-wide v5, -0x1fc07f01fc080L

    goto :goto_1

    :cond_8
    add-int/lit8 v5, v0, 0x9

    .line 9
    aget-byte v1, v2, v1

    int-to-long v9, v1

    const/16 v1, 0x38

    shl-long/2addr v9, v1

    xor-long/2addr v3, v9

    cmp-long v1, v3, v7

    if-ltz v1, :cond_9

    const-wide v0, 0xfe03f80fe03f80L

    goto :goto_2

    :cond_9
    add-int/lit8 v1, v0, 0xa

    .line 10
    aget-byte v0, v2, v5

    int-to-long v5, v0

    const/16 v0, 0x3f

    shl-long/2addr v5, v0

    xor-long v2, v3, v5

    cmp-long v0, v2, v7

    if-ltz v0, :cond_a

    const-wide v4, -0x7f01fc07f01fc080L    # -6.838959413692434E-304

    xor-long/2addr v2, v4

    .line 2
    :goto_3
    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    return-wide v2

    .line 11
    :cond_a
    :goto_4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzv()J

    move-result-wide v0

    return-wide v0
.end method

.method public final zzv()J
    .locals 6

    const/4 v0, 0x0

    const-wide/16 v1, 0x0

    :goto_0
    const/16 v3, 0x40

    if-ge v0, v3, :cond_2

    .line 1
    iget v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v4, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    if-ne v3, v4, :cond_0

    const/4 v3, 0x1

    invoke-direct {p0, v3}, Lcom/google/android/recaptcha/internal/zzaeu;->zzI(I)V

    :cond_0
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    iget v4, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    add-int/lit8 v5, v4, 0x1

    iput v5, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    .line 2
    aget-byte v3, v3, v4

    and-int/lit8 v4, v3, 0x7f

    int-to-long v4, v4

    shl-long/2addr v4, v0

    or-long/2addr v1, v4

    and-int/lit16 v3, v3, 0x80

    if-nez v3, :cond_1

    return-wide v1

    :cond_1
    add-int/lit8 v0, v0, 0x7

    goto :goto_0

    :cond_2
    const-string p0, "CodedInputStream encountered a malformed varint."

    .line 4
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public final zzw()I
    .locals 4

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int/2addr v1, v0

    const/4 v2, 0x4

    if-ge v1, v2, :cond_0

    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaeu;->zzI(I)V

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    :cond_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    add-int/lit8 v2, v0, 0x4

    iput v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    .line 2
    aget-byte p0, v1, v0

    and-int/lit16 p0, p0, 0xff

    add-int/lit8 v2, v0, 0x1

    aget-byte v2, v1, v2

    and-int/lit16 v2, v2, 0xff

    add-int/lit8 v3, v0, 0x2

    aget-byte v3, v1, v3

    and-int/lit16 v3, v3, 0xff

    add-int/lit8 v0, v0, 0x3

    aget-byte v0, v1, v0

    and-int/lit16 v0, v0, 0xff

    shl-int/lit8 v1, v2, 0x8

    or-int/2addr p0, v1

    shl-int/lit8 v1, v3, 0x10

    or-int/2addr p0, v1

    shl-int/lit8 v0, v0, 0x18

    or-int/2addr p0, v0

    return p0
.end method

.method public final zzx()J
    .locals 18

    move-object/from16 v0, p0

    .line 1
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    iget v2, v0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    sub-int/2addr v2, v1

    const/16 v3, 0x8

    if-ge v2, v3, :cond_0

    invoke-direct {v0, v3}, Lcom/google/android/recaptcha/internal/zzaeu;->zzI(I)V

    iget v1, v0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    :cond_0
    iget-object v2, v0, Lcom/google/android/recaptcha/internal/zzaeu;->zzg:[B

    add-int/lit8 v4, v1, 0x8

    iput v4, v0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    .line 2
    aget-byte v0, v2, v1

    int-to-long v4, v0

    add-int/lit8 v0, v1, 0x1

    aget-byte v0, v2, v0

    int-to-long v6, v0

    const-wide/16 v8, 0xff

    and-long/2addr v6, v8

    and-long/2addr v4, v8

    shl-long/2addr v6, v3

    add-int/lit8 v0, v1, 0x2

    aget-byte v0, v2, v0

    int-to-long v10, v0

    add-int/lit8 v0, v1, 0x3

    aget-byte v0, v2, v0

    int-to-long v12, v0

    add-int/lit8 v0, v1, 0x4

    aget-byte v0, v2, v0

    int-to-long v14, v0

    add-int/lit8 v0, v1, 0x5

    aget-byte v0, v2, v0

    move-wide/from16 v16, v8

    int-to-long v8, v0

    add-int/lit8 v0, v1, 0x6

    aget-byte v0, v2, v0

    move v3, v1

    int-to-long v0, v0

    add-int/lit8 v3, v3, 0x7

    aget-byte v2, v2, v3

    int-to-long v2, v2

    and-long v10, v10, v16

    or-long/2addr v4, v6

    and-long v6, v12, v16

    const/16 v12, 0x10

    shl-long/2addr v10, v12

    or-long/2addr v4, v10

    and-long v10, v14, v16

    const/16 v12, 0x18

    shl-long/2addr v6, v12

    or-long/2addr v4, v6

    and-long v6, v8, v16

    const/16 v8, 0x20

    shl-long v8, v10, v8

    or-long/2addr v4, v8

    and-long v0, v0, v16

    const/16 v8, 0x28

    shl-long/2addr v6, v8

    or-long/2addr v4, v6

    and-long v2, v2, v16

    const/16 v6, 0x30

    shl-long/2addr v0, v6

    or-long/2addr v0, v4

    const/16 v4, 0x38

    shl-long/2addr v2, v4

    or-long/2addr v0, v2

    return-wide v0
.end method

.method public final zzy(I)V
    .locals 10

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    sub-int/2addr v0, v1

    if-gt p1, v0, :cond_1

    if-gez p1, :cond_0

    goto :goto_0

    :cond_0
    add-int/2addr v1, p1

    .line 15
    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    return-void

    .line 1
    :cond_1
    :goto_0
    const-string v2, "\nThe InputStream implementation is buggy."

    const-string v3, "#skip returned invalid result: "

    if-ltz p1, :cond_a

    .line 2
    iget v4, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v4, v1

    const v1, 0x7fffffff

    invoke-static {v4, p1, v1}, Lcom/google/android/recaptcha/internal/zzaeu;->zzN(III)Z

    move-result v1

    if-nez v1, :cond_9

    .line 4
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzm:I

    invoke-static {v4, p1, v1}, Lcom/google/android/recaptcha/internal/zzaeu;->zzN(III)Z

    move-result v5

    if-eqz v5, :cond_3

    if-lt v1, v4, :cond_2

    sub-int/2addr v1, v4

    .line 5
    invoke-virtual {p0, v1}, Lcom/google/android/recaptcha/internal/zzaeu;->zzy(I)V

    :cond_2
    const-string p0, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    .line 7
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    :cond_3
    iput v4, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    const/4 v1, 0x0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    :goto_1
    if-ge v0, p1, :cond_6

    :try_start_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzf:Ljava/io/InputStream;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    sub-int v4, p1, v0

    int-to-long v4, v4

    .line 8
    :try_start_1
    invoke-virtual {v1, v4, v5}, Ljava/io/InputStream;->skip(J)J

    move-result-wide v6
    :try_end_1
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const-wide/16 v8, 0x0

    cmp-long v1, v6, v8

    if-ltz v1, :cond_5

    cmp-long v4, v6, v4

    if-gtz v4, :cond_5

    if-nez v1, :cond_4

    goto :goto_3

    :cond_4
    long-to-int v1, v6

    add-int/2addr v0, v1

    goto :goto_1

    :cond_5
    :try_start_2
    new-instance p1, Ljava/lang/IllegalStateException;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzf:Ljava/io/InputStream;

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    .line 11
    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v4

    add-int/lit8 v4, v4, 0x1f

    invoke-static {v6, v7}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->length()I

    move-result v5

    add-int/2addr v4, v5

    add-int/lit8 v4, v4, 0x29

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5, v4}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p1, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception p1

    .line 9
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzagk;->zza()V

    .line 10
    throw p1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 15
    :goto_2
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v1, v0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    .line 12
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzz()V

    .line 13
    throw p1

    .line 11
    :cond_6
    :goto_3
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    add-int/2addr v1, v0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzl:I

    .line 12
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzz()V

    if-ge v0, p1, :cond_8

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    sub-int v1, v0, v1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    const/4 v0, 0x1

    .line 14
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzI(I)V

    :goto_4
    sub-int v2, p1, v1

    iget v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzh:I

    if-le v2, v3, :cond_7

    add-int/2addr v1, v3

    iput v3, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    .line 15
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaeu;->zzI(I)V

    goto :goto_4

    :cond_7
    iput v2, p0, Lcom/google/android/recaptcha/internal/zzaeu;->zzj:I

    :cond_8
    return-void

    .line 2
    :cond_9
    const-string p0, "Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter()."

    .line 4
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    .line 1
    :cond_a
    const-string p0, "CodedInputStream encountered an embedded string or message which claimed to have negative size."

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method
