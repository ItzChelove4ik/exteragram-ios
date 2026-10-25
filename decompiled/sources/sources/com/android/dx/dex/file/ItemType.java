package com.android.dx.dex.file;

import com.android.dx.util.ToHuman;

/* loaded from: classes.dex */
public enum ItemType extends Enum<ItemType> implements ToHuman {
    private static final /* synthetic */ ItemType[] $VALUES = null;
    public static final ItemType TYPE_ANNOTATIONS_DIRECTORY_ITEM = null;
    public static final ItemType TYPE_ANNOTATION_ITEM = null;
    public static final ItemType TYPE_ANNOTATION_SET_ITEM = null;
    public static final ItemType TYPE_ANNOTATION_SET_REF_ITEM = null;
    public static final ItemType TYPE_ANNOTATION_SET_REF_LIST = null;
    public static final ItemType TYPE_CALL_SITE_ID_ITEM = null;
    public static final ItemType TYPE_CLASS_DATA_ITEM = null;
    public static final ItemType TYPE_CLASS_DEF_ITEM = null;
    public static final ItemType TYPE_CODE_ITEM = null;
    public static final ItemType TYPE_DEBUG_INFO_ITEM = null;
    public static final ItemType TYPE_ENCODED_ARRAY_ITEM = null;
    public static final ItemType TYPE_EXCEPTION_HANDLER_ITEM = null;
    public static final ItemType TYPE_FIELD_ID_ITEM = null;
    public static final ItemType TYPE_HEADER_ITEM = null;
    public static final ItemType TYPE_MAP_ITEM = null;
    public static final ItemType TYPE_MAP_LIST = null;
    public static final ItemType TYPE_METHOD_HANDLE_ITEM = null;
    public static final ItemType TYPE_METHOD_ID_ITEM = null;
    public static final ItemType TYPE_PROTO_ID_ITEM = null;
    public static final ItemType TYPE_STRING_DATA_ITEM = null;
    public static final ItemType TYPE_STRING_ID_ITEM = null;
    public static final ItemType TYPE_TYPE_ID_ITEM = null;
    public static final ItemType TYPE_TYPE_ITEM = null;
    public static final ItemType TYPE_TYPE_LIST = null;
    private final String humanName;
    private final int mapValue;
    private final String typeName;

    static {
        ItemType r1 = new ItemType("TYPE_HEADER_ITEM", 0, 0, "header_item");
        TYPE_HEADER_ITEM = r1;
        ItemType r2 = new ItemType("TYPE_STRING_ID_ITEM", 1, 1, "string_id_item");
        TYPE_STRING_ID_ITEM = r2;
        ItemType r3 = new ItemType("TYPE_TYPE_ID_ITEM", 2, 2, "type_id_item");
        TYPE_TYPE_ID_ITEM = r3;
        ItemType r4 = new ItemType("TYPE_PROTO_ID_ITEM", 3, 3, "proto_id_item");
        TYPE_PROTO_ID_ITEM = r4;
        ItemType r5 = new ItemType("TYPE_FIELD_ID_ITEM", 4, 4, "field_id_item");
        TYPE_FIELD_ID_ITEM = r5;
        ItemType r6 = new ItemType("TYPE_METHOD_ID_ITEM", 5, 5, "method_id_item");
        TYPE_METHOD_ID_ITEM = r6;
        ItemType r7 = new ItemType("TYPE_CLASS_DEF_ITEM", 6, 6, "class_def_item");
        TYPE_CLASS_DEF_ITEM = r7;
        ItemType r8 = new ItemType("TYPE_CALL_SITE_ID_ITEM", 7, 7, "call_site_id_item");
        TYPE_CALL_SITE_ID_ITEM = r8;
        ItemType r9 = new ItemType("TYPE_METHOD_HANDLE_ITEM", 8, 8, "method_handle_item");
        TYPE_METHOD_HANDLE_ITEM = r9;
        ItemType r10 = new ItemType("TYPE_MAP_LIST", 9, 4096, "map_list");
        TYPE_MAP_LIST = r10;
        ItemType r11 = new ItemType("TYPE_TYPE_LIST", 10, 4097, "type_list");
        TYPE_TYPE_LIST = r11;
        ItemType r12 = new ItemType("TYPE_ANNOTATION_SET_REF_LIST", 11, 4098, "annotation_set_ref_list");
        TYPE_ANNOTATION_SET_REF_LIST = r12;
        ItemType r13 = new ItemType("TYPE_ANNOTATION_SET_ITEM", 12, 4099, "annotation_set_item");
        TYPE_ANNOTATION_SET_ITEM = r13;
        ItemType r14 = new ItemType("TYPE_CLASS_DATA_ITEM", 13, 8192, "class_data_item");
        TYPE_CLASS_DATA_ITEM = r14;
        ItemType r15 = new ItemType("TYPE_CODE_ITEM", 14, 8193, "code_item");
        TYPE_CODE_ITEM = r15;
        ItemType r0 = new ItemType("TYPE_STRING_DATA_ITEM", 15, 8194, "string_data_item");
        TYPE_STRING_DATA_ITEM = r0;
        ItemType r16 = new ItemType("TYPE_DEBUG_INFO_ITEM", 16, 8195, "debug_info_item");
        TYPE_DEBUG_INFO_ITEM = r16;
        ItemType r02 = new ItemType("TYPE_ANNOTATION_ITEM", 17, 8196, "annotation_item");
        TYPE_ANNOTATION_ITEM = r02;
        ItemType r17 = new ItemType("TYPE_ENCODED_ARRAY_ITEM", 18, 8197, "encoded_array_item");
        TYPE_ENCODED_ARRAY_ITEM = r17;
        ItemType r03 = new ItemType("TYPE_ANNOTATIONS_DIRECTORY_ITEM", 19, 8198, "annotations_directory_item");
        TYPE_ANNOTATIONS_DIRECTORY_ITEM = r03;
        ItemType r18 = new ItemType("TYPE_MAP_ITEM", 20, -1, "map_item");
        TYPE_MAP_ITEM = r18;
        ItemType r22 = new ItemType("TYPE_TYPE_ITEM", 21, -1, "type_item");
        TYPE_TYPE_ITEM = r22;
        ItemType r19 = new ItemType("TYPE_EXCEPTION_HANDLER_ITEM", 22, -1, "exception_handler_item");
        TYPE_EXCEPTION_HANDLER_ITEM = r19;
        ItemType r23 = new ItemType("TYPE_ANNOTATION_SET_REF_ITEM", 23, -1, "annotation_set_ref_item");
        TYPE_ANNOTATION_SET_REF_ITEM = r23;
        $VALUES = new ItemType[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r02, r17, r03, r18, r22, r19, r23};
    }

    ItemType(String r1, int r2, int r3, String r4) {
        this.mapValue = r3;
        this.typeName = r4;
        if (r4.endsWith("_item") == false) goto L5;
        r4 = r4.substring(0, r4.length() - 5);
    L5:
        this.humanName = r4.replace('_', ' ');
    }

    public static ItemType valueOf(String r1) {
        return (ItemType) Enum.valueOf(ItemType.class, r1);
    }

    public static ItemType[] values() {
        return (ItemType[]) $VALUES.clone();
    }

    public int getMapValue() {
        return this.mapValue;
    }

    public String getTypeName() {
        return this.typeName;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.humanName;
    }
}
