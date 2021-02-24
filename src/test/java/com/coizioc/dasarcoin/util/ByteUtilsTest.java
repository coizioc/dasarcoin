package com.coizioc.dasarcoin.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("The byte utils")
class ByteUtilsTest {
    private static final byte[] BYTE_ARRAY = new byte[]{0x00, 0x35, 0x6A, 0x0F};
    private static final String HEX_STRING = "00356A0F";

    @DisplayName("should convert byte arrays to hex strings")
    @Test
    void shouldConvertByteArraysToHexStrings() {
        assertEquals(HEX_STRING, ByteUtils.toHexString(BYTE_ARRAY));
    }

    @DisplayName("should convert hex strings to byte arrays")
    @Test
    void shouldConvertHexStringsToByteArrays() {
        assertArrayEquals(BYTE_ARRAY, ByteUtils.fromHexString(HEX_STRING));
    }
}
