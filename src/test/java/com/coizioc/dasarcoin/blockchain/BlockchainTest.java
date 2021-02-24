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

@DisplayName("The Blockchain")
class BlockchainTest {
    final long epochSeconds = 1_000_000;
    final String data = "data";
    final ProofOfWork proofOfWork = new ProofOfWorkMock();

    @BeforeEach
    void setup() {
        Timestamp.clock = Clock.fixed(Instant.ofEpochSecond(epochSeconds), ZoneId.of("UTC"));
    }

    @DisplayName("should be instantiated.")
    @Test
    void shouldBeInstantiated() {
        Blockchain blockchain = new Blockchain(proofOfWork);

        assertEquals(1, blockchain.size());

        ProofOfWork.Result result = proofOfWork.run(blockchain.getLast());
        byte[] expectedHash = result.getHash();
        long expectedNonce = result.getNonce();

        Block block = blockchain.getLast();

        assertEquals(Timestamp.clock.instant().getEpochSecond(), block.getTimestamp());
        assertArrayEquals(Blockchain.GENESIS_BLOCK_DATA.getBytes(), block.getData());
        assertArrayEquals(new byte[]{}, block.getPrevBlockHash());
        assertArrayEquals(expectedHash, block.getHash());
        assertEquals(expectedNonce, block.getNonce());
    }

    @DisplayName("should be able to add additional blocks.")
    @Test
    void shouldBeAbleToAddAdditionalBlocks() {
        Blockchain blockchain = new Blockchain(proofOfWork);
        blockchain.add(data);

        assertEquals(2, blockchain.size());

        Block genesisBlock = blockchain.getBlocks().get(0);
        Block newBlock = blockchain.getLast();

        assertEquals(genesisBlock.getHash(), newBlock.getPrevBlockHash());
    }
}
