.class public final enum Lcom/google/android/recaptcha/internal/zzwp;
.super Ljava/lang/Enum;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzafz;


# static fields
.field public static final enum zza:Lcom/google/android/recaptcha/internal/zzwp;

.field public static final enum zzb:Lcom/google/android/recaptcha/internal/zzwp;

.field public static final enum zzc:Lcom/google/android/recaptcha/internal/zzwp;

.field public static final enum zzd:Lcom/google/android/recaptcha/internal/zzwp;

.field public static final enum zze:Lcom/google/android/recaptcha/internal/zzwp;

.field public static final enum zzf:Lcom/google/android/recaptcha/internal/zzwp;

.field public static final enum zzg:Lcom/google/android/recaptcha/internal/zzwp;

.field private static final synthetic zzi:[Lcom/google/android/recaptcha/internal/zzwp;


# instance fields
.field private final zzh:I


# direct methods
.method static constructor <clinit>()V
    .locals 10

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzwp;

    const-string v1, "UNKNOWN_HASH"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2, v2}, Lcom/google/android/recaptcha/internal/zzwp;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzwp;->zza:Lcom/google/android/recaptcha/internal/zzwp;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzwp;

    const-string v2, "SHA1"

    const/4 v3, 0x1

    .line 2
    invoke-direct {v1, v2, v3, v3}, Lcom/google/android/recaptcha/internal/zzwp;-><init>(Ljava/lang/String;II)V

    sput-object v1, Lcom/google/android/recaptcha/internal/zzwp;->zzb:Lcom/google/android/recaptcha/internal/zzwp;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzwp;

    const-string v3, "SHA384"

    const/4 v4, 0x2

    .line 3
    invoke-direct {v2, v3, v4, v4}, Lcom/google/android/recaptcha/internal/zzwp;-><init>(Ljava/lang/String;II)V

    sput-object v2, Lcom/google/android/recaptcha/internal/zzwp;->zzc:Lcom/google/android/recaptcha/internal/zzwp;

    new-instance v3, Lcom/google/android/recaptcha/internal/zzwp;

    const-string v4, "SHA256"

    const/4 v5, 0x3

    .line 4
    invoke-direct {v3, v4, v5, v5}, Lcom/google/android/recaptcha/internal/zzwp;-><init>(Ljava/lang/String;II)V

    sput-object v3, Lcom/google/android/recaptcha/internal/zzwp;->zzd:Lcom/google/android/recaptcha/internal/zzwp;

    new-instance v4, Lcom/google/android/recaptcha/internal/zzwp;

    const-string v5, "SHA512"

    const/4 v6, 0x4

    .line 5
    invoke-direct {v4, v5, v6, v6}, Lcom/google/android/recaptcha/internal/zzwp;-><init>(Ljava/lang/String;II)V

    sput-object v4, Lcom/google/android/recaptcha/internal/zzwp;->zze:Lcom/google/android/recaptcha/internal/zzwp;

    new-instance v5, Lcom/google/android/recaptcha/internal/zzwp;

    const-string v6, "SHA224"

    const/4 v7, 0x5

    .line 6
    invoke-direct {v5, v6, v7, v7}, Lcom/google/android/recaptcha/internal/zzwp;-><init>(Ljava/lang/String;II)V

    sput-object v5, Lcom/google/android/recaptcha/internal/zzwp;->zzf:Lcom/google/android/recaptcha/internal/zzwp;

    new-instance v6, Lcom/google/android/recaptcha/internal/zzwp;

    const/4 v7, 0x6

    const/4 v8, -0x1

    .line 7
    const-string v9, "UNRECOGNIZED"

    invoke-direct {v6, v9, v7, v8}, Lcom/google/android/recaptcha/internal/zzwp;-><init>(Ljava/lang/String;II)V

    sput-object v6, Lcom/google/android/recaptcha/internal/zzwp;->zzg:Lcom/google/android/recaptcha/internal/zzwp;

    filled-new-array/range {v0 .. v6}, [Lcom/google/android/recaptcha/internal/zzwp;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzwp;->zzi:[Lcom/google/android/recaptcha/internal/zzwp;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput p3, p0, Lcom/google/android/recaptcha/internal/zzwp;->zzh:I

    return-void
.end method

.method public static values()[Lcom/google/android/recaptcha/internal/zzwp;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzwp;->zzi:[Lcom/google/android/recaptcha/internal/zzwp;

    invoke-virtual {v0}, [Lcom/google/android/recaptcha/internal/zzwp;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/google/android/recaptcha/internal/zzwp;

    return-object v0
.end method

.method public static zzb(I)Lcom/google/android/recaptcha/internal/zzwp;
    .locals 1

    if-eqz p0, :cond_5

    const/4 v0, 0x1

    if-eq p0, v0, :cond_4

    const/4 v0, 0x2

    if-eq p0, v0, :cond_3

    const/4 v0, 0x3

    if-eq p0, v0, :cond_2

    const/4 v0, 0x4

    if-eq p0, v0, :cond_1

    const/4 v0, 0x5

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwp;->zzf:Lcom/google/android/recaptcha/internal/zzwp;

    return-object p0

    :cond_1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwp;->zze:Lcom/google/android/recaptcha/internal/zzwp;

    return-object p0

    :cond_2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwp;->zzd:Lcom/google/android/recaptcha/internal/zzwp;

    return-object p0

    :cond_3
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwp;->zzc:Lcom/google/android/recaptcha/internal/zzwp;

    return-object p0

    :cond_4
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwp;->zzb:Lcom/google/android/recaptcha/internal/zzwp;

    return-object p0

    :cond_5
    sget-object p0, Lcom/google/android/recaptcha/internal/zzwp;->zza:Lcom/google/android/recaptcha/internal/zzwp;

    return-object p0
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzwp;->zzh:I

    invoke-static {p0}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zza()I
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzwp;->zzg:Lcom/google/android/recaptcha/internal/zzwp;

    if-ne p0, v0, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzagf;->zza()I

    move-result p0

    return p0

    :cond_0
    iget p0, p0, Lcom/google/android/recaptcha/internal/zzwp;->zzh:I

    return p0
.end method
