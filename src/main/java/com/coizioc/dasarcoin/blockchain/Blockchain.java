package com.coizioc.dasarcoin.blockchain;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class Blockchain {
    public static final String GENESIS_BLOCK_DATA = "Genesis Block";
    List<Block> blocks;
    ProofOfWork pow;

    public Blockchain(ProofOfWork pow) {
        this.pow = pow;
        blocks = new ArrayList<>();
        blocks.add(new Block(pow, GENESIS_BLOCK_DATA, new byte[]{}));
    }

    public void add(String data) {
        Block prevBlock = getLast();
        blocks.add(new Block(pow, data, prevBlock.getHash()));
    }

    public int size() {
        return blocks.size();
    }

    public Block getLast() {
        return blocks.get(blocks.size() - 1);
    }
}
