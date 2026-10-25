.class public abstract Lcom/google/android/gms/location/zzn;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/gms/common/Feature;

.field public static final zzb:Lcom/google/android/gms/common/Feature;

.field public static final zzc:Lcom/google/android/gms/common/Feature;

.field public static final zzd:Lcom/google/android/gms/common/Feature;

.field public static final zze:Lcom/google/android/gms/common/Feature;

.field public static final zzf:Lcom/google/android/gms/common/Feature;

.field public static final zzg:Lcom/google/android/gms/common/Feature;

.field public static final zzh:Lcom/google/android/gms/common/Feature;

.field public static final zzi:Lcom/google/android/gms/common/Feature;

.field public static final zzj:Lcom/google/android/gms/common/Feature;

.field public static final zzk:Lcom/google/android/gms/common/Feature;

.field public static final zzl:Lcom/google/android/gms/common/Feature;

.field public static final zzm:Lcom/google/android/gms/common/Feature;

.field public static final zzn:Lcom/google/android/gms/common/Feature;

.field public static final zzo:Lcom/google/android/gms/common/Feature;

.field public static final zzp:[Lcom/google/android/gms/common/Feature;


# direct methods
.method static constructor <clinit>()V
    .locals 19

    .line 1
    new-instance v0, Lcom/google/android/gms/common/Feature;

    const-string v1, "name_ulr_private"

    const-wide/16 v2, 0x1

    const/4 v4, 0x1

    invoke-direct {v0, v1, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v0, Lcom/google/android/gms/location/zzn;->zza:Lcom/google/android/gms/common/Feature;

    new-instance v1, Lcom/google/android/gms/common/Feature;

    const-string v5, "name_sleep_segment_request"

    .line 2
    invoke-direct {v1, v5, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v1, Lcom/google/android/gms/location/zzn;->zzb:Lcom/google/android/gms/common/Feature;

    new-instance v5, Lcom/google/android/gms/common/Feature;

    const-string v6, "get_last_activity_feature_id"

    .line 3
    invoke-direct {v5, v6, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v5, Lcom/google/android/gms/location/zzn;->zzc:Lcom/google/android/gms/common/Feature;

    new-instance v6, Lcom/google/android/gms/common/Feature;

    const-string v7, "support_context_feature_id"

    .line 4
    invoke-direct {v6, v7, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v6, Lcom/google/android/gms/location/zzn;->zzd:Lcom/google/android/gms/common/Feature;

    new-instance v7, Lcom/google/android/gms/common/Feature;

    const-string v8, "get_current_location"

    const-wide/16 v9, 0x2

    .line 5
    invoke-direct {v7, v8, v9, v10, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v7, Lcom/google/android/gms/location/zzn;->zze:Lcom/google/android/gms/common/Feature;

    move-object v8, v5

    new-instance v5, Lcom/google/android/gms/common/Feature;

    const-string v9, "get_last_location_with_request"

    .line 6
    invoke-direct {v5, v9, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v5, Lcom/google/android/gms/location/zzn;->zzf:Lcom/google/android/gms/common/Feature;

    move-object v9, v6

    new-instance v6, Lcom/google/android/gms/common/Feature;

    const-string v10, "set_mock_mode_with_callback"

    .line 7
    invoke-direct {v6, v10, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v6, Lcom/google/android/gms/location/zzn;->zzg:Lcom/google/android/gms/common/Feature;

    move-object v10, v7

    new-instance v7, Lcom/google/android/gms/common/Feature;

    const-string v11, "set_mock_location_with_callback"

    .line 8
    invoke-direct {v7, v11, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v7, Lcom/google/android/gms/location/zzn;->zzh:Lcom/google/android/gms/common/Feature;

    move-object v11, v8

    new-instance v8, Lcom/google/android/gms/common/Feature;

    const-string v12, "inject_location_with_callback"

    .line 9
    invoke-direct {v8, v12, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v8, Lcom/google/android/gms/location/zzn;->zzi:Lcom/google/android/gms/common/Feature;

    move-object v12, v9

    new-instance v9, Lcom/google/android/gms/common/Feature;

    const-string v13, "location_updates_with_callback"

    .line 10
    invoke-direct {v9, v13, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v9, Lcom/google/android/gms/location/zzn;->zzj:Lcom/google/android/gms/common/Feature;

    move-object v13, v10

    new-instance v10, Lcom/google/android/gms/common/Feature;

    const-string/jumbo v14, "use_safe_parcelable_in_intents"

    const/4 v15, 0x0

    .line 11
    invoke-direct {v10, v14, v2, v3, v15}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v10, Lcom/google/android/gms/location/zzn;->zzk:Lcom/google/android/gms/common/Feature;

    move-object v14, v11

    new-instance v11, Lcom/google/android/gms/common/Feature;

    const-string v15, "flp_debug_updates"

    .line 12
    invoke-direct {v11, v15, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v11, Lcom/google/android/gms/location/zzn;->zzl:Lcom/google/android/gms/common/Feature;

    move-object v15, v12

    new-instance v12, Lcom/google/android/gms/common/Feature;

    move-object/from16 v16, v0

    const-string v0, "google_location_accuracy_enabled"

    .line 13
    invoke-direct {v12, v0, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v12, Lcom/google/android/gms/location/zzn;->zzm:Lcom/google/android/gms/common/Feature;

    move-object v0, v13

    new-instance v13, Lcom/google/android/gms/common/Feature;

    move-object/from16 v17, v0

    const-string v0, "geofences_with_callback"

    .line 14
    invoke-direct {v13, v0, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v13, Lcom/google/android/gms/location/zzn;->zzn:Lcom/google/android/gms/common/Feature;

    move-object v0, v14

    new-instance v14, Lcom/google/android/gms/common/Feature;

    move-object/from16 v18, v0

    const-string v0, "location_enabled"

    .line 15
    invoke-direct {v14, v0, v2, v3, v4}, Lcom/google/android/gms/common/Feature;-><init>(Ljava/lang/String;JZ)V

    sput-object v14, Lcom/google/android/gms/location/zzn;->zzo:Lcom/google/android/gms/common/Feature;

    move-object v3, v15

    move-object/from16 v0, v16

    move-object/from16 v4, v17

    move-object/from16 v2, v18

    filled-new-array/range {v0 .. v14}, [Lcom/google/android/gms/common/Feature;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/location/zzn;->zzp:[Lcom/google/android/gms/common/Feature;

    return-void
.end method
