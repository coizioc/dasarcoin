package com.coizioc.dasarcoin.blockchain;

import com.coizioc.dasarcoin.mock.ProofOfWorkMock;
import com.coizioc.dasarcoin.util.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("The Block")
class BlockTest {
    final long epochSeconds = 1_000_000;
    final String data = "data";
    final byte[] prevBlockHash = new byte[]{};
    final ProofOfWork proofOfWork = new ProofOfWorkMock();

    @BeforeEach
    void setup() {
        Timestamp.clock = Clock.fixed(Instant.ofEpochSecond(epochSeconds), ZoneId.of("UTC"));
    }

    @DisplayName("should be instantiated.")
    @Test
    void shouldBeInstantiated() {
        Block block = new Block(proofOfWork, data, prevBlockHash);

        ProofOfWork.Result result = proofOfWork.run(block);
        byte[] expectedHash = result.getHash();
        long expectedNonce = result.getNonce();

        assertArrayEquals(data.getBytes(), block.getData());
        assertArrayEquals(prevBlockHash, block.getPrevBlockHash());
        assertEquals(Timestamp.clock.instant().getEpochSecond(), block.getTimestamp());
        assertArrayEquals(expectedHash, block.getHash());
        assertEquals(expectedNonce, block.getNonce());
    }
}
