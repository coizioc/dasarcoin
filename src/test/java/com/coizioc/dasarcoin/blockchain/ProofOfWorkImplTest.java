package com.coizioc.dasarcoin.blockchain;

import com.coizioc.dasarcoin.util.Timestamp;
import com.coizioc.dasarcoin.blockchain.Block;
import com.coizioc.dasarcoin.blockchain.ProofOfWork;
import com.coizioc.dasarcoin.blockchain.ProofOfWorkImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("The proof of work")
class ProofOfWorkImplTest {
    final long epochSeconds = 1_000_000;
    final long nonce = 170;
    String data = "uirieotyw9gjdskdlghwo";
    byte[] prevBlockHash = new byte[]{};
    byte[] hash = {
            0, -115, -42, 37, 34, 25, -31, -120,
            43, 89, 66, -39, -89, -110, -73, -9,
            -128, 18, 2, -86, -86, 120, -74, 44,
            78, 92, -92, 20, -91, -9, 117, 107
    };

    @BeforeEach
    void setup() {
        Timestamp.clock = Clock.fixed(Instant.ofEpochSecond(epochSeconds), ZoneId.of("UTC"));
    }

    @DisplayName("should calculate a hash")
    @Test
    void shouldCalculateAHash() {
        ProofOfWork proofOfWork = new ProofOfWorkImpl(8);

        Block block = new Block(proofOfWork, data, prevBlockHash);
        ProofOfWork.Result result = proofOfWork.run(block);

        assertArrayEquals(hash, result.getHash());
        assertEquals(nonce, result.getNonce());
    }

    @DisplayName("should validate a block")
    @Test
    void shouldValidateABlock() {
        ProofOfWork proofOfWork = new ProofOfWorkImpl(8);

        Block block = new Block(proofOfWork, data, prevBlockHash);

        assertTrue(proofOfWork.validate(block));
    }
}
